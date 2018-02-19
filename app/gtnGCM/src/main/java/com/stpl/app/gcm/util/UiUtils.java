/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.AbstractOrderedLayout;

/**
 *
 * @author alok.v
 */
public class UiUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(UiUtils.class);
	private static ResourceBundle resourceBundle;
	public static final String COMPANY_CATEGORY = "COMPANY_CATEGORY";
	public static final String COMPANY_TYPE = "COMPANY_TYPE";
	public static final String COMPANY_TRADE_CLASS = "COMPANY_TRADE_CLASS";
	public static final String STATUS = "STATUS";
	public static final String CONTRACT_TYPE = "CONTRACT_TYPE";
	public static final String RS_TYPE = "RS_TYPE";
	public static final String RS_CATEGORY = "RS_CATEGORY";
	public static final String STATE = "STATE";
	public static final String CFP_TYPE = "CFP_TYPE";
	public static final String CFP_CATEGORY = "CFP_CATEGORY";
	public static final String CFP_TRADE_CLASS = "CFP_TRADE_CLASS";
	public static final String IFP_TYPE = "IFP_TYPE";
	public static final String IFP_CATEGORY = "IFP_CATEGORY";
	public static final String IFP_DESIGNATION = "IFP_DESIGNATION";
	public static final String CFP_DESIGNATION = "CFP_DESIGNATION";
	public static final String PS_TYPE = "PS_TYPE";
	public static final String PS_CATEGORY = "PS_CATEGORY";
	public static final String PS_TRADE_CLASS = "PS_TRADE_CLASS";
	public static final String PS_DESIGNATION = "PS_DESIGNATION";
	public static final String RS_TRADE_CLASS = "RS_TRADE_CLASS";
	public static final String RS_DESIGNATION = "RS_DESIGNATION";
	public static final String REBATE_PROGRAM_TYPE = "REBATE_PROGRAM_TYPE";
	public static final String CONTRACT_ALIAS_TYPE = "CONTRACT_ALIAS_TYPE";
	public static final String ITEM_TYPE = "ITEM_TYPE";
	public static final String THERAPEUTIC_CLASS = "THERAPEUTIC_CLASS";
	public static final String ITEM_CATEGORY = "ITEM_CATEGORY";
	public static final String REBATE_PLAN_LEVEL = "REBATE_PLAN_LEVEL";
	public static final String PAYMENT_FREQUENCY = "PAYMENT_FREQUENCY";
	public static final String PAYMENT_METHOD = "PAYMENT_METHOD";
	public static final String RS_CALENDAR = "RS_CALENDAR";
	public static final String FORM = "FORM";
	public static final String STRENGTH = "STRENGTH";
	public static final String REBATE_PLAN_TYPE = "REBATE_PLAN_TYPE";
	public final Object newCompanyDetailsColumns[] = new Object[] { "companyId", "companyName", "companyNo",
			Constants.START_DATE, Constants.END_DATE, "companyStatusValue", ConstantsUtils.TRADE_CLASS,
			Constants.ATTACHED_DATE_PROPERTY };
	public final Object ccCompanyDetailsColumns[] = new Object[] { "tpNo", "companyName", "companyNo",
			"companyStartDate", "companyEndDate", "companyStatus", ConstantsUtils.TRADE_CLASS, Constants.ATTACHED_DATE_PROPERTY };
	public final String newCompanyDetailsHeaders[] = new String[] { "Trading Partner No ", "Trading Partner Name",
			"Trading Partner Contract No", Constants.START_DATE_HEADER, Constants.END_DATE_HEADER,
			Constants.STATUS_FIELD, Constants.TRADECLASS, Constants.ATTACHED_DATE_FIELD };
	public final Object newIfpDetailsColumns[] = new Object[] { Constants.ITEM_NO_PROPERTY,
			Constants.ITEM_NAME_PROPERTY, Constants.BRAND_PROPERTY, Constants.ITEM_STATUS_PROPERTY,
			Constants.START_DATE, Constants.END_DATE, Constants.ATTACHED_DATE_PROPERTY };

	public final Object ccIfpDetailsColumns[] = new Object[] { Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY,
			Constants.BRAND_PROPERTY, Constants.IFP_STATUS_PROPERTY, Constants.IFP_START_DATE, Constants.IFP_END_DATE,
			Constants.ATTACHED_DATE_PROPERTY };
	public final String newIfpDetailsHeaders[] = new String[] { Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND,
			Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER,
			Constants.ATTACHED_DATE_FIELD };
	public final Object ccPsDetailsColumns[] = new Object[] { Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY,
			Constants.BRAND_PROPERTY, Constants.IFP_STATUS_PROPERTY, Constants.IFP_START_DATE, Constants.IFP_END_DATE,
			"priceType", "pricePlanNo", "pricePlanName", "priceProtectionStatus", "ppStartDate",
			"priceProtectionEndDate", "priceProtectionPriceType", "priceToleranceInterval", "priceToleranceFrequency",
			"priceToleranceType", "maxIncrementalChange", "priceTolerance", "reset", "eligibility", "resetType",
			"resetDate", "resetIntervel", "resetFrequency", Constants.ATTACHED_DATE_PROPERTY };
	public final Object newPsDetailsColumns[] = new Object[] { Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY,
			Constants.BRAND_PROPERTY, Constants.ITEM_STATUS_PROPERTY, Constants.START_DATE, Constants.END_DATE,
			"priceType", "pricePlanNo", "pricePlanName", "priceProtectionStatus", "priceProtectionStartDate",
			"priceProtectionEndDate", "priceProtectionPriceType", "priceToleranceInterval", "priceToleranceFrequency",
			"priceToleranceType", "maxIncrementalChange", "priceTolerance", "reset", "eligibility", "resetType",
			"resetDate", "resetIntervel", "resetFrequency", Constants.ATTACHED_DATE_PROPERTY };
	public final Object ccRsDetailsColumns[] = new Object[] { Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY,
			Constants.BRAND_PROPERTY, Constants.IFP_STATUS_PROPERTY, Constants.IFP_START_DATE, Constants.IFP_END_DATE,
			"formulaType", "formulaId", "formulaName", "rebatePlanId", "rebatePlanName", "rebateAmount", "bundleNo",
			Constants.ATTACHED_DATE_PROPERTY };
	public final String newPsDetailsHeaders[] = new String[] { Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND,
			Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, "Price Type",
			"Price Plan No", "Price Plan Name", "Price Protection Status", "Price Protection Start Date",
			"Price Protection End Date", "Price Protection Price Type", "Price Tolerance Interval",
			"Price Tolerance Frequency", "Price Tolerance Type", "Max Incremental Change", "Price Tolerance", "Reset",
			"Eligibility", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency",
			Constants.ATTACHED_DATE_FIELD };
	public final Object newRsDetailsColumns[] = new Object[] { Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY,
			Constants.BRAND_PROPERTY, Constants.ITEM_STATUS_PROPERTY, Constants.START_DATE, Constants.END_DATE,
			"formulaType", "formulaId", "formulaName", "rebatePlanId", "rebatePlanName", "rebateAmount", "bundleNo",
			Constants.ATTACHED_DATE_PROPERTY };
	public final String newRsDetailsHeaders[] = new String[] { Constants.IFP_NO, Constants.IFP_NAME_LABEL,
			Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER,
			"Formula Type", "Formula ID", "Formula Name", "Rebate Plan ID", "Rebate Plan Name", "Rebate Amount",
			"Bundle No", Constants.ATTACHED_DATE_FIELD };

	public static AbstractOrderedLayout getLayout(AbstractOrderedLayout layout) {
		resourceBundle = ResourceBundle.getBundle("configurations/default");
		layout.setMargin(Boolean.parseBoolean(resourceBundle.getString("layout_margin")));
		layout.setSpacing(Boolean.parseBoolean(resourceBundle.getString("layout_spacing")));
		return layout;
	}

	private static UiUtils object;
	public final Object[] visibleColumnItemSearch = { "checkRecord", Constants.SYSTEM_ID_PROPERTY,
			Constants.COMPANY_PROPERTY, Constants.ITEM_ID_PROPERTY, Constants.ITEM_NO_PROPERTY,
			Constants.ITEM_NAME_PROPERTY, Constants.ITEM_DESC_PROPERTY, CommonUtils.THERAPEUTIC_CLASS_PROPERTY,
			Constants.BRAND_PROPERTY, "form", Constants.STRENGTH_PROPERTY, Constants.PLACE_HOLDER_PROPERTY, "ndc9",
			Constants.ITEM_CATEGORY_PROPERTY, Constants.ITEM_TYPE_PROPERTY };
	public final String[] transferHeader = { "", "Item ID - From", "Item No - From", "Item Name - From", "Item ID - To",
			"Item No - To", "Item Name - To" };
	public final String[] columnHeaderItemSearch = { "", Constants.SYSTEM_ID_LABEL, Constants.COMPANY_LABEL,
			Constants.ITEM_ID, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.ITEM_DESC_LABEL,
			Constants.THERAPEUTIC_CLASS_LABEL, Constants.BRAND, Constants.FORM, Constants.STRENGTH,
			Constants.PLACE_HOLDER_LABEL, "NDC9", Constants.ITEM_CATEGORY_LABEL, Constants.ITEM_TYPE };
	public final String[] excelColumnHeaderItemSearch = { Constants.SYSTEM_ID_LABEL, Constants.COMPANY_LABEL,
			Constants.ITEM_ID, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.ITEM_DESC_LABEL,
			Constants.THERAPEUTIC_CLASS_LABEL, Constants.BRAND, Constants.FORM, Constants.STRENGTH,
			Constants.PLACE_HOLDER_LABEL, "NDC9", Constants.ITEM_CATEGORY_LABEL, Constants.ITEM_TYPE };
	public final Object[] visibleColumnItem = { Constants.SYSTEM_ID_PROPERTY, Constants.COMPANY_PROPERTY,
			Constants.ITEM_ID_PROPERTY, Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY,
			Constants.ITEM_DESC_PROPERTY, CommonUtils.THERAPEUTIC_CLASS_PROPERTY, Constants.BRAND_PROPERTY, "form",
			Constants.STRENGTH_PROPERTY, Constants.PLACE_HOLDER_PROPERTY, "ndc9", Constants.ITEM_CATEGORY_PROPERTY,
			Constants.ITEM_TYPE_PROPERTY };
	public final String[] columnHeaderItem = { Constants.SYSTEM_ID_LABEL, Constants.COMPANY_LABEL, Constants.ITEM_ID,
			Constants.ITEM_NO, Constants.ITEM_NAME, Constants.ITEM_DESC_LABEL, Constants.THERAPEUTIC_CLASS_LABEL,
			Constants.BRAND, Constants.FORM, Constants.STRENGTH, Constants.PLACE_HOLDER_LABEL, "NDC9",
			Constants.ITEM_CATEGORY_LABEL, Constants.ITEM_TYPE };
	public final Object[] excelVisibleColumnItemSearch = { Constants.SYSTEM_ID_PROPERTY, Constants.COMPANY_PROPERTY,
			Constants.ITEM_ID_PROPERTY, Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY,
			Constants.ITEM_DESC_PROPERTY, CommonUtils.THERAPEUTIC_CLASS_PROPERTY, Constants.BRAND_PROPERTY, "form",
			Constants.STRENGTH_PROPERTY, Constants.PLACE_HOLDER_PROPERTY, "ndc9", Constants.ITEM_CATEGORY_PROPERTY,
			Constants.ITEM_TYPE_PROPERTY };
	public final Object[] transferVisible = { Constants.CHECK_RECORD, Constants.ITEM_ID_PROPERTY,
			Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, "itemIdTo", "itemNoTo", "itemNameTo" };
        
    
    
    /** The Constant TRADE_CLASS. */
    public static final String TRADE_CLASS = "COMP_TRADECLASS";
    
    /** The Constant COMPANY_GROUP. */
    public static final String COMPANY_GROUP = "COMP_GROUP";
    
    /** The Constant COUNTRY. */
    public static final String COUNTRY = "COUNTRY";
    
    /** The Constant ORGANIZATION_KEY. */
    public static final String ORGANIZATION_KEY = "COMP_ORG_KEY";
    
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
    public static final Object[] QUALIFIER_COMPANY = new Object[]{"companyQualifier", ConstantsUtils.COMPANY_CRT_QUALIFIER_NAME,"effectiveDates","notes",ConstantsUtils.MODIFIEDBY,ConstantsUtils.MODIFIEDDATE,ConstantsUtils.CREATEDBY,ConstantsUtils.CREATEDDATE};
    
    /** The Constant QUALIFIER_COMPANY_HEADERS. */
    public static final String[] QUALIFIER_COMPANY_HEADERS = new String[]{"Company Qualifier", ConstantsUtils.COMPANY_QUALIFIER_NAME_HEADER,"Effective Dates","Notes",ConstantsUtils.MODIFIED_BY,ConstantsUtils.CREATED_DATE,ConstantsUtils.CREATED_BY,ConstantsUtils.CREATED_DATE};
    
    /** The Constant PARENT_COMPANY_NO_COLUMNS. */
    public static final Object[] PARENT_COMPANY_NO_COLUMNS = new Object[]{ConstantsUtils.COMPANY_ID, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,
        ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE};

    /** The Constant PARENT_COMPANY_NO_HEADERS. */
    public static final String[] PARENT_COMPANY_NO_HEADERS = new String[]{ConstantsUtils.COMPANYID,
        ConstantsUtils.COMPANYNO, ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE};
    
    
    /**
     * The Constant PARENT_COMPANY_NO_COLUMNS.
     */
    public static final Object[] PARENT_COMPANY_NO_COLUMNS_ITEM = new Object[]{ConstantsUtils.SYSTEM_ID,ConstantsUtils.COMPANY_ID, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,
        ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE, ConstantsUtils.TRADE_CLASS,ConstantsUtils.ADDRESS1,ConstantsUtils.ADDRESS2,"city" };

    /**
     * The Constant PARENT_COMPANY_NO_HEADERS.
     */
    public static final String[] PARENT_COMPANY_NO_HEADERS_ITEM = new String[]{ConstantsUtils.SYSTEM_ID_HEADER,ConstantsUtils.COMPANYID,
        ConstantsUtils.COMPANYNO, ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, ConstantsUtils.TRAD_CLASS,"Address1","Address2","City"};
    
     /**
     * The Constant PARENT_COMPANY_NO_COLUMNS.
     */
    public static final Object[] PARENT_COMPANY_NO_COLUMNS_ITEM_WITH = new Object[]{ConstantsUtils.SYSTEM_ID,ConstantsUtils.COMPANY_ID, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,
        ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE, ConstantsUtils.TRADE_CLASS, ConstantsUtils.IDENTIFIER, "identifierType",ConstantsUtils.ADDRESS1,ConstantsUtils.ADDRESS2,"city"};

    /**
     * The Constant PARENT_COMPANY_NO_HEADERS.
     */
    public static final String[] PARENT_COMPANY_NO_HEADERS_ITEM_WITH = new String[]{ConstantsUtils.SYSTEM_ID_HEADER,ConstantsUtils.COMPANYID,
        ConstantsUtils.COMPANYNO, ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, ConstantsUtils.TRAD_CLASS, "Identifier", "Identifier Type","Address1","Address2","City"};

    /** The Constant WITHOUT_IDEN. */
    public static final Object[] WITHOUT_IDEN = new Object[]{ConstantsUtils.SYSTEM_ID,ConstantsUtils.COMPANY_ID, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,
        ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE, "compStartDate", "compEndDate", ConstantsUtils.TRADE_CLASS,
        "tradeStartDate", "tradeEndDate", "lives", "companyGroup", "companyCategory",
        "organizationKey", "financialSystem", ConstantsUtils.PARENT_COMPANY_NO, "parentSDate", "parentEDate",
        "priorParentCompanyNo", "priorParentSDate", "regionCode", "udc1", "udc2", "udc3", "udc4",
        "udc5", "udc6", ConstantsUtils.ADDRESS1, ConstantsUtils.ADDRESS2, "zipCode", "city", "state", "country"};

    /** The Constant WITHOUT_IDEN_HEADER. */
    public static final String[] WITHOUT_IDEN_HEADER = new String[]{ConstantsUtils.SYSTEM_ID_HEADER,ConstantsUtils.COMPANYID,
        "Company No", ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, "Company Start Date", "Company End Date", ConstantsUtils.TRAD_CLASS, ConstantsUtils.TRADE_CLASS_START_DATE_HEADER, ConstantsUtils.TRADE_CLASS_END_DATE_HEADER, "Lives", "Company Group", "Company Category",
        "Organization ", "Financial System", "Parent Company No.", "Parent Start Date", "Parent End Date",
        "Prior Parent Company No.", "Prior Parent Start Date", "Region Code", "UDC 1", "UDC 2", "UDC 3", "UDC 4",
        "UDC 5", "UDC 6", "Address 1", "Address 2", "Zip Code", "City", "State", "Country"};
    
    /** The Constant WITH_IDEN. */
    public static final Object[] WITH_IDEN = new Object[]{ConstantsUtils.SYSTEM_ID,ConstantsUtils.COMPANY_ID,
        ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME, ConstantsUtils.COMPANY_STATUS, ConstantsUtils.COMPANY_TYPE, 
        "companyCategory", "companyGroup", ConstantsUtils.TRADE_CLASS ,"identifierTypeDesc", ConstantsUtils.IDENTIFIER,
        "compStartDate", "compEndDate", "tradeStartDate", "tradeEndDate", "lives", 
        "organizationKey", "financialSystem", ConstantsUtils.PARENT_COMPANY_NO, "parentSDate", "parentEDate",
        "priorParentCompanyNo", "priorParentSDate", "regionCode", "udc1", "udc2", "udc3", "udc4",
         "udc5", "udc6", ConstantsUtils.ADDRESS1, ConstantsUtils.ADDRESS2, "zipCode", "city", "state", "country"};

    /** The Constant WITH_IDEN_HEADER. */
    public static final String[] WITH_IDEN_HEADER = new String[]{ConstantsUtils.SYSTEM_ID_HEADER,ConstantsUtils.COMPANYID,
        "Company No", ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, 
        "Company Category", "Company Group", ConstantsUtils.TRAD_CLASS, ConstantsUtils.COMPANY_QUALIFIER_NAME_HEADER, "Company Identifier",
         "Company Start Date", "Company End Date",  ConstantsUtils.TRADE_CLASS_START_DATE_HEADER, ConstantsUtils.TRADE_CLASS_END_DATE_HEADER, "Lives", 
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
        ConstantsUtils.COMPANY_NAME, ConstantsUtils.COMPANY_TYPE, ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_STATUS, ConstantsUtils.TRADE_CLASS, ConstantsUtils.IDENTIFIER, "identifierTypeDesc"};

    /**
     * "Human readable" captions for properties in same order as in
     * NATURAL_COL_ORDER.
     */
    public static final String[] COL_HEADERS_ENGLISH = new String[]{
        ConstantsUtils.COMPANYID, ConstantsUtils.COMPANYNAME, "Company No.",
        ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANYTYPE, ConstantsUtils.TRADE_CLASS, "Identifier Type"};
    
    /** The Constant IDEN_FORM_COL_ORDER. */
    public static final Object[] IDEN_FORM_COL_ORDER = new Object[]{
        ConstantsUtils.COMPANY_CRT_QUALIFIER_NAME, "companyIdentifier", "identifierStatus", "startDate", "endDate", ConstantsUtils.CREATEDDATE, ConstantsUtils.CREATEDBY, ConstantsUtils.MODIFIEDBY, ConstantsUtils.MODIFIEDDATE
        };

    /** The Constant IDEN_FORM_COL_HEADER. */
    public static final String[] IDEN_FORM_COL_HEADER = new String[]{
        ConstantsUtils.COMPANY_QUALIFIER_NAME_HEADER, "Company Identifier", "Identifier Status", "Start Date", "End Date", ConstantsUtils.CREATED_DATE, ConstantsUtils.CREATED_BY, ConstantsUtils.MODIFIED_BY, ConstantsUtils.CREATED_DATE };

    /** The Constant IDEN_VIEW_FORM_COL_ORDER. */
    public static final Object[] IDEN_VIEW_FORM_COL_ORDER = new Object[]{
        ConstantsUtils.COMPANY_CRT_QUALIFIER_NAME, "companyIdentifier", "identifierStatusValue", "viewStartDate", "viewEndDate", "viewCreatedDate", ConstantsUtils.CREATEDBY, ConstantsUtils.MODIFIEDBY, "viewModifiedDate"
        };



    /** The Constant TRADE_CLASS_COLUMNS. */
    public static final Object[] TRADE_CLASS_COLUMNS = new Object[]{
       ConstantsUtils.TRADE_CLASS, "tradeClassSDate", "tradeClassEDate",ConstantsUtils.CREATEDDATE,ConstantsUtils.CREATEDBY, ConstantsUtils.MODIFIEDBY, ConstantsUtils.MODIFIEDDATE
    };

    /** The Constant TRADE_CLASS_HEADERS. */
    public static final String[] TRADE_CLASS_HEADERS = new String[]{
       ConstantsUtils.TRAD_CLASS, ConstantsUtils.TRADE_CLASS_START_DATE_HEADER, ConstantsUtils.TRADE_CLASS_END_DATE_HEADER,ConstantsUtils.CREATED_DATE,ConstantsUtils.CREATED_BY, ConstantsUtils.MODIFIED_BY, ConstantsUtils.CREATED_DATE
    };
    
   
    /** The Constant PARENT_COMPANY_COLUMNS. */
    public static final Object[] PARENT_COMPANY_COLUMNS= new Object[]{
		ConstantsUtils.PARENT_COMPANY_NO, "parentCompanyName", "parentStartDate","parentEndDate", ConstantsUtils.CREATEDDATE,ConstantsUtils.CREATEDBY, ConstantsUtils.MODIFIEDBY, ConstantsUtils.MODIFIEDDATE
	};
	
	/** The Constant PARENT_COMPANY_HEADERS. */
	public static final String[] PARENT_COMPANY_HEADERS= new String[]{
		"Parent Company No", "Parent Company Name", "Parent Company Start Date","Parent Company End Date",ConstantsUtils.CREATED_DATE,ConstantsUtils.CREATED_BY, ConstantsUtils.MODIFIED_BY, ConstantsUtils.CREATED_DATE	}; 
   
        
    /** The edit list. */
    public static final String EDIT_LIST = "Edit List";
   /**
    * Instantiates a new UI utils.
    */

	/**
	 * Constructor
	 */
	private UiUtils() {
		/*
		 * Constructor
		 */
	}

	public static UiUtils getInstance() {
		if (object == null) {
			object = new UiUtils();
		}
		return object;
	}
}
