/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.ui.form;

import com.stpl.app.global.abstractsearch.util.CommonUtils;
import com.stpl.app.global.abstractsearch.util.Message;
import com.stpl.app.global.abstractsearch.util.MessageUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.deductioncalendar.dto.SelectionDTO;
import com.stpl.app.global.deductioncalendar.logic.SelectionLogic;
import com.stpl.app.global.deductioncalendar.logic.tablelogic.SelectionTableLogic;
import com.stpl.app.global.deductioncalendar.ui.util.DeductionCustomerFilerGenerator;
import com.stpl.app.global.deductioncalendar.ui.util.HeaderUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.serviceUtils.ConstantUtil;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ExcelExportUtil;
import static com.stpl.app.util.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.app.util.TabNameUtil;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Arrays;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author sibi
 */
public class CustomerSelection extends CustomComponent {

    private static final Logger LOGGER = Logger.getLogger(CustomerSelection.class);
    CommonUtil commonMsg = CommonUtil.getInstance();
    
    @UiField("availResultsTableLayout")
    protected VerticalLayout availResultsTableLayout;
    @UiField("selectedResultsTableLayout")
    protected VerticalLayout selectedResultsTableLayout;
    
    @UiField("customerSearchPanel")
    protected Panel customerSearchPanel;
    
    @UiField("searchHLayout")
    protected HorizontalLayout searchHLayout;
    
    @UiField("includeButtonLayout")
    protected HorizontalLayout includeButtonLayout;
    
    @UiField("availableResultsTablePanel")
    protected Panel availableResultsTablePanel;
    
    @UiField("excludeButtonLayout")
    protected HorizontalLayout excludeButtonLayout;
    
    @UiField("selectedResultsTablePanel")
    protected Panel selectedResultsTablePanel;
    
    @UiField("cssLayout2")
    protected CssLayout cssLayout2;
    /**
     * Bean container for available result table.
     */
    protected final BeanItemContainer<SelectionDTO> availableResultsContainer = new BeanItemContainer<>(SelectionDTO.class);
    /**
     * Bean container for Selected result table.
     */
    protected BeanItemContainer<SelectionDTO> selectedResultsContainer = new BeanItemContainer<>(SelectionDTO.class);
    /**
     * Selection Table Logic
     */
    SelectionTableLogic availableTableLogic = new SelectionTableLogic();
    
    SelectionTableLogic selectionTableLogic = new SelectionTableLogic();
    /**
     * Available Customer ExtPagedTable
     */
    ExtPagedTable availableCustomersTable = new ExtPagedTable(availableTableLogic);
    /**
     * Selected Customer ExtPagedTable
     */
    ExtPagedTable selectedCustomersTable = new ExtPagedTable(selectionTableLogic);

    @UiField("customerNo")
    private TextField customerNo;
    
    @UiField("customerName")
    private TextField customerName;
    
    @UiField("tradeClassDdlb")
    private ComboBox tradeClass;
    
    @UiField("customerTypeDdlb")
    private ComboBox customerType;
    
    @UiField("customerStatusDdlb")
    private ComboBox customerStatus;
    
    @UiField("city")
    private TextField city;
    
    @UiField("state")
    private TextField state;
    
    @UiField("zipCode")
    private TextField zipcode;
    
    @UiField("searchBtn")
    private Button searchBtn;
    
    @UiField("resetBtn")
    private Button resetBtn;
    
    @UiField("addBtn")
    private Button addBtn;
    
    @UiField("addallBtn")
    private Button addallBtn;
    
    @UiField("availableExportBtn")
    private Button availableExportBtn;
    
    @UiField("removeBtn")
    private Button removeBtn;
    
    @UiField("removeallBtn")
    private Button removeallBtn;
    
    @UiField("selectedExportBtn")
    private Button selectedExportBtn;
    
    private final SelectionDTO customerSelectionDTO = new SelectionDTO();
    
    private final FieldGroup customerSearchBinder = new ErrorfulFieldGroup(new BeanItem(customerSelectionDTO));
    
    CommonUtil commonutil = CommonUtil.getInstance();
    
