/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.app.model.RelationshipBuilder;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author vishalakshi
 */
public class RelationshipBuilderFinderImpl extends BasePersistenceImpl<RelationshipBuilder> implements RelationshipBuilderFinder {
	// @SuppressWarnings("rawtypes")

	private static final Logger LOGGER = Logger.getLogger(RelationshipBuilderFinderImpl.class);// Logger
																								// Declaration

    public List findByTableName(String tableName, String columnName) {
		LOGGER.info("Entering findByTableName with P1:String tableName=" + tableName + " P2:String columnName=" + columnName);
		List list = new ArrayList();
		Session session = null;
                String sqlString = StringUtils.EMPTY;
		try {
			session = openSession();
			sqlString = "SELECT DISTINCT " + columnName + " FROM " + tableName + " WHERE " + columnName + " IS NOT NULL";
                        //GTN-721
//                       String sqlString = "SELECT (CASE WHEN A.LIST_NAME IS NULL THEN Cast("+columnName+" AS VARCHAR(8000)) ELSE DESCRIPTION END) AS OUTP\n"
//                            + "FROM   (SELECT    DISTINCT\n"
//                            + columnName
//                            + "                  B.DESCRIPTION,\n"
//                            + "                  B.LIST_NAME\n"
//                            + "        FROM      "+tableName+" A\n"
//                            + "        LEFT JOIN dbo.HELPER_TABLE B ON Cast(B.HELPER_TABLE_SID AS VARCHAR(50)) = A."+columnName+")A";
                       
                     
			SQLQuery sqlQuery = session.createSQLQuery(sqlString);
			list.addAll(sqlQuery.list());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
                        LOGGER.error(sqlString);
		} finally {
			closeSession(session);
		}
		//LOGGER.info("findByTableName return List list=" + list.size());
		return list;
	}

//	public List findByTableName(String tableName, String columnName, List hierListValues) {
//		LOGGER.info("findByTableName started with P1:String tableName=" + tableName + " P2:String columnName=" + columnName + " P3:List hierListValues");
//		List list = new ArrayList();
//		Session session = null;
//
//		try {
//			String hierarchyType = hierListValues.get(0).toString();
//			String hierarchyCategory = hierListValues.get(1).toString();
//			int levelNo = Integer.valueOf(hierListValues.get(2).toString());
//			session = openSession();
//			String sqlString = "SELECT DISTINCT " + columnName + " FROM " + tableName + " WHERE " + columnName + " IS NOT NULL";
////                        //GTN-721
////                       String sqlString = "SELECT (CASE WHEN A.LIST_NAME IS NULL THEN Cast("+columnName+" AS VARCHAR(8000)) ELSE DESCRIPTION END) AS OUTP\n"
////                            + "FROM   (SELECT    DISTINCT\n"
////                            + columnName+","
////                            + "                  B.DESCRIPTION,\n"
////                            + "                  B.LIST_NAME\n"
////                            + "        FROM      "+tableName+" A\n"
////                            + "        LEFT JOIN dbo.HELPER_TABLE B ON Cast(B.HELPER_TABLE_SID AS VARCHAR(50)) = A."+columnName+")A";
//			if (1 == levelNo && "Data Selection".equals(hierarchyCategory) && "Primary".equals(hierarchyType)) {
//				if ("COMPANY_MASTER".equals(tableName)) {
//					sqlString += " and COMPANY_TYPE='GLCOMP'";
//				}
//			}
//                        
//			SQLQuery sqlQuery = session.createSQLQuery(sqlString);
//			list.addAll(sqlQuery.list());
//		} catch (Exception ex) {
//			LOGGER.error(ex.getMessage());
//		} finally {
//			closeSession(session);
//		}
//		LOGGER.info("findByTableName return List list=" + list.size());
//		return list;
//	}
    
    
    	public List findByTableName(String tableName, String columnName, List hierListValues) {
		LOGGER.info("findByTableName started with P1:String tableName=" + tableName + " P2:String columnName=" + columnName + " P3:List hierListValues");
		List list = new ArrayList();
		Session session = null;
                String sqlString = StringUtils.EMPTY;

		try {
			String hierarchyType = hierListValues.get(0).toString();
			String hierarchyCategory = hierListValues.get(1).toString();
			int levelNo = Integer.valueOf(hierListValues.get(2).toString());
			session = openSession();
			//String sqlString = "SELECT DISTINCT " + columnName + " FROM " + tableName + " WHERE " + columnName + " IS NOT NULL";
//                        //GTN-721
                       sqlString = "SELECT (CASE WHEN A.LIST_NAME IS NULL THEN Cast("+columnName+" AS VARCHAR(8000)) ELSE DESCRIPTION END) AS OUTP\n"
                            + "FROM   (SELECT    DISTINCT\n"
                            + columnName+","
                            + "                  B.DESCRIPTION,\n"
                            + "                  B.LIST_NAME\n"
                            + "        FROM      "+tableName+" A\n"
                            + "        LEFT JOIN dbo.HELPER_TABLE B ON Cast(B.HELPER_TABLE_SID AS VARCHAR(50)) = A."+columnName+")A";
//			if (1 == levelNo && "Data Selection".equals(hierarchyCategory) && "Primary".equals(hierarchyType)) {
//				if ("COMPANY_MASTER".equals(tableName)) {
//					sqlString += " and COMPANY_TYPE='GLCOMP'";
//				}
//			}
                        
			SQLQuery sqlQuery = session.createSQLQuery(sqlString);
			list.addAll(sqlQuery.list());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
                        LOGGER.error(sqlString);
		} finally {
			closeSession(session);
		}
		//LOGGER.info("findByTableName return List list=" + list.size());
		return list;
	}
    
    
    
    
}
