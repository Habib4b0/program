/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import java.util.HashMap;

/**
 *
 * @author pvinoth
 */
public class FieldNameUtil {
    static HashMap<String, String> companyColumnName=new HashMap<String, String>();
    
    public static String getDBColumnName(String visibleColumnName) {
         return companyColumnName.get(visibleColumnName);  
       
    } 
                    
   public static HashMap<String, String> loadDbColumnName() {       
       companyColumnName.put("companyId", "COMPANY_ID");
       companyColumnName.put("companyNo", "COMPANY_NO");
       companyColumnName.put("companyName", "COMPANY_NAME");
       companyColumnName.put("companyType", "companyType");
       companyColumnName.put("companyCategory", "companyCategory");
       companyColumnName.put("tradeClass", "tradeClass");
       companyColumnName.put("address1", "ADDRESS1");
       companyColumnName.put("address2", "ADDRESS2");
       companyColumnName.put("zipCode", "ZIP_CODE");
       companyColumnName.put("city", "CITY");
       companyColumnName.put("state", "STATE");
       companyColumnName.put("country", "COUNTRY");
       companyColumnName.put("identifier", "COMPANY_IDENTIFIER_VALUE");
       companyColumnName.put("companyQualifierSid", "COMPANY_QUALIFIER_SID");                           
       companyColumnName.put("companySystemId", "COMPANY_MASTER_SID"); 
       return companyColumnName;
   }        
}
