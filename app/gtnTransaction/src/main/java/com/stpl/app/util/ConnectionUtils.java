package com.stpl.app.util;

import com.stpl.ifs.ui.util.NumericConstants;
import java.sql.Clob;
import org.jboss.logging.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 * To get the connection from connection Pool.
 *
 * @author
 */
public final class ConnectionUtils {

    /**
     * To get Logger information.
     */
    private static final Logger LOGGER = Logger.getLogger(ConnectionUtils.class);
    private static ConnectionUtils instance;

    /**
     * Executes the SQL dynamic Query and returns the builded Query
     *
     * @param dynamicQuery
     * @return connection Pool
     */
    public List getBCP_executableQuery(String dynamicQuery) {
        String[] query = new String[NumericConstants.ONE];
        try (Connection connection = DataSourceConnection.getInstance().getConnection();
                Statement statement = connection.createStatement(); 
                ResultSet result = statement.executeQuery(dynamicQuery)) {
                LOGGER.debug("Entering getConnection ");
                while (result.next()) {
                    Clob clob = result.getClob(NumericConstants.ONE);

                    String res = clob.getSubString(NumericConstants.ONE, Long.valueOf(clob.length()).intValue());

                    if (res != null && !res.isEmpty()) {
                        res = makeExecutable(res);
                    } else {
                        res = StringUtils.EMPTY;
                    }
                    query[0] = res;
                }
            LOGGER.debug("Ends getConnection with connectionPool");
            return Arrays.asList(query);
        } catch (SQLException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return Arrays.asList(query);
    }

    /**
     * Gets the single instance of DataSourceConnection.
     *
     * @return single instance of DataSourceConnection
     */
    public static ConnectionUtils getInstance() {
        if(instance == null){
            instance = new ConnectionUtils();
        }
        return instance;
    }
    /**
     * to make the query in single line 
     * and remove extra space in query
     * @param query
     * @return 
     */
    public String makeExecutable(String query) {
        query = query.replace("\n", "").replace("\r", "");
        query = query.replaceAll(" +", " ");
        query = query.replaceAll(" ,", ",");
        return query;
    }

    /**
     * Calling Constructor.
     */
    private ConnectionUtils() {

    }

}
