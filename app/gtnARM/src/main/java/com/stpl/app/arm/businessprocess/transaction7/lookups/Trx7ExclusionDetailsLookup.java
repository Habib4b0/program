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
import com.stpl.app.arm.utils.CommonConstant;

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
    private CustomTextField tr7PrivateView;
    /**
     * Allows the user to select a previously saved Exclusion Details view. All
     * publicly saved views by any user will be available in the drop down. The
     * selected of the view will auto-populate the Exclusion Details group box
     * with the values that were saved in the view
     *
     */
    @UiField("publicView")
    private CustomTextField tr7PublicView;
    /**
     * Allows the user to select a specific field value based on the Actual
     * Customer/Product GTS data set.
     */
    @UiField("feildName")
    private ComboBox tr7FieldName;

    @UiField("deleteViewBtn")
    private Button tr7DeleteViewBtn;

    private final BeanItemContainer<ExclusionLookupDTO> tr7AvailableResultsContainer = new BeanItemContainer<>(ExclusionLookupDTO.class);
    private final BeanItemContainer<ExclusionLookupDTO> tr7SelectedResultsContainer = new BeanItemContainer<>(ExclusionLookupDTO.class);

    /**
     * Displays all values based on the â€˜Field Nameâ€™ selection the
     * user selected. It will display all of the distinct values for the
     * selected Field Name.
     *
     */
    @UiField("availableCustomers")
    private ExtFilterTable tr7AvailableCustomers;
    @UiField("selectedCustomer")
    private ExtFilterTable tr7SelectedCustomer;
    private Trx7PrivatePublicLookup tr7ViewLookUp;
    private Trx7ExclusionDetailsLogic tr7ArLogic = new Trx7ExclusionDetailsLogic();
    private int tr7ProjectionSid = 0;
    private SessionDTO tr7SessionDTO;
    private DataSelectionDTO tr7DataSelectionDTO;
    private String userId = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID);
    private ViewLookupDTO tr7ViewDTO = new ViewLookupDTO();
    private StringBuilder tr7AccountId = new StringBuilder();
    private StringBuilder tr7AccountName = new StringBuilder();
    private StringBuilder tr87AccountContractId = new StringBuilder();

    /**
     * The Logger for Adjustment Rate UI the logger usually logs into a file
     * (this can be configured through jboss logging )
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Trx7ExclusionDetailsLookup.class);

    public Trx7ExclusionDetailsLookup(int projectionSid, SessionDTO sessionDTO, DataSelectionDTO dataSelectionDTO) {
        super();
        this.tr7ProjectionSid = projectionSid;
        this.tr7SessionDTO = sessionDTO;
        this.tr7DataSelectionDTO = dataSelectionDTO;
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        setContent(Clara.create(getClass().getResourceAsStream("/adjustment_rate_config/exclusion_details_lookup.xml"), this));

        configureFields();
    }

    private void configureFields() {
        tr7PrivateView.setStyleName("searchicon");
        tr7PublicView.setStyleName("searchicon");
        setCaption("Exclusion Details Popup");
        setDraggable(true);
        center();
        setModal(true);
        setResizable(false);
        configureTable();
        tr7PrivateView.setDescription("Private");
        tr7PublicView.setDescription("Public");
        tr7PrivateView.addClickListener(viewListener);
        tr7PublicView.addClickListener(viewListener);
        tr7FieldName.setImmediate(true);
        tr7FieldName.setNullSelectionAllowed(false);
        tr7FieldName.addItem(ARMConstants.getAccountId());
        tr7FieldName.addItem(ARMConstants.getAccountName());
        tr7FieldName.addItem(ARMConstants.getContractId());
        tr7FieldName.select(ARMConstants.getAccountId());
        tr7DeleteViewBtn.setEnabled(false);
        getIntialLoad();
        setFieldValues();
        getFieldValue();
        tr7FieldName.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                getFieldValue();
            }
        });
        tr7FieldName.focus();
    }

    private void configureTable() {
        tr7AvailableCustomers.setContainerDataSource(tr7AvailableResultsContainer);
        tr7AvailableCustomers.setVisibleColumns(ARMUtils.getExclusionLookupAvialableColumns());
        tr7AvailableCustomers.setColumnHeaders(ARMUtils.getExclusionLookupAvialableHeaders());
        tr7SelectedCustomer.setContainerDataSource(tr7SelectedResultsContainer);
        tr7SelectedCustomer.setVisibleColumns(ARMUtils.getExclusionLookupSelectedColumns());
        tr7SelectedCustomer.setColumnHeaders(ARMUtils.getExclusionLookupSelectedHeaders());
        tr7AvailableCustomers.setFilterBarVisible(true);
        tr7AvailableCustomers.setFilterDecorator(new ExtDemoFilterDecorator());
        tr7SelectedCustomer.setFilterBarVisible(true);
        tr7SelectedCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
    }
    private CustomTextField.ClickListener viewListener = new CustomTextField.ClickListener() {
        @Override
        public void click(CustomTextField.ClickEvent event) {
            try {
                int userIdValue = userId.equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(userId.replaceAll("\\D+", StringUtils.EMPTY));
                if (tr7ViewLookUp == null) {
                    tr7ViewLookUp = new Trx7PrivatePublicLookup(event.getComponent().getCaption(), userIdValue, StringUtils.EMPTY, event.getComponent().getId(), "");
                } else {
                    tr7ViewLookUp.reloadScreen(event.getComponent().getCaption(), userIdValue, StringUtils.EMPTY, event.getComponent().getId());
                }
                getUI().addWindow(tr7ViewLookUp);

                tr7ViewLookUp.addCloseListener(new CloseListener() {

                    @Override
                    public void windowClose(CloseEvent e) {
                        if (tr7ViewLookUp.isSelectFlag()) {
                            tr7PrivateView.setValue(StringUtils.EMPTY);
                            tr7PublicView.setValue(StringUtils.EMPTY);
                            if ("publicView".equalsIgnoreCase(tr7ViewLookUp.getDtoValue().getViewType())) {
                                tr7PublicView.setValue(tr7ViewLookUp.getDtoValue().getViewName());
                                tr7PublicView.setImmediate(true);
                                tr7DeleteViewBtn.setEnabled(true);
                            } else {
                                tr7PrivateView.setValue(tr7ViewLookUp.getDtoValue().getViewName());
                                tr7PublicView.setImmediate(true);
                                tr7DeleteViewBtn.setEnabled(true);
                            }
                            setViewDTO(tr7ViewLookUp.getDtoValue());
                            tr7FieldName.select(tr7ViewLookUp.getDtoValue().getFieldName());
                            tr7SelectedResultsContainer.removeAllItems();
                            tr7SelectedResultsContainer.addAll(tr7ArLogic.getCompanySid(tr7ViewLookUp.getDtoValue().getViewSid()));
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
                        String createdById = tr7ViewDTO.getCreatedBy();
                        String userIdString = CommonUtils.getUserNameById(userId);

                        if (createdById.equals(userIdString)) {
                            tr7ArLogic.deleteViewLogic(tr7ViewDTO.getViewSid());
                            tr7AvailableCustomers.removeAllItems();
                            tr7AvailableResultsContainer.removeAllItems();
                            tr7SelectedCustomer.removeAllItems();
                            tr7SelectedResultsContainer.removeAllItems();
                            tr7FieldName.setValue(ARMConstants.getAccountId());
                            getFieldValue();
                            tr7PrivateView.setValue(StringUtils.EMPTY);
                            tr7PublicView.setValue(StringUtils.EMPTY);
                            tr7DeleteViewBtn.setEnabled(false);
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
            if (tr7AvailableCustomers.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(CommonConstant.MOVE_ERROR, ARMMessages.getMoveLeftRightMessage_exclusion());
                return;
            }
            if (tr7AvailableCustomers.getValue() != null) {
                tr7SelectedResultsContainer.addBean((ExclusionLookupDTO) tr7AvailableCustomers.getValue());
                tr7AvailableResultsContainer.removeItem((ExclusionLookupDTO) tr7AvailableCustomers.getValue());
                setFieldValues();
            }
            tr7AvailableCustomers.setValue(null);
        } catch (Exception e) {
            LOGGER.error("Error in moveLeftButtonClick :", e);
        }
    }

    @UiHandler("moveRightBtn")
    public void moveRightButtonClick(Button.ClickEvent event) {

        try {
//            this.close();Please select at least one Customer value to exclude. 
            if (tr7SelectedCustomer.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(CommonConstant.MOVE_ERROR, ARMMessages.getMoveLeftRightMessage_exclusion());
                return;
            }
            if (tr7SelectedCustomer.getValue() != null) {
                tr7AvailableResultsContainer.addBean((ExclusionLookupDTO) tr7SelectedCustomer.getValue());
                tr7SelectedResultsContainer.removeItem(tr7SelectedCustomer.getValue());
                setFieldValues();
                getFieldValue();
            }
            tr7SelectedCustomer.setValue(null);
        } catch (Exception e) {
            LOGGER.error("Error in moveRightButtonClick :", e);
        }
    }

    @UiHandler("moveAllBtn")
    public void moveAllButtonClick(Button.ClickEvent event) {
        LOGGER.debug("Inside moveAllBtn :");
        try {
//            this.close();Please select at least one Customer value to exclude. 
            if (tr7AvailableCustomers.getItemIds().isEmpty()) {

                return;
            }
            if (!tr7AvailableCustomers.getItemIds().isEmpty()) {
                List<String> fieldList = new ArrayList<>();
                for (ExclusionLookupDTO tr7Dto : tr7AvailableResultsContainer.getItemIds()) {
                    fieldList.add(tr7Dto.getValues());
                }
                tr7SelectedResultsContainer.addAll(tr7AvailableResultsContainer.getItemIds());
                tr7AvailableResultsContainer.removeAllItems();
                setFieldValues();
            }
        } catch (Exception e) {
            LOGGER.error("Error in moveAllButtonClick :", e);
        }
    }

    @UiHandler("submitBtn")
    public void submitButtonClick(Button.ClickEvent event) {
        try {
            if (tr7SelectedCustomer.getItemIds().isEmpty()) {
                AbstractNotificationUtils.getErrorNotification(CommonConstant.NO_VALUES_SELECTED, ARMMessages.getSubmitMessage_exclusion());
                return;
            }
            if (!tr7SelectedResultsContainer.getItemIds().isEmpty()) {
                setFieldValues();
                tr7ArLogic.saveORUpdateExclusionDetailsLookUp(tr7ProjectionSid, tr7SelectedResultsContainer.getItemIds(), tr7AccountId.toString(), tr7AccountName.toString(), tr87AccountContractId.toString(), tr7SessionDTO);
                this.close();
            }
        } catch (Exception ex) {
            LOGGER.error("Error in submitButtonClick :", ex);
        }
    }

    @UiHandler("closeBtn")
    public void tr7CloseButtonClick(Button.ClickEvent event) {
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
            tr7Exclnotifier.getOkCancelMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessage_exclusion());
        } catch (Exception e) {
            LOGGER.error("Error in resetButtonClick :", e);
        }
    }

    @UiHandler("deleteViewBtn")
    public void deleteButtonClick(Button.ClickEvent event) {
        try {
            tr7Exclnotifier.setButtonName("delete");
            if (tr7SessionDTO.getUserId().compareTo(Integer.valueOf(tr7ViewLookUp.getDtoValue().getCreatedUser())) != 0) {
                AbstractNotificationUtils.getWarningNotification(CommonConstant.DELETE_CONFIRMATION, ARMMessages.getDeleteErrorMessage_exclusion());
                return;
            }
            
            tr7Exclnotifier.getConfirmationMessage(CommonConstant.DELETE_CONFIRMATION, ARMMessages.getDeleteMessage_exclusion());
        } catch (NumberFormatException e) {
            LOGGER.error("Error in deleteButtonClick :", e);
        }
    }

    public void getFieldValue() {
        try {
            List<ExclusionLookupDTO> fieldValueList;
            String accountNames = ARMConstants.getAccountName().equalsIgnoreCase(String.valueOf(tr7FieldName.getValue())) ? tr7AccountName.toString() : tr87AccountContractId.toString();
            fieldValueList = tr7ArLogic.getFieldListValue(String.valueOf(tr7FieldName.getValue()),
                    ARMConstants.getAccountId().equalsIgnoreCase(String.valueOf(tr7FieldName.getValue())) ? tr7AccountId.toString() : accountNames);
            tr7AvailableResultsContainer.removeAllItems();
            if (!fieldValueList.isEmpty()) {
                tr7AvailableResultsContainer.addAll(fieldValueList);
            }
        } catch (Exception e) {
            LOGGER.error("Error in getFieldValue :", e);
        }
    }

    @UiHandler("saveViewBtn")
    public void saveViewButtonClick(Button.ClickEvent event) {
        try {
            ExclusionLookupDTO saveViewDTO = new ExclusionLookupDTO();
            if (!StringUtils.EMPTY.equalsIgnoreCase(tr7PublicView.getValue()) || !StringUtils.EMPTY.equalsIgnoreCase(tr7PrivateView.getValue())) {
                saveViewDTO.setFieldName(tr7ViewDTO.getFieldName());
                saveViewDTO.setViewName(tr7ViewDTO.getViewName());
                saveViewDTO.setViewType(tr7ViewDTO.getViewType());
                saveViewDTO.setViewMasterSid(tr7ViewDTO.getViewSid());
                saveViewDTO.setUserID(userId.equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(userId.replaceAll("\\D+", StringUtils.EMPTY)));
                saveViewDTO.setViewStatus(true);
                saveViewDTO.setCreatedBy(tr7ViewDTO.getCreatedBy());
                saveViewDTO.setCreatedUser(tr7ViewDTO.getCreatedUser());
            } else {
                saveViewDTO.setFieldName(String.valueOf(tr7FieldName.getValue()));
                saveViewDTO.setViewName(StringUtils.EMPTY);
                saveViewDTO.setViewType(StringUtils.EMPTY);
                saveViewDTO.setUserID(userId.equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(userId.replaceAll("\\D+", StringUtils.EMPTY)));
                saveViewDTO.setViewStatus(false);
                saveViewDTO.setCreatedBy(tr7ViewDTO.getCreatedBy());
                saveViewDTO.setCreatedUser(tr7ViewDTO.getCreatedUser());
            }
            saveViewDTO.setFieldList(tr7SelectedResultsContainer.getItemIds());
            Trx7SaveViewPopup viewPopup = new Trx7SaveViewPopup(saveViewDTO);
            getUI().addWindow(viewPopup);
        } catch (Exception e) {
            LOGGER.error("Error in saveViewButtonClick :", e);
        }

    }

    public void getIntialLoad() {

        tr7FieldName.select(ARMConstants.getAccountId());
        tr7SelectedResultsContainer.removeAllItems();
        tr7AvailableResultsContainer.removeAllItems();
        List<ExclusionLookupDTO> dumbyResultsContainer = Collections.emptyList();
        tr7SelectedResultsContainer.addAll(tr7ProjectionSid != 0 ? tr7ArLogic.getIntialLoadValue(tr7ProjectionSid) : dumbyResultsContainer);
        int returnValue = tr7ArLogic.loadViewRateConfig(tr7DataSelectionDTO);
        if (returnValue != 0) {
            ViewLookupDTO dto = tr7ArLogic.loadViewViewName(returnValue);
            setViewDTO(dto);
            if ("Public".equals(dto.getViewType()) || "publicView".equals(dto.getViewType())) {
                tr7PublicView.setValue(dto.getViewName());
            } else {
                tr7PrivateView.setValue(dto.getViewName());
            }
            tr7SelectedResultsContainer.addAll(tr7ArLogic.getCompanySid(dto.getViewSid()));

        } else {
            tr7PublicView.setValue(StringUtils.EMPTY);
            tr7PrivateView.setValue(StringUtils.EMPTY);
            tr7FieldName.select(ARMConstants.getAccountId());
        }
    }

    public ViewLookupDTO getViewDTO() {
        return tr7ViewDTO;
    }

    public void setViewDTO(ViewLookupDTO viewDTO) {
        this.tr7ViewDTO = viewDTO;
    }

    public void setFieldValues() {
        tr7AccountId = new StringBuilder();
        tr7AccountName = new StringBuilder();
        tr87AccountContractId = new StringBuilder();
        for (ExclusionLookupDTO dto : tr7SelectedResultsContainer.getItemIds()) {
            if (ARMConstants.getAccountId().equalsIgnoreCase(dto.getExcludedField())) {
                tr7AccountId.append(ARMUtils.SINGLE_QUOTES).append(dto.getValues()).append("',");
            } else if (ARMConstants.getAccountName().equalsIgnoreCase(dto.getExcludedField())) {
                tr7AccountName.append(ARMUtils.SINGLE_QUOTES).append(dto.getValues()).append("',");
            } else if (ARMConstants.getContractId().equalsIgnoreCase(dto.getExcludedField())) {
                tr87AccountContractId.append(ARMUtils.SINGLE_QUOTES).append(dto.getValues()).append("',");
            }
        }
        tr7AccountId.replace(tr7AccountId.length() > 0 ? (tr7AccountId.length() - 1) : 0, tr7AccountId.length(), "");
        tr7AccountName.replace(tr7AccountName.length() > 0 ? (tr7AccountName.length() - 1) : 0, tr7AccountName.length(), "");
        tr87AccountContractId.replace(tr87AccountContractId.length() > 0 ? (tr87AccountContractId.length() - 1) : 0, tr87AccountContractId.length(), "");
        check(tr7AccountId);
        check(tr7AccountName);
        check(tr87AccountContractId);
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
