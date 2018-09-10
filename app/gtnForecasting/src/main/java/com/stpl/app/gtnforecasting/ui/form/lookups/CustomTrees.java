/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.stpl.app.gtnforecasting.dto.SalesProjectionDTO;
import static com.stpl.app.utils.Constants.ButtonConstants.ADD_TO_SELECTED;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_CLOSE;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_SAVE;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_SELECT;
import static com.stpl.app.utils.Constants.ButtonConstants.REMOVE_TO_AVAILABLE;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.TreeTable;
import com.vaadin.v7.ui.VerticalLayout;
import org.asi.ui.container.ExtTreeContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The Class CustomTrees.
 */
public class CustomTrees extends Window {
      private static final Logger LOGGER = LoggerFactory.getLogger(CustomTrees.class);

	/** The view name. */
	private final TextField viewName = new TextField();

	/** The customer table. */
	private final Table customerTable = new Table();

	/** The product table. */
	private final Table productTable = new Table();

	/** The tree table. */
	private final TreeTable treeTable = new TreeTable();

	/** The available levels bean list. */

	/** The customer container. */
	private final BeanItemContainer<SalesProjectionDTO> customerContainer = new BeanItemContainer<>(
			SalesProjectionDTO.class);

	/** The product container. */
	private final BeanItemContainer<SalesProjectionDTO> productContainer = new BeanItemContainer<>(
			SalesProjectionDTO.class);

	/** The bean container. */
	private ExtTreeContainer<SalesProjectionDTO> beanContainer = new ExtTreeContainer<>(SalesProjectionDTO.class);

	/** The Constant CUST_ID_TRANSF_COLUMNS. */
	private final Object[] custIdTransfColumns = new Object[] { "level" };

	/** The Constant CUST_ID_TRANSF_HEADER. */
	private final String[] custIdTransfHeader = new String[] { "LEVEL" };

	/** The add customer. */
	private final Button addCustomer = new Button(ADD_TO_SELECTED.getConstant());

	/** The remove customer. */
	private final Button removeCustomer = new Button(REMOVE_TO_AVAILABLE.getConstant());

	/** The add product. */
	private final Button addProduct = new Button(ADD_TO_SELECTED.getConstant());

	/** The remove product. */
	private final Button removeProduct = new Button(REMOVE_TO_AVAILABLE.getConstant());

	/** The save. */
	private final Button save = new Button(BTN_SAVE.getConstant());

	/** The select. */
	private final Button select = new Button(BTN_SELECT.getConstant());

	/** The close. */
	private final Button close = new Button(BTN_CLOSE.getConstant());

	/** The customer id. */
	private Object customerId;

	/** The product id. */
	private Object productId;

	/** The tree table id. */
	private Object treeTableId;

	private final Panel mainPanel = new Panel();

	/** The sub panel. */
	private final Panel subPanel = new Panel("Relationship Tree");

	public CustomTrees() {
		super("Custom Tree look Up");
		init();
	}

	public CustomTrees(String add, String projectionId) {

	}

	public final void init() {
		center();
		setClosable(true);
		setWidth("850px");
		setHeight("850px");
		setContent(addToContent());
		configureFields();

	}

	/**
	 * Adds the to content.
	 *
	 * @return the panel
	 */
	private Panel addToContent() {

		final HorizontalLayout hlayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		final VerticalLayout vLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		final VerticalLayout vLayout1 = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		final GridLayout gLayout = UiUtils.addCommonGridLayout(NumericConstants.TWO, NumericConstants.TWO);
		vLayout.addComponent(addViewDetails());
		gLayout.addComponent(addCustomerTable());
		gLayout.addComponent(addCustomerSelectionButtons());
		gLayout.addComponent(addProductTable());
		gLayout.addComponent(addProductSelectionButtons());
		hlayout.addComponent(gLayout);
		hlayout.addComponent(addCustomTreeTable());
		vLayout.addComponent(hlayout);
		subPanel.setContent(vLayout);
		vLayout1.addComponent(subPanel);
		vLayout1.addComponent(addFooterButtons());
		mainPanel.setContent(vLayout1);
		subPanel.setSizeFull();
		mainPanel.setSizeFull();
		return mainPanel;
	}

