/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

//import com.stpl.app.adminconsole.bpm.dto.VwHelperListDto;
//import com.stpl.app.adminconsole.bpm.logic.BpmLogic;
import com.stpl.app.model.HistRelationshipBuilder;
//import com.stpl.app.adminconsole.relationshipbuilder.util.CommonUtils;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;

/**
 *
 * @author mohamed
 */
public class HistRelationshipBuilderFinderImpl extends BasePersistenceImpl<HistRelationshipBuilder> implements HistRelationshipBuilderFinder{
    private static final Logger LOGGER = Logger.getLogger(HistRelationshipBuilderFinderImpl.class);// Logger
																								// Declaration

	public List findByTableName(String tableName, String columnName) {
		LOGGER.info("Entering findByTableName with P1:String tableName=" + tableName + " P2:String columnName=" + columnName);
		List list = new ArrayList();
		Session session = null;
                String sqlString = StringUtils.EMPTY;
		try {
			session = openSession();
			sqlString = "SELECT DISTINCT " + columnName + " FROM " + tableName + " WHERE " + columnName + " IS NOT NULL";

			LOGGER.info("Query" + sqlString);
			SQLQuery sqlQuery = session.createSQLQuery(sqlString);
			list.addAll(sqlQuery.list());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
                        LOGGER.error(sqlString);
		} finally {
			closeSession(session);
		}
		return list;
	}

        public List findByTableName(String tableName, String columnName, List hierListValues) {
		LOGGER.info("findByTableName started with P1:String tableName=" + tableName + " P2:String columnName=" + columnName + " P3:List hierListValues");
		List list = new ArrayList();
		Session session = null;
		try {
                    session = openSession();

			LOGGER.info("Query" + tableName);
			SQLQuery sqlQuery = session.createSQLQuery(tableName);
			list.addAll(sqlQuery.list());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			LOGGER.error(tableName);
		} finally {
			closeSession(session);
		}
		return list;
	}

	public List findFilteredLevelValues(String query) {
		List list = new ArrayList();
		Session session = null;
		try {
			session = openSession();
			SQLQuery sqlQuery = session.createSQLQuery(query);
			list.addAll(sqlQuery.list());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
                        LOGGER.error(query);
		} finally {
			closeSession(session);
		}
		LOGGER.info("Filtered Values list size " + list.size());
		return list;
	}
	
	public List executeQuery(String query) {
		List list = new ArrayList();
		Session session = null;

		try {
			session = openSession();
			SQLQuery sqlQuery = session.createSQLQuery(query);
			list.addAll(sqlQuery.list());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
                        LOGGER.error(query);
		} finally {
			closeSession(session);
		}
		//LOGGER.info("findByTableName return List list=" + list.size());
		return list;
	} 
        
}
