using dmstar;
using dmstar.net.Proto;
using dmstar.net.Utilities;
using System;
using System.Collections;
using System.Collections.Generic;

namespace dmstar.net.Data
{
    public class SQLDataEnumerator : IEnumerator<JdbcDataRow>
    {
        #region Fields
        private ReadResultSetResponse _currentResponse;
        private IEnumerator<JdbcDataRow> _currentChunk;
        #endregion

        #region Properties
        private SQLConnection Connection { get; }

        private ExecuteStatementResponse Response { get; }

        #endregion

        #region Constructor
        internal SQLDataEnumerator(SQLConnection connection, ExecuteStatementResponse response)
        {
            Connection = connection;
            Response = response;
        }
        #endregion

        #region IEnumerator
        public JdbcDataRow Current { get; private set; }

        object IEnumerator.Current => Current;

        public bool MoveNext()
        {
            if (_currentChunk == null)
            {
                _currentResponse = Util.request<ReadResultSetResponse>(MsgCode.ReadResultSet, new ReadResultSetRequest
                {
                    ChunkSize = Connection.ConnectionStringBuilder.ChunkSize,
                    ResultSetId = Response.ResultSetId
                });

                _currentChunk = _currentResponse.Rows.GetEnumerator();
            }

            if (_currentChunk?.MoveNext() == false)
            {
                if (_currentResponse.IsCompleted)
                    return false;

                _currentChunk = null;
                return MoveNext();
            }

            Current = _currentChunk?.Current;
            return true;
        }

        public void Reset()
        {
            throw new NotSupportedException();
        }
        #endregion

        #region IDisposable
        public void Dispose()
        {
            _currentChunk?.Dispose();
        }
        #endregion
    }
}
