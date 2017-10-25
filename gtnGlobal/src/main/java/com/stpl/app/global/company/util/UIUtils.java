package com.stpl.app.global.company.util;

import com.stpl.app.util.ConstantsUtils;

// TODO: Auto-generated Javadoc
/**
 * I holds the String constants for company UI.
 */
public final class UIUtils {
	
	/** The Constant COMPANY_STATUS. */
    public static final String STATUS = "STATUS";
    
    /** The Constant COMPANY_TYPE. */
    public static final String COMPANY_TYPE = "COMPANY_TYPE";

    /** The Constant CFP_TYPE. */
    public static final String CFP_TYPE = "CfpType";
    
    /** The Constant TRADE_CLASS. */
    public static final String TRADE_CLASS = "COMPANY_TRADE_CLASS";

    /** The Constant COMPANY_GROUP. */
    public static final String COMPANY_GROUP = "COMPANY_GROUP";

    /** The Constant COMPANY_CATEGORY. */
    public static final String COMPANY_CATEGORY = "COMPANY_CATEGORY";

    /** The Constant STATE. */
    public static final String STATE = "STATE";
    
    /** The Constant COUNTRY. */
    public static final String COUNTRY = "COUNTRY";
    
    /** The Constant ORGANIZATION_KEY. */
    public static final String ORGANIZATION_KEY = "ORGANIZATION_KEY";

    /** The Constant UDC1. */
    public static final String UDC1 = "COMP_UDC1";
    
    /** The Constant UDC2. */
    public static final String UDC2 = "COMP_UDC2";
    
    /** The Constant UDC3. */
    public static final String UDC3 = "COMP_UDC3";
    
    /** The Constant UDC4. */
    public static final String UDC4 = "COMP_UDC4";
    
    /** The Constant UDC5. */
    public static final String UDC5 = "COMP_UDC5";
    
    /** The Constant UDC6. */
    public static final String UDC6 = "COMP_UDC6";
    
    /** The Constant IDENTIFIER_STATUS. */
    public static final String IDENTIFIER_STATUS = "COMP_IDEN_STATUS";
    
    /** The Constant MANDATORY_DATE_PICKER. */
    public static final String MANDATORY_DATE_PICKER = "mandatory-date-picker";

    /** The Constant QUALIFIER_COMPANY. */
    public static final Object[] QUALIFIER_COMPANY = new Object[]{"companyQualifier", "companyCrtQualifierName","effectiveDates","notes","modifiedBy","modifiedDate","createdBy","createdDate"};
    
    /** The Constant QUALIFIER_COMPANY_HEADERS. */
    public static final String[] QUALIFIER_COMPANY_HEADERS = new String[]{"Identifier Code Qualifier", "Identifier Code Qualifier Name","Effective Dates","Notes","Modified By","Modified Date","Created By","Created Date"};
    
    /** The Constant PARENT_COMPANY_NO_COLUMNS. */
    public static final Object[] PARENT_COMPANY_NO_COLUMNS = new Object[]{ConstantsUtils.COMPANY_ID, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,
        ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE};

    /** The Constant PARENT_COMPANY_NO_HEADERS. */
    public static final String[] PARENT_COMPANY_NO_HEADERS = new String[]{ConstantsUtils.COMPANYID,
        ConstantsUtils.COMPANYNO, ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE};
    
    
    
     public static final Object[] PARENT_COMPANY_NO_COLUMNS_ITEM = new Object[]{ConstantsUtils.COMPANY_ID, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,
        ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE };

