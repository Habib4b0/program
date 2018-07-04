/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mohamed.Shahul
 */
public class SysDataSourceConnection {

    private static DataSource dataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(SysDataSourceConnection.class);

    static {
        try {
            dataSource = (DataSource) new InitialContext().lookup(System.getProperty("sys.schema.jndi.name"));
        } catch (NamingException e) {
            LOGGER.error("Error in static method od sysDataSourceConnection" + e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private SysDataSourceConnection() {
        /*
        Empty Constructor
         */
    }
}
