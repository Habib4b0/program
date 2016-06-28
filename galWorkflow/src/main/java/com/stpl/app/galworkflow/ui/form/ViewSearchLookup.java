/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galworkflow.ui.form;

import com.stpl.app.galworkflow.dto.InboxDashboardDTO;
import com.stpl.app.galworkflow.filtergenerator.ViewTableGenerator;
import com.stpl.app.galworkflow.logic.WorkflowLogic;
import com.stpl.app.galworkflow.logic.tablelogic.ViewLookuptablelogic;
import com.stpl.app.galworkflow.util.CommonUtils;
import com.stpl.app.galworkflow.util.NotificationUtils;
import com.stpl.app.util.ConstantsUtils;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.jboss.logging.Logger;

/**
 *
 * @author rohitvignesh.s
 */
public class ViewSearchLookup extends Window {

    ViewLookuptablelogic tablelogic = new ViewLookuptablelogic();
    InboxDashboardDTO inboxDashboardDTO = new InboxDashboardDTO();
    private static final Logger LOGGER = Logger.getLogger(ViewSearchLookup.class);
    private final ExtPagedTable resultTable = new ExtPagedTable(tablelogic);
    public BeanItemContainer<InboxDashboardDTO> viewLookupItemBean = new BeanItemContainer<>(InboxDashboardDTO.class);
    @UiField("viewName")
    public TextField viewName;
    @UiField("searchBtn")
    public Button searchBtn;
    @UiField("resetBtn")
    public Button resetBtn;
    @UiField("selectBtn")
    public Button selectBtn;
    @UiField("closeBtn")
    public Button closeBtn;
    @UiField("deleteBtn")
    public Button deleteBtn;
    @UiField("tableLayout")
    VerticalLayout tableLayout;
    @UiField("panelid")
    Panel panelid;
    @UiField("controlLayout")
    HorizontalLayout controlLayout;
    String lookupName = StringUtils.EMPTY;
    public boolean ispublicView = true;
    CustomTextField viewTextField;

    public ViewSearchLookup(String lookupName, CustomTextField viewTextField) {
        this.viewTextField = viewTextField;
        this.lookupName = lookupName;
        init();
    }

    private void init() {
        addToContent();
        configureTable();
        configureFields();
    }

    private void addToContent() {
        setContent(Clara.create(getClass().getResourceAsStream("/clara/ViewSearchLookup.xml"), this));
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        center();
        setClosable(true);
        setModal(true);
        setHeight(80f, Unit.PERCENTAGE);
        setWidth(80f, Unit.PERCENTAGE);
        setCaption("View Search Popup");
        panelid.getContent().setSizeUndefined();
        selectBtn.setEnabled(false);

    }

    private void configureFields() {
        searchButton();
        resetButton();
        selectButton();
        closeButton();
        deleteButton();
    }

