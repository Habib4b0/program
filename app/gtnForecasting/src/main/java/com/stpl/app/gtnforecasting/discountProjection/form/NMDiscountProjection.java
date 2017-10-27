package com.stpl.app.gtnforecasting.discountProjection.form;

import com.stpl.app.gtnforecasting.abstractforecast.ForecastDiscountProjection;
import com.stpl.app.gtnforecasting.discountProjection.logic.NMDiscountProjectionLogic;
import com.stpl.app.gtnforecasting.discountProjection.logic.tableLogic.NMDiscountTableLoadLogic;
import com.stpl.app.gtnforecasting.dto.DiscountProjectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SaveDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DiscountProjectionLogic;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.projectionvariance.logic.NMProjectionVarianceLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.ui.form.lookups.AlternateHistory;
import com.stpl.app.gtnforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.gtnforecasting.ui.form.lookups.DiscountSelection;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.CustomExcelNM;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import com.stpl.app.gtnforecasting.utils.TabNameUtil;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
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
import static com.stpl.app.utils.Constants.CommonConstants.PER_INVENTORY_WITHDRAW;
import static com.stpl.app.utils.Constants.CommonConstants.PER_OF_DEMAND;
import static com.stpl.app.utils.Constants.CommonConstants.ROLLING_ANNUAL_TREND;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.CommonConstants.SINGLE_PERIOD;
import static com.stpl.app.utils.Constants.CommonConstantsForChannels.DISABLE;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.AMOUNT;
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
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.QueryUtil;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTable.ColumnCheckListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox.ClickListener;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.extfilteringtable.ExtPagedTreeTable;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.ParseException;
import java.util.Comparator;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author shyam.d
 */
public class NMDiscountProjection extends ForecastDiscountProjection {

    final StplSecurity stplSecurity = new StplSecurity();
    ExtTreeContainer<DiscountProjectionDTO> resultBeanContainer = new ExtTreeContainer<>(DiscountProjectionDTO.class,ExtContainer.DataStructureMode.MAP);
    GtnSmallHashMap manualEntryMap = new GtnSmallHashMap();
    /* Discount Bean */
    protected BeanItemContainer<String> programBean = new BeanItemContainer<>(String.class);
    /* To store the current hierarchy */
    List<Leveldto> currentHierarchy = new ArrayList<>();
    /* To enable or disable level filter listener */
    boolean enableLevelFilterListener = true;
    /* The bean used to load the Mass Update - value Ddlb */
    BeanItemContainer<String> valueDdlbBean = new BeanItemContainer<>(String.class);
    /* To hold the selected discounts in program selection lookup */
    List<String> discountProgramsList = new LinkedList<>();
    List<String> discountProgramsNamesList = new LinkedList<>();
    /* To hold the selected program from the program selection combo box */
    List<String> programSelectionList = new ArrayList<>();
    /* The hierarchy indicator to indicate whether customer or Product */
    String hierarchyIndicator = StringUtils.EMPTY;
    /*  Non Mandated Logic */
    private final DiscountProjectionLogic logic = new DiscountProjectionLogic();
    /* table Logic to load the table Data */
    NMDiscountTableLoadLogic tableLogic;
    /* To hold the selections on generate button click. */
    ProjectionSelectionDTO projectionSelection = new ProjectionSelectionDTO();
    /* The custom id. */
    int customId = 0;
    /* To check whether list view is generated or not */
    public boolean isListviewGenerated = Boolean.TRUE;
    boolean isGroupUpdatedManually = false;
    /* The custom id to select. */
    int customIdToSelect = 0;
    /* The Right Header Dto */
    CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    /* The Right Header Dto */
    CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    /* The Excel header Dto (Right Header) */
    CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO excelHeaderLeft = new CustomTableHeaderDTO();
    /* The Results table */
    FreezePagedTreeTable resultsTable;
    /* Group filter selected value */
    private String userGroup = StringUtils.EMPTY;
    /* String to be stored during focus of List View text field */
    String focusValue = StringUtils.EMPTY;
    /* String to be stored during blur of List View text field */
    String blurValue = StringUtils.EMPTY;
    /* Discount Names to be used in Projection results */
    List<String> discountNamesList = new ArrayList<>();
    /* Discount No to be used in Projection results */
    List<String> discountNoList = new ArrayList<>();

