/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ImtdCfpDetails;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ImtdCfpDetailsLocalServiceUtil;
import com.stpl.app.serviceUtils.CommonUtils;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.serviceUtils.UIUtils;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;

/**
 *
 * @author Asha
 */
public class ImtdCfpDetailsFinderImpl extends BasePersistenceImpl<ImtdCfpDetails> implements ImtdCfpDetailsFinder {

    private static final Logger LOGGER = Logger.getLogger(ImtdCfpDetailsFinderImpl.class);

    public Object deleteAll(String userId, String sessionId, String dateCreated, String operation, Object tempCfpSystemId, Object deleteYesterdayRecordFlag, Object future3, Object future4) {
        Session session = null;
        StringBuilder sql = new StringBuilder("");
        try {
            session = openSession();

            if (Boolean.TRUE.equals(deleteYesterdayRecordFlag)) {
                sql.append("DELETE FROM IMTD_CFP_DETAILS WHERE ");
                sql.append("IMTD_CREATED_DATE='").append(CommonUtils.getYesterdayDate()).append("'; ");
            }
            sql.append("DELETE FROM IMTD_CFP_DETAILS WHERE ");
            if (tempCfpSystemId == null) {
                if (userId != null) {
                    sql.append("USERS_SID='").append(userId).append("'");
                }
                if (sessionId != null) {
                    sql.append(" and SESSION_ID='").append(sessionId).append("'");
                }
                if (dateCreated != null) {
                    sql.append(" and IMTD_CREATED_DATE='").append(dateCreated).append("'");
                }
            } else {
                sql.append("IMTD_CFP_DETAILS_SID='").append(tempCfpSystemId).append("'");
            }
            if ("delete".equals(future3)) {
                sql.append("DELETE FROM dbo.IMTD_CFP_DETAILS WHERE USERS_SID='");
                sql.append(userId);
                sql.append("' and SESSION_ID='");
                sql.append(sessionId);
                sql.append("';");
            }
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.executeUpdate();
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }

    }

    public Object updateAll(String userId, String sessionId, String dateCreated, String operation, Object tempCfpSystemId, Object deleteYesterdayRecordFlag, Object future3, Object future4) {
        Session session = null;
        StringBuilder sql = new StringBuilder("");
        try {
            session = openSession();

            sql.append("UPDATE dbo.IMTD_CFP_DETAILS SET \"OPERATION\"='D', CHECK_RECORD=0 WHERE USERS_SID='");
            sql.append(userId);
            sql.append("' and SESSION_ID='");
            sql.append(sessionId).append("'");
            if (tempCfpSystemId != null && !tempCfpSystemId.equals("null")) {
                sql.append("and CFP_MODEL_SID='").append(tempCfpSystemId).append("'");
            }
            sql.append(";");
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.executeUpdate();
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }

    }

    public Object updateOperation(String cfpMasterSystemId, String sessionId, String createdDate, String operation, Object future1, Object future2, Object future3, Object future4) {

        Session session = null;
        StringBuilder sql = new StringBuilder();
        try {
            session = openSession();
            sql.append("UPDATE dbo.CFP_DETAILS SET INBOUND_STATUS='D' where CFP_MODEL_SID= ");
            sql.append("'").append(cfpMasterSystemId).append("';");

            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.executeUpdate();
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }

    }

    public Object updateForPopulate(String userId, String sessionId, String createdDate, String operation, Object searchField, Object value, Object date, Object future4) {

        Session session = null;
        StringBuilder sql = new StringBuilder();
        try {
            session = openSession();

            sql.append("UPDATE IMTD_CFP_DETAILS SET ");
            if (searchField.toString().equals("CFP Start Date")) {
                sql.append("CFP_DETAILS_START_DATE = '").append(date.toString()).append("' ");
            }
            if (searchField.toString().equals("CFP End Date")) {
                sql.append("CFP_DETAILS_END_DATE = '").append(date.toString()).append("' ");
            }
            if (searchField.toString().equals("CFP Status")) {
                sql.append("CFP_DETAILS_ATTACHED_STATUS = '").append(value).append("' ");
            }
            sql.append(",OPERATION = ( CASE\n"
                    + "                       WHEN CFP_DETAILS_SID <> 0 THEN 'U'\n"
                    + "                       ELSE 'A'\n"
                    + "                     END )");
            if (userId != null) {
                sql.append("where USERS_SID='").append(userId).append("'");
            }
            if (sessionId != null) {
                sql.append(" and SESSION_ID='").append(sessionId).append("'");
            }
            if (createdDate != null) {
                sql.append(" and IMTD_CREATED_DATE='").append(createdDate).append("'");
            }
            sql.append(" and CHECK_RECORD=1");
            sql.append(" and OPERATION <> 'F';");
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.executeUpdate();
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    public Object updateForPopulateAll(String userId, String sessionId, String createdDate, String operation, Object searchField, Object value, Object date, Object future4) {
        Session session = null;
        StringBuilder sql = new StringBuilder();
        try {
            session = openSession();

            sql.append("UPDATE IMTD_CFP_DETAILS SET ");
            if (future4 != null) {
                if ("check".equalsIgnoreCase(String.valueOf(future4))) {
                    sql.append("CHECK_RECORD=1");
                } else if ("uncheck".equalsIgnoreCase(String.valueOf(future4))) {
                    sql.append("CHECK_RECORD=0");
                }

            } else {
                if (searchField.toString().equals("CFP Start Date")) {
                    sql.append("CFP_DETAILS_START_DATE = '").append(date.toString()).append("' ");
                }
                if (searchField.toString().equals("CFP End Date")) {
                    sql.append("CFP_DETAILS_END_DATE = '").append(date.toString()).append("' ");
                }
                if (searchField.toString().equals("CFP Status")) {
                    sql.append("CFP_DETAILS_ATTACHED_STATUS = '").append(value.toString()).append("' ");
                }
                sql.append(", OPERATION = ( CASE\n"
                        + "                       WHEN CFP_DETAILS_SID <> 0 THEN 'U'\n"
                        + "                       ELSE 'A'\n"
                        + "                     END )");
            }

            if (userId != null) {
                sql.append("where USERS_SID='").append(userId).append("'");
            }
            if (sessionId != null) {
                sql.append(" and SESSION_ID='").append(sessionId).append("'");
            }
            if (createdDate != null) {
                sql.append(" and IMTD_CREATED_DATE='").append(createdDate).append("'");
            }
            sql.append("and \"OPERATION\" NOT IN ('D','F');");

            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.executeUpdate();
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    public List getCompanyLazyList(int start, int offset, Object userSessionArray, Object operation, Object searchValue, String column, String orderBy, Map<Object, Object> filterMap) {
        Session session = null;
        StringBuilder sql = new StringBuilder();
        try {

            int tradeClass;
            int companyType;
            int companyStatus;
            int companyCategory;
            int companyGroup;
            String companyId = String.valueOf(filterMap.get(ConstantsUtils.COMPANY_ID));
            String companyNo = String.valueOf(filterMap.get(ConstantsUtils.COMPANY_NO));
            String companyName = String.valueOf(filterMap.get(ConstantsUtils.COMPANY_NAME));
            if ("".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_NO))) || "null".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_NO)))) {
                companyNo = "%";
            }
            if ("".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_NAME))) || "null".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_NAME)))) {
                companyName = "%";
            }

            if ("".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_ID))) || "null".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_ID)))) {
                companyId = "%";
            }

            if ("".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_TYPE_VALUE))) || "null".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_TYPE_VALUE)))) {
                companyType = 0;
            } else {
                companyType = Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_TYPE_VALUE)));
            }
            if ("".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_STATUS_VALUE))) || "null".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_STATUS_VALUE)))) {
                companyStatus = 0;
            } else {
                companyStatus = Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_STATUS_VALUE)));
            }

            if ("".equals(String.valueOf(filterMap.get("tradeClass"))) || "null".equals(String.valueOf(filterMap.get("tradeClass")))) {
                tradeClass = 0;
            } else {
                tradeClass = Integer.valueOf(String.valueOf(filterMap.get("tradeClass")));
            }

            if ("".equals(String.valueOf(filterMap.get("companyCategory"))) || "null".equals(String.valueOf(filterMap.get("companyCategory")))) {
                companyCategory = 0;
            } else {

                companyCategory = Integer.valueOf(String.valueOf(filterMap.get("companyCategory")));
            }

            if ("".equals(String.valueOf(filterMap.get("companyGroup"))) || "null".equals(String.valueOf(filterMap.get("companyGroup")))) {
                companyGroup = 0;
            } else {

                companyGroup = Integer.valueOf(String.valueOf(filterMap.get("companyGroup")));
            }

            final Map<String, String> searchMap = new HashMap<String, String>();
            searchMap.put("COMPANY_TYPE", UIUtils.COMPANY_TYPE);
            searchMap.put("COMPANY_STATUS", UIUtils.STATUS);

            session = openSession();
            sql.append("select");
            if (operation.equals("searchResults")) {
                sql.append(" master.COMPANY_MASTER_SID,master.COMPANY_ID,master.COMPANY_NO,master.COMPANY_NAME,master.COMPANY_TYPE,master.COMPANY_STATUS,master.COMPANY_START_DATE,master.COMPANY_END_DATE,"
                        + "htype.DESCRIPTION as ctype, hstatus.DESCRIPTION as cstatus,hcategory.DESCRIPTION as category,hgroup.DESCRIPTION as cgroup,htrade.DESCRIPTION as trade  "
                );
            } else {
                sql.append(" count(*)  ");
//                 sql.append(" LEFT JOIN COMPANY_TRADE_CLASS ctc on ctc.COMPANY_MASTER_SID=master.COMPANY_MASTER_SID");
            }

                       sql.append(" from COMPANY_MASTER master JOIN COMPANY_TRADE_CLASS ctc on ctc.COMPANY_MASTER_SID=master.COMPANY_MASTER_SID "
                    + "LEFT JOIN HELPER_TABLE htrade on htrade.HELPER_TABLE_SID=ctc.COMPANY_TRADE_CLASS "
                    + "LEFT JOIN HELPER_TABLE htype on htype.HELPER_TABLE_SID=COMPANY_TYPE "
                    + "LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID=COMPANY_CATEGORY "
                    + "LEFT JOIN HELPER_TABLE hgroup on hgroup.HELPER_TABLE_SID=COMPANY_GROUP "
                    + "LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID=COMPANY_STATUS"
                    + " where ");
                  
                    sql.append(" ctc.COMPANY_TRADE_CLASS = (SELECT\n" 
                    + " TOP 1(CT.COMPANY_TRADE_CLASS)\n" +" FROM\n" 
                    + " COMPANY_TRADE_CLASS CT\n" 
                    + " WHERE\n" 
                    + " CT.COMPANY_MASTER_SID = master.COMPANY_MASTER_SID\n" 
                    + " AND CT.INBOUND_STATUS <> 'D'\n" 
                    + " ORDER BY\n" 
                    + " CT.TRADE_CLASS_START_DATE DESC) AND ");
                     
                  sql.append(" master.INBOUND_STATUS <>'D' and ctc.INBOUND_STATUS <>'D'");

            sql.append(" AND master.COMPANY_NO LIKE '").append(companyNo).append("' AND master.COMPANY_ID LIKE '").append(companyId).append("' AND master.COMPANY_NAME LIKE '").append(companyName).append("'");
            if (companyType != 0) {
                sql.append(" AND master.COMPANY_TYPE = '").append(companyType).append("'");
            }

            if (companyStatus != 0) {
                sql.append(" AND master.COMPANY_STATUS = '").append(companyStatus).append("'");
            }

            if (companyCategory != 0) {
                sql.append(" AND master.COMPANY_CATEGORY = ").append(companyCategory);
            }

            if (companyGroup != 0) {
                sql.append(" AND master.COMPANY_GROUP = ").append(companyGroup);
            }

            if (tradeClass != 0) {
                sql.append(" AND ctc.COMPANY_TRADE_CLASS = ").append(tradeClass);
            }

            if (searchValue instanceof Object[]) {
                Object[] values = (Object[]) searchValue;
                if (!"%".equalsIgnoreCase(values[1].toString()) && ("COMPANY_TYPE".equals(values[0]) || "COMPANY_STATUS".equals(values[0]))) {
                    List returnList = new ArrayList();
                    final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
                    dynamicQuery.add(RestrictionsFactoryUtil.like("listName", searchMap.get(values[0].toString())));
                    dynamicQuery.add(RestrictionsFactoryUtil.like("description", values[1]));
                    dynamicQuery.setProjection(ProjectionFactoryUtil.property("helperTableSid"));
                    List<Integer> result = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);

                    if (!result.isEmpty()) {
                        sql.append(" and ").append(values[0]).append(" in (");
                        Iterator<Integer> list = result.iterator();
                        if (list.hasNext()) {
                            sql.append(list.next());
                            for (; list.hasNext();) {
                                sql.append(",").append(list.next());
                            }
                            sql.append(")");
                        } else {
                            sql.append(")");
                        }
                    } else {
                        if (operation.equals("searchResults")) {
                            return returnList;
                        } else {
                            returnList.add(0);
                            return returnList;
                        }
                    }
                } else if (!"%".equalsIgnoreCase(values[1].toString())) {
                    sql.append(" and ").append(values[0]).append(" like " + "'").append(values[1]).append("'");
                }

            }
