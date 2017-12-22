/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.ui.form;

import com.stpl.app.gtnworkflow.dto.InboxDashboardDTO;
import com.stpl.app.gtnworkflow.filtergenerator.ViewTableGenerator;
import com.stpl.app.gtnworkflow.logic.WorkflowLogic;
import com.stpl.app.gtnworkflow.logic.tablelogic.ViewLookuptablelogic;
import com.stpl.app.gtnworkflow.util.CommonUtils;
import com.stpl.app.gtnworkflow.util.ConstantUtils;
import com.stpl.app.gtnworkflow.util.ConstantsUtils;
import com.stpl.app.gtnworkflow.util.NotificationUtils;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
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
    InboxDashboardDTO inboxDashboardDTO;
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
    @UiField("mainPanel")
    Panel mainPanel;
    @UiField("mainLayout")
    VerticalLayout mainLayout;
    @UiField("panelid")
    Panel panelid;
    @UiField("controlLayout")
    HorizontalLayout controlLayout;
    String lookupName = StringUtils.EMPTY;
    public boolean ispublicView = true;
    CustomTextField viewTextField;
    String businessProcess;
    @UiField("vertiLayout")
    VerticalLayout vertiLayout;

    public ViewSearchLookup(String lookupName, CustomTextField viewTextField, String businessProcess, InboxDashboardDTO inboxDashboardDTO) {
        this.viewTextField = viewTextField;
        this.lookupName = lookupName;
        this.businessProcess = businessProcess;
        this.inboxDashboardDTO = inboxDashboardDTO;
        init();
    }

    private void init() {
        addToContent();
        configureTable();
        configureFields();
    }

    private void addToContent() {
        setContent(Clara.create(getClass().getResourceAsStream("/clara/ViewSearchLookup.xml"), this));
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        center();
        setClosable(true);
        setModal(true);
        setHeight(NumericConstants.FLOAT_SIXTY_FIVE, Unit.PERCENTAGE); 
        setWidth(NumericConstants.FLOAT_EIGHTY, Unit.PERCENTAGE);
        setCaption("View Search Popup");
        mainPanel.setSizeFull();
        mainLayout.setWidth("100%");
        panelid.setWidth("100%");
        vertiLayout.setWidth("100%");
        tableLayout.setWidth("100%");
        if (WorkflowConstants.getBusinessProcessNameArm().equalsIgnoreCase(businessProcess)) {
            selectBtn.setEnabled(true);
            deleteBtn.setVisible(false);
        } else {
            selectBtn.setEnabled(false);
            deleteBtn.setVisible(true);
            deleteBtn.setEnabled(false);
        }
        
                }

    private void configureFields() {
        searchButton();
        resetButton();
        selectButton();
        closeButton();
        deleteButton();
    }

    private void configureTable() {
        LOGGER.debug("Entering ViewSearchLookup configureFields");
        
        resultTable.setSizeFull();
        List<Integer> pageLength = new ArrayList<>();
        pageLength.add(NumericConstants.TEN);
        pageLength.add(NumericConstants.FIFTEEN);
        pageLength.add(NumericConstants.TWENTY);
        pageLength.add(NumericConstants.TWENTY_FIVE);
        pageLength.add(NumericConstants.FIFTY);
        pageLength.add(NumericConstants.HUNDRED);
        tablelogic.getControlConfig().setPageLengthsAndCaptions(pageLength);

        tableLayout.addComponent(resultTable);
        CommonUtils.getResponsiveControls(tablelogic.createControls(), controlLayout);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setFilterGenerator(new ViewTableGenerator());
        tablelogic.setContainerDataSource(viewLookupItemBean);
        if (!WorkflowConstants.getBusinessProcessNameArm().equalsIgnoreCase(businessProcess)) {
            resultTable.setVisibleColumns(ConstantUtils.getInstance().viewSearchLookupColumns);
            resultTable.setColumnHeaders(ConstantUtils.getInstance().viewSearchLookupHeader);
        } else {
           resultTable.setVisibleColumns(ConstantUtils.getInstance().viewSearchLookupColumnsArm);
            resultTable.setColumnHeaders(ConstantUtils.getInstance().viewSearchLookupHeaderArm);
        }
        resultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultTable.setPageLength(NumericConstants.TEN);
        tablelogic.sinkItemPerPageWithPageLength(false);
        resultTable.setComponentError(null);
        resultTable.setSelectable(true);
        resultTable.markAsDirty();
        
        resultTable.setImmediate(true);
        resultTable.setFilterBarVisible(true);
        resultTable.setValidationVisible(false);
        resultTable.addStyleName("filterbar");
        resultTable.setConverter("creationDate", new DateToStringConverter()); 
        resultTable.setConverter("modifiedDate", new DateToStringConverter()); 
        resultTable.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if(resultTable.getValue() != null) {
                    deleteBtn.setEnabled(true);
                    selectBtn.setEnabled(true);
                } else {
                    selectBtn.setEnabled(false);
                    deleteBtn.setEnabled(false);
                }
            }
        });
        LOGGER.debug("Ending ViewSearchLookup configureFields");
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    public Button searchButton() {

        searchBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (StringUtils.isEmpty(viewName.getValue())) {
                    if (WorkflowConstants.getBusinessProcessNameArm().equalsIgnoreCase(businessProcess)) {
                        NotificationUtils.getErrorNotification("Invalid Search", "There are no Views that match the search criteria. Please try again.");
                    } else {
                        NotificationUtils.getErrorNotification("No Search Criteria", "Please enter the search criteria.");
                    }
                    viewLookupItemBean.removeAllItems();
                } else {
                    inboxDashboardDTO.setViewName(viewName.getValue());
                    inboxDashboardDTO.setViewType(lookupName);
                    tablelogic.configureSearchData(inboxDashboardDTO);
                    tablelogic.setCurrentPage(1);
                    if (tablelogic.isResultsEmpty()) {
                        NotificationUtils.getErrorNotification("Invalid Search", "There are no Views that match the search criteria.  Please try again.");
                    } else {
                        CommonUtils.successNotification("Search Completed");
                    }

                }
                inboxDashboardDTO.setViewName(StringUtils.EMPTY);
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
        } else if (resultTable.getValue() == null && WorkflowConstants.getBusinessProcessNameArm().equalsIgnoreCase(businessProcess)) {
            NotificationUtils.getErrorNotification("No View Selected", "There is no view selected. Please select a saved view and try again.");
        }
    }

    public Button resetButton() {

        resetBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(Button.ClickEvent event) {
                if (WorkflowConstants.getBusinessProcessNameArm().equalsIgnoreCase(businessProcess)) {
                    new NotificationUtils() {
                        @Override
                        public void yesMethod() {
                            viewName.setValue(StringUtils.EMPTY);
                            resultTable.setVisibleColumns(ConstantUtils.getInstance().viewSearchLookupColumnsArm);
                            resultTable.setColumnHeaders(ConstantUtils.getInstance().viewSearchLookupHeaderArm);
                        }

                        @Override
                        public void noMethod() {
                            return;
                        }
                    }.getConfirmationMessage("Confirm Reset",
                            "Are you sure you want to reset the search criteria to its default state?");
                } else {
                    new NotificationUtils() {
                        @Override
                        public void yesMethod() {
                            viewName.setValue(StringUtils.EMPTY);
                            resultTable.removeAllItems();
                            resultTable.setVisibleColumns(ConstantUtils.getInstance().viewSearchLookupColumns);
                            resultTable.setColumnHeaders(ConstantUtils.getInstance().viewSearchLookupHeader);
                            selectBtn.setEnabled(false);
                        }

                        @Override
                        public void noMethod() {
                            return;
                        }
                    }.getOkCancelMessage("Reset Confirmation",
                            "Are you sure you want to reset?");
                }
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
                if (resultTable.getValue() != null) {
                    new NotificationUtils() {
                        @Override
                        public void yesMethod() {
                            deleteButtonLogic();
                        }

                        @Override
                        public void noMethod() {
                            return;
                        }
                    }.getOkCancelMessage("Delete confirmation",
                            "Are you sure that you want to delete this view?");
                }

            }
        });
        return deleteBtn;

    }

    protected void deleteButtonLogic() {
        if (resultTable.getValue() != null) {
            WorkflowLogic workFlowLogic = new WorkflowLogic();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            InboxDashboardDTO dto = (InboxDashboardDTO) resultTable.getValue();
            String createdById = dto.getCreatedById();
            String wfSearchCriteriaViewDataId = String.valueOf(dto.getWorkflowInboxSystemId());

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
