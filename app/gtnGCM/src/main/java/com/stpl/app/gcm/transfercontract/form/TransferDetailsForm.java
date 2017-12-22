/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.form;

import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.TabSheet;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Harlin
 */
public class TransferDetailsForm extends VerticalLayout {

    @UiField("transferDetailsTab")
    public TabSheet transferDetailsTab;
     @UiField("marketType")
    public ComboBox marketType;
    

    public TransferDetailsForm() {
        addComponent(Clara.create(getClass().getResourceAsStream("/TransferContract/transferDetailsForm.xml"), this));
        addTab();
        configureFields();
    }

    /**
     * Adds the tab.
     *
     * @return the component
     */
    private void addTab() {
        transferDetailsTab.addStyleName(ValoTheme.TABSHEET_FRAMED);
        transferDetailsTab.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        transferDetailsTab.markAsDirty();
        transferDetailsTab.markAsDirtyRecursive();
        transferDetailsTab.addTab(new TransferDetailsSales(), "Sales", null, 0);
        transferDetailsTab.addTab(new TransferDetailsRebate(), "Rebate", null, 1);
        transferDetailsTab.addTab(new TransferDetailsSalesRebate(), "Sales & Rebate", null, NumericConstants.TWO);
    }
    private void configureFields() {
         marketType.setNullSelectionAllowed(true);
         marketType.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
         marketType.addItem(ConstantsUtils.SELECT_ONE);
         marketType.select(ConstantsUtils.SELECT_ONE);
     }
}
