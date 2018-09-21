/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Karthikeyan.Subraman
 * @param <T>
 */
public abstract class AbstractDemandSummaryLogic<T extends AdjustmentDTO> extends AbstractSummaryLogic<T> {

    protected DecimalFormat percent = new DecimalFormat("0.00");
    protected DecimalFormat dollar = new DecimalFormat("$####");
    private final String[] doublecolumn = {"total", "Total", StringUtils.EMPTY};

    /**
     *
     * @param selection
     * @param columns
     * @param isFrequencyMultiple
     * @return
     */
    public List<Object> generateHeader(SelectionDTO selection, String[] columns, boolean isFrequencyMultiple) {
        List<Object> finalList = new ArrayList<>();
        List<String> doubleSingleColumn;
        List<String> singleVisibleColumn = new ArrayList<>();
        List<String> doubleVisibleColumn = new ArrayList<>();
        Map<Object, Object[]> doubleSingleVisibleColumn = new HashMap<>();
        List<String> excelDoubleSingleColumn;
        List<String> singleVisibleHeader = new ArrayList<>();
        List<String> doubleVisibleHeader = new ArrayList<>();
        List<String> excelVisibleColumn = new ArrayList<>();
        List<String> excelSingleColumn = new ArrayList<>();
        List<String> excelVisibleHeader = new ArrayList<>();
        Map<Object, Object[]> excelDoubleSingleVisibleColumn = new HashMap<>();
        excelDoubleSingleVisibleColumn.put("month", new Object[]{"month"});
        List<String> singleColumn = new ArrayList<>();
        int index = -1;
        List<String> columnList = getColumns(selection.getSummaryvariables());
        List<String[]> doubleHeaderVariables = isFrequencyMultiple ? selection.getSummaryfrequencyList() : selection.getSummarydeductionVariables();
        
        List<String[]> doublecolumnList = new ArrayList<>();
        doublecolumnList.addAll(doubleHeaderVariables);
        doublecolumnList.add(doublecolumn);
        doubleHeaderVariables = doublecolumnList;
        for (String[] detection : doubleHeaderVariables) {
            doubleSingleColumn = new ArrayList<>();
            excelDoubleSingleColumn = new ArrayList<>();
            for (int i = 0; i < columns.length; i++) {
                String column = columns[i];
                singleColumn.add(detection.length > NumericConstants.TWO ? (detection[2] + "~" + detection[0]) + column + ARMUtils.DOT + (++index) : detection[0] + column + ARMUtils.DOT + (++index));
                excelSingleColumn.add((detection[0].equalsIgnoreCase(ARMUtils.TOTAL) ? ARMUtils.TOTAL : detection[0].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY)) + ARMUtils.DOUBLE_HIPHEN + column + ARMUtils.DOT + (index));
                if (columnList.contains(column)) {
                    int listIndex = columnList.indexOf(column);
                    String visibleColumn = selection.getSummaryvariables().get(listIndex)[0] + ARMUtils.DOT + index;
                    String header = selection.getSummaryvariables().get(listIndex)[1];
                    singleVisibleColumn.add(visibleColumn);
                    singleVisibleHeader.add(header);
                    doubleSingleColumn.add(visibleColumn);
                    excelVisibleColumn.add((detection[0].equalsIgnoreCase(ARMUtils.TOTAL) ? ARMUtils.TOTAL : detection[0].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY)) + ARMUtils.DOUBLE_HIPHEN + visibleColumn);
                    excelVisibleHeader.add(header);
                    excelDoubleSingleColumn.add(detection[0] + StringUtils.EMPTY + excelVisibleColumn);
                }
            }
            doubleVisibleColumn.add(detection.length > NumericConstants.TWO ? (detection[2] + "~" + detection[1]).replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY) : detection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY));
            doubleVisibleHeader.add(detection[1]);
            doubleSingleVisibleColumn.put(detection.length > NumericConstants.TWO ? (detection[2] + "~" + detection[1]).replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY) : detection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY), doubleSingleColumn.toArray());
            excelDoubleSingleVisibleColumn.put(detection.length > NumericConstants.TWO ? (detection[2] + "~" + detection[1]).replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY) : detection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY), excelDoubleSingleColumn.toArray());
        }
        ((AbstractSelectionDTO) selection).setSummarycolumnList(singleColumn);
        ((AbstractSelectionDTO) selection).setExcelVisibleColumn(excelSingleColumn);
        finalList.add(singleVisibleColumn);
        finalList.add(doubleVisibleColumn);
        finalList.add(singleVisibleHeader);
        finalList.add(doubleVisibleHeader);
        finalList.add(doubleSingleVisibleColumn);
        finalList.add(excelVisibleColumn);
        finalList.add(excelVisibleHeader);
        finalList.add(excelDoubleSingleVisibleColumn);
        return finalList;
    }

    protected List getFilterInputs(List<Object> inputs, SelectionDTO selection, TreeMap<String, Integer> masterSids) {
        List input = new ArrayList<>(inputs);
        for (int i = 0; i < 2; i++) {
            input.add(ARMUtils.getDeductionValuesMapForMultiplePeriod().get(selection.getSummarydeductionLevelDes()));
            input.add(masterSids.get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
            input.add(masterSids.get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
            input.add(masterSids.get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
            input.add(masterSids.get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.BRAND.toString()));
            input.add(masterSids.get(ARMUtils.levelVariablesVarables.ITEM.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.ITEM.toString()));
            input.add(selection.getSummarydeductionValues());
        }
        return input;
    }
}
