package com.dmstar.jdbcnet.bridge.handler;

import com.dmstar.jdbcnet.bridge.Handler;
import proto.driver.Driver;

import java.sql.DriverManager;

public class LoadDriverHandler implements Handler<Driver.LoadDriverResponse> {
    @Override
    public Driver.LoadDriverResponse handle(byte[] reqData) throws Exception {
        Driver.LoadDriverRequest request = Driver.LoadDriverRequest.parseFrom(reqData);
        // Load Class from driver
        java.sql.Driver driver = getDriverByClass(Class.forName(request.getClassName()));

        // Return response
        Driver.LoadDriverResponse response = Driver.LoadDriverResponse.newBuilder()
                .setMajorVersion(driver.getMajorVersion())
                .setMinorVersion(driver.getMinorVersion())
                .build();

        return response;
    }

    java.sql.Driver getDriverByClass(Class clazz) throws ClassNotFoundException {
        java.util.Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();

        while (drivers.hasMoreElements()) {
            java.sql.Driver current = drivers.nextElement();

            if (current.getClass() == clazz)
                return current;
        }

        throw new ClassNotFoundException();
    }
}
