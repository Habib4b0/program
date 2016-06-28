package com.stpl.app.galforecasting.discountprojectionresults.utils;

import com.stpl.app.galforecasting.discountprojectionresults.logic.DPRLogic;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author pvinoth
 */
public class HeaderUtils {

    private static final Logger LOGGER = Logger.getLogger(HeaderUtils.class);

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

    public static CustomTableHeaderDTO getNMDiscountProjectionResultsLeftTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object doubleCol = Constant.GROUP;
        Object[] singleCol = {Constant.GROUP};
        tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        fullHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        fullHeaderDTO.addDoubleColumn(doubleCol, " ");
        fullHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getDiscountProjectionResultsRightTableColumn(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getCalculatedDiscountProjectionResultsColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }

    /**
     * This method is to create headers for discount projection results screen
     * in commercial forecasting
     *
     * @param tableHeaderDTO
     * @param projSelDTO
     * @param fullHeaderDTO
     * @return CustomTableHeaderDTO
     */
    public static CustomTableHeaderDTO getCalculatedDiscountProjectionResultsColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        List<String> discountNames = projSelDTO.getDiscountNameList();
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
        int projectionOrder = 1;
//        if (projOrder.contains("Ascending")) {
//            projectionOrder = 1;
//        } else {
//            projectionOrder = 2;
//        }
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

