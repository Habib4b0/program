/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

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
import com.stpl.app.cff.service.GtnAutomaticRelationServiceRunnable;
import com.stpl.app.cff.ui.ConsolidatedFinancialForecastUI;
import com.stpl.app.cff.ui.dataSelection.logic.DataSelectionLogic;
import com.stpl.app.cff.ui.dataSelection.logic.RelationShipFilterLogic;
import com.stpl.app.cff.ui.projectionresults.form.ProjectionResults;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.ResponsiveUtils;
import com.stpl.app.cff.util.SessionUtil;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.util.TableHeaderUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
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
	public CustomFieldGroup cffSearchBinder = new CustomFieldGroup(new BeanItem<>(cffSearchDTO));
	/**
	 * Bean container for result table.
	 */
	private final BeanItemContainer<CFFSearchDTO> resultsContainer = new BeanItemContainer<>(CFFSearchDTO.class);
	/**
	 * The approval details bean.
	 */
	public BeanItemContainer<ApprovalDetailsDTO> approvalDetailsBean = new BeanItemContainer<>(
			ApprovalDetailsDTO.class);
	/**
	 * The updateCycleBean bean.
	 */
	public List<ApprovalDetailsDTO> updateCycleBean = new ArrayList<>();
	/**
	 * The results bean.
	 */
	public BeanItemContainer<CFFResultsDTO> resultsBean = new BeanItemContainer<>(CFFResultsDTO.class);
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
	public static final String NO_RECORD_SELECTED = "No Record Selected.";
	private final RelationShipFilterLogic relationLogic = RelationShipFilterLogic.getInstance();
	private Future customerFuture;
	private Future productFuture;
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
		cffSearchBinder.setItemDataSource(new BeanItem<>(cffSearchDTO));
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
			financialForecastId.addValidator(new StringLengthValidator(
					"Financial Forecast Id should be less than 50 characters", 0, NumericConstants.FIFTY, true));
			financialForecastId.addValidator(
					new RegexpValidator(ConstantsUtil.ALPHA_NUM_CHARS, "Financial Forecast Id should be alphanumeric"));

			financialForecastName.setImmediate(true);
			financialForecastName.addValidator(new StringLengthValidator(
					"Financial Forecast Name should be less than 50 characters", 0, NumericConstants.FIFTY, true));
			financialForecastName.addValidator(new RegexpValidator(ConstantsUtil.ALPHA_NUM_CHARS,
					"Financial Forecast Name should be alphanumeric"));

			creationFromDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
			creationToDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
			approvalFromDate.setDateFormat(ConstantsUtil.DATE_FORMAT);
			approvalToDate.setDateFormat(ConstantsUtil.DATE_FORMAT);

			creationFrom.addStyleName(StringConstantsUtil.FROM_TO);
			creationTo.addStyleName(StringConstantsUtil.FROM_TO);
			approvalFrom.addStyleName(StringConstantsUtil.FROM_TO);
			approvalTo.addStyleName(StringConstantsUtil.FROM_TO);

			typeDdlb.focus();
			commonutil.loadComboBox(typeDdlb, ConstantsUtil.CFF_TYPE, false);
			commonutil.loadComboBoxforstatusddlb(statusDdlb, ConstantsUtil.WORKFLOW_STATUS, false);
			tableLogic.setContainerDataSource(resultsContainer);
			tableLogic.setPageLength(NumericConstants.TEN);
			tableLogic.sinkItemPerPageWithPageLength(false);
			resultTable.setVisibleColumns(TableHeaderUtils.getInstance().resultTableVisibleColumn);
			resultTable.setColumnHeaders(TableHeaderUtils.getInstance().resultTableHeader);
			resultTable.setColumnAlignment(
					TableHeaderUtils.getInstance().resultTableVisibleColumn[NumericConstants.FOUR],
					ExtCustomTable.Align.CENTER);
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
					return new SimpleDateFormat(StringConstantsUtil.MM_DD_YYYY);
				}
			});
			resultTable.setConverter("activeFromDate", new StringToDateConverter() {
				@Override
				public DateFormat getFormat(Locale locale) {
					return new SimpleDateFormat(StringConstantsUtil.MM_DD_YYYY);
				}
			});
			resultTable.setConverter("activeToDate", new StringToDateConverter() {
				@Override
				public DateFormat getFormat(Locale locale) {
					return new SimpleDateFormat(StringConstantsUtil.MM_DD_YYYY);
				}
			});

			final HorizontalLayout resultHorizontalLayout = ResponsiveUtils
					.getResponsiveControls(tableLogic.createControls());
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
				 * @param event
				 *            - Mouse Click event
				 */
				@Override
				public void buttonClick(final Button.ClickEvent event) {
					try {
						LOGGER.debug("Entering EXCEL Export Button Click");
						ConsolidatedFinancialForecastUI.EXCEL_CLOSE = true;
						CsvExportforPagedTable.createWorkSheet(resultTable.getColumnHeaders(),
								TableHeaderUtils.getInstance().resultTableVisibleColumnExcel, tableLogic,
								"Consolidated_Financial_Forecast");
						LOGGER.debug(" Ends  EXCEL Export Button Click");

					} catch (final Exception exception) {
						LOGGER.error(exception);
					}
				}
			});

			LOGGER.debug("Exists ConsolidatedFinancialForecastForm Configure Field method");
		} catch (final Exception ex) {
			java.util.logging.Logger.getLogger(ConsolidatedFinancialForecastForm.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	/**
	 * Generate button
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("addBtn")
	public void addBtnClickLogic(Button.ClickEvent event) {
		try {
			dto = new CFFSearchDTO();
			approvalDetailsBean = new BeanItemContainer<>(ApprovalDetailsDTO.class);
			resultsBean = new BeanItemContainer<>(CFFResultsDTO.class);
		} catch (final Exception e) {
			LOGGER.error(e);
		} finally {
			final SessionUtil sessionUtil = new SessionUtil();
			final SessionDTO session = sessionUtil.createSession();
			session.setProjectionId(dto.getCffMasterSid());
			session.setAction(Constants.ADD);
			approvalWindow = new CffApprovalDetailsForm(cffSearchBinder, dto, approvalDetailsBean, resultsBean, session,
					dataSelectionDto);
			UI.getCurrent().addWindow(approvalWindow);
			approvalWindow.addCloseListener(new Window.CloseListener() {
				@Override
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
					resultTable.setVisibleColumns(TableHeaderUtils.getInstance().resultTableVisibleColumn);
					resultTable.setColumnHeaders(TableHeaderUtils.getInstance().resultTableHeader);
				}
			});
		}
	}

	@UiHandler("editBtn")
	public void editBtnClickLogic(Button.ClickEvent event) {
		LOGGER.debug("Inside editBtn logic");
		try {
			final SessionUtil sessionUtil = new SessionUtil();
			final SessionDTO sessionDto = sessionUtil.createSession();
			dataSelectionDto = new DataSelectionDTO();
			dto = (CFFSearchDTO) resultTable.getValue();
			if (dto != null) {
				final int projectionIdValue = dto.getCffMasterSid();
				Long processId = 0L;
				final List<String> roleList = new ArrayList<>();
				final List processIdList = WorkflowPersistance.selectWFInstanceInfo(projectionIdValue);
				if (processIdList != null && !processIdList.isEmpty()) {
					processId = Long.valueOf(processIdList.get(0).toString());
				}
				final String userId = (String) VaadinSession.getCurrent().getAttribute("userId");
				final User userModel = UserLocalServiceUtil.getUser(Long.parseLong(userId));
				DSCalculationLogic.isValidWorkflowUser(userModel, roleList, processId);
				sessionDto.setAction("edit");
				sessionDto.setIsGenerated(Boolean.TRUE);
				sessionDto.setProcessId(processId);
				sessionDto.setWorkflowStatus(dto.getStatusDesc());
				if (dto != null && dto.getStatusDesc() != null && !Constants.APPROVED.equals(dto.getStatusDesc())) {
					final List list = new ArrayList();
					list.add(dto.getCffMasterSid());
					final List<Object[]> dsList = CommonQueryUtils.getAppData(list, "searchDS", null);
					final Object[] resultList = dsList.get(0);

					VaadinSession.getCurrent().setAttribute(StringConstantsUtil.PROJECTION_ID, projectionIdValue);
					sessionDto.setProjectionId(projectionIdValue);
					sessionDto.setAction(Constants.CommonConstants.ACTION_EDIT.getConstant());
					final DataSelectionLogic logic = new DataSelectionLogic();
					sessionDto.setCustomerHierarchyId(
							resultList[1] != null ? Integer.valueOf(resultList[1].toString()) : 0);
					sessionDto.setProductHierarchyId(resultList[NumericConstants.TWO] != null
							? Integer.valueOf(resultList[NumericConstants.TWO].toString()) : 0);
					sessionDto.setProductDescription(logic.getLevelValueMap(resultList[NumericConstants.FOUR] != null
							? Integer.valueOf(resultList[NumericConstants.FOUR].toString()) : 0));
					sessionDto.setCustomerRelationId(resultList[NumericConstants.THREE] != null
							? Integer.valueOf(resultList[NumericConstants.THREE].toString()) : 0);
					sessionDto.setCustRelationshipBuilderSid(resultList[NumericConstants.THREE] != null
							? resultList[NumericConstants.THREE].toString() : "0");
					sessionDto.setProductRelationId(resultList[NumericConstants.FOUR] != null
							? Integer.valueOf(resultList[NumericConstants.FOUR].toString()) : 0);
					sessionDto.setProdRelationshipBuilderSid(resultList[NumericConstants.FOUR] != null
							? resultList[NumericConstants.FOUR].toString() : "0");
					if (CommonUtils.isValueEligibleForLoading()) {
						sessionDto.setDedRelationshipBuilderSid(resultList[NumericConstants.TWENTY_FOUR] != null
								? resultList[NumericConstants.TWENTY_FOUR].toString() : "0");
					}
					final String marketType = dataLogic.getHelperValue("" + projectionIdValue);
					sessionDto.setMarketTypeValue(marketType);
					sessionDto.setProductLevelNumber(resultList[NumericConstants.FIVE] != null
							? resultList[NumericConstants.FIVE].toString() : "0");
					sessionDto.setCustomerLevelNumber(resultList[NumericConstants.SEVEN] != null
							? resultList[NumericConstants.SEVEN].toString() : "0");
					sessionDto.setCustomerDescription(logic.getLevelValueMap(resultList[NumericConstants.THREE] != null
							? Integer.valueOf(resultList[NumericConstants.THREE].toString()) : 0));
					sessionDto.setProjectionId(dto.getCffMasterSid());
					loadDataSelectionDTO(resultList);
					customerFuture = checkAndDoAutomaticUpdate(dataSelectionDto.getCustomerRelationShipVersionNo(),
							Integer.parseInt(dataSelectionDto.getCustomerHierSid()));
					productFuture = checkAndDoAutomaticUpdate(dataSelectionDto.getProductRelationShipVersionNo(),
							Integer.parseInt(dataSelectionDto.getProdHierSid()));
					boolean isCustRelationUpdate = (boolean) customerFuture.get();
					boolean isProdRelationUpdate = (boolean) productFuture.get();
					if(isCustRelationUpdate || isProdRelationUpdate) {
						AbstractNotificationUtils.getInfoNotification("Info", "Relationship used in this projection is updated");
					}
					sessionDto.setCustomerHierarchyVersion(dataSelectionDto.getCustomerHierVersionNo());
					sessionDto.setProductHierarchyVersion(dataSelectionDto.getProductHierVersionNo());
					sessionDto.setCustomerRelationVersion(dataSelectionDto.getCustomerRelationShipVersionNo());
					sessionDto.setProductRelationVersion(dataSelectionDto.getProductRelationShipVersionNo());
					sessionDto.setScreenName("CCP_HIERARCHY");
					CFFQueryUtils.createTempTables(sessionDto);
					dataSelectionDto.setCustomerHierSid(String.valueOf(sessionDto.getCustomerHierarchyId()));
					dataSelectionDto
							.setCustRelationshipBuilderSid(String.valueOf(sessionDto.getCustRelationshipBuilderSid()));
					dataSelectionDto
							.setProdRelationshipBuilderSid(String.valueOf(sessionDto.getProdRelationshipBuilderSid()));
					dataSelectionDto.setCustomerHierarchyLevel(sessionDto.getCustomerLevelNumber());
					dataSelectionDto.setProductHierarchyLevel(sessionDto.getProductLevelNumber());
					topLevelName = cffLogic.getTopLevelInHierarchy(dataSelectionDto.getCustomerHierSid());

					final Map<String, String> tempCustomerDescriptionMap = relationLogic.getLevelValueMap(
							dataSelectionDto.getCustRelationshipBuilderSid(),
							Integer.parseInt(dataSelectionDto.getCustomerHierSid()),
							dataSelectionDto.getCustomerHierVersionNo(),
							dataSelectionDto.getCustomerRelationShipVersionNo());
					final Map<String, String> tempProductDescriptionMap = relationLogic.getLevelValueMap(
							dataSelectionDto.getProdRelationshipBuilderSid(),
							Integer.parseInt(dataSelectionDto.getProdHierSid()),
							dataSelectionDto.getProductHierVersionNo(),
							dataSelectionDto.getProductRelationShipVersionNo());
					final int customerSelectedLevel = Integer
							.parseInt(dataSelectionDto.getCustomerHierarchyInnerLevel());
					final int productSelectedLeve = Integer.parseInt(dataSelectionDto.getProductHierarchyInnerLevel());
					final List<Leveldto> customerHierarchyLevelDefinitionList = relationLogic
							.getHierarchyLevelDefinition(Integer.parseInt(dataSelectionDto.getCustomerHierSid()),
									dataSelectionDto.getCustomerHierVersionNo());
					final List<Leveldto> productHierarchyLevelDefinitionList = relationLogic
							.getHierarchyLevelDefinition(Integer.parseInt(dataSelectionDto.getProdHierSid()),
									dataSelectionDto.getProductHierVersionNo());

					final List<Leveldto> customerItemIds = relationLogic.getRelationShipValues(
							dataSelectionDto.getProjectionId(), Boolean.TRUE, customerSelectedLevel,
							tempCustomerDescriptionMap);
					final List<Leveldto> productItemIds = relationLogic.getRelationShipValues(
							dataSelectionDto.getProjectionId(), Boolean.FALSE, productSelectedLeve,
							tempProductDescriptionMap);

					relationLogic.ccpHierarchyInsert(sessionDto.getCurrentTableNames(), customerItemIds, productItemIds,
							customerHierarchyLevelDefinitionList, productHierarchyLevelDefinitionList,
							dataSelectionDto.getCustomerRelationShipVersionNo(),
							dataSelectionDto.getProductRelationShipVersionNo());

					sessionDto.setCustomerLevelDetails(logic.getLevelValueDetails(sessionDto,
							dataSelectionDto.getCustRelationshipBuilderSid(), true));
					sessionDto.setProductLevelDetails(logic.getLevelValueDetails(sessionDto,
							dataSelectionDto.getProdRelationshipBuilderSid(), false));
					approvalDetailsBean = new BeanItemContainer<>(ApprovalDetailsDTO.class);
					resultsBean = new BeanItemContainer<>(CFFResultsDTO.class);
					loadSessionDTO();
					resultsBean.addAll(cffLogic.loadCffDetails(dto.getCffMasterSid()));
					if (CommonUtils.isValueEligibleForLoading()) {
						cffLogic.callDeductionCCPHierarchyInsertion(sessionDto, sessionDto.getCurrentTableNames(),
								Boolean.FALSE);
					}
					approvalDetailsBean.addAll(cffLogic.getApprovalDetailsForCff(dto.getCffMasterSid()));
					approvalWindow = new CffApprovalDetailsForm(cffSearchBinder, dto, approvalDetailsBean, resultsBean,
							sessionDto, dataSelectionDto);
					UI.getCurrent().addWindow(approvalWindow);
					approvalWindow.addCloseListener(new Window.CloseListener() {
						@Override
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
							resultTable.setVisibleColumns(TableHeaderUtils.getInstance().resultTableVisibleColumn);
							resultTable.setColumnHeaders(TableHeaderUtils.getInstance().resultTableHeader);
						}
					});
				} else if (dto != null && dto.getStatusDesc() != null
						&& Constants.APPROVED.equals(dto.getStatusDesc())) {
					AbstractNotificationUtils.getErrorNotification("Cannot Edit Approved Forecasts.",
							"You cannot edit an Approved forecast package.");
				} else if (dto == null) {
					AbstractNotificationUtils.getErrorNotification(NO_RECORD_SELECTED,
							"Please select a record to edit.");
				}
			} else {
				AbstractNotificationUtils.getErrorNotification(NO_RECORD_SELECTED, "Please select a record to EDIT.");
			}

		} catch (final Exception ex) {
			LOGGER.error(ex);
		}
	}

	private void loadSessionDTO() {
		final int projectionId = dto.getCffMasterSid();
		final Map<String, Object> parameters = new HashMap<>();
		final DataSelectionLogic logic = new DataSelectionLogic();
		if (projectionId != 0) {
			try {
				parameters.put(StringConstantsUtil.PROJECTION_ID, projectionId);
				final List list = logic.getProjection(projectionId);
				for (final Object list1 : list) {
					final Object[] temp = (Object[]) list1;
					sessionDTO.setProjectionName(String.valueOf(temp[0]));
					sessionDTO.setProdRelationshipBuilderSid(String.valueOf(temp[1]));
					sessionDTO.setCustRelationshipBuilderSid(String.valueOf(temp[NumericConstants.TWO]));
				}
				sessionDTO.setHasTradingPartner(logic.hasTradingPartner(projectionId));

			} catch (final Exception ex) {
				LOGGER.error(ex + " NonMandatedEditWindow - loadSessionDTO");
			}
		}
	}

	@UiHandler("viewBtn")
	public void viewBtnClickLogic(Button.ClickEvent event) {
		try {
			final SessionUtil sessionUtil = new SessionUtil();
			final DataSelectionLogic logic = new DataSelectionLogic();
			final SessionDTO sessionDTO = sessionUtil.createSession();
			sessionDTO.setAction("view");
			dataSelectionDto = new DataSelectionDTO();
			dto = (CFFSearchDTO) resultTable.getValue();
			if (dto != null) {
				final int projectionIdValue = dto.getCffMasterSid();
				sessionDTO.setProjectionId(dto.getCffMasterSid());
				VaadinSession.getCurrent().setAttribute(StringConstantsUtil.PROJECTION_ID, projectionIdValue);
				sessionDTO.setProjectionId(projectionIdValue);
				final List list = new ArrayList();
				list.add(dto.getCffMasterSid());
				final List<Object[]> dsList = CommonQueryUtils.getAppData(list, "searchDS", null);
				final Object[] resultList = dsList.get(0);
				sessionDTO
						.setCustomerHierarchyId(resultList[1] != null ? Integer.valueOf(resultList[1].toString()) : 0);
				sessionDTO.setProductHierarchyId(resultList[NumericConstants.TWO] != null
						? Integer.valueOf(resultList[NumericConstants.TWO].toString()) : 0);
				sessionDTO.setProductDescription(logic.getLevelValueMap(resultList[NumericConstants.FOUR] != null
						? Integer.valueOf(resultList[NumericConstants.FOUR].toString()) : 0));
				sessionDTO.setCustomerRelationId(resultList[NumericConstants.THREE] != null
						? Integer.valueOf(resultList[NumericConstants.THREE].toString()) : 0);
				sessionDTO.setCustRelationshipBuilderSid(resultList[NumericConstants.THREE] != null
						? resultList[NumericConstants.THREE].toString() : "0");
				sessionDTO.setProductRelationId(resultList[NumericConstants.FOUR] != null
						? Integer.valueOf(resultList[NumericConstants.FOUR].toString()) : 0);
				sessionDTO.setProdRelationshipBuilderSid(
						resultList[NumericConstants.FOUR] != null ? resultList[NumericConstants.FOUR].toString() : "0");
				if (CommonUtils.isValueEligibleForLoading()) {
					sessionDTO.setDedRelationshipBuilderSid(resultList[NumericConstants.TWENTY_FOUR] != null
							? resultList[NumericConstants.TWENTY_FOUR].toString() : "0");
				}
				final String marketType = dataLogic.getHelperValue("" + dto.getCffMasterSid());
				sessionDTO.setMarketTypeValue(marketType);
				sessionDTO.setProductLevelNumber(
						resultList[NumericConstants.FIVE] != null ? resultList[NumericConstants.FIVE].toString() : "0");
				sessionDTO.setCustomerLevelNumber(resultList[NumericConstants.SEVEN] != null
						? resultList[NumericConstants.SEVEN].toString() : "0");
				sessionDTO.setCustomerDescription(logic.getLevelValueMap(resultList[NumericConstants.THREE] != null
						? Integer.valueOf(resultList[NumericConstants.THREE].toString()) : 0));
				loadDataSelectionDTO(resultList);
				sessionDTO
						.setCustomerHierarchyId(resultList[1] != null ? Integer.valueOf(resultList[1].toString()) : 0);
				sessionDTO.setProductHierarchyId(resultList[NumericConstants.TWO] != null
						? Integer.valueOf(resultList[NumericConstants.TWO].toString()) : 0);
				sessionDTO.setProductDescription(logic.getLevelValueMap(resultList[NumericConstants.FOUR] != null
						? Integer.valueOf(resultList[NumericConstants.FOUR].toString()) : 0));
				sessionDTO.setCustomerRelationId(resultList[NumericConstants.THREE] != null
						? Integer.valueOf(resultList[NumericConstants.THREE].toString()) : 0);
				sessionDTO.setCustRelationshipBuilderSid(resultList[NumericConstants.THREE] != null
						? resultList[NumericConstants.THREE].toString() : "0");
				sessionDTO.setProductRelationId(resultList[NumericConstants.FOUR] != null
						? Integer.valueOf(resultList[NumericConstants.FOUR].toString()) : 0);
				sessionDTO.setProdRelationshipBuilderSid(
						resultList[NumericConstants.FOUR] != null ? resultList[NumericConstants.FOUR].toString() : "0");
				sessionDTO.setProductLevelNumber(
						resultList[NumericConstants.FIVE] != null ? resultList[NumericConstants.FIVE].toString() : "0");
				sessionDTO.setCustomerLevelNumber(resultList[NumericConstants.SEVEN] != null
						? resultList[NumericConstants.SEVEN].toString() : "0");
				sessionDTO.setCustomerDescription(logic.getLevelValueMap(resultList[NumericConstants.THREE] != null
						? Integer.valueOf(resultList[NumericConstants.THREE].toString()) : 0));
				sessionDTO.setScreenName("CCP_HIERARCHY");
				CFFQueryUtils.createTempTables(sessionDTO);
				sessionDTO.setCustomerHierarchyVersion(dataSelectionDto.getCustomerHierVersionNo());
				sessionDTO.setProductHierarchyVersion(dataSelectionDto.getProductHierVersionNo());
				sessionDTO.setCustomerRelationVersion(dataSelectionDto.getCustomerRelationShipVersionNo());
				sessionDTO.setProductRelationVersion(dataSelectionDto.getProductRelationShipVersionNo());
				dataSelectionDto.setCustomerHierSid(String.valueOf(sessionDTO.getCustomerHierarchyId()));
				dataSelectionDto
						.setCustRelationshipBuilderSid(String.valueOf(sessionDTO.getCustRelationshipBuilderSid()));
				dataSelectionDto
						.setProdRelationshipBuilderSid(String.valueOf(sessionDTO.getProdRelationshipBuilderSid()));
				dataSelectionDto.setCustomerHierarchyLevel(sessionDTO.getCustomerLevelNumber());
				dataSelectionDto.setProductHierarchyLevel(sessionDTO.getProductLevelNumber());
				topLevelName = cffLogic.getTopLevelInHierarchy(dataSelectionDto.getCustomerHierSid());

				final Map<String, String> tempCustomerDescriptionMap = relationLogic.getLevelValueMap(
						dataSelectionDto.getCustRelationshipBuilderSid(),
						Integer.parseInt(dataSelectionDto.getCustomerHierSid()),
						dataSelectionDto.getCustomerHierVersionNo(),
						dataSelectionDto.getCustomerRelationShipVersionNo());
				final Map<String, String> tempProductDescriptionMap = relationLogic.getLevelValueMap(
						dataSelectionDto.getProdRelationshipBuilderSid(),
						Integer.parseInt(dataSelectionDto.getProdHierSid()), dataSelectionDto.getProductHierVersionNo(),
						dataSelectionDto.getProductRelationShipVersionNo());
				final int customerSelectedLevel = Integer.parseInt(dataSelectionDto.getCustomerHierarchyInnerLevel());
				final int productSelectedLeve = Integer.parseInt(dataSelectionDto.getProductHierarchyInnerLevel());
				final List<Leveldto> customerHierarchyLevelDefinitionList = relationLogic.getHierarchyLevelDefinition(
						Integer.parseInt(dataSelectionDto.getCustomerHierSid()),
						dataSelectionDto.getCustomerHierVersionNo());
				final List<Leveldto> productHierarchyLevelDefinitionList = relationLogic.getHierarchyLevelDefinition(
						Integer.parseInt(dataSelectionDto.getProdHierSid()),
						dataSelectionDto.getProductHierVersionNo());

				final List<Leveldto> customerItemIds = relationLogic.getRelationShipValues(
						dataSelectionDto.getProjectionId(), Boolean.TRUE, customerSelectedLevel,
						tempCustomerDescriptionMap);
				final List<Leveldto> productItemIds = relationLogic.getRelationShipValues(
						dataSelectionDto.getProjectionId(), Boolean.FALSE, productSelectedLeve,
						tempProductDescriptionMap);

				relationLogic.ccpHierarchyInsert(sessionDTO.getCurrentTableNames(), customerItemIds, productItemIds,
						customerHierarchyLevelDefinitionList, productHierarchyLevelDefinitionList,
						dataSelectionDto.getCustomerRelationShipVersionNo(),
						dataSelectionDto.getProductRelationShipVersionNo());

				cffLogic.ccpHierarchyInsert(sessionDTO.getCurrentTableNames(), dataSelectionDto,
						cffLogic.getCustandProdSelection(sessionDTO.getProjectionId(),
								"EDIT_MODE_PROJECTION_CUST_SELECTION"),
						cffLogic.getCustandProdSelection(sessionDTO.getProjectionId(),
								"EDIT_MODE_PROJECTION_PROD_SELECTION"),
						topLevelName, Boolean.FALSE);

				sessionDTO.setCustomerLevelDetails(
						logic.getLevelValueDetails(sessionDTO, dataSelectionDto.getCustRelationshipBuilderSid(), true));
				sessionDTO.setProductLevelDetails(logic.getLevelValueDetails(sessionDTO,
						dataSelectionDto.getProdRelationshipBuilderSid(), false));
				approvalDetailsBean = new BeanItemContainer<>(ApprovalDetailsDTO.class);
				resultsBean = new BeanItemContainer<>(CFFResultsDTO.class);
				loadSessionDTO();
				resultsBean.addAll(cffLogic.loadCffDetails(dto.getCffMasterSid()));
				if (CommonUtils.isValueEligibleForLoading()) {
					cffLogic.callDeductionCCPHierarchyInsertion(sessionDTO, sessionDTO.getCurrentTableNames(),
							Boolean.FALSE);
				}
				approvalDetailsBean.addAll(cffLogic.getApprovalDetailsForCff(dto.getCffMasterSid()));
				approvalWindow = new CffApprovalDetailsForm(cffSearchBinder, dto, approvalDetailsBean, resultsBean,
						sessionDTO, dataSelectionDto);
				UI.getCurrent().addWindow(approvalWindow);
				approvalWindow.addCloseListener(new Window.CloseListener() {
					@Override
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
						resultTable.setVisibleColumns(TableHeaderUtils.getInstance().resultTableVisibleColumn);
						resultTable.setColumnHeaders(TableHeaderUtils.getInstance().resultTableHeader);
					}
				});
			} else {
				AbstractNotificationUtils.getErrorNotification(NO_RECORD_SELECTED, "Please select a record to view.");
			}
		} catch (final Exception ex) {
			LOGGER.error(ex);
		}
	}

	@UiHandler("searchBtn")
	public void searchBtnClickLogic(Button.ClickEvent event) {
		boolean pass = true;
		if ((financialForecastId.getValue().equals("null")
				|| StringUtils.isBlank(String.valueOf(financialForecastId.getValue())))
				&& (financialForecastName.getValue().equals("null")
						|| StringUtils.isBlank(String.valueOf(financialForecastName.getValue())))
				&& (typeDdlb.getValue() == null || String.valueOf(typeDdlb.getValue()).equals(ConstantsUtil.SELECT_ONE)
						|| typeDdlb.getValue() == null)
				&& (statusDdlb.getValue() == null
						|| String.valueOf(statusDdlb.getValue()).equals(ConstantsUtil.SELECT_ONE)
						|| statusDdlb.getValue() == null)
				&& creationFromDate.getValue() == null && creationToDate.getValue() == null
				&& approvalFromDate.getValue() == null && approvalToDate.getValue() == null) {
			pass = false;

			AbstractNotificationUtils.getErrorNotification("Search Criteria", " Please enter/select search criteria.");

		} else {
			if (creationFromDate.getValue() != null && creationToDate.getValue() != null
					&& creationFromDate.getValue().after(creationToDate.getValue())) {
				pass = false;
				AbstractNotificationUtils.getErrorNotification("Date Error",
						"Creation From date should be before Creation To date");
			}
			if (approvalFromDate.getValue() != null && approvalToDate.getValue() != null
					&& approvalFromDate.getValue().after(approvalToDate.getValue())) {
				pass = false;
				AbstractNotificationUtils.getErrorNotification("Date Error",
						"Approval From date should be before Approval To date");
			}
		}
		if (pass) {
			searchButtonClickLogic();
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
			 * @param buttonId
			 *            The buttonId of the pressed button.
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
		}.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the Search Criteria?");
	}

	/**
	 * Search button click logic.
	 *
	 * @param event
	 *            the event
	 */
	protected void searchButtonClickLogic() {
		try {
			cffSearchBinder.commit();
			resultsContainer.removeAllItems();
			if (!tableLogic.loadSetData(cffSearchDTO, Boolean.TRUE, Boolean.FALSE)) {
				MessageBox.showPlain(Icon.INFO, ConstantsUtil.ERROR,
						"There are no results that match the Search Criteria.", ButtonId.OK);
			} else {
				Notification.show("Search Completed");
			}
			resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
			resultTable.setFilterGenerator(new CFFFilterGenerator());
			resultTable.setImmediate(true);
		} catch (final FieldGroup.CommitException ex) {
			LOGGER.error(ex);
		}
	}

	public void loadDataSelectionDTO(Object[] resultList) {
		dataSelectionDto.setCustomerHierSid(resultList[1] != null ? resultList[1].toString() : "0");
		dataSelectionDto.setCustomerHierarchyLevel(
				resultList[NumericConstants.SEVEN] != null ? resultList[NumericConstants.SEVEN].toString() : "0");
		dataSelectionDto.setCustRelationshipBuilderSid(
				resultList[NumericConstants.THREE] != null ? resultList[NumericConstants.THREE].toString() : "0");
		dataSelectionDto.setProdRelationshipBuilderSid(
				resultList[NumericConstants.FOUR] != null ? resultList[NumericConstants.FOUR].toString() : "0");
		dataSelectionDto.setProdHierSid(
				resultList[NumericConstants.TWO] != null ? resultList[NumericConstants.TWO].toString() : "0");
		dataSelectionDto.setProductHierarchyLevel(
				resultList[NumericConstants.FIVE] != null ? resultList[NumericConstants.FIVE].toString() : "0");
		dataSelectionDto.setCustomerHierarchyInnerLevel(resultList[NumericConstants.SEVENTEEN] != null
				? resultList[NumericConstants.SEVENTEEN].toString() : "0");
		dataSelectionDto.setProductHierarchyInnerLevel(
				resultList[NumericConstants.EIGHTEEN] != null ? resultList[NumericConstants.EIGHTEEN].toString() : "0");
		dataSelectionDto.setCompanySid(
				resultList[NumericConstants.TWELVE] != null ? resultList[NumericConstants.TWELVE].toString() : "0");
		dataSelectionDto.setCustomerGrpSid(
				resultList[NumericConstants.THIRTEEN] != null ? resultList[NumericConstants.THIRTEEN].toString() : "0");
		dataSelectionDto.setProdGrpSid(
				resultList[NumericConstants.FIFTEEN] != null ? resultList[NumericConstants.FIFTEEN].toString() : "0");
		dataSelectionDto.setCustomerHierarchy(
				resultList[NumericConstants.SIX] != null ? resultList[NumericConstants.SIX].toString() : "0");
		dataSelectionDto.setProductHierarchy(
				resultList[NumericConstants.EIGHT] != null ? resultList[NumericConstants.EIGHT].toString() : "0");
		dataSelectionDto.setCustomerHierVersionNo(resultList[NumericConstants.NINETEEN] != null
				? Integer.valueOf(resultList[NumericConstants.NINETEEN].toString()) : 0);
		dataSelectionDto.setProductHierVersionNo(resultList[NumericConstants.TWENTY] != null
				? Integer.valueOf(resultList[NumericConstants.TWENTY].toString()) : 0);
		dataSelectionDto.setCompanyName(resultList[NumericConstants.TWENTY_ONE] != null
				? resultList[NumericConstants.TWENTY_ONE].toString() : " ");
		dataSelectionDto.setProjectionId(dto.getCffMasterSid());
		dataSelectionDto.setBusinessUnitSystemId(resultList[NumericConstants.TWENTY_TWO] != null
				? Integer.valueOf(resultList[NumericConstants.TWENTY_TWO].toString()) : 0);
		dataSelectionDto.setCustomerHierVersionNo(resultList[NumericConstants.TWENTY_FIVE] != null
				? Integer.valueOf(resultList[NumericConstants.TWENTY_FIVE].toString()) : 0);
		dataSelectionDto.setProductHierVersionNo(resultList[NumericConstants.TWENTY_SIX] != null
				? Integer.valueOf(resultList[NumericConstants.TWENTY_SIX].toString()) : 0);
		dataSelectionDto.setCustomerRelationShipVersionNo(resultList[NumericConstants.TWENTY_SEVEN] != null
				? Integer.valueOf(resultList[NumericConstants.TWENTY_SEVEN].toString()) : 0);
		dataSelectionDto.setProductRelationShipVersionNo(resultList[NumericConstants.TWENTY_EIGHT] != null
				? Integer.valueOf(resultList[NumericConstants.TWENTY_EIGHT].toString()) : 0);

	}

	public void configurePermission() {
		try {
			final StplSecurity stplSecurity = new StplSecurity();
			final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtil.USER_ID));
			final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId,
					"Consolidated Financial Forecast" + "," + "Landing screen");

			if (functionHM.get("searchBtn") != null && !functionHM.get("searchBtn").isFunctionFlag()) {
				searchBtn.setVisible(false);
			} else {
				searchBtn.setVisible(true);
			}

			if (functionHM.get("resetBtn") != null && !functionHM.get("resetBtn").isFunctionFlag()) {
				resetBtn.setVisible(false);
			} else {
				resetBtn.setVisible(true);
			}
			if (functionHM.get("editBtn") != null && !functionHM.get("editBtn").isFunctionFlag()) {
				editBtn.setVisible(false);
			} else {
				editBtn.setVisible(true);
			}
			if (functionHM.get("viewBtn") != null && !functionHM.get("viewBtn").isFunctionFlag()) {
				viewBtn.setVisible(false);
			} else {
				viewBtn.setVisible(true);
			}
			if (functionHM.get("addBtn") != null && !functionHM.get("addBtn").isFunctionFlag()) {
				addBtn.setVisible(false);
			} else {
				addBtn.setVisible(true);
			}
		} catch (final PortalException ex) {
			LOGGER.error(ex);
		} catch (final SystemException ex) {
			LOGGER.error(ex);
		}
	}
	
	private Future checkAndDoAutomaticUpdate(Object value, int hierarchyId) {
		GtnAutomaticRelationServiceRunnable wsClientRunnableTarget = new GtnAutomaticRelationServiceRunnable(value,
				hierarchyId);
		ExecutorService customerExecutorService = Executors.newSingleThreadExecutor();
		Future future = customerExecutorService.submit(wsClientRunnableTarget);
		customerExecutorService.shutdown();
		return future;
	}
}
