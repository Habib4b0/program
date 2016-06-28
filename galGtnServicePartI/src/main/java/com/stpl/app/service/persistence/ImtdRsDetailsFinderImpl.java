package com.stpl.app.service.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.model.ImtdRsDetails;
import com.stpl.portal.kernel.dao.orm.ORMException;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;

public class ImtdRsDetailsFinderImpl extends BasePersistenceImpl<ImtdRsDetails> implements ImtdRsDetailsFinder {
    private static final Logger LOGGER = Logger.getLogger(ImtdRsDetailsFinderImpl.class);
    public Object deleteAll(String userId,String sessionId,String dateCreated,String operation,Object future1,Object future2,Object future3,Object future4){
        Session session = null;
        StringBuilder deleteQuery = new StringBuilder();
        try {
            session = openSession();
            if ("RSDetails".equals(dateCreated)) {
                deleteQuery.append("UPDATE dbo.RS_Details SET INBOUND_STATUS = 'D' WHERE RS_MODEL_SID=");
                deleteQuery.append(future1);
                deleteQuery.append(";");
            } else {
                deleteQuery.append("delete FROM dbo.IMTD_RS_DETAILS WHERE USERS_SID='");
                deleteQuery.append(userId);
                deleteQuery.append("' and SESSION_ID='");
                deleteQuery.append(sessionId);
            if("All".equals(operation)){
                    deleteQuery.append("';");
            }else{
                    deleteQuery.append("' and \"Operation\"='A';");
                    deleteQuery.append("UPDATE dbo.IMTD_RS_DETAILS SET \"Operation\"='D' WHERE USERS_SID='");
                    deleteQuery.append(userId);
                    deleteQuery.append("' and SESSION_ID='");
                    deleteQuery.append(sessionId);

                    deleteQuery.append("' and \"Operation\"='U';");
                }
            }
            SQLQuery sqlQuery = session.createSQLQuery(deleteQuery.toString());
            sqlQuery.executeUpdate();
        } catch (ORMException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(deleteQuery.toString());
//            retFlag= false;
        } finally {
            closeSession(session);
        }
        return true;

    }
    
    public Object updateOperation(String userId,String sessionId,String createdDate,String operation,Object future1,Object future2,Object future3,Object future4){
        Session session = null;
        StringBuilder deleteQuery = new StringBuilder();
        try {
            session = openSession();
            deleteQuery.append("delete FROM dbo.IMTD_RS_DETAILS WHERE USERS_SID='");
            deleteQuery.append(userId);
            deleteQuery.append("' and SESSION_ID='");
            deleteQuery.append(sessionId);
            deleteQuery.append("' and \"Operation\"='D';");
          
            SQLQuery sqlQuery = session.createSQLQuery(deleteQuery.toString());
            sqlQuery.executeUpdate();
        } catch (ORMException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(deleteQuery.toString());
        } finally {
            closeSession(session);
        }
        return true;

    }

