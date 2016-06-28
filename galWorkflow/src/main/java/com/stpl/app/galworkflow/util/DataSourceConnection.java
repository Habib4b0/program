package com.stpl.app.galworkflow.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class DataSourceConnection {
     private static DataSourceConnection INSTANCE = new DataSourceConnection(); 
   private Connection connection = null;
   private String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
   private DataSource datasource;
    private static final Logger LOGGER = Logger.getLogger(DataSourceConnection.class);
   private DataSourceConnection(){
    }
   
    public static DataSourceConnection getInstance(){    
        return INSTANCE;   
    } 
    
    public  Connection getConnection(){
            Context initialContext = null;
       try {
           initialContext = new InitialContext();
       } catch (NamingException ex) {
          LOGGER.error(ex);
       }
       try {
           datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
       } catch (NamingException ex) {
          LOGGER.error(ex);
       }
            if (datasource != null) {
                try {
                    connection = datasource.getConnection();
                } catch (SQLException ex) {
                    LOGGER.error(ex);
                }
            }
    return connection;
    }
    
}
