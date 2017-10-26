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

    public static final Object PROMOTE_TP_RESULTS_COLUMNS[] = new Object[]{
        "companySystemId", "companyId", "companyNo", "companyName", "companyType", "companyCategory", "tradeClass",
        "address1", "address2", "city", "state", "zip"};
    /**
     * The Constant PROMOTE_TP_RESULTS_HEADERS.
     */
    public static final String PROMOTE_TP_RESULTS_HEADERS[] = new String[]{
        "System ID", Constants.COMPANY_ID, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYTYPE, Constants.COMPANYCATEGORY, Constants.TRADECLASS,
        "Address 1", "Address 2", "City", "State", "Zip"};
    /**
     * CURRENT_TRADING_PARTNER_COLUMNS
     */
    public static final Object CURRENT_TRADING_PARTNER_COLUMNS[] = new Object[]{
        "checkRecord", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, "rebateScheduleNo",
        "rebateScheduleName", "rarCategory", "status", "companyStartDate", "companyEndDate"};
    /**
     * The Constant CURRENT_TRADING_PARTNER_HEADERS.
     */
    public static final String CURRENT_TRADING_PARTNER_HEADERS[] = new String[]{
        "", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Rebate Schedule No",
        "Rebate Schedule Name", "RAR Category", "Status", "Company Start Date", "Company End Date"};

    public static final Object CH_HOLDER_VISIBLE_COLUMN[] = new Object[]{
        "contractHolderId", "contractHolderNo", "contractHolderName", "contractHolderStatus", "contractHolderType"};
    /**
     * The Constant CUS_COLUMN_HEADER.
     */
    public static final String CH_HOLDER_COLUMN_HEADER[] = new String[]{
        "Contract Holder ID", "Contract Holder No", "Contract Holder Name", "Contract Holder Status", "Contract Holder Type"};

    public static final Object REBATE_PLAN_VISIBLE_COLUMN[] = new Object[]{
        "rebatePlanId", "rebatePlanNo", "rebatePlanName", "rebatePlanStatus", "rebatePlanType"};
    /**
     * The Constant CUS_COLUMN_HEADER.
     */
    public static final String REBATE_PLAN_COLUMN_HEADER[] = new String[]{
        "Rebate Plan ID", "Rebate Plan No", "Rebate Plan Name", "Rebate Plan Status", "Rebate Plan Type"};
}
