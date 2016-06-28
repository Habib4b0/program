/**
 *
 */
package com.stpl.app.contract.dashboard.ui.form;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.util.UIUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.contract.dashboard.dto.CfpDetailsFilterGenerator;
import com.stpl.app.contract.dashboard.logic.AvailableCompanyTableLogic;
import com.stpl.app.contract.dashboard.ui.lazyload.LazyLoadCriteria;
import com.stpl.app.contract.dashboard.ui.lazyload.SelectedCompaniesContainer;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.global.dto.CFPCompanyDTO;
import com.stpl.app.contract.global.dto.CompanyMasterDTO;
import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;

import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.app.contract.util.CHFunctionNameUtils;
import com.stpl.app.contract.util.ResponsiveUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.ifs.util.UISecurityUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

/**
 * @author sibi
 *
 */
public class CompanyAdditionTab extends CustomComponent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final Logger LOGGER = Logger.getLogger(CompanyAdditionTab.class);

    @UiField("cssLayout")
    private CssLayout cssLayout;

    @UiField("hlayout")
    private HorizontalLayout hLayout;

    @UiField("hlayout2")
    private HorizontalLayout hLayout2;

    @UiField("searchFields")
    private ComboBox searchFields;

    @UiField("searchValue")
    private TextField searchValue;

    @UiField("searchDdlb")
    private ComboBox searchDdlb;

    @UiField("searchBtn")
    private Button companySearchBtn;

    @UiField("availableTableLayout")
    private VerticalLayout availableTableLayout;

    @UiField("selectedTableLayout")
    private VerticalLayout selectedTableLayout;

    @UiField("btnMove")
    private Button btnMove;

    @UiField("btnRemove")
    private Button btnRemove;

    @UiField("btnMoveAll")
    private Button btnMoveAll;

    @UiField("btnRemoveAll")
    private Button btnRemoveAll;

    @UiField("selectedViewTable")
    private Table selectedViewTable;

    BeanItemContainer<CompanyMasterDTO> availableCompanyResultBean;

    BeanItemContainer<CompanyMasterDTO> selectedCompanyResultBean;

    BeanItemContainer<CFPCompanyDTO> cfpResultsBean;

    BeanItemContainer<CFPCompanyDTO> saveContainer;

    final Map<String, String> companyMap;

    final CustomFieldGroup binder;

	private LazyBeanItemContainer selectedLazyContainer;
    private final CFPSearchLogic cfpLogic;

    private String tempSearchField = StringUtils.EMPTY;
    private String tempSearchValue = StringUtils.EMPTY;
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    final boolean isEditable;
    SessionDTO sessionDTO;
    final StplSecurity stplSecurity = new StplSecurity();
    String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
    private final Map<Integer, Boolean> reloadVerticalLayoutTabThreeMap = new HashMap<Integer, Boolean>();
    ContractHeaderLogic headerLogic = new ContractHeaderLogic();
    /**
     * Container to Clear the table.
     */
    private final BeanItemContainer<CompanyMasterDTO> availableTableContainer = new BeanItemContainer<CompanyMasterDTO>(CompanyMasterDTO.class);
    private final BeanItemContainer<CFPCompanyDTO> selectedTableContainer = new BeanItemContainer<CFPCompanyDTO>(CFPCompanyDTO.class);
    final Map<String, AppPermission> fieldContract = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + "," + "Company Addition", false);
    List<Object> resultList = headerLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Company Addition");
    Object[] objAvail = ContractUtils.AVAILABLE_COMPANY_COL;
    TableResultCustom tableResultCustomAvail = commonSecurityLogic.getTableColumnsPermission(resultList, objAvail, fieldContract, Constants.EDIT);
    Object[] objSelect = ContractUtils.SELECTED_COMPANY_COL;
    TableResultCustom tableResultCustomSelect = commonSecurityLogic.getTableColumnsPermission(resultList, objSelect, fieldContract, Constants.EDIT);
    AvailableCompanyTableLogic avlTblLogic = new AvailableCompanyTableLogic(true);
    ExtPagedTable availableTable = new ExtPagedTable(avlTblLogic);
    AvailableCompanyTableLogic selTblLogic = new AvailableCompanyTableLogic(false);
    ExtPagedTable selectedTable = new ExtPagedTable(selTblLogic);

    /**
     * Company Addition tab in Contract Dashboard.
     *
     * @param availableBean
     * @param selectedBean
     * @param cfpResultsBean
     * @param saveContainer
     * @param companyMap
     * @param contractBinder
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public CompanyAdditionTab(final BeanItemContainer<CompanyMasterDTO> availableBean, final BeanItemContainer<CompanyMasterDTO> selectedBean, final BeanItemContainer<CFPCompanyDTO> cfpResultsBean,
            final BeanItemContainer<CFPCompanyDTO> saveContainer, final Map<String, String> companyMap, final CustomFieldGroup contractBinder, final boolean isEditable, final SessionDTO sessionDTO) throws SystemException, PortalException, Exception {
        super();
        LOGGER.info("Entering Company Addition Tab");
        this.availableCompanyResultBean = availableBean;
        this.selectedCompanyResultBean = selectedBean;
        this.cfpResultsBean = cfpResultsBean;
        this.saveContainer = saveContainer;
        this.companyMap = companyMap;
        this.binder = contractBinder;
        this.isEditable = isEditable;
        this.sessionDTO = sessionDTO;
        cfpLogic = new CFPSearchLogic(this.sessionDTO);
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/company-addition.xml"), this));
        init();
        LOGGER.info("End of Company Addition Tab");
    }

    /**
     * Init method to configure the Company Addition Tab.
     *
     * @throws Exception
     * @throws SystemException
     * @throws PortalException
     */
    private void init() throws PortalException, SystemException, Exception {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final Map<String, AppPermission> contractDashboard = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + "," + "Company Addition", false);
        addResponsiveGrid(contractDashboard);
        if (isEditable) {
            configureFields();
            configureTables();
            configureButton();
        } else {
            configureFieldsOnView();
            configureTableForView();
            selectedViewTable.setReadOnly(true);
        }

    }

    private void configureFieldsOnView() {
        hLayout.setVisible(false);
        hLayout2.setVisible(false);
        availableTable.setVisible(false);
        selectedTable.setVisible(false);
        btnMove.setVisible(false);
        btnMoveAll.setVisible(false);
        btnRemove.setVisible(false);
        btnRemoveAll.setVisible(false);
    }

    /**
     * Method used configure the tables.
     *
     * @return the tables
     */
    public void configureTables() {
        addAvailableTable();
        addSelectedTable();
    }

    /**
     * Method used to Configure the buttons in the Company Addition Tab.
     *
     * @throws Exception
     * @throws SystemException
     * @throws PortalException
     */
    public void configureButton() throws PortalException, SystemException, Exception {

        add();
        remove();
        addAll();
        removeAll();

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final Map<String, AppPermission> funContractHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + "," + "Company Addition");
        if (!(funContractHM.get(CHFunctionNameUtils.CompanySearch) != null) || !((AppPermission) funContractHM.get(CHFunctionNameUtils.CompanySearch)).isFunctionFlag()) {
            companySearchBtn.setVisible(false);
        } else {
            addFindBtn();
        }
    }

    /**
     * Method used for Configure fields.
     */
    public void configureFields() {
        LOGGER.info("Entering configureFields method");
        searchValue.setImmediate(true);
        searchValue.setValidationVisible(true);
        searchValue.addValidator(new StringLengthValidator("Value should be less than 38 characters", 0, 38, true));
        searchValue.addValidator(new RegexpValidator("([0-9|a-z|A-Z|\\.|\\*|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*", "Allowed Special characters are @,*,#,.,$,&,_,-"));

        searchFields.setNullSelectionAllowed(true);
        searchFields.setNullSelectionItemId(Constants.SELECT_ONE);
        searchFields.setContainerDataSource(searchFieldsContainer());
        searchFields.select(Constants.SELECT_ONE);
        searchFields.setImmediate(true);
        searchFields.setValidationVisible(true);
        searchFields.focus();
        searchFields.setPageLength(7);
        searchFields.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Invoked when the value of the field is changed.
             *
             * @param event - ValueChangeEvent
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                try {
                    searchValue.setValue("");
                    searchDdlb.setValue("");
                    availableTable.setValue(null);
                    selectedTable.setValue(null);
                    if (event.getProperty().getValue() != null && !Constants.SELECT_ONE.equals(String.valueOf(event.getProperty().getValue()))) {
                        if ("Company Status".equals(String.valueOf(event.getProperty().getValue()))) {

                            searchValue.setVisible(false);
                            searchDdlb.setVisible(true);
                            loadComboBox(searchDdlb, headerLogic.getCompanyDropDownListWithoutNull(UIUtils.STATUS));
                            searchDdlb.select(Constants.SELECT_ONE);

                        } else if ("Company Type".equals(String.valueOf(event.getProperty().getValue()))) {
                            searchValue.setVisible(false);
                            searchDdlb.setVisible(true);
                            loadComboBox(searchDdlb, headerLogic.getCompanyDropDownListWithoutNull(UIUtils.COMP_TYPE));
                            searchDdlb.select(Constants.SELECT_ONE);
                        } else {
                            searchValue.setVisible(true);
                            searchDdlb.setVisible(false);
		}
                    } else {
                        searchValue.setVisible(true);
                        searchDdlb.setVisible(false);
                    }
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                }
            }
        });

        searchDdlb.setVisible(false);
        searchDdlb.setPageLength(7);
        LOGGER.info("End of configureFields method");
    }

    /**
     * method used for Adds the available table.
     *
     * @return the table
     */
    public void addAvailableTable() {
        LOGGER.info("Entering addAvailableTable method");
        if (tableResultCustomAvail.getObjResult().length > 0) {
            availableTable.markAsDirty();
            avlTblLogic.setContainerDataSource(availableTableContainer);
            availableTable.setVisibleColumns(tableResultCustomAvail.getObjResult());
            availableTable.setColumnHeaders(tableResultCustomAvail.getObjResultHeader());
        } else {
            hLayout2.setVisible(false);
        }
        availableTable.setFilterBarVisible(true);
        availableTable.addStyleName("filterbar");
        availableTable.setFilterDecorator(new ExtDemoFilterDecorator());
        avlTblLogic.setPageLength(8);
        avlTblLogic.sinkItemPerPageWithPageLength(false);
        availableTable.setImmediate(true);
        availableTable.setSelectable(true);
        availableTable.setSortEnabled(true);
        availableTable.setWidth("410px");
        availableTable.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for error handler
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
            }
        });
        availableTableLayout.addComponent(availableTable);
        HorizontalLayout layout = ResponsiveUtils.getResponsiveControls(avlTblLogic.createControls(), true);
        availableTableLayout.addComponent(layout);
        
        LOGGER.info("End of addAvailableTable method");
    }

    /**
     * Method used for Adds the selected table.
     *
     * @return the table
     */
    public void addSelectedTable() {
        LOGGER.info("Entering addSelectedTable method");
        selectedTable.markAsDirty();

        if (tableResultCustomSelect.getObjResult().length > 0) {
            selTblLogic.setContainerDataSource(selectedTableContainer);
            selectedTable.setVisibleColumns(tableResultCustomSelect.getObjResult());
            selectedTable.setColumnHeaders(tableResultCustomSelect.getObjResultHeader());
        } else {
            hLayout2.setVisible(false);
        }
        selTblLogic.setPageLength(8);
        selTblLogic.sinkItemPerPageWithPageLength(false);
        selectedTable.setImmediate(true);
        selectedTable.setFilterBarVisible(true);
        selectedTable.addStyleName("filterbar");
        selectedTable.setWidth("410px");
//        selectedTable.setFilterGenerator(new CfpDetailsFilterGenerator());
        selectedTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedTable.setSelectable(true);
        selTblLogic.fireSetData("", "", sessionDTO, false);
        selectedTable.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for error handler
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
            }
        });
        selectedTableLayout.addComponent(selectedTable);
        HorizontalLayout layout = ResponsiveUtils.getResponsiveControls(selTblLogic.createControls(), true);
        selectedTableLayout.addComponent(layout);
        LOGGER.info("End of addSelectedTable method");
    }

    /**
     * Method used for Adds button.
     *
     * @return the button
     */
    public Button add() {
        LOGGER.info("Entering add method");

        btnMove.setImmediate(true);
        btnMove.setWidth("70");
        btnMove.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to move button error handling and its listener
             */
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        btnMove.addClickListener(new ClickListener() {
            /**
             * Method used for item click event of table
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("buttonClick( ClickEvent event )  name=" + event.getButton().getCaption());

                try {
                    if (availableTable.getValue() != null) {
                        CompanyMasterDTO company = (CompanyMasterDTO) availableTable.getValue();
                        selectedTable.clearFilters();
                        cfpLogic.addCompany(company);
                        selTblLogic.fireSetData("", "", sessionDTO, false);
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Error", "Please select a company to add");
                    }
                     availableTable.setValue(null);
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        });
        LOGGER.info("End of add method");
        return btnMove;
    }

    /**
     * Method used for Removes button logic.
     *
     * @return the button
     */
    public Button remove() {

        LOGGER.info("Entering remove method");
        btnRemove.setWidth("70");
        btnRemove.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to remove button Handling error
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        btnRemove.addClickListener(new ClickListener() {
            /**
             * Method used for remove button logic
             */
            public void buttonClick(final ClickEvent event) {

                LOGGER.info("Entering btnRemove buttonClick method");
                if (selectedTable.getValue() != null) {
                    final CFPCompanyDTO company = (CFPCompanyDTO) selectedTable.getValue();
                    try {
                        CFPSearchLogic.saveToTempCFP(saveContainer.getItemIds());
                        saveContainer.removeItem(company);
                        cfpLogic.removeCompany(Integer.parseInt(company.getTempCFPSystemID()));
                        selTblLogic.fireSetData("", "", sessionDTO, false);
                       
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } catch (PortalException ex) {
                        LOGGER.error(ex);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2011));
                    }
                } else {
                    AbstractNotificationUtils.getErrorNotification("Error", "Please select a company to remove");
                }
                 selectedTable.setValue(null);
                LOGGER.info("End of btnRemove buttonClick method");
            }
            
        });
       
        LOGGER.info("End of remove method");
        return btnRemove;
    }

    /**
     * Method used for Adds all button.
     *
     * @return the button
     */
    public void addAll() {

        LOGGER.info("Entering addAll method");
        btnMoveAll.setImmediate(true);
        btnMoveAll.setWidth("70");
        btnMoveAll.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to move button handling error and its
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        btnMoveAll.addClickListener(new ClickListener() {
            /**
             * Method used for click event of move button
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.info(" buttonClick (Click Event event) name=" + event.getButton().getCaption());
                try {
                    addAllCompanyButtonClick(event);
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                }

            }
        });
        LOGGER.info("End of addAll method");
    }

    /**
     * Adds the all company button click.
     *
     * @param event the event
     */
    protected void addAllCompanyButtonClick(final ClickEvent event) throws SystemException {
        LOGGER.info("Entering addAllCompanyButtonClick method");
        selectedTable.clearFilters();
        if(tempSearchField.equals("Company Status") || tempSearchField.equals("Company Type")){
        tempSearchValue = String.valueOf(((HelperDTO) searchDdlb.getValue()).getId());
        }
        cfpLogic.addToTempCFP(tempSearchField, tempSearchValue);
        selTblLogic.fireSetData("", "", sessionDTO, false);
       
        LOGGER.info("End of addAllCompanyButtonClick method");
    }

    /**
     * Removes the all.
     *
     * @return the button
     */
    public void removeAll() {

        LOGGER.info("Entering removeAll method");
        btnRemoveAll.setWidth("70");
        btnRemoveAll.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for error handling techniques
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        btnRemoveAll.addClickListener(new ClickListener() {
            /**
             * Method used for click event for remove button
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.info(" buttonClick( Click Event event) name=" + event.getButton().getCaption());
                try {
                    removeAllCompanyButtonClick(event);
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                }
            }
        });
        LOGGER.info("End of removeAll method");
    }

    protected void removeAllCompanyButtonClick(final ClickEvent event) throws SystemException {
        LOGGER.info("Entering removeAllCompanyButtonClick method");
        saveContainer.removeAllItems();
        selectedTable.clearFilters();
        cfpLogic.removeAll();
        selTblLogic.fireSetData("", "", sessionDTO, false);
        LOGGER.info("End of removeAllCompanyButtonClick method");
    }

    /**
     * Method used for Adds the find btn.
     *
     * @return the button
     */
    public void addFindBtn() {
        LOGGER.info("Entering addFindBtn method");
        availableTable.setValue(null);
        companySearchBtn.setImmediate(true);
        companySearchBtn.setWidth("85");
        companySearchBtn.addClickListener(new ClickListener() {
            /**
             * Method used for find btn
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.info("Entering btnFind buttonClick method");
                    binder.getErrorDisplay().clearError();
                    if (searchFields.getValue() != null && !Constants.SELECT_ONE.equals(searchFields.getValue())) {

                        if (StringUtils.isNotBlank(searchValue.getValue())) {
                            tempSearchField = searchFields.getValue().toString();
                            tempSearchValue =searchValue.getValue().trim();
                            availableTable.clearFilters();
                            if(avlTblLogic.fireSetData(tempSearchField, tempSearchValue, sessionDTO,  false)){
                                CommonUIUtils.successNotification("Search Completed");
                            }else {
                                CommonUIUtils.successNotification("No results found");
                            }
                        } else if (searchDdlb.getValue() != null && !Constants.SELECT_ONE.equals(searchDdlb.getValue())) {
                            tempSearchField = searchFields.getValue().toString();
                            tempSearchValue = ((HelperDTO) searchDdlb.getValue()).getDescription();
                            availableTable.clearFilters();
                            if(avlTblLogic.fireSetData(tempSearchField, tempSearchValue, sessionDTO,  false)){
                                CommonUIUtils.successNotification("Search Completed");
                            }else {
                                CommonUIUtils.successNotification("No results found");
                            }
                            
                            
                        } else if ("Company No".equals(searchFields.getValue()) || "Company Name".equals(searchFields.getValue())) {
                            binder.getErrorDisplay().setError("Please enter a Value to search");
                        } else if ("Company Status".equals(searchFields.getValue()) || "Company Type".equals(searchFields.getValue())) {
                            binder.getErrorDisplay().setError("Please select a Value to search");
                        }

                    } else {
                        binder.getErrorDisplay().setError("Please Select atleast one option from search field");
                    }
                    LOGGER.info("End of btnFind buttonClick method");
                } catch (Exception ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                }
            }
        });
        LOGGER.info("End of addFindBtn method");
    }

    /**
     * Method used for Search fields container.
     *
     * @return the container
     */
    public Container searchFieldsContainer() {
        final List<String> list = new ArrayList<String>();
        LOGGER.info("Entering searchFieldsContainer method");
        list.add(Constants.SELECT_ONE);
        list.add("Company ID");
        list.add("Company Name");
        list.add("Company No");
        list.add("Company Status");
        list.add("Company Type");
        LOGGER.info("End of searchFieldsContainer method");
        return new IndexedContainer(list);
    }

    /**
     * Focus the search field on tab change.
     */
    public void focusSearchFields() {
        this.searchFields.focus();
    }

    /**
     * Method used for Adds the selected table.
     *
     * @return the table
     */
    public void configureTableForView() {
        LOGGER.info("Entering addSelectedTable method");
        selectedViewTable.setVisible(true);
        selectedViewTable.markAsDirty();
        selectedLazyContainer = new LazyBeanItemContainer(CFPCompanyDTO.class, new SelectedCompaniesContainer(sessionDTO), new LazyLoadCriteria());

        Object[] obj = ContractUtils.SELECTED_COMPANY_COL;
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldContract, Constants.ViewMode);
        selectedViewTable.setContainerDataSource(selectedLazyContainer);
        selectedViewTable.setVisibleColumns(tableResultCustom.getObjResult());
        selectedViewTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        selectedViewTable.setPageLength(7);
        selectedViewTable.setImmediate(true);
        selectedViewTable.setSelectable(true);
        addResponsiveVerticalTabThreeLayout();
        selectedViewTable.setSizeFull();
        selectedViewTable.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for Handling error
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
            }
        });
        LOGGER.info("End of addSelectedTable method");
    }

    public void addResponsiveVerticalTabThreeLayout() {

        reloadVerticalLayoutTabThreeMap.put(1516, true);
        reloadVerticalLayoutTabThreeMap.put(1300, true);
        reloadVerticalLayoutTabThreeMap.put(1024, true);
        reloadVerticalLayoutTabThreeMap.put(978, true);
        reloadVerticalLayoutTabThreeMap.put(800, true);
        reloadVerticalLayoutTabThreeMap.put(480, true);
        reloadVerticalLayoutTabThreeMap.put(320, true);
        reloadVerticalLayoutTabThreeMap.put(0, true);

        Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {

                int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                if (browserWidth > 1516 && reloadVerticalLayoutTabThreeMap.get(1516)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, false);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsDefault(selectedViewTable);
                } else if (browserWidth < 1516 && browserWidth > 1300 && reloadVerticalLayoutTabThreeMap.get(1300)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, false);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsDefault(selectedViewTable);
                } else if (browserWidth < 1300 && browserWidth > 1024 && reloadVerticalLayoutTabThreeMap.get(1024)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, false);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsTwoColumns(selectedViewTable);
                } else if (browserWidth < 1024 && browserWidth > 978 && reloadVerticalLayoutTabThreeMap.get(978)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, false);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsTwoColumns(selectedViewTable);
                } else if (browserWidth < 978 && browserWidth > 800 && reloadVerticalLayoutTabThreeMap.get(800)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, false);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    if (selectedViewTable.getItemIds().isEmpty()) {
                        getCollapsibleColumnsTwoColumns(selectedViewTable);
                    } else {
                        getCollapsibleColumnsOneColumn(selectedViewTable);
                    }
                } else if (browserWidth <= 800 && browserWidth > 480 && reloadVerticalLayoutTabThreeMap.get(480)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, false);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    if (selectedViewTable.getItemIds().isEmpty()) {
                        getCollapsibleColumnsTwoColumns(selectedViewTable);
                    } else {
                        getCollapsibleColumnsOneColumn(selectedViewTable);
                    }
                } else if (browserWidth < 480 && browserWidth > 320 && reloadVerticalLayoutTabThreeMap.get(320)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, false);
                    reloadVerticalLayoutTabThreeMap.put(0, true);
                    getCollapsibleColumnsOneColumn(selectedViewTable);
                } else if (browserWidth < 320 && reloadVerticalLayoutTabThreeMap.get(0)) {
                    reloadVerticalLayoutTabThreeMap.put(1516, true);
                    reloadVerticalLayoutTabThreeMap.put(1300, true);
                    reloadVerticalLayoutTabThreeMap.put(1024, true);
                    reloadVerticalLayoutTabThreeMap.put(978, true);
                    reloadVerticalLayoutTabThreeMap.put(800, true);
                    reloadVerticalLayoutTabThreeMap.put(480, true);
                    reloadVerticalLayoutTabThreeMap.put(320, true);
                    reloadVerticalLayoutTabThreeMap.put(0, false);
                    getCollapsibleColumnsOneColumn(selectedViewTable);
                }

            }
        });
    }

    private static Object[] getCollapsibleColumns978Px(Table table) {
        table.setColumnCollapsingAllowed(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(propertyIds));
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
        propertyIds = list.toArray(new Object[list.size()]);
        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }
        return propertyIds;
    }

    private static Object[] getCollapsibleColumnsDefault(Table table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0, j = 4; i < j; i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private static Object[] getCollapsibleColumnsTwoColumns(Table table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0, j = 2; i < j; i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private static Object[] getCollapsibleColumnsOneColumn(Table table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0, j = 1; i < j; i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new Object[list.size()]);

        for (Object propertyId : table.getVisibleColumns()) {
            table.setColumnCollapsed(propertyId, false);
        }

        for (Object propertyId : propertyIds) {
            table.setColumnCollapsed(propertyId, true);
        }

        return propertyIds;
    }

    private static boolean defaultColumnsVisible(Table table) {
        boolean result = true;
        for (Object propertyId : getCollapsibleColumnsOneColumn(table)) {
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent().getBrowserWindowWidth() < 800) {
                result = false;
            }
        }
        return result;
    }

    private void loadComboBox(ComboBox comboBox, List<HelperDTO> dropDownList) {
        comboBox.removeAllItems();
        comboBox.addItem(Constants.SELECT_ONE);
        for (int i = 0; dropDownList.size() > i; i++) {
            comboBox.addItem(dropDownList.get(i));

        }
    }
    private void addResponsiveGrid(final Map<String, AppPermission> contractDashboard) {

        final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
        LOGGER.info("Entering configurePermission");
        try {

            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Company Addition");

            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, contractDashboard, Constants.EDIT);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
