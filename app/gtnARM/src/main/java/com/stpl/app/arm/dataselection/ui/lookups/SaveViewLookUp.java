/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.ui.lookups;

import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.dto.SaveViewDTO;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.Window;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author
 */
public class SaveViewLookUp extends Window {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveViewLookUp.class);
    @UiField("viewName")
    private TextField viewName;
    @UiField("viewType")
    private OptionGroup viewType;
    @UiField("addBtn")
    private Button addBtn;
    @UiField("updateBtn")
    private Button updateBtn;
    private DataSelectionLogic logic = new DataSelectionLogic();
    private DataSelectionDTO dataSelectionDTO;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public SaveViewLookUp(DataSelectionDTO dataSelectionDTO) {
        super("Save View");
        LOGGER.debug("Entering saveViewPopup");
        this.dataSelectionDTO = dataSelectionDTO;
        init();
        LOGGER.debug("End of SaveViewPopup");
    }

    private void init() {
        setContent(Clara.create(getClass().getResourceAsStream("/data_selection/save_view.xml"), this));
        initializeComponents();
        configureFields();
    }

    public void initializeComponents() {
        setClosable(true);
        setModal(true);
        center();
        addStyleName(ARMUtils.BOOTSTRAP_UI);
        addStyleName(ARMUtils.BOOTSTRAP);
        addStyleName(ARMUtils.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
    }

    private void configureFields() {
        viewType.addItem(ARMUtils.PRIVATE);
        viewType.addItem(ARMUtils.PUBLIC);
        viewType.focus();
        viewType.select(ARMUtils.PRIVATE);
        viewType.setStyleName(ARMUtils.HORIZONTAL);
        viewName.setImmediate(true);
        viewName.setValidationVisible(true);
        viewName.addValidator(new StringLengthValidator("View Name Should be less than 200 characters", 0, NumericConstants.TWO_HUNDRED, true));
        if (dataSelectionDTO.isAddUpdateFlag()) {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            viewType.setEnabled(true);
        } else {
            updateBtn.setEnabled(true);
            viewType.select(dataSelectionDTO.getViewType());
            viewName.setValue(dataSelectionDTO.getViewName());
        }
        viewName.addValueChangeListener(valueChange);
        viewType.addValueChangeListener(valueChange);
    }

    @UiHandler("addBtn")
    public void addButtonLogic(Button.ClickEvent event) {
        try {
            if (StringUtils.isBlank(viewName.getValue()) || ARMUtils.NULL.equals(String.valueOf(viewName.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else if (isDuplicateView(viewName.getValue(), viewType.getValue())) {
                if (ARMUtils.PRIVATE.equals(viewType.getValue())) {
                    AbstractNotificationUtils.getErrorNotification("Duplicate View Name",
                            "The Private View name you have attempted to save is a duplicate of an existing view name. "
                            + "\nPlease enter a different view name");
                } else {
                    AbstractNotificationUtils.getErrorNotification("Duplicate View Name",
                            "The Public View name you have attempted to save is a duplicate of an existing view name. "
                            + "\nPlease enter a different view name");
                }
            } else {
                int projectionIdValue = logic.saveProjectionForView(dataSelectionDTO);
                logic.saveAdjustmentMaster(projectionIdValue, dataSelectionDTO);
                saveSelectionValue(dataSelectionDTO, projectionIdValue);
                SaveViewDTO viewDTO = new SaveViewDTO();
                viewDTO.setProjectionSid(projectionIdValue);
                viewDTO.setViewName((String) viewName.getValue());
                viewDTO.setViewType((String) viewType.getValue());
                viewDTO.setCreatedBy(dataSelectionDTO.getCreatedBy());
                viewDTO.setCreatedDate(dateFormat.format(dataSelectionDTO.getCreatedDate()));
                logic.saveViewLogic(viewDTO);
                if (viewType.getValue().equals(ARMUtils.PRIVATE)) {
                    AbstractNotificationUtils.getInfoNotification("View Added Successfully", "You have successfully added private view (" + viewDTO.getViewName() + ARMUtils.CLOSE_BRACES);
                } else {
                    AbstractNotificationUtils.getInfoNotification("View Added Successfully", "You have successfully added public view (" + viewDTO.getViewName() + ARMUtils.CLOSE_BRACES);
                }
                this.close();
            }
        } catch (Exception exception) {
            LOGGER.error("Error in addButtonLogic :", exception);
        }
    }

    @UiHandler("cancelBtn")
    public void cancelButtonLogic(Button.ClickEvent event) {
        close();
    }

    @UiHandler("updateBtn")
    public void updateButtonLogic(Button.ClickEvent event) {

        try {
            if (StringUtils.isBlank(viewName.getValue()) || ARMUtils.NULL.equals(String.valueOf(viewName.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else if (dataSelectionDTO.getViewCreatedBy() != dataSelectionDTO.getCreatedBy()) {
                AbstractNotificationUtils.getErrorNotification("Cannot update public view", "You cannot update Public View (" + viewName.getValue() + ") because it was created by another user.You can choose to save a new profile under a different profile name");
            } else if (!String.valueOf(viewName.getValue()).equals(dataSelectionDTO.getViewName())) {
                AbstractNotificationUtils.getErrorNotification("Cannot update view name", "View  name can't be Changed");
            } else if (isDuplicateView(viewName.getValue(), viewType.getValue())) {
                close();
                logic.updateSavedViewProjection(dataSelectionDTO);
                logic.updateSaveViewLogic(dataSelectionDTO);
                saveSelectionValue(dataSelectionDTO, dataSelectionDTO.getProjectionId());
                logic.updateModifiedDateLogic(dataSelectionDTO.getProjectionId());
                if (viewType.getValue().equals(ARMUtils.PRIVATE)) {
                    AbstractNotificationUtils.getInfoNotification("View updated Successfully", "You have successfully updated private view (" + viewName.getValue() + ARMUtils.CLOSE_BRACES);
                } else {
                    AbstractNotificationUtils.getInfoNotification("View Updated Successfully", "You have successfully updated public view (" + viewName.getValue() + ARMUtils.CLOSE_BRACES);
                }
            }
        } catch (Exception e) {
            LOGGER.error(" in save view -  btnUpdateLogic ", e);
        }
    }

    private boolean isDuplicateView(final String viewName, Object viewType) {
        LOGGER.debug("Entering isDuplicateView method with viewName {}", viewName);
        return logic.isDuplicateView(viewName, String.valueOf(viewType), dataSelectionDTO);
    }

    public void saveSelectionValue(DataSelectionDTO dataSelectionDTO, int projectionIdValue) {
        try {
            logic.saveCustomerHierarchyLogic(dataSelectionDTO.getCustomerList(), dataSelectionDTO.getCustomerEndLevelList(), projectionIdValue, null, ARMUtils.SAVE);
            logic.saveProductHierarchyLogic(dataSelectionDTO.getProductList(), dataSelectionDTO.getProductEndLevelList(), projectionIdValue, null, ARMUtils.SAVE);
            logic.saveDeductionLogic(new HashSet(dataSelectionDTO.getRsContractSidList()), projectionIdValue);
        } catch (Exception ex) {
            LOGGER.error("Error in Save Selection View", ex);
        }
    }
    private Property.ValueChangeListener valueChange = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (dataSelectionDTO.getViewName().equals(String.valueOf(viewName.getValue()))
                    && dataSelectionDTO.getViewType().equals(String.valueOf(viewType.getValue()))) {
                updateBtn.setEnabled(true);
            } else {
                updateBtn.setEnabled(false);
            }
        }
    };

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
