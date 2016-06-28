/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.cff.dto.ApprovalDetailsDTO;
import com.stpl.app.cff.dto.CFFDTO;
import com.stpl.app.cff.dto.CFFResultsDTO;
import com.stpl.app.cff.dto.CFFSearchDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.ui.ConsolidatedFinancialForecastUI;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.ResponsiveUtils;
import com.stpl.app.cff.util.TableHeaderUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorDisplay;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.ExcelExportUtil;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.Group;
import com.stpl.portal.model.Organization;
import com.stpl.portal.model.User;
import com.stpl.portal.service.GroupLocalServiceUtil;
import com.stpl.portal.service.OrganizationLocalServiceUtil;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author shrihariharan
 */
public class ApprovalTab extends CustomComponent {

    private static final String createWorkSheetContent = "createWorkSheetContent";
    private static final String TMP = "tmp";
    private static final String SESSION_ID = "sessionId";
    public BrowserWindowOpener opener;
    String projectionIdHidden = "";
    /**
     * Logger implementation for CffApprovalDetailsForm
     */
    private static final Logger LOGGER = Logger.getLogger(ApprovalTab.class);
    /**
     * Close Button
     */
    @UiField("closeBtn")
    private Button closeBtn;
    /**
     * Approve Button
     */
    @UiField("approveBtn")
    private Button approveBtn;
    /**
     * Reject Button
     */
    @UiField("rejectBtn")
    private Button rejectBtn;
    /**
     * Delete Button
     */
    @UiField("deleteBtn")
    private Button deleteBtn;
    /**
     * Cancel Button
     */
    @UiField("cancelBtn")
    private Button cancelBtn;
    /**
     * Submit Button
     */
    @UiField("submitBtn")
    private Button submitBtn;
    /**
     * Reset Button
     */
    @UiField("resetBtn")
    private Button resetBtn;
    /**
     * The latestEstimate.
     */
    @UiField("latestEstimate")
    private ComboBox latestEstimate;
    /**
     * The updateCycle.
     */
    @UiField("updateCycle")
    public ComboBox updateCycle;
    /**
     * The latestEstimateName
     */
    @UiField("latestEstimateName")
    public TextField latestEstimateName;
    /**
     * The updateCycleName
     */
    @UiField("updateCycleName")
    public TextField updateCycleName;
    @UiField("status")
    public TextField status;
    /**
     * Approval Layout
     */
    @UiField("approvalLayout")
    private VerticalLayout approvalLayout;
    /**
     * Approval details Table
     */
    private ExtPagedFilterTable approvalDetailsTable;
    /**
     * Pagination Layout for Approval Tables
     */
    private HorizontalLayout approvalPagination;
    /**
     * Pagination Layout for Result Tables
     */
    private HorizontalLayout resultPagination;
    /**
     * Result Layout
     */
    @UiField("resultLayout")
    private VerticalLayout resultLayout;
    /**
     * Result Table
     */
    private ExtPagedFilterTable resultTable;
    private ExtPagedFilterTable excelTable = new ExtPagedFilterTable();
    /**
     * Approval Table Container
     */
    private BeanItemContainer<ApprovalDetailsDTO> approvalContainer = new BeanItemContainer<ApprovalDetailsDTO>(ApprovalDetailsDTO.class);
    /**
     * The cff binder.
     */
    public CustomFieldGroup cffBinder = new CustomFieldGroup(new BeanItem<ApprovalDetailsDTO>(new ApprovalDetailsDTO()));
    /**
     * The results bean.
     */
    public BeanItemContainer<CFFResultsDTO> resultsBean = new BeanItemContainer<CFFResultsDTO>(
            CFFResultsDTO.class);
    /**
     * The cff dto.
     */
    public CFFDTO cffDTO;
    /**
     * The available ccp projections bean.
     */
    private BeanItemContainer<CFFResultsDTO> availableCCPProjectionsBean = new BeanItemContainer<CFFResultsDTO>(CFFResultsDTO.class);
    /**
     * The projection id.
     */
    public int projectionId;
    /**
     * The workflow id.
     */
    public String workflowId;
    /**
     * The workflow system id.
     */
    public int workflowSystemId;
    /**
     * The url.
     */
//    public String url = buildUrlForPortlet("");
    /**
     * The commonUtils.
     */
    private final CommonUtils commonUtils = new CommonUtils();
    CFFResultsDTO cffResultsDTO = new CFFResultsDTO();
    CFFLogic cffLogic = new CFFLogic();
    private NotesTabForm notestab;
    @UiField("excelExport")
    Button excelExport;
    @UiField("bottombuttonLayout")
    private HorizontalLayout bottombuttonLayout;
    boolean isFirst = false;
    
