package com.stpl.app.gtnworkflow.bpm;

import com.stpl.ifs.ui.util.NumericConstants;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.jboss.logging.Logger;

public class JdbcConnection {

    private static final Logger LOGGER = Logger.getLogger(JdbcConnection.class);

    public List<String> getGetRoleForCurrentTask(long processInstance) {
        List<String> roleList = new ArrayList<>();
        StringBuilder query = new StringBuilder("select pp.task_id,pp.entity_id from task t join PeopleAssignments_PotOwners pp on pp.task_id=t.id ");
        query.append(" where t.processInstanceId = ").append(processInstance).append(" order by t.id desc");
        try (Connection connection = getAppConnection();
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(query.toString());) {
            if (results != null) {
                while (results.next()) {
                    roleList.add(results.getString(NumericConstants.TWO));
                }
            }
            connection.close();

        } catch (Exception ex) {
            LOGGER.error("Exception at JdbcConnection "+ex);
        }
        return roleList;
    }

    public static Connection getAppConnection() {
        Context initialContext;
        DataSource dataSource = null;
        Connection conn = null;
        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/bpmDataPool");
            conn = dataSource.getConnection();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return conn;
    }
}
