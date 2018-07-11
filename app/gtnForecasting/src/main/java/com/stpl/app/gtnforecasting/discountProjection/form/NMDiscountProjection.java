package com.stpl.app.gtnforecasting.discountProjection.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.common.AppDataUtils;
import com.stpl.app.gtnforecasting.abstractforecast.ForecastDiscountProjection;
import com.stpl.app.gtnforecasting.discountProjection.logic.DiscountQueryBuilder;
import static com.stpl.app.gtnforecasting.discountProjection.logic.DiscountQueryBuilder.AND_YEAR_EQUAL;
import static com.stpl.app.gtnforecasting.discountProjection.logic.DiscountQueryBuilder.NINE_LEVELS_DED;
import com.stpl.app.gtnforecasting.discountProjection.logic.NMDiscountExcelLogic;
import com.stpl.app.gtnforecasting.discountProjection.logic.NMDiscountProjectionLogic;
import com.stpl.app.gtnforecasting.discountProjection.logic.tableLogic.NMDiscountTableLoadLogic;
import com.stpl.app.gtnforecasting.dto.DiscountProjectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SaveDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.DiscountProjectionLogic;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.projectionvariance.logic.NMProjectionVarianceLogic;
import com.stpl.app.gtnforecasting.projectionvariance.logic.PVCommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.ui.form.lookups.AlternateHistory;
import com.stpl.app.gtnforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.gtnforecasting.ui.form.lookups.DiscountSelection;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.ChangeCustomMenuBarValueUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import static com.stpl.app.gtnforecasting.utils.CommonUtil.stringNullCheck;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.CustomExcelNM;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import com.stpl.app.gtnforecasting.utils.TabNameUtil;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.ButtonConstants.ALL;
import static com.stpl.app.utils.Constants.ButtonConstants.SELECT;
import static com.stpl.app.utils.Constants.CalendarConstants.CURRENT_YEAR;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_EDIT;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import static com.stpl.app.utils.Constants.CommonConstants.AVERAGE;
import static com.stpl.app.utils.Constants.CommonConstants.CONTRACT_DETAILS;
import static com.stpl.app.utils.Constants.CommonConstants.PER_EX_FACTORY_SALES;
import static com.stpl.app.utils.Constants.CommonConstants.ROLLING_ANNUAL_TREND;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.CommonConstantsForChannels.DISABLE;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOM;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOMER;
import static com.stpl.app.utils.Constants.LabelConstants.DESCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT_AMT;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT_PROJ;
import static com.stpl.app.utils.Constants.LabelConstants.DISCOUNT_RATE;
import static com.stpl.app.utils.Constants.LabelConstants.DOLLAR;
import static com.stpl.app.utils.Constants.LabelConstants.ENABLE;
import static com.stpl.app.utils.Constants.LabelConstants.GROWTH;
import static com.stpl.app.utils.Constants.LabelConstants.INCREMENTAL;
import static com.stpl.app.utils.Constants.LabelConstants.OVERRIDE;
import static com.stpl.app.utils.Constants.LabelConstants.PERCENT;
import static com.stpl.app.utils.Constants.LabelConstants.PERCENTAGE;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM_CATEGORY;
import static com.stpl.app.utils.Constants.LabelConstants.REBATE_PER_UNIT;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_DISCOUNT_PROJECTION;
import com.stpl.app.utils.CumulativeCalculationUtils;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.stpl.ifs.ui.extfilteringtable.ExtPagedTreeTable;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import static com.stpl.ifs.util.constants.GlobalConstants.getCommercialConstant;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.themes.Reindeer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.MenuItemDTO;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtCustomTable.ColumnCheckListener;
import org.asi.ui.extfilteringtable.ExtCustomTreeTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author shyam.d
 */
public class NMDiscountProjection extends ForecastDiscountProjection {

    
    private final StplSecurity stplSecurity = new StplSecurity();
    private ExtTreeContainer<DiscountProjectionDTO> resultBeanContainer = new ExtTreeContainer<>(DiscountProjectionDTO.class,
            ExtContainer.DataStructureMode.MAP);
    private GtnSmallHashMap manualEntryMap = new GtnSmallHashMap();
    private GtnSmallHashMap multipleVariableCheckMap = new GtnSmallHashMap();
    /* Discount Bean */
    protected BeanItemContainer<String> programBean = new BeanItemContainer<>(String.class);
    /* To store the current hierarchy */
    private List<Leveldto> currentHierarchy = new ArrayList<>();
    public static final String ANULL = "null";
    public static final String DEDUCTION = "DEDUCTION";
    public static final String DISCOUNT = "Discount";
    public static final String PRODUCT1 = "PRODUCT";
    public static final String CUSTOMER1 = "CUSTOMER";
    public static final String STRING_ONE = "1";
    public static final String AMOUNT_TWO_DECIMAL = "amountTwoDecimal";
    /* To enable or disable level filter listener */
    private boolean enableLevelFilterListener = true;
    /* The bean used to load the Mass Update - value Ddlb */
    private BeanItemContainer<String> valueDdlbBean = new BeanItemContainer<>(String.class);
    /* To hold the selected discounts in program selection lookup */
    private List<String> discountProgramsList = new LinkedList<>();
    /* To hold the selected program from the program selection combo box */
    private List<String> programSelectionList = new ArrayList<>();
    /* The hierarchy indicator to indicate whether customer or Product */
    private String hierarchyIndicator = StringUtils.EMPTY;
    /* Non Mandated Logic */
    private final DiscountProjectionLogic discountProjectionLogic = new DiscountProjectionLogic();
    /* table Logic to load the table Data */
    private NMDiscountTableLoadLogic tableLogic;
    /* To hold the selections on generate button click. */
    protected ProjectionSelectionDTO projectionSelection = new ProjectionSelectionDTO();
    /* The custom id. */
    private int customId = 0;
    /* To check whether list view is generated or not */
    private boolean isListviewGenerated = true;
    private Set<String> hierarchyListForCheckRecord = new HashSet<>();

    private boolean isGroupUpdatedManually = false;
    /* The custom id to select. */
    private int customIdToSelect = 0;
    /* The Right Header Dto */
    private CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    /* The Right Header Dto */
    private CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    /* The Excel header Dto (Right Header) */
    private CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO excelHeaderLeft = new CustomTableHeaderDTO();
    /* The Results table */
    private FreezePagedTreeTable resultsTable;
    /* Group filter selected value */
    private String userGroup = StringUtils.EMPTY;
    /* String to be stored during focus of List View text field */
    private String focusValue = StringUtils.EMPTY;
    /* String to be stored during blur of List View text field */
    private String blurValue = StringUtils.EMPTY;
    /* Discount Names to be used in Projection results */
    private List<String> discountNamesList = new ArrayList<>();
    /* Discount No to be used in Projection results */
    private List<String> discountNoList = new ArrayList<>();

