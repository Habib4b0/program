
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.ui.form;

import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.netsalesformula.dto.NetSalesRuleLookupDto;
import com.stpl.app.global.netsalesformula.dto.NsfDto;
import com.stpl.app.global.netsalesformula.dto.NsfFilterGenerator;
import com.stpl.app.global.netsalesformula.logic.tablelogic.ContractSearchTableLogic;
import com.stpl.app.global.netsalesformula.dto.SalesBasisDto;
import com.stpl.app.global.netsalesformula.logic.SalesLogic;
import com.stpl.app.global.netsalesformula.logic.tablelogic.AvailableCustomerTableLogic;
import com.stpl.app.global.netsalesformula.utils.UIUtils;
import com.stpl.app.global.netsalesformula.dto.SelectedCustomerTableGenerator;
import com.stpl.app.global.netsalesformula.logic.NsfLogic;
import com.stpl.app.global.netsalesformula.logic.tablelogic.SelectedCustomerTableLogic;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ExcelExportUtil;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author karthikraja.k
 */
public class SalesBasis extends CustomComponent {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = Logger.getLogger(SalesBasis.class);
    /**
     * The net Sales Rule
     */
    @UiField("netSalesRule")
    private CustomTextField netSalesRule;
    /**
     * The Css Layout
     */
    @UiField("cssLayout2")
    CssLayout cssLayout2;
    @UiField("cssLayout3")
    CssLayout cssLayout3;
    /**
     * the Reset button
     */
    @UiField("resetBtn")
    Button resetBtn;
    /**
     * The Search button
     */
    @UiField("searchBtn")
    Button searchBtn;
    /**
     * The Add Button
     */
    @UiField("addBtn")
    Button addBtn;
    /**
     * The Display Button
     */
    @UiField("displayBtn")
    Button displayBtn;
    /**
     * the Populate Button
     */
    @UiField("btnPopulate")
    Button btnPopulate;
    /**
     * The remove Button
     */
    @UiField("removeBtn")
    Button removeBtn;
    /**
     * The reset Button
     */
    @UiField("reset")
    Button reset;
    /**
     * The Contract No
     */
    @UiField("contractNo")
    TextField contractNo;
    /**
     * The Contract Name
     */
    @UiField("contractName")
    TextField contractName;
    /**
     * The Contract Holder
     */
    @UiField("contractHolder")
    TextField contractHolder;
    /**
     * The companyFamilyPlan No
     */
    @UiField("companyFamilyPlanNo")
    TextField companyFamilyPlanNo;
    /**
     * the companyFamilyPlan Name
     */
    @UiField("companyFamilyPlanName")
    TextField companyFamilyPlanName;
    /**
     * the Item Family Plan No
     */
    @UiField("itemFamilyPlanNo")
    TextField itemFamilyPlanNo;
    /**
     * the Item Family Plan Name
     */
    @UiField("itemFamilyPlanName")
    TextField itemFamilyPlanName;
    /**
     * the Company No *
     */
    @UiField("companyNo")
    TextField companyNo;
    /**
     * The company Name
     */
    @UiField("companyName")
    TextField companyName;
    /**
     * the Item No
     */
    @UiField("itemNo")
    TextField itemNo;
    /**
     * the Item Name
     */
    @UiField("itemName")
    TextField itemName;
    /**
     * The Market Type
     */
    @UiField("marketType")
    ComboBox marketType;
    /**
     * the Contract Selection
     */
    @UiField("contractSelection")
    private OptionGroup contractSelection;
    /**
     * the Mass check
     */
    @UiField("massCheck")
    private OptionGroup massCheck;
    /**
     * The mass field.
     */
    @UiField("massField")
    private ComboBox massField;
    /**
     * The mass value.
     */
    @UiField("massValue")
    private CustomTextField massValue;
    @UiField("tableLayout")
    private VerticalLayout tableLayout;
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;
    @UiField("customerTableLayout")
    private VerticalLayout customerTableLayout;
    @UiField("customerControlLayout")
    private HorizontalLayout customerControlLayout;
    ContractSearchTableLogic tableLogic = new ContractSearchTableLogic();
    /**
     * the Available Contracts Table
     */
    private ExtPagedTable availableContractsTable = new ExtPagedTable(tableLogic);
    AvailableCustomerTableLogic customerTableLogic = new AvailableCustomerTableLogic();
    /**
     * the Available Customers Table
     */
    private ExtPagedTable availableCustomersTable = new ExtPagedTable(customerTableLogic);
    @UiField("selectedCusTableLayout")
    private VerticalLayout selectedCusTableLayout;
    @UiField("selectedCusControlLayout")
    private HorizontalLayout selectedCusControlLayout;
    SelectedCustomerTableLogic selectedCusTableLogic = new SelectedCustomerTableLogic();
    /**
     * the Selected Customers Table
     */
    private ExtPagedTable selectedCustomersTable = new ExtPagedTable(selectedCusTableLogic);
    /**
     * the Excel Export Button
     */
    @UiField("excelExport")
    private Button excelExport;
    /**
     * the NetSalesRuleLookUp
     */
    NetSalesRuleLookUp lookUp = null;
    NetSalesRuleLookupDto ruleDto = new NetSalesRuleLookupDto();
    BeanItemContainer<SalesBasisDto> availableContracts = new BeanItemContainer<>(SalesBasisDto.class);
    BeanItemContainer<SalesBasisDto> availableCustomers = new BeanItemContainer<>(SalesBasisDto.class);
    BeanItemContainer<SalesBasisDto> selectedCustomers = new BeanItemContainer<>(SalesBasisDto.class);
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;
    /**
     * The ErrorLabel.
     */
    @UiField("errorMsg")
    public ErrorLabel errorMsg;
    SalesBasisDto searchForm;
    @UiField("cssLayout")
    CssLayout cssLayout;
    @UiField("searchPanel")
    Panel searchPanel;
    @UiField("searchHLayout")
    HorizontalLayout searchHLayout;
    @UiField("contractPanel")
    Panel contractPanel;
    @UiField("custPanel")
    Panel custPanel;
    @UiField("massUpdatePanel")
    Panel massUpdatePanel;
    SalesLogic logic = new SalesLogic();
    SessionDTO sessiondto = new SessionDTO();
    NetSalesRuleLookupDto netSalesRuleLookupDto;
    NsfDto nsfDto;
    String ruleSystemId = "0";
    NsfLogic nsfLogic = new NsfLogic();
    IFPLogic ifpLogic = new IFPLogic();
    CommonSecurityLogic securityLogic = new CommonSecurityLogic();
    SalesBasisDto salesBasisDto;
    
    
    public SalesBasis(SessionDTO sessionDto, NsfDto nsfDto,SalesBasisDto salesBasisDto) {
        this.nsfDto = nsfDto;
        this.sessiondto = sessionDto;
        this.salesBasisDto=salesBasisDto;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/nsf/SalesBasis.xml"), this));
        init();
    }

