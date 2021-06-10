using dmstar.net.Data;
using dmstar.net.Data.Converters;
using dmstar.net.Proto;
using dmstar.net.Utilities;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace dmstar
{

    public class SQLConnection : DbConnection, ICloneable
    {
        #region Fields
        private string _database;
        private string _serverVersion;
        private ConnectionState _state = ConnectionState.Closed;
        private bool _isDisposed;
        #endregion

        static SQLConnection()
        {
            Util.init();
        }

        #region Properties
        public override string Database
        {
            get
            {
                CheckOpen();
                return _database;
            }
        }

        public override string DataSource => ConnectionStringBuilder.DataSource;

        public override string ServerVersion
        {
            get
            {
                CheckOpen();
                return _serverVersion;
            }
        }

        public override string ConnectionString
        {
            get => ConnectionStringBuilder.ToString();
            set => ConnectionStringBuilder = new SQLConnectionStringBuilder(value);
        }

        public SQLConnectionProperties ConnectionProperties { get; set; } = new SQLConnectionProperties();

        internal SQLConnectionStringBuilder ConnectionStringBuilder { get; set; } = new SQLConnectionStringBuilder();

        public override ConnectionState State => _state;

        internal string ConnectionId { get; private set; }

        internal SQLTransaction CurrentTransaction { get; private set; }

        #endregion

        #region Constructor
        public SQLConnection()
        {
        }

        public SQLConnection(string connectionString, IEnumerable<KeyValuePair<string, string>> connectionProperties = null) : this(new SQLConnectionStringBuilder(connectionString), connectionProperties)
        {
        }

        public SQLConnection(SQLConnectionStringBuilder connectionStringBuilder, IEnumerable<KeyValuePair<string, string>> connectionProperties = null) : this()
        {
            ConnectionStringBuilder = connectionStringBuilder;
            ConnectionProperties = connectionProperties == null ? new SQLConnectionProperties() : new SQLConnectionProperties(connectionProperties);
        }
        #endregion

        #region Public Methods
        public override DataTable GetSchema()
        {
            return null;
        }

        public override DataTable GetSchema(string collectionName)
        {
            return null;
        }

        public override DataTable GetSchema(string collectionName, string[] restrictionValues)
        {
            return null;
        }

        public override void Open()
        {
            try
            {
                OpenAsync().Wait();
            }
            catch (AggregateException e)
            {
                var innerException = e.Flatten().InnerException;

                if (innerException != null)
                    throw innerException;
            }
        }

        public override async Task OpenAsync(CancellationToken cancellationToken)
        {
            CheckDispose();

            await Task.Run(() =>
            {
                try
                {
                    _state = ConnectionState.Connecting;

                    using var timeoutToken = new CancellationTokenSource();
                    timeoutToken.CancelAfter(ConnectionStringBuilder.ConnectionTimeout);
                 
                    var request = new OpenConnectionRequest();
                    request.JdbcUrl = string.Format("jdbc:dmstar://{0}", ConnectionStringBuilder.DataSource);                    
                    foreach (var item in ConnectionProperties)
                    {
                        Map m = new Map();
                        m.Key = item.Key;
                        m.Value = item.Value;
                        request.Properties.Add(m);
                    }
                    var response = Util.request<OpenConnectionResponse>(MsgCode.OpenConnection, request);
                    //cancellationToken: CancellationTokenSource.CreateLinkedTokenSource(cancellationToken, timeoutToken.Token).Token);

                    //}, cancellationToken: CancellationTokenSource.CreateLinkedTokenSource(cancellationToken, timeoutToken.Token).Token);

                    ConnectionId = response.ConnectionId;
                    _serverVersion = response.DatabaseProductVersion;
                    _database = response.Catalog;

                    _state = ConnectionState.Open;
                }
                catch
                {
                    _state = ConnectionState.Broken;
                    throw;
                }
            }, cancellationToken);
        }

        public override void Close()
        {
            try
            {
                CloseAsync().Wait();
            }
            catch (AggregateException e)
            {
                var innerException = e.Flatten().InnerException;

                if (innerException != null)
                    throw innerException;
            }
        }

        public override async Task CloseAsync()
        {
            CheckDispose();

            await Task.Run(() =>
            {
                try
                {
                    if (_state != ConnectionState.Closed)
                    {
                        Util.request<Empty>(MsgCode.CloseConnection, new CloseConnectionRequest
                        {
                            ConnectionId = ConnectionId
                        });
                    }

                    _state = ConnectionState.Closed;
                }
                catch
                {
                    _state = ConnectionState.Broken;
                    throw;
                }
            });
        }

        public new SQLCommand CreateCommand()
        {
            return (SQLCommand)base.CreateCommand();
        }

        public SQLCommand CreateCommand(string commandText)
        {
            var command = CreateCommand();
            command.CommandText = commandText;

            return command;
        }

        protected override DbCommand CreateDbCommand()
        {
            CheckOpen();
            return new SQLCommand(this);
        }

        protected override DbTransaction BeginDbTransaction(IsolationLevel isolationLevel)
        {
            CheckOpen();

            if (CurrentTransaction?.IsDisposeed == false)
                throw new InvalidOperationException("A transaction is already in progress. Nested transactions are not supported.");

            Util.request<Empty>(MsgCode.SetAutoCommit, new SetAutoCommitRequest
            {
                ConnectionId = ConnectionId,
                UseAutoCommit = false
            });

            var originalLevel = Util.request<GetTransactionIsolationResponse>(MsgCode.GetTransactionIsolation, new GetTransactionIsolationRequest
            {
                ConnectionId = ConnectionId
            }).Isolation;

            if (isolationLevel == IsolationLevel.Unspecified)
            {
                isolationLevel = originalLevel switch
                {
                    TransactionIsolation.None => IsolationLevel.Unspecified,
                    TransactionIsolation.ReadCommitted => IsolationLevel.ReadCommitted,
                    TransactionIsolation.ReadUncommitted => IsolationLevel.ReadUncommitted,
                    TransactionIsolation.RepeatableRead => IsolationLevel.RepeatableRead,
                    TransactionIsolation.Serializable => IsolationLevel.Serializable,
                    _ => throw new ArgumentOutOfRangeException(nameof(isolationLevel))
                };
            }
            else if (IsolationLevelConverter.Convert(isolationLevel) != originalLevel)
            {
                Util.request<Empty>(MsgCode.SetTransactionIsolation, new SetTransactionIsolationRequest
                {
                    ConnectionId = ConnectionId,
                    Isolation = IsolationLevelConverter.Convert(isolationLevel)
                });
            }

            CurrentTransaction = new SQLTransaction(this, isolationLevel, originalLevel);

            return CurrentTransaction;
        }

        public override void ChangeDatabase(string databaseName)
        {
            CheckOpen();

            Util.request<Empty>(MsgCode.ChangeCatalog, new ChangeCatalogRequest
            {
                ConnectionId = ConnectionId,
                CatalogName = databaseName
            });

            _database = databaseName;
        }
        #endregion

        #region Private Methods
        private void CheckOpen()
        {
            CheckDispose();

            if (_state == ConnectionState.Closed || _state == ConnectionState.Broken)
                throw new InvalidOperationException("Connection is not open.");
        }

        private void CheckDispose()
        {
            if (_isDisposed)
                throw new ObjectDisposedException(ToString());
        }
        #endregion

        #region IDisposable
        protected override void Dispose(bool disposing)
        {
            if (_isDisposed)
                return;

            if (disposing)
                Close();

            _isDisposed = true;

            base.Dispose(disposing);
        }
        #endregion

        #region ICloneable
        public object Clone()
        {
            CheckDispose();
            return new SQLConnection(ConnectionStringBuilder, ConnectionProperties);
        }
        #endregion
    }
}