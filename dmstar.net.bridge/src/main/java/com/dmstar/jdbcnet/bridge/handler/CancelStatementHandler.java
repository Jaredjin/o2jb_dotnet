package com.dmstar.jdbcnet.bridge.handler;

import com.dmstar.jdbcnet.bridge.Handler;
import com.dmstar.jdbcnet.bridge.manager.ObjectManager;
import proto.Common;
import proto.statement.Statement;

import java.sql.PreparedStatement;

public class CancelStatementHandler implements Handler<Common.Empty> {
    @Override
    public Common.Empty handle(byte[] reqData) throws Exception {
        Statement.CancelStatementRequest request = Statement.CancelStatementRequest.parseFrom(reqData);

        PreparedStatement statement = ObjectManager.getStatement(request.getStatementId());
        statement.cancel();

        return Common.Empty.newBuilder().build();
    }
}
