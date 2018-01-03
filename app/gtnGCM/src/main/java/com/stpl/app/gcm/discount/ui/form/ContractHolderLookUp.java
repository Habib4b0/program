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

    private final RebateTableLogic tableLogic = new RebateTableLogic();
    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    private final BeanItemContainer<LookupDTO> resultsContainer = new BeanItemContainer<>(LookupDTO.class);
    private final BeanItemContainer<String> chStatusBean = new BeanItemContainer<>(String.class);
    private final BeanItemContainer<String> chTypeBean = new BeanItemContainer<>(String.class);

    public ContractHolderLookUp() {
        setContent(Clara.create(getClass().getResourceAsStream("/discount/contractHolder.xml"), this));
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(true);
        setModal(true);
        configureFields();
    }

    public void configureFields() {
        chTableLayout.addComponent(resultsTable);
        resultsTable.setHeight("500px");
        resultsTable.setPageLength(NumericConstants.FIVE);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setContainerDataSource(resultsContainer);
        resultsTable.setEditable(true);
        resultsTable.setVisibleColumns(Constants.getInstance().chSearchColumns);
        resultsTable.setColumnHeaders(Constants.getInstance().chSearchHeaders);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        for (Object propertyId : resultsTable.getVisibleColumns()) {
            resultsTable.setColumnWidth(propertyId, NumericConstants.ONE_EIGHT_ZERO);
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
