/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.Session;
import com.stpl.app.service.persistence.HelperTableFinder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Abishek.Ram
 */
public class HelperTableFinderImpl extends HelperTableFinderBaseImpl implements HelperTableFinder {

    private static final Logger LOGGER = Logger.getLogger(HelperTableFinderImpl.class);

    @Override
    public void executeUpdateQuery(String query) {
        Session session = null;
        try {
            session = openSession();

            if (query != null && StringUtils.isNotBlank(query)) {

                Query sqlQuery = session.createSQLQuery(query);

                sqlQuery.executeUpdate();
            }

        } catch (ORMException e) {

        } finally {
            closeSession(session);
            clearCache();
        }
    }

    @Override
    public int executeUpdateQueryCount(String query) {
        Session session = null;
        int count = 0;
        try {
            session = openSession();

            if (query != null && StringUtils.isNotBlank(query)) {

                Query sqlQuery = session.createSQLQuery(query);

                count = sqlQuery.executeUpdate();
            }

        } catch (ORMException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        } finally {
            closeSession(session);
            clearCache();
           
        }
         return count;
    }

    @Override
    public List executeSelectQuery(String query) {
        Session session = null;
        List<Object[]> returnList = new ArrayList<>();
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

    @Override
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
    public Object executeUpdateEntitiy(Object entity) {
        Session session = null;
        try {
            session = openSession();
            if (entity instanceof Collection) {
                int i = 0;
                for (Object entObject : (Collection) entity) {
                    session.saveOrUpdate(entObject);
                    if (i++ % 50 == 0) {
                        session.flush();
                        session.clear();
                    }
                }
            } else {
                session.saveOrUpdate(entity);
            }

            session.flush();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            closeSession(session);
        }
        return true;
    }

}
