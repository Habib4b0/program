/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.form.lookups;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.ExclusionLookupDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.ViewLookupDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.ExclusionDetailsLogic;
import com.stpl.app.arm.businessprocess.pipelineaccrual.lookups.PrivatePublicLookup;
import com.stpl.app.arm.businessprocess.pipelineaccrual.lookups.SaveViewPopup;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.CustomerGroupDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.logic.PipelineInventoryLookupLogic;
import com.stpl.app.arm.businessprocess.pipelineinventory.tablelogic.CustomerGrpAvailableTableLogic;
import com.stpl.app.arm.businessprocess.pipelineinventory.tablelogic.CustomerGrpSelectedTableLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.ResponsiveUtils;
import com.stpl.app.utils.ConstantsUtils;

import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.Compare.Equal;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Porchelvi.G
 */
public class InventoryCustomerGroupLookup extends Window {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryCustomerGroupLookup.class);

    @UiField("privateView")
    private CustomTextField privateView;

    @UiField("publicView")
    private CustomTextField publicView;
    @UiField("inventoryTableLayout")
    private VerticalLayout inventoryTableLayout;
    @UiField("inventoryCalculationLayout")
    private VerticalLayout inventoryCalculationLayout;

    /**
     * The customer group name.
     */
    @UiField("customerGroupName")
    private TextField customerGroupName;
    /**
     * The customer group.
     */
    @UiField("customerGroup")
    private TextField customerGroupNo;
    /**
     * The search btn.
     */
    @UiField("search")
    private Button searchBtn;

    @UiField("deleteViewBtn")
    private Button deleteViewBtn;

    private final BeanItemContainer<CustomerGroupDTO> resultsContainer = new BeanItemContainer<>(CustomerGroupDTO.class);
    private final BeanItemContainer<CustomerGroupDTO> resultsinventoryContainer = new BeanItemContainer<>(CustomerGroupDTO.class);
    private CustomerGrpAvailableTableLogic availableTableLogic = new CustomerGrpAvailableTableLogic();
    private CustomerGroupDTO groupDTO = new CustomerGroupDTO();
    private CustomerGrpSelectedTableLogic selectedTableLogic = new CustomerGrpSelectedTableLogic();
    private ExtPagedTable availableTable = new ExtPagedTable(availableTableLogic);
    private ExtPagedTable selectedTable = new ExtPagedTable(selectedTableLogic);
    private PrivatePublicLookup viewLookUp;
    private InventoryCustomerGroupFeildFactory inventoryCustomer = new InventoryCustomerGroupFeildFactory();
    private PipelineInventoryLookupLogic pipelineLogic = new PipelineInventoryLookupLogic();
    private int projectionId = 0;
    private String userId = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID);
    private ViewLookupDTO viewDTO = new ViewLookupDTO();
    private List<CustomerGroupDTO> customerGroupList = new ArrayList<>();
    private List<String> customerListGroup = new ArrayList<>();
    private AbstractSelectionDTO selectionDto;
    private boolean submitted = false;
    private Window instance = null;
    private String viewCategory;
    private Boolean checkdeletedRecord = Boolean.FALSE;

    public InventoryCustomerGroupLookup(int projectionId, AbstractSelectionDTO selectionDto) {
        super("Inventory Customer Group");
        this.projectionId = projectionId;
        this.selectionDto = selectionDto;
        groupDTO.setProjectionId(projectionId);
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        init();
        instance = this;
    }

    private void init() {
        setClosable(true);
        setResizable(true);
        setModal(true);
        setSizeFull();
        setContent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/Inventory/customerGroupLookup.xml"), this));
        configureFields();
    }

    private void configureFields() {
        deleteViewBtn.setEnabled(false);
        addResultsTable();
        availableTableLogic.setContainerDataSource(resultsContainer);
        availableTableLogic.setPageLength(NumericConstants.FIVE);
        availableTableLogic.setItemsPerPage(NumericConstants.FIVE);
        availableTableLogic.sinkItemPerPageWithPageLength(false);

        availableTable.setVisibleColumns(ARMUtils.getCustomerGroupLookupColumns());
        availableTable.setColumnHeaders(ARMUtils.getCustomerGroupLookupHeaders());
        availableTable.setImmediate(true);
        availableTable.setSelectable(true);
        availableTable.setFilterGenerator(new ExtFilterGenerator() {
            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {
                LOGGER.debug("Inside filterRemoved Method");
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                LOGGER.debug("Inside filterAdded Method");
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                return null;
            }
        });
        availableTable.setFilterBarVisible(true);
        availableTable.setFilterDecorator(new ExtDemoFilterDecorator());
        availableTable.setWidth("100%");
        addInventoryTable();
        configureInventoryTable();
        privateView.addClickListener(viewListener);
        publicView.addClickListener(viewListener);
        privateView.setStyleName("searchicon");
        publicView.setStyleName("searchicon");
        privateView.setDescription("Public");
        publicView.setDescription("Private");
        privateView.addClickListener(viewListener);
        publicView.addClickListener(viewListener);
        privateView.setImmediate(true);
        publicView.setImmediate(true);
        intialLoadInventory();
    }

    @UiHandler("search")
    public void btnSearchLogic(Button.ClickEvent event) {
        try {
            if (StringUtils.isNotBlank(customerGroupName.getValue().trim()) || StringUtils.isNotBlank(customerGroupNo.getValue().trim())) {

                resultsContainer.removeAllItems();
                availableTableLogic.getResultsinventoryContainer().removeAllItems();

                getCustomerGroupDto(groupDTO);
                if (availableTableLogic.loadSetData(groupDTO, false, selectionDto)) {
                    checkdeletedRecord = Boolean.TRUE;
                    Notification.show("Search Completed");
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error in btnSearchLogic :", ex);
        }
    }

    @UiHandler("add")
    public void btnAddLogic(Button.ClickEvent event) {
        if (availableTable.getValue() != null) {
            CustomerGroupDTO dto = (CustomerGroupDTO) availableTable.getValue();
            dto.setSelectedFlag(Boolean.TRUE);
            getCustomerGroupDto(groupDTO);
            selectionDto.addCustomerGroupSidSet(dto.getCustomerGroupSid());
            selectedTableLogic.loadSetData(groupDTO, false, selectionDto);
            availableTableLogic.getResultsinventoryContainer().removeAllItems();
            availableTable.removeAllItems();
            resultsContainer.removeAllItems();
            availableTableLogic.loadSetData(groupDTO, false, selectionDto);
        }
    }

    @UiHandler("addall")
    public void btnAddAllLogic(Button.ClickEvent event) {
        try {
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    LOGGER.debug("inside BTNAddAll Logic No Method");

                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {
                    loadCustomerGroupList();
                    selectedTableLogic.getResultsinventoryContainer().removeAllContainerFilters();
                    List<CustomerGroupDTO> list = selectedTableLogic.getResultsinventoryContainer().getItemIds();
                    for (CustomerGroupDTO list1 : list) {
                        list1.setSelectedFlag(Boolean.TRUE);
                    }
                    getCustomerGroupDto(groupDTO);
                    if (checkdeletedRecord) {
                        selectedTableLogic.loadSetData(groupDTO, false, selectionDto);
                        availableTableLogic.loadSetData(groupDTO, false, selectionDto);
                    }
                }
            }.getConfirmationMessage(ARMMessages.getInventoryCustomerGroupADDALLErrorMsg1(), ARMMessages.getInventoryCustomerGroupADDALLErrorMsg2());

        } catch (Exception ex) {
            LOGGER.error("Error in btnAddLineLogic :", ex);
        }
    }

    @UiHandler("remove")
    public void btnRmoveLogic(Button.ClickEvent event) {
        if (selectedTable.getValue() != null) {
            CustomerGroupDTO dto = ((CustomerGroupDTO) selectedTable.getValue());
            dto.setSelectedFlag(Boolean.FALSE);
            getCustomerGroupDto(groupDTO);
            selectionDto.removeCustomerGroupSidSet(dto.getCustomerGroupSid());
            selectedTableLogic.loadSetData(groupDTO, false, selectionDto);
            availableTableLogic.loadSetData(groupDTO, false, selectionDto);
        } else {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getRemoveButtonMessageId001());
        }
    }

    @UiHandler("removeall")
    public void btnRmoveAllLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                LOGGER.debug("inside btnRmoveAllLogic  No Method");

            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                selectedTableLogic.getResultsinventoryContainer().removeAllContainerFilters();
                List<CustomerGroupDTO> list = selectedTableLogic.getResultsinventoryContainer().getItemIds();
                for (CustomerGroupDTO list1 : list) {
                    list1.setSelectedFlag(Boolean.FALSE);
                }
                selectionDto.clearCustomerGroupSidSet();
                getCustomerGroupDto(groupDTO);
                selectedTableLogic.loadSetData(groupDTO, false, selectionDto);
                availableTableLogic.getResultsinventoryContainer().removeAllItems();
                availableTable.removeAllItems();
                resultsContainer.removeAllItems();
                availableTableLogic.loadSetData(groupDTO, false, selectionDto);
            }
        }.getConfirmationMessage(ARMMessages.getInventoryCustomerGroupRemoveALLErrorMsg1(), ARMMessages.getInventoryCustomerGroupRemoveALLErrorMsg2());

    }

    @UiHandler("submitBtn")
    public void btnSubmitLogic(Button.ClickEvent event) {
        if (checkValidField()) {
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    LOGGER.debug("inside btnSubmitLogic  No Method");

                }

                @Override
                public void yesMethod() {
                    loadCustomerList();
                    pipelineLogic.saveCustomerGroupValue(selectedTableLogic.getResultsinventoryContainer().getItemIds(), projectionId, selectionDto);
                    submitted = true;
                    selectionDto.setReloadFlag(true);
                    selectionDto.setSubmittedFlag(true);
                    instance.close();
                }
            }.getConfirmationMessage("Confirm Submit", ARMMessages.getCGLookUpSubmitConfirmTransaction3());
        } else {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getLookUpSubmitErrorTransaction3());
        }

    }

    public void loadCustomerList() {
        List<String> customerListValue = new ArrayList();
        List<CustomerGroupDTO> inventoryList = new ArrayList<>();
        inventoryList.addAll(selectedTableLogic.getResultsinventoryContainer().getItemIds());
        Collections.sort(inventoryList);
        setCustomerGroupList(inventoryList);
        if (!inventoryList.isEmpty()) {
            for (CustomerGroupDTO customerListValue1 : inventoryList) {
                customerListValue.add(customerListValue1.getCustomerGroupName() + "~" + customerListValue1.getCustomerGroupSid());
            }
            selectionDto.setCustomerGroupList(customerListValue);
            setCustomerListGroup(customerListValue);
        }
    }

    @UiHandler("saveViewBtn")
    public void btnSaveViewLogic(Button.ClickEvent event) {
        try {
            ExclusionLookupDTO saveViewDTO = new ExclusionLookupDTO();
            if (!StringUtils.EMPTY.equalsIgnoreCase(publicView.getValue()) || !StringUtils.EMPTY.equalsIgnoreCase(privateView.getValue())) {
                saveViewDTO.setViewName(viewDTO.getViewName());
                saveViewDTO.setViewType(viewDTO.getViewType());
                saveViewDTO.setViewMasterSid(viewDTO.getViewSid());
                saveViewDTO.setUserID(CommonLogic.parseStringToInteger(userId));
                saveViewDTO.setViewStatus(true);
                saveViewDTO.setSessionUserID(CommonLogic.parseStringToInteger(userId));
                saveViewDTO.setCreatedUser(viewDTO.getCreatedUser());
            } else {
                saveViewDTO.setViewName(StringUtils.EMPTY);
                saveViewDTO.setViewType(StringUtils.EMPTY);
                saveViewDTO.setUserID(CommonLogic.parseStringToInteger(userId));
                saveViewDTO.setViewStatus(false);
                saveViewDTO.setSessionUserID(CommonLogic.parseStringToInteger(userId));
                saveViewDTO.setCreatedUser(userId);

            }
            selectedTableLogic.getResultsinventoryContainer().removeAllContainerFilters();
            Container.Filter filter = new Equal("selectedFlag", Boolean.TRUE);
            selectedTableLogic.getResultsinventoryContainer().addContainerFilter(filter);
            saveViewDTO.setCustGrpList(selectedTableLogic.getResultsinventoryContainer().getItemIds());
            saveViewDTO.setScreenFlag(true);
            SaveViewPopup viewPopup = new SaveViewPopup(saveViewDTO);
            getUI().addWindow(viewPopup);
        } catch (Exception e) {
            LOGGER.error("Error in btnSaveViewLogic :", e);
        }
    }

    @UiHandler("closeBtn")
    public void btnCloseBtnLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to close the pop-up?Nothing will be submitted to the Inventory tab.", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClicked(final ButtonId buttonId) {
                if ("yes".equalsIgnoreCase(buttonId.name())) {
                    LOGGER.debug("Entering Close operation");
                    selectedTableLogic.getResultsinventoryContainer().removeAllContainerFilters();
                    selectedTableLogic.getResultsinventoryContainer().removeAllItems();
                    availableTableLogic.getResultsinventoryContainer().removeAllContainerFilters();
                    availableTableLogic.getResultsinventoryContainer().removeAllItems();
                    viewDTO = new ViewLookupDTO();
                    if (!selectionDto.isReloadFlag()) {
                        selectionDto.clearCustomerGroupSidSet();
                    }
                    close();
                    LOGGER.debug("Ending Close operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @UiHandler("deleteViewBtn")
    public void deleteButtonClick(Button.ClickEvent event) {

        try {
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    LOGGER.debug("inside deleteButtonClick  No Method");

                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {
                    ExclusionDetailsLogic arLogic = new ExclusionDetailsLogic();
                    arLogic.deleteViewLogic(viewDTO.getViewSid());
                    refreshLookup();
                }
            }.getConfirmationMessage(ARMMessages.getResetConfirmationMessage(), ARMMessages.getDeleteMessage_exclusion());

        } catch (Exception ex) {
            LOGGER.error("Error in deleteButtonClick :", ex);
        }
    }

    private void addResultsTable() {
        inventoryTableLayout.removeAllComponents();
        inventoryTableLayout.addComponent(availableTable);
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(availableTableLogic.createControls());
        inventoryTableLayout.addComponent(controls);
    }

    public CustomerGroupDTO getGroupDTO() {
        return groupDTO;
    }

    public void setGroupDTO(CustomerGroupDTO groupDTO) {
        this.groupDTO = groupDTO;
    }

    private void addInventoryTable() {
        inventoryCalculationLayout.removeAllComponents();
        inventoryCalculationLayout.addComponent(selectedTable);
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(selectedTableLogic.createControls());
        inventoryCalculationLayout.addComponent(controls);
    }

    private void configureInventoryTable() {

        selectedTable.setFilterGenerator(new ExtFilterGenerator() {
            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                return null;
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                LOGGER.debug("inside filterAdded Method");

            }

            @Override
            public void filterRemoved(Object propertyId) {
                LOGGER.debug("inside filterRemoved Method");
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                return null;
            }
        });
        selectedTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedTableLogic.setContainerDataSource(resultsinventoryContainer);

        selectedTable.setVisibleColumns(ARMUtils.getCustomerGroupInventoryColumns());
        selectedTable.setColumnHeaders(ARMUtils.getCustomerGroupInventoryHeaders());
        selectedTable.setImmediate(true);
        selectedTable.setSelectable(true);
        selectedTableLogic.setPageLength(NumericConstants.FIVE);
        selectedTableLogic.sinkItemPerPageWithPageLength(false);
        selectedTable.setFilterBarVisible(true);

        selectedTable.setWidth("100%");
        selectedTable.setTableFieldFactory(inventoryCustomer);
        if (!selectionDto.isSubmittedFlag()) {
            selectionDto.clearCustomerGroupSidSet();
        }
        selectedTable.setEditable(true);
    }

    class InventoryCustomerGroupFeildFactory implements TableFieldFactory {

        @Override
        public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
            if ("include".equals(propertyId.toString())) {
                ExtCustomCheckBox checkRecord = new ExtCustomCheckBox();
                checkRecord.setImmediate(true);
                return checkRecord;

            }
            if ("indicator".equals(propertyId.toString())) {
                CustomComboBox indicator = new CustomComboBox();
                loadIndicatorDDLB(indicator);
                return indicator;

            }
            return null;

        }

    }

    public CustomComboBox loadIndicatorDDLB(CustomComboBox indicator) {

        try {
            indicator.removeAllItems();
            indicator.setImmediate(true);
            Object nullItem = indicator.addItem();
            indicator.setNullSelectionItemId(nullItem);
            indicator.addItem(Boolean.TRUE);
            indicator.addItem(Boolean.FALSE);
            indicator.setItemCaption(nullItem, GlobalConstants.getSelectOne());
            indicator.setItemCaption(Boolean.TRUE, "+");
            indicator.setItemCaption(Boolean.FALSE, "-");
            indicator.select(nullItem);
        } catch (Exception e) {
            LOGGER.error("Error while loading Drop down with :", e);
        }
        return indicator;
    }

    public boolean checkValidField() {
        boolean ret = false;
        selectedTableLogic.getResultsinventoryContainer().removeAllContainerFilters();
        Container.Filter filter = new Equal("selectedFlag", Boolean.TRUE);
        Container.Filter filter1 = new Equal("include", Boolean.TRUE);
        selectedTableLogic.getResultsinventoryContainer().addContainerFilter(filter);
        selectedTableLogic.getResultsinventoryContainer().addContainerFilter(filter1);
        List<CustomerGroupDTO> list = selectedTableLogic.getResultsinventoryContainer().getItemIds();
        if (!list.isEmpty()) {
            for (CustomerGroupDTO dtoValue : list) {
                if (dtoValue.isInclude()) {
                    ret = true;
                    if ("null".equalsIgnoreCase(String.valueOf(dtoValue.getIndicator()))) {
                        ret = false;
                        break;
                    }
                }
            }
        }
        return ret;
    }
    private CustomTextField.ClickListener viewListener = new CustomTextField.ClickListener() {
        @Override
        public void click(CustomTextField.ClickEvent event) {
            try {
                int userIdValue = userId.equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(userId.replaceAll("\\D+", StringUtils.EMPTY));
                if (viewLookUp == null) {
                    viewLookUp = new PrivatePublicLookup(event.getComponent().getCaption(), userIdValue, "CG", event.getComponent().getId(), "Customer Group");
                } else {
                    viewLookUp.reloadScreen(event.getComponent().getCaption(), userIdValue, "CG", event.getComponent().getId());
                }
                getUI().addWindow(viewLookUp);

                viewLookUp.addCloseListener(new CloseListener() {

                    @Override
                    public void windowClose(CloseEvent e) {
                        if (viewLookUp.getDtoValue().getCheckFlag()) {
                            lookupLoadLogic(viewLookUp.getDtoValue());
                            checkdeletedRecord = Boolean.TRUE;
                        }
                    }
                });

            } catch (Exception ex) {
                LOGGER.error("Error in viewListener :", ex);
            }
        }
    };

    public ViewLookupDTO getViewDTO() {
        return viewDTO;
    }

    public void setViewDTO(ViewLookupDTO viewDTO) {
        this.viewDTO = viewDTO;
    }

    public void intialLoadInventory() {
        groupDTO.setEditFlag(true);
        selectedTableLogic.loadSetData(groupDTO, false, selectionDto);
        groupDTO.setEditFlag(false);

    }

    public void reloadScreen(int projectionId) {
        this.projectionId = projectionId;
        intialLoadInventory();
    }

    public List<CustomerGroupDTO> getCustomerGroupList() {
        return CommonLogic.getInstance().getArrayListCloned(customerGroupList);
    }

    public void setCustomerGroupList(List<CustomerGroupDTO> customerGroupList) {
        this.customerGroupList = CommonLogic.getInstance().getArrayListCloned(customerGroupList);
    }

    public List<String> getCustomerListGroup() {
        return CommonLogic.getInstance().getArrayListCloned(customerListGroup);
    }

    public void setCustomerListGroup(List<String> customerListGroup) {
        this.customerListGroup = CommonLogic.getInstance().getArrayListCloned(customerListGroup);
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    @UiHandler("reset")
    public void btnResetLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                LOGGER.debug("inside btnResetLogic  No Method");

            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                customerGroupName.setValue(StringUtils.EMPTY);
                customerGroupNo.setValue(StringUtils.EMPTY);
            }
        }.getConfirmationMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageID003());
    }

    @UiHandler("resetBtn")
    public void resetButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                LOGGER.debug("inside resetButtonLogic  No Method");

            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                resetFields();
            }
        }.getConfirmationMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageId006());
    }

    public void refreshLookup() {
        resetFields();
        viewLookUp.getDtoValue().setCheckFlag(Boolean.FALSE);
        deleteViewBtn.setEnabled(false);
    }

    public void lookupLoadLogic(ViewLookupDTO viewLookupDTO) {
        privateView.setValue(StringUtils.EMPTY);
        publicView.setValue(StringUtils.EMPTY);

        if ("publicView".equalsIgnoreCase(viewLookupDTO.getViewType())) {
            publicView.setValue(viewLookupDTO.getViewName());
            publicView.setImmediate(true);
            deleteViewBtn.setEnabled(true);
        } else {
            privateView.setValue(viewLookupDTO.getViewName());
            privateView.setImmediate(true);
            deleteViewBtn.setEnabled(true);
        }
        setViewDTO(viewLookupDTO);
        groupDTO.setViewFlag(true);
        groupDTO.setViewSid(viewLookupDTO.getViewSid());
        availableTableLogic.getResultsinventoryContainer().removeAllItems();
        availableTableLogic.setFirstCount(true);
        selectedTableLogic.setFirstCount(true);
        availableTableLogic.loadSetData(groupDTO, false, selectionDto);
        selectedTableLogic.loadSetData(groupDTO, false, selectionDto);
        selectionDto.clearCustomerGroupSidSet();
        loadCustomerSelectedGroupList();
        groupDTO.setViewFlag(false);
    }

    public void loadInitialArc(String vmSid, String viewCategory) {
        List<ViewLookupDTO> viewLookupDTO = pipelineLogic.getARCSavedPublicViewList(vmSid, viewCategory);
        if (viewLookupDTO != null && !viewLookupDTO.isEmpty()) {
            lookupLoadLogic(viewLookupDTO.get(0));
            this.viewCategory = viewCategory;
        }
    }

    public void resetFields() {
        customerGroupName.setValue(StringUtils.EMPTY);
        customerGroupNo.setValue(StringUtils.EMPTY);
        resultsContainer.removeAllItems();
        resultsinventoryContainer.removeAllItems();
        selectionDto.clearCustomerGroupSidSet();
        availableTable.removeAllItems();
        selectedTable.removeAllItems();
        availableTableLogic.clearAll();
        selectedTableLogic.clearAll();
        selectedTableLogic.getResultsinventoryContainer().removeAllItems();
        availableTableLogic.getResultsinventoryContainer().removeAllItems();
        availableTableLogic.loadSetData(groupDTO, true, selectionDto);
        selectedTableLogic.loadSetData(groupDTO, true, selectionDto);
        checkdeletedRecord = Boolean.FALSE;
        privateView.setValue(StringUtils.EMPTY);
        publicView.setValue(StringUtils.EMPTY);
    }

    public void getCustomerGroupDto(CustomerGroupDTO groupDTO) {
        groupDTO.setCustomerGroupName(customerGroupName.getValue().trim());
        groupDTO.setCustomerGroupNo(customerGroupNo.getValue().trim());
    }

    public void loadCustomerGroupList() {
        for (CustomerGroupDTO obj : availableTableLogic.getResultsinventoryContainer().getItemIds()) {
            selectionDto.addCustomerGroupSidSet(obj.getCustomerGroupSid());
        }
    }

    public void loadCustomerSelectedGroupList() {
        for (CustomerGroupDTO obj : selectedTableLogic.getResultsinventoryContainer().getItemIds()) {
            selectionDto.addCustomerGroupSidSet(obj.getCustomerGroupSid());
        }
    }

    @Override
    public boolean equals(Object invCgobj) {
        return super.equals(invCgobj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream invCgobj) throws IOException {
        invCgobj.defaultWriteObject();
    }

    private void readObject(ObjectInputStream invCgobj) throws IOException, ClassNotFoundException {
        invCgobj.defaultReadObject();
    }
}