	/**
	 * Adds the view details.
	 *
	 * @return the horizontal layout
	 */
	private HorizontalLayout addViewDetails() {
		final HorizontalLayout hlayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		hlayout.addComponent(new Label("Tree View Name"));
		hlayout.addComponent(viewName);
		return hlayout;
	}

	/**
	 * Adds the customer table.
	 *
	 * @return the component
	 */
	private Component addCustomerTable() {
		final VerticalLayout vlayLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		vlayLayout.addComponent(addSpace());
		vlayLayout.addComponent(customerTable);
		for (int i = 1; i < NumericConstants.SEVEN; i++) {
			final SalesProjectionDTO dto = new SalesProjectionDTO();
			dto.setLevel("Customer Level" + i);
			customerContainer.addBean(dto);
		}

		return vlayLayout;
	}

	/**
	 * Adds the product table.
	 *
	 * @return the vertical layout
	 */
	private VerticalLayout addProductTable() {
		final VerticalLayout vlayLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		vlayLayout.addComponent(addSpace());
		vlayLayout.addComponent(productTable);
		for (int i = 1; i < NumericConstants.FOUR; i++) {
			final SalesProjectionDTO dto = new SalesProjectionDTO();
			dto.setLevel("Product Level" + i);
			productContainer.addBean(dto);
		}

		return vlayLayout;
	}

	/**
	 * Adds the customer selection buttons.
	 *
	 * @return the vertical layout
	 */
	private VerticalLayout addCustomerSelectionButtons() {
		final VerticalLayout vlayLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		vlayLayout.addComponent(addSpace());
		vlayLayout.addComponent(addSpace());
		vlayLayout.addComponent(addSpace());
		vlayLayout.addComponent(addCustomer);
		vlayLayout.addComponent(removeCustomer);
		vlayLayout.setSizeFull();
		vlayLayout.setSizeUndefined();
		vlayLayout.setStyleName("veralign");
		vlayLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		return vlayLayout;
	}

	/**
	 * Adds the product selection buttons.
	 *
	 * @return the vertical layout
	 */
	private VerticalLayout addProductSelectionButtons() {
		final VerticalLayout vlayLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		vlayLayout.addComponent(addSpace());
		vlayLayout.addComponent(addSpace());
		vlayLayout.addComponent(addSpace());
		vlayLayout.addComponent(addProduct);
		vlayLayout.addComponent(removeProduct);
		vlayLayout.setSizeFull();
		vlayLayout.setSizeUndefined();
		vlayLayout.setStyleName("veralign");
		return vlayLayout;
	}

	/**
	 * Adds the custom tree table.
	 *
	 * @return the vertical layout
	 */
	private VerticalLayout addCustomTreeTable() {
		final VerticalLayout vlayLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		vlayLayout.addComponent(addSpace());
		vlayLayout.addComponent(treeTable);
		return vlayLayout;
	}

	/**
	 * Adds the space.
	 *
	 * @return the grid layout
	 */
	private static GridLayout addSpace() {
		final GridLayout gridLayout = new GridLayout(1, 1);
		gridLayout.addComponent(new Label("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", ContentMode.HTML));
		return gridLayout;
	}

	/**
	 * Adds the footer buttons.
	 *
	 * @return the horizontal layout
	 */
	private HorizontalLayout addFooterButtons() {
		final HorizontalLayout hlayout = (HorizontalLayout) UiUtils.getLayout(new HorizontalLayout());
		hlayout.addComponent(save);
		hlayout.addComponent(select);
		hlayout.addComponent(close);
		return hlayout;
	}

