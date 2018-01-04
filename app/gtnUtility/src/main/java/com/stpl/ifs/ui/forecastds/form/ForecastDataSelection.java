/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui.forecastds.form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;

import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.UIUtil;
import com.stpl.ifs.ui.util.UIUtil.LengthConstants;
import com.stpl.ifs.ui.util.converters.TextFieldConverter;
import com.stpl.ifs.util.HeaderUtils;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.NativeSelect;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.TreeTable;
import com.vaadin.v7.ui.VerticalLayout;
import org.asi.ui.addons.lazycontainer.LazyBeanItemContainer;

/**
 *
 * @author sooriya.lakshmanan
 */
public abstract class ForecastDataSelection extends CustomComponent implements View {

	public static final String SELECT_ONE = "-Select One-";
	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(ForecastDataSelection.class);
	/**
	 * The mode.
	 */
	// @UiField("modeOption")
	protected OptionGroup modeOption = new OptionGroup();

	/**
	 * The projection name.
	 */
	// @UiField("projectionNameText")
	protected TextField projectionName = new TextField();

	/**
	 * The description.
	 */
	protected TextField description = new TextField();

	/**
	 * The private view.
	 */
	// @UiField("privateViewText")
	protected CustomTextField privateView = new CustomTextField();

	/**
	 * The public view.
	 */
	// @UiField("publicViewText")
	protected CustomTextField publicView = new CustomTextField();

	protected ComboBox discount = new ComboBox();

	/**
	 * The from date.
	 */
	@UiField("fromDate")
	protected ComboBox fromPeriod;

	/**
	 * The to date.
	 */
	@UiField("toDate")
	protected ComboBox toPeriod;

	/**
	 * The customer hierarchy.
	 */
	@UiField("customerHierarchy")
	protected CustomTextField customerHierarchy;

	/**
	 * The customer group.
	 */
	@UiField("customerGP")
	protected CustomTextField customerGroup;

	/**
	 * The customer level.
	 */
	@UiField("customerlevel")
	protected ComboBox customerLevel;

	@UiField("customerRelation")
	protected ComboBox customerRelationComboBox;
	@UiField("customerRelationVersionLabel")
	protected Label customerRelationVersionLabel;
	@UiField("customerRelationVersion")
	protected ComboBox customerRelationVersionComboBox;

	/**
	 * The level.
	 */
	@UiField("levelDDLB")
	protected ComboBox level;

	/**
	 * The available customer.
	 */
	@UiField("availableCustomer")
	protected ExtFilterTable availableCustomer;

	/**
	 * The selected customer.
	 */
	@UiField("selectedCustomer")
	protected TreeTable selectedCustomer;

	/**
	 * The move Left Btn.
	 */
	@UiField("moveLeftBtn")
	protected Button moveLeftBtn;

	/**
	 * The move Right Btn.
	 */
	@UiField("moveRightBtn")
	protected Button moveRightBtn;

	/**
	 * The all.
	 */
	@UiField("allBtn")
	protected Button all;

	protected ComboBox company = new ComboBox();

	protected ComboBox businessUnit = new ComboBox();

	/**
	 * The product hierarchy.
	 */
	@UiField("productHierarchy")
	protected CustomTextField productHierarchy;

	/**
	 * The product level.
	 */
	@UiField("productlevel")
	protected ComboBox productLevel;

	@UiField("productRelationVersionLabel")
	protected Label productRelationVersionLabel;
	@UiField("productRelation")
	protected ComboBox productRelation;
	@UiField("productRelationVersion")
	protected ComboBox productRelationVersionComboBox;

	/**
	 * The product group.
	 */
	@UiField("productGP")
	protected CustomTextField productGroup;

	/**
	 * The productlevel ddlb.
	 */
	@UiField("productlevelDdlb")
	protected ComboBox productlevelDdlb;

	/**
	 * The available product.
	 */
	@UiField("availableProduct")
	protected ExtFilterTable availableProduct;

	/**
	 * The selected product.
	 */
	@UiField("selectedProduct")
	protected TreeTable selectedProduct;

	/**
	 * The move left product.
	 */
	@UiField("moveLeftProduct")
	protected Button moveLeftProduct;

	/**
	 * The move right product.
	 */
	@UiField("moveRightProduct")
	protected Button moveRightProduct;

	/**
	 * The all product btn.
	 */
	@UiField("allProductBtn")
	protected Button allProductBtn;

	/**
	 * The generate btn.
	 */
	@UiField("generateBtn")
	protected Button generateBtn;

	/**
	 * The search btn.
	 */
	@UiField("searchBtn")
	protected Button searchBtn;

	/**
	 * The reset btn.
	 */
	@UiField("resetBtn")
	protected Button resetBtn;

	/**
	 * The save view btn.
	 */
	@UiField("saveViewBtn")
	protected Button saveViewBtn;

	/**
	 * The delete view btn.
	 */
	@UiField("deleteViewBtn")
	protected Button deleteViewBtn;

	/**
	 * The edit btn.
	 */
	@UiField("editBtn")
	protected Button editBtn;

	/**
	 * The view btn.
	 */
	@UiField("viewBtn")
	protected Button viewBtn;

	/**
	 * The result reset btn.
	 */
	@UiField("resultResetBtn")
	protected Button resultResetBtn;

	/**
	 * The Delete btn.
	 */
	@UiField("deleteBtn")
	protected Button deleteBtn;

	// /**
	// * The result table.
	// */
	/**
	 * the VerticalLayout
	 */
	@UiField("verticalLayout")
	protected VerticalLayout verticalLayout;

	@UiField("buttonLay")
	protected HorizontalLayout buttonLay;

	@UiField("finalBtn")
	protected HorizontalLayout finalBtn;

	@UiField("productBtnLayout")
	protected GridLayout productBtnLayout;

	@UiField("customerBtnLayout")
	protected GridLayout customerBtnLayout;

	@UiField("resultsTablePanel")
	protected Panel resultsTablePanel;

	@UiField("resultsTableLayout")
	protected VerticalLayout resultsTableLayout;
	@UiField("horizontalLayout")
	protected HorizontalLayout horizontalLayout;

