/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.ui.projectionresults.logic.ProjectionResultsLogic;
import static com.stpl.app.cff.util.CommonUtils.BOTH;
import static com.stpl.app.cff.util.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.cff.util.Constants.LabelConstants.ACTUAL_RATE;
import static com.stpl.app.cff.util.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.cff.util.Constants.LabelConstants.PROJECTED_RATE;
import static com.stpl.app.cff.util.Constants.LabelConstants.PROJECTIONS;
import static com.stpl.app.cff.util.Constants.LabelConstants.SALES;
import static com.stpl.app.cff.util.Constants.LabelConstants.TOTAL_DISCOUNT;
import static com.stpl.app.cff.util.Constants.LabelConstants.UNITS;
import static com.stpl.app.cff.util.Constants.LabelConstants.VARIABLE;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.FORECAST_END_DAY;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.FORECAST_END_PERIOD;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.FORECAST_START_DAY;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.FORECAST_START_PERIOD;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.FREQUENCY_DIVISION;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_END_DAY;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_END_MONTH;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_END_PERIOD;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_END_YEAR;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_START_DAY;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_START_MONTH;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_START_PERIOD;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.HISTORY_START_YEAR;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_END_DAY;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_END_PERIOD;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_START_DAY;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_START_MONTH;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_START_MONTH_DDLB;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_START_PERIOD;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_START_YEAR;
import static com.stpl.app.cff.util.Constants.ProjectionConstants.PROJECTION_START_YEAR_DDLB;
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
 * @author manikandaprabu.g
 */
