/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.PipelineInventorySelectionDTO;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Vinoth.Parthasarathy
 * @param <T>
 * @param <E>
 */
public abstract class AbstractPipelineLogic<T extends AdjustmentDTO, E extends AbstractSelectionDTO> extends AbstractBPLogic<T> {

    /**
     * Returns the values selected for Company/BusinessUnit/AdjustmentType
     * combination in Adjustment Rate Config
     *
     * @param input
     * @return
     */
    public List<String> getRateConfigSettings(List input) {
        List result = QueryUtils.getItemData(input, "getRateConfigSettings", null);
        List<String> defaultValues = new ArrayList<>();
        if (!result.isEmpty()) {
            Object[] value = (Object[]) result.get(0);
            for (Object value1 : value) {
                if (value1 != null) {
                    defaultValues.add(String.valueOf(value1));
                } else {
                    defaultValues.add("0");
                }
            }
        }
        return defaultValues;
    }

    /**
     * Returns the period based on given frequency and RatePeriod which is
     * configured in Rate config
     *
     * @param ratePeriod
     * @param frequency
     * @param startPeriod
     * @param periodList
     * @return
     */
    public String getRatePeriod(String ratePeriod, String frequency, String startPeriod, List<String> periodList) {
        String period = StringUtils.EMPTY;
        String description = ratePeriod.contains("CURRENT") ? ratePeriod : HelperListUtil.getInstance().getDescriptionByID(Integer.valueOf(ratePeriod)).trim();
        if (StringUtils.isNotBlank(frequency) && StringUtils.isNotBlank(description)) {
            int monthNoWithPlus = getMonthNoWithPlus(description);
            int monthNo = getMonthNo(description, monthNoWithPlus);
            int currentPlus = getCurrentPlus(description, frequency);
            int currentMinus = getCurrentMinus(description, frequency, currentPlus);
            monthNo = currentMinus * monthNo;
            String freq = frequency.trim().toUpperCase(Locale.ENGLISH);
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
            String[] months = dateFormatSymbols.getShortMonths();
            Calendar calendar = Calendar.getInstance();
            if (!StringUtils.isBlank(startPeriod)) {
                String[] startArr = startPeriod.split(" ");
                calendar.set(Calendar.MONTH, CommonUtils.getMonthNo(startArr[0]) - 1);
                calendar.set(Calendar.YEAR, Integer.valueOf(startArr[1]));
            }
            calendar.add(Calendar.MONTH, monthNo);
            String year;
            int month;
            String quarter;
            String semi;
            year = String.valueOf(calendar.get(Calendar.YEAR));
            month = calendar.get(Calendar.MONTH);
            quarter = String.valueOf(month / NumericConstants.THREE + 1);
            semi = String.valueOf(month / NumericConstants.SIX + 1);
            String periodWithS = getPeriodWithSemi(freq, semi, year);
            String periodWithQ = getPeriodWithQuarter(freq, quarter, year, periodWithS);
            period = freq.startsWith("M") ? months[month] + " " + year : periodWithQ;
        }
        /**
         * This block is for returning the default value. This block is added
         * based on the comment given in GALUAT-540 and GALUAT-541 ticket
         *
         * For Eg: Case 1: If data selection from date is Oct 2013, in
         * Adjustment rate configuration they have selected CURRENT - 2 so the
         * value (Aug 2013) won't be available in the Period/Rate ddlb. So based
         * on the comments specified in the tickets we will set Oct 2013 as a
         * default value.
         *
         * Case 2: If data selection from date is Oct 2020, in Adjustment rate
         * configuration they have selected CURRENT + 12 so the value (Oct 2021)
         * Won't be avaliable in the Period/Rate ddlb. So based on the comments
         * specified in the tickets we will make Jan 2021 as a default value.
         *
         * Note: End date may differ based on the current active file.
         */
        if (periodList != null && !periodList.isEmpty() && !periodList.contains(period)) {
            if (description.contains("-")) {
                return periodList.get(1);
            } else if (description.contains("+")) {
                return periodList.get(periodList.size() - 1);
            }
        }
        return period;
    }

    private String getPeriodWithQuarter(String freq, String quarter, String year, String periodWithS) {
        return freq.startsWith("Q") ? "Q" + quarter + " " + year
                : periodWithS;
    }

