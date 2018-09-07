/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.cff.dao.CommonServiceImpl;
import com.stpl.app.cff.dto.ApprovalDetailsDTO;
import com.stpl.app.cff.dto.CFFDTO;
import com.stpl.app.cff.dto.CFFResultsDTO;
import com.stpl.app.cff.dto.CFFSearchDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.queryUtils.CFFQueryUtils;
import com.stpl.app.cff.security.StplSecurity;
import com.stpl.app.cff.ui.ConsolidatedFinancialForecastUI;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.ui.table.CFFPagedFilterTable;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.ResponsiveUtils;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.util.TableHeaderUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorDisplay;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportUtil;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Link;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.converter.StringToDateConverter;
import com.vaadin.v7.data.validator.RegexpValidator;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author shrihariharan
 */

public class ApprovalTab extends CustomComponent {

    private String projectionIdHidden = "";
    /**
     * Logger implementation for CffApprovalDetailsForm
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApprovalTab.class);
    
    
    
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

    @UiField("latestEstimateLabel")
    private Label latestEstimateLabel;

    /**
     * The updateCycle.
     */
    @UiField("updateCycle")
    public ComboBox updateCycle;

    @UiField("updateCycleLabel")
    private Label updateCycleLabel;

    /**
     * The latestEstimateName
     */
    @UiField("latestEstimateName")
    public TextField latestEstimateName;

    @UiField("latestEstimateNameLabel")
    private Label latestEstimateNameLabel;
    /**
     * The updateCycleName
     */
    @UiField("updateCycleName")
    public TextField updateCycleName;

    @UiField("updateCycleNameLabel")
    private Label updateCycleNameLabel;

    @UiField("status")
    public TextField status;

    @UiField("statusLabel")
    private Label statusLabel;
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
    private CFFPagedFilterTable resultTable;
    private ExtPagedFilterTable excelTable = new ExtPagedFilterTable();
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource("img/excel.png");
    /**
     * Approval Table Container
     */
    private BeanItemContainer<ApprovalDetailsDTO> approvalContainer = new BeanItemContainer<>(ApprovalDetailsDTO.class);
    /**
     * The cff binder.
     */
    private final  CustomFieldGroup cffBinder = new CustomFieldGroup(new BeanItem<ApprovalDetailsDTO>(new ApprovalDetailsDTO()));
    /**
     * The results bean.
     */
    private  BeanItemContainer<CFFResultsDTO> resultsBean = new BeanItemContainer<>(
            CFFResultsDTO.class);
    /**
     * The cff dto.
     */
    private  CFFDTO cffDTO;
    /**
     * The commonUtils.
     */
    private final CommonUtils commonUtils = new CommonUtils();
    private static final String ALERT = "Alert";
    private final CFFLogic cffLogic = new CFFLogic();
    private final NotesTabForm notestab;
    
    @UiField("excelExport")
    private Button excelExport;
    
    @UiField("bottombuttonLayout")
    private HorizontalLayout bottombuttonLayout;
    private boolean isFirst = false;
    private final CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    
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
    /**
     * ApprovalTab form constructor
     */
    private final CFFSearchDTO dto;
    private final SessionDTO sessionDTO;

    public ApprovalTab(CFFSearchDTO dto, BeanItemContainer<ApprovalDetailsDTO> approvalContainer, BeanItemContainer<CFFResultsDTO> resultsBean,
            NotesTabForm notestab, SessionDTO sessionDTO) {
        LOGGER.debug("Enters ApprovalTab Constructor");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/cff/tabs/ApprovalTab.xml"), this));
        bottombuttonLayout.setVisible(false);
        this.dto = dto;
        this.approvalContainer = approvalContainer;
        this.resultsBean = resultsBean;
        this.notestab = notestab;
        this.sessionDTO = sessionDTO;
        configurePermission();
        configureFields();
        LOGGER.debug("Exits ApprovalTab Constructor");
    }

