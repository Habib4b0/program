/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.accrualrateprojection.ui.form;

import com.stpl.app.gtnforecasting.accrualrateprojection.dto.AccrualRateSelectionDTO;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.AccrualRateProjectionLogic;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.tablelogic.ExclusionDetailsTableLogic;
import static com.stpl.app.gtnforecasting.accrualrateprojection.ui.form.Sales.alertMsg;
import com.stpl.app.gtnforecasting.accrualrateprojection.utils.AccrualRateUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.drools.core.util.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Nadhiya
 */
public class ExclusionDetailsLookup extends Window {

    @UiField("moveLeftBtn")
    protected Button moveLeftBtn;

    @UiField("moveRightBtn")
    protected Button moveRightBtn;

    @UiField("allBtn")
    protected Button moveAllBtn;

    @UiField("filenameDdlb")
    protected ComboBox filenameDdlb;

    @UiField("submitBtn")
    protected Button submitBtn;

    @UiField("resetBtn")
    protected Button resetBtn;

    @UiField("closeBtn")
    protected Button closeBtn;

    @UiField("availableTableLayout")
    protected HorizontalLayout availableTableLayout;

    @UiField("selectedTableLayout")
    protected HorizontalLayout selectedTableLayout;

    private final AccrualRateProjectionLogic logic = AccrualRateProjectionLogic.getInstance();

    ExclusionDetailsTableLogic avalableTableLogic = new ExclusionDetailsTableLogic();

    ExclusionDetailsTableLogic exclusionDetailsTableLogic = new ExclusionDetailsTableLogic();

    protected ExtPagedTable availableTable = new ExtPagedTable(avalableTableLogic);

    protected ExtPagedTable excludedFieldsTable = new ExtPagedTable(exclusionDetailsTableLogic);

    private final BeanItemContainer<AccrualRateSelectionDTO> availableresultBeanContainer = new BeanItemContainer<>(AccrualRateSelectionDTO.class);

    private final AccrualRateSelectionDTO accrualRateSelectionDTO = new AccrualRateSelectionDTO();

    private final BeanItemContainer<AccrualRateSelectionDTO> excludedresultBeanContainer = new BeanItemContainer<>(AccrualRateSelectionDTO.class);

    public static final Logger LOGGER = Logger.getLogger(ExclusionDetailsLookup.class);
    
    boolean submitFlag = false;
    
    SessionDTO sessionDTO;
    

    public ExclusionDetailsLookup(SessionDTO sessionDTO) {
                super("Exclusion Details");
        this.sessionDTO = sessionDTO;
        accrualRateSelectionDTO.setUserId(sessionDTO.getUserId());
        accrualRateSelectionDTO.setSessionId(sessionDTO.getSessionId());
        accrualRateSelectionDTO.setProjectionId(String.valueOf(sessionDTO.getProjectionId()));
        accrualRateSelectionDTO.setSessionDto(sessionDTO);
        init();
        }

    public void init() {
        LOGGER.debug("Entering init ");
        try {
            center();
            setClosable(true);
            setModal(true);
            setWidth(NumericConstants.FLOAT_FIFTY_FIVE, Sizeable.Unit.PERCENTAGE);
            setContent(Clara.create(getClass().getResourceAsStream("/accrualrateprojection/exclusionDetaills.xml"), this));
            addStyleName(Constant.BOOTSTRAP_UI);
            addStyleName(Constant.BOOTSTRAP);
            addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
            availableTableLayout.addComponent(availableTable);
            selectedTableLayout.addComponent(excludedFieldsTable);
            configureAvailableTable();
            configureExcludedTable();
            configureFields();
            if ("view".equalsIgnoreCase(sessionDTO.getAction())) {
                moveLeftBtn.setEnabled(false);
                moveRightBtn.setEnabled(false);
                moveAllBtn.setEnabled(false);
                resetBtn.setEnabled(false);
                submitBtn.setEnabled(false);
            }
        } catch (Exception e) {
          LOGGER.error(e);
        }
        LOGGER.debug(" init Ends");
    }

