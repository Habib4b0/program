/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountProjection.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.discountProjection.logic.tableLogic.AHSummeryDiscountTableLogic;
import com.stpl.app.gtnforecasting.discountProjection.logic.tableLogic.NMDiscountTableLoadLogic;
import com.stpl.app.gtnforecasting.dto.DiscountProjectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SaveDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DiscountProjectionLogic;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import com.stpl.app.gtnforecasting.utils.TabNameUtil;
import com.stpl.app.model.CustomViewMaster;
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
import static com.stpl.app.utils.Constants.LabelConstants.BOTH;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOM;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOMER;
import static com.stpl.app.utils.Constants.LabelConstants.DESCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author maheshj
 */
public class AltSummeryDiscount extends CustomComponent {

    private final SessionDTO session;
    
    /* The Excel table */
    private final ExtFilterTreeTable excelTable = new ExtFilterTreeTable();
    /* The excel export image */
    private final Resource excelExportImage = new ThemeResource(EXCEL_IMAGE_PATH.getConstant());

    /**
     * The resultsTableLayout VerticalLayout.
     */
    @UiField("resultsTableLayout1")
    private VerticalLayout resultsTableLayout;
    /**
     * The excelTableLayout VerticalLayout.
     */
    @UiField("excelTableLayout")
    private VerticalLayout excelTableLayout;
    /**
     * The viewHlayout HorizontalLayout.
     */
    @UiField("viewHlayout")
    private HorizontalLayout viewHlayout;

    /**
     * The Non Mandated Panel.
     */
    @UiField("panelNM")
    private Panel panelNM;

    /**
     * The frequency ddlb.
     */
    @UiField("frequencyDdlb")
    private ComboBox frequencyDdlb;
    /**
     * The history Ddlb.
     */
    @UiField("historyDdlb")
    private ComboBox historyDdlb;

    /**
     * The periodOrder OptionGroup.
     */
    @UiField("periodOrder")
    private OptionGroup periodOrder;
    /**
     * The periodOrder OptionGroup.
     */
    @UiField("actualsProjs")
    private OptionGroup actualsProjs;
    
    /**
     * The pivotView OptionGroup.
     */
    
    
    @UiField("pivotView")
    private OptionGroup pivotView;

    @UiField("levelDdlb")
    private ComboBox levelDdlb;
    /**
     * The levelFilterDdlb ComboBox.
     */
    @UiField("levelFilterDdlb")
    private ComboBox levelFilterDdlb;

    /**
     * The viewDdlb ComboBox.
     */
    @UiField("viewDdlb")
    private ComboBox viewDdlb;

    /**
     * The adjperiods OptionGroup.
     */
    @UiField(Constant.VIEW)
    private OptionGroup view;

    /**
     * The generate Button.
     */
    @UiField("generateBtn")
    private Button generateBtn;
    /**
     * The editBtn Button.
     */
    @UiField("editBtn")
    private Button editBtn;
    /**
     * The newBtn Button.
     */
    @UiField("newBtn")
    private Button newBtn;
    /**
     * The excelExport Button.
     */
    @UiField("excelExport")
    private Button excelExport;

    /**
     * The reset Button.
     */
    @UiField("resetBtn")
    private Button resetBtn;
    /**
     * The expandBtn Button.
     */
    @UiField("expandBtn")
    private Button expandBtn;
    /**
     * The collapseBtn Button.
     */
    @UiField("collapseBtn")
    private Button collapseBtn;

    private final ExtTreeContainer<DiscountProjectionDTO> resultBeanContainer = new ExtTreeContainer<>(DiscountProjectionDTO.class,ExtContainer.DataStructureMode.MAP);
    /* To store the current hierarchy */
    private List<Leveldto> currentHierarchy = new ArrayList<>();
    /* To enable or disable level filter listener */
    private boolean enableLevelFilterListener = true;
    
