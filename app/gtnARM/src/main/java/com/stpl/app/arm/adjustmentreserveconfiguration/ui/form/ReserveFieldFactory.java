
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
import org.jboss.logging.Logger;

/**
 * This class is used to configure the field factory for reserve configuration.
 *
 * @author srithar
 */
public class ReserveFieldFactory implements TableFieldFactory {

    private static final Logger LOGGER = Logger.getLogger(ReserveFieldFactory.class);

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
            if (isValueChange) {
                try {
                    if (event.getProperty() instanceof ComboBox) {
                        ComboBox combo = (ComboBox) event.getProperty();
                        combo.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
                        Map map = (Map) combo.getData();
                        Container container = (Container) map.get(ARMUtils.CONTAINER);
                        Object value = event.getProperty().getValue();

                        if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.toString().equals(map.get(ARMUtils.PROPERTY_ID))
                                || ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.toString().equals(map.get(ARMUtils.PROPERTY_ID))
                                || ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.REPORT_INDICATOR.toString().equals(map.get(ARMUtils.PROPERTY_ID))) {
                            Object oppositValue = 0;
                            Object oppositValueToBeUpdated = null;
                            switch ((int) value) {
                                // value = 0 for positive and value = 1 for negative
                                case 0:
                                    value = null;
                                    break;
                                case -1:
                                    value = 0;
                                    oppositValue = 1;
                                    oppositValueToBeUpdated = 1;
                                    break;
                                case 1:
                                    value = 1;
                                    oppositValue = -1;
                                    oppositValueToBeUpdated = 0;
                                    break;
                                default:
                                    break;

                            }
                            if (!ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.REPORT_INDICATOR.toString().equals(map.get(ARMUtils.PROPERTY_ID))) {
                                String property = ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.toString().equals(map.get(ARMUtils.PROPERTY_ID))
                                        ? ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.toString() : ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.toString();
                                isValueChange = Boolean.FALSE;
                                container.getContainerProperty(map.get(ARMUtils.ITEM_ID), property).setValue(oppositValue);
                                logic.updateTableValues(oppositValueToBeUpdated, property, map.get(ARMUtils.ITEM_ID), selection);
                                isValueChange = Boolean.TRUE;
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
                } catch (Exception ex) {
                    LOGGER.error("Error in valueChange :"+ex);
                }
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
        } else if (ArrayUtils.contains(ARMUtils.getAdjustmentReserveTextBox(), propertyId.toString())) {
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
        } else if (ArrayUtils.contains(ARMUtils.getAdjustmentReserveCombobox(), propertyId.toString())) {
            ComboBox combo = new ComboBox();
            combo = loadComboForAdjReserve(combo, propertyId, itemId, container);
            return combo;
        }
        return null;
    }

    private ComboBox loadComboForAdjReserve(ComboBox combo, final Object propertyId, final Object itemId, Container container) {
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
        map.put(ARMUtils.CONTAINER, container);
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
}
