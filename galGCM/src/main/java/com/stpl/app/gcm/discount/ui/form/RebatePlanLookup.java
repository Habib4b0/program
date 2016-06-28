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
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.customwindow.CustomWindow;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
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
    public Button selectBtn;
    @UiField("closeBtn")
    public Button closeBtn;
    RebatePlanTableLogic tableLogic = new RebatePlanTableLogic();
    private ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    public CustomTextField componentTextField;
    private BeanItemContainer<LookupDTO> resultsContainer = new BeanItemContainer<LookupDTO>(LookupDTO.class);
    CommonUtil commonUtil = CommonUtil.getInstance();
    UiUtils UIUtils = new UiUtils();
     private static final Logger LOGGER = Logger.getLogger(RebatePlanLookup.class);

    public RebatePlanLookup(CustomTextField componentTextField) {
       super("Rebate Plan Lookup");
        setContent(Clara.create(getClass().getResourceAsStream("/discount/rebatePlanLookup.xml"), this));
        addStyleName("bootstrap-ui");
        addStyleName("valo-theme-customwindow");
        addStyleName(Constants.bootstrap);
        addStyleName(Constants.bootstrap_forecast_bootstrap_nm);
        setWidth(70, Unit.PERCENTAGE);
        this.componentTextField = componentTextField;
        setClosable(true);
        setModal(true);
        configureFields();
    }

    public void configureFields() {
        try {
            formulaTableLayout.addComponent(resultsTable);
            tableLogic.setContainerDataSource(resultsContainer);
            tableLogic.setPageLength(5);
            tableLogic.sinkItemPerPageWithPageLength(false);
            resultsTable.setPageLength(5);
            resultsTable.setSelectable(true);
            resultsTable.setFilterBarVisible(true);
            resultsTable.setContainerDataSource(resultsContainer);
            resultsTable.setVisibleColumns(Constants.REBATE_SEARCH_COLUMNS);
            resultsTable.setColumnHeaders(Constants.REBATE_SEARCH_HEADERS);
            resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(tableLogic.createControls());
            formulaTableLayout.addComponent(controls);
            rebatePlanStatus = commonUtil.loadComboBox(rebatePlanStatus, UIUtils.STATUS, false);
            rebatePlanType = commonUtil.loadComboBox(rebatePlanType, UIUtils.REBATE_PLAN_TYPE, false);
            rebatePlanType.setNullSelectionAllowed(false);
            rebatePlanStatus.setNullSelectionAllowed(false);
        } catch (Exception e) {
         LOGGER.error(e.getMessage());
        }
    }

    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) throws SystemException, PortalException, Exception {
        close();
    }

    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event) throws SystemException, PortalException, Exception {
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
    public void searchButtonLogic(Button.ClickEvent event) throws SystemException, PortalException, Exception {
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
                LOGGER.error(ex.getMessage());
                }
            }

            @Override
            public void noMethod() {

            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the values in the Rebate Plan Search?");

    }

}
