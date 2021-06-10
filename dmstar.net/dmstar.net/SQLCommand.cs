using dmstar.net.Data.Utilities;
using dmstar.net.Proto;
using dmstar.net.Utilities;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace dmstar
{
    public class SQLCommand : DbCommand
    {
        #region Fields
        private bool _isDisposed;
        private SQLDataReader _dataReader;
        private SQLTransaction _dbTransaction;
        #endregion

        #region Properties
        public int FetchSize { get; set; }

        public override string CommandText { get; set; }

        public override int CommandTimeout { get; set; }

        public override CommandType CommandType { get; set; }

        public override UpdateRowSource UpdatedRowSource
        {
            get => throw new NotSupportedException();
            set => throw new NotSupportedException();
        }

        protected override DbConnection DbConnection { get; set; }

        protected override DbParameterCollection DbParameterCollection => Parameters;

        public new SQLParameterCollection Parameters { get; } = new SQLParameterCollection();

        protected override DbTransaction DbTransaction
        {
            get => _dbTransaction;
            set
            {
                if (!(value is SQLTransaction SQLTransaction))
                    throw new InvalidOperationException();

                if (!(Connection is SQLConnection jdbcConnection))
                    throw new InvalidOperationException();

                if (jdbcConnection.CurrentTransaction != SQLTransaction)
                    throw new InvalidDataException("The transaction associated with this command is not the connection's active transaction.");

                _dbTransaction = SQLTransaction;
            }
        }

        public override bool DesignTimeVisible
        {
            get => false;
            set => throw new NotSupportedException();
        }

        private bool IsPrepared => StatementId != null;

        private string StatementId { get; set; }
        #endregion

        #region Constructor
        internal SQLCommand(SQLConnection connection)
        {
            Connection = connection;
            FetchSize = connection.ConnectionStringBuilder.FetchSize;
        }
        #endregion

        #region Public Methods
        public override void Prepare()
        {
            if (IsPrepared)
                return;

            if (!(Connection is SQLConnection jdbcConnection))
                throw new InvalidOperationException();

            if (Parameters.Count <= 0)
            {
                CreateStatement(CommandText);
            }
            else
            {
                List<SQLParameter> orderedParameters = Parameters
                    .OfType<SQLParameter>()
                    .OrderBy(x => CommandText.IndexOf(x.ParameterName, StringComparison.Ordinal))
                    .ToList();

                CreateStatement(orderedParameters.Aggregate(CommandText, (x, parameter) => x.Replace(parameter.ParameterName, "?")));

                for (var i = 0; i < orderedParameters.Count; i++)
                {
                    var parameter = orderedParameters[i];

                    Util.request<Empty>(MsgCode.SetParameter, new SetParameterRequest
                    {
                        StatementId = StatementId,
                        Index = i + 1,
                        Value = parameter.Value.ToString(),
                        Type = ParameterTypeUtility.Convert(parameter.DbType)
                    });
                }
            }
        }

        public override int ExecuteNonQuery()
        {
            Task<int> task = ExecuteNonQueryAsync();

            try
            {
                return task.Result;
            }
            catch (AggregateException e)
            {
                var innerException = e.Flatten().InnerException;

                if (innerException != null)
                    throw innerException;

                throw;
            }
        }

        public override async Task<int> ExecuteNonQueryAsync(CancellationToken cancellationToken)
        {
            cancellationToken.ThrowIfCancellationRequested();
            cancellationToken.Register(Cancel);

            await using var dbDataReader = await ExecuteDbDataReaderAsync(CommandBehavior.Default, cancellationToken).ConfigureAwait(false);

            while (await dbDataReader.NextResultAsync(cancellationToken).ConfigureAwait(false))
            {
            }

            return dbDataReader.RecordsAffected;
        }

        public override object ExecuteScalar()
        {
            Task<object> task = ExecuteScalarAsync(CancellationToken.None);

            try
            {
                return task.Result;
            }
            catch (AggregateException e)
            {
                var innerException = e.Flatten().InnerException;

                if (innerException != null)
                    throw innerException;

                throw;
            }
        }

        public override async Task<object> ExecuteScalarAsync(CancellationToken cancellationToken)
        {
            cancellationToken.ThrowIfCancellationRequested();
            cancellationToken.Register(Cancel);
            object result = null;

            await using var dbDataReader = await ExecuteDbDataReaderAsync(CommandBehavior.Default, cancellationToken).ConfigureAwait(false);

            if (await dbDataReader.ReadAsync(cancellationToken).ConfigureAwait(false) && dbDataReader.FieldCount > 0)
            {
                result = dbDataReader.GetValue(0);
            }

            return result;
        }

        protected override DbParameter CreateDbParameter()
        {
            return new SQLParameter();
        }

        protected override DbDataReader ExecuteDbDataReader(CommandBehavior behavior)
        {
            Task<DbDataReader> task = ExecuteDbDataReaderAsync(behavior, CancellationToken.None);

            try
            {
                return task.Result;
            }
            catch (AggregateException e)
            {
                var innerException = e.Flatten().InnerException;

                if (innerException != null)
                    throw innerException;

                throw;
            }
        }

        protected override async Task<DbDataReader> ExecuteDbDataReaderAsync(CommandBehavior behavior, CancellationToken cancellationToken)
        {
            if (!(Connection is SQLConnection jdbcConnection))
                throw new InvalidOperationException();

            if (_dataReader?.IsClosed == false)
                throw new InvalidOperationException("The previously executed DataReader has not been closed yet.");

            if (IsPrepared)
                CloseStatement();

            await PrepareAsync(cancellationToken);

            var response = Util.request<ExecuteStatementResponse>(MsgCode.ExecuteStatement, new ExecuteStatementRequest
            {
                StatementId = StatementId,
                FetchSize = FetchSize
            });

            _dataReader = new SQLDataReader(this, response);
            return _dataReader;
        }

        public override void Cancel()
        {
            if (!(Connection is SQLConnection jdbcConnection))
                throw new InvalidOperationException();


            Util.request<Empty>(MsgCode.CancelStatement, new CancelStatementRequest
            {
                StatementId = StatementId
            });

        }
        #endregion

        #region Private Methods
        private void CreateStatement(string sql)
        {
            if (!(Connection is SQLConnection jdbcConnection))
                throw new InvalidOperationException();

            var response = Util.request<CreateStatementResponse>(MsgCode.CreateStatement, new CreateStatementRequest
            {
                ConnectionId = jdbcConnection.ConnectionId,
                Sql = sql
            });

            StatementId = response.StatementId;
        }

        private void CloseStatement()
        {
            if (!IsPrepared)
                return;

            if (!(Connection is SQLConnection jdbcConnection))
                throw new InvalidOperationException();

            Util.request<Empty>(MsgCode.CloseStatement, new CloseStatementRequest
            {
                StatementId = StatementId
            });

            StatementId = null;
        }
        #endregion

        #region IDisposable
        protected override void Dispose(bool disposing)
        {
            if (_isDisposed)
                return;

            CloseStatement();
            _isDisposed = true;

            base.Dispose(disposing);
        }
        #endregion
    }
}