        if (pivotView.contains(Constant.DISCOUNT_SMALL)) {
            DPRLogic dprLogic = new DPRLogic();
            List<Object> pcNameList = dprLogic.getProgramCodeName(projSelDTO);

            for (int i = 0; i < 3; i++) {
                String commonColumn = StringUtils.EMPTY;
                String commonHeader = StringUtils.EMPTY;
                if (i == 0) {
                    commonColumn = Constant.TOTALDISCOUNT;
                    commonHeader = "Total Discount";
                } else if (i == 1) {
                    commonColumn = "MandatedDiscount";
                    commonHeader = Constant.MANDATED_DISCOUNT;
                } else if (i == 2) {
                    commonColumn = "SupplementalDiscount";
                    commonHeader = "Supplemental Discount";
                }
                commonColumn = commonHeader.replace(" ", StringUtils.EMPTY);
                List<Object> dmap = new ArrayList<Object>();
                if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("actuals") || projections.contains(ACTUALS.getConstant())) {
                    Object singleColumn1 = commonColumn + "ActualsRate";
                    dmap.add(singleColumn1);
                    tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                    Object singleColumn2 = commonColumn + "ActualsAmount";
                    dmap.add(singleColumn2);
                    tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT, String.class);

                    Object singleColumn3 = commonColumn + "ActualsRPU";
                    dmap.add(singleColumn3);
                    tableHeaderDTO.addSingleColumn(singleColumn3, ACTUAL_RPU.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + ACTUAL_RPU, String.class);
                }
                if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("projections") || projections.contains(PROJECTIONS.getConstant())) {
                    Object singleColumn1 = commonColumn + "ProjectionsRate";
                    dmap.add(singleColumn1);
                    tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                    Object singleColumn2 = commonColumn + "ProjectionsAmount";
                    dmap.add(singleColumn2);
                    tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                    Object singleColumn3 = commonColumn + "ProjectionsRPU";
                    dmap.add(singleColumn3);
                    tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU, String.class);
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
                if (i == 1 || i == 2) {
                    getPCHeaders(pcNameList, projections, tableHeaderDTO, fullHeaderDTO, i == 1 ? "m" : Constant.S_SMALL);
                }
            }

            int startPr = projSelDTO.getHistoryStartPeriod();
            int lastPr = frequencyDivision;
            List<String> periodList = new ArrayList<String>();
            Map<String, String> periodListMap = new HashMap<String, String>();
            for (int yr = projSelDTO.getHistoryStartYear(); yr <= projSelDTO.getForecastDTO().getForecastEndYear(); yr++) {
                if (yr == projSelDTO.getForecastDTO().getForecastEndYear()) {
                    lastPr = endPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, false);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    periodList.add(commonColumn);
                    periodListMap.put(commonColumn, commonHeader);
                }
                startPr = 1;
            }

            projSelDTO.setPeriodList(periodList);
            projSelDTO.setPeriodListMap(periodListMap);

        } else {

            List<String> projectionColumnList = new ArrayList<String>();
            List<String> projectionHeaderList = new ArrayList<String>();
            int startPr = projSelDTO.getProjectionStartPeriod() == 0 ? 1 : projSelDTO.getProjectionStartPeriod();
            int lastPr = frequencyDivision;
            for (int yr = projSelDTO.getProjectionStartYear(); yr <= projSelDTO.getForecastDTO().getProjectionEndYear(); yr++) {
                if (!ANNUALLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
                    List<String> common = getCommonColumnHeader(1, yr, 0, false);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    projectionColumnList.add(commonColumn);
                    projectionHeaderList.add(commonHeader);
                }
                if (yr == projSelDTO.getForecastDTO().getProjectionEndYear()) {
                    lastPr = projSelDTO.getProjectionEndPeriod();
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, false);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    projectionColumnList.add(commonColumn);
                    projectionHeaderList.add(commonHeader);
                }
                startPr = 1;

            }
            if (projectionOrder == 1) {
                startPr = projSelDTO.getHistoryStartPeriod();
                lastPr = frequencyDivision;
                for (int yr = projSelDTO.getHistoryStartYear(); yr <= projSelDTO.getHistoryEndYear(); yr++) {
                    if (!ANNUALLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
                        List<Object> dmap = new ArrayList<Object>();
                        List<String> common = getCommonColumnHeader(1, yr, 0, false);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                        if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {

                            Object singleColumn1 = commonColumn + "ActualsRate";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + "ActualsAmount";
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);

                            Object singleColumn3 = commonColumn + "ActualsRPU";
                            dmap.add(singleColumn3);
                            tableHeaderDTO.addSingleColumn(singleColumn3, ACTUAL_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + ACTUAL_RPU.getConstant(), String.class);

                        }
                        boolean toAdd = false;

                        if (projectionColumnList.contains(commonColumn)) {
                            projectionColumnList.remove(commonColumn);
                            projectionHeaderList.remove(commonHeader);
                            toAdd = true;
                        } else if (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTIONS.getConstant())) {
                            toAdd = true;
                        }
                        if (toAdd) {

                            Object singleColumn1 = commonColumn + "ProjectionsRate";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + "ProjectionsAmount";
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                            Object singleColumn3 = commonColumn + "ProjectionsRPU";
                            dmap.add(singleColumn3);
                            tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                        }
                        if (!dmap.isEmpty()) {
                            tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        }
                    }
                    if (yr == projSelDTO.getHistoryEndYear()) {
                        lastPr = projSelDTO.getHistoryEndPeriod();
                    }
                    if (frequencyDivision == 1) {
                        startPr = yr;
                        lastPr = yr;
                    }
                    for (int pr = startPr; pr <= lastPr; pr++) {
                        List<Object> dmap = new ArrayList<Object>();
                        List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, false);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                        if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                            Object singleColumn1 = commonColumn + "ActualsRate";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + "ActualsAmount";
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);

                            Object singleColumn3 = commonColumn + "ActualsRPU";
                            dmap.add(singleColumn3);
                            tableHeaderDTO.addSingleColumn(singleColumn3, ACTUAL_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + ACTUAL_RPU.getConstant(), String.class);

                        }
                        boolean toAdd = false;

                        if (projectionColumnList.contains(commonColumn)) {
                            projectionColumnList.remove(commonColumn);
                            projectionHeaderList.remove(commonHeader);
                            toAdd = true;
                        } else if (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTIONS.getConstant())) {
                            toAdd = true;
                        }
                        if (toAdd) {
                            Object singleColumn1 = commonColumn + "ProjectionsRate";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + "ProjectionsAmount";
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                            Object singleColumn3 = commonColumn + "ProjectionsRPU";
                            dmap.add(singleColumn3);
                            tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                        }
                        if (!dmap.isEmpty()) {
                            tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        }
                    }
                    startPr = 1;
                }

                for (int i = 0; i < projectionColumnList.size(); i++) {
                    String commonColumn = projectionColumnList.get(i);
                    String commonHeader = projectionHeaderList.get(i);
                    List<Object> dmap = new ArrayList<Object>();

                    Object singleColumn1 = commonColumn + "ProjectionsRate";
                    dmap.add(singleColumn1);
                    tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                    Object singleColumn2 = commonColumn + "ProjectionsAmount";
                    dmap.add(singleColumn2);
                    tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                    Object singleColumn3 = commonColumn + "ProjectionsRPU";
                    dmap.add(singleColumn3);
                    tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                    if (!dmap.isEmpty()) {
                        tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    }
                }
                startPr = projSelDTO.getForecastStartPeriod() == 0 ? 1 : projSelDTO.getForecastStartPeriod();
                lastPr = frequencyDivision;
                for (int yr = projSelDTO.getForecastDTO().getForecastStartYear(); yr <= projSelDTO.getForecastDTO().getForecastEndYear(); yr++) {
                    //commented for issue no GAL - 2150
//                    if (!ANNUALLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
//                        List<String> common = getCommonColumnHeader(1, yr, 0, false);
//                        String commonColumn = common.get(0);
//                        String commonHeader = common.get(1);
//
//                        if (!projectionColumnList.contains(commonColumn)) {
//                            List<Object> dmap = new ArrayList<Object>();
//                            Object singleColumn1 = commonColumn + "ProjectionsRate";
//                            dmap.add(singleColumn1);
//                            tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
//                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);
//
//                            Object singleColumn2 = commonColumn + "ProjectionsAmount";
//                            dmap.add(singleColumn2);
//                            tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
//                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);
//
//                            Object singleColumn3 = commonColumn + "ProjectionsRPU";
//                            dmap.add(singleColumn3);
//                            tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
//                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);
//
//                            if (!dmap.isEmpty()) {
//                                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
//                                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
//                                tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
//                                fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
//                                fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
//                            }
//                        }
//                    }
                    if (yr == projSelDTO.getForecastDTO().getForecastEndYear()) {
                        lastPr = projSelDTO.getForecastEndPeriod();
                    }
                    if (frequencyDivision == 1) {
                        startPr = yr;
                        lastPr = yr;
                    }
                    for (int pr = startPr; pr <= lastPr; pr++) {

                        List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, false);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);

                        if (!projectionColumnList.contains(commonColumn)) {
                            List<Object> dmap = new ArrayList<Object>();
                            Object singleColumn1 = commonColumn + "ProjectionsRate";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + "ProjectionsAmount";
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                            Object singleColumn3 = commonColumn + "ProjectionsRPU";
                            dmap.add(singleColumn3);
                            tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                            if (!dmap.isEmpty()) {
                                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                                tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                                fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                                fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            }
                        }

                    }
                    startPr = 1;
                }
            } else {
                Collections.reverse(projectionColumnList);
                Collections.reverse(projectionHeaderList);
                startPr = projSelDTO.getForecastEndPeriod();
                lastPr = 1;
                for (int yr = projSelDTO.getForecastDTO().getForecastEndYear(); yr >= projSelDTO.getForecastDTO().getForecastStartYear(); yr--) {
                    if (!ANNUALLY.getConstant().equals(projSelDTO.getFrequency())) {
                        List<String> common = getCommonColumnHeader(1, yr, 0, false);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                        List<Object> dmap = new ArrayList<Object>();

                        Object singleColumn1 = commonColumn + "ProjectionsRate";
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                        Object singleColumn2 = commonColumn + "ProjectionsAmount";
                        dmap.add(singleColumn2);
                        tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                        Object singleColumn3 = commonColumn + "ProjectionsRPU";
                        dmap.add(singleColumn3);
                        tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                        if (!dmap.isEmpty()) {
                            tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        }
                        projectionColumnList.remove(commonColumn);
                        projectionHeaderList.remove(commonHeader);
                    }
                    if (yr == projSelDTO.getForecastDTO().getForecastStartYear()) {
                        lastPr = projSelDTO.getForecastStartPeriod();
                    }
                    if (frequencyDivision == 1) {
                        startPr = yr;
                        lastPr = yr;
                    }
                    for (int pr = startPr; pr >= lastPr; pr--) {

                        List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, false);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                        List<Object> dmap = new ArrayList<Object>();
                        Object singleColumn1 = commonColumn + "ProjectionsRate";
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                        Object singleColumn2 = commonColumn + "ProjectionsAmount";
                        dmap.add(singleColumn2);
                        tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                        Object singleColumn3 = commonColumn + "ProjectionsRPU";
                        dmap.add(singleColumn3);
                        tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                        if (!dmap.isEmpty()) {
                            tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        }
                        projectionColumnList.remove(commonColumn);
                        projectionHeaderList.remove(commonHeader);
                    }
                    startPr = frequencyDivision;
                }
                List<String> common1 = getCommonColumnHeader(frequencyDivision, projSelDTO.getForecastDTO().getHistoryEndYear(), projSelDTO.getHistoryEndPeriod(), false);
                String commonColumn1 = common1.get(0);
                for (int i = 0; i < projectionColumnList.indexOf(commonColumn1); i++) {
                    String commonColumn = projectionColumnList.get(i);
                    String commonHeader = projectionHeaderList.get(i);
                    List<Object> dmap = new ArrayList<Object>();
                    Object singleColumn1 = commonColumn + "ProjectionsRate";
                    dmap.add(singleColumn1);
                    tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                    Object singleColumn2 = commonColumn + "ProjectionsAmount";
                    dmap.add(singleColumn2);
                    tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                    Object singleColumn3 = commonColumn + "ProjectionsRPU";
                    dmap.add(singleColumn3);
                    tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                    if (!dmap.isEmpty()) {
                        tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    }
                }

                startPr = projSelDTO.getHistoryEndPeriod() == 0 ? 1 : projSelDTO.getHistoryEndPeriod();
                lastPr = 1;
                for (int yr = projSelDTO.getForecastDTO().getHistoryEndYear(); yr >= projSelDTO.getHistoryStartYear(); yr--) {
                    if (!ANNUALLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
                        List<String> common = getCommonColumnHeader(1, yr, 0, false);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                        List<Object> dmap = new ArrayList<Object>();
                        if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                            Object singleColumn1 = commonColumn + "ActualsRate";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + "ActualsAmount";
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);

                            Object singleColumn3 = commonColumn + "ActualsRPU";
                            dmap.add(singleColumn3);
                            tableHeaderDTO.addSingleColumn(singleColumn3, ACTUAL_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + ACTUAL_RPU.getConstant(), String.class);
                        }

                        boolean toAdd = false;
                        if (projectionColumnList.contains(commonColumn)) {
                            projectionColumnList.remove(commonColumn);
                            projectionHeaderList.remove(commonHeader);
                            toAdd = true;
                        } else if (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTIONS.getConstant())) {
                            toAdd = true;
                        }
                        if (toAdd) {
                            Object singleColumn1 = commonColumn + "ProjectionsRate";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + "ProjectionsAmount";
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                            Object singleColumn3 = commonColumn + "ProjectionsRPU";
                            dmap.add(singleColumn3);
                            tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                        }
                        if (!dmap.isEmpty()) {
                            tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        }
                    }
                    if (yr == projSelDTO.getHistoryStartYear()) {
                        lastPr = projSelDTO.getHistoryStartPeriod();
                    }
                    if (frequencyDivision == 1) {
                        startPr = yr;
                        lastPr = yr;
                    }
                    for (int pr = startPr; pr >= lastPr; pr--) {

                        List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, false);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                        List<Object> dmap = new ArrayList<Object>();
                        if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                            Object singleColumn1 = commonColumn + "ActualsRate";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + "ActualsAmount";
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);

                            Object singleColumn3 = commonColumn + "ActualsRPU";
                            dmap.add(singleColumn3);
                            tableHeaderDTO.addSingleColumn(singleColumn3, ACTUAL_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + ACTUAL_RPU.getConstant(), String.class);
                        }

                        boolean toAdd = false;

                        if (projectionColumnList.contains(commonColumn)) {
                            projectionColumnList.remove(commonColumn);
                            projectionHeaderList.remove(commonHeader);
                            toAdd = true;
                        } else if (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTIONS.getConstant())) {
                            toAdd = true;
                        }
                        if (toAdd) {

                            Object singleColumn1 = commonColumn + "ProjectionsRate";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + "ProjectionsAmount";
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                            Object singleColumn3 = commonColumn + "ProjectionsRPU";
                            dmap.add(singleColumn3);
                            tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                        }
                        if (!dmap.isEmpty()) {
                            tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        }
                    }
                    startPr = frequencyDivision;
                }
            }

        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    public static List<String> getCommonColumnHeader(int frequencyDivision, int year, int period, boolean isNMDPR) {
        List<String> common = new ArrayList<String>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == 4) {
            if (isNMDPR) {
                commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
                commonHeader = period + Constant.Q + " " + year;
            } else {
                commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
                commonHeader = Constant.Q + period + " " + year;
            }
        } else if (frequencyDivision == 2) {
            if (isNMDPR) {
                commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
                commonHeader = period + Constant.S + " " + year;
            } else {
                commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
                commonHeader = Constant.S + period + " " + year;
            }
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
        if (num == 12 || num == -1 ) {
            num = 0;
        }
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    private static void getPCHeaders(List<Object> pcNameList, String projections, CustomTableHeaderDTO tableHeaderDTO, CustomTableHeaderDTO fullHeaderDTO, String mandOrSupp) {
        if (pcNameList != null && !pcNameList.isEmpty()) {
            for (int i = 0; i < pcNameList.size(); i++) {
                String commonColumn = StringUtils.EMPTY;
                String commonHeader = StringUtils.EMPTY;
                String pcName = String.valueOf(pcNameList.get(i));

                commonColumn = StringUtils.isNotBlank(pcName) ? pcName : StringUtils.EMPTY;
                commonHeader = commonColumn;
                commonColumn = commonHeader + mandOrSupp;
                List<Object> dmap = new ArrayList<Object>();
                if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("actuals") || projections.contains(ACTUALS.getConstant())) {
                    Object singleColumn1 = commonColumn + "ActualsRate";
                    dmap.add(singleColumn1);
                    tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                    Object singleColumn2 = commonColumn + "ActualsAmount";
                    dmap.add(singleColumn2);
                    tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);

                    Object singleColumn3 = commonColumn + "ActualsRPU";
                    dmap.add(singleColumn3);
                    tableHeaderDTO.addSingleColumn(singleColumn3, ACTUAL_RPU.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + ACTUAL_RPU.getConstant(), String.class);
                }
                if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("projections") || projections.contains(PROJECTIONS.getConstant())) {
                    Object singleColumn1 = commonColumn + "ProjectionsRate";
                    dmap.add(singleColumn1);
                    tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                    Object singleColumn2 = commonColumn + "ProjectionsAmount";
                    dmap.add(singleColumn2);
                    tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                    Object singleColumn3 = commonColumn + "ProjectionsRPU";
                    dmap.add(singleColumn3);
                    tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);
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
        return getNMCalculatedDPRColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }

    public static CustomTableHeaderDTO getNMCalculatedDPRColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        return getNMCalculatedDiscountProjectionResultsColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }

    public static CustomTableHeaderDTO getNMCalculatedDiscountProjectionResultsColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        List<String> discountNames = projSelDTO.getDiscountNameList();
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
        prepareCommonColumnHeaders(projSelDTO, true);
        if (pivotView.contains(DISCOUNT.getConstant())) {
            if (!discountNames.isEmpty()) {
                for (int i = 0; i < discountNames.size(); i++) {
                    String commonColumn = StringUtils.EMPTY;
                    String commonHeader = discountNames.get(i);
                    commonColumn = commonHeader.replace(" ", StringUtils.EMPTY);
                    List<Object> dmap = new ArrayList<Object>();
                    if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("actuals") || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn;
                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                            singleColumn = commonColumn + ACTUALRATE.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);
                        }
                        if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                            singleColumn = commonColumn + ACTUALRPU.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_RPU.getConstant(), String.class);
                        }
                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                            singleColumn = commonColumn + ACTUALAMOUNT.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);
                        }
                    }
                    if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("projections") || projections.contains(PROJECTIONS.getConstant())) {
                        Object singleColumn;
                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                            singleColumn = commonColumn + PROJECTEDRATE.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);
                        }
                        if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                            singleColumn = commonColumn + PROJECTEDRPU.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);
                        }
                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                            singleColumn = commonColumn + PROJECTEDAMOUNT.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);
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
            if (projectionOrder == 2) {
                Collections.reverse(projSelDTO.getPeriodList());
            }
        } else {

            List<String> periodList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            for (int i = 0; i < periodList.size(); i++) {
                List<Object> dmap = new ArrayList<Object>();
                String commonColumn = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn);
                boolean historyFlag = false;
                boolean projectionCol = false;
                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    historyFlag = true;

                    if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn;
                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                            singleColumn = commonColumn + ACTUALRATE.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);
                        }
                        if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                            singleColumn = commonColumn + ACTUALRPU.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_RPU.getConstant(), String.class);
                        }
                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                            singleColumn = commonColumn + ACTUALAMOUNT.getConstant();
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);
                        }

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
                    Object singleColumn;
                    if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                        singleColumn = commonColumn + PROJECTEDRATE.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);
                    }
                    if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                        singleColumn = commonColumn + PROJECTEDRPU.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_RPU.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);
                    }
                    if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                        singleColumn = commonColumn + PROJECTEDAMOUNT.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_AMOUNT.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);
                    }
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
        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    public static void prepareCommonColumnHeaders(ProjectionSelectionDTO projSelDTO, boolean flag) {

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
            List<String> projectionColumnList = new ArrayList<String>();
            List<String> projectionHeaderList = new ArrayList<String>();
             startPr = projSelDTO.getProjectionStartPeriod() == 0 ? 1 : projSelDTO.getProjectionStartPeriod();
//            startPr = projSelDTO.getProjectionStartPeriod();

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
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, flag);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    projectionColumnList.add(commonColumn);
                    projectionHeaderList.add(commonHeader);
                }
                startPr = 1;
            }

            startPr = projSelDTO.getHistoryStartPeriod();
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
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, flag);
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

                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, flag);
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
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static CustomTableHeaderDTO getDiscountProjectionResultsLeftTableColumn(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
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
        List<String> discountNames = projSelDTO.getDiscountNameList();
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

        if (pivotView.contains(Constant.DISCOUNT_SMALL)) {
            String commonColumn = StringUtils.EMPTY;
            String commonHeader = "Total Discount";
            commonColumn = commonHeader.replace(" ", StringUtils.EMPTY);
            List<Object> dmap = new ArrayList<Object>();
            if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("actuals") || projections.contains(Constant.ACTUALS)) {
                Object singleColumn1 = commonColumn + "ActualsRate";
                dmap.add(singleColumn1);
                tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                Object singleColumn2 = commonColumn + "ActualsAmount";
                dmap.add(singleColumn2);
                tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);

                Object singleColumn3 = commonColumn + "ActualsRPU";
                dmap.add(singleColumn3);
                tableHeaderDTO.addSingleColumn(singleColumn3, ACTUAL_RPU.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + ACTUAL_RPU.getConstant(), String.class);
            }
            if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("projections") || projections.contains(Constant.PROJECTIONS)) {

                Object singleColumn3 = commonColumn + Constant.ProjectedRPU;
                dmap.add(singleColumn3);
                tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                Object singleColumn1 = commonColumn + "ProjectionsRate";
                dmap.add(singleColumn1);
                tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                Object singleColumn2 = commonColumn + "ProjectionsAmount";
                dmap.add(singleColumn2);
                tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);
            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }

            int startPr = startPeriod;
            int lastPr = frequencyDivision;
            List<String> periodList = new ArrayList<String>();
            Map<String, String> periodListMap = new HashMap<String, String>();
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

            List<String> projectionColumnList = new ArrayList<String>();
            List<String> projectionHeaderList = new ArrayList<String>();
            int startPr = projSelDTO.getProjectionStartPeriod();
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
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, false);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    projectionColumnList.add(commonColumn);
                    projectionHeaderList.add(commonHeader);
                }
                startPr = 1;

            }
            if (projectionOrder == 1) {
                startPr = projSelDTO.getHistoryStartPeriod();
                lastPr = frequencyDivision;
                for (int yr = projSelDTO.getHistoryStartYear(); yr <= projSelDTO.getHistoryEndYear(); yr++) {
                    if (yr == projSelDTO.getHistoryEndYear()) {
                        lastPr = projSelDTO.getHistoryEndPeriod();
                    }
                    if (frequencyDivision == 1) {
                        startPr = yr;
                        lastPr = yr;
                    }
                    for (int pr = startPr; pr <= lastPr; pr++) {
                        List<Object> dmap = new ArrayList<Object>();
                        List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, false);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                        if (projections.contains(Constant.BOTH) || projections.contains(Constant.ACTUALS)) {

                            Object singleColumn1 = commonColumn + "ActualsRate";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + "ActualsAmount";
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);

                            Object singleColumn3 = commonColumn + "ActualsRPU";
                            dmap.add(singleColumn3);
                            tableHeaderDTO.addSingleColumn(singleColumn3, ACTUAL_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + ACTUAL_RPU.getConstant(), String.class);

                        }
                        boolean toAdd = false;

                        if (projectionColumnList.contains(commonColumn)) {
                            projectionColumnList.remove(commonColumn);
                            projectionHeaderList.remove(commonHeader);
                            toAdd = true;
                        } else if (projections.contains(Constant.BOTH) || projections.contains(Constant.PROJECTIONS)) {
                            toAdd = true;
                        }
                        if (toAdd) {

                            Object singleColumn3 = commonColumn + Constant.ProjectedRPU;
                            dmap.add(singleColumn3);
                            tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                            Object singleColumn1 = commonColumn + "ProjectionsRate";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + "ProjectionsAmount";
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                        }
                        if (!dmap.isEmpty()) {
                            tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        }
                    }
                    startPr = 1;
                }

                for (int i = 0; i < projectionColumnList.size(); i++) {
                    String commonColumn = projectionColumnList.get(i);
                    String commonHeader = projectionHeaderList.get(i);
                    List<Object> dmap = new ArrayList<Object>();
                    Object singleColumn3 = commonColumn + Constant.ProjectedRPU;
                    dmap.add(singleColumn3);
                    tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                    Object singleColumn1 = commonColumn + "ProjectionsRate";
                    dmap.add(singleColumn1);
                    tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                    Object singleColumn2 = commonColumn + "ProjectionsAmount";
                    dmap.add(singleColumn2);
                    tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                    if (!dmap.isEmpty()) {
                        tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
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

                        List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, false);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);

                        if (!projectionColumnList.contains(commonColumn)) {
                            List<Object> dmap = new ArrayList<Object>();
                            Object singleColumn3 = commonColumn + Constant.ProjectedRPU;
                            dmap.add(singleColumn3);
                            tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                            Object singleColumn1 = commonColumn + "ProjectionsRate";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + "ProjectionsAmount";
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                            if (!dmap.isEmpty()) {
                                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                                tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                                fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                                fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            }
                        }

                    }
                    startPr = 1;
                }
            } else {
                Collections.reverse(projectionColumnList);
                Collections.reverse(projectionHeaderList);
                startPr = projSelDTO.getForecastEndPeriod();
                lastPr = 1;
                for (int yr = projSelDTO.getForecastDTO().getForecastEndYear(); yr >= projSelDTO.getForecastDTO().getForecastStartYear(); yr--) {
                    if (yr == projSelDTO.getForecastDTO().getForecastStartYear()) {
                        lastPr = projSelDTO.getForecastStartPeriod();
                    }
                    if (frequencyDivision == 1) {
                        startPr = yr;
                        lastPr = yr;
                    }
                    for (int pr = startPr; pr >= lastPr; pr--) {

                        List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, false);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                        List<Object> dmap = new ArrayList<Object>();

                        Object singleColumn3 = commonColumn + Constant.ProjectedRPU;
                        dmap.add(singleColumn3);
                        tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                        Object singleColumn1 = commonColumn + "ProjectionsRate";
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                        Object singleColumn2 = commonColumn + "ProjectionsAmount";
                        dmap.add(singleColumn2);
                        tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                        if (!dmap.isEmpty()) {
                            tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        }
                        projectionColumnList.remove(commonColumn);
                        projectionHeaderList.remove(commonHeader);
                    }
                    startPr = frequencyDivision;
                }
                List<String> common1 = getCommonColumnHeader(frequencyDivision, projSelDTO.getForecastDTO().getHistoryEndYear(), projSelDTO.getHistoryEndPeriod(), false);
                String commonColumn1 = common1.get(0);
                for (int i = 0; i < projectionColumnList.indexOf(commonColumn1); i++) {
                    String commonColumn = projectionColumnList.get(i);
                    String commonHeader = projectionHeaderList.get(i);
                    List<Object> dmap = new ArrayList<Object>();
                    Object singleColumn3 = commonColumn + Constant.ProjectedRPU;
                    dmap.add(singleColumn3);
                    tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                    Object singleColumn1 = commonColumn + "ProjectionsRate";
                    dmap.add(singleColumn1);
                    tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                    Object singleColumn2 = commonColumn + "ProjectionsAmount";
                    dmap.add(singleColumn2);
                    tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                    if (!dmap.isEmpty()) {
                        tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    }
                }

                startPr = projSelDTO.getHistoryEndPeriod();
                lastPr = 1;
                for (int yr = projSelDTO.getForecastDTO().getHistoryEndYear(); yr >= projSelDTO.getHistoryStartYear(); yr--) {
                    if (yr == projSelDTO.getHistoryStartYear()) {
                        lastPr = projSelDTO.getHistoryStartPeriod();
                    }
                    if (frequencyDivision == 1) {
                        startPr = yr;
                        lastPr = yr;
                    }
                    for (int pr = startPr; pr >= lastPr; pr--) {

                        List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr, false);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                        List<Object> dmap = new ArrayList<Object>();
                        if (projections.contains(Constant.BOTH) || projections.contains(Constant.ACTUALS)) {
                            Object singleColumn1 = commonColumn + "ActualsRate";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + "ActualsAmount";
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);

                            Object singleColumn3 = commonColumn + "ActualsRPU";
                            dmap.add(singleColumn3);
                            tableHeaderDTO.addSingleColumn(singleColumn3, ACTUAL_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + ACTUAL_RPU.getConstant(), String.class);

                        }

                        boolean toAdd = false;

                        if (projectionColumnList.contains(commonColumn)) {
                            projectionColumnList.remove(commonColumn);
                            projectionHeaderList.remove(commonHeader);
                            toAdd = true;
                        } else if (projections.contains(Constant.BOTH) || projections.contains(Constant.PROJECTIONS)) {
                            toAdd = true;
                        }
                        if (toAdd) {

                            Object singleColumn3 = commonColumn + Constant.ProjectedRPU;
                            dmap.add(singleColumn3);
                            tableHeaderDTO.addSingleColumn(singleColumn3, PROJECTED_RPU.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn3, commonHeader + " " + PROJECTED_RPU.getConstant(), String.class);

                            Object singleColumn1 = commonColumn + "ProjectionsRate";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + "ProjectionsAmount";
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);

                        }
                        if (!dmap.isEmpty()) {
                            tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        }
                    }
                    startPr = frequencyDivision;
                }
            }

        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

}
