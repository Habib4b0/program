/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.form;

import com.stpl.app.gcm.transfercontract.dto.ComponentDetailsDTO;
import com.stpl.app.gcm.transfercontract.dto.DetailsDTO;
import com.stpl.app.gcm.transfercontract.dto.TransferToDTO;
import com.stpl.app.gcm.transfercontract.util.HeaderUtil;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.TreeTable;
import com.vaadin.v7.ui.VerticalLayout;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import com.stpl.app.gcm.util.Constants;

/**
 *
 * @author Harlin
 */
public class CrossReferencePricing extends VerticalLayout {

    @UiField("toResultTablePricing")
    public TreeTable toResultTablePricing;

    @UiField("componentDetailsTable")
    public ExtFilterTable componentDetailsTable;

    @UiField("psResults")
    public ExtFilterTable psResults;
     
    @UiField("massField")
    public ComboBox massField;

    private final ExtTreeContainer<TransferToDTO> toResultContainer = new ExtTreeContainer<>(TransferToDTO.class);
    private final BeanItemContainer<ComponentDetailsDTO> componentDetailsContainer = new BeanItemContainer<>(ComponentDetailsDTO.class);
    private final BeanItemContainer<DetailsDTO> psResultsContainer = new BeanItemContainer<>(DetailsDTO.class);

    public CrossReferencePricing() {
        addComponent(Clara.create(getClass().getResourceAsStream("/TransferContract/crossRefPricing.xml"), this));
        configureTables();
        configureFields();
    }

    private void configureTables() {

        toResultTablePricing.setContainerDataSource(toResultContainer);
        toResultTablePricing.setVisibleColumns(HeaderUtil.getInstance().transforToColumn);
        toResultTablePricing.setColumnHeaders(HeaderUtil.getInstance().transforToHeader);

        componentDetailsTable.setContainerDataSource(componentDetailsContainer);
        componentDetailsTable.setVisibleColumns(HeaderUtil.getInstance().componentDetailsItemColumn);
        componentDetailsTable.setColumnHeaders(HeaderUtil.getInstance().componentDetailsItemHeader);

        psResults.setContainerDataSource(psResultsContainer);
        psResults.setVisibleColumns(HeaderUtil.getInstance().psDetailsColumn);
        psResults.setColumnHeaders(HeaderUtil.getInstance().psDetailsHeader);

    }
     public void configureFields(){
        massField.setNullSelectionAllowed(true);
        massField.setNullSelectionItemId(Constants.SELECT_ONE);
        massField.addItem(Constants.SELECT_ONE);
        massField.select(Constants.SELECT_ONE);
    }
}
