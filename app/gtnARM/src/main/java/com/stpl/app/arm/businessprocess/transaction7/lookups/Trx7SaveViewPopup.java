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
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Window;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.jboss.logging.Logger;
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
    CustomTextField viewName;
    @UiField("viewOption")
    OptionGroup viewOption;
    @UiField("cancel")
    Button cancel;
    @UiField("add")
    Button addBtn;
    @UiField("update")
    Button updateBtn;
    Integer userID = 0;
    ExclusionLookupDTO saveViewDTO;
    Trx7ExclusionDetailsLogic arLogic = new Trx7ExclusionDetailsLogic();
    public static final Logger LOGGER = Logger.getLogger(Trx7SaveViewPopup.class);

    public Trx7SaveViewPopup(ExclusionLookupDTO saveViewDTO) {
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
            LOGGER.error("Error in addbuttonClick :"+e);
        }
    }

    @UiHandler("cancel")
    public void cancelButtonClick(Button.ClickEvent event) {
        try {
            close();
        } catch (Exception e) {
            LOGGER.error("Error in cancelButtonClick :"+e);
        }
    }

    @UiHandler("update")
    public void updateButtonClick(Button.ClickEvent event) {
        LOGGER.debug("Inside updateButtonClick Btn");
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
            LOGGER.error("Error in updateButtonClick :"+e);
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
}
