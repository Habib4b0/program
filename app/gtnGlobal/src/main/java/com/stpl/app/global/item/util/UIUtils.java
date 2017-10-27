package com.stpl.app.global.item.util;

// TODO: Auto-generated Javadoc

import com.stpl.app.util.ConstantsUtils;

/**
 * The Class UIUtils.
 */
public class UIUtils {
    
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
	public static final String PS_TYPE_COLUMN = "ps_Type";
	
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
	/** The UD c1. */
	public static final String ARM_UDC1 = "ARM_UDC1";
    
    /** The UD c2. */
    public static final String UDC2 =	"ITEM_UDC2";
    
    public static final String ARM_UDC2 = "ARM_UDC2";
    
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

public final Object[] withoutIdenSearch = new Object[] { "systemId", ConstantsUtils.ITEM_ID,
			ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_TYPE,ConstantsUtils.THERAPEUTIC_CLASS,
			"brand", ConstantsUtils.NDC9, ConstantsUtils.NDC8, ConstantsUtils.FORM, ConstantsUtils.STRENGTH
			 };
	
        
        
    public final String[] withoutIdenSearchHeader = new String[] { LabelUtils.SYSTEM_ID, "Item ID", ConstantsUtils.ITEMS_NO,
		ConstantsUtils.ITEM_NAMES, "Item Desc", ConstantsUtils.ITEMSTATUS, ConstantsUtils.ITEMS_TYPE,LabelUtils.THERAPY_CLASS, "Brand",
                LabelUtils.NDC_9, "NDC 8", 
		 "Form","Strength",  
		};
        
        public final Object[] withIdenSearch = new Object[] { "systemId", ConstantsUtils.ITEM_ID,
			ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_IRT_QUALIFIER_NAME, ConstantsUtils.ITEM_IDENTIFIER, ConstantsUtils.THERAPEUTIC_CLASS,
			"brand", ConstantsUtils.NDC9, ConstantsUtils.NDC8, ConstantsUtils.FORM, ConstantsUtils.STRENGTH
			 };
	
	
        
        /** The Constant WITH_IDEN_SEARCH_HEADER. */
	public final String[] withIdenSearchHeader = new String[] { LabelUtils.SYSTEM_ID, "Item ID", ConstantsUtils.ITEMS_NO,
		ConstantsUtils.ITEM_NAMES, "Item Desc", ConstantsUtils.ITEMSTATUS,  ConstantsUtils.ITEMS_TYPE, "Item Qualifier Name", "Item Identifier", LabelUtils.THERAPY_CLASS, "Brand",
                LabelUtils.NDC_9, "NDC 8", 
		 "Form","Strength", 
		};
	
	
	
	
	/** The Constant COL_WITHOUT_IDEN. */
	public final Object[] colWithoutIden = new Object[] { ConstantsUtils.ITEM_ID_LABEL,
			ConstantsUtils.ITEMS_NO, ConstantsUtils.ITEM_NAMES, "Item Description", ConstantsUtils.ITEMSTATUS,
			 ConstantsUtils.ITEMS_TYPE };

	/** The Constant QUALIFIER_ITEM. */
	public final Object[] qualifierItem = new Object[] {ConstantsUtils.IDENTIFIER_CODE_QUALIFIER,ConstantsUtils.IDENTIFIER_CODE_QUALIFIER_NAME,"effectiveDates","entityCode","notes",
                                                                        ConstantsUtils.MODIFIEDBY,ConstantsUtils.MODIFIEDDATE,ConstantsUtils.CREATEDBY,ConstantsUtils.CREATEDDATE};
        
        /** The Constant QUALIFIER_ITEM_HEADER. */
	public final String[] qualifierItemHeader = new String[] {"Identifier Code Qualifier","Identifier Code Qualifier Name","Effective Dates","Speific Entity Code",
            "Notes",ConstantsUtils.MODIFIED_BY,ConstantsUtils.MODIFIED_DATE,ConstantsUtils.CREATED_BY,ConstantsUtils.CREATED_DATE1};
	
