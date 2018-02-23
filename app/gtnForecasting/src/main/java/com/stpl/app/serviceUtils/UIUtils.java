package com.stpl.app.serviceUtils;


// TODO: Auto-generated Javadoc
/**
 * I holds the String constants for company UI.
 */
public final class UIUtils {
	
	/** The Constant COMPANY_STATUS. */
    public final static String STATUS = "STATUS";
    
    /** The Constant COMPANY_TYPE. */
    public final static String COMPANY_TYPE = "COMP_TYPE";
    
    /** The Constant CFP_TYPE. */
    public final static String CFP_TYPE = "CfpType";
    
    /** The Constant TRADE_CLASS. */
    public final static String TRADE_CLASS = "COMP_TRADECLASS";
    
    /** The Constant COMPANY_GROUP. */
    public final static String COMPANY_GROUP = "COMP_GROUP";
    
    /** The Constant COMPANY_CATEGORY. */
    public final static String COMPANY_CATEGORY = "COMP_CATEGORY";
    
    /** The Constant STATE. */
    public final static String STATE = "STATE";
    
    /** The Constant COUNTRY. */
    public final static String COUNTRY = "COUNTRY";
    
    /** The Constant ORGANIZATION_KEY. */
    public final static String ORGANIZATION_KEY = "COMP_ORG_KEY";
    
    /** The Constant UDC1. */
    public final static String UDC1 = "COMP_UDC1";
    
    /** The Constant UDC2. */
    public final static String UDC2 = "COMP_UDC2";
    
    /** The Constant UDC3. */
    public final static String UDC3 = "COMP_UDC3";
    
    /** The Constant UDC4. */
    public final static String UDC4 = "COMP_UDC4";
    
    /** The Constant UDC5. */
    public final static String UDC5 = "COMP_UDC5";
    
    /** The Constant UDC6. */
    public final static String UDC6 = "COMP_UDC6";
    
    /** The Constant IDENTIFIER_STATUS. */
    public final static String IDENTIFIER_STATUS = "COMP_IDEN_STATUS";
    
    /** The Constant MANDATORY_DATE_PICKER. */
    public final static String MANDATORY_DATE_PICKER = "mandatory-date-picker";

    /** The Constant QUALIFIER_COMPANY. */
    private static final Object[] QUALIFIER_COMPANY = new Object[]{"companyQualifier", "companyCrtQualifierName","effectiveDates","notes","modifiedBy","modifiedDate","createdBy","createdDate"};
    
    /** The Constant QUALIFIER_COMPANY_HEADERS. */
    private static final String[] QUALIFIER_COMPANY_HEADERS = new String[]{"Company Qualifier", "Company Qualifier Name","Effective Dates","Notes","Modified By","Modified Date","Created By","Created Date"};
    
    /** The Constant PARENT_COMPANY_NO_COLUMNS. */
    private static final Object[] PARENT_COMPANY_NO_COLUMNS = new Object[]{ConstantsUtils.COMPANY_ID, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,
        ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE};

    /** The Constant PARENT_COMPANY_NO_HEADERS. */
    private static final String[] PARENT_COMPANY_NO_HEADERS = new String[]{ConstantsUtils.COMPANYID,
        ConstantsUtils.COMPANYNO, ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE};
    
    
    /**
     * The Constant PARENT_COMPANY_NO_COLUMNS.
     */
    private static final Object[] PARENT_COMPANY_NO_COLUMNS_ITEM = new Object[]{"systemId",ConstantsUtils.COMPANY_ID, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,
        ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE, ConstantsUtils.TRADE_CLASS,"address1","address2","city" };

    /**
     * The Constant PARENT_COMPANY_NO_HEADERS.
     */
    private static final String[] PARENT_COMPANY_NO_HEADERS_ITEM = new String[]{"System ID",ConstantsUtils.COMPANYID,
        ConstantsUtils.COMPANYNO, ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, "Trade Class","Address1","Address2","City"};
    
