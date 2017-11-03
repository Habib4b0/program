/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.projectionresults.utils;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.LabelConstants.*;
import static com.stpl.app.gtnforecasting.utils.Constant.ProjectionConstants.*;
import static com.stpl.app.utils.Constants.FrequencyConstants.*;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUAL_RATE;
import static com.stpl.app.utils.Constants.LabelConstants.BOTH;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTED_RATE;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import static com.stpl.app.utils.Constants.LabelConstants.SALES;
import static com.stpl.app.utils.Constants.LabelConstants.UNITS;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.sun.istack.logging.Logger;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sibi
 */
public class HeaderUtils {

    public static final Logger LOGGER = Logger.getLogger(HeaderUtils.class);
    public static final String NET_PROFIT1_PROPERTY = "netProfit";
    public static final String NET_SALES1 = "netSales";
    public static final String UNIT_VOL1 = "unitVol";
    public static final String CON_SALES_WAC = "conSalesWac";
    public static final String TOT_RPU = "totRPU";

    /**
     * The Constant Mandated Projection Results Right Table Columns.
     */
    public final Object[] prRightTableOneColumns = new Object[]{
        "efs", "dms", "iws", "perOfExfac", "perOfDemand", "perOfInvwithdraw", CON_SALES_WAC, UNIT_VOL1,
        Constant.TOT_DIS_PER, "totDisPerMandatedDiscount", "totDisPerSupplementalDiscount", TOT_RPU, "totRPUMandatedDiscount", "totRPUSupplementalDiscount",
        Constant.TOTAL_DISCOUNT_DOLLAR, "totDisDolMandatedDiscount", "totDisDolSupplementalDiscount", NET_SALES1, "cogs", NET_PROFIT1_PROPERTY};
    
    public static final String COST_OF_GOODS_SOLD_COGS = "Cost of Goods Sold (COGS)";
    public static final String PERCENT_OF_EX_FACTORY = "% of Ex-Factory";
    public static final String NET_PROFIT = "Net Profit";
    public static final String TOTAL_DISCOUNT_AMOUNT_DOLLAR = "Total Discount $";
    public static final String TOTAL_DISCOUNT_PERCENT_AMOUNT = "Total Discount %";
    public static final String PERCENT_OF_INVENTORY_WITHDRAWAL = "% of Inventory Withdrawal";
    public static final String PERCENT_OF_DEMAND = "% of Demand";
    public static final String INVENTORY_WITHDRAWAL_SALES = "Inventory Withdrawal Sales";
    public static final String DEMAND_SALES = "Demand Sales";
    public static final String CONTRACT_SALES_WAC_AT = "Contract Sales @ WAC";
    public static final String NET_SALES_LABEL = "Net Sales";

    /**
     * The Constant Mandated Projection Results Right Table Headers.
     */
    public final String[] prRightTableOneHeaders = new String[]{
        Constant.EX_FACTORY_SALES_LABEL, DEMAND_SALES, INVENTORY_WITHDRAWAL_SALES, PERCENT_OF_EX_FACTORY, PERCENT_OF_DEMAND, PERCENT_OF_INVENTORY_WITHDRAWAL, CONTRACT_SALES_WAC_AT, Constant.UNIT_VOLUME, TOTAL_DISCOUNT_PERCENT_AMOUNT, Constant.MANDATED_DISCOUNT, Constant.SUPPLEMENTAL_DISCOUNT_LABEL, Constant.TOTAL_RPU_CAPS, Constant.MANDATED_DISCOUNT, Constant.SUPPLEMENTAL_DISCOUNT_LABEL, TOTAL_DISCOUNT_AMOUNT_DOLLAR,
        Constant.MANDATED_DISCOUNT, Constant.SUPPLEMENTAL_DISCOUNT_LABEL, NET_SALES_LABEL, COST_OF_GOODS_SOLD_COGS, NET_PROFIT};

    /**
     * Private Constructor to avoid object instantiation outside the class.
     */
    private HeaderUtils() {

    }

