
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.ui.form;

import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentReserveLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

/**
 * This class is used to configure the field factory for reserve configuration.
 *
 * @author srithar
 */
public class ReserveFieldFactory implements TableFieldFactory {

    AdjustmentReserveLogic logic = AdjustmentReserveLogic.getInstance();
    ExtPagedTable resultsTable;
    ReserveSelection selection;
    Boolean isValueChange = Boolean.TRUE;

    public ReserveFieldFactory(ExtPagedTable resultsTable, ReserveSelection selection) {
        this.resultsTable = resultsTable;
        this.selection = selection;
    }
    /**
     * This is value change listener used to update the value to DB tables.
     */
    FocusListener focus = new FieldEvents.FocusListener() {
        @Override
        public void focus(FieldEvents.FocusEvent event) {
            ((Field) event.getComponent()).addValueChangeListener(valueChange);
            if (event.getComponent() instanceof ComboBox) {
                ((ComboBox) event.getComponent()).removeFocusListener(this);
            } else if (event.getComponent() instanceof TextField) {
                ((TextField) event.getComponent()).removeFocusListener(this);
            }
        }
    };
    /**
     * This is the Value change listener used to attache the value change
     * listener.
     */
    Property.ValueChangeListener valueChange = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (event.getProperty() instanceof ComboBox) {
                ComboBox combo = (ComboBox) event.getProperty();
                combo.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
                Map map = (Map) combo.getData();
                Object value = event.getProperty().getValue();
                if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.toString().equals(map.get(ARMUtils.PROPERTY_ID)) 
                        || ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.toString().equals(map.get(ARMUtils.PROPERTY_ID))) {
                    switch ((int) value) {
                        // value = 0 for positive and value = 1 for negative
                        case 0:
                            value = null;
                            break;
                        case -1:
                            value = 0;
                            break;
                        case 1:
                            value = 1;
                            break;

                    }
                }
                logic.updateTableValues(value, map.get(ARMUtils.PROPERTY_ID), map.get(ARMUtils.ITEM_ID), selection);
            } else {
                TextField text = (TextField) event.getProperty();
                text.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
                Map map = (Map) text.getData();
                Object value = event.getProperty().getValue();
                logic.updateTableValues(value, map.get(ARMUtils.PROPERTY_ID), map.get(ARMUtils.ITEM_ID), selection);
            }
        }
    };

    /**
     * This method is used to create the fields in tables.
     *
     * @param container
     * @param itemId
     * @param propertyId
     * @param uiContext
     * @return
     */
    @Override
    public Field<?> createField(Container container, final Object itemId, final Object propertyId, Component uiContext) {
        if (propertyId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.toString())) {
            final ExtCustomCheckBox check = new ExtCustomCheckBox();
            if (selection.isIsViewMode() || !selection.isIsCurrent()) {
                check.setEnabled(Boolean.FALSE);
            } else {
                check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                    @Override
                    public void click(ExtCustomCheckBox.ClickEvent event) {
                        Object value = check.getValue();
                        if (!check.getValue()) {
                            resultsTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), Boolean.TRUE, check.getValue());
                        }
                        logic.updateTableValues(value, propertyId, itemId, selection);
                        List list = logic.headerCheckAll(selection);
                        resultsTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true, list.size() != 1 ? false : "true".equals(String.valueOf(list.get(0))));
                    }
                });
            }
            return check;
        } else if (ArrayUtils.contains(ARMUtils.ADJUSTMENT_RESERVE_TEXT_BOX, propertyId.toString())) {
            final TextField text = new TextField();
            Map map = new HashMap<>();
            map.put(ARMUtils.PROPERTY_ID, propertyId);
            map.put(ARMUtils.ITEM_ID, itemId);
            text.setData(map);
            if (selection.isIsViewMode() || !selection.isIsCurrent()) {
                text.setEnabled(Boolean.FALSE);
            } else {
                text.addFocusListener(focus);
                text.setEnabled(Boolean.TRUE);
            }
            text.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
            return text;
        } else if (ArrayUtils.contains(ARMUtils.ADJUSTMENT_RESERVE_COMBOBOX, propertyId.toString())) {
            final ComboBox combo = new ComboBox();
            if (selection.isIsGTNDetails() && propertyId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL.toString())) {
                CommonUtils.loadComboBoxWithIntegerForComboBox(combo, "ARM_GTN_ADJUSTMENT_LEVEL", Boolean.FALSE);
            } else if (propertyId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL.toString())) {
                CommonUtils.loadComboBoxWithIntegerForComboBox(combo, "ARM_RES_ADJUSTMENT_LEVEL", Boolean.FALSE);
            } else {
                CommonUtils.loadComboBoxWithIntegerForComboBox(combo, ARMUtils.getDropDownMap().get(propertyId.toString()), Boolean.FALSE);
            }

            Map map = new HashMap<>();
            map.put(ARMUtils.PROPERTY_ID, propertyId);
            map.put(ARMUtils.ITEM_ID, itemId);
            combo.setData(map);

            if (selection.isIsViewMode() || !selection.isIsCurrent()) {
                combo.setEnabled(Boolean.FALSE);
            } else {
                combo.addFocusListener(focus);
                combo.setEnabled(Boolean.TRUE);
            }
            combo.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
            return combo;
        }
        return null;
    }
}