    @UiField("errorLabel")
    public ErrorLabel errorLabel;

    public boolean isIsFirst() {
        return isFirst;
    }

    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }
    private static final String EXCEL_HEADER = "Approval Details";
    /* Instantiates a new approval details.
     *
     * @param approvalDetailsBean the approval details bean
     * @param resultsBean the results bean
     * @param cffBinder the cff binder
     * @param cffDTO the cff dto
     */
    private BeanItemContainer<CFFResultsDTO> resultTableContainer = new BeanItemContainer<CFFResultsDTO>(CFFResultsDTO.class);
    /**
     * ApprovalTab form constructor
     */
    CFFSearchDTO dto;
    SessionDTO sessionDTO;

    public ApprovalTab(CFFSearchDTO dto, BeanItemContainer<ApprovalDetailsDTO> approvalContainer, BeanItemContainer<CFFResultsDTO> resultsBean,
            NotesTabForm notestab, SessionDTO sessionDTO) {
        LOGGER.info("Enters ApprovalTab Constructor");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/cff/tabs/ApprovalTab.xml"), this));
        bottombuttonLayout.setVisible(false);
        this.dto = dto;
        this.approvalContainer = approvalContainer;
        this.resultsBean = resultsBean;
        this.notestab = notestab;
        this.sessionDTO = sessionDTO;

        configureFields();
        LOGGER.info("Exits ApprovalTab Constructor");
    }

    /**
     * To set additional properties for the UI components
     */
    private void configureFields() {
        LOGGER.info("Enters approval Tab Configure Fields");
        try {
            approveBtnLogic();
            closeBtnLogic();

            excelTable = new ExtPagedFilterTable();
            excelTable.setContainerDataSource(resultsBean);
            excelTable.setVisibleColumns(TableHeaderUtils.APPROVED_RESULT_TABLE_VISIBLE_COLUMN);
            excelTable.setColumnHeaders(TableHeaderUtils.APPROVED_RESULT_TABLE_HEADER);
            excelTable.setColumnAlignment(TableHeaderUtils.APPROVED_RESULT_TABLE_VISIBLE_COLUMN[2], ExtCustomTable.Align.CENTER);
            excelTable.setColumnAlignment(TableHeaderUtils.APPROVED_RESULT_TABLE_VISIBLE_COLUMN[4], ExtCustomTable.Align.CENTER);
            excelTable.setFilterBarVisible(true);
            excelTable.setSizeFull();
            excelTable.setImmediate(true);
            excelTable.setPageLength(5);
            excelTable.setHeight("270px");
            excelTable.setFilterDecorator(new ExtDemoFilterDecorator());
            excelTable.addStyleName(Constants.FILTER_TABLE);
            excelTable.setVisible(false);

            latestEstimateName.setImmediate(true);
            latestEstimateName.addValidator(new StringLengthValidator("Latest Estimate name should be less than 100 characters", 0, 100, true));
            latestEstimateName.addValidator(new RegexpValidator(ConstantsUtil.alphaNumericChars, "Latest Estimate name should be alphanumeric"));

            updateCycleName.setImmediate(true);
            updateCycleName.addValidator(new StringLengthValidator("Update Cycle name should be less than 100 characters", 0, 100, true));
            updateCycleName.addValidator(new RegexpValidator(ConstantsUtil.alphaNumericChars, "Update Cycle name should be alphanumeric"));

            latestEstimate.addItem(ConstantsUtil.SELECT_ONE);
            try {
                latestEstimate = commonUtils.getNativeSelect(latestEstimate, CFFLogic.getDropDownList(ConstantsUtil.LOCKED_STATUS), StringUtils.EMPTY);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            latestEstimate.setImmediate(true);
            latestEstimate.setNullSelectionAllowed(true);
            latestEstimate.select(ConstantsUtil.SELECT_ONE);
            latestEstimate.setNullSelectionItemId(ConstantsUtil.SELECT_ONE);
            latestEstimate.setPageLength(4);

            latestEstimate.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if ("NO".equals(String.valueOf(latestEstimate.getValue()))) {
                        latestEstimateName.setEnabled(false);
                    } else {
                        latestEstimateName.setEnabled(true);
                    }
                }
            });

            updateCycle.addItem(ConstantsUtil.SELECT_ONE);
            try {
                updateCycle = commonUtils.getNativeSelect(updateCycle, CFFLogic.getDropDownList(ConstantsUtil.LOCKED_STATUS), StringUtils.EMPTY);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            updateCycle.setImmediate(true);
            updateCycle.setNullSelectionAllowed(true);
            updateCycle.select(ConstantsUtil.SELECT_ONE);
            updateCycle.setNullSelectionItemId(ConstantsUtil.SELECT_ONE);
            updateCycle.setPageLength(4);

            updateCycle.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if ("NO".equals(String.valueOf(updateCycle.getValue()))) {
                        updateCycleName.setEnabled(false);
                    } else {
                        updateCycleName.setEnabled(true);
                    }
                }
            });

            if (dto.getTypeDesc() != null) {

                if ("Latest Estimate".equals(dto.getTypeDesc())) {
                    latestEstimate = commonUtils.getNativeSelect(latestEstimate, CFFLogic.getDropDownList(ConstantsUtil.LOCKED_STATUS), ConstantsUtils.YES);
                    latestEstimateName.setValue(dto.getFinancialForecastName());

                } else if ("Update Cycle".equals(dto.getTypeDesc())) {
                    updateCycle = commonUtils.getNativeSelect(updateCycle, CFFLogic.getDropDownList(ConstantsUtil.LOCKED_STATUS), ConstantsUtils.YES);
                    updateCycleName.setValue(dto.getFinancialForecastName());
                } else if ("Both".equals(dto.getTypeDesc())) {
                    latestEstimate = commonUtils.getNativeSelect(latestEstimate, CFFLogic.getDropDownList(ConstantsUtil.LOCKED_STATUS), ConstantsUtils.YES);
                    updateCycle = commonUtils.getNativeSelect(updateCycle, CFFLogic.getDropDownList(ConstantsUtil.LOCKED_STATUS), ConstantsUtils.YES);
                    if (dto.getFinancialForecastName() != null) {
                        String[] name = dto.getFinancialForecastName().split("-");
                        latestEstimateName.setValue(name[0]);
                        updateCycleName.setValue(name[1]);
                    }

                }
            }
            if (dto.getStatusDesc() != null && !StringUtils.EMPTY.equals(dto.getStatusDesc())) {
                status.setValue(dto.getStatusDesc());

                if (Constants.APPROVED.equals(dto.getStatusDesc())) {

                    latestEstimate.setReadOnly(true);
                    updateCycle.setReadOnly(true);
                    latestEstimateName.setReadOnly(true);
                    updateCycleName.setReadOnly(true);

                    approveBtn.setEnabled(false);
                    rejectBtn.setEnabled(false);
                    deleteBtn.setEnabled(false);
                    cancelBtn.setEnabled(false);
                    submitBtn.setEnabled(false);
                    resetBtn.setEnabled(false);
                } else if ("Pending".equals(dto.getStatusDesc())) {
                    latestEstimate.setReadOnly(true);
                    updateCycle.setReadOnly(true);
                    latestEstimateName.setReadOnly(true);
                    updateCycleName.setReadOnly(true);

                    approveBtn.setEnabled(true);
                    rejectBtn.setEnabled(true);
                    deleteBtn.setEnabled(true);
                    cancelBtn.setEnabled(true);
                    submitBtn.setEnabled(false);
                    resetBtn.setEnabled(false);
                } else if (Constants.REJECTED.equals(dto.getStatusDesc())) {
                    latestEstimate.setReadOnly(true);
                    updateCycle.setReadOnly(true);
                    latestEstimateName.setReadOnly(true);
                    updateCycleName.setReadOnly(true);

                    approveBtn.setEnabled(false);
                    rejectBtn.setEnabled(false);
                    deleteBtn.setEnabled(true);
                    cancelBtn.setEnabled(true);
                    submitBtn.setEnabled(true);
                    resetBtn.setEnabled(false);
                } else if ("Cancelled".equals(dto.getStatusDesc())) {
                    latestEstimate.setReadOnly(true);
                    updateCycle.setReadOnly(true);
                    latestEstimateName.setReadOnly(true);
                    updateCycleName.setReadOnly(true);

                    approveBtn.setEnabled(false);
                    rejectBtn.setEnabled(false);
                    deleteBtn.setEnabled(false);
                    cancelBtn.setEnabled(false);
                    submitBtn.setEnabled(false);
                    resetBtn.setEnabled(false);
                }

            } else {
                status.setValue(StringUtils.EMPTY);

                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                deleteBtn.setEnabled(false);
                cancelBtn.setEnabled(false);
                submitBtn.setEnabled(true);

            }

            status.setReadOnly(true);
            approvalDetailsTable = new ExtPagedFilterTable();
            approvalDetailsTable.setContainerDataSource(approvalContainer);
            approvalDetailsTable.setVisibleColumns(TableHeaderUtils.APPROVAL_TABLE_VISIBLE_COLUMN);
            approvalDetailsTable.setColumnHeaders(TableHeaderUtils.APPROVAL_TABLE_HEADER);
            approvalDetailsTable.setColumnAlignment(TableHeaderUtils.APPROVAL_TABLE_VISIBLE_COLUMN[1], ExtCustomTable.Align.CENTER);
            approvalDetailsTable.setFilterBarVisible(true);
            approvalDetailsTable.setSizeFull();
            approvalDetailsTable.setImmediate(true);
            approvalDetailsTable.setPageLength(4);
            approvalDetailsTable.setHeight("230px");
            approvalDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            approvalDetailsTable.addStyleName(Constants.FILTER_TABLE);
            approvalDetailsTable.setConverter("approvedDate", new StringToDateConverter() {
                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MM/dd/YYYY, HH/mm/ss");
                }
            });
            if ((status.getValue().equalsIgnoreCase(Constants.PENDING)) || (status.getValue().equalsIgnoreCase(Constants.CANCELLED))
                    || (status.getValue().equalsIgnoreCase(Constants.REJECTED))) {
                approvalDetailsTable.removeAllItems();
            }
            approvalPagination = ResponsiveUtils.getResponsiveControls(approvalDetailsTable.createControls());
            approvalLayout.addComponent(approvalDetailsTable);
            approvalLayout.addComponent(approvalPagination);

            resultTable = new ExtPagedFilterTable();
            resultTable.setContainerDataSource(resultsBean);  
            resultTable.setVisibleColumns(TableHeaderUtils.APPROVED_RESULT_TABLE_VISIBLE_COLUMN);
            resultTable.setColumnHeaders(TableHeaderUtils.APPROVED_RESULT_TABLE_HEADER);
            resultTable.setColumnAlignment(TableHeaderUtils.APPROVED_RESULT_TABLE_VISIBLE_COLUMN[2], ExtCustomTable.Align.CENTER);
            resultTable.setColumnAlignment(TableHeaderUtils.APPROVED_RESULT_TABLE_VISIBLE_COLUMN[4], ExtCustomTable.Align.CENTER);
            resultTable.setFilterBarVisible(true);
            resultTable.setSizeFull();
            resultTable.setImmediate(true);
            resultTable.setPageLength(5);
            resultTable.setHeight("270px");
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.addStyleName(Constants.FILTER_TABLE);
            resultTable.setConverter("createdDate", new StringToDateConverter() {
                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MM/dd/YYYY");
                }
            });
            resultTable.setConverter("approvalDate", new StringToDateConverter() {
                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MM/dd/YYYY");
                }
            });
            loadResultTable();
            resultTable.addGeneratedColumn("workflowId", new ExtFilterTreeTable.ColumnGenerator() {
                @Override
                public Object generateCell(ExtCustomTable source, Object itemId, Object columnId) {
                    final CFFResultsDTO dto = (CFFResultsDTO) itemId;
                    final Button btn = new Button(dto.getWorkflowId());
                    btn.setStyleName(Reindeer.BUTTON_LINK);
                    btn.setImmediate(true);
                    btn.setData(dto.getWorkflowId());
                    navigationForNM(btn, dto);
                    btn.addClickListener(new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        public void buttonClick(final Button.ClickEvent event) {
                            //  navigationForNM(btn, dto.getProjectionMasterSid(),dto.getProjectionType());
                            navigationForNM(btn, dto);
                        }
                    });
                    return btn;
                }
            });        
            resultPagination = ResponsiveUtils.getResponsiveControls(resultTable.createControls());
            resultLayout.addComponent(resultTable);
            resultLayout.addComponent(excelTable);
            resultLayout.addComponent(resultPagination);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        disableFieldsOnview();

        excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExport.setStyleName("link");
        excelExport.setDescription("Export to excel");
        excelExport.setIconAlternateText("Excel export");
        excelExport.setHtmlContentAllowed(true);
        excelExport.addClickListener(new Button.ClickListener() {
            /**
             * calls excelExportLogic method on button click
             *
             * @param event - Mouse Click event
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.info("Entering EXCEL Export Button Click");
                    ConsolidatedFinancialForecastUI.EXCEL_CLOSE=true;
                    //  excelExportLogic();
                    final ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(excelTable), EXCEL_HEADER, EXCEL_HEADER, "ApprovalDetails.xls", false);
                    excel.export();
                    LOGGER.info(" Ends  EXCEL Export Button Click");

                } catch (Exception exception) {
                    LOGGER.error(exception.getMessage());
                }
            }
        });
        LOGGER.info("Exits approval Tab Configure Fields");
    }

    public void loadResultTable() {

        cffDTO = new CFFDTO();
        resultsBean.removeAllItems();
        resultsBean.addAll(cffLogic.loadLatestCCP(sessionDTO));
        resultTable.firePagedChangedEvent();        
    }

    /**
     * Navigation for nm.
     *
     * @param button the button
     * @param projectionId the projection id
     * @param projectionType
     */
    protected void navigationForNM(final Button button, CFFResultsDTO dto) {
        String workflowMasterSysId = null;
        String workflowStatus = null;
        String canApprove = "yes";
        String workflowId = null;
        if (dto != null) {

            int projectionSysId = dto.getProjectionMasterSid();
            workflowStatus = dto.getWorkflowStatus();
            workflowMasterSysId = String.valueOf(dto.getWorkflowMasterSystemID());
            workflowId = dto.getWorkflowId();
            projectionIdHidden = String.valueOf(projectionSysId);
            String furl = StringUtils.EMPTY;
            boolean isMandated = false;
            if (Page.getCurrent().getLocation().getPort() == 8080 || Page.getCurrent().getLocation().getPort() == 8081) {
                if (workflowId.startsWith("CF")) {
                    furl = "http://" + Page.getCurrent().getLocation().getHost() + ":" + Page.getCurrent().getLocation().getPort() + "/web/guest/commercial?nmFlow='true'";
                } else if (workflowId.startsWith("GF")) {
                    isMandated = true;
                    furl = "http://" + Page.getCurrent().getLocation().getHost() + ":" + Page.getCurrent().getLocation().getPort() + "/web/guest/government?mFlow='true'";
                }
            } else {
                if (workflowId.startsWith("CF")) {
                    furl = "https://" + Page.getCurrent().getLocation().getHost() + "/web/guest/commercial?nmFlow='true'";
                } else if (workflowId.startsWith("GF")) {
                    isMandated = true;
                    furl = "https://" + Page.getCurrent().getLocation().getHost() + "/web/guest/government?nmFlow='true'";
                }
            }
            LOGGER.info("Redirecting to URL :" + furl);
            opener = new BrowserWindowOpener(furl);
            opener.setFeatures("height=800,width=1000,resizable,scrollbars=1");
            opener.setFeatures("toolbar=no,scrollbars=1,location=no");

            opener.setWindowName("Forecast Window");
            opener.setParameter("projectionIdFromWorkflow", projectionIdHidden);
            opener.setParameter("workflowId", workflowMasterSysId);
            opener.setParameter("workflowStatus", workflowStatus);
            opener.setParameter("canApprove", canApprove);
            opener.setParameter("noOfApprovals", "1");
            opener.setParameter("approvalLevel", "1");

            if (isMandated) {
                opener.setParameter("portletName", "Government");
            } else {
                opener.setParameter("portletName", "Commercial");
            }
            opener.extend(button);
        } else {
            projectionIdHidden = null;

        }

    }

    /**
     * Builds the url for portlet.
     *
     * @param layoutFriendlyName the layout friendly name
     * @return the string
     */
    private String buildUrlForPortlet(String layoutFriendlyName) {
        String groupFriendlyUrl = null;
        final String serverUrl = Page.getCurrent().getLocation().toString();
        final String[] url = serverUrl.split(":");
        final String[] portSplit = url[1].split("/");
        layoutFriendlyName = "/mandated";
        try {
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final User user = CommonUtils.getUserInfo(Long.parseLong(userId));
            if (user != null) {
                final List<Organization> orgs = OrganizationLocalServiceUtil
                        .getUserOrganizations(user.getUserId());
                if (orgs != null && !orgs.isEmpty()) {
                    final Organization org = orgs.get(0);
                    final List<Group> groups = GroupLocalServiceUtil
                            .getCompanyGroups(user.getCompanyId(), -1, -1);
                    if (groups != null && !groups.isEmpty()) {
                        for (final Group group : groups) {
                            if (group.getName().equals(
                                    Long.toString(org.getOrganizationId()))) {
                                groupFriendlyUrl = group.getFriendlyURL();
                                break;
                            }
                        }
                    }
                }
            }

        } catch (Exception e1) {
            LOGGER.error(e1.getMessage());
        }
        if (groupFriendlyUrl == null) {
            groupFriendlyUrl = "/guest";
        }

        return (groupFriendlyUrl == null || serverUrl == null) ? null : ("http:" + portSplit[0]
                + layoutFriendlyName);
    }

    /**
     * Submit button logic
     *
     * @param event
     */
    @UiHandler("submitBtn")
    public void submitBtnLogic(Button.ClickEvent event) {
        LOGGER.info("inside Submit method");

        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                if (isFirst = false) {
                    submitLogic();
                    isFirst = true;
                    setIsFirst(isFirst);
                }
            }
        }.getConfirmationMessage("Submit Confirmation",
                "Are you sure you want to Submit the Forecast Workflow?");

        LOGGER.info("Exits Submit method");

    }

    /**
     * Approve button click event handler
     *
     * @param event
     */
    public void approveBtnLogic() {
        LOGGER.info("Inside Approval Button click event method");

        approveBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                AbstractNotificationUtils notification = new AbstractNotificationUtils() {
                    public void noMethod() {
                        // To change body of generated methods, choose Tools
                        // | Templates.
                    }

                    public void yesMethod() {

                        String result = "";
                        final int cffMasterSystemId = dto.getCffMasterSid();
                        final String userId = sessionDTO.getUserId();

                        result = cffLogic.approveCffInformation(cffMasterSystemId, userId);
                        result = cffLogic.approveCffApproveDetails(userId, cffMasterSystemId, CommonUtils.WORKFLOW_STATUS_APPROVED);

                        if (!result.equals(CommonUtils.FAIL)) {
                            notestab.getApprovalWindow().close();
                            CommonUIUtils.getMessageNotification("Approved Successfully");
                        }
                    }
                };
                notification.getConfirmationMessage("Confirmation", "Are you sure you want to Approve the Forecast Workflow?");

            }
        });

        LOGGER.info("Exits Approval Button click even method");
    }

    /**
     * Reject button click method event handler
     *
     * @param event
     */
    @UiHandler("rejectBtn")
    public void rejectBtnLogic(final Button.ClickEvent event) {
        LOGGER.info("Inside Rejected Button click event method");
        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                String result = "";
                final int cffMasterSystemId = dto.getCffMasterSid();
                final String userId = sessionDTO.getUserId();

                result = cffLogic.approveCffInformation(cffMasterSystemId, userId);

                result = cffLogic.approveCffApproveDetails(userId, cffMasterSystemId, CommonUtils.WORKFLOW_STATUS_REJECTED);

                if (!result.equals(CommonUtils.FAIL)) {
                    notestab.getApprovalWindow().close();
                    CommonUIUtils.getMessageNotification("Rejected Successfully");
                }
            }
        }.getConfirmationMessage("Reject Confirmation",
                "Are you sure you want to Reject the Forecast Workflow?");

        LOGGER.info("Inside Rejected Button click event method");
    }

    /**
     * Reject button click method event handler
     *
     * @param event
     */
    @UiHandler("cancelBtn")
    public void cancelBtnLogic(final Button.ClickEvent event) {
        LOGGER.info("Inside Cancelled Button click event method");
        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                String result = "";
                final int cffMasterSystemId = dto.getCffMasterSid();
                final String userId = sessionDTO.getUserId();

                result = cffLogic.approveCffInformation(cffMasterSystemId, userId);

                result = cffLogic.approveCffApproveDetails(userId, cffMasterSystemId, CommonUtils.WORKFLOW_STATUS_CANCELLED);

                if (!result.equals(CommonUtils.FAIL)) {
                    notestab.getApprovalWindow().close();
                    CommonUIUtils.getMessageNotification("Cancelled Successfully");
                }
            }
        }.getConfirmationMessage("Cancel Confirmation",
                " Are you sure you want to Cancel the Forecast Workflow?");

        LOGGER.info("Inside Cancelled Button click event method");
    }

    @UiHandler("deleteBtn")
    public void deleteBtnLogic(final Button.ClickEvent event) {
        LOGGER.info("Inside delete Button click event method");
        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                String result = StringUtils.EMPTY;
                final int cffMasterSystemId = sessionDTO.getProjectionId();
                cffLogic.deleteCff(cffMasterSystemId, ConstantsUtil.CFF_MASTER);
                cffLogic.deleteCff(cffMasterSystemId, ConstantsUtil.CFF_APPROVE_MASTER);
                cffLogic.deleteCff(cffMasterSystemId, ConstantsUtil.CFF_DETAILS);
                result = cffLogic.deleteCffAdditionalInfo(cffMasterSystemId);

                if (!result.equals(CommonUtils.FAIL)) {
                    notestab.getApprovalWindow().close();
                    CommonUIUtils.getMessageNotification("Deleted Successfully");
                }
            }
        }.getConfirmationMessage("Delete Confirmation",
                "Are you sure you want to Delete the Forecast Workflow?");

        LOGGER.info("Inside delete Button click event method");
    }

    public void closeBtnLogic() {
        LOGGER.info("Inside Close Click Listener");
        closeBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                AbstractNotificationUtils notification = new AbstractNotificationUtils() {
                    public void noMethod() {
                        // To change body of generated methods, choose Tools
                        // | Templates.
                    }

                    public void yesMethod() {

                        notestab.getApprovalWindow().close();
                    }
                };
                notification.getConfirmationMessage("Close Confirmation", "Are you sure you want to close the Consolidated Financial Forecast Approval window");

            }
        });
    }

    public void disableFieldsOnview() {
        String mode = sessionDTO.getAction();
        if ("view".equalsIgnoreCase(mode)) {
            resetBtn.setEnabled(false);
            approveBtn.setEnabled(false);
            rejectBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            cancelBtn.setEnabled(false);
            submitBtn.setEnabled(false);
        }
    }

    @UiHandler("resetBtn")
    public void resetBtnLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                latestEstimate.setValue(ConstantsUtil.SELECT_ONE);
                latestEstimateName.setValue(StringUtils.EMPTY);
                updateCycle.setValue(ConstantsUtil.SELECT_ONE);
                updateCycleName.setValue(StringUtils.EMPTY);
                status.setValue(StringUtils.EMPTY);
            }
        }.getConfirmationMessage("Reset Confirmation",
                "Are you sure you want to reset the Search Criteria group box?");
    }

    public Boolean submitLogic() {
        if (sessionDTO.getAction().equals("edit")) {
            System.out.println("inside edit - - - ");
            cffLogic.getNoOfLevelFromJbpm(sessionDTO, String.valueOf(dto.getCffMasterSid()), sessionDTO.getUserId());
            String noOfLevel = cffLogic.getNoOfLevelFromDB(sessionDTO, String.valueOf(dto.getCffMasterSid()), sessionDTO.getUserId());
            cffLogic.submitCffPendingDetails(sessionDTO.getUserId(), dto.getCffMasterSid(), noOfLevel, Boolean.TRUE);
            CommonUIUtils.getMessageNotification("CFF Re-Submitted Successfully");
            return Boolean.TRUE;
        } else {
            final int cffMasterSystemId = dto.getCffMasterSid();
            final String userId = sessionDTO.getUserId();
            Map<String, Object> valueMap = new HashMap<String, Object>();


            valueMap.put("latestEstimate", latestEstimate.getValue());
            valueMap.put("latestEstimateName", latestEstimateName.getValue());
            valueMap.put("updateCycle", updateCycle.getValue());
            valueMap.put("updateCycleName", updateCycleName.getValue());
            try {
                final String result = cffLogic.saveCffInformation(cffMasterSystemId, userId, valueMap, sessionDTO);
                notestab.saveAdditionalInformation(Integer.valueOf(String.valueOf(VaadinSession.getCurrent().getAttribute("projectionId"))), userId, sessionDTO);
                if (!result.equals(CommonUtils.FAIL)) {
                    CommonUIUtils.getMessageNotification("Submitted Successfully");
                    return Boolean.TRUE;
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return Boolean.FALSE;
    }

    public void getDefaultFocus() {
        latestEstimate.setEnabled(true);
        latestEstimateName.focus();
    }

    protected void excelExportLogic() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering Approval Details excelExportLogic");
        createWorkSheet();
        LOGGER.info("Ending Approval Details excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering Approval Details createWorkSheet");
        final long recordCount = resultsBean.size();
        ExcelExportforBB.createWorkSheet(resultTable.getColumnHeaders(), recordCount, this, getUI(), EXCEL_HEADER);

        LOGGER.info("Ending Approval Details createWorkSheet");
    }

    /**
     * Creates the work sheet content.
     *
     * @param start the start
     * @param end the end
     * @param printWriter the printWriter
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        try {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(ExcelExportUtil.DATE_FORMAT, Locale.getDefault());
            CFFResultsDTO cffResultsDTO;
            DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            final List<OrderByColumn> columns = new ArrayList<OrderByColumn>();
            final List<CFFResultsDTO> reultList = cffLogic.loadLatestCCP(sessionDTO);
            if (resultTable.size() > 0) {
                for (int rowCount = 0; rowCount < reultList.size(); rowCount++) {
                    cffResultsDTO = (CFFResultsDTO) reultList.get(rowCount);
                    printWriter.print(ConstantsUtil.QUOTE + cffResultsDTO.getWorkflowId() + ConstantsUtil.QUOTE + ExcelExportUtil.COMMA);

                    printWriter.print(ConstantsUtil.QUOTE + cffResultsDTO.getProjectionName() + ConstantsUtil.QUOTE + ExcelExportUtil.COMMA);

                    printWriter.print(ConstantsUtil.QUOTE + dateFormat.format(cffResultsDTO.getCreatedDate()) + ConstantsUtil.QUOTE + ExcelExportUtil.COMMA);

                    if (StringUtils.isNotBlank(cffResultsDTO.getCreatedBy())) {
                        printWriter.print(ConstantsUtil.QUOTE + cffResultsDTO.getCreatedBy() + ConstantsUtil.QUOTE + ExcelExportUtil.COMMA);
                    } else {
                        printWriter.print(ConstantsUtil.QUOTE + StringUtils.EMPTY + ConstantsUtil.QUOTE + ExcelExportUtil.COMMA);
                    }

                    if (cffResultsDTO.getApprovalDate() != null) {
                        printWriter.print(ConstantsUtil.QUOTE + dateFormat.format(cffResultsDTO.getApprovalDate()) + ConstantsUtil.QUOTE + ExcelExportUtil.COMMA);
                    } else {
                        printWriter.print(ConstantsUtil.QUOTE + StringUtils.EMPTY + ConstantsUtil.QUOTE + ExcelExportUtil.COMMA);
                    }

                    if (StringUtils.isNotBlank(cffResultsDTO.getApprovedBy())) {
                        printWriter.print(ConstantsUtil.QUOTE + cffResultsDTO.getApprovedBy() + ConstantsUtil.QUOTE + ExcelExportUtil.COMMA);
                    } else {
                        printWriter.print(ConstantsUtil.QUOTE + StringUtils.EMPTY + ConstantsUtil.QUOTE + ExcelExportUtil.COMMA);
                    }

                    if (StringUtils.isNotBlank(cffResultsDTO.getPriorLatestEstimate())) {
                        printWriter.print(ConstantsUtil.QUOTE + cffResultsDTO.getPriorLatestEstimate() + ConstantsUtil.QUOTE + ExcelExportUtil.COMMA);
                    } else {
                        printWriter.print(ConstantsUtil.QUOTE + StringUtils.EMPTY + ConstantsUtil.QUOTE + ExcelExportUtil.COMMA);
                    }

                    if (StringUtils.isNotBlank(cffResultsDTO.getPriorUpdateCycle())) {
                        printWriter.println(ConstantsUtil.QUOTE + cffResultsDTO.getPriorUpdateCycle() + ConstantsUtil.QUOTE);
                    } else {
                        printWriter.println(ConstantsUtil.QUOTE + StringUtils.EMPTY + ConstantsUtil.QUOTE);
                    }

                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }
    
    public boolean submitCheck() {

        if ((StringUtils.isBlank(String.valueOf(latestEstimate.getValue())) || "null".equals(String.valueOf(latestEstimate.getValue()))
                || ConstantsUtil.SELECT_ONE.equals(String.valueOf(latestEstimate.getValue())) || "NO".equals(String.valueOf(latestEstimate.getValue())))
                && (StringUtils.isBlank(String.valueOf(updateCycle.getValue())) || "null".equals(String.valueOf(updateCycle.getValue()))
                || ConstantsUtil.SELECT_ONE.equals(String.valueOf(updateCycle.getValue())) || "NO".equals(String.valueOf(updateCycle.getValue())))) {
            AbstractNotificationUtils.getErrorNotification("Alert", "Latest Estimate or Update Cycle should be selected 'Yes'");
            return Boolean.FALSE;
        }

        if (ConstantsUtils.YES.equals(String.valueOf(latestEstimate.getValue())) && StringUtils.isBlank(String.valueOf(latestEstimateName.getValue()))) {
            AbstractNotificationUtils.getErrorNotification("Alert", "Latest Estimate Name should be Entered");
            return Boolean.FALSE;
        }

            if (ConstantsUtils.YES.equals(String.valueOf(updateCycle.getValue()))
                    && StringUtils.isBlank(String.valueOf(updateCycleName.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("Alert", "Update Cycle Name should be Entered");
                return Boolean.FALSE;
            }
            if (!(String.valueOf(latestEstimateName.getValue()).matches(ConstantsUtil.alphaNumericChars))) {
                cffBinder.setErrorDisplay(errorLabel);
                cffBinder.getErrorDisplay().setError("Latest Estimate name should be alphanumeric");
                return Boolean.FALSE;
            }
            if (!(String.valueOf(updateCycleName.getValue()).matches(ConstantsUtil.alphaNumericChars))) {
                cffBinder.setErrorDisplay(errorLabel);
                cffBinder.getErrorDisplay().setError("Update Cycle name should be alphanumeric");
                return Boolean.FALSE;
        }

        if (resultsBean == null || (resultsBean != null && resultsBean.size() == 0)) {
            AbstractNotificationUtils.getErrorNotification("Alert", "Please populate the approval details before submitting the workflow for approval");
            return false;
        }
        //cffBinder.getErrorDisplay().clearError();
         ErrorDisplay msg =  cffBinder.getErrorDisplay();
     //   System.out.println("msg "+msg);
        if(msg !=null){
            msg.clearError();
        }
        return Boolean.TRUE;
    }
    
    private boolean isRejectedCff() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
