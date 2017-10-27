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
    
    public static final String CREATED_DATE = "createdDate";
    
    public static final String CREATED_BY = "createdBy";
    
    public static final String MODIFIED_BY = "modifiedBy";
    
    public static final String PARENT_COMPANY_NUMBER = "parentCompanyNo";
    
    public static final String CREATED_BY_LABEL = "Created By";
    
    public static final String COMPANY_IDENTIFIER = "companyIdentifier";
    
    public static final String MODIFIED_DATE = "modifiedDate";
    
    public static final String MODIFIED_DATE_LABEL = "Modified Date";
    
    public static final String COMPANY_CRT_QUALIFIER_NAME = "companyCrtQualifierName";
    
    public static final String CREATED_DATE_LABEL = "Created Date";

    /**
     * The Constant QUALIFIER_COMPANY.
     */
    public final Object[] qualifierCompany = new Object[]{"companyQualifier", COMPANY_CRT_QUALIFIER_NAME, "effectiveDates", "notes", MODIFIED_BY, MODIFIED_DATE, CREATED_BY, CREATED_DATE};
    public final String MODIFIED_BY_LABEL = "Modified By";

    /**
     * The Constant QUALIFIER_COMPANY_HEADERS.
     */
    public final String[] qualifierCompanyHeaders = new String[]{"Identifier Code Qualifier", "Identifier Code Qualifier Name", "Effective Dates", "Notes", MODIFIED_BY_LABEL, MODIFIED_DATE_LABEL, CREATED_BY_LABEL, CREATED_DATE_LABEL};

    /**
     * The Constant PARENT_COMPANY_NO_COLUMNS.
     */
    public final Object[] parentCompanyNoColumns = new Object[]{ConstantsUtils.COMPANY_ID, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,
        ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE};

    /**
     * The Constant PARENT_COMPANY_NO_HEADERS.
     */
    public final String[] parentCompanyNoHeaders = new String[]{ConstantsUtils.COMPANYID,
        ConstantsUtils.COMPANYNO, ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE};

    public final Object[] parentCompanyNoColumnItem = new Object[]{ConstantsUtils.COMPANY_ID, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,
        ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE};

    /**
     * The Constant PARENT_COMPANY_NO_HEADERS.
     */
    public final String[] parentCompanyNoHeadersItem = new String[]{ConstantsUtils.COMPANYID,
        ConstantsUtils.COMPANYNO, ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE};

    /**
     * The Constant WITHOUT_IDEN.
     */
    public final Object[] withoutIden = new Object[]{"systemId", ConstantsUtils.COMPANY_ID, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,
        ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE, "compStartDate", "compEndDate", ConstantsUtils.TRADE_CLASS,
        "tradeStartDate", "tradeEndDate", "lives", "companyGroup", "companyCategory",
        "organizationKey", "financialSystem", PARENT_COMPANY_NUMBER, "parentSDate", "parentEDate",
        "priorParentCompanyNo", "priorParentSDate", "regionCode", "udc1", "udc2", "udc3", "udc4",
        "udc5", "udc6", "address1", "address2", "zipCode", "city", "state", "country"};

    /**
     * The Constant WITHOUT_IDEN_HEADER.
     */
    public final String[] withoutIdenHeaders = new String[]{"System ID", ConstantsUtils.COMPANYID,
        "Company No", ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, "Company Start Date", "Company End Date", ConstantsUtils.TRAD_CLASS, ConstantsUtils.TC_SD, LabelUtils.TRADE_CLASS_END_DATE, "Lives", "Company Group", "Company Category",
        "Organization ", "Financial System", "Parent Company No.", "Parent Start Date", "Parent End Date",
        "Prior Parent Company No.", "Prior Parent Start Date", "Region Code", "UDC 1", "UDC 2", "UDC 3", "UDC 4",
        "UDC 5", "UDC 6", "Address 1", "Address 2", "Zip Code", "City", "State", "Country"};

    /**
     * The Constant WITH_IDEN.
     */
    public final Object[] withIden = new Object[]{"systemId", ConstantsUtils.COMPANY_ID,
        ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME, ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE,
        "companyCategory", "companyGroup", ConstantsUtils.TRADE_CLASS, "identifierTypeDesc", "identifier",
        "compStartDate", "compEndDate", "tradeStartDate", "tradeEndDate", "lives",
        "organizationKey", "financialSystem", PARENT_COMPANY_NUMBER, "parentSDate", "parentEDate",
        "priorParentCompanyNo", "priorParentSDate", "regionCode", "udc1", "udc2", "udc3", "udc4",
        "udc5", "udc6", "address1", "address2", "zipCode", "city", "state", "country"};

    /**
     * The Constant WITH_IDEN_HEADER.
     */
    public final String[] withIdenHeader = new String[]{"System ID", ConstantsUtils.COMPANYID,
        "Company No", ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE,
        "Company Category", "Company Group", ConstantsUtils.TRAD_CLASS, "Company Qualifier Name", "Company Identifier",
        "Company Start Date", "Company End Date", ConstantsUtils.TC_SD, LabelUtils.TRADE_CLASS_END_DATE, "Lives",
        "Organization ", "Financial System", "Parent Company No.", "Parent Start Date", "Parent End Date",
        "Prior Parent Company No.", "Prior Parent Start Date", "Region Code", "UDC 1", "UDC 2", "UDC 3", "UDC 4",
        "UDC 5", "UDC 6", "Address 1", "Address 2", "Zip Code", "City", "State", "Country"};

    /**
     * The Constant ITEMINFO_COL_ORDER.
     */
    public final Object[] itemInfoColOrder = new Object[]{ConstantsUtils.COMPANY_ID,
        ConstantsUtils.COMPANY_NAME, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_TYPE, ConstantsUtils.COMPANY_STATUS, ConstantsUtils.TRADE_CLASS, ConstantsUtils.COMPANY_START_DATE, ConstantsUtils.COMPANY_END_DATE};

    /**
     * The Constant ADDINFO_COL_ORDER.
     */
    public final Object[] addInfoColOrder = new Object[]{ConstantsUtils.COMPANY_ID,
        ConstantsUtils.COMPANY_NAME, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_TYPE, ConstantsUtils.COMPANY_STATUS, ConstantsUtils.TRADE_CLASS, ConstantsUtils.COMPANY_START_DATE, ConstantsUtils.COMPANY_END_DATE};

    /**
     * The Constant FORM_COL_ORDER.
     */
    public final Object[] formColOrder = new Object[]{ConstantsUtils.COMPANY_ID,
        ConstantsUtils.COMPANY_NAME, ConstantsUtils.COMPANY_TYPE, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_STATUS, ConstantsUtils.TRADE_CLASS, "identifier", "identifierTypeDesc"};

    /**
     * "Human readable" captions for properties in same order as in
     * NATURAL_COL_ORDER.
     */
    public final String[] colHeadersEnglish = new String[]{
        ConstantsUtils.COMPANYID, ConstantsUtils.COMPANYNAME, "Company No.",
        ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, ConstantsUtils.TRADE_CLASS, "Identifier Type"};

    /**
     * The Constant IDEN_FORM_COL_ORDER.
     */
    public final Object[] idenFormColOrder = new Object[]{
        COMPANY_CRT_QUALIFIER_NAME, COMPANY_IDENTIFIER, "identifierStatus", "startDate", "endDate", CREATED_DATE, CREATED_BY, MODIFIED_BY, MODIFIED_DATE
    };
    public final Object[] itemFormColOrder = new Object[]{
        "itemIrtQualifierName", COMPANY_IDENTIFIER, "identifierStatus", "startDate", "endDate", CREATED_DATE, CREATED_BY, MODIFIED_BY, MODIFIED_DATE
    };

    /**
     * The Constant IDEN_FORM_COL_HEADER.
     */
    public final String[] idenFormHeader = new String[]{
        "Company Qualifier Name", "Company Identifier", "Identifier Status", "Start Date", "End Date", CREATED_DATE_LABEL, CREATED_BY_LABEL, MODIFIED_BY_LABEL, MODIFIED_DATE_LABEL};

    /**
     * The Constant IDEN_VIEW_FORM_COL_ORDER.
     */
    public final Object[] idenViewFormHeader = new Object[]{
        COMPANY_CRT_QUALIFIER_NAME, COMPANY_IDENTIFIER, "identifierStatusValue", "viewStartDate", "viewEndDate", "viewCreatedDate", CREATED_BY, MODIFIED_BY, "viewModifiedDate"
    };

    /**
     * The Constant TRADE_CLASS_COLUMNS.
     */
    public final Object[] tradeClassColumns = new Object[]{
        "tradeClass", "tradeClassSDate", "tradeClassEDate", CREATED_DATE, CREATED_BY, MODIFIED_BY, MODIFIED_DATE
    };

    /**
     * The Constant TRADE_CLASS_HEADERS.
     */
    public final String[] tradeClassHeaders = new String[]{
        ConstantsUtils.TRAD_CLASS, ConstantsUtils.TC_SD, LabelUtils.TRADE_CLASS_END_DATE, CREATED_DATE_LABEL, CREATED_BY_LABEL, MODIFIED_BY_LABEL, MODIFIED_DATE_LABEL
    };

    /**
     * The Constant PARENT_COMPANY_COLUMNS.
     */
    public final Object[] parentCompanyColumns = new Object[]{
        PARENT_COMPANY_NUMBER, "parentCompanyName", "parentStartDate", "parentEndDate", CREATED_DATE, CREATED_BY, MODIFIED_BY, MODIFIED_DATE
    };

    /**
     * The Constant PARENT_COMPANY_HEADERS.
     */
    public final String[] parentCompanyHeaders = new String[]{
        "Parent Company No", "Parent Company Name", "Parent Company Start Date", "Parent Company End Date", CREATED_DATE_LABEL, CREATED_BY_LABEL, MODIFIED_BY_LABEL, MODIFIED_DATE_LABEL};

    /**
     * The edit list.
     */
    public final String EDIT_LIST = "Edit List";
    /**
     * Instantiates a new UI utils.
     */

    public final String[] FINANCIAL_CLOSE_HEADERS = new String[]{"Mode", "Status", "Calendar", "Month", "Year", "Date/Time", CREATED_DATE_LABEL, CREATED_BY_LABEL};
    public final Object[] FINANCIAL_CLOSE_COLUMNS = new Object[]{"modeDdlb", "statusDdlb", "calendar", "month", "year", "dateTime", CREATED_DATE, "createdByDdlb"};
    private UIUtils() {
        //Empty
    }

    private static UIUtils object;

    public static UIUtils getInstance() {
        if (object == null) {
            object = new UIUtils();
        }
        return object;
    }
}
