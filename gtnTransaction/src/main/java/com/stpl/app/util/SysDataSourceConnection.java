/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class DataSourceConnection.
 *
 * @author lokeshwari
 */
public final class SysDataSourceConnection {

     private static DataSource dataSource;
     private static final Logger LOGGER = Logger.getLogger(SysDataSourceConnection.class);
    static {
        try {
            dataSource = (DataSource) new InitialContext().lookup(System.getProperty("sys.schema.jndi.name"));
        }
        catch (NamingException e) { 
           LOGGER.error(e);
        }
    }

    public static Connection getConnection() throws SQLException {     
       return dataSource.getConnection();
    }
}
