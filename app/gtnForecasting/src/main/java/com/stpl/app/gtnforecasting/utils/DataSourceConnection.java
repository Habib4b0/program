/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class DataSourceConnection.
 *
 * @author lokeshwari
 */
public final class DataSourceConnection {

    /**
     * The instance.
     */
    private static final DataSourceConnection instance = new DataSourceConnection();
    /**
     * The connection.
     */
    public Connection connection;
    /**
     * The datasource context.
     */
    private final static String DATASOURCECONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConnection.class);

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
    public Connection getConnection() throws NamingException,SQLException {
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
