/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.impl;

import com.stpl.app.gcm.util.Constants;
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
        Boolean retFlag;
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
            if(filterMap.get("itemStatus") != null && !"null".equals(String.valueOf(filterMap.get("itemStatus"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("itemStatus")))){
                 sql += " AND im.ITEM_STATUS ="+Integer.valueOf(String.valueOf(filterMap.get("itemStatus")));
            }
            if(filterMap.get("strength") != null && !"null".equals(String.valueOf(filterMap.get("strength"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("strength")))){
                 sql += " AND im.STRENGTH ="+Integer.valueOf(String.valueOf(filterMap.get("strength")));
            }
            if(filterMap.get("therapeuticClass") != null && !"null".equals(String.valueOf(filterMap.get("therapeuticClass"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("therapeuticClass")))){
                 sql += " AND im.THERAPEUTIC_CLASS ="+Integer.valueOf(String.valueOf(filterMap.get("therapeuticClass")));
            }
             if(filterMap.get("packageSize") != null && !"null".equals(String.valueOf(filterMap.get("packageSize"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("packageSize")))){
                 sql += " AND im.PACKAGE_SIZE ="+Integer.valueOf(String.valueOf(filterMap.get("packageSize")));
            }
               if(filterMap.get("itemDesc") != null && !"null".equals(String.valueOf(filterMap.get("itemDesc"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("itemDesc")))){
                 sql += " AND im.ITEM_DESC LIKE '"+String.valueOf(filterMap.get("itemDesc"))+"'";
            }
               if(filterMap.get("brand") != null && !"null".equals(String.valueOf(filterMap.get("brand"))) && StringUtils.isNotBlank(String.valueOf(filterMap.get("brand")))){
                 sql += " AND bm.BRAND_NAME LIKE '"+String.valueOf(filterMap.get("brand")+"'");
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
