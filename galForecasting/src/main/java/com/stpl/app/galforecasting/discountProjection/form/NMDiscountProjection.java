/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.discountProjection.form;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.abstractforecast.ForecastDiscountProjection;
import com.stpl.app.galforecasting.discountProjection.logic.NMDiscountProjectionLogic;
import com.stpl.app.galforecasting.discountProjection.logic.tableLogic.NMDiscountTableLoadLogic;
import com.stpl.app.galforecasting.dto.DiscountProjectionDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.dto.SaveDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.logic.DiscountProjectionLogic;
import com.stpl.app.galforecasting.logic.NonMandatedLogic;
import com.stpl.app.galforecasting.projectionvariance.logic.NMProjectionVarianceLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.ui.form.lookups.AlternateHistory;
import com.stpl.app.galforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.galforecasting.ui.form.lookups.DiscountSelection;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import static com.stpl.app.galforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.CustomExcelNM;
import com.stpl.app.galforecasting.utils.HeaderUtils;
import com.stpl.app.galforecasting.utils.NotificationUtils;
import com.stpl.app.galforecasting.utils.TabNameUtil;
import com.stpl.app.galforecasting.utils.UISecurityUtil;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
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
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
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
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author shyam.d
 */
public class NMDiscountProjection extends ForecastDiscountProjection {

    final StplSecurity stplSecurity = new StplSecurity();
    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
    CustomTreeContainer<DiscountProjectionDTO> resultBeanContainer = new CustomTreeContainer<DiscountProjectionDTO>(DiscountProjectionDTO.class);
    Map<String, String> manualEntryMap = new HashMap<String, String>();
    /* Discount Bean */
    protected BeanItemContainer<String> programBean = new BeanItemContainer<String>(String.class);
    /* To store the current hierarchy */
    List<Leveldto> currentHierarchy = new ArrayList<Leveldto>();
    /* To enable or disable level filter listener */
    boolean enableLevelFilterListener = true;
    /* The bean used to load the Mass Update - value Ddlb */
    BeanItemContainer<String> valueDdlbBean = new BeanItemContainer<String>(String.class);
    /* To hold the selected discounts in program selection lookup */
    List<String> discountProgramsList = new LinkedList<String>();
    /* To hold the selected program from the program selection combo box */
    List<String> programSelectionList = new ArrayList<String>();
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
    List<String> discountNamesList = new ArrayList<String>();
    /* Discount No to be used in Projection results */
    List<String> discountNoList = new ArrayList<String>();

    /* Start and End Periods to be loaded */
    List<Integer> startAndEndPeriods = new ArrayList<Integer>();
    /* Data Format Converter */
    DataFormatConverter percentFormat = new DataFormatConverter("#,##0.000", DataFormatConverter.INDICATOR_PERCENT);
    DataFormatConverter priceFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_DOLLAR);
    /**
     * To store the details of the checked double header with its corresponding
     * Triple header and History, Projected values
     */
    Map<String, Map<String, List<String>>> tripleHeaderForCheckedDoubleHeader = new HashMap<String, Map<String, List<String>>>();
    /* To store the name of the discounts selected in the Triple header */
    List<Object> checkedDiscountsPropertyIds = new ArrayList<Object>();
    /* List to have the items to be saved */
    List<SaveDTO> saveList = new ArrayList<SaveDTO>();
    /* To store the hierarchy numbers to refresh in table */
    Set<String> refreshTableHierarchySet = new HashSet<String>();
    /* To store the custom View */
    List<CustomViewMaster> customViewList = new ArrayList<CustomViewMaster>();
    /* The Excel container */
    CustomTreeContainer<DiscountProjectionDTO> excelContainer = new CustomTreeContainer<DiscountProjectionDTO>(DiscountProjectionDTO.class);
    boolean errorFlag = false;
//    @UiField("variables")
//    public OptionGroup variables;

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
//    @UiField("actualsProjs")
//    public OptionGroup actualsProjs;
    Set<String> tableHirarechyNos = new HashSet<String>();
    DataFormatConverter dollarFormat = new DataFormatConverter("###,###,##0", DataFormatConverter.INDICATOR_DOLLAR);
    List<String> checkedList;

    String calcBase = StringUtils.EMPTY;
    private Map<Object, String> radioMap = new HashMap<>();
    private Map<Object, Boolean> checkBoxMap = new HashMap<>();
    private int baselineFlag = 0;
    private int ccpsCount = 1;
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
    List<String> aHselectedDiscounts = new ArrayList<String>();
    String actualCCPs = StringUtils.EMPTY;
    private int rsModelSid = 0;
    int totalccpCount = 0;

