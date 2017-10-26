/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.ui.lookups;

import com.stpl.app.arm.dataselection.dto.CalculationProfileDTO;
import com.stpl.app.arm.dataselection.dto.ViewDTO;
import com.stpl.app.arm.dataselection.logic.CalculationProfileLogic;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import java.text.SimpleDateFormat;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author
 */
public class CalculationProfileSaveViewLookUp extends Window {

    private static final Logger LOGGER = Logger.getLogger(CalculationProfileSaveViewLookUp.class);
    @UiField("viewName")
    private TextField viewName;
    @UiField("viewType")
    private OptionGroup viewType;
    @UiField("addBtn")
    private Button addBtn;
    @UiField("updateBtn")
    private Button updateBtn;
    DataSelectionLogic logic = new DataSelectionLogic();
    ViewDTO viewDataDTO;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    CalculationProfileDTO calculationProfileDTO;
    CalculationProfileLogic calculationProfileLogic = new CalculationProfileLogic();

    public CalculationProfileSaveViewLookUp(ViewDTO dataSelectionDTO, CalculationProfileDTO calculationProfileDTO) {
        super("Save View");
        LOGGER.debug("Entering saveViewPopup");
        this.viewDataDTO = dataSelectionDTO;
        this.calculationProfileDTO = calculationProfileDTO;
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
        viewName.addValidator(new StringLengthValidator("View Name Should be less than 200 characters", 0, 200, true));
        if (calculationProfileDTO.isAddUpdateFlag()) {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            viewType.setEnabled(true);
        } else {
            addBtn.setEnabled(false);
            updateBtn.setEnabled(true);
            viewType.select(viewDataDTO.getViewType().trim());
            viewType.setValue(viewDataDTO.getViewType().trim());
            viewType.setEnabled(false);
            viewName.setValue(viewDataDTO.getViewName());
        }
        addBtn.setImmediate(true);
        updateBtn.setImmediate(true);
    }

    @UiHandler("addBtn")
    public void addButtonLogic(Button.ClickEvent event) {
        try {
            if (StringUtils.isBlank(viewName.getValue()) || ARMUtils.NULL.equals(String.valueOf(viewName.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else if (isDuplicateView(viewName.getValue())) {
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

                calculationProfileDTO.setViewName((String) viewName.getValue());
                calculationProfileDTO.setViewType((String) viewType.getValue());

                calculationProfileLogic.insert(calculationProfileLogic.getViewQuery(calculationProfileDTO.getQuery(), calculationProfileDTO));

                if (viewType.getValue().equals(ARMUtils.PRIVATE)) {
                    AbstractNotificationUtils.getInfoNotification("View Added Successfully", "You have successfully added private view (" + calculationProfileDTO.getViewName() + ")");
                } else {
                    AbstractNotificationUtils.getInfoNotification("View Added Successfully", "You have successfully added public view (" + calculationProfileDTO.getViewName() + ")");
                }
                this.close();
            }
        } catch (Exception exception) {
            LOGGER.error(exception);
        }
    }

    @UiHandler("cancelBtn")
    public void cancelButtonLogic(Button.ClickEvent event) {
        close();
    }

    @UiHandler("updateBtn")
    public void updateButtonLogic(Button.ClickEvent event) {
        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            if (StringUtils.isBlank(viewName.getValue()) || ARMUtils.NULL.equals(String.valueOf(viewName.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else if (viewDataDTO.getViewCreatedBy() != Integer.parseInt(userId)) {
                AbstractNotificationUtils.getErrorNotification("Cannot update public view", "You cannot update Public View (" + viewName.getValue() + ") because it was created by another user.You can choose to save a new profile under a different profile name");
            } else if (!String.valueOf(viewName.getValue()).equals(viewDataDTO.getViewName())) {
                AbstractNotificationUtils.getErrorNotification("Cannot update view name", "View  name can't be Changed");
            } else if (isDuplicateView(viewName.getValue())) {
                close();

                calculationProfileDTO.setCalculationProfileId(viewDataDTO.getCalculationProfileMasterSid());
                calculationProfileLogic.insert(calculationProfileLogic.getViewUpdateQuery(calculationProfileDTO.getUpdateViewQuery(), calculationProfileDTO));

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

    private boolean isDuplicateView(final String viewName) throws SystemException {
        LOGGER.debug("Entering isDuplicateView method with viewName " + viewName);
        return calculationProfileLogic.isDuplicateView(viewName);
    }

}
