/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.customergroup.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.adminconsole.abstractsearch.ui.AbstractSearchForm;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.customergroup.dto.CustomerDetailsDTO;
import com.stpl.app.adminconsole.customergroup.dto.CustomerGroupDTO;
import com.stpl.app.adminconsole.customergroup.logic.CustomerGroupLogic;
import com.stpl.app.adminconsole.customergroup.ui.view.CustomerGroupView;
import com.stpl.app.adminconsole.itemgroup.util.UISecurityUtil;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;

import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.CommonSecurityLogic;

import com.stpl.ifs.ui.util.CommonUIUtils;
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
import org.apache.commons.lang.StringUtils;
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
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;
    @UiField("tableLayout1")
    private VerticalLayout tableLayout1;
    @UiField("controlLayout1")
    private HorizontalLayout controlLayout1;
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
    /**
     * The available results.
     */
    private ExtFilterTable availableResults = new ExtFilterTable();
    /**
     * The selected results.
     */
    private ExtFilterTable selectedResults = new ExtFilterTable();
    /**
     * The available results bean.
     */
    private BeanItemContainer<CustomerDetailsDTO> availableResultsBean = new BeanItemContainer<CustomerDetailsDTO>(CustomerDetailsDTO.class);
    /**
     * The selected results bean.
     */
    private BeanItemContainer<CustomerDetailsDTO> selectedResultsBean = new BeanItemContainer<CustomerDetailsDTO>(CustomerDetailsDTO.class);
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
    private Boolean recordAddFlag = false;
    /**
     * The record selected flag.
     */
    private Boolean recordRemoveFlag = false;
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
    public void setAvailableResults(final ExtFilterTable availableResults) {
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
    public void setSelectedResults(final ExtFilterTable selectedResults) {
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
    private boolean shouldSave = false;
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
            final BeanItemContainer selectedResultsBean, final BeanItemContainer availableResultsBean, final SessionDTO sessionDTO) throws SystemException, PortalException, Exception {
        super();
        LOGGER.info("Entering CustomerGroupInfo");
        this.customerGroupDTO = customerGroupDTO;
        this.customerGroupBinder = customerGroupBinder;
        this.customerBinder = customerBinder;
        this.customerDetailsDTO = customerDetailsDTO;
        this.selectedResultsBean = selectedResultsBean;
        this.availableResultsBean = availableResultsBean;
        this.sessionDTO = sessionDTO;
        logic = new CustomerGroupLogic(sessionDTO);
        init();

        LOGGER.info("CustomerGroupInfo Constructor Ended");

    }

    /**
     * Inits the.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public final void init() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering init Method");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/commonclara/customergroupmaster.xml"), this));
        getCustomerGroupBinder();
        getItemBinder();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = sessionDTO.getUserId();
        final Map<String, AppPermission> fieldItemHM = stplSecurity
                .getFieldOrColumnPermission(userId, UISecurityUtil.CUSTOMER_GROUP_MASTER + "," + "Functional Screen", false);
        final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.CUSTOMER_GROUP_MASTER + "," + "Functional Screen");
        getResponsiveFirstTab(fieldItemHM);
        configureFields();
        getButtonPermission(functionCompanyHM);
        addItemAvailableResults();
        addItemSelectedResults();
        LOGGER.info("init Method Ended");
    }

    private ErrorfulFieldGroup getCustomerGroupBinder() {
        LOGGER.info("Entering getCustomerGroupBinder method");
        customerGroupBinder.bindMemberFields(this);
        customerGroupBinder.setItemDataSource(new BeanItem<CustomerGroupDTO>(customerGroupDTO));
        customerGroupBinder.setBuffered(true);
        customerGroupBinder.setErrorDisplay(errorMsg);
        LOGGER.info("getCustomerGroupBinder method RETURNS customerGroupBinder ");
        return customerGroupBinder;
    }

    /**
     * Gets the item binder.
     *
     * @return the item binder
     */
    private ErrorfulFieldGroup getItemBinder() {

        LOGGER.info("getItemBinder method Started ");
        customerBinder.bindMemberFields(this);
        customerBinder.setItemDataSource(new BeanItem<CustomerDetailsDTO>(customerDetailsDTO));
        customerBinder.setBuffered(true);
        customerBinder.setErrorDisplay(errorMsg);
        LOGGER.info("getItemBinder method RETURNS customerBinder ");

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
            LOGGER.info("addItemAvailableResults mehod Started ");

            tableLayout.addComponent(availableResults);

            tableLayout.addComponent(controlLayout);

            availableResults.markAsDirty();
            availableResults.setContainerDataSource(availableResultsBean);
            final StplSecurity stplSecurity = new StplSecurity();

            final String userId = sessionDTO.getUserId();
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CUSTOMER_GROUP_MASTER + "," + "Functional List view", false);
            String mode = sessionDTO.getMode();

            List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.CUSTOMER_GROUP_MASTER, "Functional List view");
            Object[] objColumn = CommonUIUtils.CUSTOMER_RESULTS_COLUMNS;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objColumn, fieldIfpHM, mode);

            availableResults.setFilterBarVisible(true);
            availableResults.setFilterDecorator(new ExtDemoFilterDecorator());

            if (tableResultCustom.getObjResult().length > 0) {
                availableResults.setVisibleColumns(tableResultCustom.getObjResult());
                availableResults.setColumnHeaders(tableResultCustom.getObjResultHeader());
            } else {
                tableLayout.setVisible(false);
            }

            availableResults.setPageLength(5);
            availableResults.setMultiSelect(true);
            availableResults.setWidth("100%");
            availableResults.setWidth(ConstantsUtils.WIDTH);
            availableResults.setImmediate(true);
            availableResults.setSelectable(true);
            availableResults.setSizeFull();

            availableResults.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Notifies this listener that the Property's value has changed.
                 *
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final Property.ValueChangeEvent event) {

                    recordAddFlag = true;
                    recordRemoveFlag = false;
                }
            });
            availableResults.setErrorHandler(new ErrorHandler() {
                /**
                 * Interface for listening to errors in the application.
                 */
                public void error(final com.vaadin.server.ErrorEvent event) {
                   
                }
            });

            availableResults.addItemClickListener(new ItemClickEvent.ItemClickListener() {
                /**
                 * method to listen the action
                 *
                 */
                public void itemClick(final ItemClickEvent event) {
                    if (event.isDoubleClick()) {
                        addDoubleClick(event);
                    }
                }
            });

            final Object[] obj = new Object[]{"tradeClassStartDate", "tradeClassEndDate", "customerEndDate", "parentStartDate", "parentEndDate", "customerStartDate", "priorParentStartDate"};
            availableResults = CommonUtil.getFormattedTable(availableResults, obj);
            LOGGER.info("addItemAvailableResults mehod RETURNS availableResults - ExtFilterTable ");

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
            LOGGER.info("addItemSelectedResults mehod Started ");

            tableLayout1.addComponent(selectedResults);

            tableLayout1.addComponent(controlLayout1);

            selectedResults.markAsDirty();
            selectedResults.setContainerDataSource(selectedResultsBean);
            selectedResults.setFilterBarVisible(true);
            selectedResults.setFilterDecorator(new ExtDemoFilterDecorator());
            final StplSecurity stplSecurity = new StplSecurity();

            final String userId = sessionDTO.getUserId();
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CUSTOMER_GROUP_MASTER + "," + "Functional List view", false);
            String mode = sessionDTO.getMode();

            List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.CUSTOMER_GROUP_MASTER, "Functional List view");
            Object[] objColumn = CommonUIUtils.CUSTOMER_RESULTS_COLUMNS;
            TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, objColumn, fieldIfpHM, mode);

            if (tableResultCustom.getObjResult().length > 0) {
                selectedResults.setVisibleColumns(tableResultCustom.getObjResult());
                selectedResults.setColumnHeaders(tableResultCustom.getObjResultHeader());
            } else {
                tableLayout1.setVisible(false);
            }
            selectedResults.setPageLength(5);
            selectedResults.setMultiSelect(true);
            selectedResults.setWidth(ConstantsUtils.WIDTH);
            selectedResults.setImmediate(true);
            selectedResults.setSelectable(true);

            selectedResults.setSizeFull();
            selectedResults.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * method to listen value change action .
                 *
                 *
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final Property.ValueChangeEvent event) {

                    recordAddFlag = false;
                    recordRemoveFlag = true;
                }
            });
            selectedResults.setErrorHandler(new ErrorHandler() {
                /**
                 * Interface for listening to errors in the application.
                 */
                public void error(final com.vaadin.server.ErrorEvent event) {
                  
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
                        removeItemsButtonClick(event);
                    }
                }
            });
            final Object[] obj = new Object[]{"tradeClassStartDate", "tradeClassEndDate", "customerEndDate", "parentStartDate", "parentEndDate", "customerStartDate", "priorParentStartDate"};
            selectedResults = CommonUtil.getFormattedTable(selectedResults, obj);

            LOGGER.info("addItemSelectedResults mehod RETURNS selectedResults ");

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
        LOGGER.info("Entering resultsItemClick mehod");
        if (systemId == null) {
            recordSelectedFlag = false;
        } else {
            recordSelectedFlag = true;
        }
        LOGGER.info("resultsItemClick mehod Ended");
    }

    /**
     * Configure fields.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void configureFields() throws SystemException, PortalException, Exception {

        LOGGER.info("configureFields mehod Initiated ");
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

        LOGGER.info("In configureFields loadCustomerStatus started");
        customerStatusDdlb = CommonUtil.getComboBox(customerStatusDdlb, "STATUS");
        customerStatusDdlb.setNullSelectionAllowed(true);
        customerStatusDdlb.setInputPrompt(ConstantsUtils.SELECT_ONE);

        customerStatusDdlb.setImmediate(true);
        LOGGER.info("In configureFields loadCustomerStatus Ended");
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
        LOGGER.info("In configureFields loadTradeClass started");

        tradeClassDdlb = CommonUtil.getComboBox(tradeClassDdlb, "COMPANY_TRADE_CLASS");
        tradeClassDdlb.setNullSelectionAllowed(true);
        tradeClassDdlb.setInputPrompt(ConstantsUtils.SELECT_ONE);

        tradeClassDdlb.setImmediate(true);
        LOGGER.info("In configureFields loadTradeClass Ended");
        tradeClassDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             *
             */
            public void valueChange(final ValueChangeEvent event) {
                tradeClassOnChangeEvent(event.getProperty().getValue());

            }
        });
        LOGGER.info("In configureFields loadCustomerType started");

        customerTypeDdlb = CommonUtil.getComboBox(customerTypeDdlb, "COMPANY_TYPE");
        customerTypeDdlb.setNullSelectionAllowed(true);
        customerTypeDdlb.setInputPrompt(ConstantsUtils.SELECT_ONE);

        customerTypeDdlb.setImmediate(true);
        LOGGER.info("In configureFields loadCustomerType Ended");
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
                LOGGER.info("In configureFields availableResultsExcelExport Started");
                if (availableResults.size() > ConstantsUtils.ZERO_NUM) {
                    List<List> exportList;
                    final List<String> dollarList = new ArrayList();
                    dollarList.add("AdminConsole");
                    exportList = new ArrayList<List>();
                    exportList.add(dollarList);
                    exportList.add(null);
                    exportList.add(null);
                    VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, "true");
                    ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(availableResults), "Available Customer Results", "Available Customer Results", "AvailableCustomerResults.xls", false);
                    excel.export();
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results to Export", ButtonId.OK);
                }
                LOGGER.info("In configureFields availableResultsExcelExport Ended");
            }
        });

        excel1.addClickListener(new ClickListener() {
            /**
             * Adds the button click listener.
             *
             *
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("In configureFields selectedResultsExcelExport Started");
                if (selectedResults.size() > ConstantsUtils.ZERO_NUM) {

                    VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, "true");
                    ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(selectedResults), "Selected Customer Results", "Selected Customer Results", "SelectedCustomerResults.xls", false);
                    excel.export();
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results to Export", ButtonId.OK);
                }
                LOGGER.info("In configureFields selectedResultsExcelExport Ended");
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

        LOGGER.info("In configureFields Ended");

    }

    /**
     * Load customer status.
     *
     * @throws SystemException the system exception
     */
    private void loadCustomerStatus() throws SystemException {
        LOGGER.info("Entering loadCustomerStaus method");

        customerStatusDdlb = CommonUtil.getComboBox(customerStatusDdlb, "STATUS");
        LOGGER.info(" loadCustomerStaus Ended");
    }

    /**
     * Load customer type.
     *
     * @throws SystemException the system exception
     */
    private void loadCustomerType() throws SystemException {
        LOGGER.info("Entering loadCustomerType method");

        customerTypeDdlb = CommonUtil.getComboBox(customerTypeDdlb, "COMP_TYPE");
        LOGGER.info("LoadCustomerType method Ended");
    }

    /**
     * Load trade class.
     *
     * @throws SystemException the system exception
     */
    private void loadTradeClass() throws SystemException {
        LOGGER.info("Entering loadTradeClass method");

        tradeClassDdlb = CommonUtil.getComboBox(tradeClassDdlb, "COMP_TRADECLASS");

        LOGGER.info("loadTradeClass method ended");
    }

    /**
     * Customer status on change event.
     *
     * @param companyValue the company value
     */
    protected void customerStatusOnChangeEvent(final Object companyValue) {
        LOGGER.info("Entering customerStatusOnChangeEvent method");
        if (customerStatusDdlb.getValue() == null) {
            customerStatus.setValue(null);
        } else {
            customerStatus.setValue(companyValue.toString());
        }
        LOGGER.info(" customerStatusOnChangeEvent method ended");
    }

    /**
     * Trade class on change event.
     *
     * @param companyValue the company value
     */
    protected void tradeClassOnChangeEvent(final Object companyValue) {
        LOGGER.info("Entering tradeClassOnChangeEvent method with companyValue");

        if (tradeClassDdlb.getValue() == null) {
            tradeClass.setValue(null);
        } else {
            tradeClass.setValue(companyValue.toString());
        }
        LOGGER.info(" tradeClassOnChangeEvent method Ended");
    }

    /**
     * Customer type on change event.
     *
     * @param companyValue the company value
     */
    protected void customerTypeOnChangeEvent(final Object companyValue) {

        LOGGER.info("Entering customerTypeOnChangeEvent method with companyValue ");

        if (customerTypeDdlb.getValue() == null) {
            customerType.setValue(null);
        } else {
            customerType.setValue(companyValue.toString());
        }

        LOGGER.info("customerTypeOnChangeEvent method Ended");
    }

    /**
     * state on change event.
     *
     * @param object
     *
     */
    protected void stateOnChangeEvent(final Object object) {

        LOGGER.info("Entering stateOnChangeEvent method with object ");

        if (stateDdlb.getValue() == null) {
            state.setValue(null);
        } else {
            state.setValue(object.toString());
        }

        LOGGER.info("stateOnChangeEvent method Ended");
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

    protected void searchButtonClickLogic(final Button.ClickEvent event) throws SystemException, PortalException, Exception {
        LOGGER.info("Entering searchButtonClickLogic");
        count++;
        customerBinder.commit();
        availableResultsBean.removeAllItems();
        final List<CustomerDetailsDTO> searchResults = logic.getCustomerSearchResults(customerBinder, count, 0, 0);
        searchCriteria += logic.getSearchCriteria(customerBinder, count).get(8);

        if (searchResults.isEmpty()) {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results could be found that match the entered search criteria.", ButtonId.OK);
        } else {
            availableResultsBean.addAll(searchResults);
            CommonUIUtils.getMessageNotification(ConstantsUtils.SEARCH_COMPLETED);
        }

        LOGGER.info("searchButtonClickLogic Method ended");
    }

    /**
     * Reset button click logic.
     *
     */
    protected void resetButtonClickLogic() {

        LOGGER.info("resetButtonClickLogic Started");
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
        LOGGER.info("resetButtonClickLogic Ended");

    }

    /**
     * Adds the items button click.
     *
     * @param event the event
     */
    protected void addItemsButtonClick(final ClickEvent event) {

        LOGGER.info("Entering addItemsButtonClick method ");
        final java.util.Set<CustomerDetailsDTO> itemMasterDetailsList = (java.util.Set<CustomerDetailsDTO>) availableResults.getValue();
        boolean flag = false;
        for (final Iterator<CustomerDetailsDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
            final CustomerDetailsDTO item = iterator.next();
            flag = false;
            if (selectedResultsBean.size() > ConstantsUtils.ZERO_NUM) {
                for (int j = 0; j < selectedResultsBean.size(); j++) {
                    if (selectedResultsBean.getIdByIndex(j).getCustomerId().equalsIgnoreCase(item.getCustomerId()) && selectedResultsBean.getIdByIndex(j).getCustomerNo().equalsIgnoreCase(item.getCustomerNo())
                            && selectedResultsBean.getIdByIndex(j).getCustomerName().equalsIgnoreCase(item.getCustomerName()) && selectedResultsBean.getIdByIndex(j).getTradeClassSysId() == (item.getTradeClassSysId())) {
                        flag = false;
                        break;
                    } else {
                        flag = true;
                    }
                }
                if (flag) {
                    selectedResultsBean.addItem(item);
                }
            } else {
                flag = true;
                selectedResultsBean.addItem(item);
            }
        }
        if (flag) {
            for (final Iterator<CustomerDetailsDTO> iterator = itemMasterDetailsList.iterator(); iterator.hasNext();) {
                final CustomerDetailsDTO item = iterator.next();
                availableResults.removeItem(item);
            }
        }
        selectedResults.setValue(null);
        availableResults.setValue(null);
        LOGGER.info("addItemsButtonClick method Ended");

    }

    /**
     * Adds the double click.
     *
     * @param event the event
     */
    protected void addDoubleClick(final ItemClickEvent event) {
        LOGGER.info("Entering addItemsButtonClick method ");

        final Object itemId = event.getItemId();
        selectedResults.addItem(itemId);
        availableResults.removeItem(itemId);
        shouldSave = true;
        selectedResults.setValue(null);
        availableResults.setValue(null);
        LOGGER.info(" addItemsButtonClick method Ended");
    }

    /**
     * Adds the all items button click.
     *
     * @param event the event
     */
    protected void addAllItemsButtonClick(final ClickEvent event) {

        LOGGER.info("Entering addAllItemsButtonClick method ");
        for (int i = 0; i < availableResultsBean.size(); i++) {
            boolean flag = false;

            if (selectedResultsBean.size() > ConstantsUtils.ZERO_NUM) {
                for (int j = 0; j < selectedResultsBean.size(); j++) {
                    if (selectedResultsBean.getIdByIndex(j).getCustomerId().equalsIgnoreCase(availableResultsBean.getIdByIndex(i).getCustomerId()) && selectedResultsBean.getIdByIndex(j).getCustomerNo().equalsIgnoreCase(availableResultsBean.getIdByIndex(i).getCustomerNo())
                            && selectedResultsBean.getIdByIndex(j).getCustomerName().equalsIgnoreCase(availableResultsBean.getIdByIndex(i).getCustomerName()) && selectedResultsBean.getIdByIndex(j).getTradeClassSysId() == (availableResultsBean.getIdByIndex(i).getTradeClassSysId())) {
                        flag = false;
                        break;
                    } else {
                        flag = true;
                    }
                }
                if (flag) {
                    selectedResultsBean.addItem(availableResultsBean.getIdByIndex(i));
                }
            } else {
                flag = true;
                selectedResultsBean.addItem(availableResultsBean.getIdByIndex(i));
            }

        }
        shouldSave = true;
        availableResultsBean.removeAllItems();
        selectedResults.setValue(null);
        availableResults.setValue(null);
        LOGGER.info(" addAllItemsButtonClick method Ended");
    }

    /**
     * Removes the items button click.
     *
     * @param event the event
     */
    protected void removeItemsButtonClick(final ClickEvent event) {

        LOGGER.info("Entering removeItemsButtonClick method ");
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
        LOGGER.info("removeItemsButtonClick method Ended");

    }

    /**
     * Removes the items button click.
     *
     * @param event the event
     */
    protected void removeItemsButtonClick(final ItemClickEvent event) {
        LOGGER.info("Entering removeItemsButtonClick method ");
        final Object itemId = event.getItemId();
        availableResultsBean.addItem(itemId);
        selectedResultsBean.removeItem(itemId);
        selectedResults.setValue(null);
        availableResults.setValue(null);
        shouldSave = true;
        LOGGER.info(" removeItemsButtonClick method Ended");
    }

    /**
     * Removes the all items button click.
     *
     * @param event the event
     */
    protected void removeAllItemsButtonClick(final ClickEvent event) {
        LOGGER.info("Entering removeAllItemsButtonClick method ");
        for (int i = 0; i < selectedResultsBean.size(); i++) {
            availableResultsBean.addItem(selectedResultsBean.getIdByIndex(i));
        }
        selectedResultsBean.removeAllItems();
        selectedResults.setValue(null);
        availableResults.setValue(null);
        LOGGER.info(" removeAllItemsButtonClick method Ended ");

    }

    /**
     * Save button click.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private boolean saveGroupInfo(String indicator) {
        LOGGER.info("IN saveGroupInfo");
        saveFlag = false;

        try {
            customerGroupBinder.getField("customerGroupName").setRequired(false);
            customerGroupBinder.getField("customerGroupNo").setRequired(false);
            customerGroupBinder.getField("customerGroupDesc").setRequired(false);
            customerGroupBinder.commit();
            customerGroupBinder.getField("customerGroupName").setRequired(true);
            customerGroupBinder.getField("customerGroupNo").setRequired(true);
            customerGroupBinder.getField("customerGroupDesc").setRequired(true);
            if (StringUtils.isBlank(customerGroupName.getValue()) || ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(customerGroupName.getValue()))
                    || StringUtils.isBlank(customerGroupNo.getValue()) || ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(customerGroupNo.getValue()))
                    || StringUtils.isBlank(customerGroupDesc.getValue()) || ConstantsUtils.NULL.equalsIgnoreCase(String.valueOf(customerGroupDesc.getValue()))) {

                MessageBox.showPlain(Icon.ERROR, "Required Fields Missing", "Not all required fields have been populated.  Please complete all required fields and try again. ", ButtonId.OK);
                saveFlag = false;
                return saveFlag;
            } else {

                if (selectedResultsBean.size() == ConstantsUtils.ZERO_NUM) {
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
                                         * Called when a Button has been clicked
                                         * .
                                         *
                                         */
                                        @SuppressWarnings("PMD")
                                        public void buttonClicked(final ButtonId buttonId) {
                                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                                save();
//        /*GAL-804*/                 CommonUIUtils.getMessageNotification(customerGroupName.getValue()+" has been successfully saved");
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
            saveButtonClick();
        } catch (SystemException e) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (PortalException e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
        } catch (Exception e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
        }
    }

    protected void saveButtonClick() throws SystemException, PortalException, Exception {
        try {
            LOGGER.info("Entering saveButtonClick method");

            final List<CustomerDetailsDTO> selectedCompanies = selectedResultsBean.getItemIds();
            final List<String> selectedList = new ArrayList<String>();
            List<String> alreadyList = new ArrayList<String>();
            for (CustomerDetailsDTO selectedCompany : selectedCompanies) {

                selectedList.add(selectedCompany.getCustomerId());
            }
            Collections.sort(selectedList);
            for (CustomerDetailsDTO companyDetail : companyDetails) {

                alreadyList.add(companyDetail.getCustomerId());
            }
            Collections.sort(alreadyList);
            String pageView = sessionDTO.getLogic();
            LOGGER.info("pageView====" + pageView + "-----");
            if (pageView.equals("copy")) {
                alreadyList = new ArrayList<String>();
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
                        LOGGER.info("In configureFields editButtonClickLogic navigateTo CustomerGroupView");
                        getUI().getNavigator().navigateTo(CustomerGroupView.NAME);
                    }
                    final Notification notif = new Notification(customerGroupName.getValue() + " has been successfully saved", Notification.Type.HUMANIZED_MESSAGE);
                    notif.setPosition(Position.BOTTOM_CENTER);
                    notif.setStyleName("mystyle");
                    notif.setDelayMsec(3000);
                    notif.show(Page.getCurrent());
                } else {
                    customerBinder.getErrorDisplay().setError(ConstantsUtils.ERROR_OCCURED);
                }

            } else {
                final Notification notif = new Notification(customerGroupName.getValue() + " has been successfully saved", Notification.Type.HUMANIZED_MESSAGE);

                notif.setPosition(Position.BOTTOM_CENTER);
                notif.setStyleName("mystyle");
                notif.setDelayMsec(3000);
                notif.show(Page.getCurrent());
            }

            LOGGER.info("saveButtonClick method Ended");

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Reset all button click logic.
     *
     */
    protected void resetAllButtonClickLogic() {
        LOGGER.info("Entering resetAllButtonClickLogic method");
        try {

            final String fromViewPage = sessionDTO.getFromViewPage();
            if (fromViewPage.equals(ConstantsUtils.OPTION_NO)) {
                final int companyGroupSystemId = sessionDTO.getSystemId();
                final int versionNo = sessionDTO.getVersionNo();

                selectedResultsBean.removeAllItems();

                companyDetails = new CustomerGroupLogic().getSavedHistCustomerDetails(companyGroupSystemId, versionNo);

                selectedResultsBean.addAll(companyDetails);
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
                    final int companyGroupSystemId = sessionDTO.getSystemId();
                    final int versionNo = sessionDTO.getVersionNo();
                    List<CustomerDetailsDTO> companyDetails1;
                    companyDetails1 = new CustomerGroupLogic().getSavedHistCustomerDetails(companyGroupSystemId, versionNo);

                    availableResultsBean.removeAllItems();
                    customerGroupNo.setValue(ConstantsUtils.EMPTY);
                    customerGroupName.setValue(ConstantsUtils.EMPTY);
                    customerGroupDesc.setValue(ConstantsUtils.EMPTY);

                    selectedResultsBean.addAll(companyDetails1);

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
            LOGGER.info("resetAllButtonClickLogic method Ended");
        } catch (SystemException e) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (PortalException e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4006));
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

    }
    /**
     * Entry.
     *
     * @param flag the flag
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    List<CustomerDetailsDTO> companyDetails = new ArrayList<CustomerDetailsDTO>();

    public void entry(final Boolean flag) throws SystemException, PortalException, ParseException, Exception {
        LOGGER.info("entry method Started");
        LOGGER.info(ConstantsUtils.ENTRY_STARTED);
        final int customerGroupSystemId = sessionDTO.getSystemId();
        final int versionNo = sessionDTO.getVersionNo();
        if (customerGroupSystemId != ConstantsUtils.ZERO_NUM) {
            if (flag) {
                customerGroupDTO = new CustomerGroupLogic().getHistCustomerGroupInfo(versionNo, sessionDTO);
                companyDetails = new CustomerGroupLogic().getSavedHistCustomerDetails(customerGroupSystemId, versionNo);

            } else {
                customerGroupDTO = new CustomerGroupLogic().getCustomerGroupInfo(sessionDTO);
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
        LOGGER.info(ConstantsUtils.ENTRY_STARTED);
    }

    /**
     * Adds the responsive search table collapse.
     *
     * @param table the table
     */
    public void addResponsiveSearchTableCollapse(final ExtFilterTable table) {

        final Map<Integer, Boolean> reloadMap = new HashMap<Integer, Boolean>();
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
                /**
                 *
                 */
                public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {

                    final int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                    if (browserWidth > 1516 && reloadMap.get(1516)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumnsDefault(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth > 1516);
                        }
                        reloadMap.put(1516, false);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < 1516 && browserWidth > 978 && reloadMap.get(978)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 1516);
                        }

                        reloadMap.put(1516, true);
                        reloadMap.put(978, false);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < 978 && browserWidth > 600 && reloadMap.get(600)) {

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

                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, false);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < 600 && browserWidth > 480 && reloadMap.get(480)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumns600Px(table)) {
                            // table is refreshed when container reloading is disabled
                            table.setColumnCollapsed(propertyId, true);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, false);
                        reloadMap.put(320, true);
                        reloadMap.put(0, true);

                    } else if (browserWidth < 480 && browserWidth > 320 && reloadMap.get(320)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 480);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, false);
                        reloadMap.put(0, true);

                    } else if (browserWidth < 320 && reloadMap.get(0)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumns480Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < 320);
                        }
                        reloadMap.put(1516, true);
                        reloadMap.put(978, true);
                        reloadMap.put(600, true);
                        reloadMap.put(480, true);
                        reloadMap.put(320, true);
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
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
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
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
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
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
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
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        list.remove(propertyIds[2]);
        list.remove(propertyIds[3]);
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
        final List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));

        for (int i = 0; i < 10; i++) {
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
            if (browserWidth > 1516) {

                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getSixColumns(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }

            } else if (browserWidth < 1516 && browserWidth > 978) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < 978 && browserWidth > 600) {
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
            } else if (browserWidth < 600 && browserWidth > 480) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns600Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < 480 && browserWidth > 320) {
                for (Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < 320) {
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

    public void backButton() {

        backBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a {@link Button} has been clicked. A reference to the
             * button is given by {@link ClickEvent#getButton()}.
             *
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In configureFields backButton  navigateTo CustomerGroupSearchIndex");

                final String viewPage = sessionDTO.getFromViewPage();

                if (viewPage.equals("YES")) {
                    getUI().getNavigator().navigateTo(AbstractSearchForm.NAME);
                } else {
                    MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, " Any unsaved information will not be saved. Do you want to proceed"
                            + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(ConstantsUtils.YES)) {
                                        try {
                                            getUI().getNavigator().navigateTo(AbstractSearchForm.NAME);

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
        LOGGER.info("Entering getFirstTab1");
        try {
            String mode = sessionDTO.getMode();
            List<Object> resultList = commonUtil.getFieldsForSecurity(UISecurityUtil.CUSTOMER_GROUP_MASTER, "Functional Screen");
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldItemHM, mode);
            commonSecurityLogic.removeComponentOnPermission(resultList, isCssLayout, fieldItemHM, mode);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("Ending getFirstTab1");
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
                LOGGER.info("In configureFields searchButtonClickLogic started");
                try {
                    if (StringUtils.isBlank(customerNo.getValue()) && (tradeClassDdlb.getValue() == null || String.valueOf(tradeClassDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))
                            && (customerStatusDdlb.getValue() == null || String.valueOf(customerStatusDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))
                            && StringUtils.isBlank(customerName.getValue()) && (stateDdlb.getValue() == null || String.valueOf(stateDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))
                            && StringUtils.isBlank(city.getValue()) && (customerTypeDdlb.getValue() == null || String.valueOf(customerTypeDdlb.getValue()).equals(ConstantsUtils.SELECT_ONE))
                            && StringUtils.isBlank(zipCode.getValue())) {

                        MessageBox.showPlain(Icon.ERROR, "No Search Criteria", "No search criteria were found. Please enter search criteria and try again", ButtonId.OK);

                    } else {
                        searchButtonClickLogic(event);
                    }

                } catch (SystemException e) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                } catch (PortalException e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
                } catch (Exception e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
                }
                LOGGER.info("In configureFields searchButtonClickLogic Ended");
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
                LOGGER.info("In configureFields saveButtonClick started");
                boolean saved = saveGroupInfo("save");
                if (!saved) {
                    return;
                }

                LOGGER.info("In configureFields saveButtonClick Ended");
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
                LOGGER.info("In configureFields removeItemsButtonClick started");
                if (recordRemoveFlag) {
                    removeItemsButtonClick(event);
                    shouldSave = true;
                    recordRemoveFlag = false;
                } else {
                    MessageBox.showPlain(Icon.ERROR, ConstantsUtils.NO_RECORDS_SELECTED, "No customers were selected in the Selected Customers list view.  Please select at least one customer and try again.",
                            ButtonId.OK);
                }
                LOGGER.info("In configureFields removeItemsButtonClick Ended");
            }
        });
    }

    private void addButton() {
        addBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when button has clicked .
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In configureFields addItemsButtonClick started");
                if (recordAddFlag) {
                    addItemsButtonClick(event);
                    recordAddFlag = false;
                    shouldSave = true;
                    customerDetailsDTO = NULLOBJECT;
                } else {
                    MessageBox.showPlain(Icon.ERROR, ConstantsUtils.NO_RECORDS_SELECTED, "No items were selected in the Results list view.  Please select at least one Customer and try again.", ButtonId.OK);
                }
                LOGGER.info("In configureFields addItemsButtonClick Ended");
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
                LOGGER.info("In configureFields addAllItemsButtonClick started");
                if (availableResultsBean.size() > ConstantsUtils.ZERO_NUM) {
                    addAllItemsButtonClick(event);
                } else {
                    MessageBox.showPlain(Icon.ERROR, ConstantsUtils.NO_RECORDS_SELECTED, "No items were selected in the Results list view.  Please select at least one Customer and try again.", ButtonId.OK);
                }
                LOGGER.info("In configureFields addAllItemsButtonClick Ended");
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
                LOGGER.info("In configureFields removeAllItemsButtonClick started");
                if (selectedResultsBean.size() > ConstantsUtils.ZERO_NUM) {
                    removeAllItemsButtonClick(event);
                    shouldSave = true;
                } else {
                    MessageBox.showPlain(Icon.ERROR, ConstantsUtils.NO_RECORDS_SELECTED, "No customers were selected in the Selected Customers list view.  Please select at least one customer and try again.",
                            ButtonId.OK);
                }
                LOGGER.info("In configureFields removeAllItemsButtonClick Ended");
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
                             * Click event. This event is thrown, when the
                             * button is clicked.
                             *
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    LOGGER.info("In configureFields resetButtonClickLogic started");
                                    resetButtonClickLogic();
                                    LOGGER.info("In configureFields resetButtonClickLogic Ended");
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
                LOGGER.info("In configureFields resetAllButtonClickLogic Started");
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
                LOGGER.info("In configureFields resetAllButtonClickLogic Ended");
            }
        });
    }
}
