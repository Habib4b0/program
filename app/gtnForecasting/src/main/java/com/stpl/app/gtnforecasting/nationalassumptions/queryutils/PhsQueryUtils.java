/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.nationalassumptions.queryutils;

import com.stpl.app.gtnforecasting.dao.CommonResultsDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonResultsDAOImpl;
import static com.stpl.app.gtnforecasting.nationalassumptions.logic.CommonLogic.LOGGER;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.server.VaadinSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Nadhiya
 */
public class PhsQueryUtils {
 
    private static final CommonResultsDAO DAO = new CommonResultsDAOImpl();   
     public String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    
      public List loadPhsResultsChild(SessionDTO session, int parentSid, List<String> priceTypeList, boolean percentFlag) throws PortalException, SystemException {
          Map<String, Object> input = new HashMap<String, Object>();
          List phsList ;
          String customSql =StringUtils.EMPTY;
 
          String priceType = StringUtils.EMPTY;
          int size = priceTypeList.size();
          int lastOne = size - 1;         
          for (int i = 0; i < size; i++) {
              if (i == lastOne) {
                  priceType +=  priceTypeList.get(i).toUpperCase();
              } else {
                  priceType += priceTypeList.get(i).toUpperCase() + ",";
              }
          }
       
          input.put("?PID", session.getProjectionId());
          input.put("?IMID", parentSid);
          input.put("?PT", priceType);
        
          if(percentFlag){
         customSql = CustomSQLUtil.get(Constant.VIEW.equalsIgnoreCase(mode) ?"getPhsPercentageForView":"getPhsPercentage");
          }else{
                customSql = CustomSQLUtil.get(Constant.VIEW.equalsIgnoreCase(mode)?"getPhsAmountForView":"getPhsAmount");
          }
          for (String key : input.keySet()) {
              LOGGER.debug("Key : " + key);
              customSql = customSql.replace(key, String.valueOf(input.get(key)));
          }

          phsList = (List) DAO.executeSelectQuery( QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));

