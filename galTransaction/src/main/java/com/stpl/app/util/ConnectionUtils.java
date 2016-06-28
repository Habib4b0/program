package com.stpl.app.util;

import org.jboss.logging.Logger;

import com.vaadin.data.util.sqlcontainer.connection.J2EEConnectionPool;

/**
 * To get the connection from connection Pool.
 *
 * @author
 */
public final class ConnectionUtils {
	/** To get Logger information. */
	private static final Logger LOGGER = Logger.getLogger(ConnectionUtils.class);

	/**
	 * Get the connection from J2EE Connection Pool.
	 *
	 * @return connection Pool
	 */
	public static J2EEConnectionPool getConnection() throws Exception {
		LOGGER.info("Entering getConnection ");
		final J2EEConnectionPool connectionPool = new J2EEConnectionPool("java:jboss/datasources/jdbc/BPI_R2_APP_DEV");
		LOGGER.info("Ends getConnection with connectionPool");
		return connectionPool;
	}

	/**
	 * Calling Constructor.
	 */
	private ConnectionUtils() {

	}

}
