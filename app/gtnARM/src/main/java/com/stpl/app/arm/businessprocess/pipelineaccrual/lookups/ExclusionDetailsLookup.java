/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.lookups;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.ExclusionLookupDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.ViewLookupDTO;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.ExclusionDetailsLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * This Popup will allow the user to exclude certain Customers (GTS Units) from
 * the Net Units calculation on the Sales Tab. The client will use this popup to
 * exclude all â€˜Closed Planâ€™ Customers such as â€˜ESI and Kaiserâ€™ for
 * example. They will be able to use the â€˜Field Nameâ€™ drop down to search by
 * all the fields in the actual Customer Gross Trades Sales data set.
 *
 * @author sathyaseelan.v
 */
public class ExclusionDetailsLookup extends Window {

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

     @UiField("rightAligned")
    private HorizontalLayout rightAligned;
     
    private final BeanItemContainer<ExclusionLookupDTO> availableResultsContainer = new BeanItemContainer<>(ExclusionLookupDTO.class);
    private final BeanItemContainer<ExclusionLookupDTO> selectedResultsContainer = new BeanItemContainer<>(ExclusionLookupDTO.class);

    /**
     * Displays all values based on the â€˜Field Nameâ€™ selection the user
     * selected. It will display all of the distinct values for the selected
     * Field Name.
     *
     */
    @UiField("availableCustomers")
    private ExtFilterTable availableCustomers;
    @UiField("selectedCustomer")
    private ExtFilterTable selectedCustomer;
    private PrivatePublicLookup viewLookUp;
    ExclusionDetailsLogic arLogic = new ExclusionDetailsLogic();
    SaveViewPopup viewPopup;
    SessionDTO sessionDTO;
    String userId = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID);
    List<String> companyIdList = Collections.EMPTY_LIST;
    ViewLookupDTO viewDTO = new ViewLookupDTO();
    List<ExclusionLookupDTO> dumbyResultsContainer = Collections.EMPTY_LIST;
    StringBuilder accountId = new StringBuilder();
    StringBuilder accountName = new StringBuilder();
    StringBuilder accountContractId = new StringBuilder();
    DataSelectionDTO dataSelection;
    private CustomTextField publicOrPrivateView;
    /**
     * The Logger for Adjustment Rate UI the logger usually logs into a file
     * (this can be configured through jboss logging )
     */
    private static final Logger LOGGER = Logger.getLogger(ExclusionDetailsLookup.class);

    public ExclusionDetailsLookup(DataSelectionDTO dataSelection, SessionDTO sessionDTO) {
        super();
        this.sessionDTO = sessionDTO;
        this.dataSelection = dataSelection;
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
        rightAligned.addStyleName("layout-rightpadding");
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
        availableCustomers.setVisibleColumns(ARMUtils.EXCLUSION_LOOKUP_AVIALABLE_COLUMNS);
        availableCustomers.setColumnHeaders(ARMUtils.EXCLUSION_LOOKUP_AVIALABLE_HEADERS);
        selectedCustomer.setContainerDataSource(selectedResultsContainer);
        selectedCustomer.setVisibleColumns(ARMUtils.EXCLUSION_LOOKUP_SELECTED_COLUMNS);
        selectedCustomer.setColumnHeaders(ARMUtils.EXCLUSION_LOOKUP_SELECTED_HEADERS);
        availableCustomers.setFilterBarVisible(true);
        availableCustomers.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedCustomer.setFilterBarVisible(true);
        selectedCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
    }
    CustomTextField.ClickListener viewListener = new CustomTextField.ClickListener() {
        @Override
        public void click(CustomTextField.ClickEvent event) {
            publicOrPrivateView=(CustomTextField)event.getComponent();
            try {
               int userIdValue= CommonLogic.parseStringToInteger(userId);
                if (viewLookUp == null) {
                    viewLookUp = new PrivatePublicLookup(publicOrPrivateView.getCaption(),userIdValue,StringUtils.EMPTY,publicOrPrivateView.getId(),"");
                } else {
                    viewLookUp.reloadScreen(publicOrPrivateView.getCaption(),userIdValue,StringUtils.EMPTY,publicOrPrivateView.getId());
                }
                getUI().addWindow(viewLookUp);

                viewLookUp.addCloseListener(new CloseListener() {

                    @Override
                    public void windowClose(CloseEvent e) {
                        if (viewLookUp.isSelectFlag()) {
                            privateView.setValue(StringUtils.EMPTY);
                            publicView.setValue(StringUtils.EMPTY);
                            publicOrPrivateView.setImmediate(true);
                            publicOrPrivateView.setValue(viewLookUp.getDtoValue().getViewName());                            
                            setViewDTO(viewLookUp.getDtoValue());
                            fieldName.select(viewLookUp.getDtoValue().getFieldName());
                            selectedResultsContainer.removeAllItems();
                            selectedResultsContainer.addAll(arLogic.getCompanySid(viewLookUp.getDtoValue().getViewSid()));
                        }
                    }
                });

            } catch (Exception ex) {
                 LOGGER.error(ex);
            }
        }
    };
    private final CustomNotification notifier = new CustomNotification();

    class CustomNotification extends AbstractNotificationUtils {

        String buttonName;

        @Override
        public void noMethod() {
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :" + buttonName);
            if (null != buttonName) {
                switch (buttonName) {
                    case "reset":
                          getIntialLoad();
                          getFieldValue();
                          break;
                    case "delete":
                        // delete logic
                        arLogic.deleteViewLogic(viewDTO.getViewSid());
                        availableCustomers.removeAllItems();
                        availableResultsContainer.removeAllItems();
                        selectedCustomer.removeAllItems();
                        selectedResultsContainer.removeAllItems();
                        fieldName.setValue(ARMConstants.getAccountId());
                        getFieldValue();
                        privateView.setValue(StringUtils.EMPTY);
                        publicView.setValue(StringUtils.EMPTY);
                        break;
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.buttonName = buttonName;
        }

    }

    @UiHandler("moveLeftBtn")
    public void moveLeftButtonClick(Button.ClickEvent event) {
        LOGGER.debug("Inside moveLeftBtn :");
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

        } catch (Exception e) {
             LOGGER.error(e);
        }
    }

    @UiHandler("moveRightBtn")
    public void moveRightButtonClick(Button.ClickEvent event) {
        LOGGER.debug("Inside moveRightBtn :");

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

        } catch (Exception e) {
             LOGGER.error(e);
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
             LOGGER.error(e);
        }
    }

    @UiHandler("submitBtn")
    public void submitButtonClick(Button.ClickEvent event) {
        LOGGER.debug("Inside submitBtn :");
        try {
//            this.close();Please select at least one Customer value to exclude. 
            if (selectedCustomer.getItemIds().isEmpty()) {
                AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateErrorHeaderMessage(), ARMMessages.getSubmitMessage_exclusion());
                return;
            }
            if (!selectedResultsContainer.getItemIds().isEmpty()) {
                setFieldValues();
                arLogic.saveORUpdate_Exclusion_Details_LookUp(dataSelection.getProjectionId(), selectedResultsContainer.getItemIds(), accountId.toString(), accountName.toString(), accountContractId.toString(), sessionDTO);
                this.close();
            }
        } catch (Exception e) {
           LOGGER.error(e);
        }
    }

    @UiHandler("closeBtn")
    public void closeButtonClick(Button.ClickEvent event) {
        try {
            this.close();
        } catch (Exception e) {
             LOGGER.error(e);
        }
    }

    @UiHandler("resetBtn")
    public void resetButtonClick(Button.ClickEvent event) {
        try {
            notifier.setButtonName("reset");
            notifier.getOkCancelMessage(ARMMessages.getResetConfirmationMessage(), ARMMessages.getResetMessage_exclusion());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @UiHandler("deleteViewBtn")
    public void deleteButtonClick(Button.ClickEvent event) {
        LOGGER.debug("Inside deleteViewBtn :");
        try {
            notifier.setButtonName("delete");
            notifier.getConfirmationMessage(ARMMessages.getResetConfirmationMessage(), ARMMessages.getDeleteMessage_exclusion());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void getFieldValue() {
        try {
            List<ExclusionLookupDTO> fieldValueList = Collections.EMPTY_LIST;
            fieldValueList = arLogic.getFieldListValue(String.valueOf(fieldName.getValue()),
                    ARMConstants.getAccountId().equalsIgnoreCase(String.valueOf(fieldName.getValue())) ? accountId.toString() : ARMConstants.getAccountName().equalsIgnoreCase(String.valueOf(fieldName.getValue())) ? accountName.toString() : accountContractId.toString());
            availableResultsContainer.removeAllItems();
            if (!fieldValueList.isEmpty()) {
                availableResultsContainer.addAll(fieldValueList);
            }
        } catch (Exception e) {
             LOGGER.error(e);
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
                saveViewDTO.setSessionUserID(sessionDTO.getUserId());
                getSelectedIdValues();
            } else {
                saveViewDTO.setFieldName(String.valueOf(fieldName.getValue()));
                saveViewDTO.setViewName(StringUtils.EMPTY);
                saveViewDTO.setViewType(StringUtils.EMPTY);
                saveViewDTO.setUserID(userId.equals(StringUtils.EMPTY) ? 0 : Integer.parseInt(userId.replaceAll("\\D+", StringUtils.EMPTY)));
                saveViewDTO.setViewStatus(false);
                saveViewDTO.setCreatedBy(viewDTO.getCreatedBy());
                saveViewDTO.setCreatedUser(viewDTO.getCreatedUser());
                saveViewDTO.setSessionUserID(sessionDTO.getUserId());
                getSelectedIdValues();

            }
            saveViewDTO.setFieldList(selectedResultsContainer.getItemIds());
            viewPopup = new SaveViewPopup(saveViewDTO);
            getUI().addWindow(viewPopup);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    public void getIntialLoad() {
        publicView.setValue(StringUtils.EMPTY);
        privateView.setValue(StringUtils.EMPTY);
        fieldName.select(ARMConstants.getAccountId());
        selectedResultsContainer.removeAllItems();
        availableResultsContainer.removeAllItems();
        List input = new ArrayList(); // Added for GAL-7235
        input.add(dataSelection.getCompanyMasterSid());
        input.add(dataSelection.getBu_companyMasterSid());
        input.add(dataSelection.getAdjustmentId());
        input.add(dataSelection.getFromPeriod().split("-")[1]);
        List<Object[]> rawList = QueryUtils.getItemData(input, "getViewName", null);
        if(rawList!=null&& !rawList.isEmpty()){
        if("Public".equals(String.valueOf(rawList.get(0)[1]))) {
            publicView.setValue(String.valueOf(rawList.get(0)[0]));
            viewDTO.setViewSid(String.valueOf(rawList.get(0)[NumericConstants.TWO]));
            privateView.setValue(StringUtils.EMPTY);
        } else {
            privateView.setValue(String.valueOf(rawList.get(0)[0]));
            viewDTO.setViewSid(String.valueOf(rawList.get(0)[NumericConstants.TWO]));
            publicView.setValue(StringUtils.EMPTY);
        }
        }
        List<ExclusionLookupDTO> list = arLogic.getListInitialInsertFromARC(input);
        setFieldValuesOnInitialLoad(list);
        arLogic.saveORUpdate_Exclusion_Details_LookUp(dataSelection.getProjectionId(), list, accountId.toString(), accountName.toString(), accountContractId.toString(), sessionDTO); // Ends here for GAL-7235
        selectedResultsContainer.addAll(dataSelection.getProjectionId() != 0 ? arLogic.getIntialLoadValue(dataSelection.getProjectionId()) : dumbyResultsContainer);
    }

    public void getSelectedIdValues() {
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
        accountId = new StringBuilder(StringUtils.EMPTY);
        accountName = new StringBuilder(StringUtils.EMPTY);
        accountContractId = new StringBuilder(StringUtils.EMPTY);
        for (ExclusionLookupDTO dto : selectedResultsContainer.getItemIds()) {
            if (ARMConstants.getAccountId().equalsIgnoreCase(dto.getExcludedField())) {
                accountId.append("'").append(dto.getValues()).append("',");
            } else if (ARMConstants.getAccountName().equalsIgnoreCase(dto.getExcludedField())) {
                accountName.append("'").append(dto.getValues()).append("',");
            } else if (ARMConstants.getContractId().equalsIgnoreCase(dto.getExcludedField())) {
                accountContractId.append("'").append(dto.getValues()).append("',");
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
    
    public void setFieldValuesOnInitialLoad(List<ExclusionLookupDTO> list) {
        accountId = new StringBuilder(StringUtils.EMPTY);
        accountName = new StringBuilder(StringUtils.EMPTY);
        accountContractId = new StringBuilder(StringUtils.EMPTY);
        for (ExclusionLookupDTO dto : list) {
            if (ARMConstants.getAccountId().equalsIgnoreCase(dto.getExcludedField())) {
                accountId.append("'").append(dto.getValues()).append("',");
            } else if (ARMConstants.getAccountName().equalsIgnoreCase(dto.getExcludedField())) {
                accountName.append("'").append(dto.getValues()).append("',");
            } else if (ARMConstants.getContractId().equalsIgnoreCase(dto.getExcludedField())) {
                accountContractId.append("'").append(dto.getValues()).append("',");
            }
        }
        accountId.replace(accountId.length() > 0 ? (accountId.length() - 1) : 0, accountId.length(), "");
        accountName.replace(accountName.length() > 0 ? (accountName.length() - 1) : 0, accountName.length(), "");
        accountContractId.replace(accountContractId.length() > 0 ? (accountContractId.length() - 1) : 0, accountContractId.length(), "");
        check(accountId);
        check(accountName);
        check(accountContractId);
    }
}
