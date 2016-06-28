/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.form;

import com.stpl.app.util.ConstantsUtils;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Harlin
 */
public class CrossReferenceForm extends VerticalLayout {

    @UiField("crossRefTab")
    public TabSheet crossRefTab;
    @UiField("marketType")
    public ComboBox marketType;

    public CrossReferenceForm() {
        addComponent(Clara.create(getClass().getResourceAsStream("/TransferContract/crossReferenceForm.xml"), this));
        addTab();
        configureFields();
    }

    /**
     * Adds the tab.
     *
     * @return the component
     */
    private void addTab() {
        crossRefTab.addStyleName(ValoTheme.TABSHEET_FRAMED);
        crossRefTab.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        crossRefTab.setImmediate(true);
        crossRefTab.markAsDirty();
        crossRefTab.markAsDirtyRecursive();
        crossRefTab.addTab(new CrossReferenceCustomers(), "Customers", null, 0);
        crossRefTab.addTab(new CrossReferenceItems(), "Items", null, 1);
        crossRefTab.addTab(new CrossReferencePricing(), "Pricing", null, 2);
        crossRefTab.addTab(new CrossReferenceRebates(), "Rebate", null, 3);
    }
     private void configureFields() {
         marketType.setNullSelectionAllowed(true);
         marketType.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
         marketType.addItem(ConstantsUtils.SELECT_ONE);
         marketType.select(ConstantsUtils.SELECT_ONE);
     }
}
