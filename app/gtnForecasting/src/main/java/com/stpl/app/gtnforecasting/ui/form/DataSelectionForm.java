package com.stpl.app.gtnforecasting.ui.form;

import com.stpl.app.gtnforecasting.accrualrateprojection.dto.AccrualDataSelectionDTO;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.DSLogic;
import com.stpl.app.gtnforecasting.accrualrateprojection.ui.view.AccrualRateProjectionView;
import com.stpl.app.gtnforecasting.dto.CompanyDdlbDto;
import com.stpl.app.gtnforecasting.dto.RelationshipDdlbDto;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.logic.RelationShipFilterLogic;
import com.stpl.app.gtnforecasting.logic.tablelogic.DataSelectionSearchLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionUtil;
import com.stpl.app.gtnforecasting.ui.ForecastEditWindow;
import com.stpl.app.gtnforecasting.ui.ForecastWindow;
import com.stpl.app.gtnforecasting.ui.form.lookups.CustomerProductGroupLookup;
import com.stpl.app.gtnforecasting.ui.form.lookups.HierarchyLookup;
import com.stpl.app.gtnforecasting.ui.form.lookups.PrivatePublicView;
import com.stpl.app.gtnforecasting.ui.form.lookups.SaveViewPopup;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import static com.stpl.app.gtnforecasting.utils.Constant.NULL;
import com.stpl.app.gtnforecasting.utils.DataSelectionUtil;
import com.stpl.app.gtnforecasting.utils.HelperListUtil;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.GtnAutomaticRelationServiceRunnable;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_CUSTOMER_GROUP;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_CUSTOMER_HIERARCHY;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_LEVEL_NDC;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_PRIVATE_VIEW;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_PRODUCT_GROUP;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_PRODUCT_HIERARCHY;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_PUBLIC_VIEW;
import static com.stpl.app.utils.Constants.LabelConstants.MODE_ADD;
import static com.stpl.app.utils.Constants.LabelConstants.MODE_SEARCH;
import static com.stpl.app.utils.Constants.LabelConstants.PRIVATE_VIEW;
import static com.stpl.app.utils.Constants.LabelConstants.PUBLIC_VIEW;
import static com.stpl.app.utils.Constants.LabelConstants.SAVE_VIEW;
import static com.stpl.app.utils.Constants.LabelConstants.WINDOW_CUSTOMER_GROUP_LOOKUP;
import static com.stpl.app.utils.Constants.LabelConstants.WINDOW_CUSTOMER_HIERARCHY_LOOKUP;
import static com.stpl.app.utils.Constants.LabelConstants.WINDOW_PRODUCT_GROUP_LOOKUP;
import static com.stpl.app.utils.Constants.LabelConstants.WINDOW_PRODUCT_HIERARCHY_LOOKUP;
import com.stpl.app.utils.DateToStringConverter;
import com.stpl.app.utils.QueryUtils;
import com.stpl.app.utils.TableHeaderColumnsUtil;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.ui.forecastds.form.ForecastDataSelection;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.UIUtil;
import com.stpl.ifs.util.HelperDTO;
import static com.stpl.ifs.util.constants.GlobalConstants.getCommercialConstant;
import static com.stpl.ifs.util.constants.GlobalConstants.getGovernmentConstant;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.ComboBox;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

/**
 *
 * @author sooriya.lakshmanan
 */
public class DataSelectionForm extends ForecastDataSelection {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = -1811099338760834050L;

