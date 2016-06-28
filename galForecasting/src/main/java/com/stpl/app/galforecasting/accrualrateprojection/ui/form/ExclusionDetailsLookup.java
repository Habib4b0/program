/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.accrualrateprojection.ui.form;

import com.stpl.app.galforecasting.accrualrateprojection.dto.AccrualRateSelectionDTO;
import com.stpl.app.galforecasting.accrualrateprojection.logic.AccrualRateProjectionLogic;
import com.stpl.app.galforecasting.accrualrateprojection.logic.tablelogic.ExclusionDetailsTableLogic;
import static com.stpl.app.galforecasting.accrualrateprojection.ui.form.Sales.alertMsg;
import com.stpl.app.galforecasting.accrualrateprojection.utils.AccrualRateUtils;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.NotificationUtils;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;
import java.util.Map;
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

    private final AccrualRateProjectionLogic logic = new AccrualRateProjectionLogic();

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
        init();
    }

    public void init() {
        LOGGER.info("Entering init ");
        try {
            center();
            setClosable(true);
            setModal(true);
            setWidth(55f, Sizeable.Unit.PERCENTAGE);
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
        LOGGER.info(" init Ends");
    }

    private void configureFields() {
        LOGGER.info("configureFields starts");
        filenameDdlb.setImmediate(true);
        filenameDdlb.addItem(Constant.COMPANY_ID);
        filenameDdlb.setValue(Constant.COMPANY_ID);

        loadDataforAvalableTable();
        loadDataforExcludedTable();
        LOGGER.info("configureFields ends");

    }

    @UiHandler("moveLeftBtn")
    public void moveLeftBtn(Button.ClickEvent event) {
        LOGGER.info("moveLeftBtn button click starts");
        AccrualRateSelectionDTO selectedCompanyDto = (AccrualRateSelectionDTO) excludedFieldsTable.getValue();
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
                   
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            return;
        } else if (selectedCompanyDto == null) {
            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please select an item to remove", new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                  
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            return;
        }
        if (selectedCompanyDto != null) {
            excludedresultBeanContainer.removeItem(selectedCompanyDto);
            logic.removeFromTempTable(sid,accrualRateSelectionDTO, true);
            List<String> selectedCompanysList = AccrualRateProjectionLogic.getSelectedCompanyList();
            if (selectedCompanysList.contains(selectedCompanyDto.getCompanyMasterSid())) {
                AccrualRateProjectionLogic.selectedCompanyList.remove(selectedCompanyDto.getCompanyMasterSid());
            }
        }        
        LOGGER.info("moveLeftBtn button click ends");
    }

    @UiHandler("moveRightBtn")
    public void moveRightBtn(Button.ClickEvent event) {
        LOGGER.info("moveRightBtn button click starts");
        AccrualRateSelectionDTO selectedCompanyDto = (AccrualRateSelectionDTO) availableTable.getValue();
        String sid = selectedCompanyDto.getCompanyMasterSid();
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
                   
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            return;
        } else if (selectedCompanyDto == null) {
            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please select an item to add", new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                  
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            return;
        }
        List<String> selectedCompanyMasterSid = AccrualRateProjectionLogic.getSelectedCompanyList();
        if (!selectedCompanyMasterSid.isEmpty()) {
            if (!selectedCompanyMasterSid.contains(selectedCompanyDto.getCompanyMasterSid())) {
                logic.addToTempTable(sid,accrualRateSelectionDTO);
                loadDataforExcludedTable();
            }

        } else {
            logic.addToTempTable(sid,accrualRateSelectionDTO);
            loadDataforExcludedTable();
        }
        LOGGER.info("moveRightBtn button click ends");
    }

    @UiHandler("allBtn")
    public void moveAllBtn(Button.ClickEvent event) {
        LOGGER.info("moveAllBtn button click starts");
        if (excludedFieldsTable.size() != 0) {
            logic.removeFromTempTable(StringUtils.EMPTY,accrualRateSelectionDTO, false);
            logic.moveAllCompanys(accrualRateSelectionDTO);
            loadDataforExcludedTable();
        } else {
            logic.moveAllCompanys(accrualRateSelectionDTO);
            loadDataforExcludedTable();
        }
        LOGGER.info("moveAllBtn button click ends");
    }

    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) {
        LOGGER.info("closeBtn button click starts");
        if(!submitFlag){
            logic.removeFromTempTable(StringUtils.EMPTY, accrualRateSelectionDTO, submitFlag);
            AccrualRateProjectionLogic.selectedCompanyList.clear();
        }
        close();
        LOGGER.info("closeBtn button click ends");
    }

    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) {
        LOGGER.info("Reset button click starts");
        new AbstractNotificationUtils() {
            public void noMethod() {
                
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                LOGGER.info("Entering resetBtn method");
                resetBtnLogic();
                LOGGER.info("End of resetBtn method");
            }

            private void resetBtnLogic() {
                AccrualRateProjectionLogic.selectedCompanyList.clear();
                excludedFieldsTable.removeAllItems();
                logic.removeFromTempTable(StringUtils.EMPTY, accrualRateSelectionDTO, false);
               
            }

        }.getConfirmationMessage("Reset Confirmation", alertMsg.getString("EXCLUSION_DETAILS_01"));
        LOGGER.info("Reset button click end");
    }

    @UiHandler("submitBtn")
    public void submitBtn(Button.ClickEvent event) {
        LOGGER.info("submitBtn button click starts");
        if (excludedFieldsTable.size() == 0) {
            NotificationUtils.getErrorNotification("No Values Selected", alertMsg.getString("EXCLUSION_DETAILS_02"));
        } else {     
               close();
        }
        submitFlag = true;

        LOGGER.info("submitBtn button click ends");
    }

    private void configureAvailableTable() {
        LOGGER.info("configureAvailableTable starts");
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
            availableTable.setVisibleColumns(AccrualRateUtils.AVAILABLE_VALUES_VISIBLE_COLUMNS);
            availableTable.setColumnHeaders(AccrualRateUtils.AVAILABLE_VALUES_VISIBLE_HEADERS);
        } catch (Exception e) {
           LOGGER.error(e);
        }
        LOGGER.info("configureAvailableTable ends");

    }

    private void configureExcludedTable() {
        LOGGER.info("configureExcludedTable starts");
        excludedFieldsTable.setFilterBarVisible(true);
        excludedFieldsTable.setWidth("400px");
        excludedFieldsTable.setHeight("300px");
        excludedFieldsTable.setImmediate(true);
        exclusionDetailsTableLogic.sinkItemPerPageWithPageLength(false);
        excludedFieldsTable.setSortEnabled(true);
        excludedFieldsTable.setSelectable(true);
        excludedFieldsTable.markAsDirty();
        exclusionDetailsTableLogic.setContainerDataSource(excludedresultBeanContainer);
        excludedFieldsTable.setVisibleColumns(AccrualRateUtils.EXCLUDED_VALUES_VISIBLE_COLUMNS);
        excludedFieldsTable.setColumnHeaders(AccrualRateUtils.EXCLUDED_VALUES_VISIBLE_HEADERS);

        LOGGER.info("configureExcludedTable ends");

    }

    private void loadDataforAvalableTable() {
        LOGGER.info("loadData starts");
        avalableTableLogic.setRequiredCount(false);
        avalableTableLogic.setRequiredData(AccrualRateUtils.AVAILABLE_VALUES, accrualRateSelectionDTO);
        LOGGER.info("loadData ends");
    }

    private void loadDataforExcludedTable() {
        LOGGER.info("loadDataforAvalableTable starts");
        exclusionDetailsTableLogic.setRequiredCount(false);
        exclusionDetailsTableLogic.setRequiredData(AccrualRateUtils.EXCLUDED_VALUES, accrualRateSelectionDTO);
        LOGGER.info("loadDataforAvalableTable ends");
    }
}
