/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.abstractCff;

import com.stpl.app.cff.util.Constants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.vaadin.data.util.AbstractContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.Window;
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
     * The treecontainer.
     */
    protected ExtTreeContainer<?> treecontainer = null;

    /**
     * The Constant CUST_ID_TRANSF_COLUMNS.
     */
    public static final Object[] CUST_ID_TRANSF_COLUMNS = new Object[]{"level"};

    /**
     * The Constant CUST_ID_TRANSF_HEADER.
     */
    public static final String[] CUST_ID_TRANSF_HEADER = new String[]{"LEVEL"};
    @UiField("addCustomer")
    private Button addCustomer;
    @UiField("removeCustomer")
    private Button removeCustomer;
    @UiField("addProduct")
    private Button addProduct;
    @UiField("removeProduct")
    private Button removeProduct;
    @UiField("save")
    private Button save;
    @UiField("select")
    private Button select;
    @UiField("close")
    private Button close;
    boolean saveFlag = false;

    /**
     * Instantiates a new abstract custom tree view.
     */
    public AbstractCustomTreeView() {

        super("Custom Tree look Up");
    }

    /**
     * Inits the.
     */
    public void init() {
        center();
        setClosable(true);
        setModal(true);
        setWidth("710px");
        setHeight("680px");
        setContent(Clara.create(getClass().getResourceAsStream("/cff/AbstractCustomTreeView.xml"), this));
        configureFields();

    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        addStyleName(Constants.bootstrap_ui);
        addStyleName(Constants.bootstrap);
        addStyleName(Constants.bootstrap_forecast_bootstrap_nm);

        viewName.setValue(getCustomMasterData());
        viewName.setMaxLength(200);
        treeTable.setContainerDataSource(getCustomTreeContainer());
        treeTable.setVisibleColumns(CUST_ID_TRANSF_COLUMNS);
        treeTable.setColumnHeaders(CUST_ID_TRANSF_HEADER);
        treeTable.setSizeFull();
        treeTable.setSizeUndefined();
        treeTable.setSelectable(true);
        treeTable.setHeight("470px");
        treeTable.setWidth("370px");
        loadCustomTree();
        productTable.setContainerDataSource(getProductsContainer());
        productTable.setVisibleColumns(CUST_ID_TRANSF_COLUMNS);
        productTable.setColumnHeaders(CUST_ID_TRANSF_HEADER);
        productTable.setSizeFull();
        productTable.setSizeUndefined();
        productTable.setSelectable(true);
        productTable.setPageLength(10);
        productTable.setHeight("210px");
        productTable.setWidth("210px");
        loadProducts();
        customerTable.setContainerDataSource(getCustomersContainer());
        customerTable.setVisibleColumns(CUST_ID_TRANSF_COLUMNS);
        customerTable.setColumnHeaders(CUST_ID_TRANSF_HEADER);
        customerTable.setSizeFull();
        customerTable.setSizeUndefined();
        customerTable.setSelectable(true);
        customerTable.setPageLength(10);
        customerTable.setHeight("210px");
        customerTable.setWidth("210px");
        loadCustomers();

        save.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                new AbstractNotificationUtils() {

                    @Override
                    public void noMethod() {
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
     * Custom tree add product logic.
     */
    protected abstract void customTreeAddProductLogic();

    /**
     * Custom tree remove customer logic.
     */
    protected abstract void customTreeRemoveCustomerLogic();

    /**
     * Custom tree remove product logic.
     */
    protected abstract void customTreeRemoveProductLogic();

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