	@UiField("gridLayoutTimeperiod")
	protected GridLayout gridLayoutTimeperiod;
	@UiField("verticalLayoutTimeperiod")
	protected VerticalLayout verticalLayoutTimeperiod;
	@UiField("ProdSelectionHLayout")
	protected HorizontalLayout ProdSelectionHLayout;
	@UiField("productSelectionGrid2")
	protected GridLayout productSelectionGrid2;
	@UiField("ProdSelectionVLayout")
	protected VerticalLayout ProdSelectionVLayout;

	@UiField("timeperiod")
	protected Panel timeperiod;

	@UiField("panel3")
	protected Panel panel3;

	@UiField("customerSelection")
	protected Panel customerSelection;

	@UiField("customerForecastLevel")
	protected Label customerForecastLevelLabel;

	@UiField("productForecastLevel")
	protected Label productForecastLevelLabel;

	public Button getGenerateBtn() {
		return generateBtn;
	}

	public void setGenerateBtn(Button generateBtn) {
		this.generateBtn = generateBtn;
	}

	public Button getMoveLeftBtn() {
		return moveLeftBtn;
	}

	public void setMoveLeftBtn(Button moveLeftBtn) {
		this.moveLeftBtn = moveLeftBtn;
	}

	public Button getMoveRightBtn() {
		return moveRightBtn;
	}

	public void setMoveRightBtn(Button moveRightBtn) {
		this.moveRightBtn = moveRightBtn;
	}

	public Button getAll() {
		return all;
	}

	public void setAll(Button all) {
		this.all = all;
	}

	public Button getMoveLeftProduct() {
		return moveLeftProduct;
	}

	public void setMoveLeftProduct(Button moveLeftProduct) {
		this.moveLeftProduct = moveLeftProduct;
	}

	public Button getMoveRightProduct() {
		return moveRightProduct;
	}

	public void setMoveRightProduct(Button moveRightProduct) {
		this.moveRightProduct = moveRightProduct;
	}

	public Button getAllProductBtn() {
		return allProductBtn;
	}

	public void setAllProductBtn(Button allProductBtn) {
		this.allProductBtn = allProductBtn;
	}

	public Button getSearchBtn() {
		return searchBtn;
	}

	public void setSearchBtn(Button searchBtn) {
		this.searchBtn = searchBtn;
	}

	public Button getResetBtn() {
		return resetBtn;
	}

	public void setResetBtn(Button resetBtn) {
		this.resetBtn = resetBtn;
	}

	public Button getSaveViewBtn() {
		return saveViewBtn;
	}

	public void setSaveViewBtn(Button saveViewBtn) {
		this.saveViewBtn = saveViewBtn;
	}

	public Button getDeleteViewBtn() {
		return deleteViewBtn;
	}

	public void setDeleteViewBtn(Button deleteViewBtn) {
		this.deleteViewBtn = deleteViewBtn;
	}

	public Button getViewBtn() {
		return viewBtn;
	}

	public void setViewBtn(Button viewBtn) {
		this.viewBtn = viewBtn;
	}

	public Button getResultResetBtn() {
		return resultResetBtn;
	}

	public void setResultResetBtn(Button resultResetBtn) {
		this.resultResetBtn = resultResetBtn;
	}

	public Button getDeleteBtn() {
		return deleteBtn;
	}

	public void setDeleteBtn(Button deleteBtn) {
		this.deleteBtn = deleteBtn;
	}

	public Button getEditBtn() {
		return editBtn;
	}

	public void setEditBtn(Button editBtn) {
		this.editBtn = editBtn;
	}

	/**
	 * Bean container for available customers.
	 */
	protected BeanItemContainer<Leveldto> availableCustomerContainer = new BeanItemContainer<>(Leveldto.class);

	/**
	 * Bean container for available Product.
	 */
	protected BeanItemContainer<Leveldto> availableProductContainer = new BeanItemContainer<>(Leveldto.class);

	protected final BeanItemContainer<Leveldto> productFilterContainer = new BeanItemContainer<>(Leveldto.class);

	/**
	 * Bean container for selected customers.
	 */
	protected ExtTreeContainer<Leveldto> selectedCustomerContainer = new ExtTreeContainer<>(Leveldto.class);

	/**
	 * Bean container for selected Product.
	 */
	protected ExtTreeContainer<Leveldto> selectedProductContainer = new ExtTreeContainer<>(Leveldto.class);

	/**
	 * Bean container for result table.
	 */
	protected final BeanItemContainer<DataSelectionDTO> resultsContainer = new BeanItemContainer<>(
			DataSelectionDTO.class);

	/**
	 * NativeSelect for productForecastLevel.
	 */
	protected NativeSelect productForecastLevel = addDefaultNativeSelect();

	/**
	 * Container for productForecastLevel.
	 */
	protected IndexedContainer productForecastLevelContainer = new IndexedContainer();
	protected IndexedContainer defaultProductForecastLevelContainer = new IndexedContainer();

	/**
	 * Container for customerInnerLevel.
	 */
	protected final IndexedContainer customerInnerLevelContainer = new IndexedContainer();
	private final IndexedContainer defaultCustomerInnerLevelContainer = new IndexedContainer();

	/**
	 * Container for productInnerLevel.
	 */
	protected final IndexedContainer productInnerLevelContainer = new IndexedContainer();
	private final IndexedContainer defaultProductInnerLevelContainer = new IndexedContainer();
	private final IndexedContainer defaultCompanyDdlbContainer = new IndexedContainer();

	/**
	 * Customer hierarchy DTO.
	 */
	protected HierarchyLookupDTO customerHierarchyDto;
	protected List<String> relationshipBuilderSids = new ArrayList<>();
	/**
	 * Product hierarchy DTO.
	 */
	protected HierarchyLookupDTO productHierarchyDto;

	/**
	 * The inner cust levels.
	 */
	protected List<Leveldto> innerCustLevels = new ArrayList<>();
	protected List<String> groupFilteredCompanies = Collections.emptyList();
	protected List<String> groupFilteredItems = null;