//    public  Label frequency =new Label();
//    public ComboBox frequencyDdlb=new ComboBox();
//    public Label actualsProjections =new Label();
//    public OptionGroup actualsProjs=new OptionGroup();
//    public Label projectionPeriodOrder =new Label();
//    public  OptionGroup periodOrder=new OptionGroup();
//    public Label history =new Label();
//    public  ComboBox historyDdlb=new ComboBox();
//    public Label variablesLb =new Label();
//    public  OptionGroup variables=new OptionGroup();
//    public Label projTypeLb =new Label();
//    public OptionGroup projType=new OptionGroup();
//    public Label discTypeLb =new Label();
//    public  OptionGroup discType=new OptionGroup();
    public NMDiscountProjection(SessionDTO session, String screenName) throws Exception {
        super(session, screenName);
        tableLogic = new NMDiscountTableLoadLogic(this);
        tableLogic.setTempPageLength(10);
        tableLogic.setDataLoad(false);
        resultsTable = new FreezePagedTreeTable(tableLogic);
        tableLogic.setPageLength(20); // To be done before creating table       
        tableLogic.sinkItemPerPageWithPageLength(false);
        securityForButton();
        addPropertyValueChangeListeners(frequencyDdlb, viewDdlb, view, adjprograms, adjperiods, massCheck, startPeriod, levelFilterDdlb);
//        configureFeildsForNm();
    }

    /**
     * To get the content of this tab from Non Mandated form
     *
     * @return
     */
    public void getContent() {
        LOGGER.info("Inside getContent " + session.getAction());
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
        return Constant.THREE;
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
        frequency.setCaption("Frequency");
        frequency.setWidth("80px");
        frequencyDdlb.setImmediate(true);
        actualsProjections.setCaption("Actuals/Projections:");
        actualsProjections.setWidth("127px");
        actualsProjs.setEnabled(true);
        actualsProjs.addStyleName("horizontal");
        projectionPeriodOrder.setCaption("Projection Period Order:");
        projectionPeriodOrder.setWidth("165px");
        periodOrder.setEnabled(true);
        periodOrder.setStyleName("horizontal");
        history.setCaption("History:");
        history.setStyleName("labelresultalign");
        historyDdlb.setEnabled(true);
        historyDdlb.setStyleName("comboxsize");
        historyDdlb.setNullSelectionAllowed(true);
        variablesLb.setCaption("Variables:");
        variablesLb.setStyleName("labelresultalign");
        variables.setMultiSelect(true);
        variables.setEnabled(true);
        variables.setStyleName("horizontal");
        projTypeLb.setCaption("ProjectionType:");
        projTypeLb.setWidth("110");
        projType.setImmediate(true);
        projType.setEnabled(true);
        discTypeLb.setCaption("Discount Type:");
        discTypeLb.setWidth("110");
        discType.setMultiSelect(false);
        discType.setImmediate(true);
        discType.setEnabled(true);
        gridlay.addComponent(frequency);
        gridlay.addComponent(frequencyDdlb);
        gridlay.addComponent(actualsProjections);
        gridlay.addComponent(actualsProjs);
        gridlay.addComponent(projectionPeriodOrder);
        gridlay.addComponent(periodOrder);
        gridlay.addComponent(history, 0, 1, 0, 1);
        gridlay.addComponent(historyDdlb, 1, 1, 1, 1);
        gridlay.addComponent(variablesLb, 2, 1, 2, 1);
        gridlay.addComponent(variables, 3, 1, 5, 1);
        fieldDdlb.addItem(SELECT_ONE.getConstant());
        fieldDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        fieldDdlb.select(SELECT_ONE.getConstant());
        fieldDdlb.addItem(Constant.GROUPFCAPS);
        fieldDdlb.addItem("Discount Rate");
        fieldDdlb.addItem("RPU");
        fieldDdlb.addItem("Discount Amount");
        fieldDdlb.addItem(Constant.GROWTH);
        massCheck.select(ENABLE.getConstant());
        massCheck.setVisible(false);

        fieldDdlb.setEnabled(true);
        populateBtn.setEnabled(true);
        calculateBtn.setEnabled(true);

        groupFilterDdlb.setVisible(false);
        groupFilterLb.setVisible(false);
//        groupFilterDdlb.setVisible(false);
        fieldDdlb.setData("fieldDdlb");
        fieldDdlb.addValueChangeListener(propertyVlaueChangeListener);

        programSelectionLookup.addStyleName(Reindeer.BUTTON_LINK);

        programSelection.setContainerDataSource(programBean);
        programSelection.addItem(SELECT_ONE.getConstant());
        programSelection.setNullSelectionAllowed(true);
        programSelection.setNullSelectionItemId(SELECT_ONE.getConstant());
        programSelection.setValue(SELECT_ONE.getConstant());

        level.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                programBean.removeAllItems();
                programSelection.addItem(SELECT_ONE.getConstant());
                programSelection.setNullSelectionAllowed(true);
                programSelection.setNullSelectionItemId(SELECT_ONE.getConstant());
                programSelection.setValue(null);
                discountProgramsList.clear();
                programSelectionList.clear();
            }
        });

        final BeanItemContainer<String> yearBean = new BeanItemContainer<String>(String.class);
        yearSelection.setNullSelectionAllowed(true);
        yearSelection.setNullSelectionItemId(SELECT_ONE.getConstant());
        yearBean.addAll(loadYearSelection());
        yearSelection.setContainerDataSource(yearBean);

        allocMethodology.addItem(SELECT_ONE.getConstant());
        allocMethodology.setNullSelectionItemId(SELECT_ONE.getConstant());

        // The following should be changed in DB procedure if changed below
        allocMethodology.addItem("Historical % of Business");
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
                PER_INVENTORY_WITHDRAW.getConstant()
                ,Constant.PERC_OF_ADJUSTED_DEMAND
        );

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

        variables.addStyleName("horizontal");
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

        /* To load the Customer hierarchy initially */
        int hierarchyLevelNo = isInteger(session.getCustomerLevelNumber()) ? Integer.valueOf(session.getCustomerLevelNumber()) : 0;
        currentHierarchy = CommonLogic.getCustomerHierarchy(session.getProjectionId(), hierarchyLevelNo);
        hierarchyIndicator = "C";

        tableLogic.setTreeNodeMultiClick(false);
        tableLogic.setDataLoad(false);

        initializeTable();
        addResultTable();
        createLeftHeader();
        resultBeanContainer.setColumnProperties(leftHeader.getProperties());

        tableLogic.setContainerDataSource(resultBeanContainer);
        configureLeftTable();

        LOGGER.info(" session Action " + session.getAction());

        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
            if (loadSelections()) {
                generateBtnClickLogic(false);
            } else {
                loadEmptyTable();
            }
        } else {
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

        LOGGER.info("Exiting configureFields ");

    }
    ColumnCheckListener checkListener = new ColumnCheckListener() {
        @Override
        public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
            tableLogic.setCheckAll(event.isChecked());
            checkClearAll(event.isChecked());
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
        LOGGER.info(" inside load selections ");
        try {
            Map<Object, Object> map = new NMProjectionVarianceLogic().getNMProjectionSelection(session.getProjectionId(), TAB_DISCOUNT_PROJECTION.getConstant());
            if (map != null && map.size() > 0) {
                LOGGER.info(" Freq " + map.get(Constant.FREQUENCY));
                LOGGER.info(" history " + map.get(Constant.HISTORY));
                LOGGER.info(" Period Order " + map.get("Projection Period Order"));
                LOGGER.info(" Actuals / Proj" + map.get("Actuals / projections"));
                LOGGER.info(" Level " + map.get("Level"));
                LOGGER.info(" Program selection ddlb" + map.get("Program Selection Ddlb"));
                LOGGER.info(" year seelction ddlb " + map.get("year Selection Ddlb"));
                LOGGER.info(" selected discount " + map.get("Selected Discounts"));

                frequencyDdlb.select(map.get(Constant.FREQUENCY));
                historyDdlb.select(Integer.parseInt(String.valueOf((map.get(Constant.HISTORY) == null || map.get(Constant.HISTORY).toString().equalsIgnoreCase("null")) ? 0 : map.get(Constant.HISTORY))));
                periodOrder.select(map.get("Projection Period Order"));
                actualsProjs.select(map.get("Actuals / projections"));
                level.select(map.get("Level"));
                yearSelection.select(map.get("year Selection Ddlb"));
                discountProgramsList = new LinkedList<String>(Arrays.asList(String.valueOf(map.get("Selected Discounts")).split("\\s*,\\s*")));
                discountNamesList = new LinkedList<String>(discountProgramsList);
                discountNoList = new LinkedList<String>(Arrays.asList(String.valueOf(map.get("selectedDiscountNo")).split("\\s*,\\s*")));
                programBean.removeAllItems();
                programBean.addItem(SELECT_ONE.getConstant());
                Collections.sort(discountProgramsList);
                programBean.addAll(discountProgramsList);
                programSelection.select(map.get("Program Selection Ddlb"));
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
        LOGGER.info("Inside configureTable ");
        try {
            float maxSplitPosition = 1000;

            /* Minimum split position for the split bar */
            float minSplitPosition = 200;

            /* Default split position for the split bar */
            float splitPosition = 500;

            resultsTable.setImmediate(true);
            resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
            resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
            resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
            resultsTable.setHeight("650px");
            resultsTable.setWidth("100%");
            resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

            refreshBtn.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    int count = 0;
                    if (isAmountUpdatedManually) {
                        count += 1;
                    }
                    if (isRPUUpdatedManually) {
                        count += 1;
                    }
                    if (isGrowthUpdatedManually) {
                        count += 1;
                    }
                    if (isRateUpdatedManually) {
                        count += 1;
                    }
                    if (count > 1) {
                        LOGGER.info("inside if refreshBtn--------------------------------- ");

                    } else {
                        isGrowthUpdatedManually = false;
                        isRateUpdatedManually = false;
                        isRPUUpdatedManually = false;
                        isAmountUpdatedManually = false;
                        saveDiscountProjectionListview();
                        Object[] orderedArg = {session.getProjectionId(), session.getUserId(), session.getSessionId()};
                        CommonLogic.callProcedure("PRC_NM_DISCOUNT_REFRESH", orderedArg);
                        refreshTableData(getCheckedRecordsHierarchyNo());
//                        refreshTableData(getManualEntryRefreshHierarachyNo());
                        manualEntryMap.clear();
                    }

                }
            });

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending configureTable ");
    }

    /**
     * To refresh the table Data
     */
    public void refreshTableData(Set<String> finalHirarechyNo) {
        tableLogic.setRefreshHierarchyNo(getHierarchyNoForCheckedRecord(finalHirarechyNo), true);
        tableLogic.forRefresh(finalHirarechyNo);
        tableLogic.setCurrentPage(tableLogic.getCurrentPage());

    }

    /**
     * To get the hierarchy numbers affected during the manual entry
     *
     * @return
     */
    private Set<String> getManualEntryRefreshHierarachyNo() {
        Set<String> finalHierarchyNo = new HashSet<String>();
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
        resultsTableLayout.addComponent(resultsTable);

        tableLogic.setPageLength(20);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);

        HorizontalLayout controls = tableLogic.createControls();
        HorizontalLayout cssControls = CommonLogic.getResponsiveControls(controls);
        cssControls.addStyleName("responsivePagedTable");
        resultsTableLayout.addComponent(cssControls);
        excelTableLayout.addComponent(excelTable);
        excelTableLayout.setHeight(0, Unit.PIXELS);
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
        LOGGER.info("Inside checkClearAll");
        tableLogic.setRefresh(Boolean.FALSE);
        boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
        logic.checkClearAll(session, userGroup, checkClear, isProgram, projectionSelection.getDiscountProgramsList());
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
                                ccpsCount=tempDto.getCcpCount();
                updateChecks(tempId, isPresentInContainer);
            }
        }
        LOGGER.info("exiting checkClearAll");
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
        LOGGER.info("fieldDdlb value change listener starts");
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
        LOGGER.info("fieldDdlb value change listener ends");

    }

    /**
     * To load the group Ddlb
     */
    private void loadGroupDdlb() {
        LOGGER.info(" Load group ");
        valueDdlbBean.removeAllItems();
        List<String> discountToBeLoaded = new ArrayList<String>();
        if (!programSelectionList.isEmpty()) {
            discountToBeLoaded.addAll(programSelectionList);

        } else {
            discountToBeLoaded.addAll(discountProgramsList);

        }
        discountToBeLoaded.add(0, String.valueOf(level.getValue()));
        String relationshipBuilderSid = getRelationshipBuilderSid();
        valueDdlbBean.addAll(logic.loadGroupValues(session, discountToBeLoaded, relationshipBuilderSid));
        LOGGER.info(" End load group ");
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
        LOGGER.info(" Calling Discount Projection Procedure");
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
        LOGGER.info("loadLevelValues started ");

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
        LOGGER.info("loadLevelValues ended ");
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
        viewDdlb.setEnabled(false);
        editBtn.setEnabled(false);
        newBtn.setEnabled(false);
        resetTableData();
        tableLogic.setRefreshHierarchyNo(StringUtils.EMPTY, false);
        if (view.getValue() != null) {
            if (CUSTOM.getConstant().equals(String.valueOf(view.getValue()))) {
                hierarchyIndicator = "CP";
                loadCustomDDLB();
                currentHierarchy = CommonLogic.getCustomTree(customId);
                levelFilterDdlb.setEnabled(false);
                setUserGroup();
                fieldDdlb.removeItem(Constant.GROUPFCAPS);
                fieldDdlb.setValue("Discount Rate");
                resultsTable.getLeftFreezeAsTable().setColumnCollapsingAllowed(true);
                resultsTable.getLeftFreezeAsTable().setColumnCollapsed(Constant.GROUP, false);
            } else if (CUSTOMER.getConstant().equals(String.valueOf(view.getValue()))) {
                customIdToSelect = customId;
                int hierarchyLevelNo = isInteger(session.getCustomerLevelNumber()) ? Integer.valueOf(session.getCustomerLevelNumber()) : 0;
                currentHierarchy = CommonLogic.getCustomerHierarchy(session.getProjectionId(), hierarchyLevelNo);
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
                int hierarchyLevelNo = isInteger(session.getProductLevelNumber()) ? Integer.valueOf(session.getProductLevelNumber()) : 0;
                currentHierarchy = CommonLogic.getProductHierarchy(session.getProjectionId(), hierarchyLevelNo);
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
                fieldDdlb.setValue("Discount Rate");
                resultsTable.getLeftFreezeAsTable().setColumnCollapsingAllowed(true);
                resultsTable.getLeftFreezeAsTable().setColumnCollapsed(Constant.GROUP, true);
            }
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
            resultsTable.getLeftFreezeAsTable().resetFilters();
            resultsTable.getLeftFreezeAsTable().setRefresh(true);

        }
    }

    private void configureLeftTable() {
        LOGGER.info("Entering configureLeftTable");
        final ExtFilterTreeTable leftTable = resultsTable.getLeftFreezeAsTable();

        leftTable.setDoubleHeaderVisible(true);

        leftTable.setEditable(true);
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));

        leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
        leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());

        leftTable.setHeight("650px");

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
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }
        });

        resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);
        addFieldFactoryAndListenersForLeftTable();
        for (Object obj : leftHeader.getSingleColumns()) {
            if (String.valueOf(obj).contains(Constant.GROUP)) {
                leftTable.setColumnWidth(obj, 130);
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, 130);
            }
        }
        LOGGER.info("Ending configureLeftTable");
    }

    private void addFieldFactoryAndListenersForLeftTable() {
        LOGGER.info("Entering addFieldFactoryAndListenersForLeftTable ==================================================================================");
        try {
            resultsTable.getLeftFreezeAsTable().addColumnCheckListener(checkListener);
            resultsTable.getLeftFreezeAsTable().setTableFieldFactory(new DefaultFieldFactory() {
                public Field<?> createField(final Container container, final Object itemId,
                        final Object propertyId, Component uiContext) {
                    final String tableHierarchyNo = tableLogic.getTreeLevelonCurrentPage(itemId);
                    String property = String.valueOf(propertyId);
                    Item item = container.getItem(itemId);
                    String levelType = String.valueOf(item.getItemProperty("level").getValue());
                    if (property.equals(Constant.CHECKRECORD)) {
                        final ExtCustomCheckBox check = new ExtCustomCheckBox();
                        check.setEnabled(!ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
                        check.setImmediate(true);
                        check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                            @Override
                            public void click(ExtCustomCheckBox.ClickEvent event) {
                                DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                                Boolean checkValue = check.getValue();
                                if (isGroupUpdatedManually) {
                                    NotificationUtils.getAlertNotification("Group Filter Conflict", "The group value entered conflicted with the group filter. Kindly Click Refresh button before proceeding this operation");
                                    tableLogic.getContainerDataSource().getContainerProperty(itemId, Constant.CHECKRECORD).setValue(false);
                                    return;
                                }
                                dto.addBooleanProperties(propertyId, checkValue);
                                int updatedRecordsNo = updateCheckedRecord(dto) * CommonUtils.getFrequencyNumber(projectionSelection.getFrequency());
                                resultsTable.getLeftFreezeAsTable().setRefresh(false);
                                updateCheckForParentLevels(itemId, updatedRecordsNo, checkValue);
                                updateCheckForChildLevels(tableHierarchyNo, itemId, checkValue);
                                if (!checkValue) {
                                    ExtPagedTreeTable leftTable = resultsTable.getLeftFreezeAsTable();
                                    leftTable.removeColumnCheckListener(checkListener);
                                    leftTable.setColumnCheckBox(Constant.CHECKRECORD, true, false);
                                    tableLogic.setCheckAll(false);
                                    leftTable.addColumnCheckListener(checkListener);
                                }
                                resultsTable.getLeftFreezeAsTable().setRefresh(true);
                            }
                        });
                        return check;
                    }

                    if (Constant.GROUP.equals(property) && (Constant.TRADING_PARTNER.equals(levelType) || Constant.CUSTOMER_SMALL.equals(levelType)) && !Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equals(hierarchyIndicator) && !ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
                        TextField textField = new TextField();
                        textField.setWidth(100, Unit.PERCENTAGE);
                        textField.addFocusListener(new FieldEvents.FocusListener() {
                            @Override
                            public void focus(FieldEvents.FocusEvent event) {
                                focusValue = String.valueOf(container.getContainerProperty(itemId, propertyId).getValue()).trim();
                                LOGGER.info(" group focus Value" + focusValue);
                            }
                        });

                        textField.addBlurListener(new FieldEvents.BlurListener() {
                            @Override
                            public void blur(FieldEvents.BlurEvent event) {
                                blurValue = String.valueOf(container.getContainerProperty(itemId, propertyId).getValue()).trim();
                                LOGGER.info(" blur Value" + blurValue);
                                if (!focusValue.equals(blurValue)) {
                                    DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                                    dto.addStringProperties(propertyId, blurValue);
                                    LOGGER.info(" hierarchyNo " + dto.getHierarchyNo());
                                    String relationshipBuilderSid = getRelationshipBuilderSid();
                                    boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
                                    boolean isCustomHierarchy = Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equals(hierarchyIndicator);
                                    List<String> customViewDetails = new ArrayList<String>();
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
                                        LOGGER.info(" EXCEL - Custom hierarchy --- \n customId " + customId);
                                        LOGGER.info(" EXCEL - customerLevelNo " + customerLevelNo);
                                        LOGGER.info(" EXCEL - customerHierarchyNo " + customerHierarchyNo);
                                        LOGGER.info(" EXCEL - productLevelNo " + productLevelNo);
                                        LOGGER.info(" EXCEL - productHierarchyNo " + productHierachyNo);
                                        customViewDetails.add(StringUtils.EMPTY + customId);
                                        customViewDetails.add(customerLevelNo);
                                        customViewDetails.add(customerHierarchyNo);
                                        customViewDetails.add(productLevelNo);
                                        customViewDetails.add(productHierachyNo);
                                        customViewDetails.add(session.getCustRelationshipBuilderSid());
                                        customViewDetails.add(session.getProdRelationshipBuilderSid());
                                    }
                                    if (logic.saveGroupValues(session, dto.getHierarchyNo(), blurValue, isProgram, isCustomHierarchy,
                                            customViewDetails, projectionSelection.getDiscountProgramsList(), relationshipBuilderSid)) {
                                        if (!userGroup.isEmpty()) {
                                            isGroupUpdatedManually = true;
                                        }
                                        LOGGER.info(blurValue + " saved ");
                                    }
                                    groupFilterDdlb.removeValueChangeListener(groupFilterDdlbValueChange);
                                    groupFilterDdlb.addItem(blurValue);
                                    valueDdlbBean.addItem(blurValue);
                                    tableGroupDdlbBean.addItem(blurValue);
                                    groupFilterDdlb.addValueChangeListener(groupFilterDdlbValueChange);
                                }

                            }
                        });
                        return textField;

                    }
                    return null;
                }
            });

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending addFieldFactoryAndListenersForLeftTable ");
    }

    private void createLeftHeader() {
        leftHeader = HeaderUtils.getDiscountProjectionLeftTableColumns(hierarchyIndicator, false, excelHeaderLeft);
    }

    /**
     * To update the checked record
     */
    public int updateCheckedRecord(DiscountProjectionDTO dto) {
        LOGGER.info("Inside updateCheckedRecord");
        boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
        List<String> discountList = projectionSelection.getDiscountProgramsList();
        int updatedRecordCount = 0;
        try {
            int maxTreeLevelno = 0;
            int treeLevelNo = 0;
            int count = 0;
            boolean checkValue = true;
            String hierarchyNo = StringUtils.EMPTY;
            ccpsCount = dto.getCcpCount();
            List<String> customerHierarchyNoList = new ArrayList<String>();
            List<String> productHierarchyNoList = new ArrayList<String>();
            List<String> customerLevelNoList = new ArrayList<String>();
            List<String> productLevelNoList = new ArrayList<String>();
            List<String> hierarchyIndicatorList = new ArrayList<String>();
            boolean isCustomHierarchy = Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equals(hierarchyIndicator);

            if (resultBeanContainer.size() == 0) {
                LOGGER.info(" Container size is 0");
                return updatedRecordCount;
            }
            if (isCustomHierarchy) {
                if (customerHierarchyNoList.isEmpty() && productHierarchyNoList.isEmpty()) {
                    maxTreeLevelno = dto.getTreeLevelNo();
                }

                treeLevelNo = dto.getTreeLevelNo();
                if (treeLevelNo > maxTreeLevelno) {
                    maxTreeLevelno = treeLevelNo;
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
            List<String> customViewDetails = new ArrayList<String>();
            String relationshipBuilderSid = getRelationshipBuilderSid();
            if (isCustomHierarchy) {
                for (int i = 0; i < count; i++) {
                    customViewDetails = new ArrayList<String>();
                    customViewDetails.add(StringUtils.EMPTY + customId);
                    customViewDetails.add(customerLevelNoList.get(i));
                    customViewDetails.add(customerHierarchyNoList.get(i));
                    customViewDetails.add(productLevelNoList.get(i));
                    customViewDetails.add(productHierarchyNoList.get(i));

                    customViewDetails.add(session.getCustRelationshipBuilderSid());
                    customViewDetails.add(session.getProdRelationshipBuilderSid());
                    LOGGER.info(" Check record - Custom Id " + customId);
                    LOGGER.info(" Check record - customerLevelNo " + customerLevelNoList.get(i));
                    LOGGER.info(" Check record - customerHierarchyNo " + customerHierarchyNoList.get(i));
                    LOGGER.info(" Check record - productLevelNo " + productLevelNoList.get(i));
                    LOGGER.info(" Check record - productHierarchyNo " + productHierarchyNoList.get(i));
                    LOGGER.info(" Check record - checkValue " + checkValue);
                    LOGGER.info(" Check record - Hierarchy indicator " + hierarchyIndicatorList.get(i));
                    LOGGER.info(" Check record - CustRelationshipBuilderSid " + session.getCustRelationshipBuilderSid());
                    LOGGER.info(" Check record - ProdRelationshipBuilderSid " + session.getProdRelationshipBuilderSid());

                    updatedRecordCount = logic.updateCheckRecord(session, checkValue, hierarchyNo, userGroup, hierarchyIndicatorList.get(i), isCustomHierarchy, customViewDetails, relationshipBuilderSid, isProgram, discountList);
                }
            } else {
                updatedRecordCount = logic.updateCheckRecord(session, checkValue, hierarchyNo, userGroup, hierarchyIndicator, isCustomHierarchy, customViewDetails, relationshipBuilderSid, isProgram, discountList);
            }
        } catch (Exception e) {
            updatedRecordCount = 0;
        }
        LOGGER.info("Ending updateCheckedRecord");
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
        LOGGER.info("Inside updateCheckForParentLevels");
        DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
        int newRecordsCount = updatedRecordsNo;
        LOGGER.info(" updatedRecordsNo " + newRecordsCount);
        if (checkValue) {
            if (newRecordsCount > dto.getUncheckCount()) {
                newRecordsCount = dto.getUncheckCount();
            }
        } else {
            if (newRecordsCount < dto.getUncheckCount()) {
                newRecordsCount = dto.getUncheckCount();
            }
        }
        LOGGER.info((checkValue ? "Checked" : "Unchecked") + " updatedRecordsNo " + newRecordsCount);
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
                LOGGER.info(tempDto.getTreeLevelNo() + " " + tempDto.getLevelName() + " Parent Uncheck count before " + tempDto.getUncheckCount());
                if (checkValue) {
                    tempDto.setUncheckCount(tempDto.getUncheckCount() - newRecordsCount);
                } else {
                    tempDto.setUncheckCount(tempDto.getUncheckCount() + newRecordsCount);
                }
                LOGGER.info(tempDto.getTreeLevelNo() + " " + tempDto.getLevelName() + " Parent Uncheck count after " + tempDto.getUncheckCount());
                updateChecks(tempId, isPresentInContainer);
            }
        }
        LOGGER.info("Exiting updateCheckForParentLevels");
    }

    /**
     * To update records count for child
     *
     * @param tableHierarchyNo
     * @param itemId
     * @param checkValue
     */
    private void updateCheckForChildLevels(String tableHierarchyNo, Object itemId, Boolean checkValue) {
        LOGGER.info("Inside updateCheckForChildLevels");
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
                LOGGER.info(tempDto.getTreeLevelNo() + " " + tempDto.getLevelName() + " Child Uncheck count before " + tempDto.getUncheckCount());

                if (checkValue) {
                    tempDto.setUncheckCount(0);
                } else {
                    tempDto.setUncheckCount(tempDto.getCcpCount());
                }
                LOGGER.info(tempDto.getTreeLevelNo() + " " + tempDto.getLevelName() + " Child Uncheck count after " + tempDto.getUncheckCount());
                updateChecks(tempId, isPresentInContainer);
            }
        }
        LOGGER.info("Ending updateCheckForChildLevels");
    }

    /**
     * To load the values in custom Ddlb
     */
    public void loadCustomDDLB() {
        LOGGER.info("loadCustomDDLB initiated ");
        viewDdlb.setEnabled(true);
        newBtn.setEnabled(true);
        customViewList = CommonLogic.getCustomViewList(session.getProjectionId());
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
        LOGGER.info("loadCustomDDLB ends ");
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
        boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
        tableLogic.setCheckAll(false);
        logic.checkClearAll(session, userGroup, false, isProgram, projectionSelection.getDiscountProgramsList());
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
        LOGGER.info(" adjustmentPrograms " + adjustmentProgram);
    }

    @Override
    protected void adjPeriodValueChangeLogic(String adjustmentPeriod) {
        String adjustmentPrograms = String.valueOf(adjprograms.getValue());
        LOGGER.info(" adjustmentPeriods " + adjustmentPeriod);
        LOGGER.info(" adjustmentPrograms " + adjustmentPrograms);
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
                LOGGER.info(" Adj periods discount Column Name " + discountColumn);
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
            NotificationUtils.getAlertNotification("Group Filter Conflict", "The group value entered conflicted with the group filter. Kindly Click Refresh button to proceed with this operation");
            return;
        }
        final String selectedField = String.valueOf(fieldDdlb.getValue());
        final String valueText = value.getValue();
        final String valueDdlbValue = String.valueOf(valueDdlb.getValue());
        try {
            LOGGER.info("fieldDdlb.getValue()-->>>" + selectedField);
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
                    List<String> checkedDiscountNames = new ArrayList<String>();
                    if ((selectedField.equals(Constant.GROUPFCAPS) && (valueDdlb != null && valueDdlb.getValue() != null && !valueDdlbValue.isEmpty()))
                            || (selectedField.equals("Discount Rate") && !valueText.isEmpty())
                            || (selectedField.equals("RPU") && !valueText.isEmpty())
                            || (selectedField.equals("Discount Amount") && !valueText.isEmpty())
                            || (selectedField.equals(Constant.GROWTH) && !valueText.isEmpty())) {
                        if (!Constant.GROUPFCAPS.equals(selectedField)) {
                            for (Object discountPropertyId : checkedDiscountsPropertyIds) {
                                checkedDiscountNames.add(rightTable.getTripleHeaderColumnHeader(discountPropertyId));
                            }
                            if (checkedDiscountNames.isEmpty()) {
                                new AbstractNotificationUtils() {
                                    @Override
                                    public void noMethod() {
                                    }

                                    @Override
                                    public void yesMethod() {
                                        List<String> allDiscountNames = new ArrayList<String>();
                                        String[] rightTripleHeaderNames = resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeaders();
                                        for (int i = 0; i < rightTripleHeaderNames.length; i++) {
                                            allDiscountNames.add(rightTripleHeaderNames[i]);
                                        }
                                        discountRatemassUpdate(allDiscountNames, selectedField, valueText);
                                    }
                                }.getConfirmationMessage("No Discount Selected", "You have not selected a discount. The Mass Update value will apply to ALL discounts in the list view.");

                            } else {
                                discountRatemassUpdate(checkedDiscountNames, selectedField, valueText);
                            }
                        } else {
                            for (Object itemId : resultsTable.getLeftFreezeAsTable().getItemIds()) {
                                DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                                if ((Boolean) dto.getPropertyValue(Constant.CHECKRECORD) && Constant.CUSTOMER_SMALL.equals(resultsTable.getLeftFreezeAsTable().getItem(itemId).getItemProperty("level").getValue().toString())) {
                                    resultsTable.getLeftFreezeAsTable().getItem(itemId).getItemProperty(Constant.GROUP).setValue(valueDdlbValue);
                                    ((DiscountProjectionDTO) itemId).setGroup(valueDdlbValue);
                                }
                            }

                            performMassUpdate(new ArrayList<Integer>(), checkedDiscountNames, selectedField, valueDdlbValue);
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
    private void discountRatemassUpdate(List<String> checkedDiscountNames, String selectedField, String value) {

        LOGGER.info(" checkedDiscounts " + checkedDiscountNames);

        if (startPeriod != null && startPeriod.getValue() != null) {
            String startPeriodValue = startPeriod.getValue().toString();
            int startFreq = 0;
            int startYear = 0;
            int endFreq = 0;
            int endYear = 0;

            // To set startFreq (in No) and startYear
            if (!projectionSelection.getFrequency().equals(MONTHLY.getConstant())) {
                String startFreqNoValue = startPeriodValue.substring(1, 2);
                startFreq = isInteger(startFreqNoValue) ? Integer.valueOf(startFreqNoValue) : 0;
            } else {
                String startMonthValue = startPeriodValue.substring(0, startPeriodValue.length() - 5);
                startFreq = CommonUtils.getIntegerForMonth(startMonthValue);
            }
            LOGGER.info("startPeriodValue-->>" + startPeriodValue);
            startYear = Integer.valueOf(startPeriodValue.substring(startPeriodValue.length() - 4));

            // To set endFreq (in No) and endYear
            String endPeriodValue = StringUtils.EMPTY;
            if (endPeriod.getValue() == null || Constant.NULL.equals(String.valueOf(endPeriod.getValue())) || Constant.SELECT_ONE.equals(String.valueOf(endPeriod.getValue()))) {
                endPeriodValue = startPeriodBean.getIdByIndex(startPeriodBean.size() - 1);
            } else {
                endPeriodValue = endPeriod.getValue().toString();
            }
            LOGGER.info(" endPeriodValue " + endPeriodValue);
            if (!projectionSelection.getFrequency().equals(MONTHLY.getConstant())) {
                String endFreqNoValue = endPeriodValue.substring(1, 2);
                endFreq = isInteger(endFreqNoValue) ? Integer.valueOf(endFreqNoValue) : 0;
            } else {
                String endMonthValue = endPeriodValue.substring(0, endPeriodValue.length() - 5);
                endFreq = CommonUtils.getIntegerForMonth(endMonthValue);
            }
            endYear = Integer.valueOf(endPeriodValue.substring(endPeriodValue.length() - 4));

            List<Integer> massUpdatePeriods = new ArrayList<Integer>();
            massUpdatePeriods.add(startFreq);
            massUpdatePeriods.add(startYear);
            massUpdatePeriods.add(endFreq);
            massUpdatePeriods.add(endYear);
            performMassUpdate(massUpdatePeriods, checkedDiscountNames, selectedField, value);
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
    private void performMassUpdate(List<Integer> massUpdatePeriods, List<String> checkedDiscountNames, String selectedField, String value) {
        if (Constant.GROUPFCAPS.equals(selectedField)) {
            LOGGER.info("Group-->" + value);
            logic.massUpdate(session, projectionSelection.getFrequency(), massUpdatePeriods, selectedField, value, checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()));
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
            LOGGER.info("Discount Rate-->" + value);
            logic.massUpdate(session, projectionSelection.getFrequency(), massUpdatePeriods, selectedField, value, checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()));
            refreshTableData(getCheckedRecordsHierarchyNo());
        } else if ("RPU".equals(selectedField)) {
            saveDiscountProjectionListview();
            LOGGER.info("RPU-->" + value + "ccpsCount" + ccpsCount);
          //  double finalValue = doubleConversion(value);
            logic.massUpdate(session, projectionSelection.getFrequency(), massUpdatePeriods, selectedField, value, checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()));
            refreshTableData(getCheckedRecordsHierarchyNo());
        } else if ("Discount Amount".equals(selectedField)) {
            saveDiscountProjectionListview();
            LOGGER.info("Discount Amount-->" + value + "ccpCount" + ccpsCount);
            double finalValue = doubleConversion(value);
            logic.massUpdate(session, projectionSelection.getFrequency(), massUpdatePeriods, selectedField, String.valueOf(finalValue), checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()));
            refreshTableData(getCheckedRecordsHierarchyNo());
        } else if ("Growth".equals(selectedField)) {
            saveDiscountProjectionListview();
            LOGGER.info("Growth-->" + value);
            logic.massUpdate(session, projectionSelection.getFrequency(), massUpdatePeriods, selectedField, value, checkedDiscountNames, PROGRAM.getConstant().equals(level.getValue()));
            refreshTableData(getCheckedRecordsHierarchyNo());
        }

    }

    @Override
    protected void calculateBtnClickLogic() {
        Set<String> setMethodologiesValuesVal = new HashSet();
        setMethodologiesValuesVal.addAll(Arrays.asList(new String[]{"Single Period", "% of Ex-Factory Sales", "% of Demand", "% of Inventory Withdrawal", Constant.PERC_OF_ADJUSTED_DEMAND}));
        String endValue = StringUtils.EMPTY;
        List<String> checkedDiscountNames = new ArrayList<String>();
        if (endPeriodForecastTab.getValue() != null) {
            endValue = endPeriodForecastTab.getValue().toString().replace(" ", "~").trim();
        } else {
            endValue = forecaststartBean.getIdByIndex(forecaststartBean.size() - 1);
        }
        if ((methodologyDdlb.getValue() != null && !methodologyDdlb.getValue().equals(SELECT_ONE.getConstant())) && !String.valueOf(methodologyDdlb.getValue().toString().trim()).equals(Constant.NULL)) {
            if (endDateValidation(endValue)) {

                if (startPeriodForecastTab.getValue() != null && !startPeriodForecastTab.getValue().equals(SELECT_ONE.getConstant())) {
                    if (!CONTRACT_DETAILS.getConstant().equals(methodologyDdlb.getValue()) && (checkBoxMap.size() == 0 || !isCheckBoxProperty())) {
                        NotificationUtils.getErrorNotification("No period selected", "Please select a Historic Period for each discount selected.");
                    } else {
                        if (baseLineCalc(startPeriodForecastTab.getValue().toString(), endValue.replace("~", " ")) || methodologyDdlb.getValue().equals(CONTRACT_DETAILS.getConstant())) {
                            boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
                            if (logic.isAnyRecordChecked(session, isProgram, projectionSelection.getDiscountProgramsList())) {
                                if (methodologyDdlb.getValue().equals(ROLLING_ANNUAL_TREND.getConstant()) && !projectionSelection.getFrequency().equals(ANNUALLY.getConstant()) && !rollingAnnualTrendCount()) {
                                    NotificationUtils.getErrorNotification("Select complete annual period", "The Rolling Annual Trend methodology requires"
                                            + " a complete calendar year of periods to use as a baseline."
                                            + "  Please select a complete calendar year of periods "
                                            + "for each selected discount and try again.");
                                } else if (methodologyDdlb.getValue().equals(AVERAGE.getConstant()) && !checkHistorySelectedCount(2)) {
                                    NotificationUtils.getErrorNotification("No period selected", "Please select at least two historic periods to use as a baseline for each selected discount.");
                                } else if (setMethodologiesValuesVal.contains(String.valueOf(methodologyDdlb.getValue())) && !checkHistorySelectedCount(0)) {
                                    NotificationUtils.getErrorNotification("No period selected", "Please select a Historic Period for each discount selected.");
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
//                                            if (methodologyDdlb.getValue().equals(CONTRACT_DETAILS.getConstant())) {
//                                                String discounts[] = resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeaders();
//                                                for (String discount : discounts) {
//                                                    tripleHeaderForCheckedDoubleHeader.clear();
//                                                    tripleHeaderForCheckedDoubleHeader.put(discount, tripleHeaderForCheckedDoubleHeader.get(discount));
//                                                }
//                                            }
                                           
                                            for (Object discountPropertyId : checkedDiscountsPropertyIds) {
                                                checkedDiscountNames.add(resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeader(discountPropertyId));
                                            }
                                            logic.calcDataUpdate(session, projectionSelection, String.valueOf(level.getValue()),
                                                    String.valueOf(methodologyDdlb.getValue()), tripleHeaderForCheckedDoubleHeader,checkedDiscountNames);
                                            session.setFrequency(String.valueOf(frequencyDdlb.getValue()));
                                            logic.callDPProcedure(session);
//                                            Set<String> tableLevelNo = getCheckedRecordsHierarchyNo();
//                                            String hierarchyNo = getHierarchyNo(tableLevelNo);
//                                            tableLogic.setRefreshHierarchyNo(String.valueOf(hierarchyNo), true);
                                            refreshTableData(getCheckedRecordsHierarchyNo());
                                        } else {
                                            NotificationUtils.getErrorNotification("Different variable types selected", "Please select only one variable type across the baseline periods.");

                                        }
                                    }

                                }
                            } else {
                                NotificationUtils.getErrorNotification("No Hierarchy level selected", "Please select a level in the hierarchy for the methodology");
                            }
                        } else {
                            if (baselineFlag == 1) {
                                AbstractNotificationUtils.getErrorNotification("Baseline period within calculation range", "The selected baseline periods are within the Start Period and End Period range for one or more of the selected discounts.  Please select a baseline period that is prior to the calculation range for each of the selected discounts.");
                            } else if (baselineFlag == 2) {
                                AbstractNotificationUtils.getErrorNotification("Baseline period after calculation range", "The selected baseline periods are after the Start Period and End Period range for one or more of the selected discounts.  Please select a baseline period that is prior to the calculation range for each of the selected discounts.");
                            }
                        }

                    }

                } else {
                    NotificationUtils.getErrorNotification("No Start Period selected", "Please select a Start Period");
                }

            } else {
                if (errorFlag) {
                    AbstractNotificationUtils.getErrorNotification("End Period is before Start Period", "The Start Period must be before the End Period.Please try again");
                    errorFlag = false;
                }
            }
        } else {
            NotificationUtils.getErrorNotification("No Methodology selected", "Please select a Methodology");
        }
    }

    @Override
    protected void newBtnClickLogic() {
        LOGGER.info("newCustomHierarchhy clickEvent method starts");
        final CustomTreeBuild customTree = new CustomTreeBuild(Constant.ADD_FULL_SMALL, session);
        customTree.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (customTree.isIsSelect()) {
                    customIdToSelect = customTree.getCustomId();
                }
                loadCustomDDLB();
            }
        });
        UI.getCurrent().addWindow(customTree);
        LOGGER.info("newCustomHierarchhy clickEvent method ends");
    }

    @Override
    protected void editBtnClickLogic() {
        LOGGER.info("Entering editHierarchyBtn");
        if (CommonLogic.editButtonValidation(viewDdlb, customViewList)) {
            final CustomTreeBuild customTree = new CustomTreeBuild(Constant.EDIT, session, customId);
            customTree.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {
                    customIdToSelect = customTree.getCustomId();
                    loadCustomDDLB();
                }
            });
            UI.getCurrent().addWindow(customTree);
        }
        LOGGER.info(" Ending editHierarchyBtn ");
    }

    @Override
    protected void adjustBtnClickLogic() {
        if (isGroupUpdatedManually) {
            NotificationUtils.getAlertNotification("Group Filter Conflict", "The group value entered conflicted with the group filter. Kindly Click Refresh button before proceeding this operation");
            return;
        }
        final String levelType = String.valueOf(level.getValue());
        final String adjustmentType = String.valueOf(type.getValue());
        final String adjustmentBasis = String.valueOf(basis.getValue());
        final String adjustmentValue = adjustment.getValue();
        final String allocationMethodology = String.valueOf(allocMethodology.getValue());
        String adjustmentPrograms = String.valueOf(adjprograms.getValue());
        String adjustmentPeriods = String.valueOf(adjperiods.getValue());
        List<String> selectedDoubleList = new ArrayList<String>();
        saveDiscountProjectionListview();
        if (!adjustmentValue.matches("[-+]?(?:\\d+|\\d*(?:\\.\\d{1,2})?)")) {
            AbstractNotificationUtils.getErrorNotification("Field Error", "Please enter a numeric value in the Adjustment text box");
            adjustment.setValue(StringUtils.EMPTY);
            return;
        }

        if (!Constant.NULL.equals(allocationMethodology)) {

            if (!adjustmentValue.replace(" ", StringUtils.EMPTY).isEmpty()) {

                if (!checkedDiscountsPropertyIds.isEmpty()) {

                    List<String> headerList = new ArrayList<String>();
                    for (Object propertyId : checkedDiscountsPropertyIds) {
                        String tripleHeader = resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeader(propertyId);
                        headerList.add(tripleHeader);
                    }
                    List<String> remoList = new ArrayList<String>(tripleHeaderForCheckedDoubleHeader.keySet());
                    remoList.removeAll(headerList);

                    List<String> baselinePeriods = new ArrayList<String>();

                    for (Object propertyId : checkedDiscountsPropertyIds) {

                        String tripleHeader = resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeader(propertyId);
                        if (tripleHeaderForCheckedDoubleHeader.get(tripleHeader) == null) {
                            NotificationUtils.getErrorNotification("No period selected", "Please select which periods need to be included in the adjustment for each of the selected discounts.");
                            return;
                        }

                        if ("Historical % of Business".equals(allocationMethodology)) {
                            baselinePeriods = tripleHeaderForCheckedDoubleHeader.get(tripleHeader).get("H");
                        } else {
                            baselinePeriods = tripleHeaderForCheckedDoubleHeader.get(tripleHeader).get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                        }

                        if ("Historical % of Business".equals(allocationMethodology)) {
                            if (baselinePeriods == null || baselinePeriods.isEmpty()) {
                                NotificationUtils.getErrorNotification("No period selected", "Please select a historic period to use as a baseline for each of the selected discounts.");
                                return;
                            }
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
                            NotificationUtils.getErrorNotification("No period selected", "Please select which periods need to be included in the adjustment for each of the selected discounts.");
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
                            }

                            @Override
                            public void yesMethod() {
                                if (logic.adjustmentDataUpdate(session, projectionSelection.getFrequency(), levelType, adjustmentType,
                                        adjustmentBasis, adjustmentValue, allocationMethodology, tripleHeaderForCheckedDoubleHeader)) {
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
                                    logic.checkUncheckRebateBeforeAdjust(false, checkedDiscountList, session, true);
                                    session.setFrequency(projectionSelection.getFrequency());
                                    if (logic.adjustDiscountProjection(session)) {
                                        LOGGER.info(" Procedure executed Successfully");
                                        logic.checkUncheckRebateBeforeAdjust(true, selectedDiscountList, session, false);
                                        refreshTableData(getCheckedRecordsHierarchyNo());
                                    } else {
                                        logic.checkUncheckRebateBeforeAdjust(true, selectedDiscountList, session, false);
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
        Set<String> finalHirarechyNo = new HashSet<String>();
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

    @Override
    protected void excelExportClickLogic() {
        LOGGER.info("excel starts");
        try {
            excelTable.setRefresh(Boolean.FALSE);
            excelContainer = new CustomTreeContainer<DiscountProjectionDTO>(DiscountProjectionDTO.class);
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
            Map<String, String> formatter = new HashMap<String, String>();
            formatter.put("percentTwoDecimal", "Rate");
            excelTable.setRefresh(Boolean.TRUE);
            ForecastUI.EXCEL_CLOSE = true;
            CustomExcelNM excel = new CustomExcelNM(new ExtCustomTableHolder(excelTable), "Discount Projection", "Discount Projection", "DiscountProjection.xls", false, formatter);
            excel.export();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("excel ends");
    }

    public void generateButtonlogicForExcel() {
        try {
            LOGGER.info("Inside generateButtonlogicForExcel");
            excelContainer.removeAllItems();
            int levelNumber = getStartLevelNo();
            String hierarchyNo = StringUtils.EMPTY;
            int treeLevelNo = 1;

            List customDetailsList = new ArrayList();
            customDetailsList.add(levelNumber);
            customDetailsList.add(hierarchyNo);
            customDetailsList.add(treeLevelNo);

            List<String> discountToBeLoaded = new ArrayList<String>();
            if (!programSelectionList.isEmpty()) {
                discountToBeLoaded = programSelectionList;
            } else {
                discountToBeLoaded = discountProgramsList;
            }

            List<String> customViewDetails = new ArrayList<String>();
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

                LOGGER.info(" EXCEL P- Custom hierarchy --- \n customId " + customId);
                LOGGER.info(" EXCEL P- customerLevelNo " + customerLevelNo);
                LOGGER.info(" EXCEL P- customerHierarchyNo");
                LOGGER.info(" EXCEL P- productLevelNo " + productLevelNo);
                LOGGER.info(" EXCEL P- productHierarchyNo");
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
                    customDetailsList, true, isCustomHierarchy, rightHeader, 0, 1000, false, false, customViewDetails, false, false, StringUtils.EMPTY, relationshipBuilderSid, false,Collections.EMPTY_LIST,false,StringUtils.EMPTY,StringUtils.EMPTY,Collections.EMPTY_LIST,new HashMap<String,String>());
            loadDataToContainer(list, null, true);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Exit generateButtonlogicForExcel");
    }

    public void loadDataToContainer(List<DiscountProjectionDTO> resultList, Object parentId, boolean isRecursive) {
        try {
            LOGGER.info("Inside loadDataToContainer");
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
        LOGGER.info("Ended loadDataToContainer");
    }

    public void addLowerLevelsForExport(DiscountProjectionDTO dto) {
        LOGGER.info("Inside addLowerLevelsForExport");
        try {

            int treeLevelNo = 0;
            String temphierarchyIndicator = hierarchyIndicator;
            boolean isCustomHierarchy = Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equals(hierarchyIndicator);
            if (isCustomHierarchy) {
                treeLevelNo = dto.getTreeLevelNo() + 1;
            } else {
                treeLevelNo = dto.getLevelNo() + 1;
            }

            LOGGER.info(" dto.getLevelNo() " + dto.getLevelNo());
            List customDetailsList = new ArrayList();

            List<String> discountToBeLoaded = new ArrayList<String>();
            if (!programSelectionList.isEmpty()) {
                discountToBeLoaded = programSelectionList;
            } else {
                discountToBeLoaded = discountProgramsList;
            }

            List<String> customViewDetails = new ArrayList<String>();
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

                LOGGER.info(" EXCEL - Custom hierarchy --- \n customId " + customId);
                LOGGER.info(" EXCEL - customerLevelNo " + customerLevelNo);
                LOGGER.info(" EXCEL - customerHierarchyNo " + customerHierarchyNo);
                LOGGER.info(" EXCEL - productLevelNo " + productLevelNo);
                LOGGER.info(" EXCEL - productHierarchyNo " + productHierachyNo);
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
                    customDetailsList, true, isCustomHierarchy, rightHeader, 0, 1000, false, false, customViewDetails, false, false, StringUtils.EMPTY, relationshipBuilderSid, false,Collections.EMPTY_LIST,false,StringUtils.EMPTY,StringUtils.EMPTY,Collections.EMPTY_LIST,new HashMap<String,String>());
            loadDataToContainer(levelList, dto, true);
            excelTable.setCollapsed(dto, false);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("Exit addLowerLevelsForExport");
    }

    @UiHandler("programSelectionLookup")
    public void programSelectionLookupClickLogic(Button.ClickEvent event) {

        LOGGER.info("Enters the programSelectionLookupClickLogic");

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
                Collections.sort(selectedDiscountList);
                programBean.addAll(selectedDiscountList);
                discountProgramsList = selectedDiscountList;
                discountNamesList = new ArrayList<String>(selectedDiscountList);
                List<String> selectedDiscountNoList = discountSelectionLookupWindow.getSelectedDiscountsNoList();
                discountNoList = new ArrayList<String>(selectedDiscountNoList);
            }
        });
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
        LOGGER.info("Inside generateListView ");
        List<String> discountToBeLoaded = new ArrayList<String>();

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
            checkBoxMap.clear();
            radioMap.clear();
            tableLogic.sinkItemPerPageWithPageLength(false);
            boolean isFrequencyChange = !String.valueOf(projectionSelection.getFrequency()).equals(String.valueOf(frequencyDdlb.getValue()));
            createSelectionDto();
            createRightHeader();
//            viewValueChangeLogic();
            resultBeanContainer.setColumnProperties(rightHeader.getProperties());
            if (isGenerate) {
                resultsTable.constructRightFreeze(isGenerate);
            }
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
        LOGGER.info("Ending generateListView ");

    }

    private void formatTableData() {
        LOGGER.info("Start formatTableData");
        for (Object propertyId : resultsTable.getRightFreezeAsTable().getVisibleColumns()) {
            if (String.valueOf(propertyId).contains("ActualAmount") || String.valueOf(propertyId).contains("ProjectedAmount")) {
                resultsTable.getRightFreezeAsTable().setConverter(propertyId, dollarFormat);
            } else if (String.valueOf(propertyId).contains(Constant.ActualRPU)) {
                resultsTable.getRightFreezeAsTable().setConverter(propertyId, priceFormat);
            } else {
                resultsTable.getRightFreezeAsTable().setConverter(propertyId, percentFormat);
            }
        }
        LOGGER.info("End formatTableData");
    }

    private void loadDataInTable() {
        LOGGER.info("Entering loadDataInTable");
        tableLogic.clearAll();
        List<String> discountToBeLoaded = new ArrayList<String>();
        if (!programSelectionList.isEmpty()) {
            discountToBeLoaded = programSelectionList;
        } else {
            discountToBeLoaded = discountProgramsList;
        }

        int levelNo = getStartLevelNo();
        String relationshipBuilderSid = getRelationshipBuilderSid();
        tableLogic.setDataLoad(true);
        tableLogic.setDiscountVariablesForLogic(session, projectionSelection, startAndEndPeriods, PROGRAM.getConstant().equals(level.getValue()),
                discountToBeLoaded, levelNo, true, rightHeader, hierarchyIndicator, userGroup, currentHierarchy, Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equals(hierarchyIndicator), customId, relationshipBuilderSid);

        LOGGER.info(" User Id --" + session.getUserId());
        LOGGER.info(" Session Id --" + session.getSessionId());
        LOGGER.info("Ending loadDataInTable");
    }

    /**
     * To load data in tree table
     *
     * @param isFrequencyChange - To reload start and end period during
     * frequency changes
     */
    public void loadScreenBasedOnGeneratedTable(boolean isFrequencyChange) {
        LOGGER.info("Inside loadTreeTable ");
        try {
            tripleHeaderForCheckedDoubleHeader.clear();
            checkedDiscountsPropertyIds.clear();
            String fieldValue = String.valueOf(fieldDdlb.getValue());
            List<String> projectedHeaders = rightHeader.getDoubleHeaders();
            if (("Discount Rate").equals(fieldValue)) {

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

            String startYearValue = startFreq.substring(startFreq.length() - 4);
            String endYearValue = endFreq.substring(endFreq.length() - 4);

            int startYear = isInteger(startYearValue) ? Integer.valueOf(startYearValue) : 0;
            int endYear = isInteger(endYearValue) ? Integer.valueOf(endYearValue) : 0;

            startAndEndPeriods.clear();
            if (!projectionSelection.getFrequency().equals(MONTHLY.getConstant())) {
                String startFreqNoValue = startFreq.substring(1, 2);
                String endFreqNoValue = endFreq.substring(1, 2);
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
                String startMonthValue = startFreq.substring(0, startFreq.length() - 5);
                String endMonthValue = endFreq.substring(0, startFreq.length() - 5);
                LOGGER.info(" startMonthValue " + startMonthValue);
                LOGGER.info(" endMonthValue " + endMonthValue);
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
            LOGGER.info(e);
        }
        LOGGER.info("Exiting loadTreeTable ");
    }

    /**
     * To load group Filter Ddlb
     */
    private void loadGroupFilterDdlb() {
        LOGGER.info(" Load group Filter Ddlb ");
        groupFilterDdlb.removeAllItems();
        groupFilterDdlb.addItem(SELECT_ONE.getConstant());
        groupFilterDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        groupFilterDdlb.addItems(valueDdlbBean.getItemIds());

        tableGroupDdlbBean.removeAllItems();
        tableGroupDdlbBean.addBean(Constant.SHOW_ALL_GROUPS);
        tableGroupDdlbBean.addAll(valueDdlbBean.getItemIds());

        LOGGER.info(" End group Filter Ddlb ");
    }

    private void configureRightTable() {
        final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setTripleHeaderVisible(true);
        rightTable.setEditable(true);
        rightTable.setHeight("650px");
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
                    checkBoxMap.put(event.getPropertyId(), false);
                }
                doubleHeaderCheckListener(event.getPropertyId(), event.isChecked());
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
                        if (rightHeader.getSingleColumns().contains(property)) {
                            if (Constant.NULL.equals(fieldvalue)) {
                                DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                                dto.addStringProperties(propertyId, Constant.DASH);
                                container.getContainerProperty(itemId, propertyId).setValue(Constant.DASH);
                            }
                        }

                        if (rightHeader.getSingleProjectedColumns().contains(property)) {

                            TextField textField = new TextField();
                            textField.setWidth(100, Unit.PERCENTAGE);
                            textField.addStyleName(Constant.TXT_RIGHT_ALIGN);
                            if (property.contains("ProjectedRate") || property.contains(Constant.GROWTH)) {
                                textField.setConverter(percentFormat);

                            } else {
                                textField.setConverter(priceFormat);
                            }
                            textField.addFocusListener(new FieldEvents.FocusListener() {
                                @Override
                                public void focus(FieldEvents.FocusEvent event) {
                                    focusValue = String.valueOf(container.getContainerProperty(itemId, propertyId).getValue()).replace(" ", StringUtils.EMPTY);
                                    LOGGER.info(" focus Value" + focusValue);
                                }
                            });

                            textField.addBlurListener(new FieldEvents.BlurListener() {
                                @Override
                                public void blur(FieldEvents.BlurEvent event) {
                                    blurValue = String.valueOf(container.getContainerProperty(itemId, propertyId).getValue()).replace(" ", StringUtils.EMPTY);
                                    if (blurValue.isEmpty()) {
                                        blurValue = Constant.DASH;
                                    }
                                    LOGGER.info(" blur Value" + blurValue);
                                    if (!focusValue.equals(blurValue)) {
                                        final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
                                        DiscountProjectionDTO dto = (DiscountProjectionDTO) itemId;
                                        dto.addStringProperties(propertyId, blurValue);
                                        SaveDTO saveDto = new SaveDTO();

                                        String periodToUpdate = rightTable.getDoubleHeaderColumnHeader(rightTable.getDoubleHeaderForSingleHeader(String.valueOf(propertyId)));
                                        String period = periodToUpdate;
                                        String yearToUpdate = periodToUpdate.substring(periodToUpdate.length() - 4);
                                        periodToUpdate = periodToUpdate.replace(yearToUpdate, StringUtils.EMPTY);
                                        periodToUpdate = CommonUtils.replaceIntegerForMonth(periodToUpdate);
                                        periodToUpdate = periodToUpdate.replace(Constant.Q, StringUtils.EMPTY).replace(Constant.S, StringUtils.EMPTY).replace(" ", StringUtils.EMPTY);
                                        String refreshName = StringUtils.EMPTY;
                                        String property = String.valueOf(propertyId);
                                        if (property.contains("ProjectedRate")) {
                                            refreshName = Constant.RATE;
                                            isRateUpdatedManually = true;
                                        } else if (property.contains(Constant.ProjectedRPU)) {
                                            refreshName = "RPU";
                                            isRPUUpdatedManually = true;
                                        } else if (property.contains("ProjectedAmount")) {
                                            refreshName = "AMOUNT";
                                            isAmountUpdatedManually = true;
                                            double doubleVal = Double.valueOf(blurValue);
                                            ccpsCount = dto.getCcpCount();
                                            double finalValue = doubleVal / ccpsCount;
                                            blurValue = String.valueOf(finalValue);
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
                                        saveDto.setDiscountName(rightTable.getTripleHeaderColumnHeader(rightTable.getTripleHeaderForSingleHeader(String.valueOf(propertyId))));
                                        saveDto.setRefreshName(refreshName);
                                        refreshTableHierarchySet.add(tableLogic.getTreeLevelonCurrentPage(itemId));
                                        saveList.add(saveDto);

                                        if (manualEntryMap.isEmpty()) {
                                            manualEntryMap.put(period, refreshName);
                                            if (saveList.size() == 1) {
                                                saveDiscountProjectionListview();
                                            }
                                        } else if (manualEntryMap.containsKey(period) ? manualEntryMap.get(period).equals(refreshName) : false) {
                                            if (saveList.size() == 1) {
                                                saveDiscountProjectionListview();
                                            }
                                        } else if (!manualEntryMap.containsKey(period)) {
                                            manualEntryMap.put(period, refreshName);
                                            if (saveList.size() == 1) {
                                                saveDiscountProjectionListview();
                                            }
                                        } else {
                                            AbstractNotificationUtils.getErrorNotification("Multiple Variables Updated", "Multiple variables for the same customer/product/time period combination have been changed.  Please only change one variable for a single customer/product/time period combination.");
                                            container.getContainerProperty(itemId, propertyId).setValue(focusValue);
                                        }

                                        LOGGER.info(" periodToUpdate " + periodToUpdate);
                                        LOGGER.info(" yearToUpdate " + yearToUpdate);
                                        LOGGER.info(" hierarchyIndicator " + hierarchyIndicator);
                                        LOGGER.info(" hierarchyNo " + dto.getHierarchyNo());
                                        LOGGER.info(" discountName " + saveDto.getDiscountName());
                                        LOGGER.info(" discountRateValue " + saveDto.getValue());
                                        LOGGER.info(" TreeLevelNo " + saveDto.getTreeLevelNo());
                                        LOGGER.info(" CustomerHierarchyNo " + saveDto.getCustomerHierarchyNo());
                                        LOGGER.info(" ProductHierarchyNo " + saveDto.getProductHierarchyNo());
                                    }
                                }
                            });
                            return textField;
                        }
                        if (rightHeader.getSingleForecastColumns().contains(property)) {
                            TextField textField = new TextField();
                            textField.setWidth(100, Unit.PERCENTAGE);
                            textField.addStyleName(Constant.TXT_RIGHT_ALIGN);
                            if (property.contains("ProjectedRate") || property.contains(Constant.GROWTH)) {
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

    }

    /**
     * To save Discount Projection Data
     */
    public void saveDiscountProjectionListview() {
        LOGGER.info(" Inside Save ");
        if (isListviewGenerated) {
            LOGGER.info(" Discount generated ");
            boolean isCustomHierarchy = Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equals(hierarchyIndicator);
            String relationshipBuilderSid = getRelationshipBuilderSid();
            boolean isProgram = PROGRAM.getConstant().equals(level.getValue());
            logic.saveDiscountProjectionListView(session, projectionSelection.getFrequency(), saveList, customId, isProgram, isCustomHierarchy, relationshipBuilderSid);
            saveList.clear();
        }
        LOGGER.info(" Exiting  Save ");
    }

    /**
     * To save the Discount projection selections
     */
    public void saveSelections() {
        LOGGER.info(" Entering Save Selection ");
        Map map = new HashMap();
        try {
            map.put(Constant.FREQUENCY, projectionSelection.getFrequency());
            map.put(Constant.HISTORY, projectionSelection.getHistory());
            map.put("Projection Period Order", projectionSelection.getProjectionOrder());
            map.put("Actuals / projections", projectionSelection.getActualsOrProjections());
            map.put("Level", String.valueOf(level.getValue()));
            map.put("Selected Discounts", CommonUtils.CollectionToString(discountProgramsList, false));
            map.put("selectedDiscountNo", CommonUtils.CollectionToString(discountNoList, false));
            map.put("Program Selection Ddlb", String.valueOf(programSelection.getValue()));
            map.put("year Selection Ddlb", projectionSelection.getYear());
            CommonLogic.saveProjectionSelection(map, session.getProjectionId(), TAB_DISCOUNT_PROJECTION.getConstant());
        } catch (Exception e) {
            LOGGER.error(e);
        }

        LOGGER.info(" Ending Save Selection ");
    }

    /**
     * To save the discount projection screen including the Selections and list
     * view
     *
     * @param toBeRefreshed
     */
    public void saveDiscountProjectionScreen(boolean toBeRefreshed) {
        LOGGER.info(" saving DP screen");
        try {
            if (isListviewGenerated) {
//                saveSelections();
                saveDiscountProjectionListview();
                if (toBeRefreshed) {
                    refreshTableData(getManualEntryRefreshHierarachyNo());
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info(" saving DP screen over");
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
        String discountName = rightTable.getTripleHeaderColumnHeader(discountPropertyId);

        Map<String, List<String>> checkedDoubleHeaders = tripleHeaderForCheckedDoubleHeader.get(discountName);

        if (checkedDoubleHeaders == null) {
            checkedDoubleHeaders = new HashMap<String, List<String>>();
        }

        List<String> checkedHistoryList = checkedDoubleHeaders.get("H");
        List<String> checkedProjectionList = checkedDoubleHeaders.get(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);

        if (checkedHistoryList == null) {
            checkedHistoryList = new ArrayList<String>();
        }
        if (checkedProjectionList == null) {

            checkedProjectionList = new ArrayList<String>();
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
            tripleHeaderForCheckedDoubleHeader.remove(discountName);
        }

        if (checkedDoubleHeaders.isEmpty()) {
            tripleHeaderForCheckedDoubleHeader.remove(discountName);
        }

    }

    private void createSelectionDto() {
        LOGGER.info(" Entering Selection Dto Creation");
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
                    historyNum = Integer.valueOf(hist.replace("Semi-Annual", StringUtils.EMPTY).trim());
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

        List<String> discountToBeLoaded = new ArrayList<String>();
        if (!programSelectionList.isEmpty()) {
            discountToBeLoaded = programSelectionList;
        } else {
            discountToBeLoaded = discountProgramsList;
        }

        projectionSelection.setDiscountProgramsList(new ArrayList<String>(discountToBeLoaded));
        Collection c = (Collection) variables.getValue();
        List<String> l = new ArrayList();
        for (Object s : c) {
            l.add(String.valueOf(s));
        }
        projectionSelection.setdPVariablesList(l);
        if (l.isEmpty()) {
            projectionSelection.setdPVariablesList(Arrays.asList(new String[]{DISCOUNT_RATE.getConstant(), REBATE_PER_UNIT.getConstant(), DISCOUNT_AMT.getConstant()}));

        }
        LOGGER.info(" Ending Selection Dto Creation");
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
            }

            @Override
            public void yesMethod() {

                if (frequencyDdlb.getValue().equals(MONTHLY.getConstant())) {
                    historyDdlb.setValue(12);
                } else if (frequencyDdlb.getValue().equals(QUARTERLY.getConstant())) {
                    historyDdlb.setValue(4);
                } else if (frequencyDdlb.getValue().equals(ANNUALLY.getConstant())) {
                    historyDdlb.setValue(1);
                } else {
                    historyDdlb.setValue(2);
                }
                programBean.addItem(SELECT_ONE.getConstant());
                programSelection.setNullSelectionAllowed(true);
                programSelection.setNullSelectionItemId(SELECT_ONE.getConstant());
                programSelection.setValue(SELECT_ONE.getConstant());
                programSelection.setImmediate(true);
                final BeanItemContainer<String> yearBean = new BeanItemContainer<String>(String.class);
                yearSelection.setNullSelectionAllowed(true);
                yearSelection.setNullSelectionItemId(SELECT_ONE.getConstant());
                yearBean.addAll(loadYearSelection());
                yearSelection.setContainerDataSource(yearBean);
                level.setValue(PROGRAM_CATEGORY.getConstant());
                frequencyDdlb.setValue(QUARTERLY.getConstant());
                periodOrder.select(ASCENDING.getConstant());
                actualsProjs.select(ACTUALS.getConstant());
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
        LOGGER.info("levelFilterDdlbvalueChangeListener initiated ");
        if (enableLevelFilterListener) {
            if (event.getProperty() != null && !Constant.NULL.equals(String.valueOf(event.getProperty().getValue()))) {
                LOGGER.info(" event value " + event.getProperty().getValue());
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
        }
        LOGGER.info("levelFilterDdlbvalueChangeListener ended ");
    }

    @Override
    protected void massCheckValueChangeLogic(Property.ValueChangeEvent event) {
        String fieldValue = String.valueOf(event.getProperty().getValue());
        LOGGER.info(" fieldValue " + fieldValue);
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
        LOGGER.info(" Start period  " + fieldValue);
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
        LOGGER.info(" startPeriod " + startPeriod);
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
        LOGGER.info("customDdlbChangeOption ValueChangeEvent initiated ");
        groupFilterDdlb.setEnabled(true);
        customId = CommonLogic.customDdlbOptionChange(viewDdlb, editBtn, levelDdlb);
        levelDdlb.setEnabled(customId != 0);
        currentHierarchy = CommonLogic.getCustomTree(customId);
        LOGGER.info(" customId  " + customId);
        LOGGER.info(" currentHierarchy " + currentHierarchy.size());
        if (customId != 0) {
            viewChangeGenerate();
        } else {
            tableLogic.clearAll();
            tableLogic.getControlTable().getContainerDataSource().removeAllItems();
        }
        LOGGER.info("customDdlbChangeOption ValueChangeEvent ends ");
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
            final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, getCommercialConstant() + "," + UISecurityUtil.DISCOUNT_PROJECTION);
            if (!(functionPsHM.get(CommonUtils.GENERATE_BUTTON_SALES) != null && ((AppPermission) functionPsHM.get(CommonUtils.GENERATE_BUTTON_SALES)).isFunctionFlag())) {
                generateBtn.setVisible(false);
                populateBtn.setVisible(false);
                expandBtn.setVisible(false);
                collapseBtn.setVisible(false);
                newBtn.setVisible(false);
                editBtn.setVisible(false);
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
                    if (i == 2 && periodCalc.size() < i) {
                        return false;
                    }
                    if (checkedHistoryList != null) {
                        if (i == 0 && checkedHistoryList.size() == i && checkedProjList1.isEmpty()) {
                            return false;
                        }
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
        checkedList = new ArrayList<String>();
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
        LOGGER.info("Inside get Quater with String" + str);
        int a[] = new int[2];
        String[] splited = str.split("\\s+");
        a[0] = Integer.valueOf(new String(splited[0].replaceAll("[Q]+", StringUtils.EMPTY)));
        a[1] = Integer.valueOf(splited[1]);
        return a;
    }

    public boolean endDateValidation(String valueEnd) {
        LOGGER.info("Inside End Date Validation");
        if (startPeriodForecastTab.getValue() != null) {
            if (frequencyDdlb.getValue().equals(MONTHLY.getConstant())) {
                int endMonth = CommonUtils.getIntegerForMonth(valueEnd.substring(0, 3));
                int startMonth = CommonUtils.getIntegerForMonth(startPeriodForecastTab.getValue().toString().trim().substring(0, 3));
                int endYear = Integer.valueOf(valueEnd.substring(4, 8));
                int startYear = Integer.valueOf(startPeriodForecastTab.getValue().toString().trim().substring(4, 8));
                
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
            AbstractNotificationUtils.getErrorNotification("No Start Period selected", "Please select a Start Period.");
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
        for (Object key : radioMap.keySet()) {
            String value = radioMap.get(key);
            if (checkBoxMap.get(key)) {
                if (value.contains("ActualRate")) {
                    tempSalesvalue = true;
                    calc = Constant.RATE;
                }

                if (value.contains("ActualAmount")) {
                    tempUnitValue = true;
                    calc = "AMOUNT";
                }
                if (value.contains(Constant.ActualRPU)) {
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

    private void loadMonthMap(Map<String, String> monthMap) {
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
            Map<String, String> monthMap = new HashMap<String, String>();
            int defval = 0;
            String frequency = String.valueOf(frequencyDdlb.getValue().toString()).trim();
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                defval = 2;
            } else if (frequency.equals(QUARTERLY.getConstant())) {
                defval = 4;
            } else if (frequency.equals(MONTHLY.getConstant())) {
                defval = 12;
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
                int[] Quarter = new int[overall.size()];
                String tempYear = StringUtils.EMPTY, tempSubYear = StringUtils.EMPTY;
                for (int i = 0; i < overall.size(); i++) {
                    if (defval == 2 || defval == 4) {
                        tempYear = overall.get(i).toString().trim().substring(2);
                        tempSubYear = (overall.get(i).toString().trim()).replace(tempYear, StringUtils.EMPTY).trim();
                    } else if (defval == 12) {
                        tempYear = overall.get(i).toString().trim().substring(3);
                        String tmpSubYear = overall.get(i).toString().trim().replace(tempYear, StringUtils.EMPTY).trim();
                        tempSubYear = monthMap.get(tmpSubYear);
                    }
                    String subYear1 = StringUtils.EMPTY;
                    if (defval == 2) {
                        subYear1 = tempSubYear.replace(Constant.S, StringUtils.EMPTY);
                    } else if (defval == 4) {
                        subYear1 = tempSubYear.replace(Constant.Q, StringUtils.EMPTY);
                    }
                    if (defval != 1) {
                        String fullYear = tempYear + subYear1;
                        year[i] = Integer.valueOf(fullYear.trim());
                    } else {
                        year[i] = Integer.valueOf(overall.get(i).toString().trim());
                    }

                }
                int i = 0;
                Arrays.sort(year);
                String startTempYear = StringUtils.EMPTY, startTempSubYear = StringUtils.EMPTY;
                String endTempYear = StringUtils.EMPTY, endTempSubYear = StringUtils.EMPTY;
                if (defval == 2 || defval == 4) {
                    startTempYear = startPeriod.toString().trim().substring(2);
                    startTempSubYear = startPeriod.replace(startTempYear, StringUtils.EMPTY).trim();
                    endTempYear = endPeriod.toString().trim().substring(2);
                    endTempSubYear = endPeriod.replace(endTempYear, StringUtils.EMPTY).trim();
                } else if (defval == 12) {
                    startTempYear = startPeriod.toString().toString().trim().substring(3);
                    String startTmpSubYear = startPeriod.replace(startTempYear, StringUtils.EMPTY).trim();
                    startTempSubYear = monthMap.get(startTmpSubYear);
                    endTempYear = endPeriod.toString().trim().substring(3);
                    String endTmpSubYear = endPeriod.toString().replace(endTempYear, StringUtils.EMPTY).trim();
                    endTempSubYear = monthMap.get(endTmpSubYear);
                } else if (defval == 1) {
                    startTempYear = startPeriod.trim();
                    endTempYear = endPeriod.toString().trim();
                }
                String subYear2 = StringUtils.EMPTY, subYear3 = StringUtils.EMPTY;
                if (defval == 2) {
                    subYear2 = startTempSubYear.replace(Constant.S, StringUtils.EMPTY);
                    subYear3 = endTempSubYear.replace(Constant.S, StringUtils.EMPTY);
                } else if (defval == 4) {
                    subYear2 = startTempSubYear.replace(Constant.Q, StringUtils.EMPTY);
                    subYear3 = endTempSubYear.replace(Constant.Q, StringUtils.EMPTY);
                } else if (defval == 12) {
                    subYear2 = startTempSubYear;
                    subYear3 = endTempSubYear;
                }
                String startfullYear = startTempYear + subYear2;
                String endfullYear = endTempYear + subYear3;
                int finStartPeriod = 0, finEndPeriod = 0;
                finStartPeriod = Integer.valueOf(startfullYear.trim());
                finEndPeriod = Integer.valueOf(endfullYear.trim());
                if (year[year.length - 1] > finEndPeriod) {
                    baselineFlag = 2;
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
            Map<String, String> monthMap = new HashMap<String, String>();
            int defval = 0;
            String frequency = String.valueOf(frequencyDdlb.getValue().toString()).trim();
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                defval = 2;
            } else if (frequency.equals(QUARTERLY.getConstant())) {
                defval = 4;
            } else if (frequency.equals(MONTHLY.getConstant())) {
                defval = 12;
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
            if (!overall.isEmpty() && overall.size() == defval) {
                int[] year = new int[overall.size()];
                int[] Quarter = new int[overall.size()];
                String tempYear = StringUtils.EMPTY, tempSubYear = StringUtils.EMPTY;
                for (int i = 0; i < overall.size(); i++) {
                    if (defval == 2 || defval == 4) {
                        tempYear = overall.get(i).toString().trim().substring(2);
                        tempSubYear = (overall.get(i).toString().trim()).replace(tempYear, StringUtils.EMPTY).trim();
                    } else if (defval == 12) {
                        tempYear = overall.get(i).toString().trim().substring(3);
                        String tmpSubYear = overall.get(i).toString().trim().replace(tempYear, StringUtils.EMPTY).trim();
                        tempSubYear = monthMap.get(tmpSubYear);
                    } else if (defval == 1) {
                        tempYear = overall.get(i).trim();
                    }
                    String subYear1 = StringUtils.EMPTY;
                    if (defval == 2) {
                        subYear1 = tempSubYear.replace(Constant.S, StringUtils.EMPTY);
                    } else if (defval == 4) {
                        subYear1 = tempSubYear.replace(Constant.Q, StringUtils.EMPTY);
                    } else if (defval == 12) {
                        subYear1 = tempSubYear;
                    }
                    String fullYear = tempYear + subYear1;
                    year[i] = Integer.valueOf(fullYear.trim());

                }
                int i = 0;
                Arrays.sort(year);
                for (i = 0; i < year.length; i++) {
                    Quarter[i] = year[i] % 10;
                    year[i] = year[i] / 10;
                }
                if ((Quarter[0] == 1 && Quarter[defval - 1] == defval) && (year[0] == year[defval - 1])) {
                    retval = true;
                } else if ((Quarter[0] == (Quarter[defval - 1] + 1)) && (year[0] == (year[defval - 1] - 1))) {
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

    private List<String> getProjectedList(List<String> checkedProjList) {
        List<String> tempCheckedProjList = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        DateFormat dateFormat1 = new SimpleDateFormat(Constant.MM);
        Date date = new Date();
        String strMonth = Constant.DASH;
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
            divval = 6;
            defval = 2;
            month = month + divval;
        } else if (frequency.equals(QUARTERLY.getConstant())) {
            divval = 3;
            defval = 4;
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
        Map<String, String> monthMap = new HashMap<String, String>();
        if (frequency.equals(MONTHLY.getConstant())) {
            defval = 12;
            loadMonthMap(monthMap);
        }
        if (checkedProjList != null) {
            int[] year = new int[checkedProjList.size()];
            int[] Quarter = new int[checkedProjList.size()];
            String tempYear = StringUtils.EMPTY, tempSubYear = StringUtils.EMPTY;
            for (int i = 0; i < checkedProjList.size(); i++) {
                if (defval == 2 || defval == 4) {
                    tempYear = checkedProjList.get(i).toString().trim().substring(2);
                    tempSubYear = (checkedProjList.get(i).toString().trim()).replace(tempYear, StringUtils.EMPTY).trim();
                } else if (defval == 12) {
                    tempYear = checkedProjList.get(i).toString().trim().substring(3);
                    String tmpSubYear = checkedProjList.get(i).toString().trim().replace(tempYear, StringUtils.EMPTY).trim();
                    tempSubYear = monthMap.get(tmpSubYear);
                } else if (defval == 1) {
                    tempYear = checkedProjList.get(i).trim();
                }
                String subYear1 = StringUtils.EMPTY;
                if (defval == 2) {
                    subYear1 = tempSubYear.replace(Constant.S, StringUtils.EMPTY);
                } else if (defval == 4) {
                    subYear1 = tempSubYear.replace(Constant.Q, StringUtils.EMPTY);
                } else if (defval == 12) {
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
        double finalValue = doubleVal / ccpsCount;
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
            LOGGER.info(" userGroup-in tableGroupFilterDdlbValueChange-" + userGroup);
            loadDataInTable();
        }
    };

    @Override
    protected void resetBtnForTableLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
            }

            @Override
            public void yesMethod() {
                NonMandatedLogic logic1 = new NonMandatedLogic();
                try {
                    if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction())) {
                        LOGGER.info("Reset the temp table by deleting and inserting ");
                        logic1.deleteTempData(session, "DISC_DELETE_TEMP");
                        logic1.tempInsertReturns(session, "DISC_EDIT_TEMP_INSERT_QUERY");
                        generateListView(true);
                    } else if (Constants.CommonConstants.ACTION_GENERATE.getConstant().equalsIgnoreCase(session.getAction())) {
                        LOGGER.info("Reset the temp table by deleting and inserting by proc");
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
        for (Object o : checkBoxMap.keySet()) {
            if (checkBoxMap.get(String.valueOf(o)) == true) {
                propertyId = true;
            }
        }
        return propertyId;
    }

    private void callResetTableLogic() {
        List<String> discountToBeLoaded = new ArrayList<String>();
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
                Set<Integer> totalCcp = new HashSet<Integer>();
                if (CUSTOMER.getConstant().equals(String.valueOf(view.getValue()))) {
                   
                    int numoFCustomers = getCheckedCustomercount(logic);
                    
                    if (numoFCustomers > 0) {
                        String ccpIds = logic.getCCPList(logic.buildCCPListQuery(true, session.getProjectionId(), session.getUserId(), session.getSessionId()), totalCcp);
                        totalccpCount = totalCcp.size();
                       
                        if (isNotHavingActuals(true, logic, ccpIds, rsModelSid, totalCcp)) {
                          
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
                        String ccpIds = logic.getCCPList(logic.buildCCPListQuery(false, session.getProjectionId(), session.getUserId(), session.getSessionId()), totalCcp);
                        totalccpCount = totalCcp.size();
                        if (isNotHavingActuals(false, logic, ccpIds, rsModelSid, totalCcp)) {

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

    public boolean isNotHavingActuals(boolean isCustomer, NMDiscountProjectionLogic logic, String ccpIds, int rsModelSid, Set<Integer> totalccp) {
        actualCCPs = logic.getZeroActualCCPList(logic.buildActualValidateQuery(ccpIds, rsModelSid), totalccp);
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
        return logic.getCCPCount(logic.buildCCPCountQuery(true, session.getProjectionId(), session.getUserId(), session.getSessionId()));
    }

    public int getCheckedProductCount(final NMDiscountProjectionLogic logic) {
        return logic.getCCPCount(logic.buildCCPCountQuery(false, session.getProjectionId(), session.getUserId(), session.getSessionId()));
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
        return StringUtils.isEmpty(hierarchyNo) ? StringUtils.EMPTY : hierarchyNo.substring(0, (hierarchyNo.length() - 1));
    }
}
