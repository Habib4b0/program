/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.dataSelection.form;

import static com.stpl.app.cff.util.Constants.IndicatorConstants.INDICATOR_CUSTOMER_HIERARCHY;
import static com.stpl.app.cff.util.Constants.IndicatorConstants.INDICATOR_LEVEL_CUSTOMER;
import static com.stpl.app.cff.util.Constants.IndicatorConstants.INDICATOR_LEVEL_NDC;
import static com.stpl.app.cff.util.Constants.IndicatorConstants.INDICATOR_PRIVATE_VIEW;
import static com.stpl.app.cff.util.Constants.IndicatorConstants.INDICATOR_PRODUCT_GROUP;
import static com.stpl.app.cff.util.Constants.IndicatorConstants.INDICATOR_PRODUCT_HIERARCHY;
import static com.stpl.app.cff.util.Constants.IndicatorConstants.INDICATOR_PUBLIC_VIEW;
import static com.stpl.app.cff.util.Constants.LabelConstants.PRIVATE_VIEW;
import static com.stpl.app.cff.util.Constants.LabelConstants.PUBLIC_VIEW;
import static com.stpl.app.cff.util.Constants.LabelConstants.SAVE_VIEW;
import static com.stpl.app.cff.util.Constants.LabelConstants.WINDOW_CUSTOMER_GROUP_LOOKUP;
import static com.stpl.app.cff.util.Constants.LabelConstants.WINDOW_CUSTOMER_HIERARCHY_LOOKUP;
import static com.stpl.app.cff.util.Constants.LabelConstants.WINDOW_PRODUCT_GROUP_LOOKUP;
import static com.stpl.app.cff.util.Constants.LabelConstants.WINDOW_PRODUCT_HIERARCHY_LOOKUP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.abstractCff.AbstractDataSelection;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.queryUtils.CFFQueryUtils;
import com.stpl.app.cff.security.StplSecurity;
import com.stpl.app.cff.service.GtnAutomaticRelationServiceRunnable;
import com.stpl.app.cff.ui.dataSelection.dto.CompanyDdlbDto;
import com.stpl.app.cff.ui.dataSelection.dto.RelationshipDdlbDto;
import com.stpl.app.cff.ui.dataSelection.logic.DataSelectionLogic;
import com.stpl.app.cff.ui.dataSelection.logic.RelationShipFilterLogic;
import com.stpl.app.cff.ui.projectionVariance.dto.ComparisonLookupDTO;
import com.stpl.app.cff.ui.projectionVariance.form.ComparisonLookup;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.DataSelectionUtil;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.util.UiUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.data.util.converter.Converter;
import com.vaadin.v7.ui.ComboBox;
import java.util.concurrent.ExecutorService;
import org.asi.ui.customtextfield.CustomTextField;

/**
 *
 * @author mohamed.hameed
 */
public class DataSelection extends AbstractDataSelection {

