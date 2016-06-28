/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.adminconsole.dao.impl.CommonDAOImpl;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author ahalya
 */
public class CommonQueryUtils {
       /**
     * Custom query to select records from data base
     *
     * @param queryName - Framed SQL Query
     * @return List<Object[]> result list
     */
    private static final Logger LOGGER = Logger.getLogger(CommonQueryUtils.class);
    static CommonDAO dao = new CommonDAOImpl();
 
    public static Object executeSelectQuery(List input, String queryName) {
        List<Object[]> returnList = new ArrayList<>();
        try {
            if (!queryName.isEmpty()) {
                returnList = (List<Object[]>) dao.executeSelectQuery(input, queryName, null);
            }
        } catch (Exception e) {
             LOGGER.error(e);
        }
        return returnList;
    }


}
