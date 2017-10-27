/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui.form;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.common.CommonUtil;
import com.stpl.app.accountclose.dto.GroupDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.CustomerProductDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.DataSelectionDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.ViewDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.DataSelectionLogic;
import com.stpl.app.accountclose.gtnbalancereport.logic.GTNbalanceLogic;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.AvailableCustomerTableLogic;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.AvailableProductTableLogic;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.BalanceReportLogic;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.ResultTableLogic;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.SelectedCustomerTableLogic;
import com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.SelectedProductTableLogic;
import com.stpl.app.accountclose.gtnbalancereport.ui.layout.GtnBalanceEditWindow;
import com.stpl.app.accountclose.gtnbalancereport.ui.layout.GtnBalanceViewWindow;
import com.stpl.app.accountclose.gtnbalancereport.utils.Constants;
import com.stpl.app.accountclose.gtnbalancereport.utils.DataSelectionUtil;
import com.stpl.app.accountclose.gtnbalancereport.utils.HeaderUtils;
import com.stpl.app.accountclose.lazyload.CompanyDdlbCriteria;
import com.stpl.app.accountclose.lazyload.CompanyDdlbDao;
import com.stpl.app.accountclose.lookups.PrivatePublicView;
import com.stpl.app.accountclose.lookups.SaveViewPopup;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.QUARTERLY;
import com.stpl.app.accountclose.utils.Constants.IndicatorConstants;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_CUSTOMER_GROUP;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_PRIVATE_VIEW;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_PRODUCT_GROUP;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_PUBLIC_VIEW;
import static com.stpl.app.accountclose.utils.Constants.LabelConstants.*;
import com.stpl.app.accountclose.utils.Constants.MessageConstants;
import static com.stpl.app.accountclose.utils.Constants.WindowMessagesMain.REPLACE_STRING;
import static com.stpl.app.accountclose.utils.Constants.WindowMessagesMain.UNIFIED_DELETE_MESSAGE;
import static com.stpl.app.accountclose.utils.Constants.WindowMessagesMain.UNIFIED_DELETE_NOTIFICATION;
import com.stpl.app.accountclose.utils.ResponsiveUtils;
import com.stpl.app.accountclose.utils.SessionUtil;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author santanukumar
 */
