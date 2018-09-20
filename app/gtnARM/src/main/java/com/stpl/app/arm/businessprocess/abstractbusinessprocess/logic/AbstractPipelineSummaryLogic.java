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
import com.stpl.ifs.util.constants.ARMConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Karthikeyan.Subraman
 */
public abstract class AbstractPipelineSummaryLogic<T extends AdjustmentDTO> extends AbstractSummaryLogic<T> {

    protected String[] getTotalColumn() {
        return (new String[]{"Total", "Total", StringUtils.EMPTY});
    }

    public List<Object> generateHeader(AbstractSelectionDTO selection, String[] columns) {
        List<Object> finalList = new ArrayList<>();
        List<String> doubleSingleColumn;
        List<String> excelDoubleSingleColumn;
        List<String> singleVisibleColumn = new ArrayList<>();
        List<String> excelVisibleColumn = new ArrayList<>();
        List<String> excelVisibleHeader = new ArrayList<>();
        List<String> doubleVisibleColumn = new ArrayList<>();
        List<String> excelSingleColumn = new ArrayList<>();
        Map<Object, Object[]> doubleSingleVisibleColumn = new HashMap<>();
        Map<Object, Object[]> excelDoubleSingleVisibleColumn = new HashMap<>();
        excelDoubleSingleVisibleColumn.put("month", new Object[]{"month"});
        List<String> singleVisibleHeader = new ArrayList<>();
        List<String> doubleVisibleHeader = new ArrayList<>();

        List<String> singleColumn = new ArrayList<>();
        int index = 0;
        List<String> columnList = getColumns(selection.getSummaryvariables());
        if (columnList.contains("override")) {
            String[] columnDeduction = {"adjustment", "Adjustment"};
            List<String[]> columnListDeduction = new ArrayList<>();
            columnListDeduction.addAll(selection.getSummaryvariables());
            columnListDeduction.add(columnDeduction);
            columnList = getColumns(columnListDeduction);
            selection.setSummaryvariables(columnListDeduction);
        }

        List<String[]> doubleHeaderVariables = selection.getSummarydeductionVariables();
        List<String[]> doublecolumnList = new ArrayList<>();
        doublecolumnList.addAll(doubleHeaderVariables);
        String[] doublecolumn = getTotalColumn();
        if (doublecolumn.length > 1) {
            doublecolumnList.add(doublecolumn);
        }
        doubleHeaderVariables = doublecolumnList;
        for (String[] detection : doubleHeaderVariables) {
            doubleSingleColumn = new ArrayList<>();
            excelDoubleSingleColumn = new ArrayList<>();
            for (int i = 0; i < columns.length; i++, index++) {
                String column = columns[i];
                singleColumn.add(column + ARMUtils.DOT + index);
                excelSingleColumn.add((detection[0].equalsIgnoreCase(ARMUtils.TOTAL) ? ARMUtils.TOTAL : detection[0].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY)) + ARMUtils.DOUBLE_HIPHEN + column + ARMUtils.DOT + (index));
                if (columnList.contains(column)) {
                    int listIndex = columnList.indexOf(column);
                    String visibleColumn = selection.getSummaryvariables().get(listIndex)[0] + ARMUtils.DOT + index;
                    String header = selection.getSummaryvariables().get(listIndex)[1];
                    singleVisibleColumn.add(visibleColumn);
                    singleVisibleHeader.add(header);
                    doubleSingleColumn.add(visibleColumn);
                    excelVisibleColumn.add(detection[0] + ARMUtils.DOUBLE_HIPHEN + visibleColumn);
                    excelVisibleHeader.add(header);
                    excelDoubleSingleColumn.add(detection[0] + ARMUtils.DOUBLE_HIPHEN + visibleColumn);
                }
            }

            doubleVisibleColumn.add(detection[2] + "~" + detection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY));
            doubleVisibleHeader.add(detection[1]);
            doubleSingleVisibleColumn.put(detection[2] + "~" + detection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY), doubleSingleColumn.toArray());
            excelDoubleSingleVisibleColumn.put(detection[2] + "~" + detection[1].replace(ARMUtils.SPACE.toString(), StringUtils.EMPTY), excelDoubleSingleColumn.toArray());
        }
        selection.setSummarycolumnList(singleColumn);
        finalList.add(singleVisibleColumn);
        finalList.add(doubleVisibleColumn);
        finalList.add(singleVisibleHeader);
        finalList.add(doubleVisibleHeader);
        finalList.add(doubleSingleVisibleColumn);
        finalList.add(excelVisibleColumn);
        finalList.add(excelVisibleHeader);
        finalList.add(excelDoubleSingleVisibleColumn);
        finalList.add(excelSingleColumn);
        return finalList;
    }

    @Override
    protected Object[] generateInputs(Object dto, SelectionDTO selection) {
        LOGGER.debug("selection.getSummary_levelFilter()={}", selection.getSummarylevelFilterValue());
        Object[] returnObj = new Object[NumericConstants.TWO];
        List<Object> inputs = new ArrayList<>();
        inputs.add(selection.getProjectionMasterSid());
        inputs.add(selection.getSummarydeductionLevelDes());
        String viewType = "";
        String currentViewType = "";
        TreeMap<String, Integer> masterSids = null;
        if (dto instanceof AdjustmentDTO) {
            AdjustmentDTO val = (AdjustmentDTO) dto;
            int levelNo = val.getLevelNo();
            masterSids = val.getMasterIds();
            if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
                if (selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
                    currentViewType = ARMUtils.getDemandSummaryLevelsinglePeriod().get(levelNo);
                    viewType = ARMUtils.getDemandSummaryLevelsinglePeriod().get(++levelNo);
                } else {
                    currentViewType = ARMUtils.getPipelineSummaryLevelSinglePeriod().get(levelNo);
                    viewType = ARMUtils.getPipelineSummaryLevelSinglePeriod().get(++levelNo);
                }
            } else if ("Deduction Contract".equals(selection.getSummaryviewType())) {
                currentViewType = ARMUtils.getTrx7SummaryLevelSinglePeriod().get(levelNo);
                viewType = ARMUtils.getTrx7SummaryLevelSinglePeriod().get(++levelNo);

            } else {
                currentViewType = ARMUtils.getSummaryLevel().get(levelNo);
                viewType = ARMUtils.getSummaryLevel().get(++levelNo);
            }
            if (masterSids == null) {
                masterSids = new TreeMap<>();
            }
            masterSids.put(currentViewType, Integer.valueOf(val.getBranditemmasterSid()));
        } else {
            masterSids = new TreeMap<>();
            if (selection.getSummarylevelFilterNo() == 0) {
                viewType = getView(selection.getSummarydeductionLevelDes(), selection.getSummaryviewType());
            } else if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract())) {
                if (selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
                    viewType = ARMUtils.getDemandSummaryLevelsinglePeriod().get(selection.getSummarylevelFilterNo());
                } else {
                    viewType = ARMUtils.getPipelineSummaryLevelSinglePeriod().get(selection.getSummarylevelFilterNo());
                }
            } else if ("Deduction Contract".equals(selection.getSummaryviewType())) {

                viewType = ARMUtils.getTrx7SummaryLevelSinglePeriod().get(selection.getSummarylevelFilterNo());

            } else {
                String view;
                if (selection.getSummaryviewType().equals(ARMConstants.getDeductionCustomerContract()) && !selection.getSummarydeductionLevelDes().equals(ARMConstants.getDeduction())) {
                    view = "non" + ARMConstants.getDeductionCustomerContract();
                } else {
                    view = selection.getSummaryviewType();
                }

                viewType = ARMUtils.getPipeLineLevelAndLevelFilter(view).get(selection.getSummarylevelFilterNo());
            }
        }
        if (viewType.equals(ARMConstants.getDeduction())) {
            viewType = ARMUtils.getDeductionLevelQueryName(selection.getSummarydeductionLevelDes());
        }
        inputs.add(viewType);
        inputs.add(ARMUtils.getSummaryViewType(selection.getSummaryviewType()));

        inputs.add(ARMUtils.SPACE);
        inputs.add(ARMUtils.SPACE);
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.BRAND.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.ITEM.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.ITEM.toString()));

        inputs.add(selection.getSummarydeductionValues());
        returnObj[0] = inputs;
        returnObj[1] = masterSids;

        return returnObj;
    }

}