    SelectionLogic selLogic = new SelectionLogic();
    
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    
    com.stpl.app.util.CommonUIUtils commonUIUtils = new com.stpl.app.util.CommonUIUtils();
    
    SessionDTO sessionDTO;
    
    public String availableOrselected = org.apache.commons.lang3.StringUtils.EMPTY;
    
    public Boolean excelEligible = false;
    
    DeductionCalendarForm deductionCalendarForm;
    private final HeaderUtils headerUtils = new HeaderUtils();

    public CustomerSelection(SessionDTO sessionDTO, DeductionCalendarForm deductionCalendarForm) {
        this.sessionDTO = sessionDTO;
        this.deductionCalendarForm=deductionCalendarForm;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/deduction_calendar/customerSelection.xml"), this));
        init();
    }

    private void init() {
        try {
            LOGGER.debug("Entering init method");
            configureSelectionBinder();
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = sessionDTO.getUserId();
            final Map<String, AppPermission> fieldDCHM = stplSecurity
                    .getFieldOrColumnPermission(userId, ConstantsUtils.DEDUCTION_CALENDAR+ConstantsUtils.COMMA+ConstantsUtils.CUSTOMER_SELECTION,false);
            final Map<String, AppPermission> functionDCHM = stplSecurity.getBusinessFunctionPermission(userId, ConstantsUtils.DEDUCTION_CALENDAR+ConstantsUtils.COMMA+ConstantsUtils.CUSTOMER_SELECTION);
            getResponsiveFirstTab(fieldDCHM);
            configureFields();
            getButtonPermission(functionDCHM);            
            searchButton();
            if(sessionDTO.getMode().equals("view")){
                disableFieldsOnView();
            }
            LOGGER.debug("Ending init method");
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(CustomerSelection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CustomerSelection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void configureSelectionBinder(){
        try{
        customerSearchBinder.setBuffered(true);
        customerSearchBinder.bindMemberFields(this);       
        }catch (Exception ex){
            LOGGER.error(ex);
        }
    }
    
    private void configureFields() {
        try {
            commonMsg.loadComboBox(customerStatus, "STATUS", false);
            commonMsg.loadComboBox(customerType, "COMPANY_TYPE", false);
            commonMsg.loadComboBox(tradeClass, "COMPANY_TRADE_CLASS", false);
            tradeClass.setStyleName("tradeClassDdlb");
            availResultsTableLayout.addComponent(availableCustomersTable);
            availResultsTableLayout.addComponent(getResponsiveControls(availableTableLogic.createControls()));
            availableTableLogic.setContainerDataSource(availableResultsContainer);
            availableCustomersTable.setSelectable(true);
            availableCustomersTable.setMultiSelect(true);
            availableTableLogic.setPageLength(NumericConstants.TEN);
            availableTableLogic.sinkItemPerPageWithPageLength(false);
            final StplSecurity stplSecurity = new StplSecurity();

            final String userId = sessionDTO.getUserId();
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, ConstantsUtils.DEDUCTION_CALENDAR+ConstantsUtils.COMMA+ConstantsUtils.CUSTOMER_SELECTION,false);
            String mode = sessionDTO.getMode();

            List<Object> resultList = commonUIUtils.getFieldsForSecurity(ConstantsUtils.DEDUCTION_CALENDAR, ConstantsUtils.CUSTOMER_SELECTION);
            Object[] objColumn = headerUtils.customerColumns;

            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objColumn, fieldIfpHM, mode.equals("Copy")?"Edit":mode);
            
            availableCustomersTable.setVisibleColumns(tableResultCustom.getObjResult());
            availableCustomersTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            availableCustomersTable.setFilterBarVisible(true);
            availableCustomersTable.setSizeFull();
            availableCustomersTable.setImmediate(true);
            availableCustomersTable.setPageLength(NumericConstants.TEN);
            availableCustomersTable.setFilterBarVisible(true);
            availableCustomersTable.setFilterDecorator(new ExtDemoFilterDecorator());
            availableCustomersTable.setFilterGenerator(new DeductionCustomerFilerGenerator());
            availableCustomersTable.addStyleName("filtertable");
            availableCustomersTable.addStyleName("table-header-normal");
            selectedResultsTableLayout.addComponent(selectedCustomersTable);
            selectedResultsTableLayout.addComponent(getResponsiveControls(selectionTableLogic.createControls()));
            selectionTableLogic.setContainerDataSource(selectedResultsContainer);
            selectedCustomersTable.setSelectable(true);
            selectedCustomersTable.setMultiSelect(true);
            selectionTableLogic.setPageLength(NumericConstants.TEN);
            selectionTableLogic.sinkItemPerPageWithPageLength(false);
            selectedCustomersTable.setVisibleColumns(tableResultCustom.getObjResult());
            selectedCustomersTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            selectedCustomersTable.setFilterBarVisible(true);
            selectedCustomersTable.setSizeFull();
            selectedCustomersTable.setImmediate(true);
            selectedCustomersTable.setPageLength(NumericConstants.TEN);
            selectedCustomersTable.setFilterBarVisible(true);
            selectedCustomersTable.setFilterDecorator(new ExtDemoFilterDecorator());
            selectedCustomersTable.setFilterGenerator(new DeductionCustomerFilerGenerator());
            selectedCustomersTable.addStyleName("filtertable");
            selectedCustomersTable.addStyleName("table-header-normal");
            Arrays.asList(headerUtils.customerColumns);
            for (Object list1 : availableCustomersTable.getVisibleColumns()) {
                if(list1.equals("tradeClassStartDate") || list1.equals("tradeClassStartDate") ||
                        list1.equals("customerStartDate") || list1.equals("customerEndDate") ||
                        list1.equals("parentStartDate") || list1.equals("parentEndDate")  ||
                        list1.equals("priorParentStartDate")){
                    availableCustomersTable.setColumnAlignment(list1.toString(), ExtPagedTable.Align.CENTER);
                    selectedCustomersTable.setColumnAlignment(list1.toString(), ExtPagedTable.Align.CENTER);
                }
            }
            availableExportBtn.setIcon(new ThemeResource(ConstantsUtils.ICONS_EXCEL_PNG));
            availableExportBtn.setIcon(new ThemeResource(ConstantsUtils.ICONS_EXCEL_PNG));
            availableExportBtn.setStyleName("link");
            availableExportBtn.setDescription("Export to excel");
            availableExportBtn.setIconAlternateText("Excel export");
            availableExportBtn.setHtmlContentAllowed(true);

            selectedExportBtn.setIcon(new ThemeResource(ConstantsUtils.ICONS_EXCEL_PNG));
            selectedExportBtn.setIcon(new ThemeResource(ConstantsUtils.ICONS_EXCEL_PNG));
            selectedExportBtn.setStyleName("link");
            selectedExportBtn.setDescription("Export to excel");
            selectedExportBtn.setIconAlternateText("Excel export");
            selectedExportBtn.setHtmlContentAllowed(true);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    private void getButtonPermission(Map<String, AppPermission> functionDCHM) {
        if (functionDCHM.get(ConstantsUtils.RESET_BUTTON) != null && !((AppPermission) functionDCHM.get(ConstantsUtils.RESET_BUTTON)).isFunctionFlag()) {
            resetBtn.setVisible(false);
        }else{
            resetButton();
        }        
        if (functionDCHM.get(ConstantsUtils.ADD_BUTTON) != null && !((AppPermission) functionDCHM.get(ConstantsUtils.ADD_BUTTON)).isFunctionFlag()) {
            addBtn.setVisible(false);
        }else{
            addButton();
        }
        if (functionDCHM.get(ConstantsUtils.ADD_ALL_BUTTON) != null && !((AppPermission) functionDCHM.get(ConstantsUtils.ADD_ALL_BUTTON)).isFunctionFlag()) {
            addallBtn.setVisible(false);
        }else{
            addAllButton();
        }
        if (functionDCHM.get(ConstantsUtils.REMOVE_BUTTON) != null && !((AppPermission) functionDCHM.get(ConstantsUtils.REMOVE_BUTTON)).isFunctionFlag()) {
            removeBtn.setVisible(false);
        }else{
            removeButton();
        }
        if (functionDCHM.get(ConstantsUtils.REMOVE_ALL_BUTTON) != null && !((AppPermission) functionDCHM.get(ConstantsUtils.REMOVE_ALL_BUTTON)).isFunctionFlag()) {
            removeallBtn.setVisible(false);
        }else{
            removeAllButton();
        }
    }

    /**
     * The reset Button
     */
    public void resetButton() {
        resetBtn.addClickListener(new Button.ClickListener() {
            /**
             * Adds the button click listener.
             *
             */
            public void buttonClick(final Button.ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, MessageUtil.getMessage(Message.DC_RESET_HEADER), MessageUtil.getMessage(Message.DC_RESET_MESSAGE), new MessageBoxListener() {
                    /**
                     * Click event. This event is thrown, when the button is
                     * clicked.
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            LOGGER.debug("Entering reset Button operation in Customer Selection");
                            resetButtonClickLogic();
                            LOGGER.debug("Ending reset Button operation in Customer Selection");
                        }
                    }

                }, ButtonId.YES, ButtonId.NO);
            }
        });
    }
    public void resetButtonClickLogic() {
            LOGGER.debug("resetButtonClickLogic Started");
        availableTableLogic.clearAll();
        availableTableLogic.getFilters().clear();
        availableResultsContainer.removeAllItems();
        availableCustomersTable.removeAllItems();
        availableCustomersTable.setFilterDecorator(new ExtDemoFilterDecorator());
        availableCustomersTable.setFilterGenerator(new DeductionCustomerFilerGenerator());
        customerNo.setValue(StringUtils.EMPTY);
        customerName.setValue(StringUtils.EMPTY);
        tradeClass.setValue(null);
        city.setValue(StringUtils.EMPTY);
        state.setValue(StringUtils.EMPTY);
        zipcode.setValue(StringUtils.EMPTY);
        customerStatus.setValue(null);
        customerType.setValue(null);
        excelEligible = false;
        LOGGER.debug("resetButtonClickLogic Ended");
    }

    /**
     * The search Button
     */
    private void searchButton() {
        searchBtn.addClickListener(new Button.ClickListener() {
            /**
             * Adds the button click listener.
             *
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering Search Button operation in Customer Selection");
                try {
                    if (StringUtils.isBlank(customerNo.getValue()) 
                            && (tradeClass.getValue() == null || ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(tradeClass.getValue().toString()).trim())) && StringUtils.isBlank(zipcode.getValue())
                            && (customerStatus.getValue() == null ||ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(customerStatus.getValue().toString()).trim())) 
                            && StringUtils.isBlank(customerName.getValue()) 
                            && StringUtils.isBlank(state.getValue())
                            && StringUtils.isBlank(city.getValue()) 
                            && (customerType.getValue() == null || ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(customerType.getValue().toString()).trim())) && StringUtils.isBlank(zipcode.getValue())) {

                        AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.DC_SEARCH_CRITERIA_HEADER), MessageUtil.getMessage(Message.DC_SEARCH_CRITERIA_MESSAGE));
                    } else {
                        searchButtonClickLogic();
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }
                LOGGER.debug("Ending Search Button operation in Customer Selection");
            }

            private void searchButtonClickLogic() {
                try {
                    customerSearchBinder.commit();
                    boolean searchValue=availableTableLogic.fireSetData(customerSelectionDTO, false, Constants.CUSTOMER_SELECTION,ConstantsUtils.AVAILABLE);
                    if(!searchValue){
                    CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_COMPLETED);
                    } else {
                    CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
                    }
                    excelEligible=true;
                    availableTableLogic.fireSetData(customerSelectionDTO, false, Constants.CUSTOMER_SELECTION,ConstantsUtils.AVAILABLE);
                    availableCustomersTable.setFilterBarVisible(true);
                    availableCustomersTable.setFilterDecorator(new ExtDemoFilterDecorator());
                    availableCustomersTable.setFilterGenerator(new DeductionCustomerFilerGenerator());
                } catch (FieldGroup.CommitException ex) {
                    LOGGER.error(ex);
                }
            }

        });
    }

    /**
     * The add Button
     */
    private void addButton() {
        addBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when button has clicked .
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering Add Button operation in Customer Selection");
                final Set<SelectionDTO> selectionDTO = (Set<SelectionDTO>) availableCustomersTable.getValue();
                if (selectionDTO.isEmpty()) {
                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_RECORD_SELECTED_HEADER), MessageUtil.getMessage(Message.NO_RECORD_SELECTED_MESSAGE2));
                } else {
                    deductionCalendarForm.setNeedRefresh(true);
                    addItemsButtonClick();
                }
                
                LOGGER.debug("Entering Add Button operation in Customer Selection");
            }
        });
    }

    /**
     * The add all button
     */
    private void addAllButton() {
        addallBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when button has clicked .
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering Add All Button operation in Customer Selection");
                if (availableResultsContainer.size() > 0) {
                    deductionCalendarForm.setNeedRefresh(true);
                    addAllItemsButtonClick();

                } else {
                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_RECORD_SELECTED_HEADER), MessageUtil.getMessage(Message.NO_RECORD_SELECTED_MESSAGE));
                }

                LOGGER.debug("Entering Add All Button operation in Customer Selection");
            }

            private void addAllItemsButtonClick() {
                selLogic.addAllCustomersAndSaveToTempTable(sessionDTO,customerSelectionDTO,availableTableLogic.getFilters());
                selectedCustomersTable.setValue(null);
                availableCustomersTable.setValue(null);
                customerSelectionDTO.setUserId(sessionDTO.getUserId());
                customerSelectionDTO.setSessionId(sessionDTO.getUiSessionId());
                selectionTableLogic.fireSetData(customerSelectionDTO, false, Constants.CUSTOMER_SELECTION,ConstantsUtils.SELECTED);
            }

        });
    }

    /**
     * The remove button
     */
    private void removeButton() {
        removeBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when button has clicked .
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering Remove Button operation in Customer Selection");
                if (selectedCustomersTable.getValue()==null) {
                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_RECORD_SELECTED_HEADER), MessageUtil.getMessage(Message.NO_RECORD_SELECTED_MESSAGE1));
                } else {
                    deductionCalendarForm.setNeedRefresh(true);
                    removeItemsButtonClick();
                }

