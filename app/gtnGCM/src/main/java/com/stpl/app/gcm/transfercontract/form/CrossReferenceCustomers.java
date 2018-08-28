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
import com.stpl.app.gcm.util.ConstantsUtils;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.TreeTable;
import com.vaadin.v7.ui.VerticalLayout;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Harlin
 */
public class CrossReferenceCustomers extends VerticalLayout {

    @UiField("toResultTableCustomer")
    public TreeTable toResultTableCustomer;

    @UiField("componentDetailsTable")
    public ExtFilterTable componentDetailsTable;

    @UiField("cfpResults")
    public ExtFilterTable cfpResults;
    
    @UiField("massField")
    public ComboBox massField;

    private final ExtTreeContainer<TransferToDTO> toResultContainer = new ExtTreeContainer<>(TransferToDTO.class);
    private final BeanItemContainer<ComponentDetailsDTO> componentDetailsContainer = new BeanItemContainer<>(ComponentDetailsDTO.class);
    private final BeanItemContainer<DetailsDTO> cfpResultsContainer = new BeanItemContainer<>(DetailsDTO.class);

    public CrossReferenceCustomers() {
        addComponent(Clara.create(getClass().getResourceAsStream("/TransferContract/crossRefCustomer.xml"), this));
        configureTables();
        configureFields();
    }

    private void configureTables() {

        toResultTableCustomer.setContainerDataSource(toResultContainer);
        toResultTableCustomer.setVisibleColumns(HeaderUtil.getInstance().TRANSFER_TO_COLUMN);
        toResultTableCustomer.setColumnHeaders(HeaderUtil.getInstance().TRANSFER_TO_HEADER);

        componentDetailsTable.setContainerDataSource(componentDetailsContainer);
        componentDetailsTable.setVisibleColumns(HeaderUtil.getInstance().COMPONENT_DETAILS_ITEM_COLUMN);
        componentDetailsTable.setColumnHeaders(HeaderUtil.getInstance().COMPONENT_DETAILS_ITEM_HEADER);

        cfpResults.setContainerDataSource(cfpResultsContainer);
        cfpResults.setVisibleColumns(HeaderUtil.getInstance().CFP_DETAILS_COLUMN);
        cfpResults.setColumnHeaders(HeaderUtil.getInstance().CFP_DETAILS_HEADER);

    }
    public final void configureFields(){
        massField.setNullSelectionAllowed(true);
        massField.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        massField.addItem(ConstantsUtils.SELECT_ONE);
        massField.select(ConstantsUtils.SELECT_ONE);
    }
}
