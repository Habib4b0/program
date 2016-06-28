/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.gtnbalancereport.utils;

import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.CommonDao;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.app.accountclose.dao.impl.CommonDaoImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Gopinath
 */
public class BRUtils {
    final static CommonDao dao = CommonDaoImpl.getInstance();
    static BaseRateDAO BASE_RATE_DAO = new BaseRateDAOImpl();
    private static final Logger LOGGER = Logger.getLogger(BRUtils.class);

    public static List<Object>  getExecuteQuery(final Map<String, String> input, final String queryName) {
        List inputQuery = new ArrayList();
        List<Object> returnList = new ArrayList<Object>();
        StringBuilder queryString = new StringBuilder(dao.getQuery(inputQuery, queryName));
        try {
            if (input != null) {
                for (Map.Entry<String, String> entry : input.entrySet()) {
                    final String string = entry.getKey();
                    final String string1 = entry.getValue();
                    while (queryString.toString().contains(string)) {
                        
                        queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                    }
                }
               
            }

            returnList=(List<Object>) BASE_RATE_DAO.executeSelectQuery( queryString.toString());
            } catch (Exception e) {
                LOGGER.error(e);
        }
        return returnList;
    }
    public static List<Object> getExeList(List<Object> inputQuery,final String queryName){
        List<Object> returnList = new ArrayList<Object>();
       try{
        returnList = (List<Object>) dao.executeSelectQuery(inputQuery, queryName, StringUtils.EMPTY);
       }catch(Exception e){
           LOGGER.error(e);
       } 
        
    
    return returnList;
    }
    
}
