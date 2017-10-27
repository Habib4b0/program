/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.customergroup.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import com.stpl.app.adminconsole.customergroup.dto.CustomerGroupDTO;
import com.stpl.app.adminconsole.customergroup.logic.CustomerGroupLogic;
import com.stpl.app.adminconsole.customergroup.ui.lazyload.CustomerSearchCriteria;
import com.stpl.app.adminconsole.customergroup.ui.view.CustomerGroupView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import com.stpl.app.adminconsole.itemgroup.util.CommonUtils;

/**
 * The Class CustomerGroupSearchIndex.
 *
 * @author vishalakshi
 */
public class CustomerGroupSearchIndex extends CustomComponent implements View {

    public final Object[] customerGroupResultsColumns = new Object[]{
        "customerGroupName", "customerGroupDesc", "customerGroupNo", "versionNo", "createdDate", "modifiedDate", "createdBy"};

    public final String[] customerGroupResultHeaders = new String[]{
        "Customer Group Name", "Customer Group Description", "Customer Group No", "Version No", "Creation Date", "Modified Date", "Created By"};

    private final Label space = new Label("&nbsp;", ContentMode.HTML);

    public static final String NAME = ConstantsUtils.EMPTY;

    private final ErrorLabel errorMsg = new ErrorLabel();

    private final TextField customerGroupName = new TextField();

    private final TextField customerGroupDesc = new TextField();

    private final TextField customerGroupNo = new TextField();

    private final TextField customerGroupSystemId = new TextField();

    private final Button search = new Button("SEARCH");

    private final Button auditTrail = new Button("AUDIT SEARCH");

    private final Button add = new Button("ADD");

    private final Button copy = new Button("COPY");

    private final Button view = new Button("VIEW");

    private final Button edit = new Button("EDIT");

    private final Button reset = new Button("RESET");

    private final Button delete = new Button("DELETE");

    private CustomerGroupDTO customerGroupDTO;

    private CustomFieldGroup customerGroupBinder;

    private final CustomerGroupLogic logic = new CustomerGroupLogic();

    private ExtPagedFilterTable results = new ExtPagedFilterTable();

    private final BeanItemContainer<CustomerGroupDTO> resultsBean = new BeanItemContainer<>(CustomerGroupDTO.class);

    private final Button searchResultsExcelExport = new Button();

    private static final Logger LOGGER = Logger.getLogger(CustomerGroupSearchIndex.class);

    public static final String ERROR_MSG = "No Customer Group has been selected.  Please select a Customer Group and try again.";

    private static final BeanItem<?> NULLOBJECT = null;

    private int version;

    private CustomerGroupDTO itemId = new CustomerGroupDTO();

    private String searchCriteria = ConstantsUtils.EMPTY;

    private final Map<Integer, Boolean> reloadMap = new HashMap<>();

    private Button prevColumn = new Button("<<");

    private Button nextColumn = new Button(">>");

    CustomerSearchCriteria customerSearchCriteria = new CustomerSearchCriteria();
    CustomerGroupDTO custDto;

