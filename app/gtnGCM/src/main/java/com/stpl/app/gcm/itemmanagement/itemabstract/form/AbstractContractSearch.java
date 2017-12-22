/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.globalchange.fieldfactory.AddItemContractFieldFactory;
import static com.stpl.app.gcm.itemmanagement.add.form.AddContractSelection.loadPriceType;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractContractSearchDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic.AbstractContractSelectionTableLogic;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.index.form.ItemManagementLookup;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.FormulaDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.security.SecurityLogic;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.DISABLE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.ENABLE;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.UIUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar
 */
public abstract class AbstractContractSearch extends CustomComponent {

    @UiField("contractNo")
    protected TextField contractNo;
    @UiField("contractName")
    protected TextField contractName;
    @UiField(Constants.CONTRACT_HOLDER)
    protected TextField contractHolder;
    @UiField("marketType")
    protected ComboBox marketType_DTO;
    @UiField("companyFamilyPlanNo")
    protected CustomTextField cfpNO;
    @UiField("itemFamilyPlanNo")
    protected CustomTextField ifpNo;
    @UiField("priceScheduleNo")
    protected CustomTextField psNo;
    @UiField("massUpdateText")
    protected CustomTextField massUpdateText;
    @UiField("allItems")
    public ComboBox allItems;
    @UiField("rebateScheduleId")
    protected TextField rebateScheduleId;
    @UiField("rebateScheduleName")
    protected TextField rebateScheduleName;
    @UiField("rebateScheduleType")
    protected ComboBox rebateScheduleType_DTO;
    @UiField("rarCategory")
    protected ComboBox rarCategory_DTO;
    @UiField("rebateScheduleNo")
    protected TextField rebateScheduleNo;
    @UiField("rebateScheduleCategory")
    protected ComboBox rebateScheduleCategory_DTO;
    @UiField("rebateProgramType")
    protected ComboBox rebateProgramType_DTO;
    @UiField("rebateScheduleAlias")
    protected TextField rebateScheduleAlias;
    @UiField("reset1")
    protected Button reset1;
    @UiField("search")
    protected Button search;
    @UiField("MassUpdatePanel1")
    public Panel MassUpdatePanel1;
    @UiField("field")
    protected ComboBox field;
    @UiField("massUpdateValue")
    protected ComboBox massUpdateValue;
    @UiField("populateBtn")
    protected Button populateBtn;
    @UiField("Available Contracts")
    protected Panel AvailableContracts;
    @UiField("reset2")
    protected Button reset2;
    @UiField("submit")
    protected Button submit;
    @UiField("contractVertical")
    public VerticalLayout contractVertical;
    @UiField("massStartDate")
    public PopupDateField massStartDate;
    @UiField("massEndDate")
    public PopupDateField massEndDate;
    @UiField("massUpdateRadio")
    public OptionGroup massUpdateRadio;
    @UiField("allItemsButton")
    protected Button allItemsButton;
    @UiField("export")
    public Button export;
    @UiField("startdatelabel")
    public Label startdatelabel;
    @UiField("enddatelabel")
    public Label enddatelabel;
    @UiField("valuelabel")
    public Label valuelabel;
    @UiField("search")
    protected Button searchBtn;
    @UiField("reset1")
    protected Button resetBtn;
    @UiField("reset2")
    protected Button resetBtncur;
    @UiField("submit")
    protected Button submitBtncur;
    
