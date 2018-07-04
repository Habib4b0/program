/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import com.stpl.app.arm.utils.ARMUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author porchelvi.g
 */
public class HeaderUtils {

    private HeaderUtils() {
    }

    public static Map<String, List<Object>> configureBalanceSummaryTableheader(String reportType) {
        List<Object> reportTypeHeaderDetails = new ArrayList<>();

        if ("Pipeline Balance Summary".equals(reportType)) {

            reportTypeHeaderDetails.add(ARMUtils.getReportTypePipelineSingleVisibleColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypePipelineSingleHeaderColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypePipelineDoubleVisibleColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypePipelineDoubleHeaderColumn());
            reportTypeHeaderDetails.add(confDoubleHeaderMapPipelineReportType());

        } else if ("Demand Balance Summary".equals(reportType)) {
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeDemandSingleVisibleColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeDemandSingleHeaderColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeDemandDoubleVisibleColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeDemandDoubleHeaderColumn());
            reportTypeHeaderDetails.add(confDoubleHeaderMapDemandReportType());
        } else if ("Single Liability Balance Summary".equals(reportType)) {
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeLiabilitySingleVisibleColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeLiabilitySingleHeaderColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeLiabilityDoubleVisibleColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeLiabilityDoubleHeaderColumn());
            reportTypeHeaderDetails.add(confDoubleHeaderMapLiabilityReportType());
        } else if ("Return Reserve Balance Summary".equals(reportType)) {
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeReturnSingleVisibleColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeReturnSingleHeaderColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeReturnDoubleVisibleColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeReturnDoubleHeaderColumn());
            reportTypeHeaderDetails.add(confDoubleHeaderMapReturnReserveReportType());

        } else {
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeSingleVisibleColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeSingleHeaderColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeDoubleVisibleColumn());
            reportTypeHeaderDetails.add(ARMUtils.getReportTypeDoubleHeaderColumn());
            reportTypeHeaderDetails.add(configureDoubleHeaderMap());
        }

        Map<String, List<Object>> map = new HashMap<>();
        map.put(reportType, reportTypeHeaderDetails);
        return map;
    }

    public static Map<Object, Object[]> configureDoubleHeaderMap() {
        Map<Object, Object[]> headerMap = new HashMap<>();
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_START_BALANCE.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_ACCOUNT.getConstant()});
        return headerMap;
    }

    public static Map<Object, Object[]> confDoubleHeaderMapPipelineReportType() {
        Map<Object, Object[]> headerMap = new HashMap<>();
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_START_BALANCE.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_ACCOUNT.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_PIPELINE_ACCRUAL.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_PIPELINE_ACCRUAL_ADJ_TYPE.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_DEMAND_ACCRUAL.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_DEMAND_ACCRUAL_ADJ_TYPE.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_PIPELINE_INVENTORY_TRUPUP.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_PIPELINE_INVENTORY_TRUPUP_ADJ_TYPE.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_DEMAND_REFORECAST.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_DEMAND_REFORECAST_ADJ_TYPE.getConstant()});

        return headerMap;

    }

    public static Map<Object, Object[]> confDoubleHeaderMapDemandReportType() {
        Map<Object, Object[]> headerMap = new HashMap<>();
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_START_BALANCE.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_ACCOUNT.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_DEMAND_ACCRUAL.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_DEMAND_ACCRUAL_ADJ_TYPE.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_DEMAND_REFORECAST.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_DEMAND_REFORECAST_ADJ_TYPE.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_DEMAND_TRUEUP.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_DEMAND_TRUEUP_ADJ_TYPE.getConstant()});
        return headerMap;

    }

    public static Map<Object, Object[]> confDoubleHeaderMapLiabilityReportType() {
        Map<Object, Object[]> headerMap = new HashMap<>();
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_START_BALANCE.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_ACCOUNT.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_FEES_ACCRUAL.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_FEES_ACCRUAL_ADJ_TYPE.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_INFLATION_ADJUTMENT.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_INFLATION_ADJUTMENT_ADJ_TYPE.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_CREDIT_CARD_FEES.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_CREDIT_CARD_FEES_ADJ_TYPE.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_OTHER_FIXED_DOLLAR_FEES.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_OTHER_FIXED_DOLLAR_FEES_ADJ_TYPE.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_INVENTORY_VALUATION.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_INVENTORY_VALUATION_ADJ_TYPE.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_PAYMENT_TRUEUP.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_PAYMENT_TRUEUP_ADJ_TYPE.getConstant()});
        return headerMap;

    }

    public static Map<Object, Object[]> confDoubleHeaderMapReturnReserveReportType() {
        Map<Object, Object[]> headerMap = new HashMap<>();
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_START_BALANCE.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_ACCOUNT.getConstant()});
        headerMap.put(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_RETURN_RESERVE.getConstant(), new Object[]{ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_RETURN_RESERVE_ADJ_TYPE.getConstant()});
        return headerMap;

    }

}