	/**
	 * The inner cust levels.
	 */
	protected List<Leveldto> innerProdLevels = new ArrayList<>();

	protected GroupDTO selectedProductGroupDTO;
	protected GroupDTO selectedCustomerGroupDTO;
	protected TextField projectionId = new TextField();
	final ErrorLabel errorMsg = new ErrorLabel();

	protected TextField viewId = new TextField();
	private TextField viewName = new TextField();
	protected List<String> helperTableListNames;
	protected List<String> companiesInProdHier;

	protected LazyBeanItemContainer<DataSelectionDTO> resultsLazyContainer;
	protected ViewDTO viewDTO;
	boolean resetFlag = false;
	protected boolean customerLevelListenerFlag = true;
	protected boolean productLevelListenerFlag = true;

	protected String selectedCustomerLevel = StringUtils.EMPTY;
	protected String selectedProductLevel = StringUtils.EMPTY;

	protected Map<String, String> customerDescriptionMap = null;
	protected Map<String, String> productDescriptionMap = null;
	protected boolean dismantleCustomerSelection = true;
	protected boolean dismantleProductSelection = true;
	/**
	 * DTO object for DataSelection.
	 */
	protected DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();

	/**
	 * Binder for DataSelection.
	 */
	private CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(
			new BeanItem<DataSelectionDTO>(dataSelectionDTO));

	/**
	 * Container for customerForecastLevel.
	 */
	protected final IndexedContainer customerForecastLevelContainer = new IndexedContainer();
	private final IndexedContainer defaultCustomerForecastLevelContainer = new IndexedContainer();
	/**
	 * Screen Name
	 */
	protected String screenName = StringUtils.EMPTY;
	boolean landingScreenFlag;
	/**
	 * The deductionLevel DDLB
	 */
	protected ComboBox deductionLevel = new ComboBox();
	/**
	 * the deduction Value ddlb
	 */
	protected ComboBox deductionValue = new ComboBox();



	public ForecastDataSelection(CustomFieldGroup dataSelectionBinder, String screenName, boolean landingScreenFlag) {
		setCompositionRoot(Clara.create(ForecastDataSelection.class.getResourceAsStream("/ui/forecast/dataSelectionIndex.xml"), this));
		this.dataSelectionBinder = dataSelectionBinder;
		this.screenName = screenName;
		this.landingScreenFlag = landingScreenFlag;
		addComponent();
		dataSelectionBinder = getBinder();
		configureFields(screenName);

	}

	private CustomFieldGroup getBinder() {
		dataSelectionBinder.bindMemberFields(this);
		dataSelectionBinder.setItemDataSource(new BeanItem<DataSelectionDTO>(dataSelectionDTO));
		dataSelectionBinder.setBuffered(true);
		dataSelectionBinder.setErrorDisplay(errorMsg);
		return dataSelectionBinder;
	}

	private void configureFields(String screenName) {
		LOGGER.debug("configureFields---");

		try {
			resizeDdlb();
			if (HeaderUtils.RETURNS.equals(screenName)) {
				customerSelection.setVisible(false);
				configureProductSelection();
				configureProductDdlb();
			} else {
				configureCustomerSelection();
				configureProductSelection();
				configureCustomerDdlb();
				configureCustomerVersionDdlb();
				configureProductVersionDdlb();
			}

			deleteViewBtn.setEnabled(false);
			resultResetBtn.setEnabled(false);
			editBtn.setEnabled(false);
			viewBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			publicView.setWidth(HeaderUtils.TWO_ONE_SEVEN_PX);
			privateView.setWidth(HeaderUtils.TWO_ONE_SEVEN_PX);
			toPeriod.setEnabled(false);
			modeOption.setImmediate(true);
			modeOption.addItem(UIUtil.MODE_ADD);
			modeOption.addItem(UIUtil.MODE_SEARCH);
			modeOption.select(UIUtil.MODE_ADD);
			fromPeriod.setImmediate(true);
			fromPeriod.setNullSelectionAllowed(false);
			toPeriod.setImmediate(true);
			toPeriod.setNullSelectionAllowed(false);
			modeOption.addValueChangeListener(new Property.ValueChangeListener() {

				private static final long serialVersionUID = 1L;

				public void valueChange(Property.ValueChangeEvent event) {
					try {
						resetFlag = true;
						if (UIUtil.MODE_ADD.equals(String.valueOf(modeOption.getValue()))) {
							resetTwo();
							searchBtn.setEnabled(false);
							generateBtn.setEnabled(true);
							toPeriod.setEnabled(false);
							saveViewBtn.setEnabled(true);
							resultResetBtn.setEnabled(false);
							editBtn.setEnabled(false);
							viewBtn.setEnabled(false);
							deleteBtn.setEnabled(false);
							modeOptionChange(true);
						} else {
							resetTwo();
							generateBtn.setEnabled(false);
							searchBtn.setEnabled(true);
							toPeriod.setImmediate(true);
							toPeriod.setEnabled(true);
							resultResetBtn.setEnabled(true);
							editBtn.setEnabled(true);
							viewBtn.setEnabled(true);
							deleteBtn.setEnabled(true);
							saveViewBtn.setEnabled(false);
							modeOptionChange(false);
						}
						resetFlag = false;
					} catch (Exception ex) {
						LOGGER.error(ex + " in modeOption ValueChangeListener ");
					}
				}

			});

			publicView.addClickListener(new CustomTextField.ClickListener() {

				public void click(CustomTextField.ClickEvent event) {
					loadPublicView();
				}
			});

			privateView.addClickListener(new CustomTextField.ClickListener() {

				public void click(CustomTextField.ClickEvent event) {
					loadPrivateView();
				}
			});

			configureTimeDdlb(fromPeriod, toPeriod, null, null, UIUtil.MODE_ADD, screenName);
			modeOption.focus();
			projectionName.setImmediate(true);
			description.setImmediate(true);
			privateView.setImmediate(true);
			publicView.setImmediate(true);
			customerHierarchy.setImmediate(true);
			productHierarchy.setImmediate(true);
			customerGroup.setImmediate(true);
			productGroup.setImmediate(true);
			addValidations();
		} catch (Exception e) {
			LOGGER.error(e);
		}
		LOGGER.debug("configureFields ENDS---");
	}

