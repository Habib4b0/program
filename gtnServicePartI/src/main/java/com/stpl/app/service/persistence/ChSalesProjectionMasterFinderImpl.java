/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import com.stpl.app.model.ChSalesProjectionMaster;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class ChSalesProjectionMasterFinderImpl extends BasePersistenceImpl<ChSalesProjectionMaster> implements ChSalesProjectionMasterFinder {

     public static final Logger LOGGER = Logger.getLogger(ChSalesProjectionMasterFinderImpl.class);
     
    public Object executeSelectQuery(String query, Object udc1, Object udc2) {
        Session session = null;
        List<Object[]> returnList = new ArrayList<Object[]>();
        try {
            session = openSession();

            //LOGGER.debug("executeSelectQuery "+query);
            Query sqlQuery = session.createSQLQuery(query);
//			LOGGER.debug("SIZE--------->" + sqlQuery.list().size());
            returnList = sqlQuery.list();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        } finally {
            closeSession(session);
        }
        return returnList;
    }

    public Object executeBulkUpdateQuery(String query, Object udc1, Object udc2) {
        Session session = null;
        LOGGER.debug("Inside executeBulkUpdateQuery");
        try {
            session = openSession();
            //LOGGER.debug(" update  query "+query);
            Query sqlQuery = session.createSQLQuery(query);
            sqlQuery.executeUpdate();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        } finally {
            closeSession(session);
        }
        return true;
    }

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
            LOGGER.error(ex.getMessage());
        } finally {
            closeSession(session);
        }
        return true;
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

}
