/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import com.stpl.app.model.NmSalesProjectionMaster;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class NmSalesProjectionMasterFinderImpl extends BasePersistenceImpl<NmSalesProjectionMaster> implements NmSalesProjectionMasterFinder {

    /**
     * ************ BPM Methods************
     */
    public static final Logger LOGGER = Logger.getLogger(NmSalesProjectionMasterFinderImpl.class);

    public Object executeSelectQuery(String query, Object udc1, Object udc2) {
        Session session = null;
        List<Object[]> returnList = new ArrayList<Object[]>();
        try {
            session = openSession();

//            LOGGER.debug("executeSelectQuery "+query);
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

        try {
            session = openSession();

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
    /**
     * ************** END OF BPM METHODS***********
     */
}
