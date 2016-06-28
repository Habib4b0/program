/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galworkflow.ui.form;

import com.stpl.app.galworkflow.logic.WorkflowLogic;
import com.stpl.app.galworkflow.dto.InboxDashboardDTO;
import com.stpl.app.galworkflow.util.CommonUtils;
import com.stpl.app.galworkflow.util.NotificationUtils;
import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
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

    public SaveView(InboxDashboardDTO inboxDashboardDTO, Boolean isFirst) {
        this.inboxDashboardDTO = inboxDashboardDTO;
        this.isFirst = isFirst;
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
    }

    private void configureFields() {
        LOGGER.info("Entering SaveView configureFields");
        viewName.focus();
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
        } else {
            addBtn.setEnabled(false);
            updateBtn.setEnabled(true);
            viewName.setValue(inboxDashboardDTO.getViewName());
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

                if (StringUtils.isBlank(viewName.getValue()) || "null".equals(String.valueOf(viewName.getValue()))) {
                    NotificationUtils.getErrorNotification("Invalid view name", "Enter the view name");
                } else {
                    inboxDashboardDTO.setViewName(viewName.getValue());
                    inboxDashboardDTO.setViewType(String.valueOf(viewType.getValue()));
                    logic.viewInsertOrUpdate(Boolean.TRUE, inboxDashboardDTO);
                    if (CommonUtils.PRIVATE.equals(viewType.getValue())) {
                        NotificationUtils.getAlertNotification("View Added Successfully",
                                "You have successfully added private view " + viewName.getValue());
                    } else {
                        NotificationUtils.getAlertNotification("View Added Successfully",
                                "You have successfully added public view " + viewName.getValue());
                    }
                    close();
                }
            }
        });

        updateBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                inboxDashboardDTO.setViewType(String.valueOf(viewType.getValue()));
                inboxDashboardDTO.setViewName(viewName.getValue());
                logic.viewInsertOrUpdate(Boolean.FALSE, inboxDashboardDTO);
                if (CommonUtils.PRIVATE.equals(viewType.getValue())) {
                    NotificationUtils.getAlertNotification("View Updated Successfully",
                            "You have successfully updated private view " + viewName.getValue());
                } else {
                    NotificationUtils.getAlertNotification("View Updated Successfully",
                            "You have successfully updated public view " + viewName.getValue());
                }
                close();
            }
        });
        LOGGER.info("Ending SaveView configureFields");
    }

}
