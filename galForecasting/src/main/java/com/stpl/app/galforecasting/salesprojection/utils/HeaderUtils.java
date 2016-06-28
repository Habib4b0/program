/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.salesprojection.utils;

import com.stpl.app.galforecasting.dto.ForecastDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.CommonUtils;
import static com.stpl.app.galforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.ACTUALS;
import static com.stpl.app.galforecasting.utils.Constant.ANNUALLY;
import static com.stpl.app.galforecasting.utils.Constant.MONTHLY;
import static com.stpl.app.galforecasting.utils.Constant.PROJECTIONS;
import static com.stpl.app.galforecasting.utils.Constant.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author shyam.d
 */
public class HeaderUtils {

    private static final Logger LOGGER = Logger.getLogger(HeaderUtils.class);

    /**
     * The PMPY Available Product columns.
     */
    public static Object[] PMPY_PRODUCT_COLUMNS = new Object[]{Constant.PRODUCT_NO, Constant.PRODUCT_NAME};

    /**
     * The PMPY Available Product Headers.
     */
    public static String[] PMPY_PRODUCT_HEADER = new String[]{"Product #", "Product Name"};

    /**
     * The PMPY Available Product columns.
     */
    public static Object[] PMPY_COLUMNS = new Object[]{"actuals", "projections"};

    /**
     * The PMPY Available Product Headers.
     */
    public static String[] PMPY_HEADER = new String[]{Constant.ACTUALS, Constant.PROJECTIONS};

    /**
     * To load the Header
     *
     * @param visibleColumn
     * @param excelheader
     * @param columnHeader
     * @param tableHeaderDTO
     * @param excelHeader
     * @param dmap
     */
    private static void columnConfigure(String visibleColumn, String excelheader, String columnHeader, CustomTableHeaderDTO tableHeaderDTO, CustomTableHeaderDTO excelHeader, List<Object> dmap) {
        dmap.add(visibleColumn);
        tableHeaderDTO.addSingleColumn(visibleColumn, columnHeader, String.class);
        excelHeader.addSingleColumn(visibleColumn, excelheader, String.class);
    }
    
    private HeaderUtils() {

    }

    public static CustomTableHeaderDTO getSalesProjectionLeftTableColumns() {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();

        tableHeaderDTO.addSingleColumn(Constant.CHECK, " ", Boolean.class);
        tableHeaderDTO.addSingleColumn(Constant.LEVELNAME, "Level Name", String.class);
        tableHeaderDTO.addDoubleColumn("grouped", " ");
        tableHeaderDTO.addDoubleHeaderMap("grouped", new Object[]{Constant.CHECK, Constant.LEVELNAME});

        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSalesProjectionRightTableColumns(ProjectionSelectionDTO projectionDTO, CustomTableHeaderDTO fullHeader, CustomTableHeaderDTO excelHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getCalculatedSalesAllocationColumns(tableHeaderDTO, projectionDTO, fullHeader, excelHeader);
    }

    public static CustomTableHeaderDTO getCalculatedSalesAllocationColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO, CustomTableHeaderDTO excelHeader) {
        String projOrder = projSelDTO.getProjectionOrder();
        List<String> discountNames = new ArrayList<String>(projSelDTO.getDiscountNameList());
        String projections = projSelDTO.getActualsOrProjections();
        String pivotView = projSelDTO.getPivotView();
        String frequency = projSelDTO.getFrequency();
        Set massPopulateSet = new TreeSet();
         List<String> colList = new ArrayList<String>();
        CommonUtils.getHistoryAndProjectionDetails(projSelDTO);

        int historyNum = projSelDTO.getHistoryNum();
        int frequencyDivision = projSelDTO.getFrequencyDivision();

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
            projectionOrder = 2;
        }
        tableHeaderDTO.setFrequency(frequency);
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
        
