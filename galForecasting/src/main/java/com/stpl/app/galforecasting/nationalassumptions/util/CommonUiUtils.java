/*
 * 
 */
package com.stpl.app.galforecasting.nationalassumptions.util;

import com.stpl.app.galforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.FrequencyConstants.*;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.HeaderConstants.*;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.*;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * The Class CommonUiUtils.
 */
public class CommonUiUtils {

    private static final Logger LOGGER = Logger.getLogger(CommonUiUtils.class);
    /**
     * The Constant ATTACHMENT_COLUMNS.
     */
    public static final Object[] ATTACHMENT_COLUMNS = new Object[]{DOCUMENT_NAME.getConstant(), DATE_ADDED.getConstant(), USER_NAME.getConstant()};
    /**
     * The Constant ATTACHMENT_HEADER.
     */
    public static final String[] ATTACHMENT_HEADER = new String[]{"Document Name", "Date Added", "User Name"};

    /**
     * The Constant VISIBLECOLUMN.
     */
    public static final Object VISIBLECOLUMN[] = new Object[]{Constant.PRODUCT_NO, Constant.PRODUCT_NAME};

    /**
     * The Constant VISIBLEHEADER.
     */
    public static final String VISIBLEHEADER[] = new String[]{"Product No", "Product Name"};

    /**
     * The Constant VISIBLESEARCHCOLUMN.
     */
    public static final Object VISIBLESEARCHCOLUMN[] = new Object[]{Constant.PROJECTION_NAME, "company", "productGroup", "therapeuticClass", "createdDateSearch", "modifiedDateSearch", "createdBy"};

    /**
     * The Constant VISIBLESEARCHHEADER.
     */
    public static final String VISIBLESEARCHHEADER[] = new String[]{"Name", Constant.COMPANY_SMALL, "Product Group", "Therapeutic Class", "Created Date", "Modified Date", "Created By"};

    /**
     * The Constant PRODUCTGROUPCOLUMN.
     */
    public static final Object PRODUCTGROUPCOLUMN[] = new Object[]{"productGroupName", "productGroup", "productGroupDescription", "company"};

    /**
     * The PRODUCTGROUPHEADER.
     */
    public static String PRODUCTGROUPHEADER[] = new String[]{"Product Group Name", "Product Group No", "Product Group Description", Constant.COMPANY_SMALL};

    /**
     * The Constant BASELINE_PERIOD_COLUMNS.
     */
    public static final Object[] BASELINE_PERIOD_COLUMNS = new Object[]{Constant.CHECK, "period", "type"};

    /**
     * The Constant BASELINE_PERIOD_HEADER.
     */
    public static final String[] BASELINE_PERIOD_HEADER = new String[]{StringUtils.EMPTY, "Period", StringUtils.EMPTY};

    /**
     * The Constant PERIOD_TYPES_COLUMNS.
     */
    public static final Object[] PERIOD_TYPES_COLUMNS = new Object[]{"priceType", "baselineMethodology", "basePeriod", "forecastMethodology", "priceBasis", "growthRate", "rollingPeriod", "startPeriod",
        "endPeriod", "symbol"};

    /**
     * The Constant PERIOD_TYPES_HEADER.
     */
    public static final String[] PERIOD_TYPES_HEADER = new String[]{"Price Type", "Baseline Methodology", "Actuals Period", "Forecast Methodology", "Price Basis", "Growth Rate", "Actuals Period", "Start Period",
        "End Period", StringUtils.EMPTY};

    /**
     * The Constant CPI_COLUMNS.
     */
    public static final Object[] CPI_COLUMNS = new Object[]{Constant.PRODUCT_NO, Constant.PRODUCT_NAME, "productDescription"};

    /**
     * The Constant CPI_HEADER.
     */
    public static final String[] CPI_HEADER = new String[]{"Product#", "Product Name", "Product Description"};

    /**
     * The Constant NEW_NDC_COLUMNS.
     */
    public static final Object[] NEW_NDC_COLUMNS = new Object[]{"ndc9", "wac", "baseYearAMP", "baseYearCPI", "forecastAMP",
        "forecastBestPrice"};

