package com.stpl.gtn.gtn2o.ui.module.priceschedule.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

public class GtnFrameworkPSConstants {
	private GtnFrameworkPSConstants() {
		super();
	}

	private static final List<String> PRICEE_PRODETECTION_TEXT_FIELD_PROPERTIES = Arrays.asList(
			GtnFrameworkCommonConstants.ITEM_ID, GtnFrameworkCommonConstants.ITEM_NO,
			GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkCommonConstants.BRAND_NAME, "psNEP", "psDetailsPriceTol",
			"psMaxIncrementalChange");
	private static final List<String> PRICEE_PRODETECTION_CUSTOM_TEXTFIELD_PROPERTIES = Arrays.asList("psNEPFormula",
			"psNetBasePriceFormulaId", "psNetBSubseqPriceFormulaId", "psNetResetPriceFormulaId",
			"psNetResetPriceFormulaName");
	private static final List<String> PRICE_PROTECTION_DDLB_PROPERTIES=Arrays.asList("psPPStatus","psPPPriceType","psBasePriceType",
			"psNetBasePrice","psSubseqPeriodPriceType","psNetBSubseqPeriodPrice","psToleranceInterval", "psToleranceFreq", "psToleranceType",
			"psResetEligible", "psResetType","psResetInterval", "psResetFrequency", "psResetPriceType", "psNetResetPriceType","psNetPriceType");
	
	private static final List<String> PRICEE_PRODETECTION_DATE_FIELD_PROPERTIES = Arrays.asList("psPPStartDate",
			"psPPEndDate", "psResetDate", GtnFrameworkCommonConstants.PS_ATTACHED_DATE);

	private static final List<String> PRICEE_PRODETECTION_NON_EDITABLEFIELD_PROPERTIES = Arrays.asList(
			GtnFrameworkCommonConstants.ITEM_ID, GtnFrameworkCommonConstants.ITEM_NO,
			GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkCommonConstants.BRAND_NAME,
			GtnFrameworkCommonConstants.PS_ATTACHED_DATE);

	private static final List<String> PRICEE_PRODETECTION_EDITABLE_LIST = Arrays.asList(
			GtnFrameworkCommonConstants.CHECK_RECORD_ID, GtnFrameworkCommonConstants.ITEM_ID,
			GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonConstants.ITEM_NAME,
			GtnFrameworkCommonConstants.BRAND_NAME, "psPPStatus", "psPPStartDate", "psPPEndDate", "psPPPriceType",
			"psNEP", "psNEPFormula", "psBasePriceType", GtnFrameworkCommonConstants.PS_BASE_PRICE_ENTRY,
			"psNetBasePrice", "psNetBasePriceFormulaId", "psSubseqPeriodPriceType", "psNetBSubseqPeriodPrice",
			"psNetBSubseqPriceFormulaId", "psToleranceInterval", "psToleranceFreq", "psToleranceType",
			"psDetailsPriceTol", "psMaxIncrementalChange", "psResetEligible", "psResetType", "psResetDate",
			"psResetInterval", "psResetFrequency", "psResetPriceType", "psNetResetPriceType",
			"psNetResetPriceFormulaId", "psNetPriceType", "psNetResetPriceFormulaName",
			GtnFrameworkCommonConstants.PS_ATTACHED_DATE);

	private static final List<String> PRICING_TEXTFIELD_PROPERTIES_ARRAY = Arrays.asList(
			GtnFrameworkCommonConstants.ITEM_ID, GtnFrameworkCommonConstants.ITEM_NO,
			GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkCommonConstants.BRAND_NAME, "source",
			GtnFrameworkCommonConstants.PS_PRICE, GtnFrameworkCommonConstants.SUGGESTED_PRICE,
			GtnFrameworkCommonConstants.CREATED_BY);
	private static final List<String> PRICING_DATEFIELD_PROPERTIES_ARRAY = Arrays.asList(
			GtnFrameworkCommonConstants.CP_START_DATE, GtnFrameworkCommonConstants.CP_END_DATE, "psCreatedDate",
			GtnFrameworkCommonConstants.PS_ATTACHED_DATE);
	private static final List<String> PRICING_NON_EDITABFIELD_PROPERTIES_ARRAY = Arrays.asList(
			GtnFrameworkCommonConstants.CP_START_DATE, GtnFrameworkCommonConstants.CP_END_DATE,
			GtnFrameworkCommonConstants.SUGGESTED_PRICE, GtnFrameworkCommonConstants.PS_PRICE, "psStatus", "priceType",
			GtnFrameworkCommonConstants.CHECK_RECORD_ID);