    /* Start and End Periods to be loaded */
    private List<Integer> startAndEndPeriods = new ArrayList<>();
    /* Data Format Converter */
    private DataFormatConverter percentFormat = new DataFormatConverter("#,##0.000", DataFormatConverter.INDICATOR_PERCENT);
    private DataFormatConverter priceFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_DOLLAR);
    private DataFormatConverter priceConversionFormat = new DataFormatConverter("#,##0", DataFormatConverter.INDICATOR_DOLLAR);
    private DataFormatConverter priceConversionTwoDecimalFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_DOLLAR);
    /**
     * To store the details of the checked double header with its corresponding
     * Triple header and History, Projected values
     */
    private Map<String, Map<String, List<String>>> tripleHeaderForCheckedDoubleHeader = new HashMap<>();
    private Map<String, Map<String, List<String>>> tripleHeaderForCheckedDoubleHeaderCustom = new HashMap<>();
    /* To store the name of the discounts selected in the Triple header */
    private List<Object> checkedDiscountsPropertyIds = new ArrayList<>();
    /* List to have the items to be saved */
    private List<SaveDTO> saveList = new ArrayList<>();
    /* To store the hierarchy numbers to refresh in table */
    private Set<String> refreshTableHierarchySet = new HashSet<>();
    /* To store the custom View */
    private List<CustomViewMaster> customViewList = new ArrayList<>();
    /* The Excel container */
    private ExtTreeContainer<DiscountProjectionDTO> excelContainer = new ExtTreeContainer<>(DiscountProjectionDTO.class,
            ExtContainer.DataStructureMode.MAP);
    private boolean errorFlag = false;
    private Map<String, List<String>> checkedDoubleHeaders = new HashMap<>();

    @UiField("endPeriodForecastTab")
    private ComboBox endPeriodForecastTab;
    /**
     * The startPeriodForecastTab ComboBox.
     */
    @UiField("startPeriodForecastTab")
    private ComboBox startPeriodForecastTab;

    @UiField("gridlay")
    private GridLayout gridlay;
    private static final String PLEASE_SELECT_A_HISTORIC_ALERT = "Please select a Historic Period for each discount selected.";
    private List<String> checkedList;

    private String calcBase = StringUtils.EMPTY;
    private GtnSmallHashMap radioMap = new GtnSmallHashMap();
    private GtnSmallHashMap checkBoxMap = new GtnSmallHashMap();
    private GtnSmallHashMap ccpsCountForMassUpdate = new GtnSmallHashMap();
    private int baselineFlag = 0;
    private int ccpsCount = 0;
    public static final String CUSTOM_VIEW = "CUSTOM";
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NMDiscountProjection.class);

    private boolean isDiscountGenerated;
    private boolean isRateUpdatedManually = false;
    private boolean isRPUUpdatedManually = false;
    private boolean isAmountUpdatedManually = false;
    private BeanItemContainer<String> tableGroupDdlbBean = new BeanItemContainer<>(String.class);
    private String actualCCPs = StringUtils.EMPTY;
    private int rsModelSid = 0;
    private int totalccpCount = 0;
    private boolean flag = false;
    protected CommonLogic commonLogic = new CommonLogic();
    private DataSelectionLogic dsLogic = new DataSelectionLogic();
    public static final String SELECT_LEVEL_LABEL = "-Select Level-";
    public static final String SELECT_ALL_LABEL = "Select All";
    public static final String SELECT_VALUES = "-Select Values-";
    public static final String SID = "SID";
    public static final String CAPTION = "CAPTION";
    private static final String DF_LEVEL_NUMBER = "dfLevelNumber";
    private static final String DF_LEVEL_NAME = "dfLevelName";
    private static final String LEVEL_NAME_PROPERTY = "levelName";
    private static final String LEVEL_NUMBER_HEADER = "Level Number";
    private static final String LEVEL_NAME_HEADER = "Level Name";
    private static final String GROUP_PROPERTY_ID = "group";
    public static final String DISCOUNT_PROJECTION_XLS = "Discount_Projection.xls";

    private List<Object> generateDiscountToBeLoaded = new ArrayList<>();
    private List<Object> generateDiscountNamesToBeLoaded = new ArrayList<>();
    private List<Object> generateProductToBeLoaded = new ArrayList<>();
    private List<Object> generateCustomerToBeLoaded = new ArrayList<>();
    private List<String> baselinePeriods = new ArrayList<>();
    private final Map<String, Object> excelParentRecords = new HashMap();
    private boolean isMultipleVariablesUpdated = false;
    private Object[] tempSingleHeaderArray = null;

    private CustomMenuBar.SubMenuCloseListener deductionlistener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            List<CustomMenuBar.CustomMenuItem> itemList = deductionFilterDdlb.getItems();
            if (!itemList.isEmpty()) {
                MenuItemDTO menuItemDto = itemList.get(0).getMenuItem();
                String menuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(deductionFilterValues);
                menuItemDto.setCaption(menuItemValue);
            }
            deductionFilterDdlb.markAsDirty();
            generateDiscountToBeLoaded = commonLogic.getFilterValues(deductionFilterValues).get(SID);
            generateDiscountNamesToBeLoaded = commonLogic.getFilterValues(deductionFilterValues).get(CAPTION);
            projectionSelection.setIsdeductionFirst(!projectionSelection.getDeductionLevelFilter().isEmpty());
            loadCustomerLevelFilter(ANULL.equals(String.valueOf(customerlevelDdlb.getValue())) ? StringUtils.EMPTY
                    : String.valueOf(customerlevelDdlb.getValue()));
            loadProductLevelFilter(ANULL.equals(String.valueOf(productlevelDdlb.getValue())) ? StringUtils.EMPTY
                    : String.valueOf(productlevelDdlb.getValue()));

        }
    };
    private CustomMenuBar.SubMenuCloseListener productlistener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            List<CustomMenuBar.CustomMenuItem> itemList = productFilterDdlb.getItems();
            if (!itemList.isEmpty()) {
                MenuItemDTO menuItemDto = itemList.get(0).getMenuItem();
                String menuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(productFilterValues);
                menuItemDto.setCaption(menuItemValue);
            }
            productFilterDdlb.markAsDirty();
            generateProductToBeLoaded = commonLogic.getFilterValues(productFilterValues).get(SID);
            projectionSelection.setIsproductFirst(!projectionSelection.getProductLevelFilter().isEmpty());
            loadCustomerLevelFilter(ANULL.equals(String.valueOf(customerlevelDdlb.getValue())) ? StringUtils.EMPTY
                    : String.valueOf(customerlevelDdlb.getValue()));
            loadDeductionLevelFilter(ANULL.equals(String.valueOf(deductionlevelDdlb.getValue())) ? StringUtils.EMPTY
                    : String.valueOf(deductionlevelDdlb.getValue()), false);
        }
    };

    private CustomMenuBar.SubMenuCloseListener customerlistener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            List<CustomMenuBar.CustomMenuItem> itemList = customerFilterDdlb.getItems();
            if (!itemList.isEmpty()) {
                String menuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(customerFilterValues);
                MenuItemDTO menuItemDto = itemList.get(0).getMenuItem();
                menuItemDto.setCaption(menuItemValue);
            }
            customerFilterDdlb.markAsDirty();
            generateCustomerToBeLoaded = commonLogic.getFilterValues(customerFilterValues).get(SID);
            projectionSelection.setIscustomerFirst(!projectionSelection.getCustomerLevelFilter().isEmpty());
            loadDeductionLevelFilter(ANULL.equals(String.valueOf(deductionlevelDdlb.getValue())) ? StringUtils.EMPTY
                    : String.valueOf(deductionlevelDdlb.getValue()), false);
            loadProductLevelFilter(ANULL.equals(String.valueOf(productlevelDdlb.getValue())) ? StringUtils.EMPTY
                    : String.valueOf(productlevelDdlb.getValue()));
        }
    };

    protected CustomMenuBar.SubMenuCloseListener deductionInclusionListener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            List<CustomMenuBar.CustomMenuItem> itemList = deductionInclusionDdlb.getItems();
            if (!itemList.isEmpty()) {
                MenuItemDTO menuItemDto = itemList.get(0).getMenuItem();
                String menuItemValue = ChangeCustomMenuBarValueUtil.getInclusionMenuItemToDisplay(deductionInclusionValues);
                menuItemDto.setCaption(menuItemValue);
            }
            deductionInclusionDdlb.markAsDirty();
        }
    };
    protected CustomMenuBar.SubMenuCloseListener displayFormatListener = new CustomMenuBar.SubMenuCloseListener() {
        @Override
        public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
            List<CustomMenuBar.CustomMenuItem> itemList = displayFormatDdlb.getItems();
            if (!itemList.isEmpty()) {
                MenuItemDTO menuItemDto = itemList.get(0).getMenuItem();
                String menuItemValue = ChangeCustomMenuBarValueUtil.getInclusionMenuItemToDisplay(displayFormatValues);
                menuItemDto.setCaption(menuItemValue);
            }
            displayFormatDdlb.markAsDirty();
        }
    };

    public NMDiscountProjection(SessionDTO session, String screenName) {
        super(session, screenName);
        tableLogic = new NMDiscountTableLoadLogic(this);
        tableLogic.setTempPageLength(NumericConstants.TEN);
        tableLogic.setDataLoad(false);
        resultsTable = new FreezePagedTreeTable(tableLogic);
        tableLogic.setPageLength(NumericConstants.TWENTY); // To be done before
        tableLogic.sinkItemPerPageWithPageLength(false);
        if (flag) {
            configure();
    }
        flag = true;

        if (CommonUtil.isValueEligibleForLoading()) {
            CommonUtil commonUtils = CommonUtil.getInstance();
            projectionSelection.setSessionDTO(session);
            loadCustomerLevel();
            loadProductLevel();
            loadDedutionLevel();
            loadCustomerLevelFilter(StringUtils.EMPTY);
            loadProductLevelFilter(StringUtils.EMPTY);
            loadDeductionLevelFilter(StringUtils.EMPTY, false);
            loadDeductionInclusion();
            loadDisplayFormatDdlb();
            commonUtils.loadConvertionFactorComboBox(conversionFactorDdlb, Constant.CONVERSION_FACTOR);
            deductionlevelDdlb.setValue(Integer.valueOf(session.getDataSelectionDeductionLevel()));

        }
        securityForButton();
        addPropertyValueChangeListeners(frequencyDdlb,view,adjprograms, adjperiods, massCheck, startPeriod,
                levelFilterDdlb);
    }

    /**
     * To get the content of this tab from Non Mandated form
     *
     * @return
     */
    public void getContent() {
        LOGGER.debug("Inside getContent= {} ", session.getAction());
        configureFeildsForNm();
        loadDeductionLevelFilter(session.getDataSelectionDeductionLevel(), false);
       frequencyDdlb.setValue(session.getDsFrequency());
       deductionlevelDdlb.setValue(Integer.valueOf(session.getDataSelectionDeductionLevel()));
        Optional.ofNullable(deductionFilterValues.getChildren()).ifPresent(child-> {
            child.get(1).setChecked(true);
            String deductionMenuItemValue = child.get(1).getMenuItem().getCaption();
            ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(deductionFilterDdlb, deductionMenuItemValue);
        });        
        generateDiscountToBeLoaded = commonLogic.getFilterValues(deductionFilterValues).get(SID);
        generateDiscountNamesToBeLoaded = commonLogic.getFilterValues(deductionFilterValues).get(CAPTION);
        if (ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
            setDiscountViewOnly();
        }
    }

    /**
     * To get the Tab number
     *
     * @return
     */
    public int getTabNumber() {
        return Constant.FOUR;
    }

    /**
     * To disable things in View Mode
     */
    private void setDiscountViewOnly() {
        type.setEnabled(false);
        basis.setEnabled(false);
        adjustment.setEnabled(false);
        allocMethodology.setEnabled(false);
        adjprograms.setEnabled(false);
        adjperiods.setEnabled(false);
        adjustBtn.setEnabled(false);
        massCheck.select(DISABLE.getConstant());
        massCheck.setEnabled(false);
        methodologyDdlb.setEnabled(false);
        calculateBtn.setEnabled(false);
        resultsTable.getLeftFreezeAsTable().setEditable(true);
        resultsTable.getRightFreezeAsTable().setEditable(false);
    }

    void configureFeildsForNm() {
        Utility.loadHierarchyList(session);
        frequency.setCaption("Frequency");
        frequency.setWidth("80px");
        frequencyDdlb.setImmediate(true);
        frequencyDdlb.select(dataSelectionDto.getFrequency());
        actualsProjections.setCaption("Actuals/Projections:");
        actualsProjections.setWidth("127px");
        actualsProjs.setEnabled(true);
        actualsProjs.addStyleName(Constant.HORIZONTAL);
        projectionPeriodOrder.setCaption("Projection Period Order:");
        projectionPeriodOrder.setWidth("165px");
        periodOrder.setEnabled(true);
        periodOrder.setStyleName(Constant.HORIZONTAL);
        history.setCaption("History:");
        history.setStyleName("labelresultalign");
        historyDdlb.setEnabled(true);
        historyDdlb.setStyleName("comboxsize");
        historyDdlb.setNullSelectionAllowed(true);
        variablesLb.setCaption("Variables:");
        variablesLb.setStyleName("labelresultalign");
        variables.setMultiSelect(true);
        variables.setEnabled(true);
        variables.setStyleName(Constant.HORIZONTAL);
        projTypeLb.setCaption("ProjectionType:");
        projTypeLb.setWidth("110");
        projType.setImmediate(true);
        projType.setEnabled(true);
        discTypeLb.setCaption("Discount Type:");
        discTypeLb.setWidth("110");
        discType.setMultiSelect(false);
        discType.setImmediate(true);
        discType.setEnabled(true);
        gridlay.removeAllComponents();
        gridlay.addComponent(frequency);
        gridlay.addComponent(frequencyDdlb);
        gridlay.addComponent(actualsProjections);
        gridlay.addComponent(actualsProjs);
        gridlay.addComponent(projectionPeriodOrder);
        gridlay.addComponent(periodOrder);
        if (CommonUtil.isValueEligibleForLoading()) {
            gridlay.addComponent(uomLb);
            uomLb.setWidth("120px");
            gridlay.addComponent(uomDdlb);
            gridlay.addComponent(displayFormatLabel);
            displayFormatDdlb.setStyleName("custommenumulticheck");
            displayFormatDdlb.setWidth("200px");
            displayFormatLabel.setWidth("120px");
            gridlay.addComponent(displayFormatDdlb);
        } else {
            gridlay.setColumns(6);
        }
        gridlay.addComponent(history, 0, 1);
        gridlay.addComponent(historyDdlb, 1, 1);
        gridlay.addComponent(variablesLb, NumericConstants.TWO, 1);
        gridlay.addComponent(variables, NumericConstants.THREE, 1);
        variables.setWidth("500px");
        gridlay.addComponent(conversionFactor, NumericConstants.FOUR, 1);
        gridlay.addComponent(conversionFactorDdlb, NumericConstants.FIVE, 1);
        conversionFactor.setWidth("121px");
        fieldDdlb.addItem(SELECT_ONE.getConstant());
        fieldDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        fieldDdlb.select(SELECT_ONE.getConstant());
        fieldDdlb.addItem(Constant.GROUPFCAPS);
        fieldDdlb.removeItem(Constant.GROUPFCAPS);
        fieldDdlb.addItem(Constant.DISCOUNT_RATE_LABEL);
        fieldDdlb.addItem("RPU");
        fieldDdlb.addItem(Constant.DISCOUNT_AMOUNT_LABEL);
        fieldDdlb.addItem(Constant.GROWTH);
        massCheck.select(ENABLE.getConstant());
        massCheck.setVisible(false);

        fieldDdlb.setEnabled(true);
        populateBtn.setEnabled(true);
        calculateBtn.setEnabled(true);

        groupFilterDdlb.setVisible(false);
        groupFilterLb.setVisible(false);
        fieldDdlb.setData("fieldDdlb");
        fieldDdlb.addValueChangeListener(propertyVlaueChangeListener);

        programSelectionLookup.addStyleName(Reindeer.BUTTON_LINK);

        programSelection.addItem(SELECT_ONE.getConstant());
        programSelection.setNullSelectionAllowed(true);
        programSelection.setNullSelectionItemId(SELECT_ONE.getConstant());
        programSelection.setValue(SELECT_ONE.getConstant());
        editBtn.setEnabled(false);
        newBtn.setEnabled(false);

        level.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                programSelection.removeAllItems();
                programBean.removeAllItems();
                programSelection.addItem(SELECT_ONE.getConstant());
                programSelection.setNullSelectionAllowed(true);
                programSelection.setNullSelectionItemId(SELECT_ONE.getConstant());
                programSelection.setValue(null);
                discountProgramsList.clear();
                programSelectionList.clear();
            }
        });

        final BeanItemContainer<String> yearBean = new BeanItemContainer<>(String.class);
        yearSelection.setNullSelectionAllowed(true);
        yearSelection.setNullSelectionItemId(SELECT_ONE.getConstant());
        yearBean.addAll(loadYearSelection());
        yearSelection.setContainerDataSource(yearBean);

        allocMethodology.addItem(SELECT_ONE.getConstant());
        allocMethodology.setNullSelectionItemId(SELECT_ONE.getConstant());

        CommonUtil.getInstance().loadOnDemandCombobox(allocMethodology, "ADJUSTMENT_METHODOLOGIES");

        valueDdlb.setEnabled(true);
        valueDdlb.addItem(SELECT_ONE.getConstant());
        valueDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        valueDdlb.setNewItemsAllowed(true);
        valueDdlb.setNullSelectionAllowed(false);
        valueDdlb.setContainerDataSource(valueDdlbBean);

        value.addStyleName("txtRightAlign");
        
        boolean isEnabled = Utility.customEnableForRelationFromDS(session.getCustomDeductionRelationShipSid());
        view.setItemEnabled(Constant.CUSTOM_LABEL, isEnabled);
        
        methodologyDdlb.addItem(SELECT_ONE.getConstant());
        methodologyDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        loadMethodologyDdlb(methodologyDdlb);
        if (CommonUtil.isValueEligibleForLoading()) {
            commonLogic.loadUnitOfMeasureDdlb(uomDdlb, session);
        }

        methodologyDdlb.select(SELECT_ONE.getConstant());
        startPeriod.setEnabled(true);
        startPeriod.addItem(SELECT_ONE.getConstant());
        startPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
        startPeriod.setValue(SELECT_ONE.getConstant());
        startPeriod.setContainerDataSource(startPeriodBean);
        endPeriod.setEnabled(true);
        endPeriod.addItem(SELECT_ONE.getConstant());
        endPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
        endPeriod.setValue(SELECT_ONE.getConstant());
        endPeriod.setContainerDataSource(endPeriodBean);

        variables.addStyleName(Constant.HORIZONTAL);
        variables.addItem(DISCOUNT_RATE.getConstant());
        variables.addItem(REBATE_PER_UNIT.getConstant());
        variables.addItem(DISCOUNT_AMT.getConstant());
        variables.addItem(GROWTH.getConstant());
        variables.focus();
        variables.setImmediate(true);
        projectionSelection.setViewOption(Constant.CUSTOMER);
        if (!ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction())){
        variables.select(DISCOUNT_AMT.getConstant());     
        projectionSelection.setdPVariablesList(Arrays.asList(new String[]{ DISCOUNT_AMT.getConstant()}));
        }
        if(session.getCustomDeductionRelationShipSid()==0){
           viewDdlb.setValue(null);
           newBtn.setEnabled(false); 
        }
        else{
        view.setItemEnabled(Constant.CUSTOM_LABEL, true);
        }

        startPeriodForecastTab.addItem(SELECT_ONE.getConstant());
        startPeriodForecastTab.setNullSelectionItemId(SELECT_ONE.getConstant());
        startPeriodForecastTab.setValue(SELECT_ONE.getConstant());
        startPeriodForecastTab.setContainerDataSource(forecaststartBean);

        endPeriodForecastTab.addItem(SELECT_ONE.getConstant());
        endPeriodForecastTab.setNullSelectionItemId(SELECT_ONE.getConstant());
        endPeriodForecastTab.setValue(SELECT_ONE.getConstant());
        endPeriodForecastTab.setContainerDataSource(forecastendBean);

        groupFilterDdlb.addValueChangeListener(groupFilterDdlbValueChange);

        currentHierarchy = session.getCustomerHierarchyList();
        hierarchyIndicator = "C";

        tableLogic.setTreeNodeMultiClick(false);
        tableLogic.setDataLoad(false);

        initializeTable();
        addResultTable();
        createLeftHeader();
        resultBeanContainer.setColumnProperties(leftHeader.getProperties());

        tableLogic.setContainerDataSource(resultBeanContainer);
        LOGGER.debug(" session Action= {} ", session.getAction());

        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction())
                || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
            if (loadSelections(false) && !projectionSelection
                    .getDeductionLevelFilter().isEmpty()) {
                generateBtnClickLogic(BooleanConstant.getFalseFlag());
            } else {
                configureLeftTable();
                loadEmptyTable();
            }
        } else {
            configureLeftTable();
            loadEmptyTable();
        }
        altHistoryBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (PROGRAM.getConstant().equals(level.getValue())) {
                    try {

                        if (validateForAlternateHistory()) {

                            session.setFrequency(projectionSelection.getFrequency());

                            String selectedRsName = resultsTable.getRightFreezeAsTable()
                                    .getTripleHeaderColumnHeader(checkedDiscountsPropertyIds.get(0));
                            selectedRsName = selectedRsName.replace(" ", StringUtils.EMPTY);
                            session.setForecastName(TabNameUtil.DISCOUNT_PROJECTION);
                            AlternateHistory alternateContractLookup = new AlternateHistory(session,
                                    projectionSelection, tableLogic, actualCCPs, rsModelSid, selectedRsName);
                            getUI().addWindow(alternateContractLookup);
                        } else {
                        }
                    } catch (IllegalArgumentException | NullPointerException ex) {
                        LOGGER.error(ex.getMessage());
                    }
                } else {

                    NotificationUtils.getAlertNotification("Invalid Levl Selection",
                            "Please Select Level as Program and Generate to proceed Alternate History.");

                }
            }

        });

        LOGGER.debug("Exiting configureFields ");

    }

    private ColumnCheckListener checkListener = new ColumnCheckListener() {
        @Override
        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
            tableLogic.setCheckAll(event.isChecked());
            checkClearAll(event.isChecked());
        }
    };

    private FocusListener focusListener = new FocusListener() {
        @Override
        public void focus(FieldEvents.FocusEvent event) {
            Object[] obj = (Object[]) ((AbstractComponent) event.getComponent()).getData();
            if ("left".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))) {
                focusValue = String
                        .valueOf(tableLogic.getContainerDataSource().getContainerProperty(obj[0], obj[1]).getValue())
                        .trim();
            } else {
                focusValue = String
                        .valueOf(tableLogic.getContainerDataSource().getContainerProperty(obj[0], obj[1]).getValue())
                        .replace(" ", StringUtils.EMPTY);
            }
            LOGGER.debug(" group focus Value= {}", focusValue);
        }
    };

    private BlurListener blurListener = new BlurListener() {
        @Override
        public void blur(FieldEvents.BlurEvent event) {
            Object[] obj = (Object[]) ((AbstractComponent) event.getComponent()).getData();
            session.setFunctionMode("R");
            if ("left".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))) {
                blurValue = String
                        .valueOf(tableLogic.getContainerDataSource().getContainerProperty(obj[0], obj[1]).getValue())
                        .trim();
                String deductionHierarchy = DiscountQueryBuilder.getRSDiscountHierarchyNo(
                        projectionSelection.getDeductionLevelFilter(), session, session.getSelectedDeductionLevelNo());
                boolean isCustomHierarchy = CommonUtil.isValueEligibleForLoading()
                        ? Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator)
                        : Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant()
                                .equals(hierarchyIndicator);
                LOGGER.debug(" blur Value= {}", blurValue);
                if (!focusValue.equals(blurValue)) {
                    DiscountProjectionDTO dto = (DiscountProjectionDTO) obj[0];
                    dto.addStringProperties(obj[1], blurValue);
                    LOGGER.debug(" hierarchyNo= {} ", dto.getHierarchyNo());
                    boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
                    List<String> customViewDetails = new ArrayList<>();
                    if (isCustomHierarchy) {
                        String customerLevelNo;
                        String productLevelNo;
                        String customerHierarchyNo;
                        String productHierachyNo;

                        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                            customerHierarchyNo = dto.getHierarchyNo();
                            productHierachyNo = dto.getProductHierarchyNo();
                        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                            customerHierarchyNo = dto.getCustomerHierarchyNo();
                            productHierachyNo = dto.getHierarchyNo();
                        } else {
                            customerHierarchyNo = dto.getCustomerHierarchyNo();
                            productHierachyNo = dto.getProductHierarchyNo();
                        }

                        productLevelNo = Constant.PERCENT;
                        customerLevelNo = Constant.PERCENT;
                        LOGGER.debug(" EXCEL - Custom hierarchy --- \n customId= {} ", customId);
                        LOGGER.debug(" EXCEL - customerLevelNo= {} ", customerLevelNo);
                        LOGGER.debug(" EXCEL - customerHierarchyNo= {} ", customerHierarchyNo);
                        LOGGER.debug(" EXCEL - productLevelNo= {} ", productLevelNo);
                        LOGGER.debug(" EXCEL - productHierarchyNo= {} ", productHierachyNo);
                        customViewDetails.add(StringUtils.EMPTY + customId);
                        customViewDetails.add(customerLevelNo);
                        customViewDetails.add(customerHierarchyNo);
                        customViewDetails.add(productLevelNo);
                        customViewDetails.add(productHierachyNo);
                        customViewDetails.add(session.getCustRelationshipBuilderSid());
                        customViewDetails.add(session.getProdRelationshipBuilderSid());
                    }
                    if (discountProjectionLogic.saveGroupValues(session, dto.getHierarchyNo(), blurValue, isProgram,
                            projectionSelection.getDiscountProgramsList(), deductionHierarchy, hierarchyIndicator)) {
                        if (!userGroup.isEmpty()) {
                            isGroupUpdatedManually = true;
                        }
                        LOGGER.debug("saved= {}", blurValue);
                    }
                    groupFilterDdlb.removeValueChangeListener(groupFilterDdlbValueChange);
                    groupFilterDdlb.addItem(blurValue);
                    valueDdlbBean.addItem(blurValue);
                    tableGroupDdlbBean.addItem(blurValue);
                    groupFilterDdlb.addValueChangeListener(groupFilterDdlbValueChange);
                }
            } else {
                blurValue = String
                        .valueOf(tableLogic.getContainerDataSource().getContainerProperty(obj[0], obj[1]).getValue())
                        .replace(" ", StringUtils.EMPTY);
                boolean isCustomHierarchy = CommonUtil.isValueEligibleForLoading()
                        ? Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator)
                        : Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant()
                                .equals(hierarchyIndicator);
                if (blurValue.isEmpty()) {
                    blurValue = Constant.DASH;
                }
                LOGGER.debug(" blur Value= {}", blurValue);
                if (!focusValue.equals(blurValue)) {
                    final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
                    DiscountProjectionDTO dto = (DiscountProjectionDTO) obj[0];
                    dto.addStringProperties(obj[1], blurValue);
                    SaveDTO saveDto = new SaveDTO();
                    String discountName = rightTable.getTripleHeaderColumnHeader(
                            rightTable.getTripleHeaderForSingleHeader(String.valueOf(obj[1])));
                    String periodToUpdate = rightTable.getDoubleHeaderColumnHeader(
                            rightTable.getDoubleHeaderForSingleHeader(String.valueOf(obj[1])));
                    String period = periodToUpdate;
                    String yearToUpdate = periodToUpdate.substring(periodToUpdate.length() - NumericConstants.FOUR);
                    periodToUpdate = periodToUpdate.replace(yearToUpdate, StringUtils.EMPTY);
                    periodToUpdate = CommonUtils.replaceIntegerForMonth(periodToUpdate);
                    periodToUpdate = periodToUpdate.replace(Constant.Q, StringUtils.EMPTY)
                            .replace(Constant.S, StringUtils.EMPTY).replace(" ", StringUtils.EMPTY);
                    String refreshName = StringUtils.EMPTY;
                    String property = String.valueOf(obj[1]);
                    if (property.contains(Constant.PROJECTED_RATE)) {
                        refreshName = Constant.RATE;
                        isRateUpdatedManually = true;
                    } else if (property.contains(Constant.PROJECTEDRPU)) {
                        refreshName = "RPU";
                        isRPUUpdatedManually = true;
                    } else if (property.contains("ProjectedAmount")) {
                        refreshName = "AMOUNT";
                        isAmountUpdatedManually = true;
                        double doubleVal = Double.parseDouble(blurValue);
                        doubleVal = CommonUtil.getConversionFormattedMultipleValue(projectionSelection, doubleVal);
                        ccpsCount = dto.getCcpCount();
                        double finalValue = doubleVal;
                        blurValue = String.valueOf(finalValue);
                        if (blurValue.contains("E")) {
                            blurValue = blurValue.substring(0, blurValue.lastIndexOf('E'));
                        }
                    } else if (property.contains(Constant.GROWTH)) {
                        refreshName = "GROWTH";
                    }
                    saveDto.setPeriodNo(isInteger(periodToUpdate) ? Integer.parseInt(periodToUpdate) : 0);
                    saveDto.setYear(Integer.parseInt(yearToUpdate));
                    saveDto.setValue(blurValue);
                    saveDto.setHirarechyNo(dto.getHierarchyNo());
                    saveDto.setTreeLevelNo(dto.getTreeLevelNo());
                    saveDto.setCustomerHierarchyNo(dto.getCustomerHierarchyNo());
                    saveDto.setProductHierarchyNo(dto.getProductHierarchyNo());
                    saveDto.setDeductionHierarchyNo(dto.getDeductionHierarchyNo());
                    saveDto.setHierarchyIndicator(dto.getHierarchyIndicator());
                    boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
                    if (isCustomHierarchy && CommonUtil.isValueEligibleForLoading()) {
                        saveDto.setDiscountName(
                                CommonUtils.CollectionToString(session.getSelectedRsForCustom(), false));
                    } else {
                        if (CommonUtil.isValueEligibleForLoading()) {
                            saveDto.setDiscountName(rightTable.getTripleHeaderForSingleHeader(String.valueOf(obj[1])));
                        } else {
                            saveDto.setDiscountName(isProgram
                                    ? rightTable.getTripleHeaderForSingleHeader(String.valueOf(obj[1])) : discountName);
                        }
                    }
                    saveDto.setRefreshName(refreshName);
                    refreshTableHierarchySet.add(tableLogic.getTreeLevelonCurrentPage(obj[0]));
                    saveList.add(saveDto);

                    try {
                        multiVariablesCheck(period, refreshName);
                        if (!refreshName.equals("GROWTH")) {
                            checkMultiVariables(period + "~" + dto.getHierarchyNo(), refreshName);
                        }
                        if (saveList.size() == 1) {
                            CommonLogic.viewProceduresCompletionCheckDiscount(session);
                            saveDiscountProjectionListview();
                            Object[] orderedArg = {session.getProjectionId(), session.getUserId(),
                                session.getSessionId()};
                            CommonLogic.callProcedure("PRC_NM_DISCOUNT_REFRESH", orderedArg);
                            String startPeriod = getPeriodSid(period, projectionSelection.getFrequency(), "Min");
                            String endPeriod = getPeriodSid(period, projectionSelection.getFrequency(), "Max");
                            new DataSelectionLogic().callViewInsertProceduresThread(session, Constant.DISCOUNT3, startPeriod, endPeriod, "");
                        }
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                        try {
                            AbstractNotificationUtils.getErrorNotification(AppDataUtils.getValueForKeyFromProperty(Constants.getCommercialForecastingMultipleVariablesHeader()),
                                    AppDataUtils.getValueForKeyFromProperty(Constants.getCommercialForecastingMultipleVariablesMessage()));
                        } catch (IOException e1) {

                            LOGGER.error("Exception Occurred{}:", e1.getMessage());
                        }
                        tableLogic.getContainerDataSource().getContainerProperty(obj[0], obj[1]).setValue(focusValue);
                    }

                    LOGGER.debug(" periodToUpdate= {} ", periodToUpdate);
                    LOGGER.debug(" yearToUpdate= {} ", yearToUpdate);
                    LOGGER.debug(" hierarchyIndicator= {} ", hierarchyIndicator);
                    LOGGER.debug(" hierarchyNo= {} ", dto.getHierarchyNo());
                    LOGGER.debug(" discountName= {} ", saveDto.getDiscountName());
                    LOGGER.debug(" discountRateValue= {} ", saveDto.getValue());
                    LOGGER.debug(" TreeLevelNo= {} ", saveDto.getTreeLevelNo());
                    LOGGER.debug(" CustomerHierarchyNo= {} ", saveDto.getCustomerHierarchyNo());
                    LOGGER.debug(" ProductHierarchyNo= {} ", saveDto.getProductHierarchyNo());
                }
            }
        }
    };

    public String getPeriodSid(String period, String fre, String order) {
        List periodSid = (List) HelperTableLocalServiceUtil.executeSelectQuery(periodQuery(period, fre, order));
        return periodSid.get(0).toString();
    }

    
    public String periodQuery(String period, String fre, String order) {
        String startYearValue = period.substring(period.length() - NumericConstants.FOUR);
        String startFreqNoValue = period.substring(1, NumericConstants.TWO);
        int startYear = isInteger(startYearValue) ? Integer.parseInt(startYearValue) : 0;
        String where;

        if (fre.equals(MONTHLY.getConstant())) {
            String startMonthValue = period.substring(0, period.length() - NumericConstants.FIVE);
            int startFreqNo = Integer.valueOf(startMonthValue.replaceAll("[^\\d.]", StringUtils.EMPTY));
            where = "where \"MONTH\" = '" + startFreqNo + AND_YEAR_EQUAL + startYear + "'";
        } else if (fre.equals(QUARTERLY.getConstant())) {
            where = "where QUARTER = '" + startFreqNoValue + AND_YEAR_EQUAL + startYear + "'";
        } else if (fre.equals(ANNUALLY.getConstant()) || fre.equals(ANNUAL.getConstant())) {
            where = "where  \"YEAR\" = '" + startYear + "'";
        } else {
            where = "where SEMI_ANNUAL = '" + startFreqNoValue + AND_YEAR_EQUAL + startYear + "'";
        }
        return "select " + order + "(PERIOD_SID) from \"PERIOD\" " + where;
    }
    private final ExtCustomCheckBox.ClickListener clickListener = new ExtCustomCheckBox.ClickListener() {
        @Override
        public void click(ExtCustomCheckBox.ClickEvent event) {
            Object[] obj = (Object[]) ((AbstractComponent) event.getComponent()).getData();
            final String tableHierarchyNo = tableLogic.getTreeLevelonCurrentPage(obj[0]);
            DiscountProjectionDTO dto = (DiscountProjectionDTO) obj[0];
            Boolean checkValue = ((ExtCustomCheckBox) ((AbstractComponent) event.getComponent())).getValue();
            if (isGroupUpdatedManually) {
                NotificationUtils.getAlertNotification(Constant.GROUP_FILTER_CONFLICT,
                        Constant.GROUP_VALUE_VERIFICATION);
                tableLogic.getContainerDataSource().getContainerProperty(obj[0], Constant.CHECKRECORD).setValue(BooleanConstant.getFalseFlag());
                return;
            }
            dto.addBooleanProperties(obj[1], checkValue);
            if (checkValue) {
                hierarchyListForCheckRecord.add(dto.getHierarchyNo());
            } else {
                hierarchyListForCheckRecord.remove(dto.getHierarchyNo());
            }
            int updatedRecordsNo = updateCheckedRecord(dto)
                    * CommonUtils.getFrequencyNumber(projectionSelection.getFrequency());
            resultsTable.getLeftFreezeAsTable().setRefresh(false);
            updateCheckForParentLevels(obj[0], updatedRecordsNo, checkValue);
            updateCheckForChildLevels(tableHierarchyNo, obj[0], checkValue);
            if (!checkValue) {
                ExtPagedTreeTable leftTable = resultsTable.getLeftFreezeAsTable();
                leftTable.removeColumnCheckListener(checkListener);
                leftTable.setColumnCheckBox(Constant.CHECKRECORD, true, false);
                tableLogic.setCheckAll(false);
                leftTable.addColumnCheckListener(checkListener);
            } else {
                ccpsCountForMassUpdate = dto.getCcpCountForDiscount();
            }
            resultsTable.getLeftFreezeAsTable().setRefresh(true);
        }
    };
    /**
     * The Group Filter Ddlb value change.
     *
     * @param event the event
     */
    private Property.ValueChangeListener groupFilterDdlbValueChange = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            resetTableData();
            setUserGroup();
            viewChangeGenerate();
            levelDdlb.setValue(SELECT_ONE.getConstant());
            resetLevelFilterDdlb();
        }
    };

    /**
     * Validation for manual entry
     *
     * @param period
     * @param refreshName
     */
    public void multiVariablesCheck(final String period, final String refreshName) {
        if (manualEntryMap.get(period) == null) {
            manualEntryMap.put(period, refreshName);
        }
    }

    public void checkMultiVariables(final String period, final String refreshName) {
        if (multipleVariableCheckMap.get(period.trim()) == null || multipleVariableCheckMap.get(period.trim()).equals(refreshName)) {
            multipleVariableCheckMap.put(period.trim(), refreshName);
        } else {
            isMultipleVariablesUpdated = true;
        }
    }

    /**
     * Reset button logic.
     *
     * @param event the event
     */
    @UiHandler("programSelection")
    public void addProgramSelectionChange(Property.ValueChangeEvent event) {
        programSelectionList.clear();
        if (event.getProperty().getValue() != null) {
            programSelectionList.add(String.valueOf(event.getProperty().getValue()));
        }
    }

    private void loadEmptyTable() {
        createSelectionDto();
        createRightHeader();
        resultBeanContainer.setColumnProperties(rightHeader.getProperties());
        configureRightTable();
    }

    /**
     * To load the previously selected values in EDIT scenario
     */
    private boolean loadSelections(boolean isReset) {
        LOGGER.debug(" inside load selections ");
        try {
            Map<Object, Object> map = new NMProjectionVarianceLogic()
                    .getNMProjectionSelection(session.getProjectionId(), TAB_DISCOUNT_PROJECTION.getConstant());

            if (map != null && map.size() > 0) {
                Object mapValue = map.get(Constant.FREQUENCY);
                frequencyDdlb.select(mapValue);
                setSelectedVariablesValue(map);
                mapValue = map.get(Constant.VARIABLES);
                if (mapValue != null && !ANULL.equals(mapValue)) {
                    String selectedValue = String.valueOf(mapValue);
                    selectedValue = selectedValue.replace("[", "");
                    String replacedValue = selectedValue.replace("]", "");
                    String[] array = replacedValue.split(",");
                    for (int i = 0; i < array.length; i++) {
                        mapValue = array[i];
                        variables.select(((String) mapValue).trim());
                    }
                } else {
                    variables.select(DISCOUNT_AMT.getConstant());
                }
                if (!CommonUtil.nullCheck(map.get(Constant.DISPLAY_FORMAT_SAVE))) {
                    CommonUtil.setCustomMenuBarValuesInEdit(map.get(Constant.DISPLAY_FORMAT_SAVE), displayFormatValues);
                }
                String displayFormatMenuItemValue = ChangeCustomMenuBarValueUtil.getInclusionMenuItemToDisplay(displayFormatValues);
                ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(displayFormatDdlb, displayFormatMenuItemValue);
                if (isReset) {
                    CommonLogic.unCheckMultiSelect(deductionFilterValues);
                    CommonLogic.unCheckMultiSelect(customerFilterValues);
                    CommonLogic.unCheckMultiSelect(productFilterValues);
                }
                setCustomerLevelFilterValues(map);
                setProductLevelFilter(map);
                setValuesForDeductions(map);
                customizeDataForDDLB(discountProgramsList, programSelection);
                frequencyDdlb.focus();
                setListviewGenerated(true);
                return true;
            }
            viewValueChangeLogic();
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
        return false;
    }

    public void setSelectedVariablesValue(Map<Object, Object> map)  {
        Object mapValue;
        mapValue = map.get(Constant.HISTORY);
        historyDdlb.select(Integer.parseInt(String.valueOf((mapValue == null || StringUtils.isBlank(mapValue.toString())
                || mapValue.toString().equalsIgnoreCase(ANULL)) ? 0
                : map.get(Constant.HISTORY))));
        mapValue = map.get(Constant.PROJECTION_PERIOD_ORDER_LABEL);
        periodOrder.select(mapValue);
        mapValue = map.get(Constant.ACTUALS_PROJECTIONS);
        actualsProjs.select(mapValue);
        mapValue = map.get(Constant.LEVEL_LABEL);
        level.select(mapValue);
        mapValue = map.get(Constant.YEAR_SELECTION_DDLB);
        yearSelection.select(mapValue);
    }

    public void setCustomerLevelFilterValues(Map<Object, Object> map)  {
        Object mapValue = map.get(Constant.CUSTOMER_LEVEL_DDLB);
        customerlevelDdlb.setValue(CommonUtil.nullCheck(mapValue) || CommonUtil.stringNullCheck(mapValue) ? SELECT_ONE.getConstant() : DataTypeConverter.convertObjectToInt(mapValue));
        mapValue = map.get(Constant.CUSTOMER_LEVEL_VALUE);
        if (!CommonUtil.nullCheck(mapValue)) {
            CommonUtil.setCustomMenuBarValuesInEdit(mapValue, customerFilterValues);
            generateCustomerToBeLoaded = commonLogic.getFilterValues(customerFilterValues).get(SID);
            projectionSelection.setCustomerLevelFilter((List) generateCustomerToBeLoaded);
        }
        String customerMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(customerFilterValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(customerFilterDdlb, customerMenuItemValue);
    }

    public void setProductLevelFilter(Map<Object, Object> map)  {
        Object mapValue = map.get(Constant.PRODUCT_LEVEL_DDLB);
        productlevelDdlb.setValue(CommonUtil.nullCheck(mapValue) || CommonUtil.stringNullCheck(mapValue) ? SELECT_ONE.getConstant() : DataTypeConverter.convertObjectToInt(mapValue));
        mapValue = map.get(Constant.PRODUCT_LEVEL_VALUE);
        if (!CommonUtil.nullCheck(mapValue)) {
            CommonUtil.setCustomMenuBarValuesInEdit(mapValue, productFilterValues);
            generateProductToBeLoaded = commonLogic.getFilterValues(productFilterValues).get(SID);
            projectionSelection.setProductLevelFilter((List) generateProductToBeLoaded);
        }
        String productMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(productFilterValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(productFilterDdlb, productMenuItemValue);
    }

    public void setValuesForDeductions(Map<Object, Object> map){
        Object mapValue = map.get(Constant.DEDUCTION_LEVEL_DDLB);
        deductionlevelDdlb.setValue(CommonUtil.nullCheck(mapValue) || CommonUtil.stringNullCheck(mapValue) ? SELECT_ONE.getConstant() : DataTypeConverter.convertObjectToInt(mapValue));
        mapValue = map.get(Constant.DEDUCTION_LEVEL_VALUE);
        if (!CommonUtil.nullCheck(mapValue)) {
            CommonUtil.setCustomMenuBarValuesInEdit(mapValue, deductionFilterValues);
            generateDiscountToBeLoaded = commonLogic.getFilterValues(deductionFilterValues).get(SID);
            generateDiscountNamesToBeLoaded = commonLogic.getFilterValues(deductionFilterValues).get(CAPTION);
            projectionSelection.setDeductionLevelFilter((List) generateDiscountToBeLoaded);
            projectionSelection.setDeductionLevelCaptions((List) generateDiscountToBeLoaded);
        }
        String deductionMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(deductionFilterValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(deductionFilterDdlb, deductionMenuItemValue);
        mapValue = map.get(Constant.DEDUCTION_INCLUSION_DDLB);
        if (!CommonUtil.nullCheck(mapValue)) {
            CommonUtil.setCustomMenuBarValuesInEdit(mapValue, deductionInclusionValues);
        }
        String deductionInclusionValue = ChangeCustomMenuBarValueUtil.getInclusionMenuItemToDisplay(deductionInclusionValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(deductionInclusionDdlb, deductionInclusionValue);
        if (!map.get(Constant.SELECTED_DISCOUNTS).equals("")) {
            
            if ("Program Category".equals(String.valueOf(map.get(Constant.LEVEL_LABEL)))) {
                discountProgramsList = new LinkedList<>(
                        Arrays.asList(String.valueOf(map.get(Constant.SELECTED_DISCOUNTS)).split("\\s*,\\s*")));
                discountNamesList = new LinkedList<>(discountProgramsList);
                discountNoList = new LinkedList<>(
                        Arrays.asList(String.valueOf(map.get("selectedDiscountNo")).split("\\s*,\\s*")));
            } else {
                List rawRSList = discountProjectionLogic.getRSDiscountSids(String.valueOf(session.getProjectionId()));
                discountProgramsList = new LinkedList<>((List<String>) rawRSList.get(0));
                discountNamesList = new LinkedList<>(discountProgramsList);
                discountNoList = new LinkedList<>((List<String>) rawRSList.get(1));
            }
            
        }
    }

    /**
     * Configure table.
     */
    public void initializeTable() {
        LOGGER.debug("Inside configureTable ");
        try {
            float maxSplitPosition = NumericConstants.THOUSAND;

            /* Minimum split position for the split bar */
            float minSplitPosition = NumericConstants.TWO_HUNDRED;

            /* Default split position for the split bar */
            float splitPosition = NumericConstants.FIVE_HUNDRED;
            resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
            resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
            resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
            resultsTable.setHeight(Constant.SIX_FIFTY_PX);
            resultsTable.setWidth("100%");
            resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

            refreshBtn.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    session.setFunctionMode("R");
                    if (!isMultipleVariablesUpdated) {
                        if (isRateUpdatedManually || isRPUUpdatedManually
                                || isAmountUpdatedManually) {
                            LOGGER.debug("inside if refreshBtn--------------------------------- ");
                            isRateUpdatedManually = false;
                            isRPUUpdatedManually = false;
                            isAmountUpdatedManually = false;
                            try {
                                TimeUnit.SECONDS.sleep(3);
                            } catch (InterruptedException ex) {
                                LOGGER.error(" Procedure Error -- {}",ex);
                                Thread.currentThread().interrupt();
                            }
                            CommonLogic.procedureCompletionCheck(session, Constant.DISCOUNT3, String.valueOf(projectionSelection.getViewOption()));
                            refreshTableData(getCheckedRecordsHierarchyNo());
                            manualEntryMap.clear();
                            final Notification notif = new Notification(Constant.CALCULATION_COMPLETE,
                                    Notification.Type.HUMANIZED_MESSAGE);
                            notif.setPosition(Position.TOP_CENTER);
                            notif.setStyleName(ConstantsUtils.MY_STYLE);
                            notif.show(Page.getCurrent());
                            multipleVariableCheckMap.clear();
                        }

                    } else {
                        try {
                            AbstractNotificationUtils.getErrorNotification(AppDataUtils.getValueForKeyFromProperty(Constants.getCommercialForecastingMultipleVariablesHeader()),
                                    AppDataUtils.getValueForKeyFromProperty(Constants.getCommercialForecastingMultipleVariablesMessage()));
                        } catch (IOException e) {

                            LOGGER.error("Exception Occurred in Getting Property value from Property File:{}", e.getMessage());
                        }
                        isMultipleVariablesUpdated = false;
                        refreshTableData(getCheckedRecordsHierarchyNo());
                        multipleVariableCheckMap.clear();
                    }
                }
            });

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("Ending configureTable ");
    }

    /**
     * To refresh the table Data
     */
    public void refreshTableData(Set<String> finalHirarechyNo) {
        tableLogic.setRefreshHierarchyNo(getHierarchyNoForCheckedRecord(finalHirarechyNo));
        tableLogic.forRefresh(finalHirarechyNo);
        tableLogic.setCurrentPage(tableLogic.getCurrentPage());

    }

    /**
     * To get the hierarchy numbers affected during the manual entry
     *
     * @return
     */
    private Set<String> getManualEntryRefreshHierarachyNo() {
        Set<String> finalHierarchyNo = new HashSet<>();
        for (String hirarechyNo : refreshTableHierarchySet) {
            if (hirarechyNo != null && !hirarechyNo.equalsIgnoreCase(ANULL)) {
                finalHierarchyNo.addAll(tableLogic.getAllParentLevels(hirarechyNo));
                finalHierarchyNo.addAll(tableLogic.getAllChildLevels(hirarechyNo));
                finalHierarchyNo.add(hirarechyNo);
            }
        }
        refreshTableHierarchySet.clear();
        return finalHierarchyNo;
    }

    /**
     * To add Results table in UI
     */
    private void addResultTable() {
        resultsTableLayout.removeAllComponents();
        resultsTableLayout.addComponent(resultsTable);

        tableLogic.setPageLength(NumericConstants.TWENTY);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);

        HorizontalLayout controls = tableLogic.createControls();
        HorizontalLayout cssControls = CommonLogic.getResponsiveControls(controls);
        cssControls.addStyleName("responsivePagedTable");
        resultsTableLayout.addComponent(cssControls);
        resultsTableLayout.addComponent(excelTable);
    }

    /**
     * To reset the Level Filter Ddlb
     */
    private void resetLevelFilterDdlb() {
        enableLevelFilterListener = false;
        String levelNo = StringUtils.EMPTY;
        if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            levelNo = session.getCustomerLevelNumber();
        } else if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            levelNo = session.getProductLevelNumber();
        }

        levelFilterDdlb.select(Constant.DASH + levelNo);
        enableLevelFilterListener = true;
    }

    /**
     * To check and Clear all check box
     *
     * @param checkClear
     */
    public void checkClearAll(boolean checkClear) {
        LOGGER.debug("Inside checkClearAll");
        tableLogic.setRefresh(BooleanConstant.getFalseFlag());
        discountProjectionLogic.checkClearAll(session, userGroup, checkClear);
        for (String hierarchyNo : tableLogic.getAllLevels()) {
            boolean isPresentInContainer = true;
            Object tempId = tableLogic.getcurrentTreeData(hierarchyNo);
            if (tempId == null) {
                isPresentInContainer = false;
                tempId = tableLogic.getExpandedTreeValues(hierarchyNo);
            }
            if (tempId != null) {
                DiscountProjectionDTO tempDto = (DiscountProjectionDTO) tempId;
                tempDto.setUncheckCount(checkClear ? 0 : tempDto.getCcpCount());
                ccpsCount = tempDto.getCcpCount();
                updateChecks(tempId, isPresentInContainer);
            }
        }
        LOGGER.debug("exiting checkClearAll");
        tableLogic.setRefresh(BooleanConstant.getTrueFlag());
    }

    /**
     * To update the check box
     *
     * @param tempId
     * @param isPresentInContainer
     */
    private void updateChecks(Object tempId, boolean isPresentInContainer) {

        DiscountProjectionDTO tempDto = (DiscountProjectionDTO) tempId;
        boolean checkValue = tempDto.getUncheckCount() == 0;
        tempDto.addBooleanProperties(Constant.CHECKRECORD, checkValue);
        if (isPresentInContainer) {
            tableLogic.getContainerDataSource().getContainerProperty(tempId, Constant.CHECKRECORD).setValue(checkValue);
        }
    }

    @Override
    protected void fieldDdlbValueChangeLogic(Property.ValueChangeEvent event) {
        fieldDdlb(event);
    }

    public void fieldDdlb(Property.ValueChangeEvent event) {
        LOGGER.debug("fieldDdlb value change listener starts");
        String fieldValue = String.valueOf(event.getProperty().getValue());
        value.setValue(StringUtils.EMPTY);
        valueDdlb.select(SELECT_ONE.getConstant());
        startPeriod.select(SELECT_ONE.getConstant());
        endPeriod.select(SELECT_ONE.getConstant());
        if (event.getProperty().getValue() != null) {
            if (!(Constant.GROUPFCAPS).equals(fieldValue)) {
                valueDdlb.setVisible(false);
                value.setVisible(true);
                startPeriod.setVisible(true);
                endPeriod.setVisible(true);
                lblEnd.setVisible(true);
                lblStart.setVisible(true);
                List<String> projectedHeaders = rightHeader.getDoubleHeaders();
                if (projectionSelection.getProjectionOrder().equals(DESCENDING.getConstant())) {
                    Collections.reverse(projectedHeaders);
                }
                loadDiscPeriods(startPeriodBean, startPeriod, rightHeader.getDoubleProjectedHeaders());
                loadDiscPeriods(endPeriodBean, endPeriod, rightHeader.getDoubleProjectedHeaders());

            } else {
                valueDdlb.setVisible(true);
                value.setVisible(false);
                startPeriod.setVisible(false);
                endPeriod.setVisible(false);
                lblEnd.setVisible(false);
                lblStart.setVisible(false);
            }
        } else {
            valueDdlb.setVisible(false);
            value.setVisible(true);
            startPeriod.setVisible(true);
            endPeriod.setVisible(true);
            lblEnd.setVisible(true);
            lblStart.setVisible(true);
            startPeriodBean.removeAllItems();
            endPeriodBean.removeAllItems();
            startPeriod.removeAllItems();
            endPeriod.removeAllItems();
            startPeriod.addItem(SELECT_ONE.getConstant());
            startPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
            endPeriod.addItem(SELECT_ONE.getConstant());
            endPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
        }
        LOGGER.debug("fieldDdlb value change listener ends");

    }

    /**
     * To load the group Ddlb
     */
    private void loadGroupDdlb() {
        LOGGER.debug(" Load group ");
        valueDdlbBean.removeAllItems();
        List<String> discountToBeLoaded = new ArrayList<>();
        if (!programSelectionList.isEmpty()) {
            discountToBeLoaded.addAll(programSelectionList);

        } else {
            discountToBeLoaded.addAll(discountProgramsList);

        }
        if (CommonUtil.isValueEligibleForLoading()) {
            discountToBeLoaded = projectionSelection.getDeductionLevelFilter();
        }
        discountToBeLoaded.add(0, String.valueOf(level.getValue()));
        valueDdlbBean.addAll(discountProjectionLogic.loadGroupValues(session, discountToBeLoaded));
        LOGGER.debug(" End load group ");
    }

    private String getRelationshipBuilderSid() {
        String relationshipBuilderSid;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator)) {
            relationshipBuilderSid = session.getCustRelationshipBuilderSid();
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
            relationshipBuilderSid = session.getProdRelationshipBuilderSid();
        } else {
            relationshipBuilderSid = session.getDedRelationshipBuilderSid();
        }
        return relationshipBuilderSid;
    }

    /**
     * To call the discount Projection procedure
     */
    public void callDiscountProjectionProcedure() {
        LOGGER.debug(" Calling Discount Projection Procedure");
        discountProjectionLogic.callDiscountProjectionProcedure(session);
    }

    @Override
    public List<String> loadYearSelection() {
        List<String> year = new ArrayList();
        Calendar historyCalendar = Calendar.getInstance();
        historyCalendar.setTime(session.getForecastDTO().getHistoryStartDate());

        Calendar projectionCalendar = Calendar.getInstance();
        projectionCalendar.setTime(session.getToDate());

        int yearToAdd = historyCalendar.get(Calendar.YEAR);
        int historyYearCount = CURRENT_YEAR.getConstant() - historyCalendar.get(Calendar.YEAR);
        int projectionYearCount = projectionCalendar.get(Calendar.YEAR) - CURRENT_YEAR.getConstant();
        year.add(SELECT_ONE.getConstant());
        year.add(ALL.getConstant());
        for (int i = 0; i <= (projectionYearCount + historyYearCount); i++, yearToAdd++) {
            year.add(String.valueOf(yearToAdd));
        }
        return year;
    }

    /**
     * To load the level filter and level Ddlb values
     */
    private void loadLevelValues() {
        LOGGER.debug("loadLevelValues started ");

        levelFilterDdlb.removeAllItems();
        levelDdlb.removeAllItems();
        levelDdlb.addItem(SELECT_ONE.getConstant());
        levelDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        Collections.sort(currentHierarchy, new Comparator<Leveldto>() {
            @Override
            public int compare(Leveldto o1, Leveldto o2) {
                return o2.getTreeLevelNo() - o1.getTreeLevelNo();
            }
        });
        Collections.reverse(currentHierarchy);

        if (currentHierarchy != null) {
            boolean toSetCaption = true;
            for (int i = 0; i < currentHierarchy.size(); i++) {
                Leveldto levelDto = currentHierarchy.get(i);
                int startLevelNo = getStartLevelNo();
                if (levelDto.getTreeLevelNo() >= startLevelNo) {
                    if (toSetCaption) {
                        levelFilterDdlb.addItem(Constant.DASH + levelDto.getTreeLevelNo());
                        levelFilterDdlb.setItemCaption(Constant.DASH + levelDto.getTreeLevelNo(),
                                SELECT_ONE.getConstant());
                        enableLevelFilterListener = false;
                        levelFilterDdlb.select(Constant.DASH + levelDto.getTreeLevelNo());
                        enableLevelFilterListener = true;
                        toSetCaption = false;
                    }
                    String itemId = levelDto.getTreeLevelNo() + StringUtils.EMPTY;
                    String caption = Constant.LEVEL + levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                    levelFilterDdlb.addItem(itemId);
                    levelFilterDdlb.setItemCaption(itemId, caption);

                    // To load Level Ddlb
                    if (i < currentHierarchy.size() - 1) {
                        levelDdlb.addItem(itemId);
                        levelDdlb.setItemCaption(itemId, caption);
                    }

                }
            }
        }
        LOGGER.debug("loadLevelValues ended ");
    }

    /**
     * To get the starting level number
     *
     * @return
     */
    private int getStartLevelNo() {
        int levelNo = 1;
        if (view.getValue().equals(CUSTOMER.getConstant())) {
            levelNo = Integer.parseInt(session.getCustomerLevelNumber());
        } else if (view.getValue().equals(PRODUCT.getConstant())) {
            levelNo = Integer.parseInt(session.getProductLevelNumber());
        }

        return levelNo;
    }

    @Override
    protected void viewValueChangeLogic() {
        CommonLogic.procedureCompletionCheck(session,DISCOUNT,String.valueOf(view.getValue()));
        try {
            viewDdlb.setEnabled(false);
            editBtn.setEnabled(false);
            newBtn.setEnabled(false);
            resetTableData();
            tableLogic.setRefreshHierarchyNo(StringUtils.EMPTY);
            if (CUSTOM.getConstant().equals(String.valueOf(view.getValue()))
                    && CommonUtil.isValueEligibleForLoading()) {
                adjprogramsLb.setVisible(false);
                adjprograms.setVisible(false);
            } else {
                adjprogramsLb.setVisible(true);
                adjprograms.setVisible(true);
            }
            if (view.getValue() != null) {
                if (CUSTOM.getConstant().equals(String.valueOf(view.getValue()))) {
                    hierarchyIndicator = CommonUtil.isValueEligibleForLoading()
                            ? Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY : "CP";
                    loadCustomDDLB();
                    currentHierarchy = CommonLogic.getCustomTree(customId);
                    currentHierarchy = session.getCustomHierarchyMap().get(customId);
                    levelFilterDdlb.setEnabled(false);
                    setUserGroup();
                    fieldDdlb.removeItem(Constant.GROUPFCAPS);
                    fieldDdlb.setValue(Constant.DISCOUNT_RATE_LABEL);
                    projectionSelection.setViewOption(Constant.CUSTOM_LABEL);
                    if (!projectionSelection.getDeductionLevelFilter().isEmpty() ) {
                        generateListView(true);
                    }
                    resultsTable.getLeftFreezeAsTable().setColumnCollapsingAllowed(true);
                    resultsTable.getLeftFreezeAsTable().setColumnCollapsed(Constant.GROUP, false);
                    if (CommonUtil.isValueEligibleForLoading()) {
                        resultsTable.getLeftFreezeAsTable().setDoubleHeaderVisible(false);
                        resultsTable.setTripleHeaderVisible(false);
                        resultsTable.getRightFreezeAsTable().setColumnCollapsingAllowed(true);
                        resultsTable.getRightFreezeAsTable().setTripleHeaderVisible(false);
                    }
                } else if (CUSTOMER.getConstant().equals(String.valueOf(view.getValue()))) {
                    customIdToSelect = customId;
                    currentHierarchy = session.getCustomerHierarchyList();
                    hierarchyIndicator = "C";
                    levelDdlb.setEnabled(true);
                    levelFilterDdlb.setEnabled(true);
                    expandBtn.setEnabled(true);
                    collapseBtn.setEnabled(true);
                    groupFilterDdlb.setEnabled(true);
                    setUserGroup();
                    viewChangeGenerate();
                    levelFilterDdlb.select(Constant.DASH + session.getCustomerLevelNumber());
                    levelDdlb.setValue(SELECT_ONE.getConstant());
                    fieldDdlb.addItem(Constant.GROUPFCAPS);
                    projectionSelection.setViewOption(Constant.CUSTOM_LABEL);
                    if (!projectionSelection.getDeductionLevelFilter().isEmpty()) {
                        generateListView(true);
                    }
                    // As per FD Group column should not be in Product View
                    resultsTable.getLeftFreezeAsTable().setColumnCollapsingAllowed(true);
                    resultsTable.getLeftFreezeAsTable().setColumnCollapsed(Constant.GROUP, false);
                    resultsTable.getLeftFreezeAsTable().setDoubleHeaderVisible(true);
                    resultsTable.setTripleHeaderVisible(true);
                } else if (PRODUCT.getConstant().equals(String.valueOf(view.getValue()))) {
                    customIdToSelect = customId;
                    currentHierarchy = session.getProductHierarchyList();
                    hierarchyIndicator = Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY;
                    levelDdlb.setEnabled(true);
                    levelFilterDdlb.setEnabled(true);
                    expandBtn.setEnabled(true);
                    collapseBtn.setEnabled(true);
                    userGroup = StringUtils.EMPTY;
                    groupFilterDdlb.setEnabled(false);
                    projectionSelection.setViewOption(Constant.CUSTOM_LABEL);
                    viewChangeGenerate();
                    levelFilterDdlb.select(Constant.DASH + session.getProductLevelNumber());
                    levelDdlb.setValue(SELECT_ONE.getConstant());
                    fieldDdlb.removeItem(Constant.GROUPFCAPS);
                    fieldDdlb.setValue(Constant.DISCOUNT_RATE_LABEL);
                    if (!projectionSelection.getDeductionLevelFilter().isEmpty()) {
                        generateListView(true);
                    }
                    resultsTable.getLeftFreezeAsTable().setColumnCollapsingAllowed(true);
                    resultsTable.getLeftFreezeAsTable().setColumnCollapsed(Constant.GROUP, true);
                    resultsTable.getLeftFreezeAsTable().setDoubleHeaderVisible(true);
                    resultsTable.setTripleHeaderVisible(true);
                }
            }
        } catch (Property.ReadOnlyException | IllegalStateException | UnsupportedOperationException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void viewChangeGenerate() {
        if (isListviewGenerated) {
            tableLogic.clearAll();

            resultBeanContainer.setColumnProperties(leftHeader.getProperties());
            resultsTable.constructLeftFreeze(true);
            resultsTable.getLeftFreezeAsTable().setRefresh(false);
            resultsTable.getLeftFreezeAsTable().setContainerDataSource(resultBeanContainer);
            configureLeftTable();
            if (Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator)
                    && CommonUtil.isValueEligibleForLoading()) {
                resultsTable.getLeftFreezeAsTable().setDoubleHeaderVisible(false);
                resultsTable.setTripleHeaderVisible(false);
            } else {
                resultsTable.getLeftFreezeAsTable().setDoubleHeaderVisible(true);
                resultsTable.setTripleHeaderVisible(true);
            }
            if (!projectionSelection.getDeductionLevelFilter().isEmpty()) {
                loadDataInTable();
            }
            loadLevelValues();

        }
    }

    private void configureLeftTable() {
        LOGGER.debug("Entering configureLeftTable");
        final ExtFilterTreeTable leftTable = resultsTable.getLeftFreezeAsTable();

        leftTable.setDoubleHeaderVisible(true);

        leftTable.setEditable(true);
        String[] columnLeftHeader = new String[leftHeader.getSingleHeaders().size()];
        securityForListView(leftHeader.getSingleColumns().toArray(), leftHeader.getSingleHeaders().toArray(columnLeftHeader), leftTable);

        leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(
                leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
        leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());

        leftTable.setHeight(Constant.SIX_FIFTY_PX);

        leftTable.setColumnCheckBox(Constant.CHECKRECORD, true);
        leftTable.setColumnCheckBoxDisable(Constant.CHECKRECORD,
                ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));

        resultsTable.getLeftFreezeAsTable().setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.getLeftFreezeAsTable().setFilterGenerator(new ExtFilterGenerator() {

            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if (Constant.GROUP.equals(propertyId)) {
                    ComboBox tableGroupFilter = new ComboBox();
                    tableGroupFilter.addValueChangeListener(tableGroupFilterDdlbValueChange);
                    tableGroupFilter.setContainerDataSource(tableGroupDdlbBean);
                    tableGroupFilter.setNullSelectionAllowed(true);
                    tableGroupFilter.setNullSelectionItemId(Constant.SHOW_ALL_GROUPS);
                    tableGroupFilter.select(Constant.SHOW_ALL_GROUPS);
                    tableGroupFilter.setWidth("100%");
                    return tableGroupFilter;
                }

                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {
                return;
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
                return;
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }
        });

        addFieldFactoryAndListenersForLeftTable();
        for (Object obj : leftHeader.getSingleColumns()) {
            if (String.valueOf(obj).contains(Constant.GROUP)) {
                leftTable.setColumnWidth(obj, NumericConstants.ONE_THREE_ZERO);
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, NumericConstants.ONE_THREE_ZERO);
            }
        }
        resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);
        LOGGER.debug("Ending configureLeftTable");
    }

    private void addFieldFactoryAndListenersForLeftTable() {
        LOGGER.debug("Entering addFieldFactoryAndListenersForLeftTable");
        try {
            resultsTable.getLeftFreezeAsTable().addColumnCheckListener(checkListener);
            resultsTable.getLeftFreezeAsTable().setTableFieldFactory(new DefaultFieldFactory() {
                @Override
                public Field<?> createField(final Container container, final Object itemId, final Object propertyId,
                        Component uiContext) {
                    String property = String.valueOf(propertyId);
                    Item item = container.getItem(itemId);
                    String levelType = String.valueOf(item.getItemProperty("level").getValue());
                    if (property.equals(Constant.CHECKRECORD)) {
                        final ExtCustomCheckBox check = new ExtCustomCheckBox();
                        check.setEnabled(!ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
                        check.setImmediate(true);
                        check.setData(new Object[]{itemId, propertyId});
                        check.addClickListener(clickListener);
                        return check;
                    }

                    if (Constant.GROUP.equals(property)
                            && (Constant.TRADING_PARTNER.equals(levelType) || Constant.CUSTOMER_SMALL.equals(levelType))
                            && !Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant()
                                    .equals(hierarchyIndicator)
                            && !ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
                        TextField textField = new TextField();
                        textField.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
                        textField.setData(new Object[]{itemId, propertyId, "left"});
                        textField.addFocusListener(focusListener);
                        textField.addBlurListener(blurListener);
                        return textField;

                    }
                    return null;
                }
            });

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("Ending addFieldFactoryAndListenersForLeftTable ");
    }

    private void createLeftHeader() {
        leftHeader = HeaderUtils.getDiscountProjectionLeftTableColumns(hierarchyIndicator, false, excelHeaderLeft);
    }

    /**
     * To update the checked record
     */
    public int updateCheckedRecord(DiscountProjectionDTO dto) {
        LOGGER.debug("Inside updateCheckedRecord");
        boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
        List<String> discountList = projectionSelection.getDiscountProgramsList();
        int updatedRecordCount = 0;
        try {
            int maxTreeLevelno = 0;
            int treeLevelNo;
            int count = 0;
            boolean checkValue;
            String hierarchyNo;
            List<String> customerHierarchyNoList = new ArrayList<>();
            List<String> productHierarchyNoList = new ArrayList<>();
            List<String> deductionHierarchyNoList = new ArrayList<>();
            List<String> customerLevelNoList = new ArrayList<>();
            List<String> productLevelNoList = new ArrayList<>();
            List<String> deductionLevelNoList = new ArrayList<>();
            List<String> hierarchyIndicatorList = new ArrayList<>();
            boolean isCustomHierarchy = CommonUtil.isValueEligibleForLoading()
                    ? Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator)
                    : Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.toString().equals(hierarchyIndicator);

            if (resultBeanContainer.size() == 0) {
                LOGGER.debug(" Container size is 0");
                return updatedRecordCount;
            }
            if (isCustomHierarchy) {
                if (customerHierarchyNoList.isEmpty() && productHierarchyNoList.isEmpty()
                        && deductionHierarchyNoList.isEmpty()) {
                    maxTreeLevelno = dto.getTreeLevelNo();
                }

                treeLevelNo = dto.getTreeLevelNo();
                if (treeLevelNo > maxTreeLevelno) {
                    customerHierarchyNoList.clear();
                    productHierarchyNoList.clear();
                    deductionHierarchyNoList.clear();
                    customerLevelNoList.clear();
                    productLevelNoList.clear();
                    deductionLevelNoList.clear();
                    hierarchyIndicatorList.clear();
                    count = 0;
                } else if (treeLevelNo == maxTreeLevelno) {
                    count++;
                }
                customerHierarchyNoList.add(dto.getCustomerHierarchyNo());
                productHierarchyNoList.add(dto.getProductHierarchyNo());
                deductionHierarchyNoList.add(dto.getDeductionHierarchyNo());

                hierarchyIndicatorList.add(dto.getHierarchyIndicator());
                if (Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(dto.getHierarchyIndicator())
                        && CommonUtil.isValueEligibleForLoading()) {
                    customerLevelNoList.add(Constant.PERCENT);
                    productLevelNoList.add(Constant.PERCENT);
                    deductionLevelNoList.add(StringUtils.EMPTY + dto.getTreeLevelNo());
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                    customerLevelNoList.add(StringUtils.EMPTY + dto.getTreeLevelNo());
                    productLevelNoList.add(Constant.PERCENT);
                    deductionLevelNoList.add(Constant.PERCENT);
                } else {
                    customerLevelNoList.add(StringUtils.EMPTY + dto.getTreeLevelNo());
                    productLevelNoList.add(Constant.PERCENT);
                    deductionLevelNoList.add(Constant.PERCENT);
                }

            }

            checkValue = (Boolean) dto.getPropertyValue(Constant.CHECKRECORD);
            hierarchyNo = dto.getHierarchyNo();
            List<String> customViewDetails = new ArrayList<>();
            String discountIds = CommonUtils.convertCollectionToString(checkedDiscountsPropertyIds, false);
            if (isCustomHierarchy) {
                for (int i = 0; i < count; i++) {
                    customViewDetails = new ArrayList<>();
                    customViewDetails.add(StringUtils.EMPTY + customId);
                    customViewDetails.add(customerLevelNoList.get(i));
                    customViewDetails.add(customerHierarchyNoList.get(i));
                    customViewDetails.add(productLevelNoList.get(i));
                    customViewDetails.add(productHierarchyNoList.get(i));

                    customViewDetails.add(session.getCustRelationshipBuilderSid());
                    customViewDetails.add(session.getProdRelationshipBuilderSid());
                    customViewDetails.add(session.getDedRelationshipBuilderSid());
                    customViewDetails.add(deductionLevelNoList.get(i));
                    customViewDetails.add(deductionHierarchyNoList.get(i));
                    LOGGER.debug(" Check record - Custom Id= {} ", customId);
                    LOGGER.debug(" Check record - customerLevelNo= {} ", customerLevelNoList.get(i));
                    LOGGER.debug(" Check record - customerHierarchyNo= {} ", customerHierarchyNoList.get(i));
                    LOGGER.debug(" Check record - productLevelNo= {} ", productLevelNoList.get(i));
                    LOGGER.debug(" Check record - productHierarchyNo= {} ", productHierarchyNoList.get(i));
                    LOGGER.debug(" Check record - checkValue= {} ", checkValue);
                    LOGGER.debug(" Check record - Hierarchy indicator= {} ", hierarchyIndicatorList.get(i));
                    LOGGER.debug(
                            " Check record - CustRelationshipBuilderSid= {} ", session.getCustRelationshipBuilderSid());
                    LOGGER.debug(
                            " Check record - ProdRelationshipBuilderSid= {} ", session.getProdRelationshipBuilderSid());

                    updatedRecordCount = discountProjectionLogic.updateCheckRecord(session, checkValue, hierarchyNo,
                            hierarchyIndicatorList.get(i), isCustomHierarchy, customViewDetails, isProgram,
                            discountList, discountIds);
                }
            } else {
                updatedRecordCount = discountProjectionLogic.updateCheckRecord(session, checkValue, hierarchyNo, hierarchyIndicator,
                        isCustomHierarchy, customViewDetails, isProgram, discountList, discountIds);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            updatedRecordCount = 0;
        }
        LOGGER.debug("Ending updateCheckedRecord");
        return updatedRecordCount;
    }

    /**
     * To update check records count for parent
     *
     * @param itemId
     * @param updatedRecordsNo
     * @param checkValue
     */
    private void updateCheckForParentLevels(Object itemId, int updatedRecordsNo, Boolean checkValue) {
        LOGGER.debug("Inside updateCheckForParentLevels");
        DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
        int newRecordsCount = updatedRecordsNo;
        LOGGER.debug(" updatedRecordsNo= {} ", newRecordsCount);
        if (checkValue) {
            if (newRecordsCount > dto.getUncheckCount()) {
                newRecordsCount = dto.getUncheckCount();
            }
        } else if (newRecordsCount < dto.getUncheckCount()) {
            newRecordsCount = dto.getUncheckCount();
        }
        List<String> hierarchyNos = tableLogic.getAllParentLevels(itemId);

        for (String hierarchyNo : hierarchyNos) {
            boolean isPresentInContainer = true;
            Object tempId = tableLogic.getcurrentTreeData(hierarchyNo);
            if (tempId == null) {
                isPresentInContainer = false;
                tempId = tableLogic.getExpandedTreeValues(hierarchyNo);
            }
            if (tempId != null) {
                DiscountProjectionDTO tempDto = (DiscountProjectionDTO) tempId;
                LOGGER.debug(" Parent Uncheck count before= {}, {}, {} ", tempDto.getTreeLevelNo(), tempDto.getLevelName(),
                        tempDto.getUncheckCount());
                if (checkValue) {
                    tempDto.setUncheckCount(tempDto.getUncheckCount() - newRecordsCount);
                } else {
                    tempDto.setUncheckCount(tempDto.getUncheckCount() + newRecordsCount);
                }
                LOGGER.debug(" Parent Uncheck count after= {}, {}, {} ", tempDto.getTreeLevelNo(), tempDto.getLevelName(),
                        tempDto.getUncheckCount());
                updateChecks(tempId, isPresentInContainer);
            }
        }
        LOGGER.debug("Exiting updateCheckForParentLevels");
    }

    /**
     * To update records count for child
     *
     * @param tableHierarchyNo
     * @param itemId
     * @param checkValue
     */
    private void updateCheckForChildLevels(String tableHierarchyNo, Object itemId, Boolean checkValue) {
        LOGGER.debug("Inside updateCheckForChildLevels");
        List<String> childTableHierarchyNos = tableLogic.getAllChildLevels(itemId);
        childTableHierarchyNos.add(tableHierarchyNo);
        for (String hierarchyNo : childTableHierarchyNos) {
            boolean isPresentInContainer = true;
            Object tempId = tableLogic.getcurrentTreeData(hierarchyNo);
            if (tempId == null) {
                isPresentInContainer = false;
                tempId = tableLogic.getExpandedTreeValues(hierarchyNo);
            }
            if (tempId != null) {
                DiscountProjectionDTO tempDto = (DiscountProjectionDTO) tempId;
                LOGGER.debug(" Child Uncheck count before= {}, {}, {} ", tempDto.getTreeLevelNo(), tempDto.getLevelName(),
                        tempDto.getUncheckCount());

                if (checkValue) {
                    tempDto.setUncheckCount(0);
                } else {
                    tempDto.setUncheckCount(tempDto.getCcpCount());
                }
                LOGGER.debug(" Child Uncheck count after= {}, {}, {} ", tempDto.getTreeLevelNo(), tempDto.getLevelName(),
                        tempDto.getUncheckCount());
                updateChecks(tempId, isPresentInContainer);
            }
        }
        LOGGER.debug("Ending updateCheckForChildLevels");
    }

    /**
     * To load the values in custom Ddlb
     */
    public void loadCustomDDLB() {
        LOGGER.debug("loadCustomDDLB initiated ");
        editBtn.setEnabled(false);
        newBtn.setEnabled(false);
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("custSid", session.getCustRelationshipBuilderSid());
        dataMap.put("custVer", String.valueOf(session.getCustomerRelationVersion()));
        dataMap.put("prodSid", session.getProdRelationshipBuilderSid());
        dataMap.put("prodVer", String.valueOf(session.getProductRelationVersion()));
        new DataSelectionLogic().loadCustomViewDeductionValues(viewDdlb, dataMap, false);
        viewDdlb.setValue(session.getCustomDeductionRelationShipSid());
        LOGGER.debug("loadCustomDDLB ends ");
    }

    /**
     * To set the user group from group filter Ddlb
     */
    private void setUserGroup() {
        userGroup = String.valueOf(groupFilterDdlb.getValue());
        if (Constant.NULL.equals(userGroup)) {
            userGroup = StringUtils.EMPTY;
        }
    }

    /**
     * To reset the table data
     */
    private void resetTableData() {
        saveDiscountProjectionListview();
        refreshTableHierarchySet.clear();
        tableLogic.clearAll();
        tableLogic.setCheckAll(false);
        discountProjectionLogic.checkClearAll(session, userGroup, false);
    }

    @Override
    protected void adjProgramsValueChangeLogic(String adjustmentProgram) {
        ExtPagedTreeTable rightTable = resultsTable.getRightFreezeAsTable();
        checkedDiscountsPropertyIds.clear();
        boolean checkvalue = adjustmentProgram.equals(ALL.getConstant());
        for (Object columns : rightTable.getTripleHeaderVisibleColumns()) {
            rightTable.setTripleHeaderColumnCheckBox(columns, true, checkvalue);
            rightTable.setTripleHeaderColumnCheckBoxDisable(columns,
                    ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
            tripleHeaderCheckListener(columns, checkvalue);
        }
        LOGGER.debug(" adjustmentPrograms= {} ", adjustmentProgram);
    }

    @Override
    protected void adjPeriodValueChangeLogic(String adjustmentPeriod) {
        String adjustmentPrograms = String.valueOf(adjprograms.getValue());
        LOGGER.debug(" adjustmentPeriods= {} ", adjustmentPeriod);
        LOGGER.debug(" adjustmentPrograms= {} ", adjustmentPrograms);
        if (adjustmentPeriod.equals(ALL.getConstant())) {
            if (CUSTOM.getConstant().equals(String.valueOf(view.getValue()))
                    && CommonUtil.isValueEligibleForLoading()) {
                ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
                for (Object doubleColumn : rightTable.getDoubleHeaderVisibleColumns()) {
                    rightTable.setDoubleHeaderColumnCheckBox(doubleColumn, true, true);
                    rightTable.setDoubleHeaderColumnCheckBoxDisable(doubleColumn,
                            ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
                    doubleHeaderCheckListener(doubleColumn, true);
                }
            } else {
                ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
                Object[] selectedtripleHeaderColumns;
                if (adjustmentPrograms.equals(ALL.getConstant())) {
                    selectedtripleHeaderColumns = rightTable.getTripleHeaderVisibleColumns();
                } else {
                    selectedtripleHeaderColumns = checkedDiscountsPropertyIds.toArray();
                }
                tripleHeaderForCheckedDoubleHeader.clear();
                Object[] doubleColumns;
                for (Object discountColumn : selectedtripleHeaderColumns) {
                    LOGGER.debug(" Adj periods discount Column Name= {} ", discountColumn);
                    doubleColumns = rightHeader.getTripleHeaderMaps().get(discountColumn);
                    for (Object doubleColumn : doubleColumns) {
                        rightTable.setDoubleHeaderColumnCheckBox(doubleColumn, true, true);
                        rightTable.setDoubleHeaderColumnCheckBoxDisable(doubleColumn,
                                ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
                        doubleHeaderCheckListener(doubleColumn, true);
                    }
                }
            }
        } else {
            for (Object columns : resultsTable.getRightFreezeAsTable().getDoubleHeaderVisibleColumns()) {
                resultsTable.getRightFreezeAsTable().setDoubleHeaderColumnCheckBox(columns, true, false);
                resultsTable.getRightFreezeAsTable().setDoubleHeaderColumnCheckBoxDisable(columns,
                        ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
            }
            tripleHeaderForCheckedDoubleHeader.clear();
        }
    }

     @Override
    protected void populateBtnClickLogic() {
        CommonLogic.viewProceduresCompletionCheckDiscount(session);
        session.setFunctionMode("M");
        if (isGroupUpdatedManually) {
            NotificationUtils.getAlertNotification(Constant.GROUP_FILTER_CONFLICT, Constant.GROUP_VALUE_VERIFICATION);
            return;
        }
        final String selectedField = String.valueOf(fieldDdlb.getValue());
        final String valueText = value.getValue();
        final String valueDdlbValue = String.valueOf(valueDdlb.getValue());
        try {
            LOGGER.debug("fieldDdlb.getValue()-->>> = {}", selectedField);
            if (!valueText.matches("([0-9|\\.|])*")
                    && (valueText.length() - valueText.replace(".", StringUtils.EMPTY).length() > 1)) {
                AbstractNotificationUtils.getErrorNotification(Constant.FIELD_ERROR, "Only Numbers are allowed");
                value.setValue(StringUtils.EMPTY);
                return;
            }

            saveDiscountProjectionListview();
            boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
            boolean isCustomHierarchy = CUSTOM_VIEW.equalsIgnoreCase(String.valueOf(view.getValue()));
            if (discountProjectionLogic.isAnyRecordChecked(session, isProgram, projectionSelection.getDiscountProgramsList(),
                    isCustomHierarchy)) {

                if (fieldDdlb != null && !Constant.NULL.equals(selectedField) && !selectedField.isEmpty()) {
                    List<String> checkedDiscountNames = new ArrayList<>();
                    if (!isCustomHierarchy) {
                        for (Object discountPropertyId : checkedDiscountsPropertyIds) {
                            checkedDiscountNames.add(discountPropertyId.toString());
                        }
                    } else {
                        checkedDiscountNames.addAll(projectionSelection.getDeductionLevelFilter());
                    }
                    if (((valueDdlb != null && valueDdlb.getValue() != null && !valueDdlbValue.isEmpty()) 
                            && selectedField.equals(Constant.GROUPFCAPS))
                            || (!valueText.isEmpty() && selectedField.equals(Constant.DISCOUNT_RATE_LABEL))
                            || (!valueText.isEmpty() && selectedField.equals("RPU"))
                            || (!valueText.isEmpty() && selectedField.equals(Constant.DISCOUNT_AMOUNT_LABEL))
                            || (!valueText.isEmpty() && selectedField.equals(Constant.GROWTH))) {
                        if (!Constant.GROUPFCAPS.equals(selectedField)) {
                            if (checkedDiscountNames.isEmpty()) {
                                new AbstractNotificationUtils() {
                                    @Override
                                    public void noMethod() {
                                        return;
                                    }

                                    @Override
                                    public void yesMethod() {
                                        List<String> allDiscountNames = new ArrayList<>();
                                        Object[] rightTripleHeaderNames = resultsTable.getRightFreezeAsTable()
                                                .getTripleHeaderVisibleColumns();
                                        for (int i = 0; i < rightTripleHeaderNames.length; i++) {
                                            allDiscountNames.add(String.valueOf(rightTripleHeaderNames[i]));
                                        }
                                        discountRatemassUpdate(allDiscountNames, selectedField, value.getValue(),
                                                projectionSelection.getDiscountProgramsList());
                                    }
                                }.getConfirmationMessage("No Discount Selected",
                                        "You have not selected a discount. The Mass Update value will apply to ALL discounts in the list view.");

                            } else {
                                discountRatemassUpdate(checkedDiscountNames, selectedField, value.getValue(),
                                        projectionSelection.getDiscountProgramsList());
                            }
                        } else {
                            for (Object itemId : resultsTable.getLeftFreezeAsTable().getItemIds()) {
                                DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                                if ((Boolean) dto.getPropertyValue(Constant.CHECKRECORD)
                                        && Constant.CUSTOMER_SMALL.equals(resultsTable.getLeftFreezeAsTable()
                                                .getItem(itemId).getItemProperty("level").getValue().toString())) {
                                    resultsTable.getLeftFreezeAsTable().getItem(itemId).getItemProperty(Constant.GROUP)
                                            .setValue(valueDdlbValue);
                                    ((DiscountProjectionDTO) itemId).setGroup(valueDdlbValue);
                                }
                            }

                            performMassUpdate(new ArrayList<Integer>(), checkedDiscountNames, selectedField,
                                    valueDdlbValue, projectionSelection.getDiscountProgramsList());
                        }

                    } else {
                        NotificationUtils.getErrorNotification("No Value", "Please enter any value to update");
                    }
                } else {
                    NotificationUtils.getErrorNotification("No Field selected", "Please select a Field");
                }
            } else {
                NotificationUtils.getErrorNotification("No records selected",
                        "Please select which levels in the list view the Mass Update applies to. \n" + "AND/OR "
                        + "Please select which discount this Mass Update applies to. ");
            }
        } catch (Property.ReadOnlyException e) {
            LOGGER.error(e.getMessage());

        }
    }

    /**
     * To Mass update the Discount rate
     *
     * @param massUpdatePeriods
     * @param checkedDiscountNames
     * @param selectedField
     * @param value
     */
    private void discountRatemassUpdate(List<String> checkedDiscountNames, String selectedField, String value,
            List<String> selectedPeriods) {

        LOGGER.debug(" checkedDiscounts= {} ", checkedDiscountNames);

        if (startPeriod != null && startPeriod.getValue() != null) {
            String startPeriodValue = startPeriod.getValue().toString();
            int startFreq = 0;
            int startYear = 0;
            int endFreq = 0;
            int endYear = 0;

            // To set startFreq (in No) and startYear
            if (!projectionSelection.getFrequency().equals(MONTHLY.getConstant())) {
                String startFreqNoValue = startPeriodValue.substring(1, NumericConstants.TWO);
                startFreq = isInteger(startFreqNoValue) ? Integer.parseInt(startFreqNoValue) : 0;
            } else {
                String startMonthValue = startPeriodValue.substring(0,
                        startPeriodValue.length() - NumericConstants.FIVE);
                startFreq = CommonUtils.getIntegerForMonth(startMonthValue);
            }
            LOGGER.debug("startPeriodValue-->>= {}", startPeriodValue);
            startYear = Integer.parseInt(startPeriodValue.substring(startPeriodValue.length() - NumericConstants.FOUR));

            // To set endFreq (in No) and endYear
            String endPeriodValue;
            if (endPeriod.getValue() == null || Constant.NULL.equals(String.valueOf(endPeriod.getValue()))
                    || Constant.SELECT_ONE.equals(String.valueOf(endPeriod.getValue()))) {
                endPeriodValue = startPeriodBean.getIdByIndex(startPeriodBean.size() - 1);
            } else {
                endPeriodValue = endPeriod.getValue().toString();
            }
            LOGGER.debug(" endPeriodValue= {} ", endPeriodValue);
            if (!projectionSelection.getFrequency().equals(MONTHLY.getConstant())) {
                String endFreqNoValue = endPeriodValue.substring(1, NumericConstants.TWO);
                endFreq = isInteger(endFreqNoValue) ? Integer.parseInt(endFreqNoValue) : 0;
            } else {
                String endMonthValue = endPeriodValue.substring(0, endPeriodValue.length() - NumericConstants.FIVE);
                endFreq = CommonUtils.getIntegerForMonth(endMonthValue);
            }
            endYear = Integer.parseInt(endPeriodValue.substring(endPeriodValue.length() - NumericConstants.FOUR));

            List<Integer> massUpdatePeriods = new ArrayList<Integer>();
            massUpdatePeriods.add(startFreq);
            massUpdatePeriods.add(startYear);
            massUpdatePeriods.add(endFreq);
            massUpdatePeriods.add(endYear);
            performMassUpdate(massUpdatePeriods, checkedDiscountNames, selectedField, value, selectedPeriods);
        } else {
            NotificationUtils.getErrorNotification("No Start Period selected", "Please Select a Start Period");
            return;
        }
    }

    /**
     * To perform Mass Update
     *
     * @param massUpdatePeriods
     * @param checkedDiscountNames
     * @param selectedField
     * @param value
     */
    private void performMassUpdate(List<Integer> massUpdatePeriods, List<String> checkedDiscountNames,
            String selectedField, String value, List<String> selectedPeriods) {
        boolean isCustomHierarchy = CUSTOM_VIEW.equalsIgnoreCase(String.valueOf(view.getValue()));
        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction())) {
            for (Object itemId : resultsTable.getLeftFreezeAsTable().getItemIds()) {
                DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                ccpsCount = dto.getCcpCount();
            }
        }
        if (Constant.GROUPFCAPS.equals(selectedField)) {
            LOGGER.debug("Group-->= {}", value);
            discountProjectionLogic.massUpdate(session, projectionSelection, massUpdatePeriods, selectedField, value,
                    checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()),
                    getCheckedRecordsForMassUpdate(), selectedPeriods, isCustomHierarchy);
            loadGroupDdlb();
            loadGroupFilterDdlb();
            if (!userGroup.isEmpty()) {
                tableLogic.clearAll();
                tableLogic.setCurrentPage(1);
                isGroupUpdatedManually = false;
            } else {
                CommonLogic.procedureCompletionCheck(session,DISCOUNT,String.valueOf(projectionSelection.getViewOption()));
                refreshTableData(getCheckedRecordsHierarchyNo());
            }
        } else if ("Discount Rate".equals(selectedField)) {
            saveDiscountProjectionListview();
            LOGGER.debug("Discount Rate-->= {}", value);
            discountProjectionLogic.massUpdate(session, projectionSelection, massUpdatePeriods, selectedField, value,
                    checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()),
                    getCheckedRecordsForMassUpdate(), selectedPeriods, isCustomHierarchy);
            CommonLogic.procedureCompletionCheck(session,DISCOUNT,String.valueOf(projectionSelection.getViewOption()));
            refreshTableData(getCheckedRecordsHierarchyNo());
        } else if ("RPU".equals(selectedField)) {
            saveDiscountProjectionListview();
            LOGGER.debug("RPU-->= {}, ccpsCount= {} ", value, ccpsCount);
            discountProjectionLogic.massUpdate(session, projectionSelection, massUpdatePeriods, selectedField, value,
                    checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()),
                    getCheckedRecordsForMassUpdate(), selectedPeriods, isCustomHierarchy);
            CommonLogic.procedureCompletionCheck(session,DISCOUNT,String.valueOf(projectionSelection.getViewOption()));
            refreshTableData(getCheckedRecordsHierarchyNo());
        } else if (Constant.DISCOUNT_AMOUNT_LABEL.equals(selectedField)) {
            ccpsCount = 0;
            saveDiscountProjectionListview();
            LOGGER.debug("Discount Amount-->= {}, ccpsCount= {}", value, ccpsCount);

            for (int i = 0; i < checkedDiscountNames.size(); i++) {
                for (int j = 0; j < ccpsCountForMassUpdate.size(); j++) {
                    if (ccpsCountForMassUpdate.getIndex(j).getKey().toString()
                            .startsWith(checkedDiscountNames.get(i))) {
                        ccpsCount += ccpsCountForMassUpdate.getInt(ccpsCountForMassUpdate.getIndex(j).getKey());
                    }
                }
            }
            discountProjectionLogic.massUpdate(session, projectionSelection, massUpdatePeriods, selectedField,
                    String.valueOf(value), checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()),
                    getCheckedRecordsForMassUpdate(), selectedPeriods, isCustomHierarchy);
            CommonLogic.procedureCompletionCheck(session,DISCOUNT,String.valueOf(projectionSelection.getViewOption()));
            refreshTableData(getCheckedRecordsHierarchyNo());
        } else if (Constant.GROWTH.equals(selectedField)) {
            saveDiscountProjectionListview();
            LOGGER.debug("Growth-->= {}", value);
            discountProjectionLogic.massUpdate(session, projectionSelection, massUpdatePeriods, selectedField, value,
                    checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()),
                    getCheckedRecordsForMassUpdate(), selectedPeriods, isCustomHierarchy);
            CommonLogic.procedureCompletionCheck(session,DISCOUNT,String.valueOf(projectionSelection.getViewOption()));
            refreshTableData(getCheckedRecordsHierarchyNo());
        }

    }

    @Override
    protected void calculateBtnClickLogic() {
        try {
            CommonLogic.viewProceduresCompletionCheckDiscount(session);
            session.setFunctionMode("CALC");

            if (CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue())) {
                CommonUtil.getInstance().waitsForOtherThreadsToComplete(
                        session.getFutureValue(Constant.CALL_PRC_CONTRACT_DETAILS_REBATE));
            }
            Set<String> setMethodologiesValuesVal = new HashSet();
            setMethodologiesValuesVal.
                    addAll(Arrays.asList(new String[]{Constant.SINGLE_PERIOD, "% of Demand", "% of Inventory Withdrawal", Constant.PERC_OF_ADJUSTED_DEMAND}));
            String endValue;
            List<String> checkedDiscountNames = new ArrayList<>();
            List<String> checkedDiscountNamesList = new ArrayList<>();
            if (endPeriodForecastTab.getValue() != null) {
                endValue = endPeriodForecastTab.getValue().toString().replace(' ', '~').trim();
            } else {
                endValue = forecaststartBean.getIdByIndex(forecaststartBean.size() - 1);
            }
            if ((methodologyDdlb.getValue() != null && !methodologyDdlb.getValue().equals(SELECT_ONE.getConstant()))
                    && !String.valueOf(methodologyDdlb.getValue().toString().trim()).equals(Constant.NULL)) {
                if (endDateValidation(endValue)) {
                    if (startPeriodForecastTab.getValue() != null
                            && !startPeriodForecastTab.getValue().equals(SELECT_ONE.getConstant())) {
                        if (!CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue())
                                && !AVERAGE.getConstant().equals(methodologyDdlb.getValue())
                                && !ROLLING_ANNUAL_TREND.getConstant().equals(methodologyDdlb.getValue())
                                && !Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND.equals(methodologyDdlb.getValue())
                                && !Constant.SINGLE_PERIOD.equals(methodologyDdlb.getValue())
                                && (checkBoxMap.size() == 0 || checkedDiscountsPropertyIds.size() != checkBoxMap.size()) && !PER_EX_FACTORY_SALES.getConstant().equals(methodologyDdlb.getValue())) {
                            NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED, PLEASE_SELECT_A_HISTORIC_ALERT);
                        } else if (baseLineCalc(startPeriodForecastTab.getValue().toString(),
                                endValue.replace('~', ' '))
                                || methodologyDdlb.getValue().equals(CONTRACT_DETAILS.getConstant())) {
                            boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
                            boolean isCustomHierarchy = Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY
                                    .equals(view.getValue());
                            if (discountProjectionLogic.isAnyRecordChecked(session, isProgram,
                                    projectionSelection.getDiscountProgramsList(), isCustomHierarchy)) {
                                if (fileAlertForPFDChanges()) {

                                    if ((methodologyDdlb.getValue().equals(ROLLING_ANNUAL_TREND.getConstant())
                                            && !projectionSelection.getFrequency().equals(ANNUALLY.getConstant())
                                            && !rollingAnnualTrendCount())
                                            || (methodologyDdlb.getValue()
                                                    .equals(Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND)
                                            && !projectionSelection.getFrequency()
                                                    .equals(ANNUALLY.getConstant())
                                            && !exFactoryMethodologyCheck())) {
                                        NotificationUtils.getErrorNotification("Select complete annual period",
                                                "The " + methodologyDdlb.getValue() + " methodology requires"
                                                + " a complete calendar year of periods to use as a baseline."
                                                + "  Please select a complete calendar year of periods "
                                                + "for each selected discount and try again.");
                                    } else if (checkedDiscountsPropertyIds.isEmpty()) {
                                        NotificationUtils.getErrorNotification("No Discount selected",
                                                "Please select atleast one discount.");
                                    } else if (methodologyDdlb.getValue().equals(AVERAGE.getConstant())
                                            && (checkedDiscountsPropertyIds.size() != tripleHeaderForCheckedDoubleHeader
                                            .size() || !checkHistorySelectedCount(NumericConstants.TWO))) {
                                        NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED,
                                                "Please select at least two historic periods to use as a baseline for each selected discount.");
                                    } else if (setMethodologiesValuesVal
                                            .contains(String.valueOf(methodologyDdlb.getValue()))
                                            && !checkHistorySelectedCount(0)) {
                                        NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED, PLEASE_SELECT_A_HISTORIC_ALERT);
                                    } else if (setMethodologiesValuesVal
                                            .contains(String.valueOf(methodologyDdlb.getValue()))
                                            && !checkHistorySelectedCount(1)) {
                                        NotificationUtils.getErrorNotification("More than one period selected",
                                                Constant.HISTORIC_PERIOD_SELECTION_FOR_SELECTED_DISCOUNTS);
                                    } else if ("Historic % of Business".equals(methodologyDdlb.getValue())
                                            && !checkHistorySelectedCount(0)) {
                                        NotificationUtils.getErrorNotification(Constant.ERROR,
                                                Constant.HISTORIC_PERIOD_SELECTION);
                                    } else if (!CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue())
                                            && radioMap.size() == 0) {
                                        NotificationUtils.getErrorNotification(
                                                Constant.VARIABLE_TYPE_SELECTION_CONFIRMATION,
                                                Constant.VARIABLE_TYPE_SELECTION);
                                    } else {
                                        boolean doMethodology = false;
                                        if (radioMap.size() != 0) {
                                            doMethodology = true;
                                        } else if (CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue())) {
                                            doMethodology = true;
                                        } else {
                                            NotificationUtils.getErrorNotification(
                                                    Constant.VARIABLE_TYPE_SELECTION_CONFIRMATION,
                                                    Constant.VARIABLE_TYPE_SELECTION);
                                        }
                                        if (doMethodology) {
                                            if (getCalculationBased()) {
                                                projectionSelection
                                                        .setMethodology(String.valueOf(methodologyDdlb.getValue()));
                                                projectionSelection.setFromDateDdlb(discountProjectionLogic.getPeriodSid(
                                                        String.valueOf(startPeriodForecastTab.getValue()),
                                                        String.valueOf(frequencyDdlb.getValue()), "Min"));
                                                projectionSelection
                                                        .setToDateDdlb(discountProjectionLogic.getPeriodSid(endValue.replace('~', ' '),
                                                                String.valueOf(frequencyDdlb.getValue()), "Max"));
                                                projectionSelection.setCalcBased(calcBase);
                                                projectionSelection
                                                        .setDiscountLevel(String.valueOf(discType.getValue()));
                                                if (checkedDiscountNames.size() != 0) {
                                                    for (Object discountPropertyId : checkedDiscountsPropertyIds) {
                                                        checkedDiscountNames.add(resultsTable.getRightFreezeAsTable()
                                                                .getTripleHeaderColumnHeader(discountPropertyId));
                                                    }
                                                } else if (CONTRACT_DETAILS.getConstant()
                                                        .equals(methodologyDdlb.getValue())) {
                                                    checkedDiscountNames = Arrays.asList(resultsTable
                                                            .getRightFreezeAsTable().getTripleHeaderColumnHeaders());
                                                }
                                                for (Object discountPropertyId : checkedDiscountsPropertyIds) {
                                                    checkedDiscountNamesList.add(resultsTable.getRightFreezeAsTable()
                                                            .getTripleHeaderColumnHeader(discountPropertyId));
                                                }
                                                discountProjectionLogic.calcDataUpdate(session, projectionSelection,
                                                        String.valueOf(level.getValue()),
                                                        tripleHeaderForCheckedDoubleHeader, checkedDiscountNames,
                                                        isProgram, view.equals(CUSTOM.getConstant()));
                                                session.setFrequency(String.valueOf(frequencyDdlb.getValue()));
                                                commonLogic.insertPFDTemp(session,
                                                        String.valueOf(methodologyDdlb.getValue()), StringUtils.EMPTY,
                                                        false);
                                                if ("Rolling Annual Trend"
                                                        .equals(String.valueOf(methodologyDdlb.getValue()))
                                                        || Constant.SINGLE_PERIOD
                                                                .equals(String.valueOf(methodologyDdlb.getValue()))
                                                        || "Average"
                                                                .equals(String.valueOf(methodologyDdlb.getValue()))) {
                                                    cumulativeCalculation(projectionSelection,
                                                            String.valueOf(methodologyDdlb.getValue()),
                                                            "st_disc_growth_factor_", checkedDiscountNamesList,
                                                            String.valueOf(level.getValue()));
                                                }
                                                discountProjectionLogic.callDPProcedure(session, projectionSelection);
                                                CommonLogic.updateFlagStatusToRForAllViewsDiscount(session, Constant.DISCOUNT3);
                                                new DataSelectionLogic().callViewInsertProceduresThread(session, Constant.DISCOUNT3,"","","");
                                                CommonLogic.procedureCompletionCheck(session, DISCOUNT, String.valueOf(projectionSelection.getViewOption()));
                                                refreshTableData(getCheckedRecordsHierarchyNo());
                                                final Notification notif = new Notification(
                                                        Constant.CALCULATION_COMPLETE,
                                                        Notification.Type.HUMANIZED_MESSAGE);
                                                notif.setPosition(Position.TOP_CENTER);
                                                notif.setStyleName(ConstantsUtils.MY_STYLE);
                                                notif.show(Page.getCurrent());
                                            } else {
                                                NotificationUtils.getErrorNotification(
                                                        "Different variable types selected",
                                                        "Please select only one variable type across the baseline periods.");

                                            }
                                        }
                                    }
                                } else {
                                    NotificationUtils.getAlertNotification("New File Activated", "There is a new file "
                                            + session.getFileName()
                                            + " that has been activated. Please recalculate the Sales Projection, since it is currently based off of outdated data");
                                }
                            } else {
                                NotificationUtils.getErrorNotification("No Hierarchy level selected",
                                        "Please select a level in the hierarchy for the methodology");
                            }
                        } else if (baselineFlag == 1) {
                            AbstractNotificationUtils.getErrorNotification("Baseline period within calculation range",
                                    "The selected baseline periods are within the Start Period and End Period range for one or more of the selected discounts.  Please select a baseline period that is prior to the calculation range for each of the selected discounts.");
                        } else if (baselineFlag == NumericConstants.TWO) {
                            AbstractNotificationUtils.getErrorNotification("Baseline period after calculation range",
                                    "The selected baseline periods are after the Start Period and End Period range for one or more of the selected discounts.  Please select a baseline period that is prior to the calculation range for each of the selected discounts.");
                        }

                    } else {
                        NotificationUtils.getErrorNotification(Constant.NO_START_PERIOD_SELECTED,
                                "Please select a Start Period");
                    }

                } else if (errorFlag) {
                    AbstractNotificationUtils.getErrorNotification("End Period is before Start Period",
                            "The Start Period must be before the End Period.Please try again");
                    errorFlag = false;
                }
            } else {
                NotificationUtils.getErrorNotification("No Methodology selected", "Please select a Methodology");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    protected void customCalculateBtnClickLogic() {
        try {
             session.setFunctionMode("CALC");
            if (CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue())) {
                CommonUtil.getInstance().waitsForOtherThreadsToComplete(
                        session.getFutureValue(Constant.CALL_PRC_CONTRACT_DETAILS_REBATE));
            }
            Set<String> setMethodologiesValuesVal = new HashSet();
            setMethodologiesValuesVal
                    .addAll(Arrays.asList(new String[]{Constant.SINGLE_PERIOD, "% of Ex-Factory Sales", "% of Demand",
                "% of Inventory Withdrawal", Constant.PERC_OF_ADJUSTED_DEMAND}));
            String endValue;
            List<String> checkedDiscountNames = new ArrayList<>();
            List<String> checkedDiscountNamesList = new ArrayList<>();
            if (endPeriodForecastTab.getValue() != null) {
                endValue = endPeriodForecastTab.getValue().toString().replace(' ', '~').trim();
            } else {
                endValue = forecaststartBean.getIdByIndex(forecaststartBean.size() - 1);
            }
            if ((methodologyDdlb.getValue() != null && !methodologyDdlb.getValue().equals(SELECT_ONE.getConstant()))
                    && !String.valueOf(methodologyDdlb.getValue().toString().trim()).equals(Constant.NULL)) {
                if (endDateValidation(endValue)) {
                    if (startPeriodForecastTab.getValue() != null
                            && !startPeriodForecastTab.getValue().equals(SELECT_ONE.getConstant())) {
                        if (!CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue())
                                && !AVERAGE.getConstant().equals(methodologyDdlb.getValue())
                                && !ROLLING_ANNUAL_TREND.getConstant().equals(methodologyDdlb.getValue())
                                && !Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND.equals(methodologyDdlb.getValue())
                                && (checkBoxMap.size() == 0)) {
                            NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED,
                                    "Please select a Historic Period .");
                        } else if (baseLineCalc(startPeriodForecastTab.getValue().toString(),
                                endValue.replace('~', ' '))
                                || methodologyDdlb.getValue().equals(CONTRACT_DETAILS.getConstant())) {
                            boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
                            boolean isCustomHierarchy = Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY
                                    .equals(view.getValue());
                            if (discountProjectionLogic.isAnyRecordChecked(session, isProgram,
                                    projectionSelection.getDiscountProgramsList(), isCustomHierarchy)) {
                                if (fileAlertForPFDChanges()) {
                                    if ((methodologyDdlb.getValue().equals(ROLLING_ANNUAL_TREND.getConstant())
                                            || methodologyDdlb.getValue()
                                                    .equals(Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND))
                                            && !projectionSelection.getFrequency().equals(ANNUALLY.getConstant())
                                            && !rollingAnnualTrendCount()) {
                                        NotificationUtils.getErrorNotification("Select complete annual period",
                                                "The " + methodologyDdlb.getValue() + " methodology requires"
                                                + " a complete calendar year of periods to use as a baseline."
                                                + "  Please select a complete calendar year of periods "
                                                + "for each selected discount and try again.");
                                    } else if (methodologyDdlb.getValue().equals(AVERAGE.getConstant())
                                            && (!checkHistorySelectedCountCustom(NumericConstants.TWO))) {
                                        NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED,
                                                "Please select at least two historic periods to use as a baseline.");
                                    } else if (setMethodologiesValuesVal
                                            .contains(String.valueOf(methodologyDdlb.getValue()))
                                            && !checkHistorySelectedCountCustom(0)) {
                                        NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED, PLEASE_SELECT_A_HISTORIC_ALERT);
                                    } else if (setMethodologiesValuesVal
                                            .contains(String.valueOf(methodologyDdlb.getValue()))
                                            && !checkHistorySelectedCountCustom(1)) {
                                        NotificationUtils.getErrorNotification("More than one period selected",
                                                "Please select only one period to use as a baseline.");
                                    } else if ("Historic % of Business".equals(methodologyDdlb.getValue())
                                            && !checkHistorySelectedCountCustom(0)) {
                                        NotificationUtils.getErrorNotification(Constant.ERROR,
                                                "Please select a historic period to use as a baseline.");
                                    } else if (!CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue())
                                            && radioMap.size() == 0) {
                                        NotificationUtils.getErrorNotification(
                                                Constant.VARIABLE_TYPE_SELECTION_CONFIRMATION,
                                                Constant.VARIABLE_TYPE_SELECTION);
                                    } else {
                                        boolean doMethodology = false;
                                        if (radioMap.size() != 0) {
                                            doMethodology = true;
                                        } else if (CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue())) {
                                            doMethodology = true;
                                        } else {
                                            NotificationUtils.getErrorNotification(
                                                    Constant.VARIABLE_TYPE_SELECTION_CONFIRMATION,
                                                    Constant.VARIABLE_TYPE_SELECTION);
                                        }
                                        if (doMethodology) {
                                            if (getCalculationBased()) {
                                                projectionSelection
                                                        .setMethodology(String.valueOf(methodologyDdlb.getValue()));
                                                projectionSelection.setFromDateDdlb(discountProjectionLogic.getPeriodSid(
                                                        String.valueOf(startPeriodForecastTab.getValue()),
                                                        String.valueOf(frequencyDdlb.getValue()), "Min"));
                                                projectionSelection
                                                        .setToDateDdlb(discountProjectionLogic.getPeriodSid(endValue.replace('~', ' '),
                                                                String.valueOf(frequencyDdlb.getValue()), "Max"));
                                                projectionSelection.setCalcBased(calcBase);
                                                projectionSelection
                                                        .setDiscountLevel(String.valueOf(discType.getValue()));
                                                if (checkedDiscountNames.size() != 0) {
                                                    for (Object discountPropertyId : checkedDiscountsPropertyIds) {
                                                        checkedDiscountNames.add(resultsTable.getRightFreezeAsTable()
                                                                .getTripleHeaderColumnHeader(discountPropertyId));
                                                    }
                                                } else if (CONTRACT_DETAILS.getConstant()
                                                        .equals(methodologyDdlb.getValue())) {
                                                    checkedDiscountNames = Arrays.asList(resultsTable
                                                            .getRightFreezeAsTable().getTripleHeaderColumnHeaders());
                                                }
                                                for (Object discountPropertyId : checkedDiscountsPropertyIds) {
                                                    checkedDiscountNamesList.add(resultsTable.getRightFreezeAsTable()
                                                            .getTripleHeaderColumnHeader(discountPropertyId));
                                                }
                                                discountProjectionLogic.calcDataUpdate(session, projectionSelection,
                                                        String.valueOf(level.getValue()),
                                                        tripleHeaderForCheckedDoubleHeader, checkedDiscountNames,
                                                        isProgram, view.getValue().equals(CUSTOM.getConstant()));
                                                session.setFrequency(String.valueOf(frequencyDdlb.getValue()));
                                                commonLogic.insertPFDTemp(session,
                                                        String.valueOf(methodologyDdlb.getValue()), StringUtils.EMPTY,
                                                        false);

                                                if ("Rolling Annual Trend"
                                                        .equals(String.valueOf(methodologyDdlb.getValue()))
                                                        || Constant.SINGLE_PERIOD
                                                                .equals(String.valueOf(methodologyDdlb.getValue()))
                                                        || "Average"
                                                                .equals(String.valueOf(methodologyDdlb.getValue()))) {
                                                    cumulativeCalculation(projectionSelection,
                                                            String.valueOf(methodologyDdlb.getValue()),
                                                            "st_disc_growth_factor_", session.getSelectedRsForCustom(),
                                                            String.valueOf(level.getValue()));
                                                }
                                                
                                                discountProjectionLogic.callDPProcedure(session, projectionSelection);
                                                new DataSelectionLogic().callViewInsertProceduresThread(session, Constant.DISCOUNT3,"","","");
                                                CommonUtil.getInstance().waitForSeconds();
                                                CommonLogic.procedureCompletionCheck(session, DISCOUNT, com.stpl.app.serviceUtils.Constants.CUSTOM);
                                                refreshTableData(getCheckedRecordsHierarchyNo());
                                                final Notification notif = new Notification(
                                                        Constant.CALCULATION_COMPLETE,
                                                        Notification.Type.HUMANIZED_MESSAGE);
                                                notif.setPosition(Position.TOP_CENTER);
                                                notif.setStyleName(ConstantsUtils.MY_STYLE);
                                                notif.show(Page.getCurrent());
                                            } else {
                                                NotificationUtils.getErrorNotification(
                                                        "Different variable types selected",
                                                        "Please select only one variable type across the baseline periods.");

                                            }
                                        }
                                    }
                                } else {
                                    NotificationUtils.getAlertNotification("New File Activated", "There is a new file "
                                            + session.getFileName()
                                            + " that has been activated. Please recalculate the Sales Projection, since it is currently based off of outdated data");
                                }
                            } else {
                                NotificationUtils.getErrorNotification("No Hierarchy level selected",
                                        "Please select a level in the hierarchy for the methodology");
                            }
                        } else if (baselineFlag == 1) {
                            AbstractNotificationUtils.getErrorNotification("Baseline period within calculation range",
                                    "The selected baseline periods are within the Start Period and End Period range for one or more of the selected discounts.  Please select a baseline period that is prior to the calculation range for each of the selected discounts.");
                        } else if (baselineFlag == NumericConstants.TWO) {
                            AbstractNotificationUtils.getErrorNotification("Baseline period after calculation range",
                                    "The selected baseline periods are after the Start Period and End Period range for one or more of the selected discounts.  Please select a baseline period that is prior to the calculation range for each of the selected discounts.");
                        }

                    } else {
                        NotificationUtils.getErrorNotification(Constant.NO_START_PERIOD_SELECTED,
                                "Please select a Start Period");
                    }

                } else if (errorFlag) {
                    AbstractNotificationUtils.getErrorNotification("End Period is before Start Period",
                            "The Start Period must be before the End Period.Please try again");
                    errorFlag = false;
                }
            } else {
                NotificationUtils.getErrorNotification("No Methodology selected", "Please select a Methodology");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private boolean fileAlertForPFDChanges() {
        try {
            if (Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction())) {
                return true;
            } else if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) && session.isIsSalesCalculated()
                    && session.getDiscountCanBeCalculated(
                            commonLogic.getFileMethodologyName(session, String.valueOf(methodologyDdlb.getValue())))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return false;
    }

    @Override
    protected void newBtnClickLogic() {
        session.setTabName(DISCOUNT);
        LOGGER.debug("newCustomHierarchhy clickEvent method starts");
        final CustomTreeBuild customTree = new CustomTreeBuild(session);
        customTree.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (customTree.isIsSelect()) {
                    customIdToSelect = customTree.getCustomId();
                }
                session.setCustomerViewList(CommonLogic.getCustomViewList(session.getProjectionId()));
                loadCustomDDLB();
            }
        });
        UI.getCurrent().addWindow(customTree);
        LOGGER.debug("newCustomHierarchhy clickEvent method ends");
    }

    @Override
    protected void editBtnClickLogic() {
        session.setTabName(DISCOUNT);
        LOGGER.debug("Entering editHierarchyBtn");
        if (CommonLogic.editButtonValidation(viewDdlb, customViewList)) {
            final CustomTreeBuild customTree = new CustomTreeBuild(session, customId);
            customTree.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {
                    customIdToSelect = customTree.getCustomId();
                    session.setCustomerViewList(CommonLogic.getCustomViewList(session.getProjectionId()));
                    loadCustomDDLB();
                }
            });
            UI.getCurrent().addWindow(customTree);
        }
        LOGGER.debug(" Ending editHierarchyBtn ");
    }

    @Override
    protected void adjustBtnClickLogic() {
        CommonLogic.viewProceduresCompletionCheckDiscount(session);
        session.setFunctionMode("ADJ");
        if (isGroupUpdatedManually) {
            NotificationUtils.getAlertNotification(Constant.GROUP_FILTER_CONFLICT, Constant.GROUP_VALUE_VERIFICATION);
            return;
        }
        final String adjustmentType = String.valueOf(type.getValue());
        final String adjustmentBasis = String.valueOf(basis.getValue());
        final String adjustmentValue = adjustment.getValue();
        final String allocationMethodology = String.valueOf(allocMethodology.getValue());
        List<String> selectedDoubleList = new ArrayList<>();
        saveDiscountProjectionListview();
        if (!adjustmentValue.matches("[-+]?(?:\\d+|\\d*(?:\\.\\d{1,2})?)")) {
            AbstractNotificationUtils.getErrorNotification(Constant.FIELD_ERROR,
                    "Please enter a numeric value in the Adjustment text box");
            adjustment.setValue(StringUtils.EMPTY);
            return;
        }

        if (!Constant.NULL.equals(allocationMethodology)) {

            if (!adjustmentValue.replace(" ", StringUtils.EMPTY).isEmpty()) {

                if (!checkedDiscountsPropertyIds.isEmpty()) {

                    List<String> headerList = new ArrayList<>();
                    for (Object propertyId : checkedDiscountsPropertyIds) {
                        String tripleHeader = resultsTable.getRightFreezeAsTable()
                                .getTripleHeaderColumnHeader(propertyId);
                        headerList.add(tripleHeader);
                    }
                    List<String> remoList = new ArrayList<>(tripleHeaderForCheckedDoubleHeader.keySet());
                    remoList.removeAll(headerList);

                    for (Object propertyId : checkedDiscountsPropertyIds) {

                        String tripleHeader = resultsTable.getRightFreezeAsTable()
                                .getTripleHeaderColumnHeader(propertyId);
                        if (tripleHeaderForCheckedDoubleHeader.get(tripleHeader) == null) {
                            NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED,
                                    Constant.ADJUSTMENT_CONFIRMATION);
                            return;
                        }

                        if (Constant.HISTORICAL_OF_BUSINESS.equals(allocationMethodology)) {
                            baselinePeriods = tripleHeaderForCheckedDoubleHeader.get(tripleHeader).get("H");
                        } else {
                            baselinePeriods = tripleHeaderForCheckedDoubleHeader.get(tripleHeader)
                                    .get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                        }

                        if ((Constant.HISTORICAL_OF_BUSINESS.equals(allocationMethodology))
                                && (baselinePeriods == null || baselinePeriods.isEmpty())) {
                            NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED,
                                    Constant.HISTORIC_PERIOD_SELECTION);
                            return;
                        }

                        List<String> selectedPeriods = tripleHeaderForCheckedDoubleHeader.get(tripleHeader)
                                .get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                        List<String> selectedHistPeriods = tripleHeaderForCheckedDoubleHeader.get(tripleHeader)
                                .get("H");
                        if (selectedHistPeriods != null && !selectedHistPeriods.isEmpty()) {
                            selectedDoubleList.addAll(selectedHistPeriods);
                        }
                        if (selectedPeriods != null && !selectedPeriods.isEmpty()) {
                            selectedDoubleList.addAll(selectedPeriods);
                        }
                        if (selectedPeriods == null || selectedPeriods.isEmpty()) {
                            NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED,
                                    Constant.ADJUSTMENT_CONFIRMATION);
                            LOGGER.error("Please select the projection periods for the discounts= {} ", tripleHeader);
                            return;
                        }
                    }
                    boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
                    boolean isCustomHierarchy = Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(view.getValue());
                    if (!hierarchyListForCheckRecord.isEmpty()) {
                        discountProjectionLogic.updateCheckRecordForAdjust(checkedDiscountsPropertyIds, hierarchyListForCheckRecord, session, hierarchyIndicator);
                    }
                    if (discountProjectionLogic.isAnyRecordChecked(session, isProgram, projectionSelection.getDiscountProgramsList(),
                            isCustomHierarchy)) {

                        if (discountProjectionLogic.adjustDiscountProjectionValidation(projectionSelection)) {
                            NotificationUtils.getErrorNotification("Error", "When using the ‘% of Ex-Factory’ methodology, a product cannot be included in multiple selected contract, customer, and product combinations. Please update the selections");
                            return;
                        }

                        String confirmMessage = Constant.INCREMENTAL_ADJUSTMENT_CONFIRMATION;
                        String messageBody = StringUtils.EMPTY;
                        String adjustmentValidation = adjustmentBasis.equals(PERCENTAGE.getConstant())
                                ? adjustment.getValue().concat(PERCENT.getConstant()) : DOLLAR.getConstant().concat(adjustment.getValue());
                        if (adjustmentType.equals(INCREMENTAL.getConstant())) {
                            confirmMessage = Constant.INCREMENTAL_ADJUSTMENT_CONFIRMATION;
                            messageBody = "You are about to make the following " + adjustmentValidation + " adjustment for the selected periods" + selectedDoubleList
                                    + Constant.CONTINUE_CONFIRMATION;
                        } else if (adjustmentType.equals(OVERRIDE.getConstant())) {
                            confirmMessage = "Confirm Override";
                            messageBody = "You are about to replace the current values in the list view with the following variable: "
                                    + adjustmentValidation + Constant.CONTINUE_CONFIRMATION;
                        }

                        new AbstractNotificationUtils() {
                            @Override
                            public void noMethod() {
                                return;
                            }

                            @Override
                            public void yesMethod() {
                                if (discountProjectionLogic.adjustmentDataUpdate(projectionSelection.getFrequency(),
                                        allocationMethodology, tripleHeaderForCheckedDoubleHeader)) {
                                    ExtPagedTreeTable rightTable = resultsTable.getRightFreezeAsTable();
                                    List<String> selectedDiscountList = new ArrayList<>();
                                    List<String> checkedDiscountList = new ArrayList<>();
                                    for (Object obj : rightHeader.getTripleColumns()) {
                                        if (!rightTable.getTripleHeaderColumnCheckBox(obj)) {
                                            selectedDiscountList.add(rightHeader.getTripleHeader(obj));
                                        }
                                        if (rightTable.getTripleHeaderColumnCheckBox(obj)) {
                                            checkedDiscountList.add(rightHeader.getTripleHeader(obj));
                                        }
                                    }
                                    boolean isProgram = PROGRAM.getConstant().equals(level.getValue());

                                    session.setFrequency(projectionSelection.getFrequency());
                                    if (discountProjectionLogic.adjustDiscountProjection(session, adjustmentType, adjustmentBasis,
                                            adjustmentValue, Constant.STRING_ONE, baselinePeriods)) {
                                        LOGGER.debug(" Procedure executed Successfully");
                                        try {
                                            TimeUnit.SECONDS.sleep(3);
                                        } catch (InterruptedException ex) {
                                            LOGGER.error(" Procedure Error {}",ex); 
                                           Thread.currentThread().interrupt();
                                        }
                                        CommonLogic.procedureCompletionCheck(session, DISCOUNT, String.valueOf(projectionSelection.getViewOption()));
                                        refreshTableData(getCheckedRecordsHierarchyNo());
                                    } else {
                                        discountProjectionLogic.checkUncheckRebateBeforeAdjust(true, selectedDiscountList, session, false,
                                                isProgram);
                                        LOGGER.error(" Error to update data for adjustmnet. Procedure not executed");
                                    }
                                } else {
                                    LOGGER.error(" Error to Update value for Adjust");
                                }

                            }
                        }.getOkCancelMessage(confirmMessage, messageBody);
                    } else {
                        NotificationUtils.getErrorNotification("No level selected",
                                "Please select a level this adjustment applies to");
                    }

                } else {
                    NotificationUtils.getErrorNotification("No Discount (Program) selected",
                            "Please select which discount(s) this adjustment applies to.");
                }
            } else {
                NotificationUtils.getErrorNotification("No adjustment entered",
                        "Please enter a numeric value in the Adjustment text box.");
            }
        } else {
            NotificationUtils.getErrorNotification("No Allocation Methodology selected",
                    "Please select an Allocation Methodology");
        }
    }

    @Override
    protected void adjustBtnClickLogicCustom() {
        CommonLogic.viewProceduresCompletionCheckDiscount(session);
        session.setFunctionMode("ADJ");

        try {
            if (isGroupUpdatedManually) {
                NotificationUtils.getAlertNotification(Constant.GROUP_FILTER_CONFLICT,
                        Constant.GROUP_VALUE_VERIFICATION);
                return;
            }
            final String adjustmentType = String.valueOf(type.getValue());
            final String adjustmentBasis = String.valueOf(basis.getValue());
            final String adjustmentValue = adjustment.getValue();
            final String allocationMethodology = String.valueOf(allocMethodology.getValue());
            List<String> selectedDoubleList = new ArrayList<>();
            saveDiscountProjectionListview();
            if (!adjustmentValue.matches("[-+]?(?:\\d+|\\d*(?:\\.\\d{1,2})?)")) {
                AbstractNotificationUtils.getErrorNotification(Constant.FIELD_ERROR,
                        "Please enter a numeric value in the Adjustment text box");
                adjustment.setValue(StringUtils.EMPTY);
                return;
            }

            if (!Constant.NULL.equals(allocationMethodology)) {

                if (!adjustmentValue.replace(" ", StringUtils.EMPTY).isEmpty()) {

                    if (tripleHeaderForCheckedDoubleHeaderCustom.get(Constant.CUSTOM_LABEL) == null) {
                        NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED,
                                Constant.ADJUSTMENT_CONFIRMATION);
                        return;
                    }

                    if (Constant.HISTORICAL_OF_BUSINESS.equals(allocationMethodology)) {
                        baselinePeriods = tripleHeaderForCheckedDoubleHeaderCustom.get(Constant.CUSTOM_LABEL).get("H");
                    } else {
                        baselinePeriods = tripleHeaderForCheckedDoubleHeaderCustom.get(Constant.CUSTOM_LABEL)
                                .get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                    }

                    if ((Constant.HISTORICAL_OF_BUSINESS.equals(allocationMethodology))
                            && (baselinePeriods == null || baselinePeriods.isEmpty())) {
                        NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED,
                                Constant.HISTORIC_PERIOD_SELECTION);
                        return;
                    }

                    List<String> selectedPeriods = tripleHeaderForCheckedDoubleHeaderCustom.get(Constant.CUSTOM_LABEL)
                            .get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                    List<String> selectedHistPeriods = tripleHeaderForCheckedDoubleHeaderCustom.get(Constant.CUSTOM_LABEL)
                            .get("H");
                    if (selectedHistPeriods != null && !selectedHistPeriods.isEmpty()) {
                        selectedDoubleList.addAll(selectedHistPeriods);
                    }
                    if (selectedPeriods != null && !selectedPeriods.isEmpty()) {
                        selectedDoubleList.addAll(selectedPeriods);
                    }
                    if (selectedPeriods == null || selectedPeriods.isEmpty()) {
                        NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED,
                                Constant.ADJUSTMENT_CONFIRMATION);
                        return;
                    }
                    boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
                    boolean isCustomHierarchy = Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(view.getValue());
                    if (discountProjectionLogic.isAnyRecordChecked(session, isProgram, projectionSelection.getDiscountProgramsList(),
                            isCustomHierarchy)) {

                        if (discountProjectionLogic.adjustDiscountProjectionValidation(projectionSelection)) {
                            NotificationUtils.getErrorNotification("Error", "When using the ‘% of Ex-Factory’ methodology, a product cannot be included in multiple selected contract, customer, and product combinations. Please update the selections");
                            return;
                        }
                        String confirmMessage = Constant.INCREMENTAL_ADJUSTMENT_CONFIRMATION;
                        String messageBody = StringUtils.EMPTY;
                        String adjustmentValidation = adjustmentBasis.equals(PERCENTAGE.getConstant())
                                ? adjustment.getValue().concat(PERCENT.getConstant()) : DOLLAR.getConstant().concat(adjustment.getValue());

                        if (adjustmentType.equals(INCREMENTAL.getConstant())) {
                            confirmMessage = Constant.INCREMENTAL_ADJUSTMENT_CONFIRMATION;
                            messageBody = "You are about to make the following " + adjustmentValidation + " adjustment for the selected periods" + selectedDoubleList
                                    + Constant.CONTINUE_CONFIRMATION;
                        } else if (adjustmentType.equals(OVERRIDE.getConstant())) {
                            confirmMessage = "Confirm Override";
                            messageBody = "You are about to replace the current values in the list view with the following variable: "
                                    + adjustmentValidation + Constant.CONTINUE_CONFIRMATION;
                        }

                        new AbstractNotificationUtils() {
                            @Override
                            public void noMethod() {
                                return;
                            }

                            @Override
                            public void yesMethod() {
                                if (discountProjectionLogic.adjustmentDataUpdate(projectionSelection.getFrequency(),
                                        allocationMethodology, tripleHeaderForCheckedDoubleHeaderCustom)) {
                                    ExtPagedTreeTable rightTable = resultsTable.getRightFreezeAsTable();
                                    List<String> selectedDiscountList = new ArrayList<>();
                                    List<String> checkedDiscountList = new ArrayList<>();
                                    for (Object obj : rightHeader.getTripleColumns()) {
                                        if (!rightTable.getTripleHeaderColumnCheckBox(obj)) {
                                            selectedDiscountList.add(rightHeader.getTripleHeader(obj));
                                        }
                                        if (rightTable.getTripleHeaderColumnCheckBox(obj)) {
                                            checkedDiscountList.add(rightHeader.getTripleHeader(obj));
                                        }
                                    }
                                    boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
                                    discountProjectionLogic.checkUncheckRebateBeforeAdjust(false, checkedDiscountList, session, true,
                                            isProgram);
                                    session.setFrequency(projectionSelection.getFrequency());
                                    if (discountProjectionLogic.adjustDiscountProjection(session, adjustmentType, adjustmentBasis,
                                            adjustmentValue, Constant.STRING_ONE, baselinePeriods)) {
                                        LOGGER.debug(" Procedure executed Successfully");
                                        discountProjectionLogic.checkUncheckRebateBeforeAdjust(true, selectedDiscountList, session, false,
                                                isProgram);
                                        refreshTableData(getCheckedRecordsHierarchyNo());
                                    } else {
                                        discountProjectionLogic.checkUncheckRebateBeforeAdjust(true, selectedDiscountList, session, false,
                                                isProgram);
                                        LOGGER.error(" Error to update data for adjustmnet. Procedure not executed");
                                    }
                                } else {
                                    LOGGER.error(" Error to Update value for Adjust");
                                }

                            }
                        }.getOkCancelMessage(confirmMessage, messageBody);
                    } else {
                        NotificationUtils.getErrorNotification("No level selected",
                                "Please select a level this adjustment applies to");
                    }

                } else {
                    NotificationUtils.getErrorNotification("No adjustment entered",
                            "Please enter a numeric value in the Adjustment text box.");
                }
            } else {
                NotificationUtils.getErrorNotification("No Allocation Methodology selected",
                        "Please select an Allocation Methodology");
            }
        } catch (Property.ReadOnlyException e) {
        }
    }

    /**
     * To get the hierarchy numbers of checked records
     *
     * @return
     */
    private Set<String> getCheckedRecordsHierarchyNo() {
        Set<String> finalHirarechyNo = new HashSet<>();
        for (String tableTreeLevelNo : tableLogic.getAllLevels()) {
            Object itemId = tableLogic.getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                itemId = tableLogic.getExpandedTreeValues(tableTreeLevelNo);
            }
            if (itemId != null) {
                int uncheckCount = ((DiscountProjectionDTO) itemId).getUncheckCount();
                int ccpCount = ((DiscountProjectionDTO) itemId).getCcpCount();
                if (ccpCount != uncheckCount) {
                    finalHirarechyNo.add(tableTreeLevelNo);
                }
            }
        }
        finalHirarechyNo.addAll(getManualEntryRefreshHierarachyNo());
        return finalHirarechyNo;
    }

    private List<String[]> getCheckedRecordsForMassUpdate() {
        boolean isCustomHierarchy = CUSTOM_VIEW.equalsIgnoreCase(String.valueOf(view.getValue()));

        List<String[]> hierarchyList = new ArrayList<>();

        for (String tableTreeLevelNo : tableLogic.getAllLevels()) {
            Object itemId = tableLogic.getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                itemId = tableLogic.getExpandedTreeValues(tableTreeLevelNo);
            }
            if (itemId != null) {
                if ((boolean) ((DiscountProjectionDTO) itemId).getProperties().get("checkRecord")
                        && tableLogic.getAllChildLevels(tableTreeLevelNo).isEmpty()) {
                    if (!isCustomHierarchy) {
                        String[] dataForMassUpdate = new String[3];
                        dataForMassUpdate[0] = ((DiscountProjectionDTO) itemId).getHierarchyNo();
                        dataForMassUpdate[1] = ((DiscountProjectionDTO) itemId).getHierarchyIndicator();
                        dataForMassUpdate[2] = getPreviousHierachy(tableTreeLevelNo,
                                ((DiscountProjectionDTO) itemId).getHierarchyIndicator());
                        hierarchyList.add(dataForMassUpdate);
                    } else {
                        String[] dataForMassUpdate = new String[5];
                        dataForMassUpdate[0] = StringUtils.EMPTY;
                        dataForMassUpdate[1] = ((DiscountProjectionDTO) itemId).getHierarchyIndicator();
                        DiscountProjectionDTO dto = getPreviousHierachyCustom(tableTreeLevelNo,
                                ((DiscountProjectionDTO) itemId).getHierarchyIndicator());
                        dataForMassUpdate[2] = dto != null ? dto.getHierarchyNo() : StringUtils.EMPTY;
                        if (dataForMassUpdate[1].equals("D")) {
                            if (dto != null && dto.getHierarchyIndicator().equals("P")) {
                                dataForMassUpdate[0] = ((DiscountProjectionDTO) itemId).getCustomerHierarchyNo();
                            } else if (dto != null && dto.getHierarchyIndicator().equals("C")) {
                                dataForMassUpdate[0] = ((DiscountProjectionDTO) itemId).getProductHierarchyNo();
                            }
                        } else {
                            dataForMassUpdate[0] = ((DiscountProjectionDTO) itemId).getHierarchyNo();
                        }
                        dataForMassUpdate[3] = ((DiscountProjectionDTO) itemId).getLevelNo().toString();
                        dataForMassUpdate[4] = ((DiscountProjectionDTO) itemId).getHierarchyNo();
                        hierarchyList.add(dataForMassUpdate);
                    }
                }
            }
        }

        return hierarchyList;
    }

    @Override
    protected void excelExportClickLogic() {
        long startTime = System.currentTimeMillis();
        LOGGER.debug("excel starts");
        try {
            excelTable.setRefresh(BooleanConstant.getFalseFlag());
            excelContainer = new ExtTreeContainer<>(DiscountProjectionDTO.class, ExtContainer.DataStructureMode.MAP);
            excelContainer.setColumnProperties(excelHeaderLeft.getProperties());
            excelContainer.setColumnProperties(excelHeader.getProperties());
            excelTable.setContainerDataSource(excelContainer);
            ExtFilterTreeTable leftResultTable = resultsTable.getLeftFreezeAsTable();

            List<Object> leftTableVisibleColumnList = new ArrayList(Arrays.asList(leftResultTable.getVisibleColumns()));
            List<String> leftTableColumnHeaderList = new ArrayList(Arrays.asList(leftResultTable.getColumnHeaders()));
            leftTableVisibleColumnList.remove(LEVEL_NAME_PROPERTY);
            leftTableVisibleColumnList.add(DF_LEVEL_NUMBER);
            leftTableVisibleColumnList.add(DF_LEVEL_NAME);

            leftTableColumnHeaderList.add(1, LEVEL_NUMBER_HEADER);

            Object[] objectArrayHeaders = ArrayUtils.addAll(
                    leftTableColumnHeaderList.subList(1, leftTableColumnHeaderList.size()).toArray(),
                    excelHeader.getSingleHeaders().toArray(new String[0]));
            LOGGER.info("HEADERS-----------------= {}", Arrays.asList(objectArrayHeaders));

            Object[] leftTableExcelColumns = ArrayUtils.addAll(
                    leftTableVisibleColumnList.subList(1, leftTableVisibleColumnList.size()).toArray(),
                    excelHeader.getSingleColumns().toArray());

            LOGGER.info("COLUMNS-----------------= {}", Arrays.asList(leftTableExcelColumns));

            Object[] displayFormatIndex = CommonUtil.getDisplayFormatSelectedValues(displayFormatValues);
            String removeColumn = StringUtils.EMPTY;
            if (displayFormatIndex.length == 1) {
                for (int i = 0; i < displayFormatIndex.length; i++) {
                    LOGGER.info("obj--------------= {}", i);
                    int index = (Integer) displayFormatIndex[i];
                    if (index == 0) {
                        removeColumn = DF_LEVEL_NAME;
                        leftTableExcelColumns = ArrayUtils.removeElement(leftTableExcelColumns, DF_LEVEL_NAME);
                        objectArrayHeaders = ArrayUtils.removeElement(objectArrayHeaders, LEVEL_NAME_HEADER);
                    } else {
                        removeColumn = DF_LEVEL_NUMBER;
                        leftTableExcelColumns = ArrayUtils.removeElement(leftTableExcelColumns, DF_LEVEL_NUMBER);
                        objectArrayHeaders = ArrayUtils.removeElement(objectArrayHeaders, LEVEL_NUMBER_HEADER);

                    }

                }
            }
            securityForListView(leftTableExcelColumns, Arrays.copyOf(objectArrayHeaders, objectArrayHeaders.length, String[].class), excelTable);
            Object[] singleHeaderArray = getFinalLeftHeader(excelHeaderLeft.getDoubleHeaderMaps());

            List<Object> listHeadersList = new ArrayList(Arrays.asList(singleHeaderArray));
            listHeadersList.remove(LEVEL_NAME_PROPERTY);
            listHeadersList.remove("group");
            if (!removeColumn.isEmpty()) {
                listHeadersList.remove(removeColumn);
            }
            excelHeaderLeft.getDoubleHeaderMaps().put(GROUP_PROPERTY_ID, listHeadersList.toArray());
            excelTable.setDoubleHeaderVisible(true);
            excelTable
                    .setDoubleHeaderVisibleColumns(ArrayUtils.addAll(
                            Arrays.copyOfRange(leftHeader.getDoubleColumns().toArray(), 0,
                                    leftHeader.getDoubleColumns().toArray().length),
                            excelHeader.getDoubleColumns().toArray()));
            Object[] objectDoubleArray = ArrayUtils.addAll(
                    Arrays.copyOfRange(leftHeader.getDoubleHeaders().toArray(), 0,
                            leftHeader.getDoubleHeaders().toArray().length),
                    excelHeader.getDoubleHeaders().toArray(new String[excelHeader.getDoubleHeaders().size()]));
            excelTable.setDoubleHeaderColumnHeaders(
                    Arrays.copyOf(objectDoubleArray, objectDoubleArray.length, String[].class));
            Map<Object, Object[]> mapVisibleCols = new HashMap();
            mapVisibleCols.putAll(excelHeaderLeft.getDoubleHeaderMaps());
            mapVisibleCols.putAll(excelHeader.getDoubleHeaderMaps());
            excelTable.setDoubleHeaderMap(mapVisibleCols);
            boolean isRate = false;
            boolean isRPU = false;
            for (Object propertyId : excelTable.getContainerPropertyIds()) {
                if (String.valueOf(propertyId).endsWith("Rate")) {
                    isRate = true;
                    excelTable.setConverter(propertyId, percentFormat);
                } else if (String.valueOf(propertyId).endsWith("RPU")) {
                    isRPU = true;
                }
            }
            getExcelDiscountCommercial();
            Map<String, String> formatterMap = new HashMap<>();
            formatterMap.put("percentThreeDecimal", "Rate");
            formatterMap.put("currencyTwoDecimal", "RPU");
            formatterMap.put(AMOUNT_TWO_DECIMAL, AMOUNT);
            formatterMap.put("sales", "Sales");
            formatterMap.put("units", "Units");
            formatterMap.put("Growth", "Growth");
            excelTable.setRefresh(BooleanConstant.getTrueFlag());
            ForecastUI.setEXCEL_CLOSE(true);
            CustomExcelNM excel = null;
            HeaderUtils.getDiscountProjectionRightTableColumns(projectionSelection);
            if (QUARTERLY.getConstant().equals(String.valueOf(frequencyDdlb.getValue()))
                    || MONTHLY.getConstant().equals(String.valueOf(frequencyDdlb.getValue()))) {

                Map<Integer, List> headerMapBasedonYear = discountProjectionLogic
                        .configureVisibleColumnMapsForExcel(projectionSelection.getHeaderMapForExcel());
                int exportVal = headerMapBasedonYear.size() - 1;
                List<Integer> listHeader = new ArrayList(headerMapBasedonYear.keySet());
                Collections.sort(listHeader);
                for (int i = 0; i < listHeader.size(); i++) {

                    if (displayFormatIndex.length == 1) {
                        for (int k = 0; k < displayFormatIndex.length; k++) {
                            LOGGER.info("obj--------------= {}", k);
                            int index = (Integer) displayFormatIndex[k];
                            if (index == 0) {
                                leftTableVisibleColumnList.remove(DF_LEVEL_NAME);
                                leftTableColumnHeaderList.remove(LEVEL_NAME_HEADER);
                            } else {
                                leftTableVisibleColumnList.remove(DF_LEVEL_NUMBER);
                                leftTableColumnHeaderList.remove(LEVEL_NUMBER_HEADER);

                            }

                        }
                    }
                    excelTable.setVisibleColumns(ArrayUtils.addAll(
                            leftTableVisibleColumnList.subList(1, leftTableVisibleColumnList.size()).toArray(),
                            ((List<Object>) headerMapBasedonYear.get(listHeader.get(i)).get(0)).toArray()));
                    Object[] headerArray = ArrayUtils.addAll(
                            leftTableColumnHeaderList.subList(1, leftTableColumnHeaderList.size()).toArray(),
                            ((List<Object>) headerMapBasedonYear.get(listHeader.get(i)).get(1)).toArray());
                    excelTable.setColumnHeaders(Arrays.copyOf(headerArray, headerArray.length, String[].class));
                    excelTable.setDoubleHeaderVisibleColumns(ArrayUtils.addAll(
                            Arrays.copyOfRange(leftHeader.getDoubleColumns().toArray(), 0,
                                    leftHeader.getDoubleColumns().toArray().length),
                            ((List<Object>) headerMapBasedonYear.get(listHeader.get(i)).get(NumericConstants.THREE))
                                    .toArray()));
                    Object[] doubleArrayHeader = ArrayUtils.addAll(
                            Arrays.copyOfRange(leftHeader.getDoubleHeaders().toArray(), 0,
                                    leftHeader.getDoubleHeaders().toArray().length),
                            ((List<Object>) headerMapBasedonYear.get(listHeader.get(i)).get(NumericConstants.FOUR))
                                    .toArray());
                    excelTable.setDoubleHeaderColumnHeaders(
                            Arrays.copyOf(doubleArrayHeader, doubleArrayHeader.length, String[].class));
                    mapVisibleCols = new HashMap();
                    mapVisibleCols.putAll(excelHeaderLeft.getDoubleHeaderMaps());
                    mapVisibleCols.putAll(
                            (Map<Object, Object[]>) headerMapBasedonYear.get(listHeader.get(i)).get(NumericConstants.FIVE));
                    excelTable.setDoubleHeaderMap(mapVisibleCols);
                    excelTable.setRefresh(true);
                    String sheetName = "Year " + listHeader.get(i);
                    ForecastUI.setEXCEL_CLOSE(true);
                    if (i == 0) {
                            excel = new CustomExcelNM(new ExtCustomTableHolder(excelTable), sheetName,
                                Constant.DISCOUNT_PROJECTION_LABEL, DISCOUNT_PROJECTION_XLS, false, formatterMap,isRate,isRPU,projectionSelection.isIsCustomHierarchy());
                    } else {
                        excel.setNextTableHolder(new ExtCustomTableHolder(excelTable), sheetName);
                    }
                    if (i == exportVal) {
                        excel.exportMultipleTabs(true);
                    } else {
                        excel.exportMultipleTabs(false);
                    }
                }
            } else {
                excel = new CustomExcelNM(new ExtCustomTableHolder(excelTable), Constant.DISCOUNT_PROJECTION_LABEL,
                        Constant.DISCOUNT_PROJECTION_LABEL, DISCOUNT_PROJECTION_XLS, false, formatterMap,isRate,isRPU,projectionSelection.isIsCustomHierarchy());
                excel.export();
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
            LOGGER.info(e.getMessage(),e);
        }
        LOGGER.debug("excel ends");
        long endTime = System.currentTimeMillis();
        LOGGER.info("Excel Export time--------------------------------------------------------------"+(endTime-startTime)/1000);
    }

    public void generateButtonlogicForExcel() {
        try {
            LOGGER.debug("Inside generateButtonlogicForExcel");
            excelContainer.removeAllItems();
            int levelNumber = getStartLevelNo();
            String hierarchyNo = StringUtils.EMPTY;
            int treeLevelNo = 1;

            List customDetailsList = new ArrayList();
            customDetailsList.add(levelNumber);
            customDetailsList.add(hierarchyNo);
            customDetailsList.add(treeLevelNo);

            List<String> discountToBeLoaded;
            if (!programSelectionList.isEmpty()) {
                discountToBeLoaded = programSelectionList;
            } else {
                discountToBeLoaded = discountProgramsList;
            }

            if (CommonUtil.isValueEligibleForLoading()) {
                discountToBeLoaded = projectionSelection.getDeductionLevelFilter();
            }
            List<String> customViewDetails = new ArrayList<>();
            String temphierarchyIndicator = hierarchyIndicator;
            boolean isCustomHierarchy = Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator);
            if (isCustomHierarchy) {
                String customerLevelNo;
                String productLevelNo;
                String deductionLevelNo;
                Leveldto levelDto = CommonLogic.getNextLevel(treeLevelNo, currentHierarchy);
                temphierarchyIndicator = levelDto.getHierarchyIndicator();
                if (Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(temphierarchyIndicator)) {
                    customerLevelNo = Constant.PERCENT;
                    productLevelNo = Constant.PERCENT;
                    deductionLevelNo = String.valueOf(treeLevelNo);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(temphierarchyIndicator)) {
                    customerLevelNo = String.valueOf(treeLevelNo);
                    productLevelNo = Constant.PERCENT;
                    deductionLevelNo = Constant.PERCENT;
                } else {
                    customerLevelNo = Constant.PERCENT;
                    productLevelNo = String.valueOf(treeLevelNo);
                    deductionLevelNo = Constant.PERCENT;
                }

                LOGGER.debug(" EXCEL P- Custom hierarchy --- \n customId= {} ", customId);
                LOGGER.debug(" EXCEL P- customerLevelNo= {} ", customerLevelNo);
                LOGGER.debug(" EXCEL P- customerHierarchyNo");
                LOGGER.debug(" EXCEL P- productLevelNo= {} ", productLevelNo);
                LOGGER.debug(" EXCEL P- productHierarchyNo");
                customViewDetails.add(StringUtils.EMPTY + customId);
                customViewDetails.add(customerLevelNo);
                customViewDetails.add(StringUtils.EMPTY);
                customViewDetails.add(productLevelNo);
                customViewDetails.add(StringUtils.EMPTY);
                customViewDetails.add(session.getCustRelationshipBuilderSid());
                customViewDetails.add(session.getProdRelationshipBuilderSid());
                customViewDetails.add(session.getDedRelationshipBuilderSid());
                customViewDetails.add(deductionLevelNo);
                customViewDetails.add(StringUtils.EMPTY);
            }
            String relationshipBuilderSid = getRelationshipBuilderSid();
            List list = discountProjectionLogic.getDiscountProjection(session, projectionSelection.getFrequency(), startAndEndPeriods,
                    projectionSelection.getHistory(), temphierarchyIndicator, projectionSelection.getProjectionOrder(),
                    userGroup, PROGRAM.getConstant().equals(level.getValue()), discountToBeLoaded,
                    projectionSelection.getYear(), customDetailsList, true, isCustomHierarchy, rightHeader, 0,
                    NumericConstants.THOUSAND, BooleanConstant.getFalseFlag(), BooleanConstant.getFalseFlag(), 
                    customViewDetails, BooleanConstant.getFalseFlag(), BooleanConstant.getFalseFlag(), StringUtils.EMPTY,
                    relationshipBuilderSid, false, Collections.EMPTY_LIST, false, StringUtils.EMPTY, StringUtils.EMPTY,
                    Collections.EMPTY_LIST, Collections.EMPTY_MAP, projectionSelection.getForecastConfigPeriods(),
                    projectionSelection);
            loadDataToContainer(list, null, true);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("Exit generateButtonlogicForExcel");
    }

    public void loadDataToContainer(List<DiscountProjectionDTO> resultList, Object parentId, boolean isRecursive) {
        try {
            LOGGER.debug("Inside loadDataToContainer");
            for (DiscountProjectionDTO dto : resultList) {
                excelContainer.addBean(dto);
                if (parentId != null) {
                    excelContainer.setParent(dto, parentId);
                }

                excelContainer.setChildrenAllowed(dto, true);
                if (isRecursive) {
                    addLowerLevelsForExport(dto);
                }

            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

        }
        LOGGER.debug("Ended loadDataToContainer");
    }

    public void addLowerLevelsForExport(DiscountProjectionDTO dto) {
        LOGGER.debug("Inside addLowerLevelsForExport");
        try {

            int treeLevelNo = 0;
            String temphierarchyIndicator = hierarchyIndicator;
            boolean isCustomHierarchy = CommonUtil.isValueEligibleForLoading()
                    ? Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator)
                    : Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.equals(hierarchyIndicator);
            if (isCustomHierarchy) {
                treeLevelNo = dto.getTreeLevelNo() + 1;
            } else {
                treeLevelNo = dto.getLevelNo() + 1;
            }

            LOGGER.debug(" dto.getLevelNo()= {} ", dto.getLevelNo());
            List customDetailsList = new ArrayList();

            List<String> discountToBeLoaded;
            if (!programSelectionList.isEmpty()) {
                discountToBeLoaded = programSelectionList;
            } else {
                discountToBeLoaded = discountProgramsList;
            }
            if (CommonUtil.isValueEligibleForLoading()) {
                discountToBeLoaded = projectionSelection.getDeductionLevelFilter();
            }

            List<String> customViewDetails = new ArrayList<>();
            if (isCustomHierarchy) {
                String customerLevelNo;
                String productLevelNo;
                String deductionLevelNo;
                String customerHierarchyNo;
                String productHierachyNo;
                String deductionHierachyNo;
                Leveldto levelDto = CommonLogic.getNextLevel(treeLevelNo, currentHierarchy);
                temphierarchyIndicator = levelDto.getHierarchyIndicator();

                if (Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                    customerHierarchyNo = dto.getCustomerHierarchyNo();
                    productHierachyNo = dto.getProductHierarchyNo();
                    deductionHierachyNo = dto.getHierarchyNo();
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                    customerHierarchyNo = dto.getHierarchyNo();
                    productHierachyNo = dto.getProductHierarchyNo();
                    deductionHierachyNo = dto.getDeductionHierarchyNo();
                } else {
                    customerHierarchyNo = dto.getCustomerHierarchyNo();
                    productHierachyNo = dto.getHierarchyNo();
                    deductionHierachyNo = dto.getDeductionHierarchyNo();
                }

                if (Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(temphierarchyIndicator)) {
                    customerLevelNo = Constant.PERCENT;
                    productLevelNo = Constant.PERCENT;
                    deductionLevelNo = String.valueOf(dto.getTreeLevelNo() + 1);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(temphierarchyIndicator)) {
                    customerLevelNo = String.valueOf(dto.getTreeLevelNo() + 1);
                    productLevelNo = Constant.PERCENT;
                    deductionLevelNo = Constant.PERCENT;
                } else {
                    customerLevelNo = Constant.PERCENT;
                    productLevelNo = String.valueOf(dto.getTreeLevelNo() + 1);
                    deductionLevelNo = Constant.PERCENT;
                }

                LOGGER.debug(" EXCEL - Custom hierarchy --- \n customId= {} ", customId);
                LOGGER.debug(" EXCEL - customerLevelNo= {} ", customerLevelNo);
                LOGGER.debug(" EXCEL - customerHierarchyNo= {} ", customerHierarchyNo);
                LOGGER.debug(" EXCEL - productLevelNo= {} ", productLevelNo);
                LOGGER.debug(" EXCEL - productHierarchyNo= {} ", productHierachyNo);
                customViewDetails.add(StringUtils.EMPTY + customId);
                customViewDetails.add(customerLevelNo);
                customViewDetails.add(customerHierarchyNo);
                customViewDetails.add(productLevelNo);
                customViewDetails.add(productHierachyNo);
                customViewDetails.add(session.getCustRelationshipBuilderSid());
                customViewDetails.add(session.getProdRelationshipBuilderSid());
                customViewDetails.add(session.getDedRelationshipBuilderSid());
                customViewDetails.add(deductionLevelNo);
                customViewDetails.add(deductionHierachyNo);

                customDetailsList.add(dto.getLevelNo() + 1);
                customDetailsList.add(StringUtils.EMPTY);
                customDetailsList.add(treeLevelNo);
            } else {
                customDetailsList.add(dto.getLevelNo() + 1);
                customDetailsList.add(dto.getHierarchyNo());
                customDetailsList.add(treeLevelNo);
            }
            String relationshipBuilderSid = getRelationshipBuilderSid();
            List levelList = discountProjectionLogic.getDiscountProjection(session, projectionSelection.getFrequency(),
                    startAndEndPeriods, projectionSelection.getHistory(), temphierarchyIndicator,
                    projectionSelection.getProjectionOrder(), userGroup, PROGRAM.getConstant().equals(level.getValue()),
                    discountToBeLoaded, projectionSelection.getYear(), customDetailsList, true, isCustomHierarchy,
                    rightHeader, 0, NumericConstants.THOUSAND, BooleanConstant.getFalseFlag(), BooleanConstant.getFalseFlag(), 
                    customViewDetails, BooleanConstant.getFalseFlag(), BooleanConstant.getFalseFlag(),
                    StringUtils.EMPTY, relationshipBuilderSid, false, Collections.EMPTY_LIST, false, StringUtils.EMPTY,
                    StringUtils.EMPTY, Collections.EMPTY_LIST, Collections.EMPTY_MAP,
                    projectionSelection.getForecastConfigPeriods(), projectionSelection);
            loadDataToContainer(levelList, dto, true);
            excelTable.setCollapsed(dto, false);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("Exit addLowerLevelsForExport");
    }

    @UiHandler("programSelectionLookup")
    public void programSelectionLookupClickLogic(Button.ClickEvent event) {

        LOGGER.debug("Enters the programSelectionLookupClickLogic");

        final DiscountSelection discountSelectionLookupWindow = new DiscountSelection(session, discountProgramsList,
                PROGRAM.getConstant().equals(level.getValue()));
        UI.getCurrent().addWindow(discountSelectionLookupWindow);
        discountSelectionLookupWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                List<String> selectedDiscountList = discountSelectionLookupWindow.getSelectedDiscountsList();
                programBean.removeAllItems();
                programBean.addItem(SELECT_ONE.getConstant());
                programSelection.setNullSelectionAllowed(true);
                programSelection.setNullSelectionItemId(SELECT_ONE.getConstant());
                programSelection.setValue(SELECT_ONE.getConstant());
                customizeDataForDDLB(selectedDiscountList, programSelection);
                discountProgramsList = selectedDiscountList;
                discountNamesList = new ArrayList<>(selectedDiscountList);
                List<String> selectedDiscountNoList = discountSelectionLookupWindow.getSelectedDiscountsNoList();
                discountNoList = new ArrayList<>(selectedDiscountNoList);
            }
        });
    }

    public void customizeDataForDDLB(final List<String> list, final ComboBox comboBox) {
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                String rsValue = str.contains("~") ? str.split("~")[0] : str;
                comboBox.addItem(str);
                comboBox.setItemCaption(str, rsValue);
                comboBox.select(SELECT_ONE.getConstant());
            }
        }
    }

    @Override
    protected void generateBtnClickLogic(Boolean isGenerate) {
        CommonUtil.getInstance().waitsForOtherThreadsToComplete(session.getFutureValue(Constant.CUSTOMER_VIEW_DISCOUNT_POPULATION_CALL));
        CommonLogic.procedureCompletionCheck(session,DISCOUNT,String.valueOf(view.getValue()));
        isRateUpdatedManually = false;
        isRPUUpdatedManually = false;
        isAmountUpdatedManually = false;
        saveList.clear();
        if (((Collection) variables.getValue()).isEmpty()) {
            AbstractNotificationUtils.getErrorNotification("No variables were selected",
                    "No variables were selected. Please select at least one variable and try again.");
        } else {
            projectionSelection.setCustomerLevelFilter((List) (generateCustomerToBeLoaded != null ? generateCustomerToBeLoaded : new ArrayList<>()));
            projectionSelection.setProductLevelFilter((List) (generateProductToBeLoaded != null ? generateProductToBeLoaded : new ArrayList<>()));
            projectionSelection.setDeductionLevelFilter((List) (generateDiscountToBeLoaded != null ? generateDiscountToBeLoaded : new ArrayList<>()));
            projectionSelection.setDeductionLevelCaptions((List) (generateDiscountNamesToBeLoaded != null ? generateDiscountNamesToBeLoaded : new ArrayList<>()));
            generateListView(true);
        }
    }

    /**
     * To generate the list view
     *
     * @param isGenerate
     */
    public void generateListView(boolean isGenerate) {
        LOGGER.debug("Inside generateListView ");
        List<String> discountToBeLoaded = new ArrayList<>();
        Object[] displayValidation = CommonUtil.getDisplayFormatSelectedValues(displayFormatValues);

        if (CommonUtil.isValueEligibleForLoading()) {
            discountToBeLoaded = projectionSelection.getDeductionLevelFilter();
        }

        if (discountToBeLoaded.isEmpty() || "AllDiscount".equals(discountToBeLoaded.get(0))) {
            NotificationUtils.getErrorNotification("No Deduction values Selected",
                    "No Deduction Level or Deduction Filter values were selected.Please select at least one Deduction variable on the Filter Options tab, and try again.");
        } else if (frequencyDdlb.getValue() == null || frequencyDdlb.getValue().equals(SELECT_ONE.getConstant())) {
            NotificationUtils.getErrorNotification("No Frequency selected", "Please select any frequency");
        } else if (historyDdlb.getValue() == null || historyDdlb.getValue().equals(SELECT_ONE.getConstant())) {
            NotificationUtils.getErrorNotification("No History selected", "Please select any history");
        } else if (displayValidation.length == 0 && !CommonUtil.nullCheck(displayValidation)) {
            NotificationUtils.getErrorNotification("No Display Format Selected", "Please select value(s) from the Display Format field");
        } else {
            if (CommonUtil.isValueEligibleForLoading()) {
                CommonLogic.updateForFilter(projectionSelection, DEDUCTION, false);
                discountProjectionLogic.updateAllToZero(session);
            }
            hierarchyListForCheckRecord.clear();
            session.setFrequency(projectionSelection.getFrequency());
            callAdjustmentProcedure(this.session);
            tableLogic.clearAll();
            tableLogic.setRefresh(false);// will become true once setcurrentpage
            // completed
            checkBoxMap.clear();
            radioMap.clear();
            tableLogic.sinkItemPerPageWithPageLength(false);
            boolean isFrequencyChange = !String.valueOf(projectionSelection.getFrequency())
                    .equals(String.valueOf(frequencyDdlb.getValue()));
            createSelectionDto();
            createRightHeader();
            resultBeanContainer.setColumnProperties(rightHeader.getProperties());
            if (isGenerate) {
                resultsTable.constructRightFreeze(isGenerate);
            }
            resultsTable.getRightFreezeAsTable().setContainerDataSource(tableLogic.getContainerDataSource());
            configureRightTable();
            configureLeftTable();
            if (Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator)
                    && CommonUtil.isValueEligibleForLoading()) {
                resultsTable.getLeftFreezeAsTable().setDoubleHeaderVisible(false);
                resultsTable.setTripleHeaderVisible(false);
            } else {
                resultsTable.getLeftFreezeAsTable().setDoubleHeaderVisible(true);
                resultsTable.setTripleHeaderVisible(true);
            }
            List<String> checkedValues = getCheckedDeductionInclusionValues();
            session.setDeductionInclusion("ALL");
            if (checkedValues.size() == 1) {
                session.setDeductionInclusion(checkedValues.get(0).equalsIgnoreCase("Yes") ? STRING_ONE : "0");
            }
            session.setDiscountUom(uomDdlb.getValue() != null ? String.valueOf(uomDdlb.getValue()) : "EACH");
            session.setSelectedDeductionLevelNo(Integer.parseInt(String.valueOf(deductionlevelDdlb.getValue())));
            projectionSelection.setDisplayFormat(CommonUtil.getDisplayFormatSelectedValues(displayFormatValues));
            projectionSelection.setConversionFactor(conversionFactorDdlb.getValue());
            formatTableData();
            loadScreenBasedOnGeneratedTable(isFrequencyChange);
            loadDataInTable();// setcurrentpage will be called
            setListviewGenerated(true);
            loadLevelValues();
            isDiscountGenerated = true;
            adjProgramsValueChangeLogic(SELECT.getConstant());
            adjPeriodValueChangeLogic(SELECT.getConstant());
            adjperiods.select(SELECT);
            adjprograms.select(SELECT);
        }
        LOGGER.debug("Ending generateListView ");

    }

    private void formatTableData() {
        LOGGER.debug("Start formatTableData");
        for (Object propertyId : resultsTable.getRightFreezeAsTable().getVisibleColumns()) {
            if (String.valueOf(propertyId).contains("ActualAmount")
                    || String.valueOf(propertyId).contains("ProjectedAmount")) {
                resultsTable.getRightFreezeAsTable().setConverter(propertyId, getConverter(propertyId.toString()));

            } else if (String.valueOf(propertyId).contains(Constant.ACTUALRPU)) {
                resultsTable.getRightFreezeAsTable().setConverter(propertyId, priceFormat);
            } else {
                resultsTable.getRightFreezeAsTable().setConverter(propertyId, percentFormat);
            }
        }
        LOGGER.debug("End formatTableData");
    }

    private void loadDataInTable() {
        LOGGER.debug("Entering loadDataInTable");
        tableLogic.clearAll();
        List<String> discountToBeLoaded;
        boolean isCustom = CommonUtil.isValueEligibleForLoading()
                ? Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator)
                : Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.equals(hierarchyIndicator);
        if (!programSelectionList.isEmpty()) {
            discountToBeLoaded = programSelectionList;
        } else {
            discountToBeLoaded = discountProgramsList;
        }

        if (CommonUtil.isValueEligibleForLoading()) {
            discountToBeLoaded = projectionSelection.getDeductionLevelFilter();
        }

        int levelNo = getStartLevelNo();
        String relationshipBuilderSid = getRelationshipBuilderSid();
        tableLogic.setDataLoad(true);
        tableLogic.setDiscountVariablesForLogic(session, projectionSelection, startAndEndPeriods,
                PROGRAM.getConstant().equals(level.getValue()), discountToBeLoaded, levelNo, true, rightHeader,
                hierarchyIndicator, userGroup, currentHierarchy, isCustom, customId, relationshipBuilderSid);

        LOGGER.debug(" User Id --= {}", session.getUserId());
        LOGGER.debug(" Session Id --= {}", session.getSessionId());
        LOGGER.debug("Ending loadDataInTable");
    }

    /**
     * To load data in tree table
     *
     * @param isFrequencyChange - To reload start and end period during
     * frequency changes
     */
    public void loadScreenBasedOnGeneratedTable(boolean isFrequencyChange) {
        LOGGER.debug("Inside loadTreeTable ");
        try {
            tripleHeaderForCheckedDoubleHeader.clear();
            checkedDiscountsPropertyIds.clear();
            String fieldValue = String.valueOf(fieldDdlb.getValue());
            List<String> projectedHeaders = rightHeader.getDoubleHeaders();
            if (!(Constant.NULL).equals(fieldValue)) {
                if (projectionSelection.getProjectionOrder().equals(DESCENDING.getConstant())) {
                    Collections.reverse(projectedHeaders);
                }
                loadDiscPeriods(startPeriodBean, startPeriod, rightHeader.getDoubleProjectedHeaders());
                loadDiscPeriods(endPeriodBean, endPeriod, rightHeader.getDoubleProjectedHeaders());

            } else {
                startPeriodBean.removeAllItems();
                endPeriodBean.removeAllItems();
                startPeriod.addItem(SELECT_ONE.getConstant());
                startPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
                endPeriod.addItem(SELECT_ONE.getConstant());
                endPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
            }
            loadDiscPeriods(forecaststartBean, startPeriodForecastTab, rightHeader.getDoubleProjectedHeaders());
            loadDiscPeriods(forecastendBean, endPeriodForecastTab, rightHeader.getDoubleProjectedHeaders());
            if (isFrequencyChange) {
                startPeriod.select(SELECT_ONE.getConstant());
                endPeriod.select(SELECT_ONE.getConstant());
                startPeriodForecastTab.select(SELECT_ONE.getConstant());
                endPeriodForecastTab.select(SELECT_ONE.getConstant());
            }
            if (projectionSelection.getProjectionOrder().equals(DESCENDING.getConstant())
                    && (PRODUCT.getConstant().equalsIgnoreCase(String.valueOf(view.getValue())))) {
                Collections.reverse(rightHeader.getDoubleHeaders());
            }
            String startFreq = String.valueOf(rightHeader.getDoubleHeaders().get(0));
            String endFreq = String
                    .valueOf(rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1));

            String startYearValue = startFreq.substring(startFreq.length() - NumericConstants.FOUR);
            String endYearValue = endFreq.substring(endFreq.length() - NumericConstants.FOUR);

            int startYear = isInteger(startYearValue) ? Integer.parseInt(startYearValue) : 0;
            int endYear = isInteger(endYearValue) ? Integer.parseInt(endYearValue) : 0;

            startAndEndPeriods.clear();
            if (!projectionSelection.getFrequency().equals(MONTHLY.getConstant())) {
                String startFreqNoValue = startFreq.substring(1, NumericConstants.TWO);
                String endFreqNoValue = endFreq.substring(1, NumericConstants.TWO);
                int startFreqNo = isInteger(startFreqNoValue) ? Integer.parseInt(startFreqNoValue) : 0;
                int endFreqNo = isInteger(endFreqNoValue) ? Integer.parseInt(endFreqNoValue) : 0;
                if (projectionSelection.getProjectionOrder().equals(ASCENDING.getConstant())) {
                    startAndEndPeriods.add(startFreqNo);
                    startAndEndPeriods.add(endFreqNo);
                } else {
                    startAndEndPeriods.add(endFreqNo);
                    startAndEndPeriods.add(startFreqNo);
                }
            } else {
                String startMonthValue = startFreq.substring(0, startFreq.length() - NumericConstants.FIVE);
                String endMonthValue = endFreq.substring(0, startFreq.length() - NumericConstants.FIVE);
                LOGGER.debug(" startMonthValue= {} ", startMonthValue);
                LOGGER.debug(" endMonthValue= {} ", endMonthValue);
                int startFreqNo = CommonUtils.getIntegerForMonth(startMonthValue);
                int endFreqNo = CommonUtils.getIntegerForMonth(endMonthValue);
                if (projectionSelection.getProjectionOrder().equals(ASCENDING.getConstant())) {
                    startAndEndPeriods.add(startFreqNo);
                    startAndEndPeriods.add(endFreqNo);
                } else {
                    startAndEndPeriods.add(endFreqNo);
                    startAndEndPeriods.add(startFreqNo);
                }
            }
            if (projectionSelection.getProjectionOrder().equals(ASCENDING.getConstant())) {
                startAndEndPeriods.add(startYear);
                startAndEndPeriods.add(endYear);
            } else {
                startAndEndPeriods.add(endYear);
                startAndEndPeriods.add(startYear);
            }

            adjProgramsValueChangeLogic(String.valueOf(adjprograms.getValue()));
            adjProgramsValueChangeLogic(String.valueOf(adjperiods.getValue()));
            loadGroupDdlb();
            loadGroupFilterDdlb();

        } catch (NumberFormatException | UnsupportedOperationException e) {
            LOGGER.debug(e.getMessage());
        }
        LOGGER.debug("Exiting loadTreeTable ");
    }

    /**
     * To load group Filter Ddlb
     */
    private void loadGroupFilterDdlb() {
        LOGGER.debug(" Load group Filter Ddlb ");
        groupFilterDdlb.removeAllItems();
        groupFilterDdlb.addItem(SELECT_ONE.getConstant());
        groupFilterDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        groupFilterDdlb.addItems(valueDdlbBean.getItemIds());

        tableGroupDdlbBean.removeAllItems();
        tableGroupDdlbBean.addBean(Constant.SHOW_ALL_GROUPS);
        tableGroupDdlbBean.addAll(valueDdlbBean.getItemIds());

        LOGGER.debug(" End group Filter Ddlb ");
    }

    private void configureRightTable() {
        final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setTripleHeaderVisible(true);
        rightTable.setEditable(true);
        rightTable.setHeight(Constant.SIX_FIFTY_PX);
        List<Object> visibleColumn = rightHeader.getSingleColumns();
        List<String> columnHeader = rightHeader.getSingleHeaders();
        List<Object> doubleVisibleColumn = rightHeader.getDoubleColumns();
        List<String> doubleColumnHeader = rightHeader.getDoubleHeaders();
        List<Object> tripleVisibleColumn = rightHeader.getTripleColumns();
        List<String> tripleColumnHeader = rightHeader.getTripleHeader();

        rightTable.setVisibleColumns(visibleColumn.toArray());
        rightTable.setColumnHeaders(columnHeader.toArray(new String[rightHeader.getSingleHeaders().size()]));
        rightTable.setDoubleHeaderVisibleColumns(doubleVisibleColumn.toArray());
        rightTable.setDoubleHeaderColumnHeaders(
                doubleColumnHeader.toArray(new String[rightHeader.getDoubleHeaders().size()]));
        rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());

        rightTable.setTripleHeaderVisibleColumns(tripleVisibleColumn.toArray());
        rightTable.setTripleHeaderColumnHeaders(
                tripleColumnHeader.toArray(new String[rightHeader.getTripleHeader().size()]));
        rightTable.setTripleHeaderMap(rightHeader.getTripleHeaderMaps());

        for (Object columns : rightHeader.getDoubleColumns()) {
            rightTable.setDoubleHeaderColumnCheckBox(columns, true);
            rightTable.setDoubleHeaderColumnCheckBoxDisable(columns,
                    ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
        }
        for (Object columns : rightHeader.getTripleColumns()) {
            rightTable.setTripleHeaderColumnCheckBox(columns, true);
            rightTable.setTripleHeaderColumnCheckBoxDisable(columns,
                    ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
        }
        for (Object columns : rightHeader.getSingleColumns()) {
            rightTable.setColumnAlignment(columns, ExtCustomTable.Align.RIGHT);
        }

        rightTable.addTripleHeaderColumnCheckListener(new ExtCustomTable.TripleHeaderColumnCheckListener() {
            @Override
            public void tripleHeaderColumnCheck(ExtCustomTable.TripleHeaderColumnCheckEvent event) {
                tripleHeaderCheckListener(event.getPropertyId(), event.isChecked());
            }
        });

        rightTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                if (event.isChecked()) {
                    checkBoxMap.put(event.getPropertyId(), BooleanConstant.getTrueFlag());
                } else {
                    checkBoxMap.remove(event.getPropertyId());
                }
                doubleHeaderCheckListener(event.getPropertyId(), event.isChecked());
                String[] arr = rightTable.getColumnRadioButtonArray((String) event.getPropertyId());
                if (arr != null) {
                    for (String a : arr) {
                        rightTable.setColumnRadioButtonDisable(a, !event.isChecked());
                    }
                }
                if (event.isChecked()) {
                    if (rightTable.getColumnRadioButtonValue((String) event.getPropertyId()) != null) {
                        radioMap.put(event.getPropertyId(),
                                rightTable.getColumnRadioButtonValue((String) event.getPropertyId()));
                    }
                } else if (radioMap.containsKey(event.getPropertyId())) {
                    radioMap.remove(event.getPropertyId());
                }
            }
        });
        rightTable.addColumnRadioCheckListener(new ExtCustomTable.ColumnRadioCheckListener() {
            @Override
            public void columnRadioCheck(ExtCustomTable.ColumnRadioCheckEvent event) {
                radioMap.put(event.getRadioButtonName(), event.getCurrentValue());
            }
        });
        for (Object obj : rightHeader.getDoubleColumns()) {
            for (Object ob : rightHeader.getDoubleHeaderMaps().get(obj)) {
                rightTable.setColumnRadioButton(ob, (String) obj);
                rightTable.setColumnRadioButtonDisable(ob, false);
            }
        }
        rightTable.setTableFieldFactory(new DefaultFieldFactory() {
            @Override
            public Field<?> createField(final Container container, final Object itemId, final Object propertyId,
                    Component uiContext) {
                if (!ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
                    try {
                        String property = String.valueOf(propertyId);
                        DiscountProjectionDTO deductiondto = (DiscountProjectionDTO) itemId;
                        if (deductiondto.getDeductionInclusion().equals("null")) {
                            return null;
                        }
                        String fieldvalue = String
                                .valueOf(container.getContainerProperty(itemId, propertyId).getValue());
                        if ((rightHeader.getSingleColumns().contains(property)) && (Constant.NULL.equals(fieldvalue))) {
                            DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                            dto.addStringProperties(propertyId, Constant.DASH);
                            container.getContainerProperty(itemId, propertyId).setValue(Constant.DASH);
                        }

                        if (rightHeader.getSingleProjectedColumns().contains(property)) {

                            TextField textField = new TextField();
                            textField.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
                            textField.addStyleName(Constant.TXT_RIGHT_ALIGN);
                            textField.setData(new Object[]{itemId, propertyId, "right"});
                            if (property.contains(Constant.PROJECTED_RATE) || property.contains(Constant.GROWTH)) {
                                textField.setConverter(percentFormat);

                            } else {
                                textField.setConverter(getConverter(property));

                            }
                            textField.addFocusListener(focusListener);

                            textField.addBlurListener(blurListener);
                            return textField;
                        }
                        if (rightHeader.getSingleForecastColumns().contains(property)) {
                            rightTable.setColumnRadioButton(property, (String) property);
                            rightTable.setColumnRadioButtonDisable(property, true);
                            for (int i = 0; i < rightHeader.getDoubleColumns().size(); i++) {
                                if (property.contains((CharSequence) rightHeader.getDoubleColumns().get(i))) {
                                    rightTable.setDoubleHeaderColumnCheckBoxDisable(rightHeader.getDoubleColumns().get(i), true);
                                }
                            }
                        }
                    } catch (Property.ReadOnlyException e) {
                        LOGGER.error(e.getMessage());

                    }
                }
                return null;
            }
        });
        for (Object obj : rightHeader.getDoubleHistoryColumns()) {
            Object[] single = rightHeader.getDoubleHeaderMaps().get(obj);
            for (Object ob : single) {
                rightTable.setColumnRadioButtonValue((String) obj, single[0]);
                rightTable.setColumnRadioButtonDisable(ob, true);
            }
        }
        for (Object obj : rightHeader.getDoubleProjectedColumns()) {
            Object[] single = rightHeader.getDoubleHeaderMaps().get(obj);
            for (Object ob : single) {
                rightTable.setColumnRadioButtonValue((String) obj, single[0]);
                rightTable.setColumnRadioButtonDisable(ob, true);
            }
        }
    }

    /**
     * To save Discount Projection Data
     */
    public void saveDiscountProjectionListview() {
        LOGGER.debug(" Inside Save ");
        if (isListviewGenerated()) {
            LOGGER.debug(" Discount generated ");
            boolean isCustomHierarchy = CommonUtil.isValueEligibleForLoading()
                    ? Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator)
                    : Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.equals(hierarchyIndicator);
            boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
            discountProjectionLogic.saveDiscountProjectionListView(session, projectionSelection.getFrequency(), saveList, customId,
                    isProgram, isCustomHierarchy);
            saveList.clear();
        }
        LOGGER.debug(" Exiting  Save ");
    }

    /**
     * To save the Discount projection selections
     */
    public void saveSelections() {
        LOGGER.debug(" Entering Save Selection ");
        Map map = new HashMap();
        Collection c = (Collection) variables.getValue();
        List<String> l = new ArrayList();
        if (c != null) {
            for (Object s : c) {
                l.add(String.valueOf(s));
            }
        }
        projectionSelection.setdPVariablesList(l);
        try {
            map.put(Constant.FREQUENCY, projectionSelection.getFrequency().isEmpty() ? Constant.QUARTERLY : projectionSelection.getFrequency());
            map.put(Constant.HISTORY, projectionSelection.getHistory());
            map.put(Constant.PROJECTION_PERIOD_ORDER_LABEL, projectionSelection.getProjectionOrder());
            map.put(Constant.ACTUALS_PROJECTIONS, projectionSelection.getActualsOrProjections());
            map.put(Constant.LEVEL_LABEL, String.valueOf(level.getValue()));
            if (CommonUtil.isValueEligibleForLoading()) {
                map.put(Constant.SELECTED_DISCOUNTS, projectionSelection.getDeductionLevelCaptions());
                map.put("SelectedDiscountsSids", projectionSelection.getDeductionLevelFilter());
            } else {
                map.put(Constant.SELECTED_DISCOUNTS, getDiscountRSSids(discountProgramsList).get(0));
                map.put("SelectedDiscountsSids", getDiscountRSSids(discountProgramsList).get(1));
            }
            map.put("selectedDiscountNo", CommonUtils.CollectionToString(discountNoList, false));
            map.put("Program Selection Ddlb", String.valueOf(programSelection.getValue()));
            map.put(Constant.YEAR_SELECTION_DDLB, projectionSelection.getYear());
            map.put(Constant.VARIABLES, variables.getValue());
            map.put(Constant.DISPLAY_FORMAT_SAVE, StringUtils.join(CommonUtil.getDisplayFormatSelectedValues(displayFormatValues), CommonUtil.COMMA));
            map.put(Constant.CUSTOMER_LEVEL_DDLB, customerlevelDdlb.getValue());
            map.put(Constant.CUSTOMER_LEVEL_VALUE, StringUtils.join(commonLogic.getFilterValues(customerFilterValues).get(SID), CommonUtil.COMMA));
            map.put(Constant.PRODUCT_LEVEL_DDLB, productlevelDdlb.getValue());
            map.put(Constant.PRODUCT_LEVEL_VALUE, StringUtils.join(commonLogic.getFilterValues(productFilterValues).get(SID), CommonUtil.COMMA));
            map.put(Constant.DEDUCTION_LEVEL_DDLB, deductionlevelDdlb.getValue());
            map.put(Constant.DEDUCTION_LEVEL_VALUE, StringUtils.join(commonLogic.getFilterValues(deductionFilterValues).get(SID), CommonUtil.COMMA));
            map.put(Constant.DEDUCTION_INCLUSION_DDLB, StringUtils.join(CommonUtil.getDisplayFormatSelectedValues(deductionInclusionValues), CommonUtil.COMMA));
            CommonLogic.saveProjectionSelection(map, session.getProjectionId(), TAB_DISCOUNT_PROJECTION.getConstant());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.debug(" Ending Save Selection ");
    }

    public List getDiscountRSSids(List<String> discountProgramsList) {
        List<String> rawListRSSids = new ArrayList();
        String discountPgmName = StringUtils.EMPTY;
        String discountPgmSids = StringUtils.EMPTY;
        for (String rsSids : discountProgramsList) {
            discountPgmName += (rsSids.contains("~") ? rsSids.split("~")[0] : rsSids) + ',';
            discountPgmSids += (rsSids.contains("~") ? StringUtils.EMPTY + rsSids.split("~")[1] : rsSids) + ',';
        }
        discountPgmName = discountPgmName.substring(0, discountPgmName.length() - 1);
        discountPgmSids = discountPgmSids.substring(0, discountPgmSids.length() - 1);
        rawListRSSids.add(discountPgmName);
        rawListRSSids.add(discountPgmSids);
        return rawListRSSids;
    }

    public List getDiscountRSSids1(List<String> discountProgramsList) {
        List<String> rawListRSSids = new ArrayList();
        String discountPgmName = StringUtils.EMPTY;
        String discountPgmSids = StringUtils.EMPTY;
        for (String rsSids : discountProgramsList) {
            discountPgmName += (rsSids.contains("~") ? rsSids.split("~")[0] : rsSids) + ',';
            discountPgmSids += (rsSids.contains("~") ? StringUtils.EMPTY + rsSids.split("~")[1] : rsSids) + ',';
        }
        discountPgmName = discountPgmName.substring(0, discountPgmName.length() - 1);
        discountPgmSids = discountPgmSids.substring(0, discountPgmSids.length() - 1);
        rawListRSSids.add(discountPgmSids);
        rawListRSSids.add(discountPgmName);
        return rawListRSSids;
    }

    /**
     * To save the discount projection screen including the Selections and list
     * view
     *
     * @param toBeRefreshed
     */
    public void saveDiscountProjectionScreen(boolean toBeRefreshed) {
        LOGGER.debug(" saving DP screen");
        try {
            if (isListviewGenerated()) {
                saveDiscountProjectionListview();
                if (toBeRefreshed) {
                    refreshTableData(getManualEntryRefreshHierarachyNo());
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug(" saving DP screen over");
    }

    private void tripleHeaderCheckListener(Object propertyId, boolean isChecked) {
        ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
        String checkedColumn = rightTable.getTripleHeaderColumnHeader(propertyId);
        String adjustmentPeriod = String.valueOf(adjperiods.getValue());
        if (isChecked) {
            checkedDiscountsPropertyIds.add(propertyId);
        } else {
            checkedDiscountsPropertyIds.remove(propertyId);
        }
        if (adjustmentPeriod.equals(ALL.getConstant())) {
            if (isChecked) {
                tripleHeaderForCheckedDoubleHeader.remove(checkedColumn);
            }
            for (Object columns : rightHeader.getTripleHeaderMaps().get(propertyId)) {
                rightTable.setDoubleHeaderColumnCheckBox(columns, true, isChecked);
                rightTable.setDoubleHeaderColumnCheckBoxDisable(columns,
                        ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
                doubleHeaderCheckListener(columns, isChecked);
            }
        }
    }

    /**
     * Invoked on checking the double header
     *
     * @param propertyId
     * @param isChecked
     */
    private void doubleHeaderCheckListener(Object propertyId, boolean isChecked) {
        String checkedPropertyId = String.valueOf(propertyId);
        ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
        String checkedColumnName = rightTable.getDoubleHeaderColumnHeader(checkedPropertyId);
        String discountPropertyId = rightTable.getTripleHeaderForDoubleHeader(checkedPropertyId);
        String discountName = rightTable.getTripleHeaderColumnHeader(discountPropertyId);

        checkedDoubleHeaders = tripleHeaderForCheckedDoubleHeader.get(discountName);

        if (checkedDoubleHeaders == null) {
            checkedDoubleHeaders = new HashMap<>();
        }

        List<String> checkedHistoryList = checkedDoubleHeaders.get("H");
        List<String> checkedProjectionList = checkedDoubleHeaders.get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);

        if (checkedHistoryList == null) {
            checkedHistoryList = new ArrayList<>();
        }
        if (checkedProjectionList == null) {

            checkedProjectionList = new ArrayList<>();
        }

        if (isChecked) {
            if (rightHeader.getDoubleProjectedColumns().contains(propertyId)) {
                checkedProjectionList.add(checkedColumnName);
                checkedDoubleHeaders.put(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY, checkedProjectionList);
            } else {
                checkedHistoryList.add(checkedColumnName);
                checkedDoubleHeaders.put("H", checkedHistoryList);
            }
            tripleHeaderForCheckedDoubleHeader.put(discountName, checkedDoubleHeaders);
            tripleHeaderForCheckedDoubleHeaderCustom.put(Constant.CUSTOM_LABEL, checkedDoubleHeaders);
        } else {
            if (rightHeader.getDoubleProjectedColumns().contains(propertyId)) {
                checkedProjectionList.remove(checkedColumnName);

                if (checkedProjectionList.isEmpty()) {
                    checkedDoubleHeaders.remove(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                }
            } else {
                checkedHistoryList.remove(checkedColumnName);

                if (checkedHistoryList.isEmpty()) {
                    checkedDoubleHeaders.remove("H");
                }
            }
            tripleHeaderForCheckedDoubleHeader.put(discountName, checkedDoubleHeaders);
            tripleHeaderForCheckedDoubleHeaderCustom.put(Constant.CUSTOM_LABEL, checkedDoubleHeaders);
        }
        if (checkedDoubleHeaders.isEmpty()) {
            tripleHeaderForCheckedDoubleHeader.remove(discountName);
        }

    }

    private void createSelectionDto() {
        LOGGER.debug(" Entering Selection Dto Creation");
        String freq = String.valueOf(frequencyDdlb.getValue());
        String hist = String.valueOf(historyDdlb.getValue());
        String yearValue = String.valueOf(yearSelection.getValue());
        int year = isInteger(yearValue) ? Integer.parseInt(yearValue) : 0;
        int historyNum = 0;
        try {
            if (year == 0 && !yearValue.equals(ALL.getConstant())) {
                if (freq.equals(QUARTERLY.getConstant())) {
                    historyNum = Integer.parseInt(hist.replace("Quarter", StringUtils.EMPTY)
                            .replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                } else if (freq.equals(SEMI_ANNUALLY.getConstant()) || freq.equals(SEMI_ANNUAL.getConstant())) {
                    historyNum = Integer.parseInt(hist.replace(Constant.SEMI_ANNUALY, StringUtils.EMPTY).trim());
                } else if (freq.equals(MONTHLY.getConstant())) {
                    historyNum = Integer.parseInt(hist.replace("Month", StringUtils.EMPTY)
                            .replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                } else if (freq.equals(ANNUALLY.getConstant()) || freq.equals(ANNUAL.getConstant())) {
                    historyNum = Integer.parseInt(hist.replace(Constant.YEAR, StringUtils.EMPTY).trim());
                }
            } else {
                historyNum = historyDdlb.getItemIds().size();
            }
            createProjectSelectionDto(freq,hist,historyNum,yearValue);
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
    }
private void createProjectSelectionDto(String freq,String hist,int historyNum,String yearValue) {
        projectionSelection.setForecastDTO(session.getForecastDTO());
        projectionSelection.setFrequency(freq);
        projectionSelection.setHistory(hist);
        projectionSelection.setHistoryNum(historyNum);
        projectionSelection
                .setProjectionNum(CommonUtils.getProjectionNumber(projectionSelection.getFrequency(), session));
        projectionSelection.setActualsOrProjections(String.valueOf(actualsProjs.getValue()));
        projectionSelection.setProjectionOrder(String.valueOf(periodOrder.getValue()));
        projectionSelection.setYear(yearValue);

        projectionSelection.setProjectionId(session.getProjectionId());
        projectionSelection.setUserId(isInteger(session.getUserId()) ? Integer.parseInt(session.getUserId()) : 0);
        projectionSelection
                .setSessionId(isInteger(session.getSessionId()) ? Integer.parseInt(session.getSessionId()) : 0);
        projectionSelection.setCustomId(customId);
        projectionSelection.setForecastConfigPeriods(CommonUtils.prepareProjectionPeriodList(projectionSelection));
        List<String> discountToBeLoaded = projectionSelection.getDeductionLevelCaptions();
        projectionSelection.setDiscountProgramsList(new ArrayList<>(discountToBeLoaded));
        Collection c = (Collection) variables.getValue();
        List<String> l = new ArrayList();
        for (Object s : c) {
            l.add(String.valueOf(s));
        }
        projectionSelection.setdPVariablesList(l);
        if (l.isEmpty()) {
            projectionSelection.setdPVariablesList(Arrays.asList(new String[]{DISCOUNT_AMT.getConstant()}));

        }
        LOGGER.debug(" Ending Selection Dto Creation");
    }

    private void createRightHeader() {
        projectionSelection.setHierarchyIndicator(hierarchyIndicator);
        List<Object> HeaderPropertyIds = HeaderUtils.getDiscountProjectionRightTableColumns(projectionSelection);
        rightHeader = (CustomTableHeaderDTO) HeaderPropertyIds.get(0);
        excelHeader = (CustomTableHeaderDTO) HeaderPropertyIds.get(1);
    }

    @Override
    protected void resetBtnClickLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                return;
            }

            @Override
            public void yesMethod() {

                if (frequencyDdlb.getValue().equals(MONTHLY.getConstant())) {
                    historyDdlb.setValue(NumericConstants.TWELVE);
                } else if (frequencyDdlb.getValue().equals(QUARTERLY.getConstant())) {
                    historyDdlb.setValue(NumericConstants.FOUR);
                } else if (frequencyDdlb.getValue().equals(ANNUALLY.getConstant())) {
                    historyDdlb.setValue(1);
                } else {
                    historyDdlb.setValue(NumericConstants.TWO);
                }
                programBean.addItem(SELECT_ONE.getConstant());
                programSelection.setNullSelectionAllowed(true);
                programSelection.setNullSelectionItemId(SELECT_ONE.getConstant());
                programSelection.setValue(SELECT_ONE.getConstant());
                programSelection.setImmediate(true);
                final BeanItemContainer<String> yearBean = new BeanItemContainer<>(String.class);
                yearSelection.setNullSelectionAllowed(true);
                yearSelection.setNullSelectionItemId(SELECT_ONE.getConstant());
                yearBean.addAll(loadYearSelection());
                yearSelection.setContainerDataSource(yearBean);
                level.setValue(PROGRAM_CATEGORY.getConstant());
                frequencyDdlb.setValue(QUARTERLY.getConstant());
                periodOrder.select(ASCENDING.getConstant());
                actualsProjs.select(ACTUALS.getConstant());
                if (!ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction())){
                variables.unselect(DISCOUNT_RATE.getConstant());
                variables.unselect(REBATE_PER_UNIT.getConstant());
                variables.unselect(GROWTH.getConstant());
                variables.select(DISCOUNT_AMT.getConstant());
                }
                uomDdlb.select("EACH");
                loadDisplayFormatDdlb();
                if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
                    loadSelections(true);
                    loadDeductionInclusion();
                } else {
                    resetForAdd();
                }
            }
        }.getConfirmationMessage("Reset Confirmation",
                "Are you sure you want to reset the ‘Discount Projection Selections’?");
    }

    public void resetForAdd() throws IllegalStateException {
        projectionSelection.setDeductionLevelFilter(Collections.EMPTY_LIST);
        projectionSelection.setDeductionLevelCaptions(Collections.EMPTY_LIST);
        projectionSelection.setProductLevelFilter(Collections.EMPTY_LIST);
        projectionSelection.setCustomerLevelFilter(Collections.EMPTY_LIST);
        CommonLogic.unCheckMultiSelect(productFilterValues);
        CommonLogic.unCheckMultiSelect(customerFilterValues);
        CommonLogic.unCheckMultiSelect(deductionFilterValues);
        customerlevelDdlb.select(Constant.SELECT_ONE);
        productlevelDdlb.select(Constant.SELECT_ONE);
        deductionlevelDdlb.select(Constant.SELECT_ONE);
        loadDeductionInclusion();
    }

    @Override
    protected void expandBtnClickLogic() {
        expandCollapseLevelOption(true);
    }

    @Override
    protected void collapseBtnClickLogic() {
        expandCollapseLevelOption(false);
    }

    private void expandCollapseLevelOption(boolean isExpand) {
        String levelNoString = String.valueOf(levelDdlb.getValue());
        if (isInteger(levelNoString)) {
            int levelNo = Integer.parseInt(levelNoString);
            if (!isExpand) {
                levelNo--;
            }

            if (!String.valueOf(levelFilterDdlb.getValue()).startsWith(Constant.DASH)) {
                tableLogic.clearAll();
            } else {
                tableLogic.clearAllExceptCurrentPage();
            }

            tableLogic.loadExpandData(levelNo);
            resetLevelFilterDdlb();
        } else {
            AbstractNotificationUtils.getErrorNotification("No Level Selected",
                    "Please select a Level from the drop down.");
        }
    }

    @Override
    protected void levelFilterValueChangeLogic(Property.ValueChangeEvent event) {
        LOGGER.debug("levelFilterDdlbvalueChangeListener initiated ");
        if ((enableLevelFilterListener) && (event.getProperty() != null
                && !Constant.NULL.equals(String.valueOf(event.getProperty().getValue())))) {
            LOGGER.debug(" event value= {} ", event.getProperty().getValue());
            resetTableData();
            String levelNumber = String.valueOf(event.getProperty().getValue());
            int levelNo = isInteger(levelNumber) ? Integer.parseInt(levelNumber) : 0;
            if (!levelNumber.startsWith(Constant.DASH)) {
                tableLogic.setChildrenAllowed(false);
                tableLogic.filterLevels(levelNo);

                // To reset to default
                tableLogic.setChildrenAllowed(true);
                tableLogic.setLevelNo(getStartLevelNo());
            } else {
                tableLogic.filterLevels(levelNo);
            }

            levelDdlb.setValue(SELECT_ONE.getConstant());
        }
        LOGGER.debug("levelFilterDdlbvalueChangeListener ended ");
    }

    @Override
    protected void massCheckValueChangeLogic(Property.ValueChangeEvent event) {
        String fieldValue = String.valueOf(event.getProperty().getValue());
        LOGGER.debug(" fieldValue= {} ", fieldValue);
        if (ENABLE.getConstant().equals(fieldValue)) {
            fieldDdlb.setReadOnly(false);
            startPeriod.setReadOnly(false);
            value.setReadOnly(false);
            endPeriod.setReadOnly(false);
            populateBtn.setEnabled(true);
        } else if (DISABLE.getConstant().equals(fieldValue)) {
            fieldDdlb.setReadOnly(true);
            value.setReadOnly(true);
            startPeriod.setReadOnly(true);
            endPeriod.setReadOnly(true);
            populateBtn.setEnabled(false);
        }
    }

    @Override
    protected void startPeriodValueChangeLogic(Property.ValueChangeEvent event) {
        String fieldValue = String.valueOf(event.getProperty().getValue());
        LOGGER.debug(" Start period= {} ", fieldValue);
        if (!Constant.NULL.equals(fieldValue)) {
            loadEndPeriod((List<String>) startPeriod.getItemIds(), fieldValue);
        }
    }

    /* To load end period in drop down */
    public void loadEndPeriod(List<String> periods, String startPeriod) {
        endPeriodBean.removeAllItems();
        endPeriod.addItem(SELECT_ONE.getConstant());
        endPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
        boolean end = true;
        LOGGER.debug(" startPeriod= {} ", startPeriod);
        for (String period : periods) {
            if (end && !period.equals(startPeriod)) {
                continue;
            }
            end = false;
            endPeriodBean.addItem(period);
        }
        String endPeriodValue = String.valueOf(endPeriod.getValue());
        if (!Constant.NULL.equals(endPeriodValue) && !endPeriodBean.getItemIds().contains(endPeriodValue)) {
            endPeriod.select(SELECT_ONE.getConstant());
        }
    }

    /**
     * value change of view.
     *
     * @param event the event
     */
    @Override
    protected void customDdlbLogic() {
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent initiated ");
        groupFilterDdlb.setEnabled(true);
        customId = CommonLogic.customDdlbOptionChange(viewDdlb, editBtn, levelDdlb);
        levelDdlb.setEnabled(customId != 0);

        if (!session.getCustomDetailMap().containsKey(customId)) {
            session.setCustomId(customId);
            Utility.loadCustomHierarchyList(session);
    }
        CommonUtil.getInstance().waitsForOtherThreadsToComplete(session.getFutureValue(Constant.CUST_VIEW_MAP_QUERY));
        currentHierarchy = session.getCustomHierarchyMap().get(customId);
        LOGGER.debug(" customId= {} ", customId);
        LOGGER.debug(" currentHierarchy= {} ", currentHierarchy.size());
        if (customId == 0) {
            tableLogic.clearAll();
            tableLogic.getControlTable().getContainerDataSource().removeAllItems();
        }
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent ends ");
    }

    /**
     * To get the discount names as List
     *
     * @return
     */
    public List<String> getDiscountNamesList() {
        return discountNamesList == null ? discountNamesList : new ArrayList<>(discountNamesList);
    }

    /**
     * To get the discount Type
     *
     * @return
     */
    public String getDiscountType() {
        if (level != null && level.getValue() != null) {
            return String.valueOf(level.getValue());
        }
        return null;
    }

    public void securityForButton() {
        try {
            final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermissionForNm(
                    String.valueOf(VaadinSession.getCurrent().getAttribute("businessRoleIds")),
                    getCommercialConstant() + "," + UISecurityUtil.DISCOUNT_PROJECTION);
            if (!(functionPsHM.get(CommonUtils.GENERATE_BUTTON) != null
                    && ((AppPermission) functionPsHM.get(CommonUtils.GENERATE_BUTTON)).isFunctionFlag())) {
                generateBtn.setVisible(false);
                populateBtn.setVisible(false);
                expandBtn.setVisible(false);
                collapseBtn.setVisible(false);
                newBtn.setVisible(false);
                editBtn.setVisible(false);
            }
            if (functionPsHM.get(CommonUtils.ALT_HISTORY_BTN) != null
                    && ((AppPermission) functionPsHM.get(CommonUtils.ALT_HISTORY_BTN)).isFunctionFlag()) {
                altHistoryBtn.setVisible(true);
            } else {
                altHistoryBtn.setVisible(false);
            }

            if (!(functionPsHM.get(CommonUtils.CALCULATE_BTN) != null
                    && ((AppPermission) functionPsHM.get(CommonUtils.CALCULATE_BTN)).isFunctionFlag())) {
                calculateBtn.setVisible(false);
                adjustBtn.setVisible(false);
            }
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(NMDiscountProjection.class.getName()).error(StringUtils.EMPTY, ex);
        }
    }

    public void loadDiscPeriods(BeanItemContainer<String> periodBean, ComboBox comboPeriod,
            List<String> projectedHeaders) {
        if (projectionSelection.getProjectionOrder().equals(DESCENDING.getConstant())) {
            Collections.reverse(projectedHeaders);
        }
        periodBean.removeAllItems();
        comboPeriod.addItem(SELECT_ONE.getConstant());
        comboPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
        periodBean.addAll(projectedHeaders);

    }

    private boolean checkHistorySelectedCount(int i) {
        List<String> periodCalc = new ArrayList<>();
        for (Object key : tripleHeaderForCheckedDoubleHeader.keySet()) {
            Map<String, List<String>> checkDoubleHeader = tripleHeaderForCheckedDoubleHeader
                    .get(String.valueOf(key));
            if (checkDoubleHeader != null) {
                List<String> checkedHistoryList = checkDoubleHeader.get("H");
                List<String> checkedProjList = checkDoubleHeader.get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                if (checkedHistoryList != null) {
                    periodCalc.addAll(checkedHistoryList);
                }
                if (checkedProjList != null) {
                    periodCalc.addAll(checkedProjList);
                }
                List<String> checkedProjList1 = getProjectedList(checkedProjList);
                if (periodCalc != null) {
                    if (i == NumericConstants.TWO && periodCalc.size() < i) {
                        return false;
                    }
                    if ((checkedHistoryList != null)
                            && (i == 0 && checkedHistoryList.size() == i && checkedProjList1.isEmpty())) {
                        return false;
                    }
                    if (i == 1 && periodCalc.size() != i) {
                        return false;
                    }
                } else if (checkedProjList1 != null) {
                    if (i == 0 && checkedProjList1.size() == i) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
            periodCalc.clear();
        }
        return true;

    }

    private boolean checkHistorySelectedCountCustom(int i) {
        List<String> periodCalc = new ArrayList<>();
        for (Object key : tripleHeaderForCheckedDoubleHeaderCustom.keySet()) {
            Map<String, List<String>> checkDoubleHeader = tripleHeaderForCheckedDoubleHeaderCustom
                    .get(String.valueOf(key));
            if (checkDoubleHeader != null) {
                List<String> checkedHistoryList = checkDoubleHeader.get("H");
                List<String> checkedProjList = checkDoubleHeader.get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                if (checkedHistoryList != null) {
                    periodCalc.addAll(checkedHistoryList);
                }
                if (checkedProjList != null) {
                    periodCalc.addAll(checkedProjList);
                }
                List<String> checkedProjList1 = getProjectedList(checkedProjList);
                if (periodCalc != null) {
                    if (i == NumericConstants.TWO && periodCalc.size() < i) {
                        return false;
                    }
                    if ((checkedHistoryList != null)
                            && (i == 0 && checkedHistoryList.size() == i && checkedProjList1.isEmpty())) {
                        return false;
                    }
                    if (i == 1 && periodCalc.size() != i) {
                        return false;
                    }
                } else if (checkedProjList1 != null) {
                    if (i == 0 && checkedProjList1.size() == i) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
            periodCalc.clear();
        }
        return true;

    }

    public boolean ismultipleDiscount() {
        boolean isOne = true;
        boolean ismultipleDiscount = false;
        tripleHeaderForCheckedDoubleHeader.keySet().iterator();
        checkedList = new ArrayList<>();
        for (String d : tripleHeaderForCheckedDoubleHeader.keySet()) {
            Map<String, List<String>> checkDoubleHeader = tripleHeaderForCheckedDoubleHeader.get(d);
            for (String e : checkDoubleHeader.keySet()) {
                List a = checkDoubleHeader.get(e);
                if (checkedList.size() > 0 && a.size() > 0 && !isOne) {
                    ismultipleDiscount = true;
                    break;
                } else {
                    checkedList.addAll(a);
                }
            }
            if (checkedList.size() > 0) {
                isOne = false;
            }

        }
        return !ismultipleDiscount;
    }

    private boolean isInPeriod() {
        int[] startPeriodValue = getQuaterandYear((String) startPeriodForecastTab.getValue());
        int[] endPeriodValue = getQuaterandYear((String) endPeriodForecastTab.getValue());
        boolean m = true;
        for (String columnName : checkedList) {
            int[] temp = getQuaterandYear(columnName);
            if (temp[1] >= startPeriodValue[1] && temp[1] <= endPeriodValue[1]) {
                if (temp[1] == startPeriodValue[1] && temp[0] >= startPeriodValue[0]) {
                } else {
                    m = false;
                }
                if (temp[1] == endPeriodValue[1] && temp[0] <= endPeriodValue[0]) {
                } else {
                    m = false;
                }
            } else {
                m = false;
            }
        }
        return m;

    }

    private int[] getQuaterandYear(String str) {
        LOGGER.debug("Inside get Quater with String= {}", str);
        int[] a = new int[NumericConstants.TWO];
        String[] splited = str.split("\\s+");
        a[0] = Integer.parseInt(new String(splited[0].replaceAll("[Q]+", StringUtils.EMPTY)));
        a[1] = Integer.parseInt(splited[1]);
        return a;
    }

    public boolean endDateValidation(String valueEnd) {
        LOGGER.debug("Inside End Date Validation");
        if (startPeriodForecastTab.getValue() != null) {
            if (frequencyDdlb.getValue().equals(MONTHLY.getConstant())) {
                int endMonth = CommonUtils.getIntegerForMonth(valueEnd.substring(0, NumericConstants.THREE));
                int startMonth = CommonUtils.getIntegerForMonth(
                        startPeriodForecastTab.getValue().toString().trim().substring(0, NumericConstants.THREE));
                int endYear = Integer.parseInt(valueEnd.substring(NumericConstants.FOUR, NumericConstants.EIGHT));
                int startYear = Integer.parseInt(startPeriodForecastTab.getValue().toString().trim()
                        .substring(NumericConstants.FOUR, NumericConstants.EIGHT));

                if (startYear < endYear) {
                    return BooleanConstant.getTrueFlag();
                }
                if (startYear == endYear && startMonth <= endMonth) {
                    return BooleanConstant.getTrueFlag();
                }
                return BooleanConstant.getFalseFlag();
            }
            if (!frequencyDdlb.getValue().equals(ANNUALLY.getConstant())) {
                String startValue = startPeriodForecastTab.getValue().toString().replace(' ', '~').trim();
                String endValue = valueEnd.replace(' ', '~').trim();
                String[] startValue1 = startValue.split("~");
                String[] endValue1 = endValue.split("~");
                if (Integer.parseInt(startValue1[1].trim()) > Integer.parseInt(endValue1[1].trim())) {
                    errorFlag = true;
                    return false;
                } else if ((Integer.valueOf(startValue1[1].trim())).equals(Integer.valueOf(endValue1[1].trim()))) {
                    startValue1[0] = startValue1[0].replace(Constant.Q, StringUtils.EMPTY).trim();
                    endValue1[0] = endValue1[0].replace(Constant.Q, StringUtils.EMPTY).trim();
                    if (Integer.parseInt(startValue1[0]) > Integer.parseInt(endValue1[0])) {
                        errorFlag = true;
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                String startValue = startPeriodForecastTab.getValue().toString();

                if (Integer.parseInt(startValue) > Integer.parseInt(valueEnd)) {
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(Constant.NO_START_PERIOD_SELECTED,
                    "Please select a Start Period.");
            errorFlag = false;
            return false;
        }
    }

    /**
     * Gets the variable for the calculation.
     *
     * @return
     */
    public boolean getCalculationBased() {

        boolean tempSalesvalue = false;
        boolean tempUnitValue = false;
        boolean tempRpuValue = false;
        String calc = StringUtils.EMPTY;
        for (int i = 0; i < radioMap.size(); i++) {
            String value = radioMap.getIndex(i).getValue().toString();
            if (checkBoxMap.get(radioMap.getIndex(i).getKey()) != null
                    && Boolean.parseBoolean(checkBoxMap.get(radioMap.getIndex(i).getKey()).toString())) {
                if (value.contains("ActualRate")) {
                    tempSalesvalue = true;
                    calc = Constant.RATE;
                }

                if (value.contains("ActualAmount")) {
                    tempUnitValue = true;
                    calc = "AMOUNT";
                }
                if (value.contains(Constant.ACTUALRPU)) {
                    tempRpuValue = true;
                    calc = "RPU";
                }
            }

            if ((tempSalesvalue && tempUnitValue) || (tempSalesvalue && tempRpuValue)
                    || (tempRpuValue && tempUnitValue)) {
                return false;

            }
        }
        calcBase = calc;
        return true;
    }

    public boolean isDiscountGenerated() {
        return isDiscountGenerated;
    }

    private void loadMonthMap(GtnSmallHashMap monthMap) {
        monthMap.put("Jan", "01");
        monthMap.put("Feb", "02");
        monthMap.put("Mar", "03");
        monthMap.put("Apr", "04");
        monthMap.put("May", "05");
        monthMap.put("Jun", "06");
        monthMap.put("Jul", "07");
        monthMap.put("Aug", "08");
        monthMap.put("Sep", "09");
        monthMap.put("Oct", "10");
        monthMap.put("Nov", "11");
        monthMap.put("Dec", "12");
    }

    public boolean baseLineCalc(String startPeriod, String endPeriod) {
        boolean retval = true;
        Map<String, List<String>> checkDoubleHeader = null;
        for (Object key : tripleHeaderForCheckedDoubleHeader.keySet()) {
            checkDoubleHeader = tripleHeaderForCheckedDoubleHeader.get(String.valueOf(key));
        }
        if (checkDoubleHeader != null) {
            List<String> checkedHistoryList = checkDoubleHeader.get("H");
            List<String> checkedProjList = checkDoubleHeader.get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            GtnSmallHashMap monthMap = new GtnSmallHashMap();
            int defval = 0;
            String frequency = String.valueOf(frequencyDdlb.getValue().toString()).trim();
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                defval = NumericConstants.TWO;
            } else if (frequency.equals(QUARTERLY.getConstant())) {
                defval = NumericConstants.FOUR;
            } else if (frequency.equals(MONTHLY.getConstant())) {
                defval = NumericConstants.TWELVE;
                loadMonthMap(monthMap);
            } else if (frequency.equals(ANNUALLY.getConstant())) {
                defval = 1;
            }
            List<String> overall = new ArrayList<>();
            if (checkedHistoryList != null) {
                overall.addAll(checkedHistoryList);
            }
            if (checkedProjList != null) {
                overall.addAll(checkedProjList);
            }
            if (!overall.isEmpty()) {
                int[] year = new int[overall.size()];
                String tempYear = StringUtils.EMPTY;
                String tempSubYear = StringUtils.EMPTY;
                for (int i = 0; i < overall.size(); i++) {
                    if (defval == NumericConstants.TWO || defval == NumericConstants.FOUR) {
                        tempYear = overall.get(i).trim().substring(NumericConstants.TWO);
                        tempSubYear = (overall.get(i).trim()).replace(tempYear, StringUtils.EMPTY).trim();
                    } else if (defval == NumericConstants.TWELVE) {
                        tempYear = overall.get(i).trim().substring(NumericConstants.THREE);
                        String tmpSubYear = overall.get(i).trim().replace(tempYear, StringUtils.EMPTY)
                                .trim();
                        tempSubYear = monthMap.get(tmpSubYear).toString();
                    }
                    String subYear1 = StringUtils.EMPTY;
                    if (defval == NumericConstants.TWO) {
                        subYear1 = tempSubYear.replace(Constant.S, StringUtils.EMPTY);
                    } else if (defval == NumericConstants.FOUR) {
                        subYear1 = tempSubYear.replace(Constant.Q, StringUtils.EMPTY);
                    }
                    if (defval != 1) {
                        String fullYear = tempYear + subYear1;
                        year[i] = Integer.parseInt(fullYear.trim());
                    } else {
                        year[i] = Integer.parseInt(overall.get(i).trim());
                    }

                }
                Arrays.sort(year);
                String startTempYear = StringUtils.EMPTY;
                String startTempSubYear = StringUtils.EMPTY;
                String endTempYear = StringUtils.EMPTY;
                String endTempSubYear = StringUtils.EMPTY;
                if (defval == NumericConstants.TWO || defval == NumericConstants.FOUR) {
                    startTempYear = startPeriod.trim().substring(NumericConstants.TWO);
                    startTempSubYear = startPeriod.replace(startTempYear, StringUtils.EMPTY).trim();
                    endTempYear = endPeriod.trim().substring(NumericConstants.TWO);
                    endTempSubYear = endPeriod.replace(endTempYear, StringUtils.EMPTY).trim();
                } else if (defval == NumericConstants.TWELVE) {
                    startTempYear = startPeriod.trim().substring(NumericConstants.THREE);
                    String startTmpSubYear = startPeriod.replace(startTempYear, StringUtils.EMPTY).trim();
                    startTempSubYear = monthMap.get(startTmpSubYear).toString();
                    endTempYear = endPeriod.trim().substring(NumericConstants.THREE);
                    String endTmpSubYear = endPeriod.replace(endTempYear, StringUtils.EMPTY).trim();
                    endTempSubYear = monthMap.get(endTmpSubYear).toString();
                } else if (defval == 1) {
                    startTempYear = startPeriod.trim();
                    endTempYear = endPeriod.trim();
                }
                String subYear2 = StringUtils.EMPTY;
                String subYear3 = StringUtils.EMPTY;
                if (defval == NumericConstants.TWO) {
                    subYear2 = startTempSubYear.replace(Constant.S, StringUtils.EMPTY);
                    subYear3 = endTempSubYear.replace(Constant.S, StringUtils.EMPTY);
                } else if (defval == NumericConstants.FOUR) {
                    subYear2 = startTempSubYear.replace(Constant.Q, StringUtils.EMPTY);
                    subYear3 = endTempSubYear.replace(Constant.Q, StringUtils.EMPTY);
                } else if (defval == NumericConstants.TWELVE) {
                    subYear2 = startTempSubYear;
                    subYear3 = endTempSubYear;
                }
                String startfullYear = startTempYear + subYear2;
                String endfullYear = endTempYear + subYear3;
                int finStartPeriod = 0;
                int finEndPeriod = 0;
                finStartPeriod = Integer.parseInt(startfullYear.trim());
                finEndPeriod = Integer.parseInt(endfullYear.trim());
                if (year[year.length - 1] > finEndPeriod) {
                    baselineFlag = NumericConstants.TWO;
                    return false;
                } else if (year[year.length - 1] >= finStartPeriod && year[year.length - 1] <= finEndPeriod) {
                    baselineFlag = 1;
                    return false;
                } else {
                    retval = true;
                }
            }
        }
        return retval;
    }

    private boolean rollingAnnualTrendCount() {
        boolean retval = true;
        Map<String, List<String>> checkDoubleHeader = null;
        for (Object key : tripleHeaderForCheckedDoubleHeader.keySet()) {
            checkDoubleHeader = tripleHeaderForCheckedDoubleHeader.get(String.valueOf(key));
        }
        if (checkDoubleHeader != null) {
            List<String> checkedHistoryList = checkDoubleHeader.get("H");
            List<String> checkedProjList = checkDoubleHeader.get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            GtnSmallHashMap monthMap = new GtnSmallHashMap();
            int defval = 0;
            String frequency = String.valueOf(frequencyDdlb.getValue().toString()).trim();
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                defval = NumericConstants.TWO;
            } else if (frequency.equals(QUARTERLY.getConstant())) {
                defval = NumericConstants.FOUR;
            } else if (frequency.equals(MONTHLY.getConstant())) {
                defval = NumericConstants.TWELVE;
                loadMonthMap(monthMap);
            } else if (frequency.equals(ANNUALLY.getConstant())) {
                defval = 1;
            }
            List<String> overall = new ArrayList<>();
            if (checkedHistoryList != null) {
                overall.addAll(checkedHistoryList);
            }
            if (checkedProjList != null) {
                overall.addAll(checkedProjList);
            }

            final Comparator<String> dateCompare = new Comparator<String>() {

                @Override
                public int compare(String o1, String o2) {
                    int retval = NumericConstants.MINUS_ONE;
                    SimpleDateFormat s = new SimpleDateFormat("MMM");
                    try {
                        Date s1 = s.parse(o1);
                        Date s2 = s.parse(o2);
                        retval = s1.compareTo(s2);
                    } catch (ParseException e) {
                        LOGGER.error(e.getMessage());
                    }
                    return retval;
                }
            };

            Collections.sort(overall, dateCompare);
            if (!overall.isEmpty() && overall.size() == defval) {
                String subYear1 = StringUtils.EMPTY;
                int[] year = new int[overall.size()];
                int[] Quarter = new int[overall.size()];
                String tempYear = StringUtils.EMPTY;
                String tempSubYear = StringUtils.EMPTY;
                for (int i = 0; i < overall.size(); i++) {
                    if (defval == NumericConstants.TWO || defval == NumericConstants.FOUR) {
                        tempYear = overall.get(i).trim().substring(NumericConstants.TWO);
                        tempSubYear = (overall.get(i).trim()).replace(tempYear, StringUtils.EMPTY).trim();
                    } else if (defval == NumericConstants.TWELVE) {
                        tempYear = overall.get(i).trim().substring(NumericConstants.THREE);
                        String tmpSubYear = overall.get(i).trim().replace(tempYear, StringUtils.EMPTY)
                                .trim();
                        tempSubYear = monthMap.get(tmpSubYear).toString();
                    } else if (defval == 1) {
                        tempYear = overall.get(i).trim();
                    }

                    if (defval == NumericConstants.TWO) {
                        subYear1 = tempSubYear.replace(Constant.S, StringUtils.EMPTY);
                    } else if (defval == NumericConstants.FOUR) {
                        subYear1 = tempSubYear.replace(Constant.Q, StringUtils.EMPTY);
                    } else if (defval == NumericConstants.TWELVE) {
                        subYear1 = tempSubYear;
                    }
                    String fullYear = tempYear + subYear1;
                    year[i] = Integer.parseInt(fullYear.trim());

                }
                int i = 0;
                Arrays.sort(year);
                for (i = 0; i < year.length; i++) {
                    Quarter[i] = year[i] % NumericConstants.TEN;
                    year[i] = year[i] / NumericConstants.TEN;
                }
                if ((Quarter[0] == 1 && Quarter[defval - 1] == defval) && (year[0] == year[defval - 1])) {
                    retval = true;
                } else if ((Quarter[0] == (Quarter[defval - 1] + 1)) && (year[0] == (year[defval - 1] - 1))) {
                    retval = true;
                } else if (Integer.parseInt(subYear1) == NumericConstants.TWELVE) {
                    retval = true;
                } else {
                    retval = false;
                }
            } else {
                retval = false;
            }
        }
        return retval;
    }

    private boolean exFactoryMethodologyCheck() {
        boolean retval = true;
        Map<String, List<String>> checkDoubleHeader = null;
        for (Object key : tripleHeaderForCheckedDoubleHeader.keySet()) {
            checkDoubleHeader = tripleHeaderForCheckedDoubleHeader.get(String.valueOf(key));
        }
        if (checkDoubleHeader != null) {
            List<String> checkedHistoryList = checkDoubleHeader.get("H");
            List<String> checkedProjList = checkDoubleHeader.get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            GtnSmallHashMap monthMap = new GtnSmallHashMap();
            int defval = 0;
            String frequency = String.valueOf(frequencyDdlb.getValue().toString()).trim();
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                defval = NumericConstants.TWO;
            } else if (frequency.equals(QUARTERLY.getConstant())) {
                defval = NumericConstants.FOUR;
            } else if (frequency.equals(MONTHLY.getConstant())) {
                defval = NumericConstants.TWELVE;
                loadMonthMap(monthMap);
            } else if (frequency.equals(ANNUALLY.getConstant())) {
                defval = 1;
            }
            List<String> overall = new ArrayList<>();
            if (checkedHistoryList != null) {
                overall.addAll(checkedHistoryList);
            }
            if (checkedProjList != null) {
                overall.addAll(checkedProjList);
            }

            final Comparator<String> dateCompare = new Comparator<String>() {

                @Override
                public int compare(String o1, String o2) {
                    int retval = NumericConstants.MINUS_ONE;
                    SimpleDateFormat s = new SimpleDateFormat("MMM");
                    try {
                        Date s1 = s.parse(o1);
                        Date s2 = s.parse(o2);
                        retval = s1.compareTo(s2);
                    } catch (ParseException e) {
                        LOGGER.error(e.getMessage());
                    }
                    return retval;
                }
            };

            Collections.sort(overall, dateCompare);
            if (!overall.isEmpty() && overall.size() == defval) {
                Set<Integer> year = new HashSet<Integer>();
                String tempYear = StringUtils.EMPTY;
                for (int i = 0; i < overall.size(); i++) {
                    if (defval == NumericConstants.TWO || defval == NumericConstants.FOUR) {
                        tempYear = overall.get(i).trim().substring(NumericConstants.TWO);
                    } else if (defval == NumericConstants.TWELVE) {
                        tempYear = overall.get(i).trim().substring(NumericConstants.THREE);
                    } else if (defval == 1) {
                        tempYear = overall.get(i).trim();
                    }
                    String fullYear = tempYear;
                    year.add(Integer.valueOf(fullYear.trim()));
                }
                if (year.size() > 1) {
                    retval = false;
                } else {
                    retval = true;
                }
            } else {
                retval = false;
            }
        }
        return retval;
    }

    private List<String> getProjectedList(List<String> checkedProjList) {
        List<String> tempCheckedProjList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        DateFormat dateFormat1 = new SimpleDateFormat(Constant.MM);
        Date date = new Date();
        String strMonth;
        if (dateFormat1.format(date).length() > 1 && (dateFormat1.format(date).charAt(0) == '0')) {
            strMonth = dateFormat1.format(date).substring(1);
        } else {
            strMonth = dateFormat1.format(date);
        }
        int divval = 1;
        int defval = 0;
        int month = Integer.parseInt(strMonth);
        String frequency = String.valueOf(frequencyDdlb.getValue().toString()).trim();
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            divval = NumericConstants.SIX;
            defval = NumericConstants.TWO;
            month = month + divval;
        } else if (frequency.equals(QUARTERLY.getConstant())) {
            divval = NumericConstants.THREE;
            defval = NumericConstants.FOUR;
            month = month + divval;
        } else if (frequency.equals(ANNUALLY.getConstant())) {
            defval = 1;
        }
        if (month % divval == 0 && divval != 1 && frequency.equals(MONTHLY.getConstant())) {
            month = (month / divval) - 1;
        } else if (divval != 1) {
            month = month / divval;
        }
        String strYear = dateFormat.format(date) + String.valueOf(month);
        int intYear = Integer.parseInt(strYear);
        GtnSmallHashMap monthMap = new GtnSmallHashMap();
        if (frequency.equals(MONTHLY.getConstant())) {
            defval = NumericConstants.TWELVE;
            loadMonthMap(monthMap);
        }
        if (checkedProjList != null) {
            int[] year = new int[checkedProjList.size()];

            String tempYear = StringUtils.EMPTY, tempSubYear = StringUtils.EMPTY;
            for (int i = 0; i < checkedProjList.size(); i++) {
                if (defval == NumericConstants.TWO || defval == NumericConstants.FOUR) {
                    tempYear = checkedProjList.get(i).trim().substring(NumericConstants.TWO);
                    tempSubYear = (checkedProjList.get(i).trim()).replace(tempYear, StringUtils.EMPTY)
                            .trim();
                } else if (defval == NumericConstants.TWELVE) {
                    tempYear = checkedProjList.get(i).trim().substring(NumericConstants.THREE);
                    String tmpSubYear = checkedProjList.get(i).trim().replace(tempYear, StringUtils.EMPTY)
                            .trim();
                    tempSubYear = monthMap.get(tmpSubYear).toString();
                } else if (defval == 1) {
                    tempYear = checkedProjList.get(i).trim();
                }
                String subYear1 = StringUtils.EMPTY;
                if (defval == NumericConstants.TWO) {
                    subYear1 = tempSubYear.replace(Constant.S, StringUtils.EMPTY);
                } else if (defval == NumericConstants.FOUR) {
                    subYear1 = tempSubYear.replace(Constant.Q, StringUtils.EMPTY);
                } else if (defval == NumericConstants.TWELVE) {
                    subYear1 = tempSubYear;
                }
                String fullYear = tempYear + subYear1;
                year[i] = Integer.parseInt(fullYear.trim());
            }
            int i = 0;
            Arrays.sort(year);
            for (i = 0; i < year.length; i++) {
                if (year[i] < intYear) {
                    tempCheckedProjList.add(String.valueOf(year[i]));
                }
            }
        }
        return tempCheckedProjList;
    }

    public double doubleConversion(String value) {
        double doubleVal = Double.parseDouble(value);
        int freqDiv = getFrequencyDivision(projectionSelection.getFrequency());
        return doubleVal / (ccpsCount * freqDiv);
    }

    /**
     * The Table Group Filter Ddlb value change.
     *
     * @param event the event
     */
    private Property.ValueChangeListener tableGroupFilterDdlbValueChange = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            userGroup = String.valueOf(groupFilterDdlb.getValue());
            userGroup = String.valueOf(event.getProperty().getValue());
            if (Constant.NULL.equals(userGroup)) {
                userGroup = StringUtils.EMPTY;
            }
            userGroup = Constant.SHOW_ALL_GROUPS.equals(userGroup) ? StringUtils.EMPTY : userGroup;
            LOGGER.debug(" userGroup-in tableGroupFilterDdlbValueChange-= {}", userGroup);
            loadDataInTable();
        }
    };

    @Override
    protected void resetBtnForTableLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                return;
            }

            @Override
            public void yesMethod() {
                NonMandatedLogic logic1 = new NonMandatedLogic();
                try {
                    if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction())) {
                        LOGGER.debug("Reset the temp table by deleting and inserting ");
                        logic1.deleteTempData(session, "DISC_DELETE_TEMP");
                        logic1.tempInsertReturns(session, "DISC_EDIT_TEMP_INSERT_QUERY");
                        if (!generateDiscountToBeLoaded.isEmpty()) {
                            generateListView(true);
                        }
                    } else if (Constants.CommonConstants.ACTION_GENERATE.getConstant()
                            .equalsIgnoreCase(session.getAction())) {
                        LOGGER.debug("Reset the temp table by deleting and inserting by proc");
                        logic1.deleteTempData(session, "DISC_DELETE_TEMP");
                        discountProjectionLogic.callDiscountProjectionProcedure(session);
                        refreshTableData(getManualEntryRefreshHierarachyNo());
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        }.getConfirmationMessage("Confirm List View Reset",
                "Are you sure you want to reset the list view to the last saved state?");

    }

    private boolean isCheckBoxProperty() {
        boolean propertyId = false;

        if (checkBoxMap.containsValue(BooleanConstant.getTrueFlag())) {
            propertyId = true;
        }
        return propertyId;
    }

    private void callResetTableLogic() {
        List<String> discountToBeLoaded;
        if (!programSelectionList.isEmpty()) {
            discountToBeLoaded = programSelectionList;
        } else {
            discountToBeLoaded = discountProgramsList;
        }
        if (discountToBeLoaded == null || discountToBeLoaded.isEmpty()) {
        } else if (frequencyDdlb.getValue() == null || frequencyDdlb.getValue().equals(SELECT_ONE.getConstant())) {
        } else if (historyDdlb.getValue() == null || historyDdlb.getValue().equals(SELECT_ONE.getConstant())) {
        } else {
            tableLogic.sinkItemPerPageWithPageLength(false);
            boolean isFrequencyChange = !String.valueOf(projectionSelection.getFrequency())
                    .equals(String.valueOf(frequencyDdlb.getValue()));
            createSelectionDto();
            createRightHeader();
            viewValueChangeLogic();
            resultBeanContainer.setColumnProperties(rightHeader.getProperties());
            resultsTable.constructRightFreeze(true);
            resultsTable.getRightFreezeAsTable().setContainerDataSource(tableLogic.getContainerDataSource());
            configureRightTable();
            configureLeftTable();
            tableLogic.setRefresh(BooleanConstant.getFalseFlag());
            loadScreenBasedOnGeneratedTable(isFrequencyChange);
            loadDataInTable();
            tableLogic.setRefresh(BooleanConstant.getFalseFlag()); // As the row refresh will be
            formatTableData();
            tableLogic.setRefresh(BooleanConstant.getTrueFlag());
            setListviewGenerated(true);
            loadLevelValues();
            isDiscountGenerated = true;
            adjProgramsValueChangeLogic(SELECT.getConstant());
            adjPeriodValueChangeLogic(SELECT.getConstant());
            adjperiods.select(SELECT);
            adjprograms.select(SELECT);
        }
    }

    public boolean validateForAlternateHistory() {

        NMDiscountProjectionLogic logic = new NMDiscountProjectionLogic();

        if (checkedDiscountsPropertyIds.size() > 0) {
            if (checkedDiscountsPropertyIds.size() != 0) {
                String selectedRsName = resultsTable.getRightFreezeAsTable()
                        .getTripleHeaderColumnHeader(checkedDiscountsPropertyIds.get(0));

                rsModelSid = logic.getRsModelSid(selectedRsName);
                Set<Integer> totalCcp = new HashSet<>();
                if (CUSTOMER.getConstant().equals(String.valueOf(view.getValue()))) {

                    int numoFCustomers = getCheckedCustomercount(logic);

                    if (numoFCustomers > 0) {
                        String ccpIds = logic.getCCPList(logic.buildCCPListQuery(true, session.getProjectionId()),
                                totalCcp, session);
                        totalccpCount = totalCcp.size();

                        if (isNotHavingActuals(logic, ccpIds, rsModelSid, totalCcp)) {

                            return true;

                        } else {

                            NotificationUtils.getAlertNotification("Invalid Record Selection",
                                    "The selected Customer and Dicount combination already having actuals.");
                            return false;
                        }

                    } else if (numoFCustomers == 0) {

                        NotificationUtils.getAlertNotification("No Customer Level  Selected",
                                "Please select a Customer level for which  Alternate History to be imported.");
                        return false;

                    }
                } else if (PRODUCT.getConstant().equals(String.valueOf(view.getValue()))) {

                    int numOfNDC = getCheckedProductCount(logic);

                    if (numOfNDC == 1) {
                        String ccpIds = logic.getCCPList(logic.buildCCPListQuery(false, session.getProjectionId()),
                                totalCcp, session);
                        totalccpCount = totalCcp.size();
                        if (isNotHavingActuals(logic, ccpIds, rsModelSid, totalCcp)) {

                            return true;

                        } else {

                            NotificationUtils.getAlertNotification("Invalid Record Selection",
                                    "The selected Item and Dicount combination already having actuals.");
                            return false;
                        }

                    } else if (numOfNDC > 0) {

                        NotificationUtils.getAlertNotification("No NDC Level Selected",
                                "Please select a NDC level for which  Alternate History to be imported.");
                        return false;

                    }
                }

            } else {
                NotificationUtils.getAlertNotification("Multiple Discount Selected",
                        "Please select a single discount for which  Alternate History to be imported.");
                return false;
            }

        } else {
            NotificationUtils.getAlertNotification("No Discount Selected",
                    "Please select which discount Alternate History to be imported.");
            return false;

        }

        return true;
    }

    public boolean isNotHavingActuals(NMDiscountProjectionLogic logic, String ccpIds, int rsModelSid,
            Set<Integer> totalccp) {
        actualCCPs = logic.getZeroActualCCPList(logic.buildActualValidateQuery(ccpIds, rsModelSid), totalccp, session);
        if (totalccpCount == totalccp.size() && actualCCPs.isEmpty()) {
            for (int ccpId : totalccp) {
                if (actualCCPs.isEmpty()) {
                    actualCCPs = StringUtils.EMPTY + ccpId;
                } else {
                    actualCCPs = actualCCPs + "," + ccpId;

                }
            }
            return true;
        }

        return false;

    }

    public int getCheckedCustomercount(final NMDiscountProjectionLogic logic) {
        return logic.getCCPCount(QueryUtil.replaceTableNames(logic.buildCCPCountQuery(true, session.getProjectionId()),
                session.getCurrentTableNames()));
    }

    public int getCheckedProductCount(final NMDiscountProjectionLogic logic) {
        return logic.getCCPCount(QueryUtil.replaceTableNames(logic.buildCCPCountQuery(false, session.getProjectionId()),
                session.getCurrentTableNames()));
    }

    /**
     * To get the Hierarchy No of Checked record for Refresh
     *
     * @param tableLevelNo
     * @return
     */
    private String getHierarchyNoForCheckedRecord(final Set<String> tableLevelNo) {
        String hierarchyNo;
        StringBuilder hierarchyNoBuilder = new StringBuilder(); 
        for (String tableTreeLevelNo : tableLevelNo) {

            Object itemId = tableLogic.getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                itemId = tableLogic.getExpandedTreeValues(tableTreeLevelNo);
            }
            if (itemId != null) {
                hierarchyNoBuilder.append(((DiscountProjectionDTO) itemId).getHierarchyNo()).append(',');
            }
        }
        hierarchyNo = hierarchyNoBuilder.toString();
        return StringUtils.isEmpty(hierarchyNo) ? StringUtils.EMPTY
                : hierarchyNo.substring(0, hierarchyNo.length() - 1);
    }

    public boolean getSubmitFlag() {
        return !resultBeanContainer.isEmpty();
    }

    public void configure() {
        if (flag) {
            getContent();

            flag = false;
        }
    }

    private int getFrequencyDivision(String freq) {
        int freDiv = NumericConstants.THREE;
        if (freq.equalsIgnoreCase(ANNUALLY.getConstant())) {
            freDiv = NumericConstants.TWELVE;
        } else if (freq.equalsIgnoreCase(SEMI_ANNUALLY.getConstant())) {
            freDiv = NumericConstants.SIX;
        } else if (freq.equalsIgnoreCase(QUARTERLY.getConstant())) {
            freDiv = NumericConstants.THREE;
        } else if (freq.equalsIgnoreCase(MONTHLY.getConstant())) {
            freDiv = 1;
        }
        return freDiv;
    }

    private void cumulativeCalculation(ProjectionSelectionDTO projectionSelection, String methodology, String tableName,
            List<String> checkedDiscountNames, String level) {
        try {
            Object[] procedureInputs = null;
            projectionSelection.setTabName(DISCOUNT_PROJ.getConstant());
            LOGGER.debug("PRC_GROWTH_CALCULATION--------------------------------------- ");
            String discountId = CommonUtils.CollectionToString(checkedDiscountNames, false);
            procedureInputs = new Object[]{projectionSelection.getProjectionId(), projectionSelection.getUserId(),
                projectionSelection.getSessionDTO().getSessionId(), projectionSelection.getTabName(), methodology,
                projectionSelection.getFrequency(), UiUtils.getDate(), level, projectionSelection.getFromDateDdlb(),
                projectionSelection.getToDateDdlb(), discountId};
            new CumulativeCalculationUtils(procedureInputs, String.valueOf(projectionSelection.getUserId()),
                    projectionSelection.getSessionDTO().getSessionId(), methodology, projectionSelection.getTabName(),
                    tableName);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    private String getPreviousHierachy(String hierarchyNo, String hierarchyIndicator) {
        String parentHierarchy = tableLogic.getParentHierarchyNo(hierarchyNo);
        Object itemId = tableLogic.getcurrentTreeData(hierarchyNo);
        if (itemId == null) {
            itemId = tableLogic.getExpandedTreeValues(hierarchyNo);
        }
        if (itemId != null) {
            if (hierarchyIndicator.equals(((DiscountProjectionDTO) itemId).getHierarchyIndicator())) {
                return getPreviousHierachy(parentHierarchy, ((DiscountProjectionDTO) itemId).getHierarchyIndicator());
            }
            return ((DiscountProjectionDTO) itemId).getHierarchyNo();
        }
        return "";
    }

    private DiscountProjectionDTO getPreviousHierachyCustom(String hierarchyNo, String hierarchyIndicator) {
        String parentHierarchy = tableLogic.getParentHierarchyNo(hierarchyNo);
        Object itemId = tableLogic.getcurrentTreeData(hierarchyNo);
        if (itemId != null) {
            if (hierarchyIndicator.equals(((DiscountProjectionDTO) itemId).getHierarchyIndicator())) {
                return getPreviousHierachyCustom(parentHierarchy, ((DiscountProjectionDTO) itemId).getHierarchyIndicator());
            }
            return (DiscountProjectionDTO) itemId;
        }
        return null;
    }

    public ExtTreeContainer<DiscountProjectionDTO> getResultBeanContainer() {
        return resultBeanContainer;
    }

    private void loadProductLevel() {

        int hierarchyLevelNo = isInteger(session.getProductLevelNumber())
                ? Integer.parseInt(session.getProductLevelNumber()) : 0;
        currentHierarchy = CommonLogic.getProductHierarchy(session.getProjectionId(), hierarchyLevelNo,
                session.getProdRelationshipBuilderSid(), session.getProductRelationVersion());
        Collections.sort(currentHierarchy, new Comparator<Leveldto>() {
            @Override
            public int compare(Leveldto o1, Leveldto o2) {
                return o2.getTreeLevelNo() - o1.getTreeLevelNo();
            }
        });
        Collections.reverse(currentHierarchy);
        Utility.loadDdlbForLevelFilterOption(productlevelDdlb, currentHierarchy, StringUtils.EMPTY);
        productlevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                generateProductToBeLoaded = Collections.emptyList();
                if (event.getProperty().getValue() != null) {
                    String productlevelDdlbValue = String.valueOf(event.getProperty().getValue());
                    productlevelDdlbValue = ANULL.equals(productlevelDdlbValue) ? StringUtils.EMPTY
                            : productlevelDdlbValue;
                    loadProductLevelFilter(productlevelDdlbValue);
                } else {
                    loadProductLevelFilter(StringUtils.EMPTY);
                }
            }
        });
    }

    private void loadDedutionLevel() {
        List<String[]> deductionLevel = CommonLogic.getDeductionLevel(session.getProjectionId());
        Utility.loadDdlbForDeduction(deductionlevelDdlb, deductionLevel,session);
        deductionlevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    session.setDataSelectionDeductionLevelCaption(deductionlevelDdlb.getItemCaption(deductionlevelDdlb.getValue()));
                    String deductionLevelDdlbValue = String.valueOf(event.getProperty().getValue());
                    deductionLevelDdlbValue = ANULL.equals(deductionLevelDdlbValue) ? StringUtils.EMPTY
                            : deductionLevelDdlbValue;
                    loadDeductionLevelFilter(deductionLevelDdlbValue, true);
                } else {
                    loadDeductionLevelFilter(StringUtils.EMPTY, true);
                }
            }
        });
    }

    private void loadProductLevelFilter(String levelNo) {
        List<Object[]> productLevelFilter = new ArrayList<>();

        productFilterDdlb.removeSubMenuCloseListener(productlistener);

        productFilterDdlb.removeItems();
        productFilterValues = productFilterDdlb.addItem(SELECT_LEVEL_LABEL, null);

        if (!levelNo.isEmpty()) {
            productLevelFilter.add(0, new Object[]{0, SELECT_ALL_LABEL});
            productLevelFilter
                    .addAll(commonLogic.getProductLevelValues(session.getProjectionId(), levelNo, projectionSelection, generateCustomerToBeLoaded, generateDiscountToBeLoaded, String.valueOf(session.getProductRelationVersion())));
            CommonLogic.loadCustomMenuBar(productLevelFilter, productFilterValues);
        }

        productFilterDdlb.setScrollable(true);
        productFilterDdlb.setPageLength(NumericConstants.TEN);
        CommonLogic.loadMenuBar((List) generateProductToBeLoaded, productFilterValues);
        String productMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(productFilterValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(productFilterDdlb, productMenuItemValue);
        productFilterDdlb.addSubMenuCloseListener(productlistener);
    }

    private void loadDeductionLevelFilter(String levelNo, boolean isResetNeeded) {
        List<Object[]> deductionLevelFilter = new ArrayList<>();
        if (isResetNeeded) {
            generateDiscountToBeLoaded.clear();
        }
        deductionFilterDdlb.removeSubMenuCloseListener(deductionlistener);
        deductionFilterDdlb.removeItems();
        deductionFilterValues = deductionFilterDdlb.addItem(SELECT_LEVEL_LABEL, null);

        if (!StringUtils.isBlank(levelNo) && !"0".equals(levelNo)) {
            deductionLevelFilter.add(0, new Object[]{0, SELECT_ALL_LABEL});
            deductionLevelFilter.addAll(
                    commonLogic.getDeductionLevelValues(session.getProjectionId(), levelNo, projectionSelection, generateProductToBeLoaded, generateCustomerToBeLoaded));

            if (CommonUtil.isValueEligibleForLoading() && Constant.TEN_STRING.equals(levelNo)) {
                CommonLogic.loadCustomMenuBarFoScheduleID(deductionLevelFilter, deductionFilterValues);
            } else {
                CommonLogic.loadCustomMenuBar(deductionLevelFilter, deductionFilterValues);
            }
        }

        deductionFilterDdlb.setScrollable(true);
        deductionFilterDdlb.setPageLength(NumericConstants.TEN);
        CommonLogic.loadMenuBar((List) generateDiscountToBeLoaded, deductionFilterValues);
        String deductionMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(deductionFilterValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(deductionFilterDdlb, deductionMenuItemValue);
        deductionFilterDdlb.addSubMenuCloseListener(deductionlistener);
    }

    private void loadCustomerLevel() {
        int hierarchyNo = isInteger(session.getCustomerLevelNumber())
                ? Integer.parseInt(session.getCustomerLevelNumber()) : 0;
        currentHierarchy = CommonLogic.getCustomerHierarchy(session.getProjectionId(), hierarchyNo,
                session.getCustRelationshipBuilderSid(), session.getCustomerRelationVersion());
        Collections.sort(currentHierarchy, new Comparator<Leveldto>() {
            @Override
            public int compare(Leveldto o1, Leveldto o2) {
                return o2.getTreeLevelNo() - o1.getTreeLevelNo();
            }
        });
        Collections.reverse(currentHierarchy);
        Utility.loadDdlbForLevelFilterOption(customerlevelDdlb, currentHierarchy, StringUtils.EMPTY);

        customerlevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                generateCustomerToBeLoaded = Collections.emptyList();
                if (event.getProperty().getValue() != null) {
                    String customerlevelDdlbValue = String.valueOf(customerlevelDdlb.getValue());
                    customerlevelDdlbValue = ANULL.equals(customerlevelDdlbValue) ? StringUtils.EMPTY
                            : customerlevelDdlbValue;
                    loadCustomerLevelFilter(customerlevelDdlbValue);
                } else {
                    loadCustomerLevelFilter(StringUtils.EMPTY);
                }
            }
        });
    }

    private void loadCustomerLevelFilter(String levelNo) {
        List<Object[]> customerLevelFilter = new ArrayList<>();

        customerFilterDdlb.removeSubMenuCloseListener(customerlistener);
        customerFilterDdlb.removeItems();
        customerFilterValues = customerFilterDdlb.addItem(SELECT_LEVEL_LABEL, null);
        if (!levelNo.isEmpty()) {
            customerLevelFilter.add(0, new Object[]{0, SELECT_ALL_LABEL});
            customerLevelFilter.addAll(
                    commonLogic.getCustomerLevelValues(session.getProjectionId(), levelNo, projectionSelection, generateProductToBeLoaded, generateDiscountToBeLoaded, String.valueOf(session.getCustomerRelationVersion())));
            CommonLogic.loadCustomMenuBar(customerLevelFilter, customerFilterValues);
        }
        customerFilterDdlb.setScrollable(true);
        customerFilterDdlb.setPageLength(NumericConstants.TEN);
        CommonLogic.loadMenuBar((List) generateCustomerToBeLoaded, customerFilterValues);
        String customerMenuItemValue = ChangeCustomMenuBarValueUtil.getMenuItemToDisplay(customerFilterValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(customerFilterDdlb, customerMenuItemValue);
        customerFilterDdlb.addSubMenuCloseListener(customerlistener);
    }

    private void loadDeductionInclusion() throws IllegalStateException {
        String[] deductionValues = {"Yes", "No"};
        deductionInclusionDdlb.removeSubMenuCloseListener(deductionInclusionListener);
        deductionInclusionDdlb.removeItems();
        deductionInclusionValues = deductionInclusionDdlb.addItem(SELECT_VALUES, null);
        CustomMenuBar.CustomMenuItem[] deductionInclusionCustomItem = new CustomMenuBar.CustomMenuItem[deductionValues.length];
        for (int i = 0; i < deductionValues.length; i++) {
            MenuItemDTO dto = new MenuItemDTO(i, deductionValues[i].trim());
            deductionInclusionCustomItem[i] = deductionInclusionValues.addItem(dto, null);
            deductionInclusionCustomItem[i].setCheckable(true);
            deductionInclusionCustomItem[i].setItemClickable(true);
            deductionInclusionCustomItem[i].setItemClickNotClosable(true);

        }
        deductionInclusionDdlb.addSubMenuCloseListener(deductionInclusionListener);
        if (!ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || (!ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()) )) {
        deductionInclusionDdlb.addSubMenuCloseListener(deductionInclusionListener);
        deductionInclusionValues.getChildren().get(0).setChecked(true);
         ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(deductionInclusionDdlb, "Yes");
        }
    }

    protected List getCheckedDeductionInclusionValues() {
        List<String> results = new ArrayList<>();
        if (deductionInclusionValues != null && deductionInclusionValues.getSize() > 0) {
            List<CustomMenuBar.CustomMenuItem> items = deductionInclusionValues.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked()) {
                    results.add(customMenuItem1.getMenuItem().getCaption());
                }
            }
        }
        return results;
    }

    private void callAdjustmentProcedure(SessionDTO session) {
        if (session.isActualAdjustment()) {
            discountProjectionLogic.adjustDiscountProjection(session, "Override", AMOUNT, "0", "0", baselinePeriods);
        }
    }
    public static final String AMOUNT = "Amount";

    protected void loadDisplayFormatDdlb() {
        List<Object[]> displayFormatFilter = new ArrayList<>();
        displayFormatDdlb.removeSubMenuCloseListener(displayFormatListener);
        displayFormatFilter.addAll(commonLogic.displayFormatValues());
        displayFormatDdlb.removeItems();
        displayFormatValues = displayFormatDdlb.addItem(SELECT_VALUES, null);
        commonLogic.loadDisplayFormat(displayFormatFilter, displayFormatValues);
        displayFormatDdlb.setScrollable(true);
        String displayFormatMenuItemValue = ChangeCustomMenuBarValueUtil.getInclusionMenuItemToDisplay(displayFormatValues);
        ChangeCustomMenuBarValueUtil.setMenuItemToDisplay(displayFormatDdlb, displayFormatMenuItemValue);
        displayFormatDdlb.addSubMenuCloseListener(displayFormatListener);
    }

    protected DataFormatConverter getConverter(String indicator) {
        if (!CommonUtil.isValueEligibleForLoading()) {
            return priceFormat;
        }
        if (indicator.contains("RPU")) {
            return priceFormat;
        }
        return (stringNullCheck(projectionSelection.getConversionFactor())
                || StringUtils.isBlank(String.valueOf(projectionSelection.getConversionFactor()))
                || Constant.CONVERSION_FACTOR_DEFALUT_VALUE.equals(String.valueOf(projectionSelection.getConversionFactor()))) ? priceConversionFormat : priceConversionTwoDecimalFormat;
    }

    public void loadMethodologyDdlb(final ComboBox methodology) {
        String query;
        List<String> returnList = new ArrayList<>();
        Map<String, List<String>> input = new HashMap<>();
        List<String> defaultNames = Arrays.asList("1.Contract Details", "2.Single Period", "3.Average", "4.Rolling Annual Trend");
        List<String> exfactNames = Arrays.asList("5.% of Ex-Factory Sales", "9.% OF Ex-Factory - Seasonal Trend");
        List<String> demandNames = Arrays.asList("6.% of Demand");
        List<String> adjDemandNames = Arrays.asList("8.% of Adjusted Demand");
        List<String> inventoryNames = Arrays.asList("7.% of Inventory Withdrawal");
        List<String> outputList = new ArrayList<>();
        input.put("Ex-Factory Sales", exfactNames);
        input.put("Demand", demandNames);
        input.put("Inventory Withdrawal - Forecast Detail", inventoryNames);
        input.put("Inventory Withdrawal - Forecast Summary", inventoryNames);
        input.put("Adjusted Demand", adjDemandNames);
        query = SQlUtil.getQuery("get-file-type-query");
        returnList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        outputList.addAll(defaultNames);
        for (String string : returnList) {
            if (!"Customer Sales".equals(string) && !"Adjusted Demand".equals(string)) {
                outputList.addAll(input.get(string));
            }
        }
        Collections.sort(outputList);
        for (String str : outputList) {
            methodology.addItem(str.split("\\.")[1]);
        }
    }

    private void securityForListView(Object[] visibleColumnArray, String[] columnHeaderArray, ExtCustomTreeTable table) {
        try {
            final String userId = String.valueOf(session.getUserId());
            final Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Forecasting", "Commercial", "Discount Projection", session.getAction());
            List<List> headeInformationsList = CommonLogic.isPropertyVisibleAccess(visibleColumnArray, columnHeaderArray, functionHM);
            List<String> headerArray = headeInformationsList.get(1);
            table.setVisibleColumns(headeInformationsList.get(0).toArray());
            table.setColumnHeaders(headerArray.toArray(new String[headerArray.size()]));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public boolean isListviewGenerated() {
        return isListviewGenerated;
    }

    public void setListviewGenerated(boolean isListviewGenerated) {
        this.isListviewGenerated = isListviewGenerated;
    }

    private void getExcelDiscountCommercial() {
        try {
            String tempKey;
            String parentKey;
            boolean isCustomHierarchy = CommonUtil.isValueEligibleForLoading()
                    ? Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(hierarchyIndicator)
                    : Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant()
                            .equals(hierarchyIndicator);
            projectionSelection.setIsCustomHierarchy(isCustomHierarchy);
            List<Object[]> discountExcelList = getDiscountProjectionExcelResults();
            NMDiscountExcelLogic nmDiscountExcelLogic = new NMDiscountExcelLogic();
            List doubleProjectedAndHistoryCombinedUniqueList = discountProjectionLogic.getDoubleProjectedAndHistoryCombinedUniqueList(rightHeader);
            if(!isCustomHierarchy){
            nmDiscountExcelLogic.getCustomizedExcelData(discountExcelList, projectionSelection, doubleProjectedAndHistoryCombinedUniqueList);
            }else{
            nmDiscountExcelLogic.getCustomizedExcelDataCustom(discountExcelList, projectionSelection, doubleProjectedAndHistoryCombinedUniqueList);
            }
            DiscountProjectionDTO itemId;
            for (Iterator<String> it = nmDiscountExcelLogic.getHierarchyKeys().listIterator(); it.hasNext();) {
                String key = it.next();
                it.remove();
                itemId = getItemId(nmDiscountExcelLogic.getResultMap(), key);
                excelContainer.addBean(itemId);
                Object parentItemId;
                key = key.contains("$") ? key.substring(0, key.indexOf('$')) : key;
                tempKey = key.trim();
                parentKey = CommonUtil.getParentItemId(key, isCustomHierarchy, itemId.getParentHierarchyNo());
                parentItemId = excelParentRecords.get(parentKey);
                if (parentItemId != null) {
                    excelContainer.setParent(itemId, parentItemId);
                }
                parentItemId = itemId;
                excelParentRecords.put(tempKey, itemId);
                excelContainer.setChildrenAllowed(itemId, true);

            }

            excelContainer.sort(new Object[]{LEVEL_NAME_PROPERTY}, new boolean[]{true});
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.info(e.getMessage(),e);
        }
    }

    private DiscountProjectionDTO getItemId(Map<String, DiscountProjectionDTO> map, String key) {
        DiscountProjectionDTO itemId = new DiscountProjectionDTO();
        if (map.containsKey(key)) {
            itemId = map.get(key);
            map.remove(key);
        }
        return itemId;
    }

    private List<Object[]> getDiscountExcelResults(ProjectionSelectionDTO projectionSelectionDTO) {
        String sIds = projectionSelectionDTO.getDeductionLevelFilter().isEmpty() ? null : PVCommonLogic.removeBracesInList(projectionSelectionDTO.getDeductionLevelFilter());
        int customMasterSid = Integer.parseInt(viewDdlb.getValue() == null ? "0" : viewDdlb.getValue().toString());
        Object[] orderedArg = {projectionSelectionDTO.getProjectionId(), projectionSelectionDTO.getUserId(), projectionSelectionDTO.getSessionDTO().getSessionId(), deductionlevelDdlb.getItemCaption(deductionlevelDdlb.getValue()),
            projectionSelectionDTO.getFrequency().substring(0, 1), projectionSelectionDTO.isIsCustomHierarchy() ? "D" : projectionSelectionDTO.getHierarchyIndicator(),
            "Sales", "0", projectionSelectionDTO.getHierarchyNo(),
            projectionSelectionDTO.getLevelNo(), "DETAIL_DISCOUNT", customMasterSid, "Period", projectionSelectionDTO.getUomCode(), "ALL".equals(projectionSelectionDTO.getSessionDTO().getSalesInclusion()) ? null : projectionSelectionDTO.getSessionDTO().getSalesInclusion(), "ALL".equals(projectionSelectionDTO.getSessionDTO().getDeductionInclusion()) ? null : projectionSelectionDTO.getSessionDTO().getDeductionInclusion(), sIds, DISCOUNT};
        return CommonLogic.callProcedure("PRC_PROJECTION_VARIANCE", orderedArg);
    }

    @Override
    protected void excelExportBtnClickLogic() {
        LOGGER.debug("excel starts");
        try {
            excelTable.setRefresh(BooleanConstant.getFalseFlag());
            excelContainer = new ExtTreeContainer<>(DiscountProjectionDTO.class, ExtContainer.DataStructureMode.MAP);
            excelContainer.setColumnProperties(excelHeaderLeft.getProperties());
            excelContainer.setColumnProperties(rightHeader.getProperties());
            excelTable.setContainerDataSource(excelContainer);
            ExtFilterTreeTable leftTable = resultsTable.getLeftFreezeAsTable();
            Object[] leftTableVisibleColumn = leftTable.getVisibleColumns();
            String[] leftTableColumnHeader = leftTable.getColumnHeaders();

            Object[] objectArray = ArrayUtils.addAll(
                    Arrays.copyOfRange(leftTableColumnHeader, 1, leftTableColumnHeader.length),
                    excelHeader.getSingleHeaders().toArray(new String[0]));

            Object[] leftTableExcelColumn = ArrayUtils.addAll(
                    Arrays.copyOfRange(leftTableVisibleColumn, 1, leftTableVisibleColumn.length),
                    excelHeader.getSingleColumns().toArray());

            securityForListView(leftTableExcelColumn, Arrays.copyOf(objectArray, objectArray.length, String[].class), excelTable);

            excelTable.setDoubleHeaderVisible(true);
            excelTable
                    .setDoubleHeaderVisibleColumns(ArrayUtils.addAll(
                            Arrays.copyOfRange(leftHeader.getDoubleColumns().toArray(), 0,
                                    leftHeader.getDoubleColumns().toArray().length),
                            excelHeader.getDoubleColumns().toArray()));
            Object[] objectArrayDouble = ArrayUtils.addAll(
                    Arrays.copyOfRange(leftHeader.getDoubleHeaders().toArray(), 0,
                            leftHeader.getDoubleHeaders().toArray().length),
                    excelHeader.getDoubleHeaders().toArray(new String[excelHeader.getDoubleHeaders().size()]));
            excelTable.setDoubleHeaderColumnHeaders(
                    Arrays.copyOf(objectArrayDouble, objectArrayDouble.length, String[].class));
            Map<Object, Object[]> mapVisibleCols = new HashMap();
            mapVisibleCols.putAll(excelHeaderLeft.getDoubleHeaderMaps());
            mapVisibleCols.putAll(excelHeader.getDoubleHeaderMaps());
            excelTable.setDoubleHeaderMap(mapVisibleCols);
             boolean isRate = false;
             boolean isRPU = false;
             for (Object propertyId : excelTable.getContainerPropertyIds()) {
                if (String.valueOf(propertyId).endsWith("Rate")) {
                    isRate = true;
                    excelTable.setConverter(propertyId, percentFormat);
                }else if (String.valueOf(propertyId).endsWith("RPU")) {
                    isRPU = true;
                }
            }
            getExcelDiscountCommercial();
            Map<String, String> formatter = new HashMap<>();
            formatter.put("percentThreeDecimal", "Rate");
            formatter.put("currencyTwoDecimal", "RPU");
            formatter.put(AMOUNT_TWO_DECIMAL, AMOUNT);
            formatter.put(AMOUNT_TWO_DECIMAL, AMOUNT);
            excelTable.setRefresh(BooleanConstant.getTrueFlag());
            ForecastUI.setEXCEL_CLOSE(true);
            CustomExcelNM excel = null;
            HeaderUtils.getDiscountProjectionRightTableColumns(projectionSelection);
            if (QUARTERLY.getConstant().equals(String.valueOf(frequencyDdlb.getValue()))
                    || MONTHLY.getConstant().equals(String.valueOf(frequencyDdlb.getValue()))) {

                Map<Integer, List> headerMapBasedonYear = discountProjectionLogic
                        .configureVisibleColumnMapsForExcel(projectionSelection.getHeaderMapForExcel());
                int exportAt = headerMapBasedonYear.size() - 1;
                List<Integer> list = new ArrayList(headerMapBasedonYear.keySet());
                Collections.sort(list);
                for (int i = 0; i < list.size(); i++) {

                    excelTable.setVisibleColumns(ArrayUtils.addAll(
                            Arrays.copyOfRange(leftTableVisibleColumn, 1, leftTableVisibleColumn.length),
                            ((List<Object>) headerMapBasedonYear.get(list.get(i)).get(0)).toArray()));
                    Object[] header = ArrayUtils.addAll(
                            Arrays.copyOfRange(leftTableColumnHeader, 1, leftTableColumnHeader.length),
                            ((List<Object>) headerMapBasedonYear.get(list.get(i)).get(1)).toArray());
                    excelTable.setColumnHeaders(Arrays.copyOf(header, header.length, String[].class));
                    excelTable.setDoubleHeaderVisibleColumns(ArrayUtils.addAll(
                            Arrays.copyOfRange(leftHeader.getDoubleColumns().toArray(), 0,
                                    leftHeader.getDoubleColumns().toArray().length),
                            ((List<Object>) headerMapBasedonYear.get(list.get(i)).get(NumericConstants.THREE))
                                    .toArray()));
                    Object[] doubleHeader = ArrayUtils.addAll(
                            Arrays.copyOfRange(leftHeader.getDoubleHeaders().toArray(), 0,
                                    leftHeader.getDoubleHeaders().toArray().length),
                            ((List<Object>) headerMapBasedonYear.get(list.get(i)).get(NumericConstants.FOUR))
                                    .toArray());
                    excelTable.setDoubleHeaderColumnHeaders(
                            Arrays.copyOf(doubleHeader, doubleHeader.length, String[].class));
                    mapVisibleCols = new HashMap();
                    mapVisibleCols.putAll(excelHeaderLeft.getDoubleHeaderMaps());
                    mapVisibleCols.putAll(
                            (Map<Object, Object[]>) headerMapBasedonYear.get(list.get(i)).get(NumericConstants.FIVE));
                    excelTable.setDoubleHeaderMap(mapVisibleCols);
                    excelTable.setRefresh(true);
                    String sheetName = "Year " + list.get(i);
                    ForecastUI.setEXCEL_CLOSE(true);
                    if (i == 0) {
                        excel = new CustomExcelNM(new ExtCustomTableHolder(excelTable), sheetName,
                                Constant.DISCOUNT_PROJECTION_LABEL, DISCOUNT_PROJECTION_XLS, false, formatter,isRate,isRPU,projectionSelection.isIsCustomHierarchy());
                    } else {
                        excel.setNextTableHolder(new ExtCustomTableHolder(excelTable), sheetName);
                    }
                    if (i == exportAt) {
                        excel.exportMultipleTabs(true);
                    } else {
                        excel.exportMultipleTabs(false);
                    }
                }
            } else {
                excel = new CustomExcelNM(new ExtCustomTableHolder(excelTable), Constant.DISCOUNT_PROJECTION_LABEL,
                        Constant.DISCOUNT_PROJECTION_LABEL, DISCOUNT_PROJECTION_XLS, false, formatter,isRate,isRPU,projectionSelection.isIsCustomHierarchy());
                excel.export();
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("excel ends");
    }

    private Object[] getFinalLeftHeader(Map<Object,Object[]> doubleHeader) {
        if (this.tempSingleHeaderArray == null) {
            this.tempSingleHeaderArray = doubleHeader.get(GROUP_PROPERTY_ID);
        }
        return this.tempSingleHeaderArray;
    }
    
    public List getDiscountProjectionExcelResults() {
        if (!projectionSelection.isIsCustomHierarchy()) {
            String queryBuilder = StringUtils.EMPTY;
            String oppositeDed = session.getDeductionInclusion().equals("1") ? "0" : "1";
            String deducQuery = NINE_LEVELS_DED;
            String viewTableJoin = "P".equals(hierarchyIndicator) ? "ST_PRODUCT_DISCOUNT" : "ST_CUSTOMER_DISCOUNT";
            queryBuilder = SQlUtil.getQuery("discount-customerproduct-excelQuery");
            queryBuilder = queryBuilder.replace("@CUSTORPROD", "P".equals(hierarchyIndicator) ? "PROD_HIERARCHY_NO" : "CUST_HIERARCHY_NO")
                    .replace("@VIEWTABLE", "P".equals(hierarchyIndicator) ? "ST_PRODUCT_DISCOUNT" : "ST_CUSTOMER_DISCOUNT")
                    .replace("@DEDINCLUSION",getValue(session.getDeductionInclusion()," and STC.DEDUCTION_INCLUSION= " + session.getDeductionInclusion()))
                    .replace("@UNIONALL", getValue(session.getDeductionInclusion(), deducQuery + viewTableJoin +" STC INNER JOIN #DISCOUNT_PROJECTION_MASTER SH ON STC.HIERARCHY_NO = SH.HIERARCHY_NO AND STC.DEDUCTION_INCLUSION = SH.DEDUCTION_INCLUSION @ENDDEDINCLUSION "))
                    .replace("@ENDDEDINCLUSION", getValue(session.getDeductionInclusion()," WHERE STC.DEDUCTION_INCLUSION= " + oppositeDed))
                    .replace("@DEDUCTIONLEVEL", (deductionlevelDdlb.getValue() == null || "ALL".equals(deductionlevelDdlb.getItemCaption(deductionlevelDdlb.getValue())) ? StringUtils.EMPTY : deductionlevelDdlb.getItemCaption(deductionlevelDdlb.getValue())));
            queryBuilder = QueryUtil.replaceTableNames(queryBuilder, session.getCurrentTableNames());
            return HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder);
        } else {
            return DiscountQueryBuilder.getDiscountProjectionCustomExcel(session,PROGRAM.getConstant().equals(level.getValue()),projectionSelection);
        }
    }
   
    private String getValue(String value, String defaultValue) {
        return (value == null || "ALL".equals(value)) ? StringUtils.EMPTY : defaultValue;
    }
}