    private void configureTable() {
        LOGGER.info("Entering ViewSearchLookup configureFields");
        viewName.focus();
        List<Integer> pageLength = new ArrayList<>();
        pageLength.add(10);
        pageLength.add(15);
        pageLength.add(20);
        pageLength.add(25);
        pageLength.add(50);
        pageLength.add(100);
        tablelogic.getControlConfig().setPageLengthsAndCaptions(pageLength);

        tableLayout.addComponent(resultTable);
        tableLayout.addComponent(controlLayout);
        CommonUtils.getResponsiveControls(tablelogic.createControls(), controlLayout);

        tablelogic.setContainerDataSource(viewLookupItemBean);
        resultTable.setVisibleColumns(CommonUtils.VIEW_SEARCH_LOOKUP_COLUMNS);
        resultTable.setColumnHeaders(CommonUtils.VIEW_SEARCH_LOOKUP_HEADER);
        resultTable.setPageLength(10);
        tablelogic.sinkItemPerPageWithPageLength(false);
        resultTable.setComponentError(null);
        resultTable.setSelectable(true);
        resultTable.markAsDirty();
        resultTable.setFilterBarVisible(true);
        resultTable.setImmediate(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setFilterGenerator(new ViewTableGenerator());
        resultTable.setValidationVisible(false);
        resultTable.addStyleName("filterbar");
        resultTable.setFilterBarVisible(true);
        resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void itemClick(ItemClickEvent event) {
                try {
                    selectBtn.setEnabled(true);
                } catch (Exception e) {
                    LOGGER.error(e);
                }

            }
        });
        LOGGER.info("Ending ViewSearchLookup configureFields");
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public Button searchButton() {

        searchBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (StringUtils.isEmpty(viewName.getValue())) {
                    NotificationUtils.getErrorNotification("No Search Criteria", "Please enter the search criteria.");
                    viewLookupItemBean.removeAllItems();
                } else {
                    inboxDashboardDTO.setViewName(viewName.getValue());
                    inboxDashboardDTO.setViewType(lookupName);
                    tablelogic.configureSearchData(inboxDashboardDTO);
                    tablelogic.setCurrentPage(1);
                    if (tablelogic.isResultsEmpty()) {
                        CommonUtils.successNotification("No results found");
                    } else {
                        CommonUtils.successNotification("Search Completed");
                    }

                }
            }
        });
        return searchBtn;
    }

    public Button selectButton() {
        selectBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                selectButtonOnClick();
            }
        });
        return selectBtn;
    }

    protected void selectButtonOnClick() {
        if (resultTable.getValue() != null) {
            inboxDashboardDTO = (InboxDashboardDTO) resultTable.getValue();
            viewTextField.setData(inboxDashboardDTO);
            viewTextField.setValue(inboxDashboardDTO.getViewName());
            close();
        }
    }

    public Button resetButton() {

        resetBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(Button.ClickEvent event) {

                new NotificationUtils() {
                    @Override
                    public void yesMethod() {
                        viewName.setValue(StringUtils.EMPTY);
                        resultTable.removeAllItems();
                        resultTable.setVisibleColumns(CommonUtils.VIEW_SEARCH_LOOKUP_COLUMNS);
                        resultTable.setColumnHeaders(CommonUtils.VIEW_SEARCH_LOOKUP_HEADER);
                        selectBtn.setEnabled(false);
                    }

                    @Override
                    public void noMethod() {
                    }
                }.getOkCancelMessage("Reset Confirmation",
                        "Are you sure you want to reset?");
            }
        });
        return resetBtn;

    }

    private Button closeButton() {
        closeBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().setData(null);
                close();
            }
        });
        return closeBtn;

    }

    private Button deleteButton() {
        deleteBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(Button.ClickEvent event) {
                new NotificationUtils() {
                    @Override
                    public void yesMethod() {
                        deleteButtonLogic();
                    }

                    @Override
                    public void noMethod() {
                    }
                }.getOkCancelMessage("Delete confirmation",
                        "Are you sure that you want to delete this view?");

            }
        });
        return deleteBtn;

    }

    protected void deleteButtonLogic() {
        if (resultTable.getValue() != null) {
            WorkflowLogic workFlowLogic = new WorkflowLogic();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            InboxDashboardDTO dto = (InboxDashboardDTO) resultTable.getValue();
            String createdById = dto.getCreatedBy();
            String wfSearchCriteriaViewDataId = String.valueOf(dto.getWorkflowMasterSystemId());

            if (createdById.equals(userId)) {
                Boolean isDeleted = workFlowLogic.deleteView(Integer.valueOf(wfSearchCriteriaViewDataId));
                inboxDashboardDTO.setViewName(viewName.getValue());
                inboxDashboardDTO.setViewType(lookupName);
                tablelogic.configureSearchData(inboxDashboardDTO);
                tablelogic.setCurrentPage(1);
                if (isDeleted) {
                    Notification.show("View Deleted Successfully");
                }
            } else {
                NotificationUtils.getErrorNotification("Delete View",
                        "You can't delete other's view.\n Please try again.");
            }
        }

    }
}
