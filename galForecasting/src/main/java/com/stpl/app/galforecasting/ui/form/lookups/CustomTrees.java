/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.galforecasting.ui.form.lookups;



import com.stpl.app.galforecasting.dto.SalesProjectionDTO;

import static com.stpl.app.utils.Constants.ButtonConstants.*;
import com.stpl.app.utils.UiUtils;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;


// TODO: Auto-generated Javadoc
/**
 * The Class CustomTrees.
 */
public class CustomTrees extends Window {

    /** The view name. */
    private final TextField viewName = new TextField();
    
    /** The customer table. */
    private final Table customerTable = new Table();
    
    /** The product table. */
    private final Table productTable = new Table();
    
    /** The tree table. */
    private final TreeTable treeTable = new TreeTable();
    
    /** The available levels bean list. */
    private List<BeanItemContainer<SalesProjectionDTO>> availableLevelsBeanList = new ArrayList<BeanItemContainer<SalesProjectionDTO>>();
    
    /** The customer container. */
    private final BeanItemContainer<SalesProjectionDTO> customerContainer = new BeanItemContainer<SalesProjectionDTO>(SalesProjectionDTO.class);
    
    /** The product container. */
    private final BeanItemContainer<SalesProjectionDTO> productContainer = new BeanItemContainer<SalesProjectionDTO>(SalesProjectionDTO.class);
  
    /** The bean container. */
    private ExtTreeContainer<SalesProjectionDTO> beanContainer = new ExtTreeContainer<SalesProjectionDTO>(SalesProjectionDTO.class);
    
    /** The Constant CUST_ID_TRANSF_COLUMNS. */
    public static final Object[] CUST_ID_TRANSF_COLUMNS = new Object[]{"level"};
    
    /** The Constant CUST_ID_TRANSF_HEADER. */
    public static final String[] CUST_ID_TRANSF_HEADER = new String[]{"LEVEL"};
    
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
    
    /** The hierarchy tree. */
    private Tree hierarchyTree = new Tree();
    
    /** The tree bean. */
    private SalesProjectionDTO treeBean;
    
    /** The total levels. */
    private int totalLevels;
    
    /** The available table list. */
    private List<ExtFilterTable> availableTableList = new ArrayList<ExtFilterTable>();
    
    /** The final selected results bean. */
    private BeanItemContainer<SalesProjectionDTO> finalSelectedResultsBean = new BeanItemContainer<SalesProjectionDTO>(SalesProjectionDTO.class);
   
    /** The level values list. */
    private Map<String, List<SalesProjectionDTO>> levelValuesList;
    
    /** The saved level values list. */
    private Map<String, List<SalesProjectionDTO>> savedLevelValuesList;
    
    /** The level layout. */
    private HorizontalLayout levelLayout;
    
    /** The layout. */
    private GridLayout layout;
    
    /** The main panel. */
    private Panel mainPanel = new Panel();
    
    /** The sub panel. */
    private Panel subPanel = new Panel("Relationship Tree");
    
    /** The level panel. */
    private Panel levelPanel = new Panel("Relationship Levels");
    
    /** The selected levels bean list. */
    private List<BeanItemContainer<SalesProjectionDTO>> selectedLevelsBeanList = new ArrayList<BeanItemContainer<SalesProjectionDTO>>();
  

    /**
     * Instantiates a new custom trees.
     */
    public CustomTrees() {
        super("Custom Tree look Up");
        init();
    }
    
    /**
     * Instantiates a new custom trees.
     *
     * @param indicator the indicator
     * @param projectionId the projection id
     */
    public CustomTrees(String indicator, int projectionId) {
        super("Custom Tree look Up");
        init();
    }

    /**
     * Instantiates a new custom trees.
     *
     * @param add the add
     * @param projectionId the projection id
     */
    public CustomTrees(String add, String projectionId) {
        
    }

