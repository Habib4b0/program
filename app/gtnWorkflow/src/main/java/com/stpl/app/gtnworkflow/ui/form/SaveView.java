/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.ui.form;

import com.stpl.app.gtnworkflow.logic.WorkflowLogic;
import com.stpl.app.gtnworkflow.dto.InboxDashboardDTO;
import com.stpl.app.gtnworkflow.util.CommonUtils;
import com.stpl.app.gtnworkflow.util.ConstantUtils;
import com.stpl.app.gtnworkflow.util.NotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.jboss.logging.Logger;

/**
 *
 * @author rohitvignesh.s
 */
public class SaveView extends Window {

    WorkflowLogic logic = new WorkflowLogic();

    InboxDashboardDTO inboxDashboardDTO = new InboxDashboardDTO();

    private static final Logger LOGGER = Logger.getLogger(SaveView.class);

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
    String viewTypeForSave = CommonUtils.PRIVATE;
    String viewNameForSave;
    Boolean isFirst = false;
    String businessProcess;

    public SaveView(InboxDashboardDTO inboxDashboardDTO, Boolean isFirst, String businessProcess) {
        this.inboxDashboardDTO = inboxDashboardDTO;
        this.isFirst = isFirst;
        this.businessProcess = businessProcess;
        init();
    }

    private void init() {
        addToContent();
        configureFields();
    }

    private void addToContent() {
        setContent(Clara.create(getClass().getResourceAsStream("/clara/SaveViewLookup.xml"), this));
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

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    private void configureFields() {
        LOGGER.debug("Entering SaveView configureFields");
        viewName.focus();
        viewName.setValidationVisible(true);
        viewName.addValidator(new StringLengthValidator("View Name should be less than 200 characters", 0, NumericConstants.TWO_HUNDRED, false));
        viewName.setValue(inboxDashboardDTO.getViewName());        
        viewType.addItem(CommonUtils.PRIVATE);
        viewType.addItem(CommonUtils.PUBLIC);
        viewType.select(CommonUtils.PRIVATE);

        viewType.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1172963944103056571L;

            @Override
            public void valueChange(final Property.ValueChangeEvent event) {
                viewNameForSave = viewName.getValue();
                if (viewType.getValue() == CommonUtils.PRIVATE) {
                    viewTypeForSave = CommonUtils.PRIVATE;
                } else {
                    viewTypeForSave = CommonUtils.PUBLIC;

                }
            }
        });
        if ((StringUtils.isBlank(inboxDashboardDTO.getViewName())) || ("null".equals(inboxDashboardDTO.getViewName()))) {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
        } else if (inboxDashboardDTO.isViewFlag()) {
            addBtn.setEnabled(false);
            updateBtn.setEnabled(true);
            viewName.setValue(inboxDashboardDTO.getViewName());
        } else {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            viewName.setValue(StringUtils.EMPTY);
        }
        
        cancelBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().setData(null);
                close();
            }
        });

        addBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    if (StringUtils.isBlank(viewName.getValue()) || "null".equals(String.valueOf(viewName.getValue()))) {
                        NotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
                    } else if (isDuplicateView(viewName.getValue())) {
                        if (CommonUtils.PRIVATE.equals(viewType.getValue())) {
                            NotificationUtils.getErrorNotification("Duplicate View Name",
                                    "The Private View name you have attempted to save is a duplicate of an existing view name. "
                                    + "\nPlease enter a different view name");
                        }
                    } else {
                        inboxDashboardDTO.setViewName(viewName.getValue());
                        inboxDashboardDTO.setViewType(String.valueOf(viewType.getValue()));
                        logic.viewInsertOrUpdate(Boolean.TRUE, inboxDashboardDTO, businessProcess);
                        if (CommonUtils.PRIVATE.equals(viewType.getValue())) {
                            NotificationUtils.getAlertNotification("View Added Successfully",
                                    "You have successfully added private view " + viewName.getValue());
                        } else {
                            NotificationUtils.getAlertNotification("View Added Successfully",
                                    "You have successfully added public view " + viewName.getValue());
                        }
                        close();
                    }
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(SaveView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        updateBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                final String userId = (String) VaadinSession.getCurrent().getAttribute(ConstantUtils.USER_ID);
                final String useridint =CommonUtils.getUserInfo(String.valueOf(userId));
                boolean non_creatoralert = true;
                if ((inboxDashboardDTO.getCreatedBy().equals(useridint)) && CommonUtils.PUBLIC.equals(viewType.getValue())) {
                    non_creatoralert = true;
                } else {
                    non_creatoralert = false;
                }
                if (!non_creatoralert && CommonUtils.PUBLIC.equals(viewType.getValue())) {

                    NotificationUtils.getErrorNotification("Cannot update public view", "You cannot update Public View (" + viewName.getValue() + ") because it was created by another user.You can choose to save a new profile under a different profile name");
                } else {
                    inboxDashboardDTO.setViewType(String.valueOf(viewType.getValue()));
                    inboxDashboardDTO.setViewName(viewName.getValue());
                    inboxDashboardDTO.setModifiedBy(userId);
                    logic.viewInsertOrUpdate(Boolean.FALSE, inboxDashboardDTO, businessProcess);
                    if (CommonUtils.PRIVATE.equals(viewType.getValue())) {
                        NotificationUtils.getAlertNotification("View Updated Successfully",
                                "You have successfully updated private view " + viewName.getValue());
                    } else {
                        NotificationUtils.getAlertNotification("View Updated Successfully",
                                "You have successfully updated public view " + viewName.getValue());
                    }
                }
                close();
            }
        });
        LOGGER.debug("Ending SaveView configureFields");
    }

    private boolean isDuplicateView(final String viewName) {
        LOGGER.debug("Entering isDuplicateView method with viewName " + viewName);
        return logic.isDuplicateView(viewName);
    }
}
