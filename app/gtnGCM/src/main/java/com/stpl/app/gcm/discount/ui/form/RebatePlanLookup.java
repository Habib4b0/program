/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.form;

import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.discount.dto.LookupDTO;
import com.stpl.app.gcm.discount.logic.RebatePlanTableLogic;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.ResponsiveUtils;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.customwindow.CustomWindow;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author santanukumar
 */
public class RebatePlanLookup extends CustomWindow {

    @UiField("formulaTableLayout")
    public VerticalLayout formulaTableLayout;
    @UiField("rebatePlanId")
    public TextField rebatePlanId;
    @UiField("rebatePlanNo")
    public TextField rebatePlanNo;
    @UiField("rebatePlanName")
    public TextField rebatePlanName;
    @UiField("rebatePlanStatus")
    public ComboBox rebatePlanStatus;
    @UiField("rebatePlanType")
    public ComboBox rebatePlanType;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("selectBtn")
    public Button selectButton;
    @UiField("closeBtn")
    public Button closeButton;
    private final RebatePlanTableLogic tableLogic = new RebatePlanTableLogic();
    private final ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    public CustomTextField componentTextField;
    private final BeanItemContainer<LookupDTO> resultsContainer = new BeanItemContainer<>(LookupDTO.class);
    private final CommonUtil commonUtil = CommonUtil.getInstance();
     private static final Logger LOGGER = LoggerFactory.getLogger(RebatePlanLookup.class);

    public RebatePlanLookup(CustomTextField componentTextField) {
       super("Rebate Plan Lookup");
        setContent(Clara.create(getClass().getResourceAsStream("/discount/rebatePlanLookup.xml"), this));
        addStyleName("bootstrap-ui");
        addStyleName("valo-theme-customwindow");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setWidth(NumericConstants.SEVENTY, Unit.PERCENTAGE);
        this.componentTextField = componentTextField;
        setClosable(true);
        setModal(true);
        configureFields();
    }

    public void configureFields() {
        try {
            formulaTableLayout.addComponent(resultsTable);
            tableLogic.setContainerDataSource(resultsContainer);
            tableLogic.setPageLength(NumericConstants.FIVE);
            tableLogic.sinkItemPerPageWithPageLength(false);
            resultsTable.setPageLength(NumericConstants.FIVE);
            resultsTable.setSelectable(true);
            resultsTable.setFilterBarVisible(true);
            resultsTable.setContainerDataSource(resultsContainer);
            resultsTable.setVisibleColumns(Constants.getInstance().rebateSearchColumns);
            resultsTable.setColumnHeaders(Constants.getInstance().rebateSearchHeaders);
            resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(tableLogic.createControls());
            formulaTableLayout.addComponent(controls);
            rebatePlanStatus = commonUtil.loadComboBox(rebatePlanStatus, UiUtils.STATUS, false);
            rebatePlanType = commonUtil.loadComboBox(rebatePlanType, UiUtils.REBATE_PLAN_TYPE, false);
            rebatePlanType.setNullSelectionAllowed(false);
            rebatePlanStatus.setNullSelectionAllowed(false);
        } catch (Exception e) {
         LOGGER.error("",e);
        }
    }

    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) {
        close();
    }

    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event){
        if (resultsTable.getValue() != null) {
            LookupDTO componentLookUp = (LookupDTO) resultsTable.getValue();
            componentTextField.setValue(componentLookUp.getRebatePlanName());
            componentTextField.setData(componentLookUp.getRebatePlanSysId());
            close();
        } else {
            MessageBox.showPlain(Icon.INFO, "Select error", "Please select a record. ", ButtonId.OK);
        }
    }

    @UiHandler("searchBtn")
    public void searchButtonLogic(Button.ClickEvent event) {
        LookupDTO searchDto = new LookupDTO();
        searchDto.setRebatePlanId(rebatePlanId.getValue());
        searchDto.setRebatePlanName(rebatePlanName.getValue());
        searchDto.setRebatePlanNo(rebatePlanNo.getValue());
        searchDto.setRebatePlanStatus(rebatePlanStatus.getValue() != null ? String.valueOf(rebatePlanStatus.getValue()) : StringUtils.EMPTY);
        searchDto.setRebatePlanType(rebatePlanType.getValue() != null ? String.valueOf(rebatePlanType.getValue()) : StringUtils.EMPTY);
        if (!tableLogic.loadSetData(searchDto)) {
            AbstractNotificationUtils.getErrorNotification("No Matching Records",
                    "There were no records matching the search criteria.  Please try again.");
        } else {
            Notification.show("Search Completed");
        }
    }

    @UiHandler("resetBtn")
    public void resetButtonLogic(Button.ClickEvent event) {

        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    resultsTable.resetFilters();
                    rebatePlanId.setValue(StringUtils.EMPTY);
                    rebatePlanNo.setValue(StringUtils.EMPTY);
                    rebatePlanName.setValue(StringUtils.EMPTY);
                    rebatePlanStatus.setValue(Constants.ZEROSTRING);
                    rebatePlanType.setValue(Constants.ZEROSTRING);
                    Notification.show("Rebate Plan view is reset");
                } catch (Exception ex) {
                LOGGER.error("",ex);
                }
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the values in the Rebate Plan Search?");

    }

}
