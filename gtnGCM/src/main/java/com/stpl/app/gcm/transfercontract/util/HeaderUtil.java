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

    public static final Object[] CONTRACT_SEARCH_COLUMN = {"check", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "marketTypeValue", Constants.START_DATE, Constants.END_DATE, "companyFamilyPlan", "itemFamilyPlan", "priceSchedule", "rebateSchedule"};
    public static final String[] CONTRACT_SEARCH_HEADER = {"", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Company Family Plan", "Item FamilyPlan", Constants.PRICE_SCHEDULE, "Rebate Schedule"};
    public static final Object[] TRANSFOR_FROM_COLUMN = {"category", "id", "number", "name"};
    public static final String[] TRANSFOR_FROM_HEADER = {"Category", "ID", "Number", "Name"};
    public static final Object[] TRANSFOR_TO_COLUMN = {"category", "id", "number", "name", "transferId", "transferNumber", "transferName"};
    public static final String[] TRANSFOR_TO_HEADER = {"Category", "ID", "Number", "Name", "Transfer ID", "Transfer No.", "Transfer Name"};
    public static final Object[] COMPONENT_DETAILS_ITEM_COLUMN = {"itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE};
    public static final String[] COMPONENT_DETAILS_ITEM_HEADER = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date"};
    public static final Object[] COMPONENT_DETAILS_PS_COLUMN = {"itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE, "priceType", "pricePlanNo", "pricePlanName", "priceProtectionStatus",
        "priceProtectionStartDate", "priceProtectionEndDate", "priceProtectionPriceType", "priceToleranceInterval", "priceToleranceFrequency",
        "priceToleranceType", "maxIncrementalChange", "priceTolerance", "resetEligible", "resetType", "resetDate",
        "resetInterval", "resetFrequency", "attachedDate"};
    public static final String[] COMPONENT_DETAILS_PS_HEADER = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date", "Price Type", "Price Plan No", "Price Plan Name", "Price Protection Status",
        "Price Protection Start Date", "Price Protection End Date", "Price Protection Price Type", "Price Tolerance Interval", "Price Tolerance Frequency",
        "Price Tolerance Type", "Max Incremental Change", "Price Tolerance", "Reset Eligible", "Reset Type", "Reset Date",
        "Reset Interval", "Reset Frequency", "Attached Date"};
    public static final Object[] COMPONENT_DETAILS_RS_COLUMN = {"itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE, "formulaType", "formulaID", "formulaName", "rebatePlanID",
        "rebatePlanName", "rebateAmount", "bundleNo", "attachedDate"};
    public static final String[] COMPONENT_DETAILS_RS_HEADER = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date", "Formula Type", "Formula ID", "Formula Name", "Rebate Plan ID",
        "RebatePlan Name", "Rebate Amount", "Bundle No", "Attached Date"};
    public static final Object[] COMPONENT_DETAILS_COMPANY_COLUMN = {"tradingPartnerNo", "tradingPartnerName", "tpContractNo", Constants.START_DATE, Constants.END_DATE, "status", "tradeClass", "attachedDate"};
    public static final String[] COMPONENT_DETAILS_COMPANY_HEADER = {"Trading Partner No", "Trading Partner Name", "TP Contract No", "Start Date", "End Date", "Status", Constants.TRADECLASS, "Attached Date"};
    public static final Object[] CFP_DETAILS_COLUMN = {"check", "id", "number", "name", "match", "add", "remove"};
    public static final String[] CFP_DETAILS_HEADER = {"", "ID", "Number", "Name", "Match", "Add", "Remove Sales"};
    public static final Object[] IFP_DETAILS_COLUMN = {"check", "id", "number", "name", "match", "add"};
    public static final String[] IFP_DETAILS_HEADER = {"", "ID", "Number", "Name", "Match", "Add"};
    public static final Object[] PS_DETAILS_COLUMN = {"check", "id", "number", "name", "match", "add"};
    public static final String[] PS_DETAILS_HEADER = {"", "ID", "Number", "Name", "Match", "Add"};
    public static final Object[] RS_DETAILS_COLUMN = {"check", "id", "number", "name", "match", "add", "remove"};
    public static final String[] RS_DETAILS_HEADER = {"", "ID", "Number", "Name", "Match", "Add", "Remove Rebate"};

    public static final Object[] AD_COMPONENT_DETAILS_COMPANY_COLUMN = {"companyNo", "companyName", "status", Constants.START_DATE, Constants.END_DATE};
    public static final String[] AD_COMPONENT_DETAILS_COMPANY_HEADER = {Constants.COMPANYNO, Constants.COMPANYNAME, "Status", "Start Date", "End Date"};
    public static final Object[] AD_COMPONENT_DETAILS_PS_COLUMN = {"itemNo", "itemName", "therapyClass", "brand", "status", Constants.START_DATE, Constants.END_DATE};
    public static final String[] AD_COMPONENT_DETAILS_PS_HEADER = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date"};

}
