package com.stpl.app.global.item.util;

// TODO: Auto-generated Javadoc

import com.stpl.app.global.item.util.LabelUtils;
import com.stpl.app.util.ConstantsUtils;

/**
 * The Class UIUtils.
 */
public final class UIUtils {
    
    /** The ALL list type. */
	public static final String ALL = "ALL";

	/** The item type. */
	public static final String ITEM_TYPE = "ITEM_TYPE";
	
	/** The item status. */
	public static final String ITEM_STATUS = "ITEM_STATUS";
        
        public static final String STATUS = "STATUS";
	
	/** The ifp type. */
	public static final String IFP_TYPE = "ItemFamilyPlanType";
	
	/** The ps type. */
	public static final String PS_TYPE = "PS_TYPE";
        /** The ps type. */
	public static final String PS_TYPE__COLUMN = "ps_Type";
	
	/** The ps category. */
	public static final String PS_CATEGORY = "PsCategory";
	
	/** The price type. */
	public static final String PRICE_TYPE="PriceType";
	
	/** The price tolerance. */
	public static final String PRICE_TOLERANCE="PriceTolerance";
	
	/** The price tolerance type. */
	public static final String PRICE_TOLERANCE_TYPE="PriceToleranceType";
	
	/** The price tolerance interval. */
	public static final String PRICE_TOLERANCE_INTERVAL="PriceToleranceInterval";
	
	/** The price tolerance frerquency. */
	public static final String PRICE_TOLERANCE_FRERQUENCY="PriceToleranceFrequency";
	
	/** The ps designation. */
	public static final String PS_DESIGNATION = "PriceScheduleDesignation";
	
	/** The trade class. */
	public static final String TRADE_CLASS = "TradeClass";

	
	/** Therapeutic class. */
	public static final String THERAPEUTIC_CLASS  = "THERAPEUTIC_CLASS";
	
	/** The UD c1. */
	public static final String UDC1 = "ITEM_UDC1";
    
    /** The UD c2. */
    public static final String UDC2 =	"ITEM_UDC2";
    
    /** The UD c3. */
    public static final String UDC3 = "ITEM_UDC3";
    
    /** The UD c4. */
    public static final String UDC4 = "ITEM_UDC4";
    
    /** The UD c5. */
    public static final String UDC5 = "ITEM_UDC5";
    
    /** The UD c6. */
    public static final String UDC6 = "ITEM_UDC6";
    
    /** The display brand. */
    public static final String DISPLAY_BRAND   = "ITEM_DISP_BRAND";
    
    /** The item category. */
    public static final String ITEM_CATEGORY  = "ITEM_CATEGORY";
    
     /** The item category. */
    public static final String ITEM_CLASS  = "ITEM_CLASS";
    
    /** The item type indication. */
    public static final String ITEM_TYPE_INDICATION   = "ITEM_TYPE_INDICATION";
    
    /** The item type indication. */
    public static final String PACKAGE_SIZE   = "PACKAGE_SIZE";
    
    /** The shelf life type. */
    public static final String SHELF_LIFE_TYPE  = "SHELF_LIFE_TYPE";
    
    /** The primary uom. */
    public static final String PRIMARY_UOM = "ITEM_PRIM_UOM";
    
    /** The secondary uom. */
    public static final String SECONDARY_UOM = "ITEM_SEC_UOM";
    
    /** The item identifier status. */
    public static final String ITEM_IDENTIFIER_STATUS="ITEM_IDEN_STATUS";
    
    /** The pricing status. */
    public static final String PRICING_STATUS="ITEM_PRIC_QUAL_STATUS";
	
	/** The item uom. */
	public static final String ITEM_UOM = "ITEM_UOM";
        
        public static final String UOM = "UOM";
	
	/** The form. */
	public static final String FORM = "ITEM_FORM";
        
        public static final String FORM1 = "FORM";
	
	/** The strength. */
	public static final String STRENGTH = "ITEM_STRENGTH";
        public static final String STRENGTH1 = "STRENGTH";
        public static final String NS_FORMULA_TYPE= "NS_FORMULA_TYPE";

public static final Object[] WITHOUT_IDEN_SEARCH = new Object[] { "systemId", ConstantsUtils.ITEM_ID,
			ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_TYPE,ConstantsUtils.THERAPEUTIC_CLASS,
			"brand", ConstantsUtils.NDC9, ConstantsUtils.NDC8, ConstantsUtils.FORM, ConstantsUtils.STRENGTH
			 };
	
        
        