	/**
	 * Configure fields.
	 */
	private void configureFields() {
		final String TABLE_HEIGHT = "210px";
		customerTable.setCaption("Customer Hierarchy");
		productTable.setCaption("Product Hierarchy");
		treeTable.setCaption("Tree Structure");

		treeTable.setContainerDataSource(beanContainer);
		treeTable.setVisibleColumns(custIdTransfColumns);
		treeTable.setColumnHeaders(custIdTransfHeader);
		treeTable.setSizeFull();
		treeTable.setSizeUndefined();
		treeTable.setHeight("491px");
		treeTable.setWidth("370px");
		treeTable.setSelectable(true);
		treeTable.setSortEnabled(false);

		productTable.setContainerDataSource(productContainer);
		productTable.setVisibleColumns(custIdTransfColumns);
		productTable.setColumnHeaders(custIdTransfHeader);
		productTable.setSizeFull();
		productTable.setSizeUndefined();
		productTable.setPageLength(NumericConstants.TEN);
		productTable.setHeight(TABLE_HEIGHT);
		productTable.setWidth(TABLE_HEIGHT);
		productTable.setSelectable(true);

		customerTable.setContainerDataSource(customerContainer);
		customerTable.setVisibleColumns(custIdTransfColumns);
		customerTable.setColumnHeaders(custIdTransfHeader);
		customerTable.setSizeFull();
		customerTable.setSizeUndefined();
		customerTable.setPageLength(NumericConstants.TEN);
		customerTable.setHeight(TABLE_HEIGHT);
		customerTable.setWidth(TABLE_HEIGHT);
		customerTable.setSelectable(true);
		customerTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

			@Override
			public void itemClick(final ItemClickEvent event) {
				customerId = event.getItemId();
			}
		});

		productTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

			@Override
			public void itemClick(final ItemClickEvent event) {
				productId = event.getItemId();
			}
		});
		treeTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

			@Override
			public void itemClick(final ItemClickEvent event) {
				treeTableId = event.getItemId();
			}
		});

		addCustomer.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(final Button.ClickEvent event) {
				if (customerId != null) {
					customerTable.removeItem(customerId);
					beanContainer.addBean(getBeanFromId(customerId));
					beanContainer.setChildrenAllowed(getBeanFromId(customerId), true);
					if (treeTableId != null) {
						final SalesProjectionDTO dto = getBeanFromId(treeTableId);
						beanContainer.setParent(getBeanFromId(customerId), dto);
						treeTable.setCollapsed(getBeanFromId(treeTableId), false);
					}
				}
			}
		});
		removeCustomer.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(final Button.ClickEvent event) {
				if (treeTableId != null) {
                                   LOGGER.debug("removeCustomer addClickListener");
				}
			}
		});
		addProduct.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(final Button.ClickEvent event) {
				if (productId != null) {
					productTable.removeItem(productId);
					beanContainer.addBean(getBeanFromId(productId));
					beanContainer.setChildrenAllowed(getBeanFromId(productId), true);
					if (treeTableId != null) {
						final SalesProjectionDTO dto = getBeanFromId(treeTableId);
						beanContainer.setParent(getBeanFromId(productId), dto);
						treeTable.setCollapsed(getBeanFromId(treeTableId), false);
					}

				}
			}
		});

		removeProduct.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(final Button.ClickEvent event) {
				if (treeTableId != null) {
                                    LOGGER.debug("removeCustomer addClickListener");
				}
			}
		});

	}

	/**
	 * Gets the bean from id.
	 *
	 * @param obj
	 *            the id
	 * @return the bean from id
	 */
	public SalesProjectionDTO getBeanFromId(final Object obj) {

		BeanItem<?> targetItem = null;
		if (obj instanceof BeanItem<?>) {
			targetItem = (BeanItem<?>) obj;
		} else if (obj instanceof SalesProjectionDTO) {
			targetItem = new BeanItem<>((SalesProjectionDTO) obj);
		}
                if (targetItem != null) {
                    return (SalesProjectionDTO) targetItem.getBean();
                } else {
                    return null;
                }
	}
}
