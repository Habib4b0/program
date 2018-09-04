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
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.server.Sizeable;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.v7.ui.TextField;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mohamed.Shahul
 */
public class BalanceSummaryFieldFactory implements TableFieldFactory {

    private BalanceSummaryLogic logic = BalanceSummaryLogic.getInstance();
    private ExtPagedTable balSumResultsTable;
    private ReserveSelection bsrSelection;
    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceSummaryFieldFactory.class);

    public BalanceSummaryFieldFactory(final ExtPagedTable resultsTable, ReserveSelection bsrSelection) {
        this.balSumResultsTable = resultsTable;
        this.bsrSelection = bsrSelection;
    }
    /**
     * This is value change listener used to update the value to DB tables.
     */
    private FocusListener balSumFocus = new FocusListener() {
        /**
         * Will execute,when we click an uploader.
         */
        @Override
        public void focus(FocusEvent bsrFocusEvent) {
            ((Field) bsrFocusEvent.getComponent()).addValueChangeListener(valueChange);
            if (bsrFocusEvent.getComponent() instanceof ComboBox) {
                ((ComboBox) bsrFocusEvent.getComponent()).removeFocusListener(this);
            } else if (bsrFocusEvent.getComponent() instanceof TextField) {
                ((TextField) bsrFocusEvent.getComponent()).removeFocusListener(this);
            }
        }
    };

    /**
     * This is the Value change listener used to attache the value change
     * listener.
     */
    private Property.ValueChangeListener valueChange = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (event.getProperty() instanceof ComboBox) {
                try {
                    ComboBox combo = (ComboBox) event.getProperty();
                    Map dataMap = (Map) combo.getData();
                    final String propertyId = dataMap.get(ARMUtils.PROPERTY_ID).toString();
                    final AdjustmentReserveDTO itemIdDto = (AdjustmentReserveDTO) dataMap.get(ARMUtils.ITEM_ID);
                    logic.updateTableValues(itemIdDto.getBalanceSummarySid(), combo.getValue(), ARMUtils.getBalanceSummaryVisibleToDBColumnMap().get(propertyId), bsrSelection.getBalanceSummaryTempTableName());
                    if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_ACCOUNT.getConstant().equals(propertyId)) {
                        ComboBox accountCombo = (ComboBox) itemIdDto.getFieldFactoryComponentMap().get(propertyId);
                        for (Object visibleComboBoxes : ARMUtils.getBalSummaryConfComboHeader()) {
                            ComboBox dependentComboBoxes = (ComboBox) itemIdDto.getFieldFactoryComponentMap().get(visibleComboBoxes.toString());
                            if (dependentComboBoxes != null) {
                                CommonUtils.loadTransactionNameForCurrentSessionFromAccount(dependentComboBoxes, bsrSelection.getTempTableName(), Boolean.FALSE, accountCombo.getValue());
                            }
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.error("Error in valueChange:" , ex);
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
            addCheckListener(check, fieldFactoryValuesDTO);
            return check;
        }
        if (ArrayUtils.contains(ARMUtils.getBalSummaryConfComboHeader(), propertyId.toString())) {
            final ComboBox combo = new ComboBox();
            CommonUtils.loadTransactionNameForCurrentSession(combo, bsrSelection.getTempTableName(), Boolean.FALSE);
            getComboboxProperties(fieldFactoryValuesDTO, propertyId, combo, map);
            return combo;
        }
        if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_ACCOUNT.getConstant().equals(propertyId.toString())) {
            final ComboBox combo = new ComboBox();
            CommonUtils.loadDistinctAccount(combo, bsrSelection.getTempTableName(), Boolean.FALSE);
            getComboboxProperties(fieldFactoryValuesDTO, propertyId, combo, map);
            return combo;
        }
        return null;
    }

    private void addCheckListener(final ExtCustomCheckBox check, final AdjustmentReserveDTO fieldFactoryValuesDTO) {
        if (bsrSelection.isIsViewMode()) {
            check.setEnabled(false);
        } else {
            check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                @Override
                public void click(ExtCustomCheckBox.ClickEvent event) {
                    Object value = check.getValue();
                    if (!check.getValue()) {
                        balSumResultsTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true, check.getValue());
                    }
                    logic.updateTableValues(fieldFactoryValuesDTO.getBalanceSummarySid(), value, "CHECK_RECORD", bsrSelection.getBalanceSummaryTempTableName());
                    List list = AdjustmentSummaryConfigLogic.getInstance().isAllCheckBoxesAreChecked(bsrSelection);
                    balSumResultsTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true, list.size() != 1 ? false : "true".equals(String.valueOf(list.get(0))));
                    
                }
            });
        }
    }

    private void getComboboxProperties(final AdjustmentReserveDTO fieldFactoryValuesDTO, Object propertyId, final ComboBox combo, Map<String, Object> map) {
        fieldFactoryValuesDTO.addFieldFactoryMap(propertyId.toString(), combo);
        combo.setData(map);
        if (bsrSelection.isIsViewMode()) {
            combo.setEnabled(false);
        } else {
            combo.addFocusListener(balSumFocus);
            combo.setEnabled(true);
        }
        combo.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
        combo.setData(map);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
