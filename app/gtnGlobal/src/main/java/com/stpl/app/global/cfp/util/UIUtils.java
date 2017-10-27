package com.stpl.app.global.cfp.util;

import com.stpl.app.util.ConstantsUtils;


/**
 * The Class UIUtils.
 */
public final class UIUtils {
	
	/** The Constant COMPANY_TYPE. */
	public static final String COMPANY_TYPE = "CompanyType";
	
	/** The Constant CFP_TYPE. */
	public static final String CFP_TYPE = "CFP_TYPE";
	
	/** The Constant TRADE_CLASS. */
	public static final String TRADE_CLASS = "CFP_TRADE_CLASS";
	
	/** The Constant CFP_DESIGNATION. */
	public static final String CFP_DESIGNATION="CFP_DESIGNATION";
	
	/** The Constant COMPANY_GROUP. */
	public static final String COMPANY_GROUP = "CompanyGroup";
	
	/** The Constant COMPANY_CATEGORY. */
	public static final String COMPANY_CATEGORY = "CompanyCategory";
	
	/** The Constant STATE. */
	public static final String STATE = "State";
	
	/** The Constant COUNTRY. */
	public static final String COUNTRY = "Country";
	
	/** The Constant CFP_STATUS. */
	public static final String CFP_STATUS="STATUS";
	
	/** The Constant CFP_CATAGORY. */
	public static final String CFP_CATAGORY="CFP_CATEGORY";
        
        /** The view. */
 	public static final String VIEW = "View";
        /** The view. */
 	public static final String COMPANY_TRADE_CLASS = "COMPANY_TRADE_CLASS";
        /** The view. */
 	public static final String COMPANY_CATEGORY_LIST_NAME = "COMPANY_CATEGORY";
        /** The view. */
 	public static final String COMPANY_TYPE_LIST_NAME = "COMPANY_TYPE";

	/** The Constant CFP_SEARCH. */
	public static final Object[] CFP_SEARCH = new Object[] {"systemId",
			"companyFamilyPlanId", "companyFamilyPlanNo", "companyFamilyPlanName","companyFamilyPlanType",
			"companyFamilyPlanStatus","companyFamilyPlanCategory",ConstantsUtils.CFP_START_DATE, ConstantsUtils.CFP_END_DATE ,
			"companyFamilyPlanDesignation",
			ConstantsUtils.PARENT_CFP_ID, ConstantsUtils.PARENT_CFP_NAME,ConstantsUtils.MODIFIEDDATE,ConstantsUtils.MODIFIEDBY,
                         ConstantsUtils.CREATEDBY,ConstantsUtils.CREATEDDATE};

	/** The Constant CFP_SEARCH_HEADER. */
	public static final String[] CFP_SEARCH_HEADER = new String[] {"System ID", "CFP ID", LabelUtils.COMPANYFAMILYPLANNO,
			LabelUtils.COMPANYFAMILYPLANNAME, LabelUtils.COMPANYFAMILYPLANTYPE,LabelUtils.COMPANYFAMILYPLANSTATUS,LabelUtils.COMPANYFAMILYPLANCATEGORY, "Start Date", 
			"End Date",LabelUtils.COMPANYFAMILYPLANDESIGNATION,"Parent ID","Parent Name",ConstantsUtils.MODIFIED_DATE,ConstantsUtils.MODIFIED_BY,
                        ConstantsUtils.CREATED_BY,ConstantsUtils.CREATED_DATE1};
        

	/**
	 * "Human readable" captions for properties in same order as in
	 * NATURAL_COL_ORDER.
	 */
	public static final String[] COL_HEADERS = new String[] { "CFP Id",
			LabelUtils.COMPANYFAMILYPLANNAME, LabelUtils.COMPANYFAMILYPLANSTATUS, LabelUtils.COMPANYFAMILYPLANNO, LabelUtils.COMPANYFAMILYPLANTYPE, ConstantsUtils.TRAD_CLASS,
			LabelUtils.COMPANYFAMILYPLANSTARTDATE, LabelUtils.COMPANYFAMILYPLANENDDATE, LabelUtils.COMPANYFAMILYPLANCATEGORY,
			LabelUtils.COMPANYFAMILYPLANDESIGNATION, "Parent CFP ID", "Parent CFP Name" };

	/** The Constant AVAILABLE_COMPANY_COL. */
	public static final Object[] AVAILABLE_COL = new Object[] {ConstantsUtils.COMPANY_ID,
			ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,ConstantsUtils.COMPANY_STATUS_VALUE,ConstantsUtils.COMPANY_TYPE_VALUE,ConstantsUtils.TRADE_CLASS_VALUE,
        ConstantsUtils.COMPANY_CATEGORY_VALUE,ConstantsUtils.COMPANY_GROUP_VALUE};
	
	/** The Constant AVAILABLE_COMPANY_COL_HEADER. */
	public static final String[] AVAILABLE_HEADER = new String[] {
			ConstantsUtils.COMPANYID,ConstantsUtils.COMPANYNO, ConstantsUtils.COMPANYNAME,LabelUtils.ATTACHEDSTATUS,ConstantsUtils.COMPANYTYPE,ConstantsUtils.TRAD_CLASS,ConstantsUtils.COMPANY_CATEGRY,ConstantsUtils.COMPANY_GRUP };
	
