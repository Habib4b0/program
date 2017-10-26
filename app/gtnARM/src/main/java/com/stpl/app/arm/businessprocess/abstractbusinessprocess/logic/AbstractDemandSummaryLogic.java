/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.supercode.SelectionDTO;
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

    public DecimalFormat PERCENT = new DecimalFormat("0.00");
    public DecimalFormat DOLLAR = new DecimalFormat("$####");

    /**
     *
     * @param selection
     * @param columns
     * @param isFrequencyMultiple
     * @return
     */
    public List<Object> generateHeader(SelectionDTO selection, String[] columns, boolean isFrequencyMultiple) {
        List<Object> finalList = new ArrayList<>();
        List<String> double_single_Column;
        List<String> singleVisibleColumn = new ArrayList<>();
        List<String> doubleVisibleColumn = new ArrayList<>();
        Map<Object, Object[]> double_single_visibleColumn = new HashMap<>();
        List<String> excel_double_single_Column;
        List<String> singleVisibleHeader = new ArrayList<>();
        List<String> doubleVisibleHeader = new ArrayList<>();
        List<String> excelVisibleColumn = new ArrayList<>();
        List<String> excelSingleColumn = new ArrayList<>();
        List<String> excelVisibleHeader = new ArrayList<>();
        Map<Object, Object[]> excel_double_single_visibleColumn = new HashMap<>();
        excel_double_single_visibleColumn.put("month", new Object[]{"month"});
        List<String> singleColumn = new ArrayList<>();
        ((AbstractSelectionDTO) selection).setSummary_columnList(singleColumn);
        ((AbstractSelectionDTO) selection).setExcelVisibleColumn(excelSingleColumn);
        int index = -1;
        List<String> columnList = getColumns(selection.getSummary_variables());
        List<String[]> doubleHeaderVariables = isFrequencyMultiple ? selection.getSummary_frequencyList() : selection.getSummary_deductionVariables();
        String[] doublecolumn = {"total", "Total"};
        List<String[]> doublecolumnList = new ArrayList<>();
        doublecolumnList.addAll(doubleHeaderVariables);
        doublecolumnList.add(doublecolumn);
        doubleHeaderVariables = doublecolumnList;
        for (String[] detection : doubleHeaderVariables) {
            double_single_Column = new ArrayList<>();
            excel_double_single_Column = new ArrayList<>();
            for (int i = 0; i < columns.length; i++) {
                String column = columns[i];
                singleColumn.add(detection[0] + column + "." + (++index));
                excelSingleColumn.add(detection[1].replace(" ", StringUtils.EMPTY) + column + "." + (index));
                if (columnList.contains(column)) {
                    int listIndex = columnList.indexOf(column);
                    String visibleColumn = selection.getSummary_variables().get(listIndex)[0] + "." + index;
                    String header = selection.getSummary_variables().get(listIndex)[1];
                    singleVisibleColumn.add(visibleColumn);
                    singleVisibleHeader.add(header);
                    double_single_Column.add(visibleColumn);
                    excelVisibleColumn.add(detection[1].replace(" ", StringUtils.EMPTY) + StringUtils.EMPTY + visibleColumn);
                    excelVisibleHeader.add(header);
                    excel_double_single_Column.add(detection[0] + StringUtils.EMPTY + visibleColumn);
                }
            }
            doubleVisibleColumn.add(detection[1].replace(" ", StringUtils.EMPTY));
            doubleVisibleHeader.add(detection[1]);
            double_single_visibleColumn.put(detection[1].replace(" ", StringUtils.EMPTY), double_single_Column.toArray());
            excel_double_single_visibleColumn.put(detection[1].replace(" ", StringUtils.EMPTY), excel_double_single_Column.toArray());
        }
        finalList.add(singleVisibleColumn);
        finalList.add(doubleVisibleColumn);
        finalList.add(singleVisibleHeader);
        finalList.add(doubleVisibleHeader);
        finalList.add(double_single_visibleColumn);
        finalList.add(excelVisibleColumn);
        finalList.add(excelVisibleHeader);
        finalList.add(excel_double_single_visibleColumn);
        return finalList;
    }

}
