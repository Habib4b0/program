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
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Harlin
 */
public class CrossReferenceItems extends VerticalLayout {

    @UiField("toResultTableItem")
    public TreeTable toResultTableItem;

    @UiField("componentDetailsTable")
    public ExtFilterTable componentDetailsTable;

    @UiField("ifpResults")
    public ExtFilterTable ifpResults;
    
     @UiField("massField")
    public ComboBox massField;

    private final ExtTreeContainer<TransferToDTO> toResultContainer = new ExtTreeContainer<>(TransferToDTO.class);
    private final BeanItemContainer<ComponentDetailsDTO> componentDetailsContainer = new BeanItemContainer<>(ComponentDetailsDTO.class);
    private final BeanItemContainer<DetailsDTO> ifpResultsContainer = new BeanItemContainer<>(DetailsDTO.class);

    public CrossReferenceItems() {
        addComponent(Clara.create(getClass().getResourceAsStream("/TransferContract/crossRefItems.xml"), this));
        configureTables();
        configureFields();
    }

    private void configureTables() {

        toResultTableItem.setContainerDataSource(toResultContainer);
        toResultTableItem.setVisibleColumns(HeaderUtil.getInstance().transforToColumn);
        toResultTableItem.setColumnHeaders(HeaderUtil.getInstance().transforToHeader);

        componentDetailsTable.setContainerDataSource(componentDetailsContainer);
        componentDetailsTable.setVisibleColumns(HeaderUtil.getInstance().componentDetailsItemColumn);
        componentDetailsTable.setColumnHeaders(HeaderUtil.getInstance().componentDetailsItemHeader);

        ifpResults.setContainerDataSource(ifpResultsContainer);
        ifpResults.setVisibleColumns(HeaderUtil.getInstance().ifpDetailsColumn);
        ifpResults.setColumnHeaders(HeaderUtil.getInstance().ifpDetailsHeader);

    }
     public void configureFields(){
        massField.setNullSelectionAllowed(true);
        massField.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        massField.addItem(ConstantsUtils.SELECT_ONE);
        massField.select(ConstantsUtils.SELECT_ONE);
    }
}
