package com.stpl.app.global.company.util;

import com.stpl.app.util.ConstantsUtils;
import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * It hold all field name constants for company.
 */
public final class FiledNameUtils {

    /** The Constant ORGANIZATION_KEY. */
    public static final String ORGANIZATION_KEY = "organizationKey";
    
    /** The Constant COMPANY_ID. */
    public static final String COMPANY_ID = ConstantsUtils.COMPANY_ID;
    
    /** The Constant COMPANY_NO. */
    public static final String COMPANY_NO = ConstantsUtils.COMPANY_NO;
    
    /** The Constant COMPANY_NAME. */
    public static final String COMPANY_NAME = ConstantsUtils.COMPANY_NAME;
    
    /** The Constant TRADE_CLASS. */
    public static final String TRADE_CLASS = ConstantsUtils.TRADE_CLASS;
    
    /** The Constant TRADE_CLASS_START_DATE. */
    public static final String TRADE_CLASS_START_DATE = "tradeClassStartDate";
    
    /** The Constant TRADE_CLASS_END_DATE. */
    public static final String TRADE_CLASS_END_DATE = "tradeClassEndDate";
    
    /** The Constant COMPANY_TYPE. */
    public static final String COMPANY_TYPE = ConstantsUtils.COMPANY_TYPE;
    
    /** The Constant COMPANY_STATUS. */
    public static final String COMPANY_STATUS = ConstantsUtils.COMPANY_STATUS;
    
    /** The Constant LIVES. */
    public static final String LIVES = "lives";
    
    /** The Constant COMPANY_END_DATE. */
    public static final String COMPANY_END_DATE = ConstantsUtils.COMPANY_END_DATE;
    
    /** The Constant UDC1. */
    public static final String UDC1 = "udc1";
    
    /** The Constant UDC2. */
    public static final String UDC2 = "udc2";
    
    /** The Constant UDC3. */
    public static final String UDC3 = "udc3";
    
    /** The Constant UDC4. */
    public static final String UDC4 = "udc4";
    
    /** The Constant UDC5. */
    public static final String UDC5 = "udc5";
    
    /** The Constant UDC6. */
    public static final String UDC6 = "udc6";
    
    /** The Constant COMPANY_GROUP. */
    public static final String COMPANY_GROUP = "companyGroup";
    
    /** The Constant FINANCIAL_SYSTEM. */
    public static final String FINANCIAL_SYSTEM = "financialSystem";
    
    /** The Constant ADDRESS1. */
    public static final String ADDRESS1 = "address1";
    
    /** The Constant ADDRESS2. */
    public static final String ADDRESS2 = "address2";
    
    /** The Constant CITY. */
    public static final String CITY = "city";
    
    /** The Constant STATE. */
    public static final String STATE = "state";
    
    /** The Constant ZIP_CODE. */
    public static final String ZIP_CODE = "zipCode";
    
    /** The Constant COUNTRY. */
    public static final String COUNTRY = "country";
    
    /** The Constant REGION_CODE. */
    public static final String REGION_CODE = "regionCode";
    
    /** The Constant PARENT_COMPANY_NO. */
    public static final String PARENT_COMPANY_NO = "parentCompanyNo";
    
    /** The Constant PARENT_START_DATE. */
    public static final String PARENT_START_DATE = "parentStartDate";
    
    /** The Constant PARENT_END_DATE. */
    public static final String PARENT_END_DATE = "parentEndDate";
    
    /** The Constant LAST_UPDATED_DATE. */
    public static final String LAST_UPDATED_DATE = "lastUpdatedDate";
    
    /** The Constant COMPANY_START_DATE. */
    public static final String COMPANY_START_DATE = ConstantsUtils.COMPANY_START_DATE;
    
    /** The Constant PRIOR_PARENT_COMPANY_NO. */
    public static final String PRIOR_PARENT_COMPANY_NO = "priorParentCompanyNo";
    
    /** The Constant PRIOR_PARENT_START_DATE. */
    public static final String PRIOR_PARENT_START_DATE = "priorParentStartDate";
    
    /** The Constant COMPANY_CATEGORY. */
    public static final String COMPANY_CATEGORY = "companyCategory";

    /** The Constant COMPANY_IDENTIFIER. */
    public static final String COMPANY_IDENTIFIER = "companyIdentifier";
    
    /** The Constant IDENTIFIER_STATUS. */
    public static final String IDENTIFIER_STATUS = "identifierStatus";
    
    /** The Constant COMPANY_CRT_QUALIFIER_NAME. */
    public static final String COMPANY_CRT_QUALIFIER_NAME = "companyCrtQualifierName";
    
    /** The Constant START_DATE. */
    public static final String START_DATE = "startDate";
    
    /** The Constant END_DATE. */
    public static final String END_DATE = "endDate";
    
    /** The Constant ENTITY_CODE. */
    public static final String ENTITY_CODE = "entityCode";
    