    /**
     * Instantiates a new customer group search index.
     *
     * @param customerGroupDTO the customer group dto
     * @param customerGroupBinder the customer group binder
     * @param prevColumn the prev column
     * @param nextColumn the next column
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public CustomerGroupSearchIndex(final CustomerGroupDTO customerGroupDTO, final CustomFieldGroup customerGroupBinder, final Button prevColumn, final Button nextColumn) {
        super();
        LOGGER.debug("CustomerGroupSearchIndex Constructor initiated ");
        this.customerGroupDTO = customerGroupDTO;
        this.customerGroupBinder = customerGroupBinder;
        this.prevColumn = prevColumn;
        this.nextColumn = nextColumn;
        init();
        addResponsiveSearchTableCollapse(this.results);
        addButtonListeners();
        LOGGER.debug("CustomerGroupSearchIndex Constructor Ended ");
    }

    public CustomerSearchCriteria getCustomerSearchCriteria() {
        return customerSearchCriteria;
    }

    public void setCustomerSearchCriteria(
            final CustomerSearchCriteria customerSearchCriteria) {
        this.customerSearchCriteria = customerSearchCriteria;
    }

    public Map<Integer, Boolean> getReloadMap() {
        return reloadMap;
    }

    public Button getPrevColumn() {
        return prevColumn;
    }

    public void setPrevColumn(final Button prevColumn) {
        this.prevColumn = prevColumn;
    }

    public Button getNextColumn() {
        return nextColumn;
    }

    public void setNextColumn(final Button nextColumn) {
        this.nextColumn = nextColumn;
    }

    public CustomerGroupDTO getItemId() {
        return itemId;
    }

    public void setItemId(final CustomerGroupDTO itemId) {
        this.itemId = itemId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    public CustomerGroupDTO getCustomerGroupDTO() {
        return customerGroupDTO;
    }

    public void setCustomerGroupDTO(final CustomerGroupDTO customerGroupDTO) {
        this.customerGroupDTO = customerGroupDTO;
    }

    public CustomFieldGroup getCustomerGroupBinder() {
        return customerGroupBinder;
    }

    public void setCustomerGroupBinder(final CustomFieldGroup customerGroupBinder) {
        this.customerGroupBinder = customerGroupBinder;
    }

    public ExtPagedFilterTable getResults() {
        return results;
    }

    public void setResults(final ExtPagedFilterTable results) {
        this.results = results;
    }

    public Label getSpace() {
        return space;
    }

    public static String getName() {
        return NAME;
    }

    public TextField getCustomerGroupName() {
        return customerGroupName;
    }

    public TextField getCustomerGroupDesc() {
        return customerGroupDesc;
    }

    public TextField getCustomerGroupNo() {
        return customerGroupNo;
    }

    public TextField getCustomerGroupSystemId() {
        return customerGroupSystemId;
    }

    public Button getSearch() {
        return search;
    }

    public Button getAdd() {
        return add;
    }

    public Button getCopy() {
        return copy;
    }

    public Button getView() {
        return view;
    }

    public Button getEdit() {
        return edit;
    }

    public Button getReset() {
        return reset;
    }

    public Button getDelete() {
        return delete;
    }

    public CustomerGroupLogic getLogic() {
        return logic;
    }

    public BeanItemContainer<CustomerGroupDTO> getResultsBean() {
        return resultsBean;
    }

    public Button getSearchResultsExcelExport() {
        return searchResultsExcelExport;
    }

    public static String getErrorMsg() {
        return ERROR_MSG;
    }

    public static String getNAME() {
        return NAME;
    }

    public Button getAuditTrail() {
        return auditTrail;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static String getERRORMSG() {
        return ERROR_MSG;
    }

    /**
     * Inits the.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public final void init() {
        LOGGER.debug("init Method Started ");
        setCompositionRoot(addToContent());
        LOGGER.debug("init Method Ended ");

    }

    /**
     * Adds the to content.
     *
     * @return the component
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private Component addToContent() {
        LOGGER.debug("addToContent method started ");
        final VerticalLayout content = new VerticalLayout();
        space.setHeight("20");

        content.addComponent(errorMsg);
        errorMsg.setStyleName("search-criteria-error-message");
        content.setMargin(true);
        final VerticalLayout out = new VerticalLayout();
        final Panel panel = new Panel("Customer Group Search Criteria");
        out.setMargin(true);
        out.setSpacing(true);
        out.addComponent(addCustomerGroupSearch());
        out.addComponent(addSearchButtons());
        panel.setContent(out);
        content.addComponent(panel);
        content.addComponent(addCoulmnButtons());
        final Panel resultTable = new Panel("Results");
        resultTable.setContent(addResultsTable());
        content.addComponent(resultTable);
        content.addComponent(ResponsiveUtils.getResponsiveControls(results.createControls()));
        getBinder();
        content.addComponent(addButtons());
        content.setStyleName("bootstrap-search-criteria");
        configureFields();
        LOGGER.debug("addToContent method RETURNS content-Component");

        return content;
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private CustomFieldGroup getBinder() {

        LOGGER.debug("getBinder method Started ");
        customerGroupBinder.bindMemberFields(this);
        customerGroupBinder.setItemDataSource(new BeanItem<>(customerGroupDTO));
        customerGroupBinder.setBuffered(true);
        customerGroupBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("getBinder method RETURNS customerGroupBinder - Binder ");

        return customerGroupBinder;
    }

    private HorizontalLayout addCustomerGroupSearch() {
        LOGGER.debug("addCustomerGroupSearch Method Started ");

        final HorizontalLayout hlayout = new HorizontalLayout();
        hlayout.setStyleName("responsiveGrid");

        final CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        Label cgn = new Label("Customer Group Name: ");
        cgn.setWidth("184px");
        cssLayout.addComponent(cgn);
        cssLayout.addComponent(customerGroupName);
        cssLayout.addComponent(new Label("Customer Group No: "));
        cssLayout.addComponent(customerGroupNo);
        Label cgd = new Label("Customer Group Description: ");
        cgd.setWidth("188px");
        cssLayout.addComponent(cgd);
        cssLayout.addComponent(customerGroupDesc);
        hlayout.addComponent(cssLayout);
        LOGGER.debug("addCustomerGroupSearch Method RETURNS panel ");

        return hlayout;
    }

    /**
     * Adds the search buttons.
     *
     * @return the horizontal layout
     */
    private HorizontalLayout addSearchButtons() {
        final HorizontalLayout layout = new HorizontalLayout();
        final CssLayout cssLayout = new CssLayout();
        layout.setStyleName("responsiveGrid");
        layout.addComponent(search);
        search.setEnabled(true);
        layout.addComponent(auditTrail);
        search.setEnabled(true);
        layout.addComponent(cssLayout);
        layout.setSpacing(true);

        return layout;
    }

