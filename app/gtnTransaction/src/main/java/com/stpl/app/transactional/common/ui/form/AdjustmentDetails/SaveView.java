/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.ui.form.AdjustmentDetails;

import com.stpl.app.transactional.common.dto.AdjustmentDetailsDTO;
import com.stpl.app.transactional.common.logic.SearchLogic;
import com.stpl.app.util.ConstantUtil;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Rohit.Vignesh
 */
public class SaveView extends Window {

    private static final Logger LOGGER = Logger.getLogger(SaveView.class);
    AdjustmentDetailsDTO adjustmentDetailsDTO = new AdjustmentDetailsDTO();
    SearchLogic logic = new SearchLogic();

    @UiField("viewName")
    public TextField viewName;
    @UiField("viewType")
    public OptionGroup viewType;
    @UiField("cancelBtn")
    public Button cancelBtn;
    @UiField("addBtn")
    public Button addBtn;
    @UiField("updateBtn")
    public Button updateBtn;

    String viewTypeForSave = ConstantUtil.PRIVATE;
    String viewNameForSave;
    Boolean isAddUpdateFlag = false;
    SearchLogic searchlogic;
    String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));

    public SaveView(AdjustmentDetailsDTO adjustmentDetailsDTO) {
        this.adjustmentDetailsDTO = adjustmentDetailsDTO;
        adjustmentDetailsDTO.setUserId(userId);
        init();
    }

    private void init() {
        addToContent();
        configureFields();
    }

    private void addToContent() {
        setContent(Clara.create(getClass().getResourceAsStream("/ui/adjustment_details/SaveLookup.xml"), this));
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        center();
        setClosable(true);
        setModal(true);
        setWidth("480px");
        setHeight("215px");
        setCaption("Save View Popup");
    }

    private void configureFields() {
        viewType.addItem(ConstantUtil.PRIVATE);
        viewType.addItem(ConstantUtil.PUBLIC);
        viewType.select(ConstantUtil.PRIVATE);
        if (!adjustmentDetailsDTO.isMode()) {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            viewType.setEnabled(true);
        } else {
            addBtn.setEnabled(false);
            updateBtn.setEnabled(true);
            viewType.select(adjustmentDetailsDTO.getViewType());
            viewType.setEnabled(false);
            viewName.setValue(adjustmentDetailsDTO.getViewName());
        }
        addBtn.setImmediate(true);
        updateBtn.setImmediate(true);
        
        cancelBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().setData(null);
                close();
            }
        });
    }

    @UiHandler("addBtn")
    public void addButtonLogic(Button.ClickEvent event) {
        try {
            if (StringUtils.isBlank(viewName.getValue()) || ConstantUtil.NULL.equals(String.valueOf(viewName.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else if (isDuplicateView(viewName.getValue())) {
                if (ConstantUtil.PRIVATE.equals(viewType.getValue())) {
                    AbstractNotificationUtils.getErrorNotification("Duplicate View Name",
                            "The Private View name you have attempted to save is a duplicate of an existing view name. "
                            + "\nPlease enter a different view name");
                } else {
                    AbstractNotificationUtils.getErrorNotification("Duplicate View Name",
                            "The Public View name you have attempted to save is a duplicate of an existing view name. "
                            + "\nPlease enter a different view name");
                }
            } else {
                adjustmentDetailsDTO.setViewType(viewType.getValue().toString());
                adjustmentDetailsDTO.setViewName(viewName.getValue());
                logic.addOrUpdate(adjustmentDetailsDTO);
                if (viewType.getValue().equals(ConstantUtil.PRIVATE)) {
                    AbstractNotificationUtils.getInfoNotification("View Added Successfully", "You have successfully added private view (" + adjustmentDetailsDTO.getViewName() + ")");
                } else {
                    AbstractNotificationUtils.getInfoNotification("View Added Successfully", "You have successfully added public view (" + adjustmentDetailsDTO.getViewName() + ")");
                }
                this.close();
            }
        } catch (Exception exception) {
            LOGGER.error(exception);
        }
    }


    @UiHandler("updateBtn")
    public void updateButtonLogic(Button.ClickEvent event) {

        int non_creatoralert = 0;
        if (!adjustmentDetailsDTO.getViewType().equals(StringUtils.EMPTY) && adjustmentDetailsDTO.getViewCreatedBy().equals(adjustmentDetailsDTO.getCreatedBy())) {
                non_creatoralert++;
        }
        try {
            if (StringUtils.isBlank(viewName.getValue()) || ConstantUtil.NULL.equals(String.valueOf(viewName.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
            } else if (non_creatoralert != 0) {
                AbstractNotificationUtils.getErrorNotification("Cannot update public view", "You cannot update Public View (" + viewName.getValue() + ") because it was created by another user.You can choose to save a new profile under a different profile name");
            } else if (!String.valueOf(viewName.getValue()).equals(adjustmentDetailsDTO.getViewName())) {
                AbstractNotificationUtils.getErrorNotification("Cannot update view name", "View  name can't be Changed");
            } else if (isDuplicateView(viewName.getValue())) {
                logic.addOrUpdate(adjustmentDetailsDTO);
                close();
                if (viewType.getValue().equals(ConstantUtil.PRIVATE)) {
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
        return logic.isDuplicateView(viewName);
    }
    
    
    
            }
