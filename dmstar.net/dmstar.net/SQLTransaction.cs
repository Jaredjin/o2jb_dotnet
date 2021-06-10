using dmstar.net.Proto;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Text;
using dmstar.net.Utilities;
using dmstar.net.Data.Converters;

namespace dmstar
{
    public class SQLTransaction : DbTransaction
    {
        #region Fields
        private readonly SQLConnection _connection;
        #endregion

        #region Properties
        protected override DbConnection DbConnection => _connection;

        public override IsolationLevel IsolationLevel { get; }

        internal TransactionIsolation OriginalLevel { get; }

        public bool IsDisposeed { get; private set; }
        #endregion

        #region Constructor
        internal SQLTransaction(SQLConnection connection, IsolationLevel IsolationLevel, TransactionIsolation originalLevel)
        {
            _connection = connection;
            this.IsolationLevel = IsolationLevel;
            OriginalLevel = originalLevel;
        }
        #endregion

        #region IDbTransaction
        public override void Commit()
        {
            if (IsDisposeed)
                throw new ObjectDisposedException(ToString());

            TransactionRequest req = new TransactionRequest();
            req.ConnectionId = _connection.ConnectionId;

            Util.request<Empty>(MsgCode.Commit, req);
        }

        public override void Rollback()
        {
            if (IsDisposeed)
                throw new ObjectDisposedException(ToString());

            TransactionRequest req = new TransactionRequest();
            req.ConnectionId = _connection.ConnectionId;

            Util.request<Empty>(MsgCode.Rollback, req);
        }
        #endregion

        #region IDisposable
        protected override void Dispose(bool disposing)
        {
            if (IsDisposeed)
                return;

            if (IsolationLevelConverter.Convert(IsolationLevel) != OriginalLevel)
            {
                SetTransactionIsolationRequest req = new SetTransactionIsolationRequest();
                req.ConnectionId = _connection.ConnectionId;
                req.Isolation = OriginalLevel;

                Util.request<Empty>(MsgCode.SetTransactionIsolation, req);
            }

            Util.request<Empty>(MsgCode.SetAutoCommit, new SetAutoCommitRequest
            {
                ConnectionId = _connection.ConnectionId,
                UseAutoCommit = true
            });

            base.Dispose(disposing);
            IsDisposeed = true;
        }
        #endregion
    }
}