    /**
     * The Constant PARENT_COMPANY_NO_HEADERS.
     */
    public static final String[] PARENT_COMPANY_NO_HEADERS_ITEM = new String[]{ConstantsUtils.COMPANYID,
        ConstantsUtils.COMPANYNO, ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE};
   
    
    /** The Constant WITHOUT_IDEN. */
    public static final Object[] WITHOUT_IDEN = new Object[]{"systemId",ConstantsUtils.COMPANY_ID, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,
        ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE, "compStartDate", "compEndDate", ConstantsUtils.TRADE_CLASS,
        "tradeStartDate", "tradeEndDate", "lives", "companyGroup", "companyCategory",
        "organizationKey", "financialSystem", "parentCompanyNo", "parentSDate", "parentEDate",
        "priorParentCompanyNo", "priorParentSDate", "regionCode", "udc1", "udc2", "udc3", "udc4",
        "udc5", "udc6", "address1", "address2", "zipCode", "city", "state", "country"};

    /** The Constant WITHOUT_IDEN_HEADER. */
    public static final String[] WITHOUT_IDEN_HEADER = new String[]{"System ID",ConstantsUtils.COMPANYID,
        "Company No", ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, "Company Start Date", "Company End Date", ConstantsUtils.TRAD_CLASS, ConstantsUtils.TC_SD, LabelUtils.TRADE_CLASS_END_DATE, "Lives", "Company Group", "Company Category",
        "Organization ", "Financial System", "Parent Company No.", "Parent Start Date", "Parent End Date",
        "Prior Parent Company No.", "Prior Parent Start Date", "Region Code", "UDC 1", "UDC 2", "UDC 3", "UDC 4",
        "UDC 5", "UDC 6", "Address 1", "Address 2", "Zip Code", "City", "State", "Country"};
    
    /** The Constant WITH_IDEN. */
    public static final Object[] WITH_IDEN = new Object[]{"systemId",ConstantsUtils.COMPANY_ID,
        ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME, ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE, 
        "companyCategory", "companyGroup", ConstantsUtils.TRADE_CLASS ,"identifierTypeDesc", "identifier",
        "compStartDate", "compEndDate", "tradeStartDate", "tradeEndDate", "lives", 
        "organizationKey", "financialSystem", "parentCompanyNo", "parentSDate", "parentEDate",
        "priorParentCompanyNo", "priorParentSDate", "regionCode", "udc1", "udc2", "udc3", "udc4",
         "udc5", "udc6", "address1", "address2", "zipCode", "city", "state", "country"};

    /** The Constant WITH_IDEN_HEADER. */
    public static final String[] WITH_IDEN_HEADER = new String[]{"System ID",ConstantsUtils.COMPANYID,
        "Company No", ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, 
        "Company Category", "Company Group", ConstantsUtils.TRAD_CLASS, "Company Qualifier Name", "Company Identifier",
         "Company Start Date", "Company End Date",  ConstantsUtils.TC_SD, LabelUtils.TRADE_CLASS_END_DATE, "Lives", 
         "Organization ", "Financial System", "Parent Company No.", "Parent Start Date", "Parent End Date",
         "Prior Parent Company No.", "Prior Parent Start Date", "Region Code", "UDC 1", "UDC 2", "UDC 3", "UDC 4",
         "UDC 5", "UDC 6", "Address 1", "Address 2", "Zip Code", "City", "State", "Country"};

    /** The Constant ITEMINFO_COL_ORDER. */
    public static final Object[] ITEMINFO_COL_ORDER = new Object[]{ConstantsUtils.COMPANY_ID,
        ConstantsUtils.COMPANY_NAME, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_TYPE, ConstantsUtils.COMPANY_STATUS, ConstantsUtils.TRADE_CLASS, ConstantsUtils.COMPANY_START_DATE, ConstantsUtils.COMPANY_END_DATE};

    /** The Constant ADDINFO_COL_ORDER. */
    public static final Object[] ADDINFO_COL_ORDER = new Object[]{ConstantsUtils.COMPANY_ID,
        ConstantsUtils.COMPANY_NAME, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_TYPE, ConstantsUtils.COMPANY_STATUS, ConstantsUtils.TRADE_CLASS, ConstantsUtils.COMPANY_START_DATE, ConstantsUtils.COMPANY_END_DATE};

