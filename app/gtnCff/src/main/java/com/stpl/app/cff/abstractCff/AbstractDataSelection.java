/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.abstractCff;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.ui.forecastds.form.ForecastDataSelection;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.UIUtil;
import com.stpl.ifs.ui.util.converters.TextFieldConverter;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author mohamed.hameed
 */
public abstract class AbstractDataSelection extends CustomComponent implements View {

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(ForecastDataSelection.class);
	/**
	 * The private view.
	 */
	protected CustomTextField privateView = new CustomTextField();
	/**
	 * The public view.
	 */
	protected CustomTextField publicView = new CustomTextField();
	protected ComboBox discount = new ComboBox();
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
	protected ComboBox customerRelation;
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
	/**
	 * The company.
	 */

	protected ComboBox company = new ComboBox();
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
	
        @UiField("productRelation")
	protected ComboBox productRelation;
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
	@UiField("verticalLayout")
	protected VerticalLayout verticalLayout;
	@UiField("buttonLay")
	protected HorizontalLayout buttonLay;
	@UiField("productBtnLayout")
	protected GridLayout productBtnLayout;
	@UiField("customerBtnLayout")
	protected GridLayout customerBtnLayout;
	@UiField("horizontalLayout")
	protected HorizontalLayout horizontalLayout;
	@UiField("ProdSelectionHLayout")
	protected HorizontalLayout prodSelectionHLayout;
	@UiField("productSelectionGrid2")
	protected GridLayout productSelectionGrid2;
	@UiField("ProdSelectionVLayout")
	protected VerticalLayout prodSelectionVLayout;
	@UiField("panel3")
	protected Panel panel3;
	@UiField("customerSelection")
	protected Panel customerSelection;

	@UiField("productRelationVersionLabel")
	protected Label productRelationVersionLabel;
	@UiField("productRelationVersion")
	protected ComboBox productRelationVersionComboBox;
	@UiField("customerRelationVersionLabel")
	protected Label customerRelationVersionLabel;
	@UiField("customerRelationVersion")
	protected ComboBox customerRelationVersionComboBox;

	public ComboBox businessUnit = new ComboBox();

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
	protected List<String> groupFilteredCompanies = null;
	protected List<String> groupFilteredItems = null;
	/**
	 * The inner cust levels.
	 */
	protected List<Leveldto> innerProdLevels = new ArrayList<>();
	protected GroupDTO selectedProductGroupDTO;
	protected GroupDTO selectedCustomerGroupDTO;
	protected TextField projectionId = new TextField();
	private final ErrorLabel errorMsg = new ErrorLabel();
	protected TextField viewId = new TextField();
	private final TextField viewName = new TextField();
	protected List<String> helperTableListNames;
	protected List<String> companiesInProdHier;
	protected LazyBeanItemContainer<DataSelectionDTO> resultsLazyContainer;
	protected ViewDTO viewDTO;
	protected boolean customerLevelListenerFlag = true;
	protected boolean productLevelListenerFlag = true;
	protected String selectedCustomerLevel = StringUtils.EMPTY;
	protected String selectedProductLevel = StringUtils.EMPTY;
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
	private String screenName = StringUtils.EMPTY;

	private final CFFLogic logic = new CFFLogic();

