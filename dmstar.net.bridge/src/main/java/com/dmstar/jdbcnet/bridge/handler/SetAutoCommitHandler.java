package com.dmstar.jdbcnet.bridge.handler;

import com.dmstar.jdbcnet.bridge.manager.ObjectManager;
import com.dmstar.jdbcnet.bridge.Handler;
import proto.Common;
import proto.database.Database;

import java.sql.Connection;

public class SetAutoCommitHandler implements Handler<Common.Empty> {
    @Override
    public Common.Empty handle(byte[] reqData) throws Exception {
        Database.SetAutoCommitRequest request = Database.SetAutoCommitRequest.parseFrom(reqData);
        Connection connection = ObjectManager.getConnection(request.getConnectionId());
        connection.setAutoCommit(request.getUseAutoCommit());

        return Common.Empty.newBuilder().build();

    }
}