    /**
     * The Constant NEW_NDC_HEADER.
     */
    public static final String[] NEW_NDC_HEADER = new String[]{"NDC Number", Constant.WAC, "Base Year AMP", "Base Year CPI", "Forecast AMP",
        "Forecast Best Price"};

    /**
     * The Constant NEW_NDC_COLUMNS.
     */
    public static final Object[] FEDERAL_NDC_COLUMNS = new Object[]{Constant.ITEM_NO, "wac", "nonFamp", "fssOGA"};

    /**
     * The Constant NEW_NDC_HEADER.
     */
    public static final String[] FEDERAL_NDC_HEADER = new String[]{"NDC Number", Constant.WAC, "Non-FAMP", "FSS(OGA)"};

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    public static CustomTableHeaderDTO getLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO) {

        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object doubleCol = Constant.GROUP;
        Object[] singleCol = {Constant.GROUP};
        tableHeaderDTO.addSingleColumn(singleCol[0], Constant.NDC, String.class);
        tableHeaderDTO.addDoubleColumn(doubleCol, " ");
        tableHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        fullHeaderDTO.addSingleColumn(singleCol[0], Constant.NDC, String.class);
        fullHeaderDTO.addDoubleColumn(doubleCol, " ");
        fullHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        return tableHeaderDTO;
    }

    static ProjectionSelectionDTO getHistoryAndProjectionDetails(ProjectionSelectionDTO projSelDTO) {
        String frequency = projSelDTO.getFrequency();
        Calendar ob = Calendar.getInstance();
        int curMonth = ob.get(Calendar.MONTH);
        int curYear = ob.get(Calendar.YEAR);
        int currentPeriod = 1;
        int frequencyDivision = 1;
        if (frequency.equals(QUARTERLY.getConstant())) {
            currentPeriod = (curMonth / 3);
            frequencyDivision = 4;
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            currentPeriod = (curMonth / 6);
            frequencyDivision = 2;
        } else if (frequency.equals(MONTHLY.getConstant())) {
            currentPeriod = curMonth;
            frequencyDivision = 12;
        } else if (frequency.equals(ANNUALLY.getConstant())) {
            currentPeriod = curYear;
            frequencyDivision = 1;
        }
        projSelDTO.setFrequencyDivision(frequencyDivision);
        projSelDTO.setCurrentYear(curYear);
        projSelDTO.setCurrentPeriod(currentPeriod);
        projSelDTO = getHistoryDetail(projSelDTO);
        projSelDTO = getProjectionDetail(projSelDTO);
        return projSelDTO;
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
        } else if (frequencyDivision == 2) {
            if (startFreq == 1) {
                projSelDTO.setStartMonth(1);
            } else if (startFreq == 2) {
                projSelDTO.setStartMonth(7);
            }
        } else if (frequencyDivision == 4) {
            if (startFreq == 1) {
                projSelDTO.setStartMonth(1);
            } else if (startFreq == 2) {
                projSelDTO.setStartMonth(4);
            } else if (startFreq == 3) {
                projSelDTO.setStartMonth(7);
            } else if (startFreq == 4) {
                projSelDTO.setStartMonth(10);
            }
        } else if (frequencyDivision == 12) {
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
        Calendar ob = Calendar.getInstance();
        ob.set(projSelDTO.getEndYear(), projSelDTO.getEndMonth() - 1, 1);
        int daysInMonth = ob.getActualMaximum(Calendar.DAY_OF_MONTH);
        projSelDTO.setEndDay(daysInMonth);
        return projSelDTO;
    }

    public static CustomTableHeaderDTO getRightTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getCalculatedColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }

    public static CustomTableHeaderDTO getCalculatedColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        LOGGER.info("inside getCalculatedColumns ");
        String projOrder = projSelDTO.getProjectionOrder();
        String projections = projSelDTO.getActualsOrProjections();
        String pivotView = projSelDTO.getPivotView() == null ? " " : projSelDTO.getPivotView();
        String frequency = projSelDTO.getFrequency();
        int endYear = projSelDTO.getEndYear();
        int endMonth = projSelDTO.getEndMonth();
        projSelDTO = getHistoryAndProjectionDetails(projSelDTO);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        int historyNum = projSelDTO.getHistoryNum();
        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endPeriod = getQuarter(endMonth);
        int projectionOrder = 0;
        int histProjYear = projSelDTO.getHistProjYear();
        int histProjMonth = projSelDTO.getHistProjMonth();
        int histProjPeriod = getQuarter(histProjMonth);

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

        if (pivotView.contains(PRICE_TYPE.getConstant())) {// pivot view

            List priceTypeColumns = projSelDTO.getPriceTypeList();
            int size = priceTypeColumns.size();
            for (int i = 0; i < size; i++) {
                String commonHeader = String.valueOf(priceTypeColumns.get(i));
                String commonColumn = String.valueOf(priceTypeColumns.get(i)).replace(" ", StringUtils.EMPTY).replace("-", StringUtils.EMPTY).toLowerCase();

                List<Object> dmap = new ArrayList<Object>();
                if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("actuals") || projections.contains(ACTUALS.getConstant())) {
                    Object singleColumn = commonColumn + ACTUALS.getConstant();
                    dmap.add(singleColumn);

                    tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);

                }
                Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                dmap.add(singleColumn);

                tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);

                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
            }

            List<String> periodList = new ArrayList<String>();
            List<String> pivotList = new ArrayList<String>();
            Map<String, String> periodListMap = new HashMap<String, String>();
            if (projectionOrder == 1) {
                int startPr = startPeriod;
                int lastPr = frequencyDivision;
                for (int yr = startYear; yr <= endYear; yr++) { // acending
                    if (yr == endYear) {
                        lastPr = endPeriod;
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
                        pivotList.add(commonHeader);
                        periodListMap.put(commonColumn, commonHeader);
                    }
                    startPr = 1;
                }
            } else { //   descending
                int startPr = endPeriod;
                int lastPr = 1;
                for (int yr = endYear; yr >= startYear; yr--) {
                    if (yr == startYear) {
                        lastPr = startPeriod;
                    }
                    if (frequencyDivision == 1) {
                        startPr = yr;
                        lastPr = yr;
                    }
                    for (int pr = startPr; pr >= lastPr; pr--) {
                        List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                        periodList.add(commonColumn);
                        pivotList.add(commonHeader);
                        periodListMap.put(commonColumn, commonHeader);
                    }
                    startPr = frequencyDivision;
                }
            }
            projSelDTO.setPivotList(pivotList);
            projSelDTO.setPeriodList(periodList);
            projSelDTO.setPeriodListMap(periodListMap);
        } else {  //  period view
            if (frequencyDivision != 1) {
                currentPeriod = currentPeriod + 1;
            }
            boolean hist = false;
            boolean proj = false;
            boolean projectionCol = false;
            if (projectionOrder == 1) {
                int startPr = startPeriod;
                int lastPr = frequencyDivision;
                hist = true;
                proj = false;
                for (int yr = startYear; yr <= endYear; yr++) {
                    if (yr == endYear) {
                        lastPr = endPeriod;
                    }
                    if (frequencyDivision == 1) {
                        startPr = yr;
                        lastPr = yr;
                    }
                    for (int pr = startPr; pr <= lastPr; pr++) {
                        if (pr == currentPeriod && yr == currentYear) {
                            hist = false;
                            proj = true;
                        }
                        List<Object> dmap = new ArrayList<Object>();
                        List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                        if (hist) {
                            if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("actuals") || projections.contains(ACTUALS.getConstant())) {
                                Object singleColumn = commonColumn + ACTUALS.getConstant();
                                dmap.add(singleColumn);
                                tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                                tableHeaderDTO.addSingleHistoryColumn(singleColumn, ACTUALS.getConstant());
                                fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                            }
                        }
                        if (frequencyDivision != 1) {
                            if (yr >= histProjYear && pr >= histProjPeriod) {
                                projectionCol = true;
                            }
                        }
                        if (yr > histProjYear) {
                            projectionCol = true;
                        }

                        if (hist || proj) {
                            if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("projections") || projections.contains(PROJECTIONS.getConstant()) || projectionCol) {
                                Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                                dmap.add(singleColumn);
                                tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                                tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTIONS.getConstant());
                                fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
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
                    startPr = 1;
                }
            } else {

                ///     descending
                boolean descend = false;
                int startPr = endPeriod;
                int lastPr = 1;
                proj = true;
                hist = false;
                projectionCol = true;
                int flagCount = 0;
                for (int yr = endYear; yr >= startYear; yr--) {
                    if (yr == startYear) {
                        lastPr = startPeriod;
                    }
                    if (frequencyDivision == 1) {
                        startPr = yr;
                        lastPr = yr;
                    }
                    for (int pr = startPr; pr >= lastPr; pr--) {
                        if (descend) {
                            hist = true;
                            proj = false;
                        }
                        if (pr == currentPeriod && yr == currentYear) {
                            descend = true;
                        }

                        List<Object> dmap = new ArrayList<Object>();
                        List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                        if (hist) {
                            if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("actuals") || projections.contains(ACTUALS.getConstant())) {
                                Object singleColumn = commonColumn + ACTUALS.getConstant();
                                dmap.add(singleColumn);
                                tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                                tableHeaderDTO.addSingleHistoryColumn(singleColumn, ACTUALS.getConstant());
                                fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                            }
                        }

                        if (frequencyDivision == 1 && yr == histProjYear) { // condition for Annually frequency
                            flagCount++;
                        }
                        if (flagCount > 0) {
                            projectionCol = false;
                        }
                        if (yr == histProjYear && pr == histProjPeriod) { // condition for Quarterly frequency
                            flagCount++;
                        }

                        if (hist || proj) {
                            if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("projections") || projections.contains(PROJECTIONS.getConstant()) || projectionCol) {
                                Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                                dmap.add(singleColumn);
                                tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                                tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTIONS.getConstant());
                                fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
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
                    startPr = frequencyDivision;
                }
            }
        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        LOGGER.info("getCalculatedColumns ends");
        return tableHeaderDTO;
    }

    // Worksheet header formation 
    public static CustomTableHeaderDTO getWorkSheetLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO) {

        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object doubleCol = Constant.GROUP;
        Object[] singleCol = {Constant.GROUP};
        tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        tableHeaderDTO.addDoubleColumn(doubleCol, " ");
        tableHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        fullHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        fullHeaderDTO.addDoubleColumn(doubleCol, " ");
        fullHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getWorkSheetRightTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getFcpCalculatedWorkSheetColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }

    public static CustomTableHeaderDTO getFcpCalculatedWorkSheetColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        String frequency = projSelDTO.getFrequency();
        int endYear = projSelDTO.getEndYear();
        int endMonth = projSelDTO.getEndMonth();
        projSelDTO = getHistoryAndProjectionDetails(projSelDTO);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        int historyNum = projSelDTO.getHistoryNum();
        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endPeriod = getQuarter(endMonth);
        int histProjYear = projSelDTO.getHistProjYear();
        int histProjMonth = projSelDTO.getHistProjMonth();
        int histProjPeriod = getQuarter(histProjMonth);
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

        if (frequencyDivision != 1) {
            currentPeriod = currentPeriod + 1;
        }
        boolean hist = false;
        boolean proj = false;
        if (projectionOrder == 1) {
            int startPr = startPeriod;
            int lastPr = frequencyDivision;
            hist = true;
            proj = false;
            List<Object> dmap = new ArrayList<Object>();

            for (int yr = startYear; yr <= endYear; yr++) {
                if (yr == endYear) {
                    lastPr = endPeriod;
                }

                for (int pr = startPr; pr <= lastPr; pr++) {
                    if (pr == currentPeriod && yr == currentYear) {
                        hist = false;
                        proj = true;
                    }

                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);

                    if (hist) {

                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, commonHeader, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, commonHeader);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                        if (pr == 3) {
                            int incrementedYear = (yr + 1);
                            Object singleReportColn = incrementedYear + "reportedFcp";
                            String singleReportedHeader = incrementedYear + " " + "Reported FCP";
                            dmap.add(singleReportColn);
                            tableHeaderDTO.addSingleColumn(singleReportColn, singleReportedHeader, String.class);
                            tableHeaderDTO.addSingleHistoryColumn(singleReportColn, singleReportedHeader);
                            fullHeaderDTO.addSingleColumn(singleReportColn, singleReportedHeader, String.class);
                        }
                    }
                }

                startPr = 1;
            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                tableHeaderDTO.addDoubleHeaderMap(ACTUALS.getConstant(), dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                fullHeaderDTO.addDoubleColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                fullHeaderDTO.addDoubleHeaderMap(ACTUALS.getConstant(), dmap.toArray());
            }

            /// Projection  
            startPr = histProjPeriod;
            lastPr = frequencyDivision;
            hist = true;
            proj = true;

            for (int yr = histProjYear; yr <= endYear; yr++) {
                if (yr == endYear) {
                    lastPr = endPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);

                    if (proj) {

                        Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, commonHeader, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, commonHeader);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + FORECAST.getConstant(), String.class);
                        if (pr == 3) {
                            int incrementedYear = (yr + 1);
                            Object singleForecastColn = incrementedYear + "forecastFcp";
                            String singleForecastHeader = incrementedYear + " " + "Forecast FCP";
                            dmap.add(singleForecastColn);
                            tableHeaderDTO.addSingleColumn(singleForecastColn, singleForecastHeader, String.class);
                            tableHeaderDTO.addSingleHistoryColumn(singleForecastColn, singleForecastHeader);
                            fullHeaderDTO.addSingleColumn(singleForecastColn, singleForecastHeader, String.class);
                        }
                    }

                }

                startPr = 1;
            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(PROJECTIONS.getConstant(), FORECAST.getConstant());
                tableHeaderDTO.addDoubleHeaderMap(PROJECTIONS.getConstant(), dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(PROJECTIONS.getConstant(), FORECAST.getConstant());
                fullHeaderDTO.addDoubleColumn(PROJECTIONS.getConstant(), FORECAST.getConstant());
                fullHeaderDTO.addDoubleHeaderMap(PROJECTIONS.getConstant(), dmap.toArray());
            }

        } else {
            int startPr = endPeriod;
            int lastPr = 1;
            proj = true;
            hist = false;
            List<Object> dmap = new ArrayList<Object>();

            boolean descend = false;

            for (int yr = endYear; yr >= startYear; yr--) {
                if (yr == startYear) {
                    lastPr = startPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr >= lastPr; pr--) {
                    if (descend) {
                        hist = true;
                        proj = false;
                    }
                    if (pr == currentPeriod && yr == currentYear) {
                        descend = true;
                    }
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);

                    if (hist) {

                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, commonHeader, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, commonHeader);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                        if (pr == 4) {
                            int incrementedYear = (yr + 1);
                            Object singleReportColn = incrementedYear + "reportedFcp";
                            String singleReportedHeader = incrementedYear + " " + "Reported FCP";
                            dmap.add(singleReportColn);
                            tableHeaderDTO.addSingleColumn(singleReportColn, singleReportedHeader, String.class);
                            tableHeaderDTO.addSingleHistoryColumn(singleReportColn, singleReportedHeader);
                            fullHeaderDTO.addSingleColumn(singleReportColn, singleReportedHeader, String.class);
                        }
                    }

                }
                startPr = frequencyDivision;
            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                tableHeaderDTO.addDoubleHeaderMap(ACTUALS.getConstant(), dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                fullHeaderDTO.addDoubleColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                fullHeaderDTO.addDoubleHeaderMap(ACTUALS.getConstant(), dmap.toArray());

            }

            /// Projection in descending
            startPr = endPeriod;
            lastPr = 1;
            proj = true;
            hist = false;

            for (int yr = endYear; yr >= histProjYear; yr--) {
                if (yr == histProjYear) {
                    lastPr = histProjPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr >= lastPr; pr--) {

                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);

                    if (proj) {

                        Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, commonHeader, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, commonHeader);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + FORECAST.getConstant(), String.class);
                        if (pr == 4) {
                            int incrementedYear = (yr + 1);
                            Object singleForecastColn = incrementedYear + "forecastFcp";
                            String singleForecastHeader = incrementedYear + " " + "Forecast FCP";
                            dmap.add(singleForecastColn);
                            tableHeaderDTO.addSingleColumn(singleForecastColn, singleForecastHeader, String.class);
                            tableHeaderDTO.addSingleHistoryColumn(singleForecastColn, singleForecastHeader);
                            fullHeaderDTO.addSingleColumn(singleForecastColn, singleForecastHeader, String.class);
                        }

                    }

                }
                startPr = frequencyDivision;
            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(PROJECTIONS.getConstant(), FORECAST.getConstant());
                tableHeaderDTO.addDoubleHeaderMap(PROJECTIONS.getConstant(), dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(PROJECTIONS.getConstant(), FORECAST.getConstant());
                fullHeaderDTO.addDoubleColumn(PROJECTIONS.getConstant(), FORECAST.getConstant());
                fullHeaderDTO.addDoubleHeaderMap(PROJECTIONS.getConstant(), dmap.toArray());
            }

        }

        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getMedicaidWorkSheetLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO) {

        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object doubleCol = Constant.GROUP;
        Object doubleColumn = "baseYear";
        Object[] singleCol = {Constant.GROUP, "baseYear"};
        tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        tableHeaderDTO.addDoubleColumn(doubleCol, " ");
        tableHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        fullHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        fullHeaderDTO.addDoubleColumn(doubleCol, " ");
        fullHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);

        tableHeaderDTO.addSingleColumn(singleCol[1], "Base Year", String.class);
        tableHeaderDTO.addDoubleColumn(doubleColumn, " ");
        tableHeaderDTO.addDoubleHeaderMap(doubleColumn, singleCol);
        fullHeaderDTO.addSingleColumn(singleCol[1], "Base Year", String.class);
        fullHeaderDTO.addDoubleColumn(doubleColumn, " ");
        fullHeaderDTO.addDoubleHeaderMap(doubleColumn, singleCol);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getMedicaidWorkSheetRightTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getMedicaidCalculatedWorkSheetColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }

    public static CustomTableHeaderDTO getMedicaidCalculatedWorkSheetColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        String frequency = projSelDTO.getFrequency();
        int endYear = projSelDTO.getEndYear();
        int endMonth = projSelDTO.getEndMonth();
        projSelDTO = getHistoryAndProjectionDetails(projSelDTO);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        int historyNum = projSelDTO.getHistoryNum();
        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endPeriod = getQuarter(endMonth);
        int histProjYear = projSelDTO.getHistProjYear();
        int histProjMonth = projSelDTO.getHistProjMonth();
        int histProjPeriod = getQuarter(histProjMonth);
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

        if (frequencyDivision != 1) {
            currentPeriod = currentPeriod + 1;
        }
        boolean hist = false;
        boolean proj = false;
        if (projectionOrder == 1) {
            int startPr = startPeriod;
            int lastPr = frequencyDivision;
            hist = true;
            proj = false;
            List<Object> dmap = new ArrayList<Object>();

            for (int yr = startYear; yr <= endYear; yr++) {
                if (yr == endYear) {
                    lastPr = endPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    if (pr == currentPeriod && yr == currentYear) {
                        hist = false;
                        proj = true;
                    }

                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    if (hist) {
                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, commonHeader, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, commonHeader);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);

                    }
                }

                startPr = 1;
            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                tableHeaderDTO.addDoubleHeaderMap(ACTUALS.getConstant(), dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                fullHeaderDTO.addDoubleColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                fullHeaderDTO.addDoubleHeaderMap(ACTUALS.getConstant(), dmap.toArray());
            }

            /// Projection  
            startPr = histProjPeriod;
            lastPr = frequencyDivision;
            hist = true;
            proj = true;

            for (int yr = histProjYear; yr <= endYear; yr++) {
                if (yr == endYear) {
                    lastPr = endPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);

                    if (proj) {
                        Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, commonHeader, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, commonHeader);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + FORECAST.getConstant(), String.class);
                    }

                }

                startPr = 1;
            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(PROJECTIONS.getConstant(), FORECAST.getConstant());
                tableHeaderDTO.addDoubleHeaderMap(PROJECTIONS.getConstant(), dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(PROJECTIONS.getConstant(), FORECAST.getConstant());
                fullHeaderDTO.addDoubleColumn(PROJECTIONS.getConstant(), FORECAST.getConstant());
                fullHeaderDTO.addDoubleHeaderMap(PROJECTIONS.getConstant(), dmap.toArray());
            }

        } else {
            int startPr = endPeriod;
            int lastPr = 1;
            proj = true;
            hist = false;
            List<Object> dmap = new ArrayList<Object>();
            boolean descend = false;

            for (int yr = endYear; yr >= startYear; yr--) {
                if (yr == startYear) {
                    lastPr = startPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr >= lastPr; pr--) {
                    if (descend) {
                        hist = true;
                        proj = false;
                    }
                    if (pr == currentPeriod && yr == currentYear) {
                        descend = true;
                    }
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);

                    if (hist) {

                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, commonHeader, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, commonHeader);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);

                    }

                }
                startPr = frequencyDivision;
            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                tableHeaderDTO.addDoubleHeaderMap(ACTUALS.getConstant(), dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                fullHeaderDTO.addDoubleColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                fullHeaderDTO.addDoubleHeaderMap(ACTUALS.getConstant(), dmap.toArray());

            }

            /// Projection in descending  
            startPr = endPeriod;
            lastPr = 1;
            proj = true;
            hist = false;

            for (int yr = endYear; yr >= histProjYear; yr--) {
                if (yr == histProjYear) {
                    lastPr = histProjPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr >= lastPr; pr--) {
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);

                    if (proj) {

                        Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, commonHeader, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, commonHeader);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                    }

                }
                startPr = frequencyDivision;
            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(PROJECTIONS.getConstant(), FORECAST.getConstant());
                tableHeaderDTO.addDoubleHeaderMap(PROJECTIONS.getConstant(), dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(PROJECTIONS.getConstant(), FORECAST.getConstant());
                fullHeaderDTO.addDoubleColumn(PROJECTIONS.getConstant(), FORECAST.getConstant());
                fullHeaderDTO.addDoubleHeaderMap(PROJECTIONS.getConstant(), dmap.toArray());
            }

        }

        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getPhsWorkSheetLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO) {

        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object doubleCol = Constant.GROUP;
        Object[] singleCol = {Constant.GROUP};
        tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        tableHeaderDTO.addDoubleColumn(doubleCol, " ");
        tableHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        fullHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        fullHeaderDTO.addDoubleColumn(doubleCol, " ");
        fullHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getPhsWorkSheetRightTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getPhsCalculatedWorkSheetColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }

    public static CustomTableHeaderDTO getPhsCalculatedWorkSheetColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        String frequency = projSelDTO.getFrequency();
        projSelDTO = getHistoryAndProjectionDetails(projSelDTO);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        int historyNum = projSelDTO.getHistoryNum();
        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endYear = projSelDTO.getEndYear();
        int endMonth = projSelDTO.getEndMonth();
        int endPeriod = getQuarter(endMonth);
        int histProjYear = projSelDTO.getHistProjYear();
        int histProjMonth = projSelDTO.getHistProjMonth();
        int histProjPeriod = getQuarter(histProjMonth);
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

        if (frequencyDivision != 1) {
            currentPeriod = currentPeriod + 1;
        }
        boolean hist = false;
        boolean proj = false;
        if (projectionOrder == 1) {
            int startPr = startPeriod;
            int lastPr = frequencyDivision;
            hist = true;
            proj = false;
            List<Object> dmap = new ArrayList<Object>();

            for (int yr = startYear; yr <= endYear; yr++) {
                if (yr == endYear) {
                    lastPr = endPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    if (pr == currentPeriod && yr == currentYear) {
                        hist = false;
                        proj = true;
                    }

                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);

                    if (hist) {

                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, commonHeader, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, commonHeader);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);

                    }
                }

                startPr = 1;
            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                tableHeaderDTO.addDoubleHeaderMap(ACTUALS.getConstant(), dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                fullHeaderDTO.addDoubleColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                fullHeaderDTO.addDoubleHeaderMap(ACTUALS.getConstant(), dmap.toArray());
            }

            /// Projection  
            startPr = histProjPeriod;
            lastPr = frequencyDivision;
            hist = true;
            proj = true;

            for (int yr = histProjYear; yr <= endYear; yr++) {
                if (yr == endYear) {
                    lastPr = endPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);

                    if (proj) {

                        Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, commonHeader, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, commonHeader);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + FORECAST.getConstant(), String.class);
                    }

                }

                startPr = 1;
            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(FORECAST.getConstant(), FORECAST.getConstant());
                tableHeaderDTO.addDoubleHeaderMap(FORECAST.getConstant(), dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(FORECAST.getConstant(), FORECAST.getConstant());
                fullHeaderDTO.addDoubleColumn(FORECAST.getConstant(), FORECAST.getConstant());
                fullHeaderDTO.addDoubleHeaderMap(FORECAST.getConstant(), dmap.toArray());
            }

        } else {
            int startPr = endPeriod;
            int lastPr = 1;
            proj = true;
            hist = false;
            List<Object> dmap = new ArrayList<Object>();
            boolean descend = false;

            for (int yr = endYear; yr >= startYear; yr--) {
                if (yr == startYear) {
                    lastPr = startPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr >= lastPr; pr--) {
                    if (descend) {
                        hist = true;
                        proj = false;
                    }
                    if (pr == currentPeriod && yr == currentYear) {
                        descend = true;
                    }

                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);

                    if (hist) {

                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, commonHeader, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, commonHeader);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);

                    }

                }
                startPr = frequencyDivision;
            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                tableHeaderDTO.addDoubleHeaderMap(ACTUALS.getConstant(), dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                fullHeaderDTO.addDoubleColumn(ACTUALS.getConstant(), ACTUALS.getConstant());
                fullHeaderDTO.addDoubleHeaderMap(ACTUALS.getConstant(), dmap.toArray());

            }

            /// Projection in descending  
            startPr = endPeriod;
            lastPr = 1;
            proj = true;
            hist = false;

            for (int yr = endYear; yr >= histProjYear; yr--) {
                if (yr == histProjYear) {
                    lastPr = histProjPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr >= lastPr; pr--) {
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);

                    if (proj) {

                        Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, commonHeader, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, commonHeader);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + FORECAST.getConstant(), String.class);
                    }

                }
                startPr = frequencyDivision;
            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(FORECAST.getConstant(), FORECAST.getConstant());
                tableHeaderDTO.addDoubleHeaderMap(FORECAST.getConstant(), dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(FORECAST.getConstant(), FORECAST.getConstant());
                fullHeaderDTO.addDoubleColumn(FORECAST.getConstant(), FORECAST.getConstant());
                fullHeaderDTO.addDoubleHeaderMap(FORECAST.getConstant(), dmap.toArray());
            }

        }

        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    private static int getQuarter(int projectionStartMonth) {
        if (projectionStartMonth <= 3) {
            projectionStartMonth = 1;
        } else if (projectionStartMonth <= 6) {
            projectionStartMonth = 2;
        } else if (projectionStartMonth <= 9) {
            projectionStartMonth = 3;
        } else if (projectionStartMonth <= 12) {
            projectionStartMonth = 4;
        }
        return projectionStartMonth;

    }
}
