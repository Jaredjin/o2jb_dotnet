package com.dmstar.jdbcnet.bridge.handler;

import com.dmstar.jdbcnet.bridge.Handler;
import com.dmstar.jdbcnet.bridge.manager.ObjectManager;
import proto.statement.Statement;

import java.sql.Connection;
import java.sql.ResultSet;

public class CreateStatementHandler implements Handler<Statement.CreateStatementResponse> {
    @Override
    public Statement.CreateStatementResponse handle(byte[] reqData) throws Exception {
        Statement.CreateStatementRequest request = Statement.CreateStatementRequest.parseFrom(reqData);
        Connection connection = ObjectManager.getConnection(request.getConnectionId());
        java.sql.PreparedStatement statement = connection.prepareStatement(request.getSql(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        String statementId = ObjectManager.putStatement(statement);

        return Statement.CreateStatementResponse.newBuilder()
                .setStatementId(statementId)
                .build();
    }
}
