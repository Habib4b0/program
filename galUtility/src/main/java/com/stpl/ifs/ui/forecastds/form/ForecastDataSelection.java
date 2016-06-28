/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui.forecastds.form;

import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.converters.TextFieldConverter;
import com.stpl.ifs.ui.util.UIUtil;
import com.stpl.ifs.ui.util.UIUtil.LengthConstants;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author sooriya.lakshmanan
 */
public abstract class ForecastDataSelection extends CustomComponent implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ForecastDataSelection.class);
    /**
     * The mode.
     */
//    @UiField("modeOption")
    protected OptionGroup modeOption = new OptionGroup();

    /**
     * The projection name.
     */
//    @UiField("projectionNameText")
    protected TextField projectionName = new TextField();

    /**
     * The description.
     */
    protected TextField description = new TextField();

    /**
     * The private view.
     */
//    @UiField("privateViewText")
    protected CustomTextField privateView = new CustomTextField();

    /**
     * The public view.
     */
//    @UiField("publicViewText")
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
    @UiField("company")
    protected ComboBox company;

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

//    /**
//     * The result table.
//     */
//    @UiField("resultTable")
//    protected ExtFilterTable resultTable;

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
    @UiField("productSelectionGrid")
    protected GridLayout productSelectionGrid;
    @UiField("productSelectionGrid2")
    protected GridLayout productSelectionGrid2;
   @UiField("ProdSelectionHLayout1")
    protected HorizontalLayout ProdSelectionHLayout1; 
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
    protected BeanItemContainer<Leveldto> availableCustomerContainer = new BeanItemContainer<Leveldto>(Leveldto.class);

    /**
     * Bean container for available Product.
     */
    protected BeanItemContainer<Leveldto> availableProductContainer = new BeanItemContainer<Leveldto>(Leveldto.class);

    protected final BeanItemContainer<Leveldto> productFilterContainer = new BeanItemContainer<Leveldto>(Leveldto.class);

    /**
     * Bean container for selected customers.
     */
    protected ExtTreeContainer<Leveldto> selectedCustomerContainer = new ExtTreeContainer<Leveldto>(Leveldto.class);

    /**
     * Bean container for selected Product.
     */
    protected ExtTreeContainer<Leveldto> selectedProductContainer = new ExtTreeContainer<Leveldto>(Leveldto.class);

    /**
     * Bean container for result table.
     */
    protected final BeanItemContainer<DataSelectionDTO> resultsContainer = new BeanItemContainer<DataSelectionDTO>(DataSelectionDTO.class);

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
     * NativeSelect for productInnerLevel.
     */
    private NativeSelect productInnerLevel = addDefaultNativeSelect();
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
    protected List<String> relationshipBuilderSids = new ArrayList<String>();
    /**
     * Product hierarchy DTO.
     */
    protected HierarchyLookupDTO productHierarchyDto;

    /**
     * The inner cust levels.
     */
    protected List<Leveldto> innerCustLevels = new ArrayList<Leveldto>();
    protected List<String> groupFilteredCompanies = null;
    protected List<String> groupFilteredItems = null;

    /**
     * The inner cust levels.
     */
    protected List<Leveldto> innerProdLevels = new ArrayList<Leveldto>();

    private List<GroupDTO> customerGroupList = new ArrayList<GroupDTO>();
    private List<GroupDTO> productGroupList = new ArrayList<GroupDTO>();
    protected GroupDTO selectedProductGroupDTO;
    protected GroupDTO selectedCustomerGroupDTO;
    protected TextField projectionId = new TextField();
    final ErrorLabel errorMsg = new ErrorLabel();
    private TextField projectionVersion = new TextField();

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
    private CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem<DataSelectionDTO>(dataSelectionDTO));

    /**
     * Container for customerForecastLevel.
     */
    protected final IndexedContainer customerForecastLevelContainer = new IndexedContainer();
    private final IndexedContainer defaultCustomerForecastLevelContainer = new IndexedContainer();
    protected String screenName = StringUtils.EMPTY;
    boolean landingScreenFlag;
   protected ComboBox deductionLevel=new ComboBox();
   protected ComboBox deductionValue=new ComboBox();
   
    public ForecastDataSelection(CustomFieldGroup dataSelectionBinder, String screenName, boolean landingScreenFlag) throws Exception {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/ui/forecast/dataSelectionIndex.xml"), this));
        this.dataSelectionBinder = dataSelectionBinder;
        this.screenName = screenName;
        this.landingScreenFlag = landingScreenFlag;
        addComponent();
//        if (landingScreenFlag) {
        dataSelectionBinder = getBinder();
//        }
        configureFields(screenName);
       
    }

    private CustomFieldGroup getBinder() {
        dataSelectionBinder.bindMemberFields(this);
        dataSelectionBinder.setItemDataSource(new BeanItem<DataSelectionDTO>(dataSelectionDTO));
        dataSelectionBinder.setBuffered(true);
        dataSelectionBinder.setErrorDisplay(errorMsg);
        return dataSelectionBinder;
    }

    private void configureFields(String screenName) throws Exception {
        LOGGER.info("configureFields---");
      
        try{
        resizeDdlb();
        if ("Returns".equals(screenName)) {
            customerSelection.setVisible(false);
            configureProductSelection();
            configureProductDdlb();
        } else {
            try{
            configureCustomerSelection();
            configureProductSelection();
            configureCustomerDdlb();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
            
        deleteViewBtn.setEnabled(false);
        resultResetBtn.setEnabled(false);
        editBtn.setEnabled(false);
        viewBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        publicView.setWidth("217px");
        privateView.setWidth("217px");
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
                        //  DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant());
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
                        //  DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_SEARCH.getConstant());
                    }
                    resetFlag = false;
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage() + " in modeOption ValueChangeListener ");
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
        }catch(Exception e){
            e.printStackTrace();
        }
        LOGGER.info("configureFields ENDS---");
    }

    private void resizeDdlb() {
        String width = "250px";
        customerLevel.setWidth(width);
        productLevel.setWidth(width);
        level.setWidth(width);
        productlevelDdlb.setWidth(width);
        customerRelation.setWidth(width);
        productRelation.setWidth(width);
        company.setWidth(width);
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
        LOGGER.info("configureDdlb called");
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
        resetCompany();
        resetProductRelation();
    }

    private void resetCompany() {
        company.removeAllItems();
        company.setImmediate(true);
        company.setNullSelectionAllowed(true);
        company.setInputPrompt(UIUtil.SELECT_ONE);
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
     * @param event the event
     */
    @UiHandler("generateBtn")
    public void generateBtn(Button.ClickEvent event) throws Exception {
        generateButtonLogic();
    }

    private List<Leveldto> getCustomerHierarchyEndLevels() throws Exception {
        List<Leveldto> customerHierarchyEndLevels = new ArrayList<Leveldto>();
        for (Object item : selectedCustomerContainer.getItemIds()) {
            if (!selectedCustomerContainer.hasChildren(item)) {
                customerHierarchyEndLevels.add(getBeanFromId(item));
            }
        }
        return customerHierarchyEndLevels;
    }

    private List<Leveldto> getProductHierarchyEndLevels() {
        List<Leveldto> productHierarchyEndLevels = new ArrayList<Leveldto>();
        for (Object item : selectedProductContainer.getItemIds()) {
            if (!selectedProductContainer.hasChildren(item)) {
                productHierarchyEndLevels.add(getBeanFromId(item));
            }
        }
        return productHierarchyEndLevels;
    }

    private void setRelationshipBuilderSids(String rbSid) {
        // order is preserved
        relationshipBuilderSids.add(rbSid);
    }

    private String getProductHierarchyEndLevelsHierNo() {

        StringBuilder returnString = new StringBuilder(StringUtils.EMPTY);

        List<String> productHierarchyEndLevelsHierNo = new ArrayList<String>();
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

    protected void resetOne() throws Exception {
        availableCustomerContainer = new BeanItemContainer<Leveldto>(Leveldto.class);
        availableCustomer.setContainerDataSource(availableCustomerContainer);
        availableProductContainer = new BeanItemContainer<Leveldto>(Leveldto.class);
        availableProduct.setContainerDataSource(availableProductContainer);
        selectedCustomerContainer = new ExtTreeContainer<Leveldto>(Leveldto.class);
        selectedCustomer.setContainerDataSource(selectedCustomerContainer);
        selectedProductContainer = new ExtTreeContainer<Leveldto>(Leveldto.class);
        selectedProduct.setContainerDataSource(selectedProductContainer);
        availableProduct.setVisibleColumns("displayValue");
        availableProduct.setColumnHeaders("Level");
        availableCustomer.setVisibleColumns("displayValue");
        availableCustomer.setColumnHeaders("Level");
        selectedCustomer.setVisibleColumns("displayValue");
        selectedCustomer.setColumnHeaders("Customer Hierarchy Group Builder");
        selectedProduct.setVisibleColumns("displayValue");
        selectedProduct.setColumnHeaders("Product Hierarchy Group Builder");
        availableCustomer.setFilterBarVisible(true);
        availableCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
        availableCustomer.setStyleName("filtertable");
        availableProduct.setFilterBarVisible(true);
        availableProduct.setFilterDecorator(new ExtDemoFilterDecorator());
        availableProduct.setStyleName("filtertable");
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
        if("Returns".equals(screenName)){
        configureProductDdlb();    
        } else {
        configureCustomerDdlb();
        configureProductDdlb();    
        }
//         if(!"AccrualRateProjection".equals(screenName)){
        configureTimeDdlb(fromPeriod, toPeriod, null, null, UIUtil.MODE_ADD, screenName);
//         }
        dataSelectionDTO.clear();
//        dataSelectionBinder.commit();
    }

    /**
     * Reset button.
     *
     * @param event the event
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
             * @param buttonId The buttonId of the pressed button.
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
                    LOGGER.error(ex.getMessage() + " - in resetBtn");
                }
            }
        }.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");

    }

    /**
     * Search button logic.
     *
     * @param event the event
     */
    @UiHandler("searchBtn")
    public void searchBtn(Button.ClickEvent event) {
        searchButtonLogic();
    }

    /**
     * Edit button logic. Used to edit a projection.
     *
     * @param event the event
     */
    @UiHandler("editBtn")
    public void editBtn(Button.ClickEvent event) {
        editButtonLogic();
    }

    /**
     * View btn.
     *
     * @param event the event
     */
    @UiHandler("viewBtn")
    public void viewBtn(Button.ClickEvent event) {
        viewButtonLogic();
    }

    /**
     * Delete btn.
     *
     * @param event the event
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
     * @param event the event
     */
    @UiHandler("productGP")
    public void productGroup(CustomTextField.ClickEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        loadProductGroup();
    }

    /**
     * Customer group.
     *
     * @param event the event
     */
    @UiHandler("customerGP")
    public void customerGroup(CustomTextField.ClickEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        loadCustomerGroup();
    }

    /**
     * Save view btn.
     *
     * @param event the event
     */
    @UiHandler("saveViewBtn")
    public void saveViewBtn(Button.ClickEvent event) throws Exception {
        saveViewButtonlogic();
    }

    /**
     * Move left btn.
     *
     * @param event the event
     */
    @UiHandler("moveLeftBtn")
    public void moveLeftBtn(Button.ClickEvent event) throws Exception {
        moveLeftButtonLogic();
    }

    @UiHandler("allBtn")
    public void allBtn(Button.ClickEvent event) throws Exception {
        moveAllButtonLogic();
    }

    @UiHandler("allProductBtn")
    public void allProductBtn(Button.ClickEvent event) throws Exception {
        moveAllProductButtonLogic();
    }

    /**
     * Move left btn.
     *
     * @param event the event
     */
    @UiHandler("moveRightBtn")
    public void moveRightBtn(Button.ClickEvent event) {
        moveRigthButtonLogic();
    }

    /**
     * Move left product.
     *
     * @param event the event
     */
    @UiHandler("moveRightProduct")
    public void moveRightProduct(Button.ClickEvent event) {
        moveRigthProductButtonLogic();
    }

    /**
     * Move right product.
     *
     * @param event the event
     * @throws java.lang.Exception
     */
    @UiHandler("moveLeftProduct")
    public void moveLeftProduct(Button.ClickEvent event) throws Exception {
        moveLeftProductButtonLogic();
    }

    /**
     * Result reset btn.
     *
     * @param event the event
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
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                resetTwo();
            }
        }.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");
    }

    /**
     * Delete view btn.
     *
     * @param event the event
     */
    @UiHandler("deleteViewBtn")
    public void deleteViewBtn(Button.ClickEvent event) {
        deleteViewButtonLogic();
    }

    @UiHandler("company")
    public void company(Property.ValueChangeEvent event) throws Exception {
        companyValueChangeLogic(event.getProperty().getValue());
    }

    /**
     * Gets the bean from id.
     *
     * @param obj the id
     * @return the bean from id
     */
    public static Leveldto getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof Leveldto) {
            targetItem = new BeanItem<Leveldto>((Leveldto) obj);
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

    protected abstract void customerHierarchyLookUp()throws InstantiationException, IllegalAccessException, ClassNotFoundException;

    protected abstract void productHierarchyLookUp()throws InstantiationException, IllegalAccessException, ClassNotFoundException;

    protected abstract void modeOptionChange(boolean isAddMode);

    protected abstract void customerLevelValueChange(Property.ValueChangeEvent event, boolean flag);

    protected abstract void levelValueChangeListener(Object value);

    protected abstract void productLevelValueChange(Object value, boolean flag);

    protected abstract void customerRelationValueChange(Object value);

    protected abstract void productRelationValueChange(Object value);

    protected abstract void generateButtonLogic();

    protected abstract void searchButtonLogic();

    protected abstract void editButtonLogic();

    protected abstract void viewButtonLogic();

    protected abstract void deleteButtonLogic();

    protected abstract void configureTimeDdlb(ComboBox fromPeriod, ComboBox toPeriod, Object object, Object object0, String constant, String screenName);

    protected abstract void loadPublicView();

    protected abstract void loadPrivateView();

    protected abstract void loadProductGroup()throws InstantiationException, IllegalAccessException, ClassNotFoundException;

    protected abstract void loadCustomerGroup()throws InstantiationException, IllegalAccessException, ClassNotFoundException;

    protected abstract void saveViewButtonlogic();

    protected abstract void moveLeftButtonLogic();

    protected abstract void moveAllButtonLogic();

    protected abstract void moveAllProductButtonLogic();

    protected abstract void moveRigthButtonLogic();

    protected abstract void moveRigthProductButtonLogic();

    protected abstract void moveLeftProductButtonLogic();

    protected abstract void deleteViewButtonLogic();

    protected abstract void companyValueChangeLogic(Object value) throws Exception;

    protected abstract void resetButtonLogic();

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
     
        if (screenName.equals("Returns")) {
            gridLayoutTimeperiod.setVisible(false);
            GridLayout layoutT1 = new GridLayout(2, 2);
            layoutT1.setSpacing(Boolean.TRUE);
            layoutT1.addComponent(new Label("From: ") {
                {
                   // setContentMode(ContentMode.HTML);
                }
            });
            layoutT1.addComponent(fromPeriod);
            layoutT1.addComponent(new Label("To: ") {
                {
                    //setContentMode(ContentMode.HTML);
                }
            });
            layoutT1.addComponent(toPeriod);
            verticalLayoutTimeperiod.addComponent(layoutT1);
            GridLayout layoutG1 = new GridLayout(6, 1);
            layoutG1.addComponent(new Label("Mode: ") {
                {
                    setWidth("50px");
                    setContentMode(ContentMode.HTML);
                }
            });
            modeOption.setStyleName("horizontal");
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

            //modeOption.setWidth("150px");
            layoutG1.addComponent(new Label("Projection Name: ") {
                {
                    setContentMode(ContentMode.HTML);
                }
            });
            layoutG1.addComponent(projectionName);
            GridLayout layoutG2 = new GridLayout(4, 2);
            layoutG2.addComponent(new Label("Private Views: ") {
                {
                    setWidth("100px");
                    setContentMode(ContentMode.HTML);
                }
            });
            privateView.setStyleName("searchText");
            privateView.setWidth("100px");
            layoutG2.addComponent(privateView);
            layoutG2.addComponent(new Label("Description: ") {
                {
                    setContentMode(ContentMode.HTML);
                }
            });
            layoutG2.addComponent(description);
            layoutG2.addComponent(new Label("Public Views: ") {
                {
                    setContentMode(ContentMode.HTML);
                    setStyleName("labelresultalign");
                }
            });

            publicView.setStyleName("searchText");
            layoutG2.addComponent(publicView);
            verticalLayout.addComponent(layoutG1);
            verticalLayout.addComponent(layoutG2);

                    ProdSelectionVLayout.removeComponent(ProdSelectionHLayout1);
                 productSelectionGrid.setVisible(false);
            productSelectionGrid2.setVisible(false);
            GridLayout layoutG3 = new GridLayout(6, 2);
            layoutG3.addComponent(new Label("Company: "){
                {
                    setWidth("50px");
                }
            });
            layoutG3.addComponent(company);
            layoutG3.addComponent(new Label("Hierarchy: "){
                {
                    setWidth("50px");
                }
            });
            layoutG3.addComponent(productHierarchy);
            layoutG3.addComponent(new Label("Relationship: "){
                {
                    setWidth("100px");
                }
            });
            layoutG3.addComponent(productRelation);
            layoutG3.addComponent(new Label("Forecast Level: "){
                {
                    setWidth("104px");
                }
            });
            layoutG3.addComponent(productLevel);
            layoutG3.addComponent(new Label("Product Group: "){
                {
                    setWidth("108px");
                }
            });
            layoutG3.addComponent(productGroup);
            productGroup.setWidth("217px");
            ProdSelectionHLayout.addComponent(layoutG3);
        }  else if("AccrualRateProjection".equals(screenName)){
//            "Accruals Projection".equals(screenName)
          GridLayout layoutG1 = new GridLayout(2, 1);
            layoutG1.addComponent(new Label("Mode: ") {
                {
                    setWidth("50px");
                    setContentMode(ContentMode.HTML);
                }
            });
            modeOption.setStyleName("horizontal");
            layoutG1.addComponent(modeOption);
            GridLayout layoutG2 = new GridLayout(6, 2);
            layoutG2.addComponent(new Label("Private Views: ") {
                {
                    setWidth("100px");
                    setContentMode(ContentMode.HTML);
                }
            });
            privateView.setStyleName("searchText");
            layoutG2.addComponent(privateView);
            layoutG2.addComponent(new Label("Projection Name: ") {
                {
                    setContentMode(ContentMode.HTML);
                }
            });
            layoutG2.addComponent(projectionName);
            layoutG2.addComponent(new Label("Deduction Level:"){
                {
                    setWidth("100px");
                    setContentMode(ContentMode.HTML);
                    setStyleName("labelresultalign");
                }
            });
            layoutG2.addComponent(deductionLevel);
            layoutG2.addComponent(new Label("Public Views: ") {
                {
                    setWidth("100px");
                    setContentMode(ContentMode.HTML);
                    setStyleName("labelresultalign");
                }
            });
            
            publicView.setStyleName("searchText");
            layoutG2.addComponent(publicView);
            layoutG2.addComponent(new Label("Description: ") {
                {
                    setContentMode(ContentMode.HTML);
                    setStyleName("labelresultalign");
                }
            });
            layoutG2.addComponent(description);
             layoutG2.addComponent(new Label("Deduction Value:"){
                {
                    setWidth("100px");
                    setContentMode(ContentMode.HTML);
                    setStyleName("labelresultalign");
                }
            });
            layoutG2.addComponent(deductionValue);
            verticalLayout.addComponent(layoutG1);
            verticalLayout.addComponent(layoutG2);
           
        }else {
            GridLayout layoutG1 = new GridLayout(2, 1);
            layoutG1.addComponent(new Label("Mode: ") {
                {
                    setWidth("50px");
                    setContentMode(ContentMode.HTML);
                }
            });
            modeOption.setStyleName("horizontal");
            layoutG1.addComponent(modeOption);
            GridLayout layoutG2 = new GridLayout(4, 2);
            layoutG2.addComponent(new Label("Private Views: ") {
                {
                    setWidth("100px");
                    setContentMode(ContentMode.HTML);
                }
            });
            privateView.setStyleName("searchText");
            layoutG2.addComponent(privateView);
            layoutG2.addComponent(new Label("Projection Name: ") {
                {
                    setContentMode(ContentMode.HTML);
                }
            });
            layoutG2.addComponent(projectionName);
            layoutG2.addComponent(new Label("Public Views: ") {
                {
                    setWidth("100px");
                    setContentMode(ContentMode.HTML);
                    setStyleName("labelresultalign");
                }
            });

            publicView.setStyleName("searchText");
            layoutG2.addComponent(publicView);
            layoutG2.addComponent(new Label("Description: ") {
                {
                    setContentMode(ContentMode.HTML);
                    setStyleName("labelresultalign");
                }
            });
            layoutG2.addComponent(description);
            verticalLayout.addComponent(layoutG1);
            verticalLayout.addComponent(layoutG2);
        }
    }

    private void configureCustomerSelection() {
//        availableCustomer.setFilterBarVisible(true);
//        availableCustomer.setStyleName("filtertable");
        selectedCustomer.setSortEnabled(false);
        customerHierarchy.setWidth("217px");
        customerLevel.setImmediate(true);
        level.setImmediate(true);
        availableCustomer.setContainerDataSource(availableCustomerContainer);
        selectedCustomer.setContainerDataSource(selectedCustomerContainer);
        availableCustomer.setImmediate(true);
        selectedCustomer.setImmediate(true);
        availableCustomer.setVisibleColumns(new Object[]{"displayValue"});
        availableCustomer.setColumnHeaders(new String[]{"Level"});
        selectedCustomer.setVisibleColumns(new Object[]{"displayValue"});
        selectedCustomer.setColumnHeaders(new String[]{"Customer Hierarchy Group Builder"});
        availableCustomer.setSelectable(true);
        selectedCustomer.setSelectable(true);
        customerHierarchy.addStyleName("searchIcon");
        customerHierarchy.addClickListener(new CustomTextField.ClickListener() {

            @Override
            public void click(CustomTextField.ClickEvent event) {
                try {
                    customerHierarchyLookUp();
                } catch (InstantiationException ex) {
                    java.util.logging.Logger.getLogger(ForecastDataSelection.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(ForecastDataSelection.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(ForecastDataSelection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        if (landingScreenFlag) {
            customerLevel.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(final Property.ValueChangeEvent event) {
                    LOGGER.info("customerLevel - ValueChangeListener");
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
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */

                            public void yesMethod() {
                                customerLevelValueChange(event, true);
                            }
                        }.getConfirmationMessage("Confirm Change", "You have selected a new Forecast Level. Are you sure you want to proceed? You will lose the current Customer/Product hierarchies if you continue.");
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
                levelValueChangeListener(event.getProperty().getValue());
            }
        });

        customerRelation.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                LOGGER.info("customerRelation - ValueChangeListener ");
                customerRelationValueChange(event.getProperty().getValue());
            }
        });
    }

    private void configureProductSelection() {
//        availableProduct.setFilterBarVisible(true);
//        availableProduct.setStyleName("filtertable");
        selectedProduct.setSortEnabled(false);
        productHierarchy.setWidth("217px");
        availableProduct.setContainerDataSource(availableProductContainer);
        selectedProduct.setContainerDataSource(selectedProductContainer);
        availableProduct.setImmediate(true);
        selectedProduct.setImmediate(true);
        availableProduct.setVisibleColumns(new Object[]{"displayValue"});
        availableProduct.setColumnHeaders(new String[]{"Level"});
        selectedProduct.setVisibleColumns(new Object[]{"displayValue"});
        selectedProduct.setColumnHeaders(new String[]{"Product Hierarchy Group Builder"});
        availableProduct.setSelectable(true);
        selectedProduct.setSelectable(true);
        availableProduct.setFilterBarVisible(true);
        availableProduct.setFilterDecorator(new ExtDemoFilterDecorator());
        availableProduct.setStyleName("filtertable");

        productHierarchy.addClickListener(new CustomTextField.ClickListener() {

            @Override
            public void click(CustomTextField.ClickEvent event) {
                try {
                    productHierarchyLookUp();
                } catch (InstantiationException ex) {
                    java.util.logging.Logger.getLogger(ForecastDataSelection.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(ForecastDataSelection.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(ForecastDataSelection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        if (landingScreenFlag) {

            productLevel.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(final Property.ValueChangeEvent event) {
                    LOGGER.info("product Level - ValueChangeListener ");
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
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            public void yesMethod() {
                                productLevelValueChange(event.getProperty().getValue(), true);
                            }
                        }.getConfirmationMessage("Confirm Change", "You have selected a new Forecast Level. Are you sure you want to proceed? You will lose the current Customer/Product hierarchies if you continue.");
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
                LOGGER.info("product inner Level - ValueChangeListener selectedLevel " + selectedLevel);
                // loadFilteredProductSelection(selectedLevel, false);
                productLevelDdlbValueChange(selectedLevel, false);
            }
        });

        productRelation.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                productRelationValueChange(event.getProperty().getValue());
            }
        });
    }
    }