public class HeaderUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(HeaderUtils.class);
    /**
     * The comparison columns.
     */
    public final Object[] comparisionColumns = new Object[]{"projectionName", "projectionDescription",
        "marketType", "customer", "contract", "brand", "ndcNo", "ndcName", "createdDate", "createdBy"};
    /**
     * The parity header.
     */
    public final String[] comparisionHeader = new String[]{"Projection Name", "Description",
        "Market Type", "Customer", "Contract", "Brand", "NDC #", "NDC Name", "Created Date", "Created By"};
    
    public static final String BP_NAME = "ALLERGAN";
    public static final String BUSINESS_PROCESS = "businessProcess";

    public static CustomTableHeaderDTO getLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO, String group) {

        CustomTableHeaderDTO cffTableHeaderDTO = new CustomTableHeaderDTO();
        cffTableHeaderDTO.addSingleColumn(StringConstantsUtil.RELATIONSHIP_LEVEL_NAME, group, String.class);
        fullHeaderDTO.addSingleColumn(StringConstantsUtil.RELATIONSHIP_LEVEL_NAME, group, String.class);
        fullHeaderDTO.addDoubleColumn(StringConstantsUtil.RELATIONSHIP_LEVEL_NAME, " ");
        fullHeaderDTO.addDoubleHeaderMap(StringConstantsUtil.RELATIONSHIP_LEVEL_NAME, new Object[]{StringConstantsUtil.RELATIONSHIP_LEVEL_NAME});
        return cffTableHeaderDTO;
    }

    static void getHistoryAndProjectionDetails(PVSelectionDTO projSelDTO, String frequency) {
        int freqDivision = 1;
        int histStartPeriod = 1;
        int histEndPeriod = 1;
        int histEndMonth = 1;
        int histEndYear = 1;
        int foreStartPeriod = 1;
        int foreEndPeriod = 1;
        int projStartPeriod = 1;
        int projEndPeriod = 1;
        if (frequency.equals(ConstantsUtil.QUARTERLY)) {
            freqDivision = NumericConstants.FOUR;
            histStartPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), NumericConstants.THREE);
            histEndPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), NumericConstants.THREE);
            foreStartPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), NumericConstants.THREE);
            foreEndPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), NumericConstants.THREE);
            projStartPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), NumericConstants.THREE);
            projEndPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), NumericConstants.THREE);
        } else if (frequency.equals(ConstantsUtil.SEMI_ANNUALLY)) {
            freqDivision = NumericConstants.TWO;
            histStartPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), NumericConstants.SIX);
            histEndPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), NumericConstants.SIX);
            foreStartPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), NumericConstants.SIX);
            foreEndPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), NumericConstants.SIX);
            projStartPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), NumericConstants.SIX);
            projEndPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), NumericConstants.SIX);
        } else if (frequency.equals(ConstantsUtil.MONTHLY)) {
            freqDivision = NumericConstants.TWELVE;
            histStartPeriod = projSelDTO.getForecastDTO().getHistoryStartMonth();
            histEndPeriod = projSelDTO.getForecastDTO().getHistoryEndMonth();
            foreStartPeriod = projSelDTO.getForecastDTO().getForecastStartMonth();
            foreEndPeriod = projSelDTO.getForecastDTO().getForecastEndMonth();
            projStartPeriod = projSelDTO.getForecastDTO().getProjectionStartMonth();
            projEndPeriod = projSelDTO.getForecastDTO().getProjectionEndMonth();
        } else if (frequency.equals(ConstantsUtil.ANNUAL)) {
            freqDivision = 1;
            histStartPeriod = projSelDTO.getForecastDTO().getHistoryStartYear();
            histEndPeriod = projSelDTO.getForecastDTO().getHistoryEndYear();
            foreStartPeriod = projSelDTO.getForecastDTO().getForecastStartYear();
            foreEndPeriod = projSelDTO.getForecastDTO().getForecastEndYear();
            projStartPeriod = projSelDTO.getForecastDTO().getProjectionStartYear();
            projEndPeriod = projSelDTO.getForecastDTO().getProjectionEndYear();
        }
        histEndMonth = projSelDTO.getForecastDTO().getHistoryEndMonth();
        histEndYear = projSelDTO.getForecastDTO().getHistoryEndYear();
        if (histEndPeriod == foreStartPeriod && histEndYear == projSelDTO.getForecastDTO().getForecastStartYear()) {
            histEndPeriod--;
            if (freqDivision == 1) {
                histEndMonth = NumericConstants.TWELVE;
                histEndYear = histEndYear - 1;
            } else if (histEndPeriod < 1) {
                histEndMonth = NumericConstants.TWELVE;
                histEndYear = histEndYear - 1;
            } else if (freqDivision == NumericConstants.TWO) {
                if (histEndPeriod == 1) {
                    histEndMonth = NumericConstants.SIX;
                } else if (histEndPeriod == NumericConstants.TWO) {
                    histEndMonth = NumericConstants.TWELVE;
                }
            } else if (freqDivision == NumericConstants.FOUR) {
                if (histEndPeriod == 1) {
                    histEndMonth = NumericConstants.THREE;
                } else if (histEndPeriod == NumericConstants.TWO) {
                    histEndMonth = NumericConstants.SIX;
                } else if (histEndPeriod == NumericConstants.THREE) {
                    histEndMonth = NumericConstants.NINE;
                } else if (histEndPeriod == NumericConstants.FOUR) {
                    histEndMonth = NumericConstants.TWELVE;
                }
            } else if (freqDivision == NumericConstants.TWELVE) {
                histEndMonth = histEndPeriod;
            }
        }

        projSelDTO.setFrequencyDivision(freqDivision);
        projSelDTO.addProjectionDetails(FREQUENCY_DIVISION.getConstant(), freqDivision);
        projSelDTO.addProjectionDetails(HISTORY_END_YEAR.getConstant(), histEndYear);
        projSelDTO.addProjectionDetails(HISTORY_END_MONTH.getConstant(), histEndMonth);
        projSelDTO.addProjectionDetails(HISTORY_START_YEAR.getConstant(), projSelDTO.getForecastDTO().getHistoryStartYear());
        projSelDTO.addProjectionDetails(HISTORY_START_MONTH.getConstant(), projSelDTO.getForecastDTO().getHistoryStartMonth());
        projSelDTO.addProjectionDetails(HISTORY_START_PERIOD.getConstant(), histStartPeriod);
        projSelDTO.addProjectionDetails(HISTORY_END_PERIOD.getConstant(), histEndPeriod);
        projSelDTO.addProjectionDetails(FORECAST_START_PERIOD.getConstant(), foreStartPeriod);
        projSelDTO.addProjectionDetails(FORECAST_END_PERIOD.getConstant(), foreEndPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_START_PERIOD.getConstant(), projStartPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_END_PERIOD.getConstant(), projEndPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_START_YEAR.getConstant(), projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.addProjectionDetails(PROJECTION_START_MONTH.getConstant(), projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.addProjectionDetails(PROJECTION_START_YEAR_DDLB.getConstant(), projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.addProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant(), projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.addProjectionDetails(HISTORY_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(FORECAST_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(PROJECTION_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(HISTORY_END_DAY.getConstant(), getEndDay(histEndMonth, histEndYear));
        projSelDTO.addProjectionDetails(FORECAST_END_DAY.getConstant(), getEndDay(projSelDTO.getForecastDTO().getForecastEndMonth(), projSelDTO.getForecastDTO().getForecastEndYear()));
        projSelDTO.addProjectionDetails(PROJECTION_END_DAY.getConstant(), getEndDay(projSelDTO.getForecastDTO().getProjectionEndMonth(), projSelDTO.getForecastDTO().getProjectionEndYear()));

        projSelDTO.setProjectionStartYear(projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.setProjectionStartMonth(projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.setHistoryEndYear(histEndYear);
        projSelDTO.setHistoryEndMonth(histEndMonth);
        projSelDTO.setHistoryStartPeriod(histStartPeriod);
        projSelDTO.setHistoryEndPeriod(histEndPeriod);
        projSelDTO.setForecastStartPeriod(foreStartPeriod);
        projSelDTO.setForecastEndPeriod(foreEndPeriod);
        projSelDTO.setProjectionStartPeriod(projStartPeriod);
        projSelDTO.setProjectionEndPeriod(projEndPeriod);
        projSelDTO.setHistoryEndDay(getEndDay(projSelDTO.getForecastDTO().getHistoryEndMonth(), projSelDTO.getForecastDTO().getHistoryEndYear()));
        projSelDTO.setForecastStartDay(1);
        projSelDTO.setForecastEndDay(getEndDay(projSelDTO.getForecastDTO().getForecastEndMonth(), projSelDTO.getForecastDTO().getForecastEndYear()));
        projSelDTO.setProjectionStartDay(1);
        projSelDTO.setProjectionEndDay(getEndDay(projSelDTO.getForecastDTO().getProjectionEndMonth(), projSelDTO.getForecastDTO().getProjectionEndYear()));

    }

    public static List<String> getCommonColumnHeaderForPV(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<>();
        String commonColumn = "";
        String commonHeader = "";
        switch (frequencyDivision) {
            case 1:
                commonColumn = Integer.toString(year);
                commonHeader = Integer.toString(year);
                break;
            case NumericConstants.FOUR:
                commonColumn = "q" + period + "" + year;
                commonHeader = "Q" + period + " " + year;
                break;
            case NumericConstants.TWO:
                commonColumn = "s" + period + "" + year;
                commonHeader = "S" + period + " " + year;
                break;
            case NumericConstants.TWELVE:
                String monthName = getMonthForInt(period - 1);
                commonColumn = monthName + year;
                commonHeader = monthName + " " + year;
                break;
            default:
                break;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    static CustomTableHeaderDTO loadSingleHeader(final String commonColumn, final String commonHeader, final PVSelectionDTO selection, final CustomTableHeaderDTO cffTableHeaderDTO, final CustomTableHeaderDTO cffExcelHeaderDTO) {
        String currentProjName = selection.getCurrentProjectionName();
        String variableCategory = selection.getVariableCategory() != null ? selection.getVariableCategory() : StringUtils.EMPTY;
        List<Integer> projList = selection.getProjIdList();
        Map<Integer, String> priorMap = selection.getProjectionMap();
        boolean disc = true;
        List<Object> dmap = new ArrayList<>();
        while (disc) {
            if(variableCategory.contains(StringConstantsUtil.ACTUALS1)){
            cffTableHeaderDTO.addSingleColumn(commonColumn + ConstantsUtil.ACTUAL + selection.getCurrentProjId(), StringConstantsUtil.ACTUALS1, String.class);
            cffExcelHeaderDTO.addSingleColumn(commonColumn + ConstantsUtil.ACTUAL + selection.getCurrentProjId(), commonHeader + " " + StringConstantsUtil.ACTUALS1, String.class);
            }
            if(variableCategory.contains(StringConstantsUtil.ACCRUALS)){
            cffTableHeaderDTO.addSingleColumn(commonColumn + ConstantsUtil.ACCRUAL + selection.getCurrentProjId(), StringConstantsUtil.ACCRUALS, String.class);
            cffExcelHeaderDTO.addSingleColumn(commonColumn + ConstantsUtil.ACCRUAL + selection.getCurrentProjId(), commonHeader + " " + StringConstantsUtil.ACCRUALS, String.class);
            }
            cffTableHeaderDTO.addSingleColumn(commonColumn + StringConstantsUtil.CURRENT_LABEL + selection.getCurrentProjId(), currentProjName, String.class);
            cffExcelHeaderDTO.addSingleColumn(commonColumn + StringConstantsUtil.CURRENT_LABEL + selection.getCurrentProjId(), currentProjName + " " + commonHeader, String.class);
            if(variableCategory.contains(StringConstantsUtil.ACTUALS1)){
            dmap.add(commonColumn + ConstantsUtil.ACTUAL + selection.getCurrentProjId());
            }
             if(variableCategory.contains(StringConstantsUtil.ACCRUALS)){
            dmap.add(commonColumn + ConstantsUtil.ACCRUAL + selection.getCurrentProjId());
            }
            dmap.add(commonColumn + StringConstantsUtil.CURRENT_LABEL + selection.getCurrentProjId());
            if (!projList.isEmpty()) {
                for (int j = 0; j < projList.size(); j++) {
                    cffTableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                    cffExcelHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)) + " " + commonHeader, String.class);
                    dmap.add(commonColumn + projList.get(j));
                }
            }
            disc = false;
            if (!dmap.isEmpty()) {
                cffTableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                cffTableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                cffExcelHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                cffExcelHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }
        }
        return cffTableHeaderDTO;
    }

    public static String getMonthForInt(int num) {
        String month = "wrong";
        int number = num;
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num == NumericConstants.TWELVE) {
            number = 0;
        }
        if (num >= 0 && num <= NumericConstants.ELEVEN) {
            month = months[number];
        }
        return month;
    }

    static int getPeriod(int monthNo, int division) {
        return ((monthNo - 1) / division) + 1;
    }

    static int getEndDay(int monthNo, int year) {
        Calendar ob = Calendar.getInstance();
        ob.set(year, monthNo - 1, 1);
        return ob.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * getVarianceLeftTableColumns
     *
     * @param fullHeaderDTO
     * @return
     */
    public static CustomTableHeaderDTO getVarianceLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object doubleCol = StringConstantsUtil.GROUP_PROPERTY;
        Object[] singleCol = {StringConstantsUtil.GROUP_PROPERTY};
        tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        tableHeaderDTO.addDoubleColumn(doubleCol, " ");
        tableHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        
         if (CommonUtils.isValueEligibleForLoading()) {
            fullHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
            fullHeaderDTO.addDoubleColumn(doubleCol, " ");
            fullHeaderDTO.addSingleColumn("dfLevelNumber", "Level Number", String.class);
            fullHeaderDTO.addSingleColumn("dfLevelName", "Level Name", String.class);
            fullHeaderDTO.addDoubleHeaderMap(doubleCol, new Object[]{singleCol[0],"dfLevelNumber", "dfLevelName"});
        } else {
            fullHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
            fullHeaderDTO.addDoubleColumn(doubleCol, " ");
            fullHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        }
        return tableHeaderDTO;
    }

    /**
     * getVarianceRightTableColumns
     *
     * @param pvsdto
     * @param fullHeader
     * @return
     */
    public static List<Object> getVarianceRightTableColumns(PVSelectionDTO pvsdto, CustomTableHeaderDTO fullHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getProjectionVarianceColumns(tableHeaderDTO, pvsdto, fullHeader);
    }

     public static List<Object> getProjectionVarianceColumns(CustomTableHeaderDTO cffPVTableHeaderDTO, PVSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeader) {
         String freq = projSelDTO.getFrequency();
        String currentProjName = projSelDTO.getCurrentProjectionName();
        String headerIndicator = projSelDTO.getPivotView();
        List<Integer> projList = projSelDTO.getProjIdList();
        Map<Integer, String> priorMap = projSelDTO.getProjectionMap();
        String pivotView = projSelDTO.getPivotView();
        String projOrder = projSelDTO.getProjectionPeriodOrder();
        String variableCategory = projSelDTO.getVariableCategory() != null ? projSelDTO.getVariableCategory() : StringUtils.EMPTY;
        String variable = projSelDTO.getVariables() != null ? projSelDTO.getVariables() : StringUtils.EMPTY;
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
        CustomTableHeaderDTO tabHeaderDto = cffPVTableHeaderDTO; 
        if (projOrder.contains("Ascending")) {
            projectionOrder = 1;
        } else {
            projectionOrder = NumericConstants.TWO;
        }
        tabHeaderDto.setFrequency(freq);
        tabHeaderDto.setFrequencyDivision(frequencyDivision);
        tabHeaderDto.setHistoryNum(historyNum);
        tabHeaderDto.setProjectionNum(projectionNum);
        tabHeaderDto.setCurrentPeriod(currentPeriod);
        tabHeaderDto.setCurrentYear(currentYear);
        tabHeaderDto.setStartYear(startYear);
        tabHeaderDto.setStartPeriod(startPeriod);
        tabHeaderDto.setEndYear(endYear);
        tabHeaderDto.setEndPeriod(endPeriod);
        tabHeaderDto.setProjectionOrder(projectionOrder);

        fullHeader.setFrequency(freq);
        fullHeader.setFrequencyDivision(frequencyDivision);
        fullHeader.setHistoryNum(historyNum);
        fullHeader.setProjectionNum(projectionNum);
        fullHeader.setCurrentPeriod(currentPeriod);
        fullHeader.setCurrentYear(currentYear);
        fullHeader.setStartYear(startYear);
        fullHeader.setStartPeriod(startPeriod);
        fullHeader.setEndYear(endYear);
        fullHeader.setEndPeriod(endPeriod);
        fullHeader.setProjectionOrder(projectionOrder);

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

        prepareCommonColumnHeaders(projSelDTO);
        if (pivotView.equals("Period") || headerIndicator.equals("Period")) {
            List<String> periodList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            for (int i = 0; i < periodList.size(); i++) {
                List<Object> dmap = new ArrayList<>();
                String commonColumn1 = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn1);
                String commonColumn = commonColumn1;
                if (frequencyDivision != NumericConstants.TWELVE) {
                    commonColumn = commonColumn1.toUpperCase();
                }
                if(variableCategory.contains(StringConstantsUtil.ACTUALS1)){
                tabHeaderDto.addSingleColumn(commonColumn + ConstantsUtil.ACTUAL + projSelDTO.getCurrentProjId(), StringConstantsUtil.ACTUALS1, String.class);
                fullHeader.addSingleColumn(commonColumn + ConstantsUtil.ACTUAL + projSelDTO.getCurrentProjId(), commonHeader + " " + StringConstantsUtil.ACTUALS1, String.class);
                }
                if(variableCategory.contains(StringConstantsUtil.ACCRUALS)){
                tabHeaderDto.addSingleColumn(commonColumn + ConstantsUtil.ACCRUAL + projSelDTO.getCurrentProjId(), StringConstantsUtil.ACCRUALS, String.class);
                fullHeader.addSingleColumn(commonColumn + ConstantsUtil.ACCRUAL + projSelDTO.getCurrentProjId(), commonHeader + " " + StringConstantsUtil.ACCRUALS, String.class);
                }
                tabHeaderDto.addSingleColumn(commonColumn + StringConstantsUtil.CURRENT_LABEL + projSelDTO.getCurrentProjId(), currentProjName, String.class);
                tabHeaderDto.addSingleProjectedColumn(commonColumn + StringConstantsUtil.CURRENT_LABEL + projSelDTO.getCurrentProjId(), StringConstantsUtil.CURRENT_LABEL);
                fullHeader.addSingleColumn(commonColumn + StringConstantsUtil.CURRENT_LABEL + projSelDTO.getCurrentProjId(),  commonHeader + " " +currentProjName, String.class);
                if(variableCategory.contains(StringConstantsUtil.ACTUALS1)){
                dmap.add(commonColumn + ConstantsUtil.ACTUAL + projSelDTO.getCurrentProjId());
                }
                if(variableCategory.contains(StringConstantsUtil.ACCRUALS)){
                dmap.add(commonColumn + ConstantsUtil.ACCRUAL + projSelDTO.getCurrentProjId());
                }
                dmap.add(commonColumn + StringConstantsUtil.CURRENT_LABEL + projSelDTO.getCurrentProjId());

                if (!projList.isEmpty()) {
                    for (int j = 0; j < projList.size(); j++) {
                        tabHeaderDto.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                        tabHeaderDto.addSingleProjectedColumn(commonColumn + projList.get(j), "" + projList.get(j));
                        fullHeader.addSingleColumn(commonColumn + projList.get(j),  commonHeader + " " + priorMap.get(projList.get(j)), String.class);
                        dmap.add(commonColumn + projList.get(j));
                    }
                }
                if (!dmap.isEmpty()) {
                    tabHeaderDto.addDoubleColumn(commonColumn, commonHeader);
                    tabHeaderDto.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    tabHeaderDto.addDoubleProjectedColumn(commonColumn, commonHeader);
                    fullHeader.addDoubleColumn(commonColumn, commonHeader);
                    fullHeader.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    fullHeader.addDoubleProjectedColumn(commonColumn, commonHeader);
                }
            }
        } else {
            setBaseVariables(variableCategory, variable, projSelDTO);
            Map<String, Object> headerMap = new HashMap<>();
            String commonColumn = "";

            String commonHeader = "";
            // Exfactory Sales
            if (projSelDTO.isVarExFacSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "ExFacValue";
                    commonHeader = "Ex-Factory Product Value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "ExFacVariance";
                    commonHeader = "Ex-Factory Product variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "ExFacPer";
                    commonHeader = "Ex-Factory Product % change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            // Exfactory Customer
            if (projSelDTO.isVarExFacCustomer()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "CustExFacValue";
                    commonHeader = "Ex-Factory Customer Value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "CustExFacVariance";
                    commonHeader = "Ex-Factory Customer variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "CustExFacPer";
                    commonHeader = "Ex-Factory Customer % change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            //Demand sales
            if (projSelDTO.isVarDemandSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "DemandSalesValue";
                    commonHeader = "Demand Value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "DemandSalesVariance";
                    commonHeader = "Demand variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "DemandSalesPer";
                    commonHeader = "Demand % change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            //Adjusted Demand 
            if (projSelDTO.isVarAdjDemand()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "AdjDemandValue";
                    commonHeader = "Adjusted Demand Value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "AdjDemandVariance";
                    commonHeader = "Adjusted Demand variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "AdjDemandPer";
                    commonHeader = "Adjusted Demand % change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            //Inventory summary
            if (projSelDTO.isVarInvSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "InvWithValue";
                    commonHeader = "Inventory Withdrawal Summary value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "InvWithVariance";
                    commonHeader = "Inventory Withdrawal Summary variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "InvWithPer";
                    commonHeader = "Inventory Withdrawal Summary % change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            //Inventory Details
            if (projSelDTO.isVarIwDetails()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "InvWithDetailsValue";
                    commonHeader = "Inventory Withdrawal Details value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "InvWithDetailsVariance";
                    commonHeader = "Inventory Withdrawal Details variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "InvWithDetailsPer";
                    commonHeader = "Inventory Withdrawal Details % change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            // % Of Ex Fact product 
            if (projSelDTO.isVarPerExFacSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "PerExFacValue";
                    commonHeader = "% Of Ex-Factory Product Value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "PerExFacVariance";
                    commonHeader = "% Of  Ex-Factory Product variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "PerExFacPer";
                    commonHeader = "% Of Ex-Factory Product % change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            // % Of Ex Fact customer
            if (projSelDTO.isVarPerExFacCustomer()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "PerCustExFacValue";
                    commonHeader = "% Of Ex-Factory Customer Value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "PerCustExFacVariance";
                    commonHeader = "% Of  Ex-Factory Customer variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "PerCustExFacPer";
                    commonHeader = "% Of Ex-Factory Customer % change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            //% of Demand 
            if (projSelDTO.isVarPerDemandSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "PerDemandSalesValue";
                    commonHeader = "% Of Demand Value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "PerDemandSalesVariance";
                    commonHeader = "% Of Demand variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "PerDemandSalesPer";
                    commonHeader = "% Of Demand change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            //% of Adjusted Demand 
            if (projSelDTO.isVarPerAdjDemand()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "PerAdjDemandValue";
                    commonHeader = "% Of Adjusted Demand Value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "PerAdjDemandSalesVariance";
                    commonHeader = "% Of Adjusted Demand variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "PerAdjDemandSalesPer";
                    commonHeader = "% Of Adjusted Demand change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            //% of Inventory Summary
            if (projSelDTO.isVarPerInvSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "PerInvWithValue";
                    commonHeader = "% Of Inventory Withdrawal Summary value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "PerInvWithVariance";
                    commonHeader = "% Of Inventory Withdrawal Summary variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "PerInvWithPer";
                    commonHeader = "% Of Inventory Withdrawal Summary % change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            //% of Inventory Details
            if (projSelDTO.isVarPerIwDetails()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "PerInvWithDetailsValue";
                    commonHeader = "% Of Inventory Withdrawal Details value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "PerInvWithDetailsVariance";
                    commonHeader = "% Of Inventory Withdrawal Details variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "PerInvWithDetailsPer";
                    commonHeader = "% Of Inventory Withdrawal Details % change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }

            if (projSelDTO.isVarContractsales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "ContractSalesWACValue";
                    commonHeader = "Contract Sales @ WAC Value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "ContractSalesWACVar";
                    commonHeader = "Contract Sales @ WAC Variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "ContractSalesWACVarPer";
                    commonHeader = "Contract Sales @ WAC % Change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            if (projSelDTO.isVarContractUnits()) {

                if (projSelDTO.isColValue()) {
                    commonColumn = "ContractUnitsValue";
                    commonHeader = "Contract Units Value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "ContractUnitsVar";
                    commonHeader = "Contract Units Variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "ContractUnitsPer";
                    commonHeader = "Contract Units % Change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            if (projSelDTO.isVarDisAmount()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "DiscountAmountValue";
                    commonHeader = "Discount $ Value";
                    String commString = commonColumn;
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                        tabHeaderDto = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tabHeaderDto, headerMap,fullHeader);
                    }
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "DiscountAmountVar";
                    commonHeader = "Discount $ Variance";
                    String commString = commonColumn;
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                        tabHeaderDto = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tabHeaderDto, headerMap,fullHeader);
                    }
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "DiscountAmountPer";
                    commonHeader = "Discount $ % change";
                    String commString = commonColumn;
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                        tabHeaderDto = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tabHeaderDto, headerMap,fullHeader);
                    }
                }
            }
            if (projSelDTO.isVarDisRate()) {

                if (projSelDTO.isColValue()) {
                    commonColumn = "DiscountSalesValue";
                    commonHeader = "Discount % Value";
                    String commString = commonColumn;
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                        tabHeaderDto = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tabHeaderDto, headerMap,fullHeader);
                    }
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "DiscountSalesVar";
                    commonHeader = "Discount % Variance";
                    String commString = commonColumn;
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                        tabHeaderDto = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tabHeaderDto, headerMap,fullHeader);
                    }

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "DiscountSalesPer";
                    commonHeader = "Discount % % Change";
                    String commString = commonColumn;
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                        tabHeaderDto = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tabHeaderDto, headerMap,fullHeader);
                    }
                }
            }
            if (projSelDTO.isVarRPU()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "RPUValue";
                    commonHeader = "RPU Value";
                    String commString = commonColumn;
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                        tabHeaderDto = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tabHeaderDto, headerMap,fullHeader);
                    }
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "RPUVariance";
                    commonHeader = "RPU Variance";
                    String commString = commonColumn;
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                        tabHeaderDto = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tabHeaderDto, headerMap,fullHeader);
                    }
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "RPUPer";
                    commonHeader = "RPU %Change";
                    String commString = commonColumn;
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                        tabHeaderDto = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tabHeaderDto, headerMap,fullHeader);
                    }
                }
            }
            if (projSelDTO.isDiscountPerExFactory()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "DiscountPerExFactoryValue";
                    commonHeader = "Discount % Of Ex-Factory Value";
                    String commString = commonColumn;
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                        tabHeaderDto = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tabHeaderDto, headerMap,fullHeader);
                    }
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "DiscountPerExFactoryVar";
                    commonHeader = "Discount % Of Ex-Factory variance";
                    String commString = commonColumn;
                    
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                        tabHeaderDto = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tabHeaderDto, headerMap,fullHeader);
                    }
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "DiscountPerExFactoryPer";
                    commonHeader = "Discount % Of Ex-Factory % change";
                    String commString = commonColumn;
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                        tabHeaderDto = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tabHeaderDto, headerMap,fullHeader);
                    }
                }
            }
            if (projSelDTO.isVarNetSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "NetSalesValue";
                    commonHeader = "Net Sales Value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "NetSalesVariance";
                    commonHeader = "Net Sales Variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "NetSalesPer";
                    commonHeader = "Net Sales %Change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
             if (projSelDTO.isNetSalesExFactory()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "NetSalesExFactoryValue";
                    commonHeader = "Net Sales % of Ex-Factory Value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "NetSalesExFactoryVariance";
                    commonHeader = "Net Sales % of Ex-Factory Variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "NetSalesExFactoryPer";
                    commonHeader = "Net Sales % of Ex-Factory %Change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            if (ConstantsUtil.DETAIL.equals(projSelDTO.getLevel()) && (projSelDTO.getView().equals(Constants.PRODUCT_LABEL) || projSelDTO.getView().equals(Constants.LabelConstants.CUSTOM.toString()))) {
                if (projSelDTO.isNetExFactorySales()) {
                    if (projSelDTO.isColValue()) {
                        commonColumn = ConstantsUtil.NET_EXFACT_SALES_COLUMN_VALUE;
                        commonHeader = ConstantsUtil.NET_EXFACT_SALES_HEADER_VALUE;
                        tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                    }
                    if (projSelDTO.isColVariance()) {
                        commonColumn = ConstantsUtil.NET_EXFACT_SALES_COLUMN_VARIANCE;
                        commonHeader = ConstantsUtil.NET_EXFACT_SALES_HEADER_VARIANCE;
                        tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                    }
                    if (projSelDTO.isColPercentage()) {
                        commonColumn = ConstantsUtil.NET_EXFACT_SALES_COLUMN_PER_CHANGE;
                        commonHeader = ConstantsUtil.NET_EXFACT_SALES_HEADER_PER_CHANGE;
                        tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                    }
                }
                if (projSelDTO.isNetExFactorySalesPerExFactory()) {
                    if (projSelDTO.isColValue()) {
                        commonColumn = ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VALUE;
                        commonHeader = ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_HEADER_VALUE;
                        tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                    }
                    if (projSelDTO.isColVariance()) {
                        commonColumn = ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VARIANCE;
                        commonHeader = ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_HEADER_VARIANCE;
                        tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                    }
                    if (projSelDTO.isColPercentage()) {
                        commonColumn = ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_PER_CHANGE;
                        commonHeader = ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_HEADER_PER_CHANGE;
                        tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                    }
                }
            }
            if (projSelDTO.isVarCOGC()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "COGCValue";
                    commonHeader = "COGS Value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "COGCVariance";
                    commonHeader = "COGS Variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "COGCPer";
                    commonHeader = "COGS %Change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            if (projSelDTO.isVarNetProfit()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "NetProfitValue";
                    commonHeader = "Net Profit Value";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "NetProfitVariance";
                    commonHeader = "Net Profit Variance";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "NetProfitPer";
                    commonHeader = "Net Profit %Change";
                    tabHeaderDto = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tabHeaderDto, fullHeader);
                }
            }
            List<String> periodList = projSelDTO.getPeriodList();
            List<String> periodListUpper = new ArrayList<>();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            Map<String, String> periodListMapUpper = new HashMap<>();
            for (int i = 0; i < periodList.size(); i++) {
                String commonColumn1 = periodList.get(i);
                String comHeader = periodListMap.get(commonColumn1);
                String comColumn = commonColumn1;
                if (frequencyDivision != NumericConstants.TWELVE) {
                    comColumn = commonColumn1.toUpperCase();
                }
                periodListUpper.add(comColumn);
                periodListMapUpper.put(comColumn, comHeader);

            }
            projSelDTO.setPeriodList(periodListUpper);
            projSelDTO.setPeriodListMap(periodListMapUpper);
            projSelDTO.setRightHeader(tabHeaderDto);
        }
        projSelDTO.setPeriodHeaderList(projSelDTO.getPeriodList());
        List<Object> headerContents = new ArrayList<>();
        headerContents.add(tabHeaderDto);
        projSelDTO.setColumns(CommonUtils.objectListToStringList(tabHeaderDto.getSingleColumns()));
        return headerContents;
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
            List<String> projectionColumnList = new ArrayList<>();
            List<String> projectionHeaderList = new ArrayList<>();

            startPr = projSelDTO.getProjectionStartPeriod() == 0 ? 1 : projSelDTO.getProjectionStartPeriod();
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

    /**
     * To check whether the given string is integer or not
     *
     * @param s
     * @return
     */
    public static boolean isInteger(String s) {
        try {
              return s != null && !"null".equals(s)&& s.matches("^\\d+$");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static List<String> getCommonColumnHeader(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<>();
        String commonColumn = "";
        String commonHeader = "";
        if (frequencyDivision == 1) {
            commonColumn = Integer.toString(year); 
            commonHeader = Integer.toString(year);
        } else if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = "q" + period + "" + year;
            commonHeader = "Q" + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = "s" + period + "" + year;
            commonHeader = "S" + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase(Locale.ENGLISH) + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    static CustomTableHeaderDTO loadSingleDiscountHeader(String commonColumn, String commonHeader, final PVSelectionDTO selection, final CustomTableHeaderDTO tableHeaderDTO, Map<String, Object> headerMap,final CustomTableHeaderDTO excelHeaderDTO) {
        String column = commonColumn;
        String variableCategory = selection.getVariableCategory() != null ? selection.getVariableCategory() : StringUtils.EMPTY;
        List<String> discountNames = new ArrayList<>(selection.getDiscountLevel().equals("Program") ? selection.getDiscountNameList() : selection.getDiscountNameCFF());
        List<String> dedNames = !selection.getDeductionLevelFilter().isEmpty() ? selection.getDeductionLevelCaptions() : discountNames;
        List<Integer> projList = selection.getProjIdList();
        Map<Integer, String> priorMap = selection.getProjectionMap();
        List<String> commonColumnList = new ArrayList<>();
        List<Object> dmap = new ArrayList<>();
        if (!dedNames.isEmpty()) {

            for (int i = 0; i < dedNames.size(); i++) {
                dmap.clear();
                String disCommonHeader = dedNames.get(i);
                column = column + disCommonHeader.replace(" ", "") + i;
                
                if(variableCategory.contains(StringConstantsUtil.ACTUALS1)){
                tableHeaderDTO.addSingleColumn(column + ConstantsUtil.ACTUAL + selection.getCurrentProjId(), StringConstantsUtil.ACTUALS1, String.class);
                excelHeaderDTO.addSingleColumn(column + ConstantsUtil.ACTUAL + selection.getCurrentProjId(),  disCommonHeader+" "+StringConstantsUtil.ACTUALS1 , String.class);
                dmap.add(column + ConstantsUtil.ACTUAL + selection.getCurrentProjId());
                }
                if(variableCategory.contains(StringConstantsUtil.ACCRUALS)){
                tableHeaderDTO.addSingleColumn(column + ConstantsUtil.ACCRUAL + selection.getCurrentProjId(), StringConstantsUtil.ACCRUALS, String.class);
                excelHeaderDTO.addSingleColumn(column + ConstantsUtil.ACCRUAL + selection.getCurrentProjId(),  disCommonHeader+" "+StringConstantsUtil.ACCRUALS , String.class);
                dmap.add(column + ConstantsUtil.ACCRUAL + selection.getCurrentProjId());
                }
                tableHeaderDTO.addSingleColumn(column + StringConstantsUtil.CURRENT_LABEL + selection.getCurrentProjId(), selection.getCurrentProjectionName(), String.class);
                excelHeaderDTO.addSingleColumn(column + StringConstantsUtil.CURRENT_LABEL + selection.getCurrentProjId(), disCommonHeader+ " "+selection.getCurrentProjectionName()+" "+commonHeader, String.class);
                dmap.add(column + StringConstantsUtil.CURRENT_LABEL + selection.getCurrentProjId());
                if (!projList.isEmpty()) {
                    for (int j = 0; j < projList.size(); j++) {
                        tableHeaderDTO.addSingleColumn(column + projList.get(j), priorMap.get(projList.get(j)), String.class);
                        excelHeaderDTO.addSingleColumn(column + projList.get(j), disCommonHeader+ " "+priorMap.get(projList.get(j))+" "+commonHeader, String.class);
                        dmap.add(column + projList.get(j));
                    }
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(column, disCommonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(column, dmap.toArray());
                    excelHeaderDTO.addDoubleColumn(column, commonHeader);
                    excelHeaderDTO.addDoubleHeaderMap(column, dmap.toArray());
                }
                commonColumnList.add(column);
                headerMap.put(column, commonColumnList);
            }

        }
        selection.setHeaderMap(headerMap);

        return tableHeaderDTO;
    }

    public static void setBaseVariables(String columns, String varriables, PVSelectionDTO projSelDTO) {
        LOGGER.debug("Entering setBaseVariables method");
        projSelDTO.setColValue(false);
        projSelDTO.setColVariance(false);
        projSelDTO.setColPercentage(false);
        projSelDTO.setVarExFacSales(false);
        projSelDTO.setVarDemandSales(false);
        projSelDTO.setVarInvSales(false);
        projSelDTO.setVarPerExFacSales(false);
        projSelDTO.setVarPerDemandSales(false);
        projSelDTO.setVarPerInvSales(false);
        projSelDTO.setVarContractsales(false);
        projSelDTO.setVarContractUnits(false);
        projSelDTO.setVarDisAmount(false);
        projSelDTO.setVarDisRate(false);
        projSelDTO.setVarNetSales(false);
        projSelDTO.setVarCOGC(false);
        projSelDTO.setVarRPU(false);
        projSelDTO.setVarExFacCustomer(false);
        projSelDTO.setVarPerExFacCustomer(false);
        projSelDTO.setVarAdjDemand(false);
        projSelDTO.setVarPerAdjDemand(false);
        projSelDTO.setVarIwDetails(false);
        projSelDTO.setVarPerIwDetails(false);
        projSelDTO.setNetExFactorySales(false);
        projSelDTO.setNetExFactorySalesPerExFactory(false);
        String column = columns.substring(1, columns.length() - 1);
        String varriable = varriables.substring(1, varriables.length() - 1);
        final String[] col = column.split(",");
        final String[] var = varriable.split(",");
        for (String value : col) {

            value = StringUtils.trim(value);
            if (value.equalsIgnoreCase(StringUtils.trim(CommonUtils.COL_VALUE))) {
                projSelDTO.setColValue(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(CommonUtils.COL_VARIANCE))) {
                projSelDTO.setColVariance(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(CommonUtils.COL_PERCENTAGE))) {
                projSelDTO.setColPercentage(true);
            }
        }

        for (String value : var) {

            value = StringUtils.trim(value);
            if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.EX_FACTORY_PRODUCT.toString()))) {
                projSelDTO.setVarExFacSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.DEMAND.toString()))) {
                projSelDTO.setVarDemandSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.INVENTORY_SUMMARY.toString()))) {
                projSelDTO.setVarInvSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString()))) {
                projSelDTO.setVarPerExFacSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.PER_DEMAND.toString()))) {
                projSelDTO.setVarPerDemandSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString()))) {
                projSelDTO.setVarPerInvSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_CONTRACT_SALES.toString()))) {
                projSelDTO.setVarContractsales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_CONTRACT_UNITS.toString()))) {
                projSelDTO.setVarContractUnits(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_DIS_AMOUNT.toString()))) {
                projSelDTO.setVarDisAmount(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_DIS_RATE.toString()))) {
                projSelDTO.setVarDisRate(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_RPU.toString()))) {
                projSelDTO.setVarRPU(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_NETSALES.toString()))) {
                projSelDTO.setVarNetSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_COGS.toString()))) {
                projSelDTO.setVarCOGC(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.VAR_NET_PROFITE.toString()))) {
                projSelDTO.setVarNetProfit(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString()))) {
                projSelDTO.setVarExFacCustomer(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString()))) {
                projSelDTO.setVarPerExFacCustomer(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.ADJUSTED_DEMAND.toString()))) {
                projSelDTO.setVarAdjDemand(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString()))) {
                projSelDTO.setVarPerAdjDemand(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.INVENTORY_DETAILS.toString()))) {
                projSelDTO.setVarIwDetails(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString()))) {
                projSelDTO.setVarPerIwDetails(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(ConstantsUtil.PVVariables.NET_EX_FACTORY_SALES.toString()))) {
                projSelDTO.setNetExFactorySales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(ConstantsUtil.PVVariables.NET_EX_FACTORY_SALES_PER_EX_FACTORY.toString()))) {
                projSelDTO.setNetExFactorySalesPerExFactory(true);
            }
        }

        LOGGER.debug("End of setBaseVariables method");
    }

    public static CustomTableHeaderDTO getProjectionResultsLeftTableColumns(final ProjectionSelectionDTO projSelDTO, final CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object doubleCol = "group1";
        Object[] singleCol = {StringConstantsUtil.GROUP_PROPERTY};
        tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        tableHeaderDTO.addDoubleColumn(doubleCol, projSelDTO.getView());
        tableHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        fullHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        fullHeaderDTO.addDoubleColumn(doubleCol, projSelDTO.getView());
        fullHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getProjectionResultsRightTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        LOGGER.debug("Inside Get Projections Results Colimn Right=========");
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        LOGGER.debug("Ending Get Projections Results Colimn Right=========");
        return getCalculatedProjectionColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }

    public static CustomTableHeaderDTO getCalculatedProjectionColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        LOGGER.debug("Inside Get Projections Results getCalculatedProjectionColumns Right=========");
        String projOrder = projSelDTO.getProjectionOrder();

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
        int projectionOrder;
        if (projOrder.contains("Ascending")) {
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
        List<Object> singleColumnForExcel = new ArrayList<>();
        List<String> singleHeaderForExcel = new ArrayList<>();
        List<Object> doubleColumnForExcel = new ArrayList<>();
        List<String> doubleHeaderForExcel = new ArrayList<>();
        Map<Object, Object[]> doubleHeaderMap = new HashMap<>();
        List headerListForExcel;
        int tempYear;
        int j = 0; 
        int k = 0;
        if (pivotView.contains(VARIABLE.getConstant())) {

            configurePivotHeaderForNonMandated(projSelDTO, projections, tableHeaderDTO, fullHeaderDTO);

        } else {

            List<String> periodList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            Object leftColumn = fullHeaderDTO.getSingleColumns().get(0);
            String leftHeader = fullHeaderDTO.getSingleHeaders().get(0);
            List<Object> dmap = new ArrayList<>();
            for (int i = 0; i < periodList.size(); i++) {
                String commonColumn = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn);
                boolean historyFlag = false;
                boolean projectionCol = false;
                boolean excelTab = projSelDTO.getFrequencyDivision() == NumericConstants.FOUR || projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE;
                tempYear = excelTab ? projSelDTO.getFrequencyDivision() == NumericConstants.FOUR ? Integer.parseInt(commonColumn.substring(NumericConstants.TWO, commonColumn.length()))
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
                    singleColumnForExcel.add(leftColumn);
                    singleHeaderForExcel.add(leftHeader);
                    doubleColumnForExcel.add(StringUtils.EMPTY);
                    doubleHeaderForExcel.add(StringUtils.EMPTY);
                    doubleHeaderMap.put(StringUtils.EMPTY, new Object[]{leftColumn});
                    k = tempYear;
                }
                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    historyFlag = true;

                    if (projections.contains(BOTH) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, ACTUALS.getConstant());
                        fullHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                        singleColumnForExcel.add(singleColumn);
                        singleHeaderForExcel.add(ACTUALS.getConstant());
                    }
                }
                if ((isProjectionIndex(i, projSelDTO)) || (isHistoryAndProjectionColumns(historyFlag, projections)) || (isForecastIndex(i, projSelDTO))) {
                    projectionCol = true;
                }

                if (projectionCol) {
                    Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTIONS.getConstant());
                    fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                    singleColumnForExcel.add(singleColumn);
                    singleHeaderForExcel.add(PROJECTIONS.getConstant());
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

    private static boolean isForecastIndex(int i, ProjectionSelectionDTO projSelDTO) {
        return i >= projSelDTO.getForecastStartIndex() && i <= projSelDTO.getForecastEndIndex();
    }

    private static boolean isHistoryAndProjectionColumns(boolean historyFlag, String projections) {
        return historyFlag && (projections.contains(BOTH) || projections.contains(PROJECTIONS.getConstant()));
    }

    private static boolean isProjectionIndex(int i, ProjectionSelectionDTO projSelDTO) {
        return i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex();
    }

    private static void configurePivotHeaderForNonMandated(final ProjectionSelectionDTO projSelDTO, String projections, CustomTableHeaderDTO tableHeaderDTO, CustomTableHeaderDTO fullHeaderDTO) {
        projSelDTO.setDiscountNameCFF(ProjectionResultsLogic.getRSName(projSelDTO.getProjectionId()));
        List<String> discountNames = new ArrayList<>(projSelDTO.getDiscountNameCFF());
        List<Object> dmap = new ArrayList<>();
        for (int i = 0; i < NumericConstants.TWENTY_TWO; i++) {
            String commonColumn = "";
            String oldCommonColumn = "";
            String commonHeader = "";
            if (i == 0) {
                commonColumn = "exFactory";
                commonHeader = "Ex-Factory Product";
            } else if (i == 1) {
                commonColumn = "custExFactory";
                commonHeader = "Ex-Factory Customer";
            } else if (i == NumericConstants.TWO) {
                commonColumn = "demand";
                commonHeader = "Demand";
            } else if (i == NumericConstants.THREE) {
                commonColumn = "adjDemand";
                commonHeader = "Adjusted Demand ";
            } else if (i == NumericConstants.FOUR) {
                commonColumn = "inventory";
                commonHeader = "Inventory Withdrawal Summary";
            } else if (i == NumericConstants.FIVE) {
                commonColumn = "inventoryDetails";
                commonHeader = "Inventory Withdrawal Details ";
            } else if (i == NumericConstants.SIX) {
                commonColumn = "perExFactory";
                commonHeader = "% of Ex-Factory Product";
            } else if (i == NumericConstants.SEVEN) {
                commonColumn = "perCustExFactory";
                commonHeader = "% of Ex-Factory Customer";
            } else if (i == NumericConstants.EIGHT) {
                commonColumn = "perDemand";
                commonHeader = "% of Demand";
            } else if (i == NumericConstants.NINE) {
                commonColumn = "perAdjDemand";
                commonHeader = "% of Adjusted Demand";
            } else if (i == NumericConstants.TEN) {
                commonColumn = "perInventory";
                commonHeader = "% of Inventory Withdrawal Summary";
            } else if (i == NumericConstants.ELEVEN) {
                commonColumn = "perInventoryDetails";
                commonHeader = "% of Inventory Withdrawal Details";
            } else if (i == NumericConstants.TWELVE) {
                if (projSelDTO.getSalesOrUnit().equals(BOTH) || projSelDTO.getSalesOrUnit().equals(SALES.getConstant())) {
                    commonColumn = "conSalesWac";
                    commonHeader = "Contract Sales @ WAC";
                } else {
                    continue;
                }
            } else if (i == NumericConstants.THIRTEEN) {
                if (projSelDTO.getSalesOrUnit().equals(BOTH) || projSelDTO.getSalesOrUnit().equals(UNITS.getConstant())) {
                    commonColumn = "unitVol";
                    commonHeader = "Unit Volume";
                } else {
                    continue;
                }
            } else if (i == NumericConstants.FOURTEEN) {
                commonColumn = "totDisPer";
                commonHeader = "Total Discount %";
            } else if (i == NumericConstants.FIFTEEN) {
                commonColumn = "totalRPU";
                commonHeader = "Total RPU";
            } else if (i == NumericConstants.SIXTEEN) {
                commonColumn = "totDisDol";
                commonHeader = "Total Discount $";
            } else if (i == NumericConstants.SEVENTEEN) {
                commonColumn = "disPerExFactory";
                commonHeader = Constants.LabelConstants.DISCOUNT_PERCENTAGE_EXFACTORY.getConstant();
            } else if (i == NumericConstants.EIGHTEEN) {
                commonColumn = "netSales";
                commonHeader = "Net Sales";
            } else if (i == NumericConstants.NINETEEN) {
                commonColumn = "netSalesPerExFactory";
                commonHeader = Constants.LabelConstants.NET_SALES_PERCENTAGE_EXFACTORY.getConstant();
            } else if (i == NumericConstants.TWENTY) {
                commonColumn = "cogs";
                commonHeader = "Cost of Goods Sold (COGS)";
            } else if (i == NumericConstants.TWENTY_ONE) {
                commonColumn = "netProfit";
                commonHeader = "Net Profit";
            }
            oldCommonColumn = commonColumn;
            int j = -1;
            boolean disc = true;
            while (disc) {
                if (projections.contains(BOTH) || projections.contains(ACTUALS.getConstant())) {
                    Object singleColumn = commonColumn + ACTUALS.getConstant();
                    dmap.add(singleColumn);
                    if (i == NumericConstants.SIX || i == NumericConstants.SEVEN || i == NumericConstants.EIGHT || i == NumericConstants.NINE || i == NumericConstants.TEN || i == NumericConstants.ELEVEN || i == NumericConstants.FOURTEEN || i == NumericConstants.SEVENTEEN) {
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);
                    } else {
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                    }

                }
                Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                dmap.add(singleColumn);
                if (i == NumericConstants.SIX || i == NumericConstants.SEVEN || i == NumericConstants.EIGHT || i == NumericConstants.NINE || i == NumericConstants.TEN || i == NumericConstants.ELEVEN || i == NumericConstants.FOURTEEN || i == NumericConstants.SEVENTEEN) {
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_RATE.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);
                } else {
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                }
                disc = false;
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
                if (!discountNames.isEmpty() && (i == NumericConstants.FOURTEEN || i == NumericConstants.FIFTEEN || i == NumericConstants.SIXTEEN || i == NumericConstants.SEVENTEEN) && discountNames.size() > (j + 1)) {
                        disc = true;
                        j++;
                        commonHeader = discountNames.get(j);
                        commonColumn = oldCommonColumn + commonHeader.replace(" ", "");

                }
            }
        }
    }
}