                LOGGER.debug("Entering Remove Button operation in Customer Selection");
            }

        });
    }

    /**
     * The remove all button
     */
    private void removeAllButton() {
        removeallBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when button has clicked .
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering Remove All Button operation in Customer Selection");
                if (selectedResultsContainer.size() > 0) {
                    deductionCalendarForm.setNeedRefresh(true);
                    removeAllItemsButtonClick();

                } else {
                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_RECORD_SELECTED_HEADER), MessageUtil.getMessage(Message.NO_RECORD_SELECTED_MESSAGE1));
                }

                LOGGER.debug("Ending Remove All Button operation in Customer Selection");
            }

            private void removeAllItemsButtonClick() {
                selLogic.deleteAllCustomersFromTempTable(sessionDTO);
                selectedResultsContainer.removeAllItems();
                selectedCustomersTable.setValue(null);
                availableCustomersTable.setValue(null);
                availableTableLogic.fireSetData(customerSelectionDTO, false, Constants.CUSTOMER_SELECTION,ConstantsUtils.AVAILABLE);
            }

        });
    }
    protected void excelExportLogic() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        LOGGER.debug("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.debug("Ending excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        LOGGER.debug("Entering createWorkSheet");
        customerSelectionDTO.setUserId(sessionDTO.getUserId());
        customerSelectionDTO.setSessionId(sessionDTO.getUiSessionId());
        final int recordCount = (Integer) selLogic.getCustomerSearchResult(customerSelectionDTO,0,0,true,null,null,availableOrselected);
        ExcelExportforBB.createWorkSheet(headerUtils.customerHeaders, recordCount, this, getUI(), TabNameUtil.CUSTOMER_SELECTION_EXPORT);
        LOGGER.debug("Ending createWorkSheet");
    }
    
    @UiHandler("availableExportBtn")
    public void excelExportBtnClick(final Button.ClickEvent event) {
        availableOrselected=ConstantsUtils.AVAILABLE;
        if(excelEligible){
            excelExport();
    }
    }
    @UiHandler("selectedExportBtn")
    public void excelExportButtonClick(final Button.ClickEvent event) {
        availableOrselected=ConstantsUtils.SELECTED;
        excelExport();
    }

    public void excelExport(){
        try {
            LOGGER.debug("Entering EXCEL Export Button Click :::: ");
            excelExportLogic();
            LOGGER.debug(" Ends  EXCEL Export Button Click ::::  ");

        } catch (SystemException sysException) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
            LOGGER.error(errorMsg);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message box is
                         * pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
        } catch (PortalException portException) {
            LOGGER.error(portException);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1011), new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message box is
                         * pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
        } catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1011), new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message box is
                         * pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
            LOGGER.error(exception);

        }
    }
    
    public void createWorkSheetContent(final Integer start, final Integer end,  PrintWriter printWriter) {
        try {
        SelectionDTO dto;
        final List<SelectionDTO> searchList = (List) selLogic.getCustomerSearchResult(customerSelectionDTO,start,end,false,null,null,availableOrselected);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String date;
        for (int rowCount = 0; rowCount < searchList.size(); rowCount++) {
                dto = (SelectionDTO) searchList.get(rowCount);

            if (dto.getOrganisationKey() != null && !StringUtils.EMPTY.equals(dto.getOrganisationKey().getDescription()) && !ConstantsUtils.NULL.equals(dto.getOrganisationKey().getDescription())) {
                printWriter.print(ConstantsUtils.QUOTE + dto.getOrganisationKey().getDescription() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + CommonUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
                printWriter.print(ConstantsUtils.QUOTE + dto.getCustomerId() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                printWriter.print(ConstantsUtils.QUOTE + dto.getCustomerNo() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                printWriter.print(ConstantsUtils.QUOTE + dto.getCustomerName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                if (dto.getTradeClass() != null && !StringUtils.EMPTY.equals(dto.getTradeClass().getDescription()) && !ConstantsUtils.NULL.equals(dto.getTradeClass().getDescription())) {
            printWriter.print(ConstantsUtils.QUOTE + dto.getTradeClass().getDescription()+ ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                }else{
                printWriter.print(ConstantsUtils.QUOTE + CommonUtils.EMPTY+ ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                }
                if (dto.getTradeClassStartDate() != null && !ConstantsUtils.NULL.equals(dto.getTradeClassStartDate())) {
                date = format.format(dto.getTradeClassStartDate());
                printWriter.print(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
         } else {
                    printWriter.print(CommonUtils.EMPTY + ExcelExportUtil.COMMA);
                }
            
            if (dto.getTradeClassEndDate() != null && !ConstantsUtils.NULL.equals(dto.getTradeClassEndDate())) {
                  date = format.format(dto.getTradeClassEndDate());
                printWriter.print(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
             } else {
                    printWriter.print(CommonUtils.EMPTY + ExcelExportUtil.COMMA);
            }

                if (dto.getCustomerType() != null && !StringUtils.EMPTY.equals(dto.getCustomerType().getDescription()) && !ConstantsUtils.NULL.equals(dto.getCustomerType().getDescription())) {
            printWriter.print(ConstantsUtils.QUOTE + dto.getCustomerType().getDescription() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                }else{
                 printWriter.print(ConstantsUtils.QUOTE + CommonUtils.EMPTY+ ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
           
                }
            printWriter.print(ConstantsUtils.QUOTE + dto.getCustomerStatus().getDescription()+ ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getLives()+ ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            if (dto.getCustomerStartDate() != null && !ConstantsUtils.NULL.equals(dto.getCustomerStartDate())) {
                date = format.format(dto.getCustomerStartDate());
                printWriter.print(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + CommonUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
            if (dto.getCustomerEndDate()!= null && !ConstantsUtils.NULL.equals(dto.getCustomerEndDate())) {
                date = format.format(dto.getCustomerEndDate());
                printWriter.print(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + CommonUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
               
            if (dto.getUdc1() != null && !StringUtils.EMPTY.equals(dto.getUdc1().getDescription()) && !ConstantsUtils.NULL.equals(dto.getUdc1().getDescription())) {
                printWriter.print(ConstantsUtils.QUOTE + dto.getUdc1().getDescription() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + CommonUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
            if (dto.getUdc2() != null && !StringUtils.EMPTY.equals(dto.getUdc2().getDescription()) && !ConstantsUtils.NULL.equals(dto.getUdc2().getDescription())) {
                printWriter.print(ConstantsUtils.QUOTE + dto.getUdc2().getDescription() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + CommonUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
            if (dto.getUdc3() != null && !StringUtils.EMPTY.equals(dto.getUdc3().getDescription()) && !ConstantsUtils.NULL.equals(dto.getUdc3().getDescription())) {
                printWriter.print(ConstantsUtils.QUOTE + dto.getUdc3().getDescription() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + CommonUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
            if (dto.getUdc4() != null && !StringUtils.EMPTY.equals(dto.getUdc4().getDescription()) && !ConstantsUtils.NULL.equals(dto.getUdc4().getDescription())) {
                printWriter.print(ConstantsUtils.QUOTE + dto.getUdc4().getDescription() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + CommonUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
            if (dto.getUdc5() != null && !StringUtils.EMPTY.equals(dto.getUdc5().getDescription()) && !ConstantsUtils.NULL.equals(dto.getUdc5().getDescription())) {
                printWriter.print(ConstantsUtils.QUOTE + dto.getUdc5().getDescription() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + CommonUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
            if (dto.getUdc6() != null && !StringUtils.EMPTY.equals(dto.getUdc6().getDescription()) && !ConstantsUtils.NULL.equals(dto.getUdc6().getDescription())) {
                printWriter.print(ConstantsUtils.QUOTE + dto.getUdc6().getDescription() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + CommonUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
            
            if(dto.getCustomerGroup()!=null && !ConstantsUtils.NULL.equals(dto.getCustomerGroup())) {
            printWriter.print(ConstantsUtils.QUOTE + dto.getCustomerGroup()+ ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(ConstantsUtils.QUOTE + CommonUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            }
                printWriter.print(ConstantsUtils.QUOTE + dto.getFinancialSystem() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                printWriter.print(ConstantsUtils.QUOTE + dto.getAddress1() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                if(dto.getAddress2()!=null && !ConstantsUtils.NULL.equals(dto.getAddress2())) {
                printWriter.print(ConstantsUtils.QUOTE + dto.getAddress2() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                }else{
                printWriter.print(ConstantsUtils.QUOTE +CommonUtils.EMPTY + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                }
                printWriter.print(ConstantsUtils.QUOTE + dto.getCity() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                printWriter.print(ConstantsUtils.QUOTE + dto.getState() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                printWriter.print(ConstantsUtils.QUOTE + dto.getZipCode() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                printWriter.print(ConstantsUtils.QUOTE + dto.getCountry() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                printWriter.print(ConstantsUtils.QUOTE + dto.getRegionCode() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                printWriter.print(ConstantsUtils.QUOTE + dto.getParentCustomerNo() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                if (dto.getParentStartDate() != null && !ConstantsUtils.NULL.equals(dto.getParentStartDate())) {
                  date = format.format(dto.getParentStartDate());
                printWriter.print(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
             } else {
                    printWriter.print(CommonUtils.EMPTY + ExcelExportUtil.COMMA);
            }
             if (dto.getParentEndDate() != null && !ConstantsUtils.NULL.equals(dto.getParentEndDate())) {
                  date = format.format(dto.getParentEndDate());
                printWriter.print(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
             } else {
                    printWriter.print(CommonUtils.EMPTY + ExcelExportUtil.COMMA);
            }
             if (dto.getPriorParentStartDate() != null && !ConstantsUtils.NULL.equals(dto.getPriorParentStartDate())) {
                  date = format.format(dto.getPriorParentStartDate());
                printWriter.print(ConstantsUtils.QUOTE + date + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
             } else {
                    printWriter.print(CommonUtils.EMPTY + ExcelExportUtil.COMMA);
            }
                printWriter.println(dto.getPriorParentCustomerNo() + ExcelExportUtil.COMMA);
            }
        } catch (Exception e) {
            LOGGER.error(e);
            }
    }
    protected void addItemsButtonClick() {

        LOGGER.debug("Entering addItemsButtonClick method ");
        final java.util.Set<SelectionDTO> selectedCompaniesList = (java.util.Set<SelectionDTO>) availableCustomersTable.getValue();
        if (selectedCompaniesList.isEmpty()) {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_RECORD_SELECTED_HEADER), MessageUtil.getMessage(Message.NO_RECORD_SELECTED_MESSAGE));
            return;
        }
        for (SelectionDTO selDTO : selectedCompaniesList) {
            if (selDTO != null) {
                selLogic.moveCustomersAndSaveToTempTable(selDTO, sessionDTO);
                selDTO.setUserId(sessionDTO.getUserId());
                selDTO.setSessionId(sessionDTO.getUiSessionId());
                selectionTableLogic.fireSetData(selDTO, false, Constants.CUSTOMER_SELECTION, ConstantsUtils.SELECTED);
                selectedCustomersTable.setFilterBarVisible(true);
                selectedCustomersTable.setFilterDecorator(new ExtDemoFilterDecorator());
                selectedCustomersTable.setFilterGenerator(new DeductionCustomerFilerGenerator());
            }
        }
        selectedCustomersTable.setValue(null);
        availableCustomersTable.setValue(null);
        LOGGER.debug("addItemsButtonClick method Ended");

    }

    protected void removeItemsButtonClick() {

        LOGGER.debug("Entering removeItemsButtonClick method ");
        final java.util.Set<SelectionDTO> itemMasterDetailsList = (java.util.Set<SelectionDTO>) selectedCustomersTable.getValue();
        List<String> ids = new ArrayList<>();
        if (!itemMasterDetailsList.isEmpty()) {
            for (final Iterator<SelectionDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
                final SelectionDTO item = iterator.next();
                ids.add(String.valueOf(item.getCompanyMasterSid()));
                selectedResultsContainer.removeItem(item);
            }

            selLogic.deleteCustomersFromTempTable(StringUtils.join(ids, ","), sessionDTO);
        } else {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_RECORD_SELECTED_HEADER), MessageUtil.getMessage(Message.NO_RECORD_SELECTED_MESSAGE1));
        }
        selectedCustomersTable.setValue(null);
        availableCustomersTable.setValue(null);
        loadInEdit(sessionDTO);
        LOGGER.debug("addItemsButtonClick method Ended");

    }
    
    public void loadInEdit(SessionDTO session){
        SelectionDTO selDTO = new SelectionDTO();
        selDTO.setUserId(session.getUserId());
        selDTO.setSessionId(session.getUiSessionId());
        selectionTableLogic.fireSetData(selDTO, false, Constants.CUSTOMER_SELECTION,ConstantsUtils.SELECTED);
        selectedCustomersTable.setFilterBarVisible(true);
        selectedCustomersTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedCustomersTable.setFilterGenerator(new DeductionCustomerFilerGenerator());
    }

     public void disableFieldsOnView(){
         resetBtn.setVisible(false);
         searchBtn.setVisible(false);
         addBtn.setVisible(false);
         addallBtn.setVisible(false);
         removeBtn.setVisible(false);
         removeallBtn.setVisible(false);
         
         customerSearchPanel.setVisible(false);
         searchHLayout.setVisible(false);
         includeButtonLayout.setVisible(false);
         availableResultsTablePanel.setVisible(false);
         selectedResultsTablePanel.setVisible(true);
     }
    
     private void getResponsiveFirstTab(final Map<String, AppPermission> fieldItemHM) {
        try {
            String mode = sessionDTO.getMode();
            List<Object> resultList = commonUIUtils.getFieldsForSecurity(ConstantsUtils.DEDUCTION_CALENDAR, ConstantsUtils.CUSTOMER_SELECTION);

            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout2, fieldItemHM,mode.equals("Copy")?"Edit":mode);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}