	private static final List<String> PRICING_EDITABFIELD_PROPERTIES_ARRAY = Arrays.asList(
			GtnFrameworkCommonConstants.CHECK_RECORD_ID, GtnFrameworkCommonConstants.ITEM_ID,
			GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonConstants.ITEM_NAME,
			GtnFrameworkCommonConstants.BRAND_NAME, "source", GtnFrameworkCommonConstants.PS_PRICE,
			GtnFrameworkCommonConstants.SUGGESTED_PRICE, GtnFrameworkCommonConstants.CREATED_BY,
			GtnFrameworkCommonConstants.CP_START_DATE, GtnFrameworkCommonConstants.CP_END_DATE, "psCreatedDate",
			GtnFrameworkCommonConstants.PS_ATTACHED_DATE, "psStatus", "priceType");
	
       private static final String[] FIELDS = new String[] { "priceScheduleId1", "priceScheduleNo1", "priceScheduleName1",
				"priceScheduleStatus1", "priceScheduleStartDate" };
        private static final String[] CUSTOM_FILTER_PROPERTY_IDS = { GtnFrameworkCommonConstants.IFP_STATUS, GtnFrameworkCommonConstants.PROPERTY_IFP_TYPE,
				GtnFrameworkCommonConstants.PROPERTY_IFP_CATEGORY };
        private static final String[] CUSTOM_FILER_LIST_NAME = { "STATUS", "IFP_TYPE", "IFP_CATEGORY" };

       private static final Object[] ADD_ENABLE_FIELD = new String[] { GtnFrameworkCommonConstants.NOTES_TAB,
				GtnFrameworkCommonConstants.CFP_LEFT_RESULT_TABLE, GtnFrameworkCommonConstants.CFP_RIGHT_RESULT_TABLE,
				"psPriceProtectionTabResultDataTable", "psPricingTabResultDataTable",
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_END_DATE,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_START_DATE, GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE1,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_DESIGNATION,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS1, GtnFrameworkCommonConstants.CREATED_BY,
				GtnFrameworkCommonConstants.MODIFIED_BY, GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID_TOP,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO_TOP, GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME_TOP,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID1, GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO1,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME1, GtnFrameworkCommonConstants.PRICE_PROTECTION_TAB,
				GtnFrameworkCommonConstants.CFP_LEFT_RESULT_TABLE, GtnFrameworkCommonConstants.CFP_RIGHT_RESULT_TABLE,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_SEARCH_VALUE,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_GTN_SEARCH_01,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_MOVE_RIGHT_BUTTONS,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_MOVE_LEFT_BUTTONS, "PricingTab",
				GtnFrameworkCommonConstants.PRICE_PROTECTION_TAB };
        
