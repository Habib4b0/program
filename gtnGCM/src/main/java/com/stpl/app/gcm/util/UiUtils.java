/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import com.vaadin.ui.AbstractOrderedLayout;
import java.util.ResourceBundle;
import org.jboss.logging.Logger;

/**
 *
 * @author alok.v
 */
public class UiUtils {

    private static final Logger LOGGER = Logger.getLogger(UiUtils.class);
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
    public static final Object NEW_COMPANY_DETAILS_COLUMNS[] = new Object[]{
        "companyId", "companyName", "companyNo", Constants.START_DATE, Constants.END_DATE, "companyStatusValue", "tradeClass", "attachedDate"};
   public static final Object CC_COMPANY_DETAILS_COLUMNS[] = new Object[]{
        "tpNo", "companyName", "companyNo", "companyStartDate", "companyEndDate", "companyStatus", "tradeClass", "attachedDate"};
    public static final String NEW_COMPANY_DETAILS_HEADERS[] = new String[]{
        "Trading Partner No ", "Trading Partner Name", "Trading Partner Contract No", "Start Date", "End Date", "Status", Constants.TRADECLASS, "Attached Date"};
    public static final Object NEW_IFP_DETAILS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "brand", "itemStatus", Constants.START_DATE, Constants.END_DATE, "attachedDate"};
    
     public static final Object CC_IFP_DETAILS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "brand", "ifpStatus", Constants.IFP_START_DATE, Constants.IFP_END_DATE, "attachedDate"};
    public static final String NEW_IFP_DETAILS_HEADERS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date", "Attached Date"};
    public static final Object CC_PS_DETAILS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "brand", "ifpStatus", Constants.IFP_START_DATE, Constants.IFP_END_DATE, "priceType", "pricePlanNo", "pricePlanName", "priceProtectionStatus", "ppStartDate", "priceProtectionEndDate",
        "priceProtectionPriceType", "priceToleranceInterval", "priceToleranceFrequency", "priceToleranceType", "maxIncrementalChange", "priceTolerance", "reset", "eligibility", "resetType",
        "resetDate", "resetIntervel", "resetFrequency", "attachedDate"};
    public static final Object NEW_PS_DETAILS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "brand", "itemStatus", Constants.START_DATE, Constants.END_DATE, "priceType", "pricePlanNo", "pricePlanName", "priceProtectionStatus", "priceProtectionStartDate", "priceProtectionEndDate",
        "priceProtectionPriceType", "priceToleranceInterval", "priceToleranceFrequency", "priceToleranceType", "maxIncrementalChange", "priceTolerance", "reset", "eligibility", "resetType",
        "resetDate", "resetIntervel", "resetFrequency", "attachedDate"};
    public static final Object CC_RS_DETAILS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "brand", "ifpStatus", Constants.IFP_START_DATE, Constants.IFP_END_DATE, "formulaType", "formulaId", "formulaName", "rebatePlanId", "rebatePlanName", "rebateAmount", "bundleNo", "attachedDate"};
    public static final String NEW_PS_DETAILS_HEADERS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date", "Price Type", "Price Plan No", "Price Plan Name", "Price Protection Status", "Price Protection Start Date", "Price Protection End Date",
        "Price Protection Price Type", "Price Tolerance Interval", "Price Tolerance Frequency", "Price Tolerance Type", "Max Incremental Change", "Price Tolerance", "Reset", "Eligibility", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency", "Attached Date"};
    public static final Object NEW_RS_DETAILS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "brand", "itemStatus", Constants.START_DATE, Constants.END_DATE, "formulaType", "formulaId", "formulaName", "rebatePlanId", "rebatePlanName", "rebateAmount", "bundleNo", "attachedDate"};
    public static final String NEW_RS_DETAILS_HEADERS[] = new String[]{
        Constants.IFP_NO, Constants.IfpNAME, Constants.BRAND, "Status", "Start Date", "End Date", "Formula Type", "Formula ID", "Formula Name", "Rebate Plan ID", "Rebate Plan Name", "Rebate Amount", "Bundle No", "Attached Date"};

    public static AbstractOrderedLayout getLayout(Class<?> theClass) {
        resourceBundle = ResourceBundle.getBundle("configurations/default");
        AbstractOrderedLayout layout = null;
        try {
            layout = (AbstractOrderedLayout) Class.forName(theClass.getName()).newInstance();

            layout.setMargin(Boolean.valueOf(resourceBundle.getString("layout_margin")));
            layout.setSpacing(Boolean.valueOf(resourceBundle.getString("layout_spacing")));
            return layout;
        } catch (InstantiationException ex) {
            LOGGER.error(ex);
        } catch (IllegalAccessException ex) {
            LOGGER.error(ex);
        } catch (ClassNotFoundException ex) {
            LOGGER.error(ex);
        }
        return layout;
    }
}
