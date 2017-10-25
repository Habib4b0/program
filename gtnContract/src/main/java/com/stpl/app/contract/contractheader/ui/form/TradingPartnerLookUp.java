package com.stpl.app.contract.contractheader.ui.form;

import com.stpl.app.contract.contractheader.dto.CompanyResultsDTO;
import com.stpl.app.contract.contractheader.dto.TradingPartnerLookupGenerator;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.global.dto.CompanyCriteria;
import com.stpl.app.contract.global.dto.CompanyDAO;
import com.stpl.app.contract.global.dto.CompanySearchDto;
import com.stpl.app.contract.global.lazyload.CompanyTypeCriteria;
import com.stpl.app.contract.global.lazyload.CompanyTypeDAO;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.serviceUtils.ErrorCodeUtil;
import com.stpl.app.serviceUtils.ErrorCodes;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.ValidationUtils;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import elemental.events.KeyboardEvent.KeyCode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * Class for TradingPartnerLookUp Window.
 *
 * @author
 */
public final class TradingPartnerLookUp extends Window {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(TradingPartnerLookUp.class);
    /**
     * The error msg.
     */
    @UiField("errorLB")
    private ErrorLabel errorMsg;
    /**
     * The company no.
     */
    @UiField("companyNo")
    private  TextField companyNo;
    /**
     * The company id.
     */
    @UiField("companyId")
    private  TextField companyId;
    /**
     * The tp system id.
     */
    private TextField tpSystemId;
    /**
     * The company name.
     */
    @UiField("companyName")
    private  TextField companyName;
    /**
     * The tp name.
     */
    private TextField tpName;
    /**
     * The company status.
     */
    @UiField("companyStatus")
    private  ComboBox companyStatus;
    /**
     * The company type.
     */
    @UiField("companyType")
    private  ComboBox companyType;
    /**
     * The table.
     */
    @UiField("resultsTable")
    private ExtFilterTable table;
    @UiField("selectBtn")
    private Button btnSelect;
    
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
     /**
	 * The select button.
	 */
     @UiField("selectBtn")
    Button selectBtn;
     /**
	 * The close button.
	 */
    @UiField("closeBtn")
    Button closeBtn;
    
    /**
     * The binder.
     */
    private HelperDTO dto = new HelperDTO(Constants.SELECT_ONE);
    private ErrorfulFieldGroup binder;
    private final CommonUtils commonsUtil = new CommonUtils();
    CompanyCriteria searchCriteria = new CompanyCriteria();
    
    private String tpRefName = StringUtils.EMPTY;
    
    private final Label space = new Label(Constants.SPACE, ContentMode.HTML);

	/** The search resultbeans. */
	private LazyBeanItemContainer searchResultbeans;
        
        /** The dummy search resultbeans. */
	private final BeanItemContainer<CompanyResultsDTO> dummySearchResultbeans = new BeanItemContainer<CompanyResultsDTO>(CompanyResultsDTO.class);
   