       private static final Object[] ADD_DISABLE_FIELD = new Object[] { GtnFrameworkCommonConstants.CREATED_BY,
				GtnFrameworkCommonConstants.MODIFIED_BY, GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID_TOP,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO_TOP, GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME_TOP,
				GtnFrameworkCommonConstants.PARENT_PRICE_SCHEDULE_NAME, GtnFrameworkCommonConstants.CREATED_DATE,
				GtnFrameworkCommonConstants.MODIFIED_DATE, GtnFrameworkCommonConstants.PARENT_PRICE_SCHEDULE_ID,
				GtnFrameworkCommonConstants.PS_PRICING_TAB_TAB_POPULATE_BUTTON,
				GtnFrameworkCommonConstants.PS_PRICING_TAB_TAB_POPULATE_ALL_BUTTON,
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_POPULATE_BUTTON,
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_POPULATE_ALL_BUTTON };
private static final Object[] ADD_VIEW_DISABLE_FIELD = new Object[] { GtnFrameworkCommonConstants.NOTES_TAB,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_END_DATE,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_START_DATE, "priceScheduleTradeClass",
				"priceScheduleCategory", GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE1,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_DESIGNATION,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS1, GtnFrameworkCommonConstants.CREATED_BY,
				GtnFrameworkCommonConstants.MODIFIED_BY, GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID_TOP,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO_TOP, GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME_TOP,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID1, GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO1,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME1,
				GtnFrameworkCommonConstants.PARENT_PRICE_SCHEDULE_NAME, GtnFrameworkCommonConstants.CREATED_DATE,
				GtnFrameworkCommonConstants.MODIFIED_DATE,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_MOVE_RIGHT_BUTTONS,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_MOVE_LEFT_BUTTONS };
private static final Object[] ADD_VIEW_VISIBLE_FIELD = new String[] { GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ADD_VIEW_ADD_RESET_BUTTON,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ADD_VIEW_A_ADD_DELETE_BUTTON, "itemOrIfpOption",
				"psItemAdditionSearchField", GtnFrameworkCommonConstants.PS_ITEM_ADDITION_SEARCH_VALUE,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_GTN_SEARCH_01, "cfpPricingTabMassCheck",
				"psPricingTabTabMassField", GtnFrameworkCommonConstants.PS_PRICING_TAB_TAB_POPULATE_BUTTON,
				GtnFrameworkCommonConstants.PS_PRICING_TAB_TAB_POPULATE_ALL_BUTTON, "cfpPriceProtectionMassCheck",
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_MASS_FIELD,
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_MASS_FIELD,
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_POPULATE_BUTTON,
				"psPriceProtectionTabPopulateAllButton","massUpdatePanel","pricingMassUpdatePanel"};
private static final Object[] ADD_EDIT_ENABLE_FIELD = new String[] { GtnFrameworkCommonConstants.NOTES_TAB,
				GtnFrameworkCommonConstants.CFP_LEFT_RESULT_TABLE, GtnFrameworkCommonConstants.CFP_RIGHT_RESULT_TABLE,
				"psPriceProtectionTabResultDataTable", "psPricingTabResultDataTable",
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_END_DATE,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_START_DATE, "priceScheduleTradeClass",
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE1,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_DESIGNATION,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS1, GtnFrameworkCommonConstants.CREATED_BY,
				GtnFrameworkCommonConstants.MODIFIED_BY, GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID_TOP,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO_TOP, GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME_TOP,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID1, GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO1,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME1, GtnFrameworkCommonConstants.PRICE_PROTECTION_TAB,
				GtnFrameworkCommonConstants.CFP_LEFT_RESULT_TABLE, GtnFrameworkCommonConstants.CFP_RIGHT_RESULT_TABLE,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_SEARCH_VALUE,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_GTN_SEARCH_01,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_MOVE_RIGHT_BUTTONS,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_MOVE_LEFT_BUTTONS, "PricingTab",
				GtnFrameworkCommonConstants.PRICE_PROTECTION_TAB };
private static final Object[] ADD_EDIT_DISABLE_FIELD = new Object[] { GtnFrameworkCommonConstants.CREATED_BY,
				GtnFrameworkCommonConstants.MODIFIED_BY, GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID_TOP,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO_TOP, GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME_TOP,
				GtnFrameworkCommonConstants.CREATED_DATE, GtnFrameworkCommonConstants.MODIFIED_DATE,
				GtnFrameworkCommonConstants.PARENT_PRICE_SCHEDULE_NAME, "psPricingTabTabMassField",
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_MASS_FIELD,
				GtnFrameworkCommonConstants.PS_PRICING_TAB_TAB_POPULATE_BUTTON,
				GtnFrameworkCommonConstants.PS_PRICING_TAB_TAB_POPULATE_ALL_BUTTON,
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_POPULATE_BUTTON,
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_POPULATE_ALL_BUTTON };
private static final String[] ADD_EDIT_VISIBLE_FIELD = new String[] { GtnFrameworkCommonConstants.PRICE_SCHEDULE_ADD_VIEW_A_ADD_DELETE_BUTTON,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ADD_VIEW_ADD_RESET_BUTTON,
				GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON };

    public static String[] getAddEditVisibleField() {
        return ADD_EDIT_VISIBLE_FIELD.clone();
    }

    public static Object[] getAddEditDisableField() {
        return ADD_EDIT_DISABLE_FIELD.clone();
    }



    public static Object[] getAddEditEnableField() {
        return ADD_EDIT_ENABLE_FIELD.clone();
    }


