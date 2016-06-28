/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.discountProjection.form;

import com.stpl.addons.tableexport.ExcelExport;
import static com.stpl.app.utils.Constants.LabelConstants.BOTH;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import com.vaadin.ui.CustomComponent;
import org.vaadin.teemu.clara.Clara;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.discountProjection.logic.tableLogic.NMDiscountTableLoadLogic;
import com.stpl.app.galforecasting.dto.DiscountProjectionDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.dto.SaveDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.logic.DiscountProjectionLogic;
import com.stpl.app.galforecasting.logic.NonMandatedLogic;
import com.stpl.app.galforecasting.projectionvariance.logic.NMProjectionVarianceLogic;
import com.stpl.app.galforecasting.discountProjection.logic.tableLogic.AHSummeryDiscountTableLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import static com.stpl.app.galforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.HeaderUtils;
import com.stpl.app.galforecasting.utils.NotificationUtils;
import com.stpl.app.galforecasting.utils.TabNameUtil;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.ButtonConstants.ALL;
import static com.stpl.app.utils.Constants.CalendarConstants.CURRENT_YEAR;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_EDIT;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOM;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOMER;
import static com.stpl.app.utils.Constants.LabelConstants.DESCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_DISCOUNT_PROJECTION;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author maheshj
 */
public class AltSummeryDiscount extends CustomComponent {

    protected SessionDTO session;
    protected String screenName;

    /* The Excel table */
    protected ExtFilterTreeTable excelTable = new ExtFilterTreeTable();
    /* The excel export image */
    protected final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());

    /**
     * The resultsTableLayout VerticalLayout.
     */
    @UiField("resultsTableLayout1")
    protected VerticalLayout resultsTableLayout;
    /**
     * The excelTableLayout VerticalLayout.
     */
    @UiField("excelTableLayout")
    protected VerticalLayout excelTableLayout;
    /**
     * The viewHlayout HorizontalLayout.
     */
    @UiField("viewHlayout")
    protected HorizontalLayout viewHlayout;

    /**
     * The Non Mandated Panel.
     */
    @UiField("panelNM")
    protected Panel panelNM;

    /**
     * The frequency ddlb.
     */
    @UiField("frequencyDdlb")
    protected ComboBox frequencyDdlb;
    /**
     * The history Ddlb.
     */
    @UiField("historyDdlb")
    protected ComboBox historyDdlb;

    /**
     * The periodOrder OptionGroup.
     */
    @UiField("periodOrder")
    protected OptionGroup periodOrder;
    /**
     * The periodOrder OptionGroup.
     */
    @UiField("actualsProjs")
    protected OptionGroup actualsProjs;
    @UiField("pivotView")
    protected OptionGroup pivotView;

    @UiField("levelDdlb")
    protected ComboBox levelDdlb;
    /**
     * The levelFilterDdlb ComboBox.
     */
    @UiField("levelFilterDdlb")
    protected ComboBox levelFilterDdlb;

    /**
     * The viewDdlb ComboBox.
     */
    @UiField("viewDdlb")
    protected ComboBox viewDdlb;

    /**
     * The adjperiods OptionGroup.
     */
    @UiField(Constant.VIEW)
    protected OptionGroup view;

    /**
     * The generate Button.
     */
    @UiField("generateBtn")
    protected Button generateBtn;
    /**
     * The editBtn Button.
     */
    @UiField("editBtn")
    protected Button editBtn;
    /**
     * The newBtn Button.
     */
    @UiField("newBtn")
    protected Button newBtn;
    /**
     * The excelExport Button.
     */
    @UiField("excelExport")
    protected Button excelExport;

    /**
     * The reset Button.
     */