	/** The Constant QUALIFIER_PRICING. */
	public final Object[] qualifierPricing = new Object[] {
		ConstantsUtils.IDENTIFIER_CODE_QUALIFIER, ConstantsUtils.IDENTIFIER_CODE_QUALIFIER_NAME };
        
        /** The Constant PRICING_QUALIFIER_HEADER. */
	public final String[] pricingQualifierHeader = new String[] {"Pricing Qualifier", "Pricing Qualifier Name" };        
	
	/** The Constant COL_WITH_IDEN. */
	public final Object[] colWiithIden = new Object[] { ConstantsUtils.ITEM_ID_LABEL,
			ConstantsUtils.ITEMS_NO, ConstantsUtils.ITEM_NAMES, "Item Description", ConstantsUtils.ITEMSTATUS,
			 ConstantsUtils.ITEMS_TYPE, "Qualifier", "Identifier" };

	/** The Constant ITEMINFO_COL_ORDER. */
	public final Object[] itemInfoColOrder = new Object[] { ConstantsUtils.ITEM_ID,
			ConstantsUtils.ITEM_NAME, ConstantsUtils.STRENGTH, ConstantsUtils.PRIMARY_UOM, ConstantsUtils.ITEM_NO, ConstantsUtils.SECONDARY_UOM,
			ConstantsUtils.FORM, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_CODE,
			ConstantsUtils.PACKAGE_SIZE, ConstantsUtils.ITEM_CLASS, ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE };

	/** The Constant ADDINFO_COL_ORDER. */
	public final Object[] addInfoColOrder = new Object[] { ConstantsUtils.ITEM_ID,
			ConstantsUtils.ITEM_NAME, ConstantsUtils.STRENGTH, ConstantsUtils.PRIMARY_UOM, ConstantsUtils.ITEM_NO, ConstantsUtils.SECONDARY_UOM,
			ConstantsUtils.FORM, ConstantsUtils.ITEM_DESC, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_CODE,
			ConstantsUtils.PACKAGE_SIZE, ConstantsUtils.ITEM_CLASS, ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE };

	/** The Constant FORM_COL_ORDER. */
	public final Object[] formColOrder = new Object[] { ConstantsUtils.ITEM_ID,
			ConstantsUtils.ITEM_NAME, ConstantsUtils.ITEM_TYPE, ConstantsUtils.ITEM_NO, ConstantsUtils.MANUFACTURED_ID, ConstantsUtils.ITEM_DESC,
			ConstantsUtils.ITEM_STATUS, "identifier", "identifierTypeDesc" };

	/**
	 * "Human readable" captions for properties in same order as in
	 * NATURAL_COL_ORDER.
	 */
	public final String[] colHeadersEnglish = new String[] {
			ConstantsUtils.ITEM_ID_LABEL, ConstantsUtils.ITEM_NAMES, ConstantsUtils.ITEMS_NO, "item Description",
			ConstantsUtils.ITEMSTATUS,  ConstantsUtils.ITEMS_TYPE, "Manufacturer Id", "Identifier Type" };

	/** The Constant IDEN_FORM_COL_ORDER. */
	public final Object[] idenFormColOrder = new Object[] {
			ConstantsUtils.ITEM_IRT_QUALIFIER_NAME,ConstantsUtils.ITEM_IDENTIFIER, "identifierStatus", "startDate", "endDate",ConstantsUtils.ENTITY_CODE,ConstantsUtils.ENTITY_CODE_NAME, ConstantsUtils.MODIFIEDBY, ConstantsUtils.MODIFIEDDATE, ConstantsUtils.CREATEDBY, ConstantsUtils.CREATEDDATE};
	        
