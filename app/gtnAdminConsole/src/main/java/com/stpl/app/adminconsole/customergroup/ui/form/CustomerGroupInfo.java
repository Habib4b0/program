/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.customergroup.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.adminconsole.util.StringConstantUtils;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.stpl.ifs.ui.DateToStringConverter;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.customergroup.dto.CustomerDetailsDTO;
import com.stpl.app.adminconsole.customergroup.dto.CustomerGroupDTO;
import com.stpl.app.adminconsole.customergroup.logic.CustomerGroupLogic;
import com.stpl.app.adminconsole.customergroup.logic.tablelogic.AvailableLogic;
import com.stpl.app.adminconsole.customergroup.logic.tablelogic.CGMTableLogic;
import com.stpl.app.adminconsole.customergroup.logic.tablelogic.SelectedLogic;
import com.stpl.app.adminconsole.customergroup.ui.view.CustomerGroupSearchMain;
import com.stpl.app.adminconsole.customergroup.ui.view.CustomerGroupView;
import com.stpl.app.adminconsole.itemgroup.util.UISecurityUtil;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.adminconsole.util.ResponsiveUtils;

import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.CommonSecurityLogic;

import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.text.ParseException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilteringTableConstant;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerGroupInfo.
 *
 * @author vishalakshi
 */
public class CustomerGroupInfo extends CustomComponent implements View {

    public static final String NAME = ConstantsUtils.EMPTY;
    @UiField("customerGroupNameLb")
    private Label customerGroupNameLb;
    @UiField("customerGroupName")
    private TextField customerGroupName;
    @UiField("customerGroupNoLb")
    private Label customerGroupNoLb;
    @UiField("customerGroupNo")
    private TextField customerGroupNo;
    @UiField("customerGroupDescLb")
    private Label customerGroupDescLb;
    @UiField("customerGroupDesc")
    private TextField customerGroupDesc;
    @UiField("customerNoLb")
    private Label customerNoLb;
    @UiField("customerNo")
    private TextField customerNo;
    @UiField("customerNameLb")
    private Label customerNameLb;
    @UiField("customerName")
    private TextField customerName;
    @UiField("cityLb")
    private Label cityLb;
    @UiField("city")
    private TextField city;
    @UiField("zipCodeLb")
    private Label zipCodeLb;
    @UiField("zipCode")
    private TextField zipCode;
    @UiField("tableLayout")
    private VerticalLayout tableLayout;
    @UiField("tableLayout1")
    private VerticalLayout tableLayout1;
    @UiField("customerStatusDdlbLb")
    private Label customerStatusDdlbLb;
    @UiField("customerStatusDdlb")
    private ComboBox customerStatusDdlb;
    @UiField("tradeClassDdlbLb")
    private Label tradeClassDdlbLb;
    @UiField("tradeClassDdlb")
    private ComboBox tradeClassDdlb;
    @UiField("customerTypeLb")
    private Label customerTypeLb;
    @UiField("customerTypeDdlb")
    private ComboBox customerTypeDdlb;
    @UiField("stateLb")
    private Label stateLb;
    @UiField("stateDdlb")
    private ComboBox stateDdlb;
    @UiField("backBtn")
    private Button backBtn;
    @UiField("resetBtn")
    private Button resetBtn;
    @UiField("resetbutton")
    private Button resetbutton;
    @UiField("searchBtn")
    private Button searchBtn;
    @UiField("excel")
    private Button excel;
    @UiField("excel1")
    private Button excel1;
    @UiField("addBtn")
    private Button addBtn;
    @UiField("addallBtn")
    private Button addallBtn;
    @UiField("removeBtn")
    private Button removeBtn;
    @UiField("removeallBtn")
    private Button removeallBtn;
    @UiField("savetBtn")
    private Button savetBtn;
    @UiField("customerSearchpanel")
    private Panel customerSearchpanel;
    @UiField("resultpanel")
    private Panel resultpanel;
    @UiField("selectedcustomerpanel")
    private Panel selectedcustomerpanel;
    @UiField("isCssLayout")
    private CssLayout isCssLayout;
    @UiField("cssLayout")
    private CssLayout cssLayout;
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    /**
     * The space.
     */
    private final Label space = new Label("&nbsp;", ContentMode.HTML);
    /**
     * The error msg.
     */
    private final ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The search button layout.
     */
    @UiField("searchButtonLayout")
    private HorizontalLayout searchButtonLayout;
    /**
     * The include button layout.
     */
    @UiField("includeButtonLayout")
    private HorizontalLayout includeButtonLayout;
    /**
     * The exclude button layout.
     */
    @UiField("excludeButtonLayout")
    private HorizontalLayout excludeButtonLayout;
    /**
     * The exclude button layout1.
     */
    @UiField("excludeButtonLayout1")
    private HorizontalLayout excludeButtonLayout1;

    @UiField("excelLayoutAvailable")
    private HorizontalLayout excelLayoutAvailable;

    @UiField("excelLayoutSelected")
    private HorizontalLayout excelLayoutSelected;
    /**
     * The customer group name.
     */

    private final TextField tradeClass = new TextField();

    private final TextField state = new TextField();

    private final TextField customerType = new TextField();
    /**
     * The customer status.
     */
    private final TextField customerStatus = new TextField();
    private final CGMTableLogic availableTableLogic = new CGMTableLogic(new AvailableLogic());
    private final CGMTableLogic selectedTableLogic = new CGMTableLogic(new SelectedLogic());
    private final Set<String> masterSidsList = new LinkedHashSet<>();
    private ExtFilterTable excelAvailableTable;
    private ExtFilterTable excelSelectedTable;
    /**
     * The available results bean.
     */
    private BeanItemContainer<CustomerDetailsDTO> availableExcelResultsBean = new BeanItemContainer<>(CustomerDetailsDTO.class);
    /**
     * The selected results bean.
     */
    private BeanItemContainer<CustomerDetailsDTO> selectedExcelResultsBean = new BeanItemContainer<>(CustomerDetailsDTO.class);
    /**
     * /**
     * The available results.
     */
    ExtPagedTable availableResults = new ExtPagedTable(availableTableLogic);
    /**
     * The selected results.
     */
    private ExtPagedTable selectedResults = new ExtPagedTable(selectedTableLogic);
    /**
     * The available results bean.
     */
    private BeanItemContainer<CustomerDetailsDTO> availableResultsBean = new BeanItemContainer<>(CustomerDetailsDTO.class);
    /**
     * The selected results bean.
     */
    private BeanItemContainer<CustomerDetailsDTO> selectedResultsBean = new BeanItemContainer<>(CustomerDetailsDTO.class);

    /**
     * The customer group dto.
     */
    private CustomerGroupDTO customerGroupDTO;
    /**
     * The customer details dto.
     */
    private CustomerDetailsDTO customerDetailsDTO;
    /**
     * The customer group binder.
     */
    private ErrorfulFieldGroup customerGroupBinder;
    /**
     * The customer binder.
     */
    private ErrorfulFieldGroup customerBinder;
    /**
     * The logic.
     */
    private final CustomerGroupLogic logic;

