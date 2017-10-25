package com.stpl.app.contract.dashboard.ui.lookup;

import com.stpl.app.contract.common.util.CommonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
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

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import elemental.events.KeyboardEvent.KeyCode;

/**
 * The Class ParentLookUp.
 */
public final class ParentLookUp extends Window {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ParentLookUp.class);
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
     * The rebate schedule trans ref no.
     */
    public TextField rebateScheduleTransRefNo = new TextField();
    /**
     * The rebate schedule trans ref name.
     */
    public TextField rebateScheduleTransRefName = new TextField();
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
    private ExtFilterTable tabel;
    
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
    public RsCriteria rsCriteria = new RsCriteria();
    
    /**
     * A dummy BeanItemContainer to avoid load issue in empty lazy bean
     * container
     */
    private BeanItemContainer<RebateScheduleMasterDTO> dummySearchResulbeans = new BeanItemContainer<RebateScheduleMasterDTO>(RebateScheduleMasterDTO.class);

    /**
     * Gets the rebate schedule trans ref no.
     *
     * @return the rebate schedule trans ref no
     */
    public TextField getRebateScheduleTransRefNo() {
        return rebateScheduleTransRefNo;
    }

    /**
     * Sets the rebate schedule trans ref no.
     *
     * @param rebateScheduleTransRefNo the rebate schedule trans ref no
     */
    public void setRebateScheduleTransRefNo(final TextField rebateScheduleTransRefNo) {
        this.rebateScheduleTransRefNo = rebateScheduleTransRefNo;
    }

    /**
     * Gets the rebate schedule trans ref name.
     *
     * @return the rebate schedule trans ref name
     */
    public TextField getRebateScheduleTransRefName() {
        return rebateScheduleTransRefName;
    }

    /**
     * Sets the rebate schedule trans ref name.
     *
     * @param rebateScheduleTransRefName the rebate schedule trans ref name
     */
    public void setRebateScheduleTransRefName(final TextField rebateScheduleTransRefName) {
        this.rebateScheduleTransRefName = rebateScheduleTransRefName;
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
        return tabel;
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
    /** The common util. */
    private CommonUtil commonUtil = CommonUtil.getInstance();

    /**
     * The Constructor.
     *
     * @param rebateScheduleTransRefNo the rebate schedule trans ref no
     * @param rebateScheduleTransRefName the rebate schedule trans ref name
     */
    public ParentLookUp(final TextField rebateScheduleTransRefNo, final TextField rebateScheduleTransRefName) throws SystemException{
        super("Rebate Schedule Reference No");
        this.rebateScheduleTransRefNo = rebateScheduleTransRefNo;
        this.rebateScheduleTransRefName = rebateScheduleTransRefName;
        LOGGER.debug("Entering ParentLookUp");
        init();
        LOGGER.debug("End of ParentLookUp");
    }

    /**
     * Custom ErrorfulFieldGroup to Gets the binder.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {
        LOGGER.debug("Entering getBinder method");
        binder = new ErrorfulFieldGroup(new BeanItem<RebateScheduleMasterDTO>(new RebateScheduleMasterDTO()));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        LOGGER.debug("End of getBinder method");
        return binder;
    }

    /**
     * Inits Method load while calling constructor.
     */
    private void init() throws SystemException {
        LOGGER.debug("Entering init method");

        center();
        setClosable(true);
        setModal(true);
        setHeight("600px");
        setContent(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/parent-rebate-lookup.xml"),this));
        addToContent();
        configureFields();
        getBinder();
        LOGGER.debug("End of init method");

    }

    /**
     * Adds the to content.
     *
     * @return the panel
     */
    private void addToContent() {        
        LOGGER.debug("Entering addToContent method");
        ResponsiveUtils.addButtonListeners(tabel, prevColumn, nextColumn);
        addTabel();        
        LOGGER.debug("End of addToContent method");        
    }

    protected HorizontalLayout addToGrid() {
        final HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setStyleName("responsiveTabGrid");
        final CssLayout cssLayout = new CssLayout();
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
        hLayout.addComponent(cssLayout);
        return hLayout;

    }

    /**
     * Adds the tabel.
     *
     * @return the table
     */
    private void addTabel() {
        LOGGER.debug("Entering addTabel method");
        tabel.setPageLength(NumericConstants.TEN);
        tabel.setImmediate(true);
        tabel.setSelectable(true);
        tabel.setWidth(NumericConstants.NINETY_EIGHT, Unit.PERCENTAGE);
        tabel.setContainerDataSource(dummySearchResulbeans);
        tabel.addStyleName("filterbar");
        tabel.setFilterBarVisible(true);
        tabel.setFilterGenerator(new RsLookupFilterGenerator());
        tabel.setFilterDecorator(new ExtDemoFilterDecorator());
        tabel.setVisibleColumns(CommonUtils.RS_COL_COLUMNS);
        tabel.setColumnHeaders(CommonUtils.RS_COL_HEADERS);
        tabel.addItemClickListener(new ItemClickListener() {
            /**
             * Called by Table when a cell (and row) is painted.
             *
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                LOGGER.debug("Entering tabel itemClick method");
                final RebateScheduleMasterDTO companySearchDto = (RebateScheduleMasterDTO) searchResults.getItem(event.getItemId()).getBean();
                rebateScheduleTransRefNo.setValue(companySearchDto.rebateScheduleNo);
                rebateScheduleTransRefName.setValue(companySearchDto.rebateScheduleName);
                close();
                LOGGER.debug("End of tabel itemClick method");
            }
        });
        setDefaultTableColumns();
        LOGGER.debug("End of addTabel method");        
    }

    /**
     * Gets the rebate schedule status.
     *
     * @return the rebate schedule status
     */

    /**
     * Adds the buttons.
     *
     * @return the horizontal layout
     */

    /**
     * Configure fields.
     */
    protected void configureFields() throws SystemException{
        LOGGER.debug("Entering configureFields method");
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        setResizable(false);
        
        rebateScheduleId.setImmediate(true);
        rebateScheduleId.focus();
        
        commonUtil.loadComboBox(rebateScheduleStatus, CommonUtils.STATUS, false);
        commonUtil.loadComboBox(rebateScheduleType, CommonUtils.RS_TYPE, false);
        commonUtil.loadComboBox(rebateProgramType, CommonUtils.REBATE_PROGRAM_TYPE, false);
        

        search.addClickListener(new ClickListener() {
            /**
             * Method used to search button logic and its listener.
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                try {
                    List<Object> collapsedColumns = new ArrayList<Object>();

                    for (Object item : tabel.getVisibleColumns()) {
                        if (tabel.isColumnCollapsed(item)) {
                            collapsedColumns.add(item);
                        }
                    }
                    LOGGER.debug("Entering search buttonClick method");
                    binder.commit();
                    if (StringUtils.isBlank(binder.getField(Constants.RS_ID).getValue().toString()) && StringUtils.isBlank(binder.getField(Constants.RS_NO).getValue().toString())
                            && StringUtils.isBlank(binder.getField(Constants.RS_NAME).getValue().toString()) && binder.getField(Constants.RS_TYPE).getValue()==null
                            && binder.getField(Constants.REBATE_PROGRAM_TYPE).getValue()==null && binder.getField(Constants.RS_STATUS).getValue()==null ) {
                        
                        MessageBox.showPlain(Icon.ERROR, "Search Error", "Please enter Search Criteria", ButtonId.OK);
                        
                    }else{
                        
                        rsCriteria.setCustomDirty(true);
                        searchResults = new LazyBeanItemContainer(RebateScheduleMasterDTO.class, new RsDAO(binder), rsCriteria);

                        tabel.setContainerDataSource(searchResults);
                        if (searchResults.size() > Constants.ZERO) {
                            CommonUIUtils.successNotification("Search Completed");
                        } else {
                            CommonUIUtils.successNotification("No results found");
                        }
                        tabel.setVisibleColumns(CommonUtils.RS_COL_COLUMNS);
                        tabel.setColumnHeaders(CommonUtils.RS_COL_HEADERS);
                        rsCriteria.setCustomDirty(false);
                        for (Object propertyId : collapsedColumns) {
                            tabel.setColumnCollapsed(propertyId, true);
                        }
                        rsCriteria.setCustomDirty(true);
                    }
                    LOGGER.debug("End of search buttonClick method");
                } catch (CommitException e) {
                    LOGGER.error(e);
                }
            }
        });

        reset.addClickListener(new ClickListener() {
            /**
             * Method used to reset button logic and its listener.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering reset buttonClick method");
                List<Object> collapsedColumns = new ArrayList<Object>();
                    for (Object item : tabel.getVisibleColumns()) {
                        if (tabel.isColumnCollapsed(item)) {
                            collapsedColumns.add(item);
                        }
                    }
                binder.setItemDataSource(new BeanItem<RebateScheduleMasterDTO>(new RebateScheduleMasterDTO()));
                searchResults.removeAllItems();
                binder.getErrorDisplay().clearError();

                tabel.setContainerDataSource(dummySearchResulbeans);
                tabel.setVisibleColumns(CommonUtils.RS_COL_COLUMNS);
                tabel.setColumnHeaders(CommonUtils.RS_COL_HEADERS);

                binder.getErrorDisplay().clearError();
               
                    for (Object propertyId : collapsedColumns) {
                        tabel.setColumnCollapsed(propertyId, true);
                    }
                    
                LOGGER.debug("End of reset buttonClick method");
            }
        });
        setCloseShortcut(KeyCode.ESC);
        LOGGER.debug("End of configureFields method");

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
        tabel.setColumnCollapsingAllowed(true);
        int browserWidth = Page.getCurrent().getBrowserWindowWidth();
        try {

            if (browserWidth > NumericConstants.EIGHT_FIVE_ZERO) {
              
                for (Object propertyId : tabel.getVisibleColumns()) {
                    tabel.setColumnCollapsed(propertyId, false);
}
                for (Object propertyId : getCollapsibleColumnsDefault(tabel)) {
                    tabel.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < NumericConstants.EIGHT_FIVE_ZERO && browserWidth > NumericConstants.FOUR_FIVE_ZERO) {
              
                for (Object propertyId : tabel.getVisibleColumns()) {
                    tabel.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns850Px(tabel)) {
                    tabel.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < NumericConstants.FOUR_FIVE_ZERO) {
               
                for (Object propertyId : tabel.getVisibleColumns()) {
                    tabel.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns450Px(tabel)) {
                    tabel.setColumnCollapsed(propertyId, true);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private static String[] getCollapsibleColumnsDefault(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[1]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        list.remove(propertyIds[NumericConstants.FOUR]);
        list.remove(propertyIds[NumericConstants.FIVE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
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
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

}
