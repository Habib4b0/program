/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.ui.fieldfactory;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentSummaryConfigLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.BalanceSummaryLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
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
 *
 * @author Mohamed.Shahul
 */
public class BalanceSummaryFieldFactory implements TableFieldFactory {

    BalanceSummaryLogic logic = BalanceSummaryLogic.getInstance();
    ExtPagedTable resultsTable;
    ReserveSelection selection;
    Boolean isValueChange = Boolean.TRUE;
    private static final Logger LOGGER = Logger.getLogger(BalanceSummaryFieldFactory.class);

    public BalanceSummaryFieldFactory(final ExtPagedTable resultsTable, ReserveSelection selection) {
        this.resultsTable = resultsTable;
        this.selection = selection;
    }
    /**
     * This is value change listener used to update the value to DB tables.
     */
    FieldEvents.FocusListener focus = new FieldEvents.FocusListener() {
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
                try {
                    ComboBox combo = (ComboBox) event.getProperty();
                    Map dataMap = (Map) combo.getData();
                    final String propertyId = dataMap.get(ARMUtils.PROPERTY_ID).toString();
                    final AdjustmentReserveDTO itemIdDto = (AdjustmentReserveDTO) dataMap.get(ARMUtils.ITEM_ID);
                    logic.updateTableValues(itemIdDto.getBalanceSummarySid(), combo.getValue(), ARMUtils.getBalanceSummaryVisibleToDBColumnMap().get(propertyId), selection.getBalanceSummaryTempTableName());
                    if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_ACCOUNT.getConstant().equals(propertyId)) {
                        ComboBox accountCombo = (ComboBox) itemIdDto.getFieldFactoryComponentMap().get(propertyId);
                        for (Object visibleComboBoxes : ARMUtils.getBalSummaryConfComboHeader()) {
                            ComboBox dependentComboBoxes = (ComboBox) itemIdDto.getFieldFactoryComponentMap().get(visibleComboBoxes.toString());
                            if (dependentComboBoxes != null) {
                                CommonUtils.loadTransactionNameForCurrentSessionFromAccount(dependentComboBoxes, selection.getTempTableName(), Boolean.FALSE, accountCombo.getValue());
                            }
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.error("Error in valueChange:"+ex);
                }
            }
        }
    };

    @Override
    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
        final AdjustmentReserveDTO fieldFactoryValuesDTO = (AdjustmentReserveDTO) itemId;
        Map<String, Object> map = new HashMap<>();
        map.put(ARMUtils.PROPERTY_ID, propertyId);
        map.put(ARMUtils.ITEM_ID, itemId);
        map.put(ARMUtils.CONTAINER, container);
        if (propertyId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant())) {
            final ExtCustomCheckBox check = new ExtCustomCheckBox();
            fieldFactoryValuesDTO.addFieldFactoryMap(propertyId.toString(), check);
            if (selection.isIsViewMode()) {
                check.setEnabled(false);
            } else {
                check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                    @Override
                    public void click(ExtCustomCheckBox.ClickEvent event) {
                        Object value = check.getValue();
                        if (!check.getValue()) {
                            resultsTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true, check.getValue());
                        }
                        logic.updateTableValues(fieldFactoryValuesDTO.getBalanceSummarySid(), value, "CHECK_RECORD", selection.getBalanceSummaryTempTableName());
                        List list = AdjustmentSummaryConfigLogic.getInstance().isAllCheckBoxesAreChecked(selection);
                        resultsTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true, list.size() != 1 ? false : "true".equals(String.valueOf(list.get(0))));

                    }
                });
            }

            return check;
        }
        if (ArrayUtils.contains(ARMUtils.getBalSummaryConfComboHeader(), propertyId.toString())) {
            final ComboBox combo = new ComboBox();
            CommonUtils.loadTransactionNameForCurrentSession(combo, selection.getTempTableName(), Boolean.FALSE);
            fieldFactoryValuesDTO.addFieldFactoryMap(propertyId.toString(), combo);
            combo.setData(map);
            if (selection.isIsViewMode()) {
                combo.setEnabled(false);
            } else {
                combo.addFocusListener(focus);
                combo.setEnabled(true);
            }
            combo.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
            combo.setData(map);
            return combo;
        }
        if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_ACCOUNT.getConstant().equals(propertyId.toString())) {
            final ComboBox combo = new ComboBox();
            CommonUtils.loadDistinctAccount(combo, selection.getTempTableName(), Boolean.FALSE);
            fieldFactoryValuesDTO.addFieldFactoryMap(propertyId.toString(), combo);
            combo.setData(map);
            if (selection.isIsViewMode()) {
                combo.setEnabled(false);
            } else {
                combo.addFocusListener(focus);
                combo.setEnabled(true);
            }
            combo.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
            combo.setData(map);
            return combo;
        }
        return null;
    }
}
