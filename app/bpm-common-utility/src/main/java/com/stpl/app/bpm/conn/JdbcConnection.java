package com.stpl.app.bpm.conn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcConnection {
	//private static String url = "jdbc:sqlserver://localhost\\SQLExpress;database=Northwind;integratedSecurity=true;";
    //private static String userName = "sa";
    //private static String password = "myPassword";
//	private static String url = "jdbc:sqlserver://GTN-DEV-MSSQL:1433;databaseName=BPIGTN_APP_DEV;";
//	private static String userName = "bpigtn_dev";
//    private static String password = "^Wc2015Dev!-";
    //jdbc:sqlserver://10.0.1.185:1433;databaseName=BPI_R2_APP

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        RunDemo();
    }

    public List<String> getContractType() {
        try {
            return null;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public List<String> getTableName() {
        Connection connection = null;
        ResultSet results = null;
        List<String> list = new ArrayList<String>();
        try {
            connection = getAppConnection();
            Statement statement = connection.createStatement();
            results = statement.executeQuery("SELECT distinct TABLE_NAME FROM information_schema.tables WHERE table_schema = 'dbo' AND table_type = 'BASE TABLE' AND TABLE_NAME NOT LIKE 'ST%' AND TABLE_NAME NOT LIKE 'HIST%'  ORDER BY TABLE_NAME ");
            try {
                while (results.next()) {
                    list.add(results.getString(1).toUpperCase());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection.close();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public List<String> getColumnName(String tableName) {
        Connection connection = null;
        ResultSet results = null;
        List<String> list = new ArrayList<String>();
        try {
//        	com.microsoft.sqlserver.jdbc.SQLServerDriver

            connection = getAppConnection();

            Statement statement = connection.createStatement();
            results = statement.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name ='" + tableName + "'");
            try {
                while (results.next()) {
                    list.add(results.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection.close();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public static Connection getAppConnection() {
        Context initialContext;
        DataSource dataSource = null;
        Connection conn = null;
        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            conn = dataSource.getConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public List<String> getRuleOrRuleGroupValues(String columnName) {
        Connection connection = null;
        ResultSet results = null;
        List<String> list = new ArrayList<String>();
        try {
            connection = getAppConnection();

            Statement statement = connection.createStatement();
            if (columnName.equalsIgnoreCase("ruleGroup")) {
                results = statement.executeQuery(" select distinct RULE_FLOW_GROUP_NAME from HIERARCHY_RULES_DEFINITION ");
            } else {
                results = statement.executeQuery(" select distinct RULE_NAME from HIERARCHY_RULES_DEFINITION ");
            }
            try {
                while (results.next()) {
                    list.add(results.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection.close();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return list;
    }
}
