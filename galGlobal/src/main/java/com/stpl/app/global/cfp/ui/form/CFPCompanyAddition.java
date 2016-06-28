/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.cfp.ui.form;

import com.stpl.app.global.cfp.dto.CFPCompanyDTO;
import com.stpl.app.global.cfp.logic.CFPSearchLogic;
import com.stpl.app.global.cfp.ui.lazyload.AvailableCompaniesContainer;
import com.stpl.app.global.cfp.ui.lazyload.AvailableCompaniesCriteria;
import com.stpl.app.global.cfp.ui.lazyload.CompanyDetailsContainer;
import com.stpl.app.global.cfp.ui.lazyload.CompanyDetailsCriteria;
import com.stpl.app.global.cfp.ui.lazyload.SelectedCompaniesContainer;
import com.stpl.app.global.cfp.ui.lazyload.SelectedCompaniesCriteria;
import com.stpl.app.global.cfp.util.CFPTestGenerator;
import com.stpl.app.global.cfp.util.CommonUtils;
import com.stpl.app.global.cfp.util.UIUtils;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.company.dto.CompanyMasterDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.StplR2Exception;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonLazyUtilDTO;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;


/**
 *
 * @author sooriya.lakshmanan
 */
public class CFPCompanyAddition extends CustomComponent {

    /**
     * this object is used to store the searchField value once search operation
     * is clicked
     */
    Object searchFieldObject;
    /**
     * this object is used to store the search value once search operation is
     * clicked
     */
    Object searchValueObject;
    /**
     * the DTO used in lazy container
     */
    CompanyDetailsCriteria companyDetailsCriteria = new CompanyDetailsCriteria();
    final CommonUtils commonUtils = new CommonUtils();
    CommonLazyUtilDTO lazyUtilDTO = new CommonLazyUtilDTO();
    private static final Logger LOGGER = Logger.getLogger(CFPCompanyAddition.class);
    private final IFPLogic ifpLogic = new IFPLogic();
    private final CommonUIUtils commonUIUtils=new CommonUIUtils();
    final ErrorfulFieldGroup binder;
    /**
     * the search map is used to get the column for life ray service
     */
    final Map<String, String> searchMap = new ConcurrentHashMap<String, String>();
    /**
     * the searchMapAvailable map is used to get the column for database
     */
    final Map<String, String> searchMapAvailable = new ConcurrentHashMap<String, String>();
    private final Map<Integer, Boolean> reloadMap = new HashMap<Integer, Boolean>();
    /**
     * The cfp logic.
     */
    private final CFPSearchLogic cfpLogic;
    /**
     * The lazy container for available table.
     */
    private LazyBeanItemContainer availableItemResultLazyBean;
    /**
     * The lazy container for selected table.
     */
    private LazyBeanItemContainer selectedItemResultLazyBean;
    /**
     * the used id
     */
    private String userId;
    /**
     * the session id
     */
    private String sessionId;
    /**
     * the temp table records created date /** the Mode
     */
    private String mode;
    private String tempCreatedDate;
    
    @UiField("cssLayout")
    private CssLayout cssLayout;
    @UiField("addLayout")
    private VerticalLayout addLayout;
    @UiField("viewLayout")
    private VerticalLayout viewLayout;
    @UiField("searchBtn")
    private Button searchBtn;
    @UiField("searchFields")
    private ComboBox searchFields;
    @UiField("searchDdlb")
        private ComboBox searchDdlb;
    @UiField("searchValue")
    private TextField searchValue;
    /**
     * The available table.
     */
    @UiField("availableTable")
    private CustomePagedFilterTable availableTable;
    /**
     * The selected table.
     */
    @UiField("selectedTable")
    private CustomePagedFilterTable selectedTable;
    /**
     * The View Results table.
     */
    @UiField("viewResultsTable")
    private CustomePagedFilterTable viewResultsTable;
    @UiField("prevColumn")
    private Button prevColumn;
    @UiField("nextColumn")
    private Button nextColumn;
    @UiField("btnMove")
    private Button btnMove;
    @UiField("btnRemove")
    private Button btnRemove;
    @UiField("btnMoveAll")
    private Button btnMoveAll;
    @UiField("btnRemoveAll")
    private Button btnRemoveAll;
    @UiField("availableTableLayout")
    private VerticalLayout availableTableLayout;
    @UiField("selectedTableLayout")
    private VerticalLayout selectedTableLayout;
    CFPCompanyDTO cfpMaster;
    
