package com.dmstar.jdbcnet.bridge.handler;

import com.dmstar.jdbcnet.bridge.manager.ObjectManager;
import com.dmstar.jdbcnet.bridge.Handler;
import proto.Common;
import proto.database.Database;

import java.sql.Connection;

public class CloseConnectionHandler implements Handler<Common.Empty> {
    @Override
    public Common.Empty handle(byte[] reqData) throws Exception {
        Database.CloseConnectionRequest request = Database.CloseConnectionRequest.parseFrom(reqData);
        Connection connection = ObjectManager.getConnection(request.getConnectionId());
        connection.close();

        ObjectManager.removeConnection(request.getConnectionId());

        return Common.Empty.newBuilder().build();
    }
}
