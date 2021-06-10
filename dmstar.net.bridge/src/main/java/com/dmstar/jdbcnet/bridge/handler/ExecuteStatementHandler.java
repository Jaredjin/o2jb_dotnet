package com.dmstar.jdbcnet.bridge.handler;

import com.dmstar.jdbcnet.bridge.Handler;
import com.dmstar.jdbcnet.bridge.manager.ObjectManager;
import com.dmstar.jdbcnet.bridge.models.ResultSetEx;
import proto.Common;
import proto.statement.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;

public class ExecuteStatementHandler implements Handler<Statement.ExecuteStatementResponse> {
    @Override
    public Statement.ExecuteStatementResponse handle(byte[] reqData) throws Exception {
        Statement.ExecuteStatementRequest request = Statement.ExecuteStatementRequest.parseFrom(reqData);
        PreparedStatement statement = ObjectManager.getStatement(request.getStatementId());
        statement.setFetchSize(request.getFetchSize() == -1 ? statement.getMaxRows() : request.getFetchSize());

        if (!statement.execute()) {
            Statement.ExecuteStatementResponse.Builder responseBuilder = Statement.ExecuteStatementResponse.newBuilder()
                    .setRecordsAffected(statement.getUpdateCount());

            return responseBuilder.build();
        }

        ResultSetEx resultSet = new ResultSetEx(statement.getResultSet());
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        String resultSetId = ObjectManager.putResultSet(resultSet);

        Statement.ExecuteStatementResponse.Builder responseBuilder = Statement.ExecuteStatementResponse.newBuilder()
                .setResultSetId(resultSetId)
                .setHasRows(resultSet.getHasRows());

        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            String columnName = resultSetMetaData.getColumnName(i);
            String columnLabel = resultSetMetaData.getColumnLabel(i);

            String schemaName = resultSetMetaData.getSchemaName(i);

            responseBuilder.addColumns(Common.JdbcDataColumn.newBuilder()
                    .setOrdinal(i - 1)
                    .setTableName(resultSetMetaData.getTableName(i))
                    .setSchemaName(schemaName == null ? "" : schemaName)
                    .setCatalogName(resultSetMetaData.getCatalogName(i))
                    .setColumnName(columnName)
                    .setColumnLabel(columnLabel)
                    .setColumnDisplaySize(resultSetMetaData.getColumnDisplaySize(i))
                    .setColumnPrecision(resultSetMetaData.getPrecision(i))
                    .setColumnScale(resultSetMetaData.getScale(i))
                    .setDataTypeName(resultSetMetaData.getColumnTypeName(i))
                    .setDataTypeClassName(resultSetMetaData.getColumnClassName(i))
                    .setDataTypeCode(resultSetMetaData.getColumnType(i))
                    .setIsAutoIncrement(resultSetMetaData.isAutoIncrement(i))
                    .setIsCaseSensitive(resultSetMetaData.isCaseSensitive(i))
                    .setIsDefinitelyWritable(resultSetMetaData.isDefinitelyWritable(i))
                    .setIsSearchable(resultSetMetaData.isSearchable(i))
                    .setIsNullable(resultSetMetaData.isNullable(i))
                    .setIsAliased(!columnName.equals(columnLabel))
                    .setIsWritable(resultSetMetaData.isWritable(i))
                    .setIsCurrency(resultSetMetaData.isCurrency(i))
                    .setIsReadOnly(resultSetMetaData.isReadOnly(i))
                    .setIsSigned(resultSetMetaData.isSigned(i))
                    .build());
        }

        return responseBuilder.build();
    }
}
