/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import com.stpl.app.gcm.discount.dto.LookupDTO;
import com.stpl.app.gcm.discount.logic.RebateTableLogic;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
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
    private BeanItemContainer<LookupDTO> resultsContainer = new BeanItemContainer<>(LookupDTO.class);
    private BeanItemContainer<String> itemStatusBean = new BeanItemContainer<>(String.class);
    private BeanItemContainer<String> itemTypeBean = new BeanItemContainer<>(String.class);

    public ItemLookUp() {
        setContent(Clara.create(getClass().getResourceAsStream("/discount/item.xml"), this));
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(true);
        setModal(true);
        configureFields();
    }

    public void configureFields() {
        itemTableLayout.addComponent(resultsTable);
        resultsTable.setHeight("500px");
        resultsTable.setPageLength(NumericConstants.FIVE);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setEditable(true);
        resultsTable.setVisibleColumns(Constants.getInstance().itemSearchColumns);
        resultsTable.setColumnHeaders(Constants.getInstance().itemSearchHeaders);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, NumericConstants.ONE_EIGHT_ZERO);
        }

        itemStatusBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        itemStatus.setContainerDataSource(itemStatusBean);
        itemStatus.setNullSelectionAllowed(true);
        itemStatus.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        itemStatus.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());

        itemTypeBean.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        itemType.setContainerDataSource(itemTypeBean);
        itemType.setNullSelectionAllowed(true);
        itemType.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        itemType.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());

    }

}