        fullHeaderDTO.setFrequency(frequency);
        fullHeaderDTO.setFrequencyDivision(frequencyDivision);
        fullHeaderDTO.setHistoryNum(historyNum);
        fullHeaderDTO.setProjectionNum(projectionNum);
        fullHeaderDTO.setCurrentPeriod(currentPeriod);
        fullHeaderDTO.setCurrentYear(currentYear);
        fullHeaderDTO.setStartYear(startYear);
        fullHeaderDTO.setStartPeriod(startPeriod);
        fullHeaderDTO.setEndYear(endYear);
        fullHeaderDTO.setEndPeriod(endPeriod);
        fullHeaderDTO.setProjectionOrder(projectionOrder);
        prepareCommonColumnHeaders(projSelDTO);
        if (pivotView != null && pivotView.contains(Constant.VARIABLE)) {
            projSelDTO.setColumns(new ArrayList<String>());
            String commonColumn = StringUtils.EMPTY;
            String commonHeader = StringUtils.EMPTY;
            commonColumn = "uv";
            commonHeader = Constant.UNITS_CAPS;
            List<Object> dmap = new ArrayList<Object>();
            if (projections.contains(Constant.BOTH) || projections.contains(Constant.ACTUALS)) {
                Object singleColumn = commonColumn + Constant.ACTUALS;
                dmap.add(singleColumn);
                tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACTUAL_UNITS, String.class);
                fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + Constant.ACTUALS, String.class);
                excelHeader.addSingleColumn(singleColumn, Constant.ACTUAL_UNITS, String.class);
            }
            Object singleColumn = commonColumn + Constant.PROJECTIONS;
            dmap.add(singleColumn);
            tableHeaderDTO.addSingleColumn(singleColumn, Constant.PROJECTED_UNITS, String.class);
            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + Constant.PROJECTIONS, String.class);
            excelHeader.addSingleColumn(singleColumn, Constant.PROJECTED_UNITS, String.class);
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }
            projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        } else {
        List<String> periodList = projSelDTO.getPeriodList();
        Map<String, String> periodListMap = projSelDTO.getPeriodListMap();       
        for (int i = 0; i < periodList.size(); i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = periodList.get(i);
            String commonHeader = periodListMap.get(commonColumn);
            if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(projSelDTO.getScreenName())) {
                if (projSelDTO.getFrequencyDivision() != 1) {
                    StringBuilder str = new StringBuilder(commonColumn);
                    commonColumn = str.insert(commonColumn.length() - 4, '-').toString();
                }
                String[] variableArr = new String[projSelDTO.getVariableList().size()];
                variableArr = projSelDTO.getVariableList().toArray(variableArr);

                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    if (Constant.ACTUALS.equals(projSelDTO.getActualsOrProjections()) || Constant.BOTH.equals(projSelDTO.getActualsOrProjections())) {

                        columnConfigure(commonColumn + "-ActualReturned%", commonHeader + " Actual Returned %", "Actual Returned %", tableHeaderDTO, excelHeader, dmap);
                        columnConfigure(commonColumn + "-ActualRPU", commonHeader + " Actual RPU", "Actual RPU", tableHeaderDTO, excelHeader, dmap);
                        columnConfigure(commonColumn + "-ActualReturnedAmount", commonHeader + " Actual Returned Amount", "Actual Returned Amount", tableHeaderDTO, excelHeader, dmap);

                    }
                    if ((Constant.PROJECTIONS.equals(projSelDTO.getActualsOrProjections()) || Constant.BOTH.equals(projSelDTO.getActualsOrProjections())) && i < projSelDTO.getProjectionStartIndex()) {

                        columnConfigure(commonColumn + "-HistoryProjectedReturn%", commonHeader + " Projected Return %", "Projected Return %", tableHeaderDTO, excelHeader, dmap);
                        columnConfigure(commonColumn + "-HistoryProjectedRPU", commonHeader + "Projected RPU", "Projected RPU", tableHeaderDTO, excelHeader, dmap);
                        columnConfigure(commonColumn + "-HistoryProjectedReturnAmount", commonHeader + "Projected Return Amount", "Projected Return Amount", tableHeaderDTO, excelHeader, dmap);
                        columnConfigure(commonColumn + "-HistoryGrowthRate", commonHeader + "Growth Rate", "Growth Rate", tableHeaderDTO, excelHeader, dmap);
                        colList.add(commonColumn);
                        projSelDTO.setCommonColumn(colList);
                    }
                    tableHeaderDTO.addDoubleHistoryColumn(commonColumn, commonHeader);
                }
                if (i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex()) {
                    columnConfigure(commonColumn + "-ProjectedReturn%", commonHeader + " Projected Return %", "Projected Return %", tableHeaderDTO, excelHeader, dmap);
                    columnConfigure(commonColumn + "-ProjectedRPU", commonHeader + "Projected RPU", "Projected RPU", tableHeaderDTO, excelHeader, dmap);
                    columnConfigure(commonColumn + "-ProjectedReturnAmount", commonHeader + "Projected Return Amount", "Projected Return Amount", tableHeaderDTO, excelHeader, dmap);
                    columnConfigure(commonColumn + "-GrowthRate", commonHeader + "Growth Rate", "Growth Rate", tableHeaderDTO, excelHeader, dmap);
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                }

                if (i > projSelDTO.getProjectionEndIndex() && i <= projSelDTO.getForecastEndIndex()) {

                    columnConfigure(commonColumn + "-ProjectedReturn%", commonHeader + " Projected Return %", "Projected Return %", tableHeaderDTO, excelHeader, dmap);
                    columnConfigure(commonColumn + "-ProjectedRPU", commonHeader + "Projected RPU", "Projected RPU", tableHeaderDTO, excelHeader, dmap);
                    columnConfigure(commonColumn + "-HistoryProjectedSales", commonHeader + " Projected Sales", "Projected Sales", tableHeaderDTO, excelHeader, dmap);
                    columnConfigure(commonColumn + "-ProjectedReturnAmount", commonHeader + "Projected Return Amount", "Projected Return Amount", tableHeaderDTO, excelHeader, dmap);
                    columnConfigure(commonColumn + "-GrowthRate", commonHeader + "Growth Rate", "Growth Rate", tableHeaderDTO, excelHeader, dmap);
                }
            } else {
                if (projSelDTO.getFrequencyDivision() != 1) {
                    StringBuilder str = new StringBuilder(commonColumn);
                    commonColumn = str.insert(commonColumn.length() - 4, '-').toString();
                }
                String[] variableArr = new String[projSelDTO.getVariableList().size()];
                variableArr = projSelDTO.getVariableList().toArray(variableArr);

                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    if (Constant.ACTUALS.equals(projSelDTO.getActualsOrProjections()) || Constant.BOTH.equals(projSelDTO.getActualsOrProjections())) {
                        for (String value : variableArr) {
                            value = value.trim();
                            switch (value) {
                                case Constant.SALES_SMALL:
                                    columnConfigure(commonColumn + "-ActualSales", commonHeader + " Actual Sales", Constant.ACTUAL_SALES, tableHeaderDTO, excelHeader, dmap);
                                    break;
                                case Constant.UNITS_SMALL:
                                    columnConfigure(commonColumn + "-ActualUnits", commonHeader + " Actual Units", "Actual Units", tableHeaderDTO, excelHeader, dmap);
                                    break;
                            }
                        }
                    }
                    if ((Constant.PROJECTIONS.equals(projSelDTO.getActualsOrProjections()) || Constant.BOTH.equals(projSelDTO.getActualsOrProjections())) && i < projSelDTO.getProjectionStartIndex()) {
                        for (String value : variableArr) {
                            value = value.trim();
                            switch (value) {
                                case Constant.SALES_SMALL:
                                    columnConfigure(commonColumn + "-HistoryProjectedSales", commonHeader + " Projected Sales", "Projected Sales", tableHeaderDTO, excelHeader, dmap);
                                    break;
                                case Constant.UNITS_SMALL:
                                    columnConfigure(commonColumn + "-HistoryProjectedUnits", commonHeader + " Projected Units", "Projected Units", tableHeaderDTO, excelHeader, dmap);
                                    break;
                            }
                        }
                    }
                    tableHeaderDTO.addDoubleHistoryColumn(commonColumn, commonHeader);
                }
                if (i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex()) {
                    for (String value : variableArr) {
                        value = value.trim();
                        massPopulateSet.add(commonHeader);
                        switch (value) {
                            case Constant.SALES_SMALL:
                                columnConfigure(commonColumn + "-ProjectedSales", commonHeader + " Projected Sales", "Projected Sales", tableHeaderDTO, excelHeader, dmap);
                                break;
                            case Constant.UNITS_SMALL:
                                columnConfigure(commonColumn + "-ProjectedUnits", commonHeader + " Projected Units", "Projected Units", tableHeaderDTO, excelHeader, dmap);
                                break;
                            case Constant.PRODUCT_GROWTH:
                                columnConfigure(commonColumn + "-ProductGrowth", commonHeader + " Product Growth", Constant.PRODUCT_GROWTH, tableHeaderDTO, excelHeader, dmap);
                                break;
                            case Constant.ACCOUNT_GROWTH:
                                columnConfigure(commonColumn + "-AccountGrowth", commonHeader + " Account Growth", Constant.ACCOUNT_GROWTH, tableHeaderDTO, excelHeader, dmap);
                                break;
                        }
                    }
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                }

                if (i > projSelDTO.getProjectionEndIndex() && i <= projSelDTO.getForecastEndIndex()) {
                    for (String value : variableArr) {
                        value = value.trim();
                        switch (value) {
                            case Constant.SALES_SMALL:                               

                             columnConfigure(commonColumn + "-ProjectedSalesDis", commonHeader + " Projected Sales", "Projected Sales", tableHeaderDTO, excelHeader, dmap);
                                break;
                            case Constant.UNITS_SMALL:
                                columnConfigure(commonColumn + "-ProjectedUnitsDis", commonHeader + " Projected Units", "Projected Units", tableHeaderDTO, excelHeader, dmap);
                                break;
                            case Constant.PRODUCT_GROWTH:
                                columnConfigure(commonColumn + "-ProductGrowthDis", commonHeader + " Product Growth", Constant.PRODUCT_GROWTH, tableHeaderDTO, excelHeader, dmap);
                                break;
                            case Constant.ACCOUNT_GROWTH:
                                columnConfigure(commonColumn + "-AccountGrowthDis", commonHeader + " Account Growth", Constant.ACCOUNT_GROWTH, tableHeaderDTO, excelHeader, dmap);
                                break;
                        }
                    }
                }
            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());

                fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }
        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        List massPopulateList = new ArrayList();
        for (Object object : massPopulateList) {
            massPopulateList.add(object);
        }
        projSelDTO.setProjectedList(massPopulateList);
        }
        return tableHeaderDTO;
    }

    public static void prepareCommonColumnHeaders(ProjectionSelectionDTO projSelDTO) {

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projOrder = projSelDTO.getProjectionOrder();
        int projectionOrder = 0;
        if (projOrder.contains(ASCENDING.getConstant())) {
            projectionOrder = 1;
        } else {
            projectionOrder = 2;
        }
        String yearValue = projSelDTO.getYear();
        int year = isInteger(yearValue) ? Integer.valueOf(yearValue) : 0;
        int historyStartIndex = -1;
        int projectionStartIndex = -1;
        int forecastStartIndex = -1;
        int historyEndIndex = -1;
        int projectionEndIndex = -1;
        int forecastEndIndex = -1;
        List<String> periodList = new ArrayList<String>();
        Map<String, String> periodListMap = new HashMap<String, String>();
        int startPr = 1;
        int lastPr = frequencyDivision;
        if (year != 0) {
            if (year == projSelDTO.getHistoryStartYear()) {
                startPr = projSelDTO.getHistoryStartPeriod();
            }
            if (year == projSelDTO.getForecastDTO().getForecastEndYear()) {
                lastPr = projSelDTO.getForecastEndPeriod();
            }

            for (int pr = startPr; pr <= lastPr; pr++) {
                List<String> common = getCommonColumnHeader(frequencyDivision, year, pr);
                String commonColumn = common.get(0);
                String commonHeader = common.get(1);
                if ((year > projSelDTO.getHistoryStartYear() && year < projSelDTO.getHistoryEndYear()) || (year == projSelDTO.getHistoryStartYear() && pr >= projSelDTO.getHistoryStartPeriod()) || (year == projSelDTO.getHistoryEndYear() && pr <= projSelDTO.getHistoryEndPeriod())) {
                    if (historyStartIndex == -1) {
                        historyStartIndex = periodList.size();
                    }
                    historyEndIndex = periodList.size();
                }
                if ((year > projSelDTO.getProjectionStartYear() && year < projSelDTO.getForecastDTO().getProjectionEndYear()) || (year == projSelDTO.getProjectionStartYear() && pr >= projSelDTO.getProjectionStartPeriod()) || (year == projSelDTO.getForecastDTO().getProjectionEndYear() && pr <= projSelDTO.getProjectionEndPeriod())) {

                    if (projectionStartIndex == -1) {
                        projectionStartIndex = periodList.size();
                    }
                    projectionEndIndex = periodList.size();
                }
                if ((year > projSelDTO.getForecastDTO().getForecastStartYear() && year < projSelDTO.getForecastDTO().getForecastEndYear()) || (year == projSelDTO.getForecastDTO().getForecastStartYear() && pr >= projSelDTO.getForecastStartPeriod()) || (year == projSelDTO.getForecastDTO().getForecastEndYear() && pr <= projSelDTO.getForecastEndPeriod())) {

                    if (forecastStartIndex == -1) {
                        forecastStartIndex = periodList.size();
                    }
                    forecastEndIndex = periodList.size();
                }
                periodList.add(commonColumn);
                periodListMap.put(commonColumn, commonHeader);
            }
        } else {
            List<String> projectionColumnList = new ArrayList<String>();
            List<String> projectionHeaderList = new ArrayList<String>();
            startPr = projSelDTO.getProjectionStartPeriod() == 0 ? 1 : projSelDTO.getProjectionStartPeriod();

            // To Calculate the projection headers list and projection columns list
            for (int yr = projSelDTO.getProjectionStartYear(); yr <= projSelDTO.getForecastDTO().getProjectionEndYear(); yr++) {
                if (yr == projSelDTO.getForecastDTO().getProjectionEndYear()) {
                    lastPr = projSelDTO.getProjectionEndPeriod();
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    projectionColumnList.add(commonColumn);
                    projectionHeaderList.add(commonHeader);
                }
                startPr = 1;
            }

            startPr = projSelDTO.getHistoryStartPeriod() == 0 ? 1 : projSelDTO.getHistoryStartPeriod();
            lastPr = frequencyDivision;
            historyStartIndex = 0;
            for (int yr = projSelDTO.getHistoryStartYear(); yr <= projSelDTO.getHistoryEndYear(); yr++) {
                if (yr == projSelDTO.getHistoryEndYear()) {
                    lastPr = projSelDTO.getHistoryEndPeriod();
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    if (projectionColumnList.contains(commonColumn)) {
                        projectionColumnList.remove(commonColumn);
                        projectionHeaderList.remove(commonHeader);
                        if (projectionStartIndex == -1) {
                            projectionStartIndex = periodList.size();
                        }
                    }
                    periodList.add(commonColumn);
                    periodListMap.put(commonColumn, commonHeader);
                }
                startPr = 1;
            }
            historyEndIndex = periodList.size() - 1;
            for (int i = 0; i < projectionColumnList.size(); i++) {
                String commonColumn = projectionColumnList.get(i);
                String commonHeader = projectionHeaderList.get(i);
                if (projectionStartIndex == -1) {
                    projectionStartIndex = periodList.size();
                }
                periodList.add(commonColumn);
                periodListMap.put(commonColumn, commonHeader);
            }
            projectionEndIndex = periodList.size() - 1;

            startPr = projSelDTO.getForecastStartPeriod() == 0 ? 1 : projSelDTO.getForecastStartPeriod();
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

                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    if (forecastStartIndex == -1) {
                        if (periodList.contains(commonColumn)) {
                            forecastStartIndex = periodList.indexOf(commonColumn);
                        } else {
                            forecastStartIndex = periodList.size();
                        }
                    }
                    if (!periodList.contains(commonColumn)) {
                        periodList.add(commonColumn);
                        periodListMap.put(commonColumn, commonHeader);
                    }
                }
                startPr = 1;
            }
            forecastEndIndex = periodList.size() - 1;
        }
        if (projectionOrder != 1) {
            String historyStart = null;
            String projectionStart = null;
            String forecastStart = null;
            String historyEnd = null;
            String projectionEnd = null;
            String forecastEnd = null;

            if (historyStartIndex >= 0) {
                historyStart = periodList.get(historyStartIndex);
            }
            if (projectionStartIndex >= 0) {
                projectionStart = periodList.get(projectionStartIndex);
            }
            if (forecastStartIndex >= 0) {
                forecastStart = periodList.get(forecastStartIndex);
            }
            if (historyEndIndex >= 0) {
                historyEnd = periodList.get(historyEndIndex);
            }
            if (projectionEndIndex >= 0) {
                projectionEnd = periodList.get(projectionEndIndex);
            }
            if (forecastEndIndex >= 0) {
                forecastEnd = periodList.get(forecastEndIndex);
            }

            Collections.reverse(periodList);

            historyStartIndex = -1;
            projectionStartIndex = -1;
            forecastStartIndex = -1;
            historyEndIndex = -1;
            projectionEndIndex = -1;
            forecastEndIndex = -1;

            if (historyEnd != null) {
                historyStartIndex = periodList.indexOf(historyEnd);
            }
            if (projectionEnd != null) {
                projectionStartIndex = periodList.indexOf(projectionEnd);
            }
            if (forecastEnd != null) {
                forecastStartIndex = periodList.indexOf(forecastEnd);
            }
            if (historyStart != null) {
                historyEndIndex = periodList.indexOf(historyStart);
            }
            if (projectionStart != null) {
                projectionEndIndex = periodList.indexOf(projectionStart);
            }
            if (forecastStart != null) {
                forecastEndIndex = periodList.indexOf(forecastStart);
            }
        }
        projSelDTO.setPeriodList(periodList);
        projSelDTO.setPeriodListMap(periodListMap);
        projSelDTO.setHistoryStartIndex(historyStartIndex);
        projSelDTO.setHistoryEndIndex(historyEndIndex);
        projSelDTO.setProjectionStartIndex(projectionStartIndex);
        projSelDTO.setProjectionEndIndex(projectionEndIndex);
        projSelDTO.setForecastStartIndex(forecastStartIndex);
        projSelDTO.setForecastEndIndex(forecastEndIndex);

    }

    public static List<String> getCommonColumnHeader(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<String>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == 4) {
            commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = Constant.Q + period + " " + year;
        } else if (frequencyDivision == 2) {
            commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = Constant.S + period + " " + year;
        } else if (frequencyDivision == 12) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    /**
     * Loads the Header for Sales Projection Left Tables.
     *
     * @return - CustomTableHeaderDTO
     */
    public static CustomTableHeaderDTO getSalesLeftTableColumns(final ProjectionSelectionDTO projectionSelectionDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        if ("Alternate_History".equals(projectionSelectionDTO.getFunctionality())) {
            tableHeaderDTO.addSingleColumn(Constant.LEVELNAME, "Level Name", String.class);
            tableHeaderDTO.addDoubleColumn("group1", " ");
            tableHeaderDTO.addDoubleHeaderMap("group1", new Object[]{Constant.LEVELNAME});
        } else {
            tableHeaderDTO.addSingleColumn(Constant.CHECK, " ", Boolean.class);
            tableHeaderDTO.addSingleColumn(Constant.LEVELNAME, "Level Name", String.class);
            if (projectionSelectionDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                tableHeaderDTO.addSingleColumn(Constant.GROUP, "Group", String.class);
            }
            tableHeaderDTO.addSingleColumn(Constant.BASELINE, "Base Line", String.class);
            tableHeaderDTO.addSingleColumn(Constant.METHODOLOGY, "Methodology", String.class);
            tableHeaderDTO.addDoubleColumn("group1", " ");
            tableHeaderDTO.addDoubleHeaderMap("group1", new Object[]{Constant.CHECK, Constant.LEVELNAME, Constant.GROUP, Constant.BASELINE, Constant.METHODOLOGY});
        }
        return tableHeaderDTO;
    }

    /**
     * Loads the Header for Sales Projection Right Tables and for Excel Export.
     *
     * @param selection
     * @param sessionDTO
     * @return List - CustomTableHeaderDTO
     */
    public static List<CustomTableHeaderDTO> getSalesProjectionRightTableColumns(Map selection, SessionDTO sessionDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        CustomTableHeaderDTO excelDto = new CustomTableHeaderDTO();
        excelDto.addSingleColumn(Constant.CHECK, " ", Boolean.class);
        excelDto.addSingleColumn(Constant.LEVELNAME, "Level Name", String.class);
        excelDto.addSingleColumn(Constant.BASELINE, "Base Line", String.class);
        excelDto.addSingleColumn(Constant.METHODOLOGY, "Methodology", String.class);
        return getCalculatedSalesColumnsforSales(selection, tableHeaderDTO, excelDto, sessionDTO);
    }

    /**
     * Loads the Header for the Sales Projection Right Table based on Forecast
     * Configuration, History and Projections.
     *
     * @param selection
     * @param tableHeaderDTO
     * @param excelDto
     * @param session
     * @return
     */
    public static List getCalculatedSalesColumnsforSales(Map selection, CustomTableHeaderDTO tableHeaderDTO, CustomTableHeaderDTO excelDto, SessionDTO session) {
        ForecastDTO forecastDTO = session.getForecastDTO();

        Map<Object, Object[]> reProjectedColumn = new HashMap<Object, Object[]>();
        Map<Object, Object[]> doubleHeaderHistoryMap = new HashMap<Object, Object[]>();
        List<String> totalProjected = new ArrayList<String>();

        String hist = selection.get(Constant.HISTORY).toString();
        String projFreq = selection.get("projectFrequency").toString();

        String projection = selection.get("actualsorprojections").toString();

        String salesVar = (String) selection.get(Constant.SALES);
        String unitsVar = (String) selection.get(Constant.UNITS);
        String pGrowthVar = (String) selection.get(Constant.P_Growth);
        String aGrowthVar = (String) selection.get(Constant.A_Growth);

        int curMonth = forecastDTO.getForecastStartMonth() - 1;
        int curYear = forecastDTO.getForecastStartYear();
        boolean reprojectionAllowed = false;
        boolean projAllowed = true;

        int current = (curMonth / 3);
        int frequency = Integer.valueOf(hist.replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
        int projectFrequency = Integer.valueOf(projFreq);
        int division = 4;
        int pastYear = curYear;
        int startFreq = current + 1;
        int tempFreq = frequency - current;
        if (tempFreq > 0) {
            pastYear = pastYear - tempFreq / division;
            startFreq = 1;
            if (tempFreq % division > 0) {
                pastYear = pastYear - 1;
                startFreq = division - (tempFreq % division) + 1;
            }
        } else {
            startFreq = startFreq - frequency;
        }

        int squr = startFreq;
        int syear = pastYear;

        int reprojtionStartQuator = getQuarter(forecastDTO.getProjectionStartMonth());
        int reprojtionStartYear = forecastDTO.getProjectionStartYear();
        int reprojtionEndQuator = getQuarter(forecastDTO.getProjectionEndMonth());
        int reprojtionEndYear = forecastDTO.getProjectionEndYear();
        for (int i = 0; i < frequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            List<Object> historyObj = new ArrayList<Object>();
            List<Object> projectionObj = new ArrayList<Object>();

            String commonColumn = Constant.Q_SMALL + squr + "-" + syear;
            String commonHeader = Constant.Q + squr + " " + syear;

            if (syear >= reprojtionStartYear && syear <= reprojtionEndYear) {

                if (syear == reprojtionStartYear) {
                    if (squr >= reprojtionStartQuator) {
                        reprojectionAllowed = true;
                    } else {
                        reprojectionAllowed = false;
                    }
                } else if (syear == reprojtionEndYear) {
                    if (squr <= reprojtionEndQuator) {
                        reprojectionAllowed = true;
                    } else {
                        reprojectionAllowed = false;
                    }
                } else {
                    reprojectionAllowed = true;
                }
            }

            if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS)) {
                if (salesVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ActualSales";
                    dmap.add(singleColumn);
                    historyObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACTUAL_SALES, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.ACTUAL_SALES);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Actual Sales", String.class);
                }
            }
            if (!reprojectionAllowed) {
                if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS)) {
                    if (salesVar.equals(Constant.TRUE)) {
                        String singleColumn = commonColumn + "-HistoryProjectedSales";
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projected Sales", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, "Projected Sales");
                        excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Sales", String.class);
                    }
                }
            } else {
                if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS)) {
                    if (salesVar.equals(Constant.TRUE)) {
                        String singleColumn = commonColumn + "-ProjectedSales";
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        projectionObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projected Sales", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, "Projected Sales");
                        excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Sales", String.class);
                    }

                }
            }
            if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS)) {

                if (unitsVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ActualUnits";
                    dmap.add(singleColumn);
                    historyObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Actual Units", String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, "Actual Units");
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Actual Units", String.class);
                }
            }
            if (!reprojectionAllowed) {
                if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS)) {
                    if (unitsVar.equals(Constant.TRUE)) {
                        String singleColumn = commonColumn + "-HistoryProjectedUnits";
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projected Units", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, "Projected Units");
                        excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Units", String.class);
                    }

                }

            } else {
                if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS)) {
                    if (unitsVar.equals(Constant.TRUE)) {
                        String singleColumn = commonColumn + "-ProjectedUnits";
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        projectionObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projected Units", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, "Projected Units");
                        excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Units", String.class);
                    }

                }

                if (pGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProductGrowth";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.PRODUCT_GROWTH, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.PRODUCT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Product Growth", String.class);
                }

                if (aGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-AccountGrowth";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACCOUNT_GROWTH, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.ACCOUNT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Account Growth", String.class);
                }
                totalProjected.add(commonHeader);
            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleHistoryColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHistoryHeaderMap(commonColumn, dmap.toArray());
                doubleHeaderHistoryMap.put(commonColumn, historyObj.toArray());
                reProjectedColumn.put(commonColumn, projectionObj.toArray());
            }

            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
        }
        squr = current + 1;
        syear = curYear;
        for (int i = 0; i < projectFrequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            List<Object> projectionObj = new ArrayList<Object>();
            String commonColumn = Constant.Q_SMALL + squr + "-" + syear;
            String commonHeader = Constant.Q + squr + " " + syear;

            if (syear >= reprojtionStartYear && syear <= reprojtionEndYear) {

                if (syear == reprojtionStartYear) {
                    if (squr >= reprojtionStartQuator) {
                        projAllowed = true;
                    } else {
                        projAllowed = false;
                    }
                } else if (syear == reprojtionEndYear) {
                    if (squr <= reprojtionEndQuator) {
                        projAllowed = true;
                    } else {
                        projAllowed = false;
                    }
                } else {
                    projAllowed = true;
                }
            } else if (syear > reprojtionEndYear) {
                projAllowed = false;

            }

            if (projAllowed) {

                if (salesVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProjectedSales";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Projected Sales", String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, "Projected Sales");
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Sales", String.class);
                }
                if (unitsVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProjectedUnits";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Projected Units", String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, "Projected Units");
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Units", String.class);
                }
                if (pGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProductGrowth";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.PRODUCT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.PRODUCT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Product Growth", String.class);
                }
                if (aGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-AccountGrowth";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACCOUNT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.ACCOUNT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Account Growth", String.class);
                }

                reprojtionStartQuator++;
                if (reprojtionStartQuator > division) {
                    reprojtionStartQuator = 1;
                }

                totalProjected.add(commonHeader);
            }
       

            if (!projAllowed) {

                if (salesVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProjectedSalesDis";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Projected Sales", String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, "Projected Sales");
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Sales", String.class);
                }
                if (unitsVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProjectedUnitsDis";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Projected Units", String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, "Projected Units");
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Units", String.class);
                }
                if (pGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProductGrowthDis";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.PRODUCT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.PRODUCT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Product Growth", String.class);
                }
                if (aGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-AccountGrowthDis";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACCOUNT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.ACCOUNT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Account Growth", String.class);
                }

            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleProjectedHeaderMap(commonColumn, dmap.toArray());
                reProjectedColumn.put(commonColumn, projectionObj.toArray());

            }
            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
        }

        List headerList = new ArrayList();
        headerList.add(tableHeaderDTO);
        headerList.add(excelDto);
        headerList.add(totalProjected);
        headerList.add(doubleHeaderHistoryMap);
        return headerList;
    }

    /**
     * Returns the Quarter based on Month.
     *
     * @param month
     * @return
     */
    public static int getQuarter(int month) {
        if (month < 4) {
            return 1;
        }
        if (month < 7) {
            return 2;
        }
        if (month < 10) {
            return 3;
        }
        return 4;
    }

    public static CustomTableHeaderDTO getPMPYTableColumns(String frequency, int frequencyRange, boolean isExcelExport) {
        LOGGER.info("----Inside getPMPYTableColumns() -----");
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getPMPYCalculatedColumns(tableHeaderDTO, frequency, frequencyRange, isExcelExport);
    }

    public static CustomTableHeaderDTO getPMPYCalculatedColumns(CustomTableHeaderDTO tableHeaderDTO, String frequency, int frequencyRange, boolean isExcelExport) {

        Calendar ob = Calendar.getInstance();
        int curMonth = ob.get(Calendar.MONTH);
        int curYear = ob.get(Calendar.YEAR);
        int current = 1;
        int division = 1;
        if (frequency.equals(QUARTERLY)) {
            current = (curMonth / 3);
            division = 4;
        } else if (frequency.equals(SEMI_ANNUALLY)) {
            current = (curMonth / 6);
            division = 2;
        } else if (frequency.equals(MONTHLY)) {
            current = curMonth;
            division = 12;
        } else if (frequency.equals(ANNUALLY)) {
            current = curYear;
            division = 1;
        }
        int pastYear = curYear;
        pastYear = pastYear - 3;
        if (!frequency.equals(ANNUALLY)) {
            int tempFreq = frequencyRange + current - 1;
            frequencyRange = tempFreq + 1;
        } else {
            frequencyRange = 3;
        }
        int squr = 1;
        int syear = pastYear;

        for (int i = 0; i < frequencyRange; i++) {

            if (isExcelExport) {
                tableHeaderDTO.addSingleColumn("firstColumn", StringUtils.EMPTY, String.class);
                isExcelExport = false;
            }

            List<Object> doubleHeaderMap = new ArrayList<Object>();
            List<Object> tripleHeaderMap = new ArrayList<Object>();
            String commonColumn = StringUtils.EMPTY;
            String commonHeader = StringUtils.EMPTY;
            if (frequency.contains(QUARTERLY)) {
                commonColumn = Constant.Q + squr + StringUtils.EMPTY + syear;
                commonHeader = Constant.Q + squr + " " + syear + "    ";
            } else if (frequency.contains(SEMI_ANNUALLY.getConstant())) {
                commonColumn = Constant.S + squr + StringUtils.EMPTY + syear;
                commonHeader = Constant.S + squr + " " + syear + "    ";
            } else if (frequency.contains(ANNUALLY)) {
                commonColumn = StringUtils.EMPTY + syear;
                commonHeader = StringUtils.EMPTY + syear + "    ";
            } else if (frequency.contains(MONTHLY)) {
                String monthName = getMonthForInt(squr - 1);
                commonColumn = (monthName + syear).toUpperCase();
                commonHeader = monthName + " " + syear + "    ";
            }

            doubleHeaderMap.add(commonColumn);

            tripleHeaderMap.add(commonColumn);
            tableHeaderDTO.addSingleColumn(commonColumn, commonHeader, String.class);

            if (!doubleHeaderMap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, PROJECTIONS);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, doubleHeaderMap.toArray());
            }

            if (!tripleHeaderMap.isEmpty()) {
                tableHeaderDTO.addTripleColumn(commonColumn, ACTUALS);
                tableHeaderDTO.addTripleHeaderMap(commonColumn, tripleHeaderMap.toArray());
            }

            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
        }

        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getProjectionReturnsLeftTableColumns(final ProjectionSelectionDTO projectionSelectionDTO, CustomTableHeaderDTO excelDto) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn(Constant.CHECK, " ", Boolean.class);
        tableHeaderDTO.addSingleColumn(Constant.LEVELNAME, Constant.BRAND_CAPS, String.class);
        tableHeaderDTO.addSingleColumn("log", "Lag", String.class);
        tableHeaderDTO.addSingleColumn("closedDate", "Closed Date", String.class);
        tableHeaderDTO.addSingleColumn("loeDate", "LOE Date", String.class);
        tableHeaderDTO.addDoubleColumn("group1", " ");
        tableHeaderDTO.addDoubleHeaderMap("group1", new Object[]{Constant.CHECK, Constant.LEVELNAME, "log", "closedDate", "loeDate"});

        excelDto.addSingleColumn(Constant.CHECK, " ", Boolean.class);
        excelDto.addSingleColumn(Constant.LEVELNAME, Constant.BRAND_CAPS, String.class);
        excelDto.addSingleColumn("log", "Lag", String.class);
        excelDto.addSingleColumn("closedDate", "Closed Date", String.class);
        excelDto.addSingleColumn("loeDate", "LOE Date", String.class);
        excelDto.addDoubleColumn("group1", " ");
        excelDto.addDoubleHeaderMap("group1", new Object[]{Constant.CHECK, Constant.LEVELNAME, "log", "closedDate", "loeDate"});
        return tableHeaderDTO;
    }
}
