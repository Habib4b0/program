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
    private CustomTextField viewName;
    @UiField("viewOption")
    private OptionGroup viewOption;
    @UiField("cancel")
    private Button cancel;
    @UiField("add")
    private Button addBtn;
    @UiField("update")
    private Button updateBtn;
    private ExclusionLookupDTO saveViewDTO;
    private ExclusionDetailsLogic arLogic = new ExclusionDetailsLogic();
    public static final Logger LOGGER = LoggerFactory.getLogger(SaveViewPopup.class);

    public SaveViewPopup(ExclusionLookupDTO saveViewDTO) {
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        setContent(Clara.create(getClass().getResourceAsStream("/adjustment_rate_config/save-view-popup.xml"), this));
        center();
        this.saveViewDTO = saveViewDTO;
        configureFields();
    }

    public void configureFields() {
        setDraggable(true);
        center();
        setModal(true);
        setResizable(false);
        viewName.setImmediate(true);
        viewOption.addItem(VariableConstants.PUBLIC);
        viewOption.addItem(VariableConstants.PRIVATE);
        viewOption.select(VariableConstants.PUBLIC);
        viewOption.setImmediate(true);
        viewOption.focus();
        if ("publicView".equals(saveViewDTO.getViewType())) {
            viewOption.select(VariableConstants.PUBLIC);
        } else if ("privateView".equals(saveViewDTO.getViewType())) {
            viewOption.select(VariableConstants.PRIVATE);
        }
        if (saveViewDTO.isViewStatus()) {
            addBtn.setEnabled(false);
            updateBtn.setEnabled(true);
            viewName.setValue(saveViewDTO.getViewName());
            viewName.setImmediate(true);
            viewOption.select(saveViewDTO.getViewType());
            viewOption.setEnabled(false);
        } else {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            viewOption.setEnabled(true);
        }
        viewName.addFocusListener(new FocusListener() {
            @Override
            public void focus(FocusEvent event) {
                viewName.addValueChangeListener(viewNameListener);
                viewName.removeFocusListener(this);
            }
        });

    }

    private Property.ValueChangeListener viewNameListener = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            saveViewDTO.setViewStatus(false);
            viewOption.setEnabled(true);
        }
    };

    @UiHandler("add")
    public void addButtonClick(Button.ClickEvent event) {
        try {
            if (StringUtils.isBlank(viewName.getValue()) || "null".equals(String.valueOf(viewName.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else {
                if (isDuplicateView(String.valueOf(viewName.getValue()))) {
                    if (VariableConstants.PRIVATE.equals(String.valueOf(viewOption.getValue()))) {
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
                    saveViewDTO.setViewName(String.valueOf(viewName.getValue()));
                    saveViewDTO.setViewType(String.valueOf(viewOption.getValue()));
                    isSaveView(saveViewDTO);
                    if (viewOption.getValue().equals(ARMUtils.PRIVATE)) {
                        AbstractNotificationUtils.getInfoNotification("View Added Successfully", "You have successfully added private view (" + saveViewDTO.getViewName() + ARMUtils.CLOSE_BRACES);
                    } else {
                        AbstractNotificationUtils.getInfoNotification("View Added Successfully", "You have successfully added public view (" + saveViewDTO.getViewName() + ARMUtils.CLOSE_BRACES);
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
        if ((!saveViewDTO.getViewType().equals(StringUtils.EMPTY) && ("publicView".equals(saveViewDTO.getViewType()))) && (!String.valueOf(saveViewDTO.getSessionUserID()).equals(saveViewDTO.getCreatedUser()))) {
            creatorAlert = 1;

        }
        try {
            if (StringUtils.isBlank(viewName.getValue()) || "null".equals(String.valueOf(viewName.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else if (creatorAlert != 0) {
                AbstractNotificationUtils.getErrorNotification("Cannot update public view", "You cannot update Public View (" + saveViewDTO.getViewName() + ") because it was created by another user.You can choose to save a new profile under a different profile name");

            } else if (!viewName.getValue().equals(saveViewDTO.getViewName())) {
                AbstractNotificationUtils.getErrorNotification("Cannot update view name", "View  name can't be Changed");
            } else {
                saveViewDTO.setViewStatus(true);
                close();
                isSaveView(saveViewDTO);
                if (viewOption.getValue().equals(ARMUtils.PRIVATE)) {
                    AbstractNotificationUtils.getInfoNotification("View UPDATED Successfully", "You have successfully updated private view (" + saveViewDTO.getViewName() + ARMUtils.CLOSE_BRACES);
                } else {
                    AbstractNotificationUtils.getInfoNotification("View UPDATED Successfully", "You have successfully updated public view (" + saveViewDTO.getViewName() + ARMUtils.CLOSE_BRACES);
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
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
