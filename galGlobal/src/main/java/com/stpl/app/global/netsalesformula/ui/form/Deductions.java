/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.ui.form;

import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;

import com.stpl.app.global.netsalesformula.dto.DeductionDto;
import com.stpl.app.global.netsalesformula.dto.DeductionsFilterGenerator;
import com.stpl.app.global.netsalesformula.dto.NetSalesRuleLookupDto;
import com.stpl.app.global.netsalesformula.dto.NsfDto;
import com.stpl.app.global.netsalesformula.logic.DeductionsLogic;
import com.stpl.app.global.netsalesformula.logic.NsfLogic;
import com.stpl.app.global.netsalesformula.logic.tablelogic.DeductionTableGenerator;
import com.stpl.app.global.netsalesformula.logic.tablelogic.DeductionTableLogic;
import com.stpl.app.global.netsalesformula.utils.UIUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.app.util.ExcelExportUtil;
import com.stpl.ifs.util.HelperDTO;
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
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author shrihariharan
 */
public class Deductions extends CustomComponent {

    /**
     * LOGGER
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(Deductions.class);
    @UiField("formulaType")
    ComboBox formulaType;
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
     * The Market Type
     */
    @UiField("marketType")
    ComboBox marketType;
    /**
     * the Deduction Type
     */
    @UiField("deductionType")
    ComboBox deductionType;
    /**
     * Deduction Sub Type
     */
    @UiField("deductionSubType")
    ComboBox deductionSubType;
    /**
     * Deduction Category
     */
    @UiField("deductionCategory")
    ComboBox deductionCategory;
    /**
     * Price Schedule No
     */
    @UiField("priceScheduleNo")
    TextField priceScheduleNo;
    /**
     * Deduction No
     */
    @UiField("deductionNo")
    TextField deductionNo;
    /**
     * price Schedule Name
     */
    @UiField("priceScheduleName")
    TextField priceScheduleName;
    /**
     * Deduction Name
     */
    @UiField("deductionName")
    TextField deductionName;
    /**
     * Deduction Alias
     */
    @UiField("deductionAlias")
    TextField deductionAlias;
    /**
     * the Mass Check
     */
    @UiField("massCheck")
    private OptionGroup massCheck;
    /**
     * The mass field.
     */
    @UiField("massField")
    private ComboBox massField;
    @UiField("massCombo")
    private ComboBox massCombo;
    /**
     * the Populate Button
     */
    @UiField("btnPopulate")
    Button btnPopulate;
    /**
     * the Search Button
     *
     */
    @UiField("searchBtn")
    Button searchBtn;
    /**
     * the Reset Button
     *
     */
    @UiField("resetBtn")
    Button resetBtn;
    /**
     * the Add Button
     */
    @UiField("addBtn")
    Button addBtn;
    /**
     * the Remove Button
     */
    @UiField("removeBtn")
    Button removeBtn;
    /**
     * reset Available Table
     */
    @UiField("resetAvailBtn")
    Button resetAvailBtn;
    /**
     * The mass value.
     */
    @UiField("massValue")
    private CustomTextField massValue;
    @UiField("controlLayout")
    private HorizontalLayout controlLayout;
    @UiField("controlLayout2")
    private HorizontalLayout controlLayout2;
    @UiField("excelExport")
    private Button excelExport;
    @UiField("cssLayout")
    CssLayout cssLayout;
    @UiField("cssSearchLayout")
    CssLayout cssSearchLayout;
    @UiField("cssMassLayout")
    CssLayout cssMassLayout;
    @UiField("resetTableBtn")
    Button resetTableBtn;
    @UiField("deductionPanel")
    Panel deductionPanel;
    /**
     * the NetSalesRuleLookUp
     */
    NetSalesRuleLookUp lookUp = null;
    SessionDTO sessiondto;
    DeductionTableLogic tableLogic = new DeductionTableLogic();
    DeductionTableLogic selectedTableLogic = new DeductionTableLogic();
    NetSalesRuleLookupDto ruleDto = new NetSalesRuleLookupDto();
    @UiField("availableDeductionTable")
    private ExtPagedTable availableDeductionTable = new ExtPagedTable(tableLogic);
    @UiField("selectedDeductionTable")
    private ExtPagedTable selectedDeductionTable = new ExtPagedTable(selectedTableLogic);
    BeanItemContainer<DeductionDto> availableDeductions = new BeanItemContainer<>(DeductionDto.class);
    BeanItemContainer<DeductionDto> selectedDeductions = new BeanItemContainer<>(DeductionDto.class);
    DeductionDto deductionDto = new DeductionDto();
    int count;
    boolean isFirst = true;
    static String searchCriteria = StringUtils.EMPTY;
    /**
     * The customer binder.
     */
    private ErrorfulFieldGroup deductionBinder = new ErrorfulFieldGroup(new BeanItem<>(deductionDto));
    private DeductionsLogic logic = new DeductionsLogic(sessiondto);
    NsfDto nsfDto;
    CommonUtil commmonUtil = CommonUtil.getInstance();
    NsfLogic nsfLogic = new NsfLogic();
    IFPLogic ifpLogic = new IFPLogic();
    CommonSecurityLogic securityLogic = new CommonSecurityLogic();
    boolean changedFTOnce=false;
    Object ftValue=null;
    
    public Deductions(SessionDTO sessionDto, NsfDto nsfDto) {
        this.nsfDto = nsfDto;
        this.sessiondto = sessionDto;
        logic = new DeductionsLogic(sessiondto);
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/nsf/Deductions.xml"), this));
        init();
    }

