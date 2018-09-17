package com.stpl.app.gtnforecasting.ui.form;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import static com.stpl.app.gtnforecasting.utils.Constant.NULL;
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
import static com.stpl.ifs.util.constants.GlobalConstants.getCommercialConstant;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.accrualrateprojection.dto.AccrualDataSelectionDTO;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.DSLogic;
import com.stpl.app.gtnforecasting.accrualrateprojection.ui.view.AccrualRateProjectionView;
import com.stpl.app.gtnforecasting.dto.CompanyDdlbDto;
import com.stpl.app.gtnforecasting.dto.RelationshipDdlbDto;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.logic.RelationShipFilterLogic;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.logic.tablelogic.DataSelectionSearchLogic;
import com.stpl.app.gtnforecasting.projectionvariance.logic.NMProjectionVarianceLogic;
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
import com.stpl.app.gtnforecasting.utils.DataSelectionUtil;
import com.stpl.app.gtnforecasting.utils.HelperListUtil;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_DISCOUNT_PROJECTION;
import com.stpl.app.utils.DateToStringConverter;
import com.stpl.app.utils.QueryUtils;
import com.stpl.app.utils.TableHeaderColumnsUtil;
import com.stpl.app.utils.UiUtils;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastInputBean;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.ui.forecastds.form.ForecastDataSelection;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.UIUtil;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.ui.ComboBox;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.HashMap;
import java.util.Map.Entry;

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
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSelectionForm.class);
        
        

	/**
	 * The Constant NAME.*
	 */
	protected static final String NAME = "DATA";

	/**
	 * The data selection binder.
	 */
	private final String scrName;

	private Map<String, String> customerDescMap = null;
	private Map<String, String> productDescMap = null;
	private boolean dismantleCustSelection = true;
	private boolean dismantleProdSelection = true;
	private final CompanyDdlbDto discountDdlbDefault = new CompanyDdlbDto(0, SELECT_ONE, true);
	private final DataSelectionLogic dataLogic = new DataSelectionLogic();
	private final DataSelectionSearchLogic tableLogic = new DataSelectionSearchLogic();
	protected ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
	private final List<Integer> customerBeanList = new ArrayList<>();
	private final List<Integer> productBeanList = new ArrayList<>();
	private String publicViewName;
	private String privateViewName;
	private final CommonUtil commonUtils = CommonUtil.getInstance();
	private final DataSelectionLogic dsLogic = new DataSelectionLogic();
	private final NonMandatedLogic nmLogic = new NonMandatedLogic();
	private boolean editnotif = false;
	private List<Object[]> businessUnitlist;
	private Map<String,String> customViewInput=new HashMap<>();
	private List<Leveldto> productHierarchyLevelDefinitionList = Collections.emptyList();
	private List<Leveldto> customerHierarchyLevelDefinitionList = Collections.emptyList();
	private final RelationShipFilterLogic relationLogic = RelationShipFilterLogic.getInstance();
        public static HierarchyLookup customerHierarchyLookupWindow;

        public static HierarchyLookup productHierarchyLookupWindow;

    public static HierarchyLookup getProductHierarchyLookupWindow() {
        return productHierarchyLookupWindow;
    }

    public static void setProductHierarchyLookupWindow(HierarchyLookup productHierarchyLookupWindow) {
        DataSelectionForm.productHierarchyLookupWindow = productHierarchyLookupWindow;
    }
        private static List<Object> queryParametersForSelectedTable = new ArrayList<>(); 
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

        
    public static HierarchyLookup getCustomerHierarchyLookupWindow() {
        return customerHierarchyLookupWindow;
    }

    public static void setCustomerHierarchyLookupWindow(HierarchyLookup customerHierarchyLookupWindow) {
        DataSelectionForm.customerHierarchyLookupWindow = customerHierarchyLookupWindow;
    }
	private final HelperListUtil helperListUtil = HelperListUtil.getInstance();

	private ResourceBundle tableName = ResourceBundle.getBundle("properties.Constants");

	// Used for CCP_HIERARCHY_INSERT query formation
	private String topLevelName = StringUtils.EMPTY;

	/**
	 * Instantiates a new data selection index.
	 *
	 * @param dataSelectionBinder
	 * @param dataSelectionDTO
	 * @throws java.lang.Exception
	 */
        
	
         public DataSelectionForm(){
             this.scrName = "Non Mandated";
         }
         
        public DataSelectionForm(SessionDTO sessionDto,DataSelectionDTO dataSelectionDto, GtnFrameworkForecastInputBean inputBean) {
		super();
                this.scrName = "Non Mandated";
                this.dataSelectionDTO = dataSelectionDto;
                this.inputBean = inputBean;
		LOGGER.info("DataSelectionIndex Initializing... ");
		LOGGER.info("DataSelectionIndex Ends");
	}
        
	public DataSelectionForm(CustomFieldGroup dataSelectionBinder, DataSelectionDTO dataSelectionDTO, String screenName)
			throws  PortalException {
		super(dataSelectionBinder, screenName, true);
		LOGGER.debug("DataSelectionIndex Initializing... ");
		this.dataSelectionDTO = dataSelectionDTO;
		this.scrName = screenName;
		configureFields();

		final StplSecurity stplSecurity = new StplSecurity();
                            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
				Map<String, AppPermission> nMfunctionCompanyHM = stplSecurity.getBusinessFunctionPermissionForNm(
						String.valueOf(VaadinSession.getCurrent().getAttribute("businessRoleIds")),
						getCommercialConstant() + "," + UISecurityUtil.DATA_SELECTION_INDEX);
                                 getButtonPermission(nMfunctionCompanyHM);
                                
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
                tableLogic.setPageLength(10);
                resultTable.setItemsPerPage(10);
		tableLogic.sinkItemPerPageWithPageLength(false);
		resultTable.setSelectable(true);
		setProductForecastLevelNullSelection();
		setProductLevelNullSelection();
		setProductRelationNullSelection();
                setNullSelectionCustomDdlb(customRelationDdlb);
                setNullSelectionCustomDdlb(customRelationDdlbDeduction);
		if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {
			resultTable.setVisibleColumns(TableHeaderColumnsUtil.getDataSelectionColumnsAccrual());
			resultTable.setColumnHeaders(TableHeaderColumnsUtil.getDataSelectionHeadersAccrual());
			productLevel.setVisible(false);
			customerLevel.setVisible(false);
			productForecastLevelLabel.setVisible(false);
			customerForecastLevelLabel.setVisible(false);
                 
                        forecastEligibleDateLB.setVisible(false);
                        forecastEligibleDate.setVisible(false);
                        customRelation.setVisible(false);
                        customRelationDdlb.setVisible(false);
                        customRelationDiscount.setVisible(false);
                        customRelationDdlbDeduction.setVisible(false);
                        
			modeOptionChange(true);
		} else {
			resultTable.setVisibleColumns(TableHeaderColumnsUtil.getDataSelectionColumns());
			resultTable.setColumnHeaders(TableHeaderColumnsUtil.getDataSelectionHeaders());
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
                forecastEligibleDate.setValue(dsLogic.getDefaultEligibleDateFromForecastConfiguration());

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
		resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
		resultTable.addStyleName(Constant.FILTER_TABLE);
		resultTable.addStyleName("table-header-normal");
		com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils.getUserName();
		if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {
			loadDiscountLevel();
		}
                configureDataSelectionDeductionLevel();
	}

	public void loadFilteredProductSelection(final String selectedLevel) {
		try {

			availableProductContainer.removeAllItems();
			int forecastLevel = 0;
			boolean isProduct = false;
			List<Leveldto> selectedCustomerContractList;
			String levelName = Constant.LEVEL_LABEL;
			List<Leveldto> resultedLevelsList = new ArrayList<>();
			if (selectedLevel != null && !Constants.CommonConstants.NULL.getConstant().equals(selectedLevel)
					&& !SELECT_ONE.equals(selectedLevel)) {
				int relationVersionNo = Integer.parseInt(
						productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
				dataSelectionDTO.setProductRelationShipVersionNo(relationVersionNo);
				String customerVersionNo = customerRelationVersionComboBox
						.getItemCaption(customerRelationVersionComboBox.getValue());
				int customerRelationVersionNo = customerVersionNo == null ? 0 : Integer.parseInt(customerVersionNo);
				int hierarchyVersionNo = Integer.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
				productDescMap = relationLogic.getLevelValueMap(String.valueOf(productRelation.getValue()),
						productHierarchyDto.getHierarchyId(), hierarchyVersionNo, relationVersionNo);
				String dedLevel = StringUtils.EMPTY;
				String dedValue = StringUtils.EMPTY;
				if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)
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
				Leveldto selectedHierarchyLevelDto = productHierarchyLevelDefinitionList.get(forecastLevel - 1);
				isProduct = (selectedHierarchyLevelDto.getLevel().equalsIgnoreCase("Package")
						|| selectedHierarchyLevelDto.getLevel().equalsIgnoreCase("NDC-11"));
				selectedCustomerContractList = getSelectedCustomerContractList();

				List<String> tempGroupFileter = groupFilteredItems == null ? Collections.<String>emptyList()
						: groupFilteredItems;
//				resultedLevelsList = relationLogic.loadAvailableProductlevel(selectedHierarchyLevelDto,
//						Integer.parseInt(relationshipSid), tempGroupFileter, selectedCustomerContractList, isProduct,
//						dedLevel, dedValue, relationVersionNo, customerRelationVersionNo, businessUnit.getValue(),
//						productDescMap);
				if (selectedHierarchyLevelDto.getLevel() != null) {
					levelName = selectedHierarchyLevelDto.getLevel();
				}
				availableProduct.removeAllItems();
				availableProductContainer.removeAllItems();
				availableProductContainer.addAll(resultedLevelsList);
			}
			availableProduct.setContainerDataSource(availableProductContainer);
			if (isProduct) {
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

			LOGGER.error(" - in loadFilteredProductSelection= {}", ex);
		}
	}

	private List<Leveldto> getSelectedCustomerContractList() {
		List<Leveldto> ccList = Collections.emptyList();
		if (selectedCustomerContainer != null && !selectedCustomerContainer.getItemIds().isEmpty()) {
			Leveldto selectedCurrentDto;
			ccList = new ArrayList<>();
			for (Object item : selectedCustomerContainer.getItemIds()) {
				selectedCurrentDto = DataSelectionUtil.getBeanFromId(item);
				if (selectedCurrentDto != null && !StringUtils.isBlank(selectedCurrentDto.getTableName())) {
					ccList.add(selectedCurrentDto);
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
		dismantleCustSelection = true;
		dismantleProdSelection = true;
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
		if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(scrName)) {
			deductionLevel.setValue(viewDTO.getDeductionLevel());

			deductionValue.setValue(
					helperListUtil.getHelperDTOByID(StringUtils.isNumeric(String.valueOf(viewDTO.getDeductionValueId()))
							? viewDTO.getDeductionValueId() : 0));
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

			loadCustomerLevel(viewDTO.getCustomerHierarchySid(), viewDTO.getCustomerLevel(), viewDTO.getCustHierarchyVersion());
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
			loadProductLevel(viewDTO.getProductHierarchySid(), viewDTO.getProductLevel(), viewDTO.getProdHierarchyVersion());
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

		DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(), scrName);
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
                frequency.select(viewDTO.getDataSelectionFrequency() != null ? viewDTO.getDataSelectionFrequency() : "Quarterly");
                dataSelectionDeductionLevel.select(viewDTO.getDataSelectionDedLevel() != null ? viewDTO.getDataSelectionDedLevel() : 1);
                customRelationDdlb.select(viewDTO.getCustomRelationShipSid());
                customRelationDdlbDeduction.select(viewDTO.getCustomDeductionRelationShipSid());

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
                        if (dataSelectionDeductionLevel.getValue() != null
					&& !SELECT_ONE.equals(dataSelectionDeductionLevel.getValue())) {

				dataSelectionDTO.setDataSelectionDeductionLevelSid(Integer.parseInt(String.valueOf(dataSelectionDeductionLevel.getValue())));
			} else {
				dataSelectionDTO.setDataSelectionDeductionLevelSid(0);
			}
                        if (frequency.getValue() != null
					&& !SELECT_ONE.equals(frequency.getValue())) {

				dataSelectionDTO.setFrequency(String.valueOf(frequency.getValue()));
			} else {
				dataSelectionDTO.setFrequency("0");
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
			dataSelectionDTO.setCustomerGroup(customerGroup.getValue());
			dataSelectionDTO.setProductGroup(productGroup.getValue());
			if (customerHierarchyDto != null && customerRelationVersionComboBox.getValue()!= null) {
				int custHierarchyVersionNo = Integer
						.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
				dataSelectionDTO.setCustomerHierVersionNo(custHierarchyVersionNo);
			} else {
				dataSelectionDTO.setCustomerHierVersionNo(0);
			}
			if (productHierarchyDto != null && productRelationVersionComboBox.getValue() != null) {
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

			if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

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
                        dataSelectionDTO.setForecastEligibleDate(forecastEligibleDate.getValue());
                        dataSelectionDTO.setCustomRelationShipSid(customRelationDdlb.getValue()!=null ? Integer.parseInt(String.valueOf(customRelationDdlb.getValue())): 0 );
                        dataSelectionDTO.setCustomDeductionRelationShipSid(customRelationDdlbDeduction.getValue()!=null ? Integer.parseInt(String.valueOf(customRelationDdlbDeduction.getValue())):0 );

		} catch (ParseException ex) {

			LOGGER.error(" in binding for save, can't parse dates= {}", ex);
		}
		return dataSelectionDTO;
	}
	public DataSelectionDTO bindDataselectionDtoToSaveNewArch(GtnFrameworkForecastInputBean inputBean) {
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");

			dataSelectionDTO.clear();
			SimpleDateFormat format = new SimpleDateFormat(Constants.CommonConstants.DATE_FORMAT.getConstant());
//			if (company.getValue() != null && !SELECT_ONE.equals(company.getValue())) {

				dataSelectionDTO.setCompanySid(String.valueOf(inputBean.getCompany()));
//			} else {
//				dataSelectionDTO.setCompanySid(String.valueOf(0));
//			}

//			if (businessUnit.getValue() != null && !SELECT_ONE.equals(businessUnit.getValue())) {
				dataSelectionDTO.setBusinessUnitSystemId((Integer) inputBean.getBusinessUnit());
//			} else {
//				dataSelectionDTO.setBusinessUnitSystemId(0);
//			}
//			if (deductionLevel.getValue() != null && !SELECT_ONE.equals(deductionLevel.getValue())) {
				dataSelectionDTO.setDeductionLevel(inputBean.getDeductionLevel());
//			}
//			if (deductionValue.getValue() != null && !SELECT_ONE.equals(deductionValue.getValue())) {
				dataSelectionDTO.setDeductionValue(String.valueOf(inputBean.getDeductionValue()));
//			}

//			if (customerRelationComboBox.getValue() != null
//					&& !SELECT_ONE.equals(customerRelationComboBox.getValue())) {

				dataSelectionDTO.setCustRelationshipBuilderSid(String.valueOf(inputBean.getCustomerRelationSid()));
                                dataSelectionDTO.setCustomerRelationShipVersionNo(inputBean.getCustomerRelationVersionNo());
//			} else {
//				dataSelectionDTO.setCustRelationshipBuilderSid(String.valueOf(0));
//			}
//			if (productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue())) {

				dataSelectionDTO.setProdRelationshipBuilderSid(String.valueOf(inputBean.getProductRelationSid()));
                                dataSelectionDTO.setProductRelationShipVersionNo(inputBean.getProductRelationVersionNo());
//			} else {
//				dataSelectionDTO.setProdRelationshipBuilderSid(String.valueOf(0));
//			}
//			if (customerHierarchyDto != null
//					&& !Constants.CommonConstants.NULL.getConstant()
//							.equals(String.valueOf(customerHierarchyDto.getHierarchyId()))
//					&& !StringUtils.isEmpty(String.valueOf(customerHierarchyDto.getHierarchyId()))
//					&& !StringUtils.isBlank(String.valueOf(customerHierarchyDto.getHierarchyId()))) {

//				if (!StringUtils.isBlank(customerHierarchy.getValue())
//						|| Constant.NULL.equals(customerHierarchy.getValue())) {
					dataSelectionDTO.setCustomerHierSid(String.valueOf(inputBean.getCustomerHierarchySid()));
//				} else {
//					dataSelectionDTO.setCustomerHierSid(String.valueOf(0));
//				}
//			} else {
//				dataSelectionDTO.setCustomerHierSid(String.valueOf(0));
//			}
//			if (productHierarchyDto != null
//					&& !Constants.CommonConstants.NULL.getConstant()
//							.equals(String.valueOf(productHierarchyDto.getHierarchyId()))
//					&& !StringUtils.isEmpty(String.valueOf(productHierarchyDto.getHierarchyId()))
//					&& !StringUtils.isBlank(String.valueOf(productHierarchyDto.getHierarchyId()))) {

//				if (!(StringUtils.isBlank(productHierarchy.getValue())
//						|| Constant.NULL.equals(productHierarchy.getValue()))) {
					dataSelectionDTO.setProdHierSid(String.valueOf(inputBean.getProductHierarchySid()));
//				} else {
//					dataSelectionDTO.setProdHierSid(String.valueOf(0));
//				}

//			} else {
//				dataSelectionDTO.setProdHierSid(String.valueOf(0));
//			}

//			if (selectedCustomerGroupDTO != null
//					&& !Constants.CommonConstants.NULL.getConstant()
//							.equals(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()))
//					&& !StringUtils.isEmpty(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()))
//					&& !StringUtils.isBlank(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()))
//					&& !customerGroup.getValue().isEmpty()) {
//				dataSelectionDTO.setCustomerGrpSid(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()));
//			} else {
				dataSelectionDTO.setCustomerGrpSid(String.valueOf(0));
//			}
//			if (selectedProductGroupDTO != null
//					&& !Constants.CommonConstants.NULL.getConstant()
//							.equals(String.valueOf(selectedProductGroupDTO.getProductGroupSid()))
//					&& !StringUtils.isEmpty(String.valueOf(selectedProductGroupDTO.getProductGroupSid()))
//					&& !StringUtils.isBlank(String.valueOf(selectedProductGroupDTO.getProductGroupSid()))
//					&& !productGroup.getValue().isEmpty()) {
//				dataSelectionDTO.setProdGrpSid(String.valueOf(selectedProductGroupDTO.getProductGroupSid()));
//			} else {
				dataSelectionDTO.setProdGrpSid(String.valueOf(0));
//			}
//                        if (dataSelectionDeductionLevel.getValue() != null
//					&& !SELECT_ONE.equals(dataSelectionDeductionLevel.getValue())) {

				dataSelectionDTO.setDataSelectionDeductionLevelSid(Integer.parseInt(String.valueOf("1")));
//			} else {
//				dataSelectionDTO.setDataSelectionDeductionLevelSid(0);
//			}
//                        if (frequency.getValue() != null
//					&& !SELECT_ONE.equals(frequency.getValue())) {

				dataSelectionDTO.setFrequency(String.valueOf("Quarterly"));
//			} else {
//				dataSelectionDTO.setFrequency("0");
//			}

			int customerForecastLevel = 0;
			int productForecastLevel = 0;
			int customerForecastInnerLevel = 0;
			int productForecastInnerLevel = 0;
//			if (customerLevel.getValue() != null && !SELECT_ONE.equals(customerLevel.getValue())) {
				customerForecastLevel = inputBean.getCustomerHierarchyLevel();
//			}
//			if (productLevel.getValue() != null && !SELECT_ONE.equals(productLevel.getValue())) {
				productForecastLevel = inputBean.getProductHierarchyLevel();
//			}
//			if (level.getValue() != null && !SELECT_ONE.equals(level.getValue())) {
				customerForecastInnerLevel = inputBean.getCustomerRelationLevel();
//			}
//			if (productlevelDdlb.getValue() != null && !SELECT_ONE.equals(productlevelDdlb.getValue())) {
				productForecastInnerLevel = inputBean.getProductRelationLevel();
//			}

			dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(customerForecastLevel));
			dataSelectionDTO.setProductHierarchyLevel(String.valueOf(productForecastLevel));
			dataSelectionDTO.setProductHierarchy(inputBean.getProductHierarchyName());
			dataSelectionDTO.setCustomerHierarchy(inputBean.getCustomerHierarchyName());
			dataSelectionDTO.setCustomerGroup("0");
			dataSelectionDTO.setProductGroup("0");
//			if (customerHierarchyDto != null && customerRelationVersionComboBox.getValue()!= null) {
//				int custHierarchyVersionNo = Integer
//						.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
				dataSelectionDTO.setCustomerHierVersionNo(inputBean.getCustomerHierarchyVersion());
//			} else {
//				dataSelectionDTO.setCustomerHierVersionNo(0);
//			}
//			if (productHierarchyDto != null && productRelationVersionComboBox.getValue() != null) {
//				int prodHierarchyVersionNo = Integer
//						.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
				dataSelectionDTO.setProductHierVersionNo(inputBean.getProductHierarchyVersion());
//			} else {
//				dataSelectionDTO.setProductHierVersionNo(0);
//			}

//			if (fromPeriod.getData() == null) {
//				if (fromPeriod.getValue() != null) {
//					if (MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
//						dataSelectionDTO.setFromPeriod(String.valueOf(fromPeriod.getValue()));
//						if (!String.valueOf(fromPeriod.getValue()).isEmpty()
//								&& !Constant.SELECT_ONE.equals(String.valueOf(fromPeriod.getValue()))) {
//							dataSelectionDTO.setFromDate(format.parse(
//									DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
//						}
//					} else {
//						dataSelectionDTO.setFromPeriod(
//								DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
//						dataSelectionDTO.setFromDate(format
//								.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
//					}
//
//				}
//			} else 
//                            if (fromPeriod.getValue() != null) {
//				if (MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
//					dataSelectionDTO.setFromPeriod(String.valueOf(fromPeriod.getValue()));
//					if (!String.valueOf(fromPeriod.getValue()).isEmpty()
//							&& !Constant.SELECT_ONE.equals(String.valueOf(fromPeriod.getValue()))) {
//						dataSelectionDTO.setFromDate(format
//								.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
//					}
//				} else {
//					// Getting Current Year & Period Value
//					Date dateFromValue = format
//							.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
//					int quarterFromValue = DataSelectionUtil.getQuarter(dateFromValue);
//					int yearFromValue = DataSelectionUtil.getYearFromDate(dateFromValue);
//
//					// Getting Existing Year & Period Value
//					Date date = inputFormat.parse(String.valueOf(fromPeriod.getData()));
//					int quarterFromFc = DataSelectionUtil.getQuarter(date);
//					int yearFromFc = DataSelectionUtil.getYearFromDate(date);
//					// Comparing Existing year Value with New Value
//					if (yearFromValue == yearFromFc) {
//						// Comparing Existing year Period with New Year Period
//						if (quarterFromValue == quarterFromFc) {
//							String outputString = format.format(date);
//							dataSelectionDTO.setFromPeriod(outputString);
//							dataSelectionDTO.setFromDate(format.parse(outputString));
//						} else {
//							dataSelectionDTO.setFromPeriod(
//									DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
//							dataSelectionDTO.setFromDate(format.parse(
//									DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
//						}
//					} else {
						dataSelectionDTO.setFromPeriod(
								DataSelectionUtil.getDateFromQuarter(String.valueOf("Q1 - 2017")));
						dataSelectionDTO.setFromDate(format
								.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf("Q1 - 2017"))));
//					}
//				}
//
//			}
//			if (toPeriod.getData() == null) {
//				if (toPeriod.getValue() != null) {
//					if (MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
//						dataSelectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
//						if (!String.valueOf(toPeriod.getValue()).isEmpty()
//								&& !Constant.SELECT_ONE.equals(String.valueOf(toPeriod.getValue()))) {
//							dataSelectionDTO.setToDate(format.parse(
//									DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue()))));
//						}
//					} else {
						dataSelectionDTO.setToPeriod(
								DataSelectionUtil.getLastDateFromQuarter(String.valueOf("Q4 - 2020")));
						dataSelectionDTO.setToDate(format
								.parse(DataSelectionUtil.getLastDateFromQuarter(String.valueOf("Q4 - 2020"))));
//					}
//				}
//
//			} else 
//                            if (toPeriod.getValue() != null) {
//				if (MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
//					dataSelectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
//					if (!String.valueOf(toPeriod.getValue()).isEmpty()
//							&& !Constant.SELECT_ONE.equals(String.valueOf(toPeriod.getValue()))) {
//						Date date = inputFormat.parse(String.valueOf(toPeriod.getData()));
//						String outputString = format.format(date);
//						dataSelectionDTO.setToDate(format.parse(outputString));
//					}
//				} else {
//					Date date = inputFormat.parse(String.valueOf(toPeriod.getData()));
//					Date dateToValue = format
//							.parse(DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue())));
//					int quarterToValue = DataSelectionUtil.getQuarter(dateToValue);
//					int yearToValue = DataSelectionUtil.getYearFromDate(dateToValue);
//					int quarterToFc = DataSelectionUtil.getQuarter(date);
//					int yearToFc = DataSelectionUtil.getYearFromDate(dateToValue);
//					if (yearToValue == yearToFc) {
//						if (quarterToValue == quarterToFc) {
//							String outputString = format.format(date);
//							dataSelectionDTO.setToPeriod(outputString);
//							dataSelectionDTO.setToDate(format.parse(outputString));
//						} else {
//							dataSelectionDTO.setToPeriod(
//									DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue())));
//							dataSelectionDTO.setToDate(format.parse(
//									DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue()))));
//						}
//
//					} else {
//						dataSelectionDTO.setToPeriod(
//								DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue())));
//						dataSelectionDTO.setToDate(format
//								.parse(DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue()))));
//					}
//				}
//			}

//			if (discount.getValue() != null && !SELECT_ONE.equals(discount.getValue())
//					&& StringUtils.isNotBlank(discount.getValue().toString())) {
//				CompanyDdlbDto dto = (CompanyDdlbDto) discount.getValue();
//				dataSelectionDTO.setDiscount(dto.getRsNo());
//				dataSelectionDTO.setDiscountSid(dto.getRsModelSid());
//			} else {
//				dataSelectionDTO.setDiscount(SELECT_ONE);
//				dataSelectionDTO.setDiscountSid(0);
//			}

			if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

				dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(customerForecastInnerLevel));
				dataSelectionDTO.setProductHierarchyLevel(String.valueOf(productForecastInnerLevel));
			} else {
				dataSelectionDTO.setCustomerHierarchyLevel(String.valueOf(customerForecastLevel));
				dataSelectionDTO.setProductHierarchyLevel(String.valueOf(productForecastLevel));
			}

			dataSelectionDTO.setCustomerHierarchyInnerLevel(String.valueOf(customerForecastInnerLevel));
			dataSelectionDTO.setProductHierarchyInnerLevel(String.valueOf(productForecastInnerLevel));
			dataSelectionDTO.setProjectionName(inputBean.getProjectionName());
			dataSelectionDTO.setDescription(inputBean.getProjectionDescription());
                        dataSelectionDTO.setForecastEligibleDate(inputBean.getForecastEligibleDate());
//                        dataSelectionDTO.setCustomRelationShipSid(inputBean.getCustomerRelationSid());
//                        dataSelectionDTO.setCustomDeductionRelationShipSid(customRelationDdlbDeduction.getValue()!=null ? Integer.parseInt(String.valueOf(customRelationDdlbDeduction.getValue())):0 );

		} catch (Exception ex) {

			LOGGER.error(" in binding for save, can't parse dates= {}", ex);
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
//                        int customerHierarchyVersionNo = Integer
//					.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
//			int customerRelationVersionNo = Integer.parseInt(
//					customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
                         List<GtnWsRelationshipBuilderBean> relationshipBuilderBeanList = getRelationshipBuilderBeanList();
                        int customerRelationVersionNo = 0;
                            for(GtnWsRelationshipBuilderBean relationshipBuiderBean:relationshipBuilderBeanList){
                                if(relationshipBuiderBean.getRelationshipBuilderSid() == (int)customerRelationComboBox.getValue()){
                                    customerRelationVersionNo = relationshipBuiderBean.getVersionNo();
                                    break;
                                }
                            }
                        int customerHierarchyVersionNo = customerHierarchyLookupWindow.getHierarchyDto().getVersionNo();
                                 
			if (availableCustomer.getValue() != null) {
				int forecastLevel = 0;
				if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {
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
						List<String> hierarchyNos = new ArrayList<>();
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
						
						if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

							newChildLevels = logic.getChildLevelsWithHierarchyNo(
									UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
									customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]),
									forecastEligibleDate.getValue(), false);
						} else {
                                                    
                                                    newChildLevels = logic.getChildLevelsWithHierarchyNoNewArch(
									UiUtils.parseStringToInteger(
											String.valueOf(customerLevel.getValue())),
									customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
                                                                        Integer.parseInt(String.valueOf(level.getValue())),
									forecastEligibleDate.getValue(), false,queryParametersForSelectedTable,availableCustomer,DataSelection.LOAD_SELECTED_CUSTOMER);
                                                    
                                                    
//							newChildLevels = logic.getChildLevelsWithHierarchyNo(
//									UiUtils.parseStringToInteger(
//											String.valueOf(customerLevel.getValue()).split("-")[0]),
//									customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
//									customerHierarchyVersionNo, customerRelationVersionNo,
//									UiUtils.getDataSelectionFormattedLevelNo(
//											String.valueOf(level.getValue()).split("-")[0]),
//									forecastEligibleDate.getValue(), false);
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


					} else {
						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
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
							newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), customerDescMap,
									customerHierarchyVersionNo, customerRelationVersionNo);
						}
						if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

							newChildLevels = logic.getChildLevelsWithHierarchyNo(
									UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
									customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]),
									forecastEligibleDate.getValue(), false);
						} 
                                                else {
							newChildLevels = logic.getChildLevelsWithHierarchyNoNewArch(
									UiUtils.parseStringToInteger(
											String.valueOf(customerLevel.getValue())),
									customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
                                                                        Integer.parseInt(String.valueOf(level.getValue())),
									forecastEligibleDate.getValue(), false,queryParametersForSelectedTable,availableCustomer,DataSelection.LOAD_SELECTED_CUSTOMER);
						}
 //                                               else {
