/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.constants;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnWsAdjusmtmentDetailsQueryConstants {

    private static final Map<String, String> FILTER_MAP = new HashMap<>();

    public static Map<String, String> getColumnMap() {
        if (FILTER_MAP.isEmpty()) {
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_COMPANY, "COMPANY");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_DIVISION, "DIVISON");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_COST_CENTER, "COST_CENTER");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ACCOUNT, "ACCOUNT");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_BRAND, "BRAND");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_PROJECT, "PROJECT");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_FUTURE_1, "FUTURE_1");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_FUTURE_2, "FUTURE_2");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_DEBIT, "DEBIT");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_CREDIT, "CREDIT");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_LINE_DESCRIPTION, "LINE_DESCRIPTION");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_BALANCE_TYPE, "BALANCE_TYPE");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_DATABASE, "ARD_DB");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_DATA_ACCESS_SET, "DATA_ACCESS_SET");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_CHART_OF_ACCOUNTS, "CHART_OF_ACCOUNTS");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_LEDGER, "LEDGER");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_CATEGORY, "CATEGORY");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_SOURCE, "SOURCE");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_CURRENCY, "CURRENCY");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_ACCOUNTING_DATE, "ACCOUNTING_DATE");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_BATCH_NAME, "BATCH_NAME");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_JOURNAL_NAME, "JOURNAL_NAME");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_JOURNAL_DESCRIPTION, "JOURNAL_DESCRIPTION");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_REVERSE_JOURNAL, "REVERSE_JOURNAL");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_REVERSAL_PERIOD_DATE, "REVERSAL_PERIOD_DATE");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_BUSINESS_UNIT, "BUSINESS_UNIT");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ADJUSTMENT_TYPE, "ADJUSTMENT_TYPE");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ADJUSTMENT_LEVEL, "ADJUSTMENT_LEVEL");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ACCOUNT_CATEGORY, "ACCOUNT_CATEGORY");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ACCOUNT_TYPE, "ACCOUNT_TYPE");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ACCOUNT_DESCRIPTION, "ACCOUNT_DESCRIPTION");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ACCOUNT_INDICATOR, "ACCOUNT_INDICATOR");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_UDC_1, "UDC_1");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_UDC_2, "UDC_2");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_UDC_3, "UDC_3");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_UDC_4, "UDC_4");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_UDC_5, "UDC_5");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_UDC_6, "UDC_6");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_REDEMPTION_PERIOD, "REDEMPTION_PERIOD");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_CALCULATION_PERIOD, "CALCULATION_PERIOD");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_POSTING_INDICATOR, "POSTING_INDICATOR");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_WORKFLOW_ID, "WORKFLOW_ID");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_WORKFLOW_NAME, "WORKFLOW_NAME");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_WORKFLOW_CREATED_BY, "WORKFLOW_CREATED_BY");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_WORKFLOW_CREATED_DATE, "WORKFLOW_CREATED_DATE");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_WORKFLOW_APPROVED_BY, "WORKFLOW_APPROVED_BY");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_WORKFLOW_APPROVED_DATE, "WORKFLOW_APPROVED_DATE");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_BATCH_ID, "BATCH_ID");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ORIGINAL_BATCH_ID, "ORIGINAL_BATCH_ID");
            FILTER_MAP.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_ADJUSTMENT_RESERVE_DETAIL_SID, "ADJUSTMENT_RESERVE_DETAIL_SID");

            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_TYPE, "DEDUCTION_TYPE");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_GL_MONTH, "GL_MONTH");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_GL_YEAR, "GL_YEAR");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_GL_STRING, "GL_STRING");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_GL_COMPANY_ID, "GL_COMPANY_ID");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_BRAND_ID, "BRAND_ID");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_ITEM_NO, "ITEM_NO");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_AMOUNT, "DEDUCTION_AMOUNT");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_GL_COMPANY_NO, "GL_COMPANY_NO");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_GL_DATE, "GL_DATE");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_GL_COMPANY_NAME, "GL_COMPANY_NAME");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_BUSINESS_UNIT_ID, "BUSINESS_UNIT_ID");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_BUSINESS_UNIT_NO, "BUSINESS_UNIT_NO");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_BUSINESS_UNIT_NAME, "BUSINESS_UNIT_NAME");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_CONTRACT_ID, "CONTRACT_ID");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_CONTRACT_NO, "CONTRACT_NO");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_CONTRACT_NAME, "CONTRACT_NAME");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_COMPANY_ID, "COMPANY_ID");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_COMPANY_NO, "COMPANY_NO");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_COMPANY_NAME, "COMPANY_NAME");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_ITEM_ID, "ITEM_ID");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_ITEM_NAME, "ITEM_NAME");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_BRAND_NAME, "BRAND_NAME");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_ID, "DEDUCTION_ID");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_NO, "DEDUCTION_NO");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_NAME, "DEDUCTION_NAME");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_CATEGORY, "DEDUCTION_CATEGORY");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_PROGRAM, "DEDUCTION_PROGRAM");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_INCLUSION, "DEDUCTION_INCLUSION");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_UDC_1, "DEDUCTION_UDC_1");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_UDC_2, "DEDUCTION_UDC_2");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_UDC_3, "DEDUCTION_UDC_3");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_UDC_4, "DEDUCTION_UDC_4");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_UDC_5, "DEDUCTION_UDC_5");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_UDC_6, "DEDUCTION_UDC_6");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_RATE, "DEDUCTION_RATE");
            FILTER_MAP.put(GtnFrameworkCommonConstants.GTN_DETAILS_ADJUSTMENT_GTN_DETAIL_SID, "ADJUSTMENT_GTN_DETAIL_SID");
        }
        return Collections.unmodifiableMap(FILTER_MAP);
    }
}
