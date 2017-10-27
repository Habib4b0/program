/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.bpm.logic;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.jboss.logging.Logger;

/**
 *
 * @author Asha.Ravi
 */
public class DataSourceConnection {

    /**
     * The instance.
     */
    private static DataSourceConnection instance = new DataSourceConnection();
    /**
     * The connection.
     */
    public Connection connection;
    /**
     * The datasource context.
     */
    private static final String DATASOURCECONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DataSourceConnection.class);

    /**
     * Instantiates a new data source connection.
     */
    private DataSourceConnection() {
    }

    /**
     * Gets the single instance of DataSourceConnection.
     *
     * @return single instance of DataSourceConnection
     */
    public static DataSourceConnection getInstance() {
        return instance;
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     * @throws Exception the exception
     */
    public Connection getConnection() throws NamingException, SQLException {
        Context initialContext;

        LOGGER.debug("Entering getConnection method ");

        initialContext = new InitialContext();
        DataSource datasource;

        datasource = (DataSource) initialContext.lookup(DATASOURCECONTEXT);

        if (datasource != null) {

            connection = datasource.getConnection();

        }
        LOGGER.debug("End of getConnection method");
        return connection;
    }

    /**
     * Sets the connection.
     *
     * @param connection the connection to set
     */
    public void setConnection(final Connection connection) {
        this.connection = connection;
    }

}