    /* Start and End Periods to be loaded */
    List<Integer> startAndEndPeriods = new ArrayList<>();
    /* Data Format Converter */
    DataFormatConverter percentFormat = new DataFormatConverter("#,##0.000", DataFormatConverter.INDICATOR_PERCENT);
    DataFormatConverter priceFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_DOLLAR);
    /**
     * To store the details of the checked double header with its corresponding
     * Triple header and History, Projected values
     */
    Map<String, Map<String, List<String>>> tripleHeaderForCheckedDoubleHeader = new HashMap<>();
    /* To store the name of the discounts selected in the Triple header */
    List<Object> checkedDiscountsPropertyIds = new ArrayList<>();
    /* List to have the items to be saved */
    List<SaveDTO> saveList = new ArrayList<>();
    /* To store the hierarchy numbers to refresh in table */
    Set<String> refreshTableHierarchySet = new HashSet<>();
    /* To store the custom View */
    List<CustomViewMaster> customViewList = new ArrayList<>();
    /* The Excel container */
    ExtTreeContainer<DiscountProjectionDTO> excelContainer = new ExtTreeContainer<>(DiscountProjectionDTO.class,ExtContainer.DataStructureMode.MAP);
    boolean errorFlag = false;

    @UiField("endPeriodForecastTab")
    protected ComboBox endPeriodForecastTab;
    /**
     * The startPeriodForecastTab ComboBox.
     */
    @UiField("startPeriodForecastTab")
    protected ComboBox startPeriodForecastTab;

    @UiField("calculateBtn")
    public Button calculateBtn;

    @UiField("generateBtn")
    public Button generateBtn;

    @UiField("adjustBtn")
    public Button adjustBtn;

    @UiField("gridlay")
    public GridLayout gridlay;
    Set<String> tableHirarechyNos = new HashSet<>();
    DataFormatConverter dollarFormat = new DataFormatConverter("###,###,##0", DataFormatConverter.INDICATOR_DOLLAR);
    List<String> checkedList;

    String calcBase = StringUtils.EMPTY;
    private GtnSmallHashMap radioMap = new GtnSmallHashMap();
    private GtnSmallHashMap checkBoxMap = new GtnSmallHashMap();
    private GtnSmallHashMap ccpsCountForMassUpdate = new GtnSmallHashMap();
    private int baselineFlag = 0;
    private int ccpsCount = 0;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(NMDiscountProjection.class);

    private boolean isDiscountGenerated;
    boolean isRateUpdatedManually = false;
    boolean isRPUUpdatedManually = false;
    boolean isAmountUpdatedManually = false;
    private boolean isGrowthUpdatedManually = false;
    BeanItemContainer<String> tableGroupDdlbBean = new BeanItemContainer<>(String.class);
    List<String> aHselectedDiscounts = new ArrayList<>();
    String actualCCPs = StringUtils.EMPTY;
    private int rsModelSid = 0;
    int totalccpCount = 0;
    boolean flag = false;
    CommonLogic commonLogic = new CommonLogic();

    public NMDiscountProjection(SessionDTO session, String screenName)  {
        super(session, screenName);
        tableLogic = new NMDiscountTableLoadLogic(this);
        tableLogic.setTempPageLength(NumericConstants.TEN);
        tableLogic.setDataLoad(false);
        resultsTable = new FreezePagedTreeTable(tableLogic);
        tableLogic.setPageLength(NumericConstants.TWENTY); // To be done before creating table       
        tableLogic.sinkItemPerPageWithPageLength(false);
        if (flag) {
            configure();
        }
        flag = true;
        securityForButton();
        addPropertyValueChangeListeners(frequencyDdlb, viewDdlb, view, adjprograms, adjperiods, massCheck, startPeriod, levelFilterDdlb);
    }

    /**
     * To get the content of this tab from Non Mandated form
     *
     * @return
     */
    public void getContent() {
        LOGGER.debug("Inside getContent " + session.getAction());
        configureFeildsForNm();
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
        gridlay.addComponent(history, 0, 1, 0, 1);
        gridlay.addComponent(historyDdlb, 1, 1, 1, 1);
        gridlay.addComponent(variablesLb, NumericConstants.TWO, 1, NumericConstants.TWO, 1);
        gridlay.addComponent(variables, NumericConstants.THREE, 1, NumericConstants.FIVE, 1);
        fieldDdlb.addItem(SELECT_ONE.getConstant());
        fieldDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        fieldDdlb.select(SELECT_ONE.getConstant());
        fieldDdlb.addItem(Constant.GROUPFCAPS);
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

        // The following should be changed in DB procedure if changed below
        allocMethodology.addItem(Constant.HISTORICAL_OF_BUSINESS);
        allocMethodology.addItem("Forecast % of Business");

        valueDdlb.setEnabled(true);
        valueDdlb.addItem(SELECT_ONE.getConstant());
        valueDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        valueDdlb.setNewItemsAllowed(true);
        valueDdlb.setNullSelectionAllowed(false);
        valueDdlb.setContainerDataSource(valueDdlbBean);

        value.addStyleName("txtRightAlign");

        methodologyDdlb.addItem(SELECT_ONE.getConstant());
        methodologyDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        methodologyDdlb.addItems(CONTRACT_DETAILS.getConstant(),
                SINGLE_PERIOD.getConstant(),
                AVERAGE.getConstant(),
                ROLLING_ANNUAL_TREND.getConstant(),
                PER_EX_FACTORY_SALES.getConstant(),
                PER_OF_DEMAND.getConstant(),
                PER_INVENTORY_WITHDRAW.getConstant(), Constant.PERC_OF_ADJUSTED_DEMAND);
        if (CommonUtil.isValueEligibleForLoading()) {
            methodologyDdlb.addItem(Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND);
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
        variables.addStyleName("optiongroupextrawidth");
        variables.addItem(DISCOUNT_RATE.getConstant());
        variables.addItem(REBATE_PER_UNIT.getConstant());
        variables.addItem(DISCOUNT_AMT.getConstant());
        variables.addItem(GROWTH.getConstant());
        variables.focus();
        variables.setImmediate(true);
        variables.select(DISCOUNT_RATE.getConstant());
        variables.select(REBATE_PER_UNIT.getConstant());
        variables.select(DISCOUNT_AMT.getConstant());
        projectionSelection.setdPVariablesList(Arrays.asList(new String[]{DISCOUNT_RATE.getConstant(), REBATE_PER_UNIT.getConstant(), DISCOUNT_AMT.getConstant()}));
        newBtn.setEnabled(true);

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
        LOGGER.debug(" session Action " + session.getAction());

        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
            if (loadSelections() && !discountProgramsList.isEmpty()) {
                generateBtnClickLogic(false);
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

                            String selectedRsName = resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeader(checkedDiscountsPropertyIds.get(0));
                            selectedRsName = selectedRsName.replace(" ", StringUtils.EMPTY);
                            session.setForecastName(TabNameUtil.DISCOUNT_PROJECTION);
                            AlternateHistory alternateContractLookup = new AlternateHistory(session, projectionSelection, tableLogic, actualCCPs, rsModelSid, selectedRsName);
                            getUI().addWindow(alternateContractLookup);
                        } else {
                        }
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                } else {

                    NotificationUtils.getAlertNotification("Invalid Levl Selection", "Please Select Level as Program and Generate to proceed Alternate History.");

                }
            }

        });

        LOGGER.debug("Exiting configureFields ");

    }
    ColumnCheckListener checkListener = new ColumnCheckListener() {
        @Override
        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
            tableLogic.setCheckAll(event.isChecked());
            checkClearAll(event.isChecked());
        }
    };

    
    FocusListener focusListener = new FocusListener() {
        @Override
        public void focus(FieldEvents.FocusEvent event) {
            Object[] obj = (Object[])((AbstractComponent)event.getComponent()).getData();
            if("left".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))) {
                focusValue = String.valueOf(tableLogic.getContainerDataSource().getContainerProperty(obj[0], obj[1]).getValue()).trim();
            } else {
                focusValue = String.valueOf(tableLogic.getContainerDataSource().getContainerProperty(obj[0], obj[1]).getValue()).replace(" ", StringUtils.EMPTY);
            }
            LOGGER.debug(" group focus Value" + focusValue);
        }
    };
    
    BlurListener blurListener = new BlurListener() {
        @Override
        public void blur(FieldEvents.BlurEvent event) {
            Object[] obj = (Object[])((AbstractComponent)event.getComponent()).getData();
            if ("left".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))) {
                blurValue = String.valueOf(tableLogic.getContainerDataSource().getContainerProperty(obj[0], obj[1]).getValue()).trim();
                LOGGER.debug(" blur Value" + blurValue);
                if (!focusValue.equals(blurValue)) {
                    DiscountProjectionDTO dto = (DiscountProjectionDTO) obj[0];
                    dto.addStringProperties(obj[1], blurValue);
                    LOGGER.debug(" hierarchyNo " + dto.getHierarchyNo());
                    String relationshipBuilderSid = getRelationshipBuilderSid();
                    boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
                    boolean isCustomHierarchy = Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equals(hierarchyIndicator);
                    List<String> customViewDetails = new ArrayList<>();
                    if (isCustomHierarchy) {
                        String customerLevelNo;
                        String productLevelNo;
                        String customerHierarchyNo;
                        String productHierachyNo;

                        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                            customerHierarchyNo = dto.getHierarchyNo();
                            productHierachyNo = dto.getProductHierarchyNo();
                        } else {
                            customerHierarchyNo = dto.getCustomerHierarchyNo();
                            productHierachyNo = dto.getHierarchyNo();
                        }

                        productLevelNo = Constant.PERCENT;
                        customerLevelNo = Constant.PERCENT;
                        LOGGER.debug(" EXCEL - Custom hierarchy --- \n customId " + customId);
                        LOGGER.debug(" EXCEL - customerLevelNo " + customerLevelNo);
                        LOGGER.debug(" EXCEL - customerHierarchyNo " + customerHierarchyNo);
                        LOGGER.debug(" EXCEL - productLevelNo " + productLevelNo);
                        LOGGER.debug(" EXCEL - productHierarchyNo " + productHierachyNo);
                        customViewDetails.add(StringUtils.EMPTY + customId);
                        customViewDetails.add(customerLevelNo);
                        customViewDetails.add(customerHierarchyNo);
                        customViewDetails.add(productLevelNo);
                        customViewDetails.add(productHierachyNo);
                        customViewDetails.add(session.getCustRelationshipBuilderSid());
                        customViewDetails.add(session.getProdRelationshipBuilderSid());
                    }
                    if (logic.saveGroupValues(session, dto.getHierarchyNo(), blurValue, isProgram, projectionSelection.getDiscountProgramsList())) {
                        if (!userGroup.isEmpty()) {
                            isGroupUpdatedManually = true;
                        }
                        LOGGER.debug(blurValue + " saved ");
                    }
                    groupFilterDdlb.removeValueChangeListener(groupFilterDdlbValueChange);
                    groupFilterDdlb.addItem(blurValue);
                    valueDdlbBean.addItem(blurValue);
                    tableGroupDdlbBean.addItem(blurValue);
                    groupFilterDdlb.addValueChangeListener(groupFilterDdlbValueChange);
                }
            } else {
                blurValue = String.valueOf(tableLogic.getContainerDataSource().getContainerProperty(obj[0], obj[1]).getValue()).replace(" ", StringUtils.EMPTY);
                if (blurValue.isEmpty()) {
                    blurValue = Constant.DASH;
                }
                LOGGER.debug(" blur Value" + blurValue);
                if (!focusValue.equals(blurValue)) {
                    final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
                    DiscountProjectionDTO dto = (DiscountProjectionDTO) obj[0];
                    dto.addStringProperties(obj[1], blurValue);
                    SaveDTO saveDto = new SaveDTO();
                    String discountName = rightTable.getTripleHeaderColumnHeader(rightTable.getTripleHeaderForSingleHeader(String.valueOf(obj[1])));
                    String periodToUpdate = rightTable.getDoubleHeaderColumnHeader(rightTable.getDoubleHeaderForSingleHeader(String.valueOf(obj[1])));
                    String period = periodToUpdate;
                    String yearToUpdate = periodToUpdate.substring(periodToUpdate.length() - NumericConstants.FOUR);
                    periodToUpdate = periodToUpdate.replace(yearToUpdate, StringUtils.EMPTY);
                    periodToUpdate = CommonUtils.replaceIntegerForMonth(periodToUpdate);
                    periodToUpdate = periodToUpdate.replace(Constant.Q, StringUtils.EMPTY).replace(Constant.S, StringUtils.EMPTY).replace(" ", StringUtils.EMPTY);
                    String refreshName = StringUtils.EMPTY;
                    String property = String.valueOf(obj[1]);
                    int frequencyDiv = getFrequencyDivision(projectionSelection.getFrequency());
                    if (property.contains(Constant.PROJECTED_RATE)) {
                        refreshName = Constant.RATE;
                        isRateUpdatedManually = true;
                    } else if (property.contains(Constant.PROJECTEDRPU)) {
                        refreshName = "RPU";
                        isRPUUpdatedManually = true;
                    } else if (property.contains("ProjectedAmount")) {
                        refreshName = "AMOUNT";
                        isAmountUpdatedManually = true;
                        double doubleVal = Double.valueOf(blurValue);
                        ccpsCount = dto.getCcpCount();
                       double finalValue = doubleVal / ((dto.getCcpCountForDiscount().get(discountName) != null ? dto.getCcpCountForDiscount().getInt(discountName):ccpsCount) * frequencyDiv);
                       blurValue = String.valueOf(finalValue);
                       if(blurValue.contains("E")){
                       blurValue = blurValue.substring(0, blurValue.lastIndexOf("E"));
                       }
                    } else if (property.contains(Constant.GROWTH)) {
                        refreshName = "GROWTH";
                        isGrowthUpdatedManually = true;
                    }
                    saveDto.setPeriodNo(isInteger(periodToUpdate) ? Integer.valueOf(periodToUpdate) : 0);
                    saveDto.setYear(Integer.valueOf(yearToUpdate));
                    saveDto.setValue(blurValue);
                    saveDto.setHirarechyNo(dto.getHierarchyNo());
                    saveDto.setTreeLevelNo(dto.getTreeLevelNo());
                    saveDto.setCustomerHierarchyNo(dto.getCustomerHierarchyNo());
                    saveDto.setProductHierarchyNo(dto.getProductHierarchyNo());
                    saveDto.setHierarchyIndicator(dto.getHierarchyIndicator());
                    boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
                    saveDto.setDiscountName(isProgram ? rightTable.getTripleHeaderForSingleHeader(String.valueOf(obj[1])) : discountName);
                    saveDto.setRefreshName(refreshName);
                    refreshTableHierarchySet.add(tableLogic.getTreeLevelonCurrentPage(obj[0]));
                    saveList.add(saveDto);
 
                    try {
                        multiVariablesCheck(period, refreshName);
                        if (saveList.size() == 1) {
                            saveDiscountProjectionListview();
                        }
                    } catch (Exception e) {
                        LOGGER.error(e);
                        AbstractNotificationUtils.getErrorNotification("Multiple Variables Updated", "Multiple variables for the same customer/product/time period combination have been changed.  Please only change one variable for a single customer/product/time period combination.");
                        tableLogic.getContainerDataSource().getContainerProperty(obj[0], obj[1]).setValue(focusValue);
                    }
                      

                    LOGGER.debug(" periodToUpdate " + periodToUpdate);
                    LOGGER.debug(" yearToUpdate " + yearToUpdate);
                    LOGGER.debug(" hierarchyIndicator " + hierarchyIndicator);
                    LOGGER.debug(" hierarchyNo " + dto.getHierarchyNo());
                    LOGGER.debug(" discountName " + saveDto.getDiscountName());
                    LOGGER.debug(" discountRateValue " + saveDto.getValue());
                    LOGGER.debug(" TreeLevelNo " + saveDto.getTreeLevelNo());
                    LOGGER.debug(" CustomerHierarchyNo " + saveDto.getCustomerHierarchyNo());
                    LOGGER.debug(" ProductHierarchyNo " + saveDto.getProductHierarchyNo());
                }
            }
        }
    };
    
    ClickListener clickListener = new ClickListener(){
        @Override
        public void click(ExtCustomCheckBox.ClickEvent event) {
            Object[] obj = (Object[])((AbstractComponent)event.getComponent()).getData();
            final String tableHierarchyNo = tableLogic.getTreeLevelonCurrentPage(obj[0]);
            DiscountProjectionDTO dto = (DiscountProjectionDTO) obj[0];
            Boolean checkValue = ((ExtCustomCheckBox)((AbstractComponent)event.getComponent())).getValue();
            if (isGroupUpdatedManually) {
                NotificationUtils.getAlertNotification(Constant.GROUP_FILTER_CONFLICT, "The group value entered conflicted with the group filter. Kindly Click Refresh button before proceeding this operation");
                tableLogic.getContainerDataSource().getContainerProperty(obj[0], Constant.CHECKRECORD).setValue(false);
                return;
            }
            dto.addBooleanProperties(obj[1], checkValue);
            int updatedRecordsNo = updateCheckedRecord(dto) * CommonUtils.getFrequencyNumber(projectionSelection.getFrequency());
            resultsTable.getLeftFreezeAsTable().setRefresh(false);
            updateCheckForParentLevels(obj[0], updatedRecordsNo, checkValue);
            updateCheckForChildLevels(tableHierarchyNo, obj[0], checkValue);
            if (!checkValue) {
                ExtPagedTreeTable leftTable = resultsTable.getLeftFreezeAsTable();
                leftTable.removeColumnCheckListener(checkListener);
                leftTable.setColumnCheckBox(Constant.CHECKRECORD, true, false);
                tableLogic.setCheckAll(false);
                leftTable.addColumnCheckListener(checkListener);
            }else {
               ccpsCountForMassUpdate=dto.getCcpCountForDiscount();
            }
            resultsTable.getLeftFreezeAsTable().setRefresh(true);
        }
    };
    /**
     * The Group Filter Ddlb value change.
     *
     * @param event the event
     */
    Property.ValueChangeListener groupFilterDdlbValueChange = new Property.ValueChangeListener() {
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
    public void multiVariablesCheck(final String period, final String refreshName)  {
        if (manualEntryMap.get(period) == null) {
            manualEntryMap.put(period, refreshName);
            return;
        }
        if (refreshName.equals(manualEntryMap.get(period))) {
            return;
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
    private boolean loadSelections() {
        LOGGER.debug(" inside load selections ");
        try {
            Map<Object, Object> map = new NMProjectionVarianceLogic().getNMProjectionSelection(session.getProjectionId(), TAB_DISCOUNT_PROJECTION.getConstant());
            
            if (map != null && map.size() > 0) {
                LOGGER.debug(" Freq " + map.get(Constant.FREQUENCY));
                LOGGER.debug(" history " + map.get(Constant.HISTORY));
                LOGGER.debug(" Period Order " + map.get(Constant.PROJECTION_PERIOD_ORDER_LABEL));
                LOGGER.debug(" Actuals / Proj" + map.get(Constant.ACTUALS_PROJECTIONS));
                LOGGER.debug(" Level " + map.get(Constant.LEVEL_LABEL));
                LOGGER.debug(" Program selection ddlb" + map.get("Program Selection Ddlb"));
                LOGGER.debug(" year seelction ddlb " + map.get(Constant.YEAR_SELECTION_DDLB));
                LOGGER.debug(" selected discount " + map.get(Constant.SELECTED_DISCOUNTS));

                frequencyDdlb.select(map.get(Constant.FREQUENCY));
                historyDdlb.select(Integer.parseInt(String.valueOf((map.get(Constant.HISTORY) == null || map.get(Constant.HISTORY).toString().equalsIgnoreCase("null")) ? 0 : map.get(Constant.HISTORY))));
                periodOrder.select(map.get(Constant.PROJECTION_PERIOD_ORDER_LABEL));
                actualsProjs.select(map.get(Constant.ACTUALS_PROJECTIONS));
                level.select(map.get(Constant.LEVEL_LABEL));
                yearSelection.select(map.get(Constant.YEAR_SELECTION_DDLB));
               if(!map.get(Constant.SELECTED_DISCOUNTS).equals("")) {

                    if ("Program Category".equals(String.valueOf(map.get(Constant.LEVEL_LABEL)))) {
                        discountProgramsList = new LinkedList<>(Arrays.asList(String.valueOf(map.get(Constant.SELECTED_DISCOUNTS)).split("\\s*,\\s*")));
                        discountNamesList = new LinkedList<>(discountProgramsList);
                        discountNoList = new LinkedList<>(Arrays.asList(String.valueOf(map.get("selectedDiscountNo")).split("\\s*,\\s*")));
                    } else {
                        List rawRSList=logic.getRSDiscountSids(""+session.getProjectionId());
                        discountProgramsList = new LinkedList<>((List<String>) rawRSList.get(0));
                        discountNamesList = new LinkedList<>(discountProgramsList);
                        discountNoList = new LinkedList<>((List<String>) rawRSList.get(1));
                    }
               }
                customizeDataForDDLB(discountProgramsList,programSelection);
                frequencyDdlb.focus();
                isListviewGenerated = true;
                return true;
            }
            viewValueChangeLogic();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return false;
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

            resultsTable.setImmediate(true);
            resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
            resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
            resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
            resultsTable.setHeight(Constant.SIX_FIFTY_PX);
            resultsTable.setWidth("100%");
            resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

            refreshBtn.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    if (isGrowthUpdatedManually || isRateUpdatedManually || isRPUUpdatedManually || isAmountUpdatedManually) {
                       LOGGER.debug("inside if refreshBtn--------------------------------- "); 
                        isGrowthUpdatedManually = false;
                        isRateUpdatedManually = false;
                        isRPUUpdatedManually = false;
                        isAmountUpdatedManually = false;
                        saveDiscountProjectionListview();
                        Object[] orderedArg = {session.getProjectionId(), session.getUserId(), session.getSessionId()};
                        CommonLogic.callProcedure("PRC_NM_DISCOUNT_REFRESH", orderedArg);
                        refreshTableData(getCheckedRecordsHierarchyNo());
                        manualEntryMap.clear();
                        final Notification notif = new Notification("Calculation Complete", Notification.Type.HUMANIZED_MESSAGE);
                        notif.setPosition(Position.TOP_CENTER);
                        notif.setStyleName(ConstantsUtils.MY_STYLE);
                        notif.show(Page.getCurrent());
                    }

                }
            });

        } catch (Exception e) {
            LOGGER.error(e);
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
            if (hirarechyNo != null && !hirarechyNo.equalsIgnoreCase("null")) {
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
        tableLogic.setRefresh(Boolean.FALSE);
        boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
        logic.checkClearAll(session, userGroup, checkClear);
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
        tableLogic.setRefresh(Boolean.TRUE);
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
        discountToBeLoaded.add(0, String.valueOf(level.getValue()));
        valueDdlbBean.addAll(logic.loadGroupValues(session, discountToBeLoaded));
        LOGGER.debug(" End load group ");
    }

    private String getRelationshipBuilderSid() {
        String relationshipBuilderSid;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator)) {
            relationshipBuilderSid = session.getCustRelationshipBuilderSid();
        } else {
            relationshipBuilderSid = session.getProdRelationshipBuilderSid();
        }
        return relationshipBuilderSid;
    }

    /**
     * To call the discount Projection procedure
     */
    public void callDiscountProjectionProcedure() {
        LOGGER.debug(" Calling Discount Projection Procedure");
        logic.callDiscountProjectionProcedure(session);
    }

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

        if (currentHierarchy != null) {
            boolean toSetCaption = true;
            for (int i = 0; i < currentHierarchy.size(); i++) {
                Leveldto levelDto = currentHierarchy.get(i);
                int startLevelNo = getStartLevelNo();
                if (levelDto.getTreeLevelNo() >= startLevelNo) {
                    if (toSetCaption) {
                        levelFilterDdlb.addItem(Constant.DASH + levelDto.getTreeLevelNo());
                        levelFilterDdlb.setItemCaption(Constant.DASH + levelDto.getTreeLevelNo(), SELECT_ONE.getConstant());
                        enableLevelFilterListener = false;
                        levelFilterDdlb.select(Constant.DASH + levelDto.getTreeLevelNo());
                        enableLevelFilterListener = true;
                        toSetCaption = false;
                    }
                    String itemId = levelDto.getTreeLevelNo() + StringUtils.EMPTY;
                    String caption = Constant.LEVEL + levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                    levelFilterDdlb.addItem(itemId);
                    levelFilterDdlb.setItemCaption(itemId, caption);

                    //To load Level Ddlb
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
            levelNo = Integer.valueOf(session.getCustomerLevelNumber());
        } else if (view.getValue().equals(PRODUCT.getConstant())) {
            levelNo = Integer.valueOf(session.getProductLevelNumber());
        }

        return levelNo;
    }

    @Override
    protected void viewValueChangeLogic() {
        try{
        viewDdlb.setEnabled(false);
        editBtn.setEnabled(false);
        newBtn.setEnabled(false);
        resetTableData();
        tableLogic.setRefreshHierarchyNo(StringUtils.EMPTY);
        if (view.getValue() != null) {
            if (CUSTOM.getConstant().equals(String.valueOf(view.getValue()))) {
                hierarchyIndicator = "CP";
                loadCustomDDLB();
                currentHierarchy = CommonLogic.getCustomTree(customId);
                currentHierarchy = session.getCustomHierarchyMap().get(customId);
                levelFilterDdlb.setEnabled(false);
                setUserGroup();
                fieldDdlb.removeItem(Constant.GROUPFCAPS);
                fieldDdlb.setValue(Constant.DISCOUNT_RATE_LABEL);
                resultsTable.getLeftFreezeAsTable().setColumnCollapsingAllowed(true);
                resultsTable.getLeftFreezeAsTable().setColumnCollapsed(Constant.GROUP, false);
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
                // As per FD Group column should not be in Product View
                resultsTable.getLeftFreezeAsTable().setColumnCollapsingAllowed(true);
                resultsTable.getLeftFreezeAsTable().setColumnCollapsed(Constant.GROUP, false);
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
                viewChangeGenerate();
                levelFilterDdlb.select(Constant.DASH + session.getProductLevelNumber());
                levelDdlb.setValue(SELECT_ONE.getConstant());
                fieldDdlb.removeItem(Constant.GROUPFCAPS);
                fieldDdlb.setValue(Constant.DISCOUNT_RATE_LABEL);
                resultsTable.getLeftFreezeAsTable().setColumnCollapsingAllowed(true);
                resultsTable.getLeftFreezeAsTable().setColumnCollapsed(Constant.GROUP, true);
            }
        }
        }catch(Exception e){
           LOGGER.error(e);
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
            loadDataInTable();

            loadLevelValues();

        }
    }

    private void configureLeftTable() {
        LOGGER.debug("Entering configureLeftTable");
        final ExtFilterTreeTable leftTable = resultsTable.getLeftFreezeAsTable();

        leftTable.setDoubleHeaderVisible(true);

        leftTable.setEditable(true);
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));

        leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
        leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());

        leftTable.setHeight(Constant.SIX_FIFTY_PX);

        leftTable.setColumnCheckBox(Constant.CHECKRECORD, true);
        leftTable.setColumnCheckBoxDisable(Constant.CHECKRECORD, ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));

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
                public Field<?> createField(final Container container, final Object itemId,
                        final Object propertyId, Component uiContext) {
                    String property = String.valueOf(propertyId);
                    Item item = container.getItem(itemId);
                    String levelType = String.valueOf(item.getItemProperty("level").getValue());
                    if (property.equals(Constant.CHECKRECORD)) {
                        final ExtCustomCheckBox check = new ExtCustomCheckBox();
                        check.setEnabled(!ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
                        check.setImmediate(true);
                        check.setData(new Object[]{itemId,propertyId});
                        check.addClickListener(clickListener);
                        return check;
                    }

                    if (Constant.GROUP.equals(property) && (Constant.TRADING_PARTNER.equals(levelType) || Constant.CUSTOMER_SMALL.equals(levelType)) && !Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equals(hierarchyIndicator) && !ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
                        TextField textField = new TextField();
                        textField.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
                        textField.setData(new Object[]{itemId,propertyId,"left"});
                        textField.addFocusListener(focusListener);
                        textField.addBlurListener(blurListener);
                        return textField;

                    }
                    return null;
                }
            });

        } catch (Exception e) {
            LOGGER.error(e);
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
            int treeLevelNo = 0;
            int count = 0;
            boolean checkValue = true;
            String hierarchyNo;
            List<String> customerHierarchyNoList = new ArrayList<>();
            List<String> productHierarchyNoList = new ArrayList<>();
            List<String> customerLevelNoList = new ArrayList<>();
            List<String> productLevelNoList = new ArrayList<>();
            List<String> hierarchyIndicatorList = new ArrayList<>();
            boolean isCustomHierarchy = Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equals(hierarchyIndicator);

            if (resultBeanContainer.size() == 0) {
                LOGGER.debug(" Container size is 0");
                return updatedRecordCount;
            }
            if (isCustomHierarchy) {
                if (customerHierarchyNoList.isEmpty() && productHierarchyNoList.isEmpty()) {
                    maxTreeLevelno = dto.getTreeLevelNo();
                }

                treeLevelNo = dto.getTreeLevelNo();
                if (treeLevelNo > maxTreeLevelno) {
                    customerHierarchyNoList.clear();
                    productHierarchyNoList.clear();
                    customerLevelNoList.clear();
                    productLevelNoList.clear();
                    hierarchyIndicatorList.clear();
                    count = 0;
                } else if (treeLevelNo == maxTreeLevelno) {
                    count++;
                }
                customerHierarchyNoList.add(dto.getCustomerHierarchyNo());
                productHierarchyNoList.add(dto.getProductHierarchyNo());

                hierarchyIndicatorList.add(dto.getHierarchyIndicator());
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                    customerLevelNoList.add(StringUtils.EMPTY + dto.getTreeLevelNo());
                    productLevelNoList.add(Constant.PERCENT);
                } else {
                    customerLevelNoList.add(Constant.PERCENT);
                    productLevelNoList.add(StringUtils.EMPTY + dto.getTreeLevelNo());
                }

            }

            checkValue = (Boolean) dto.getPropertyValue(Constant.CHECKRECORD);
            hierarchyNo = dto.getHierarchyNo();
            List<String> customViewDetails = new ArrayList<>();
            String relationshipBuilderSid = getRelationshipBuilderSid();
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
                    LOGGER.debug(" Check record - Custom Id " + customId);
                    LOGGER.debug(" Check record - customerLevelNo " + customerLevelNoList.get(i));
                    LOGGER.debug(" Check record - customerHierarchyNo " + customerHierarchyNoList.get(i));
                    LOGGER.debug(" Check record - productLevelNo " + productLevelNoList.get(i));
                    LOGGER.debug(" Check record - productHierarchyNo " + productHierarchyNoList.get(i));
                    LOGGER.debug(" Check record - checkValue " + checkValue);
                    LOGGER.debug(" Check record - Hierarchy indicator " + hierarchyIndicatorList.get(i));
                    LOGGER.debug(" Check record - CustRelationshipBuilderSid " + session.getCustRelationshipBuilderSid());
                    LOGGER.debug(" Check record - ProdRelationshipBuilderSid " + session.getProdRelationshipBuilderSid());

                    updatedRecordCount = logic.updateCheckRecord(session, checkValue, hierarchyNo, hierarchyIndicatorList.get(i), isCustomHierarchy, customViewDetails,  isProgram, discountList);
                }
            } else {
                updatedRecordCount = logic.updateCheckRecord(session, checkValue, hierarchyNo, hierarchyIndicator, isCustomHierarchy, customViewDetails, isProgram, discountList);
            }
        } catch (Exception e) {
            LOGGER.error(e);
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
        LOGGER.debug(" updatedRecordsNo " + newRecordsCount);
        if (checkValue) {
            if (newRecordsCount > dto.getUncheckCount()) {
                newRecordsCount = dto.getUncheckCount();
            }
        } else if (newRecordsCount < dto.getUncheckCount()) {
            newRecordsCount = dto.getUncheckCount();
        }
        LOGGER.debug((checkValue ? "Checked" : "Unchecked") + " updatedRecordsNo " + newRecordsCount);
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
                LOGGER.debug(tempDto.getTreeLevelNo() + " " + tempDto.getLevelName() + " Parent Uncheck count before " + tempDto.getUncheckCount());
                if (checkValue) {
                    tempDto.setUncheckCount(tempDto.getUncheckCount() - newRecordsCount);
                } else {
                    tempDto.setUncheckCount(tempDto.getUncheckCount() + newRecordsCount);
                }
                LOGGER.debug(tempDto.getTreeLevelNo() + " " + tempDto.getLevelName() + " Parent Uncheck count after " + tempDto.getUncheckCount());
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
                LOGGER.debug(tempDto.getTreeLevelNo() + " " + tempDto.getLevelName() + " Child Uncheck count before " + tempDto.getUncheckCount());

                if (checkValue) {
                    tempDto.setUncheckCount(0);
                } else {
                    tempDto.setUncheckCount(tempDto.getCcpCount());
                }
                LOGGER.debug(tempDto.getTreeLevelNo() + " " + tempDto.getLevelName() + " Child Uncheck count after " + tempDto.getUncheckCount());
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
        viewDdlb.setEnabled(true);
        newBtn.setEnabled(true);
        if(session.getCustomerViewList().isEmpty()){
        customViewList = CommonLogic.getCustomViewList(session.getProjectionId());
            session.setCustomerViewList(customViewList);
            }else{
                customViewList=session.getCustomerViewList();
            }
        if (customViewList != null) {
            viewDdlb.removeAllItems();
            viewDdlb.addItem(SELECT_ONE);
            viewDdlb.setNullSelectionItemId(SELECT_ONE);
            Object select = null;
            for (CustomViewMaster obj : customViewList) {
                int customSid = obj.getCustomViewMasterSid();
                Object itemId = customSid;
                if (customIdToSelect == customSid) {
                    select = itemId;
                }
                viewDdlb.addItem(itemId);
                viewDdlb.setItemCaption(itemId, obj.getViewName());
            }
            if (select != null) {
                levelDdlb.setEnabled(true);
                viewDdlb.select(customIdToSelect);
            } else {
                levelDdlb.setEnabled(false);
                viewDdlb.setValue(SELECT_ONE);
            }
        }
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
        logic.checkClearAll(session, userGroup, false);
    }

    @Override
    protected void adjProgramsValueChangeLogic(String adjustmentProgram) {
        ExtPagedTreeTable rightTable = resultsTable.getRightFreezeAsTable();
        checkedDiscountsPropertyIds.clear();
        boolean checkvalue = adjustmentProgram.equals(ALL.getConstant());
        for (Object columns : rightTable.getTripleHeaderVisibleColumns()) {
            rightTable.setTripleHeaderColumnCheckBox(columns, true, checkvalue);
            rightTable.setTripleHeaderColumnCheckBoxDisable(columns, ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
            tripleHeaderCheckListener(columns, checkvalue);
        }
        LOGGER.debug(" adjustmentPrograms " + adjustmentProgram);
    }

    @Override
    protected void adjPeriodValueChangeLogic(String adjustmentPeriod) {
        String adjustmentPrograms = String.valueOf(adjprograms.getValue());
        LOGGER.debug(" adjustmentPeriods " + adjustmentPeriod);
        LOGGER.debug(" adjustmentPrograms " + adjustmentPrograms);
        if (adjustmentPeriod.equals(ALL.getConstant())) {
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
                LOGGER.debug(" Adj periods discount Column Name " + discountColumn);
                doubleColumns = rightHeader.getTripleHeaderMaps().get(discountColumn);
                for (Object doubleColumn : doubleColumns) {
                    rightTable.setDoubleHeaderColumnCheckBox(doubleColumn, true, true);
                    rightTable.setDoubleHeaderColumnCheckBoxDisable(doubleColumn, ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
                    doubleHeaderCheckListener(doubleColumn, true);
                }
            }
        } else {
            for (Object columns : resultsTable.getRightFreezeAsTable().getDoubleHeaderVisibleColumns()) {
                resultsTable.getRightFreezeAsTable().setDoubleHeaderColumnCheckBox(columns, true, false);
                resultsTable.getRightFreezeAsTable().setDoubleHeaderColumnCheckBoxDisable(columns, ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
            }
            tripleHeaderForCheckedDoubleHeader.clear();
        }
    }

    @Override
    protected void populateBtnClickLogic() {
        if (isGroupUpdatedManually) {
            NotificationUtils.getAlertNotification(Constant.GROUP_FILTER_CONFLICT, "The group value entered conflicted with the group filter. Kindly Click Refresh button to proceed with this operation");
            return;
        }
        final String selectedField = String.valueOf(fieldDdlb.getValue());
        final String valueText = value.getValue();
        final String valueDdlbValue = String.valueOf(valueDdlb.getValue());
        try {
            LOGGER.debug("fieldDdlb.getValue()-->>>" + selectedField);
            if (!valueText.matches("([0-9|\\.|])*") && (valueText.length() - valueText.replace(".", StringUtils.EMPTY).length() > 1)) {
                AbstractNotificationUtils.getErrorNotification("Field Error", "Only Numbers are allowed");
                value.setValue(StringUtils.EMPTY);
                return;
            }

            saveDiscountProjectionListview();
            boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
            if (logic.isAnyRecordChecked(session, isProgram, projectionSelection.getDiscountProgramsList())) {

                if (fieldDdlb != null && !Constant.NULL.equals(selectedField) && !selectedField.isEmpty()) {
                    final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
                    List<String> checkedDiscountNames = new ArrayList<>();
                    if ((selectedField.equals(Constant.GROUPFCAPS) && (valueDdlb != null && valueDdlb.getValue() != null && !valueDdlbValue.isEmpty()))
                            || (selectedField.equals(Constant.DISCOUNT_RATE_LABEL) && !valueText.isEmpty())
                            || (selectedField.equals("RPU") && !valueText.isEmpty())
                            || (selectedField.equals(Constant.DISCOUNT_AMOUNT_LABEL) && !valueText.isEmpty())
                            || (selectedField.equals(Constant.GROWTH) && !valueText.isEmpty())) {
                        if (!Constant.GROUPFCAPS.equals(selectedField)) {
                            for (Object discountPropertyId : checkedDiscountsPropertyIds) {
                                checkedDiscountNames.add(rightTable.getTripleHeaderColumnHeader(discountPropertyId));
                            }
                            if (checkedDiscountNames.isEmpty()) {
                                new AbstractNotificationUtils() {
                                    @Override
                                    public void noMethod() {
                                        return;
                                    }

                                    @Override
                                    public void yesMethod() {
                                        List<String> allDiscountNames = new ArrayList<>();
                                        String[] rightTripleHeaderNames = resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeaders();
                                        for (int i = 0; i < rightTripleHeaderNames.length; i++) {
                                            allDiscountNames.add(rightTripleHeaderNames[i]);
                                        }
                                  discountRatemassUpdate(allDiscountNames, selectedField, value.getValue() , projectionSelection.getDiscountProgramsList());
                                    }
                                }.getConfirmationMessage("No Discount Selected", "You have not selected a discount. The Mass Update value will apply to ALL discounts in the list view.");

                            } else {
                                
                                discountRatemassUpdate(checkedDiscountNames, selectedField, value.getValue(), projectionSelection.getDiscountProgramsList());
                            }
                        } else {
                            for (Object itemId : resultsTable.getLeftFreezeAsTable().getItemIds()) {
                                DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                                if ((Boolean) dto.getPropertyValue(Constant.CHECKRECORD) && Constant.CUSTOMER_SMALL.equals(resultsTable.getLeftFreezeAsTable().getItem(itemId).getItemProperty("level").getValue().toString())) {
                                    resultsTable.getLeftFreezeAsTable().getItem(itemId).getItemProperty(Constant.GROUP).setValue(valueDdlbValue);
                                    ((DiscountProjectionDTO) itemId).setGroup(valueDdlbValue);
                                }
                            }

                             performMassUpdate(new ArrayList<Integer>(), checkedDiscountNames, selectedField, value.getValue(), projectionSelection.getDiscountProgramsList());
                        }

                    } else {
                        NotificationUtils.getErrorNotification("No Value", "Please enter any value to update");
                    }
                } else {
                    NotificationUtils.getErrorNotification("No Field selected", "Please select a Field");
                }
            } else {
                NotificationUtils.getErrorNotification("No records selected", "Please select which levels in the list view the Mass Update applies to. \n"
                        + "AND/OR " + "Please select which discount this Mass Update applies to. ");
            }
        } catch (Exception e) {
            LOGGER.error(e);

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
    private void discountRatemassUpdate(List<String> checkedDiscountNames, String selectedField, String value, List<String> selectedPeriods) {

        LOGGER.debug(" checkedDiscounts " + checkedDiscountNames);

        if (startPeriod != null && startPeriod.getValue() != null) {
            String startPeriodValue = startPeriod.getValue().toString();
            int startFreq = 0;
            int startYear = 0;
            int endFreq = 0;
            int endYear = 0;

            // To set startFreq (in No) and startYear
            if (!projectionSelection.getFrequency().equals(MONTHLY.getConstant())) {
                String startFreqNoValue = startPeriodValue.substring(1, NumericConstants.TWO);
                startFreq = isInteger(startFreqNoValue) ? Integer.valueOf(startFreqNoValue) : 0;
            } else {
                String startMonthValue = startPeriodValue.substring(0, startPeriodValue.length() - NumericConstants.FIVE);
                startFreq = CommonUtils.getIntegerForMonth(startMonthValue);
            }
            LOGGER.debug("startPeriodValue-->>" + startPeriodValue);
            startYear = Integer.valueOf(startPeriodValue.substring(startPeriodValue.length() - NumericConstants.FOUR));

            // To set endFreq (in No) and endYear
            String endPeriodValue = StringUtils.EMPTY;
            if (endPeriod.getValue() == null || Constant.NULL.equals(String.valueOf(endPeriod.getValue())) || Constant.SELECT_ONE.equals(String.valueOf(endPeriod.getValue()))) {
                endPeriodValue = startPeriodBean.getIdByIndex(startPeriodBean.size() - 1);
            } else {
                endPeriodValue = endPeriod.getValue().toString();
            }
            LOGGER.debug(" endPeriodValue " + endPeriodValue);
            if (!projectionSelection.getFrequency().equals(MONTHLY.getConstant())) {
                String endFreqNoValue = endPeriodValue.substring(1, NumericConstants.TWO);
                endFreq = isInteger(endFreqNoValue) ? Integer.valueOf(endFreqNoValue) : 0;
            } else {
                String endMonthValue = endPeriodValue.substring(0, endPeriodValue.length() - NumericConstants.FIVE);
                endFreq = CommonUtils.getIntegerForMonth(endMonthValue);
            }
            endYear = Integer.valueOf(endPeriodValue.substring(endPeriodValue.length() - NumericConstants.FOUR));

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
     private void performMassUpdate(List<Integer> massUpdatePeriods, List<String> checkedDiscountNames, String selectedField, String value, List<String> selectedPeriods ) {
        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction())) {
            for (Object itemId : resultsTable.getLeftFreezeAsTable().getItemIds()) {
                DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                ccpsCount = dto.getCcpCount();
            }
        }
        if (Constant.GROUPFCAPS.equals(selectedField)) {
            LOGGER.debug("Group-->" + value);
            logic.massUpdate(session, projectionSelection.getFrequency(), massUpdatePeriods, selectedField, value, checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()),getCheckedRecordsForMassUpdate(), selectedPeriods);
            loadGroupDdlb();
            loadGroupFilterDdlb();
            if (!userGroup.isEmpty()) {
                tableLogic.clearAll();
                tableLogic.setCurrentPage(1);
                isGroupUpdatedManually = false;
            } else {
                refreshTableData(getCheckedRecordsHierarchyNo());
            }
        } else if ("Discount Rate".equals(selectedField)) {
            saveDiscountProjectionListview();
            LOGGER.debug("Discount Rate-->" + value);
            logic.massUpdate(session, projectionSelection.getFrequency(), massUpdatePeriods, selectedField, value, checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()),getCheckedRecordsForMassUpdate(), selectedPeriods);
            refreshTableData(getCheckedRecordsHierarchyNo());
        } else if ("RPU".equals(selectedField)) {
            saveDiscountProjectionListview();
            LOGGER.debug("RPU-->" + value + "ccpsCount" + ccpsCount);
            logic.massUpdate(session, projectionSelection.getFrequency(), massUpdatePeriods, selectedField, value, checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()),getCheckedRecordsForMassUpdate(), selectedPeriods);
            refreshTableData(getCheckedRecordsHierarchyNo());
        } else if ("Discount Amount".equals(selectedField)) {
            ccpsCount = 0;
            saveDiscountProjectionListview();
            LOGGER.debug("Discount Amount-->" + value + "ccpCount" + ccpsCount);

            for (int i = 0; i < checkedDiscountNames.size(); i++) {
                for (int j = 0; j < ccpsCountForMassUpdate.size(); j++) {
                    if (ccpsCountForMassUpdate.getIndex(j).getKey().toString().startsWith(checkedDiscountNames.get(i))) {
                        ccpsCount += ccpsCountForMassUpdate.getInt(ccpsCountForMassUpdate.getIndex(j).getKey());
                    }
                }
            }
            double finalValue = doubleConversion(value);
//            value = String.valueOf(finalValue);
            logic.massUpdate(session, projectionSelection.getFrequency(), massUpdatePeriods, selectedField, String.valueOf(value), checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()),getCheckedRecordsForMassUpdate(), selectedPeriods);
            refreshTableData(getCheckedRecordsHierarchyNo());
        } else if ("Growth".equals(selectedField)) {
            saveDiscountProjectionListview();
            LOGGER.debug("Growth-->" + value);
            logic.massUpdate(session, projectionSelection.getFrequency(), massUpdatePeriods, selectedField, value, checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()),getCheckedRecordsForMassUpdate(), selectedPeriods);
            refreshTableData(getCheckedRecordsHierarchyNo());
        }

    }

    @Override
    protected void calculateBtnClickLogic() {
        try {

            if (CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue())) {
                CommonUtil.getInstance().waitsForOtherThreadsToComplete(session.getFutureValue(Constant.CALL_PRC_CONTRACT_DETAILS_REBATE));
            }
            Set<String> setMethodologiesValuesVal = new HashSet();
            setMethodologiesValuesVal.addAll(Arrays.asList(new String[]{Constant.SINGLE_PERIOD, "% of Ex-Factory Sales", "% of Demand", "% of Inventory Withdrawal", Constant.PERC_OF_ADJUSTED_DEMAND}));
            String endValue;
            List<String> checkedDiscountNames = new ArrayList<>();
            List<String> checkedDiscountNamesList = new ArrayList<>();
            if (endPeriodForecastTab.getValue() != null) {
                endValue = endPeriodForecastTab.getValue().toString().replace(" ", "~").trim();
            } else {
                endValue = forecaststartBean.getIdByIndex(forecaststartBean.size() - 1);
            }
            if ((methodologyDdlb.getValue() != null && !methodologyDdlb.getValue().equals(SELECT_ONE.getConstant())) && !String.valueOf(methodologyDdlb.getValue().toString().trim()).equals(Constant.NULL)) {
                if (endDateValidation(endValue)) {
                    if (startPeriodForecastTab.getValue() != null && !startPeriodForecastTab.getValue().equals(SELECT_ONE.getConstant())) {
                        if (!CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue()) && !AVERAGE.getConstant().equals(methodologyDdlb.getValue())
                                && !ROLLING_ANNUAL_TREND.getConstant().equals(methodologyDdlb.getValue()) && !Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND.equals(methodologyDdlb.getValue())
                                && (checkBoxMap.size() == 0 || checkedDiscountsPropertyIds.size() != checkBoxMap.size())) {
                            NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED, "Please select a Historic Period for each discount selected.");
                        } else if (baseLineCalc(startPeriodForecastTab.getValue().toString(), endValue.replace("~", " ")) || methodologyDdlb.getValue().equals(CONTRACT_DETAILS.getConstant())) {
                            boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
                            if (logic.isAnyRecordChecked(session, isProgram, projectionSelection.getDiscountProgramsList())) {
                                if (fileAlertForPFDChanges()) {
                                    if ((methodologyDdlb.getValue().equals(ROLLING_ANNUAL_TREND.getConstant()) || methodologyDdlb.getValue().equals(Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND)) && !projectionSelection.getFrequency().equals(ANNUALLY.getConstant()) && !rollingAnnualTrendCount()) {
                                        NotificationUtils.getErrorNotification("Select complete annual period", "The "+methodologyDdlb.getValue().toString()+" methodology requires"
                                                + " a complete calendar year of periods to use as a baseline."
                                                + "  Please select a complete calendar year of periods "
                                                + "for each selected discount and try again.");
                                    } else if (methodologyDdlb.getValue().equals(AVERAGE.getConstant()) && (checkedDiscountsPropertyIds.size() != tripleHeaderForCheckedDoubleHeader.size()
                                            || !checkHistorySelectedCount(NumericConstants.TWO))) {
                                        NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED, "Please select at least two historic periods to use as a baseline for each selected discount.");
                                    } else if (setMethodologiesValuesVal.contains(String.valueOf(methodologyDdlb.getValue())) && !checkHistorySelectedCount(0)) {
                                        NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED, "Please select a Historic Period for each discount selected.");
                                    } else if (setMethodologiesValuesVal.contains(String.valueOf(methodologyDdlb.getValue())) && !checkHistorySelectedCount(1)) {
                                        NotificationUtils.getErrorNotification("More than one period selected", "Please select only one period to use as a baseline for each discount selected.");
                                    } else if ("Historic % of Business".equals(methodologyDdlb.getValue()) && !checkHistorySelectedCount(0)) {
                                        NotificationUtils.getErrorNotification("Error", "Please select a historic period to use as a baseline for each of the selected discounts.");
                                    } else if (!CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue()) && checkedDiscountsPropertyIds.size() == 0) {
                                        NotificationUtils.getErrorNotification("No Discount selected", "Please select atleast one discount.");
                                    } else if (!CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue()) && radioMap.size() == 0) {
                                        NotificationUtils.getErrorNotification("No variable types selected", "Please select variable type across the baseline periods.");
                                    } else {
                                        boolean doMethodology = false;
                                        if (radioMap.size() != 0) {
                                            doMethodology = true;
                                        } else if (CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue())) {
                                            doMethodology = true;
                                        } else {
                                            NotificationUtils.getErrorNotification("No variable types selected", "Please select variable type across the baseline periods.");
                                        }
                                        if (doMethodology) {
                                            if (getCalculationBased()) {
                                                projectionSelection.setMethodology(String.valueOf(methodologyDdlb.getValue()));
                                                projectionSelection.setFromDateDdlb(logic.getPeriodSid(String.valueOf(startPeriodForecastTab.getValue()), String.valueOf(frequencyDdlb.getValue()), "Min"));
                                                projectionSelection.setToDateDdlb(logic.getPeriodSid(endValue.replace("~", " "), String.valueOf(frequencyDdlb.getValue()), "Max"));
                                                projectionSelection.setCalcBased(calcBase);
                                                projectionSelection.setDiscountLevel(String.valueOf(discType.getValue()));
                                                if (checkedDiscountNames.size() != 0) {
                                                    for (Object discountPropertyId : checkedDiscountsPropertyIds) {
                                                        checkedDiscountNames.add(resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeader(discountPropertyId));
                                                    }
                                                } else if (CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue())) {
                                                    checkedDiscountNames = Arrays.asList(resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeaders());
                                                }
                                                for (Object discountPropertyId : checkedDiscountsPropertyIds) {
                                                    checkedDiscountNamesList.add(resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeader(discountPropertyId));
                                                }
                                                logic.calcDataUpdate(session, projectionSelection, String.valueOf(level.getValue()),
                                                        tripleHeaderForCheckedDoubleHeader, checkedDiscountNames, isProgram);
                                                session.setFrequency(String.valueOf(frequencyDdlb.getValue()));
                                                commonLogic.insertPFDTemp(session, String.valueOf(methodologyDdlb.getValue()), StringUtils.EMPTY, false);
                                                if ("Rolling Annual Trend".equals(String.valueOf(methodologyDdlb.getValue())) || Constant.SINGLE_PERIOD.equals(String.valueOf(methodologyDdlb.getValue())) || "Average".equals(String.valueOf(methodologyDdlb.getValue()))) {
                                                    cumulativeCalculation(projectionSelection, String.valueOf(methodologyDdlb.getValue()), "st_disc_growth_factor_", checkedDiscountNamesList, String.valueOf(level.getValue()));
                                                }
                                                logic.callDPProcedure(session, projectionSelection);
                                                refreshTableData(getCheckedRecordsHierarchyNo());
                                                final Notification notif = new Notification("Calculation Complete", Notification.Type.HUMANIZED_MESSAGE);
                                                notif.setPosition(Position.TOP_CENTER);
                                                notif.setStyleName(ConstantsUtils.MY_STYLE);
                                                notif.show(Page.getCurrent());
                                            } else {
                                                NotificationUtils.getErrorNotification("Different variable types selected", "Please select only one variable type across the baseline periods.");

                                            }
                                        }
                                    }
                                } else {
                                    NotificationUtils.getAlertNotification("New File Activated", "There is a new file " + session.getFileName() + " that has been activated. Please recalculate the Sales Projection, since it is currently based off of outdated data");
                                }
                            } else {
                                NotificationUtils.getErrorNotification("No Hierarchy level selected", "Please select a level in the hierarchy for the methodology");
                            }
                        } else if (baselineFlag == 1) {
                            AbstractNotificationUtils.getErrorNotification("Baseline period within calculation range", "The selected baseline periods are within the Start Period and End Period range for one or more of the selected discounts.  Please select a baseline period that is prior to the calculation range for each of the selected discounts.");
                        } else if (baselineFlag == NumericConstants.TWO) {
                            AbstractNotificationUtils.getErrorNotification("Baseline period after calculation range", "The selected baseline periods are after the Start Period and End Period range for one or more of the selected discounts.  Please select a baseline period that is prior to the calculation range for each of the selected discounts.");
                        }

                    } else {
                        NotificationUtils.getErrorNotification(Constant.NO_START_PERIOD_SELECTED, "Please select a Start Period");
                    }

                } else if (errorFlag) {
                    AbstractNotificationUtils.getErrorNotification("End Period is before Start Period", "The Start Period must be before the End Period.Please try again");
                    errorFlag = false;
                }
            } else {
                NotificationUtils.getErrorNotification("No Methodology selected", "Please select a Methodology");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    
    private boolean fileAlertForPFDChanges() {
        try {
            if (Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction())) {
                return true;
            } else if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction())
                    && session.isIsSalesCalculated()
                    && session.getDiscountCanBeCalculated(commonLogic.getFileMethodologyName(session, String.valueOf(methodologyDdlb.getValue())))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    protected void newBtnClickLogic() {
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
        if (isGroupUpdatedManually) {
            NotificationUtils.getAlertNotification(Constant.GROUP_FILTER_CONFLICT, "The group value entered conflicted with the group filter. Kindly Click Refresh button before proceeding this operation");
            return;
        }
        final String levelType = String.valueOf(level.getValue());
        final String adjustmentType = String.valueOf(type.getValue());
        final String adjustmentBasis = String.valueOf(basis.getValue());
        final String adjustmentValue = adjustment.getValue();
        final String allocationMethodology = String.valueOf(allocMethodology.getValue());
        List<String> selectedDoubleList = new ArrayList<>();
        saveDiscountProjectionListview();
        if (!adjustmentValue.matches("[-+]?(?:\\d+|\\d*(?:\\.\\d{1,2})?)")) {
            AbstractNotificationUtils.getErrorNotification("Field Error", "Please enter a numeric value in the Adjustment text box");
            adjustment.setValue(StringUtils.EMPTY);
            return;
        }

        if (!Constant.NULL.equals(allocationMethodology)) {

            if (!adjustmentValue.replace(" ", StringUtils.EMPTY).isEmpty()) {

                if (!checkedDiscountsPropertyIds.isEmpty()) {

                    List<String> headerList = new ArrayList<>();
                    for (Object propertyId : checkedDiscountsPropertyIds) {
                        String tripleHeader = resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeader(propertyId);
                        headerList.add(tripleHeader);
                    }
                    List<String> remoList = new ArrayList<>(tripleHeaderForCheckedDoubleHeader.keySet());
                    remoList.removeAll(headerList);

                    List<String> baselinePeriods;

                    for (Object propertyId : checkedDiscountsPropertyIds) {

                        String tripleHeader = resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeader(propertyId);
                        if (tripleHeaderForCheckedDoubleHeader.get(tripleHeader) == null) {
                            NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED, "Please select which periods need to be included in the adjustment for each of the selected discounts.");
                            return;
                        }

                        if (Constant.HISTORICAL_OF_BUSINESS.equals(allocationMethodology)) {
                            baselinePeriods = tripleHeaderForCheckedDoubleHeader.get(tripleHeader).get("H");
                        } else {
                            baselinePeriods = tripleHeaderForCheckedDoubleHeader.get(tripleHeader).get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                        }

                        if ((Constant.HISTORICAL_OF_BUSINESS.equals(allocationMethodology)) && (baselinePeriods == null || baselinePeriods.isEmpty())) {
                                NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED, "Please select a historic period to use as a baseline for each of the selected discounts.");
                                return;
                            }

                        List<String> selectedPeriods = tripleHeaderForCheckedDoubleHeader.get(tripleHeader).get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                        List<String> selectedHistPeriods = tripleHeaderForCheckedDoubleHeader.get(tripleHeader).get("H");
                        if (selectedHistPeriods != null && !selectedHistPeriods.isEmpty()) {
                            selectedDoubleList.addAll(selectedHistPeriods);
                        }
                        if (selectedPeriods != null && !selectedPeriods.isEmpty()) {
                            selectedDoubleList.addAll(selectedPeriods);
                        }
                        if (selectedPeriods == null || selectedPeriods.isEmpty()) {
                            NotificationUtils.getErrorNotification(Constant.NO_PERIOD_SELECTED, "Please select which periods need to be included in the adjustment for each of the selected discounts.");
                            LOGGER.error("Please select the projection periods for the discounts " + tripleHeader);
                            return;
                        }
                    }
                    boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
                    if (logic.isAnyRecordChecked(session, isProgram, projectionSelection.getDiscountProgramsList())) {

                        String confirmMessage = "Confirm Incremental adjustment";
                        String messageBody = StringUtils.EMPTY;
                        String basisCharacter = StringUtils.EMPTY;
                        if (adjustmentBasis.equals(AMOUNT.getConstant())) {
                            basisCharacter = DOLLAR.getConstant();
                        } else if (adjustmentBasis.equals(PERCENTAGE.getConstant())) {
                            basisCharacter = PERCENT.getConstant();
                        }
                        if (adjustmentType.equals(INCREMENTAL.getConstant())) {
                            confirmMessage = "Confirm Incremental adjustment";
                            messageBody = "You are about to make the following " + adjustment.getValue() + basisCharacter + " adjustment for the selected periods" + selectedDoubleList + ". Are you sure you want to continue?";
                        } else if (adjustmentType.equals(OVERRIDE.getConstant())) {
                            confirmMessage = "Confirm Override";
                            messageBody = "You are about to replace the current values in the list view with the following variable: " + adjustment.getValue() + basisCharacter + ". Are you sure you want to continue?";
                        }

                        new AbstractNotificationUtils() {
                            @Override
                            public void noMethod() {
                                return;
                            }

                            @Override
                            public void yesMethod() {
                                if (logic.adjustmentDataUpdate(projectionSelection.getFrequency(), allocationMethodology, tripleHeaderForCheckedDoubleHeader)) {
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
                                    logic.checkUncheckRebateBeforeAdjust(false, checkedDiscountList, session, true, isProgram);
                                    session.setFrequency(projectionSelection.getFrequency());
                                    if (logic.adjustDiscountProjection(session,adjustmentType,
                                        adjustmentBasis, adjustmentValue, allocationMethodology)) {
                                        LOGGER.debug(" Procedure executed Successfully");
                                        logic.checkUncheckRebateBeforeAdjust(true, selectedDiscountList, session, false, isProgram);
                                        refreshTableData(getCheckedRecordsHierarchyNo());
                                    } else {
                                        logic.checkUncheckRebateBeforeAdjust(true, selectedDiscountList, session, false, isProgram);
                                        LOGGER.error(" Error to update data for adjustmnet. Procedure not executed");
                                    }
                                } else {
                                    LOGGER.error(" Error to Update value for Adjust");
                                }

                            }
                        }.getOkCancelMessage(confirmMessage, messageBody);
                    } else {
                        NotificationUtils.getErrorNotification("No level selected", "Please select a level this adjustment applies to");
                    }

                } else {
                    NotificationUtils.getErrorNotification("No Discount (Program) selected", "Please select which discount(s) this adjustment applies to.");
                }
            } else {
                NotificationUtils.getErrorNotification("No adjustment entered", "Please enter a numeric value in the Adjustment text box.");
            }
        } else {
            NotificationUtils.getErrorNotification("No Allocation Methodology selected", "Please select an Allocation Methodology");
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
       

        List<String[]> hierarchyList = new ArrayList<>();

        for (String tableTreeLevelNo : tableLogic.getAllLevels()) {
            Object itemId = tableLogic.getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                itemId = tableLogic.getExpandedTreeValues(tableTreeLevelNo);
            }
            if (itemId != null) {
                if ((boolean)((DiscountProjectionDTO) itemId).getProperties().get("checkRecord") && tableLogic.getAllChildLevels(tableTreeLevelNo).isEmpty()) {
                     String[] dataForMassUpdate = new String[3];
                    dataForMassUpdate[0] = ((DiscountProjectionDTO) itemId).getHierarchyNo();
                    dataForMassUpdate[1] = ((DiscountProjectionDTO) itemId).getHierarchyIndicator();
                    dataForMassUpdate[2] = getPreviousHierachy(tableTreeLevelNo, ((DiscountProjectionDTO) itemId).getHierarchyIndicator());
                    hierarchyList.add(dataForMassUpdate);
                }
            }
        }
        return hierarchyList;
    }
    @Override
    protected void excelExportClickLogic() {
        LOGGER.debug("excel starts");
        try {
            excelTable.setRefresh(Boolean.FALSE);
            excelContainer = new ExtTreeContainer<>(DiscountProjectionDTO.class,ExtContainer.DataStructureMode.MAP);
            excelContainer.setColumnProperties(excelHeaderLeft.getProperties());
            excelContainer.setColumnProperties(rightHeader.getProperties());
            excelTable.setContainerDataSource(excelContainer);
            ExtFilterTreeTable leftTable = resultsTable.getLeftFreezeAsTable();
            Object[] leftTableVisibleColumn = leftTable.getVisibleColumns();
            String[] leftTableColumnHeader = leftTable.getColumnHeaders();
            excelTable.setVisibleColumns(ArrayUtils.addAll(Arrays.copyOfRange(leftTableVisibleColumn, 1, leftTableVisibleColumn.length), excelHeader.getSingleColumns().toArray()));
            Object[] objectArray = ArrayUtils.addAll(Arrays.copyOfRange(leftTableColumnHeader, 1, leftTableColumnHeader.length), excelHeader.getSingleHeaders().toArray(new String[0]));
            excelTable.setColumnHeaders(Arrays.copyOf(objectArray, objectArray.length, String[].class));
            excelTable.setDoubleHeaderVisible(true);
            excelTable.setDoubleHeaderVisibleColumns(ArrayUtils.addAll(Arrays.copyOfRange(leftHeader.getDoubleColumns().toArray(), 0, leftHeader.getDoubleColumns().toArray().length), excelHeader.getDoubleColumns().toArray()));
            Object[] objectArrayDouble = ArrayUtils.addAll(Arrays.copyOfRange(leftHeader.getDoubleHeaders().toArray(), 0, leftHeader.getDoubleHeaders().toArray().length), excelHeader.getDoubleHeaders().toArray(new String[excelHeader.getDoubleHeaders().size()]));
            excelTable.setDoubleHeaderColumnHeaders(Arrays.copyOf(objectArrayDouble, objectArrayDouble.length, String[].class));
            Map<Object, Object[]> mapVisibleCols = new HashMap();
            mapVisibleCols.putAll(excelHeaderLeft.getDoubleHeaderMaps());
            mapVisibleCols.putAll(excelHeader.getDoubleHeaderMaps());
            excelTable.setDoubleHeaderMap(mapVisibleCols);
            for (Object propertyId : excelTable.getContainerPropertyIds()) {
                if (String.valueOf(propertyId).endsWith("Rate")) {
                    excelTable.setConverter(propertyId, percentFormat);
                }
            }
            generateButtonlogicForExcel();
            Map<String, String> formatter = new HashMap<>();
            formatter.put("percentThreeDecimal", "Rate");
            formatter.put("currencyTwoDecimal", "RPU");
            formatter.put("amountTwoDecimal", "Amount");
            excelTable.setRefresh(Boolean.TRUE);
            ForecastUI.EXCEL_CLOSE = true;
            CustomExcelNM excel = null;
            HeaderUtils.getDiscountProjectionRightTableColumns(projectionSelection);
            if (QUARTERLY.getConstant().equals(String.valueOf(frequencyDdlb.getValue())) || MONTHLY.getConstant().equals(String.valueOf(frequencyDdlb.getValue()))) {
                
                Map<Integer,List> headerMapBasedonYear = logic.configureVisibleColumnMapsForExcel(projectionSelection.getHeaderMapForExcel());
                int exportAt = headerMapBasedonYear.size()  - 1;                                
                List<Integer> list = new ArrayList(headerMapBasedonYear.keySet());
                Collections.sort(list);
                for (int i = 0; i < list.size(); i++) {
                    excelTable.setVisibleColumns(ArrayUtils.addAll(Arrays.copyOfRange(leftTableVisibleColumn, 1, leftTableVisibleColumn.length), ((List<Object>) headerMapBasedonYear.get(list.get(i)).get(0)).toArray()));
                    Object[] header = ArrayUtils.addAll(Arrays.copyOfRange(leftTableColumnHeader, 1, leftTableColumnHeader.length), ((List<Object>) headerMapBasedonYear.get(list.get(i)).get(1)).toArray());
                    excelTable.setColumnHeaders(Arrays.copyOf(header, header.length, String[].class));
                    excelTable
                            .setDoubleHeaderVisibleColumns(ArrayUtils.addAll(Arrays.copyOfRange(leftHeader.getDoubleColumns().toArray(), 0, leftHeader.getDoubleColumns().toArray().length), ((List<Object>) headerMapBasedonYear.get(list.get(i)).get(NumericConstants.THREE)).toArray()));
                    Object[] doubleHeader = ArrayUtils.addAll(Arrays.copyOfRange(leftHeader.getDoubleHeaders().toArray(), 0, leftHeader.getDoubleHeaders().toArray().length), ((List<Object>) headerMapBasedonYear.get(list.get(i)).get(NumericConstants.FOUR)).toArray());
                    excelTable
                            .setDoubleHeaderColumnHeaders(Arrays.copyOf(doubleHeader, doubleHeader.length, String[].class));
                    mapVisibleCols = new HashMap();
                    mapVisibleCols.putAll(excelHeaderLeft.getDoubleHeaderMaps());
                    mapVisibleCols.putAll((Map<Object, Object[]>) headerMapBasedonYear.get(list.get(i)).get(NumericConstants.FIVE));
                    excelTable.setDoubleHeaderMap(mapVisibleCols);
                    excelTable.setRefresh(true);
                    String sheetName = "Year " + list.get(i);
                    ForecastUI.EXCEL_CLOSE = true;
                    if (i == 0) {
                        excel = new CustomExcelNM(new ExtCustomTableHolder(excelTable), sheetName, Constant.DISCOUNT_PROJECTION_LABEL, "Discount_Projection.xls", false, formatter);
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
                excel = new CustomExcelNM(new ExtCustomTableHolder(excelTable), Constant.DISCOUNT_PROJECTION_LABEL, Constant.DISCOUNT_PROJECTION_LABEL, "Discount_Projection.xls", false, formatter);
                excel.export();
            }
        } catch (Exception e) {            
            LOGGER.error(e);
        }
        LOGGER.debug("excel ends");
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

            List<String> customViewDetails = new ArrayList<>();
            String temphierarchyIndicator = hierarchyIndicator;
            boolean isCustomHierarchy = Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equals(hierarchyIndicator);
            if (isCustomHierarchy) {
                String customerLevelNo;
                String productLevelNo;
                Leveldto levelDto = CommonLogic.getNextLevel(treeLevelNo, currentHierarchy);
                temphierarchyIndicator = levelDto.getHierarchyIndicator();
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(temphierarchyIndicator)) {
                    customerLevelNo = String.valueOf(treeLevelNo);
                    productLevelNo = Constant.PERCENT;
                } else {
                    customerLevelNo = Constant.PERCENT;
                    productLevelNo = String.valueOf(treeLevelNo);
                }

                LOGGER.debug(" EXCEL P- Custom hierarchy --- \n customId " + customId);
                LOGGER.debug(" EXCEL P- customerLevelNo " + customerLevelNo);
                LOGGER.debug(" EXCEL P- customerHierarchyNo");
                LOGGER.debug(" EXCEL P- productLevelNo " + productLevelNo);
                LOGGER.debug(" EXCEL P- productHierarchyNo");
                customViewDetails.add(StringUtils.EMPTY + customId);
                customViewDetails.add(customerLevelNo);
                customViewDetails.add(StringUtils.EMPTY);
                customViewDetails.add(productLevelNo);
                customViewDetails.add(StringUtils.EMPTY);
                customViewDetails.add(session.getCustRelationshipBuilderSid());
                customViewDetails.add(session.getProdRelationshipBuilderSid());
            }
            String relationshipBuilderSid = getRelationshipBuilderSid();
            List list = logic.getDiscountProjection(session, projectionSelection.getFrequency(), startAndEndPeriods,
                    projectionSelection.getHistory(), temphierarchyIndicator, projectionSelection.getProjectionOrder(), userGroup,
                    PROGRAM.getConstant().equals(level.getValue()), discountToBeLoaded, projectionSelection.getYear(),
                    customDetailsList, true, isCustomHierarchy, rightHeader, 0, NumericConstants.THOUSAND, false, false, customViewDetails, false, false, StringUtils.EMPTY,
                    relationshipBuilderSid, false, Collections.EMPTY_LIST, false, StringUtils.EMPTY, StringUtils.EMPTY, Collections.EMPTY_LIST,
                    Collections.EMPTY_MAP, projectionSelection.getForecastConfigPeriods());
            loadDataToContainer(list, null, true);
        } catch (Exception e) {
            LOGGER.error(e);
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
            LOGGER.error(e);

        }
        LOGGER.debug("Ended loadDataToContainer");
    }

    public void addLowerLevelsForExport(DiscountProjectionDTO dto) {
        LOGGER.debug("Inside addLowerLevelsForExport");
        try {

            int treeLevelNo = 0;
            String temphierarchyIndicator = hierarchyIndicator;
            boolean isCustomHierarchy = Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equals(hierarchyIndicator);
            if (isCustomHierarchy) {
                treeLevelNo = dto.getTreeLevelNo() + 1;
            } else {
                treeLevelNo = dto.getLevelNo() + 1;
            }

            LOGGER.debug(" dto.getLevelNo() " + dto.getLevelNo());
            List customDetailsList = new ArrayList();

            List<String> discountToBeLoaded;
            if (!programSelectionList.isEmpty()) {
                discountToBeLoaded = programSelectionList;
            } else {
                discountToBeLoaded = discountProgramsList;
            }

            List<String> customViewDetails = new ArrayList<>();
            if (isCustomHierarchy) {
                String customerLevelNo;
                String productLevelNo;
                String customerHierarchyNo;
                String productHierachyNo;
                Leveldto levelDto = CommonLogic.getNextLevel(treeLevelNo, currentHierarchy);
                temphierarchyIndicator = levelDto.getHierarchyIndicator();

                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                    customerHierarchyNo = dto.getHierarchyNo();
                    productHierachyNo = dto.getProductHierarchyNo();
                } else {
                    customerHierarchyNo = dto.getCustomerHierarchyNo();
                    productHierachyNo = dto.getHierarchyNo();
                }

                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(temphierarchyIndicator)) {
                    customerLevelNo = String.valueOf(dto.getTreeLevelNo() + 1);
                    productLevelNo = Constant.PERCENT;
                } else {
                    customerLevelNo = Constant.PERCENT;
                    productLevelNo = String.valueOf(dto.getTreeLevelNo() + 1);
                }

                LOGGER.debug(" EXCEL - Custom hierarchy --- \n customId " + customId);
                LOGGER.debug(" EXCEL - customerLevelNo " + customerLevelNo);
                LOGGER.debug(" EXCEL - customerHierarchyNo " + customerHierarchyNo);
                LOGGER.debug(" EXCEL - productLevelNo " + productLevelNo);
                LOGGER.debug(" EXCEL - productHierarchyNo " + productHierachyNo);
                customViewDetails.add(StringUtils.EMPTY + customId);
                customViewDetails.add(customerLevelNo);
                customViewDetails.add(customerHierarchyNo);
                customViewDetails.add(productLevelNo);
                customViewDetails.add(productHierachyNo);
                customViewDetails.add(session.getCustRelationshipBuilderSid());
                customViewDetails.add(session.getProdRelationshipBuilderSid());

                customDetailsList.add(dto.getLevelNo() + 1);
                customDetailsList.add(StringUtils.EMPTY);
                customDetailsList.add(treeLevelNo);
            } else {
                customDetailsList.add(dto.getLevelNo() + 1);
                customDetailsList.add(dto.getHierarchyNo());
                customDetailsList.add(treeLevelNo);
            }
            String relationshipBuilderSid = getRelationshipBuilderSid();
            List levelList = logic.getDiscountProjection(session, projectionSelection.getFrequency(), startAndEndPeriods,
                    projectionSelection.getHistory(), temphierarchyIndicator, projectionSelection.getProjectionOrder(), userGroup,
                    PROGRAM.getConstant().equals(level.getValue()), discountToBeLoaded, projectionSelection.getYear(),
                    customDetailsList, true, isCustomHierarchy, rightHeader, 0, NumericConstants.THOUSAND, false, false, customViewDetails, false, false, StringUtils.EMPTY,
                    relationshipBuilderSid, false, Collections.EMPTY_LIST, false, StringUtils.EMPTY, StringUtils.EMPTY, Collections.EMPTY_LIST,
                    Collections.EMPTY_MAP, projectionSelection.getForecastConfigPeriods());
            loadDataToContainer(levelList, dto, true);
            excelTable.setCollapsed(dto, false);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Exit addLowerLevelsForExport");
    }

    @UiHandler("programSelectionLookup")
    public void programSelectionLookupClickLogic(Button.ClickEvent event) {

        LOGGER.debug("Enters the programSelectionLookupClickLogic");

        final DiscountSelection discountSelectionLookupWindow = new DiscountSelection(session, discountProgramsList, PROGRAM.getConstant().equals(level.getValue()));
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
                customizeDataForDDLB(selectedDiscountList,programSelection);
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
                String rsValue=str.contains("~")?str.split("~")[0]:str;
                comboBox.addItem(str);
                comboBox.setItemCaption(str, rsValue);
                comboBox.select(SELECT_ONE.getConstant());
            }
        }
    }

    @Override
    protected void generateBtnClickLogic(Boolean isGenerate) {
        isGrowthUpdatedManually = false;
        isRateUpdatedManually = false;
        isRPUUpdatedManually = false;
        isAmountUpdatedManually = false;
        saveList.clear();
        if (((Collection) variables.getValue()).isEmpty()) {
            AbstractNotificationUtils.getErrorNotification("No variables were selected", "No variables were selected. Please select at least one variable and try again.");
        } else {
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
        List<String> discountToBeLoaded;

        if (!programSelectionList.isEmpty()) {
            discountToBeLoaded = programSelectionList;
        } else {
            discountToBeLoaded = discountProgramsList;
        }

        if (discountToBeLoaded == null || discountToBeLoaded.isEmpty()) {
            NotificationUtils.getErrorNotification("No Discounts selected", "Please select some discounts from program selection lookup");
        } else if (frequencyDdlb.getValue() == null || frequencyDdlb.getValue().equals(SELECT_ONE.getConstant())) {
            NotificationUtils.getErrorNotification("No Frequency selected", "Please select any frequency");
        } else if (historyDdlb.getValue() == null || historyDdlb.getValue().equals(SELECT_ONE.getConstant())) {
            NotificationUtils.getErrorNotification("No History selected", "Please select any history");
        } else {
            tableLogic.clearAll();
            tableLogic.setRefresh(false);//will become true once setcurrentpage completed
            checkBoxMap.clear();
            radioMap.clear();
            tableLogic.sinkItemPerPageWithPageLength(false);
            boolean isFrequencyChange = !String.valueOf(projectionSelection.getFrequency()).equals(String.valueOf(frequencyDdlb.getValue()));
            createSelectionDto();
            createRightHeader();
            resultBeanContainer.setColumnProperties(rightHeader.getProperties());
            if (isGenerate) {
                resultsTable.constructRightFreeze(isGenerate);
            }
            resultsTable.getRightFreezeAsTable().setContainerDataSource(tableLogic.getContainerDataSource());
            configureRightTable();
            configureLeftTable();
            formatTableData();
            loadScreenBasedOnGeneratedTable(isFrequencyChange);
            loadDataInTable();//setcurrentpage will be called
            isListviewGenerated = true;
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
            if (String.valueOf(propertyId).contains("ActualAmount") || String.valueOf(propertyId).contains("ProjectedAmount")) {
                resultsTable.getRightFreezeAsTable().setConverter(propertyId, priceFormat);
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
        if (!programSelectionList.isEmpty()) {
            discountToBeLoaded = programSelectionList;
        } else {
            discountToBeLoaded = discountProgramsList;
        }

        int levelNo = getStartLevelNo();
        String relationshipBuilderSid = getRelationshipBuilderSid();
        tableLogic.setDataLoad(true);
        tableLogic.setDiscountVariablesForLogic(session, projectionSelection, startAndEndPeriods, PROGRAM.getConstant().equals(level.getValue()),
                discountToBeLoaded, levelNo, true, rightHeader, hierarchyIndicator, userGroup, currentHierarchy,
                Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equals(hierarchyIndicator), customId, relationshipBuilderSid);

        LOGGER.debug(" User Id --" + session.getUserId());
        LOGGER.debug(" Session Id --" + session.getSessionId());
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
            if ((Constant.DISCOUNT_RATE_LABEL).equals(fieldValue)) {

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
            if (projectionSelection.getProjectionOrder().equals(DESCENDING.getConstant()) && (PRODUCT.getConstant().equalsIgnoreCase(String.valueOf(view.getValue())))) {
                Collections.reverse(rightHeader.getDoubleHeaders());
            }
            String startFreq = String.valueOf(rightHeader.getDoubleHeaders().get(0));
            String endFreq = String.valueOf(rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1));

            String startYearValue = startFreq.substring(startFreq.length() - NumericConstants.FOUR);
            String endYearValue = endFreq.substring(endFreq.length() - NumericConstants.FOUR);

            int startYear = isInteger(startYearValue) ? Integer.valueOf(startYearValue) : 0;
            int endYear = isInteger(endYearValue) ? Integer.valueOf(endYearValue) : 0;

            startAndEndPeriods.clear();
            if (!projectionSelection.getFrequency().equals(MONTHLY.getConstant())) {
                String startFreqNoValue = startFreq.substring(1, NumericConstants.TWO);
                String endFreqNoValue = endFreq.substring(1, NumericConstants.TWO);
                int startFreqNo = isInteger(startFreqNoValue) ? Integer.valueOf(startFreqNoValue) : 0;
                int endFreqNo = isInteger(endFreqNoValue) ? Integer.valueOf(endFreqNoValue) : 0;
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
                LOGGER.debug(" startMonthValue " + startMonthValue);
                LOGGER.debug(" endMonthValue " + endMonthValue);
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

        } catch (Exception e) {
            LOGGER.debug(e);
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
        rightTable.setDoubleHeaderColumnHeaders(doubleColumnHeader.toArray(new String[rightHeader.getDoubleHeaders().size()]));
        rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());

        rightTable.setTripleHeaderVisibleColumns(tripleVisibleColumn.toArray());
        rightTable.setTripleHeaderColumnHeaders(tripleColumnHeader.toArray(new String[rightHeader.getTripleHeader().size()]));
        rightTable.setTripleHeaderMap(rightHeader.getTripleHeaderMaps());

        for (Object columns : rightHeader.getDoubleColumns()) {
            rightTable.setDoubleHeaderColumnCheckBox(columns, true);
            rightTable.setDoubleHeaderColumnCheckBoxDisable(columns, ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
        }
        for (Object columns : rightHeader.getTripleColumns()) {
            rightTable.setTripleHeaderColumnCheckBox(columns, true);
            rightTable.setTripleHeaderColumnCheckBoxDisable(columns, ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
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
                    checkBoxMap.put(event.getPropertyId(), true);
                } else {
                    checkBoxMap.remove(event.getPropertyId());
                }
                doubleHeaderCheckListener(event.getPropertyId(), event.isChecked());
                String arr[] = rightTable.getColumnRadioButtonArray((String) event.getPropertyId());
                    if (arr != null) {
                        for (String a : arr) {
                            rightTable.setColumnRadioButtonDisable(a, !event.isChecked());
                        }
                    }
                    if (event.isChecked()) {
                        if (rightTable.getColumnRadioButtonValue((String) event.getPropertyId()) != null) {
                            radioMap.put(event.getPropertyId(), rightTable.getColumnRadioButtonValue((String) event.getPropertyId()));
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
            public Field<?> createField(final Container container, final Object itemId,
                    final Object propertyId, Component uiContext) {
                if (!ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
                    try {
                        String property = String.valueOf(propertyId);

                        String fieldvalue = String.valueOf(container.getContainerProperty(itemId, propertyId).getValue());
                        if ((rightHeader.getSingleColumns().contains(property)) && (Constant.NULL.equals(fieldvalue))) {
                                DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                                dto.addStringProperties(propertyId, Constant.DASH);
                                container.getContainerProperty(itemId, propertyId).setValue(Constant.DASH);
                            }

                        if (rightHeader.getSingleProjectedColumns().contains(property)) {

                            TextField textField = new TextField();
                            textField.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
                            textField.addStyleName(Constant.TXT_RIGHT_ALIGN);
                            textField.setData(new Object[]{itemId,propertyId,"right"});
                            if (property.contains(Constant.PROJECTED_RATE) || property.contains(Constant.GROWTH)) {
                                textField.setConverter(percentFormat);

                            } else {
                                textField.setConverter(priceFormat);
                            }
                            textField.addFocusListener(focusListener);

                            textField.addBlurListener(blurListener);
                            return textField;
                        }
                        if (rightHeader.getSingleForecastColumns().contains(property)) {
                            TextField textField = new TextField();
                            textField.setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
                            textField.addStyleName(Constant.TXT_RIGHT_ALIGN);
                            if (property.contains(Constant.PROJECTED_RATE) || property.contains(Constant.GROWTH)) {
                                textField.setConverter(percentFormat);
                            } else {
                                textField.setConverter(priceFormat);
                            }
                            textField.setEnabled(false);
                            return textField;
                        }
                    } catch (Exception e) {
                        LOGGER.error(e);

                    }
                }
                return null;
            }
        });
         for (Object obj : rightHeader.getDoubleHistoryColumns()) {
            Object single[] = rightHeader.getDoubleHeaderMaps().get(obj);
            for (Object ob : single) {
                rightTable.setColumnRadioButtonValue((String) obj, single[0]);
                rightTable.setColumnRadioButtonDisable(ob, true);
            }
        }
        for (Object obj : rightHeader.getDoubleProjectedColumns()) {
            Object single[] = rightHeader.getDoubleHeaderMaps().get(obj);
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
        if (isListviewGenerated) {
            LOGGER.debug(" Discount generated ");
            boolean isCustomHierarchy = Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equals(hierarchyIndicator);
            boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
            logic.saveDiscountProjectionListView(session, projectionSelection.getFrequency(), saveList, customId, isProgram, isCustomHierarchy);
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
        try {
            map.put(Constant.FREQUENCY, projectionSelection.getFrequency());
            map.put(Constant.HISTORY, projectionSelection.getHistory());
            map.put(Constant.PROJECTION_PERIOD_ORDER_LABEL, projectionSelection.getProjectionOrder());
            map.put(Constant.ACTUALS_PROJECTIONS, projectionSelection.getActualsOrProjections());
            map.put(Constant.LEVEL_LABEL, String.valueOf(level.getValue()));
            map.put(Constant.SELECTED_DISCOUNTS, getDiscountRSSids(discountProgramsList).get(0));
            map.put("SelectedDiscountsSids", getDiscountRSSids(discountProgramsList).get(1));
            map.put("selectedDiscountNo", CommonUtils.CollectionToString(discountNoList, false));
            map.put("Program Selection Ddlb", String.valueOf(programSelection.getValue()));
            map.put(Constant.YEAR_SELECTION_DDLB, projectionSelection.getYear());
            CommonLogic.saveProjectionSelection(map, session.getProjectionId(), TAB_DISCOUNT_PROJECTION.getConstant());
        } catch (Exception e) {
            LOGGER.error(e);
        }

        LOGGER.debug(" Ending Save Selection ");
    }

    public List getDiscountRSSids(List<String> discountProgramsList) {
        List<String> rawListRSSids = new ArrayList();
        String discountPgmName = StringUtils.EMPTY;
        String discountPgmSids = StringUtils.EMPTY;
        for (String rsSids : discountProgramsList) {
            discountPgmName += (rsSids.contains("~") ? rsSids.split("~")[0] : rsSids) + ",";
            discountPgmSids += (rsSids.contains("~") ? StringUtils.EMPTY + rsSids.split("~")[1] : rsSids) + ",";
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
            discountPgmName += (rsSids.contains("~") ? rsSids.split("~")[0] : rsSids) + ",";
            discountPgmSids += (rsSids.contains("~") ? StringUtils.EMPTY + rsSids.split("~")[1] : rsSids) + ",";
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
            if (isListviewGenerated) {
                saveDiscountProjectionListview();
                if (toBeRefreshed) {
                    refreshTableData(getManualEntryRefreshHierarachyNo());
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
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
                rightTable.setDoubleHeaderColumnCheckBoxDisable(columns, ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
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
        String discountName = PROGRAM_CATEGORY.getConstant().equals(String.valueOf(level.getValue())) ? rightTable.getTripleHeaderColumnHeader(discountPropertyId) : discountPropertyId;

        Map<String, List<String>> checkedDoubleHeaders = tripleHeaderForCheckedDoubleHeader.get(discountName);

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
        int year = isInteger(yearValue) ? Integer.valueOf(yearValue) : 0;
        int historyNum = 0;
        try {
            if (year == 0 && !yearValue.equals(ALL.getConstant())) {
                if (freq.equals(QUARTERLY.getConstant())) {
                    historyNum = Integer.valueOf(hist.replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                } else if (freq.equals(SEMI_ANNUALLY.getConstant())) {
                    historyNum = Integer.valueOf(hist.replace(Constant.SEMI_ANNUALY, StringUtils.EMPTY).trim());
                } else if (freq.equals(MONTHLY.getConstant())) {
                    historyNum = Integer.valueOf(hist.replace("Month", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                } else if (freq.equals(ANNUALLY.getConstant())) {
                    historyNum = Integer.valueOf(hist.replace(Constant.YEAR, StringUtils.EMPTY).trim());
                }
            } else {
                historyNum = historyDdlb.getItemIds().size();
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }

        projectionSelection.setForecastDTO(session.getForecastDTO());
        projectionSelection.setFrequency(freq);
        projectionSelection.setHistory(hist);
        projectionSelection.setHistoryNum(historyNum);
        projectionSelection.setProjectionNum(CommonUtils.getProjectionNumber(projectionSelection.getFrequency(), session));
        projectionSelection.setActualsOrProjections(String.valueOf(actualsProjs.getValue()));
        projectionSelection.setProjectionOrder(String.valueOf(periodOrder.getValue()));
        projectionSelection.setYear(yearValue);

        projectionSelection.setProjectionId(session.getProjectionId());
        projectionSelection.setUserId(isInteger(session.getUserId()) ? Integer.valueOf(session.getUserId()) : 0);
        projectionSelection.setSessionId(isInteger(session.getSessionId()) ? Integer.valueOf(session.getSessionId()) : 0);
        projectionSelection.setCustomId(customId);
        projectionSelection.setForecastConfigPeriods(CommonUtils.prepareProjectionPeriodList(projectionSelection));
        List<String> discountToBeLoaded;
        if (!programSelectionList.isEmpty()) {
            discountToBeLoaded = programSelectionList;
        } else {
            discountToBeLoaded = discountProgramsList;
        }

        projectionSelection.setDiscountProgramsList(new ArrayList<>(discountToBeLoaded));
        Collection c = (Collection) variables.getValue();
        List<String> l = new ArrayList();
        for (Object s : c) {
            l.add(String.valueOf(s));
        }
        projectionSelection.setdPVariablesList(l);
        if (l.isEmpty()) {
            projectionSelection.setdPVariablesList(Arrays.asList(new String[]{DISCOUNT_RATE.getConstant(), REBATE_PER_UNIT.getConstant(), DISCOUNT_AMT.getConstant()}));

        }
        LOGGER.debug(" Ending Selection Dto Creation");
    }

    private void createRightHeader() {
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
                variables.select(DISCOUNT_RATE.getConstant());
                variables.select(REBATE_PER_UNIT.getConstant());
                variables.select(DISCOUNT_AMT.getConstant());
                variables.unselect(GROWTH.getConstant());
            }
        }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the ‘Discount Projection Selections’?");
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
            int levelNo = Integer.valueOf(levelNoString);
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
            AbstractNotificationUtils.getErrorNotification("No Level Selected", "Please select a Level from the drop down.");
        }
    }

    @Override
    protected void levelFilterValueChangeLogic(Property.ValueChangeEvent event) {
        LOGGER.debug("levelFilterDdlbvalueChangeListener initiated ");
            if ((enableLevelFilterListener) && (event.getProperty() != null && !Constant.NULL.equals(String.valueOf(event.getProperty().getValue())))) {
                LOGGER.debug(" event value " + event.getProperty().getValue());
                resetTableData();
                String levelNumber = String.valueOf(event.getProperty().getValue());
                int levelNo = isInteger(levelNumber) ? Integer.valueOf(levelNumber) : 0;
                if (!levelNumber.startsWith(Constant.DASH)) {
                    tableLogic.setChildrenAllowed(false);
                    tableLogic.filterLevels(levelNo);

                    //To reset to default
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
        LOGGER.debug(" fieldValue " + fieldValue);
        if (ENABLE.getConstant().equals(fieldValue)) {
            fieldDdlb.setReadOnly(false);
            startPeriod.setReadOnly(false);
            value.setReadOnly(false);
            endPeriod.setReadOnly(false);
            populateBtn.setReadOnly(false);
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
        LOGGER.debug(" Start period  " + fieldValue);
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
        LOGGER.debug(" startPeriod " + startPeriod);
        for (String period : periods) {
            if (!period.equals(startPeriod) && end) {
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

        currentHierarchy = session.getCustomHierarchyMap().get(customId);
        LOGGER.debug(" customId  " + customId);
        LOGGER.debug(" currentHierarchy " + currentHierarchy.size());
        if (customId != 0) {
            viewChangeGenerate();
        } else {
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
        return discountNamesList;
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
            final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermissionForNm(String.valueOf(VaadinSession.getCurrent().getAttribute("businessRoleIds")), getCommercialConstant() + "," + UISecurityUtil.DISCOUNT_PROJECTION);
            if (!(functionPsHM.get(CommonUtils.GENERATE_BUTTON) != null && ((AppPermission) functionPsHM.get(CommonUtils.GENERATE_BUTTON)).isFunctionFlag())) {
                generateBtn.setVisible(false);
                populateBtn.setVisible(false);
                expandBtn.setVisible(false);
                collapseBtn.setVisible(false);
                newBtn.setVisible(false);
                editBtn.setVisible(false);
            }
            if (functionPsHM.get(CommonUtils.ALT_HISTORY_BTN) != null && ((AppPermission) functionPsHM.get(CommonUtils.ALT_HISTORY_BTN)).isFunctionFlag()) {
                altHistoryBtn.setVisible(true);
            } else {
                altHistoryBtn.setVisible(false);
            }

            if (!(functionPsHM.get(CommonUtils.CALCULATE_BTN) != null && ((AppPermission) functionPsHM.get(CommonUtils.CALCULATE_BTN)).isFunctionFlag())) {
                calculateBtn.setVisible(false);
                adjustBtn.setVisible(false);
            }
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(NMDiscountProjection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(NMDiscountProjection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadDiscPeriods(BeanItemContainer<String> periodBean, ComboBox comboPeriod, List<String> projectedHeaders) {
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
            Map<String, List<String>> checkedDoubleHeaders = tripleHeaderForCheckedDoubleHeader.get(String.valueOf(key));
            if (checkedDoubleHeaders != null) {
                List<String> checkedHistoryList = checkedDoubleHeaders.get("H");
                List<String> checkedProjList = checkedDoubleHeaders.get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
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
                        if ((checkedHistoryList != null) && (i == 0 && checkedHistoryList.size() == i && checkedProjList1.isEmpty())) {
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
            Map<String, List<String>> checkedDoubleHeaders = tripleHeaderForCheckedDoubleHeader.get(d);
            for (String e : checkedDoubleHeaders.keySet()) {
                List a = checkedDoubleHeaders.get(e);
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
        LOGGER.debug("Inside get Quater with String" + str);
        int a[] = new int[NumericConstants.TWO];
        String[] splited = str.split("\\s+");
        a[0] = Integer.valueOf(new String(splited[0].replaceAll("[Q]+", StringUtils.EMPTY)));
        a[1] = Integer.valueOf(splited[1]);
        return a;
    }
   
    public boolean endDateValidation(String valueEnd) {
        LOGGER.debug("Inside End Date Validation");
        if (startPeriodForecastTab.getValue() != null) {
            if (frequencyDdlb.getValue().equals(MONTHLY.getConstant())) {
                int endMonth = CommonUtils.getIntegerForMonth(valueEnd.substring(0, NumericConstants.THREE));
                int startMonth = CommonUtils.getIntegerForMonth(startPeriodForecastTab.getValue().toString().trim().substring(0, NumericConstants.THREE));
                int endYear = Integer.valueOf(valueEnd.substring(NumericConstants.FOUR, NumericConstants.EIGHT));
                int startYear = Integer.valueOf(startPeriodForecastTab.getValue().toString().trim().substring(NumericConstants.FOUR, NumericConstants.EIGHT));

                if (startYear < endYear) {
                    return Boolean.TRUE;
                }
                if (startYear == endYear && startMonth <= endMonth) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
            if (!frequencyDdlb.getValue().equals(ANNUALLY.getConstant())) {
                String startValue = startPeriodForecastTab.getValue().toString().replace(" ", "~").trim();
                String endValue = valueEnd.replace(" ", "~").trim();
                String startValue1[] = startValue.split("~");
                String endValue1[] = endValue.split("~");
                if (Integer.valueOf(startValue1[1].trim()) > Integer.valueOf(endValue1[1].trim())) {
                    errorFlag = true;
                    return false;
                } else if ((Integer.valueOf(startValue1[1].trim())).equals(Integer.valueOf(endValue1[1].trim()))) {
                    startValue1[0] = startValue1[0].replace(Constant.Q, StringUtils.EMPTY).trim();
                    endValue1[0] = endValue1[0].replace(Constant.Q, StringUtils.EMPTY).trim();
                    if (Integer.valueOf(startValue1[0]) > Integer.valueOf(endValue1[0])) {
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

                if (Integer.valueOf(startValue) > Integer.valueOf(valueEnd)) {
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(Constant.NO_START_PERIOD_SELECTED, "Please select a Start Period.");
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
            if (checkBoxMap.get(radioMap.getIndex(i).getKey())!=null && Boolean.valueOf(checkBoxMap.get(radioMap.getIndex(i).getKey()).toString())) {
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
        Map<String, List<String>> checkedDoubleHeaders = null;
        for (Object key : tripleHeaderForCheckedDoubleHeader.keySet()) {
            checkedDoubleHeaders = tripleHeaderForCheckedDoubleHeader.get(String.valueOf(key));
        }
        if (checkedDoubleHeaders != null) {
            List<String> checkedHistoryList = checkedDoubleHeaders.get("H");
            List<String> checkedProjList = checkedDoubleHeaders.get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
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
                String tempYear = StringUtils.EMPTY, tempSubYear = StringUtils.EMPTY;
                for (int i = 0; i < overall.size(); i++) {
                    if (defval == NumericConstants.TWO || defval == NumericConstants.FOUR) {
                        tempYear = overall.get(i).toString().trim().substring(NumericConstants.TWO);
                        tempSubYear = (overall.get(i).toString().trim()).replace(tempYear, StringUtils.EMPTY).trim();
                    } else if (defval == NumericConstants.TWELVE) {
                        tempYear = overall.get(i).toString().trim().substring(NumericConstants.THREE);
                        String tmpSubYear = overall.get(i).toString().trim().replace(tempYear, StringUtils.EMPTY).trim();
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
                        year[i] = Integer.valueOf(fullYear.trim());
                    } else {
                        year[i] = Integer.valueOf(overall.get(i).toString().trim());
                    }

                }
                Arrays.sort(year);
                String startTempYear = StringUtils.EMPTY, startTempSubYear = StringUtils.EMPTY;
                String endTempYear = StringUtils.EMPTY, endTempSubYear = StringUtils.EMPTY;
                if (defval == NumericConstants.TWO || defval == NumericConstants.FOUR) {
                    startTempYear = startPeriod.toString().trim().substring(NumericConstants.TWO);
                    startTempSubYear = startPeriod.replace(startTempYear, StringUtils.EMPTY).trim();
                    endTempYear = endPeriod.toString().trim().substring(NumericConstants.TWO);
                    endTempSubYear = endPeriod.replace(endTempYear, StringUtils.EMPTY).trim();
                } else if (defval == NumericConstants.TWELVE) {
                    startTempYear = startPeriod.toString().toString().trim().substring(NumericConstants.THREE);
                    String startTmpSubYear = startPeriod.replace(startTempYear, StringUtils.EMPTY).trim();
                    startTempSubYear = monthMap.get(startTmpSubYear).toString();
                    endTempYear = endPeriod.toString().trim().substring(NumericConstants.THREE);
                    String endTmpSubYear = endPeriod.toString().replace(endTempYear, StringUtils.EMPTY).trim();
                    endTempSubYear = monthMap.get(endTmpSubYear).toString();
                } else if (defval == 1) {
                    startTempYear = startPeriod.trim();
                    endTempYear = endPeriod.toString().trim();
                }
                String subYear2 = StringUtils.EMPTY, subYear3 = StringUtils.EMPTY;
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
                int finStartPeriod = 0, finEndPeriod = 0;
                finStartPeriod = Integer.valueOf(startfullYear.trim());
                finEndPeriod = Integer.valueOf(endfullYear.trim());
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
        Map<String, List<String>> checkedDoubleHeaders = null;
        for (Object key : tripleHeaderForCheckedDoubleHeader.keySet()) {
            checkedDoubleHeaders = tripleHeaderForCheckedDoubleHeader.get(String.valueOf(key));
        }
        if (checkedDoubleHeaders != null) {
            List<String> checkedHistoryList = checkedDoubleHeaders.get("H");
            List<String> checkedProjList = checkedDoubleHeaders.get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
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
                        LOGGER.error(e);
                    }
                    return retval;
                }
            };
  
     Collections.sort(overall, dateCompare);
            if (!overall.isEmpty() && overall.size() == defval) {
                 String subYear1 = StringUtils.EMPTY;
                int[] year = new int[overall.size()];
                int[] Quarter = new int[overall.size()];
                String tempYear = StringUtils.EMPTY, tempSubYear = StringUtils.EMPTY;
                for (int i = 0; i < overall.size(); i++) {
                    if (defval == NumericConstants.TWO || defval == NumericConstants.FOUR) {
                        tempYear = overall.get(i).toString().trim().substring(NumericConstants.TWO);
                        tempSubYear = (overall.get(i).toString().trim()).replace(tempYear, StringUtils.EMPTY).trim();
                    } else if (defval == NumericConstants.TWELVE) {
                        tempYear = overall.get(i).toString().trim().substring(NumericConstants.THREE);
                        String tmpSubYear = overall.get(i).toString().trim().replace(tempYear, StringUtils.EMPTY).trim();
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
                    year[i] = Integer.valueOf(fullYear.trim());

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
                } else if(Integer.parseInt(subYear1) == NumericConstants.TWELVE){
                    retval = true;
                }else{
                    retval = false;
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
        int month = Integer.valueOf(strMonth);
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
        int intYear = Integer.valueOf(strYear);
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
                    tempYear = checkedProjList.get(i).toString().trim().substring(NumericConstants.TWO);
                    tempSubYear = (checkedProjList.get(i).toString().trim()).replace(tempYear, StringUtils.EMPTY).trim();
                } else if (defval == NumericConstants.TWELVE) {
                    tempYear = checkedProjList.get(i).toString().trim().substring(NumericConstants.THREE);
                    String tmpSubYear = checkedProjList.get(i).toString().trim().replace(tempYear, StringUtils.EMPTY).trim();
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
                year[i] = Integer.valueOf(fullYear.trim());
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
        double doubleVal = Double.valueOf(value);
        int freqDiv = getFrequencyDivision(projectionSelection.getFrequency());
        double finalValue = doubleVal / (ccpsCount * freqDiv);
        return finalValue;
    }
    /**
     * The Table Group Filter Ddlb value change.
     *
     * @param event the event
     */
    Property.ValueChangeListener tableGroupFilterDdlbValueChange = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            userGroup = String.valueOf(groupFilterDdlb.getValue());
            userGroup = String.valueOf(event.getProperty().getValue());
            if (Constant.NULL.equals(userGroup)) {
                userGroup = StringUtils.EMPTY;
            }
            userGroup = Constant.SHOW_ALL_GROUPS.equals(userGroup) ? StringUtils.EMPTY : userGroup;
            LOGGER.debug(" userGroup-in tableGroupFilterDdlbValueChange-" + userGroup);
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
                        generateListView(true);
                    } else if (Constants.CommonConstants.ACTION_GENERATE.getConstant().equalsIgnoreCase(session.getAction())) {
                        LOGGER.debug("Reset the temp table by deleting and inserting by proc");
                        logic1.deleteTempData(session, "DISC_DELETE_TEMP");
                        logic.callDiscountProjectionProcedure(session);
                        refreshTableData(getManualEntryRefreshHierarachyNo());
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }.getConfirmationMessage("Confirm List View Reset", "Are you sure you want to reset the list view to the last saved state?");

    }

    private boolean isCheckBoxProperty() {
        boolean propertyId = false;
        
        if (checkBoxMap.containsValue(true)) {
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
            boolean isFrequencyChange = !String.valueOf(projectionSelection.getFrequency()).equals(String.valueOf(frequencyDdlb.getValue()));
            createSelectionDto();
            createRightHeader();
            viewValueChangeLogic();
            resultBeanContainer.setColumnProperties(rightHeader.getProperties());
            resultsTable.constructRightFreeze(true);
            resultsTable.getRightFreezeAsTable().setContainerDataSource(tableLogic.getContainerDataSource());
            configureRightTable();
            configureLeftTable();
            tableLogic.setRefresh(Boolean.FALSE);
            loadScreenBasedOnGeneratedTable(isFrequencyChange);
            loadDataInTable();
            tableLogic.setRefresh(Boolean.FALSE); //As the row refresh will be set true during the load data.
            formatTableData();
            tableLogic.setRefresh(Boolean.TRUE);
            isListviewGenerated = true;
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
                String selectedRsName = resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeader(checkedDiscountsPropertyIds.get(0));

                rsModelSid = logic.getRsModelSid(selectedRsName);
                Set<Integer> totalCcp = new HashSet<>();
                if (CUSTOMER.getConstant().equals(String.valueOf(view.getValue()))) {

                    int numoFCustomers = getCheckedCustomercount(logic);

                    if (numoFCustomers > 0) {
                        String ccpIds = logic.getCCPList(logic.buildCCPListQuery(true, session.getProjectionId()), totalCcp,session);
                        totalccpCount = totalCcp.size();

                        if (isNotHavingActuals(logic, ccpIds, rsModelSid, totalCcp)) {

                            return true;

                        } else {

                            NotificationUtils.getAlertNotification("Invalid Record Selection", "The selected Customer and Dicount combination already having actuals.");
                            return false;
                        }

                    } else if (numoFCustomers == 0) {

                        NotificationUtils.getAlertNotification("No Customer Level  Selected", "Please select a Customer level for which  Alternate History to be imported.");
                        return false;

                    }
                } else if (PRODUCT.getConstant().equals(String.valueOf(view.getValue()))) {

                    int numOfNDC = getCheckedProductCount(logic);

                    if (numOfNDC == 1) {
                        String ccpIds = logic.getCCPList(logic.buildCCPListQuery(false, session.getProjectionId()), totalCcp,session);
                        totalccpCount = totalCcp.size();
                        if (isNotHavingActuals( logic, ccpIds, rsModelSid, totalCcp)) {

                            return true;

                        } else {

                            NotificationUtils.getAlertNotification("Invalid Record Selection", "The selected Item and Dicount combination already having actuals.");
                            return false;
                        }

                    } else if (numOfNDC > 0) {

                        NotificationUtils.getAlertNotification("No NDC Level Selected", "Please select a NDC level for which  Alternate History to be imported.");
                        return false;

                    }
                }

            } else {
                NotificationUtils.getAlertNotification("Multiple Discount Selected", "Please select a single discount for which  Alternate History to be imported.");
                return false;
            }

        } else {
            NotificationUtils.getAlertNotification("No Discount Selected", "Please select which discount Alternate History to be imported.");
            return false;

        }

        return true;
    }

    public boolean isNotHavingActuals(NMDiscountProjectionLogic logic, String ccpIds, int rsModelSid, Set<Integer> totalccp) {
        actualCCPs = logic.getZeroActualCCPList(logic.buildActualValidateQuery(ccpIds, rsModelSid), totalccp,session);
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
        return logic.getCCPCount(QueryUtil.replaceTableNames(logic.buildCCPCountQuery(true, session.getProjectionId()), session.getCurrentTableNames()));
    }

    public int getCheckedProductCount(final NMDiscountProjectionLogic logic) {
        return logic.getCCPCount(QueryUtil.replaceTableNames(logic.buildCCPCountQuery(false, session.getProjectionId()), session.getCurrentTableNames()));
    }

    /**
     * To get the Hierarchy No of Checked record for Refresh
     *
     * @param tableLevelNo
     * @return
     */
    private String getHierarchyNoForCheckedRecord(final Set<String> tableLevelNo) {
        String hierarchyNo = StringUtils.EMPTY;
        for (String tableTreeLevelNo : tableLevelNo) {

            Object itemId = tableLogic.getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                itemId = tableLogic.getExpandedTreeValues(tableTreeLevelNo);
            }
            if (itemId != null) {
                hierarchyNo += ((DiscountProjectionDTO) itemId).getHierarchyNo() + ",";
            }
        }
        return StringUtils.isEmpty(hierarchyNo) ? StringUtils.EMPTY : hierarchyNo.substring(0, hierarchyNo.length() - 1);
    }

    public boolean getSubmitFlag() {
        if (resultBeanContainer.getItemIds().size() > 0) {
            return true;
        } else {
            return false;
        }
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
    private void cumulativeCalculation(ProjectionSelectionDTO projectionSelection, String methodology, String tableName, List<String> checkedDiscountNames,String level) {
        try {
            Object[] procedureInputs = null;
            projectionSelection.setTabName(DISCOUNT_PROJ.getConstant());
            LOGGER.debug("PRC_GROWTH_CALCULATION--------------------------------------- ");
            String discountId = CommonUtils.CollectionToString(checkedDiscountNames, false);
            procedureInputs = new Object[]{projectionSelection.getProjectionId(), projectionSelection.getUserId(), projectionSelection.getSessionDTO().getSessionId(),projectionSelection.getTabName(), methodology, projectionSelection.getFrequency(),UiUtils.getDate(),level,projectionSelection.getFromDateDdlb(),projectionSelection.getToDateDdlb(),discountId};
             List<Object[]> list =CommonLogic.callProcedure("PRC_GROWTH_CALCULATION", procedureInputs);
            new CumulativeCalculationUtils(list, String.valueOf(projectionSelection.getUserId()),projectionSelection.getSessionDTO().getSessionId(),methodology,projectionSelection.getTabName(),tableName);
        } catch (Exception ex) {
           LOGGER.error(ex);
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
    public ExtTreeContainer<DiscountProjectionDTO> getResultBeanContainer() {
        return resultBeanContainer;
    }
    
    
}