	private void resizeDdlb() {
		String width = "250px";
		customerLevel.setWidth(width);
		productLevel.setWidth(width);
		level.setWidth(width);
		productlevelDdlb.setWidth(width);
		customerRelationComboBox.setWidth(width);
		productRelation.setWidth(width);
		company.setWidth(width);
		businessUnit.setWidth(width);
	}

	protected abstract void resetTwo();

	protected void resetCustomerLevel() {
		customerLevel.setNullSelectionAllowed(true);
		customerLevel.setInputPrompt(UIUtil.SELECT_ONE);
	}

	protected void resetProductLevel() {
		productLevel.setNullSelectionAllowed(true);
		productLevel.setInputPrompt(UIUtil.SELECT_ONE);

	}

	protected void resetSecondCustomerLevel() {
		level.setNullSelectionAllowed(true);
		level.setInputPrompt(UIUtil.SELECT_ONE);
	}

	protected void resetSecondProductLevel() {
		productlevelDdlb.setNullSelectionAllowed(true);
		productlevelDdlb.setInputPrompt(UIUtil.SELECT_ONE);
	}

	protected void setCustomerForecastLevelNullSelection() {
		customerLevel.setNullSelectionAllowed(true);
		customerLevel.setInputPrompt(UIUtil.SELECT_ONE);
		customerLevel.select(null);
	}

	protected void setProductForecastLevelNullSelection() {
		productLevel.setNullSelectionAllowed(true);
		productLevel.setInputPrompt(UIUtil.SELECT_ONE);
		productLevel.select(null);
	}

	protected void setCustomerLevelNullSelection() {
		level.setNullSelectionAllowed(true);
		level.setInputPrompt(UIUtil.SELECT_ONE);
		level.select(null);
	}

	protected void setProductLevelNullSelection() {
		productlevelDdlb.setNullSelectionAllowed(true);
		productlevelDdlb.setInputPrompt(UIUtil.SELECT_ONE);
		productlevelDdlb.select(null);
	}

	/**
	 * set default value to customerRelation
	 */
	protected void setCustomerRelationNullSelection() {
		customerRelationComboBox.setNullSelectionAllowed(true);
		customerRelationComboBox.setInputPrompt(UIUtil.SELECT_ONE);
		customerRelationComboBox.select(null);
	}

	protected void setProductRelationNullSelection() {
		productRelation.setNullSelectionAllowed(true);
		productRelation.setInputPrompt(UIUtil.SELECT_ONE);
		productRelation.select(null);
	}

	public String getSelectedCustomerLevel() {
		return selectedCustomerLevel;
	}

	public void setSelectedCustomerLevel(String selectedCustomerLevel) {
		this.selectedCustomerLevel = selectedCustomerLevel;
	}

	public String getSelectedProductLevel() {
		return selectedProductLevel;
	}

	public void setSelectedProductLevel(String selectedProductLevel) {
		this.selectedProductLevel = selectedProductLevel;
	}

	private void configureCustomerDdlb() {
		LOGGER.debug("configureDdlb called");
		defaultCustomerForecastLevelContainer.addItem(UIUtil.SELECT_ONE);
		defaultCustomerInnerLevelContainer.addItem(UIUtil.SELECT_ONE);
		resetCustomerLevel();
		resetSecondCustomerLevel();
		resetCustomerRelation();
	}

	private void configureProductDdlb() {
		defaultProductForecastLevelContainer.addItem(UIUtil.SELECT_ONE);
		defaultProductInnerLevelContainer.addItem(UIUtil.SELECT_ONE);
		defaultCompanyDdlbContainer.addItem(UIUtil.SELECT_ONE);
		company.select(null);
		businessUnit.select(null);
		resetProductLevel();
		resetSecondProductLevel();
		resetProductRelation();
	}

	/**
	 * Method to reset the customer Relation DDLB
	 */
	private void resetCustomerRelation() {
		customerRelationComboBox.removeAllItems();
		customerRelationComboBox.setImmediate(true);
		customerRelationComboBox.setNullSelectionAllowed(true);
		customerRelationComboBox.setInputPrompt(UIUtil.SELECT_ONE);
	}

	/**
	 * Method to reset the Product Relation DDLB
	 */
	private void resetProductRelation() {
		productRelation.removeAllItems();
		productRelation.setImmediate(true);
		productRelation.setNullSelectionAllowed(true);
		productRelation.setInputPrompt(UIUtil.SELECT_ONE);
	}

	private void addValidations() {
		TextFieldConverter trimmer = new TextFieldConverter();
		projectionName.setMaxLength(LengthConstants.LENGTH_200.getConstant());
		privateView.setMaxLength(LengthConstants.LENGTH_200.getConstant());
		publicView.setMaxLength(LengthConstants.LENGTH_200.getConstant());
		customerHierarchy.setMaxLength(LengthConstants.LENGTH_200.getConstant());
		productHierarchy.setMaxLength(LengthConstants.LENGTH_200.getConstant());
		description.setMaxLength(LengthConstants.LENGTH_500.getConstant());
		projectionName.setConverter(trimmer);
		description.setConverter(trimmer);
		privateView.setConverter(trimmer);
		publicView.setConverter(trimmer);
		customerHierarchy.setConverter(trimmer);
		productHierarchy.setConverter(trimmer);
		customerGroup.setConverter(trimmer);
		productGroup.setConverter(trimmer);
	}

