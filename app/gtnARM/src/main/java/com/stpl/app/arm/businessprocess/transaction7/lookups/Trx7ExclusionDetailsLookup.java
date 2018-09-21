/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.lookups;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.ExclusionLookupDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.ViewLookupDTO;
import com.stpl.app.arm.businessprocess.transaction7.logic.Trx7ExclusionDetailsLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;

import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.ConstantsUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Window;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * This Popup will allow the user to exclude certain Customers (GTS Units) from
 * the Net Units calculation on the Sales Tab. The client will use this popup to
 * exclude all â€˜Closed Planâ€™ Customers such as â€˜ESI and
 * Kaiserâ€™ for example. They will be able to use the â€˜Field
 * Nameâ€™ drop down to search by all the fields in the actual Customer
 * Gross Trades Sales data set.
 *
 * @author 
 */
public class Trx7ExclusionDetailsLookup extends Window implements Serializable {

    /**
     * Allows the user to select a previously saved Exclusion Details view. Only
     * views saved by the current user will be available in the drop down. The
     * selected of the view will auto-populate the Exclusion Details group box
     * with the values that were saved in the view
     *
     */
    @UiField("privateView")
    private CustomTextField privateView;
    /**
     * Allows the user to select a previously saved Exclusion Details view. All
     * publicly saved views by any user will be available in the drop down. The
     * selected of the view will auto-populate the Exclusion Details group box
     * with the values that were saved in the view
     *
     */
    @UiField("publicView")
    private CustomTextField publicView;
    /**
     * Allows the user to select a specific field value based on the Actual
     * Customer/Product GTS data set.
     */
    @UiField("feildName")
    private ComboBox fieldName;

    @UiField("deleteViewBtn")
    private Button deleteViewBtn;

    private final BeanItemContainer<ExclusionLookupDTO> availableResultsContainer = new BeanItemContainer<>(ExclusionLookupDTO.class);
    private final BeanItemContainer<ExclusionLookupDTO> selectedResultsContainer = new BeanItemContainer<>(ExclusionLookupDTO.class);

