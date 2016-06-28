

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.form;

import com.stpl.app.cff.bpm.logic.DSCalculationLogic;
import com.stpl.app.cff.bpm.persistance.WorkflowPersistance;
import com.stpl.app.cff.dto.ApprovalDetailsDTO;
import com.stpl.app.cff.dto.CFFFilterGenerator;
import com.stpl.app.cff.dto.CFFResultsDTO;
import com.stpl.app.cff.dto.CFFSearchDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.lazyLoad.CFFIndexTableLogic;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.queryUtils.CommonQueryUtils;
import com.stpl.app.cff.ui.ConsolidatedFinancialForecastUI;
import com.stpl.app.cff.ui.dataSelection.logic.DataSelectionLogic;
import com.stpl.app.cff.ui.projectionresults.form.ProjectionResults;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.NotificationUtils;
import com.stpl.app.cff.util.ResponsiveUtils;
import com.stpl.app.cff.util.SessionUtil;
import com.stpl.app.cff.util.TableHeaderUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.util.CsvExportforPagedTable;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * CFF Landing page
 *
 * @author Manasa
 */
public class ConsolidatedFinancialForecastForm extends CustomComponent {

    /**
     * Logger implementation for ConsolidatedFinancialForecastForm
     */
    private static final Logger LOGGER = Logger.getLogger(ConsolidatedFinancialForecastForm.class);
    /**
     *
     * Financial ForecastId Label
     */
    @UiField("financialForecastIdLabel")
    private Label financialForecastIdLabel;
    /**
     * The financialForecastId.
     */
    @UiField("financialForecastId")
    private TextField financialForecastId;
    /**
     *
     * Type Label
     */
    @UiField("typeLabel")
    private Label typeLabel;
    /**
     * The typeDdlb.
     */
    @UiField("typeDdlb")
    private ComboBox typeDdlb;
    /**
     *
     * Financial Forecast Name Label
     */
    @UiField("financialForecastNameLabel")
    private Label financialForecastNameLabel;
    /**
     * The financialForecastName.
     */
    @UiField("financialForecastName")
    private TextField financialForecastName;
    /**
     *
     * Status Label
     */
    @UiField("statusLabel")
    private Label statusLabel;
    /**
     * The statusDdlb.
     */
    @UiField("statusDdlb")
    private ComboBox statusDdlb;
    /**
     * The creationFromDate.
     */
    @UiField("creationFromDate")
    private PopupDateField creationFromDate;
    /**
     * The creationToDate.
     */
    @UiField("creationToDate")
    private PopupDateField creationToDate;
    /**
     * The approvalFromDate.
     */
    @UiField("approvalFromDate")
    private PopupDateField approvalFromDate;
    /**
     * The approvalToDate.
     */
    @UiField("approvalToDate")
    private PopupDateField approvalToDate;
    /**
     * The search button.
     */
    @UiField("searchBtn")
    private Button searchBtn;
    /**
     * The reset button.
     */
    @UiField("resetBtn")
    private Button resetBtn;
    /**
     * The add button.
     */
    @UiField("addBtn")
    private Button addBtn;
    /**
     * The edit button.
     */
    @UiField("editBtn")
    private Button editBtn;
    /**
     * The view button.
     */
    @UiField("viewBtn")
    private Button viewBtn;
    CFFIndexTableLogic tableLogic = new CFFIndexTableLogic();
    public ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    ProjectionResults projectionResults;
    /**
     * Label for Created From
     */
    @UiField("creationFrom")
    private Label creationFrom;
    /**
     * Label for Created To
     */
    @UiField("creationTo")
    private Label creationTo;
    /**
     * Label for approval from
     */
    @UiField("approvalFrom")
    private Label approvalFrom;
    /**
     * Label for approval to
     */
    @UiField("approvalTo")
    private Label approvalTo;
    @UiField("resultLayout")
    private VerticalLayout resultLayout;
    /**
     * ApprovalPopup Window
     */
    CffApprovalDetailsForm approvalWindow;
    /**
     * The cff logic
     */
    CFFLogic cffLogic = new CFFLogic();
    /**
     * The cff search dto.
     */
    public CFFSearchDTO cffSearchDTO = new CFFSearchDTO();
    DataSelectionDTO dataSelectionDto;
    /**
     * The cff search binder.
     */
    public CustomFieldGroup cffSearchBinder = new CustomFieldGroup(new BeanItem<CFFSearchDTO>(cffSearchDTO));
    /**
     * Bean container for result table.
     */
    private final BeanItemContainer<CFFSearchDTO> resultsContainer = new BeanItemContainer<CFFSearchDTO>(CFFSearchDTO.class);
    /**
     * Bean container for result table.
     */
    private BeanItemContainer<CFFSearchDTO> container = new BeanItemContainer<CFFSearchDTO>(CFFSearchDTO.class);
    /**
     * The approval details bean.
     */
    public BeanItemContainer<ApprovalDetailsDTO> approvalDetailsBean = new BeanItemContainer<ApprovalDetailsDTO>(
            ApprovalDetailsDTO.class);
    /**
     * The results bean.
     */
    public BeanItemContainer<CFFResultsDTO> resultsBean = new BeanItemContainer<CFFResultsDTO>(
            CFFResultsDTO.class);
    CFFSearchDTO dto;
    @UiField("excelExport")
    Button excelExport;
    /**
     * The error msg.
     */
    final ErrorLabel errorMsg = new ErrorLabel();
    SessionDTO sessionDTO;
    CommonUtils commonutil = new CommonUtils();
    DataSelectionLogic dataLogic = new DataSelectionLogic();
    int projectionid;