    /**
     * Inits the.
     */
    public void init() {
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
        
        final HorizontalLayout hlayout = (HorizontalLayout)UiUtils.getLayout(HorizontalLayout.class);
        final VerticalLayout vLayout = (VerticalLayout)UiUtils.getLayout(VerticalLayout.class);
        final VerticalLayout vLayout1 = (VerticalLayout)UiUtils.getLayout(VerticalLayout.class);
        final GridLayout gLayout = UiUtils.addCommonGridLayout(2, 2);
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
        final HorizontalLayout hlayout = (HorizontalLayout)UiUtils.getLayout(HorizontalLayout.class);
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
        final VerticalLayout vlayLayout = (VerticalLayout)UiUtils.getLayout(VerticalLayout.class);
        vlayLayout.addComponent(addSpace());
        vlayLayout.addComponent(customerTable);
         for(int i=1; i<7;i++){
            final SalesProjectionDTO dto = new SalesProjectionDTO();
            dto.setLevel("Customer Level"+i);
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
        final VerticalLayout vlayLayout = (VerticalLayout)UiUtils.getLayout(VerticalLayout.class);
        vlayLayout.addComponent(addSpace());
        vlayLayout.addComponent(productTable);
         for(int i=1; i<4;i++){
            final SalesProjectionDTO dto = new SalesProjectionDTO();
            dto.setLevel("Product Level"+i);
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
        final VerticalLayout vlayLayout = (VerticalLayout)UiUtils.getLayout(VerticalLayout.class);
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
        final VerticalLayout vlayLayout = (VerticalLayout)UiUtils.getLayout(VerticalLayout.class);
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
        final VerticalLayout vlayLayout = (VerticalLayout)UiUtils.getLayout(VerticalLayout.class);
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
        final HorizontalLayout hlayout = (HorizontalLayout)UiUtils.getLayout(HorizontalLayout.class);
        hlayout.addComponent(save);
        hlayout.addComponent(select);
        hlayout.addComponent(close);
        return hlayout;
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
    	final String tableHeight = "210px";
        customerTable.setCaption("Customer Hierarchy");
        productTable.setCaption("Product Hierarchy");
        treeTable.setCaption("Tree Structure");

        treeTable.setContainerDataSource(beanContainer);
        treeTable.setVisibleColumns(CUST_ID_TRANSF_COLUMNS);
        treeTable.setColumnHeaders(CUST_ID_TRANSF_HEADER);
        treeTable.setSizeFull();
        treeTable.setSizeUndefined();
        treeTable.setHeight("491px");
        treeTable.setWidth("370px");
       treeTable.setSelectable(true);
       treeTable.setSortEnabled(false);

        productTable.setContainerDataSource(productContainer);
        productTable.setVisibleColumns(CUST_ID_TRANSF_COLUMNS);
        productTable.setColumnHeaders(CUST_ID_TRANSF_HEADER);
        productTable.setSizeFull();
        productTable.setSizeUndefined();
        productTable.setPageLength(10);
        productTable.setHeight(tableHeight);
        productTable.setWidth(tableHeight);
        productTable.setSelectable(true);
        
        customerTable.setContainerDataSource(customerContainer);
        customerTable.setVisibleColumns(CUST_ID_TRANSF_COLUMNS);
        customerTable.setColumnHeaders(CUST_ID_TRANSF_HEADER);
        customerTable.setSizeFull();
        customerTable.setSizeUndefined();
        customerTable.setPageLength(10);
        customerTable.setHeight(tableHeight);
        customerTable.setWidth(tableHeight);
        customerTable.setSelectable(true);
        customerTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(final ItemClickEvent event) {
                customerId=event.getItemId();
            }
        });
        
        productTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(final ItemClickEvent event) {
                productId=event.getItemId();
            }
        });
        treeTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(final ItemClickEvent event) {
                treeTableId=event.getItemId();
            }
        });
        
        
        addCustomer.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(final Button.ClickEvent event) {
             if(customerId!=null){
                 customerTable.removeItem(customerId);
        beanContainer.addBean(getBeanFromId(customerId));
        beanContainer.setChildrenAllowed(getBeanFromId(customerId), true);
        if(treeTableId!=null){
         final SalesProjectionDTO dto=getBeanFromId(treeTableId);
        beanContainer.setParent(getBeanFromId(customerId), dto);
        treeTable.setCollapsed(getBeanFromId(treeTableId), false);
        }
             }
            }
        });
         removeCustomer.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(final Button.ClickEvent event) {
               if(treeTableId!=null){
              
             
             }
            }
        });
          addProduct.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(final Button.ClickEvent event) {
               if(productId!=null){
                    productTable.removeItem(productId);
             beanContainer.addBean(getBeanFromId(productId));
        beanContainer.setChildrenAllowed(getBeanFromId(productId), true);
        if(treeTableId!=null){
         final SalesProjectionDTO dto=getBeanFromId(treeTableId);
        beanContainer.setParent(getBeanFromId(productId), dto);
        treeTable.setCollapsed(getBeanFromId(treeTableId), false);
        }
             
             }
            }
        });
          
           removeProduct.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(final Button.ClickEvent event) {
               if(treeTableId!=null){
             
             
             }
            }
        });
              
        
       
       
    }
     
		
			/**
     * Gets the bean from id.
     *
     * @param obj the id
     * @return the bean from id
     */
    public SalesProjectionDTO getBeanFromId(final Object obj) {
       
        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
           targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof SalesProjectionDTO) {
           targetItem = new BeanItem<SalesProjectionDTO>((SalesProjectionDTO) obj);
        }
        return (SalesProjectionDTO)targetItem.getBean();
    }
}
  

