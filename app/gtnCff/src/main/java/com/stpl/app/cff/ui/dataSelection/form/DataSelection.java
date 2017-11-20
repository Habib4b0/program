/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.dataSelection.form;

import static com.stpl.app.cff.util.Constants.IndicatorConstants.INDICATOR_CUSTOMER_GROUP;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;

import com.stpl.app.cff.abstractCff.AbstractDataSelection;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.queryUtils.CFFQueryUtils;
import com.stpl.app.cff.security.StplSecurity;
import com.stpl.app.cff.service.GtnAutomaticRelationServiceRunnable;
import com.stpl.app.cff.ui.dataSelection.dto.CompanyDdlbDto;
import com.stpl.app.cff.ui.dataSelection.dto.RelationshipDdlbDto;
import com.stpl.app.cff.ui.dataSelection.logic.DataSelectionLogic;
import com.stpl.app.cff.ui.dataSelection.logic.RelationShipFilterLogic;
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
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 *
 * @author mohamed.hameed
 */
public class DataSelection extends AbstractDataSelection {

	private static final long serialVersionUID = 1905122041950251207L;
	private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DataSelection.class);
	CustomFieldGroup dataSelectionBinder;
	/**
	 * The data selection binder.
	 */
	public static final String SELECT_ONE = "-Select One-";
	public static final String TRADING_PARTNER = "Trading Partner";
	public static final String COMPANY_MASTER_TABLE = "COMPANY_MASTER";
	public static final String NULL = "null";
	String screenName = CommonUtils.MODULE_NAME;
	Map<String, String> customerDescriptionMap = null;
	Map<String, String> productDescriptionMap = null;
	private boolean dismantleCustomerSelection = true;
	private boolean dismantleProductSelection = true;
	CompanyDdlbDto discountDdlbDefault = new CompanyDdlbDto(0, SELECT_ONE);
	CompanyDdlbDto discountDTO = null;
	public static Map<String, String> relationLevelValues = new HashMap<>();
	DataSelectionLogic dataLogic = new DataSelectionLogic();
	List<Integer> customerBeanList = new ArrayList<>();
	List<Integer> productBeanList = new ArrayList<>();
	String publicViewName;
	String privateViewName;
	SessionDTO sessionDTO;
	CFFLogic cffLogic = new CFFLogic();
	TabSheet tabSheet;
	private String topLevelName = StringUtils.EMPTY;
	ExecutorService service = ThreadPool.getInstance().getService();
	private final RelationShipFilterLogic relationLogic = RelationShipFilterLogic.getInstance();

	private List<Leveldto> productHierarchyLevelDefinitionList = Collections.emptyList();
	private List<Leveldto> customerHierarchyLevelDefinitionList = Collections.emptyList();
	private Future customerFuture;
	private Future productFuture;

	public DataSelection(CustomFieldGroup dataSelectionBinder, DataSelectionDTO dataSelectionDTO, TabSheet tabSheet,
			SessionDTO sessionDTO) {
		super(dataSelectionBinder, CommonUtils.MODULE_NAME);
		this.dataSelectionBinder = dataSelectionBinder;
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

	}

	public void configureOnLoading(int projectionId, DataSelectionDTO dataSelectionDTO, SessionDTO session) {
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
			businessUnit.setValue(dataSelectionDTO.getBusinessUnitSystemId());
		} catch (final Exception e) {
			LOGGER.error(e);
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
		final HierarchyLookup productHierarchyLookupWindow = new HierarchyLookup(
				INDICATOR_PRODUCT_HIERARCHY.getConstant(), WINDOW_PRODUCT_HIERARCHY_LOOKUP.getConstant(),
				productHierarchy, productHierarchyDto);
		UI.getCurrent().addWindow(productHierarchyLookupWindow);
		productHierarchyLookupWindow.addCloseListener(new Window.CloseListener() {
			@Override
			public void windowClose(Window.CloseEvent e) {
				if (productHierarchyLookupWindow.getHierarchyDto() != null) {
					final HierarchyLookupDTO lookupDto = productHierarchyLookupWindow.getHierarchyDto();
					productHierarchyDto = lookupDto;
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

	public void readModeOnly() {
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
		businessUnit.setEnabled(false);

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
					customerHierarchyDto.getHierarchyName(),
					Integer.parseInt(customerRelationVersionComboBox.getValue().toString()));
			String[] val = selectedLevel.split(" ");
			int forecastLevel = Integer.parseInt(val[1]);
			sessionDTO.setCustomerLevelNumber(String.valueOf(forecastLevel));
			customerInnerLevelContainer.removeAllItems();
			selectedCustomer.removeAllItems();
			selectedCustomerContainer.removeAllItems();
			customerBeanList.removeAll(customerBeanList);
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
			customerBeanList.removeAll(customerBeanList);
		}
	}

	protected void levelValueChangeListener(Object value) {

		LOGGER.debug("customer inner Level - ValueChangeListener  " + value);
		availableCustomerContainer.removeAllItems();
		String levelName = StringConstantsUtil.LEVEL;
		int relationVersionNo = Integer
				.parseInt(customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
		int hierarchyVersionNo = Integer.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
		try {
			int forecastLevel = 0;
			if (value != null && customerRelation.getValue() != null
					&& !SELECT_ONE.equals(customerRelation.getValue())) {
				String selectedLevel = String.valueOf(value);
				String relationshipSid = String.valueOf(customerRelation.getValue());
				String[] val = selectedLevel.split(" ");
				forecastLevel = Integer.parseInt(val[1]);
				customerHierarchyLevelDefinitionList = relationLogic
						.getHierarchyLevelDefinition(customerHierarchyDto.getHierarchyId(), hierarchyVersionNo);
				Leveldto selectedHierarchyLevelDto = customerHierarchyLevelDefinitionList.get(forecastLevel - 1);
				List<Leveldto> levelHierarchyLevelDefinitionList = customerHierarchyLevelDefinitionList.subList(0,
						forecastLevel);
				List<String> tempGroupFileter = groupFilteredCompanies == null ? Collections.<String>emptyList()
						: groupFilteredCompanies;
				List<Leveldto> resultedLevelsList = relationLogic.loadAvailableCustomerlevel(selectedHierarchyLevelDto,
						Integer.valueOf(relationshipSid), tempGroupFileter, levelHierarchyLevelDefinitionList,
						relationVersionNo);
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
		} catch (Exception ex) {

			LOGGER.error(ex + " level  ValueChangeListener ");
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
					productHierarchyDto.getHierarchyName(),
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
				ExecutorService customerExecutorService = Executors.newSingleThreadExecutor();
				customerFuture = checkAndDoAutomaticUpdate(customerRelationVersionComboBox.getValue(),
						customerHierarchyDto.getHierarchyId(), customerExecutorService);
				int relationVersionNo = Integer.parseInt(
						customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
				int hierarchyVersionNo = Integer.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
				customerDescriptionMap = relationLogic.getLevelValueMap(String.valueOf(customerRelation.getValue()),
						customerHierarchyDto.getHierarchyId(), hierarchyVersionNo, relationVersionNo);
			} catch (Exception ex) {
				LOGGER.error(ex + " in customerRelation value change");
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
		LOGGER.debug("productRelation - ValueChangeListener " + value);
		if (value != null && !SELECT_ONE.equals(String.valueOf(value))) {
			try {
				selectedProduct.removeAllItems();
				selectedProductContainer.removeAllItems();
				availableProduct.removeAllItems();
				availableProductContainer.removeAllItems();
				setProductForecastLevelNullSelection();
				setProductLevelNullSelection();
				setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));
				ExecutorService customerExecutorService = Executors.newSingleThreadExecutor();
				productFuture = checkAndDoAutomaticUpdate(productRelation.getValue(),
						productHierarchyDto.getHierarchyId(), customerExecutorService);
				int relationVersionNo = Integer.parseInt(
						productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
				int hierarchyVersionNo = Integer.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
				productDescriptionMap = relationLogic.getLevelValueMap(String.valueOf(productRelation.getValue()),
						productHierarchyDto.getHierarchyId(), hierarchyVersionNo, relationVersionNo);
			} catch (Exception ex) {
				LOGGER.error(ex + " in productRelation value change");
			}
		} else if ((value == null && SELECT_ONE.equals(String.valueOf(value)))) {
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
			int projectionIdValue = cffLogic.saveCFFMaster(dataSelectionDTO, Boolean.FALSE, 0);
			VaadinSession.getCurrent().setAttribute("projectionId", projectionIdValue);
			dataSelectionDTO.setProjectionId(projectionIdValue);
			int prodRelationVersionNo = Integer
					.parseInt(productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
			int custRelationVersionNo = Integer.parseInt(
					customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
			if (projectionIdValue != 0) {

				sessionDTO.setProjectionId(projectionIdValue);
				sessionDTO.setProductLevelNumber(String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
				sessionDTO.setCustomerLevelNumber(String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
				sessionDTO.setAction(Constants.ADD);
				sessionDTO.setFromDate(dataSelectionDTO.getFromDate());
				sessionDTO.setToDate(dataSelectionDTO.getToDate());
				sessionDTO.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
				sessionDTO.setProductRelationId(Integer.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
				sessionDTO.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
				sessionDTO.setProductDescription(productDescriptionMap);
				sessionDTO.setCustomerHierarchyId(0);
				sessionDTO.setCustomerDescription(customerDescriptionMap);
				sessionDTO.setCustomerHierarchyId(Integer.valueOf(dataSelectionDTO.getCustomerHierSid()));
				sessionDTO.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
				sessionDTO.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
				Object[] obj = cffLogic.deductionRelationBuilderId(dataSelectionDTO.getProdRelationshipBuilderSid());
				sessionDTO.setDedRelationshipBuilderSid(obj[0].toString());
				sessionDTO.setCompanySystemId((Integer) company.getValue());
				sessionDTO.setScreenName("CCP_HIERARCHY");
				CFFQueryUtils.createTempTables(sessionDTO);
				relationLogic.ccpHierarchyInsert(sessionDTO.getCurrentTableNames(),
						selectedCustomerContainer.getItemIds(), selectedProductContainer.getItemIds(),
						customerHierarchyLevelDefinitionList, productHierarchyLevelDefinitionList,
						custRelationVersionNo, prodRelationVersionNo);
				insertCffDetailsUsingExecutorService(projectionIdValue, sessionDTO.getCurrentTableNames());
				sessionDTO.setCustomerLevelDetails(
						cffLogic.getLevelValueDetails(sessionDTO, customerRelation.getValue(), true));
				sessionDTO.setProductLevelDetails(
						cffLogic.getLevelValueDetails(sessionDTO, productRelation.getValue(), false));
				if (sessionDTO.getFuture() != null) {
					sessionDTO.getFuture().get();
					cffLogic.callDeductionCCPHierarchyInsertion(sessionDTO, sessionDTO.getCurrentTableNames(),
							Boolean.FALSE);
				}

			}

			tabSheet.setSelectedTab(1);
			sessionDTO.setIsGenerated(Boolean.TRUE);

		} catch (Exception ex) {
			Logger.getLogger(DataSelection.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	protected void loadPublicView() {
		final PrivatePublicView publicViewLookup = new PrivatePublicView(INDICATOR_PUBLIC_VIEW.getConstant(),
				publicView, PUBLIC_VIEW.getConstant(), screenName);
		UI.getCurrent().addWindow(publicViewLookup);
		publicViewLookup.setImmediate(true);
		publicViewLookup.addCloseListener(new Window.CloseListener() {
			@Override
			public void windowClose(Window.CloseEvent e) {
				if (publicViewLookup.getViewDTO() != null) {
					ViewDTO viewDTO = publicViewLookup.getViewDTO();
					setViewDTO(viewDTO);
					publicView.setValue(viewDTO.getViewName().trim());
					try {
						loadView(viewDTO);
					} catch (Exception ex) {
						LOGGER.error(ex + " publicView close");
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
		privateViewLookup.setImmediate(true);
		privateViewLookup.addCloseListener(new Window.CloseListener() {
			@Override
			public void windowClose(Window.CloseEvent e) {
				if (privateViewLookup.getViewDTO() != null) {
					ViewDTO viewDTO = privateViewLookup.getViewDTO();
					setViewDTO(viewDTO);
					privateView.setValue(viewDTO.getViewName().trim());
					try {
						loadView(viewDTO);
					} catch (Exception ex) {
						LOGGER.error(ex + " privateView close");
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
				INDICATOR_CUSTOMER_GROUP.getConstant(), WINDOW_CUSTOMER_GROUP_LOOKUP.getConstant(), customerGroup,
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
					productListEndSids, screenName, sessionDTO);
			getUI().getCurrent().addWindow(saveViewPopup);
		} catch (Exception e) {
			LOGGER.error(e);
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
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
								customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo,
								getDataSelectionFormattedLevelNo(String.valueOf(level.getValue()).split("-")[0]));
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
								customerHierarchyVersionNo, customerRelationVersionNo,
								getDataSelectionFormattedLevelNo(String.valueOf(level.getValue()).split("-")[0]));
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
								customerHierarchyVersionNo, customerRelationVersionNo,
								getDataSelectionFormattedLevelNo(String.valueOf(level.getValue()).split("-")[0]));
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
								customerHierarchyVersionNo, customerRelationVersionNo,
								getDataSelectionFormattedLevelNo(String.valueOf(level.getValue()).split("-")[0]));
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
										|| !customerBeanList.contains(newLevel.getRelationShipBuilderId())) {
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
		} catch (Exception e) {
			LOGGER.error(e);
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
								if (!uncommonValues.isEmpty()) {
									newParentLevels = logic.getParentLevelsWithHierarchyNo(
											UiUtils.stringListToString(uncommonValues), customerDescriptionMap,
											customerHierarchyVersionNo, customerRelationVersionNo);
								}
								newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
										UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
										customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
										customerHierarchyVersionNo, customerRelationVersionNo,
										getDataSelectionFormattedLevelNo(
												String.valueOf(level.getValue()).split("-")[0]));
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
							if (!uncommonValues.isEmpty()) {
								newParentLevels = logic.getParentLevelsWithHierarchyNo(
										UiUtils.stringListToString(uncommonValues), customerDescriptionMap,
										customerHierarchyVersionNo, customerRelationVersionNo);
							}
							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
									customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
									getDataSelectionFormattedLevelNo(String.valueOf(level.getValue()).split("-")[0]));
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
									customerHierarchyVersionNo, customerRelationVersionNo,
									getDataSelectionFormattedLevelNo(String.valueOf(level.getValue()).split("-")[0]));
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
									customerHierarchyVersionNo, customerRelationVersionNo,
									getDataSelectionFormattedLevelNo(String.valueOf(level.getValue()).split("-")[0]));
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
		} catch (Exception e) {
			LOGGER.error(e);
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
										productDescriptionMap, businessUnit.getValue(),
										DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
										productRelationVersionNo, getDataSelectionFormattedLevelNo(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
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
									productDescriptionMap, businessUnit.getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo, getDataSelectionFormattedLevelNo(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
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
									productDescriptionMap, businessUnit.getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo, getDataSelectionFormattedLevelNo(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
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
									productDescriptionMap, businessUnit.getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo, getDataSelectionFormattedLevelNo(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
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
		} catch (Exception e) {
			LOGGER.error(e);
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
				if (currentLevel != 0 && DataSelectionUtil.getBeanFromId(selectedItem).getLevelNo() == currentLevel) {
				}
				DataSelectionUtil.removeItemsRecursively(selectedItem, selectedCustomer, availableCustomer,
						selectedCustomerContainer, availableCustomerContainer, currentLevel);
				selectedCustomerContainer.removeItem(DataSelectionUtil.getBeanFromId(selectedItem));
				selectedCustomer.removeItem(selectedItem);
				customerBeanList.remove(DataSelectionUtil.getBeanFromId(selectedItem).getRelationshipLevelSid());
				customerBeanList.removeAll(customerBeanList);
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
			LOGGER.error(ex);
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
				productBeanList.removeAll(productBeanList);
				List<Leveldto> productListValue = selectedProductContainer.getItemIds();
				for (Leveldto dto : productListValue) {
					productBeanList.add(dto.getRelationshipLevelSid());
				}
			} else {
				AbstractNotificationUtils.getErrorNotification("No Product hierarchy level Selected",
						"No Level was selected to move. Please try again. ");
			}
		} catch (Exception ex) {
			LOGGER.error(ex + " in moveRightProduct");
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
				int productHierarchyVersionNo = Integer
						.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
				int productRelationVersionNo = Integer.parseInt(
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
									UiUtils.stringListToString(uncommonValues), productDescriptionMap,
									productHierarchyVersionNo, productRelationVersionNo);
						}
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								productDescriptionMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo, getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
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
										if (parentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
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

					} else {
						// When no value selected in selected product tree
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
							for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
								if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
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
								UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								productDescriptionMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo, getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
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
								productDescriptionMap, productHierarchyVersionNo, productRelationVersionNo);
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								productDescriptionMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo, getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
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
						}
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								productDescriptionMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo, getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
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
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	@Override
	protected void deleteViewButtonLogic() {
		final ViewDTO dto = getViewDTO();
		new AbstractNotificationUtils() {
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
				try {
					if (dto != null) {
						CFFLogic.deleteView(Integer.parseInt(dto.getViewId()));
						CommonUIUtils.getMessageNotification(dto.getViewName() + " has been successfully deleted.");
						deleteViewBtn.setEnabled(false);
						resetOne();
					}

				} catch (Exception exception) {
					LOGGER.error(exception);
				}
			}
		}.getConfirmationMessage(Constants.MessageConstants.CONFIRM_DELETION_TITLE.getConstant(),
				Constants.MessageConstants.CONFIRM_DELETION_BODY.getConstant().replace("?#", dto.getViewName()));
	}

	@Override
	protected void resetButtonLogic() {
		company.setValue(0);
		businessUnit.setValue(0);
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
		businessUnit.setValue(viewDTO.getBusinessUnitSystemId());
		dismantleCustomerSelection = true;
		dismantleProductSelection = true;
		customerHierarchyDto = new HierarchyLookupDTO();
		productHierarchyDto = new HierarchyLookupDTO();
		viewId.setValue(viewDTO.getViewId());
		projectionId.setValue(viewDTO.getProjectionId());
		dataSelectionDTO.setProjectionId(UiUtils.parseStringToInteger(viewDTO.getProjectionId()));
		customerHierarchyDto.setHierarchyId(
				viewDTO.getCustomerHierarchySid() != null && viewDTO.getCustomerHierarchySid().equals(StringUtils.EMPTY)
						? 0 : Integer.parseInt(viewDTO.getCustomerHierarchySid()));
		customerHierarchyDto.setHierarchyName(viewDTO.getCustomerHierarchy());
		productHierarchyDto.setHierarchyId(
				viewDTO.getProductHierarchySid() != null && viewDTO.getProductHierarchySid().equals(StringUtils.EMPTY)
						? 0 : Integer.parseInt(viewDTO.getProductHierarchySid()));
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
			relationLevelValues.putAll(dataLogic.getLevelValueMap(viewDTO.getCustRelationshipBuilderSid()));
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
			relationLevelValues.putAll(dataLogic.getLevelValueMap(viewDTO.getProdRelationshipBuilderSid()));
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
		LOGGER.debug("Logging - loadRelationDdlb hierarchyDefinitionSid " + hierarchyDefinitionSid);
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
			relationship.setImmediate(true);
			relationship.setNullSelectionAllowed(true);
			relationship.setInputPrompt("-Select One-");
		} catch (Exception ex) {
			LOGGER.error(ex);
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
					innerProdLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY,
							dataSelectionDTO.getProductHierVersionNo());
				}

			}
			if ((productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue()))
					&& (innerProdLevels != null)) {
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

				if (companyLevel != null) {
					innerLevelValues = logic.loadInnerLevel(companyLevel.getLevel(),
							productHierarchyDto.getHierarchyId(), selectedLevelSids, false, companyLevel.getFieldName(),
							relationshipSid, productDescriptionMap, StringUtils.EMPTY, screenName,
							discountDTO != null ? discountDTO.getRsModelSid() : 0, companyLevel.getLevelNo(),
							company.getValue(), businessUnit.getValue());
				}
			}
		} catch (Exception ex) {
			LOGGER.error(ex + " in getCompanySidFromHierarchy");
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
				int productRelationVersionNo = Integer.parseInt(
						productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
				String customerVersionNo = customerRelationVersionComboBox
						.getItemCaption(customerRelationVersionComboBox.getValue());
				int customerRelationVersionNo = customerVersionNo == null ? 0 : Integer.parseInt(customerVersionNo);
				int hierarchyVersionNo = Integer.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
				String relationshipSid = String.valueOf(productRelation.getValue());

				String[] val = selectedLevel.split(" ");
				forecastLevel = Integer.parseInt(val[1]);
				productHierarchyLevelDefinitionList = relationLogic
						.getHierarchyLevelDefinition(productHierarchyDto.getHierarchyId(), hierarchyVersionNo);
				List<Leveldto> customerHierarchyDefinitionList;
				if (customerHierarchyDto == null) {
					customerHierarchyDefinitionList = Collections.emptyList();
				} else {
					int customerHierarchyVersionNo = Integer
							.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
					customerHierarchyDefinitionList = relationLogic.getHierarchyLevelDefinition(
							customerHierarchyDto.getHierarchyId(), customerHierarchyVersionNo);
				}
				List<Leveldto> hierarchyLevelDefinitionList = productHierarchyLevelDefinitionList.subList(0,
						forecastLevel);
				Leveldto selectedHierarchyLevelDto = productHierarchyLevelDefinitionList.get(forecastLevel - 1);
				isNdc = selectedHierarchyLevelDto.getTableName().equalsIgnoreCase("ITEM_MASTER");
				selectedCustomerContractList = getSelectedCustomerContractList();

				List<String> tempGroupFileter = groupFilteredItems == null ? Collections.<String>emptyList()
						: groupFilteredItems;
				resultedLevelsList = relationLogic.loadAvailableProductlevel(selectedHierarchyLevelDto,
						Integer.valueOf(relationshipSid), tempGroupFileter, selectedCustomerContractList, isNdc,
						hierarchyLevelDefinitionList, customerHierarchyDefinitionList, productRelationVersionNo,
						customerRelationVersionNo, businessUnit.getValue());
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

		} catch (Exception ex) {

			LOGGER.error(ex + " - in loadFilteredProductSelection");
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
			if ((productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue()))
					&& (innerProdLevels != null)) {
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
							discountDTO != null ? discountDTO.getRsModelSid() : 0, ndcLevel.getLevelNo(),
							company.getValue(), businessUnit.getValue());
				}
			}
		} catch (Exception ex) {
			LOGGER.error(ex + " in getItemSidFromHierarchy");
		}
		return innerLevelValues;
	}

	/**
	 * Manual trigger and processing of customer group select button logic Code
	 * based on customer group select button logic Any change made there, should
	 * be made here accordingly
	 *
	 * @param customerGrpSid
	 */
	public void triggerCustGrpOnView(String customerGrpSid, final boolean filter) {

		List<String> companySids = null;
		List<String> customerSidsFromDetails = null;
		DataSelectionLogic logic = new DataSelectionLogic();
		try {
			if (!StringUtils.isBlank(customerGrpSid) && !NULL.equalsIgnoreCase(customerGrpSid)) {
				companySids = DataSelectionUtil.getCustomerSidFromHierarchy(getCustomersFromHierarchy());
				if (companySids != null) {
					List<String> finalCompanySids = new ArrayList<>(companySids);
					customerSidsFromDetails = logic.getCustomerGroupDetails(Integer.parseInt(customerGrpSid));
					finalCompanySids.retainAll(customerSidsFromDetails);
					groupFilteredCompanies = new ArrayList<>(finalCompanySids);
				}
				if (filter) {
					filterCustomerOnGroupSelect();
				}
			}
		} catch (Exception ex) {
			LOGGER.error(ex + " at triggerCustGrpOnView");
		}
	}

	/**
	 * Manual trigger and processing of product group select button logic Code
	 * based on product group select button logic Any change made there, should
	 * be made here accordingly
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
		} catch (Exception ex) {
			LOGGER.error(ex + " at triggerProdGrpOnView");
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
							discountDTO != null ? discountDTO.getRsModelSid() : 0, tempDto.getLevelNo(),
							company.getValue(), businessUnit.getValue());

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
								LOGGER.error(ex + " level ValueChangeListener ");
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
		} catch (final Exception ex) {
			LOGGER.error(ex + " filterCustomerOnGroupSelect ");
		}
	}

	private List<Leveldto> getCustomersFromHierarchy() {
		List<Leveldto> innerLevelValues = null;
		try {
			if ((customerRelation.getValue() != null && !SELECT_ONE.equals(customerRelation.getValue()))
					&& (innerCustLevels != null)) {
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
							INDICATOR_LEVEL_CUSTOMER.getConstant(), screenName,
							discountDTO != null ? discountDTO.getRsModelSid() : 0, customerLevelDto.getLevelNo(),
							company.getValue(), businessUnit.getValue());
				}
			}
		} catch (Exception ex) {
			LOGGER.error(ex + " in getCustomersFromHierarchy");
		}
		return innerLevelValues;
	}

	public DataSelectionDTO bindDataselectionDtoToSave() {
		if (company.getValue() != null && !SELECT_ONE.equals(company.getValue())) {
			dataSelectionDTO.setCompanySid(String.valueOf(company.getValue()));
		} else {
			dataSelectionDTO.setCompanySid(String.valueOf(0));
		}
		if (businessUnit.getValue() != null && !SELECT_ONE.equals(businessUnit.getValue())) {
			dataSelectionDTO.setBusinessUnitSystemId((Integer) businessUnit.getValue());
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
			dataSelectionDTO.setCustomerHierarchyVer(String.valueOf(customerRelationVersionComboBox.getValue()));
			dataSelectionDTO.setCustomerRelationShipVersionNo(Integer.parseInt(
					customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue())));
		} else {
			dataSelectionDTO.setCustomerHierarchyVer(String.valueOf(0));
			dataSelectionDTO.setCustomerRelationShipVersionNo(0);
		}
		if (productHierarchyDto != null) {
			dataSelectionDTO.setProductHierarchyVer(String.valueOf(productRelationVersionComboBox.getValue()));
			dataSelectionDTO.setProductRelationShipVersionNo(Integer.parseInt(
					productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue())));
		} else {
			dataSelectionDTO.setProductHierarchyVer(String.valueOf(0));
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
		LOGGER.debug("-- getProductHierarchyEndLevels size------------------------->>>>>"
				+ productHierarchyEndLevels.size());
		return productHierarchyEndLevels;
	}

	public String getProductHierarchyEndLevelsHierNo(final ExtTreeContainer<Leveldto> selectedProductContainer) {

		StringBuilder returnString = new StringBuilder(StringUtils.EMPTY);

		List<String> productHierarchyEndLevelsHierNo = new ArrayList<>();
		for (Object item : selectedProductContainer.getItemIds()) {
			if (!selectedProductContainer.hasChildren(item)) {
				productHierarchyEndLevelsHierNo.add(DataSelectionUtil.getBeanFromId(item).getHierarchyNo());
			}
		}
		if (!productHierarchyEndLevelsHierNo.isEmpty()) {
			for (int loop = 0, limit = productHierarchyEndLevelsHierNo.size(); loop < limit; loop++) {
				returnString.append("'");
				returnString.append(productHierarchyEndLevelsHierNo.get(loop));
				returnString.append("'");
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
		reslistOne = relationLogic.getRelationShipValues(projectionId, Boolean.FALSE, productLevel,
				productDescriptionMap);
		LOGGER.debug("relist===========" + reslistOne.toString());

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
		try {

			if (dataSelectionDTO != null) {
				sessionDTO.setProductLevelNumber(String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
				sessionDTO.setCustomerLevelNumber(String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));

				if (dataSelectionDTO.getCustomerHierSid() != null
						&& !StringUtils.EMPTY.equals(String.valueOf(dataSelectionDTO.getCustomerHierSid()))
						&& !Constants.CommonConstants.NULL.getConstant()
								.equals(String.valueOf(dataSelectionDTO.getCustomerHierSid()))) {

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
					customerHierarchyDto = new HierarchyLookupDTO();
					customerHierarchyDto
							.setHierarchyId(UiUtils.parseStringToInteger(dataSelectionDTO.getCustomerHierSid()));
					customerHierarchyDto.setHierarchyName(dataSelectionDTO.getCustomerHierarchy());
					customerHierarchy.setValue(customerHierarchyDto.getHierarchyName());
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
					productHierarchyDto = new HierarchyLookupDTO();
					productHierarchyDto.setHierarchyId(UiUtils.parseStringToInteger(dataSelectionDTO.getProdHierSid()));
					productHierarchyDto.setHierarchyName(dataSelectionDTO.getProductHierarchy());
					productHierarchy.setValue(productHierarchyDto.getHierarchyName());
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
			}
		} catch (Exception ex) {
			LOGGER.error(ex + " in initializeFromDto ");
		}
	}

	private void initializeCustomerHierarchy(final int projectionId, final String customerLevel) {
		LOGGER.debug("Initializing Customer Hierarchy...");
		List<Leveldto> initialCustomerHierarchy = relationLogic.getRelationShipValues(projectionId, Boolean.TRUE,
				customerLevel, customerDescriptionMap);
		int forecastLevel = 0;
		forecastLevel = UiUtils.parseStringToInteger(customerLevel);
		customerBeanList.clear();
		for (final Leveldto dto : initialCustomerHierarchy) {
			customerBeanList.add(dto.getRelationshipLevelSid());
		}

		for (final Leveldto dto : initialCustomerHierarchy) {
			if (dto.getLevelNo() == 1) {
				selectedCustomerContainer.removeAllItems();
				selectedCustomerContainer.addItem(dto);
				selectedCustomerContainer.setChildrenAllowed(dto, true);
			} else {
				for (Object tempdto : selectedCustomerContainer.getItemIds()) {
					if (dto.getParentNode().contains("~")) {
						final String[] parentarr = dto.getParentNode().split("~");
						final String parentName = parentarr[1];
						if (getBeanFromId(tempdto).getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
							selectedCustomerContainer.addBean(dto);
							if (forecastLevel != dto.getLevelNo()) {
								selectedCustomerContainer.setChildrenAllowed(dto, true);
							} else {
								selectedCustomerContainer.setChildrenAllowed(dto, false);
							}
							selectedCustomerContainer.setParent(dto, tempdto);
							break;
						}
					} else {
						selectedCustomerContainer.addBean(dto);
						if (forecastLevel != dto.getLevelNo()) {
							selectedCustomerContainer.setChildrenAllowed(dto, true);
						} else {
							selectedCustomerContainer.setChildrenAllowed(dto, false);
						}
						selectedCustomerContainer.setParent(dto, tempdto);
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
		innerCustLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY,
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
		innerCustLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY, hierarchyVersion);
		for (int i = 1; i <= forecastLevel; i++) {
			String levelName = innerCustLevels.get(i - 1).getLevel();
			customerInnerLevelContainer.addItem(StringConstantsUtil.LEVEL_SPACE + i + " - " + levelName);
			if (i == innerLevel) {
				selectedLevelName = levelName;
			}
		}
		level.setContainerDataSource(customerInnerLevelContainer);
		level.select(StringConstantsUtil.LEVEL_SPACE + innerLevel + " - " + selectedLevelName);
	}

	private void loadProductLevel(final String hierarchyId, final int hierarchyVersion) {
		DataSelectionLogic logic = new DataSelectionLogic();
		innerProdLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY,
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
		innerProdLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY, hierarchyVersion);
		for (int i = 1; i <= forecastLevel; i++) {
			String levelName = innerProdLevels.get(i - 1).getLevel();
			productInnerLevelContainer.addItem(StringConstantsUtil.LEVEL_SPACE + i + " - " + levelName);
			if (i == innerLevel) {
				selectedLevelName = levelName;
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

	public void configureOnTabLoad(int projectionId, SessionDTO session) {
		try {
			customerDescriptionMap = sessionDTO.getCustomerDescription();
			productDescriptionMap = sessionDTO.getProductDescription();
			initializeFromDto();
			if (!CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
				initializeCustomerHierarchy(projectionId, String.valueOf(session.getCustomerLevelNumber()));
			}
			initializeProductHierarchy(projectionId, String.valueOf(session.getProductLevelNumber()));
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	public void loadCustomerLevel(final String hierarchyId, final String innerLevel, final int hierarchyVersion) {
		LOGGER.debug("Logging - loadCustomerLevel hierarchyId " + hierarchyId + " innerLevel " + innerLevel);
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			innerCustLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY,
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
		} catch (Exception ex) {
			LOGGER.error(ex + " in loadCustomerLevel");
		}

	}

	public void loadProductLevel(final String hierarchyId, final String innerLevel, final int hierarchyVersion) {
		LOGGER.debug("Logging - loadProductLevel hierarchyId " + hierarchyId + " innerLevel " + innerLevel);
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			innerProdLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY,
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
		} catch (Exception ex) {
			LOGGER.error(ex + " loadProductLevel");
		}
	}

	private void configureBusinessUnitDdlb() {
		businessUnit.addValueChangeListener(new Property.ValueChangeListener() {
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
		Future future = service.submit(new CFFDetailsInsertJobRun(projectionIdValue, tempTableNames));
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
		innerLevels = new DataSelectionLogic().loadCustomerForecastLevel(hierarchySid, StringUtils.EMPTY,
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

		int projectionId;
		GtnSmallHashMap tempTableNames;

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
			} catch (Exception ex) {
				Logger.getLogger(DataSelection.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public void configurePermission() {
		try {
			final StplSecurity stplSecurity = new StplSecurity();
			final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtil.USER_ID));
			Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId,
					"Consolidated Financial Forecast" + "," + "Data Selection");
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
			LOGGER.error(ex);
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
		return value == null && StringUtils.isBlank(value) ? 0 : Integer.parseInt(value.split(" ")[1]);
	}

	private Future checkAndDoAutomaticUpdate(Object value, int hierarchyId, ExecutorService executorService) {
		GtnAutomaticRelationServiceRunnable wsClientRunnableTarget = new GtnAutomaticRelationServiceRunnable(value,
				hierarchyId);
		Future future = executorService.submit(wsClientRunnableTarget);
		executorService.shutdown();
		return future;
	}

}