    /**
     * ConsolidatedFinancialForecastForm constructor
     *
     * @param sessionDTO
     */
    public ConsolidatedFinancialForecastForm(final SessionDTO sessionDTO) {
        LOGGER.info("Enters ConsolidatedFinancialForecastForm Constructor");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/CFFSearch.xml"), this));
        this.sessionDTO = sessionDTO;
        configureFields();
        getBinder();
        LOGGER.info("Exits ConsolidatedFinancialForecastForm Constructor");
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private CustomFieldGroup getBinder() {
        cffSearchBinder.bindMemberFields(this);
        cffSearchBinder.setItemDataSource(new BeanItem<CFFSearchDTO>(cffSearchDTO));
        cffSearchBinder.setBuffered(true);
        cffSearchBinder.setErrorDisplay(errorMsg);
        return cffSearchBinder;
    }

    /**
     * To set additional properties for the UI components
     */
    private void configureFields() {
        try {
            LOGGER.info("Enters ConsolidatedFinancialForecastForm Configure Field method");

            financialForecastId.setImmediate(true);
            financialForecastId.addValidator(new StringLengthValidator("Financial Forecast Id should be less than 50 characters", 0, 50, true));
            financialForecastId.addValidator(new RegexpValidator(ConstantsUtil.alphaNumericChars, "Financial Forecast Id should be alphanumeric"));

            financialForecastName.setImmediate(true);
            financialForecastName.addValidator(new StringLengthValidator("Financial Forecast Name should be less than 50 characters", 0, 50, true));
            financialForecastName.addValidator(new RegexpValidator(ConstantsUtil.alphaNumericChars, "Financial Forecast Name should be alphanumeric"));

            creationFromDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            creationToDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            approvalFromDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
            approvalToDate.setDateFormat(ConstantsUtil.DATE_FORMAT);

            creationFrom.addStyleName("fromTo");
            creationTo.addStyleName("fromTo");
            approvalFrom.addStyleName("fromTo");
            approvalTo.addStyleName("fromTo");

            typeDdlb.focus();
            commonutil.loadComboBox(typeDdlb, ConstantsUtil.CFF_TYPE, false);
            commonutil.loadComboBoxforstatusddlb(statusDdlb, ConstantsUtil.WORKFLOW_STATUS, false);
            tableLogic.setContainerDataSource(resultsContainer);
            tableLogic.setPageLength(10);
            tableLogic.sinkItemPerPageWithPageLength(false);
            resultTable.setVisibleColumns(TableHeaderUtils.RESULT_TABLE_VISIBLE_COLUMN);
            resultTable.setColumnHeaders(TableHeaderUtils.RESULT_TABLE_HEADER);
            resultTable.setColumnAlignment(TableHeaderUtils.RESULT_TABLE_VISIBLE_COLUMN[4], ExtCustomTable.Align.CENTER);
            resultTable.setFilterBarVisible(true);
            resultTable.setSizeFull();
            resultTable.setSelectable(true);
            resultTable.setImmediate(true);
            resultTable.setPageLength(10);
            resultTable.setHeight("455px");
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.setFilterGenerator(new CFFFilterGenerator());
            resultTable.addStyleName("table-header-normal");
            resultTable.addStyleName("filterbar");
            resultTable.addStyleName(Constants.FILTER_TABLE);
            resultTable.setConverter("finalApprovalDate", new StringToDateConverter() {
                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MM/dd/YYYY");
                }
            });
            resultTable.setConverter("activeFromDate", new StringToDateConverter() {
                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MM/dd/YYYY");
                }
            });
            resultTable.setConverter("activeToDate", new StringToDateConverter() {
                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MM/dd/YYYY");
                }
            });

