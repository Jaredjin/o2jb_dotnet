package com.dmstar.jdbcnet.bridge.handler;

import com.dmstar.jdbcnet.bridge.Handler;
import com.dmstar.jdbcnet.bridge.manager.ObjectManager;
import proto.Common;
import proto.statement.Statement;

import java.sql.PreparedStatement;

public class CloseStatementHandler implements Handler<Common.Empty> {
    @Override
    public Common.Empty handle(byte[] reqData) throws Exception {
        Statement.CloseStatementRequest request = Statement.CloseStatementRequest.parseFrom(reqData);
        PreparedStatement statement = ObjectManager.getStatement(request.getStatementId());
        statement.close();

        ObjectManager.removeStatement(request.getStatementId());

        return Common.Empty.newBuilder().build();
    }
}
