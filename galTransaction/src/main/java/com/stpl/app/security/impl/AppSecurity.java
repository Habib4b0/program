package com.stpl.app.security.impl;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

/**
 * The Class AppSecurity.
 */
public final class AppSecurity {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(AppSecurity.class);

    /**
     * The Constructor.
     */
    private AppSecurity() {

    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public static Connection getConnection() {

        try {
            LOGGER.info("Entering getConnection");
            final Context initialContext = new InitialContext();
            final DataSource dataSource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            /* Establishing db connection  */
            final Connection conn = dataSource.getConnection();
            LOGGER.info("Ends getConnection with conn");
            return conn;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

        return null;
    }

    /**
     * Gets the infra connection.
     *
     * @return the infra connection
     */
    public static Connection getInfraConnection() {

        try {
            LOGGER.info("Entering getInfraConnection");
            final Context initialContext = new InitialContext();
            final DataSource dataSource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/infraDataPool");
            /* Establishing db connection  */
            final Connection sqlconn = dataSource.getConnection();
            LOGGER.info("Ends getInfraConnection with sqlconn");
            return sqlconn;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

        return null;
    }
}