//    @UiField("resetBtnForTable")
//    protected Button resetBtnForTable;
    /**
     * The reset Button.
     */
    @UiField("resetBtn")
    protected Button resetBtn;
    /**
     * The expandBtn Button.
     */
    @UiField("expandBtn")
    protected Button expandBtn;
    /**
     * The collapseBtn Button.
     */
    @UiField("collapseBtn")
    protected Button collapseBtn;

    /**
     * The bean for loading Start Period drop down.
     */
    final protected BeanItemContainer<String> startPeriodBean = new BeanItemContainer<String>(String.class);
    /**
     * The bean for loading End Period drop down.
     */
    final protected BeanItemContainer<String> endPeriodBean = new BeanItemContainer<String>(String.class);
    /**
     * The bean for loading Start Period drop down.
     */
    final protected BeanItemContainer<String> forecaststartBean = new BeanItemContainer<String>(String.class);
    /**
     * The bean for loading End Period drop down.
     */
    final protected BeanItemContainer<String> forecastendBean = new BeanItemContainer<String>(String.class);

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
    AHSummeryDiscountTableLogic tableLogic;
    /* To hold the selections on generate button click. */
    ProjectionSelectionDTO projectionSelection = new ProjectionSelectionDTO();
    /* The custom id. */
    int customId = 0;
    /* To check whether list view is generated or not */
    public boolean isListviewGenerated = false;
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
    private static final Logger LOGGER = Logger.getLogger(AltSummeryDiscount.class);

    private boolean isDiscountGenerated;
    boolean isRateUpdatedManually = false;
    boolean isRPUUpdatedManually = false;
    boolean isAmountUpdatedManually = false;
    BeanItemContainer<String> tableGroupDdlbBean = new BeanItemContainer<>(String.class);
    NMDiscountTableLoadLogic discountTableLogic;
    private boolean initialLoad = true;
    String rsName = StringUtils.EMPTY;

    public AltSummeryDiscount(SessionDTO session, String screenName, ProjectionSelectionDTO projectionSelection, NMDiscountTableLoadLogic discountTableLogic, String rsName) throws Exception {

        this.session = session;
        this.screenName = screenName;
        this.rsName = rsName;
        this.projectionSelection = projectionSelection;
        this.discountTableLogic = discountTableLogic;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nonmandated/AHDiscountSummery.xml"), this));

        configureFields();
        tableLogic = new AHSummeryDiscountTableLogic(this);
        tableLogic.setTempPageLength(10);
        tableLogic.setDataLoad(false);
        resultsTable = new FreezePagedTreeTable(tableLogic);
        tableLogic.setPageLength(15);        
        tableLogic.sinkItemPerPageWithPageLength(false);
        getContent();
    }

    public void configureFields() throws Exception {
        frequencyDdlb.addItem(SELECT_ONE.getConstant());
        frequencyDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        frequencyDdlb.addItem(MONTHLY.getConstant());
        frequencyDdlb.addItem(QUARTERLY.getConstant());
        frequencyDdlb.addItem(SEMI_ANNUALLY.getConstant());
        frequencyDdlb.addItem(ANNUALLY.getConstant());
        frequencyDdlb.setValue(QUARTERLY.getConstant());
        frequencyDdlb.focus();

        loadFrequency(QUARTERLY.getConstant());
        historyDdlb.setNullSelectionAllowed(false);
        historyDdlb.setValue("4");

        frequencyDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(Property.ValueChangeEvent event) {
                loadFrequency(frequencyDdlb.getValue().toString());
            }
        });

        periodOrder.addItem(ASCENDING.getConstant());
        periodOrder.addItem(DESCENDING.getConstant());
        periodOrder.select(ASCENDING.getConstant());

        actualsProjs.addItem(ACTUALS.getConstant());
        actualsProjs.addItem(PROJECTIONS.getConstant());
        actualsProjs.addItem(BOTH.getConstant());
        actualsProjs.select(BOTH.getConstant());
        view.addItem(CUSTOMER.getConstant());
        view.addItem(PRODUCT.getConstant());
        view.addItem(CUSTOM.getConstant());
        view.select(CUSTOMER.getConstant());

        view.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                viewValueChangeLogic();
            }
        });

        levelDdlb.addItem(SELECT_ONE.getConstant());
        levelDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        levelDdlb.setValue(SELECT_ONE.getConstant());
        levelDdlb.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        levelDdlb.setImmediate(true);
        levelFilterDdlb.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        levelFilterDdlb.setImmediate(true);
        levelFilterDdlb.addItem(SELECT_ONE.getConstant());
        levelFilterDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        levelFilterDdlb.setValue(SELECT_ONE.getConstant());

        viewDdlb.addItem(SELECT_ONE.getConstant());
        viewDdlb.setNullSelectionItemId(SELECT_ONE.getConstant());
        viewDdlb.setValue(SELECT_ONE.getConstant());
        viewDdlb.setEnabled(false);
         pivotView.addItem("Period");
        pivotView.addItem("Variable");
        pivotView.select("Period");
        pivotView.setImmediate(true);
        editBtn.setEnabled(false);
        newBtn.setEnabled(false);

        excelExport.setIcon(excelExportImage);
        excelTable.setVisible(false);

        excelExport.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                excelExportClickLogic();
            }
        });
        generateBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                generateBtnClickLogic(true);
            }
        });
        resetBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                resetBtnClickLogic();
            }
        });
