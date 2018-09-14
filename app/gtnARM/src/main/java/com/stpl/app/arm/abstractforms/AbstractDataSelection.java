
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.abstractforms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.dataselection.dto.DeductionLevelDTO;
import com.stpl.app.arm.dataselection.dto.HierarchyLookupDTO;
import com.stpl.app.arm.dataselection.dto.LevelDTO;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.stpl.app.arm.dataselection.ui.lookups.HierarchyLookup;
import com.stpl.app.arm.dataselection.ui.lookups.PrivatePublicLookUp;
import com.stpl.app.arm.dataselection.ui.lookups.ViewSearchLookUp;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.DataSelectionUtils;
import static com.stpl.app.arm.utils.DataSelectionUtils.getBeanFromId;
import com.stpl.gtn.gtn2o.ws.arm.dataselection.bean.GtnARMHierarchyInputBean;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.TreeTable;
import com.vaadin.v7.ui.VerticalLayout;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.asi.ui.extfilteringtable.paged.logic.HierarchyString;

/**
 * Allows the user to select the Data Selection work flow tab. All values in
 * this 'work flow version' of the Data Selection screen will be read only. The
 * user will not be able to edit, change, or alter any of the values that were
 * previously selected for this 'Fixed Dollar Adjustment - Base Rate' process.
 *
 */
public abstract class AbstractDataSelection extends CustomComponent {

    /**
     * Logger for the class AbstractDataSelection, Which logs the Current class
     * error, info, warning and etc
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDataSelection.class);
    /**
     * Adjustment Options UI fields in Data selection Screen
     */
    /**
     * Select a saved Private view using the lookup icon. When selected, the
     * View Search lookup will be launched. This is a simple lookup that will
     * allow the user to search for any previously created and saved public
     * views.
     */
    @UiField("privateViewText")
    protected CustomTextField privateView;

    /**
     * Select a saved Public view using the lookup icon. When selected, the View
     * Search lookup will be launched. This is a simple lookup that will allow
     * the user to search for any previously created and saved public views.
     */
    @UiField("publicViewText")
    protected CustomTextField publicView;
    /**
     * the drop down is used to select the Adjustments
     */
    @UiField("adjustmentType")
    protected ComboBox adjustmentType;

    /**
     * The description text field is used to give description for each
     * adjustment types
     */
    @UiField("descriptionText")
    protected CustomTextField description;
    /**
     * The company drop down loads the Company_type as General Ledger(GL_comp)
     * from Database
     */
    @UiField("companyDDLB")
    protected ComboBox company;
    /**
     * The businessUnit drop down loads the Company_type as Business unit from
     * Database
     */
    @UiField("businessUnit")
    protected ComboBox businessUnit;

    /**
     * The From-Period will be loaded with Current-12 to Current+36
     */
    @UiField("fromDate")
    protected ComboBox fromPeriod;

    /**
     * The To-Period will be loaded with Current-12 to Current+36
     */
    @UiField("toDate")
    protected ComboBox toPeriod;

    /**
     * Deduction Selection UI fields in Data selection Screen
     */
    /**
     * The Deduction Level Drop down have : Deduction Category, Deduction Type,
     * Deduction Program, Deduction Category 2, Deduction Category 3, Deduction
     * Category 4, Deduction Category 5, Deduction Category 6, Deduction.
     */
    @UiField("deductionLevel")
    protected ComboBox deductionLevel;

    /**
     * Will update and display values based on the Level selected. First row
     * will be a filter row
     *
     */
    @UiField("availableDeduction")
    protected ExtFilterTable availableDeduction;

    /**
     * Will display values moved over from the Available Deductions list view.
     *
     * The entire tree structure displayed: 1) Deduction Category, 2) Deduction
     * Type,[NumericConstants.THREE] Deduction Program Deduction ('Rebate
     * Schedule ID ' Rebate Schedule Name').
     */
    @UiField("selectedDeduction")
    protected TreeTable selectedDeduction;

