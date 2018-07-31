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

    private final Map<String, String> filterMap = new HashMap<>();

    public static final String SELECT_PRIVATE_PUBLIC_VIEW = "FROM ARM_VIEW_MASTER AVM "
            + "JOIN ARM_ADJUSTMENT_VIEW_DETAILS AVD ON AVM.ARM_VIEW_MASTER_SID = AVD.ARM_VIEW_MASTER_SID \n"
            + "        LEFT JOIN @SYS.dbo.User_ UN\n"
            + "        on un.userId=AVM.CREATED_BY\n"
            + "        LEFT JOIN @SYS.dbo.User_ MB\n"
            + "        on MB.userId=AVM.MODIFIED_BY";

    public static final String AVM = "AVM";
    public static final String AVD = "AVD";
    public static final String UN = "UN";
    public static final String SPACE = "  ";
    public static final char PERCENT = '%';
    public static final char ASTERISK = '*';
    public static final char QUOTES = '\'';

    public GtnWsAdjusmtmentDetailsQueryConstants() {
        super();
    }

    public Map<String, String> getColumnMap() {
        if (filterMap.isEmpty()) {
            filterMap.put(GtnFrameworkCommonConstants.CHECK_RECORD_ID, "CHECK_RECORD");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_COMPANY, "GL_COMPANY");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_DIVISION, "DIVISON");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_COST_CENTER, "COST_CENTER");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ACCOUNT, "ACCOUNT");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_BRAND, "BRAND");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_PROJECT, "PROJECT");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_FUTURE_1, "FUTURE_1");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_FUTURE_2, "FUTURE_2");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_DEBIT, "DEBIT");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_CREDIT, "CREDIT");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_LINE_DESCRIPTION, "LINE_DESCRIPTION");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_BALANCE_TYPE, "BALANCE_TYPE");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_DATABASE, "ARD_DB");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_DATA_ACCESS_SET, "DATA_ACCESS_SET");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_CHART_OF_ACCOUNTS, "CHART_OF_ACCOUNTS");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_LEDGER, "LEDGER");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_CATEGORY, "CATEGORY");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_SOURCE, "SOURCE");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_CURRENCY, "CURRENCY");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_ACCOUNTING_DATE, "ACCOUNTING_DATE");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_BATCH_NAME, "BATCH_NAME");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_JOURNAL_NAME, "JOURNAL_NAME");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_JOURNAL_DESCRIPTION, "JOURNAL_DESCRIPTION");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_REVERSE_JOURNAL, "REVERSE_JOURNAL");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_REVERSAL_PERIOD_DATE, "REVERSAL_PERIOD_DATE");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_BUSINESS_UNIT, "BUSINESS_UNIT_NAME");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ADJUSTMENT_TYPE, "ADJUSTMENT_TYPE");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ADJUSTMENT_LEVEL, "ADJUSTMENT_LEVEL");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ACCOUNT_CATEGORY, "ACCOUNT_CATEGORY");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ACCOUNT_TYPE, "ACCOUNT_TYPE");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ACCOUNT_DESCRIPTION, "ACCOUNT_DESCRIPTION");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ACCOUNT_INDICATOR, "ACCOUNT_INDICATOR");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_UDC_1, "UDC_1");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_UDC_2, "UDC_2");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_UDC_3, "UDC_3");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_UDC_4, "UDC_4");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_UDC_5, "UDC_5");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_UDC_6, "UDC_6");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_REDEMPTION_PERIOD, "REDEMPTION_PERIOD");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_CALCULATION_PERIOD, "CALCULATION_PERIOD");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_POSTING_INDICATOR, "POSTING_INDICATOR");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_WORKFLOW_ID, "WORKFLOW_ID");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_WORKFLOW_NAME, "WORKFLOW_NAME");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_WORKFLOW_CREATED_BY, "WORKFLOW_CREATED_BY");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_WORKFLOW_CREATED_DATE, "CREATED_DATE");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_WORKFLOW_APPROVED_BY, "WORKFLOW_APPROVED_BY");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_WORKFLOW_APPROVED_DATE, "WORKFLOW_APPROVED_DATE");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_BATCH_ID, "BATCH_ID");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_GTN_DETAILS_ORIGINAL_BATCH_ID, "ORIGINAL_BATCH_ID");
            filterMap.put(GtnFrameworkCommonConstants.RESERVE_DETAILS_ADJUSTMENT_RESERVE_DETAIL_SID, "ADJUSTMENT_RESERVE_DETAIL_SID");

            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_TYPE, "DEDUCTION_TYPE");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_GL_MONTH, "GL_MONTH");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_GL_YEAR, "GL_YEAR");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_GL_STRING, "GL_STRING");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_GL_COMPANY_ID, "GL_COMPANY_ID");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_BRAND_ID, "BRAND_ID");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_ITEM_NO, "ITEM_NO");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_AMOUNT, "DEDUCTION_AMOUNT");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_GL_COMPANY_NO, "GL_COMPANY_NO");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_GL_DATE, "GL_DATE");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_GL_COMPANY_NAME, "GL_COMPANY_NAME");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_BUSINESS_UNIT_ID, "BUSINESS_UNIT_ID");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_BUSINESS_UNIT_NO, "BUSINESS_UNIT_NO");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_BUSINESS_UNIT_NAME, "BUSINESS_UNIT_NAME");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_CONTRACT_ID, "CONTRACT_ID");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_CONTRACT_NO, "CONTRACT_NO");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_CONTRACT_NAME, "CONTRACT_NAME");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_COMPANY_ID, "COMPANY_ID");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_COMPANY_NO, "COMPANY_NO");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_COMPANY_NAME, "COMPANY_NAME");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_ITEM_ID, "ITEM_ID");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_ITEM_NAME, "ITEM_NAME");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_BRAND_NAME, "BRAND_NAME");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_ID, "DEDUCTION_ID");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_NO, "DEDUCTION_NO");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_NAME, "DEDUCTION_NAME");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_CATEGORY, "DEDUCTION_CATEGORY");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_PROGRAM, "DEDUCTION_PROGRAM");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_INCLUSION, "DEDUCTION_INCLUSION");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_UDC_1, "DEDUCTION_UDC_1");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_UDC_2, "DEDUCTION_UDC_2");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_UDC_3, "DEDUCTION_UDC_3");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_UDC_4, "DEDUCTION_UDC_4");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_UDC_5, "DEDUCTION_UDC_5");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_UDC_6, "DEDUCTION_UDC_6");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_DEDUCTION_RATE, "DEDUCTION_RATE");
            filterMap.put(GtnFrameworkCommonConstants.GTN_DETAILS_ADJUSTMENT_GTN_DETAIL_SID, "ADJUSTMENT_GTN_DETAIL_SID");
        }
        return Collections.unmodifiableMap(filterMap);
    }
}
