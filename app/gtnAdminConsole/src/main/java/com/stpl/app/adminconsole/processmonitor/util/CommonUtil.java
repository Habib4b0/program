/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processmonitor.util;

import com.stpl.app.adminconsole.util.QueryReader;
import com.stpl.app.adminconsole.util.SysDataSourceConnection;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Asha
 */
public class CommonUtil {
    
    public static String workFlowQuery(boolean count, int start, int offset,boolean monitor,String orderByColumn,boolean orderBy) throws SQLException {
        String query = QueryReader.getQuery("workFlowQuery");

        try(Connection connection = SysDataSourceConnection.getConnection()){
            query = query.replace("?", connection.getCatalog()); 
        }
        if(!count){
                    if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                        query += " ORDER BY WP.PROCESS_NAME "; 
                    }
                    
                    else {
                        query += " ORDER BY " + orderByColumn + (!orderBy ? " ASC " : " DESC ");
                    }
                    query += "OFFSET " +start+" ROWS FETCH NEXT "+offset+" ROWS ONLY";
                }        
        return query;
    }
    
}
