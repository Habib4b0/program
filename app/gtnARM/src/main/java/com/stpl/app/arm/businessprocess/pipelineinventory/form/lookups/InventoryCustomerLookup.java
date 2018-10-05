/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.form.lookups;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.ExclusionLookupDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.ViewLookupDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineaccrual.lookups.PrivatePublicLookup;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.ExclusionDetailsLogic;
import com.stpl.app.arm.businessprocess.pipelineaccrual.lookups.SaveViewPopup;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.CustomerGroupDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.logic.PipelineInventoryLookupLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.utils.ConstantsUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.TableFieldFactory;
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
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class InventoryCustomerLookup extends Window {

    @UiField("privateView")
    private CustomTextField privateView;

    @UiField("publicView")
    private CustomTextField publicView;
    @UiField("inventoryTableLayout")
    private VerticalLayout inventoryTableLayout;
    @UiField("closeBtn")
    private Button closeBtn;
    @UiField("resetBtn")
    private Button resetBtn;
    @UiField("deleteViewBtn")
    private Button deleteViewBtn;
    private ExtFilterTable customerTable = new ExtFilterTable();
    private final BeanItemContainer<CustomerGroupDTO> resultsContainer = new BeanItemContainer<>(CustomerGroupDTO.class);
    private Object[] visibleColumns = {"customerName", "include", "indicator"};
    private String[] visibleHeaders = {"Customer", "Include", "+/-Indicator"};
    private PrivatePublicLookup customerViewLookUp;
    private int projectionId = 0;
    private String userId = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID);
    private ViewLookupDTO viewDTO = new ViewLookupDTO();
    private InventoryCustomerFeildFactory inventoryCustomer = new InventoryCustomerFeildFactory();
    private PipelineInventoryLookupLogic pipelineLogic = new PipelineInventoryLookupLogic();
    private List<CustomerGroupDTO> cusomerList = new ArrayList<>();
    private List<String> customerGroupList = new ArrayList<>();
    private AbstractSelectionDTO selectionDto;
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryCustomerLookup.class);
    private boolean submitted = false;
    private Window instance = null;

    public InventoryCustomerLookup(int projectionId, AbstractSelectionDTO selectionDto) {
        super("");
        this.projectionId = projectionId;
        this.selectionDto = selectionDto;
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        setContent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/Inventory/customerLookup.xml"), this));
        configureFields();
        instance = this;
    }

    private void configureFields() {
        privateView.setStyleName("searchicon");
        privateView.addStyleName("minWidthLabelImportant");
        publicView.setStyleName("searchicon");
        publicView.addStyleName("minWidthLabelImportant");
        setCaption("Customer Lookup");
        setDraggable(true);
        center();
        setModal(true);
        setResizable(true);
        setClosable(true);
        privateView.addClickListener(customerViewListener);
        publicView.addClickListener(customerViewListener);
        configureTable();
        inventoryTableLayout.addComponent(customerTable);
        inventoryTableLayout.setHeight("50%");
        loadResultTable();
        deleteViewBtn.setEnabled(false);
    }

    private CustomTextField.ClickListener customerViewListener = new CustomTextField.ClickListener() {
        @Override
        public void click(CustomTextField.ClickEvent event) {
            try {
                int userIdValue = userId.equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(userId.replaceAll("\\D+", StringUtils.EMPTY));
                if (customerViewLookUp == null) {
                    customerViewLookUp = new PrivatePublicLookup(event.getComponent().getCaption(), userIdValue, "C", event.getComponent().getId(), "Customer");
                } else {
                    customerViewLookUp.reloadScreen(event.getComponent().getCaption(), userIdValue, "C", event.getComponent().getId());
                }
                getUI().addWindow(customerViewLookUp);

                customerViewLookUp.addCloseListener(new CloseListener() {

                    @Override
                    public void windowClose(CloseEvent e) {
                        if (customerViewLookUp.isSelectFlag()) {
                            lookupLoadLogic(customerViewLookUp.getDtoValue());
                            deleteViewBtn.setEnabled(true);
                        } else {
                            publicView.setValue("");
                            privateView.setValue("");
                            loadResultTable();

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

    private void configureTable() {
        customerTable.setContainerDataSource(resultsContainer);
        customerTable.setVisibleColumns(visibleColumns);
        customerTable.setColumnHeaders(visibleHeaders);
        customerTable.setWidth("100%");
        customerTable.setHeight("50%");
        customerTable.setTableFieldFactory(inventoryCustomer);
        customerTable.setEditable(true);
        customerTable.setSelectable(true);
        customerTable.setMultiSelect(true);
    }

    class InventoryCustomerFeildFactory implements TableFieldFactory {

        @Override
        public Field<?> createField(Container container, Object itemId, Object customerPropertyId, Component uiContext) {
            if ("include".equals(customerPropertyId.toString())) {
                ExtCustomCheckBox customerCheckRecord = new ExtCustomCheckBox();
                customerCheckRecord.setImmediate(true);
                return customerCheckRecord;

            }
            if ("indicator".equals(customerPropertyId.toString())) {
                CustomComboBox customerIndicator = new CustomComboBox();
                loadIndicatorDDLB(customerIndicator);
                return customerIndicator;

            }
            return null;
        }

    }

    public CustomComboBox loadIndicatorDDLB(CustomComboBox customerIndicator) {

        try {
            customerIndicator.removeAllItems();
            customerIndicator.setImmediate(true);
            Object nullItem = customerIndicator.addItem();
            customerIndicator.setNullSelectionItemId(nullItem);
            customerIndicator.addItem(Boolean.TRUE);
            customerIndicator.addItem(Boolean.FALSE);
            customerIndicator.setItemCaption(nullItem, GlobalConstants.getSelectOne());
            customerIndicator.setItemCaption(Boolean.TRUE, "+");
            customerIndicator.setItemCaption(Boolean.FALSE, "-");
            customerIndicator.select(nullItem);
        } catch (Exception e) {
            LOGGER.error("Error in loadIndicatorDDLB :", e);
        }
        return customerIndicator;
    }

    public boolean checkValidField() {
        boolean ret = false;
        List<CustomerGroupDTO> list = resultsContainer.getItemIds();
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

    public void loadResultTable() {
        try {
            resultsContainer.removeAllItems();
            resultsContainer.addAll(pipelineLogic.getPipelineInventory(projectionId, selectionDto));
        } catch (Exception ex) {
            LOGGER.error("Error in loadResultTable :", ex);
        }

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
                    loadCustomerGroupList();
                    pipelineLogic.saveCustomerGroupValue(resultsContainer.getItemIds(), projectionId, selectionDto);
                    submitted = true;
                    instance.close();
                }
            }.getConfirmationMessage("Confirm Submit", ARMMessages.getCLookUpSubmitConfirmTransaction3());

        } else {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getLookUpSubmitErrorTransaction3());
        }

    }

    public void loadCustomerGroupList() {

        final List<CustomerGroupDTO> selectionDTO = getSelectedCustomers();
        setCusomerList(selectionDTO);
        List<String> customerList = new ArrayList<>();
        for (CustomerGroupDTO customerGroupDTO : selectionDTO) {
            customerList.add(customerGroupDTO.getCustomerName() + "~" + customerGroupDTO.getCompanyMasterSid());
        }
        selectionDto.setCustomerList(customerList);
        setCustomerGroupList(customerList);
    }

    private List<CustomerGroupDTO> getSelectedCustomers() {
        List<CustomerGroupDTO> returnList = new ArrayList<>();
        List<CustomerGroupDTO> containerList = resultsContainer.getItemIds();
        for (CustomerGroupDTO customerGroupDTO : containerList) {
            if (customerGroupDTO.isInclude()) {
                returnList.add(customerGroupDTO);
            }
        }
        return returnList;
    }

    @UiHandler("saveViewBtn")
    public void btnSaveViewLogic(Button.ClickEvent event) {
        try {
            ExclusionLookupDTO saveViewDTO = new ExclusionLookupDTO();
            if (!StringUtils.EMPTY.equalsIgnoreCase(publicView.getValue()) || !StringUtils.EMPTY.equalsIgnoreCase(privateView.getValue())) {
                saveViewDTO.setViewName(viewDTO.getViewName());
                saveViewDTO.setViewType(viewDTO.getViewType());
                saveViewDTO.setViewMasterSid(viewDTO.getViewSid());
                saveViewDTO.setUserID(userId.equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(userId.replaceAll("\\D+", StringUtils.EMPTY)));
                saveViewDTO.setViewStatus(true);
                saveViewDTO.setSessionUserID(CommonLogic.parseStringToInteger(userId));
                saveViewDTO.setCreatedUser(viewDTO.getCreatedUser());
            } else {
                saveViewDTO.setViewName(StringUtils.EMPTY);
                saveViewDTO.setViewType(StringUtils.EMPTY);
                saveViewDTO.setUserID(userId.equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(userId.replaceAll("\\D+", StringUtils.EMPTY)));
                saveViewDTO.setViewStatus(false);
                saveViewDTO.setSessionUserID(CommonLogic.parseStringToInteger(userId));
                saveViewDTO.setCreatedUser(userId);
            }
            saveViewDTO.setCustGrpList(resultsContainer.getItemIds());
            saveViewDTO.setScreenFlag(true);
            SaveViewPopup viewPopup = new SaveViewPopup(saveViewDTO);
            getUI().addWindow(viewPopup);
        } catch (Exception e) {
            LOGGER.error("Error in btnSaveViewLogic :", e);
        }
    }

    public void reloadScreen(int projectionId) {
        this.projectionId = projectionId;
        loadResultTable();
    }

    public List<CustomerGroupDTO> getCusomerList() {
        return CommonLogic.getInstance().getArrayListCloned(cusomerList);
    }

    public void setCusomerList(List<CustomerGroupDTO> cusomerList) {
        this.cusomerList = CommonLogic.getInstance().getArrayListCloned(cusomerList);
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
                    loadResultTable();
                    publicView.setValue(StringUtils.EMPTY);
                    privateView.setValue(StringUtils.EMPTY);

                }
            }.getConfirmationMessage(ARMMessages.getResetConfirmationMessage(), ARMMessages.getDeleteMessage_exclusion());

        } catch (Exception ex) {
            LOGGER.error("Error in deleteButtonClick :", ex);
        }
    }

    public List<String> getCustomerGroupList() {
        return CommonLogic.getInstance().getArrayListCloned(customerGroupList);
    }

    public void setCustomerGroupList(List<String> customerGroupList) {
        this.customerGroupList = CommonLogic.getInstance().getArrayListCloned(customerGroupList);
    }

    @UiHandler("closeBtn")
    public void closeBtnLogic(Button.ClickEvent event) {
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
                    close();
                    LOGGER.debug("Ending Close operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    /*Resoved code*/
    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    @UiHandler("resetBtn")
    public void resetFields(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the pop-up", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClicked(final ButtonId buttonId) {
                if ("yes".equalsIgnoreCase(buttonId.name())) {
                    LOGGER.debug("Entering Reset operation");
                    resetFields();
                    LOGGER.debug("Ending Reset operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    public void resetFields() {
        LOGGER.debug("Inside table Reset");
        inventoryCustomer = new InventoryCustomerFeildFactory();
        resultsContainer.removeAllItems();
        configureTable();
        loadResultTable();
        privateView.setValue(StringUtils.EMPTY);
        publicView.setValue(StringUtils.EMPTY);
    }

    public void lookupLoadLogic(ViewLookupDTO viewLookupDTO) {
        privateView.setValue(StringUtils.EMPTY);
        publicView.setValue(StringUtils.EMPTY);
        if ("publicView".equalsIgnoreCase(viewLookupDTO.getViewType())) {
            publicView.setValue(viewLookupDTO.getViewName());
            publicView.setImmediate(true);
            privateView.setValue(StringUtils.EMPTY);
        } else {
            privateView.setValue(viewLookupDTO.getViewName());
            privateView.setImmediate(true);
            publicView.setValue(StringUtils.EMPTY);
        }
        setViewDTO(viewLookupDTO);
        resultsContainer.removeAllItems();
        resultsContainer.addAll(pipelineLogic.getCustomerView(viewDTO.getViewSid()));
    }

    public void loadInitialArc(String vmSid, String viewCategory) {
        List<ViewLookupDTO> viewLookupDTO = pipelineLogic.getARCSavedPublicViewList(vmSid, viewCategory);
        if (viewLookupDTO != null && !viewLookupDTO.isEmpty()) {
            lookupLoadLogic(viewLookupDTO.get(0));
        }
    }

    @Override
    public boolean equals(Object invCusobj) {
        return super.equals(invCusobj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream invCusobj) throws IOException {
        invCusobj.defaultWriteObject();
    }

    private void readObject(ObjectInputStream invCusobj) throws IOException, ClassNotFoundException {
        invCusobj.defaultReadObject();
    }

}