public class DataSelectionIndex extends CustomComponent implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DataSelectionIndex.class);
    /**
     * The mode option.
     */
    @UiField("modeOption")
    public OptionGroup modeOption;
    /**
     * The private view.
     */
    @UiField("privateViews")
    public CustomTextField privateView;
    /**
     * The public view.
     */
    @UiField("publicViews")
    public CustomTextField publicView;
    /**
     * The from date.
     */
    @UiField("fromDate")
    public ComboBox fromPeriod;
    /**
     * The to date.
     */
    @UiField("toDate")
    public ComboBox toPeriod;
    /**
     * The customer group.
     */
    @UiField("customerGroup")
    public CustomTextField customerGroup;
    /**
     * The available customer.
     */
    @UiField("availableCustomersTableLayout")
    public VerticalLayout availableCustomersTableLayout;
    /**
     * The add customer.
     */
    @UiField("nextBtn")
    public Button nextBtnForCustomer;
    /**
     * The remove customer.
     */
    @UiField("preBtn")
    public Button preBtnForCustomer;
    /**
     * The all btn.
     */
    @UiField("allLeftToRight")
    public Button allLeftToRightForCustomer;
    @UiField("allRightToLeft")
    public Button allRightToLeftForCustomer;
    /**
     * The selected customer.
     */
    @UiField("selectedCustomersTableLayout")
    public VerticalLayout selectedCustomersTableLayout;
    /**
     * The product group.
     */
    @UiField("productGroup")
    public CustomTextField productGroup;
    /**
     * The available product.
     */
    @UiField("pdtGroup")
    public Button pdtGroup;
    /**
     * link for product
     */
    @UiField("cusGroup")
    public Button cusGroup;
    @UiField("availableProductsTableLayout")
    public VerticalLayout availableProductsTableLayout;
    /**
     * The next btnfor product.
     */
    @UiField("nextBtnforProduct")
    public Button nextBtnforProduct;
    /**
     * The pre btnfor product.
     */
    @UiField("preBtnforProduct")
    public Button preBtnforProduct;
    /**
     * The all btnfor product.
     */
    @UiField("allLeftToRightForProduct")
    public Button allLeftToRightForProduct;
    @UiField("allRightToLeftForProduct")
    public Button allRightToLeftForProduct;
    /**
     * The selected product.
     */
    @UiField("selectedProductsTableLayout")
    public VerticalLayout selectedProductsTableLayout;
    /**
     * The generate btn.
     */
    @UiField("generateBtn")
    public Button generateBtn;
    /**
     * The search btn.
     */
    @UiField("searchBtn")
    public Button searchBtn;
    /**
     * The reset btn.
     */
    @UiField("resetBtn")
    public Button resetBtn;
    /**
     * The saveview btn.
     */
    @UiField("saveviewBtn")
    public Button saveViewBtn;
    /**
     * The deleteview btn.
     */
    @UiField("deleteviewBtn")
    public Button deleteviewBtn;
    /**
     * The result table.
     */
    @UiField("resultTableLayout")
    public VerticalLayout resultTableLayout;
    /**
     * The view btn.
     */
    @UiField("viewBtn")
    public Button viewBtn;
    @UiField("companyDdlb")
    private ComboBox companyDdlb;
    @UiField("marketType")
    private ComboBox marketTypeDdlb;
    @UiField("deductionType")
    private ComboBox deductionType;
    @UiField("deductionCatogory")
    private ComboBox deductionCatogory;
    @UiField("contractDdlb")
    private ComboBox contractDdlb;
    @UiField("product")
    private ComboBox productDdlb;
    @UiField("deductionSubType")
    private ComboBox deductionSubType;
    @UiField("reportName")
    public TextField reportName;
    @UiField("description")
    public TextField description;
    @UiField("typeDdlb")
    public ComboBox typeDdlb;
    @UiField("productIdentifier")
    public ComboBox productIdentifierDdlb;
    @UiField("accountNo")
    public TextField accountNo;
    ViewDTO viewDTO = new ViewDTO();
    /**
     * The company ddlb lazy container
     */
    public AvailableCustomerTableLogic availableCustomerTableLogic = new AvailableCustomerTableLogic();
    public SelectedCustomerTableLogic selectedCustomerTableLogic = new SelectedCustomerTableLogic();
    public AvailableProductTableLogic availableProductTableLogic = new AvailableProductTableLogic();
    public SelectedProductTableLogic selectedProductTableLogic = new SelectedProductTableLogic();
    public ResultTableLogic resultTableLogic = new ResultTableLogic();
    public ExtPagedTable availableCustomerTable = new ExtPagedTable(availableCustomerTableLogic);
    public ExtPagedTable selectedCustomerTable = new ExtPagedTable(selectedCustomerTableLogic);
    public ExtPagedTable availableProductsTable = new ExtPagedTable(availableProductTableLogic);
    public ExtPagedTable selectedProductsTable = new ExtPagedTable(selectedProductTableLogic);
    public ExtPagedTable resultTable = new ExtPagedTable(resultTableLogic);
    /**
     * Bean container for result table.
     */
    private BeanItemContainer<DataSelectionDTO> resultsContainer = new BeanItemContainer<DataSelectionDTO>(DataSelectionDTO.class);
    private BeanItemContainer<CustomerProductDTO> availableCustomerContainer = new BeanItemContainer<CustomerProductDTO>(CustomerProductDTO.class);
    private BeanItemContainer<CustomerProductDTO> selectedCustomerContainer = new BeanItemContainer<CustomerProductDTO>(CustomerProductDTO.class);
    private BeanItemContainer<CustomerProductDTO> availableProductContainer = new BeanItemContainer<CustomerProductDTO>(CustomerProductDTO.class);
    private BeanItemContainer<CustomerProductDTO> selectedProductContainer = new BeanItemContainer<CustomerProductDTO>(CustomerProductDTO.class);
    public static final String SELECT_ONE = "-Select One-";
    HelperDTO ddlbDefault = new HelperDTO(0, SELECT_ONE);
    CommonLogic commonLogic = new CommonLogic();
    DataSelectionLogic dataSelectionLogic = new DataSelectionLogic();
    SessionDTO session = new SessionDTO();
    private DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();
    GTNbalanceLogic gtnlogic = new GTNbalanceLogic();
    
    private CommonUtil commonUtils = CommonUtil.getInstance();
    private static final ResourceBundle confirmationMessage = ResourceBundle.getBundle("properties.message");
    @UiField("privateLabel")
    private Label privateLabel;
      
    @UiField("publicLabel")
    private Label publicLabel;
    
    @UiField("gridLayout")
    private GridLayout gridLayout;
    
    @UiField("privateViews")
    private CustomTextField privateViews;
    
    @UiField("publicViews")
    private CustomTextField publicViews;
    
    @UiField("modeLabel")
    private Label modeLabel;
    
    @UiField("dsLayout")
    private GridLayout dsLayout;

    /**
     * Constructor for DataSelection.
     *
     * @param dataSelectionBinder Binder for DataSelection
     * @param screenIndicator the screen indicator
     */
    public DataSelectionIndex() throws Exception {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/dataSelectionIndex.xml"), this));
        configureFields();
    }

    /**
     * Configures the components.
     */
    protected void configureFields() throws Exception {
            try{
        addAvailableCustomersTable();
        addSelectedCustomersTable();
        addAvailableProductsTable();
        addSelectedProductsTable();
        addResultsTable();

         commonUtils.loadComboBox(deductionCatogory, "RS_CATEGORY", false);
         commonUtils.loadComboBox(marketTypeDdlb, "CONTRACT_TYPE", false);
             
        availableCustomerTableLogic.setContainerDataSource(availableCustomerContainer);
        availableCustomerTableLogic.setPageLength(5);
        availableCustomerTableLogic.sinkItemPerPageWithPageLength(false);

        selectedCustomerTableLogic.setContainerDataSource(selectedCustomerContainer);
        selectedCustomerTableLogic.setPageLength(5);
        selectedCustomerTableLogic.sinkItemPerPageWithPageLength(false);

        availableProductTableLogic.setContainerDataSource(availableProductContainer);
        availableProductTableLogic.setPageLength(5);
        availableProductTableLogic.sinkItemPerPageWithPageLength(false);

        selectedProductTableLogic.setContainerDataSource(selectedProductContainer);
        selectedProductTableLogic.setPageLength(5);
        selectedProductTableLogic.sinkItemPerPageWithPageLength(false);

        resultTableLogic.setContainerDataSource(resultsContainer);
        resultTableLogic.setPageLength(5);
        resultTableLogic.sinkItemPerPageWithPageLength(false);

        viewBtn.setEnabled(false);
        modeOption.setImmediate(true);
        modeOption.addItem(MODE_ADD.getConstant());
        modeOption.addItem(MODE_SEARCH.getConstant());
        modeOption.select(MODE_ADD.getConstant());

        fromPeriod.setImmediate(true);
        fromPeriod.setNullSelectionAllowed(false);
        toPeriod.setImmediate(true);
        toPeriod.setNullSelectionAllowed(false);
        toPeriod.setEnabled(true);

        resultTable.setVisibleColumns(HeaderUtils.DATA_SELECTION_RESULTS_COLUMNS);
        resultTable.setColumnHeaders(HeaderUtils.DATA_SELECTION_RESULTS_HEADERS);
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setFilterBarVisible(true);
        resultTable.setSelectable(true);

        availableCustomerTable.setVisibleColumns(HeaderUtils.AVAILABLE_SELECTED_CUSTOMERS_COLUMNS);
        availableCustomerTable.setColumnHeaders(HeaderUtils.AVAILABLE_SELECTED_CUSTOMERS_HEADERS);
        availableCustomerTable.setImmediate(true);
        availableCustomerTable.setWidth("500px");
        availableCustomerTable.setFilterGenerator(new ExtFilterGenerator() {
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                return null;
            }

            public void filterRemoved(Object propertyId) {
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                return null;
            }
        });
        availableCustomerTable.setFilterBarVisible(true);
        availableCustomerTable.setFilterDecorator(new ExtDemoFilterDecorator());
        availableCustomerTable.setSelectable(true);

        selectedCustomerTable.setVisibleColumns(HeaderUtils.AVAILABLE_SELECTED_CUSTOMERS_COLUMNS);
        selectedCustomerTable.setColumnHeaders(HeaderUtils.AVAILABLE_SELECTED_CUSTOMERS_HEADERS);
        selectedCustomerTable.setImmediate(true);
        selectedCustomerTable.setWidth("500px");
        selectedCustomerTable.setFilterGenerator(new ExtFilterGenerator() {
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                return null;
            }

            public void filterRemoved(Object propertyId) {
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                return null;
            }
        });
        selectedCustomerTable.setFilterBarVisible(true);
        selectedCustomerTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedCustomerTable.setSelectable(true);

        availableProductsTable.setVisibleColumns(HeaderUtils.AVAILABLE_SELECTED_PRODUCTS_COLUMNS);
        availableProductsTable.setColumnHeaders(HeaderUtils.AVAILABLE_SELECTED_PRODUCTS_HEADERS);
        availableProductsTable.setImmediate(true);
        availableProductsTable.setWidth("500px");
        availableProductsTable.setFilterGenerator(new ExtFilterGenerator() {
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                return null;
            }

            public void filterRemoved(Object propertyId) {
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                return null;
            }
        });
        availableProductsTable.setFilterBarVisible(true);
        availableProductsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        availableProductsTable.setSelectable(true);

        selectedProductsTable.setVisibleColumns(HeaderUtils.AVAILABLE_SELECTED_PRODUCTS_COLUMNS);
        selectedProductsTable.setColumnHeaders(HeaderUtils.AVAILABLE_SELECTED_PRODUCTS_HEADERS);
        selectedProductsTable.setImmediate(true);
        selectedProductsTable.setWidth("500px");
        selectedProductsTable.setFilterGenerator(new ExtFilterGenerator() {
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                return null;
            }

            public void filterRemoved(Object propertyId) {
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                return null;
            }
        });
        selectedProductsTable.setFilterBarVisible(true);
        selectedProductsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedProductsTable.setSelectable(true);

        loadCompanyDdlb();
        session = SessionUtil.createSession();
        DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(), "DS", QUARTERLY.getConstant());
        modeChangeMethod();
        typeDdlb.setImmediate(true);
        typeDdlb.setNullSelectionAllowed(false);
        typeDdlb.addItem(Constants.VariableConstants.SELECT_ONE.getConstant());
        typeDdlb.setValue(Constants.VariableConstants.SELECT_ONE.getConstant());

        productIdentifierDdlb.addItem(IndicatorConstants.SELECT_ONE.getConstant());
        productIdentifierDdlb.setNullSelectionAllowed(true);
        productIdentifierDdlb.setNullSelectionItemId(IndicatorConstants.SELECT_ONE.getConstant());
        productIdentifierDdlb.addItem(IndicatorConstants.NDC8.getConstant());
        productIdentifierDdlb.addItem(IndicatorConstants.NDC10.getConstant());
        productIdentifierDdlb.addItem(IndicatorConstants.NDC11.getConstant());
        productIdentifierDdlb.setValue(IndicatorConstants.SELECT_ONE.getConstant());
        
            dsLayout.setComponentAlignment(modeLabel, Alignment.MIDDLE_LEFT);
            dsLayout.setComponentAlignment(modeOption, Alignment.MIDDLE_LEFT);
            gridLayout.setComponentAlignment(privateLabel, Alignment.MIDDLE_LEFT);
            gridLayout.setComponentAlignment(privateViews, Alignment.MIDDLE_LEFT);
            gridLayout.setComponentAlignment(publicLabel, Alignment.MIDDLE_LEFT);
            gridLayout.setComponentAlignment(publicViews,Alignment.MIDDLE_LEFT);
            
            
        loadTypeDdlb();
    }catch(Exception e){
        LOGGER.error(e);
    }
    }

    /**
     * Enter.
     *
     * @param event the event
     */
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @UiHandler("privateViews")
    public void privateViewLookup(CustomTextField.ClickEvent event) {
        final PrivatePublicView privateViewLookup = new PrivatePublicView(INDICATOR_PRIVATE_VIEW.getConstant(), privateView, PRIVATE_VIEW.getConstant());
        UI.getCurrent().addWindow(privateViewLookup);
        privateViewLookup.setImmediate(true);
        privateViewLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (privateViewLookup.getViewDTO() != null) {
                    try {
                        ViewDTO viewDTO = privateViewLookup.getViewDTO();
                        setViewDTO(viewDTO);
                        loadViewData(viewDTO);
                        privateView.setValue(viewDTO.getViewName().trim());
                        publicView.setValue(StringUtils.EMPTY);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }

            }
        });
    }

    @UiHandler("publicViews")
    public void publicViewLookup(CustomTextField.ClickEvent event) {
        final PrivatePublicView privateViewLookup = new PrivatePublicView(INDICATOR_PUBLIC_VIEW.getConstant(), publicView, PUBLIC_VIEW.getConstant());
        UI.getCurrent().addWindow(privateViewLookup);
        privateViewLookup.setImmediate(true);
        privateViewLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (privateViewLookup.getViewDTO() != null) {
                    try {
                        ViewDTO viewDTO = privateViewLookup.getViewDTO();
                        setViewDTO(viewDTO);
                        loadViewData(viewDTO);
                        publicView.setValue(viewDTO.getViewName().trim());
                        privateView.setValue(StringUtils.EMPTY);

                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }

            }
        });
    }

    @UiHandler("saveviewBtn")
    public void saveViewBtn(Button.ClickEvent event) {
        LOGGER.info("Entering saveViewBtn method");
        if (CommonUtil.emptyCheck(reportName.getValue()) || CommonUtil.emptyCheck(description.getValue()) || CommonUtil.emptyCheck(String.valueOf(fromPeriod.getValue()))
                || CommonUtil.emptyCheck(String.valueOf(toPeriod.getValue())) || CommonUtil.emptyCheck(String.valueOf(marketTypeDdlb.getValue()))
                || CommonUtil.emptyCheck(String.valueOf(deductionType.getValue())) || CommonUtil.emptyCheck(String.valueOf(deductionSubType.getValue()))
                || CommonUtil.emptyCheck(String.valueOf(companyDdlb.getValue()))) {
            bindDataSelectionDtoToSave();
            final SaveViewPopup saveViewPopup = new SaveViewPopup(dataSelectionDTO, viewDTO);
            UI.getCurrent().addWindow(saveViewPopup);
        } else {
            AbstractNotificationUtils.getErrorNotification(confirmationMessage.getString("BR_MSG_ID_026"),  confirmationMessage.getString("MSG_ID_016"));
            return;
        }
       

        LOGGER.info("Ending saveViewBtn method");
    }

    /**
     * Detach listeners.
     *
     * @param field the field
     */
    public void detachListeners(final AbstractField field) {

        List<Property.ValueChangeListener> listeners;

        listeners = (List<Property.ValueChangeListener>) field.getListeners(Property.ValueChangeEvent.class);
        for (final Property.ValueChangeListener object : listeners) {
            field.removeValueChangeListener(object);
        }

    }

    @UiHandler("generateBtn")
    public void generateFunction(Button.ClickEvent event) throws Exception {
        try {
            if (CommonUtil.emptyCheck(reportName.getValue()) && CommonUtil.emptyCheck(description.getValue()) && CommonUtil.emptyCheck(String.valueOf(fromPeriod.getValue()))
                    && CommonUtil.emptyCheck(String.valueOf(toPeriod.getValue())) && CommonUtil.emptyCheck(String.valueOf(marketTypeDdlb.getValue()))
                    && CommonUtil.emptyCheck(String.valueOf(deductionType.getValue())) && CommonUtil.emptyCheck(String.valueOf(deductionSubType.getValue()))
                    && CommonUtil.emptyCheck(String.valueOf(companyDdlb.getValue())) && selectedCustomerTable.size() > 0 && selectedProductsTable.size() > 0) {
                if (isDuplicateReportName(reportName.getValue())) {

                    AbstractNotificationUtils.getErrorNotification(confirmationMessage.getString("BR_MSG_ID_025"),confirmationMessage.getString("MSG_ID_017"));

                } else {

                    bindDataSelectionDtoToSave();
                    int acctCloserMasterId = gtnlogic.saveOrUpdateGtnReport(dataSelectionDTO,StringUtils.EMPTY);
                    VaadinSession.getCurrent().setAttribute(Constants.VariableConstants.ACCT_CLOSER_MASTERID.getConstant(), acctCloserMasterId);
                    if (acctCloserMasterId != 0) {
                        session.setAction("EDIT");
                        session.setAcctCloserMasterId(acctCloserMasterId);
                        session.setDsFromDate(dataSelectionDTO.getFromDate());
                        session.setDsToDate(dataSelectionDTO.getToDate());
                        session.setDataSelectionDTO(dataSelectionDTO);
                        dataSelectionLogic.insertToAccCloserDetails(session);
                        BalanceReportLogic.callGtnProcedure(acctCloserMasterId, session.getUserId(), session.getSessionId());
                        GtnBalanceEditWindow editWindow = new GtnBalanceEditWindow(session);
                        UI.getCurrent().addWindow(editWindow);
                    }
                }

            } else {
                AbstractNotificationUtils.getErrorNotification(confirmationMessage.getString("BR_MSG_ID_026"),confirmationMessage.getString("MSG_ID_016"));
                return;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    private void addAvailableCustomersTable() {
        availableCustomersTableLayout.addComponent(availableCustomerTable);
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(availableCustomerTableLogic.createControls());
        availableCustomersTableLayout.addComponent(controls);
    }

    private void addSelectedCustomersTable() {
        selectedCustomersTableLayout.addComponent(selectedCustomerTable);
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(selectedCustomerTableLogic.createControls());
        selectedCustomersTableLayout.addComponent(controls);
    }

    private void addAvailableProductsTable() {
        availableProductsTableLayout.addComponent(availableProductsTable);
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(availableProductTableLogic.createControls());
        availableProductsTableLayout.addComponent(controls);
    }

    private void addSelectedProductsTable() {
        selectedProductsTableLayout.addComponent(selectedProductsTable);
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(selectedProductTableLogic.createControls());
        selectedProductsTableLayout.addComponent(controls);
    }

    private void addResultsTable() {
        resultTableLayout.addComponent(resultTable);
        HorizontalLayout controls = ResponsiveUtils.getResponsiveControls(resultTableLogic.createControls());
        resultTableLayout.addComponent(controls);
    }

    @UiHandler("customerGroup")
    public void customerGroup(CustomTextField.ClickEvent event) {
        final CustomerGroupLookup prodGroupLookupWindow = new CustomerGroupLookup(INDICATOR_CUSTOMER_GROUP.getConstant(), "Customer Group Lookup", customerGroup, StringUtils.EMPTY);
        UI.getCurrent().addWindow(prodGroupLookupWindow);
        prodGroupLookupWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (prodGroupLookupWindow.getGroupDTO() != null) {
                    GroupDTO groupDTO = prodGroupLookupWindow.getGroupDTO();
                    customerGroup.setValue(groupDTO.getCustomerGroupName().trim());
                    dataSelectionDTO.setCustomerGRpSid(Integer.valueOf(groupDTO.getCustomerGroupSid()));
                }
            }
        });
    }

    @UiHandler("cusGroup")
    public void custgrpLink(Button.ClickEvent event) {
        final CustomerGroupLookup prodGroupLookupWindow = new CustomerGroupLookup(INDICATOR_CUSTOMER_GROUP.getConstant(), "Customer Group Lookup", customerGroup, StringUtils.EMPTY);
        UI.getCurrent().addWindow(prodGroupLookupWindow);
        prodGroupLookupWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (prodGroupLookupWindow.getGroupDTO() != null) {
                    GroupDTO groupDTO = prodGroupLookupWindow.getGroupDTO();
                    customerGroup.setValue(groupDTO.getCustomerGroupName().trim());
                    dataSelectionDTO.setCustomerGRpSid(Integer.valueOf(groupDTO.getCustomerGroupSid()));
                }
            }
        });
    }

    /**
     * Product Group Popup
     *
     * @param event
     */
    @UiHandler("productGroup")
    public void productGroup(CustomTextField.ClickEvent event) {
        final ProductGroupLookup prodGroupLookupWindow = new ProductGroupLookup(INDICATOR_PRODUCT_GROUP.getConstant(), "Product Group Lookup", productGroup, StringUtils.EMPTY);
        UI.getCurrent().addWindow(prodGroupLookupWindow);
        prodGroupLookupWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (prodGroupLookupWindow.getGroupDTO() != null) {
                    GroupDTO groupDTO = prodGroupLookupWindow.getGroupDTO();
                    productGroup.setValue(groupDTO.getProductGroupName().trim());
                    dataSelectionDTO.setProductGrpSid(Integer.valueOf(groupDTO.getProductGroupSid()));
                }
            }
        });
    }

    @UiHandler("pdtGroup")
    public void pdtgrpLink(Button.ClickEvent event) {
        final ProductGroupLookup prodGroupLookupWindow = new ProductGroupLookup(INDICATOR_PRODUCT_GROUP.getConstant(), "Product Group Lookup", productGroup, StringUtils.EMPTY);
        UI.getCurrent().addWindow(prodGroupLookupWindow);
        prodGroupLookupWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (prodGroupLookupWindow.getGroupDTO() != null) {
                    GroupDTO groupDTO = prodGroupLookupWindow.getGroupDTO();
                    productGroup.setValue(groupDTO.getProductGroupName().trim());
                    dataSelectionDTO.setProductGrpSid(Integer.valueOf(groupDTO.getProductGroupSid()));
                }
            }
        });
    }

    /**
     * Load Company Ddlb
     *
     */
    private void loadCompanyDdlb() throws Exception {
        LOGGER.info("Entering loadCompanyDdlb method");
        detachListeners(companyDdlb);
        companyDdlb = commonLoadingDdlb(companyDdlb, "0", "gtncompanyForm");
        attachListeners(companyDdlb, "companyDdlb");
        LOGGER.info("End of loadCompanyDdlb method");
    }

    public void loadMarketDdlb() throws Exception {
        LOGGER.info("Entering loadMarketDdlb method");
              commonUtils.loadComboBox(marketTypeDdlb, "CONTRACT_TYPE", false);
        LOGGER.info("End of loadMarketDdlb method");
    }

    @UiHandler("marketType")
    public void loadDiscDdlb(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadDiscDdlb method");
        String id = String.valueOf(((HelperDTO)marketTypeDdlb.getValue()).getId());
        detachListeners(deductionType);
        deductionType = commonLoadingDdlb(deductionType, String.valueOf(id == null ? "0" : id), "acctTypeForm");
        attachListeners(deductionType, "deductionType");
        LOGGER.info("End of loadDiscDdlb method");
    }

    @UiHandler("deductionType")
    public void loadDiscSubDdlb(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadDiscSubDdlb method");
        String id = (String.valueOf(deductionType.getValue()));
        detachListeners(deductionSubType);
        deductionSubType = commonLoadingDdlb(deductionSubType, id, "acctSubTypeForm");
        attachListeners(deductionSubType, "deductionSubType");
        LOGGER.info("End of loadDiscSubDdlb method");
    }

    @UiHandler("deductionType")
    public void loadcontractDdlb(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadcontractDdlb method");
        String id = (String.valueOf(deductionType.getValue()));
        detachListeners(contractDdlb);
        contractDdlb = commonLoadingDdlb(contractDdlb, id, "contractForm");
        attachListeners(contractDdlb, "contractDdlb");
        LOGGER.info("End of loadcontractDdlb method");
    }

    @UiHandler("deductionType")
    public void loadProductDdlb(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadcontractDdlb method");
        String id = (String.valueOf(deductionType.getValue()));
        productDdlb = commonLoadingDdlb(productDdlb, id, "productForm");
        LOGGER.info("End of loadcontractDdlb method");
    }

    @UiHandler("deductionType")
    public void loadAvailableCustomers(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadcontractDdlb method");
        loadAvailableCustomersBasedOnFilter();
        LOGGER.info("End of loadcontractDdlb method");
    }

    @UiHandler("deductionSubType")
    public void loadAvailableCustomersBasedOnDeductionSubType(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadcontractDdlb method");
        loadAvailableCustomersBasedOnFilter();
        LOGGER.info("End of loadcontractDdlb method");
    }

    @UiHandler("contractDdlb")
    public void loadAvailableCustomersBasedOnContract(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadcontractDdlb method");
        loadAvailableCustomersBasedOnFilter();
        LOGGER.info("End of loadcontractDdlb method");
    }

    public ComboBox commonLoadingDdlb(ComboBox comboBox, final String id, final String query) throws Exception {
        List<String> ids = new ArrayList<String>();
        if (!"0".equals(id)) {
            ids.add(id);
        }
        List<HelperDTO> dtos = commonLogic.getDdlbList(query, ids, false);
        comboBox = CommonLogic.getComboBox(comboBox, dtos);
        comboBox.setNullSelectionAllowed(false);
        return comboBox;
    }

    public void attachListeners(final AbstractField field, final String component) {

        field.setImmediate(true);
        field.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                try {
                    if (component.equals("deductionType")) {
                        loadDiscSubDdlb(event);
                        loadcontractDdlb(event);
                        loadProductDdlb(event);
                        loadAvailableCustomers(event);
                    } else if (component.equals("deductionSubType")) {
                        loadAvailableCustomersBasedOnDeductionSubType(event);
                    } else if (component.equals("contractDdlb")) {
                        loadAvailableCustomersBasedOnContract(event);
                    } else if (component.equals("companyDdlb")) {
                        loadAvailableProducts(event);
                    }

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });

    }

    public void loadAvailableCustomersBasedOnFilter() {
        String mkTypeId = String.valueOf(marketTypeDdlb.getValue() == null ? "0" : ((HelperDTO)marketTypeDdlb.getValue()).getId());
        String deductionTypeId = String.valueOf(deductionType.getValue());
        String deductionSubTypeId = String.valueOf(deductionSubType.getValue());
        String contractId = String.valueOf(contractDdlb.getValue());
        CustomerProductDTO cpdto = new CustomerProductDTO();
        cpdto.setMarketTypeId(mkTypeId);
        cpdto.setDeductionTypeId(deductionTypeId);
        cpdto.setDeductionSubTypeId(deductionSubTypeId);
        cpdto.setContractId(contractId);
        availableCustomerTableLogic.loadSetData(cpdto, session, false);
    }

    public static CustomerProductDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof CustomerProductDTO) {
            targetItem = new BeanItem<CustomerProductDTO>((CustomerProductDTO) obj);
        }

        return (CustomerProductDTO) targetItem.getBean();
    }

    @UiHandler("nextBtn")
    public void moveLeftToRight(Button.ClickEvent event) {
        if (availableCustomerTable.getValue() == null) {
        } else {
            Object selectedItem = availableCustomerTable.getValue();
            CustomerProductDTO customerProductDTO = getBeanFromId(selectedItem);
            customerProductDTO.setIdenfier("customer");
            dataSelectionLogic.insertIntoTempTable(customerProductDTO, session);
            selectedCustomerTableLogic.loadSetData(customerProductDTO, session, false);
            availableCustomerContainer.removeItem(selectedItem);
        }
    }

    @UiHandler("allLeftToRight")
    public void allLeftToRightForCustomer(Button.ClickEvent event) {
        CustomerProductDTO customerProductDTO = new CustomerProductDTO();
        List<CustomerProductDTO> report = availableCustomerContainer.getItemIds();
        for (CustomerProductDTO dto : report) {
            customerProductDTO = getBeanFromId(dto);
            customerProductDTO.setIdenfier("customer");
            dataSelectionLogic.insertIntoTempTable(customerProductDTO, session);
    }
        selectedCustomerTableLogic.loadSetData(customerProductDTO, session, false);
        availableCustomerContainer.removeAllItems();
    }

    @UiHandler("preBtn")
    public void moveRightToLeft(Button.ClickEvent event) {
        if (selectedCustomerTable.getValue() == null) {
        } else {
            Object selectedItem = selectedCustomerTable.getValue();
            CustomerProductDTO customerProductDTO = getBeanFromId(selectedItem);
            customerProductDTO.setIdenfier("customer");
            dataSelectionLogic.removeSelectedCP(session, customerProductDTO);
            availableCustomerContainer.addBean(customerProductDTO);
            selectedCustomerTableLogic.loadSetData(customerProductDTO, session, false);

        }
    }

    @UiHandler("allRightToLeft")
    public void allRightToLeftForCustomer(Button.ClickEvent event) {
        List<CustomerProductDTO> report = new ArrayList<CustomerProductDTO>();
        CustomerProductDTO customerProductDTO = new CustomerProductDTO();
        report = selectedCustomerContainer.getItemIds();
        if (!report.isEmpty()) {
            for (CustomerProductDTO dto : report) {
                customerProductDTO = getBeanFromId(dto);
                customerProductDTO.setIdenfier("customer");
                dataSelectionLogic.removeSelectedCP(session, customerProductDTO);
                availableCustomerContainer.addBean(customerProductDTO);
            }
            selectedCustomerTableLogic.loadSetData(customerProductDTO, session, false);
        }
    }

    @UiHandler("companyDdlb")
    public void loadAvailableProducts(Property.ValueChangeEvent event) throws Exception {
        LOGGER.info("Entering loadAvailableProducts method");
        loadAvailableProductsBasedOnFilter();
        LOGGER.info("End of loadAvailableProducts method");
    }

    private void loadAvailableProductsBasedOnFilter() {
        String mkTypeId = String.valueOf(marketTypeDdlb.getValue() == null ? "0" : marketTypeDdlb.getValue());
        String deductionTypeId = (String.valueOf(deductionType.getValue()));
        String deductionSubTypeId = (String.valueOf(deductionSubType.getValue()));
        String contractId = (String.valueOf(contractDdlb.getValue()));
        CustomerProductDTO cpdto = new CustomerProductDTO();
        cpdto.setMarketTypeId(mkTypeId);
        cpdto.setDeductionTypeId(deductionTypeId);
        cpdto.setDeductionSubTypeId(deductionSubTypeId);
        cpdto.setContractId(contractId);
        String companyId = String.valueOf(companyDdlb.getValue() == null ? "0" : companyDdlb.getValue());
        cpdto.setCompanyMasterSid(companyId);
        availableProductTableLogic.loadSetData(cpdto, session, false);
    }

    @UiHandler("nextBtnforProduct")
    public void moveLeftToRightForProduct(Button.ClickEvent event) {
        if (availableProductsTable.getValue() == null) {
        } else {
            Object selectedItem = availableProductsTable.getValue();
            CustomerProductDTO customerProductDTO = getBeanFromId(selectedItem);
            customerProductDTO.setIdenfier("product");
            dataSelectionLogic.insertIntoTempTable(customerProductDTO, session);
            selectedProductTableLogic.loadSetData(customerProductDTO, session, false);
            availableProductContainer.removeItem(selectedItem);
        }
    }

    @UiHandler("allLeftToRightForProduct")
    public void allLeftToRightForProduct(Button.ClickEvent event) {
        CustomerProductDTO customerProductDTO = new CustomerProductDTO();
        List<CustomerProductDTO> report = new ArrayList<CustomerProductDTO>();
        report = availableProductContainer.getItemIds();
        if (!report.isEmpty()) {
            for (CustomerProductDTO dto : report) {
                customerProductDTO = getBeanFromId(dto);
                customerProductDTO.setIdenfier("product");
                dataSelectionLogic.insertIntoTempTable(customerProductDTO, session);
            }
            selectedProductTableLogic.loadSetData(customerProductDTO, session, false);
            availableProductContainer.removeAllItems();
        }
    }

    @UiHandler("preBtnforProduct")
    public void moveRightToLeftForProduct(Button.ClickEvent event) {
        if (selectedProductsTable.getValue() == null) {
        } else {
            Object selectedItem = selectedProductsTable.getValue();
            CustomerProductDTO customerProductDTO = getBeanFromId(selectedItem);
            customerProductDTO.setIdenfier("product");
            dataSelectionLogic.removeSelectedCP(session, customerProductDTO);
            availableProductContainer.addBean(customerProductDTO);
            selectedProductTableLogic.loadSetData(customerProductDTO, session, false);
        }
    }

    @UiHandler("allRightToLeftForProduct")
    public void allRightToLeftForProduct(Button.ClickEvent event) {
        List<CustomerProductDTO> report = new ArrayList<CustomerProductDTO>();
        CustomerProductDTO customerProductDTO = new CustomerProductDTO();
        report = selectedProductContainer.getItemIds();
        if (!report.isEmpty()) {
            for (CustomerProductDTO dto : report) {
                customerProductDTO = getBeanFromId(dto);
                customerProductDTO.setIdenfier("product");
                dataSelectionLogic.removeSelectedCP(session, customerProductDTO);
                availableProductContainer.addBean(customerProductDTO);
            }
            selectedProductTableLogic.loadSetData(customerProductDTO, session, false);
        }
    }

    public void modeChangeMethod() {
        modeOption.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            public void valueChange(Property.ValueChangeEvent event) {
                try {
                    if (MODE_ADD.getConstant().equals(String.valueOf(modeOption.getValue()))) {
                        resetButtonLogic();
                        resetTables();
                        searchBtn.setEnabled(false);
                        generateBtn.setEnabled(true);
                        saveViewBtn.setEnabled(true);
                        resetBtn.setEnabled(true);
                        viewBtn.setEnabled(false);
                        toPeriod.setEnabled(true);
                        DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(), "DS", QUARTERLY.getConstant());

                    } else {
                        resetButtonLogic();
                        resetTables();
                        generateBtn.setEnabled(false);
                        searchBtn.setEnabled(true);
                        resetBtn.setEnabled(true);
                        viewBtn.setEnabled(true);
                        saveViewBtn.setEnabled(true);
                        toPeriod.setEnabled(true);
                        DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_SEARCH.getConstant(), "DS", QUARTERLY.getConstant());
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
    }

    public void resetTables() {
        CustomerProductDTO customerProductDTO = new CustomerProductDTO();
        selectedProductTableLogic.loadSetData(customerProductDTO, session, true);
        selectedCustomerTableLogic.loadSetData(customerProductDTO, session, true);
        availableProductTableLogic.loadSetData(customerProductDTO, session, true);
        availableCustomerTableLogic.loadSetData(customerProductDTO, session, true);
    }

    @UiHandler("resetBtn")
    public void resetBtnLogic(Button.ClickEvent event) throws Exception {

        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    resetButtonLogic();
                    resetTables();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage(confirmationMessage.getString("MSG_ID_001"),confirmationMessage.getString("MSG_ID_018"));

    }

    @UiHandler("searchBtn")
    public void searchButton(Button.ClickEvent event) throws SystemException, PortalException, Exception {
        if (CommonUtil.emptyCheck(reportName.getValue()) && CommonUtil.emptyCheck(description.getValue())
                && (CommonUtil.emptyCheck(marketTypeDdlb.getValue()))
                && (CommonUtil.emptyCheck(companyDdlb.getValue()))
                && (CommonUtil.emptyCheck(productGroup.getValue()))
                && (CommonUtil.emptyCheck(deductionType.getValue()))
                && (CommonUtil.emptyCheck(customerGroup.getValue()))
                && (CommonUtil.emptyCheck(productDdlb.getValue()))
                && (CommonUtil.emptyCheck(deductionSubType.getValue()))
                && (CommonUtil.emptyCheck(contractDdlb.getValue()))
                && (CommonUtil.emptyCheck(productIdentifierDdlb.getValue()))
                && (CommonUtil.emptyCheck(fromPeriod.getValue()))
                && (CommonUtil.emptyCheck(toPeriod.getValue()))) {
            MessageBox.showPlain(Icon.INFO, confirmationMessage.getString("BR_MSG_ID_026"), confirmationMessage.getString("MSG_ID_019"), ButtonId.OK);
        } else {
            bindSearchFields();

            if (!resultTableLogic.fireSetData(dataSelectionDTO, false)) {

                AbstractNotificationUtils.getErrorNotification(confirmationMessage.getString("BR_MSG_ID_026"),
                        confirmationMessage.getString("MSG_ID_020"));
            } else {
                Notification.show("Search Completed");
            }
        }
    }

    private void bindDataSelectionDtoToSave() {
        try {
            SimpleDateFormat format = new SimpleDateFormat(Constants.DateFormatConstants.MMddyyyy.getConstant());
            if (companyDdlb.getValue() != null) {
             
                dataSelectionDTO.setCompanySid(String.valueOf(companyDdlb.getValue()));
            } else {
                dataSelectionDTO.setCompanySid("0");
            }
            if (CommonUtil.emptyCheck(fromPeriod.getValue())) {
                if (SELECT_ONE.equals(String.valueOf(fromPeriod.getValue())) && MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
                    dataSelectionDTO.setFromPeriod((String.valueOf(fromPeriod.getValue())));
                    dataSelectionDTO.setFromDate(null);
                } else {
                    dataSelectionDTO.setFromPeriod(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
                    dataSelectionDTO.setFromDate(format.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
                }
            }
            if (CommonUtil.emptyCheck(toPeriod.getValue())) {
                if (SELECT_ONE.equals(String.valueOf(toPeriod.getValue())) && MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
                    dataSelectionDTO.setToPeriod((String.valueOf(toPeriod.getValue())));
                    dataSelectionDTO.setToDate(null);
                } else {
                    dataSelectionDTO.setToPeriod(DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue())));
                    dataSelectionDTO.setToDate(format.parse(DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue()))));
                }
            }
            if (marketTypeDdlb.getValue() != null) {
               HelperDTO marketType = (HelperDTO) marketTypeDdlb.getValue();
                dataSelectionDTO.setMarketType(String.valueOf(marketType.getId()));
            }
            if (deductionType.getValue() != null) {
                dataSelectionDTO.setDeductionType(String.valueOf(deductionType.getValue()));
            }
            if (deductionSubType.getValue() != null) {
                dataSelectionDTO.setDeductionSubType(String.valueOf(deductionSubType.getValue()));
            }
            if (deductionCatogory.getValue() != null) {
                HelperDTO dedCat = (HelperDTO) deductionCatogory.getValue();
                dataSelectionDTO.setDeductionCategory(String.valueOf(dedCat.getId()));
            }
        
            if (contractDdlb.getValue() != null) {
                dataSelectionDTO.setContractSid(String.valueOf(contractDdlb.getValue()));
            }
            if (productDdlb.getValue() != null) {
                dataSelectionDTO.setItemSid(String.valueOf(productDdlb.getValue()));
            }
            if (productIdentifierDdlb.getValue() != null) {
                dataSelectionDTO.setProductIdentifier(String.valueOf(productIdentifierDdlb.getValue()));
            }
            if (productGroup.getValue() != null && !productGroup.getValue().isEmpty()) {
                dataSelectionDTO.setProductGroup(productGroup.getValue());
            }
            if (customerGroup.getValue() != null && !customerGroup.getValue().isEmpty()) {
                dataSelectionDTO.setCustomerGroup(customerGroup.getValue());
            }
            session.setReportName(reportName.getValue());
            dataSelectionDTO.setReportName(reportName.getValue());
            dataSelectionDTO.setDescription(description.getValue());
            dataSelectionDTO.setAccountNo(accountNo.getValue());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public ViewDTO getViewDTO() {
        return viewDTO;
    }

    public void setViewDTO(ViewDTO viewDTO) {
        this.viewDTO = viewDTO;
    }

    @UiHandler("deleteviewBtn")
    public void deleteviewBtnLogic(Button.ClickEvent event) {

        final ViewDTO dto = getViewDTO();
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
            }

            @Override
            public void yesMethod() {
                try {
                    if (dto != null) {
                        final String flag = DataSelectionLogic.deleteView(dto.getViewMasterId());
                        if (!Constants.FAIL.equals(flag)) {
                            CommonUIUtils.getMessageNotification(UNIFIED_DELETE_NOTIFICATION.getConstant().replace(REPLACE_STRING.getConstant(), flag));
                        }
                    }
                } catch (Exception exception) {
                    LOGGER.error(exception);
                }
            }
        }.getConfirmationMessage(MessageConstants.CONFIRM_DELETION_TITLE.getConstant(), UNIFIED_DELETE_MESSAGE.getConstant().replace(REPLACE_STRING.getConstant(), dto.getViewName()));

    }

    @UiHandler("viewBtn")
    public void viewBtn(Button.ClickEvent event) {
        try {
            if (resultTable.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(confirmationMessage.getString("BR_MSG_ID_027"),confirmationMessage.getString("MSG_ID_021"));
            } else {

                DataSelectionDTO dto = (DataSelectionDTO) resultTable.getValue();
                int accountIdValue = Integer.valueOf(dto.getAccountClosureSid());
                SessionUtil sessionUtil = new SessionUtil();
                session = sessionUtil.createSession();
                session.setAcctCloserMasterId(accountIdValue);
                session.setReportName(dto.getReportName());
                session.setAction("VIEW");
                session.setDsFromDate(dto.getFromDate());
                session.setDsToDate(dto.getToDate());
                session.setDataSelectionDTO(dto);
                BalanceReportLogic.callGtnProcedure(accountIdValue, session.getUserId(), session.getSessionId());
                GtnBalanceViewWindow viewWindow = new GtnBalanceViewWindow(dto.getReportName(), session);
                UI.getCurrent().addWindow(viewWindow);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     *
     * @param value
     * @return
     */
    private boolean isDuplicateReportName(String value) throws SystemException, Exception {
        LOGGER.info("Entering isDuplicateReportName method with report name " + value);
        return dataSelectionLogic.isDuplicateReportName(value);
    }

    public void resetButtonLogic() {
        reportName.setValue(StringUtils.EMPTY);
        description.setValue(StringUtils.EMPTY);
        accountNo.setValue(StringUtils.EMPTY);
        typeDdlb.setValue("0");
        marketTypeDdlb.setValue("0");
        deductionType.setValue("0");
        deductionSubType.setValue("0");
        companyDdlb.setValue("0");
        productIdentifierDdlb.setValue(null);
        customerGroup.setValue(StringUtils.EMPTY);
        productGroup.setValue(StringUtils.EMPTY);
        contractDdlb.setValue("0");
        productDdlb.setValue("0");
        publicView.setValue(StringUtils.EMPTY);
        privateView.setValue(StringUtils.EMPTY);
        viewDTO = new ViewDTO();
        dataSelectionDTO = new DataSelectionDTO();
        DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(), "DS", QUARTERLY.getConstant());
    }

    private void loadTypeDdlb() throws Exception {
        LOGGER.info("Entering loadTypeDdlb method ");
        typeDdlb = CommonLogic.getComboBox(typeDdlb, CommonLogic.getDropDownList("ADJUSTMENT_TYPE"));
        LOGGER.info("Entering loadTypeDdlb method ");
    }

    private void bindSearchFields() {
        try {
            SimpleDateFormat format = new SimpleDateFormat(Constants.DateFormatConstants.MMddyyyy.getConstant());
            if (companyDdlb.getValue() != null) {
                dataSelectionDTO.setCompanySid(String.valueOf(companyDdlb.getValue()));
            } else {
                dataSelectionDTO.setCompanySid(String.valueOf(0));
            }
            if (CommonUtil.emptyCheck(fromPeriod.getValue())) {
                if (MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
                    dataSelectionDTO.setFromPeriod((String.valueOf(fromPeriod.getValue())));
                    dataSelectionDTO.setFromDate(null);
                } else {
                    dataSelectionDTO.setFromPeriod(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
                    dataSelectionDTO.setFromDate(format.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
                }
            } else {
                dataSelectionDTO.setFromDate(null);
            }
            if (CommonUtil.emptyCheck(toPeriod.getValue())) {
                if (MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
                    dataSelectionDTO.setToPeriod((String.valueOf(toPeriod.getValue())));
                    dataSelectionDTO.setToDate(null);
                } else {
                    dataSelectionDTO.setToPeriod(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
                    dataSelectionDTO.setToDate(format.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
                }
            } else {
                dataSelectionDTO.setToDate(null);
            }
            if (marketTypeDdlb.getValue() != null) {
                dataSelectionDTO.setMarketType(String.valueOf(marketTypeDdlb.getValue()));
            }
            if (deductionType.getValue() != null) {
                dataSelectionDTO.setDeductionType(String.valueOf(deductionType.getValue()));
            } else {
                dataSelectionDTO.setDeductionType(StringUtils.EMPTY);
            }
            if (deductionSubType.getValue() != null) {
                dataSelectionDTO.setDeductionSubType(String.valueOf(deductionSubType.getValue()));
            } else {
                dataSelectionDTO.setDeductionSubType(StringUtils.EMPTY);
            }
            if (contractDdlb.getValue() != null) {
                dataSelectionDTO.setContractSid(String.valueOf(contractDdlb.getValue()));
            } else {
                dataSelectionDTO.setContractSid(StringUtils.EMPTY);
            }
            if (productDdlb.getValue() != null) {
                dataSelectionDTO.setItemSid(String.valueOf(productDdlb.getValue()));
            } else {
                dataSelectionDTO.setItemSid(StringUtils.EMPTY);
            }
            if (productIdentifierDdlb.getValue() != null) {
                dataSelectionDTO.setProductIdentifier(String.valueOf(productIdentifierDdlb.getValue()));
            } else {
                dataSelectionDTO.setProductIdentifier(StringUtils.EMPTY);
            }
            if (productGroup.getValue() != null && !productGroup.getValue().isEmpty()) {
                dataSelectionDTO.setProductGroup(productGroup.getValue());
            } else {
                dataSelectionDTO.setProductGroup(StringUtils.EMPTY);
            }
            if (customerGroup.getValue() != null && !customerGroup.getValue().isEmpty()) {
                dataSelectionDTO.setCustomerGroup(customerGroup.getValue());
            } else {
                dataSelectionDTO.setCustomerGroup(StringUtils.EMPTY);
            }
            session.setReportName(reportName.getValue());
            dataSelectionDTO.setReportName(reportName.getValue());
            dataSelectionDTO.setDescription(description.getValue());
            if(deductionCatogory.getValue()!=null){
                 dataSelectionDTO.setDeductionCategory(String.valueOf(deductionCatogory.getValue()));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * loadView DTO
     *
     * @param viewDTO
     * @throws Exception
     */
    private void loadViewData(ViewDTO viewDTO) throws Exception {
        loadMarketDdlb();
        marketTypeDdlb.setValue(StringUtils.EMPTY + viewDTO.getMartketType_DTO().getId());
        deductionType.setValue(StringUtils.EMPTY + viewDTO.getDeductionType_DTO().getId());
        deductionSubType.setValue(StringUtils.EMPTY + viewDTO.getSubDeductionType_DTO().getId());
        contractDdlb.setValue(StringUtils.EMPTY + viewDTO.getContract());
        loadCompanyDdlb();
        companyDdlb.setValue(StringUtils.EMPTY + viewDTO.getCompany_DTO().getId());
        reportName.setValue(viewDTO.getReportName());
        description.setValue(viewDTO.getDescription());
    }

}