    /**
     * Move a selected deduction value from Available to Selected
     */
    @UiField("deduction_moveLeftBtn")
    protected Button deductionMoveLeftBtn;

    /**
     * Move a selected deduction value from Selected to Available.
     */
    @UiField("deduction_moveRightBtn")
    protected Button deductionMoveRightBtn;

    /**
     * Move all deduction values between Selected/Available or
     * Available/Selected
     */
    @UiField("deduction_moveAllBtn")
    protected Button deductionMoveAllBtn;

    /**
     * Customer Selection UI fields in Data selection Screen
     */
    /**
     * Allows the user to search for and select a previously created and saved
     * Master Customer Hierarchy which will serve as the organizational guide
     * for each Segment Group, Segment, Contract Holder, Market Type, and
     * Trading Partner that the user brings into the adjustment.
     */
    @UiField("customerHierarchy")
    protected CustomTextField customerHierarchy;
    /**
     * Allows the user to select a Relationship for the selected Hierarchy.
     * There can be multiple relationship for the same Hierarchy. *
     */
    @UiField("customerRelation")
    protected ComboBox customerRelation;
    /**
     * Populates with a list of all the available 'levels' based on the Selected
     * Customer Hierarchy, The user will use this to populate the 'Available
     * Customer' list view.
     */
    @UiField("customerLevel")
    protected ComboBox customerLevel;

    /**
     * Displays the results of the selected 'Level' based on the Master Customer
     * Hierarchy. The user can search for the levels they want to bring into the
     * forecast with this list view.
     */
    @UiField("availableCustomer")
    protected ExtFilterTable availableCustomer;

    /**
     * This list view will display all the levels that the user has moved over
     * from the available Customer list view. It will display each level with
     * indents, so that the user will know how each level is related to each
     * other.
     */
    @UiField("selectedCustomer")
    protected TreeTable selectedCustomer;

    /**
     * Brings the selected level from the available, to the selected list view.
     * This is how the user can manually create their own adjustment hierarchy.
     */
    @UiField("customer_moveLeftBtn")
    protected Button customerMoveLeftBtn;

    /**
     * Removes the selected level from the selected, to the available list view.
     */
    @UiField("customer_moveRightBtn")
    protected Button customerMoveRightBtn;

    /**
     * Brings all of the displayed level values in the available list view, into
     * the selected list view, or vice versa.
     */
    @UiField("customer_MoveAllBtn")
    protected Button customerMoveAllBtn;

    /**
     * Product Selection UI fields in Data selection Screen
     */
    /**
     * Allows the user to search for and select a previously created and saved
     * Master Product Hierarchy which will serve as the organizational guide for
     * each Company, Therapeutic Class, Brand and NDC 11 that will be brought
     * into the adjustment.
     */
    @UiField("productHierarchy")
    protected CustomTextField productHierarchy;
    /**
     * Allows the user to select a Relationship for the selected Hierarchy.
     * There can be multiple relationship for the same Hierarchy.
     */
    @UiField("productRelation")
    protected ComboBox productRelation;
    /**
     * Populates with a list of all the available 'levels' based on the Selected
     * Product Hierarchy, The user will use this to populate the 'Available
     * Products' list view.
     */
    @UiField("productLevel")
    protected ComboBox productLevel;

    /**
     * Displays the results of the selected 'Level' based on the Master Product
     * Hierarchy. The user can search for the level they want to bring into the
     * adjustment with this list view.
     */
    @UiField("availableProduct")
    protected ExtFilterTable availableProduct;

    /**
     * This list view will display all the levels that the user has moved over
     * from the available Product list view. It will display each level with
     * indents, so that the user will know how each level is related to each
     * other.
     */
    @UiField("selectedProduct")
    protected TreeTable selectedProduct;