//							newChildLevels = logic.getChildLevelsWithHierarchyNo(
//									UiUtils.parseStringToInteger(
//											String.valueOf(customerLevel.getValue())),
//									customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
//									customerHierarchyVersionNo, customerRelationVersionNo,
//                                                                        Integer.parseInt(String.valueOf(level.getValue())),
//									forecastEligibleDate.getValue(), false);
//						}
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
							customerDescMap, customerHierarchyVersionNo, customerRelationVersionNo);
					if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
								customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(level.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), false);
					} else {
                                            newChildLevels = logic.getChildLevelsWithHierarchyNoNewArch(
									UiUtils.parseStringToInteger(
											String.valueOf(customerLevel.getValue())),
									customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
                                                                        Integer.parseInt(String.valueOf(level.getValue())),
									forecastEligibleDate.getValue(), false,queryParametersForSelectedTable,availableCustomer,DataSelection.LOAD_SELECTED_CUSTOMER);
                                            
//						newChildLevels = logic.getChildLevelsWithHierarchyNo(
//								UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]),
//								customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
//								customerHierarchyVersionNo, customerRelationVersionNo,
//								UiUtils.getDataSelectionFormattedLevelNo(
//										String.valueOf(level.getValue()).split("-")[0]),
//								forecastEligibleDate.getValue(), false);
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
					if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
								customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(level.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), false);
					} else {
						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]),
								customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(level.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), false);
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
			int selecetedLevel = 0;
			int customerHieVersionNo = Integer
					.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
			int customerRelationVersionNo = Integer.parseInt(
					customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
			if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {
				if (level.getValue() != null) {
					selecetedLevel = UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]);
				}
			} else if (customerLevel.getValue() != null) {
				selecetedLevel = UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]);
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
								List<String> uncommonValuesList = DataSelectionUtil.storeUncommonValues(hierarchyNos,
										selectedHierarchyNos);
								List<String> removeValuesList = new ArrayList<>();
								for (String uncommonHierValue : uncommonValuesList) {
									if (selectedHierarchyNos.contains(uncommonHierValue)) {
										removeValuesList.add(uncommonHierValue);
									}
								}
								if (!removeValuesList.isEmpty()) {
									uncommonValuesList.removeAll(removeValuesList);
								}
								if (!uncommonValuesList.isEmpty()) {
									newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValuesList), customerDescMap,
											customerHieVersionNo, customerRelationVersionNo);
								}
								if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

									newChildLevels = logic.getChildLevelsWithHierarchyNo(
											UiUtils.parseStringToInteger(
													String.valueOf(level.getValue()).split("-")[0]),
											customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
											customerHieVersionNo, customerRelationVersionNo,
											UiUtils.getDataSelectionFormattedLevelNo(
													String.valueOf(level.getValue()).split("-")[0]),
											forecastEligibleDate.getValue(), false);
								} else {
									newChildLevels = logic.getChildLevelsWithHierarchyNo(
											UiUtils.parseStringToInteger(
													String.valueOf(customerLevel.getValue()).split("-")[0]),
											customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
											customerHieVersionNo, customerRelationVersionNo,
											UiUtils.getDataSelectionFormattedLevelNo(
													String.valueOf(level.getValue()).split("-")[0]),
											forecastEligibleDate.getValue(), false);
								}
								if (newParentLevels != null) {
									int pos2 = 0;
									String parentHierarchyNo;
									Leveldto parentLevelDto = null;
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
											if (selecetedLevel != newLevel.getLevelNo()) {
												selectedCustomerContainer.setChildrenAllowed(newLevel, true);
											} else {
												selectedCustomerContainer.setChildrenAllowed(newLevel, false);
											}
										}

										if (!StringUtils.isBlank(parentHierarchyNo)) {
											for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
												if (parentHierarchyNo
														.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
													parentLevelDto = selectedLevel;
													break;
												}
											}
										}
										selectedCustomerContainer.setParent(newLevel, parentLevelDto);
										parentLevelDto = newLevel;
									}
									if (!newChildLevels.isEmpty()) {
										int pos3 = 0;
										String childHieNo;
										Leveldto childsParent = null;
										for (Leveldto newLevel : newChildLevels) {
											String tempHNo = newLevel.getHierarchyNo();
											if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
												tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
											}
											pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
											if (pos3 != -1) {
												childHieNo = tempHNo.substring(0, pos3) + ".";
											} else {
												childHieNo = tempHNo + ".";
											}
											if (customerBeanList.isEmpty()
													|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
												customerBeanList.add(newLevel.getRelationshipLevelSid());
												selectedCustomerContainer.addBean(newLevel);
												if (selecetedLevel != newLevel.getLevelNo()) {
													selectedCustomerContainer.setChildrenAllowed(newLevel, true);
												} else {
													selectedCustomerContainer.setChildrenAllowed(newLevel, false);
												}
											}
											if (!StringUtils.isBlank(childHieNo)) {
												for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
													if (childHieNo
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
								newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), customerDescMap,
										customerHieVersionNo, customerRelationVersionNo);
							}
							if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

								newChildLevels = logic.getChildLevelsWithHierarchyNo(
										UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
										customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
										customerHieVersionNo, customerRelationVersionNo,
										UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(level.getValue()).split("-")[0]),
										forecastEligibleDate.getValue(), false);
							} else {
								newChildLevels = logic.getChildLevelsWithHierarchyNo(
										UiUtils.parseStringToInteger(
												String.valueOf(customerLevel.getValue()).split("-")[0]),
										customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
										customerHieVersionNo, customerRelationVersionNo,
										UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(level.getValue()).split("-")[0]),
										forecastEligibleDate.getValue(), false);
							}
							if (newParentLevels != null) {
								for (Leveldto newLevel : newParentLevels) {
									if (customerBeanList.isEmpty()
											|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
										customerBeanList.add(newLevel.getRelationshipLevelSid());
										selectedCustomerContainer.addBean(newLevel);
										if (selecetedLevel != newLevel.getLevelNo()) {
											selectedCustomerContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedCustomerContainer.setChildrenAllowed(newLevel, false);
										}
										selectedCustomerContainer.setParent(newLevel, selectedParent);
										selectedParent = newLevel;
									}
								}
								if (!newChildLevels.isEmpty()) {
									int position3 = 0;
									String childHierarchyNo;
									Leveldto childsParent = null;
									for (Leveldto newLevel : newChildLevels) {
										String tempHNo = newLevel.getHierarchyNo();
										if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
											tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
										}
										position3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
										if (position3 != -1) {
											childHierarchyNo = tempHNo.substring(0, position3) + ".";
										} else {
											childHierarchyNo = tempHNo + ".";
										}
										if (customerBeanList.isEmpty()
												|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
											customerBeanList.add(newLevel.getRelationshipLevelSid());
											selectedCustomerContainer.addBean(newLevel);
											if (selecetedLevel != newLevel.getLevelNo()) {
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
							if (selecetedLevel != selectedParent.getLevelNo()) {
								selectedCustomerContainer.setChildrenAllowed(selectedParent, true);
							} else {
								selectedCustomerContainer.setChildrenAllowed(selectedParent, false);
							}
						}
						if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

							newChildLevels = logic.getChildLevelsWithHierarchyNo(
									UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
									customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHieVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]),
									forecastEligibleDate.getValue(), false);
						} else {
							newChildLevels = logic.getChildLevelsWithHierarchyNo(
									UiUtils.parseStringToInteger(
											String.valueOf(customerLevel.getValue()).split("-")[0]),
									customerDescMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHieVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]),
									forecastEligibleDate.getValue(), false);
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
									if (selecetedLevel != newLevel.getLevelNo()) {
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

					for (Leveldto levelBean : iteams) {
						String hierarchyNo = DataSelectionUtil.getBeanFromId(levelBean).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
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
						newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(uncommonValues), customerDescMap,
								customerHieVersionNo, customerRelationVersionNo);
						if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

							newChildLevels = logic.getChildLevelsWithHierarchyNo(
									UiUtils.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]),
									customerDescMap, 0, DataSelectionUtil.getBeanFromId(levelBean),
									customerHieVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]),
									forecastEligibleDate.getValue(), false);
						} else {
							newChildLevels = logic.getChildLevelsWithHierarchyNo(
									UiUtils.parseStringToInteger(
											String.valueOf(customerLevel.getValue()).split("-")[0]),
									customerDescMap, 0, DataSelectionUtil.getBeanFromId(levelBean),
									customerHieVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]),
									forecastEligibleDate.getValue(), false);
						}
						if (newParentLevels != null) {
							for (Leveldto newLevel : newParentLevels) {
								if (customerBeanList.isEmpty()
										|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
									customerBeanList.add(newLevel.getRelationshipLevelSid());
									selectedCustomerContainer.addBean(newLevel);
									if (selecetedLevel != newLevel.getLevelNo()) {
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
										if (selecetedLevel != newLevel.getLevelNo()) {
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

		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	protected void moveAllProductButtonLogic() {
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			int selectedForecastLevel = 0;
			int prodHierarchyVersionNo = Integer.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
			int prodRelationVersionNo = Integer
					.parseInt(productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
			if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {
				if (productlevelDdlb.getValue() != null) {
					selectedForecastLevel = UiUtils
							.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]);
				}
			} else if (productLevel.getValue() != null) {
				selectedForecastLevel = UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]);
			}

			if (availableProductContainer.size() > 0) {
				List<Leveldto> items = new ArrayList<>(availableProductContainer.getItemIds());
				Object selectedItem = null;
				if (selectedProductContainer.size() > 0) {
					if (selectedProduct.getValue() != null) {
						selectedItem = selectedProduct.getValue();
						for (Leveldto item : items) {
							if (DataSelectionUtil.getBeanFromId(item).getLevelNo() > DataSelectionUtil
									.getBeanFromId(selectedItem).getLevelNo()) {

								String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
								if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
									hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
								}
								List<String> hierarchyNoList = new ArrayList<>();
								List<Leveldto> newParentLevels = null;
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
								}
								newParentLevels = logic.getParentLevelsWithHierarchyNo(
										UiUtils.stringListToString(uncommonValues), productDescMap,
										prodHierarchyVersionNo, prodRelationVersionNo);
								if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

									newChildLevels = logic.getChildLevelsWithHierarchyNo(
											UiUtils.parseStringToInteger(
													String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
											productDescMap, businessUnit.getValue(),
											DataSelectionUtil.getBeanFromId(item), prodHierarchyVersionNo,
											prodRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
													String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
											forecastEligibleDate.getValue(), true);
								} else {
									newChildLevels = logic.getChildLevelsWithHierarchyNo(
											UiUtils.parseStringToInteger(
													String.valueOf(productLevel.getValue()).split("-")[0]),
											productDescMap, businessUnit.getValue(),
											DataSelectionUtil.getBeanFromId(item), prodHierarchyVersionNo,
											prodRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
													String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
											forecastEligibleDate.getValue(), true);
								}
								if (newParentLevels != null) {
									int pos2 = 0;
									String parentHieNo;
									Leveldto parent = null;
									for (Leveldto newLevel : newParentLevels) {
										String tempHNo = newLevel.getHierarchyNo();
										if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
											tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
										}
										pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
										if (pos2 != -1) {
											parentHieNo = tempHNo.substring(0, pos2) + ".";
										} else {
											parentHieNo = tempHNo + ".";
										}
										if (productBeanList.isEmpty()
												|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
											productBeanList.add(newLevel.getRelationshipLevelSid());
											selectedProductContainer.addBean(newLevel);
											if (selectedForecastLevel != newLevel.getLevelNo()) {
												selectedProductContainer.setChildrenAllowed(newLevel, true);
											} else {
												selectedProductContainer.setChildrenAllowed(newLevel, false);
											}
										}

										if (!StringUtils.isBlank(parentHieNo)) {
											for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
												if (parentHieNo
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
												if (selectedForecastLevel != newLevel.getLevelNo()) {
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

						for (Leveldto item : items) {
							String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
								hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
							}
							List<String> hierarchyNosList = new ArrayList<>();
							List<Leveldto> newParentLevelsList = null;
							List<Leveldto> newChildLevelsList = null;
							hierarchyNosList.add(hierarchyNo + ".");
							int pos = 0;
							String selectedParentHierarchyNo = StringUtils.EMPTY;
							Leveldto selectedParent = null;
							while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
								pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos != -1) {
									hierarchyNo = hierarchyNo.substring(0, pos);
								}
								hierarchyNosList.add(hierarchyNo + ".");
							}
							Collections.reverse(hierarchyNosList);
							List<String> selectedHierarchyNos = new ArrayList<>();
							for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
								if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
									selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
								}
							}
							List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNosList,
									selectedHierarchyNos);
							List<String> removeValuesList = new ArrayList<>();
							for (String uncommonHierValue : uncommonValues) {
								if (selectedHierarchyNos.contains(uncommonHierValue)) {
									removeValuesList.add(uncommonHierValue);
								}
							}
							if (!removeValuesList.isEmpty()) {
								uncommonValues.removeAll(removeValuesList);
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
								newParentLevelsList = logic.getParentLevelsWithHierarchyNo(
										UiUtils.stringListToString(uncommonValues), productDescMap,
										prodHierarchyVersionNo, prodRelationVersionNo);
							}
							if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

								newChildLevelsList = logic.getChildLevelsWithHierarchyNo(
										UiUtils.parseStringToInteger(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
										productDescMap, businessUnit.getValue(),
										DataSelectionUtil.getBeanFromId(item), prodHierarchyVersionNo,
										prodRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
										forecastEligibleDate.getValue(), true);
							} else {
								newChildLevelsList = logic.getChildLevelsWithHierarchyNo(
										UiUtils.parseStringToInteger(
												String.valueOf(productLevel.getValue()).split("-")[0]),
										productDescMap, businessUnit.getValue(),
										DataSelectionUtil.getBeanFromId(item), prodHierarchyVersionNo,
										prodRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
										forecastEligibleDate.getValue(), true);
							}
							if (newParentLevelsList != null) {
								for (Leveldto newLevel : newParentLevelsList) {
									if (productBeanList.isEmpty()
											|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
										productBeanList.add(newLevel.getRelationshipLevelSid());
										selectedProductContainer.addBean(newLevel);
										if (selectedForecastLevel != newLevel.getLevelNo()) {
											selectedProductContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedProductContainer.setChildrenAllowed(newLevel, false);
										}
										selectedProductContainer.setParent(newLevel, selectedParent);
										selectedParent = newLevel;
									}
								}
								if (!newChildLevelsList.isEmpty()) {
									int pos3 = 0;
									String childHierarchyNo;
									Leveldto childsParent = null;
									for (Leveldto newLevel : newChildLevelsList) {
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
											if (selectedForecastLevel != newLevel.getLevelNo()) {
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
							.valueOf(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)
									? productlevelDdlb.getValue() : productLevel.getValue());
					if (tempProductLevel != null && UiUtils.parseStringToInteger(tempProductLevel) == 1) {

						for (Leveldto item : items) {
							selectedProductContainer.removeAllItems();
							selectedProduct.removeAllItems();
							Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);

							String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
								hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
							}
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
								if (selectedForecastLevel != selectedParent.getLevelNo()) {
									selectedProductContainer.setChildrenAllowed(selectedParent, true);
								} else {
									selectedProductContainer.setChildrenAllowed(selectedParent, false);
								}
								availableProductContainer.removeItem(selectedParent);
								availableProduct.removeItem(selectedParent);
							}
							if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

								newChildLevels = logic.getChildLevelsWithHierarchyNo(
										UiUtils.parseStringToInteger(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
										productDescMap, businessUnit.getValue(),
										DataSelectionUtil.getBeanFromId(item), prodHierarchyVersionNo,
										prodRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
										forecastEligibleDate.getValue(), true);
							} else {
								newChildLevels = logic.getChildLevelsWithHierarchyNo(
										UiUtils.parseStringToInteger(
												String.valueOf(productLevel.getValue()).split("-")[0]),
										productDescMap, businessUnit.getValue(),
										DataSelectionUtil.getBeanFromId(item), prodHierarchyVersionNo,
										prodRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
										forecastEligibleDate.getValue(), true);
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
										if (selectedForecastLevel != newLevel.getLevelNo()) {
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

						for (Leveldto item : items) {
							String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
								hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
							}
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
										UiUtils.stringListToString(uncommonValues), productDescMap,
										prodHierarchyVersionNo, prodRelationVersionNo);
							}
							if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

								newChildLevels = logic.getChildLevelsWithHierarchyNo(
										UiUtils.parseStringToInteger(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
										productDescMap, businessUnit.getValue(),
										DataSelectionUtil.getBeanFromId(item), prodHierarchyVersionNo,
										prodRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
										forecastEligibleDate.getValue(), true);
							} else {
								newChildLevels = logic.getChildLevelsWithHierarchyNo(
										UiUtils.parseStringToInteger(
												String.valueOf(productLevel.getValue()).split("-")[0]),
										productDescMap, businessUnit.getValue(),
										DataSelectionUtil.getBeanFromId(item), prodHierarchyVersionNo,
										prodRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
										forecastEligibleDate.getValue(), true);
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
										if (selectedForecastLevel != newLevel.getLevelNo()) {
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
											if (selectedForecastLevel != newLevel.getLevelNo()) {
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
			if (dismantleCustSelection) {
				triggerCustGrpOnView(dataSelectionDTO.getCustomerGrpSid(), false);
				dismantleCustSelection = false;
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
				List<Leveldto> levelList;
				Object selectedItem = selectedProduct.getValue();
				Leveldto selectedLevel = (Leveldto) DataSelectionUtil.getBeanFromId(selectedItem);
				String levelInString = DASH;
				if (!String.valueOf(productlevelDdlb.getValue()).equalsIgnoreCase(String.valueOf(SELECT_ONE))) {
					levelInString = String.valueOf(productlevelDdlb.getValue());
				}
				int currentLevel = UiUtils.parseStringToInteger(levelInString);
				if ((currentLevel != 0 && selectedLevel.getLevelNo() == currentLevel)
						&& (Constant.NDC.equalsIgnoreCase(selectedLevel.getLevel()))) {
					levelList = DataSelectionUtil.getFSValue(selectedLevel.getRelationshipLevelValue(),
							selectedLevel.getFieldName());
					selectedLevel.setForm(StringUtils.EMPTY + levelList.get(0).getForm());
					selectedLevel.setStrength(StringUtils.EMPTY + levelList.get(0).getStrength());

				}
				DataSelectionUtil.removeItemsRecursively(selectedItem, selectedProduct, selectedProductContainer);
				selectedProductContainer.removeItem(selectedLevel);
				selectedProduct.removeItem(selectedItem);
				if (dismantleProdSelection) {
					triggerProdGrpOnView(dataSelectionDTO.getProdGrpSid(), false);
					dismantleProdSelection = false;
				}
				productBeanList.clear();
				List<Leveldto> productListValue = selectedProductContainer.getItemIds();
				for (Leveldto levelDto : productListValue) {
					productBeanList.add(levelDto.getRelationshipLevelSid());
				}
			} else {
				AbstractNotificationUtils.getErrorNotification("No Product hierarchy level Selected",
						"No Level was selected to move. Please try again. ");
			}

		} catch (Exception ex) {
			LOGGER.error(" in moveRightProduct= {}",ex);
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
			if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {
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
									UiUtils.stringListToString(uncommonValues), productDescMap,
									productHierarchyVersionNo, productRelationVersionNo);
						}
						if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

							newChildLevels = logic.getChildLevelsWithHierarchyNo(
									UiUtils.parseStringToInteger(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
									productDescMap, businessUnit.getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
									forecastEligibleDate.getValue(), true);
						} else {
							newChildLevels = logic.getChildLevelsWithHierarchyNo(
									UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
									productDescMap, businessUnit.getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
									forecastEligibleDate.getValue(), true);
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
									UiUtils.stringListToString(uncommonValues), productDescMap,
									productHierarchyVersionNo, productRelationVersionNo);
						}
						if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

							newChildLevels = logic.getChildLevelsWithHierarchyNo(
									UiUtils.parseStringToInteger(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
									productDescMap, businessUnit.getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
									forecastEligibleDate.getValue(), true);
						} else {
							newChildLevels = logic.getChildLevelsWithHierarchyNo(
									UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
									productDescMap, businessUnit.getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo, UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
									forecastEligibleDate.getValue(), true);
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
							productDescMap, productHierarchyVersionNo, productRelationVersionNo);
					if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								productDescMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), true);
					} else {
						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
								productDescMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), true);
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
					if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {

						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								productDescMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), true);
					} else {
						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
								productDescMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), true);
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

		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
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
				resultTable.setVisibleColumns(TableHeaderColumnsUtil.getDataSelectionColumns());
				resultTable.setColumnHeaders(TableHeaderColumnsUtil.getDataSelectionHeaders());
			} else {
				bindDataselectionDtoToSave();
				dataSelectionDTO
						.setSelectedCustomerRelationSid(getRelationshipSid(selectedCustomerContainer.getItemIds()));
				dataSelectionDTO
						.setSelectedProductRelationSid(getRelationshipSid(selectedProductContainer.getItemIds()));
				dataSelectionDTO.setModulName(scrName);
				if (tableLogic.fireSetData(dataSelectionDTO, false)) {
					resultTable.setRefresh(BooleanConstant.getFalseFlag());

					setTableHeader();
					resultTable.setConverter("createdDateSearch", new DateToStringConverter());
					resultTable.setConverter("modifiedDateSearch", new DateToStringConverter());
					resultTable.setRefresh(BooleanConstant.getTrueFlag());
				} else {
					setTableHeader();
					AbstractNotificationUtils.getErrorNotification(
							Constants.MessageConstants.NO_RECORDS_TITLE.getConstant(),
							"There were no records matching the search criteria.  Please try again.");
				}
			}

		} catch (Exception ex) {

			LOGGER.error(" searchBtn= {}",ex);
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
					try {
						if (dto != null) {
							final String flag = NonMandatedLogic.deleteView(Integer.parseInt(dto.getViewId()));
							resetOne();
							deductionLevel.setValue(null);
							deductionValue.setValue(null);
							modeOption.select("Add");
							dismantleCustSelection = true;
							dismantleProdSelection = true;
							resetButtonLogic();
							if (!Constant.FAIL.equals(flag)) {
								CommonUIUtils
										.getMessageNotification(dto.getViewName() + " has been successfully deleted.");
							}
							deleteViewBtn.setEnabled(false);
							viewDTO = new ViewDTO();
						}

					} catch (SystemException | PortalException | Property.ReadOnlyException | NumberFormatException sysException) {
						LOGGER.error(sysException.getMessage());
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
                long startTime = System.currentTimeMillis();
		if (resultTable.getValue() == null) {
			AbstractNotificationUtils.getErrorNotification(Constant.SELECT_RECORD1,
					NO_RECORD_WAS_SELECTED_PLEASE_TRY_AGAIN);
		} else {
			try {
				Map<String, String> tempCustomerDescriptionMap;
				Map<String, String> tempProductDescriptionMap;
				final DataSelectionDTO dto = (DataSelectionDTO) resultTable.getValue();
				int projectionIdValue = dto.getProjectionId();
                                Map<Object, Object> map = new NMProjectionVarianceLogic().getNMProjectionSelection(projectionIdValue, TAB_DISCOUNT_PROJECTION.getConstant());
                                Object mapValue = map.get(Constant.DEDUCTION_LEVEL_DDLB);
				VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, projectionIdValue);
				relationLogic.waitForAutomaticRelation();
				boolean isRelationShipIsUpdated = relationLogic.isRelationUPdated(projectionIdValue);
				 if (isRelationShipIsUpdated) {
					AbstractNotificationUtils.getInfoNotification("Info",
							"Relationship used in this projection is updated");
				}
				final SessionDTO tempSession = SessionUtil.createSession();
				tempSession.setScreenName(scrName);
                                tempSession.setFunctionMode("E");
                                tempSession.setDsFrequency(String.valueOf(map.get(Constant.FREQUENCY)));
				tempSession.setProjectionId(projectionIdValue);
				tempSession.setCustomerHierarchyVersion(dto.getCustomerHierVersionNo());
				tempSession.setProductHierarchyVersion(dto.getProductHierVersionNo());
				tempSession.setCustomerRelationVersion(dto.getCustomerRelationShipVersionNo());
				tempSession.setProductRelationVersion(dto.getProductRelationShipVersionNo());
				tempSession.setDeductionRelationVersion(dto.getDeductionRelationShipVersionNo());
                                tempSession.setDataSelectionDeductionLevel(String.valueOf(CommonUtil.nullCheck(mapValue) || CommonUtil.stringNullCheck(mapValue) ? 1 : DataTypeConverter.convertObjectToInt(mapValue)));
                                dto.setDataSelectionDeductionLevelSid(Integer.parseInt(tempSession.getDataSelectionDeductionLevel()));
                                tempSession.setDataSelectionDeductionLevelCaption(dataSelectionDeductionLevel.getItemCaption(Integer.valueOf(tempSession.getDataSelectionDeductionLevel())));
                                tempSession.setDeductionLevel(String.valueOf(tempSession.getDataSelectionDeductionLevel()));
				tempCustomerDescriptionMap = relationLogic.getLevelValueMap(dto.getCustRelationshipBuilderSid(),
						Integer.parseInt(dto.getCustomerHierSid()), dto.getCustomerHierVersionNo(),
						dto.getCustomerRelationShipVersionNo());
				tempProductDescriptionMap = relationLogic.getLevelValueMap(dto.getProdRelationshipBuilderSid(),
						Integer.parseInt(dto.getProdHierSid()), dto.getProductHierVersionNo(),
						dto.getProductRelationShipVersionNo());
				if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(scrName)
						|| CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(scrName)) {
					// To create the temp tables with userId and session id
					QueryUtils.createTempTables(tempSession);
					nmLogic.loadPFDFromMainToTemp(tempSession);

					int customerSelectedLevel = Integer.parseInt(dto.getCustomerHierarchyInnerLevel());
					int productSelectedLeve = Integer.parseInt(dto.getProductHierarchyInnerLevel());
					List<Leveldto> customerItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
							BooleanConstant.getTrueFlag(), customerSelectedLevel, tempCustomerDescriptionMap);
					List<Leveldto> productItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
							BooleanConstant.getFalseFlag(), productSelectedLeve, tempProductDescriptionMap);
					topLevelName = dsLogic.getTopLevelInHierarchy(dto.getCustomerHierSid());
					customerHierarchyLevelDefinitionList = relationLogic.getHierarchyLevelDefinition(
							Integer.parseInt(dto.getCustomerHierSid()), dto.getCustomerHierVersionNo());
					productHierarchyLevelDefinitionList = relationLogic.getHierarchyLevelDefinition(
							Integer.parseInt(dto.getProdHierSid()), dto.getProductHierVersionNo());
					if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(scrName)) {

						relationLogic.ccpHierarchyInsert(tempSession.getCurrentTableNames(), customerItemIds,
								productItemIds, dto);

					} else {
						HelperListUtil helperUtil = HelperListUtil.getInstance();
						String dedLevel = getDedutionLevel(dto.getDeductionLevel());
						String dedValue = String
								.valueOf(helperUtil.getIdByDescription(dto.getDeductionValue(), dedLevel));
						relationLogic.ccpHierarchyInsertARP(tempSession.getCurrentTableNames(), customerItemIds,
								productItemIds, dto, dedLevel, dedValue);
					}
				}
				tempSession.setProjectionId(projectionIdValue);
				tempSession.setAction(Constant.EDIT_SMALL);

				tempSession.setCustomerRelationId(Integer.parseInt(dto.getCustRelationshipBuilderSid()));
				tempSession.setProductRelationId(Integer.parseInt(dto.getProdRelationshipBuilderSid()));
				tempSession.setProdRelationshipBuilderSid(dto.getProdRelationshipBuilderSid());
				tempSession.setCustomerHierarchyId(Integer.parseInt(dto.getCustomerHierSid()));
				tempSession.setProductHierarchyId(Integer.parseInt(dto.getProdHierSid()));
				tempSession.setCustomRelationShipSid(dto.getCustomRelationShipSid());
				tempSession.setCustomDeductionRelationShipSid(dto.getCustomDeductionRelationShipSid());
                                tempSession.setBusineesUnit(businessUnitlist);
				tempSession.setProjectionName(dto.getProjectionName());
                                String contractIds=dsLogic.getremovedcontractbasedonEligibleDate(tempSession);
                                if (contractIds != null && !contractIds.isEmpty()) {
                                MessageBox.showPlain(Icon.QUESTION, "Info", contractIds + " is removed from the projection as it is not eligible to be brought into the projection" + "\"",
                                        new MessageBoxListener() {
                                    @SuppressWarnings("PMD")
                                    @Override
                                    public void buttonClicked(final ButtonId buttonId) {
                                        return;
                                    }
                                }, ButtonId.OK);
                            }
                                tempSession.setForecastEligibleDate(dto.getForecastEligibleDate());
				tempSession.setDiscount(dto.getDiscount());
				tempSession.setDiscountTypeId(dto.getDiscountSid());
				tempSession.setProductLevelNumber(dto.getProductHierarchyLevel());
				tempSession.setDescription(dto.getDescription());
                                    if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(scrName)) {
					tempSession.setCustomerLevelDetails(
							dsLogic.getLevelValueDetails(tempSession, dto.getCustRelationshipBuilderSid(), true));
			                tempSession.setProductLevelDetails(
                                        dsLogic.getLevelValueDetails(tempSession, dto.getProdRelationshipBuilderSid(), false));
                                       
                                        tempSession.setSalesHierarchyLevelDetails(
                                        dsLogic.getRelationshipDetailsCustom(tempSession, String.valueOf(tempSession.getCustomRelationShipSid())));
                                        tempSession.setDiscountCustomerProductLevelDetails(
                                        dsLogic.getRelationshipDetailsCustom(tempSession, String.valueOf(tempSession.getCustomDeductionRelationShipSid())));
                                  
                                tempSession.setCustomerDescription(tempCustomerDescriptionMap);
                                tempSession.setProductDescription(tempProductDescriptionMap);
                            } else {
                                tempSession.setCustomerDescription(tempCustomerDescriptionMap);
                                tempSession.setProductDescription(tempProductDescriptionMap);
                            }
                            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(scrName)) {
                                tempSession.setCustRelationshipBuilderSid(dto.getCustRelationshipBuilderSid());
                                tempSession.setProdRelationshipBuilderSid(dto.getProdRelationshipBuilderSid());
                                if (CommonUtil.isValueEligibleForLoading()) {
                                    Object[] obj = nmLogic.deductionRelationBuilderId(dto.getProdRelationshipBuilderSid());
                                    tempSession.setDedRelationshipBuilderSid(obj[0].toString());
                                    }
                                if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(scrName)) {
                                    if (dataLogic.isFileChanged(tempSession) == 0) {
                                        MessageBox.showPlain(Icon.QUESTION, "New File is Activated in the File Management module",
                                                "There is a new file " + "[ " + tempSession.getFileName() + " ]\n"
                                                + " that has been activated. \n"
                                                + "Please re-calculate Sales and Discount projections to utilize the new values.",
                                                new MessageBoxListener() {
                                            @SuppressWarnings("PMD")
                                            @Override
                                            public void buttonClicked(final ButtonId buttonId) {
                                                return;
                                            }
                                        }, ButtonId.OK);
                                    }
                                }
                                ForecastWindow forecastWindow = new ForecastWindow(dto.getProjectionName(), tempSession,
							resultTable, scrName, this, dto);
					UI.getCurrent().addWindow(forecastWindow);
                                        long endTime = System.currentTimeMillis();
                                        LOGGER.info("DataSelection  ---- :{}" , (endTime - startTime)/1000);
                                        
				} else if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(scrName)) {
					ForecastEditWindow editWindow = new ForecastEditWindow(dto.getProjectionName(), tempSession,
							resultTable, scrName, this);
					UI.getCurrent().addWindow(editWindow);
				} else if (!editnotif) {
					editnotif = true;
					tempSession.setDeductionLevel(dto.getDeductionLevel());
					tempSession.setDeductionValue(dto.getDeductionValue());
					tempSession.setIsFileNotChanged(DSLogic.getFileStatus(projectionIdValue));
					if (!tempSession.isFileNotChanged()) {
							MessageBox.showPlain(Icon.QUESTION, "Alert",
									"A new Customer Gross Trade Sales file has been activated since this workflow was last saved. Would you like this workflow to be updated based on the new active file?",
									new MessageBoxListener() {

										@SuppressWarnings("PMD")
                                                                                @Override
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
					} else {
						tempSession.setIsNewFileCalculationNeeded(false);
						callARPView(dto, tempSession);
					}
				}
			} catch (Exception ex) {
				
				LOGGER.error(" - in editBtn= {}",ex);
			}
		}
	}
        
	public void editButtonLogicNewArch(GtnFrameworkForecastInputBean inputBean) {
                long startTime = System.currentTimeMillis();
//		if (resultTable.getValue() == null) {
//			AbstractNotificationUtils.getErrorNotification(Constant.SELECT_RECORD1,
//					NO_RECORD_WAS_SELECTED_PLEASE_TRY_AGAIN);
//		} else {
			try {
				Map<String, String> tempCustomerDescriptionMap;
				Map<String, String> tempProductDescriptionMap;
                                
				final DataSelectionDTO dto = bindDataselectionDtoToSaveNewArch(inputBean);
                                dto.setProjectionId(inputBean.getProjectionMasterSid());
				int projectionIdValue = dto.getProjectionId();
                                Map<Object, Object> map = new NMProjectionVarianceLogic().getNMProjectionSelection(projectionIdValue, TAB_DISCOUNT_PROJECTION.getConstant());
                                Object mapValue = map.get(Constant.DEDUCTION_LEVEL_DDLB);
				VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, projectionIdValue);
				relationLogic.waitForAutomaticRelation();
				boolean isRelationShipIsUpdated = relationLogic.isRelationUPdated(projectionIdValue);
				 if (isRelationShipIsUpdated) {
					AbstractNotificationUtils.getInfoNotification("Info",
							"Relationship used in this projection is updated");
				}
				final SessionDTO tempSession = SessionUtil.createSession();
                                tempSession.setUserId(inputBean.getUserId());
                                tempSession.setSessionId(tempSession.getSessionId());
				tempSession.setScreenName(scrName);
                                tempSession.setFunctionMode("E");
                                tempSession.setDsFrequency(String.valueOf(map.get(Constant.FREQUENCY)));
				tempSession.setProjectionId(projectionIdValue);
				tempSession.setCustomerHierarchyVersion(dto.getCustomerHierVersionNo());
				tempSession.setProductHierarchyVersion(dto.getProductHierVersionNo());
				tempSession.setCustomerRelationVersion(dto.getCustomerRelationShipVersionNo());
				tempSession.setProductRelationVersion(dto.getProductRelationShipVersionNo());
				tempSession.setDeductionRelationVersion(dto.getDeductionRelationShipVersionNo());
                                tempSession.setDataSelectionDeductionLevel(String.valueOf(CommonUtil.nullCheck(mapValue) || CommonUtil.stringNullCheck(mapValue) ? 1 : DataTypeConverter.convertObjectToInt(mapValue)));
                                dto.setDataSelectionDeductionLevelSid(Integer.parseInt(tempSession.getDataSelectionDeductionLevel()));
                                tempSession.setDataSelectionDeductionLevelCaption(inputBean.getDeductionValue());
                                tempSession.setDeductionLevel(String.valueOf(tempSession.getDataSelectionDeductionLevel()));
				tempCustomerDescriptionMap = relationLogic.getLevelValueMapOldArch(dto.getCustRelationshipBuilderSid(),
						Integer.parseInt(dto.getCustomerHierSid()), dto.getCustomerHierVersionNo(),
						dto.getCustomerRelationShipVersionNo());
				tempProductDescriptionMap = relationLogic.getLevelValueMapOldArch(dto.getProdRelationshipBuilderSid(),
						Integer.parseInt(dto.getProdHierSid()), dto.getProductHierVersionNo(),
						dto.getProductRelationShipVersionNo());
				if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(scrName)
						|| CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(scrName)
						) {
					// To create the temp tables with userId and session id
					QueryUtils.createTempTables(tempSession);
					nmLogic.loadPFDFromMainToTemp(tempSession);

					int customerSelectedLevel = inputBean.getCustomerRelationLevel();
					int productSelectedLeve = inputBean.getProductRelationLevel();
					List<Leveldto> customerItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
							BooleanConstant.getTrueFlag(), customerSelectedLevel, tempCustomerDescriptionMap);
					List<Leveldto> productItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
							BooleanConstant.getFalseFlag(), productSelectedLeve, tempProductDescriptionMap);
					topLevelName = dsLogic.getTopLevelInHierarchy(dto.getCustomerHierSid());
					customerHierarchyLevelDefinitionList = relationLogic.getHierarchyLevelDefinition(
							Integer.parseInt(dto.getCustomerHierSid()), dto.getCustomerHierVersionNo());
					productHierarchyLevelDefinitionList = relationLogic.getHierarchyLevelDefinition(
							Integer.parseInt(dto.getProdHierSid()), dto.getProductHierVersionNo());
					if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(scrName)) {

						relationLogic.ccpHierarchyInsert(tempSession.getCurrentTableNames(), customerItemIds,
								productItemIds, dto);

					} else {
						HelperListUtil helperUtil = HelperListUtil.getInstance();
						String dedLevel = getDedutionLevel(dto.getDeductionLevel());
						String dedValue = String
								.valueOf(helperUtil.getIdByDescription(dto.getDeductionValue(), dedLevel));
						relationLogic.ccpHierarchyInsertARP(tempSession.getCurrentTableNames(), customerItemIds,
								productItemIds, dto, dedLevel, dedValue);
					}
				}
				tempSession.setProjectionId(projectionIdValue);
				tempSession.setAction(Constant.EDIT_SMALL);

				tempSession.setCustomerRelationId(Integer.parseInt(dto.getCustRelationshipBuilderSid()));
				tempSession.setProductRelationId(Integer.parseInt(dto.getProdRelationshipBuilderSid()));
				tempSession.setProdRelationshipBuilderSid(dto.getProdRelationshipBuilderSid());
				tempSession.setCustomerHierarchyId(Integer.parseInt(dto.getCustomerHierSid()));
				tempSession.setProductHierarchyId(Integer.parseInt(dto.getProdHierSid()));
				tempSession.setCustomRelationShipSid(dto.getCustomRelationShipSid());
				tempSession.setCustomDeductionRelationShipSid(dto.getCustomDeductionRelationShipSid());
                                tempSession.setBusineesUnit(businessUnitlist);
				tempSession.setProjectionName(dto.getProjectionName());
                                String contractIds=dsLogic.getremovedcontractbasedonEligibleDate(tempSession);
                                if (contractIds != null && !contractIds.isEmpty()) {
                                MessageBox.showPlain(Icon.QUESTION, "Info", contractIds + " is removed from the projection as it is not eligible to be brought into the projection" + "\"",
                                        new MessageBoxListener() {
                                    @SuppressWarnings("PMD")
                                    @Override
                                    public void buttonClicked(final ButtonId buttonId) {
                                        return;
                                    }
                                }, ButtonId.OK);
                            }
                                tempSession.setForecastEligibleDate(dto.getForecastEligibleDate());