    /* To hold the selected discounts in program selection lookup */
    private final List<String> discountProgramsList = new LinkedList<>();
    /* To hold the selected program from the program selection combo box */
    private final List<String> programSelectionList = new ArrayList<>();
    /* The hierarchy indicator to indicate whether customer or Product */
    private String hierarchyIndicator = StringUtils.EMPTY;
    /*  Non Mandated Logic */
    private final DiscountProjectionLogic logic = new DiscountProjectionLogic();
    /* table Logic to load the table Data */
    private final AHSummeryDiscountTableLogic tableLogic;
    /* To hold the selections on generate button click. */
    private ProjectionSelectionDTO projectionSelection = new ProjectionSelectionDTO();
    /* The custom id. */
    private int customId = 0;
    /* To check whether list view is generated or not */
    private boolean isListviewGenerated = false;
    /* The custom id to select. */
    private int customIdToSelect = 0;
    /* The Right Header Dto */
    private CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    /* The Right Header Dto */
    private CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    /* The Excel header Dto (Right Header) */
    private CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
    /* The Results table */
    private final FreezePagedTreeTable resultsTable;
    /* Group filter selected value */
    private String userGroup = StringUtils.EMPTY;
    /* Discount Names to be used in Projection results */
    private final List<String> discountNamesList = new ArrayList<>();
    /* Discount No to be used in Projection results */
    
    /* Start and End Periods to be loaded */
    private final List<Integer> startAndEndPeriods = new ArrayList<>();
    /* Data Format Converter */
    private final DataFormatConverter percentFormat = new DataFormatConverter("#,##0.000", DataFormatConverter.INDICATOR_PERCENT);
    private final DataFormatConverter priceFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_DOLLAR);
    /**
     * To store the details of the checked double header with its corresponding
     * Triple header and History, Projected values
     */
    private final Map<String, Map<String, List<String>>> tripleHeaderForCheckedDoubleHeader = new HashMap<>();
    /* List to have the items to be saved */
    private final List<SaveDTO> saveList = new ArrayList<>();
    /* To store the custom View */
    private List<CustomViewMaster> customViewList = new ArrayList<>();
    /* The Excel container */
    private ExtTreeContainer<DiscountProjectionDTO> excelContainer = new ExtTreeContainer<>(DiscountProjectionDTO.class,ExtContainer.DataStructureMode.MAP);
    private List<String> checkedList;

    private final int ccpsCount = 1;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(AltSummeryDiscount.class);