    private CommonUtil commonUtil = CommonUtil.getInstance();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    SessionDTO sessionDTO;
    CFPCompanies cfpCompanies;

    public CFPCompanyAddition(final ErrorfulFieldGroup binder, CFPCompanyDTO cfpMaster, final SessionDTO sessionDTO,CFPCompanies cfpCompanies) throws Exception {
        this.binder = binder;
        this.cfpMaster = cfpMaster;
        this.sessionDTO=sessionDTO;
        this.cfpCompanies=cfpCompanies;
        cfpLogic = new CFPSearchLogic(this.sessionDTO);
        try {
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/company_family_plan/companyaddition.xml"), this));
            binder.bindMemberFields(this);
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            sessionId = sessionDTO.getUiSessionId();
            tempCreatedDate = sessionDTO.getSessionDate();
            mode = sessionDTO.getMode();

            final StplSecurity stplSecurity = new StplSecurity();
            final Map<String, AppPermission> fieldCfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN+ConstantsUtils.COMMA+"Company Addition",false);
            final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN+ConstantsUtils.COMMA+"Company Addition");

            if (mode.equals(ConstantsUtils.VIEW_BTN)) {

                addLayout.setVisible(false);
                addLayout.removeAllComponents();
                viewLayout.setVisible(true);

                addResultsTable();
            } else {
                viewLayout.setVisible(false);
                viewLayout.removeAllComponents();
                init();

                configureFields();
            }
            configurePermission(fieldCfpHM,functionCompanyHM);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void init() {
        searchDdlb.setVisible(false);
        addFindBtn();
        addAvailableTable();
        addSelectedTable();
        add();
        remove();
        addAll();
        removeAll();
        
    }

    private void configurePermission(Map<String, AppPermission> fieldCfpHM,final Map<String, AppPermission> functionHM) {
         LOGGER.info("Entering configurePermission");
        try {
        
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_FAMILY_PLAN, TabNameUtil.CFP_COMPANY_ADDITION);
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldCfpHM, mode);
        
