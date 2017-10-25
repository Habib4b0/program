/**
 *
 */
package com.stpl.app.contract.dashboard.ui.form;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.contract.dashboard.dto.IfpAvailableTableGenerator;
import com.stpl.app.contract.dashboard.dto.TempPricingDTO;
import com.stpl.app.contract.dashboard.logic.AvailableTableLogic;
import com.stpl.app.contract.dashboard.logic.SelectedTableLogic;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.global.dto.IfpItemDTO;
import com.stpl.app.contract.global.dto.ItemMasterDTO;
import com.stpl.app.contract.global.dto.VwContractPriceInfoDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CHFunctionNameUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.contract.util.ResponsiveUtils;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.ifs.util.UISecurityUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;

/**
 * @author sibi
 *
 */
public class ItemAdditionTab extends CustomComponent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final static Logger LOGGER = Logger.getLogger(ItemAdditionTab.class);

    @UiField("hlayout")
    private HorizontalLayout hLayout;

    @UiField("hlayout2")
    private HorizontalLayout hLayout2;

    @UiField("searchFields")
    private ComboBox searchFields;

    @UiField("searchValue")
    private TextField searchValue;

    @UiField("valueList")
    private ComboBox valueList;

    @UiField("searchBtn")
    private Button btnFind;

    @UiField("controlLayout")
    private HorizontalLayout controlLayout;
    

    @UiField("btnMove")
    private Button btnMove;

    @UiField("hlayout2")
    private HorizontalLayout hlayout2;

    @UiField("cssLayout")
    private CssLayout cssLayout;

    @UiField("btnRemove")
    private Button btnRemove;

    @UiField("btnMoveAll")
    private Button btnMoveAll;

    @UiField("btnRemoveAll")
    private Button btnRemoveAll;


    @UiField("searchType")
    private OptionGroup searchType;

    @UiField("availableTableLayout")
    private VerticalLayout availableTableLayout;
    
     @UiField("selectedTableLayout")
    private VerticalLayout selectedTableLayout;
     
    @UiField("viewSelectedTableLayout")
    private VerticalLayout viewSelectedTableLayout;


    final boolean isEditable;
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();

    /**
     * The available item result bean.
     */
    /**
     * The selected item result bean.
     */

    private BeanItemContainer<TempPricingDTO> saveContainer = new BeanItemContainer<TempPricingDTO>(TempPricingDTO.class);

    /**
     * Dummy lazy bean container to clear the Table
     */
    private final BeanItemContainer<ItemMasterDTO> tableContainer = new BeanItemContainer<ItemMasterDTO>(ItemMasterDTO.class);
    private final BeanItemContainer<IfpItemDTO> selectedTableContainer = new BeanItemContainer<IfpItemDTO>(IfpItemDTO.class);

    private String tempSearchField = StringUtils.EMPTY;

    private String tempSearchValue = StringUtils.EMPTY;

    /**
     * The item map.
     */
    private final ContractHeaderLogic contractHL = new ContractHeaderLogic();
    /**
     * The item details results bean.
     */

    /**
     * The ifp logic.
     */
    private final IfpLogic ifpLogic;
    /**
     * The common util.
     */
    private CommonUtil commonUtil = CommonUtil.getInstance();
    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    final StplSecurity stplSecurity = new StplSecurity();
    String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
    final Map<String, AppPermission> fieldContract = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + "Item Addition", false);
    List<Object> resultList = contractHL.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Item Addition");
    SessionDTO sessionDTO;

    AvailableTableLogic tableLogic = new AvailableTableLogic();
    SelectedTableLogic selectedTableLogic = new SelectedTableLogic();
    private ExtPagedTable availableTable = new ExtPagedTable(tableLogic);
    private ExtPagedTable selectedTable = new ExtPagedTable(selectedTableLogic);
    List<Integer> pageLength = new ArrayList<Integer>();
    boolean validationFlag=true;

    /**
     * The Constructor.
     *
     * @param availableItemResultBean the available item result bean
     * @param selectedItemResultBean the selected item result bean
     * @param itemDetailsResultsBean the item details results bean
     * @param itemMap the item map
     */
    public ItemAdditionTab(final BeanItemContainer<ItemMasterDTO> availableItemResultBean, final BeanItemContainer<ItemMasterDTO> selectedItemResultBean,
            final BeanItemContainer<TempPricingDTO> saveContainer, final BeanItemContainer<VwContractPriceInfoDTO> itemDetailsResultsBean, final Map<String, String> itemMap, final boolean isEditable, final SessionDTO sessionDTO)
            throws SystemException, PortalException {
        super();
        LOGGER.debug("Entering ItemsAndPricingTab");
        this.saveContainer = saveContainer;
        this.isEditable = isEditable;
        this.sessionDTO = sessionDTO;
        ifpLogic = new IfpLogic(this.sessionDTO);
        this.setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-dashboard/item-addition.xml"), this));
        init();
        LOGGER.debug("End of ItemsAndPricingTab");
    }

    private void init() throws SystemException ,PortalException {

        final Map<String, AppPermission> fieldItemAddition = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + "Item Addition", false);
        addResponsiveGrid(fieldItemAddition);
        pageLength.add(NumericConstants.TEN);
        pageLength.add(NumericConstants.FIFTEEN);
        pageLength.add(NumericConstants.TWENTY);
        pageLength.add(NumericConstants.TWENTY_FIVE);
        pageLength.add(NumericConstants.FIFTY);
        pageLength.add(NumericConstants.HUNDRED);
        if (isEditable) {
            configureFields();
            configureButton();
            configureTables();
            viewSelectedTableLayout.setVisible(false);
        } else {
            configureFieldsOnView();
            addSelectedTableForView();
            selectedTable.setReadOnly(true);
        }
        searchType.setImmediate(true);
        searchType.addItem("Item");
        searchType.addItem("IFP");
        searchType.setValue("IFP");

        searchType.addStyleName("horizontal");
    }

    private void configureFieldsOnView() {
        hLayout.setVisible(false);
        hLayout2.setVisible(false);
        availableTable.setVisible(false);
        btnMove.setVisible(false);
        btnMoveAll.setVisible(false);
        btnRemove.setVisible(false);
        btnRemoveAll.setVisible(false);
    }

    private void configureButton() {
        try {
            final Map<String, AppPermission> funItemAdditionHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CONTRACT_DASHBOARD + Constants.COMMA + "Item Addition");
            add();
            remove();
            addAll();
            removeAll();
            if (!(funItemAdditionHM.get(CHFunctionNameUtils.ItemSearch) != null) || !((AppPermission) funItemAdditionHM.get(CHFunctionNameUtils.ItemSearch)).isFunctionFlag()) {
                btnFind.setVisible(false);
            } else {
                addFindBtn();
            }
            addFindBtn();
        } catch (SystemException ex) {
           LOGGER.error(ex);
        } catch (Exception ex) {
             LOGGER.error(ex);
        }
    }

    /**
     * Gets the tables.
     *
     * @return the tables
     */
    public void configureTables() {
        LOGGER.debug("Entering getTables method");
        addAvailableTable();
        addSelectedTable();
        LOGGER.debug("End of getTables method");
    }

    /**
     * Adds the find btn.
     *
     * @return the button
     */
    public void addFindBtn() {

        LOGGER.debug("Entering addFindBtn method");
        btnFind.setImmediate(true);
        btnFind.setWidth("85");
        btnFind.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to find button error handling
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        btnFind.addClickListener(new ClickListener() {
            /**
             * Method used to find button and its logic.
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    int value = 0;
                    LOGGER.debug("Entering btnFind buttonClick method");
                    if (searchFields == null || searchFields.getValue() == null) {
                        AbstractNotificationUtils.getWarningNotification("Search Criteria", "Please select the search field");
                        return;
                    }
                    if (searchFields.getValue() != null && searchValue.isVisible() && (searchValue.getValue() == null || StringUtils.isEmpty(searchValue.getValue().trim()))) {
                        AbstractNotificationUtils.getWarningNotification("Search Criteria", "Please enter a Value to search");
                        return;
                    }
                    if (valueList.getValue() != null) {
                        value = ((HelperDTO) (valueList.getValue())).getId();
                    }
                    if (searchFields.getValue() != null && valueList.isVisible() && value == 0) {
                        AbstractNotificationUtils.getWarningNotification("Search Criteria", "Please select the value to search");
                        return;
                    }

                    if (searchFields.getValue() != null && (!StringUtils.EMPTY.equals(searchValue.getValue()) || value != 0)) {
                        tempSearchField = searchFields.getValue().toString();
                        if (searchValue.isVisible()) {
                            tempSearchValue = searchValue.getValue().trim();
                        } else if (valueList.isVisible()) {
                            tempSearchValue = String.valueOf(((HelperDTO) (valueList.getValue())).getId());
                        }
                        availableTable.clearFilters();
                        tableLogic.configureSearchData(tempSearchField, tempSearchValue,"Available",sessionDTO);
                        Object[] objAvail = ContractUtils.AVAILABLE_ITEM_COL;
                        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objAvail, fieldContract, Constants.EDIT);
                        if (tableResultCustom.getObjResult().length > 0) {
                            availableTable.setVisibleColumns(tableResultCustom.getObjResult());
                            availableTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
                        } else {
                            hlayout2.setVisible(false);
                        }
                        availableTable.setPageLength(NumericConstants.SIX);
                        availableTable.setImmediate(true);
                        availableTable.setSelectable(true);
                        if (availableTable.size() > Constants.ZERO) {
                            CommonUIUtils.successNotification("Search Completed");
                        } else {
                            CommonUIUtils.successNotification("No results found");
                        }
                    }
                    LOGGER.debug("End of btnFind buttonClick method");
                } catch (Exception e) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                }
            }
        });
        LOGGER.debug("End of addFindBtn method");
    }

    /**
     * Adds the Available table.
     *
     * @return the table
     */
    public void addAvailableTable() {
        LOGGER.debug("Entering addAvailableTable method");
        Object[] objAvail = ContractUtils.AVAILABLE_ITEM_COL;
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objAvail, fieldContract, Constants.EDIT);      
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pageLength);
        availableTableLayout.addComponent(availableTable);
        HorizontalLayout layout = ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), true);
        availableTableLayout.addComponent(layout);

        tableLogic.setContainerDataSource(tableContainer);
        if (tableResultCustom.getObjResult().length > 0) {
            availableTable.setVisibleColumns(tableResultCustom.getObjResult());
            availableTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        } else {
            hlayout2.setVisible(false);
        }
        tableLogic.setPageLength(NumericConstants.SIX);
        tableLogic.sinkItemPerPageWithPageLength(false);

        availableTable.setImmediate(true);
        availableTable.setSelectable(true);
        availableTable.markAsDirty();
        availableTable.setComponentError(null);
        availableTable.setFilterBarVisible(true);
        availableTable.setFilterGenerator(new IfpAvailableTableGenerator());
        availableTable.setFilterDecorator(new ExtDemoFilterDecorator());
        availableTable.setValidationVisible(false);
        availableTable.addStyleName("filterbar");
        availableTable.setWidth("390px");
        LOGGER.debug("End of addAvailableTable method");
    }

    /**
     * Adds the selected table .
     *
     * @return the Selected table
     */
    public void addSelectedTable() {
        LOGGER.debug("Entering addSelectedTable method");
        availableTable.markAsDirty();
        selectedTableLogic.getControlConfig().setPageLengthsAndCaptions(pageLength);
        selectedTableLayout.addComponent(selectedTable);
        HorizontalLayout layout = ResponsiveUtils.getResponsiveControls(selectedTableLogic.createControls(), true);
        selectedTableLayout.addComponent(layout);
        selectedTableLogic.setContainerDataSource(selectedTableContainer);
        loadSelectedTable();
        selectedTable.setPageLength(NumericConstants.SIX);
        selectedTableLogic.sinkItemPerPageWithPageLength(false);
        selectedTable.setImmediate(true);
        selectedTable.setSelectable(true);
        selectedTable.setFilterBarVisible(true);
        selectedTable.addStyleName("filterbar");
        selectedTable.setWidth("390px");
        selectedTable.setFilterGenerator(new IfpAvailableTableGenerator());
        selectedTable.setFilterDecorator(new ExtDemoFilterDecorator());
        
        selectedTable.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to selected table error handling.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
              
            }
        });
        LOGGER.debug("End of addSelectedTable method");
    }

    /**
     * Adds the.
     *
     * @return the button
     */
    public void add() {
        LOGGER.debug("Entering add method");
        btnMove.setImmediate(true);
        btnMove.setWidth("70");
        btnMove.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to add button error handler.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        btnMove.addClickListener(new ClickListener() {
            /**
             * Method used to move button logic and its listener.
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.debug("Entering btnMove buttonClick method");
                    if (availableTable.getValue() != null) {
                    ItemMasterDTO itemMasterDetailsList = (ItemMasterDTO) availableTable.getValue();
                    selectedTable.clearFilters();
                    ifpLogic.addItem(itemMasterDetailsList);
                    loadSelectedTable();
                    LOGGER.debug("End of btnMove buttonClick method");
                    }
                    else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please select a Item to add");
                    }
                    availableTable.setValue(null);
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                }
            }
        });
        LOGGER.debug("End of add method");
    }

    /**
     * Removes the.
     *
     * @return the button
     */
    public void remove() {
        LOGGER.debug("Entering remove method");

        btnRemove.setWidth("70");
        btnRemove.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to remove button error handling and its technique
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        btnRemove.addClickListener(new ClickListener() {
            /**
             * Method used for remove button logic and its listener.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug(" buttonClick ,( ClickEvent eventv ) name=" + event.getButton().getCaption());
                if (selectedTable.getValue() != null) {
                    IfpItemDTO temp = (IfpItemDTO) selectedTable.getValue();
                    try {
                        if (saveContainer.size() > 0) {
                            IfpLogic.saveToTempIFP(saveContainer.getItemIds(), isEditable);
                            saveContainer.removeAllItems();
                        }
                        ifpLogic.removeItem(Integer.parseInt(temp.getTempItemPriceRebateSystemId()));
                        loadSelectedTable();
                    } catch (SystemException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                        AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                    } catch (PortalException ex) {
                        final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                        LOGGER.error(errorMsg);
                    }
                } else {
                    AbstractNotificationUtils.getErrorNotification(Constants.ERROR, "Please select a Item to remove");
                }
                selectedTable.setValue(null);
            }
        });
        LOGGER.debug("End of remove method");

    }
    
    /**
     * Adds the all.
     *
     * @return the button
     */
    public void addAll() {

        LOGGER.debug("Entering addAll method");
        btnMoveAll.setImmediate(true);
        btnMoveAll.setWidth("70");
        btnMoveAll.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to add all button and its listener.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        btnMoveAll.addClickListener(new ClickListener() {
            /**
             * Method used to move button logic and its listener.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug(" ButtonClick ( ClickEvent event ) name=" + event.getButton().getCaption());
                try {
                    addAllCompanyButtonClick(event);
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                }
            }
        });
        LOGGER.debug("End of addAll method");
    }

    /**
     * Adds the all company button click.
     *
     * @param event the event
     */
    protected void addAllCompanyButtonClick(final ClickEvent event) throws SystemException {
        LOGGER.debug("inside ItemsAndPricingTab addAllCompanyButtonClick");
        if(availableTable.size()>0){
        ifpLogic.addToTempIFP(tempSearchField, tempSearchValue);
        if ("IFP No".equalsIgnoreCase(tempSearchField) || "IFP Name".equalsIgnoreCase(tempSearchField) || "Brand Name".equalsIgnoreCase(tempSearchField)) {
            selectedTableLogic.configureSearchData(sessionDTO);
            Object[] objSelected = ContractUtils.SELECTED_ITEM_COL;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objSelected, fieldContract, Constants.EDIT);
            if (tableResultCustom.getObjResult().length > 0) {
                selectedTable.setVisibleColumns(tableResultCustom.getObjResult());
                selectedTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            } else {
                hlayout2.setVisible(false);
            }
        } else {
            loadSelectedTable();
        }
        }
    }

    /**
     * Removes the all.
     *
     * @return the button
     */
    public void removeAll() {

        LOGGER.debug("Entering removeAll method");
        btnRemoveAll.setWidth("70");
        btnRemoveAll.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to remove all button error handling.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });
        btnRemoveAll.addClickListener(new ClickListener() {
            /**
             * Method used to remove button and its listener.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("Entering btnRemove buttonClick method");
                try {
                    try {
                        removeAllCompanyButtonClick(event);
                    } catch (PortalException ex) {
                        LOGGER.error(ex);
                    }
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                }
                LOGGER.debug("End of btnRemove buttonClick method");
            }
        });
        LOGGER.debug("End of removeAll method");
    }

    /**
     * Removes the all company button click.
     *
     * @param event the event
     */
    protected void removeAllCompanyButtonClick(final ClickEvent event) throws SystemException, PortalException {
        LOGGER.debug("Entering removeAllCompanyButtonClick method");
        if (saveContainer.size() > 0) {
            ifpLogic.saveToTempIFP(saveContainer.getItemIds(), isEditable);
            saveContainer.removeAllItems();
        }                
        ifpLogic.removeAll();
        loadSelectedTable();
        LOGGER.debug("End of removeAllCompanyButtonClick method");
    }

    public void loadSelectedTable() {
        Object[] objSelected = ContractUtils.SELECTED_ITEM_COL;
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objSelected, fieldContract, Constants.EDIT);
        selectedTableLogic.configureSearchData(sessionDTO);
        if (tableResultCustom.getObjResult().length > 0) {
            selectedTable.setVisibleColumns(tableResultCustom.getObjResult());
            selectedTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        } else {
            hlayout2.setVisible(false);
        }
        selectedTable.setPageLength(NumericConstants.SIX);
        selectedTable.setImmediate(true);
        selectedTable.setSelectable(true);
    }

    /**
     * To Configure all the Fields
     *
     * @throws SystemException
     */
    public void configureFields() throws SystemException {
        LOGGER.debug("Entering configureFields method");

        searchValue.setImmediate(true);
        final ContractUtils ifpUtils = new ContractUtils();
        searchFields.setNullSelectionAllowed(true);
        searchFields.setNullSelectionItemId(Constants.SELECT_ONE);
          

        valueList.setImmediate(true);
        valueList.setVisible(false);
        valueList.setImmediate(true);
        valueList.setNullSelectionAllowed(true);
        valueList.addItem(Constants.ZERO);
        valueList.setItemCaption(Constants.ZERO, Constants.SELECT_ONE);
        valueList.setNullSelectionItemId(Constants.ZERO);
        valueList.select(Constants.ZERO);

        searchFields.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for mass field logic and its listener
             */
            public void valueChange(final ValueChangeEvent event) {
                try {
                      availableTable.setValue(null);
                     selectedTable.setValue(null);
                    if (searchFields.getValue() != null && !String.valueOf(searchFields.getValue()).equals(StringUtils.EMPTY) | !String.valueOf(searchFields.getValue()).equals(Constants.NULL)) {
                        final String value = String.valueOf(searchFields.getValue());
                        if ("Form".equals(value)) {
                            searchValue.setVisible(false);
                            valueList.setVisible(true);
                            valueList.removeAllItems();
                            commonUtil.loadComboBox(valueList, "FORM", false);
                        } else if ("Strength".equals(value)) {
                            searchValue.setVisible(false);
                            valueList.setVisible(true);
                            valueList.removeAllItems();
                            commonUtil.loadComboBox(valueList, "STRENGTH", false);
                        } else if ("Therapeutic Class".equals(value)) {
                            searchValue.setVisible(false);
                            valueList.setVisible(true);
                            valueList.removeAllItems();
                            commonUtil.loadComboBox(valueList, "THERAPEUTIC_CLASS", false);
                        } else {
                            searchValue.setValue(StringUtils.EMPTY);
                            searchValue.setVisible(true);
                            valueList.setVisible(false);
                        }
                        if (searchFields.getValue().equals("Item Description")) {
                            searchValue.removeAllValidators();
                            String regex = "([0-9|a-z|A-Z|\\[|\\]|\\s|\\'|\\\\.|\\\\,|\\\\@|\\\\#|\\\\$|\\\\&|\\\\%||\\\\^||\\\\&||\\\\<||\\\\>||\\\\*||\\\\[||\\\\]||\\\\?||\\\\:||\\\\;||\\\\{||\\\\}||\\\\|||\\\\+||\\\\\"\"||\\\\\"||\\\\=|\\\\/|\\\\(|\\\\!|\\\\)|\\\\\\\\])*"; 
                            String regexvalue= "Allowed Special characters are  !,@,#,$,%,^,&,*,.,(,),<,>,?,/,””’,:,;,[,},{,],\\\\,|,+,=\\\"";
                            searchValue.addValidator(new RegexpValidator(regex, regexvalue));
                        }else if(!searchFields.getValue().equals("Item Description")){
                            searchValue.removeAllValidators();
                            searchValue.addValidator(new StringLengthValidator("Value should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));
                            searchValue.addValidator(new RegexpValidator("([0-9|a-z|A-Z|\\.|\\[|\\]|\\,|\\*|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*", "Allowed Special characters are @,*,#,.,$,&,_,-"));
                        }
                    } else {
                        searchFields.setValue(null);
                        searchValue.setVisible(true);
                        valueList.setVisible(false);
                    }
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                } catch (Exception ex) {
                  LOGGER.error(ex);
                }
            }
        });
        searchType.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    try {
                        searchFields.setContainerDataSource(ifpUtils.searchFields("IFP".equalsIgnoreCase(String.valueOf(searchType.getValue()))));
                        searchFields.select(Constants.SELECT_ONE);
                    } catch (Exception e) {
                       LOGGER.error(e.getMessage());
                    }
                }
            });
        LOGGER.debug("End of configureFields method");
    }

    public void focusSearchField() {
        searchFields.focus();
    }

    public void resetFields() {
        this.searchFields.setValue(null);
        this.searchValue.setValue(StringUtils.EMPTY);
        this.valueList.setValue("Disable");
    }

    /**
     * Adds the selected table.
     *
     * @return the table
     */
    public void addSelectedTableForView() {
        LOGGER.debug("Entering addSelectedTable method");
        Object[] objSelected = ContractUtils.SELECTED_ITEM_COL;
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objSelected, fieldContract, Constants.ViewMode);
        LOGGER.debug("Entering addSelectedTable method");
        availableTable.markAsDirty();
        selectedTable.setVisible(true);
        selectedTableLogic.getControlConfig().setPageLengthsAndCaptions(pageLength);
        viewSelectedTableLayout.addComponent(selectedTable);
        ResponsiveUtils.getResponsiveControls(selectedTableLogic.createControls(), controlLayout);
        viewSelectedTableLayout.addComponent(controlLayout);
        selectedTableLogic.setContainerDataSource(selectedTableContainer);
        selectedTableLogic.configureSearchData(sessionDTO);
        if (tableResultCustom.getObjResult().length > 0) {
            selectedTable.setVisibleColumns(tableResultCustom.getObjResult());
            selectedTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        } else {
            hlayout2.setVisible(false);
        }
        selectedTable.setImmediate(true);
        selectedTable.setSelectable(true);
        selectedTable.setPageLength(NumericConstants.SIX);
        selectedTableLogic.sinkItemPerPageWithPageLength(false);
        selectedTable.setImmediate(true);
        selectedTable.setSelectable(true);
        selectedTable.setFilterBarVisible(false);
        selectedTable.addStyleName("filterbar");
        selectedTable.setFilterGenerator(new IfpAvailableTableGenerator());
        selectedTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedTable.setSizeFull();
        selectedTable.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to selected table error handling.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
            }
        });
        LOGGER.debug("End of addSelectedTable method");
        
    }

    private void addResponsiveGrid(Map<String, AppPermission> fieldItemAddition) {
        try {
            List<Object> resultList = contractHL.getFieldsForSecurity(UISecurityUtil.CONTRACT_DASHBOARD, "Item Addition");
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldItemAddition, Constants.EDIT);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

}
