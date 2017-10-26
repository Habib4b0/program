/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.form;

import com.stpl.app.arm.accountconfiguration.dto.AccountConfigDTO;
import com.stpl.app.arm.accountconfiguration.dto.AccountConfigSelection;
import com.stpl.app.arm.accountconfiguration.logic.AccountConfigLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

/**
 *
 * @author Srithar.Raju
 */
public class AccountConfigFieldFactory implements TableFieldFactory {

    AccountConfigLogic logic = AccountConfigLogic.getInstance();
    ExtPagedTable resultsTable;
    AccountConfigSelection selection;
    Boolean isValueChange = Boolean.TRUE;
    List<Object[]> resultsBrandFactoryDdlbList;
    List<Object[]> resultsCompanyFactoryDdlbList;
    List<Object[]> resultsBusinessUnitFactoryDdlbList;
    Map<String, String> updateFieldFactoryValues;

    public AccountConfigFieldFactory(ExtPagedTable resultsTable, AccountConfigSelection selection, List<Object[]> resultsBrandFactoryDdlbList, List<Object[]> resultsCompanyFactoryDdlbList, List<Object[]> resultsBusinessUnitFactoryDdlbList, Map<String, String> updateFieldFactoryValues) {
        this.resultsTable = resultsTable;
        this.selection = selection;
        this.resultsBrandFactoryDdlbList = resultsBrandFactoryDdlbList;
        this.resultsCompanyFactoryDdlbList = resultsCompanyFactoryDdlbList;
        this.resultsBusinessUnitFactoryDdlbList = resultsBusinessUnitFactoryDdlbList;
        this.updateFieldFactoryValues = updateFieldFactoryValues;
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
            AbstractComponent comp = (AbstractComponent) event.getProperty();
            Map<String, Object> map = (Map) comp.getData();
            String propertyId = map.get(ARMUtils.PROPERTY_ID).toString();
            Object itemId = map.get(ARMUtils.ITEM_ID);
            AccountConfigDTO dto = (AccountConfigDTO) itemId;
            Object value = event.getProperty().getValue();
            if (event.getProperty() instanceof ComboBox) {
                Container container = (Container) map.get(ARMUtils.CONTAINER);
                if (propertyId.equals("companyNoHelperDto")) {
                    int company = dto.getCompanyNoHelperDto() != null ? dto.getCompanyNoHelperDto().getId() : NumericConstants.ZERO;
                    int businessUnit = dto.getBusinessNoHelperDto() != null ? dto.getBusinessNoHelperDto().getId() : NumericConstants.ZERO;
                    value = company;
                    ComboBox accountCombo = (ComboBox) dto.getFieldFactoryComponentMap().get("account");
                    logic.loadAccount(accountCombo, company, businessUnit);
                    dto.setCompanyName(dto.getCompanyNoHelperDto() != null ? dto.getCompanyNoHelperDto().getDescription() : StringUtils.EMPTY);
                    container.getContainerProperty(itemId, "companyName").setValue(dto.getCompanyNoHelperDto() != null ? dto.getCompanyNoHelperDto().getDescription() : StringUtils.EMPTY);
                } else if (propertyId.equals("businessNoHelperDto")) {
                    int company = dto.getCompanyNoHelperDto() != null ? dto.getCompanyNoHelperDto().getId() : NumericConstants.ZERO;
                    int businessUnit = dto.getBusinessNoHelperDto() != null ? dto.getBusinessNoHelperDto().getId() : NumericConstants.ZERO;
                    value = businessUnit;
                    ComboBox accountCombo = (ComboBox) dto.getFieldFactoryComponentMap().get("account");
                    logic.loadAccount(accountCombo, company, businessUnit);
                    dto.setBusinessUnitName(dto.getBusinessNoHelperDto() != null ? dto.getBusinessNoHelperDto().getDescription() : StringUtils.EMPTY);
                    container.getContainerProperty(itemId, "businessUnitName").setValue(dto.getBusinessNoHelperDto() != null ? dto.getBusinessNoHelperDto().getDescription() : StringUtils.EMPTY);
                }
            }
            logic.updateTableValues(value, updateFieldFactoryValues.get(propertyId), dto, selection);

        }
    };

    private boolean findAllChecked() {
        List<AccountConfigDTO> containerItems = (List<AccountConfigDTO>) resultsTable.getItemIds();
        boolean checkAll = true;
        for (AccountConfigDTO accountConfigDTO : containerItems) {
            if (!accountConfigDTO.getCheckRecord()) {
                checkAll = false;
                break;
            }
        }
        return checkAll;
    }

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
        if (selection.isCurrentView()) {
            final AccountConfigDTO fieldFactoryValuesDTO = (AccountConfigDTO) itemId;
            Map<String, Object> map = new HashMap<>();
            map.put(ARMUtils.PROPERTY_ID, propertyId);
            map.put(ARMUtils.ITEM_ID, itemId);
            map.put(ARMUtils.CONTAINER, container);
            if (propertyId.equals("checkRecord")) {
                final ExtCustomCheckBox check = new ExtCustomCheckBox();
                fieldFactoryValuesDTO.addFieldFactoryMap(propertyId.toString(), check);
                check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                    @Override
                    public void click(ExtCustomCheckBox.ClickEvent event) {
                        Boolean val = ((ExtCustomCheckBox) event.getComponent()).getValue();
                        if (val == null || !val) {
                            if (resultsTable.getColumnCheckBox(VariableConstants.CHECK_RECORD)) {
                                resultsTable.setColumnCheckBox(VariableConstants.CHECK_RECORD, true, false);
                            }
                        } else {
                            resultsTable.setColumnCheckBox(VariableConstants.CHECK_RECORD, true, findAllChecked());
                        }
                        Object value = check.getValue();
                        logic.updateTableValues(value, updateFieldFactoryValues.get(propertyId.toString()), fieldFactoryValuesDTO, selection);
                    }
                });

                return check;
            }
            if (propertyId.equals("costCentre")) {
                final TextField costCentre = new TextField();
                costCentre.setImmediate(true);
                fieldFactoryValuesDTO.addFieldFactoryMap(propertyId.toString(), costCentre);
                costCentre.addFocusListener(focus);
                costCentre.setData(map);

                return costCentre;
            }
            if (ArrayUtils.contains(ARMUtils.ACCOUNT_CONFIG_COMBOBOX_PROPERTIES, propertyId.toString())) {
                final ComboBox comboBox = new ComboBox();
                comboBox.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
                comboBox.setImmediate(true);
                if ("account".equals(propertyId.toString())) {
                    comboBox.setImmediate(true);
                    int company = fieldFactoryValuesDTO.getCompanyNoHelperDto() != null ? fieldFactoryValuesDTO.getCompanyNoHelperDto().getId() : NumericConstants.ZERO;
                    int businessUnit = fieldFactoryValuesDTO.getBusinessNoHelperDto() != null ? fieldFactoryValuesDTO.getBusinessNoHelperDto().getId() : NumericConstants.ZERO;
                    logic.loadAccount(comboBox, company, businessUnit);
                }
                if ("companyNoHelperDto".equals(propertyId.toString())) {
                    if (selection.getSession().getMode().equals(ARMUtils.EDIT)) {
                        return null;
                    }
                    logic.loadCompanyOrBusinessUnitDdlb(comboBox, resultsCompanyFactoryDdlbList, "getCompanyQuery", Boolean.TRUE, Boolean.FALSE);
                } else if ("businessNoHelperDto".equals(propertyId.toString())) {
                    if (selection.getSession().getMode().equals(ARMUtils.EDIT)) {
                        return null;
                    }
                    logic.loadCompanyOrBusinessUnitDdlb(comboBox, resultsBusinessUnitFactoryDdlbList, "getBusinessQuery", Boolean.TRUE, Boolean.FALSE);
                } else if ("brandDdlb".equals(propertyId.toString())) {
                    logic.loadBrandDdlb(comboBox, resultsBrandFactoryDdlbList, Boolean.TRUE, Boolean.FALSE);
                }
                fieldFactoryValuesDTO.addFieldFactoryMap(propertyId.toString(), comboBox);
                comboBox.addFocusListener(focus);
                comboBox.setData(map);
                return comboBox;
            }
        }
        return null;

    }

}