//           
            if (operation.equals("searchResults")) {
                sql.append(" order by " + column + " " + orderBy + " ");

                sql.append("OFFSET ").append(start).append(" ROWS ");

                sql.append("FETCH NEXT ").append(offset).append(" ROWS ONLY");
            }

            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }

    }

    public Object updateToCFPDetails(int cfpMasterSystemId, String userId, String sessionId, String createdDate, String operation, Object updateFlag, Object future2, Object future3, Object future4) {
        Session session = null;
        StringBuilder sql = new StringBuilder("");
        try {
            session = openSession();

            sql.append("DELETE FROM dbo.CFP_DETAILS WHERE ");
            sql.append(" CFP_MODEL_SID = ").append(cfpMasterSystemId).append(" ;");
//             }
            sql.append(CustomSQLUtil.get("updateCfpDetailsAttched"));
            if (cfpMasterSystemId != 0) {
                sql.append(cfpMasterSystemId).append(",'A',0,").append(Integer.parseInt(userId)).append(" ");
            }
            sql.append(" from dbo.IMTD_CFP_DETAILS ");

            if (userId != null) {
                sql.append(" where USERS_SID='").append(userId).append("'");
            }
            if (sessionId != null) {
                sql.append(" and SESSION_ID='").append(sessionId).append("'");
            }
            if (createdDate != null) {
                sql.append(" and IMTD_CREATED_DATE='").append(createdDate).append("'");
            }
            sql.append(" and CHECK_RECORD=1 and \"OPERATION\" <> 'D';");
            sql.append(" DELETE FROM dbo.IMTD_CFP_DETAILS ");
            if (userId != null) {
                sql.append(" where USERS_SID='").append(userId).append("'");
            }
            if (sessionId != null) {
                sql.append(" and SESSION_ID='").append(sessionId).append("'");
            }
            if (createdDate != null) {
                sql.append(" and IMTD_CREATED_DATE='").append(createdDate).append("';");
            }

            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.executeUpdate();
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }

    }

    public Object insertTempCfpDetailsInADD(String userId, String sessionId, String createdDate, String cfpAttachedDate, Object searchValues, Object future2, Object future3, Object future4) {
        Session session = null;
        StringBuilder sql = new StringBuilder();
        try {
            session = openSession();

              sql.append("MERGE IMTD_CFP_DETAILS AS T\n"
                    + "USING "
                    + "(SELECT * FROM (select \n" +
"		CM.COMPANY_MASTER_SID,\n" +
"		CM.COMPANY_ID,\n" +
"		CM.COMPANY_NO,\n" +
"		CM.COMPANY_NAME,\n" +
"		CM.COMPANY_TYPE,\n" +
"		CM.COMPANY_STATUS,\n" +
"		CM.COMPANY_CATEGORY,\n" +
"		CM.COMPANY_GROUP,\n" +
"		CM.COMPANY_START_DATE,\n" +
"		CM.COMPANY_END_DATE,\n" +
"		CM.ORGANIZATION_KEY,\n" +
"		CM.LIVES,\n" +
"		CM.FINANCIAL_SYSTEM,\n" +
"		CM.ADDRESS1,\n" +
"		CM.ADDRESS2,\n" +
"		CM.CITY,\n" +
"		CM.STATE,\n" +
"		CM.ZIP_CODE,\n" +
"		CM.COUNTRY REGION_CODE,\n" +
"		CM.LAST_UPDATED_DATE,\n" +
"		CM.INTERNAL_NOTES,\n" +
"		CM.INBOUND_STATUS,\n" +
"		CM.RECORD_LOCK_STATUS,\n" +
"		CM.BATCH_ID,\n" +
"		CM.SOURCE,\n" +
"		CM.CREATED_BY,\n" +
"		CM.CREATED_DATE,\n" +
"		CM.MODIFIED_BY,\n" +
"		CM.MODIFIED_DATE,\n" +
"		htrade.DESCRIPTION AS CFP_DETAILS_TRADE_CLASS,\n" +
                      " ROW_NUMBER() OVER (PARTITION BY CM.COMPANY_MASTER_SID ORDER BY CTC.TRADE_CLASS_START_DATE DESC) AS RN" +
"	from\n" +
"		COMPANY_MASTER CM\n" +
"	JOIN COMPANY_TRADE_CLASS CTC on\n" +
"		CM.COMPANY_MASTER_SID = CTC.COMPANY_MASTER_SID and ctc.INBOUND_STATUS <> 'D' \n" +
"		JOIN HELPER_TABLE htrade on\n" +
"	htrade.HELPER_TABLE_SID = CTC.COMPANY_TRADE_CLASS\n" +
"	where\n" +
"		CM.INBOUND_STATUS <> 'D' ");
            if (searchValues instanceof Object[]) {
                Object[] value = (Object[]) searchValues;
                sql.append(" AND ").append(value[0]).append(" like '").append(value[1]).append("'");
            }
            sql.append("  ) B WHERE B.RN = 1)");
            sql.append("AS S\n"
                    + "ON ( S.COMPANY_MASTER_SID = T.COMPANY_MASTER_SID\n");
            if (future4 != null && !future4.equals("null")) {
                sql.append("   AND T.CFP_MODEL_SID = ").append(future4);
            }

            sql.append(" AND T.USERS_SID ='").append(userId).append("'");
            sql.append(" AND T.SESSION_ID ='").append(sessionId).append("'");

            sql.append(" and t.cfp_details_trade_class=s.CFP_DETAILS_TRADE_CLASS and s.company_start_date=t.COMPANY_START_DATE )");
            sql.append("WHEN MATCHED AND T.OPERATION IN ('F', 'D') THEN\n"
                    + "  UPDATE SET T.OPERATION = 'A',\n"
                    + "             CFP_DETAILS_SID = 0,\n"
                    + "          COMPANY_STATUS = null ,\n"
                    + "          COMPANY_START_DATE = null ,\n"
                    + "          COMPANY_END_DATE = null \n"
                    + "WHEN NOT MATCHED BY TARGET THEN\n"
                    + "INSERT( COMPANY_MASTER_SID,\n"
                    + "          COMPANY_ID,\n"
                    + "          COMPANY_NO,\n"
                    + "          COMPANY_NAME,\n"
                    + "          COMPANY_TYPE,\n"
                    + "          COMPANY_STATUS,\n"
                    + "          COMPANY_START_DATE,\n"
                    + "          COMPANY_END_DATE,\n"
                    + "          CFP_DETAILS_TRADE_CLASS,\n"
                    + "          CFP_DETAILS_ATTACHED_DATE,\n"
                    + "          USERS_SID,\n"
                    + "          SESSION_ID,\n"
                    + "          IMTD_CREATED_DATE,\n"
                    + "          CHECK_RECORD,\n"
                    + "          \"OPERATION\",\n"
                    + "          CFP_DETAILS_CREATED_BY,\n"
                    + "CFP_DETAILS_CREATED_DATE, ");
            if (future4 != null && !future4.equals("null")) {
                sql.append("CFP_MODEL_SID, ");
            }
            sql.append("CFP_DETAILS_SID\n"
                    + ")\n"
                    + "  VALUES(S.COMPANY_MASTER_SID,\n"
                    + "          S.COMPANY_ID,\n"
                    + "          S.COMPANY_NO,\n"
                    + "          S.COMPANY_NAME,\n"
                    + "          S.COMPANY_TYPE,\n"
                    + "          S.COMPANY_STATUS,\n"
                    + "          S.COMPANY_START_DATE,\n"
                    + "          S.COMPANY_END_DATE,\n"
                    + "          S.CFP_DETAILS_TRADE_CLASS,\n");
            // // cfpAttachedDate
            sql.append(" '").append(cfpAttachedDate).append("'");
            // user id
            sql.append(",'").append(userId).append("','");
            // sessionId
            sql.append(sessionId).append("','");
            // temp createdDate
            sql.append(createdDate).append("',");
            // check box and operation flag
            sql.append(0).append(",'A'");
            // craeted by
            sql.append(",'").append(userId).append("','");
            // created date
            sql.append(cfpAttachedDate).append("','");
            if (future4 != null && !future4.equals("null")) {
                sql.append(future4).append("','");
            }
            sql.append(0).append("' );");

            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.executeUpdate();
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    public Object insertTempCfpDetailsInEdit(String userId, String sessionId, String createdDate, String cfpMasterSystemId, Object future1, Object future2, Object future3, Object future4) {
        Session session = null;
        StringBuilder sql = new StringBuilder();
        try {
            session = openSession();
            sql.append("DELETE FROM IMTD_CFP_DETAILS WHERE ");
            sql.append("IMTD_CREATED_DATE='").append(CommonUtils.getYesterdayDate()).append("'; ");

            sql.append(" Delete from dbo.IMTD_CFP_DETAILS where ");
            //userId
            if (userId != null) {
                sql.append("USERS_SID='").append(userId).append("' ");
            }
            //sessionId
            if (sessionId != null) {
                sql.append("and SESSION_ID='").append(sessionId).append("' ");
            }
            //createdDate
            if (createdDate != null) {
                sql.append("and IMTD_CREATED_DATE='").append(createdDate).append("'; ");
            }

            sql.append(CustomSQLUtil.get("insertTempCfpDetailsEdit"));
            // modified date
            if (createdDate != null) {
                sql.append("'").append(createdDate).append("'").append(",");
            }
            // modified by
            if (userId != null) {
                sql.append("'").append(userId).append("'").append(",");
            }
            // user id
            if (userId != null) {
                sql.append("'").append(userId).append("'").append(",");
            }
            // sessionId
            if (sessionId != null) {
                sql.append("'").append(sessionId).append("'").append(",");
            }
            // temp created date
            if (createdDate != null) {
                sql.append("'").append(createdDate).append("'").append(",");
            }
            sql.append(1).append(",");
            if (cfpMasterSystemId != null) {
                sql.append(cfpMasterSystemId);
            }
            sql.append(" from dbo.COMPANY_MASTER cm, dbo.CFP_DETAILS  cfp , dbo.COMPANY_TRADE_CLASS CTC , dbo.HELPER_TABLE HT where cm.INBOUND_STATUS <> 'D' and CTC.INBOUND_STATUS <> 'D' and cm.COMPANY_MASTER_SID=cfp.COMPANY_MASTER_SID and cm.COMPANY_MASTER_SID = CTC.COMPANY_MASTER_SID  and HT.HELPER_TABLE_SID = CTC.COMPANY_TRADE_CLASS");
            if (cfpMasterSystemId != null) {
                sql.append(" and cfp.CFP_MODEL_SID=").append(cfpMasterSystemId);
            }

            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.executeUpdate();
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }

    }

    public Object validateTempCFPDeatils(String userId, String sessionId, String createdDate, String process, Object future1, Object future2, Object future3, Object future4) {
        Session session = null;
        StringBuilder sql = new StringBuilder("");
        try {
            session = openSession();
            if (process.equalsIgnoreCase("tempCount")) {
                sql.append("select count(*) from dbo.IMTD_CFP_DETAILS where OPERATION Not In ('D','F') and");
            }
            if (process.equalsIgnoreCase("checkRecord")) {
                sql.append("select count(*) from dbo.IMTD_CFP_DETAILS where CHECK_RECORD=1 and OPERATION Not In ('D','F') and");
            }
            if (process.equalsIgnoreCase("startDateNull")) {
                sql.append("select COMPANY_ID from dbo.IMTD_CFP_DETAILS where CFP_DETAILS_START_DATE is null and CHECK_RECORD=1 and OPERATION Not In ('D','F') and");
            }
            if (process.equalsIgnoreCase("StatusNull")) {
                sql.append("select COMPANY_ID from dbo.IMTD_CFP_DETAILS where (CFP_DETAILS_ATTACHED_STATUS is null  or CFP_DETAILS_ATTACHED_STATUS ='' or CFP_DETAILS_ATTACHED_STATUS = 0) and CHECK_RECORD=1 and OPERATION Not In ('D','F') and");
            }
            if (process.equalsIgnoreCase("startDateGreaterThanEndDate")) {
                sql.append("select COMPANY_ID from dbo.IMTD_CFP_DETAILS where CFP_DETAILS_START_DATE > CFP_DETAILS_END_DATE and");
            }
            if (process.equalsIgnoreCase("companyDuplicationCheck")) {
                sql.append("select COMPANY_ID from (select COMPANY_ID,CFP_DETAILS_START_DATE,count(*) as countA from IMTD_CFP_DETAILS where ");
            }
            if (userId != null) {
                sql.append("  USERS_SID='").append(userId).append("'");
            }
            if (sessionId != null) {
                sql.append(" and SESSION_ID='").append(sessionId).append("'");
            }
            if (createdDate != null) {
                sql.append(" and IMTD_CREATED_DATE='").append(createdDate).append("'");
            }
            if (process.equalsIgnoreCase("companyDuplicationCheck")) {
                sql.append(" group by COMPANY_ID,CFP_DETAILS_START_DATE) a where a.countA >1;");
            }
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());

            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    public Object getTempCFPLazyList(String cfpMasterSystemId, String start, String offset, String operation, Object column, Object orderBy, Object future1, Object future2, Map<Object, Object> filterMap) {
        Session session = null;
        StringBuilder sql = new StringBuilder("");
        try {
            session = openSession();
//            select cm.COMPANY_SYSTEM_ID,cm.COMPANY_ID,cm.COMPANY_NO,cm.COMPANY_NAME,cm.COMPANY_TYPE,cm.COMPANY_START_DATE,
//cm.COMPANY_END_DATE,cm.COMPANY_STATUS,cfp.TRADING_PARTNER_CONTRACT_NO,cfp.ATTACHED_DATE,cfp.START_DATE,cfp.END_DATE,cfp.ATTACHED_STATUS
// from COMPANY_FAMILYPLAN_DETAILS as cfp , COMPANY_MASTER as cm where  cfp.TRADING_PARTNER_SYSTEM_ID=cm.COMPANY_SYSTEM_ID 
// and cfp.INBOUND_STATUS <>'D' and cfp.COMPANY_FAMILY_PLAN_SYSTEM_ID=211219
            if (orderBy == null) {
                orderBy = "ASC";
            }
            if ("searchResults".equals(operation)) {
                sql.append(" select cm.COMPANY_MASTER_SID,cm.COMPANY_ID,cm.COMPANY_NO,cm.COMPANY_NAME,cm.COMPANY_TYPE,cm.COMPANY_START_DATE as C_StartDate,");
                sql.append("cm.COMPANY_END_DATE as C_EndDate,cm.COMPANY_STATUS,cfp.TRADING_PARTNER_CONTRACT_NO,cfp.COMPANY_CFP_ATTACHED_DATE,cfp.COMPANY_START_DATE,cfp.COMPANY_END_DATE,cfp.COMPANY_CFP_ATTACHED_STATUS,hstatus.DESCRIPTION as cstatus,htype.DESCRIPTION as ctype,hCFPstatus.DESCRIPTION as CFPstatus,");
//                sql.append("cm.COMPANY_END_DATE,cm.COMPANY_STATUS,cfp.COMPANY_CFP_ATTACHED_DATE,cfp.COMPANY_START_DATE,cfp.COMPANY_END_DATE,cfp.COMPANY_CFP_ATTACHED_STATUS");
                sql.append("cfp.MODIFIED_DATE,cfp.MODIFIED_BY,cfp.CREATED_DATE,cfp.CREATED_BY from CFP_DETAILS as cfp JOIN COMPANY_MASTER as cm on cfp.COMPANY_MASTER_SID=cm.COMPANY_MASTER_SID ");
                sql.append(" LEFT JOIN HelPER_TABLE htype on htype.HELPER_TABLE_SID =  cm.COMPANY_TYPE");
                sql.append(" LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID =  cm.COMPANY_STATUS");
                sql.append(" LEFT JOIN HELPER_TABLE hCFPstatus on hCFPstatus.HELPER_TABLE_SID =  cfp.COMPANY_CFP_ATTACHED_STATUS");
                sql.append(" where  cfp.COMPANY_MASTER_SID=cm.COMPANY_MASTER_SID and cfp.INBOUND_STATUS <>'D'");
            } else {
                sql.append("select count(*)");
                sql.append(" from CFP_DETAILS as cfp , COMPANY_MASTER as cm where  cfp.COMPANY_MASTER_SID=cm.COMPANY_MASTER_SID and cfp.INBOUND_STATUS <>'D' ");
            }

            if (filterMap.get("Current") != null && filterMap.get("History") != null && filterMap.get("Future") != null) {
                sql.append(" AND ( '").append(filterMap.get("Current")).append("' BETWEEN cfp.COMPANY_START_DATE AND ISNULL(cfp.COMPANY_END_DATE,'").append(filterMap.get("Current")).append("') ");
                sql.append(" OR ").append(" cfp.COMPANY_END_DATE < '").append(filterMap.get("History")).append("' ");
                sql.append(" OR ").append(" cfp.COMPANY_START_DATE > '").append(filterMap.get("Future")).append("' )");
            } else if ((filterMap.get("Current") != null && filterMap.get("History") != null) || (filterMap.get("History") != null && filterMap.get("Current") != null)) {
                sql.append(" AND ( '").append(filterMap.get("Current")).append("' BETWEEN cfp.COMPANY_START_DATE AND ISNULL(cfp.COMPANY_END_DATE,'").append(filterMap.get("Current")).append("') OR cfp.COMPANY_END_DATE < '").append(filterMap.get("History")).append("') ");
            } else if ((filterMap.get("History") != null && filterMap.get("Future") != null) || (filterMap.get("Future") != null && filterMap.get("History") != null)) {
                sql.append(" AND (").append(" cfp.COMPANY_END_DATE < '").append(filterMap.get("History")).append("' OR cfp.COMPANY_START_DATE > '").append(filterMap.get("Future")).append("') ");;
            } else if ((filterMap.get("Future") != null && filterMap.get("Current") != null) || (filterMap.get("Current") != null && filterMap.get("Future") != null)) {
                sql.append(" AND ( '").append(filterMap.get("Current")).append("' BETWEEN cfp.COMPANY_START_DATE AND ISNULL(cfp.COMPANY_END_DATE,'").append(filterMap.get("Current")).append("') OR cfp.COMPANY_START_DATE > '").append(filterMap.get("Future")).append("') ");
            } else if (filterMap.get("Current") != null) {
                sql.append(" AND '").append(filterMap.get("Current")).append("' BETWEEN cfp.COMPANY_START_DATE AND ISNULL(cfp.COMPANY_END_DATE,'").append(filterMap.get("Current")).append("') ");
            } else if (filterMap.get("History") != null) {
                sql.append(" AND ").append(" cfp.COMPANY_END_DATE < '").append(filterMap.get("History")).append("' ");
            } else if (filterMap.get("Future") != null) {
                sql.append(" AND ").append(" cfp.COMPANY_START_DATE > '").append(filterMap.get("Future")).append("' ");
            }

            if (cfpMasterSystemId != null) {
                sql.append(" and cfp.CFP_MODEL_SID='").append(cfpMasterSystemId).append("'");
            }
            if ("searchResults".equals(operation)) {
//                sql.append(" order by cm.COMPANY_MASTER_SID asc ");
//
//                sql.append("OFFSET ").append(start).append(" ROWS ");
//
//
//                sql.append("FETCH NEXT ").append(offset).append(" ROWS ONLY");
                String orderByColumn = " ORDER BY " + String.valueOf(column) + " " + String.valueOf(orderBy) + " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
                sql.append(orderByColumn);

            }
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());

            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    public Object updateCFPDetails(int cfpMasterSystemId, String userId, String sessionId, String createdDate, String operation, Object updateFlag, Object future2, Object future3, Object future4) {
        Session session = null;
        StringBuilder sql = new StringBuilder();
        try {
            if (operation.equals("Flag")) {
                return updatestatusCFPDetails(cfpMasterSystemId, userId, sessionId, createdDate, operation, updateFlag, future2, future3, future4);
            }
            session = openSession();

            SimpleDateFormat simpledateformat5 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            String currentDate = simpledateformat5.format(new Date());
            sql.append("UPDATE CFP SET CFP.MODIFIED_DATE=").append("convert(datetime,'").append(currentDate).append("'),");
            sql.append(CustomSQLUtil.get("cfpupdate1"));
            sql.append("and tmp.USERS_SID='").append(userId).append("'");
            sql.append(" and tmp.SESSION_ID='").append(sessionId).append("';");
            sql.append("UPDATE CFP SET CFP.MODIFIED_DATE=").append("convert(datetime,'").append(currentDate).append("'),");
            sql.append(CustomSQLUtil.get("cfpupdate2"));
            sql.append("and tmp.USERS_SID='").append(userId).append("'");
            sql.append(" and tmp.SESSION_ID='").append(sessionId).append("';");
            sql.append(CustomSQLUtil.get("cfpupdate3"));
            sql.append(" and S.USERS_SID='").append(userId).append("'");
            sql.append(" and S.SESSION_ID='").append(sessionId).append("'");
            sql.append(CustomSQLUtil.get("cfpupdate4"));
            sql.append("convert(datetime,'").append(currentDate).append("')");
            sql.append(") WHEN MATCHED AND S.CFP_DETAILS_SID=0 AND S.OPERATION='A' AND S.CHECK_RECORD = 1 ");
            sql.append(" and S.USERS_SID='").append(userId).append("'");
            sql.append(" and S.SESSION_ID='").append(sessionId).append("'");
            sql.append("THEN UPDATE SET T.MODIFIED_DATE=").append("convert(datetime,'").append(currentDate).append("'),");
            sql.append(CustomSQLUtil.get("cfpupdate5"));
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.executeUpdate();

            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }

    }

    public Object updatestatusCFPDetails(int cfpMasterSystemId, String userId, String sessionId, String createdDate, String operation, Object updateFlag, Object future2, Object future3, Object future4) {
        Session session = null;
        String sqlQuery = StringUtils.EMPTY;
        try {
            session = openSession();
            sqlQuery = CustomSQLUtil.get("cfpupdate6");

            String sysId = (String.valueOf(cfpMasterSystemId));
            sqlQuery = sqlQuery.replaceFirst("[?]", sysId);
            sqlQuery = sqlQuery.replaceFirst("[?]", userId);
            sqlQuery = sqlQuery.replaceFirst("[?]", sessionId);
            sqlQuery = sqlQuery.replaceFirst("[?]", createdDate);
            sqlQuery = sqlQuery.replaceFirst("[?]", userId);
            sqlQuery = sqlQuery.replaceFirst("[?]", sessionId);
            sqlQuery = sqlQuery.replaceFirst("[?]", createdDate);
//            LOGGER.debug("query------>" + sqlQuery);
            SQLQuery q = session.createSQLQuery(sqlQuery);
            q.executeUpdate();
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sqlQuery);
            return null;
        } finally {
            closeSession(session);
        }
    }

    public List getTempCfpDetails(String userId, String sessionId, int start, int offset, String column, String orderBy, Map<Object, Object> filterMap) {
        Session session = null;
        String sql = "";

//        String cfpNo = String.valueOf(filterMap.get("companyFamilyPlanNo"));
//        if("null".equals(cfpNo)||"".equals(cfpNo)){
//            cfpNo = "%";
//        }
//        String cfpName = String.valueOf(filterMap.get("companyFamilyPlanName"));
//        if("null".equals(cfpNo)||"".equals(cfpNo)){
//            cfpName = "%";
//        }
        String companyId = String.valueOf(filterMap.get("companyId"));
        if ("null".equals(companyId) || "".equals(companyId)) {
            companyId = "%";
        }
        String companyNo = String.valueOf(filterMap.get(ConstantsUtils.COMPANY_NO));
        if ("null".equals(companyNo) || "".equals(companyNo)) {
            companyNo = "%";
        }
        String companyName = String.valueOf(filterMap.get(ConstantsUtils.COMPANY_NAME));
        if ("null".equals(companyName) || "".equals(companyName)) {
            companyName = "%";
        }
        String tpContractName = String.valueOf(filterMap.get("tradingPartnerContractNo"));
        if ("null".equals(tpContractName) || "".equals(tpContractName)) {
            tpContractName = "%";
        }

        String cfpStartDateFrom = "";
        String cfpStartDateTo = "";

        if (filterMap.containsKey("companyFamilyPlanStartDate-From")) {
            cfpStartDateFrom = String.valueOf(filterMap.get("companyFamilyPlanStartDate-From")).trim();
        }
        if (filterMap.containsKey("companyFamilyPlanStartDate-To")) {
            cfpStartDateTo = String.valueOf(filterMap.get("companyFamilyPlanStartDate-To")).trim();
        }

        String cfpEndDateFrom = "";
        String cfpEndDateTo = "";

        if (filterMap.containsKey("companyFamilyPlanEndDate-From")) {
            cfpEndDateFrom = String.valueOf(filterMap.get("companyFamilyPlanEndDate-From")).trim();
        }
        if (filterMap.containsKey("companyFamilyPlanEndDate-To")) {
            cfpEndDateTo = String.valueOf(filterMap.get("companyFamilyPlanEndDate-To")).trim();
        }

        String cfpCompanyStartDateFrom = "";
        String cfpCompanyStartDateTo = "";

        if (filterMap.containsKey("cfpCompanyStartDate-From")) {
            cfpCompanyStartDateFrom = String.valueOf(filterMap.get("cfpCompanyStartDate-From")).trim();
        }
        if (filterMap.containsKey("cfpCompanyStartDate-To")) {
            cfpCompanyStartDateTo = String.valueOf(filterMap.get("cfpCompanyStartDate-To")).trim();
        }

        String cfpCompanyEndDateFrom = "";
        String cfpCompanyEndDateTo = "";

        if (filterMap.containsKey("cfpCompanyEndDate-From")) {
            cfpCompanyEndDateFrom = String.valueOf(filterMap.get("cfpCompanyEndDate-From")).trim();
        }
        if (filterMap.containsKey("cfpCompanyEndDate-To")) {
            cfpCompanyEndDateTo = String.valueOf(filterMap.get("cfpCompanyEndDate-To")).trim();
        }

        String cfpAttachedDateFrom = "";
        String cfpAttachedDateTo = "";

        if (filterMap.containsKey("cfpAttachedDate-From")) {
            cfpAttachedDateFrom = String.valueOf(filterMap.get("cfpAttachedDate-From")).trim();
        }
        if (filterMap.containsKey("cfpAttachedDate-To")) {
            cfpAttachedDateTo = String.valueOf(filterMap.get("cfpAttachedDate-To")).trim();
        }

        int cfpStatus;
        if ("null".equals(String.valueOf(filterMap.get("companyFamilyPlanStatus"))) || "".equals(String.valueOf(filterMap.get("companyFamilyPlanStatus")))) {
            cfpStatus = 0;
        } else {
            cfpStatus = Integer.valueOf(String.valueOf(filterMap.get("companyFamilyPlanStatus")));
        }

        int companyStatus,modifiedBy,createdBy;
        if ("null".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_STATUS_VALUE))) || "".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_STATUS_VALUE)))) {
            companyStatus = 0;
        }else {
            companyStatus = Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_STATUS_VALUE)));
        }
        if ("null".equals(String.valueOf(filterMap.get(ConstantsUtils.MODIFIEDBY))) || "".equals(String.valueOf(filterMap.get(ConstantsUtils.MODIFIEDBY)))) {
            modifiedBy = 0;
        } else {
            modifiedBy = Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.MODIFIEDBY)));
        }
        
        if ("null".equals(String.valueOf(filterMap.get(ConstantsUtils.CREATEDBY))) || "".equals(String.valueOf(filterMap.get(ConstantsUtils.CREATEDBY)))) {
            createdBy = 0;
        } else {
            createdBy = Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.CREATEDBY)));
        }
        String companyType = String.valueOf(filterMap.get(ConstantsUtils.COMPANY_TYPE_VALUE));

        if ("null".equals(companyType) || "".equals(companyType)) {
            companyType = "%";
        }

        try {
            session = openSession();

            if (orderBy == null) {
                orderBy = "ASC";
            }
//            String sqls = "SELECT cm.COMPANY_MASTER_SID, cm.COMPANY_ID, cm.COMPANY_NO, cm.COMPANY_NAME, cm.COMPANY_STATUS, cm.COMPANY_TYPE, htype.DESCRIPTION as ctype, hstatus.DESCRIPTION as cstatus"
//                    + " from COMPANY_MASTER cm LEFT JOIN HelPER_TABLE htype on htype.HELPER_TABLE_SID =  cm.COMPANY_TYPE"
//                    + " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID =  cm.COMPANY_STATUS"
//                    + " WHERE cm.COMPANY_TYPE not in (select HELPER_TABLE_SID from HELPER_TABLE where LIST_NAME = 'COMP_TYPE' and DESCRIPTION ='BUNIT')"
//                    + " AND cm.COMPANY_TYPE not in (select HELPER_TABLE_SID from HELPER_TABLE where LIST_NAME = 'COMP_TYPE' and DESCRIPTION ='" + Constants.MANUFACTURER.toUpperCase() + "')";

            if (filterMap.get("Count") != null && String.valueOf(filterMap.get("Count")).equals("true")) {
                sql += " SELECT COUNT(*) ";
            } else {
                sql += "Select ICD.CHECK_RECORD,ICD.CFP_DETAILS_ATTACHED_STATUS,hstatus.DESCRIPTION as cstatus,ICD.CFP_DETAILS_START_DATE,ICD.CFP_DETAILS_END_DATE,ICD.COMPANY_ID,ICD.COMPANY_NO,"
                        + "ICD.COMPANY_NAME,ICD.COMPANY_START_DATE,ICD.COMPANY_END_DATE,ICD.COMPANY_STATUS,hCstatus.DESCRIPTION as ccstatus,ICD.COMPANY_TYPE,htype.DESCRIPTION as ctype,ICD.TRADING_PARTNER_CONTRACT_NO,"
                        + "ICD.CFP_DETAILS_ATTACHED_DATE,ICD.COMPANY_MASTER_SID,ICD.CFP_MODEL_SID,ICD.CFP_DETAILS_SID,ICD.OPERATION,ICD.CFP_DETAILS_CREATED_DATE,ICD.USERS_SID,ICD.SESSION_ID,ICD.IMTD_CFP_DETAILS_SID,ICD.COMPANY_MASTER_SID,"
                        + "ICD.CFP_DETAILS_CREATED_BY,ICD.CFP_DETAILS_MODIFIED_DATE,ICD.CFP_DETAILS_MODIFIED_BY ";
            }
            sql += "FROM IMTD_CFP_DETAILS ICD LEFT JOIN HelPER_TABLE htype on htype.HELPER_TABLE_SID =  ICD.COMPANY_TYPE"
                    + " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID =  ICD.CFP_DETAILS_ATTACHED_STATUS"
                    + " LEFT JOIN HELPER_TABLE hCstatus on hCstatus.HELPER_TABLE_SID =  ICD.COMPANY_STATUS"
                    + " WHERE ICD.OPERATION NOT IN ('D','F')";

            sql += " AND ICD.USERS_SID = '" + userId + "'";

            sql += " AND ICD.SESSION_ID = '" + sessionId + "'";

            sql += " AND ICD.COMPANY_ID LIKE '" + companyId + "'";

            sql += " AND ICD.COMPANY_NO LIKE '" + companyNo + "'";

            sql += " AND ICD.COMPANY_NAME LIKE '" + companyName + "'";

            if ("%".equals(tpContractName)) {
                sql += " AND ( ICD.TRADING_PARTNER_CONTRACT_NO LIKE '" + tpContractName + "'" + " OR ICD.TRADING_PARTNER_CONTRACT_NO IS NULL ) ";
            } else {
                sql += " AND ICD.TRADING_PARTNER_CONTRACT_NO LIKE '" + tpContractName + "'";
            }

            if (companyStatus != 0) {
                sql += " AND ICD.COMPANY_STATUS LIKE '" + companyStatus + "'";
            } 
            if (createdBy != 0) {
                sql += " AND ICD.CREATED_BY LIKE '" + createdBy + "'";
            }
            if (modifiedBy != 0) {
                sql += " AND ICD.MODIFIED_BY LIKE '" + modifiedBy + "'";
            }

            if (cfpStatus != 0) {
                sql += " AND ICD.CFP_DETAILS_ATTACHED_STATUS LIKE '" + cfpStatus + "'";
            }

            if ("%".equals(companyType)) {
                sql += " AND ICD.COMPANY_TYPE LIKE '" + companyType + "'";
            } else {
                sql += " AND ICD.COMPANY_TYPE = '" + companyType + "'";
            }

            if (!"".equals(cfpStartDateFrom)) {
                sql += "AND ICD.CFP_DETAILS_START_DATE >= '" + cfpStartDateFrom + "'";
            }

            if (!"".equals(cfpStartDateTo)) {
                sql += "AND ICD.CFP_DETAILS_START_DATE <= '" + cfpStartDateTo + "'";
            }

            if (!"".equals(cfpEndDateFrom)) {
                sql += "AND ICD.CFP_DETAILS_END_DATE >= '" + cfpEndDateFrom + "'";
            }

            if (!"".equals(cfpEndDateTo)) {
                sql += "AND ICD.CFP_DETAILS_END_DATE <= '" + cfpEndDateTo + "'";
            }

            if (!"".equals(cfpCompanyStartDateFrom)) {
                sql += "AND ICD.COMPANY_START_DATE >= '" + cfpCompanyStartDateFrom + "'";
            }

            if (!"".equals(cfpCompanyStartDateTo)) {
                sql += "AND ICD.COMPANY_START_DATE <= '" + cfpCompanyStartDateTo + "'";
            }

            if (!"".equals(cfpCompanyEndDateFrom)) {
                sql += "AND ICD.COMPANY_END_DATE >= '" + cfpCompanyEndDateFrom + "'";
            }

            if (!"".equals(cfpCompanyEndDateTo)) {
                sql += "AND ICD.COMPANY_END_DATE <= '" + cfpCompanyEndDateTo + "'";
            }

            if (!"".equals(cfpAttachedDateFrom)) {
                sql += "AND ICD.CFP_DETAILS_ATTACHED_DATE >= '" + cfpAttachedDateFrom + "'";
            }

            if (!"".equals(cfpAttachedDateTo)) {
                sql += "AND ICD.CFP_DETAILS_ATTACHED_DATE <= '" + cfpAttachedDateTo + "'";
            }
            if (filterMap.get("Current") != null && filterMap.get("History") != null && filterMap.get("Future") != null) {
                sql += " AND ( '" + String.valueOf(filterMap.get("Current")) + "' BETWEEN ICD.CFP_DETAILS_START_DATE AND ISNULL(ICD.CFP_DETAILS_END_DATE,'" + String.valueOf(filterMap.get("Current")) + "') ";
                sql += " OR " + " ICD.CFP_DETAILS_END_DATE < '" + filterMap.get("History") + "' ";
                sql += " OR " + " ICD.CFP_DETAILS_START_DATE > '" + filterMap.get("Future") + "' )";
            } else if ((filterMap.get("Current") != null && filterMap.get("History") != null) || (filterMap.get("History") != null && filterMap.get("Current") != null)) {
                sql += " AND ( '" + String.valueOf(filterMap.get("Current")) + "' BETWEEN ICD.CFP_DETAILS_START_DATE AND ISNULL(ICD.CFP_DETAILS_END_DATE,'" + String.valueOf(filterMap.get("Current")) + "') OR ICD.CFP_DETAILS_END_DATE < '" + String.valueOf(filterMap.get("History")) + "') ";
            } else if ((filterMap.get("History") != null && filterMap.get("Future") != null) || (filterMap.get("Future") != null && filterMap.get("History") != null)) {
                sql += " AND (" + " ICD.CFP_DETAILS_END_DATE < '" + String.valueOf(filterMap.get("History")) + "' OR ICD.CFP_DETAILS_START_DATE > '" + String.valueOf(filterMap.get("Future")) + "') ";
            } else if ((filterMap.get("Future") != null && filterMap.get("Current") != null) || (filterMap.get("Current") != null && filterMap.get("Future") != null)) {
                sql += " AND ( '" + String.valueOf(filterMap.get("Current")) + "' BETWEEN ICD.CFP_DETAILS_START_DATE AND ISNULL(ICD.CFP_DETAILS_END_DATE,'" + String.valueOf(filterMap.get("Current")) + "') OR ICD.CFP_DETAILS_START_DATE > '" + String.valueOf(filterMap.get("Future")) + "') ";
            } else if (filterMap.get("Current") != null) {
                sql += " AND '" + String.valueOf(filterMap.get("Current")) + "' BETWEEN ICD.CFP_DETAILS_START_DATE AND ISNULL(ICD.CFP_DETAILS_END_DATE,'" + String.valueOf(filterMap.get("Current")) + "') ";
            } else if (filterMap.get("History") != null) {
                sql += " AND " + " ICD.CFP_DETAILS_END_DATE < '" + String.valueOf(filterMap.get("History")) + "' ";
            } else if (filterMap.get("Future") != null) {
                sql += " AND " + " ICD.CFP_DETAILS_START_DATE > '" + String.valueOf(filterMap.get("Future")) + "' ";
            }

            if (filterMap.get("Count") != null && String.valueOf(filterMap.get("Count")).equals("false")) {
                sql += " ORDER BY " + column + " " + orderBy + " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
            }
            LOGGER.debug("getTempCfpDetails sql" + sql);
            SQLQuery sqlQuery = session.createSQLQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }

    }

    public List getSelectedCompanies(int start, int offset, String userId, String sessionId, String columnName, String orderBy, Map<Object, Object> filterMap, String operation) {
      
        int companyType = 0;
        int companyStatus = 0;
        int companyCategory = 0;
        int companyGroup = 0;
        int tradeClass = 0;
        String companyId = String.valueOf(filterMap.get(ConstantsUtils.COMPANY_ID));
        String companyNo = String.valueOf(filterMap.get(ConstantsUtils.COMPANY_NO));
        String companyName = String.valueOf(filterMap.get(ConstantsUtils.COMPANY_NAME));
        if ("".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_NO))) || "null".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_NO)))) {
            companyNo = "%";
        }

        if ("".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_ID))) || "null".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_ID)))) {
            companyId = "%";
        }

        if ("".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_NAME))) || "null".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_NAME)))) {
            companyName = "%";
        }

        if ("".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_TYPE_VALUE))) || "null".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_TYPE_VALUE)))) {
            companyType = 0;
        } else {
            companyType = Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_TYPE_VALUE)));
        }
        if ("".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_STATUS_VALUE))) || "null".equals(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_STATUS_VALUE)))) {
            companyStatus = 0;
        } else {
            companyStatus = Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.COMPANY_STATUS_VALUE)));
        }
        if ("".equals(String.valueOf(filterMap.get("companyCategory"))) || "null".equals(String.valueOf(filterMap.get("companyCategory")))) {
            companyCategory = 0;
        } else {
            companyCategory = Integer.valueOf(String.valueOf(filterMap.get("companyCategory")));
        }

        if ("".equals(String.valueOf(filterMap.get("companyGroup"))) || "null".equals(String.valueOf(filterMap.get("companyGroup")))) {
            companyGroup = 0;
        } else {
            companyGroup = Integer.valueOf(String.valueOf(filterMap.get("companyGroup")));
        }

        if ("".equals(String.valueOf(filterMap.get("tradeClass"))) || "null".equals(String.valueOf(filterMap.get("tradeClass")))) {
            tradeClass = 0;
        } else {
            tradeClass = Integer.valueOf(String.valueOf(filterMap.get("tradeClass")));
        }

        Session session = null;
        String sql = "";
        try {
            session = openSession();
            if ("count".equals(operation)) {
                sql += " select count(*) ";
            } else {
                sql += "SELECT imtd.COMPANY_MASTER_SID,imtd.COMPANY_NO,imtd.COMPANY_NAME,imtd.COMPANY_TYPE,imtd.COMPANY_STATUS,imtd.IMTD_CFP_DETAILS_SID,hstatus.DESCRIPTION as cstatus,"
                        + "htype.DESCRIPTION as ctype,hcategory.DESCRIPTION as category,hgroup.DESCRIPTION as cgroup,htrade.DESCRIPTION as trade,"
                        + "imtd.COMPANY_ID";
            }
            sql += " FROM IMTD_CFP_DETAILS imtd "
                    + "JOIN COMPANY_MASTER cm on cm.COMPANY_MASTER_SID=imtd.COMPANY_MASTER_SID "
                    + "LEFT JOIN COMPANY_TRADE_CLASS ctc on ctc.COMPANY_MASTER_SID=imtd.COMPANY_MASTER_SID "
                    + "JOIN HELPER_TABLE htrade on htrade.HELPER_TABLE_SID=ctc.COMPANY_TRADE_CLASS "
                    + "AND htrade.DESCRIPTION = imtd.CFP_DETAILS_TRADE_CLASS "
                    + "LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID=cm.COMPANY_CATEGORY "
                    + "LEFT JOIN HELPER_TABLE hgroup on hgroup.HELPER_TABLE_SID=cm.COMPANY_GROUP "
                    + "LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID=imtd.COMPANY_STATUS "
                    + "LEFT JOIN HELPER_TABLE htype on htype.HELPER_TABLE_SID=imtd.COMPANY_TYPE "
                    + "WHERE "
                    + " ctc.COMPANY_TRADE_CLASS = (SELECT\n" 
                    + " TOP 1(CT.COMPANY_TRADE_CLASS)\n" +" FROM\n" 
                    + " COMPANY_TRADE_CLASS CT\n" 
                    + " WHERE\n" 
                    + " CT.COMPANY_MASTER_SID = cm.COMPANY_MASTER_SID\n" 
                    + " AND CT.INBOUND_STATUS <> 'D'\n" 
                    + " ORDER BY\n" 
                    + " CT.TRADE_CLASS_START_DATE DESC) AND "
                    + " USERS_SID = '" + userId + "' AND SESSION_ID = '" + sessionId + "' AND OPERATION NOT IN ('D','F') "
                    + " AND ctc.INBOUND_STATUS <> 'D'\n"
                    + "AND imtd.COMPANY_NO LIKE '" + companyNo + "' AND imtd.COMPANY_ID LIKE '" + companyId + "' AND imtd.COMPANY_NAME LIKE '" + companyName + "'";
                if (companyType != 0) {
                    sql += " AND imtd.COMPANY_TYPE = '" + companyType + "'";
                }

                if (companyStatus != 0) {
                    sql += " AND imtd.COMPANY_STATUS = '" + companyStatus + "'";
                }

                if (companyCategory != 0) {
                    sql += " AND cm.COMPANY_CATEGORY = '" + companyCategory + "'";
                }

                if (companyGroup != 0) {
                    sql += " AND cm.COMPANY_GROUP = '" + companyGroup + "'";
                }

                if (tradeClass != 0) {
                    sql += " AND ctc.COMPANY_TRADE_CLASS = '" + tradeClass + "'";
                }
                if (offset != 0) {
                    sql += "ORDER BY " + columnName + " " + orderBy + " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
                }
           
            LOGGER.debug("getSelectedCompanies" + sql);
            LOGGER.debug("query = = = =>" + sql);
            SQLQuery sqlQuery = session.createSQLQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }
    }

    /**
     * Insert the records into the TempCompanyDetailsAttached from
     * CompanyDetailsAttached.
     *
     * @param input
     * @param future
     * @return
     */
    public Boolean loadTempCompanydetails(final List<Object> input, final Object future) {
        Session session = null;
        Boolean retFlag;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            if ("Add".equals(future)) {
                sql = CustomSQLUtil.get("com.contractDashboard.process.addToTempCFP");
            } else {
                sql = CustomSQLUtil.get("com.contractDashboard.process.loadTempCFP");
            }
            sql = sql.replaceFirst("[?]", input.get(0).toString());
            sql = sql.replaceFirst("[?]", input.get(1).toString());
            sql = sql.replaceFirst("[?]", input.get(2).toString());
            sql = sql.replaceFirst("[?]", input.get(3).toString());
            sql = sql.replaceFirst("[?]", input.get(4).toString());
            if ("Add".equals(future)) {
                sql = sql.replaceFirst("[?]", input.get(5).toString());
                sql = sql.replaceFirst("[?]", input.get(6).toString());
                sql = sql.replaceFirst("[?]", input.get(7).toString());
                sql = sql.replaceFirst("[?]", input.get(8).toString());
                sql = sql.replaceFirst("[?]", input.get(9).toString());
                sql = sql.replaceFirst("[?]", input.get(10).toString());
                sql = sql.replaceFirst("[?]", input.get(11).toString());
            }
            LOGGER.debug("loadTempCompanydetails" + sql);
            SQLQuery q = session.createSQLQuery(sql);
            q.executeUpdate();
            retFlag = true;

        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    /**
     * To perform Mass Populate.
     *
     * @param input
     * @param future
     * @return
     */
    public Boolean massPopulate(final List<Object> input, final Object future) {
        Session session = null;
        Boolean retFlag;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();
            sql = CustomSQLUtil.get("com.contractDashboard.process.populateTempCFP");
            for (Object temp : input) {
                sql = sql.replaceFirst("[?]", temp.toString());
            }

            SQLQuery q = session.createSQLQuery(sql);
            q.executeUpdate();
            retFlag = true;
        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    /**
     * To perform Mass Populate All.
     *
     * @param input
     * @param future
     * @return
     */
    public Boolean massPopulateAll(final List<Object> input, final Object future) {
        Session session = null;
        Boolean retFlag;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();
            if ("CHECK_RECORD".equalsIgnoreCase(input.get(0).toString())) {
                sql += "UPDATE dbo.IMTD_CFP_DETAILS SET CHECK_RECORD=" + input.get(1);
                sql += " WHERE USERS_SID='" + input.get(4) + "' AND SESSION_ID='" + input.get(5) + "'";

            } else {
                sql = CustomSQLUtil.get("com.contractDashboard.process.populateAllTempCFP");
                for (Object temp : input) {
                    sql = sql.replaceFirst("[?]", temp.toString());
                }
            }
            sql += " AND \"OPERATION\" <> 'D';";

            SQLQuery q = session.createSQLQuery(sql);
            q.executeUpdate();
            retFlag = true;

        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    /**
     * To save the CompanyDetailsAttached from Temp Table.
     *
     * @param input
     * @param future
     * @return
     */
    public Boolean saveCompany(final List<Object> input, final Object future) {
        Session session = null;
        Boolean retFlag;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();
            if ("SaveDetails".equals(future)) {
                List<ImtdCfpDetails> saveList = (List<ImtdCfpDetails>) input.get(0);
                for (ImtdCfpDetails temp : saveList) {
                    ImtdCfpDetailsLocalServiceUtil.updateImtdCfpDetails(temp);
                }
                retFlag = true;
            } else if ("Validation".equals(future)) {
                sql = CustomSQLUtil.get("com.contractDashboard.process.saveCFPNullValidation");
                for (Object temp : input) {
                    sql = sql.replaceFirst("[?]", temp.toString());
                }

                SQLQuery q = session.createSQLQuery(sql);
                List<?> temp = q.list();
                retFlag = (Integer) temp.get(0) > 0;
            } else {
                sql = CustomSQLUtil.get(String.valueOf(future));
                for (Object temp : input) {
                    sql = sql.replaceFirst("[?]", temp.toString());
                }

                SQLQuery q = session.createSQLQuery(sql);
                q.executeUpdate();
                retFlag = true;
            }
        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());;
            LOGGER.error(sql);
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    /**
     * To clear the temp table.
     *
     * @param input
     * @param future
     * @return
     */
    public Boolean deleteAll(final List<Object> input, final Object future) {
        Session session = null;
        Boolean retFlag;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();
            if ("Back".equals(future)) {
                sql = CustomSQLUtil.get("com.contractDashboard.process.deleteOnBackTempCFP");
            } else if ("Load".equals(future)) {
                sql = CustomSQLUtil.get("com.contractDashboard.process.deleteOnLoadTempCFP");
            } else {
                sql = CustomSQLUtil.get("com.contractDashboard.process.deleteByDTempCFP");
            }
            sql = sql.replaceFirst("[?]", input.get(0).toString());
            sql = sql.replaceFirst("[?]", input.get(1).toString());
            if ("Load".equals(future)) {
                final Date tempDate = new Date();
                final SimpleDateFormat tempFromat = new SimpleDateFormat("yyyy-MM-dd");
                tempDate.setDate(tempDate.getDate() - 1);
                sql = sql.replaceFirst("[?]", tempFromat.format(tempDate));

            }

            SQLQuery q = session.createSQLQuery(sql);
            q.executeUpdate();
            retFlag = true;

        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    /**
     * To perform update operation for delete.
     *
     * @param input
     * @param future
     * @return
     */
    public Boolean updateAll(final List<Object> input, final Object future) {
        Session session = null;
        Boolean retFlag;
        String upadteQuery = StringUtils.EMPTY;
        String upadteQuery1 = StringUtils.EMPTY;
        try {
            session = openSession();
            upadteQuery = CustomSQLUtil.get("com.contractDashboard.process.updateOperation");
            upadteQuery = upadteQuery.replaceFirst("[?]", input.get(0).toString());
            upadteQuery = upadteQuery.replaceFirst("[?]", input.get(1).toString());
            upadteQuery1 = CustomSQLUtil.get("com.contractDashboard.process.deleteByNotDTempCFP");
            upadteQuery1 = upadteQuery1.replaceFirst("[?]", input.get(0).toString());
            upadteQuery1 = upadteQuery1.replaceFirst("[?]", input.get(1).toString());

            SQLQuery q = session.createSQLQuery(upadteQuery);
            q.executeUpdate();
            SQLQuery q1 = session.createSQLQuery(upadteQuery1);
            q1.executeUpdate();
            retFlag = true;

        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());
            LOGGER.error(upadteQuery);
            LOGGER.error(upadteQuery1);
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    public Object getOverlapedCompanies(final List<Object> input, final Object future) {
        Session session = null;
        List<Object[]> retList;
        String upadteQuery = StringUtils.EMPTY;
        try {
            session = openSession();
            upadteQuery = CustomSQLUtil.get("com.contractDashboard.process.getOverlappedCompines");
            upadteQuery = upadteQuery.replaceFirst("[?]", input.get(0).toString());
            upadteQuery = upadteQuery.replaceFirst("[?]", input.get(1).toString());

            SQLQuery q = session.createSQLQuery(upadteQuery);
            retList = q.list();

        } catch (Exception e) {
            retList = new ArrayList<Object[]>();
            LOGGER.error(e.getMessage());
            LOGGER.error(upadteQuery);
        } finally {
            closeSession(session);
        }
        return retList;
    }

    public List getSelectedCompanies(String userID, String sessionID, int start, int offset, String column, String orderBy, boolean flag, Object future1, Map<String, Object> filterMap, boolean isCount) {
        
        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();
            if (orderBy == null) {
                orderBy = "ASC";
            }
            final SimpleDateFormat tempFromat = new SimpleDateFormat("yyyy-MM-dd");

            if (isCount) {
                sql = "SELECT COUNT(cm.COMPANY_MASTER_SID) ";
            } else {
                sql = "SELECT cm.COMPANY_MASTER_SID, cm.COMPANY_ID, cm.COMPANY_TYPE, cm.COMPANY_NO, cm.COMPANY_NAME, cm.COMPANY_STATUS, cm.IMTD_CFP_DETAILS_SID ,hcategory.DESCRIPTION as ccategory,hgroup.DESCRIPTION as cgroup,htrade.DESCRIPTION as ctrade, cm.CREATED_DATE, cm.CREATED_BY, cm.MODIFIED_DATE,cm.MODIFIED_BY ";
                if (flag) {
                    sql += ", cm.COMPANY_START_DATE, cm.COMPANY_END_DATE, cm.CFP_DETAILS_START_DATE, cm.CFP_DETAILS_END_DATE, cm.CFP_DETAILS_ATTACHED_STATUS, cm.CFP_DETAILS_ATTACHED_DATE, cm.CHECK_RECORD";
                }

                sql += ", htype.DESCRIPTION as ctype, hstatus.DESCRIPTION as cstatus";
            }
            sql += " from IMTD_CFP_DETAILS cm LEFT JOIN COMPANY_MASTER com on com.COMPANY_MASTER_SID=cm.COMPANY_MASTER_SID AND com.INBOUND_STATUS <> 'D'"
                    //                + " LEFT JOIN COMPANY_TRADE_CLASS ctc on ctc.COMPANY_MASTER_SID=com.COMPANY_MASTER_SID "       
                    + " LEFT JOIN HelPER_TABLE htype on htype.HELPER_TABLE_SID =  cm.COMPANY_TYPE"
                    + " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID =  cm.COMPANY_STATUS"
                    + " LEFT JOIN HELPER_TABLE htrade on htrade.HELPER_TABLE_SID=cm.TRADING_PARTNER_CONTRACT_NO "
                    + " LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID=com.COMPANY_CATEGORY "
                    + " LEFT JOIN HELPER_TABLE hgroup on hgroup.HELPER_TABLE_SID=com.COMPANY_GROUP "
                    + " WHERE cm.operation <> 'D' and cm.USERS_SID='" + userID + "' and cm.SESSION_ID='" + sessionID + "'";

            if (filterMap.containsKey("Remove") && "remove".equals(String.valueOf(filterMap.get("Remove")))) {
                sql += " AND cm.CHECK_RECORD = '1' ";
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("companyNo")))) {
                sql += " AND cm.COMPANY_NO LIKE '" + String.valueOf(filterMap.get("companyNo")) + "'";
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("companyName")))) {
                sql += " AND cm.COMPANY_NAME LIKE '" + String.valueOf(filterMap.get("companyName")) + "'";
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("companyStatus")))) {
                sql += " AND hstatus.DESCRIPTION LIKE '" + String.valueOf(filterMap.get("companyStatus")) + "'";
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("companyType")))) {
                sql += " AND htype.DESCRIPTION LIKE '" + String.valueOf(filterMap.get("companyType")) + "'";
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("companyFamilyPlanStatus")))) {
                sql += " AND cm.CFP_DETAILS_ATTACHED_STATUS =" + Integer.valueOf(String.valueOf(filterMap.get("companyFamilyPlanStatus")));
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("tradeClass"))) && filterMap.get("tradeClass")!=null) {
                sql += " AND htrade.DESCRIPTION LIKE '" + String.valueOf(filterMap.get("tradeClass")) + "'";
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("companyCategory"))) && filterMap.get("companyCategory")!=null) {
                sql += " AND hcategory.DESCRIPTION LIKE '" + String.valueOf(filterMap.get("companyCategory")) + "'";
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("companyGroup"))) && filterMap.get("companyGroup")!=null) {
                sql += " AND hgroup.DESCRIPTION LIKE '" + String.valueOf(filterMap.get("companyGroup")) + "'";
            }

            if (filterMap.containsKey("companyId") && filterMap.get("companyId") != null && StringUtils.isNotBlank(String.valueOf(filterMap.get("companyId")))) {
                sql += " AND cm.COMPANY_ID like '" + String.valueOf(filterMap.get("companyId")) + "'";
            }

            if (filterMap.get("companyStartDate-from") != null && filterMap.get("companyStartDate-to") != null) {

                Date from = (Date) filterMap.get("companyStartDate-from");

                Date to = (Date) filterMap.get("companyStartDate-to");

                sql += " AND cm.COMPANY_START_DATE BETWEEN '" + tempFromat.format(from) + "' AND '" + tempFromat.format(to) + "'";

            }

            if (filterMap.get("companyEndDate-from") != null && filterMap.get("companyEndDate-to") != null) {

                Date from = (Date) filterMap.get("companyEndDate-from");

                Date to = (Date) filterMap.get("companyEndDate-to");

                sql += " AND cm.COMPANY_END_DATE BETWEEN '" + tempFromat.format(from) + "' AND '" + tempFromat.format(to) + "'";

            }

            if (filterMap.get("companyFamilyPlanStartDate-from") != null && filterMap.get("companyFamilyPlanStartDate-to") != null) {

                Date from = (Date) filterMap.get("companyFamilyPlanStartDate-from");

                Date to = (Date) filterMap.get("companyFamilyPlanStartDate-to");

                sql += " AND cm.CFP_DETAILS_START_DATE BETWEEN '" + tempFromat.format(from) + "' AND '" + tempFromat.format(to) + "'";

            }

            if (filterMap.get("companyFamilyPlanEndDate-from") != null && filterMap.get("companyFamilyPlanEndDate-to") != null) {

                Date from = (Date) filterMap.get("companyFamilyPlanEndDate-from");

                Date to = (Date) filterMap.get("companyFamilyPlanEndDate-to");

                sql += " AND cm.CFP_DETAILS_END_DATE BETWEEN '" + tempFromat.format(from) + "' AND '" + tempFromat.format(to) + "'";

            }

            if (filterMap.get("attachedDate-from") != null && filterMap.get("attachedDate-to") != null) {

                Date from = (Date) filterMap.get("attachedDate-from");

                Date to = (Date) filterMap.get("attachedDate-to");

                sql += " AND cm.CFP_DETAILS_ATTACHED_DATE BETWEEN '" + tempFromat.format(from) + "' AND '" + tempFromat.format(to) + "'";

            }
            if (filterMap.get("Current") != null && filterMap.get("History") != null && filterMap.get("Future") != null) {
                sql += " AND ( '" + String.valueOf(filterMap.get("Current")) + "' BETWEEN cm.CFP_DETAILS_START_DATE AND ISNULL(cm.CFP_DETAILS_END_DATE,'" + String.valueOf(filterMap.get("Current")) + "') ";
                sql += " OR " + " cm.CFP_DETAILS_END_DATE < '" + filterMap.get("History") + "' ";
                sql += " OR " + " cm.CFP_DETAILS_START_DATE > '" + filterMap.get("Future") + "' )";
            } else if ((filterMap.get("Current") != null && filterMap.get("History") != null) || (filterMap.get("History") != null && filterMap.get("Current") != null)) {
                sql += " AND ( '" + String.valueOf(filterMap.get("Current")) + "' BETWEEN cm.CFP_DETAILS_START_DATE AND ISNULL(cm.CFP_DETAILS_END_DATE,'" + String.valueOf(filterMap.get("Current")) + "') OR cm.CFP_DETAILS_END_DATE < '" + String.valueOf(filterMap.get("History")) + "') ";
            } else if ((filterMap.get("History") != null && filterMap.get("Future") != null) || (filterMap.get("Future") != null && filterMap.get("History") != null)) {
                sql += " AND (" + " cm.CFP_DETAILS_END_DATE < '" + String.valueOf(filterMap.get("History")) + "' OR cm.CFP_DETAILS_START_DATE > '" + String.valueOf(filterMap.get("Future")) + "') ";
            } else if ((filterMap.get("Future") != null && filterMap.get("Current") != null) || (filterMap.get("Current") != null && filterMap.get("Future") != null)) {
                sql += " AND ( '" + String.valueOf(filterMap.get("Current")) + "' BETWEEN cm.CFP_DETAILS_START_DATE AND ISNULL(cm.CFP_DETAILS_END_DATE,'" + String.valueOf(filterMap.get("Current")) + "') OR cm.CFP_DETAILS_START_DATE > '" + String.valueOf(filterMap.get("Future")) + "') ";
            } else if (filterMap.get("Current") != null) {
                sql += " AND '" + String.valueOf(filterMap.get("Current")) + "' BETWEEN cm.CFP_DETAILS_START_DATE AND ISNULL(cm.CFP_DETAILS_END_DATE,'" + String.valueOf(filterMap.get("Current")) + "') ";
            } else if (filterMap.get("History") != null) {
                sql += " AND " + " cm.CFP_DETAILS_END_DATE < '" + String.valueOf(filterMap.get("History")) + "' ";
            } else if (filterMap.get("Future") != null) {
                sql += " AND " + " cm.CFP_DETAILS_START_DATE > '" + String.valueOf(filterMap.get("Future")) + "' ";
            }
            if (!isCount) {
                sql += " ORDER BY " + column + " " + orderBy + " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
            }
           
            LOGGER.debug("selected query" +sql);
            SQLQuery sqlQuery = session.createSQLQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }

    }
    
    }
