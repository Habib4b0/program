package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdPsDetails;
import com.stpl.app.serviceUtils.CommonUtils;
import com.stpl.portal.kernel.dao.orm.ORMException;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

public class ImtdPsDetailsFinderImpl extends BasePersistenceImpl<ImtdPsDetails> implements ImtdPsDetailsFinder {

    private static final Logger LOGGER = Logger.getLogger(ImtdPsDetailsFinderImpl.class);
    public static final String SHOW_ALL = "Show All";
    public static final String YES = "Yes";

    public Object deleteAll(String userId, String sessionId, String dateCreated, String operation, Object type, Object psId, Object ifpId, Object future4) {
        Session session = null;
        StringBuilder sql = new StringBuilder("");
        try {
            session = openSession();
           
            if("Delete".equals(type)){
            
            sql.append("DELETE FROM Imtd_Ps_Details WHERE ");
            if (operation == null) {
                if (userId != null) {
                    sql.append("users_Sid=").append(userId);
                }
                if (sessionId != null) {
                    sql.append(" and session_Id='").append(sessionId).append("'");
                }
                if (dateCreated != null) {
                    sql.append(" and created_Date='").append(dateCreated).append("'");
                }
            } else {
                sql.append("imtd_Ps_Details_Sid='").append(operation).append("'");
            }
            }else {
                sql.append(" UPDATE Imtd_Ps_Details SET Check_Record = 0, \"operation\" ='D' ");
                sql.append(" WHERE ");
                sql.append(" users_Sid='").append(userId).append("'");
                sql.append(" and Session_Id='").append(sessionId).append("'");
                sql.append(" and ps_Model_Sid='").append(psId).append("'");
               
            }
            
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.executeUpdate();
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());;
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    public Object updateOperation(String userId, String sessionId, String createdDate, String operation, Object future1, Object future2, Object future3, Object future4) {
        return true;
    }

    public Object updateForPopulate(String userId, String sessionId, String createdDate, String operation, Object populateField, Object populateValue, Object mode, Object psMasterId) {
        Session session = null;
        Boolean retFlag;
        StringBuilder insertQuery = new StringBuilder();
        try {
            SimpleDateFormat simpledateformat5 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            String currentDate = simpledateformat5.format(new Date());
            session = openSession();
            insertQuery.append("UPDATE dbo.Imtd_Ps_Details SET ");
                        insertQuery.append(populateField);
                        insertQuery.append("='");
                        insertQuery.append(populateValue).append("' ");
                       if ("PS_DETAILS_PRICETYPE".equals(populateField)) {
                            insertQuery.append(", PS_DETAILS_PRICE");
                            insertQuery.append(" = 0 ");
                        } else if ("PS_DETAILS_PRICE".equals(populateField)) {
                            insertQuery.append(", PS_DETAILS_PRICETYPE");
                            insertQuery.append(" = (select ITEM_PRICING_QUALIFIER_SID from ITEM_PRICING_QUALIFIER where ITEM_PRICING_QUALIFIER_NAME like 'contract%') ");
                        }
//                }
                if("Edit".equals(mode)){
                    insertQuery.append(", ps_Details_Modified_By ='");
                    insertQuery.append(userId).append("'");
                    insertQuery.append(", ps_Details_Modified_Date ='");
                    insertQuery.append(createdDate).append("'");
                    insertQuery.append(", \"operation\" = ( CASE WHEN ps_Details_Sid <> 0 THEN 'U' ELSE 'A' END ) ");
                    insertQuery.append(" , ps_Details_Revision_Date =").append("convert(datetime,'").append(currentDate).append("')");
                }
            insertQuery.append(" WHERE users_Sid='");
            insertQuery.append(userId);
            insertQuery.append("' AND session_Id='");
            insertQuery.append(sessionId);
            if(((Integer)psMasterId)!=0){
                insertQuery.append("' AND ps_Model_Sid='");
                insertQuery.append(psMasterId);                
            }
            insertQuery.append("' AND \"operation\" not in ('D','F') AND check_Record like 1;");
            LOGGER.debug("insertQuery populate ...>>>>" + insertQuery.toString());
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            sqlQuery.executeUpdate();

            retFlag = true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());;
            LOGGER.error(insertQuery.toString());
            retFlag = false;
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

   public Object updateForPopulateAll(String userId, String sessionId, String createdDate, String operation, Object populateField, Object populateValue, Object mode, Object psMasterId) {
        Session session = null;
        Boolean retFlag;
        StringBuilder insertQuery = new StringBuilder();
        try {
        SimpleDateFormat simpledateformat5 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        String currentDate = simpledateformat5.format(new Date());            
            session = openSession();
            insertQuery.append("UPDATE dbo.Imtd_Ps_Details SET ");
            if("checked".equals(operation)){
                insertQuery.append(" check_Record = 1 ");
            }else if("unChecked".equals(operation)){
                insertQuery.append(" check_Record = 0 ");
            }else{
                insertQuery.append(populateField);
                insertQuery.append("='");
                insertQuery.append(populateValue).append("' ");
                if ("PS_DETAILS_PRICETYPE".equals(populateField)) {
                    insertQuery.append(", PS_DETAILS_PRICE");
                    insertQuery.append(" = 0 ");
                } else if ("PS_DETAILS_PRICE".equals(populateField)) {
                    insertQuery.append(", PS_DETAILS_PRICETYPE");
                    insertQuery.append(" = (select ITEM_PRICING_QUALIFIER_SID from ITEM_PRICING_QUALIFIER where ITEM_PRICING_QUALIFIER_NAME like 'contract%') ");
                }
                if("Edit".equals(mode)){
                    insertQuery.append(", ps_Details_Modified_By ='");
                    insertQuery.append(userId).append("'");
                    insertQuery.append(", ps_Details_Modified_Date ='");
                    insertQuery.append(createdDate).append("'");
                    insertQuery.append(", \"operation\" = ");
                    insertQuery.append(" ( CASE WHEN ps_Details_Sid <> 0 THEN 'U' ELSE 'A' END )");
                    insertQuery.append(" , ps_Details_Revision_Date =").append("convert(datetime,'").append(currentDate).append("')");
                }
            }
            insertQuery.append(" WHERE users_Sid='");
            insertQuery.append(userId);
            insertQuery.append("' AND session_Id='");
            insertQuery.append(sessionId);
            if(((Integer)psMasterId)!=0){
                insertQuery.append("' AND ps_Model_Sid='");
                insertQuery.append(psMasterId);                
            }            
            insertQuery.append("' AND \"operation\" not in ('D','F');");
           
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            sqlQuery.executeUpdate();

            retFlag = true;
        } catch (Exception e) {
                LOGGER.error(e.getMessage());
                LOGGER.error(insertQuery.toString());
            retFlag = false;
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    public List getIfpLazyList(int start, int offset, Object ifpIdList, Object operation, Object searchValue) {
        return null;

    }

    public Object updateToPsDetails(int psSystemId, String userId, String sessionId, String createdDate, String operation, Object future1, Object future2, Object future3, Object future4) {
        Session session = null;
         StringBuilder sql = new StringBuilder("");
         String sqlQuery1 = StringUtils.EMPTY;
        try {
            session = openSession();
        SimpleDateFormat simpledateformat5 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        String currentDate = simpledateformat5.format(new Date());
            if ("update".equalsIgnoreCase(String.valueOf(future4))) {
                sql.append(CustomSQLUtil.get("softDeleteUncheckedItems1"));                
                sql.append(" , b.modified_Date =").append("convert(datetime,'").append(currentDate).append("')")
                        .append(" ,b.modified_By='").append(userId).append("'");
                sql.append(CustomSQLUtil.get("softDeleteUncheckedItems2"));                
                sql.append(" and a.ps_Model_Sid='").append(psSystemId).append("'");
                sql.append(" and a.session_id='").append(sessionId).append("'");
                sql.append(" and a.users_Sid='").append(userId).append("'");
                sql.append(CustomSQLUtil.get("softDeleteUncheckedItems3"));                                
                sql.append(" and b.ps_Model_Sid ='").append(psSystemId).append("';");
                
//--------------
                                
                sql.append(CustomSQLUtil.get("updateCheckedItems1"));
                sql.append(" , b.modified_Date =").append("convert(datetime,'").append(currentDate).append("')")
                        .append(" ,b.modified_By='").append(userId).append("'");                
                sql.append(CustomSQLUtil.get("updateCheckedItems2"));
                sql.append(" and a.ps_Model_Sid='").append(psSystemId).append("'");
                sql.append(" and a.session_Id='").append(sessionId).append("'");
                sql.append(" and a.users_Sid='").append(userId).append("';");


                sql.append(CustomSQLUtil.get("mergeCondition"));
                sql.append("and S.users_Sid='").append(userId).append("' and S.session_Id='").append(sessionId).append("'");
                sql.append(" and S.ps_Model_Sid ='").append(psSystemId).append("')");

                sql.append(CustomSQLUtil.get("mergeConditionNotMatched1"));
                sql.append(" AND S.ps_Details_Sid=0 AND S.check_Record = 1 AND S.operation = 'A' AND S.ps_Model_Sid <> 0 AND S.users_Sid = '").append(userId)
                        .append("' AND S.session_Id = '").append(sessionId)
                        .append("' AND S.ps_Model_Sid = '").append(psSystemId).append("'");
                sql.append(CustomSQLUtil.get("mergeConditionNotMatched2"));
                sql.append(", ").append(userId).append(" ,convert(datetime,'").append(currentDate).append("'), convert(datetime,'").append(currentDate).append("'))");
                                
                sql.append(CustomSQLUtil.get("mergeConditionMatched"));
                sql.append(" , T.modified_Date =").append("convert(datetime,'").append(currentDate).append("')")
                        .append(" ,T.modified_By='").append(userId).append("';");                                
               
                SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
                sqlQuery.executeUpdate();

            }else if("save".equalsIgnoreCase(String.valueOf(future4))) {
             sql.append(CustomSQLUtil.get("updatePSDetailsAttched"));
            
            sql.append(",'").append(userId).append("' ,").append("convert(datetime,'").append(currentDate).append("')");
            sql.append(",'").append(userId).append("' ,").append("convert(datetime,'").append(currentDate).append("'),");
            
            sql.append(psSystemId);

                sql.append(",'A'");

            sql.append(",'0' ");
            sql.append(" from dbo.Imtd_Ps_Details ");

            if (userId != null) {
                sql.append(" where users_Sid='").append(userId).append("'");
            }
            if (sessionId != null) {
                sql.append(" and session_Id='").append(sessionId).append("'");
            }
            if (createdDate != null) {
                sql.append(" and created_Date='").append(createdDate).append("'");
            }
            sql.append(" AND check_Record like 1;");
            
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.executeUpdate();
            
            }else{
            	sqlQuery1 = CustomSQLUtil.get("com.priceDetails.softDeleteSave");
            	
            	sqlQuery1=sqlQuery1.replaceFirst("[?]", String.valueOf(psSystemId));
            	sqlQuery1=sqlQuery1.replaceFirst("[?]", userId);
            	sqlQuery1=sqlQuery1.replaceFirst("[?]", sessionId);
            	sqlQuery1=sqlQuery1.replaceFirst("[?]", createdDate);
            	sqlQuery1=sqlQuery1.replaceFirst("[?]", userId);
            	sqlQuery1=sqlQuery1.replaceFirst("[?]", sessionId);
            	sqlQuery1=sqlQuery1.replaceFirst("[?]", createdDate);
            	sqlQuery1=sqlQuery1.replaceFirst("[?]", userId);
            	sqlQuery1=sqlQuery1.replaceFirst("[?]", sessionId);
                SQLQuery q = session.createSQLQuery(sqlQuery1);
                q.executeUpdate();
            	
            	
            }


            
            return Boolean.TRUE;            
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            LOGGER.error(sqlQuery1);
            return null;
        } finally {
            closeSession(session);
        }

    }


    public Object insertTempPsDetailsInADD(String userId, String sessionId, String createdDate, String operation, Object future1, Object future2, Object future3, Object future4) {
        Session session = null;
        Boolean retFlag;
        session = openSession();
            StringBuilder sql = new StringBuilder();
        try {
            if(!"delete".equals(future4)) {
            sql.append(CustomSQLUtil.get("insertTempPSDetailsAdd"));
            sql.append(",'").append(0).append("','").append(0).append("'");
            sql.append(",'").append(userId).append("','").append(createdDate).append("'");
            // temp createdDate
            sql.append(",'").append(createdDate).append("','");
            // sessionId
            sql.append(0).append("','");
            sql.append(sessionId).append("','");
            sql.append(userId).append("','");
            sql.append("A'");
            sql.append(",'").append(future3).append("'");
            sql.append(",'").append(0).append("'");
            sql.append(" from Ifp_Details IFD, Ifp_Model IFM, Item_Master IM ");
            sql.append(" Where IM.item_Master_Sid=IFD.item_Master_Sid AND IFM.ifp_Model_Sid=IFD.ifp_Model_Sid AND IFM.ifp_Model_Sid=");
            sql.append(operation);
            sql.append(" and IFD.INBOUND_STATUS Not in ('D','F')");
            
            }
            if("delete".equals(future4)) {
                sql.append("DELETE FROM dbo.IMTD_PS_DETAILS WHERE USERS_SID='");
                sql.append(userId);
                sql.append("' and SESSION_ID='");
                sql.append(sessionId);
                sql.append("';");
            }
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            
            sqlQuery.executeUpdate();

            retFlag = true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            retFlag = false;
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    public Object insertTempPsDetailsInEdit(String userId, String sessionId, String createdDate, String operation, Object future1, Object future2, Object future3, Object future4) {
        Session session = null;
        StringBuilder sql = new StringBuilder();
        try {
            session = openSession();
            sql.append("DELETE FROM Imtd_Ps_Details WHERE ");
            sql.append("created_Date='").append(CommonUtils.getYesterdayDate()).append("'; ");

            sql.append(" Delete from dbo.Imtd_Ps_Details where ");
            //userId
            if (userId != null) {
                sql.append("users_Sid='").append(userId).append("' ");
            }
            //sessionId
            if (sessionId != null) {
                sql.append("and session_Id='").append(sessionId).append("' ");
            }
            //createdDate
            if (createdDate != null) {
                sql.append("and created_Date='").append(createdDate).append("'; ");
            }

            sql.append(CustomSQLUtil.get("insertTempPSDetailsEdit"));

            /*
            sql.append("'").append(createdDate).append("'").append(",");
            sql.append("'").append(userId).append("'").append(",");
            */
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
                    //.append("'A'");
            sql.append(" CASE WHEN PSD.inbound_Status in ('A','U') THEN 'U' else 'F' END ");
            
           
            sql.append(" from item_Master IM,Ps_Details PSD,Ifp_Model IFM where PSD.inbound_Status in ('A','U','D') and  IFM.ifp_Model_Sid=PSD.ifp_Model_Sid and IM.item_Master_Sid=PSD.item_Master_Sid");
            if (operation != null) {
                sql.append(" and PSD.ps_Model_Sid=").append(operation);
            }
           
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.executeUpdate();
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            closeSession(session);
        }
    }

    public Object validateTempPSDeatils(String userId, String sessionId, String createdDate, String process, Object future1, Object future2, Object future3, Object future4) {
        Session session = null;
        StringBuilder sql = new StringBuilder("");
        try {
            session = openSession();
            if (process.equalsIgnoreCase("tempCount")) {
                sql.append("select * from dbo.Imtd_Ps_Details where ");
            }
            if (process.equalsIgnoreCase("Status")) {
                sql.append("select item_No from dbo.Imtd_Ps_Details where (status is null OR status = '0') and ");
            }
            if (process.equalsIgnoreCase("tempCheckedCount")) {
                sql.append("select * from dbo.Imtd_Ps_Details where ");
            }            
            if (process.equalsIgnoreCase("Price")) {
                sql.append("select item_No from dbo.Imtd_Ps_Details where (ps_Details_Price is null OR ps_Details_Price = '0') "
                        + " and ps_Details_Pricetype in (select ITEM_PRICING_QUALIFIER_SID from ITEM_PRICING_QUALIFIER where ITEM_PRICING_QUALIFIER_NAME like 'contract%') and ");
            }
            if (process.equalsIgnoreCase("PriceNull")) {
                sql.append("select item_No from dbo.Imtd_Ps_Details where ps_Details_Price is null and");
            }
            if (process.equalsIgnoreCase("CPNull")) {
                sql.append("select item_No from dbo.Imtd_Ps_Details where (PS_Details_Contract_Price is null OR PS_Details_Contract_Price = '0') and ");
            }
            if (process.equalsIgnoreCase("PriceType")) {
                sql.append("select item_No from dbo.Imtd_Ps_Details where ( ps_Details_Pricetype is null OR ps_Details_Pricetype = '0') and");
            }
            if (process.equalsIgnoreCase("CPStartDateNull")) {
                sql.append("select item_No from dbo.Imtd_Ps_Details where ps_Dtls_Cont_Price_Startdate is null and");
            }
            if (process.equalsIgnoreCase("CPStartDateEqual")) {
                sql.append("select item_No from dbo.Imtd_Ps_Details where ps_Dtls_Cont_Price_Startdate = ps_Dtls_Cont_Price_Enddate and");
            }
            if (process.equalsIgnoreCase("CPStartDateLess")) {
                sql.append("select item_No from dbo.Imtd_Ps_Details where ps_Dtls_Cont_Price_Startdate > ps_Dtls_Cont_Price_Enddate and");
            }
            if (process.equalsIgnoreCase("PPStartDateEqual")) {
                sql.append("select item_No from dbo.Imtd_Ps_Details where PS_DETAILS_PRIC_PRTCN_STDATE = ps_Details_Pric_Prtcn_Eddate and");
            }
            if (process.equalsIgnoreCase("PPStartDateLess")) {
                sql.append("select item_No from dbo.Imtd_Ps_Details where ps_Details_Pric_Prtcn_Stdate > ps_Details_Pric_Prtcn_Eddate and");
            }
            if (process.equalsIgnoreCase("itemDuplicationCheck")) {
                sql.append("select item_Id from (select item_Id,ps_Dtls_Cont_Price_Startdate,count(*) as countA from Imtd_Ps_Details where ");
            }
                       
            if (userId != null) {
                sql.append("  users_Sid='").append(userId).append("'");
            }
            if (sessionId != null) {
                sql.append(" and session_Id='").append(sessionId).append("'");
            }
            if (createdDate != null) {
                sql.append(" and created_Date='").append(createdDate).append("'");
            }
            
            if (process.equalsIgnoreCase("tempCheckedCount") || process.equalsIgnoreCase("Status") || process.equalsIgnoreCase("Price")|| process.equalsIgnoreCase("PriceType")
                    || process.equalsIgnoreCase("CPStartDateNull") || process.equalsIgnoreCase("CPStartDateEqual")
                    || process.equalsIgnoreCase("CPStartDateLess") || process.equalsIgnoreCase("PPStartDateEqual")
                    || process.equalsIgnoreCase("PPStartDateLess")) {
                sql.append(" and check_record = 1");
            } 
            
            if (process.equalsIgnoreCase("itemDuplicationCheck")) {
                sql.append(" group by item_Id,ps_Dtls_Cont_Price_Startdate) a where a.countA >1;");
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

    public Object getTempPSLazyList(String psId, String sessionId, String createdDate, String operation, Object future1, Object future2, Object future3, Object future4) {
        Session session = null;
        Object result = 0;
        StringBuilder insertQuery = new StringBuilder();
        try {
            session = openSession();
            insertQuery.append("SELECT ");
            if ("Selected".equals(operation)) {
                
            } else {
                insertQuery.append("IM.item_No, IM.item_Name, PSD.price, PSD.item_Pricing_Qualifier_Sid, PSD.contract_Price, PSD.contract_Price_Start_Date, PSD.contract_Price_End_Date, PSD.price_Tolerance, "
                        + "PSD.price_Protection_Start_Date, PSD.price_Protection_End_Date, PSD.price_Tolerance_Type, PSD.price_Tolerance_Interval, PSD.price_Tolerance_Frequency, PSD.base_Price, "
                        + "PSD.revision_Date, PSD.item_Ps_Attached_Date");
            }
            insertQuery.append(" from dbo.Item_Master IM, dbo.Ps_Details PSD");
            insertQuery.append(" where PSD.ps_Model_Sid=");
            insertQuery.append(psId);
            insertQuery.append(" and PSD.item_Master_Sid=IM.item_Master_Sid and PSD.inbound_Status != 'D'");
            insertQuery.append(" ORDER BY PSD.item_Master_Sid ASC OFFSET ");
            insertQuery.append(future1);
            insertQuery.append(" ROWS FETCH NEXT ");
            insertQuery.append(future2);
            insertQuery.append(" ROWS ONLY;");
            
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            result = sqlQuery.list();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());;
            LOGGER.error(insertQuery.toString());
        } finally {
            closeSession(session);
        }
        return result;
    }
    
      public List getItemPriceDetails(int start, int offset,String userId, String sessionId,String columnName,String orderBy,Map<String, Object> parameters){
      
     Session session = null;
     String GTN="gtn";
     String andOperator = " AND ";
     StringBuilder sql = new StringBuilder();
          try {
              session = openSession();
              if(parameters.get("Count")!=null && String.valueOf(parameters.get("Count")).equals("true")){
                  sql.append(" SELECT COUNT(IMTD_PS_DETAILS_SID) ");
              }else{
              sql.append( "SELECT IMTD_PS_DETAILS_SID,CHECK_RECORD,IFP_NO,ITEM_NAME,");
              sql.append( "ITEM_NO,ITEM_MASTER_SID,PS_DETAILS_ATTACHED_DATE,PS_DETAILS_ATTACHED_STATUS,PS_DETAILS_BASE_PRICE,");
              sql.append( "PS_DETAILS_CONTRACT_PRICE,PS_DTLS_CONT_PRICE_ENDDATE,PS_DTLS_CONT_PRICE_STARTDATE,IFP_MODEL_SID,");
              sql.append( "PS_DETAILS_PRICE,PS_DETAILS_PRIC_PRTCN_EDDATE,PS_DETAILS_PRIC_PRTCN_STDATE,temp.PS_MODEL_SID,");
              sql.append( "PS_DETAILS_PRICE_TOLERANCE,PS_DTLS_PRICE_TOLERANCE_FREQ,PS_DTLS_PRICE_TOLERANCE_INTRVL,");
              sql.append( "PS_DTLS_PRICE_TOLERANCE_TYPE,PS_DETAILS_REVISION_DATE,USERS_SID,SESSION_ID,PS_DETAILS_CREATED_BY,");
              sql.append( "PS_DETAILS_CREATED_DATE,PS_DETAILS_MODIFIED_BY,PS_DETAILS_MODIFIED_DATE,PS_DETAILS_SID,");
              sql.append( "temp.PS_MODEL_SID,OPERATION,PS_DETAILS_PRICETYPE, ipq.ITEM_PRICING_QUALIFIER_NAME as qualifiername,htolInt.DESCRIPTION as ");
              sql.append("tolinterval,htoltyp.DESCRIPTION as toltype,htolfrq.DESCRIPTION as tolfrequency, ITEM_ID, BM.BRAND_MASTER_SID, PRICE_PROTECTION_STATUS, NEP, Status, MAX_INCREMENTAL_CHANGE, RESET_ELIGIBLE");
              sql.append(",RESET_TYPE, RESET_DATE, RESET_INTERVAL, RESET_FREQUENCY, NET_PRICE_TYPE, NET_PRICE_TYPE_FORMULA, PRICE_PROTECTION_PRICE_TYPE, PS_DETAILS_SUGGESTED_PRICE ,NEP_FORMULA ,BASE_PRICE_TYPE,BASE_PRICE_ENTRY,");
              sql.append("BASE_PRICE_DATE,NET_BASE_PRICE,BASE_PRICE_DDLB,SUBSEQUENT_PERIOD_PRICE_TYPE,NET_SUBSEQUENT_PERIOD_PRICE,NET_SUBSEQUENT_PRICE_FORMULA_ID,NSPF.NET_SALES_FORMULA_NAME AS NET_SUBSEQUENT_PRICE_FORMULA_NAME");
              sql.append( ",RESET_PRICE_TYPE,NET_RESET_PRICE_TYPE,NET_RESET_PRICE_FORMULA_ID,NRPF.NET_SALES_FORMULA_NAME AS NET_RESET_PRICE_FORMULA_NAME,NET_BASE_PRICE_FORMULA_ID,NBPF.NET_SALES_FORMULA_NAME AS NET_BASE_PRICE_FORMULA_NAME");
                }
              sql.append(" FROM IMTD_PS_DETAILS temp ");
              sql.append( "LEFT JOIN ITEM_PRICING_QUALIFIER ipq on ipq.ITEM_PRICING_QUALIFIER_SID=PS_DETAILS_PRICETYPE ");
              sql.append( "LEFT JOIN HELPER_TABLE htolInt on htolint.HELPER_TABLE_SID=PS_DTLS_PRICE_TOLERANCE_INTRVL ");
              sql.append( "LEFT JOIN HELPER_TABLE htoltyp on htoltyp.HELPER_TABLE_SID=PS_DTLS_PRICE_TOLERANCE_TYPE ");
              sql.append( "LEFT JOIN HELPER_TABLE htolfrq on htolfrq.HELPER_TABLE_SID=PS_DTLS_PRICE_TOLERANCE_FREQ ");
              sql.append( "LEFT JOIN NET_SALES_FORMULA_MASTER NSPF on NSPF.NET_SALES_FORMULA_MASTER_SID = NET_SUBSEQUENT_PRICE_FORMULA_ID ");
              sql.append( "LEFT JOIN NET_SALES_FORMULA_MASTER NRPF on NRPF.NET_SALES_FORMULA_MASTER_SID = NET_RESET_PRICE_FORMULA_ID ");
              sql.append( "LEFT JOIN NET_SALES_FORMULA_MASTER NBPF on NBPF.NET_SALES_FORMULA_MASTER_SID = NET_BASE_PRICE_FORMULA_ID ");
              sql.append("LEFT JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID = TEMP.BRAND_MASTER_SID ");
              sql.append(" LEFT JOIN PS_MODEL PSM ON PSM.PS_MODEL_SID=temp.PS_MODEL_SID ");
              sql.append( "WHERE USERS_SID = '");
              sql.append(userId).append("' AND SESSION_ID = '").append(sessionId).append("' AND OPERATION NOT IN ('D','F')");
              if (parameters.get("filter~itemFamilyplanNo") != null) {
                String itemFamilyplanNo= parameters.get("filter~itemFamilyplanNo").toString();
                sql.append(andOperator).append(" IFP_NO like '").append(itemFamilyplanNo).append("' ");
                  andOperator = " AND ";
              }
              if (parameters.get("filter~itemID") != null) {
                String itemID = parameters.get("filter~itemID").toString();
                sql.append(andOperator).append(" ITEM_ID like '").append(itemID).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~itemNo") != null) {
                String itemNo= parameters.get("filter~itemNo").toString();
                sql.append(andOperator).append(" ITEM_NO like '").append(itemNo).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~itemName") != null) {
                String itemName= parameters.get("filter~itemName").toString();
                sql.append(andOperator).append(" ITEM_NAME like '").append(itemName).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("price-equal") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("price-equal")));
                sql.append(andOperator).append(" PS_DETAILS_PRICE =").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("maxIncrementalChange-equal") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("maxIncrementalChange-equal")));
                sql.append(andOperator).append(" MAX_INCREMENTAL_CHANGE =").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("maxIncrementalChange-greater") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("maxIncrementalChange-greater")));
                sql.append(andOperator).append(" MAX_INCREMENTAL_CHANGE >").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("maxIncrementalChange-less") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("maxIncrementalChange-less")));
                sql.append(andOperator).append(" MAX_INCREMENTAL_CHANGE <").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("maxIncrementalChangeandless") != null && parameters.get("maxIncrementalChangeandgreater") != null) {
                double less= Double.valueOf(String.valueOf(parameters.get("maxIncrementalChangeandless")));
                double greater= Double.valueOf(String.valueOf(parameters.get("maxIncrementalChangeandgreater")));
                sql.append(andOperator).append(" MAX_INCREMENTAL_CHANGE <").append(less).append(" AND MAX_INCREMENTAL_CHANGE > ").append(greater);
                andOperator = " AND ";
            }
            
            if (parameters.get("nep-equal") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("nep-equal")));
                sql.append(andOperator).append(" nep =").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("nep-greater") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("nep-greater")));
                sql.append(andOperator).append(" nep >").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("nep-less") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("nep-less")));
                sql.append(andOperator).append(" nep <").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("price-greater") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("price-greater")));
                sql.append(andOperator).append(" PS_DETAILS_PRICE >").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("nepandless") != null && parameters.get("nepandgreater") != null) {
                double less= Double.valueOf(String.valueOf(parameters.get("nepandless")));
                double greater= Double.valueOf(String.valueOf(parameters.get("nepandgreater")));
                sql.append(andOperator).append(" nep <").append(less).append(" AND nep > ").append(greater);
                andOperator = " AND ";
            }
            
            if (parameters.get("price-less") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("price-less")));
                sql.append(andOperator).append(" PS_DETAILS_PRICE <").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("priceandless") != null && parameters.get("priceandgreater") != null) {
                double less= Double.valueOf(String.valueOf(parameters.get("priceandless")));
                double greater= Double.valueOf(String.valueOf(parameters.get("priceandgreater")));
                sql.append(andOperator).append(" PS_DETAILS_PRICE <").append(less).append(" AND PS_DETAILS_PRICE > ").append(greater);
                andOperator = " AND ";
            }
            if (parameters.get("priceType") != null) {
                String compType= parameters.get("priceType").toString();
                sql.append(andOperator).append(" PS_DETAILS_PRICETYPE = '").append(compType).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("itemStatus") != null) {
                String compType= parameters.get("itemStatus").toString();
                sql.append(andOperator).append(" STATUS = '").append(compType).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("priceProtectionStatus") != null) {
                String compType= parameters.get("priceProtectionStatus").toString();
                sql.append(andOperator).append(" PRICE_PROTECTION_STATUS = '").append(compType).append("' ");
                andOperator = " AND ";
            }
            
            if (parameters.get("contractPrice-equal") != null) {;
                String compStatus= parameters.get("contractPrice-equal").toString();
                sql.append(andOperator).append(" PS_DETAILS_CONTRACT_PRICE =").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("contractPrice-greater") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("contractPrice-greater")));
                sql.append(andOperator).append(" PS_DETAILS_CONTRACT_PRICE >").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("contractPrice-less") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("contractPrice-less")));
                sql.append(andOperator).append(" PS_DETAILS_CONTRACT_PRICE <").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("contractPriceandless") != null && parameters.get("contractPriceandgreater") != null) {
                double less= Double.valueOf(String.valueOf(parameters.get("contractPriceandless")));
                double greater= Double.valueOf(String.valueOf(parameters.get("contractPriceandgreater")));
                sql.append(andOperator).append(" PS_DETAILS_CONTRACT_PRICE <").append(less).append(" AND PS_DETAILS_CONTRACT_PRICE > ").append(greater);
                andOperator = " AND ";
            }
            
            if (parameters.get("filter~contractPriceStartDate") != null) {
                String compStart= parameters.get("filter~contractPriceStartDate").toString();
                sql.append(andOperator).append(" CONVERT(CHAR(10),PS_DTLS_CONT_PRICE_STARTDATE,120) >=  '").append(compStart).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~contractPriceEndDate") != null) {
                String compStart= parameters.get("filter~contractPriceEndDate").toString();
                sql.append(andOperator).append(" CONVERT(CHAR(10),PS_DTLS_CONT_PRICE_ENDDATE,120) >=  '").append(compStart).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("priceTolerance-equal") != null) {
                String compStatus= parameters.get("priceTolerance-equal").toString();
                sql.append(andOperator).append(" PS_DETAILS_PRICE_TOLERANCE =").append(compStatus);
                andOperator = " AND ";
            }
            
            
            if (parameters.get("priceTolerance-greater") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("priceTolerance-greater")));
                sql.append(andOperator).append(" PS_DETAILS_PRICE_TOLERANCE >").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("priceTolerance-less") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("priceTolerance-less")));
                sql.append(andOperator).append(" PS_DETAILS_PRICE_TOLERANCE <").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("priceToleranceandless") != null && parameters.get("priceToleranceandgreater") != null) {
                double less= Double.valueOf(String.valueOf(parameters.get("priceToleranceandless")));
                double greater= Double.valueOf(String.valueOf(parameters.get("priceToleranceandgreater")));
                sql.append(andOperator).append(" PS_DETAILS_PRICE_TOLERANCE <").append(less).append(" AND PS_DETAILS_PRICE_TOLERANCE > ").append(greater);
                andOperator = " AND ";
            }
            
            
            if (parameters.get("filter~priceProtectionStartDate") != null) {
                String compStart= parameters.get("filter~priceProtectionStartDate").toString();
                sql.append(andOperator).append(" CONVERT(CHAR(10),PS_DETAILS_PRIC_PRTCN_STDATE,120) >= '").append(compStart).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~priceProtectionEndDate") != null) {
                String compStart= parameters.get("filter~priceProtectionEndDate").toString();
                sql.append(andOperator).append(" CONVERT(CHAR(10),PS_DETAILS_PRIC_PRTCN_EDDATE,120) >= '").append(compStart).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("priceToleranceType") != null) {
                String compType= parameters.get("priceToleranceType").toString();
                sql.append(andOperator).append(" PS_DTLS_PRICE_TOLERANCE_TYPE = '").append(compType).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("priceToleranceInterval") != null) {
                String compType= parameters.get("priceToleranceInterval").toString();
                sql.append(andOperator).append(" PS_DTLS_PRICE_TOLERANCE_INTRVL like '").append(compType).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("priceToleranceFrequency") != null) {
                String compType= parameters.get("priceToleranceFrequency").toString();
                sql.append(andOperator).append(" PS_DTLS_PRICE_TOLERANCE_FREQ = '").append(compType).append("' ");
                andOperator = " AND ";
            }
             if (parameters.get("attachedDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDatefrom")))) { 
                String startDate = parameters.get("attachedDatefrom").toString();
                sql.append(andOperator).append(" CONVERT(CHAR(10),PS_DETAILS_ATTACHED_DATE,120) >= '").append(startDate).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("attachedDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("attachedDateto")))) {
                String endDate = parameters.get("attachedDateto").toString();
                sql.append(andOperator).append(" CONVERT(CHAR(10),PS_DETAILS_ATTACHED_DATE,120) <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
             if (parameters.get("createdDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("createdDatefrom")))) { 
                String startDate = parameters.get("createdDatefrom").toString();
                sql.append(andOperator).append(" CONVERT(CHAR(10),PS_DETAILS_CREATED_DATE,120) >= '").append(startDate).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("createdDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("createdDateto")))) {
                String endDate = parameters.get("createdDateto").toString();
                sql.append(andOperator).append(" CONVERT(CHAR(10),PS_DETAILS_CREATED_DATE,120) <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
             if (parameters.get("contractPriceEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("contractPriceEndDatefrom")))) { 
                String startDate = parameters.get("contractPriceEndDatefrom").toString();
                sql.append(andOperator).append(" CONVERT(CHAR(10),PS_DTLS_CONT_PRICE_ENDDATE,120) >= '").append(startDate).append("' ");
                andOperator =" AND ";
            }
            if (parameters.get("contractPriceEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("contractPriceEndDateto")))) {
                String endDate = parameters.get("contractPriceEndDateto").toString();
                sql.append(andOperator).append(" CONVERT(CHAR(10),PS_DTLS_CONT_PRICE_ENDDATE,120) <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
             if (parameters.get("contractPriceStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("contractPriceStartDatefrom")))) { 
                String startDate = parameters.get("contractPriceStartDatefrom").toString();
                sql.append(andOperator).append(" CONVERT(CHAR(10),PS_DTLS_CONT_PRICE_STARTDATE,120) >= '").append(startDate).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("contractPriceStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("contractPriceStartDateto")))) {
                String endDate = parameters.get("contractPriceStartDateto").toString();
                sql.append(andOperator).append(" CONVERT(CHAR(10),PS_DTLS_CONT_PRICE_STARTDATE,120) <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
              if (parameters.get("source") != null) {
                  String source = parameters.get("source").toString();
                  if (parameters.get("mode").equals("Add") && (GTN.contains(source))) {
                      sql.append(andOperator).append(" PSM.\"SOURCE\" IS NULL ");
                  } else {
                      sql.append(andOperator).append(" PSM.\"SOURCE\" like '%").append(source).append("%' ");
                  }
                  andOperator = " AND ";
              }
              if (parameters.get("brand") != null) {
                  String source = parameters.get("brand").toString();
                
                      sql.append(andOperator).append(" BM.BRAND_NAME like '%").append(source).append("%' ");
                  
                  andOperator = " AND ";
              }
            if (parameters.get("suggestedPrice") != null) {
                String source= parameters.get("suggestedPrice").toString();
                if(source.equals("0")){
                                sql.append(andOperator).append(" PS_DETAILS_SUGGESTED_PRICE like '%").append("0.0").append("%' ");
                }else{
                sql.append(andOperator).append(" PS_DETAILS_SUGGESTED_PRICE like '%").append(source).append("%' ");
                }
                andOperator = " AND ";
            }
            if (parameters.get("basePrice-equal") != null) {
                String compType= parameters.get("basePrice-equal").toString();
                sql.append(andOperator).append(" PS_DETAILS_BASE_PRICE = ").append(compType);
                andOperator = " AND ";
            }
            
             if (parameters.get("basePrice-greater") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("basePrice-greater")));
                sql.append(andOperator).append(" PS_DETAILS_BASE_PRICE >").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("basePrice-less") != null) {
                double compStatus= Double.valueOf(String.valueOf(parameters.get("contractPrice-less")));
                sql.append(andOperator).append(" PS_DETAILS_BASE_PRICE <").append(compStatus);
                andOperator = " AND ";
            }
            
            if (parameters.get("basePriceandless") != null && parameters.get("basePriceandgreater") != null) {
                double less= Double.valueOf(String.valueOf(parameters.get("basePriceandless")));
                double greater= Double.valueOf(String.valueOf(parameters.get("basePriceandgreater")));
                sql.append(andOperator).append(" PS_DETAILS_BASE_PRICE <").append(less).append(" AND PS_DETAILS_BASE_PRICE > ").append(greater);
                andOperator = " AND ";
            }
            
            if (parameters.get("filter~revisionDate") != null) {
                String compStart= parameters.get("filter~revisionDate").toString();
                sql.append(andOperator).append(" PS_DETAILS_REVISION_DATE like '").append(compStart).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~attachedDate") != null) {
                String compStart= parameters.get("filter~attachedDate").toString();
                sql.append(andOperator).append(" PS_DETAILS_ATTACHED_DATE like '").append(compStart).append("' ");
                andOperator = " AND ";
            }

            if (parameters.get("filter~createdUserName") != null) {
                String compType= parameters.get("filter~createdUserName").toString();
                sql.append(andOperator).append(" PS_DETAILS_CREATED_BY like '").append(compType).append("' ");
                andOperator = " AND ";
            }                                   
              if (parameters.get("netBasePrice") != null && !parameters.get("netBasePrice").equals(SHOW_ALL)) {
                String compStart = parameters.get("netBasePrice").toString();
                sql.append(andOperator).append(" NET_BASE_PRICE = ").append(compStart.equals(YES) ? "1" : "0").append(" ");
                andOperator = " AND ";
            }

            if (parameters.get("netSubsequentPeriodPrice") != null && !parameters.get("netSubsequentPeriodPrice").equals(SHOW_ALL)) {
                String compStart = parameters.get("netSubsequentPeriodPrice").toString();
                sql.append(andOperator).append(" NET_SUBSEQUENT_PERIOD_PRICE = ").append(compStart.equals(YES) ? "1" : "0").append(" ");
                andOperator = " AND ";
            }

            if (parameters.get("netResetPriceType") != null && !parameters.get("netResetPriceType").equals(SHOW_ALL)) {
                String compStart = parameters.get("netResetPriceType").toString();
                sql.append(andOperator).append(" NET_RESET_PRICE_TYPE = ").append(compStart.equals(YES) ? "1" : "0").append(" ");
                andOperator = " AND ";
            }

            if (parameters.get("basePriceType") != null && !parameters.get("basePriceType").equals(SHOW_ALL)) {
                String compStart = parameters.get("basePriceType").toString();
                sql.append(andOperator).append(" BASE_PRICE_TYPE LIKE '").append(compStart).append("' ");
                andOperator = " AND ";
            }

            if (parameters.get("resetPriceType") != null && !parameters.get("resetPriceType").equals(SHOW_ALL)) {
                String compStart = parameters.get("resetPriceType").toString();
                sql.append(andOperator).append(" RESET_PRICE_TYPE = ").append(compStart).append(" ");
                andOperator = " AND ";
            }

            if (parameters.get("subsequentPeriodPriceType") != null && !parameters.get("subsequentPeriodPriceType").equals(SHOW_ALL)) {
                String compStart = parameters.get("subsequentPeriodPriceType").toString();
                sql.append(andOperator).append(" SUBSEQUENT_PERIOD_PRICE_TYPE = ").append(compStart).append(" ");
                andOperator = " AND ";
            }

            if (parameters.get("filter~netBasePriceFormulaName") != null && !parameters.get("filter~netBasePriceFormulaName").equals(SHOW_ALL)) {
                String compStart = parameters.get("filter~netBasePriceFormulaName").toString();
                sql.append(andOperator).append(" NBPF.NET_SALES_FORMULA_NAME LIKE '").append(compStart).append("' ");
                andOperator = " AND ";
            }

            if (parameters.get("filter~netSubsequentPriceFormulaName") != null && !parameters.get("filter~netSubsequentPriceFormulaName").equals(SHOW_ALL)) {
                String compStart = parameters.get("filter~netSubsequentPriceFormulaName").toString();
                sql.append(andOperator).append(" NSPF.NET_SALES_FORMULA_NAME LIKE '").append(compStart).append("' ");
                andOperator = " AND ";
            }

            if (parameters.get("filter~netResetPriceFormulaName") != null && !parameters.get("filter~netResetPriceFormulaName").equals(SHOW_ALL)) {
                String compStart = parameters.get("filter~netResetPriceFormulaName").toString();
                sql.append(andOperator).append(" NRPF.NET_SALES_FORMULA_NAME LIKE '").append(compStart).append("' ");
                andOperator = " AND ";
            }            
            
            if (parameters.get("filter~tempPsDetailsSystemId~>") != null) {
                String value = parameters.get("filter~tempPsDetailsSystemId~>").toString();
                String[] value1 = value.split("--");
                String companySid = value1[0];
                String operator = value1[1];
                if (operator.equals(">0")) {
                    sql.append(" AND (IMTD_PS_DETAILS_SID >'").append(companySid).append("' ").append(" or");
                    sql.append(" IMTD_PS_DETAILS_SID ='").append("0").append("' )");
                }
                if (operator.equals(">")) {
                    sql.append(" AND IMTD_PS_DETAILS_SID >'").append(companySid).append("' ");
                }
            }
            if (parameters.get("filter~tempPsDetailsSystemId~<") != null) {
                String value = parameters.get("filter~tempPsDetailsSystemId~<").toString();
                String[] value1 = value.split("--");
                String companySid = value1[0];
                String operator = value1[1];
                if (operator.equals("<0")) {
                    sql.append(" AND (IMTD_PS_DETAILS_SID <'").append(companySid).append("' ").append(" or");
                    sql.append(" IMTD_PS_DETAILS_SID ='").append("0").append("') ");
                }
                if (operator.equals("<")) {
                    sql.append(" AND IMTD_PS_DETAILS_SID <'").append(companySid).append("' ");
                }
            }
            if (parameters.get("filter~tempPsDetailsSystemId~=") != null) {
                String value = parameters.get("filter~tempPsDetailsSystemId~=").toString();
                String[] value1 = value.split("--");
                String companySid = value1[0];
                String operator = value1[1];
                if (operator.equals("0") || operator.equals("=")) {
                    sql.append(" AND IMTD_PS_DETAILS_SID ='").append(companySid).append("' ");
                }
            }
            if (parameters.get("filter~tempPsDetailsSystemId~<<") != null || parameters.get("filter~tempPsDetailsSystemId~>>") != null) {
                String lesser = parameters.get("filter~tempPsDetailsSystemId~<<").toString();
                String greater = parameters.get("filter~tempPsDetailsSystemId~>>").toString();
                String[] lesser1 = lesser.split("--");
                String[] greater1 = greater.split("--");
                String companySidLesser = lesser1[0];
                String companySidGreater = greater1[0];
                sql.append(" AND IMTD_PS_DETAILS_SID > '");
                sql.append(companySidGreater).append("'");
                sql.append(" AND IMTD_PS_DETAILS_SID < '");

                sql.append(companySidLesser).append("'");
              }
                  if (parameters.get("Current") != null && parameters.get("History") != null && parameters.get("Future") != null) {
                      sql.append(" AND ( '").append(parameters.get("Current")).append("' BETWEEN PS_DTLS_CONT_PRICE_STARTDATE AND ISNULL(PS_DTLS_CONT_PRICE_ENDDATE,'").append(parameters.get("Current")).append("') ");
                      sql.append(" OR ").append(" PS_DTLS_CONT_PRICE_ENDDATE < '").append(parameters.get("History")).append("' ");
                      sql.append(" OR ").append(" PS_DTLS_CONT_PRICE_STARTDATE > '").append(parameters.get("Future")).append("' )");
                  } else if ((parameters.get("Current") != null && parameters.get("History") != null) || (parameters.get("History") != null && parameters.get("Current") != null)) {
                      sql.append(" AND ( '").append(parameters.get("Current")).append("' BETWEEN PS_DTLS_CONT_PRICE_STARTDATE AND ISNULL(PS_DTLS_CONT_PRICE_ENDDATE,'").append(parameters.get("Current")).append("') OR PS_DTLS_CONT_PRICE_ENDDATE < '").append(parameters.get("History")).append("') ");
                  } else if ((parameters.get("History") != null && parameters.get("Future") != null) || (parameters.get("Future") != null && parameters.get("History") != null)) {
                      sql.append(" AND (").append(" PS_DTLS_CONT_PRICE_ENDDATE < '").append(parameters.get("History")).append("' OR PS_DTLS_CONT_PRICE_STARTDATE > '").append(parameters.get("Future")).append("') ");;
                  } else if ((parameters.get("Future") != null && parameters.get("Current") != null) || (parameters.get("Current") != null && parameters.get("Future") != null)) {
                      sql.append(" AND ( '").append(parameters.get("Current")).append("' BETWEEN PS_DTLS_CONT_PRICE_STARTDATE AND ISNULL(PS_DTLS_CONT_PRICE_ENDDATE,'").append(parameters.get("Current")).append("') OR PS_DTLS_CONT_PRICE_STARTDATE > '").append(parameters.get("Future")).append("') ");
                  } else if (parameters.get("Current") != null) {
                      sql.append(" AND '").append(parameters.get("Current")).append("' BETWEEN PS_DTLS_CONT_PRICE_STARTDATE AND ISNULL(PS_DTLS_CONT_PRICE_ENDDATE,'").append(parameters.get("Current")).append("') ");
                  } else if (parameters.get("History") != null) {
                      sql.append(" AND ").append(" PS_DTLS_CONT_PRICE_ENDDATE < '").append(parameters.get("History")).append("' ");
                  } else if (parameters.get("Future") != null) {
                      sql.append(" AND ").append(" PS_DTLS_CONT_PRICE_STARTDATE > '").append(parameters.get("Future")).append("' ");
                  }
              if(parameters.get("Count")!=null && String.valueOf(parameters.get("Count")).equals("false")){
              sql.append(" ORDER BY ").append(columnName).append(" ")
                      .append(orderBy).append(" OFFSET ").append(start).append(" ROWS FETCH NEXT ").append(offset).append(" ROWS ONLY");
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

    public Object executeUpdateQuery(String queryList, Object obj1, Object obj2) {
        Session session = null;
        try {
            session = openSession();
            Query sqlQuery = session.createSQLQuery(queryList);
            sqlQuery.executeUpdate();
            session.flush();
            return true;
        } catch (ORMException ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(queryList);
        } finally {
            closeSession(session);
            clearCache();
        }
        return false;
    }
   
}