    /**
     * The record selected flag.
     */
    private Boolean recordSelectedFlag = false;
    /**
     * To provide null.
     */
    private static final CustomerDetailsDTO NULLOBJECT = null;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CustomerGroupInfo.class);
    /**
     * The record selected flag.
     */
    /**
     * The record selected flag.
     */
    final Panel resultTable = new Panel("Results");

    /**
     * Gets the available results.
     *
     * @return the available results
     */
    public ExtFilterTable getAvailableResults() {
        return availableResults;
    }

    /**
     * Sets the available results.
     *
     * @param availableResults the available results
     */
    public void setAvailableResults(final ExtPagedTable availableResults) {
        this.availableResults = availableResults;
    }

    /**
     * Gets the selected results.
     *
     * @return the selected results
     */
    public ExtFilterTable getSelectedResults() {
        return selectedResults;
    }

    /**
     * Sets the selected results.
     *
     * @param selectedResults the selected results
     */
    public void setSelectedResults(final ExtPagedTable selectedResults) {
        this.selectedResults = selectedResults;
    }

    /**
     * Gets the selected results bean.
     *
     * @return the selected results bean
     */
    public BeanItemContainer<CustomerDetailsDTO> getSelectedResultsBean() {
        return selectedResultsBean;
    }

    /**
     * Sets the selected results bean.
     *
     * @param selectedResultsBean the selected results bean
     */
    public void setSelectedResultsBean(final BeanItemContainer<CustomerDetailsDTO> selectedResultsBean) {
        this.selectedResultsBean = selectedResultsBean;
    }

    /**
     * Gets the customer group dto.
     *
     * @return the customer group dto
     */
    public CustomerGroupDTO getCustomerGroupDTO() {
        return customerGroupDTO;
    }

    /**
     * Sets the customer group dto.
     *
     * @param customerGroupDTO the customer group dto
     */
    public void setCustomerGroupDTO(final CustomerGroupDTO customerGroupDTO) {
        this.customerGroupDTO = customerGroupDTO;
    }

    /**
     * Gets the customer details dto.
     *
     * @return the customer details dto
     */
    public CustomerDetailsDTO getCustomerDetailsDTO() {
        return customerDetailsDTO;
    }

    /**
     * Sets the customer details dto.
     *
     * @param customerDetailsDTO the customer details dto
     */
    public void setCustomerDetailsDTO(final CustomerDetailsDTO customerDetailsDTO) {
        this.customerDetailsDTO = customerDetailsDTO;
    }

    /**
     * Gets the customer binder.
     *
     * @return the customer binder
     */
    public ErrorfulFieldGroup getCustomerBinder() {
        return customerBinder;
    }

    /**
     * Sets the customer binder.
     *
     * @param customerBinder the customer binder
     */
    public void setCustomerBinder(final ErrorfulFieldGroup customerBinder) {
        this.customerBinder = customerBinder;
    }

    /**
     * Gets the record selected flag.
     *
     * @return the record selected flag
     */
    public Boolean getRecordSelectedFlag() {
        return recordSelectedFlag;
    }

    /**
     * Sets the record selected flag.
     *
     * @param recordSelectedFlag the record selected flag
     */
    public void setRecordSelectedFlag(final Boolean recordSelectedFlag) {
        this.recordSelectedFlag = recordSelectedFlag;
    }

    /**
     * Gets the space.
     *
     * @return the space
     */
    public Label getSpace() {
        return space;
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
     * Gets the customer group name.
     *
     * @return the customer group name
     */
    public TextField getCustomerGroupName() {
        return customerGroupName;
    }

    /**
     * Gets the customer group desc.
     *
     * @return the customer group desc
     */
    public TextField getCustomerGroupDesc() {
        return customerGroupDesc;
    }

    /**
     * Gets the customer group no.
     *
     * @return the customer group no
     */
    public TextField getCustomerGroupNo() {
        return customerGroupNo;
    }

    /**
     * Gets the customer name.
     *
     * @return the customer name
     */
    public TextField getCustomerName() {
        return customerName;
    }

    /**
     * Gets the trade class.
     *
     * @return the trade class
     */
    public TextField getTradeClass() {
        return tradeClass;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public TextField getCity() {
        return city;
    }

    /**
     * Gets the customer no.
     *
     * @return the customer no
     */
    public TextField getCustomerNo() {
        return customerNo;
    }

    /**
     * Gets the zip code.
     *
     * @return the zip code
     */
    public TextField getZipCode() {
        return zipCode;
    }

    /**
     * Gets the customer type.
     *
     * @return the customer type
     */
    public TextField getCustomerType() {
        return customerType;
    }

    /**
     * Gets the customer status.
     *
     * @return the customer status
     */
    public TextField getCustomerStatus() {
        return customerStatus;
    }

    /**
     * Gets the search.
     *
     * @return the search
     */
    /**
     * Gets the available results bean.
     *
     * @return the available results bean
     */
    public BeanItemContainer<CustomerDetailsDTO> getAvailableResultsBean() {
        return availableResultsBean;
    }

    /**
     * Gets the logic.
     *
     * @return the logic
     */
    public CustomerGroupLogic getLogic() {
        return logic;
    }

    /**
     * Gets the empty customer details dto.
     *
     * @return the empty customer details dto
     */
    public CustomerDetailsDTO getEmptyCustomerDetailsDTO() {
        return NULLOBJECT;
    }

    /**
     * Sets the customer group binder.
     *
     * @param customerGroupBinder the customer group binder
     */
    public void setCustomerGroupBinder(final ErrorfulFieldGroup customerGroupBinder) {
        this.customerGroupBinder = customerGroupBinder;
    }
    private boolean saveFlag = false;
    CommonUtil commonUtil = new CommonUtil();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();

    SessionDTO sessionDTO;

    /**
     * Instantiates a new customer group info.
     *
     * @param customerGroupDTO the customer group dto
     * @param customerGroupBinder the customer group binder
     * @param customerBinder the customer binder
     * @param customerDetailsDTO the customer details dto
     * @param selectedResultsBean the selected results bean
     * @param availableResultsBean
     * @param sessionDTO
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public CustomerGroupInfo(final CustomerGroupDTO customerGroupDTO, final ErrorfulFieldGroup customerGroupBinder, final ErrorfulFieldGroup customerBinder, final CustomerDetailsDTO customerDetailsDTO,
            final BeanItemContainer selectedResultsBean, final BeanItemContainer availableResultsBean, final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        LOGGER.debug("Entering CustomerGroupInfo");
        this.customerGroupDTO = customerGroupDTO;
        this.customerGroupBinder = customerGroupBinder;
        this.customerBinder = customerBinder;
        this.customerDetailsDTO = customerDetailsDTO;
        this.selectedResultsBean = selectedResultsBean;
        this.availableResultsBean = availableResultsBean;
        this.sessionDTO = sessionDTO;
        logic = new CustomerGroupLogic(sessionDTO);
        init();

        LOGGER.debug("CustomerGroupInfo Constructor Ended");

    }

    /**
     * Inits the.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public final void init() throws SystemException, PortalException {
        LOGGER.debug("Entering init Method");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/commonclara/customergroupmaster.xml"), this));
        getCustomerGroupBinder();
        getItemBinder();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = sessionDTO.getUserId();
        final Map<String, AppPermission> fieldItemHM = stplSecurity
                .getFieldOrColumnPermission(userId, UISecurityUtil.CUSTOMER_GROUP_MASTER + "," + StringConstantUtils.FUNCTIONAL_SCREEN, false);
        final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CUSTOMER_GROUP_MASTER + "," + StringConstantUtils.FUNCTIONAL_SCREEN);
        getResponsiveFirstTab(fieldItemHM);
        configureFields();
        getButtonPermission(functionCompanyHM);
        addItemAvailableResults();
        addItemSelectedResults();
        LOGGER.debug("init Method Ended");
    }

    private ErrorfulFieldGroup getCustomerGroupBinder() {
        LOGGER.debug("Entering getCustomerGroupBinder method");
        customerGroupBinder.bindMemberFields(this);
        customerGroupBinder.setItemDataSource(new BeanItem<>(customerGroupDTO));
        customerGroupBinder.setBuffered(true);
        customerGroupBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("getCustomerGroupBinder method RETURNS customerGroupBinder ");
        return customerGroupBinder;
    }

    /**
     * Gets the item binder.
     *
     * @return the item binder
     */
    private ErrorfulFieldGroup getItemBinder() {

        LOGGER.debug("getItemBinder method Started ");
        customerBinder.bindMemberFields(this);
        customerBinder.setItemDataSource(new BeanItem<>(customerDetailsDTO));
        customerBinder.setBuffered(true);
        customerBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("getItemBinder method RETURNS customerBinder ");

        return customerBinder;
    }

    /**
     * Adds the customer group info.
     *
     * @return the panel
     */
    /**
     * Adds the item search.
     *
     * @return the panel
     */
    /**
     * Adds the search buttons.
     *
     * @return the component
     */
    /**
     * Adds the item available results.
     *
     * @return the filter table
     */
    private ExtFilterTable addItemAvailableResults() {

        try {
            LOGGER.debug("addItemAvailableResults mehod Started ");
            excelLayoutAvailable.setVisible(false);
            tableLayout.addComponent(availableResults);
            tableLayout.addComponent(createControls(availableTableLogic));
            availableResults.markAsDirty();
            availableTableLogic.setContainerDataSource(availableResultsBean);
            final StplSecurity stplSecurity = new StplSecurity();

            final String userId = sessionDTO.getUserId();
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CUSTOMER_GROUP_MASTER + "," + StringConstantUtils.FUNCTIONAL_LIST_VIEW, false);
            String mode = sessionDTO.getMode();

            List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.CUSTOMER_GROUP_MASTER, StringConstantUtils.FUNCTIONAL_LIST_VIEW);
            Object[] objColumn = new CommonUIUtils().customerResultsColumns;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objColumn, fieldIfpHM, mode);

            availableResults.setFilterBarVisible(true);
            availableResults.setFilterDecorator(new ExtDemoFilterDecorator());

            if (tableResultCustom.getObjResult().length > 0) {
                availableResults.setVisibleColumns(tableResultCustom.getObjResult());
                availableResults.setColumnHeaders(tableResultCustom.getObjResultHeader());
            } else {
                tableLayout.setVisible(false);
            }
            availableResults.setMultiSelect(true); 
            availableTableLogic.setPageLength(NumericConstants.FIVE);
            availableTableLogic.sinkItemPerPageWithPageLength(false);
            availableResults.setWidth("100%");
            availableResults.setWidth(ConstantsUtils.WIDTH);
            availableResults.setImmediate(true);
            availableResults.setSelectable(true);
            availableResults.setConverter(StringConstantUtils.TRADE_CLASS_START_DATE, new DateToStringConverter());
            availableResults.setConverter(StringConstantUtils.TRADE_CLASS_END_DATE, new DateToStringConverter());
            availableResults.setConverter(StringConstantUtils.PARENT_START_DATE, new DateToStringConverter());
            availableResults.setConverter(StringConstantUtils.PARENT_END_DATE, new DateToStringConverter());
            availableResults.setConverter(StringConstantUtils.CUSTOMER_START_DATE, new DateToStringConverter());
            availableResults.setConverter(StringConstantUtils.CUSTOMER_END_DATE, new DateToStringConverter());
            availableResults.setConverter(StringConstantUtils.PRIOR_PARENT_START_DATE, new DateToStringConverter());
            for (Object object : availableResults.getVisibleColumns()) {
                if (object.toString().contains("Date")) {
                    availableResults.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
                }
            }
            availableResults.setErrorHandler(new ErrorHandler() {
                /**
                 * Interface for listening to errors in the application.
                 */
                public void error(final com.vaadin.server.ErrorEvent event) {
                    return;
                }
            });

            availableResults.addItemClickListener(new ItemClickEvent.ItemClickListener() {
                public void itemClick(final ItemClickEvent event) {
                    if (event.isDoubleClick()) {
                        addDoubleClick();
                    }
                }
            });

            final Object[] obj = new Object[]{StringConstantUtils.TRADE_CLASS_START_DATE, StringConstantUtils.TRADE_CLASS_END_DATE, StringConstantUtils.CUSTOMER_END_DATE, StringConstantUtils.PARENT_START_DATE, StringConstantUtils.PARENT_END_DATE, StringConstantUtils.CUSTOMER_START_DATE, StringConstantUtils.PRIOR_PARENT_START_DATE};
            availableResults = CommonUtil.getFormattedTable(availableResults, obj);
            LOGGER.debug("addItemAvailableResults mehod RETURNS availableResults - ExtFilterTable ");

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return availableResults;
    }

    /**
     * Adds the include buttons.
     *
     * @return the component
     */
    /**
     * Adds the item selected results.
     *
     * @return the filter table
     */
    private ExtFilterTable addItemSelectedResults() {

        try {
            LOGGER.debug("addItemSelectedResults mehod Started ");
            
            excelLayoutSelected.setVisible(false);
            tableLayout1.addComponent(selectedResults);
            tableLayout1.addComponent(createControls(selectedTableLogic));

            selectedResults.markAsDirty();
            selectedTableLogic.setContainerDataSource(selectedResultsBean);
            selectedResults.setFilterBarVisible(true);
            selectedResults.setFilterDecorator(new ExtDemoFilterDecorator());
            final StplSecurity stplSecurity = new StplSecurity();

            final String userId = sessionDTO.getUserId();
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CUSTOMER_GROUP_MASTER + "," + StringConstantUtils.FUNCTIONAL_LIST_VIEW, false);
            String mode = sessionDTO.getMode();

            List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.CUSTOMER_GROUP_MASTER, StringConstantUtils.FUNCTIONAL_LIST_VIEW);
            Object[] objColumn = new CommonUIUtils().customerResultsColumns;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objColumn, fieldIfpHM, mode);

            if (tableResultCustom.getObjResult().length > 0) {
                selectedResults.setVisibleColumns(tableResultCustom.getObjResult());
                selectedResults.setColumnHeaders(tableResultCustom.getObjResultHeader());
            } else {
                tableLayout1.setVisible(false);
            }
            selectedResults.setMultiSelect(true);
            selectedTableLogic.setPageLength(NumericConstants.FIVE);
            selectedTableLogic.sinkItemPerPageWithPageLength(false);
            selectedResults.setWidth(ConstantsUtils.WIDTH);
            selectedResults.setImmediate(true);
            selectedResults.setSelectable(true);
            selectedResults.setConverter(StringConstantUtils.TRADE_CLASS_START_DATE, new DateToStringConverter());
            selectedResults.setConverter(StringConstantUtils.TRADE_CLASS_END_DATE, new DateToStringConverter());
            selectedResults.setConverter(StringConstantUtils.PARENT_START_DATE, new DateToStringConverter());
            selectedResults.setConverter(StringConstantUtils.PARENT_END_DATE, new DateToStringConverter());
            selectedResults.setConverter(StringConstantUtils.CUSTOMER_START_DATE, new DateToStringConverter());
            selectedResults.setConverter(StringConstantUtils.CUSTOMER_END_DATE, new DateToStringConverter());
            selectedResults.setConverter(StringConstantUtils.PRIOR_PARENT_START_DATE, new DateToStringConverter());
            for (Object object : selectedResults.getVisibleColumns()) {
                if (object.toString().contains("Date")) {
                    selectedResults.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
                }
            }
            selectedResults.setErrorHandler(new ErrorHandler() {
                public void error(final com.vaadin.server.ErrorEvent event) {
                    return;
                }
            });

            selectedResults.addItemClickListener(new ItemClickEvent.ItemClickListener() {
                /**
                 * Called when a {@link Button} has been clicked. A reference to
                 * the button is given by {@link ClickEvent#getButton()}.
                 *
                 */
                public void itemClick(final ItemClickEvent event) {
                    if (event.isDoubleClick()) {
                        removeItemsButtonClick();
                    }
                }
            });
            final Object[] obj = new Object[]{StringConstantUtils.TRADE_CLASS_START_DATE, StringConstantUtils.TRADE_CLASS_END_DATE, StringConstantUtils.CUSTOMER_END_DATE, StringConstantUtils.PARENT_START_DATE, StringConstantUtils.PARENT_END_DATE, StringConstantUtils.CUSTOMER_START_DATE, StringConstantUtils.PRIOR_PARENT_START_DATE};
            selectedResults = CommonUtil.getFormattedTable(selectedResults, obj);
            if (ConstantsUtils.LOWERCASE_EDIT.equalsIgnoreCase(sessionDTO.getLogic()) || ConstantsUtils.VIEW.equalsIgnoreCase(sessionDTO.getLogic())
                    || ConstantsUtils.LOWERCASE_COPY.equalsIgnoreCase(sessionDTO.getLogic())) {
                loadSelectedTableOnEdit();
            }
            LOGGER.debug("addItemSelectedResults mehod RETURNS selectedResults ");

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return selectedResults;
    }

    /**
     * Results item click.
     *
     * @param systemId the id
     */
    protected void resultsItemClick(final Object systemId) {
        LOGGER.debug("Entering resultsItemClick mehod");
        if (systemId == null) {
            recordSelectedFlag = false;
        } else {
            recordSelectedFlag = true;
        }
        LOGGER.debug("resultsItemClick mehod Ended");
    }

    /**
     * Configure fields.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void configureFields() throws SystemException {

        LOGGER.debug("configureFields mehod Initiated ");
        customerGroupName.focus();

        customerGroupNo.setImmediate(true);
        customerGroupName.setImmediate(true);
        customerGroupDesc.setImmediate(true);

        customerGroupNo.setRequired(true);
        customerGroupNo.setRequiredError("Please enter Customer Group No");

        customerGroupName.setRequired(true);
        customerGroupName.setRequiredError("Please enter Customer Group Name");

        customerGroupDesc.setRequired(true);
        customerGroupDesc.setRequiredError("Please enter Customer Group Description");
        if (sessionDTO.getMode() != null && sessionDTO.getMode().equals(ConstantsUtils.EDIT)) {
            savetBtn.setCaption("UPDATE");
        }


        LOGGER.debug("In configureFields loadCustomerStatus started");
        customerStatusDdlb = CommonUtil.getComboBox(customerStatusDdlb, "STATUS");
        customerStatusDdlb.setNullSelectionAllowed(true);
        customerStatusDdlb.setInputPrompt(ConstantsUtils.SELECT_ONE);

        customerStatusDdlb.setImmediate(true);
        LOGGER.debug("In configureFields loadCustomerStatus Ended");
        customerStatusDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             *
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {
                customerStatusOnChangeEvent(event.getProperty().getValue());
            }
        });
        LOGGER.debug("In configureFields loadTradeClass started");

        tradeClassDdlb = CommonUtil.getComboBox(tradeClassDdlb, "COMPANY_TRADE_CLASS");
        tradeClassDdlb.setNullSelectionAllowed(true);
        tradeClassDdlb.setInputPrompt(ConstantsUtils.SELECT_ONE);

        tradeClassDdlb.setStyleName("tradeClassDdlb");
        tradeClassDdlb.setImmediate(true);
        LOGGER.debug("In configureFields loadTradeClass Ended");
        tradeClassDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             *
             */
            public void valueChange(final ValueChangeEvent event) {
                tradeClassOnChangeEvent(event.getProperty().getValue());

            }
        });
        LOGGER.debug("In configureFields loadCustomerType started");

        customerTypeDdlb = CommonUtil.getComboBox(customerTypeDdlb, "COMPANY_TYPE");
        customerTypeDdlb.setNullSelectionAllowed(true);
        customerTypeDdlb.setInputPrompt(ConstantsUtils.SELECT_ONE);

        customerTypeDdlb.setImmediate(true);
        LOGGER.debug("In configureFields loadCustomerType Ended");
        customerTypeDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             *
             */
            public void valueChange(final ValueChangeEvent event) {
                customerTypeOnChangeEvent(event.getProperty().getValue());
            }
        });
        stateDdlb = CommonUtil.getComboBox(stateDdlb, "STATE");
        stateDdlb.setNullSelectionAllowed(true);
        stateDdlb.setInputPrompt(ConstantsUtils.SELECT_ONE);

        stateDdlb.setImmediate(true);
        stateDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             *
             */
            public void valueChange(final ValueChangeEvent event) {
                stateOnChangeEvent(event.getProperty().getValue());
            }
        });
        
        excel.addClickListener(new ClickListener() {
            /**
             * Adds the button click listener.
             *
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields availableResultsExcelExport Started");
                if (availableResults.size() > ConstantsUtils.ZERO_NUM) {
                    configureExcelAvailableResultTable();
                    loadAvailableExcelTable();
                    VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, "true");
                    ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(excelAvailableTable), "Available Customer Results", "Available Customer Results", "AvailableCustomerResults.xls", false);
                    excel.export();
                    excelLayoutAvailable.removeComponent(excelAvailableTable);
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results to Export", ButtonId.OK);
                }
                LOGGER.debug("In configureFields availableResultsExcelExport Ended");
            }
        });

        excel1.addClickListener(new ClickListener() {
            /**
             * Adds the button click listener.
             *
             *
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields selectedResultsExcelExport Started");
                if (selectedResults.size() > ConstantsUtils.ZERO_NUM) {
                    configureExcelSelectedResultTable();
                    loadSelectedExcelTable();
                    VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, "true");
                    ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(excelSelectedTable), "Selected Customer Results", "Selected Customer Results", "SelectedCustomerResults.xls", false);
                    excel.export();
                    excelLayoutSelected.removeComponent(excelSelectedTable);
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results to Export", ButtonId.OK);
                }
                LOGGER.debug("In configureFields selectedResultsExcelExport Ended");
            }
        });

        excel.setIcon(excelExportImage);
        excel.setDescription("Export to excel");
        excel.setIconAlternateText("Excel export");
        excel.setHtmlContentAllowed(true);

        excel1.setIcon(excelExportImage);
        excel1.setDescription("Export to excel");
        excel1.setIconAlternateText("Excel export");
        excel1.setHtmlContentAllowed(true);

        LOGGER.debug("In configureFields Ended");

    }


    /**
     * Customer status on change event.
     *
     * @param companyValue the company value
     */
    protected void customerStatusOnChangeEvent(final Object companyValue) {
        LOGGER.debug("Entering customerStatusOnChangeEvent method");
        if (customerStatusDdlb.getValue() == null) {
            customerStatus.setValue(null);
        } else {
            customerStatus.setValue(companyValue.toString());
        }
        LOGGER.debug(" customerStatusOnChangeEvent method ended");
    }

    /**
     * Trade class on change event.
     *
     * @param companyValue the company value
     */
    protected void tradeClassOnChangeEvent(final Object companyValue) {
        LOGGER.debug("Entering tradeClassOnChangeEvent method with companyValue");

        if (tradeClassDdlb.getValue() == null) {
            tradeClass.setValue(null);
        } else {
            tradeClass.setValue(companyValue.toString());
        }
        LOGGER.debug(" tradeClassOnChangeEvent method Ended");
    }

    /**
     * Customer type on change event.
     *
     * @param companyValue the company value
     */
    protected void customerTypeOnChangeEvent(final Object companyValue) {

        LOGGER.debug("Entering customerTypeOnChangeEvent method with companyValue ");

        if (customerTypeDdlb.getValue() == null) {
            customerType.setValue(null);
        } else {
            customerType.setValue(companyValue.toString());
        }

        LOGGER.debug("customerTypeOnChangeEvent method Ended");
    }

    /**
     * state on change event.
     *
     * @param object
     *
     */
    protected void stateOnChangeEvent(final Object object) {

        LOGGER.debug("Entering stateOnChangeEvent method with object ");

        if (stateDdlb.getValue() == null) {
            state.setValue(null);
        } else {
            state.setValue(object.toString());
        }

        LOGGER.debug("stateOnChangeEvent method Ended");
    }
    /**
     * Search button click logic.
     *
     * @param event the event
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    int count;
    static String searchCriteria = StringUtils.EMPTY;

    protected void searchButtonClickLogic() throws FieldGroup.CommitException{
        LOGGER.debug("Entering searchButtonClickLogic");
        count++;
        customerBinder.commit();
        searchCriteria += logic.getSearchCriteria(customerBinder, count).get(NumericConstants.EIGHT);
        customerDetailsDTO.setGenerate(Boolean.TRUE);
        if (!availableTableLogic.loadSetData(customerDetailsDTO)) {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results could be found that match the entered search criteria.", ButtonId.OK);
        } else {
            CommonUIUtils.getMessageNotification(ConstantsUtils.SEARCH_COMPLETED);
        }
        LOGGER.debug("searchButtonClickLogic Method ended");
    }

    /**
     * Reset button click logic.
     *
     */
    protected void resetButtonClickLogic() {

        LOGGER.debug("resetButtonClickLogic Started");
        customerGroupBinder.getErrorDisplay().clearError();
        availableResults.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedResults.setFilterDecorator(new ExtDemoFilterDecorator());

        customerNo.setValue(ConstantsUtils.EMPTY);
        customerName.setValue(ConstantsUtils.EMPTY);
        zipCode.setValue(ConstantsUtils.EMPTY);
        stateDdlb.setValue(null);
        city.setValue(ConstantsUtils.EMPTY);
        tradeClassDdlb.setValue(null);
        customerStatusDdlb.setValue(null);
        customerTypeDdlb.setValue(null);
        LOGGER.debug("resetButtonClickLogic Ended");

    }

    /**
     * Adds the double click.
     *
     * @param event the event
     */
    protected void addDoubleClick() {
        LOGGER.debug("Entering addItemsButtonClick method ");
        if (addCustomers()) {
            customerDetailsDTO.setMaster_Sid_List(masterSidsList);
            customerDetailsDTO.setEditMode(Boolean.FALSE);
            selectedTableLogic.loadSetData(customerDetailsDTO);
            availableTableLogic.loadSetData(customerDetailsDTO);
        }
        availableResults.setValue(null);
        LOGGER.debug(" addItemsButtonClick method Ended");
    }

    /**
     * Removes the items button click.
     *
     * @param event the event
     */
    protected void removeItemsButtonClick(final ClickEvent event) {

        LOGGER.debug("Entering removeItemsButtonClick method ");
        final Object itemId = selectedResults.getValue();
        selectedResultsBean.removeItem(itemId);
        availableResultsBean.addItem(itemId);
        final java.util.Set<CustomerDetailsDTO> itemMasterDetailsList = (java.util.Set<CustomerDetailsDTO>) selectedResults.getValue();
        availableResultsBean.addAll(itemMasterDetailsList);
        for (final Iterator<CustomerDetailsDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
            final CustomerDetailsDTO item = iterator.next();
            selectedResultsBean.removeItem(item);
        }
        selectedResults.setValue(null);
        availableResults.setValue(null);
        LOGGER.debug("removeItemsButtonClick method Ended");

    }

    /**
     * Removes the items button click.
     *
     * @param event the event
     */
    protected void removeItemsButtonClick() {
        LOGGER.debug("Entering removeItemsButtonClick method ");
        removeCustomers();
        customerDetailsDTO.setMaster_Sid_List(masterSidsList);
        customerDetailsDTO.setEditMode(Boolean.FALSE);
        selectedTableLogic.loadSetData(customerDetailsDTO);
        availableTableLogic.loadSetData(customerDetailsDTO);
        LOGGER.debug(" removeItemsButtonClick method Ended");
    }

    /**
     * Save button click.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private boolean saveGroupInfo(String indicator) {
        LOGGER.debug("IN saveGroupInfo");
        saveFlag = false;

        try {
            customerGroupBinder.getField("customerGroupName").setRequired(false);
            customerGroupBinder.getField("customerGroupNo").setRequired(false);
            customerGroupBinder.getField("customerGroupDesc").setRequired(false);
            customerGroupBinder.commit();
            customerGroupBinder.getField("customerGroupName").setRequired(true);
            customerGroupBinder.getField("customerGroupNo").setRequired(true);
            customerGroupBinder.getField("customerGroupDesc").setRequired(true);
            customerGroupDTO.setCustomerGroupNo(customerGroupBinder.getField(ConstantsUtils.CUST_GROUP_NO).getValue().toString());
            customerGroupDTO.setCustomerGroupName(customerGroupBinder.getField(ConstantsUtils.CUST_GROUP_NAME).getValue().toString());
            customerGroupDTO.setCustomerGroupDesc(customerGroupBinder.getField(ConstantsUtils.CUST_GROUP_DESC).getValue().toString());
            if (StringUtils.isBlank(customerGroupName.getValue()) || ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(customerGroupName.getValue()))
                    || StringUtils.isBlank(customerGroupNo.getValue()) || ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(customerGroupNo.getValue()))
                    || StringUtils.isBlank(customerGroupDesc.getValue()) || ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(customerGroupDesc.getValue()))) {

                MessageBox.showPlain(Icon.ERROR, "Required Fields Missing", "Not all required fields have been populated.  Please complete all required fields and try again. ", ButtonId.OK);
                saveFlag = false;
                return saveFlag;
            } else if (selectedResultsBean.size() == ConstantsUtils.ZERO_NUM) {
                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Please select atleast one customer to save the Customer Group ", ButtonId.OK);
                saveFlag = false;
                return saveFlag;
            } else {
                String msg = logic.duplicateCheck(customerGroupBinder);
                if (sessionDTO.getMode().equalsIgnoreCase(ConstantsUtils.EDIT)) {
                    msg = "S";
                }
                if ("S".equals(msg)) {
                    if ("back".equals(indicator)) {
                        save();
                    } else {
                        MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Save record "
                                + customerGroupName.getValue() + "?", new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked .
                             *
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    save();
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                    }
                    saveFlag = true;
                } else if ("NOEXIST".equals(msg)) {
                        MessageBox.showPlain(Icon.ERROR, ConstantsUtils.ERROR, "Customer Group No already exists, Please enter different Customer Group No  ", ButtonId.OK);
                        saveFlag = false;
                        return saveFlag;
                } else if ("NAMEEXIST".equals(msg)) {
                        MessageBox.showPlain(Icon.ERROR, ConstantsUtils.ERROR, "Customer Group Name already exists, Please enter different Customer Group Name", ButtonId.OK);
                        saveFlag = false;
                        return saveFlag;
                    }                    

            }
        } catch (FieldGroup.CommitException e) {
            LOGGER.error(e);
            saveFlag = false;
        } catch (Exception e) {
            LOGGER.error(e);
            saveFlag = false;
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
        }
        return saveFlag;
    }

    private void save() {
        try {
            saveLogic();
        } catch (Exception e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
        }
    }

    protected void saveButtonClick() throws SystemException, PortalException {
        try {
            LOGGER.debug("Entering saveButtonClick method");

            final List<CustomerDetailsDTO> selectedCompanies = selectedResultsBean.getItemIds();
            final List<String> selectedList = new ArrayList<>();
            List<String> alreadyList = new ArrayList<>();
            for (CustomerDetailsDTO selectedCompany : selectedCompanies) {

                selectedList.add(selectedCompany.getCustomerId());
            }
            Collections.sort(selectedList);
            for (CustomerDetailsDTO companyDetail : companyDetails) {

                alreadyList.add(companyDetail.getCustomerId());
            }
            Collections.sort(alreadyList);
            String pageView = sessionDTO.getLogic();
            LOGGER.debug("pageView====" + pageView + "-----");
            if (pageView.equals("copy")) {
                alreadyList = new ArrayList<>();
                sessionDTO.setSystemId(0);
            }
            if (!selectedList.equals(alreadyList)) {
                final List<Integer> idList = logic.saveCustomerGroup(customerGroupBinder, selectedCompanies, searchCriteria, sessionDTO);
                String page = sessionDTO.getFromViewPage();
                if (!idList.isEmpty()) {
                    if (page.equalsIgnoreCase(ConstantsUtils.ADD)) {
                        sessionDTO.setSystemId(idList.get(0));
                        sessionDTO.setFromViewPage(ConstantsUtils.OPTION_NO);
                        sessionDTO.setVersionNo(idList.get(1));
                        sessionDTO.setLogic("edit");
                        sessionDTO.setMode(ConstantsUtils.EDIT);
                        LOGGER.debug("In configureFields editButtonClickLogic navigateTo CustomerGroupView");
                        getUI().getNavigator().navigateTo(CustomerGroupView.NAME);
                    }
                    final Notification notif = new Notification(customerGroupName.getValue() + StringConstantUtils.HAS_BEEN_SUCCESSFULLY_SAVED, Notification.Type.HUMANIZED_MESSAGE);
                    notif.setPosition(Position.BOTTOM_CENTER);
                    notif.setStyleName(StringConstantUtils.MYSTYLE);
                    notif.setDelayMsec(NumericConstants.THREE_THOUSAND);
                    notif.show(Page.getCurrent());
                } else {
                    customerBinder.getErrorDisplay().setError(ConstantsUtils.ERROR_OCCURED);
                }

            } else {
                final Notification notif = new Notification(customerGroupName.getValue() + StringConstantUtils.HAS_BEEN_SUCCESSFULLY_SAVED, Notification.Type.HUMANIZED_MESSAGE);

                notif.setPosition(Position.BOTTOM_CENTER);
                notif.setStyleName(StringConstantUtils.MYSTYLE);
                notif.setDelayMsec(NumericConstants.THREE_THOUSAND);
                notif.show(Page.getCurrent());
            }

            LOGGER.debug("saveButtonClick method Ended");

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Reset all button click logic.
     *
     */
    protected void resetAllButtonClickLogic() {
        LOGGER.debug("Entering resetAllButtonClickLogic method");
        try {

            final String fromViewPage = sessionDTO.getFromViewPage();
            if (fromViewPage.equals(ConstantsUtils.OPTION_NO)) {

                selectedResultsBean.removeAllItems();
                if (ConstantsUtils.LOWERCASE_EDIT.equalsIgnoreCase(sessionDTO.getLogic())) {
                   loadSelectedTableOnEdit();
                } else {
                    selectedResults.setFilterDecorator(new ExtDemoFilterDecorator());
                }

                availableResults.setFilterDecorator(new ExtDemoFilterDecorator());
                availableResultsBean.removeAllItems();
                customerNo.setValue(ConstantsUtils.EMPTY);
                customerName.setValue(ConstantsUtils.EMPTY);
                zipCode.setValue(ConstantsUtils.EMPTY);
                stateDdlb.setValue(null);
                city.setValue(ConstantsUtils.EMPTY);
                tradeClass.setValue(ConstantsUtils.EMPTY);
                tradeClassDdlb.setValue(null);
                customerStatusDdlb.setValue(null);
                customerTypeDdlb.setValue(null);
            } else {
                final String fromPage = sessionDTO.getFromViewPage();
                if (fromPage.equals(ConstantsUtils.ADD)) {
                    selectedResultsBean.removeAllItems();
                    availableResultsBean.removeAllItems();
                    customerGroupNo.setValue(ConstantsUtils.EMPTY);
                    customerGroupName.setValue(ConstantsUtils.EMPTY);
                    customerGroupDesc.setValue(ConstantsUtils.EMPTY);
                } else if (fromPage.equals(ConstantsUtils.COPY)) {
                    selectedResultsBean.removeAllItems();

                    availableResultsBean.removeAllItems();
                    customerGroupNo.setValue(ConstantsUtils.EMPTY);
                    customerGroupName.setValue(ConstantsUtils.EMPTY);
                    customerGroupDesc.setValue(ConstantsUtils.EMPTY);


                }

                customerGroupBinder.getErrorDisplay().clearError();
                customerGroupBinder.setEnabled(true);
                availableResults.setFilterDecorator(new ExtDemoFilterDecorator());
                selectedResults.setFilterDecorator(new ExtDemoFilterDecorator());
                availableResultsBean.removeAllItems();

                customerNo.setValue(ConstantsUtils.EMPTY);
                customerName.setValue(ConstantsUtils.EMPTY);
                zipCode.setValue(ConstantsUtils.EMPTY);
                stateDdlb.setValue(null);
                city.setValue(ConstantsUtils.EMPTY);
                tradeClass.setValue(ConstantsUtils.EMPTY);
                tradeClassDdlb.setValue(null);
                customerStatusDdlb.setValue(null);
                customerTypeDdlb.setValue(null);

            }
            if (!ConstantsUtils.LOWERCASE_EDIT.equalsIgnoreCase(sessionDTO.getLogic())) {
                customerDetailsDTO.setGenerate(Boolean.FALSE);
                availableTableLogic.loadSetData(customerDetailsDTO);
                selectedTableLogic.loadSetData(customerDetailsDTO);
            }
            if (ConstantsUtils.ADD.equalsIgnoreCase(sessionDTO.getLogic())) {
                masterSidsList.clear();
            }
            
            LOGGER.debug("resetAllButtonClickLogic method Ended");
        } catch (Exception e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4006));
        }
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        return;
    }
    /**
     * Entry.
     *
     * @param flag the flag
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    List<CustomerDetailsDTO> companyDetails = new ArrayList<>();

    public void entry(final Boolean flag) throws SystemException {
        LOGGER.debug("entry method Started");
        LOGGER.debug(ConstantsUtils.ENTRY_STARTED);
        final int customerGroupSystemId = sessionDTO.getSystemId();
        final int versionNo = sessionDTO.getVersionNo();
        if (customerGroupSystemId != ConstantsUtils.ZERO_NUM) {
            if (flag) {
                customerGroupDTO = new CustomerGroupLogic().getHistCustomerGroupInfo(versionNo, sessionDTO);

            }
            customerGroupNo.setReadOnly(false);
            customerGroupName.setReadOnly(false);
            customerGroupDesc.setReadOnly(false);
            customerGroupName.setValue(customerGroupDTO.getCustomerGroupName());
            customerGroupNo.setValue(customerGroupDTO.getCustomerGroupNo());
            customerGroupDesc.setValue(customerGroupDTO.getCustomerGroupDesc());
            availableResults.removeAllItems();
        }

        final String fromViewPage = sessionDTO.getFromViewPage();
        if (fromViewPage.equals(ConstantsUtils.YES)) {
            customerGroupNo.setReadOnly(true);
            customerGroupName.setReadOnly(true);
            customerGroupDesc.setReadOnly(true);
            customerSearchpanel.setVisible(false);
            resultpanel.setVisible(false);
            addBtn.setVisible(false);
            addallBtn.setVisible(false);
            excel.setVisible(false);
            removeBtn.setVisible(false);
            removeallBtn.setVisible(false);
            availableResults.setVisible(false);
            resultTable.setVisible(false);
            excel1.setVisible(true);
            savetBtn.setEnabled(false);
            resetbutton.setEnabled(false);

            selectedcustomerpanel.setReadOnly(true);

        } else {
            customerGroupNo.setReadOnly(true);
            customerGroupDesc.setReadOnly(true);
            customerGroupName.setReadOnly(true);
            availableResults.setVisible(true);
            selectedResults.setReadOnly(false);
            final String fromAddPage = sessionDTO.getFromViewPage();
            if (fromAddPage.equals(ConstantsUtils.ADD)) {
                sessionDTO.setLogic("add");
                customerGroupNo.setReadOnly(false);
                customerGroupDesc.setReadOnly(false);
                customerGroupName.setReadOnly(false);
                selectedResultsBean.removeAllItems();
                availableResultsBean.removeAllItems();
                customerGroupNo.setValue(ConstantsUtils.EMPTY);
                customerGroupName.setValue(ConstantsUtils.EMPTY);
                customerGroupDesc.setValue(ConstantsUtils.EMPTY);
            } else if (fromAddPage.equals(ConstantsUtils.COPY)) {
                customerGroupNo.setReadOnly(false);
                customerGroupDesc.setReadOnly(false);
                customerGroupName.setReadOnly(false);

                customerGroupNo.setValue(ConstantsUtils.EMPTY);
                customerGroupName.setValue(ConstantsUtils.EMPTY);
                customerGroupDesc.setValue(ConstantsUtils.EMPTY);

            }
        }
        LOGGER.debug(ConstantsUtils.ENTRY_STARTED);
    }

    /**
     * Adds the responsive search table collapse.
     *
     * @param table the table
     */
    public void addResponsiveSearchTableCollapse(final ExtFilterTable table) {

        final Map<Integer, Boolean> reloadMap = new HashMap<>();
        reloadMap.put(ConstantsUtils.PX_1516, true);
        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
        reloadMap.put(0, true);

        try {

            table.setColumnCollapsingAllowed(true);

            Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
                @Override
                /**
                 *
                 */
                public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {

                    final int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                    if (browserWidth > ConstantsUtils.PX_1516 && reloadMap.get(ConstantsUtils.PX_1516)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumnsDefault(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth > ConstantsUtils.PX_1516);
                        }
                        reloadMap.put(ConstantsUtils.PX_1516, false);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < ConstantsUtils.PX_1516 && browserWidth > NumericConstants.NINE_SEVEN_EIGHT && reloadMap.get(NumericConstants.NINE_SEVEN_EIGHT)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < ConstantsUtils.PX_1516);
                        }

                        reloadMap.put(ConstantsUtils.PX_1516, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, false);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.SIX_HUNDRED && reloadMap.get(NumericConstants.SIX_HUNDRED)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }

                        if (table != null && table.getItemIds().isEmpty()) {
                            for (final Object propertyId : getCollapsibleColumns978Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        } else {
                            for (final Object propertyId : getCollapsibleColumns600Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        }

                        reloadMap.put(ConstantsUtils.PX_1516, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, false);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < NumericConstants.SIX_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO && reloadMap.get(NumericConstants.FOUR_EIGHT_ZERO)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumns600Px(table)) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, true);
                        }
                        reloadMap.put(ConstantsUtils.PX_1516, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, false);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO && reloadMap.get(NumericConstants.THREE_TWO_ZERO)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < NumericConstants.FOUR_EIGHT_ZERO);
                        }
                        reloadMap.put(ConstantsUtils.PX_1516, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, false);
                        reloadMap.put(0, true);

                    } else if (browserWidth < NumericConstants.THREE_TWO_ZERO && reloadMap.get(0)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < NumericConstants.THREE_TWO_ZERO);
                        }
                        reloadMap.put(ConstantsUtils.PX_1516, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, false);

                    }

                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Gets the collapsible columns600 px.
     *
     * @param table the table
     * @return the collapsible columns600 px
     */
    private static String[] getCollapsibleColumns600Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns480 px.
     *
     * @param table the table
     * @return the collapsible columns480 px
     */
    private static String[] getCollapsibleColumns480Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns978 px.
     *
     * @param table the table
     * @return the collapsible columns978 px
     */
    private static String[] getCollapsibleColumns978Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns default1515 px.
     *
     * @param table the table
     * @return the collapsible columns default1515 px
     */
    private static String[] getCollapsibleColumnsDefault1515Px(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[NumericConstants.TWO]);
        list.remove(propertyIds[NumericConstants.THREE]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Gets the collapsible columns default.
     *
     * @param table the table
     * @return the collapsible columns default
     */
    private static String[] getCollapsibleColumnsDefault(final ExtFilterTable table) {
        final Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        final List<String> list = new ArrayList<>(Arrays.asList(propertyIds));

        for (int i = 0; i < NumericConstants.TEN; i++) {
            list.remove(propertyIds[i]);
        }

        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * setDefaultTableWidth
     *
     * @param table
     * @param container
     */
    public void setDefaultTableWidth(final ExtFilterTable table, final BeanItemContainer<CustomerDetailsDTO> container) {

        try {
            table.setColumnCollapsingAllowed(true);
            int browserWidth = Page.getCurrent().getBrowserWindowWidth();
            if (browserWidth > ConstantsUtils.PX_1516) {

                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getSixColumns(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }

            } else if (browserWidth < ConstantsUtils.PX_1516 && browserWidth > NumericConstants.NINE_SEVEN_EIGHT) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.SIX_HUNDRED) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }

                if (container != null && container.getItemIds().isEmpty()) {
                    for (Object propertyId : getCollapsibleColumns978Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                } else {
                    for (Object propertyId : getCollapsibleColumns600Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                }
            } else if (browserWidth < NumericConstants.SIX_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns600Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < NumericConstants.THREE_TWO_ZERO) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * getSixColumns
     *
     * @param table
     * @return object
     */
    private static Object[] getSixColumns(ExtFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        table.setImmediate(true);
        Object[] visibleColumns = table.getVisibleColumns();
        Object[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, Object[].class);
        List<Object> list = new ArrayList<>(Arrays.asList(visibleColumns));
        for (int i = 0; i < NumericConstants.SIX; i++) {
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

    public void backButton() {

        backBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a {@link Button} has been clicked. A reference to the
             * button is given by {@link ClickEvent#getButton()}.
             *
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields backButton  navigateTo CustomerGroupSearchIndex");

                final String viewPage = sessionDTO.getFromViewPage();

                if (viewPage.equals("YES")) {
                    getUI().getNavigator().navigateTo(CustomerGroupSearchMain.NAME);
                } else {
                    MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, " Any unsaved information will not be saved. Do you want to proceed"
                            + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                try {
                                    getUI().getNavigator().navigateTo(CustomerGroupSearchMain.NAME);
                                    
                                    
                                } catch (Exception e) {
                                    LOGGER.error(e);
                                }
                            }

                        }
                    }, ButtonId.YES, ButtonId.NO);
                }
            }
        });
    }

    private void getResponsiveFirstTab(final Map<String, AppPermission> fieldItemHM) {
        LOGGER.debug("Entering getFirstTab1");
        try {
            String mode = sessionDTO.getMode();
            List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.CUSTOMER_GROUP_MASTER, StringConstantUtils.FUNCTIONAL_SCREEN);
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldItemHM, mode);
            commonSecurityLogic.removeComponentOnPermission(resultList, isCssLayout, fieldItemHM, mode);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending getFirstTab1");
    }

    private void getButtonPermission(Map<String, AppPermission> functionCompanyHM) {

        backButton();

        if (functionCompanyHM.get(ConstantsUtils.SEARCH_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.SEARCH_BUTTON)).isFunctionFlag()) {

            searchBtn.setVisible(false);
        } else {
            searchButton();
        }
        if (functionCompanyHM.get(ConstantsUtils.SAVE_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.SAVE_BUTTON)).isFunctionFlag()) {
            savetBtn.setVisible(false);
        } else {
            saveButton();
        }
        if (functionCompanyHM.get(ConstantsUtils.REMOVE_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.REMOVE_BUTTON)).isFunctionFlag()) {
            removeBtn.setVisible(false);
        } else {
            removeButton();
        }
        if (functionCompanyHM.get(ConstantsUtils.ADD_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.ADD_BUTTON)).isFunctionFlag()) {
            addBtn.setVisible(false);
        } else {
            addButton();
        }
        if (functionCompanyHM.get(ConstantsUtils.ADD_ALL_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.ADD_ALL_BUTTON)).isFunctionFlag()) {
            addallBtn.setVisible(false);
        } else {
            addAllButton();
        }
        if (functionCompanyHM.get(ConstantsUtils.REMOVE_ALL_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.REMOVE_ALL_BUTTON)).isFunctionFlag()) {
            removeallBtn.setVisible(false);
        } else {
            removeAllButton();
        }
        if (functionCompanyHM.get(ConstantsUtils.RESET_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.RESET_BUTTON)).isFunctionFlag()) {
            resetBtn.setVisible(false);
        } else {
            resetButton();
        }
        if (functionCompanyHM.get(ConstantsUtils.RESET_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.RESET_BUTTON)).isFunctionFlag()) {
            resetbutton.setVisible(false);
        } else {
            resetAllButton();
        }
    }

    private void searchButton() {
        searchBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a {@link Button} has been clicked. A reference to the
             * button is given by {@link ClickEvent#getButton()}.
             *
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields searchButtonClickLogic started");
                try {
                    if (StringUtils.isBlank(customerNo.getValue()) && (tradeClassDdlb.getValue() == null || String.valueOf(tradeClassDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))
                            && (customerStatusDdlb.getValue() == null || String.valueOf(customerStatusDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))
                            && StringUtils.isBlank(customerName.getValue()) && (stateDdlb.getValue() == null || String.valueOf(stateDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))
                            && StringUtils.isBlank(city.getValue()) && (customerTypeDdlb.getValue() == null || String.valueOf(customerTypeDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))
                            && StringUtils.isBlank(zipCode.getValue())) {

                        MessageBox.showPlain(Icon.ERROR, "No Search Criteria", "No search criteria were found. Please enter search criteria and try again", ButtonId.OK);

                    } else {
                        searchButtonClickLogic();
                    }

                } catch (Exception e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
                }
                LOGGER.debug("In configureFields searchButtonClickLogic Ended");
            }
        });
    }

    private void saveButton() {
        savetBtn.addClickListener(new Button.ClickListener() {
            /**
             * Adds the button click listener.
             *
             *
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields saveButtonClick started");
                boolean saved = saveGroupInfo("save");
                if (!saved) {
                    return;
                }

                LOGGER.debug("In configureFields saveButtonClick Ended");
            }
        });
    }

    private void removeButton() {
        removeBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a {@link Button} has been clicked. A reference to the
             * button is given by {@link ClickEvent#getButton()}.
             *
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields removeItemsButtonClick started");
                if (selectedResults.getValue() != null && !((Set) selectedResults.getValue()).isEmpty()) {
                    removeCustomers();
                    customerDetailsDTO.setMaster_Sid_List(masterSidsList);
                    customerDetailsDTO.setEditMode(Boolean.FALSE);
                    selectedTableLogic.loadSetData(customerDetailsDTO);
                    availableTableLogic.loadSetData(customerDetailsDTO);
                    selectedResults.setValue(null);
                } else {
                    MessageBox.showPlain(Icon.ERROR, ConstantsUtils.NO_RECORDS_SELECTED, "No customers were selected in the Selected Customers list view.  Please select at least one customer and try again.",
                            ButtonId.OK);
                }
                LOGGER.debug("In configureFields removeItemsButtonClick Ended");
            }
        });
    }

    private void addButton() {
        addBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when button has clicked .
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields addItemsButtonClick started");
                if (availableResults.getValue() != null && !((Set) availableResults.getValue()).isEmpty()) {
                    if (addCustomers()) {
                        customerDetailsDTO.setMaster_Sid_List(masterSidsList);
                        customerDetailsDTO.setEditMode(Boolean.FALSE);
                        selectedTableLogic.loadSetData(customerDetailsDTO);
                        availableTableLogic.loadSetData(customerDetailsDTO);
                    }
                    availableResults.setValue(null);
                } else {
                    MessageBox.showPlain(Icon.ERROR, ConstantsUtils.NO_RECORDS_SELECTED, "No Customers were selected in the Results list view.  Please select at least one Customer and try again.", ButtonId.OK);
                }
                LOGGER.debug("In configureFields addItemsButtonClick Ended");
            }
        });
    }

    private void addAllButton() {
        addallBtn.addClickListener(new Button.ClickListener() {
            /**
             * Interface for listening for a {@link ClickEvent} fired by a
             * {@link Component}.
             *
             *
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields addAllItemsButtonClick started");
                if (availableResultsBean.size() > ConstantsUtils.ZERO_NUM) {
                    addAllCustomers();
                    customerDetailsDTO.setMaster_Sid_List(masterSidsList);
                    customerDetailsDTO.setEditMode(Boolean.FALSE);
                    selectedTableLogic.loadSetData(customerDetailsDTO);
                    availableTableLogic.loadSetData(customerDetailsDTO);
                } else {
                    MessageBox.showPlain(Icon.ERROR, ConstantsUtils.NO_RECORDS_SELECTED, "No items were selected in the Results list view.  Please select at least one Customer and try again.", ButtonId.OK);
                }
                LOGGER.debug("In configureFields addAllItemsButtonClick Ended");
            }
        });
    }

    private void removeAllButton() {
        removeallBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a {@link Button} has been clicked. A reference to the
             * button is given by {@link ClickEvent#getButton()}.
             *
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields removeAllItemsButtonClick started");
                if (selectedResultsBean.size() > ConstantsUtils.ZERO_NUM) {
                    removeAllCustomers();
                    customerDetailsDTO.setMaster_Sid_List(masterSidsList);
                    customerDetailsDTO.setEditMode(Boolean.FALSE);
                    selectedTableLogic.loadSetData(customerDetailsDTO);
                    availableTableLogic.loadSetData(customerDetailsDTO);
                } else {
                    MessageBox.showPlain(Icon.ERROR, ConstantsUtils.NO_RECORDS_SELECTED, "No customers were selected in the Selected Customers list view.  Please select at least one customer and try again.",
                            ButtonId.OK);
                }
                LOGGER.debug("In configureFields removeAllItemsButtonClick Ended");
            }
        });
    }

    private void resetButton() {
        resetBtn.addClickListener(new Button.ClickListener() {
            /**
             * Adds the button click listener.
             *
             *
             */
            public void buttonClick(final Button.ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values"
                        + " ?", new MessageBoxListener() {
                    /**
                     * Click event. This event is thrown, when the button is
                     * clicked.
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            LOGGER.debug("In configureFields resetButtonClickLogic started");
                            resetButtonClickLogic();
                            LOGGER.debug("In configureFields resetButtonClickLogic Ended");
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });
    }

    private void resetAllButton() {
        resetbutton.addClickListener(new Button.ClickListener() {
            /**
             * Called when a {@link Button} has been clicked. A reference to the
             * button is given by {@link ClickEvent#getButton()}.
             *
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields resetAllButtonClickLogic Started");
                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values?", new MessageBoxListener() {
                    /**
                     * Adds the button click listener.
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            resetAllButtonClickLogic();
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
                LOGGER.debug("In configureFields resetAllButtonClickLogic Ended");
            }
        });
    }

    HorizontalLayout createControls(CGMTableLogic tableLogic) {
        HorizontalLayout controls = tableLogic.createControls();
        HorizontalLayout controlLayout = ResponsiveUtils.getResponsiveControls(controls);
        return controlLayout;
    }

    /**
     * Remove add logic
     *
     * @param isAvailable
     */
    private boolean addCustomers() {
        Set<CustomerDetailsDTO> itemMasterDetailsList = (Set) availableResults.getValue();
        boolean flag = Boolean.FALSE;
        for (final Iterator<CustomerDetailsDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
            final CustomerDetailsDTO item = iterator.next();
            String masterId = String.valueOf(item.getCompanySystemId());
            if (masterSidsList.add(masterId)) {
                flag = Boolean.TRUE;
            }
        }
        return flag;
    }

    /**
     * Remove add logic
     *
     * @param isAvailable
     */
    private void removeCustomers() {
        Set<CustomerDetailsDTO> itemMasterDetailsList = (Set) selectedResults.getValue();
        for (final Iterator<CustomerDetailsDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
            final CustomerDetailsDTO item = iterator.next();
            String masterId = String.valueOf(item.getCompanySystemId());
            masterSidsList.remove(masterId);
        }
    }

    /**
     * Method to get ALL the Selected Company Ids
     *
     */
    private void addAllCustomers() {
        masterSidsList.addAll(logic.getCompaniesFromQuery(customerDetailsDTO, availableTableLogic.getFilters(), availableTableLogic.getSortByColumns()));
    }

    /**
     * Method to get All Available Company Ids
     */
    private void removeAllCustomers() {
        masterSidsList.removeAll(logic.getCompaniesFromQuery(customerDetailsDTO, selectedTableLogic.getFilters(), selectedTableLogic.getSortByColumns()));
    }

    /**
     * This the Logic function which will be invoked whenever SAVE button is
     * triggered
     */
    private void saveLogic() throws SystemException {
        customerGroupDTO.setSearchCriteria("");
        List<Integer> idList = logic.saveCGM(customerGroupDTO, sessionDTO, masterSidsList);
        String page = sessionDTO.getFromViewPage();
        if (page.equalsIgnoreCase(ConstantsUtils.LOWERCASE_COPY)) {
            sessionDTO.setSystemId(0);
        }
        if (!idList.isEmpty()) {
            if (page.equalsIgnoreCase(ConstantsUtils.ADD) || page.equalsIgnoreCase(ConstantsUtils.COPY)) {
                sessionDTO.setSystemId(idList.get(0));
                sessionDTO.setFromViewPage(ConstantsUtils.OPTION_NO);
                sessionDTO.setVersionNo(idList.get(1));
                sessionDTO.setLogic("edit");
                sessionDTO.setMode(ConstantsUtils.EDIT);
                LOGGER.debug("In configureFields editButtonClickLogic navigateTo CustomerGroupView");
                getUI().getNavigator().navigateTo(CustomerGroupView.NAME);
            }
            final Notification notif = new Notification(customerGroupName.getValue() + StringConstantUtils.HAS_BEEN_SUCCESSFULLY_SAVED, Notification.Type.HUMANIZED_MESSAGE);
            notif.setPosition(Position.BOTTOM_CENTER);
            notif.setStyleName(StringConstantUtils.MYSTYLE);
            notif.setDelayMsec(NumericConstants.THREE_THOUSAND);
            notif.show(Page.getCurrent());
        } else {
            customerBinder.getErrorDisplay().setError(ConstantsUtils.ERROR_OCCURED);
        }
    }

    /**
     * This method is to load not in ADD mode
     */
    private void loadSelectedTableOnEdit() {
        masterSidsList.clear();
        masterSidsList.addAll(logic.getSelectedCompanyMasterSids(sessionDTO.getSystemId()));
        customerDetailsDTO.setMaster_Sid_List(masterSidsList);
        customerDetailsDTO.setEditMode(Boolean.TRUE);
        customerDetailsDTO.setCompanySystemId(sessionDTO.getSystemId());
        customerDetailsDTO.setVersionNo(sessionDTO.getVersionNo());
        customerDetailsDTO.setGenerate(Boolean.TRUE);
        selectedTableLogic.loadSetData(customerDetailsDTO);
    }

    /**
     * To configure Excel Results Table
     */
    private void configureExcelAvailableResultTable() {
        availableExcelResultsBean = new BeanItemContainer<>(CustomerDetailsDTO.class);
        excelAvailableTable = new ExtFilterTable();
        excelLayoutAvailable.addComponent(excelAvailableTable);
        excelAvailableTable.setVisible(false);
        excelAvailableTable.addStyleName("table-header-normal");
        excelAvailableTable.addStyleName(ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE);
        excelAvailableTable.setContainerDataSource(availableExcelResultsBean);
        excelAvailableTable.setVisibleColumns(availableResults.getVisibleColumns());
        excelAvailableTable.setColumnHeaders(availableResults.getColumnHeaders());
        excelAvailableTable.setConverter(StringConstantUtils.TRADE_CLASS_START_DATE, new DateToStringConverter());
        excelAvailableTable.setConverter(StringConstantUtils.TRADE_CLASS_END_DATE, new DateToStringConverter());
        excelAvailableTable.setConverter(StringConstantUtils.PARENT_START_DATE, new DateToStringConverter());
        excelAvailableTable.setConverter(StringConstantUtils.PARENT_END_DATE, new DateToStringConverter());
        excelAvailableTable.setConverter(StringConstantUtils.CUSTOMER_START_DATE, new DateToStringConverter());
        excelAvailableTable.setConverter(StringConstantUtils.CUSTOMER_END_DATE, new DateToStringConverter());
        excelAvailableTable.setConverter(StringConstantUtils.PRIOR_PARENT_START_DATE, new DateToStringConverter());
        for (Object object : excelAvailableTable.getVisibleColumns()) {
            if (object.toString().contains("Date")) {
                excelAvailableTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
            }
        }
        final Object[] obj = new Object[]{StringConstantUtils.TRADE_CLASS_START_DATE, StringConstantUtils.TRADE_CLASS_END_DATE, StringConstantUtils.CUSTOMER_END_DATE, StringConstantUtils.PARENT_START_DATE, StringConstantUtils.PARENT_END_DATE, StringConstantUtils.CUSTOMER_START_DATE, StringConstantUtils.PRIOR_PARENT_START_DATE};
        excelAvailableTable = CommonUtil.getFormattedTable(excelAvailableTable, obj);
        excelAvailableTable.markAsDirtyRecursive();

    }

    /**
     * To load excel Table similar to Table in UI
     *
     * @param tableFieldLookUpDTO
     * @throws Exception
     */
    private void loadAvailableExcelTable() {
        availableExcelResultsBean.removeAllItems();
        if (availableResults.size() != 0) {
            try {
                customerDetailsDTO.setCount(Boolean.TRUE);
                customerDetailsDTO.setQueryName("getCustomerDetailsCount");
                customerDetailsDTO.setAvailableContainer(Boolean.TRUE);
                int count = logic.getCompaniesCount(customerDetailsDTO, availableTableLogic.getFilters());
                customerDetailsDTO.setCount(Boolean.FALSE);
                customerDetailsDTO.setStart(0);
                customerDetailsDTO.setOffset(count);
                customerDetailsDTO.setQueryName("getCustomerDetails");
                List<CustomerDetailsDTO> resultList = (List<CustomerDetailsDTO>) logic.getCompaniesResults(customerDetailsDTO, availableTableLogic.getFilters(), availableTableLogic.getSortByColumns());
                availableExcelResultsBean.addAll(resultList);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
    }

    /**
     * To configure Excel Results Table
     */
    private void configureExcelSelectedResultTable() {
        selectedExcelResultsBean = new BeanItemContainer<>(CustomerDetailsDTO.class);
        excelSelectedTable = new ExtFilterTable();
        excelLayoutSelected.addComponent(excelSelectedTable);
        excelSelectedTable.setVisible(false);
        excelSelectedTable.setContainerDataSource(selectedExcelResultsBean);
        excelSelectedTable.setVisibleColumns(selectedResults.getVisibleColumns());
        excelSelectedTable.setColumnHeaders(selectedResults.getColumnHeaders());
        excelSelectedTable.addStyleName("table-header-normal");
        excelSelectedTable.addStyleName(ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE);
        excelSelectedTable.setConverter(StringConstantUtils.TRADE_CLASS_START_DATE, new DateToStringConverter());
        excelSelectedTable.setConverter(StringConstantUtils.TRADE_CLASS_END_DATE, new DateToStringConverter());
        excelSelectedTable.setConverter(StringConstantUtils.PARENT_START_DATE, new DateToStringConverter());
        excelSelectedTable.setConverter(StringConstantUtils.PARENT_END_DATE, new DateToStringConverter());
        excelSelectedTable.setConverter(StringConstantUtils.CUSTOMER_START_DATE, new DateToStringConverter());
        excelSelectedTable.setConverter(StringConstantUtils.CUSTOMER_END_DATE, new DateToStringConverter());
        excelSelectedTable.setConverter(StringConstantUtils.PRIOR_PARENT_START_DATE, new DateToStringConverter());
        for (Object object : excelSelectedTable.getVisibleColumns()) {
            if (object.toString().contains("Date")) {
                excelSelectedTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
            }
        }
        final Object[] obj = new Object[]{StringConstantUtils.TRADE_CLASS_START_DATE, StringConstantUtils.TRADE_CLASS_END_DATE, StringConstantUtils.CUSTOMER_END_DATE, StringConstantUtils.PARENT_START_DATE, StringConstantUtils.PARENT_END_DATE, StringConstantUtils.CUSTOMER_START_DATE, StringConstantUtils.PRIOR_PARENT_START_DATE};
        excelSelectedTable = CommonUtil.getFormattedTable(excelSelectedTable, obj);
        excelSelectedTable.markAsDirtyRecursive();

    }

    /**
     * To load excel Table similar to Table in UI
     *
     * @param tableFieldLookUpDTO
     * @throws Exception
     */
    private void loadSelectedExcelTable() {
        try {
            selectedExcelResultsBean.removeAllItems();
            customerDetailsDTO.setCount(Boolean.TRUE);
            if (!customerDetailsDTO.isEditMode()) {
                customerDetailsDTO.setQueryName("getSelectedCustomerDetailsCount");
            } else {
                customerDetailsDTO.setQueryName("getSelectedCompanyListCount");
            }

            customerDetailsDTO.setAvailableContainer(Boolean.FALSE);
            int count = logic.getCompaniesCount(customerDetailsDTO, selectedTableLogic.getFilters());
            if (!customerDetailsDTO.isEditMode()) {
                customerDetailsDTO.setQueryName("getSelectedCustomerDetails");
            } else {
                customerDetailsDTO.setQueryName("getSelectedCompanyList");
            }
            customerDetailsDTO.setCount(Boolean.FALSE);
            customerDetailsDTO.setStart(0);
            customerDetailsDTO.setOffset(count);
            customerDetailsDTO.setAvailableContainer(Boolean.FALSE);
            List<CustomerDetailsDTO> resultList = logic.getCompaniesResults(customerDetailsDTO, selectedTableLogic.getFilters(), selectedTableLogic.getSortByColumns());
            selectedExcelResultsBean.addAll(resultList);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