    /**
     * To set additional properties for the UI components
     */
    private void configureFields() {
        LOGGER.debug("Enters approval Tab Configure Fields");
        try {
            approveBtnLogic();
            closeBtnLogic();
            excelTable = new ExtPagedFilterTable();
            excelTable.setContainerDataSource(resultsBean);
            excelTable.setVisibleColumns(TableHeaderUtils.getInstance().approvedResultTableVisibleColumn);
            excelTable.setColumnHeaders(TableHeaderUtils.getInstance().approvedResultTableHeader);
            excelTable.setColumnAlignment(TableHeaderUtils.getInstance().approvedResultTableVisibleColumn[NumericConstants.TWO], ExtCustomTable.Align.CENTER);
            excelTable.setColumnAlignment(TableHeaderUtils.getInstance().approvedResultTableVisibleColumn[NumericConstants.FOUR], ExtCustomTable.Align.CENTER);
            excelTable.setFilterBarVisible(true);
            excelTable.setSizeFull();
            excelTable.setImmediate(true);
            excelTable.setPageLength(NumericConstants.FIVE);
            excelTable.setHeight("270px");
            excelTable.setFilterDecorator(new ExtDemoFilterDecorator());
            excelTable.addStyleName(Constants.FILTER_TABLE);
            excelTable.setVisible(false);
            excelExport.setIcon(excelExportImage);

            latestEstimateName.setImmediate(true);
            latestEstimateName.addValidator(new StringLengthValidator("Latest Estimate name should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
            latestEstimateName.addValidator(new RegexpValidator(ConstantsUtil.ALPHA_NUM_CHARS, "Latest Estimate name should be alphanumeric"));

            updateCycleName.setImmediate(true);
            updateCycleName.addValidator(new StringLengthValidator("Update Cycle name should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
            updateCycleName.addValidator(new RegexpValidator(ConstantsUtil.ALPHA_NUM_CHARS, "Update Cycle name should be alphanumeric"));

            latestEstimate.addItem(ConstantsUtil.SELECT_ONE);
                latestEstimate = commonUtils.getNativeSelect(latestEstimate, CFFLogic.getDropDownList(ConstantsUtil.LOCKED_STATUS), StringUtils.EMPTY);
            latestEstimate.setImmediate(true);
            latestEstimate.setNullSelectionAllowed(true);
            latestEstimate.select(ConstantsUtil.SELECT_ONE);
            latestEstimate.setNullSelectionItemId(ConstantsUtil.SELECT_ONE);
            latestEstimate.setPageLength(NumericConstants.FOUR);

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
                updateCycle = commonUtils.getNativeSelect(updateCycle, CFFLogic.getDropDownList(ConstantsUtil.LOCKED_STATUS), StringUtils.EMPTY);
            updateCycle.setImmediate(true);
            updateCycle.setNullSelectionAllowed(true);
            updateCycle.select(ConstantsUtil.SELECT_ONE);
            updateCycle.setNullSelectionItemId(ConstantsUtil.SELECT_ONE);
            updateCycle.setPageLength(NumericConstants.FOUR);

            if (sessionDTO.getAction().equals("edit") || sessionDTO.getAction().equals("view")) {
                if (("Latest Estimate").equals(dto.getTypeDesc())) {
                    updateCycle = commonUtils.getNativeSelect(updateCycle, CFFLogic.getDropDownList(ConstantsUtil.LOCKED_STATUS), ConstantsUtils.NO);
                } else if (("Update Cycle").equals(dto.getTypeDesc())) {
                    latestEstimate = commonUtils.getNativeSelect(latestEstimate, CFFLogic.getDropDownList(ConstantsUtil.LOCKED_STATUS), ConstantsUtils.NO);
                }
            }

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
            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, StringConstantsUtil.CONSOLIDATED_FINANCIAL_FORECAST + ConstantsUtils.COMMA + EXCEL_HEADER, false);
            List<Object> resultList = getFieldsForSecurity(StringConstantsUtil.CONSOLIDATED_FINANCIAL_FORECAST, EXCEL_HEADER);
            Object[] obj = TableHeaderUtils.getInstance().approvalTableVisibleColumn;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, "Add");
            if (tableResultCustom.getObjResult().length == 0) {
                approvalDetailsTable.setVisible(false);
            }
            approvalDetailsTable = new ExtPagedFilterTable();
            approvalDetailsTable.setContainerDataSource(approvalContainer);
            approvalDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
            approvalDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            approvalDetailsTable.setColumnAlignment(TableHeaderUtils.getInstance().approvalTableVisibleColumn[1], ExtCustomTable.Align.CENTER);
            approvalDetailsTable.setFilterBarVisible(true);
            approvalDetailsTable.setSizeFull();
            approvalDetailsTable.setImmediate(true);
            approvalDetailsTable.setPageLength(NumericConstants.FOUR);
            approvalDetailsTable.setHeight("230px");
            approvalDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            approvalDetailsTable.addStyleName(Constants.FILTER_TABLE);
            approvalDetailsTable.setConverter("approvedDate", new StringToDateConverter() {
                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MM/dd/yyyy, HH/mm/ss");
                }
            });
            if ((status.getValue().equalsIgnoreCase(Constants.PENDING)) || (status.getValue().equalsIgnoreCase(Constants.CANCELLED))
                    || (status.getValue().equalsIgnoreCase(Constants.REJECTED))) {
                approvalDetailsTable.removeAllItems();
            }
            approvalPagination = ResponsiveUtils.getResponsiveControls(approvalDetailsTable.createControls());
            approvalLayout.addComponent(approvalDetailsTable);
            approvalLayout.addComponent(approvalPagination);

            Object[] objects = TableHeaderUtils.getInstance().approvedResultTableVisibleColumn;
            TableResultCustom tableResultCustoms = commonSecurityLogic.getTableColumnsPermission(resultList, objects, fieldIfpHM, "Add");
            if (tableResultCustoms.getObjResult().length == 0) {
                resultTable.setVisible(false);
            }
            resultTable = new CFFPagedFilterTable();
            resultTable.setContainerDataSource(resultsBean);
            resultTable.setVisibleColumns(tableResultCustoms.getObjResult());
            resultTable.setColumnHeaders(tableResultCustoms.getObjResultHeader());
            resultTable.setColumnAlignment(TableHeaderUtils.getInstance().approvedResultTableVisibleColumn[NumericConstants.TWO], ExtCustomTable.Align.CENTER);
            resultTable.setColumnAlignment(TableHeaderUtils.getInstance().approvedResultTableVisibleColumn[NumericConstants.FOUR], ExtCustomTable.Align.CENTER);
            resultTable.setFilterBarVisible(true);
            resultTable.setSizeFull();
            resultTable.setImmediate(true);
            resultTable.setPageLength(NumericConstants.FIVE);
            resultTable.setHeight("270px");
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.addStyleName(Constants.FILTER_TABLE);
            resultTable.setConverter("createdDate", new StringToDateConverter() {
                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MM/dd/yyyy");
                }
            });
            resultTable.setConverter("approvalDate", new StringToDateConverter() {
                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MM/dd/yyyy");
                }
            });
            loadResultTable();
            resultTable.addGeneratedColumn("workflowId", new ExtFilterTreeTable.ColumnGenerator() {
                @Override
                public Object generateCell(ExtCustomTable source, Object itemId, Object columnId) {
                    final CFFResultsDTO cffResultDTO = (CFFResultsDTO) itemId;
                    Link link = new Link();
                    link.setCaption(cffResultDTO.getWorkflowId());
                    link.addStyleName("opener");
                    navigationForNM(link, cffResultDTO);
                    return link;
                }
            });
            resultPagination = ResponsiveUtils.getResponsiveControls(resultTable.createControls());
            resultLayout.addComponent(resultTable);
            resultLayout.addComponent(excelTable);
            resultLayout.addComponent(resultPagination);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        disableFieldsOnview();

        excelExport.setIcon(excelExportImage);
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
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.debug("Entering EXCEL Export Button Click");
                    ConsolidatedFinancialForecastUI.setEXCEL_CLOSE(true);
                    final ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(excelTable), EXCEL_HEADER, EXCEL_HEADER, "ApprovalDetails.xls", false);
                    excel.export();
                    LOGGER.debug(" Ends  EXCEL Export Button Click");

                } catch (Exception exception) {
                    LOGGER.error(exception.getMessage());
                }
            }
        });
        LOGGER.debug("Exits approval Tab Configure Fields");
    }

    public void loadResultTable() {

        setCffDTO(new CFFDTO());
        resultsBean.removeAllItems();
        resultsBean.addAll(cffLogic.loadLatestCCP(sessionDTO));
        resultTable.firePagedChangedEvent();
    }

    /**
     * Navigation for nm.
     *
     * @param link
     * @param dto
     */
    protected void navigationForNM(final Link link, CFFResultsDTO dto) {
        String workflowMasterSysId = null;
        String workflowStatus = null;
        String canApprove = "yes";
        String workflowId = null;
        List<Integer> list;
        if (dto != null) {

            int projectionSysId = dto.getProjectionMasterSid();
            workflowStatus = dto.getWorkflowStatus();
            workflowMasterSysId = String.valueOf(dto.getWorkflowMasterSystemID());
            workflowId = dto.getWorkflowId();
            projectionIdHidden = String.valueOf(projectionSysId);
            String furl = StringUtils.EMPTY;
            boolean isMandated = false;
            String location = Page.getCurrent().getLocation().toString().replaceAll("consolidated-financial-forecast", "%s");
            if (workflowId.startsWith("CF")) {
                furl = String.format(location, "commercial?nmFlow='true'");
            } else if (workflowId.startsWith("GF")) {
                isMandated = true;
                furl = String.format(location, "government?mFlow='true''");
            }

            String parameter = "&projectionIdFromWorkflow=" + projectionIdHidden + "&workflowId=" + workflowMasterSysId + "&workflowStatus=" + workflowStatus + "&canApprove=" + canApprove + "&noOfApprovals=1&approvalLevel=1";

            if (isMandated) {
                parameter += "&portletName=" + "Government";
                link.setDescription("Open Government Forecasting");
            } else {
                parameter += "&portletName=" + "Commercial";
                list = getApprovedDetails(projectionSysId);
                if (!list.isEmpty()) {
                    parameter += "&customerHierSid=" + list.get(0);
                    parameter += "&customerHierarchyLevel=" + list.get(1);
                    parameter += "&custRelationshipBuilderSid=" + list.get(NumericConstants.TWO);
                    parameter += "&productHierarchyLevel=" + list.get(NumericConstants.THREE);
                    parameter += "&prodRelationshipBuilderSid=" + list.get(NumericConstants.FOUR);
                    parameter += "&productHierSid=" + list.get(NumericConstants.FIVE);
                }
                link.setDescription("Open Commercial Forecasting");
            }
            furl += parameter;
            LOGGER.debug("Redirecting to URL Ready : --------------{}",furl);
            link.setResource(new ExternalResource(furl));
        } else {
            projectionIdHidden = null;

        }

    }

    public List<Integer> getApprovedDetails(int projectionId) {
        return CFFQueryUtils.getApprovedDetails(projectionId);
    }

    /**
     * Submit button logic
     *
     * @param event
     */
    @UiHandler("submitBtn")
    public void submitBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("inside Submit method");

        new AbstractNotificationUtils() {
            @Override
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
                    submitLogic();
                    isFirst = true;
                    setIsFirst(isFirst);
            }
        }.getConfirmationMessage("Submit Confirmation",
                "Are you sure you want to Submit the Forecast Workflow?");

        LOGGER.debug("Exits Submit method");

    }

    /**
     * Approve button click event handler
     *
     * @param event
     */
    public void approveBtnLogic() {
        LOGGER.debug("Inside Approval Button click event method");

        approveBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                AbstractNotificationUtils notification = new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                        // To change body of generated methods, choose Tools
                        // | Templates.
                    }

                    @Override
                    public void yesMethod() {

                        String result = "";
                        final int cffMasterSystemId = dto.getCffMasterSid();
                        final String userId = sessionDTO.getUserId();

                        cffLogic.approveCffInformation(cffMasterSystemId, userId);
                        result = cffLogic.approveCffApproveDetails(userId, cffMasterSystemId, CommonUtils.WORKFLOW_STATUS_APPROVED).get(0).toString();

                        if (!result.equals(CommonUtils.FAIL)) {
                            notestab.getApprovalWindow().close();
                            CommonUIUtils.getMessageNotification("Approved Successfully");
                        }
                    }
                };
                notification.getConfirmationMessage("Confirmation", "Are you sure you want to Approve the Forecast Workflow?");

            }
        });

        LOGGER.debug("Exits Approval Button click even method");
    }

    /**
     * Reject button click method event handler
     *
     * @param event
     */
    @UiHandler("rejectBtn")
    public void rejectBtnLogic(final Button.ClickEvent event) {
        LOGGER.debug("Inside Rejected Button click event method");
        new AbstractNotificationUtils() {
            @Override
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

                cffLogic.approveCffInformation(cffMasterSystemId, userId);

                result = cffLogic.approveCffApproveDetails(userId, cffMasterSystemId, CommonUtils.WORKFLOW_STATUS_REJECTED).get(0).toString();

                if (!result.equals(CommonUtils.FAIL)) {
                    notestab.getApprovalWindow().close();
                    CommonUIUtils.getMessageNotification("Rejected Successfully");
                }
            }
        }.getConfirmationMessage("Reject Confirmation",
                "Are you sure you want to Reject the Forecast Workflow?");

        LOGGER.debug("Inside Rejected Button click event method");
    }

    /**
     * Reject button click method event handler
     *
     * @param event
     */
    @UiHandler("cancelBtn")
    public void cancelBtnLogic(final Button.ClickEvent event) {
        LOGGER.debug("Inside Cancelled Button click event method");
        new AbstractNotificationUtils() {
            @Override
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

                cffLogic.approveCffInformation(cffMasterSystemId, userId);

                result = cffLogic.approveCffApproveDetails(userId, cffMasterSystemId, CommonUtils.WORKFLOW_STATUS_CANCELLED).get(0).toString();

                if (!result.equals(CommonUtils.FAIL)) {
                    notestab.getApprovalWindow().close();
                    CommonUIUtils.getMessageNotification("Cancelled Successfully");
                }
            }
        }.getConfirmationMessage("Cancel Confirmation",
                " Are you sure you want to Cancel the Forecast Workflow?");

        LOGGER.debug("Inside Cancelled Button click event method");
    }

    @UiHandler("deleteBtn")
    public void deleteBtnLogic(final Button.ClickEvent event) {
        LOGGER.debug("Inside delete Button click event method");
        new AbstractNotificationUtils() {
            @Override
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
                String result;
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

        LOGGER.debug("Inside delete Button click event method");
    }

    public void closeBtnLogic() {
        LOGGER.debug("Inside Close Click Listener");
        closeBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                AbstractNotificationUtils notification = new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                        // To change body of generated methods, choose Tools
                        // | Templates.
                    }

                    @Override
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
            @Override
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
            cffLogic.getNoOfLevelFromJbpm(sessionDTO, sessionDTO.getUserId());
            String noOfLevel = cffLogic.getNoOfLevelFromDB(String.valueOf(dto.getCffMasterSid()));
            cffLogic.submitCffPendingDetails(sessionDTO.getUserId(), dto.getCffMasterSid(), noOfLevel);
            CommonUIUtils.getMessageNotification("CFF Re-Submitted Successfully");
            return Boolean.TRUE;
        } else {
            final int cffMasterSystemId = dto.getCffMasterSid();
            final String userId = sessionDTO.getUserId();
            Map<String, Object> valueMap = new HashMap<>();

            valueMap.put(StringConstantsUtil.LATEST_ESTIMATE, latestEstimate.getValue());
            valueMap.put("latestEstimateName", latestEstimateName.getValue());
            valueMap.put(StringConstantsUtil.UPDATE_CYCLE, updateCycle.getValue());
            valueMap.put(StringConstantsUtil.UPDATE_CYCLE_NAME, updateCycleName.getValue());
            valueMap.put("cffEligiblDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sessionDTO.getCffEligibleDate()));
            try {
                final String result = cffLogic.saveCffInformation(cffMasterSystemId, userId, valueMap, sessionDTO);
                notestab.saveAdditionalInformation(Integer.parseInt(String.valueOf(VaadinSession.getCurrent().getAttribute("projectionId"))), userId, sessionDTO);
                if (!result.equals(CommonUtils.FAIL)) {
                    CommonUIUtils.getMessageNotification("Submitted Successfully");
                    return Boolean.TRUE;
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return Boolean.FALSE;
    }

    public void getDefaultFocus() {
        latestEstimate.setEnabled(true);
        latestEstimateName.focus();
    }

    protected void excelExportLogic() {
        LOGGER.debug("Entering Approval Details excelExportLogic");
        createWorkSheet();
        LOGGER.debug("Ending Approval Details excelExportLogic");
    }

    private void createWorkSheet() {
        try {
            LOGGER.debug("Entering Approval Details createWorkSheet");
            final long recordCount = resultsBean.size();
            ExcelExportforBB.createWorkSheet(resultTable.getColumnHeaders(), recordCount, this, getUI(), EXCEL_HEADER);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("Ending Approval Details createWorkSheet");
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
    public void createWorkSheetContent(final PrintWriter printWriter) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ExcelExportUtil.DATE_FORMAT, Locale.getDefault());
        CFFResultsDTO cffResultsDTO;
        final List<CFFResultsDTO> reultList = cffLogic.loadLatestCCP(sessionDTO);
        if (resultTable.size() > 0) {
            for (int rowCount = 0; rowCount < reultList.size(); rowCount++) {
                cffResultsDTO =  reultList.get(rowCount);
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
    }

    public boolean submitCheck() {

        if ((StringUtils.isBlank(String.valueOf(latestEstimate.getValue())) || "null".equals(String.valueOf(latestEstimate.getValue()))
                || ConstantsUtil.SELECT_ONE.equals(String.valueOf(latestEstimate.getValue())) || "NO".equals(String.valueOf(latestEstimate.getValue())))
                && (StringUtils.isBlank(String.valueOf(updateCycle.getValue())) || "null".equals(String.valueOf(updateCycle.getValue()))
                || ConstantsUtil.SELECT_ONE.equals(String.valueOf(updateCycle.getValue())) || "NO".equals(String.valueOf(updateCycle.getValue())))) {
            AbstractNotificationUtils.getErrorNotification(ALERT, "Latest Estimate or Update Cycle should be selected 'Yes'");
            return BooleanConstant.getFalseFlag();
        }

        if (ConstantsUtils.YES.equals(String.valueOf(latestEstimate.getValue())) && StringUtils.isBlank(String.valueOf(latestEstimateName.getValue()))) {
            AbstractNotificationUtils.getErrorNotification(ALERT, "Latest Estimate Name should be Entered");
            return BooleanConstant.getFalseFlag();
        }

        if (ConstantsUtils.YES.equals(String.valueOf(updateCycle.getValue()))
                && StringUtils.isBlank(String.valueOf(updateCycleName.getValue()))) {
            AbstractNotificationUtils.getErrorNotification(ALERT, "Update Cycle Name should be Entered");
            return BooleanConstant.getFalseFlag();
        }
        if (!(String.valueOf(latestEstimateName.getValue()).matches(ConstantsUtil.ALPHA_NUM_CHARS))) {
            cffBinder.setErrorDisplay(errorLabel);
            cffBinder.getErrorDisplay().setError("Latest Estimate name should be alphanumeric");
            return BooleanConstant.getFalseFlag();
        }
        if (!(String.valueOf(updateCycleName.getValue()).matches(ConstantsUtil.ALPHA_NUM_CHARS))) {
            cffBinder.setErrorDisplay(errorLabel);
            cffBinder.getErrorDisplay().setError("Update Cycle name should be alphanumeric");
            return BooleanConstant.getFalseFlag();
        }

        if ((resultsBean != null && resultsBean.size() == 0)) {
            AbstractNotificationUtils.getErrorNotification(ALERT, "Please populate the approval details before submitting the workflow for approval");
            return false;
        }
        ErrorDisplay msg = cffBinder.getErrorDisplay();
        if (msg != null) {
            msg.clearError();
        }
        return BooleanConstant.getTrueFlag();
    }

    public final void configurePermission() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtil.USER_ID));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, StringConstantsUtil.CONSOLIDATED_FINANCIAL_FORECAST + "," + "Approval Details");
            if (functionHM.get(StringConstantsUtil.UPDATE_CYCLE) != null && !((AppPermission) functionHM.get(StringConstantsUtil.UPDATE_CYCLE)).isFunctionFlag()) {
                updateCycle.setVisible(false);
                updateCycleLabel.setVisible(false);
            } else {
                updateCycle.setVisible(true);
                updateCycleLabel.setVisible(true);
            }
            if (functionHM.get(StringConstantsUtil.LATEST_ESTIMATE) != null && !((AppPermission) functionHM.get(StringConstantsUtil.LATEST_ESTIMATE)).isFunctionFlag()) {
                latestEstimate.setVisible(false);
                latestEstimateLabel.setVisible(false);
            } else {
                latestEstimate.setVisible(true);
                latestEstimateLabel.setVisible(true);
            }
            if (functionHM.get("latestEstimateName") != null && !((AppPermission) functionHM.get("expandLvlBtn")).isFunctionFlag()) {
                latestEstimateName.setVisible(false);
                latestEstimateNameLabel.setVisible(false);
            } else {
                latestEstimateName.setVisible(true);
                latestEstimateNameLabel.setVisible(true);
            }
            if (functionHM.get(StringConstantsUtil.UPDATE_CYCLE_NAME) != null && !((AppPermission) functionHM.get(StringConstantsUtil.UPDATE_CYCLE_NAME)).isFunctionFlag()) {
                updateCycleName.setVisible(false);
                updateCycleNameLabel.setVisible(false);
            } else {
                updateCycleName.setVisible(true);
                updateCycleNameLabel.setVisible(true);
            }
            if (functionHM.get("status") != null && !((AppPermission) functionHM.get("status")).isFunctionFlag()) {
                status.setVisible(false);
                statusLabel.setVisible(false);
            } else {
                status.setVisible(true);
                statusLabel.setVisible(true);
            }
            if (functionHM.get("resetBtn") != null && !((AppPermission) functionHM.get("resetBtn")).isFunctionFlag()) {
                resetBtn.setVisible(false);
            } else {
                resetBtn.setVisible(true);
            }

        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Search forecast results to load table .
     *
     * @param forecastDTO the forecast dto
     * @return object of list or count
     */
    public List<Object> getFieldsForSecurity(String moduleName, String tabName) {
        List<Object> resultList = new ArrayList<>();
        try {
            resultList = CommonServiceImpl.getInstance().fetchFieldsForSecurity(moduleName, tabName);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return resultList;
    }

	public CFFDTO getCffDTO() {
		return cffDTO;
	}

	public void setCffDTO(CFFDTO cffDTO) {
		this.cffDTO = cffDTO;
	}

}