    /** The common util. */
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
     * Gets the company type.
     *
     * @return the company type
     */
    public ComboBox getCompanyType() {
        return companyType;
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
	public BeanItemContainer<CompanyResultsDTO> getDummySearchResultbeans() {
		return dummySearchResultbeans;
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
     * Parameterized constructor with the parameters TradingPartnerSystem Id and
     * Name.
     *
     * @param tpSystemId - Textfield
     * @param tpName - Textfield
     */
    public TradingPartnerLookUp(final TextField tpSystemId, final TextField tpName) throws SystemException {
        super("Trading Partner Look Up");
        LOGGER.debug("Entering SearchResults constructor ");
        this.tpSystemId = tpSystemId;
        this.tpName = tpName;
        setContent(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-header/company-tp-lookup.xml"),this));
        init();
        LOGGER.debug(" SearchResults constructor ends");
    }

    /**
     * Adds the entire form to the UI and make initial configuration.
     */
    private void init() throws SystemException {
        LOGGER.debug("Entering init method ");
        center();
        setClosable(true);
        setModal(true);
        setHeight("600px");        
        configureFields();
        configureTable();
        getBinder();
        LOGGER.debug(" init method ends");
    }

    /**
     * processes all member fields whose type extends Field and that can be
     * mapped to a property id.
     *
     * @return ErrorfulFieldGroup.
     */
    private ErrorfulFieldGroup getBinder() {
        LOGGER.debug("Entering getBinder method ");
        binder = new ErrorfulFieldGroup(new BeanItem<CompanySearchDto>(new CompanySearchDto()));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        LOGGER.debug(" getBinder method ends");
        return binder;
    }

  
   
    

    /**
     * Configures the table and returns it.
     *
     * @return Table
     */
    private void configureTable() {
        LOGGER.debug("Entering addToTable method ");
        table.setCaption("Results");
		table.setContainerDataSource(dummySearchResultbeans);
        table.setVisibleColumns(UIUtils.TRADING_LOOKUP_COLUMNS);
        table.setColumnHeaders(UIUtils.TRADING_LOOKUP_HEADERS);
        table.setPageLength(7);
        table.addStyleName("filterbar");
        table.setFilterBarVisible(true);
        table.setFilterGenerator(new TradingPartnerLookupGenerator());
        table.setFilterDecorator(new ExtDemoFilterDecorator());
        table.setImmediate(true);
        table.setSelectable(true);
        table.setWidth(98, UNITS_PERCENTAGE);
        table.addItemClickListener(new ItemClickListener() {
            /**
             * Sets the value for tradingPartnerSystem Id and Name on button
             * click.
             *
             * @param event - ItemClickEvent
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {              
                LOGGER.debug("Entering table itemclick() ");
                try 
                {
                    final CompanyResultsDTO companySearchDto = (CompanyResultsDTO) searchResultbeans.getItem(event.getItemId()).getBean();
                tpSystemId.setValue(String.valueOf(companySearchDto.getCompanySystemId()));
                tpRefName = companySearchDto.getCompanyName();
                }catch (Exception ex) {
                            LOGGER.error(ex);
                            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {    
                                /**           
                                 * The method is triggered when a button of the message box is    
                                 * pressed .       
                                 *               
                                 * @param buttonId The buttonId of the pressed button.         
                                 */             
                                @SuppressWarnings("PMD")  
                                public void buttonClicked(final ButtonId buttonId) {       
                                }       
                            }, ButtonId.OK);  
                            msg.getButton(ButtonId.OK).focus();
                        }
                        btnSelect.setEnabled(true);
                LOGGER.debug(" table itemclick method ends ");

            }
        });
         setDefaultTableColumns();
        LOGGER.debug(" addToTable method ends and returns table");       
    }

    /**
     * Configures the SearchButton and returns it.
     *
     * @return the button
     */
    private Button searchButton() {
        
        LOGGER.debug("Entering SearchButton()");
        btnSearch.setErrorHandler(new ErrorHandler() {
            /**
             * This method is called whenever an error occurs.
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
             * Sets the Data Source for the fable on button click.
             *
             * @param event - ClickEvent
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("Enters btnSearch buttonClick method");
                    List<Object> collapsedColumns = new ArrayList<Object>();
                    for (Object item : table.getVisibleColumns()) {
                        if (table.isColumnCollapsed(item)) {
                            collapsedColumns.add(item);
                        }
                    }
                    binder.commit();
                   if (StringUtils.isBlank(binder.getField(Constants.COMPANY_ID).getValue().toString()) && StringUtils.isBlank(binder.getField(Constants.COMPANY_NO).getValue().toString())
                            && StringUtils.isBlank(binder.getField(Constants.COMPANY_NAME).getValue().toString()) 
                            && (binder.getField(Constants.COMPANY_STATUS).getValue() == null || ((HelperDTO)binder.getField(Constants.COMPANY_STATUS).getValue()).getId()==0)
                            && (binder.getField(Constants.COMPANY_TYPE).getValue() == null || ((HelperDTO)binder.getField(Constants.COMPANY_TYPE).getValue()).getId()==0)) {
                        
                        MessageBox.showPlain(Icon.ERROR, "Search Error", "Please enter Search Criteria", ButtonId.OK);
                        
                    }else{
                      searchCriteria.setCustomDirty(true);
					searchResultbeans = new LazyBeanItemContainer(CompanyResultsDTO.class, new CompanyDAO(binder), searchCriteria);
					table.setContainerDataSource(searchResultbeans);
					if (searchResultbeans.size() > (Constants.ZERO)) {
                        CommonUIUtils.successNotification("Search Completed");
                    } else {
                        CommonUIUtils.successNotification("No results found");
                    }
                    table.setVisibleColumns(UIUtils.TRADING_LOOKUP_COLUMNS);
                    table.setColumnHeaders(UIUtils.TRADING_LOOKUP_HEADERS);
                    
                    if (StringUtils.isBlank(binder.getField(Constants.COMPANY_ID).getValue().toString()) && StringUtils.isBlank(binder.getField(Constants.COMPANY_NO).getValue().toString())
                            && StringUtils.isBlank(binder.getField(Constants.COMPANY_NAME).getValue().toString()) && binder.getField(Constants.COMPANY_TYPE).getValue()==null
                            && (binder.getField(Constants.COMPANY_STATUS).getValue() == null || ((HelperDTO)binder.getField(Constants.COMPANY_STATUS).getValue()).getId()==0)) {
                        
                        MessageBox.showPlain(Icon.ERROR, "Search Error", "Please enter Search Criteria", ButtonId.OK);
                        
                    }else{
                        
                        searchCriteria.setCustomDirty(true);
                        searchResultbeans = new LazyBeanItemContainer(CompanyResultsDTO.class, new CompanyDAO(binder), searchCriteria);
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
                    searchCriteria.setCustomDirty(true);
                    }
                    LOGGER.debug("End of btnSearch buttonClick method");
                } catch (CommitException ex) {
                    LOGGER.error(ex);
                }
            }
        });
        LOGGER.debug(" SearchButton method ends and returns btnSearch");
        return btnSearch;
    }

    /**
     * Configures the ResetButtons and returns the button.
     *
     * @return the button
     */
    private Button resetButton() {
        
        LOGGER.debug("Entering resetButton()");
        btnReset.setErrorHandler(new ErrorHandler() {
            /**
             * This method is called whenever an error occurs.
             *
             * @param event - ErrorEvent
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        btnReset.addClickListener(new ClickListener() {
            /**
             * Resets the Table on reset button click
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
                        if (buttonId.name().equals(Constants.YES)) {
                            LOGGER.debug("Entering btnReset buttonClick() ");
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
                            LOGGER.debug("End of btnReset buttonClick() ");
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

          
            }
        });
        LOGGER.debug(" resetButton method ends and returns btnReset");
        return btnReset;
    }

    /**
     * Configures the fields in that page.
     */
    private void configureFields() throws SystemException {
        LOGGER.debug("Entering configureFields method ");
        try{   
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        
        searchButton();
        resetButton();
        
        setResizable(false);
        companyName.addValidator(new RegexpValidator(ValidationUtils.Search_specialCharacter, "Company Name" + " " + ValidationUtils.Search_specialCharacter_Message));
        companyName.addValidator(new StringLengthValidator("Company Name Should be less than 100 characters", 0, 100, true));
        companyName.setImmediate(true);
        companyName.setValidationVisible(true);

        companyNo.addValidator(new RegexpValidator(ValidationUtils.Search_specialCharacter, "Company No " + " " + ValidationUtils.Search_specialCharacter_Message));
        companyNo.addValidator(new StringLengthValidator("Company No Should be less than 100 characters", 0, 100, true));
        companyNo.setImmediate(true);
        companyNo.setValidationVisible(true);

        companyId.addValidator(new RegexpValidator(ValidationUtils.Search_specialCharacter, "Company Id " + " " + ValidationUtils.Search_specialCharacter_Message));
        companyId.addValidator(new StringLengthValidator("Company ID Should be less than 100 characters", 0, 100, true));
        companyId.setImmediate(true);
        companyId.setValidationVisible(true);

        commonUtil.loadComboBox(companyStatus, UIUtils.STATUS, false);
       
        
        
        companyType.setImmediate(true);
        companyType.setNullSelectionAllowed(true);
        companyType.setNullSelectionItemId(dto);
        companyType.setItemCaptionPropertyId(Constants.DESCRIPTION);
        companyType.setInputPrompt(Constants.SELECT_ONE);
        companyType.markAsDirty();
        LazyContainer manufacturerContainer = new LazyContainer(HelperDTO.class, new CompanyTypeDAO(), new CompanyTypeCriteria());
        manufacturerContainer.setMinFilterLength(0);
     
     
        companyType.setContainerDataSource(manufacturerContainer);
           companyType.select(dto);
        companyType.setPageLength(7);
        companyType.setValue(dto);        
        setCloseShortcut(KeyCode.ESC);
        companyId.focus();
        }catch(Exception e){
            LOGGER.error(e);
        }
        LOGGER.debug("configureFields method ends ");
    }

    /**
     * Gets the search searchResultbeans.
     *
     * @return the search searchResultbeans
     */
    public LazyBeanItemContainer getSearchResultbeans() {
        return searchResultbeans;
    }
    
    /**
     * Sets the search searchResultbeans.
     *
     */
    public void setSearchResultbeans(final LazyBeanItemContainer searchResultbeans) {
        this.searchResultbeans = searchResultbeans;
    }        
  private void setDefaultTableColumns() {
        table.setColumnCollapsingAllowed(true);
        int browserWidth = Page.getCurrent().getBrowserWindowWidth();
        try {
            
            if (browserWidth > 850 ) {
                
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
}
                for (Object propertyId : getCollapsibleColumnsDefault(table)) {
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
            LOGGER.error(ex);
        }
    }
        
        private static String[] getCollapsibleColumnsDefault(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
}
        private static String[] getCollapsibleColumns450Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
}

    private static String[] getCollapsibleColumns850Px(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }
    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) {
        tpName.setReadOnly(false); 
        tpName.setValue(StringUtils.EMPTY);
        tpName.setReadOnly(true); 
        close();
    } 
    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event) {
        tpName.setReadOnly(false);     
        tpName.setValue(tpRefName);
        tpName.setReadOnly(true);     
        close();
    } 
    public void searchButtonLogic() {
        try {
            LOGGER.debug("Enters btnSearch buttonClick method");
            List<Object> collapsedColumns = new ArrayList<Object>();
            for (Object item : table.getVisibleColumns()) {
                if (table.isColumnCollapsed(item)) {
                    collapsedColumns.add(item);
}
            }
            binder.commit();
            if (StringUtils.isBlank(binder.getField(Constants.COMPANY_ID).getValue().toString()) && StringUtils.isBlank(binder.getField(Constants.COMPANY_NO).getValue().toString())
                    && StringUtils.isBlank(binder.getField(Constants.COMPANY_NAME).getValue().toString())
                    && (binder.getField(Constants.COMPANY_STATUS).getValue() == null || ((HelperDTO) binder.getField(Constants.COMPANY_STATUS).getValue()).getId() == 0)
                    && (binder.getField(Constants.COMPANY_TYPE).getValue() == null || ((HelperDTO) binder.getField(Constants.COMPANY_TYPE).getValue()).getId() == 0)) {

                MessageBox.showPlain(Icon.ERROR, "Search Error", "Please enter Search Criteria", ButtonId.OK);

            } else {
                searchCriteria.setCustomDirty(true);
                searchResultbeans = new LazyBeanItemContainer(CompanyResultsDTO.class, new CompanyDAO(binder), searchCriteria);
                table.setContainerDataSource(searchResultbeans);
                if (searchResultbeans.size() > (Constants.ZERO)) {
                    CommonUIUtils.successNotification("Search Completed");
                } else {
                    CommonUIUtils.successNotification("No results found");
                }
                table.setVisibleColumns(UIUtils.TRADING_LOOKUP_COLUMNS);
                table.setColumnHeaders(UIUtils.TRADING_LOOKUP_HEADERS);

                if (StringUtils.isBlank(binder.getField(Constants.COMPANY_ID).getValue().toString()) && StringUtils.isBlank(binder.getField(Constants.COMPANY_NO).getValue().toString())
                        && StringUtils.isBlank(binder.getField(Constants.COMPANY_NAME).getValue().toString()) && binder.getField(Constants.COMPANY_TYPE).getValue() == null
                        && (binder.getField(Constants.COMPANY_STATUS).getValue() == null || ((HelperDTO) binder.getField(Constants.COMPANY_STATUS).getValue()).getId() == 0)) {

                    MessageBox.showPlain(Icon.ERROR, "Search Error", "Please enter Search Criteria", ButtonId.OK);

                } else {

                    searchCriteria.setCustomDirty(true);
                    searchResultbeans = new LazyBeanItemContainer(CompanyResultsDTO.class, new CompanyDAO(binder), searchCriteria);
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
                searchCriteria.setCustomDirty(true);
            }
            LOGGER.debug("End of btnSearch buttonClick method");
        } catch (CommitException ex) {
            LOGGER.error(ex);
        }
    }
}