    /** The Constant EDIT_LIST_COMPANY_CRT_QUALIFIER_NAME. */
    public static final String EDIT_LIST_COMPANY_CRT_QUALIFIER_NAME = "editListCompanyQualifierName";
    
    /** The Constant EDIT_LIST_COMPANY_CRT_QUALIFIER. */
    public static final String EDIT_LIST_COMPANY_CRT_QUALIFIER = "editListCompanyQualifier";
    
    /** The Constant EDIT_LIST_COMPANY_CRT_QUALIFIER. */
    public static final String COMPANY_CRT_QUALIFIER_NAME_DDLB = "companyCrtQualifierNameDDLB";
    
    static HashMap<String, String> companyColumnName=new HashMap<String, String>();
    
    /** The view. */
    public static final String VIEW = "View";
    
    /** The Constant source. */
    public static final String SOURCE = "source";
    
     /** The Constant system id. */
    public static final String SYSTEM_ID = "companyMasterSid";
    
    public static final String ATTACH_IDENTIFIER = "attachIdentifier";
        
    public static final String REMOVE_IDENTIFIER = "removeIdentifier";
        
    public static final String ATTACH_TRADECLASS = "attachTradeClass";
        
    public static final String REMOVE_TRADECLASS = "removeTradeClass";
    
    public static final String ATTACH_PARENT = "attachParentCompany";
       
    public static final String REMOVE_PARENT = "removeParentCompany";
    
    public static String getDBColumnName(String visibleColumnName) {
         return companyColumnName.get(visibleColumnName);  
       
    } 
                    
   public static HashMap<String, String> loadDbColumnName() {       
       companyColumnName.put(ConstantsUtils.COMPANY_ID, "COMPANY_ID");
       companyColumnName.put(ConstantsUtils.COMPANY_NO, "COMPANY_NO");
       companyColumnName.put(ConstantsUtils.COMPANY_NAME, "COMPANY_NAME");
       companyColumnName.put(ConstantsUtils.COMPANY_STATUS, "cm.COMPANY_STATUS");
       companyColumnName.put(ConstantsUtils.COMPANY_TYPE, "COMPANY_TYPE");
       companyColumnName.put(ConstantsUtils.COMPANY_START_DATE, "COMPANY_START_DATE");
       companyColumnName.put(ConstantsUtils.COMP_START_DATE, "COMPANY_START_DATE");
       companyColumnName.put(ConstantsUtils.COMPANY_END_DATE, "COMPANY_END_DATE");
       companyColumnName.put(ConstantsUtils.COMP_END_DATE, "COMPANY_END_DATE");
       companyColumnName.put(ConstantsUtils.TRADE_CLASS, "TRADE_CLASS");
       companyColumnName.put("tradeClassStartDate", "TRADE_CLASS_START_DATE");
       companyColumnName.put("tradeStartDate", "TRADE_CLASS_START_DATE");
       companyColumnName.put("tradeClassEndDate", "TRADE_CLASS_END_DATE");
       companyColumnName.put("tradeEndDate", "TRADE_CLASS_END_DATE"); 
       companyColumnName.put("lives", "LIVES");
       companyColumnName.put("companyGroup", "COMPANY_GROUP");
       companyColumnName.put("companyCategory", "COMPANY_CATEGORY");
       companyColumnName.put("organizationKey", "ORGANIZATION_KEY");
       companyColumnName.put("financialSystem", "FINANCIAL_SYSTEM");
       companyColumnName.put("parentCompanyNo", "comp");
       companyColumnName.put("parentStartDate", "PARENT_START_DATE");
       companyColumnName.put("parentSDate", "PARENT_START_DATE");
       companyColumnName.put("parentEndDate", "PARENT_END_DATE");
       companyColumnName.put("parentEDate", "PARENT_END_DATE");
       companyColumnName.put("priorParentCompanyNo", "");
       companyColumnName.put("priorParentStartDate", "");
       companyColumnName.put("regionCode", "REGION_CODE");
       companyColumnName.put("udc1", "UDC1");
       companyColumnName.put("udc2", "UDC2");
       companyColumnName.put("udc3", "UDC3");
       companyColumnName.put("udc4", "UDC4");
       companyColumnName.put("udc5", "UDC5");
       companyColumnName.put("udc6", "UDC6");
       companyColumnName.put("address1", "ADDRESS1");
       companyColumnName.put("address2", "ADDRESS2");
       companyColumnName.put("zipCode", "ZIP_CODE");
       companyColumnName.put("city", "CITY");
       companyColumnName.put("state", "STATE");
       companyColumnName.put("country", "COUNTRY");
       companyColumnName.put("identifier", "COMPANY_IDENTIFIER_VALUE");
       companyColumnName.put("companyQualifierSid", "COMPANY_QUALIFIER_SID");                           
       companyColumnName.put("systemId", "COMPANY_MASTER_SID"); 
       return companyColumnName;
   }        
    
    
    /**
     * Constructor
     */
    private FiledNameUtils(){
    	//Empty
    }
}