    @UiField("baseWacPriceType")
    private ComboBox baseWacPriceType;
    @UiField("baseWacManual")
    protected TextField baseWacManual;
     @UiField("baseWacDate")
    public PopupDateField baseWacDate;
   
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    public AbstractContractSearchDTO binderDto = new AbstractContractSearchDTO();
    public CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<>(binderDto));
    public static final Logger LOGGER = Logger.getLogger(AbstractContractSearch.class);
    public AbstractContractSelectionTableLogic contractSelectionTableLogic = new AbstractContractSelectionTableLogic();
    public static final String SEARCHICON = "searchicon";
    public ExtPagedTable contractSelectionTable = new ExtPagedTable(contractSelectionTableLogic);
    
    Object[] CONTRACT_SELECTION_VISIBLE_COLUMN = {Constants.CHECK_RECORD, "projectionIdLink", "workFlowStatus", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO,
        Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, Constants.STATUS_S, "itemStartDate",
        "itemEndDate", StringConstantsUtil.CP_START_DATE, StringConstantsUtil.CP_END_DATE, Constants.PRICE_TYPE_PROPERTY,
        StringConstantsUtil.PRICE_PROPERTY, Constants.PRICE_PROTECTION_STATUS_PROPERTY, Constants.PRICE_PROTECTION_START_DATE_PROPERTY, Constants.PRICE_PROTECTION_END_DATE_PROPERTY,
        Constants.MEASUREMENT_PRICE_PROPERTY, Constants.NEP_PROPERTY, Constants.NEP_FORMULA_PROPERTY, Constants.BASE_PRICE_PROPERTY,Constants.BASELINE_WAC_PROPERTY,
        Constants.BASELINE_NET_WAC_PROPERTY, Constants.NET_BASELINE_WAC_FORMULA_PROPERTY, Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY, Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY, "netSubsequentPeriodPriceFormula",
        Constants.PRICE_TOLERANCE_INTERVAL, Constants.PRICE_TOLERANCE_FREQUENCY_PROPERTY, Constants.PRICE_TOLERANCE_TYPE_PROPERTY, Constants.PRICE_TOLERANCE_PROPERTY,
        Constants.MAX_INCREMENTAL_CHANGE_PROPERTY, Constants.RESET_ELIGIBLE_PROPERTY, Constants.RESET_TYPE_PROPERTY, Constants.RESET_DATE_PROPERTY, Constants.RESET_INTERVAL_PROPERTY, Constants.RESET_FREQUENCY_PROPERTY,
        Constants.RESET_PRICE_TYPE_PROPERTY, Constants.NET_RESET_PRICE_TYPE_PROPERTY, Constants.NET_RESET_PRICE_FORMULA_PROPERTY, Constants.NET_PRICE_TYPE_PROPERTY, Constants.NET_PRICE_TYPE_FORMULA_PROPERTY,
        "cfpNO", Constants.CFP_NAME, "ifpNo", Constants.IFPNAME, "psNo", Constants.PSNAME, "rsNo", Constants.RSNAME, "rarCategory"};

    String[] CONTRACT_SELECTION_HEADER = {StringUtils.EMPTY, Constants.PROJECTION_ID_HEADER, Constants.WORK_FLOW_STATUS_HEADER, Constants.CONTRACT_HOLDER_HEADER, Constants.CONTRACT_NO_HEADER,
        Constants.CONTRACT_NAME_HEADER, Constants.MARKET_TYPE_HEADER, Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, Constants.STATUS_FIELD, Constants.ITEM_START_DATE,
        Constants.ITEM_END_DATE, StringConstantsUtil.CP_START_DATE_LABEL, StringConstantsUtil.CP_END_DATE_LABEL, Constants.PRICE_TYPE_LABEL,
        StringConstantsUtil.PRICE_LABEL, Constants.PRICE_PROTECTION_STATUS_LABEL, Constants.PRICE_PROTECTION_START_DATE_LABEL, Constants.PRICE_PROTECTION_END_DATE_LABEL,
        Constants.MEASUREMENT_PRICE_LABLE_NAME, Constants.NEP_LABLE_NAME, Constants.NEP_FORMULA_LABLE_NAME,  Constants.BASE_PRICE_TYPE_LABLE_NAME ,Constants.BASELINE_WAC_LABLE_NAME,
        Constants.BASELINE_NET_WAC_LABLE_NAME, Constants.NET_BASELINE_WAC_FORMULA_LABLE_NAME, Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_LABLE_NAME,Constants.NET_SUBSEQUENT_PERIOD_PRICE_LABLE_NAME, Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME,
        Constants.PRICE_TOLERANCE_INTERVAL_LABEL, Constants.PRICE_TOLERANCE_FREQUENCY_LABEL, Constants.PRICE_TOLERANCE_TYPE_LABEL, Constants.PRICE_TOLERANCE_LABEL,
        Constants.MAX_INCREMENTAL_CHANGE_LABLE_NAME,  Constants.RESET_ELIGIBLE_LABLE_NAME, Constants.RESET_TYPE_LABLE_NAME, Constants.RESET_DATE_LABLE_NAME, Constants.RESET_INTERVAL_LABLE_NAME, Constants.RESET_FREQUENCY_LABLE_NAME,
        Constants.RESET_PRICE_TYPE_LABLE_NAME, Constants.NET_RESET_PRICE_TYPE_LABLE_NAME, Constants.NET_RESET_PRICE_FORMULA_LABLE_NAME,  Constants.NET_PRICE_TYPE_LABLE_NAME,  Constants.NET_PRICE_TYPE_FORMULA_LABLE_NAME,
        Constants.CFP_NO_HEADER, Constants.CFP_NAME_HEADER, Constants.IFP_NO, Constants.IFP_NAME_LABEL, Constants.PS_NO_LABEL, Constants.PS_NAME_LABEL, Constants.RS_NO_HEADER, Constants.RS_NAME_LABEL, Constants.RAR_CATEGORY_HEADER};
    
    Object[] EXCEL_CONTRACT_SELECTION_VISIBLE_COLUMN = {ConstantsUtil.PROJECTION_ID, "workFlowStatus", 
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE,
        Constants.END_DATE, ConstantsUtil.STATUS, ConstantsUtil.ITEM_START_DATE, ConstantsUtil.ITEM_END_DATE, StringConstantsUtil.CP_START_DATE, 
        StringConstantsUtil.CP_END_DATE, StringConstantsUtil.CONTRACT_PRICE_PROPERTY, StringConstantsUtil.PRICE_PROPERTY, Constants.PRICE_PROTECTION_START_DATE_PROPERTY, Constants.PRICE_PROTECTION_END_DATE_PROPERTY, Constants.PRICE_TOLERANCE_TYPE_PROPERTY,
        Constants.PRICE_TOLERANCE_PROPERTY, Constants.PRICE_TOLERANCE_FREQUENCY_PROPERTY, Constants.PRICE_TOLERANCE_INTERVAL, StringConstantsUtil.BASE_PRICE_PROPERTY, StringConstantsUtil.RS_START_DATE_LABEL, StringConstantsUtil.RS_END_DATE_COLUMN, 
        Constants.FORMULA_ID_PROPERTY, Constants.REBATE_PLAN_PROPERTY, StringConstantsUtil.FORMULA_METHOD_ID_PROPERTY, Constants.REBATE_AMOUNT_PROPERTY, "cfpNO", Constants.CFP_NAME, "ifpNo", Constants.IFPNAME,
        "psNo", Constants.PSNAME, "rsNo", Constants.RSNAME, "rarCategory"};
    String[] EXCEL_CONTRACT_SELECTION_HEADER = {"Projection ID", "WorkFlow Status", "Contract Holder", "Contract No", "Contract Name", 
        "Market Type", Constants.START_DATE_HEADER, Constants.END_DATE_HEADER, Constants.STATUS_FIELD, Constants.ITEM_START_DATE, Constants.ITEM_END_DATE,
        StringConstantsUtil.CP_START_DATE_LABEL, StringConstantsUtil.CP_END_DATE_LABEL, StringConstantsUtil.CONTRACT_PRICE_LABEL, StringConstantsUtil.PRICE_LABEL, Constants.PRICE_PROTECTION_START_DATE_LABEL, Constants.PRICE_PROTECTION_END_DATE_LABEL, Constants.PRICE_TOLERANCE_TYPE_LABEL,
        Constants.PRICE_TOLERANCE_LABEL, Constants.PRICE_TOLERANCE_FREQUENCY_LABEL, Constants.PRICE_TOLERANCE_INTERVAL_LABEL, StringConstantsUtil.BASE_PRICE_LABEL, StringConstantsUtil.RS_START_DATE_LABEL_CAPS, StringConstantsUtil.RS_END_DATE_LABEL, Constants.FORMULA_ID_LABEL, 
        Constants.REBATE_PLAN_LABEL, "Formula Method Id", Constants.REBATE_AMOUNT_LABEL, "CFP No", "CFP Name", Constants.IFP_NO, Constants.IFP_NAME_LABEL, "PS No", " PS Name",
        "RS No", "RS Name", "RAR Category"};
    SelectionDTO selection;
    public List<ItemIndexDto> selectedItemList;
    public AbstractLogic logic = AbstractLogic.getInstance();
    public BeanItemContainer<AbstractContractSearchDTO> contractExcelResultBean = new BeanItemContainer<>(AbstractContractSearchDTO.class);
    public ExtCustomTable contractExcelTable;
    public VerticalLayout contractDashboardLay = new VerticalLayout();
    String massUpdateString = StringUtils.EMPTY;
    String tabOperation = StringUtils.EMPTY;
    public StplSecurity stplSecurity = new StplSecurity();
    public Map<String, AppPermission> functionHM = new HashMap<>();
    public String userId = VaadinSession.getCurrent().getAttribute(Constants.USER_ID).toString();
    boolean isChecked = true;
    boolean isFound = false;
    public String contractItemName = StringUtils.EMPTY;
    public String transferSalesString = StringUtils.EMPTY;
    public boolean removeProjectionBooleanVal = false;
    public boolean isSubmit = false;
    AddItemContractFieldFactory fieldFactory;
    Map comboToTableMap = new HashMap();
    Map tempTableMap = new HashMap();
    Map startDateEndDateMap = new HashMap();
    Map fieldAndPropertyMap = new HashMap();
    CustomTextField.ClickListener clickLister;
    public String dateMessage = StringUtils.EMPTY;
    BeanItemContainer<AbstractContractSearchDTO> container = new BeanItemContainer<>(AbstractContractSearchDTO.class);

    public AbstractContractSearch(SelectionDTO selection, List selectedItemList) {
        this.selection = selection;
        this.selectedItemList = selectedItemList;
    }

    private void configurefields() {
        export.setPrimaryStyleName("link");
        export.setIcon(excelExportImage, "Excel Export");
        configureAllitems();
        getMassUpdate();
        createFieldFactory();

    }

    public Component getContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/item/ItemContractSearch.xml"), this));
        contractNo.focus();
        Panel panel = new Panel();
        panel.setContent(layout);
        setCompositionRoot(layout);
        configurefields();
        return panel;
    }

    public Component ConfigureTable() {

        contractSelectionTableLogic.setContainerDataSource(container);
        setVisibleColumns();
        contractSelectionTable.addStyleName(ConstantsUtil.FILTERCOMBOBOX);
        contractSelectionTable.setPageLength(NumericConstants.FIVE);
        contractSelectionTableLogic.sinkItemPerPageWithPageLength(false);
        for (Object object : contractSelectionTable.getVisibleColumns()) {
            if (!String.valueOf(object).contains(ConstantsUtil.CHECK_RECORD)) {
                contractSelectionTable.setColumnWidth(object, NumericConstants.ONE_SEVEN_ZERO);
            }

            if (String.valueOf(object).contains("Date")) {
                contractSelectionTable.setColumnAlignment(object, ExtCustomTable.Align.CENTER);
                contractSelectionTable.setConverter(object, new DateToStringConverter());
            } else {
                contractSelectionTable.setColumnAlignment(object, ExtCustomTable.Align.LEFT);
            }
        }

        contractSelectionTable.setFilterGenerator(new ExtFilterGenerator() {
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        Object id=originatingField.getValue();
                        if(originatingField.getValue() instanceof HelperDTO){
                            HelperDTO dto = (HelperDTO) originatingField.getValue();
                            id=dto.getId();
                        }

                        return new SimpleStringFilter(propertyId, String.valueOf(id), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }

            public void filterRemoved(Object propertyId) {
                return;
            }

            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                return;
            }

            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if (Constants.CHECK_RECORD.equals(propertyId)) {
                    CustomTextField text = new CustomTextField();
                    text.setEnabled(false);
                    return text;
                }
                if (Constants.MARKET_TYPE.equals(propertyId)) {
                    ComboBox marketTypeDdlb = new ComboBox();
                    logic.LazyLoadDdlb(marketTypeDdlb, "Load Market Type Count", "Load Market Type", true);
                    return marketTypeDdlb;
                }
                if ("status".equals(propertyId)) {
                    ComboBox statusDdlb = new ComboBox();
                    logic.LazyLoadDdlb(statusDdlb, "Load Item Status Count", "Load Item Status", true);
                    return statusDdlb;
                }
                if (Constants.PRICE_TOLERANCE_INTERVAL.equals(propertyId)) {
                    ComboBox pricetolerenceintDdlb = new ComboBox();
                    logic.LazyLoadDdlb(pricetolerenceintDdlb, "Load PS_INTERVAL Count", "Load PS_INTERVAL", true);
                    return pricetolerenceintDdlb;
                }
                if (Constants.PRICE_TOLERANCE_TYPE_PROPERTY.equals(propertyId)) {
                    ComboBox pricetolerencetypeDdlb = new ComboBox();
                    try {
                        logic.loadComboBox(pricetolerencetypeDdlb, "PS_TYPE", true);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                    return pricetolerencetypeDdlb;
                }
                if (Constants.PRICE_TOLERANCE_FREQUENCY_PROPERTY.equals(propertyId)) {
                    ComboBox pricetolerencefreqDdlb = new ComboBox();
                    logic.LazyLoadDdlb(pricetolerencefreqDdlb, "Load PS_FREQ Count", "Load PS_FREQ", true);
                    return pricetolerencefreqDdlb;
                }
                
                if (Constants.PRICE_PROTECTION_STATUS_PROPERTY.equals(propertyId)) {
                    ComboBox priceProtectionDdlb = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(priceProtectionDdlb, UIUtils.STATUS, true);
                    return priceProtectionDdlb;
                }
                if (Constants.BASE_PRICE_PROPERTY.equals(propertyId)) {
                    ComboBox basePriceType = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(basePriceType, Constants.BASE_PRICE_TYPE_LISTNAME, true);
                    return basePriceType;
                }
                if (Constants.PRICE_TYPE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    loadPriceType(comboBox, true);
                    return comboBox;
                }
                if (Constants.MEASUREMENT_PRICE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    loadPriceType(comboBox, true);
                    return comboBox;
                }
                if (Constants.RESET_PRICE_TYPE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    loadPriceType(comboBox, true);
                    return comboBox;
                }
                if (Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    loadPriceType(comboBox, true);
                    return comboBox;
                }
                if (Constants.RESET_ELIGIBLE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                    return comboBox;
                }
                if (Constants.RESET_TYPE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, Constants.RESET_TYPE_LISTNAME, true);
                    return comboBox;
                }
                if (Constants.RESET_INTERVAL_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, StringConstantsUtil.PRICE_TOLERANCE_INTERVAL_LABEL, true);
                    return comboBox;
                }
                if (Constants.RESET_FREQUENCY_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY_LABEL, true);
                    return comboBox;
                }
                if (Constants.NET_RESET_PRICE_TYPE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                    return comboBox;
                }
                if (Constants.NET_PRICE_TYPE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                    return comboBox;
                }
                if (Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                    return comboBox;
                }
                if (Constants.BASELINE_NET_WAC_PROPERTY.equals(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    CommonUtil.loadComboBoxForGCM(comboBox, Constants.LOCKED_STATUS_LISTNAME, true);
                    return comboBox;
                }
                
                return null;
            }
        });
        contractSelectionTable.setFilterBarVisible(true);
        contractSelectionTable.setFilterDecorator(new ExtDemoFilterDecorator());
        contractSelectionTable.setSelectable(Boolean.TRUE);
        contractVertical.addComponent(contractSelectionTable);
        HorizontalLayout controls = contractSelectionTableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        contractVertical.addComponent(controlLayout);
        contractSelectionTable.setEditable(Boolean.TRUE);
        contractSelectionTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = contractSelectionTable.getItemIds();
                for (Object obj : itemList) {
                    AbstractContractSearchDTO dto = (AbstractContractSearchDTO) obj;
                    dto.setCheckRecord(event.isChecked());
                    contractSelectionTable.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                    logic.checkAllInsert(event.isChecked(), selection);
                }
            }
        });
        contractSelectionTable.setFilterFieldVisible(ConstantsUtil.CHECK_RECORD, false);
        return contractSelectionTable;
    }

    public void getMassUpdate() {
        massUpdateRadio.addItem(DISABLE.getConstant());
        massUpdateRadio.addItem(ENABLE.getConstant());
        massUpdateRadio.select(DISABLE.getConstant());
        field.addItem(Constants.SELECT_ONE);
        LoadField();
        field.setNullSelectionAllowed(true);
        field.setNullSelectionItemId(Constants.SELECT_ONE);
        field.select(Constants.SELECT_ONE);
        massUpdateRadio.select(DISABLE.getConstant());
        massUpdateValue.setReadOnly(true);
        massUpdateValue.setVisible(Boolean.FALSE);
        massStartDate.setReadOnly(true);
        massEndDate.setReadOnly(true);
        massStartDate.setVisible(Boolean.FALSE);
        massEndDate.setVisible(Boolean.FALSE);
        populateBtn.setEnabled(false);
        massUpdateRadio.addItems(ENABLE.getConstant(), DISABLE.getConstant());
        visibilityOptions();
        LoadComboToTableMap();
        LoadTempToTableMap();
        loadFieldAndPropertyMap();
        loadStartEndDateMap();

    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {
        binderDto.setIsCount(true);
        List visibleList = Arrays.asList(contractSelectionTable.getVisibleColumns()).subList(1, contractSelectionTable.getVisibleColumns().length);
        try {
            if (end != 0) {
                List input = getInput();
                input = getNewInput(input);
                selection.setFilters(contractSelectionTableLogic.getFilters());
                final List<AbstractContractSearchDTO> searchList = logic.getContractResults(selection, start, end, input);
                ExcelExportforBB.createFileContent(visibleList.toArray(), searchList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void createWorkSheet(String moduleName) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException,  InvocationTargetException {
        long recordCount = 0;
        List<String> visibleList = Arrays.asList(contractSelectionTable.getColumnHeaders()).subList(1, contractSelectionTable.getVisibleColumns().length);
        selection.setFilters(contractSelectionTableLogic.getFilters());
        if (selection.isReset()) {
            List input = getInput();
            input = getNewInput(input);
            binderDto.setIsCount(false);
            recordCount = logic.getContractCount(selection, input);
            ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.replace(" ", "_").toUpperCase());
        }
    }

    @UiHandler("export")
    public void excelExport(Button.ClickEvent event) {

        try {
            if (container.size() > 0) {
                createWorkSheet("Contract_Details");
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @UiHandler("field")
    public void fieldTypeLogic(Property.ValueChangeEvent event) {
        String processName = String.valueOf(field.getValue()); 
        massUpdateString = processName;
        massUpdateText.setReadOnly(false);
        massUpdateText.setValue(StringUtils.EMPTY);

        if (null != processName) {
            switch (processName) {
                case Constants.PRICE_TYPE_LABEL:
                case Constants.MEASUREMENT_PRICE_LABLE_NAME:
                case Constants.RESET_PRICE_TYPE_LABLE_NAME:
                case Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_LABLE_NAME:
                    loadValueddlbField();
                    loadPriceType(massUpdateValue, false);
                    break;
                case Constants.NET_PRICE_TYPE_LABLE_NAME:
                case Constants.NET_RESET_PRICE_TYPE_LABLE_NAME:
                case Constants.RESET_ELIGIBLE_LABLE_NAME:
                case Constants.NET_SUBSEQUENT_PERIOD_PRICE_LABLE_NAME:
                case Constants.BASELINE_NET_WAC_LABLE_NAME:
                    loadValueddlbField();
                    CommonUtil.loadComboBoxForGCM(massUpdateValue, Constants.LOCKED_STATUS_LISTNAME, false);
                    break;
                case StringConstantsUtil.PRICE_LABEL:
                case Constants.NEP_LABLE_NAME:
                case Constants.PRICE_TOLERANCE_LABEL:
                case Constants.MAX_INCREMENTAL_CHANGE_LABLE_NAME:
                    loadValueddlbTextField();
                    massUpdateText.removeStyleName(SEARCHICON);
                    massUpdateText.removeClickListener(clickLister);
                    break;
                case Constants.ITEM_START_DATE:
                case Constants.ITEM_END_DATE:
                case StringConstantsUtil.CP_START_DATE_LABEL:
                case StringConstantsUtil.CP_END_DATE_LABEL:
                case Constants.PRICE_PROTECTION_START_DATE_LABEL:
                case Constants.PRICE_PROTECTION_END_DATE_LABEL:
                case Constants.RESET_DATE_LABLE_NAME:
                    loadValueddlbDateField(processName);
                    break;
                case Constants.STATUS_FIELD:
                case Constants.PRICE_PROTECTION_STATUS_LABEL:
                    loadValueddlbField();
                    loadStatus();
                    break;
                case Constants.RESET_FREQUENCY_LABLE_NAME:
                case Constants.PRICE_TOLERANCE_FREQUENCY_LABEL:
                    loadValueddlbField();
                    loadPriceToleranceFrequency();
                    break;
                case Constants.PRICE_TOLERANCE_INTERVAL_LABEL:
                case Constants.RESET_INTERVAL_LABLE_NAME:
                    loadValueddlbField();
                    loadPriceToleranceInterval();
                    break;
                case Constants.BASE_PRICE_TYPE_LABLE_NAME:
                    loadValueddlbField();
                    CommonUtil.loadComboBoxForGCM(massUpdateValue, Constants.BASE_PRICE_TYPE_LISTNAME, false);
                    break;
                case Constants.PRICE_TOLERANCE_TYPE_LABEL:
                    loadValueddlbField();
                    loadPriceTolerenceType();
                    break;
                case Constants.RESET_TYPE_LABLE_NAME:
                    loadValueddlbField();
                    CommonUtil.loadComboBoxForGCM(massUpdateValue, Constants.RESET_TYPE_LISTNAME, false);
                    break;
                case Constants.NEP_FORMULA_LABLE_NAME:
                    loadValueddlbTextField();
                    massUpdateText.addStyleName(SEARCHICON);
                    if (clickLister != null) {
                        massUpdateText.removeClickListener(clickLister);
                    }
                    clickLister = new CustomTextField.ClickListener() {
                        public void click(CustomTextField.ClickEvent event) {
                            NEPLookup formulaLookUp = new NEPLookup(massUpdateText, Constants.NEP_FORMULA_LABLE_NAME);
                            formulaLookUp.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    massUpdateText.setReadOnly(false);
                                    if (massUpdateText.getData() != null) {
                                        FormulaDTO object = (FormulaDTO) massUpdateText.getData();
                                        massUpdateText.setValue(object.getFormulaName());
                                    }
                                    massUpdateText.setReadOnly(true);
                                }
                            });
                            UI.getCurrent().addWindow(formulaLookUp);
                        }
                    };
                    massUpdateText.addClickListener(clickLister);
                    massUpdateText.setReadOnly(true);
                    break;
                case Constants.NET_BASELINE_WAC_FORMULA_LABLE_NAME:
                    loadValueddlbTextField();
                    massUpdateText.addStyleName(SEARCHICON);
                    if (clickLister != null) {
                        massUpdateText.removeClickListener(clickLister);
                    }
                    clickLister = new CustomTextField.ClickListener() {
                        public void click(CustomTextField.ClickEvent event) {
                            NEPLookup formulaLookUp = new NEPLookup(massUpdateText, Constants.NET_BASELINE_WAC_FORMULA_LABLE_NAME);
                            formulaLookUp.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    massUpdateText.setReadOnly(false);
                                    if (massUpdateText.getData() != null) {
                                        FormulaDTO object = (FormulaDTO) massUpdateText.getData();
                                        massUpdateText.setValue(object.getFormulaName());
                                    }
                                    massUpdateText.setReadOnly(true);
                                }
                            });
                            UI.getCurrent().addWindow(formulaLookUp);
                        }
                    };
                    massUpdateText.addClickListener(clickLister);
                    massUpdateText.setReadOnly(true);
                    break;
                case Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME:
                    loadValueddlbTextField();
                    massUpdateText.addStyleName(SEARCHICON);
                    if (clickLister != null) {
                        massUpdateText.removeClickListener(clickLister);
                    }
                    clickLister = new CustomTextField.ClickListener() {
                        public void click(CustomTextField.ClickEvent event) {
                            NEPLookup formulaLookUp = new NEPLookup(massUpdateText, Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME);
                            formulaLookUp.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    massUpdateText.setReadOnly(false);
                                    if (massUpdateText.getData() != null) {
                                        FormulaDTO object = (FormulaDTO) massUpdateText.getData();
                                        massUpdateText.setValue(object.getFormulaName());
                                    }
                                    massUpdateText.setReadOnly(true);
                                }
                            });
                            UI.getCurrent().addWindow(formulaLookUp);
                        }
                    };
                    massUpdateText.addClickListener(clickLister);
                    massUpdateText.setReadOnly(true);
                    break;

                case Constants.NET_RESET_PRICE_FORMULA_LABLE_NAME:
                    loadValueddlbTextField();
                    massUpdateText.addStyleName(SEARCHICON);
                    if (clickLister != null) {
                        massUpdateText.removeClickListener(clickLister);
                    }
                    clickLister = new CustomTextField.ClickListener() {
                        public void click(CustomTextField.ClickEvent event) {
                            NEPLookup formulaLookUp = new NEPLookup(massUpdateText, Constants.NET_RESET_PRICE_FORMULA_LABLE_NAME);
                            formulaLookUp.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    massUpdateText.setReadOnly(false);
                                    if (massUpdateText.getData() != null) {
                                        FormulaDTO object = (FormulaDTO) massUpdateText.getData();
                                        massUpdateText.setValue(object.getFormulaName());
                                    }
                                    massUpdateText.setReadOnly(true);
                                }
                            });
                            UI.getCurrent().addWindow(formulaLookUp);
                        }
                    };
                    massUpdateText.addClickListener(clickLister);
                    massUpdateText.setReadOnly(true);
                    break;
                case Constants.NET_PRICE_TYPE_FORMULA_LABLE_NAME:
                    loadValueddlbTextField();
                    massUpdateText.addStyleName(SEARCHICON);
                    if (clickLister != null) {
                        massUpdateText.removeClickListener(clickLister);
                    }
                    clickLister = new CustomTextField.ClickListener() {
                        public void click(CustomTextField.ClickEvent event) {
                            NEPLookup formulaLookUp = new NEPLookup(massUpdateText, Constants.NET_PRICE_TYPE_LABLE_NAME);
                            formulaLookUp.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    massUpdateText.setReadOnly(false);
                                    if (massUpdateText.getData() != null) {
                                        FormulaDTO object = (FormulaDTO) massUpdateText.getData();
                                        massUpdateText.setValue(object.getFormulaName());
                                    }
                                    massUpdateText.setReadOnly(true);
                                }
                            });
                            UI.getCurrent().addWindow(formulaLookUp);
                        }
                    };
                    massUpdateText.addClickListener(clickLister);
                    massUpdateText.setReadOnly(true);
                    break;
                default:
                    break;
            }
        }
        massStartDate.setValue(null);
        massEndDate.setValue(null);
        massUpdateValue.setValue(null);
        massUpdateText.setValue(StringUtils.EMPTY);
        baseWacPriceType.setValue(null);
        baseWacManual.setValue(StringUtils.EMPTY);
        baseWacDate.setValue(null);
                    }
        
    @UiHandler("allItemsButton")
    public void allItemsButtonLogic(Button.ClickEvent event) {
        AbstractAllItemLookup itemLookup = new AbstractAllItemLookup(selectedItemList);
        UI.getCurrent().addWindow(itemLookup);
    }

    @UiHandler("massUpdateRadio")
    public void massTypeLogic(Property.ValueChangeEvent event) {
        String processName = String.valueOf(massUpdateRadio.getValue());
        if (DISABLE.getConstant().equals(processName)) {
            resetMassUpdate();
            field.setReadOnly(true);
            massStartDate.setReadOnly(true);
            massEndDate.setReadOnly(true);
            massUpdateValue.setReadOnly(true);
            populateBtn.setEnabled(false);
            massUpdateText.setEnabled(false);

        }
        if (ENABLE.getConstant().equals(processName)) {
            field.setReadOnly(false);
            massStartDate.setReadOnly(false);
            massEndDate.setReadOnly(false);
            massUpdateValue.setReadOnly(false);
            populateBtn.setEnabled(true);
            massUpdateText.setEnabled(true);

        }

    }
    @UiHandler("massUpdateValue")
    public void basePriceTypeLogic(Property.ValueChangeEvent event) {
        String processName = String.valueOf(massUpdateValue.getValue());
        if (Constants.BASE_PRICE_TYPE_LABLE_NAME.equals(String.valueOf(field.getValue()))) {
            baseWacDate.setValue(null);
            baseWacPriceType.setValue(null);
            baseWacManual.setValue(StringUtils.EMPTY);
            if (Constants.SELECT_ONE.equals(processName) || Constants.NULL.equals(processName)) {
                baseWacManual.setVisible(false);
                baseWacPriceType.setVisible(false);
                baseWacDate.setVisible(false);
            } else if (Constants.MANUAL_LABLE_NAME.equals(processName)) {
                baseWacManual.setVisible(true);
                baseWacPriceType.setVisible(false);
                baseWacDate.setVisible(false);
            } else if (Constants.DATE_LABLE_NAME.equals(processName)) {
                baseWacDate.setVisible(true);
                baseWacManual.setVisible(false);
                baseWacPriceType.setVisible(false);
            } else if (Constants.PRICE_TYPE_LABEL.equals(processName)) {
                baseWacPriceType.setVisible(true);
                baseWacManual.setVisible(false);
                baseWacDate.setVisible(false);
                loadPriceType(baseWacPriceType, false);
            }
        }
    }

    public CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    public void searchButtonLogic(boolean isSearch) throws FieldGroup.CommitException {
        ItemManagementLookup.waitForSave(selection);
        selection.setReset(true);
        binder.commit();
        List input = getInput();
        resetCheckRecord();
        boolean isRecordAvailable=contractSelectionTableLogic.loadSetData(selection, true, selectedItemList, input);
        if(isSearch){
        if (!isRecordAvailable) {
            AbstractNotificationUtils.getErrorNotification("No Matching Records",
                    "There were no records matching the search criteria.  Please try again.");
        } else  {
            Notification.show("Search Completed");
        }
        }
    }

    public Object getItemIds(List<ItemIndexDto> itemList) {
        List itemIdList = new ArrayList();
        for (ItemIndexDto dto : itemList) {
            itemIdList.add(dto.getSystemId());
        }
        return CommonUtils.getListToString(itemIdList);
    }

    public void createFieldFactory() {
        fieldFactory = new AddItemContractFieldFactory(selection, contractSelectionTable, fieldAndPropertyMap);
        contractSelectionTable.setTableFieldFactory(fieldFactory);
    }

    @UiHandler("reset1")
    public void resetSearchCriteria(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                resetSearchField();
                contractSelectionTable.resetFilters();
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER, "Are you sure you want to reset the values?");

    }

    @UiHandler("reset2")
    public void resetViewButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                selection.setReset(false);
                contractSelectionTableLogic.loadSetData(selection, false, selectedItemList, new ArrayList());
            }

            @Override
            public void noMethod() {
                return;
            }
        }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER, "Are you sure you want to reset the values in the Available Contracts list view?");

    }

    @UiHandler("populateBtn")
    public void populateButton(Button.ClickEvent event) {
        if (!massUpdateString.isEmpty()) {
            if (massUpdateValue.getValue() != null || massStartDate.getValue() != null || massEndDate.getValue() != null || (massUpdateText != null && !massUpdateText.getValue().isEmpty())) {
                if (multipleEndDateCheck(massUpdateString.contains(Constants.ITEM_START_DATE) ? massStartDate.getValue() : massUpdateString.contains(Constants.ITEM_END_DATE) ? massEndDate.getValue() : null)) {
                    isFound = isPresent();
                    if (isFound) {
                        populateLogic();
                    }
                } else {
                    MessageBox.showPlain(Icon.INFO, "Date Conflicts", "You Cannot Proceed with this date since " + dateMessage, ButtonId.OK);
                }
            } else {
                MessageBox.showPlain(Icon.INFO, Constants.ERROR, "Please enter a " + massUpdateString + " to Mass Update. ", ButtonId.OK);

            }
        } else {
            MessageBox.showPlain(Icon.INFO, Constants.ERROR, "Please select a Field to Mass Update. ", ButtonId.OK);

        }
        isFound = false;
    }

    private void resetSearchField() {
        try {
            contractHolder.setValue(StringUtils.EMPTY);
            marketType_DTO.setValue(null);
            cfpNO.setValue(StringUtils.EMPTY);
            contractNo.setValue(StringUtils.EMPTY);
            rebateScheduleCategory_DTO.setValue(null);
            ifpNo.setValue(StringUtils.EMPTY);
            contractName.setValue(StringUtils.EMPTY);
            rebateProgramType_DTO.setValue(null);
            psNo.setValue(StringUtils.EMPTY);
            rebateScheduleNo.setValue(StringUtils.EMPTY);
            rebateScheduleName.setValue(StringUtils.EMPTY);
            rebateScheduleId.setValue(StringUtils.EMPTY);

            rebateScheduleAlias.setValue(StringUtils.EMPTY);

            rebateScheduleType_DTO.setValue(null);
            rarCategory_DTO.setValue(null);

            binder.commit();
        } catch (FieldGroup.CommitException ex) {
            LOGGER.error(ex);
        }
    }

    public Object getDBDate(Object date) {
        return date == null ? null : "'" + CommonUtils.DBDate.format(date) + "'";
    }

    public void loadAllDdlb() {
        loadRsType();
        loadMarketType();
        loadRsCategory();
        loadRptype();
    }

    private void loadRsType() {
        logic.LazyLoadDdlb(rebateScheduleType_DTO, "LoadRsTypeCount", "LoadRsType", false);
    }

    private void loadMarketType() {
        logic.LazyLoadDdlb(marketType_DTO, "Load Market Type Count", "Load Market Type", false);
    }

    private void loadRsCategory() {
        logic.LazyLoadDdlb(rebateScheduleCategory_DTO, "LoadRsCategoryCount", "LoadRsCategory", false);
    }

    private void loadRptype() {
        logic.LazyLoadDdlb(rebateProgramType_DTO, "LoadRpTypeCount", "LoadRpType", false);
    }

    /**
     * Load Alias Type
     */
    private void loadStatus() {
        CommonUtil.getComboBoxByListName(massUpdateValue, UIUtils.STATUS, false);
    }

    private void loadPriceTolerenceType() {
        CommonUtil.getComboBoxByListName(massUpdateValue, StringConstantsUtil.PRICE_TOLERANCE_TYPE_LABEL, false);
    }

    private void loadPriceToleranceFrequency() {
        CommonUtil.getComboBoxByListName(massUpdateValue, StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY_LABEL, false);
    }

    private void loadPriceToleranceInterval() {
        CommonUtil.getComboBoxByListName(massUpdateValue, StringConstantsUtil.PRICE_TOLERANCE_INTERVAL_LABEL, false);
    }

    @UiHandler("submit")
    public void submitButtonLogic(Button.ClickEvent event) {
        submit();
    }

    public boolean submit() {
        List input = getSessionInput(selection);
        if (selectingOneContract("Selecting one contract", input)) {
            if (submitButtonCheck()) {
                if (!nonApprovedSubmitCheck()) {
                    if (singleContractCheck("Single contract check", input)) {
                        new AbstractNotificationUtils() {
                            @Override
                            public void yesMethod() {
                                try {
                                    List input = getResultsInput(selection);
                                    ItemQueries.itemUpdate(input, "Submitting the contract For UPdate");
                                    updateSubmittedContract();
                                    binder.commit();
                                    searchButtonLogic(false);
                                    selection.getLookup().changeTab();
                                    isSubmit = true;
                                } catch (Exception ex) {
                                    LOGGER.error(ex);
                                }
                            }

                            @Override
                            public void noMethod() {
                                isSubmit = false;
                                setIsSubmit(false);
                            }
                        }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER, "Are you sure you want to submit the selected contracts?");
                    } else {
                        AbstractNotificationUtils.getErrorNotification(Constants.ERROR,
                                "Please select one contract to proceed.");
                    }
                } else {
                    AbstractNotificationUtils.getErrorNotification("Workflow Status Issue",
                            "The Selected Contract & Item combination is currently associated to a projection in a ‘Saved’ or ‘Rejected’ Workflow queue."
                            + "You must delete or approve the projection before proceeding with this Update Item process.");
                }
            } else {
                isSubmit = false;
                AbstractNotificationUtils.getErrorNotification("Required Fields Error",
                        "Not all the required fields are filled.  Please try again.");
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("Required Fields Error",
                    "Please select atleast one approved contract to proceed.");
        }
        return isSubmit;
    }

    public Boolean submitButtonCheck() {
        List input = AbstractLogic.getResultsInput(selection);
        List<Object[]> list = ItemQueries.getItemData(input, "Submit condition check for Item Update", null);
        if (AbstractLogic.getCount(list) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean nonApprovedSubmitCheck() {
        List input = AbstractLogic.getResultsInput(selection);
        List<Object[]> list = ItemQueries.getItemData(input, "Non Approved Submit check", null);
        if (AbstractLogic.getCount(list) == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @return
     */
    public List getInput() {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(selection.getButtonMode());
        if (binderDto.getContractNo() != null && !binderDto.getContractNo().isEmpty()) {
            input.add(binderDto.getContractNo().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getContractName() != null && !binderDto.getContractName().isEmpty()) {
            input.add(binderDto.getContractName().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getContractHolder() != null && !binderDto.getContractHolder().isEmpty()) {
            input.add(binderDto.getContractHolder().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getMarketType_DTO() != null) {
            input.add(binderDto.getMarketType_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getCfpNO() != null && !binderDto.getCfpNO().isEmpty()) {
            input.add(binderDto.getCfpNO().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getIfpNo() != null && !binderDto.getIfpNo().isEmpty()) {
            input.add(binderDto.getIfpNo().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getPsNo() != null && !binderDto.getPsNo().isEmpty()) {
            input.add(binderDto.getPsNo().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getRebateScheduleId() != null && !binderDto.getRebateScheduleId().isEmpty()) {
            input.add(binderDto.getRebateScheduleId().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getRebateScheduleName() != null && !binderDto.getRebateScheduleName().isEmpty()) {
            input.add(binderDto.getRebateScheduleName().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getRebateScheduleType_DTO() != null) {
            input.add(binderDto.getRebateScheduleType_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getRebateScheduleCategory_DTO() != null) {
            input.add(binderDto.getRebateScheduleCategory_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getRebateProgramType_DTO() != null) {
            input.add(binderDto.getRebateProgramType_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getRebateScheduleAlias() != null && !binderDto.getRebateScheduleAlias().isEmpty()) {
            input.add(binderDto.getRebateScheduleAlias().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getRebateScheduleNo() != null && !binderDto.getRebateScheduleNo().isEmpty()) {
            input.add(binderDto.getRebateScheduleNo().replace("*", "%"));
        } else {
            input.add("%");
        }
        return input;
    }

    /**
     *
     * @param selection
     * @return
     */
    public List getResultsInput(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(selection.getButtonMode());
        input.add("SUMMARY");
        return input;
    }

    /**
     *
     * @param selection
     * @return
     */
    public List getPopulateDateCheckInput(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(selection.getButtonMode());
        return input;
    }

    public void LoadField() {
        field.addItems(Arrays.asList(ConstantsUtil.MassUpdateConstants.values()));
    }

    private void configureAllitems() {
        allItems.addItems("YES", "NO");
        allItems.select("YES");
        allItems.setNullSelectionAllowed(Boolean.FALSE);
    }

    public Boolean massUpdateItemDetails(final List input, final SelectionDTO selection) {
        input.addAll(getResultsInput(selection));
        Boolean isUpdated = ItemQueries.itemUpdate(input, "Abstract Mass update");
        return isUpdated;
    }

    /**
     * Security
     */
    public void setButtonSecurity() {
        SecurityLogic.isPermitted(functionHM, "reset1", reset1);
        SecurityLogic.isPermitted(functionHM, "search", search);
        SecurityLogic.isPermitted(functionHM, "reset2", reset2);
        SecurityLogic.isPermitted(functionHM, "submit", submit);
        SecurityLogic.isPermitted(functionHM, "export", export);
    }

    public void setVisibleColumns() {
        contractSelectionTable.setVisibleColumns(CONTRACT_SELECTION_VISIBLE_COLUMN);
        contractSelectionTable.setColumnHeaders(CONTRACT_SELECTION_HEADER);
    }

    private void resetMassUpdate() {
        field.setValue(null);
        massStartDate.setValue(null);
        massEndDate.setValue(null);
        massUpdateValue.setValue(null);
    }

    public boolean getBooleanValue(List list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean isPresent() {
        Collection itemId = contractSelectionTable.getItemIds();
        boolean isChecked = true;
        for (Object object : itemId) {
            AbstractContractSearchDTO dto = (AbstractContractSearchDTO) object;
            if (dto.getCheckRecord()) {
                isChecked = false;
                isFound = true;
                if (!isHavingValue(massUpdateString)) {
                    isFound = false;
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {

                            populateLogic();
                        }

                        @Override
                        public void noMethod() {
                            return;
                        }
                    }.getConfirmationMessage(StringConstantsUtil.CONFIRMATION_HEADER, "There are values in these fields already. Are you sure you want to replace them?");
                } else {
                    populateLogic();
                }
                break;

            }
        }
        if (isChecked) {
            MessageBox.showPlain(Icon.INFO, Constants.ERROR, "Please select at least one contract to apply the Mass Update to. ", ButtonId.OK);
        }
        return isFound;

    }

    public void populateLogic() {
        Collection itemId = contractSelectionTable.getItemIds();
        List list = new ArrayList();
        Object value = null;
        String columnName = StringUtils.EMPTY;
        String textValue;
        HelperDTO tempDTO;
        Date tempDdate;
        Object baseLineValue = null;
        String baseLineColumnName = StringUtils.EMPTY;
        String baseLineTextValue;
        for (Object object : itemId) {
            AbstractContractSearchDTO dto = (AbstractContractSearchDTO) object;
            if (dto.getCheckRecord()) {
                switch (massUpdateString) {
                    case Constants.STATUS_FIELD:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.STATUS_S).setValue(tempDTO);
                        columnName = StringConstantsUtil.ITEM_STATUS_COLUMN;
                        value = tempDTO.getId();
                        break;
                    case Constants.ITEM_START_DATE:
                        contractSelectionTable.getItem(object).getItemProperty("itemStartDate").setValue(massStartDate.getValue());
                        columnName = StringConstantsUtil.START_DATE_COLUMN;
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;
                    case Constants.ITEM_END_DATE:
                        tempDdate = dto.getItemStartDate();
                        if (tempDdate != null && massEndDate.getValue().before(tempDdate)) {
                            MessageBox.showPlain(Icon.INFO, Constants.ERROR, StringConstantsUtil.END_DATE_AFTER_START_DATE, ButtonId.OK);
                            break;
                        } else {
                            contractSelectionTable.getItem(object).getItemProperty("itemEndDate").setValue(massEndDate.getValue());
                            columnName = StringConstantsUtil.END_DATE_COLUMN;
                            value = CommonUtils.DBDate.format(massEndDate.getValue());

                        }
                        break;
                    case StringConstantsUtil.CP_START_DATE_LABEL:
                        contractSelectionTable.getItem(object).getItemProperty(StringConstantsUtil.CP_START_DATE).setValue(massStartDate.getValue());
                        columnName = StringConstantsUtil.CONTRACT_PRICE_START_DATE_COLUMN;
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;
                    case StringConstantsUtil.CP_END_DATE_LABEL:
                        tempDdate = dto.getCpStartDate();
                        if (tempDdate != null && massEndDate.getValue().before(tempDdate)) {
                            MessageBox.showPlain(Icon.INFO, Constants.ERROR, StringConstantsUtil.END_DATE_AFTER_START_DATE, ButtonId.OK);
                            break;
                        } else {
                            contractSelectionTable.getItem(object).getItemProperty(StringConstantsUtil.CP_END_DATE).setValue(massEndDate.getValue());
                            columnName = StringConstantsUtil.CONTRACT_PRICE_END_DATE_COLUMN;
                            value = CommonUtils.DBDate.format(massEndDate.getValue());

                        }
                        break;

                    case Constants.PRICE_TYPE_LABEL:
                        value = massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.PRICE_TYPE_PROPERTY).setValue(value);
                        columnName = Constants.PRICE_TYPE_COLUMN_NAME;
                        break;
                    case StringConstantsUtil.PRICE_LABEL:
                        textValue = massUpdateText.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(StringConstantsUtil.PRICE_PROPERTY).setValue(textValue);
                        columnName = StringConstantsUtil.PRICE_COLUMN;
                        value = textValue;
                        break;
                    case Constants.PRICE_PROTECTION_STATUS_LABEL:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.PRICE_PROTECTION_STATUS_PROPERTY).setValue(tempDTO);
                        columnName = Constants.PRICE_PROTECTION_STATUS_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.PRICE_PROTECTION_START_DATE_LABEL:
                        contractSelectionTable.getItem(object).getItemProperty(Constants.PRICE_PROTECTION_START_DATE_PROPERTY).setValue(massStartDate.getValue());
                        columnName = StringConstantsUtil.PRICE_PROTECTION_START_DATE_COLUMN;
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;

                    case Constants.PRICE_PROTECTION_END_DATE_LABEL:
                        tempDdate = dto.getPriceProtectionStartDate();
                        if (tempDdate != null && massEndDate.getValue().before(tempDdate)) {
                            MessageBox.showPlain(Icon.INFO, Constants.ERROR, StringConstantsUtil.END_DATE_AFTER_START_DATE, ButtonId.OK);
                            break;
                        } else {
                            contractSelectionTable.getItem(object).getItemProperty(Constants.PRICE_PROTECTION_END_DATE_PROPERTY).setValue(massEndDate.getValue());
                            columnName = StringConstantsUtil.PRICE_PROTECTION_END_DATE_LABEL;
                            value = CommonUtils.DBDate.format(massEndDate.getValue());

                        }
                        break;
                    case Constants.MEASUREMENT_PRICE_LABLE_NAME:
                        value = massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.MEASUREMENT_PRICE_PROPERTY).setValue(value);
                        columnName = Constants.MEASUREMENT_PRICE_COLUMN_NAME;
                        break;
                    case Constants.NEP_LABLE_NAME:
                        textValue = massUpdateText.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.NEP_PROPERTY).setValue(textValue);
                        columnName = Constants.NEP_COLUMN_NAME;
                        value = textValue;
                        break;
                    case Constants.NEP_FORMULA_LABLE_NAME:
                        FormulaDTO nepForumulaDto = (FormulaDTO) massUpdateText.getData();
                        textValue = String.valueOf(nepForumulaDto.getFormulaNo());
                        contractSelectionTable.getItem(object).getItemProperty(Constants.NEP_FORMULA_PROPERTY).setValue(textValue);
                        columnName = Constants.NEP_FORMULA_COLUMN_NAME;
                        value = nepForumulaDto.getFormulaSid();
                        break;
                        case Constants.BASE_PRICE_TYPE_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.BASE_PRICE_PROPERTY).setValue(tempDTO);
                        columnName = Constants.BASE_PRICE_TYPE_COLUMN_NAME;
                        value = tempDTO.getId();
                            if (Constants.MANUAL_LABLE_NAME.equals(tempDTO.getDescription())) {
                                baseLineTextValue = baseWacManual.getValue();
                                contractSelectionTable.getItem(object).getItemProperty("baseLineWacManual").setValue(baseLineTextValue);
                                baseLineColumnName = Constants.BASELINE_WAC_MANUAL_COLUMN_NAME;
                               logic.updateBaseLineWacColumn(baseLineColumnName, baseLineTextValue, dto, selection);
                            } else if (Constants.DATE_LABLE_NAME.equals(tempDTO.getDescription())) {
                                contractSelectionTable.getItem(object).getItemProperty("baseLineWacDate").setValue(baseWacDate.getValue());
                                baseLineColumnName = Constants.BASELINE_WAC_DATE_COLUMN_NAME;
                                baseLineValue = CommonUtils.DBDate.format(baseWacDate.getValue());
                                logic.updateBaseLineWacColumn(baseLineColumnName, baseLineValue, dto, selection);
                            } else if (Constants.PRICE_TYPE_LABEL.equals(tempDTO.getDescription())) {
                                baseLineValue = baseWacPriceType.getValue();
                                contractSelectionTable.getItem(object).getItemProperty("baseLineWacPriceType").setValue(baseLineValue);
                                baseLineColumnName = Constants.BASELINE_WAC_PRICE_TYPE_COLUMN_NAME;
                                logic.updateBaseLineWacColumn(baseLineColumnName, baseLineValue, dto, selection);
                            }
                        break;
                    case Constants.BASELINE_NET_WAC_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.BASELINE_NET_WAC_PROPERTY).setValue(tempDTO);
                        columnName = Constants.BASELINE_NET_WAC_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.NET_BASELINE_WAC_FORMULA_LABLE_NAME:
                        FormulaDTO netBaselineWACFormulaDto = (FormulaDTO) massUpdateText.getData();
                        textValue = String.valueOf(netBaselineWACFormulaDto.getFormulaNo());
                        contractSelectionTable.getItem(object).getItemProperty(Constants.NET_BASELINE_WAC_FORMULA_PROPERTY).setValue(textValue);
                        columnName = Constants.NET_BASELINE_WAC_FORMULA_COLUMN_NAME;
                        value = netBaselineWACFormulaDto.getFormulaSid();
                        break;
                    case Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_LABLE_NAME:
                        value = massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY).setValue(value);
                        columnName = Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_COLUMN_NAME;
                        break;
                    case Constants.NET_SUBSEQUENT_PERIOD_PRICE_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY).setValue(tempDTO);
                        columnName = Constants.NET_SUBSEQUENT_PERIOD_PRICE_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME:
                        FormulaDTO netSubsequentPeriodPriceFormulaDto = (FormulaDTO) massUpdateText.getData();
                        textValue = String.valueOf(netSubsequentPeriodPriceFormulaDto.getFormulaNo());
                        contractSelectionTable.getItem(object).getItemProperty("netSubsequentPeriodPriceFormula").setValue(textValue);
                        columnName = Constants.NET_SUBSEQUENT_PRICE_FORMULA_COLUMN_NAME;
                        value = netSubsequentPeriodPriceFormulaDto.getFormulaSid();
                        break;
                    case Constants.PRICE_TOLERANCE_TYPE_LABEL:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.PRICE_TOLERANCE_TYPE_PROPERTY).setValue(tempDTO);
                        columnName = StringConstantsUtil.PRICE_TOLERANCE_TYPE_LABEL;
                        value = tempDTO.getId();
                        break;
                    case Constants.PRICE_TOLERANCE_LABEL:
                        textValue = massUpdateText.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.PRICE_TOLERANCE_PROPERTY).setValue(textValue);
                        columnName = StringConstantsUtil.PRICE_TOLERANCE_COLUMN;
                        value = textValue;
                        break;
                    case Constants.PRICE_TOLERANCE_FREQUENCY_LABEL:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.PRICE_TOLERANCE_FREQUENCY_PROPERTY).setValue(tempDTO);
                        columnName = StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY_LABEL;
                        value = tempDTO.getId();
                        break;
                    case Constants.PRICE_TOLERANCE_INTERVAL_LABEL:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.PRICE_TOLERANCE_INTERVAL).setValue(tempDTO);
                        columnName = StringConstantsUtil.PRICE_TOLERANCE_INTERVAL_LABEL;
                        value = tempDTO.getId();
                        break;
                    case Constants.MAX_INCREMENTAL_CHANGE_LABLE_NAME:
                        textValue = massUpdateText.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.MAX_INCREMENTAL_CHANGE_PROPERTY).setValue(textValue);
                        columnName = Constants.MAX_INCREMENTAL_CHANGE_COLUMN_NAME;
                        value = textValue;
                        break;
                    case Constants.RESET_ELIGIBLE_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.RESET_ELIGIBLE_PROPERTY).setValue(tempDTO);
                        columnName = Constants.RESET_ELIGIBLE_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.RESET_TYPE_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.RESET_TYPE_PROPERTY).setValue(tempDTO);
                        columnName = Constants.RESET_TYPE_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.RESET_DATE_LABLE_NAME:
                        contractSelectionTable.getItem(object).getItemProperty(Constants.RESET_DATE_PROPERTY).setValue(massStartDate.getValue());
                        columnName = Constants.RESET_DATE_COLUMN_NAME;
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;
                    case Constants.RESET_INTERVAL_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.RESET_INTERVAL_PROPERTY).setValue(tempDTO);
                        columnName = Constants.RESET_INTERVAL_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.RESET_FREQUENCY_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.RESET_FREQUENCY_PROPERTY).setValue(tempDTO);
                        columnName = Constants.RESET_FREQUENCY_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.RESET_PRICE_TYPE_LABLE_NAME:
                        value = massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.RESET_PRICE_TYPE_PROPERTY).setValue(value);
                        columnName = Constants.RESET_PRICE_TYPE_COLUMN_NAME;
                        break;
                    case Constants.NET_RESET_PRICE_TYPE_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.NET_RESET_PRICE_TYPE_PROPERTY).setValue(tempDTO);
                        columnName = Constants.NET_RESET_PRICE_TYPE_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.NET_RESET_PRICE_FORMULA_LABLE_NAME:
                        FormulaDTO netResetPriceFormulaDto = (FormulaDTO) massUpdateText.getData();
                        textValue = String.valueOf(netResetPriceFormulaDto.getFormulaNo());
                        contractSelectionTable.getItem(object).getItemProperty(Constants.NET_RESET_PRICE_FORMULA_PROPERTY).setValue(textValue);
                        columnName = Constants.NET_RESET_PRICE_FORMULA_COLUMN_NAME;
                        value = netResetPriceFormulaDto.getFormulaSid();
                        break;
                    case Constants.NET_PRICE_TYPE_LABLE_NAME:
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty(Constants.NET_PRICE_TYPE_PROPERTY).setValue(tempDTO);
                        columnName = Constants.NET_PRICE_TYPE_COLUMN_NAME;
                        value = tempDTO.getId();
                        break;
                    case Constants.NET_PRICE_TYPE_FORMULA_LABLE_NAME:
                        FormulaDTO netPriceTypeFormulaDto = (FormulaDTO) massUpdateText.getData();
                        textValue = String.valueOf(netPriceTypeFormulaDto.getFormulaNo());
                        contractSelectionTable.getItem(object).getItemProperty(Constants.NET_PRICE_TYPE_FORMULA_PROPERTY).setValue(textValue);
                        columnName = Constants.NET_PRICE_TYPE_FORMULA_COLUMN_NAME;
                        value = netPriceTypeFormulaDto.getFormulaSid();
                        break;
                }
            }
        }

        list.add(columnName);

        list.add(value);
        if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            list.add(selection.getSessionId());
            list.add(getTabOperation());
        } else {
            list.addAll(getSessionInput(selection));
        }

        logic.massUpdateItemDetails(list);
        contractSelectionTable.getContainerLogic().setCurrentPage(1);
    }

    public boolean singleContractCheck(String queryName, List input) {
        List<Object[]> contractList = ItemQueries.getItemData(input, queryName, null);
        if (AbstractLogic.getCount(contractList) == 1) {
            return true;
        }
        return false;

    }

    public List getSessionInput(SelectionDTO selection) {
        return AbstractLogic.getResultsInput(selection);
    }

    public List getSessionSummary(SelectionDTO selection) {
        return AbstractLogic.getResultsSummary(selection);
    }

    public void resetCheckRecord() {
        if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            ItemQueries.itemUpdate(AbstractLogic.getCurrentInput(selection), "ResettingCheckRecordForProjTransfer");
        } else {
            ItemQueries.itemUpdate(getSessionInput(selection), "Resetting CheckRecord");
        }

    }

    public boolean selectingOneContract(String queryName, List input) {

        List<Object[]> list = ItemQueries.getItemData(input, queryName, null);
        if (AbstractLogic.getCount(list) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public String getContractItemName() {
        return contractItemName;
    }

    public void setContractItemName(String contractItemName) {
        this.contractItemName = contractItemName;
    }

    private boolean isHavingValue(String columnName) {
        List input = getSessionInput(selection);
        input.add(tempTableMap.get(columnName));
        List<Object[]> list = ItemQueries.getItemData(input, "Mass Update data check", null);
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            return obj == null ? Boolean.TRUE : (Integer) obj == 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        return Boolean.TRUE;

    }

    public boolean isIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(boolean isSubmit) {
        this.isSubmit = isSubmit;
    }

    public void updateSubmittedContract() {
        List updateInput = new ArrayList();
        updateInput.add("OPERATION");
        updateInput.add(ConstantsUtil.SUMMARY);
        updateInput.addAll(getSessionInput(selection));
        ItemQueries.itemUpdate(updateInput, "Abstract Mass update"); // To Change The operation from Current transfer to Current summary
    }

    public String getTabOperation() {
        return tabOperation;
    }

    public void setTabOperation(String tabOperation) {
        this.tabOperation = tabOperation;
    }

    public String getTransferSalesString() {
        return transferSalesString;
    }

    public void setTransferSalesString(String transferSalesString) {
        this.transferSalesString = transferSalesString;
    }

    public boolean isRemoveProjectionBooleanVal() {
        return removeProjectionBooleanVal;
    }

    public void setRemoveProjectionBooleanVal(boolean removeProjectionBooleanVal) {
        this.removeProjectionBooleanVal = removeProjectionBooleanVal;
    }

    public boolean isSalesAndUnitsCheck(Collection itemId) {
        if (getTransferSalesString().equals("Yes")) {
            setContractItemName(StringUtils.EMPTY);
            Integer projectionId = 0;
            String forecastingType = StringUtils.EMPTY;
            for (Object object : itemId) {
                AbstractContractSearchDTO dto = (AbstractContractSearchDTO) object;
                if (dto.getCheckRecord()) {
                    setContractItemName(dto.getContractName());
                }
            }
            List<Object[]> list = ItemQueries.getItemData(getSessionInput(selection), "latestAndGreatestProjection", null);
            if (!list.isEmpty()) {
                Object[] obj = list.get(0);
                projectionId = obj[0] == null ? 0 : (Integer) obj[0];
                forecastingType = obj[1] == null ? StringUtils.EMPTY : (String) obj[1];
            }
            List salesInput = new ArrayList();
            List<String> forecastList = CommonLogic.getApprovedProjectionResults(forecastingType, true);
            salesInput.add(forecastList.get(1));
            salesInput.add(forecastList.get(1));
            salesInput.add(forecastList.get(0));
            salesInput.add(projectionId);
            List<Object[]> salesList = ItemQueries.getItemData(salesInput, "SalesUnitsCheck", null);
            if (AbstractLogic.getDataCount(salesList) == 0) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        } else {
            return Boolean.TRUE;
        }
    }

    public Boolean nonApprovedSubmitCheck(String queryName, List input) {
        List<Object[]> list = ItemQueries.getItemData(input, queryName, null);
        if (AbstractLogic.getCount(list) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public String getContractNAmesCheck(String queryName, List input) {
        StringBuilder sb = new StringBuilder();
        List<Object[]> list = ItemQueries.getItemData(input, queryName, null);
        if (list.isEmpty()) {
            return StringUtils.EMPTY;
        } else {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append("<").append(String.valueOf(obj[0])).append(",").
                        append(String.valueOf(obj[1])).append(">");

            }
            return sb.toString();
        }

    }

    private void LoadComboToTableMap() {
        comboToTableMap.put(Constants.STATUS_FIELD, ConstantsUtil.STATUS);
        comboToTableMap.put(Constants.START_DATE_HEADER, ConstantsUtil.ITEM_START_DATE);
        comboToTableMap.put(Constants.END_DATE_HEADER, ConstantsUtil.ITEM_END_DATE);
        comboToTableMap.put(StringConstantsUtil.CP_START_DATE_LABEL, StringConstantsUtil.CP_START_DATE);
        comboToTableMap.put(StringConstantsUtil.CP_END_DATE_LABEL, StringConstantsUtil.CP_END_DATE);
        comboToTableMap.put(StringConstantsUtil.CONTRACT_PRICE_LABEL, StringConstantsUtil.CONTRACT_PRICE_PROPERTY);
        comboToTableMap.put(StringConstantsUtil.PRICE_LABEL, StringConstantsUtil.PRICE_PROPERTY);
        comboToTableMap.put(Constants.PRICE_PROTECTION_START_DATE_LABEL, Constants.PRICE_PROTECTION_START_DATE_PROPERTY);
        comboToTableMap.put(Constants.PRICE_PROTECTION_END_DATE_LABEL, Constants.PRICE_PROTECTION_END_DATE_PROPERTY);
        comboToTableMap.put(Constants.PRICE_TOLERANCE_LABEL, Constants.PRICE_TOLERANCE_PROPERTY);
        comboToTableMap.put(Constants.PRICE_TOLERANCE_TYPE_LABEL, Constants.PRICE_TOLERANCE_TYPE_PROPERTY);
        comboToTableMap.put(Constants.PRICE_TOLERANCE_FREQUENCY_LABEL, Constants.PRICE_TOLERANCE_FREQUENCY_PROPERTY);
        comboToTableMap.put(Constants.PRICE_TOLERANCE_INTERVAL_LABEL, Constants.PRICE_TOLERANCE_INTERVAL);
        comboToTableMap.put(StringConstantsUtil.BASE_PRICE_LABEL, StringConstantsUtil.BASE_PRICE_PROPERTY);
        comboToTableMap.put(StringConstantsUtil.RS_START_DATE_LABEL_CAPS, StringConstantsUtil.RS_START_DATE_LABEL);
        comboToTableMap.put(StringConstantsUtil.RS_END_DATE_LABEL, StringConstantsUtil.RS_END_DATE_COLUMN);
        comboToTableMap.put(Constants.FORMULA_ID_LABEL, Constants.FORMULA_ID_PROPERTY);
        comboToTableMap.put(Constants.REBATE_PLAN_LABEL, Constants.REBATE_PLAN_PROPERTY);
        comboToTableMap.put(StringConstantsUtil.FORMULA_METHOD_ID_LABEL, StringConstantsUtil.FORMULA_METHOD_ID_PROPERTY);
        comboToTableMap.put(Constants.NEP_COLUMN_NAME, Constants.NEP_PROPERTY);
        comboToTableMap.put(Constants.PRICE_PROTECTION_STATUS_LABEL, Constants.PRICE_PROTECTION_STATUS_PROPERTY);
        comboToTableMap.put(Constants.NEP_FORMULA_LABLE_NAME, "nepFormula");
        comboToTableMap.put(Constants.MAX_INCREMENTAL_CHANGE_LABLE_NAME, Constants.MAX_INCREMENTAL_CHANGE_PROPERTY);
        comboToTableMap.put(Constants.RESET_ELIGIBLE_LABLE_NAME, Constants.RESET_ELIGIBLE_PROPERTY);
        comboToTableMap.put(Constants.RESET_TYPE_LABLE_NAME, Constants.RESET_TYPE_PROPERTY);
        comboToTableMap.put(Constants.RESET_DATE_LABLE_NAME, Constants.RESET_DATE_PROPERTY);
        comboToTableMap.put(Constants.RESET_INTERVAL_LABLE_NAME, Constants.RESET_INTERVAL_PROPERTY);
        comboToTableMap.put(Constants.RESET_FREQUENCY_LABLE_NAME, Constants.RESET_FREQUENCY_PROPERTY);
        comboToTableMap.put(Constants.NET_PRICE_TYPE_LABLE_NAME, Constants.NET_PRICE_TYPE_PROPERTY);
        comboToTableMap.put(Constants.NET_PRICE_TYPE_FORMULA_LABLE_NAME, Constants.NET_PRICE_TYPE_FORMULA_PROPERTY);
        comboToTableMap.put(Constants.RESET_PRICE_TYPE_LABLE_NAME, Constants.RESET_PRICE_TYPE_PROPERTY);
        comboToTableMap.put(Constants.NET_RESET_PRICE_TYPE_LABLE_NAME, Constants.NET_RESET_PRICE_TYPE_PROPERTY);
        comboToTableMap.put(Constants.NET_RESET_PRICE_FORMULA_LABLE_NAME, Constants.NET_RESET_PRICE_FORMULA_PROPERTY);
        comboToTableMap.put(Constants.BASE_PRICE_TYPE_LABLE_NAME, Constants.BASE_PRICE_PROPERTY);
        comboToTableMap.put(Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_LABLE_NAME, Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY);
        comboToTableMap.put(Constants.NET_SUBSEQUENT_PERIOD_PRICE_LABLE_NAME, Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY);
        comboToTableMap.put(Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME, "netSubsequentPriceFormulaId");
        comboToTableMap.put(Constants.NET_BASELINE_WAC_FORMULA_LABLE_NAME, "netBaselineWacFormulaId");
        comboToTableMap.put(Constants.BASELINE_NET_WAC_LABLE_NAME, Constants.BASELINE_NET_WAC_PROPERTY);
        comboToTableMap.put(Constants.PRICE_TYPE_LABEL, Constants.PRICE_TYPE_PROPERTY);
        comboToTableMap.put(Constants.MEASUREMENT_PRICE_LABLE_NAME, Constants.MEASUREMENT_PRICE_PROPERTY);
    }

    private void LoadTempToTableMap() {
        tempTableMap.put(Constants.STATUS_FIELD, StringConstantsUtil.ITEM_STATUS_COLUMN);
        tempTableMap.put(Constants.START_DATE_HEADER, StringConstantsUtil.START_DATE_COLUMN);
        tempTableMap.put(Constants.END_DATE_HEADER, StringConstantsUtil.END_DATE_COLUMN);
        tempTableMap.put(StringConstantsUtil.CP_START_DATE_LABEL, StringConstantsUtil.CONTRACT_PRICE_START_DATE_COLUMN);
        tempTableMap.put(StringConstantsUtil.CP_END_DATE_LABEL, StringConstantsUtil.CONTRACT_PRICE_END_DATE_COLUMN);
        tempTableMap.put(StringConstantsUtil.CONTRACT_PRICE_LABEL, StringConstantsUtil.CONTRACT_PRICE_COLUMN);
        tempTableMap.put(StringConstantsUtil.PRICE_LABEL, StringConstantsUtil.PRICE_COLUMN);
        tempTableMap.put(Constants.PRICE_PROTECTION_START_DATE_LABEL, StringConstantsUtil.PRICE_PROTECTION_START_DATE_COLUMN);
        tempTableMap.put(Constants.PRICE_PROTECTION_END_DATE_LABEL, StringConstantsUtil.PRICE_PROTECTION_END_DATE_LABEL);
        tempTableMap.put(Constants.PRICE_TOLERANCE_LABEL, StringConstantsUtil.PRICE_TOLERANCE_COLUMN);
        tempTableMap.put(Constants.PRICE_TOLERANCE_TYPE_LABEL, StringConstantsUtil.PRICE_TOLERANCE_TYPE_LABEL);
        tempTableMap.put(Constants.PRICE_TOLERANCE_FREQUENCY_LABEL, StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY_LABEL);
        tempTableMap.put(Constants.PRICE_TOLERANCE_INTERVAL_LABEL, StringConstantsUtil.PRICE_TOLERANCE_INTERVAL_LABEL);
        tempTableMap.put(StringConstantsUtil.BASE_PRICE_LABEL, StringConstantsUtil.BASE_PRICE_COLUMN);
        tempTableMap.put(StringConstantsUtil.RS_START_DATE_LABEL_CAPS, StringConstantsUtil.ITEM_REBATE_START_DATE_LABEL);
        tempTableMap.put(StringConstantsUtil.RS_END_DATE_LABEL, StringConstantsUtil.ITEM_REBATE_END_DATE_LABEL);
        tempTableMap.put(Constants.FORMULA_ID_LABEL, StringConstantsUtil.FORMULA_ID_LABEL);
        tempTableMap.put(Constants.REBATE_PLAN_LABEL, StringConstantsUtil.REBATE_PLAN_SYSTEM_ID_LABEL);
        tempTableMap.put(StringConstantsUtil.FORMULA_METHOD_ID_LABEL, StringConstantsUtil.FORMULA_METHOD_ID_COLUMN);
        tempTableMap.put(Constants.REBATE_AMOUNT_LABEL, StringConstantsUtil.REBATE_AMOUNT_COLUMN);
        tempTableMap.put(Constants.NEP_LABLE_NAME, Constants.NEP_COLUMN_NAME);
        tempTableMap.put(Constants.PRICE_PROTECTION_STATUS_LABEL, Constants.PRICE_PROTECTION_STATUS_COLUMN_NAME);
        tempTableMap.put(Constants.NEP_FORMULA_LABLE_NAME, Constants.NEP_FORMULA_COLUMN_NAME);
        tempTableMap.put(Constants.MAX_INCREMENTAL_CHANGE_LABLE_NAME, Constants.MAX_INCREMENTAL_CHANGE_COLUMN_NAME);
        tempTableMap.put(Constants.RESET_ELIGIBLE_LABLE_NAME, Constants.RESET_ELIGIBLE_COLUMN_NAME);
        tempTableMap.put(Constants.RESET_TYPE_LABLE_NAME, Constants.RESET_TYPE_COLUMN_NAME);
        tempTableMap.put(Constants.RESET_DATE_LABLE_NAME, Constants.RESET_DATE_COLUMN_NAME);
        tempTableMap.put(Constants.RESET_INTERVAL_LABLE_NAME, Constants.RESET_INTERVAL_COLUMN_NAME);
        tempTableMap.put(Constants.RESET_FREQUENCY_LABLE_NAME, Constants.RESET_FREQUENCY_COLUMN_NAME);
        tempTableMap.put(Constants.NET_PRICE_TYPE_LABLE_NAME, Constants.NET_PRICE_TYPE_COLUMN_NAME);
        tempTableMap.put(Constants.NET_PRICE_TYPE_FORMULA_LABLE_NAME, Constants.NET_PRICE_TYPE_FORMULA_COLUMN_NAME);
        tempTableMap.put(Constants.RESET_PRICE_TYPE_LABLE_NAME, Constants.RESET_PRICE_TYPE_COLUMN_NAME);
        tempTableMap.put(Constants.NET_RESET_PRICE_TYPE_LABLE_NAME, Constants.NET_RESET_PRICE_TYPE_COLUMN_NAME);
        tempTableMap.put(Constants.NET_RESET_PRICE_FORMULA_LABLE_NAME, Constants.NET_RESET_PRICE_FORMULA_COLUMN_NAME);
        tempTableMap.put(Constants.BASE_PRICE_TYPE_LABLE_NAME, Constants.BASE_PRICE_TYPE_COLUMN_NAME);
        tempTableMap.put(Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_LABLE_NAME, Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_COLUMN_NAME);
        tempTableMap.put(Constants.NET_SUBSEQUENT_PERIOD_PRICE_LABLE_NAME, Constants.NET_SUBSEQUENT_PERIOD_PRICE_COLUMN_NAME);
        tempTableMap.put(Constants.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME, Constants.NET_SUBSEQUENT_PRICE_FORMULA_COLUMN_NAME);
        tempTableMap.put(Constants.NET_BASELINE_WAC_FORMULA_LABLE_NAME, Constants.NET_BASELINE_WAC_FORMULA_COLUMN_NAME);
        tempTableMap.put(Constants.BASELINE_WAC_LABLE_NAME, Constants.BASELINE_WAC_MANUAL_COLUMN_NAME);
        tempTableMap.put(Constants.BASELINE_WAC_LABLE_NAME, Constants.BASELINE_WAC_DATE_COLUMN_NAME);
        tempTableMap.put(Constants.BASELINE_WAC_LABLE_NAME, Constants.BASELINE_WAC_PRICE_TYPE_COLUMN_NAME);
    }

    private void loadFieldAndPropertyMap() {
        fieldAndPropertyMap.put(StringConstantsUtil.ITEM_STATUS_COLUMN, ConstantsUtil.STATUS);
        fieldAndPropertyMap.put(StringConstantsUtil.START_DATE_COLUMN, ConstantsUtil.ITEM_START_DATE);
        fieldAndPropertyMap.put(StringConstantsUtil.END_DATE_COLUMN, ConstantsUtil.ITEM_END_DATE);
        fieldAndPropertyMap.put(StringConstantsUtil.CONTRACT_PRICE_START_DATE_COLUMN, StringConstantsUtil.CP_START_DATE);
        fieldAndPropertyMap.put(StringConstantsUtil.CONTRACT_PRICE_END_DATE_COLUMN, StringConstantsUtil.CP_END_DATE);
        fieldAndPropertyMap.put(StringConstantsUtil.CONTRACT_PRICE_COLUMN, StringConstantsUtil.CONTRACT_PRICE_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.PRICE_COLUMN, StringConstantsUtil.PRICE_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.PRICE_PROTECTION_START_DATE_COLUMN, Constants.PRICE_PROTECTION_START_DATE_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.PRICE_PROTECTION_END_DATE_LABEL, Constants.PRICE_PROTECTION_END_DATE_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.PRICE_TOLERANCE_COLUMN, Constants.PRICE_TOLERANCE_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.PRICE_TOLERANCE_TYPE_LABEL, Constants.PRICE_TOLERANCE_TYPE_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY_LABEL, Constants.PRICE_TOLERANCE_FREQUENCY_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.PRICE_TOLERANCE_INTERVAL_LABEL, Constants.PRICE_TOLERANCE_INTERVAL);
        fieldAndPropertyMap.put(StringConstantsUtil.BASE_PRICE_COLUMN, StringConstantsUtil.BASE_PRICE_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.ITEM_REBATE_START_DATE_LABEL, StringConstantsUtil.RS_START_DATE_LABEL);
        fieldAndPropertyMap.put(StringConstantsUtil.ITEM_REBATE_END_DATE_LABEL, StringConstantsUtil.RS_END_DATE_COLUMN);
        fieldAndPropertyMap.put(StringConstantsUtil.FORMULA_ID_LABEL, Constants.FORMULA_ID_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.REBATE_PLAN_SYSTEM_ID_LABEL, Constants.REBATE_PLAN_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.FORMULA_METHOD_ID_COLUMN, StringConstantsUtil.FORMULA_METHOD_ID_PROPERTY);
        fieldAndPropertyMap.put(StringConstantsUtil.REBATE_AMOUNT_COLUMN, Constants.REBATE_AMOUNT_PROPERTY);
        fieldAndPropertyMap.put(Constants.NEP_COLUMN_NAME, Constants.NEP_PROPERTY);
        fieldAndPropertyMap.put(Constants.PRICE_PROTECTION_STATUS_COLUMN_NAME, Constants.PRICE_PROTECTION_STATUS_PROPERTY);
        fieldAndPropertyMap.put(Constants.NEP_FORMULA_COLUMN_NAME, "nepFormula");
        fieldAndPropertyMap.put(Constants.MAX_INCREMENTAL_CHANGE_COLUMN_NAME, Constants.MAX_INCREMENTAL_CHANGE_PROPERTY);
        fieldAndPropertyMap.put(Constants.RESET_ELIGIBLE_COLUMN_NAME, Constants.RESET_ELIGIBLE_PROPERTY);
        fieldAndPropertyMap.put(Constants.RESET_TYPE_COLUMN_NAME, Constants.RESET_TYPE_PROPERTY);
        fieldAndPropertyMap.put(Constants.RESET_DATE_COLUMN_NAME, Constants.RESET_DATE_PROPERTY);
        fieldAndPropertyMap.put(Constants.RESET_INTERVAL_COLUMN_NAME, Constants.RESET_INTERVAL_PROPERTY);
        fieldAndPropertyMap.put(Constants.RESET_FREQUENCY_COLUMN_NAME, Constants.RESET_FREQUENCY_PROPERTY);
        fieldAndPropertyMap.put(Constants.NET_PRICE_TYPE_COLUMN_NAME, Constants.NET_PRICE_TYPE_PROPERTY);
        fieldAndPropertyMap.put(Constants.NET_PRICE_TYPE_FORMULA_COLUMN_NAME, Constants.NET_PRICE_TYPE_FORMULA_PROPERTY);
        fieldAndPropertyMap.put(Constants.RESET_PRICE_TYPE_COLUMN_NAME, Constants.RESET_PRICE_TYPE_PROPERTY);
        fieldAndPropertyMap.put(Constants.NET_RESET_PRICE_TYPE_COLUMN_NAME, Constants.NET_RESET_PRICE_TYPE_PROPERTY);
        fieldAndPropertyMap.put(Constants.NET_RESET_PRICE_FORMULA_COLUMN_NAME, Constants.NET_RESET_PRICE_FORMULA_PROPERTY);
        fieldAndPropertyMap.put(Constants.BASE_PRICE_TYPE_COLUMN_NAME, Constants.BASE_PRICE_PROPERTY);
        fieldAndPropertyMap.put(Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_COLUMN_NAME, Constants.SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY);
        fieldAndPropertyMap.put(Constants.NET_SUBSEQUENT_PERIOD_PRICE_COLUMN_NAME, Constants.NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY);
        fieldAndPropertyMap.put(Constants.NET_SUBSEQUENT_PRICE_FORMULA_COLUMN_NAME, "netSubsequentPriceFormulaId");
        fieldAndPropertyMap.put(Constants.NET_BASELINE_WAC_FORMULA_COLUMN_NAME, "netBaselineWacFormulaId");
        fieldAndPropertyMap.put(Constants.BASELINE_NET_WAC_COLUMN_NAME, Constants.BASELINE_NET_WAC_PROPERTY);
        fieldAndPropertyMap.put(Constants.PRICE_TYPE_COLUMN_NAME, Constants.PRICE_TYPE_PROPERTY);
        fieldAndPropertyMap.put(Constants.MEASUREMENT_PRICE_COLUMN_NAME, Constants.MEASUREMENT_PRICE_PROPERTY);
        fieldAndPropertyMap.put(Constants.BASELINE_WAC_MANUAL_COLUMN_NAME, Constants.BASELINE_WAC_MANUAL_LABLE_NAME);
        fieldAndPropertyMap.put(Constants.BASELINE_WAC_DATE_COLUMN_NAME, Constants.BASELINE_WAC_DATE_LABLE_NAME);
        fieldAndPropertyMap.put(Constants.BASELINE_WAC_PRICE_TYPE_COLUMN_NAME, Constants.BASELINE_WAC_PRICE_TYPE_LABLE_NAME);
    }
    
    
    private void loadStartEndDateMap() {
        startDateEndDateMap.put(Constants.ITEM_START_DATE, StringConstantsUtil.START_DATE_COLUMN);
        startDateEndDateMap.put(Constants.ITEM_END_DATE, StringConstantsUtil.END_DATE_COLUMN);
        startDateEndDateMap.put(StringConstantsUtil.CP_END_DATE_LABEL, StringConstantsUtil.CONTRACT_PRICE_START_DATE_COLUMN);
        startDateEndDateMap.put(StringConstantsUtil.CP_START_DATE_LABEL, StringConstantsUtil.CONTRACT_PRICE_END_DATE_COLUMN);
        startDateEndDateMap.put(Constants.PRICE_PROTECTION_END_DATE_LABEL, StringConstantsUtil.PRICE_PROTECTION_START_DATE_COLUMN);
        startDateEndDateMap.put(Constants.PRICE_PROTECTION_START_DATE_LABEL, StringConstantsUtil.PRICE_PROTECTION_END_DATE_LABEL);
        startDateEndDateMap.put(StringConstantsUtil.RS_END_DATE_LABEL, StringConstantsUtil.ITEM_REBATE_START_DATE_LABEL);
        startDateEndDateMap.put(StringConstantsUtil.RS_START_DATE_LABEL_CAPS, StringConstantsUtil.ITEM_REBATE_END_DATE_LABEL);
        startDateEndDateMap.put(Constants.RESET_DATE_LABLE_NAME, Constants.RESET_DATE_COLUMN_NAME);
    }
    
    
    public void loadValueddlbField() {
        massUpdateValue.setVisible(true);
        massStartDate.setVisible(false);
        massEndDate.setVisible(false);
        populateBtn.setVisible(true);
        valuelabel.setVisible(true);
        startdatelabel.setVisible(false);
        enddatelabel.setVisible(false);
        massUpdateText.setVisible(false);
        visibilityOptions();
    }

    public void loadValueddlbTextField() {
        massUpdateValue.setVisible(false);
        massStartDate.setVisible(false);
        massEndDate.setVisible(false);
        startdatelabel.setVisible(false);
        enddatelabel.setVisible(false);
        massUpdateText.setVisible(true);
        populateBtn.setVisible(true);
        valuelabel.setVisible(true);
        visibilityOptions();
    }

    public void loadValueddlbDateField(String processName) {
        massUpdateValue.setVisible(false);
        populateBtn.setVisible(true);
        valuelabel.setVisible(false);
        massUpdateText.setVisible(false);
        visibilityOptions();

        switch (processName) {
            case Constants.ITEM_START_DATE:
                startDateVisibility();
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.ITEM_START_DATE.getConstant());
                break;
            case Constants.ITEM_END_DATE:
                endDateVisibility();
                enddatelabel.setValue(ConstantsUtil.MassUpdateConstants.ITEM_END_DATE.getConstant());
                break;
            case StringConstantsUtil.CP_START_DATE_LABEL:
                startDateVisibility();
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.CP_START_DATE.getConstant());
                break;
            case StringConstantsUtil.CP_END_DATE_LABEL:
                endDateVisibility();
                enddatelabel.setValue(ConstantsUtil.MassUpdateConstants.CP_END_DATE.getConstant());
                break;
            case Constants.PRICE_PROTECTION_START_DATE_LABEL:
                startDateVisibility();
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.PRICE_PRODECTION_START_DATE.getConstant());
                break;
            case Constants.PRICE_PROTECTION_END_DATE_LABEL:
                endDateVisibility();
                enddatelabel.setValue(ConstantsUtil.MassUpdateConstants.PRICE_PRODECTION_END_DATE.getConstant());
                break;
            case Constants.RESET_DATE_LABLE_NAME:
                startDateVisibility();
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.RESET_DATE.getConstant());
                break;
        }
    }

    public void endDateVisibility() {
        massStartDate.setVisible(false);
        massEndDate.setVisible(true);
        startdatelabel.setVisible(false);
        enddatelabel.setVisible(true);
    }

    public void startDateVisibility() {
        massStartDate.setVisible(true);
        massEndDate.setVisible(false);
        startdatelabel.setVisible(true);
        enddatelabel.setVisible(false);
    }
    
    public Boolean multipleEndDateCheck(final Object massUpdate) {
        Boolean multipleDateCheck = Boolean.TRUE;
        if (massUpdate != null && massUpdateString.contains("Date")) {
            List input = new ArrayList();
            String queryMax = StringUtils.EMPTY;
            Boolean isStartDate = Boolean.TRUE;
            if (massUpdateString.contains(Constants.ITEM_END_DATE)) {
                isStartDate = Boolean.FALSE;
                queryMax = "MIN(?)";
            } else if (massUpdateString.contains(Constants.ITEM_START_DATE)) {
                isStartDate = Boolean.TRUE;
                queryMax = "MAX(?)";
            }
            input.add(queryMax.replace("?", startDateEndDateMap.get(massUpdateString).toString()));
            input.addAll(getPopulateDateCheckInput(selection));
            List<Object[]> list = ItemQueries.getItemData(input, "Mass Update Multiple End date Check", null);
            Date date = null;
            if (!list.isEmpty()) {
                Object obj = list.get(0);
                date = obj == null ? null : (Date) obj;
            }
            if (!isStartDate) {
                multipleDateCheck = massUpdateDateCheck((Date) massUpdate, date, "E");
            } else {
                multipleDateCheck = massUpdateDateCheck((Date) massUpdate, date, "S");
            }
        }
        return multipleDateCheck;
    }

    Boolean massUpdateDateCheck(Date masspdateDate, Date maxDate, String indicator) {
        if (indicator.equals("S")) {
            if (maxDate != null && masspdateDate.after(maxDate)) {
                dateMessage = "Start Date cannot come After End Date";
                return false;
            }
            return true;
        } else if (indicator.equals("E")) {
            if (maxDate != null && masspdateDate.before(maxDate)) {
                dateMessage = "End Date cannot come before Start Date";
                return false;
            }
            return true;
        }
        return true;
    }

    private List getNewInput(List input) {
        StringBuilder sql = AbstractFilter.getInstance().contractfilterQueryGenerator(selection.getFilters());
        if (sql != null) {
            input.add(sql);
        } else {
            input.add(StringUtils.EMPTY);
        }
        return input;
    }

    public Button getPopulateBtn() {
        return populateBtn;
    }

    public Button getSearchBtn() {
        return searchBtn;
    }

    public Button getResetBtn() {
        return resetBtn;
    }

    public Button getResetBtncur() {
        return resetBtncur;
    }

    public Button getSubmitBtncur() {
        return submitBtncur;
    }

    public Button getSubmit() {
        return submit;
    }
    public void visibilityOptions() {
        baseWacPriceType.setVisible(false);
        baseWacManual.setVisible(false);
        baseWacDate.setVisible(false);
    }

    
}
