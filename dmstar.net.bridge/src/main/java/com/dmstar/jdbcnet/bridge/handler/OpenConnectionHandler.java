package com.dmstar.jdbcnet.bridge.handler;

import com.dmstar.jdbcnet.bridge.manager.ObjectManager;
import com.dmstar.jdbcnet.bridge.Handler;
import proto.database.Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.Properties;

public class OpenConnectionHandler implements Handler<Database.OpenConnectionResponse> {
    @Override
    public Database.OpenConnectionResponse handle(byte[] reqData) throws Exception {
        Database.OpenConnectionRequest request = Database.OpenConnectionRequest.parseFrom(reqData);
        Properties properties = new Properties();
        for (int i = 0; i < request.getPropertiesCount(); i++) {
            Database.Map m = request.getProperties(i);
            properties.put(m.getKey(), m.getValue());
        }

        Connection connection = DriverManager.getConnection(request.getJdbcUrl(), properties);
        connection.setAutoCommit(true);

        String connectionId = ObjectManager.putConnection(connection);
        DatabaseMetaData metaData = connection.getMetaData();

        return Database.OpenConnectionResponse.newBuilder()
                .setConnectionId(connectionId)
                .setCatalog(connection.getCatalog() == null ? "" : connection.getCatalog())
                .setDatabaseMajorVersion(metaData.getDatabaseMajorVersion())
                .setDatabaseMinorVersion(metaData.getDatabaseMinorVersion())
                .setDatabaseProductVersion(metaData.getDatabaseProductVersion())
                .setDatabaseProductName(metaData.getDatabaseProductName())
                .build();
    }
}
