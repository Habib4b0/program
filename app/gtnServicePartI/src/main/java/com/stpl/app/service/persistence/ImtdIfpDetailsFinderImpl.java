package com.stpl.app.service.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.model.ImtdIfpDetails;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.portal.kernel.dao.orm.ORMException;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;

public class ImtdIfpDetailsFinderImpl extends BasePersistenceImpl<ImtdIfpDetails> implements ImtdIfpDetailsFinder{
    private static final Logger LOGGER = Logger.getLogger(ImtdIfpDetailsFinderImpl.class);
    public Object deleteAll(String userId,String sessionId,String dateCreated,String operation,Object future1,Object future2,Object future3,Object future4){
        Session session = null;
        Boolean retFlag;
        StringBuilder deleteQuery = new StringBuilder();
        try {
            session = openSession();
            
            if ("IFPDetails".equals(operation)) {
                deleteQuery.append("UPDATE dbo.IFP_DETAILS SET INBOUND_STATUS = 'D' WHERE IFP_MODEL_SID=");
                deleteQuery.append(future1);
                deleteQuery.append(";");
            } else {
                deleteQuery.append("delete FROM dbo.IMTD_IFP_DETAILS WHERE USERS_SID='");
                deleteQuery.append(userId);
                deleteQuery.append("' and SESSION_ID='");
                deleteQuery.append(sessionId);
                if ("All".equals(operation)) {
                    deleteQuery.append("';");
                } else {
                    deleteQuery.append("' and \"OPERATION\"='A';");
                    deleteQuery.append("UPDATE dbo.IMTD_IFP_DETAILS SET \"OPERATION\"='D' WHERE USERS_SID='");
                    deleteQuery.append(userId);
                    deleteQuery.append("' and SESSION_ID='");
                    deleteQuery.append(sessionId);
                    deleteQuery.append("' and \"OPERATION\"='U';");
                }
            }
            
            if("delete".equals(future2)) {
                deleteQuery.append("DELETE FROM dbo.IMTD_IFP_DETAILS WHERE USERS_SID='");
                deleteQuery.append(userId);
                deleteQuery.append("' and SESSION_ID='");
                deleteQuery.append(sessionId);
                deleteQuery.append("';");
            }
            SQLQuery sqlQuery = session.createSQLQuery(deleteQuery.toString());
            sqlQuery.executeUpdate();
          
            retFlag = true;
        } catch (ORMException e) {
            retFlag= false;
            LOGGER.error(e);
            LOGGER.error(deleteQuery.toString());
        } catch (Exception e) {
              retFlag= false;
            LOGGER.error(e);
            LOGGER.error(deleteQuery.toString());
        }finally {
            closeSession(session);
        }
        return retFlag;
    }
    public Object updateOperation(String userId,String sessionId,String createdDate,String operation,Object searchField,Object searchValue,Object searchList,Object future4, Map<String, Object> parameters){
        Object result=0;
        
         Session session = null;
         List<Integer> valueList=(List<Integer>) searchList;
         StringBuilder countQuery = new StringBuilder();
        try {
            session = openSession();
            
            countQuery.append("SELECT COUNT(IM.ITEM_MASTER_SID)");
            countQuery.append(" FROM dbo.ITEM_MASTER IM JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID");
//            countQuery.append(" left outer join dbo.IMTD_IFP_DETAILS TID");
//            countQuery.append(" ON IM.ITEM_MASTER_SID=TID.ITEM_MASTER_SID and TID.USERS_SID='");
//            countQuery.append(userId);
//            countQuery.append("' and TID.SESSION_ID='");
//            countQuery.append(sessionId);
//            countQuery.append("' AND TID.\"OPERATION\" Not In ('D','F')");
//            countQuery.append(" WHERE TID.ITEM_ID is null ");
            countQuery.append(" WHERE IM.INBOUND_STATUS != 'D' AND BM.BRAND_NAME IS NOT NULL AND BM.BRAND_NAME!= 'NONE'");
            
            if (!"%".equals(searchValue)) {
                if ("BRAND_MASTER_SID".equals(searchField)) {
                    if (!valueList.isEmpty()) {
                        countQuery.append(" and IM.");
                        countQuery.append(searchField);
                        countQuery.append("  in (");

                        Iterator<Integer> list = valueList.iterator();
                        if (list.hasNext()) {
                            countQuery.append(list.next());
                            for (; list.hasNext();) {
                                countQuery.append(",").append(list.next());
                            }
                            countQuery.append(")");
                        } else {
                            countQuery.append(")");
                        }
                    }else{
                        return result;
                    }
                    
                } else if (("PRIMARY_UOM".equalsIgnoreCase(searchField.toString()) || "Form".equalsIgnoreCase(searchField.toString()) || "Strength".equalsIgnoreCase(searchField.toString()) || "THERAPEUTIC_CLASS".equalsIgnoreCase(searchField.toString()) || "ITEM_STATUS".equalsIgnoreCase(searchField.toString())) && !"0".equals(searchValue)) {
                    countQuery.append(" and IM.");
                    countQuery.append(searchField);
                    countQuery.append(" =");
                    countQuery.append(searchValue);

                } else {
                    countQuery.append(" and IM.");
                    countQuery.append(searchField);
                    countQuery.append(" like '");
                    countQuery.append(searchValue);
                    countQuery.append("'");
                }

            }
            if ((parameters.get(ConstantsUtils.ITEM_ID) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_ID))))) {
               countQuery .append(" AND IM.ITEM_ID LIKE '%"+String.valueOf(parameters.get(ConstantsUtils.ITEM_ID))+"%'"); 
            }
            if ((parameters.get(ConstantsUtils.ITEM_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NO))))) {
               countQuery .append(" AND IM.ITEM_NO LIKE '%"+String.valueOf(parameters.get(ConstantsUtils.ITEM_NO))+"%'"); 
            }
            if ((parameters.get(ConstantsUtils.ITEM_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME))))) {
               countQuery .append(" AND IM.ITEM_NAME LIKE '%"+String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME))+"%'"); 
            }
            if ((parameters.get("itemDesc") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemDesc"))))) {
               countQuery .append(" AND IM.ITEM_DESC LIKE '%"+String.valueOf(parameters.get("itemDesc"))+"%'"); 
            }
            if ((parameters.get("displayItemStatus") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("displayItemStatus"))))) {
               countQuery .append(" AND IM.ITEM_STATUS ="+String.valueOf(parameters.get("displayItemStatus"))+""); 
            }
            if ((parameters.get("displayForm") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("displayForm"))))) {
               countQuery .append(" AND IM.FORM ="+String.valueOf(parameters.get("displayForm"))+""); 
            }
            if ((parameters.get("strength") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("strength"))))) {
               countQuery .append(" AND IM.STRENGTH ="+String.valueOf(parameters.get("strength"))+""); 
            }
            if ((parameters.get("therapeuticClass") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("therapeuticClass"))))) {
               countQuery .append(" AND IM.THERAPEUTIC_CLASS ="+String.valueOf(parameters.get("therapeuticClass"))+""); 
            }
            if ((parameters.get("brand") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("brand"))))) {
               countQuery .append(" AND IM.BRAND_MASTER_SID ="+String.valueOf(parameters.get("brand"))+""); 
            }
            if ((parameters.get("createdBy") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("createdBy"))))) {
               countQuery .append(" AND IM.CREATED_BY ="+String.valueOf(parameters.get("createdBy"))+""); 
            }
            if ((parameters.get("modifiedBy") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("modifiedBy"))))) {
               countQuery .append(" AND IM.MODIFIED_BY ="+String.valueOf(parameters.get("modifiedBy"))+""); 
            }