	/**
	 * Generate btn.
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("generateBtn")
	public void generateBtn(Button.ClickEvent event) {
		generateButtonLogic();
	}

	protected void resetOne() {
		availableCustomerContainer = new BeanItemContainer<>(Leveldto.class);
		availableCustomer.setContainerDataSource(availableCustomerContainer);
		availableProductContainer = new BeanItemContainer<>(Leveldto.class);
		availableProduct.setContainerDataSource(availableProductContainer);
		selectedCustomerContainer = new ExtTreeContainer<>(Leveldto.class);
		selectedCustomer.setContainerDataSource(selectedCustomerContainer);
		selectedProductContainer = new ExtTreeContainer<>(Leveldto.class);
		selectedProduct.setContainerDataSource(selectedProductContainer);
		availableProduct.setVisibleColumns(HeaderUtils.DISPLAY_VALUE);
		availableProduct.setColumnHeaders(HeaderUtils.LEVEL);
		availableCustomer.setVisibleColumns(HeaderUtils.DISPLAY_VALUE);
		availableCustomer.setColumnHeaders(HeaderUtils.LEVEL);
		selectedCustomer.setVisibleColumns(HeaderUtils.DISPLAY_VALUE);
		selectedCustomer.setColumnHeaders("Customer Hierarchy Group Builder");
		selectedProduct.setVisibleColumns(HeaderUtils.DISPLAY_VALUE);
		selectedProduct.setColumnHeaders("Product Hierarchy Group Builder");
		availableCustomer.setFilterBarVisible(true);
		availableCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
		availableCustomer.setStyleName(HeaderUtils.FILTERTABLE);
		availableProduct.setFilterBarVisible(true);
		availableProduct.setFilterDecorator(new ExtDemoFilterDecorator());
		availableProduct.setStyleName(HeaderUtils.FILTERTABLE);
		projectionName.setValue(StringUtils.EMPTY);
		projectionId = new CustomTextField();
		viewId = new CustomTextField();
		viewName.setValue(StringUtils.EMPTY);
		description.setValue(StringUtils.EMPTY);
		customerGroup.setValue(StringUtils.EMPTY);
		productGroup.setValue(StringUtils.EMPTY);
		customerHierarchy.setValue(StringUtils.EMPTY);
		productHierarchy.setValue(StringUtils.EMPTY);
		privateView.setValue(StringUtils.EMPTY);
		publicView.setValue(StringUtils.EMPTY);
		selectedCustomerGroupDTO = new GroupDTO();
		selectedProductGroupDTO = new GroupDTO();
		customerHierarchyDto = new HierarchyLookupDTO();
		productHierarchyDto = new HierarchyLookupDTO();
		relationshipBuilderSids.clear();
		viewDTO = null;
		if (HeaderUtils.RETURNS.equals(screenName)) {
			configureProductDdlb();
		} else {
			configureCustomerDdlb();
			configureProductDdlb();
		}
		configureTimeDdlb(fromPeriod, toPeriod, null, null, UIUtil.MODE_ADD, screenName);

	}

	/**
	 * Reset button.
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("resetBtn")
	public void resetBtn(Button.ClickEvent event) {
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
					resetOne();
					deductionLevel.setValue(null);
					deductionValue.setValue(null);
					modeOption.select("Add");
					dismantleCustomerSelection = true;
					dismantleProductSelection = true;
					resetButtonLogic();
				} catch (Exception ex) {
					LOGGER.error(ex + " - in resetBtn");
				}
			}
		}.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");

	}

	/**
	 * Search button logic.
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("searchBtn")
	public void searchBtn(Button.ClickEvent event) {
		searchButtonLogic();
	}

	/**
	 * Edit button logic. Used to edit a projection.
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("editBtn")
	public void editBtn(Button.ClickEvent event) {
		editButtonLogic();
	}

	/**
	 * View btn.
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("viewBtn")
	public void viewBtn(Button.ClickEvent event) {
		viewButtonLogic();
	}

	/**
	 * Delete btn.
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("deleteBtn")
	public void deleteBtn(Button.ClickEvent event) {
		deleteButtonLogic();
	}

	protected void reloadResultsTable() {
		searchBtn(null);
	}

	/**
	 * Product group.
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("productGP")
	public void productGroup(CustomTextField.ClickEvent event)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		loadProductGroup();
	}

	/**
	 * Customer group.
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("customerGP")
	public void customerGroup(CustomTextField.ClickEvent event)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		loadCustomerGroup();
	}

	/**
	 * Save view btn.
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("saveViewBtn")
	public void saveViewBtn(Button.ClickEvent event) {
		saveViewButtonlogic();
	}

	/**
	 * Move left btn.
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("moveLeftBtn")
	public void moveLeftBtn(Button.ClickEvent event) {
		moveLeftButtonLogic();
	}

	@UiHandler("allBtn")
	public void allBtn(Button.ClickEvent event) {
		moveAllButtonLogic();
	}

	@UiHandler("allProductBtn")
	public void allProductBtn(Button.ClickEvent event) {
		moveAllProductButtonLogic();
	}

	/**
	 * Move left btn.
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("moveRightBtn")
	public void moveRightBtn(Button.ClickEvent event) {
		moveRigthButtonLogic();
	}

	/**
	 * Move left product.
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("moveRightProduct")
	public void moveRightProduct(Button.ClickEvent event) {
		moveRigthProductButtonLogic();
	}

	/**
	 * Move right product.
	 *
	 * @param event
	 *            the event
	 * @throws java.lang.Exception
	 */
	@UiHandler("moveLeftProduct")
	public void moveLeftProduct(Button.ClickEvent event) {
		moveLeftProductButtonLogic();
	}

	/**
	 * Result reset btn.
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("resultResetBtn")
	public void resultResetBtn(Button.ClickEvent event) {
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
				resetTwo();
			}
		}.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");
	}

	/**
	 * Delete view btn.
	 *
	 * @param event
	 *            the event
	 */
	@UiHandler("deleteViewBtn")
	public void deleteViewBtn(Button.ClickEvent event) {
		deleteViewButtonLogic();
	}

	/**
	 * Gets the bean from id.
	 *
	 * @param obj
	 *            the id
	 * @return the bean from id
	 */
	public static Leveldto getBeanFromId(Object obj) {

		BeanItem<?> targetItem = null;
		if (obj instanceof BeanItem<?>) {
			targetItem = (BeanItem<?>) obj;
		} else if (obj instanceof Leveldto) {
			targetItem = new BeanItem<>((Leveldto) obj);
		}

		return (Leveldto) targetItem.getBean();
	}

