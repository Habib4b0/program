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
    public static Boolean saveIfpDetailsAttached(final List<Object> input, final Object future){
        boolean retFlag;
        String sql = StringUtils.EMPTY;
        try {
            sql = SQlUtil.getQuery("com.contractDashboard.saveIFP");
            sql=sql.replaceFirst("[?]", input.get(0).toString());
            sql=sql.replaceFirst("[?]", input.get(1).toString());
            sql=sql.replaceFirst("[?]", input.get(2).toString());
            sql=sql.replaceFirst("[?]", input.get(3).toString());
            sql=sql.replaceFirst("[?]", input.get(4).toString());
            sql=sql.replaceFirst("[?]", input.get(5).toString());
            sql=sql.replace("@IFP_START_DATE","'"+input.get(6).toString()+"'");
            sql=sql.replace("@IFP_END_DATE",String.valueOf(input.get(7)).equals("null") ? "NULL" : "'"+input.get(7).toString()+"'");
            sql=sql.replace("@ATTACHDATE",input.get(8).toString());
            HelperTableLocalServiceUtil.executeUpdateQuery(sql);
            retFlag = true;

        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        }
        return retFlag;

    }
    
    public List findIFP(final Object field, final Object value, final List<Integer> future,Map<String, Object> filterMap, int start, int end, String column, String orderBy, Object future1){
        String sql = StringUtils.EMPTY;
        try {
            
            if(orderBy == null) {
                orderBy = "ASC";
            }
            
            if (Constants.BRAND_NAME.equals(field.toString())) {
                sql = SQlUtil.getQuery("findBrand");
                if (!"%".equals(value.toString().trim())) {
                    sql += " and im.BRAND_MASTER_SID in (";
                    Iterator<Integer> list=future.iterator();
                    if (list.hasNext()) {
                        sql +=list.next();
                        for (;list.hasNext();) {
                            sql += ","+list.next();
                        }
                        sql += ")";
                    }else{
                        sql += ")";
                    }
                }
                
            }else{
                if ("IFP_NO".equalsIgnoreCase(field.toString()) || "IFP_NAME".equalsIgnoreCase(field.toString())) {
                    sql += SQlUtil.getQuery("findIFP");
                    sql += " where ifp."+String.valueOf(field)+" like '" + String.valueOf(value) + "' ";
                }else{
                    sql += SQlUtil.getQuery("findItems");
                    if (Constants.STRENGTH_PROPERTY.equalsIgnoreCase(field.toString()) || "THERAPEUTIC_CLASS".equalsIgnoreCase(field.toString())) {
                        sql += " and im."+String.valueOf(field)+" = " + String.valueOf(value) + " ";
                    }else{
                        sql += " and im."+String.valueOf(field)+" like '" + String.valueOf(value) + "' ";
                    }
                }
                
                
            }
            
            if(StringUtils.isNotBlank(String.valueOf(filterMap.get("itemNo")))){
                sql += " AND im.ITEM_NO LIKE '"+String.valueOf(filterMap.get("itemNo"))+"'";
            }
            
            if(StringUtils.isNotBlank(String.valueOf(filterMap.get("itemName")))){
                sql += " AND im.ITEM_NAME LIKE '"+String.valueOf(filterMap.get("itemName"))+"'";
            }
            if(StringUtils.isNotBlank(String.valueOf(filterMap.get("form")))){
                sql += " AND im.FORM ="+Integer.valueOf(String.valueOf(filterMap.get("form")));
            }
            if(filterMap.get(ConstantsUtils.ITEM_STATUS) != null && !"null".equals(String.valueOf(filterMap.get(ConstantsUtils.ITEM_STATUS))) && StringUtils.isNotBlank(String.valueOf(filterMap.get(ConstantsUtils.ITEM_STATUS)))){
                 sql += " AND im.ITEM_STATUS ="+Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.ITEM_STATUS)));
            }
            if(filterMap.get(ConstantsUtils.STRENGTH) != null && !"null".equals(String.valueOf(filterMap.get(ConstantsUtils.STRENGTH))) && StringUtils.isNotBlank(String.valueOf(filterMap.get(ConstantsUtils.STRENGTH)))){
                 sql += " AND im.STRENGTH ="+Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.STRENGTH)));
            }
            if(filterMap.get(ConstantsUtils.THERAPEUTIC_CLASS) != null && !"null".equals(String.valueOf(filterMap.get(ConstantsUtils.THERAPEUTIC_CLASS))) && StringUtils.isNotBlank(String.valueOf(filterMap.get(ConstantsUtils.THERAPEUTIC_CLASS)))){
                 sql += " AND im.THERAPEUTIC_CLASS ="+Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.THERAPEUTIC_CLASS)));
            }
             if(filterMap.get(ConstantsUtils.PACKAGE_SIZE) != null && !"null".equals(String.valueOf(filterMap.get(ConstantsUtils.PACKAGE_SIZE))) && StringUtils.isNotBlank(String.valueOf(filterMap.get(ConstantsUtils.PACKAGE_SIZE)))){
                 sql += " AND im.PACKAGE_SIZE ="+Integer.valueOf(String.valueOf(filterMap.get(ConstantsUtils.PACKAGE_SIZE)));
            }
               if(filterMap.get(ConstantsUtils.ITEM_DESC) != null && !"null".equals(String.valueOf(filterMap.get(ConstantsUtils.ITEM_DESC))) && StringUtils.isNotBlank(String.valueOf(filterMap.get(ConstantsUtils.ITEM_DESC)))){
                 sql += " AND im.ITEM_DESC LIKE '"+String.valueOf(filterMap.get(ConstantsUtils.ITEM_DESC))+"'";
            }
               if(filterMap.get(ConstantsUtils.BRAND) != null && !"null".equals(String.valueOf(filterMap.get(ConstantsUtils.BRAND))) && StringUtils.isNotBlank(String.valueOf(filterMap.get(ConstantsUtils.BRAND)))){
                 sql += " AND bm.BRAND_NAME LIKE '"+String.valueOf(filterMap.get(ConstantsUtils.BRAND)+"'");
            }
            if (column!=null) {
                sql += " ORDER BY "+column+" "+orderBy+" OFFSET "+start+" ROWS FETCH NEXT "+end+" ROWS ONLY";
            }
            
		
            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
           LOGGER.error("",e);
           LOGGER.error(sql);
            return null;
        } 
    }
    
}
