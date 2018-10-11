/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.lookups;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.ExclusionLookupDTO;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.ExclusionDetailsLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.v7.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.ui.Window;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Gopinath.Mathiyalaga
 */
/**
 * The Class SaveViewPopup.
 *
 *
 */
public class SaveViewPopup extends Window {

    @UiField("viewName")
    private CustomTextField viewNameSavePopup;
    @UiField("viewOption")
    private OptionGroup viewOptionSavePopup;
    @UiField("cancel")
    private Button cancelSavePopup;
    @UiField("add")
    private Button addBtnSavePopup;
    @UiField("update")
    private Button updateBtnSavePopup;
    private ExclusionLookupDTO saveViewDTOSavePopup;
    private ExclusionDetailsLogic arLogic = new ExclusionDetailsLogic();
    public static final Logger LOGGER = LoggerFactory.getLogger(SaveViewPopup.class);

    public SaveViewPopup(ExclusionLookupDTO saveViewDTO) {
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        setContent(Clara.create(getClass().getResourceAsStream("/adjustment_rate_config/save-view-popup.xml"), this));
        center();
        this.saveViewDTOSavePopup = saveViewDTO;
        configureFields();
    }

    private void configureFields() {
        setDraggable(true);
        center();
        setModal(true);
        setResizable(false);
        setCaption("Save View");
        viewNameSavePopup.setImmediate(true);
        viewOptionSavePopup.addItem(VariableConstants.PUBLIC);
        viewOptionSavePopup.addItem(VariableConstants.PRIVATE);
        viewOptionSavePopup.select(VariableConstants.PUBLIC);
        viewOptionSavePopup.setImmediate(true);
        viewOptionSavePopup.focus();
        if ("publicView".equals(saveViewDTOSavePopup.getViewType())) {
            viewOptionSavePopup.select(VariableConstants.PUBLIC);
        } else if ("privateView".equals(saveViewDTOSavePopup.getViewType())) {
            viewOptionSavePopup.select(VariableConstants.PRIVATE);
        }
        if (saveViewDTOSavePopup.isViewStatus()) {
            addBtnSavePopup.setEnabled(false);
            updateBtnSavePopup.setEnabled(true);
            viewNameSavePopup.setValue(saveViewDTOSavePopup.getViewName());
            viewNameSavePopup.setImmediate(true);
            viewOptionSavePopup.select(saveViewDTOSavePopup.getViewType());
            viewOptionSavePopup.setEnabled(false);
        } else {
            addBtnSavePopup.setEnabled(true);
            updateBtnSavePopup.setEnabled(false);
            viewOptionSavePopup.setEnabled(true);
        }
        viewNameSavePopup.addFocusListener(new FocusListener() {
            @Override
            public void focus(FocusEvent event) {
                viewNameSavePopup.addValueChangeListener(viewNameListener);
                viewNameSavePopup.removeFocusListener(this);
            }
        });

    }

    private Property.ValueChangeListener viewNameListener = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            addBtnSavePopup.setEnabled(true);
            updateBtnSavePopup.setEnabled(false);
            saveViewDTOSavePopup.setViewStatus(false);
            viewOptionSavePopup.setEnabled(true);
        }
    };

    @UiHandler("add")
    public void addButtonClick(Button.ClickEvent event) {
        try {
            if (StringUtils.isBlank(viewNameSavePopup.getValue()) || "null".equals(String.valueOf(viewNameSavePopup.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else {
                if (isDuplicateView(String.valueOf(viewNameSavePopup.getValue()))) {
                    if (VariableConstants.PRIVATE.equals(String.valueOf(viewOptionSavePopup.getValue()))) {
                        AbstractNotificationUtils.getErrorNotification("Duplicate View Name",
                                "The Private View name you have attempted to save is a duplicate of an existing view name. "
                                + "\nPlease enter a different view name");
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Duplicate View Name",
                                "The Public View name you have attempted to save is a duplicate of an existing view name. "
                                + "\nPlease enter a different view name");
                    }
                } else {
                    close();
                    saveViewDTOSavePopup.setViewName(String.valueOf(viewNameSavePopup.getValue()));
                    saveViewDTOSavePopup.setViewType(String.valueOf(viewOptionSavePopup.getValue()));
                    isSaveView(saveViewDTOSavePopup);
                    if (viewOptionSavePopup.getValue().equals(ARMUtils.PRIVATE)) {
                        AbstractNotificationUtils.getInfoNotification("View Added Successfully", "You have successfully added private view (" + saveViewDTOSavePopup.getViewName() + ARMUtils.CLOSE_PARANTHESIS);
                    } else {
                        AbstractNotificationUtils.getInfoNotification("View Added Successfully", "You have successfully added public view (" + saveViewDTOSavePopup.getViewName() + ARMUtils.CLOSE_PARANTHESIS);
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error("Error in addButtonClick :", e);
        }
    }

    @UiHandler("cancel")
    public void cancelButtonClick(Button.ClickEvent event) {
        try {
            close();
        } catch (Exception e) {
            LOGGER.error("Error in cancelButtonClick :", e);
        }
    }

    @UiHandler("update")
    public void updateButtonClick(Button.ClickEvent event) {
        int creatorAlert = 0;
        if ((!saveViewDTOSavePopup.getViewType().equals(StringUtils.EMPTY) && ("publicView".equals(saveViewDTOSavePopup.getViewType()))) && (!String.valueOf(saveViewDTOSavePopup.getSessionUserID()).equals(saveViewDTOSavePopup.getCreatedUser()))) {
            creatorAlert = 1;

        }
        try {
            if (StringUtils.isBlank(viewNameSavePopup.getValue()) || "null".equals(String.valueOf(viewNameSavePopup.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else if (creatorAlert != 0) {
                AbstractNotificationUtils.getErrorNotification("Cannot update public view", "You cannot update Public View (" + saveViewDTOSavePopup.getViewName() + ") because it was created by another user.You can choose to save a new profile under a different profile name");

            } else if (!viewNameSavePopup.getValue().equals(saveViewDTOSavePopup.getViewName())) {
                AbstractNotificationUtils.getErrorNotification("Cannot update view name", "View  name can't be Changed");
            } else {
                saveViewDTOSavePopup.setViewStatus(true);
                close();
                isSaveView(saveViewDTOSavePopup);
                if (viewOptionSavePopup.getValue().equals(ARMUtils.PRIVATE)) {
                    AbstractNotificationUtils.getInfoNotification("View UPDATED Successfully", "You have successfully updated private view (" + saveViewDTOSavePopup.getViewName() + ARMUtils.CLOSE_PARANTHESIS);
                } else {
                    AbstractNotificationUtils.getInfoNotification("View UPDATED Successfully", "You have successfully updated public view (" + saveViewDTOSavePopup.getViewName() + ARMUtils.CLOSE_PARANTHESIS);
                }

            }
        } catch (Exception e) {
            LOGGER.error("Error in updateButtonClick :", e);
        }
    }

    public boolean isDuplicateView(String viewName) {
        return arLogic.isDuplicateName(viewName);
    }

    public void isSaveView(ExclusionLookupDTO saveViewDTO) {
        arLogic.isAddORUpdateView(saveViewDTO);
    }

    @Override
    public boolean equals(Object saveViewobj) {
        return super.equals(saveViewobj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream saveViewobj) throws IOException {
        saveViewobj.defaultWriteObject();
    }

    private void readObject(ObjectInputStream saveViewobj) throws IOException, ClassNotFoundException {
        saveViewobj.defaultReadObject();
    }
}