	/** The Constant SELECTED_COMPANY_COL. */
	public static final Object[] SELECTED_COL = new Object[] {ConstantsUtils.COMPANY_ID,
			ConstantsUtils.COMPANY_NO, ConstantsUtils.COMPANY_NAME,ConstantsUtils.COMPANY_STATUS_VALUE,ConstantsUtils.COMPANY_TYPE_VALUE,
        ConstantsUtils.TRADE_CLASS_VALUE,ConstantsUtils.COMPANY_CATEGORY_VALUE,ConstantsUtils.COMPANY_GROUP_VALUE};
	
	/** The Constant SELECTED_COMPANY_COL_HEADER. */
	public static final String[] SELECTED_HEADER = new String[] {ConstantsUtils.COMPANYID,
			ConstantsUtils.COMPANYNO, ConstantsUtils.COMPANYNAME,LabelUtils.ATTACHEDSTATUS,ConstantsUtils.COMPANYTYPE,ConstantsUtils.TRAD_CLASS,ConstantsUtils.COMPANY_CATEGRY,ConstantsUtils.COMPANY_GRUP };

	/** The Constant ITEM_DETAILS_COL. */
	public static final Object[] ITEM_DETAILS_COL = new Object[] { "checkbox",ConstantsUtils.COMPANY_ID,
			ConstantsUtils.COMPANY_NO,ConstantsUtils.COMPANY_NAME,ConstantsUtils.CFP_STATUS_VALUE,
			ConstantsUtils.CFP_START_DATE, ConstantsUtils.CFP_END_DATE ,
			ConstantsUtils.COMPANY_STATUS_VALUE,ConstantsUtils.COMPANY_TYPE_VALUE,ConstantsUtils.TRADE_CLASS,ConstantsUtils.COMPANY_CATEGORY,
                        ConstantsUtils.TRADING_PARTNER_CONTRACT_NO,ConstantsUtils.CFP_ATTACHED_DATE_COL,ConstantsUtils.MODIFIEDDATE,ConstantsUtils.MODIFIEDBY,ConstantsUtils.CREATEDDATE,ConstantsUtils.CREATEDBY };
	
	/** The Constant ITEM_DETAILS_COL_HEADER. */
	public static final String[] ITEM_COL_HEADER = new String[] { "",ConstantsUtils.COMPANYID,ConstantsUtils.COMPANYNO,
			ConstantsUtils.COMPANYNAME,LabelUtils.COMPANYFAMILYPLANSTATUS,LabelUtils.COMPANYFAMILYPLANSTARTDATE, LabelUtils.COMPANYFAMILYPLANENDDATE,
			ConstantsUtils.COMPANYSTATUS,ConstantsUtils.COMPANYTYPE,ConstantsUtils.COMPANY_TRADE_CLASS,ConstantsUtils.COMPANY_CATEGRY,
			ConstantsUtils.TRADING_PARTNER_CONTRACT_NO_LABEL,ConstantsUtils.ATTACHED_DATE,ConstantsUtils.MODIFIED_DATE,ConstantsUtils.MODIFIED_BY,ConstantsUtils.CREATED_DATE1,ConstantsUtils.CREATED_BY};
	
	/** The Constant ITEM_DETAILS_COL_VIEW. */
	public static final Object[] ITEM_COL_VIEW = new Object[] {ConstantsUtils.COMPANY_ID,
			ConstantsUtils.COMPANY_NO,ConstantsUtils.COMPANY_NAME,ConstantsUtils.CFP_STATUS_VALUE,
			ConstantsUtils.CFP_START_DATE, ConstantsUtils.CFP_END_DATE ,
			ConstantsUtils.COMPANY_STATUS_VALUE,ConstantsUtils.COMPANY_TYPE_VALUE,ConstantsUtils.TRADE_CLASS,ConstantsUtils.COMPANY_CATEGORY,
                        ConstantsUtils.TRADING_PARTNER_CONTRACT_NO,ConstantsUtils.CFP_ATTACHED_DATE_COL,ConstantsUtils.MODIFIEDDATE,ConstantsUtils.MODIFIEDBY,ConstantsUtils.CREATEDDATE,ConstantsUtils.CREATEDBY };
	
	/** The Constant ITEM_DETAILS_COL_HEADER_VIEW. */
	public static final String[] ITEM_HEADER_VIEW = new String[] {ConstantsUtils.COMPANYID,ConstantsUtils.COMPANYNO,
			ConstantsUtils.COMPANYNAME,LabelUtils.COMPANYFAMILYPLANSTATUS,LabelUtils.COMPANYFAMILYPLANSTARTDATE, LabelUtils.COMPANYFAMILYPLANENDDATE,
			ConstantsUtils.COMPANYSTATUS,ConstantsUtils.COMPANYTYPE,ConstantsUtils.COMPANY_TRADE_CLASS,ConstantsUtils.COMPANY_CATEGRY,
			ConstantsUtils.TRADING_PARTNER_CONTRACT_NO_LABEL,ConstantsUtils.ATTACHED_DATE,ConstantsUtils.MODIFIED_DATE,ConstantsUtils.MODIFIED_BY,ConstantsUtils.CREATED_DATE1,ConstantsUtils.CREATED_BY};
	
	/**
	 * Constructor
	 */
	private UIUtils(){
		//Empty
	}
}