//            countQuery.append(" AND IM.INBOUND_STATUS != 'D';");
            countQuery.append(" ;");
            SQLQuery sqlQuery = session.createSQLQuery(countQuery.toString());
            result=sqlQuery.list().get(0);
         } catch (ORMException e) {
             LOGGER.error(e);
            LOGGER.error(countQuery.toString());
        }catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(countQuery.toString());
        } 
        finally {
            closeSession(session);
        }
        return result;
    }
    
    public Object updateForPopulate(String userId,String sessionId,String createdDate,String operation,Object populateField,Object populateValue,Object future3,Object future4){
         Session session = null;
        Boolean retFlag;
        StringBuilder insertQuery = new StringBuilder();
        try {
            session = openSession();
            
            insertQuery.append("UPDATE dbo.IMTD_IFP_DETAILS SET ");
            insertQuery.append(populateField);
            insertQuery.append("='");
            insertQuery.append(populateValue);
//            insertQuery.append("', IFP_DETAILS_MODIFIED_BY ='");
//            insertQuery.append(userId);
//            insertQuery.append("', IFP_DETAILS_MODIFIED_DATE ='");
//            insertQuery.append(createdDate);
            insertQuery.append("', OPERATION = ( CASE\n" +
"                       WHEN IFP_DETAILS_SID <> 0 THEN 'U'\n" +
"                       ELSE 'A'\n" +
"                     END )");
            insertQuery.append(" WHERE USERS_SID='");
            insertQuery.append(userId);
            insertQuery.append("' AND SESSION_ID='");
            insertQuery.append(sessionId);
            insertQuery.append("' AND \"OPERATION\" NOT IN ('D','F') AND CHECK_BOX like 1;");
            
            
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            sqlQuery.executeUpdate();
            
         retFlag=true;
         } catch (ORMException e) {
            retFlag= false;
            LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        }catch (Exception e) {
               retFlag= false;
            LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
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
            
            insertQuery.append("UPDATE dbo.IMTD_IFP_DETAILS SET ");
            insertQuery.append(populateField);
            insertQuery.append("='");
            insertQuery.append(populateValue);
//            insertQuery.append("', IFP_DETAILS_MODIFIED_BY ='");
//            insertQuery.append(userId);
//            insertQuery.append("', IFP_DETAILS_MODIFIED_DATE ='");
//            insertQuery.append(createdDate);
            insertQuery.append("', OPERATION = ( CASE\n" +
"                       WHEN IFP_DETAILS_SID <> 0 THEN 'U'\n" +
"                       ELSE 'A'\n" +
"                     END )");
            insertQuery.append(" WHERE USERS_SID='");
            insertQuery.append(userId);
            insertQuery.append("' AND SESSION_ID='");
            insertQuery.append(sessionId);
            insertQuery.append("' AND \"OPERATION\" NOT IN ('D','F');");
            
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            sqlQuery.executeUpdate();
            
         retFlag=true;
         } catch (ORMException e) {
            retFlag= false;
            LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        } catch (Exception e) {
               retFlag= false;
               LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        } finally {
            closeSession(session);
        }
        return retFlag;
    }
    
    public Object checkAll(String userId,String sessionId,String createdDate,int value,Object populateField,Object populateValue,Object future3,Object future4){
         Session session = null;
        Boolean retFlag;
        StringBuilder insertQuery = new StringBuilder();
        try {
            session = openSession();
            
            insertQuery.append("UPDATE dbo.IMTD_IFP_DETAILS SET ");
            insertQuery.append("CHECK_BOX");
            insertQuery.append("=");
            insertQuery.append(value);
            
            insertQuery.append(" WHERE USERS_SID='");
            insertQuery.append(userId);
            insertQuery.append("' AND SESSION_ID='");
            insertQuery.append(sessionId);

            insertQuery.append("' AND \"OPERATION\" NOT IN ('D','F');");
            
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            sqlQuery.executeUpdate();
            
         retFlag=true;
         } catch (ORMException e) {
            retFlag= false;
            LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        } catch (Exception e) {
               retFlag= false;
               LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        } finally {
            closeSession(session);
        }
        return retFlag;
    }
    
    
     public List getItemLazyList(int start, int offset, Object itemIdList, Object operation, Object searchValue, Object valueList,Object columnName,Object orderBy,Object future3,Object future4, Map<String, Object> parameters) {
     Session session = null;
     List result =new ArrayList();
     List<String> list=(List<String> ) itemIdList;
     List<Integer> convertedList=(List<Integer>) valueList;
     StringBuilder resultQuery = new StringBuilder();
        try {
            session = openSession();
            
            resultQuery.append("SELECT IM.ITEM_MASTER_SID, IM.ITEM_ID, IM.ITEM_NO, IM.ITEM_NAME, IM.ITEM_START_DATE, IM.ITEM_END_DATE, IM.PACKAGE_SIZE, IM.PRIMARY_UOM, IM.THERAPEUTIC_CLASS, IM.STRENGTH, IM.FORM, IM.ITEM_STATUS, IM.BRAND_MASTER_SID,IM.ITEM_DESC,BM.BRAND_NAME ");
            resultQuery.append(" FROM dbo.ITEM_MASTER IM JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID");
//            resultQuery.append(" left outer join dbo.IMTD_IFP_DETAILS TID");
//            resultQuery.append(" ON IM.ITEM_MASTER_SID=TID.ITEM_MASTER_SID and TID.USERS_SID='");
//            resultQuery.append(list.get(0));
//            resultQuery.append("' and TID.SESSION_ID='");
//            resultQuery.append(list.get(1));
//            resultQuery.append("' AND TID.\"OPERATION\" NOT IN ('D','F')");
//            resultQuery.append(" WHERE TID.ITEM_ID is null ");
            resultQuery.append(" WHERE IM.INBOUND_STATUS != 'D' AND BM.BRAND_NAME IS NOT NULL AND BM.BRAND_NAME!= 'NONE'");
            
            if (!"%".equals(searchValue)) {
                
                if ("BRAND_MASTER_SID".equals(operation)){
                    if (!convertedList.isEmpty()) {
                        resultQuery.append(" and IM.");
                        resultQuery.append(operation);
                        resultQuery.append("  in (");

                        Iterator<Integer> intList = convertedList.iterator();
                        if (intList.hasNext()) {
                            resultQuery.append(intList.next());
                            for (; intList.hasNext();) {
                                resultQuery.append(",").append(intList.next());
                            }
                            resultQuery.append(")");
                        } else {
                            resultQuery.append(")");
                        }
                    }else{
                        return result;
                    }
                    
                }else if (("PRIMARY_UOM".equalsIgnoreCase(operation.toString()) || "Form".equalsIgnoreCase(operation.toString()) || "Strength".equalsIgnoreCase(operation.toString()) || "THERAPEUTIC_CLASS".equalsIgnoreCase(operation.toString()) || "ITEM_STATUS".equalsIgnoreCase(operation.toString())) && !"0".equals(searchValue)) {
                    resultQuery.append(" and IM.");
                    resultQuery.append(operation);
                    resultQuery.append(" =");
                    resultQuery.append(searchValue);

                } else{
                    resultQuery.append(" and IM.");
                    resultQuery.append(operation);
                    resultQuery.append(" like '");
                    resultQuery.append(searchValue);
                    resultQuery.append("'");
                }
                
            }
            if ((parameters.get(ConstantsUtils.ITEM_ID) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_ID))))) {
               resultQuery .append(" AND IM.ITEM_ID LIKE '%"+String.valueOf(parameters.get(ConstantsUtils.ITEM_ID))+"%'"); 
            }
            if ((parameters.get(ConstantsUtils.ITEM_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NO))))) {
               resultQuery .append(" AND IM.ITEM_NO LIKE '%"+String.valueOf(parameters.get(ConstantsUtils.ITEM_NO))+"%'"); 
            }
            if ((parameters.get(ConstantsUtils.ITEM_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME))))) {
               resultQuery .append(" AND IM.ITEM_NAME LIKE '%"+String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME))+"%'"); 
            }
            if ((parameters.get("itemDesc") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemDesc"))))) {
               resultQuery .append(" AND IM.ITEM_DESC LIKE '%"+String.valueOf(parameters.get("itemDesc"))+"%'"); 
            }
            if ((parameters.get("displayItemStatus") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("displayItemStatus"))))) {
               resultQuery .append(" AND IM.ITEM_STATUS ="+String.valueOf(parameters.get("displayItemStatus"))+""); 
            }
            if ((parameters.get("displayForm") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("displayForm"))))) {
               resultQuery .append(" AND IM.FORM ="+String.valueOf(parameters.get("displayForm"))+""); 
            }
            if ((parameters.get("strength") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("strength"))))) {
               resultQuery .append(" AND IM.STRENGTH ="+String.valueOf(parameters.get("strength"))+""); 
            }
            if ((parameters.get("therapeuticClass") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("therapeuticClass"))))) {
               resultQuery .append(" AND IM.THERAPEUTIC_CLASS ="+String.valueOf(parameters.get("therapeuticClass"))+""); 
            }
            if ((parameters.get("brand") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("brand"))))) {
               resultQuery .append(" AND IM.BRAND_MASTER_SID ="+String.valueOf(parameters.get("brand"))+""); 
            }
            if ((parameters.get("createdBy") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("createdBy"))))) {
               resultQuery .append(" AND IM.CREATED_BY ="+String.valueOf(parameters.get("createdBy"))+""); 
            }
            if ((parameters.get("modifiedBy") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("modifiedBy"))))) {
               resultQuery .append(" AND IM.MODIFIED_BY ="+String.valueOf(parameters.get("modifiedBy"))+"");               
            }
            if ((parameters.get("ifpcreatedBy") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("ifpcreatedBy"))))) {
               resultQuery .append(" AND IM.CREATED_BY ="+String.valueOf(parameters.get("ifpcreatedBy"))+"");               
            }
