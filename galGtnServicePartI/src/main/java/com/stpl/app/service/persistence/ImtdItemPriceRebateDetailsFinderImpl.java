/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.model.ImtdItemPriceRebateDetails;
import com.stpl.app.service.ImtdItemPriceRebateDetailsLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.ORMException;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;

/**
 *
 * @author pvinoth
 */
public class ImtdItemPriceRebateDetailsFinderImpl extends BasePersistenceImpl<ImtdItemPriceRebateDetails> implements ImtdItemPriceRebateDetailsFinder {

    private static final Logger LOGGER = Logger.getLogger(ImtdItemPriceRebateDetailsFinderImpl.class);

    /**
     * Insert the records into the TempItemPricingDetailsAttached from
     * IFPDetailsAttached, PsDetailsAttached and RsDetailsAttached.
     *
     * @param input
     * @param future
     * @return
     */
    @Override
    public Boolean loadTempItemdetails(final List<Object> input, final Object future) {
        Session session = null;
        Boolean retFlag;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(String.valueOf(future));
            for (Object temp : input) {
                if (temp == null && sql.contains("?") && sql.charAt(sql.indexOf("?") - 1) == '=') {
                    sql = sql.replaceFirst("=[?]", " is null ");
                } else {
                    sql = sql.replaceFirst("[?]", String.valueOf(temp));
                }
            }
            System.out.println("sql = = =Insert  = = " + sql);    
            SQLQuery q = session.createSQLQuery(sql);
            q.executeUpdate();
            retFlag = true;
        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());
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
    @Override
    public Boolean massPopulate(final List<Object> input, final Object future) {
        Session session = null;
        Boolean retFlag;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get("com.contractDashboard.process.populateTempIFP");
            for (Object temp : input) {
                sql = sql.replaceFirst("[?]", temp.toString());
            }
            SQLQuery q = session.createSQLQuery(sql);
            q.executeUpdate();
            retFlag = true;
        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());
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
    @Override
    public Boolean massPopulateAll(final List<Object> input, final Object future) {
        Session session = null;
        Boolean retFlag;
        try {
            session = openSession();
            String sql = StringUtils.EMPTY;
            if ("CHECK_RECORD".equalsIgnoreCase(input.get(0).toString()) || "RS_CHECK_RECORD".equalsIgnoreCase(input.get(0).toString())) {
                sql += "UPDATE dbo.IMTD_ITEM_PRICE_REBATE_DETAILS SET " + input.get(0) + "=" + input.get(1);
                sql += " WHERE USERS_SID='" + input.get(4) + "' AND SESSION_ID='" + input.get(5) + "'";
                sql += " AND \"OPERATION\" <> 'D';";
            } else {
                sql = CustomSQLUtil.get("com.contractDashboard.process.populateAllTempIFP");
                for (Object temp : input) {
                    sql = sql.replaceFirst("[?]", temp.toString());
                }
            }

            SQLQuery q = session.createSQLQuery(sql);
            q.executeUpdate();
            retFlag = true;
        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    /**
     * To save the IFPDetailsAttached, PsDetailsAttached and RsDetailsAttached
     * from Temp Table.
     *
     * @param input
     * @param future
     * @return
     */
    @Override
    public Boolean saveItem(final List<Object> input, final Object future) {
        Session session = null;
        Boolean retFlag;
        try {
            session = openSession();
            if ("SaveDetails".equals(future)) {
                List<ImtdItemPriceRebateDetails> saveList = (List<ImtdItemPriceRebateDetails>) input.get(0);
                for (ImtdItemPriceRebateDetails temp : saveList) {
                    ImtdItemPriceRebateDetailsLocalServiceUtil.updateImtdItemPriceRebateDetails(temp);
                }
                retFlag = true;
            } else if ("Validation".equals(future)) {

                if (input.contains("Rebate_Amount")) {
                    //select count(IMTD_ITEM_PRICE_REBATE_SID) from dbo.IMTD_ITEM_PRICE_REBATE_DETAILS where CHECK_RECORD=1 AND USERS_SID=? AND SESSION_ID = '?' AND (? is null OR ? =0)
                    StringBuilder insertQuery = new StringBuilder();
                    insertQuery.append("SELECT Count(IMTD_ITEM_PRICE_REBATE_SID) FROM dbo.IMTD_ITEM_PRICE_REBATE_DETAILS WHERE USERS_SID='");
                    insertQuery.append(input.get(0));
                    insertQuery.append("' and SESSION_ID='");
                    insertQuery.append(input.get(1));
                    //insertQuery.append("' and \"Operation\" != 'D' and CHECK_RECORD = 1");
                    insertQuery.append("' and ");
                    insertQuery.append("(((Rebate_Amount is not null and Rebate_Amount <> 0) AND (REBATE_PLAN_SYSTEM_ID is not null AND REBATE_PLAN_SYSTEM_ID <> 0)) ");
                    insertQuery.append("OR ((Rebate_Amount is null or Rebate_Amount=0) AND (REBATE_PLAN_SYSTEM_ID is null OR REBATE_PLAN_SYSTEM_ID = 0)))");
                    insertQuery.append("");
                    String sql = insertQuery.toString();
                    SQLQuery q = session.createSQLQuery(sql);
                    List<?> temp = q.list();
                    retFlag = (Integer) temp.get(0) > 0;
                } else {
                    String sql = CustomSQLUtil.get("com.contractDashboard.process.saveIFPNullValidation");
                    if (input.contains("CONTRACT_PRICE") || input.contains("Price") || input.contains("BASE_PRICE") || input.contains("Rebate_Amount")) {
                        sql = CustomSQLUtil.get("com.contractDashboard.process.saveIFPNnumericValidation");
                    }
                    for (Object temp : input) {
                        sql = sql.replaceFirst("[?]", temp.toString());
                    }

                    SQLQuery q = session.createSQLQuery(sql);
                    List<?> temp = q.list();
                    retFlag = (Integer) temp.get(0) > 0;
                }
            } else if ("AlphaNumericValidation".equals(future)) {
                String sql = CustomSQLUtil.get("com.contractDashboard.process.saveIFPAlphaNumericValidation");
                for (Object temp : input) {
                    sql = sql.replaceFirst("[?]", temp.toString());
                }

                SQLQuery q = session.createSQLQuery(sql);
                List<?> temp = q.list();
                retFlag = (Integer) temp.get(0) > 0;
            } else {
                String sql = CustomSQLUtil.get(String.valueOf(future));
                for (Object temp : input) {
                    sql = sql.replaceFirst("[?]", temp.toString());
                }
                LOGGER.info("item update----------->" + sql);

                SQLQuery q = session.createSQLQuery(sql);
                q.executeUpdate();
                retFlag = true;
            }

        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());
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
    @Override
    public Boolean deleteAll(final List<Object> input, final Object future) {
        Session session = null;
        Boolean retFlag;
        try {
            session = openSession();
            String sql;
            if ("Back".equals(future)) {
                sql = CustomSQLUtil.get("com.contractDashboard.process.deleteOnBackTempIFP");
            } else if ("Load".equals(future)) {
                sql = CustomSQLUtil.get("com.contractDashboard.process.deleteOnLoadTempIFP");
            } else {
                sql = CustomSQLUtil.get("com.contractDashboard.process.deleteByDTempIFP");
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
    @Override
    public Boolean updateAll(final List<Object> input, final Object future) {
        Session session = null;
        Boolean retFlag;
        try {
            session = openSession();
            String upadteQuery = CustomSQLUtil.get("com.contractDashboard.process.updateOperationIFP");
            upadteQuery = upadteQuery.replaceFirst("[?]", input.get(0).toString());
            upadteQuery = upadteQuery.replaceFirst("[?]", input.get(1).toString());
            String upadteQuery1 = CustomSQLUtil.get("com.contractDashboard.process.deleteByNotDTempIFP");
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
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    public Object validateTempRSDeatils(String userId, String sessionId, String createdDate, String operation, Object future1, Object future2, Object future3, Object future4) {
        Session session = null;
        Object result = 0;
        try {
            session = openSession();
            StringBuilder insertQuery = new StringBuilder();
            if ("RSDetailsBundleNo".equals(operation)) {
                insertQuery.append("SELECT IMTD_ITEM_PRICE_REBATE_SID FROM dbo.IMTD_ITEM_PRICE_REBATE_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                //insertQuery.append("' and \"Operation\" != 'D' and (BUNDLE_NO is null or BUNDLE_NO='') and CHECK_RECORD = 1;");
                insertQuery.append("' and (BUNDLE_NO is null or BUNDLE_NO='') and CHECK_RECORD = 1;");
            } else if ("ProductBundleRPNameRSAmout".equals(operation)) {
                /*
                
                 This Query will findout the bundle numbers which is having both Rebate Plan and Rebate amount.
                
                 SELECT RS_DETAILS_BUNDLE_NO FROM IMTD_RS_DETAILS  where USERS_SID='13330' and SESSION_ID='011220150748184818' 
                 and "Operation" != 'D' and CHECK_RECORD = 1
                 GROUP BY RS_DETAILS_BUNDLE_NO HAVING Count(NULLIF(REBATE_PLAN_MASTER_SID,0))>0 AND Count(NULLIF(RS_DETAILS_REBATE_AMOUNT,0))>0;
                 */
                insertQuery.append("SELECT BUNDLE_NO FROM IMTD_ITEM_PRICE_REBATE_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                //insertQuery.append("' and \"Operation\" != 'D' and CHECK_RECORD = 1");
                insertQuery.append("' GROUP BY BUNDLE_NO HAVING Count(NULLIF(REBATE_PLAN_SYSTEM_ID,0))>0 AND Count(NULLIF(Rebate_Amount,0))>0;");
            } else if ("ProductBundleRPName".equals(operation)) {
                /*
                
                 This Query will findout the bundle numbers which is having different rebate amounts.
                
                 SELECT RS_DETAILS_BUNDLE_NO FROM IMTD_RS_DETAILS  where USERS_SID='13330' and SESSION_ID='011220150748184818' 
                 and "Operation" != 'D' and CHECK_RECORD = 1
                 GROUP BY RS_DETAILS_BUNDLE_NO HAVING Count(NULLIF(REBATE_PLAN_MASTER_SID,0))>1;
                 */
                insertQuery.append("SELECT BUNDLE_NO FROM IMTD_ITEM_PRICE_REBATE_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                //insertQuery.append("' and \"Operation\" != 'D' and CHECK_RECORD = 1");
                insertQuery.append("' GROUP BY BUNDLE_NO HAVING Count(distinct(NULLIF(REBATE_PLAN_SYSTEM_ID,0)))>1;");
            } else if ("ProductBundleRSAmount".equals(operation)) {
                /*
                
                 This Query will findout the bundle numbers which is having different rebate plans.
                
                 SELECT RS_DETAILS_BUNDLE_NO FROM IMTD_RS_DETAILS  where USERS_SID='13330' and SESSION_ID='011220150748184818' 
                 and "Operation" != 'D' and CHECK_RECORD = 1
                 GROUP BY RS_DETAILS_BUNDLE_NO HAVING Count(NULLIF(REBATE_PLAN_MASTER_SID,0))>1;
                 */
                insertQuery.append("SELECT BUNDLE_NO FROM IMTD_ITEM_PRICE_REBATE_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                //insertQuery.append("' and \"Operation\" != 'D' and CHECK_RECORD = 1");
                insertQuery.append("' GROUP BY BUNDLE_NO HAVING Count(distinct(NULLIF(Rebate_Amount,0)))>1;");
            }
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            result = sqlQuery.list();

        } catch (ORMException e) {
            LOGGER.error(e.getMessage());
        } finally {
            closeSession(session);
        }
        return result;
    }

    public List getSelectedItemList(String userID, String sessionID, int start, int offset, String column, Boolean orderBy, Map<String, Object> filterMap, boolean isCount, Object future1, Object future2, Object future3) {
        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            sql = "SELECT impr.IMTD_ITEM_PRICE_REBATE_SID,impr.ITEM_NO,impr.ITEM_NAME,hpackageSize.DESCRIPTION as package, hform.DESCRIPTION as form, im.ITEM_DESC , hstatus.DESCRIPTION as status, hstrength.DESCRIPTION as strength, htherapeutic.DESCRIPTION as therapeutic, BM.BRAND_NAME AS BRAND  from IMTD_ITEM_PRICE_REBATE_DETAILS impr"
                    + " LEFT JOIN ITEM_MASTER im on im.ITEM_MASTER_SID=impr.ITEM_MASTER_SID"
                    + " LEFT JOIN HELPER_TABLE hform on hform.HELPER_TABLE_SID=im.FORM"
                    + " LEFT JOIN HELPER_TABLE hstrength on hstrength.HELPER_TABLE_SID=im.STRENGTH"
                    + " LEFT JOIN HELPER_TABLE htherapeutic on htherapeutic.HELPER_TABLE_SID=im.THERAPEUTIC_CLASS"
                    + " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID=im.ITEM_STATUS"
                    + " LEFT JOIN HELPER_TABLE hpackageSize on hpackageSize.HELPER_TABLE_SID=im.PACKAGE_SIZE"
                    + " Left Join BRAND_MASTER BM ON im.BRAND_MASTER_SID = BM.BRAND_MASTER_SID"
                    + " WHERE OPERATION <> 'D' and impr.USERS_SID = '" + userID + "' and impr.SESSION_ID='" + sessionID + "'";

            if (filterMap.get("itemNo") != null && StringUtils.isNotBlank(String.valueOf(filterMap.get("itemNo")))) {
                sql += " AND impr.ITEM_NO LIKE '" + String.valueOf(filterMap.get("itemNo")) + "'";
            }

            if (filterMap.get("itemName") != null && StringUtils.isNotBlank(String.valueOf(filterMap.get("itemName")))) {
                sql += " AND impr.ITEM_NAME LIKE '" + String.valueOf(filterMap.get("itemName")) + "'";
            }

            if (filterMap.get("packageSize") != null && StringUtils.isNotBlank(String.valueOf(filterMap.get("packageSize")))) {
                sql += " AND im.PACKAGE_SIZE = " + Integer.valueOf(String.valueOf(filterMap.get("packageSize")));
            }

            if (filterMap.get("form") != null && StringUtils.isNotBlank(String.valueOf(filterMap.get("form")))) {
                sql += " AND im.FORM = " + Integer.valueOf(String.valueOf(filterMap.get("form")));
            }

            if (filterMap.get("itemStatus") != null && !"null".equals(String.valueOf(filterMap.get("itemStatus"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("itemStatus")))) {
                sql += " AND im.ITEM_STATUS =" + Integer.valueOf(String.valueOf(filterMap.get("itemStatus")));
            }
            if (filterMap.get("strength") != null && !"null".equals(String.valueOf(filterMap.get("strength"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("strength")))) {
                sql += " AND im.STRENGTH =" + Integer.valueOf(String.valueOf(filterMap.get("strength")));
            }
            if (filterMap.get("therapeuticClass") != null && !"null".equals(String.valueOf(filterMap.get("therapeuticClass"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("therapeuticClass")))) {
                sql += " AND im.THERAPEUTIC_CLASS =" + Integer.valueOf(String.valueOf(filterMap.get("therapeuticClass")));
            }

            if (filterMap.get("itemDesc") != null && !"null".equals(String.valueOf(filterMap.get("itemDesc"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("itemDesc")))) {
                sql += " AND im.ITEM_DESC LIKE '" + String.valueOf(filterMap.get("itemDesc")) + "'";
            }
            if (filterMap.get("brand") != null && !"null".equals(String.valueOf(filterMap.get("brand"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("brand")))) {
                sql += " AND BM.BRAND_NAME LIKE '" + String.valueOf(filterMap.get("brand")) + "'";
            }

            if (isCount) {

                if (column == null || StringUtils.EMPTY.equals(column)) {
                    sql += " ORDER BY IMTD_ITEM_PRICE_REBATE_SID";
                    sql += (!orderBy) ? " DESC " : " ASC ";
                } else {
                    sql += "ORDER BY ";
                    sql += column;
                    sql += (!orderBy) ? " DESC " : " ASC ";
                }

                sql += " " + "OFFSET ";
                sql += start;
                sql += " ROWS FETCH NEXT ";
                sql += ((Integer) offset - (Integer) start);
                sql += (" ROWS ONLY;");
            }

            SQLQuery sqlQuery = session.createSQLQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.info("SQL to load selected item --->  " + sql);
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            closeSession(session);
        }

    }

    public void mergeImtdRsContractDetailsFormula(int contRsdSid, int itemSid, String userId, String sessionId) {

        Session session = null;
        SQLQuery sqlQuery;
        try {
            session = openSession();

            String frImtdSqlUpdate = "UPDATE IMTD_RS_CONTRACT_DETAILS_FR SET \"Operation\" = 'D' WHERE IMTD_ITEM_PRICE_REBATE_DETAILS_SID in (SELECT IMTD_ITEM_PRICE_REBATE_SID FROM IMTD_ITEM_PRICE_REBATE_DETAILS WHERE CHECK_RECORD=0);";

            String frSqlUpdate = "UPDATE RS_CONTRACT_DETAILS_FR SET INBOUND_STATUS='D' WHERE RS_CONTRACT_DETAILS_FR_SID in (SELECT RS_CONTRACT_DETAILS_FR_SID FROM IMTD_RS_CONTRACT_DETAILS_FR WHERE USERS_ID='"
                    + userId + "' and SESSION_ID='" + sessionId + "' and \"Operation\" = 'D');";

            String sql = "MERGE RS_CONTRACT_DETAILS_FR T USING IMTD_RS_CONTRACT_DETAILS_FR S ON (S.ITEM_MASTER_SID=" + itemSid + " AND S.RS_CONTRACT_DETAILS_FR_SID=T.RS_CONTRACT_DETAILS_FR_SID"
                    + " AND S.USERS_ID = '" + userId + "' AND S.SESSION_ID ='" + sessionId + "')"
                    + " WHEN NOT MATCHED BY TARGET AND S.Operation <> 'D' AND S.ITEM_MASTER_SID=" + itemSid + " AND S.USERS_ID = '" + userId + "' AND SESSION_ID='" + sessionId + "' THEN"
                    + " INSERT (RS_CONTRACT_DETAILS_SID,ITEM_MASTER_SID,FORMULA_ID,FORMULA_METHOD_ID,INBOUND_STATUS,RECORD_LOCK_STATUS,CREATED_BY,MODIFIED_BY) VALUES("
                    + contRsdSid + "," + itemSid + ",S.FORMULA_ID,S.FORMULA_METHOD_ID,'A','false'," + userId + "," + userId + ")"
                    + " WHEN MATCHED AND S.Operation <> 'D' AND S.ITEM_MASTER_SID=" + itemSid + " AND S.USERS_ID = '" + userId + "' AND S.SESSION_ID='" + sessionId + "' THEN UPDATE SET"
                    + " T.RS_CONTRACT_DETAILS_SID=" + contRsdSid + ",T.ITEM_MASTER_SID=" + itemSid + ", T.FORMULA_ID=S.FORMULA_ID,T.FORMULA_METHOD_ID=S.FORMULA_METHOD_ID,T.INBOUND_STATUS='U',T.RECORD_LOCK_STATUS='false',"
                    + "T.CREATED_BY=" + userId + ",T.MODIFIED_BY=" + userId + ";";

            sqlQuery = session.createSQLQuery(frImtdSqlUpdate);
            sqlQuery.executeUpdate();

            sqlQuery = session.createSQLQuery(frSqlUpdate);
            sqlQuery.executeUpdate();
            sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();

        } catch (ORMException e) {

            LOGGER.error(e.getMessage());;
            LOGGER.error(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

        } finally {
            closeSession(session);
        }
    }

    public void deleteTempRsContractTableRecords(int contRsSid, int contRsdSid, String userId, String sessionId) {
        Session session = null;
        SQLQuery sqlQuery;
        try {
            session = openSession();

            String formulaSql = " DELETE FROM  IMTD_RS_CONTRACT_DETAILS_FR WHERE USERS_ID = '" + userId + "' AND SESSION_ID = '" + sessionId + "' ";

            sqlQuery = session.createSQLQuery(formulaSql);
            sqlQuery.executeUpdate();
        } catch (Exception e) {

            LOGGER.error(e.getMessage());

        } finally {
            closeSession(session);
        }

    }

    public void insertFormulaToContractRsdFrImtd(int contRsdSid, String userId, String sessionId, String createdDate) {

        Session session = null;
        SQLQuery sqlQuery;
        try {
            session = openSession();

            String sql = "INSERT INTO IMTD_RS_CONTRACT_DETAILS_FR (RS_CONTRACT_DETAILS_FR_SID,RS_CONTRACT_DETAILS_SID,ITEM_MASTER_SID,FORMULA_ID,FORMULA_METHOD_ID,INBOUND_STATUS,RECORD_LOCK_STATUS,"
                    + "IMTD_CREATED_DATE,\"Operation\",USERS_ID,SESSION_ID) SELECT contRsdf.RS_CONTRACT_DETAILS_FR_SID,contRsdf.RS_CONTRACT_DETAILS_SID,contRsdf.ITEM_MASTER_SID,contRsdf.FORMULA_ID,contRsdf.FORMULA_METHOD_ID,"
                    + "contRsdf.INBOUND_STATUS,'false' as RECORD_LOCK_STATUS,'" + createdDate + "' as IMTD_CREATED_DATE,'U' as \"Operation\",'" + userId + "'as USERS_ID,'"
                    + sessionId + "' as SESSION_ID FROM RS_CONTRACT_DETAILS_FR contRsdf WHERE contRsdf.INBOUND_STATUS <> 'D' AND contRsdf.RS_CONTRACT_DETAILS_SID=" + contRsdSid;

            sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
        } catch (Exception e) {

            LOGGER.error(e.getMessage());

        } finally {
            closeSession(session);
        }
    }

    public void addAllFormulaToContractRsdFrImtd(int itemSid, String userId, String sessionId, String createdDate) {

        Session session = null;
        SQLQuery sqlQuery;
        try {
            session = openSession();

            String sql = "MERGE IMTD_RS_CONTRACT_DETAILS_FR T USING FORECASTING_FORMULA S ON (T.ITEM_MASTER_SID=" + itemSid + " AND S.FORMULA_NAME=T.FORMULA_METHOD_ID"
                    + " AND T.USERS_ID = '" + userId + "' AND T.SESSION_ID ='" + sessionId + "')"
                    + " WHEN NOT MATCHED BY TARGET THEN"
                    + " INSERT (ITEM_MASTER_SID,FORMULA_METHOD_ID,\"Operation\",RECORD_LOCK_STATUS,CREATED_BY,MODIFIED_BY,USERS_ID,SESSION_ID) VALUES(" + itemSid
                    + ",S.FORMULA_NAME,'A','false','" + userId + "','" + userId + "','" + userId + "','" + sessionId + "')"
                    + " WHEN MATCHED AND T.OPERATION <> 'A' THEN UPDATE SET T.ITEM_MASTER_SID=" + itemSid + ","
                    + " T.FORMULA_METHOD_ID=S.FORMULA_NAME,T.OPERATION='U',T.RECORD_LOCK_STATUS='false',"
                    + "T.CREATED_BY=" + userId + ",T.MODIFIED_BY=" + userId + ",T.USERS_ID='" + userId + "',T.SESSION_ID='" + sessionId + "';";

            sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
        } catch (Exception e) {

            LOGGER.error(e.getMessage());

        } finally {
            closeSession(session);
        }
    }

    public void remaoveAllFormulaToContractRsdFrImtd(int itemSid, String userId, String sessionId, String createdDate) {
        Session session = null;
        SQLQuery sqlQuery;
        try {
            session = openSession();

            String sql = "UPDATE IMTD_RS_CONTRACT_DETAILS_FR T SET T.OPERATION = 'D' WHERE T.ITEM_MASTER_SID=" + itemSid + ", T.USERS_ID='" + userId + "', T.SESSION_ID='" + sessionId + "',"
                    + "T.IMTD_CREATED_DATE='" + createdDate + "';";

            sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
        } catch (Exception e) {

            LOGGER.error(e.getMessage());

        } finally {
            closeSession(session);
        }
    }

}
