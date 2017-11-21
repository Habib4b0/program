/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastabstract.lookups;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.util.AbstractContainer;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.TreeTable;
import org.asi.ui.container.ExtTreeContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author karthikraja.k
 */
public abstract class AbstractCustomTreeView extends Window {

    /**
     * The view name.
     */
    @UiField("viewName")
    private TextField viewName;

    /**
     * The customer table.
     */
    @UiField("customerTable")
    protected Table customerTable;

    /**
     * The product table.
     */
    @UiField("productTable")
    protected Table productTable;
    
    
    /**
     * The deduction table.
     */
    @UiField("deductionTable")
    protected Table deductionTable;

    /**
     * The tree table.
     */
    @UiField("treeTable")
    protected TreeTable treeTable;

    /**
     * The customer container.
     */
    protected BeanItemContainer<?> customerContainer = null;

    /**
     * The product container.
     */
    protected BeanItemContainer<?> productContainer = null;

    
     /**
     * The deduction container.
     */
    protected BeanItemContainer<?> deductionContainer = null;
    /**
     * The treecontainer.
     */
    protected ExtTreeContainer<?> treecontainer = null;

    /**
     * The Constant CUST_ID_TRANSF_COLUMNS.
     */
    public final Object[] custIdTransfColumns = new Object[]{"level"};

    /**
     * The Constant CUST_ID_TRANSF_HEADER.
     */
    public final String[] custIdTransfHeader = new String[]{"LEVEL"};
    @UiField("addCustomer")
    private Button addCustomer;
    @UiField("removeCustomer")
    private Button removeCustomer;
    @UiField("addProduct")
    private Button addProduct;
    @UiField("removeProduct")
    private Button removeProduct;
    @UiField("addDeduction")
    private Button addDeduction;
    @UiField("removeDeduction")
    private Button removeDeduction;
    @UiField("save")
    private Button save;
    @UiField("select")
    private Button select;
    @UiField("close")
    private Button close;
    @UiField("deductionLayout")
    private HorizontalLayout deductionLayout;
    boolean saveFlag = false;
    SessionDTO sessionDto;

    /**
     * Instantiates a new abstract custom tree view.
     */
    public AbstractCustomTreeView(SessionDTO sessionDto) {
 
        super("Custom Tree look Up");
        this.sessionDto = sessionDto;
        
    }

    /**
     * Inits the.
     */
    public void init() {
        center();
        setClosable(true);
        setModal(true);
        setWidth("785px");
        setHeight("680px");
        setContent(Clara.create(getClass().getResourceAsStream("/AbstractCustomTreeView.xml"), this));
        configureFields(sessionDto);

    }

    /**
     * Configure fields.
     */
    private void configureFields(SessionDTO session) {
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_NM);

        viewName.setValue(getCustomMasterData());
        viewName.setMaxLength(NumericConstants.TWO_HUNDRED);
        treeTable.setContainerDataSource(getCustomTreeContainer());
        treeTable.setVisibleColumns(custIdTransfColumns);
        treeTable.setColumnHeaders(custIdTransfHeader);
        treeTable.setSelectable(true);
        treeTable.setHeight("680px");
        treeTable.setWidth("860px");
        loadCustomTree();
        productTable.setContainerDataSource(getProductsContainer());
        productTable.setVisibleColumns(custIdTransfColumns);
        productTable.setColumnHeaders(custIdTransfHeader);
        productTable.setSizeFull();
        productTable.setSizeUndefined();
        productTable.setSelectable(true);
        productTable.setPageLength(NumericConstants.TEN);
        productTable.setHeight(Constant.TWO_TEN_PX);
        productTable.setWidth(Constant.TWO_TEN_PX);
        loadProducts();
        customerTable.setContainerDataSource(getCustomersContainer());
        customerTable.setVisibleColumns(custIdTransfColumns);
        customerTable.setColumnHeaders(custIdTransfHeader);
        customerTable.setSizeFull();
        customerTable.setSizeUndefined();
        customerTable.setSelectable(true);
        customerTable.setPageLength(NumericConstants.TEN);
        customerTable.setHeight(Constant.TWO_TEN_PX);
        customerTable.setWidth(Constant.TWO_TEN_PX);
        loadCustomers();
        if (CommonUtil.isValueEligibleForLoading()  && !session.isIsDeductionCustom()) {
            deductionLayout.setVisible(false);
        } else {
            deductionLayout.setVisible(true);
            deductionTable.setContainerDataSource(getDeductionsContainer());
            deductionTable.setVisibleColumns(custIdTransfColumns);
            deductionTable.setColumnHeaders(custIdTransfHeader);
            deductionTable.setSizeFull();
            deductionTable.setSizeUndefined();
            deductionTable.setSelectable(true);
            deductionTable.setPageLength(NumericConstants.TEN);
            deductionTable.setHeight(Constant.TWO_TEN_PX);
            deductionTable.setWidth(Constant.TWO_TEN_PX);
            loadDeductions();
        }

