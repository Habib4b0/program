/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.impl;

import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.ConstantsUtils;
import com.stpl.app.gcm.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class IfpContractDetailsImpl {
     private static final Logger LOGGER = LoggerFactory.getLogger(IfpContractDetailsImpl.class);
    /**
     * Save the CFP Details to CFP Details Attached
     * 
     * @param input
     * @param future
     * @return 
     */
    public static Boolean saveIfpDetailsAttached(final List<Object> input){
        boolean retFlag;
        String sqlSaveIfpDetailsAttached = StringUtils.EMPTY;
        try {
            sqlSaveIfpDetailsAttached = SQlUtil.getQuery("com.contractDashboard.saveIFP");
            sqlSaveIfpDetailsAttached=sqlSaveIfpDetailsAttached.replaceFirst("[?]", input.get(0).toString());
            sqlSaveIfpDetailsAttached=sqlSaveIfpDetailsAttached.replaceFirst("[?]", input.get(1).toString());
            sqlSaveIfpDetailsAttached=sqlSaveIfpDetailsAttached.replaceFirst("[?]", input.get(2).toString());
            sqlSaveIfpDetailsAttached=sqlSaveIfpDetailsAttached.replaceFirst("[?]", input.get(3).toString());
            sqlSaveIfpDetailsAttached=sqlSaveIfpDetailsAttached.replaceFirst("[?]", input.get(4).toString());
            sqlSaveIfpDetailsAttached=sqlSaveIfpDetailsAttached.replaceFirst("[?]", input.get(5).toString());
            sqlSaveIfpDetailsAttached=sqlSaveIfpDetailsAttached.replace("@IFP_START_DATE","'"+input.get(6)+"'");
            sqlSaveIfpDetailsAttached=sqlSaveIfpDetailsAttached.replace("@IFP_END_DATE",String.valueOf(input.get(7)).equals("null") ? "NULL" : "'"+input.get(7)+"'");
            sqlSaveIfpDetailsAttached=sqlSaveIfpDetailsAttached.replace("@ATTACHDATE",input.get(8).toString());
            HelperTableLocalServiceUtil.executeUpdateQuery(sqlSaveIfpDetailsAttached);
            retFlag = true;

        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());
            LOGGER.error(sqlSaveIfpDetailsAttached);
        }
        return retFlag;

    }
    
    public List findIFP(final Object field, final Object value, final List<Integer> future,Map<String, Object> filterMap, int start, int end, String column, String orderBy){
        StringBuilder sql = new StringBuilder();
        try {
            
            if(orderBy == null) {
                orderBy = "ASC";
            }
            
            if (Constants.BRAND_NAME.equals(field.toString())) {
                sql.append(SQlUtil.getQuery("findBrand"));
                if (!"%".equals(value.toString().trim())) {
                    sql.append( " and im.BRAND_MASTER_SID in (");
                    Iterator<Integer> list=future.iterator();
                    if (list.hasNext()) {
                        sql.append(list.next());
                        for (;list.hasNext();) {
                            sql.append(',').append(list.next());
                        }
                        sql.append(')');
                    }else{
                        sql.append(')');
                    }
                }
                
            }else{
                if ("IFP_NO".equalsIgnoreCase(field.toString()) || "IFP_NAME".equalsIgnoreCase(field.toString())) {
                    sql.append(SQlUtil.getQuery("findIFP"));
                    sql.append(" where ifp.").append(String.valueOf(field)).append(" like '" ).append( String.valueOf(value) ).append( "' ");
                }else{
                    sql.append(SQlUtil.getQuery("findItems"));
                    if (Constants.STRENGTH_PROPERTY.equalsIgnoreCase(field.toString()) || "THERAPEUTIC_CLASS".equalsIgnoreCase(field.toString())) {
                        sql.append(" and im.").append(String.valueOf(field)).append(" = " ).append( String.valueOf(value) ).append( ' ');
                    }else{
                        sql.append(" and im.").append(String.valueOf(field)).append(" like '" ).append( String.valueOf(value) ).append( "' ");
                    }
                }
                
                
            }
            
            if(StringUtils.isNotBlank(String.valueOf(filterMap.get("itemNo")))){
                sql.append(" AND im.ITEM_NO LIKE '").append(String.valueOf(filterMap.get("itemNo"))).append('\'');
            }
            
            if(StringUtils.isNotBlank(String.valueOf(filterMap.get("itemName")))){
                sql.append(" AND im.ITEM_NAME LIKE '").append(String.valueOf(filterMap.get("itemName"))).append('\'');
            }
            if(StringUtils.isNotBlank(String.valueOf(filterMap.get("form")))){
                sql.append(" AND im.FORM =").append(Integer.valueOf(String.valueOf(filterMap.get("form"))));
            }
            if(filterMap.get(ConstantsUtils.ITEM_STATUS) != null && !"null".equals(String.valueOf(filterMap.get(ConstantsUtils.ITEM_STATUS))) && StringUtils.isNotBlank(String.valueOf(filterMap.get(ConstantsUtils.ITEM_STATUS)))){
                 sql.append(" AND im.ITEM_STATUS =").append(Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.ITEM_STATUS))));
            }
            if(filterMap.get(ConstantsUtils.STRENGTH) != null && !"null".equals(String.valueOf(filterMap.get(ConstantsUtils.STRENGTH))) && StringUtils.isNotBlank(String.valueOf(filterMap.get(ConstantsUtils.STRENGTH)))){
                 sql.append(" AND im.STRENGTH =").append(Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.STRENGTH))));
            }
            if(filterMap.get(ConstantsUtils.THERAPEUTIC_CLASS) != null && !"null".equals(String.valueOf(filterMap.get(ConstantsUtils.THERAPEUTIC_CLASS))) && StringUtils.isNotBlank(String.valueOf(filterMap.get(ConstantsUtils.THERAPEUTIC_CLASS)))){
                 sql.append(" AND im.THERAPEUTIC_CLASS =").append(Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.THERAPEUTIC_CLASS))));
            }
             if(filterMap.get(ConstantsUtils.PACKAGE_SIZE) != null && !"null".equals(String.valueOf(filterMap.get(ConstantsUtils.PACKAGE_SIZE))) && StringUtils.isNotBlank(String.valueOf(filterMap.get(ConstantsUtils.PACKAGE_SIZE)))){
                 sql.append(" AND im.PACKAGE_SIZE =").append(Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.PACKAGE_SIZE))));
            }
               if(filterMap.get(ConstantsUtils.ITEM_DESC) != null && !"null".equals(String.valueOf(filterMap.get(ConstantsUtils.ITEM_DESC))) && StringUtils.isNotBlank(String.valueOf(filterMap.get(ConstantsUtils.ITEM_DESC)))){
                 sql.append(" AND im.ITEM_DESC LIKE '").append(String.valueOf(filterMap.get(ConstantsUtils.ITEM_DESC))).append('\'');
            }
               if(filterMap.get(ConstantsUtils.BRAND) != null && !"null".equals(String.valueOf(filterMap.get(ConstantsUtils.BRAND))) && StringUtils.isNotBlank(String.valueOf(filterMap.get(ConstantsUtils.BRAND)))){
                 sql.append(" AND bm.BRAND_NAME LIKE '").append(String.valueOf(filterMap.get(ConstantsUtils.BRAND))).append('\'');
            }
            if (column!=null) {
                sql.append(" ORDER BY ").append(column).append(' ').append(orderBy).append(" OFFSET ").append(start).append(" ROWS FETCH NEXT ").append(end).append(" ROWS ONLY");
            }
            
		
            return HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
        } catch (Exception e) {
           LOGGER.error("",e);
           LOGGER.error(sql.toString());
            return Collections.emptyList();
        } 
    }
    
}
