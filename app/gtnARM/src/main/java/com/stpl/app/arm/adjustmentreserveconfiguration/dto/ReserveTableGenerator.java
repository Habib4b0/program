/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.dto;

import com.stpl.app.arm.utils.ARMUtils;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.TextField;

/**
 *
 * @author 
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
        } else {
            return new TextField();
        }
    }
}
