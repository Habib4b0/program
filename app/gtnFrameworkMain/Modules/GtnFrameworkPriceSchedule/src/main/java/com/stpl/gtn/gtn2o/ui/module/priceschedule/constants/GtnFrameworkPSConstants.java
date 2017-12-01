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
			GtnFrameworkCommonConstants.PS_ATTACHED_DATE,"psToleranceTypeDes", "psBasePriceDate", "psBasePriceDdlb", "psBasePriceTypeDes");

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
	public static final String GTN_CONTRACT_HEADER_PS_ID_VALIDATION = "Please enter different Price Schedule ID since the Price Schedule  ID already exists";
	public static final String GTN_CONTRACT_HEADER_PS_NO_VALIDATION = "Please enter different Price Schedule No since the Price Schedule  No already exists";

	private static final String[] PRICE_PROTECTION_HEADER = { "", "Item Id", "Item No", "Item Name", "Brand",
			"Price Protection Status", "Price Protection Start Date", "Price Protection End Date",  
			"Measurement Price", "NEP", "NEP Formula", GtnFrameworkCommonConstants.BASE_PRICE_TYPE,
			"Baseline WAC", "Baseline Net WAC", "Net Base Price Formula",
			"Subsequent Period Price Type", "Net Subsequent Period Price", "NetSubsequent Price Formula",
			"Price Tolerance Interval", "Price Tolerance Frequency", "Price Tolerance Type", "Price Tolerance",
			"Max Incremental Change", "Reset Eligible", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency",
			"Reset Price Type", "Net Reset Price Type", "Net Reset Price Formula", "Net Price Type",
			"Net Price Type Formula", " AttachedDate", GtnFrameworkCommonConstants.PRICE_TOLERANCE_TYPE, GtnFrameworkCommonConstants.BASE_PRICE,
			GtnFrameworkCommonConstants.BASE_PRICE, GtnFrameworkCommonConstants.BASE_PRICE_TYPE };

	private static final String[] PRICE_PROTECTION_VIEW_HEADER = { "Item Id", "Item No", "Item Name", "Brand",
			"Price Protection Status", "Price Protection Start Date", "Price Protection End Date",
			"Price Protection Price Type", "NEP", "NEP Formula", GtnFrameworkCommonConstants.BASE_PRICE_TYPE,
			"Baseline WAC", "Baseline Net WAC", "Net Base Price Formula",
			"Subsequent Period Price Type", "Net Subsequent Period Price", "NetSubsequent Price Formula",
			"Price Tolerance Interval", "Price Tolerance Frequency", "Price Tolerance Type", "Price Tolerance",
			"Max Incremental Change", "Reset Eligible", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency",
			"Reset Price Type", "Net Reset Price Type", "Net Reset Price Formula", "Net Price Type",
			"Net Price Type Formula", " AttachedDate", GtnFrameworkCommonConstants.BASE_PRICE,
			GtnFrameworkCommonConstants.BASE_PRICE, GtnFrameworkCommonConstants.BASE_PRICE_TYPE };

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
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.PRICE_TOLERANCE_INTERVAL, "PRICE_TOLERANCE_INTERVAL");
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.PRICE_TOLERANCE_FREQUENCY, "PRICE_TOLERANCE_FREQUENCY");
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.PRICE_TOLERANCE_TYPE, "PRICE_TOLERANCE_TYPE");
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.RESET_ELIGIBLE, GtnFrameworkCommonConstants.LOCKED_STATUS);
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.RESET_TYPE, "RESET_TYPE");
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.RESET_INTERVAL, "PRICE_TOLERANCE_INTERVAL");
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.RESET_FREQUENCY, "PRICE_TOLERANCE_FREQUENCY");
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.NET_PRICE_TYPE, GtnFrameworkCommonConstants.LOCKED_STATUS);
		psPriceProtectioncomboBoxFieldMap.put("Price Type", "pricingCodeQualifierName");
                
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.BASELINE_NET_WAC, GtnFrameworkCommonConstants.LOCKED_STATUS);
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.SUBSEQUENT_PERIOD_PRICE_TYPE_HEADER, GtnFrameworkCommonConstants.ITEM_PRICING_QUALIFIER);
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.NET_SUBSEQUENT_PERIOD_PRICE_HEADER, GtnFrameworkCommonConstants.LOCKED_STATUS);
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.RESET_PRICE_TYPE_HEADER, GtnFrameworkCommonConstants.ITEM_PRICING_QUALIFIER);
		psPriceProtectioncomboBoxFieldMap.put(GtnFrameworkCommonConstants.NET_RESET_PRICE_TYPE_HEADER, GtnFrameworkCommonConstants.LOCKED_STATUS);
	}
}