	public AbstractDataSelection(CustomFieldGroup dataSelectionBinder, String screenName) {
		setCompositionRoot(Clara.create(getClass().getResourceAsStream("/cff/tabs/dataSelection.xml"), this));
		this.dataSelectionBinder = dataSelectionBinder;
		this.screenName = screenName;
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
		customerLevel.setVisible(false);
		productLevel.setVisible(false);
		try {
			resizeDdlb();
			if (ConstantsUtils.RETURNS.equals(screenName)) {
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
			publicView.setWidth(StringConstantsUtil.TWO_SEVENTEEN_PX);
			privateView.setWidth(StringConstantsUtil.TWO_SEVENTEEN_PX);
			businessUnit.setWidth(StringConstantsUtil.TWO_SEVENTEEN_PX);
			company.setWidth(StringConstantsUtil.TWO_SEVENTEEN_PX);
			publicView.addClickListener(new CustomTextField.ClickListener() {
                                @Override
				public void click(CustomTextField.ClickEvent event) {
					loadPublicView();
				}
			});

			privateView.addClickListener(new CustomTextField.ClickListener() {
                                @Override
				public void click(CustomTextField.ClickEvent event) {
					loadPrivateView();
				}
			});
			businessUnit.setPageLength(NumericConstants.SEVEN);
			businessUnit.setImmediate(true);
			businessUnit.addItem(0);
			businessUnit.setItemCaption(0, Constants.CommonConstants.SELECT_ONE.getConstant());
			businessUnit.setNullSelectionAllowed(true);
			businessUnit.setNullSelectionItemId(0);
			businessUnit.setInputPrompt(Constants.CommonConstants.SELECT_ONE.getConstant());
			businessUnit.markAsDirty();

			List<Object[]> list = logic.getBusinessUnits(0);
			if (list != null && !list.isEmpty()) {
				for (Object[] object : list) {
					businessUnit.addItem(object[0]);
					businessUnit.setItemCaption(object[0], object[NumericConstants.TWO] + Constants.SPACE
							+ Constants.DASH_NO_DATA + Constants.SPACE + object[NumericConstants.THREE]);
				}
			}
			businessUnit.select(0);

			company.setPageLength(NumericConstants.SEVEN);
			company.setImmediate(true);
			company.addItem(0);
			company.setItemCaption(0, Constants.CommonConstants.SELECT_ONE.getConstant());
			company.setNullSelectionAllowed(true);
			company.setNullSelectionItemId(0);
			company.setInputPrompt(Constants.CommonConstants.SELECT_ONE.getConstant());
			company.markAsDirty();

			List<Object[]> companyList = logic.getCompanies(0);
			if (companyList != null && !companyList.isEmpty()) {
				for (Object[] object : companyList) {
					company.addItem(object[0]);
					company.setItemCaption(object[0], object[NumericConstants.TWO] + Constants.SPACE
							+ Constants.DASH_NO_DATA + Constants.SPACE + object[NumericConstants.THREE]);
				}
			}
			company.select(0);

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
		customerRelation.setWidth(width);
		productRelation.setWidth(width);

	}

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

	protected void setCustomerRelationNullSelection() {
		customerRelation.setNullSelectionAllowed(true);
		customerRelation.setInputPrompt(UIUtil.SELECT_ONE);
		customerRelation.select(null);
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
		resetProductLevel();
		resetSecondProductLevel();
		resetProductRelation();
	}

	private void resetCustomerRelation() {
		customerRelation.removeAllItems();
		customerRelation.setImmediate(true);
		customerRelation.setNullSelectionAllowed(true);
		customerRelation.setInputPrompt(UIUtil.SELECT_ONE);
	}

	private void resetProductRelation() {
		productRelation.removeAllItems();
		productRelation.setImmediate(true);
		productRelation.setNullSelectionAllowed(true);
		productRelation.setInputPrompt(UIUtil.SELECT_ONE);
	}

	private void addValidations() {
		TextFieldConverter trimmer = new TextFieldConverter();
		privateView.setMaxLength(UIUtil.LengthConstants.LENGTH_200.getConstant());
		publicView.setMaxLength(UIUtil.LengthConstants.LENGTH_200.getConstant());
		customerHierarchy.setMaxLength(UIUtil.LengthConstants.LENGTH_200.getConstant());
		productHierarchy.setMaxLength(UIUtil.LengthConstants.LENGTH_200.getConstant());
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

	public String getProductHierarchyEndLevelsHierNo() {

		StringBuilder returnString = new StringBuilder(StringUtils.EMPTY);

		List<String> productHierarchyEndLevelsHierNo = new ArrayList<>();
		for (Object item : selectedProductContainer.getItemIds()) {
			if (!selectedProductContainer.hasChildren(item)) {
				productHierarchyEndLevelsHierNo.add(getBeanFromId(item).getHierarchyNo());
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

	protected void resetOne() {
		availableCustomerContainer = new BeanItemContainer<>(Leveldto.class);
		availableCustomer.setContainerDataSource(availableCustomerContainer);
		availableProductContainer = new BeanItemContainer<>(Leveldto.class);
		availableProduct.setContainerDataSource(availableProductContainer);
		selectedCustomerContainer = new ExtTreeContainer<>(Leveldto.class);
		selectedCustomer.setContainerDataSource(selectedCustomerContainer);
		selectedProductContainer = new ExtTreeContainer<>(Leveldto.class);
		selectedProduct.setContainerDataSource(selectedProductContainer);
		availableProduct.setVisibleColumns(StringConstantsUtil.DISPLAY_VALUE);
		availableProduct.setColumnHeaders(StringConstantsUtil.LEVEL);
		availableCustomer.setVisibleColumns(StringConstantsUtil.DISPLAY_VALUE);
		availableCustomer.setColumnHeaders(StringConstantsUtil.LEVEL);
		selectedCustomer.setVisibleColumns(StringConstantsUtil.DISPLAY_VALUE);
		selectedCustomer.setColumnHeaders("Customer Hierarchy Group Builder");
		selectedProduct.setVisibleColumns(StringConstantsUtil.DISPLAY_VALUE);
		selectedProduct.setColumnHeaders("Product Hierarchy Group Builder");
		projectionId = new CustomTextField();
		viewId = new CustomTextField();
		viewName.setValue(StringUtils.EMPTY);
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
		if (ConstantsUtils.RETURNS.equals(screenName)) {
			configureProductDdlb();
		} else {
			configureCustomerDdlb();
			configureProductDdlb();
		}

		dataSelectionDTO.clear();
		company.select(0);
		businessUnit.select(0);
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
					resetOne();
					resetButtonLogic();
				} catch (Exception ex) {
					LOGGER.error(ex);
				}
			}
		}.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");

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

	protected abstract void customerLevelValueChange(Property.ValueChangeEvent event, boolean flag);

	protected abstract void levelValueChangeListener(Object value);

	protected abstract void productLevelValueChange(Object value, boolean flag);

	protected abstract void customerRelationValueChange(Object value);

	protected abstract void productRelationValueChange(Object value);

	protected abstract void generateButtonLogic();

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

	protected abstract void loadProductVersionNo(Object selectedProductRelation);

	protected abstract void loadCustomerVersionNo(Object selectedCustomerRelation);

	protected abstract void loadForecastLevels(List<Leveldto> innerLevels,
			IndexedContainer productForecastLevelContainer, ComboBox level, int hierarchySid, int hierarchyVersion);

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
		Label empty = new Label(StringUtils.EMPTY, ContentMode.HTML);
		empty.setWidth("15px");
		GridLayout layoutG2 = new GridLayout(NumericConstants.TWELVE, NumericConstants.ONE);
		layoutG2.setMargin(Boolean.FALSE);
		layoutG2.addComponent(new Label("Private Views:") {
			{
				setWidth(StringConstantsUtil.HUNDRED_PX);
				setContentMode(ContentMode.HTML);
				setStyleName(StringConstantsUtil.LABEL_RESULT_ALIGN);
			}
		});
		privateView.setStyleName("searchText");
		layoutG2.addComponent(privateView);
		layoutG2.addComponent(empty);
		layoutG2.addComponent(new Label("Public Views:") {
			{
				setWidth(StringConstantsUtil.HUNDRED_PX);
				setContentMode(ContentMode.HTML);
				setStyleName(StringConstantsUtil.LABEL_RESULT_ALIGN);
			}
		});

		publicView.setStyleName("searchText");
		layoutG2.addComponent(publicView);
		empty = new Label(StringUtils.EMPTY, ContentMode.HTML);
		empty.setWidth("15px");
		layoutG2.addComponent(empty);
		layoutG2.addComponent(new Label("Company:") {
			{
				setWidth(StringConstantsUtil.HUNDRED_PX);
				setContentMode(ContentMode.HTML);
				setStyleName(StringConstantsUtil.LABEL_RESULT_ALIGN);
			}
		});

		layoutG2.addComponent(company);
		empty = new Label(StringUtils.EMPTY, ContentMode.HTML);
		empty.setWidth("15px");
		layoutG2.addComponent(empty);
		layoutG2.addComponent(new Label("Business Unit:") {
			{
				setWidth(StringConstantsUtil.HUNDRED_PX);
				setContentMode(ContentMode.HTML);
				setStyleName(StringConstantsUtil.LABEL_RESULT_ALIGN);
			}
		});

		layoutG2.addComponent(businessUnit);
		layoutG2.addComponent(new Label(StringUtils.EMPTY, ContentMode.HTML));
		verticalLayout.addComponent(layoutG2);
	}

	private void configureCustomerSelection() {
		availableCustomer.setFilterBarVisible(true);
		availableCustomer.setStyleName(Constants.FILTER_TABLE);
		selectedCustomer.setSortEnabled(false);
		customerHierarchy.setWidth(StringConstantsUtil.TWO_SEVENTEEN_PX);
		customerLevel.setImmediate(true);
		level.setImmediate(true);
		availableCustomer.setContainerDataSource(availableCustomerContainer);
		selectedCustomer.setContainerDataSource(selectedCustomerContainer);
		availableCustomer.setImmediate(true);
		selectedCustomer.setImmediate(true);
		availableCustomer.setVisibleColumns(new Object[] { StringConstantsUtil.DISPLAY_VALUE });
		availableCustomer.setColumnHeaders(new String[] { StringConstantsUtil.LEVEL });
		selectedCustomer.setVisibleColumns(new Object[] { StringConstantsUtil.DISPLAY_VALUE });
		selectedCustomer.setColumnHeaders(new String[] { "Customer Hierarchy Group Builder" });
		availableCustomer.setSelectable(true);
		selectedCustomer.setSelectable(true);
		customerHierarchy.addStyleName(ConstantsUtils.SEARCH_ICON);
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

		customerLevel.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				LOGGER.debug("customerLevel - ValueChangeListener");
				if (!selectedCustomerContainer.getItemIds().isEmpty() && customerLevelListenerFlag) {
					final String customerLevelValue = getSelectedCustomerLevel();
					new AbstractNotificationUtils() {
                        @Override
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
		level.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {

				levelValueChangeListener(event.getProperty().getValue());
			}
		});

		customerRelation.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				LOGGER.debug("customerRelation - ValueChangeListener ");
				loadCustomerVersionNo(customerRelation.getValue());
				customerRelationValueChange(event.getProperty().getValue());
				loadForecastLevels(innerCustLevels, customerInnerLevelContainer, level,
						customerHierarchyDto.getHierarchyId(),
						Integer.valueOf(customerRelationVersionComboBox.getValue().toString()));
			}
		});
	}

