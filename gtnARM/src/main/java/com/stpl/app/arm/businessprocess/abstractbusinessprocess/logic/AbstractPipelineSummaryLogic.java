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

    protected String[] getTotalColumn(){
        String[] doublecolumn = {"Total", "Total"};
        return doublecolumn;
    }
    public List<Object> generateHeader(AbstractSelectionDTO selection, String[] columns) {
        List<Object> finalList = new ArrayList<>();
        List<String> double_single_Column;
        List<String> excel_double_single_Column;
        List<String> singleVisibleColumn = new ArrayList<>();
        List<String> excelVisibleColumn = new ArrayList<>();
        List<String> excelVisibleHeader = new ArrayList<>();
        List<String> doubleVisibleColumn = new ArrayList<>();
        Map<Object, Object[]> double_single_visibleColumn = new HashMap<>();
        Map<Object, Object[]> excel_double_single_visibleColumn = new HashMap<>();
        excel_double_single_visibleColumn.put("month", new Object[]{"month"});
        List<String> singleVisibleHeader = new ArrayList<>();
        List<String> doubleVisibleHeader = new ArrayList<>();

        List<String> singleColumn = new ArrayList<>();
        selection.setSummary_columnList(singleColumn);
        int index = 0;
        List<String> columnList = getColumns(selection.getSummary_variables());
        if (columnList.contains("override")) {
            String[] columnDeduction = {"adjustment", "Adjustment"};
            List<String[]> columnListDeduction = new ArrayList<>();
            columnListDeduction.addAll(selection.getSummary_variables());
            columnListDeduction.add(columnDeduction);
            columnList = getColumns(columnListDeduction);
            selection.setSummary_variables(columnListDeduction);
        }
        
        
        List<String[]> doubleHeaderVariables = selection.getSummary_deductionVariables();
        List<String[]> doublecolumnList = new ArrayList<>();
        doublecolumnList.addAll(doubleHeaderVariables);
        String[] doublecolumn =getTotalColumn();
        if(doublecolumn!=null){
            doublecolumnList.add(doublecolumn);
        }
        doubleHeaderVariables = doublecolumnList;
        for (String[] detection : doubleHeaderVariables) {
            double_single_Column = new ArrayList<>();
            excel_double_single_Column = new ArrayList<>();
            for (int i = 0; i < columns.length; i++, index++) {
                String column = columns[i];
                singleColumn.add(column + "." + index);
                if (columnList.contains(column)) {
                    int listIndex = columnList.indexOf(column);
                    String visibleColumn = selection.getSummary_variables().get(listIndex)[0] + "." + index;
                    String header = selection.getSummary_variables().get(listIndex)[1];
                    singleVisibleColumn.add(visibleColumn);
                    singleVisibleHeader.add(header);
                    double_single_Column.add(visibleColumn);
                    excelVisibleColumn.add(detection[0] + StringUtils.EMPTY + visibleColumn);
                    excelVisibleHeader.add(header);
                    excel_double_single_Column.add(detection[0] + StringUtils.EMPTY + visibleColumn);
                }
            }

            doubleVisibleColumn.add(detection[0]);
            doubleVisibleHeader.add(detection[1]);
            double_single_visibleColumn.put(detection[0], double_single_Column.toArray());
            excel_double_single_visibleColumn.put(detection[0], excel_double_single_Column.toArray());
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

    @Override
    protected Object[] generateInputs(Object dto, SelectionDTO selection) {
        LOGGER.debug("selection.getSummary_levelFilter()=" + selection.getSummary_levelFilterValue());
        Object[] returnObj = new Object[NumericConstants.TWO];
        List<Object> inputs = new ArrayList<>();
        inputs.add(selection.getProjectionMasterSid());
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        inputs.add(selection.getSummary_deductionLevelDes());
        String viewType = "";
        String currentViewType = "";
        TreeMap<String, Integer> masterSids = null;
        if (dto instanceof AdjustmentDTO) {
            AdjustmentDTO val = (AdjustmentDTO) dto;
            int levelNo = val.getLevelNo();
            LOGGER.debug("levelNo----" + levelNo+"ARMUtils.getSummaryLevel().get(levelNo + 1)==="+ARMUtils.getSummaryLevel().get(levelNo + 1));
            masterSids = val.getMasterIds();
            if (selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomerContract())) {
                if (selection.getSummary_deductionLevelDes().equals(ARMConstants.getDeduction())) {
                    currentViewType = ARMUtils.getDemandSummaryLevel_singlePeriod().get(levelNo);
                    viewType = ARMUtils.getDemandSummaryLevel_singlePeriod().get(++levelNo);
                } else {
                    currentViewType = ARMUtils.getPipelineSummaryLevel_singlePeriod().get(levelNo);
                    viewType = ARMUtils.getPipelineSummaryLevel_singlePeriod().get(++levelNo);
                }
            }else if(selection.getSummary_viewType().equals("Deduction Contract")){
                 currentViewType = ARMUtils.getTrx7SummaryLevel_singlePeriod().get(levelNo);
                    viewType = ARMUtils.getTrx7SummaryLevel_singlePeriod().get(++levelNo);
            
            
            } else {
                currentViewType = ARMUtils.getSummaryLevel().get(levelNo);
                viewType = ARMUtils.getSummaryLevel().get(++levelNo);
            }
            if(masterSids==null){
             masterSids = new TreeMap<>();
            }
            masterSids.put(currentViewType, Integer.valueOf(val.getBrand_item_masterSid()));
        } else {
            masterSids = new TreeMap<>();
            if (selection.getSummary_levelFilterNo() == 0) {
                viewType = getView(selection.getSummary_deductionLevelDes(), selection.getSummary_viewType());
            } else if (selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomerContract())) {
                if (selection.getSummary_deductionLevelDes().equals(ARMConstants.getDeduction())) {
                    viewType = ARMUtils.getDemandSummaryLevel_singlePeriod().get(selection.getSummary_levelFilterNo());
                } else {
                    viewType = ARMUtils.getPipelineSummaryLevel_singlePeriod().get(selection.getSummary_levelFilterNo());
                }
            } else if(selection.getSummary_viewType().equals("Deduction Contract")){
                
                    viewType = ARMUtils.getTrx7SummaryLevel_singlePeriod().get(selection.getSummary_levelFilterNo());
                 
            }else {
                String view = StringUtils.EMPTY;
                if (selection.getSummary_viewType().equals(ARMConstants.getDeductionCustomerContract()) && !selection.getSummary_deductionLevelDes().equals(ARMConstants.getDeduction())) {
                    view = "non" + ARMConstants.getDeductionCustomerContract();
                } else {
                    view = selection.getSummary_viewType();
                }

                viewType = ARMUtils.getPipeLineLevelAndLevelFilter(view).get(selection.getSummary_levelFilterNo());
            }
        }
        if (viewType.equals(ARMConstants.getDeduction())) {
            viewType = ARMUtils.getDeductionLevelQueryName(selection.getSummary_deductionLevelDes());
        }
        inputs.add(viewType);
        inputs.add(ARMUtils.getSummaryViewType(selection.getSummary_viewType()));
        if (!isView) {

            inputs.add(" ");
            inputs.add(" ");
        } else {
            inputs.add(" ");
            inputs.add(" ");
        }
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.BRAND.toString()));
        inputs.add(masterSids.get(ARMUtils.levelVariablesVarables.ITEM.toString()) == null ? "%" : masterSids.get(ARMUtils.levelVariablesVarables.ITEM.toString()));

        inputs.add(selection.getSummary_deductionValues());
        returnObj[0] = inputs;
        returnObj[1] = masterSids;

        return returnObj;
    }

}
