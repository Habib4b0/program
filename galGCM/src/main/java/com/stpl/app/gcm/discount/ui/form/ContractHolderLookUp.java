/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import com.stpl.app.gcm.discount.dto.LookupDTO;
import com.stpl.app.gcm.discount.logic.RebateTableLogic;
import com.stpl.app.gcm.util.Constants;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author vigneshkanna
 */
public class ContractHolderLookUp extends Window {

    @UiField("chTableLayout")
    public VerticalLayout chTableLayout;
    @UiField("chId")
    public TextField chId;
    @UiField("chNo")
    public TextField chNo;
    @UiField("chName")
    public TextField chName;
    @UiField("chStatus")
    public ComboBox chStatus;
    @UiField("chType")
    public ComboBox chType;

    RebateTableLogic tableLogic = new RebateTableLogic();
    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<LookupDTO> resultsContainer = new BeanItemContainer<LookupDTO>(LookupDTO.class);
    private BeanItemContainer<String> chStatusBean = new BeanItemContainer<String>(String.class);
    private BeanItemContainer<String> chTypeBean = new BeanItemContainer<String>(String.class);

    public ContractHolderLookUp() {
        setContent(Clara.create(getClass().getResourceAsStream("/discount/contractHolder.xml"), this));
        addStyleName("bootstrap-ui");
        addStyleName(Constants.bootstrap);
        addStyleName(Constants.bootstrap_forecast_bootstrap_nm);
        setClosable(true);
        setModal(true);
        configureFields();
    }

    public void configureFields() {
        chTableLayout.addComponent(resultsTable);
        resultsTable.setHeight("500px");
        resultsTable.setPageLength(5);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setEditable(true);
        resultsTable.setVisibleColumns(Constants.CH_SEARCH_COLUMNS);
        resultsTable.setColumnHeaders(Constants.CH_SEARCH_HEADERS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, 180);
        }

        chStatusBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        chStatus.setImmediate(true);
        chStatus.setContainerDataSource(chStatusBean);
        chStatus.setNullSelectionAllowed(true);
        chStatus.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        chStatus.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());

        chTypeBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        chType.setImmediate(true);
        chType.setContainerDataSource(chTypeBean);
        chType.setNullSelectionAllowed(true);
        chType.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        chType.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());

    }

}