    public static Object[] getAddViewVisibleField() {
        return ADD_VIEW_VISIBLE_FIELD.clone();
    }

    public static Object[] getAddViewDisableField() {
        return ADD_VIEW_DISABLE_FIELD.clone();
    }

    public static Object[] getAddDisableField() {
        return ADD_DISABLE_FIELD.clone();
    }
        
       
    public static String[] getCustomFilterPropertyIds() {
        return CUSTOM_FILTER_PROPERTY_IDS.clone();
    }

    public static String[] getCustomFilterListName() {
        return CUSTOM_FILER_LIST_NAME.clone();
    }

    public static Object[] getAddEnableField() {
        return ADD_ENABLE_FIELD.clone();
    }

    

    public static String[] getFields() {
        return FIELDS.clone();
    }
        
        public static final String GTN_CONTRACT_HEADER_PS_ID_VALIDATION = "Please enter different Price Schedule ID since the Price Schedule  ID already exists";
	public static final String GTN_CONTRACT_HEADER_PS_NO_VALIDATION = "Please enter different Price Schedule No since the Price Schedule  No already exists";
	public static final String PS_DATE_EQUAL_VALIDATION = "PS Start date and PS End date should not be equal";
	public static final String PS_DATE_LESS_THAN_VALIDATION = "PS End date should be greater than PS Start date";
    public static final String PS_PRICEPROTECTION_RESULTPANEL= "psPriceProtectionTabResultPanel";
    public static final String PS_PRICINGTAB_RESULTPANEL= "psPricingTabTabResultPanel";
    public static final String PS_PRICINGTAB_RESULT_LAYOUT="psPricingTabTabResultLayout";
	private static final String[] PRICE_PROTECTION_HEADER = { "", "Item ID", "Item No", "Item Name", "Brand",

			"Price Protection Status", "Price Protection Start Date", "Price Protection End Date",  
			"Measurement Price", "NEP", "NEP Formula", GtnFrameworkCommonConstants.BASE_PRICE_TYPE,
			"Baseline WAC", "Baseline Net WAC", "Net Baseline WAC Formula",
			"Subsequent Period Price Type", "Net Subsequent Period Price", "Net Subsequent Period Price Formula",
			"Price Tolerance Interval", "Price Tolerance Frequency", "Price Tolerance Type", "Price Tolerance",
			"Max Incremental Change", "Reset Eligible", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency",
			"Reset Price Type", "Net Reset Price Type", "Net Reset Price Formula", "Net Price Type",
			"Net Price Type Formula", " Attached Date"};


	private static final String[] PRICE_PROTECTION_VIEW_HEADER = { "Item Id", "Item No", "Item Name", "Brand",
			"Price Protection Status", "Price Protection Start Date", "Price Protection End Date",
			"Price Protection Price Type", "NEP", "NEP Formula", GtnFrameworkCommonConstants.BASE_PRICE_TYPE,
			"Baseline WAC", "Baseline Net WAC", "Net Baseline WAC Formula", "Subsequent Period Price Type",
			"Net Subsequent Period Price", "Net Subsequent Period Price Formula", "Price Tolerance Interval",
			"Price Tolerance Frequency", "Price Tolerance Type", "Price Tolerance", "Max Incremental Change",
			"Reset Eligible", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency", "Reset Price Type",
			"Net Reset Price Type", "Net Reset Price Formula", "Net Price Type", "Net Price Type Formula",
			" Attached Date"};

	private static final List<String> PRICE_PROTECTION_TEXT_RIGHT_JUSTIFIED=Arrays.asList("v-textfield-txtRightAlign");
	
	private static final String[] PRICE_PROTECTION_COLUMN_ALIGNMENT={"RIGHT"};
	
	private static final Object[] PRICE_PROTECTION_COLUMN_ALIGNMENT_HEADER={GtnFrameworkCommonConstants.PS_DETAILS_PRICE_TOL};
	
	public static List<String> getPriceProtectionTextFieldProperties() {
		return Collections.unmodifiableList(PRICEE_PRODETECTION_TEXT_FIELD_PROPERTIES);
	}

	public static List<String> getPriceProtectionCustomTextFieldProperties() {
		return Collections.unmodifiableList(PRICEE_PRODETECTION_CUSTOM_TEXTFIELD_PROPERTIES);
	}