	public ViewDTO getViewDTO() {
		return viewDTO;
	}

	public void setViewDTO(ViewDTO viewDTO) {
		this.viewDTO = viewDTO;
	}

	protected abstract void productLevelDdlbValueChange(String selectedLevel, boolean flag);

	protected abstract void customerHierarchyLookUp()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	protected abstract void productHierarchyLookUp()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	protected abstract void modeOptionChange(boolean isAddMode);

	protected abstract void customerLevelValueChange(Property.ValueChangeEvent event, boolean flag);

	protected abstract void levelValueChangeListener(Object value)
			throws ClassNotFoundException, CloneNotSupportedException, IOException;

	protected abstract void productLevelValueChange(Object value, boolean flag);

	protected abstract void customerRelationValueChange(Object value);

	protected abstract void productRelationValueChange(Object value);

	protected abstract void generateButtonLogic();

	protected abstract void searchButtonLogic();

	protected abstract void editButtonLogic();

	protected abstract void viewButtonLogic();

	protected abstract void deleteButtonLogic();

	protected abstract void configureTimeDdlb(ComboBox fromPeriod, ComboBox toPeriod, Object object, Object object0,
			String constant, String screenName);

	protected abstract void loadPublicView();

	protected abstract void loadPrivateView();

	protected abstract void loadProductGroup()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	protected abstract void loadCustomerGroup()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException;

	protected abstract void saveViewButtonlogic();

	protected abstract void moveLeftButtonLogic();

	protected abstract void moveAllButtonLogic();

	protected abstract void moveAllProductButtonLogic();

	protected abstract void moveRigthButtonLogic();

	protected abstract void moveRigthProductButtonLogic();

	protected abstract void moveLeftProductButtonLogic();

	protected abstract void deleteViewButtonLogic();

	protected abstract void resetButtonLogic();

	public abstract void loadProductVersionNo(Object selectedProductRelation);

	public abstract void loadCustomerVersionNo(Object selectedProductRelation);
	
	protected abstract void loadForecastLevels(List<Leveldto> innerLevels, IndexedContainer productForecastLevelContainer, ComboBox level, int hierarchySid, int hierarchyVersion);


	/**
	 * Adds a default native select with only -Select One- in list
	 *
	 * @return default native select
	 */
	public static NativeSelect addDefaultNativeSelect() {
		NativeSelect defaultNativeSelect = new NativeSelect();
		defaultNativeSelect.setNullSelectionAllowed(true);
		defaultNativeSelect.setNullSelectionItemId(UIUtil.SELECT_ONE);
		return defaultNativeSelect;
	}

