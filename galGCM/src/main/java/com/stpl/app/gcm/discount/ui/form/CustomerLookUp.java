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
public class CustomerLookUp extends Window {

    @UiField("custTableLayout")
    public VerticalLayout custTableLayout;
    @UiField("custId")
    public TextField custId;
    @UiField("custNo")
    public TextField custNo;
    @UiField("custName")
    public TextField custName;
    @UiField("custStatus")
    public ComboBox custStatus;
    @UiField("custType")
    public ComboBox custType;

    RebateTableLogic tableLogic = new RebateTableLogic();
    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<LookupDTO> resultsContainer = new BeanItemContainer<LookupDTO>(LookupDTO.class);
    private BeanItemContainer<String> custStatusBean = new BeanItemContainer<String>(String.class);
    private BeanItemContainer<String> custTypeBean = new BeanItemContainer<String>(String.class);

    public CustomerLookUp() {
        setContent(Clara.create(getClass().getResourceAsStream("/discount/cust.xml"), this));
        addStyleName("bootstrap-ui");
        addStyleName(Constants.bootstrap);
        addStyleName(Constants.bootstrap_forecast_bootstrap_nm);
        setClosable(true);
        setModal(true);
        configureFields();
    }

    public void configureFields() {
        custTableLayout.addComponent(resultsTable);
        resultsTable.setHeight("500px");
        resultsTable.setPageLength(5);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setEditable(true);
        resultsTable.setVisibleColumns(Constants.CUST_SEARCH_COLUMNS);
        resultsTable.setColumnHeaders(Constants.CUST_SEARCH_HEADERS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, 180);
        }

        custStatusBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        custStatus.setImmediate(true);
        custStatus.setContainerDataSource(custStatusBean);
        custStatus.setNullSelectionAllowed(true);
        custStatus.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        custStatus.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());

        custTypeBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        custType.setImmediate(true);
        custType.setContainerDataSource(custTypeBean);
        custType.setNullSelectionAllowed(true);
        custType.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        custType.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());

    }

}
