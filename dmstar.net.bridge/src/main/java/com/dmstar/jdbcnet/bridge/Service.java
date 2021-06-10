package com.dmstar.jdbcnet.bridge;

import com.dmstar.jdbcnet.bridge.handler.*;
import proto.Common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Service {

    private final static Map<Common.MsgCode, Handler> handlerMap = new ConcurrentHashMap<>();

    static {
        handlerMap.put(Common.MsgCode.LoadDriver, new LoadDriverHandler());
        handlerMap.put(Common.MsgCode.OpenConnection, new OpenConnectionHandler());
        handlerMap.put(Common.MsgCode.CloseConnection, new CloseConnectionHandler());
        handlerMap.put(Common.MsgCode.ChangeCatalog, new ChangeCatalogHandler());
        handlerMap.put(Common.MsgCode.SetAutoCommit, new SetAutoCommitHandler());
        handlerMap.put(Common.MsgCode.GetTransactionIsolation, new GetTransactionIsolationHandler());
        handlerMap.put(Common.MsgCode.SetTransactionIsolation, new SetTransactionIsolationHandler());
        handlerMap.put(Common.MsgCode.Rollback, new RollbackHandler());
        handlerMap.put(Common.MsgCode.Commit, new CommitHandler());
        handlerMap.put(Common.MsgCode.CreateStatement, new CreateStatementHandler());
        handlerMap.put(Common.MsgCode.ExecuteStatement, new ExecuteStatementHandler());
        handlerMap.put(Common.MsgCode.CancelStatement, new CancelStatementHandler());
        handlerMap.put(Common.MsgCode.CloseStatement, new CloseStatementHandler());
        handlerMap.put(Common.MsgCode.SetParameter, new SetParameterHandler());
        handlerMap.put(Common.MsgCode.ReadResultSet, new ReadResultSetHandler());
        handlerMap.put(Common.MsgCode.CloseResultSet, new CloseResultSetHandler());
    }

    public static byte[] process(byte[] reqData) {
        Common.ResWrapper.Builder response = Common.ResWrapper.newBuilder();

        if (reqData.length == 0) {
            response.setSuccess(false).setMsg("request data is empty.").build().toByteArray();
        } else {
            try {
                Common.ReqWrapper req = Common.ReqWrapper.parseFrom(reqData);
                Handler handler = handlerMap.get(req.getCode());
                if (handler == null) {
                    response.setSuccess(false).setMsg("not found handler.").build().toByteArray();
                } else {
                    com.google.protobuf.GeneratedMessage resMsg = handler.handle(req.getData().toByteArray());
                    response.setSuccess(true).setData(resMsg.toByteString()).build().toByteArray();
                }
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                response.setSuccess(false).setMsg(sw.toString()).build().toByteArray();
            }
        }

        return response.build().toByteArray();
    }

}
