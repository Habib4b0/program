/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.util;

import com.stpl.app.gcm.util.Constants;

/**
 *
 * @author Harlin
 */
public class HeaderUtil {

    private static final Object[] CONTRACT_SEARCH_COLUMN  = {Constants.CHECK, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "marketTypeValue", Constants.START_DATE, Constants.END_DATE, "companyFamilyPlan", "itemFamilyPlan", "priceSchedule", "rebateSchedule"};
    private static final String[] CONTRACT_SEARCH_HEADER  = {"", "Contract Holder", "Contract No", "Contract Name", "Market Type", Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, "Company Family Plan", "Item FamilyPlan", Constants.PRICE_SCHEDULE, "Rebate Schedule"};
    private static final Object[] TRANSFER_FROM_COLUMN  = {"category", "id", Constants.NUMBER_PROPERTY, "name"};
    private static final String[] TRANSFER_FROM_HEADER  = {"Category", "ID", Constants.NUMBER_LABEL, "Name"};
    private static final Object[] TRANSFER_TO_COLUMN  = {"category", "id", Constants.NUMBER_PROPERTY, "name", "transferId", "transferNumber", "transferName"};
    private static final String[] TRANSFER_TO_HEADER  = {"Category", "ID", Constants.NUMBER_LABEL, "Name", "Transfer ID", "Transfer No.", "Transfer Name"};
    private static final Object[] COMPONENT_DETAILS_ITEM_COLUMN  = {Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE};
    private static final String[] COMPONENT_DETAILS_ITEM_HEADER  = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER};
    private static final Object[] COMPONENT_DETAILS_PS_COLUMN  = {Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE, "priceType", "pricePlanNo", "pricePlanName", "priceProtectionStatus",
        "priceProtectionStartDate", "priceProtectionEndDate", "priceProtectionPriceType", "priceToleranceInterval", "priceToleranceFrequency",
        "priceToleranceType", "maxIncrementalChange", "priceTolerance", "resetEligible", "resetType", "resetDate",
        "resetInterval", "resetFrequency", Constants.ATTACHED_DATE_PROPERTY};
    private static final String[] COMPONENT_DETAILS_PS_HEADER  = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, "Price Type", "Price Plan No", "Price Plan Name", "Price Protection Status",
        "Price Protection Start Date", "Price Protection End Date", "Price Protection Price Type", "Price Tolerance Interval", "Price Tolerance Frequency",
        "Price Tolerance Type", "Max Incremental Change", "Price Tolerance", "Reset Eligible", "Reset Type", "Reset Date",
        "Reset Interval", "Reset Frequency", Constants.ATTACHED_DATE_FIELD};
    private static final Object[] COMPONENT_DETAILS_RS_COLUMN  = {Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE, "formulaType", "formulaID", "formulaName", "rebatePlanID",
        "rebatePlanName", "rebateAmount", "bundleNo", Constants.ATTACHED_DATE_PROPERTY};
    private static final String[] COMPONENT_DETAILS_RS_HEADER  = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, "Formula Type", "Formula ID", "Formula Name", "Rebate Plan ID",
        "RebatePlan Name", "Rebate Amount", "Bundle No", Constants.ATTACHED_DATE_FIELD};
    private static final Object[] COMPONENT_DETAILS_COMPANY_COLUMN  = {"tradingPartnerNo", "tradingPartnerName", "tpContractNo", Constants.START_DATE, Constants.END_DATE, Constants.STATUS_S, "tradeClass", Constants.ATTACHED_DATE_PROPERTY};
    private static final String[] COMPONENT_DETAILS_COMPANY_HEADER  = {"Trading Partner No", "Trading Partner Name", "TP Contract No", Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, Constants.STATUS_FIELD, Constants.TRADECLASS, Constants.ATTACHED_DATE_FIELD};
    public static final String MATCH_PROPERTY = "match";
    private static final Object[] CFP_DETAILS_COLUMN  = {Constants.CHECK, "id", Constants.NUMBER_PROPERTY, "name", MATCH_PROPERTY, "add", "remove"};
    public static final String MATCH_LABEL = "Match";
    private static final String[] CFP_DETAILS_HEADER  = {"", "ID", Constants.NUMBER_LABEL, "Name", MATCH_LABEL, "Add", "Remove Sales"};
    private static final Object[] IFP_DETAILS_COLUMN  = {Constants.CHECK, "id", Constants.NUMBER_PROPERTY, "name", MATCH_PROPERTY, "add"};
    private static final String[] IFP_DETAILS_HEADER  = {"", "ID", Constants.NUMBER_LABEL, "Name", MATCH_LABEL, "Add"};
    private static final Object[] PS_DETAILS_COLUMN  = {Constants.CHECK, "id", Constants.NUMBER_PROPERTY, "name", MATCH_PROPERTY, "add"};
    private static final String[] PS_DETAILS_HEADER  = {"", "ID", Constants.NUMBER_LABEL, "Name", MATCH_LABEL, "Add"};
    private static final Object[] RS_DETAILS_COLUMN  = {Constants.CHECK, "id", Constants.NUMBER_PROPERTY, "name", MATCH_PROPERTY, "add", "remove"};
    private static final String[] RS_DETAILS_HEADER  = {"", "ID", Constants.NUMBER_LABEL, "Name", MATCH_LABEL, "Add", "Remove Rebate"};

    private static final Object[] ADCOMPONENT_DETAILS_COMPANY_COLUMN  = {"companyNo", "companyName", Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE};
    private static final String[] ADCOMPONENT_DETAILS_COMPANY_HEADER  = {Constants.COMPANYNO, Constants.COMPANYNAME, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER};
    private static final Object[] ADCOMPONENT_DETAILS_PS_COLUMN  = {Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, "therapyClass", Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE};
    private static final String[] ADCOMPONENT_DETAILS_PS_HEADER  = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER};
    private static HeaderUtil object;
    /**
     * Constructor
     */
    
    public static Object[] getContractSearchColumn() {
        return CONTRACT_SEARCH_COLUMN.clone();
    }

    public static String[] getContractSearchHeader() {
        return CONTRACT_SEARCH_HEADER.clone();
    }

    public static Object[] getTransferFromColumn() {
        return TRANSFER_FROM_COLUMN.clone();
    }

    public static String[] getTransferFromHeader() {
        return TRANSFER_FROM_HEADER.clone();
    }

    public static Object[] getTransferToColumn() {
        return TRANSFER_TO_COLUMN.clone();
    }

    public static String[] getTransferToHeader() {
        return TRANSFER_TO_HEADER.clone();
    }

    public static Object[] getComponentDetailsItemColumn() {
        return COMPONENT_DETAILS_ITEM_COLUMN.clone();
    }

    public static String[] getComponentDetailsItemHeader() {
        return COMPONENT_DETAILS_ITEM_HEADER.clone();
    }

    public static Object[] getComponentDetailsPsColumn() {
        return COMPONENT_DETAILS_PS_COLUMN.clone();
    }

    public static String[] getComponentDetailsPsHeader() {
        return COMPONENT_DETAILS_PS_HEADER.clone();
    }

    public static Object[] getComponentDetailsRsColumn() {
        return COMPONENT_DETAILS_RS_COLUMN.clone();
    }

    public static String[] getComponentDetailsHsHeader() {
        return COMPONENT_DETAILS_RS_HEADER.clone();
    }

    public static Object[] getComponentDetailsCompanyColumn() {
        return COMPONENT_DETAILS_COMPANY_COLUMN.clone();
    }

    public static String[] getComponentDetailsCompanyHeader() {
        return COMPONENT_DETAILS_COMPANY_HEADER.clone();
    }

    public static Object[] getCfpDetailsColumn() {
        return CFP_DETAILS_COLUMN.clone();
    }
    public static String[] getCfpDetailsHeader() {
        return CFP_DETAILS_HEADER.clone();
    }
    public static Object[] getIfpDetailsColumn() {
        return IFP_DETAILS_COLUMN.clone();
    }
    public static String[] getIfpDetailsHeader() {
        return IFP_DETAILS_HEADER.clone();
    }
    public static Object[] getPsDetailsColumn() {
        return PS_DETAILS_COLUMN.clone();
    }

    public static String[] getPsDetailsHeader() {
        return PS_DETAILS_HEADER.clone();
    }
    public static Object[] getRsDetailsColumn() {
        return RS_DETAILS_COLUMN.clone();
    }

    public static String[] getRsDetailsHeader() {
        return RS_DETAILS_HEADER.clone();
    }

    public static Object[] getAdcomponentDetailsCompanyColumn() {
        return ADCOMPONENT_DETAILS_COMPANY_COLUMN.clone();
    }

    public static String[] getAdcomponentDetailsCompanyHeader() {
        return ADCOMPONENT_DETAILS_COMPANY_HEADER.clone();
    }

    public static Object[] getAdcomponentDetailsPsColumn() {
        return ADCOMPONENT_DETAILS_PS_COLUMN.clone();
    }

    public static String[] getAdcomponentDetailsPsHeader() {
        return ADCOMPONENT_DETAILS_PS_HEADER.clone();
    }
    
        /*
            Constructor
        */

    private HeaderUtil() {
    }

    public static HeaderUtil getInstance() {
        if (object == null) {
            object = new HeaderUtil();
        }
        return object;
    }
}