    /**
     * Generates the left table column headers for Government , Commercial and
     * Other Channels.
     *
     * @param projSelDTO
     * @param fullHeaderDTO
     * @return
     */
    public static CustomTableHeaderDTO getProjectionResultsLeftTableColumns(final CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object doubleCol = "group1";
        Object[] singleCol = {Constant.GROUP};
        tableHeaderDTO.addSingleColumn(singleCol[0], GROUP_COLON, String.class);
        tableHeaderDTO.addDoubleColumn(doubleCol, GROUP_COLON);
        tableHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        fullHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        fullHeaderDTO.addDoubleColumn(doubleCol, GROUP_COLON);
        fullHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        return tableHeaderDTO;
    }
    
    public static final String GROUP_COLON = "Group:";

    /**
     * Method creates the header for the right table in Commercial , Government
     * and Other Channels Forecasting.
     *
     * @param projSelDTO
     * @param fullHeaderDTO
     * @return
     */
    public static CustomTableHeaderDTO getProjectionResultsRightTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getCalculatedProjectionColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }

    /**
     * Method calculates the actual and projection periods and generates the
     * headers dynamically based on the given inputs for Commercial,Government
     * and Other Channels Forecasting.
     *
     * @param tableHeaderDTO
     * @param projSelDTO
     * @param fullHeaderDTO
     * @return
     */
    public static CustomTableHeaderDTO getCalculatedProjectionColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
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
        if (pivotView.contains(VARIABLE.getConstant())) {

            String screenName = StringUtils.isBlank(projSelDTO.getScreenName()) ? StringUtils.EMPTY : projSelDTO.getScreenName();
            switch (screenName) {
                case CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED:
                    configurePivotHeaderForNonMandated(projSelDTO, projections, tableHeaderDTO, fullHeaderDTO);
                    break;
                case CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED:
                    configurePivotHeaderForMandated(projSelDTO, projections, tableHeaderDTO, fullHeaderDTO);
                    break;
                case CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS:
                    configurePivotHeaderForChannels(projSelDTO, projections, tableHeaderDTO, fullHeaderDTO);
                    break;
                default:
                    LOGGER.warning("BUSINESS_PROCESS_TYPE is Empty. Commercial forecast is generated by default.");
                    configurePivotHeaderForNonMandated(projSelDTO, projections, tableHeaderDTO, fullHeaderDTO);
                    break;
            }

        } else {

            List<String> periodList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            for (int i = 0; i < periodList.size(); i++) {
                List<Object> dmap = new ArrayList<>();
                String commonColumn = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn);
                boolean historyFlag = false;
                boolean projectionCol = false;
                if (!commonColumn.contains("wrong")) {
                    if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                        historyFlag = true;

                        if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                            Object singleColumn = commonColumn + ACTUALS.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                            tableHeaderDTO.addSingleHistoryColumn(singleColumn, ACTUALS.getConstant());
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                        }
                    }
                    if (i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex()) {
                        projectionCol = true;
                    } else if (historyFlag && (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTIONS.getConstant()))) {
                        projectionCol = true;
                    }

                    if (i >= projSelDTO.getForecastStartIndex() && i <= projSelDTO.getForecastEndIndex()) {
                        projectionCol = true;
                    }

                    if (projectionCol) {
                        Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                        tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTIONS.getConstant());
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                    }
                    if (!dmap.isEmpty()) {
                        tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    }
                }
            }

        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    /**
     * Configures the Projection Selection DTO based on the inputs from Session
     * and Forecast configuration.
     *
     * @param projSelDTO
     */
    public static void getHistoryAndProjectionDetails(ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = 1;
        int historyStartPeriod = 1;
        int historyEndPeriod = 1;
        int historyEndMonth = 1;
        int historyEndYear = 1;
        int forecastStartPeriod = 1;
        int forecastEndPeriod = 1;
        int projectionStartPeriod = 1;
        int projectionEndPeriod = 1;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(QUARTERLY.getConstant())) {
            frequencyDivision = NumericConstants.FOUR;
            historyStartPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), NumericConstants.THREE);
            historyEndPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), NumericConstants.THREE);
            forecastStartPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), NumericConstants.THREE);
            forecastEndPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), NumericConstants.THREE);
            projectionStartPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), NumericConstants.THREE);
            projectionEndPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), NumericConstants.THREE);
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequencyDivision = NumericConstants.TWO;
            historyStartPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), NumericConstants.SIX);
            historyEndPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), NumericConstants.SIX);
            forecastStartPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), NumericConstants.SIX);
            forecastEndPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), NumericConstants.SIX);
            projectionStartPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), NumericConstants.SIX);
            projectionEndPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), NumericConstants.SIX);
        } else if (frequency.equals(MONTHLY.getConstant())) {
            frequencyDivision = NumericConstants.TWELVE;
            historyStartPeriod = projSelDTO.getForecastDTO().getHistoryStartMonth();
            historyEndPeriod = projSelDTO.getForecastDTO().getHistoryEndMonth();
            forecastStartPeriod = projSelDTO.getForecastDTO().getForecastStartMonth();
            forecastEndPeriod = projSelDTO.getForecastDTO().getForecastEndMonth();
            projectionStartPeriod = projSelDTO.getForecastDTO().getProjectionStartMonth();
            projectionEndPeriod = projSelDTO.getForecastDTO().getProjectionEndMonth();
        } else if (frequency.equals(ANNUALLY.getConstant())) {
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
                historyEndYear = historyEndYear - 1;
                historyEndMonth = NumericConstants.TWELVE;
            } else if (historyEndPeriod < 1) {
                historyEndPeriod = frequencyDivision;
                historyEndMonth = NumericConstants.TWELVE;
                historyEndYear = historyEndYear - 1;
            } else {
                historyEndMonth = (NumericConstants.TWELVE / frequencyDivision) * historyEndPeriod;
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

        getHistoryDetail(projSelDTO);
    }

    /**
     * Gets the frequency index based on the
     *
     * @param monthNo
     * @param division
     * @return
     */
    static int getPeriod(int monthNo, int division) {
        return ((monthNo - 1) / division) + 1;
    }

    /**
     * Returns the end day of the month.
     *
     * @param monthNo
     * @param year
     * @return
     */
    static int getEndDay(int monthNo, int year) {
        Calendar ob = Calendar.getInstance();
        ob.set(year, monthNo - 1, 1);
        int daysInMonth = ob.getActualMaximum(Calendar.DAY_OF_MONTH);
        return daysInMonth;
    }

    /**
     * To get Start year, Start period and Start month
     *
     * @param projSelDTO
     * @return
     */
    static ProjectionSelectionDTO getHistoryDetail(ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        int historyNum = projSelDTO.getHistoryNum();
        int historyStartYear = projSelDTO.getHistoryEndYear();
        int historyEndPeriod = projSelDTO.getHistoryEndPeriod();
        int historyStartFreq = historyEndPeriod + 1;
        if (frequencyDivision == 1) {
            historyStartYear = historyStartYear - historyNum + 1;
            historyStartFreq = historyStartYear;
        } else {
            int historyTempFreq = historyNum - historyEndPeriod;
            if (historyTempFreq > 0) {
                historyStartYear = historyStartYear - historyTempFreq / frequencyDivision;
                historyStartFreq = 1;
                if (historyTempFreq % frequencyDivision > 0) {
                    historyStartYear = historyStartYear - 1;
                    historyStartFreq = frequencyDivision - (historyTempFreq % frequencyDivision) + 1;
                }
            } else {
                historyStartFreq = historyStartFreq - historyNum;
            }
        }
        if (frequencyDivision == 1) {
            projSelDTO.setHistoryStartMonth(frequencyDivision);
        } else if (frequencyDivision == NumericConstants.TWO) {
            if (historyStartFreq == 1) {
                projSelDTO.setHistoryStartMonth(1);
            } else if (historyStartFreq == NumericConstants.TWO) {
                projSelDTO.setHistoryStartMonth(NumericConstants.SEVEN);
            }
        } else if (frequencyDivision == NumericConstants.FOUR) {
            if (historyStartFreq == 1) {
                projSelDTO.setHistoryStartMonth(1);
            } else if (historyStartFreq == NumericConstants.TWO) {
                projSelDTO.setHistoryStartMonth(NumericConstants.FOUR);
            } else if (historyStartFreq == NumericConstants.THREE) {
                projSelDTO.setHistoryStartMonth(NumericConstants.SEVEN);
            } else if (historyStartFreq == NumericConstants.FOUR) {
                projSelDTO.setHistoryStartMonth(NumericConstants.TEN);
            }
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            projSelDTO.setHistoryStartMonth(historyStartFreq);
        }
        if (projSelDTO.getHistoryStartPeriod() == historyStartFreq && projSelDTO.getForecastDTO().getHistoryStartYear() == historyStartYear) {
            projSelDTO.setHistoryStartMonth(projSelDTO.getForecastDTO().getHistoryStartMonth());
        } else {
            projSelDTO.setHistoryStartMonth(projSelDTO.getForecastDTO().getHistoryStartMonth());
        }
        projSelDTO.setHistoryStartPeriod(historyStartFreq);
        projSelDTO.setHistoryStartYear(historyStartYear);
        projSelDTO.setHistoryStartDay(1);

        int startYear = projSelDTO.getCurrentYear();

        int currentPeriod = projSelDTO.getCurrentPeriod();

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

    /**
     * Generates the Common Column Headers based on the given inputs.
     *
     * @param projSelDTO
     */
    public static void prepareCommonColumnHeaders(ProjectionSelectionDTO projSelDTO) {

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projOrder = projSelDTO.getProjectionOrder();
        int projectionOrder;
        if (projOrder.contains(Constant.ASCENDING)) {
            projectionOrder = 1;
        } else {
            projectionOrder = NumericConstants.TWO;
        }
        String yearValue = projSelDTO.getYear();
        int year = isInteger(yearValue) ? Integer.valueOf(yearValue) : 0;
        int historyStartIndex = -1;
        int projectionStartIndex = -1;
        int forecastStartIndex = -1;
        int historyEndIndex = -1;
        int projectionEndIndex = -1;
        int forecastEndIndex = -1;
        List<String> periodList = new ArrayList<>();
        Map<String, String> periodListMap = new HashMap<>();
        int startPr = projSelDTO.getStartPeriod();
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
            startPr = projSelDTO.getProjectionStartPeriod();

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
            startPr = projSelDTO.getHistoryStartPeriod();
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
            startPr = projSelDTO.getForecastStartPeriod();

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

    /**
     * Creates the Visible Column dynamically based on the frequency for the
     * given period and year.
     *
     * @param frequencyDivision Frequency Division : 1 - Annual , 2 -
     * Semi-Annual , 4 - Quarterly, 12 - Monthly.
     * @param year Year
     * @param period Period
     * @return
     */
    public static List<String> getCommonColumnHeader(final int frequencyDivision, final int year, final int period) {
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
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    /**
     * Returns the Name of the month based on the month number.
     *
     * @param num Month number.
     * @return Month Name.
     */
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
     * Configures the Table Headers for Commercial forecast in Pivot View.
     *
     * @param projSelDTO
     * @param projections
     * @param tableHeaderDTO
     * @param fullHeaderDTO
     */
    private static void configurePivotHeaderForNonMandated(final ProjectionSelectionDTO projSelDTO, String projections, CustomTableHeaderDTO tableHeaderDTO, CustomTableHeaderDTO fullHeaderDTO) {
        List<String> discountNames = new ArrayList<>(projSelDTO.getDiscountNameList());
        List<String> discountNos=new  ArrayList<>(projSelDTO.getDiscountNoList()); 
        //PPA
        CommonLogic.getPPADiscountListPR(projSelDTO, discountNos, discountNames);

        for (int i = 0; i < NumericConstants.SIXTEEN; i++) {
            String commonColumn = StringUtils.EMPTY;
            String oldCommonColumn;
            String commonHeader = StringUtils.EMPTY;
            if (i == 0) {
                commonColumn = "exFactory";
                commonHeader = Constant.EX_FACTORY_SALES_LABEL;
            } else if (i == 1) {
                commonColumn = "demand";
                commonHeader = DEMAND_SALES;
            } else if (i == NumericConstants.TWO) {
                commonColumn = "inventory";
                commonHeader = INVENTORY_WITHDRAWAL_SALES;
            } else if (i == NumericConstants.THREE) {
                commonColumn = "perExFactory";
                commonHeader = PERCENT_OF_EX_FACTORY;
            } else if (i == NumericConstants.FOUR) {
                commonColumn = "perDemand";
                commonHeader = PERCENT_OF_DEMAND;
            } else if (i == NumericConstants.FIVE) {
                commonColumn = "perInventory";
                commonHeader = PERCENT_OF_INVENTORY_WITHDRAWAL;
            } else if (i == NumericConstants.SIX) {
                if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant()) || projSelDTO.getSalesOrUnit().equals(SALES.getConstant())) {
                    commonColumn = CON_SALES_WAC;
                    commonHeader = CONTRACT_SALES_WAC_AT;
                } else {
                    continue;
                }
            } else if (i == NumericConstants.SEVEN) {
                if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant()) || projSelDTO.getSalesOrUnit().equals(UNITS.getConstant())) {
                    commonColumn = UNIT_VOL1;
                    commonHeader = Constant.UNIT_VOLUME;
                } else {
                    continue;
                }
            } else if (i == NumericConstants.EIGHT) {
                commonColumn = Constant.TOT_DIS_PER;
                commonHeader = TOTAL_DISCOUNT_PERCENT_AMOUNT;
            } else if (i == NumericConstants.NINE) {
                commonColumn = Constant.TOTAL_RPU;
                commonHeader = Constant.TOTAL_RPU_CAPS;
            } else if (i == NumericConstants.TEN) {
                commonColumn = Constant.TOTAL_DISCOUNT_DOLLAR;
                commonHeader = TOTAL_DISCOUNT_AMOUNT_DOLLAR;
             } else if (i == NumericConstants.ELEVEN) { 
                commonColumn = Constant.DISCOUNT_PER_OF_EX_FACTORY;
                commonHeader = Constant.DISCOUNT_PER_OF_EX_FACTORY_HEADER;   
            } else if (i == NumericConstants.TWELVE) {
                commonColumn = NET_SALES1;
                commonHeader = NET_SALES_LABEL;
            } else if (i == NumericConstants.THIRTEEN) { 
                commonColumn = Constant.NET_SALES_PER_OF_EX_FACTORY;
                commonHeader = Constant.NET_SALES_PER_OF_EX_FACTORY_HEADER;
            } else if (i == NumericConstants.FOURTEEN) {
                commonColumn = "cogs";
                commonHeader = COST_OF_GOODS_SOLD_COGS;
            } else if (i == NumericConstants.FIFTEEN) { 
                commonColumn = NET_PROFIT1_PROPERTY;
                commonHeader = NET_PROFIT;
            }

            oldCommonColumn = commonColumn;
            int j = -1;
            boolean disc = true;
            while (disc) {
                List<Object> dmap = new ArrayList<>();
                if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                    Object singleColumn = commonColumn + ACTUALS.getConstant();
                    dmap.add(singleColumn);
                    if (i == NumericConstants.THREE || i == NumericConstants.FOUR || i == NumericConstants.FIVE || i == NumericConstants.EIGHT) { 
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);
                    } else {
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                    }

                }
                Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                dmap.add(singleColumn); 
                if (i == NumericConstants.THREE || i == NumericConstants.FOUR || i == NumericConstants.FIVE || i == NumericConstants.EIGHT) { 
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
                if ((!discountNos.isEmpty() && (i == NumericConstants.EIGHT || i == NumericConstants.NINE || i == NumericConstants.TEN ||  i == NumericConstants.ELEVEN)) && (discountNos.size() > (j + 1))) {
                    disc = true;
                        j++;
                        commonHeader = discountNames.get(j);
                        commonColumn = oldCommonColumn +discountNos.get(j);

                }
            }
        }
    }

    /**
     * Configures the Table Headers for Government forecast in Pivot View.
     *
     * @param projSelDTO
     * @param projections
     * @param tableHeaderDTO
     * @param fullHeaderDTO
     */
    private static void configurePivotHeaderForMandated(final ProjectionSelectionDTO projSelDTO, String projections, CustomTableHeaderDTO tableHeaderDTO, CustomTableHeaderDTO fullHeaderDTO) {
        List<String> discountNames = projSelDTO.getDiscountProgramsList();
        for (int i = 0; i < NumericConstants.TWENTY; i++) {
            String commonColumn = StringUtils.EMPTY;
            String oldCommonColumn;
            String commonHeader = StringUtils.EMPTY;
            if (i == 0) {
                commonColumn = "efs";
                commonHeader = Constant.EX_FACTORY_SALES_LABEL;
            } else if (i == 1) {
                commonColumn = "dms";
                commonHeader = DEMAND_SALES;
            } else if (i == NumericConstants.TWO) {
                commonColumn = "iws";
                commonHeader = INVENTORY_WITHDRAWAL_SALES;
            } else if (i == NumericConstants.THREE) {
                commonColumn = "perOfExfac";
                commonHeader = PERCENT_OF_EX_FACTORY;
            } else if (i == NumericConstants.FOUR) {
                commonColumn = "perOfDemand";
                commonHeader = PERCENT_OF_DEMAND;
            } else if (i == NumericConstants.FIVE) {
                commonColumn = "perOfInvwithdraw";
                commonHeader = PERCENT_OF_INVENTORY_WITHDRAWAL;
            } else if (i == NumericConstants.SIX) {
                commonColumn = CON_SALES_WAC;
                commonHeader = CONTRACT_SALES_WAC_AT;
            } else if (i == NumericConstants.SEVEN) {
                commonColumn = UNIT_VOL1;
                commonHeader = Constant.UNIT_VOLUME;
            } else if (i == NumericConstants.EIGHT) {
                commonColumn = Constant.TOT_DIS_PER;
                commonHeader = TOTAL_DISCOUNT_PERCENT_AMOUNT;
            } else if (i == NumericConstants.NINE) {
                commonColumn = Constant.TOT_DIS_PER + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim(); 
                commonHeader = MANDATED_DISCOUNT.getConstant();
            } else if (i == NumericConstants.TEN) {
                commonColumn = Constant.TOT_DIS_PER + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim();
                commonHeader = SUPPLEMENTAL_DISCOUNT.getConstant();
            } else if (i == NumericConstants.ELEVEN) {
                commonColumn = TOT_RPU;
                commonHeader = Constant.TOTAL_RPU_CAPS;
            } else if (i == NumericConstants.TWELVE) {
                commonColumn = TOT_RPU + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim();
                commonHeader = MANDATED_DISCOUNT.getConstant();
            } else if (i == NumericConstants.THIRTEEN) {
                commonColumn = TOT_RPU + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim();
                commonHeader = SUPPLEMENTAL_DISCOUNT.getConstant();
            } else if (i == NumericConstants.FOURTEEN) {
                commonColumn = Constant.TOTAL_DISCOUNT_DOLLAR;
                commonHeader = TOTAL_DISCOUNT_AMOUNT_DOLLAR;
            } else if (i == NumericConstants.FIFTEEN) {
                commonColumn = Constant.TOTAL_DISCOUNT_DOLLAR + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim();
                commonHeader = MANDATED_DISCOUNT.getConstant();
            } else if (i == NumericConstants.SIXTEEN) {
                commonColumn = Constant.TOTAL_DISCOUNT_DOLLAR + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim();
                commonHeader = SUPPLEMENTAL_DISCOUNT.getConstant();
            } else if (i == NumericConstants.SEVENTEEN) {
                commonColumn = NET_SALES1;
                commonHeader = NET_SALES_LABEL;
            } else if (i == NumericConstants.EIGHTEEN) {
                commonColumn = "cogs";
                commonHeader = COST_OF_GOODS_SOLD_COGS;
            } else if (i == NumericConstants.NINETEEN) {
                commonColumn = NET_PROFIT1_PROPERTY;
                commonHeader = NET_PROFIT;
            }
            oldCommonColumn = commonColumn;
            int j = -1;
            boolean disc = true;
            while (disc) {
                List<Object> dmap = new ArrayList<>();
                if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                    Object singleColumn = commonColumn + ACTUALS.getConstant();
                    dmap.add(singleColumn);
                    if (i == NumericConstants.THREE || i == NumericConstants.FOUR || i == NumericConstants.FIVE || i == NumericConstants.EIGHT) {
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);
                    } else {
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                    }

                }
                Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                dmap.add(singleColumn);
                if (i == NumericConstants.THREE || i == NumericConstants.FOUR || i == NumericConstants.FIVE || i == NumericConstants.EIGHT) {
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
                if ((!discountNames.isEmpty() && (i == NumericConstants.NINE || i == NumericConstants.TEN || i == NumericConstants.TWELVE || i == NumericConstants.THIRTEEN || i == NumericConstants.FIFTEEN || i == NumericConstants.SIXTEEN)) && (discountNames.size() > (j + 1))) {
                        disc = true;
                        j++;
                        commonHeader = discountNames.get(j);
                        commonColumn = oldCommonColumn + commonHeader.replace(" ", StringUtils.EMPTY).trim();
                }
            }
        }
    }

    /**
     * Configures the Table Headers for Other Channels forecast in Pivot View.
     *
     * @param projSelDTO
     * @param projections
     * @param tableHeaderDTO
     * @param fullHeaderDTO
     */
    private static void configurePivotHeaderForChannels(final ProjectionSelectionDTO projSelDTO, String projections, CustomTableHeaderDTO tableHeaderDTO, CustomTableHeaderDTO fullHeaderDTO) {
        for (int i = 0; i < NumericConstants.SIX; i++) {

            String commonColumn = StringUtils.EMPTY;
            String commonHeader = StringUtils.EMPTY;
            if (i == 0) {
                commonColumn = "gts";
                commonHeader = "Gross Trade Sales";
            } else if (i == 1) {
                commonColumn = "perOfBus";
                commonHeader = "% of Business";
            } else if (i == NumericConstants.TWO) {
                if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant()) || projSelDTO.getSalesOrUnit().equals(SALES.getConstant())) {
                    commonColumn = CON_SALES_WAC;
                    commonHeader = CONTRACT_SALES_WAC_AT;
                } else {
                    continue;
                }
            } else if (i == NumericConstants.THREE) {
                if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant()) || projSelDTO.getSalesOrUnit().equals(UNITS.getConstant())) {
                    commonColumn = UNIT_VOL1;
                    commonHeader = Constant.UNIT_VOLUME;
                } else {
                    continue;
                }
            } else if (i == NumericConstants.FOUR) {
                commonColumn = Constant.TOT_DIS_PER;
                commonHeader = CommonUtils.VAR_DIS_RATE;
            } else if (i == NumericConstants.FIVE) {
                commonColumn = Constant.TOTAL_DISCOUNT_DOLLAR;
                commonHeader = CommonUtils.VAR_DIS_AMOUNT;
            }

            boolean disc = true;
            while (disc) {
                List<Object> dmap = new ArrayList<>();
                if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                    Object singleColumn = commonColumn + ACTUALS.getConstant();
                    dmap.add(singleColumn);
                    if (i == 1 || i == NumericConstants.FOUR) {
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);
                    } else {
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                    }

                }
                Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                dmap.add(singleColumn);
                if (i == 1 || i == NumericConstants.FOUR) {
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
            }
        }
    }

}