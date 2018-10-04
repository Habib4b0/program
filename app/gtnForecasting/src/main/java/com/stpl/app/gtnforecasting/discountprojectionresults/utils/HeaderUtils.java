package com.stpl.app.gtnforecasting.discountprojectionresults.utils;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author pvinoth
 */
public class HeaderUtils {
    
    private HeaderUtils() {
        // HeaderUtils
    }
    
    public static final String PROJECTIONS_RPU = "ProjectionsRPU";
    public static final String PROJECTIONS_RATE = "ProjectionsRate";

    public static CustomTableHeaderDTO getDiscountProjectionResultsLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO, String group) {

        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object doubleCol = Constant.GROUP;
        Object[] singleCol = {"relationshipLevelName"};
        tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        tableHeaderDTO.addDoubleColumn(doubleCol, group);
        tableHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        fullHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        fullHeaderDTO.addDoubleColumn(doubleCol, group);
        fullHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getNMDiscountProjectionResultsLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object doubleCol = Constant.GROUP;
        Object[] singleCol = {Constant.GROUP};
        tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        fullHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        fullHeaderDTO.addDoubleColumn(doubleCol, " ");
        fullHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        return tableHeaderDTO;
    }

    public static List<String> getCommonColumnHeader(int frequencyDivision, int year, int period, boolean isNMDPR) {
        List<String> common = new ArrayList<>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == NumericConstants.FOUR) {
            if (isNMDPR) {
                commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
                commonHeader = period + Constant.Q + " " + year;
            } else {
                commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
                commonHeader = Constant.Q + period + " " + year;
            }
        } else if (frequencyDivision == NumericConstants.TWO) {
            if (isNMDPR) {
                commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
                commonHeader = period + Constant.S + " " + year;
            } else {
                commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
                commonHeader = Constant.S + period + " " + year;
            }
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
        int number = num;
        String[] months = dfs.getShortMonths();
        if (number == NumericConstants.TWELVE || number == -1) {
            number = 0;
        }
        if (number >= 0 && number <= NumericConstants.ELEVEN) {
            month = months[number];
        }
        return month;
    }

    private static void getPCHeaders(List<Object> pcNameList, String projections, CustomTableHeaderDTO tableHeaderDTO, CustomTableHeaderDTO fullHeaderDTO, String mandOrSupp) {
        List<Object> dmap = new ArrayList<>();
        if (pcNameList != null && !pcNameList.isEmpty()) {
            for (int i = 0; i < pcNameList.size(); i++) {
                String commonColumn;
                String commonHeader;
                String pcName = String.valueOf(pcNameList.get(i));

                commonColumn = StringUtils.isNotBlank(pcName) ? pcName : StringUtils.EMPTY;
                commonHeader = commonColumn;
                commonColumn = commonHeader + mandOrSupp;
                if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains(Constant.ACTUALS_PROPERTY) || projections.contains(ACTUALS.getConstant())) {
                    Object singleColumn1 = commonColumn + Constant.ACTUALS_RATE;
                    dmap.add(singleColumn1);
                    tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                    Object singleColumn2 = commonColumn + Constant.ACTUALS_AMOUNT;
                    dmap.add(singleColumn2);
                    tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);

                    Object singleColumn3 = commonColumn + Constant.ACTUALS_RPU;
                    dmap.add(singleColumn3);
                    tableHeaderDTO.addSingleColumn(singleColumn3, ACTUAL_RPU.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + ACTUAL_RPU.getConstant(), String.class);
                    
                    Object singleColumn4 = commonColumn + Constant.ACTUALS_EX_FACTORY;
                    dmap.add(singleColumn4);
                    tableHeaderDTO.addSingleColumn(singleColumn4, ACTUALEXFACTORY.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn4, commonHeader + " " + ACTUALEXFACTORY.getConstant(), String.class);
                    
                }
                if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains(Constant.PROJECTIONS1) || projections.contains(PROJECTIONS.getConstant())) {
                    Object singleColumn1 = commonColumn + PROJECTIONS_RATE;
                    dmap.add(singleColumn1);
                    tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                    Object singleColumn2 = commonColumn + Constant.PROJECTIONS_AMOUNT;
                    dmap.add(singleColumn2);
                    tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                    Object singleColumn3 = commonColumn + PROJECTIONS_RPU;
                    dmap.add(singleColumn3);
                    tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);
                    
                    Object singleColumn4 = commonColumn + Constant.PROJECTIONS_EX_FACTORY;
                    dmap.add(singleColumn4);
                    tableHeaderDTO.addSingleColumn(singleColumn4, PROJECTEDEXFACTORY.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn4, commonHeader + " " + PROJECTEDEXFACTORY.getConstant(), String.class);
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }

            }
        }
    }

    public static CustomTableHeaderDTO getNMDiscountProjectionResultsRightTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getNMCalculatedDiscountProjectionResultsColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }


    public static CustomTableHeaderDTO getNMCalculatedDiscountProjectionResultsColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        List<String> discountNames = projSelDTO.getDiscountNameList();
        List<String> discountNo = projSelDTO.getDiscountNoList();
        
        String projections = projSelDTO.getActualsOrProjections();
        String pivotView = projSelDTO.getPivotView();
        String frequency = projSelDTO.getFrequency();
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
        if (projOrder.contains(ASCENDING.getConstant())) {
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
        prepareCommonColumnHeaders(projSelDTO, true);
        Map<Integer, List> periodListMapForExcel = new HashMap<>();
        List<Object> dmap = new ArrayList<>();
        if (pivotView.contains(DISCOUNT.getConstant())) {
            if (!discountNames.isEmpty()) {
                for (int i = 0; i < discountNames.size(); i++) {
                    String commonColumn =discountNo.get(i);
                    String commonHeader =discountNames.get(i);
                    commonHeader = commonHeader.replace(" ", StringUtils.EMPTY);
                    dmap = new ArrayList<>();
                    if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains(Constant.ACTUALS_PROPERTY) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn;
                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                            singleColumn = commonColumn+ ACTUALRATE.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);
                        }
                        if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                            singleColumn = commonColumn+ ACTUALRPU.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_RPU.getConstant(), String.class);
                        }
                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                            singleColumn = commonColumn+ ACTUALAMOUNT.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);
                        }
                        
                        if (projSelDTO.getdPVariablesList().contains(PER_EX_FACTORY.getConstant())) {
                            singleColumn = commonColumn+ ACTUALEXFACTORY.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUALEXFACTORY.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALEXFACTORY.getConstant(), String.class);
                        }
                        
                    }
                    if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains(Constant.PROJECTIONS1) || projections.contains(PROJECTIONS.getConstant())) {
                        Object singleColumn;
                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                            singleColumn = commonColumn+ PROJECTEDRATE.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);
                        }
                        if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                            singleColumn = commonColumn+ PROJECTEDRPU.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);
                        }
                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                            singleColumn = commonColumn+ PROJECTEDAMOUNT.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);
                        }
                        
                        if (projSelDTO.getdPVariablesList().contains(PER_EX_FACTORY.getConstant())) {
                            singleColumn = commonColumn+ PROJECTEDEXFACTORY.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, PROJECTEDEXFACTORY.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTEDEXFACTORY.getConstant(), String.class);
                        }
                    }
                    if (!dmap.isEmpty()) {
                        tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    }

                }
            } else {
                Object doubleCol = "empty";
                Object[] singleCol = {"empty"};
                tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
                tableHeaderDTO.addDoubleColumn(doubleCol, " ");
                tableHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
            }
            if (projectionOrder == NumericConstants.TWO) {
                Collections.reverse(projSelDTO.getPeriodList());
            }
        } else {
            List<String> periodList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            List<Object> singleColumnForExcel = new ArrayList<>();
            List<String> singleHeaderForExcel = new ArrayList<>();
            List<Object> doubleColumnForExcel = new ArrayList<>();
            List<String> doubleHeaderForExcel = new ArrayList<>();
            Map<Object, Object[]> doubleHeaderMap = new HashMap<>();
            List headerListForExcel;
            Object leftColumn = fullHeaderDTO.getSingleColumns().get(0);
            String leftHeader = fullHeaderDTO.getSingleHeaders().get(0);
            int tempYear;
            int j = 0;
            int k = 0;
            for (int i = 0; i < periodList.size(); i++) {
                dmap = new ArrayList<>();
                String commonColumn = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn);
                boolean historyFlag = false;
                boolean projectionCol = false;
                boolean excelTab = frequencyDivision == NumericConstants.FOUR || frequencyDivision == NumericConstants.TWELVE;
                tempYear = excelTab ? frequencyDivision == NumericConstants.FOUR ? Integer.parseInt(commonColumn.substring(NumericConstants.TWO, commonColumn.length()))
                        : Integer.parseInt(commonColumn.replaceAll(Constant.REGEX_D, StringUtils.EMPTY)) : 0;
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
                    singleColumnForExcel.add(leftColumn);
                    singleHeaderForExcel.add(leftHeader);
                    doubleColumnForExcel.add(StringUtils.EMPTY);
                    doubleHeaderForExcel.add(StringUtils.EMPTY);
                    doubleHeaderMap.put(StringUtils.EMPTY, new Object[]{leftColumn});
                    k = tempYear;
                }
                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    historyFlag = true;

                    if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn;
                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                            singleColumn = commonColumn + ACTUALRATE.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RATE.getConstant(), String.class);
                            singleColumnForExcel.add(singleColumn);
                            singleHeaderForExcel.add(ACTUAL_RATE.getConstant());
                        }
                        if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                            singleColumn = commonColumn + ACTUALRPU.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RPU.getConstant(), String.class);
                            singleColumnForExcel.add(singleColumn);
                            singleHeaderForExcel.add(ACTUAL_RPU.getConstant());
                        }
                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                            singleColumn = commonColumn + ACTUALAMOUNT.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, ACTUAL_AMOUNT.getConstant(), String.class);
                            singleColumnForExcel.add(singleColumn);
                            singleHeaderForExcel.add(ACTUAL_AMOUNT.getConstant());
                        }
                        
                        if (projSelDTO.getdPVariablesList().contains(PER_EX_FACTORY.getConstant())) {
                            singleColumn = commonColumn + ACTUALEXFACTORY.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUALEXFACTORY.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, ACTUALEXFACTORY.getConstant(), String.class);
                            singleColumnForExcel.add(singleColumn);
                            singleHeaderForExcel.add(ACTUALEXFACTORY.getConstant());
                        }

                    }
                }
                if (isWithinIndex(i, projSelDTO) || isProjectionColReq(historyFlag, projections)) {
                    projectionCol = true;
                }

                if (i >= projSelDTO.getForecastStartIndex() && i <= projSelDTO.getForecastEndIndex()) {
                    projectionCol = true;
                }

                if (projectionCol) {
                    Object singleColumn;
                    if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                        singleColumn = commonColumn + PROJECTEDRATE.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, PROJECTED_RATE.getConstant(), String.class);
                        singleColumnForExcel.add(singleColumn);
                        singleHeaderForExcel.add(PROJECTED_RATE.getConstant());
                    }
                    if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                        singleColumn = commonColumn + PROJECTEDRPU.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_RPU.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, PROJECTED_RPU.getConstant(), String.class);
                        singleColumnForExcel.add(singleColumn);
                        singleHeaderForExcel.add(PROJECTED_RPU.getConstant());
                    }
                    if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                        singleColumn = commonColumn + PROJECTEDAMOUNT.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_AMOUNT.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, PROJECTED_AMOUNT.getConstant(), String.class);
                        singleColumnForExcel.add(singleColumn);
                        singleHeaderForExcel.add(PROJECTED_AMOUNT.getConstant());
                    }
                    
                    if (projSelDTO.getdPVariablesList().contains(PER_EX_FACTORY.getConstant())) {
                        singleColumn = commonColumn + PROJECTEDEXFACTORY.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTEDEXFACTORY.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, PROJECTEDEXFACTORY.getConstant(), String.class);
                        singleColumnForExcel.add(singleColumn);
                        singleHeaderForExcel.add(PROJECTEDEXFACTORY.getConstant());
                    }
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    doubleColumnForExcel.add(commonColumn);
                    doubleHeaderForExcel.add(commonHeader);
                    doubleHeaderMap.put(commonColumn, dmap.toArray());
                }
            }

        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        projSelDTO.setHeaderMapForExcel(periodListMapForExcel);
        return tableHeaderDTO;
    }

    private static boolean isProjectionColReq(boolean historyFlag, String projections) {
        return historyFlag && (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTIONS.getConstant()));
    }

    public static void prepareCommonColumnHeaders(ProjectionSelectionDTO projSelDTO, boolean flag) {

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
        int startPr = projSelDTO.getHistoryStartPeriod();
        int lastPr = frequencyDivision;
        if (year != 0) {
            if (year == projSelDTO.getHistoryStartYear()) {
                startPr = projSelDTO.getHistoryStartPeriod();
            }
            if (year == projSelDTO.getForecastDTO().getForecastEndYear()) {
                lastPr = projSelDTO.getForecastEndPeriod();
            }

            for (int pr = startPr; pr <= lastPr; pr++) {
                List<String> common = getCommonColumnHeader(frequencyDivision, year, pr, flag);
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
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, flag);
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
            String projectionStartCommon = getCommonColumnHeader(frequencyDivision, projectionStartYear, startPr, flag).get(0);
            String projectionEndCommon = getCommonColumnHeader(frequencyDivision, projectionEndYear, lastPr, flag).get(0);

            projectionStartIndex = periodList.indexOf(projectionStartCommon);
            projectionEndIndex = periodList.indexOf(projectionEndCommon);

            //Calculating history
            startPr = projSelDTO.getHistoryStartPeriod();
            int historyStartYear = projSelDTO.getHistoryStartYear();
            int historyEndYear = projSelDTO.getHistoryEndYear();
            lastPr = projSelDTO.getHistoryEndPeriod();
            if (frequencyDivision == 1) {
                startPr = historyStartYear;
                lastPr = historyStartYear;
            }
            String historyStartCommon = getCommonColumnHeader(frequencyDivision, historyStartYear, startPr, flag).get(0);
            String historyEndCommon = getCommonColumnHeader(frequencyDivision, historyEndYear, lastPr, flag).get(0);
            historyStartIndex = periodList.indexOf(historyStartCommon);
            historyEndIndex = periodList.indexOf(historyEndCommon);

            //Calculating forecast
            startPr = projSelDTO.getForecastStartPeriod();

            int forecastStartYear = projSelDTO.getForecastDTO().getForecastStartYear();
            int forecastEndYear = projSelDTO.getForecastDTO().getForecastEndYear();
            lastPr = projSelDTO.getForecastEndPeriod();
            if (frequencyDivision == 1) {
                startPr = forecastStartYear;
                lastPr = forecastStartYear;
            }

            String forecastStartCommon = getCommonColumnHeader(frequencyDivision, forecastStartYear, startPr, flag).get(0);
            String forecastEndCommon = getCommonColumnHeader(frequencyDivision, forecastEndYear, lastPr, flag).get(0);

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

    /**
     * To check whether the given string is integer or not
     *
     * @param s
     * @return
     */
    public static boolean isInteger(String str) {
        try {
             return str != null && !"null".equals(str)&& str.matches("^\\d+$");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static CustomTableHeaderDTO getDiscountProjectionResultsLeftTableColumn(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object doubleCol = Constant.GROUP;
        Object[] singleCol = {Constant.GROUP};
        tableHeaderDTO.addSingleColumn(singleCol[0], Constant.CUSTOMER_SMALL, String.class);
        fullHeaderDTO.addSingleColumn(singleCol[0], Constant.CUSTOMER_SMALL, String.class);
        fullHeaderDTO.addDoubleColumn(doubleCol, " ");
        fullHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getMMDiscountProjectionResultsRightTableColumn(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getCalculatedMMDiscountProjectionResultsColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }

    public static CustomTableHeaderDTO getCalculatedMMDiscountProjectionResultsColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        String projections = projSelDTO.getActualsOrProjections();
        String pivotView = projSelDTO.getPivotView();
        String frequency = projSelDTO.getFrequency();
        CommonUtil.getHistoryAndProjectionDetailsDPR(projSelDTO);
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
        if (projOrder.contains(ASCENDING.getConstant())) {
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
        prepareCommonColumnHeaders(projSelDTO, false);
        Map<Integer, List> periodListMapForExcel = new HashMap<>();
        List<Object> dmap = new ArrayList<>();
        if (pivotView.contains(Constant.DISCOUNT_SMALL)) {
            String commonColumn;
            String commonHeader = "Total Discount";
            commonColumn = commonHeader.replace(" ", StringUtils.EMPTY);
            dmap = new ArrayList<>();
            if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains(Constant.ACTUALS_PROPERTY) || projections.contains(Constant.ACTUALS)) {
                Object singleColumn1 = commonColumn + Constant.ACTUALS_RATE;
                dmap.add(singleColumn1);
                tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                Object singleColumn2 = commonColumn + Constant.ACTUALS_AMOUNT;
                dmap.add(singleColumn2);
                tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);

                Object singleColumn3 = commonColumn + Constant.ACTUALS_RPU;
                dmap.add(singleColumn3);
                tableHeaderDTO.addSingleColumn(singleColumn3, ACTUAL_RPU.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + ACTUAL_RPU.getConstant(), String.class);
                
                 Object singleColumn4 = commonColumn + Constant.ACTUALS_EX_FACTORY;
                dmap.add(singleColumn4);
                tableHeaderDTO.addSingleColumn(singleColumn4, ACTUALEXFACTORY.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn4, commonHeader + " " + ACTUALEXFACTORY.getConstant(), String.class);
                
            }
            if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains(Constant.PROJECTIONS1) || projections.contains(Constant.PROJECTIONS)) {

                Object singleColumn3 = commonColumn + Constant.PROJECTEDRPU;
                dmap.add(singleColumn3);
                tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                Object singleColumn1 = commonColumn + PROJECTIONS_RATE;
                dmap.add(singleColumn1);
                tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                Object singleColumn2 = commonColumn + Constant.PROJECTIONS_AMOUNT;
                dmap.add(singleColumn2);
                tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);
            
               Object singleColumn4 = commonColumn + Constant.PROJECTIONS_EX_FACTORY;
                dmap.add(singleColumn4);
                tableHeaderDTO.addSingleColumn(singleColumn4, PROJECTEDEXFACTORY.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn4, commonHeader + " " + PROJECTEDEXFACTORY.getConstant(), String.class);
            
            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }

            int startPr = startPeriod;
            int lastPr = frequencyDivision;
            List<String> periodList = new ArrayList<>();
            Map<String, String> periodListMap = new HashMap<>();
            for (int yr = startYear; yr <= endYear; yr++) {
                if (yr == endYear) {
                    lastPr = endPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {

                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, false);
                    String commonColumn2 = common.get(0);
                    String commonHeader2 = common.get(1);
                    periodList.add(commonColumn2);
                    periodListMap.put(commonColumn2, commonHeader2);
                }
                startPr = 1;
            }

            projSelDTO.setPeriodList(periodList);
            projSelDTO.setPeriodListMap(periodListMap);

        } else {
            List<String> periodList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            List<Object> singleColumnForExcel = new ArrayList<>();
            List<String> singleHeaderForExcel = new ArrayList<>();
            List<Object> doubleColumnForExcel = new ArrayList<>();
            List<String> doubleHeaderForExcel = new ArrayList<>();
            Map<Object, Object[]> doubleHeaderMap = new HashMap<>();
            List headerListForExcel;
            Object leftColumn = fullHeaderDTO.getSingleColumns().get(0);
            String leftHeader = fullHeaderDTO.getSingleHeaders().get(0);
            int tempYear;
            int j = 0;
            int k = 0;
            for (int i = 0; i < periodList.size(); i++) {
                dmap = new ArrayList<>();
                String commonColumn = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn);
                boolean historyFlag = false;
                boolean projectionCol = false;
                boolean excelTab = frequencyDivision == NumericConstants.FOUR || frequencyDivision == NumericConstants.TWELVE;
                tempYear = excelTab ? frequencyDivision == NumericConstants.FOUR ? Integer.parseInt(commonColumn.substring(NumericConstants.TWO, commonColumn.length()))
                        : Integer.parseInt(commonColumn.replaceAll(Constant.REGEX_D, StringUtils.EMPTY)) : 0;
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
                    singleColumnForExcel.add(leftColumn);
                    singleHeaderForExcel.add(leftHeader);
                    doubleColumnForExcel.add(StringUtils.EMPTY);
                    doubleHeaderForExcel.add(StringUtils.EMPTY);
                    doubleHeaderMap.put(StringUtils.EMPTY, new Object[]{leftColumn});
                    k = tempYear;
                }
                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    historyFlag = true;

                    if (projections.contains(Constant.BOTH) || projections.contains(Constant.ACTUALS)) {

                        Object singleColumn1 = commonColumn + Constant.ACTUALS_RATE;
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                        singleColumnForExcel.add(singleColumn1);
                        singleHeaderForExcel.add(ACTUAL_RATE.getConstant());

                        Object singleColumn2 = commonColumn + Constant.ACTUALS_AMOUNT;
                        dmap.add(singleColumn2);
                        tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                        singleColumnForExcel.add(singleColumn2);
                        singleHeaderForExcel.add(ACTUAL_AMOUNT.getConstant());

                        Object singleColumn3 = commonColumn + Constant.ACTUALS_RPU;
                        dmap.add(singleColumn3);
                        tableHeaderDTO.addSingleColumn(singleColumn3, ACTUAL_RPU.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn3, ACTUAL_RPU.getConstant(), String.class);
                        singleColumnForExcel.add(singleColumn3);
                        singleHeaderForExcel.add(ACTUAL_RPU.getConstant());
                        
                         Object singleColumn4 = commonColumn + Constant.ACTUALS_EX_FACTORY;
                        dmap.add(singleColumn4);
                        tableHeaderDTO.addSingleColumn(singleColumn4, ACTUALEXFACTORY.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn4, ACTUALEXFACTORY.getConstant(), String.class);
                        singleColumnForExcel.add(singleColumn4);
                        singleHeaderForExcel.add(ACTUALEXFACTORY.getConstant());

                    }
                }
                if (isWithinIndex(i, projSelDTO) || isProjectionColReq(historyFlag, projections)) {
                    projectionCol = true;
                }

                if (i >= projSelDTO.getForecastStartIndex() && i <= projSelDTO.getForecastEndIndex()) {
                    projectionCol = true;
                }

                if (projectionCol) {
                    Object singleColumn3 = commonColumn + Constant.PROJECTEDRPU;
                    dmap.add(singleColumn3);
                    tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                    singleColumnForExcel.add(singleColumn3);
                    singleHeaderForExcel.add(PROJECTED_RPU.getConstant());

                    Object singleColumn1 = commonColumn + PROJECTIONS_RATE;
                    dmap.add(singleColumn1);
                    tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                    singleColumnForExcel.add(singleColumn1);
                    singleHeaderForExcel.add(PROJECTED_RATE.getConstant());

                    Object singleColumn2 = commonColumn + Constant.PROJECTIONS_AMOUNT;
                    dmap.add(singleColumn2);
                    tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                    singleColumnForExcel.add(singleColumn2);
                    singleHeaderForExcel.add(PROJECTED_AMOUNT.getConstant());
                    Object singleColumn4 = commonColumn + Constant.PROJECTIONS_EX_FACTORY;
                    dmap.add(singleColumn4);
                    tableHeaderDTO.addSingleColumn(singleColumn4, PROJECTEDEXFACTORY.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn4, PROJECTEDEXFACTORY.getConstant(), String.class);
                    singleColumnForExcel.add(singleColumn4);
                    singleHeaderForExcel.add(PROJECTEDEXFACTORY.getConstant());
                    
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    doubleColumnForExcel.add(commonColumn);
                    doubleHeaderForExcel.add(commonHeader);
                    doubleHeaderMap.put(commonColumn, dmap.toArray());
                }
            }

        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        projSelDTO.setHeaderMapForExcel(periodListMapForExcel);
        return tableHeaderDTO;
    }

    private static boolean isWithinIndex(int i, ProjectionSelectionDTO projSelDTO) {
        return i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex();
    }

}
