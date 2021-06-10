package com.dmstar.jdbcnet.bridge.handler;

import com.dmstar.jdbcnet.bridge.manager.ObjectManager;
import com.dmstar.jdbcnet.bridge.Handler;
import proto.database.Database;

import java.sql.Connection;

public class GetTransactionIsolationHandler implements Handler<Database.GetTransactionIsolationResponse> {
    @Override
    public Database.GetTransactionIsolationResponse handle(byte[] reqData) throws Exception {
        Database.GetTransactionIsolationRequest request = Database.GetTransactionIsolationRequest.parseFrom(reqData);
        Connection connection = ObjectManager.getConnection(request.getConnectionId());

        return Database.GetTransactionIsolationResponse.newBuilder()
                .setIsolation(Database.TransactionIsolation.valueOf(connection.getTransactionIsolation()))
                .build();
    }
}
