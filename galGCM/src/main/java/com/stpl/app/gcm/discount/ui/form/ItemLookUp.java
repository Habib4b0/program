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
public class ItemLookUp extends Window {

    @UiField("itemTableLayout")
    public VerticalLayout itemTableLayout;
    @UiField("itemId")
    public TextField itemId;
    @UiField("itemNo")
    public TextField itemNo;
    @UiField("itemName")
    public TextField itemName;
    @UiField("itemStatus")
    public ComboBox itemStatus;
    @UiField("itemType")
    public ComboBox itemType;

    RebateTableLogic tableLogic = new RebateTableLogic();
    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<LookupDTO> resultsContainer = new BeanItemContainer<LookupDTO>(LookupDTO.class);
    private BeanItemContainer<String> itemStatusBean = new BeanItemContainer<String>(String.class);
    private BeanItemContainer<String> itemTypeBean = new BeanItemContainer<String>(String.class);

    public ItemLookUp() {
        setContent(Clara.create(getClass().getResourceAsStream("/discount/item.xml"), this));
        addStyleName("bootstrap-ui");
        addStyleName(Constants.bootstrap);
        addStyleName(Constants.bootstrap_forecast_bootstrap_nm);
        setClosable(true);
        setModal(true);
        configureFields();
    }

    public void configureFields() {
        itemTableLayout.addComponent(resultsTable);
        resultsTable.setHeight("500px");
        resultsTable.setPageLength(5);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setEditable(true);
        resultsTable.setVisibleColumns(Constants.ITEM_SEARCH_COLUMNS);
        resultsTable.setColumnHeaders(Constants.ITEM_SEARCH_HEADERS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, 180);
        }

        itemStatusBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        itemStatus.setImmediate(true);
        itemStatus.setContainerDataSource(itemStatusBean);
        itemStatus.setNullSelectionAllowed(true);
        itemStatus.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        itemStatus.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());

        itemTypeBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        itemType.setImmediate(true);
        itemType.setContainerDataSource(itemTypeBean);
        itemType.setNullSelectionAllowed(true);
        itemType.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        itemType.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());

    }

}
