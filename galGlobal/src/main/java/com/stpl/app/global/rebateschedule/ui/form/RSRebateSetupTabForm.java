
package com.stpl.app.global.rebateschedule.ui.form;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.ui.lookup.FormulaLookup;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.netsalesformula.dto.NetSalesRuleLookupDto;
import com.stpl.app.global.netsalesformula.ui.form.NetSalesRuleLookUp;
import com.stpl.app.global.rebateschedule.dto.ItemDetailsDTO;
import com.stpl.app.global.rebateschedule.dto.NetSalesFormulaDTO;
import com.stpl.app.global.rebateschedule.dto.RSFilterGenerate;
import com.stpl.app.global.rebateschedule.dto.RSFormulaDTO;
import com.stpl.app.global.rebateschedule.dto.RSItemTableGenerator;
import com.stpl.app.global.rebateschedule.dto.RebatePlanDTO;
import com.stpl.app.global.rebateschedule.dto.RsDeductionLookupDto;
import com.stpl.app.global.rebateschedule.logic.RebateScheduleLogic;
import com.stpl.app.global.rebateschedule.logic.tablelogic.RebateSetupTableLogic;
import com.stpl.app.global.rebateschedule.ui.lookup.DeductionDetailsLookup;
import com.stpl.app.global.rebateschedule.ui.lookup.NetSalesFormulaLookup;
import com.stpl.app.global.rebateschedule.ui.lookup.RebatePlanLookup;
import com.stpl.app.global.rebateschedule.ui.lookup.RsDeductionLookup;
import com.stpl.app.global.rebateschedule.util.LabelUtils;
import com.stpl.app.global.rebateschedule.util.RsUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ExcelExportUtil;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

public class RSRebateSetupTabForm extends CustomComponent implements View {

    RebatePlanLookup rebatePlanLookup=null;
    FormulaLookup formulaLookup =null;
    NetSalesFormulaLookup netSalesLookup = null;
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(RSRebateSetupTabForm.class);
    @UiField("massCheck")
    private OptionGroup massCheck;
    /**
     * The mass field.
     */
    @UiField("massField")
    private ComboBox massField;
    @UiField("massFieldLB")
    private Label massFieldLB;
    @UiField("valueLB")
    private Label valueLB;
    /**
     * The mass value.
     */
    @UiField("massValue")
    private TextField massValue;
    /**
     * The mass date.
     */
    @UiField("massDate")
    private PopupDateField massDate;
    /**
     * The rebatePlanName.
     */
    @UiField("rebatePlanNameDdlb")
    private ComboBox massDDLB;

    @UiField("massLookup")
    private CustomTextField massLookup;

    /**
     * The btn populate.
     */
    @UiField("btnPopulate")
    private Button btnPopulate;
    /**
     * The btn all populate.
     */
    @UiField("btnAllPopulate")
    private Button btnAllPopulate;
    @UiField("hlayout")
    private HorizontalLayout hlayout;

    @UiField("resultsPanel")
    private Panel resultsPanel;
    
    @UiField("excel")
    private Button excel;
    
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");

    private final ErrorfulFieldGroup binder;

    private final RebateScheduleLogic rebateLogic;
    private final BeanItemContainer<ItemDetailsDTO> itemResultBean;
    private String calculationType = StringUtils.EMPTY;
    /**
     * The field mass.
     */
    private String fieldMass = StringUtils.EMPTY;

    public boolean populate = true;

    /**
     * The map.
     */
    private final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

    private final RSInfoTabForm infoTab;
    final StplSecurity stplSecurity = new StplSecurity();
    private String userId;
    private final IFPLogic ifpLogic = new IFPLogic();
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    /**
     * The ifp item list.
     */
    private final List<ItemDetailsDTO> ifpItemList = new ArrayList<>();

    @UiField("tableLayout")
    private VerticalLayout tableLayout;

    @UiField("controlLayout")
    private HorizontalLayout controlLayout;

    @UiField("periodCheckBox")
    private OptionGroup periodCheckBox;

    private final CommonUtil commonUtil = CommonUtil.getInstance();

    private final BeanItemContainer<ItemDetailsDTO> itemDetailsContainer = new BeanItemContainer<>(ItemDetailsDTO.class);

    private final RebateSetupTableLogic rebateSetupTableLogic;

    private final ExtPagedTable itemDetailsTable;

    @UiField("cssLayout")
    CssLayout cssLayout;

    
    /**
     * Record checkbox
     */
    @UiField("record")
    private OptionGroup record;
    
    private final HelperDTO dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private final boolean isViewMode;
    private List<Object> resultList;
    private Map<String, AppPermission> fieldRsHM;
    private String mode;
    SessionDTO sessionDTO;
    /**
     * The rebate schedule system id.
     */
    private final TextField rebateScheduleSystemId = new TextField();
    RsDeductionLookup rsDeductionLookup;
    NetSalesRuleLookUp  calculationLookup =null;
    HelperListUtil helperList=HelperListUtil.getInstance();

    public RSRebateSetupTabForm(final ErrorfulFieldGroup binder, final RebateScheduleLogic rebateLogic, final BeanItemContainer<ItemDetailsDTO> itemResultBean,
            final LazyBeanItemContainer identifierTypeDescContainer, final RSInfoTabForm infoTab,
            String mode, final SessionDTO sessionDTO) throws Exception {
        this.binder = binder;
        this.rebateLogic = rebateLogic;
        this.itemResultBean = itemResultBean;
        this.infoTab = infoTab;
        this.sessionDTO = sessionDTO;
        this.mode = mode;
        rebateSetupTableLogic = new RebateSetupTableLogic(this.sessionDTO);
        itemDetailsTable = new ExtPagedTable(rebateSetupTableLogic);
        this.isViewMode = ConstantsUtils.VIEW.equals(mode);
        init();
    }

    private void init() throws Exception {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/rebateschedule/rebatesetup.xml"), this));
        getBinder();
        addToContent();
        configureFields();
    }

