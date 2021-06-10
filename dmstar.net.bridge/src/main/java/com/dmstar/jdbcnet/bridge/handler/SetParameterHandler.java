package com.dmstar.jdbcnet.bridge.handler;

import com.dmstar.jdbcnet.bridge.Handler;
import com.dmstar.jdbcnet.bridge.manager.ObjectManager;
import proto.Common;
import proto.statement.Statement;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;

public class SetParameterHandler implements Handler<Common.Empty> {
    @Override
    public Common.Empty handle(byte[] reqData) throws Exception {
        Statement.SetParameterRequest request = Statement.SetParameterRequest.parseFrom(reqData);
        PreparedStatement statement = ObjectManager.getStatement(request.getStatementId());

        int index = request.getIndex();
        String value = request.getValue();

        switch (request.getType()) {
            case INT:
                statement.setInt(index, Integer.parseInt(value));
                break;

            case LONG:
                statement.setLong(index, Long.parseLong(value));
                break;

            case SHORT:
                statement.setShort(index, Short.parseShort(value));
                break;

            case FLOAT:
                statement.setFloat(index, Float.parseFloat(value));
                break;

            case DOUBLE:
                statement.setDouble(index, Double.parseDouble(value));
                break;

            case STRING:
                statement.setString(index, value);
                break;

            case BOOLEAN:
                statement.setBoolean(index, Boolean.parseBoolean(value));

            case TIME:
                statement.setTime(index, Time.valueOf(value));
                break;

            case DATE:
                statement.setDate(index, Date.valueOf(value));
                break;
        }

        return Common.Empty.newBuilder().build();
    }
}
