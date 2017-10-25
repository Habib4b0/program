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
import com.stpl.app.cff.queryUtils.CFFQueryUtils;
import com.stpl.app.cff.queryUtils.CommonQueryUtils;
import com.stpl.app.cff.security.StplSecurity;
import com.stpl.app.cff.ui.ConsolidatedFinancialForecastUI;
import com.stpl.app.cff.ui.dataSelection.logic.DataSelectionLogic;
import com.stpl.app.cff.ui.projectionresults.form.ProjectionResults;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.ResponsiveUtils;
import com.stpl.app.cff.util.SessionUtil;
import com.stpl.app.cff.util.TableHeaderUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.util.NumericConstants;
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
     * The approval details bean.
     */
    public BeanItemContainer<ApprovalDetailsDTO> approvalDetailsBean = new BeanItemContainer<ApprovalDetailsDTO>(
            ApprovalDetailsDTO.class);
    /**
     * The updateCycleBean bean.
     */
    public List<ApprovalDetailsDTO> updateCycleBean = new ArrayList<ApprovalDetailsDTO>();
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
    private String topLevelName = StringUtils.EMPTY;

    /**
     * ConsolidatedFinancialForecastForm constructor
     *
     * @param sessionDTO
     */
    public ConsolidatedFinancialForecastForm(final SessionDTO sessionDTO) {
        LOGGER.debug("Enters ConsolidatedFinancialForecastForm Constructor");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/CFFSearch.xml"), this));
        this.sessionDTO = sessionDTO;
        configurePermission();
        configureFields();
        getBinder();
        LOGGER.debug("Exits ConsolidatedFinancialForecastForm Constructor");
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
            LOGGER.debug("Enters ConsolidatedFinancialForecastForm Configure Field method");

            financialForecastId.setImmediate(true);
            financialForecastId.addValidator(new StringLengthValidator("Financial Forecast Id should be less than 50 characters", 0, NumericConstants.FIFTY, true));
            financialForecastId.addValidator(new RegexpValidator(ConstantsUtil.alphaNumericChars, "Financial Forecast Id should be alphanumeric"));

            financialForecastName.setImmediate(true);
            financialForecastName.addValidator(new StringLengthValidator("Financial Forecast Name should be less than 50 characters", 0, NumericConstants.FIFTY, true));
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
            tableLogic.setPageLength(NumericConstants.TEN);
            tableLogic.sinkItemPerPageWithPageLength(false);
            resultTable.setVisibleColumns(TableHeaderUtils.RESULT_TABLE_VISIBLE_COLUMN);
            resultTable.setColumnHeaders(TableHeaderUtils.RESULT_TABLE_HEADER);
            resultTable.setColumnAlignment(TableHeaderUtils.RESULT_TABLE_VISIBLE_COLUMN[NumericConstants.FOUR], ExtCustomTable.Align.CENTER);
            resultTable.setFilterBarVisible(true);
            resultTable.setSizeFull();
            resultTable.setSelectable(true);
            resultTable.setImmediate(true);
            resultTable.setPageLength(NumericConstants.TEN);
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

            CFFLogic.mapUsers();

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
                        LOGGER.debug("Entering EXCEL Export Button Click");
                        ConsolidatedFinancialForecastUI.EXCEL_CLOSE = true;
                        CsvExportforPagedTable.createWorkSheet(resultTable.getColumnHeaders(), TableHeaderUtils.RESULT_TABLE_VISIBLE_COLUMN_EXCEL, tableLogic, "Consolidated_Financial_Forecast");
                        LOGGER.debug(" Ends  EXCEL Export Button Click");

                    } catch (Exception exception) {
                        LOGGER.error(exception);
                    }
                }
            });

            LOGGER.debug("Exists ConsolidatedFinancialForecastForm Configure Field method");
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
        } finally {
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
        LOGGER.debug("Inside editBtn logic");
        try {
            SessionUtil sessionUtil = new SessionUtil();
            final SessionDTO sessionDto = sessionUtil.createSession();
            dataSelectionDto = new DataSelectionDTO();
            dto = (CFFSearchDTO) resultTable.getValue();
            if (dto != null) {
                int projectionIdValue = dto.getCffMasterSid();
                Long processId = 0L;
                List<String> roleList = new ArrayList<String>();
                List processIdList = WorkflowPersistance.selectWFInstanceInfo(projectionIdValue);
                if (processIdList != null && !processIdList.isEmpty()) {
                    processId = Long.valueOf(processIdList.get(0).toString());
                }
                String userId = (String) VaadinSession.getCurrent().getAttribute("userId");
                User userModel = UserLocalServiceUtil.getUser(Long.parseLong(userId));
                DSCalculationLogic.isValidWorkflowUser(userModel, roleList, processId);
                sessionDto.setAction("edit");
                sessionDto.setIsGenerated(Boolean.TRUE);
                sessionDto.setProcessId(processId);
                sessionDto.setWorkflowStatus(dto.getStatusDesc());
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
                    sessionDto.setProductHierarchyId(resultList[NumericConstants.TWO] != null ? Integer.valueOf(resultList[NumericConstants.TWO].toString()) : 0);
                    sessionDto.setProductDescription(logic.getLevelValueMap(resultList[NumericConstants.FOUR] != null ? Integer.valueOf(resultList[NumericConstants.FOUR].toString()) : 0));
                    sessionDto.setCustomerRelationId(resultList[NumericConstants.THREE] != null ? Integer.valueOf(resultList[NumericConstants.THREE].toString()) : 0);
                    sessionDto.setCustRelationshipBuilderSid(resultList[NumericConstants.THREE] != null ? resultList[NumericConstants.THREE].toString() : "0");
                    sessionDto.setProductRelationId(resultList[NumericConstants.FOUR] != null ? Integer.valueOf(resultList[NumericConstants.FOUR].toString()) : 0);
                    sessionDto.setProdRelationshipBuilderSid(resultList[NumericConstants.FOUR] != null ? resultList[NumericConstants.FOUR].toString() : "0");
                    String marketType = dataLogic.getHelperValue("" + projectionIdValue);
                    sessionDto.setMarketTypeValue(marketType);
                    sessionDto.setProductLevelNumber(resultList[NumericConstants.FIVE] != null ? resultList[NumericConstants.FIVE].toString() : "0");
                    sessionDto.setCustomerLevelNumber(resultList[NumericConstants.SEVEN] != null ? resultList[NumericConstants.SEVEN].toString() : "0");
                    sessionDto.setCustomerDescription(logic.getLevelValueMap(resultList[NumericConstants.THREE] != null ? Integer.valueOf(resultList[NumericConstants.THREE].toString()) : 0));
                    sessionDto.setProjectionId(dto.getCffMasterSid());
                    loadDataSelectionDTO(resultList);
                    sessionDto.setScreenName("CCP_HIERARCHY");
                    CFFQueryUtils.createTempTables(sessionDto);
                    dataSelectionDto.setCustomerHierSid(String.valueOf(sessionDto.getCustomerHierarchyId()));
                    dataSelectionDto.setCustRelationshipBuilderSid(String.valueOf(sessionDto.getCustRelationshipBuilderSid()));
                    dataSelectionDto.setProdRelationshipBuilderSid(String.valueOf(sessionDto.getProdRelationshipBuilderSid()));
                    dataSelectionDto.setCustomerHierarchyLevel(sessionDto.getCustomerLevelNumber());
                    dataSelectionDto.setProductHierarchyLevel(sessionDto.getProductLevelNumber());
                    topLevelName = cffLogic.getTopLevelInHierarchy(dataSelectionDto.getCustomerHierSid());
                    cffLogic.ccpHierarchyInsert(sessionDto.getCurrentTableNames(), dataSelectionDto, cffLogic.getCustandProdSelection(sessionDto.getProjectionId(), "EDIT_MODE_PROJECTION_CUST_SELECTION"),
                            cffLogic.getCustandProdSelection(sessionDto.getProjectionId(), "EDIT_MODE_PROJECTION_PROD_SELECTION"), topLevelName, Boolean.FALSE);
                    sessionDto.setCustomerLevelDetails(logic.getLevelValueDetails(sessionDto, dataSelectionDto.getCustRelationshipBuilderSid(), true));
                    sessionDto.setProductLevelDetails(logic.getLevelValueDetails(sessionDto, dataSelectionDto.getProdRelationshipBuilderSid(), false));
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
                AbstractNotificationUtils.getErrorNotification("No Record Selected.", "Please select a record to EDIT.");
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void loadSessionDTO() {
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
                    sessionDTO.setCustRelationshipBuilderSid(String.valueOf(temp[NumericConstants.TWO]));
                }
                sessionDTO.setHasTradingPartner(logic.hasTradingPartner(projectionId));

            } catch (Exception ex) {
                LOGGER.error(ex + " NonMandatedEditWindow - loadSessionDTO");
            }
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
                int projectionIdValue = dto.getCffMasterSid();
                sessionDTO.setProjectionId(dto.getCffMasterSid());
                VaadinSession.getCurrent().setAttribute("projectionId", projectionIdValue);
                sessionDTO.setProjectionId(projectionIdValue);
                List list = new ArrayList();
                list.add(dto.getCffMasterSid());
                List<Object[]> dsList = CommonQueryUtils.getAppData(list, "searchDS", null);
                Object[] resultList = dsList.get(0);
                sessionDTO.setCustomerHierarchyId(resultList[1] != null ? Integer.valueOf(resultList[1].toString()) : 0);
                sessionDTO.setProductHierarchyId(resultList[NumericConstants.TWO] != null ? Integer.valueOf(resultList[NumericConstants.TWO].toString()) : 0);
                sessionDTO.setProductDescription(logic.getLevelValueMap(resultList[NumericConstants.FOUR] != null ? Integer.valueOf(resultList[NumericConstants.FOUR].toString()) : 0));
                sessionDTO.setCustomerRelationId(resultList[NumericConstants.THREE] != null ? Integer.valueOf(resultList[NumericConstants.THREE].toString()) : 0);
                sessionDTO.setCustRelationshipBuilderSid(resultList[NumericConstants.THREE] != null ? resultList[NumericConstants.THREE].toString() : "0");
                sessionDTO.setProductRelationId(resultList[NumericConstants.FOUR] != null ? Integer.valueOf(resultList[NumericConstants.FOUR].toString()) : 0);
                sessionDTO.setProdRelationshipBuilderSid(resultList[NumericConstants.FOUR] != null ? resultList[NumericConstants.FOUR].toString() : "0");
                String marketType = dataLogic.getHelperValue("" + dto.getCffMasterSid());
                sessionDTO.setMarketTypeValue(marketType);
                sessionDTO.setProductLevelNumber(resultList[NumericConstants.FIVE] != null ? resultList[NumericConstants.FIVE].toString() : "0");
                sessionDTO.setCustomerLevelNumber(resultList[NumericConstants.SEVEN] != null ? resultList[NumericConstants.SEVEN].toString() : "0");
                sessionDTO.setCustomerDescription(logic.getLevelValueMap(resultList[NumericConstants.THREE] != null ? Integer.valueOf(resultList[NumericConstants.THREE].toString()) : 0));
                loadDataSelectionDTO(resultList);
                sessionDTO.setCustomerHierarchyId(resultList[1] != null ? Integer.valueOf(resultList[1].toString()) : 0);
                sessionDTO.setProductHierarchyId(resultList[NumericConstants.TWO] != null ? Integer.valueOf(resultList[NumericConstants.TWO].toString()) : 0);
                sessionDTO.setProductDescription(logic.getLevelValueMap(resultList[NumericConstants.FOUR] != null ? Integer.valueOf(resultList[NumericConstants.FOUR].toString()) : 0));
                sessionDTO.setCustomerRelationId(resultList[NumericConstants.THREE] != null ? Integer.valueOf(resultList[NumericConstants.THREE].toString()) : 0);
                sessionDTO.setCustRelationshipBuilderSid(resultList[NumericConstants.THREE] != null ? resultList[NumericConstants.THREE].toString() : "0");
                sessionDTO.setProductRelationId(resultList[NumericConstants.FOUR] != null ? Integer.valueOf(resultList[NumericConstants.FOUR].toString()) : 0);
                sessionDTO.setProdRelationshipBuilderSid(resultList[NumericConstants.FOUR] != null ? resultList[NumericConstants.FOUR].toString() : "0");
                sessionDTO.setProductLevelNumber(resultList[NumericConstants.FIVE] != null ? resultList[NumericConstants.FIVE].toString() : "0");
                sessionDTO.setCustomerLevelNumber(resultList[NumericConstants.SEVEN] != null ? resultList[NumericConstants.SEVEN].toString() : "0");
                sessionDTO.setCustomerDescription(logic.getLevelValueMap(resultList[NumericConstants.THREE] != null ? Integer.valueOf(resultList[NumericConstants.THREE].toString()) : 0));
                sessionDTO.setScreenName("CCP_HIERARCHY");
                CFFQueryUtils.createTempTables(sessionDTO);
                dataSelectionDto.setCustomerHierSid(String.valueOf(sessionDTO.getCustomerHierarchyId()));
                dataSelectionDto.setCustRelationshipBuilderSid(String.valueOf(sessionDTO.getCustRelationshipBuilderSid()));
                dataSelectionDto.setProdRelationshipBuilderSid(String.valueOf(sessionDTO.getProdRelationshipBuilderSid()));
                dataSelectionDto.setCustomerHierarchyLevel(sessionDTO.getCustomerLevelNumber());
                dataSelectionDto.setProductHierarchyLevel(sessionDTO.getProductLevelNumber());
                topLevelName = cffLogic.getTopLevelInHierarchy(dataSelectionDto.getCustomerHierSid());
                cffLogic.ccpHierarchyInsert(sessionDTO.getCurrentTableNames(), dataSelectionDto, cffLogic.getCustandProdSelection(sessionDTO.getProjectionId(), "EDIT_MODE_PROJECTION_CUST_SELECTION"),
                        cffLogic.getCustandProdSelection(sessionDTO.getProjectionId(), "EDIT_MODE_PROJECTION_PROD_SELECTION"), topLevelName, Boolean.FALSE);
                sessionDTO.setCustomerLevelDetails(logic.getLevelValueDetails(sessionDTO, dataSelectionDto.getCustRelationshipBuilderSid(), true));
                sessionDTO.setProductLevelDetails(logic.getLevelValueDetails(sessionDTO, dataSelectionDto.getProdRelationshipBuilderSid(), false));
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
                && (typeDdlb.getValue() == null || String.valueOf(typeDdlb.getValue()).equals(ConstantsUtil.SELECT_ONE) || typeDdlb.getValue() == null)
                && (statusDdlb.getValue() == null || String.valueOf(statusDdlb.getValue()).equals(ConstantsUtil.SELECT_ONE) || statusDdlb.getValue() == null)
                && creationFromDate.getValue() == null && creationToDate.getValue() == null && approvalFromDate.getValue() == null && approvalToDate.getValue() == null) {
            pass = false;

            AbstractNotificationUtils.getErrorNotification("Search Criteria", " Please enter/select search criteria.");

        } else {
            if (creationFromDate.getValue() != null && creationToDate.getValue() != null && creationFromDate.getValue().after(creationToDate.getValue())) {
                pass = false;
                AbstractNotificationUtils.getErrorNotification("Date Error", "Creation From date should be before Creation To date");
            }
            if (approvalFromDate.getValue() != null && approvalToDate.getValue() != null && approvalFromDate.getValue().after(approvalToDate.getValue())) {
                pass = false;
                AbstractNotificationUtils.getErrorNotification("Date Error", "Approval From date should be before Approval To date");
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
                tableLogic.loadSetData(null, Boolean.FALSE, Boolean.TRUE);
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
            if (!tableLogic.loadSetData(cffSearchDTO, Boolean.TRUE, Boolean.FALSE)) {
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
        dataSelectionDto.setCustomerHierarchyLevel(resultList[NumericConstants.SEVEN] != null ? resultList[NumericConstants.SEVEN].toString() : "0");
        dataSelectionDto.setCustRelationshipBuilderSid(resultList[NumericConstants.THREE] != null ? resultList[NumericConstants.THREE].toString() : "0");
        dataSelectionDto.setProdRelationshipBuilderSid(resultList[NumericConstants.FOUR] != null ? resultList[NumericConstants.FOUR].toString() : "0");
        dataSelectionDto.setProdHierSid(resultList[NumericConstants.TWO] != null ? resultList[NumericConstants.TWO].toString() : "0");
        dataSelectionDto.setProductHierarchyLevel(resultList[NumericConstants.FIVE] != null ? resultList[NumericConstants.FIVE].toString() : "0");
        dataSelectionDto.setCustomerHierarchyInnerLevel(resultList[NumericConstants.SEVENTEEN] != null ? resultList[NumericConstants.SEVENTEEN].toString() : "0");
        dataSelectionDto.setProductHierarchyInnerLevel(resultList[NumericConstants.EIGHTEEN] != null ? resultList[NumericConstants.EIGHTEEN].toString() : "0");
        dataSelectionDto.setCompanySid(resultList[NumericConstants.TWELVE] != null ? resultList[NumericConstants.TWELVE].toString() : "0");
        dataSelectionDto.setCustomerGrpSid(resultList[NumericConstants.THIRTEEN] != null ? resultList[NumericConstants.THIRTEEN].toString() : "0");
        dataSelectionDto.setProdGrpSid(resultList[NumericConstants.FIFTEEN] != null ? resultList[NumericConstants.FIFTEEN].toString() : "0");
        dataSelectionDto.setCustomerHierarchy(resultList[NumericConstants.SIX] != null ? resultList[NumericConstants.SIX].toString() : "0");
        dataSelectionDto.setProductHierarchy(resultList[NumericConstants.EIGHT] != null ? resultList[NumericConstants.EIGHT].toString() : "0");
        dataSelectionDto.setCustomerHierVersionNo(resultList[NumericConstants.NINETEEN] != null ? Integer.valueOf(resultList[NumericConstants.NINETEEN].toString()) : 0);
        dataSelectionDto.setProductHierVersionNo(resultList[NumericConstants.TWENTY] != null ? Integer.valueOf(resultList[NumericConstants.TWENTY].toString()) : 0);
        dataSelectionDto.setCompanyName(resultList[NumericConstants.TWENTY_ONE] != null ? resultList[NumericConstants.TWENTY_ONE].toString() : " ");
        dataSelectionDto.setProjectionId(dto.getCffMasterSid());
        dataSelectionDto.setBusinessUnitSystemId(resultList[NumericConstants.TWENTY_TWO] != null ? Integer.valueOf(resultList[NumericConstants.TWENTY_TWO].toString()) : 0);

    }

    public void configurePermission() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtil.USER_ID));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Consolidated Financial Forecast" + "," + "Landing screen");

            if (functionHM.get("searchBtn") != null && !((AppPermission) functionHM.get("searchBtn")).isFunctionFlag()) {
                searchBtn.setVisible(false);
            } else {
                searchBtn.setVisible(true);
            }

            if (functionHM.get("resetBtn") != null && !((AppPermission) functionHM.get("resetBtn")).isFunctionFlag()) {
                resetBtn.setVisible(false);
            } else {
                resetBtn.setVisible(true);
            }
            if (functionHM.get("editBtn") != null && !((AppPermission) functionHM.get("editBtn")).isFunctionFlag()) {
                editBtn.setVisible(false);
            } else {
                editBtn.setVisible(true);
            }
            if (functionHM.get("viewBtn") != null && !((AppPermission) functionHM.get("viewBtn")).isFunctionFlag()) {
                viewBtn.setVisible(false);
            } else {
                viewBtn.setVisible(true);
            }
            if (functionHM.get("addBtn") != null && !((AppPermission) functionHM.get("addBtn")).isFunctionFlag()) {
                addBtn.setVisible(false);
            } else {
                addBtn.setVisible(true);
            }
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }
}