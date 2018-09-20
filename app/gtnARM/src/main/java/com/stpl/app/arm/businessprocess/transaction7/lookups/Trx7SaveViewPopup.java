/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.lookups;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.ExclusionLookupDTO;
import com.stpl.app.arm.businessprocess.transaction7.logic.Trx7ExclusionDetailsLogic;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
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
public class Trx7SaveViewPopup extends Window {

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
    private final ExclusionLookupDTO saveViewDTO;
    private final Trx7ExclusionDetailsLogic arLogic = new Trx7ExclusionDetailsLogic();
    public static final Logger TR7_SAVE_VIEW_LOGGER = LoggerFactory.getLogger(Trx7SaveViewPopup.class);

    public Trx7SaveViewPopup(ExclusionLookupDTO saveViewDTO) {
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        setContent(Clara.create(getClass().getResourceAsStream("/adjustment_rate_config/save-view-popup.xml"), this));
        center();
        this.saveViewDTO = saveViewDTO;
        configureFields();
    }

    private void configureFields() {
        setDraggable(true);
        center();
        setModal(true);
        setResizable(false);
        viewName.setImmediate(true);
        viewOption.addItem("Public");
        viewOption.addItem("Private");
        viewOption.select("Public");
        viewOption.setImmediate(true);
        viewOption.focus();
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
    }

    @UiHandler("add")
    public void addButtonClick(Button.ClickEvent event) {
        try {
            if (StringUtils.isBlank(viewName.getValue()) || "null".equals(String.valueOf(viewName.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else {
                if (isDuplicateView(String.valueOf(viewName.getValue()))) {
                    if ("Private".equals(String.valueOf(viewOption.getValue()))) {
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
                }
            }

        } catch (Exception e) {
            TR7_SAVE_VIEW_LOGGER.error("Error in addbuttonClick :", e);
        }
    }

    @UiHandler("cancel")
    public void cancelBtnClick(Button.ClickEvent event) {
        try {
            close();
        } catch (Exception e) {
            TR7_SAVE_VIEW_LOGGER.error("Error in cancelButtonClick :", e);
        }
    }

    @UiHandler("update")
    public void updateButtonClick(Button.ClickEvent event) {
        TR7_SAVE_VIEW_LOGGER.debug("Inside updateButtonClick Btn");
        int creatorAlert = 0;
        try {
            if ((!saveViewDTO.getViewType().equals(StringUtils.EMPTY) && ("publicView".equals(saveViewDTO.getViewType()))) && (!String.valueOf(saveViewDTO.getSessionUserID()).equals(saveViewDTO.getCreatedUser()))) {
                creatorAlert = 1;
            }
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

            }
        } catch (Exception e) {
            TR7_SAVE_VIEW_LOGGER.error("Error in updateButtonClick :", e);
        }
    }

    public boolean isDuplicateView(String viewName) {
        return arLogic.isDuplicateName(viewName);
    }

    public void isSaveView(ExclusionLookupDTO saveViewDTO) {
        arLogic.isAddORUpdateView(saveViewDTO);
    }

    @Override
    public boolean equals(Object tr7SaveViewObj) {
        return super.equals(tr7SaveViewObj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream tr7SaveViewObj) throws IOException {
        tr7SaveViewObj.defaultWriteObject();
    }

    private void readObject(ObjectInputStream tr7SaveViewObj) throws IOException, ClassNotFoundException {
        tr7SaveViewObj.defaultReadObject();
    }
}