     /**
     * The Constant PARENT_COMPANY_NO_COLUMNS.
     */
    private static final Object[] PARENT_COMPANY_NO_COLUMNS_ITEM_WITH = new Object[]{"systemId",ConstantsUtils.COMPANY_ID, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,
        ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE, ConstantsUtils.TRADE_CLASS, "identifier", "identifierType","address1","address2","city"};

    /**
     * The Constant PARENT_COMPANY_NO_HEADERS.
     */
    private static final String[] PARENT_COMPANY_NO_HEADERS_ITEM_WITH = new String[]{"System ID",ConstantsUtils.COMPANYID,
        ConstantsUtils.COMPANYNO, ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, "Trade Class", "Identifier", "Identifier Type","Address1","Address2","City"};

    /** The Constant WITHOUT_IDEN. */
    private static final Object[] WITHOUT_IDEN = new Object[]{"systemId",ConstantsUtils.COMPANY_ID, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,
        ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE, "compStartDate", "compEndDate", ConstantsUtils.TRADE_CLASS,
        "tradeStartDate", "tradeEndDate", "lives", "companyGroup", "companyCategory",
        "organizationKey", "financialSystem", "parentCompanyNo", "parentSDate", "parentEDate",
        "priorParentCompanyNo", "priorParentSDate", "regionCode", "udc1", "udc2", "udc3", "udc4",
        "udc5", "udc6", "address1", "address2", "zipCode", "city", "state", "country"};

    /** The Constant WITHOUT_IDEN_HEADER. */
    private static final String[] WITHOUT_IDEN_HEADER = new String[]{"System ID",ConstantsUtils.COMPANYID,
        "Company No", ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, "Company Start Date", "Company End Date", "Trade Class", "Trade Class Start Date", "Trade Class End Date", "Lives", "Company Group", "Company Category",
        "Organization ", "Financial System", "Parent Company No.", "Parent Start Date", "Parent End Date",
        "Prior Parent Company No.", "Prior Parent Start Date", "Region Code", "UDC 1", "UDC 2", "UDC 3", "UDC 4",
        "UDC 5", "UDC 6", "Address 1", "Address 2", "Zip Code", "City", "State", "Country"};
    
    /** The Constant WITH_IDEN. */
    private static final Object[] WITH_IDEN = new Object[]{"systemId",ConstantsUtils.COMPANY_ID,
        ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME, ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE, 
        "companyCategory", "companyGroup", ConstantsUtils.TRADE_CLASS ,"identifierTypeDesc", "identifier",
        "compStartDate", "compEndDate", "tradeStartDate", "tradeEndDate", "lives", 
        "organizationKey", "financialSystem", "parentCompanyNo", "parentSDate", "parentEDate",
        "priorParentCompanyNo", "priorParentSDate", "regionCode", "udc1", "udc2", "udc3", "udc4",
         "udc5", "udc6", "address1", "address2", "zipCode", "city", "state", "country"};

    /** The Constant WITH_IDEN_HEADER. */
    private static final String[] WITH_IDEN_HEADER = new String[]{"System ID",ConstantsUtils.COMPANYID,
        "Company No", ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, 
        "Company Category", "Company Group", "Trade Class", "Company Qualifier Name", "Company Identifier",
         "Company Start Date", "Company End Date",  "Trade Class Start Date", "Trade Class End Date", "Lives", 
         "Organization ", "Financial System", "Parent Company No.", "Parent Start Date", "Parent End Date",
         "Prior Parent Company No.", "Prior Parent Start Date", "Region Code", "UDC 1", "UDC 2", "UDC 3", "UDC 4",
         "UDC 5", "UDC 6", "Address 1", "Address 2", "Zip Code", "City", "State", "Country"};