    /**
     * Adds the results table.
     *
     * @return the filter table
     */
    private ExtFilterTable addResultsTable() {
        LOGGER.debug("addResultsTable method started ");

        results.setFilterBarVisible(true);
        results.setFilterDecorator(new ExtDemoFilterDecorator());
        results.setContainerDataSource(resultsBean);
        results.sinkItemPerPageWithPageLength(false);
        Object[] objColumn = customerGroupResultsColumns;
        for (Object objColumn1 : objColumn) {
            String value = objColumn1.toString();
            if (value.endsWith("Date")) {
                results.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
            }
        }
        results.setVisibleColumns(customerGroupResultsColumns);
        results.setColumnHeaders(customerGroupResultHeaders);
        results.setPageLength(NumericConstants.FIVE);
        results.setSizeFull();

        results.setImmediate(true);
        results.setSelectable(true);
        setDefaultTableWidth(results, resultsBean);
        results.addValueChangeListener(new Property.ValueChangeListener() {

            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                LOGGER.debug("In addResultsTable ValueChange.resultsCustomerClick Started");
                resultsCustomerClick(event.getProperty().getValue());
                LOGGER.debug("In addResultsTable ValueChange.resultsCustomerClick Ended");
            }
        });
        results.setErrorHandler(new ErrorHandler() {

            public void error(final com.vaadin.server.ErrorEvent event) {
                return;
            }
        });

        LOGGER.debug("addResultsTable method RETURNS Table-results ");

