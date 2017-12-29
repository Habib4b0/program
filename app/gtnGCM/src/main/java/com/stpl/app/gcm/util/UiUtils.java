/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import java.util.ResourceBundle;
import com.vaadin.ui.AbstractOrderedLayout;

/**
 *
 * @author alok.v
 */
public class UiUtils {

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
			Constants.START_DATE, Constants.END_DATE, "companyStatusValue", "tradeClass",
			Constants.ATTACHED_DATE_PROPERTY };
	public final Object ccCompanyDetailsColumns[] = new Object[] { "tpNo", "companyName", "companyNo",
			"companyStartDate", "companyEndDate", "companyStatus", "tradeClass", Constants.ATTACHED_DATE_PROPERTY };
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
		layout.setMargin(Boolean.valueOf(resourceBundle.getString("layout_margin")));
		layout.setSpacing(Boolean.valueOf(resourceBundle.getString("layout_spacing")));
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
