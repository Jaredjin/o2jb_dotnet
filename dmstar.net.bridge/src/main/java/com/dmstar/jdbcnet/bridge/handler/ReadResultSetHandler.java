package com.dmstar.jdbcnet.bridge.handler;

import com.dmstar.jdbcnet.bridge.Handler;
import com.dmstar.jdbcnet.bridge.manager.ObjectManager;
import com.dmstar.jdbcnet.bridge.models.ResultSetEx;
import com.dmstar.jdbcnet.bridge.utils.Utils;
import com.google.protobuf.ByteString;
import proto.Common;
import proto.reader.Reader;

import java.sql.Array;
import java.sql.Clob;
import java.sql.ResultSetMetaData;

public class ReadResultSetHandler implements Handler<Reader.ReadResultSetResponse> {
    @Override
    public Reader.ReadResultSetResponse handle(byte[] reqData) throws Exception {
        Reader.ReadResultSetRequest request = Reader.ReadResultSetRequest.parseFrom(reqData);
        ResultSetEx resultSet = ObjectManager.getResultSet(request.getResultSetId());

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        Reader.ReadResultSetResponse.Builder response = Reader.ReadResultSetResponse.newBuilder();
        int rows = request.getChunkSize();

        do {
            if (!resultSet.getHasRows()) {
                break;
            }
            Common.JdbcDataRow.Builder rowBuilder = Common.JdbcDataRow.newBuilder();

            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                Common.JdbcDataItem.Builder item = Common.JdbcDataItem.newBuilder();
                Object value = resultSet.getObject(i);

                if (value == null) {
                    item.setIsNull(true);
                } else if (value.getClass() == byte[].class) {
                    item.setByteArray(ByteString.copyFrom((byte[]) value));
                } else if (value instanceof Clob) {
                    java.io.Reader reader = ((Clob) value).getCharacterStream();

                    StringBuilder builder = new StringBuilder();

                    while (true) {
                        int data = reader.read();
                        if (data == -1) break;
                        builder.append((char) data);
                    }

                    item.setText(builder.toString());
                } else if (value instanceof Array) {
                    ByteString byteString = ByteString.copyFromUtf8("{");

                    Object[] arr = ((Object[]) ((Array) value).getArray());
                    for (Object v : arr) {
                        if (byteString.size() > 1) {
                            byteString = byteString.concat(ByteString.copyFromUtf8(", "));
                        }

                        if (v.getClass() == byte[].class) {
                            byteString = byteString.concat(ByteString.copyFromUtf8(Utils.bytesToHex((byte[]) v)));
                        } else {
                            byteString = byteString.concat(ByteString.copyFromUtf8(v.toString()));
                        }
                    }

                    byteString = byteString.concat(ByteString.copyFromUtf8("}"));

                    item.setTextBytes(byteString);
                } else {
                    item.setText(value.toString());
                }

                rowBuilder.addItems(item.build());
            }

            response.addRows(rowBuilder.build());

            rows--;
            if (rows == 0) {
                break;
            }
        } while (resultSet.next());

        response.setIsCompleted(rows != 0);
        return response.build();
    }
}