//            resultQuery.append(" AND IM.INBOUND_STATUS != 'D'");
            resultQuery.append(" ORDER BY ").append(columnName).append(" ").append(orderBy).append(" " + "OFFSET ");
            resultQuery.append(start);
            resultQuery.append(" ROWS FETCH NEXT ");
            resultQuery.append(offset);
            resultQuery.append(" ROWS ONLY;");
            SQLQuery sqlQuery = session.createSQLQuery(resultQuery.toString());
            result=sqlQuery.list();
         } catch (ORMException e) {
             LOGGER.error(e);
             LOGGER.error(resultQuery.toString());
        }catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(resultQuery.toString());
        }  finally {
            closeSession(session);
        }
        return result;
       
    }
     public Object updateToIFPDetails(int ifpMasterSystemId, String userId, String sessionId, String createdDate, String operation, Object future1, Object future2, Object future3, Object future4) {
        Session session = null;
        Boolean retFlag;
        StringBuilder insertQuery = new StringBuilder();
        try {
            session = openSession();
            
            
                
                insertQuery.append("DELETE FROM dbo.IFP_DETAILS WHERE IFP_MODEL_SID=");
                insertQuery.append(ifpMasterSystemId);
                insertQuery.append(";");
                

                insertQuery.append("INSERT INTO  dbo.IFP_DETAILS (IFP_MODEL_SID,ITEM_MASTER_SID,START_DATE,END_DATE,ITEM_IFP_ATTACHED_STATUS,ITEM_IFP_ATTACHED_DATE,CREATED_BY,CREATED_DATE,RECORD_LOCK_STATUS,INBOUND_STATUS)");
                insertQuery.append(" SELECT  ");
                insertQuery.append(ifpMasterSystemId);
                insertQuery.append(" AS IFP_MODEL_SID, ITEM_MASTER_SID, IFP_DETAILS_START_DATE, IFP_DETAILS_END_DATE, IFP_DETAILS_ATTACHED_STATUS, IFP_DETAILS_ATTACHED_DATE,IFP_DETAILS_CREATED_BY, IFP_DETAILS_CREATED_DATE, 'false' as RECORD_LOCK_STATUS , 'A' as INBOUND_STATUS from dbo.IMTD_IFP_DETAILS");
                insertQuery.append(" WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"OPERATION\" <> 'D'");
                insertQuery.append(" and CHECK_BOX=1;");
                
                insertQuery.append("DELETE FROM dbo.IMTD_IFP_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("';");
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            sqlQuery.executeUpdate();
            
            retFlag = true;
        } catch (ORMException e) {
            retFlag = false;
            LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        }catch (Exception e) {
               retFlag= false;
               LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        }  finally {
            closeSession(session);
        }
        return retFlag;
    }
     
      public Object updateIFPDetails(int ifpMasterSystemId, String userId, String sessionId, String createdDate, String operation, Object future1, Object future2, Object future3, Object future4) {
        Session session = null;
        Boolean retFlag;
        StringBuilder sql = new StringBuilder();
        try {
            if(operation.equals("Flag")){
            return updateFlagIFPDetails( ifpMasterSystemId,  userId,  sessionId,  createdDate,  operation,  future1,  future2,  future3,  future4);
            }
            
            session = openSession();
            sql.append(CustomSQLUtil.get("ifpupdate1"));
            sql.append("and tmp.USERS_SID='").append(userId).append("'");
            sql.append(" and tmp.SESSION_ID='").append(sessionId).append("';");
            sql.append(CustomSQLUtil.get("ifpupdate2"));
            sql.append("and tmp.USERS_SID='").append(userId).append("'");
            sql.append(" and tmp.SESSION_ID='").append(sessionId).append("';");
            sql.append(CustomSQLUtil.get("ifpupdate3"));
            sql.append(" S.USERS_SID='").append(userId).append("'");
            sql.append(" and S.SESSION_ID='").append(sessionId).append("'");
            sql.append(CustomSQLUtil.get("ifpupdate4"));
            sql.append(" S.USERS_SID='").append(userId).append("'");
            sql.append(" and S.SESSION_ID='").append(sessionId).append("'");
            sql.append(CustomSQLUtil.get("ifpupdate5")).append(";");
            sql.append("DELETE FROM dbo.IMTD_IFP_DETAILS WHERE USERS_SID='");
            sql.append(userId);
            sql.append("' and SESSION_ID='");
            sql.append(sessionId);
            sql.append("';");
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            sqlQuery.executeUpdate();
            
            retFlag = true;
        } catch (ORMException e) {
            retFlag = false;
            LOGGER.error(e);
            LOGGER.error(sql.toString());
        }catch (Exception e) {
               retFlag= false;
               LOGGER.error(e);
            LOGGER.error(sql.toString());
        }  finally {
            closeSession(session);
        }
        return retFlag;
    }
    private Object updateFlagIFPDetails(int ifpMasterSystemId, String userId, String sessionId, String createdDate, String operation, Object future1, Object future2, Object future3, Object future4) {
        Session session = null;
        Boolean retFlag;
        String sqlQuery = StringUtils.EMPTY;
        try {
            session = openSession();
            String ifpModelSid = String.valueOf(ifpMasterSystemId);
            sqlQuery = CustomSQLUtil.get("ifpFlagupdate1");
          
            sqlQuery = sqlQuery.replaceFirst("[?]", ifpModelSid);
            sqlQuery = sqlQuery.replaceFirst("[?]", userId);
            sqlQuery = sqlQuery.replaceFirst("[?]", sessionId);
            sqlQuery = sqlQuery.replaceFirst("[?]", createdDate);
            sqlQuery = sqlQuery.replaceFirst("[?]", userId);
            sqlQuery = sqlQuery.replaceFirst("[?]", sessionId);
            sqlQuery = sqlQuery.replaceFirst("[?]", createdDate);
            sqlQuery = sqlQuery.replaceFirst("[?]", userId);
            sqlQuery = sqlQuery.replaceFirst("[?]", sessionId);

            SQLQuery sql = session.createSQLQuery(sqlQuery);
            sql.executeUpdate();

        
            retFlag = true;
        } catch (Exception e) {

            retFlag = false;
            LOGGER.error(e.getMessage());;
            LOGGER.error(sqlQuery);
        }
        return retFlag;
    }
    public Object insertTempIfpDetailsInADD(String userId, String sessionId, String createdDate, String operation, Object searchField, Object searchValue, Object future3, Object future4) {
        Session session = null;
        Boolean retFlag;
        StringBuilder insertQuery = new StringBuilder();
        try {
            session = openSession();
            
            insertQuery.append("MERGE dbo.IMTD_IFP_DETAILS AS Target");
            insertQuery.append(" USING (SELECT\n" +"IM.FORM,\n" +"IM.BRAND_MASTER_SID,\n" +"IM.ITEM_ID,\n" +"IM.ITEM_NAME,\n" +"IM.ITEM_NO,\n" +"IM.ITEM_STATUS,\n" +"IM.ITEM_DESC,\n" +"IM.PACKAGE_SIZE,\n" +"IM.PRIMARY_UOM,\n" +
            "IM.STRENGTH,\n" +"IM.ITEM_MASTER_SID,\n" +"IM.THERAPEUTIC_CLASS,\n" +"IM.INBOUND_STATUS,\n" +"BM.BRAND_NAME");
            insertQuery.append(" FROM dbo.ITEM_MASTER IM JOIN dbo.BRAND_MASTER BM\n" +
            "ON BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID WHERE IM.INBOUND_STATUS <> 'D' AND ");
            insertQuery.append(searchField);
            insertQuery.append(" like '");
            insertQuery.append(searchValue);
            insertQuery.append("' ) AS Source ON (Target.ITEM_MASTER_SID=Source.ITEM_MASTER_SID ");
            if (future4 != null && !future4.equals("null")) {
                insertQuery.append("   AND Target.IFP_MODEL_SID = ").append(future4);
            }
            insertQuery.append(" AND Target.USERS_SID ='").append(userId).append("'");
            insertQuery.append(" AND Target.SESSION_ID ='").append(sessionId).append("'");

            insertQuery.append(" )");
            insertQuery.append("WHEN MATCHED AND Target.OPERATION IN ('F', 'D') THEN");
            
            insertQuery.append(" UPDATE SET Target.\"OPERATION\" = 'A', IFP_DETAILS_SID =0");
            insertQuery.append(" WHEN NOT MATCHED BY TARGET THEN");
            insertQuery.append(" INSERT (ITEM_FORM,ITEM_STATUS, ITEM_BRAND,   ITEM_DESC, ITEM_ID, ITEM_NAME, ITEM_NO, ITEM_PACKAGE_SIZE, ITEM_PRIMARY_UOM, ITEM_STRENGTH, ITEM_MASTER_SID, ITEM_THERAPEUTIC_CLASS,\"OPERATION\", SESSION_ID, IMTD_CREATEDDATE, USERS_SID,CHECK_BOX,IFP_DETAILS_CREATED_BY, IFP_DETAILS_CREATED_DATE, IFP_DETAILS_ATTACHED_DATE,");
            if (future4 != null && !future4.equals("null")) {
                insertQuery.append("IFP_MODEL_SID,");
            }
            insertQuery.append("IFP_DETAILS_SID)");
            insertQuery.append(" VALUES (Source.FORM,Source.ITEM_STATUS, Source.BRAND_MASTER_SID, Source.ITEM_DESC, Source.ITEM_ID, Source.ITEM_NAME, Source.ITEM_NO, Source.PACKAGE_SIZE, Source.PRIMARY_UOM, Source.STRENGTH, Source.ITEM_MASTER_SID, Source.THERAPEUTIC_CLASS,");
            insertQuery.append("'A','");
            insertQuery.append(sessionId);
            insertQuery.append("','");
            insertQuery.append(createdDate);
            insertQuery.append("','");
            insertQuery.append(userId);
            insertQuery.append("',0, '");
            insertQuery.append(userId);
            insertQuery.append("','");
            insertQuery.append(createdDate);
            insertQuery.append("','");
            insertQuery.append(createdDate).append("','");
            if (future4 != null && !future4.equals("null")) {
                insertQuery.append(future4).append("','");
            }
            insertQuery.append(0).append("');");
           SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            sqlQuery.executeUpdate();

            retFlag = true;
        } catch (ORMException e) {
            retFlag = false;
            LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        } catch (Exception e) {
               retFlag= false;
               LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    public Object insertTempIfpDetailsInEdit(String userId, String sessionId, String createdDate, String operation, Object ifpId, Object future2, Object future3, Object future4) {
        Session session = null;
        Boolean retFlag;
        StringBuilder insertQuery = new StringBuilder();
        try {
            session = openSession();
            
            insertQuery.append("INSERT INTO dbo.IMTD_IFP_DETAILS (ITEM_FORM, ITEM_BRAND,ITEM_DESC, ITEM_END_DATE, ITEM_ID, ITEM_NAME, ITEM_NO, ITEM_PACKAGE_SIZE, ITEM_PRIMARY_UOM, ITEM_START_DATE, ITEM_STATUS, ITEM_STRENGTH, ITEM_MASTER_SID, ITEM_THERAPEUTIC_CLASS,");
            insertQuery.append(" IFP_DETAILS_ATTACHED_DATE,IFP_DETAILS_ATTACHED_STATUS, IFP_DETAILS_CREATED_BY, IFP_DETAILS_CREATED_DATE, IFP_DETAILS_END_DATE, IFP_DETAILS_MODIFIED_BY, IFP_DETAILS_MODIFIED_DATE, IFP_DETAILS_START_DATE, IFP_DETAILS_SID, IFP_MODEL_SID,");
            insertQuery.append(" \"OPERATION\", SESSION_ID, IMTD_CREATEDDATE, USERS_SID,CHECK_BOX)");
            insertQuery.append("SELECT IM.FORM, IM.BRAND_MASTER_SID,IM.ITEM_DESC, IM.ITEM_END_DATE, IM.ITEM_ID, IM.ITEM_NAME, IM.ITEM_NO, IM.PACKAGE_SIZE, IM.PRIMARY_UOM, IM.ITEM_START_DATE, IM.ITEM_STATUS, IM.STRENGTH, IM.ITEM_MASTER_SID, IM.THERAPEUTIC_CLASS,");
            insertQuery.append(" IFD.ITEM_IFP_ATTACHED_DATE, IFD.ITEM_IFP_ATTACHED_STATUS, IFD.CREATED_BY, IFD.CREATED_DATE, IFD.END_DATE, IFD.MODIFIED_BY, IFD.MODIFIED_DATE, IFD.START_DATE, IFD.IFP_DETAILS_SID,IFD.IFP_MODEL_SID,");
            insertQuery.append("CASE WHEN IFD.INBOUND_STATUS <>'D' Then 'A' Else 'F' End,'");
            insertQuery.append(sessionId);
            insertQuery.append("' as SESSION_ID, '");
            insertQuery.append(createdDate);
            insertQuery.append("' as IMTD_CREATEDDATE,'");
            insertQuery.append(userId);
            insertQuery.append("' as USERS_SID, 1 as CHECK_BOX");
            insertQuery.append(" from dbo.ITEM_MASTER IM, dbo.IFP_DETAILS IFD where IFD.IFP_MODEL_SID='");
            insertQuery.append(ifpId);
            insertQuery.append("' and IFD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID;");

            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            sqlQuery.executeUpdate();

            retFlag = true;
        } catch (ORMException e) { 
            retFlag = false;
            LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        }catch (Exception e) {
               retFlag= false;
               LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        }  finally {
            closeSession(session);
        }
        return retFlag;
    }

    public Object validateTempIFPDeatils(String userId, String sessionId, String createdDate, String operation, Object future1, Object future2, Object future3, Object future4) {
        Session session = null;
        Object result = 0;
        StringBuilder insertQuery = new StringBuilder();
        try {
            session = openSession();
            
            if ("AtleastOne".equals(operation)) {
                insertQuery.append("SELECT Count(ITEM_ID) FROM dbo.IMTD_IFP_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"OPERATION\" NOT IN ('D','F');");
            } else if ("StartDate".equals(operation)) {
                insertQuery.append("SELECT ITEM_ID FROM dbo.IMTD_IFP_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"OPERATION\" NOT IN ('D','F') and CHECK_BOX=1 and IFP_DETAILS_START_DATE is null;");
            } else if ("Status".equals(operation)) {
                insertQuery.append("SELECT ITEM_ID FROM dbo.IMTD_IFP_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"OPERATION\" NOT IN ('D','F') and CHECK_BOX=1 and (IFP_DETAILS_ATTACHED_STATUS is null or IFP_DETAILS_ATTACHED_STATUS='');");
            } else if ("EndDate".equals(operation)) {
                insertQuery.append("SELECT Count(ITEM_ID) FROM dbo.IMTD_IFP_DETAILS WHERE USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("' and \"OPERATION\" NOT IN ('D','F') and CHECK_BOX=1 and IFP_DETAILS_START_DATE>=IFP_DETAILS_END_DATE;");
            } else if ("checkRecord".equalsIgnoreCase(operation)) {
                insertQuery.append("select count(*) from dbo.IMTD_IFP_DETAILS where CHECK_BOX=1 and OPERATION Not In ('D','F') and USERS_SID='");
                insertQuery.append(userId);
                insertQuery.append("' and SESSION_ID='");
                insertQuery.append(sessionId);
                insertQuery.append("';");
            }

            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            if ("StartDate".equals(operation) || "Status".equals(operation)) {
                result = sqlQuery.list();
            }else{
                result = sqlQuery.list().get(0);
            }
            
        } catch (ORMException e) {
            LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        }catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        }  finally {
            closeSession(session);
        }
        return result;
    }
       public Object getTempIFPLazyList(String ifpId,String sessionId,String createdDate,String operation,Object future1,Object future2,Object column,Object orderBy){
          Session session = null;
        Object result = 0;
        StringBuilder insertQuery = new StringBuilder();
        try {
            session = openSession();
            
            insertQuery.append("SELECT ");
            if ("Selected".equals(operation)) {
                insertQuery.append("IM.ITEM_NO, IM.ITEM_NAME ,IM.ITEM_MASTER_SID, IM.ITEM_STATUS, IM.FORM, IM.STRENGTH, IM.THERAPEUTIC_CLASS, IM.BRAND_MASTER_SID, IM.ITEM_DESC");
            } else {
                insertQuery.append("IM.FORM,IM.BRAND_MASTER_SID, IM.ITEM_ID, IM.ITEM_NO, IM.ITEM_NAME, IM.PACKAGE_SIZE, IM.PRIMARY_UOM, IM.STRENGTH, IM.THERAPEUTIC_CLASS, IFD.ITEM_IFP_ATTACHED_STATUS, IFD.START_DATE, IFD.END_DATE, IFD.CREATED_BY, IFD.CREATED_DATE, IFD.MODIFIED_BY, IFD.MODIFIED_DATE, IFD.ITEM_IFP_ATTACHED_DATE, IM.ITEM_START_DATE, IM.ITEM_END_DATE, IM.ITEM_STATUS,IM.ITEM_DESC");
            } 
            insertQuery.append(" from dbo.ITEM_MASTER IM, dbo.IFP_DETAILS IFD");
            insertQuery.append(" where IFD.IFP_MODEL_SID=");
            insertQuery.append(ifpId);
            insertQuery.append(" and IFD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID and IFD.INBOUND_STATUS != 'D'");
            if(!column.toString().equals(StringUtils.EMPTY) && !orderBy.toString().equals(StringUtils.EMPTY)){
            insertQuery.append(" ORDER BY ").append(column).append(" ").append(orderBy).append(" " + "OFFSET ");
            }
            insertQuery.append(future1);
            insertQuery.append(" ROWS FETCH NEXT ");
            insertQuery.append(future2);
            insertQuery.append(" ROWS ONLY;");
            
            SQLQuery sqlQuery = session.createSQLQuery(insertQuery.toString());
            result = sqlQuery.list();

        } catch (ORMException e) {
            LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(insertQuery.toString());
        } finally {
            closeSession(session);
        }
        return result;
      }
      
       public Object updateAll(String userId,String sessionId,String dateCreated,String operation,Object tempIfpSystemId,Object deleteYesterdayRecordFlag,Object future3,Object future4){
           Session session = null;
           StringBuilder sql = new StringBuilder("");
        try {
            session = openSession();

            
            sql.append("UPDATE dbo.IMTD_IFP_DETAILS SET \"OPERATION\"='D', CHECK_BOX=0 WHERE USERS_SID='");
            sql.append(userId);
            sql.append("' and SESSION_ID='");
            sql.append(sessionId).append("'");
            if (tempIfpSystemId!=null && !tempIfpSystemId.equals("null")) {
                sql.append("and IFP_MODEL_SID='").append(tempIfpSystemId).append("'");
            }
            sql.append(";");
            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
             sqlQuery.executeUpdate ();
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        }  finally {
            closeSession(session);
        }
       
    }
      
    public List getIFPSearchList(String ifpId,String ifpNo,String ifpName,int ifpType,int ifpStatus,
            String itemId, String itemNo, String itemName, int start, int offset, String column,String orderBy,boolean countFlag, Map<String, Object> parameters){
          Session session = null;
          String sql = StringUtils.EMPTY;
          
        try {
            session = openSession();
            
//            if(orderBy!=null){
//                     
//            }else{
//                orderBy="ASC";
//            }
            
                     
            sql = "SELECT DISTINCT ifp.IFP_MODEL_SID,ifp.IFP_ID,ifp.IFP_NO, ifp.IFP_NAME,ifp.IFP_STATUS,"
                    + " ifp.IFP_TYPE,ifp.IFP_CATEGORY, ifp.IFP_START_DATE, ifp.IFP_END_DATE,ifp.IFP_DESIGNATION,"
                    + " ifp.PARENT_IFP_ID,ifp.PARENT_IFP_NAME,ifp.TOTAL_DOLLAR_COMMITMENT,ifp.COMMITMENT_PERIOD,ifp.TOTAL_VOLUME_COMMITMENT,"
                    + " ifp.TOTAL_MARKETSHARE_COMMITMENT, ifp.CREATED_BY,ifp.CREATED_DATE, htype.DESCRIPTION as itype,hstatus.DESCRIPTION as istatus,"
                    + " hcategory.DESCRIPTION as icategory,hdegin.DESCRIPTION as idesign,ifp.RECORD_LOCK_STATUS"
                    +" from IFP_MODEL ifp JOIN IFP_DETAILS ifd on ifp.IFP_MODEL_SID=ifd.IFP_MODEL_SID JOIN ITEM_MASTER im on ifd.ITEM_MASTER_SID=im.ITEM_MASTER_SID"
                    +" LEFT JOIN HELPER_TABLE htype on htype.HELPER_TABLE_SID =  ifp.IFP_TYPE​"
                    +" LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID =  ifp.IFP_STATUS"
                    +" LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID =  ifp.IFP_CATEGORY"
                    +" LEFT JOIN HELPER_TABLE hdegin on hdegin.HELPER_TABLE_SID =  ifp.IFP_DESIGNATION"
                    +" WHERE ifp.INBOUND_STATUS <> 'D'";
            
            if(StringUtils.isNotBlank(ifpId)&&StringUtils.isNotEmpty(ifpId)){
               sql += " AND ifp.IFP_ID LIKE '"+ifpId+"'";
            }
            if ((parameters.get("ifpId") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("ifpId"))))) {
               sql += " AND ifp.IFP_ID LIKE '%"+String.valueOf(parameters.get("ifpId"))+"%'"; 
            }
            if(StringUtils.isNotBlank(ifpNo)&&StringUtils.isNotEmpty(ifpNo)){
               sql += " AND ifp.IFP_NO LIKE '"+ifpNo+"'";
            }
            if ((parameters.get("ifpNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("ifpNo"))))) {
               sql += " AND ifp.IFP_NO LIKE '%"+String.valueOf(parameters.get("ifpNo"))+"%'"; 
            }
            if(StringUtils.isNotBlank(ifpName)&&StringUtils.isNotEmpty(ifpName)){
               sql += " AND ifp.IFP_NAME LIKE '"+ifpName+"'";
            }
            if ((parameters.get("ifpName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("ifpName"))))) {
               sql += " AND ifp.IFP_NAME LIKE '%"+String.valueOf(parameters.get("ifpName"))+"%'"; 
            }
            if (parameters.get("ifPStatus") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("ifPStatus"))) && !"Show all".equalsIgnoreCase(String.valueOf(parameters.get("ifPStatus")))) {
               sql += " AND ifp.IFP_STATUS = "+String.valueOf(parameters.get("ifPStatus"));
            }
            if (parameters.get("ifpType") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("ifpType"))) && !"Show all".equalsIgnoreCase(String.valueOf(parameters.get("ifpType")))) {
               sql += " AND ifp.IFP_TYPE = "+String.valueOf(parameters.get("ifpType"));
            }
            if (parameters.get("ifpCategory") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("ifpCategory")))  && !"Show all".equalsIgnoreCase(String.valueOf(parameters.get("ifpCategory")))) {
               sql += " AND ifp.IFP_CATEGORY = "+String.valueOf(parameters.get("ifpCategory"));
            }
            if (parameters.get("ifpDesignation") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("ifpDesignation"))) && !"Show all".equalsIgnoreCase(String.valueOf(parameters.get("ifpCategory")))) {
               sql += " AND ifp.IFP_DESIGNATION = "+String.valueOf(parameters.get("ifpDesignation"));
            }
            if ((parameters.get("itemFamilyplanSystemId") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemFamilyplanSystemId"))))) {
               sql += " AND ifp.IFP_MODEL_SID like '%"+String.valueOf(parameters.get("itemFamilyplanSystemId"))+"%'";
            }
            if ((parameters.get("parentItemFamilyplanId") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentItemFamilyplanId"))))) {
               sql += " AND ifp.PARENT_IFP_ID LIKE '%"+String.valueOf(parameters.get("parentItemFamilyplanId"))+"%'"; 
            }
            if ((parameters.get("parentItemFamilyplanName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentItemFamilyplanName"))))) {
               sql += " AND ifp.PARENT_IFP_NAME LIKE '%"+String.valueOf(parameters.get("parentItemFamilyplanName"))+"%'"; 
            }
            if ((parameters.get("totalDollarCommitment") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("totalDollarCommitment"))))) {
               sql += " AND ifp.TOTAL_DOLLAR_COMMITMENT LIKE '%"+String.valueOf(parameters.get("totalDollarCommitment"))+"%'"; 
            }
            if ((parameters.get("commitmentPeriod") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("commitmentPeriod"))))) {
               sql += " AND ifp.COMMITMENT_PERIOD LIKE '%"+String.valueOf(parameters.get("commitmentPeriod"))+"%'"; 
            }
            if ((parameters.get("totalVolumeCommitment") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("totalVolumeCommitment"))))) {
               sql += " AND ifp.TOTAL_VOLUME_COMMITMENT LIKE '%"+String.valueOf(parameters.get("totalVolumeCommitment"))+"%'"; 
            }
            if ((parameters.get("totalMarketshareCommitment") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("totalMarketshareCommitment"))))) {
               sql += " AND ifp.TOTAL_MARKETSHARE_COMMITMENT LIKE '%"+String.valueOf(parameters.get("totalMarketshareCommitment"))+"%'"; 
            }
            if ((parameters.get("createdBy") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("createdBy"))))) {
               sql += " AND ifp.CREATED_BY ="+String.valueOf(parameters.get("createdBy")); 
            }
            if ((parameters.get("ifpcreatedDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("ifpcreatedDatefrom"))))) {
               sql += " AND ifp.CREATED_DATE >='"+String.valueOf(parameters.get("ifpcreatedDatefrom")+"'"); 
            }
            if ((parameters.get("createdDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("createdDatefrom"))))) {
               sql += " AND ifp.CREATED_DATE >='"+String.valueOf(parameters.get("createdDatefrom")+"'"); 
            }
            if ((parameters.get("ifpcreatedDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("ifpcreatedDateto"))))) {
               sql += " AND CAST(ifp.CREATED_DATE AS DATE) <='"+String.valueOf(parameters.get("ifpcreatedDateto")+"'"); 
            }
            if ((parameters.get("createdDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("createdDateto"))))) {
               sql += " AND CAST(ifp.CREATED_DATE AS DATE) <='"+String.valueOf(parameters.get("createdDateto")+"'"); 
            }
            if ((parameters.get("itemFamilyplanStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemFamilyplanStartDatefrom"))))) {
               sql += " AND ifp.IFP_START_DATE >='"+String.valueOf(parameters.get("itemFamilyplanStartDatefrom")+"'"); 
            }
            if ((parameters.get("startDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDatefrom"))))) {
               sql += " AND ifp.IFP_START_DATE >='"+String.valueOf(parameters.get("startDatefrom")+"'"); 
            }
            if ((parameters.get("itemFamilyplanStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemFamilyplanStartDateto"))))) {
               sql += " AND CAST(ifp.IFP_START_DATE  AS DATE) <='"+String.valueOf(parameters.get("itemFamilyplanStartDateto")+"'"); 
            }
            if ((parameters.get("startDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("startDateto"))))) {
               sql += " AND CAST(ifp.IFP_START_DATE  AS DATE) <='"+String.valueOf(parameters.get("startDateto")+"'"); 
            }
            if ((parameters.get("itemFamilyplanEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemFamilyplanEndDatefrom"))))) {
               sql += " AND ifp.IFP_END_DATE >='"+String.valueOf(parameters.get("itemFamilyplanEndDatefrom")+"'"); 
            }
            if ((parameters.get("endDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDatefrom"))))) {
               sql += " AND CAST(ifp.IFP_END_DATE  AS DATE) >='"+String.valueOf(parameters.get("endDatefrom")+"'"); 
            }
            if ((parameters.get("itemFamilyplanEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemFamilyplanEndDateto"))))) {
               sql += " AND CAST(ifp.IFP_END_DATE AS DATE) <='"+String.valueOf(parameters.get("itemFamilyplanEndDateto")+"'"); 
            }
            if ((parameters.get("endDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("endDateto"))))) {
               sql += " AND CAST(ifp.IFP_END_DATE AS DATE) <='"+String.valueOf(parameters.get("endDateto")+"'"); 
            }
            if ((parameters.get("itemFamilyplanSystemIdfrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemFamilyplanSystemIdfrom"))))) {
               sql += " AND ifp.IFP_MODEL_SID >'"+String.valueOf(parameters.get("itemFamilyplanSystemIdfrom")+"'"); 
            }
            if ((parameters.get("itemFamilyplanSystemIdto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemFamilyplanSystemIdto"))))) {
               sql += " AND ifp.IFP_MODEL_SID <'"+String.valueOf(parameters.get("itemFamilyplanSystemIdto")+"'"); 
            }
            if ((parameters.get("itemFamilyplanSystemIdequal") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemFamilyplanSystemIdequal"))))) {
               sql += " AND ifp.IFP_MODEL_SID ='"+String.valueOf(parameters.get("itemFamilyplanSystemIdequal")+"'"); 
            }
             if ((parameters.get("ifpcreatedBy") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("ifpcreatedBy"))))) {
               sql += " AND ifp.CREATED_BY ="+String.valueOf(parameters.get("ifpcreatedBy")); 
            }
             
             if ((parameters.get("itemFamilyplanId") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemFamilyplanId"))))) {
                sql += " AND ifp.IFP_ID LIKE '%"+String.valueOf(parameters.get("itemFamilyplanId"))+"%'"; 
            }
             if ((parameters.get("itemFamilyplanNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemFamilyplanNo"))))) {
              sql += " AND ifp.IFP_NO LIKE '%"+String.valueOf(parameters.get("itemFamilyplanNo"))+"%'"; 
            }
             if ((parameters.get("itemFamilyplanName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("itemFamilyplanName"))))) {
              sql += " AND ifp.IFP_NAME LIKE '%"+String.valueOf(parameters.get("itemFamilyplanName"))+"%'"; 
            }
            
            if(ifpStatus != 0) {
               sql += " AND ifp.IFP_STATUS = "+ifpStatus;
            }
            
            if(ifpType != 0) {
               sql += " AND ifp.IFP_TYPE = "+ifpType;
                        
            }
            if(StringUtils.isNotBlank(itemId)&&StringUtils.isNotEmpty(itemId)){
               sql += " AND im.ITEM_ID LIKE '"+itemId+"'";
            }
            if(StringUtils.isNotBlank(itemNo)&&StringUtils.isNotEmpty(itemNo)){
               sql += " AND im.ITEM_NO LIKE '"+itemNo+"'";
            }
            if(StringUtils.isNotBlank(itemName)&&StringUtils.isNotEmpty(itemName)){
               sql += " AND im.ITEM_NAME LIKE '"+itemName+"'";
            }
            
            if(!countFlag){
            sql +=" ORDER BY "+column+" "+orderBy+" OFFSET "+start+" ROWS FETCH NEXT "+offset+" ROWS ONLY";
            }
            SQLQuery sqlQuery = session.createSQLQuery(sql);
            return sqlQuery.list();

        } catch (ORMException e) {
            LOGGER.error(e.getMessage());;
            LOGGER.error(sql);
             return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());;
            LOGGER.error(sql);
               return null;
        } finally {
            closeSession(session);
        }
     
      }  
    
   public List fetchFieldsForSecurity(String moduleName, String tabName, Object obj1, Object obj2, Object obj3){
        Session session = null;
        String query = "";
        try {
            session = openSession();
        

        query = "SELECT DISPLAY_NAME, PROPERTY_NAME ,CATEGORY_NAME FROM MODULE_PROPERTIES WHERE MODULE_NAME = '"+moduleName+"' "
                + " AND TAB_NAME = '"+tabName+"' AND CATEGORY_NAME NOT IN ('Button','Tab') ";
        SQLQuery sqlQuery = session.createSQLQuery(query);
        return sqlQuery.list();
        } catch(Exception ex){
           LOGGER.error(ex.getMessage());
           LOGGER.error(query);
           return null;           
        } finally {
            closeSession(session);
        }
        
    }
        }