    private void configureFields() {
        LOGGER.debug("configureFields starts");
        filenameDdlb.setImmediate(true);
        filenameDdlb.addItem(Constant.COMPANY_ID);
        filenameDdlb.setValue(Constant.COMPANY_ID);

        loadDataforAvalableTable();
        loadDataforExcludedTable();
        LOGGER.debug("configureFields ends");

    }

    @UiHandler("moveLeftBtn")
    public void moveLeftBtn(Button.ClickEvent event) {
        LOGGER.debug("moveLeftBtn button click starts");
        AccrualRateSelectionDTO selectedCompanyDto = new AccrualRateSelectionDTO();
        Object value = excludedFieldsTable.getValue();
           boolean isSelected = false;
            if (value != null) {
                 selectedCompanyDto = (AccrualRateSelectionDTO)excludedFieldsTable.getValue();
                isSelected = selectedCompanyDto!=null;
            }
        String sid = selectedCompanyDto.getCompanyMasterSid();
        if (excludedFieldsTable.size() <= 0) {
            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "There are no items to remove", new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    return;
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            return;
        } else if (!isSelected) {
            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please select an item to remove", new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    return;
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            return;
        }
       
            excludedresultBeanContainer.removeItem(selectedCompanyDto);
            logic.removeFromTempTable(sid,accrualRateSelectionDTO, true);
            List<String> selectedCompanysList = AccrualRateProjectionLogic.getSelectedCompanyList();
            if (selectedCompanysList.contains(selectedCompanyDto.getCompanyMasterSid())) {
                AccrualRateProjectionLogic.selectedCompanyList.remove(selectedCompanyDto.getCompanyMasterSid());
            }
             
        LOGGER.debug("moveLeftBtn button click ends");
        }

    @UiHandler("moveRightBtn")
    public void moveRightBtn(Button.ClickEvent event) {
        LOGGER.debug("moveRightBtn button click starts");
        AccrualRateSelectionDTO selectedCompanyDto = new AccrualRateSelectionDTO();
        Object value = availableTable.getValue();
           boolean isSelected = false;
            if (value != null) {
                 selectedCompanyDto = (AccrualRateSelectionDTO)availableTable.getValue();
                isSelected = selectedCompanyDto!=null;
            }
        String sid = String.valueOf(selectedCompanyDto.getCompanyMasterSid());
        if (availableTable.size() <= 0) {
            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "There are no items to move", new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    return;
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            return;
        } else if (!isSelected) {
            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please select an item to add", new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    return;
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            return;
        }
        List<String> selectedCompanyMasterSid = AccrualRateProjectionLogic.getSelectedCompanyList();
        if (excludedFieldsTable.size() == 0) {
            selectedCompanyMasterSid.clear();
        }
        if (!selectedCompanyMasterSid.isEmpty()) {
            if (!selectedCompanyMasterSid.contains(selectedCompanyDto.getCompanyMasterSid())) {
                logic.addToTempTable(sid,accrualRateSelectionDTO);
                loadDataforExcludedTable();
            }

        } else {
            logic.addToTempTable(sid,accrualRateSelectionDTO);
            loadDataforExcludedTable();
        }
        LOGGER.debug("moveRightBtn button click ends");
        }

