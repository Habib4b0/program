/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.ui.form;

import com.stpl.app.global.abstractsearch.util.Message;
import com.stpl.app.global.abstractsearch.util.MessageUtil;
import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.deductioncalendar.dto.DeductionDetailsDTO;
import com.stpl.app.global.deductioncalendar.logic.DeductionDetailsLogic;
import com.stpl.app.global.deductioncalendar.logic.SelectionLogic;
import com.stpl.app.ui.NotesTabForm;
import com.stpl.app.ui.StplCustomComponent;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.TabNameUtil;
import com.stpl.domain.global.base.AddBaseForm;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.ui.Button;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import com.stpl.app.global.deductioncalendar.dto.DeductionCalendarDTO;
import com.stpl.app.global.deductioncalendar.dto.ItemMasterGenerate;
import com.stpl.app.global.deductioncalendar.logic.DeductionCalendarLogic;
import com.stpl.app.global.deductioncalendar.ui.util.DeductionCustomerFilerGenerator;
import com.stpl.app.global.deductioncalendar.ui.view.DeductionCalendarView;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.ui.TextField;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rohit vignesh.s
 */
public class DeductionCalendarForm extends StplCustomComponent implements AddBaseForm {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeductionCalendarForm.class);
    
    

    private final CommonUtil commonMsg = CommonUtil.getInstance();

    @UiField("tabSheet")
    private TabSheet tabSheet;

    @UiField("backBtn")
    private Button backBtn;
    /**
     * The Reset Button
     */
    @UiField("reset")
    private Button reset;

    /**
     * The company logic.
     */
    @UiField("errorMsg")
    private ErrorLabel errorMsg;

    @UiField("saveBtn")
    private Button saveBtn;

    @UiField("deductionCalendarNo")
    private TextField deductionCalendarNo;

    @UiField("deductionCalendarName")
    private TextField deductionCalendarName;

    @UiField("deductionCalendarDesc")
    private TextField deductionCalendarDesc;

    @UiField("categoryDdlb")
    private ComboBox categoryDdlb;

    private CustomerSelection customerSelection;
    private DeductionDetails deductiondetails;
    private ItemSelection itemSelection;
    private NotesTabForm notesTabForm;
    private final SessionDTO sessionDTO;
    private String tabName;
    private final SelectionLogic selectionLogic = new SelectionLogic();

    private DeductionDetailsDTO detailsDto = new DeductionDetailsDTO();
    private final DeductionDetailsLogic logic = new DeductionDetailsLogic();
    private final DeductionCalendarLogic deductionCalendarLogic = new DeductionCalendarLogic();
    private DeductionCalendarDTO dto = new DeductionCalendarDTO();

    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(dto));
    /**
     * variable to check whether details tab is seek for refresh
     */
    private boolean needRefresh = true;

    public DeductionCalendarForm(final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        this.sessionDTO = sessionDTO;
        init();
        binder = getBinder();
    }

    public DeductionCalendarForm(final SessionDTO sessionDTO, final ErrorfulFieldGroup binder) throws SystemException, PortalException {
        super();
        this.sessionDTO = sessionDTO;
        this.binder = binder;
        init();
        getEditBinder();
        if ((ConstantsUtils.COPY).equals(sessionDTO.getMode())) {
            dto = deductionCalendarLogic.getDeductionCalendarByIdForCopy(sessionDTO.getSystemId(), dto);
            deductionCalendarNo.setValue(StringUtils.EMPTY);
            deductionCalendarName.setValue(StringUtils.EMPTY);
            deductionCalendarDesc.setValue(StringUtils.EMPTY);
            categoryDdlb.select(dto.getCategoryDdlb());
        }
        if (sessionDTO.getMode().equals("view")) {
            binder.getField("deductionCalendarNo").setReadOnly(true);
            binder.getField("deductionCalendarName").setReadOnly(true);
            binder.getField("deductionCalendarDesc").setReadOnly(true);
            binder.getField("categoryDdlb").setReadOnly(true);
        }
    }

    private ErrorfulFieldGroup getEditBinder() {
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        return binder;
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        return binder;
    }

    @Override
    public void addLogic() {
        return;
    }

    @Override
    public void init() throws SystemException, PortalException {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/deduction_calendar/deductionCalendarTab.xml"), this));
        addToContent();
        configureFields();
    }

    @Override
    public void configureFields() {
        try {
            commonMsg.loadComboBox(categoryDdlb, "DEDUCTION_CALENDAR_CATEGORY", false);
            deductionCalendarNo.setImmediate(true);
            deductionCalendarName.setImmediate(true);
            deductionCalendarDesc.setImmediate(true);
            categoryDdlb.setImmediate(true);
            backButton();
            saveButton();
            resetButton();
            if (sessionDTO.getMode().equals("view")) {
                saveBtn.setVisible(false);
                reset.setVisible(false);
                if ("RS".equalsIgnoreCase(sessionDTO.getFlagForDeduction())) {
                    backBtn.setVisible(false);
                }
            }
            if (sessionDTO.getMode().equals("Edit")) {
                saveBtn.setCaption("UPDATE");
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @Override
    public void addToContent() throws PortalException, SystemException {
        addTabSheet();
    }

    private void addTabSheet() {
        try {
            tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
            tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
            customerSelection = new CustomerSelection(sessionDTO, this);
            customerSelection.setCaption(TabNameUtil.CUSTOMER_SELECTION);
            tabSheet.addTab(customerSelection, TabNameUtil.CUSTOMER_SELECTION);
            itemSelection = new ItemSelection(sessionDTO, this);
            itemSelection.setCaption(TabNameUtil.ITEM_SELECTION);
            tabSheet.addTab(itemSelection, TabNameUtil.ITEM_SELECTION);
            deductiondetails = new DeductionDetails(sessionDTO);
            deductiondetails.setCaption(TabNameUtil.DEDUCTION_DETAILS);
            tabSheet.addTab(deductiondetails, TabNameUtil.DEDUCTION_DETAILS);
            notesTabForm = new NotesTabForm(binder, "Deduction Calendar", "DEDUCTION_CALENDAR_MASTER", String.valueOf(sessionDTO.getSystemId()), "view".equals(sessionDTO.getMode()) ? "view" : sessionDTO.getMode());
            notesTabForm.setCaption(TabNameUtil.ADDITIONAL_INFO);
            if (!"Add".equals(sessionDTO.getMode())) {
                notesTabForm.readOnlyNotesHistory(false);
                notesTabForm.setNotesHistoryValue(sessionDTO.getAdditionalNotes());
                notesTabForm.readOnlyNotesHistory(true);
            }
            tabSheet.addTab(notesTabForm, TabNameUtil.ADDITIONAL_INFO, null);

            final String mode = sessionDTO.getMode();

            tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {

                @Override
                public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                    tabName = event.getTabSheet().getSelectedTab().getCaption();
                    if (TabNameUtil.DEDUCTION_DETAILS.equals(tabName)) {
                        if (isNeedRefresh()) {
                            if ("Add".equals(sessionDTO.getMode())) {
                                detailsDto = logic.getForecastConfig(detailsDto);
                                selectionLogic.saveToTempDeductionDetails(sessionDTO, detailsDto);
                                deductiondetails.loadFilterDdlb();
                                setNeedRefresh(false);
                            } else {
                                detailsDto = logic.getForecastConfig(detailsDto);
                                selectionLogic.saveToTempDeductionDetails(sessionDTO, detailsDto);
                                deductiondetails.loadFilterDdlb();
                                deductiondetails.loadDetailsOnTabChange();
                                detailsDto.setGenerated((deductionCalendarLogic.itemAndCompanySelectionCheck(sessionDTO, BooleanConstant.getTrueFlag())) && (deductionCalendarLogic.itemAndCompanySelectionCheck(sessionDTO, BooleanConstant.getFalseFlag())));
                                setNeedRefresh(false);
                            }
                        }
                    } else if (TabNameUtil.ADDITIONAL_INFO.equals(tabName)) {
                        notesTabForm.refreshTable();
                    }
                }

            });
            if ((ConstantsUtils.EDIT).equals(mode) || (ConstantsUtils.VIEW).equals(mode) || (ConstantsUtils.COPY).equals(mode)) {
                deductionCalendarLogic.sameToTemp(sessionDTO, sessionDTO.getSystemId());
            }
            if ((ConstantsUtils.EDIT).equals(mode) || (ConstantsUtils.VIEW).equals(mode) || (ConstantsUtils.COPY).equals(mode)) {
                customerSelection.loadInEdit(sessionDTO);
                itemSelection.loadInEdit();
                notesTabForm.setNotesHistoryValue(sessionDTO.getAdditionalNotes());
            }
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    private void backButton() {

        backBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        backBtn.addClickListener(new ClickListener() {
            /**
             * Listener is called when an Button is clicked
             *
             */
            @Override
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering Deduction Calendar back button operation from Add");
                try {
                    MessageBox.showPlain(Icon.QUESTION, "Confirmation", commonMsg.getBackMessage(), new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                LOGGER.debug("NavigateTo AbstractSearchView page");
                                AbstractSearchView.setFlag(false); 
                                deductionCalendarLogic.deleteTempSeletionTable(sessionDTO);
                                deductionCalendarLogic.deleteTempDeductionDetails(sessionDTO);
                                getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                } catch (Exception exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1009), new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing    
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    LOGGER.error(exception.getMessage());
                }
                LOGGER.debug("Ending Deduction Calendar back button operation from Add");
            }
        });
    }

    private void saveButton() {
        saveBtn.setWidth(ConstantsUtils.BTN_WIDTH);
        saveBtn.addClickListener(new ClickListener() {
            /**
             * Listener is called when an Button is clicked
             *
             */
            @Override
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering Deduction Calendar Save button operation from Add");
                try {
                    binder.commit();
                    detailsDto = logic.getForecastConfig(detailsDto);
                    final DeductionCalendarDTO deductionCalendarDTO = getBeanFromId(binder.getItemDataSource());
                    boolean flag = false;
                    StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + ConstantsUtils.BREAK);
                    if (binder.getField(ConstantsUtils.DEDUCTION_CALENDAR_NO).getValue().toString().trim().isEmpty()) {
                        if (flag) {
                            errorMessage.append(',');
                        }
                        errorMessage.append("DEDUCTION CALENDAR NO");
                        flag = true;
                    }
                    if (binder.getField(ConstantsUtils.DEDUCTION_CALENDAR_NAME).getValue().toString().trim().isEmpty()) {
                        if (flag) {
                            errorMessage.append(',');
                        }
                        errorMessage.append("DEDUCTION CALENDAR NAME");
                        flag = true;
                    }
                    if (!deductionCalendarLogic.itemAndCompanySelectionCheck(sessionDTO, BooleanConstant.getFalseFlag())) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.BREAK);
                        }
                        errorMessage.append("Please Add Customer in Customer Selection Tab");
                        flag = true;
                    }

                    if (!deductionCalendarLogic.itemAndCompanySelectionCheck(sessionDTO, BooleanConstant.getTrueFlag())) {
                        if (flag) {
                            errorMessage.append(ConstantsUtils.BREAK);
                        }
                        errorMessage.append("Please Add Item in Item Selection Tab");
                        flag = true;

                    }
                    if (sessionDTO.getMode().equalsIgnoreCase("Add")) {
                        if (deductiondetails.getResultBeanContainer().size() == 0) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.BREAK);
                            }
                            errorMessage.append("Please Generate ListView In Deduction Details Tab");
                            flag = true;
                        }
                    }
                    if (!sessionDTO.getMode().equalsIgnoreCase("Edit")) {
                        if (deductionCalendarLogic.deductionNoAndNameDuplicateCheck(deductionCalendarDTO.getDeductionCalendarNo(), BooleanConstant.getFalseFlag(), sessionDTO)) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.BREAK);
                            }
                            errorMessage.append("Deduction Calendar No already exists.");
                            flag = true;
                        }
                        if (deductionCalendarLogic.deductionNoAndNameDuplicateCheck(deductionCalendarDTO.getDeductionCalendarName(), BooleanConstant.getTrueFlag(), sessionDTO)) {
                            if (flag) {
                                errorMessage.append(ConstantsUtils.BREAK);
                            }
                            errorMessage.append("Deduction Calendar Name already exists.");
                            flag = true;
                        }
                    }
                    if (flag) {
                        binder.getErrorDisplay().setError(errorMessage.toString());
                        return;
                    }

                    MessageBox.showPlain(Icon.QUESTION, "Confirmation", commonMsg.getSaveMessage(binder.getField(ConstantsUtils.DEDUCTION_CALENDAR_NO).getValue().toString()), new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                saveButtonyesMethod(deductionCalendarDTO);
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                } catch (FieldGroup.CommitException exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1003), new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing    
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    LOGGER.error(exception.getMessage());
                }
                LOGGER.debug("Ending Deduction Calendar Save button operation from Add");

            }
        });
        backBtn.setErrorHandler(new ErrorHandler() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * Back button UI error handler
             */
            @Override
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(String.valueOf(event));
            }
        });
    }

    public DeductionCalendarDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof DeductionCalendarDTO) {
            targetItem = new BeanItem<>(
                    (DeductionCalendarDTO) obj);
        }
        return (DeductionCalendarDTO) targetItem.getBean();
    }

    /**
     * Reset Button
     */
    private void resetButton() {
        reset.addClickListener(new Button.ClickListener() {
            /**
             * Adds the button click listener.
             *
             */
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                String errorMessage = MessageUtil.getMessage(Message.DC_RESET_ALL_MESSAGE);
                MessageBox.showPlain(Icon.QUESTION, MessageUtil.getMessage(Message.DC_RESET_ALL_HEADER), errorMessage, new MessageBoxListener() {
                    /**
                     * Click event. This event is thrown, when the button is
                     * clicked.
                     *
                     */
                    @SuppressWarnings("PMD")
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            LOGGER.debug("Entering reset Button in Deduction Calendar Form");
                            resetButtonClickLogic();
                            LOGGER.debug("Ending reset Button in Deduction Calendar Form");
                        }
                    }

                    private void resetButtonClickLogic() {
                        final String mode = sessionDTO.getMode();

                        DeductionCalendarDTO sessionDto = new DeductionCalendarDTO();
                        if (!((ConstantsUtils.COPY).equals(mode) || (ConstantsUtils.ADD).equals(mode))) {
                            sessionDto = deductionCalendarLogic.getDeductionCalendarById(sessionDTO.getSystemId(), sessionDto);
                        }
                        if ((ConstantsUtils.COPY).equals(mode)) {
                            sessionDto = deductionCalendarLogic.getDeductionCalendarByIdForCopy(sessionDTO.getSystemId(), sessionDto);
                        }

                        deductionCalendarNo.setValue(sessionDto.getDeductionCalendarNo());
                        deductionCalendarName.setValue(sessionDto.getDeductionCalendarName());
                        deductionCalendarDesc.setValue(sessionDto.getDeductionCalendarDesc());
                        categoryDdlb.setValue(sessionDto.getCategoryDdlb());
                        if (TabNameUtil.CUSTOMER_SELECTION.equals(tabSheet.getSelectedTab().getCaption())) {
                            customerSelection.selectedCustomersTable.setFilterDecorator(new ExtDemoFilterDecorator());
                            customerSelection.selectedCustomersTable.setFilterGenerator(new DeductionCustomerFilerGenerator());
                            customerSelection.resetButtonClickLogic();
                            customerSelection.selectedResultsContainer.removeAllItems();
                            selectionLogic.resetCompanyAndItem(sessionDTO, BooleanConstant.getFalseFlag());
                            if ((ConstantsUtils.EDIT).equals(mode) || (ConstantsUtils.COPY).equals(mode)) {
                                deductionCalendarLogic.deleteCustomer_TempDeductionDetails(sessionDTO);
                                deductionCalendarLogic.insertToTempSelectionForCust(sessionDTO.getUserId(), sessionDTO.getUiSessionId(), sessionDTO.getSystemId());
                                customerSelection.loadInEdit(sessionDTO);
                            }
                        } else if (TabNameUtil.ITEM_SELECTION.equals(tabSheet.getSelectedTab().getCaption())) {
                            itemSelection.availableResultsContainer.removeAllItems();
                            itemSelection.selectedResultsContainer.removeAllItems();
                            itemSelection.selectedItemTable.setFilterGenerator(new ItemMasterGenerate());
                            itemSelection.selectedItemTable.setFilterDecorator(new ExtDemoFilterDecorator());
                            itemSelection.resetBtnLogic();
                            selectionLogic.resetCompanyAndItem(sessionDTO, BooleanConstant.getTrueFlag());
                            if ((ConstantsUtils.EDIT).equals(mode) || (ConstantsUtils.COPY).equals(mode)) {
                                deductionCalendarLogic.deleteItem_TempDeductionDetails(sessionDTO);
                                deductionCalendarLogic.insertToTempSelectionForProd(sessionDTO.getUserId(), sessionDTO.getUiSessionId(), sessionDTO.getSystemId());
                                itemSelection.loadInEdit();
                            }
                        } else if (TabNameUtil.DEDUCTION_DETAILS.equals(tabSheet.getSelectedTab().getCaption())) {
                            deductiondetails.resetFields();
                        } else if (TabNameUtil.ADDITIONAL_INFO.equals(tabSheet.getSelectedTab().getCaption())) {
                            if (!mode.equals("Add")) {
                                notesTabForm.resetBtnLogic(sessionDto.getInternalNotes());
                            } else {
                                notesTabForm.resetAddMode();
                            }
                        }
                    }

                }, ButtonId.YES, ButtonId.NO);
            }
        });
    }

    public boolean isNeedRefresh() {
        return needRefresh;
    }

    public void setNeedRefresh(boolean needRefresh) {
        this.needRefresh = needRefresh;
    }

    public void saveButtonyesMethod(final DeductionCalendarDTO deductionCalendarDTO) {
        try {
            String msg;
            msg = deductionCalendarLogic.saveDeductionCalendarMaster(deductionCalendarDTO, sessionDTO, notesTabForm.getUploadedData(), notesTabForm.getAddedNotes(), notesTabForm.removeDetailsList());
            deductionCalendarLogic.saveDeductionDetails(sessionDTO);
            if (msg.equals(ConstantsUtils.SUCCESS)) {
                
                final Notification notif = new Notification(commonMsg.getSavedSuccessfulMessage(String.valueOf(binder.getField(ConstantsUtils.DEDUCTION_CALENDAR_NO).getValue()), String.valueOf(binder.getField(ConstantsUtils.DEDUCTION_CALENDAR_NAME).getValue())), Notification.Type.HUMANIZED_MESSAGE);
                notif.setPosition(Position.MIDDLE_CENTER);
                notif.setStyleName(ConstantsUtils.MY_STYLE);
                notif.show(Page.getCurrent());
                notif.setDelayMsec(NumericConstants.TWO_THOUSAND);
                sessionDTO.setMode(ConstantsUtils.EDIT);
                saveBtn.setCaption("UPDATE");
                getUI().getNavigator().navigateTo(DeductionCalendarView.NAME);
            }

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
