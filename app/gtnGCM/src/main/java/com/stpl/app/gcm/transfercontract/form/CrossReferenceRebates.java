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
public class CrossReferenceRebates extends VerticalLayout {

    @UiField("toResultTableRebate")
    public TreeTable toResultTableRebate;

    @UiField("componentDetailsTable")
    public ExtFilterTable componentDetailsTable;

    @UiField("rsResults")
    public ExtFilterTable rsResults;
     
    @UiField("massField")
    public ComboBox massField;
    

    private final ExtTreeContainer<TransferToDTO> toResultContainer = new ExtTreeContainer<>(TransferToDTO.class);
    private final BeanItemContainer<ComponentDetailsDTO> componentDetailsContainer = new BeanItemContainer<>(ComponentDetailsDTO.class);
    private final BeanItemContainer<DetailsDTO> rsResultsContainer = new BeanItemContainer<>(DetailsDTO.class);

    public CrossReferenceRebates() {
        addComponent(Clara.create(getClass().getResourceAsStream("/TransferContract/crossRefRebate.xml"), this));
        configureTables();
        configureFields();
    }

    private void configureTables() {

        toResultTableRebate.setContainerDataSource(toResultContainer);
        toResultTableRebate.setVisibleColumns(HeaderUtil.getInstance().transforToColumn);
        toResultTableRebate.setColumnHeaders(HeaderUtil.getInstance().transforToHeader);

        componentDetailsTable.setContainerDataSource(componentDetailsContainer);
        componentDetailsTable.setVisibleColumns(HeaderUtil.getInstance().componentDetailsItemColumn);
        componentDetailsTable.setColumnHeaders(HeaderUtil.getInstance().componentDetailsItemHeader);

        rsResults.setContainerDataSource(rsResultsContainer);
        rsResults.setVisibleColumns(HeaderUtil.getInstance().rsDetailsColumn);
        rsResults.setColumnHeaders(HeaderUtil.getInstance().rsDetailsHeader);

    }
     public void configureFields(){
        massField.setNullSelectionAllowed(true);
        massField.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        massField.addItem(ConstantsUtils.SELECT_ONE);
        massField.select(ConstantsUtils.SELECT_ONE);
    }
}