        return results;
    }

    /**
     * Adds the buttons.
     *
     * @return the component
     */
    private Component addButtons() {
        LOGGER.debug("addButtons method Started ");
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName("responsiveTabGrid");

        layout.setSpacing(true);
        layout.addComponent(reset);
        reset.setEnabled(true);
        layout.addComponent(add);
        add.setEnabled(true);
        layout.addComponent(edit);
        edit.setEnabled(true);
        layout.addComponent(view);
        view.setEnabled(true);
        layout.addComponent(copy);
        copy.setEnabled(true);
        layout.addComponent(delete);
        delete.setEnabled(true);
        layout.addComponent(searchResultsExcelExport);

        LOGGER.debug("addButtons method RETURNS layout ");

        return layout;
    }

    /**
     * Configure fields.
     */
    private void configureFields() {

        customerGroupName.focus();
        LOGGER.debug("ConfigureFields method Initiated ");
        searchResultsExcelExport.setDescription(ConstantsUtils.EXCEL_EXPORT);

        customerGroupNo.setImmediate(true);
        customerGroupName.setImmediate(true);
        customerGroupDesc.setImmediate(true);
        customerGroupNo.setValidationVisible(true);
        customerGroupName.setValidationVisible(true);
        customerGroupDesc.setValidationVisible(true);

        searchResultsExcelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        searchResultsExcelExport.setStyleName("link");

        search.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked .
             *
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields searchButtonClickLogic started");
                try {
                    if (StringUtils.isEmpty(String.valueOf(customerGroupName.getValue())) && StringUtils.isEmpty(String.valueOf(customerGroupDesc.getValue())) && StringUtils.isEmpty(String.valueOf(customerGroupNo.getValue()))) {
                        AbstractNotificationUtils.getErrorNotification("Search Error", "No search criteria have been entered.  Please enter search criteria and try again.");
                        return;
                    }
                    itemId = null;
                    searchCriteria = "search";

                } catch (Exception e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
                }
                LOGGER.debug("In configureFields searchButtonClickLogic Ended");
            }
        });
        auditTrail.addClickListener(new Button.ClickListener() {

            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields searchButtonClickLogic started");
                try {
                    if (StringUtils.isEmpty(String.valueOf(customerGroupName.getValue())) && StringUtils.isEmpty(String.valueOf(customerGroupDesc.getValue())) && StringUtils.isEmpty(String.valueOf(customerGroupNo.getValue()))) {
                        AbstractNotificationUtils.getErrorNotification("Search Error", "No search criteria have been entered.  Please enter search criteria and try again.");
                        return;
                    }
                    itemId = null;
                    searchCriteria = "auditSearch";

                } catch (Exception e) {
                    LOGGER.error(e);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
                }
                LOGGER.debug("In configureFields searchButtonClickLogic Ended");
            }
        });

        results.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            private static final long serialVersionUID = -3975567936229651936L;

            public void itemClick(final ItemClickEvent event) {
                custDto = getBeanFromId(event.getItemId());
                if (itemId == null) {
                    version = custDto.getVersionNo();
                } else {
                    itemId = null;
                }
            }
        });
        reset.addClickListener(new Button.ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields resetButtonClickLogic started");
                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values"
                        + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {

                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    resetButtonClickLogic();
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                LOGGER.debug("In configureFields resetButtonClickLogic Ended");
            }
        });

        add.addClickListener(new ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields addButtonClickLogic started");
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.CUST_GROUP_SYS_ID, 0);
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.FROM_VIEW_PAGE, ConstantsUtils.ADD);
                LOGGER.debug("In configureFields addButtonClickLogic navigateTo CustomerGroupView");
                getUI().getNavigator().navigateTo(CustomerGroupView.NAME);
                LOGGER.debug("In configureFields addButtonClickLogic Ended");
            }
        });

        edit.addClickListener(new ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields editButtonClickLogic started");
                if (customerGroupSystemId.getValue() == null || ConstantsUtils.EMPTY.equals(customerGroupSystemId.getValue()) || ConstantsUtils.ZERO.equals(customerGroupSystemId.getValue())) {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ERROR_MSG, ButtonId.OK);
                } else {
                    try {
                        final int latestVersion = logic.getExistingVersion(Integer.valueOf(customerGroupSystemId.getValue()));

                        if (version == latestVersion) {
                            VaadinSession.getCurrent().setAttribute(ConstantsUtils.CUST_GROUP_SYS_ID,
                                    Integer.parseInt(customerGroupSystemId.getValue().toString().replaceAll(ConstantsUtils.COMMA, ConstantsUtils.EMPTY)));
                            VaadinSession.getCurrent().setAttribute(ConstantsUtils.FROM_VIEW_PAGE, ConstantsUtils.OPTION_NO);
                            VaadinSession.getCurrent().setAttribute(ConstantsUtils.VERSION_NO, version);
                            VaadinSession.getCurrent().setAttribute(CommonUtils.LOGIC_ATTRIBUTE_TXT, "edit");
                            LOGGER.debug("In configureFields editButtonClickLogic navigateTo CustomerGroupView");
                            getUI().getNavigator().navigateTo(CustomerGroupView.NAME);
                        } else {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No Latest Version is selected.  Please Select latest version and try again.", ButtonId.OK);
                        }
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
                LOGGER.debug("In configureFields editButtonClickLogic Ended");
            }
        });

        view.addClickListener(new ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields viewButtonClickLogic started");
                if (customerGroupSystemId.getValue() == null || ConstantsUtils.EMPTY.equals(customerGroupSystemId.getValue()) || ConstantsUtils.ZERO.equals(customerGroupSystemId.getValue())) {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ERROR_MSG, ButtonId.OK);
                } else {
                    try {

                        VaadinSession.getCurrent().setAttribute(ConstantsUtils.CUST_GROUP_SYS_ID,
                                Integer.parseInt(customerGroupSystemId.getValue().toString().replaceAll(ConstantsUtils.COMMA, ConstantsUtils.EMPTY)));
                        VaadinSession.getCurrent().setAttribute(ConstantsUtils.FROM_VIEW_PAGE, ConstantsUtils.YES);
                        VaadinSession.getCurrent().setAttribute(ConstantsUtils.VERSION_NO, version);
                        VaadinSession.getCurrent().setAttribute(CommonUtils.LOGIC_ATTRIBUTE_TXT, "view");
                        LOGGER.debug("In configureFields viewButtonClickLogic navigateTo CustomerGroupView");
                        getUI().getNavigator().navigateTo(CustomerGroupView.NAME);
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }

                }
                LOGGER.debug("In configureFields viewButtonClickLogic Ended");
            }
        });

        copy.addClickListener(new ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields copyButtonClickLogic started");
                if (customerGroupSystemId.getValue() == null || ConstantsUtils.EMPTY.equals(customerGroupSystemId.getValue()) || ConstantsUtils.ZERO.equals(customerGroupSystemId.getValue())) {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ERROR_MSG, ButtonId.OK);
                } else {
                    MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to copy the selected record"
                            + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                                /**
                                 * Adds the button click listener.
                                 *
                                 *
                                 */
                                @SuppressWarnings("PMD")
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(ConstantsUtils.YES)) {
                                        try {
                                            final int latestVersion = logic.getExistingVersion(Integer.valueOf(customerGroupSystemId.getValue()));
                                            if (version == latestVersion) {
                                                VaadinSession.getCurrent().setAttribute(ConstantsUtils.CUST_GROUP_SYS_ID,
                                                        Integer.parseInt(customerGroupSystemId.getValue().toString().replaceAll(ConstantsUtils.COMMA, ConstantsUtils.EMPTY)));
                                                VaadinSession.getCurrent().setAttribute(ConstantsUtils.FROM_VIEW_PAGE, ConstantsUtils.COPY);
                                                VaadinSession.getCurrent().setAttribute(ConstantsUtils.VERSION_NO, version);
                                                VaadinSession.getCurrent().setAttribute(CommonUtils.LOGIC_ATTRIBUTE_TXT, "copy");
                                                LOGGER.debug("In configureFields copyButtonClickLogic navigateTo CustomerGroupView");
                                                getUI().getNavigator().navigateTo(CustomerGroupView.NAME);
                                            } else {
                                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No Latest Version is selected.  Please Select latest version and try again.", ButtonId.OK);
                                            }
                                        } catch (Exception e) {
                                            LOGGER.error(e);
                                        }
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);

                }
                LOGGER.debug("In configureFields copyButtonClickLogic Ended");
            }
        });

        delete.addClickListener(new ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields delete.addClickListener started");
                deleteButtonClickLogic();
                LOGGER.debug("In configureFields delete.addClickListener Ended");
            }
        });

        searchResultsExcelExport.addClickListener(new ClickListener() {

            public void buttonClick(final ClickEvent event) {
                LOGGER.debug("In configureFields searchResultsExcelExport started");
                if (results.size() > ConstantsUtils.ZERO_NUM) {

                    List<List> exportList;
                    final List<String> dollarList = new ArrayList();
                    dollarList.add("AdminConsole");
                    exportList = new ArrayList<>();
                    exportList.add(dollarList);
                    exportList.add(null);
                    exportList.add(null);
                    ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(results), "Customer Group Search Results", "Customer Group Search Results", "CustomerGroupSearchResults.xls", false);
                    excel.export();
                } else {
                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "No results to Export", ButtonId.OK);
                }
                LOGGER.debug("In configureFields searchResultsExcelExport Ended");
            }
        });

        LOGGER.debug("Configurefields method Ended ");

    }

    /**
     * Reset button click logic.
     *
     */
    protected void resetButtonClickLogic() {

        LOGGER.debug("resetButtonClickLogic method Started ");
        final List<Object> collapsedColumns = new ArrayList<>();
        for (final Object item : results.getVisibleColumns()) {
            if (results.isColumnCollapsed(item)) {

                collapsedColumns.add(item);
            }
        }
        customerGroupBinder.getErrorDisplay().clearError();
        resultsBean.removeAllItems();

        results.firePagedChangedEvent();

        customerGroupNo.setValue(ConstantsUtils.EMPTY);
        customerGroupName.setValue(ConstantsUtils.EMPTY);
        customerGroupDesc.setValue(ConstantsUtils.EMPTY);
        for (final Object propertyId : collapsedColumns) {
            results.setColumnCollapsed(propertyId, true);
        }
        LOGGER.debug("resetButtonClickLogic method ENDED ");

    }

    /**
     * Delete button click logic.
     *
     * @param event the event
     */
    protected void deleteButtonClickLogic() {

        LOGGER.debug("deleteButtonClickLogic method Started ");
        if (customerGroupSystemId.getValue() == null || ConstantsUtils.EMPTY.equals(customerGroupSystemId.getValue()) || ConstantsUtils.ZERO.equals(customerGroupSystemId.getValue())) {
            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, ERROR_MSG, ButtonId.OK);
        } else {
            LOGGER.debug("In configureFields deleteButtonClickLogic started");
            MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to delete record " + custDto.getCustomerGroupName()
                    + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                        /**
                         * Adds the button click listener.
                         *
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {

                            if (buttonId.name().equals(ConstantsUtils.YES)) {
                                try {
                                    final String deletedCustomerGroupName = logic.deleteCustomerGroup(Integer.parseInt(customerGroupSystemId.getValue()
                                                    .replaceAll(ConstantsUtils.COMMA, ConstantsUtils.EMPTY)), version);
                                    if (deletedCustomerGroupName != null && !ConstantsUtils.EMPTY.equals(deletedCustomerGroupName)) {
                                        CommonUIUtils.getMessageNotification(deletedCustomerGroupName + " has been successfully deleted");

                                        customerGroupBinder.commit();
                                        resultsBean.removeAllItems();

                                        results.setVisibleColumns(customerGroupResultsColumns);
                                        results.setColumnHeaders(customerGroupResultHeaders);

                                    }
                                } catch (SystemException e) {

                                    final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
                                    LOGGER.error(e);
                                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
                                } catch (PortalException e) {

                                    LOGGER.error(e);
                                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4004));
                                } catch (Exception e) {

                                    LOGGER.error(e);
                                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4004));
                                }

                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

            LOGGER.debug("In configureFields deleteButtonClickLogic Ended");

        }
        LOGGER.debug("deleteButtonClickLogic method ENDED ");

    }

    /**
     * Results customer click.
     *
     * @param systemId the system id
     */
    protected void resultsCustomerClick(final Object systemId) {
        LOGGER.debug("resultsCustomerClick method started");
        if (systemId == null) {
            customerGroupSystemId.setValue(null);
            version = 0;
            searchCriteria = ConstantsUtils.EMPTY;

        } else {
            enableActionButton();
            BeanItem<?> targetCustomer;
            if (systemId instanceof BeanItem<?>) {
                targetCustomer = (BeanItem<?>) systemId;
            } else if (systemId instanceof CustomerGroupDTO) {
                targetCustomer = new BeanItem<>((CustomerGroupDTO) systemId);
            } else {
                targetCustomer = NULLOBJECT;
            }
            final int customerGroupSysId = ((CustomerGroupDTO) targetCustomer.getBean()).getCustomerGroupSystemId();
            version = ((CustomerGroupDTO) targetCustomer.getBean()).getVersionNo();
            customerGroupSystemId.setValue(String.valueOf(customerGroupSysId));

        }

        LOGGER.debug("resultsCustomerClick method started");
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
     * gets the null bean item.
     *
     * @return Null beanitem
     */
    public BeanItem<?> getNULLOBJECT() {
        return NULLOBJECT;
    }

    /**
     * Adds the coulmn buttons.
     *
     * @return the horizontal layout
     */
    private HorizontalLayout addCoulmnButtons() {

        final HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName("prev-next-layout");
        layout.addComponent(prevColumn);
        layout.addComponent(nextColumn);
        prevColumn.setStyleName("prev-column-button");
        nextColumn.setStyleName("next-column-button");

        return layout;
    }

    /**
     * Method to get the Bean Item Id while click any record from table.
     *
     * @param identifier the identifier
     * @return ApproveLeaveDto instance
     */
    public CustomerGroupDTO getBeanFromId(final Object identifier) {
        BeanItem<?> targetItem = null;
        CustomerGroupDTO dto;
        if (identifier instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) identifier;
        } else if (identifier instanceof CustomerGroupDTO) {
            targetItem = new BeanItem<>((CustomerGroupDTO) identifier);
        }
        dto = (CustomerGroupDTO) targetItem.getBean();
        return dto;
    }

    /**
     * Enable action button.
     */
    private void enableActionButton() {
        if (!ConstantsUtils.EMPTY.equalsIgnoreCase(searchCriteria) && ConstantsUtils.SEARCH.equalsIgnoreCase(searchCriteria)) {
            delete.setEnabled(true);
            copy.setEnabled(true);
            view.setEnabled(true);
            edit.setEnabled(true);
        } else if (!ConstantsUtils.EMPTY.equalsIgnoreCase(searchCriteria) && ConstantsUtils.AUDIT_SEARCH.equalsIgnoreCase(searchCriteria)) {
            delete.setEnabled(false);
            copy.setEnabled(false);
            view.setEnabled(true);
            edit.setEnabled(false);
        }
    }

    /**
     * Adds the responsive search table collapse.
     *
     * @param table the table
     */
    public void addResponsiveSearchTableCollapse(final ExtFilterTable table) {

        reloadMap.put(NumericConstants.ONE_THREE_FIVE_ZERO, true);
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
                    if (browserWidth > NumericConstants.ONE_THREE_FIVE_ZERO && reloadMap.get(NumericConstants.ONE_THREE_FIVE_ZERO)) {


                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumnsDefault(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth > NumericConstants.ONE_THREE_FIVE_ZERO);
                        }
                        reloadMap.put(NumericConstants.ONE_THREE_FIVE_ZERO, false);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, true);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);
                    } else if (browserWidth < NumericConstants.ONE_THREE_FIVE_ZERO && browserWidth > NumericConstants.NINE_SEVEN_EIGHT && reloadMap.get(NumericConstants.NINE_SEVEN_EIGHT)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }
                        for (final Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                            table.setColumnCollapsed(propertyId, browserWidth < NumericConstants.ONE_THREE_FIVE_ZERO);
                        }

                        reloadMap.put(NumericConstants.ONE_THREE_FIVE_ZERO, true);
                        reloadMap.put(NumericConstants.NINE_SEVEN_EIGHT, false);
                        reloadMap.put(NumericConstants.SIX_HUNDRED, true);
                        reloadMap.put(NumericConstants.FOUR_EIGHT_ZERO, true);
                        reloadMap.put(NumericConstants.THREE_TWO_ZERO, true);
                        reloadMap.put(0, true);
                    } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.SIX_HUNDRED && reloadMap.get(NumericConstants.SIX_HUNDRED)) {

                        for (final Object propertyId : table.getVisibleColumns()) {
                            table.setColumnCollapsed(propertyId, false);
                        }

                        if (results != null && results.getItemIds().isEmpty()) {
                            for (final Object propertyId : getCollapsibleColumns978Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        } else {
                            for (final Object propertyId : getCollapsibleColumns600Px(table)) {
                                table.setColumnCollapsed(propertyId, true);
                            }
                        }

                        reloadMap.put(NumericConstants.ONE_THREE_FIVE_ZERO, true);
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
                        reloadMap.put(NumericConstants.ONE_THREE_FIVE_ZERO, true);
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
                        reloadMap.put(NumericConstants.ONE_THREE_FIVE_ZERO, true);
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
                        reloadMap.put(NumericConstants.ONE_THREE_FIVE_ZERO, true);
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

        for (int i = 0; i < NumericConstants.TEN && !list.isEmpty(); i++) {
            list.remove(propertyIds[i]);
        }

        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    /**
     * Sets the default table width.
     *
     * @param table the new default table width
     */
    public void setDefaultTableWidth(final ExtFilterTable table) {
        final BeanItemContainer<CustomerGroupDTO> resetBeans = new BeanItemContainer<>(CustomerGroupDTO.class);
        table.setContainerDataSource(resetBeans);

        try {
            table.setColumnCollapsingAllowed(true);
            final int browserWidth = Page.getCurrent().getBrowserWindowWidth();
            if (browserWidth > NumericConstants.ONE_THREE_FIVE_NINE) {
                for (final Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (final Object propertyId : getCollapsibleColumnsDefault(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < NumericConstants.ONE_THREE_FIVE_ZERO && browserWidth > NumericConstants.NINE_SEVEN_EIGHT) {
                for (final Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (final Object propertyId : getCollapsibleColumnsDefault1515Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.SIX_HUNDRED) {
                for (final Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }

                if (table.getItemIds().isEmpty()) {
                    for (final Object propertyId : getCollapsibleColumns978Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                } else {
                    for (final Object propertyId : getCollapsibleColumns600Px(table)) {
                        table.setColumnCollapsed(propertyId, true);
                    }
                }
            } else if (browserWidth < NumericConstants.SIX_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO) {
                for (final Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (final Object propertyId : getCollapsibleColumns600Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO) {
                for (final Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (final Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            } else if (browserWidth < ConstantsUtils.PX_320) {
                for (final Object propertyId : table.getVisibleColumns()) {
                    table.setColumnCollapsed(propertyId, false);
                }
                for (final Object propertyId : getCollapsibleColumns480Px(table)) {
                    table.setColumnCollapsed(propertyId, true);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Adds the button listeners.
     */
    private void addButtonListeners() {

        prevColumn.addClickListener(new Button.ClickListener() {
            /**
             *
             */
            public void buttonClick(final Button.ClickEvent event) {

                // List of all visible columns
                final List<Object> visibleColumnsList = new ArrayList<>(Arrays.asList(results.getVisibleColumns()));

                final List<Object> collapsedColumns = new ArrayList<>();
                int index = 0;
                for (final Object item : results.getVisibleColumns()) {
                    if (!results.isColumnCollapsed(item)) {
                        collapsedColumns.add(item);
                    }
                }

                //index - gets the index of the last column currently displayed in the table among the total visible columns.
                index = visibleColumnsList.indexOf(collapsedColumns.get(collapsedColumns.size() - 1));
                if ((index) > ConstantsUtils.ZERO_NUM) {  // Condition to check whether it is the column is the only one displayed in the table.
                    results.setColumnCollapsed(visibleColumnsList.get(index), true);
                }

                // Logic to enable next button
                if ((index) < visibleColumnsList.size()) {
                    nextColumn.setEnabled(true);
                }

                // Logic to disable the previous button
                if (collapsedColumns.size() <= ConstantsUtils.TWO_NUM) {
                    prevColumn.setEnabled(false);
                }
            }
        });

        nextColumn.addClickListener(new Button.ClickListener() {
            /**
             *
             */
            public void buttonClick(final Button.ClickEvent event) {

                // List of all visible columns
                final List<Object> visibleColumnsList = new ArrayList<>(Arrays.asList(results.getVisibleColumns()));
                // List of columns currently visible in the table
                final List<Object> collapsedColumns = new ArrayList<>();
                int index = 0;
                for (final Object item : results.getVisibleColumns()) {
                    if (!results.isColumnCollapsed(item)) {
                        collapsedColumns.add(item);
                    }
                }
                //index - gets the index of the last column currently displayed in the table among the total visible columns.
                index = visibleColumnsList.indexOf(collapsedColumns.get(collapsedColumns.size() - 1));
                if ((index + 1) < visibleColumnsList.size()) {
                    results.setColumnCollapsed(visibleColumnsList.get(index + 1), false);
                }

                //A column is added and a refreshed list is needed to enable and disable the buttons
                final List<Object> updatedCollapsedColumns = new ArrayList<>();
                for (final Object item : results.getVisibleColumns()) {
                    if (!results.isColumnCollapsed(item)) {
                        updatedCollapsedColumns.add(item);
                    }
                }
                // Logic to enable previous button
                if (updatedCollapsedColumns.size() > ConstantsUtils.ONE) {
                    prevColumn.setEnabled(true);
                }
                // Logic to disable next button
                if (updatedCollapsedColumns.size() >= visibleColumnsList.size()) {
                    nextColumn.setEnabled(false);
                }
            }
        });

    }

    /**
     * setDefaultTableWidth
     *
     * @param table
     * @param container
     */
    public void setDefaultTableWidth(final ExtFilterTable table, final BeanItemContainer<CustomerGroupDTO> container) {

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
}
