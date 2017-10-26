package com.stpl.app.service.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.model.HelperTable;
import com.stpl.portal.kernel.dao.orm.ORMException;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;

public class HelperTableFinderImpl extends BasePersistenceImpl<HelperTable> implements HelperTableFinder {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HelperTableFinderImpl.class);

    /**
     * Custom query to select records from data base
     *
     * @param query - Framed SQL Query
     * @return List<Object[]> result list
     */
    public List executeSelectQuery(String query) {
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

    public void executeUpdateQuery(String query) {
        Session session = null;
        try {
            session = openSession();

            if (query != null && StringUtils.isNotBlank(query)) {

                Query sqlQuery = session.createSQLQuery(query);

                sqlQuery.executeUpdate();
            }

        } catch (ORMException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        } finally {
            closeSession(session);
            clearCache();
        }
    }

    public int executeUpdateQueryCount(String query) {
        Session session = null;
        int count = 0;
        try {
            session = openSession();

            if (query != null && StringUtils.isNotBlank(query)) {

                Query sqlQuery = session.createSQLQuery(query);

                count=sqlQuery.executeUpdate();
            }

        } catch (ORMException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        } finally {
            closeSession(session);
            clearCache();
            return count;
        }
    }

}
