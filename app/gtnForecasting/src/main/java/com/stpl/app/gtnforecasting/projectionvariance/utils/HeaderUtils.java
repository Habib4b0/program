/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.projectionvariance.utils;

import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.projectionvariance.logic.MProjectionVarianceLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.ANNUALLY;
import static com.stpl.app.gtnforecasting.utils.Constant.MONTHLY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.FORECAST_END_DAY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.FORECAST_END_PERIOD;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.FORECAST_START_DAY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.FORECAST_START_PERIOD;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.FREQUENCY_DIVISION;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_END_DAY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_END_MONTH;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_END_PERIOD;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_END_YEAR;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_START_DAY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_START_MONTH;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_START_PERIOD;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.HISTORY_START_YEAR;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_END_DAY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_END_PERIOD;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_DAY;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_MONTH;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_MONTH_DDLB;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_PERIOD;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_YEAR;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.PROJECTION_START_YEAR_DDLB;
import static com.stpl.app.gtnforecasting.utils.Constant.QUARTERLY;
import static com.stpl.app.gtnforecasting.utils.Constant.SEMI_ANNUALLY;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Jayaram
 */
public class HeaderUtils {

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num == NumericConstants.TWELVE) {
            num = 0;
        }
        if (num >= 0 && num <= NumericConstants.ELEVEN) {
            month = months[num];
        }
        return month;
    }

    public static List<String> getCommonColumnHeaderForPV(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = Constant.Q + period + StringUtils.EMPTY + year;
            commonHeader = Constant.Q + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = Constant.S + period + StringUtils.EMPTY + year;
            commonHeader = Constant.S + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    public static CustomTableHeaderDTO getLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO, String group) {

        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn(RELATIONSHIP_LEVEL_NAME, group, String.class);
        fullHeaderDTO.addSingleColumn(RELATIONSHIP_LEVEL_NAME, group, String.class);
        fullHeaderDTO.addDoubleColumn(RELATIONSHIP_LEVEL_NAME, " ");
        fullHeaderDTO.addDoubleHeaderMap(RELATIONSHIP_LEVEL_NAME, new Object[]{RELATIONSHIP_LEVEL_NAME});
        return tableHeaderDTO;
    }
    public static final String RELATIONSHIP_LEVEL_NAME = "relationshipLevelName";

    public static List<Object> getPVRightTableColumns(PVSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getProjectionVarianceColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }

    public static List<Object> getProjectionVarianceColumns(CustomTableHeaderDTO tableHeaderDTO, PVSelectionDTO projSelDTO, CustomTableHeaderDTO excelHeaderDTO) {
        String freq = projSelDTO.getFrequency();
        String currentProjName = projSelDTO.getCurrentProjectionName();
        String headerIndicator = projSelDTO.getPivotView();
        List<Integer> projList = projSelDTO.getProjIdList();
        Map<Integer, String> priorMap = projSelDTO.getProjectionMap();
        String pivotView = projSelDTO.getPivotView();
        String projOrder = projSelDTO.getProjectionPeriodOrder();
        CommonUtils.getHistoryAndProjectionDetails(projSelDTO);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        int historyNum = projSelDTO.getHistoryNum();
        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endYear = projSelDTO.getEndYear();
        int endPeriod = projSelDTO.getEndPeriod();

        int projectionOrder = 0;
        if (projOrder.contains(Constant.ASCENDING)) {
            projectionOrder = 1;
        } else {
            projectionOrder = NumericConstants.TWO;
        }
        tableHeaderDTO.setFrequency(freq);
        tableHeaderDTO.setFrequencyDivision(frequencyDivision);
        tableHeaderDTO.setHistoryNum(historyNum);
        tableHeaderDTO.setProjectionNum(projectionNum);
        tableHeaderDTO.setCurrentPeriod(currentPeriod);
        tableHeaderDTO.setCurrentYear(currentYear);
        tableHeaderDTO.setStartYear(startYear);
        tableHeaderDTO.setStartPeriod(startPeriod);
        tableHeaderDTO.setEndYear(endYear);
        tableHeaderDTO.setEndPeriod(endPeriod);
        tableHeaderDTO.setProjectionOrder(projectionOrder);

        projSelDTO.setFrequency(freq);
        projSelDTO.setFrequencyDivision(frequencyDivision);
        projSelDTO.setHistoryNum(historyNum);
        projSelDTO.setProjectionNum(projectionNum);
        projSelDTO.setCurrentPeriod(currentPeriod);
        projSelDTO.setCurrentYear(currentYear);
        projSelDTO.setStartYear(startYear);
        projSelDTO.setStartPeriod(startPeriod);
        projSelDTO.setEndYear(endYear);
        projSelDTO.setEndPeriod(endPeriod);

        List<Object> collapseIconList = new ArrayList<>();
        List<Object> collapsedList = new ArrayList<>();
        List<Object> programCodeCollapsedList = new ArrayList<>();
        List<String> programCodeName = new ArrayList<>();
        if (pivotView.equals(Constant.PERIOD) || headerIndicator.equals(Constant.PERIOD)) {

            List<String> projectionColumnList = new ArrayList<>();
            List<String> projectionHeaderList = new ArrayList<>();
            
            int year=0;
            int count=0;
            List<Object> yearwiseColumns;
            List<Object> visibleColumns = null;
            List<Object> columnHeaders = null;
            List<Object> doubleVisibleColumns = null;
            List<Object> doubleColumnHeaders = null;
            Map<Object, Object[]> doubleHeaderMapExcel = null;
            List<Object> staticColumns = new ArrayList<> (excelHeaderDTO.getSingleColumns());
            List<String> staticHeaders = new ArrayList<> (excelHeaderDTO.getSingleHeaders());
            Map<Integer, List<Object>> columnsMap = new HashMap<>();
            
            //This condition is added for issue no GAL-2314
            int startPr = projSelDTO.getProjectionStartPeriod() != 0 ? projSelDTO.getProjectionStartPeriod() : 1;
            int lastPr = frequencyDivision;
            boolean flag=frequencyDivision == NumericConstants.TWELVE || frequencyDivision == NumericConstants.FOUR;
            for (int yr = projSelDTO.getProjectionStartYear(); yr <= projSelDTO.getForecastDTO().getProjectionEndYear(); yr++) {
                if (yr == projSelDTO.getForecastDTO().getProjectionEndYear()) {
                    lastPr = projSelDTO.getProjectionEndPeriod();
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = getCommonColumnHeaderForPV(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    projectionColumnList.add(commonColumn);
                    projectionHeaderList.add(commonHeader);
                }
                startPr = 1;

            }
            for (int i = 0; i < projectionColumnList.size(); i++) {
                String commonColumn = projectionColumnList.get(i);
                String commonHeader = projectionHeaderList.get(i);
                List<Object> dmap = new ArrayList<>();
                
                int tempYear=frequencyDivision==1 ? 0 :Integer.valueOf(commonHeader.trim().split(" ")[1]);
                
                if ((flag) && year!=tempYear) {
                    yearwiseColumns= new ArrayList<>();
                    visibleColumns= new ArrayList<>();
                    columnHeaders= new ArrayList<>();
                    doubleVisibleColumns= new ArrayList<>();
                    doubleColumnHeaders= new ArrayList<>();
                    yearwiseColumns.add(visibleColumns);
                    yearwiseColumns.add(columnHeaders);
                    visibleColumns.addAll(staticColumns);
                    columnHeaders.addAll(staticHeaders);
                    doubleVisibleColumns.add("");
                    doubleColumnHeaders.add("");
                    doubleHeaderMapExcel = new HashMap<>();
                    yearwiseColumns.add(doubleVisibleColumns);
                    yearwiseColumns.add(doubleColumnHeaders);
                    yearwiseColumns.add(doubleHeaderMapExcel);
                    yearwiseColumns.add(tempYear);
                    doubleHeaderMapExcel.put("", staticColumns.toArray());
                    columnsMap.put(count, yearwiseColumns);
                    year=tempYear;
                    count++;
                }
                
                
                if ("Period".equalsIgnoreCase(pivotView) && "Descending".equalsIgnoreCase(projOrder)) {
                if (!projList.isEmpty()) {
                    for (int j = 0; j < projList.size(); j++) {
                        tableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
 excelHeaderDTO.addSingleColumn(commonColumn + projList.get(j), commonHeader + " " + priorMap.get(projList.get(j)), String.class);
                        if (flag) {
                            visibleColumns.add(commonColumn + projList.get(j));
                            columnHeaders.add(priorMap.get(projList.get(j)));
                        }
                        tableHeaderDTO.addSingleProjectedColumn(commonColumn + projList.get(j), "" + projList.get(j));
                        dmap.add(commonColumn + projList.get(j));
                    }
                }
                    tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT, currentProjName, String.class);
                    if (flag) {
                        visibleColumns.add(commonColumn + Constant.CURRENT);
                        columnHeaders.add(currentProjName);
                    }
                    excelHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT, commonHeader + " " + currentProjName, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(commonColumn + Constant.CURRENT, Constant.CURRENT);
                    dmap.add(commonColumn + Constant.CURRENT);
                } else {
                    tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT, currentProjName, String.class);
                    if (flag) {
                        visibleColumns.add(commonColumn + Constant.CURRENT);
                        columnHeaders.add(currentProjName);
                    }
                    excelHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT, commonHeader + " " + currentProjName, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(commonColumn + Constant.CURRENT, Constant.CURRENT);
                    dmap.add(commonColumn + Constant.CURRENT);
                    if (!projList.isEmpty()) {
                        for (int j = 0; j < projList.size(); j++) {
                            tableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                            if (flag) {
                                visibleColumns.add(commonColumn + projList.get(j));
                                columnHeaders.add(priorMap.get(projList.get(j)));
                            }
                            excelHeaderDTO.addSingleColumn(commonColumn + projList.get(j), commonHeader + " " + priorMap.get(projList.get(j)), String.class);
                            tableHeaderDTO.addSingleProjectedColumn(commonColumn + projList.get(j), "" + projList.get(j));
                            dmap.add(commonColumn + projList.get(j));
                        }
                    }
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                    excelHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    excelHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    excelHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                    if (flag) {
                        doubleVisibleColumns.add(commonColumn);
                        doubleColumnHeaders.add(commonHeader);
                        doubleHeaderMapExcel.put(commonColumn, dmap.toArray());
                    }
                }
            }
            startPr = projSelDTO.getForecastStartPeriod();
            lastPr = frequencyDivision;
            for (int yr = projSelDTO.getForecastDTO().getForecastStartYear(); yr <= projSelDTO.getForecastDTO().getForecastEndYear(); yr++) {
                if (yr == projSelDTO.getForecastDTO().getForecastEndYear()) {
                    lastPr = projSelDTO.getForecastEndPeriod();
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {

                    List<String> common = getCommonColumnHeaderForPV(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);

                    if (!projectionColumnList.contains(commonColumn)) {
                        List<Object> dmap = new ArrayList<>();
                        if ("Period".equalsIgnoreCase(pivotView) && "Descending".equalsIgnoreCase(projOrder)) {
                        if (!projList.isEmpty()) {
                            for (int j = 0; j < projList.size(); j++) {
                                tableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                                if (flag) {
                                    visibleColumns.add(commonColumn + projList.get(j));
                                    columnHeaders.add(priorMap.get(projList.get(j)));
                                }
                                excelHeaderDTO.addSingleColumn(commonColumn + projList.get(j), commonHeader + " " + priorMap.get(projList.get(j)), String.class);
                                    tableHeaderDTO.addSingleProjectedColumn(commonColumn + projList.get(j), "" + projList.get(j));
                                dmap.add(commonColumn + projList.get(j));
                            }
                        }
                            tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT, currentProjName, String.class);
                            if (flag) {
                                visibleColumns.add(commonColumn + Constant.CURRENT);
                                columnHeaders.add(currentProjName);
                            }
                            excelHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT, commonHeader + " " + currentProjName, String.class);
                            tableHeaderDTO.addSingleProjectedColumn(commonColumn + Constant.CURRENT, Constant.CURRENT);
                            dmap.add(commonColumn + Constant.CURRENT);
                        } else {
                            tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT, currentProjName, String.class);
                            if (flag) {
                                visibleColumns.add(commonColumn + Constant.CURRENT);
                                columnHeaders.add(currentProjName);
                            }
                            excelHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT, commonHeader + " " + currentProjName, String.class);
                            tableHeaderDTO.addSingleProjectedColumn(commonColumn + Constant.CURRENT, Constant.CURRENT);
                            dmap.add(commonColumn + Constant.CURRENT);
                            if (!projList.isEmpty()) {
                                for (int j = 0; j < projList.size(); j++) {
                                    tableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                                    if (flag) {
                                        visibleColumns.add(commonColumn + projList.get(j));
                                        columnHeaders.add(priorMap.get(projList.get(j)));
                                    }
                                    excelHeaderDTO.addSingleColumn(commonColumn + projList.get(j), commonHeader + " " + priorMap.get(projList.get(j)), String.class);
                                    tableHeaderDTO.addSingleProjectedColumn(commonColumn + projList.get(j), "" + projList.get(j));
                                    dmap.add(commonColumn + projList.get(j));
                                }
                            }
                        }
                        if (!dmap.isEmpty()) {
                            tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                            excelHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            excelHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            excelHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                            if (flag) {
                                doubleVisibleColumns.add(commonColumn);
                                doubleColumnHeaders.add(commonHeader);
                                doubleHeaderMapExcel.put(commonColumn, dmap.toArray());
                            }
                        }
                    }
                }
                startPr = 1;
            }
            Map<String, String> periodListMap = new HashMap<>();
            List<String> period = new ArrayList<>();
            for (int i = 0; i < tableHeaderDTO.getDoubleColumns().size(); i++) {
                period.add(String.valueOf(tableHeaderDTO.getDoubleColumns().get(i)));
            }
            projSelDTO.setPeriodHeaderList(period);
            for (int pr = 0; pr < tableHeaderDTO.getDoubleColumns().size(); pr++) {
                periodListMap.put(String.valueOf(tableHeaderDTO.getDoubleColumns().get(pr)), tableHeaderDTO.getDoubleHeaders().get(pr));
            }
            projSelDTO.setPeriodListMap(periodListMap);
            if (flag) {
                projSelDTO.setYearwiseColumns(columnsMap);
            }
            
        } else {
            if (Constant.COMPONENT.equals(projSelDTO.getDiscountLevel())) {
                if (projSelDTO.getProgramCodeNameList() == null || projSelDTO.getProgramCodeNameList().size() == 0) {
                    programCodeName = MProjectionVarianceLogic.getProgramCodeName(projSelDTO.getProjectionId());
                } else {
                    programCodeName = projSelDTO.getProgramCodeNameList();
                }
            }
            String commonColumn;

            String commonHeader;

            // Exfactory Sales
            if (projSelDTO.isVarExFacSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "ExFacValue";
                    commonHeader = "Ex-Factory Sales Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "ExFacVariance";
                    commonHeader = "Ex-Factory Sales variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "ExFacPer";
                    commonHeader = "Ex-Factory Sales % change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
            }
            //Demand sales
            if (projSelDTO.isVarDemandSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "DemandSalesValue";
                    commonHeader = "Demand Sales Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "DemandSalesVariance";
                    commonHeader = "Demand Sales variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "DemandSalesPer";
                    commonHeader = "Demand Sales  % change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
            }
            //Inventory Sales
            if (projSelDTO.isVarInvSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "InvWithValue";
                    commonHeader = "Inventory Withdrawal Sales value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "InvWithVariance";
                    commonHeader = "Inventory Withdrawal Sales variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "InvWithPer";
                    commonHeader = "Inventory Withdrawal Sales % change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
            }
            // % Of Ex Fact
            if (projSelDTO.isVarPerExFacSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "PerExFacValue";
                    commonHeader = "% Of Ex-Factory Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "PerExFacVariance";
                    commonHeader = "% Of  Ex-Factory variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "PerExFacPer";
                    commonHeader = "% Of Ex-Factory % change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
            }
            //% of Demand sales
            if (projSelDTO.isVarPerDemandSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "PerDemandSalesValue";
                    commonHeader = "% Of Demand Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "PerDemandSalesVariance";
                    commonHeader = "% Of Demand variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "PerDemandSalesPer";
                    commonHeader = "% Of Demand change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
            }
            //% of Inventory Sales
            if (projSelDTO.isVarPerInvSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "PerInvWithValue";
                    commonHeader = "% Of Inventory Withdrawal value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "PerInvWithVariance";
                    commonHeader = "% Of Inventory Withdrawal variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "PerInvWithPer";
                    commonHeader = "% Of Inventory Withdrawal % change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
            }

            if (projSelDTO.isVarContractsales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "ContractSalesWACValue";
                    commonHeader = "Contract Sales @ WAC Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "ContractSalesWACVar";
                    commonHeader = "Contract Sales @ WAC Variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "ContractSalesWACVarPer";
                    commonHeader = "Contract Sales @ WAC Change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
            }
            if (projSelDTO.isVarContractUnits()) {

                if (projSelDTO.isColValue()) {
                    commonColumn = "ContractUnitsValue";
                    commonHeader = "Contract Units Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "ContractUnitsVar";
                    commonHeader = "Contract Units Variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "ContractUnitsPer";
                    commonHeader = "Contract Units Change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
            }
            if (projSelDTO.isVarDisAmount()) {

                if (projSelDTO.isColValue()) {
                    commonColumn = "DiscountAmountValue";
                    commonHeader = "Discount $ Value";
                    collapseIconList.add(commonColumn);
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                    if (Constant.COMPONENT.equals(projSelDTO.getDiscountLevel())) {
                        commonColumn = "mandatedDiscountAmountValue";
                        commonHeader = "Mandated Discount $ Value";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "mdProgramCodeAmountValue" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                        commonColumn = "supplementalDiscountAmountValue";
                        commonHeader = "Supplemental Discount $ Value";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "sdProgramCodeAmountValue" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                    }
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "DiscountAmountVar";
                    commonHeader = "Discount $ Variance";
                    collapseIconList.add(commonColumn);
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                    if (Constant.COMPONENT.equals(projSelDTO.getDiscountLevel())) {
                        commonColumn = "mandatedDiscountAmountVar";
                        commonHeader = "Mandated Discount $ Variance";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "mdProgramCodeAmountVar" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                        commonColumn = "supplementalDiscountAmountVar";
                        commonHeader = "Supplemental Discount $ Variance";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "sdProgramCodeAmountVar" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }

                    }
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "DiscountAmountPer";
                    commonHeader = "Discount $ % change";
                    collapseIconList.add(commonColumn);
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                    if (Constant.COMPONENT.equals(projSelDTO.getDiscountLevel())) {
                        commonColumn = "mandatedDiscountAmountPer";
                        commonHeader = "Mandated Discount $ % change";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "mdProgramCodeAmountper" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                        commonColumn = "supplementalDiscountAmountPer";
                        commonHeader = "Supplemental Discount $ % change";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "sdProgramCodeAmountPer" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                    }

                }
            }
            if (projSelDTO.isVarDisRate()) {

                if (projSelDTO.isColValue()) {
                    commonColumn = "DiscountSalesValue";
                    commonHeader = "Discount % Value";
                    collapseIconList.add(commonColumn);
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                    if (Constant.COMPONENT.equals(projSelDTO.getDiscountLevel())) {
                        commonColumn = "mandatedDiscountSalesValue";
                        commonHeader = "Mandated Discount % % Value";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "mdProgramCodeSalesValue" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                        commonColumn = "supplementalDiscountSalesValue";
                        commonHeader = "Supplemental Discount % % Value";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "sdProgramCodeSalesValue" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                    }

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "DiscountSalesVar";
                    commonHeader = "Discount % Variance";
                    collapseIconList.add(commonColumn);
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                    if (Constant.COMPONENT.equals(projSelDTO.getDiscountLevel())) {
                        commonColumn = "mandatedDiscountSalesVar";
                        commonHeader = "Mandated Discount % % Variance";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "mdProgramCodeSalesVar" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }

                        commonColumn = "supplementalDiscountSalesVar";
                        commonHeader = "Supplemental Discount % % Variance";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "sdProgramCodeSalesVar" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                    }
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "DiscountSalesPer";
                    commonHeader = "Discount % % Change";
                    collapseIconList.add(commonColumn);
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                    if (Constant.COMPONENT.equals(projSelDTO.getDiscountLevel())) {
                        commonColumn = "mandatedDiscountSalesPer";
                        commonHeader = "Mandated Discount % % change";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "mdProgramCodeSalesPer" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                        commonColumn = "supplementalDiscountSalesPer";
                        commonHeader = "Supplemental Discount % % change";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "sdProgramCodeSalesPer" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                    }
                }
            }
            if (projSelDTO.isVarRPU()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "RPUValue";
                    commonHeader = "RPU Value";
                    collapseIconList.add(commonColumn);
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                    if (Constant.COMPONENT.equals(projSelDTO.getDiscountLevel())) {
                        commonColumn = "mandatedDiscountRPUValue";
                        commonHeader = "Mandated PRU Value";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "mdProgramCodeRPUValue" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                        commonColumn = "supplementalRPUValue";
                        commonHeader = "Supplemental RPU Value";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "sdProgramCodeRPUValue" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                    }

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "RPUVariance";
                    commonHeader = "RPU Variance";
                    collapseIconList.add(commonColumn);
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                    if (Constant.COMPONENT.equals(projSelDTO.getDiscountLevel())) {
                        commonColumn = "mandatedDiscountRPUVar";
                        commonHeader = "Mandated RPU Variance";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "mdProgramCodeRPUVar" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                        commonColumn = "supplementalRPUVar";
                        commonHeader = "Supplemental RPU Variance";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "sdProgramCodeRPUVar" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                    }

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "RPUPer";
                    commonHeader = "RPU %Change";
                    collapseIconList.add(commonColumn);
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                    if (Constant.COMPONENT.equals(projSelDTO.getDiscountLevel())) {
                        commonColumn = "mandatedDiscountRPUPer";
                        commonHeader = "Mandated PRU %Change";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "mdProgramCodeRPUPer" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                        commonColumn = "supplementalRPUPer";
                        commonHeader = "Supplemental RPU %Change";
                        collapsedList.add(commonColumn);
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                        for (int i = 0; i < programCodeName.size(); i++) {
                            commonColumn = "sdProgramCodeRPUPer" + programCodeName.get(i);
                            tableHeaderDTO = loadSingleHeader(commonColumn, programCodeName.get(i), projSelDTO, tableHeaderDTO, excelHeaderDTO);
                            programCodeCollapsedList.add(commonColumn);
                        }
                    }
                }
            }
            if (projSelDTO.isVarNetSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "NetSalesValue";
                    commonHeader = "Net Sales Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "NetSalesVariance";
                    commonHeader = "Net Sales Variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "NetSalesPer";
                    commonHeader = "Net Sales %Change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
            }
            if (projSelDTO.isVarCOGC()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "COGCValue";
                    commonHeader = "COGS Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "COGCVariance";
                    commonHeader = "COGS Variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "COGCPer";
                    commonHeader = "COGS %Change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
            }
            if (projSelDTO.isVarNetProfit()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "NetProfitValue";
                    commonHeader = "Net Profit Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "NetProfitVariance";
                    commonHeader = "Net Profit Variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "NetProfitPer";
                    commonHeader = "Net Profit %Change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, excelHeaderDTO);
                }
            }
            List<String> periodList = new ArrayList<>();
            List<String> pivotList = new ArrayList<>();
            Map<String, String> periodListMap = new HashMap<>();
            //This condition is added for issue no GAL-2314
            int startPr = projSelDTO.getProjectionStartPeriod() != 0 ? projSelDTO.getProjectionStartPeriod() : 1;
            int lastPr = frequencyDivision;
            for (int yr = projSelDTO.getProjectionStartYear(); yr <= projSelDTO.getForecastDTO().getProjectionEndYear(); yr++) {
                if (yr == projSelDTO.getForecastDTO().getProjectionEndYear()) {
                    lastPr = projSelDTO.getProjectionEndPeriod();
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = getCommonColumnHeaderForPV(frequencyDivision, yr, pr);
                    String commonColum = common.get(0);
                    String commHeader = common.get(1);
                    if (!periodList.contains(commonColum)) {
                        periodList.add(commonColum);
                        pivotList.add(commHeader);
                        periodListMap.put(commonColum, commHeader);
                    }
                }
                startPr = 1;

            }
            startPr = projSelDTO.getForecastStartPeriod();
            lastPr = frequencyDivision;
            for (int yr = projSelDTO.getForecastDTO().getForecastStartYear(); yr <= projSelDTO.getForecastDTO().getForecastEndYear(); yr++) {
                if (yr == projSelDTO.getForecastDTO().getForecastEndYear()) {
                    lastPr = projSelDTO.getForecastEndPeriod();
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = getCommonColumnHeaderForPV(frequencyDivision, yr, pr);
                    String commonColum = common.get(0);
                    String commHeader = common.get(1);
                    if (!periodList.contains(commonColum)) {
                        periodList.add(commonColum);
                        pivotList.add(commHeader);
                        periodListMap.put(commonColum, commHeader);
                    }
                }
                startPr = 1;
            }

            projSelDTO.setPivotList(pivotList);
            projSelDTO.setPeriodList(periodList);
            projSelDTO.setPeriodListMap(periodListMap);
            projSelDTO.setRightHeader(tableHeaderDTO);
            projSelDTO.setPeriodHeaderList(projSelDTO.getPeriodList());
        }

        List<Object> headerContents = new ArrayList<>();
        headerContents.add(tableHeaderDTO);

        headerContents.add(collapseIconList);
        headerContents.add(collapsedList);
        headerContents.add(programCodeCollapsedList);
        headerContents.add(programCodeName);
        projSelDTO.setColumns(CommonUtils.objectListToStringList(tableHeaderDTO.getSingleColumns()));
        return headerContents;
    }

    static CustomTableHeaderDTO loadSingleHeader(final String commonColumn, final String commonHeader, final PVSelectionDTO selection, final CustomTableHeaderDTO tableHeaderDTO, final CustomTableHeaderDTO excelHeaderDTO) {
        String currentProjName = selection.getCurrentProjectionName();
        List<Integer> projList = selection.getProjIdList();
        Map<Integer, String> priorMap = selection.getProjectionMap();
        boolean disc = true;
        while (disc) {
            List<Object> dmap = new ArrayList<>();
            tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT + selection.getCurrentProjectionID(), currentProjName, String.class);
            excelHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT + selection.getCurrentProjectionID(), currentProjName + " " + commonHeader, String.class);
            dmap.add(commonColumn + Constant.CURRENT + selection.getCurrentProjectionID());
            if (!projList.isEmpty()) {
                for (int j = 0; j < projList.size(); j++) {
                    tableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                    excelHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)) + " " + commonHeader, String.class);
                    dmap.add(commonColumn + projList.get(j));
                }
            }
            disc = false;
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                excelHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                excelHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }
        }
        return tableHeaderDTO;
    }

    static CustomTableHeaderDTO loadSingleDiscountHeader(String commonColumn, String commonHeader, final PVSelectionDTO selection, final CustomTableHeaderDTO tableHeaderDTO, Map<String, Object> headerMap) {
        String column = commonColumn;

        List<String> discountNames = new ArrayList<>(selection.getDiscountNameList());
        List<Integer> projList = selection.getProjIdList();
        Map<Integer, String> priorMap = selection.getProjectionMap();
        List<String> commonColumnList = new ArrayList<>();
        if (!discountNames.isEmpty()) {
            for (int i = 0; i < discountNames.size(); i++) {
                List<Object> dmap = new ArrayList<>();
                commonHeader = discountNames.get(i);
                commonColumn = column + commonHeader.replace(" ", StringUtils.EMPTY) + i;
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT + selection.getCurrentProjectionID(), selection.getCurrentProjectionName(), String.class);
                dmap.add(commonColumn + Constant.CURRENT + selection.getCurrentProjectionID());
                if (!projList.isEmpty()) {
                    for (int j = 0; j < projList.size(); j++) {
                        tableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                        dmap.add(commonColumn + projList.get(j));
                    }
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
                commonColumnList.add(commonColumn);
                headerMap.put(column, commonColumnList);
            }
            selection.setHeaderMap(headerMap);


        }
        return tableHeaderDTO;
    }

    static void getHistoryAndProjectionDetails(PVSelectionDTO projSelDTO, String frequency) {
        int frequencyDivision = 1;
        int historyStartPeriod = 1;
        int historyEndPeriod = 1;
        int historyEndMonth = 1;
        int historyEndYear = 1;
        int forecastStartPeriod = 1;
        int forecastEndPeriod = 1;
        int projectionStartPeriod = 1;
        int projectionEndPeriod = 1;
        if (frequency.equals(QUARTERLY)) {
            frequencyDivision = NumericConstants.FOUR;
            historyStartPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), NumericConstants.THREE);
            historyEndPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), NumericConstants.THREE);
            forecastStartPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), NumericConstants.THREE);
            forecastEndPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), NumericConstants.THREE);
            projectionStartPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth() + 1, NumericConstants.THREE);
            projectionEndPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), NumericConstants.THREE);
        } else if (frequency.equals(SEMI_ANNUALLY)) {
            frequencyDivision = NumericConstants.TWO;
            historyStartPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), NumericConstants.SIX);
            historyEndPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), NumericConstants.SIX);
            forecastStartPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), NumericConstants.SIX);
            forecastEndPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), NumericConstants.SIX);
            projectionStartPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), NumericConstants.SIX);
            projectionEndPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), NumericConstants.SIX);
        } else if (frequency.equals(MONTHLY)) {
            frequencyDivision = NumericConstants.TWELVE;
            historyStartPeriod = projSelDTO.getForecastDTO().getHistoryStartMonth();
            historyEndPeriod = projSelDTO.getForecastDTO().getHistoryEndMonth();
            forecastStartPeriod = projSelDTO.getForecastDTO().getForecastStartMonth();
            forecastEndPeriod = projSelDTO.getForecastDTO().getForecastEndMonth();
            projectionStartPeriod = projSelDTO.getForecastDTO().getProjectionStartMonth();
            projectionEndPeriod = projSelDTO.getForecastDTO().getProjectionEndMonth();
        } else if (frequency.equals(ANNUALLY)) {
            frequencyDivision = 1;
            historyStartPeriod = projSelDTO.getForecastDTO().getHistoryStartYear();
            historyEndPeriod = projSelDTO.getForecastDTO().getHistoryEndYear();
            forecastStartPeriod = projSelDTO.getForecastDTO().getForecastStartYear();
            forecastEndPeriod = projSelDTO.getForecastDTO().getForecastEndYear();
            projectionStartPeriod = projSelDTO.getForecastDTO().getProjectionStartYear();
            projectionEndPeriod = projSelDTO.getForecastDTO().getProjectionEndYear();
        }
        historyEndMonth = projSelDTO.getForecastDTO().getHistoryEndMonth();
        historyEndYear = projSelDTO.getForecastDTO().getHistoryEndYear();
        if (historyEndPeriod == forecastStartPeriod && historyEndYear == projSelDTO.getForecastDTO().getForecastStartYear()) {
            historyEndPeriod--;
            if (frequencyDivision == 1) {
                historyEndMonth = NumericConstants.TWELVE;
                historyEndYear = historyEndYear - 1;
            } else if (historyEndPeriod < 1) {
                historyEndMonth = NumericConstants.TWELVE;
                historyEndYear = historyEndYear - 1;
            } else if (frequencyDivision == NumericConstants.TWO) {
                if (historyEndPeriod == 1) {
                    historyEndMonth = NumericConstants.SIX;
                } else if (historyEndPeriod == NumericConstants.TWO) {
                    historyEndMonth = NumericConstants.TWELVE;
                }
            } else if (frequencyDivision == NumericConstants.FOUR) {
                if (historyEndPeriod == 1) {
                    historyEndMonth = NumericConstants.THREE;
                } else if (historyEndPeriod == NumericConstants.TWO) {
                    historyEndMonth = NumericConstants.SIX;
                } else if (historyEndPeriod == NumericConstants.THREE) {
                    historyEndMonth = NumericConstants.NINE;
                } else if (historyEndPeriod == NumericConstants.FOUR) {
                    historyEndMonth = NumericConstants.TWELVE;
                }
            } else if (frequencyDivision == NumericConstants.TWELVE) {
                historyEndMonth = historyEndPeriod;
            }
        }

        projSelDTO.setFrequencyDivision(frequencyDivision);
        projSelDTO.addProjectionDetails(FREQUENCY_DIVISION.getConstant(), frequencyDivision);
        projSelDTO.addProjectionDetails(HISTORY_END_YEAR.getConstant(), historyEndYear);
        projSelDTO.addProjectionDetails(HISTORY_END_MONTH.getConstant(), historyEndMonth);
        projSelDTO.addProjectionDetails(HISTORY_START_YEAR.getConstant(), projSelDTO.getForecastDTO().getHistoryStartYear());
        projSelDTO.addProjectionDetails(HISTORY_START_MONTH.getConstant(), projSelDTO.getForecastDTO().getHistoryStartMonth());
        projSelDTO.addProjectionDetails(HISTORY_START_PERIOD.getConstant(), historyStartPeriod);
        projSelDTO.addProjectionDetails(HISTORY_END_PERIOD.getConstant(), historyEndPeriod);
        projSelDTO.addProjectionDetails(FORECAST_START_PERIOD.getConstant(), forecastStartPeriod);
        projSelDTO.addProjectionDetails(FORECAST_END_PERIOD.getConstant(), forecastEndPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_START_PERIOD.getConstant(), projectionStartPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_END_PERIOD.getConstant(), projectionEndPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_START_YEAR.getConstant(), projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.addProjectionDetails(PROJECTION_START_MONTH.getConstant(), projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.addProjectionDetails(PROJECTION_START_YEAR_DDLB.getConstant(), projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.addProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant(), projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.addProjectionDetails(HISTORY_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(FORECAST_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(PROJECTION_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(HISTORY_END_DAY.getConstant(), getEndDay(historyEndMonth, historyEndYear));
        projSelDTO.addProjectionDetails(FORECAST_END_DAY.getConstant(), getEndDay(projSelDTO.getForecastDTO().getForecastEndMonth(), projSelDTO.getForecastDTO().getForecastEndYear()));
        projSelDTO.addProjectionDetails(PROJECTION_END_DAY.getConstant(), getEndDay(projSelDTO.getForecastDTO().getProjectionEndMonth(), projSelDTO.getForecastDTO().getProjectionEndYear()));

        projSelDTO.setProjectionStartYear(projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.setProjectionStartMonth(projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.setHistoryEndYear(historyEndYear);
        projSelDTO.setHistoryEndMonth(historyEndMonth);
        projSelDTO.setHistoryStartPeriod(historyStartPeriod);
        projSelDTO.setHistoryEndPeriod(historyEndPeriod);
        projSelDTO.setForecastStartPeriod(forecastStartPeriod);
        projSelDTO.setForecastEndPeriod(forecastEndPeriod);
        projSelDTO.setProjectionStartPeriod(projectionStartPeriod);
        projSelDTO.setProjectionEndPeriod(projectionEndPeriod);
        projSelDTO.setHistoryEndDay(getEndDay(projSelDTO.getForecastDTO().getHistoryEndMonth(), projSelDTO.getForecastDTO().getHistoryEndYear()));
        projSelDTO.setForecastStartDay(1);
        projSelDTO.setForecastEndDay(getEndDay(projSelDTO.getForecastDTO().getForecastEndMonth(), projSelDTO.getForecastDTO().getForecastEndYear()));
        projSelDTO.setProjectionStartDay(1);
        projSelDTO.setProjectionEndDay(getEndDay(projSelDTO.getForecastDTO().getProjectionEndMonth(), projSelDTO.getForecastDTO().getProjectionEndYear()));

    }

    static ProjectionSelectionDTO getHistoryDetail(ProjectionSelectionDTO projSelDTO) {
        int startYear = projSelDTO.getCurrentYear();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int historyNum = projSelDTO.getHistoryNum();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        int startFreq = currentPeriod + 1;

        int tempFreq = historyNum - currentPeriod;
        if (tempFreq > 0) {
            startYear = startYear - tempFreq / frequencyDivision;
            startFreq = 1;
            if (tempFreq % frequencyDivision > 0) {
                startYear = startYear - 1;
                startFreq = frequencyDivision - (tempFreq % frequencyDivision) + 1;
            }
        } else {
            startFreq = startFreq - historyNum;
        }
        if (frequencyDivision == 1) {
            startYear = currentPeriod - historyNum;
        }

        projSelDTO.setStartYear(startYear);
        projSelDTO.setStartPeriod(startFreq);
        projSelDTO.setStartDay(1);
        if (frequencyDivision == 1) {
            projSelDTO.setStartMonth(frequencyDivision);
        } else if (frequencyDivision == NumericConstants.TWO) {
            if (startFreq == 1) {
                projSelDTO.setStartMonth(1);
            } else if (startFreq == NumericConstants.TWO) {
                projSelDTO.setStartMonth(NumericConstants.SEVEN);
            }
        } else if (frequencyDivision == NumericConstants.FOUR) {
            if (startFreq == 1) {
                projSelDTO.setStartMonth(1);
            } else if (startFreq == NumericConstants.TWO) {
                projSelDTO.setStartMonth(NumericConstants.FOUR);
            } else if (startFreq == NumericConstants.THREE) {
                projSelDTO.setStartMonth(NumericConstants.SEVEN);
            } else if (startFreq == NumericConstants.FOUR) {
                projSelDTO.setStartMonth(NumericConstants.TEN);
            }
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            projSelDTO.setStartMonth(startFreq);
        }
        return projSelDTO;
    }

    static ProjectionSelectionDTO getProjectionDetail(ProjectionSelectionDTO projSelDTO) {
        int endYear = projSelDTO.getCurrentYear();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int projectionNum = projSelDTO.getProjectionNum();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        int endFreq = currentPeriod + 1;
        int tmpFrq = projectionNum - (frequencyDivision - endFreq);

        if (tmpFrq > 0) {
            endYear = endYear + (tmpFrq / frequencyDivision);
            if (tmpFrq % frequencyDivision > 0) {
                endFreq = tmpFrq % frequencyDivision;
                endYear = endYear + 1;
            } else {
                endFreq = frequencyDivision;
            }
        } else {
            endFreq = endFreq + projectionNum;
        }
        if (frequencyDivision == 1) {
            endYear = currentPeriod + projectionNum;
        }
        projSelDTO.setEndYear(endYear);
        projSelDTO.setEndPeriod(endFreq);
        if (frequencyDivision == 1) {
            projSelDTO.setEndMonth(NumericConstants.TWELVE);
        } else if (frequencyDivision == NumericConstants.TWO) {
            if (endFreq == 1) {
                projSelDTO.setEndMonth(NumericConstants.SIX);
            } else if (endFreq == NumericConstants.TWO) {
                projSelDTO.setEndMonth(NumericConstants.TWELVE);
            }
        } else if (frequencyDivision == NumericConstants.FOUR) {
            if (endFreq == 1) {
                projSelDTO.setEndMonth(NumericConstants.THREE);
            } else if (endFreq == NumericConstants.TWO) {
                projSelDTO.setEndMonth(NumericConstants.SIX);
            } else if (endFreq == NumericConstants.THREE) {
                projSelDTO.setEndMonth(NumericConstants.NINE);
            } else if (endFreq == NumericConstants.FOUR) {
                projSelDTO.setEndMonth(NumericConstants.TWELVE);
            }
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            projSelDTO.setEndMonth(endFreq);
        }
        Calendar ob = Calendar.getInstance();
        ob.set(projSelDTO.getEndYear(), projSelDTO.getEndMonth() - 1, 1);
        int daysInMonth = ob.getActualMaximum(Calendar.DAY_OF_MONTH);
        projSelDTO.setEndDay(daysInMonth);
        return projSelDTO;
    }

    static int getEndDay(int monthNo, int year) {
        Calendar ob = Calendar.getInstance();
        ob.set(year, monthNo - 1, 1);
        int daysInMonth = ob.getActualMaximum(Calendar.DAY_OF_MONTH);
        return daysInMonth;
    }

    static int getPeriod(int monthNo, int division) {
        return ((monthNo - 1) / division) + 1;
    }
}