    private void init() {
        try {
            getBinder();
            configureFields();
            searchBtnLogic();
            removeBtnLogic();
            btnPopoulateLogic();
            resetBtnLogic();
            loadSelectedTable();
            getResponsiveFirstTab();
            configureButtonPermission();
        } catch (Exception ex) { 
            LOGGER.info("Exception occured--->" + ex);
        }
    }

    private void configureFields() throws Exception {

        formulaType.setNullSelectionAllowed(true);
        formulaType.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        formulaType.addItem(ConstantsUtils.SELECT_ONE);
        formulaType.select(ConstantsUtils.SELECT_ONE);

        massCheck.addItem(ConstantsUtils.ENABLE);
        massCheck.addItem(ConstantsUtils.DISABLE);
        massCheck.setValue(ConstantsUtils.DISABLE);
        massCheck.setImmediate(true);
        massCheck.setStyleName("horizontal");
        massCheck.setDescription((String) massCheck.getValue());
        massField.setImmediate(true);
        massField.setEnabled(false);
        massValue.setVisible(false);
        massCombo.setVisible(false);
        massField.setNullSelectionAllowed(true);
        massField.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        massField.addItem(ConstantsUtils.SELECT_ONE);
        massField.addItem("+/- Indicator");
        massField.addItem("Net Sales Rule No");
        massField.select(ConstantsUtils.SELECT_ONE);



        tableLogic.setContainerDataSource(availableDeductions);
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableLogic.setPageLength(7);
        selectedTableLogic.setContainerDataSource(selectedDeductions);
        selectedTableLogic.sinkItemPerPageWithPageLength(false);
        selectedTableLogic.setPageLength(7);   

        selectedDeductionTable.setFilterBarVisible(true);
        selectedDeductionTable.setPageLength(5);
        selectedDeductionTable.addStyleName(ConstantsUtils.FILTER_BAR);
        availableDeductionTable.setFilterBarVisible(true);
        availableDeductionTable.setPageLength(5);
        availableDeductionTable.setSizeFull();
        availableDeductionTable.addStyleName(ConstantsUtils.FILTER_BAR);
        availableDeductionTable.setImmediate(true);
        availableDeductionTable.setFilterDecorator(new ExtDemoFilterDecorator());
        availableDeductionTable.setFilterGenerator(new DeductionsFilterGenerator());
        availableDeductionTable.setSelectable(true);
        availableDeductionTable.setMultiSelect(true);

        selectedDeductionTable.setSizeFull();
        selectedDeductionTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedDeductionTable.setFilterGenerator(new DeductionsFilterGenerator());
        selectedDeductionTable.setColumnCheckBox(ConstantsUtils.SELECTED_CHECK, true);
        selectedDeductionTable.getFilterField(ConstantsUtils.SELECTED_CHECK).setVisible(false);



        selectedDeductionTable.setTableFieldFactory(new DeductionTableGenerator(selectedDeductionTable, availableDeductions, sessiondto, this));
        selectedDeductionTable.setEditable(true);
        selectedDeductionTable.setSelectable(true);
        selectedDeductionTable.setMultiSelect(true);
        selectedDeductionTable.setSelectable(true);
        selectedDeductionTable.setMultiSelect(true);
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), controlLayout);
        ResponsiveUtils.getResponsiveControls(selectedTableLogic.createControls(), controlLayout2);

        btnPopulate.setEnabled(false);

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
                    deductionBinder.getFields();
                    excelExportLogic();

                } catch (Exception exception) {
                    LOGGER.error(exception);
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
                    massCombo.setVisible(false);
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
  
        formulaType.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                    //To change body of generated methods, choose Tools | Templates.
                   if(!changedFTOnce){
                    if (selectedDeductionTable.size() > 0) {
                        MessageBox.showPlain(Icon.QUESTION, "Change Formula Type?", "Changing the Formula Type will clear the Selected Deductions section. Do you want to proceed?", new MessageBoxListener() {
                            /**
                             * Called when a Button has been clicked .
                             *
                             */
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (ButtonId.YES.equals(buttonId)) {
                                    try {
                                        changedFTOnce=false;
                                        ftValue=formulaType.getValue();
                                        logic.deleteSelectedFromTemp(sessiondto);
                                        onChangeFormulaType();
                                    } catch (Exception ex) { 
                                        LOGGER.info("Error-----" + ex);
                                    }
                                }else{
                                    changedFTOnce=true;
                                    formulaType.select(ftValue);
                                    
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);

                    } else {
                        changedFTOnce=false;
                        ftValue=formulaType.getValue();
                        onChangeFormulaType();
                    }
                } else {
                    changedFTOnce = false;
                }
            }
        });
        massField.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (("+/- Indicator").equals(massField.getValue())) {
                    massValue.setVisible(false);
                    massCombo.setVisible(true);
                    UIUtils utils = new UIUtils();
                    massCombo = utils.loadIndicator(massCombo, false);
                } else if (("Net Sales Rule No").equals(massField.getValue())) {
                    massValue.setVisible(true);
                    massValue.setValue(StringUtils.EMPTY);
                    massValue.addClickListener(new CustomTextField.ClickListener() {
                        @Override
                        public void click(CustomTextField.ClickEvent event) {
                            try {
                                lookUp = new NetSalesRuleLookUp(massValue, ruleDto,"Deduction");
                                    UI.getCurrent().addWindow(lookUp);
                                
                                lookUp.addCloseListener(new Window.CloseListener() {
                                    @SuppressWarnings("PMD")
                                    public void windowClose(final Window.CloseEvent e) {
                                        if (lookUp.isSelected) {
                                            ruleDto = lookUp.getDetailsDto();
                                            final Map<String, String> map = new HashMap<>();
                                            map.put("ruleNo", ruleDto.getRuleNo());
                                            map.put("ruleName", ruleDto.getRuleName());
                                            map.put("ruleSystemSID", String.valueOf(ruleDto.getRuleSystemId()));
                                            massValue.setData(map);
                                        }
                                    }
                                });
                            } catch (Exception e) { 
                                LOGGER.error(e);

                            }
                        }
                    });
                    massCombo.setVisible(false);
                } else {
                    massValue.setVisible(false);
                    massCombo.setVisible(false);
                }
            }
        });
        CommonUtil commonUtil = CommonUtil.getInstance();
        commonUtil.loadComboBox(marketType, com.stpl.app.global.abstractsearch.util.UIUtils.CONTRACT_TYPE, false);
        commonUtil.loadComboBox(deductionType, com.stpl.app.global.abstractsearch.util.UIUtils.DEDUCTION_TYPE, false);
        commonUtil.loadComboBox(deductionSubType, com.stpl.app.global.abstractsearch.util.UIUtils.DEDUCTION_SUB_TYPE, false);
        commonUtil.loadComboBox(deductionCategory, com.stpl.app.global.abstractsearch.util.UIUtils.DEDUCTION_CATEGORY, false);
        commonUtil.loadComboBox(formulaType, com.stpl.app.global.abstractsearch.util.UIUtils.NS_FORMULA_TYPE, false);

        if (!ConstantsUtils.ADD.equalsIgnoreCase(sessiondto.getMode())) {
            formulaType.select(nsfDto.getFormulaType());
        }
    }

    void searchBtnLogic() {
        searchBtn.addClickListener(new Button.ClickListener() {
            @SuppressWarnings("PMD")
            /**
             * Constant SerialID
             */
            private static final long serialVersionUID = 1L;

            /**
             * Logic for search button.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In configureFields searchButtonClickLogic started");
                try {
                    if ((formulaType.getValue() == null || String.valueOf(formulaType.getValue()).equals(ConstantsUtils.SELECT_ONE))) {
                        MessageBox.showPlain(Icon.ERROR, "Formula Type is Mandatory", "Please select Formula Type", ButtonId.OK);
                    } else if (StringUtils.isBlank(contractNo.getValue()) && (marketType.getValue() == null || String.valueOf(marketType.getValue()).equals(ConstantsUtils.SELECT_ONE))
                        && (deductionType.getValue() == null || String.valueOf(deductionType.getValue()).equals(ConstantsUtils.SELECT_ONE))
                            && StringUtils.isBlank(contractName.getValue()) && (deductionSubType.getValue() == null || String.valueOf(deductionSubType.getValue()).equals(ConstantsUtils.SELECT_ONE))
                            && StringUtils.isBlank(contractHolder.getValue()) && (deductionCategory.getValue() == null || String.valueOf(deductionCategory.getValue()).equals(ConstantsUtils.SELECT_ONE))
                            && StringUtils.isBlank(companyFamilyPlanNo.getValue()) && StringUtils.isBlank(companyFamilyPlanName.getValue()) && StringUtils.isBlank(itemFamilyPlanNo.getValue())
                            && StringUtils.isBlank(itemFamilyPlanName.getValue()) && StringUtils.isBlank(priceScheduleNo.getValue()) && StringUtils.isBlank(priceScheduleName.getValue())
                            && StringUtils.isBlank(deductionName.getValue()) && StringUtils.isBlank(deductionAlias.getValue())) {
                            
                        MessageBox.showPlain(Icon.ERROR, "No Search Criteria", "No search criteria were found. Please enter search criteria and try again", ButtonId.OK);

                    } else {
                        searchButtonClickLogic(event);
                    }

                } catch (Exception e) { 
                    LOGGER.error(e);
                }
                LOGGER.info("In configureFields searchButtonClickLogic Ended");
            }
        });
    }

    void resetBtnLogic() {
        resetBtn.addClickListener(new Button.ClickListener() {
            @SuppressWarnings("PMD")
            /**
             * Constant SerialID
             */
            private static final long serialVersionUID = 1L;

            /**
             * Logic for reset button.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {

                LOGGER.info("Entering Reset Logic");
                MessageBox.showPlain(Icon.QUESTION, "Reset Confirmation", "Are you sure you want to reset the values in the Search Criteria group box?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (ButtonId.YES.equals(buttonId)) {
                            contractNo.setValue(StringUtils.EMPTY);
                            contractName.setValue(StringUtils.EMPTY);
                            contractHolder.setValue(StringUtils.EMPTY);
                            companyFamilyPlanNo.setValue(StringUtils.EMPTY);
                            companyFamilyPlanName.setValue(StringUtils.EMPTY);
                            itemFamilyPlanNo.setValue(StringUtils.EMPTY);
                            itemFamilyPlanName.setValue(StringUtils.EMPTY);
                            priceScheduleNo.setValue(StringUtils.EMPTY);
                            priceScheduleName.setValue(StringUtils.EMPTY);
                            deductionNo.setValue(StringUtils.EMPTY);
                            deductionName.setValue(StringUtils.EMPTY);
                            deductionAlias.setValue(StringUtils.EMPTY);
                            deductionType.setValue(null);
                            deductionSubType.setValue(null);
                            deductionCategory.setValue(null);
                            marketType.setValue(null);
                        }

                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });

        resetTableBtn.addClickListener(new Button.ClickListener() {
            @SuppressWarnings("PMD")
            /**
             * Constant SerialID
             */
            private static final long serialVersionUID = 1L;

            /**
             * Logic for reset button.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {

                LOGGER.info("Entering Reset Logic");
                MessageBox.showPlain(Icon.QUESTION, "Reset Confirmation", "Are you sure you want to reset the values in the Selected Deductions group box?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (ButtonId.YES.equals(buttonId)) {
                            try {
                                if (sessiondto.getMode().equalsIgnoreCase("Add")) {

                                    massValue.setValue(StringUtils.EMPTY);
                                    massCombo.setValue(ConstantsUtils.SELECT_ONE);
                                    massField.setValue(ConstantsUtils.SELECT_ONE);
                                    massCheck.setValue(ConstantsUtils.DISABLE);
                                    logic.removeAll(sessiondto);

                                } else if (sessiondto.getMode().equalsIgnoreCase("Edit")) {
                                    logic.removeAll(sessiondto);
                                    if ("Contract".equalsIgnoreCase(nsfDto.getFormulaType().getDescription())) {
                                        nsfLogic.nsfInsert(sessiondto, "tempDeductionInsertContract", false, "tempSbInsert");
                                    } else {
                                        nsfLogic.nsfInsert(sessiondto, "tempDeductionInsert", false, "tempSbInsert");
                                    }
                                    
                                } else if (sessiondto.getMode().equalsIgnoreCase("Copy")) {
                                    logic.removeAll(sessiondto);
                                    if ("Contract".equalsIgnoreCase(nsfDto.getFormulaType().getDescription())) {
                                        nsfLogic.nsfInsert(sessiondto, "copyTempDeductionContractInsert", false, "tempSbInsert");
                                    } else {
                                        nsfLogic.nsfInsert(sessiondto, "copyTempDeductionInsert", false, "tempSbInsert");
                                    }
                                  
                                }
                                selectedDeductionTable.setFilterDecorator(new ExtDemoFilterDecorator());
                                selectedDeductionTable.setFilterGenerator(new DeductionsFilterGenerator());
                                refreshTable(); 
                            } catch (Exception ex) { 
                                LOGGER.info("Error-----" + ex);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });
        resetAvailBtn.addClickListener(new Button.ClickListener() {
            @SuppressWarnings("PMD")
            /**
             * Constant SerialID
             */
            private static final long serialVersionUID = 1L;

            /**
             * Logic for reset button.
             *
             * @param event
             */
            @SuppressWarnings("PMD")
            public void buttonClick(final Button.ClickEvent event) {

                LOGGER.info("Entering Reset Logic");
                MessageBox.showPlain(Icon.QUESTION, "Reset Confirmation", "Are you sure you want to reset the values in the Available Deductions group box?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (ButtonId.YES.equals(buttonId)) {
                           
                            availableDeductionTable.setFilterDecorator(new ExtDemoFilterDecorator());
                            availableDeductionTable.setFilterGenerator(new DeductionsFilterGenerator());
                            availableDeductions.removeAllItems();
                            availableDeductionTable.removeAllItems();
                            tableLogic.clearAll();
                           
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });


    }

    private ErrorfulFieldGroup getBinder() throws FieldGroup.CommitException {


        LOGGER.info("getItemBinder method Started ");
        deductionBinder.bindMemberFields(this);
        deductionBinder.setItemDataSource(new BeanItem<DeductionDto>(deductionDto));
        deductionBinder.setBuffered(true);
        deductionBinder.commit();
        LOGGER.info("getItemBinder method RETURNS customerBinder ");

        return deductionBinder;
    }

    private void searchButtonClickLogic(Button.ClickEvent event) {
        try {
            LOGGER.info("Entering searchButtonClickLogic");

            deductionBinder.commit();
            availableDeductions.removeAllItems();        
            tableLogic.setSearchData(availableDeductions, deductionBinder, "Add", sessiondto, false, false);
            tableLogic.setCurrentPage(tableLogic.getCurrentPage());

            LOGGER.info("tableLogic.isResultsEmpty()"+tableLogic.isResultsEmpty());
            if (tableLogic.isResultsEmpty()) {
                com.stpl.app.util.CommonUIUtils.successNotification(ConstantUtil.NO_RESULTS_COMPLETED);
                
            } else {
                com.stpl.app.util.CommonUIUtils.successNotification(ConstantUtil.SEARCH_COMPLETED);
            }

            LOGGER.info("searchButtonClickLogic Method ended");
        } catch (Exception ex) { 
            LOGGER.error(ex);
            LOGGER.info(" Exception" + ex);
        }

    }
  
    @UiHandler("addBtn")
    public void addBtnLogic(Button.ClickEvent event) {
        
                LOGGER.info("Entering add available Deductions Logic");
                final Set<DeductionDto> availableList = (Set<DeductionDto>) availableDeductionTable.getValue();
                if (availableDeductionTable.size() <= 0) {
                    final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "There are no items to move", new MessageBoxListener() {
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    return;

                } else if (availableList.isEmpty()) {
                    final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "Please select an item to add", new MessageBoxListener() {
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    return;
                }
                StringBuilder addQuery = new StringBuilder(StringUtils.EMPTY);
                for (DeductionDto item : availableList) {
                    if (item != null) {
                        try {

                            if (logic.addDuplicateValidation(item, sessiondto)) {
                             logic.addToTempTable(item, sessiondto, String.valueOf((HelperDTO) deductionBinder.getField("formulaType").getValue()).equals("Contract"), ruleDto,addQuery);
                            }
                        } catch (Exception ex) { 
                            LOGGER.info("Exception..In adding" + ex);
                        }
                    }
                }
                nsfLogic.update(addQuery.toString());
                
                selectedDeductions.addAll(availableList);
                refreshTable();
                refreshAvailableTable();
                LOGGER.info("Ending add available Deductions Logic");
    }

    public void loadSelectedTable() throws PortalException, SystemException {
        selectedDeductionTable.removeAllItems();
        selectedTableLogic.setSearchData(selectedDeductions, deductionBinder, "Add", sessiondto,
                String.valueOf((HelperDTO) deductionBinder.getField("formulaType").getValue()).equals("Contract"), true);
        selectedTableLogic.setCurrentPage(selectedTableLogic.getCurrentPage());
        if (formulaType.getValue()!=null && formulaType.getValue().toString().equals("Contract")) {
            selectedDeductionTable.setConverter(ConstantsUtils.START_DATE, new DateToStringConverter());
            selectedDeductionTable.setConverter(ConstantsUtils.END_DATE, new DateToStringConverter());
            selectedDeductionTable.setVisibleColumns(UIUtils.SELECTED_DED_COL1);
            selectedDeductionTable.setColumnHeaders(UIUtils.SELECTED_DED_HEADER1);
            selectedDeductionTable.setColumnAlignment(ConstantsUtils.START_DATE, ExtCustomTable.Align.CENTER);
            selectedDeductionTable.setColumnAlignment(ConstantsUtils.END_DATE, ExtCustomTable.Align.CENTER);
        } else {
            selectedDeductionTable.setVisibleColumns(UIUtils.SELECTED_DED_COL);
            selectedDeductionTable.setColumnHeaders(UIUtils.SELECTED_DED_HEADER);
        }
        selectedDeductionTable.setFilterDecorator(new ExtDemoFilterDecorator());
        selectedDeductionTable.setFilterGenerator(new DeductionsFilterGenerator());
        selectedDeductionTable.setColumnCheckBox(ConstantsUtils.SELECTED_CHECK, true);
        selectedDeductionTable.getFilterField(ConstantsUtils.SELECTED_CHECK).setVisible(false);
        selectedDeductionTable.setTableFieldFactory(new DeductionTableGenerator(selectedDeductionTable, availableDeductions, sessiondto, this));
        selectedDeductionTable.addStyleName(ConstantsUtils.FILTER_BAR);
        selectedDeductionTable.setSizeFull();
        selectedDeductionTable.setSelectable(false);
        selectedDeductionTable.setMultiSelect(false);
        selectedDeductionTable.addStyleName(ConstantsUtils.TABLE_CHECK_BOX);

        if (!ConstantsUtils.VIEW.equalsIgnoreCase(sessiondto.getMode())) {
            selectedDeductionTable.addColumnCheckListener(new ExtPagedTable.ColumnCheckListener() {
                @Override
                public void columnCheck(ExtPagedTable.ColumnCheckEvent event) {
                    if (ConstantsUtils.SELECTED_CHECK.equals(event.getPropertyId().toString())) {
                        if (event.isChecked()) {
                            try {

                                List<DeductionDto> itemIds = new ArrayList<>();
                                itemIds.addAll(selectedDeductions.getItemIds());
                                for (DeductionDto item : itemIds) {
                                    item.setSelectedCheck(true);
                                    logic.updateTempTable(true, item, sessiondto.getUiSessionId());
                                }

                                selectedDeductionTable.setColumnCheckBox(ConstantsUtils.SELECTED_CHECK, true, true);
                                selectedDeductionTable.setCurrentPage(selectedDeductionTable.getCurrentPage());
                            } catch (Exception ex) { 
                                LOGGER.error(ex);
                            }

                        } else {
                            try {
                                List<DeductionDto> itemIds = new ArrayList<>();
                                itemIds.addAll(selectedDeductions.getItemIds());
                                for (DeductionDto item : itemIds) {
                                    item.setSelectedCheck(false);
                                    logic.updateTempTable(false, item, sessiondto.getUiSessionId());
                                }

                                selectedDeductionTable.setColumnCheckBox(ConstantsUtils.SELECTED_CHECK, true, false);
                                selectedDeductionTable.setCurrentPage(selectedDeductionTable.getCurrentPage());
                            } catch (Exception ex) { 
                                LOGGER.error(ex);
                            }
                        }
                        refreshTable();
                    }
                }
            });
        } else {
            selectedDeductionTable.setColumnCheckBoxDisable(ConstantsUtils.SELECTED_CHECK, true);
        }
        getResponsiveFirstTab();
    }

    void removeBtnLogic() {
        removeBtn.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                if (!logic.isEmpty(sessiondto)) {
                    MessageBox.showPlain(Icon.QUESTION, "Remove Confirmation", "Are you sure you want to remove the selected value from the Selected Deductions list view?", new MessageBoxListener() {
                        /**
                         * Called when a Button has been clicked .
                         *
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            if (ButtonId.YES.equals(buttonId)) {
                                try {

                                    logic.removeFromTempTable(sessiondto);
                                    refreshTable();
                                    LOGGER.info("Ending Remove Method ");

                                } catch (Exception e) { 
                                    LOGGER.error(e);
                                }
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

                } else {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "No values selected to remove", "Please select at least one record in the ‘Selected Deductions’ list view to remove", new MessageBoxListener() {
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
        });

    }

    public NetSalesRuleLookupDto getRuleDto() {
        return ruleDto;
    }

    public void setRuleDto(NetSalesRuleLookupDto ruleDto) {
        this.ruleDto = ruleDto;
    }

    public HelperDTO getFormulaType() {
        return ((HelperDTO) (formulaType.getValue()==null? new HelperDTO():formulaType.getValue())); 
    }

    public boolean isTableSelected() {
        return selectedDeductionTable.size() > 0;
    }

    void btnPopoulateLogic() {
        btnPopulate.addClickListener(new Button.ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("Entering sales Basis Populate operation");
                try {
                    if (!logic.isEmpty(sessiondto)) {
                        populateLogic();
                    } else {
                        final MessageBox msg = MessageBox.showPlain(Icon.INFO, "No values in the Selected Deductions list view are selected", "Please select at least one value in the ‘Selected Deductions’ list view to apply the Mass Update to", new MessageBoxListener() {
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
                        return;
                    }
                } catch (Exception exception) {

                    LOGGER.error(exception);
                }
                LOGGER.debug("Ending sales Basis Populate operation");
            }
        });
    }

    public void disableDeductionFields() {
        cssLayout.setEnabled(false);
        cssSearchLayout.setEnabled(false);
        resetBtn.setEnabled(false);
        searchBtn.setEnabled(false);
        cssMassLayout.setEnabled(false);
        removeBtn.setEnabled(false);
        addBtn.setEnabled(false);
        resetTableBtn.setEnabled(false);
        deductionPanel.setEnabled(false);
        resetAvailBtn.setEnabled(false);
    }

    void populateLogic() throws SystemException, PortalException, ParseException {

        String fieldMass = "";
        boolean massflag = false;
        String populateField = StringUtils.EMPTY;
        String populateValue = StringUtils.EMPTY;
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        String sessionId = String.valueOf(sessiondto.getUiSessionId());
        String tempCreatedDate = String.valueOf(sessiondto.getSessionDate());
        if (massField.getValue() != null) {
            boolean netflag = false;
            if (("+/- Indicator").equals(massField.getValue())) {
                if (massCombo.getValue() != null) {
                    populateField = "INDICATOR";
                    LOGGER.info("massCombo.getValue().toString()" + massCombo.getValue().toString());

                    populateValue = massCombo.getValue().toString().equals("Add") ? "+" : "-";
                    massflag = true;

                } else {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Populate Error", "Please select value for the " + massField.getValue(), new MessageBoxListener() {
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
            } else {
                if (massValue.getValue() != null && StringUtils.isNotEmpty(massValue.getValue())) {

                    if (massField.getValue() != null && StringUtils.isNotEmpty(massValue.getValue())) {

                        fieldMass = massField.getValue().toString();
                        if ("Net Sales Rule No".equals(fieldMass)) {
                            populateField = "CDR_MODEL_SID";
                            Map<String, String> map = (HashMap) massValue.getData();
                            populateValue = "0".equals(map.get("ruleSystemSID"))?null:map.get("ruleSystemSID");
                            netflag = true;
                            massflag = true;
                        }
                    } else {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Populate Error", "Please enter value for the " + massField.getValue(), new MessageBoxListener() {
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
                }
            }
            if (massflag) {
                if (selectedDeductions.size() > 0) {
                    logic.populateLogic(netflag,userId, sessionId, tempCreatedDate, populateField, populateValue, deductionDto);
                    refreshTable();
                } else {
                    final MessageBox msg = MessageBox.showPlain(Icon.INFO, "Info", "There are no items for Mass Update", new MessageBoxListener() {
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    return;
                }

            }
            selectedDeductionTable.setCurrentPage(selectedDeductionTable.getCurrentPage());
            LOGGER.debug("Ending POPULATE method");

            massField.setValue("");
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

    public void refreshTable() {
        selectedDeductionTable.removeAllItems();
        selectedTableLogic.setSearchData(selectedDeductions, deductionBinder, "Add", sessiondto,
                String.valueOf((HelperDTO) deductionBinder.getField("formulaType").getValue()).equals("Contract"), true);
        selectedTableLogic.setCurrentPage(selectedTableLogic.getCurrentPage());

    }

    public void refreshAvailableTable() {
        availableDeductionTable.removeAllItems();
        tableLogic.setSearchData(availableDeductions, deductionBinder, "Add", sessiondto, false, false);
        tableLogic.setCurrentPage(tableLogic.getCurrentPage());

    }

    void configureButtonPermission() throws PortalException, SystemException {

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.NET_SALES_FORMULA + "," + "Deductions");
        if (functionHM.get("searchBtn") == null || !((AppPermission) functionHM.get("searchBtn")).isFunctionFlag()) {
            searchBtn.setVisible(false);
        }
        if (functionHM.get("addBtn") == null ||  !((AppPermission) functionHM.get("addBtn")).isFunctionFlag()) {
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
        if (functionHM.get("resetTableBtn") == null ||   !((AppPermission) functionHM.get("resetTableBtn")).isFunctionFlag()) {
            resetTableBtn.setVisible(false);
        }
        if (functionHM.get("resetAvailBtn") == null ||  !((AppPermission) functionHM.get("resetAvailBtn")).isFunctionFlag()) {
            resetAvailBtn.setVisible(false);
        }
        if (functionHM.get("excelExport") == null ||   !((AppPermission) functionHM.get("excelExport")).isFunctionFlag()) {
            excelExport.setVisible(false);
        }

    }

    private void getResponsiveFirstTab() throws PortalException, SystemException {
        LOGGER.info("Entering getFirstTab1");
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldNsfHM = stplSecurity
                    .getFieldOrColumnPermission(userId, UISecurityUtil.NET_SALES_FORMULA + "," + "Deductions", false);
            final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.NET_SALES_FORMULA + "," + "Deductions");
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.NET_SALES_FORMULA, "Deductions");
            LOGGER.info("------result===List" + resultList.size());
            securityLogic.removeComponentOnPermission(resultList, cssLayout, fieldNsfHM, sessiondto.getMode().equalsIgnoreCase("Copy")?"Add":sessiondto.getMode());
            securityLogic.removeComponentOnPermission(resultList, cssSearchLayout, fieldNsfHM, sessiondto.getMode().equalsIgnoreCase("Copy")?"Add":sessiondto.getMode());
            Object[] obj;
            Object[] obj1;
            if (formulaType.getValue()!=null && formulaType.getValue().toString().equals("Contract")) {
                availableDeductionTable.setConverter(ConstantsUtils.START_DATE, new DateToStringConverter());
                availableDeductionTable.setConverter(ConstantsUtils.END_DATE, new DateToStringConverter());
                availableDeductionTable.setColumnAlignment(ConstantsUtils.START_DATE, ExtCustomTable.Align.CENTER);
                availableDeductionTable.setColumnAlignment(ConstantsUtils.END_DATE, ExtCustomTable.Align.CENTER);
                selectedDeductionTable.setConverter(ConstantsUtils.START_DATE, new DateToStringConverter());
                selectedDeductionTable.setConverter(ConstantsUtils.END_DATE, new DateToStringConverter());
                obj1 = UIUtils.SELECTED_DED_COL1;
                obj = UIUtils.AVAILABLE_DED_COL1;
            } else {
                obj1 = UIUtils.SELECTED_DED_COL;
                obj = UIUtils.AVAILABLE_DED_COL;
            }
            TableResultCustom tableResultCustom = securityLogic.getTableColumnsPermission(resultList, obj, fieldNsfHM, sessiondto.getMode().equalsIgnoreCase("Copy")?"Add":sessiondto.getMode());
            if (tableResultCustom.getObjResult().length == 0) {
                availableDeductionTable.setVisible(false);
            } else {
                availableDeductionTable.setVisible(true);
            }
            int availSize = tableResultCustom.getObjResult().length;
            Object availColumnn[] = new Object[availSize];
            for (int i = 0; i < availSize; i++) {
                availColumnn[i] = tableResultCustom.getObjResult()[i];
                if (String.valueOf(availColumnn[i]).equalsIgnoreCase("marketType")) {
                    availColumnn[i] = "marketTypeTable";
                }
                if (String.valueOf(availColumnn[i]).equalsIgnoreCase("deductionType")) {
                    availColumnn[i] = "deductionTypeTable";
                }
                if (String.valueOf(availColumnn[i]).equalsIgnoreCase("deductionSubType")) {
                    availColumnn[i] = "deductionSubTypeTable";
                }
                 if (String.valueOf(availColumnn[i]).equalsIgnoreCase("deductionCategory")) {
                    availColumnn[i] = "deductionCategoryTable";
                }
            }
            availableDeductionTable.setVisibleColumns(availColumnn);
            availableDeductionTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
            TableResultCustom tableResultCustom2 = securityLogic.getTableColumnsPermission(resultList, obj1, fieldNsfHM, sessiondto.getMode().equalsIgnoreCase("Copy")?"Add":sessiondto.getMode());
            if (tableResultCustom2.getObjResult().length == 0) {
                selectedDeductionTable.setVisible(false);
            } else {
                selectedDeductionTable.setVisible(true);
            }
            
            Object columnn[]=new Object[tableResultCustom2.getObjResult().length+1];
            String header2[]=new String[tableResultCustom2.getObjResult().length+1];
            columnn[0]=ConstantsUtils.SELECTED_CHECK;
            header2[0]="";
            System.arraycopy(tableResultCustom2.getObjResult(), 0, columnn, 1, tableResultCustom2.getObjResult().length);
            System.arraycopy(tableResultCustom2.getObjResultHeader(), 0, header2, 1, tableResultCustom2.getObjResultHeader().length);
            selectedDeductionTable.setVisibleColumns(columnn);
            selectedDeductionTable.setColumnHeaders(header2); 
           
            availableDeductionTable.setFilterDecorator(new ExtDemoFilterDecorator());
            availableDeductionTable.setFilterGenerator(new DeductionsFilterGenerator());
            selectedDeductionTable.setFilterDecorator(new ExtDemoFilterDecorator());
            selectedDeductionTable.setFilterGenerator(new DeductionsFilterGenerator());

        } catch (Exception ex) { 
            LOGGER.error(ex);
        }
    }

    protected void excelExportLogic() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.info("Ending excelExportLogic");
    }

    private void createWorkSheet() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering createWorkSheet");
        boolean isContract = (String.valueOf((HelperDTO) deductionBinder.getField("formulaType").getValue()).equals("Contract"));
        long recordCount = 0;
        List list = logic.tempTableCount(null, null, isContract, true, 0, 0);
        if (list != null) {
            recordCount = Integer.valueOf(String.valueOf(list.get(0)));
        }
        ExcelExportforBB.createWorkSheet(selectedDeductionTable.getColumnHeaders(), recordCount, this, getUI(), TabNameUtil.DEDUCTIONS);
        LOGGER.info("Ending createWorkSheet");
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        DeductionDto dto;
         if(end !=0){
        boolean isContract = (String.valueOf((HelperDTO) deductionBinder.getField("formulaType").getValue()).equals("Contract"));
        final List<DeductionDto> searchList = logic.tempTableCount(null,null,isContract, false, start, end);
        if(null !=searchList){
        for (DeductionDto deductionList : searchList) {
            dto = deductionList;
            printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);
            if(isContract){
                printWriter.print(ConstantsUtils.QUOTE + dto.getContractNo() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                
                printWriter.print(ConstantsUtils.QUOTE + dto.getContractName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                
                printWriter.print(ConstantsUtils.QUOTE + dto.getDeductionNo() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                
                printWriter.print(ConstantsUtils.QUOTE + dto.getDeductionName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                if (dto.getDeductionType().getId() == 0) {
                    printWriter.print(StringUtils.EMPTY + ConstantsUtils.COMMA);
                }else{
                printWriter.print(ConstantsUtils.QUOTE + dto.getDeductionType() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                }
                if (dto.getDeductionSubType().getId() == 0) {
                    printWriter.print(StringUtils.EMPTY + ConstantsUtils.COMMA);
                }else{
                printWriter.print(ConstantsUtils.QUOTE + dto.getDeductionSubType() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                }
                  if (dto.getDeductionCategory().getId() == 0) {
                    printWriter.print(StringUtils.EMPTY + ConstantsUtils.COMMA);
                }else{
                printWriter.print(ConstantsUtils.QUOTE + dto.getDeductionCategory() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                  }
                printWriter.print(ConstantsUtils.QUOTE + dto.getMarketType() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                
                if (dto.getStartDate() != null) {
                    printWriter.print(dto.getStartDate() + ExcelExportUtil.COMMA);
                } else {
                    printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);
                }
                if (dto.getEndDate() != null) {
                    printWriter.print(dto.getEndDate() + ExcelExportUtil.COMMA);
                } else {
                    printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);
                }
                printWriter.print(ConstantsUtils.QUOTE + dto.getContractHolder() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                
                printWriter.print(ConstantsUtils.QUOTE + dto.getCompanyFamilyPlanNo() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                
                printWriter.print(ConstantsUtils.QUOTE + dto.getCompanyFamilyPlanName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                
                printWriter.print(ConstantsUtils.QUOTE + dto.getItemFamilyPlanNo() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                
                printWriter.print(ConstantsUtils.QUOTE + dto.getItemFamilyPlanName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                
                printWriter.print(ConstantsUtils.QUOTE + dto.getPriceScheduleNo() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                
                printWriter.print(ConstantsUtils.QUOTE + dto.getPriceScheduleName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            } else {
                if (dto.getDeductionType().getId() == 0) {
                    printWriter.print(StringUtils.EMPTY + ConstantsUtils.COMMA);
                } else {
                    printWriter.print(ConstantsUtils.QUOTE + dto.getDeductionType() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                }
                if (dto.getDeductionSubType().getId() == 0) {
                    printWriter.print(StringUtils.EMPTY + ConstantsUtils.COMMA);
                } else {
                    printWriter.print(ConstantsUtils.QUOTE + dto.getDeductionSubType() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                }
                if (dto.getDeductionCategory().getId() == 0) {
                    printWriter.print(StringUtils.EMPTY + ConstantsUtils.COMMA);
                } else {
                    printWriter.print(ConstantsUtils.QUOTE + dto.getDeductionCategory() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
                }
            }
            if (dto.getIndicator() != null) {
                printWriter.print(dto.getIndicator() + ExcelExportUtil.COMMA);
            } else {
                printWriter.print(StringUtils.EMPTY + ExcelExportUtil.COMMA);
            }
            printWriter.print(ConstantsUtils.QUOTE + dto.getNetSalesRuleNo() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
            printWriter.println(ConstantsUtils.QUOTE + dto.getNetSalesRuleName() + ConstantsUtils.QUOTE + ExcelExportUtil.COMMA);
        }
    }
    }
    }
    void onChangeFormulaType() {
        try {
            if (String.valueOf(formulaType.getValue()).equals("Contract")) {
                contractNo.setEnabled(true);
                contractName.setEnabled(true);
                contractHolder.setEnabled(true);
                companyFamilyPlanNo.setEnabled(true);
                companyFamilyPlanName.setEnabled(true);
                itemFamilyPlanNo.setEnabled(true);
                itemFamilyPlanName.setEnabled(true);
                marketType.setEnabled(true);
                deductionNo.setEnabled(true);
                deductionName.setEnabled(true);
                deductionAlias.setEnabled(true);
                priceScheduleNo.setEnabled(true);
                priceScheduleName.setEnabled(true);

            } else {
                contractNo.setEnabled(false);
                contractName.setEnabled(false);
                contractHolder.setEnabled(false);
                companyFamilyPlanNo.setEnabled(false);
                companyFamilyPlanName.setEnabled(false);
                itemFamilyPlanNo.setEnabled(false);
                itemFamilyPlanName.setEnabled(false);
                marketType.setEnabled(false);
                deductionNo.setEnabled(false);
                deductionName.setEnabled(false);
                deductionAlias.setEnabled(false);
                priceScheduleNo.setEnabled(false);
                priceScheduleName.setEnabled(false);

            }
            getResponsiveFirstTab();
            selectedDeductionTable.removeAllItems();
            availableDeductionTable.removeAllItems();
            loadSelectedTable();
        } catch (Exception ex) { 
            LOGGER.info("Error-----" + ex);
        }
    }
}