	private void addComponent() {

		if (screenName.equals(HeaderUtils.RETURNS)) {
			gridLayoutTimeperiod.setVisible(false);
			GridLayout layoutT1 = new GridLayout(NumericConstants.FOUR, 1);
			layoutT1.setSpacing(Boolean.TRUE);
			layoutT1.addComponent(new Label("From: ") {
				{
				}
			});
			layoutT1.addComponent(fromPeriod);
			layoutT1.addComponent(new Label("To: ") {
				{
				}
			});
			layoutT1.addComponent(toPeriod);
			verticalLayoutTimeperiod.addComponent(layoutT1);
			GridLayout layoutG1 = new GridLayout(NumericConstants.SIX, 1);
			layoutG1.addComponent(new Label(HeaderUtils.MODE) {
				{
					setWidth("50px");
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			modeOption.setStyleName(HeaderUtils.HORIZONTAL);
			layoutG1.addComponent(new Label("") {
				{
					setWidth("50px");
				}
			});
			layoutG1.addComponent(modeOption);
			layoutG1.addComponent(new Label("") {
				{
					setWidth("53px");
				}
			});

			GridLayout layoutG2 = new GridLayout(NumericConstants.SIX, NumericConstants.TWO);
			layoutG2.addComponent(new Label(HeaderUtils.PRIVATE_VIEWS) {
				{
					setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			privateView.setStyleName(HeaderUtils.SEARCH_TEXT);
			privateView.setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
			layoutG2.addComponent(privateView);

			layoutG2.addComponent(new Label(HeaderUtils.COMPANY) {
				{
					setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			layoutG2.addComponent(company);

			layoutG2.addComponent(new Label(HeaderUtils.PROJECTION_NAME) {
				{
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			layoutG2.addComponent(projectionName);
			layoutG2.addComponent(new Label(HeaderUtils.PUBLIC_VIEWS) {
				{
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});

			publicView.setStyleName(HeaderUtils.SEARCH_TEXT);
			layoutG2.addComponent(publicView);

			layoutG2.addComponent(new Label(HeaderUtils.BUSINESS_UNIT) {
				{
					setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			layoutG2.addComponent(businessUnit);

			layoutG2.addComponent(new Label(HeaderUtils.DESCRIPTION) {
				{
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			layoutG2.addComponent(description);

			verticalLayout.addComponent(layoutG1);
			verticalLayout.addComponent(layoutG2);

			productSelectionGrid2.setVisible(false);
			GridLayout layoutG3 = new GridLayout(NumericConstants.FOUR, NumericConstants.TWO);

			layoutG3.addComponent(new Label("Hierarchy: ") {
				{
					setWidth("50px");
				}
			});
			layoutG3.addComponent(productHierarchy);
			layoutG3.addComponent(new Label("Relationship: ") {
				{
					setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
				}
			});
			layoutG3.addComponent(productRelation);
			layoutG3.addComponent(new Label("Forecast Level: ") {
				{
					setWidth("104px");
				}
			});
			layoutG3.addComponent(productLevel);
			layoutG3.addComponent(new Label("Product Group: ") {
				{
					setWidth("108px");
				}
			});
			layoutG3.addComponent(productGroup);
			productGroup.setWidth(HeaderUtils.TWO_ONE_SEVEN_PX);
			ProdSelectionHLayout.addComponent(layoutG3);
		} else if ("AccrualRateProjection".equals(screenName)) {
			// "Accruals Projection".equals(screenName)
			GridLayout layoutG1 = new GridLayout(NumericConstants.TWO, 1);
			layoutG1.addComponent(new Label(HeaderUtils.MODE) {
				{
					setWidth("50px");
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			modeOption.setStyleName(HeaderUtils.HORIZONTAL);
			layoutG1.addComponent(modeOption);
			GridLayout layoutG2 = new GridLayout(NumericConstants.EIGHT, NumericConstants.TWO);
			layoutG2.addComponent(new Label(HeaderUtils.PRIVATE_VIEWS) {
				{
					setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			privateView.setStyleName(HeaderUtils.SEARCH_TEXT);
			layoutG2.addComponent(privateView);

			layoutG2.addComponent(new Label(HeaderUtils.COMPANY) {
				{
					setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			layoutG2.addComponent(company);

			layoutG2.addComponent(new Label(HeaderUtils.PROJECTION_NAME) {
				{
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			layoutG2.addComponent(projectionName);
			layoutG2.addComponent(new Label("Deduction Level:") {
				{
					setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			layoutG2.addComponent(deductionLevel);
			layoutG2.addComponent(new Label(HeaderUtils.PUBLIC_VIEWS) {
				{
					setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			publicView.setStyleName(HeaderUtils.SEARCH_TEXT);
			layoutG2.addComponent(publicView);

			layoutG2.addComponent(new Label(HeaderUtils.BUSINESS_UNIT) {
				{
					setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			layoutG2.addComponent(businessUnit);

			layoutG2.addComponent(new Label(HeaderUtils.DESCRIPTION) {
				{
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			layoutG2.addComponent(description);
			layoutG2.addComponent(new Label("Deduction Value:") {
				{
					setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			layoutG2.addComponent(deductionValue);
			verticalLayout.addComponent(layoutG1);
			verticalLayout.addComponent(layoutG2);

		} else {
			GridLayout layoutG1 = new GridLayout(NumericConstants.TWO, 1);
			layoutG1.addComponent(new Label(HeaderUtils.MODE) {
				{
					setWidth("50px");
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			modeOption.setStyleName(HeaderUtils.HORIZONTAL);
			layoutG1.addComponent(modeOption);
			GridLayout layoutG2 = new GridLayout(NumericConstants.SIX, NumericConstants.TWO);
			layoutG2.addComponent(new Label(HeaderUtils.PRIVATE_VIEWS) {
				{
					setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			privateView.setStyleName(HeaderUtils.SEARCH_TEXT);
			layoutG2.addComponent(privateView);
			layoutG2.addComponent(new Label(HeaderUtils.COMPANY) {
				{
					setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			layoutG2.addComponent(company);

			layoutG2.addComponent(new Label(HeaderUtils.PROJECTION_NAME) {
				{
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			layoutG2.addComponent(projectionName);
			layoutG2.addComponent(new Label(HeaderUtils.PUBLIC_VIEWS) {
				{
					setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});

			publicView.setStyleName(HeaderUtils.SEARCH_TEXT);
			layoutG2.addComponent(publicView);
			layoutG2.addComponent(new Label(HeaderUtils.BUSINESS_UNIT) {
				{
					setWidth(HeaderUtils.ONE_ZERO_ZERO_PX);
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			layoutG2.addComponent(businessUnit);

			layoutG2.addComponent(new Label(HeaderUtils.DESCRIPTION) {
				{
					setContentMode(ContentMode.HTML);
					setStyleName(HeaderUtils.LABEL_RESULT_ALIGN);
				}
			});
			layoutG2.addComponent(description);
			verticalLayout.addComponent(layoutG1);
			verticalLayout.addComponent(layoutG2);
		}
	}

	/**
	 * configure customer Selection
	 */
	private void configureCustomerSelection() {
		selectedCustomer.setSortEnabled(false);
		customerHierarchy.setWidth(HeaderUtils.TWO_ONE_SEVEN_PX);
		customerLevel.setImmediate(true);
		level.setImmediate(true);
		availableCustomer.setContainerDataSource(availableCustomerContainer);
		selectedCustomer.setContainerDataSource(selectedCustomerContainer);
		availableCustomer.setImmediate(true);
		selectedCustomer.setImmediate(true);
		availableCustomer.setVisibleColumns(HeaderUtils.DISPLAY_VALUE);
		availableCustomer.setColumnHeaders(HeaderUtils.LEVEL);
		selectedCustomer.setVisibleColumns(HeaderUtils.DISPLAY_VALUE);
		selectedCustomer.setColumnHeaders("Customer Hierarchy Group Builder");
		availableCustomer.setSelectable(true);
		selectedCustomer.setSelectable(true);
		customerHierarchy.addStyleName("searchIcon");

		customerHierarchy.addClickListener(new CustomTextField.ClickListener() {

			@Override
			public void click(CustomTextField.ClickEvent event) {
				try {
					customerHierarchyLookUp();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
					java.util.logging.Logger.getLogger(ForecastDataSelection.class.getName()).log(Level.SEVERE, null,
							ex);
				}
			}
		});

		if (landingScreenFlag) {
			customerLevel.addValueChangeListener(new Property.ValueChangeListener() {

				@Override
				public void valueChange(final Property.ValueChangeEvent event) {
					LOGGER.debug("customerLevel - ValueChangeListener");
					if (!selectedCustomerContainer.getItemIds().isEmpty() && customerLevelListenerFlag) {
						final String customerLevelValue = getSelectedCustomerLevel();
						new AbstractNotificationUtils() {

							public void noMethod() {
								// do nothing
								if (!StringUtils.isBlank(customerLevelValue)) {
									customerLevelListenerFlag = false;
									customerLevel.select(customerLevelValue);
									customerLevelListenerFlag = true;
								}
							}

							@Override
							/**
							 * The method is triggered when Yes button of the
							 * message box is pressed .
							 *
							 * @param buttonId
							 *            The buttonId of the pressed button.
							 */

							public void yesMethod() {
								customerLevelValueChange(event, true);
							}
						}.getConfirmationMessage("Confirm Change",
								"You have selected a new Forecast Level. Are you sure you want to proceed? You will lose the current Customer/Product hierarchies if you continue.");
					} else {
						if (customerLevelListenerFlag) {
							customerLevelValueChange(event, false);
						}
					}

				}
			});
		}
		level.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				try {
					levelValueChangeListener(event.getProperty().getValue());
				} catch (ClassNotFoundException | CloneNotSupportedException | IOException e) {
					LOGGER.error(e + " - in resetBtn");
				}
			}
		});

		customerRelationComboBox.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				LOGGER.debug("customerRelation - ValueChangeListener ");
				customerRelationValueChange(event.getProperty().getValue());

			}
		});
	}

	/**
	 * configure product Selection
	 */
	private void configureProductSelection() {
		selectedProduct.setSortEnabled(false);
		productHierarchy.setWidth(HeaderUtils.TWO_ONE_SEVEN_PX);
		availableProduct.setContainerDataSource(availableProductContainer);
		selectedProduct.setContainerDataSource(selectedProductContainer);
		availableProduct.setImmediate(true);
		selectedProduct.setImmediate(true);
		availableProduct.setVisibleColumns(new Object[] { HeaderUtils.DISPLAY_VALUE });
		availableProduct.setColumnHeaders(new String[] { HeaderUtils.LEVEL });
		selectedProduct.setVisibleColumns(new Object[] { HeaderUtils.DISPLAY_VALUE });
		selectedProduct.setColumnHeaders(new String[] { "Product Hierarchy Group Builder" });
		availableProduct.setSelectable(true);
		selectedProduct.setSelectable(true);
		availableProduct.setFilterBarVisible(true);
		availableProduct.setFilterDecorator(new ExtDemoFilterDecorator());
		availableProduct.setStyleName(HeaderUtils.FILTERTABLE);

		productHierarchy.addClickListener(new CustomTextField.ClickListener() {

			@Override
			public void click(CustomTextField.ClickEvent event) {
				try {
					productHierarchyLookUp();
				} catch (InstantiationException ex) {
					java.util.logging.Logger.getLogger(ForecastDataSelection.class.getName()).log(Level.SEVERE, null,
							ex);
				} catch (IllegalAccessException ex) {
					java.util.logging.Logger.getLogger(ForecastDataSelection.class.getName()).log(Level.SEVERE, null,
							ex);
				} catch (ClassNotFoundException ex) {
					java.util.logging.Logger.getLogger(ForecastDataSelection.class.getName()).log(Level.SEVERE, null,
							ex);
				}
			}
		});

		if (landingScreenFlag) {

			productLevel.addValueChangeListener(new Property.ValueChangeListener() {
				@Override
				public void valueChange(final Property.ValueChangeEvent event) {
					LOGGER.debug("product Level - ValueChangeListener ");
					if (!selectedProductContainer.getItemIds().isEmpty() && productLevelListenerFlag) {
						final String productLevelValue = getSelectedProductLevel();
						new AbstractNotificationUtils() {
							public void noMethod() {
								// do nothing
								if (!StringUtils.isBlank(productLevelValue)) {
									productLevelListenerFlag = false;
									productLevel.select(productLevelValue);
									productLevelListenerFlag = true;
								}
							}

							@Override
							/**
							 * The method is triggered when Yes button of the
							 * message box is pressed .
							 *
							 * @param buttonId
							 *            The buttonId of the pressed button.
							 */
							public void yesMethod() {
								productLevelValueChange(event.getProperty().getValue(), true);
							}
						}.getConfirmationMessage("Confirm Change",
								"You have selected a new Forecast Level. Are you sure you want to proceed? You will lose the current Customer/Product hierarchies if you continue.");
					} else {
						if (productLevelListenerFlag) {
							productLevelValueChange(event.getProperty().getValue(), false);
						}
					}
				}
			});

		}

		productlevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				String selectedLevel = String.valueOf(event.getProperty().getValue());
				LOGGER.debug("product inner Level - ValueChangeListener selectedLevel " + selectedLevel);

				productLevelDdlbValueChange(selectedLevel, false);
			}
		});

		productRelation.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				productRelationValueChange(productRelation.getValue());

			}

		});
	}

	private void configureCustomerVersionDdlb() {
		customerRelationVersionComboBox.setNullSelectionAllowed(Boolean.TRUE);
		customerRelationVersionComboBox.setNullSelectionItemId(UIUtil.SELECT_ONE);
		customerRelationVersionComboBox.addItem(UIUtil.SELECT_ONE);
		customerRelationVersionComboBox.select(UIUtil.SELECT_ONE);
		customerRelationVersionLabel.setVisible(Boolean.FALSE);
		customerRelationVersionComboBox.setVisible(Boolean.FALSE);

	}

	private void configureProductVersionDdlb() {
		productRelationVersionComboBox.setNullSelectionAllowed(Boolean.TRUE);
		productRelationVersionComboBox.setNullSelectionItemId(UIUtil.SELECT_ONE);
		productRelationVersionComboBox.addItem(UIUtil.SELECT_ONE);
		productRelationVersionComboBox.select(UIUtil.SELECT_ONE);
		productRelationVersionLabel.setVisible(Boolean.FALSE);
		productRelationVersionComboBox.setVisible(Boolean.FALSE);

	}

	@UiHandler("customerRelationVersion")
	public void customerRelationVersionComboBoxValueChange(Property.ValueChangeEvent event) {

	}

	@UiHandler("productRelationVersion")
	public void productRelationVersionComboBoxValueChange(Property.ValueChangeEvent event) {

	}
        
        protected Object loadComboBoxBasedOnRelationshipVersion(ComboBox relationShipVersionComboBox, List<Object[]> resultList) {
            Object returnValue = 0;
            if (resultList != null && !resultList.isEmpty()) {
                for (int i = 0; i < resultList.size(); i++) {
                    Object[] resultListValue = resultList.get(i);
                    relationShipVersionComboBox.addItem(resultListValue[1]);
                    relationShipVersionComboBox.setItemCaption(resultListValue[1], resultListValue[0].toString());
                    if (i == 0) {
                        returnValue = resultListValue[1];
                    }
                }
            }
            return returnValue;
        }

}