        save.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                new AbstractNotificationUtils() {

                    @Override
                    public void noMethod() {
                        return;
                    }

                    @Override
                    public void yesMethod() {
                        customTreeSaveLogic(viewName.getValue());
                        saveFlag = true;
                    }
                }.getConfirmationMessage("Confirm Save", "Save record " + viewName.getValue() + "?");
            }
        });

        select.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {

                if (!saveFlag) {

                    new AbstractNotificationUtils() {

                        @Override
                        public void noMethod() {
                            return;
                        }

                        @Override
                        public void yesMethod() {
                            int selected = customTreeSelectLogic(viewName.getValue());
                            if (selected != 0) {
                                close();
                            }
                        }
                    }.getConfirmationMessage("Save Confirmation", "Save record " + viewName.getValue() + "?");

                } else {
                    int selected = customTreeSelectLogic(viewName.getValue());

                    if (selected != 0) {
                        close();
                    }

                }

            }

        });

        close.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                customTreeCloseLogic();
            }

        });

        addProduct.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                customTreeAddProductLogic();
            }

        });

        removeProduct.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                customTreeRemoveProductLogic();
            }

        });
        
        addDeduction.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                customTreeAddDeductionLogic();
            }

        });

        removeDeduction.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                customTreeRemoveDeductionLogic();
            }

        });

        addCustomer.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                customTreeAddCustomerLogic();
            }

        });
        removeCustomer.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                customTreeRemoveCustomerLogic();
            }

        });

    }

    /**
     * Load customers.
     *
     */
    protected abstract void loadCustomers();

    /**
     * Load products.
     *
     */
    protected abstract void loadProducts();
    
    
     /**
     * Load Deductions.
     *
     */
    protected abstract void loadDeductions();

    /**
     * Load Customers.
     *
     */
    protected abstract void loadCustomTree();

    /**
     * Custom tree save logic.
     *
     * @param viewName the view name
     * @return
     */
    protected abstract int customTreeSaveLogic(String viewName);

    /**
     * Custom tree select logic.
     *
     * @param viewName
     * @return
     */
    protected abstract int customTreeSelectLogic(String viewName);

    /**
     * Custom tree close logic.
     */
    protected abstract void customTreeCloseLogic();

    /**
     * Custom tree add customer logic.
     */
    protected abstract void customTreeAddCustomerLogic();

    /**
     * Custom tree add Deduction logic.
     */
    protected abstract void customTreeAddProductLogic();
    
      /**
     * Custom tree add product logic.
     */
    protected abstract void customTreeAddDeductionLogic();

    /**
     * Custom tree remove customer logic.
     */
    protected abstract void customTreeRemoveCustomerLogic();

    /**
     * Custom tree remove product logic.
     */
    protected abstract void customTreeRemoveProductLogic();
    
       /**
     * Custom tree remove Deduction logic.
     */
    protected abstract void customTreeRemoveDeductionLogic();

    /**
     * Load Custom view master data.
     *
     * @return
     */
    protected abstract String getCustomMasterData();

    /**
     * Gets products container.
     *
     * @return the abstract container
     */
    protected abstract AbstractContainer getProductsContainer();
    
      /**
     * Gets deduction container.
     *
     * @return the abstract container
     */
    protected abstract AbstractContainer getDeductionsContainer();

    /**
     * Gets Customers container.
     *
     * @return the abstract container
     */
    protected abstract AbstractContainer getCustomersContainer();

    /**
     * Gets CustomTree container.
     *
     * @return the abstract container
     */
    protected abstract AbstractContainer getCustomTreeContainer();

}
