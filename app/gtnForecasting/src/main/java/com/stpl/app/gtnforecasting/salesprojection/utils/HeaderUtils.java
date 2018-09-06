/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.utils;

import com.stpl.app.gtnforecasting.dto.ForecastDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.ACTUALS;
import static com.stpl.app.gtnforecasting.utils.Constant.ANNUALLY;
import static com.stpl.app.gtnforecasting.utils.Constant.MONTHLY;
import static com.stpl.app.gtnforecasting.utils.Constant.PROJECTIONS;
import static com.stpl.app.gtnforecasting.utils.Constant.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author shyam.d
 */
public class HeaderUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeaderUtils.class);

    /**
     * The PMPY Available Product columns.
     */
    private static final Object[] pmpyProductColumns = new Object[]{Constant.PRODUCT_NO, Constant.PRODUCT_NAME};

    /**
     * The PMPY Available Product Headers.
     */
    private static final String[] pmpyProductHeader = new String[]{"Product #", "Product Name"};

    /**
     * The PMPY Available Product columns.
     */
    private static final Object[] pmpyColumns = new Object[]{"actuals", "projections"};
    public static final String PRODUCT_GROWTH1 = " Product Growth";
    public static final String PRODUCT_GROWTH1_SUM = " Product Growth Sum";
    public static final String PROJECTED_UNITS1 = " Projected Units";
    public static final String PROJECTED_SALES1 = "Projected Sales";

    /**
     * The PMPY Available Product Headers.
     */
    private static final String[] pmpyHeader = new String[]{Constant.ACTUALS, Constant.PROJECTIONS};

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
    //Added for sum column
    private static void columnConfigureSum(String visibleColumn, String excelheader, CustomTableHeaderDTO excelHeader, List<Object> dmapsum) {
        dmapsum.add(visibleColumn);
        excelHeader.addSingleColumn(visibleColumn, excelheader, String.class);
    }
    
    private HeaderUtils() {

    }
    
    public static CustomTableHeaderDTO getSalesProjectionLeftTableColumns() {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();

        tableHeaderDTO.addSingleColumn(Constant.CHECK, " ", Boolean.class);
        tableHeaderDTO.addSingleColumn(Constant.LEVEL_NAME, Constant.LEVEL_NAME_LABEL, String.class);
        tableHeaderDTO.addDoubleColumn("grouped", " ");
        tableHeaderDTO.addDoubleHeaderMap("grouped", new Object[]{Constant.CHECK, Constant.LEVEL_NAME});

        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSalesProjectionRightTableColumns(ProjectionSelectionDTO projectionDTO, CustomTableHeaderDTO fullHeader, CustomTableHeaderDTO excelHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getCalculatedSalesAllocationColumns(tableHeaderDTO, projectionDTO, fullHeader, excelHeader);
    }

    public static CustomTableHeaderDTO getCalculatedSalesAllocationColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO, CustomTableHeaderDTO excelHeader) {
        String projOrder = projSelDTO.getProjectionOrder();
        String projections = projSelDTO.getActualsOrProjections();
        String pivotView = projSelDTO.getPivotView();
        String frequency = projSelDTO.getFrequency();
         List<String> colList = new ArrayList<>();
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
            projectionOrder = NumericConstants.TWO;
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
        Map<Integer, List> periodListMapForExcel = new HashMap<>();
        if (pivotView != null && pivotView.contains(Constant.VARIABLE)) {
            projSelDTO.setColumns(new ArrayList<String>());
            String commonColumn;
            String commonHeader;
            commonColumn = "uv";
            commonHeader = Constant.UNITS_CAPS;
            List<Object> dmap = new ArrayList<>();
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
            try{
                List<String> periodList = projSelDTO.getPeriodList();
                Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
                List<Object> singleColumnForExcel = new ArrayList<>();//Added for tabwise excel export
                List<String> singleHeaderForExcel = new ArrayList<>();
                List<Object> doubleColumnForExcel = new ArrayList<>();
                List<String> doubleHeaderForExcel = new ArrayList<>();
                Map<Object, Object[]> doubleHeaderMap = new HashMap<>();
                List headerListForExcel;
                int sizeToCopy = excelHeader.getSingleColumns().size();//Added for tabwise excel export
                List<Object> singleColumns = new ArrayList<>(excelHeader.getSingleColumns().subList(0, sizeToCopy));
                List<String> singleHeaders = new ArrayList<>(excelHeader.getSingleHeaders().subList(0, sizeToCopy));//Ends here

                int tempYear, j = 0, k = 0;//Ends here
                for (int i = 0; i < periodList.size(); i++) {
                    List<Object> dmap = new ArrayList<>();
                    List<Object> dmapsum = new ArrayList<>();
                    String commonColumn = periodList.get(i);
                    String commonHeader = periodListMap.get(commonColumn);

                    boolean excelTab = frequencyDivision == NumericConstants.FOUR || frequencyDivision == NumericConstants.TWELVE;//Added for tabwise excel export
                    tempYear = excelTab ? frequencyDivision == NumericConstants.FOUR ? Integer.parseInt(commonColumn.substring(NumericConstants.TWO, commonColumn.length()))
                            : Integer.parseInt(commonColumn.replaceAll("[^\\d.]", StringUtils.EMPTY)) : 0;
                    if (k != tempYear) {

                        singleColumnForExcel = new ArrayList<>();
                        singleHeaderForExcel = new ArrayList<>();
                        headerListForExcel = new ArrayList();
                        doubleColumnForExcel = new ArrayList<>();
                        doubleHeaderForExcel = new ArrayList<>();
                        doubleHeaderMap = new HashMap<>();
                        headerListForExcel.add(singleColumnForExcel);
                        headerListForExcel.add(singleHeaderForExcel);
                        headerListForExcel.add(tempYear);
                        headerListForExcel.add(doubleColumnForExcel);
                        headerListForExcel.add(doubleHeaderForExcel);
                        headerListForExcel.add(doubleHeaderMap);
                        periodListMapForExcel.put(j, headerListForExcel);
                        j++;
                        singleColumnForExcel.addAll(singleColumns);
                        singleHeaderForExcel.addAll(singleHeaders);

                        doubleColumnForExcel.add(StringUtils.EMPTY);
                        doubleHeaderForExcel.add(StringUtils.EMPTY);
                        doubleHeaderMap.put(StringUtils.EMPTY, singleColumns.toArray());
                        k = tempYear;
                    }//Ends here
            if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(projSelDTO.getScreenName())) {
                if (projSelDTO.getFrequencyDivision() != 1) {
                    StringBuilder str = new StringBuilder(commonColumn);
                    commonColumn = str.insert(commonColumn.length() - NumericConstants.FOUR, '-').toString();
                }

                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    if (Constant.ACTUALS.equals(projSelDTO.getActualsOrProjections()) || Constant.BOTH.equals(projSelDTO.getActualsOrProjections())) {

                        columnConfigure(commonColumn + "-ActualReturned%", commonHeader + " Actual Returned %", "Actual Returned %", tableHeaderDTO, excelHeader, dmap);
                        columnConfigure(commonColumn + "-ActualRPU", commonHeader + " Actual RPU", "Actual RPU", tableHeaderDTO, excelHeader, dmap);
                        columnConfigure(commonColumn + "-ActualReturnedAmount", commonHeader + " Actual Returned Amount", "Actual Returned Amount", tableHeaderDTO, excelHeader, dmap);

                    }
                    if ((Constant.PROJECTIONS.equals(projSelDTO.getActualsOrProjections()) || Constant.BOTH.equals(projSelDTO.getActualsOrProjections())) && i < projSelDTO.getProjectionStartIndex()) {

                        columnConfigure(commonColumn + Constant.HISTORY_PROJECTED_RETURN, commonHeader + Constant.PROJECTED_RETURN_PERCENT, Constant.PROJECTED_RETURN_PER, tableHeaderDTO, excelHeader, dmap);
                        columnConfigure(commonColumn + Constant.HISTORY_PROJECTED_RPU, commonHeader + Constant.PROJECTED_RPU1, Constant.PROJECTED_RPU1, tableHeaderDTO, excelHeader, dmap);
                        columnConfigure(commonColumn + Constant.HISTORY_PROJECTED_RETURN_AMOUNT, commonHeader + Constant.PROJECTED_RETURN_AMOUNT1, Constant.PROJECTED_RETURN_AMOUNT1, tableHeaderDTO, excelHeader, dmap);
                        columnConfigure(commonColumn + Constant.HISTORY_GROWTH_RATE, commonHeader + Constant.GROWTH_RATE, Constant.GROWTH_RATE, tableHeaderDTO, excelHeader, dmap);
                        colList.add(commonColumn);
                        projSelDTO.setCommonColumn(colList);
                    }
                    tableHeaderDTO.addDoubleHistoryColumn(commonColumn, commonHeader);
                }
                if (i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex()) {
                    columnConfigure(commonColumn + "-ProjectedReturn%", commonHeader + Constant.PROJECTED_RETURN_PERCENT, Constant.PROJECTED_RETURN_PER, tableHeaderDTO, excelHeader, dmap);
                    columnConfigure(commonColumn + "-ProjectedRPU", commonHeader + Constant.PROJECTED_RPU1, Constant.PROJECTED_RPU1, tableHeaderDTO, excelHeader, dmap);
                    columnConfigure(commonColumn + "-ProjectedReturnAmount", commonHeader + Constant.PROJECTED_RETURN_AMOUNT1, Constant.PROJECTED_RETURN_AMOUNT1, tableHeaderDTO, excelHeader, dmap);
                    columnConfigure(commonColumn + "-GrowthRate", commonHeader + Constant.GROWTH_RATE, Constant.GROWTH_RATE, tableHeaderDTO, excelHeader, dmap);
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                } else if (i >= projSelDTO.getForecastStartIndex() && i <= projSelDTO.getForecastEndIndex()) {

                    columnConfigure(commonColumn + "-ProjectedReturn%", commonHeader + Constant.PROJECTED_RETURN_PERCENT, Constant.PROJECTED_RETURN_PER, tableHeaderDTO, excelHeader, dmap);
                    columnConfigure(commonColumn + "-ProjectedRPU", commonHeader + Constant.PROJECTED_RPU1, Constant.PROJECTED_RPU1, tableHeaderDTO, excelHeader, dmap);
                    columnConfigure(commonColumn + Constant.HISTORY_PROJECTED_SALES_DASH, commonHeader + Constant.PROJECTED_SALES, PROJECTED_SALES1, tableHeaderDTO, excelHeader, dmap);
                    columnConfigure(commonColumn + "-ProjectedReturnAmount", commonHeader + Constant.PROJECTED_RETURN_AMOUNT1, Constant.PROJECTED_RETURN_AMOUNT1, tableHeaderDTO, excelHeader, dmap);
                    columnConfigure(commonColumn + "-GrowthRate", commonHeader + Constant.GROWTH_RATE, Constant.GROWTH_RATE, tableHeaderDTO, excelHeader, dmap);
                }
            } else {
                if (projSelDTO.getFrequencyDivision() != 1) {
                    StringBuilder str = new StringBuilder(commonColumn);
                    commonColumn = str.insert(commonColumn.length() - NumericConstants.FOUR, '-').toString();
                }
                
                int countColumn = 0;
                
                String[] variableArr = new String[projSelDTO.getVariableList().size()];
                variableArr = projSelDTO.getVariableList().toArray(variableArr);
                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    if (Constant.ACTUALS.equals(projSelDTO.getActualsOrProjections()) || Constant.BOTH.equals(projSelDTO.getActualsOrProjections())) {
                        for (String value : variableArr) {
                            value = value.trim();
                            switch (value) {
                                case Constant.SALES_SMALL:
                                    columnConfigure(commonColumn + Constant.ACTUAL_SALES_AT, commonHeader + " Actual Sales", Constant.ACTUAL_SALES, tableHeaderDTO, excelHeader, dmap);
                                    singleColumnForExcel.add(commonColumn + Constant.ACTUAL_SALES_AT);//Added for tabwise excel export
                                    singleHeaderForExcel.add(commonHeader + " Actual Sales");//Ends here
                                    break;
                                case Constant.UNITS_SMALL:
                                    columnConfigure(commonColumn + Constant.ACTUAL_UNITS_DASH, commonHeader + Constant.ACTUAL_UNITS_SPACE, Constant.ACTUAL_UNITS_LABEL, tableHeaderDTO, excelHeader, dmap);
                                    singleColumnForExcel.add(commonColumn + Constant.ACTUAL_UNITS_DASH);//Added for tabwise excel export
                                    singleHeaderForExcel.add(commonHeader + Constant.ACTUAL_UNITS_SPACE);//Ends here
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    if ((Constant.PROJECTIONS.equals(projSelDTO.getActualsOrProjections()) || Constant.BOTH.equals(projSelDTO.getActualsOrProjections())) && i < projSelDTO.getProjectionStartIndex()) {
                        for (String value : variableArr) {
                            value = value.trim();
                            switch (value) {
                                case Constant.SALES_SMALL:
                                    columnConfigure(commonColumn + Constant.HISTORY_PROJECTED_SALES_DASH, commonHeader + Constant.PROJECTED_SALES, PROJECTED_SALES1, tableHeaderDTO, excelHeader, dmap);
                                    singleColumnForExcel.add(commonColumn + Constant.HISTORY_PROJECTED_SALES_DASH);//Added for tabwise excel export
                                    singleHeaderForExcel.add(commonHeader + Constant.PROJECTED_SALES);//Ends here
                                    break;
                                case Constant.UNITS_SMALL:
                                    columnConfigure(commonColumn + Constant.HISTORY_PROJECTED_UNITS1, commonHeader + PROJECTED_UNITS1, Constant.PROJECTED_UNITS, tableHeaderDTO, excelHeader, dmap);
                                    singleColumnForExcel.add(commonColumn + Constant.HISTORY_PROJECTED_UNITS1);//Added for tabwise excel export
                                    singleHeaderForExcel.add(commonHeader + PROJECTED_UNITS1);//Ends here
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    tableHeaderDTO.addDoubleHistoryColumn(commonColumn, commonHeader);
                } 
                if (i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex()) {
                    for (String value : variableArr) {
                        value = value.trim();
                        switch (value) {
                            case Constant.SALES_SMALL:
                                columnConfigure(commonColumn + Constant.PROJECTED_SALES2, commonHeader + Constant.PROJECTED_SALES, PROJECTED_SALES1, tableHeaderDTO, excelHeader, dmap);
                                singleColumnForExcel.add(commonColumn + Constant.PROJECTED_SALES2);//Added for tabwise excel export
                                singleHeaderForExcel.add(commonHeader + Constant.PROJECTED_SALES);//Ends here
                                break;
                            case Constant.UNITS_SMALL:
                                columnConfigure(commonColumn + Constant.PROJECTED_UNITS_TILT, commonHeader + PROJECTED_UNITS1, Constant.PROJECTED_UNITS, tableHeaderDTO, excelHeader, dmap);
                                singleColumnForExcel.add(commonColumn + Constant.PROJECTED_UNITS_TILT);//Added for tabwise excel export
                                singleHeaderForExcel.add(commonHeader + PROJECTED_UNITS1);//Ends here
                                break;
                            case Constant.PRODUCT_GROWTH:
                                columnConfigure(commonColumn + Constant.PRODUCT_GROWTH_TILT, commonHeader + PRODUCT_GROWTH1, Constant.PRODUCT_GROWTH, tableHeaderDTO, excelHeader, dmap);
                                singleColumnForExcel.add(commonColumn + Constant.PRODUCT_GROWTH_TILT);//Added for tabwise excel export
                                singleHeaderForExcel.add(commonHeader + PRODUCT_GROWTH1);//Ends here
                                
                                columnConfigureSum(commonColumn + Constant.PRODUCT_GROWTH_TILT_SUM, commonHeader + PRODUCT_GROWTH1_SUM, excelHeader, dmapsum);
                                singleColumnForExcel.add(commonColumn + Constant.PRODUCT_GROWTH_TILT_SUM);//Added for tabwise excel export
                                singleHeaderForExcel.add(commonHeader + PRODUCT_GROWTH1_SUM);//Ends here
                                countColumn  += 1; 
                                break;
                            case Constant.ACCOUNT_GROWTH:
                                columnConfigure(commonColumn + Constant.ACCOUNT_GROWTH_TILT, commonHeader + Constant.ACCOUNT_GROWTH_1, Constant.ACCOUNT_GROWTH, tableHeaderDTO, excelHeader, dmap);
                                singleColumnForExcel.add(commonColumn + Constant.ACCOUNT_GROWTH_TILT);//Added for tabwise excel export
                                singleHeaderForExcel.add(commonHeader + Constant.ACCOUNT_GROWTH_1);//Ends here
                                
                                columnConfigureSum(commonColumn + Constant.ACCOUNT_GROWTH_TILT_SUM, commonHeader + Constant.ACCOUNT_GROWTH_1_SUM, excelHeader, dmapsum);
                                singleColumnForExcel.add(commonColumn + Constant.ACCOUNT_GROWTH_TILT_SUM);//Added for tabwise excel export
                                singleHeaderForExcel.add(commonHeader + Constant.ACCOUNT_GROWTH_1_SUM);//Ends here
                                countColumn  += 1; 
                                break;
                                
                            default:
                                break;
                        }
                    }
                    
                    if(countColumn != 0)
                    {
                		columnConfigureSum(commonColumn + Constant.CHILD_COUNT, commonHeader + Constant.CHILD_COUNT_HEADER, excelHeader, dmapsum);
                        singleColumnForExcel.add(commonColumn + Constant.CHILD_COUNT);//Added for tabwise excel export
                        singleHeaderForExcel.add(commonHeader + Constant.CHILD_COUNT_HEADER);//Ends here
                    }
                    
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                    
                } else if (i >= projSelDTO.getForecastStartIndex() && i <= projSelDTO.getForecastEndIndex()) {
                    for (String value : variableArr) {
                        value = value.trim();
                        switch (value) {
                            case Constant.SALES_SMALL:                               

                             columnConfigure(commonColumn + Constant.PROJECTED_SALES2, commonHeader + Constant.PROJECTED_SALES, PROJECTED_SALES1, tableHeaderDTO, excelHeader, dmap);
                             singleColumnForExcel.add(commonColumn + Constant.PROJECTED_SALES2);//Added for tabwise excel export
                                singleHeaderForExcel.add(commonHeader + Constant.PROJECTED_SALES);//Ends here
                                break;
                            case Constant.UNITS_SMALL:
                                columnConfigure(commonColumn + Constant.PROJECTED_UNITS_TILT, commonHeader + PROJECTED_UNITS1, Constant.PROJECTED_UNITS, tableHeaderDTO, excelHeader, dmap);
                                singleColumnForExcel.add(commonColumn + Constant.PROJECTED_UNITS_TILT);//Added for tabwise excel export
                                singleHeaderForExcel.add(commonHeader + PROJECTED_UNITS1);//Ends here
                                break;
                            case Constant.PRODUCT_GROWTH:
                                columnConfigure(commonColumn + Constant.PRODUCT_GROWTH_TILT, commonHeader + PRODUCT_GROWTH1, Constant.PRODUCT_GROWTH, tableHeaderDTO, excelHeader, dmap);
                                singleColumnForExcel.add(commonColumn + Constant.PRODUCT_GROWTH_TILT);//Added for tabwise excel export
                                singleHeaderForExcel.add(commonHeader + PRODUCT_GROWTH1);//Ends here
                                
                                columnConfigureSum(commonColumn + Constant.PRODUCT_GROWTH_TILT_SUM, commonHeader + PRODUCT_GROWTH1_SUM, excelHeader, dmapsum);
                                singleColumnForExcel.add(commonColumn + Constant.PRODUCT_GROWTH_TILT_SUM);//Added for tabwise excel export
                                singleHeaderForExcel.add(commonHeader + PRODUCT_GROWTH1_SUM);//Ends here
                                countColumn  += 1; 
                                break;
                           
                            case Constant.ACCOUNT_GROWTH:
                                columnConfigure(commonColumn + Constant.ACCOUNT_GROWTH_TILT, commonHeader + Constant.ACCOUNT_GROWTH_1, Constant.ACCOUNT_GROWTH, tableHeaderDTO, excelHeader, dmap);
                                singleColumnForExcel.add(commonColumn + Constant.ACCOUNT_GROWTH_TILT);//Added for tabwise excel export
                                singleHeaderForExcel.add(commonHeader + Constant.ACCOUNT_GROWTH_1);//Ends here
                                
                                columnConfigureSum(commonColumn + Constant.ACCOUNT_GROWTH_TILT_SUM, commonHeader + Constant.ACCOUNT_GROWTH_1_SUM, excelHeader, dmapsum);
                                singleColumnForExcel.add(commonColumn + Constant.ACCOUNT_GROWTH_TILT_SUM);//Added for tabwise excel export
                                singleHeaderForExcel.add(commonHeader + Constant.ACCOUNT_GROWTH_1_SUM);//Ends here
                                countColumn  += 1; 
                                break;
                                
                            default:
                                break;
                        }
                        
                    }
                    // Added Count Column
                    if(countColumn != 0)
                    {
                		columnConfigureSum(commonColumn + Constant.CHILD_COUNT, commonHeader + Constant.CHILD_COUNT_HEADER, excelHeader, dmapsum);
                        singleColumnForExcel.add(commonColumn + Constant.CHILD_COUNT);//Added for tabwise excel export
                        singleHeaderForExcel.add(commonHeader + Constant.CHILD_COUNT_HEADER);//Ends here
                    }
                }
            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                doubleHeaderMap.put(commonColumn, dmap.toArray());//Ends here
            }
            
            // Added for AG and PG SUM
            if (!dmapsum.isEmpty()) {

                excelHeader.addDoubleColumn(commonColumn, commonHeader);
                excelHeader.addDoubleHeaderMap(commonColumn, dmap.toArray());
                
                doubleColumnForExcel.add(commonColumn);//Added for tabwise excel export
                doubleHeaderForExcel.add(commonHeader);
                doubleHeaderMap.put(commonColumn, dmapsum.toArray());//Ends here
            }
        }
        List<String> columnList=CommonUtils.objectListToStringList(tableHeaderDTO.getSingleColumns());
        projSelDTO.setColumns(columnList);
        
            } catch (NumberFormatException e) {
                LOGGER.error(e.getMessage());
            }
        }
        projSelDTO.setHeaderMapForExcel(periodListMapForExcel);//Added for tabwise excel export
        return tableHeaderDTO;
    }

    public static void prepareCommonColumnHeaders(ProjectionSelectionDTO projSelDTO) {

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projOrder = projSelDTO.getProjectionOrder();
        int projectionOrder = 0;
        if (projOrder.contains(ASCENDING.getConstant())) {
            projectionOrder = 1;
        } else {
            projectionOrder = NumericConstants.TWO;
        }
        String yearValue = projSelDTO.getYear();
        int year = isInteger(yearValue) ? Integer.parseInt(yearValue) : 0;
        int historyStartIndex = -1;
        int projectionStartIndex = -1;
        int forecastStartIndex = -1;
        int historyEndIndex = -1;
        int projectionEndIndex = -1;
        int forecastEndIndex = -1;
        List<String> periodList = new ArrayList<>();
        Map<String, String> periodListMap = new HashMap<>();
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
            
            for (int yr = projSelDTO.getStartYear(); yr <= projSelDTO.getEndYear(); yr++) {
                if (yr == projSelDTO.getEndYear()) {
                    lastPr = projSelDTO.getEndPeriod();
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    periodList.add(commonColumn);
                    periodListMap.put(commonColumn, commonHeader);
                }
                startPr = 1;
            }
            
            //Calculating projection
            startPr = projSelDTO.getProjectionStartPeriod() == 0 ? 1 : projSelDTO.getProjectionStartPeriod();
            int projectionStartYear = projSelDTO.getProjectionStartYear();
            int projectionEndYear = projSelDTO.getForecastDTO().getProjectionEndYear();
                lastPr = projSelDTO.getProjectionEndPeriod();
            if (frequencyDivision == 1) {
                startPr = projectionStartYear;
                lastPr = projectionStartYear;
            }
            String projectionStartCommon = getCommonColumnHeader(frequencyDivision, projectionStartYear, startPr).get(0);
            String projectionEndCommon = getCommonColumnHeader(frequencyDivision, projectionEndYear, lastPr).get(0);
            projectionStartIndex = periodList.indexOf(projectionStartCommon);
            projectionEndIndex = periodList.indexOf(projectionEndCommon);

            //Calculating history
            startPr = projSelDTO.getHistoryStartPeriod() == 0 ? 1 : projSelDTO.getHistoryStartPeriod();
            int historyStartYear = projSelDTO.getHistoryStartYear();
            int historyEndYear = projSelDTO.getHistoryEndYear();
            lastPr = projSelDTO.getHistoryEndPeriod();
                if (frequencyDivision == 1) {
                    startPr = historyStartYear;
                    lastPr = historyStartYear;
                }
            
                
            String historyStartCommon = getCommonColumnHeader(frequencyDivision, historyStartYear, startPr).get(0);
            String historyEndCommon = getCommonColumnHeader(frequencyDivision, historyEndYear, lastPr).get(0);
            historyStartIndex = periodList.indexOf(historyStartCommon);
            historyEndIndex = periodList.indexOf(historyEndCommon);
            
            //Calculating forecast
            startPr = projSelDTO.getForecastStartPeriod() == 0 ? 1 : projSelDTO.getForecastStartPeriod();
            int forecastStartYear = projSelDTO.getForecastDTO().getForecastStartYear();
            int forecastEndYear = projSelDTO.getForecastDTO().getForecastEndYear();
            lastPr = projSelDTO.getForecastEndPeriod();
                if (frequencyDivision == 1) {
                    startPr = forecastStartYear;
                    lastPr = forecastStartYear;
                }
            
                
            String forecastStartCommon = getCommonColumnHeader(frequencyDivision, forecastStartYear, startPr).get(0);
            String forecastEndCommon = getCommonColumnHeader(frequencyDivision, forecastEndYear, lastPr).get(0);
            forecastStartIndex = periodList.indexOf(forecastStartCommon);
            forecastEndIndex = periodList.indexOf(forecastEndCommon);
            
            
            
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
        List<String> common = new ArrayList<>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = Constant.Q + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = Constant.S + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase(Locale.ENGLISH) + year;
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
        if (num >= 0 && num <= NumericConstants.ELEVEN) {
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
            tableHeaderDTO.addSingleColumn(Constant.LEVEL_NAME, Constant.LEVEL_NAME_LABEL, String.class);
            tableHeaderDTO.addDoubleColumn(Constant.GROUP1_SMALL, " ");
            tableHeaderDTO.addDoubleHeaderMap(Constant.GROUP1_SMALL, new Object[]{Constant.LEVEL_NAME});
        } else {
            tableHeaderDTO.addSingleColumn(Constant.CHECK, " ", Boolean.class);
            tableHeaderDTO.addSingleColumn(Constant.LEVEL_NAME, Constant.LEVEL_NAME_LABEL, String.class);
            if (projectionSelectionDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                tableHeaderDTO.addSingleColumn(Constant.GROUP, "Group", String.class);
            }
            tableHeaderDTO.addSingleColumn(Constant.BASELINE, "Base Line", String.class);
            tableHeaderDTO.addSingleColumn(Constant.METHODOLOGY, "Methodology", String.class);
            tableHeaderDTO.addDoubleColumn(Constant.GROUP1_SMALL, " ");
            tableHeaderDTO.addDoubleHeaderMap(Constant.GROUP1_SMALL, new Object[]{Constant.CHECK, Constant.LEVEL_NAME, Constant.GROUP, Constant.BASELINE, Constant.METHODOLOGY});
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
        excelDto.addSingleColumn(Constant.LEVEL_NAME, Constant.LEVEL_NAME_LABEL, String.class);
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

        Map<Object, Object[]> doubleHeaderHistoryMap = new HashMap<>();
        List<String> totalProjected = new ArrayList<>();

        String hist = selection.get(Constant.HISTORY).toString();
        String projFreq = selection.get("projectFrequency").toString();

        String projection = selection.get("actualsorprojections").toString();

        String salesVar = (String) selection.get(Constant.SALES);
        String unitsVar = (String) selection.get(Constant.UNITS);
        String pGrowthVar = (String) selection.get(Constant.P_GROWTH);
        String aGrowthVar = (String) selection.get(Constant.A_GROWTH);

        int curMonth = forecastDTO.getForecastStartMonth() - 1;
        int curYear = forecastDTO.getForecastStartYear();
        boolean reprojectionAllowed = false;
        boolean projAllowed = true;

        int current = curMonth / NumericConstants.THREE;
        int frequency = Integer.parseInt(hist.replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
        int projectFrequency = Integer.parseInt(projFreq);
        int division = NumericConstants.FOUR;
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
            List<Object> dmap = new ArrayList<>();
            List<Object> historyObj = new ArrayList<>();
            List<Object> projectionObj = new ArrayList<>();

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

            if ((projection.contains(Constant.BOTH) || projection.contains(ACTUALS)) && (salesVar.equals(Constant.TRUE))) {
                    String singleColumn = commonColumn + Constant.ACTUAL_SALES_AT;
                    dmap.add(singleColumn);
                    historyObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACTUAL_SALES, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.ACTUAL_SALES);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Actual Sales", String.class);
            }
            if (!reprojectionAllowed) {
                if ((projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS)) && (salesVar.equals(Constant.TRUE))) {
                        String singleColumn = commonColumn + Constant.HISTORY_PROJECTED_SALES_DASH;
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_SALES1, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, PROJECTED_SALES1);
                        excelDto.addSingleColumn(singleColumn, commonHeader + Constant.PROJECTED_SALES_SPACE, String.class);
                }
            } else {
                if ((projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS)) && (salesVar.equals(Constant.TRUE))) {
                        String singleColumn = commonColumn + Constant.PROJECTED_SALES2;
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        projectionObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_SALES1, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, PROJECTED_SALES1);
                        excelDto.addSingleColumn(singleColumn, commonHeader + Constant.PROJECTED_SALES_SPACE, String.class);

                }
            }
            if ((projection.contains(Constant.BOTH) || projection.contains(ACTUALS)) && (unitsVar.equals(Constant.TRUE))) {

                    String singleColumn = commonColumn + Constant.ACTUAL_UNITS_DASH;
                    dmap.add(singleColumn);
                    historyObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACTUAL_UNITS_LABEL, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.ACTUAL_UNITS_LABEL);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Actual Units", String.class);
            }
            if (!reprojectionAllowed) {
                if ((projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS)) && (unitsVar.equals(Constant.TRUE))) {
                        String singleColumn = commonColumn + Constant.HISTORY_PROJECTED_UNITS1;
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, Constant.PROJECTED_UNITS, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.PROJECTED_UNITS);
                        excelDto.addSingleColumn(singleColumn, commonHeader + Constant.PROJECTED_UNITS_SPACE, String.class);

                }

            } else {
                if ((projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS)) && (unitsVar.equals(Constant.TRUE))) {
                        String singleColumn = commonColumn + Constant.PROJECTED_UNITS_TILT;
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        projectionObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, Constant.PROJECTED_UNITS, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.PROJECTED_UNITS);
                        excelDto.addSingleColumn(singleColumn, commonHeader + Constant.PROJECTED_UNITS_SPACE, String.class);

                }

                if (pGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + Constant.PRODUCT_GROWTH_TILT;
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.PRODUCT_GROWTH, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.PRODUCT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + Constant.PRODUCT_GROWTH_SPACE, String.class);
                }

                if (aGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + Constant.ACCOUNT_GROWTH_TILT;
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACCOUNT_GROWTH, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.ACCOUNT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + Constant.SPACE_ACCOUNT_GROWTH, String.class);
                }
                totalProjected.add(commonHeader);
            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleHistoryColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHistoryHeaderMap(commonColumn, dmap.toArray());
                doubleHeaderHistoryMap.put(commonColumn, historyObj.toArray());
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
            List<Object> dmap = new ArrayList<>();
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
                    String singleColumn = commonColumn + Constant.PROJECTED_SALES2;
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_SALES1, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTED_SALES1);
                    excelDto.addSingleColumn(singleColumn, commonHeader + Constant.PROJECTED_SALES_SPACE, String.class);
                }
                if (unitsVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + Constant.PROJECTED_UNITS_TILT;
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.PROJECTED_UNITS, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.PROJECTED_UNITS);
                    excelDto.addSingleColumn(singleColumn, commonHeader + Constant.PROJECTED_UNITS_SPACE, String.class);
                }
                if (pGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + Constant.PRODUCT_GROWTH_TILT;
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.PRODUCT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.PRODUCT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + Constant.PRODUCT_GROWTH_SPACE, String.class);
                }
                if (aGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + Constant.ACCOUNT_GROWTH_TILT;
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACCOUNT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.ACCOUNT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + Constant.SPACE_ACCOUNT_GROWTH, String.class);
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
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_SALES1, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTED_SALES1);
                    excelDto.addSingleColumn(singleColumn, commonHeader + Constant.PROJECTED_SALES_SPACE, String.class);
                }
                if (unitsVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProjectedUnitsDis";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.PROJECTED_UNITS, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.PROJECTED_UNITS);
                    excelDto.addSingleColumn(singleColumn, commonHeader + Constant.PROJECTED_UNITS_SPACE, String.class);
                }
                if (pGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProductGrowthDis";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.PRODUCT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.PRODUCT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + Constant.PRODUCT_GROWTH_SPACE, String.class);
                }
                if (aGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-AccountGrowthDis";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACCOUNT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.ACCOUNT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + Constant.SPACE_ACCOUNT_GROWTH, String.class);
                }

            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleProjectedHeaderMap(commonColumn, dmap.toArray());

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
        if (month < NumericConstants.FOUR) {
            return 1;
        }
        if (month < NumericConstants.SEVEN) {
            return NumericConstants.TWO;
        }
        if (month < NumericConstants.TEN) {
            return NumericConstants.THREE;
        }
        return NumericConstants.FOUR;
    }

    public static CustomTableHeaderDTO getPMPYTableColumns(String frequency, int frequencyRange, boolean isExcelExport) {
        LOGGER.debug("----Inside getPMPYTableColumns() -----");
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getPMPYCalculatedColumns(tableHeaderDTO, frequency, frequencyRange, isExcelExport);
    }

    public static CustomTableHeaderDTO getPMPYCalculatedColumns(CustomTableHeaderDTO tableHeaderDTO, String frequency, int frequencyRange, boolean isExcelExport) {
        int frequencyRangePMPY  = frequencyRange;
        boolean isExcelExp = isExcelExport;
        Calendar ob = Calendar.getInstance();
        int curMonth = ob.get(Calendar.MONTH);
        int curYear = ob.get(Calendar.YEAR);
        int current = 1;
        int division = 1;
        if (frequency.equals(QUARTERLY)) {
            current = curMonth / NumericConstants.THREE;
            division = NumericConstants.FOUR;
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            current = curMonth / NumericConstants.SIX;
            division = NumericConstants.TWO;
        } else if (frequency.equals(MONTHLY)) {
            current = curMonth;
            division = NumericConstants.TWELVE;
        } else if (frequency.equals(ANNUALLY)) {
            current = curYear;
            division = 1;
        }
        int pastYear = curYear;
        pastYear = pastYear - NumericConstants.THREE;
        if (!frequency.equals(ANNUALLY)) {
            int tempFreq = frequencyRangePMPY + current - 1;
            frequencyRangePMPY = tempFreq + 1;
        } else {
            frequencyRangePMPY = NumericConstants.THREE;
        }
        int squr = 1;
        int syear = pastYear;
        List<Object> doubleHeaderMap = new ArrayList<>();
        List<Object> tripleHeaderMap = new ArrayList<>();

        for (int i = 0; i < frequencyRangePMPY; i++) {

            if (isExcelExp) {
                tableHeaderDTO.addSingleColumn("firstColumn", StringUtils.EMPTY, String.class);
                isExcelExp = false;
            }

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
                commonColumn = (monthName + syear).toUpperCase(Locale.ENGLISH);
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

    public static CustomTableHeaderDTO getProjectionReturnsLeftTableColumns( CustomTableHeaderDTO excelDto) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn(Constant.CHECK, " ", Boolean.class);
        tableHeaderDTO.addSingleColumn(Constant.LEVEL_NAME, Constant.BRAND_CAPS, String.class);
        tableHeaderDTO.addSingleColumn("log", "Lag", String.class);
        tableHeaderDTO.addSingleColumn(Constant.CLOSED_DATE, "Closed Date", String.class);
        tableHeaderDTO.addSingleColumn(Constant.LOE_DATE, "LOE Date", String.class);
        tableHeaderDTO.addDoubleColumn(Constant.GROUP1_SMALL, " ");
        tableHeaderDTO.addDoubleHeaderMap(Constant.GROUP1_SMALL, new Object[]{Constant.CHECK, Constant.LEVEL_NAME, "log", Constant.CLOSED_DATE, Constant.LOE_DATE});
        excelDto.addSingleColumn(Constant.LEVEL_NAME, Constant.BRAND_CAPS, String.class);
        excelDto.addSingleColumn("log", "Lag", String.class);
        excelDto.addSingleColumn(Constant.CLOSED_DATE, "Closed Date", String.class);
        excelDto.addSingleColumn(Constant.LOE_DATE, "LOE Date", String.class);
        excelDto.addDoubleColumn(Constant.GROUP1_SMALL, " ");
        excelDto.addDoubleHeaderMap(Constant.GROUP1_SMALL, new Object[]{Constant.CHECK, Constant.LEVEL_NAME, "log", Constant.CLOSED_DATE, Constant.LOE_DATE});
        return tableHeaderDTO;
    }
    
    private static HeaderUtils object;

    public static HeaderUtils getInstance() {
        if (object == null) {
            object = new HeaderUtils();
        }
        return object;
    }

	public Object[] getPmpyProductColumns() {
		return pmpyProductColumns.clone();
	}

	public String[] getPmpyProductHeader() {
		return pmpyProductHeader.clone();
	}

	public Object[] getPmpyColumns() {
		return pmpyColumns.clone();
	}

	public String[] getPmpyHeader() {
		return pmpyHeader.clone();
	}
}
