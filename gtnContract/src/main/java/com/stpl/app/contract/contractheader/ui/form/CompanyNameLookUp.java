package com.stpl.app.contract.contractheader.ui.form;

import com.stpl.app.contract.contractheader.dto.TradingPartnerLookupGenerator;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.global.dto.CompanyCriteria;
import com.stpl.app.contract.global.dto.CompanyNameDAO;
import com.stpl.app.contract.global.dto.CompanySearchDto;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.app.contract.util.Constants;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.ValidationUtils;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import elemental.events.KeyboardEvent;
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
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * Class for CompanyNameLookUp Window.
 *
 * @author
 */
public final class CompanyNameLookUp extends Window {

    /**
     * The logger.
     */
    private final static Logger LOGGER = Logger.getLogger(CompanyNameLookUp.class);
    /**
     * The error msg.
     */
    @UiField("errorLB")
    private ErrorLabel errorMsg;
    /**
     * The company no.
     */
    @UiField("companyNo")
    private TextField companyNo;
    /**
     * The company id.
     */
    @UiField("companyId")
    private TextField companyId;
    /**
     * The tp system id.
     */
    private TextField tpSystemId;
    /**
     * The company name.
     */
    @UiField("companyName")
    private TextField companyName;
    /**
     * The tp name.
     */
    private TextField tpName;
    /**
     * The company status.
     */
    @UiField("companyStatus")
    private ComboBox companyStatus;
    /**
     * The table.
     */
    @UiField("resultsTable")
    private ExtFilterTable table;
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;
    /**
     * The search resultbeans.
     */
    private LazyBeanItemContainer searchResultbeans;

    private String compName = StringUtils.EMPTY;

    private String compId = StringUtils.EMPTY;

    /**
     * The dummy search resultbeans.
     */
    private final BeanItemContainer<CompanySearchDto> dummySearchResultbeans = new BeanItemContainer<CompanySearchDto>(CompanySearchDto.class);

    CompanyCriteria searchCriteria = new CompanyCriteria();

    @UiField("companyIdLabel")
    private Label companyIdLabel;

    @UiField("companyNoLabel")
    private Label companyNoLabel;

    @UiField("companyNameLabel")
    private Label companyNameLabel;

    @UiField("companyStatusLabel")
    private Label companyStatusLabel;

    @UiField("companyTypeLabel")
    private Label companyTypeLabel;

    @UiField("searchBtn")
    private Button btnSearch;

    @UiField("resetBtn")
    private Button btnReset;

    @UiField("closeBtn")
    private Button closeBtn;

    @UiField("selectBtn")
    private Button selectBtn;

    /**
     * The company type.
     */
    @UiField("companyType")
    private ComboBox companyType;
    /**
     * The common util.
     */
    private com.stpl.app.contract.abstractsearch.util.CommonUtils commonUtil = com.stpl.app.contract.abstractsearch.util.CommonUtils.getInstance();

    /**
     * Gets the tp system id.
     *
     * @return the tp system id
     */
    public TextField getTpSystemId() {
        return tpSystemId;
    }

    /**
     * Sets the tp system id.
     *
     * @param tpSystemId the tp system id
     */
    public void setTpSystemId(final TextField tpSystemId) {
        this.tpSystemId = tpSystemId;
    }

    /**
     * Gets the tp name.
     *
     * @return the tp name
     */
    public TextField getTpName() {
        return tpName;
    }