    /**
     * Brings the selected level from the available, to the selected list view.
     * This is how the user can manually create their own forecasting hierarchy.
     */
    @UiField("product_moveLeftProduct")
    protected Button productMoveLeftProduct;

    /**
     * Removes the selected level from the selected, to the available list view.
     */
    @UiField("product_moveRightProduct")
    protected Button productMoveRightProduct;

    /**
     * Brings all of the displayed level values in the available list view, into
     * the selected list view.
     */
    @UiField("product_moveAllBtn")
    protected Button productMoveAllBtn;

    /**
     * Launches the adjustment workflow in a separate window.
     */
    @UiField("generateBtn")
    protected Button generateBtn;

    /**
     * Resets the screen to its default state.
     */
    @UiField("resetBtn")
    protected Button resetBtn;

    /**
     * Saves the current view of the Data Selection screen. This will allow
     * users to create 'common' adjustment templates that can be made private,
     * or public. They will serve to expedite the Data Selection process.
     *
     */
    @UiField("saveViewBtn")
    protected Button saveViewBtn;

    /**
     * Deletes the current view that has been loaded via the 'Public' or
     * 'Private' View lookups. Note: only the creator of a view can delete it.
     * This button is only enabled when a user has used the 'Public/Private'
     * View lookup and loaded a view into the Data Selection Screen.
     *
     */
    @UiField("deleteViewBtn")
    protected Button deleteViewBtn;

    @UiField("panelLayout")
    protected VerticalLayout panelLayout;

    @UiField("buttonLay")
    protected HorizontalLayout buttonLay;

    @UiField("dsLayout")
    protected VerticalLayout dsLayout;

    @UiField("panel3")
    protected Panel panel3;

    @UiField("panel1")
    protected Panel panel1;

    public static final int HIERSID = 0;

    protected List<LevelDTO> innerCustLevels = new ArrayList<>();
    protected List<LevelDTO> innerProdLevels = new ArrayList<>();
    protected List<Integer> customerBeanList = new ArrayList<>();
    protected List<Integer> productBeanList = new ArrayList<>();
    protected Map<String, String> customerDescriptionMap = null;
    protected Map<String, String> productDescriptionMap = null;
    protected HierarchyLookup customerHierarchyLookup;
    protected HierarchyLookup productHierarchyLookup;
    protected PrivatePublicLookUp privateLookUp;
    protected PrivatePublicLookUp publicLookUp;
    protected boolean custLevelListenerFlag = true;
    protected boolean prodLevelListenerFlag = true;
    protected boolean deductionViewFlag = false;
    /**
     * Returns Helper Table SID and Description in an array for deduction level
     * Number
     */
    protected Map<Integer, String[]> deductionHelperLevelMap = new HashMap<>();
    /**
     * Returns Deduction Level Name for deduction level Number
     */
    protected Map<String, Integer> deductionLevelMapForInteger = new HashMap<>();
    protected Map<String, Set<Integer>> selectedLevelIds = new HashMap<>();
    protected BeanItemContainer<LevelDTO> availableCustomerContainer = new BeanItemContainer<>(LevelDTO.class);
    protected ExtTreeContainer<LevelDTO> selectedCustomerContainer = new ExtTreeContainer<>(LevelDTO.class);
    protected BeanItemContainer<DeductionLevelDTO> availableDeductionContainer = new BeanItemContainer<>(DeductionLevelDTO.class);
    protected ExtTreeContainer<DeductionLevelDTO> selectedDeductionContainer = new ExtTreeContainer<>(DeductionLevelDTO.class);
    protected ExtTreeContainer<LevelDTO> selectedProductContainer = new ExtTreeContainer<>(LevelDTO.class);
    protected BeanItemContainer<LevelDTO> availableProductContainer = new BeanItemContainer<>(LevelDTO.class);

    private static final String LEVEL_VALUE = "levelValue";
    private static final String DISPLAY_VALUE = "displayValue";

