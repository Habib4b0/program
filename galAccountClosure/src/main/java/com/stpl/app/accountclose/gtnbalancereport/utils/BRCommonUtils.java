/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.gtnbalancereport.utils;

import com.stpl.app.accountclose.gtnbalancereport.dto.SelectionDTO;
import com.vaadin.ui.ComboBox;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.custommenubar.CustomMenuBar;

/**
 *
 * @author gopinath
 */
public class BRCommonUtils {
     /**
     * The list name bundle.
     */
    private static ResourceBundle listNameBundle = ResourceBundle.getBundle("properties.gtnbalancereport");

    public ComboBox loadDDLBValue(ComboBox comboBox, String propertyName) {
        String listValues[] = listNameBundle.getString(propertyName).split(",");
        for (int i = 0; i < listValues.length; i++) {
            comboBox.addItem(StringUtils.EMPTY + listValues[i]);
        }
        return comboBox;
    }

  public List<String> getCurrentCheckValue(List<String> selectedVariables, CustomMenuBar.CustomMenuItem customMenuItem) {
        List<String> finalOrderedList = new ArrayList<String>();
        selectedVariables.removeAll(selectedVariables);
        
        if (customMenuItem != null && customMenuItem.getSize() > 0) {
            List<String> result = new ArrayList<String>();
            List<CustomMenuBar.CustomMenuItem> items = customMenuItem.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked()) {
                    result.add(customMenuItem1.getMenuItem().getCaption());
                    selectedVariables.add(customMenuItem1.getMenuItem().getCaption());
                }
            }

        }
        finalOrderedList.clear();
        finalOrderedList.addAll(getOrderedCheckedValue(selectedVariables));
        return finalOrderedList;
    }

    public List<String> getOrderedCheckedValue(List<String> selectedVariables) {
        List<String> orderedCheckValue = new ArrayList<String>();
        SelectionDTO brSelectionDTO = new SelectionDTO();
        for (String selecVar : selectedVariables) {
            if (StringUtils.isNotBlank(selecVar)) {
                selecVar = StringUtils.trim(selecVar);
                if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.ACCRUALS_BASE_RATE.toString()))) {
                    brSelectionDTO.setAccruals_base_rate(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.ACCRUALS_MANUAL_ADJUSTMENTS.toString()))) {
                    brSelectionDTO.setAccruals_manual_adjustments(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.ACCRUALS_RECONCILIATION.toString()))) {
                    brSelectionDTO.setAccruals_reconciliation(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.ACCRUALS_OTHER.toString()))) {
                    brSelectionDTO.setAccruals_other(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PROJECTED_DEDUCTION_AMOUNT.toString()))) {
                    brSelectionDTO.setProjected_deduction_amount(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PROJ_DEDUCTION_RATE.toString()))) {
                    brSelectionDTO.setProj_deduction_rate(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.DEDUCTION_RATE.toString()))) {
                    brSelectionDTO.setDeduction_rate(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PROJ_CONTRACT_SALES_AMT.toString()))) {
                    brSelectionDTO.setProj_contract_sales_amt(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.CONTRACT_SALES_AMT.toString()))) {
                    brSelectionDTO.setContract_sales_amt(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PROJ_CONTRACT_SALES_UNITS.toString()))) {
                    brSelectionDTO.setContract_sales_units(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.DEMAND_AMOUNT.toString()))) {
                    brSelectionDTO.setDemand_amount(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.DEMAND_UNITS.toString()))) {
                    brSelectionDTO.setDemand_units(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.GTS_AMOUNT.toString()))) {
                    brSelectionDTO.setGts_amount(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.GTS_UNITS.toString()))) {
                    brSelectionDTO.setGts_units(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.INVENTORY_WITHDRAWAL_AMOUNT.toString()))) {
                    brSelectionDTO.setInventory_withdrawal_amount(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.INVENTORY_WITHDRAWAL_UNITS.toString()))) {
                    brSelectionDTO.setInventory_withdrawal_units(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_OF_PROJ_DEMAND.toString()))) {
                    brSelectionDTO.setPer_of_proj_demand(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_OF_DEMAND.toString()))) {
                    brSelectionDTO.setPer_of_demand(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_OF_PROJ_GTS.toString()))) {
                    brSelectionDTO.setPer_of_proj_gts(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_OF_GTS.toString()))) {
                    brSelectionDTO.setPer_of_gts(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_OF_PROJ_INVENTORY_WITHDRAWALS.toString()))) {
                    brSelectionDTO.setPer_of_proj_inventory_withdrawals(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_OF_INVENTORY_WITHDRAWALS.toString()))) {
                    brSelectionDTO.setPer_of_inventory_withdrawals(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_ACHIEVED_ACCRUALS_TO_PROJ_DEDUCTIONS.toString()))) {
                    brSelectionDTO.setPer_achieved_accruals_to_proj_deductions(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.VARIANCE_ACCRUALS_TO_PROJ_DEDUCTIONS.toString()))) {
                    brSelectionDTO.setVar_accruals_to_proj_deductions(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_ACHIEVED_ACCRUALS_TO_DEDUCTIONS.toString()))) {
                    brSelectionDTO.setPer_achieved_accruals_to_deductions(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_ACHIEVED_DEDUCTIONS_TO_PROJ_DEDUCTIONS.toString()))) {
                    brSelectionDTO.setPer_achieved_deductions_to_proj_deductions(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.VARIANCE_DEDUCTIONS_AMT_TO_PROJ_DEDUCTIONS_AMT.toString()))) {
                    brSelectionDTO.setVar_deductions_amt_to_proj_deductions_amt(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.VARIANCE_DEDUCTIONS_RATE_TO_PROJ_DEDUCTION_RATE.toString()))) {
                    brSelectionDTO.setVar_deductions_rate_to_proj_deduction_rate(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_ACHIEVED_CONTRACT_SALES_TO_PROJ_CONTRACT_SALES.toString()))) {
                    brSelectionDTO.setPer_achieved_contract_sales_to_proj_contract_sales(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.VARIANCE_CONTRACT_SALES_TO_PROJ_CONTRACT_SALES.toString()))) {
                    brSelectionDTO.setVar_contract_sales_to_proj_contract_sales(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_ACHIEVED_CONTRACT_UNITS_TO_PROJEC_CONTRACT_UNITS.toString()))) {
                    brSelectionDTO.setPer_achieved_contract_units_to_projec_contract_units(true);
                } else if (selecVar.equalsIgnoreCase(StringUtils.trim(com.stpl.app.accountclose.utils.Constants.BasReportVariables.VARIANCE_CONTRACT_UNITS_TO_PROJ_CONTRACT_UNITS.toString()))) {
                    brSelectionDTO.setVar_contract_units_to_proj_contract_units(true);
                }
            }
        }
        orderedCheckValue.clear();
        orderedCheckValue.addAll(getBRCheckedVariable(brSelectionDTO));

        return orderedCheckValue;

    }

    public List<String> getBRCheckedVariable(SelectionDTO brSelectionDTO) {
        List<String> checkedBRVariable = new ArrayList<String>();
        if (true) {
            checkedBRVariable.add("Accruals");
        }
        if (brSelectionDTO.isAccruals_base_rate()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.ACCRUALS_BASE_RATE.toString());
        }
        if (brSelectionDTO.isAccruals_manual_adjustments()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.ACCRUALS_MANUAL_ADJUSTMENTS.toString());
        }
        if (brSelectionDTO.isAccruals_reconciliation()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.ACCRUALS_RECONCILIATION.toString());
        }
        if (brSelectionDTO.isAccruals_other()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.ACCRUALS_OTHER.toString());
        }
        if (brSelectionDTO.isProjected_deduction_amount()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PROJECTED_DEDUCTION_AMOUNT.toString());
        }
        if (true) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.DEDUCTIONS.toString());
        }
        if (brSelectionDTO.isProj_deduction_rate()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PROJ_DEDUCTION_RATE.toString());
        }
        if (brSelectionDTO.isDeduction_rate()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.DEDUCTION_RATE.toString());
        }
        if (brSelectionDTO.isProj_contract_sales_amt()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PROJ_CONTRACT_SALES_AMT.toString());
        }
        if (brSelectionDTO.isContract_sales_amt()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.CONTRACT_SALES_AMT.toString());
        }
        if (brSelectionDTO.isContract_sales_units()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PROJ_CONTRACT_SALES_UNITS.toString());
        }
        if (brSelectionDTO.isDemand_amount()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.DEMAND_AMOUNT.toString());
        }
        if (brSelectionDTO.isDemand_units()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.DEMAND_UNITS.toString());
        }
        if (brSelectionDTO.isGts_amount()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.GTS_AMOUNT.toString());
        }
        if (brSelectionDTO.isGts_units()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.GTS_UNITS.toString());
        }
        if (brSelectionDTO.isInventory_withdrawal_amount()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.INVENTORY_WITHDRAWAL_AMOUNT.toString());
        }
        if (brSelectionDTO.isInventory_withdrawal_units()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.INVENTORY_WITHDRAWAL_UNITS.toString());
        }
        if (brSelectionDTO.isPer_of_proj_demand()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_OF_PROJ_DEMAND.toString());
        }
        if (brSelectionDTO.isPer_of_demand()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_OF_DEMAND.toString());
        }
        if (brSelectionDTO.isPer_of_proj_gts()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_OF_PROJ_GTS.toString());
        }
        if (brSelectionDTO.isPer_of_gts()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_OF_GTS.toString());
        }
        if (brSelectionDTO.isPer_of_proj_inventory_withdrawals()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_OF_PROJ_INVENTORY_WITHDRAWALS.toString());
        }
        if (brSelectionDTO.isPer_of_inventory_withdrawals()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_OF_INVENTORY_WITHDRAWALS.toString());
        }
        if (brSelectionDTO.isPer_achieved_accruals_to_proj_deductions()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_ACHIEVED_ACCRUALS_TO_PROJ_DEDUCTIONS.toString());
        }
        if (brSelectionDTO.isVar_accruals_to_proj_deductions()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.VARIANCE_ACCRUALS_TO_PROJ_DEDUCTIONS.toString());
        }
        if (brSelectionDTO.isPer_achieved_accruals_to_deductions()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_ACHIEVED_ACCRUALS_TO_DEDUCTIONS.toString());
        }
        if (true) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.VARIANCE_ACCRUALS_TO_DEDUCTIONS.toString());
        }
        if (brSelectionDTO.isPer_achieved_deductions_to_proj_deductions()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_ACHIEVED_DEDUCTIONS_TO_PROJ_DEDUCTIONS.toString());
        }
        if (brSelectionDTO.isVar_deductions_amt_to_proj_deductions_amt()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.VARIANCE_DEDUCTIONS_AMT_TO_PROJ_DEDUCTIONS_AMT.toString());
        }
        if (brSelectionDTO.isVar_deductions_rate_to_proj_deduction_rate()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.VARIANCE_DEDUCTIONS_RATE_TO_PROJ_DEDUCTION_RATE.toString());
        }
        if (brSelectionDTO.isPer_achieved_contract_sales_to_proj_contract_sales()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_ACHIEVED_CONTRACT_SALES_TO_PROJ_CONTRACT_SALES.toString());
        }
        if (brSelectionDTO.isVar_contract_sales_to_proj_contract_sales()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.VARIANCE_CONTRACT_SALES_TO_PROJ_CONTRACT_SALES.toString());
        }
        if (brSelectionDTO.isPer_achieved_contract_units_to_projec_contract_units()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.PER_ACHIEVED_CONTRACT_UNITS_TO_PROJEC_CONTRACT_UNITS.toString());
        }
        if (brSelectionDTO.isVar_contract_units_to_proj_contract_units()) {
            checkedBRVariable.add(com.stpl.app.accountclose.utils.Constants.BasReportVariables.VARIANCE_CONTRACT_UNITS_TO_PROJ_CONTRACT_UNITS.toString());
        }
        return checkedBRVariable;
    }
}