	/** The Constant IDEN_FORM_COL_ORDER_HEADER. */
	public final String[] idenFormColOrderHeader = new String[] {
		"Item Qualifier Name", "Item Identifier", "Identifier Status", "Start Date", "End Date",ConstantsUtils.ENTITYCODE,ConstantsUtils.ENTITYCODENAME, ConstantsUtils.MODIFIED_BY, ConstantsUtils.MODIFIED_DATE, ConstantsUtils.CREATED_BY, ConstantsUtils.CREATED_DATE1};
	
	/** The Constant IDEN_VIEW_FORM_COL_ORDER. */
	public final Object[] idenViewFormColOrder = new Object[] {
		ConstantsUtils.ITEM_IRT_QUALIFIER_NAME, ConstantsUtils.ITEM_IDENTIFIER, "identifierStatusView", "viewStartDate", "viewEndDate",ConstantsUtils.ENTITY_CODE, ConstantsUtils.ENTITY_CODE_NAME, ConstantsUtils.MODIFIEDBY, ConstantsUtils.MODIFIEDDATE, ConstantsUtils.CREATEDBY, ConstantsUtils.CREATEDDATE
		  };
	
	/** The Constant PRICING_FORM_COL_ORDER. */
	public final Object[] pricingFormColOrder = new Object[] {

			"itemPricingQualifierName", "itemPrice","pricingCodeStatus", "itemUom",
			 "pricingStartDate", "pricingEndDate", ConstantsUtils.ENTITY_CODE,"source", ConstantsUtils.MODIFIEDBY, ConstantsUtils.MODIFIEDDATE, ConstantsUtils.CREATEDBY, ConstantsUtils.CREATEDDATE};
	
	/** The Constant PRICING_FORM_COL_ORDER_HEADER. */
	public final String[] pricingFormColOrderHeader = new String[] {
		"Pricing Qualifier Name", "Item Price", "Pricing Status", "Item UOM", "Start Date", "End Date", 
		"Entity Code","Source", ConstantsUtils.MODIFIED_BY, ConstantsUtils.MODIFIED_DATE, ConstantsUtils.CREATED_BY, ConstantsUtils.CREATED_DATE1};
	