          return phsList;

    }
    public List loadPhsResultsTable(int projMasterId,int brandSid,String queryName,int parentLevelId, int itemMasterSID,int therapeuticSid) throws PortalException, SystemException {
        List phsList;          
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID", projMasterId);
        if( brandSid==0){
        input.put("?BID", Constant.NULL_CAPS);
        }else{
        input.put("?BID", brandSid);
        }
        if(therapeuticSid==0){
        input.put("?TID", Constant.NULL_CAPS);
        }else{
        input.put("?TID", therapeuticSid);
        }       
        input.put("?IMD", itemMasterSID);
        String customSql = CustomSQLUtil.get(queryName);
    
            for (String key : input.keySet()) {
                customSql = customSql.replace(key, String.valueOf(input.get(key)));
            }
            if (parentLevelId != 0) {
            customSql += " AND IM.ITEM_MASTER_SID = " + parentLevelId;
        }
     
          phsList = (List) DAO.executeSelectQuery(customSql);
      
           return phsList;
        
    }
     public static void saveSelection(Map map, int projectionID, String screenName, String saveOrUpdate) throws PortalException, SystemException {
        Object[] obj = map.keySet().toArray();
        StringBuilder queryBuilder = new StringBuilder();
        if (Constant.SAVE.equalsIgnoreCase(saveOrUpdate)) {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("INSERT INTO NA_PROJECTION_SELECTION (NA_PROJ_MASTER_SID,SCREEN_NAME,FIELD_NAME,FIELD_VALUES) VALUES ");
                queryBuilder.append("('").append(projectionID).append("','").append(screenName).append("','");
                queryBuilder.append(obj[i]).append("','").append(map.get(obj[i])).append("')\n");
            }
        } else {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("UPDATE NA_PROJECTION_SELECTION SET FIELD_NAME = '");
                queryBuilder.append(obj[i]).append("',").append("FIELD_VALUES = '").append(map.get(obj[i])).append("'");
                queryBuilder.append(" WHERE NA_PROJ_MASTER_SID = '").append(projectionID).append(" ' AND SCREEN_NAME = '").append(screenName).append("' AND FIELD_NAME ='").append(obj[i]).append("'\n");
            }
        }
        DAO.executeBulkUpdateQuery(queryBuilder.toString());
    }
    public List loadPhsWorksheet(SessionDTO session, int ndcSid,boolean adjustFlag) throws PortalException, SystemException {
        List phsWSList;
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID", session.getProjectionId());
        input.put("?IMID", ndcSid);
        String customSql;
        if (adjustFlag) {
            customSql = CustomSQLUtil.get(Constant.VIEW.equalsIgnoreCase(mode)?"getPhsWorkSheetAdjustmentForView":"getPhsWorkSheetAdjustment");
        } else {
            customSql = CustomSQLUtil.get(Constant.VIEW.equalsIgnoreCase(mode) ?"getPhsWorkSheetForView":"getPhsWorkSheet");
        }
        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }

        phsWSList = (List) DAO.executeSelectQuery( QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));

        return phsWSList;
    }
    public void saveNotes(Map<String, String> editedValues, SessionDTO session, int itemSid) throws PortalException, SystemException {
   
        List<StringBuilder> queryList = new ArrayList<StringBuilder>();
        StringBuilder queryBuilder1 = null;
        if (!editedValues.isEmpty()) {
            for (String values : editedValues.keySet()) {
                queryBuilder1 = new StringBuilder(StringUtils.EMPTY);

                String formatedValue = editedValues.get(values);

                String tempValue[] = values.split("~");
                String propertyId = tempValue[0];
                String rowId = tempValue[1];
                String qValue = propertyId.substring(1, NumericConstants.TWO);
                String yearValue = propertyId.substring(NumericConstants.TWO, NumericConstants.SIX);

                int year = Integer.parseInt(yearValue);
                int quarter = Integer.parseInt(qValue);
                Double finalvalue = 0.0;

                if (rowId.equals(Constant.ADJUSTMENT)) {
                    formatedValue = formatedValue.replace(Constant.PERCENT, StringUtils.EMPTY);
                    formatedValue = formatedValue.replace("$", StringUtils.EMPTY);
                    formatedValue = formatedValue.trim();
                    if (StringUtils.isNotBlank(formatedValue)) {
                        Double value = Double.valueOf(formatedValue);
                        finalvalue = value;
                        if (value == 0) {
                            queryBuilder1.append(" UPDATE dbo.ST_PHS_PROJ SET ADJUSTMENT=").append(Constant.NULL_CAPS);
                        } else {
                            queryBuilder1.append(" UPDATE dbo.ST_PHS_PROJ SET ADJUSTMENT='").append(finalvalue).append("' ");
                        }
                    } else {
                        queryBuilder1.append(" UPDATE dbo.ST_PHS_PROJ SET ADJUSTMENT=").append(Constant.NULL_CAPS);
                    }   
                    } else if (rowId.equals(Constant.NOTES)) {

                    queryBuilder1.append("UPDATE dbo.ST_PHS_PROJ SET NOTES='").append(formatedValue).append("' ");

                }

                queryBuilder1.append(" where NA_PROJ_DETAILS_SID ");

                queryBuilder1.append("  in ( ");

                queryBuilder1.append(" SELECT NA_PROJ_DETAILS_SID FROM  NA_PROJ_DETAILS WHERE  NA_PROJ_MASTER_SID=" + session.getProjectionId());

                queryBuilder1.append(" AND ITEM_MASTER_SID=" + itemSid);

                queryBuilder1.append(" ) AND PRICE_TYPE='PHS'");

                queryBuilder1.append(" AND PERIOD_SID in(SELECT PERIOD_SID FROM PERIOD where YEAR ='").append(year).append("'  and QUARTER ='").append(quarter).append("' ) ");


                String replacedQuery = QueryUtil.replaceTableNames(queryBuilder1.toString(), session.getCurrentTableNames());
                queryBuilder1 = new StringBuilder(replacedQuery);
                queryList.add(queryBuilder1);

            }
            DAO.executeUpdateQuery(queryList);
            queryList.clear();
        }
    }
     public List loadPhsParent(int projMasterId, int brandSid,int parentLevelId,com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO session,int therapeuticSid) throws PortalException, SystemException {
        List fcpList;
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID", projMasterId);
        if( brandSid==0){
        input.put("?BID", Constant.NULL_CAPS);
        }else{
        input.put("?BID",  brandSid);
        }
        if(therapeuticSid==0){
        input.put("?TID", Constant.NULL_CAPS);
        }else{
        input.put("?TID",therapeuticSid);
        }       
      
         if (session.isPageFlag()) {
             input.put("?OFFSET", +session.getStart() + 1);
             input.put("?LIMIT", session.getOffset());
         }

         String customSql;

         if (parentLevelId != 0) {
             customSql = CustomSQLUtil.get("getPhsLevelFilter");
             customSql += " AND IM.ITEM_MASTER_SID = " + parentLevelId;
         } else {
             customSql = CustomSQLUtil.get("getPhsParent");
         }
         for (String key : input.keySet()) {
             customSql = customSql.replace(key, String.valueOf(input.get(key)));
         }

        fcpList = (List) DAO.executeSelectQuery(customSql);
        return fcpList;
    }
}