            HorizontalLayout resultHorizontalLayout = ResponsiveUtils.getResponsiveControls(tableLogic.createControls());
            resultLayout.addComponent(resultTable);
            resultLayout.addComponent(resultHorizontalLayout);

            try {
                CFFLogic.mapUsers();
            } catch (PortalException ex) {
                LOGGER.error(ex);
            } catch (SystemException ex) {
                LOGGER.error(ex);
            }

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
                        CsvExportforPagedTable.createWorkSheet(resultTable.getColumnHeaders(), TableHeaderUtils.RESULT_TABLE_VISIBLE_COLUMN_EXCEL, tableLogic, "Consolidated_Financial_Forecast");
                        LOGGER.info(" Ends  EXCEL Export Button Click");

                    } catch (Exception exception) {
                        LOGGER.error(exception.getMessage());
                    }
                }
            });

            LOGGER.info("Exists ConsolidatedFinancialForecastForm Configure Field method");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ConsolidatedFinancialForecastForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Generate button
     *
     * @param event the event
     */
    @UiHandler("addBtn")
    public void addBtnClickLogic(Button.ClickEvent event) {
        try {
            dto = new CFFSearchDTO();
            approvalDetailsBean = new BeanItemContainer<ApprovalDetailsDTO>(
                    ApprovalDetailsDTO.class);
            resultsBean = new BeanItemContainer<CFFResultsDTO>(
                    CFFResultsDTO.class);
        } catch (Exception e) {
            LOGGER.error(e);
        }finally{
            SessionUtil sessionUtil = new SessionUtil();
            final SessionDTO session = sessionUtil.createSession();
            session.setProjectionId(dto.getCffMasterSid());
            session.setAction(Constants.ADD);
            approvalWindow = new CffApprovalDetailsForm(cffSearchBinder, dto, approvalDetailsBean, resultsBean, session, dataSelectionDto);
            UI.getCurrent().addWindow(approvalWindow);
            approvalWindow.addCloseListener(new Window.CloseListener() {
                @SuppressWarnings("PMD")
                public void windowClose(final Window.CloseEvent e) {
                    financialForecastId.setValue(StringUtils.EMPTY);
                    financialForecastName.setValue(StringUtils.EMPTY);
                    typeDdlb.setValue(ConstantsUtil.SELECT_ONE);
                    statusDdlb.setValue(ConstantsUtil.SELECT_ONE);
                    creationFromDate.setValue(null);
                    creationToDate.setValue(null);
                    approvalFromDate.setValue(null);
                    approvalToDate.setValue(null);
                    resultTable.removeAllItems();
                    resultTable.setContainerDataSource(resultsContainer);
                    resultTable.setVisibleColumns(TableHeaderUtils.RESULT_TABLE_VISIBLE_COLUMN);
                    resultTable.setColumnHeaders(TableHeaderUtils.RESULT_TABLE_HEADER);
                }
            });
        }
    }

    @UiHandler("editBtn")
    public void editBtnClickLogic(Button.ClickEvent event) {
       LOGGER.info("Inside editBtn logic");
        try {
            SessionUtil sessionUtil = new SessionUtil();
            final SessionDTO sessionDto = sessionUtil.createSession();
            dataSelectionDto = new DataSelectionDTO();
            dto = (CFFSearchDTO) resultTable.getValue();
           if (dto != null) {
            int projectionIdValue = dto.getCffMasterSid();
            boolean workflowFlag = false;
            Long processId = 0L;
            List<String> roleList = new ArrayList<String>();
            List processIdList = WorkflowPersistance.selectWFInstanceInfo(projectionIdValue);
            if (processIdList != null && !processIdList.isEmpty()) {
                processId = Long.valueOf(processIdList.get(0).toString());
            }
            String userId = (String) VaadinSession.getCurrent().getAttribute("userId");
            User userModel = UserLocalServiceUtil.getUser(Long.parseLong(userId));
            workflowFlag = DSCalculationLogic.isValidWorkflowUser(userModel, roleList, processId);
            if (workflowFlag) {
                sessionDto.setAction("edit");
                sessionDto.setIsGenerated(Boolean.TRUE);
                sessionDto.setProcessId(processId);
                if (dto != null && dto.getStatusDesc() != null && !Constants.APPROVED.equals(dto.getStatusDesc())) {
                    List list = new ArrayList();
                    list.add(dto.getCffMasterSid());
                    List<Object[]> dsList = CommonQueryUtils.getAppData(list, "searchDS", null);
                    Object[] resultList = dsList.get(0);

                    VaadinSession.getCurrent().setAttribute("projectionId", projectionIdValue);
                    sessionDto.setProjectionId(projectionIdValue);
                    sessionDto.setAction(Constants.CommonConstants.ACTION_EDIT.getConstant());
                    DataSelectionLogic logic = new DataSelectionLogic();
                    sessionDto.setCustomerHierarchyId(resultList[1] != null ? Integer.valueOf(resultList[1].toString()) : 0);
                    sessionDto.setProductHierarchyId(resultList[2] != null ? Integer.valueOf(resultList[2].toString()) : 0);
                    sessionDto.setProductDescription(logic.getLevelValueMap(resultList[4] != null ? Integer.valueOf(resultList[4].toString()) : 0));
                    sessionDto.setCustomerRelationId(resultList[3] != null ? Integer.valueOf(resultList[3].toString()) : 0);
                    sessionDto.setCustRelationshipBuilderSid(resultList[3] != null ? (resultList[3].toString()) : "0");
                    sessionDto.setProductRelationId(resultList[4] != null ? Integer.valueOf(resultList[4].toString()) : 0);
                    sessionDto.setProdRelationshipBuilderSid(resultList[4] != null ? (resultList[4].toString()) : "0");
                    String marketType = dataLogic.getHelperValue("" + projectionIdValue);
                    sessionDto.setMarketTypeValue(marketType);
                    sessionDto.setProductLevelNumber(resultList[5] != null ? (resultList[5].toString()) : "0");
                    sessionDto.setCustomerLevelNumber(resultList[7] != null ? (resultList[7].toString()) : "0");
                    sessionDto.setCustomerDescription(logic.getLevelValueMap(resultList[3] != null ? Integer.valueOf(resultList[3].toString()) : 0));
                    sessionDto.setProjectionId(dto.getCffMasterSid());
                    loadDataSelectionDTO(resultList);

                    approvalDetailsBean = new BeanItemContainer<ApprovalDetailsDTO>(
                            ApprovalDetailsDTO.class);
                    resultsBean = new BeanItemContainer<CFFResultsDTO>(
                            CFFResultsDTO.class);
                    loadSessionDTO();
                    resultsBean.addAll(cffLogic.loadCffDetails(dto.getCffMasterSid()));
                    approvalDetailsBean.addAll(cffLogic.getApprovalDetailsForCff(dto.getCffMasterSid()));
                    approvalWindow = new CffApprovalDetailsForm(cffSearchBinder, dto, approvalDetailsBean, resultsBean, sessionDto, dataSelectionDto);
                    UI.getCurrent().addWindow(approvalWindow);
                    approvalWindow.addCloseListener(new Window.CloseListener() {
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent e) {
                            financialForecastId.setValue(StringUtils.EMPTY);
                            financialForecastName.setValue(StringUtils.EMPTY);
                            typeDdlb.setValue(ConstantsUtil.SELECT_ONE);
                            statusDdlb.setValue(ConstantsUtil.SELECT_ONE);
                            creationFromDate.setValue(null);
                            creationToDate.setValue(null);
                            approvalFromDate.setValue(null);
                            approvalToDate.setValue(null);
                            resultTable.removeAllItems();
                            resultTable.setContainerDataSource(resultsContainer);
                            resultTable.setVisibleColumns(TableHeaderUtils.RESULT_TABLE_VISIBLE_COLUMN);
                            resultTable.setColumnHeaders(TableHeaderUtils.RESULT_TABLE_HEADER);
                        }
                    });
                } else if (dto != null && dto.getStatusDesc() != null && Constants.APPROVED.equals(dto.getStatusDesc())) {
                    AbstractNotificationUtils.getErrorNotification("Cannot Edit Approved Forecasts.", "You cannot edit an Approved forecast package.");
                } else if (dto == null) {
                    AbstractNotificationUtils.getErrorNotification("No Record Selected.", "Please select a record to edit.");
                }
            } else {
                StringBuilder notiMsg = new StringBuilder("You dont have permission to edit a projection.");
                if (!roleList.isEmpty()) {
                    notiMsg.append("\n Only ").append(roleList).append(" can edit a projection.");
                }
                NotificationUtils.getAlertNotification("Permission Denied", notiMsg.toString());
            }
           }else {
                AbstractNotificationUtils.getErrorNotification("No Record Selected.", "Please select a record to EDIT.");
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void loadSessionDTO() {
        DataSelectionDTO dataSelectionDTO;
        int projectionId = dto.getCffMasterSid();
        Map<String, Object> parameters = new HashMap<String, Object>();
        DataSelectionLogic logic = new DataSelectionLogic();
        if (projectionId != 0) {
            try {
                parameters.put("projectionId", projectionId);
                List list = logic.getProjection(projectionId);
                for (Object list1 : list) {
                    Object[] temp = (Object[]) list1;
                    sessionDTO.setProjectionName(String.valueOf(temp[0]));
                    sessionDTO.setProdRelationshipBuilderSid(String.valueOf(temp[1]));
                    sessionDTO.setCustRelationshipBuilderSid(String.valueOf(temp[2]));
                }
                sessionDTO.setHasTradingPartner(logic.hasTradingPartner(projectionId));

            } catch (Exception ex) {
                LOGGER.error(ex + " NonMandatedEditWindow - loadSessionDTO");
            }
        } else {
            dataSelectionDTO = new DataSelectionDTO();
        }
    }

    @UiHandler("viewBtn")
    public void viewBtnClickLogic(Button.ClickEvent event) {
        try {
            SessionUtil sessionUtil = new SessionUtil();
            DataSelectionLogic logic = new DataSelectionLogic();
            final SessionDTO sessionDTO = sessionUtil.createSession();
            sessionDTO.setAction("view");
            dataSelectionDto = new DataSelectionDTO();
            dto = (CFFSearchDTO) resultTable.getValue();
            if (dto != null) {
                sessionDTO.setProjectionId(dto.getCffMasterSid());
                VaadinSession.getCurrent().setAttribute("projectionId", dto.getCffMasterSid());
                List list = new ArrayList();
                list.add(dto.getCffMasterSid());
                List<Object[]> dsList = CommonQueryUtils.getAppData(list, "searchDS", null);
                Object[] resultList = dsList.get(0);
                sessionDTO.setCustomerHierarchyId(resultList[1] != null ? Integer.valueOf(resultList[1].toString()) : 0);
                sessionDTO.setProductHierarchyId(resultList[2] != null ? Integer.valueOf(resultList[2].toString()) : 0);
                sessionDTO.setProductDescription(logic.getLevelValueMap(resultList[4] != null ? Integer.valueOf(resultList[4].toString()) : 0));
                sessionDTO.setCustomerRelationId(resultList[3] != null ? Integer.valueOf(resultList[3].toString()) : 0);
                sessionDTO.setCustRelationshipBuilderSid(resultList[3] != null ? (resultList[3].toString()) : "0");
                sessionDTO.setProductRelationId(resultList[4] != null ? Integer.valueOf(resultList[4].toString()) : 0);
                sessionDTO.setProdRelationshipBuilderSid(resultList[4] != null ? (resultList[4].toString()) : "0");
                String marketType = dataLogic.getHelperValue("" + dto.getCffMasterSid());
                sessionDTO.setMarketTypeValue(marketType);
                sessionDTO.setProductLevelNumber(resultList[5] != null ? (resultList[5].toString()) : "0");
                sessionDTO.setCustomerLevelNumber(resultList[7] != null ? (resultList[7].toString()) : "0");
                sessionDTO.setCustomerDescription(logic.getLevelValueMap(resultList[3] != null ? Integer.valueOf(resultList[3].toString()) : 0));
                loadDataSelectionDTO(resultList);
                    sessionDTO.setCustomerHierarchyId(resultList[1] != null ? Integer.valueOf(resultList[1].toString()) : 0);
                    sessionDTO.setProductHierarchyId(resultList[2] != null ? Integer.valueOf(resultList[2].toString()) : 0);
                    sessionDTO.setProductDescription(logic.getLevelValueMap(resultList[4] != null ? Integer.valueOf(resultList[4].toString()) : 0));
                    sessionDTO.setCustomerRelationId(resultList[3] != null ? Integer.valueOf(resultList[3].toString()) : 0);
                    sessionDTO.setCustRelationshipBuilderSid(resultList[3] != null ? (resultList[3].toString()) : "0");
                    sessionDTO.setProductRelationId(resultList[4] != null ? Integer.valueOf(resultList[4].toString()) : 0);
                    sessionDTO.setProdRelationshipBuilderSid(resultList[4] != null ? (resultList[4].toString()) : "0");
                  //  String marketType = dataLogic.getHelperValue("" + projectionIdValue);
                  //  sessionDTO.setMarketTypeValue(marketType);
                    sessionDTO.setProductLevelNumber(resultList[5] != null ? (resultList[5].toString()) : "0");
                    sessionDTO.setCustomerLevelNumber(resultList[7] != null ? (resultList[7].toString()) : "0");
                sessionDTO.setCustomerDescription(logic.getLevelValueMap(resultList[3] != null ? Integer.valueOf(resultList[3].toString()) : 0));
                approvalDetailsBean = new BeanItemContainer<ApprovalDetailsDTO>(
                        ApprovalDetailsDTO.class);
                resultsBean = new BeanItemContainer<CFFResultsDTO>(
                        CFFResultsDTO.class);
                 loadSessionDTO();
                resultsBean.addAll(cffLogic.loadCffDetails(dto.getCffMasterSid()));
                approvalDetailsBean.addAll(cffLogic.getApprovalDetailsForCff(dto.getCffMasterSid()));
                approvalWindow = new CffApprovalDetailsForm(cffSearchBinder, dto, approvalDetailsBean, resultsBean, sessionDTO, dataSelectionDto);
                UI.getCurrent().addWindow(approvalWindow);
                approvalWindow.addCloseListener(new Window.CloseListener() {
                    @SuppressWarnings("PMD")
                    public void windowClose(final Window.CloseEvent e) {
                        financialForecastId.setValue(StringUtils.EMPTY);
                        financialForecastName.setValue(StringUtils.EMPTY);
                        typeDdlb.setValue(ConstantsUtil.SELECT_ONE);
                        statusDdlb.setValue(ConstantsUtil.SELECT_ONE);
                        creationFromDate.setValue(null);
                        creationToDate.setValue(null);
                        approvalFromDate.setValue(null);
                        approvalToDate.setValue(null);
                        resultTable.removeAllItems();
                        resultTable.setContainerDataSource(resultsContainer);
                        resultTable.setVisibleColumns(TableHeaderUtils.RESULT_TABLE_VISIBLE_COLUMN);
                        resultTable.setColumnHeaders(TableHeaderUtils.RESULT_TABLE_HEADER);
                    }
                });
            } else {
                AbstractNotificationUtils.getErrorNotification("No Record Selected.", "Please select a record to view.");
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @UiHandler("searchBtn")
    public void searchBtnClickLogic(Button.ClickEvent event) {
        boolean pass = true;
        if ((financialForecastId.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(financialForecastId.getValue()))))
                && (financialForecastName.getValue().equals("null") || (StringUtils.isBlank(String.valueOf(financialForecastName.getValue()))))
                && (typeDdlb.getValue() == null || String.valueOf(typeDdlb.getValue()).equals(ConstantsUtil.SELECT_ONE) || String.valueOf(typeDdlb.getValue()).equals(null))
                && (statusDdlb.getValue() == null || String.valueOf(statusDdlb.getValue()).equals(ConstantsUtil.SELECT_ONE) || String.valueOf(statusDdlb.getValue()).equals(null))
                && creationFromDate.getValue() == null && creationToDate.getValue() == null && approvalFromDate.getValue() == null && approvalToDate.getValue() == null) {
            pass = false;

            AbstractNotificationUtils.getErrorNotification("Search Criteria", " Please enter/select search criteria.");

        } else {
            if (creationFromDate.getValue() != null && creationToDate.getValue() != null) {
                if (creationFromDate.getValue().after(creationToDate.getValue())) {
                    pass = false;
                    AbstractNotificationUtils.getErrorNotification("Date Error", "Creation From date should be before Creation To date");
                }
            }
            if (approvalFromDate.getValue() != null && approvalToDate.getValue() != null) {
                if (approvalFromDate.getValue().after(approvalToDate.getValue())) {
                    pass = false;
                    AbstractNotificationUtils.getErrorNotification("Date Error", "Approval From date should be before Approval To date");
                }
            }
        }
        if (pass) {
            searchButtonClickLogic(event);
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
                  financialForecastId.setValue(StringUtils.EMPTY);
                  financialForecastName.setValue(StringUtils.EMPTY);
                  typeDdlb.setValue(null);
                  statusDdlb.setValue(null);
                  creationFromDate.setValue(null);
                  creationToDate.setValue(null);
                  approvalFromDate.setValue(null);
                  approvalToDate.setValue(null);
                  resultsContainer.removeAllItems();
                  resultTable.removeAllItems();
                  tableLogic.loadSetData(null, Boolean.FALSE,Boolean.TRUE);
//                cffSearchBinder.getErrorDisplay().clearError();
//                cffSearchBinder.setItemDataSource(new BeanItem<CFFSearchDTO>(new CFFSearchDTO()));
            }
        }.getConfirmationMessage("Reset Confirmation",
                "Are you sure you want to reset the Search Criteria?");
    }

    /**
     * Search button click logic.
     *
     * @param event the event
     */
    protected void searchButtonClickLogic(final Button.ClickEvent event) {
        try {
            cffSearchBinder.commit();
            resultsContainer.removeAllItems();
            if (!tableLogic.loadSetData(cffSearchDTO, Boolean.TRUE,Boolean.FALSE)) {
                MessageBox.showPlain(Icon.INFO, ConstantsUtil.ERROR, "There are no results that match the Search Criteria.", ButtonId.OK);
            } else {
                Notification.show("Search Completed");
            }
            resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
            resultTable.setFilterGenerator(new CFFFilterGenerator());
            resultTable.setImmediate(true);
        } catch (FieldGroup.CommitException ex) {
            LOGGER.error(ex);
        }
    }

    public void loadDataSelectionDTO(Object[] resultList) {
        dataSelectionDto.setCustomerHierSid(resultList[1] != null ? resultList[1].toString() : "0");
        dataSelectionDto.setCustomerHierarchyLevel(resultList[7] != null ? resultList[7].toString() : "0");
        dataSelectionDto.setCustRelationshipBuilderSid(resultList[3] != null ? resultList[3].toString() : "0");
        dataSelectionDto.setProdRelationshipBuilderSid(resultList[4] != null ? resultList[4].toString() : "0");
        dataSelectionDto.setProdHierSid(resultList[2] != null ? resultList[2].toString() : "0");
        dataSelectionDto.setProductHierarchyLevel(resultList[5] != null ? resultList[5].toString() : "0");
        dataSelectionDto.setCustomerHierarchyInnerLevel(resultList[17] != null ? resultList[17].toString() : "0");
        dataSelectionDto.setProductHierarchyInnerLevel(resultList[18] != null ? resultList[18].toString() : "0");
        dataSelectionDto.setCompanySid(resultList[12] != null ? resultList[12].toString() : "0");
        dataSelectionDto.setCustomerGrpSid(resultList[13] != null ? resultList[13].toString() : "0");
        dataSelectionDto.setProdGrpSid(resultList[15] != null ? resultList[15].toString() : "0");
        dataSelectionDto.setCustomerHierarchy(resultList[6] != null ? resultList[6].toString() : "0");
        dataSelectionDto.setProductHierarchy(resultList[8] != null ? resultList[8].toString() : "0");
        dataSelectionDto.setCustomerHierVersionNo(resultList[19] != null ? Integer.valueOf(resultList[19].toString()) : 0);
        dataSelectionDto.setProductHierVersionNo(resultList[20] != null ? Integer.valueOf(resultList[20].toString()) : 0);
        dataSelectionDto.setCompanyName(resultList[21] != null ? resultList[21].toString() : " ");
        dataSelectionDto.setProjectionId(dto.getCffMasterSid());

    }
}

