/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galworkflow.ui.form;

import com.stpl.app.galworkflow.bpm.JdbcConnection;
import com.stpl.app.galworkflow.dto.InboxDashboardDTO;
import com.stpl.app.galworkflow.filtergenerator.WorkFlowFilterGenerate;
import com.stpl.app.galworkflow.logic.WorkflowLogic;
import com.stpl.app.galworkflow.logic.tablelogic.WorkFlowTableLogic;
import com.stpl.app.galworkflow.ui.lookup.UsersLookup;
import com.stpl.app.galworkflow.ui.lookup.WorkFlowHistoryLookup;
import com.stpl.app.galworkflow.util.CommonUtils;
import com.stpl.app.galworkflow.util.ConstantUtils;
import com.stpl.app.galworkflow.util.HelperListUtil;
import com.stpl.app.galworkflow.util.NotificationUtils;
import com.stpl.app.model.WorkflowProcessInfo;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.WorkflowProcessInfoLocalServiceUtil;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.util.CsvExportforPagedTable;
import com.stpl.ifs.util.HelperDTO;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.Group;
import com.stpl.portal.model.Organization;
import com.stpl.portal.model.Role;
import com.stpl.portal.model.User;
import com.stpl.portal.service.GroupLocalServiceUtil;
import com.stpl.portal.service.OrganizationLocalServiceUtil;
import com.stpl.portal.service.RoleLocalServiceUtil;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * class is responsible for the Landing page of Workflow Inbox.
 *
 * @author KarthikeyanS
 */
public class InboxDashBoard extends CustomComponent implements View {

    private static final Logger LOGGER = Logger.getLogger(InboxDashBoard.class);
    WorkFlowTableLogic tableLogic = new WorkFlowTableLogic();
    private ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    Button approve = new Button("APPROVE");
    Button reject = new Button("REJECT");
    Button cancel = new Button("CANCEL");
    UsersLookup usersLookUp;
    ViewSearchLookup lookUp = null;
    SaveView saveLookup = null;
    @UiField("privateView")
    public CustomTextField privateView;
    @UiField("publicView")
    public CustomTextField publicView;
    @UiField("workflowId")
    public TextField workflowId;
    @UiField("workflowName")
    public TextField workflowName;
    @UiField("workflowDescription")
    public TextField workflowDescription;
    @UiField("businessProcess")
    public ComboBox businessProcess;

    @UiField("createdBy")
    public CustomTextField createdBy;
    @UiField("approvedBy")
    public CustomTextField approvedBy;
    @UiField("creationDateRangeFrom")
    public PopupDateField creationDateRangeFrom;
    @UiField("creationDateRangeTo")
    public PopupDateField creationDateRangeTo;
    @UiField("approvedDateRangeFrom")
    public PopupDateField approvedDateRangeFrom;
    @UiField("approvedDateRangeTo")
    public PopupDateField approvedDateRangeTo;

    @UiField("contractId")
    public TextField contractId;
    @UiField("contractNo")
    public TextField contractNo;
    @UiField("contractName")
    public TextField contractName;
    @UiField("contractType")
    public ComboBox contractType;
    @UiField("companyId")
    public TextField companyId;
    @UiField("companyNo")
    public TextField companyNo;
    @UiField("companyName")
    public TextField companyName;
    @UiField("itemId")
    public TextField itemId;
    @UiField("itemNo")
    public TextField itemNo;
    @UiField("itemName")
    public TextField itemName;
    @UiField("deductionLevel")
    public ComboBox deductionLevel;
    @UiField("deductionValue")
    public ComboBox deductionValue;

    @UiField("search")
    public Button search;
    @UiField("reset")
    public Button reset;
    @UiField("saveProfile")
    public Button saveProfile;
    @UiField("history")
    public Button history;
    @UiField("excelBtn")
    public Button excelBtn;
    @UiField("open")
    public Button open;
    @UiField("view")
    public Button view;
    @UiField("resultslayout")
    public VerticalLayout resultslayout;
    @UiField("controlLayout")
    public HorizontalLayout controlLayout;
    @UiField("cssLayout")
    public CssLayout cssLayout;
    @UiField("cssLayoutBtn")
    public CssLayout cssLayoutBtn;
    @UiField("cssLayoutBottom")
    public CssLayout cssLayoutBottom;

    @UiField("cssLayout1")
    public CssLayout cssLayout1;
    @UiField("detailSearch")
    public Panel detailSearch;
    HelperDTO dto = new HelperDTO(null);
    public InboxDashboardDTO inboxDashboardDTO = new InboxDashboardDTO();
    final ErrorLabel errorMsg = new ErrorLabel();
    public BeanItemContainer<InboxDashboardDTO> inboxDashboardBean = new BeanItemContainer<InboxDashboardDTO>(InboxDashboardDTO.class);
    InboxDashboardDTO hiddenInboxDashBoardDTO = new InboxDashboardDTO();
    WorkflowLogic logic = new WorkflowLogic();
    String userId;

    String moduleName;
    public GridLayout gridLayout = new GridLayout(4, 1);
    CommonUtils commonUtils = new CommonUtils();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    final TextField masterIdHidden = new TextField();
    private boolean viewPermission = false;
    private boolean openPermission = false;
    public InboxDashboardDTO inboxDashBoardDTOHidden = new InboxDashboardDTO();
    public boolean ispublicView = true;

    public InboxDashBoard() {
        init();

    }