    private void init() {
        try {
            configureFields();
            getBinder();
            getResponsiveFirstTab();
            configureButtonPermission();
            setSalesBasis();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public ErrorfulFieldGroup getBinder() {
        binder = new ErrorfulFieldGroup(new BeanItem<>(salesBasisDto));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
        return binder;
    }

    private void configureFields() throws Exception {
        contractNo.setImmediate(true);
        contractHolder.setImmediate(true);
        contractName.setImmediate(true);
        companyFamilyPlanNo.setImmediate(true);
        companyFamilyPlanName.setImmediate(true);
        itemFamilyPlanName.setImmediate(true);
        itemFamilyPlanNo.setImmediate(true);
        itemName.setImmediate(true);
        itemNo.setImmediate(true);
        companyName.setImmediate(true);
        companyNo.setImmediate(true);
        marketType.setImmediate(true);
        netSalesRule.setImmediate(true);
        contractSelection.addItem("Existing Contract");
        contractSelection.addItem("Select Contract");
        contractSelection.select("Existing Contract");
        contractSelection.setMultiSelect(false);
        massCheck.addItem(ConstantsUtils.ENABLE);
        massCheck.addItem(ConstantsUtils.DISABLE);
        massCheck.setValue(ConstantsUtils.DISABLE);
        massCheck.setImmediate(true);
        massCheck.setStyleName("horizontal");
        massCheck.setDescription((String) massCheck.getValue());
        massField.setImmediate(true);
        massField.setEnabled(false);
        massValue.setVisible(false);
        massField.setNullSelectionAllowed(true);
        massField.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        massField.addItem(ConstantsUtils.SELECT_ONE);
        massField.addItem("Net Sales Rule No");
        massField.select(ConstantsUtils.SELECT_ONE);

        List<Integer> pageLength = new ArrayList<Integer>();
        pageLength.add(10);
        pageLength.add(15);
        pageLength.add(20);
        pageLength.add(25);
        pageLength.add(50);
        pageLength.add(100);
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pageLength);
        tableLayout.addComponent(availableContractsTable);
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);
        tableLogic.setContainerDataSource(availableContracts);
        tableLogic.setPageLength(10);
        tableLogic.sinkItemPerPageWithPageLength(false);

        availableContractsTable.setVisibleColumns(UIUtils.AVAILABLE_CONTR_COL);
        availableContractsTable.setColumnHeaders(UIUtils.AVAILABLE_CONTR_HEADER);
        availableContractsTable.markAsDirtyRecursive();
        availableContractsTable.setImmediate(true);
        availableContractsTable.setWidth(100, UNITS_PERCENTAGE);

        availableContractsTable.setSelectable(true);
        availableContractsTable.markAsDirty();
        availableContractsTable.setComponentError(null);
        availableContractsTable.setFilterBarVisible(true);
        availableContractsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        availableContractsTable.setFilterGenerator(new NsfFilterGenerator());
        availableContractsTable.setValidationVisible(false);
        availableContractsTable.addStyleName(ConstantsUtils.FILTER_BAR);

        availableContractsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.getItemId() != null) {
                    searchForm = (SalesBasisDto) event.getItemId();
                }
            }
        });

        customerTableLogic.getControlConfig().setPageLengthsAndCaptions(pageLength);
        customerTableLayout.addComponent(availableCustomersTable);
        ResponsiveUtils.getResponsiveControls(customerTableLogic.createControls(), customerControlLayout);
        customerTableLayout.addComponent(customerControlLayout);
        customerTableLogic.setContainerDataSource(availableCustomers);
        customerTableLogic.setPageLength(10);
        customerTableLogic.sinkItemPerPageWithPageLength(false);

        availableCustomersTable.markAsDirtyRecursive();
        availableCustomersTable.setImmediate(true);
        availableCustomersTable.setWidth(100, UNITS_PERCENTAGE);
        availableCustomersTable.setSelectable(true);
        availableCustomersTable.setMultiSelect(true);
        availableCustomersTable.markAsDirty();
        availableCustomersTable.setComponentError(null);
        availableCustomersTable.setFilterBarVisible(true);
        availableCustomersTable.setFilterDecorator(new ExtDemoFilterDecorator());
        availableCustomersTable.setFilterGenerator(new NsfFilterGenerator());
        availableCustomersTable.setValidationVisible(false);
        availableCustomersTable.addStyleName(ConstantsUtils.FILTER_BAR);
        availableCustomersTable.setFilterBarVisible(true);
        availableCustomersTable.setPageLength(5);
        availableCustomersTable.setSizeFull();

        selectedCusTableLogic.getControlConfig().setPageLengthsAndCaptions(pageLength);
        selectedCusTableLayout.addComponent(selectedCustomersTable);
        ResponsiveUtils.getResponsiveControls(selectedCusTableLogic.createControls(), selectedCusControlLayout);
        selectedCusTableLayout.addComponent(selectedCusControlLayout);
        selectedCusTableLogic.setContainerDataSource(selectedCustomers);
        selectedCusTableLogic.setPageLength(10);
        selectedCusTableLogic.sinkItemPerPageWithPageLength(false);

        selectedCustomersTable.setFilterBarVisible(true);
        selectedCustomersTable.setPageLength(5);
        selectedCustomersTable.setSizeFull();
        selectedCustomersTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedCustomersTable.setFilterGenerator(new NsfFilterGenerator());
        selectedCustomersTable.addStyleName(ConstantsUtils.FILTER_BAR);
        selectedCustomersTable.removeAllItems();
        selectedCustomersTable.setColumnCheckBox(ConstantsUtils.AVAILABLE_CHECK, true);
        selectedCustomersTable.getFilterField(ConstantsUtils.AVAILABLE_CHECK).setVisible(false);
        selectedCustomersTable.setTableFieldFactory(new SelectedCustomerTableGenerator(selectedCustomersTable, selectedCustomers, sessiondto, this));
        selectedCustomersTable.setEditable(true);
        if (!ConstantsUtils.VIEW.equalsIgnoreCase(sessiondto.getMode())) {
            selectedCustomersTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
                @Override
                public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                    if (ConstantsUtils.AVAILABLE_CHECK.equals(event.getPropertyId().toString())) {
                        if (event.isChecked()) {

                            logic.checkAllSalesBasisTempTable(Boolean.TRUE, null, sessiondto.getUiSessionId());
                            selectedCustomers.removeAllItems();
                            loadSelectedTable();
                            // this setCurrentPage is used to refresh the lazy conatiner
                            selectedCustomersTable.setCurrentPage(selectedCustomersTable.getCurrentPage());
                            selectedCustomersTable.setColumnCheckBox(ConstantsUtils.AVAILABLE_CHECK, true, true);
                        } else {

                            logic.checkAllSalesBasisTempTable(Boolean.FALSE, null, sessiondto.getUiSessionId());
                            selectedCustomers.removeAllItems();
                            loadSelectedTable();
                            // this setCurrentPage is used to refresh the lazy conatiner
                            selectedCustomersTable.setCurrentPage(selectedCustomersTable.getCurrentPage());
                            selectedCustomersTable.setColumnCheckBox(ConstantsUtils.AVAILABLE_CHECK, true, false);

                        }
                    }
                }
            });
        } else {
            selectedCustomersTable.setColumnCheckBoxDisable(ConstantsUtils.AVAILABLE_CHECK, true);
        }
        btnPopulate.setEnabled(false);

        netSalesRule.addClickListener(new CustomTextField.ClickListener() {
            @Override
            public void click(CustomTextField.ClickEvent event) {
                try {
                    lookUp = new NetSalesRuleLookUp(netSalesRule, ruleDto,"Sales Basis");
                    UI.getCurrent().addWindow(lookUp);
                    lookUp.addCloseListener(new Window.CloseListener() {
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent e) {
                            if(lookUp.isSelected){
                            netSalesRuleLookupDto = lookUp.getNetSalesRuleLookupDto();
                            ruleSystemId = netSalesRuleLookupDto.getRuleSystemId();
                            }
                        }
                    });
                } catch (Exception e) {
                    LOGGER.error(e);

                }
            }
        });
        netSalesRule.setEnabled(true);
        cssLayout2.setEnabled(false);
        resetBtn.setEnabled(false);
        searchBtn.setEnabled(false);
        cssLayout3.setEnabled(false);
        removeBtn.setEnabled(false);
        addBtn.setEnabled(false);
        reset.setEnabled(false);
        displayBtn.setEnabled(false);

        contractSelection.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                try {
                    getResponsiveFirstTab();
                    selectedCustomersTable.removeAllItems();
                    availableCustomersTable.removeAllItems();
                    availableContractsTable.removeAllItems();
                    if (contractSelection.getValue().toString().equals("Select Contract")) {
                        netSalesRule.setEnabled(false);
                        cssLayout2.setEnabled(true);
                        resetBtn.setEnabled(true);
                        searchBtn.setEnabled(true);
                        cssLayout3.setEnabled(true);
                        removeBtn.setEnabled(true);
                        addBtn.setEnabled(true);
                        reset.setEnabled(true);
                        displayBtn.setEnabled(true);  
                        selectedCustomersTable.setEnabled(true);
                        loadSelectedTable();
                    } else {
                        netSalesRule.setEnabled(true);
                        cssLayout2.setEnabled(false);
                        resetBtn.setEnabled(false);
                        searchBtn.setEnabled(false);
                        cssLayout3.setEnabled(false);
                        removeBtn.setEnabled(false);
                        addBtn.setEnabled(false);
                        reset.setEnabled(false);
                        displayBtn.setEnabled(false);                        
                        btnPopulate.setEnabled(false);
                       selectedCustomersTable.setEnabled(false);
                    }
                  
                } catch (PortalException | SystemException ex) {
                    LOGGER.error(ex);
                }


            }
        });

        massCheck.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {

                if ((ConstantsUtils.DISABLE).equals(massCheck.getValue())) {

                    massField.setEnabled(false);
                    massValue.setValue(StringUtils.EMPTY);
                    massValue.setVisible(false);
                    btnPopulate.setEnabled(false);
                    btnPopulate.setReadOnly(true);
                    markAsDirty();

                } else if ((ConstantsUtils.ENABLE).equals(massCheck.getValue())) {
                    massField.setEnabled(true);
                    btnPopulate.setEnabled(true);
                    markAsDirty();
                }

            }
        });


        massField.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (("Net Sales Rule No").equals(massField.getValue())) {
                    massValue.setVisible(true);
                    massValue.addClickListener(new CustomTextField.ClickListener() {
                        @Override
                        public void click(CustomTextField.ClickEvent event) {
                            try {
                                    lookUp = new NetSalesRuleLookUp(massValue, ruleDto,"Sales Basis");
                                    UI.getCurrent().addWindow(lookUp);
                                lookUp.addCloseListener(new Window.CloseListener() {
                                    @SuppressWarnings("PMD")
                                    public void windowClose(final Window.CloseEvent e) {
                                        if (lookUp.isSelected) {
                                            NetSalesRuleLookupDto nsfDTO = lookUp.getDetailsDto();
                                            final Map<String, String> map = new HashMap<>();
                                            map.put("ruleNo", nsfDTO.getRuleNo());
                                            map.put("ruleName", nsfDTO.getRuleName());
                                            map.put("ruleSystemSID", nsfDTO.getRuleSystemId());
                                            massValue.setData(map);
                                        }
                                    }
                                });
                            } catch (Exception e) {
                                LOGGER.error(e);

                            }
                        }
                    });
                } else {
                    massValue.setVisible(false);
                }
            }
        });
        contractSelection.setValue("Existing Contract");

        excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExport.setStyleName("link");
        excelExport.setDescription("Export to excel");
        excelExport.setIconAlternateText("Excel export");
        excelExport.setHtmlContentAllowed(true);
        excelExport.setErrorHandler(new ErrorHandler() {
            /**
             * Invoked when an error occurs
             *
             * @param event - ErrorEvent
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                // parses the error.
            }
        });
        excelExport.addClickListener(new Button.ClickListener() {
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    binder.getFields();
                    excelExportLogic();
                } catch (Exception exception) {
                    LOGGER.error(exception);
                }
            }
        });
        CommonUtil commonUtil = CommonUtil.getInstance();
        commonUtil.loadComboBox(marketType, com.stpl.app.global.abstractsearch.util.UIUtils.CONTRACT_TYPE, false);

        displayBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(Button.ClickEvent event) {

       
                try{
                    if (String.valueOf(availableContractsTable.getValue()).equals("null")) {
            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "No value selected to display", "Please select a value in the Available contract list view to display. ", new MessageBoxListener() {
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            return;

                    }else{

                String contractMasterSid = searchForm.getContractMasterSid();
                salesBasisDto.setContractMasterSid(contractMasterSid);
                loadAvailableCustomersTable(contractMasterSid);
            }
                }catch(Exception e){
                LOGGER.error(e);
                }
            }
            

        });

        resetBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, "Reset Confirmation", "Are you sure you want to reset the values in the Search Criteria group box?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (ButtonId.YES.equals(buttonId)) {
                            binder.getErrorDisplay().clearError();
                            binder.setItemDataSource(new BeanItem<SalesBasisDto>(new SalesBasisDto()));
                            }
                        }
                }, ButtonId.YES, ButtonId.NO);
            }
        });
        reset.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, "Reset Confirmation", "Are you sure you want to reset the values in the Selected Customers group box?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (ButtonId.YES.equals(buttonId)) {
                            try {

                                massValue.setValue(StringUtils.EMPTY);
                                massField.setValue(ConstantsUtils.SELECT_ONE);
                                massCheck.setValue(ConstantsUtils.DISABLE);
                                if (sessiondto.getMode().equalsIgnoreCase("Add")) {

                                    logic.removeAll(sessiondto);


                                } else if (sessiondto.getMode().equalsIgnoreCase("Edit")) {
                                    logic.removeAll(sessiondto);
                                    nsfLogic.nsfInsert(sessiondto, "tempSbInsert", false, "");
                                } else if (sessiondto.getMode().equalsIgnoreCase("Copy")) {
                                    logic.removeAll(sessiondto);
                                    nsfLogic.nsfInsert(sessiondto, "copyTempSbInsert", false, "");

                                }
                                loadSelectedTable();
                            } catch (Exception ex) {
                                LOGGER.info("Errot--->" + ex);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });
        btnPopulate.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering sales Basis Populate operation");
                try {
                    String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                    if (SalesLogic.isChecked(userId, sessiondto.getUiSessionId())) {
                        populateLogic();
                    } else {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Populate Error", "Please select atleast one record to populate", new MessageBoxListener() {
                            /**
                             * The method is triggered when a button of the
                             * message box is pressed .
                             *
                             * @param buttonId The buttonId of the pressed
                             * button.
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                // Do Nothing  
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                    }

                } catch (FieldGroup.CommitException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing  
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                } catch (Exception exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1002), new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing  
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    LOGGER.error(exception);
                }
                LOGGER.debug("Ending sales Basis Populate operation");
            }
        });
       
    }

    @UiHandler("searchBtn")
    public void btnSearchLogic(Button.ClickEvent event) {
         if (StringUtils.isBlank(contractNo.getValue()) && (marketType.getValue() == null || String.valueOf(marketType.getValue()).equals(ConstantsUtils.SELECT_ONE))
                            && StringUtils.isBlank(contractName.getValue())
                            && StringUtils.isBlank(contractHolder.getValue()) 
                            && StringUtils.isBlank(companyFamilyPlanNo.getValue()) && StringUtils.isBlank(companyFamilyPlanName.getValue()) && StringUtils.isBlank(itemFamilyPlanNo.getValue())
                            && StringUtils.isBlank(itemFamilyPlanName.getValue()) && StringUtils.isBlank(companyNo.getValue()) && StringUtils.isBlank(companyName.getValue())
                            && StringUtils.isBlank(itemNo.getValue()) && StringUtils.isBlank(itemName.getValue())) {

                        MessageBox.showPlain(Icon.ERROR, "No Search Criteria", "No search criteria were found. Please enter search criteria and try again", ButtonId.OK);

                    }else{
        binder.getErrorDisplay().clearError();
        List<Object> collapsedColumns = new ArrayList<Object>();
        for (Object item : availableContractsTable.getVisibleColumns()) {
            if (availableContractsTable.isColumnCollapsed(item)) {
                collapsedColumns.add(item);
            }
        }

        loadSearchResults();
        if (tableLogic.isResultsEmpty()) {
            CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_COMPLETED);
        } else {
            CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
        }

    }


    }

    @UiHandler("addBtn")
    public void addBtnLogic(Button.ClickEvent event) {

        LOGGER.info("Entering add available Customers Logic");
        final Set<SalesBasisDto> availableList = (Set<SalesBasisDto>) availableCustomersTable.getValue();
        if (availableCustomersTable.size() <= 0) {
            final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "There are no customers to move", new MessageBoxListener() {
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            return;

                    } else if (availableList.isEmpty()) {
                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please select customers to add", new MessageBoxListener() {
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                // Do Nothing
                            }
                        }, ButtonId.OK);
                        msg.getButton(ButtonId.OK).focus();
                        return;
                    }
                     try {  
                         StringBuilder addQuery = new StringBuilder(StringUtils.EMPTY);
                         for (SalesBasisDto item : availableList) {
                             if (item != null) {
                                 if (logic.addDuplicateValidation(item, sessiondto)) {
                                     logic.addToTempTable(item, sessiondto, ruleDto,addQuery);
                                 }
                             }
                         }
                          nsfLogic.update(addQuery.toString());
                         loadSelectedTable();
                     } catch (Exception ex) {
                               LOGGER.info("Exception..In adding"+ ex);
                      }
                    LOGGER.info("Ending add available Customers Logic");
          
    }

    private void loadSelectedTable() {
        selectedCustomersTable.removeAllItems();
        selectedCusTableLogic.configureSearchData(sessiondto);
        selectedCustomersTable.setVisibleColumns(UIUtils.SELECTED_CUSTOMER_COL1);
        selectedCustomersTable.setColumnHeaders(UIUtils.SELECTED_CUSTOMER_HEADER1);
        selectedCustomersTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedCustomersTable.setFilterGenerator(new NsfFilterGenerator());
        selectedCustomersTable.setColumnCheckBox(ConstantsUtils.AVAILABLE_CHECK, true);
        selectedCustomersTable.getFilterField(ConstantsUtils.AVAILABLE_CHECK).setVisible(false);

    }

    public String getContractSelected() {

        String contractSelected = String.valueOf(contractSelection.getValue());

        return contractSelected;
    }

    public String isTableSelected() {
        String errorMessage = StringUtils.EMPTY;
        if (contractSelection.getValue().toString().equals("Select Contract")) {
            if (selectedCustomersTable.size() == 0) {
                 errorMessage = "Select at least one Customer in Sales Basis tab ";
            } 
        }
        return errorMessage;
    }

    @UiHandler("removeBtn")
    public void removeBtnLogic(Button.ClickEvent event) {
        
        
         if (!logic.isEmpty(sessiondto)) {
                    MessageBox.showPlain(Icon.QUESTION, "Remove Confirmation", "Are you sure you want to remove the selected value from the Selected Customers list view?", new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (ButtonId.YES.equals(buttonId)) {
        try {
            LOGGER.info("Entering inside  Remove method ");
            logic.removeFromTempTable(sessiondto);
            loadSelectedTable();
                                    refreshLazyTable();
            LOGGER.info("Ending Remove Method ");

        } catch (Exception e) {
            LOGGER.error(e);
        }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

                }else {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "No values selected to remove", "Please select at least one record in the ‘Selected Customers’ list view to remove", new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing  
    }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();

                }

    }

    private void populateLogic() throws FieldGroup.CommitException, PortalException, SystemException {
        binder.getErrorDisplay().clearError();
        binder.commit();
        String fieldMass = "";
        if (massField.getValue() != null) {
            if (massValue.getValue() != null && StringUtils.isNotEmpty(massValue.getValue())) {
                String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                String sessionId = String.valueOf(sessiondto.getUiSessionId());
                String tempCreatedDate = String.valueOf(sessiondto.getSessionDate());

                if (massField.getValue() != null && StringUtils.isNotEmpty(massValue.getValue())) {
                    final String populateField;
                    final String populateValue;
                    fieldMass = massField.getValue().toString();
                    if ("Net Sales Rule No".equalsIgnoreCase(fieldMass)) {
                        populateField = "CDR_MODEL_SID";
                        Map<String, String> map = (HashMap) massValue.getData();
                        populateValue = "0".equals(map.get("ruleSystemSID"))?null:map.get("ruleSystemSID");
                    } else {
                        populateField = StringUtils.EMPTY;
                        populateValue = StringUtils.EMPTY;
                    }
                    logic.populateLogic(userId, sessionId, tempCreatedDate, populateField, populateValue, searchForm);
                    loadSelectedTable();
                    selectedCustomersTable.setCurrentPage(selectedCustomersTable.getCurrentPage());
                    LOGGER.debug("Ending POPULATE method");
                }
                massField.setValue("");

            } else {
                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Populate Error", "Please enter value for the " + massField.getValue(), new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        // Do Nothing  
                    }
                }, ButtonId.OK);
                msg.getButton(ButtonId.OK).focus();
            }
        } else {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Populate Error", "Please Select a field to Populate", new MessageBoxListener() {
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
        }
    }

    public void disableSalesFields() {
        netSalesRule.setEnabled(false);
        cssLayout2.setEnabled(false);
        resetBtn.setEnabled(false);
        searchBtn.setEnabled(false);
        cssLayout3.setEnabled(false);
        cssLayout.setEnabled(false);
        removeBtn.setEnabled(false);
        addBtn.setEnabled(false);
        reset.setEnabled(false);
        displayBtn.setEnabled(false);
        btnPopulate.setEnabled(false);
    }

    public void refreshLazyTable() {

        selectedCustomersTable.removeAllItems();
        selectedCusTableLogic.configureSearchData(sessiondto);
        selectedCustomersTable.setVisibleColumns(UIUtils.SELECTED_CUSTOMER_COL1);
        selectedCustomersTable.setColumnHeaders(UIUtils.SELECTED_CUSTOMER_HEADER1);
        selectedCustomersTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedCustomersTable.setFilterGenerator(new NsfFilterGenerator());
        selectedCustomersTable.setColumnCheckBox(ConstantsUtils.AVAILABLE_CHECK, true);
        selectedCustomersTable.getFilterField(ConstantsUtils.AVAILABLE_CHECK).setVisible(false);

    }

    protected void excelExportLogic() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.info("Ending excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering createWorkSheet");
        final long recordCount = logic.tempTableCount(null, sessiondto);
        ExcelExportforBB.createWorkSheet(selectedCustomersTable.getColumnHeaders(), recordCount, this, getUI(), TabNameUtil.SALES_BASIS);
        LOGGER.info("Ending createWorkSheet");
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        SalesBasisDto dto;
     if(end !=0){
        final List<SalesBasisDto> searchList = logic.tempTableResults(sessiondto, start, (start + end), null,null);
      if(null !=searchList){
        for (int rowCount = 0; rowCount < searchList.size(); rowCount++) {

            dto = searchList.get(rowCount);

            printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getCompanyNo() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getCompanyName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getContractNo() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getContractName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getCompanyFamilyPlanNo() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getCompanyFamilyPlanName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.print(ConstantsUtils.QUOTE + dto.getNetSalesRuleNo() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);

            printWriter.println(ConstantsUtils.QUOTE + dto.getNetSalesRuleName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
        }
      }
    }
    }

    private void getResponsiveFirstTab() throws PortalException, SystemException {
        LOGGER.info("Entering getFirstTab1");
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldNsfHM = stplSecurity
                    .getFieldOrColumnPermission(userId, UISecurityUtil.NET_SALES_FORMULA + "," + "Sales Basis", false);
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.NET_SALES_FORMULA, "Sales Basis");
            securityLogic.removeComponentOnPermission(resultList, cssLayout, fieldNsfHM, sessiondto.getMode().equalsIgnoreCase("Copy")?"Add":sessiondto.getMode());
            securityLogic.removeComponentOnPermission(resultList, cssLayout2, fieldNsfHM,sessiondto.getMode().equalsIgnoreCase("Copy")?"Add":sessiondto.getMode());
            Object[] obj;
            Object[] obj1;
            Object[] obj2;
            if (contractSelection.getValue().toString().equals("Select Contract")) {
                availableCustomersTable.setVisibleColumns(UIUtils.AVAILABLE_CUSTOMER_COL);
                selectedCustomersTable.setVisibleColumns(UIUtils.SELECTED_CUSTOMER_COL1);
                obj1 = UIUtils.AVAILABLE_CUSTOMER_COL;
                obj = UIUtils.AVAILABLE_CONTR_COL;
                obj2 = UIUtils.SELECTED_CUSTOMER_COL1;
              }
              else {
                obj1 = UIUtils.AVAILABLE_CUSTOMER_COL;
                obj = UIUtils.AVAILABLE_CONTR_COL;
                obj2 = UIUtils.SELECTED_CUSTOMER_COL1;
            }
            TableResultCustom tableResultCustom = securityLogic.getTableColumnsPermission(resultList, obj, fieldNsfHM, sessiondto.getMode().equalsIgnoreCase("Copy")?"Add":sessiondto.getMode());
            if (tableResultCustom.getObjResult().length == 0) {
                availableContractsTable.setVisible(false);
            } else {
                availableContractsTable.setVisible(true);
            }
            availableContractsTable.setVisibleColumns(tableResultCustom.getObjResult());
            availableContractsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            
            TableResultCustom tableResultCustom2 = securityLogic.getTableColumnsPermissionWithListViewCheck(resultList, obj1, fieldNsfHM,sessiondto.getMode().equalsIgnoreCase("Copy")?"Add":sessiondto.getMode());
            if (tableResultCustom2.getObjResult().length == 0) {
                availableCustomersTable.setVisible(false);
            } else {
                availableCustomersTable.setVisible(true);
            }
            availableCustomersTable.setVisibleColumns(tableResultCustom2.getObjResult());
            availableCustomersTable.setColumnHeaders(tableResultCustom2.getObjResultHeader());
            TableResultCustom tableResultCustom3 = securityLogic.getTableColumnsPermissionWithListViewCheck(resultList, obj2, fieldNsfHM,sessiondto.getMode().equalsIgnoreCase("Copy")?"Add":sessiondto.getMode());
            if (tableResultCustom2.getObjResult().length == 0) {
                selectedCustomersTable.setVisible(false);
            } else {
                selectedCustomersTable.setVisible(true);
            }
            
            Object columnn[]=new Object[tableResultCustom3.getObjResult().length+1];
            String header2[]=new String[tableResultCustom3.getObjResult().length+1];
            columnn[0]=ConstantsUtils.AVAILABLE_CHECK;
            header2[0]="";
            System.arraycopy(tableResultCustom3.getObjResult(), 0, columnn, 1, tableResultCustom3.getObjResult().length);
            System.arraycopy(tableResultCustom3.getObjResultHeader(), 0, header2, 1, tableResultCustom3.getObjResultHeader().length);
            selectedCustomersTable.setVisibleColumns(columnn);
            selectedCustomersTable.setColumnHeaders(header2); 
          
            availableContractsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            availableContractsTable.setFilterGenerator(new NsfFilterGenerator());
         
              selectedCustomersTable.setFilterDecorator(new ExtDemoFilterDecorator());
              selectedCustomersTable.setFilterGenerator(new NsfFilterGenerator());
              selectedCustomersTable.setColumnCheckBox(ConstantsUtils.AVAILABLE_CHECK, true);
              selectedCustomersTable.getFilterField(ConstantsUtils.AVAILABLE_CHECK).setVisible(false);


        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    void configureButtonPermission() throws PortalException, SystemException {

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.NET_SALES_FORMULA + "," + "Sales Basis");
        if (functionHM.get("searchBtn") == null ||  !((AppPermission) functionHM.get("searchBtn")).isFunctionFlag()) {
            searchBtn.setVisible(false);
        }
        if (functionHM.get("addBtn") == null ||   !((AppPermission) functionHM.get("addBtn")).isFunctionFlag()) {
  
            addBtn.setVisible(false);
        }
        if (functionHM.get("removeBtn") == null ||   !((AppPermission) functionHM.get("removeBtn")).isFunctionFlag()) {
            removeBtn.setVisible(false);
        }
        if (functionHM.get("btnPopulate") == null ||   !((AppPermission) functionHM.get("btnPopulate")).isFunctionFlag()) {
            btnPopulate.setVisible(false);
        }
        if (functionHM.get("resetBtn") == null ||   !((AppPermission) functionHM.get("resetBtn")).isFunctionFlag()) {
            resetBtn.setVisible(false);
        }
        if (functionHM.get("reset") == null ||   !((AppPermission) functionHM.get("reset")).isFunctionFlag()) {
            reset.setVisible(false);
        }
        if (functionHM.get("displayBtn") == null ||   !((AppPermission) functionHM.get("displayBtn")).isFunctionFlag()) {
            displayBtn.setVisible(false);
        }
        if (functionHM.get("excelExport") == null ||   !((AppPermission) functionHM.get("excelExport")).isFunctionFlag()) {
            excelExport.setVisible(false);
        }
    }

    public String getRuleSystemId() {
        return ruleSystemId;
    }

    public void setRuleSystemId(String ruleSystemId) {
        this.ruleSystemId = ruleSystemId;
    }
      public void saveSalesBasisSelections(int nsfId) throws PortalException {
        LOGGER.info("saveSalesProjection method starts");
        Map map = new HashMap();

        SalesLogic sbLogic=new SalesLogic();
        sbLogic.getDisplaySearchMap(binder,map);
          if (!map.isEmpty()) {
              map.put("contractMasterSid", salesBasisDto.getContractMasterSid());  
              nsfLogic.saveProjectionSelection(map, nsfId, "Sales Basis");
          }
        LOGGER.info("saveSalesProjection method ends");

}
      
    void loadAvailableCustomersTable(String contractMasterSid) {
        customerTableLogic.configureSearchData(contractMasterSid);
        availableCustomersTable.setFilterDecorator(new ExtDemoFilterDecorator());
        availableCustomersTable.setFilterGenerator(new NsfFilterGenerator());
        availableCustomersTable.setImmediate(true);
        availableCustomersTable.setWidth(99, UNITS_PERCENTAGE);
        availableCustomersTable.addStyleName(ConstantsUtils.TABLE_CHECK_BOX);
        availableCustomersTable.setSelectable(true);
        availableCustomersTable.markAsDirtyRecursive();
    }
    
    public void loadSearchResults(){
          try {
            binder.commit();

            tableLogic.configureSearchData(binder, this);
            availableContractsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            availableContractsTable.setFilterGenerator(new NsfFilterGenerator());
            availableContractsTable.setImmediate(true);
            availableContractsTable.setWidth(99, UNITS_PERCENTAGE);
            availableContractsTable.addStyleName(ConstantsUtils.TABLE_CHECK_BOX);
            availableContractsTable.setSelectable(true);

            availableContractsTable.markAsDirtyRecursive();

        } catch (FieldGroup.CommitException commit) {
            tableLogic.clearAll();
            tableLogic.getFilters().clear();
            availableContractsTable.setFilterDecorator(new ExtDemoFilterDecorator());
            availableContractsTable.setFilterGenerator(new NsfFilterGenerator());
            LOGGER.error(commit);
        } catch (Exception exception) {
            LOGGER.error(exception);
        }
    }

    private void setSalesBasis() {
        if (!ConstantsUtils.ADD.equalsIgnoreCase(sessiondto.getMode())) {
            contractSelection.select(nsfDto.getContractSelected());
            netSalesRule.setValue(nsfDto.getNsfRuleName());
            ruleSystemId = String.valueOf(nsfDto.getNsRuleId());
            if (StringUtils.isNotBlank(salesBasisDto.getContractMasterSid())&&!"0".equals(salesBasisDto.getContractMasterSid())) {
                loadSearchResults();
                loadAvailableCustomersTable(salesBasisDto.getContractMasterSid());
            }
        }
    }
    
}

