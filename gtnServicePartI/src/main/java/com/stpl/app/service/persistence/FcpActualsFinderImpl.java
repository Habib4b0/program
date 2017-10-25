/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

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
public class FcpActualsFinderImpl<FcpActuals> extends BasePersistenceImpl implements FcpActualsFinder{
   /**
     * ************ BPM Methods************
     */
    public static final Logger LOGGER = Logger.getLogger(FcpActualsFinderImpl.class);

    public Object executeSelectQuery(String query) {
        Session session = null;
        List<Object[]> returnList = new ArrayList<Object[]>();
        try {
            session = openSession();
            Query sqlQuery = session.createSQLQuery(query);
            returnList = sqlQuery.list();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        } finally {
            closeSession(session);
        }
        return returnList;
    }

    public Object executeBulkUpdateQuery(String query) {
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

    @Override
    public Object executeUpdateQuery(List<StringBuilder> queryList) {
        Session session = null;
        try {
            int affectedRecords=0;
            session = openSession();
            for (StringBuilder builder : queryList) {
                Query sqlQuery = session.createSQLQuery(builder.toString());
                affectedRecords+=sqlQuery.executeUpdate();
            }
           LOGGER.debug("Total Records Updated  --------->" + affectedRecords);
            session.flush();
            return true;
        } catch (Exception ex) {
          LOGGER.error(ex.getMessage());
          LOGGER.error(queryList.toString());
        } finally {
            closeSession(session);
            clearCache();
        }
        return false;
    }

    @Override
    public Object executeUpdateQuery(String query) {
        Session session = null;
        try {
            session = openSession();
            Query sqlQuery = session.createSQLQuery(query);
            int affectedRecords=sqlQuery.executeUpdate();

            LOGGER.debug("Records Updated in executeUpdateQuery--------->" + affectedRecords);

            session.flush();
            return true;
        } catch (Exception ex) {
          LOGGER.error(ex.getMessage());
          LOGGER.error(query);
        } finally {
            closeSession(session);
            clearCache();
        }
        return false;
    }  
    
    
}
