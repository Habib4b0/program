/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import static com.stpl.app.gcm.util.Constants.IndicatorConstants.AMOUNT;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.RATE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.SALES;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.UNITS;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lokeshwari
 */
public class HeaderUtils {

    public static CustomTableHeaderDTO getSalesTabLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("levelValue", "Customer/Contract/Product", String.class);
        tableHeaderDTO.addDoubleColumn("levelValue", "");
        tableHeaderDTO.addDoubleHeaderMap("levelValue", new Object[]{"levelValue"});
        fullHeaderDTO.addSingleColumn("levelValue", "Customer/Contract/Product", String.class);
        fullHeaderDTO.addDoubleColumn("levelValue", "");
        fullHeaderDTO.addDoubleHeaderMap("levelValue", new Object[]{"levelValue"});
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSalesTabsRightTableColumns(CustomTableHeaderDTO fullHeaderDTO, String frequency) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getSalesTabsCalculatedColumns(tableHeaderDTO, fullHeaderDTO, frequency);
    }

    public static CustomTableHeaderDTO getSalesTabsCalculatedColumns(CustomTableHeaderDTO tableHeaderDTO, CustomTableHeaderDTO fullHeaderDTO, String frequency) {
        List<String> periodList = new ArrayList<String>();
        Map<String, String> periodListMap = new HashMap<String, String>();
        prepareCommonColumnHeaders(periodList, periodListMap, frequency);
        for (int i = 0; i < periodList.size(); i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = periodList.get(i);
            String commonHeader = periodListMap.get(commonColumn);
            Object singleColumn = commonColumn + SALES.getConstant();
            dmap.add(singleColumn);
            tableHeaderDTO.addSingleColumn(singleColumn, SALES.getConstant(), String.class);
            tableHeaderDTO.addSingleHistoryColumn(singleColumn, SALES.getConstant());
            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + SALES.getConstant(), String.class);
            Object singleColumnUnit = commonColumn + UNITS.getConstant();
            dmap.add(singleColumnUnit);
            tableHeaderDTO.addSingleColumn(singleColumnUnit, UNITS.getConstant(), String.class);
            tableHeaderDTO.addSingleProjectedColumn(singleColumnUnit, UNITS.getConstant());
            fullHeaderDTO.addSingleColumn(singleColumnUnit, commonHeader + " " + UNITS.getConstant(), String.class);
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }
        }
        return tableHeaderDTO;
    }

    public static void prepareCommonColumnHeaders(List<String> periodList, Map<String, String> periodListMap, String frequency) {
        int startPr = 1;
        int lastPr = 0;
        int frequencyDivision = 0;
        if (frequency.equalsIgnoreCase(Constants.QUARTERLY)) {
            frequencyDivision = 4;
            lastPr = 4;
        } else if (frequency.equalsIgnoreCase(Constants.ANNUALLY)) {
            frequencyDivision = 1;
            lastPr = 1;
        } else if (frequency.equalsIgnoreCase(Constants.SEMIANNUALLY)) {
            frequencyDivision = 2;
            lastPr = 2;
        } else {
            frequencyDivision = 12;
            lastPr = 12;
        }
        for (int yr = 2014; yr <= 2016; yr++) {
            for (int pr = startPr; pr <= lastPr; pr++) {
                List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                String commonColumn = common.get(0);
                String commonHeader = common.get(1);
                periodList.add(commonColumn);
                periodListMap.put(commonColumn, commonHeader);
            }
            startPr = 1;
        }
    }

    public static List<String> getCommonColumnHeader(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<String>();
        String commonColumn = "";
        String commonHeader = "";
        if (frequencyDivision == 1) {
            commonColumn = "" + year;
            commonHeader = "" + year;
        } else if (frequencyDivision == 4) {
            commonColumn = "q" + period + "" + year;
            commonHeader = "Q" + period + " " + year;
        } else if (frequencyDivision == 2) {
            commonColumn = "s" + period + "" + year;
            commonHeader = "S" + period + " " + year;
        } else if (frequencyDivision == 12) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    public static CustomTableHeaderDTO getRebateTabsRightTableColumns(CustomTableHeaderDTO fullHeaderDTO, String frequency) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getRebateTabsCalculatedColumns(tableHeaderDTO, fullHeaderDTO, frequency);
    }

    public static CustomTableHeaderDTO getRebateTabsCalculatedColumns(CustomTableHeaderDTO tableHeaderDTO, CustomTableHeaderDTO fullHeaderDTO, String frequency) {
        List<String> periodList = new ArrayList<String>();
        Map<String, String> periodListMap = new HashMap<String, String>();
        prepareCommonColumnHeaders(periodList, periodListMap, frequency);
        for (int i = 0; i < periodList.size(); i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = periodList.get(i);
            String commonHeader = periodListMap.get(commonColumn);
            Object singleColumn = commonColumn + AMOUNT.getConstant();
            dmap.add(singleColumn);
            tableHeaderDTO.addSingleColumn(singleColumn, AMOUNT.getConstant(), String.class);
            tableHeaderDTO.addSingleHistoryColumn(singleColumn, AMOUNT.getConstant());
            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + AMOUNT.getConstant(), String.class);
            Object singleColumnUnit = commonColumn + RATE.getConstant();
            dmap.add(singleColumnUnit);
            tableHeaderDTO.addSingleColumn(singleColumnUnit, RATE.getConstant(), String.class);
            tableHeaderDTO.addSingleProjectedColumn(singleColumnUnit, RATE.getConstant());
            fullHeaderDTO.addSingleColumn(singleColumnUnit, commonHeader + " " + RATE.getConstant(), String.class);
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }
        }
        return tableHeaderDTO;
    }

    /**
     * Get left Columns
     *
     * @param fullHeaderDTO
     * @return
     */
    public static CustomTableHeaderDTO getSalesTabLeftTableColumnsForPromoteTP(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("levelValue", "Contract Holder/Contract/Product", String.class);
        tableHeaderDTO.addDoubleColumn("group", "");
        tableHeaderDTO.addDoubleHeaderMap("group", new Object[]{"levelValue"});
        fullHeaderDTO.addSingleColumn("levelValue", "Contract Holder/Contract/Product", String.class);
        fullHeaderDTO.addDoubleColumn("group", "");
        fullHeaderDTO.addDoubleHeaderMap("group", new Object[]{"levelValue"});
        return tableHeaderDTO;
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

    public static CustomTableHeaderDTO getSalesAndRebateColumnsdsaf(Map selection, CustomTableHeaderDTO tableHeaderDTO, int historyNum, String freque, boolean isSalesFlag) {
        String freq = freque;
        String projection = "b";

        Calendar ob = Calendar.getInstance();
        int curMonth = ob.get(Calendar.MONTH);
        int curDate = ob.get(Calendar.DATE);
        int curYear = ob.get(Calendar.YEAR);
        int current = 1;

        int frequency = historyNum;
        int projectFrequency = 3;

        int division = 1;
        if (freq.equals("Quarterly")) {
            current = (curMonth / 3);
            division = 4;
        } else if (freq.equals("Semi-Annually")) {
            current = (curMonth / 6);
            division = 2;
        } else if (freq.equals("Monthly")) {
            current = curMonth;
            division = 12;
        } else if (freq.equals("Annually")) {
            current = curYear;
            division = 1;
        }
        projectFrequency = projectFrequency + 1;
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
        if (freq.contains("Annually") && !freq.contains("Semi-Annually")) {
            syear = current - frequency;
        }
        for (int i = 0; i < frequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = "";
            String commonHeader = "";
            if (freq.contains("Quarterly")) {
                commonColumn = "q" + squr + "" + syear;
                commonHeader = "Q" + squr + " " + syear;
            } else if (freq.contains("Semi-Annually")) {
                commonColumn = "s" + squr + "" + syear;
                commonHeader = "S" + squr + " " + syear;
            } else if (freq.contains("Annually")) {
                commonColumn = "" + syear;
                commonHeader = "" + syear;
            } else if (freq.contains("Monthly")) {
                String monthName = getMonthForInt(squr - 1);
                commonColumn = monthName + syear;
                commonHeader = monthName + " " + syear;
            }

            if (isSalesFlag) {
                dmap.add(commonColumn + "Sales");
                tableHeaderDTO.addSingleColumn(commonColumn + "Sales", "Sales", String.class);
                dmap.add(commonColumn + "Units");
                tableHeaderDTO.addSingleColumn(commonColumn + "Units", "Units", String.class);
            } else {
                dmap.add(commonColumn + "Amount");
                tableHeaderDTO.addSingleColumn(commonColumn + "Amount", "Amount", String.class);
                dmap.add(commonColumn + "Rate");
                tableHeaderDTO.addSingleColumn(commonColumn + "Rate", "Rate", String.class);

            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }
            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
        }
        squr = current + 1;
        for (int i = 0; i < projectFrequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = "";
            String commonHeader = "";
            if (freq.contains("Quarterly")) {
                commonColumn = "Q" + squr + "" + syear;
                commonHeader = "Q" + squr + " " + syear;
            } else if (freq.contains("Semi-Annually")) {
                commonColumn = "S" + squr + "" + syear;
                commonHeader = "S" + squr + " " + syear;
            } else if (freq.contains("Annually")) {
                commonColumn = "" + syear;
                commonHeader = "" + syear;
            } else if (freq.contains("Monthly")) {
                String monthName = getMonthForInt(squr - 1);
                commonColumn = monthName + syear;
                commonHeader = monthName + " " + syear;
            }
            if (isSalesFlag) {
                dmap.add(commonColumn + "Sales");
                tableHeaderDTO.addSingleColumn(commonColumn + "Sales", "Sales", String.class);
                dmap.add(commonColumn + "Units");
                tableHeaderDTO.addSingleColumn(commonColumn + "Units", "Units", String.class);
            } else {
                dmap.add(commonColumn + "Amount");
                tableHeaderDTO.addSingleColumn(commonColumn + "Amount", "Amount", String.class);
                dmap.add(commonColumn + "Rate");
                tableHeaderDTO.addSingleColumn(commonColumn + "Rate", "Rate", String.class);

            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }

            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
        }

        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSalesAndRebateColumns(CustomTableHeaderDTO tableHeaderDTO,CustomTableHeaderDTO fullHeader ,int historyNum, String freque, boolean isSalesFlag) {
        String freq = freque;
        Calendar ob = Calendar.getInstance();
        int curMonth = ob.get(Calendar.MONTH);
        int curYear = ob.get(Calendar.YEAR);
        int current = 1;

        int frequency = historyNum;
        int projectFrequency = 4;

        int division = 1;
        if (freq.equals("Quarterly")) {
            current = (curMonth / 3);
            division = 4;
            projectFrequency = 12;
        } else if (freq.equals("Semi-Annually")) {
            current = (curMonth / 6);
            division = 2;
            projectFrequency = 6;
        } else if (freq.equals("Monthly")) {
            current = curMonth;
            division = 12;
            projectFrequency = 36;
        } else if (freq.equals("Annually")) {
            current = curYear;
            division = 1;
            projectFrequency = 4;
        }
        projectFrequency = projectFrequency + 1;
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
        if (freq.contains("Annually") && !freq.contains("Semi-Annually")) {
            syear = current - frequency;
        }
        for (int i = 0; i < frequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = "";
            String commonHeader = "";
            if (freq.contains("Quarterly")) {
                commonColumn = "q" + squr + "" + syear;
                commonHeader = "Q" + squr + " " + syear;
            } else if (freq.contains("Semi-Annually")) {
                commonColumn = "s" + squr + "" + syear;
                commonHeader = "S" + squr + " " + syear;
            } else if (freq.contains("Annually")) {
                commonColumn = "" + syear;
                commonHeader = "" + syear;
            } else if (freq.contains("Monthly")) {
                String monthName = getMonthForInt(squr - 1);
                commonColumn = monthName + syear;
                commonColumn = commonColumn.toLowerCase();
                commonHeader = monthName + " " + syear;
            }
            if (isSalesFlag) {
                dmap.add(commonColumn + "Sales");
                tableHeaderDTO.addSingleColumn(commonColumn + "Sales", "Sales", String.class);
                fullHeader.addSingleColumn(commonColumn + "Sales", commonHeader+" Sales", String.class);
               
                dmap.add(commonColumn + "Units");
                tableHeaderDTO.addSingleColumn(commonColumn + "Units", "Units", String.class);
                fullHeader.addSingleColumn(commonColumn + "Units", commonHeader +" Units", String.class);
            } else {
                dmap.add(commonColumn + "Amount");
                tableHeaderDTO.addSingleColumn(commonColumn + "Amount", "Amount", String.class);
                fullHeader.addSingleColumn(commonColumn + "Amount", commonHeader+" Amount", String.class);
                dmap.add(commonColumn + "Rate");
                tableHeaderDTO.addSingleColumn(commonColumn + "Rate", "Rate", String.class);
                fullHeader.addSingleColumn(commonColumn + "Rate", commonHeader+" Rate", String.class);

            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                fullHeader.addDoubleColumn(commonColumn, commonHeader);
                fullHeader.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }
            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
        }
        squr = current + 1;
        for (int i = 0; i < projectFrequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = "";
            String commonHeader = "";
            if (freq.contains("Quarterly")) {
                commonColumn = "q" + squr + "" + syear;
                commonHeader = "Q" + squr + " " + syear;
            } else if (freq.contains("Semi-Annually")) {
                commonColumn = "s" + squr + "" + syear;
                commonHeader = "S" + squr + " " + syear;
            } else if (freq.contains("Annually")) {
                commonColumn = "" + syear;
                commonHeader = "" + syear;
            } else if (freq.contains("Monthly")) {
                String monthName = getMonthForInt(squr - 1);
                commonColumn = monthName + syear;
                commonColumn = commonColumn.toLowerCase();
                commonHeader = monthName + " " + syear;
            }
            if (isSalesFlag) {
                dmap.add(commonColumn + "Sales");
                tableHeaderDTO.addSingleColumn(commonColumn + "Sales", "Sales", String.class);
                fullHeader.addSingleColumn(commonColumn + "Sales", commonHeader+" Sales", String.class);
                dmap.add(commonColumn + "Units");
                tableHeaderDTO.addSingleColumn(commonColumn + "Units", "Units", String.class);
                fullHeader.addSingleColumn(commonColumn + "Units", commonHeader+" Units", String.class);
            } else {
                dmap.add(commonColumn + "Amount");
                tableHeaderDTO.addSingleColumn(commonColumn + "Amount", "Amount", String.class);
                fullHeader.addSingleColumn(commonColumn + "Amount", commonHeader+" Amount", String.class);
                dmap.add(commonColumn + "Rate");
                tableHeaderDTO.addSingleColumn(commonColumn + "Rate", "Rate", String.class);
                fullHeader.addSingleColumn(commonColumn + "Rate", commonHeader+" Rate", String.class);

            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                fullHeader.addDoubleColumn(commonColumn, commonHeader);
                fullHeader.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }

            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
        }
        return tableHeaderDTO;
    }
}