    /** The Constant FORM_COL_ORDER. */
    public static final Object[] FORM_COL_ORDER = new Object[]{ConstantsUtils.COMPANY_ID,
        ConstantsUtils.COMPANY_NAME, ConstantsUtils.COMPANY_TYPE, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_STATUS, ConstantsUtils.TRADE_CLASS, "identifier", "identifierTypeDesc"};

    /**
     * "Human readable" captions for properties in same order as in
     * NATURAL_COL_ORDER.
     */
    public static final String[] COL_HEADERS_ENGLISH = new String[]{
        ConstantsUtils.COMPANYID, ConstantsUtils.COMPANYNAME, "Company No.",
        ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, ConstantsUtils.TRADE_CLASS, "Identifier Type"};
    
    /** The Constant IDEN_FORM_COL_ORDER. */
    public static final Object[] IDEN_FORM_COL_ORDER = new Object[]{
        "companyCrtQualifierName", "companyIdentifier", "identifierStatus", "startDate", "endDate", "createdDate", "createdBy", "modifiedBy", "modifiedDate"
        };
    public static final Object[] ITEM_FORM_COL_ORDER = new Object[]{
        "itemIrtQualifierName", "companyIdentifier", "identifierStatus", "startDate", "endDate", "createdDate", "createdBy", "modifiedBy", "modifiedDate"
        };

    /** The Constant IDEN_FORM_COL_HEADER. */
    public static final String[] IDEN_FORM_COL_HEADER = new String[]{
        "Company Qualifier Name", "Company Identifier", "Identifier Status", "Start Date", "End Date", "Created Date", "Created By", "Modified By", "Modified Date" };

    /** The Constant IDEN_VIEW_FORM_COL_ORDER. */
    public static final Object[] IDEN_VIEW_FORM_COL_ORDER = new Object[]{
        "companyCrtQualifierName", "companyIdentifier", "identifierStatusValue", "viewStartDate", "viewEndDate", "viewCreatedDate", "createdBy", "modifiedBy", "viewModifiedDate"
        };



    /** The Constant TRADE_CLASS_COLUMNS. */
    public static final Object[] TRADE_CLASS_COLUMNS = new Object[]{
       "tradeClass", "tradeClassSDate", "tradeClassEDate","createdDate","createdBy", "modifiedBy", "modifiedDate"
    };

    /** The Constant TRADE_CLASS_HEADERS. */
    public static final String[] TRADE_CLASS_HEADERS = new String[]{
       ConstantsUtils.TRAD_CLASS, ConstantsUtils.TC_SD, LabelUtils.TRADE_CLASS_END_DATE,"Created Date","Created By", "Modified By", "Modified Date"
    };
    
   
    /** The Constant PARENT_COMPANY_COLUMNS. */
    public static final Object[] PARENT_COMPANY_COLUMNS= new Object[]{
		"parentCompanyNo", "parentCompanyName", "parentStartDate","parentEndDate", "createdDate","createdBy", "modifiedBy", "modifiedDate"
	};
	
	/** The Constant PARENT_COMPANY_HEADERS. */
	public static final String[] PARENT_COMPANY_HEADERS= new String[]{
		"Parent Company No", "Parent Company Name", "Parent Company Start Date","Parent Company End Date","Created Date","Created By", "Modified By", "Modified Date"	}; 
   
        
    /** The edit list. */
    public static final String EDIT_LIST = "Edit List";
   /**
    * Instantiates a new UI utils.
    */
    
    public static final String[] FINANCIAL_CLOSE_HEADERS = new String[]{"Mode", "Status","Calendar", "Month","Year", "Date/Time", "Created Date", "Created By"};
    public static final Object[] FINANCIAL_CLOSE_COLUMNS = new Object[]{"modeDdlb","statusDdlb", "calendar", "month","year", "dateTime", "createdDate", "createdByDdlb"};
    private UIUtils() {
	   //Empty
   }
}