//        resetBtnForTable.addClickListener(new Button.ClickListener() {
//
//            @Override
//            public void buttonClick(Button.ClickEvent event) {
//                resetBtnForTableLogic();
//            }
//        });
        editBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                editBtnClickLogic();
            }
        });
        newBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                newBtnClickLogic();
            }
        });

        expandBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                expandBtnClickLogic();
            }
        });
        collapseBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                collapseBtnClickLogic();
            }
        });

        levelFilterDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                levelFilterValueChangeLogic(event);
            }
        });

    }

    private void loadFrequency(String frequency) {
        CommonUtils.frequenceValueChange(frequency, historyDdlb, session);
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

    @UiHandler("viewDdlb")
    public void customDdlbChangeOption(Property.ValueChangeEvent event) {
        customDdlbLogic();
    }

    public void getContent() {
        LOGGER.info("Inside getContent " + session.getAction());
        configureFeildsForNm();

    }

    void configureFeildsForNm() {

        final BeanItemContainer<String> yearBean = new BeanItemContainer<String>(String.class);
        newBtn.setEnabled(true);
        /* To load the Customer hierarchy initially */
        int hierarchyLevelNo = isInteger(session.getCustomerLevelNumber()) ? Integer.valueOf(session.getCustomerLevelNumber()) : 0;
        currentHierarchy = CommonLogic.getCustomerHierarchy(session.getProjectionId(), hierarchyLevelNo);
        hierarchyIndicator = "C";

        tableLogic.setTreeNodeMultiClick(false);
        tableLogic.setDataLoad(false);
        tableLogic.setPageLength(15);
        tableLogic.setItemsPerPage(20);
        initializeTable();
        addResultTable();
        createLeftHeader();
        resultBeanContainer.setColumnProperties(leftHeader.getProperties());

        tableLogic.setContainerDataSource(resultBeanContainer);
        configureLeftTable();

        LOGGER.info(" session Action " + session.getAction());

        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {

            generateBtnClickLogic(false);

        } else {
            loadEmptyTable();
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
                historyDdlb.select(Integer.parseInt(String.valueOf(map.get(Constant.HISTORY))));
                periodOrder.select(map.get("Projection Period Order"));
                actualsProjs.select(map.get("Actuals / projections"));
                discountProgramsList = new LinkedList<String>(Arrays.asList(String.valueOf(map.get("Selected Discounts")).split("\\s*,\\s*")));
                discountNamesList = new LinkedList<String>(discountProgramsList);
                discountNoList = new LinkedList<String>(Arrays.asList(String.valueOf(map.get("selectedDiscountNo")).split("\\s*,\\s*")));
                programBean.removeAllItems();
                programBean.addItem(SELECT_ONE.getConstant());
                Collections.sort(discountProgramsList);
                programBean.addAll(discountProgramsList);

                frequencyDdlb.focus();
                isListviewGenerated = true;
                return true;
            }
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
//            resultsTable.setHeight("650px");
//            resultsTable.setWidth("100%");
            resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending configureTable ");
    }

    /**
     * To get the hierarchy numbers affected during the manual entry
     *
     * @return
     */
    private Set<String> getManualEntryRefreshHierarachyNo() {
        Set<String> finalHierarchyNo = new HashSet<String>();
        for (String hirarechyNo : refreshTableHierarchySet) {
            finalHierarchyNo.addAll(tableLogic.getAllParentLevels(hirarechyNo));
            finalHierarchyNo.addAll(tableLogic.getAllChildLevels(hirarechyNo));
        }
        refreshTableHierarchySet.clear();
        return finalHierarchyNo;
    }

    /**
     * To add Results table in UI
     */
    private void addResultTable() {
        resultsTableLayout.addComponent(resultsTable);

        tableLogic.setPageLength(15);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);

        HorizontalLayout controls = tableLogic.createControls();
        HorizontalLayout cssControls = CommonLogic.getResponsiveControls(controls);
        cssControls.addStyleName(Constant.RESPONSIVE_PAGED_TABLE);
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
                    String caption = Constant.LEVEL+ levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                    levelFilterDdlb.addItem(itemId);
                    levelFilterDdlb.setItemCaption(itemId, caption);
                   int levelSize="Variable".equalsIgnoreCase(String.valueOf(pivotView.getValue()))?currentHierarchy.size():currentHierarchy.size() - 1;
                    if (i <levelSize ) {
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

    protected void viewValueChangeLogic() {
        viewDdlb.setEnabled(false);
        editBtn.setEnabled(false);
        newBtn.setEnabled(false);

        if (view.getValue() != null) {
            if (CUSTOM.getConstant().equals(String.valueOf(view.getValue()))) {
                 newBtn.setEnabled(true);
                hierarchyIndicator = "CP";
                if(customId!=0){
                loadCustomDDLB();
                currentHierarchy = CommonLogic.getCustomTree(customId);
                levelFilterDdlb.setEnabled(false);

                resultsTable.getLeftFreezeAsTable().setColumnCollapsingAllowed(true);
                resultsTable.getLeftFreezeAsTable().setColumnCollapsed(Constant.GROUP, false);
                } else {
                    tableLogic.clearAll();
                    tableLogic.getControlTable().getContainerDataSource().removeAllItems();
                }
            } else if (CUSTOMER.getConstant().equals(String.valueOf(view.getValue()))) {
                customIdToSelect = customId;
                int hierarchyLevelNo = isInteger(session.getCustomerLevelNumber()) ? Integer.valueOf(session.getCustomerLevelNumber()) : 0;
                currentHierarchy = CommonLogic.getCustomerHierarchy(session.getProjectionId(), hierarchyLevelNo);
                hierarchyIndicator = "C";
                levelDdlb.setEnabled(true);
                levelFilterDdlb.setEnabled(true);
                expandBtn.setEnabled(true);
                collapseBtn.setEnabled(true);
                viewChangeGenerate();
                levelFilterDdlb.select(Constant.DASH + session.getCustomerLevelNumber());
                levelDdlb.setValue(SELECT_ONE.getConstant());

                // As per FD Group column should not be in Product View
                resultsTable.getLeftFreezeAsTable().setColumnCollapsingAllowed(true);
                resultsTable.getLeftFreezeAsTable().setColumnCollapsed(Constant.GROUP, false);
            } else if (PRODUCT.getConstant().equals(String.valueOf(view.getValue()))) {
                customIdToSelect = customId;
                int hierarchyLevelNo = isInteger(session.getProductLevelNumber()) ? Integer.valueOf(session.getProductLevelNumber()) : 0;
                currentHierarchy = CommonLogic.getProductHierarchy(session.getProjectionId(), hierarchyLevelNo);
                hierarchyIndicator = "P";
                levelDdlb.setEnabled(true);
                levelFilterDdlb.setEnabled(true);
                expandBtn.setEnabled(true);
                collapseBtn.setEnabled(true);
                userGroup = StringUtils.EMPTY;

                viewChangeGenerate();
                levelFilterDdlb.select(Constant.DASH + session.getProductLevelNumber());
                levelDdlb.setValue(SELECT_ONE.getConstant());

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
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));

        leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
        leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());

//        leftTable.setHeight("650px");

        leftTable.setColumnCheckBox(Constant.CHECKRECORD, true);
        leftTable.setColumnCheckBoxDisable(Constant.CHECKRECORD, ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
        for (Object obj : leftHeader.getSingleColumns()) {
            if (String.valueOf(obj).contains(Constant.GROUP)) {
                leftTable.setColumnWidth(obj, 130);
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, 130);
            }
        }
        LOGGER.info("Ending configureLeftTable");
    }

    private void createLeftHeader() {
        leftHeader = HeaderUtils.getAHSummeryTabLeftTableColumns(new CustomTableHeaderDTO());

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

    protected void excelExportClickLogic() {
        LOGGER.info("excel starts");
        try {

        excelContainer = new CustomTreeContainer<>(DiscountProjectionDTO.class);

        excelContainer.setColumnProperties(excelHeader.getProperties());

        excelTable.setRefresh(false);
        excelTable.setContainerDataSource(excelContainer);
        excelTable.setVisibleColumns(excelHeader.getSingleColumns().toArray());
        excelTable.setColumnHeaders(excelHeader.getSingleHeaders().toArray(new String[excelHeader.getSingleHeaders().size()]));
        excelTable.setDoubleHeaderVisible(false);
        excelTable.setRefresh(true);

            for (Object propertyId : excelTable.getContainerPropertyIds()) {
                if (String.valueOf(propertyId).contains("ActualAmount") || String.valueOf(propertyId).contains("ProjectedAmount")) {
                    excelTable.setConverter(propertyId, priceFormat);
                } else if (String.valueOf(propertyId).contains(Constant.ActualRPU)) {
                    excelTable.setConverter(propertyId, priceFormat);
                } else {
                    excelTable.setConverter(propertyId, percentFormat);
                }
            }
            generateButtonlogicForExcel();
            String excelName="Summary";
            ForecastUI.EXCEL_CLOSE=true;
            ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(excelTable), excelName, excelName, excelName + ".xls", false);
            excel.export();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
            boolean isCustomHierarchy = "CP".equals(hierarchyIndicator);
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
                    true, discountTableLogic.getDiscountList(), projectionSelection.getYear(),
                    customDetailsList, true, isCustomHierarchy, excelHeader, 0, 1000, false, false, customViewDetails, false, false, StringUtils.EMPTY, relationshipBuilderSid, true,Collections.EMPTY_LIST,true,StringUtils.EMPTY, StringUtils.EMPTY,Collections.EMPTY_LIST,new HashMap<String,String>());
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
                    if(!"Total Alternate History".equals(dto.getLevelName())){
                        addLowerLevelsForExport(dto);
                    }
                }

            }
        } catch (Exception e) {
            LOGGER.info(e);

        }
        LOGGER.info("Ended loadDataToContainer");
    }

    public void addLowerLevelsForExport(DiscountProjectionDTO dto) {
        LOGGER.info("Inside addLowerLevelsForExport");
        try {

            int treeLevelNo = 0;
            String temphierarchyIndicator = hierarchyIndicator;
            boolean isCustomHierarchy = "CP".equals(hierarchyIndicator);
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
            discountToBeLoaded.add("REBATE_SCHEDULE_ID_15");
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
                    true, discountTableLogic.getDiscountList(), projectionSelection.getYear(),
                    customDetailsList, true, isCustomHierarchy, excelHeader, 0, 1000, false, false, customViewDetails, false, false, StringUtils.EMPTY, relationshipBuilderSid, true,Collections.EMPTY_LIST,false,StringUtils.EMPTY, StringUtils.EMPTY,Collections.EMPTY_LIST,new HashMap<String,String>());
            loadDataToContainer(levelList, dto, true);
            excelTable.setCollapsed(dto, false);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.info("Exit addLowerLevelsForExport");
    }

    protected void generateBtnClickLogic(Boolean isGenerate) {

        isRateUpdatedManually = false;
        isRPUUpdatedManually = false;
        isAmountUpdatedManually = false;
        saveList.clear();
        generateListView(true);

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
        discountToBeLoaded.add("REBATE_SCHEDULE_ID_15");
        if (discountToBeLoaded == null || discountToBeLoaded.isEmpty()) {
            NotificationUtils.getErrorNotification("No Discounts selected", "Please select some discounts from program selection lookup");
        } else if (frequencyDdlb.getValue() == null || frequencyDdlb.getValue().equals(SELECT_ONE.getConstant())) {
            NotificationUtils.getErrorNotification("No Frequency selected", "Please select any frequency");
        } else if (historyDdlb.getValue() == null || historyDdlb.getValue().equals(SELECT_ONE.getConstant())) {
            NotificationUtils.getErrorNotification("No History selected", "Please select any history");
        } else {
            tableLogic.sinkItemPerPageWithPageLength(false);
            boolean isFrequencyChange = !String.valueOf(projectionSelection.getFrequency()).equals(String.valueOf(frequencyDdlb.getValue()));

            createSelectionDto();
            createRightHeader();
            viewValueChangeLogic();
            resultBeanContainer.setColumnProperties(rightHeader.getProperties());
            if (isGenerate) {
                resultsTable.constructRightFreeze(isGenerate);
            }
            resultsTable.getRightFreezeAsTable().setContainerDataSource(tableLogic.getContainerDataSource());
            configureRightTable();
            configureLeftTable();
            String startFreq =StringUtils.EMPTY;
            String endFreq=StringUtils.EMPTY;
            int startYear =0;
            int endYear =0;
            if("Period".equalsIgnoreCase(String.valueOf(pivotView.getValue()))){
                startFreq = String.valueOf(rightHeader.getDoubleHeaders().get(0));
                endFreq = String.valueOf(rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1));
                String startYearValue = startFreq.substring(startFreq.length() - 4);
                String endYearValue = endFreq.substring(endFreq.length() - 4);
                startYear = isInteger(startYearValue) ? Integer.valueOf(startYearValue) : 0;
                endYear = isInteger(endYearValue) ? Integer.valueOf(endYearValue) : 0;
            }
            else{
                startFreq = String.valueOf(projectionSelection.getPeriodList().get(0));
                endFreq = String.valueOf(projectionSelection.getPeriodList().get(projectionSelection.getPeriodList().size()-1));
                String startYearValue = projectionSelection.getPeriodList().get(0).substring(projectionSelection.getPeriodList().get(0).length() - 4);
                String endYearValue = projectionSelection.getPeriodList().get(projectionSelection.getPeriodList().size()-1).substring(projectionSelection.getPeriodList().get(projectionSelection.getPeriodList().size()-1).length() - 4);
                startYear = isInteger(startYearValue) ? Integer.valueOf(startYearValue) : 0;
                endYear = isInteger(endYearValue) ? Integer.valueOf(endYearValue) : 0;
            }
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
                String startMonthValue = StringUtils.EMPTY;
                String endMonthValue = StringUtils.EMPTY;
                if ("Period".equalsIgnoreCase(String.valueOf(pivotView.getValue()))) {
                    startMonthValue = startFreq.substring(0, startFreq.length() - 5);
                    endMonthValue = endFreq.substring(0, startFreq.length() - 5);
                } else {
                    startMonthValue = startFreq.substring(0, startFreq.length() - 4);
                    endMonthValue = endFreq.substring(0, endFreq.length() - 4);
                    startMonthValue = startMonthValue.substring(0, 1).toUpperCase() + startMonthValue.substring(1);
                    endMonthValue = endMonthValue.substring(0, 1).toUpperCase() + endMonthValue.substring(1);
                }
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

            tableLogic.setRefresh(Boolean.FALSE);

            loadDataInTable();
            tableLogic.setRefresh(Boolean.FALSE); //As the row refresh will be set true during the load data.
            formatTableData();
            tableLogic.setRefresh(Boolean.TRUE);
            isListviewGenerated = true;

            loadLevelValues();

            isDiscountGenerated = true;

        }

        LOGGER.info("Ending generateListView ");

    }

    private void formatTableData() {
        LOGGER.info("Start formatTableData");
        if (!"Variable".equalsIgnoreCase(String.valueOf(pivotView.getValue()))) {
            for (Object propertyId : resultsTable.getRightFreezeAsTable().getVisibleColumns()) {
                if (String.valueOf(propertyId).contains("ActualAmount") || String.valueOf(propertyId).contains("ProjectedAmount")) {
                    resultsTable.getRightFreezeAsTable().setConverter(propertyId, priceFormat);
                } else if (String.valueOf(propertyId).contains(Constant.ActualRPU)) {
                    resultsTable.getRightFreezeAsTable().setConverter(propertyId, priceFormat);
                } else {
                    resultsTable.getRightFreezeAsTable().setConverter(propertyId, percentFormat);
                }
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
        discountToBeLoaded.add("REBATE_SCHEDULE_ID_15");

        int levelNo = getStartLevelNo();
        String relationshipBuilderSid = getRelationshipBuilderSid();
        getDetailsId();
        tableLogic.setDataLoad(true);

        if (initialLoad) {
            tableLogic.setDiscountVariablesForLogic(session, projectionSelection, startAndEndPeriods, true,
                    discountTableLogic.getDiscountList(), discountTableLogic.getLevelNo(), true, discountTableLogic.getRightDto(), discountTableLogic.getHierarchyIndicator(), discountTableLogic.getUserGroup(), discountTableLogic.getCurrentHierarchy(), discountTableLogic.isIsCustomHierarchy(), discountTableLogic.getCustomId(), discountTableLogic.getRelationshipBuilderSid());
            initialLoad = false;
        } else {
            tableLogic.setDiscountVariablesForLogic(session, projectionSelection, startAndEndPeriods, true,
                    discountTableLogic.getDiscountList(), levelNo, true, discountTableLogic.getRightDto(), hierarchyIndicator, userGroup, currentHierarchy, Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant().equalsIgnoreCase(hierarchyIndicator), customId, relationshipBuilderSid);

        }
        LOGGER.info(" User Id --" + session.getUserId());
        LOGGER.info(" Session Id --" + session.getSessionId());
        LOGGER.info("Ending loadDataInTable");
    }

    private void configureRightTable() {
        final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setTripleHeaderVisible(true);
//        rightTable.setHeight("650px");
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
        for (Object columns : rightHeader.getSingleColumns()) {
            if ("Variable".equalsIgnoreCase(String.valueOf(pivotView.getValue()))) {
                rightTable.setColumnWidth(columns, 130);
                resultsTable.getRightFreezeAsTable().setColumnWidth(columns, 130);
            }
            rightTable.setColumnAlignment(columns, ExtCustomTable.Align.RIGHT);
        }
    }

    private void createSelectionDto() {
        LOGGER.info(" Entering Selection Dto Creation");

        String freq = String.valueOf(frequencyDdlb.getValue());
        String hist = String.valueOf(historyDdlb.getValue());

        int historyNum = 0;
        try {

            if (freq.equals(QUARTERLY.getConstant())) {
                historyNum = Integer.valueOf(hist.replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
            } else if (freq.equals(SEMI_ANNUALLY.getConstant())) {
                historyNum = Integer.valueOf(hist.replace("Semi-Annual", StringUtils.EMPTY).trim());
            } else if (freq.equals(MONTHLY.getConstant())) {
                historyNum = Integer.valueOf(hist.replace("Month", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
            } else if (freq.equals(ANNUALLY.getConstant())) {
                historyNum = Integer.valueOf(hist.replace(Constant.YEAR, StringUtils.EMPTY).trim());
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
         projectionSelection.setPivotView("Variable".equalsIgnoreCase(String.valueOf(pivotView.getValue()))?"Pivot":"");
        projectionSelection.setVariableView(String.valueOf(pivotView.getValue()));
        projectionSelection.setIsAlternate(true);   
        projectionSelection.setActualsOrProjections(String.valueOf(actualsProjs.getValue()));
        projectionSelection.setProjectionOrder(String.valueOf(periodOrder.getValue()));
        projectionSelection.setProjectionId(session.getProjectionId());
        projectionSelection.setUserId(isInteger(session.getUserId()) ? Integer.valueOf(session.getUserId()) : 0);
        projectionSelection.setSessionId(isInteger(session.getSessionId()) ? Integer.valueOf(session.getSessionId()) : 0);
        projectionSelection.setCustomId(customId);

        LOGGER.info(" Ending Selection Dto Creation");
    }

    private void createRightHeader() {
        session.setForecastName(TabNameUtil.DISCOUNT_PROJECTION);
        excelHeader = new CustomTableHeaderDTO();
        excelHeader.addSingleColumn(Constant.LEVELNAME, "  ", String.class);
        excelHeader.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        excelHeader.addDoubleHeaderMap(Constant.GROUP, new Object[]{Constant.LEVELNAME});
        rightHeader = HeaderUtils.getAlternateHistoryRightTableColumnsForDiscount(projectionSelection, session, excelHeader, rsName);
    }

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

                frequencyDdlb.setValue(QUARTERLY.getConstant());
                periodOrder.select(ASCENDING.getConstant());
                actualsProjs.select(ACTUALS.getConstant());
            }
        }.getConfirmationMessage("Reset Confirmation", "Are you sure you want to reset the ‘Discount Projection Selections’?");
    }

    protected void expandBtnClickLogic() {
        expandCollapseLevelOption(true);
    }

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

    protected void levelFilterValueChangeLogic(Property.ValueChangeEvent event) {
        LOGGER.info("levelFilterDdlbvalueChangeListener initiated ");
        if (enableLevelFilterListener) {
            if (event.getProperty() != null && !Constant.NULL.equals(String.valueOf(event.getProperty().getValue()))) {
                LOGGER.info(" event value " + event.getProperty().getValue());

                String levelNumber = String.valueOf(event.getProperty().getValue());
                int levelNo = isInteger(levelNumber) ? Integer.valueOf(levelNumber) : 0;
                if (!levelNumber.startsWith(Constant.DASH)) {
                    projectionSelection.setIsFilter(true);
                    if (!"Variable".equalsIgnoreCase(String.valueOf(pivotView.getValue()))) {
                        tableLogic.setChildrenAllowed(false);
                    }
                    tableLogic.clearAll();
                    tableLogic.filterLevels(levelNo);

                    //To reset to default
                    projectionSelection.setIsFilter(true);
                    tableLogic.setChildrenAllowed(true);
                    tableLogic.setLevelNo(getStartLevelNo());

                } else {
                    projectionSelection.setIsFilter(false);
                    tableLogic.clearAll();
                    tableLogic.filterLevels(levelNo);
                }
                levelDdlb.setValue(SELECT_ONE.getConstant());
            }
        }
        LOGGER.info("levelFilterDdlbvalueChangeListener ended ");
    }

    protected void customDdlbLogic() {
        LOGGER.info("customDdlbChangeOption ValueChangeEvent initiated ");

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
        return PROGRAM.getConstant();
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

    public double doubleConversion(String value) {
        double doubleVal = Double.valueOf(value);
        double finalValue = doubleVal / ccpsCount;
        return finalValue;
    }

    protected void resetBtnForTableLogic() {
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

            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }
    
    public void getDetailsId() {
        try {
            projectionSelection.setDetailsSid(logic.getDetailsId(session.getUserId(), session.getSessionId()));
        } catch (Exception e) {
            LOGGER.error("Details Sid Value:::" + e.getMessage());
        }
    } 
   
}
