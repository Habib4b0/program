/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.dto;

import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.data.Container;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author sathyaseelan
 */
public class ReserveTableGenerator extends DefaultFieldFactory {

    /**
     * This method is used to set the components to the column in the Table
     * container.
     *
     * @param container the container
     * @param itemId the item id
     * @param propertyId the property id
     * @param uiContext the ui context
     * @return the field<?>
     */
    @Override
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
        if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant().equals(propertyId)) {
            return new CheckBox();
        } else if (ArrayUtils.contains(ARMUtils.getArmConfigDetailTextfieldGenerator(), propertyId.toString())) {
            return new TextField();
        } else if (ArrayUtils.contains(ARMUtils.getArmConfigDetailComboboxGenerator(), propertyId.toString())) {
            ComboBox comboBox = new ComboBox();
            comboBox.setNullSelectionAllowed(true);
            comboBox.addItem(GlobalConstants.getSelectOne());
            comboBox.setNullSelectionItemId(GlobalConstants.getSelectOne());
            return comboBox;
        } else {
            return new TextField();
        }
    }
}
