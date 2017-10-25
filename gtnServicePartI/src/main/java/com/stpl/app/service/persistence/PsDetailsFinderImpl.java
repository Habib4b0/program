package com.stpl.app.service.persistence;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.model.PsDetails;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;

public class PsDetailsFinderImpl extends BasePersistenceImpl<PsDetails> implements PsDetailsFinder {

    private static final Logger LOGGER = Logger.getLogger(PsDetailsFinderImpl.class);

    public List getItemAndPricingForPs(int psSystemId) {

        Session session = null;
        String sql = CustomSQLUtil
                    .get("com.global.priceschedule.service.persistence.PriceScheduleDetailsFinder.findPsDetails");
        try {
            session = openSession();

            


            if (psSystemId != 0) {
                sql += " and PSD.psModelSid="
                        + psSystemId + " ";
            }
            Query sqlQuery = session.createQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }

    }
    
    public List getPsSearchList(String psId,String psNo,String psName,int psStatus,int psType,String itemId,String itemNo,
            String itemName,Map<String,Object> filterMap,int start,int end,String column,String orderBy,boolean isCount){
        Session session = null;
        String sql = StringUtils.EMPTY;
        String createdUserName = String.valueOf(filterMap.get("createdUserName"));
        String modifiedBy = String.valueOf(filterMap.get("modifiedBy"));
       
          
        try {
            session = openSession();
           sql = "SELECT DISTINCT ps.PS_MODEL_SID,ps.PS_ID,ps.PS_NO,ps.PS_NAME,ps.PS_TYPE,ps.PS_STATUS,ps.PS_CATEGORY,ps.PS_START_DATE,ps.PS_END_DATE,"
                   + "ps.PS_DESIGNATION,ps.PARENT_PS_ID,ps.PARENT_PS_NAME,ps.PS_TRADE_CLASS,htype.DESCRIPTION as type,hstatus.DESCRIPTION as status,"
                   + "hcategory.DESCRIPTION as category,hdesign.DESCRIPTION as designation,htrade.DESCRIPTION as trade,ps.RECORD_LOCK_STATUS  FROM PS_MODEL ps"
                   + " JOIN PS_DETAILS psd on ps.PS_MODEL_SID=psd.PS_MODEL_SID"
                   + " JOIN ITEM_MASTER im on psd.ITEM_MASTER_SID=im.ITEM_MASTER_SID"
                   + " LEFT JOIN HELPER_TABLE htype on htype.HELPER_TABLE_SID=ps.PS_TYPE"
                   + " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID=ps.PS_STATUS"
                   + " LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID=ps.PS_CATEGORY"
                   + " LEFT JOIN HELPER_TABLE hdesign on hdesign.HELPER_TABLE_SID=ps.PS_DESIGNATION"
                   + " LEFT JOIN HELPER_TABLE htrade on htrade.HELPER_TABLE_SID=ps.PS_TRADE_CLASS"
                   + " WHERE ps.INBOUND_STATUS <> 'D'";
           
           if(StringUtils.isNotBlank(psId)){
               sql+=" AND ps.PS_ID LIKE '"+psId+"'";
           }
           
           if(StringUtils.isNotBlank(psNo)){
               sql+=" AND ps.PS_NO LIKE '"+psNo+"'";
           } 
           
           if(StringUtils.isNotBlank(psName)){
               sql+=" AND ps.PS_NAME LIKE '"+psName+"'";
           }
           
           if(psStatus!=0){
               sql+=" AND ps.PS_STATUS ="+psStatus;
           }
           
           if(psType!=0){
               sql+=" AND ps.PS_TYPE ="+psType;
           }
           
           if(StringUtils.isNotBlank(itemId)){
               sql+=" AND im.ITEM_ID LIKE '"+itemId+"'";
           }
           
           if(StringUtils.isNotBlank(itemNo)){
               sql+=" AND im.ITEM_NO LIKE '"+itemNo+"'";
           }
           
           if(StringUtils.isNotBlank(itemName)){
               sql+=" AND im.ITEM_NAME LIKE '"+itemName+"'";
           }
           
           if(filterMap.get("psSystemId")!=null && StringUtils.isNotBlank(String.valueOf(filterMap.get("psSystemId")))){
               sql+=" AND ps.PS_MODEL_SID LIKE '"+String.valueOf(filterMap.get("psSystemId"))+"'";
           }
           
           if(filterMap.get("priceScheduleId")!=null && StringUtils.isNotBlank(String.valueOf(filterMap.get("priceScheduleId")))){
               sql+=" AND ps.PS_ID LIKE '"+String.valueOf(filterMap.get("priceScheduleId"))+"'";
           }
           
           if(filterMap.get("priceScheduleNo")!=null && StringUtils.isNotBlank(String.valueOf(filterMap.get("priceScheduleNo")))){
               sql+=" AND ps.PS_NO LIKE '"+String.valueOf(filterMap.get("priceScheduleNo"))+"'";
           }
   
           if(filterMap.get("createdUserName")!=null && StringUtils.isNotBlank(String.valueOf(filterMap.get("createdUserName")))){
                sql += " AND ps.CREATED_BY LIKE '" + createdUserName + "'";
            }
            if(filterMap.get("modifiedBy")!=null && StringUtils.isNotBlank(String.valueOf(filterMap.get("modifiedBy")))){
                sql += " AND ps.MODIFIED_BY LIKE '" + modifiedBy + "'";
            }
           
           if(filterMap.get("priceScheduleName")!=null && StringUtils.isNotBlank(String.valueOf(filterMap.get("priceScheduleName")))){
               sql+=" AND ps.PS_NAME LIKE '"+String.valueOf(filterMap.get("priceScheduleName"))+"'";
           }
           
           if(filterMap.get("priceScheduleStatus")!=null && Integer.valueOf(String.valueOf(filterMap.get("priceScheduleStatus")))!=0){
               sql+=" AND ps.PS_STATUS = "+Integer.valueOf(String.valueOf(filterMap.get("priceScheduleStatus")));
           }
           
           if(filterMap.get("priceScheduleType")!=null && Integer.valueOf(String.valueOf(filterMap.get("priceScheduleType")))!=0){
               sql+=" AND ps.PS_TYPE = "+Integer.valueOf(String.valueOf(filterMap.get("priceScheduleType")));
           }
           
           if(filterMap.get("priceScheduleCategory")!=null && Integer.valueOf(String.valueOf(filterMap.get("priceScheduleCategory")))!=0){
               sql+=" AND ps.PS_CATEGORY = "+Integer.valueOf(String.valueOf(filterMap.get("priceScheduleCategory")));
           }
           
           if(filterMap.get("priceScheduleDesignation")!=null && Integer.valueOf(String.valueOf(filterMap.get("priceScheduleDesignation")))!=0){
               sql+=" AND ps.PS_DESIGNATION = "+Integer.valueOf(String.valueOf(filterMap.get("priceScheduleDesignation")));
           }
           if ((filterMap.get("priceScheduleStartDatestart") != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get("priceScheduleStartDatestart"))))) { 
                String startDate = filterMap.get("priceScheduleStartDatestart").toString();
                sql+=" AND ps.PS_START_DATE >= '" +startDate + "' ";
            }
            if ((filterMap.get("priceScheduleStartDateend") != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get("priceScheduleStartDateend"))))) {
                String endDate = filterMap.get("priceScheduleStartDateend").toString();
                sql+=" AND ps.PS_START_DATE <= '" + endDate + "' ";
            }
            if ((filterMap.get("priceScheduleEndDatestart") != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get("priceScheduleEndDatestart"))))) { 
                String startDate = filterMap.get("priceScheduleEndDatestart").toString();
                sql+=" AND ps.PS_END_DATE >= '" + startDate + "' ";
            }
            if ((filterMap.get("priceScheduleEndDateend") != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get("priceScheduleEndDateend"))))) {
                String endDate = filterMap.get("priceScheduleEndDateend").toString();
                sql+=" AND ps.PS_END_DATE <= '" + endDate + "' ";
            }
           if(filterMap.get("priceScheduleStartDatestart")!=null && filterMap.get("priceScheduleStartDateend")!=null 
                   && StringUtils.isNotBlank(String.valueOf(filterMap.get("priceScheduleStartDatestart"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("priceScheduleStartDateend")))){
               
               String startDate = String.valueOf(filterMap.get("priceScheduleStartDatestart"));
               String endDate = String.valueOf(filterMap.get("priceScheduleStartDateend"));
               
               sql+=" AND ps.PS_START_DATE BETWEEN '"+startDate+"' AND '"+endDate+"'";
           }
           
           if(filterMap.get("priceScheduleEndDatestart")!=null && filterMap.get("priceScheduleEndDateend")!=null 
                   && StringUtils.isNotBlank(String.valueOf(filterMap.get("priceScheduleEndDatestart"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("priceScheduleEndDateend")))){
               
               String startDate = String.valueOf(filterMap.get("priceScheduleEndDatestart"));
               String endDate = String.valueOf(filterMap.get("priceScheduleEndDateend"));
               
               sql+=" AND ps.PS_END_DATE BETWEEN '"+startDate+"' AND '"+endDate+"'";
           }
           
           if(filterMap.get("parentId")!=null && StringUtils.isNotBlank(String.valueOf(filterMap.get("parentId")))){
               sql+=" AND ps.PS_ID LIKE '"+String.valueOf(filterMap.get("parentId"))+"'";
           }
           
           if(filterMap.get("parentName")!=null && StringUtils.isNotBlank(String.valueOf(filterMap.get("parentName")))){
               sql+=" AND ps.PARENT_PS_NAME LIKE '"+String.valueOf(filterMap.get("parentName"))+"'";
           }
           
            if(filterMap.get("tradeClass")!=null && Integer.valueOf(String.valueOf(filterMap.get("tradeClass")))!=0){
               sql+=" AND ps.PS_TRADE_CLASS = "+Integer.valueOf(String.valueOf(filterMap.get("tradeClass")));
           }
           
           if(!isCount){  
              sql+= "ORDER BY " + column + " " + orderBy + " OFFSET " + start + " ROWS FETCH NEXT " + end + " ROWS ONLY";
            } 
            
            
         
            Query sqlQuery = session.createSQLQuery(sql);
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