    /*
     *  Used for initialised the Layout
     */
    private void init() {

        addToContent();
        configureTable();
        configureFields();
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
    /*
     * Used to add the component to the layout
     */

    public void addToContent() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/InboxDashboard.xml"), this));
    }

    private void configureTable() {
        LOGGER.info("Inside Configure Table results");
        List<Integer> pageLength = new ArrayList<Integer>();
        pageLength.add(10);
        pageLength.add(15);
        pageLength.add(20);
        pageLength.add(25);
        pageLength.add(50);
        pageLength.add(100);
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pageLength);
        resultslayout.addComponent(resultTable);
        CommonUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
        resultslayout.addComponent(controlLayout);

        tableLogic.setContainerDataSource(inboxDashboardBean);
        resultTable.setVisibleColumns(CommonUtils.INBOX_DASHBOARD_COLUMNS);
        resultTable.setColumnHeaders(CommonUtils.INBOX_DASHBOARD_HEADER);
        tableLogic.setPageLength(15);
        tableLogic.sinkItemPerPageWithPageLength(false);

        resultTable.setSelectable(true);
        resultTable.markAsDirty();
        resultTable.setComponentError(null);
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setFilterGenerator(new WorkFlowFilterGenerate());
        resultTable.setValidationVisible(false);
        resultTable.setWidth("100%");
        resultTable.addStyleName("filterbar");

        resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                resultsItemClick(event.getItemId());
            }
        });
    }

    BrowserWindowOpener opener;
    /*
     * Used for getting the data while clicking a row
     * Also used for initializing the browser opener
     */

    protected void resultsItemClick(Object id) {
        JdbcConnection connection = new JdbcConnection();
        String workflowMasterSysId = null;
        String workflowStatus = null;
        String userType = null;
        String workflowId = null;
        String portletName = StringUtils.EMPTY;
        String contractStructure = StringUtils.EMPTY;
        if (id != null) {
            BeanItem<?> targetItem = null;
            if (id instanceof BeanItem<?>) {
                targetItem = (BeanItem<?>) id;
            } else if (id instanceof InboxDashboardDTO) {
                targetItem = new BeanItem<InboxDashboardDTO>((InboxDashboardDTO) id);
            }
            String createdById = (((InboxDashboardDTO) targetItem.getBean()).getCreatedById());
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(CommonUtils.USER_ID));
            String masterSid = String.valueOf(((InboxDashboardDTO) targetItem.getBean()).getMasterSid());
            int noOfApprovals = ((InboxDashboardDTO) targetItem.getBean()).getNoOfApprovals();
            int approvalLevel = ((InboxDashboardDTO) targetItem.getBean()).getApprovalLevel();
            masterIdHidden.setValue(masterSid);
            workflowMasterSysId = String.valueOf(((InboxDashboardDTO) targetItem.getBean()).getWorkflowMasterSystemId());
            inboxDashBoardDTOHidden = (InboxDashboardDTO) targetItem.getBean();
            workflowStatus = ((InboxDashboardDTO) targetItem.getBean()).getWorkFlowStatus();

            workflowId = ((InboxDashboardDTO) targetItem.getBean()).getWorkflowId();
            contractStructure = ((InboxDashboardDTO) targetItem.getBean()).getContractStructure();
            List list = null;
            Long processId = 0L;
            boolean isSubmitter = (createdById.equals(userId));
            cssLayoutBottom.removeAllComponents();
            cssLayoutBottom.addComponent(history);
            cssLayoutBottom.addComponent(open);
            cssLayoutBottom.addComponent(view);
            open.setEnabled(true);
            view.setEnabled(true);
            cssLayoutBottom.addComponent(excelBtn);
            try {
                if (!isSubmitter) {
                    DynamicQuery query = DynamicQueryFactoryUtil.forClass(WorkflowProcessInfo.class);
                    final ProjectionList projList = ProjectionFactoryUtil.projectionList();
                    projList.add(ProjectionFactoryUtil.property("processInstanceId"));
                    if (workflowId.startsWith(ConstantsUtils.BR) || workflowId.startsWith(ConstantsUtils.FD)) {
                        query.add(RestrictionsFactoryUtil.eq("accClosureMasterSid", Integer.parseInt(masterSid)));
                    } else if (workflowId.startsWith(WorkflowConstants.getContractWorkflowParameter())) {
                        query.add(RestrictionsFactoryUtil.eq("contractMasterSid", Integer.parseInt(masterSid)));
                        query.add(RestrictionsFactoryUtil.eq("contractStructure", contractStructure));
                    } else {
                        query.add(RestrictionsFactoryUtil.eq("projectionMasterSid", Integer.parseInt(masterSid)));
                    }
                    query.setProjection(ProjectionFactoryUtil.distinct(projList));
                    try {
                        list = WorkflowProcessInfoLocalServiceUtil.dynamicQuery(query);
                    } catch (SystemException ex) {
                        java.util.logging.Logger.getLogger(InboxDashBoard.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    List<String> roles = new ArrayList<>();
                    if (list != null && list.size() > 0) {
                        processId = Long.valueOf(list.get(0) == null ? "0" : list.get(0).toString());
                        if (processId != 0) {
                            roles = connection.getGetRoleForCurrentTask(processId);
                        }
                    }
                    List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(Long.parseLong(userId));
                    boolean roleMatched = false;
                    if (roles != null) {
                        for (Role r : userRoles) {
                            if (roles.contains(r.getName())) {
                                roleMatched = true;
                                break;
                            }
                        }
                    }
                    viewPermission = roleMatched;
                    openPermission = false;
                    if (workflowId.startsWith(WorkflowConstants.getGovWorkflowParameter()) || workflowId.startsWith(WorkflowConstants.getComWorkflowParameter()) || workflowId.startsWith(WorkflowConstants.getContractWorkflowParameter())|| workflowId.startsWith(WorkflowConstants.getAccrualWorkflowParameter()) ) {
                        userType = WorkflowConstants.getApproverConstant();
                    } else if (workflowId.startsWith(ConstantsUtils.BR) || workflowId.startsWith(ConstantsUtils.FD)) {
                        userType = "yes";
                    }
                } else {
                    userType = WorkflowConstants.getCreatorConstant();
                    openPermission = true;
                    viewPermission = false;
                }

            } catch (Exception e) {
                LOGGER.error("Exception at submitter check" + e);
            }
            String furl = StringUtils.EMPTY;

            if (Page.getCurrent().getLocation().getPort() == 8080 || Page.getCurrent().getLocation().getPort() == 8081) {
                if (workflowId.startsWith(WorkflowConstants.getComWorkflowParameter())) {
                    furl = "http://" + Page.getCurrent().getLocation().getHost() + ":" + Page.getCurrent().getLocation().getPort() + "/web/guest/commercial?nmFlow='true'";
                } else if (workflowId.startsWith(WorkflowConstants.getGovWorkflowParameter())) {
                    furl = "http://" + Page.getCurrent().getLocation().getHost() + ":" + Page.getCurrent().getLocation().getPort() + "/web/guest/government?mFlow='true'";
                } else if (workflowId.startsWith(ConstantsUtils.BR)) {
                    furl = "http://" + Page.getCurrent().getLocation().getHost() + ":" + Page.getCurrent().getLocation().getPort() + "/web/guest/base-rate?nmFlow='true'";
                } else if (workflowId.startsWith(ConstantsUtils.FD)) {
                    furl = "http://" + Page.getCurrent().getLocation().getHost() + ":" + Page.getCurrent().getLocation().getPort() + "/web/guest/fixed-dollar-adjustment?nmFlow='true'";
                } else if (workflowId.startsWith(WorkflowConstants.getAccrualWorkflowParameter())) {
                    furl = "http://" + Page.getCurrent().getLocation().getHost() + ":" + Page.getCurrent().getLocation().getPort() + "/web/guest/accrual-rate-projection?arFlow='true'";
                } else if (workflowId.startsWith(WorkflowConstants.getContractWorkflowParameter())) {
                    furl = "http://" + Page.getCurrent().getLocation().getHost() + ":" + Page.getCurrent().getLocation().getPort() + "/web/guest/contract-dashboard?cwFlow='true'";
                }
            } else {

                if (workflowId.startsWith(WorkflowConstants.getComWorkflowParameter())) {
                    furl = "https://" + Page.getCurrent().getLocation().getHost() + "/web/guest/commercial?nmFlow='true'";
                } else if (workflowId.startsWith(WorkflowConstants.getGovWorkflowParameter())) {
                    furl = "https://" + Page.getCurrent().getLocation().getHost() + "/web/guest/government?nmFlow='true'";
                } else if (workflowId.startsWith(ConstantsUtils.BR)) {
                    furl = "https://" + Page.getCurrent().getLocation().getHost() + "/web/guest/base-rate?nmFlow='true'";
                } else if (workflowId.startsWith(ConstantsUtils.FD)) {
                    furl = "https://" + Page.getCurrent().getLocation().getHost() + "/web/guest/fixed-dollar-adjustment?nmFlow='true'";
                } else if (workflowId.startsWith(WorkflowConstants.getAccrualWorkflowParameter())) {
                    furl = "https://" + Page.getCurrent().getLocation().getHost() + "/web/guest/accrual-rate-projection?arFlow='true'";
                } else if (workflowId.startsWith(WorkflowConstants.getContractWorkflowParameter())) {
                    furl = "https://" + Page.getCurrent().getLocation().getHost() + "/web/guest/contract-dashboard?cwFlow='true'";
                }
            }
            LOGGER.info("Redirecting to URL :" + furl);
            opener = new BrowserWindowOpener(furl);
            opener.setFeatures("height=800,width=1000,resizable,scrollbars=1");
            opener.setFeatures("toolbar=no,scrollbars=1,location=no");

            Component openComponent = cssLayoutBottom.getComponent(1);
            Component viewComponent = cssLayoutBottom.getComponent(2);

            if (isSubmitter && (WorkflowConstants.getWithdrawnStatus().equalsIgnoreCase(workflowStatus) || WorkflowConstants.getRejectedStatus().equalsIgnoreCase(workflowStatus) || WorkflowConstants.getPendingStatus().equals(workflowStatus))) {
                Button open1 = new Button("OPEN");
                cssLayoutBottom.replaceComponent(openComponent, open1);
                openComponent = cssLayoutBottom.getComponent(1);

                if (openPermission) {
                    opener.extend(open1);
                } else {
                    open1.addClickListener(openBtnClickListener);
                }
                viewComponent.setEnabled(false);
                openComponent.setEnabled(true);
            } else {
                Button view1 = new Button("VIEW");
                cssLayoutBottom.replaceComponent(viewComponent, view1);
                viewComponent = cssLayoutBottom.getComponent(2);
                opener.extend(view1);
                viewComponent.setEnabled(true);
                openComponent.setEnabled(false);
            }

            if (workflowId.startsWith(WorkflowConstants.getGovWorkflowParameter()) || workflowId.startsWith(WorkflowConstants.getComWorkflowParameter()) || workflowId.startsWith(WorkflowConstants.getAccrualWorkflowParameter())) {
                opener.setParameter("projectionIdFromWorkflow", masterIdHidden.getValue());
            } else if (workflowId.startsWith(ConstantsUtils.BR) || workflowId.startsWith(ConstantsUtils.FD)) {
                opener.setParameter("accClosureIdMasterSid", masterIdHidden.getValue());
            } else if (workflowId.startsWith(WorkflowConstants.getContractWorkflowParameter())) {
                opener.setParameter("contractMasterSid", masterIdHidden.getValue());
            }
            if (workflowId.startsWith(WorkflowConstants.getGovWorkflowParameter())) {
                portletName = getGovernmentConstant();
            } else if (workflowId.startsWith(WorkflowConstants.getComWorkflowParameter())) {
                portletName = getCommercialConstant();
            } else if (workflowId.startsWith(WorkflowConstants.getAccrualWorkflowParameter())) {
                portletName = getAccrualConstant();
            }
            opener.setParameter("workflowId", workflowMasterSysId);
            opener.setParameter("workflowStatus", workflowStatus);
            opener.setParameter("userType", userType);
            opener.setParameter("noOfApprovals", String.valueOf(noOfApprovals));
            opener.setParameter("approvalLevel", String.valueOf(approvalLevel));
            opener.setParameter("portletName", portletName);
        } else {
            masterIdHidden.setValue(null);
        }
    }
    /*
     * Used to configure the components
     */

    private void configureFields() {

        try {
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldRsHM = stplSecurity.getFieldOrColumnPermission(userId, CommonUtils.WORKFLOW_INBOX + "," + "LandingScreen", false);
            final String selectOne = getSelectOne();
            addResposivenessToInformationPanel(fieldRsHM);
            
            JavaScript.getCurrent().addFunction("storageEventListener", new JavaScriptFunction() {

                @Override
                public void call(JSONArray arguments) throws JSONException {
                    try {
                        String businessProcessvalue = String.valueOf(businessProcess.getValue());
                        if (arguments.getString(0).equals(businessProcessvalue) && arguments.getBoolean(1)) {
                            tableLogic.setCurrentPage(tableLogic.getCurrentPage());
                            JavaScript.getCurrent().execute("localStorage.setItem('"+businessProcessvalue+"', 'false');");
                        }
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            });
            workflowName.setImmediate(true);
            workflowName.setDescription(workflowName.getValue());
            creationDateRangeFrom.setDateFormat(getCommonDateFormat());
            creationDateRangeTo.setDateFormat(getCommonDateFormat());
            approvedDateRangeFrom.setDateFormat(getCommonDateFormat());
            approvedDateRangeTo.setDateFormat(getCommonDateFormat());
            search.setEnabled(true);
            /*
             * RESET logic implementation
             */
            reset.addClickListener(new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                public void buttonClick(Button.ClickEvent event) {
                    new NotificationUtils() {
                        @Override
                        public void yesMethod() {

                            resetButtonClickLogic();
                        }

                        @Override
                        public void noMethod() {
                        }
                    }.getOkCancelMessage(commonUtils.getConfirmResetMessage(), commonUtils.getResetMessage());
                }
            });

            /*
             * Search logic implementation
             */
            search.addClickListener(new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                public void buttonClick(Button.ClickEvent event) {
                    List<Object> collapsedColumns = new ArrayList<Object>();
                    for (Object item : resultTable.getVisibleColumns()) {
                        if (resultTable.isColumnCollapsed(item)) {
                            collapsedColumns.add(item);
                        }
                    }
                    if (businessProcess.getValue() == null || selectOne.equals(businessProcess.getValue()) || businessProcess.getValue().equals(StringUtils.EMPTY)) {
                        final MessageBox msg = MessageBox.showPlain(Icon.WARN, commonUtils.getErrorHeaderMessage(), commonUtils.getSearchMessageBusinessProcessError(), new MessageBoxListener() {

                            /**
                             * The method is triggered when a button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                    } else if (deductionLevel.getValue() != null && !String.valueOf(deductionLevel.getValue()).isEmpty() && deductionValue.getValue() == null) {
                        final MessageBox msg = MessageBox.showPlain(Icon.WARN, commonUtils.getErrorHeaderMessage(), commonUtils.getSearchMessageDeductionError(), new MessageBoxListener() {

                            /**
                             * The method is triggered when a button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                    } else {

                        cssLayoutBottom.getComponent(1).setEnabled(false);
                        cssLayoutBottom.getComponent(2).setEnabled(false);
                        try {
                            setDataToDto();
                            tableLogic.configureSearchData(inboxDashboardDTO, true);
                            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
                            resultTable.setFilterGenerator(new WorkFlowFilterGenerate());
                            resultTable.setImmediate(true);
                            resultTable.addStyleName("TableCheckBox");
                            resultTable.setSelectable(true);
                            resultTable.setConverter("creationDate", new DateToStringConverter());
                            resultTable.setConverter("approvedDate", new DateToStringConverter());
                            if (tableLogic.isResultsEmpty()) {
                                CommonUtils.successNotification(commonUtils.getNoResultMessage());
                            } else {
                                CommonUtils.successNotification(commonUtils.getSearchComplete());
                            }

                            resultTable.markAsDirtyRecursive();

                        } catch (Exception exception) {
                            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, commonUtils.getErrorHeaderMessage(), commonUtils.getSearchErrorMessage(), new MessageBoxListener() {
                                /**
                                 * The method is triggered when a button of the
                                 * message box is pressed .
                                 *
                                 * @param buttonId The buttonId of the pressed
                                 * button.
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                }
                            }, ButtonId.OK);
                            msg.getButton(ButtonId.OK).focus();
                            LOGGER.error(exception);
                        }
                    }
                }
            });
            /*
             Excel Button configuration
             */
            excelBtn.setIcon(new ThemeResource("../../icons/excel.png"));
            excelBtn.setStyleName("link");
            excelBtn.setDescription("Export to excel");
            excelBtn.setIconAlternateText("Excel export");
            excelBtn.setHtmlContentAllowed(true);
            excelBtn.addClickListener(new Button.ClickListener() {

                public void buttonClick(Button.ClickEvent event) {
                    try {
                        excelExportLogic();
                    } catch (FieldGroup.CommitException commit) {
                        LOGGER.error(commit);
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            });
            /*
             Created By Users lookup Configuration 
             */
            createdBy.setImmediate(true);
            createdBy.setStyleName("searchicon");
            createdBy.setValidationVisible(true);
            createdBy.setDescription(createdBy.getValue());
            createdBy.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    createdBy.setDescription(createdBy.getValue());
                }
            });

            createdBy.addClickListener(new CustomTextField.ClickListener() {

                /**
                 * Logic for focus event.
                 *
                 * @param event
                 */
                public void click(final CustomTextField.ClickEvent event) {
                    try {
                        if (usersLookUp == null) {
                            usersLookUp = new UsersLookup("Created By");
                            UI.getCurrent().addWindow(usersLookUp);
                        }
                        usersLookUp.addCloseListener(new Window.CloseListener() {
                            /**
                             * window close
                             */
                            public void windowClose(final Window.CloseEvent e) {
                                String name = usersLookUp.getIdValue() == null ? StringUtils.EMPTY : (usersLookUp.getIdValue().getLastName() + " " + usersLookUp.getIdValue().getFirstName());
                                createdBy.setValue(name);
                                if (usersLookUp.getIdValue() == null) {
                                    inboxDashboardDTO.setCreatedById("0");
                                } else {
                                    inboxDashboardDTO.setCreatedById(String.valueOf(usersLookUp.getIdValue().getUserId()));
                                }
                                usersLookUp = null;
                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            /*
             Approved By Users Lookup Configuration
             */
            approvedBy.setImmediate(true);
            approvedBy.setStyleName("searchicon");
            approvedBy.setValidationVisible(true);
            approvedBy.setDescription(approvedBy.getValue());
            approvedBy.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    approvedBy.setDescription(approvedBy.getValue());
                }
            });

            approvedBy.addClickListener(new CustomTextField.ClickListener() {

                /**
                 * Logic for focus event.
                 *
                 * @param event
                 */
                public void click(final CustomTextField.ClickEvent event) {
                    try {
                        if (usersLookUp == null) {
                            usersLookUp = new UsersLookup("Approved By");
                            UI.getCurrent().addWindow(usersLookUp);
                        }

                        usersLookUp.addCloseListener(new Window.CloseListener() {
                            /**
                             * window Close Listener
                             */
                            @Override
                            public void windowClose(final Window.CloseEvent e) {
                                String name = usersLookUp.getIdValue() == null ? StringUtils.EMPTY : (usersLookUp.getIdValue().getLastName() + " " + usersLookUp.getIdValue().getFirstName());
                                if (usersLookUp.getIdValue() == null) {
                                    inboxDashboardDTO.setApprovedById("0");
                                } else {
                                    inboxDashboardDTO.setApprovedById(String.valueOf(usersLookUp.getIdValue().getUserId()));
                                }
                                approvedBy.setValue(name);
                                usersLookUp = null;
                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            /*
             Business Process Combobox Loading
             */
            businessProcess.setImmediate(true);
            businessProcess.addItem(selectOne);
            businessProcess.setNullSelectionAllowed(true);
            businessProcess.setNullSelectionItemId(selectOne);

            businessProcess.addItem(WorkflowConstants.getBusinessProcessNameForecast());
            businessProcess.addItem(WorkflowConstants.getBusinessProcessNameAccurals());
            businessProcess.addItem(WorkflowConstants.getBusinessProcessNameContract());
            businessProcess.setValue(selectOne);
            
            for(Object value:businessProcess.getItemIds()){
                JavaScript.getCurrent().execute("localStorage.setItem('"+String.valueOf(value)+"', 'false');");
            }

            deductionLevel.setImmediate(true);
            deductionLevel.addItem(selectOne);
            deductionLevel.setNullSelectionAllowed(true);
            deductionLevel.setNullSelectionItemId(selectOne);

            deductionLevel.addItem(CommonUtils.DEDUCTION_CATEGORY);
            deductionLevel.addItem(CommonUtils.DEDUCTION_PROGRAM_TYPE);
            deductionLevel.addItem(CommonUtils.DEDUCTION_TYPE);
            deductionLevel.setValue(selectOne);
            commonUtils.loadComboBox(deductionValue, StringUtils.EMPTY, false);
            commonUtils.loadComboBox(contractType, CommonUtils.CONTRACT_TYPE, false);

        } catch (PortalException pe) {
            LOGGER.error(pe);
        } catch (SystemException se) {
            LOGGER.error(se);
        } catch (Exception e) {
            LOGGER.error(e);
        }

        publicView.addClickListener(new CustomTextField.ClickListener() {

            public void click(CustomTextField.ClickEvent event) {
                ispublicView = true;
                privateView.setValue(StringUtils.EMPTY);
                lookUp = new ViewSearchLookup(CommonUtils.PUBLIC, publicView);
                UI.getCurrent().addWindow(lookUp);
                lookUp.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        if (publicView.getData() != null) {
                            inboxDashboardDTO = (InboxDashboardDTO) publicView.getData();
                            setDataFromDto(CommonUtils.PUBLIC);
                        }
                    }
                });
            }
        });
        privateView.addClickListener(new CustomTextField.ClickListener() {

            public void click(CustomTextField.ClickEvent event) {
                ispublicView = false;
                publicView.setValue(StringUtils.EMPTY);
                lookUp = new ViewSearchLookup(CommonUtils.PRIVATE, privateView);
                UI.getCurrent().addWindow(lookUp);
                lookUp.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        if (privateView.getData() != null) {
                            inboxDashboardDTO = (InboxDashboardDTO) privateView.getData();
                            setDataFromDto(CommonUtils.PRIVATE);
                        }
                    }
                });
            }
        });

        saveProfile.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                if (businessProcess.getValue() == null) {
                    NotificationUtils.getErrorNotification(commonUtils.getErrorHeaderMessage(), commonUtils.getSearchMessageBusinessProcessError());

                } else if (inboxDashboardDTO.getWorkflowInboxCreatorId() != 0 && !userId.equals(String.valueOf(inboxDashboardDTO.getWorkflowInboxCreatorId()))) {
                    NotificationUtils.getErrorNotification(commonUtils.getErrorHeaderMessage(), "You dont have permission to update the existing view. \nKindly reset and create a new view");

                } else {
                    setDataToDto();
                    saveLookup = new SaveView(inboxDashboardDTO, true);
                    UI.getCurrent().addWindow(saveLookup);
                }
            }
        });

        deductionLevel.addValueChangeListener(new CustomComboBox.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                try {
                    String deductionLevelValue = StringUtils.EMPTY;
                    if (CommonUtils.DEDUCTION_CATEGORY.equalsIgnoreCase(String.valueOf(deductionLevel.getValue()))) {
                        deductionLevelValue = CommonUtils.RS_CATEGORY;
                    } else if (CommonUtils.DEDUCTION_PROGRAM_TYPE.equalsIgnoreCase(String.valueOf(deductionLevel.getValue()))) {
                        deductionLevelValue = CommonUtils.REBATE_PROGRAM_TYPE;
                    } else if (CommonUtils.DEDUCTION_TYPE.equalsIgnoreCase(String.valueOf(deductionLevel.getValue()))) {
                        deductionLevelValue = CommonUtils.RS_TYPE;
                    }
                    commonUtils.loadComboBox(deductionValue, deductionLevelValue, false);
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        });

        detailSearch.setVisible(false);
        for (int i = 0; i < cssLayout1.getComponentCount(); i++) {
            cssLayout1.getComponent(i).setVisible(false);
        }
        businessProcess.addValueChangeListener(new CustomComboBox.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                try {
                    if ("Forecasting".equalsIgnoreCase(String.valueOf(businessProcess.getValue()))
                            || "Accrual Rate Projection".equalsIgnoreCase(String.valueOf(businessProcess.getValue()))) {
                        detailSearch.setVisible(true);
                        for (int i = 0; i < cssLayout1.getComponentCount(); i++) {
                            cssLayout1.getComponent(i).setVisible(true);
                        }
                    } else {
                        detailSearch.setVisible(false);
                        for (int i = 0; i < cssLayout1.getComponentCount(); i++) {
                            cssLayout1.getComponent(i).setVisible(false);
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        });
        privateView.setImmediate(true);
        publicView.setImmediate(true);
        privateView.setStyleName("searchicon");
        publicView.setStyleName("searchicon");
    }

    /**
     * For excel export
     *
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    protected void excelExportLogic() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.info("Ending excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering createWorkSheet");
        try {
            CsvExportforPagedTable.createWorkSheet(resultTable.getColumnHeaders(), resultTable.getVisibleColumns(), tableLogic, "WorkFlow_Details");
            LOGGER.info("Ending createWorkSheet");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Logic for creating worksheet content.
     *
     * @param start the start
     * @param end the end
     * @param printWriter the print writer
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        InboxDashboardDTO dto;
        final List<InboxDashboardDTO> searchList = logic.getWorkflowSearchResults(inboxDashboardDTO, start, end, tableLogic.getSortByColumns(), tableLogic.getFilters(), false);
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantUtils.DATEFORMATM_MMDDYY);
        String date;
        for (int rowCount = 0; rowCount < searchList.size(); rowCount++) {

            dto = searchList.get(rowCount);

            printWriter.print(ConstantsUtils.QUOTE + dto.getWorkflowId() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getWorkflowName() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getCreatedBy() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getApprovedBy() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
            printWriter.print(ConstantsUtils.QUOTE + dto.getModifiedBy() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
            String creationDate = dto.getCreationDate() == null ? (StringUtils.EMPTY + ConstantsUtils.COMMA) : (dateFormat.format(dto.getCreationDate()) + ConstantsUtils.COMMA);
            printWriter.print(creationDate);
            printWriter.println(ConstantsUtils.QUOTE + dto.getWorkFlowStatus() + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
        }
    }
    /*
     * Reset button logic
     */

    private void resetButtonClickLogic() {
        publicView.setValue(StringUtils.EMPTY);
        privateView.setValue(StringUtils.EMPTY);
        clearTableDto();
        workflowId.setValue(StringUtils.EMPTY);
        workflowName.setValue(StringUtils.EMPTY);
        workflowId.setValue(StringUtils.EMPTY);
        workflowDescription.setValue(StringUtils.EMPTY);
        businessProcess.setValue(getSelectOne());
        createdBy.setValue(StringUtils.EMPTY);
        approvedBy.setValue(StringUtils.EMPTY);
        creationDateRangeFrom.setValue(null);
        creationDateRangeTo.setValue(null);
        approvedDateRangeFrom.setValue(null);
        approvedDateRangeTo.setValue(null);
        tableLogic.clearAll();
        resultTable.setContainerDataSource(inboxDashboardBean);
        resultTable.setVisibleColumns(CommonUtils.INBOX_DASHBOARD_COLUMNS);
        resultTable.setColumnHeaders(CommonUtils.INBOX_DASHBOARD_HEADER);

        cssLayoutBottom.getComponent(1).setEnabled(false);
        cssLayoutBottom.getComponent(2).setEnabled(false);
    }

    /*
     * Used for building the url for navigating to other modules
     */
    private String buildUrlForPortlet() {
        String groupFriendlyUrl = null;
        String[] urlSplit = null;
        String pageUrl = (String) VaadinSession.getCurrent().getAttribute("pageUrl");
        String[] url = pageUrl.split(":");
        urlSplit = url[1].split("/");

        try {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            User user = logic.getUserInfo(Long.parseLong(userId));
            if (user != null) {
                List<Organization> orgs = OrganizationLocalServiceUtil
                        .getUserOrganizations(user.getUserId());
                if (orgs != null && !orgs.isEmpty()) {
                    Organization org = orgs.get(0);
                    List<Group> groups = GroupLocalServiceUtil
                            .getCompanyGroups(user.getCompanyId(), -1, -1);
                    if (groups != null && !groups.isEmpty()) {
                        for (Group group : groups) {
                            if (group.getName().equals(
                                    Long.toString(org.getOrganizationId()))) {
                                groupFriendlyUrl = group.getFriendlyURL();
                                break;
                            }
                        }
                    }
                }

            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        if (groupFriendlyUrl == null) {
            groupFriendlyUrl = "/guest";
        }

        return (groupFriendlyUrl != null && pageUrl != null) ? ("http:" + urlSplit[0]) : null;
    }

    private void addResposivenessToInformationPanel(final Map<String, AppPermission> fieldRsHM) throws Exception {
        LOGGER.info("Entering configurePermission");
        try {
            String mode = "search";
            List<Object> resultList = logic.getFieldsForSecurity(CommonUtils.WORKFLOW_INBOX, "LandingScreen");
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldRsHM, mode);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayoutBtn, fieldRsHM, mode);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayoutBottom, fieldRsHM, mode);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("Ending configurePermission");
    }

    ClickListener openBtnClickListener = new Button.ClickListener() {

        @Override
        public void buttonClick(ClickEvent event) {
            if (resultTable.getValue() != null) {
                if (!openPermission) {
                    NotificationUtils.getErrorNotification(commonUtils.getPermDenied(), "You dont have permission to edit this projection.");
                }
            } else {
                NotificationUtils.getErrorNotification(commonUtils.getErrorHeaderMessage(), commonUtils.getProjSelectOpen());
            }
        }
    };

    ClickListener viewBtnClickListener = new Button.ClickListener() {

        @Override
        public void buttonClick(ClickEvent event) {
            if (resultTable.getValue() != null) {
                if (!viewPermission) {
                    NotificationUtils.getErrorNotification(commonUtils.getPermDenied(), "You dont have permission to view this projection.");
                }
            } else {
                NotificationUtils.getErrorNotification(commonUtils.getErrorHeaderMessage(), commonUtils.getProjSelectView());
            }
        }
    };

    @UiHandler("history")
    public void historyLookupLogic(Button.ClickEvent event) {
        if (resultTable.getValue() != null) {
            HelperListUtil helperListUtil = HelperListUtil.getInstance();
            helperListUtil.loadValuesWithListName("workflowinbox");
            WorkFlowHistoryLookup wfLookup = new WorkFlowHistoryLookup(inboxDashBoardDTOHidden);
            UI.getCurrent().addWindow(wfLookup);
        } else {
            NotificationUtils.getErrorNotification(commonUtils.getErrorHeaderMessage(), commonUtils.getProjSelect());
        }
    }

    private void setDataFromDto(String lookupName) {
        if (lookupName.equals(CommonUtils.PRIVATE)) {
            privateView.setValue(inboxDashboardDTO.getViewName());
        } else if (lookupName.equals(CommonUtils.PUBLIC)) {
            publicView.setValue(inboxDashboardDTO.getViewName());
        }
        workflowName.setValue(inboxDashboardDTO.getWorkflowName());
        workflowDescription.setValue(inboxDashboardDTO.getWorkflowDescription());
        businessProcess.setValue(inboxDashboardDTO.getBusinessProcess());
        workflowId.setValue(inboxDashboardDTO.getWorkflowId());
        createdBy.setValue(inboxDashboardDTO.getCreatedBy());
        approvedBy.setValue(inboxDashboardDTO.getApprovedBy());
        creationDateRangeFrom.setValue(inboxDashboardDTO.getCreationDateRangeFrom());
        creationDateRangeTo.setValue(inboxDashboardDTO.getCreationDateRangeTo());
        approvedDateRangeFrom.setValue(inboxDashboardDTO.getApprovedDateRangeFrom());
        approvedDateRangeTo.setValue(inboxDashboardDTO.getApprovedDateRangeTo());
        contractId.setValue(inboxDashboardDTO.getContractId());
        contractNo.setValue(inboxDashboardDTO.getContractNo());
        contractName.setValue(inboxDashboardDTO.getContractName());
        contractType.setValue(inboxDashboardDTO.getContractType());
        companyId.setValue(inboxDashboardDTO.getCompanyId());
        companyNo.setValue(inboxDashboardDTO.getCompanyNo());
        companyName.setValue(inboxDashboardDTO.getCompanyName());
        itemId.setValue(inboxDashboardDTO.getItemId());
        itemNo.setValue(inboxDashboardDTO.getItemNo());
        itemName.setValue(inboxDashboardDTO.getItemName());
        deductionLevel.setValue(inboxDashboardDTO.getDeductionLevel());
        deductionValue.setValue(inboxDashboardDTO.getDeductionValue());
    }

    private void setDataToDto() {
        inboxDashboardDTO.setWorkflowId(workflowId.getValue());
        inboxDashboardDTO.setWorkflowName(workflowName.getValue());
        inboxDashboardDTO.setWorkflowDescription(workflowDescription.getValue());
        inboxDashboardDTO.setBusinessProcess(String.valueOf(businessProcess.getValue()));
        inboxDashboardDTO.setCreatedBy(createdBy.getValue());
        inboxDashboardDTO.setApprovedBy(approvedBy.getValue());
        inboxDashboardDTO.setCreationDateRangeFrom(creationDateRangeFrom.getValue());
        inboxDashboardDTO.setCreationDateRangeTo(creationDateRangeTo.getValue());
        inboxDashboardDTO.setApprovedDateRangeFrom(approvedDateRangeFrom.getValue());
        inboxDashboardDTO.setApprovedDateRangeTo(approvedDateRangeTo.getValue());
        inboxDashboardDTO.setContractId(contractId.getValue());
        inboxDashboardDTO.setContractNo(contractNo.getValue());
        inboxDashboardDTO.setContractName(contractName.getValue());
        inboxDashboardDTO.setContractType(contractType.getValue() != null ? (HelperDTO) contractType.getValue() : null);
        inboxDashboardDTO.setCompanyId(companyId.getValue());
        inboxDashboardDTO.setCompanyNo(companyNo.getValue());
        inboxDashboardDTO.setCompanyName(companyName.getValue());
        inboxDashboardDTO.setItemId(itemId.getValue());
        inboxDashboardDTO.setItemNo(itemNo.getValue());
        inboxDashboardDTO.setItemName(itemName.getValue());
        inboxDashboardDTO.setDeductionLevel(String.valueOf(deductionLevel.getValue()));
        inboxDashboardDTO.setDeductionValue(deductionValue.getValue() != null ? (HelperDTO) deductionValue.getValue() : null);
    }

    private void clearTableDto() {
        inboxDashboardDTO.setWorkflowId(StringUtils.EMPTY);
        inboxDashboardDTO.setWorkflowName(StringUtils.EMPTY);
        inboxDashboardDTO.setWorkflowDescription(StringUtils.EMPTY);
        inboxDashboardDTO.setBusinessProcess(StringUtils.EMPTY);
        inboxDashboardDTO.setStatus(null);
        inboxDashboardDTO.setCreatedById("0");
        inboxDashboardDTO.setApprovedById("0");
        inboxDashboardDTO.setCreatedBy(StringUtils.EMPTY);
        inboxDashboardDTO.setApprovedBy(StringUtils.EMPTY);
        inboxDashboardDTO.setCreationDateRangeFrom(null);
        inboxDashboardDTO.setCreationDateRangeTo(null);
        inboxDashboardDTO.setApprovedDateRangeFrom(null);
        inboxDashboardDTO.setApprovedDateRangeTo(null);
        inboxDashboardDTO.setContractId(StringUtils.EMPTY);
        inboxDashboardDTO.setContractNo(StringUtils.EMPTY);
        inboxDashboardDTO.setContractName(StringUtils.EMPTY);
        inboxDashboardDTO.setContractType(null);
        inboxDashboardDTO.setCompanyId(StringUtils.EMPTY);
        inboxDashboardDTO.setCompanyNo(StringUtils.EMPTY);
        inboxDashboardDTO.setCompanyName(StringUtils.EMPTY);
        inboxDashboardDTO.setItemId(StringUtils.EMPTY);
        inboxDashboardDTO.setItemNo(StringUtils.EMPTY);
        inboxDashboardDTO.setItemName(StringUtils.EMPTY);
        inboxDashboardDTO.setDeductionLevel(StringUtils.EMPTY);
        inboxDashboardDTO.setDeductionValue(null);
        inboxDashboardDTO.setWorkflowInboxSystemId(0);
    }
}
