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
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author
 */
public class SaveViewLookUp extends Window {

    private static final Logger LOGGER = Logger.getLogger(SaveViewLookUp.class);
    @UiField("viewName")
    private TextField viewName;
    @UiField("viewType")
    private OptionGroup viewType;
    @UiField("addBtn")
    private Button addBtn;
    @UiField("updateBtn")
    private Button updateBtn;
    DataSelectionLogic logic = new DataSelectionLogic();
    DataSelectionDTO dataSelectionDTO;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    String tempViewName = StringUtils.EMPTY;
    String tempViewType = StringUtils.EMPTY;

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
            tempViewName = dataSelectionDTO.getViewName();
            tempViewType = dataSelectionDTO.getViewType();
        }
        addBtn.setImmediate(true);
        updateBtn.setImmediate(true);
        viewName.addValueChangeListener(valueChange);
        viewType.addValueChangeListener(valueChange);
    }

    @UiHandler("addBtn")
    public void addButtonLogic(Button.ClickEvent event) {
        try {
            if (StringUtils.isBlank(viewName.getValue()) || ARMUtils.NULL.equals(String.valueOf(viewName.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else if (isDuplicateView(viewName.getValue(),viewType.getValue())) {
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
                    AbstractNotificationUtils.getInfoNotification("View Added Successfully", "You have successfully added private view (" + viewDTO.getViewName() + ")");
                } else {
                    AbstractNotificationUtils.getInfoNotification("View Added Successfully", "You have successfully added public view (" + viewDTO.getViewName() + ")");
                }
                this.close();
            }
        } catch (SystemException sysException) {
            LOGGER.error("Error in addButtonLogic :" + sysException);
        } catch (Exception exception) {
            LOGGER.error("Error in addButtonLogic :" + exception);
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
            } else if (isDuplicateView(viewName.getValue(),viewType.getValue())) {
                close();
                logic.updateSavedViewProjection(dataSelectionDTO);
                logic.updateSaveViewLogic(dataSelectionDTO);
                saveSelectionValue(dataSelectionDTO, dataSelectionDTO.getProjectionId());
                if (viewType.getValue().equals(ARMUtils.PRIVATE)) {
                    AbstractNotificationUtils.getInfoNotification("View updated Successfully", "You have successfully updated private view (" + viewName.getValue() + ")");
                } else {
                    AbstractNotificationUtils.getInfoNotification("View Updated Successfully", "You have successfully updated public view (" + viewName.getValue() + ")");
                }
            }
        } catch (Exception e) {
            LOGGER.error(e + " in save view -  btnUpdateLogic ");
        }
    }

    private boolean isDuplicateView(final String viewName,Object viewType) throws SystemException {
        LOGGER.debug("Entering isDuplicateView method with viewName " + viewName);
        return logic.isDuplicateView(viewName, String.valueOf(viewType),dataSelectionDTO);
    }

    public void saveSelectionValue(DataSelectionDTO dataSelectionDTO, int projectionIdValue) {
        try {
            logic.saveCustomerHierarchyLogic(dataSelectionDTO.getCustomerList(), dataSelectionDTO.getCustomerEndLevelList(), projectionIdValue, null, ARMUtils.SAVE);
            logic.saveProductHierarchyLogic(dataSelectionDTO.getProductList(), dataSelectionDTO.getProductEndLevelList(), projectionIdValue, null, ARMUtils.SAVE);
            logic.saveDeductionLogic(new HashSet(dataSelectionDTO.getRsContractSidList()), projectionIdValue);
        } catch (Exception ex) {
            LOGGER.error("Error in Save Selection View " + projectionIdValue + ex);
        }
    }
    Property.ValueChangeListener valueChange = new Property.ValueChangeListener() {
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
}
