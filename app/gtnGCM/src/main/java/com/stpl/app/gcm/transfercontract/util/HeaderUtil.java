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

    public final Object[] contractSearchColumn  = {Constants.CHECK, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "marketTypeValue", Constants.START_DATE, Constants.END_DATE, "companyFamilyPlan", "itemFamilyPlan", "priceSchedule", "rebateSchedule"};
    public final String[] contractSearchHeader  = {"", "Contract Holder", "Contract No", "Contract Name", "Market Type", Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, "Company Family Plan", "Item FamilyPlan", Constants.PRICE_SCHEDULE, "Rebate Schedule"};
    public final Object[] transforFromColumn  = {"category", "id", Constants.NUMBER_PROPERTY, "name"};
    public final String[] transforFromHeader  = {"Category", "ID", Constants.NUMBER_LABEL, "Name"};
    public final Object[] transforToColumn  = {"category", "id", Constants.NUMBER_PROPERTY, "name", "transferId", "transferNumber", "transferName"};
    public final String[] transforToHeader  = {"Category", "ID", Constants.NUMBER_LABEL, "Name", "Transfer ID", "Transfer No.", "Transfer Name"};
    public final Object[] componentDetailsItemColumn  = {Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE};
    public final String[] componentDetailsItemHeader  = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER};
    public final Object[] componentDetailsPsColumn  = {Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE, "priceType", "pricePlanNo", "pricePlanName", "priceProtectionStatus",
        "priceProtectionStartDate", "priceProtectionEndDate", "priceProtectionPriceType", "priceToleranceInterval", "priceToleranceFrequency",
        "priceToleranceType", "maxIncrementalChange", "priceTolerance", "resetEligible", "resetType", "resetDate",
        "resetInterval", "resetFrequency", Constants.ATTACHED_DATE_PROPERTY};
    public final String[] componentDetailsPsHeader  = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, "Price Type", "Price Plan No", "Price Plan Name", "Price Protection Status",
        "Price Protection Start Date", "Price Protection End Date", "Price Protection Price Type", "Price Tolerance Interval", "Price Tolerance Frequency",
        "Price Tolerance Type", "Max Incremental Change", "Price Tolerance", "Reset Eligible", "Reset Type", "Reset Date",
        "Reset Interval", "Reset Frequency", Constants.ATTACHED_DATE_FIELD};
    public final Object[] componentDetailsRsColumn  = {Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE, "formulaType", "formulaID", "formulaName", "rebatePlanID",
        "rebatePlanName", "rebateAmount", "bundleNo", Constants.ATTACHED_DATE_PROPERTY};
    public final String[] componentDetailsRsHeader  = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, "Formula Type", "Formula ID", "Formula Name", "Rebate Plan ID",
        "RebatePlan Name", "Rebate Amount", "Bundle No", Constants.ATTACHED_DATE_FIELD};
    public final Object[] componentDetailsCompanyColumn  = {"tradingPartnerNo", "tradingPartnerName", "tpContractNo", Constants.START_DATE, Constants.END_DATE, Constants.STATUS_S, "tradeClass", Constants.ATTACHED_DATE_PROPERTY};
    public final String[] componentDetailsCompanyHeader  = {"Trading Partner No", "Trading Partner Name", "TP Contract No", Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, Constants.STATUS_FIELD, Constants.TRADECLASS, Constants.ATTACHED_DATE_FIELD};
    public final String MATCH_PROPERTY = "match";
    public final Object[] cfpDetailsColumn  = {Constants.CHECK, "id", Constants.NUMBER_PROPERTY, "name", MATCH_PROPERTY, "add", "remove"};
    public final String MATCH_LABEL = "Match";
    public final String[] cfpDetailsHeader  = {"", "ID", Constants.NUMBER_LABEL, "Name", MATCH_LABEL, "Add", "Remove Sales"};
    public final Object[] ifpDetailsColumn  = {Constants.CHECK, "id", Constants.NUMBER_PROPERTY, "name", MATCH_PROPERTY, "add"};
    public final String[] ifpDetailsHeader  = {"", "ID", Constants.NUMBER_LABEL, "Name", MATCH_LABEL, "Add"};
    public final Object[] psDetailsColumn  = {Constants.CHECK, "id", Constants.NUMBER_PROPERTY, "name", MATCH_PROPERTY, "add"};
    public final String[] psDetailsHeader  = {"", "ID", Constants.NUMBER_LABEL, "Name", MATCH_LABEL, "Add"};
    public final Object[] rsDetailsColumn  = {Constants.CHECK, "id", Constants.NUMBER_PROPERTY, "name", MATCH_PROPERTY, "add", "remove"};
    public final String[] rsDetailsHeader  = {"", "ID", Constants.NUMBER_LABEL, "Name", MATCH_LABEL, "Add", "Remove Rebate"};

    public final Object[] adComponentDetailsCompanyColumn  = {"companyNo", "companyName", Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE};
    public final String[] adComponentDetailsCompanyHeader  = {Constants.COMPANYNO, Constants.COMPANYNAME, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER};
    public final Object[] adComponentDetailsPsColumn  = {Constants.ITEM_NO_PROPERTY, Constants.ITEM_NAME_PROPERTY, "therapyClass", Constants.BRAND_PROPERTY, Constants.STATUS_S, Constants.START_DATE, Constants.END_DATE};
    public final String[] aDComponentDetailsPsHeader  = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, Constants.STATUS_FIELD, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER};
    private static HeaderUtil object;
    /**
     * Constructor
     */
    private HeaderUtil() {
        /*
            Constructor
        */
    }

    public static HeaderUtil getInstance() {
        if (object == null) {
            object = new HeaderUtil();
        }
        return object;
    }
}
