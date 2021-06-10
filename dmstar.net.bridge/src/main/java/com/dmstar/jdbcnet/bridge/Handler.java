package com.dmstar.jdbcnet.bridge;

public interface Handler<T extends com.google.protobuf.GeneratedMessage> {

    T handle(byte[] reqData) throws Exception;
}