//				if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equalsIgnoreCase(scrName)) {
//					String marketType = dataLogic.getHelperValue(StringUtils.EMPTY + projectionIdValue);
//					tempSession.setMarketTypeValue(marketType);
//				}
				tempSession.setDiscount(dto.getDiscount());
				tempSession.setDiscountTypeId(dto.getDiscountSid());
				tempSession.setProductLevelNumber(dto.getProductHierarchyLevel());
				tempSession.setDescription(dto.getDescription());
//				if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(scrName)) {
//					// To create the temp tables with userId and session id
//					QueryUtils.createTempTables(tempSession);
//					tempSession.setProductDescription(tempProductDescriptionMap); // Fix
//					// for
//					// GAL-8786
//					tempSession.setReturnsDetailsMap(dsLogic.getReturnDetails(tempSession, false));
//				} 
                             if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(scrName)
						) {
					tempSession.setCustomerLevelDetails(
							dsLogic.getLevelValueDetails(tempSession, dto.getCustRelationshipBuilderSid(), true));
			                tempSession.setProductLevelDetails(
                                        dsLogic.getLevelValueDetails(tempSession, dto.getProdRelationshipBuilderSid(), false));
                                       
                                        tempSession.setSalesHierarchyLevelDetails(
                                        dsLogic.getRelationshipDetailsCustom(tempSession, String.valueOf(tempSession.getCustomRelationShipSid())));
                                        tempSession.setDiscountCustomerProductLevelDetails(
                                        dsLogic.getRelationshipDetailsCustom(tempSession, String.valueOf(tempSession.getCustomDeductionRelationShipSid())));
                                  
                                tempSession.setCustomerDescription(tempCustomerDescriptionMap);
                                tempSession.setProductDescription(tempProductDescriptionMap);
                            } else {
                                tempSession.setCustomerDescription(tempCustomerDescriptionMap);
                                tempSession.setProductDescription(tempProductDescriptionMap);
                            }
                            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(scrName)) {
                                tempSession.setCustRelationshipBuilderSid(dto.getCustRelationshipBuilderSid());
                                tempSession.setProdRelationshipBuilderSid(dto.getProdRelationshipBuilderSid());
                                if (CommonUtil.isValueEligibleForLoading()) {
                                    Object[] obj = nmLogic.deductionRelationBuilderId(dto.getProdRelationshipBuilderSid());
                                    tempSession.setDedRelationshipBuilderSid(obj[0].toString());
                                    }
                                if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(scrName)) {
                                    if (dataLogic.isFileChanged(tempSession) == 0) {
                                        MessageBox.showPlain(Icon.QUESTION, "New File is Activated in the File Management module",
                                                "There is a new file " + "[ " + tempSession.getFileName() + " ]\n"
                                                + " that has been activated. \n"
                                                + "Please re-calculate Sales and Discount projections to utilize the new values.",
                                                new MessageBoxListener() {
                                            @SuppressWarnings("PMD")
                                            @Override
                                            public void buttonClicked(final ButtonId buttonId) {
                                                return;
                                            }
                                        }, ButtonId.OK);
                                    }
                                }
                                ForecastWindow forecastWindow = new ForecastWindow(dto.getProjectionName(), tempSession,
							resultTable, scrName, this, dto);
					UI.getCurrent().addWindow(forecastWindow);
                                        long endTime = System.currentTimeMillis();
                                        LOGGER.info("DataSelection  ---- :{}" , (endTime - startTime)/1000);
                                        
				} else if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(scrName)) {
					ForecastEditWindow editWindow = new ForecastEditWindow(dto.getProjectionName(), tempSession,
							resultTable, scrName, this);
					UI.getCurrent().addWindow(editWindow);
				} else if (!editnotif) {
					editnotif = true;
					tempSession.setDeductionLevel(dto.getDeductionLevel());
					tempSession.setDeductionValue(dto.getDeductionValue());
					tempSession.setIsFileNotChanged(DSLogic.getFileStatus(projectionIdValue));
					if (!tempSession.isFileNotChanged()) {
							MessageBox.showPlain(Icon.QUESTION, "Alert",
									"A new Customer Gross Trade Sales file has been activated since this workflow was last saved. Would you like this workflow to be updated based on the new active file?",
									new MessageBoxListener() {

										@SuppressWarnings("PMD")
                                                                                @Override
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
					} else {
						tempSession.setIsNewFileCalculationNeeded(false);
						callARPView(dto, tempSession);
					}
				}
			} catch (Exception ex) {
				
				LOGGER.error(" - in editBtn= {}",ex);
			}
//		}
	}
        
	public void callARPView(DataSelectionDTO dto, SessionDTO session) {
		DSLogic dSLogic = new DSLogic();

		if (session.getCurrentTableNames().isEmpty() && !session.getAction().equalsIgnoreCase(Constant.VIEW)) {
			session.setScreenName(scrName);
			QueryUtils.createTempTables(session);
		}
		if (!session.getAction().equalsIgnoreCase(Constant.VIEW)) {
			dSLogic.insertAccrualTemp(session);
		}
		// Used to maintain the thread count
		session.setNumberOfThreads(4);
		AccrualRateProjectionView arpView = new AccrualRateProjectionView(StringUtils.EMPTY + dto.getProjectionId(),
				session, scrName, this, false);
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
                                Map<Object, Object> map = new NMProjectionVarianceLogic().getNMProjectionSelection(projectionIdValue, TAB_DISCOUNT_PROJECTION.getConstant());
                                Object mapValue = map.get(Constant.DEDUCTION_LEVEL_DDLB);
				SessionDTO session = SessionUtil.createSession();
				session.setProjectionId(projectionIdValue);
				session.setBusineesUnit(businessUnitlist);
				session.setProjectionName(dto.getProjectionName());
				session.setScreenName(scrName);
				session.setAction(Constant.VIEW);
                                session.setFunctionMode("E");
				session.setCustomerHierarchyVersion(dto.getCustomerHierVersionNo());
				session.setProductHierarchyVersion(dto.getProductHierVersionNo());
				session.setCustomerRelationVersion(dto.getCustomerRelationShipVersionNo());
				session.setProductRelationVersion(dto.getProductRelationShipVersionNo());
                                session.setDeductionRelationVersion(dto.getDeductionRelationShipVersionNo());
                                session.setDsFrequency(String.valueOf(frequency.getValue()));
                                 session.setCustomRelationShipSid(dto.getCustomRelationShipSid());
                                session.setCustomDeductionRelationShipSid(dto.getCustomDeductionRelationShipSid());
                                session.setDataSelectionDeductionLevel(String.valueOf(CommonUtil.nullCheck(mapValue) || CommonUtil.stringNullCheck(mapValue) ? 1 : DataTypeConverter.convertObjectToInt(mapValue)));
                                dto.setDataSelectionDeductionLevelSid(Integer.parseInt(session.getDataSelectionDeductionLevel()));
                                 session.setDataSelectionDeductionLevelCaption(dataSelectionDeductionLevel.getItemCaption(Integer.valueOf(session.getDataSelectionDeductionLevel())));
                                session.setDeductionLevel(String.valueOf(session.getDataSelectionDeductionLevel()));
				customerDescMap = relationLogic.getLevelValueMap(dto.getCustRelationshipBuilderSid(),
						Integer.parseInt(dto.getCustomerHierSid()), dto.getCustomerHierVersionNo(),
						dto.getCustomerRelationShipVersionNo());
				productDescMap = relationLogic.getLevelValueMap(dto.getProdRelationshipBuilderSid(),
						Integer.parseInt(dto.getProdHierSid()), dto.getProductHierVersionNo(),
						dto.getProductRelationShipVersionNo());
				if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(scrName)) {
					// To create the temp tables with userId and session id
					QueryUtils.createTempTables(session);
					topLevelName = dsLogic.getTopLevelInHierarchy(dto.getCustomerHierSid());
					int customerSelectedLevel = Integer.parseInt(dto.getCustomerHierarchyInnerLevel());
					int productSelectedLeve = Integer.parseInt(dto.getProductHierarchyInnerLevel());

					List<Leveldto> customerItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
							BooleanConstant.getTrueFlag(), customerSelectedLevel, customerDescMap);
					List<Leveldto> productItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
							BooleanConstant.getFalseFlag(), productSelectedLeve, productDescMap);

					customerHierarchyLevelDefinitionList = relationLogic.getHierarchyLevelDefinition(
							Integer.parseInt(dto.getCustomerHierSid()), dto.getCustomerHierVersionNo());
					productHierarchyLevelDefinitionList = relationLogic.getHierarchyLevelDefinition(
							Integer.parseInt(dto.getProdHierSid()), dto.getProductHierVersionNo());
					relationLogic.ccpHierarchyInsert(session.getCurrentTableNames(), customerItemIds, productItemIds,
							dto);
				}
				DataSelectionLogic logic = new DataSelectionLogic();
				session.setProductRelationId(Integer.parseInt(dto.getProdRelationshipBuilderSid()));
				session.setProductLevelNumber(dto.getProductHierarchyLevel());
                               

				if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(scrName)) {
					session.setCustomerLevelDetails(
							logic.getLevelValueDetails(session, dto.getCustRelationshipBuilderSid(), true));
					session.setProductLevelDetails(
							logic.getLevelValueDetails(session, dto.getProdRelationshipBuilderSid(), false));
                                        session.setSalesHierarchyLevelDetails(
                                        dsLogic.getRelationshipDetailsCustom(session, String.valueOf(session.getCustomRelationShipSid())));
                                        session.setDiscountCustomerProductLevelDetails(
                                        dsLogic.getRelationshipDetailsCustom(session, String.valueOf(session.getCustomDeductionRelationShipSid())));
                                }
                                session.setCustomerDescription(customerDescMap);
                                session.setProductDescription(productDescMap);
                            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(scrName)) {
                                session.setCustRelationshipBuilderSid(dto.getCustRelationshipBuilderSid());
                                session.setProdRelationshipBuilderSid(dto.getProdRelationshipBuilderSid());
                                if (CommonUtil.isValueEligibleForLoading()) {
                                    Object[] obj = nmLogic.deductionRelationBuilderId(dto.getProdRelationshipBuilderSid());
                                    session.setDedRelationshipBuilderSid(obj[0].toString());
                                    }
                                ForecastWindow forecastWindow = new ForecastWindow(dto.getProjectionName(), session, resultTable,
                                        scrName, this, dto);
                                UI.getCurrent().addWindow(forecastWindow);
                            } else if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(scrName)) {
                                ForecastEditWindow editWindow = new ForecastEditWindow(dto.getProjectionName(), session,
                                        resultTable, scrName, this);
                                UI.getCurrent().addWindow(editWindow);
                            } else {
                                session.setDeductionLevel(dto.getDeductionLevel());
                                session.setDeductionValue(dto.getDeductionValue());
                                session.setIsFileNotChanged(true);
                               	session.setIsNewFileCalculationNeeded(false);
					callARPView(dto, session);
				}

			} catch (Exception ex) {

				LOGGER.error(" - in View button= {}",ex);
			}

		}
	}
        
	public void viewButtonLogicNewArch(GtnFrameworkForecastInputBean inputBean) {
//		if (resultTable.getValue() == null) {
//			AbstractNotificationUtils.getErrorNotification(Constant.SELECT_RECORD1,
//					NO_RECORD_WAS_SELECTED_PLEASE_TRY_AGAIN);
//		} 
//                else {
			try {
				DataSelectionDTO dto = bindDataselectionDtoToSaveNewArch(inputBean);
				int projectionIdValue = inputBean.getProjectionMasterSid();
                                Map<Object, Object> map = new NMProjectionVarianceLogic().getNMProjectionSelection(projectionIdValue, TAB_DISCOUNT_PROJECTION.getConstant());
                                Object mapValue = map.get(Constant.DEDUCTION_LEVEL_DDLB);
				SessionDTO session = SessionUtil.createSession();
                                session.setUserId(inputBean.getUserId());
                                session.setSessionId(session.getSessionId());
                                dto.setProjectionId(inputBean.getProjectionMasterSid());
				session.setProjectionId(projectionIdValue);
				session.setBusineesUnit(businessUnitlist);
				session.setProjectionName(dto.getProjectionName());
				session.setScreenName(scrName);
				session.setAction(Constant.VIEW);
                                session.setFunctionMode("E");
				session.setCustomerHierarchyVersion(dto.getCustomerHierVersionNo());
				session.setProductHierarchyVersion(dto.getProductHierVersionNo());
				session.setCustomerRelationVersion(dto.getCustomerRelationShipVersionNo());
				session.setProductRelationVersion(dto.getProductRelationShipVersionNo());
                                session.setDeductionRelationVersion(dto.getDeductionRelationShipVersionNo());
                                session.setDsFrequency(String.valueOf(frequency.getValue()));
                                 session.setCustomRelationShipSid(dto.getCustomRelationShipSid());
                                session.setCustomDeductionRelationShipSid(dto.getCustomDeductionRelationShipSid());
                                session.setDataSelectionDeductionLevel(String.valueOf(CommonUtil.nullCheck(mapValue) || CommonUtil.stringNullCheck(mapValue) ? 1 : DataTypeConverter.convertObjectToInt(mapValue)));
                                dto.setDataSelectionDeductionLevelSid(Integer.parseInt(session.getDataSelectionDeductionLevel()));
                                 session.setDataSelectionDeductionLevelCaption("Schedule Category");
                                session.setDeductionLevel(String.valueOf(session.getDataSelectionDeductionLevel()));
				customerDescMap = relationLogic.getLevelValueMapOldArch(dto.getCustRelationshipBuilderSid(),
						Integer.parseInt(dto.getCustomerHierSid()), dto.getCustomerHierVersionNo(),
						dto.getCustomerRelationShipVersionNo());
				productDescMap = relationLogic.getLevelValueMapOldArch(dto.getProdRelationshipBuilderSid(),
						Integer.parseInt(dto.getProdHierSid()), dto.getProductHierVersionNo(),
						dto.getProductRelationShipVersionNo());
				if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(scrName)) {
					// To create the temp tables with userId and session id
					QueryUtils.createTempTables(session);
					topLevelName = dsLogic.getTopLevelInHierarchy(dto.getCustomerHierSid());
					int customerSelectedLevel = Integer.parseInt(dto.getCustomerHierarchyInnerLevel());
					int productSelectedLeve = Integer.parseInt(dto.getProductHierarchyInnerLevel());

					List<Leveldto> customerItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
							BooleanConstant.getTrueFlag(), customerSelectedLevel, customerDescMap);
					List<Leveldto> productItemIds = relationLogic.getRelationShipValues(dto.getProjectionId(),
							BooleanConstant.getFalseFlag(), productSelectedLeve, productDescMap);

					customerHierarchyLevelDefinitionList = relationLogic.getHierarchyLevelDefinition(
							Integer.parseInt(dto.getCustomerHierSid()), dto.getCustomerHierVersionNo());
					productHierarchyLevelDefinitionList = relationLogic.getHierarchyLevelDefinition(
							Integer.parseInt(dto.getProdHierSid()), dto.getProductHierVersionNo());
					relationLogic.ccpHierarchyInsert(session.getCurrentTableNames(), customerItemIds, productItemIds,
							dto);
				}
				DataSelectionLogic logic = new DataSelectionLogic();
				session.setProductRelationId(Integer.parseInt(dto.getProdRelationshipBuilderSid()));
				session.setProductLevelNumber(dto.getProductHierarchyLevel());
                               

				if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(scrName)) {
					session.setCustomerLevelDetails(
							logic.getLevelValueDetails(session, dto.getCustRelationshipBuilderSid(), true));
					session.setProductLevelDetails(
							logic.getLevelValueDetails(session, dto.getProdRelationshipBuilderSid(), false));
                                        session.setSalesHierarchyLevelDetails(
                                        dsLogic.getRelationshipDetailsCustom(session, String.valueOf(session.getCustomRelationShipSid())));
                                        session.setDiscountCustomerProductLevelDetails(
                                        dsLogic.getRelationshipDetailsCustom(session, String.valueOf(session.getCustomDeductionRelationShipSid())));
                                }
                                session.setCustomerDescription(customerDescMap);
                                session.setProductDescription(productDescMap);
                            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(scrName)) {
                                session.setCustRelationshipBuilderSid(dto.getCustRelationshipBuilderSid());
                                session.setProdRelationshipBuilderSid(dto.getProdRelationshipBuilderSid());
                                if (CommonUtil.isValueEligibleForLoading()) {
                                    Object[] obj = nmLogic.deductionRelationBuilderId(dto.getProdRelationshipBuilderSid());
                                    session.setDedRelationshipBuilderSid(obj[0].toString());
                                    }
                                ForecastWindow forecastWindow = new ForecastWindow(dto.getProjectionName(), session, resultTable,
                                        scrName, this, dto);
                                UI.getCurrent().addWindow(forecastWindow);
                            } else if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(scrName)) {
                                ForecastEditWindow editWindow = new ForecastEditWindow(dto.getProjectionName(), session,
                                        resultTable, scrName, this);
                                UI.getCurrent().addWindow(editWindow);
                            } else {
                                session.setDeductionLevel(dto.getDeductionLevel());
                                session.setDeductionValue(dto.getDeductionValue());
                                session.setIsFileNotChanged(true);
                               	session.setIsNewFileCalculationNeeded(false);
					callARPView(dto, session);
				}

			} catch (Exception ex) {

				LOGGER.error(" - in View button= {}",ex);
			}

