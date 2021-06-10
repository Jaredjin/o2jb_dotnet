package com.dmstar.jdbcnet.bridge.handler;

import com.dmstar.jdbcnet.bridge.manager.ObjectManager;
import com.dmstar.jdbcnet.bridge.Handler;
import proto.Common;
import proto.database.Database;

import java.sql.Connection;

public class CommitHandler implements Handler<Common.Empty> {
    @Override
    public Common.Empty handle(byte[] reqData) throws Exception {
        Database.TransactionRequest request = Database.TransactionRequest.parseFrom(reqData);
        Connection connection = ObjectManager.getConnection(request.getConnectionId());
        connection.commit();

        return Common.Empty.newBuilder().build();
    }
}