    protected Label summaryTypeLabel = new Label("Summary Type:");
    protected ComboBox summaryTypeDdlb = new ComboBox();

    protected Label viewLabel = new Label("View :");
    protected CustomTextField view = new CustomTextField();
    @UiField("companyLabel")
    protected Label companyLabel;
    @UiField("businessUnitLabel")
    protected Label businessUnitLabel;
    protected ViewSearchLookUp viewLookUp;
    @UiField("horizontalLayout")
    protected HorizontalLayout horizontalLayout;
    @UiField("selectionVerticalLayout")
    protected VerticalLayout selectionVerticalLayout;

    protected Map<Integer, Integer> custVersionMap = new HashMap<>();
    protected Map<Integer, Integer> prodVersionMap = new HashMap<>();

    /**
     * Initialization Of UI Fields.
     */
    public AbstractDataSelection() {
        init();
        configureFields();
        configureTables();
    }

    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/data_selection/data-selection.xml"), this));
        panelLayout.setSizeUndefined();
        panel3.setSizeUndefined();
    }

    private void configureFields() {
        setAdjustmentOptionsPanelForBalanceSummaryReport();
        CommonLogic.setComboBoxItemIDAndCaption(adjustmentType, "LoadAdjustmentType", Collections.emptyList());
        adjustmentType.focus();
        description.setImmediate(true);
        description.setValidationVisible(true);
        description.addValidator(new StringLengthValidator(" Description Should be less than 200 characters", 0, NumericConstants.TWO_HUNDRED, true));
        configurePeriodDropDown(fromPeriod);
        configurePeriodDropDown(toPeriod);
        buttonLay.setVisible(true);
        configureDropDowns(deductionLevel);
        CommonLogic.getComboBoxByListNameSorted(deductionLevel, "DEDUCTION_LEVELS", Boolean.FALSE, deductionHelperLevelMap);
        configureDropDowns(company);
        configureDropDowns(businessUnit);
        CommonLogic.loadCompanyAndBusinessUnit(company, "getCompanyQueryForDS");
        CommonLogic.loadCompanyAndBusinessUnit(businessUnit, "getBusinessQueryForDS");
        configureDropDowns(customerRelation);
        configureDropDowns(productRelation);
        configureDropDowns(customerLevel);
        configureDropDowns(productLevel);
        if (deductionLevelMapForInteger.isEmpty()) {
            CommonLogic.loadDeductionLevelMapForInteger(deductionLevelMapForInteger);
        }
        deleteViewBtn.setEnabled(false);
    }

    private void configureTables() {
        availableDeduction.setContainerDataSource(availableDeductionContainer);
        availableDeduction.setVisibleColumns(LEVEL_VALUE);
        availableDeduction.setColumnHeader(LEVEL_VALUE, "Available Deductions");
        configureTableProperties(availableDeduction);

        selectedDeduction.setContainerDataSource(selectedDeductionContainer);
        selectedDeduction.setVisibleColumns(LEVEL_VALUE);
        selectedDeduction.setColumnHeader(LEVEL_VALUE, "Selected Deductions");

        availableCustomer.setContainerDataSource(availableCustomerContainer);
        availableCustomer.setVisibleColumns(new Object[]{DISPLAY_VALUE});
        availableCustomer.setColumnHeaders(new String[]{"Available Customers"});
        configureTableProperties(availableCustomer);

        selectedCustomer.setContainerDataSource(selectedCustomerContainer);
        selectedCustomer.setVisibleColumns(new Object[]{DISPLAY_VALUE});
        selectedCustomer.setColumnHeaders(new String[]{"Selected Customers"});

        availableProduct.setContainerDataSource(availableProductContainer);
        availableProduct.setVisibleColumns(new Object[]{DISPLAY_VALUE});
        availableProduct.setColumnHeaders(new String[]{"Available Products"});
        configureTableProperties(availableProduct);

        selectedProduct.setContainerDataSource(selectedProductContainer);
        selectedProduct.setVisibleColumns(new Object[]{DISPLAY_VALUE});
        selectedProduct.setColumnHeaders(new String[]{"Selected Products"});
    }

    public void configureDropDowns(ComboBox box) {
        box.setImmediate(true);
        box.setNullSelectionAllowed(true);
        box.addItem(GlobalConstants.getSelectOne());
        box.setNullSelectionItemId(GlobalConstants.getSelectOne());
        box.select(null);
    }

    public void configurePeriodDropDown(ComboBox box) {
        box.setImmediate(true);
        box.setNullSelectionAllowed(false);
        box.addItem(0);
        box.setItemCaption(0, GlobalConstants.getSelectOne());
        box.select(0);
    }

    public void configureTableProperties(ExtFilterTable table) {
        table.setFilterBarVisible(true);
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setStyleName("filtertable");
        table.setSelectable(true);
    }

    @UiHandler("customerLevel")
    public void customerLevelValueChange(Property.ValueChangeEvent event) {
        if (custLevelListenerFlag) {
            loadAvailableCustomers();
        }
        productLevel.select(null);
    }

    @UiHandler("productLevel")
    public void productlevelValueChange(Property.ValueChangeEvent event) {
        if (prodLevelListenerFlag) {
            loadAvailableProducts();
        }
    }

    public void customerValueChange() {
        if (customerHierarchyLookup != null && customerHierarchyLookup.getHierarchyDto() != null && customerHierarchyLookup.getHierarchyDto().getHierarchyId() != 0) {
            int custHierSid = customerHierarchyLookup.getHierarchyDto().getHierarchyId();
            loadCustRelationAndLevel(custHierSid, customerHierarchyLookup.getCtfEvent());
            customerRelation.select(null);
            customerLevel.select(null);
        }
    }

    public void productValueChange(int glCompId) {
        if (productHierarchyLookup != null && productHierarchyLookup.getHierarchyDto() != null && productHierarchyLookup.getHierarchyDto().getHierarchyId() != 0) {

            int prodHierSid = productHierarchyLookup.getHierarchyDto().getHierarchyId();
            loadProdRelationAndLevel(prodHierSid, glCompId, productHierarchyLookup.getCtfEvent());
            productRelation.select(null);
            productLevel.select(null);
        }
    }

    @UiHandler("customerHierarchy")
    public void customerHierarchyClickListener(CustomTextField.ClickEvent event) {
        try {
            if (customerHierarchyLookup == null) {
                customerHierarchyLookup = new HierarchyLookup(event);
            } else {
                customerHierarchyLookup.setEvent(event);
                customerHierarchyLookup.getHierarchyNameDS().focus();
                customerHierarchyLookup.resetFields();
                customerHierarchyLookup.resetTable();
            }
            customerHierarchyCloseListener();
            getUI().addWindow(customerHierarchyLookup);
        } catch (Exception ex) {
            LOGGER.error("Error in customerHierarchyClickListener :", ex);
        }
    }

    @UiHandler("productHierarchy")
    public void productHierarchyClickListener(CustomTextField.ClickEvent event) {
        try {
            if (productHierarchyLookup == null) {
                productHierarchyLookup = new HierarchyLookup(event);
            } else {
                productHierarchyLookup.setEvent(event);
                productHierarchyLookup.getHierarchyNameDS().focus();
                productHierarchyLookup.resetFields();
                productHierarchyLookup.resetTable();
            }
            productHierarchyCloseListener();
            getUI().addWindow(productHierarchyLookup);
        } catch (Exception ex) {
            LOGGER.error("Error in productHierarchyClickListener :", ex);
        }
    }

    @UiHandler("customerRelation")
    public void customerRelationValueChange(Property.ValueChangeEvent event) {
        customerRelationValueChange(event.getProperty().getValue());
    }

    @UiHandler("productRelation")
    public void productRelationValueChange(Property.ValueChangeEvent event) {
        productRelationValueChange(event.getProperty().getValue());
    }

    @UiHandler("customer_moveLeftBtn")
    public void leftCustomersButtonLogic(Button.ClickEvent event) {
        moveLeftCustomersButtonLogic();
    }

    @UiHandler("customer_moveRightBtn")
    public void rightCustomersButtonLogic(Button.ClickEvent event) {
        moveRightCustomersButtonLogic();
    }

    @UiHandler("product_moveLeftProduct")
    public void leftProductsButtonLogic(Button.ClickEvent event) {
        moveLeftProductsButtonLogic();
    }

    @UiHandler("product_moveRightProduct")
    public void rightProductsButtonLogic(Button.ClickEvent event) {
        moveRightProductsButtonLogic();
    }

    @UiHandler("product_moveAllBtn")
    public void allRightProductsButtonLogic(Button.ClickEvent event) {
        moveAllProductsButtonLogic();
    }

    @UiHandler("customer_MoveAllBtn")
    public void allRightCustomersButtonLogic(Button.ClickEvent event) {
        moveAllCustomersButtonLogic();
    }

    @UiHandler("generateBtn")
    public void generateButtonLogic(Button.ClickEvent event) {
        generateButtonLogicForScreens();
    }

    @UiHandler("privateViewText")
    public void privateViewListener(CustomTextField.ClickEvent event) {
        loadPrivateViewLook();
    }

    @UiHandler("publicViewText")
    public void publicViewListener(CustomTextField.ClickEvent event) {
        loadPublicViewLook();
    }

    @UiHandler("deductionLevel")
    public void deductionValueChange(Property.ValueChangeEvent event) {
        loadAvailableDeductions();
    }

    @UiHandler("deduction_moveLeftBtn")
    public void moveLeftToRightDeduction(Button.ClickEvent event) {
        moveLeftToRightDeductions();
    }

    @UiHandler("deduction_moveAllBtn")
    public void moveAllDeduction(Button.ClickEvent event) {
        moveAllDeductions();
    }

    @UiHandler("deduction_moveRightBtn")
    public void moveRightDeduction(Button.ClickEvent event) {
        moveRightToLeftDeductions();
    }

    @UiHandler("saveViewBtn")
    public void saveviewLogicBtn(Button.ClickEvent event) {
        saveViewLogic();
    }

    @UiHandler("resetBtn")
    public void resetButtonLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the page to default values? ", new MessageBoxListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClicked(final ButtonId buttonId) {
                if ("yes".equalsIgnoreCase(buttonId.name())) {
                    LOGGER.debug("Entering Reset operation");
                    resetFields();
                    LOGGER.debug("Ending Reset operation");
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    public abstract void loadCustRelationAndLevel(int hierSid, CustomTextField.ClickEvent event);

    public abstract void loadProdRelationAndLevel(int hierSid, int glComp, CustomTextField.ClickEvent event);

    public abstract void loadAvailableCustomers();

    public abstract void loadAvailableProducts();

    public abstract void customerHierarchyCloseListener();

    public abstract void productHierarchyCloseListener();

    public abstract void moveLeftCustomersButtonLogic();

    public abstract void moveRightCustomersButtonLogic();

    public abstract void moveLeftProductsButtonLogic();

    public abstract void moveRightProductsButtonLogic();

    public abstract void moveAllProductsButtonLogic();

    public abstract void moveAllCustomersButtonLogic();

    public abstract void customerRelationValueChange(Object value);

    public abstract void productRelationValueChange(Object value);

    public abstract void generateButtonLogicForScreens();

    public abstract void loadPrivateViewLook();

    public abstract void loadPublicViewLook();

    public abstract void loadAvailableDeductions();

    public abstract void moveLeftToRightDeductions();

    public abstract void privateLookupCloseListener();

    public abstract void publicLookupCloseListener();

    public abstract void moveAllDeductions();

    public abstract void moveRightToLeftDeductions();

    public abstract void saveViewLogic();

    public void setAdjustmentOptionsPanelForBalanceSummaryReport() {

    }

    public void resetFields() {
        privateView.setValue(StringUtils.EMPTY);
        publicView.setValue(StringUtils.EMPTY);
        description.setValue(StringUtils.EMPTY);
        adjustmentType.select(null);
        deductionLevel.select(null);
        availableDeduction.removeAllItems();
        availableDeductionContainer.removeAllItems();
        selectedDeduction.removeAllItems();
        selectedDeductionContainer.removeAllItems();
        customerHierarchy.setValue(StringUtils.EMPTY);
        customerHierarchyLookup = null;
        customerRelation.removeAllItems();
        configureDropDowns(customerRelation);
        customerLevel.removeAllItems();
        configureDropDowns(customerLevel);
        availableCustomer.removeAllItems();
        availableCustomerContainer.removeAllItems();
        selectedCustomerContainer.removeAllItems();
        selectedCustomer.removeAllItems();
        productHierarchy.setValue(StringUtils.EMPTY);
        productHierarchyLookup = null;
        productRelation.removeAllItems();
        configureDropDowns(productRelation);
        productLevel.removeAllItems();
        configureDropDowns(productLevel);
        availableProduct.removeAllItems();
        availableProductContainer.removeAllItems();
        selectedProductContainer.removeAllItems();
        selectedProduct.removeAllItems();
        availableCustomer.resetFilters();
        availableProduct.resetFilters();
        availableDeduction.resetFilters();
        company.select(null);
        businessUnit.select(null);
        configureDropDowns(fromPeriod);
        configureDropDowns(toPeriod);
        fromPeriod.setValue(0);
        toPeriod.setValue(0);
        clearListView();
        deleteViewBtn.setEnabled(false);
    }

    @Override
    public boolean equals(Object dsOut) {
        return super.equals(dsOut);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void clearListView() {
        return;
    }

    private void writeObject(ObjectOutputStream dsOut) throws IOException {
        dsOut.defaultWriteObject();
    }

    private void readObject(ObjectInputStream dsOut) throws IOException, ClassNotFoundException {
        dsOut.defaultReadObject();
    }

    protected GtnARMHierarchyInputBean createInputBean(HierarchyLookupDTO selectedHierarchyLevelDto, int relationshipSid, int relationVersionNo, int levelNo, int hierLevelDefnSid, boolean isNdc, Set<Integer> rsContractSids) {
        GtnARMHierarchyInputBean inputBean = new GtnARMHierarchyInputBean();
        inputBean.setDeductionValues(StringUtils.join(rsContractSids, ARMUtils.COMMA));
        inputBean.setRelationShipBuilderSid(relationshipSid);
        inputBean.setRelationVersionNo(relationVersionNo);
        inputBean.setHierarchyDefinitionSid(selectedHierarchyLevelDto.getHierarchyId());
        inputBean.setHierarchyLevelDefinitionSid(hierLevelDefnSid);
        inputBean.setHierarchyVersionNo(selectedHierarchyLevelDto.getVersionNo());
        inputBean.setLevelNo(levelNo);
        inputBean.setIsNdc(isNdc);
        inputBean.setBusinessUnit(businessUnit.getValue() != null ? Integer.valueOf(String.valueOf(businessUnit.getValue())) : NumericConstants.ZERO);
        inputBean.setGlCompany(company.getValue() != null ? Integer.valueOf(String.valueOf(company.getValue())) : NumericConstants.ZERO);
        return inputBean;
    }

    protected List<LevelDTO> getSelectedCustomerContractList() {
        List<LevelDTO> ccList = Collections.emptyList();
        if (selectedCustomerContainer != null && !selectedCustomerContainer.getItemIds().isEmpty()) {
            LevelDTO selectedCurrentDto;
            ccList = new ArrayList<>();
            for (Object item : selectedCustomerContainer.getItemIds()) {
                selectedCurrentDto = DataSelectionUtils.getBeanFromId(item);
                if (selectedCurrentDto != null && !StringUtils.isBlank(selectedCurrentDto.getTableName())) {
                    ccList.add(selectedCurrentDto);
                }
            }
        }
        return ccList;
    }

    protected GtnARMHierarchyInputBean loadCustomersInInputbean(GtnARMHierarchyInputBean inputBean, int customerRelationVersionNo,
            List<LevelDTO> selectedCustomerContractList, int custHierSid, int customerVersionNo) {
        inputBean.setSelectedCustomerRelationShipBuilderVersionNo(customerRelationVersionNo);
        if (selectedCustomerContractList != null && !selectedCustomerContractList.isEmpty()) {
            inputBean.setSelectedCustomerList(DataSelectionLogic.convetToRelationBean(selectedCustomerContractList));
            inputBean.setSelectedCustomerHierarcySid(custHierSid);
            inputBean.setSelectedCustomerHierarchyVersionNo(customerVersionNo);
        }
        return inputBean;
    }

    protected void setDeductionTree(Map<String, DeductionLevelDTO> levelKeys,List<String> hierarchyKeys) {
        List<HierarchyString> strkeys = HierarchyString.getHierarchyStringList(hierarchyKeys, true);
        for (HierarchyString hKey : strkeys) {
            String key = hKey.getString();
            DeductionLevelDTO value = levelKeys.get(key);
            String parentKey = key.substring(0, key.lastIndexOf('.'));
            if (parentKey.lastIndexOf('.') >= 0) {
                parentKey = parentKey.substring(0, parentKey.lastIndexOf('.') + 1);
            }
            selectedDeductionContainer.addItem(value);
            DeductionLevelDTO parent = levelKeys.get(parentKey);

            if (parent != null) {
                selectedDeductionContainer.setParent(value, parent);
            }
            if (StringUtils.countMatches(key, ".") == NumericConstants.NINE) {
                selectedDeductionContainer.setChildrenAllowed(value, false);
            }
        }
    }
    
    protected void createHierarchyBasedOnHierarchyNo(ExtTreeContainer<LevelDTO> treeContainer, List<LevelDTO> reslistOne, int customerOrProductLevel) {
        treeContainer.removeAllItems();
        reslistOne.forEach(levelDto -> {
            addToContainer(levelDto, treeContainer, customerOrProductLevel);
        });
    }

    private void addToContainer(LevelDTO levelDto, ExtTreeContainer<LevelDTO> treeContainer, int customerOrProductLevel) {
        if (levelDto.getLevelNo() == 1) {
            treeContainer.addItem(levelDto);
            treeContainer.setChildrenAllowed(levelDto, true);
        } else {
            LevelDTO parentLevelDTO = getParentNode(levelDto, treeContainer);
            treeContainer.addBean(levelDto);
            treeContainer.setParent(levelDto, parentLevelDTO);
            treeContainer.setChildrenAllowed(levelDto, customerOrProductLevel != levelDto.getLevelNo());
        }
    }

    private LevelDTO getParentNode(LevelDTO childLevelDto, ExtTreeContainer<LevelDTO> treeContainer) {
        String childHierarchyNo = childLevelDto.getHierarchyNo();
        String tempString = childHierarchyNo.substring(0, childHierarchyNo.lastIndexOf('.'));
        String parentHierarchyNo = childHierarchyNo.substring(0, tempString.lastIndexOf('.') + 1);

        return treeContainer.getItemIds()
                .stream()
                .filter(levelDto -> getBeanFromId(levelDto).getHierarchyNo().equals(parentHierarchyNo))
                .findFirst()
                .orElse(childLevelDto);
    }
}
