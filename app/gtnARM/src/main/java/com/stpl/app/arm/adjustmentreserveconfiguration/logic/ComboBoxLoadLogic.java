/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.logic;

import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.utils.ConstantsUtils;

import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.ui.ComboBox;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohamed.Shahul
 */
public class ComboBoxLoadLogic {

    /**
     * loadCompanyOrBusinessUnitDdlb
     *
     * @param comboBox
     * @param resultsList
     * @param queryName
     */
    public void loadAdjustmentTypesExistingInReserve(ComboBox comboBox, List<Object[]> resultsList, Boolean isFiler, String tableName) {

        comboBox.addItem(NumericConstants.ZERO);
        comboBox.setItemCaption(NumericConstants.ZERO, isFiler ? ConstantsUtils.SHOW_ALL : GlobalConstants.getSelectOne());
        List<Object[]> resultsListValue = null;
        if (resultsList.isEmpty()) {
            List inputList = new ArrayList(NumericConstants.ONE);
            inputList.add(tableName);
            resultsListValue = QueryUtils.getItemData(inputList, "loadAdjustmentTypesExistingInReserve", null);
            inputList.clear();
        } else {
            resultsListValue = resultsList;
        }
        for (Object[] obj : resultsListValue) {
            if (obj[NumericConstants.TWO] != null) {
                comboBox.addItem((int) obj[NumericConstants.ZERO]);
                comboBox.setItemCaption((int) obj[NumericConstants.ZERO], obj[NumericConstants.ONE] + "");
            }
        }
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(NumericConstants.ZERO);
    }
}