	private void configureProductSelection() {
		availableProduct.setFilterBarVisible(true);
		availableProduct.setStyleName(Constants.FILTER_TABLE);
		selectedProduct.setSortEnabled(false);
		productHierarchy.setWidth(StringConstantsUtil.TWO_SEVENTEEN_PX);
		availableProduct.setContainerDataSource(availableProductContainer);
		selectedProduct.setContainerDataSource(selectedProductContainer);
		availableProduct.setImmediate(true);
		selectedProduct.setImmediate(true);
		availableProduct.setVisibleColumns(new Object[] { StringConstantsUtil.DISPLAY_VALUE });
		availableProduct.setColumnHeaders(new String[] { StringConstantsUtil.LEVEL });
		selectedProduct.setVisibleColumns(new Object[] { StringConstantsUtil.DISPLAY_VALUE });
		selectedProduct.setColumnHeaders(new String[] { "Product Hierarchy Group Builder" });
		availableProduct.setSelectable(true);
		selectedProduct.setSelectable(true);

		productHierarchy.addClickListener(new CustomTextField.ClickListener() {
			@Override
			public void click(CustomTextField.ClickEvent event) {
				try {
					productHierarchyLookUp();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
					java.util.logging.Logger.getLogger(ForecastDataSelection.class.getName()).log(Level.SEVERE, null,
							ex);
				}
			}
		});

		productLevel.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				LOGGER.debug("product Level - ValueChangeListener ");
				if (!selectedProductContainer.getItemIds().isEmpty() && productLevelListenerFlag) {
					final String productLevelValue = getSelectedProductLevel();
					new AbstractNotificationUtils() {
                        @Override
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

		productlevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {

				try {
					String selectedLevel = String.valueOf(event.getProperty().getValue());
					LOGGER.debug("product inner Level - ValueChangeListener selectedLevel " + selectedLevel);
					productLevelDdlbValueChange(selectedLevel, false);
				} catch (Exception e) {
					LOGGER.error(e);
				}
			}
		});

		productRelation.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				loadProductVersionNo(productRelation.getValue());
				productRelationValueChange(event.getProperty().getValue());
				loadForecastLevels(innerProdLevels, productInnerLevelContainer, productlevelDdlb,
						productHierarchyDto.getHierarchyId(),
						Integer.parseInt(productRelationVersionComboBox.getValue().toString()));
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

	protected Object loadComboBoxBasedOnRelationshipVersion(ComboBox relationShipVersionComboBox,
			List<Object[]> resultList) {
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

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
	}
}
