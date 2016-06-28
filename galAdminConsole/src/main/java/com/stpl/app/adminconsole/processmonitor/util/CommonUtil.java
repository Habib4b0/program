/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processmonitor.util;

import com.stpl.app.adminconsole.util.QueryReader;

/**
 *
 * @author Asha
 */
public class CommonUtil {
    
    public static String workFlowQuery(boolean count, int start, int offset) {
        String query = QueryReader.getQuery("workFlowQuery");
        if(!count){
                   query += " ORDER BY WP.PROCESS_NAME OFFSET "+start+" ROWS FETCH NEXT "+offset+" ROWS ONLY"; 
                } 
        return query;
    }
    
}