	/**
	 * The Constant LOGGER.
	 */
	private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DataSelectionForm.class);

	/**
	 * The Constant NAME.*
	 */
	protected static final String NAME = "DATA";

	/**
	 * The data selection binder.
	 */
	public CustomFieldGroup dataSelectionBinder;
	String screenName;

	Map<String, String> customerDescriptionMap = null;
	Map<String, String> productDescriptionMap = null;
	private boolean dismantleCustomerSelection = true;
	private boolean dismantleProductSelection = true;
	CompanyDdlbDto discountDdlbDefault = new CompanyDdlbDto(0, SELECT_ONE, true);
	CompanyDdlbDto discountDTO = null;
	DataSelectionLogic dataLogic = new DataSelectionLogic();
	DataSelectionSearchLogic tableLogic = new DataSelectionSearchLogic();
	protected ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
	List<Integer> customerBeanList = new ArrayList<>();
	List<Integer> productBeanList = new ArrayList<>();
	String publicViewName;
	String privateViewName;
	CommonUtil commonUtils = CommonUtil.getInstance();
	CommonUtils commonUtilData = new CommonUtils();
	DataSelectionForm dataSelectionForm = this;
	public final DataSelectionLogic dsLogic = new DataSelectionLogic();
	final NonMandatedLogic nmLogic = new NonMandatedLogic();
	public static final ResourceBundle alertMsg = ResourceBundle.getBundle("properties.alertmessage");
	boolean editnotif = false;
	List<Object[]> businessUnitlist;
	SessionDTO session;

	private List<Leveldto> productHierarchyLevelDefinitionList = Collections.emptyList();
	private List<Leveldto> customerHierarchyLevelDefinitionList = Collections.emptyList();
	private RelationShipFilterLogic relationLogic = RelationShipFilterLogic.getInstance();

	private Future customerFuture;
	private Future productFuture;

	public static final String NO_RECORD_WAS_SELECTED_PLEASE_TRY_AGAIN = "No record was selected.  Please try again.";

	public String getPublicViewName() {
		return publicViewName;
	}

	public void setPublicViewName(String publicViewName) {
		this.publicViewName = publicViewName;
	}

	public String getPrivateViewName() {
		return privateViewName;
	}

	public void setPrivateViewName(String privateViewName) {
		this.privateViewName = privateViewName;
	}

	HelperListUtil helperListUtil = HelperListUtil.getInstance();

	ResourceBundle tableName = ResourceBundle.getBundle("properties.Constants");

	// Used for CCP_HIERARCHY_INSERT query formation
	private String topLevelName = StringUtils.EMPTY;

	/**
	 * Instantiates a new data selection index.
	 *
	 * @param dataSelectionBinder
	 * @param dataSelectionDTO
	 * @throws java.lang.Exception
	 */
	public DataSelectionForm(CustomFieldGroup dataSelectionBinder, DataSelectionDTO dataSelectionDTO, String screenName)
			throws SystemException, PortalException {
		super(dataSelectionBinder, screenName, true);
		LOGGER.debug("DataSelectionIndex Initializing... ");
		this.dataSelectionBinder = dataSelectionBinder;
		this.dataSelectionDTO = dataSelectionDTO;
		this.screenName = screenName;
		configureFields();

		final StplSecurity stplSecurity = new StplSecurity();
		if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)
				|| screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
			Map<String, AppPermission> functionCompanyHM = null;
			if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
				functionCompanyHM = stplSecurity.getBusinessFunctionPermissionForNm(
						String.valueOf(VaadinSession.getCurrent().getAttribute("businessRoleIds")),
						getGovernmentConstant() + "," + UISecurityUtil.DATA_SELECTION_INDEX);
			} else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
				functionCompanyHM = stplSecurity.getBusinessFunctionPermissionForNm(
						String.valueOf(VaadinSession.getCurrent().getAttribute("businessRoleIds")),
						getCommercialConstant() + "," + UISecurityUtil.DATA_SELECTION_INDEX);
			}
			getButtonPermission(functionCompanyHM);
		}

		if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION)) {
			configurePermission();
		}

		LOGGER.debug("DataSelectionIndex Ends");
	}

	private void getButtonPermission(Map<String, AppPermission> functionCompanyHM) {
		if (functionCompanyHM.get(CommonUtils.GENERATE_BTN) != null
				&& !((AppPermission) functionCompanyHM.get(CommonUtils.GENERATE_BTN)).isFunctionFlag()) {
			buttonLay.removeComponent(getGenerateBtn());
		}
		if (functionCompanyHM.get(CommonUtils.SEARCH_BTN) != null
				&& !((AppPermission) functionCompanyHM.get(CommonUtils.SEARCH_BTN)).isFunctionFlag()) {
			buttonLay.removeComponent(getSearchBtn());
		}
		if (functionCompanyHM.get(CommonUtils.RESET_BTN) != null
				&& !((AppPermission) functionCompanyHM.get(CommonUtils.RESET_BTN)).isFunctionFlag()) {
			buttonLay.removeComponent(getResetBtn());
		}
		if (functionCompanyHM.get(CommonUtils.SAVE_VIEW_BTN) != null
				&& !((AppPermission) functionCompanyHM.get(CommonUtils.SAVE_VIEW_BTN)).isFunctionFlag()) {
			buttonLay.removeComponent(getSaveViewBtn());
		}

		if (functionCompanyHM.get(CommonUtils.DELETE_VIEW) != null
				&& !((AppPermission) functionCompanyHM.get(CommonUtils.DELETE_VIEW)).isFunctionFlag()) {

			buttonLay.removeComponent(getDeleteViewBtn());
		}

		if (functionCompanyHM.get(CommonUtils.RESULT_RESET_BTN) != null
				&& !((AppPermission) functionCompanyHM.get(CommonUtils.RESULT_RESET_BTN)).isFunctionFlag()) {
			finalBtn.removeComponent(getResultResetBtn());
		}
		if (functionCompanyHM.get(CommonUtils.EDIT) != null
				&& !((AppPermission) functionCompanyHM.get(CommonUtils.EDIT)).isFunctionFlag()) {
			finalBtn.removeComponent(getEditBtn());
		}
		if (functionCompanyHM.get(CommonUtils.VIEW_BTN) != null
				&& !((AppPermission) functionCompanyHM.get(CommonUtils.VIEW_BTN)).isFunctionFlag()) {
			finalBtn.removeComponent(getViewBtn());
		}
		if (functionCompanyHM.get(CommonUtils.DELETE_BTN) != null
				&& !((AppPermission) functionCompanyHM.get(CommonUtils.DELETE_BTN)).isFunctionFlag()) {
			finalBtn.removeComponent(getDeleteBtn());
		}
	}

	/**
	 * Configure fields.
	 */
	private void configureFields() throws SystemException {
		resultsTableLayout.addComponent(resultTable);
		resultsTableLayout.addComponent(tableLogic.createControls());
		tableLogic.setContainerDataSource(resultsContainer);
		tableLogic.sinkItemPerPageWithPageLength(false);
		resultTable.setSelectable(true);
		tableLogic.setPageLength(NumericConstants.TEN);
		setProductForecastLevelNullSelection();
		setProductLevelNullSelection();
		setProductRelationNullSelection();

		if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
			resultTable.setVisibleColumns(TableHeaderColumnsUtil.getInstance().dataselectionColumnsAccrual);
			resultTable.setColumnHeaders(TableHeaderColumnsUtil.getInstance().dataSelectionHeadersAccrual);
			productLevel.setVisible(false);
			customerLevel.setVisible(false);
			productForecastLevelLabel.setVisible(false);
			customerForecastLevelLabel.setVisible(false);
			modeOptionChange(true);
		} else {
			resultTable.setVisibleColumns(TableHeaderColumnsUtil.getInstance().dataSelectionColumns);
			resultTable.setColumnHeaders(TableHeaderColumnsUtil.getInstance().dataSelectionHeaders);
		}

		businessUnit.setPageLength(NumericConstants.SEVEN);
		businessUnit.setImmediate(true);
		businessUnit.addItem(0);
		businessUnit.setItemCaption(0, Constants.CommonConstants.SELECT_ONE.getConstant());
		businessUnit.setNullSelectionAllowed(true);
		businessUnit.setNullSelectionItemId(0);
		businessUnit.setInputPrompt(Constants.CommonConstants.SELECT_ONE.getConstant());
		businessUnit.markAsDirty();
		businessUnitlist = dsLogic.getBusinessUnits(0);
		if (businessUnitlist != null && !businessUnitlist.isEmpty()) {
			for (Object[] object : businessUnitlist) {
				businessUnit.addItem(object[0]);
				businessUnit.setItemCaption(object[0], object[NumericConstants.TWO] + Constant.SPACE
						+ Constant.DASH_NO_DATA + Constant.SPACE + object[NumericConstants.THREE]);
			}
		}
		businessUnit.setValue(null);
		businessUnit.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(Property.ValueChangeEvent event) {

				availableProductContainer.removeAllItems();
				selectedProductContainer.removeAllItems();
				productBeanList.clear();
			}
		});

		company.setPageLength(NumericConstants.SEVEN);
		company.setImmediate(true);
		company.addItem(0);
		company.setItemCaption(0, Constants.CommonConstants.SELECT_ONE.getConstant());
		company.setNullSelectionAllowed(true);
		company.setNullSelectionItemId(0);
		company.setInputPrompt(Constants.CommonConstants.SELECT_ONE.getConstant());
		company.markAsDirty();

		List<Object[]> companyList = dsLogic.getCompanies();
		if (companyList != null && !companyList.isEmpty()) {
			for (Object[] object : companyList) {
				company.addItem(object[0]);
				company.setItemCaption(object[0], object[NumericConstants.TWO] + Constant.SPACE + Constant.DASH_NO_DATA
						+ Constant.SPACE + object[NumericConstants.THREE]);
			}
		}
		company.setValue(null);

		company.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(Property.ValueChangeEvent event) {

				availableProductContainer.removeAllItems();
				selectedProductContainer.removeAllItems();
				productBeanList.clear();
			}
		});

		resultTable.setFilterBarVisible(true);
		resultTable.setSizeFull();
		resultTable.setImmediate(true);
		resultTable.setPageLength(NumericConstants.TEN);
		resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
		resultTable.addStyleName(Constant.FILTER_TABLE);
		resultTable.addStyleName("table-header-normal");
		com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils.getUserName();
		if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
			loadDiscountLevel();
		}
	}

	public void loadFilteredProductSelection(final String selectedLevel) {
		try {

			availableProductContainer.removeAllItems();
			int forecastLevel = 0;
			boolean isNdc = false;
			List<Leveldto> selectedCustomerContractList;
			String levelName = Constant.LEVEL_LABEL;
			List<Leveldto> resultedLevelsList;
			if (selectedLevel != null && !Constants.CommonConstants.NULL.getConstant().equals(selectedLevel)
					&& !SELECT_ONE.equals(selectedLevel)) {
				productFuture.get();
				int relationVersionNo = Integer.parseInt(
						productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
				dataSelectionDTO.setProductRelationShipVersionNo(relationVersionNo);
				String customerVersionNo = customerRelationVersionComboBox
						.getItemCaption(customerRelationVersionComboBox.getValue());
				int customerRelationVersionNo = customerVersionNo == null ? 0 : Integer.parseInt(customerVersionNo);
				int hierarchyVersionNo = Integer.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
				productDescriptionMap = relationLogic.getLevelValueMap(String.valueOf(productRelation.getValue()),
						productHierarchyDto.getHierarchyId(), hierarchyVersionNo, relationVersionNo);
				String dedLevel = StringUtils.EMPTY;
				String dedValue = StringUtils.EMPTY;
				if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)
						&& !(deductionValue.getValue() instanceof String)) {
					dedLevel = getDedutionLevel(String.valueOf(deductionLevel.getValue()));
					dedValue = deductionValue.getValue() == null ? StringUtils.EMPTY
							: String.valueOf(((HelperDTO) deductionValue.getValue()).getId());
				}

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
				isNdc = (selectedHierarchyLevelDto.getLevel().equalsIgnoreCase("Package")
						|| selectedHierarchyLevelDto.getLevel().equalsIgnoreCase("NDC-11"));
				selectedCustomerContractList = getSelectedCustomerContractList();

				List<String> tempGroupFileter = groupFilteredItems == null ? Collections.<String>emptyList()
						: groupFilteredItems;
				resultedLevelsList = relationLogic.loadAvailableProductlevel(selectedHierarchyLevelDto,
						Integer.valueOf(relationshipSid), tempGroupFileter, selectedCustomerContractList, isNdc,
						hierarchyLevelDefinitionList, customerHierarchyDefinitionList, dedLevel, dedValue,
						relationVersionNo, customerRelationVersionNo, businessUnit.getValue());
				if (selectedHierarchyLevelDto.getLevel() != null) {
					levelName = selectedHierarchyLevelDto.getLevel();
				}
				availableProduct.removeAllItems();
				availableProductContainer.removeAllItems();
				availableProductContainer.addAll(resultedLevelsList);
			}

			availableProduct.setContainerDataSource(availableProductContainer);
			if (isNdc) {
				availableProduct.setVisibleColumns(Constant.DISPLAY_VALUE, "form", "strength");
				availableProduct.setColumnHeaders(Constant.NDC, "Form", "Strength");
			} else {
				availableProduct.setVisibleColumns(Constant.DISPLAY_VALUE);
				availableProduct.setColumnHeaders(levelName);
			}
			availableProduct.setFilterBarVisible(true);
			availableProduct.setFilterDecorator(new ExtDemoFilterDecorator());
			availableProduct.setStyleName(Constant.FILTER_TABLE);

		} catch (Exception ex) {

			LOGGER.error(ex + " - in loadFilteredProductSelection");
		}
	}

	private List<Leveldto> getSelectedCustomerContractList() {
		List<Leveldto> ccList = Collections.emptyList();
		if (selectedCustomerContainer != null && !selectedCustomerContainer.getItemIds().isEmpty()) {
			Leveldto currentDto;
			ccList = new ArrayList<>();
			for (Object item : selectedCustomerContainer.getItemIds()) {
				currentDto = DataSelectionUtil.getBeanFromId(item);
				if (currentDto != null && !StringUtils.isBlank(currentDto.getTableName())) {
					ccList.add(currentDto);
				}
			}
		}
		return ccList;
	}

	/**
	 * Loads data selection after selecting a private/public view
	 *
	 * @param viewDTO
	 *            DTO with view parameters
	 */
	private void loadView(ViewDTO viewDTO) {
		dismantleCustomerSelection = true;
		dismantleProductSelection = true;
		customerHierarchyDto = new HierarchyLookupDTO();
		productHierarchyDto = new HierarchyLookupDTO();
		company.setValue(Integer.valueOf(viewDTO.getCompanyMasterSid()));
		businessUnit.setValue(viewDTO.getBusinessUnitSystemId());
		viewId.setValue(viewDTO.getViewId());
		projectionId.setValue(viewDTO.getProjectionId());
		dataSelectionDTO.setProjectionId(UiUtils.parseStringToInteger(viewDTO.getProjectionId()));
		projectionName.setValue(viewDTO.getProjectionName());
		description.setValue(viewDTO.getDescription());
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
		if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
			deductionLevel.setValue(viewDTO.getDeductionLevel());

			deductionValue.setValue(
					helperListUtil.getHelperDTOByID(StringUtils.isNumeric(String.valueOf(viewDTO.getDeductionValueId()))
							? Integer.valueOf(viewDTO.getDeductionValueId()) : 0));
		}

		RelationshipDdlbDto selectedCustomerRelationshipDdlbDto = null;
		if (!StringUtils.isBlank(viewDTO.getCustRelationshipBuilderSid())
				&& !Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCustRelationshipBuilderSid())) {
			selectedCustomerRelationshipDdlbDto = new RelationshipDdlbDto();
			selectedCustomerRelationshipDdlbDto.setRelationshipBuilderSid(viewDTO.getCustRelationshipBuilderSid());
		}
		loadRelationDdlb(UiUtils.parseStringToInteger(viewDTO.getCustomerHierarchySid()),
				selectedCustomerRelationshipDdlbDto, customerRelationComboBox);
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyGroupSid())
				&& !DASH.equals(viewDTO.getCompanyGroupSid())
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
				&& !DASH.equals(viewDTO.getProductGroupSid())
				&& !StringUtils.EMPTY.equals(viewDTO.getProductGroupSid())) {
			dataSelectionDTO.setProdGrpSid(viewDTO.getProductGroupSid());
		}
		productGroup.setValue(viewDTO.getProductGroup());
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCustomerHierarchySid())
				&& !DASH.equals(viewDTO.getCustomerHierarchySid())
				&& !StringUtils.EMPTY.equals(viewDTO.getCustomerHierarchySid())) {

			loadCustomerLevel(viewDTO.getCustomerHierarchySid(), viewDTO.getCustomerLevel());
			loadInnerCustomerLevel(Integer.parseInt(viewDTO.getCustomerLevel()),
					UiUtils.parseStringToInteger(viewDTO.getCustomerInnerLevel()),
					UiUtils.parseStringToInteger(viewDTO.getCustomerHierarchySid()));
			initializeCustomerHierarchy(UiUtils.parseStringToInteger(viewDTO.getProjectionId()),
					viewDTO.getCustomerLevel());
			dataSelectionDTO.setCustomerHierSid(viewDTO.getCustomerHierarchySid());
		}
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyGroupSid())
				&& !DASH.equals(viewDTO.getCompanyGroupSid())
				&& !StringUtils.EMPTY.equals(viewDTO.getCompanyGroupSid())) {
			dataSelectionDTO.setCustomerGrpSid(viewDTO.getCompanyGroupSid());
			triggerCustGrpOnView(viewDTO.getCompanyGroupSid(), true);
		}
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductHierarchySid())
				&& !DASH.equals(viewDTO.getProductHierarchySid())
				&& !StringUtils.EMPTY.equals(viewDTO.getProductHierarchySid())) {
			loadProductLevel(viewDTO.getProductHierarchySid(), viewDTO.getProductLevel());
			loadInnerProductLevel(Integer.parseInt(viewDTO.getProductLevel()),
					UiUtils.parseStringToInteger(viewDTO.getProductInnerLevel()),
					UiUtils.parseStringToInteger(viewDTO.getProductHierarchySid()));
			initializeProductHierarchy(UiUtils.parseStringToInteger(viewDTO.getProjectionId()),
					viewDTO.getProductLevel());
			dataSelectionDTO.setProdHierSid(viewDTO.getProductHierarchySid());
		}
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductGroupSid())
				&& !DASH.equals(viewDTO.getProductGroupSid())
				&& !StringUtils.EMPTY.equals(viewDTO.getProductGroupSid())) {
			dataSelectionDTO.setProdGrpSid(viewDTO.getProductGroupSid());
			triggerProdGrpOnView(viewDTO.getProductGroupSid(), false);
		}
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyMasterSid())
				&& !DASH.equals(viewDTO.getCompanyMasterSid())
				&& !StringUtils.EMPTY.equals(viewDTO.getCompanyMasterSid())) {
			dataSelectionDTO.setCompanySid(viewDTO.getCompanyMasterSid());
		}
		deleteViewBtn.setEnabled(true);

		DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(), screenName);
		fromPeriod.select(viewDTO.getFromPeriod());
		toPeriod.select(viewDTO.getToPeriod());

		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getCompanyGroupSid())
				&& !DASH.equals(viewDTO.getCompanyGroupSid())
				&& !StringUtils.EMPTY.equals(viewDTO.getCompanyGroupSid())) {
			dataSelectionDTO.setCustomerGrpSid(viewDTO.getCompanyGroupSid());
			selectedCustomerGroupDTO.setCustomerGroupSid(String.valueOf(viewDTO.getCompanyGroupSid()));
		}
		if (!Constants.CommonConstants.NULL.getConstant().equals(viewDTO.getProductGroupSid())
				&& !DASH.equals(viewDTO.getProductGroupSid())
				&& !StringUtils.EMPTY.equals(viewDTO.getProductGroupSid())) {
			dataSelectionDTO.setProdGrpSid(viewDTO.getProductGroupSid());
			selectedProductGroupDTO.setProductGroupSid(String.valueOf(viewDTO.getProductGroupSid()));
		}

	}

	public DataSelectionDTO bindDataselectionDtoToSave() {
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");

			dataSelectionDTO.clear();
			SimpleDateFormat format = new SimpleDateFormat(Constants.CommonConstants.DATE_FORMAT.getConstant());
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
			if (deductionLevel.getValue() != null && !SELECT_ONE.equals(deductionLevel.getValue())) {
				dataSelectionDTO.setDeductionLevel(String.valueOf(deductionLevel.getValue()));
			}
			if (deductionValue.getValue() != null && !SELECT_ONE.equals(deductionValue.getValue())) {
				dataSelectionDTO.setDeductionValue(String.valueOf(deductionValue.getValue()));
			}

			if (customerRelationComboBox.getValue() != null
					&& !SELECT_ONE.equals(customerRelationComboBox.getValue())) {

				dataSelectionDTO.setCustRelationshipBuilderSid(String.valueOf(customerRelationComboBox.getValue()));
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

				if (!StringUtils.isBlank(customerHierarchy.getValue())
						|| Constant.NULL.equals(customerHierarchy.getValue())) {
					dataSelectionDTO.setCustomerHierSid(String.valueOf(customerHierarchyDto.getHierarchyId()));
				} else {
					dataSelectionDTO.setCustomerHierSid(String.valueOf(0));
				}
			} else {
				dataSelectionDTO.setCustomerHierSid(String.valueOf(0));
			}
			if (productHierarchyDto != null
					&& !Constants.CommonConstants.NULL.getConstant()
							.equals(String.valueOf(productHierarchyDto.getHierarchyId()))
					&& !StringUtils.isEmpty(String.valueOf(productHierarchyDto.getHierarchyId()))
					&& !StringUtils.isBlank(String.valueOf(productHierarchyDto.getHierarchyId()))) {

				if (!(StringUtils.isBlank(productHierarchy.getValue())
						|| Constant.NULL.equals(productHierarchy.getValue()))) {
					dataSelectionDTO.setProdHierSid(String.valueOf(productHierarchyDto.getHierarchyId()));
				} else {
					dataSelectionDTO.setProdHierSid(String.valueOf(0));
				}

			} else {
				dataSelectionDTO.setProdHierSid(String.valueOf(0));
			}

			if (selectedCustomerGroupDTO != null
					&& !Constants.CommonConstants.NULL.getConstant()
							.equals(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()))
					&& !StringUtils.isEmpty(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()))
					&& !StringUtils.isBlank(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()))
					&& !customerGroup.getValue().isEmpty()) {
				dataSelectionDTO.setCustomerGrpSid(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()));
			} else {
				dataSelectionDTO.setCustomerGrpSid(String.valueOf(0));
			}
			if (selectedProductGroupDTO != null
					&& !Constants.CommonConstants.NULL.getConstant()
							.equals(String.valueOf(selectedProductGroupDTO.getProductGroupSid()))
					&& !StringUtils.isEmpty(String.valueOf(selectedProductGroupDTO.getProductGroupSid()))
					&& !StringUtils.isBlank(String.valueOf(selectedProductGroupDTO.getProductGroupSid()))
					&& !productGroup.getValue().isEmpty()) {
				dataSelectionDTO.setProdGrpSid(String.valueOf(selectedProductGroupDTO.getProductGroupSid()));
			} else {
				dataSelectionDTO.setProdGrpSid(String.valueOf(0));
			}

			int customerForecastLevel = 0;
			int productForecastLevel = 0;
			int customerForecastInnerLevel = 0;
			int productForecastInnerLevel = 0;
			if (customerLevel.getValue() != null && !SELECT_ONE.equals(customerLevel.getValue())) {
				customerForecastLevel = UiUtils
						.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]);
			}
			if (productLevel.getValue() != null && !SELECT_ONE.equals(productLevel.getValue())) {
				productForecastLevel = UiUtils
						.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]);
			}
			if (level.getValue() != null && !SELECT_ONE.equals(level.getValue())) {
				customerForecastInnerLevel = UiUtils
						.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]);
			}
			if (productlevelDdlb.getValue() != null && !SELECT_ONE.equals(productlevelDdlb.getValue())) {
				productForecastInnerLevel = UiUtils
						.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]);
			}

			dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(customerForecastLevel));
			dataSelectionDTO.setProductHierarchyLevel(String.valueOf(productForecastLevel));
			dataSelectionDTO.setProductHierarchy(productHierarchy.getValue());
			dataSelectionDTO.setCustomerHierarchy(customerHierarchy.getValue());
			if (customerHierarchyDto != null) {
				int custHierarchyVersionNo = Integer
						.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
				dataSelectionDTO.setCustomerHierVersionNo(custHierarchyVersionNo);
			} else {
				dataSelectionDTO.setCustomerHierVersionNo(0);
			}
			if (productHierarchyDto != null) {
				int prodHierarchyVersionNo = Integer
						.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
				dataSelectionDTO.setProductHierVersionNo(prodHierarchyVersionNo);
			} else {
				dataSelectionDTO.setProductHierVersionNo(0);
			}

			if (fromPeriod.getData() == null) {
				if (fromPeriod.getValue() != null) {
					if (MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
						dataSelectionDTO.setFromPeriod(String.valueOf(fromPeriod.getValue()));
						if (!String.valueOf(fromPeriod.getValue()).isEmpty()
								&& !Constant.SELECT_ONE.equals(String.valueOf(fromPeriod.getValue()))) {
							dataSelectionDTO.setFromDate(format.parse(
									DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
						}
					} else {
						dataSelectionDTO.setFromPeriod(
								DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
						dataSelectionDTO.setFromDate(format
								.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
					}

				}
			} else if (fromPeriod.getValue() != null) {
				if (MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
					dataSelectionDTO.setFromPeriod(String.valueOf(fromPeriod.getValue()));
					if (!String.valueOf(fromPeriod.getValue()).isEmpty()
							&& !Constant.SELECT_ONE.equals(String.valueOf(fromPeriod.getValue()))) {
						dataSelectionDTO.setFromDate(format
								.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
					}
				} else {
					// Getting Current Year & Period Value
					Date dateFromValue = format
							.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
					int quarterFromValue = DataSelectionUtil.getQuarter(dateFromValue);
					int yearFromValue = DataSelectionUtil.getYearFromDate(dateFromValue);

					// Getting Existing Year & Period Value
					Date date = inputFormat.parse(String.valueOf(fromPeriod.getData()));
					int quarterFromFc = DataSelectionUtil.getQuarter(date);
					int yearFromFc = DataSelectionUtil.getYearFromDate(date);
					// Comparing Existing year Value with New Value
					if (yearFromValue == yearFromFc) {
						// Comparing Existing year Period with New Year Period
						if (quarterFromValue == quarterFromFc) {
							String outputString = format.format(date);
							dataSelectionDTO.setFromPeriod(outputString);
							dataSelectionDTO.setFromDate(format.parse(outputString));
						} else {
							dataSelectionDTO.setFromPeriod(
									DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
							dataSelectionDTO.setFromDate(format.parse(
									DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
						}
					} else {
						dataSelectionDTO.setFromPeriod(
								DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
						dataSelectionDTO.setFromDate(format
								.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
					}
				}

			}
			if (toPeriod.getData() == null) {
				if (toPeriod.getValue() != null) {
					if (MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
						dataSelectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
						if (!String.valueOf(toPeriod.getValue()).isEmpty()
								&& !Constant.SELECT_ONE.equals(String.valueOf(toPeriod.getValue()))) {
							dataSelectionDTO.setToDate(format.parse(
									DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue()))));
						}
					} else {
						dataSelectionDTO.setToPeriod(
								DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue())));
						dataSelectionDTO.setToDate(format
								.parse(DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue()))));
					}
				}

			} else if (toPeriod.getValue() != null) {
				if (MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
					dataSelectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
					if (!String.valueOf(toPeriod.getValue()).isEmpty()
							&& !Constant.SELECT_ONE.equals(String.valueOf(toPeriod.getValue()))) {
						Date date = inputFormat.parse(String.valueOf(toPeriod.getData()));
						String outputString = format.format(date);
						dataSelectionDTO.setToDate(format.parse(outputString));
					}
				} else {
					Date date = inputFormat.parse(String.valueOf(toPeriod.getData()));
					Date dateToValue = format
							.parse(DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue())));
					int quarterToValue = DataSelectionUtil.getQuarter(dateToValue);
					int yearToValue = DataSelectionUtil.getYearFromDate(dateToValue);
					int quarterToFc = DataSelectionUtil.getQuarter(date);
					int yearToFc = DataSelectionUtil.getYearFromDate(dateToValue);
					if (yearToValue == yearToFc) {
						if (quarterToValue == quarterToFc) {
							String outputString = format.format(date);
							dataSelectionDTO.setToPeriod(outputString);
							dataSelectionDTO.setToDate(format.parse(outputString));
						} else {
							dataSelectionDTO.setToPeriod(
									DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue())));
							dataSelectionDTO.setToDate(format.parse(
									DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue()))));
						}

					} else {
						dataSelectionDTO.setToPeriod(
								DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue())));
						dataSelectionDTO.setToDate(format
								.parse(DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue()))));
					}
				}
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

			if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

				dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(customerForecastInnerLevel));
				dataSelectionDTO.setProductHierarchyLevel(String.valueOf(productForecastInnerLevel));
			} else {
				dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(customerForecastLevel));
				dataSelectionDTO.setProductHierarchyLevel(String.valueOf(productForecastLevel));
			}

			dataSelectionDTO.setCustomerHierarchyInnerLevel(String.valueOf(customerForecastInnerLevel));
			dataSelectionDTO.setProductHierarchyInnerLevel(String.valueOf(productForecastInnerLevel));
			dataSelectionDTO.setProjectionName(projectionName.getValue());
			dataSelectionDTO.setDescription(description.getValue());

		} catch (ParseException ex) {

			LOGGER.error(ex + " in binding for save, can't parse dates");
		}
		return dataSelectionDTO;
	}

	/**
	 * Move left btn.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	protected void moveLeftButtonLogic() {
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			int customerHierarchyVersionNo = Integer
					.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
			int customerRelationVersionNo = Integer.parseInt(
					customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
			if (availableCustomer.getValue() != null) {
				int forecastLevel = 0;
				if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
					if (level.getValue() != null) {
						forecastLevel = UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]);
					}
				} else if (customerLevel.getValue() != null) {
					forecastLevel = UiUtils
							.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]);
				}
				Object item = availableCustomer.getValue();
				if (selectedCustomerContainer.size() > 0) {
					if (selectedCustomer.getValue() != null) {

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
						if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
									customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]));
						} else {
							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(
											String.valueOf(customerLevel.getValue()).split("-")[0]),
									customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]));
						}
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
						if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
									customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]));
						} else {
							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(
											String.valueOf(customerLevel.getValue()).split("-")[0]),
									customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]));
						}
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

				} else if (availableCustomer.getValue() != null
						&& (DataSelectionUtil.getBeanFromId(availableCustomer.getValue()).getLevelNo() > 1)) {

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
					if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
								customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(level.getValue()).split("-")[0]));
					} else {
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]),
								customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(level.getValue()).split("-")[0]));
					}
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
				} else {
					Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);

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
					if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
								customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(level.getValue()).split("-")[0]));
					} else {
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]),
								customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(level.getValue()).split("-")[0]));
					}
					if ((newChildLevels != null) && (!newChildLevels.isEmpty())) {
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
			int customerHierarchyVersionNo = Integer
					.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
			int customerRelationVersionNo = Integer.parseInt(
					customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
			if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
				if (level.getValue() != null) {
					forecastLevel = UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]);
				}
			} else if (customerLevel.getValue() != null) {
				forecastLevel = UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]);
			}
			if (availableCustomerContainer.size() > 0) {
				List<Leveldto> iteams = new ArrayList<>(availableCustomerContainer.getItemIds());
				Object selectedItem = null;
				if (selectedCustomerContainer.size() > 0) {
					if (selectedCustomer.getValue() != null) {
						selectedItem = selectedCustomer.getValue();
						for (Leveldto item : iteams) {
							if (DataSelectionUtil.getBeanFromId(item).getLevelNo() > DataSelectionUtil
									.getBeanFromId(selectedItem).getLevelNo()) {

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
								if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

									newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
											UiUtils.parseStringToInteger(
													String.valueOf(level.getValue()).split("-")[0]),
											customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
											customerHierarchyVersionNo, customerRelationVersionNo,
											UiUtils.getDataSelectionFormattedLevelNo(
													String.valueOf(level.getValue()).split("-")[0]));
								} else {
									newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
											UiUtils.parseStringToInteger(
													String.valueOf(customerLevel.getValue()).split("-")[0]),
											customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
											customerHierarchyVersionNo, customerRelationVersionNo,
											UiUtils.getDataSelectionFormattedLevelNo(
													String.valueOf(level.getValue()).split("-")[0]));
								}
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
							if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

								newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
										UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
										customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
										customerHierarchyVersionNo, customerRelationVersionNo,
										UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(level.getValue()).split("-")[0]));
							} else {
								newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
										UiUtils.parseStringToInteger(
												String.valueOf(customerLevel.getValue()).split("-")[0]),
										customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
										customerHierarchyVersionNo, customerRelationVersionNo,
										UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(level.getValue()).split("-")[0]));
							}
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
				} else if (level.getValue() != null
						&& UiUtils.parseStringToInteger(String.valueOf(level.getValue())) == 1) {

					for (Leveldto item : iteams) {
						selectedCustomerContainer.removeAllItems();
						selectedCustomer.removeAllItems();
						Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);

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
						if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
									customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]));
						} else {
							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(
											String.valueOf(customerLevel.getValue()).split("-")[0]),
									customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]));
						}
						if ((newChildLevels != null) && (!newChildLevels.isEmpty())) {
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
				} else {

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
								if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
									selectedParent = selectedLevel;
									break;
								}
							}
						}
						newParentLevels = logic.getParentLevelsWithHierarchyNo(
								UiUtils.stringListToString(uncommonValues), customerDescriptionMap,
								customerHierarchyVersionNo, customerRelationVersionNo);
						if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
									customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]));
						} else {
							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(
											String.valueOf(customerLevel.getValue()).split("-")[0]),
									customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]));
						}
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

		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	@Override
	protected void moveAllProductButtonLogic() {
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			int forecastLevel = 0;
			int productHierarchyVersionNo = Integer.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
			int productRelationVersionNo = Integer
					.parseInt(productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
			if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
				if (productlevelDdlb.getValue() != null) {
					forecastLevel = UiUtils
							.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]);
				}
			} else if (productLevel.getValue() != null) {
				forecastLevel = UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]);
			}

			if (availableProductContainer.size() > 0) {
				List<Leveldto> iteams = new ArrayList<>(availableProductContainer.getItemIds());
				Object selectedItem = null;
				if (selectedProductContainer.size() > 0) {
					if (selectedProduct.getValue() != null) {
						selectedItem = selectedProduct.getValue();
						for (Leveldto item : iteams) {
							if (DataSelectionUtil.getBeanFromId(item).getLevelNo() > DataSelectionUtil
									.getBeanFromId(selectedItem).getLevelNo()) {

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
									uncommonValues.removeAll(removeValues);
								}
								newParentLevels = logic.getParentLevelsWithHierarchyNo(
										UiUtils.stringListToString(uncommonValues), productDescriptionMap,
										productHierarchyVersionNo, productRelationVersionNo);
								if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

									newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
											UiUtils.parseStringToInteger(
													String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
											productDescriptionMap, businessUnit.getValue(),
											DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
											productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
													String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
								} else {
									newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
											UiUtils.parseStringToInteger(
													String.valueOf(productLevel.getValue()).split("-")[0]),
											productDescriptionMap, businessUnit.getValue(),
											DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
											productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
													String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
								}
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
							if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

								newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
										UiUtils.parseStringToInteger(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
										productDescriptionMap, businessUnit.getValue(),
										DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
										productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
							} else {
								newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
										UiUtils.parseStringToInteger(
												String.valueOf(productLevel.getValue()).split("-")[0]),
										productDescriptionMap, businessUnit.getValue(),
										DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
										productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
							}
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
				} else {

					String tempProductLevel = String
							.valueOf(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)
									? productlevelDdlb.getValue() : productLevel.getValue());
					if (tempProductLevel != null && UiUtils.parseStringToInteger(tempProductLevel) == 1) {

						for (Leveldto item : iteams) {
							selectedProductContainer.removeAllItems();
							selectedProduct.removeAllItems();
							Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);

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
							if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

								newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
										UiUtils.parseStringToInteger(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
										productDescriptionMap, businessUnit.getValue(),
										DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
										productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
							} else {
								newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
										UiUtils.parseStringToInteger(
												String.valueOf(productLevel.getValue()).split("-")[0]),
										productDescriptionMap, businessUnit.getValue(),
										DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
										productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
							}
							if ((newChildLevels != null) && (!newChildLevels.isEmpty())) {
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
							if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

								newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
										UiUtils.parseStringToInteger(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
										productDescriptionMap, businessUnit.getValue(),
										DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
										productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
							} else {
								newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
										UiUtils.parseStringToInteger(
												String.valueOf(productLevel.getValue()).split("-")[0]),
										productDescriptionMap, businessUnit.getValue(),
										DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
										productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
							}
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

	/**
	 * Move left btn.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	protected void moveRigthButtonLogic() {
		if (selectedCustomer.getValue() != null) {
			Object selectedItem = selectedCustomer.getValue();
			DataSelectionUtil.removeItemsRecursively(selectedItem, selectedCustomer, selectedCustomerContainer);
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
	}

	/**
	 * Move left product.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	protected void moveRigthProductButtonLogic() {
		try {
			if (selectedProduct.getValue() != null) {
				List<Leveldto> listValue;
				Object selectedItem = selectedProduct.getValue();
				Leveldto selectedLevel = (Leveldto) DataSelectionUtil.getBeanFromId(selectedItem);
				String levelInString = DASH;
				if (!String.valueOf(productlevelDdlb.getValue()).equalsIgnoreCase(String.valueOf(SELECT_ONE))) {
					levelInString = String.valueOf(productlevelDdlb.getValue());
				}
				int currentLevel = UiUtils.parseStringToInteger(levelInString);
				if ((currentLevel != 0 && selectedLevel.getLevelNo() == currentLevel)
						&& (Constant.NDC.equalsIgnoreCase(selectedLevel.getLevel()))) {
					listValue = DataSelectionUtil.getFSValue(selectedLevel.getRelationshipLevelValue(),
							selectedLevel.getFieldName());
					selectedLevel.setForm(StringUtils.EMPTY + listValue.get(0).getForm());
					selectedLevel.setStrength(StringUtils.EMPTY + listValue.get(0).getStrength());

				}
				DataSelectionUtil.removeItemsRecursively(selectedItem, selectedProduct, selectedProductContainer);
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
			LOGGER.error(ex + " in moveRightProduct");
		}

	}

	/**
	 * Move right product.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	protected void moveLeftProductButtonLogic() {
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			int forecastLevel = 0;
			int productHierarchyVersionNo = Integer.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
			int productRelationVersionNo = Integer
					.parseInt(productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
			if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
				if (productlevelDdlb.getValue() != null) {
					forecastLevel = UiUtils
							.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]);
				}
			} else if (productLevel.getValue() != null) {
				forecastLevel = UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]);
			}

			if (availableProduct.getValue() != null) {
				Object item = availableProduct.getValue();
				if (selectedProductContainer.size() > 0) {
					if (selectedProduct.getValue() != null) {

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
							uncommonValues.removeAll(removeValues);
						}
						if (!uncommonValues.isEmpty()) {
							newParentLevels = logic.getParentLevelsWithHierarchyNo(
									UiUtils.stringListToString(uncommonValues), productDescriptionMap,
									productHierarchyVersionNo, productRelationVersionNo);
						}
						if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
									productDescriptionMap, businessUnit.getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
						} else {
							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
									productDescriptionMap, businessUnit.getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
						}
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
						if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
									productDescriptionMap, businessUnit.getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
						} else {
							newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
									UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
									productDescriptionMap, businessUnit.getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
						}
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
				} else if (availableProduct.getValue() != null
						&& (DataSelectionUtil.getBeanFromId(availableProduct.getValue()).getLevelNo() > 1)) {

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
					if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								productDescriptionMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
					} else {
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
								productDescriptionMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
					}
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
				} else {
					Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);

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
					if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {

						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								productDescriptionMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
					} else {
						newChildLevels = logic.getChildLevelsWithHierarchyNo(currentHierarchyNo,
								UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
								productDescriptionMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]));
					}
					if ((newChildLevels != null) && (!newChildLevels.isEmpty())) {
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
				DataSelectionLogic.selectedProductTableAlignmentChange(selectedProduct, selectedProductContainer);
			} else {
				AbstractNotificationUtils.getErrorNotification("No Product hierarchy level Selected",
						"No Level was selected to move. Please try again.");
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	/**
	 * Search button logic.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	protected void searchButtonLogic() {
		try {
			resultsContainer.removeAllItems();
			resultTable.setContainerDataSource(resultsContainer);
			if (resultsLazyContainer != null) {
				resultsLazyContainer.removeAllItems();
			}
			resultTable.removeAllItems();
			if ((StringUtils.isBlank(projectionName.getValue()) || Constant.NULL.equals(projectionName.getValue()))
					&& (StringUtils.isBlank(description.getValue()) || Constant.NULL.equals(description.getValue()))
					&& (StringUtils.isBlank(productHierarchy.getValue())
							|| Constant.NULL.equals(productHierarchy.getValue()))
					&& (StringUtils.isBlank(customerHierarchy.getValue())
							|| Constant.NULL.equals(customerHierarchy.getValue()))
					&& (company.getValue() == null || SELECT_ONE.equals(company.getValue()))
					&& (businessUnit.getValue() == null || SELECT_ONE.equals(businessUnit.getValue()))
					&& (deductionLevel.getValue() == null || SELECT_ONE.equals(deductionLevel.getValue())
							|| StringUtils.isBlank(String.valueOf(deductionLevel.getValue())))
					&& (deductionValue.getValue() == null || SELECT_ONE.equals(deductionValue.getValue())
							|| StringUtils.isBlank(String.valueOf(deductionValue.getValue())))
					&& (StringUtils.isBlank(customerGroup.getValue()) || Constant.NULL.equals(customerGroup.getValue()))
					&& (StringUtils.isBlank(productGroup.getValue())
							|| Constant.NULL.equals(productGroup.getValue()))) {
				AbstractNotificationUtils.getErrorNotification(
						Constants.MessageConstants.NO_SEARCH_CRITERIA_TITLE.getConstant(),
						Constants.MessageConstants.NO_SEARCH_CRITERIA_BODY.getConstant());
				resultTable.setVisibleColumns(TableHeaderColumnsUtil.getInstance().dataSelectionColumns);
				resultTable.setColumnHeaders(TableHeaderColumnsUtil.getInstance().dataSelectionHeaders);
			} else {
				bindDataselectionDtoToSave();
				dataSelectionDTO
						.setSelectedCustomerRelationSid(getRelationshipSid(selectedCustomerContainer.getItemIds()));
				dataSelectionDTO
						.setSelectedProductRelationSid(getRelationshipSid(selectedProductContainer.getItemIds()));
				dataSelectionDTO.setModulName(screenName);
				if (tableLogic.fireSetData(dataSelectionDTO, false)) {
					resultTable.setRefresh(Boolean.FALSE);

					setTableHeader();
					resultTable.setConverter("createdDateSearch", new DateToStringConverter());
					resultTable.setConverter("modifiedDateSearch", new DateToStringConverter());
					resultTable.setRefresh(Boolean.TRUE);
				} else {
					setTableHeader();
					AbstractNotificationUtils.getErrorNotification(
							Constants.MessageConstants.NO_RECORDS_TITLE.getConstant(),
							"There were no records matching the search criteria.  Please try again.");
				}
			}

		} catch (Exception ex) {

			LOGGER.error(ex + " searchBtn");
		}

	}

	/**
	 * Delete view btn.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	protected void deleteViewButtonLogic() {
		final ViewDTO dto = getViewDTO();
		String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
		Map<String, String> userMap = CommonUtils.getAllUsers();
		String userName = userMap.get(userId);
		if (!dto.getCreatedBy().equals(userName)) {
			com.stpl.ifs.ui.util.AbstractNotificationUtils.getErrorNotification("Delete View Confirmation",
					"You do not have permission to delete this projection.It can only be deleted by the creator");
		} else {
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
							final String flag = NonMandatedLogic.deleteView(Integer.parseInt(dto.getViewId()));
							resetOne();
							deductionLevel.setValue(null);
							deductionValue.setValue(null);
							modeOption.select("Add");
							dismantleCustomerSelection = true;
							dismantleProductSelection = true;
							resetButtonLogic();
							if (!Constant.FAIL.equals(flag)) {
								CommonUIUtils
										.getMessageNotification(dto.getViewName() + " has been successfully deleted.");
							}
							deleteViewBtn.setEnabled(false);
							viewDTO = new ViewDTO();
						}

					} catch (SystemException sysException) {
						LOGGER.error(sysException);
					} catch (PortalException porException) {
						LOGGER.error(porException);
					} catch (Exception exception) {
						LOGGER.error(exception);
					}
				}
			}.getConfirmationMessage(Constants.MessageConstants.CONFIRM_DELETION_TITLE.getConstant(),
					Constants.MessageConstants.CONFIRM_DELETION_BODY.getConstant().replace("?#", dto.getViewName()));
		}
	}

	/**
	 * Edit button logic. Used to edit a projection.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	protected void editButtonLogic() {
		if (resultTable.getValue() == null) {
			AbstractNotificationUtils.getErrorNotification(Constant.SELECT_RECORD1,
					NO_RECORD_WAS_SELECTED_PLEASE_TRY_AGAIN);
		} else {
			try {
				Map<String, String> tempCustomerDescriptionMap;
				Map<String, String> tempProductDescriptionMap;
				final DataSelectionDTO dto = (DataSelectionDTO) resultTable.getValue();
				int projectionIdValue = dto.getProjectionId();
				VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, projectionIdValue);
				customerFuture = checkAndDoAutomaticUpdate(dto.getCustomerRelationShipVersionNo(),
						Integer.parseInt(dto.getCustomerHierSid()));
				productFuture = checkAndDoAutomaticUpdate(dto.getProductRelationShipVersionNo(),
						Integer.parseInt(dto.getProdHierSid()));
				boolean isCustRelationUpdate = (boolean) customerFuture.get();
				boolean isProdRelationUpdate = (boolean) productFuture.get();
				if(isCustRelationUpdate || isProdRelationUpdate) {
					AbstractNotificationUtils.getInfoNotification("Info", "Relationship used in this projection is updated");
				}
				final SessionDTO tempSession = SessionUtil.createSession();
				tempSession.setScreenName(screenName);
				tempSession.setProjectionId(projectionIdValue);
				tempSession.setCustomerHierarchyVersion(dto.getCustomerHierVersionNo());
				tempSession.setProductHierarchyVersion(dto.getProductHierVersionNo());
				tempSession.setCustomerRelationVersion(dto.getCustomerRelationShipVersionNo());
				tempSession.setProductRelationVersion(dto.getProductRelationShipVersionNo());
				tempCustomerDescriptionMap = relationLogic.getLevelValueMap(dto.getCustRelationshipBuilderSid(),
						Integer.parseInt(dto.getCustomerHierSid()), dto.getCustomerHierVersionNo(),
						dto.getCustomerRelationShipVersionNo());
				tempProductDescriptionMap = relationLogic.getLevelValueMap(dto.getProdRelationshipBuilderSid(),
						Integer.parseInt(dto.getProdHierSid()), dto.getProductHierVersionNo(),
						dto.getProductRelationShipVersionNo());
				if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(screenName)
						|| CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)
						|| CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equalsIgnoreCase(screenName)) {
					// To create the temp tables with userId and session id
					QueryUtils.createTempTables(tempSession);
					nmLogic.loadPFDFromMainToTemp(tempSession);

					int customerSelectedLevel = Integer.parseInt(dto.getCustomerHierarchyInnerLevel());
					int productSelectedLeve = Integer.parseInt(dto.getProductHierarchyInnerLevel());
					relationLogic.checkAndUpdateHierarchy(dto);
					List<Leveldto> customerItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
							Boolean.TRUE, customerSelectedLevel, tempCustomerDescriptionMap);
					List<Leveldto> productItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
							Boolean.FALSE, productSelectedLeve, tempProductDescriptionMap);
					topLevelName = dsLogic.getTopLevelInHierarchy(dto.getCustomerHierSid());
					customerHierarchyLevelDefinitionList = relationLogic.getHierarchyLevelDefinition(
							Integer.parseInt(dto.getCustomerHierSid()), dto.getCustomerHierVersionNo());
					productHierarchyLevelDefinitionList = relationLogic.getHierarchyLevelDefinition(
							Integer.parseInt(dto.getProdHierSid()), dto.getProductHierVersionNo());
					if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(screenName)
							|| CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equalsIgnoreCase(screenName)) {

						relationLogic.ccpHierarchyInsert(tempSession.getCurrentTableNames(), customerItemIds,
								productItemIds, customerHierarchyLevelDefinitionList,
								productHierarchyLevelDefinitionList, dto.getCustomerRelationShipVersionNo(),
								dto.getProductRelationShipVersionNo());

					} else {
						HelperListUtil helperUtil = HelperListUtil.getInstance();
						String dedLevel = getDedutionLevel(dto.getDeductionLevel());
						String deductionValue = String
								.valueOf(helperUtil.getIdByDescription(dto.getDeductionValue(), dedLevel));
						relationLogic.ccpHierarchyInsertARP(tempSession.getCurrentTableNames(), customerItemIds,
								productItemIds, customerHierarchyLevelDefinitionList,
								productHierarchyLevelDefinitionList, dto.getProjectionId(), dedLevel, deductionValue,
								dto.getCustomerRelationShipVersionNo(), dto.getProductRelationShipVersionNo());
					}
				}
				tempSession.setProjectionId(projectionIdValue);
				tempSession.setAction(Constant.EDIT_SMALL);

				tempSession.setCustomerRelationId(Integer.valueOf(dto.getCustRelationshipBuilderSid()));
				tempSession.setProductRelationId(Integer.valueOf(dto.getProdRelationshipBuilderSid()));
				tempSession.setProdRelationshipBuilderSid(dto.getProdRelationshipBuilderSid());
				tempSession.setCustomerHierarchyId(Integer.valueOf(dto.getCustomerHierSid()));
				tempSession.setProductHierarchyId(Integer.valueOf(dto.getProdHierSid()));
				tempSession.setBusineesUnit(businessUnitlist);
				tempSession.setProjectionName(dto.getProjectionName());
				if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equalsIgnoreCase(screenName)) {
					String marketType = dataLogic.getHelperValue(StringUtils.EMPTY + projectionIdValue);
					tempSession.setMarketTypeValue(marketType);
				}
				tempSession.setDiscount(dto.getDiscount());
				tempSession.setDiscountTypeId(dto.getDiscountSid());
				tempSession.setProductLevelNumber(dto.getProductHierarchyLevel());
				tempSession.setDescription(dto.getDescription());
				if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
					// To create the temp tables with userId and session id
					QueryUtils.createTempTables(tempSession);
					tempSession.setProductDescription(tempProductDescriptionMap); // Fix
					// for
					// GAL-8786
					tempSession.setReturnsDetailsMap(dsLogic.getReturnDetails(tempSession, false));
				} else if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(screenName)
						|| CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equalsIgnoreCase(screenName)) {
					tempSession.setCustomerLevelDetails(
							dsLogic.getLevelValueDetails(tempSession, dto.getCustRelationshipBuilderSid(), true));
					tempSession.setProductLevelDetails(
							dsLogic.getLevelValueDetails(tempSession, dto.getProdRelationshipBuilderSid(), false));
					tempSession.setCustomerDescription(tempCustomerDescriptionMap);
					tempSession.setProductDescription(tempProductDescriptionMap);
				} else {
					tempSession.setCustomerDescription(tempCustomerDescriptionMap);
					tempSession.setProductDescription(tempProductDescriptionMap);
				}
				if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(screenName)
						|| CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equalsIgnoreCase(screenName)) {
					tempSession.setCustRelationshipBuilderSid(dto.getCustRelationshipBuilderSid());
					tempSession.setProdRelationshipBuilderSid(dto.getProdRelationshipBuilderSid());
					if (CommonUtil.isValueEligibleForLoading()) {
						Object[] obj = nmLogic.deductionRelationBuilderId(dto.getProdRelationshipBuilderSid());
						tempSession.setDedRelationshipBuilderSid(obj[0].toString());
					}
					if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(screenName)) {
						if (dataLogic.isFileChanged(tempSession) == 0) {
							MessageBox.showPlain(Icon.QUESTION, "New File is Activated in the File Management module",
									"There is a new file " + "[ " + tempSession.getFileName() + " ]\n"
											+ " that has been activated. \n"
											+ "Please re-calculate Sales and Discount projections to utilize the new values.",
									new MessageBoxListener() {
										@SuppressWarnings("PMD")
										public void buttonClicked(final ButtonId buttonId) {
											return;
										}
									}, ButtonId.OK);
						}
					}
					ForecastWindow forecastWindow = new ForecastWindow(dto.getProjectionName(), tempSession,
							resultTable, screenName, this, dto);
					UI.getCurrent().addWindow(forecastWindow);

				} else if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {

					ForecastEditWindow editWindow = new ForecastEditWindow(dto.getProjectionName(), tempSession,
							resultTable, screenName, this);
					UI.getCurrent().addWindow(editWindow);
				} else if (!editnotif) {
					editnotif = true;
					tempSession.setDeductionLevel(dto.getDeductionLevel());
					tempSession.setDeductionValue(dto.getDeductionValue());
					tempSession.setIsFileNotChanged(DSLogic.getFileStatus(projectionIdValue));
					if (!tempSession.isFileNotChanged()) {
						try {
							MessageBox.showPlain(Icon.QUESTION, "Alert",
									"A new Customer Gross Trade Sales file has been activated since this workflow was last saved. Would you like this workflow to be updated based on the new active file?",
									new MessageBoxListener() {

										@SuppressWarnings("PMD")
										public void buttonClicked(final ButtonId buttonId) {
											if (buttonId.name().equals(Constant.YES)) {
												tempSession.setIsNewFileCalculationNeeded(true);
												callARPView(dto, tempSession);
												editnotif = false;
											} else {
												tempSession.setIsNewFileCalculationNeeded(false);
												callARPView(dto, tempSession);
												editnotif = false;
											}
										}
									}, ButtonId.YES, ButtonId.NO);

						} catch (

						Exception e) {

							LOGGER.error(e);
						}
					} else {
						tempSession.setIsNewFileCalculationNeeded(false);
						callARPView(dto, tempSession);
					}
				}
			} catch (Exception ex) {
				LOGGER.error(ex + " - in editBtn");
			}
		}
	}

	public void callARPView(DataSelectionDTO dto, SessionDTO session) {
		DSLogic dSLogic = new DSLogic();

		if (session.getCurrentTableNames().isEmpty() && !session.getAction().equalsIgnoreCase(Constant.VIEW)) {
			session.setScreenName(screenName);
			QueryUtils.createTempTables(session);
		}
		if (!session.getAction().equalsIgnoreCase(Constant.VIEW)) {
			dSLogic.insertAccrualTemp(session);
		}
		// Used to maintain the thread count
		session.setNumberOfThreads(4);
		AccrualRateProjectionView arpView = new AccrualRateProjectionView(StringUtils.EMPTY + dto.getProjectionId(),
				session, screenName, this, false);
		getUI().getNavigator().addView(AccrualRateProjectionView.ARP_VIEW, arpView);
		getUI().getNavigator().navigateTo(AccrualRateProjectionView.ARP_VIEW);
	}

	/**
	 * View btn.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	protected void viewButtonLogic() {
		if (resultTable.getValue() == null) {
			AbstractNotificationUtils.getErrorNotification(Constant.SELECT_RECORD1,
					NO_RECORD_WAS_SELECTED_PLEASE_TRY_AGAIN);
		} else {
			try {
				DataSelectionDTO dto = (DataSelectionDTO) resultTable.getValue();
				int projectionIdValue = dto.getProjectionId();
				SessionDTO session = SessionUtil.createSession();
				session.setProjectionId(projectionIdValue);
				session.setBusineesUnit(businessUnitlist);
				session.setProjectionName(dto.getProjectionName());
				session.setScreenName(screenName);
				session.setAction(Constant.VIEW);
				session.setCustomerHierarchyVersion(dto.getCustomerHierVersionNo());
				session.setProductHierarchyVersion(dto.getProductHierVersionNo());
				session.setCustomerRelationVersion(dto.getCustomerRelationShipVersionNo());
				session.setProductRelationVersion(dto.getProductRelationShipVersionNo());
				customerDescriptionMap = relationLogic.getLevelValueMap(dto.getCustRelationshipBuilderSid(),
						Integer.parseInt(dto.getCustomerHierSid()), dto.getCustomerHierVersionNo(),
						dto.getCustomerRelationShipVersionNo());
				productDescriptionMap = relationLogic.getLevelValueMap(dto.getProdRelationshipBuilderSid(),
						Integer.parseInt(dto.getProdHierSid()), dto.getProductHierVersionNo(),
						dto.getProductRelationShipVersionNo());
				if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(screenName)
						|| CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equalsIgnoreCase(screenName)) {
					// To create the temp tables with userId and session id
					QueryUtils.createTempTables(session);
					topLevelName = dsLogic.getTopLevelInHierarchy(dto.getCustomerHierSid());
					int customerSelectedLevel = Integer.parseInt(dto.getCustomerHierarchyInnerLevel());
					int productSelectedLeve = Integer.parseInt(dto.getProductHierarchyInnerLevel());

					List<Leveldto> customerItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
							Boolean.TRUE, customerSelectedLevel, customerDescriptionMap);
					List<Leveldto> productItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
							Boolean.FALSE, productSelectedLeve, productDescriptionMap);

					customerHierarchyLevelDefinitionList = relationLogic.getHierarchyLevelDefinition(
							Integer.parseInt(dto.getCustomerHierSid()), dto.getCustomerHierVersionNo());
					productHierarchyLevelDefinitionList = relationLogic.getHierarchyLevelDefinition(
							Integer.parseInt(dto.getProdHierSid()), dto.getProductHierVersionNo());
					relationLogic.ccpHierarchyInsert(session.getCurrentTableNames(), customerItemIds, productItemIds,
							customerHierarchyLevelDefinitionList, productHierarchyLevelDefinitionList,
							dto.getCustomerRelationShipVersionNo(), dto.getProductRelationShipVersionNo());
				}
				DataSelectionLogic logic = new DataSelectionLogic();
				session.setProductRelationId(Integer.valueOf(dto.getProdRelationshipBuilderSid()));
				session.setProductLevelNumber(dto.getProductHierarchyLevel());

				if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
					session.setCustomerLevelDetails(
							logic.getLevelValueDetails(session, dto.getCustRelationshipBuilderSid(), true));
					session.setProductLevelDetails(
							logic.getLevelValueDetails(session, dto.getProdRelationshipBuilderSid(), false));
				}
				if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
					session.setProductDescription(productDescriptionMap); // Fix
					// for
					// GAL-8786
					session.setReturnsDetailsMap(logic.getReturnDetails(session, false));

				} else {
					// In back hand it was throwing Error because in returns
					// customer RelationShip we are not using
					session.setCustomerDescription(customerDescriptionMap);
					session.setProductDescription(productDescriptionMap);
				}
				if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(screenName)
						|| CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equalsIgnoreCase(screenName)) {
					session.setCustRelationshipBuilderSid(dto.getCustRelationshipBuilderSid());
					session.setProdRelationshipBuilderSid(dto.getProdRelationshipBuilderSid());
					if (CommonUtil.isValueEligibleForLoading()) {
						Object[] obj = nmLogic.deductionRelationBuilderId(dto.getProdRelationshipBuilderSid());
						session.setDedRelationshipBuilderSid(obj[0].toString());
					}
					ForecastWindow forecastWindow = new ForecastWindow(dto.getProjectionName(), session, resultTable,
							screenName, this, dto);
					UI.getCurrent().addWindow(forecastWindow);
				} else if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
					ForecastEditWindow editWindow = new ForecastEditWindow(dto.getProjectionName(), session,
							resultTable, screenName, this);
					UI.getCurrent().addWindow(editWindow);
				} else {
					session.setDeductionLevel(dto.getDeductionLevel());
					session.setDeductionValue(dto.getDeductionValue());
					session.setIsFileNotChanged(true);
					session.setIsNewFileCalculationNeeded(false);
					callARPView(dto, session);
				}

			} catch (Exception ex) {

				LOGGER.error(ex + " - in View button");
			}

		}
	}

	/**
	 * Delete btn.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	protected void deleteButtonLogic() {
		if (resultTable.getValue() == null) {
			AbstractNotificationUtils.getErrorNotification(Constant.SELECT_RECORD1,
					NO_RECORD_WAS_SELECTED_PLEASE_TRY_AGAIN);
			return;
		}

		final DataSelectionDTO projection = (DataSelectionDTO) resultTable.getValue();
		MessageBox.showPlain(Icon.QUESTION, "Confirm Deletion",
				"Are you sure you want to delete record " + projection.getProjectionName() + " ?",
				new MessageBoxListener() {

					@Override
					public void buttonClicked(ButtonId buttonId) {
						if (buttonId.name().equals(Constant.YES)) {
							DataSelectionLogic logic = new DataSelectionLogic();
							if (resultTable.getValue() != null) {
								try {
									String currentUserId = (String) VaadinSession.getCurrent()
											.getAttribute(Constant.USER_ID);
									String flag = logic.deleteProjection(projection.getProjectionId(), currentUserId,
											screenName);
									if (!Constant.FAIL.equals(flag)) {
										if ("accessDenined".equals(flag)) {
											NotificationUtils.getErrorNotification("Cannot Delete Record",
													"You do not have permission to delete this projection.  It can only be deleted by the creator.");
											return;
										}
										resultsContainer.removeItem(projection);
										projectionId.setValue(null);
										reloadResultsTable();
										CommonUIUtils.getMessageNotification(
												projection.getProjectionName() + " has been successfully deleted.");
									}
								} catch (Exception ex) {
									LOGGER.error(ex + " - in deleteBtn");
								}
							}
						}
					}
				}, ButtonId.YES, ButtonId.NO);

	}

	public void loadCustomerLevel(final String hierarchyId, final String innerLevel) {
		LOGGER.debug("Logging - loadCustomerLevel hierarchyId " + hierarchyId + "  innerLevel  " + innerLevel);
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			innerCustLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY);
			customerForecastLevelContainer.removeAllItems();
			int levelNo = UiUtils.parseStringToInteger(innerLevel);
			String selectedLevelName = innerCustLevels.get(levelNo - 1).getLevel();
			for (int i = 1; i <= innerCustLevels.size(); i++) {
				String levelName = innerCustLevels.get(i - 1).getLevel();
				levelCheck(levelName);
				customerForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
			}
			customerLevel.setContainerDataSource(customerForecastLevelContainer);
			customerLevel.select(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);
			setSelectedCustomerLevel(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);

		} catch (Exception ex) {
			LOGGER.error(ex + " in loadCustomerLevel");
		}

	}

	public void loadProductLevel(final String hierarchyId, final String innerLevel) {
		LOGGER.debug("Logging - loadProductLevel hierarchyId " + hierarchyId + "  innerLevel " + innerLevel);
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			innerProdLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY);
			int levelNo = UiUtils.parseStringToInteger(innerLevel);
			String selectedLevelName = innerProdLevels.get(levelNo - 1).getLevel();
			productForecastLevelContainer.removeAllItems();
			for (int i = 1; i <= innerProdLevels.size(); i++) {
				String levelName = innerProdLevels.get(i - 1).getLevel();
				productForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
			}
			productLevel.setContainerDataSource(productForecastLevelContainer);
			productLevel.select(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);
			setSelectedProductLevel(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);

		} catch (Exception ex) {
			LOGGER.error(ex + " loadProductLevel");
		}
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

	public List<Leveldto> getProductHierarchyEndLevels(final ExtTreeContainer<Leveldto> selectedProductContainer) {
		List<Leveldto> productHierarchyEndLevels = new ArrayList<>();
		for (Object item : selectedProductContainer.getItemIds()) {
			if (!selectedProductContainer.hasChildren(item)) {
				productHierarchyEndLevels.add(DataSelectionUtil.getBeanFromId(item));
			}
		}
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

	private List<String> getRelationshipSid(List<Leveldto> leveldtos) {
		List<String> relationshipSids = new ArrayList<>();
		for (Leveldto dto : leveldtos) {
			relationshipSids.add(String.valueOf(dto.getRelationshipLevelSid()));
		}
		return relationshipSids;
	}

	/**
	 * Load customer hierarchy.
	 *
	 * @param projectionId
	 * @throws java.lang.Exception
	 */

	public void initializeCustomerHierarchy(final int projectionId, String customerLevel) {
		int forecastLevel = 0;
		forecastLevel = UiUtils.parseStringToInteger(customerLevel);
		List<Leveldto> reslistOne;
		reslistOne = relationLogic.getRelationShipValues(projectionId, Boolean.TRUE, customerLevel,
				customerDescriptionMap);
		for (Leveldto dto : reslistOne) {
			if (dto.getLevelNo() == 1) {
				selectedCustomerContainer.removeAllItems();
				selectedCustomerContainer.addItem(dto);
				selectedCustomerContainer.setChildrenAllowed(dto, true);
			} else {
				for (Object tempdto : selectedCustomerContainer.getItemIds()) {
					if (dto.getParentNode().contains("~")) {
						String[] parentarr = dto.getParentNode().split("~");
						String parentName = parentarr[1];
						int parentLevel = 0;
						try {
							parentLevel = Integer.valueOf(parentarr[0]);
						} catch (NumberFormatException nfe) {
							LOGGER.error("Error While loading the Customer level." + parentarr[0]
									+ " is not a valid number");
						}
						Leveldto levelDto = DataSelectionUtil.getBeanFromId(tempdto);
						if (levelDto.getLevelNo() == parentLevel
								&& levelDto.getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
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
		selectedCustomer.setVisibleColumns(new Object[] { Constant.DISPLAY_VALUE });
		selectedCustomer.setColumnHeaders(new String[] { "Customer Hierarchy Group Builder" });
		for (Leveldto ddo : selectedCustomerContainer.getItemIds()) {
			selectedCustomer.setCollapsed(ddo, false);
		}
	}

	/**
	 * Load customer hierarchy.
	 *
	 * @param projectionId
	 * @throws java.lang.Exception
	 */
	public void initializeProductHierarchy(final int projectionId, String productLevel) {
		int forecastLevel = 0;
		forecastLevel = UiUtils.parseStringToInteger(productLevel);
		List<Leveldto> reslistOne;
		reslistOne = relationLogic.getRelationShipValues(projectionId, Boolean.FALSE, productLevel,
				productDescriptionMap);
		for (Leveldto dto : reslistOne) {
			if (dto.getLevelNo() == 1) {
				selectedProductContainer.removeAllItems();
				selectedProductContainer.addItem(dto);
				selectedProductContainer.setChildrenAllowed(dto, true);
			} else {
				for (Object tempdto : selectedProductContainer.getItemIds()) {
					if (dto.getParentNode().contains("~")) {
						String[] parentarr = dto.getParentNode().split("~");
						String parentName = parentarr[1];
						int parentLevel = 0;
						try {
							parentLevel = Integer.valueOf(parentarr[0]);
						} catch (NumberFormatException nfe) {
							LOGGER.error(
									"Error While loading the Product level." + parentarr[0] + " is not a valid number");
						}
						Leveldto levelDto = DataSelectionUtil.getBeanFromId(tempdto);
						if (levelDto.getLevelNo() == parentLevel
								&& levelDto.getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
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
		selectedProduct.setVisibleColumns(Constant.DISPLAY_VALUE);
		selectedProduct.setColumnHeaders("Product Hierarchy Group Builder");
		for (Leveldto ddo : selectedProductContainer.getItemIds()) {
			selectedProduct.setCollapsed(ddo, false);
		}
	}

	private void setRelationshipBuilderSids(String rbSid) {
		relationshipBuilderSids.add(rbSid);
	}

	private void loadInnerCustomerLevel(int forecastLevel, int innerLevel, int hierarchyId) {
		LOGGER.debug("Logging - loadInnerCustomerLevel forecastLevel " + forecastLevel + " innerLevel " + innerLevel
				+ " hierarchyId " + hierarchyId);
		customerInnerLevelContainer.removeAllItems();
		String selectedLevelName = StringUtils.EMPTY;
		for (int i = 1; i <= forecastLevel; i++) {
			String levelName = innerCustLevels.get(i - 1).getLevel();
			customerInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
			if (i == innerLevel) {
				selectedLevelName = levelName;
			}
		}

		level.setContainerDataSource(customerInnerLevelContainer);
		level.select(Constant.LEVEL + innerLevel + " - " + selectedLevelName);
	}

	private void loadInnerProductLevel(int forecastLevel, int innerLevel, int hierarchyId) {
		LOGGER.debug("Logging - loadInnerProductLevel forecastLevel " + forecastLevel + " innerLevel " + innerLevel
				+ " hierarchyId " + hierarchyId);
		productInnerLevelContainer.removeAllItems();
		String selectedLevelName = StringUtils.EMPTY;
		for (int i = 1; i <= forecastLevel; i++) {
			String levelName = innerProdLevels.get(i - 1).getLevel();
			productInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
			if (i == innerLevel) {
				selectedLevelName = levelName;
			}
		}
		productlevelDdlb.setContainerDataSource(productInnerLevelContainer);
		productlevelDdlb.select(Constant.LEVEL + innerLevel + " - " + selectedLevelName);
	}

	@Override
	public String getSelectedCustomerLevel() {
		return selectedCustomerLevel;
	}

	@Override
	public void setSelectedCustomerLevel(String selectedCustomerLevel) {
		this.selectedCustomerLevel = selectedCustomerLevel;
	}

	@Override
	public String getSelectedProductLevel() {
		return selectedProductLevel;
	}

	@Override
	public void setSelectedProductLevel(String selectedProductLevel) {
		this.selectedProductLevel = selectedProductLevel;
	}

	/**
	 * Manual trigger and processing of customer group select button logic Code
	 * based on customer group select button logic Any change made there, should
	 * be made here accordingly
	 *
	 * @param customerGrpSid
	 */
	public void triggerCustGrpOnView(String customerGrpSid, final boolean filter) {

		List<String> customerSidsFromDetails = null;
		DataSelectionLogic logic = new DataSelectionLogic();
		try {
			if (!StringUtils.isBlank(customerGrpSid) && !NULL.equalsIgnoreCase(customerGrpSid)) {
				customerSidsFromDetails = logic.getCustomerGroupDetails(Integer.parseInt(customerGrpSid));
				groupFilteredCompanies = new ArrayList<>(customerSidsFromDetails);
				if (filter) {
					levelValueChangeListener(level.getValue());
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
					groupFilteredItems = new ArrayList<>(finalItemSids);
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

	private List<Leveldto> getItemSidFromHierarchy() {
		List<Leveldto> innerLevelValues = null;
		String dedLevel = StringUtils.EMPTY;
		String dedValue = StringUtils.EMPTY;
		if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
			dedLevel = Constant.DEDUCTION_PROGRAM_TYPE1
					.equalsIgnoreCase(String.valueOf(deductionLevel.getValue()))
							? Constant.REBATE_PROGRAM_TYPE1
							: Constant.DEDUCTION_CATEGORY1.equalsIgnoreCase(String.valueOf(deductionLevel.getValue()))
									? Constant.RS_CATEGORY1
									: Constant.DEDUCTION_SCHEDULE_TYPE1
											.equalsIgnoreCase(String.valueOf(deductionLevel.getValue()))
													? Constant.RS_TYPE : StringUtils.EMPTY;
			dedValue = String.valueOf(deductionValue.getValue());
		}

		try {

			if ((productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue()))
					&& (innerProdLevels != null)) {

				String relationshipSid = String.valueOf(productRelation.getValue());
				DataSelectionLogic logic = new DataSelectionLogic();
				Leveldto ndcLevel = null;
				for (Leveldto dto : innerProdLevels) {
					if (Constant.NDC.equalsIgnoreCase(dto.getLevel()) || "Item".equalsIgnoreCase(dto.getLevel())) {
						ndcLevel = dto;
						break;
					}
				}
				if (ndcLevel != null) {

					innerLevelValues = logic.loadInnerLevel(ndcLevel.getLevel(), productHierarchyDto.getHierarchyId(),
							DataSelectionUtil.getSelectedRelationshipLevelSids(selectedProductContainer.getItemIds()),
							true, ndcLevel.getFieldName(), relationshipSid, productDescriptionMap,
							INDICATOR_LEVEL_NDC.getConstant(), screenName,
							discountDTO != null ? discountDTO.getRsModelSid() : 0, ndcLevel.getLevelNo(), dedValue,
							dedLevel, company.getValue(), businessUnit.getValue());
				}
			}
		} catch (Exception ex) {
			LOGGER.error(ex + " in getItemSidFromHierarchy");
		}
		return innerLevelValues;
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
			relationship.setPageLength(7);
			relationship.setImmediate(true);
			relationship.setNullSelectionAllowed(true);
			relationship.setInputPrompt(SELECT_ONE);

		} catch (Exception ex) {
			LOGGER.error(ex);
		}
	}

	@Override
	protected void productLevelDdlbValueChange(String selectedLevel, boolean flag) {
		loadFilteredProductSelection(selectedLevel);
	}

	@Override
	protected void customerHierarchyLookUp() {
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
					DataSelectionLogic logic = new DataSelectionLogic();
					innerCustLevels = logic.loadCustomerForecastLevel(lookupDto.getHierarchyId(),
							lookupDto.getHierarchyName());
					loadRelationDdlb(customerHierarchyDto.getHierarchyId(), null, customerRelationComboBox);
					customerForecastLevelContainer.removeAllItems();
					for (int i = 1; i <= innerCustLevels.size(); i++) {
						String levelName = innerCustLevels.get(i - 1).getLevel();
						levelCheck(levelName);
						customerForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
					}
					resetCustomerLevel();
					resetSecondCustomerLevel();
					availableCustomer.removeAllItems();
					availableCustomerContainer.removeAllItems();
					selectedCustomer.removeAllItems();
					selectedCustomerContainer.removeAllItems();
					customerGroup.setValue(StringUtils.EMPTY);
					dataSelectionDTO.setCustomerGrpSid(null);
					groupFilteredCompanies = null;
					customerLevel.setContainerDataSource(customerForecastLevelContainer);
					if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
						level.setContainerDataSource(customerForecastLevelContainer);
					}
					setCustomerForecastLevelNullSelection();
					setCustomerRelationNullSelection();
					dataSelectionDTO.setCustomerHierSid(String.valueOf(customerHierarchyDto.getHierarchyId()));
				}
			}
		});
	}

	@Override
	protected void productHierarchyLookUp() {
		final HierarchyLookup productHierarchyLookupWindow = new HierarchyLookup(
				INDICATOR_PRODUCT_HIERARCHY.getConstant(), WINDOW_PRODUCT_HIERARCHY_LOOKUP.getConstant(),
				productHierarchy, productHierarchyDto);
		UI.getCurrent().addWindow(productHierarchyLookupWindow);
		productHierarchyLookupWindow.addCloseListener(new Window.CloseListener() {

			@Override
			public void windowClose(Window.CloseEvent e) {
				if (productHierarchyLookupWindow.getHierarchyDto() != null) {
					final HierarchyLookupDTO lookupDto = productHierarchyLookupWindow.getHierarchyDto();
					DataSelectionLogic logic = new DataSelectionLogic();
					productHierarchyDto = lookupDto;
					innerProdLevels = logic.loadCustomerForecastLevel(lookupDto.getHierarchyId(),
							lookupDto.getHierarchyName());
					productForecastLevelContainer.removeAllItems();
					for (int i = 1; i <= innerProdLevels.size(); i++) {
						String levelName = innerProdLevels.get(i - 1).getLevel();
						productForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
					}
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
					productLevel.setContainerDataSource(productForecastLevelContainer);
					if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
						productlevelDdlb.setContainerDataSource(productForecastLevelContainer);
					}
				}
			}
		});
	}

	@Override
	protected void modeOptionChange(boolean isAddMode) {
		try {
			if (isAddMode) {
				DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(),
						screenName);
			} else {
				DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_SEARCH.getConstant(),
						screenName);
			}

		} catch (Exception ex) {
			LOGGER.error(ex);
		}
	}

	@Override
	protected void customerLevelValueChange(Property.ValueChangeEvent event, boolean flag) {
		LOGGER.debug("Logging - customerLevelValueChange");
		customerInnerLevelContainer.removeAllItems();
		if (event.getProperty().getValue() != null
				&& !SELECT_ONE.equals(String.valueOf(event.getProperty().getValue()))) {
			String selectedLevel = String.valueOf(event.getProperty().getValue());
			setSelectedCustomerLevel(selectedLevel);

			String val[] = selectedLevel.split(" ");
			int forecastLevel = Integer.parseInt(val[1]);
			customerInnerLevelContainer.removeAllItems();
			selectedCustomer.removeAllItems();
			selectedCustomerContainer.removeAllItems();
			customerBeanList.clear();
			for (int i = 1; i <= forecastLevel; i++) {
				String levelName = innerCustLevels.get(i - 1).getLevel();
				customerInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
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

	protected void levelValueChangeListener(Object value)
			throws ClassNotFoundException, CloneNotSupportedException, IOException {

		LOGGER.debug("customer inner Level - ValueChangeListener  " + value);
		availableCustomerContainer.removeAllItems();
		String levelName = Constant.LEVEL_LABEL;
		try {
			customerFuture.get();
			int forecastLevel = 0;
			if (value != null && customerRelationComboBox.getValue() != null
					&& !SELECT_ONE.equals(customerRelationComboBox.getValue())) {
				int relationVersionNo = Integer.parseInt(
						customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
				int hierarchyVersionNo = Integer.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));

				String dedLevel = StringUtils.EMPTY;
				String dedValue = StringUtils.EMPTY;
				if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)
						&& !(deductionValue.getValue() instanceof String)) {
					{
						dedLevel = getDedutionLevel(String.valueOf(deductionLevel.getValue()));
						dedValue = deductionValue.getValue() == null ? StringUtils.EMPTY
								: String.valueOf(((HelperDTO) deductionValue.getValue()).getId());
					}
				}

				customerDescriptionMap = relationLogic.getLevelValueMap(
						String.valueOf(customerRelationComboBox.getValue()), customerHierarchyDto.getHierarchyId(),
						hierarchyVersionNo, relationVersionNo);
				dataSelectionDTO.setCustomerRelationShipVersionNo(relationVersionNo);
				String selectedLevel = String.valueOf(value);
				String relationshipSid = String.valueOf(customerRelationComboBox.getValue());
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
						Integer.valueOf(relationshipSid), tempGroupFileter, levelHierarchyLevelDefinitionList, dedLevel,
						dedValue, relationVersionNo);
				if (selectedHierarchyLevelDto.getLevel() != null) {
					levelName = selectedHierarchyLevelDto.getLevel();
				}
				availableCustomerContainer.addAll(resultedLevelsList);
				availableCustomer.setContainerDataSource(availableCustomerContainer);
				availableCustomer.setVisibleColumns(Constant.DISPLAY_VALUE);
				availableCustomer.setColumnHeaders(levelName);
				availableCustomer.setFilterBarVisible(true);
				availableCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
				availableCustomer.setStyleName(Constant.FILTER_TABLE);
			}
		} catch (Exception ex) {

			LOGGER.error(ex + " level  ValueChangeListener ");
		}
	}

	private String getDedutionLevel(String deductionLevel) {
		switch (deductionLevel) {
		case Constant.DEDUCTION_PROGRAM_TYPE1:
			return Constant.REBATE_PROGRAM_TYPE1;
		case Constant.DEDUCTION_CATEGORY1:
			return Constant.RS_CATEGORY1;
		case Constant.DEDUCTION_SCHEDULE_TYPE1:
			return Constant.RS_TYPE;
		default:
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected void productLevelValueChange(Object value, boolean flag) {
		LOGGER.debug("Logging - productLevelValueChange");
		productInnerLevelContainer.removeAllItems();
		if (value != null && !SELECT_ONE.equals(String.valueOf(value))) {
			String selectedLevel = String.valueOf(value);
			setSelectedProductLevel(selectedLevel);

			String[] val = selectedLevel.split(" ");
			int forecastLevel = Integer.parseInt(val[1]);
			productInnerLevelContainer.removeAllItems();
			selectedProduct.removeAllItems();
			selectedProductContainer.removeAllItems();
			productBeanList.clear();
			for (int i = 1; i <= forecastLevel; i++) {
				String levelName = innerProdLevels.get(i - 1).getLevel();
				productInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
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

		LOGGER.debug("customerRelationValueChange" + value);

		if (value != null && !SELECT_ONE.equals(String.valueOf(value))) {
			try {
				availableCustomer.removeAllItems();
				availableCustomerContainer.removeAllItems();
				selectedCustomer.removeAllItems();
				selectedCustomerContainer.removeAllItems();
				setCustomerForecastLevelNullSelection();
				setCustomerLevelNullSelection();
				setRelationshipBuilderSids(String.valueOf(customerRelationComboBox.getValue()));
				customerFuture = checkAndDoAutomaticUpdate(customerRelationComboBox.getValue(),
						customerHierarchyDto.getHierarchyId());
				if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)
						&& !innerCustLevels.isEmpty()) {
					customerForecastLevelContainer.removeAllItems();
					for (int i = 1; i <= innerCustLevels.size(); i++) {
						String levelName = innerCustLevels.get(i - 1).getLevel();
						customerForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
					}
					level.setContainerDataSource(customerForecastLevelContainer);
				}

			} catch (Exception ex) {

				LOGGER.error(ex + " in customerRelation value change");
			}
		} else if (value == null || SELECT_ONE.equals(String.valueOf(value))) {
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

	private Future checkAndDoAutomaticUpdate(Object value, int hierarchyId) {
		GtnAutomaticRelationServiceRunnable wsClientRunnableTarget = new GtnAutomaticRelationServiceRunnable(value,
				hierarchyId);
		ExecutorService customerExecutorService = Executors.newSingleThreadExecutor();
		Future future = customerExecutorService.submit(wsClientRunnableTarget);
		customerExecutorService.shutdown();
		return future;
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

				productFuture = checkAndDoAutomaticUpdate(productRelation.getValue(),
						productHierarchyDto.getHierarchyId());

				if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)
						&& !innerProdLevels.isEmpty()) {
					productForecastLevelContainer.removeAllItems();
					for (int i = 1; i <= innerProdLevels.size(); i++) {
						String levelName = innerProdLevels.get(i - 1).getLevel();
						productForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
					}
					productlevelDdlb.setContainerDataSource(productForecastLevelContainer);
				}

			} catch (Exception ex) {
				LOGGER.error(ex + " in productRelation value change");
			}
		} else if ((value == null || SELECT_ONE.equals(String.valueOf(value)))) {
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
		LOGGER.debug("generateBtn click listener started " + screenName);

		if (dsLogic.checkForActiveFiles(businessUnit.getValue(), company.getValue())) {
			setPrivateViewName(privateView.getValue());
			setPublicViewName(publicView.getValue());

			if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
				try {
					if (selectedCustomer.size() <= 0 || selectedProduct.size() <= 0 || fromPeriod.getValue() == null
							|| StringUtils.isBlank(projectionName.getValue())
							|| Constants.CommonConstants.NULL.getConstant().equalsIgnoreCase(projectionName.getValue())
							|| company.getValue() == null || SELECT_ONE.equals(company.getValue())
							|| (Integer) company.getValue() == 0 || (Integer) businessUnit.getValue() == 0) {
						AbstractNotificationUtils.getErrorNotification("Selection Criteria",
								Constant.NOT_ALL_REQUIRED_FIELDS_POPULATED);
						return;
					}

					bindDataselectionDtoToSave();
					int projectionIdValue = nmLogic.saveProjection(dataSelectionDTO, screenName);
					VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, projectionIdValue);
					projectionId.setValue(String.valueOf(projectionIdValue));
					dataSelectionDTO.setProjectionId(projectionIdValue);
					relationshipBuilderSids.clear();
					setRelationshipBuilderSids(String.valueOf(customerRelationComboBox.getValue()));
					setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));

					dataSelectionDTO
							.setSelectedCustomerRelationSid(getRelationshipSid(selectedCustomerContainer.getItemIds()));
					dataSelectionDTO
							.setSelectedProductRelationSid(getRelationshipSid(selectedProductContainer.getItemIds()));

					if (projectionIdValue != 0) {
						SessionDTO session = SessionUtil.createSession();
						session.setGenerateFlag(true);
						session.setProjectionId(projectionIdValue);
						session.setBusineesUnit(businessUnitlist);
						session.setAction(Constant.ADD_FULL_SMALL);
						session.setCustomerDescription(customerDescriptionMap);
						session.setProductDescription(productDescriptionMap);
						session.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
						session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
						session.setCustomerHierarchyVersion(
								dataSelectionDTO.getCustomerHierVersionNo());
						session.setProductHierarchyVersion(dataSelectionDTO.getProductHierVersionNo());
						session.setCustomerRelationVersion(dataSelectionDTO.getCustomerRelationShipVersionNo());
						session.setProductRelationVersion(dataSelectionDTO.getProductRelationShipVersionNo());
						if (CommonUtil.isValueEligibleForLoading()) {
							Object[] obj = nmLogic
									.deductionRelationBuilderId(dataSelectionDTO.getProdRelationshipBuilderSid());
							session.setDedRelationshipBuilderSid(obj[0].toString());
						}
						session.setScreenName(screenName);
						session.setProjectionName(dataSelectionDTO.getProjectionName());
						// To create the temp tables with userId and session id
						QueryUtils.createTempTables(session);
						int hierarchyVersionNo = Integer
								.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
						customerHierarchyLevelDefinitionList = relationLogic
								.getHierarchyLevelDefinition(customerHierarchyDto.getHierarchyId(), hierarchyVersionNo);
						int customerRelationVersionNo = Integer.parseInt(customerRelationVersionComboBox
								.getItemCaption(customerRelationVersionComboBox.getValue()));
						int productRelationVersionNo = Integer.parseInt(productRelationVersionComboBox
								.getItemCaption(productRelationVersionComboBox.getValue()));
						relationLogic.ccpHierarchyInsert(session.getCurrentTableNames(),
								selectedCustomerContainer.getItemIds(), selectedProductContainer.getItemIds(),
								customerHierarchyLevelDefinitionList, productHierarchyLevelDefinitionList,
								customerRelationVersionNo, productRelationVersionNo);

						session.setCustomerLevelDetails(
								dsLogic.getLevelValueDetails(session, customerRelationComboBox.getValue(), true));
						session.setProductLevelDetails(
								dsLogic.getLevelValueDetails(session, productRelation.getValue(), false));
						dsLogic.loadProjectionFileDetailsTabInGenerate(session);
						ForecastWindow forecastWindow = new ForecastWindow(projectionName.getValue(), session,
								resultTable, screenName, this, dataSelectionDTO);
						UI.getCurrent().addWindow(forecastWindow);
						session.setGenerateFlag(false);
					}
					resetOne();
					resetTwo();
					LOGGER.debug("generateBtn click listener ends  ");
				} catch (Exception e) {

					LOGGER.error(e + " generateBtn click listener ");
				}
				UI.getCurrent().setFocusedComponent(UI.getCurrent());

			} else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
				generateBtn();
			} else if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
				generateLogicForReturns();
			} else if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
				generateLogicForARP();
			}
		} else if (businessUnit.getValue() == null) {
			AbstractNotificationUtils.getErrorNotification(Constant.SELECTION_CRITERIA_HEADER,
					Constant.NOT_ALL_REQUIRED_FIELDS_POPULATED);
		} else {
			AbstractNotificationUtils.getErrorNotification("File Not available",
					"No active file available for the selected Business Unit");
		}
	}

	public void generateBtn() {

		String marketType = StringUtils.EMPTY;
		String marketTypeHierarchyNo = StringUtils.EMPTY;
		int countMarkType = 0;
		int relationId = 0;
		int countSegment = 0;
		try {
			LOGGER.debug("generateBtn click listener started ");

			if (selectedCustomer.size() <= 0 || selectedProduct.size() <= 0 || fromPeriod.getValue() == null
					|| StringUtils.isBlank(projectionName.getValue())
					|| Constant.NULL.equalsIgnoreCase(projectionName.getValue())) {
				AbstractNotificationUtils.getErrorNotification(Constant.SELECTION_CRITERIA_HEADER,
						Constant.NOT_ALL_REQUIRED_FIELDS_POPULATED);
				return;
			}
			List<Leveldto> customerList = selectedCustomerContainer.getItemIds();
			for (Leveldto dto : customerList) {
				if (dto.getLevel().equals("Market Type")) {
					marketTypeHierarchyNo = dto.getHierarchyNo();
					countMarkType += 1;
				}
				if (customerLevel.getValue().equals(Constant.LEVEL + 1 + " - " + Constant.SEGMENT_LABEL)) {
					relationId = Integer.valueOf(dto.getRelationShipBuilderId());
				}

			}
			if (customerLevel.getValue().equals(Constant.LEVEL + 1 + " - " + Constant.SEGMENT_LABEL)) {
				countSegment = dsLogic.getGenerateMarketValueResult(relationId).size();
			}
			bindDataselectionDtoToSave();

			if (countMarkType > 1
					|| (customerLevel.getValue().equals(Constant.LEVEL + 1 + " - " + Constant.SEGMENT_LABEL)
							&& countSegment >= 2)) {
				AbstractNotificationUtils.getErrorNotification("More than one Market Type Selected",
						"The projection can be created for only one Market Type.  Please select only one Market Type and try again.");
				return;
			}
			int projectionIdValue = nmLogic.saveProjection(dataSelectionDTO, screenName);
			VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, projectionIdValue);
			projectionName.setValue(String.valueOf(projectionName.getValue()));
			dataSelectionDTO.setProjectionId(projectionIdValue);
			relationshipBuilderSids.clear();
			setRelationshipBuilderSids(String.valueOf(customerRelationComboBox.getValue()));
			setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));
			dataSelectionDTO.setSelectedCustomerRelationSid(getRelationshipSid(selectedCustomerContainer.getItemIds()));
			dataSelectionDTO.setSelectedProductRelationSid(getRelationshipSid(selectedProductContainer.getItemIds()));
			if (projectionIdValue != 0) {
				final SessionDTO session = SessionUtil.createSession();
				session.setProjectionId(projectionIdValue);
				session.setProjectionName(dataSelectionDTO.getProjectionName());
				session.setProductLevelNumber(String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
				session.setCustomerLevelNumber(String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
				session.setAction(Constant.ADD_FULL_SMALL);
				session.setBusineesUnit(businessUnitlist);
				session.setFromDate(dataSelectionDTO.getFromDate());
				session.setToDate(dataSelectionDTO.getToDate());
				session.setFromPeriod(String.valueOf(fromPeriod.getValue()));
				session.setToPeriod(String.valueOf(toPeriod.getValue()));
				session.setCustomerHierarchyId(Integer.valueOf(dataSelectionDTO.getCustomerHierSid()));
				session.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
				session.setCustomerRelationId(Integer.valueOf(dataSelectionDTO.getCustRelationshipBuilderSid()));
				session.setProductRelationId(Integer.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
				String definedOrUDValue = StringUtils.EMPTY;
				String definedValue = dsLogic.getDefinedValue(dataSelectionDTO.getCustomerHierSid());
				session.setCustomerHierarchyId(Integer.valueOf(dataSelectionDTO.getCustomerHierSid()));
				session.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
				session.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
				session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
				session.setCustomerDescription(customerDescriptionMap);
				session.setProductDescription(productDescriptionMap);
				session.setScreenName(screenName);
				// To create the temp tables with userId and session id
				QueryUtils.createTempTables(session);
				int customerRelationVersionNo = Integer.parseInt(
						customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
				int productRelationVersionNo = Integer.parseInt(
						productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
				relationLogic.ccpHierarchyInsert(session.getCurrentTableNames(), selectedCustomerContainer.getItemIds(),
						selectedProductContainer.getItemIds(), customerHierarchyLevelDefinitionList,
						productHierarchyLevelDefinitionList, customerRelationVersionNo, productRelationVersionNo);
				session.setCustomerLevelDetails(
						dsLogic.getLevelValueDetails(session, customerRelationComboBox.getValue(), true));
				session.setProductLevelDetails(
						dsLogic.getLevelValueDetails(session, productRelation.getValue(), false));
				if ("User Defined".equalsIgnoreCase(definedValue)) {
					if (!StringUtils.EMPTY.equals(dataSelectionDTO.getCustRelationshipBuilderSid())
							&& !DASH.equals(dataSelectionDTO.getCustRelationshipBuilderSid())) {
						marketType = dsLogic.getGenerateMarketValue(
								Integer.valueOf(dataSelectionDTO.getCustRelationshipBuilderSid()));
					}
					definedOrUDValue = marketType;
				} else if ("Market Type"
						.equals(session.getHierarchyLevelDetails().get(marketTypeHierarchyNo).get(1).toString())) {
					definedOrUDValue = session.getHierarchyLevelDetails().get(marketTypeHierarchyNo).get(0).toString();
				}
				session.setMarketTypeValue(definedOrUDValue);
				ForecastWindow forecastWindow = new ForecastWindow(projectionName.getValue(), session, resultTable,
						screenName, this, dataSelectionDTO);
				UI.getCurrent().addWindow(forecastWindow);
			}
			resetOne();
			resetTwo();

			LOGGER.debug("generateBtn click listener ends ");

		} catch (Exception e) {
			LOGGER.error(e);
		}
		UI.getCurrent().setFocusedComponent(UI.getCurrent());
	}

	@Override
	protected void configureTimeDdlb(ComboBox fromPeriod, ComboBox toPeriod, Object object, Object object0,
			String constant, String screenName) {
		try {

			DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(), screenName);
		} catch (Exception ex) {

			LOGGER.error(ex);
		}
	}

	@Override
	protected void loadPublicView() {
		final PrivatePublicView publicViewLookup = new PrivatePublicView(INDICATOR_PUBLIC_VIEW.getConstant(),
				PUBLIC_VIEW.getConstant(), screenName);
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
				PRIVATE_VIEW.getConstant(), screenName);
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

					} catch (Exception ex) {
						LOGGER.error(ex + " privateView close");
					}
				}

			}
		});
	}

	@Override
	protected void loadProductGroup() {
		final CustomerProductGroupLookup productGroupLookupWindow = new CustomerProductGroupLookup(
				INDICATOR_PRODUCT_GROUP.getConstant(), WINDOW_PRODUCT_GROUP_LOOKUP.getConstant(), productGroup,
				screenName);
		productGroupLookupWindow.init();
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
	protected void loadCustomerGroup() {
		final CustomerProductGroupLookup customerGroupLookupWindow = new CustomerProductGroupLookup(
				INDICATOR_CUSTOMER_GROUP.getConstant(), WINDOW_CUSTOMER_GROUP_LOOKUP.getConstant(), customerGroup,
				Collections.<String>emptyList(), screenName);
		customerGroupLookupWindow.init();
		UI.getCurrent().addWindow(customerGroupLookupWindow);
		customerGroupLookupWindow.addCloseListener(new Window.CloseListener() {

			@Override
			public void windowClose(Window.CloseEvent e) {
				if (customerGroupLookupWindow.getSelectedCustHierarchy() != null) {
					selectedCustomerGroupDTO = customerGroupLookupWindow.getSelectedCustHierarchy();
					groupFilteredCompanies = customerGroupLookupWindow.getFilteredSids();
					try {
						levelValueChangeListener(level.getValue());
					} catch (ClassNotFoundException | CloneNotSupportedException | IOException ex) {
						LOGGER.error(ex + " loadCustomerGroup");
					}
				}
			}
		});
	}

	@Override
	protected void saveViewButtonlogic() {
		LOGGER.debug("Entering saveViewBtn method");
		if ((StringUtils.isEmpty(projectionName.getValue()) || Constant.NULL.equals(projectionName.getValue()))
				&& (StringUtils.isEmpty(description.getValue()) || Constant.NULL.equals(description.getValue()))) {
			AbstractNotificationUtils.getErrorNotification(
					Constants.MessageConstants.NO_SEARCH_CRITERIA_TITLE.getConstant(),
					"No search criteria were found. Please enter search criteria and try again.");
		} else {
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
				if (privateView.getValue() != null && privateView.getValue() != StringUtils.EMPTY) {
					dataSelectionDTO.setViewType("private");
				} else if (publicView.getValue() != null) {
					dataSelectionDTO.setViewType("public");
				} else {
					dataSelectionDTO.setViewType(StringUtils.EMPTY);
				}
				dataSelectionDTO.setDeductionLevel(String.valueOf(deductionLevel.getValue()));

				dataSelectionDTO.setDeductionValue(deductionValue.getValue() == null || deductionValue.getValue() == ""
						? "" : String.valueOf(((HelperDTO) deductionValue.getValue()).getId()));
				dataSelectionDTO
						.setDeductionValueId(deductionValue.getValue() == null || deductionValue.getValue() == "" ? 0
								: ((HelperDTO) deductionValue.getValue()).getId());

				List<String> customerListEndSids = DataSelectionUtil
						.getEndLevelHierNo(getCustomerHierarchyEndLevels(selectedCustomerContainer));
				List<String> productListEndSids = DataSelectionUtil
						.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));
				final SaveViewPopup saveViewPopup = new SaveViewPopup(SAVE_VIEW.getConstant(), dataSelectionDTO,
						selectedCustomersList, selectedProductsList,
						getCustomerHierarchyEndLevels(selectedCustomerContainer),
						getProductHierarchyEndLevelsHierNo(selectedProductContainer), viewDTO, customerListEndSids,
						productListEndSids, screenName);
				getUI().getCurrent().addWindow(saveViewPopup);

			} catch (Exception e) {
				LOGGER.error(e);
			}
		}
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		return;
	}

	@Override
	protected void resetButtonLogic() {
		discount.select(discountDdlbDefault);
	}

	@Override
	protected void resetTwo() {
		resultsContainer.removeAllItems();
		resultTable.setContainerDataSource(resultsContainer);
		tableLogic.fireSetData(dataSelectionDTO, true);
		resultTable.setVisibleColumns(UIUtil.getInstance().dataSelectionColumns);
		resultTable.setColumnHeaders(UIUtil.getInstance().dataSelectionHeaders);
	}

	private void generateLogicForReturns() {
		try {
			LOGGER.debug("generateBtn click  listener started ");
			if (selectedProduct.size() <= 0 || fromPeriod.getValue() == null
					|| StringUtils.isBlank(projectionName.getValue())
					|| Constant.NULL.equalsIgnoreCase(projectionName.getValue()) || company.getValue() == null
					|| SELECT_ONE.equals(company.getValue())) {
				AbstractNotificationUtils.getErrorNotification(Constant.SELECTION_CRITERIA_HEADER,
						Constant.NOT_ALL_REQUIRED_FIELDS_POPULATED);
				return;
			}
			List<Leveldto> productList = selectedProductContainer.getItemIds();
			bindDataselectionDtoToSave();
			int projectionIdValue = nmLogic.saveProjection(dataSelectionDTO, screenName);
			VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, projectionIdValue);
			projectionName.setValue(String.valueOf(projectionName.getValue()));
			dataSelectionDTO.setProjectionId(projectionIdValue);
			List<String> productListEndSids = DataSelectionUtil
					.getEndLevelHierNo(getProductHierarchyEndLevels(selectedProductContainer));

			dsLogic.saveProductHierarchyLogic(productList, productListEndSids, projectionIdValue, null, Constant.SAVE,
					dataSelectionDTO);
			relationshipBuilderSids.clear();
			setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));
			dsLogic.insertToReturnDetails(projectionIdValue);
			if (projectionIdValue != 0) {
				SessionUtil sessionUtil = new SessionUtil();
				final SessionDTO session = sessionUtil.createSession();
				session.setProjectionId(projectionIdValue);
				session.setProductLevelNumber(String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
				session.setCustomerLevelNumber(String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
				session.setAction(Constant.ADD_FULL_SMALL);
				session.setBusineesUnit(businessUnitlist);
				session.setFromDate(dataSelectionDTO.getFromDate());
				session.setToDate(dataSelectionDTO.getToDate());
				session.setFromPeriod(String.valueOf(fromPeriod.getValue()));
				session.setToPeriod(String.valueOf(toPeriod.getValue()));
				session.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
				session.setProductRelationId(Integer.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
				session.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
				session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
				session.setProductDescription(productDescriptionMap);
				session.setCustomerHierarchyId(0);
				session.setReturnsDetailsMap(dsLogic.getReturnDetails(session, true));
				session.setScreenName(screenName);
				QueryUtils.createTempTables(session);
				ForecastEditWindow editWindow = new ForecastEditWindow(projectionName.getValue(), session, resultTable,
						screenName, this);
				UI.getCurrent().addWindow(editWindow);
			}
			resetOne();
			resetTwo();
			LOGGER.debug("generateBtn click listener ends ");

		} catch (Exception e) {
			LOGGER.error(e);
		}
		UI.getCurrent().setFocusedComponent(UI.getCurrent());
	}

	/**
	 * Used to set the Visible Header for DataSelection ListView
	 *
	 */
	private void setTableHeader() {
		if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
			resultTable.setVisibleColumns(TableHeaderColumnsUtil.getInstance().dataSelectionColumnReturns);
			resultTable.setColumnHeaders(TableHeaderColumnsUtil.getInstance().dataSelectionHeaderReturns);
		} else if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
			resultTable.setVisibleColumns(TableHeaderColumnsUtil.getInstance().dataselectionColumnsAccrual);
			resultTable.setColumnHeaders(TableHeaderColumnsUtil.getInstance().dataSelectionHeadersAccrual);
		} else {
			resultTable.setVisibleColumns(TableHeaderColumnsUtil.getInstance().dataSelectionColumns);
			resultTable.setColumnHeaders(TableHeaderColumnsUtil.getInstance().dataSelectionHeaders);
		}
	}

	private void generateLogicForARP() {
		DSLogic accdsLogic = new DSLogic();
		try {
			bindDataselectionDtoToSave();
			if (selectedCustomer.size() <= 0 || selectedProduct.size() <= 0
					|| StringUtils.isBlank(projectionName.getValue())
					|| Constants.CommonConstants.NULL.getConstant().equalsIgnoreCase(projectionName.getValue())
					|| company.getValue() == null || SELECT_ONE.equals(company.getValue())
					|| deductionLevel.getValue() == null || deductionValue.getValue() == null) {
				AbstractNotificationUtils.getErrorNotification(Constant.SELECTION_CRITERIA_HEADER,
						Constant.NOT_ALL_REQUIRED_FIELDS_POPULATED);
				return;
			}

			int projectionIdValue = accdsLogic.saveProjection(dataSelectionDTO, screenName);

			AccrualDataSelectionDTO dtoValue = new AccrualDataSelectionDTO();
			dtoValue.setDeductionType(String.valueOf(deductionLevel.getValue()));
			dtoValue.setDeductionValue(String.valueOf(deductionValue.getValue()));
			dtoValue.setProjectionId(StringUtils.EMPTY + projectionIdValue);
			dtoValue.setScreenName(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION);
			dtoValue.setDeductionValueId(deductionValue.getValue() == null || deductionValue.getValue() == "" ? 0
					: ((HelperDTO) deductionValue.getValue()).getId());
			accdsLogic.updateRebateValue(dtoValue);
			projectionId.setValue(String.valueOf(projectionIdValue));
			dataSelectionDTO.setProjectionId(projectionIdValue);
			dataSelectionDTO.setDeductionType(String.valueOf(deductionLevel.getValue()));
			dataSelectionDTO.setDeductionLevel(String.valueOf(deductionLevel.getValue()));
			dataSelectionDTO.setDeductionValue(String.valueOf(deductionValue.getValue()));
			dataSelectionDTO.setDeductionValueId(deductionValue.getValue() == null || deductionValue.getValue() == ""
					? 0 : ((HelperDTO) deductionValue.getValue()).getId());
			setRelationshipBuilderSids(String.valueOf(customerRelationComboBox.getValue()));
			setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));

			if (projectionIdValue != 0) {

				SessionUtil sessionUtil = new SessionUtil();
				final SessionDTO session = sessionUtil.createSession();
				session.setScreenName(screenName);
				// To create the temp tables with userId and session id
				QueryUtils.createTempTables(session);
				String dedValue = deductionValue.getValue() == null ? StringUtils.EMPTY
						: String.valueOf(((HelperDTO) deductionValue.getValue()).getId());

				String dedLevel = getDedutionLevel(String.valueOf(deductionLevel.getValue()));
				int customerRelationVersionNo = Integer.parseInt(
						customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
				int productRelationVersionNo = Integer.parseInt(
						productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
				relationLogic.ccpHierarchyInsertARP(session.getCurrentTableNames(),
						selectedCustomerContainer.getItemIds(), selectedProductContainer.getItemIds(),
						customerHierarchyLevelDefinitionList, productHierarchyLevelDefinitionList,
						dataSelectionDTO.getProjectionId(), dedLevel, dedValue, customerRelationVersionNo,
						productRelationVersionNo);
				session.setProjectionId(projectionIdValue);
				session.setSelectedCustomerRelationSid(getRelationshipSid(selectedCustomerContainer.getItemIds()));
				session.setSelectedProductRelationSid(getRelationshipSid(selectedProductContainer.getItemIds()));
				session.setProductLevelNumber(String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
				session.setCustomerLevelNumber(String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
				session.setAction(Constant.ADD_FULL_SMALL);
				session.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
				session.setProductRelationId(Integer.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
				session.setProductHierarchyId(Integer.valueOf(dataSelectionDTO.getProdHierSid()));
				session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
				session.setCustomerDescription(customerDescriptionMap);
				session.setProductDescription(productDescriptionMap);
				session.setCustomerHierarchyId(0);
				session.setBusineesUnit(businessUnitlist);
				session.setDeductionLevel(String.valueOf(deductionLevel.getValue()));
				session.setDeductionValue(String.valueOf(deductionValue.getValue()));

				DataSelectionLogic.hierarchyDetailsInsert(session.getSelectedCustomerRelationSid(),
						"PROJECTION_CUST_HIERARCHY", session.getProjectionId(), Boolean.FALSE);
				DataSelectionLogic.hierarchyDetailsInsert(session.getSelectedProductRelationSid(),
						"PROJECTION_PROD_HIERARCHY", session.getProjectionId(), Boolean.FALSE);
				AccrualRateProjectionView arpView = new AccrualRateProjectionView(
						StringUtils.EMPTY + session.getProjectionId(), session, screenName, this, false);
				getUI().getNavigator().addView(AccrualRateProjectionView.ARP_VIEW, arpView);
				getUI().getNavigator().navigateTo(AccrualRateProjectionView.ARP_VIEW);
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

	public void closeBtn() {
		try {
			resetOne();
			deductionLevel.setValue(null);
			deductionValue.setValue(null);
			modeOption.select("Add");
			dismantleCustomerSelection = true;
			dismantleProductSelection = true;
			resetButtonLogic();
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	public void loadDiscountLevel() {
		try {
			deductionLevel.setImmediate(true);
			deductionLevel.setNullSelectionAllowed(true);
			deductionLevel.setNullSelectionItemId(UIUtil.SELECT_ONE);
			deductionLevel.addItem(UIUtil.SELECT_ONE);
			deductionLevel.addItem(Constant.DEDUCTION_CATEGORY1);
			deductionLevel.addItem(Constant.DEDUCTION_PROGRAM_TYPE1);
			deductionLevel.addItem(Constant.DEDUCTION_SCHEDULE_TYPE1);
			deductionLevel.select(UIUtil.SELECT_ONE);

			deductionLevel.addValueChangeListener(new Property.ValueChangeListener() {

				@Override
				public void valueChange(Property.ValueChangeEvent event) {
					try {

						if (Constant.DEDUCTION_CATEGORY1.equalsIgnoreCase(String.valueOf(deductionLevel))) {

							commonUtils.loadComboBox(deductionValue, Constant.RS_CATEGORY1, false);
						} else if (Constant.DEDUCTION_PROGRAM_TYPE1.equalsIgnoreCase(String.valueOf(deductionLevel))) {

							commonUtils.loadComboBox(deductionValue, Constant.REBATE_PROGRAM_TYPE1, false);
						} else if (Constant.DEDUCTION_SCHEDULE_TYPE1.equalsIgnoreCase(String.valueOf(deductionLevel))) {

							commonUtils.loadComboBox(deductionValue, Constant.RS_TYPE, false);
						}

					} catch (Exception e) {
						LOGGER.error(e);
					}
				}
			});

		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

	public void reloadResultsTable() {
		searchButtonLogic();
	}

	/**
	 * Used to check which level is top in selected customer hierarchy either
	 * customer or contract It is used for CCP_HIERARCHY_INSERT query formation
	 *
	 * @param levelName
	 */
	private void levelCheck(String levelName) {
		if (StringUtils.isBlank(topLevelName) && ("Customer".equals(levelName) || "Trading Partner".equals(levelName)
				|| "TradingPartner".equals(levelName) || "Contract".equals(levelName))) {
			topLevelName = levelName;

		}
	}

	public void configurePermission() {

		try {
			final StplSecurity stplSecurity = new StplSecurity();
			final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
			Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId,
					"Accrual Rate Projection" + "," + "Landing screen");
			if (functionHM.get("generateBtn") != null
					&& !((AppPermission) functionHM.get("generateBtn")).isFunctionFlag()) {
				generateBtn.setVisible(false);
			} else {
				generateBtn.setVisible(true);
			}
			if (functionHM.get("searchBtn") != null
					&& !((AppPermission) functionHM.get("searchBtn")).isFunctionFlag()) {
				searchBtn.setVisible(false);
			} else {
				searchBtn.setVisible(true);
			}
			if (functionHM.get("saveViewBtn") != null
					&& !((AppPermission) functionHM.get("saveViewBtn")).isFunctionFlag()) {
				saveViewBtn.setVisible(false);
			} else {
				saveViewBtn.setVisible(true);
			}
			if (functionHM.get("resetBtn") != null && !((AppPermission) functionHM.get("resetBtn")).isFunctionFlag()) {
				resetBtn.setVisible(false);
			} else {
				resetBtn.setVisible(true);
			}
			if (functionHM.get("deleteViewBtn") != null
					&& !((AppPermission) functionHM.get("deleteViewBtn")).isFunctionFlag()) {
				deleteViewBtn.setVisible(false);
			} else {
				deleteViewBtn.setVisible(true);
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
			if (functionHM.get("deleteBtn") != null
					&& !((AppPermission) functionHM.get("deleteBtn")).isFunctionFlag()) {
				deleteBtn.setVisible(false);
			} else {
				deleteBtn.setVisible(true);
			}
			if (functionHM.get("resultResetBtn") != null
					&& !((AppPermission) functionHM.get("resultResetBtn")).isFunctionFlag()) {
				resultResetBtn.setVisible(false);
			} else {
				resultResetBtn.setVisible(true);
			}
		} catch (PortalException | SystemException ex) {
			LOGGER.error(ex);
		}

	}

	@Override
	public void loadProductVersionNo(Object selectedProductRelation) {
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
	public void loadCustomerVersionNo(Object selectedCustomerRelation) {
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

}