	private static final long serialVersionUID = 1905122041950251207L;
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DataSelection.class);
        public static final String STRING_COMMA = ",";
        private final Map<String,String> customViewInput=new HashMap<>();
        
	/**
	 * The data selection binder.
	 */
	public static final String SELECT_ONE = "-Select One-";
	public static final String TRADING_PARTNER = "Trading Partner";
	public static final String COMPANY_MASTER_TABLE = "COMPANY_MASTER";
	public static final String NULL = "null";
	private final String screenName = CommonUtils.MODULE_NAME;
	private Map<String, String> customerDescriptionMap = null;
	private Map<String, String> productDescriptionMap = null;
	private boolean dismantleCustomerSelection = true;
	private boolean dismantleProductSelection = true;
	private final List<Integer> customerBeanList = new ArrayList<>();
	private final List<Integer> productBeanList = new ArrayList<>();
	private final SessionDTO sessionDTO;
	private final CFFLogic cffLogic = new CFFLogic();
	private final TabSheet tabSheet;
	private String topLevelName = StringUtils.EMPTY;
	private final ThreadPool service = ThreadPool.getInstance();
	private final RelationShipFilterLogic relationLogic = RelationShipFilterLogic.getInstance();

	private Future customerFuture;
	private Future productFuture;

	public DataSelection(CustomFieldGroup dataSelectionBinder, DataSelectionDTO dataSelectionDTO, TabSheet tabSheet,
			SessionDTO sessionDTO) {
		super(dataSelectionBinder, CommonUtils.MODULE_NAME);
		this.dataSelectionDTO = dataSelectionDTO;
		this.tabSheet = tabSheet;
		this.sessionDTO = sessionDTO;
		customerDescriptionMap = sessionDTO.getCustomerDescription();
		productDescriptionMap = sessionDTO.getProductDescription();
		if (!Constants.ADD.equals(sessionDTO.getAction())) {
			LOGGER.debug("Inside Edit of Tab loading");
			configurePermission();
			configureOnLoading(dataSelectionDTO.getProjectionId(), dataSelectionDTO, sessionDTO);
			configureOnTabLoad(sessionDTO.getProjectionId(), sessionDTO);
			initializeCompanyCombobox();
			readModeOnly();
		}

		configureBusinessUnitDdlb();
                loadCFFEligibleDate();

	}

	public final void configureOnLoading(int projectionId, DataSelectionDTO dataSelectionDTO, SessionDTO session) {
		try {
			sessionDTO.setFromPeriod(dataSelectionDTO.getFromPeriod());
			sessionDTO.setToPeriod(dataSelectionDTO.getToPeriod());
			sessionDTO.setFromDate(dataSelectionDTO.getFromDate());
			sessionDTO.setToDate(dataSelectionDTO.getToDate());
			sessionDTO.setProductHierarchyId(session.getProductHierarchyId());
			initializeProductHierarchy(projectionId, String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
			initializeFromDto();
			if (!CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
				sessionDTO.setCustomerHierarchyId(session.getCustomerHierarchyId());
				initializeCustomerHierarchy(projectionId, String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
			}
			company.setValue(Integer.valueOf(dataSelectionDTO.getCompanySid()));
			getBusinessUnit().setValue(dataSelectionDTO.getBusinessUnitSystemId());
		} catch (final Property.ReadOnlyException | NumberFormatException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	protected void productLevelDdlbValueChange(String selectedLevel, boolean flag) {
		loadFilteredProductSelection(selectedLevel);
	}

	@Override
	protected void customerHierarchyLookUp()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		final HierarchyLookup customerHierarchyLookupWindow = new HierarchyLookup(
				INDICATOR_CUSTOMER_HIERARCHY.getConstant(), WINDOW_CUSTOMER_HIERARCHY_LOOKUP.getConstant(),
				customerHierarchy, customerHierarchyDto);
		UI.getCurrent().addWindow(customerHierarchyLookupWindow);
		customerHierarchyLookupWindow.addCloseListener(new Window.CloseListener() {
			@Override
			public void windowClose(Window.CloseEvent e) {
				if (customerHierarchyLookupWindow.getHierarchyDto() != null) {
					final HierarchyLookupDTO lookupDto = customerHierarchyLookupWindow.getHierarchyDto();
					customerHierarchyDto = lookupDto;
					loadRelationDdlb(customerHierarchyDto.getHierarchyId(), null, customerRelation);
					resetCustomerLevel();
					resetSecondCustomerLevel();
					availableCustomer.removeAllItems();
					availableCustomerContainer.removeAllItems();
					selectedCustomer.removeAllItems();
					selectedCustomerContainer.removeAllItems();
					customerGroup.setValue(StringUtils.EMPTY);
					dataSelectionDTO.setCustomerGrpSid(null);
					groupFilteredCompanies = null;
					setCustomerForecastLevelNullSelection();
					setCustomerRelationNullSelection();
					dataSelectionDTO.setCustomerHierSid(String.valueOf(customerHierarchyDto.getHierarchyId()));
				}
			}
		});
	}

	@Override
	protected void productHierarchyLookUp()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		final HierarchyLookup productHieLookupWindow = new HierarchyLookup(
				INDICATOR_PRODUCT_HIERARCHY.getConstant(), WINDOW_PRODUCT_HIERARCHY_LOOKUP.getConstant(),
				productHierarchy, productHierarchyDto);
		UI.getCurrent().addWindow(productHieLookupWindow);
		productHieLookupWindow.addCloseListener(new Window.CloseListener() {
			@Override
			public void windowClose(Window.CloseEvent e) {
				if (productHieLookupWindow.getHierarchyDto() != null) {
					final HierarchyLookupDTO prodLookpu = productHieLookupWindow.getHierarchyDto();
					productHierarchyDto = prodLookpu;
					companiesInProdHier = new ArrayList<>();
					loadRelationDdlb(productHierarchyDto.getHierarchyId(), null, productRelation);
					resetProductLevel();
					resetSecondProductLevel();
					setProductForecastLevelNullSelection();
					setProductRelationNullSelection();
					availableProduct.removeAllItems();
					availableProductContainer.removeAllItems();
					selectedProduct.removeAllItems();
					selectedProductContainer.removeAllItems();
					dataSelectionDTO.setProdGrpSid(null);
					productGroup.setValue(StringUtils.EMPTY);
					groupFilteredItems = null;

				}
			}
		});
	}

	public final void readModeOnly() {
		company.setEnabled(false);
		productRelation.setEnabled(false);
		customerHierarchy.setEnabled(false);
		customerRelation.setEnabled(false);
		customerLevel.setEnabled(false);
		customerGroup.setEnabled(false);
		level.setEnabled(false);
		productHierarchy.setEnabled(false);
		productRelation.setEnabled(false);
		productLevel.setEnabled(false);
		productGroup.setEnabled(false);
		productlevelDdlb.setEnabled(false);
		privateView.setEnabled(false);
		publicView.setEnabled(false);
		moveLeftBtn.setEnabled(false);
		moveLeftProduct.setEnabled(false);
		moveRightBtn.setEnabled(false);
		moveRightProduct.setEnabled(false);
		all.setEnabled(false);
		allProductBtn.setEnabled(false);
		generateBtn.setEnabled(false);
		resetBtn.setEnabled(false);
		saveViewBtn.setEnabled(false);
		deleteViewBtn.setEnabled(false);
		getBusinessUnit().setEnabled(false);

	}

	@Override
	protected void customerLevelValueChange(Property.ValueChangeEvent event, boolean flag) {
		LOGGER.debug("Logging - customerLevelValueChange");
		customerInnerLevelContainer.removeAllItems();
		if (event.getProperty().getValue() != null
				&& !SELECT_ONE.equals(String.valueOf(event.getProperty().getValue()))) {
			String selectedLevel = String.valueOf(event.getProperty().getValue());
			setSelectedCustomerLevel(selectedLevel);
			DataSelectionLogic logic = new DataSelectionLogic();
			logic.loadCustomerForecastLevel(customerHierarchyDto.getHierarchyId(),
					Integer.parseInt(customerRelationVersionComboBox.getValue().toString()));
			String[] val = selectedLevel.split(" ");
			int forecastLevel = Integer.parseInt(val[1]);
			sessionDTO.setCustomerLevelNumber(String.valueOf(forecastLevel));
			customerInnerLevelContainer.removeAllItems();
			selectedCustomer.removeAllItems();
			selectedCustomerContainer.removeAllItems();
			customerBeanList.clear();
			for (int i = 1; i <= forecastLevel; i++) {
				String levelName = innerCustLevels.get(i - 1).getLevel();
				customerInnerLevelContainer.addItem(StringConstantsUtil.LEVEL_SPACE + i + " - " + levelName);
			}
			level.setContainerDataSource(customerInnerLevelContainer);
			setCustomerLevelNullSelection();
		} else if (event.getProperty().getValue() == null || (event.getProperty().getValue() != null
				&& SELECT_ONE.equals(String.valueOf(event.getProperty().getValue())))) {
			customerInnerLevelContainer.removeAllItems();
			availableCustomer.removeAllItems();
			availableCustomerContainer.removeAllItems();
			selectedCustomer.removeAllItems();
			selectedCustomerContainer.removeAllItems();
			setCustomerLevelNullSelection();
			customerBeanList.clear();
		}
	}

        @Override
	protected void levelValueChangeListener(Object value) {

		LOGGER.debug("customer inner Level - ValueChangeListener: {} ", value);
		try {
			availableCustomerContainer.removeAllItems();
			String levelName = StringConstantsUtil.LEVEL;
			int forecastLevel = 0;
			if (value != null && customerRelation.getValue() != null
					&& !SELECT_ONE.equals(customerRelation.getValue())) {
				customerFuture.get();
				int relationVersionNo = Integer.parseInt(
						customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
				int hierarchyVersionNo = Integer.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
				customerDescriptionMap = relationLogic.getLevelValueMap(String.valueOf(customerRelation.getValue()),
						customerHierarchyDto.getHierarchyId(), hierarchyVersionNo, relationVersionNo);
				String selectedLevel = String.valueOf(value);
				String relationshipSid = String.valueOf(customerRelation.getValue());
				String[] val = selectedLevel.split(" ");
				forecastLevel = Integer.parseInt(val[1]);
                                dataSelectionDTO.setSelectedCustomerLevelNo(selectedLevel);
				List<Leveldto> customerHierarchyLevelDefinitionList = relationLogic
						.getHierarchyLevelDefinition(customerHierarchyDto.getHierarchyId(), hierarchyVersionNo);
				Leveldto selectedHierarchyLevelDto = customerHierarchyLevelDefinitionList.get(forecastLevel - 1);
				List<String> tempGroupFileter = groupFilteredCompanies == null ? Collections.<String>emptyList()
						: groupFilteredCompanies;
				List<Leveldto> resultedLevelsList = relationLogic.loadAvailableCustomerlevel(selectedHierarchyLevelDto,
						Integer.parseInt(relationshipSid), tempGroupFileter, "",
						"", relationVersionNo, cffEligibleDate.getValue(), customerDescriptionMap);
				if (selectedHierarchyLevelDto.getLevel() != null) {
					levelName = selectedHierarchyLevelDto.getLevel();
				}
				availableCustomerContainer.addAll(resultedLevelsList);
				availableCustomer.setContainerDataSource(availableCustomerContainer);
				availableCustomer.setVisibleColumns(StringConstantsUtil.DISPLAY_VALUE);
				availableCustomer.setColumnHeaders(levelName);
				availableCustomer.setFilterBarVisible(true);
				availableCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
				availableCustomer.setStyleName(StringConstantsUtil.FILTER_TABLE);
			}
		} catch (CloneNotSupportedException | InterruptedException | NumberFormatException | ExecutionException ex) {
			LOGGER.error(" level  ValueChangeListener= {}", ex);
		}
	}

	@Override
	protected void productLevelValueChange(Object value, boolean flag) {
		LOGGER.debug("Logging - productLevelValueChange");
		productInnerLevelContainer.removeAllItems();
		if (value != null && !SELECT_ONE.equals(String.valueOf(value))) {
			final String selectedLevel = String.valueOf(value);
			setSelectedProductLevel(selectedLevel);
			final DataSelectionLogic logic = new DataSelectionLogic();
			logic.loadCustomerForecastLevel(productHierarchyDto.getHierarchyId(),
					Integer.parseInt(productRelationVersionComboBox.getValue().toString()));
			final String[] val = selectedLevel.split(" ");
			final int forecastLevel = Integer.parseInt(val[1]);
			sessionDTO.setProductLevelNumber(String.valueOf(forecastLevel));
			productInnerLevelContainer.removeAllItems();
			selectedProduct.removeAllItems();
			selectedProductContainer.removeAllItems();
			productBeanList.clear();
			for (int i = 1; i <= forecastLevel; i++) {
				final String levelName = innerProdLevels.get(i - 1).getLevel();
				productInnerLevelContainer.addItem(StringConstantsUtil.LEVEL_SPACE + i + " - " + levelName);
			}
			productlevelDdlb.setContainerDataSource(productInnerLevelContainer);
			setProductLevelNullSelection();
		} else if (value == null || SELECT_ONE.equals(String.valueOf(value))) {
			productInnerLevelContainer.removeAllItems();
			availableProduct.removeAllItems();
			availableProductContainer.removeAllItems();
			selectedProduct.removeAllItems();
			selectedProductContainer.removeAllItems();
			productBeanList.clear();
			setProductLevelNullSelection();
		}
	}

	@Override
	protected void customerRelationValueChange(Object value) {
		if (value != null && !SELECT_ONE.equals(String.valueOf(value))) {
			try {
				availableCustomer.removeAllItems();
				availableCustomerContainer.removeAllItems();
				selectedCustomer.removeAllItems();
				selectedCustomerContainer.removeAllItems();
				setCustomerForecastLevelNullSelection();
				setCustomerLevelNullSelection();
				setRelationshipBuilderSids(String.valueOf(customerRelation.getValue()));
				customerFuture = checkAndDoAutomaticUpdate(customerRelationVersionComboBox.getValue(),
						customerHierarchyDto.getHierarchyId());
                                customViewInput.put(ConstantsUtil.CUSTVER, String.valueOf(customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue())));
                                customViewInput.put(ConstantsUtil.CUSTOMER_SID_LITERAL, String.valueOf(customerRelation.getValue()));
                                comparison.setEnabled(false);
                                loadCustomViewDropDown(customViewDdlb);
			} catch (Exception ex) {
				LOGGER.error(" in customerRelation value change= {}", ex);
			}
		} else if ((value == null || SELECT_ONE.equals(String.valueOf(value)))) {
			availableCustomer.removeAllItems();
			availableCustomerContainer.removeAllItems();
			selectedCustomer.removeAllItems();
			selectedCustomerContainer.removeAllItems();
			customerInnerLevelContainer.removeAllItems();
			setCustomerForecastLevelNullSelection();
			setCustomerLevelNullSelection();
			customerDescriptionMap = null;
		}
		customerGroup.setValue(StringUtils.EMPTY);
		selectedCustomerGroupDTO = new GroupDTO();
		dataSelectionDTO.setCustomerGrpSid(null);
		groupFilteredCompanies = null;
	}

	@Override
	protected void productRelationValueChange(Object value) {
		LOGGER.debug("productRelation - ValueChangeListener: {} ", value);
		if (value != null && !SELECT_ONE.equals(String.valueOf(value))) {
			try {
				selectedProduct.removeAllItems();
				selectedProductContainer.removeAllItems();
				availableProduct.removeAllItems();
				availableProductContainer.removeAllItems();
				setProductForecastLevelNullSelection();
				setProductLevelNullSelection();
				setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));
				productFuture = checkAndDoAutomaticUpdate(productRelation.getValue(),
						productHierarchyDto.getHierarchyId());
				int relationVersionNo = Integer.parseInt(
						productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
				int hierarchyVersionNo = Integer.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
				productDescriptionMap = relationLogic.getLevelValueMap(String.valueOf(productRelation.getValue()),
						productHierarchyDto.getHierarchyId(), hierarchyVersionNo, relationVersionNo);
                                customViewInput.put(ConstantsUtil.PRODVER, String.valueOf(relationVersionNo));
                                customViewInput.put(ConstantsUtil.PROD_SID_LITERAL, String.valueOf(productRelation.getValue()));
                                comparison.setEnabled(false);
                                loadCustomViewDropDown(customViewDdlb);
			} catch (NumberFormatException ex) {
				LOGGER.error(" in productRelation value change= {}", ex);
			}
		} else  {
			selectedProduct.removeAllItems();
			selectedProductContainer.removeAllItems();
			availableProduct.removeAllItems();
			availableProductContainer.removeAllItems();
			productInnerLevelContainer.removeAllItems();
			setProductForecastLevelNullSelection();
			setProductLevelNullSelection();
			productDescriptionMap = null;
		}
		dataSelectionDTO.setProdGrpSid(null);
		productGroup.setValue(StringUtils.EMPTY);
		selectedProductGroupDTO = new GroupDTO();
		groupFilteredItems = null;
	}

	@Override
	protected void generateButtonLogic() {
		try {
			if (selectedCustomer.size() <= 0 || selectedProduct.size() <= 0 || company.getValue() == null
					|| SELECT_ONE.equals(company.getValue())) {
				AbstractNotificationUtils.getErrorNotification("Selection Criteria",
						"Not all required fields have been populated. Please try again.");
				return;
			}

			bindDataselectionDtoToSave();
			int projectionIdValue = cffLogic.saveCFFMaster(dataSelectionDTO, BooleanConstant.getFalseFlag(), 0,sessionDTO);
			VaadinSession.getCurrent().setAttribute("projectionId", projectionIdValue);
			dataSelectionDTO.setProjectionId(projectionIdValue);
			if (projectionIdValue != 0) {
                                sessionDTO.setStatusName("G");
				sessionDTO.setProjectionId(projectionIdValue);
				sessionDTO.setProductLevelNumber(String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
				sessionDTO.setCustomerLevelNumber(String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
				sessionDTO.setAction(Constants.ADD);
				sessionDTO.setFromDate(dataSelectionDTO.getFromDate());
				sessionDTO.setToDate(dataSelectionDTO.getToDate());
				sessionDTO.setProductHierarchyId(Integer.parseInt(dataSelectionDTO.getProdHierSid()));
				sessionDTO.setProductRelationId(Integer.parseInt(dataSelectionDTO.getProdRelationshipBuilderSid()));
				sessionDTO.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
				sessionDTO.setProductDescription(productDescriptionMap);
				sessionDTO.setCustomerHierarchyId(0);
				sessionDTO.setCustomerDescription(customerDescriptionMap);
				sessionDTO.setCustomerHierarchyId(Integer.parseInt(dataSelectionDTO.getCustomerHierSid()));
				sessionDTO.setProductHierarchyId(Integer.parseInt(dataSelectionDTO.getProdHierSid()));
				sessionDTO.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
                                sessionDTO.setFrequency(String.valueOf(frequencyDataSelection.getValue()));
				Object[] obj = cffLogic.deductionRelationBuilderId(dataSelectionDTO.getProdRelationshipBuilderSid());
				sessionDTO.setDedRelationshipBuilderSid(obj[0].toString());
                                List versionNoList = cffLogic.getDeductionVersionNoList(sessionDTO.getDedRelationshipBuilderSid());
                                                    sessionDTO.setDeductionRelationVersion((int) versionNoList.get(0));
				sessionDTO.setCompanySystemId((Integer) company.getValue());
				sessionDTO.setCustomerHierarchyVersion(dataSelectionDTO.getCustomerHierVersionNo());
				sessionDTO.setProductHierarchyVersion(dataSelectionDTO.getProductHierVersionNo());
				sessionDTO.setCustomerRelationVersion(dataSelectionDTO.getCustomerRelationShipVersionNo());
				sessionDTO.setProductRelationVersion(dataSelectionDTO.getProductRelationShipVersionNo());
				sessionDTO.setScreenName("CCP_HIERARCHY");
				CFFQueryUtils.createTempTables(sessionDTO);
				relationLogic.ccpHierarchyInsert(sessionDTO.getCurrentTableNames(),
						selectedCustomerContainer.getItemIds(), selectedProductContainer.getItemIds(),
						dataSelectionDTO);
				insertCffDetailsUsingExecutorService(projectionIdValue, sessionDTO.getCurrentTableNames());
				sessionDTO.setCustomerLevelDetails(
						cffLogic.getLevelValueDetails(sessionDTO, customerRelation.getValue(), true));
				sessionDTO.setProductLevelDetails(
						cffLogic.getLevelValueDetails(sessionDTO, productRelation.getValue(), false));
                                
				if (sessionDTO.getFuture() != null) {
					sessionDTO.getFuture().get();
					cffLogic.callDeductionCCPHierarchyInsertion(sessionDTO, sessionDTO.getCurrentTableNames(),
							BooleanConstant.getFalseFlag());
				}
                            StringBuilder br = new StringBuilder();
                            
                                if (sessionDTO.getComparisonLookupData() != null) {
                                    for (ComparisonLookupDTO checkedSalesValue : ((ComparisonLookupDTO) sessionDTO.getComparisonLookupData()).getSelected()) {
                                        br.append(checkedSalesValue.getProjectionId()).append(Constants.COMMA_CHAR);
                                }
                                sessionDTO.setPriorProjectionId(br.replace(br.lastIndexOf(Constants.COMMA), br.length(), StringUtils.EMPTY).toString());
                                }
                                String keyValue = deductionDdlb.getItemCaption(deductionDdlb.getValue());
                                keyValue = keyValue.startsWith("UDC") ? keyValue.replace(" ", StringUtils.EMPTY) : keyValue;

                                sessionDTO.setDeductionName(keyValue);
                                sessionDTO.setDeductionNo(Integer.parseInt(String.valueOf(deductionDdlb.getValue())));
                                sessionDTO.setCustomDescription(cffLogic.getRelationshipDetailsCustom(sessionDTO, String.valueOf(customViewDdlb.getValue())));
                                sessionDTO.setDeductionLevelDescription(cffLogic.getRelationshipDetailsDeductionCustom(sessionDTO, String.valueOf(customViewDdlb.getValue())));
                                cffLogic.loadSalesTempTableInThread(sessionDTO,true);
                                cffLogic.loadDiscountTempTableInThread(sessionDTO, true);
                                cffLogic.callCFFHierarachyDetailsProcedure(sessionDTO, true);

			}

			tabSheet.setSelectedTab(1);
			sessionDTO.setIsGenerated(BooleanConstant.getTrueFlag());

		} catch (InterruptedException | NumberFormatException | ExecutionException ex) {
			Logger.getLogger(DataSelection.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
   

	@Override
	protected void loadPublicView() {
		final PrivatePublicView publicViewLookup = new PrivatePublicView(INDICATOR_PUBLIC_VIEW.getConstant(),
				publicView, PUBLIC_VIEW.getConstant(), screenName);
		UI.getCurrent().addWindow(publicViewLookup);
		publicViewLookup.addCloseListener(new Window.CloseListener() {
			@Override
			public void windowClose(Window.CloseEvent e) {
				if (publicViewLookup.getViewDTO() != null) {
					ViewDTO viewDTO = publicViewLookup.getViewDTO();
					setViewDTO(viewDTO);
					publicView.setValue(viewDTO.getViewName().trim());
					try {
						loadView(viewDTO);
					} catch (SystemException ex) {
						LOGGER.error(" publicView close= {}", ex);
					}
				}

			}
		});
	}

	@Override
	protected void loadPrivateView() {
		final PrivatePublicView privateViewLookup = new PrivatePublicView(INDICATOR_PRIVATE_VIEW.getConstant(),
				privateView, PRIVATE_VIEW.getConstant(), screenName);
		UI.getCurrent().addWindow(privateViewLookup);
		privateViewLookup.addCloseListener(new Window.CloseListener() {
			@Override
			public void windowClose(Window.CloseEvent e) {
				if (privateViewLookup.getViewDTO() != null) {
					ViewDTO viewDTO = privateViewLookup.getViewDTO();
					setViewDTO(viewDTO);
					privateView.setValue(viewDTO.getViewName().trim());
					try {
						loadView(viewDTO);
					} catch (SystemException ex) {
						LOGGER.error(" privateView close= {}", ex);
					}
				}

			}
		});
	}

	@Override
	protected void loadProductGroup() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		final CustomerProductGroupLookup productGroupLookupWindow = new CustomerProductGroupLookup(
				INDICATOR_PRODUCT_GROUP.getConstant(), WINDOW_PRODUCT_GROUP_LOOKUP.getConstant(), productGroup,
				DataSelectionUtil.getItemSidFromHierarchy(getItemSidFromHierarchy()));
		UI.getCurrent().addWindow(productGroupLookupWindow);
		productGroupLookupWindow.addCloseListener(new Window.CloseListener() {
			@Override
			public void windowClose(Window.CloseEvent e) {
				if (productGroupLookupWindow.getSelectedProdHierarchy() != null) {
					selectedProductGroupDTO = productGroupLookupWindow.getSelectedProdHierarchy();
					groupFilteredItems = productGroupLookupWindow.getFilteredSids();
					if (productlevelDdlb.getValue() != null && !String.valueOf(SELECT_ONE)
							.equalsIgnoreCase(String.valueOf(productlevelDdlb.getValue()))) {
						loadFilteredProductSelection(String.valueOf(productlevelDdlb.getValue()));
					}
				}
			}
		});
	}

	@Override
	protected void loadCustomerGroup() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		final CustomerProductGroupLookup customerGroupLookupWindow = new CustomerProductGroupLookup(
				Constants.CUSTOMER_GROUP, WINDOW_CUSTOMER_GROUP_LOOKUP.getConstant(), customerGroup,
				DataSelectionUtil.getCustomerSidFromHierarchy(getCustomersFromHierarchy()));
		UI.getCurrent().addWindow(customerGroupLookupWindow);
		customerGroupLookupWindow.addCloseListener(new Window.CloseListener() {
			@Override
			public void windowClose(Window.CloseEvent e) {
				if (customerGroupLookupWindow.getSelectedCustHierarchy() != null) {
					selectedCustomerGroupDTO = customerGroupLookupWindow.getSelectedCustHierarchy();
					groupFilteredCompanies = customerGroupLookupWindow.getFilteredSids();
					filterCustomerOnGroupSelect();
				}
			}
		});
	}

	@Override
	protected void saveViewButtonlogic() {
		try {
			bindDataselectionDtoToSave();
			final List<Leveldto> selectedCustomersList = new ArrayList<>();
			final List<Leveldto> selectedProductsList = new ArrayList<>();
			for (int i = 0; i < selectedCustomerContainer.size(); i++) {
				final Leveldto cpDto = (Leveldto) selectedCustomerContainer.getIdByIndex(i);
				selectedCustomersList.add(cpDto);
			}
			for (int i = 0; i < selectedProductContainer.size(); i++) {
				final Leveldto cpDto = (Leveldto) selectedProductContainer.getIdByIndex(i);
				selectedProductsList.add(cpDto);
			}
			if (privateView.getValue() != null && privateView.getValue() != "") {
				dataSelectionDTO.setViewType("private");
			} else if (publicView.getValue() != null) {
				dataSelectionDTO.setViewType("public");
			} else {
				dataSelectionDTO.setViewType("");
			}
			List<String> customerListEndSids = DataSelectionUtil
					.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer));
			List<String> productListEndSids = DataSelectionUtil
					.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));
			final SaveViewPopup saveViewPopup = new SaveViewPopup(SAVE_VIEW.getConstant(), dataSelectionDTO,
					selectedCustomersList, selectedProductsList,
					getCustomerHierarchyEndLevels(selectedCustomerContainer),
					getProductHierarchyEndLevelsHierNo(selectedProductContainer), viewDTO, customerListEndSids,
					productListEndSids, sessionDTO);
			UI.getCurrent().addWindow(saveViewPopup);
		} catch (IllegalArgumentException | NullPointerException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	protected void moveLeftButtonLogic() {
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			if (availableCustomer.getValue() != null) {
				int customerHierarchyVersionNo = Integer
						.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
				int customerRelationVersionNo = Integer.parseInt(
						customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
				int forecastLevel = 0;
				if (level.getValue() != null) {
					forecastLevel = UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]);
					dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(forecastLevel));
					dataSelectionDTO.setCustomerHierarchyInnerLevel(String.valueOf(forecastLevel));
				}
				Object item = availableCustomer.getValue();

				if (selectedCustomerContainer.size() > 0) {
					if (selectedCustomer.getValue() != null) {
						// When a value selected in selected customer tree
						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						String currentHierarchyNoString = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						List<String> hierarchyNos = new ArrayList<>();
						List<Leveldto> newParentLevels = null;
						List<Leveldto> newChildLevels = null;
						hierarchyNos.add(hierarchyNo + ".");
						int pos = 0;
						while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos != -1) {
								hierarchyNo = hierarchyNo.substring(0, pos);
							}
							hierarchyNos.add(hierarchyNo + ".");
						}
						Collections.reverse(hierarchyNos);
						List<String> selectedHierarchyNos = new ArrayList<>();
						for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
							if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
								selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
							}
						}
						List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
								selectedHierarchyNos);
						List<String> removeValues = new ArrayList<>();
						for (String uncommonHierValue : uncommonValues) {
							if (selectedHierarchyNos.contains(uncommonHierValue)) {
								removeValues.add(uncommonHierValue);
							}
						}
						if (!removeValues.isEmpty()) {
							uncommonValues.removeAll(removeValues); // At this
																	// point,
																	// uncommonValues
																	// should
																	// contain
																	// only 1
																	// value
																	// since
																	// only one
																	// value is
																	// selected
																	// to be
																	// moved in
																	// available
																	// table.
						}
						if (!uncommonValues.isEmpty()) {
							newParentLevels = logic.getParentLevelsWithHierarchyNo(
									UiUtils.stringListToString(uncommonValues), customerDescriptionMap,
									customerHierarchyVersionNo, customerRelationVersionNo);
						}
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNoString,
								UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
								customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo);
						if (newParentLevels != null) {
							int pos2 = 0;
							String parentHierarchyNo;
							Leveldto parent = null;
							for (Leveldto newLevel : newParentLevels) {
								String tempHNo = newLevel.getHierarchyNo();
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos2 != -1) {
									parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
								} else {
									parentHierarchyNo = tempHNo + ".";
								}
								if (customerBeanList.isEmpty()
										|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
									customerBeanList.add(newLevel.getRelationshipLevelSid());
									selectedCustomerContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedCustomerContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedCustomerContainer.setChildrenAllowed(newLevel, false);
									}
								}

								if (!StringUtils.isBlank(parentHierarchyNo)) {
									for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
										if (parentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
											parent = selectedLevel;
											break;
										}
									}
								}
								selectedCustomerContainer.setParent(newLevel, parent);
								parent = newLevel;
							}
							if (!newChildLevels.isEmpty()) {
								int pos3 = 0;
								String childHierarchyNo;
								Leveldto childsParent = null;
								for (Leveldto newLevel : newChildLevels) {
									String tempHNo = newLevel.getHierarchyNo();
									if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
										tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
									}
									pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos3 != -1) {
										childHierarchyNo = tempHNo.substring(0, pos3) + ".";
									} else {
										childHierarchyNo = tempHNo + ".";
									}
									if (customerBeanList.isEmpty()
											|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
										customerBeanList.add(newLevel.getRelationshipLevelSid());
										selectedCustomerContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedCustomerContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedCustomerContainer.setChildrenAllowed(newLevel, false);
										}
									}

									if (!StringUtils.isBlank(childHierarchyNo)) {
										for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
											if (childHierarchyNo
													.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
												childsParent = selectedLevel;
												break;
											}
										}
									}
									selectedCustomerContainer.setParent(newLevel, childsParent);
								}
							}
						}

					} else {
						// When no value selected in selected customer tree
						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						List<String> hierarchyNos = new ArrayList<>();
						List<Leveldto> newParentLevels = null;
						List<Leveldto> newChildLevels = null;
						hierarchyNos.add(hierarchyNo + ".");
						int pos = 0;
						String selectedParentHierarchyNo = StringUtils.EMPTY;
						Leveldto selectedParent = null;
						while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos != -1) {
								hierarchyNo = hierarchyNo.substring(0, pos);
							}
							hierarchyNos.add(hierarchyNo + ".");
						}
						Collections.reverse(hierarchyNos);
						List<String> selectedHierarchyNos = new ArrayList<>();
						for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
							if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
								selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
							}
						}
						List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
								selectedHierarchyNos);
						List<String> removeValues = new ArrayList<>();
						for (String uncommonHierValue : uncommonValues) {
							if (selectedHierarchyNos.contains(uncommonHierValue)) {
								removeValues.add(uncommonHierValue);
							}
						}
						if (!removeValues.isEmpty()) {
							uncommonValues.removeAll(removeValues); // At this
																	// point,
																	// uncommonValues
																	// should
																	// contain
																	// only 1
																	// value
																	// since
																	// only one
																	// value is
																	// selected
																	// to be
																	// moved in
																	// available
																	// table.
						}
						if (!uncommonValues.isEmpty()) {
							String tempHNo = uncommonValues.get(0);
							if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
								tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
							}
							int pos2 = 0;
							pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos2 != -1) {
								selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
							} else {
								selectedParentHierarchyNo = tempHNo + ".";
							}
						}
						if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
							for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
								if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
									selectedParent = selectedLevel;
									break;
								}
							}
						}
						if (!uncommonValues.isEmpty()) {
							newParentLevels = logic.getParentLevelsWithHierarchyNo(
									UiUtils.stringListToString(uncommonValues), customerDescriptionMap,
									customerHierarchyVersionNo, customerRelationVersionNo);
						}
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
								customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo);
						if (newParentLevels != null) {
							for (Leveldto newLevel : newParentLevels) {
								if (customerBeanList.isEmpty()
										|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
									customerBeanList.add(newLevel.getRelationshipLevelSid());
									selectedCustomerContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedCustomerContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedCustomerContainer.setChildrenAllowed(newLevel, false);
									}
									selectedCustomerContainer.setParent(newLevel, selectedParent);
									selectedParent = newLevel;
								}
							}
							if (!newChildLevels.isEmpty()) {
								int pos3 = 0;
								String childHierarchyNo;
								Leveldto childsParent = null;
								for (Leveldto newLevel : newChildLevels) {
									String tempHNo = newLevel.getHierarchyNo();
									if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
										tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
									}
									pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos3 != -1) {
										childHierarchyNo = tempHNo.substring(0, pos3) + ".";
									} else {
										childHierarchyNo = tempHNo + ".";
									}

									if (customerBeanList.isEmpty()
											|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
										customerBeanList.add(newLevel.getRelationshipLevelSid());
										selectedCustomerContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedCustomerContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedCustomerContainer.setChildrenAllowed(newLevel, false);
										}
									}

									if (!StringUtils.isBlank(childHierarchyNo)) {
										for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
											if (childHierarchyNo
													.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
												childsParent = selectedLevel;
												break;
											}
										}
									}

									selectedCustomerContainer.setParent(newLevel, childsParent);
								}
							}
						}

					}
				} else // When selected customer tree table is empty
				{
					if (availableCustomer.getValue() != null
							&& (DataSelectionUtil.getBeanFromId(availableCustomer.getValue()).getLevelNo() > 1)) {
						// Not root level as checked with condition getLevelNo()
						// > 1
						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						List<String> hierarchyNos = new ArrayList<>();
						List<Leveldto> newParentLevels = null;
						List<Leveldto> newChildLevels = null;
						hierarchyNos.add(hierarchyNo + ".");
						int pos = 0;
						String selectedParentHierarchyNo = StringUtils.EMPTY;
						Leveldto selectedParent2 = null;
						while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos != -1) {
								hierarchyNo = hierarchyNo.substring(0, pos);
							}
							hierarchyNos.add(hierarchyNo + ".");
						}
						Collections.reverse(hierarchyNos);
						if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
							for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
								if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
									selectedParent2 = selectedLevel;
									break;
								}
							}
						}
						newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(hierarchyNos),
								customerDescriptionMap, customerHierarchyVersionNo, customerRelationVersionNo);
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
								customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo);
						if (newParentLevels != null) {
							for (Leveldto newLevel : newParentLevels) {
								if (customerBeanList.isEmpty()
										|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {

									customerBeanList.add(newLevel.getRelationshipLevelSid());
									selectedCustomerContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedCustomerContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedCustomerContainer.setChildrenAllowed(newLevel, false);
									}
									selectedCustomerContainer.setParent(newLevel, selectedParent2);
									selectedParent2 = newLevel;
								}
							}
							if (!newChildLevels.isEmpty()) {
								int pos3 = 0;
								String childHierarchyNo;
								Leveldto childsParent = null;
								for (Leveldto newLevel : newChildLevels) {
									String tempHNo = newLevel.getHierarchyNo();
									if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
										tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
									}
									pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos3 != -1) {
										childHierarchyNo = tempHNo.substring(0, pos3) + ".";
									} else {
										childHierarchyNo = tempHNo + ".";
									}
									if (customerBeanList.isEmpty()
											|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
										customerBeanList.add(newLevel.getRelationshipLevelSid());
										selectedCustomerContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedCustomerContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedCustomerContainer.setChildrenAllowed(newLevel, false);
										}
									}
									if (!StringUtils.isBlank(childHierarchyNo)) {
										for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
											if (childHierarchyNo
													.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
												childsParent = selectedLevel;
												break;
											}
										}
									}
									selectedCustomerContainer.setParent(newLevel, childsParent);
								}
							}
						}
					} else {
						Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);
						// Root
						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						List<Leveldto> newChildLevels = null;
						int pos = 0;
						if (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
								pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos != -1) {
									hierarchyNo = hierarchyNo.substring(0, pos) + ".";
								}
							}
						}
						if (customerBeanList.isEmpty() || !customerBeanList
								.contains(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid())) {
							customerBeanList.add(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid());
							selectedCustomerContainer.addBean(selectedParent);
							if (forecastLevel != selectedParent.getLevelNo()) {
								selectedCustomerContainer.setChildrenAllowed(selectedParent, true);
							} else {
								selectedCustomerContainer.setChildrenAllowed(selectedParent, false);
							}
						}
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
								customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo);
						if (newChildLevels != null && !newChildLevels.isEmpty()) {
							int pos3 = 0;
							String childHierarchyNo;
							Leveldto childsParent = null;
							for (Leveldto newLevel : newChildLevels) {
								String tempHNo = newLevel.getHierarchyNo();
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos3 != -1) {
									childHierarchyNo = tempHNo.substring(0, pos3) + ".";
								} else {
									childHierarchyNo = tempHNo + ".";
								}
								if (customerBeanList.isEmpty()
										|| !customerBeanList.contains(Integer.valueOf(newLevel.getRelationShipBuilderId()))) {
									customerBeanList.add(newLevel.getRelationshipLevelSid());
									selectedCustomerContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedCustomerContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedCustomerContainer.setChildrenAllowed(newLevel, false);
									}
								}

								if (!StringUtils.isBlank(childHierarchyNo)) {
									for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
										if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
											childsParent = selectedLevel;
											break;
										}
									}
								}
								selectedCustomerContainer.setParent(newLevel, childsParent);
							}
						}

					}
				}

			} else {
				AbstractNotificationUtils.getErrorNotification("No Customer hierarchy level Selected",
						"No Level was selected to move. Please try again.");
			}
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	protected void moveAllButtonLogic() {
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			int forecastLevel = 0;
			if (level.getValue() != null) {
				forecastLevel = UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]);
				dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(forecastLevel));
				dataSelectionDTO.setCustomerHierarchyInnerLevel(String.valueOf(forecastLevel));
			}
			if (availableCustomerContainer.size() > 0) {
				int customerHierarchyVersionNo = Integer
						.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
				int customerRelationVersionNo = Integer.parseInt(
						customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
				List<Leveldto> iteams = new ArrayList<>(availableCustomerContainer.getItemIds());
				Object selectedItem = null;
				if (selectedCustomerContainer.size() > 0) {
					if (selectedCustomer.getValue() != null) {
						selectedItem = selectedCustomer.getValue();
						for (Leveldto item : iteams) {
							if (DataSelectionUtil.getBeanFromId(item).getLevelNo() > DataSelectionUtil
									.getBeanFromId(selectedItem).getLevelNo()) {
								// Check whether available level is greater to
								// selected level
								String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
								if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
									hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
								}
								String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
								List<String> hierarchyNos = new ArrayList<>();
								List<Leveldto> newParentLevels = null;
								List<Leveldto> newChildLevels = null;
								hierarchyNos.add(hierarchyNo + ".");
								int pos = 0;
								while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
									pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos != -1) {
										hierarchyNo = hierarchyNo.substring(0, pos);
									}
									hierarchyNos.add(hierarchyNo + ".");
								}
								Collections.reverse(hierarchyNos);
								List<String> selectedHierarchyNos = new ArrayList<>();
								for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
									if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
										selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
									}
								}
								List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
										selectedHierarchyNos);
								List<String> removeValues = new ArrayList<>();
								for (String uncommonHierValue : uncommonValues) {
									if (selectedHierarchyNos.contains(uncommonHierValue)) {
										removeValues.add(uncommonHierValue);
									}
								}
								if (!removeValues.isEmpty()) {
									uncommonValues.removeAll(removeValues); 
								}
								if (!uncommonValues.isEmpty()) {
									newParentLevels = logic.getParentLevelsWithHierarchyNo(
											UiUtils.stringListToString(uncommonValues), customerDescriptionMap,
											customerHierarchyVersionNo, customerRelationVersionNo);
								}
								newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
										UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
										customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
										customerHierarchyVersionNo, customerRelationVersionNo);
								if (newParentLevels != null) {
									int pos2 = 0;
									String parentHierarchyNo;
									Leveldto parent = null;
									for (Leveldto newLevel : newParentLevels) {
										String tempHNo = newLevel.getHierarchyNo();
										if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
											tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
										}
										pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());

										if (pos2 != -1) {
											parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
										} else {
											parentHierarchyNo = tempHNo + ".";
										}
										if (customerBeanList.isEmpty()
												|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
											customerBeanList.add(newLevel.getRelationshipLevelSid());
											selectedCustomerContainer.addBean(newLevel);
											if (forecastLevel != newLevel.getLevelNo()) {
												selectedCustomerContainer.setChildrenAllowed(newLevel, true);
											} else {
												selectedCustomerContainer.setChildrenAllowed(newLevel, false);
											}
										}

										if (!StringUtils.isBlank(parentHierarchyNo)) {
											for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
												if (parentHierarchyNo
														.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
													parent = selectedLevel;
													break;
												}
											}
										}
										selectedCustomerContainer.setParent(newLevel, parent);
										parent = newLevel;
									}
									if (!newChildLevels.isEmpty()) {
										int pos3 = 0;
										String childHierarchyNo;
										Leveldto childsParent = null;
										for (Leveldto newLevel : newChildLevels) {
											String tempHNo = newLevel.getHierarchyNo();
											if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
												tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
											}
											pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
											if (pos3 != -1) {
												childHierarchyNo = tempHNo.substring(0, pos3) + ".";
											} else {
												childHierarchyNo = tempHNo + ".";
											}
											if (customerBeanList.isEmpty()
													|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
												customerBeanList.add(newLevel.getRelationshipLevelSid());
												selectedCustomerContainer.addBean(newLevel);
												if (forecastLevel != newLevel.getLevelNo()) {
													selectedCustomerContainer.setChildrenAllowed(newLevel, true);
												} else {
													selectedCustomerContainer.setChildrenAllowed(newLevel, false);
												}
											}
											if (!StringUtils.isBlank(childHierarchyNo)) {
												for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
													if (childHierarchyNo
															.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
														childsParent = selectedLevel;
														break;
													}
												}
											}
											selectedCustomerContainer.setParent(newLevel, childsParent);
										}
									}
								}
							}
						}
					} else {
						// Adding without selecting a parent
						for (Leveldto item : iteams) {
							String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
								hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
							}
							String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							List<String> hierarchyNos = new ArrayList<>();
							List<Leveldto> newParentLevels = null;
							List<Leveldto> newChildLevels = null;
							hierarchyNos.add(hierarchyNo + ".");
							int pos = 0;
							String selectedParentHierarchyNo = StringUtils.EMPTY;
							Leveldto selectedParent = null;
							while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
								pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos != -1) {
									hierarchyNo = hierarchyNo.substring(0, pos);
								}
								hierarchyNos.add(hierarchyNo + ".");
							}
							Collections.reverse(hierarchyNos);
							List<String> selectedHierarchyNos = new ArrayList<>();
							for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
								if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
									selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
								}
							}
							List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
									selectedHierarchyNos);
							List<String> removeValues = new ArrayList<>();
							for (String uncommonHierValue : uncommonValues) {
								if (selectedHierarchyNos.contains(uncommonHierValue)) {
									removeValues.add(uncommonHierValue);
								}
							}
							if (!removeValues.isEmpty()) {
								uncommonValues.removeAll(removeValues); 
							}
							if (!uncommonValues.isEmpty()) {
								String tempHNo = uncommonValues.get(0);
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								int pos2 = 0;
								pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos2 != -1) {
									selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
								} else {
									selectedParentHierarchyNo = tempHNo + ".";
								}
							}
							if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
								for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
									if (selectedParentHierarchyNo
											.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
										selectedParent = selectedLevel;
										break;
									}
								}
							}
							if (!uncommonValues.isEmpty()) {
								newParentLevels = logic.getParentLevelsWithHierarchyNo(
										UiUtils.stringListToString(uncommonValues), customerDescriptionMap,
										customerHierarchyVersionNo, customerRelationVersionNo);
							}
							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
									customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo);
							if (newParentLevels != null) {
								for (Leveldto newLevel : newParentLevels) {
									if (customerBeanList.isEmpty()
											|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
										customerBeanList.add(newLevel.getRelationshipLevelSid());
										selectedCustomerContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedCustomerContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedCustomerContainer.setChildrenAllowed(newLevel, false);
										}
										selectedCustomerContainer.setParent(newLevel, selectedParent);
										selectedParent = newLevel;
									}
								}
								if (!newChildLevels.isEmpty()) {
									int pos3 = 0;
									String childHierarchyNo;
									Leveldto childsParent = null;
									for (Leveldto newLevel : newChildLevels) {
										String tempHNo = newLevel.getHierarchyNo();
										if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
											tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
										}
										pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
										if (pos3 != -1) {
											childHierarchyNo = tempHNo.substring(0, pos3) + ".";
										} else {
											childHierarchyNo = tempHNo + ".";
										}
										if (customerBeanList.isEmpty()
												|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
											customerBeanList.add(newLevel.getRelationshipLevelSid());
											selectedCustomerContainer.addBean(newLevel);
											if (forecastLevel != newLevel.getLevelNo()) {
												selectedCustomerContainer.setChildrenAllowed(newLevel, true);
											} else {
												selectedCustomerContainer.setChildrenAllowed(newLevel, false);
											}
										}

										if (!StringUtils.isBlank(childHierarchyNo)) {
											for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
												if (childHierarchyNo
														.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
													childsParent = selectedLevel;
													break;
												}
											}
										}
										selectedCustomerContainer.setParent(newLevel, childsParent);
									}
								}
							}

						}
					}
				} else // Adding without selecting a parent with empty tree
				{
					if (level.getValue() != null
							&& UiUtils.parseStringToInteger(String.valueOf(level.getValue())) == 1) {
						// Root
						for (Leveldto item : iteams) {
							selectedCustomerContainer.removeAllItems();
							selectedCustomer.removeAllItems();
							Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);
							// Root
							String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
								hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
							}
							String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							List<Leveldto> newChildLevels = null;
							int pos = 0;
							if (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
								while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
									pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos != -1) {
										hierarchyNo = hierarchyNo.substring(0, pos) + ".";
									}
								}
							}
							if (customerBeanList.isEmpty() || !customerBeanList
									.contains(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid())) {
								customerBeanList.add(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid());
								selectedCustomerContainer.addBean(selectedParent);
								if (forecastLevel != selectedParent.getLevelNo()) {
									selectedCustomerContainer.setChildrenAllowed(selectedParent, true);
								} else {
									selectedCustomerContainer.setChildrenAllowed(selectedParent, false);
								}
							}

							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
									customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo);
							if (newChildLevels != null && !newChildLevels.isEmpty()) {
								int pos3 = 0;
								String childHierarchyNo;
								Leveldto childsParent = null;
								for (Leveldto newLevel : newChildLevels) {
									String tempHNo = newLevel.getHierarchyNo();
									if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
										tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
									}
									pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos3 != -1) {
										childHierarchyNo = tempHNo.substring(0, pos3) + ".";
									} else {
										childHierarchyNo = tempHNo + ".";
									}
									if (customerBeanList.isEmpty()
											|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
										customerBeanList.add(newLevel.getRelationshipLevelSid());
										selectedCustomerContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedCustomerContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedCustomerContainer.setChildrenAllowed(newLevel, false);
										}
									}
									if (!StringUtils.isBlank(childHierarchyNo)) {
										for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
											if (childHierarchyNo
													.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
												childsParent = selectedLevel;
												break;
											}
										}
									}
									selectedCustomerContainer.setParent(newLevel, childsParent);
								}
							}
						}
					} else {
						// Not root, none selected in selected tree
						for (Leveldto item : iteams) {
							String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
								hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
							}
							String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							List<String> hierarchyNos = new ArrayList<>();
							List<Leveldto> newParentLevels = null;
							List<Leveldto> newChildLevels = null;
							hierarchyNos.add(hierarchyNo + ".");
							int pos = 0;
							String selectedParentHierarchyNo = StringUtils.EMPTY;
							Leveldto selectedParent = null;
							while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
								pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos != -1) {
									hierarchyNo = hierarchyNo.substring(0, pos);
								}
								hierarchyNos.add(hierarchyNo + ".");
							}
							Collections.reverse(hierarchyNos);
							List<String> selectedHierarchyNos = new ArrayList<>();
							for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
								if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
									selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
								}
							}

							List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
									selectedHierarchyNos);
							List<String> removeValues = new ArrayList<>();
							for (String uncommonHierValue : uncommonValues) {
								if (selectedHierarchyNos.contains(uncommonHierValue)) {
									removeValues.add(uncommonHierValue);
								}
							}
							if (!removeValues.isEmpty()) {
								uncommonValues.removeAll(removeValues); // At
																		// this
																		// point,
																		// uncommonValues
																		// should
																		// contain
																		// only
																		// 1
																		// value
																		// since
																		// only
																		// one
																		// value
																		// is
																		// selected
																		// to be
																		// moved
																		// in
																		// available
																		// table.
							}
							if (!uncommonValues.isEmpty()) {
								String tempHNo = uncommonValues.get(0);
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								int pos2 = 0;
								pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos2 != -1) {
									selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
								} else {
									selectedParentHierarchyNo = tempHNo + ".";
								}
							}
							if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
								for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
									if (selectedParentHierarchyNo
											.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
										selectedParent = selectedLevel;
										break;
									}
								}
							}
							newParentLevels = logic.getParentLevelsWithHierarchyNo(
									UiUtils.stringListToString(uncommonValues), customerDescriptionMap,
									customerHierarchyVersionNo, customerRelationVersionNo);
							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
									customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo);
							if (newParentLevels != null) {
								for (Leveldto newLevel : newParentLevels) {
									if (customerBeanList.isEmpty()
											|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
										customerBeanList.add(newLevel.getRelationshipLevelSid());
										selectedCustomerContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedCustomerContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedCustomerContainer.setChildrenAllowed(newLevel, false);
										}
										selectedCustomerContainer.setParent(newLevel, selectedParent);
										selectedParent = newLevel;
									}
								}
								if (!newChildLevels.isEmpty()) {
									int pos3 = 0;
									String childHierarchyNo;
									Leveldto childsParent = null;
									for (Leveldto newLevel : newChildLevels) {
										String tempHNo = newLevel.getHierarchyNo();
										if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
											tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
										}
										pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
										if (pos3 != -1) {
											childHierarchyNo = tempHNo.substring(0, pos3) + ".";
										} else {
											childHierarchyNo = tempHNo + ".";
										}
										if (customerBeanList.isEmpty()
												|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
											customerBeanList.add(newLevel.getRelationshipLevelSid());
											selectedCustomerContainer.addBean(newLevel);
											if (forecastLevel != newLevel.getLevelNo()) {
												selectedCustomerContainer.setChildrenAllowed(newLevel, true);
											} else {
												selectedCustomerContainer.setChildrenAllowed(newLevel, false);
											}
										}
										if (!StringUtils.isBlank(childHierarchyNo)) {
											for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
												if (childHierarchyNo
														.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
													childsParent = selectedLevel;
													break;
												}
											}
										}
										selectedCustomerContainer.setParent(newLevel, childsParent);
									}
								}
							}
						}
					}
				}
			}
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	protected void moveAllProductButtonLogic() {
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			int forecastLevel = 0;
			if (productlevelDdlb.getValue() != null) {
				forecastLevel = UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]);
				dataSelectionDTO.setProductHierarchyLevel(String.valueOf(forecastLevel));
				dataSelectionDTO.setProductHierarchyInnerLevel(String.valueOf(forecastLevel));
			}

			if (availableProductContainer.size() > 0) {
				int productHierarchyVersionNo = Integer
						.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
				int productRelationVersionNo = Integer.parseInt(
						productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
				List<Leveldto> iteams = new ArrayList<>(availableProductContainer.getItemIds());
				Object selectedItem = null;
				if (selectedProductContainer.size() > 0) {
					if (selectedProduct.getValue() != null) {
						selectedItem = selectedProduct.getValue();
						for (Leveldto item : iteams) {
							if (DataSelectionUtil.getBeanFromId(item).getLevelNo() > DataSelectionUtil
									.getBeanFromId(selectedItem).getLevelNo()) {
								// Check whether available level is greater to
								// selected level
								String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
								if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
									hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
								}
								String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
								List<String> hierarchyNos = new ArrayList<>();
								List<Leveldto> newParentLevels = null;
								List<Leveldto> newChildLevels = null;
								hierarchyNos.add(hierarchyNo + ".");
								int pos = 0;
								while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
									pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos != -1) {
										hierarchyNo = hierarchyNo.substring(0, pos);
									}
									hierarchyNos.add(hierarchyNo + ".");
								}
								Collections.reverse(hierarchyNos);
								List<String> selectedHierarchyNos = new ArrayList<>();
								for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
									if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
										selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
									}
								}
								List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
										selectedHierarchyNos);
								List<String> removeValues = new ArrayList<>();
								for (String uncommonHierValue : uncommonValues) {
									if (selectedHierarchyNos.contains(uncommonHierValue)) {
										removeValues.add(uncommonHierValue);
									}
								}
								if (!removeValues.isEmpty()) {
									uncommonValues.removeAll(removeValues); // At
																			// this
																			// point,
																			// uncommonValues
																			// should
																			// contain
																			// only
																			// 1
																			// value
																			// since
																			// only
																			// one
																			// value
																			// is
																			// selected
																			// to
																			// be
																			// moved
																			// in
																			// available
																			// table.
								}
								newParentLevels = logic.getParentLevelsWithHierarchyNo(
										UiUtils.stringListToString(uncommonValues), productDescriptionMap,
										productHierarchyVersionNo, productRelationVersionNo);
								newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
										UiUtils.parseStringToInteger(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
										productDescriptionMap, getBusinessUnit().getValue(),
										DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
										productRelationVersionNo);
								if (newParentLevels != null) {
									int pos2 = 0;
									String parentHierarchyNo;
									Leveldto parent = null;
									for (Leveldto newLevel : newParentLevels) {
										String tempHNo = newLevel.getHierarchyNo();
										if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
											tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
										}
										pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());

										if (pos2 != -1) {
											parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
										} else {
											parentHierarchyNo = tempHNo + ".";
										}
										if (productBeanList.isEmpty()
												|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
											productBeanList.add(newLevel.getRelationshipLevelSid());
											selectedProductContainer.addBean(newLevel);
											if (forecastLevel != newLevel.getLevelNo()) {
												selectedProductContainer.setChildrenAllowed(newLevel, true);
											} else {
												selectedProductContainer.setChildrenAllowed(newLevel, false);
											}
										}

										if (!StringUtils.isBlank(parentHierarchyNo)) {
											for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
												if (parentHierarchyNo
														.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
													parent = selectedLevel;
													break;
												}
											}
										}
										selectedProductContainer.setParent(newLevel, parent);
										parent = newLevel;
									}
									if (!newChildLevels.isEmpty()) {
										int pos3 = 0;
										String childHierarchyNo;
										Leveldto childsParent = null;
										for (Leveldto newLevel : newChildLevels) {
											String tempHNo = newLevel.getHierarchyNo();
											if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
												tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
											}
											pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
											if (pos3 != -1) {
												childHierarchyNo = tempHNo.substring(0, pos3) + ".";
											} else {
												childHierarchyNo = tempHNo + ".";
											}
											if (productBeanList.isEmpty()
													|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
												productBeanList.add(newLevel.getRelationshipLevelSid());
												selectedProductContainer.addBean(newLevel);
												if (forecastLevel != newLevel.getLevelNo()) {
													selectedProductContainer.setChildrenAllowed(newLevel, true);
												} else {
													selectedProductContainer.setChildrenAllowed(newLevel, false);
												}
											}
											if (!StringUtils.isBlank(childHierarchyNo)) {
												for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
													if (childHierarchyNo
															.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
														childsParent = selectedLevel;
														break;
													}
												}
											}
											selectedProductContainer.setParent(newLevel, childsParent);
										}
									}
								}
							}
						}
					} else {
						// Adding without selecting a parent
						for (Leveldto item : iteams) {
							String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
								hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
							}
							String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							List<String> hierarchyNos = new ArrayList<>();
							List<Leveldto> newParentLevels = null;
							List<Leveldto> newChildLevels = null;
							hierarchyNos.add(hierarchyNo + ".");
							int pos = 0;
							String selectedParentHierarchyNo = StringUtils.EMPTY;
							Leveldto selectedParent = null;
							while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
								pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos != -1) {
									hierarchyNo = hierarchyNo.substring(0, pos);
								}
								hierarchyNos.add(hierarchyNo + ".");
							}
							Collections.reverse(hierarchyNos);
							List<String> selectedHierarchyNos = new ArrayList<>();
							for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
								if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
									selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
								}
							}
							List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
									selectedHierarchyNos);
							List<String> removeValues = new ArrayList<>();
							for (String uncommonHierValue : uncommonValues) {
								if (selectedHierarchyNos.contains(uncommonHierValue)) {
									removeValues.add(uncommonHierValue);
								}
							}
							if (!removeValues.isEmpty()) {
								uncommonValues.removeAll(removeValues); // At
																		// this
																		// point,
																		// uncommonValues
																		// should
																		// contain
																		// only
																		// 1
																		// value
																		// since
																		// only
																		// one
																		// value
																		// is
																		// selected
																		// to be
																		// moved
																		// in
																		// available
																		// table.
							}
							if (!uncommonValues.isEmpty()) {
								String tempHNo = uncommonValues.get(0);
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								int pos2 = 0;
								pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos2 != -1) {
									selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
								} else {
									selectedParentHierarchyNo = tempHNo + ".";
								}
							}
							if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
								for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
									if (selectedParentHierarchyNo
											.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
										selectedParent = selectedLevel;
										break;
									}
								}
							}
							if (!uncommonValues.isEmpty()) {
								newParentLevels = logic.getParentLevelsWithHierarchyNo(
										UiUtils.stringListToString(uncommonValues), productDescriptionMap,
										productHierarchyVersionNo, productRelationVersionNo);
							}
							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
									productDescriptionMap, getBusinessUnit().getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo);
							if (newParentLevels != null) {
								for (Leveldto newLevel : newParentLevels) {
									if (productBeanList.isEmpty()
											|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
										productBeanList.add(newLevel.getRelationshipLevelSid());
										selectedProductContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedProductContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedProductContainer.setChildrenAllowed(newLevel, false);
										}
										selectedProductContainer.setParent(newLevel, selectedParent);
										selectedParent = newLevel;
									}
								}
								if (!newChildLevels.isEmpty()) {
									int pos3 = 0;
									String childHierarchyNo;
									Leveldto childsParent = null;
									for (Leveldto newLevel : newChildLevels) {
										String tempHNo = newLevel.getHierarchyNo();
										if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
											tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
										}
										pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
										if (pos3 != -1) {
											childHierarchyNo = tempHNo.substring(0, pos3) + ".";
										} else {
											childHierarchyNo = tempHNo + ".";
										}
										if (productBeanList.isEmpty()
												|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
											productBeanList.add(newLevel.getRelationshipLevelSid());
											selectedProductContainer.addBean(newLevel);
											if (forecastLevel != newLevel.getLevelNo()) {
												selectedProductContainer.setChildrenAllowed(newLevel, true);
											} else {
												selectedProductContainer.setChildrenAllowed(newLevel, false);
											}
										}

										if (!StringUtils.isBlank(childHierarchyNo)) {
											for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
												if (childHierarchyNo
														.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
													childsParent = selectedLevel;
													break;
												}
											}
										}
										selectedProductContainer.setParent(newLevel, childsParent);
									}
								}
							}

						}
					}
				} else // Adding without selecting a parent with empty tree
				{
					if (productLevel.getValue() != null
							&& UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue())) == 1) {
						// Root
						for (Leveldto item : iteams) {
							selectedProductContainer.removeAllItems();
							selectedProduct.removeAllItems();
							Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);
							// Root
							String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
								hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
							}
							String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							List<Leveldto> newChildLevels = null;
							int pos = 0;
							if (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
								while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
									pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos != -1) {
										hierarchyNo = hierarchyNo.substring(0, pos) + ".";
									}
								}
							}
							if (productBeanList.isEmpty() || !productBeanList
									.contains(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid())) {
								productBeanList.add(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid());
								selectedProductContainer.addBean(selectedParent);
								if (forecastLevel != selectedParent.getLevelNo()) {
									selectedProductContainer.setChildrenAllowed(selectedParent, true);
								} else {
									selectedProductContainer.setChildrenAllowed(selectedParent, false);
								}
								availableProductContainer.removeItem(selectedParent);
								availableProduct.removeItem(selectedParent);
							}
							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
									productDescriptionMap, getBusinessUnit().getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo);
							if (newChildLevels != null && !newChildLevels.isEmpty()) {
								int pos3 = 0;
								String childHierarchyNo;
								Leveldto childsParent = null;
								for (Leveldto newLevel : newChildLevels) {
									String tempHNo = newLevel.getHierarchyNo();
									if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
										tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
									}
									pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos3 != -1) {
										childHierarchyNo = tempHNo.substring(0, pos3) + ".";
									} else {
										childHierarchyNo = tempHNo + ".";
									}
									if (productBeanList.isEmpty()
											|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
										productBeanList.add(newLevel.getRelationshipLevelSid());
										selectedProductContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedProductContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedProductContainer.setChildrenAllowed(newLevel, false);
										}
									}
									if (!StringUtils.isBlank(childHierarchyNo)) {
										for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
											if (childHierarchyNo
													.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
												childsParent = selectedLevel;
												break;
											}
										}
									}
									selectedProductContainer.setParent(newLevel, childsParent);
								}
							}
						}
					} else {
						// Not root, none selected in selected tree
						for (Leveldto item : iteams) {
							String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
								hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
							}
							String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							List<String> hierarchyNos = new ArrayList<>();
							List<Leveldto> newParentLevels = null;
							List<Leveldto> newChildLevels = null;
							hierarchyNos.add(hierarchyNo + ".");
							int pos = 0;
							String selectedParentHierarchyNo = StringUtils.EMPTY;
							while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
								pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos != -1) {
									hierarchyNo = hierarchyNo.substring(0, pos);
								}
								hierarchyNos.add(hierarchyNo + ".");
							}
							Collections.reverse(hierarchyNos);
							List<String> selectedHierarchyNos = new ArrayList<>();
							for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
								if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
									selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
								}
							}
							List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
									selectedHierarchyNos);
							List<String> removeValues = new ArrayList<>();
							for (String uncommonHierValue : uncommonValues) {
								if (selectedHierarchyNos.contains(uncommonHierValue)) {
									removeValues.add(uncommonHierValue);
								}
							}
							if (!removeValues.isEmpty()) {
								uncommonValues.removeAll(removeValues); // At
																		// this
																		// point,
																		// uncommonValues
																		// should
																		// contain
																		// only
																		// 1
																		// value
																		// since
																		// only
																		// one
																		// value
																		// is
																		// selected
																		// to be
																		// moved
																		// in
																		// available
																		// table.
							}
							if (!uncommonValues.isEmpty()) {
								String tempHNo = uncommonValues.get(0);
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								int pos2 = 0;
								pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos2 != -1) {
									selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
								} else {
									selectedParentHierarchyNo = tempHNo + ".";
								}
							}
							if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
								for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
									if (selectedParentHierarchyNo
											.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
										break;
									}
								}
							}
							if (!uncommonValues.isEmpty()) {
								newParentLevels = logic.getParentLevelsWithHierarchyNo(
										UiUtils.stringListToString(uncommonValues), productDescriptionMap,
										productHierarchyVersionNo, productRelationVersionNo);
							}
							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
									productDescriptionMap, getBusinessUnit().getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo);
							if (newParentLevels != null) {
								int pos2 = 0;
								String parentHierarchyNo;
								Leveldto parent = null;
								for (Leveldto newLevel : newParentLevels) {
									String tempHNo = newLevel.getHierarchyNo();
									if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
										tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
									}
									pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());

									if (pos2 != -1) {
										parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
									} else {
										parentHierarchyNo = tempHNo + ".";
									}
									if (productBeanList.isEmpty()
											|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
										productBeanList.add(newLevel.getRelationshipLevelSid());
										selectedProductContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedProductContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedProductContainer.setChildrenAllowed(newLevel, false);
										}
									}
									if (!StringUtils.isBlank(parentHierarchyNo)) {
										for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
											if (parentHierarchyNo
													.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
												parent = selectedLevel;
												break;
											}
										}
									}
									selectedProductContainer.setParent(newLevel, parent);
									parent = newLevel;
								}

								if (!newChildLevels.isEmpty()) {
									int pos3 = 0;
									String childHierarchyNo;
									Leveldto childsParent = null;
									for (Leveldto newLevel : newChildLevels) {
										String tempHNo = newLevel.getHierarchyNo();
										if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
											tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
										}
										pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
										if (pos3 != -1) {
											childHierarchyNo = tempHNo.substring(0, pos3) + ".";
										} else {
											childHierarchyNo = tempHNo + ".";
										}
										if (productBeanList.isEmpty()
												|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
											productBeanList.add(newLevel.getRelationshipLevelSid());
											selectedProductContainer.addBean(newLevel);
											if (forecastLevel != newLevel.getLevelNo()) {
												selectedProductContainer.setChildrenAllowed(newLevel, true);
											} else {
												selectedProductContainer.setChildrenAllowed(newLevel, false);
											}
										}
										if (!StringUtils.isBlank(childHierarchyNo)) {
											for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
												if (childHierarchyNo
														.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
													childsParent = selectedLevel;
													break;
												}
											}
										}
										selectedProductContainer.setParent(newLevel, childsParent);
									}
								}
							}
						}
					}
				}
				DataSelectionLogic.selectedProductTableAlignmentChange(selectedProduct, selectedProductContainer);
			}
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	protected void moveRigthButtonLogic() {
		try {

			if (selectedCustomer.getValue() != null) {
				Object selectedItem = selectedCustomer.getValue();
				String levelInString = "0";
				if (level.getValue() != null
						&& !String.valueOf(level.getValue()).equalsIgnoreCase(String.valueOf(SELECT_ONE))) {
					levelInString = String.valueOf(level.getValue());
				}
				int currentLevel = UiUtils.parseStringToInteger(levelInString);
				
				DataSelectionUtil.removeItemsRecursively(selectedItem, selectedCustomer, availableCustomer,
						selectedCustomerContainer, availableCustomerContainer, currentLevel);
				selectedCustomerContainer.removeItem(DataSelectionUtil.getBeanFromId(selectedItem));
				selectedCustomer.removeItem(selectedItem);
				customerBeanList.remove(DataSelectionUtil.getBeanFromId(selectedItem).getRelationshipLevelSid());
				customerBeanList.clear();
				List<Leveldto> selectedValueItem = selectedCustomerContainer.getItemIds();
				for (Leveldto dto : selectedValueItem) {
					customerBeanList.add(dto.getRelationshipLevelSid());
				}
				if (dismantleCustomerSelection) {
					triggerCustGrpOnView(dataSelectionDTO.getCustomerGrpSid(), false);
					dismantleCustomerSelection = false;
				}
			} else {
				AbstractNotificationUtils.getErrorNotification("No Customer hierarchy level Selected",
						"No Level was selected to move. Please try again. ");
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	@Override
	protected void moveRigthProductButtonLogic() {
		try {
			if (selectedProduct.getValue() != null) {
				List<Leveldto> listValue;
				Object selectedItem = selectedProduct.getValue();
				Leveldto selectedLevel = (Leveldto) DataSelectionUtil.getBeanFromId(selectedItem);
				String levelInString = "0";
				if (!String.valueOf(productlevelDdlb.getValue()).equalsIgnoreCase(String.valueOf(SELECT_ONE))) {
					levelInString = String.valueOf(productlevelDdlb.getValue());
				}
				int currentLevel = UiUtils.parseStringToInteger(levelInString);
				if (currentLevel != 0 && selectedLevel.getLevelNo() == currentLevel
						&& "NDC".equalsIgnoreCase(selectedLevel.getLevel())) {
					listValue = DataSelectionUtil.getFSValue(selectedLevel.getRelationshipLevelValue(),
							selectedLevel.getFieldName());
					selectedLevel.setForm("" + listValue.get(0).getForm());
					selectedLevel.setStrength("" + listValue.get(0).getStrength());
				}
				DataSelectionUtil.removeItemsRecursively(selectedItem, selectedProduct, availableProduct,
						selectedProductContainer, availableProductContainer, currentLevel);
				selectedProductContainer.removeItem(selectedLevel);
				selectedProduct.removeItem(selectedItem);
				if (dismantleProductSelection) {
					triggerProdGrpOnView(dataSelectionDTO.getProdGrpSid(), false);
					dismantleProductSelection = false;
				}
				productBeanList.clear();
				List<Leveldto> productListValue = selectedProductContainer.getItemIds();
				for (Leveldto dto : productListValue) {
					productBeanList.add(dto.getRelationshipLevelSid());
				}
			} else {
				AbstractNotificationUtils.getErrorNotification("No Product hierarchy level Selected",
						"No Level was selected to move. Please try again. ");
			}
		} catch (Exception ex) {
			LOGGER.error(" in moveRightProduct= {}", ex);
		}
	}

	@Override
	protected void moveLeftProductButtonLogic() {
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			int forecastLevel = 0;
			if (productlevelDdlb.getValue() != null) {
				forecastLevel = UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]);
				dataSelectionDTO.setProductHierarchyLevel(String.valueOf(forecastLevel));
				dataSelectionDTO.setProductHierarchyInnerLevel(String.valueOf(forecastLevel));
			}
			if (availableProduct.getValue() != null) {
				int prodHieVersionNo = Integer
						.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
				int prodRelVersionNo = Integer.parseInt(
						productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
				Object item = availableProduct.getValue();
				if (selectedProductContainer.size() > 0) {
					if (selectedProduct.getValue() != null) {
						// When a value selected in selected product tree
						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						List<String> hierarchyNoList = new ArrayList<>();
						List<Leveldto> newParentLevelsList = null;
						List<Leveldto> newChildLevels = null;
						hierarchyNoList.add(hierarchyNo + ".");
						int pos = 0;
						while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos != -1) {
								hierarchyNo = hierarchyNo.substring(0, pos);
							}
							hierarchyNoList.add(hierarchyNo + ".");
						}
						Collections.reverse(hierarchyNoList);
						List<String> selectedHierarchyNos = new ArrayList<>();
						for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
							if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
								selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
							}
						}
						List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNoList,
								selectedHierarchyNos);
						List<String> removeValues = new ArrayList<>();
						for (String uncommonHierValue : uncommonValues) {
							if (selectedHierarchyNos.contains(uncommonHierValue)) {
								removeValues.add(uncommonHierValue);
							}
						}
						if (!removeValues.isEmpty()) {
							uncommonValues.removeAll(removeValues);
							// At this point,uncommonValues should contain only 1 value since only one value
							// is selected to be moved in available table.
						}
						if (!uncommonValues.isEmpty()) {
							newParentLevelsList = logic.getParentLevelsWithHierarchyNo(
									UiUtils.stringListToString(uncommonValues), productDescriptionMap,
									prodHieVersionNo, prodRelVersionNo);
						}
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								productDescriptionMap, getBusinessUnit().getValue(), DataSelectionUtil.getBeanFromId(item),
								prodHieVersionNo, prodRelVersionNo);
						if (newParentLevelsList != null) {
							int pos2 = 0;
							String parentHierarchyNo;
							Leveldto parent = null;
							for (Leveldto newParentLevel : newParentLevelsList) {
								String tempHNo = newParentLevel.getHierarchyNo();
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos2 != -1) {
									parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
								} else {
									parentHierarchyNo = tempHNo + ".";
								}
								if (productBeanList.isEmpty()
										|| !productBeanList.contains(newParentLevel.getRelationshipLevelSid())) {
									productBeanList.add(newParentLevel.getRelationshipLevelSid());
									selectedProductContainer.addBean(newParentLevel);
									if (forecastLevel != newParentLevel.getLevelNo()) {
										selectedProductContainer.setChildrenAllowed(newParentLevel, true);
									} else {
										selectedProductContainer.setChildrenAllowed(newParentLevel, false);
									}
								}

								if (!StringUtils.isBlank(parentHierarchyNo)) {
									for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
										if (parentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
											parent = selectedLevel;
											break;
										}
									}
								}
								selectedProductContainer.setParent(newParentLevel, parent);
								parent = newParentLevel;
							}
							if (!newChildLevels.isEmpty()) {
								int pos3 = 0;
								String childHierarchyNo;
								Leveldto childsParent = null;
								for (Leveldto selectedLeve : newChildLevels) {
									String tempHNo = selectedLeve.getHierarchyNo();
									if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
										tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
									}
									pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos3 != -1) {
										childHierarchyNo = tempHNo.substring(0, pos3) + ".";
									} else {
										childHierarchyNo = tempHNo + ".";
									}
									if (productBeanList.isEmpty()
											|| !productBeanList.contains(selectedLeve.getRelationshipLevelSid())) {
										productBeanList.add(selectedLeve.getRelationshipLevelSid());
										selectedProductContainer.addBean(selectedLeve);
										if (forecastLevel != selectedLeve.getLevelNo()) {
											selectedProductContainer.setChildrenAllowed(selectedLeve, true);
										} else {
											selectedProductContainer.setChildrenAllowed(selectedLeve, false);
										}
									}

									if (!StringUtils.isBlank(childHierarchyNo)) {
										for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
											if (childHierarchyNo
													.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
												childsParent = selectedLevel;
												break;
											}
										}
									}
									selectedProductContainer.setParent(selectedLeve, childsParent);
								}
							}
						}

					} else {
						// When no value selected in selected product tree
						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						List<String> hieNoList = new ArrayList<>();
						List<Leveldto> newParentLevelList = null;
						List<Leveldto> newChildList = null;
						hieNoList.add(hierarchyNo + ".");
						int pos = 0;
						String selectedParentHierarchyNo = StringUtils.EMPTY;
						Leveldto selectedParent = null;
						while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos != -1) {
								hierarchyNo = hierarchyNo.substring(0, pos);
							}
							hieNoList.add(hierarchyNo + ".");
						}
						Collections.reverse(hieNoList);
						List<String> selectedHierarchyNos = new ArrayList<>();
						for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
							if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
								selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
							}
						}
						List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hieNoList,
								selectedHierarchyNos);
						List<String> removeValueList = new ArrayList<>();
						for (String uncommonHierValue : uncommonValues) {
							if (selectedHierarchyNos.contains(uncommonHierValue)) {
								removeValueList.add(uncommonHierValue);
							}
						}
						if (!removeValueList.isEmpty()) {
							uncommonValues.removeAll(removeValueList); // At this
																	// point,
																	// uncommonValues
																	// should
																	// contain
																	// only 1
																	// value
																	// since
																	// only one
																	// value is
																	// selected
																	// to be
																	// moved in
																	// available
																	// table.
						}
						if (!uncommonValues.isEmpty()) {
							String tempHierarchyNo = uncommonValues.get(0);
							if (tempHierarchyNo.length() > 0 && tempHierarchyNo.charAt(tempHierarchyNo.length() - 1) == '.') {
								tempHierarchyNo = tempHierarchyNo.substring(0, tempHierarchyNo.length() - 1);
							}
							int pos2 = 0;
							pos2 = tempHierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos2 != -1) {
								selectedParentHierarchyNo = tempHierarchyNo.substring(0, pos2) + ".";
							} else {
								selectedParentHierarchyNo = tempHierarchyNo + ".";
							}
						}
						if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
							for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
								if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
									selectedParent = selectedLevel;
									break;
								}
							}
						}
						if (!uncommonValues.isEmpty()) {
							newParentLevelList = logic.getParentLevelsWithHierarchyNo(
									UiUtils.stringListToString(uncommonValues), productDescriptionMap,
									prodHieVersionNo, prodRelVersionNo);
						}
						newChildList = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								productDescriptionMap, getBusinessUnit().getValue(), DataSelectionUtil.getBeanFromId(item),
								prodHieVersionNo, prodRelVersionNo);
						if (newParentLevelList != null) {
							for (Leveldto newLevel : newParentLevelList) {
								if (productBeanList.isEmpty()
										|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
									productBeanList.add(newLevel.getRelationshipLevelSid());
									selectedProductContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedProductContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedProductContainer.setChildrenAllowed(newLevel, false);
									}
									selectedProductContainer.setParent(newLevel, selectedParent);
									selectedParent = newLevel;
								}
							}
							if (!newChildList.isEmpty()) {
								int pos3 = 0;
								String childHierarchyNo;
								Leveldto childsParent = null;
								for (Leveldto newChildLevel : newChildList) {
									String tempHNo = newChildLevel.getHierarchyNo();
									if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
										tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
									}
									pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos3 != -1) {
										childHierarchyNo = tempHNo.substring(0, pos3) + ".";
									} else {
										childHierarchyNo = tempHNo + ".";
									}
									if (productBeanList.isEmpty()
											|| !productBeanList.contains(newChildLevel.getRelationshipLevelSid())) {
										productBeanList.add(newChildLevel.getRelationshipLevelSid());
										selectedProductContainer.addBean(newChildLevel);
										if (forecastLevel != newChildLevel.getLevelNo()) {
											selectedProductContainer.setChildrenAllowed(newChildLevel, true);
										} else {
											selectedProductContainer.setChildrenAllowed(newChildLevel, false);
										}
									}

									if (!StringUtils.isBlank(childHierarchyNo)) {
										for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
											if (childHierarchyNo
													.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
												childsParent = selectedLevel;
												break;
											}
										}
									}

									selectedProductContainer.setParent(newChildLevel, childsParent);
								}
							}
						}

					}
				} else // When selected product tree table is empty
				{
					if (availableProduct.getValue() != null
							&& (DataSelectionUtil.getBeanFromId(availableProduct.getValue()).getLevelNo() > 1)) {
						// Not root level as checked with condition getLevelNo()
						// > 1
						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						String currentHierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						List<String> hierarchyNos = new ArrayList<>();
						List<Leveldto> newParentLevels = null;
						List<Leveldto> newChildLevels = null;
						hierarchyNos.add(hierarchyNo + ".");
						int pos = 0;
						String selectedParentHierarchyNo = StringUtils.EMPTY;
						Leveldto selectedParent2 = null;
						while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos != -1) {
								hierarchyNo = hierarchyNo.substring(0, pos);
							}
							hierarchyNos.add(hierarchyNo + ".");
						}
						Collections.reverse(hierarchyNos);
						if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
							for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
								if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
									selectedParent2 = selectedLevel;
									break;
								}
							}
						}
						newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(hierarchyNos),
								productDescriptionMap, prodHieVersionNo, prodRelVersionNo);
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								productDescriptionMap, getBusinessUnit().getValue(), DataSelectionUtil.getBeanFromId(item),
								prodHieVersionNo, prodRelVersionNo);
						if (newParentLevels != null) {
							for (Leveldto newLevel : newParentLevels) {
								if (productBeanList.isEmpty()
										|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
									productBeanList.add(newLevel.getRelationshipLevelSid());
									selectedProductContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedProductContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedProductContainer.setChildrenAllowed(newLevel, false);
									}
									selectedProductContainer.setParent(newLevel, selectedParent2);
									selectedParent2 = newLevel;
								}
							}
							if (!newChildLevels.isEmpty()) {
								int pos3 = 0;
								String childHierarchyNo;
								Leveldto childsParent = null;
								for (Leveldto newLevel : newChildLevels) {
									String tempHNo = newLevel.getHierarchyNo();
									if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
										tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
									}
									pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos3 != -1) {
										childHierarchyNo = tempHNo.substring(0, pos3) + ".";
									} else {
										childHierarchyNo = tempHNo + ".";
									}
									if (productBeanList.isEmpty()
											|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
										productBeanList.add(newLevel.getRelationshipLevelSid());
										selectedProductContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedProductContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedProductContainer.setChildrenAllowed(newLevel, false);
										}
									}

									if (!StringUtils.isBlank(childHierarchyNo)) {
										for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
											if (childHierarchyNo
													.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
												childsParent = selectedLevel;
												break;
											}
										}
									}
									selectedProductContainer.setParent(newLevel, childsParent);
								}
							}
						}
					} else {
						Leveldto selectedParentLevel = DataSelectionUtil.getBeanFromId(item);
						// Root
						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						String currentHieNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						List<Leveldto> newChildLevels = null;
						int pos = 0;
						if (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
								pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos != -1) {
									hierarchyNo = hierarchyNo.substring(0, pos) + ".";
								}
							}
						}
						if (productBeanList.isEmpty() || !productBeanList
								.contains(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid())) {
							productBeanList.add(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid());
							selectedProductContainer.addBean(selectedParentLevel);
							if (forecastLevel != selectedParentLevel.getLevelNo()) {
								selectedProductContainer.setChildrenAllowed(selectedParentLevel, true);
							} else {
								selectedProductContainer.setChildrenAllowed(selectedParentLevel, false);
							}
						}
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHieNo,
								UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								productDescriptionMap, getBusinessUnit().getValue(), DataSelectionUtil.getBeanFromId(item),
								prodHieVersionNo, prodRelVersionNo);
						if (newChildLevels != null && !newChildLevels.isEmpty()) {
							int pos3 = 0;
							String childHierarchyNo;
							Leveldto childsParent = null;
							for (Leveldto newLevel : newChildLevels) {
								String tempHNo = newLevel.getHierarchyNo();
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos3 != -1) {
									childHierarchyNo = tempHNo.substring(0, pos3) + ".";
								} else {
									childHierarchyNo = tempHNo + ".";
								}
								if (productBeanList.isEmpty()
										|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
									productBeanList.add(newLevel.getRelationshipLevelSid());
									selectedProductContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedProductContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedProductContainer.setChildrenAllowed(newLevel, false);
									}
								}
								if (!StringUtils.isBlank(childHierarchyNo)) {
									for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
										if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
											childsParent = selectedLevel;
											break;
										}
									}
								}
								selectedProductContainer.setParent(newLevel, childsParent);
							}
						}
					}
				}
				DataSelectionLogic.selectedProductTableAlignmentChange(selectedProduct, selectedProductContainer);
			} else {
				AbstractNotificationUtils.getErrorNotification("No Product hierarchy level Selected",
						"No Level was selected to move. Please try again.");
			}
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	protected void deleteViewButtonLogic() {
		final ViewDTO dto = getViewDTO();
		new AbstractNotificationUtils() {
                        @Override
			public void noMethod() {
				// do nothing
			}

			@Override
			/**
			 * The method is triggered when Yes button of the message box is pressed .
			 *
			 * @param buttonId
			 *            The buttonId of the pressed button.
			 */
			public void yesMethod() {
				try {
					if (dto != null) {
						CFFLogic.deleteView(Integer.parseInt(dto.getViewId()));
						CommonUIUtils.getMessageNotification(dto.getViewName() + " has been successfully deleted.");
						deleteViewBtn.setEnabled(false);
						resetOne();
					}

				} catch (NumberFormatException exception) {
					LOGGER.error(exception.getMessage());
				}
			}
		}.getConfirmationMessage(Constants.MessageConstants.CONFIRM_DELETION_TITLE.getConstant(),
				Constants.MessageConstants.CONFIRM_DELETION_BODY.getConstant().replace("?#", dto.getViewName()));
	}

	@Override
	protected void resetButtonLogic() {
             DataSelectionLogic logic = new DataSelectionLogic();
             cffEligibleDate.setValue(logic.getDefaultEligibleDateFromForecastConfiguration());
		company.setValue(0);
		getBusinessUnit().setValue(0);
		customerBeanList.clear();
		productBeanList.clear();
	}

	/**
	 * Loads data selection after selecting a private/public view
	 *
	 * @param viewDTO
	 *            DTO with view parameters
	 */
	private void loadView(ViewDTO viewDTO) throws SystemException {
		company.setValue(Integer.valueOf(viewDTO.getCompanyMasterSid()));
		getBusinessUnit().setValue(viewDTO.getBusinessUnitSystemId());
		dismantleCustomerSelection = true;
		dismantleProductSelection = true;
		customerHierarchyDto = new HierarchyLookupDTO();
		productHierarchyDto = new HierarchyLookupDTO();
		viewId.setValue(viewDTO.getViewId());
		projectionId.setValue(viewDTO.getProjectionId());
		dataSelectionDTO.setProjectionId(UiUtils.parseStringToInteger(viewDTO.getProjectionId()));
		customerHierarchyDto.setHierarchyId(
				viewDTO.getCustomerHierarchySid() != null && viewDTO.getCustomerHierarchySid().equals(StringUtils.EMPTY)
						? 0
						: Integer.parseInt(viewDTO.getCustomerHierarchySid()));
		customerHierarchyDto.setHierarchyName(viewDTO.getCustomerHierarchy());
		productHierarchyDto.setHierarchyId(
				viewDTO.getProductHierarchySid() != null && viewDTO.getProductHierarchySid().equals(StringUtils.EMPTY)
						? 0
						: Integer.parseInt(viewDTO.getProductHierarchySid()));
		productHierarchyDto.setHierarchyName(viewDTO.getProductHierarchy());
		customerHierarchy.setValue(viewDTO.getCustomerHierarchy());
		productHierarchy.setValue(viewDTO.getProductHierarchy());

		RelationshipDdlbDto selectedCustomerRelationshipDdlbDto = null;
		if (!StringUtils.isBlank(viewDTO.getCustRelationshipBuilderSid())
				&& !Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCustRelationshipBuilderSid())) {
			selectedCustomerRelationshipDdlbDto = new RelationshipDdlbDto();
			selectedCustomerRelationshipDdlbDto.setRelationshipBuilderSid(viewDTO.getCustRelationshipBuilderSid());
		}
		loadRelationDdlb(UiUtils.parseStringToInteger(viewDTO.getCustomerHierarchySid()),
				selectedCustomerRelationshipDdlbDto, customerRelation);
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyGroupSid())
				&& !"0".equals(viewDTO.getCompanyGroupSid())
				&& !StringUtils.EMPTY.equals(viewDTO.getCompanyGroupSid())) {
			dataSelectionDTO.setCustomerGrpSid(viewDTO.getCompanyGroupSid());
		}
		customerGroup.setValue(viewDTO.getCustomerGroup());
		RelationshipDdlbDto selectedProductRelationshipDdlbDto = null;
		if (!StringUtils.isBlank(viewDTO.getProdRelationshipBuilderSid())
				&& !Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProdRelationshipBuilderSid())) {
			selectedProductRelationshipDdlbDto = new RelationshipDdlbDto();
			selectedProductRelationshipDdlbDto.setRelationshipBuilderSid(viewDTO.getProdRelationshipBuilderSid());
		}
		loadRelationDdlb(UiUtils.parseStringToInteger(viewDTO.getProductHierarchySid()),
				selectedProductRelationshipDdlbDto, productRelation);
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductGroupSid())
				&& !"0".equals(viewDTO.getProductGroupSid())
				&& !StringUtils.EMPTY.equals(viewDTO.getProductGroupSid())) {
			dataSelectionDTO.setProdGrpSid(viewDTO.getProductGroupSid());
		}
		productGroup.setValue(viewDTO.getProductGroup());
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCustomerHierarchySid())
				&& !"0".equals(viewDTO.getCustomerHierarchySid())
				&& !StringUtils.EMPTY.equals(viewDTO.getCustomerHierarchySid())) {

			loadCustomerLevel(viewDTO.getCustomerHierarchySid(), viewDTO.getCustomerLevel(),
					viewDTO.getCustHierarchyVersion());
                        int relationVersionNo = Integer.parseInt(
						customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
			int hierarchyVersionNo = Integer.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
                        customerDescriptionMap = relationLogic.getLevelValueMap(String.valueOf(customerRelation.getValue()),
						customerHierarchyDto.getHierarchyId(), hierarchyVersionNo, relationVersionNo);
			initializeCustomerHierarchy(UiUtils.parseStringToInteger(viewDTO.getProjectionId()),
					viewDTO.getCustomerLevel());
			loadInnerCustomerLevel(Integer.parseInt(viewDTO.getCustomerLevel()),
					UiUtils.parseStringToInteger(viewDTO.getCustomerInnerLevel()),
					UiUtils.parseStringToInteger(viewDTO.getCustomerHierarchySid()), viewDTO.getCustHierarchyVersion());
			dataSelectionDTO.setCustomerHierSid(viewDTO.getCustomerHierarchySid());
			if (level.getValue() != null) {
				int forecastLevel = UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]);
				dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(forecastLevel));
				dataSelectionDTO.setCustomerHierarchyInnerLevel(String.valueOf(forecastLevel));
			}
		}
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyGroupSid())
				&& !"0".equals(viewDTO.getCompanyGroupSid())
				&& !StringUtils.EMPTY.equals(viewDTO.getCompanyGroupSid())) {
			dataSelectionDTO.setCustomerGrpSid(viewDTO.getCompanyGroupSid());
			triggerCustGrpOnView(viewDTO.getCompanyGroupSid(), true);
		}
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductHierarchySid())
				&& !"0".equals(viewDTO.getProductHierarchySid())
				&& !StringUtils.EMPTY.equals(viewDTO.getProductHierarchySid())) {
			loadProductLevel(viewDTO.getProductHierarchySid(), viewDTO.getProductLevel(),
					viewDTO.getProdHierarchyVersion());
			initializeProductHierarchy(UiUtils.parseStringToInteger(viewDTO.getProjectionId()),
					viewDTO.getProductLevel());
			loadInnerProductLevel(Integer.parseInt(viewDTO.getProductLevel()),
					UiUtils.parseStringToInteger(viewDTO.getProductInnerLevel()),
					UiUtils.parseStringToInteger(viewDTO.getProductHierarchySid()), viewDTO.getProdHierarchyVersion());
			dataSelectionDTO.setProdHierSid(viewDTO.getProductHierarchySid());
			if (productlevelDdlb.getValue() != null) {
				int forecastLevel = UiUtils
						.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]);
				dataSelectionDTO.setProductHierarchyLevel(String.valueOf(forecastLevel));
				dataSelectionDTO.setProductHierarchyInnerLevel(String.valueOf(forecastLevel));
			}
		}
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductGroupSid())
				&& !"0".equals(viewDTO.getProductGroupSid())
				&& !StringUtils.EMPTY.equals(viewDTO.getProductGroupSid())) {
			dataSelectionDTO.setProdGrpSid(viewDTO.getProductGroupSid());
			triggerProdGrpOnView(viewDTO.getProductGroupSid(), false);
		}
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyMasterSid())
				&& !"0".equals(viewDTO.getCompanyMasterSid())
				&& !StringUtils.EMPTY.equals(viewDTO.getCompanyMasterSid())) {
			dataSelectionDTO.setCompanySid(viewDTO.getCompanyMasterSid());
		}
		deleteViewBtn.setEnabled(true);
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyGroupSid())
				&& !"0".equals(viewDTO.getCompanyGroupSid())
				&& !StringUtils.EMPTY.equals(viewDTO.getCompanyGroupSid())) {
			dataSelectionDTO.setCustomerGrpSid(viewDTO.getCompanyGroupSid());
			selectedCustomerGroupDTO.setCustomerGroupSid(String.valueOf(viewDTO.getCompanyGroupSid()));
		}
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductGroupSid())
				&& !"0".equals(viewDTO.getProductGroupSid())
				&& !StringUtils.EMPTY.equals(viewDTO.getProductGroupSid())) {
			dataSelectionDTO.setProdGrpSid(viewDTO.getProductGroupSid());
			selectedProductGroupDTO.setProductGroupSid(String.valueOf(viewDTO.getProductGroupSid()));
		}
	}

	private void initializeCompanyCombobox() {
		DataSelectionUtil.getCompanySidFromHierarchy(getCompanySidFromHierarchy(false, true), screenName);

	}

	public static void loadRelationDdlb(final int hierarchyDefinitionSid,
			final RelationshipDdlbDto selectedRelationshipDdlbDto, final ComboBox relationship) {
		LOGGER.debug("Logging - loadRelationDdlb hierarchyDefinitionSid: {} ", hierarchyDefinitionSid);
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			List<RelationshipDdlbDto> relationshipSidList = logic.getRelationshipSid(hierarchyDefinitionSid);
			relationship.removeAllItems();
			for (RelationshipDdlbDto relationshipDto : relationshipSidList) {
				relationship.addItem(relationshipDto.getRelationshipBuilderSid());
				relationship.setItemCaption(relationshipDto.getRelationshipBuilderSid(),
						relationshipDto.getRelationshipName());
			}
			if (selectedRelationshipDdlbDto != null) {
				relationship.select(selectedRelationshipDdlbDto.getRelationshipBuilderSid());
			}
			relationship.setPageLength(NumericConstants.SEVEN);
			relationship.setNullSelectionAllowed(true);
			relationship.setInputPrompt("-Select One-");
		} catch (PortalException | SystemException | UnsupportedOperationException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	/**
	 * getCompanySidFromHierarchy
	 *
	 * @param loadFromSelected
	 * @param dsTabflag
	 * @return
	 */
	public List<Leveldto> getCompanySidFromHierarchy(final boolean loadFromSelected, final boolean dsTabflag) {
		List<Leveldto> innerLevelValues = null;
		try {
			DataSelectionLogic logic = new DataSelectionLogic();

			if (dsTabflag) {
				int hierarchyId = 0;
				if (productHierarchyDto == null) {
					hierarchyId = UiUtils.parseStringToInteger(dataSelectionDTO.getProdHierSid());
				} else {
					hierarchyId = productHierarchyDto.getHierarchyId();
				}
				if (innerProdLevels == null || innerProdLevels.isEmpty() || productHierarchyDto == null) {
					innerProdLevels = logic.loadCustomerForecastLevel(hierarchyId,
							dataSelectionDTO.getProductHierVersionNo());
				}

			}
			if ((innerProdLevels != null) && (productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue()))) {
				String relationshipSid = String.valueOf(productRelation.getValue());
				Leveldto companyLevel = null;
				for (Leveldto dto : innerProdLevels) {
					if (StringConstantsUtil.COMPANY_LABEL.equalsIgnoreCase(dto.getLevel())
							|| "GL Comp".contains(dto.getLevel()) || "GL Company".contains(dto.getLevel())) {
						companyLevel = dto;
						break;
					}
				}
				List<Integer> selectedLevelSids = null;
				if (loadFromSelected) {
					selectedLevelSids = DataSelectionUtil
							.getSelectedRelationshipLevelSids(selectedProductContainer.getItemIds());
				}

				if (companyLevel != null && productHierarchyDto != null) {
					innerLevelValues = logic.loadInnerLevel(companyLevel.getLevel(),
							productHierarchyDto.getHierarchyId(), selectedLevelSids, false, companyLevel.getFieldName(),
							relationshipSid, productDescriptionMap, StringUtils.EMPTY, screenName,
							0, companyLevel.getLevelNo(),
							company.getValue(), getBusinessUnit().getValue());
				}
			}
		} catch (SystemException ex) {
			LOGGER.error(" in getCompanySidFromHierarchy= {}", ex);
		}
		return innerLevelValues;
	}

	private void setRelationshipBuilderSids(String rbSid) {
		// order is preserved
		relationshipBuilderSids.add(rbSid);
	}

	public void loadFilteredProductSelection(final String selectedLevel) {
		try {
			availableProductContainer.removeAllItems();
			int forecastLevel = 0;
			boolean isNdc = false;
			List<Leveldto> selectedCustomerContractList;
			String levelName = StringConstantsUtil.LEVEL;
			List<Leveldto> resultedLevelsList;
			if (selectedLevel != null && !Constants.CommonConstants.NULL.getConstant().equals(selectedLevel)
					&& !SELECT_ONE.equals(selectedLevel)) {
				productFuture.get();
				int productRelationVersionNo = Integer.parseInt(
						productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
				String customerVersionNo = customerRelationVersionComboBox
						.getItemCaption(customerRelationVersionComboBox.getValue());
				int customerRelationVersionNo = customerVersionNo == null ? 0 : Integer.parseInt(customerVersionNo);
				int hierarchyVersionNo = Integer.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
				String relationshipSid = String.valueOf(productRelation.getValue());

				String[] val = selectedLevel.split(" ");
				forecastLevel = Integer.parseInt(val[1]);
				List<Leveldto> productHierarchyLevelDefinitionList = relationLogic
						.getHierarchyLevelDefinition(productHierarchyDto.getHierarchyId(), hierarchyVersionNo);
				Leveldto selectedHierarchyLevelDto = productHierarchyLevelDefinitionList.get(forecastLevel - 1);
				isNdc = (selectedHierarchyLevelDto.getLevel().equalsIgnoreCase("Package")
						|| selectedHierarchyLevelDto.getLevel().equalsIgnoreCase("NDC-11")); 

				selectedCustomerContractList = getSelectedCustomerContractList();

				List<String> tempGroupFileter = groupFilteredItems == null ? Collections.<String>emptyList()
						: groupFilteredItems;
				resultedLevelsList = relationLogic.loadAvailableProductlevel(selectedHierarchyLevelDto,
						Integer.parseInt(relationshipSid), tempGroupFileter, selectedCustomerContractList, isNdc,
						"", "", productRelationVersionNo, customerRelationVersionNo, getBusinessUnit().getValue(),
						productDescriptionMap);
				if (selectedHierarchyLevelDto.getLevel() != null) {
					levelName = selectedHierarchyLevelDto.getLevel();
				}
				availableProduct.removeAllItems();
				availableProductContainer.removeAllItems();
				availableProductContainer.addAll(resultedLevelsList);
			}

			availableProduct.setContainerDataSource(availableProductContainer);
			if (isNdc) {
				availableProduct.setVisibleColumns(StringConstantsUtil.DISPLAY_VALUE, "form", "strength");
				availableProduct.setColumnHeaders(StringConstantsUtil.NDC, "Form", "Strength");
			} else {
				availableProduct.setVisibleColumns(StringConstantsUtil.DISPLAY_VALUE);
				availableProduct.setColumnHeaders(levelName);
			}
			availableProduct.setFilterBarVisible(true);
			availableProduct.setFilterDecorator(new ExtDemoFilterDecorator());
			availableProduct.setStyleName(StringConstantsUtil.FILTER_TABLE);

		} catch (CloneNotSupportedException | InterruptedException | NumberFormatException | ExecutionException ex) {

			LOGGER.error(" - in loadFilteredProductSelection= {}", ex);
		}
	}

	private List<Leveldto> getSelectedCustomerContractList() {
		List<Leveldto> ccList = Collections.emptyList();
		if (selectedCustomerContainer != null && !selectedCustomerContainer.getItemIds().isEmpty()) {
			Leveldto currentDto;
			ccList = new ArrayList<>();
			for (final Object item : selectedCustomerContainer.getItemIds()) {
				currentDto = DataSelectionUtil.getBeanFromId(item);
				if (currentDto != null && !StringUtils.isBlank(currentDto.getTableName())) {
					ccList.add(currentDto);
				}
			}
		}
		return ccList;
	}

	public List<Leveldto> getCustomerHierarchyEndLevels(final ExtTreeContainer<Leveldto> selectedCustomerContainer) {
		List<Leveldto> customerHierarchyEndLevels = new ArrayList<>();
		for (Object item : selectedCustomerContainer.getItemIds()) {
			if (!selectedCustomerContainer.hasChildren(item)) {
				customerHierarchyEndLevels.add(DataSelectionUtil.getBeanFromId(item));
			}
		}
		return customerHierarchyEndLevels;
	}

	private List<Leveldto> getItemSidFromHierarchy() {
		List<Leveldto> innerLevelValues = null;
		try {
			if ((innerProdLevels != null) && (productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue()))) {
				String relationshipSid = String.valueOf(productRelation.getValue());
				DataSelectionLogic logic = new DataSelectionLogic();
				Leveldto ndcLevel = null;
				for (Leveldto dto : innerProdLevels) {
					if ("NDC".equalsIgnoreCase(dto.getLevel()) || "Item".equalsIgnoreCase(dto.getLevel())) {
						ndcLevel = dto;
						break;
					}
				}
				if (ndcLevel != null) {
					innerLevelValues = logic.loadInnerLevel(ndcLevel.getLevel(), productHierarchyDto.getHierarchyId(),
							DataSelectionUtil.getSelectedRelationshipLevelSids(selectedProductContainer.getItemIds()),
							true, ndcLevel.getFieldName(), relationshipSid, productDescriptionMap,
							INDICATOR_LEVEL_NDC.getConstant(), screenName,
							0, ndcLevel.getLevelNo(),
							company.getValue(), getBusinessUnit().getValue());
				}
			}
		} catch (SystemException ex) {
			LOGGER.error(" in getItemSidFromHierarchy= {}", ex);
		}
		return innerLevelValues;
	}

	/**
	 * Manual trigger and processing of customer group select button logic Code
	 * based on customer group select button logic Any change made there, should be
	 * made here accordingly
	 *
	 * @param customerGrpSid
	 */
	public void triggerCustGrpOnView(String customerGrpSid, final boolean isFilter) {

		List<String> companySids = null;
		List<String> customerSidsFromDetails = null;
		DataSelectionLogic dslogic = new DataSelectionLogic();
		try {
			if (!StringUtils.isBlank(customerGrpSid) && !NULL.equalsIgnoreCase(customerGrpSid)) {
				companySids = DataSelectionUtil.getCustomerSidFromHierarchy(getCustomersFromHierarchy());
				if (companySids != null) {
					List<String> companySidList = new ArrayList<>(companySids);
					customerSidsFromDetails = dslogic.getCustomerGroupDetails(Integer.parseInt(customerGrpSid));
					companySidList.retainAll(customerSidsFromDetails);
					groupFilteredCompanies = new ArrayList<>(companySidList);
				}
				if (isFilter) {
					filterCustomerOnGroupSelect();
				}
			}
		} catch (SystemException | NumberFormatException ex) {
			LOGGER.error(" at triggerCustGrpOnView= {}", ex);
		}
	}

	/**
	 * Manual trigger and processing of product group select button logic Code based
	 * on product group select button logic Any change made there, should be made
	 * here accordingly
	 *
	 * @param productGrpSid
	 */
	public void triggerProdGrpOnView(String productGrpSid, final boolean filter) {
		try {
			if (!StringUtils.isBlank(productGrpSid) && !NULL.equalsIgnoreCase(productGrpSid)) {
				List<String> itemSids = null;
				List<String> itemSidsFromDetails = null;
				DataSelectionLogic logic = new DataSelectionLogic();
				itemSids = DataSelectionUtil.getItemSidFromHierarchy(getItemSidFromHierarchy());
				if (itemSids != null) {
					List<String> finalItemSids = new ArrayList<>(itemSids);
					itemSidsFromDetails = logic.getItemGroupDetails(Integer.parseInt(productGrpSid));
					finalItemSids.retainAll(itemSidsFromDetails);
					groupFilteredItems = new ArrayList<String>(finalItemSids);
				}
				if (filter && productlevelDdlb.getValue() != null
						&& !String.valueOf(SELECT_ONE).equalsIgnoreCase(String.valueOf(productlevelDdlb.getValue()))) {
					loadFilteredProductSelection(String.valueOf(productlevelDdlb.getValue()));
				}
			}
		} catch (SystemException | NumberFormatException ex) {
			LOGGER.error(" at triggerProdGrpOnView= {}", ex);
		}
	}

	private void filterCustomerOnGroupSelect() {
		LOGGER.debug("customer filterCustomerOnGroupSelect");
		availableCustomerContainer.removeAllItems();
		String levelName = StringConstantsUtil.LEVEL;
		try {
			int forecastLevel = 0;
			boolean isNdc = false;
			List<Leveldto> custVlues = null;
			if (level.getValue() != null && customerRelation.getValue() != null
					&& !SELECT_ONE.equals(customerRelation.getValue())) {
				String selectedLevel = String.valueOf(level.getValue());
				if (customerRelation.getValue() != null && !SELECT_ONE.equals(customerRelation.getValue())) {
					String relationshipSid = String.valueOf(customerRelation.getValue());
					DataSelectionLogic logic = new DataSelectionLogic();
					String[] val = selectedLevel.split(" ");
					forecastLevel = Integer.parseInt(val[1]);
					Leveldto tempDto = (Leveldto) innerCustLevels.get(forecastLevel - 1);
					if (tempDto.getLevel() != null) {
						levelName = tempDto.getLevel();
					}
					if (tempDto.getLevel() != null
							&& ("NDC".equalsIgnoreCase(tempDto.getLevel())
									|| "Item".equalsIgnoreCase(tempDto.getLevel()))
							&& StringConstantsUtil.ITEM_MASTER.equals(tempDto.getTableName())) {
						isNdc = true;
					} else {
						isNdc = false;
					}
					custVlues = logic.loadInnerLevel(tempDto.getLevel(), customerHierarchyDto.getHierarchyId(),
							DataSelectionUtil.getSelectedRelationshipLevelSids(selectedCustomerContainer.getItemIds()),
							isNdc, tempDto.getFieldName(), relationshipSid, customerDescriptionMap,
							DataSelectionUtil.identifyLevel(tempDto), screenName,
							0, tempDto.getLevelNo(),
							company.getValue(), getBusinessUnit().getValue());

					if (groupFilteredCompanies != null
							&& COMPANY_MASTER_TABLE.equalsIgnoreCase(String.valueOf(tempDto.getTableName()))
							&& (TRADING_PARTNER.equals(levelName)
									|| StringConstantsUtil.COMPANY_LABEL.equals(levelName))) {
						List<Leveldto> filteredValues = new ArrayList<>();
						if (!groupFilteredCompanies.isEmpty()) {
							try {
								for (Leveldto leveldto : custVlues) {
									if (groupFilteredCompanies.contains(leveldto.getRelationshipLevelValue().trim())) {
										filteredValues.add(leveldto);
									}
								}
							} catch (Exception ex) {
								LOGGER.error(" level ValueChangeListener = {}", ex);
							}

						}
						availableCustomerContainer.addAll(filteredValues);
					} else {
						availableCustomerContainer.addAll(custVlues);
					}

				}
			}
			availableCustomer.setContainerDataSource(availableCustomerContainer);
			availableCustomer.setVisibleColumns(StringConstantsUtil.DISPLAY_VALUE);
			availableCustomer.setColumnHeaders(levelName);
		} catch (final SystemException | NumberFormatException ex) {
			LOGGER.error(" filterCustomerOnGroupSelect = {}", ex);
		}
	}

	private List<Leveldto> getCustomersFromHierarchy() {
		List<Leveldto> innerLevelValues = null;
		try {
			if ((innerCustLevels != null) && (customerRelation.getValue() != null && !SELECT_ONE.equals(customerRelation.getValue()))) {
				String relationshipSid = String.valueOf(customerRelation.getValue());
				DataSelectionLogic logic = new DataSelectionLogic();
				Leveldto customerLevelDto = null;
				for (Leveldto dto : innerCustLevels) {
					if ("Customer".equalsIgnoreCase(dto.getLevel())
							|| StringConstantsUtil.COMPANY_LABEL.equalsIgnoreCase(dto.getLevel())
							|| TRADING_PARTNER.equalsIgnoreCase(dto.getLevel())) {
						customerLevelDto = dto;
						break;
					}
				}
				if (customerLevelDto != null) {
					innerLevelValues = logic.loadInnerLevel(customerLevelDto.getLevel(),
							customerHierarchyDto.getHierarchyId(),
							DataSelectionUtil.getSelectedRelationshipLevelSids(selectedCustomerContainer.getItemIds()),
							false, customerLevelDto.getFieldName(), relationshipSid, customerDescriptionMap,
							INDICATOR_LEVEL_CUSTOMER.getConstant(), screenName,0, customerLevelDto.getLevelNo(),
							company.getValue(), getBusinessUnit().getValue());
				}
			}
		} catch (SystemException ex) {
			LOGGER.error(" in getCustomersFromHierarchy= {}", ex);
		}
		return innerLevelValues;
	}

	public DataSelectionDTO bindDataselectionDtoToSave() {
		if (company.getValue() != null && !SELECT_ONE.equals(company.getValue())) {
			dataSelectionDTO.setCompanySid(String.valueOf(company.getValue()));
		} else {
			dataSelectionDTO.setCompanySid(String.valueOf(0));
		}
		if (getBusinessUnit().getValue() != null && !SELECT_ONE.equals(getBusinessUnit().getValue())) {
			dataSelectionDTO.setBusinessUnitSystemId((Integer) getBusinessUnit().getValue());
		} else {
			dataSelectionDTO.setBusinessUnitSystemId(0);
		}
		if (customerRelation.getValue() != null && !SELECT_ONE.equals(customerRelation.getValue())) {
			dataSelectionDTO.setCustRelationshipBuilderSid(String.valueOf(customerRelation.getValue()));
		} else {
			dataSelectionDTO.setCustRelationshipBuilderSid(String.valueOf(0));
		}
		if (productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue())) {
			dataSelectionDTO.setProdRelationshipBuilderSid(String.valueOf(productRelation.getValue()));
		} else {
			dataSelectionDTO.setProdRelationshipBuilderSid(String.valueOf(0));
		}
		if (customerHierarchyDto != null
				&& !Constants.CommonConstants.NULL.getConstant()
						.equals(String.valueOf(customerHierarchyDto.getHierarchyId()))
				&& !StringUtils.isEmpty(String.valueOf(customerHierarchyDto.getHierarchyId()))
				&& !StringUtils.isBlank(String.valueOf(customerHierarchyDto.getHierarchyId()))) {
			dataSelectionDTO.setCustomerHierSid(String.valueOf(customerHierarchyDto.getHierarchyId()));
		} else {
			dataSelectionDTO.setCustomerHierSid(String.valueOf(0));
		}
		if (productHierarchyDto != null
				&& !Constants.CommonConstants.NULL.getConstant()
						.equals(String.valueOf(productHierarchyDto.getHierarchyId()))
				&& !StringUtils.isEmpty(String.valueOf(productHierarchyDto.getHierarchyId()))
				&& !StringUtils.isBlank(String.valueOf(productHierarchyDto.getHierarchyId()))) {
			dataSelectionDTO.setProdHierSid(String.valueOf(productHierarchyDto.getHierarchyId()));
		} else {
			dataSelectionDTO.setProdHierSid(String.valueOf(0));
		}

		if (selectedCustomerGroupDTO != null
				&& !Constants.CommonConstants.NULL.getConstant()
						.equals(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()))
				&& !StringUtils.isEmpty(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()))
				&& !StringUtils.isBlank(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()))) {
			dataSelectionDTO.setCustomerGrpSid(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()));
		} else {
			dataSelectionDTO.setCustomerGrpSid(String.valueOf(0));
		}
		if (selectedProductGroupDTO != null
				&& !Constants.CommonConstants.NULL.getConstant()
						.equals(String.valueOf(selectedProductGroupDTO.getProductGroupSid()))
				&& !StringUtils.isEmpty(String.valueOf(selectedProductGroupDTO.getProductGroupSid()))
				&& !StringUtils.isBlank(String.valueOf(selectedProductGroupDTO.getProductGroupSid()))) {
			dataSelectionDTO.setProdGrpSid(String.valueOf(selectedProductGroupDTO.getProductGroupSid()));
		} else {
			dataSelectionDTO.setProdGrpSid(String.valueOf(0));
		}
		dataSelectionDTO.setProductHierarchy(productHierarchy.getValue());
		dataSelectionDTO.setCustomerHierarchy(customerHierarchy.getValue());
		if (customerHierarchyDto != null) {
			dataSelectionDTO.setCustomerHierVersionNo(
					Integer.parseInt(String.valueOf(customerRelationVersionComboBox.getValue())));
			dataSelectionDTO.setCustomerRelationShipVersionNo(Integer.parseInt(
					customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue())));
		} else {
			dataSelectionDTO.setCustomerHierVersionNo(0);
			dataSelectionDTO.setCustomerRelationShipVersionNo(0);
		}
		if (productHierarchyDto != null) {
			dataSelectionDTO.setProductHierVersionNo(
					Integer.parseInt(String.valueOf(productRelationVersionComboBox.getValue())));
			dataSelectionDTO.setProductRelationShipVersionNo(Integer.parseInt(
					productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue())));
		} else {
			dataSelectionDTO.setProductHierVersionNo(0);
			dataSelectionDTO.setProductRelationShipVersionNo(0);
		}

		if (discount.getValue() != null && !SELECT_ONE.equals(discount.getValue())
				&& StringUtils.isNotBlank(discount.getValue().toString())) {
			CompanyDdlbDto dto = (CompanyDdlbDto) discount.getValue();
			dataSelectionDTO.setDiscount(dto.getRsNo());
			dataSelectionDTO.setDiscountSid(dto.getRsModelSid());
		} else {
			dataSelectionDTO.setDiscount(SELECT_ONE);
			dataSelectionDTO.setDiscountSid(0);
		}
                sessionDTO.setCffEligibleDate(cffEligibleDate.getValue());
                dataSelectionDTO.setCustomViewMasterSid(Integer.parseInt(String.valueOf(customViewDdlb.getValue())));
		return dataSelectionDTO;
	}

	public List<Leveldto> getProductHierarchyEndLevels(final ExtTreeContainer<Leveldto> selectedProductContainer) {
		LOGGER.debug("--inside getProductHierarchyEndLevels------------------------->>>>>");
		List<Leveldto> productHierarchyEndLevels = new ArrayList<>();
		for (Object item : selectedProductContainer.getItemIds()) {
			if (!selectedProductContainer.hasChildren(item)) {
				productHierarchyEndLevels.add(DataSelectionUtil.getBeanFromId(item));
			}
		}
		LOGGER.debug("-- getProductHierarchyEndLevels size------------------------->>>>> {}", productHierarchyEndLevels.size());
		return productHierarchyEndLevels;
	}

	public String getProductHierarchyEndLevelsHierNo(final ExtTreeContainer<Leveldto> selectedProductContainer) {

		StringBuilder returnString = new StringBuilder();

		List<String> productHieEndLevelsHierNo = new ArrayList<>();
		for (Object item : selectedProductContainer.getItemIds()) {
			if (!selectedProductContainer.hasChildren(item)) {
				productHieEndLevelsHierNo.add(DataSelectionUtil.getBeanFromId(item).getHierarchyNo());
			}
		}
		if (!productHieEndLevelsHierNo.isEmpty()) {
			for (int loop = 0, limit = productHieEndLevelsHierNo.size(); loop < limit; loop++) {
				returnString.append('\'');
				returnString.append(productHieEndLevelsHierNo.get(loop));
				returnString.append('\'');
				if (loop != (limit - 1)) {
					returnString.append(", ");
				}
			}
		}
		return returnString.toString();
	}

	private void initializeProductHierarchy(final int projectionId, final String productLevel) {
		LOGGER.debug("Intializind Product Hierarchy");
		int forecastLevel = 0;
		forecastLevel = UiUtils.parseStringToInteger(productLevel);
		List<Leveldto> reslistOne;
		reslistOne = relationLogic.getRelationShipValues(projectionId, BooleanConstant.getFalseFlag(), productLevel,
				productDescriptionMap);
		LOGGER.debug("relist===========: {}", reslistOne.toString());

		productBeanList.clear();
		for (final Leveldto dto : reslistOne) {
			productBeanList.add(dto.getRelationshipLevelSid());
		}

		for (final Leveldto dto : reslistOne) {
			if (dto.getLevelNo() == 1) {
				selectedProductContainer.removeAllItems();
				selectedProductContainer.addItem(dto);
				selectedProductContainer.setChildrenAllowed(dto, true);
			} else {
				for (Object tempdto : selectedProductContainer.getItemIds()) {
					if (dto.getParentNode().contains("~")) {
						final String[] parentarr = dto.getParentNode().split("~");
						final String parentName = parentarr[1];
						if (getBeanFromId(tempdto).getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
							selectedProductContainer.addBean(dto);
							if (forecastLevel != dto.getLevelNo()) {
								selectedProductContainer.setChildrenAllowed(dto, true);
							} else {
								selectedProductContainer.setChildrenAllowed(dto, false);
							}
							selectedProductContainer.setParent(dto, tempdto);
							break;
						}
					} else {
						selectedProductContainer.addBean(dto);
						if (forecastLevel != dto.getLevelNo()) {
							selectedProductContainer.setChildrenAllowed(dto, true);
						} else {
							selectedProductContainer.setChildrenAllowed(dto, false);
						}
						selectedProductContainer.setParent(dto, tempdto);
						break;
					}
				}
			}
		}
		selectedProduct.setContainerDataSource(selectedProductContainer);
		selectedProduct.setVisibleColumns(StringConstantsUtil.DISPLAY_VALUE);
		selectedProduct.setColumnHeaders("Product Hierarchy Group Builder");
		for (final Leveldto ddo : selectedProductContainer.getItemIds()) {
			selectedProduct.setCollapsed(ddo, false);
		}
	}

	private void initializeFromDto() {
            DataSelectionLogic logic = new DataSelectionLogic();
		try {

			if (dataSelectionDTO != null) {
				sessionDTO.setProductLevelNumber(String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
				sessionDTO.setCustomerLevelNumber(String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));

				if (dataSelectionDTO.getCustomerHierSid() != null
						&& !StringUtils.EMPTY.equals(String.valueOf(dataSelectionDTO.getCustomerHierSid()))
						&& !Constants.CommonConstants.NULL.getConstant()
								.equals(String.valueOf(dataSelectionDTO.getCustomerHierSid()))) {
                                        customerHierarchyDto = new HierarchyLookupDTO();
					customerHierarchyDto
							.setHierarchyId(UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierSid()));
					customerHierarchyDto.setHierarchyName(dataSelectionDTO.getCustomerHierarchy());
					customerHierarchy.setValue(customerHierarchyDto.getHierarchyName());

					RelationshipDdlbDto selectedRelationshipDdlbDto = null;
					if (!StringUtils.isBlank(dataSelectionDTO.getCustRelationshipBuilderSid())
							&& !Constants.CommonConstants.NULL.getConstant()
									.equals(dataSelectionDTO.getCustRelationshipBuilderSid())) {
						selectedRelationshipDdlbDto = new RelationshipDdlbDto();
						selectedRelationshipDdlbDto
								.setRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
						sessionDTO.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
					}
					DataSelection.loadRelationDdlb(UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierSid()),
							selectedRelationshipDdlbDto, customerRelation);
				}
				if (dataSelectionDTO.getCustomerHierSid() != null
						&& !StringUtils.EMPTY.equals(String.valueOf(dataSelectionDTO.getCustomerHierSid()))
						&& !Constants.CommonConstants.NULL.getConstant()
								.equals(String.valueOf(dataSelectionDTO.getCustomerHierSid()))) {
					
					loadCustomerLevel(String.valueOf(dataSelectionDTO.getCustomerHierSid()),
							dataSelectionDTO.getCustomerHierVersionNo());
					if (!StringUtils.isBlank(dataSelectionDTO.getCustomerHierarchyInnerLevel())
							&& !Constants.CommonConstants.NULL.getConstant()
									.equals(dataSelectionDTO.getCustomerHierarchyInnerLevel())) {
						loadInnerCustomerLevel(
								UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierarchyLevel()),
								UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierarchyInnerLevel()),
								UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierSid()),
								dataSelectionDTO.getCustomerHierVersionNo());
					}
				}
				if (dataSelectionDTO.getProdHierSid() != null
						&& !StringUtils.EMPTY.equals(String.valueOf(dataSelectionDTO.getProdHierSid()))
						&& !Constants.CommonConstants.NULL.getConstant()
								.equals(String.valueOf(dataSelectionDTO.getProdHierSid()))) {
                                     productHierarchyDto = new HierarchyLookupDTO();
					productHierarchyDto.setHierarchyId(UiUtils.parseStringToInteger(dataSelectionDTO.getProdHierSid()));
					productHierarchyDto.setHierarchyName(dataSelectionDTO.getProductHierarchy());
					productHierarchy.setValue(productHierarchyDto.getHierarchyName());
					RelationshipDdlbDto selectedRelationshipDdlbDto = null;
					if (!StringUtils.isBlank(dataSelectionDTO.getProdRelationshipBuilderSid())
							&& !Constants.CommonConstants.NULL.getConstant()
									.equals(dataSelectionDTO.getProdRelationshipBuilderSid())) {
						selectedRelationshipDdlbDto = new RelationshipDdlbDto();
						selectedRelationshipDdlbDto
								.setRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
						sessionDTO.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
					}
					DataSelection.loadRelationDdlb(UiUtils.parseStringToInteger(dataSelectionDTO.getProdHierSid()),
							selectedRelationshipDdlbDto, productRelation);
				}
				if (dataSelectionDTO.getProdHierSid() != null
						&& !StringUtils.EMPTY.equals(String.valueOf(dataSelectionDTO.getProdHierSid()))
						&& !Constants.CommonConstants.NULL.getConstant()
								.equals(String.valueOf(dataSelectionDTO.getProdHierSid()))) {
					
					loadProductLevel(String.valueOf(dataSelectionDTO.getProdHierSid()),
							dataSelectionDTO.getProductHierVersionNo());
					if (!StringUtils.isBlank(dataSelectionDTO.getProductHierarchyInnerLevel())
							&& !Constants.CommonConstants.NULL.getConstant()
									.equals(dataSelectionDTO.getProductHierarchyInnerLevel())) {
						loadInnerProductLevel(UiUtils.parseStringToInteger(dataSelectionDTO.getProductHierarchyLevel()),
								UiUtils.parseStringToInteger(dataSelectionDTO.getProductHierarchyInnerLevel()),
								UiUtils.parseStringToInteger(dataSelectionDTO.getProdHierSid()),
								dataSelectionDTO.getProductHierVersionNo());
					}
				}

				if (dataSelectionDTO.getProdGrpSid() != null
						&& !StringUtils.EMPTY.equals(String.valueOf(dataSelectionDTO.getProdGrpSid()))
						&& !Constants.CommonConstants.NULL.getConstant()
								.equals(String.valueOf(dataSelectionDTO.getProdGrpSid()))) {
					selectedProductGroupDTO = new GroupDTO();
					selectedProductGroupDTO.setProductGroupSid(String.valueOf(dataSelectionDTO.getProdGrpSid()));
					selectedProductGroupDTO.setProductGroupName(dataSelectionDTO.getProductGroup());
					triggerProdGrpOnView(dataSelectionDTO.getProdGrpSid(), true);
				}
				if (dataSelectionDTO.getCustomerGrpSid() != null
						&& !StringUtils.EMPTY.equals(String.valueOf(dataSelectionDTO.getCustomerGrpSid()))
						&& !Constants.CommonConstants.NULL.getConstant()
								.equals(String.valueOf(dataSelectionDTO.getCustomerGrpSid()))) {
					selectedCustomerGroupDTO = new GroupDTO();
					selectedCustomerGroupDTO.setCustomerGroupSid(String.valueOf(dataSelectionDTO.getCustomerGrpSid()));
					selectedCustomerGroupDTO.setCustomerGroupName(dataSelectionDTO.getCustomerGroup());
					triggerCustGrpOnView(dataSelectionDTO.getCustomerGrpSid(), true);
				}

				if (dataSelectionDTO.getSelectedCompanyName() == null || Constants.CommonConstants.NULL.getConstant()
						.equalsIgnoreCase(dataSelectionDTO.getSelectedCompanyName())) {
					company.setValue(StringUtils.EMPTY);
				} else {
					company.setValue(dataSelectionDTO.getSelectedCompanyName());
				}
                                if (sessionDTO.getCffEligibleDate()!=null) {
                                       cffEligibleDate.setValue(sessionDTO.getCffEligibleDate());
                                } else {
                                      cffEligibleDate.setValue(logic.getDefaultEligibleDateFromForecastConfiguration());
                                }
			}
		} catch (Property.ReadOnlyException | Converter.ConversionException ex) {
			LOGGER.error(" in initializeFromDto = {}", ex);
		}
	}

	private void initializeCustomerHierarchy(final int projectionId, final String customerLevel) {
		LOGGER.debug("Initializing Customer Hierarchy...");
		List<Leveldto> initialCustomerHierarchy = relationLogic.getRelationShipValues(projectionId, BooleanConstant.getTrueFlag(),
				customerLevel, customerDescriptionMap);
		int forecastLevel = 0;
		forecastLevel = UiUtils.parseStringToInteger(customerLevel);
		customerBeanList.clear();
		for (final Leveldto dto : initialCustomerHierarchy) {
			customerBeanList.add(dto.getRelationshipLevelSid());
		}

		for (final Leveldto levelDto : initialCustomerHierarchy) {
			if (levelDto.getLevelNo() == 1) {
				selectedCustomerContainer.removeAllItems();
				selectedCustomerContainer.addItem(levelDto);
				selectedCustomerContainer.setChildrenAllowed(levelDto, true);
			} else {
				for (Object tempdto : selectedCustomerContainer.getItemIds()) {
					if (levelDto.getParentNode().contains("~")) {
						final String[] parentarr = levelDto.getParentNode().split("~");
						final String parentName = parentarr[1];
						if (getBeanFromId(tempdto).getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
							selectedCustomerContainer.addBean(levelDto);
							if (forecastLevel != levelDto.getLevelNo()) {
								selectedCustomerContainer.setChildrenAllowed(levelDto, true);
							} else {
								selectedCustomerContainer.setChildrenAllowed(levelDto, false);
							}
							selectedCustomerContainer.setParent(levelDto, tempdto);
							break;
						}
					} else {
						selectedCustomerContainer.addBean(levelDto);
						if (forecastLevel != levelDto.getLevelNo()) {
							selectedCustomerContainer.setChildrenAllowed(levelDto, true);
						} else {
							selectedCustomerContainer.setChildrenAllowed(levelDto, false);
						}
						selectedCustomerContainer.setParent(levelDto, tempdto);
						break;
					}
				}
			}
		}
		selectedCustomer.setContainerDataSource(selectedCustomerContainer);
		selectedCustomer.setVisibleColumns(StringConstantsUtil.DISPLAY_VALUE);
		selectedCustomer.setColumnHeaders("Customer Hierarchy Group Builder");
		for (final Leveldto ddo : selectedCustomerContainer.getItemIds()) {
			selectedCustomer.setCollapsed(ddo, false);
		}
	}

	private void loadCustomerLevel(final String hierarchyId, final int hierarchyVersion) {
		DataSelectionLogic logic = new DataSelectionLogic();
		innerCustLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId),
				hierarchyVersion);
		int levelNo = UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierarchyLevel());
		String selectedLevelName = innerCustLevels.get(levelNo - 1).getLevel();
		customerForecastLevelContainer.removeAllItems();
		for (int i = 1; i <= innerCustLevels.size(); i++) {
			String levelName = innerCustLevels.get(i - 1).getLevel();
			customerForecastLevelContainer.addItem(StringConstantsUtil.LEVEL_SPACE + i + " - " + levelName);
		}
		sessionDTO.setLowerMostCustomerLevelNo(innerCustLevels.size());
		customerLevel.setContainerDataSource(customerForecastLevelContainer);
		customerLevel.setNullSelectionAllowed(true);
		customerLevel.select(StringConstantsUtil.LEVEL_SPACE + (levelNo) + " - " + selectedLevelName);
		setSelectedCustomerLevel(StringConstantsUtil.LEVEL_SPACE + (levelNo) + " - " + selectedLevelName);
	}

	private void loadInnerCustomerLevel(int forecastLevel, int innerLevel, int hierarchyId,
			final int hierarchyVersion) {
		DataSelectionLogic logic = new DataSelectionLogic();
		String selectedLevelName = StringUtils.EMPTY;
		customerInnerLevelContainer.removeAllItems();
		innerCustLevels = logic.loadCustomerForecastLevel(hierarchyId, hierarchyVersion);
		for (int i = 1; i <= forecastLevel; i++) {
			String levelName = innerCustLevels.get(i - 1).getLevel();
			customerInnerLevelContainer.addItem(StringConstantsUtil.LEVEL_SPACE + i + " - " + levelName);
			if (i == innerLevel) {
				selectedLevelName = levelName;
                                break;
			}
		}
		level.setContainerDataSource(customerInnerLevelContainer);
		level.select(StringConstantsUtil.LEVEL_SPACE + innerLevel + " - " + selectedLevelName);
	}

	private void loadProductLevel(final String hierarchyId, final int hierarchyVersion) {
		DataSelectionLogic logic = new DataSelectionLogic();
		innerProdLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId),
				hierarchyVersion);
		int levelNo = UiUtils.parseStringToInteger(dataSelectionDTO.getProductHierarchyLevel());
		String selectedLevelName = innerProdLevels.get(levelNo - 1).getLevel();
		productForecastLevelContainer.removeAllItems();
		for (int i = 1; i <= innerProdLevels.size(); i++) {
			String levelName = innerProdLevels.get(i - 1).getLevel();
			productForecastLevelContainer.addItem(StringConstantsUtil.LEVEL_SPACE + i + " - " + levelName);
		}
		sessionDTO.setLowerMostProductLevelNo(innerProdLevels.size());
		productLevel.setContainerDataSource(productForecastLevelContainer);
		productLevel.setNullSelectionAllowed(true);
		productLevel.select(StringConstantsUtil.LEVEL_SPACE + (levelNo) + " - " + selectedLevelName);
		setSelectedProductLevel(StringConstantsUtil.LEVEL_SPACE + (levelNo) + " - " + selectedLevelName);
	}

	private void loadInnerProductLevel(int forecastLevel, int innerLevel, int hierarchyId, final int hierarchyVersion) {
		DataSelectionLogic logic = new DataSelectionLogic();
		String selectedLevelName = StringUtils.EMPTY;
		productInnerLevelContainer.removeAllItems();
		innerProdLevels = logic.loadCustomerForecastLevel(hierarchyId,hierarchyVersion);
		for (int i = 1; i <= forecastLevel; i++) {
			String levelName = innerProdLevels.get(i - 1).getLevel();
			productInnerLevelContainer.addItem(StringConstantsUtil.LEVEL_SPACE + i + " - " + levelName);
			if (i == innerLevel) {
				selectedLevelName = levelName;
                                break;
			}
		}
		productlevelDdlb.setContainerDataSource(productInnerLevelContainer);
		productlevelDdlb.select(StringConstantsUtil.LEVEL_SPACE + innerLevel + " - " + selectedLevelName);
	}

	private List<Leveldto> getInitialHierarchy(final int projectionId, String indicator, final String level,
			final Map<String, String> descriptionMap) {
		DataSelectionLogic logic = new DataSelectionLogic();
		List<Leveldto> initialHierarchy = logic.getRelationShipValues(projectionId, indicator, level, descriptionMap);
		return initialHierarchy;
	}

	public final void configureOnTabLoad(int projectionId, SessionDTO session) {
		try {
			customerDescriptionMap = sessionDTO.getCustomerDescription();
			productDescriptionMap = sessionDTO.getProductDescription();
			initializeFromDto();
			if (!CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
				initializeCustomerHierarchy(projectionId, String.valueOf(session.getCustomerLevelNumber()));
			}
			initializeProductHierarchy(projectionId, String.valueOf(session.getProductLevelNumber()));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	public void loadCustomerLevel(final String hierarchyId, final String innerLevel, final int hierarchyVersion) {
		LOGGER.debug("Logging - loadCustomerLevel hierarchyId: {} and innerLevel: {}", hierarchyId, innerLevel);
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			innerCustLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId),
					hierarchyVersion);
			customerForecastLevelContainer.removeAllItems();
			int levelNo = UiUtils.parseStringToInteger(innerLevel);
			String selectedLevelName = innerCustLevels.get(levelNo - 1).getLevel();
			for (int i = 1; i <= innerCustLevels.size(); i++) {
				String levelName = innerCustLevels.get(i - 1).getLevel();
				levelCheck(levelName);
				customerForecastLevelContainer.addItem(StringConstantsUtil.LEVEL_SPACE + i + " - " + levelName);
			}
			customerLevel.setContainerDataSource(customerForecastLevelContainer);
			customerLevel.select(StringConstantsUtil.LEVEL_SPACE + (levelNo) + " - " + selectedLevelName);
			setSelectedCustomerLevel(StringConstantsUtil.LEVEL_SPACE + (levelNo) + " - " + selectedLevelName);
		} catch (NumberFormatException ex) {
			LOGGER.error(" in loadCustomerLevel = {}", ex);
		}

	}

	public void loadProductLevel(final String hierarchyId, final String innerLevel, final int hierarchyVersion) {
		LOGGER.debug("Logging - loadProductLevel hierarchyId: {} and innerLevel: {} ", hierarchyId, innerLevel);
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			innerProdLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId),
					hierarchyVersion);
			int levelNo = UiUtils.parseStringToInteger(innerLevel);
			String selectedLevelName = innerProdLevels.get(levelNo - 1).getLevel();
			productForecastLevelContainer.removeAllItems();
			for (int i = 1; i <= innerProdLevels.size(); i++) {
				String levelName = innerProdLevels.get(i - 1).getLevel();
				productForecastLevelContainer.addItem(StringConstantsUtil.LEVEL_SPACE + i + " - " + levelName);
			}
			productLevel.setContainerDataSource(productForecastLevelContainer);
			productLevel.select(StringConstantsUtil.LEVEL_SPACE + (levelNo) + " - " + selectedLevelName);
			setSelectedProductLevel(StringConstantsUtil.LEVEL_SPACE + (levelNo) + " - " + selectedLevelName);
		} catch (NumberFormatException ex) {
			LOGGER.error(" loadProductLevel= {}", ex);
		}
	}

	private void configureBusinessUnitDdlb() {
		getBusinessUnit().addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				availableProductContainer.removeAllItems();
				selectedProductContainer.removeAllItems();
				productBeanList.clear();
			}
		});
		company.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				availableProductContainer.removeAllItems();
				selectedProductContainer.removeAllItems();
				productBeanList.clear();
			}
		});

	}
        public final void loadCFFEligibleDate() {
            cffEligibleDate.setDateFormat("MM/dd/yyyy");
            DataSelectionLogic logic = new DataSelectionLogic();
            if (sessionDTO.getCffEligibleDate() != null) {
             cffEligibleDate.setValue(sessionDTO.getCffEligibleDate());           
            } else {
                cffEligibleDate.setValue(logic.getDefaultEligibleDateFromForecastConfiguration());
            }
             cffEligibleDate.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                  sessionDTO.setCffEligibleDate(cffEligibleDate.getValue());
                    levelValueChangeListener(dataSelectionDTO.getSelectedCustomerLevelNo());
                }
            });
             loadDdlbForDeduction(deductionDdlb);
            
             comparison.addClickListener(new CustomTextField.ClickListener() {
                @Override
                public void click(CustomTextField.ClickEvent event) {
                    comparisonLookupLogic();
                }
            });
              comparison.setEnabled(false);
        }

        
         protected void comparisonLookupLogic() {
        LOGGER.debug("Comparision lookup started");
        
        final ComparisonLookup comparisonLookupWindow = new ComparisonLookup(comparison, sessionDTO, new ArrayList<>(),true);
        UI.getCurrent().addWindow(comparisonLookupWindow);
        comparisonLookupWindow.addCloseListener(new Window.CloseListener() {
            /**
             * Default method.
             */
            @Override
            public void windowClose(final Window.CloseEvent event) {
                sessionDTO.setComparisonLookupName(comparison.getValue());
                sessionDTO.setComparisonLookupData(comparison.getData());
                if (comparisonLookupWindow.getSelectedProjection().getItemIds().isEmpty()) {
                    comparison.setReadOnly(false);
                    comparison.setValue(Constants.SELECT_ONE_LABEL);
                    comparison.setData(null);
                    comparison.setReadOnly(true);
                }
            }
        });

        LOGGER.debug("Comparision lookup ends");
    }                 
                  

	/**
	 * Used to check which level is top in selected customer hierarchy either
	 * customer or contract It is used for CCP_HIERARCHY_INSERT query formation
	 *
	 * @param levelName
	 */
	private void levelCheck(String levelName) {
		if (StringUtils.isBlank(topLevelName) && ("Customer".equals(levelName) || TRADING_PARTNER.equals(levelName)
				|| "TradingPartner".equals(levelName) || "Contract".equals(levelName))) {
			topLevelName = levelName;
		}
	}

	public void insertCffDetailsUsingExecutorService(final int projectionIdValue,
			final GtnSmallHashMap tempTableNames) {
		Future future = service.submitRunnable(new CFFDetailsInsertJobRun(projectionIdValue, tempTableNames));
		sessionDTO.setFuture(future);
	}

	@Override
	protected void loadProductVersionNo(Object selectedProductRelation) {
		try {
			if (selectedProductRelation != null && !SELECT_ONE.equals(String.valueOf(selectedProductRelation))) {
				List<Object[]> versionNoList = relationLogic.getVersionNoList(selectedProductRelation);
				Object value = loadComboBoxBasedOnRelationshipVersion(productRelationVersionComboBox, versionNoList);
				productRelationVersionComboBox.select(value);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	protected void loadCustomerVersionNo(Object selectedCustomerRelation) {
		try {
			if (selectedCustomerRelation != null && !SELECT_ONE.equals(String.valueOf(selectedCustomerRelation))) {
				List<Object[]> versionNoList = relationLogic.getVersionNoList(selectedCustomerRelation);
				Object value = loadComboBoxBasedOnRelationshipVersion(customerRelationVersionComboBox, versionNoList);
				customerRelationVersionComboBox.select(value);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	protected void loadForecastLevels(List<Leveldto> innerLevels, IndexedContainer productForecastLevelContainer,
			ComboBox level, int hierarchySid, int hierarchyVersion) {
		innerLevels = new DataSelectionLogic().loadCustomerForecastLevel(hierarchySid,
				hierarchyVersion);
		productForecastLevelContainer.removeAllItems();
		for (int i = 1; i <= innerLevels.size(); i++) {
			String levelName = innerLevels.get(i - 1).getLevel();
			productForecastLevelContainer.addItem(StringConstantsUtil.LEVEL_SPACE + i + " - " + levelName);
		}
		level.setContainerDataSource(productForecastLevelContainer);
	}

	/**
	 * Inner class for running seperate Job to insert CFF details
	 */
	class CFFDetailsInsertJobRun implements Runnable {

		private final int projectionId;
		private final GtnSmallHashMap tempTableNames;

		public CFFDetailsInsertJobRun(int projectionId, GtnSmallHashMap tempTableNames) {
			this.projectionId = projectionId;
			this.tempTableNames = tempTableNames;
		}

		@Override
		public void run() {
			try {
				List<Leveldto> productList = selectedProductContainer.getItemIds();
				List<String> productListEndSids = DataSelectionUtil
						.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));
				cffLogic.saveProductHierarchyLogic(productList, productListEndSids, projectionId, null, "save");
				List<Leveldto> customerList = selectedCustomerContainer.getItemIds();
				List<String> customerListEndSids = DataSelectionUtil
						.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer));
				cffLogic.saveCustomerHierarchyLogic(customerList, customerListEndSids, projectionId, null, "save");
				relationshipBuilderSids.clear();
				setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));
				cffLogic.saveCcp(getCustomerHierarchyEndLevels(selectedCustomerContainer), String.valueOf(projectionId),
						tempTableNames);
			} catch (SystemException ex) {
				Logger.getLogger(DataSelection.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public final void configurePermission() {
		try {
			final StplSecurity stplSecurity = new StplSecurity();
			final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtil.USER_ID));
			Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId,
					"Consolidated Financial Forecast" + STRING_COMMA + "Data Selection");
			if (functionHM.get("generateBtn") != null
					&& !((AppPermission) functionHM.get("generateBtn")).isFunctionFlag()) {
				generateBtn.setVisible(false);
			} else {
				generateBtn.setVisible(true);
			}
			if (functionHM.get("resetBtn") != null && !((AppPermission) functionHM.get("resetBtn")).isFunctionFlag()) {
				resetBtn.setVisible(false);
			} else {
				resetBtn.setVisible(true);
			}
			if (functionHM.get("saveViewBtn") != null
					&& !((AppPermission) functionHM.get("saveViewBtn")).isFunctionFlag()) {
				saveViewBtn.setVisible(false);
			} else {
				saveViewBtn.setVisible(true);
			}
			if (functionHM.get("deleteViewBtn") != null
					&& !((AppPermission) functionHM.get("deleteViewBtn")).isFunctionFlag()) {
				deleteViewBtn.setVisible(false);
			} else {
				deleteViewBtn.setVisible(true);
			}

		} catch (final PortalException | SystemException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		s.defaultWriteObject();
	}

	// Dont delete. this Method is called during Serialization

	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}

	public static int getDataSelectionFormattedLevelNo(String value) {
		return StringUtils.isBlank(value) ? 0 : Integer.parseInt(value.split(" ")[1]);
	}

	private Future<Boolean> checkAndDoAutomaticUpdate(Object value, int hierarchyId) {
		GtnAutomaticRelationServiceRunnable wsClientRunnableTarget = new GtnAutomaticRelationServiceRunnable(value,
				hierarchyId, sessionDTO.getUserId());
		ExecutorService customerExecutorService = Executors.newSingleThreadExecutor();
		Future<Boolean> future = customerExecutorService.submit(wsClientRunnableTarget);
		customerExecutorService.shutdown();
		return future;
	}
        
         public void loadDdlbForDeduction(ComboBox ddlb) {
             ddlb.setNullSelectionAllowed(false);
           List<String[]> currentHierarchy= CommonLogic.getDeductionLevelForDataSelection();
            if (currentHierarchy != null && !currentHierarchy.isEmpty()) {
                for (int i = 0; i < currentHierarchy.size(); i++) {
                    Object[] levelValues = currentHierarchy.get(i);
                    ddlb.addItem(DataTypeConverter.convertObjectToInt(levelValues[0]));
                    ddlb.setItemCaption(DataTypeConverter.convertObjectToInt(levelValues[0]), String.valueOf(levelValues[1]));
                }
                ddlb.select(1);
            }
    }
         
         private void loadCustomViewDropDown(ComboBox customViewDdlb) {
            setNullSelectionCustomDdlb(customViewDdlb);
            logic.loadCustomViewValues(customViewDdlb,customViewInput,true);
            customViewDdlb.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if(event.getProperty().getValue()!=null){
                    sessionDTO.setCustomViewMasterSid(Integer.parseInt(String.valueOf(event.getProperty().getValue())));
                    }
                    comparison.setEnabled(event.getProperty().getValue()!=null);
                }
            });
         }
         
        public void setNullSelectionCustomDdlb(ComboBox customViewDdlb) {
            customViewDdlb.removeAllItems();
            customViewDdlb.setNullSelectionAllowed(false);
            customViewDdlb.setInputPrompt(SELECT_ONE);
        }
    }