    public static final String[] WITHOUT_IDEN_SEARCH_HEADER = new String[] { LabelUtils.SYSTEM_ID, "Item ID", ConstantsUtils.ITEMS_NO,
		ConstantsUtils.ITEM_NAMES, "Item Desc", ConstantsUtils.ITEMSTATUS, "Item Type",LabelUtils.THERAPY_CLASS, "Brand",
                LabelUtils.NDC_9, "NDC 8", 
		 "Form","Strength",  
		};
        
        public static final Object[] WITH_IDEN_SEARCH = new Object[] { "systemId", ConstantsUtils.ITEM_ID,
			ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_IRT_QUALIFIER_NAME, ConstantsUtils.ITEM_IDENTIFIER, ConstantsUtils.THERAPEUTIC_CLASS,
			"brand", ConstantsUtils.NDC9, ConstantsUtils.NDC8, ConstantsUtils.FORM, ConstantsUtils.STRENGTH
			 };
	
	
        
        /** The Constant WITH_IDEN_SEARCH_HEADER. */
	public static final String[] WITH_IDEN_SEARCH_HEADER = new String[] { LabelUtils.SYSTEM_ID, "Item ID", ConstantsUtils.ITEMS_NO,
		ConstantsUtils.ITEM_NAMES, "Item Desc", ConstantsUtils.ITEMSTATUS, "Item Type", "Item Qualifier Name", "Item Identifier", LabelUtils.THERAPY_CLASS, "Brand",
                LabelUtils.NDC_9, "NDC 8", 
		 "Form","Strength", 
		};
	
	
	
	
	/** The Constant COL_WITHOUT_IDEN. */
	public static final Object[] COL_WITHOUT_IDEN = new Object[] { "Item Id",
			ConstantsUtils.ITEMS_NO, "Item Name", "Item Description", "Item Status",
			"Item Type" };

	/** The Constant QUALIFIER_ITEM. */
	public static final Object[] QUALIFIER_ITEM = new Object[] {"identifierCodeQualifier","identifierCodeQualifierName","effectiveDates","entityCode","notes",
                                                                        "modifiedBy","modifiedDate","createdBy","createdDate"};
        
        /** The Constant QUALIFIER_ITEM_HEADER. */
	public static final String[] QUALIFIER_ITEM_HEADER = new String[] {"Identifier Code Qualifier","Identifier Code Qualifier Name","Effective Dates","Speific Entity Code",
            "Notes","Modified By","Modified Date","Created By","Created Date"};
	
	/** The Constant QUALIFIER_PRICING. */
	public static final Object[] QUALIFIER_PRICING = new Object[] {
		"identifierCodeQualifier", "identifierCodeQualifierName" };
        
        /** The Constant PRICING_QUALIFIER_HEADER. */
	public static final String[] PRICING_QUALIFIER_HEADER = new String[] {"Pricing Qualifier", "Pricing Qualifier Name" };        
	
	/** The Constant COL_WITH_IDEN. */
	public static final Object[] COL_WITH_IDEN = new Object[] { "Item Id",
			ConstantsUtils.ITEMS_NO, "Item Name", "Item Description", "Item Status",
			"Item Type", "Qualifier", "Identifier" };

	/** The Constant ITEMINFO_COL_ORDER. */
	public static final Object[] ITEMINFO_COL_ORDER = new Object[] { ConstantsUtils.ITEM_ID,
			ConstantsUtils.ITEM_NAME, ConstantsUtils.STRENGTH, ConstantsUtils.PRIMARY_UOM, ConstantsUtils.ITEM_NO, ConstantsUtils.SECONDARY_UOM,
			ConstantsUtils.FORM, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_CODE,
			ConstantsUtils.PACKAGE_SIZE, ConstantsUtils.ITEM_CLASS, ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE };

	/** The Constant ADDINFO_COL_ORDER. */
	public static final Object[] ADDINFO_COL_ORDER = new Object[] { ConstantsUtils.ITEM_ID,
			ConstantsUtils.ITEM_NAME, ConstantsUtils.STRENGTH, ConstantsUtils.PRIMARY_UOM, ConstantsUtils.ITEM_NO, ConstantsUtils.SECONDARY_UOM,
			ConstantsUtils.FORM, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_CODE,
			ConstantsUtils.PACKAGE_SIZE, ConstantsUtils.ITEM_CLASS, ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE };

	/** The Constant FORM_COL_ORDER. */
	public static final Object[] FORM_COL_ORDER = new Object[] { ConstantsUtils.ITEM_ID,
			ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_NO, ConstantsUtils.MANUFACTURED_ID, ConstantsUtils.ITEM_DESC,
			ConstantsUtils.ITEM_STATUS, "identifier", "identifierTypeDesc" };

	/**
	 * "Human readable" captions for properties in same order as in
	 * NATURAL_COL_ORDER.
	 */
	public static final String[] COL_HEADERS_ENGLISH = new String[] {
			"Item Id", "Item Name", ConstantsUtils.ITEMS_NO, "item Description",
			"Item Status", "Item Type", "Manufacturer Id", "Identifier Type" };

	/** The Constant IDEN_FORM_COL_ORDER. */
	public static final Object[] IDEN_FORM_COL_ORDER = new Object[] {
			ConstantsUtils.ITEM_IRT_QUALIFIER_NAME,ConstantsUtils.ITEM_IDENTIFIER, "identifierStatus", "startDate", "endDate",ConstantsUtils.ENTITY_CODE,ConstantsUtils.ENTITY_CODE_NAME, "modifiedBy", "modifiedDate", "createdBy", "createdDate"};
	        
	/** The Constant IDEN_FORM_COL_ORDER_HEADER. */
	public static final String[] IDEN_FORM_COL_ORDER_HEADER = new String[] {
		"Item Qualifier Name", "Item Identifier", "Identifier Status", "Start Date", "End Date",ConstantsUtils.ENTITYCODE,ConstantsUtils.ENTITYCODENAME, "Modified By", "Modified Date", "Created By", "Created Date"};
	
	/** The Constant IDEN_VIEW_FORM_COL_ORDER. */
	public static final Object[] IDEN_VIEW_FORM_COL_ORDER = new Object[] {
		ConstantsUtils.ITEM_IRT_QUALIFIER_NAME, ConstantsUtils.ITEM_IDENTIFIER, "identifierStatusView", "viewStartDate", "viewEndDate",ConstantsUtils.ENTITY_CODE, ConstantsUtils.ENTITY_CODE_NAME, "modifiedBy", "modifiedDate", "createdBy", "createdDate"
		  };
	
	/** The Constant PRICING_FORM_COL_ORDER. */
	public static final Object[] PRICING_FORM_COL_ORDER = new Object[] {

			"itemPricingQualifierName", "itemPrice","pricingCodeStatus", "itemUom",
			 "pricingStartDate", "pricingEndDate", ConstantsUtils.ENTITY_CODE,"source", "modifiedBy", "modifiedDate", "createdBy", "createdDate"};
	
	/** The Constant PRICING_FORM_COL_ORDER_HEADER. */
	public static final String[] PRICING_FORM_COL_ORDER_HEADER = new String[] {
		"Pricing Qualifier Name", "Item Price", "Pricing Status", "Item UOM", "Start Date", "End Date", 
		"Entity Code","Source", "Modified By", "Modified Date", "Created By", "Created Date"};
	