    /**
     * Displays all values based on the â€˜Field Nameâ€™ selection the
     * user selected. It will display all of the distinct values for the
     * selected Field Name.
     *
     */
    @UiField("availableCustomers")
    private ExtFilterTable availableCustomers;
    @UiField("selectedCustomer")
    private ExtFilterTable selectedCustomer;
    private Trx7PrivatePublicLookup viewLookUp;
    private Trx7ExclusionDetailsLogic arLogic = new Trx7ExclusionDetailsLogic();
    private int projectionSid = 0;
    private SessionDTO sessionDTO;
    private DataSelectionDTO dataSelectionDTO;
    private String userId = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID);
    private ViewLookupDTO viewDTO = new ViewLookupDTO();
    private StringBuilder accountId = new StringBuilder();
    private StringBuilder accountName = new StringBuilder();
    private StringBuilder accountContractId = new StringBuilder();

    /**
     * The Logger for Adjustment Rate UI the logger usually logs into a file
     * (this can be configured through jboss logging )
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Trx7ExclusionDetailsLookup.class);

    public Trx7ExclusionDetailsLookup(int projectionSid, SessionDTO sessionDTO, DataSelectionDTO dataSelectionDTO) {
        super();
        this.projectionSid = projectionSid;
        this.sessionDTO = sessionDTO;
        this.dataSelectionDTO = dataSelectionDTO;
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        setContent(Clara.create(getClass().getResourceAsStream("/adjustment_rate_config/exclusion_details_lookup.xml"), this));

        configureFields();
    }

    private void configureFields() {
        privateView.setStyleName("searchicon");
        publicView.setStyleName("searchicon");
        setCaption("Exclusion Details Look up");
        setDraggable(true);
        center();
        setModal(true);
        setResizable(false);
        configureTable();
        privateView.setDescription("Private");
        publicView.setDescription("Public");
        privateView.addClickListener(viewListener);
        publicView.addClickListener(viewListener);
        fieldName.setImmediate(true);
        fieldName.setNullSelectionAllowed(false);
        fieldName.addItem(ARMConstants.getAccountId());
        fieldName.addItem(ARMConstants.getAccountName());
        fieldName.addItem(ARMConstants.getContractId());
        fieldName.select(ARMConstants.getAccountId());
        deleteViewBtn.setEnabled(false);
        getIntialLoad();
        setFieldValues();
        getFieldValue();
        fieldName.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                getFieldValue();
            }
        });
        fieldName.focus();
    }

    private void configureTable() {
        availableCustomers.setContainerDataSource(availableResultsContainer);
        availableCustomers.setVisibleColumns(ARMUtils.getExclusionLookupAvialableColumns());
        availableCustomers.setColumnHeaders(ARMUtils.getExclusionLookupAvialableHeaders());
        selectedCustomer.setContainerDataSource(selectedResultsContainer);
        selectedCustomer.setVisibleColumns(ARMUtils.getExclusionLookupSelectedColumns());
        selectedCustomer.setColumnHeaders(ARMUtils.getExclusionLookupSelectedHeaders());
        availableCustomers.setFilterBarVisible(true);
        availableCustomers.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedCustomer.setFilterBarVisible(true);
        selectedCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
    }
    private CustomTextField.ClickListener viewListener = new CustomTextField.ClickListener() {
        @Override
        public void click(CustomTextField.ClickEvent event) {
            try {
                int userIdValue = userId.equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(userId.replaceAll("\\D+", StringUtils.EMPTY));
                if (viewLookUp == null) {
                    viewLookUp = new Trx7PrivatePublicLookup(event.getComponent().getCaption(), userIdValue, StringUtils.EMPTY, event.getComponent().getId(), "");
                } else {
                    viewLookUp.reloadScreen(event.getComponent().getCaption(), userIdValue, StringUtils.EMPTY, event.getComponent().getId());
                }
                getUI().addWindow(viewLookUp);

                viewLookUp.addCloseListener(new CloseListener() {

                    @Override
                    public void windowClose(CloseEvent e) {
                        if (viewLookUp.isSelectFlag()) {
                            privateView.setValue(StringUtils.EMPTY);
                            publicView.setValue(StringUtils.EMPTY);
                            if ("publicView".equalsIgnoreCase(viewLookUp.getDtoValue().getViewType())) {
                                publicView.setValue(viewLookUp.getDtoValue().getViewName());
                                publicView.setImmediate(true);
                                deleteViewBtn.setEnabled(true);
                            } else {
                                privateView.setValue(viewLookUp.getDtoValue().getViewName());
                                publicView.setImmediate(true);
                                deleteViewBtn.setEnabled(true);
                            }
                            setViewDTO(viewLookUp.getDtoValue());
                            fieldName.select(viewLookUp.getDtoValue().getFieldName());
                            selectedResultsContainer.removeAllItems();
                            selectedResultsContainer.addAll(arLogic.getCompanySid(viewLookUp.getDtoValue().getViewSid()));
                        }
                    }
                });
            } catch (Exception ex) {
                LOGGER.error("Error in viewLookUp :", ex);
            }
        }
    };
    private final Tr7ExclCustomNotification tr7Exclnotifier = new Tr7ExclCustomNotification();

    class Tr7ExclCustomNotification extends AbstractNotificationUtils {

        private String tr7ExclButtonName;

        public Tr7ExclCustomNotification() {
            /*
        THE DEFAULT CONSTRUCTOR
             */
        }

        @Override
        public void noMethod() {
            LOGGER.debug("Inside CustomNotification NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :{}", tr7ExclButtonName);
            if (null != tr7ExclButtonName) {
                switch (tr7ExclButtonName) {
                    case "reset":
                        getIntialLoad();
                        setFieldValues();
                        getFieldValue();
                        break;
                    case "delete":
                        // delete logic
                        String createdById = viewDTO.getCreatedBy();
                        String userIdString = CommonUtils.getUserNameById(userId);

                        if (createdById.equals(userIdString)) {
                            arLogic.deleteViewLogic(viewDTO.getViewSid());
                            availableCustomers.removeAllItems();
                            availableResultsContainer.removeAllItems();
                            selectedCustomer.removeAllItems();
                            selectedResultsContainer.removeAllItems();
                            fieldName.setValue(ARMConstants.getAccountId());
                            getFieldValue();
                            privateView.setValue(StringUtils.EMPTY);
                            publicView.setValue(StringUtils.EMPTY);
                            deleteViewBtn.setEnabled(false);
                        } else {
                            AbstractNotificationUtils.getErrorNotification("Error", "You can only delete views that you have created.");
                        }

                        break;
                    default:
                        LOGGER.debug("BUTTON SHOULD BE EITHER DELETE OR RESET");
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.tr7ExclButtonName = buttonName;
        }

    }

    @UiHandler("moveLeftBtn")
    public void moveLeftButtonClick(Button.ClickEvent event) {
        try {
//            this.close();Please select at least one Customer value to exclude. 
            if (availableCustomers.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateErrorHeaderMessage(), ARMMessages.getMoveLeftRightMessage_exclusion());
                return;
            }
            if (availableCustomers.getValue() != null) {
                selectedResultsContainer.addBean((ExclusionLookupDTO) availableCustomers.getValue());
                availableResultsContainer.removeItem((ExclusionLookupDTO) availableCustomers.getValue());
                setFieldValues();
            }
            availableCustomers.setValue(null);
        } catch (Exception e) {
            LOGGER.error("Error in moveLeftButtonClick :", e);
        }
    }

    @UiHandler("moveRightBtn")
    public void moveRightButtonClick(Button.ClickEvent event) {

        try {
//            this.close();Please select at least one Customer value to exclude. 
            if (selectedCustomer.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateErrorHeaderMessage(), ARMMessages.getMoveLeftRightMessage_exclusion());
                return;
            }
            if (selectedCustomer.getValue() != null) {
                availableResultsContainer.addBean((ExclusionLookupDTO) selectedCustomer.getValue());
                selectedResultsContainer.removeItem(selectedCustomer.getValue());
                setFieldValues();
                getFieldValue();
            }
            selectedCustomer.setValue(null);
        } catch (Exception e) {
            LOGGER.error("Error in moveRightButtonClick :", e);
        }
    }

    @UiHandler("moveAllBtn")
    public void moveAllButtonClick(Button.ClickEvent event) {
        LOGGER.debug("Inside moveAllBtn :");
        try {
//            this.close();Please select at least one Customer value to exclude. 
            if (availableCustomers.getItemIds().isEmpty()) {

                return;
            }
            if (!availableCustomers.getItemIds().isEmpty()) {
                List<String> fieldList = new ArrayList<>();
                for (ExclusionLookupDTO dto : availableResultsContainer.getItemIds()) {
                    fieldList.add(dto.getValues());
                }
                selectedResultsContainer.addAll(availableResultsContainer.getItemIds());
                availableResultsContainer.removeAllItems();
                setFieldValues();
            }
        } catch (Exception e) {
            LOGGER.error("Error in moveAllButtonClick :", e);
        }
    }

    @UiHandler("submitBtn")
    public void submitButtonClick(Button.ClickEvent event) {
        try {
//            this.close();Please select at least one Customer value to exclude. 
            if (selectedCustomer.getItemIds().isEmpty()) {
                AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateErrorHeaderMessage(), ARMMessages.getSubmitMessage_exclusion());
                return;
            }
            if (!selectedResultsContainer.getItemIds().isEmpty()) {
                setFieldValues();
                arLogic.saveORUpdateExclusionDetailsLookUp(projectionSid, selectedResultsContainer.getItemIds(), accountId.toString(), accountName.toString(), accountContractId.toString(), sessionDTO);
                this.close();
            }
        } catch (Exception e) {
            LOGGER.error("Error in submitButtonClick :", e);
        }
    }

    @UiHandler("closeBtn")
    public void closeButtonClick(Button.ClickEvent event) {
        try {
            this.close();
        } catch (Exception e) {
            LOGGER.error("Error in closeButtonClick :", e);
        }
    }

    @UiHandler("resetBtn")
    public void resetButtonClick(Button.ClickEvent event) {
        try {
            tr7Exclnotifier.setButtonName("reset");
            tr7Exclnotifier.getOkCancelMessage(ARMMessages.getResetConfirmationMessage(), ARMMessages.getResetMessage_exclusion());
        } catch (Exception e) {
            LOGGER.error("Error in resetButtonClick :", e);
        }
    }

    @UiHandler("deleteViewBtn")
    public void deleteButtonClick(Button.ClickEvent event) {
        try {
            tr7Exclnotifier.setButtonName("delete");
            tr7Exclnotifier.getConfirmationMessage(ARMMessages.getResetConfirmationMessage(), ARMMessages.getDeleteMessage_exclusion());
        } catch (Exception e) {
            LOGGER.error("Error in deleteButtonClick :", e);
        }
    }

    public void getFieldValue() {
        try {
            List<ExclusionLookupDTO> fieldValueList;
            String accountNames = ARMConstants.getAccountName().equalsIgnoreCase(String.valueOf(fieldName.getValue())) ? accountName.toString() : accountContractId.toString();
            fieldValueList = arLogic.getFieldListValue(String.valueOf(fieldName.getValue()),
                    ARMConstants.getAccountId().equalsIgnoreCase(String.valueOf(fieldName.getValue())) ? accountId.toString() : accountNames);
            availableResultsContainer.removeAllItems();
            if (!fieldValueList.isEmpty()) {
                availableResultsContainer.addAll(fieldValueList);
            }
        } catch (Exception e) {
            LOGGER.error("Error in getFieldValue :", e);
        }
    }

    @UiHandler("saveViewBtn")
    public void saveViewButtonClick(Button.ClickEvent event) {
        try {
            ExclusionLookupDTO saveViewDTO = new ExclusionLookupDTO();
            if (!StringUtils.EMPTY.equalsIgnoreCase(publicView.getValue()) || !StringUtils.EMPTY.equalsIgnoreCase(privateView.getValue())) {
                saveViewDTO.setFieldName(viewDTO.getFieldName());
                saveViewDTO.setViewName(viewDTO.getViewName());
                saveViewDTO.setViewType(viewDTO.getViewType());
                saveViewDTO.setViewMasterSid(viewDTO.getViewSid());
                saveViewDTO.setUserID(userId.equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(userId.replaceAll("\\D+", StringUtils.EMPTY)));
                saveViewDTO.setViewStatus(true);
                saveViewDTO.setCreatedBy(viewDTO.getCreatedBy());
                saveViewDTO.setCreatedUser(viewDTO.getCreatedUser());
                getSelectedIdValues();
            } else {
                saveViewDTO.setFieldName(String.valueOf(fieldName.getValue()));
                saveViewDTO.setViewName(StringUtils.EMPTY);
                saveViewDTO.setViewType(StringUtils.EMPTY);
                saveViewDTO.setUserID(userId.equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(userId.replaceAll("\\D+", StringUtils.EMPTY)));
                saveViewDTO.setViewStatus(false);
                saveViewDTO.setCreatedBy(viewDTO.getCreatedBy());
                saveViewDTO.setCreatedUser(viewDTO.getCreatedUser());
                getSelectedIdValues();

            }
            saveViewDTO.setFieldList(selectedResultsContainer.getItemIds());
            Trx7SaveViewPopup viewPopup = new Trx7SaveViewPopup(saveViewDTO);
            getUI().addWindow(viewPopup);
        } catch (Exception e) {
            LOGGER.error("Error in saveViewButtonClick :", e);
        }

    }

    public void getIntialLoad() {

        fieldName.select(ARMConstants.getAccountId());
        selectedResultsContainer.removeAllItems();
        availableResultsContainer.removeAllItems();
        List<ExclusionLookupDTO> dumbyResultsContainer = Collections.emptyList();
        selectedResultsContainer.addAll(projectionSid != 0 ? arLogic.getIntialLoadValue(projectionSid) : dumbyResultsContainer);
        int returnValue = arLogic.loadViewRateConfig(dataSelectionDTO);
        if (returnValue != 0) {
            ViewLookupDTO dto = arLogic.loadViewViewName(returnValue);
            setViewDTO(dto);
            if ("Public".equals(dto.getViewType()) || "publicView".equals(dto.getViewType())) {
                publicView.setValue(dto.getViewName());
            } else {
                privateView.setValue(dto.getViewName());
            }
            selectedResultsContainer.addAll(arLogic.getCompanySid(dto.getViewSid()));

        } else {
            publicView.setValue(StringUtils.EMPTY);
            privateView.setValue(StringUtils.EMPTY);
            fieldName.select(ARMConstants.getAccountId());
        }
    }

    public void getSelectedIdValues() {
        List<String> companyIdList;
        if (!selectedResultsContainer.getItemIds().isEmpty()) {
            companyIdList = new ArrayList();
            for (ExclusionLookupDTO dtoValue : selectedResultsContainer.getItemIds()) {
                companyIdList.add(dtoValue.getValues());
            }
        }
    }

    public ViewLookupDTO getViewDTO() {
        return viewDTO;
    }

    public void setViewDTO(ViewLookupDTO viewDTO) {
        this.viewDTO = viewDTO;
    }

    public void setFieldValues() {
        accountId = new StringBuilder();
        accountName = new StringBuilder();
        accountContractId = new StringBuilder();
        for (ExclusionLookupDTO dto : selectedResultsContainer.getItemIds()) {
            if (ARMConstants.getAccountId().equalsIgnoreCase(dto.getExcludedField())) {
                accountId.append(ARMUtils.SINGLE_QUOTES).append(dto.getValues()).append("',");
            } else if (ARMConstants.getAccountName().equalsIgnoreCase(dto.getExcludedField())) {
                accountName.append(ARMUtils.SINGLE_QUOTES).append(dto.getValues()).append("',");
            } else if (ARMConstants.getContractId().equalsIgnoreCase(dto.getExcludedField())) {
                accountContractId.append(ARMUtils.SINGLE_QUOTES).append(dto.getValues()).append("',");
            }
        }
        accountId.replace(accountId.length() > 0 ? (accountId.length() - 1) : 0, accountId.length(), "");
        accountName.replace(accountName.length() > 0 ? (accountName.length() - 1) : 0, accountName.length(), "");
        accountContractId.replace(accountContractId.length() > 0 ? (accountContractId.length() - 1) : 0, accountContractId.length(), "");
        check(accountId);
        check(accountName);
        check(accountContractId);
    }

    public void check(StringBuilder sb) {
        if (sb.length() == 0) {
            sb.append("''");
        }
    }

    @Override
    public boolean equals(Object tr7ExclObj) {
        return super.equals(tr7ExclObj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream tr7ExclObj) throws IOException {
        tr7ExclObj.defaultWriteObject();
    }

    private void readObject(ObjectInputStream tr7ExclObj) throws IOException, ClassNotFoundException {
        tr7ExclObj.defaultReadObject();
    }
}
