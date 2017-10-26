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
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
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
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class InventoryCustomerGroupLookup extends Window {

    private static final Logger LOGGER = Logger.getLogger(InventoryCustomerGroupLookup.class);

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
    private final BeanItemContainer<CustomerGroupDTO> resultsContainer = new BeanItemContainer<CustomerGroupDTO>(CustomerGroupDTO.class);
    private final BeanItemContainer<CustomerGroupDTO> resultsinventoryContainer = new BeanItemContainer<CustomerGroupDTO>(CustomerGroupDTO.class);
    CustomerGrpAvailableTableLogic availableTableLogic = new CustomerGrpAvailableTableLogic();
    CustomerGroupDTO groupDTO = new CustomerGroupDTO();
    CustomerGrpSelectedTableLogic selectedTableLogic = new CustomerGrpSelectedTableLogic();
    public ExtPagedTable availableTable = new ExtPagedTable(availableTableLogic);
    public ExtPagedTable selectedTable = new ExtPagedTable(selectedTableLogic);
    private PrivatePublicLookup viewLookUp;
    InventoryCustomerGroupFeildFactory inventoryCustomer = new InventoryCustomerGroupFeildFactory();
    PipelineInventoryLookupLogic pipelineLogic = new PipelineInventoryLookupLogic();
    int projectionId = 0;
    String userId = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID);
    SaveViewPopup viewPopup;
    ViewLookupDTO viewDTO = new ViewLookupDTO();
    List<CustomerGroupDTO> customerGroupList = new ArrayList<CustomerGroupDTO>();
    List<String> customerListGroup = new ArrayList<>();
    AbstractSelectionDTO selectionDto;
    boolean submitted = Boolean.FALSE;
    Window instance = null;
    String vmSid="0", viewCategory;

    public InventoryCustomerGroupLookup(int projectionId, AbstractSelectionDTO selectionDto) {
        super("Inventory Customer Group");
        this.projectionId = projectionId;
        this.selectionDto = selectionDto;
        groupDTO.setProjectionId(projectionId);
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        init();
        configureFields();
        instance = this;
    }

    public void init() {
        setClosable(true);
        setResizable(true);
        setModal(true);
        setSizeFull();
//        setWidth(NumericConstants.FLOAT_SIXTY, Sizeable.Unit.PERCENTAGE);
//        setHeight(NumericConstants.FLOAT_FORTY, Sizeable.Unit.PERCENTAGE);
        setContent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/Inventory/customerGroupLookup.xml"), this));
        configureFields();
    }

    private void configureFields() {
        addResultsTable();
        availableTableLogic.setContainerDataSource(resultsContainer);
        availableTableLogic.setPageLength(NumericConstants.FIVE);
        availableTableLogic.setItemsPerPage(NumericConstants.FIVE);
        availableTableLogic.sinkItemPerPageWithPageLength(false);

        availableTable.setVisibleColumns(ARMUtils.CUSTOMER_GROUP_LOOKUP_COLUMNS);
        availableTable.setColumnHeaders(ARMUtils.CUSTOMER_GROUP_LOOKUP_HEADERS);
        availableTable.setImmediate(true);
        availableTable.setSelectable(true);
        availableTable.setFilterGenerator(new ExtFilterGenerator() {
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                return null;
            }

            public void filterRemoved(Object propertyId) {
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                return null;
            }
        });
        availableTable.setFilterBarVisible(true);
        availableTable.setFilterDecorator(new ExtDemoFilterDecorator());
        availableTable.setWidth("100%");
        searchBtn.setImmediate(true);
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
                    Notification.show("Search Completed");
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
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
                    loadCustomerGroupList();
                    selectedTableLogic.getResultsinventoryContainer().removeAllContainerFilters();
                    List<CustomerGroupDTO> list = selectedTableLogic.getResultsinventoryContainer().getItemIds();
                    for (CustomerGroupDTO list1 : list) {
                        list1.setSelectedFlag(Boolean.TRUE);
                    }
                    getCustomerGroupDto(groupDTO);
                    selectedTableLogic.loadSetData(groupDTO, false, selectionDto);
                    availableTableLogic.loadSetData(groupDTO, false, selectionDto);
                }
            }.getConfirmationMessage(ARMMessages.getInventoryCustomerGroupADDALLErrorMsg1(), ARMMessages.getInventoryCustomerGroupADDALLErrorMsg2());

        } catch (Exception ex) {
            LOGGER.error(ex);
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
                selectedTableLogic.getResultsinventoryContainer().removeAllContainerFilters();
                List<CustomerGroupDTO> list = selectedTableLogic.getResultsinventoryContainer().getItemIds();
                for (CustomerGroupDTO list1 : list) {
                    list1.setSelectedFlag(Boolean.FALSE);
                }
                selectionDto.getCustomerGroupSidSet().clear();
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

                }

                @Override
                public void yesMethod() {
                    loadCustomerList();
                    pipelineLogic.saveCustomerGroupValue(selectedTableLogic.getResultsinventoryContainer().getItemIds(), projectionId, selectionDto);
                    submitted = Boolean.TRUE;
                    selectionDto.setReloadFlag(true);
                    instance.close();
                }
            }.getConfirmationMessage("Confirm Submit", ARMMessages.getCGLookUpSubmitConfirmTransaction3());
        }else{
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
                saveViewDTO.setCreatedUser(viewDTO.getCreatedUser());
            } else {
                saveViewDTO.setViewName(StringUtils.EMPTY);
                saveViewDTO.setViewType(StringUtils.EMPTY);
                saveViewDTO.setUserID(CommonLogic.parseStringToInteger(userId));
                saveViewDTO.setViewStatus(false);
                saveViewDTO.setCreatedUser(userId);

            }
            selectedTableLogic.getResultsinventoryContainer().removeAllContainerFilters();
            Container.Filter filter = new Equal("selectedFlag", Boolean.TRUE);
            selectedTableLogic.getResultsinventoryContainer().addContainerFilter(filter);
            saveViewDTO.setCustGrpList(selectedTableLogic.getResultsinventoryContainer().getItemIds());
            saveViewDTO.setScreenFlag(true);
            viewPopup = new SaveViewPopup(saveViewDTO);
            getUI().addWindow(viewPopup);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @UiHandler("closeBtn")
    public void btnCloseBtnLogic(Button.ClickEvent event) {
        selectedTableLogic.getResultsinventoryContainer().removeAllContainerFilters();
        selectedTableLogic.getResultsinventoryContainer().removeAllItems();
        availableTableLogic.getResultsinventoryContainer().removeAllContainerFilters();
        availableTableLogic.getResultsinventoryContainer().removeAllItems();
        if (!selectionDto.isReloadFlag()) {
            selectionDto.getCustomerGroupSidSet().clear();
        }
        this.close();
    }

    @UiHandler("deleteViewBtn")
    public void deleteButtonClick(Button.ClickEvent event) {
        
          try {
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
                   ExclusionDetailsLogic arLogic = new ExclusionDetailsLogic();
                   arLogic.deleteViewLogic(viewDTO.getViewSid());
                   refreshLookup();
                }
            }.getConfirmationMessage(ARMMessages.getResetConfirmationMessage(), ARMMessages.getDeleteMessage_exclusion());

        } catch (Exception ex) {
            LOGGER.error(ex);
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
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                return null;
            }

            public void filterRemoved(Object propertyId) {
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                return null;
            }
        });
        selectedTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedTableLogic.setContainerDataSource(resultsinventoryContainer);

        selectedTable.setVisibleColumns(ARMUtils.CUSTOMER_GROUP_INVENTORY_COLUMNS);
        selectedTable.setColumnHeaders(ARMUtils.CUSTOMER_GROUP_INVENTORY_HEADERS);
        selectedTable.setImmediate(true);
        selectedTable.setSelectable(true);
        selectedTableLogic.setPageLength(NumericConstants.FIVE);
        selectedTableLogic.sinkItemPerPageWithPageLength(false);
        selectedTable.setFilterBarVisible(true);

        selectedTable.setWidth("100%");
        selectedTable.setTableFieldFactory(inventoryCustomer);
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
            indicator.addItem(true);
            indicator.addItem(false);
            indicator.setItemCaption(nullItem, ConstantsUtils.SELECT_ONE);
            indicator.setItemCaption(true, "+");
            indicator.setItemCaption(false, "-");
            indicator.select(nullItem);
        } catch (Exception e) {
            LOGGER.error("Error while loading Drop down :" + indicator + " with :" + e);
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
                    if ((Boolean) "null".equalsIgnoreCase(String.valueOf(dtoValue.getIndicator()))) {
                        ret = false;
                        break;
                    }
                }
            }
        }
        return ret;
    }
    CustomTextField.ClickListener viewListener = new CustomTextField.ClickListener() {
        @Override
        public void click(CustomTextField.ClickEvent event) {
            try {
                int userIdValue = userId.equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(userId.replaceAll("\\D+", StringUtils.EMPTY));
                if (viewLookUp == null) {
                    viewLookUp = new PrivatePublicLookup(event.getComponent().getCaption(), userIdValue, "CG",event.getComponent().getId(),"Customer Group");
                } else {
                    viewLookUp.reloadScreen(event.getComponent().getCaption(), userIdValue, "CG", event.getComponent().getId());
                }
                getUI().addWindow(viewLookUp);

                viewLookUp.addCloseListener(new CloseListener() {

                    @Override
                    public void windowClose(CloseEvent e) {
                        if (viewLookUp.getDtoValue().getCheckFlag()) {
                          lookupLoadLogic(viewLookUp.getDtoValue());
                        }
                    }
                });

            } catch (Exception ex) {
                LOGGER.error(ex);
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
        return customerGroupList;
    }

    public void setCustomerGroupList(List<CustomerGroupDTO> customerGroupList) {
        this.customerGroupList = customerGroupList;
    }

    public List<String> getCustomerListGroup() {
        return customerListGroup;
    }

    public void setCustomerListGroup(List<String> customerListGroup) {
        this.customerListGroup = customerListGroup;
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
                customerGroupName.setValue(StringUtils.EMPTY);
                customerGroupNo.setValue(StringUtils.EMPTY);
            }
        }.getConfirmationMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageID003());
    }

    @UiHandler("resetBtn")
    public void resetButtonLogic(Button.ClickEvent event) {
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
                if ("0".equals(vmSid)) {
                    resetFields();
                } else {
                    loadInitialArc(vmSid, viewCategory);
                }
            }
        }.getConfirmationMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageId006());
    }

    public void refreshLookup() {
        customerGroupName.setValue(StringUtils.EMPTY);
        customerGroupNo.setValue(StringUtils.EMPTY);
        resultsContainer.removeAllItems();
        resultsinventoryContainer.removeAllItems();
        availableTable.removeAllItems();
        selectedTable.removeAllItems();
        privateView.setValue(StringUtils.EMPTY);
        publicView.setValue(StringUtils.EMPTY);
        viewLookUp.getDtoValue().setCheckFlag(false);
    }
    
    public void lookupLoadLogic(ViewLookupDTO viewLookupDTO) {
        privateView.setValue(StringUtils.EMPTY);
        publicView.setValue(StringUtils.EMPTY);
        
        if ("publicView".equalsIgnoreCase(viewLookupDTO.getViewType())) {
            publicView.setValue(viewLookupDTO.getViewName());
            publicView.setImmediate(true);
        } else {
            privateView.setValue(viewLookupDTO.getViewName());
            privateView.setImmediate(true);
        }
        setViewDTO(viewLookupDTO);
        groupDTO.setViewFlag(true);
        groupDTO.setViewSid(viewLookupDTO.getViewSid());
        availableTableLogic.getResultsinventoryContainer().removeAllItems();
        availableTableLogic.setFirstCount(true);
        selectedTableLogic.setFirstCount(true);
        availableTableLogic.loadSetData(groupDTO, false, selectionDto);
        selectedTableLogic.loadSetData(groupDTO, false, selectionDto);
        groupDTO.setViewFlag(false);
    }
    
    public void loadInitialArc(String vmSid,String viewCategory) {
        List<ViewLookupDTO> viewLookupDTO = pipelineLogic.getARCSavedPublicViewList(vmSid,viewCategory);
        if (viewLookupDTO != null && !viewLookupDTO.isEmpty()) {
            lookupLoadLogic(viewLookupDTO.get(0));
            this.vmSid=vmSid;
            this.viewCategory=viewCategory;
        }
    }
    
    public void resetFields() {
        resultsContainer.removeAllItems();
        resultsinventoryContainer.removeAllItems();
        availableTable.removeAllItems();
        selectedTable.removeAllItems();
        privateView.setValue(StringUtils.EMPTY);
        publicView.setValue(StringUtils.EMPTY);
    }

    public void getCustomerGroupDto(CustomerGroupDTO groupDTO) {
        groupDTO.setCustomerGroupName(customerGroupName.getValue().trim());
        groupDTO.setCustomerGroupNo(customerGroupNo.getValue().trim());
    }
    public void loadCustomerGroupList(){
        for (CustomerGroupDTO obj : availableTableLogic.getResultsinventoryContainer().getItemIds()) {
            selectionDto.addCustomerGroupSidSet(obj.getCustomerGroupSid());
}
    }
    }