    /**
     * Sets the tp name.
     *
     * @param tpName the tp name
     */
    public void setTpName(final TextField tpName) {
        this.tpName = tpName;
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
     * Gets the company no.
     *
     * @return the company no
     */
    public TextField getCompanyNo() {
        return companyNo;
    }

    /**
     * Gets the company id.
     *
     * @return the company id
     */
    public TextField getCompanyId() {
        return companyId;
    }

    /**
     * Gets the company name.
     *
     * @return the company name
     */
    public TextField getCompanyName() {
        return companyName;
    }

    /**
     * Gets the company status.
     *
     * @return the company status
     */
    public ComboBox getCompanyStatus() {
        return companyStatus;
    }

    /**
     * Gets the table.
     *
     * @return the table
     */
    public ExtFilterTable getTable() {
        return table;
    }

    /**
     * Gets the search resultbeans.
     *
     * @return the search resultbeans
     */
    public LazyBeanItemContainer getSearchResultbeans() {
        return searchResultbeans;
    }

    /**
     * Sets the search resultbeans.
     *
     */
    public void setSearchResultbeans(final LazyBeanItemContainer searchResultbeans) {
        this.searchResultbeans = searchResultbeans;
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
     * Gets the companyType.
     *
     * @return the companyType
     */
    public ComboBox getCompanyType() {
        return companyType;
    }

    /**
     * Parameterized Constructor.
     *
     * @param tpSystemId - TextField
     * @param tpName - TextField
     */
    public CompanyNameLookUp(final TextField tpSystemId, final TextField tpName) {
        super("Company Name Look Up");
        LOGGER.debug("Entering CompanyNameLookUp constructor ");
        this.tpSystemId = tpSystemId;
        this.tpName = tpName;
        setContent(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-header/company-tp-lookup.xml"), this));
        init();
        LOGGER.debug(" CompanyNameLookUp constructor ends");
    }

    /**
     * method adds the content and configures the field to the UI.
     */
    public void init() {
        LOGGER.debug("entering init method");
        center();
        setClosable(true);
        setModal(true);
        setHeight("600px");
        setWidth("1000px");
        configureFields();
        configureTable();
        getBinder();
        LOGGER.debug(" init method ended");
    }

    /**
     * Binds the data holded by the field to DTO.
     *
     * @return ErrorfulFieldGroup
     */
    private ErrorfulFieldGroup getBinder() {
        LOGGER.debug("entering getBinder method");
        binder = new ErrorfulFieldGroup(new BeanItem<CompanySearchDto>(new CompanySearchDto()));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        LOGGER.debug("getBinder method ended");
        return binder;
    }

    /**
     * Sets the data source,configures and returns the table object.
     *
     * @return Table
     */
    private void configureTable() {
        LOGGER.debug("Entering addToTable method ");
        table.setCaption("Results");
        table.setContainerDataSource(dummySearchResultbeans);
        table.setVisibleColumns(UIUtils.TRADING_LOOKUP_COLUMNS);
        table.setColumnHeaders(UIUtils.TRADING_LOOKUP_HEADERS);
        table.setPageLength(NumericConstants.SEVEN);
        table.addStyleName("filterbar");
        table.setFilterBarVisible(true);
        table.setFilterGenerator(new TradingPartnerLookupGenerator());
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setImmediate(true);
        table.setSelectable(true);
        table.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
        table.addStyleName("table-header-normal");
        table.addItemClickListener(new ItemClickListener() {
            /**
             * Invoked when the item is clicked.
             *
             * @param event - ItemClickEvent
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {

                LOGGER.debug("table click listener is called");
                final CompanySearchDto companySearchDto = (CompanySearchDto) searchResultbeans.getItem(event.getItemId()).getBean();
                compId = String.valueOf(companySearchDto.getCompanySystemId());
                compName = companySearchDto.getCompanyName();
                tpSystemId.setValue(String.valueOf(companySearchDto.getCompanySystemId()));
                LOGGER.debug("table click listener ended");
            }
        });
        setDefaultTableColumns();
        LOGGER.debug(" addToTable method ends and returns table");
    }

    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event) {

        tpName.setReadOnly(false);
        tpName.setValue(compName);
        tpName.setReadOnly(true);
        close();

    }
    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) {
        tpName.setReadOnly(false);
        tpName.setValue(StringUtils.EMPTY);
        tpName.setReadOnly(true);
        close();
    }
    /**
     * Configures the search button.
     *
     * @return Button
     */
    private Button searchButton() {
        LOGGER.debug("Entering SearchButton method ");
        btnSearch.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             *
             * @param event - ErrorEvent
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        btnSearch.addClickListener(new ClickListener() {
            /**
             * Invoked when the search button is clicked.
             *
             * @param event - ClickEvent
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("btnSearch click listener is called");
                    List<Object> collapsedColumns = new ArrayList<Object>();
                    for (Object item : table.getVisibleColumns()) {
                        if (table.isColumnCollapsed(item)) {
                            collapsedColumns.add(item);
                        }
                    }
                    binder.commit();
                    if (StringUtils.isBlank(binder.getField(Constants.COMPANY_ID).getValue().toString()) && StringUtils.isBlank(binder.getField(Constants.COMPANY_NO).getValue().toString())
                            && StringUtils.isBlank(binder.getField(Constants.COMPANY_NAME).getValue().toString())
                            && (binder.getField(Constants.COMPANY_STATUS).getValue() == null || ((HelperDTO) binder.getField(Constants.COMPANY_STATUS).getValue()).getId() == Constants.ZERO)
                            && (binder.getField(Constants.COMPANY_TYPE).getValue() == null || ((HelperDTO) binder.getField(Constants.COMPANY_TYPE).getValue()).getId() == Constants.ZERO)) {
                        MessageBox.showPlain(Icon.ERROR, "Search Error", "Please enter Search Criteria", ButtonId.OK);

                    } else {
                        searchCriteria.setCustomDirty(true);
                        searchResultbeans = new LazyBeanItemContainer(CompanySearchDto.class, new CompanyNameDAO(binder), searchCriteria);
                        table.setContainerDataSource(searchResultbeans);
                        if (searchResultbeans.size() > (Constants.ZERO)) {
                            CommonUIUtils.successNotification("Search Completed");
                        } else {
                            CommonUIUtils.successNotification("No results found");
                        }
                        table.setVisibleColumns(UIUtils.TRADING_LOOKUP_COLUMNS);
                        table.setColumnHeaders(UIUtils.TRADING_LOOKUP_HEADERS);

                        searchCriteria.setCustomDirty(false);
                        for (Object propertyId : collapsedColumns) {
                            table.setColumnCollapsed(propertyId, true);
                        }
                        searchCriteria.setCustomDirty(true);
                    }
                    LOGGER.debug("btnSearch click listener ended");
                } catch (CommitException e) {
                    LOGGER.error(e);
                }
            }
        });
        LOGGER.debug(" SearchButton method ends");
        return btnSearch;
    }

    /**
     * Configures the reset button.
     *
     * @return Button
     */
    private Button resetButton() {
        LOGGER.debug("Entering resetButton method ");
        btnReset.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             *
             * @param event ErrorEvent
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        btnReset.addClickListener(new ClickListener() {
            /**
             * Invoked when the reset button is clicked.
             *
             * @param event - ClickEvent
             */
            public void buttonClick(final ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the page to default/previous values " + " ?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            LOGGER.debug("Enters btnReset click listener ");
                            List<Object> collapsedColumns = new ArrayList<Object>();
                            for (Object item : table.getVisibleColumns()) {
                                if (table.isColumnCollapsed(item)) {

                                    collapsedColumns.add(item);
                                }
                            }
                            binder.setItemDataSource(new BeanItem<CompanySearchDto>(new CompanySearchDto()));
                            binder.getErrorDisplay().clearError();
                            table.setContainerDataSource(dummySearchResultbeans);

                            table.setVisibleColumns(UIUtils.TRADING_LOOKUP_COLUMNS);
                            table.setColumnHeaders(UIUtils.TRADING_LOOKUP_HEADERS);

                            binder.getErrorDisplay().clearError();

                            searchCriteria.setCustomDirty(false);
                            for (Object propertyId : collapsedColumns) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                            searchCriteria.setCustomDirty(true);
                            LOGGER.debug(" btnReset click listener ends");
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }
        });
        LOGGER.debug(" resetButton method ends");
        return btnReset;
    }

    /**
     * Configures the fields in the page.
     */
    public void configureFields() {
        LOGGER.debug("Enters configureFields method ");
        try {
            addStyleName("bootstrap");
            addStyleName("bootstrap-bb");

            searchButton();
            resetButton();
            setResizable(false);
            companyName.addValidator(new RegexpValidator(ValidationUtils.Search_specialCharacter, "Company Name" + " " + ValidationUtils.Search_specialCharacter_Message));
            companyName.addValidator(new StringLengthValidator("Company Name Should be less than 100 characters", Constants.ZERO, NumericConstants.HUNDRED, true));
            companyName.setImmediate(true);
            companyName.setValidationVisible(true);

            companyNo.addValidator(new RegexpValidator(ValidationUtils.Search_specialCharacter, "Company No " + " " + ValidationUtils.Search_specialCharacter_Message));
            companyNo.addValidator(new StringLengthValidator("Company No Should be less than 100 characters", Constants.ZERO, NumericConstants.HUNDRED, true));
            companyNo.setImmediate(true);
            companyNo.setValidationVisible(true);

            companyId.addValidator(new RegexpValidator(ValidationUtils.Search_specialCharacter, "Company Id " + " " + ValidationUtils.Search_specialCharacter_Message));
            companyId.addValidator(new StringLengthValidator("Company ID Should be less than 100 characters", Constants.ZERO, NumericConstants.HUNDRED, true));
            companyId.setImmediate(true);
            companyId.setValidationVisible(true);

            commonUtil.loadComboBox(companyStatus, UIUtils.STATUS, false);
            commonUtil.loadComboBox(companyType, UIUtils.COMP_TYPE, false);

            setCloseShortcut(KeyboardEvent.KeyCode.ESC);
            companyId.focus();
            closeBtn.addClickListener(new ClickListener() {
                /**
                 * Invoked when the close button is clicked.
                 *
                 * @param event - ClickEvent
                 */
                public void buttonClick(final ClickEvent event) {
                    LOGGER.debug("Close Button click listener is called");
                    close();
                    LOGGER.debug("Close Button click listener ended");
                }
            });
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug(" configureFields method ends here");
    }

    /**
     * Gets the dummy search resultbeans.
     *
     * @return the dummy search resultbeans
     */
    public BeanItemContainer<CompanySearchDto> getDummySearchResultbeans() {
        return dummySearchResultbeans;
    }

    private void setDefaultTableColumns() {
        table.setColumnCollapsingAllowed(true);
        int browserWidth = Page.getCurrent().getBrowserWindowWidth();
        try {

            if (browserWidth > NumericConstants.EIGHT_FIVE_ZERO) {

                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumnsDefault(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < NumericConstants.EIGHT_FIVE_ZERO && browserWidth > NumericConstants.FOUR_FIVE_ZERO) {

                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns850Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < NumericConstants.FOUR_FIVE_ZERO) {

                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns450Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private static String[] getCollapsibleColumnsDefault(Table table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[Constants.ZERO]);
        list.remove(propertyIds[NumericConstants.ONE]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        list.remove(propertyIds[NumericConstants.FOUR]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumnsDefault(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[Constants.ZERO]);
        list.remove(propertyIds[NumericConstants.ONE]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        list.remove(propertyIds[NumericConstants.FOUR]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumns450Px(Table table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[Constants.ZERO]);
        list.remove(propertyIds[NumericConstants.ONE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumns450Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[Constants.ZERO]);
        list.remove(propertyIds[NumericConstants.ONE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumns850Px(Table table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[Constants.ZERO]);
        list.remove(propertyIds[NumericConstants.ONE]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumns850Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[Constants.ZERO]);
        list.remove(propertyIds[NumericConstants.ONE]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    public String getCompName() {
        return compName;
    }

    public String getCompId() {
        return compId;
    }

}