	/** The Constant PRICING_VIEW_FORM_COL_ORDER. */
	public final Object[] pricingViewFormColOrder = new Object[] {

		ConstantsUtils.IDENTIFIER_CODE_QUALIFIER_NAME, "itemPrice","pricingCodeStatusView","itemUomView",
		 "priceStartDate", "priceEndDate", ConstantsUtils.ENTITY_CODE,"source", ConstantsUtils.MODIFIEDBY, ConstantsUtils.MODIFIEDDATE, ConstantsUtils.CREATEDBY, ConstantsUtils.CREATEDDATE};
		
	
	/** The Constant PRICING_GRID_COL_ORDER. */
	public final Object[] pricingGridColOrder = new Object[] {
			ConstantsUtils.ITEM_IRT_QUALIFIER_NAME, ConstantsUtils.ITEM_IDENTIFIER, ConstantsUtils.ENTITY_CODE,
			"startDate", "endDate", "identifierStatus" };
	
	
/** The Constant IFP_COL_HEADERS. */
public final String[] ifpColHeaders = new String[] {
	"System ID","IFP ID", "IFP No", "IFP Name",
	"IFP Type","IFP Status","IFP Category", "IFP Start Date", "IFP End Date","IFP Designation","Parent ID",
        "Parent Name",
        ConstantsUtils.CREATED_BY,ConstantsUtils.CREATED_DATE1};

/** The Constant IFP_SEARCH_TABLE. */
public final Object[] ifpSearchTable = new Object[] {
		"itemFamilyplanSystemId","ifpId", "ifpNo", "ifpName",
		"ifpType", "ifpStatus", "ifpCategory", "itemFamilyplanStartDate", "itemFamilyplanEndDate","ifpDesignation","parentItemFamilyplanId",
                "parentItemFamilyplanName",
		ConstantsUtils.CREATEDBY,ConstantsUtils.CREATEDDATE};

/** The Constant AVAILABLE_ITEM_COL. */
public final Object[] availableItemCol = new Object[] {
	ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME, ConstantsUtils.FORM,
	ConstantsUtils.PACKAGE_SIZE };

/** The Constant AVAILABLE_ITEM_COL_HEADER. */
public final String[] availableItemColHeader = new String[] {
	ConstantsUtils.ITEMNO, ConstantsUtils.ITEM_NAMES, ConstantsUtils.FORM,
	ConstantsUtils.PACK_SIZE };

/** The Constant SELECTED_ITEM_COL. */
public final Object[] selectedItemCol = new Object[] {
	ConstantsUtils.ITEM_NO, ConstantsUtils.ITEM_NAME};

/** The Constant SELECTED_ITEM_COL_HEADER. */
public final String[] selectedItemColHeader = new String[] {
	ConstantsUtils.ITEMNO, ConstantsUtils.ITEM_NAMES};

/** The Constant ITEM_DETAILS_COL. */
public final Object[] itemDetailsCol = new Object[] {
	"checkbox", "itemFamilyplanNo", "itemFamilyplanName",
	ConstantsUtils.ITEM_NO ,ConstantsUtils.ITEM_NAME, "Uom", ConstantsUtils.PACKAGE_SIZE,ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE, "ifpStartDate","ifpEndDate", ConstantsUtils.ITEM_STATUS};

/** The Constant ITEM_DETAILS_COL_HEADER. */
public final String[] itemDetailsColHeader = new String[] {
	"check box", "item Familyplan No", "item Familyplan Name",
	ConstantsUtils.ITEMNO ,ConstantsUtils.ITEM_NAMES, "Uom", ConstantsUtils.PACK_SIZE,ConstantsUtils.ITEM_STARTDATE, ConstantsUtils.ITEM_ENDDATE, "ifp Start Date","ifp End Date", ConstantsUtils.ITEMSTATUS};



/** The Constant NEW_FORMULATION_SEARCH. */
public final Object[] newFormulationSearch = new Object[] {  ConstantsUtils.ITEM_ID,ConstantsUtils.ITEM_NO,
	ConstantsUtils.ITEM_NAME,ConstantsUtils.ITEM_STATUS, ConstantsUtils.ITEM_TYPE,ConstantsUtils.MANUFACTURED_ID, ConstantsUtils.ITEM_DESC,  ConstantsUtils.ITEM_CODE,
	ConstantsUtils.PACKAGE_SIZE, ConstantsUtils.UPPS, ConstantsUtils.ITEM_START_DATE, ConstantsUtils.ITEM_END_DATE,
	 "labelerCode", ConstantsUtils.FORM, ConstantsUtils.STRENGTH, ConstantsUtils.PRIMARY_UOM,
	ConstantsUtils.SECONDARY_UOM, ConstantsUtils.ITEM_CLASS, "pediatricExclusiveStartDate",
	"pediatricExclusiveEndDate", "clottingFactorStartDate",
	"clottingFactorEndDate" };

/** The Constant NEW_FORMULATION_HEADER. */
public final String[] newFormulationHeader = new String[] { "item Id",ConstantsUtils.ITEMS_NO,
ConstantsUtils.ITEM_NAMES,ConstantsUtils.ITEMSTATUS, "item Type","manufacturer Id", "item Desc",  "item Code",
ConstantsUtils.PACK_SIZE, ConstantsUtils.UPPS, ConstantsUtils.ITEM_STARTDATE, ConstantsUtils.ITEM_ENDDATE,
 "labeler Code", ConstantsUtils.FORM, ConstantsUtils.STRENGTH, "primary Uom",
"secondary Uom", "item Class", "pediatric Exclusive Start Date",
"pediatric Exclusive End Date", "clotting Factor Start Date",
"clotting Factor End Date" };
    private static UIUtils object;

    /**
     * Constructor
     */
    private UIUtils() {
        //Empty
    }

    public static UIUtils getInstance() {
        if (object == null) {
            object = new UIUtils();
        }
        return object;
    }
}
