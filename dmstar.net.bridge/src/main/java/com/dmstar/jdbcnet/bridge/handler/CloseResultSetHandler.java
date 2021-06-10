package com.dmstar.jdbcnet.bridge.handler;

import com.dmstar.jdbcnet.bridge.manager.ObjectManager;
import com.dmstar.jdbcnet.bridge.Handler;
import proto.Common;
import proto.reader.Reader;

import java.sql.ResultSet;

public class CloseResultSetHandler implements Handler<Common.Empty> {
    @Override
    public Common.Empty handle(byte[] reqData) throws Exception {
        Reader.CloseResultSetRequest request = Reader.CloseResultSetRequest.parseFrom(reqData);
        ResultSet resultSet = ObjectManager.getResultSet(request.getResultSetId());
        resultSet.close();

        ObjectManager.removeResultSet(request.getResultSetId());

        return Common.Empty.newBuilder().build();
    }
}
