/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Karthikeyan.Subraman
 * @param <T>
 */
public abstract class AbstractDemandSummaryLogic<T extends AdjustmentDTO> extends AbstractSummaryLogic<T> {

    public DecimalFormat percent = new DecimalFormat("0.00");
    public DecimalFormat dollar = new DecimalFormat("$####");

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
        ((AbstractSelectionDTO) selection).setSummarycolumnList(singleColumn);
        ((AbstractSelectionDTO) selection).setExcelVisibleColumn(excelSingleColumn);
        int index = -1;
        List<String> columnList = getColumns(selection.getSummaryvariables());
        List<String[]> doubleHeaderVariables = isFrequencyMultiple ? selection.getSummaryfrequencyList() : selection.getSummarydeductionVariables();
        String[] doublecolumn = {"total", "Total", StringUtils.EMPTY};
        List<String[]> doublecolumnList = new ArrayList<>();
        doublecolumnList.addAll(doubleHeaderVariables);
        doublecolumnList.add(doublecolumn);
        doubleHeaderVariables = doublecolumnList;
        for (String[] detection : doubleHeaderVariables) {
            doubleSingleColumn = new ArrayList<>();
            excelDoubleSingleColumn = new ArrayList<>();
            for (int i = 0; i < columns.length; i++) {
                String column = columns[i];
                singleColumn.add(detection.length > NumericConstants.TWO ? (detection[2] + "~" + detection[0]) + column + "." + (++index) : detection[0] + column + "." + (++index));
                excelSingleColumn.add(detection[1].replace(" ", StringUtils.EMPTY) + column + "." + (index));
                if (columnList.contains(column)) {
                    int listIndex = columnList.indexOf(column);
                    String visibleColumn = selection.getSummaryvariables().get(listIndex)[0] + "." + index;
                    String header = selection.getSummaryvariables().get(listIndex)[1];
                    singleVisibleColumn.add(visibleColumn);
                    singleVisibleHeader.add(header);
                    doubleSingleColumn.add(visibleColumn);
                    excelVisibleColumn.add(detection[1].replace(" ", StringUtils.EMPTY) + StringUtils.EMPTY + visibleColumn);
                    excelVisibleHeader.add(header);
                    excelDoubleSingleColumn.add(detection[0] + StringUtils.EMPTY + visibleColumn);
                }
            }
            doubleVisibleColumn.add(detection.length > NumericConstants.TWO ? (detection[2] + "~" + detection[1]).replace(" ", StringUtils.EMPTY) : detection[1].replace(" ", StringUtils.EMPTY));
            doubleVisibleHeader.add(detection[1]);
            doubleSingleVisibleColumn.put(detection.length > NumericConstants.TWO ? (detection[2] + "~" + detection[1]).replace(" ", StringUtils.EMPTY) : detection[1].replace(" ", StringUtils.EMPTY), doubleSingleColumn.toArray());
            excelDoubleSingleVisibleColumn.put(detection.length > NumericConstants.TWO ? (detection[2] + "~" + detection[1]).replace(" ", StringUtils.EMPTY) : detection[1].replace(" ", StringUtils.EMPTY), excelDoubleSingleColumn.toArray());
        }
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

}