//		}
	}

    public ComboBox getDataSelectionDeductionLevel() {
        return dataSelectionDeductionLevel;
    }

    public void setDataSelectionDeductionLevel(ComboBox dataSelectionDeductionLevel) {
        this.dataSelectionDeductionLevel = dataSelectionDeductionLevel;
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
											scrName);
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
								} catch (Property.ReadOnlyException ex) {
									LOGGER.error(" - in deleteBtn= {}",ex);
								}
							}
						}
					}
				}, ButtonId.YES, ButtonId.NO);

	}

	public void loadCustomerLevel(final String hierarchyId, final String innerLevel, final int hierarchyVersion) {
		LOGGER.debug("Logging - loadCustomerLevel hierarchyId= {}, innerLevel= {} " , hierarchyId, innerLevel);
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			innerCustLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY, hierarchyVersion);
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

		} catch (NumberFormatException ex) {
			LOGGER.error(" in loadCustomerLevel= {}",ex);
		}

	}

	public void loadProductLevel(final String hierarchyId, final String innerLevel, final int hierarchyVersion) {
		LOGGER.debug("Logging - loadProductLevel hierarchyId= {}, innerLevel= {} " , hierarchyId, innerLevel);
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			innerProdLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY, hierarchyVersion);
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

		} catch (NumberFormatException ex) {
			LOGGER.error(" loadProductLevel= {}",ex);
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

		StringBuilder returnString = new StringBuilder();

		List<String> productHierarchyEndLevelsHierNo = new ArrayList<>();
		for (Object item : selectedProductContainer.getItemIds()) {
			if (!selectedProductContainer.hasChildren(item)) {
				productHierarchyEndLevelsHierNo.add(DataSelectionUtil.getBeanFromId(item).getHierarchyNo());
			}
		}
		if (!productHierarchyEndLevelsHierNo.isEmpty()) {
			for (int loop = 0, limit = productHierarchyEndLevelsHierNo.size(); loop < limit; loop++) {
				returnString.append('\'');
				returnString.append(productHierarchyEndLevelsHierNo.get(loop));
				returnString.append('\'');
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
        
        
        private List<String> getNewArchRelationshipSid(List<GtnWsRecordBean> recordBeanList) {
		List<String> relationshipSids = new ArrayList<>();
		for (GtnWsRecordBean dto : recordBeanList) {
			relationshipSids.add(String.valueOf(dto.getPropertyValueByIndex(7)));
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
		reslistOne = relationLogic.getRelationShipValues(projectionId, BooleanConstant.getTrueFlag(), customerLevel,
				customerDescMap);
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
							parentLevel = Integer.parseInt(parentarr[0]);
						} catch (NumberFormatException nfe) {
							LOGGER.error("Error While loading the Customer level is not a valid number.= {}" , parentarr[0]);
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
		reslistOne = relationLogic.getRelationShipValues(projectionId, BooleanConstant.getFalseFlag(), productLevel,
				productDescMap);
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
							parentLevel = Integer.parseInt(parentarr[0]);
						} catch (NumberFormatException nfe) {
							LOGGER.error(
									"Error While loading the Product levelis not a valid number.= {}" , parentarr[0] );
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
		LOGGER.debug("Logging - loadInnerCustomerLevel forecastLevel= {}, innerlevel= {}, hierarchyId= {} " , forecastLevel, innerLevel
				, hierarchyId);
		customerInnerLevelContainer.removeAllItems();
		String selectedLevelName = StringUtils.EMPTY;
		for (int i = 1; i <= forecastLevel; i++) {
			String levelName = innerCustLevels.get(i - 1).getLevel();
			customerInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
			if (i == innerLevel) {
				selectedLevelName = levelName;
                                break;
			}
		}

		level.setContainerDataSource(customerInnerLevelContainer);
		level.select(Constant.LEVEL + innerLevel + " - " + selectedLevelName);
	}

	private void loadInnerProductLevel(int forecastLevel, int innerLevel, int hierarchyId) {
		LOGGER.debug("Logging - loadInnerProductLevel forecastLevel= {}, innerLevel= {}, hierarchyId= {} " , forecastLevel, innerLevel
				,hierarchyId);
		productInnerLevelContainer.removeAllItems();
		String selectedLevelName = StringUtils.EMPTY;
		for (int i = 1; i <= forecastLevel; i++) {
			String levelName = innerProdLevels.get(i - 1).getLevel();
			productInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
			if (i == innerLevel) {
				selectedLevelName = levelName;
                                break;
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

		} catch (SystemException | IOException | ClassNotFoundException | CloneNotSupportedException | NumberFormatException ex) {
			LOGGER.error(" at triggerCustGrpOnView= {}", ex);
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

		} catch (SystemException | NumberFormatException ex) {
			LOGGER.error(" at triggerProdGrpOnView= {}",ex);
		}
	}

	private List<Leveldto> getItemSidFromHierarchy() {
		List<Leveldto> innerLevelValues = null;
		String dedLevel = StringUtils.EMPTY;
		String dedValue = StringUtils.EMPTY;
		if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {
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

			if ((innerProdLevels != null) && (productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue()))) {

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
							true, ndcLevel.getFieldName(), relationshipSid, productDescMap,
							INDICATOR_LEVEL_NDC.getConstant(), scrName,
							0, ndcLevel.getLevelNo(), dedValue,
							dedLevel, company.getValue(), businessUnit.getValue());
				}
			}
		} catch (SystemException ex) {
			LOGGER.error(" in getItemSidFromHierarchy= {}", ex);
		}
		return innerLevelValues;
	}

	public static void loadRelationDdlb(int hierarchyDefinitionSid,
			final RelationshipDdlbDto selectedRelationshipDdlbDto, final ComboBox relationship) {
		LOGGER.debug("Logging - loadRelationDdlb hierarchyDefinitionSid= {} " , hierarchyDefinitionSid);
		try {
                    
                    
//			DataSelectionLogic logic = new DataSelectionLogic();
//			List<RelationshipDdlbDto> relationshipSidList = logic.getRelationshipSid(hierarchyDefinitionSid);
                        Map<Integer, List<GtnWsRelationshipBuilderBean>> relationshipMapper = new HashMap<>();
                        ObjectMapper mapper = new ObjectMapper();
                        if(getCustomerHierarchyLookupWindow()!=null){
                        if(getCustomerHierarchyLookupWindow().getHierarchyDto().getHierarchySelection().contains("customer")){
                         relationshipMapper = getCustomerHierarchyLookupWindow().getHierarchyDto().getRelationshipMap();
                         }
                        }
                        else{
                        if(customerHierarchyLookupWindow !=null && customerHierarchyLookupWindow.getHierarchyDto().getHierarchySelection().contains("customer")){
                         relationshipMapper = customerHierarchyLookupWindow.getHierarchyDto().getRelationshipMap();
                        }
                        }
                        
                        if(getProductHierarchyLookupWindow()!=null){
                           if(getProductHierarchyLookupWindow() !=null && getProductHierarchyLookupWindow().getHierarchyDto().getHierarchySelection().contains("product")){
                         relationshipMapper = getProductHierarchyLookupWindow().getHierarchyDto().getRelationshipMap();
                        } 
                        }
                        else{
                        if(productHierarchyLookupWindow !=null && productHierarchyLookupWindow.getHierarchyDto().getHierarchySelection().contains("product")){
                         relationshipMapper = productHierarchyLookupWindow.getHierarchyDto().getRelationshipMap();
                        }
                        }
			Map<Integer, List<GtnWsRelationshipBuilderBean>> relationshipMap = mapper.convertValue(
					relationshipMapper, new TypeReference<Map<Integer, List<GtnWsRelationshipBuilderBean>>>() {});
			List<GtnWsRelationshipBuilderBean> relationshipBuilderBeanList = relationshipMap.get(hierarchyDefinitionSid);
                        
			relationship.removeAllItems();
                        for (GtnWsRelationshipBuilderBean relationshipBuilderBean : relationshipBuilderBeanList) {
				relationship.addItem(relationshipBuilderBean.getRelationshipBuilderSid());
				relationship.setItemCaption(relationshipBuilderBean.getRelationshipBuilderSid(),
						relationshipBuilderBean.getRelationshipName());
			}
//			for (RelationshipDdlbDto relationshipDto : relationshipSidList) {
//				relationship.addItem(relationshipDto.getRelationshipBuilderSid());
//				relationship.setItemCaption(relationshipDto.getRelationshipBuilderSid(),
//						relationshipDto.getRelationshipName());
//			}
			if (selectedRelationshipDdlbDto != null) {
				relationship.select(selectedRelationshipDdlbDto.getRelationshipBuilderSid());
			}
			relationship.setPageLength(7);
			relationship.setNullSelectionAllowed(true);
			relationship.setInputPrompt(SELECT_ONE);

		} catch (SystemException | UnsupportedOperationException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	@Override
	protected void productLevelDdlbValueChange(String selectedLevel, boolean flag) {
		loadFilteredProductSelection(selectedLevel);
	}

	@Override
	protected void customerHierarchyLookUp() {
		this.customerHierarchyLookupWindow = new HierarchyLookup(
				INDICATOR_CUSTOMER_HIERARCHY.getConstant(), WINDOW_CUSTOMER_HIERARCHY_LOOKUP.getConstant(),
				customerHierarchy, customerHierarchyDto);
		UI.getCurrent().addWindow(customerHierarchyLookupWindow);
		customerHierarchyLookupWindow.addCloseListener(new Window.CloseListener() {

			@Override
			public void windowClose(Window.CloseEvent e) {
				if (customerHierarchyLookupWindow.getHierarchyDto() != null) {
					final HierarchyLookupDTO lookupDto = customerHierarchyLookupWindow.getHierarchyDto();
					customerHierarchyDto = lookupDto;
                                        customerHierarchyDto.setHierarchySelection("customerSelection");
					loadRelationDdlb(customerHierarchyDto.getHierarchyId(), null, customerRelationComboBox);
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
	protected void productHierarchyLookUp() {
		this.productHierarchyLookupWindow = new HierarchyLookup(
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
                                        productHierarchyDto.setHierarchySelection("productSelection");
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

	@Override
	protected void modeOptionChange(boolean isAddMode) {
		try {
			if (isAddMode) {
				DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(),
						scrName);
			} else {
				DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_SEARCH.getConstant(),
						scrName);
			}

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	@Override
	protected void customerLevelValueChange(Property.ValueChangeEvent event, boolean flag) {
		LOGGER.debug("Logging - customerLevelValueChange");
                
              if (customerRelationComboBox.getValue() != null
                    && !SELECT_ONE.equals(customerRelationComboBox.getValue())) {
                level.removeAllItems();
                Map<Integer, String> hierarchyLevelMapper = new HashMap<>();
                if(getCustomerHierarchyLookupWindow()!=null){
                    hierarchyLevelMapper = getCustomerHierarchyLookupWindow().getHierarchyDto().getHierarchyMap();
                }
                else{
                     hierarchyLevelMapper = customerHierarchyLookupWindow.getHierarchyDto().getHierarchyMap();
                }
               
                ObjectMapper mapper = new ObjectMapper();

                Map<Integer, String> hierarchyLevelMap = mapper.convertValue(
                        hierarchyLevelMapper, new TypeReference<Map<Integer, String>>() {
                        });
                Object[] keySet = hierarchyLevelMap.keySet().toArray();
                int selectedLevelValue = Integer.valueOf(customerLevel.getValue().toString());
                for (int i = 0; i < selectedLevelValue; i++) {
                    level.addItem((int) keySet[i]);
                    level.setItemCaption((int) keySet[i], formHierarchyInnerLevelValues((int) keySet[i], hierarchyLevelMap.get(keySet[i])));
                }
            }
                
//		customerInnerLevelContainer.removeAllItems();
//		if (event.getProperty().getValue() != null
//				&& !SELECT_ONE.equals(String.valueOf(event.getProperty().getValue()))) {
//			String selectedLevel = String.valueOf(event.getProperty().getValue());
//			setSelectedCustomerLevel(selectedLevel);
//
//			String val[] = selectedLevel.split(" ");
//			int forecastLevel = Integer.parseInt(val[1]);
//			customerInnerLevelContainer.removeAllItems();
//			selectedCustomer.removeAllItems();
//			selectedCustomerContainer.removeAllItems();
//			customerBeanList.clear();
//			for (int i = 1; i <= forecastLevel; i++) {
//				String levelName = innerCustLevels.get(i - 1).getLevel();
//				customerInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
//			}
//			level.setContainerDataSource(customerInnerLevelContainer);
//			setCustomerLevelNullSelection();
//		} else if (event.getProperty().getValue() == null || (event.getProperty().getValue() != null
//				&& SELECT_ONE.equals(String.valueOf(event.getProperty().getValue())))) {
//			customerInnerLevelContainer.removeAllItems();
//			availableCustomer.removeAllItems();
//			availableCustomerContainer.removeAllItems();
//			selectedCustomer.removeAllItems();
//			selectedCustomerContainer.removeAllItems();
//			setCustomerLevelNullSelection();
//			customerBeanList.clear();
//		}
	}
        
      private String formHierarchyInnerLevelValues(int levelNo, String levelValue) {
        String levelName = "Level " + levelNo + " - " + levelValue;
       return levelName;
    }
        @Override
	protected void levelValueChangeListener(Object value)
			throws ClassNotFoundException, CloneNotSupportedException, IOException {

		LOGGER.debug("customer inner Level - ValueChangeListener= {}  " , value);
		availableCustomerContainer.removeAllItems();
		String levelName = Constant.LEVEL_LABEL;
		try {
                    
                    int forecastLevel = 0;
                    int relationVersionNo =0;
			if (value != null && customerRelationComboBox.getValue() != null
					&& !SELECT_ONE.equals(customerRelationComboBox.getValue())) {
                            List<GtnWsRelationshipBuilderBean> relationshipBuilderBeanList = getRelationshipBuilderBeanList();
                        
                            for(GtnWsRelationshipBuilderBean relationshipBuiderBean:relationshipBuilderBeanList){
                                if(relationshipBuiderBean.getRelationshipBuilderSid() == (int)customerRelationComboBox.getValue()){
                                    relationVersionNo = relationshipBuiderBean.getVersionNo();
                                    break;
                                }
                            }
                                 int hierarchyVersionNo = customerHierarchyLookupWindow.getHierarchyDto().getVersionNo();
//				 relationVersionNo = Integer.parseInt(
//						customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
//				 hierarchyVersionNo = Integer.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
				String dedLevel = StringUtils.EMPTY;
				String dedValue = StringUtils.EMPTY;
				if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)
						&& !(deductionValue.getValue() instanceof String)) {
						dedLevel = getDedutionLevel(String.valueOf(deductionLevel.getValue()));
						dedValue = deductionValue.getValue() == null ? StringUtils.EMPTY
								: String.valueOf(((HelperDTO) deductionValue.getValue()).getId());
				}

				customerDescMap = relationLogic.getLevelValueMap(
						String.valueOf(customerRelationComboBox.getValue()), customerHierarchyDto.getHierarchyId(),
						hierarchyVersionNo, relationVersionNo);
				dataSelectionDTO.setCustomerRelationShipVersionNo(relationVersionNo);
				String selectedLevel = String.valueOf(value);
				String relationshipSid = String.valueOf(customerRelationComboBox.getValue());
				//String[] val = selectedLevel.split(" ");
				forecastLevel = (int)value;
                                dataSelectionDTO.setSelectedCustomerLevelNo(selectedLevel);
				customerHierarchyLevelDefinitionList = relationLogic
						.getHierarchyLevelDefinition(customerHierarchyDto.getHierarchyId(), hierarchyVersionNo);
				Leveldto selectedHierarchyLevelDto = customerHierarchyLevelDefinitionList.get(forecastLevel - 1);
				List<String> tempGroupFileter = groupFilteredCompanies == null ? Collections.<String>emptyList()
						: groupFilteredCompanies;
				List<Object> resultedLevelsGenericList = relationLogic.loadAvailableCustomerlevel(selectedHierarchyLevelDto,
						Integer.parseInt(relationshipSid), tempGroupFileter, 
						dedLevel,
						dedValue, relationVersionNo, forecastEligibleDate.getValue(), customerDescMap);
                                List<Leveldto> resultedLevelsList = (List<Leveldto>)resultedLevelsGenericList.get(0);
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
                                
                                queryParametersForSelectedTable.add((String)resultedLevelsGenericList.get(resultedLevelsGenericList.size()-1));
                                queryParametersForSelectedTable.add(customerDescMap);
                                GtnReportHierarchyLevelBean selectedHierarchyLevelBean = getSelectedHierarchyLevelBean(selectedHierarchyLevelDto);
                                queryParametersForSelectedTable.add(selectedHierarchyLevelBean);
                                List<GtnReportHierarchyLevelBean> selectedHierarchyLevelBeanList = new ArrayList<>();
                                for(int i=0;i<customerHierarchyLevelDefinitionList.size();i++){
                                    selectedHierarchyLevelBeanList.add(getSelectedHierarchyLevelBean(customerHierarchyLevelDefinitionList.get(i)));
                                }
                                queryParametersForSelectedTable.add(selectedHierarchyLevelBeanList);
                                queryParametersForSelectedTable.add(relationVersionNo);
                                queryParametersForSelectedTable.add(hierarchyVersionNo);
                                queryParametersForSelectedTable.add(Integer.parseInt(selectedLevel));
                                
                                queryParametersForSelectedTable.add(forecastEligibleDate.getValue());
                                queryParametersForSelectedTable.add(Boolean.FALSE);
//			int forecastLevel = 0;
//			if (value != null && customerRelationComboBox.getValue() != null
//					&& !SELECT_ONE.equals(customerRelationComboBox.getValue())) {
//				int relationVersionNo = Integer.parseInt(
//						customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
//				int hierarchyVersionNo = Integer.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
//				String dedLevel = StringUtils.EMPTY;
//				String dedValue = StringUtils.EMPTY;
//				if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)
//						&& !(deductionValue.getValue() instanceof String)) {
//					{
//						dedLevel = getDedutionLevel(String.valueOf(deductionLevel.getValue()));
//						dedValue = deductionValue.getValue() == null ? StringUtils.EMPTY
//								: String.valueOf(((HelperDTO) deductionValue.getValue()).getId());
//					}
//				}
//
//				customerDescMap = relationLogic.getLevelValueMap(
//						String.valueOf(customerRelationComboBox.getValue()), customerHierarchyDto.getHierarchyId(),
//						hierarchyVersionNo, relationVersionNo);
//				dataSelectionDTO.setCustomerRelationShipVersionNo(relationVersionNo);
//				String selectedLevel = String.valueOf(value);
//				String relationshipSid = String.valueOf(customerRelationComboBox.getValue());
//				String[] val = selectedLevel.split(" ");
//				forecastLevel = Integer.parseInt(val[1]);
//                                dataSelectionDTO.setSelectedCustomerLevelNo(selectedLevel);
//				customerHierarchyLevelDefinitionList = relationLogic
//						.getHierarchyLevelDefinition(customerHierarchyDto.getHierarchyId(), hierarchyVersionNo);
//				Leveldto selectedHierarchyLevelDto = customerHierarchyLevelDefinitionList.get(forecastLevel - 1);
//				List<String> tempGroupFileter = groupFilteredCompanies == null ? Collections.<String>emptyList()
//						: groupFilteredCompanies;
//				List<Leveldto> resultedLevelsList = relationLogic.loadAvailableCustomerlevel(selectedHierarchyLevelDto,
//						Integer.parseInt(relationshipSid), tempGroupFileter, 
//						dedLevel,
//						dedValue, relationVersionNo, forecastEligibleDate.getValue(), customerDescMap);
//				if (selectedHierarchyLevelDto.getLevel() != null) {
//					levelName = selectedHierarchyLevelDto.getLevel();
//				}
//				availableCustomerContainer.addAll(resultedLevelsList);
//				availableCustomer.setContainerDataSource(availableCustomerContainer);
//				availableCustomer.setVisibleColumns(Constant.DISPLAY_VALUE);
//				availableCustomer.setColumnHeaders(levelName);
//				availableCustomer.setFilterBarVisible(true);
//				availableCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
//				availableCustomer.setStyleName(Constant.FILTER_TABLE);
			}
		} catch (Exception ex) {
			
			LOGGER.error(" level  ValueChangeListener= {} ",ex);
		}
	}

    private List<GtnWsRelationshipBuilderBean> getRelationshipBuilderBeanList() throws IllegalArgumentException {
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer, List<GtnWsRelationshipBuilderBean>> relationshipMapper = customerHierarchyLookupWindow.getHierarchyDto().getRelationshipMap();
        Map<Integer, List<GtnWsRelationshipBuilderBean>> relationshipMap = mapper.convertValue(
                relationshipMapper, new TypeReference<Map<Integer, List<GtnWsRelationshipBuilderBean>>>() {});
        List<GtnWsRelationshipBuilderBean> relationshipBuilderBeanList = relationshipMap.get(customerHierarchyLookupWindow.getHierarchyDto().getHierarchyId());
        return relationshipBuilderBeanList;
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
		try {
                    if (productRelation.getValue() != null
                            && !SELECT_ONE.equals(productRelation.getValue())) {
                        productlevelDdlb.removeAllItems();
                        Map<Integer, String> hierarchyLevelMapper = productHierarchyLookupWindow.getHierarchyDto().getHierarchyMap();
                        ObjectMapper mapper = new ObjectMapper();

                        Map<Integer, String> hierarchyLevelMap = mapper.convertValue(
                                hierarchyLevelMapper, new TypeReference<Map<Integer, String>>() {
                                });
                        Object[] keySet = hierarchyLevelMap.keySet().toArray();
                        int selectedLevelValue = Integer.valueOf(productLevel.getValue().toString());
                        for (int i = 0; i < selectedLevelValue; i++) {
                            productlevelDdlb.addItem((int) keySet[i]);
                            productlevelDdlb.setItemCaption((int) keySet[i], formHierarchyInnerLevelValues((int) keySet[i], hierarchyLevelMap.get(keySet[i])));
                        }
                    }
                    
//			if (value != null && !SELECT_ONE.equals(String.valueOf(value))) {
//				String selectedLevel = String.valueOf(value);
//				setSelectedProductLevel(selectedLevel);
//
//				String[] val = selectedLevel.split(" ");
//				int forecastLevel = Integer.parseInt(val[1]);
//				productInnerLevelContainer.removeAllItems();
//				selectedProduct.removeAllItems();
//				selectedProductContainer.removeAllItems();
//				productBeanList.clear();
//				for (int i = 1; i <= forecastLevel; i++) {
//					String levelName = innerProdLevels.get(i - 1).getLevel();
//					productInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
//				}
//				productlevelDdlb.setContainerDataSource(productInnerLevelContainer);
//				setProductLevelNullSelection();
//			} else if (value == null || SELECT_ONE.equals(String.valueOf(value))) {
//				productInnerLevelContainer.removeAllItems();
//				availableProduct.removeAllItems();
//				availableProductContainer.removeAllItems();
//				selectedProduct.removeAllItems();
//				selectedProductContainer.removeAllItems();
//				productBeanList.clear();
//				setProductLevelNullSelection();
//			}
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	protected void customerRelationValueChange(Object value) {
		LOGGER.debug("customerRelationValueChange= {}" , value);
		if (value != null && !SELECT_ONE.equals(String.valueOf(value)) ) {
                    
			try {
                            customerLevel.removeAllItems();
                            Map<Integer, String> hierarchyMap = new HashMap<>();
                            if(getCustomerHierarchyLookupWindow()!=null){
                                hierarchyMap = getCustomerHierarchyLookupWindow().getHierarchyDto().getHierarchyMap();
                            }
                            else{
                            hierarchyMap = customerHierarchyLookupWindow.getHierarchyDto().getHierarchyMap();
                            }
                            for (Map.Entry<Integer, String> hierarchyLevelEntry : hierarchyMap.entrySet()) {
				customerLevel.addItem(hierarchyLevelEntry.getKey());
                                customerLevel.setItemCaption(hierarchyLevelEntry.getKey(),formHierarchyLevelValues(hierarchyLevelEntry));
			}
                        
//				relationLogic.waitForAutomaticRelation();
//				availableCustomer.removeAllItems();
//				availableCustomerContainer.removeAllItems();
//				selectedCustomer.removeAllItems();
//				selectedCustomerContainer.removeAllItems();
//				setCustomerForecastLevelNullSelection();
//				setCustomerLevelNullSelection();
//				setRelationshipBuilderSids(String.valueOf(customerRelationComboBox.getValue()));
//				loadCustomerVersionNo(customerRelationComboBox.getValue());
//                                customViewInput.put("custVer", customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
//                                customViewInput.put("custSid", String.valueOf(customerRelationComboBox.getValue()));
//				loadForecastLevels(innerCustLevels, customerForecastLevelContainer, customerLevel,
//						customerHierarchyDto.getHierarchyId(),
//						Integer.parseInt(customerRelationVersionComboBox.getValue().toString()));
//				if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)
//						&& !innerCustLevels.isEmpty()) {
//					customerForecastLevelContainer.removeAllItems();
//					for (int i = 1; i <= innerCustLevels.size(); i++) {
//						String levelName = innerCustLevels.get(i - 1).getLevel();
//						customerForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
//					}
//					level.setContainerDataSource(customerForecastLevelContainer);
//				}
//                                
//                                if(!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {
//                                    loadCustomViewDropDown(customRelationDdlb,customViewInput);
//                                    loadCustomViewDeductionDropDown(customRelationDdlbDeduction, customViewInput);  
//                                }
			} catch (NumberFormatException ex) {
				
				LOGGER.error(" in customerRelation value change= {}",ex);
			}
		}
                //else if (value == null || SELECT_ONE.equals(String.valueOf(value))) {
//			availableCustomer.removeAllItems();
//			availableCustomerContainer.removeAllItems();
//			selectedCustomer.removeAllItems();
//			selectedCustomerContainer.removeAllItems();
//			customerInnerLevelContainer.removeAllItems();
//                        setNullSelectionCustomDdlb(customRelationDdlb);
//                        setNullSelectionCustomDdlb(customRelationDdlbDeduction);
//			setCustomerForecastLevelNullSelection();
//			setCustomerLevelNullSelection();
//			customerDescMap = null;
//		}
//		customerGroup.setValue(StringUtils.EMPTY);
//		selectedCustomerGroupDTO = new GroupDTO();
//		dataSelectionDTO.setCustomerGrpSid(null);
//		groupFilteredCompanies = null;
	}

        public String formHierarchyLevelValues(Entry<Integer, String> hierarchyLevelEntry) {
		String levelValue = "Level " + hierarchyLevelEntry.getKey() + " - " + hierarchyLevelEntry.getValue();
                return levelValue;
        }
        
	@Override
	protected void productRelationValueChange(Object value) {
		LOGGER.debug("productRelation - ValueChangeListener= {} " , value);
		if (value != null && !SELECT_ONE.equals(String.valueOf(value))) {
			try {
                            
                            productLevel.removeAllItems();
                            Map<Integer, String> hierarchyMap = productHierarchyLookupWindow.getHierarchyDto().getHierarchyMap();
                            for (Map.Entry<Integer, String> hierarchyLevelEntry : hierarchyMap.entrySet()) {
				productLevel.addItem(hierarchyLevelEntry.getKey());
                                productLevel.setItemCaption(hierarchyLevelEntry.getKey(),formHierarchyLevelValues(hierarchyLevelEntry));
			}
                            
//				relationLogic.waitForAutomaticRelation();
//				selectedProduct.removeAllItems();
//				selectedProductContainer.removeAllItems();
//				availableProduct.removeAllItems();
//				availableProductContainer.removeAllItems();
//				setProductForecastLevelNullSelection();
//				setProductLevelNullSelection();
//				setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));
//				loadProductVersionNo(productRelation.getValue());
//                                customViewInput.put("prodVer", productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
//                                customViewInput.put("prodSid", String.valueOf(productRelation.getValue()));
//				loadForecastLevels(innerProdLevels, productForecastLevelContainer, productLevel,
//						productHierarchyDto.getHierarchyId(),
//						Integer.parseInt(productRelationVersionComboBox.getValue().toString()));
//				if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)
//						&& !innerProdLevels.isEmpty()) {
//					productForecastLevelContainer.removeAllItems();
//					for (int i = 1; i <= innerProdLevels.size(); i++) {
//						String levelName = innerProdLevels.get(i - 1).getLevel();
//						productForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
//					}
//					productlevelDdlb.setContainerDataSource(productForecastLevelContainer);
//				}
//                                
//                                if(!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {
//                                    loadCustomViewDropDown(customRelationDdlb,customViewInput);
//                                    loadCustomViewDeductionDropDown(customRelationDdlbDeduction, customViewInput);
//                                }

			} catch (NumberFormatException ex) {
				LOGGER.error(" in productRelation value change= {}",ex);
			}
		} 
                        //else if ((value == null || SELECT_ONE.equals(String.valueOf(value)))) {
//			selectedProduct.removeAllItems();
//			selectedProductContainer.removeAllItems();
//			availableProduct.removeAllItems();
//			availableProductContainer.removeAllItems();
//			productInnerLevelContainer.removeAllItems();
//                        setNullSelectionCustomDdlb(customRelationDdlb);
//                        setNullSelectionCustomDdlb(customRelationDdlbDeduction);
//			setProductForecastLevelNullSelection();
//			setProductLevelNullSelection();
//			productDescMap = null;
//		}
//		dataSelectionDTO.setProdGrpSid(null);
//		productGroup.setValue(StringUtils.EMPTY);
//		selectedProductGroupDTO = new GroupDTO();
//		groupFilteredItems = null;
	}

	@Override
	protected void generateButtonLogic() {
		LOGGER.debug("generateBtn click listener started= {} " , scrName);

		if (dsLogic.checkForActiveFiles(businessUnit.getValue(), company.getValue())) {
			setPrivateViewName(privateView.getValue());
			setPublicViewName(publicView.getValue());

			if (scrName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
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
                                        dataSelectionDTO.setCustomRelationShipSid(customRelationDdlb.getValue()!=null ? Integer.parseInt(String.valueOf(customRelationDdlb.getValue())) :0 );
                                        dataSelectionDTO.setCustomDeductionRelationShipSid(customRelationDdlbDeduction.getValue()!=null ? Integer.parseInt(String.valueOf(customRelationDdlbDeduction.getValue())): 0);
					int projectionIdValue = nmLogic.saveProjection(dataSelectionDTO, scrName,false);
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
                                                session.setDsFrequency(String.valueOf(frequency.getValue()));
                                                session.setDataSelectionDeductionLevel(String.valueOf(dataSelectionDeductionLevel.getValue()));
                                                session.setDataSelectionDeductionLevelCaption(dataSelectionDeductionLevel.getItemCaption(dataSelectionDeductionLevel.getValue()));
						session.setProjectionId(projectionIdValue);
						session.setBusineesUnit(businessUnitlist);
						session.setAction(Constant.ADD_FULL_SMALL);
						session.setCustomerDescription(customerDescMap);
						session.setProductDescription(productDescMap);
						session.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
						session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
						session.setCustomRelationShipSid(dataSelectionDTO.getCustomRelationShipSid());
						session.setCustomDeductionRelationShipSid(dataSelectionDTO.getCustomDeductionRelationShipSid());
                                                session.setCustomerHierarchyVersion(
								dataSelectionDTO.getCustomerHierVersionNo());
						session.setProductHierarchyVersion(dataSelectionDTO.getProductHierVersionNo());
						session.setCustomerRelationVersion(dataSelectionDTO.getCustomerRelationShipVersionNo());
						session.setProductRelationVersion(dataSelectionDTO.getProductRelationShipVersionNo());
						if (CommonUtil.isValueEligibleForLoading()) {
							 Object[] obj = nmLogic
                                                        .deductionRelationBuilderId(dataSelectionDTO.getProdRelationshipBuilderSid());
                                                session.setDedRelationshipBuilderSid(obj[0].toString());
                                                List versionNoList = nmLogic.getDeductionVersionNoList(session.getDedRelationshipBuilderSid());
                                                if (versionNoList != null) {
                                                    session.setDeductionRelationVersion((int) versionNoList.get(0));
                                                }
						}
						session.setScreenName(scrName);
						session.setFunctionMode("G");
						session.setProjectionName(dataSelectionDTO.getProjectionName());
						// To create the temp tables with userId and session id
						QueryUtils.createTempTables(session);
						int hierarchyVersionNo = Integer
								.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
						customerHierarchyLevelDefinitionList = relationLogic
								.getHierarchyLevelDefinition(customerHierarchyDto.getHierarchyId(), hierarchyVersionNo);
						relationLogic.ccpHierarchyInsert(session.getCurrentTableNames(),
								selectedCustomerContainer.getItemIds(), selectedProductContainer.getItemIds(),
								dataSelectionDTO);

                                            session.setCustomerLevelDetails(
                                                    dsLogic.getLevelValueDetails(session, customerRelationComboBox.getValue(), true));
                                            session.setProductLevelDetails(
                                                    dsLogic.getLevelValueDetails(session, productRelation.getValue(), false));
                                             session.setSalesHierarchyLevelDetails(
                                        dsLogic.getRelationshipDetailsCustom(session, String.valueOf(session.getCustomRelationShipSid())));
                                        session.setDiscountCustomerProductLevelDetails(
                                        dsLogic.getRelationshipDetailsCustom(session, String.valueOf(session.getCustomDeductionRelationShipSid())));
                                            dsLogic.loadProjectionFileDetailsTabInGenerate(session);
                                            ForecastWindow forecastWindow = new ForecastWindow(projectionName.getValue(), session,
								resultTable, scrName, this, dataSelectionDTO);
						UI.getCurrent().addWindow(forecastWindow);
						session.setGenerateFlag(false);
					}
					resetOne();
					resetTwo();
					LOGGER.debug("generateBtn click listener ends  ");
				} catch (Exception e) {

					LOGGER.error(" generateBtn click listener = {}",e);
				}
				UI.getCurrent().setFocusedComponent(UI.getCurrent());

			} else if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {
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

        public void generateButtonLogicNewArch(SessionDTO sessionDto,GtnFrameworkForecastInputBean inputBean) {
		LOGGER.info("generateBtn click listener started= {} " , scrName);

		if (dsLogic.checkForActiveFiles(inputBean.getBusinessUnit(), inputBean.getCompany())) {
			setPrivateViewName(inputBean.getPrivateViewName());
			setPublicViewName(inputBean.getPublicViewName());

			if (scrName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
				try {
//					if (inputBean.getSelectedCustomerList().size() <= 0 || inputBean.getSelectedProductList().size() <= 0 || fromPeriod.getValue() == null
//							|| StringUtils.isBlank(inputBean.getProjectionName())
//							|| Constants.CommonConstants.NULL.getConstant().equalsIgnoreCase(projectionName.getValue())
//							|| company.getValue() == null || SELECT_ONE.equals(company.getValue())
//							|| (Integer) company.getValue() == 0 || (Integer) businessUnit.getValue() == 0) {
//						AbstractNotificationUtils.getErrorNotification("Selection Criteria",
//								Constant.NOT_ALL_REQUIRED_FIELDS_POPULATED);
//						return;
//					}

					bindDataselectionDtoToSaveNewArch(inputBean);
                                        dataSelectionDTO.setCustomRelationShipSid(0);
                                        dataSelectionDTO.setCustomDeductionRelationShipSid(0);
                                        VaadinSession.getCurrent().setAttribute(Constant.USER_ID, inputBean.getUserId());
					int projectionIdValue = nmLogic.saveProjection(dataSelectionDTO, scrName,false);
					VaadinSession.getCurrent().setAttribute(Constant.PROJECTION_ID, projectionIdValue);
					projectionId.setValue(String.valueOf(projectionIdValue));
					dataSelectionDTO.setProjectionId(projectionIdValue);
					relationshipBuilderSids.clear();
					setRelationshipBuilderSids(String.valueOf(inputBean.getCustomerRelationSid()));
					setRelationshipBuilderSids(String.valueOf(inputBean.getProductRelationSid()));
					dataSelectionDTO
							.setSelectedCustomerRelationSid(getNewArchRelationshipSid(inputBean.getSelectedCustomerList()));
					dataSelectionDTO
							.setSelectedProductRelationSid(getNewArchRelationshipSid(inputBean.getSelectedProductList()));

					if (projectionIdValue != 0) {
						SessionDTO session = SessionUtil.createSession();
                                                session.setUserId(inputBean.getUserId());
                                                session.setSessionId(session.getSessionId());
						session.setGenerateFlag(true);
                                                session.setDsFrequency(String.valueOf("Quarterly"));
                                                session.setDataSelectionDeductionLevel("1");
                                                session.setDataSelectionDeductionLevelCaption("Schedule Category");
						session.setProjectionId(projectionIdValue);
						session.setBusineesUnit(businessUnitlist);
						session.setAction(Constant.ADD_FULL_SMALL);
						session.setCustomerDescription(customerDescMap);
						session.setProductDescription(productDescMap);
						session.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
						session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
						session.setCustomRelationShipSid(dataSelectionDTO.getCustomRelationShipSid());
						session.setCustomDeductionRelationShipSid(dataSelectionDTO.getCustomDeductionRelationShipSid());
                                                session.setCustomerHierarchyVersion(
								dataSelectionDTO.getCustomerHierVersionNo());
						session.setProductHierarchyVersion(dataSelectionDTO.getProductHierVersionNo());
						session.setCustomerRelationVersion(dataSelectionDTO.getCustomerRelationShipVersionNo());
						session.setProductRelationVersion(dataSelectionDTO.getProductRelationShipVersionNo());
						if (CommonUtil.isValueEligibleForLoading()) {
							 Object[] obj = nmLogic
                                                        .deductionRelationBuilderId(dataSelectionDTO.getProdRelationshipBuilderSid());
                                                session.setDedRelationshipBuilderSid(obj[0].toString());
                                                List versionNoList = nmLogic.getDeductionVersionNoList(session.getDedRelationshipBuilderSid());
                                                if (versionNoList != null) {
                                                    session.setDeductionRelationVersion((int) versionNoList.get(0));
                                                }
						}
						session.setScreenName(scrName);
						session.setFunctionMode("G");
						//session.setLayout(sessionDto.getLayout());
						session.setProjectionName(dataSelectionDTO.getProjectionName());
						// To create the temp tables with userId and session id
						QueryUtils.createTempTables(session);
						int hierarchyVersionNo = Integer
								.parseInt(String.valueOf(inputBean.getCustomerHierarchyVersion()));
						int prodHierarchyVersionNo = Integer
								.parseInt(String.valueOf(inputBean.getProductHierarchyVersion()));
						customerHierarchyLevelDefinitionList = relationLogic
								.getHierarchyLevelDefinition(inputBean.getCustomerHierarchySid(), hierarchyVersionNo);
                                                productHierarchyLevelDefinitionList = relationLogic
						.getHierarchyLevelDefinition(inputBean.getProductHierarchySid(), prodHierarchyVersionNo);
						relationLogic.ccpHierarchyInsert(session.getCurrentTableNames(),
								dataLogic.customizeLevelDtoFromRecordBean(inputBean.getSelectedCustomerList(),inputBean.getCustomerRelationVersionNo()), dataLogic.customizeLevelDtoFromRecordBean(inputBean.getSelectedProductList(),inputBean.getProductRelationVersionNo()),
								dataSelectionDTO);
                                                
                                                

                                            session.setCustomerLevelDetails(
                                                    dsLogic.getLevelValueDetails(session, inputBean.getCustomerRelationSid(), true));
                                            session.setProductLevelDetails(
                                                    dsLogic.getLevelValueDetails(session, inputBean.getProductRelationSid(), false));
                                             session.setSalesHierarchyLevelDetails(
                                        dsLogic.getRelationshipDetailsCustom(session, String.valueOf(session.getCustomRelationShipSid())));
                                        session.setDiscountCustomerProductLevelDetails(
                                        dsLogic.getRelationshipDetailsCustom(session, String.valueOf(session.getCustomDeductionRelationShipSid())));
                                            dsLogic.loadProjectionFileDetailsTabInGenerate(session);
                                            ForecastWindow forecastWindow = new ForecastWindow(projectionName.getValue(), session,
								resultTable, scrName, this, dataSelectionDTO);
						UI.getCurrent().addWindow(forecastWindow);
						session.setGenerateFlag(false);
					}
//					resetOne();
//					resetTwo();
					LOGGER.debug("generateBtn click listener ends  ");
				}catch (Exception e) {

					LOGGER.error(" generateBtn click listener = {}",e);
				}
				UI.getCurrent().setFocusedComponent(UI.getCurrent());

			}else if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {
				generateLogicForARP();
			}
//		} else if (businessUnit.getValue() == null) {
//			AbstractNotificationUtils.getErrorNotification(Constant.SELECTION_CRITERIA_HEADER,
//					Constant.NOT_ALL_REQUIRED_FIELDS_POPULATED);
//		} else {
//			AbstractNotificationUtils.getErrorNotification("File Not available",
//					"No active file available for the selected Business Unit");
//		}
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
					relationId = Integer.parseInt(dto.getRelationShipBuilderId());
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
			int projectionIdValue = nmLogic.saveProjection(dataSelectionDTO, scrName,false);
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
				session.setCustomerHierarchyId(Integer.parseInt(dataSelectionDTO.getCustomerHierSid()));
				session.setProductHierarchyId(Integer.parseInt(dataSelectionDTO.getProdHierSid()));
				session.setCustomerRelationId(Integer.parseInt(dataSelectionDTO.getCustRelationshipBuilderSid()));
				session.setProductRelationId(Integer.parseInt(dataSelectionDTO.getProdRelationshipBuilderSid()));
				String definedOrUDValue = StringUtils.EMPTY;
				String definedValue = dsLogic.getDefinedValue(dataSelectionDTO.getCustomerHierSid());
				session.setCustomerHierarchyId(Integer.parseInt(dataSelectionDTO.getCustomerHierSid()));
				session.setProductHierarchyId(Integer.parseInt(dataSelectionDTO.getProdHierSid()));
				session.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
				session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
				session.setCustomerDescription(customerDescMap);
				session.setProductDescription(productDescMap);
				session.setScreenName(scrName);
				// To create the temp tables with userId and session id
				QueryUtils.createTempTables(session);
				relationLogic.ccpHierarchyInsert(session.getCurrentTableNames(), selectedCustomerContainer.getItemIds(),
						selectedProductContainer.getItemIds(), dataSelectionDTO);
				session.setCustomerLevelDetails(
						dsLogic.getLevelValueDetails(session, customerRelationComboBox.getValue(), true));
				session.setProductLevelDetails(
						dsLogic.getLevelValueDetails(session, productRelation.getValue(), false));
				if ("User Defined".equalsIgnoreCase(definedValue)) {
					if (!StringUtils.EMPTY.equals(dataSelectionDTO.getCustRelationshipBuilderSid())
							&& !DASH.equals(dataSelectionDTO.getCustRelationshipBuilderSid())) {
						marketType = dsLogic.getGenerateMarketValue(
								Integer.parseInt(dataSelectionDTO.getCustRelationshipBuilderSid()));
					}
					definedOrUDValue = marketType;
				} else if ("Market Type"
						.equals(session.getHierarchyLevelDetails().get(marketTypeHierarchyNo).get(1).toString())) {
					definedOrUDValue = session.getHierarchyLevelDetails().get(marketTypeHierarchyNo).get(0).toString();
				}
				session.setMarketTypeValue(definedOrUDValue);
				ForecastWindow forecastWindow = new ForecastWindow(projectionName.getValue(), session, resultTable,
						scrName, this, dataSelectionDTO);
				UI.getCurrent().addWindow(forecastWindow);
			}
			resetOne();
			resetTwo();

			LOGGER.debug("generateBtn click listener ends ");

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		UI.getCurrent().setFocusedComponent(UI.getCurrent());
	}

	@Override
	protected void configureTimeDdlb(ComboBox fromPeriod, ComboBox toPeriod, Object object, Object object0,
			String constant, String screenName) {
		try {

			DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(), screenName);
		} catch (Exception ex) {

			LOGGER.error(ex.getMessage());
		}
	}

	@Override
	protected void loadPublicView() {
		final PrivatePublicView publicViewLookup = new PrivatePublicView(INDICATOR_PUBLIC_VIEW.getConstant(),
				PUBLIC_VIEW.getConstant(), scrName);
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
						LOGGER.error(" publicView close= {}",ex);
					}
				}

			}
		});
	}

	@Override
	protected void loadPrivateView() {
		final PrivatePublicView privateViewLookup = new PrivatePublicView(INDICATOR_PRIVATE_VIEW.getConstant(),
				PRIVATE_VIEW.getConstant(), scrName);
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
						
						LOGGER.error(" privateView close= {}", ex);
					}
				}

			}
		});
	}

	@Override
	protected void loadProductGroup() {
		final CustomerProductGroupLookup productGroupLookupWindow = new CustomerProductGroupLookup(
				INDICATOR_PRODUCT_GROUP.getConstant(), WINDOW_PRODUCT_GROUP_LOOKUP.getConstant(), productGroup,
				scrName);
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
				Collections.<String>emptyList(), scrName);
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
						LOGGER.error(" loadCustomerGroup= {}",ex);
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
				if (privateView.getValue() != null && !StringUtils.EMPTY.equals(privateView.getValue())) {
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
						productListEndSids, scrName);
				UI.getCurrent().addWindow(saveViewPopup);

			} catch (IllegalArgumentException | NullPointerException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		return;
	}

	@Override
	protected void resetButtonLogic() {
                forecastEligibleDate.setValue(dsLogic.getDefaultEligibleDateFromForecastConfiguration());
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

	/**
	 * Used to set the Visible Header for DataSelection ListView
	 *
	 */
	private void setTableHeader() {
                    if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(scrName)) {
			resultTable.setVisibleColumns(TableHeaderColumnsUtil.getDataSelectionColumnsAccrual());
			resultTable.setColumnHeaders(TableHeaderColumnsUtil.getDataSelectionHeadersAccrual());
		} else {
			resultTable.setVisibleColumns(TableHeaderColumnsUtil.getDataSelectionColumns());
			resultTable.setColumnHeaders(TableHeaderColumnsUtil.getDataSelectionHeaders());
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

			int projectionIdValue = accdsLogic.saveProjection(dataSelectionDTO, scrName);

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
				final SessionDTO session = SessionUtil.createSession();
				session.setScreenName(scrName);
				// To create the temp tables with userId and session id
				QueryUtils.createTempTables(session);
                                
                                HelperListUtil helperUtil = HelperListUtil.getInstance();
				String dedLevel = getDedutionLevel(dataSelectionDTO.getDeductionLevel());
				String dedValue = String.valueOf(helperUtil.getIdByDescription(dataSelectionDTO.getDeductionValue(), dedLevel));
				relationLogic.ccpHierarchyInsertARP(session.getCurrentTableNames(), selectedCustomerContainer.getItemIds(),
								selectedProductContainer.getItemIds(), dataSelectionDTO, dedLevel, dedValue);
                                
				session.setProjectionId(projectionIdValue);
				session.setSelectedCustomerRelationSid(getRelationshipSid(selectedCustomerContainer.getItemIds()));
				session.setSelectedProductRelationSid(getRelationshipSid(selectedProductContainer.getItemIds()));
				session.setProductLevelNumber(String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
				session.setCustomerLevelNumber(String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
				session.setAction(Constant.ADD_FULL_SMALL);
				session.setProductHierarchyId(Integer.parseInt(dataSelectionDTO.getProdHierSid()));
				session.setProductRelationId(Integer.parseInt(dataSelectionDTO.getProdRelationshipBuilderSid()));
				session.setProductHierarchyId(Integer.parseInt(dataSelectionDTO.getProdHierSid()));
				session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
				session.setCustomerDescription(customerDescMap);
				session.setProductDescription(productDescMap);
				session.setCustomerHierarchyId(0);
				session.setBusineesUnit(businessUnitlist);
				session.setDeductionLevel(String.valueOf(deductionLevel.getValue()));
				session.setDeductionValue(String.valueOf(deductionValue.getValue()));

				DataSelectionLogic.hierarchyDetailsInsert(session.getSelectedCustomerRelationSid(),
						"PROJECTION_CUST_HIERARCHY", session.getProjectionId(), BooleanConstant.getFalseFlag());
				DataSelectionLogic.hierarchyDetailsInsert(session.getSelectedProductRelationSid(),
						"PROJECTION_PROD_HIERARCHY", session.getProjectionId(), BooleanConstant.getFalseFlag());
				AccrualRateProjectionView arpView = new AccrualRateProjectionView(
						StringUtils.EMPTY + session.getProjectionId(), session, scrName, this, false);
				getUI().getNavigator().addView(AccrualRateProjectionView.ARP_VIEW, arpView);
				getUI().getNavigator().navigateTo(AccrualRateProjectionView.ARP_VIEW);
			}

		} catch (SystemException | Property.ReadOnlyException | NumberFormatException | ParseException e) {
			LOGGER.error(e.getMessage());
		}

	}

	public void closeBtn() {
		try {
			resetOne();
			deductionLevel.setValue(null);
			deductionValue.setValue(null);
			modeOption.select("Add");
			dismantleCustSelection = true;
			dismantleProdSelection = true;
			resetButtonLogic();
		} catch (Property.ReadOnlyException e) {
			LOGGER.error(e.getMessage());
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

						if (Constant.DEDUCTION_CATEGORY1.equalsIgnoreCase(String.valueOf(deductionLevel.getValue()))) {

							commonUtils.loadComboBox(deductionValue, Constant.RS_CATEGORY1, false);
						} else if (Constant.DEDUCTION_PROGRAM_TYPE1.equalsIgnoreCase(String.valueOf(deductionLevel.getValue()))) {

							commonUtils.loadComboBox(deductionValue, Constant.REBATE_PROGRAM_TYPE1, false);
						} else if (Constant.DEDUCTION_SCHEDULE_TYPE1.equalsIgnoreCase(String.valueOf(deductionLevel.getValue()))) {

							commonUtils.loadComboBox(deductionValue, Constant.RS_TYPE, false);
						}

					} catch (Exception e) {
						LOGGER.error(e.getMessage());
					}
				}
			});

		} catch (UnsupportedOperationException e) {
			LOGGER.error(e.getMessage());
		}

	}

        @Override
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

	public final void configurePermission() {

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
			LOGGER.error(ex.getMessage());
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
	
	@Override
	protected void loadForecastLevels(List<Leveldto> innerLevels, IndexedContainer productForecastLevelContainer,
			ComboBox level, int hierarchySid, int hierarchyVersion) {
		innerLevels.clear();
		innerLevels.addAll(new DataSelectionLogic().loadCustomerForecastLevel(hierarchySid, StringUtils.EMPTY,
				hierarchyVersion));
		productForecastLevelContainer.removeAllItems();
		for (int i = 1; i <= innerLevels.size(); i++) {
			String levelName = innerLevels.get(i - 1).getLevel();
			productForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
		}
		level.setContainerDataSource(productForecastLevelContainer);
	}

	public ResourceBundle getTableName() {
		return tableName;
	}

	public void setTableName(ResourceBundle tableName) {
		this.tableName = tableName;
	}
        
        private void configureDataSelectionDeductionLevel() {

		List<String[]> newDeductionLevelList = CommonLogic.getDataselectionDeductionLevel();
		Utility.loadDdlbForDataselectionDeduction(dataSelectionDeductionLevel, newDeductionLevelList);
                dataSelectionDeductionLevel.select(1);
		
	}

    private void loadCustomViewDropDown(ComboBox customRelationDdlb,Map<String,String> inputData) {
        setNullSelectionCustomDdlb(customRelationDdlb);
        dataLogic.loadCustomViewValues(customRelationDdlb,inputData,true);
        
    }
	

    private void loadCustomViewDeductionDropDown(ComboBox customRelationDdlb,Map<String,String> inputData) {
        setNullSelectionCustomDdlb(customRelationDdlb);
        dataLogic.loadCustomViewDeductionValues(customRelationDdlb,inputData,true);
        
    }

    private GtnReportHierarchyLevelBean getSelectedHierarchyLevelBean(Leveldto selectedHierarchyLevelDto) {
         GtnReportHierarchyLevelBean selectedHierarchyLevelBean = new GtnReportHierarchyLevelBean();
          
         selectedHierarchyLevelBean.setFieldName(selectedHierarchyLevelDto.getFieldName()==null ? "" : selectedHierarchyLevelDto.getFieldName());
         selectedHierarchyLevelBean.setHierarchyDefSid(selectedHierarchyLevelDto.getHierarchyId() == null ? 0 : selectedHierarchyLevelDto.getHierarchyId());
         selectedHierarchyLevelBean.setHierarchyLevelDefSid(selectedHierarchyLevelDto.getHierarchyLevelDefnId() == null ? "" : selectedHierarchyLevelDto.getHierarchyLevelDefnId());
         selectedHierarchyLevelBean.setHierarchyNo(selectedHierarchyLevelDto.getHierarchyNo() == null ? "" : selectedHierarchyLevelDto.getHierarchyNo());
         selectedHierarchyLevelBean.setHierarchyType(selectedHierarchyLevelDto.getHierarchyType() == null ? "" : selectedHierarchyLevelDto.getHierarchyType());
         selectedHierarchyLevelBean.setHierarchyVersionNo(selectedHierarchyLevelDto.getHierarchyVersionNo() == null ? 0:selectedHierarchyLevelDto.getHierarchyVersionNo());
         selectedHierarchyLevelBean.setLevel(selectedHierarchyLevelDto.getLevel() == null ? "" : selectedHierarchyLevelDto.getLevel());
         selectedHierarchyLevelBean.setLevelNo(selectedHierarchyLevelDto.getLevelNo() == null ? 0 : selectedHierarchyLevelDto.getLevelNo());
         selectedHierarchyLevelBean.setLevelValueReference(selectedHierarchyLevelDto.getLevelValueReference() == null ? "" : selectedHierarchyLevelDto.getLevelValueReference());
         selectedHierarchyLevelBean.setRelationShipBuilderId(selectedHierarchyLevelDto.getRelationShipBuilderId() == null ? "" :selectedHierarchyLevelDto.getRelationShipBuilderId() );
         selectedHierarchyLevelBean.setRelationShipSid(selectedHierarchyLevelDto.getRelationShipSid() == null ? "" : selectedHierarchyLevelDto.getRelationShipSid());
         selectedHierarchyLevelBean.setRelationshipLevelSid(selectedHierarchyLevelDto.getRelationshipLevelSid() == null ? 0 :selectedHierarchyLevelDto.getRelationshipLevelSid());
         selectedHierarchyLevelBean.setRelationshipLevelValue(selectedHierarchyLevelDto.getRelationshipLevelValue() == null ? "" : selectedHierarchyLevelDto.getRelationshipLevelValue());
         selectedHierarchyLevelBean.setTableName(selectedHierarchyLevelDto.getTableName() == null ? "" : selectedHierarchyLevelDto.getTableName());
         
         return selectedHierarchyLevelBean;
    }


}