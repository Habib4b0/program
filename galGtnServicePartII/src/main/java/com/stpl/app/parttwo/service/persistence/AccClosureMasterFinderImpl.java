/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AccClosureMaster;
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
 * @author mohamed.hameed
 */
public class AccClosureMasterFinderImpl extends BasePersistenceImpl<AccClosureMaster> implements AccClosureMasterFinder{

    private static final Logger LOGGER = Logger.getLogger(AccClosureMasterFinderImpl.class);

    /**
     * Custom query to select records from data base
     *
     * @param queryName - Framed SQL Query
     * @return List<Object[]> result list
     */
    public Object executeSelectQuery(List input, String queryName, String quaryName2) {
        Session session = null;
        List<Object[]> returnList = new ArrayList<Object[]>();
        try {
            StringBuilder sql = new StringBuilder();
            if (queryName != null && !queryName.isEmpty()) {
                sql = new StringBuilder(CustomSQLUtil.get(queryName));
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(CustomSQLUtil.get(quaryName2));
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                LOGGER.info("CustomSQLUtil - Name ' " + queryName + " ' Select Query --- > " + sql);
                session = openSession();
                Query sqlQuery = session.createSQLQuery(sql.toString());
                returnList = sqlQuery.list();
            }
        } catch (Exception e) {
            
            LOGGER.error(e);
        } finally {
            closeSession(session);
            clearCache();
        }
        return returnList;
    }

    public Boolean executeUpdateQuery(List input, String queryName) {
        Session session = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            LOGGER.info("CustomSQLUtil - Name ' " + queryName + " ' Update Query --- > " + sql);
            session = openSession();
            Query sqlQuery = session.createSQLQuery(sql.toString());
            int count = sqlQuery.executeUpdate();
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            LOGGER.error(e);
            
        } finally {
            closeSession(session);
            clearCache();
        }
        return Boolean.FALSE;
    }

    public  String getQuery(List input, String queryName) {
        StringBuilder sql = null;
        try {
            sql = new StringBuilder();
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
             LOGGER.info("CustomSQLUtil - Name ' " + queryName + " ' get Query --- > " + sql);
        } catch (Exception ex) {
            LOGGER.error(ex);
                  }
        return sql.toString();
    }
    }
