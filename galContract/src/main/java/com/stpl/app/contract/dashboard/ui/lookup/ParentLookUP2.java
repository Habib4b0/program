package com.stpl.app.contract.dashboard.ui.lookup;


import com.stpl.app.contract.common.util.CommonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.contract.dashboard.dto.RsLookupFilterGenerator;
import com.stpl.app.contract.global.dto.RebateScheduleMasterDTO;
import com.stpl.app.contract.global.dto.RsCriteria;
import com.stpl.app.contract.global.dto.RsDAO;
import com.stpl.app.contract.global.util.CommonUtils;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ResponsiveUtils;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import elemental.events.KeyboardEvent;

/**
 * The Class ParentLookUP2.
 */
public final class ParentLookUP2 extends Window {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ParentLookUP2.class);
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -689317138566663656L;
    /**
     * The rebate schedule id.
     */
    @UiField("rebateScheduleID")
    private TextField rebateScheduleId;
    /**
     * The rebate schedule system id.
     */
    public final TextField rebateScheduleSystemId = new TextField();
    /**
     * The rebate schedule name.
     */
    @UiField("rebateScheduleName")
    private TextField rebateScheduleName;
    /**
     * The rebate schedule no.
     */
    @UiField("rebateScheduleNo")
    private TextField rebateScheduleNo;
    /**
     * The rebate schedule status.
     */
    @UiField("rebateScheduleStatus")
    private ComboBox rebateScheduleStatus;
    /**
     * The rebate schedule type.
     */
    @UiField("rebateScheduleType")
    private ComboBox rebateScheduleType;
    /**
     * The rebate program type.
     */
    @UiField("rebateProgramType")
    private ComboBox rebateProgramType;
    /**
     * The parent rebate schedule name.
     */
    public TextField parentRebateScheduleName = new TextField();
    /**
     * The parent rebate schedule id.
     */
    public TextField parentRebateScheduleId = new TextField();
    /**
     * The search.
     */
    @UiField("searchBtn")
    private Button search;
    /**
     * The reset.
     */
    @UiField("resetBtn")
    private Button reset;
    /**
     * The tabel.
     */
    @UiField("resultsTable")
    private ExtFilterTable table;
    
    @UiField("previousColumnBtn")
    private Button prevColumn;
    
    @UiField("nextColumnBtn")
    private Button nextColumn;
    
    @UiField("errorLB")
    private ErrorLabel errorMsg;
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;
    /**
     * The search results.
     */
    private LazyBeanItemContainer searchResults;
    /**
     * A dummy BeanItemContainer to avoid load issue in empty lazy bean
     * container
     */
    private BeanItemContainer<RebateScheduleMasterDTO> dummySearchResulbeans = new BeanItemContainer<RebateScheduleMasterDTO>(RebateScheduleMasterDTO.class);
    /** The common util. */
    private CommonUtil commonUtil = CommonUtil.getInstance();    

    /**
     * Gets the parent rebate schedule name.
     *
     * @return the parent rebate schedule name
     */
    public TextField getParentRebateScheduleName() {
        return parentRebateScheduleName;
    }

    /**
     * Sets the parent rebate schedule name.
     *
     * @param parentRebateScheduleName the parent rebate schedule name
     */
    public void setParentRebateScheduleName(final TextField parentRebateScheduleName) {
        this.parentRebateScheduleName = parentRebateScheduleName;
    }

    /**
     * Gets the parent rebate schedule id.
     *
     * @return the parent rebate schedule id
     */
    public TextField getParentRebateScheduleId() {
        return parentRebateScheduleId;
    }

    /**
     * Sets the parent rebate schedule id.
     *
     * @param parentRebateScheduleId the parent rebate schedule id
     */
    public void setParentRebateScheduleId(final TextField parentRebateScheduleId) {
        this.parentRebateScheduleId = parentRebateScheduleId;
    }

    /**
     * Gets the rebate schedule id.
     *
     * @return the rebate schedule id
     */
    public TextField getRebateScheduleId() {
        return rebateScheduleId;
    }

    /**
     * Gets the rebate schedule system id.
     *
     * @return the rebate schedule system id
     */
    public TextField getRebateScheduleSystemId() {
        return rebateScheduleSystemId;
    }

    /**
     * Gets the rebate schedule name.
     *
     * @return the rebate schedule name
     */
    public TextField getRebateScheduleName() {
        return rebateScheduleName;
    }

    /**
     * Gets the rebate schedule no.
     *
     * @return the rebate schedule no
     */
    public TextField getRebateScheduleNo() {
        return rebateScheduleNo;
    }

    /**
     * Gets the rebate schedule type.
     *
     * @return the rebate schedule type
     */
    public ComboBox getRebateScheduleType() {
        return rebateScheduleType;
    }

    /**
     * Gets the rebate program type.
     *
     * @return the rebate program type
     */
    public ComboBox getRebateProgramType() {
        return rebateProgramType;
    }

    /**
     * Gets the search.
     *
     * @return the search
     */
    public Button getSearch() {
        return search;
    }

    /**
     * Gets the reset.
     *
     * @return the reset
     */
    public Button getReset() {
        return reset;
    }

    /**
     * Gets the tabel.
     *
     * @return the tabel
     */
    public ExtFilterTable getTabel() {
        return table;
    }

    /**
     * Gets the error msg.
     *
     * @return the error msg
     */
    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    /**
     * Gets the search results.
     *
     * @return the search results
     */
    public LazyBeanItemContainer getSearchResults() {
        return searchResults;
    }

    /**
     * Sets the search results.
     *
     */
    public void setSearchResults(final LazyBeanItemContainer searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * Sets the binder.
     *
     * @param binder the binder
     */
    public void setBinder(final ErrorfulFieldGroup binder) {
        this.binder = binder;
    }

    /**
     * The Constructor.
     *
     * @param parentRebateScheduleId the parent rebate schedule id
     * @param parentRebateScheduleName the parent rebate schedule name
     */
    public ParentLookUP2(final TextField parentRebateScheduleId, final TextField parentRebateScheduleName) throws SystemException, Exception {
        super("Parent Rebate Schedule");
        this.parentRebateScheduleId = parentRebateScheduleId;
        this.parentRebateScheduleName = parentRebateScheduleName;
        LOGGER.info("Entering ParentLookUP2");
        init();
        LOGGER.info("End of ParentLookUP2");
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {
        LOGGER.info("Entering getBinder method");
        binder = new ErrorfulFieldGroup(new BeanItem<RebateScheduleMasterDTO>(new RebateScheduleMasterDTO()));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        LOGGER.info("End of getBinder method");
        return binder;
    }

    /**
     * Inits the.
     */
    private void init() throws SystemException, Exception {
        LOGGER.info("Entering init method");

        center();
        setClosable(true);
        setModal(true);
        setHeight("600px");
        setContent(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/parent-rebate-lookup.xml"),this));
        addToContent();
        configureFields();
        getBinder();
        LOGGER.info("End of init method");

    }

    /**
     * Adds the to content.
     *
     * @return the panel
     */
    private void addToContent() {      
        LOGGER.info("Entering addToContent method");      
        ResponsiveUtils.addButtonListeners(table, prevColumn, nextColumn);
        addTabel1();        
        LOGGER.info("End of addToContent method");        
    }

    /**
     * Adds the to grid.
     *
     * @return the grid layout
     */
    protected HorizontalLayout addToGrid() {
        LOGGER.info("Entering addToGrid method");
        final HorizontalLayout hLayout1= new HorizontalLayout();
        hLayout1.setStyleName("responsiveTabGrid");
        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(new Label("Rebate Schedule ID:"));
        cssLayout.addComponent(rebateScheduleId);
        cssLayout.addComponent(new Label("Rebate Schedule Name:"));
        cssLayout.addComponent(rebateScheduleName);
        cssLayout.addComponent(new Label("Rebate Schedule No:"));
        cssLayout.addComponent(rebateScheduleNo);
        cssLayout.addComponent(new Label("Rebate Schedule Status:"));
        cssLayout.addComponent(rebateScheduleStatus);
        cssLayout.addComponent(new Label("Rebate Schedule Type:"));
        cssLayout.addComponent(rebateScheduleType);
        cssLayout.addComponent(new Label("Program Type:"));
        cssLayout.addComponent(rebateProgramType);
        hLayout1.addComponent(cssLayout);
        LOGGER.info("End of addToGrid method");
        return hLayout1;
    }

    /**
     * Adds the tabel1.
     *
     * @return the table
     */
    private ExtFilterTable addTabel1() {
        LOGGER.info("Entering addTabel1 method");
        table.setPageLength(10);
        table.setImmediate(true);
        table.setSelectable(true);
        table.setWidth(98, Unit.PERCENTAGE);
        table.setContainerDataSource(dummySearchResulbeans);
        table.setVisibleColumns(CommonUtils.RS_COL_COLUMNS);
        table.setColumnHeaders(CommonUtils.RS_COL_HEADERS);
        table.addStyleName("filterbar");
        table.setFilterBarVisible(true);
        table.setFilterGenerator(new RsLookupFilterGenerator());
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.addItemClickListener(new ItemClickListener() {
            /**
             * Method used to table logic and its item click listener
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                LOGGER.info("Entering table itemClick method");
                final RebateScheduleMasterDTO companySearchDto = (RebateScheduleMasterDTO) searchResults.getItem(event.getItemId()).getBean();
                parentRebateScheduleId.setValue(companySearchDto.rebateScheduleNo);
                parentRebateScheduleName.setReadOnly(false);
                parentRebateScheduleName.setValue(companySearchDto.rebateScheduleName);
                parentRebateScheduleName.setReadOnly(true);
                close();
                LOGGER.info("End of table itemClick method");

            }
        });
        setDefaultTableColumns();
        LOGGER.info("End of addTabel1 method");
        return table;
    }

    /**
     * Gets the rebate schedule status.
     *
     * @return the rebate schedule status
     */
    private Container getRebateScheduleStatus() {
        LOGGER.info("Entering getRebateScheduleStatus method");
        final List<String> stsLst = new ArrayList<String>();
        stsLst.add(Constants.SELECT_ONE);
        stsLst.add(Constants.ACTIVE);
        stsLst.add(Constants.IN_ACTIVE);
        LOGGER.info("End of getRebateScheduleStatus method");
        return new IndexedContainer(stsLst);
    }

    /**
     * Adds the buttons.
     *
     * @return the horizontal layout
     */
    private HorizontalLayout addButtons() {
        final HorizontalLayout button = new HorizontalLayout();
        LOGGER.info("Entering addButtons method");
        button.addComponent(search);
        button.addComponent(reset);
        button.setSpacing(true);
        LOGGER.info("End of addButtons method");
        return button;
    }

    /**
     * Configure fields.
     */
    protected void configureFields() throws SystemException, Exception {
        LOGGER.info("Entering configureFields method");
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
         setResizable(false);
         
        commonUtil.loadComboBox(rebateScheduleStatus, CommonUtils.STATUS, false);
        commonUtil.loadComboBox(rebateScheduleType, CommonUtils.RS_TYPE, false);
        commonUtil.loadComboBox(rebateProgramType, CommonUtils.REBATE_PROGRAM_TYPE, false);
         

        search.addClickListener(new ClickListener() {
            /**
             * Called when a button has been clicked.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.info("Entering search buttonClick method");
                    RsCriteria searchCriteria = new RsCriteria();
                    List<Object> collapsedColumns = new ArrayList<Object>();
                    for (Object item : table.getVisibleColumns()) {
                        if (table.isColumnCollapsed(item)) {
                            collapsedColumns.add(item);
                        }
                    }
                    binder.commit();
                    searchCriteria.setCustomDirty(true);
                    searchResults = new LazyBeanItemContainer(RebateScheduleMasterDTO.class, new RsDAO(binder), searchCriteria);


                    table.setContainerDataSource(searchResults);
                    if (searchResults.size() > Constants.ZERO) {
                        CommonUIUtils.successNotification("Search Completed");
                    } else {
                        CommonUIUtils.successNotification("No results found");
                    }

                    table.setVisibleColumns(CommonUtils.RS_COL_COLUMNS);
                    table.setColumnHeaders(CommonUtils.RS_COL_HEADERS);
                    searchCriteria.setCustomDirty(false);
                    for (Object propertyId : collapsedColumns) {
                    table.setColumnCollapsed(propertyId, true);
                    }
                    searchCriteria.setCustomDirty(true);
                    LOGGER.info("End of search buttonClick method");

                } catch (CommitException e) {
                    LOGGER.error(e);
                }
            }
        });

        reset.addClickListener(new ClickListener() {
            /**
             * Called when a button has been clicked.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("Entering reset buttonClick method");
                List<Object> collapsedColumns = new ArrayList<Object>();
                    for (Object item : table.getVisibleColumns()) {
                        if (table.isColumnCollapsed(item)) {
                            collapsedColumns.add(item);
                        }
                    }
                binder.setItemDataSource(new BeanItem<RebateScheduleMasterDTO>(new RebateScheduleMasterDTO()));
                searchResults.removeAllItems();
                binder.getErrorDisplay().clearError();

                table.setContainerDataSource(dummySearchResulbeans);

                table.setVisibleColumns(CommonUtils.RS_COL_COLUMNS);
                table.setColumnHeaders(CommonUtils.RS_COL_HEADERS);

                binder.getErrorDisplay().clearError();
                setCloseShortcut(KeyboardEvent.KeyCode.ESC);
                    for (Object propertyId : collapsedColumns) {
                    table.setColumnCollapsed(propertyId, true);
                    }
                LOGGER.info("End of reset buttonClick method");
            }
        });
        LOGGER.info("End of configureFields method");

    }

    /**
     * Gets the dummySearchResulbeans.
     *
     * @return the dummySearchResulbeans
     */
    public BeanItemContainer<RebateScheduleMasterDTO> getDummySearchResulbeans() {
        return dummySearchResulbeans;
    }

    /**
     * Sets the dummySearchResulbeans
     *
     */
    public void setDummySearchResulbeans(final BeanItemContainer<RebateScheduleMasterDTO> dummySearchResulbeans) {
        this.dummySearchResulbeans = dummySearchResulbeans;
    }
    
    private void setDefaultTableColumns() {

        table.setColumnCollapsingAllowed(true);
        int browserWidth = Page.getCurrent().getBrowserWindowWidth();
        try {
            
            if (browserWidth > 850) {
               
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
}
                for (Object propertyId : getDefaultCollapsibleColumns(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < 850 && browserWidth > 450) {
               
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns850Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }

            } else if (browserWidth < 450) {
              
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns450Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);;
        }
    }
    
    private static String[] getCollapsibleColumns450Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
}

    private static String[] getCollapsibleColumns850Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }
    private static String[] getDefaultCollapsibleColumns(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
        list.remove(propertyIds[4]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }
}