	/** The Constant PRICING_VIEW_FORM_COL_ORDER. */
	public static final Object[] PRICING_VIEW_FORM_COL_ORDER = new Object[] {

		"identifierCodeQualifierName", "itemPrice","pricingCodeStatusView","itemUomView",
		 "priceStartDate", "priceEndDate", ConstantsUtils.ENTITY_CODE,"source", "modifiedBy", "modifiedDate", "createdBy", "createdDate"};
		
	
	/** The Constant PRICING_GRID_COL_ORDER. */
	public static final Object[] PRICING_GRID_COL_ORDER = new Object[] {
			ConstantsUtils.ITEM_IRT_QUALIFIER_NAME, ConstantsUtils.ITEM_IDENTIFIER, ConstantsUtils.ENTITY_CODE,
			"startDate", "endDate", "identifierStatus" };
	
	
/** The Constant IFP_COL_HEADERS. */
public static final String[] IFP_COL_HEADERS = new String[] {
	"System ID","IFP ID", "IFP No", "IFP Name",
	"IFP Type","IFP Status","IFP Category", "IFP Start Date", "IFP End Date","IFP Designation","Parent ID",
        "Parent Name",
        "Created By","Created Date"};

/** The Constant IFP_SEARCH_TABLE. */
public static final Object[] IFP_SEARCH_TABLE = new Object[] {
		"itemFamilyplanSystemId","ifpId", "ifpNo", "ifpName",
		"ifpType", "ifpStatus", "ifpCategory", "itemFamilyplanStartDate", "itemFamilyplanEndDate","ifpDesignation","parentItemFamilyplanId",
                "parentItemFamilyplanName",
		"createdBy","createdDate"};

/** The Constant AVAILABLE_ITEM_COL. */
public static final Object[] AVAILABLE_ITEM_COL = new Object[] {
	ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, ConstantsUtils.FORM,
	ConstantsUtils.PACKAGE_SIZE };

/** The Constant AVAILABLE_ITEM_COL_HEADER. */
public static final String[] AVAILABLE_ITEM_COL_HEADER = new String[] {
	"item No", ConstantsUtils.ITEM_NAMES, ConstantsUtils.FORM,
	ConstantsUtils.PACK_SIZE };

/** The Constant SELECTED_ITEM_COL. */
public static final Object[] SELECTED_ITEM_COL = new Object[] {
	ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME};

/** The Constant SELECTED_ITEM_COL_HEADER. */
public static final String[] SELECTED_ITEM_COL_HEADER = new String[] {
	"item No", ConstantsUtils.ITEM_NAMES};

/** The Constant ITEM_DETAILS_COL. */
public static final Object[] ITEM_DETAILS_COL = new Object[] {
	"checkbox", "itemFamilyplanNo", "itemFamilyplanName",
	ConstantsUtils.ITEM_NO ,ConstantsUtils.ITEM_NAME, "Uom", ConstantsUtils.PACKAGE_SIZE,ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE, "ifpStartDate","ifpEndDate", ConstantsUtils.ITEM_STATUS};

/** The Constant ITEM_DETAILS_COL_HEADER. */
public static final String[] ITEM_DETAILS_COL_HEADER = new String[] {
	"check box", "item Familyplan No", "item Familyplan Name",
	"item No" ,ConstantsUtils.ITEM_NAMES, "Uom", ConstantsUtils.PACK_SIZE,ConstantsUtils.ITEM_STARTDATE, ConstantsUtils.ITEM_ENDDATE, "ifp Start Date","ifp End Date", ConstantsUtils.ITEMSTATUS};



/** The Constant NEW_FORMULATION_SEARCH. */
public static final Object[] NEW_FORMULATION_SEARCH = new Object[] {  ConstantsUtils.ITEM_ID,ConstantsUtils.ITEM_NO,
	ConstantsUtils.ITEM_NAME,ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_TYPE,ConstantsUtils.MANUFACTURED_ID, ConstantsUtils.ITEM_DESC,  ConstantsUtils.ITEM_CODE,
	ConstantsUtils.PACKAGE_SIZE, ConstantsUtils.UPPS, ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE,
	 "labelerCode", ConstantsUtils.FORM, ConstantsUtils.STRENGTH, ConstantsUtils.PRIMARY_UOM,
	ConstantsUtils.SECONDARY_UOM, ConstantsUtils.ITEM_CLASS, "pediatricExclusiveStartDate",
	"pediatricExclusiveEndDate", "clottingFactorStartDate",
	"clottingFactorEndDate" };

/** The Constant NEW_FORMULATION_HEADER. */
public static final String[] NEW_FORMULATION_HEADER = new String[] { "item Id",ConstantsUtils.ITEMS_NO,
ConstantsUtils.ITEM_NAMES,ConstantsUtils.ITEMSTATUS, "item Type","manufacturer Id", "item Desc",  "item Code",
ConstantsUtils.PACK_SIZE, ConstantsUtils.UPPS, ConstantsUtils.ITEM_STARTDATE, ConstantsUtils.ITEM_ENDDATE,
 "labeler Code", ConstantsUtils.FORM, ConstantsUtils.STRENGTH, "primary Uom",
"secondary Uom", "item Class", "pediatric Exclusive Start Date",
"pediatric Exclusive End Date", "clotting Factor Start Date",
"clotting Factor End Date" };
   
/**
 * Constructor
 */
private UIUtils(){
	//Empty
}

}
