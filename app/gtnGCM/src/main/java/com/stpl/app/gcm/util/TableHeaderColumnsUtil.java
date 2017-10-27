/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

/**
 *
 * @author alok.v
 */
public class TableHeaderColumnsUtil {

    public final Object promoteTpResultsColumns [] = new Object[]{
        "companySystemId", "companyId", "companyNo", "companyName", "companyType", "companyCategory", "tradeClass",
        "address1", "address2", "city", "state", "zip"};
    /**
     * The Constant PROMOTE_TP_RESULTS_HEADERS.
     */
    public final String promoteTpResultsHeaders [] = new String[]{
        "System ID", Constants.COMPANY_ID, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYTYPE, Constants.COMPANYCATEGORY, Constants.TRADECLASS,
        "Address 1", "Address 2", "City", "State", "Zip"};
    /**
     * CURRENT_TRADING_PARTNER_COLUMNS
     */
    public final Object currentTradingPartnerColumns[] = new Object[]{
        "checkRecord", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, "rebateScheduleNo",
        "rebateScheduleName", "rarCategory", "status", "companyStartDate", "companyEndDate"};
    /**
     * The Constant CURRENT_TRADING_PARTNER_HEADERS.
     */
    public final String currentTradingPartnerHeaders[] = new String[]{
        "", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Rebate Schedule No",
        "Rebate Schedule Name", "RAR Category", "Status", "Company Start Date", "Company End Date"};

    public final Object chHolderVisibleColumn[] = new Object[]{
        "contractHolderId", "contractHolderNo", "contractHolderName", "contractHolderStatus", "contractHolderType"};
    /**
     * The Constant CUS_COLUMN_HEADER.
     */
    public final String chHolderColumnHeader [] = new String[]{
        "Contract Holder ID", "Contract Holder No", "Contract Holder Name", "Contract Holder Status", "Contract Holder Type"};

    public final Object rebatePlanVisibleColumn [] = new Object[]{
        "rebatePlanId", "rebatePlanNo", "rebatePlanName", "rebatePlanStatus", "rebatePlanType"};
    /**
     * The Constant CUS_COLUMN_HEADER.
     */
    public final String rebatePlanColumnHeader[] = new String[]{
        "Rebate Plan ID", "Rebate Plan No", "Rebate Plan Name", "Rebate Plan Status", "Rebate Plan Type"};
     private static TableHeaderColumnsUtil object;
    /**
     * Constructor
     */
    private TableHeaderColumnsUtil() {
        /*
            Constructor
        */
    }

    public static TableHeaderColumnsUtil getInstance() {
        if (object == null) {
            object = new TableHeaderColumnsUtil();
        }
        return object;
    }
}