    @UiHandler("allBtn")
    public void moveAllBtn(Button.ClickEvent event) {
        LOGGER.debug("moveAllBtn button click starts");
        if (excludedFieldsTable.size() != 0) {
            logic.removeFromTempTable(StringUtils.EMPTY,accrualRateSelectionDTO, false);
            logic.moveAllCompanys(accrualRateSelectionDTO);
            loadDataforExcludedTable();
        } else {
            logic.moveAllCompanys(accrualRateSelectionDTO);
            loadDataforExcludedTable();
        }
        LOGGER.debug("moveAllBtn button click ends");
    }

    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) {
        LOGGER.debug("closeBtn button click starts");
        if(!submitFlag){
            logic.removeFromTempTable(StringUtils.EMPTY, accrualRateSelectionDTO, submitFlag);
            AccrualRateProjectionLogic.selectedCompanyList.clear();
        }
        close();
        LOGGER.debug("closeBtn button click ends");
    }

    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) {
        LOGGER.debug("Reset button click starts");
        new AbstractNotificationUtils() {
            public void noMethod() {
                return;
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                LOGGER.debug("Entering resetBtn method");
                resetBtnLogic();
                LOGGER.debug("End of resetBtn method");
            }

            private void resetBtnLogic() {
                AccrualRateProjectionLogic.selectedCompanyList.clear();
                excludedFieldsTable.removeAllItems();
                logic.removeFromTempTable(StringUtils.EMPTY, accrualRateSelectionDTO, false);
               
            }

        }.getConfirmationMessage("Reset Confirmation", alertMsg.getString("EXCLUSION_DETAILS_01"));
        LOGGER.debug("Reset button click end");
    }

    @UiHandler("submitBtn")
    public void submitBtn(Button.ClickEvent event) {
        LOGGER.debug("submitBtn button click starts");
        if (excludedFieldsTable.size() == 0) {
            NotificationUtils.getErrorNotification("No Values Selected", alertMsg.getString("EXCLUSION_DETAILS_02"));
        } else {     
               close();
        }
        submitFlag = true;

        LOGGER.debug("submitBtn button click ends");
    }

    private void configureAvailableTable() {
        LOGGER.debug("configureAvailableTable starts");
        try {
            availableTable.setWidth("400px");
            availableTable.setHeight("300px");
            availableTable.setFilterBarVisible(true);
            avalableTableLogic.sinkItemPerPageWithPageLength(false);
            availableTable.setImmediate(true);
            availableTable.setSelectable(true);
            availableTable.setSortEnabled(true);
            availableTable.markAsDirty();
            avalableTableLogic.setContainerDataSource(availableresultBeanContainer);
            availableTable.setVisibleColumns(AccrualRateUtils.getInstance().availableValuesVisibleColumns);
            availableTable.setColumnHeaders(AccrualRateUtils.getInstance().availableValuesVisibleHeaders);
        } catch (Exception e) {
           LOGGER.error(e);
        }
        LOGGER.debug("configureAvailableTable ends");

    }

    private void configureExcludedTable() {
        LOGGER.debug("configureExcludedTable starts");
        excludedFieldsTable.setFilterBarVisible(true);
        excludedFieldsTable.setWidth("400px");
        excludedFieldsTable.setHeight("300px");
        excludedFieldsTable.setImmediate(true);
        exclusionDetailsTableLogic.sinkItemPerPageWithPageLength(false);
        excludedFieldsTable.setSortEnabled(true);
        excludedFieldsTable.setSelectable(true);
        excludedFieldsTable.markAsDirty();
        exclusionDetailsTableLogic.setContainerDataSource(excludedresultBeanContainer);
        excludedFieldsTable.setVisibleColumns(AccrualRateUtils.getInstance().excludedValuesVisibleColumns);
        excludedFieldsTable.setColumnHeaders(AccrualRateUtils.getInstance().excludedValuesVisibleHeaders);

        LOGGER.debug("configureExcludedTable ends");

    }

    private void loadDataforAvalableTable() {
        LOGGER.debug("loadData starts");
        avalableTableLogic.setRequiredCount(false);
        boolean isViewMode = "view".equalsIgnoreCase(sessionDTO.getAction());
        avalableTableLogic.setRequiredData(AccrualRateUtils.AVAILABLE_VALUES, accrualRateSelectionDTO, isViewMode);
        LOGGER.debug("loadData ends");
    }

    private void loadDataforExcludedTable() {
        LOGGER.debug("loadDataforAvalableTable starts");
        exclusionDetailsTableLogic.setRequiredCount(false);
        boolean isViewMode = "view".equalsIgnoreCase(sessionDTO.getAction());
        exclusionDetailsTableLogic.setRequiredData(AccrualRateUtils.EXCLUDED_VALUES, accrualRateSelectionDTO, isViewMode);
        LOGGER.debug("loadDataforAvalableTable ends");
    }
}
