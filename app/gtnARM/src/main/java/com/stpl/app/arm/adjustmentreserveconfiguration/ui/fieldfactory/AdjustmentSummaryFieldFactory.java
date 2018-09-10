/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.ui.fieldfactory;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentSummaryConfigLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.ui.util.NumericConstants;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mohamed.Shahul
 */
public class AdjustmentSummaryFieldFactory implements TableFieldFactory {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentSummaryFieldFactory.class);

    private AdjustmentSummaryConfigLogic logic = AdjustmentSummaryConfigLogic.getInstance();
    private ExtPagedTable resultsTable;
    private ReserveSelection selection;

    public AdjustmentSummaryFieldFactory(final ExtPagedTable resultsTable, ReserveSelection selection) {
        this.resultsTable = resultsTable;
        this.selection = selection;
    }
    /**
     * This is value change listener used to update the value to DB tables.
     */

    private FocusListener focus = new FocusListener() {
        /**
         * Will execute,when we click an uploader.
         */
        @Override
        public void focus(com.vaadin.event.FieldEvents.FocusEvent focusEvent) {
            ((Field) focusEvent.getComponent()).addValueChangeListener(focusValueChange);
            if (focusEvent.getComponent() instanceof ComboBox) {
                ((ComboBox) focusEvent.getComponent()).removeFocusListener(this);
            } else if (focusEvent.getComponent() instanceof TextField) {
                ((TextField) focusEvent.getComponent()).removeFocusListener(this);
            }
        }
    };

    /**
     * This is the Value change listener used to attache the value change
     * listener.
     */
    private Property.ValueChangeListener focusValueChange = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
            if (valueChangeEvent.getProperty() instanceof ComboBox) {
                try {
                    ComboBox comboBox = (ComboBox) valueChangeEvent.getProperty();
                    Map dataMap = (Map) comboBox.getData();
                    final String propertyId = dataMap.get(ARMUtils.PROPERTY_ID).toString();
                    final AdjustmentReserveDTO itemIdDto = (AdjustmentReserveDTO) dataMap.get(ARMUtils.ITEM_ID);
                    logic.updateTableValues(itemIdDto, comboBox.getValue(), ARMUtils.getAdjustmentSummaryVisibleToDBColumnMap().get(propertyId), selection.getAdjustmentSummaryTempTableName());

                    if (ARMUtils.getAdjustmentSummaryAccountMap().containsKey(propertyId)) {
                        int adjustmentType = comboBox.getValue() != null ? (Integer) comboBox.getValue() : 0;
                        final CustomMenuBar custom = (CustomMenuBar) itemIdDto.getFieldFactoryComponent(ARMUtils.getAdjustmentSummaryAccountMap().get(propertyId));
                        CommonUtils.loadCustomMenubarAccount(custom, custom.getItems().get(0), adjustmentType, selection);
                        if (selection.isIsViewMode()) {
                            custom.setEnabled(false);
                        } else {
                            custom.setEnabled(true);
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.error("Error in valueChange :" , ex);
                }
            }
        }
    };
    private CustomMenuBar.SubMenuCloseListener submenuCloseListener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            CustomMenuBar custom = (CustomMenuBar) event.getComponent();
            StringBuilder value = new StringBuilder();
            Map dataMap = (Map) custom.getData();
            final String propertyId = dataMap.get(ARMUtils.PROPERTY_ID).toString();
            final AdjustmentReserveDTO itemIdDto = (AdjustmentReserveDTO) dataMap.get(ARMUtils.ITEM_ID);
            List<CustomMenuBar.CustomMenuItem> items = custom.getItems().get(0).getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked()) {
                    value.append(customMenuItem1.getMenuItem().getCaption()).append(ARMUtils.COMMA_CHAR);
                }
            }
            String values;
            if (value.length() > 0) {
                values = value.toString().substring(0, value.length() - 1);
            } else {
                values = null;
            }

            logic.updateTableValues(itemIdDto, values, ARMUtils.getAdjustmentSummaryVisibleToDBColumnMap().get(propertyId), selection.getAdjustmentSummaryTempTableName());
        }
    };

    @Override
    public Field<?> createField(Container container, final Object itemId, final Object propertyId, Component uiContext) {
        final AdjustmentReserveDTO fieldValuesDTO = (AdjustmentReserveDTO) itemId;
        Map<String, Object> summaryMap = new HashMap<>();
        summaryMap.put(ARMUtils.PROPERTY_ID, propertyId);
        summaryMap.put(ARMUtils.ITEM_ID, itemId);
        summaryMap.put(ARMUtils.CONTAINER, container);
        if (propertyId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant())) {
            final ExtCustomCheckBox check = new ExtCustomCheckBox();
            fieldValuesDTO.addFieldFactoryMap(propertyId.toString(), check);
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
                        logic.updateTableValues(fieldValuesDTO, value, "CHECK_RECORD", selection.getAdjustmentSummaryTempTableName());
                        List list = logic.isAllCheckBoxesAreChecked(selection);
                        resultsTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true, list.size() != 1 ? false : "true".equals(String.valueOf(list.get(0))));

                    }
                });
            }

            return check;
        }
        if (ArrayUtils.contains(ARMUtils.getArmAdjSummaryConfComboHeaders(), propertyId.toString())) {
            ComboBox combo = new ComboBox();
            loadTransactionNameForCurrentSession(combo, propertyId, summaryMap, fieldValuesDTO);
            return combo;
        }
        return null;
    }

    private void loadTransactionNameForCurrentSession(ComboBox combo, Object propertyId, Map<String, Object> map, AdjustmentReserveDTO fieldFactoryValuesDTO) {
        CommonUtils.loadTransactionNameForCurrentSession(combo, selection.getTempTableName(), Boolean.FALSE);
        fieldFactoryValuesDTO.addFieldFactoryMap(propertyId.toString(), combo);
        combo.setData(map);
        if (selection.isIsViewMode() || !selection.isIsCurrent()) {
            combo.setEnabled(false);
        } else {
            combo.addFocusListener(focus);
            combo.setEnabled(true);
        }
        combo.setData(map);
        combo.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
    }

    /**
     *
     * Add Custom Menu bar to the field factory
     */
    public void addFields() {
        for (final Object propertyId : ARMUtils.getArmAdjSummaryConfCustomMenubarHeaders()) {
            resultsTable.addGeneratedColumn(propertyId.toString(), new ExtCustomTable.ColumnGenerator() {
                @Override
                public Object generateCell(ExtCustomTable source, Object itemId, Object columnId) {
                    final AdjustmentReserveDTO fieldFactoryValuesDTO = (AdjustmentReserveDTO) itemId;
                    Map map = new HashMap<>();
                    map.put(ARMUtils.PROPERTY_ID, columnId);
                    map.put(ARMUtils.ITEM_ID, itemId);
                    map.put(ARMUtils.CONTAINER, source);
                    if (selection.isIsViewMode()) {
                        CustomTextField customTextField = new CustomTextField();
                        customTextField.setValue(fieldFactoryValuesDTO.getAccountValueById(propertyId.toString()));
                        customTextField.setEnabled(false);
                        return customTextField;

                    } else {
                        CustomMenuBar customBar = new CustomMenuBar();
                        CustomMenuBar.CustomMenuItem customMenuItem = customBar.addItem("  - Select Value -  ", null);
                        int adjType = fieldFactoryValuesDTO.getBalanceColumnById(propertyId.toString());
                        CommonUtils.loadCustomMenubarAccount(customBar, customMenuItem, adjType, selection);
                        if (adjType != 0) {
                            prePopulateAccountsInEdit(fieldFactoryValuesDTO.getAccountValueById(propertyId.toString()), customMenuItem);
                        }
                        customBar.addSubMenuCloseListener(submenuCloseListener);
                        fieldFactoryValuesDTO.addFieldFactoryMap(columnId.toString(), customBar);
                        customBar.setData(map);
                        return customBar;
                    }
                }
            });
        }
    }

    private void prePopulateAccountsInEdit(String values, CustomMenuBar.CustomMenuItem customMenuItem) {
        String[] splittedValues = values.split(",");
        if (splittedValues.length > 0 && customMenuItem.getChildren() != null) {
            for (int i = 0; i < splittedValues.length; i++) {
                for (CustomMenuBar.CustomMenuItem string : customMenuItem.getChildren()) {
                    if (string.getText().equals(String.valueOf(splittedValues[i]).trim())) {
                        string.setChecked(true);
                    }
                }
            }
        }
    }

    private void writeObject(ObjectOutputStream sumFieldFactout) throws IOException {
        sumFieldFactout.defaultWriteObject();
    }

    private void readObject(ObjectInputStream sumFieldFactout) throws IOException, ClassNotFoundException {
        sumFieldFactout.defaultReadObject();
    }
}