    public Object updateForPopulate(String userId,String sessionId,String createdDate,String operation,Object populateField,Object populateValue,Object rebatePlanLevel,Object future4){
        Session session = null;
        Boolean retFlag;
        StringBuilder insertQuery = new StringBuilder();

        try {
            session = openSession();
            insertQuery.append("UPDATE dbo.IMTD_RS_DETAILS SET ");
            insertQuery.append(populateField);
            insertQuery.append("='");
            insertQuery.append(populateValue);
            insertQuery.append("', RS_DETAILS_MODIFIED_BY ='");
            insertQuery.append(userId);
            insertQuery.append("', RS_DETAILS_MODIFIED_DATE ='");
            insertQuery.append(createdDate);
            insertQuery.append("'");
            if("REBATE_PLAN_MASTER_SID".equals(populateField))
            {
                insertQuery.append(", RS_DETAILS_REBATE_AMOUNT ");
                insertQuery.append("=NULL");
                //insertQuery.append(populateValue);
            }
            insertQuery.append(" WHERE USERS_SID='");
            insertQuery.append(userId);
            insertQuery.append("' AND SESSION_ID='");
            insertQuery.append(sessionId);
            insertQuery.append("' AND \"Operation\" != 'D' AND CHECK_RECORD like 1;");            
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            sqlQuery.executeUpdate();

         retFlag=true;
        } catch (ORMException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(insertQuery.toString());
            retFlag= false;
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    public Object updateForPopulateAll(String userId,String sessionId,String createdDate,String operation,Object populateField,Object populateValue,Object future3,Object future4){
        Session session = null;
        Boolean retFlag;
        StringBuilder insertQuery = new StringBuilder();
        try {
            session = openSession();
            insertQuery.append("UPDATE dbo.IMTD_RS_DETAILS SET ");
            if(operation!="populate") {
                if ("check".equalsIgnoreCase(operation)) {
                    insertQuery.append("CHECK_RECORD=1");
                }else if ("uncheck".equalsIgnoreCase(operation)) {
                    insertQuery.append("CHECK_RECORD=0");
                }
            } else {
                insertQuery.append(populateField);
                insertQuery.append("='");
                insertQuery.append(populateValue).append("',");
            if("REBATE_PLAN_MASTER_SID".equals(populateField)) {
                    insertQuery.append(" RS_DETAILS_REBATE_AMOUNT ");
                    insertQuery.append("=NULL,");
                }
                insertQuery.append(" RS_DETAILS_MODIFIED_BY ='");
                insertQuery.append(userId);
                insertQuery.append("', RS_DETAILS_MODIFIED_DATE ='");
                insertQuery.append(createdDate).append("'");
            }
            insertQuery.append(" WHERE USERS_SID='");
            insertQuery.append(userId);
            insertQuery.append("' AND SESSION_ID='");
            insertQuery.append(sessionId);
            insertQuery.append("' AND \"Operation\" !='D';");            
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            sqlQuery.executeUpdate();

         retFlag=true;
        } catch (ORMException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(insertQuery.toString());
            retFlag= false;
        } finally {
            closeSession(session);
        }
        return retFlag;
    }
    public List getIfpLazyList(int start, int offset, Object ifpIdList, Object operation, Object searchValue) {
        return null;

    }

    public Object updateToRsDetails(int ifpMasterSystemId, String userId, String sessionId, String createdDate, String flag, Object future1, Object future2, Object future3, Object future4) {
        Session session = null;
        Boolean retFlag;
        StringBuilder insertQuery = new StringBuilder();
        String frImtdSqlUpdate = StringUtils.EMPTY;
        String frSqlUpdate = StringUtils.EMPTY;
        try {
            session = openSession();
            

            SimpleDateFormat simpledateformat5 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            String currentDate = simpledateformat5.format(new Date());                        
            if ("Add".equals(flag)) {
                insertQuery.append("UPDATE IMTD_RS_DETAILS SET \"Operation\" = 'D' WHERE CHECK_RECORD=0;");

                insertQuery.append("UPDATE RS_Details SET INBOUND_STATUS='D' WHERE RS_DETAILS_SID in (SELECT RS_DETAILS_SID FROM dbo.IMTD_RS_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"Operation\" = 'D');");

                insertQuery.append(" MERGE RS_DETAILS T ");
                insertQuery.append(" USING IMTD_RS_DETAILS S ");
                insertQuery.append(" ON ( S.ITEM_MASTER_SID = T.ITEM_MASTER_SID ");
                insertQuery.append("      AND S.IFP_MODEL_SID = T.IFP_MODEL_SID ");
                insertQuery.append("      AND S.CHECK_RECORD = 1 ");
                insertQuery.append(" AND T.RS_MODEL_SID = '").append(ifpMasterSystemId).append("' ");
                insertQuery.append("      AND S.USERS_SID = '").append(userId).append("' ");
                insertQuery.append("      AND S.SESSION_ID = '").append(sessionId).append("' ) ");
                insertQuery.append(" WHEN NOT MATCHED BY TARGET AND S.CHECK_RECORD = 1 AND S.Operation = 'A' AND S.USERS_SID = '").append(userId).append("' AND S.SESSION_ID = '").append(sessionId).append("' THEN ");

                insertQuery.append(" INSERT (RS_MODEL_SID, ITEM_MASTER_SID, ITEM_RS_ATTACHED_STATUS, ITEM_RS_ATTACHED_DATE ");
                insertQuery.append("         , MODIFIED_DATE, ITEM_REBATE_START_DATE, ITEM_REBATE_END_DATE, REBATE_AMOUNT, FORMULA_ID ");
                insertQuery.append("         , IFP_MODEL_SID, CREATED_BY, CREATED_DATE, MODIFIED_BY, INBOUND_STATUS, FORMULA_METHOD_ID ");
                insertQuery.append("         , REBATE_PLAN_MASTER_SID, RECORD_LOCK_STATUS, BUNDLE_NO,DEDUCTION_CALENDAR_MASTER_SID,NET_SALES_FORMULA_MASTER_SID,"
                        +"NET_SALES_RULE,EVALUATION_RULE,CALCULATION_RULE,EVALUATION_RULE_BUNDLE,CALCULATION_RULE_BUNDLE) ");
                insertQuery.append(" VALUES ( ").append(ifpMasterSystemId).append("  , ITEM_MASTER_SID, RS_DETAILS_ATTACHED_STATUS, RS_DETAILS_ATTACHED_DATE, ")
                        .append("convert(datetime,'").append(currentDate).append("')").append(", ITEM_REBATE_START_DATE, ITEM_REBATE_END_DATE, RS_DETAILS_REBATE_AMOUNT, RS_DETAILS_FORMULA_ID, IFP_MODEL_SID, ").append(userId).append(", ")
                        .append("convert(datetime,'").append(currentDate).append("')").append(", ").append(userId).append(", 'A' , RS_DETAILS_FORMULA_METHOD_ID, REBATE_PLAN_MASTER_SID, 'false' , RS_DETAILS_BUNDLE_NO ,DEDUCTION_CALENDAR_MASTER_SID,NET_SALES_FORMULA_MASTER_SID,"
                                + " NET_SALES_RULE,EVALUATION_RULE,CALCULATION_RULE,EVALUATION_RULE_BUNDLE,CALCULATION_RULE_BUNDLE )");

                insertQuery.append(" WHEN MATCHED THEN ");
                insertQuery.append(" UPDATE SET ");
                insertQuery.append("  T.ITEM_RS_ATTACHED_STATUS = S.RS_DETAILS_ATTACHED_STATUS, T.ITEM_RS_ATTACHED_DATE = S.RS_DETAILS_ATTACHED_DATE, T.MODIFIED_DATE = ")
                        .append("convert(datetime,'").append(currentDate).append("')").append(", T.ITEM_REBATE_START_DATE = S.ITEM_REBATE_START_DATE, T.ITEM_REBATE_END_DATE = S.ITEM_REBATE_END_DATE")
                        .append(" , T.REBATE_AMOUNT = S.RS_DETAILS_REBATE_AMOUNT, T.FORMULA_ID = S.RS_DETAILS_FORMULA_ID,T.MODIFIED_BY = ")
                        .append(userId).append(", T.FORMULA_METHOD_ID = S.RS_DETAILS_FORMULA_METHOD_ID, T.REBATE_PLAN_MASTER_SID = S.REBATE_PLAN_MASTER_SID ")
                        .append(", T.BUNDLE_NO = S.RS_DETAILS_BUNDLE_NO ")
                        .append(", T.INBOUND_STATUS = 'A',T.RECORD_LOCK_STATUS = 'false', T.DEDUCTION_CALENDAR_MASTER_SID=S.DEDUCTION_CALENDAR_MASTER_SID ,T.NET_SALES_FORMULA_MASTER_SID=S.NET_SALES_FORMULA_MASTER_SID,"
                                + "T.NET_SALES_RULE=S.NET_SALES_RULE,T.EVALUATION_RULE=S.EVALUATION_RULE,T.CALCULATION_RULE=S.CALCULATION_RULE,T.EVALUATION_RULE_BUNDLE=S.EVALUATION_RULE_BUNDLE,T.CALCULATION_RULE_BUNDLE=S.CALCULATION_RULE_BUNDLE ;");

                

            } else {
                insertQuery.append("UPDATE IMTD_RS_DETAILS SET \"Operation\" = 'D' WHERE CHECK_RECORD=0;");
//
                insertQuery.append("UPDATE RS_Details SET INBOUND_STATUS='D' WHERE RS_DETAILS_SID in (SELECT RS_DETAILS_SID FROM dbo.IMTD_RS_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"Operation\" = 'D')");

                if ("Edit".equals(flag)) {

                    insertQuery.append("MERGE dbo.RS_Details AS Target USING (SELECT RS_DETAILS_ATTACHED_STATUS, RS_DETAILS_ATTACHED_DATE, RS_DETAILS_MODIFIED_DATE, ITEM_REBATE_START_DATE, ITEM_REBATE_END_DATE, RS_DETAILS_REBATE_AMOUNT, RS_DETAILS_FORMULA_ID, RS_DETAILS_CREATED_BY, RS_DETAILS_CREATED_DATE, RS_DETAILS_MODIFIED_BY, RS_DETAILS_FORMULA_METHOD_ID, REBATE_PLAN_MASTER_SID, RS_DETAILS_SID, RS_MODEL_SID, RS_DETAILS_BUNDLE_NO,DEDUCTION_CALENDAR_MASTER_SID,"
                            + " NET_SALES_FORMULA_MASTER_SID,NET_SALES_RULE,EVALUATION_RULE,CALCULATION_RULE,EVALUATION_RULE_BUNDLE,CALCULATION_RULE_BUNDLE from IMTD_RS_DETAILS ");
                    insertQuery.append("WHERE USERS_SID='");
                    insertQuery.append(userId);
                    insertQuery.append("' and SESSION_ID='");
                    insertQuery.append(sessionId);
                    insertQuery.append("' and \"Operation\" = 'U' and CHECK_RECORD=1) AS Source ON (Target.RS_MODEL_SID = Source.RS_MODEL_SID AND Target.RS_DETAILS_SID = Source.RS_DETAILS_SID AND Target.INBOUND_STATUS <> 'D')");
                    insertQuery.append("WHEN MATCHED THEN");
                    insertQuery.append(" UPDATE SET Target.ITEM_RS_ATTACHED_STATUS = Source.RS_DETAILS_ATTACHED_STATUS,Target.BUNDLE_NO = Source.RS_DETAILS_BUNDLE_NO, Target.ITEM_RS_ATTACHED_DATE = Source.RS_DETAILS_ATTACHED_DATE, Target.MODIFIED_DATE = ").append("convert(datetime,'").append(currentDate).append("')").append(", Target.ITEM_REBATE_START_DATE = Source.ITEM_REBATE_START_DATE, Target.ITEM_REBATE_END_DATE = Source.ITEM_REBATE_END_DATE, Target.REBATE_AMOUNT = Source.RS_DETAILS_REBATE_AMOUNT, Target.FORMULA_ID = Source.RS_DETAILS_FORMULA_ID,Target.MODIFIED_BY = ").append(userId).append(", Target.FORMULA_METHOD_ID = Source.RS_DETAILS_FORMULA_METHOD_ID, Target.REBATE_PLAN_MASTER_SID = Source.REBATE_PLAN_MASTER_SID, Target.INBOUND_STATUS = 'C',Target.RECORD_LOCK_STATUS = 'false', Target.DEDUCTION_CALENDAR_MASTER_SID = Source.DEDUCTION_CALENDAR_MASTER_SID,"
                            + " Target.NET_SALES_FORMULA_MASTER_SID =Source.NET_SALES_FORMULA_MASTER_SID,Target.NET_SALES_RULE=Source.NET_SALES_RULE,Target.EVALUATION_RULE=Source.EVALUATION_RULE,Target.CALCULATION_RULE=Source.CALCULATION_RULE,Target.EVALUATION_RULE_BUNDLE=Source.EVALUATION_RULE_BUNDLE,Target.CALCULATION_RULE_BUNDLE=Source.CALCULATION_RULE_BUNDLE;");
                }

                // Merge query for RsDetails Insert and update
                String mergeRsDetails = CustomSQLUtil.get("mergeRsDetails");
                mergeRsDetails = mergeRsDetails.replace("[$USERS_SID]", userId);
                mergeRsDetails = mergeRsDetails.replace("[$SESSION_ID]", sessionId);
                mergeRsDetails = mergeRsDetails.replace("[$CURRENT_DATE]", currentDate);
                insertQuery.append(mergeRsDetails);

            }      
            
            frImtdSqlUpdate = "UPDATE IMTD_RS_DETAILS_FR SET \"Operation\" = 'D' WHERE IMTD_RS_DETAILS_SID in (SELECT IMTD_RS_DETAILS_SID FROM IMTD_RS_DETAILS WHERE CHECK_RECORD=0);";

            frSqlUpdate = "UPDATE RS_DETAILS_FR SET INBOUND_STATUS='D' WHERE RS_DETAILS_FR_SID in (SELECT RS_DETAILS_FR_SID FROM IMTD_RS_DETAILS_FR WHERE USERS_ID='"
                    + userId + "' and SESSION_ID='" + sessionId + "' and \"Operation\" = 'D');";
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            sqlQuery.executeUpdate();
            
            sqlQuery = session.createSQLQuery(frImtdSqlUpdate);
            sqlQuery.executeUpdate();

            sqlQuery = session.createSQLQuery(frSqlUpdate);
            sqlQuery.executeUpdate();
            retFlag = true;
        } catch (ORMException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(insertQuery.toString());
            LOGGER.error(frImtdSqlUpdate);
            LOGGER.error(frSqlUpdate);
            retFlag = false;
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    public Object insertTempRsDetailsInADD(String userId, String sessionId, String createdDate, String ifpSystemId, Object idValue, Object future2, Object future3, Object future4) {
        Session session = null;
        Boolean retFlag;
        StringBuilder insertQuery = new StringBuilder();
       
        try {

            SimpleDateFormat simpledateformat5 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            String currentDate = simpledateformat5.format(new Date());

            session = openSession();
            insertQuery.append("INSERT INTO dbo.IMTD_RS_DETAILS (IFP_MODEL_SID, ITEM_MASTER_SID, ITEM_ID, ITEM_NO, ITEM_NAME,RS_DETAILS_ATTACHED_STATUS, RS_DETAILS_ATTACHED_DATE, \"Operation\", SESSION_ID, IMTD_CREATED_DATE, USERS_SID, CHECK_RECORD,RS_MODEL_SID)");
            insertQuery.append("SELECT IFD.IFP_MODEL_SID, IM.ITEM_MASTER_SID, IM.ITEM_ID, IM.ITEM_NO, IM.ITEM_NAME, 0, ");
            insertQuery.append("convert(datetime,'").append(currentDate).append("')");
            insertQuery.append(" as RS_DETAILS_ATTACHED_DATE, 'A' as \"Operation\",'");
            insertQuery.append(sessionId);
            insertQuery.append("'as SessionId,'");
            insertQuery.append(createdDate);
            insertQuery.append("'as IMTD_CREATED_DATE,'");
            insertQuery.append(userId);
            insertQuery.append("'as USERS_SID,");
            idValue=idValue==null || idValue.equals("null") || idValue.equals("")?"0":idValue;
            insertQuery.append("0 as CHECK_RECORD, "+idValue+" as RS_MODEL_SID");
            insertQuery.append(" FROM dbo.ITEM_MASTER IM, dbo.IFP_DETAILS IFD WHERE IM.ITEM_MASTER_SID = IFD.ITEM_MASTER_SID and IFD.IFP_MODEL_SID=");
            insertQuery.append(ifpSystemId);
            insertQuery.append(" and IFD.INBOUND_STATUS Not in ('D','F')");
            insertQuery.append(";");
            
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            sqlQuery.executeUpdate();

            retFlag = true;
        } catch (ORMException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(insertQuery.toString());
            retFlag = false;
        } finally {
            closeSession(session);
        }
        return retFlag;

    }

    public Object insertTempRsDetailsInEdit(String userId, String sessionId, String createdDate, String rsSystemId, Object rebateScheduleId, Object future2, Object future3, Object future4) {
        Session session = null;
        Boolean retFlag;
        StringBuilder insertQuery = new StringBuilder();
        try {
            session = openSession();
            insertQuery.append("INSERT INTO dbo.IMTD_RS_DETAILS (RS_MODEL_SID, RS_DETAILS_SID, RS_ID, IFP_MODEL_SID, ITEM_MASTER_SID, ITEM_ID, ITEM_NO,ITEM_NAME, RS_DETAILS_ATTACHED_STATUS, RS_DETAILS_ATTACHED_DATE, RS_DETAILS_MODIFIED_DATE, ITEM_REBATE_START_DATE, ITEM_REBATE_END_DATE,RS_DETAILS_REBATE_AMOUNT,REBATE_PLAN_MASTER_SID,RS_DETAILS_BUNDLE_NO,\"Operation\", SESSION_ID, IMTD_CREATED_DATE, USERS_SID,CHECK_RECORD,DEDUCTION_CALENDAR_MASTER_SID,NET_SALES_FORMULA_MASTER_SID,NET_SALES_RULE,EVALUATION_RULE,CALCULATION_RULE,EVALUATION_RULE_BUNDLE,CALCULATION_RULE_BUNDLE,RS_DETAILS_FORMULA_ID,RS_DETAILS_FORMULA_NAME,RS_DETAILS_FORMULA_NO)");
            insertQuery.append("SELECT RSD.RS_MODEL_SID, RSD.RS_DETAILS_SID, '").append(rebateScheduleId).append("' as REBATE_SCHEDULE_ID, RSD.IFP_MODEL_SID,IM.ITEM_MASTER_SID, IM.ITEM_ID, IM.ITEM_NO, IM.ITEM_NAME,RSD.ITEM_RS_ATTACHED_STATUS, RSD.ITEM_RS_ATTACHED_DATE, RSD.MODIFIED_DATE, RSD.ITEM_REBATE_START_DATE,RSD.ITEM_REBATE_END_DATE, RSD.REBATE_AMOUNT, RSD.REBATE_PLAN_MASTER_SID,RSD.BUNDLE_NO,");
            insertQuery.append("'U' as \"Operation\",'");
            insertQuery.append(sessionId);
            insertQuery.append("'as SessionId,'");
            insertQuery.append(createdDate);
            insertQuery.append("'as IMTD_CREATED_DATE,'");
            insertQuery.append(userId);
            insertQuery.append("'as USERS_SID,");
            insertQuery.append("1 as CHECK_RECORD,RSD.DEDUCTION_CALENDAR_MASTER_SID,RSD.NET_SALES_FORMULA_MASTER_SID,RSD.NET_SALES_RULE,RSD.EVALUATION_RULE,RSD.CALCULATION_RULE,RSD.EVALUATION_RULE_BUNDLE,RSD.CALCULATION_RULE_BUNDLE,RSD.FORMULA_ID,FF.FORMULA_NAME,FF.FORMULA_NO");
            insertQuery.append(" FROM dbo.ITEM_MASTER IM, dbo.RS_Details RSD LEFT JOIN dbo.FORECASTING_FORMULA FF on FF.FORECASTING_FORMULA_SID = RSD.FORMULA_ID WHERE RSD.INBOUND_STATUS <> 'D' and RSD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID and RSD.RS_MODEL_SID=");
            insertQuery.append(rsSystemId);
            insertQuery.append(";");
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            sqlQuery.executeUpdate();

            retFlag = true;
        } catch (ORMException e) {
            retFlag= false;
            LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    public List getItemLazyList(int start, int offset, Object companyIdList, Object operation, Object searchValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object updateToIFPDetails(int ifpMasterSystemId, String userId, String sessionId, String createdDate, String operation, Object future1, Object future2, Object future3, Object future4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object insertTempIfpDetailsInADD(String userId, String sessionId, String createdDate, String operation, Object future1, Object future2, Object future3, Object future4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object insertTempIfpDetailsInEdit(String userId, String sessionId, String createdDate, String operation, Object future1, Object future2, Object future3, Object future4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Object validateTempRSDeatils(String userId,String sessionId,String createdDate,String operation,Object future1,Object future2,Object future3,Object future4){
        Session session = null;
        Object result = 0;
        StringBuilder insertQuery = new StringBuilder();
        try {
            session = openSession();
            
            if ("AtleastOne".equals(operation)) {
                insertQuery.append("SELECT Count(ITEM_ID) FROM dbo.IMTD_RS_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"Operation\" != 'D';");
            } else if ("ItemRebateStartDate".equals(operation)) {
                insertQuery.append("SELECT Count(ITEM_ID) FROM dbo.IMTD_RS_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"Operation\" != 'D' and (ITEM_REBATE_START_DATE is null or ITEM_REBATE_START_DATE='') and CHECK_RECORD = 1;");
            } else if ("RSDetailsBundleNo".equals(operation)) {
                insertQuery.append("SELECT Count(ITEM_ID) FROM dbo.IMTD_RS_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"Operation\" != 'D' and (RS_DETAILS_BUNDLE_NO is null or RS_DETAILS_BUNDLE_NO='') and CHECK_RECORD = 1;");
            } else if ("RSDetailsRebateAmount".equals(operation)) {
                insertQuery.append("SELECT Count(ITEM_ID) FROM dbo.IMTD_RS_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"Operation\" != 'D' and CHECK_RECORD = 1");
                insertQuery.append(" and ");
                insertQuery.append("(((RS_DETAILS_REBATE_AMOUNT is not null and RS_DETAILS_REBATE_AMOUNT <> 0) AND (REBATE_PLAN_MASTER_SID is not null AND REBATE_PLAN_MASTER_SID <> 0)) ");
                insertQuery.append("OR ((RS_DETAILS_REBATE_AMOUNT is null or RS_DETAILS_REBATE_AMOUNT=0) AND (REBATE_PLAN_MASTER_SID is null OR REBATE_PLAN_MASTER_SID = 0)))");
                insertQuery.append("");
            } else if ("RSDetailsRebatePlanName".equals(operation)) {
                insertQuery.append("SELECT Count(ITEM_ID) FROM dbo.IMTD_RS_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"Operation\" != 'D' and(REBATE_PLAN_MASTER_SID is not null and REBATE_PLAN_MASTER_SID<>0) and CHECK_RECORD = 1;");
            } else if ("ItemRebateEndDate".equals(operation)) {
                insertQuery.append("SELECT Count(ITEM_ID) FROM dbo.IMTD_RS_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"Operation\" != 'D' and ITEM_REBATE_START_DATE>=ITEM_REBATE_END_DATE and CHECK_RECORD = 1;");
            } else if ("CheckRecord".equals(operation)) {
                insertQuery.append("SELECT Count(ITEM_ID) FROM dbo.IMTD_RS_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"Operation\" != 'D' and CHECK_RECORD = 1;");
            } else if ("ProductBundleRPNameRSAmout".equals(operation)) {
                /*
                
                 This Query will findout the bundle numbers which is having both Rebate Plan and Rebate amount.
                
                 SELECT RS_DETAILS_BUNDLE_NO FROM IMTD_RS_DETAILS  where USERS_SID='13330' and SESSION_ID='011220150748184818' 
                 and "Operation" != 'D' and CHECK_RECORD = 1
                 GROUP BY RS_DETAILS_BUNDLE_NO HAVING Count(NULLIF(REBATE_PLAN_MASTER_SID,0))>0 AND Count(NULLIF(RS_DETAILS_REBATE_AMOUNT,0))>0;
                 */
                insertQuery.append("SELECT RS_DETAILS_BUNDLE_NO FROM IMTD_RS_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"Operation\" != 'D' and CHECK_RECORD = 1");
                insertQuery.append("GROUP BY RS_DETAILS_BUNDLE_NO HAVING Count(NULLIF(REBATE_PLAN_MASTER_SID,0))>0 AND Count(NULLIF(RS_DETAILS_REBATE_AMOUNT,0))>0;");
            } else if ("ProductBundleRPName".equals(operation)) {
                /*
                
                 This Query will findout the bundle numbers which is having different rebate amounts.
                
                 SELECT RS_DETAILS_BUNDLE_NO FROM IMTD_RS_DETAILS  where USERS_SID='13330' and SESSION_ID='011220150748184818' 
                 and "Operation" != 'D' and CHECK_RECORD = 1
                 GROUP BY RS_DETAILS_BUNDLE_NO HAVING Count(NULLIF(REBATE_PLAN_MASTER_SID,0))>1;
                 */
                insertQuery.append("SELECT RS_DETAILS_BUNDLE_NO FROM IMTD_RS_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"Operation\" != 'D' and CHECK_RECORD = 1");
                insertQuery.append("GROUP BY RS_DETAILS_BUNDLE_NO HAVING Count(distinct(NULLIF(REBATE_PLAN_MASTER_SID,0)))>1;");
            } else if ("ProductBundleRSAmount".equals(operation)) {
                /*
                
                 This Query will findout the bundle numbers which is having different rebate plans.
                
                 SELECT RS_DETAILS_BUNDLE_NO FROM IMTD_RS_DETAILS  where USERS_SID='13330' and SESSION_ID='011220150748184818' 
                 and "Operation" != 'D' and CHECK_RECORD = 1
                 GROUP BY RS_DETAILS_BUNDLE_NO HAVING Count(NULLIF(REBATE_PLAN_MASTER_SID,0))>1;
                 */
                insertQuery.append("SELECT RS_DETAILS_BUNDLE_NO FROM IMTD_RS_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"Operation\" != 'D' and CHECK_RECORD = 1");
                insertQuery.append("GROUP BY RS_DETAILS_BUNDLE_NO HAVING Count(distinct(NULLIF(RS_DETAILS_REBATE_AMOUNT,0)))>1;");
            }
            else if("tempCheckedCount".equals(operation)){
                insertQuery.append("select * from dbo.Imtd_Rs_Details where ");
                if (userId != null) {
                    insertQuery.append("  users_Sid='").append(userId).append("'");
                }
                if (sessionId != null) {
                    insertQuery.append(" and session_Id='").append(sessionId).append("'");
                }
                
             if (operation.equalsIgnoreCase("tempCheckedCount") ) {
                    insertQuery.append(" and check_record = 1");
                }
            }
            else if ("ItemRebateScheduleStatus".equals(operation)) {
                insertQuery.append(" SELECT Count(RS_DETAILS_ATTACHED_STATUS) ");
                insertQuery.append(" FROM IMTD_RS_DETAILS WHERE (RS_DETAILS_ATTACHED_STATUS = 0 OR RS_DETAILS_ATTACHED_STATUS IS NULL) AND ");
                if (userId != null) {
                    insertQuery.append("  users_Sid='").append(userId).append("'");
                }
                if (sessionId != null) {
                    insertQuery.append(" and session_Id='").append(sessionId).append("'");
                }
                insertQuery.append(" AND \"OPERATION\" <> 'D' AND CHECK_RECORD = 1;");
            }
            
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());

            if(operation.contains("ProductBundle") ||operation.contains("tempCheckedCount") ) {
                result = sqlQuery.list();
            } 
             else {
                result = sqlQuery.list().get(0);
            }

        } catch (ORMException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(insertQuery.toString());
        } finally {
            closeSession(session);
        }
        return result;
    }
    public Object getTempRSLazyList(String rsId,String sessionId,String createdDate,String operation,Object start,Object offset,Object column,Object orderBy){
        Session session = null;
        Object result = 0;
        StringBuilder insertQuery = new StringBuilder();
        try {
            session = openSession();
            
            if (orderBy == null) {
                orderBy = "ASC";
            }
            insertQuery.append("SELECT ");
            if ("Selected".equals(operation)) {
                //insertQuery.append("IM.ITEM_ID, IM.ITEM_NAME ,IM.ITEM_SYSTEM_ID");
            } else {
                insertQuery.append("IM.ITEM_NO, IM.ITEM_NAME, RSD.FORMULA_ID, '' as FORMULA_NAME, RSD.REBATE_AMOUNT, RSD.ITEM_REBATE_START_DATE, RSD.ITEM_REBATE_END_DATE, RSD.ITEM_RS_ATTACHED_STATUS, '' as FORMULA_TYPE, RSD.FORMULA_METHOD_ID, '' as FORMULA_NO, '' as rebatePlanName, RSD.REBATE_PLAN_MASTER_SID, RSD.ITEM_RS_ATTACHED_DATE, RSD.MODIFIED_DATE, htype.DESCRIPTION as status, rp.REBATE_PLAN_NAME as rpName,IM.ITEM_ID,IM.ITEM_MASTER_SID");
            }
            insertQuery.append(" from dbo.ITEM_MASTER IM, dbo.RS_Details RSD LEFT JOIN HelPER_TABLE htype on htype.HELPER_TABLE_SID =  RSD.ITEM_RS_ATTACHED_STATUS");
            insertQuery.append(" LEFT JOIN dbo.REBATE_PLAN_MASTER rp on rp.REBATE_PLAN_MASTER_SID =  RSD.REBATE_PLAN_MASTER_SID ");
            insertQuery.append(" where RSD.RS_MODEL_SID=");
            insertQuery.append(rsId);
            insertQuery.append(" and RSD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID and RSD.INBOUND_STATUS != 'D'");
            String s= " ORDER BY "+column+" "+orderBy+" OFFSET "+start+" ROWS FETCH NEXT "+offset+" ROWS ONLY";
            insertQuery.append(s);
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            result = sqlQuery.list();

        } catch (ORMException e) {
            LOGGER.error(e.getMessage());;
            LOGGER.error(insertQuery.toString());
        } finally {
            closeSession(session);
        }
        return result;
    }
    
    public List loadIFPItems(int start, int offset, Object userID, Object sessionID, Object column, Object orderBy, Object value1, Object value2, Map<String, Object> parameters) {
        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            if (orderBy == null) {
                orderBy = "ASC";
            }

            sql = "SELECT cm.IMTD_RS_DETAILS_SID, cm.USERS_SID, cm.SESSION_ID, cm.IFP_MODEL_SID, cm.ITEM_NO, cm.ITEM_NAME, cm.ITEM_MASTER_SID, cm.RS_DETAILS_REBATE_AMOUNT, cm.ITEM_REBATE_START_DATE, cm.ITEM_REBATE_END_DATE, cm.RS_DETAILS_FORMULA_ID,"
                    + "cm.RS_DETAILS_FORMULA_NAME, cm.RS_DETAILS_ATTACHED_STATUS, cm.RS_DETAILS_ATTACHED_DATE, cm.RS_DETAILS_MODIFIED_DATE, cm.RS_DETAILS_FORMULA_TYPE, cm.CHECK_RECORD, cm.RS_DETAILS_BUNDLE_NO, cm.REBATE_PLAN_MASTER_SID, htype.DESCRIPTION as status, rp.REBATE_PLAN_NAME as rpName,"
                    + "cm.ITEM_ID from IMTD_RS_DETAILS cm LEFT JOIN HelPER_TABLE htype on htype.HELPER_TABLE_SID =  cm.RS_DETAILS_ATTACHED_STATUS"
                    + " LEFT JOIN dbo.REBATE_PLAN_MASTER rp on rp.REBATE_PLAN_MASTER_SID =  cm.REBATE_PLAN_MASTER_SID "
                    + " WHERE cm.USERS_SID='" + userID + "' AND SESSION_ID='" + sessionID
                    + "' AND cm.\"OPERATION\" <> 'D'";
            
            if ((parameters.get("bundleNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("bundleNo"))))) {
               sql += " AND cm.RS_DETAILS_BUNDLE_NO LIKE '%"+String.valueOf(parameters.get("bundleNo"))+"%'"; 
            }        
            if ((parameters.get("itemNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemNo"))))) {
               sql += " AND cm.ITEM_NO LIKE '%"+String.valueOf(parameters.get("itemNo"))+"%'"; 
            }
            if ((parameters.get("itemName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemName"))))) {
               sql += " AND cm.ITEM_NAME LIKE '%"+String.valueOf(parameters.get("itemName"))+"%'"; 
            }
            if (parameters.get("attachedStatus") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedStatus"))) && !"0".equalsIgnoreCase(String.valueOf(parameters.get("attachedStatus")))) {
                
               sql += " AND cm.RS_DETAILS_ATTACHED_STATUS = "+String.valueOf(parameters.get("attachedStatus"));
            }
            if (parameters.get("rebatePlanName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("rebatePlanName"))) && !"0".equalsIgnoreCase(String.valueOf(parameters.get("rebatePlanName")))) {
               sql += " AND cm.REBATE_PLAN_MASTER_SID = "+String.valueOf(parameters.get("rebatePlanName"));
            }
            if ((parameters.get("startDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDatefrom"))))) {
               sql += " AND cm.ITEM_REBATE_START_DATE >='"+String.valueOf(parameters.get("startDatefrom")+"'"); 
            }
            if ((parameters.get("startDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDateto"))))) {
               sql += " AND cm.ITEM_REBATE_START_DATE <='"+String.valueOf(parameters.get("startDateto")+"'"); 
            }
            if ((parameters.get("endDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDatefrom"))))) {
               sql += " AND cm.ITEM_REBATE_END_DATE >='"+String.valueOf(parameters.get("endDatefrom")+"'"); 
            }
            if ((parameters.get("endDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDateto"))))) {
               sql += " AND cm.ITEM_REBATE_END_DATE <='"+String.valueOf(parameters.get("endDateto")+"'"); 
            }
            if ((parameters.get("attachedDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDatefrom"))))) {
               sql += " AND cm.RS_DETAILS_ATTACHED_DATE >='"+String.valueOf(parameters.get("attachedDatefrom")+"'"); 
            }
            if ((parameters.get("attachedDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDateto"))))) {
               sql += " AND cm.RS_DETAILS_ATTACHED_DATE <='"+String.valueOf(parameters.get("attachedDateto")+"'"); 
            }
            if ((parameters.get("revisionDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("revisionDatefrom"))))) {
               sql += " AND cm.RS_DETAILS_MODIFIED_DATE >='"+String.valueOf(parameters.get("revisionDatefrom")+"'"); 
            }
            if ((parameters.get("revisionDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("revisionDateto"))))) {
               sql += " AND cm.RS_DETAILS_MODIFIED_DATE <='"+String.valueOf(parameters.get("revisionDateto")+"'"); 
            }
            
            
            sql += " ORDER BY "+column+" "+orderBy+" OFFSET "+start+" ROWS FETCH NEXT "+offset+" ROWS ONLY";

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
    
    
    public void mergeImtdRsDetailsFormula(int rsdSid,int itemSid,String userId,String sessionId){
       
         Session session = null;
         SQLQuery sqlQuery;
         String frImtdSqlUpdate=StringUtils.EMPTY;
         String frSqlUpdate=StringUtils.EMPTY;
         String sql=StringUtils.EMPTY;
        try {
            session = openSession();
            
        frImtdSqlUpdate="UPDATE IMTD_RS_DETAILS_FR SET \"Operation\" = 'D' WHERE IMTD_RS_DETAILS_SID in (SELECT IMTD_RS_DETAILS_SID FROM IMTD_RS_DETAILS WHERE CHECK_RECORD=0);";
        
        frSqlUpdate ="UPDATE RS_DETAILS_FR SET INBOUND_STATUS='D' WHERE RS_DETAILS_FR_SID in (SELECT RS_DETAILS_FR_SID FROM IMTD_RS_DETAILS_FR WHERE USERS_ID='"
                +userId+"' and SESSION_ID='"+sessionId+"' and \"Operation\" = 'D');";
       
        
       
        
        sql = "MERGE RS_DETAILS_FR T USING IMTD_RS_DETAILS_FR S ON (T.ITEM_MASTER_SID="+itemSid+" AND S.RS_DETAILS_FR_SID=T.RS_DETAILS_FR_SID)"
                + " WHEN NOT MATCHED BY TARGET AND S.Operation <> 'D' AND S.ITEM_MASTER_SID="+itemSid+" AND S.USERS_ID = '"+userId+"' AND S.SESSION_ID='"+sessionId+"' THEN"
                + " INSERT (RS_DETAILS_SID,ITEM_MASTER_SID,FORMULA_ID,FORMULA_METHOD_ID,INBOUND_STATUS,RECORD_LOCK_STATUS,CREATED_BY,MODIFIED_BY) VALUES("
                + rsdSid+","+itemSid+",S.FORMULA_ID,S.FORMULA_METHOD_ID,'A','false',"+userId+","+userId+")"
                + " WHEN MATCHED AND S.Operation <> 'D' AND S.ITEM_MASTER_SID="+itemSid+" AND S.USERS_ID = '"+userId+"' AND S.SESSION_ID='"+sessionId+"' THEN UPDATE SET"
                + " T.RS_DETAILS_SID="+rsdSid+",T.ITEM_MASTER_SID="+itemSid+", T.FORMULA_ID=S.FORMULA_ID,T.FORMULA_METHOD_ID=S.FORMULA_METHOD_ID,T.INBOUND_STATUS='U',T.RECORD_LOCK_STATUS='false',"
                + "T.CREATED_BY="+userId+",T.MODIFIED_BY="+userId+";";
                
                
                
        
        sqlQuery = session.createSQLQuery(frImtdSqlUpdate);
        sqlQuery.executeUpdate();
        
        sqlQuery = session.createSQLQuery(frSqlUpdate);
        sqlQuery.executeUpdate();
            
        sqlQuery = session.createSQLQuery(sql);
        sqlQuery.executeUpdate();
            
        } catch (ORMException e) {
            LOGGER.error(e.getMessage());;
            LOGGER.error(frImtdSqlUpdate);
            LOGGER.error(frSqlUpdate);
            LOGGER.error(sql);
        } catch(Exception e){
            LOGGER.error(e.getMessage());
            LOGGER.error(frImtdSqlUpdate);
            LOGGER.error(frSqlUpdate);
            LOGGER.error(sql);
            
        }finally {
            closeSession(session);
        }
    }
    
    public void deleteTempTableRecords(int rsSid,int rsdSid,String userId,String sessionId){
        Session session = null;
         SQLQuery sqlQuery;
         String sql=StringUtils.EMPTY;
         String formulaSql=StringUtils.EMPTY;
        try {
            session = openSession();
            sql=" DELETE  S FROM  RS_DETAILS T  JOIN IMTD_RS_DETAILS S  ON S.ITEM_MASTER_SID = T.ITEM_MASTER_SID  AND S.IFP_MODEL_SID = T.IFP_MODEL_SID "
                    + " AND S.CHECK_RECORD = 1 AND S.USERS_SID = '"+userId+"' AND S.SESSION_ID = '"+sessionId+"' "
                    + "AND T.RS_MODEL_SID = '"+rsSid+"'";
            
            formulaSql=" DELETE FROM  IMTD_RS_DETAILS_FR WHERE USERS_ID = '"+userId+"' AND SESSION_ID = '"+sessionId+"' ";
            
            sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
            
            sqlQuery = session.createSQLQuery(formulaSql);
            sqlQuery.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            LOGGER.error(formulaSql);
        } finally {
            closeSession(session);
        }
       
    }
    
    public void insertFormulaToRsdFrImtd(int rsdSid,String userId,String sessionId,String createdDate){
         
         Session session = null;
         SQLQuery sqlQuery;
         String sql = StringUtils.EMPTY;
        try {
            session = openSession();  
            
            sql = "INSERT INTO IMTD_RS_DETAILS_FR (RS_DETAILS_FR_SID,RS_DETAILS_SID,ITEM_MASTER_SID,FORMULA_ID,FORMULA_METHOD_ID,INBOUND_STATUS,RECORD_LOCK_STATUS,"
                    + "IMTD_CREATED_DATE,\"Operation\",USERS_ID,SESSION_ID) SELECT rsdf.RS_DETAILS_FR_SID,rsdf.RS_DETAILS_SID,rsdf.ITEM_MASTER_SID,rsdf.FORMULA_ID,rsdf.FORMULA_METHOD_ID,"
                    + "rsdf.INBOUND_STATUS,'false' as RECORD_LOCK_STATUS,'"+createdDate+"' as IMTD_CREATED_DATE,'U' as \"Operation\",'"+userId+"'as USERS_ID,'"
                    + sessionId+"' as SESSION_ID FROM RS_DETAILS_FR rsdf WHERE rsdf.INBOUND_STATUS <> 'D' AND rsdf.RS_DETAILS_SID="+rsdSid;
          
            sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        } finally {
            closeSession(session);
        }  
    }
    
  public void addAllFormulaToRsdFrImtd(int itemSid,String userId,String sessionId,String createdDate){
         
         Session session = null;
         SQLQuery sqlQuery;
         String sql = StringUtils.EMPTY;
        try {
            session = openSession();  
            
            sql ="MERGE IMTD_RS_DETAILS_FR T USING FORECASTING_FORMULA S ON (T.ITEM_MASTER_SID="+itemSid+" AND S.FORMULA_NAME=T.FORMULA_METHOD_ID"
                + " AND T.USERS_ID = '"+userId+"' AND T.SESSION_ID ='"+sessionId+"')"
                + " WHEN NOT MATCHED BY TARGET THEN"
                + " INSERT (ITEM_MASTER_SID,FORMULA_METHOD_ID,\"Operation\",RECORD_LOCK_STATUS,CREATED_BY,MODIFIED_BY,USERS_ID,SESSION_ID) VALUES("+itemSid
                + ",S.FORMULA_NAME,'A','false','"+userId+"','"+userId+"','"+userId+"','"+sessionId+"')"
                + " WHEN MATCHED AND T.OPERATION <> 'A' THEN UPDATE SET T.ITEM_MASTER_SID="+itemSid+","
                + " T.FORMULA_METHOD_ID=S.FORMULA_NAME,T.OPERATION='U',T.RECORD_LOCK_STATUS='false',"
                + "T.CREATED_BY="+userId+",T.MODIFIED_BY="+userId+",T.USERS_ID='"+userId+"',T.SESSION_ID='"+sessionId+"';";
           
            sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        } finally {
            closeSession(session);
        }  
    }
   
  public void remaoveAllFormulaToRsdFrImtd(int itemSid,String userId,String sessionId,String createdDate){
      Session session = null;
         SQLQuery sqlQuery;
         String sql = StringUtils.EMPTY;
        try {
            session = openSession();  
            
            sql ="UPDATE IMTD_RS_DETAILS_FR T SET T.OPERATION = 'D' WHERE T.ITEM_MASTER_SID="+itemSid+", T.USERS_ID='"+userId+"', T.SESSION_ID='"+sessionId+"',"
                    + "T.IMTD_CREATED_DATE='"+createdDate+"';";
          
            sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        } finally {
            closeSession(session);
        }  
    }
  
  public void deleteRsdFr(int rsdSid){
      Session session = null;
         SQLQuery sqlQuery;
         String sql=StringUtils.EMPTY;
        try {
            session = openSession();  
            sql="UPDATE RS_DETAILS_FR SET INBOUND_STATUS = 'D' WHERE RS_DETAILS_SID="+rsdSid;
            sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
              } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        } finally {
            closeSession(session);
        }  
  }
  
}