/**
 * boolean for generate
 */
    private boolean isDiscountGenerated;
    private final NMDiscountTableLoadLogic discountTableLogic;
    private boolean initialLoad = true;
    private String rsName = StringUtils.EMPTY;

    public AltSummeryDiscount(SessionDTO session, String screenName, ProjectionSelectionDTO projectionSelection, NMDiscountTableLoadLogic discountTableLogic, String rsName)  {

        this.session = session;
        this.rsName = rsName;
        this.projectionSelection = projectionSelection;
        this.discountTableLogic = discountTableLogic;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nonmandated/AHDiscountSummery.xml"), this));

        configureFields();
        tableLogic = new AHSummeryDiscountTableLogic(this);
        tableLogic.setTempPageLength(NumericConstants.TEN);
        tableLogic.setDataLoad(false);
        resultsTable = new FreezePagedTreeTable(tableLogic);
        tableLogic.setPageLength(NumericConstants.FIFTEEN);        
        tableLogic.sinkItemPerPageWithPageLength(false);
        getContent();
    }

    public void configureFields() {
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

            @Override
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
         pivotView.addItem(Constant.PERIOD);
        pivotView.addItem(Constant.VARIABLE);
        pivotView.select(Constant.PERIOD);
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
                generateBtnClickLogic();
            }
        });
        resetBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                resetBtnClickLogic();
            }
        });
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
        LOGGER.debug("Inside getContent " + session.getAction());
        configureFeildsForNm();

    }

    void configureFeildsForNm() {

        newBtn.setEnabled(true);
        /* To load the Customer hierarchy initially */
        int hierarchyLevelNo = isInteger(session.getCustomerLevelNumber()) ? Integer.valueOf(session.getCustomerLevelNumber()) : 0;
        currentHierarchy = CommonLogic.getCustomerHierarchy(session.getProjectionId(), hierarchyLevelNo);
        hierarchyIndicator = "C";

        tableLogic.setTreeNodeMultiClick(false);
        tableLogic.setDataLoad(false);
        tableLogic.setPageLength(NumericConstants.FIFTEEN);
        tableLogic.setItemsPerPage(NumericConstants.TWENTY);
        initializeTable();
        addResultTable();
        createLeftHeader();
        resultBeanContainer.setColumnProperties(leftHeader.getProperties());

        tableLogic.setContainerDataSource(resultBeanContainer);
        configureLeftTable();

        LOGGER.debug(" session Action " + session.getAction());

        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {

            generateBtnClickLogic();

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
            resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("Ending configureTable ");
    }

    /**
     * To get the hierarchy numbers affected during the manual entry
     *
     * @return
     */

    /**
     * To add Results table in UI
     */
    private void addResultTable() {
        resultsTableLayout.addComponent(resultsTable);

        tableLogic.setPageLength(NumericConstants.FIFTEEN);
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
                    String caption = Constant.LEVEL+ levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                    levelFilterDdlb.addItem(itemId);
                    levelFilterDdlb.setItemCaption(itemId, caption);
                   int levelSize=Constant.VARIABLE.equalsIgnoreCase(String.valueOf(pivotView.getValue()))?currentHierarchy.size():currentHierarchy.size() - 1;
                    if (i <levelSize ) {
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
        LOGGER.debug("Entering configureLeftTable");
        final ExtFilterTreeTable leftTable = resultsTable.getLeftFreezeAsTable();

        leftTable.setDoubleHeaderVisible(true);
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));

        leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
        leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());


        leftTable.setColumnCheckBox(Constant.CHECKRECORD, true);
        leftTable.setColumnCheckBoxDisable(Constant.CHECKRECORD, ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
        for (Object obj : leftHeader.getSingleColumns()) {
            if (String.valueOf(obj).contains(Constant.GROUP)) {
                leftTable.setColumnWidth(obj, NumericConstants.ONE_THREE_ZERO);
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, NumericConstants.ONE_THREE_ZERO);
            }
        }
        LOGGER.debug("Ending configureLeftTable");
    }

    private void createLeftHeader() {
        leftHeader = HeaderUtils.getAHSummeryTabLeftTableColumns(new CustomTableHeaderDTO());

    }

    /**
     * To load the values in custom Ddlb
     */
    public void loadCustomDDLB() {
        LOGGER.debug("loadCustomDDLB initiated ");
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
        LOGGER.debug("loadCustomDDLB ends ");
    }

    protected void newBtnClickLogic() {
        LOGGER.debug("newCustomHierarchhy clickEvent method starts");
        final CustomTreeBuild customTree = new CustomTreeBuild(session);
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
        LOGGER.debug("newCustomHierarchhy clickEvent method ends");
    }

    protected void editBtnClickLogic() {
        LOGGER.debug("Entering editHierarchyBtn");
        if (CommonLogic.editButtonValidation(viewDdlb, customViewList)) {
            final CustomTreeBuild customTree = new CustomTreeBuild(session, customId);
            customTree.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {
                    customIdToSelect = customTree.getCustomId();
                    loadCustomDDLB();
                }
            });
            UI.getCurrent().addWindow(customTree);
        }
        LOGGER.debug(" Ending editHierarchyBtn ");
    }

    protected void excelExportClickLogic() {
        LOGGER.debug("excel starts");
        try {

        excelContainer = new ExtTreeContainer<>(DiscountProjectionDTO.class,ExtContainer.DataStructureMode.MAP);

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
                } else if (String.valueOf(propertyId).contains(Constant.ACTUALRPU)) {
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


            List<String> customViewDetails = new ArrayList<>();
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
                    true, discountTableLogic.getDiscountList(), projectionSelection.getYear(),
                    customDetailsList, true, isCustomHierarchy, excelHeader, 0, NumericConstants.THOUSAND, false, false, customViewDetails, false, false, StringUtils.EMPTY, 
                    relationshipBuilderSid, true,Collections.EMPTY_LIST,true,StringUtils.EMPTY, StringUtils.EMPTY,Collections.EMPTY_LIST,
                    new HashMap<String,String>(), projectionSelection.getForecastConfigPeriods(),projectionSelection);
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
                    if ((isRecursive) && (!"Total Alternate History".equals(dto.getLevelName()))){
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
            boolean isCustomHierarchy = "CP".equals(hierarchyIndicator);
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
            discountToBeLoaded.add(Constant.REBATE_SCHEDULE_ID_15);
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
                    true, discountTableLogic.getDiscountList(), projectionSelection.getYear(),
                    customDetailsList, true, isCustomHierarchy, excelHeader, 0, NumericConstants.THOUSAND, false, false, customViewDetails, false, false, 
                    StringUtils.EMPTY, relationshipBuilderSid, true,Collections.EMPTY_LIST,false,StringUtils.EMPTY, StringUtils.EMPTY,
                    Collections.EMPTY_LIST,new HashMap<String,String>(), projectionSelection.getForecastConfigPeriods(),projectionSelection);
            loadDataToContainer(levelList, dto, true);
            excelTable.setCollapsed(dto, false);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Exit addLowerLevelsForExport");
    }

    protected void generateBtnClickLogic() {
        saveList.clear();
        generateListView(true);
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
        discountToBeLoaded.add(Constant.REBATE_SCHEDULE_ID_15);
        if (discountToBeLoaded == null || discountToBeLoaded.isEmpty()) {
            NotificationUtils.getErrorNotification("No Discounts selected", "Please select some discounts from program selection lookup");
        } else if (frequencyDdlb.getValue() == null || frequencyDdlb.getValue().equals(SELECT_ONE.getConstant())) {
            NotificationUtils.getErrorNotification("No Frequency selected", "Please select any frequency");
        } else if (historyDdlb.getValue() == null || historyDdlb.getValue().equals(SELECT_ONE.getConstant())) {
            NotificationUtils.getErrorNotification("No History selected", "Please select any history");
        } else {
            tableLogic.sinkItemPerPageWithPageLength(false);

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
            String startFreq;
            String endFreq;
            int startYear =0;
            int endYear =0;
            if(Constant.PERIOD.equalsIgnoreCase(String.valueOf(pivotView.getValue()))){
                startFreq = String.valueOf(rightHeader.getDoubleHeaders().get(0));
                endFreq = String.valueOf(rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1));
                String startYearValue = startFreq.substring(startFreq.length() - NumericConstants.FOUR);
                String endYearValue = endFreq.substring(endFreq.length() - NumericConstants.FOUR);
                startYear = isInteger(startYearValue) ? Integer.valueOf(startYearValue) : 0;
                endYear = isInteger(endYearValue) ? Integer.valueOf(endYearValue) : 0;
            }
            else{
                startFreq = String.valueOf(projectionSelection.getPeriodList().get(0));
                endFreq = String.valueOf(projectionSelection.getPeriodList().get(projectionSelection.getPeriodList().size()-1));
                String startYearValue = projectionSelection.getPeriodList().get(0).substring(projectionSelection.getPeriodList().get(0).length() - NumericConstants.FOUR);
                String endYearValue = projectionSelection.getPeriodList().get(projectionSelection.getPeriodList().size()-1).substring(projectionSelection.getPeriodList().get(projectionSelection.getPeriodList().size()-1).length() - NumericConstants.FOUR);
                startYear = isInteger(startYearValue) ? Integer.valueOf(startYearValue) : 0;
                endYear = isInteger(endYearValue) ? Integer.valueOf(endYearValue) : 0;
            }
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
                String startMonthValue;
                String endMonthValue;
                if (Constant.PERIOD.equalsIgnoreCase(String.valueOf(pivotView.getValue()))) {
                    startMonthValue = startFreq.substring(0, startFreq.length() - NumericConstants.FIVE);
                    endMonthValue = endFreq.substring(0, startFreq.length() - NumericConstants.FIVE);
                } else {
                    startMonthValue = startFreq.substring(0, startFreq.length() - NumericConstants.FOUR);
                    endMonthValue = endFreq.substring(0, endFreq.length() - NumericConstants.FOUR);
                    startMonthValue = startMonthValue.substring(0, 1).toUpperCase() + startMonthValue.substring(1);
                    endMonthValue = endMonthValue.substring(0, 1).toUpperCase() + endMonthValue.substring(1);
                }
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

            tableLogic.setRefresh(Boolean.FALSE);

            loadDataInTable();
            tableLogic.setRefresh(Boolean.FALSE); //As the row refresh will be set true during the load data.
            formatTableData();
            tableLogic.setRefresh(Boolean.TRUE);
            isListviewGenerated = true;

            loadLevelValues();

            isDiscountGenerated = true;

        }

        LOGGER.debug("Ending generateListView ");

    }

    private void formatTableData() {
        LOGGER.debug("Start formatTableData");
        if (!Constant.VARIABLE.equalsIgnoreCase(String.valueOf(pivotView.getValue()))) {
            for (Object propertyId : resultsTable.getRightFreezeAsTable().getVisibleColumns()) {
                if (String.valueOf(propertyId).contains("ActualAmount") || String.valueOf(propertyId).contains("ProjectedAmount")) {
                    resultsTable.getRightFreezeAsTable().setConverter(propertyId, priceFormat);
                } else if (String.valueOf(propertyId).contains(Constant.ACTUALRPU)) {
                    resultsTable.getRightFreezeAsTable().setConverter(propertyId, priceFormat);
                } else {
                    resultsTable.getRightFreezeAsTable().setConverter(propertyId, percentFormat);
                }
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
        discountToBeLoaded.add(Constant.REBATE_SCHEDULE_ID_15);

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
        LOGGER.debug(" User Id --" + session.getUserId());
        LOGGER.debug(" Session Id --" + session.getSessionId());
        LOGGER.debug("Ending loadDataInTable");
    }

    private void configureRightTable() {
        final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setTripleHeaderVisible(true);
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
            if (Constant.VARIABLE.equalsIgnoreCase(String.valueOf(pivotView.getValue()))) {
                rightTable.setColumnWidth(columns, NumericConstants.ONE_THREE_ZERO);
                resultsTable.getRightFreezeAsTable().setColumnWidth(columns, NumericConstants.ONE_THREE_ZERO);
            }
            rightTable.setColumnAlignment(columns, ExtCustomTable.Align.RIGHT);
        }
    }

    private void createSelectionDto() {
        LOGGER.debug(" Entering Selection Dto Creation");

        String freq = String.valueOf(frequencyDdlb.getValue());
        String hist = String.valueOf(historyDdlb.getValue());

        int historyNum = 0;
        try {

            if (freq.equals(QUARTERLY.getConstant())) {
                historyNum = Integer.valueOf(hist.replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
            } else if (freq.equals(SEMI_ANNUALLY.getConstant())) {
                historyNum = Integer.valueOf(hist.replace(Constant.SEMI_ANNUALY, StringUtils.EMPTY).trim());
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
         projectionSelection.setPivotView(Constant.VARIABLE.equalsIgnoreCase(String.valueOf(pivotView.getValue()))?"Pivot":"");
        projectionSelection.setVariableView(String.valueOf(pivotView.getValue()));
        projectionSelection.setIsAlternate(true);   
        projectionSelection.setActualsOrProjections(String.valueOf(actualsProjs.getValue()));
        projectionSelection.setProjectionOrder(String.valueOf(periodOrder.getValue()));
        projectionSelection.setProjectionId(session.getProjectionId());
        projectionSelection.setUserId(isInteger(session.getUserId()) ? Integer.valueOf(session.getUserId()) : 0);
        projectionSelection.setSessionId(isInteger(session.getSessionId()) ? Integer.valueOf(session.getSessionId()) : 0);
        projectionSelection.setCustomId(customId);

        LOGGER.debug(" Ending Selection Dto Creation");
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
        LOGGER.debug("levelFilterDdlbvalueChangeListener initiated ");
            if ((enableLevelFilterListener) && (event.getProperty() != null && !Constant.NULL.equals(String.valueOf(event.getProperty().getValue())))) {
                LOGGER.debug(" event value " + event.getProperty().getValue());

                String levelNumber = String.valueOf(event.getProperty().getValue());
                int levelNo = isInteger(levelNumber) ? Integer.valueOf(levelNumber) : 0;
                if (!levelNumber.startsWith(Constant.DASH)) {
                    projectionSelection.setIsFilter(true);
                    if (!Constant.VARIABLE.equalsIgnoreCase(String.valueOf(pivotView.getValue()))) {
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
        LOGGER.debug("levelFilterDdlbvalueChangeListener ended ");
    }

    protected void customDdlbLogic() {
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent initiated ");

        customId = CommonLogic.customDdlbOptionChange(viewDdlb, editBtn, levelDdlb);
        levelDdlb.setEnabled(customId != 0);
        currentHierarchy = CommonLogic.getCustomTree(customId);
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
        checkedList = new ArrayList<>();
        for (String d : tripleHeaderForCheckedDoubleHeader.keySet()) {
            Map<String, List<String>> checkedDoubleHeaders = tripleHeaderForCheckedDoubleHeader.get(d);
            for (String e : checkedDoubleHeaders.keySet()) {
                List a = checkedDoubleHeaders.get(e);
                if (!checkedList.isEmpty() && !a.isEmpty() && !isOne) {
                    ismultipleDiscount = true;
                    break;
                } else {
                    checkedList.addAll(a);
                }
            }
            if (!checkedList.isEmpty()) {
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
            Map<String, String> monthMap = new HashMap<>();
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
                        tempSubYear = monthMap.get(tmpSubYear);
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
                    startTempSubYear = monthMap.get(startTmpSubYear);
                    endTempYear = endPeriod.toString().trim().substring(NumericConstants.THREE);
                    String endTmpSubYear = endPeriod.toString().replace(endTempYear, StringUtils.EMPTY).trim();
                    endTempSubYear = monthMap.get(endTmpSubYear);
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
                    return false;
                } else if (year[year.length - 1] >= finStartPeriod && year[year.length - 1] <= finEndPeriod) {
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
                LOGGER.debug("Reset the temp table by deleting and inserting ");
                logic1.deleteTempData(session, "DISC_DELETE_TEMP");
                logic1.tempInsertReturns(session, "DISC_EDIT_TEMP_INSERT_QUERY");
                generateListView(true);
            } else if (Constants.CommonConstants.ACTION_GENERATE.getConstant().equalsIgnoreCase(session.getAction())) {
                LOGGER.debug("Reset the temp table by deleting and inserting by proc");
                logic1.deleteTempData(session, "DISC_DELETE_TEMP");
                logic.callDiscountProjectionProcedure(session);

            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }
    
    public void getDetailsId() {
        try {
            projectionSelection.setDetailsSid(logic.getDetailsId(session));
        } catch (Exception e) {
            LOGGER.error(e);
        }
    } 
   
}