    /** The Constant ITEMINFO_COL_ORDER. */
    private static final Object[] ITEMINFO_COL_ORDER = new Object[]{ConstantsUtils.COMPANY_ID,
        ConstantsUtils.COMPANY_NAME, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_TYPE, ConstantsUtils.COMPANY_STATUS, ConstantsUtils.TRADE_CLASS, ConstantsUtils.COMPANY_START_DATE, ConstantsUtils.COMPANY_END_DATE};

    /** The Constant ADDINFO_COL_ORDER. */
    private static final Object[] ADDINFO_COL_ORDER = new Object[]{ConstantsUtils.COMPANY_ID,
        ConstantsUtils.COMPANY_NAME, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_TYPE, ConstantsUtils.COMPANY_STATUS, ConstantsUtils.TRADE_CLASS, ConstantsUtils.COMPANY_START_DATE, ConstantsUtils.COMPANY_END_DATE};

    /** The Constant FORM_COL_ORDER. */
    private static final Object[] FORM_COL_ORDER = new Object[]{ConstantsUtils.COMPANY_ID,
        ConstantsUtils.COMPANY_NAME, ConstantsUtils.COMPANY_TYPE, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_STATUS, ConstantsUtils.TRADE_CLASS, "identifier", "identifierTypeDesc"};

    /**
     * "Human readable" captions for properties in same order as in
     * NATURAL_COL_ORDER.
     */
    private static final String[] COL_HEADERS_ENGLISH = new String[]{
        ConstantsUtils.COMPANYID, ConstantsUtils.COMPANYNAME, "Company No.",
        ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, ConstantsUtils.TRADE_CLASS, "Identifier Type"};
    
    /** The Constant IDEN_FORM_COL_ORDER. */
    private static final Object[] IDEN_FORM_COL_ORDER = new Object[]{
        "companyCrtQualifierName", "companyIdentifier", "identifierStatus", "startDate", "endDate", "createdDate", "createdBy", "modifiedBy", "modifiedDate"
        };

    /** The Constant IDEN_FORM_COL_HEADER. */
    private static final String[] IDEN_FORM_COL_HEADER = new String[]{
        "Company Qualifier Name", "Company Identifier", "Identifier Status", "Start Date", "End Date", "Created Date", "Created By", "Modified By", "Modified Date" };

    /** The Constant IDEN_VIEW_FORM_COL_ORDER. */
    private static final Object[] IDEN_VIEW_FORM_COL_ORDER = new Object[]{
        "companyCrtQualifierName", "companyIdentifier", "identifierStatusValue", "viewStartDate", "viewEndDate", "viewCreatedDate", "createdBy", "modifiedBy", "viewModifiedDate"
        };



    /** The Constant TRADE_CLASS_COLUMNS. */
    private static final Object[] TRADE_CLASS_COLUMNS = new Object[]{
       "tradeClass", "tradeClassSDate", "tradeClassEDate","createdDate","createdBy", "modifiedBy", "modifiedDate"
    };

    /** The Constant TRADE_CLASS_HEADERS. */
    private static final String[] TRADE_CLASS_HEADERS = new String[]{
       "Trade Class", "Trade Class Start Date", "Trade Class End Date","Created Date","Created By", "Modified By", "Modified Date"
    };
    
   
    /** The Constant PARENT_COMPANY_COLUMNS. */
    private static final Object[] PARENT_COMPANY_COLUMNS= new Object[]{
		"parentCompanyNo", "parentCompanyName", "parentStartDate","parentEndDate", "createdDate","createdBy", "modifiedBy", "modifiedDate"
	};
	
	/** The Constant PARENT_COMPANY_HEADERS. */
	private static final String[] PARENT_COMPANY_HEADERS= new String[]{
		"Parent Company No", "Parent Company Name", "Parent Company Start Date","Parent Company End Date","Created Date","Created By", "Modified By", "Modified Date"	}; 
   
        
    /** The edit list. */
    public final static String EDIT_LIST = "Edit List";
   /**
    * Instantiates a new UI utils.
    */
    private UIUtils()
   {
	   //Empty
   }
}