	public static List<String> getPricePrtectionDateFieldProperties() {
		return Collections.unmodifiableList(PRICEE_PRODETECTION_DATE_FIELD_PROPERTIES);
	}

	public static List<String> getPriceProtectionNonEditableFieldProperties() {
		return Collections.unmodifiableList(PRICEE_PRODETECTION_NON_EDITABLEFIELD_PROPERTIES);
	}

	public static List<String> getPriceProtectionEditableList() {
		return Collections.unmodifiableList(PRICEE_PRODETECTION_EDITABLE_LIST);
	}

	public static List<String> getPricingTextFieldPropertiesArray() {
		return Collections.unmodifiableList(PRICING_TEXTFIELD_PROPERTIES_ARRAY);
	}

	public static List<String> getPricingDateFieldPropertiesArray() {
		return Collections.unmodifiableList(PRICING_DATEFIELD_PROPERTIES_ARRAY);
	}

	public static List<String> getPricingNonEditableFieldPropertiesArray() {
		return Collections.unmodifiableList(PRICING_NON_EDITABFIELD_PROPERTIES_ARRAY);
	}

	public static List<String> getPricingEditableFieldPropertiesArray() {
		return Collections.unmodifiableList(PRICING_EDITABFIELD_PROPERTIES_ARRAY);
	}

	public static List<String> getPriceProtectionDdlbFieldPropertiesArray() {
		return Collections.unmodifiableList(PRICE_PROTECTION_DDLB_PROPERTIES);
	}
	
	public static List<String> getPriceProtectionTextRightJustified() {
		return Collections.unmodifiableList(PRICE_PROTECTION_TEXT_RIGHT_JUSTIFIED);
	}
	
	public static String[] getPriceProtectionHeader() {
		return PRICE_PROTECTION_HEADER.clone();
	}

	public static String[] getPriceProtectionViewHeader() {
		return PRICE_PROTECTION_VIEW_HEADER.clone();
	}

	
	public static String[] getPriceProtectionColumnAlignment() {
		return PRICE_PROTECTION_COLUMN_ALIGNMENT.clone();
	}
	
	public static Object[] getPriceProtectionColumnAlignmentHeader() {
		return PRICE_PROTECTION_COLUMN_ALIGNMENT_HEADER.clone();
	}

	public static void loadPsPriceProtectioncomboBoxFieldMap(Map<String, String> psPriceProtectioncomboBoxFieldMap) {
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.PRICE_PROTECTION_STATUS, "STATUS");
		psPriceProtectioncomboBoxFieldMap.put("Measurement Price", GtnFrameworkCommonConstants.ITEM_PRICING_QUALIFIER);
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.PRICE_TOLERANCE_INTERVAL,
				"PRICE_TOLERANCE_INTERVAL");
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.PRICE_TOLERANCE_FREQUENCY,
				"PRICE_TOLERANCE_FREQUENCY");
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.PRICE_TOLERANCE_TYPE, "PRICE_TOLERANCE_TYPE");
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.RESET_ELIGIBLE,
				GtnFrameworkCommonConstants.LOCKED_STATUS);
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.RESET_TYPE, "RESET_TYPE");
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.RESET_INTERVAL, "PRICE_TOLERANCE_INTERVAL");
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.RESET_FREQUENCY, "PRICE_TOLERANCE_FREQUENCY");
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.NET_PRICE_TYPE,
				GtnFrameworkCommonConstants.LOCKED_STATUS);
		psPriceProtectioncomboBoxFieldMap.put("Price Type", "pricingCodeQualifierName");

		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.BASELINE_NET_WAC,
				GtnFrameworkCommonConstants.LOCKED_STATUS);
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.SUBSEQUENT_PERIOD_PRICE_TYPE_HEADER,
				GtnFrameworkCommonConstants.ITEM_PRICING_QUALIFIER);
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.NET_SUBSEQUENT_PERIOD_PRICE_HEADER,
				GtnFrameworkCommonConstants.LOCKED_STATUS);
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.RESET_PRICE_TYPE_HEADER,
				GtnFrameworkCommonConstants.ITEM_PRICING_QUALIFIER);
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.NET_RESET_PRICE_TYPE_HEADER,
				GtnFrameworkCommonConstants.LOCKED_STATUS);
	}
}