    private static String getPeriodWithSemi(String freq, String semi, String year) {
        return freq.startsWith("S") ? "S" + semi + " " + year : year;
    }

    private int getCurrentMinus(String description, String frequency, int currentPlus) {
        return description.contains("-") ? -1 * getFreqDistribution(frequency.trim().charAt(0)) : currentPlus;
    }

    private int getCurrentPlus(String description, String frequency) {
        return description.contains("+") ? 1 * getFreqDistribution(frequency.trim().charAt(0)) : 0;
    }

    private int getMonthNo(String description, int monthNoWithPlus) {
        return description.contains("-") ? Integer.parseInt(description.split("-")[1].trim()) : monthNoWithPlus;
    }

    private int getMonthNoWithPlus(String description) {
        return description.contains("+") ? Integer.parseInt(description.split("[+]")[1].trim()) : 0;
    }

    /**
     * This is same method as above method - getRatePeriod But this method
     * returns Date in String format based on the DATA selection from Date.
     *
     * This changes is pertaining to GALUAT - 725
     *
     * @param ratePeriod
     * @param frequency
     * @param startPeriod
     * @param periodList
     * @param fromDate - Data se
     * @return
     */
    public String getRatePeriodFromDS(String ratePeriod, String frequency, String startPeriod, List<String> periodList, Date fromDate) {
        String period = StringUtils.EMPTY;
        String description = ratePeriod.contains("CURRENT") ? ratePeriod : HelperListUtil.getInstance().getDescriptionByID(Integer.valueOf(ratePeriod)).trim();
        if (StringUtils.isNotBlank(frequency) && StringUtils.isNotBlank(description)) {
            int monthNoWithPlus = getMonthNoWithPlus(description);
            int monthNo = getMonthNo(description, monthNoWithPlus);
            int currentPlus = getCurrentPlus(description, frequency);
            int currentMinus = getCurrentMinus(description, frequency, currentPlus);
            monthNo = currentMinus * monthNo;
            String freq = frequency.trim().toUpperCase(Locale.ENGLISH);
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
            String[] months = dateFormatSymbols.getShortMonths();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fromDate);
            if (!StringUtils.isBlank(startPeriod)) {
                String[] startArr = startPeriod.split(" ");
                calendar.set(Calendar.MONTH, CommonUtils.getMonthNo(startArr[0]) - 1);
                calendar.set(Calendar.YEAR, Integer.valueOf(startArr[1]));
            }
            calendar.add(Calendar.MONTH, monthNo);
            String year;
            int month;
            String quarter;
            String semi;
            year = String.valueOf(calendar.get(Calendar.YEAR));
            month = calendar.get(Calendar.MONTH);
            quarter = String.valueOf(month / NumericConstants.THREE + 1);
            semi = String.valueOf(month / NumericConstants.SIX + 1);
            String periodWithS = getPeriodWithSemi(freq, semi, year);
            String periodWithQ = getPeriodWithQuarter(freq, quarter, year, periodWithS);
            period = freq.startsWith("M") ? months[month] + " " + year : periodWithQ;
        }
        /**
         * This block is for returning the default value. This block is added
         * based on the comment given in GALUAT-540 and GALUAT-541 ticket
         *
         * For Eg: Case 1: If data selection from date is Oct 2013, in
         * Adjustment rate configuration they have selected CURRENT - 2 so the
         * value (Aug 2013) won't be available in the Period/Rate ddlb. So based
         * on the comments specified in the tickets we will set Oct 2013 as a
         * default value.
         *
         * Case 2: If data selection from date is Oct 2020, in Adjustment rate
         * configuration they have selected CURRENT + 12 so the value (Oct 2021)
         * Won't be avaliable in the Period/Rate ddlb. So based on the comments
         * specified in the tickets we will make Jan 2021 as a default value.
         *
         * Note: End date may differ based on the current active file.
         */
        if (periodList != null && !periodList.isEmpty() && !periodList.contains(period)) {
            if (description.contains("-")) {
                return periodList.get(1);
            } else if (description.contains("+")) {
                return periodList.get(periodList.size() - 1);
            }
        }
        return period;
    }

    private int getFreqDistribution(char frequency) {
        int freq = 1;
        if ('Q' == frequency) {
            freq = NumericConstants.THREE;
        } else if ('S' == frequency) {
            freq = NumericConstants.SIX;
        } else if ('A' == frequency) {
            freq = NumericConstants.TWELVE;
        }
        return freq;
    }

    public List<Object> generateHeader(AbstractSelectionDTO selection) {
        List<Object> finalList = new ArrayList<>();
        List<String> singleVisibleColumn = new ArrayList<>();
        List<String> singleVisibleHeader = new ArrayList<>();

        for (int i = 0; i < selection.getSalesVariables().size(); i++) {
            String column = selection.getSalesVariables().get(i)[0];
            String header = selection.getSalesVariables().get(i)[1];
            singleVisibleColumn.add(column);
            singleVisibleHeader.add(header);
        }

        finalList.add(singleVisibleColumn);
        finalList.add(singleVisibleHeader);
        return finalList;
    }

    public List<Object> generateInventoryHeader(E selection) {
        List<Object> finalList = new ArrayList<>();
        List<String> singleVisibleColumn = new ArrayList<>();
        List<String> defaultVisibleColumn = new ArrayList<>();
        List<String> singleVisibleHeader = new ArrayList<>();

        int index = 0;
        int defaultindex = 0;
        List<String> defaultColumn = new ArrayList();
        defaultColumn.add("totalInventory");
        defaultColumn.add("weeksOnHand");
        defaultColumn.add("unitsPerRetail");
        defaultColumn.add("price");
        defaultColumn.add("priceOverride");
        defaultColumn.add("returnReserve");
        defaultColumn.add("netPipelineValue");
        List<String> columnList = getColumns(selection.getSalesVariables());
        String[] variableVisibleColumn = selection.getVariableVisibleColumns();
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < variableVisibleColumn.length; i++) {
            String column = variableVisibleColumn[i];

            if (columnList.contains(column)) {
                int listIndex = columnList.indexOf(column);
                String visibleColumn = selection.getSalesVariables().get(listIndex)[0] + "." + index;
                String header = selection.getSalesVariables().get(listIndex)[1];
                singleVisibleColumn.add(visibleColumn);
                singleVisibleHeader.add(header);
            }

            if (column.contains("~") && columnList.contains(column)) {
                int listIndex = columnList.indexOf(column);
                String visibleColumn = selection.getSalesVariables().get(listIndex)[0] + "." + index;
                defaultVisibleColumn.add(visibleColumn);
            } else if (!column.contains("~")) {
                String column1 = defaultColumn.get(defaultindex++);
                String visibleColumn = column1 + "." + index;
                defaultVisibleColumn.add(visibleColumn);
                map.put(column1, visibleColumn);
            }
            index++;

        }

        finalList.add(singleVisibleColumn);
        finalList.add(singleVisibleHeader);
        selection.setSummarycolumnList(defaultVisibleColumn);
        selection.setInventorycolumnList(defaultVisibleColumn);
        ((PipelineInventorySelectionDTO) selection).setInventoryfixedColumnList(map);
        return finalList;
    }

    protected List<String> getColumns(List<String[]> varibales) {
        List<String> column = new ArrayList<>();
        for (int i = 0; i < varibales.size(); i++) {
            String[] object = varibales.get(i);
            column.add(object[0]);
        }
        return column;
    }

    public boolean updateOverride(List input) {
        try {
            QueryUtils.itemUpdate(input, "OVERRIDE_QUERY");
        } catch (Exception e) {
            LOGGER.error("Error in updateOverride :", e);
            return false;
        }
        return true;
    }
     public boolean updateOverrideLevelFilter(List input) {
         try {
            QueryUtils.itemUpdate(input, "OVERRIDE_QUERY_LEVEL_FILTER");
        } catch (Exception e) {
            LOGGER.error("Error in updateOverride :", e);
            return false;
        }
        return true;
    }

    public List getTableInput(SessionDTO sessionDTO) {
        LOGGER.debug("sessionDTO{}", sessionDTO);
        return Collections.emptyList();
    }

    public boolean getCondition(AdjustmentDTO dto, Object propertyId, AbstractSelectionDTO selection) {
        LOGGER.debug("AdjustmentDTO{}", dto);
        LOGGER.debug("propertyId{}", propertyId);
        LOGGER.debug("AbstractSelectionDTO{}", selection);
        return false;
    }

}