    private ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        return binder;
    }

    /**
     * Adds the content and configures the fields to the layout.
     *
     * @throws Exception
     */
    private void addToContent() throws Exception {
        tableLayout.addComponent(itemDetailsTable);
        addItemDetailsTable();
        ResponsiveUtils.getResponsiveControls(rebateSetupTableLogic.createControls(), controlLayout);
        userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> fieldRsHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.REBATE_SCHEDULE+","+"Rebate Setup",false);
        final Map<String, AppPermission> functionRsHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.REBATE_SCHEDULE+","+"Rebate Setup");
        addResponsiveness(fieldRsHM);
        if (functionRsHM.get(FunctionNameUtil.POPULATE) != null && ((AppPermission) functionRsHM.get(FunctionNameUtil.POPULATE)).isFunctionFlag()) {
            addBtnPopulate();
        }else{
            btnPopulate.setVisible(false);
        }
        if (functionRsHM.get(FunctionNameUtil.POPULATE_ALL) != null && ((AppPermission) functionRsHM.get(FunctionNameUtil.POPULATE_ALL)).isFunctionFlag()) {
            addAllBtnPopulate();
       }else{
            btnAllPopulate.setVisible(false);
        }
        excel.setIcon(excelExportImage);
        excel.setDescription("Export to excel");
        excel.setIconAlternateText("Excel export");
        excel.setHtmlContentAllowed(true);
        record.addItems("Current", "History", "Future");
        record.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                try{
                    String value = String.valueOf(record.getValue());
                if (isViewMode) {
                    itemResultBean.removeAllItems();
                    final String idValue = String.valueOf(sessionDTO.getSystemId());
                    final int systemId = Integer.valueOf(idValue);
                    itemResultBean.addAll(rebateLogic.getItemDetails(systemId,value));
                    itemDetailsTable.setContainerDataSource(itemResultBean);
                    itemDetailsTable.setVisibleColumns(Arrays.copyOfRange(RsUtils.REBATE_SETUP_FORMULA, 1, RsUtils.REBATE_SETUP_FORMULA.length));
                    itemDetailsTable.setColumnHeaders(Arrays.copyOfRange(RsUtils.REBATE_SETUP_FORMULA_HEADER, 1, RsUtils.REBATE_SETUP_FORMULA_HEADER.length));
                    loadBasedOnCalculationType(String.valueOf(binder.getField("calculationType").getValue()));
                } else {
                    rebateSetupTableLogic.setSearchData(itemResultBean, value);
                    rebateSetupTableLogic.setCurrentPage(1);
                }
            }catch(Exception ex){
            LOGGER.error(ex);
        }
        }
        });
    }

    /**
     *
     * @throws Exception
     */
    public void addItemDetailsTable() throws Exception {

        userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        fieldRsHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.REBATE_SCHEDULE+","+"Rebate Setup",false);
        resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.REBATE_SCHEDULE, "Rebate Setup");
        Object[] obj = RsUtils.REBATE_SETUP_FORMULA;
        TableResultCustom tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldRsHM, mode);
        if (tableResultCustom.getObjResult().length == 0) {
            itemDetailsTable.setVisible(false);
        }
        rebateSetupTableLogic.setContainerDataSource(itemDetailsContainer);
        rebateSetupTableLogic.sinkItemPerPageWithPageLength(false);
        rebateSetupTableLogic.setPageLength(7);
        itemDetailsTable.markAsDirty();
        if (isViewMode) {
            itemDetailsTable.setContainerDataSource(itemResultBean);
            hlayout.setVisible(false);
        } else {
            loadItemDetailsTable();
        }
        itemDetailsTable.addStyleName(ConstantsUtils.TABLE_CHECK_BOX);
        itemDetailsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        itemDetailsTable.addStyleName(ConstantsUtils.FILTER_BAR);
        itemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
        itemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        itemDetailsTable.setImmediate(true);
        itemDetailsTable.setFilterBarVisible(true);
        itemDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        itemDetailsTable.setFilterGenerator(new RSFilterGenerate());
        itemDetailsTable.setValidationVisible(false);
        itemDetailsTable.setSizeFull();
        if (!isViewMode) {
            itemDetailsTable.setSelectable(true);
            itemDetailsTable.setEditable(true);
            itemDetailsTable.setTableFieldFactory(new RSItemTableGenerator(itemDetailsTable, itemResultBean, infoTab.getRebatePlanLevelCaption(), rebateSetupTableLogic));

            itemDetailsTable.setFilterFieldVisible(ConstantsUtils.CHECK_BOX, false);
            itemDetailsTable.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, false);
        } else {
            itemDetailsTable.setFilterBarVisible(false);
            itemDetailsTable.setVisibleColumns(Arrays.copyOfRange(RsUtils.REBATE_SETUP_FORMULA, 1, RsUtils.REBATE_SETUP_FORMULA.length));
            itemDetailsTable.setColumnHeaders(Arrays.copyOfRange(RsUtils.REBATE_SETUP_FORMULA_HEADER, 1, RsUtils.REBATE_SETUP_FORMULA_HEADER.length));
        }

        itemDetailsTable.setColumnWidth(ConstantsUtils.CHECK_BOX, 54);
        itemDetailsTable.addItemClickListener(new ItemClickListener() {
            /**
             * Method used for item Details table
             */
            @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                // item click method .
            }
        });
        itemDetailsTable.setErrorHandler(new ErrorHandler() {
            /**
             * Method used to Handling error for item Details table
             */
            public void error(final com.vaadin.server.ErrorEvent event) {

            }
        });

        itemDetailsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                if (ConstantsUtils.CHECK_BOX.equals(event.getPropertyId().toString())) {
                    if (event.isChecked()) {
                        try {
                            rebateLogic.saveToTemp(itemResultBean.getItemIds(), false);
                            itemResultBean.removeAllItems();
                            rebateLogic.populateLogic(null, null, true, "check", null);
                            // this setCurrentPage is used to refresh the
                            // companyDetailsResultLazyBean lazy conatiner
                            itemDetailsTable.setCurrentPage(itemDetailsTable.getCurrentPage());
                            itemDetailsTable.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, true);
                            rebateSetupTableLogic.setCurrentPage(rebateSetupTableLogic.getCurrentPage());
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }

                    } else {
                        try {
                            rebateLogic.saveToTemp(itemResultBean.getItemIds(), false);
                            itemResultBean.removeAllItems();
                            rebateLogic.populateLogic(null, null, true, "uncheck", null);
                            // this setCurrentPage is used to refresh the
                            // companyDetailsResultLazyBean lazy conatiner
                            itemDetailsTable.setCurrentPage(itemDetailsTable.getCurrentPage());
                            itemDetailsTable.setColumnCheckBox(ConstantsUtils.CHECK_BOX, true, false);
                            rebateSetupTableLogic.setCurrentPage(rebateSetupTableLogic.getCurrentPage());
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                }
            }
        });
        itemDetailsTable.addGeneratedColumn(ConstantsUtils.DEDUCTION_CALENDAR_NAME, new ExtPagedTreeTable.ColumnGenerator() {

            @Override
            public Object generateCell(ExtCustomTable source, Object itemId, Object columnId) {

                final ItemDetailsDTO tableDto = getBeanFromId(itemId);
                Button deductionLink = new Button(tableDto.getDeductionCalendarName());
                deductionLink.setStyleName(Reindeer.BUTTON_LINK);
                deductionLink.setImmediate(true);
                deductionLink.setData(tableDto.getDeductionCalendarName());
                deductionLink.addClickListener(new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(final Button.ClickEvent event) {
                        final DeductionDetailsLookup lookUp = new DeductionDetailsLookup(ConstantsUtils.VIEW,Integer.parseInt(tableDto.getDeductionSystemId()));
                        UI.getCurrent().addWindow(lookUp);
                        lookUp.addCloseListener(new Window.CloseListener() {

                            @Override
                            public void windowClose(Window.CloseEvent e) {

                            }
                        });
                    }
                });
                return deductionLink;
            }
        }); 

    }

    private void configureFields() throws Exception {
        

        valueLB.setVisible(false);
        if (isViewMode) {
            hlayout.setVisible(false);
        }
        massDDLB.setPageLength(7);
        massDDLB.setImmediate(true);
        massDDLB.setNullSelectionAllowed(true);
        massDDLB.setInputPrompt(ConstantsUtils.SELECT_ONE);
        massDDLB.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        massDDLB.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        massDDLB.markAsDirty();
        massFieldLB.addStyleName("minWidthLabelImportant");
        massFieldLB.setWidth("50px");
        valueLB.addStyleName("minWidthLabelImportant");
        massDDLB.select(dto);
        massDDLB.setVisible(false);
        massDDLB.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Logic for value change event.
             *
             * @param event
             */
            public void valueChange(final ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && StringUtils.EMPTY.equals(event.getProperty().getValue())) {
                    massDDLB.select(ConstantsUtils.SELECT_ONE);
                }

            }
        });

        massDate.setDescription(ConstantsUtils.DATE_DES);
        massLookup.setImmediate(true);
        massLookup.addStyleName("searchicon");
        massLookup.setVisible(false);

        periodCheckBox.setImmediate(true);
        periodCheckBox.setMultiSelect(true);
        periodCheckBox.addItem("Current");
        periodCheckBox.addItem("History");
        periodCheckBox.addItem("Future");
        periodCheckBox.addStyleName("horizontal");
        periodCheckBox.addStyleName("optiongroupwidth");
        periodCheckBox.setEnabled(false);

        massCheck.addItem(ConstantsUtils.ENABLE);
        massCheck.addItem(ConstantsUtils.DISABLE);
        massCheck.setValue(ConstantsUtils.DISABLE);
        massCheck.setImmediate(true);
        massCheck.setStyleName("horizontal");
        massCheck.setDescription((String) massCheck.getValue());
        massField.setImmediate(true);
        massField.setEnabled(false);
        massValue.setVisible(false);

        massDate.setImmediate(true);
        massDate.setVisible(false);
        massDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        massDate.setId("MassDate");
        massDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for mass update in Value Change Listener
             */
            public void valueChange(final ValueChangeEvent event) {
                massDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(massDate.getValue()));
            }
        });
        btnPopulate.setEnabled(false);
        btnAllPopulate.setEnabled(false);
        btnAllPopulate.setImmediate(true);
        massField.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used massField for value change listener
             */
            public void valueChange(final ValueChangeEvent event) {
                try {
                    final String value = String.valueOf(massField.getValue());
                    massField.setDescription(value);
                   if (RsUtils.REBATE_AMOUNT.equals(value) || "Bundle No".equals(value)||RsUtils.EVALUATION_RULE_BUNDLE.equals(value) || RsUtils.CALCULATION_RULE_BUNDLE.equals(value)) {
                        valueLB.setVisible(true);
                        massValue.setVisible(true);
                        massDate.setVisible(false);
                        massDDLB.setVisible(false);
                        massLookup.setVisible(false);
                        btnPopulate.setEnabled(true);
                        btnAllPopulate.setEnabled(true);
                    } else if (LabelUtils.ITEM_REBATE_START_DATE.equals(value) || LabelUtils.ITEM_REBATE_END_DATE.equals(value)) {
                        massValue.setVisible(false);
                        valueLB.setVisible(true);
                        massDate.setVisible(true);
                        massDDLB.setVisible(false);
                        massLookup.setVisible(false);
                        btnPopulate.setEnabled(true);

                    } else if (RsUtils.REBATE_PLAN_NAME.equals(value)) {
                        massValue.setVisible(false);
                        massDate.setVisible(false);
                        valueLB.setVisible(true);
                        massDDLB.setVisible(true);
                        massLookup.setVisible(false);
                        
                        if ("Single Rebate Plan".equals(infoTab.getRebatePlanLevelCaption())) {

                            btnPopulate.setEnabled(false);
                        } else {
                            btnPopulate.setEnabled(true);
                        }
                        btnAllPopulate.setEnabled(true);
                    } else if ("RS Status".equals(value)) {
                        massLookup.setVisible(false);
                        massValue.setVisible(false);
                        massDate.setVisible(false);
                        massDDLB.setVisible(true);
                        valueLB.setVisible(true);
                        commonUtil.loadComboBox(massDDLB, "STATUS", false);
                        btnPopulate.setEnabled(true);
                        btnAllPopulate.setEnabled(true); 

                    } else if (ConstantsUtils.REBATE_PLAN_NO_LAB.equals(value) || "Formula No".equals(value) || RsUtils.NET_SALES_FORMULA.equals(value) || "Deduction Calendar No".equals(value)
                            || RsUtils.NET_SALES_RULE.equals(value) || RsUtils.EVALUATION_RULE.equals(value) || RsUtils.CALCULATION_RULE.equals(value)  ) {
                        massValue.setVisible(false);
                        massDate.setVisible(false);
                        massDDLB.setVisible(false);
                        massLookup.setVisible(true);
                        valueLB.setVisible(true);
                        massLookup.setValue(StringUtils.EMPTY);
                    }else{
                          valueLB.setVisible(false);
                        massValue.setVisible(false);
                        massDate.setVisible(false);
                        massDDLB.setVisible(false);
                        massLookup.setVisible(false);
                    }
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(RSRebateSetupTabForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        massLookup.addClickListener(new CustomTextField.ClickListener() {

            @Override
            public void click(CustomTextField.ClickEvent event) {
                try {
                    
                    if (ConstantsUtils.REBATE_PLAN_NO_LAB.equals(massField.getValue().toString())) {
                        if(rebatePlanLookup==null){
                        rebatePlanLookup = new RebatePlanLookup();
                        UI.getCurrent().addWindow(rebatePlanLookup);
                        rebatePlanLookup.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                if (rebatePlanLookup.isSelected()) {
                                    RebatePlanDTO rebatePlanDTO = rebatePlanLookup.getSelectedItem();
                                    massLookup.setValue(rebatePlanLookup.getSelectedItem().getRebatePlanNo());
                                    final Map<String, String> map = new HashMap<>();
                                    map.put("rebatePlanNo", rebatePlanDTO.getRebatePlanNo());
                                    map.put("rebatePlanSystemId", rebatePlanDTO.getRebatePlanSystemId());
                                    map.put("rebatePlanName", rebatePlanDTO.getRebatePlanName());
                                    massLookup.setData(map);
                                }
                                rebatePlanLookup=null;
                            }
                        });
                        }
                    
                    } else if(RsUtils.NET_SALES_FORMULA.equals(massField.getValue().toString())) {
                        if(netSalesLookup==null){
                        netSalesLookup = new NetSalesFormulaLookup(massLookup);
                        UI.getCurrent().addWindow(netSalesLookup);
                        netSalesLookup.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                if (netSalesLookup.isSelected()) {
                                    NetSalesFormulaDTO rebatePlanDTO = netSalesLookup.getSelectedItem();
                                    final Map<String, String> map = new HashMap<>();
                                    map.put("netSalesFormulaNo", rebatePlanDTO.getNetSalesFormulaNo());
                                    map.put(ConstantsUtils.SYS_ID, rebatePlanDTO.getSystemID());
                                    map.put("netSalesFormulaName", rebatePlanDTO.getNetSalesFormulaName());
                                    massLookup.setData(map);
                                }
                                netSalesLookup=null;
                            }
                        });
                        }
                    } else if ("Deduction Calendar No".equals(massField.getValue().toString())) {
                        if (rsDeductionLookup == null) {
                            rsDeductionLookup = new RsDeductionLookup(massLookup);
                            UI.getCurrent().addWindow(rsDeductionLookup);
                            rsDeductionLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    if (rsDeductionLookup.isSelected) {
                                        RsDeductionLookupDto deductionDto = rsDeductionLookup.getDeductionDto();
                                        final Map<String, String> map = new HashMap<>();
                                        map.put(ConstantsUtils.DEDUCTION_CALENDAR_NO, deductionDto.getDeductionNo());
                                        map.put("deductionSystemId", deductionDto.getDeductionSystemId());
                                        map.put(ConstantsUtils.DEDUCTION_CALENDAR_NAME, deductionDto.getDeductionName());
                                        massLookup.setData(map);
                                    }
                                    rsDeductionLookup = null;
                                }
                            });
                        }
                    } else if (RsUtils.NET_SALES_RULE.equals(massField.getValue().toString()) || RsUtils.EVALUATION_RULE.equals(massField.getValue().toString()) || RsUtils.CALCULATION_RULE.equals(massField.getValue().toString())) {
                            String windowName=StringUtils.EMPTY;
                            HelperDTO  tempDto=new HelperDTO();
                            String tempMassField=StringUtils.EMPTY;
                            if(RsUtils.NET_SALES_RULE.equals(massField.getValue().toString())){
                            tempMassField="Net Sales";
                            windowName="Net Sales Rule LookUp ";
                            
                            }else if(RsUtils.CALCULATION_RULE.equals(massField.getValue().toString())){
                             tempMassField="Calculation";
                             windowName="Calculation Rule LookUp ";
                            }else if(RsUtils.EVALUATION_RULE.equals(massField.getValue().toString())){
                            tempMassField="Evaluation";
                              windowName="Evaluation Rule LookUp ";
                            }
                            
                           for(HelperDTO helperDto: helperList.getListNameMap().get("RULE_TYPE")){
                               if(helperDto.getDescription().equalsIgnoreCase(tempMassField)){
                                  tempDto.setId(helperDto.getId());
                               tempDto.setDescription(helperDto.getDescription());
                               break;
                               }
                            }
                            calculationLookup = new NetSalesRuleLookUp(tempDto,windowName,"Rebate Schedule");
                           
                            
                            UI.getCurrent().addWindow(calculationLookup);
                            calculationLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                  
                                      if (calculationLookup.isSelected) {
                                          
                                         NetSalesRuleLookupDto searchDTO = calculationLookup.getSelectedItem();
                                          massLookup.setValue(searchDTO.getRuleName());
                                          
                                         final Map<String, String> map = new HashMap<>();
                                         
                                          if (RsUtils.NET_SALES_RULE.equals(massField.getValue().toString())) {
                                              map.put(ConstantsUtils.NET_SALES_RULE, searchDTO.getRuleName());
                                              map.put(ConstantsUtils.SYS_ID, searchDTO.getRuleSystemId());
                                          } else if (RsUtils.CALCULATION_RULE.equals(massField.getValue().toString())) {
                                              map.put(ConstantsUtils.NET_SALES_RULE, searchDTO.getRuleName());
                                              map.put(ConstantsUtils.SYS_ID, searchDTO.getRuleSystemId());
                                          } else if (RsUtils.EVALUATION_RULE.equals(massField.getValue().toString())) {
                                              map.put(ConstantsUtils.NET_SALES_RULE, searchDTO.getRuleName());
                                              map.put(ConstantsUtils.SYS_ID, searchDTO.getRuleSystemId());
                                          }
                                          massLookup.setData(map);
                                          calculationLookup.setData(searchDTO.getRuleSystemId());
                                          
                                         
                                      }
                                  
                                       
                                 
                                }
                            });
                        
                    } else {
                        if( formulaLookup ==null){
                        formulaLookup = new FormulaLookup();
                        UI.getCurrent().addWindow(formulaLookup);
                        formulaLookup.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                if (formulaLookup.isSelected()) {
                                    RSFormulaDTO rSFormulaDTO = formulaLookup.getSelectedItem();
                                    massLookup.setValue(rSFormulaDTO.getFormulaNo());
                                    final Map<String, String> map = new HashMap<>();
                                    map.put("formulaNo", rSFormulaDTO.getFormulaNo());
                                    map.put("formulaName", rSFormulaDTO.getFormulaName());
                                    map.put("formulaID", rSFormulaDTO.getFormulaID());
                                    massLookup.setData(map);
                                }
                                formulaLookup=null;
                            }
                        });
                    }
                    }
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(RSRebateSetupTabForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        massCheck.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for mass check
             */
            public void valueChange(final ValueChangeEvent event) {
                try {
                    massCheck.setDescription((String) massCheck.getValue());
                    massCheck.focus();
                    massCheckOnChangeEvent(event.getProperty().getValue());
                } catch (SystemException sysException) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
                    LOGGER.error(errorMsg);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {  
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
                } catch (PortalException portException) {
                    LOGGER.error(portException);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {  
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
                } catch (Exception exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() { 
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
                    LOGGER.error(exception);

                }
            }
        });

        map.put(RsUtils.REBATE_AMOUNT, ConstantsUtils.REBATE_AMOUNT);
        map.put("Start Date", ConstantsUtils.START_DATE);
        map.put("End Date", ConstantsUtils.END_DATE);
        map.put(RsUtils.REBATE_PLAN_NAME, ConstantsUtils.REBATE_PLAN_NAME);
        map.put(RsUtils.DEDUCTION_CALENDAR_NAME, ConstantsUtils.DEDUCTION_CALENDAR_NAME);
        // TODO Auto-generated method stub

    }

    public void addBtnPopulate() throws Exception {

        btnPopulate.setReadOnly(true);
        btnPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for button Populate logic
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });

        btnPopulate.addClickListener(new ClickListener() {
            /**
             * Method used for populate button logic
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.info("Entering Rebate Schedule Populate operation in Add");
                    binder.getErrorDisplay().clearError();
                    boolean massUpdate;
                    boolean tempflag = false;
                    
                    if (massField.getValue() == null || massField.getValue().toString().equals(ConstantsUtils.SELECT_ONE)) {
                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Error", "Select a Field.", new MessageBoxListener() {  
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
                        massUpdate = false;
                    } else {
                        final String value = String.valueOf(massField.getValue());
                        if (RsUtils.REBATE_AMOUNT.equals(value) && massValue.getValue() != null) {
                            massUpdate = true;
                        } else if (RsUtils.REBATE_PLAN_NAME.equals(value) && massDDLB.getValue() != null) {
                            massUpdate = true;
                        } else if ((LabelUtils.ITEM_REBATE_START_DATE.equals(value) || LabelUtils.ITEM_REBATE_END_DATE.equals(value)) && massDate.getValue() != null || "Bundle No".equals(value) || "RS Status".equals(value)) {
                            massUpdate = true;
                        } else if (value.equals(ConstantsUtils.REBATE_PLAN_NO_LAB) || value.equals("Formula No")) {
                            massUpdate = true; 
                        } else if (value.equals("Deduction Calendar No")){
                            massUpdate = massLookup.getValue() != null && StringUtils.isNotBlank(String.valueOf(massLookup.getValue()));
                        } else if (value.equals(RsUtils.NET_SALES_FORMULA)){
                            massUpdate = massLookup.getValue() != null && StringUtils.isNotBlank(String.valueOf(massLookup.getValue()));
                        } else if (RsUtils.NET_SALES_RULE.equals(massField.getValue().toString()) || RsUtils.EVALUATION_RULE.equals(massField.getValue().toString()) || RsUtils.CALCULATION_RULE.equals(massField.getValue().toString())){
                            massUpdate = massLookup.getValue() != null && StringUtils.isNotBlank(String.valueOf(massLookup.getValue()));
                        }
                        else if (RsUtils.EVALUATION_RULE_BUNDLE.equals(massField.getValue().toString()) || RsUtils.CALCULATION_RULE_BUNDLE.equals(massField.getValue().toString())) {
                            massUpdate = true;
                        } else {
                            massUpdate = false;
                        }

                    }

                    if (massUpdate) {
                        LOGGER.info("Entering Mass update operation");
                        populate = true;
                        final String populateField;
                        final String populateValue;
                                               final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                        rebateLogic.saveToTemp(itemResultBean.getItemIds(), false);
                        fieldMass = map.get(massField.getValue().toString()) == null ? massField.getValue().toString() : map.get(massField.getValue().toString());
                        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                        String sessionId = String.valueOf(sessionDTO.getUiSessionId());
                        String tempCreatedDate = String.valueOf(sessionDTO.getSessionDate());
                        List<Object> itemList = rebateLogic.validateNull(userId, sessionId, tempCreatedDate, "tempCheckedCount");
                        if (itemList.isEmpty()) {
                            binder.getErrorDisplay().setError("Please select an item to populate");
                            return;
                        }
                        if (fieldMass.equals(ConstantsUtils.START_DATE)) {
                            populateField = "ITEM_REBATE_START_DATE";
                            populateValue = fmt.format(massDate.getValue());
                        } else if (fieldMass.equals(ConstantsUtils.END_DATE)) {
                            populateField = "ITEM_REBATE_END_DATE";
                            populateValue = fmt.format(massDate.getValue());
                        } else if (fieldMass.equals("Bundle No")) {
                            populateField = "RS_DETAILS_BUNDLE_NO";
                            populateValue = massValue.getValue();
                        } else if (fieldMass.equals("RS Status")) {
                            populateField = "RS_DETAILS_ATTACHED_STATUS";
                            populateValue = String.valueOf(((HelperDTO) massDDLB.getValue()).getId()); 
                        } else if (fieldMass.equals(ConstantsUtils.REBATE_PLAN_NO_LAB) || fieldMass.equals("Formula No")) {
                            populateField = StringUtils.EMPTY;
                            populateValue = StringUtils.EMPTY;
                            rebateLogic.saveToTemp(itemResultBean.getItemIds(), false);
                            rebateLogic.massPopulateForLookUp(fieldMass, massLookup.getData(),true);
                             tempflag=true;
                            itemResultBean.removeAllItems();
                            refreshTable();
                        } else if (fieldMass.equals(ConstantsUtils.REBATE_AMOUNT)) {
                            Boolean saveFlag = rebateLogic.rebateSaveValidation("RSDetailsRebatePlanName");
                            if (saveFlag) {
                                binder.getErrorDisplay().setError("The Rebate Plan Name is already selected for items. You should deselect the Rebate Plan Name to populate Rebate Plan Amount.");
                                return;
                            }
                            populateField = "RS_DETAILS_REBATE_AMOUNT";
                            populateValue = String.valueOf(massValue.getValue()).trim();
                        } else if ("Deduction Calendar No".equals(fieldMass) ) {
                          Map<String,String> deductionMap = (Map)  massLookup.getData();  
                            rebateLogic.massPopulateDeductionLookUp(fieldMass, deductionMap,true);
                            populateField = "DEDUCTION_CALENDAR_MASTER_SID";
                            populateValue = deductionMap.get("deductionSystemId");
                            massLookup.setValue(StringUtils.EMPTY);
                            tempflag=true;
                            itemResultBean.removeAllItems();
                            refreshTable();
                        }else if (RsUtils.NET_SALES_FORMULA.equals(fieldMass) ) {
                            Map<String,String> netSalesMap = (Map)  massLookup.getData();  
                            rebateLogic.massPopulateNetSalesLookUp(fieldMass, netSalesMap,true);
                            populateField = "NET_SALES_FORMULA_MASTER_SID";
                            populateValue = map.get(ConstantsUtils.SYS_ID);
                            massLookup.setValue(StringUtils.EMPTY);
                            tempflag=true;
                            itemResultBean.removeAllItems();
                            refreshTable();
                        }else if (RsUtils.NET_SALES_RULE.equals(massField.getValue().toString()) || RsUtils.EVALUATION_RULE.equals(massField.getValue().toString()) || RsUtils.CALCULATION_RULE.equals(massField.getValue().toString()) ) {
                            Map<String,String> rulemap = (Map)  massLookup.getData();  
                            rebateLogic.massPopulateRuleLookUps(fieldMass, rulemap,true);
                           populateField = StringUtils.EMPTY;
                           populateValue = StringUtils.EMPTY;
                            massLookup.setValue(StringUtils.EMPTY);
                            tempflag=true;
                            itemResultBean.removeAllItems();
                            refreshTable();
                        }else if (RsUtils.EVALUATION_RULE_BUNDLE.equals(massField.getValue().toString())) {
                            populateField = "EVALUATION_RULE_BUNDLE";
                            populateValue = String.valueOf(massValue.getValue()).trim();
                        }else if ( RsUtils.CALCULATION_RULE_BUNDLE.equals(massField.getValue().toString())) {
                            populateField = "CALCULATION_RULE_BUNDLE";
                            populateValue = String.valueOf(massValue.getValue()).trim();
                        }else {
                            populateField = StringUtils.EMPTY;
                            populateValue = StringUtils.EMPTY;
                        }
                        if (RsUtils.REBATE_AMOUNT.equals(String.valueOf(massField.getValue())) && massValue.getValue() != null) {
                            massValue.setValidationVisible(true);
                            massValue.addValidator(new StringLengthValidator(" Rebate Amount should be less than 38 Characters", 0, 38, true));
                            massValue.addValidator(new RegexpValidator(ValidationUtils.REBATE_SCHEDULE_AMT, ValidationUtils.NUM_VALID_MSG));
                        }
                    if(!tempflag ){
                        rebateLogic.saveToTemp(itemResultBean.getItemIds(), false);
                        if (populate) {
                            itemResultBean.removeAllItems(); 
                            if (!fieldMass.equals(ConstantsUtils.REBATE_PLAN_NO_LAB) && !fieldMass.equals("Formula No") && !fieldMass.equals(RsUtils.NET_SALES_FORMULA)) {
                                rebateLogic.populateLogic(populateField, populateValue, false, null, infoTab.getRebatePlanLevelCaption());
                            }
                            refreshTable();
                            itemResultBean.removeAllItems();
                           
                        }
                    }
                    }
                    massDDLB.setValue(null);
                    massDate.setValue(null);
                    LOGGER.info("Ending Rebate Schedule Populate operation in Add");
                } catch (Exception exception) {

                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1002), new MessageBoxListener() {  
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
                    LOGGER.error(exception);

                }
            }
        });

    }

    /**
     * Method to add Populate All button and its listener.
     *
     * @return the button
     */
    public void addAllBtnPopulate() throws Exception {

        btnAllPopulate.setReadOnly(true);
        btnAllPopulate.setErrorHandler(new ErrorHandler() {
            /**
             * Method used for add Populate All button and its listener.
             */
            @SuppressWarnings("PMD")
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.error(event.toString());
            }
        });

        btnAllPopulate.addClickListener(new ClickListener() {
            /**
             * Method used for btnAllPopulate logic
             */
            public void buttonClick(final ClickEvent event) {
                try {
                    LOGGER.info("Entering Rebate Schedule Populate All operation in Add");
                    binder.getErrorDisplay().clearError();
                    boolean massUpdate;

                      boolean tempflag = false;
                    if (massField.getValue() == null || massField.getValue().toString().equals(ConstantsUtils.SELECT_ONE)) {
                        binder.getErrorDisplay().setError("Select Field");
                        massUpdate = false;
                    } else {
                        final String value = String.valueOf(massField.getValue());
                        if (RsUtils.REBATE_AMOUNT.equals(value) && massValue.getValue() != null) {
                            massUpdate = true;
                        } else if (RsUtils.REBATE_PLAN_NAME.equals(value) && massDDLB.getValue() != null) {
                            massUpdate = true;
                        } else if ((LabelUtils.ITEM_REBATE_START_DATE.equals(value) || LabelUtils.ITEM_REBATE_END_DATE.equals(value)) && massDate.getValue() != null || "Bundle No".equals(value) || "RS Status".equals(value)) {
                            massUpdate = true;
                        } else if (value.equals(ConstantsUtils.REBATE_PLAN_NO_LAB) || value.equals("Formula No")) {
                            massUpdate = true;
                        } else if (value.equals("Deduction Calendar No")){
                            massUpdate = massLookup.getValue() != null && StringUtils.isNotBlank(String.valueOf(massLookup.getValue()));
                       } else if (value.equals(RsUtils.NET_SALES_FORMULA)||RsUtils.NET_SALES_RULE.equals(massField.getValue().toString()) || RsUtils.EVALUATION_RULE.equals(massField.getValue().toString()) || RsUtils.CALCULATION_RULE.equals(massField.getValue().toString())){
                            massUpdate = massLookup.getValue() != null && StringUtils.isNotBlank(String.valueOf(massLookup.getValue()));
                        }
                        else if (RsUtils.EVALUATION_RULE_BUNDLE.equals(massField.getValue().toString()) || RsUtils.CALCULATION_RULE_BUNDLE.equals(massField.getValue().toString())) {
                            massUpdate = true;
                        }
                       else {
                            massUpdate = false;
                        }

                    }

                    if (massUpdate) {
                        LOGGER.info("Entering  Populate all Mass update operation");
                        rebateLogic.saveToTemp(itemResultBean.getItemIds(), false);
                        populate = true;
                        final String populateField;
                        final String populateValue;
                        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                        fieldMass = map.get(massField.getValue().toString()) == null ? massField.getValue().toString() : map.get(massField.getValue().toString());
                        if (fieldMass.equals(ConstantsUtils.START_DATE)) {
                            populateField = "ITEM_REBATE_START_DATE";
                            populateValue = fmt.format(massDate.getValue());
                        } else if (fieldMass.equals(ConstantsUtils.END_DATE)) {
                            populateField = "ITEM_REBATE_END_DATE";
                            populateValue = fmt.format(massDate.getValue());
                        } else if (fieldMass.equals("Bundle No")) {
                            populateField = "RS_DETAILS_BUNDLE_NO";
                            populateValue = massValue.getValue();
                        } else if (fieldMass.equals("RS Status")) {
                            populateField = "RS_DETAILS_ATTACHED_STATUS";
                            populateValue = String.valueOf(((HelperDTO) massDDLB.getValue()).getId());
                        } else if (fieldMass.equals(ConstantsUtils.REBATE_PLAN_NO_LAB) || fieldMass.equals("Formula No")) {
                            populateField = StringUtils.EMPTY;
                            populateValue = StringUtils.EMPTY;
                            populate = false;
                            rebateLogic.massPopulateForLookUp(fieldMass, massLookup.getData(),false);
                            itemResultBean.removeAllItems();
                            refreshTable();
                        } else if (fieldMass.equals(ConstantsUtils.REBATE_AMOUNT)) {
                            Boolean saveFlag = rebateLogic.rebateSaveValidation("RSDetailsRebatePlanName");
                            if (saveFlag) {
                                binder.getErrorDisplay().setError("The Rebate Plan Name is already selected for items. You should deselect the Rebate Plan Name to populate Rebate Plan Amount.");
                                return;
                            }
                            populateField = "RS_DETAILS_REBATE_AMOUNT";
                            populateValue = String.valueOf(massValue.getValue()).trim();
                        } else if ("Deduction Calendar No".equals(fieldMass) ) {
                            populateField = "DEDUCTION_CALENDAR_MASTER_SID";
                            Map<String,String> deductionMap = (Map)  massLookup.getData();  
                            populateValue = deductionMap.get("deductionSystemId");
                            rebateLogic.massPopulateDeductionLookUp(fieldMass, deductionMap,false);
                            massLookup.setValue(StringUtils.EMPTY);
                            tempflag=true;
                            itemResultBean.removeAllItems();
                            refreshTable();
                        }else if (RsUtils.NET_SALES_FORMULA.equals(fieldMass) ) {
                            Map<String,String> netSalesMap = (Map)  massLookup.getData();  
                            rebateLogic.massPopulateNetSalesLookUp(fieldMass, netSalesMap,false);
                            populateField = "NET_SALES_FORMULA_MASTER_SID";
                            populateValue = map.get(ConstantsUtils.SYS_ID);
                            massLookup.setValue(StringUtils.EMPTY);
                            tempflag=true;
                            itemResultBean.removeAllItems();
                            refreshTable();
                        }else if (RsUtils.NET_SALES_RULE.equals(massField.getValue().toString()) || RsUtils.EVALUATION_RULE.equals(massField.getValue().toString()) || RsUtils.CALCULATION_RULE.equals(massField.getValue().toString()) ) {
                            Map<String,String> rulemap = (Map)  massLookup.getData();  
                            rebateLogic.massPopulateRuleLookUps(fieldMass, rulemap,false);
                           populateField = StringUtils.EMPTY;
                           populateValue = StringUtils.EMPTY;
                            massLookup.setValue(StringUtils.EMPTY);
                            tempflag=true;
                            itemResultBean.removeAllItems();
                            refreshTable();
                        }else if (RsUtils.EVALUATION_RULE_BUNDLE.equals(massField.getValue().toString()) ) {
                           
                            populateField = "EVALUATION_RULE_BUNDLE";
                            populateValue = String.valueOf(massValue.getValue()).trim();
                        }else if ( RsUtils.CALCULATION_RULE_BUNDLE.equals(massField.getValue().toString())) {
                           
                            populateField = "CALCULATION_RULE_BUNDLE";
                            populateValue = String.valueOf(massValue.getValue()).trim();
                        }else {
                            populateField = StringUtils.EMPTY;
                            populateValue = StringUtils.EMPTY;
                        }
                        if (RsUtils.REBATE_AMOUNT.equals(String.valueOf(massField.getValue())) && massValue.getValue() != null) {
                            massValue.setValidationVisible(true);
                            massValue.addValidator(new StringLengthValidator(" Rebate Amount should be less than 38 Characters", 0, 38, true));
                            massValue.addValidator(new RegexpValidator(ValidationUtils.REBATE_SCHEDULE_AMT, ValidationUtils.NUM_VALID_MSG));
                        }
                        if (!tempflag) {
                            rebateLogic.saveToTemp(itemResultBean.getItemIds(), false);
                       
                        if (populate) {
                            itemResultBean.removeAllItems();
                            rebateLogic.populateLogic(populateField, populateValue, true, "populate", infoTab.getRebatePlanLevelCaption());
                            refreshTable();
                            massDate.setValue(null);
                            massDDLB.setValue(null);
                        }
                        }
                         LOGGER.info("Ending Populate all Mass update operation");
                    }
                    LOGGER.info("Ending Rebate Schedule Populate All operation in Add");
                } catch (Exception exception) {

                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1002), new MessageBoxListener() {  
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
                    LOGGER.error(exception);
                }
            }
        });

    }

    /**
     * This method is called when the OptionGroup Mass check on changed .
     *
     * @param value the value
     */
    protected void massCheckOnChangeEvent(final Object value) throws Exception {

        if (value != null && ConstantsUtils.ENABLE.equals(value.toString())) {

            massField.setEnabled(true);
            if (!"Single Rebate Plan".equals(infoTab.getRebatePlanLevelCaption())) {

                btnPopulate.setEnabled(true);
            }
            btnAllPopulate.setEnabled(true);
        } else if (value != null && ConstantsUtils.DISABLE.equals(value.toString())) {
            massField.setValue(null);
            massField.setEnabled(false);
            massValue.setVisible(false);
            valueLB.setVisible(false);
            massDate.setVisible(false);
            btnPopulate.setEnabled(false);
            btnAllPopulate.setEnabled(false);

            if (itemResultBean != null) {
                final List<ItemDetailsDTO> ifpItemDTOList = itemResultBean.getItemIds();

                for (int i = 0; i < ifpItemDTOList.size(); i++) {
                    final ItemDetailsDTO dto = ifpItemDTOList.get(i);
                    if (dto.getCheckbox()) {
                        dto.setCheckbox(false);
                    }
                    ifpItemList.add(dto);
                }
                if (!ifpItemList.isEmpty()) {
                    itemResultBean.removeAllItems();
                    itemResultBean.addAll(ifpItemList);
                }
            }
        }

    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }

    public void focusMassCheck() {
        massCheck.focus();
        // TODO Auto-generated method stub

    }

    public void setEnabledPopulateBtn(boolean b) {
        // TODO Auto-generated method stub
        btnPopulate.setEnabled(b);

    }

public void setMassCheckValue(String values) {
        massCheck.setValue(values);
    }

    public ExtPagedTable<?> getItemDetailsTable() {
        return itemDetailsTable;
    }

    public TextField getRebateScheduleSystemId() {
        return rebateScheduleSystemId;
    }

    public void refreshTable() {
        rebateSetupTableLogic.clearFilters();
        rebateSetupTableLogic.clearSortByColumns();        
        rebateSetupTableLogic.setCurrentPage(rebateSetupTableLogic.getCurrentPage());
    }

    public OptionGroup getMassCheck() {
        return massCheck;
    }

    private void addResponsiveness(final Map<String, AppPermission> fieldRsHM) throws Exception {
        LOGGER.info("Entering configurePermission");
        try {
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.REBATE_SCHEDULE, "Rebate Setup");
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldRsHM, mode);
        } catch (Exception ex) {
            LOGGER.error(ex);

        }
        LOGGER.info("Ending configurePermission");

    }

    /**
     * Loads the Table columns and Mass Update Fields based on Calculation Type.
     *
     * @param calculationType
     */
    public void loadBasedOnCalculationType(String calculationType) {
        Object[] obj;
        TableResultCustom tableResultCustom;
        configureMassField();
        calculationType = calculationType == null || "null".equals(calculationType) ? "default":calculationType;
        this.calculationType = calculationType;
        switch (calculationType) {
            case RsUtils.CALC_FORMULA:
                obj = RsUtils.REBATE_SETUP_FORMULA;
                tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldRsHM, mode);
                if (tableResultCustom.getObjResult().length == 0) {
                    itemDetailsTable.setVisible(false);
                }
                 itemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
                itemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
                resultsPanel.setWidth("100%");
                massField.addItem("Formula No");
                massField.addItem(RsUtils.NET_SALES_FORMULA);
                massField.addItem(RsUtils.NET_SALES_RULE);
                massField.addItem(RsUtils.EVALUATION_RULE);
                massField.addItem(RsUtils.CALCULATION_RULE);
                massField.addItem(RsUtils.EVALUATION_RULE_BUNDLE);
                massField.addItem(RsUtils.CALCULATION_RULE_BUNDLE);
                break;
            case RsUtils.CALC_REBATE_PLAN:
                obj = RsUtils.REBATE_SETUP_REBATE_PLAN;
                tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldRsHM, mode);
                if (tableResultCustom.getObjResult().length == 0) {
                    itemDetailsTable.setVisible(false);
                }
                itemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
                itemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
                resultsPanel.setWidth("100%");
                massField.addItem("Bundle No");
                massField.addItem(ConstantsUtils.REBATE_PLAN_NO_LAB);
                massField.addItem(RsUtils.NET_SALES_FORMULA);
                massField.addItem(RsUtils.NET_SALES_RULE);
                massField.addItem(RsUtils.EVALUATION_RULE);
                massField.addItem(RsUtils.CALCULATION_RULE);
                massField.addItem(RsUtils.EVALUATION_RULE_BUNDLE);
                massField.addItem(RsUtils.CALCULATION_RULE_BUNDLE);
                break;
            case RsUtils.CALC_DEDUCTION_CALENDAR:
                obj = RsUtils.REBATE_SETUP_DEDUCTION_CALENDAR;
                tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldRsHM, mode);
                if (tableResultCustom.getObjResult().length == 0) {
                    itemDetailsTable.setVisible(false);
                }
                itemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
                itemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
                resultsPanel.setWidth("100%");
                massField.addItem("Deduction Calendar No");
                massField.addItem(RsUtils.EVALUATION_RULE);
                massField.addItem(RsUtils.CALCULATION_RULE);
                massField.addItem(RsUtils.EVALUATION_RULE_BUNDLE);
                massField.addItem(RsUtils.CALCULATION_RULE_BUNDLE);
                break;
            default:
                obj = RsUtils.REBATE_SETUP_DEFAULT;
                tableResultCustom = commonSecurityLogic.getTableColumnsPermission(resultList, obj, fieldRsHM, mode);
                if (tableResultCustom.getObjResult().length == 0) {
                    itemDetailsTable.setVisible(false);
                }
                itemDetailsTable.setVisibleColumns(tableResultCustom.getObjResult());
                itemDetailsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
                resultsPanel.setWidth("75%");
                LOGGER.info("Calculation type is not selected in Information Tab");
                break;
        }
        if (isViewMode) {
            Object object[] = itemDetailsTable.getVisibleColumns();
            List viewList = new ArrayList();
            for (int i = 1; i < object.length; i++) {
                viewList.add(object[i]);
            }
            itemDetailsTable.setVisibleColumns(viewList.toArray());
        }
        itemDetailsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        itemDetailsTable.setFilterFieldVisible(ConstantsUtils.CHECK_BOX, false);

        for (Object object : itemDetailsTable.getVisibleColumns()) {
            if (String.valueOf(object).contains("Date")) {
                itemDetailsTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
            }
        }
    }

    /**
     * Configures the mass update field based on the Calculation Type.
     */
    public void configureMassField() {
        massField.removeAllItems();
        massField.setNullSelectionAllowed(true);
        massField.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        massField.addItem(ConstantsUtils.SELECT_ONE);
        massField.addItem("RS Status");
        massField.addItem("Start Date");
        massField.addItem("End Date");
        massField.select(ConstantsUtils.SELECT_ONE);
        massField.setDescription((String) massField.getValue());
    }

    /**
     * Loads the Item Details Table using table logic class.
     */
    public void loadItemDetailsTable() {
        rebateSetupTableLogic.setSearchData(itemResultBean,String.valueOf(record.getValue()));
        rebateSetupTableLogic.setCurrentPage(1);
    }

    /**
     * Clear the Item Details Table Data in the Rebate Setup Tab
     */
    public void clearRebateSetupData() {
        rebateSetupTableLogic.clearAll();
        itemDetailsTable.removeAllItems();
        this.itemResultBean.removeAllItems();
        loadItemDetailsTable();
    }
    
    /**
     * Reset the RS Details based on user ID and session ID in Add Mode.
     * @param calculationType 
     */
    public void setDefaultOnReset(String calculationType) {
        String userID = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        String sessionID = String.valueOf(sessionDTO.getUiSessionId());
        rebateLogic.updateDetailsOnReset(calculationType, userID, sessionID);
    }

     /**
     * Excel button logic.
     *
     * @param event the event
     */
    @UiHandler("excel")
    public void excelButtonLogic(Button.ClickEvent event) {
        try {
            excelExportLogic();
        } catch (Exception e) {
           LOGGER.error(e);
        }

    }
    
     /**
     * 
     * @throws PortalException
     * @throws SystemException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws Exception 
     */
    public void excelExportLogic() throws PortalException, SystemException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, Exception {
        LOGGER.info("Entering excelExportLogic");
        createWorkSheet();
        LOGGER.info("Ending excelExportLogic");
    }
    
    /**
     * 
     * @throws PortalException
     * @throws SystemException
     * @throws IllegalArgumentException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws Exception 
     */
    private void createWorkSheet() throws PortalException, SystemException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, Exception {
        LOGGER.info("Entering createWorkSheet");
        int recordCount = 0;
        if (!isViewMode) {
            recordCount = (Integer) rebateLogic.getLazyItemDetailsResults(0, 0, null, null, true,String.valueOf(record.getValue()));
        }else{
            recordCount = itemResultBean.size();
        }
        ExcelExportforBB.createWorkSheet(itemDetailsTable.getColumnHeaders(), recordCount, this, getUI(), "Rebate_Setup");
        LOGGER.info("Ending createWorkSheet");
    }
    
  public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws PortalException, SystemException {
        LOGGER.info("Entering createWorkSheetContent");
        final SimpleDateFormat dateFormat = new SimpleDateFormat(ExcelExportUtil.DATE_FORMAT, Locale.getDefault());
        if (itemDetailsTable.size() > 0) {
            ItemDetailsDTO itemDetailsDTO;
            final List<SortByColumn> columns = new ArrayList<>();
            List<ItemDetailsDTO> dtoList;            
            if(isViewMode){
                dtoList = itemResultBean.getItemIds();
            }else{
                dtoList = (List<ItemDetailsDTO>)rebateLogic.getLazyItemDetailsResults(start, end, columns, null,false,String.valueOf(record.getValue()));
            }            
            for (int rowCount = 0; rowCount < dtoList.size(); rowCount++) {

                itemDetailsDTO = (ItemDetailsDTO) dtoList.get(rowCount);
                if (!isViewMode) {
                    printWriter.print(ConstantsUtils.QUOTE + StringUtils.EMPTY + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                }

                printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getItemNo() != null ? itemDetailsDTO.getItemNo() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getItemName() != null ? itemDetailsDTO.getItemName() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getAttachedStatus().getId() != 0 ? itemDetailsDTO.getAttachedStatus() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getStartDate() != null ? dateFormat.format(itemDetailsDTO.getStartDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                

                if (RsUtils.CALC_REBATE_PLAN.equals(this.calculationType)) {
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getEndDate() != null ? dateFormat.format(itemDetailsDTO.getEndDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getBundleNo()!= null ? itemDetailsDTO.getBundleNo() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getRebatePlanNo() != null ? itemDetailsDTO.getRebatePlanNo() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getRebatePlanName() != null ? itemDetailsDTO.getRebatePlanName() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getNetSalesFormulaName()!= null ? itemDetailsDTO.getNetSalesFormulaName(): StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getNetSalesRule()!= null ? itemDetailsDTO.getNetSalesRule(): StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getEvaluationRule()!= null ? itemDetailsDTO.getEvaluationRule() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getEvaluationRuleBundle()!= null ? itemDetailsDTO.getEvaluationRuleBundle() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getCalculationRule()!= null ? itemDetailsDTO.getCalculationRule() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getCalculationRuleBundle()!= null ? itemDetailsDTO.getCalculationRuleBundle() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    if (!isViewMode) {
                    printWriter.println(ConstantsUtils.QUOTE + (itemDetailsDTO.getAttachedDate() != null ? dateFormat.format(itemDetailsDTO.getAttachedDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    }
                    } else if (RsUtils.CALC_FORMULA.equals(this.calculationType)) {
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getEndDate() != null ? dateFormat.format(itemDetailsDTO.getEndDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getFormulaType()!= null ? itemDetailsDTO.getFormulaType() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getFormulaNo()!= null ? itemDetailsDTO.getFormulaNo() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getFormulaName()!= null ? itemDetailsDTO.getFormulaName() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getNetSalesFormulaName()!= null ? itemDetailsDTO.getNetSalesFormulaName(): StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getNetSalesRule()!= null ? itemDetailsDTO.getNetSalesRule(): StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getEvaluationRule()!= null ? itemDetailsDTO.getEvaluationRule() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getEvaluationRuleBundle()!= null ? itemDetailsDTO.getEvaluationRuleBundle() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getCalculationRule()!= null ? itemDetailsDTO.getCalculationRule() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getCalculationRuleBundle()!= null ? itemDetailsDTO.getCalculationRuleBundle() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                     if (!isViewMode) {
                    printWriter.println(ConstantsUtils.QUOTE + (itemDetailsDTO.getAttachedDate() != null ? dateFormat.format(itemDetailsDTO.getAttachedDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    }
                    } else if (RsUtils.CALC_DEDUCTION_CALENDAR.equals(this.calculationType)) {
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getEndDate() != null ? dateFormat.format(itemDetailsDTO.getEndDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getDeductionCalendarNo()!= null ? itemDetailsDTO.getDeductionCalendarNo() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getDeductionCalendarName()!= null ? itemDetailsDTO.getDeductionCalendarName() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getEvaluationRule()!= null ? itemDetailsDTO.getEvaluationRule() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getEvaluationRuleBundle()!= null ? itemDetailsDTO.getEvaluationRuleBundle() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getCalculationRule()!= null ? itemDetailsDTO.getCalculationRule() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    printWriter.print(ConstantsUtils.QUOTE + (itemDetailsDTO.getCalculationRuleBundle()!= null ? itemDetailsDTO.getCalculationRuleBundle() : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    if (!isViewMode) {
                    printWriter.println(ConstantsUtils.QUOTE + (itemDetailsDTO.getAttachedDate() != null ? dateFormat.format(itemDetailsDTO.getAttachedDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);
                    }
                } else {
                    printWriter.println(ConstantsUtils.QUOTE + (itemDetailsDTO.getEndDate() != null ? dateFormat.format(itemDetailsDTO.getEndDate()) : StringUtils.EMPTY) + ConstantsUtils.QUOTE + ConstantsUtils.COMMA);                    
                }

            }
            LOGGER.info("Ending createWorkSheetContent");
        }
    }
     /**
     * 
     * @param obj
     * @return 
     */   
    public ItemDetailsDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof ItemDetailsDTO) {
            targetItem = new BeanItem<ItemDetailsDTO>(
                    (ItemDetailsDTO) obj);
        }
        return (ItemDetailsDTO) targetItem.getBean();
    }
}
