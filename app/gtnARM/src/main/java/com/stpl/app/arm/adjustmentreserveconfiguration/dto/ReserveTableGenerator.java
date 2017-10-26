/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.dto;

import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.data.Container;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

/**
 *
 * @author sathyaseelan.v
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
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
        if (propertyId.equals("checkRecord")) {
            CheckBox checkRecord = new CheckBox();
            return checkRecord;
        } else if (propertyId.equals("account")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("accountDescription")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("companyNo")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("division")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("businessUnit")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("costCenter")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("project")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("future1")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("future2")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("balanceType")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("database")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("dataAccessSet")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("chartOfAccounts")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("ledger")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("category")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("source")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("journalName")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("journalDescription")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("reverseJournal")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("lineDescription")) {
            TextField account = new TextField();
            return account;
        } else if (propertyId.equals("adjustmentType")) {
            ComboBox adjustmentType = new ComboBox();
            adjustmentType.setNullSelectionAllowed(true);
            adjustmentType.addItem(GlobalConstants.getSelectOne());
            adjustmentType.setNullSelectionItemId(GlobalConstants.getSelectOne());
            return adjustmentType;
        } else if (propertyId.equals("accountCategory")) {
            ComboBox accountCategory = new ComboBox();
            accountCategory.setNullSelectionAllowed(true);
            accountCategory.addItem(GlobalConstants.getSelectOne());
            accountCategory.setNullSelectionItemId(GlobalConstants.getSelectOne());
            return accountCategory;
        } else if (propertyId.equals("accountType")) {
            ComboBox accountType = new ComboBox();
            accountType.setNullSelectionAllowed(true);
            accountType.addItem(GlobalConstants.getSelectOne());
            accountType.setNullSelectionItemId(GlobalConstants.getSelectOne());
            return accountType;
        } else if (propertyId.equals("accountIndictor")) {
            ComboBox accountIndictor = new ComboBox();
            accountIndictor.setNullSelectionAllowed(true);
            accountIndictor.addItem(GlobalConstants.getSelectOne());
            accountIndictor.setNullSelectionItemId(GlobalConstants.getSelectOne());
            return accountIndictor;
        } else if (propertyId.equals("currency")) {
            ComboBox currency = new ComboBox();
            currency.setNullSelectionAllowed(true);
            currency.addItem(GlobalConstants.getSelectOne());
            currency.setNullSelectionItemId(GlobalConstants.getSelectOne());
            return currency;
        } else if (propertyId.equals("reversalPeriodDate")) {
            ComboBox reversalPeriodDate = new ComboBox();
            reversalPeriodDate.setNullSelectionAllowed(true);
            reversalPeriodDate.addItem(GlobalConstants.getSelectOne());
            reversalPeriodDate.setNullSelectionItemId(GlobalConstants.getSelectOne());
            return reversalPeriodDate;
        } else if (propertyId.equals("UDC1")) {
            ComboBox UDC1 = new ComboBox();
            UDC1.setNullSelectionAllowed(true);
            UDC1.addItem(GlobalConstants.getSelectOne());
            UDC1.setNullSelectionItemId(GlobalConstants.getSelectOne());
            return UDC1;
        } else if (propertyId.equals("UDC2")) {
            ComboBox UDC2 = new ComboBox();
            UDC2.setNullSelectionAllowed(true);
            UDC2.addItem(GlobalConstants.getSelectOne());
            UDC2.setNullSelectionItemId(GlobalConstants.getSelectOne());
            return UDC2;
        } else if (propertyId.equals("UDC3")) {
            ComboBox UDC3 = new ComboBox();
            UDC3.setNullSelectionAllowed(true);
            UDC3.addItem(GlobalConstants.getSelectOne());
            UDC3.setNullSelectionItemId(GlobalConstants.getSelectOne());
            return UDC3;
        } else if (propertyId.equals("UDC4")) {
            ComboBox UDC4 = new ComboBox();
            UDC4.setNullSelectionAllowed(true);
            UDC4.addItem(GlobalConstants.getSelectOne());
            UDC4.setNullSelectionItemId(GlobalConstants.getSelectOne());
            return UDC4;
        } else if (propertyId.equals("UDC5")) {
            ComboBox UDC5 = new ComboBox();
            UDC5.setNullSelectionAllowed(true);
            UDC5.addItem(GlobalConstants.getSelectOne());
            UDC5.setNullSelectionItemId(GlobalConstants.getSelectOne());
            return UDC5;
        } else if (propertyId.equals("UDC6")) {
            ComboBox UDC6 = new ComboBox();
            UDC6.setNullSelectionAllowed(true);
            UDC6.addItem(GlobalConstants.getSelectOne());
            UDC6.setNullSelectionItemId(GlobalConstants.getSelectOne());
            return UDC6;
        } else {
            TextField textField = new TextField();
            return textField;
        }
    }
}