        if (functionHM.get("search") != null && ((AppPermission) functionHM.get("search")).isFunctionFlag()) {

           addFindBtn();
        LOGGER.info("Ending configurePermission");
    }
        }catch(Exception ex){
            LOGGER.error(ex);
        }
    }

    private void configureFields() {
        searchFields.setContainerDataSource(commonUtils.searchFields());
        searchFields.setNullSelectionAllowed(true);
        searchFields.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        searchFields.setDescription((String) searchFields.getValue());
        searchFields.setValue(ConstantsUtils.SELECT_ONE);
        searchFields.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                try{
                
                if(event.getProperty().getValue()!=null && !Constants.SELECT_ONE.equals(String.valueOf(event.getProperty().getValue()))){
                                if(ConstantsUtils.COMPANYSTATUS.equals(String.valueOf(event.getProperty().getValue()))){

                                        searchValue.setVisible(false);
                                        searchDdlb.setVisible(true);
                                        commonUtil.loadComboBox(searchDdlb, "STATUS", false);
                                        searchDdlb.select(Constants.SELECT_ONE);

                                }else if(ConstantsUtils.COMPANYTYPE.equals(String.valueOf(event.getProperty().getValue()))){
                                    searchValue.setVisible(false);
                                    searchDdlb.setVisible(true);
                                    commonUtil.loadComboBox(searchDdlb, UIUtils.COMPANY_TYPE, false);
                                    searchDdlb.select(Constants.SELECT_ONE);
                                }else{
                                    searchValue.setVisible(true);
                                    searchDdlb.setVisible(false);
                                }
                            }else{
                                 searchValue.setVisible(true);
                                 searchDdlb.setVisible(false);
                            }

                } catch (Exception ex) {
                    LOGGER.info(ex);
                }
            }
        });

        lazyUtilDTO.setUserId(userId);
        lazyUtilDTO.setSessionId(sessionId);
        lazyUtilDTO.setDate(tempCreatedDate);
        lazyUtilDTO.setSearchFlag(true);
    }

    /**
     * Adds the find button.
     *
     * @return the button
     * @throws StplR2Exception the stpl r2 exception
     */
    public void addFindBtn() {

        searchBtn.setImmediate(true);
        searchBtn.setWidth("85");
        searchBtn.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error("Error in Search button");
            }
        });
        searchBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    binder.getErrorDisplay().clearError();
                    LOGGER.info("Entering inside  SEARCH  Method ");
                    loadData();
                    if (searchFieldObject == null) {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Error", "Select the search field", new MessageBoxListener() {
                            /**
                             * The method is triggered when a button of the message box is
                             * pressed .
                             *
                             * @param buttonId The buttonId of the pressed button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                // Do Nothing
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                        return;
                    }
                    if ((StringUtils.isEmpty(searchValueObject.toString().trim()) && searchValue.isVisible()) || ((HelperDTO) searchDdlb.getValue() == null && searchDdlb.isVisible())) {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Error", "Please enter a value to search", new MessageBoxListener() {
                            /**
                             * The method is triggered when a button of the message box is
                             * pressed .
                             *
                             * @param buttonId The buttonId of the pressed button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                // Do Nothing
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                        return;
                    }
                    if (searchFieldObject != null && searchValueObject != null) {
                        loadMap();
                        if (ConstantsUtils.COMPANYTYPE.equals(searchFieldObject.toString())) {
                            if (searchDdlb.getValue() == null && Constants.SELECT_ONE.equals(searchDdlb.getValue())) {
                                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Error", "Select Company type", new MessageBoxListener() {
                                    /**
                                     * The method is triggered when a button of the message box is
                                     * pressed .
                                     *
                                     * @param buttonId The buttonId of the pressed button.
                                     */
                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                        // Do Nothing
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();
                            } else {
                                lazyUtilDTO.setSearchFields(searchMapAvailable.get(searchFieldObject.toString().trim()));
                                lazyUtilDTO.setSearchFieldsCompany(searchMap.get(searchFieldObject.toString().trim()));
                                lazyUtilDTO.setSearchValue(String.valueOf(((HelperDTO) searchDdlb.getValue()).getDescription()));
                            }
                        } else if (ConstantsUtils.COMPANYSTATUS.equals(searchFieldObject.toString())) {
                            if (searchDdlb.getValue() == null && Constants.SELECT_ONE.equals(searchDdlb.getValue())) {
                                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Error", "Select Company Status", new MessageBoxListener() {
                                    /**
                                     * The method is triggered when a button of the message box is
                                     * pressed .
                                     *
                                     * @param buttonId The buttonId of the pressed button.
                                     */
                                    @SuppressWarnings("PMD")
                                    public void buttonClicked(final ButtonId buttonId) {
                                        // Do Nothing
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();
                            } else {
                                lazyUtilDTO.setSearchFields(searchMapAvailable.get(searchFieldObject.toString().trim()));
                                lazyUtilDTO.setSearchFieldsCompany(searchMap.get(searchFieldObject.toString().trim()));
                                lazyUtilDTO.setSearchValue(String.valueOf(((HelperDTO) searchDdlb.getValue()).getDescription()));
                                lazyUtilDTO.setHelperTableSid(String.valueOf(((HelperDTO) searchDdlb.getValue()).getId()));
                            }
                        } else {
                            lazyUtilDTO.setSearchFields(searchMapAvailable.get(searchFieldObject.toString().trim()));
                            lazyUtilDTO.setSearchFieldsCompany(searchMap.get(searchFieldObject.toString().trim()));
                            lazyUtilDTO.setSearchValue(searchValueObject.toString().trim());
                        }
                        // here we are setting the lazyUtilDTO and getting inside the AvailableCompaniesContainer
                        // that is used to pass the  used id , sessionid, serach field and search value
                        }
                        availableTable.setData(lazyUtilDTO);
                        loadAvailbleTable();
                        } catch (Exception e) {
                    LOGGER.error(e);
                }
                LOGGER.info("Ending  SEARCH  Method ");

            }
        });
    }

    private void loadData() {
        searchFieldObject = searchFields.getValue();
        searchValueObject = searchValue.getValue();
    }

    /**
     * the method is used to load availableItemResultLazyBean
     */
    private void loadAvailbleTable() throws PortalException, SystemException, Exception {
         final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN+ConstantsUtils.COMMA+"Company Addition",false);
            
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_FAMILY_PLAN, TabNameUtil.CFP_COMPANY_ADDITION);
            Object[] obj = UIUtils.AVAILABLE_COL;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode);
        if(tableResultCustom.getObjResult().length == 0){
              availableTable.setVisible(false);
            }
            
            availableTable.clearFilters();
        availableItemResultLazyBean = new LazyBeanItemContainer(CompanyMasterDTO.class, new AvailableCompaniesContainer(availableTable), new AvailableCompaniesCriteria());
        availableTable.setContainerDataSource(availableItemResultLazyBean);
        availableTable.setVisibleColumns(tableResultCustom.getObjResult());
        availableTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        availableTable.setFilterBarVisible(true);
        availableTable.setFilterDecorator(new ExtDemoFilterDecorator());
        availableTable.setFilterGenerator(new CFPTestGenerator());
        availableTable.addStyleName(ConstantsUtils.FILTER_BAR);
        if (availableItemResultLazyBean.size() > Constants.ZERO) {
            CommonUIUtils.successNotification(ConstantsUtils.SEARCH_COMPLETED);
        } else {
            CommonUIUtils.successNotification(ConstantsUtils.NO_RESULTS_COMPLETED);
        }
    }

    /**
     * the method is used to load the searchMap and searchMapAvailable
     */
    private void loadMap() {
        searchMap.put(ConstantsUtils.COMPANYNO, ConstantsUtils.COMPANY_NO);
        searchMap.put(ConstantsUtils.COMPANYNAME, ConstantsUtils.COMPANY_NAME);
        searchMap.put(ConstantsUtils.COMPANYTYPE, ConstantsUtils.COMPANY_TYPE);
        searchMap.put(ConstantsUtils.COMPANYSTATUS, ConstantsUtils.COMPANY_STATUS);
        searchMapAvailable.put(ConstantsUtils.COMPANYNO, "COMPANY_NO");
        searchMapAvailable.put(ConstantsUtils.COMPANYNAME, "COMPANY_NAME");
        searchMapAvailable.put(ConstantsUtils.COMPANYTYPE, UIUtils.COMPANY_TYPE);
        searchMapAvailable.put(ConstantsUtils.COMPANYSTATUS, "COMPANY_STATUS");
    }

    /**
     * the method is used to load selectedItemResultLazyBean
     */
    public void loadSelectedTable() throws Exception {
        try {
            selectedTable.clearFilters();
            selectedItemResultLazyBean = new LazyBeanItemContainer(CFPCompanyDTO.class, new SelectedCompaniesContainer(lazyUtilDTO), new SelectedCompaniesCriteria());
            
              final StplSecurity stplSecurity = new StplSecurity();
                userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN+ConstantsUtils.COMMA+"Company Addition",false);
                
                List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_FAMILY_PLAN, TabNameUtil.CFP_COMPANY_ADDITION);
                Object[] obj = UIUtils.SELECTED_COL;
                TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode);
                if(tableResultCustom.getObjResult().length == 0){
              selectedTable.setVisible(false);
            }
            selectedTable.setContainerDataSource(selectedItemResultLazyBean);
            selectedTable.setVisibleColumns(tableResultCustom.getObjResult());
            selectedTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            selectedTable.setFilterBarVisible(true);
            selectedTable.setFilterDecorator(new ExtDemoFilterDecorator());
            selectedTable.setFilterGenerator(new CFPTestGenerator());
            selectedTable.addStyleName(ConstantsUtils.FILTER_BAR);
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * logic for add button.
     *
     * @return the button
     * @throws StplR2Exception the stpl r2 exception
     */
    public void add() {


        btnMove.setImmediate(true);
        btnMove.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.info("Error in Single record move button");
            }
        });
        btnMove.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    binder.getErrorDisplay().clearError();
                    LOGGER.info("Entering inside  MOVE_RIGHT Method");
                    final Set<CompanyMasterDTO> companyDto = (Set<CompanyMasterDTO>) availableTable.getValue();
                    
                    if (companyDto.isEmpty()) {
                                binder.getErrorDisplay().setError("Please select an company to Add");
                                return;
                            } 
                    for (CompanyMasterDTO company : companyDto) {
                        if (company != null) {
                             if (!cfpLogic.moveToRightDuplicateValidation(company, lazyUtilDTO)) {
                                // method to save to temp table
                                cfpLogic.moveAndSaveToTempTable(company, lazyUtilDTO);
                                // here we are setting the lazyUtilDTO and getting inside the SelectedCompaniesContainer
                                // that is used to pass the  used id , sessionid
                            }
                        }
                    }
                    loadSelectedTable();
                    LOGGER.info("Ending  MOVE_RIGHT Method ");
                } catch (Exception e) {

                    LOGGER.error(e);
                }
            }
        });
    }

    /**
     * method to implement remove button.
     *
     * @return the button
     * @throws StplR2Exception the stpl r2 exception
     */
    public void remove() {

        btnRemove.setErrorHandler(new ErrorHandler() {
            /**
             *
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.info(ConstantsUtils.ERROR_IN_SEARCH);
            }
        });
        btnRemove.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    LOGGER.info("Entering inside  MOVE_LEFT Method ");
                    binder.getErrorDisplay().clearError();
                    final Set<CompanyMasterDTO> companyDto = (Set<CompanyMasterDTO>) selectedTable.getValue();
                    if (companyDto.isEmpty()) {
                                binder.getErrorDisplay().setError("Please select an company to remove");
                                return;
                            } 
                    for (CompanyMasterDTO company : companyDto) {
                        if (company != null) {
                                cfpLogic.deleteTempCFPDetails(company, lazyUtilDTO);
                                List<CFPCompanyDTO> savedList=new ArrayList<>();
                                savedList.addAll(cfpCompanies.companyDetailsResultSaveBean.getItemIds());
                                for (Iterator<CFPCompanyDTO> it = savedList.iterator(); it.hasNext();) {
                                CFPCompanyDTO dto = it.next();
                                if (dto.getCompanyId().trim().equals((company.getCompanyId()))) {
                                    cfpCompanies.companyDetailsResultSaveBean.removeItem(dto);
                                }
                            }
                                // here we are setting the lazyUtilDTO and getting inside the SelectedCompaniesContainer
                                // that is used to pass the used id , sessionid
                        }
                    }

                    loadSelectedTable();
                    LOGGER.info("Ending  MOVE_LEFT Method ");
                } catch (PortalException ex) {
                    LOGGER.error(ex);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message box is
                         * pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),errorMsg, new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message box is
                         * pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    /**
     * Adds the addall button.
     *
     * @return the button
     */
    public void addAll() {

        btnMoveAll.setImmediate(true);
        btnMoveAll.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error("Error in All button");
            }
        });
        btnMoveAll.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("Entering inside  MOVE_ALL_RIGHT Method ");
                binder.getErrorDisplay().clearError();
                addAllCompanyButtonClick(event);
                LOGGER.info("ENDING  MOVE_ALL_RIGHT Method ");
            }
        });
    }

    /**
     * This method is called when clicked on MOVE_ALL_RIGHT button to transfer
     * all items of Customers and Contracts.
     *
     * @param event the event
     * @throws StplR2Exception the stpl r2 exception
     */
    protected void addAllCompanyButtonClick(final Button.ClickEvent event) {
        try {
            cfpLogic.addToTempTable(lazyUtilDTO);
            // here we are setting the lazyUtilDTO and getting inside the SelectedCompaniesContainer
            // that is used to pass the used id , sessionid
            selectedTable.setData(lazyUtilDTO);
            loadSelectedTable();
        } catch (PortalException ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } catch (SystemException ex) {
            final String errorMs = ErrorCodeUtil.getErrorMessage(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMs, new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is used to creates a ConstantsUtils.MOVE_ALL_LEFT button and
     * handle the click event
     *
     * @return Button
     * @throws StplR2Exception the stpl r2 exception
     */
    public void removeAll() {

        btnRemoveAll.setErrorHandler(new ErrorHandler() {
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error("Error in Remove All button");
            }
        });
        btnRemoveAll.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("Entering inside  MOVE_ALL_LEFT Method ");
                binder.getErrorDisplay().clearError();
                removeAllCompanyButtonClick(event);
                LOGGER.info("Ending inside  MOVE_ALL_LEFT Method ");
            }
        });
    }

    /**
     * This method is called when clicked on ConstantsUtils.MOVE_ALL_LEFT button
     * to transfer all items of Customers and Contracts
     *
     * @param event the event
     * @throws StplR2Exception the stpl r2 exception
     */
    public void removeAllCompanyButtonClick(final Button.ClickEvent event) {
        try {
            LOGGER.info("Entering removeAllCompanyButtonClick");

            cfpLogic.deleteAllTempCFPDetails(lazyUtilDTO);

            loadSelectedTable();
            LOGGER.info("Ending removeAllCompanyButtonClick");
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
            final String error = ErrorCodeUtil.getErrorMessage(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), error, new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
            	@SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adds the available table.
     *
     * @return the table
     * @throws StplR2Exception the stpl r2 exception
     */
    public void addAvailableTable() {
        try {
            LOGGER.info("Entering addAvailableTable");
             final StplSecurity stplSecurity = new StplSecurity();
                userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                final Map<String, AppPermission> fieldCfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN+ConstantsUtils.COMMA+"Company Addition",false);
                  HorizontalLayout layout = ResponsiveUtils.getResponsiveControls(availableTable.createControls(), true);
                List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_FAMILY_PLAN, TabNameUtil.CFP_COMPANY_ADDITION);
                Object[] obj = UIUtils.AVAILABLE_COL;
                TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldCfpHM, mode);
                if(tableResultCustom.getObjResult().length == 0){
              availableTable.setVisible(false);
              layout.setVisible(false);
            }
                 availableItemResultLazyBean = new LazyBeanItemContainer(CompanyMasterDTO.class, new AvailableCompaniesContainer(availableTable), new AvailableCompaniesCriteria());
                availableTable.setContainerDataSource(availableItemResultLazyBean);
                availableTable.setVisibleColumns(tableResultCustom.getObjResult());
                availableTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
                
            availableTable.markAsDirty();
           
   
            availableTable.setPageLength(6);
            availableTable.setNullSelectionAllowed(true);
            availableTable.sinkItemPerPageWithPageLength(false);
            availableTable.setImmediate(true);
            availableTable.setSelectable(true);
            availableTable.setMultiSelect(true);
            availableTable.setMultiSelectMode(MultiSelectMode.DEFAULT);
            availableTable.setFilterBarVisible(true);
            availableTable.setFilterDecorator(new ExtDemoFilterDecorator());
            availableTable.setFilterGenerator(new CFPTestGenerator());
            availableTable.addStyleName(ConstantsUtils.FILTER_BAR);
            availableTable.setWidth("380px");
            availableTable.setFilterBarVisible(true);
            availableTable.setFilterDecorator(new ExtDemoFilterDecorator());
            availableTable.setFilterGenerator(new CFPTestGenerator());
            availableTableLayout.addComponent(availableTable);
            availableTableLayout.addComponent(layout);
            } catch (PortalException ex) {
                LOGGER.error(ex);
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                LOGGER.error(ex);
                java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
        }
            availableTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
                /**
                 * Called when a item has been clicked.
                 */
                @Override
                public void itemClick(ItemClickEvent event) {
                }
            });
            availableTable.setErrorHandler(new ErrorHandler() {
                /**
                 * Invoked when an error occurs.
                 */
                public void error(final com.vaadin.server.ErrorEvent event) {
                    LOGGER.error("Error in avalable table");
                }
            });
            LOGGER.info("Ending addAvailableTable");
        
    }

    /**
     * Adds the selected table.
     *
     * @return the table
     * @throws StplR2Exception the stpl r2 exception
     */
    public void addSelectedTable() {
        try {
            LOGGER.info("Entering addSelectedTable");
             final StplSecurity stplSecurity = new StplSecurity();
                userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                final Map<String, AppPermission> fieldCfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN+ConstantsUtils.COMMA+"Company Addition",false);
                HorizontalLayout layout = ResponsiveUtils.getResponsiveControls(selectedTable.createControls(), true);
                List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_FAMILY_PLAN, TabNameUtil.CFP_COMPANY_ADDITION);
                Object[] obj = UIUtils.SELECTED_COL;
                TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldCfpHM, mode);
               if(tableResultCustom.getObjResult().length == 0){
              selectedTable.setVisible(false);
              layout.setVisible(false);
            }
            selectedTable.markAsDirty();
            if (mode.equals("Edit")) {
                lazyUtilDTO.setUserId(userId);
                lazyUtilDTO.setSessionId(sessionId);
                lazyUtilDTO.setDate(tempCreatedDate);
                lazyUtilDTO.setSearchFlag(true);
                selectedItemResultLazyBean = new LazyBeanItemContainer(CFPCompanyDTO.class, new SelectedCompaniesContainer(lazyUtilDTO), new SelectedCompaniesCriteria());
            } else {
                selectedItemResultLazyBean = new LazyBeanItemContainer(CFPCompanyDTO.class, new SelectedCompaniesContainer(null), new SelectedCompaniesCriteria());
            }
             selectedTable.setContainerDataSource(selectedItemResultLazyBean);
                selectedTable.setVisibleColumns(tableResultCustom.getObjResult());
                selectedTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            selectedTable.setNullSelectionAllowed(true);
            selectedTable.setPageLength(6);
            selectedTable.sinkItemPerPageWithPageLength(false);
            selectedTable.setImmediate(true);
            selectedTable.setSelectable(true);
            selectedTable.setMultiSelect(true);
            selectedTable.setMultiSelectMode(MultiSelectMode.DEFAULT);
            selectedTable.setFilterBarVisible(true);
            selectedTable.setFilterDecorator(new ExtDemoFilterDecorator());
            selectedTable.setFilterGenerator(new CFPTestGenerator());
            selectedTable.addStyleName(ConstantsUtils.FILTER_BAR);
            selectedTable.setWidth("380px");
            selectedTable.setFilterBarVisible(true);
            selectedTable.setFilterDecorator(new ExtDemoFilterDecorator());
            selectedTable.setFilterGenerator(new CFPTestGenerator());
            selectedTableLayout.addComponent(selectedTable);
            selectedTableLayout.addComponent(layout);
            selectedTable.setErrorHandler(new ErrorHandler() {
                /**
                 * Invoked when an error occurs.
                 */
                public void error(final com.vaadin.server.ErrorEvent event) {
                    LOGGER.error("Error in Selectable Table");
                }
            });
            LOGGER.info("Ending addSelectedTable");
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void resetAvailableTable() {
        try {
            BeanItemContainer<CFPCompanyDTO> emptyContainer = new BeanItemContainer<CFPCompanyDTO>(CFPCompanyDTO.class);
                      final StplSecurity stplSecurity = new StplSecurity();
                    userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                    final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN+ConstantsUtils.COMMA+"Company Addition",false);
                    
                    List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_FAMILY_PLAN, TabNameUtil.CFP_COMPANY_ADDITION);
                    Object[] obj = UIUtils.AVAILABLE_COL;
                    TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode);
                if(tableResultCustom.getObjResult().length == 0){
              availableTable.setVisible(false);
            }
            availableTable.setContainerDataSource(emptyContainer);
            availableTable.setVisibleColumns(tableResultCustom.getObjResult());
            availableTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addResultsTable() {
        try {
            LOGGER.info("Entering CFPViewForm addSelectedTable");
            viewResultsTable.markAsDirty();
            viewResultsTable.addStyleName(ConstantsUtils.TABLE_CHECK_BOX);
           selectedItemResultLazyBean = new LazyBeanItemContainer(CFPCompanyDTO.class, new CompanyDetailsContainer(viewResultsTable, Boolean.TRUE,
                    String.valueOf(cfpMaster.getCompanyFamilyPlanSystemId()), new String[]{cfpMaster.getCompanyFamilyPlanNo(), cfpMaster.getCompanyFamilyPlanName()}, "CompanyAddition"), new CompanyDetailsCriteria());
                   final StplSecurity stplSecurity = new StplSecurity();
                userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN+ConstantsUtils.COMMA+"Company Addition",false);
                
                List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_FAMILY_PLAN, TabNameUtil.CFP_COMPANY_ADDITION);
                Object[] obj = UIUtils.SELECTED_COL;
                TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldIfpHM, mode.equals("View")? "view" : mode);
            if(tableResultCustom.getObjResult().length == 0){
              viewResultsTable.setVisible(false);
            }
            viewResultsTable.setContainerDataSource(selectedItemResultLazyBean);
            viewResultsTable.setVisibleColumns(tableResultCustom.getObjResult());
            viewResultsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            viewResultsTable.setPageLength(11);
            viewResultsTable.setImmediate(true);
            viewResultsTable.setSelectable(true);
            viewResultsTable.setSizeFull();
            viewResultsTable.setErrorHandler(new ErrorHandler() {
                /**
                 * Invoked when an error occurs.
                 */
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                    LOGGER.error(event.toString());
                }
            });
            LOGGER.info("Ending CFPViewForm addSelectedTable");
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CFPCompanyAddition.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addResponsiveSearchTableCollapse(final CustomePagedFilterTable table) {
        reloadMap.put(1516, true);
        reloadMap.put(978, true);
        reloadMap.put(600, true);
        reloadMap.put(480, true);
        reloadMap.put(320, true);
        reloadMap.put(0, true);

        try {

            table.setColumnCollapsingAllowed(true);

            Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
                @Override
                public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {

                    int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                    if (browserWidth > 1516 && reloadMap.get(1516)) {
                        companyDetailsCriteria.setCustomDirty(false);

                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumnsDefault(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth > 1516);
                        }
                        reloadMap.put(1516, false);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);
                        companyDetailsCriteria.setCustomDirty(true);
                    } else if (browserWidth < 1516 && browserWidth > 978 && reloadMap.get(978)) {
                        companyDetailsCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 1516);
                        }

                        reloadMap.put(1516, true);
                        reloadMap.put(978, false);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);
                        companyDetailsCriteria.setCustomDirty(true);
                    } else if (browserWidth < 978 && browserWidth > 600 && reloadMap.get(600)) {
                        companyDetailsCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }

                        if (selectedItemResultLazyBean != null && selectedItemResultLazyBean.getItemIds().isEmpty()) {
                            for (Object propertyId : getCollapsibleColumns978Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        } else {
                            for (Object propertyId : getCollapsibleColumns600Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        }

                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, false);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);
                        companyDetailsCriteria.setCustomDirty(true);
                    } else if (browserWidth < 600 && browserWidth > 480 && reloadMap.get(480)) {
                        companyDetailsCriteria.setCustomDirty(false);       // --> Disables reloading the container
                        for (Object propertyId : table.getVisibleColumns()) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns600Px(table)) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, true);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, false);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);
                        companyDetailsCriteria.setCustomDirty(true);            // --> Enables reloading the container
                    } else if (browserWidth < 480 && browserWidth > 320 && reloadMap.get(320)) {

                        companyDetailsCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 480);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, false);
                        reloadMap.put(0, true);
                        companyDetailsCriteria.setCustomDirty(true);
                    } else if (browserWidth < 320 && reloadMap.get(0)) {

                        companyDetailsCriteria.setCustomDirty(false);
                        for (Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 320);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, false);
                        companyDetailsCriteria.setCustomDirty(true);
                    }

                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private static String[] getCollapsibleColumns600Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumns480Px(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleColumns978Px(CustomePagedFilterTable table) {
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

    private static String[] getCollapsibleColumnsDefault1515Px(CustomePagedFilterTable table) {
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

    private static String[] getCollapsibleColumnsDefault(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        for (int i = 0; i < 10 && !list.isEmpty(); i++) {
            list.remove(propertyIds[i]);
        }
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    public void setDefaultTableWidth(final CustomePagedFilterTable table) {

        try {
            table.setColumnCollapsingAllowed(true);
            int browserWidth = Page.getCurrent().getBrowserWindowWidth();
            if (browserWidth > 1516) {
                companyDetailsCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getSixColumns(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                companyDetailsCriteria.setCustomDirty(true);
            } else if (browserWidth < 1516 && browserWidth > 978) {
                companyDetailsCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                companyDetailsCriteria.setCustomDirty(true);
            } else if (browserWidth < 978 && browserWidth > 600) {
                companyDetailsCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }

                if (selectedItemResultLazyBean != null && selectedItemResultLazyBean.getItemIds().isEmpty()) {
                    for (Object propertyId : getCollapsibleColumns978Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                } else {
                    for (Object propertyId : getCollapsibleColumns600Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                }
                companyDetailsCriteria.setCustomDirty(true);
            } else if (browserWidth < 600 && browserWidth > 480) {
                companyDetailsCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns600Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                companyDetailsCriteria.setCustomDirty(true);
            } else if (browserWidth < 480 && browserWidth > 320) {
                companyDetailsCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                companyDetailsCriteria.setCustomDirty(true);
            } else if (browserWidth < 320) {
                companyDetailsCriteria.setCustomDirty(false);
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
                companyDetailsCriteria.setCustomDirty(true);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private static Object[] getSixColumns(CustomePagedFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<Object>(Arrays.asList(visibleColumns));
        for (int i = 0; i < 6; i++) {
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
    
    public void selectedTableLoad(){
        selectedTable.setCurrentPage(selectedTable.getCurrentPage());
    }
}
