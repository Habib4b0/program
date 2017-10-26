/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.globalchange.fieldfactory.AddItemContractFieldFactory;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractContractSearchDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic.AbstractContractSelectionTableLogic;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.index.form.ItemManagementLookup;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.FormulaDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.security.SecurityLogic;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.UIUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
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

    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    public AbstractContractSearchDTO binderDto = new AbstractContractSearchDTO();
    public CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<AbstractContractSearchDTO>(binderDto));
    public static final Logger LOGGER = Logger.getLogger(AbstractContractSearch.class);
    public AbstractContractSelectionTableLogic contractSelectionTableLogic = new AbstractContractSelectionTableLogic();
    public ExtPagedTable contractSelectionTable = new ExtPagedTable(contractSelectionTableLogic);
    Object[] CONTRACT_SELECTION_VISIBLE_COLUMN = {ConstantsUtil.CHECK_RECORD, "projectionIdLink", "workFlowStatus", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, ConstantsUtil.STATUS, ConstantsUtil.ITEM_START_DATE, ConstantsUtil.ITEM_END_DATE, "cpStartDate", "cpEndDate", "contractPrice", "price", "priceProtectionStartDate", "priceProtectionEndDate", "priceToleranceType", "priceTolerance", "priceToleranceFrequency", "priceToleranceInterval", "basePrice", "RSStartDate", "RSEndDate", "formulaId", "rebatePlan", "formulaMethodId", "rebateAmount", "cfpNO", Constants.CFP_NAME, "ifpNo", Constants.IFPNAME, "psNo", Constants.PSNAME, "rsNo", Constants.RSNAME, "rarCategory"};
    String[] CONTRACT_SELECTION_HEADER = {StringUtils.EMPTY, "Projection ID", "WorkFlow Status", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Status", Constants.ITEM_START_DATE, Constants.ITEM_END_DATE, "CP Start Date", "CP End Date", "Contract Price", "Price", "Price Protection Start Date", "Price Protection End Date", "Price Tolerance Type", "Price Tolerance", "Price Tolerance Frequency", "Price Tolerance Interval", "Base Price", "RS Start Date", "RS End Date", "Formula ID", "Rebate Plan", "Formula Method Id", "Rebate Amount", "CFP No", "CFP Name", Constants.IFP_NO, Constants.IfpNAME, "PS No", " PS Name", "RS No", "RS Name", "RAR Category"};
    Object[] EXCEL_CONTRACT_SELECTION_VISIBLE_COLUMN = {ConstantsUtil.PROJECTION_ID, "workFlowStatus", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, ConstantsUtil.STATUS, ConstantsUtil.ITEM_START_DATE, ConstantsUtil.ITEM_END_DATE, "cpStartDate", "cpEndDate", "contractPrice", "price", "priceProtectionStartDate", "priceProtectionEndDate", "priceToleranceType", "priceTolerance", "priceToleranceFrequency", "priceToleranceInterval", "basePrice", "RSStartDate", "RSEndDate", "formulaId", "rebatePlan", "formulaMethodId", "rebateAmount", "cfpNO", Constants.CFP_NAME, "ifpNo", Constants.IFPNAME, "psNo", Constants.PSNAME, "rsNo", Constants.RSNAME, "rarCategory"};
    String[] EXCEL_CONTRACT_SELECTION_HEADER = {"Projection ID", "WorkFlow Status", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Status", Constants.ITEM_START_DATE, Constants.ITEM_END_DATE, "CP Start Date", "CP End Date", "Contract Price", "Price", "Price Protection Start Date", "Price Protection End Date", "Price Tolerance Type", "Price Tolerance", "Price Tolerance Frequency", "Price Tolerance Interval", "Base Price", "RS Start Date", "RS End Date", "Formula ID", "Rebate Plan", "Formula Method Id", "Rebate Amount", "CFP No", "CFP Name", Constants.IFP_NO, Constants.IfpNAME, "PS No", " PS Name", "RS No", "RS Name", "RAR Category"};
    SelectionDTO selection;
    public List<ItemIndexDto> selectedItemList;
    public AbstractLogic logic = AbstractLogic.getInstance();
    public BeanItemContainer<AbstractContractSearchDTO> contractExcelResultBean = new BeanItemContainer<AbstractContractSearchDTO>(AbstractContractSearchDTO.class);
    public ExtCustomTable contractExcelTable;
    public VerticalLayout contractDashboardLay = new VerticalLayout();
    String massUpdateString = StringUtils.EMPTY;
    String tabOperation = StringUtils.EMPTY;
    public StplSecurity stplSecurity = new StplSecurity();
    public Map<String, AppPermission> functionHM = new HashMap<String, AppPermission>();
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
    BeanItemContainer<AbstractContractSearchDTO> container = new BeanItemContainer<AbstractContractSearchDTO>(AbstractContractSearchDTO.class);

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
                        HelperDTO dto = (HelperDTO) originatingField.getValue();

                        return new SimpleStringFilter(propertyId, String.valueOf(dto.getId()), false, false);
                    } else {
                        return null;
                    }
                }
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
                if (Constants.CHECK_RECORD.equals(propertyId)) {
                    CustomTextField text = new CustomTextField();
                    text.setEnabled(false);
                    text.setImmediate(true);
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
                if ("priceToleranceInterval".equals(propertyId)) {
                    ComboBox pricetolerenceintDdlb = new ComboBox();
                    logic.LazyLoadDdlb(pricetolerenceintDdlb, "Load PS_INTERVAL Count", "Load PS_INTERVAL", true);
                    return pricetolerenceintDdlb;
                }
                if ("priceToleranceType".equals(propertyId)) {
                    ComboBox pricetolerencetypeDdlb = new ComboBox();
                    try {
                        logic.loadComboBox(pricetolerencetypeDdlb, "PS_TYPE", true);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                    return pricetolerencetypeDdlb;
                }
                if ("priceToleranceFrequency".equals(propertyId)) {
                    ComboBox pricetolerencefreqDdlb = new ComboBox();
                    logic.LazyLoadDdlb(pricetolerencefreqDdlb, "Load PS_FREQ Count", "Load PS_FREQ", true);
                    return pricetolerencefreqDdlb;
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
        return contractSelectionTable;
    }

    public void getMassUpdate() {
        massUpdateRadio.addItem("Disable");
        massUpdateRadio.addItem("Enable");
        massUpdateRadio.select("Disable");
        field.addItem(Constants.SELECT_ONE);
        LoadField();
        field.setImmediate(true);
        field.setNullSelectionAllowed(true);
        field.setNullSelectionItemId(Constants.SELECT_ONE);
        field.select(Constants.SELECT_ONE);
        massUpdateRadio.select("Disable");
        massUpdateRadio.setImmediate(true);
        massUpdateValue.setReadOnly(true);
        massUpdateValue.setVisible(Boolean.FALSE);
        massStartDate.setReadOnly(true);
        massEndDate.setReadOnly(true);
        massStartDate.setVisible(Boolean.FALSE);
        massEndDate.setVisible(Boolean.FALSE);
        populateBtn.setEnabled(false);
        massUpdateRadio.addItems("Enable", "Disable");
        LoadComboToTableMap();
        LoadTempToTableMap();
        loadFieldAndPropertyMap();
        loadStartEndDateMap();

    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException {
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

    public void createWorkSheet(String moduleName) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
    public void fieldTypeLogic(Property.ValueChangeEvent event) throws SystemException {
        String processName = String.valueOf(field.getValue());
        massUpdateString = processName;

        switch (processName) {
            case "Status":
                massUpdateValue.setVisible(true);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                loadStatus();
                break;
            case "Start Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(true);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(true);
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.START_DATE.getConstant());
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                break;
            case "End Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(true);
                massUpdateText.setVisible(false);
                enddatelabel.setValue(ConstantsUtil.MassUpdateConstants.END_DATE.getConstant());
                break;
            case "CP Start Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(true);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(true);
                massUpdateText.setVisible(false);
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.CP_START_DATE.getConstant());
                enddatelabel.setVisible(false);
                break;
            case "CP End Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.CP_END_DATE.getConstant());
                enddatelabel.setVisible(true);
                break;
            case "Contract Price":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                break;
            case "Price":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                break;
            case "Price Protection Start Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(true);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(true);
                massUpdateText.setVisible(false);
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.PRICE_PRODECTION_START_DATE.getConstant());
                enddatelabel.setVisible(false);
                break;
            case "Price Protection End Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                enddatelabel.setValue(ConstantsUtil.MassUpdateConstants.PRICE_PRODECTION_END_DATE.getConstant());
                enddatelabel.setVisible(true);
                break;
            case "Price Tolerance Type":
                massUpdateValue.setVisible(true);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                loadPriceTolerenceType();
                break;
            case "Price Tolerance":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                break;
            case "Price Tolerance Frequency":
                massUpdateValue.setVisible(true);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                loadPriceToleranceFrequency();
                break;
            case "Price Tolerance Interval":
                massUpdateValue.setVisible(true);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                loadPriceToleranceInterval();
                break;
            case "Base Price":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                break;
            case "RS Start Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(true);
                massEndDate.setVisible(false);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(true);
                massUpdateText.setVisible(false);
                startdatelabel.setValue(ConstantsUtil.MassUpdateConstants.RS_START_DATE.getConstant());
                enddatelabel.setVisible(false);
                break;
            case "RS End Date":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(false);
                startdatelabel.setVisible(false);
                massUpdateText.setVisible(false);
                enddatelabel.setValue(ConstantsUtil.MassUpdateConstants.RS_END_DATE.getConstant());
                enddatelabel.setVisible(true);
                break;
            case "Formula ID":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                massUpdateText.addStyleName("searchicon");
                if (clickLister != null) {
                    massUpdateText.removeClickListener(clickLister);
                }

                clickLister = new CustomTextField.ClickListener() {
                    public void click(CustomTextField.ClickEvent event) {
                        FormulaLookUp formulaLookUp = new FormulaLookUp(massUpdateText);
                        formulaLookUp.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                massUpdateText.setReadOnly(false);
                                if (massUpdateText.getData() != null) {
                                    FormulaDTO object = (FormulaDTO) massUpdateText.getData();
                                    massUpdateText.setValue(object.getFormulaNo());
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
            case "Rebate Plan":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                massUpdateText.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                if (clickLister != null) {
                    massUpdateText.removeClickListener(clickLister);
                }
                clickLister = new CustomTextField.ClickListener() {
                    public void click(CustomTextField.ClickEvent event) {
                        final ComponentLookUp contractNum = new ComponentLookUp("Rebate Plan", "Rebate Plan Lookup", massUpdateText);
                        contractNum.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                massUpdateText.setReadOnly(false);
                                if (massUpdateText.getData() != null) {
                                    ComponentLookUpDTO object = (ComponentLookUpDTO) massUpdateText.getData();
                                    massUpdateText.setValue(object.getComponentNo());
                                }
                                massUpdateText.setReadOnly(true);
                            }
                        });

                        UI.getCurrent().addWindow(contractNum);
                    }
                };
                massUpdateText.addClickListener(clickLister);
                massUpdateText.setReadOnly(true);
                break;
            case "Formula Method ID":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                if (clickLister != null) {
                    massUpdateText.removeClickListener(clickLister);
                }
                massUpdateText.removeStyleName("searchicon");
                massUpdateText.setReadOnly(false);
                massUpdateText.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                break;
            case "Rebate Amount":
                massUpdateValue.setVisible(false);
                massStartDate.setVisible(false);
                massEndDate.setVisible(false);
                startdatelabel.setVisible(false);
                enddatelabel.setVisible(false);
                if (clickLister != null) {
                    massUpdateText.removeClickListener(clickLister);
                }
                massUpdateText.removeStyleName("searchicon");
                massUpdateText.setReadOnly(false);
                massUpdateText.setVisible(true);
                populateBtn.setVisible(true);
                valuelabel.setVisible(true);
                break;
        }
        massStartDate.setValue(null);
        massEndDate.setValue(null);
        massUpdateValue.setValue(null);
        massUpdateText.setValue(StringUtils.EMPTY);
    }

    @UiHandler("allItemsButton")
    public void allItemsButtonLogic(Button.ClickEvent event) throws SystemException {
        AbstractAllItemLookup itemLookup = new AbstractAllItemLookup(selectedItemList);
        UI.getCurrent().addWindow(itemLookup);
    }

    @UiHandler("massUpdateRadio")
    public void massTypeLogic(Property.ValueChangeEvent event) throws SystemException {
        String processName = String.valueOf(massUpdateRadio.getValue());
        if ("Disable".equals(processName)) {
            resetMassUpdate();
            field.setReadOnly(true);
            massStartDate.setReadOnly(true);
            massEndDate.setReadOnly(true);
            massUpdateValue.setReadOnly(true);
            populateBtn.setEnabled(false);
            massUpdateText.setEnabled(false);

        }
        if ("Enable".equals(processName)) {
            field.setReadOnly(false);
            massStartDate.setReadOnly(false);
            massEndDate.setReadOnly(false);
            massUpdateValue.setReadOnly(false);
            populateBtn.setEnabled(true);
            massUpdateText.setEnabled(true);

        }

    }

    public CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<AbstractContractSearchDTO>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    public void searchButtonLogic() throws FieldGroup.CommitException {
        ItemManagementLookup.waitForSave(selection);
        selection.setReset(true);
        binder.commit();
        List input = getInput();
        resetCheckRecord();
        if (!contractSelectionTableLogic.loadSetData(selection, true, selectedItemList, input)) {
            AbstractNotificationUtils.getErrorNotification("No Matching Records",
                    "There were no records matching the search criteria.  Please try again.");
        } else {
            Notification.show("Search Completed");
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
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the values?");

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
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to reset the values in the Available Contracts list view?");

    }

    @UiHandler("populateBtn")
    public void populateButton(Button.ClickEvent event) {
        if (!massUpdateString.isEmpty()) {
            if (massUpdateValue.getValue() != null || massStartDate.getValue() != null || massEndDate.getValue() != null || (massUpdateText != null && !massUpdateText.getValue().isEmpty())) {
                if (multipleEndDateCheck(massUpdateString.contains("Start Date") ? massStartDate.getValue() : massUpdateString.contains("End Date") ? massEndDate.getValue() : null)) {
                    isFound = isPresent();
                    if (isFound) {
                        populateLogic();
                    }
                } else {
                    MessageBox.showPlain(Icon.INFO, "Date Conflicts", "You Cannot Proceed with this date since " + dateMessage, ButtonId.OK);
                }
            } else {
                MessageBox.showPlain(Icon.INFO, "Error", "Please enter a " + massUpdateString + " to Mass Update. ", ButtonId.OK);

            }
        } else {
            MessageBox.showPlain(Icon.INFO, "Error", "Please select a Field to Mass Update. ", ButtonId.OK);

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
        CommonUtil.getComboBoxByListName(massUpdateValue, "PRICE_TOLERANCE_TYPE", false);
    }

    private void loadPriceToleranceFrequency() {
        CommonUtil.getComboBoxByListName(massUpdateValue, "PRICE_TOLERANCE_FREQUENCY", false);
    }

    private void loadPriceToleranceInterval() {
        CommonUtil.getComboBoxByListName(massUpdateValue, "PRICE_TOLERANCE_INTERVAL", false);
    }

    @UiHandler("submit")
    public void submitButtonLogic(Button.ClickEvent event) throws SystemException, PortalException {
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
                                    searchButtonLogic();
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
                        }.getConfirmationMessage("Confirmation", "Are you sure you want to submit the selected contracts?");
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Error",
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
        List<Object[]> list = ItemQueries.getItemData(input, "Submit condition check", null);
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
                        }
                    }.getConfirmationMessage("Confirmation", "There are values in these fields already. Are you sure you want to replace them?");
                } else {
                    populateLogic();
                }
                break;

            }
        }
        if (isChecked) {
            MessageBox.showPlain(Icon.INFO, "Error", "Please select at least one contract to apply the Mass Update to. ", ButtonId.OK);
        }
        return isFound;

    }

    public void populateLogic() {
        Collection itemId = contractSelectionTable.getItemIds();
        List list = new ArrayList();
        Object value = null;
        String columnName = StringUtils.EMPTY;
        String textValue = StringUtils.EMPTY;
        HelperDTO tempDTO;
        Date tempDdate;
        for (Object object : itemId) {
            AbstractContractSearchDTO dto = (AbstractContractSearchDTO) object;
            if (dto.getCheckRecord()) {
                switch (massUpdateString) {
                    case "Status":
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty("status").setValue(tempDTO);
                        columnName = "ITEM_STATUS";
                        value = tempDTO.getId();
                        break;
                    case "Start Date":
                        contractSelectionTable.getItem(object).getItemProperty("itemStartDate").setValue(massStartDate.getValue());
                        columnName = "START_DATE";
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;
                    case "End Date":
                        tempDdate = dto.getItemStartDate();
                        if (massEndDate.getValue().before(tempDdate)) {
                            MessageBox.showPlain(Icon.INFO, "Error", "The End Date must come after the Start Date. Please reenter the End Date. ", ButtonId.OK);
                            return;
                        } else {
                            contractSelectionTable.getItem(object).getItemProperty("itemEndDate").setValue(massEndDate.getValue());
                            columnName = "END_DATE";
                            value = CommonUtils.DBDate.format(massEndDate.getValue());

                        }
                        break;
                    case "CP Start Date":
                        contractSelectionTable.getItem(object).getItemProperty("cpStartDate").setValue(massStartDate.getValue());
                        columnName = "CONTRACT_PRICE_START_DATE";
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;
                    case "CP End Date":
                        tempDdate = dto.getCpStartDate();
                        if (massEndDate.getValue().before(tempDdate)) {
                            MessageBox.showPlain(Icon.INFO, "Error", "The End Date must come after the Start Date. Please reenter the End Date. ", ButtonId.OK);
                            return;
                        } else {
                            contractSelectionTable.getItem(object).getItemProperty("cpEndDate").setValue(massEndDate.getValue());
                            columnName = "CONTRACT_PRICE_END_DATE";
                            value = CommonUtils.DBDate.format(massEndDate.getValue());

                        }
                        break;
                    case "Contract Price":
                        textValue = massUpdateText.getValue();
                        contractSelectionTable.getItem(object).getItemProperty("contractPrice").setValue(textValue);
                        columnName = "CONTRACT_PRICE";
                        value = textValue;
                        break;
                    case "Price":
                        textValue = massUpdateText.getValue();
                        contractSelectionTable.getItem(object).getItemProperty("price").setValue(textValue);
                        columnName = "PRICE";
                        value = textValue;
                        break;
                    case "Price Protection Start Date":
                        contractSelectionTable.getItem(object).getItemProperty("priceProtectionStartDate").setValue(massStartDate.getValue());
                        columnName = "PRICE_PROTECTION_START_DATE";
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;

                    case "Price Protection End Date":
                        tempDdate = dto.getPriceProtectionStartDate();
                        if (massEndDate.getValue().before(tempDdate)) {
                            MessageBox.showPlain(Icon.INFO, "Error", "The End Date must come after the Start Date. Please reenter the End Date. ", ButtonId.OK);
                            return;
                        } else {
                            contractSelectionTable.getItem(object).getItemProperty("priceProtectionEndDate").setValue(massEndDate.getValue());
                            columnName = "PRICE_PROTECTION_END_DATE";
                            value = CommonUtils.DBDate.format(massEndDate.getValue());

                        }
                        break;
                    case "Price Tolerance Type":
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty("priceToleranceType").setValue(tempDTO);
                        columnName = "PRICE_TOLERANCE_TYPE";
                        value = tempDTO.getId();
                        break;
                    case "Price Tolerance":
                        textValue = massUpdateText.getValue();
                        contractSelectionTable.getItem(object).getItemProperty("priceTolerance").setValue(textValue);
                        columnName = "PRICE_TOLERANCE";
                        value = textValue;
                        break;
                    case "Price Tolerance Frequency":
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty("priceToleranceFrequency").setValue(tempDTO);
                        columnName = "PRICE_TOLERANCE_FREQUENCY";
                        value = tempDTO.getId();
                        break;
                    case "Price Tolerance Interval":
                        tempDTO = (HelperDTO) massUpdateValue.getValue();
                        contractSelectionTable.getItem(object).getItemProperty("priceToleranceInterval").setValue(tempDTO);
                        columnName = "PRICE_TOLERANCE_INTERVAL";
                        value = tempDTO.getId();
                        break;
                    case "Base Price":
                        textValue = massUpdateText.getValue();
                        contractSelectionTable.getItem(object).getItemProperty("basePrice").setValue(textValue);
                        columnName = "BASE_PRICE";
                        value = textValue;
                        break;
                    case "RS Start Date":
                        contractSelectionTable.getItem(object).getItemProperty("RSStartDate").setValue(massStartDate.getValue());
                        columnName = "ITEM_REBATE_START_DATE";
                        value = CommonUtils.DBDate.format(massStartDate.getValue());
                        break;
                    case "RS End Date":
                        tempDdate = dto.getRSStartDate();
                        if (massEndDate.getValue().before(tempDdate)) {
                            MessageBox.showPlain(Icon.INFO, "Error", "The End Date must come after the Start Date. Please reenter the End Date. ", ButtonId.OK);
                            return;
                        } else {
                            contractSelectionTable.getItem(object).getItemProperty("RSEndDate").setValue(massEndDate.getValue());
                            columnName = "ITEM_REBATE_END_DATE";
                            value = CommonUtils.DBDate.format(massEndDate.getValue());

                        }
                        break;
                    case "Formula ID":

                        FormulaDTO formulaDto = (FormulaDTO) massUpdateText.getData();
                        textValue = String.valueOf(formulaDto.getFormulaNo());
                        contractSelectionTable.getItem(object).getItemProperty("formulaId").setValue(textValue);
                        columnName = "FORMULA_ID";
                        value = formulaDto.getFormulaSid();
                        break;
                    case "Rebate Plan":
                        massUpdateText.addStyleName("searchicon");
                        ComponentLookUpDTO rebateDto = (ComponentLookUpDTO) massUpdateText.getData();
                        textValue = rebateDto.getComponentNo();
                        contractSelectionTable.getItem(object).getItemProperty("rebatePlan").setValue(textValue);
                        columnName = "REBATE_PLAN_SYSTEM_ID";
                        value = rebateDto.getComponentSid();
                        break;
                    case "Formula Method ID":
                        textValue = massUpdateText.getValue();
                        contractSelectionTable.getItem(object).getItemProperty("formulaMethodId").setValue(textValue);
                        columnName = "FORMULA_METHOD_ID";
                        value = textValue;
                        break;
                    case "Rebate Amount":
                        textValue = massUpdateText.getValue();
                        contractSelectionTable.getItem(object).getItemProperty("rebateAmount").setValue(textValue);
                        columnName = "REBATE_AMOUNT";
                        value = textValue;
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
                Object obj[] = (Object[]) list.get(i);
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
        comboToTableMap.put("Status", ConstantsUtil.STATUS);
        comboToTableMap.put("Start Date", ConstantsUtil.ITEM_START_DATE);
        comboToTableMap.put("End Date", ConstantsUtil.ITEM_END_DATE);
        comboToTableMap.put("CP Start Date", "cpStartDate");
        comboToTableMap.put("CP End Date", "cpEndDate");
        comboToTableMap.put("Contract Price", "contractPrice");
        comboToTableMap.put("Price", "price");
        comboToTableMap.put("Price Protection Start Date", "priceProtectionStartDate");
        comboToTableMap.put("Price Protection End Date", "priceProtectionEndDate");
        comboToTableMap.put("Price Tolerance", "priceTolerance");
        comboToTableMap.put("Price Tolerance Type", "priceToleranceType");
        comboToTableMap.put("Price Tolerance Frequency", "priceToleranceFrequency");
        comboToTableMap.put("Price Tolerance Interval", "priceToleranceInterval");
        comboToTableMap.put("Base Price", "basePrice");
        comboToTableMap.put("RS Start Date", "RSStartDate");
        comboToTableMap.put("RS End Date", "RSEndDate");
        comboToTableMap.put("Formula ID", "formulaId");
        comboToTableMap.put("Rebate Plan", "rebatePlan");
        comboToTableMap.put("Formula Method ID", "formulaMethodId");
        comboToTableMap.put("Rebate Amount", "rebateAmount");
    }

    private void loadFieldAndPropertyMap() {
        fieldAndPropertyMap.put("ITEM_STATUS", ConstantsUtil.STATUS);
        fieldAndPropertyMap.put("START_DATE", ConstantsUtil.ITEM_START_DATE);
        fieldAndPropertyMap.put("END_DATE", ConstantsUtil.ITEM_END_DATE);
        fieldAndPropertyMap.put("CONTRACT_PRICE_START_DATE", "cpStartDate");
        fieldAndPropertyMap.put("CONTRACT_PRICE_END_DATE", "cpEndDate");
        fieldAndPropertyMap.put("CONTRACT_PRICE", "contractPrice");
        fieldAndPropertyMap.put("PRICE", "price");
        fieldAndPropertyMap.put("PRICE_PROTECTION_START_DATE", "priceProtectionStartDate");
        fieldAndPropertyMap.put("PRICE_PROTECTION_END_DATE", "priceProtectionEndDate");
        fieldAndPropertyMap.put("PRICE_TOLERANCE", "priceTolerance");
        fieldAndPropertyMap.put("PRICE_TOLERANCE_TYPE", "priceToleranceType");
        fieldAndPropertyMap.put("PRICE_TOLERANCE_FREQUENCY", "priceToleranceFrequency");
        fieldAndPropertyMap.put("PRICE_TOLERANCE_INTERVAL", "priceToleranceInterval");
        fieldAndPropertyMap.put("BASE_PRICE", "basePrice");
        fieldAndPropertyMap.put("ITEM_REBATE_START_DATE", "RSStartDate");
        fieldAndPropertyMap.put("ITEM_REBATE_END_DATE", "RSEndDate");
        fieldAndPropertyMap.put("FORMULA_ID", "formulaId");
        fieldAndPropertyMap.put("REBATE_PLAN_SYSTEM_ID", "rebatePlan");
        fieldAndPropertyMap.put("FORMULA_METHOD_ID", "formulaMethodId");
        fieldAndPropertyMap.put("REBATE_AMOUNT", "rebateAmount");
    }

    private void LoadTempToTableMap() {
        tempTableMap.put("Status", "ITEM_STATUS");
        tempTableMap.put("Start Date", "START_DATE");
        tempTableMap.put("End Date", "END_DATE");
        tempTableMap.put("CP Start Date", "CONTRACT_PRICE_START_DATE");
        tempTableMap.put("CP End Date", "CONTRACT_PRICE_END_DATE");
        tempTableMap.put("Contract Price", "CONTRACT_PRICE");
        tempTableMap.put("Price", "PRICE");
        tempTableMap.put("Price Protection Start Date", "PRICE_PROTECTION_START_DATE");
        tempTableMap.put("Price Protection End Date", "PRICE_PROTECTION_END_DATE");
        tempTableMap.put("Price Tolerance", "PRICE_TOLERANCE");
        tempTableMap.put("Price Tolerance Type", "PRICE_TOLERANCE_TYPE");
        tempTableMap.put("Price Tolerance Frequency", "PRICE_TOLERANCE_FREQUENCY");
        tempTableMap.put("Price Tolerance Interval", "PRICE_TOLERANCE_INTERVAL");
        tempTableMap.put("Base Price", "BASE_PRICE");
        tempTableMap.put("RS Start Date", "ITEM_REBATE_START_DATE");
        tempTableMap.put("RS End Date", "ITEM_REBATE_END_DATE");
        tempTableMap.put("Formula ID", "FORMULA_ID");
        tempTableMap.put("Rebate Plan", "REBATE_PLAN_SYSTEM_ID");
        tempTableMap.put("Formula Method ID", "FORMULA_METHOD_ID");
        tempTableMap.put("Rebate Amount", "REBATE_AMOUNT");
    }

    private void loadStartEndDateMap() {
        startDateEndDateMap.put("End Date", "START_DATE");
        startDateEndDateMap.put("Start Date", "END_DATE");
        startDateEndDateMap.put("CP End Date", "CONTRACT_PRICE_START_DATE");
        startDateEndDateMap.put("CP Start Date", "CONTRACT_PRICE_END_DATE");
        startDateEndDateMap.put("Price Protection End Date", "PRICE_PROTECTION_START_DATE");
        startDateEndDateMap.put("Price Protection Start Date", "PRICE_PROTECTION_END_DATE");
        startDateEndDateMap.put("RS End Date", "ITEM_REBATE_START_DATE");
        startDateEndDateMap.put("RS Start Date", "ITEM_REBATE_END_DATE");
    }

    public Boolean multipleEndDateCheck(final Object massUpdate) {
        Boolean multipleDateCheck = Boolean.TRUE;
        if (massUpdate != null && massUpdateString.contains("Date")) {
            List input = new ArrayList();
            String queryMax = StringUtils.EMPTY;
            Boolean isStartDate = Boolean.TRUE;
            if (massUpdateString.contains("End Date")) {
                isStartDate = Boolean.FALSE;
                queryMax = "MIN(?)";
            } else if (massUpdateString.contains("Start Date")) {
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
            } else if (isStartDate) {
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

    
}
