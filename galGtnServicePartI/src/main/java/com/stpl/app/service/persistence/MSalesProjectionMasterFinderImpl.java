/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.service.persistence;

import com.stpl.app.model.MSalesProjectionMaster;
import com.stpl.portal.kernel.dao.orm.ORMException;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class MSalesProjectionMasterFinderImpl extends BasePersistenceImpl<MSalesProjectionMaster> implements MSalesProjectionMasterFinder {
    
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(MSalesProjectionMasterFinderImpl.class);
    
    public Object executeSelectQuery(String query, Object udc1, Object udc2) {
        Session session = null;
        List<Object[]> returnList = new ArrayList<Object[]>();
        try {
            session = openSession();
           
            if (query != null && StringUtils.isNotBlank(query)) {
                Query sqlQuery = session.createSQLQuery(query);
                returnList = sqlQuery.list();
            }

        } catch (ORMException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        } finally {
            closeSession(session);
            clearCache();
        }
        return returnList;
    }
    
    public Object executeUpdateQuery(List<StringBuilder> queryList, Object obj1, Object obj2) {
        Session session = null;
        try {  
            session = openSession();
            for (StringBuilder builder : queryList) {
                Query sqlQuery = session.createSQLQuery(builder.toString());
                sqlQuery.executeUpdate();                
            }            
            session.flush();
            return true;
        } catch (ORMException ex) {	
            LOGGER.error(ex.getMessage());
            LOGGER.error(queryList.toString());
        } finally {
            closeSession(session);
            clearCache();
        }
        return false;
    }

    public List executeUpdateQuery(String query, Object obj1, Object obj2) {
        Session session = null;
        List list = new ArrayList();
        try {
            session = openSession();
            Query sqlQuery = session.createSQLQuery(query);
            int result=sqlQuery.executeUpdate();  
            list.add(result);
            session.flush();
        } catch (ORMException ex) {
            LOGGER.error(ex.getMessage());	
            LOGGER.error("Query-->> "+query);
        } finally {
            closeSession(session);
            clearCache();
        }
        return list;
    }  
    
    public List getAssumptionResult(List input, String queryName) {
        List list = new ArrayList();
        Session session = null;
        StringBuilder sql = new StringBuilder(CustomSQLUtil.get(queryName));

        try {
            
            if ("update".equals(input.get(0))) {
                for (int i = 1; i < input.size(); i++) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(input.get(i)));
                }
            	LOGGER.info("Query After Change : "+sql.toString());
                session = openSession();
                Query sqlQuery = session.createSQLQuery(sql.toString());
                sqlQuery.executeUpdate();
            } else {
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                session = openSession();
               
                Query sqlQuery = session.createSQLQuery(sql.toString());
                list = sqlQuery.list();
            }

        } catch (Exception ex) {
            LOGGER.error(ex.getCause());
            LOGGER.error(sql.toString());
        } finally {
            closeSession(session);
        }

        return list;
    }
    
    public Object executeUpdateSQL(String query, Object obj1, Object obj2) {
        Session session = null;
        try {
            session = openSession();
            Query sqlQuery = session.createSQLQuery(query);
            sqlQuery.executeUpdate();            
            session.flush();
            return true;
        } catch (ORMException ex) {
            LOGGER.error(ex.getMessage());	
            LOGGER.error(query);
        } finally {
            closeSession(session);
            clearCache();
        }
        return false;
    }
    
   /**
     * ************ BPM Methods************
     */
 public Object executeUpdateQuery(List<?> nmSalesList, Object udc1, Object udc2, Object udc3) {
        Session session = null;
        try {
            session = openSession();
            for (int i = 0; i < nmSalesList.size(); i++) {
                Object nmSalesProjection = nmSalesList.get(i);
                session.saveOrUpdate(nmSalesProjection);
                if (i % 50 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex.getMessage());
        } finally {
            closeSession(session);
        }
        return true;
    }
    /**
     * ************** END OF BPM METHODS***********
     */  
    
}
