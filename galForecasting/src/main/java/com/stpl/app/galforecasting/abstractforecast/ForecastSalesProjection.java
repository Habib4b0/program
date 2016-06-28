
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.abstractforecast;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.dto.ContractBrandDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.dto.SalesRowDto;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.salesprojection.logic.AlternateHistoryLogic;
import com.stpl.app.galforecasting.ui.form.lookups.AlternateHistory;
import com.stpl.app.galforecasting.salesprojection.logic.SalesLogic;
import com.stpl.app.galforecasting.salesprojection.logic.tablelogic.MSalesProjectionTableLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.form.lookups.ContractBrandLookup;
import com.stpl.app.galforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import static com.stpl.app.galforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.ANNUAL;
import static com.stpl.app.galforecasting.utils.Constant.MONTHLY;
import com.stpl.app.galforecasting.utils.NotificationUtils;
import com.stpl.app.galforecasting.utils.TabNameUtil;
import com.stpl.app.galforecasting.utils.TotalLivesChart;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_EDIT;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOM_HIERARCHY;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.utils.Constants.ResourceConstants.GRAPH_IMAGE_PATH;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTreeTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author vinodhini
 */
public abstract class ForecastSalesProjection extends CustomComponent implements View {

    /**
     * View name for navigation.
     */
    public static final String NAME = StringUtils.EMPTY;
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger
            .getLogger(ForecastSalesProjection.class);
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The max split position.
     */
    private final float maxSplitPosition = 1000;
    /**
     * The min split position.
     */
    private final float minSplitPosition = 300;
    /**
     * The split position.
     */
    private final float splitPosition = 300;
    public CustomTreeContainer<SalesRowDto> customContainer = new CustomTreeContainer<>(SalesRowDto.class);
    protected ProjectionSelectionDTO projectionDTO = new ProjectionSelectionDTO();
    protected ProjectionSelectionDTO initialProjSelDTO = new ProjectionSelectionDTO();
    protected CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
    protected CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    /**
     * The history ddlb.
     */
    @UiField("historyDdlb")
    protected ComboBox historyDdlb;
    @UiField("channelHistory")
    protected ComboBox channelHistory;

    @UiField("tabsheet")
    private TabSheet tabsheet;
    @UiField("massUpdateLayout")
    private VerticalLayout massUpdateLayout;
    /**
     * The mass update.
     */
    @UiField("massUpdate")
    protected OptionGroup massUpdate;
    /**
     * The field ddlb.
     */
    @UiField("fieldDdlb")
    protected ComboBox fieldDdlb;

    /**
     * The value ddlb.
     */
    @UiField("valueDdlb")
    protected ComboBox valueDdlb;
    /**
     * The pro period ord.
     */
    @UiField("proPeriodOrd")
    protected OptionGroup proPeriodOrd;
    @UiField("channelPeriodOrder")
    protected OptionGroup channelPeriodOrder;
    /**
     * The actuals projections.
     */
    @UiField("ActualsProjections")
    protected OptionGroup actualsProjections;
    /**
     * The variables.
     */
    @UiField("variables")
    protected OptionGroup variables;
    @UiField("forecastLayout")
    private VerticalLayout forecastLayout;
    @UiField("adjustmentLayout")
    private VerticalLayout adjustmentLayout;
    /**
     * The contract.
     */
    @UiField("contract")
    protected CustomTextField contract;
    /**
     * The brand.
     */
    @UiField("brand")
    protected CustomTextField brand;
    /**
     * The value txt.
     */
    @UiField("valueTxt")
    protected TextField valueTxt;
    /**
     * The type.
     */
    @UiField("type")
    protected OptionGroup type;
    /**
     * The basis.
     */
    @UiField("basis")
    protected OptionGroup basis;
    /**
     * The variable.
     */
    @UiField("variable")
    protected OptionGroup variable;
    /**
     * The adjust periods.
     */
    @UiField("adjustPeriods")
    protected OptionGroup adjustPeriods;
    /**
     * The view.
     */
    @UiField(Constant.VIEW)
    protected OptionGroup view;
    /**
     * The newnew btn.
     */
    @UiField("newBtn")
    protected Button newBtn;
    /**
     * The edit btn.
     */
    @UiField("editBtn")
    protected Button editBtn;
    /**
     * The pmpy.
     */
    @UiField("pmpy")
    protected Button pmpy;
    /**
     * The refreshBtn.
     */
    @UiField("refreshBtn")
    protected Button refreshBtn;
    /**
     * The reset.
     */
    @UiField("resetBtn")
    protected Button resetBtn;
    /**
     * The populate.
     */
    @UiField("populate")
    protected Button populate;
    /**
     * The excel export.
     */
    @UiField("excelExport")
    protected Button excelExport;
    /**
     * The graph icon.
     */
    @UiField("graphIcon")
    protected Button graphIcon;
    /**
     * The start period
     */
    @UiField("startPeriod")
    protected ComboBox startPeriod;
    /**
     * The end period
     */
    @UiField("endPeriod")
    protected ComboBox endPeriod;
    /**
     * The methodology ddlb
     */
    @UiField("methodologyDdlb")
    protected ComboBox methodology;
    /**
     * The allocation methodology
     */
    @UiField("allocationMethodology")
    protected ComboBox allocMethodology;
    /**
     * The allocation basis
     */
    @UiField("allocationBasis")
    protected ComboBox allocationBasis;
    @UiField("adjustment")
    protected TextField adjustment;
    /**
     * The level
     */
    @UiField("levelDdlb")
    protected ComboBox level;
    /**
     * The level filter ddlb
     */
    @UiField("levelFilterDdlb")
    protected ComboBox levelFilter;
    /**
     * The view ddlb
     */
    @UiField("viewDdlb")
    protected ComboBox viewDdlb;
    /**
     * The forecastStartPeriod
     */
    @UiField("forecastStartPeriod")
    protected ComboBox forecastStartPeriod;
    /**
     * The forecastEndPeriod
     */
    @UiField("forecastEndPeriod")
    protected ComboBox forecastEndPeriod;
    /**
     * The generate
     */
    @UiField("generateBtn")
    protected Button generate;
    @UiField("adjust")
    protected Button adjust;
    @UiField("totalLives")
    protected TextField totalLives;
    @UiField("calculate")
    protected Button calculate;
    @UiField("tableLayout")
    protected VerticalLayout tableLayout;
    @UiField("expand")
    protected Button expand;
    @UiField("collapse")
    protected Button collapse;
    @UiField("lblEnd")
    protected Label lblEnd;
    @UiField("lblStart")
    protected Label lblStart;
    @UiField("viewLabel")
    public Label viewLabel;
    @UiField("channelLayout")
    protected VerticalLayout channelLayout;
    @UiField("calculationPanel")
    private GridLayout calculationPanel;
    @UiField("altHistoryBtn")
    protected Button altHistoryBtn;
    @UiField("alternateHistoryPanel")
    public Panel alternateHistoryPanel;
    @UiField("buttonLayout")
    protected HorizontalLayout buttonLayout;

    public Button returnsResetBtn = new Button("RESET");
    @UiField("populateLevel")
    public ComboBox populateLevel;
    @UiField("populateLabel")
    public Label populateLabel;
    public int customId = 0;
    protected boolean checkAll = false;
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(
            EXCEL_IMAGE_PATH.getConstant());
    /**
     * The graph image.
     */
    private final Resource graphImage = new ThemeResource(GRAPH_IMAGE_PATH.getConstant());
    protected ExtFilterTreeTable leftTable;
    protected ExtFilterTreeTable rightTable;
    protected CustomTableHeaderDTO leftHeader = null;
    protected CustomTableHeaderDTO rightHeader = null;
    final public BeanItemContainer<String> groupBean = new BeanItemContainer<String>(String.class);
    final public BeanItemContainer<String> massGroupBean = new BeanItemContainer<String>(String.class);
    protected SessionDTO session;
    protected FreezePagedTreeTable resultsTable;
    protected MSalesProjectionTableLogic mSalesProjectionTableLogic;
    protected ExtCustomTreeTable excelTable = new ExtCustomTreeTable();
    protected CustomTreeContainer<SalesRowDto> excelContainer = new CustomTreeContainer<SalesRowDto>(SalesRowDto.class);
    AlternateHistoryLogic logic = new AlternateHistoryLogic();
    protected int uncheckRecordCount;
    /**
     * Level Filter Listener
     */
    protected Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                projectionDTO.setGroup(StringUtils.EMPTY);
            }
            levelFilterDdlbChangeOption();
        }
    };
    Property.ValueChangeListener groupFilterValueChangeListener = null;
    Property.ValueChangeListener baseLineFilterValueChangeListener = null;
    Property.ValueChangeListener methodologyFilterValueChangeListener = null;
    /**
     * Refresh Button
     */
    ComboBox userGroupTest = new ComboBox();
    protected ComboBox metohdologyFilter = new ComboBox();
    protected ComboBox baseLineFilter = new ComboBox();
    final public BeanItemContainer<String> methdologyBean = new BeanItemContainer<String>(String.class);
    final public BeanItemContainer<String> baseLineBean = new BeanItemContainer<String>(String.class);
    protected String screenName;
    @UiField("mainPanel")
    Panel mainPanel;
    @UiField("channelMassUpdatePanel")
    Panel channelMassUpdatePanel;
    @UiField("salesProjectionSelection")
    Panel salesProjectionSelection;
    @UiField("channelSalesAllocationSelection")
    Panel channelSalesAllocationSelection;
    @UiField("totalLivesLayout")
    HorizontalLayout totalLivesLayout;
    @UiField("viewLayout")
    HorizontalLayout viewLayout;
    @UiField("level")
    protected OptionGroup levelOption;
    @UiField("channelView")
    protected OptionGroup channelView;
    @UiField("spAdjustment")
    Panel spAdjustment;
    @UiField("ndcDdlb")
    protected ComboBox ndcDdlb;
    @UiField("therapeuticClass")
    protected ComboBox therapeuticClass;
    @UiField("frequencyDdlb")
    protected ComboBox frequencyDdlb;
    @UiField("nmFrequencyDdlb")
    protected ComboBox nmFrequencyDdlb;
    protected int customIdToSelect = 0;
    protected List<CustomViewMaster> customViewList = new ArrayList<>();
    protected final SalesLogic salesLogic = new SalesLogic();
    protected List<Leveldto> currentHierarchy = new ArrayList<>();
    DataFormatConverter unitFormat = new DataFormatConverter("#,##0.0");
    DataFormatConverter salesFormat = new DataFormatConverter("#,##0", DataFormatConverter.INDICATOR_DOLLAR);
    DataFormatConverter growthFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_PERCENT);
    String oldValue = StringUtils.EMPTY;
    String oldGroupValue = StringUtils.EMPTY;
    final Set<String> tableHirarechyNos = new HashSet<>();
    private Map<Object, Boolean> checkBoxMap = new HashMap<>();
    private Map<Object, String> radioMap = new HashMap<>();
    protected boolean isSalesCalculated;
    List<String> checkedList;
    Map<String, Map<String, List<String>>> tripleHeaderForCheckedDoubleHeader = new HashMap<String, Map<String, List<String>>>();
    List<Object> checkedDiscountsPropertyIds = new ArrayList<Object>();
    @UiField("AlternateGrid")
    private GridLayout AlternateGrid;
    @UiField("ContractLabel")
    protected Label ContractLabel;
    @UiField("BrandLabel")
    protected Label BrandLabel;
    @UiField("GridLayoutProjection")
    protected GridLayout GridLayoutProjection;

    @UiField("gridPopulate")
    protected GridLayout gridPopulate;

    @UiField("Variables")
    protected Label Variables;
    @UiField("projPeriodOrdr")
    protected Label projPeriodOrdr;
    @UiField("ForecastHorizonyalLayout")
    HorizontalLayout ForecastHorizonyalLayout;
    HorizontalLayout forecastReturnsLayout = new HorizontalLayout();
    @UiField("Allocation")
    protected Label Allocation;
    @UiField("forecastSPeriod")
    protected Label forecastSPeriod;
    @UiField("forecastEPeriod")
    protected Label forecastEPeriod;
    boolean returnsFlag = false;
    public static ResourceBundle alertMsg = ResourceBundle.getBundle("properties.alertmessage");


    /**
     * Instantiates a new Forecast Sales Projection.
     *
     * @param session
     * @param screenName
     */
    public ForecastSalesProjection(SessionDTO session, String screenName) {
        try {
            this.session = session;
            this.screenName = screenName;
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/abstractforecast/forecastSalesProjection.xml"), this));
            returnsFlag = CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName);
            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
                projectionDTO.setScreenName(screenName);
                mSalesProjectionTableLogic = new MSalesProjectionTableLogic();
                resultsTable = new FreezePagedTreeTable(mSalesProjectionTableLogic);
            }
            addComponent();
            configurefields();
            enableDisableFields();
        } catch (Exception ex) {
            LOGGER.error(ex.getCause());
        }
    }

    /**
     *
     * Configurefields.
     */
    private void configurefields() throws Exception {
        level.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        level.setImmediate(true);
        levelFilter.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        levelFilter.setImmediate(true);
        viewDdlb.setEnabled(false);
        editBtn.setEnabled(false);
        newBtn.setEnabled(true);
        alternateHistoryPanel.setVisible(false);
        fieldDdlb.setEnabled(true);
        valueDdlb.setEnabled(true);
        valueTxt.setEnabled(true);
        startPeriod.setEnabled(true);
        endPeriod.setEnabled(true);
        populate.setEnabled(true);

        historyDdlb.focus();
        historyDdlb.addItem(Constant.SELECT_ONE);
        historyDdlb.setNullSelectionItemId(Constant.SELECT_ONE);

        tableLayout.addComponent(resultsTable);
        tableLayout.addComponent(excelTable);

        actualsProjections.addItem(Constant.ACTUALS);
        actualsProjections.addItem(Constant.PROJECTIONS);
        actualsProjections.addItem(Constant.BOTH);
        actualsProjections.select(Constant.ACTUALS);
        actualsProjections.setStyleName(Constant.HORIZONTAL);

        type.addItem(Constant.LabelConstants.INCREMENTAL);
        type.addItem(Constant.LabelConstants.OVERRIDE);
        type.select(Constant.LabelConstants.INCREMENTAL);
        type.setStyleName(Constant.HORIZONTAL);

        basis.addItem(Constant.LabelConstants.AMOUNT);
        basis.addItem(Constant.LabelConstants.PERCENTAGE);
        basis.select(Constant.LabelConstants.AMOUNT);
        basis.setStyleName(Constant.HORIZONTAL);

        variable.addItem(Constant.SALES_SMALL);
        variable.addItem(Constant.UNIT);
        variable.select(Constant.SALES_SMALL);
        variable.setStyleName(Constant.HORIZONTAL);

        adjustPeriods.addItem(Constant.All);
        adjustPeriods.addItem(Constants.ButtonConstants.SELECT.getConstant());
        adjustPeriods.select(Constant.All);
        adjustPeriods.setStyleName(Constant.HORIZONTAL);
        adjustment.setStyleName(Constant.TXT_RIGHT_ALIGN);

        graphIcon.setStyleName(Reindeer.BUTTON_LINK);

        view.addItem(Constant.CUSTOMER_SMALL);
        view.addItem(Constant.PRODUCT);
        view.addItem(Constant.CUSTOM);
        view.select(Constant.CUSTOMER_SMALL);
        view.setStyleName(Constant.HORIZONTAL);

        excelExport.setIcon(excelExportImage);

        excelTable.setVisible(false);
        graphIcon.setIcon(graphImage);

        methodology.addItem(Constant.SELECT_ONE);
        fieldDdlb.addItem(Constant.SELECT_ONE);
        populateLabel.setVisible(false);
        populateLevel.setVisible(false);

        if (returnsFlag) {
            tabsheet.addTab(massUpdateLayout, "Mass Update");
            tabsheet.addTab(forecastLayout, Constant.FORECAST);

            populateLabel.setVisible(true);
            populateLevel.setVisible(true);
            populateLabel.setEnabled(true);
            populateLevel.setEnabled(true);
            ContractLabel.setCaption("Brand:");
            AlternateGrid.replaceComponent(contract, brand);
            BrandLabel.setVisible(false);
            brand.setVisible(false);
            BrandLabel.setVisible(false);
            variables.setMultiSelect(false);
            Variables.setVisible(false);
            actualsProjections.setVisible(true);
            GridLayoutProjection.replaceComponent(variables, proPeriodOrd);
            GridLayoutProjection.replaceComponent(Variables, projPeriodOrdr);
            totalLivesLayout.setVisible(false);
            adjustmentLayout.setVisible(false);

            adjustmentLayout.setVisible(false);
            ForecastHorizonyalLayout.setVisible(false);
            forecastReturnsLayout.setImmediate(true);
            forecastReturnsLayout.setSpacing(false);
            forecastReturnsLayout.setMargin(false);
            forecastReturnsLayout.addComponent(new Label("Methodology:"));
            forecastReturnsLayout.addComponent(methodology);
            forecastReturnsLayout.addComponent(forecastSPeriod);
            forecastReturnsLayout.addComponent(forecastStartPeriod);
            forecastReturnsLayout.addComponent(forecastEPeriod);
            forecastReturnsLayout.addComponent(forecastEndPeriod);
            forecastReturnsLayout.addComponent(calculate);
            forecastLayout.addComponent(forecastReturnsLayout);

            buttonLayout.replaceComponent(pmpy, returnsResetBtn);
            returnsResetBtn.setVisible(true);
            returnsResetBtn.setImmediate(true);
            newBtn.setVisible(Boolean.FALSE);
            editBtn.setVisible(false);
            view.setVisible(false);
            viewLabel.setVisible(false);
            viewDdlb.setVisible(false);
            channelLayout.setVisible(false);
            massUpdate.setVisible(false);
            fieldDdlb.addItem(Constant.PROJECTED_RETURN_PER);
            fieldDdlb.addItem(Constant.PROJECTED_RPU);
            fieldDdlb.addItem(Constant.PROJECTED_RETURN_AMT);
            fieldDdlb.addItem(Constant.GROWTH_RATE);
            gridPopulate.setImmediate(true);
            gridPopulate.replaceComponent(valueDdlb, valueTxt);

            methodology.addItem(Constant.PERCOFEXFACTORY);
            methodology.addItem(Constant.SINGLE_PERIOD);
            methodology.addItem(Constant.AVERAGE);
            methodology.addItem(Constant.ROLLINGANNUALTREND);
            methodology.addItem(Constant.TIRE_ONE);

            methodology.addItem(Constant.TIRE_TWO);
            methodology.addItem(Constant.QUARTILE);
            valueDdlb.setVisible(false);
            valueTxt.setVisible(true);

            valueTxt.setValue(StringUtils.EMPTY);
            valueTxt.addStyleName(Constant.ALIGN_RIGHT);
            populateLevel.setVisible(true);

        } else {
            variables.addItem(Constant.SALES_SMALL);
            variables.addItem(Constant.UNITS_SMALL);
            variables.addItem(Constant.PRODUCT_GROWTH);
            variables.addItem(Constant.ACCOUNT_GROWTH);

            variables.select(Constant.SALES_SMALL);
            variables.setStyleName(Constant.HORIZONTAL);
            tabsheet.addTab(massUpdateLayout, "Mass Update");
            tabsheet.addTab(forecastLayout, Constant.FORECAST);
            tabsheet.addTab(adjustmentLayout, Constant.ADJUSTMENT);

            channelLayout.setVisible(false);
            fieldDdlb.addItem(Constant.ACCOUNT_GROWTH);
            fieldDdlb.addItem(Constant.PRODUCT_GROWTH);
            valueDdlb.setVisible(false);
            valueDdlb.addItem(Constant.SELECT_ONE);
            valueDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
            valueDdlb.setTextInputAllowed(true);

            valueTxt.setStyleName(Constant.TXT_RIGHT_ALIGN);
            valueTxt.setVisible(true);

            methodology.addItem(Constant.SINGLE_PERIOD);
            methodology.addItem(Constant.AVERAGE);
            methodology.addItem(Constant.ROLLINGANNUALTREND);

            methodology.addItem(Constant.PERCOFEXFACTORYSALES);
            methodology.addItem(Constant.PERCOFDEMAND);
            methodology.addItem(Constant.PERCOFINVENTORYWITHDRAWAL);
            methodology.addItem(Constant.PERC_OF_ADJUSTED_DEMAND);

            variables.addItem(Constant.SALES_SMALL);
            variables.addItem(Constant.UNITS_SMALL);
            variables.addItem(Constant.PRODUCT_GROWTH);
            variables.addItem(Constant.ACCOUNT_GROWTH);

            variables.select(Constant.SALES_SMALL);
            variables.setStyleName(Constant.HORIZONTAL);

            massUpdate.addItem(Constant.LabelConstants.ENABLE);
            massUpdate.addItem(Constant.LabelConstants.DISABLE);
            massUpdate.select(Constant.LabelConstants.DISABLE);
            massUpdate.setStyleName(Constant.HORIZONTAL);
            fieldDdlb.setEnabled(false);
            valueDdlb.setEnabled(false);
            valueTxt.setEnabled(false);
            startPeriod.setEnabled(false);
            endPeriod.setEnabled(false);
            populate.setEnabled(false);
        }

        configureFrequency(nmFrequencyDdlb, historyDdlb, true);
        loadFrequency(nmFrequencyDdlb, historyDdlb);

        refreshBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                mSalesProjectionTableLogic.setRefresh(false);
                refreshTableData(getCheckedRecordsHierarchyNo());
                mSalesProjectionTableLogic.setRefresh(true);
            }
        });
        tabsheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabsheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        fieldDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
        methodology.setNullSelectionItemId(Constant.SELECT_ONE);

        startPeriod.addItem(Constant.SELECT_ONE);
        startPeriod.setNullSelectionItemId(Constant.SELECT_ONE);

        forecastStartPeriod.addItem(Constant.SELECT_ONE);
        forecastStartPeriod.setNullSelectionItemId(Constant.SELECT_ONE);

        forecastEndPeriod.addItem(Constant.SELECT_ONE);
        forecastEndPeriod.setNullSelectionItemId(Constant.SELECT_ONE);

        endPeriod.addItem(Constant.SELECT_ONE);
        endPeriod.setNullSelectionItemId(Constant.SELECT_ONE);

        allocMethodology.addItem(Constant.SELECT_ONE);
        allocMethodology.setNullSelectionItemId(Constant.SELECT_ONE);
        allocMethodology.addItem(Constant.HISPEROFBUSINESS);
        allocMethodology.addItem(Constant.FOREPERCOFBUSINESS);


        allocationBasis.addItem(Constant.SELECT_ONE);
        allocationBasis.setNullSelectionItemId(Constant.SELECT_ONE);
        allocationBasis.addItem(Constant.LabelConstants.PERC_OF_EX_FACTORY.getConstant());
        allocationBasis.addItem(Constant.PERCOFDEMAND);
        allocationBasis.addItem(Constant.PERCOFINVENTORYWITHDRAWAL);
        
        allocationBasis.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                if ((Constant.LabelConstants.PERC_OF_EX_FACTORY.getConstant()).equals(allocationBasis.getValue())) {
                    altHistoryBtn.setEnabled(true);
                } else if ((Constant.PERCOFDEMAND).equals(allocationBasis.getValue())
                        || (Constant.PERCOFINVENTORYWITHDRAWAL).equals(allocationBasis.getValue())
                        || (Constant.SELECT_ONE.equals(allocationBasis.getValue()))
                        || ((allocationBasis.getValue() == null))) {

                    altHistoryBtn.setEnabled(false);
                }

            }
        });

        level.addItem(Constant.SELECT_ONE);
        level.setNullSelectionItemId(Constant.SELECT_ONE);

        levelFilter.addItem(Constant.SELECT_ONE);
        levelFilter.setNullSelectionItemId(Constant.SELECT_ONE);

        populateLevel.addItem(Constant.SELECT_ONE);
        populateLevel.setNullSelectionItemId(Constant.SELECT_ONE);
        viewDdlb.addItem(Constant.SELECT_ONE);
        viewDdlb.setNullSelectionItemId(Constant.SELECT_ONE);

        metohdologyFilter.setContainerDataSource(methdologyBean);
        metohdologyFilter.setNullSelectionAllowed(false);
        baseLineFilter.setNullSelectionAllowed(false);
        baseLineFilter.setContainerDataSource(baseLineBean);
        startPeriod.setImmediate(true);
        endPeriod.setImmediate(true);

        if (ACTION_VIEW.getConstant().equals(session.getAction())) {
            configureOnViewMode();
        }

        groupFilterValueChangeListener = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                LOGGER.info("groupDdlbChangeOption ValueChangeEvent initiated ");
                String groupValue = String.valueOf(event.getProperty().getValue());
                groupValue = Constant.SHOW_ALL_GROUPS.equals(groupValue) ? Constant.PERCENT : groupValue;
                projectionDTO.setGroup(groupValue);
                mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
                LOGGER.info("groupDdlbChangeOption ValueChangeEvent ends ");
            }
        };

        baseLineFilterValueChangeListener = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
            }
        };

        methodologyFilterValueChangeListener = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
            }
        };

        baseLineFilter.addValueChangeListener(baseLineFilterValueChangeListener);
        metohdologyFilter.addValueChangeListener(methodologyFilterValueChangeListener);

        massUpdate.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                massUpdateLogic();
            }
        });

        altHistoryBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {

                try {

                    if (validateForAlternateHistory()) {
                        AlternateHistory alternateContractLookup;
                        session.setForecastName("Sales Projection");
                        session.setFrequency(projectionDTO.getFrequency());
                        alternateContractLookup = new AlternateHistory(session,projectionDTO.getVariableList());
                       
                        alternateContractLookup.addCloseListener(new Window.CloseListener() {

                            @Override
                            public void windowClose(Window.CloseEvent e) {
                               
                                logic.removeTempTable(session);
                                if (session.getForecastName().equals(TabNameUtil.DISCOUNT_PROJECTION)) {
                                    logic.executeDelete(session, false);
                                } else {
                                    logic.executeDelete(session, true);
                                }
                            }
                        });
                        getUI().addWindow(alternateContractLookup);
                    }

                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(ForecastSalesProjection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        loadLevelFilterValue(returnsFlag ? PRODUCT_HIERARCHY.getConstant() : String.valueOf(view.getValue()));
        view.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                loadOnViewChange();
            }
        });

        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
            setProjectionSelection();
        }
    }

    @UiHandler("newBtn")
    public void newHierarchyBtn(Button.ClickEvent event) {
        customTreeViewLogic();
    }

    /**
     * edit the hierarchy btn.
     *
     * @param event the event
     */
    @UiHandler("editBtn")
    public void editHierarchyBtn(Button.ClickEvent event) {
        editHierarchyLogic();

    }

    /**
     * Pmpy calculator.
     *
     * @param event the event
     */
    @UiHandler("pmpy")
    public void pmpyCalculator(Button.ClickEvent event) {
        pmpyLogic();
    }

    @UiHandler("graphIcon")
    public void totalLiveGraph(Button.ClickEvent event) {
        LOGGER.info("Entering Total Lives Handler");
        TotalLivesChart totalLivesChart = new TotalLivesChart(session);
        getUI().addWindow(totalLivesChart);
        LOGGER.info("Ending Total Lives Handler");
    }

    /**
     * Reset btn.
     *
     * @param event the event
     */
    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            public void noMethod() {
               
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                LOGGER.info("Entering resetBtn method");
                resetBtnLogic();
                LOGGER.info("End of resetBtn method");
            }
        }.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default/previous values?");

    }

    /**
     * Populate btn logic.
     *
     * @param event the event
     */
    @UiHandler("populate")
    public void populate(Button.ClickEvent event) {
        populateBtnLogic();
    }

    /**
     * Adjustment Logic
     *
     * @param event
     */
    @UiHandler("adjust")
    public void adjust(Button.ClickEvent event) {
        adjustmentLogic();
    }

    /**
     * Calculate Logic
     *
     * @param event
     */
    @UiHandler("calculate")
    public void calculate(Button.ClickEvent event) {
        calculateLogic();
    }

    /**
     * Generate Button Logic
     *
     * @param event
     */
    @UiHandler("generateBtn")
    public void generateBtn(Button.ClickEvent event) {
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            projectionDTO.setGroup(StringUtils.EMPTY);
        }
        checkBoxMap.clear();
        radioMap.clear();
        generateBtnLogic(event);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    /**
     * value change of view.
     *
     * @param event the event
     */
    @UiHandler("viewDdlb")
    public void viewDdlb(Property.ValueChangeEvent event) {
        LOGGER.info("customDdlbChangeOption ValueChangeEvent initiated ");
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            projectionDTO.setGroup(StringUtils.EMPTY);
        }
        customDdlbChangeOption();
        LOGGER.info("customDdlbChangeOption ValueChangeEvent ends ");
    }

    public int getTabNumber() {
        return Constant.ONE;
    }

    @UiHandler("excelExport")
    public void excelExportListener(Button.ClickEvent event) {
        excelExportLogic();
    }

    /**
     * Mass update Value TextField value change listener
     *
     * @param event
     */
    /**
     * Expand button logic.
     *
     * @param event the event
     */
    @UiHandler("expand")
    public void expand(Button.ClickEvent event) {
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            projectionDTO.setGroup(StringUtils.EMPTY);
        }
        expandButtonLogic();
    }

    /**
     * Collapse button logic.
     *
     * @param event the event
     */
    @UiHandler("collapse")
    public void collapse(Button.ClickEvent event) {
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            projectionDTO.setGroup(StringUtils.EMPTY);
        }
        collapseButtonLogic();
    }

    @UiHandler("fieldDdlb")
    public void fieldDdlb(Property.ValueChangeEvent event) {
        LOGGER.info("fieldDdlb value change listener starts");
        fieldDdlbLogic();
        LOGGER.info("fieldDdlb value change listener ends");
    }

    private void addComponent() {

        spAdjustment.setCaption("Sales Projection");
        channelSalesAllocationSelection.setVisible(false);
        proPeriodOrd.addItem(ASCENDING.getConstant());
        proPeriodOrd.addItem(Constant.DESCENDING);
        proPeriodOrd.select(ASCENDING.getConstant());
        proPeriodOrd.setStyleName(Constant.HORIZONTAL);
        CommonUtils.frequenceValueChange(QUARTERLY.getConstant(), historyDdlb, session);
        historyDdlb.setValue(1);

    }

    protected void initializeResultTable() {
        resultsTable.markAsDirty();
        resultsTable.setDoubleHeaderVisible(true);
        resultsTable.setSelectable(false);
        resultsTable.setImmediate(true);
        resultsTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultsTable.addStyleName(Constant.CENTER_CHECK);
    }

    protected void initializeDualTable() {
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();
        leftTable.markAsDirty();
        rightTable.markAsDirty();
        rightTable.setEditable(true);
        leftTable.setEditable(true);
        leftTable.setImmediate(true);
        rightTable.setImmediate(true);

    }

    protected abstract void levelFilterDdlbChangeOption();

    protected abstract void excelExportLogic();

    protected abstract void enableDisableFields();

    protected void loadLevelFilterValue(final String view) {
        if (view.equalsIgnoreCase(PRODUCT_HIERARCHY.getConstant())) {
            int hierarchyLevelNo = isInteger(session.getProductLevelNumber()) ? Integer.valueOf(session.getProductLevelNumber()) : 0;
            currentHierarchy = CommonLogic.getProductHierarchy(session.getProjectionId(), hierarchyLevelNo, session.getProdRelationshipBuilderSid());
            CommonUtils.loadLevelDdlb(level, true, currentHierarchy);
            CommonUtils.loadLevelDdlb(levelFilter, false, currentHierarchy);
            CommonUtils.loadLevelDdlb(populateLevel, false, currentHierarchy);
        } else if (view.equalsIgnoreCase(CUSTOMER_HIERARCHY.getConstant())) {
            int hierarchyLevelNo = isInteger(session.getCustomerLevelNumber()) ? Integer.valueOf(session.getCustomerLevelNumber()) : 0;
            currentHierarchy = CommonLogic.getCustomerHierarchy(session.getProjectionId(), hierarchyLevelNo, session.getCustRelationshipBuilderSid());
            CommonUtils.loadLevelDdlb(level, true, currentHierarchy);
            CommonUtils.loadLevelDdlb(levelFilter, false, currentHierarchy);
        } else if (view.equalsIgnoreCase(CUSTOM_HIERARCHY.getConstant())) {
            currentHierarchy = CommonLogic.getCustomTree(customId);
            CommonUtils.loadLevelDdlb(level, true, currentHierarchy);
        }
    }

    protected abstract void populateBtnLogic();

    protected abstract void adjustmentLogic();

    protected abstract void calculateLogic();

    protected abstract void generateBtnLogic(Button.ClickEvent event);

    protected abstract void massUpdateLogic();

    protected abstract void viewChangeOption();

    protected abstract void customDdlbChangeOption();

    protected abstract void expandButtonLogic();

    protected abstract void collapseButtonLogic();

    protected abstract void fieldDdlbLogic();

    protected abstract void resetBtnLogic();

    protected abstract void pmpyLogic();

    /**
     * Configures the Sales Projection Selection for Mandated and Non-Mandated
     * and returns the map containing Sales Projection Selection.
     *
     * @return - Selection Map
     */
    protected Map configureSalesProjectionSelection() {

        String history = String.valueOf(historyDdlb.getValue());
        history = history.trim();
        final String actualsOrProjections = String.valueOf(actualsProjections.getValue());
        final String projectionPeriodorder = String.valueOf(proPeriodOrd.getValue());
        Map<String, String> selection = new HashMap<String, String>();
        selection.put(Constant.FREQUENCY, QUARTERLY.getConstant());
        selection.put(Constant.HISTORY, history);
        if (session.getForecastDTO().getForecastEndDate().after(session.getForecastDTO().getProjectionEndDate())) {
            selection.put("projectFrequency", String.valueOf(CommonUtils.getProjections(new Date(), session.getForecastDTO().getForecastEndDate(), QUARTERLY.getConstant())));
        } else {
            selection.put("projectFrequency", String.valueOf(CommonUtils.getProjections(new Date(), session.getForecastDTO().getProjectionEndDate(), QUARTERLY.getConstant())));
        }
        selection.put(Constant.ORDER, projectionPeriodorder);
        selection.put("actualsorprojections", actualsOrProjections);
        selection.put(Constant.SALES, "false");
        selection.put(Constant.UNITS, "false");
        selection.put(Constant.P_Growth, "false");
        selection.put(Constant.A_Growth, "false");

        if (variables.getValue() != null) {
            String tempVariables = variables.getValue().toString();
            tempVariables = tempVariables.substring(1, tempVariables.length() - 1);
            final String[] col = tempVariables.split(",");
            for (String value : col) {
                value = value.trim();
                switch (value) {
                    case Constant.SALES_SMALL:
                        selection.put(Constant.SALES, Constant.TRUE);
                        break;
                    case Constant.UNITS_SMALL:
                        selection.put(Constant.UNITS, Constant.TRUE);
                        break;
                    case Constant.ACCOUNT_GROWTH:
                        selection.put(Constant.P_Growth, Constant.TRUE);
                        break;
                    case Constant.PRODUCT_GROWTH:
                        selection.put(Constant.A_Growth, Constant.TRUE);
                        break;
                }
            }
        }

        return selection;
    }

    /**
     * Returns the SalesRowDto based on the given Item Id.
     *
     * @param obj
     * @return
     */
    public SalesRowDto getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof SalesRowDto) {
            targetItem = new BeanItem<>(
                    (SalesRowDto) obj);
        }
        return (SalesRowDto) targetItem.getBean();
    }

    /**
     * Method holds the logic for loading the sales projection grid on view
     * change.
     */
    protected void loadOnViewChange() {
        LOGGER.info("viewOptionGroup value change listener starts");
        salesLogic.resetChkbox(projectionDTO, false);
        mSalesProjectionTableLogic.setRefresh(false);
        leftTable.setColumnCheckBox(Constant.CHECK, true, false);
        leftTable.setColumnCheckBoxDisable(Constant.CHECK, ACTION_VIEW.getConstant().equals(session.getAction()));
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            projectionDTO.setGroup(StringUtils.EMPTY);
        }
        if ((PRODUCT.getConstant()).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, true);
        } else if ((Constant.CUSTOM).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, false);
        } else if ((Constant.CUSTOMER_SMALL).equals(view.getValue())) {

            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, false);
        }
        if ((Constant.CUSTOM).equals(view.getValue())) {
            newBtn.setEnabled(!ACTION_VIEW.getConstant().equals(session.getAction()));
            viewDdlb.setEnabled(!ACTION_VIEW.getConstant().equals(session.getAction()));
            projectionDTO.setIsCustomHierarchy(true);
            projectionDTO.setHierarchyIndicator(Constant.CUSTOM);
            loadLevelFilterValue(String.valueOf(view.getValue()));
            loadLevelAndFilterValue();
            level.setValue(SELECT_ONE);
            level.setEnabled(true);
            levelFilter.setValue(SELECT_ONE);
            levelFilter.setEnabled(false);
            loadCustomDDLB();
            viewDdlb.setValue(SELECT_ONE);
            mSalesProjectionTableLogic.clearAll();
        } else if ((PRODUCT.getConstant()).equals(view.getValue())) {
            projectionDTO.setIsCustomHierarchy(false);
            projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            projectionDTO.setView(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            newBtn.setEnabled(true);
            editBtn.setEnabled(false);
            viewDdlb.setEnabled(false);
            viewDdlb.setValue(SELECT_ONE);
            loadLevelFilterValue(String.valueOf(view.getValue()));
            loadLevelAndFilterValue();
            level.setValue(SELECT_ONE);
            level.setEnabled(true);
            levelFilter.setValue(SELECT_ONE);
            levelFilter.setEnabled(true);
            projectionDTO.setCustomFlag(false);
            mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
        } else {
            projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            projectionDTO.setIsCustomHierarchy(false);
            projectionDTO.setView(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            newBtn.setEnabled(true);
            editBtn.setEnabled(false);
            viewDdlb.setEnabled(false);
            viewDdlb.setValue(SELECT_ONE);
            loadLevelFilterValue(String.valueOf(view.getValue()));
            loadLevelAndFilterValue();
            level.setValue(SELECT_ONE);
            level.setEnabled(true);
            levelFilter.setValue(SELECT_ONE);
            levelFilter.setEnabled(true);
            projectionDTO.setCustomFlag(false);
            mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
        }
        mSalesProjectionTableLogic.setRefresh(true);
        LOGGER.info("viewOptionGroup value change listener ends");
    }

    /**
     * Loads the previously created Custom Hierarchy for a particular projection
     * in the Custom DDLB.
     */
    protected void loadCustomDDLB() {
        LOGGER.info("loadCustomDDLB initiated " + customIdToSelect);
        viewDdlb.setEnabled(true);
        newBtn.setEnabled(true);
        editBtn.setEnabled(false);
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
                viewDdlb.select(customIdToSelect);
            } else {
                viewDdlb.setValue(SELECT_ONE);
            }
        }
        customId = customIdToSelect;
        LOGGER.info("loadCustomDDLB ends ");
    }

    /**
     * Opens the CustomTreePopup to create a new Custom Hierarchy
     */
    protected void customTreeViewLogic() {

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

    /**
     * Opens the CustomTreePopup to edit the existing custom hierarchy.
     */
    public void editHierarchyLogic() {
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

    /**
     * Configures the field factory for the tables used Mandated and
     * Non-Mandated.
     */
    protected void configureTableFieldFactory() {

        final ExtCustomTable.ColumnCheckListener checkListener = new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                checkAll = event.isChecked();
                salesLogic.headerCheckALLQuery(session, checkAll ? 1 : 0, true);
                checkClearAll(event.isChecked());
            }
        };

        leftTable = resultsTable.getLeftFreezeAsTable();
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            NonMandatedFilter();
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionDTO.getScreenName())) {
            MandatedFilter();
        }
        if (!returnsFlag) {
            if (leftHeader.getDoubleColumns() != null) {
                leftTable.setDoubleHeaderVisible(false);
                leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
                leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
            }
        }
        UiUtils.setExtFilterTreeTableColumnWidth(rightTable, 145, TAB_SALES_PROJECTION.getConstant());

        leftTable.addColumnCheckListener(checkListener);
        leftTable.setTableFieldFactory(new DefaultFieldFactory() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * To create editable fields inside table .
             */
            @Override
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                if (String.valueOf(propertyId).equals(Constant.CHECK)) {
                    final ExtCustomCheckBox check = new ExtCustomCheckBox();
                    check.setValue(false);
                    check.setEnabled(!ACTION_VIEW.getConstant().equals(session.getAction()));
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            try {
                                SalesRowDto checkDTO = (SalesRowDto) itemId;
                                Boolean checkValue = check.getValue();
                                mSalesProjectionTableLogic.setRefresh(false);
                                final String tableHierarchyNo = mSalesProjectionTableLogic.getTreeLevelonCurrentPage(itemId);
                                checkDTO.addBooleanProperties(propertyId, checkValue);
                                int updatedRecordsNo = returnsFlag ? salesLogic.queryToUpdateCheckRecord(session, (checkValue ? 1 : 0), true, checkDTO.getReturnDetailsSid()) : salesLogic.saveCheckedRecords(projectionDTO, getBeanFromId(itemId), checkValue, false);
                                updateCheckForParentLevels(itemId, updatedRecordsNo, checkValue);
                                updateCheckForChildLevels(tableHierarchyNo, itemId, checkValue);

                                if (!checkValue) {
                                    ExtPagedTreeTable leftTable = resultsTable.getLeftFreezeAsTable();
                                    leftTable.removeColumnCheckListener(checkListener);
                                    leftTable.setColumnCheckBox(Constant.CHECK, true, false);
                                    leftTable.addColumnCheckListener(checkListener);
                                    checkAll = false;
                                }
                                  if(returnsFlag==true){
                                List<Integer> listStr = salesLogic.headerCheckALLQuery(session, 0, false);
                                leftTable.setColumnCheckBox(Constant.CHECK, true, listStr.size() != 1 ? false : (String.valueOf(listStr.get(0)) == "true"));
                                resultsTable.getLeftFreezeAsTable().setRefresh(false);
                                  }
                                resultsTable.getLeftFreezeAsTable().setRefresh(true);
                            } catch (Exception ex) {
                                LOGGER.error(ex.getMessage());
                            }
                        }
                    });
                    return check;
                } else if (!ACTION_VIEW.getConstant().equals(session.getAction()) && Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue()))) {
                    SalesRowDto salesRowDto = (SalesRowDto) itemId;
                    if (Constant.GROUP.equals(propertyId) && (Constant.TRADINGPARTNER.equalsIgnoreCase(salesRowDto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(salesRowDto.getHierarchyLevel()))) {
                        final TextField textField = new TextField();
                        textField.setData(getBeanFromId(itemId).getHierarchyNo());
                        textField.setImmediate(true);
                        textField.setWidth(100, UNITS_PERCENTAGE);
                        textField.addFocusListener(new FieldEvents.FocusListener() {
                            @Override
                            public void focus(FieldEvents.FocusEvent event) {
                                oldGroupValue = String.valueOf(((TextField) event.getComponent()).getValue());
                            }
                        });
                        textField.addBlurListener(new FieldEvents.BlurListener() {
                            @Override
                            public void blur(FieldEvents.BlurEvent event) {
                                String newValue = ((TextField) event.getComponent()).getValue();
                                if (!oldGroupValue.equals(newValue)) {
                                    try {
                                        SalesRowDto dto = (SalesRowDto) itemId;
                                        mSalesProjectionTableLogic.getContainerDataSource().getContainerProperty(itemId, Constant.GROUP).setValue(String.valueOf(((TextField) event.getComponent()).getValue()));
                                        dto.addStringProperties(Constant.GROUP, String.valueOf(((TextField) event.getComponent()).getValue()));
                                        salesLogic.saveSalesGroup(projectionDTO, dto.getHierarchyNo(), newValue);
                                        groupBean.removeAllItems();
                                        groupBean.addBean(Constant.SHOW_ALL_GROUPS);
                                        groupBean.addAll(salesLogic.loadSalesGroup(projectionDTO));
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            }
                        });
                        return textField;
                    }
                }
                return null;
            }
        });

        rightTable.setTableFieldFactory(new DefaultFieldFactory() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * To create editable fields inside table .
             */
            @Override
            public Field<?> createField(final Container container,
                    final Object itemId, final Object propertyId,
                    final Component uiContext) {
                if (!ACTION_VIEW.getConstant().equals(session.getAction()) && !String.valueOf(propertyId).contains(Constant.HISTORY_CAPS) && !String.valueOf(propertyId).contains(Constant.ACTUALSALES) && !String.valueOf(propertyId).contains(Constant.ACTUALUNITS)
                        && !String.valueOf(propertyId).contains(Constant.METHODOLOGY) && !String.valueOf(propertyId).contains(Constant.BASELINE) && !String.valueOf(propertyId).contains(Constant.GROUP) && !String.valueOf(propertyId).contains("Dis")
                        && !String.valueOf(propertyId).contains("ActualReturned") && !String.valueOf(propertyId).contains(Constant.ActualRPU)) {

                    final TextField textField = new TextField();
                    final SalesRowDto salesRowDto = getBeanFromId(itemId);
                    textField.setData(propertyId + "~" + salesRowDto.getHierarchyNo());
                    textField.setImmediate(true);
                    textField.addStyleName(Constant.TXT_RIGHT_ALIGN);
                    textField.setWidth(100, UNITS_PERCENTAGE);
                    textField.setEnabled(true);
                    if (String.valueOf(propertyId).contains(Constant.SALES_SMALL)) {
                        textField.setConverter(salesFormat);
                    } else if (String.valueOf(propertyId).contains(Constant.UNITS_SMALL)) {
                        textField.setConverter(unitFormat);
                    } else if (String.valueOf(propertyId).contains(Constant.GROWTH)) {
                        textField.setConverter(growthFormat);
                    }

                    textField.addFocusListener(new FieldEvents.FocusListener() {
                        @Override
                        public void focus(FieldEvents.FocusEvent event) {
                            oldValue = String.valueOf(((TextField) event.getComponent()).getValue());
                            oldValue = oldValue.replace("$", StringUtils.EMPTY);
                            oldValue = oldValue.replace(",", StringUtils.EMPTY);
                            oldValue = oldValue.replace(Constant.PERCENT, StringUtils.EMPTY);
                        }
                    });
                    textField.addBlurListener(new FieldEvents.BlurListener() {
                        @Override
                        public void blur(FieldEvents.BlurEvent event) {
                            String newValue = String.valueOf(((TextField) event.getComponent()).getValue());
                            newValue = newValue.replace("$", StringUtils.EMPTY);
                            newValue = newValue.replace(",", StringUtils.EMPTY);
                            newValue = newValue.replace(Constant.PERCENT, StringUtils.EMPTY);
                            if (!oldValue.equals(newValue)) {
                                try {
                                    Double newNumber, oldNumber;
                                    newNumber = StringUtils.EMPTY.equals(newValue) || Constant.NULL.equals(newValue) ? 0.0 : Double.valueOf(newValue);
                                    oldNumber = StringUtils.EMPTY.equals(oldValue) || Constant.NULL.equals(oldValue) ? 0.0 : Double.valueOf(oldValue);

                                    Double incOrDec = ((newNumber - oldNumber) / oldNumber) * 100;
                                    String tempValue = String.valueOf(((TextField) event.getComponent()).getData());
                                    String tempArray[] = tempValue.split("-");
                                    tempValue = tempArray[2];
                                    String tempArray1[] = tempValue.split("~");
                                    String changedProperty = tempArray1[0];

                                    String changedValue = ((TextField) event.getComponent()).getValue();
                                    changedValue = StringUtils.isBlank(changedValue) || Constant.NULL.equals(changedValue) ? "0.0" : changedValue;
                                    if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equalsIgnoreCase(screenName)) {
                                        salesLogic.saveEditedRecsReturns(propertyId.toString(), changedValue, incOrDec, salesRowDto, projectionDTO, checkAll, !tempArray1[0].contains(Constant.GROWTH));
                                    } else {
                                        salesLogic.saveEditedRecs(propertyId.toString(), changedValue, incOrDec, changedProperty, salesRowDto, projectionDTO, checkAll, !tempArray1[0].contains(Constant.GROWTH));
                                    }
                                    salesRowDto.addStringProperties(propertyId, newValue);
                                    tableHirarechyNos.add(mSalesProjectionTableLogic.getTreeLevelonCurrentPage(itemId));
                                } catch (Exception ex) {
                                    LOGGER.error(ex);
                                }
                            }
                        }
                    });
                    return textField;
                }
                return null;
            }
        });
        if (!returnsFlag) {
            for (Object obj : rightHeader.getDoubleHistoryColumns()) {
                Object single[] = rightHeader.getDoubleHeaderMaps().get(obj);
                for (Object ob : single) {
                    rightTable.setColumnRadioButton(ob, (String) obj);
                    if (String.valueOf(ob).contains(Constant.ACTUALSALES)) {
                        rightTable.setColumnRadioButtonValue((String) obj, ob);
                    }
                    rightTable.setColumnRadioButtonDisable(ob, true);
                }
            }
            for (Object obj : rightHeader.getSingleColumns()) {
                if (String.valueOf(obj).contains(Constant.ACTUALSALES)) {
                    rightTable.setColumnAlignment(obj, ExtFilterTreeTable.Align.RIGHT);
                }
                if (String.valueOf(obj).contains(Constant.ACTUALUNITS)) {
                    rightTable.setColumnAlignment(obj, ExtFilterTreeTable.Align.RIGHT);
                }
            }
            resultsTable.addStyleName("stableheader");
        }

        List objList = rightHeader.getDoubleColumns();
        for (Object obj : objList) {

            if (!String.valueOf(obj).contains(Constant.GROUP)) {

                rightTable.setDoubleHeaderColumnCheckBox(obj, true);

                rightTable.setDoubleHeaderColumnCheckBoxDisable(obj, ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
                checkBoxMap.put(obj, false);
            }
        }
        rightTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {

                checkBoxMap.put(event.getPropertyId(), event.isChecked());
                if (!returnsFlag) {
                    String arr[] = rightTable.getColumnRadioButtonArray((String) event.getPropertyId());
                    if (arr != null) {
                        for (String a : arr) {
                            rightTable.setColumnRadioButtonDisable(a, !event.isChecked());
                        }
                    }
                    if (event.isChecked()) {
                        radioMap.put(event.getPropertyId(), rightTable.getColumnRadioButtonValue((String) event.getPropertyId()));
                    } else {
                        if (radioMap.containsKey(event.getPropertyId())) {
                            radioMap.remove(event.getPropertyId());
                        }
                    }
                }
            }
        });
        if (!returnsFlag) {
            rightTable.addColumnRadioCheckListener(new ExtCustomTable.ColumnRadioCheckListener() {
                @Override
                public void columnRadioCheck(ExtCustomTable.ColumnRadioCheckEvent event) {
                    radioMap.put(event.getRadioButtonName(), event.getCurrentValue());
                }
            });
        }

        for (Object obj : leftHeader.getSingleColumns()) {
            if (String.valueOf(obj).contains(Constant.CHECK)) {
                leftTable.setColumnCheckBox(obj, true);
                leftTable.setColumnCheckBoxDisable(obj, ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction()));
                checkAll = false;
            }
        }
        resultsTable.addStyleName("stableheader");

    }

    /**
     * Method Checks/Unchecks the records in grid on click in the check box in
     * the table header.
     *
     * @param checkClear
     */
    public void checkClearAll(boolean checkClear) {
        try {
            mSalesProjectionTableLogic.setRefresh(false);
            if (returnsFlag) {
                salesLogic.resetChkboxForReturns(projectionDTO, checkAll);
            } else {
                salesLogic.resetChkbox(projectionDTO, checkAll);
            }
            for (Object itemID : leftTable.getItemIds()) {
                SalesRowDto dto = (SalesRowDto) itemID;
                dto.addBooleanProperties(Constant.CHECK, checkClear);
                leftTable.getContainerProperty(itemID, Constant.CHECK).setValue(checkClear);
            }

            for (String hierarchyNo : mSalesProjectionTableLogic.getAllLevels()) {
                boolean isPresentInContainer = true;
                Object tempId = mSalesProjectionTableLogic.getcurrentTreeData(hierarchyNo);
                if (tempId == null) {
                    isPresentInContainer = false;
                    tempId = mSalesProjectionTableLogic.getExpandedTreeValues(hierarchyNo);
                }
                if (tempId != null) {
                    SalesRowDto tempDto = (SalesRowDto) tempId;
                    tempDto.setUncheckCount(checkClear ? 0 : Integer.valueOf(tempDto.getCcpCount()));
                    updateChecks(tempId, isPresentInContainer);
                }
            }
            mSalesProjectionTableLogic.setRefresh(true);

        } catch (Property.ReadOnlyException | NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * To update the check box
     *
     * @param tempId
     * @param isPresentInContainer
     */
    private void updateChecks(Object tempId, boolean isPresentInContainer) {

        SalesRowDto tempDto = (SalesRowDto) tempId;
        boolean checkValue = tempDto.getUncheckCount() == 0;
        tempDto.addBooleanProperties(Constant.CHECK, checkValue);
        if (isPresentInContainer) {
            mSalesProjectionTableLogic.getContainerDataSource().getContainerProperty(tempId, Constant.CHECK).setValue(checkValue);
        }
    }

    /**
     * Updates the check value of a parent record, when child records are
     * checked.
     *
     * @param itemId
     * @param updatedRecordsNo
     * @param checkValue
     */
    private void updateCheckForParentLevels(Object itemId, int updatedRecordsNo, Boolean checkValue) {

        SalesRowDto dto = (SalesRowDto) itemId;
        int newRecordsCount = updatedRecordsNo;
        if (checkValue) {
            if (newRecordsCount > dto.getUncheckCount()) {
                newRecordsCount = dto.getUncheckCount();
            }
        } else {
            if (newRecordsCount < dto.getUncheckCount()) {
                newRecordsCount = dto.getUncheckCount();
            }
        }

        List<String> hierarchyNos = mSalesProjectionTableLogic.getAllParentLevels(itemId);

        for (String hierarchyNo : hierarchyNos) {
            Object tempId = mSalesProjectionTableLogic.getcurrentTreeData(hierarchyNo);
            if (tempId == null) {
                tempId = mSalesProjectionTableLogic.getExpandedTreeValues(hierarchyNo);
            }
            if (tempId != null) {
                SalesRowDto tempDto = (SalesRowDto) tempId;

               
                if (checkValue) {
                    tempDto.setUncheckCount(tempDto.getUncheckCount() - newRecordsCount);
                } else {
                    tempDto.setUncheckCount(tempDto.getUncheckCount() + newRecordsCount);
                }

                updateChecks(tempId);
            }
        }
    }

    /**
     * updates the check value of a record.
     *
     * @param tempId
     */
    private void updateChecks(Object tempId) {

        leftTable = resultsTable.getLeftFreezeAsTable();
    
        SalesRowDto tempDto = (SalesRowDto) tempId;
        if (tempDto.getUncheckCount() != 0) {
            tempDto.addBooleanProperties(Constant.CHECK, false);
            if (leftTable.containsId(tempId)) {
                leftTable.getContainerProperty(tempId, Constant.CHECK).setValue(false);
            }
        } else {
            tempDto.addBooleanProperties(Constant.CHECK, true);
            if (leftTable.containsId(tempId)) {
                leftTable.getContainerProperty(tempId, Constant.CHECK).setValue(true);
            }
        }
    }

    /**
     * Updates the checked status of the child record, when a parent record is
     * checked.
     *
     * @param tableHierarchyNo
     * @param itemId
     * @param checkValue
     */
    private void updateCheckForChildLevels(String tableHierarchyNo, Object itemId, Boolean checkValue) {

        List<String> childTableHierarchyNos = mSalesProjectionTableLogic.getAllChildLevels(itemId);
        childTableHierarchyNos.add(tableHierarchyNo);
        for (String hierarchyNo : childTableHierarchyNos) {
            Object tempId = mSalesProjectionTableLogic.getcurrentTreeData(hierarchyNo);
            if (tempId == null) {
                tempId = mSalesProjectionTableLogic.getExpandedTreeValues(hierarchyNo);
            }
            if (tempId != null) {
                SalesRowDto tempDto = (SalesRowDto) tempId;

                if (checkValue) {
                    tempDto.setUncheckCount(0);
                } else {
                    tempDto.setUncheckCount(Integer.valueOf(tempDto.getCcpCount()));
                }
                updateChecks(tempId);
            }
        }

    }

    /**
     * Gets the hierarchy no. of the checked records.
     *
     * @return
     */
    public Set<String> getCheckedRecordsHierarchyNo() {
        uncheckRecordCount = 0;
        Set<String> finalHirarechyNo = new HashSet<>();
        for (String tableTreeLevelNo : mSalesProjectionTableLogic.getAllLevels()) {
            Object itemId = mSalesProjectionTableLogic.getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                itemId = mSalesProjectionTableLogic.getExpandedTreeValues(tableTreeLevelNo);
            }
            if (itemId != null) {
                int uncheckCount = ((SalesRowDto) itemId).getUncheckCount();
                  uncheckRecordCount += uncheckCount;
                int ccpCount = Integer.valueOf(((SalesRowDto) itemId).getCcpCount());
                if (ccpCount != uncheckCount) {
                    finalHirarechyNo.add(tableTreeLevelNo);
                }
            }
        }
        finalHirarechyNo.addAll(getManualEntryRefreshHiearachyNo());
        return finalHirarechyNo;
    }

    public Set<String> getReturnsCheckedRecordsHierarchyNo() {
        Set<String> finalHirarechyNo = new HashSet<>();
        for (String tableTreeLevelNo : mSalesProjectionTableLogic.getAllLevels()) {
            finalHirarechyNo.add(tableTreeLevelNo);
        }
        finalHirarechyNo.addAll(getManualEntryRefreshHiearachyNo());
        return finalHirarechyNo;
    }

    /**
     * Gets the hierarchy No. of the records that are changed to manipulate that
     * during table refresh.
     *
     * @return
     */
    private Set<String> getManualEntryRefreshHiearachyNo() {
        Set<String> finalHirarechyNo = new HashSet<>();
        for (String hirarechyNo : tableHirarechyNos) {
            if (hirarechyNo != null && !hirarechyNo.equalsIgnoreCase(Constant.NULL)) {
                finalHirarechyNo.addAll(mSalesProjectionTableLogic.getAllParentLevels(hirarechyNo));
                finalHirarechyNo.addAll(mSalesProjectionTableLogic.getAllChildLevels(hirarechyNo));
                finalHirarechyNo.add(hirarechyNo);
            }
        }
        tableHirarechyNos.clear();
        return finalHirarechyNo;
    }

    /**
     * Populate Button Logic for Commercial and Government Forecast. Contains
     * the logic to check and save the entered values in the Mass Update Field.
     */
    protected void populateButtonLogic() {
        List<String> populateValue = new ArrayList<String>();
        try {
            boolean isAtleastChkd = false;
            Collection<?> salesList = resultsTable.getLeftFreezeAsTable().getItemIds();
            if (!salesList.isEmpty()) {
                for (Object item : salesList) {
                    if ((Boolean) mSalesProjectionTableLogic.getContainerDataSource().getContainerProperty(item, Constant.CHECK).getValue()) {
                        isAtleastChkd = true;
                        break;
                    }
                }
            }
            if (startPeriod.getValue() != null && endPeriod.getValue() != null && !endDateValidationForReturns()) {
                AbstractNotificationUtils.getErrorNotification("Error", "The Start Period must be before the End Period.Please try again");
                return;
            }
            if (fieldDdlb.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification("No Field Selected", "Please select any Field to update");
            } else if (fieldDdlb.getValue() != null && (Constant.ACCOUNT_GROWTH.equals(String.valueOf(fieldDdlb.getValue()))
                    || Constant.PRODUCT_GROWTH.equals(String.valueOf(fieldDdlb.getValue()))
                    || Constant.PROJECTED_RETURN_PER.equals(String.valueOf(fieldDdlb.getValue()))
                    || Constant.PROJECTED_RETURN_AMT.equals(String.valueOf(fieldDdlb.getValue()))
                    || Constant.PROJECTED_RPU.equals(String.valueOf(fieldDdlb.getValue()))
                    || Constant.GROWTH_RATE.equals(String.valueOf(fieldDdlb.getValue()))) && startPeriod.getValue() == null) {
                
               if(returnsFlag){
                AbstractNotificationUtils.getErrorNotification("No Start Date Selected", "Please select a start date.");
               } else {
                AbstractNotificationUtils.getErrorNotification("No Start Date Selected", "A start date is required for an Account Growth or Product Growth.  Please select a start date and try again.");
               }
            } else if ((valueTxt.getValue() == null || StringUtils.isEmpty(valueTxt.getValue())) && (valueDdlb.getValue() == null || StringUtils.isEmpty(String.valueOf(valueDdlb.getValue()))) || Constant.SELECT_ONE.equals(String.valueOf(valueDdlb.getValue()))) {
                AbstractNotificationUtils.getErrorNotification("No Value Entered", "Please enter any value to update");
            } else if (!isAtleastChkd) {
                AbstractNotificationUtils.getErrorNotification("No record selected", "Please select at-least one record");
            } else {

                if (returnsFlag && !Constant.GROWTH_RATE.equals(String.valueOf(fieldDdlb.getValue())) ? populateLevel.getValue() == null : false) {
                    AbstractNotificationUtils.getErrorNotification("No Value Entered", "Please select any Mass Update Level to update");
                    return;
                }

                String endPeriodValue = null;
                String startPeriodValue = null;
                int startQuater;
                int startYear;
                int endQuater;
                int endYear;
                int length;
                String temp;
                String forecastPeriodend = null;
                if (endPeriod.getValue() == null) {

                    forecastPeriodend = rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1);
                    endQuater = rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1).charAt(1) - 48;
                    temp = rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1);
                    length = temp.length();
                    endYear = Integer.valueOf(temp.substring((length - 4), length));
                    if (projectionDTO.getFrequencyDivision() == 12) {
                        String peroidValue = rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1);
                        String month = peroidValue.substring(0, peroidValue.length() - 5);
                        endQuater = CommonUtils.getIntegerForMonth(month);
                    }
                } else {
                    endPeriodValue = endPeriod.getValue().toString();
                    forecastPeriodend = endPeriodValue;
                    endQuater = endPeriod.getValue().toString().charAt(1) - 48;
                    endPeriod.getValue().toString().length();

                    length = endPeriod.getValue().toString().length();
                    endYear = Integer.valueOf(endPeriod.getValue().toString().substring((length - 4), length));
                    if (projectionDTO.getFrequencyDivision() == 12) {
                        String peroidValue = endPeriod.getValue().toString();
                        String month = peroidValue.substring(0, peroidValue.length() - 5);
                        endQuater = CommonUtils.getIntegerForMonth(month);
                    }
                }
                if (startPeriod.getValue() == null) {
                    startQuater = rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1).charAt(1) - 48;
                    temp = rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1);
                    length = temp.length();
                    startYear = Integer.valueOf(temp.substring((length - 4), length));
                    if (projectionDTO.getFrequencyDivision() == 12) {
                        String peroidValue = rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1);
                        String month = peroidValue.substring(0, peroidValue.length() - 5);
                        startQuater = CommonUtils.getIntegerForMonth(month);
                    }
                } else {
                    startPeriodValue = String.valueOf(startPeriod.getValue());
                    startQuater = startPeriod.getValue().toString().charAt(1) - 48;
                    length = startPeriod.getValue().toString().length();
                    startYear = Integer.valueOf(startPeriod.getValue().toString().substring((length - 4), length));
                    if (projectionDTO.getFrequencyDivision() == 12) {
                        String peroidValue = startPeriod.getValue().toString();
                        String month = peroidValue.substring(0, peroidValue.length() - 5);
                        startQuater = CommonUtils.getIntegerForMonth(month);
                    }
                }
                boolean isUpdated = false;

                String enteredValue = StringUtils.EMPTY;
                enteredValue = Constant.GROUPFCAPS.equals(String.valueOf(fieldDdlb.getValue())) ? String.valueOf(valueDdlb.getValue()) : String.valueOf(valueTxt.getValue());

                String updateVariable;
                if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
                    if (Constant.PROJECTED_RETURN_AMT.equals(String.valueOf(fieldDdlb.getValue()))) {
                        updateVariable = Constant.PROJECTED_RETURN_AMT;
                    } else if (Constant.PROJECTED_RETURN_PER.equals(String.valueOf(fieldDdlb.getValue()))) {
                        updateVariable = Constant.PROJECTED_RETURN_PER;
                    } else if (Constant.PROJECTED_RPU.equals(String.valueOf(fieldDdlb.getValue()))) {
                        updateVariable = Constant.PROJECTED_RPU;
                    } else if (Constant.GROWTH_RATE.equals(String.valueOf(fieldDdlb.getValue()))) {
                        updateVariable = Constant.GROWTH_RATE;
                    } else {
                        updateVariable = StringUtils.EMPTY;
                    }
                } else {
                    if (Constant.ACCOUNT_GROWTH.equals(String.valueOf(fieldDdlb.getValue()))) {
                        updateVariable = Constant.ACCOUNT_GROWTH;
                    } else if (Constant.PRODUCT_GROWTH.equals(String.valueOf(fieldDdlb.getValue()))) {
                        updateVariable = Constant.PRODUCT_GROWTH;
                    } else if (Constant.GROUPFCAPS.equals(String.valueOf(fieldDdlb.getValue()))) {
                        updateVariable = Constant.GROUPFCAPS;
                    } else {
                        updateVariable = StringUtils.EMPTY;
                    }
                }

                if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
                    Set<String> returnDetilsSidSet = new HashSet<String>();
                    boolean levelFlag = false;
                    for (String tableTreeLevelNo : mSalesProjectionTableLogic.getAllLevels()) {
                        Object itemId = mSalesProjectionTableLogic.getcurrentTreeData(tableTreeLevelNo);
                        if (itemId == null) {
                            itemId = mSalesProjectionTableLogic.getExpandedTreeValues(tableTreeLevelNo);
                        }
                        if (itemId != null) {
                            Map<String, Double> selectedValues = new TreeMap<String, Double>();
                            SalesRowDto dto = (SalesRowDto) itemId;
                            if (dto.getPropertyValue(Constant.CHECK) != null && (Boolean) dto.getPropertyValue(Constant.CHECK) && Integer.parseInt(String.valueOf(populateLevel.getValue())) == dto.getLevelNo()) {
                                levelFlag = true;
                                String[] str = dto.getReturnDetailsSid().split(",");
                                for (String str1 : str) {
                                    returnDetilsSidSet.add(str1);
                                }
                                if (Constant.PROJECTED_RETURN_AMT.equals(String.valueOf(fieldDdlb.getValue()))) {
                                    for (Map.Entry<Object, Object> entry : dto.getProperties().entrySet()) {
                                        if (entry.getKey().toString().endsWith("ProjectedReturnAmount")) {
                                            Integer[] periods = getPeriod(entry.getKey(), projectionDTO.getFrequencyDivision());
                                            if (projectionDTO.getFrequencyDivision() == 1 && periods[0] >= startYear
                                                    && periods[0] <= endYear) {

                                                String oldValue = String.valueOf(entry.getValue()).replace("$", StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
                                                Double newNumber = Double.valueOf(enteredValue);
                                                Double oldNumber = Double.valueOf(oldValue);
                                                Double incOrDec = ((newNumber - oldNumber) / oldNumber) * 100;
                                                selectedValues.put(StringUtils.EMPTY + periods[0], incOrDec);
                                            } else if (periods[0] >= startQuater
                                                    && periods[0] <= endQuater
                                                    && periods[1] >= startYear
                                                    && periods[1] <= endYear) {

                                                String oldValue = String.valueOf(entry.getValue()).replace("$", StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
                                                Double newNumber = Double.valueOf(enteredValue);
                                                Double oldNumber = Double.valueOf(oldValue);
                                                Double incOrDec = ((newNumber - oldNumber) / oldNumber) * 100;
                                                selectedValues.put(periods[1] + "," + periods[0], incOrDec);
                                            }
                                        }
                                    }
                                }
                                if (dto.getReturnDetailsSid().split(",").length == 1) {
                                    salesLogic.saveOnMassUpdateReturns(projectionDTO, startYear, endYear, startQuater, endQuater, enteredValue, updateVariable, dto, true, selectedValues);
                                } else {
                                    salesLogic.saveOnMassUpdateReturns(projectionDTO, startYear, endYear, startQuater, endQuater, enteredValue, updateVariable, dto, false, selectedValues);
                                }
                            }
                        }
                    }
                    if (levelFlag) {
                        String[] returnDetilsSid = returnDetilsSidSet.toArray(new String[returnDetilsSidSet.size()]);
                        salesLogic.callRefreshProcedure(projectionDTO.getProjectionId(), Arrays.toString(returnDetilsSid).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY), (startPeriod + "," + forecastPeriodend), String.valueOf(projectionDTO.getUserId()), String.valueOf(projectionDTO.getSessionId()), true);
                        isUpdated = true;
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Selected Valid Record", "Please select a valid record");
                        return;
                    }
                } else {
                    salesLogic.saveOnMassUpdate(projectionDTO, startYear, endYear, startQuater, endQuater, enteredValue, updateVariable);
                    isUpdated = true;
                    refreshGroupDdlb();
                }

                    if (isUpdated) {
                        refreshTableData(getCheckedRecordsHierarchyNo());
                        startPeriod.select(startPeriodValue);
                        endPeriod.select(endPeriodValue);
                    }
                }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    /**
     * Method contains the logic for the Table Refresh.
     *
     * @param finalHirarechyNo
     */
    public void refreshTableData(Set<String> finalHirarechyNo) {
        mSalesProjectionTableLogic.forRefresh(finalHirarechyNo);
        mSalesProjectionTableLogic.setCurrentPage(mSalesProjectionTableLogic.getCurrentPage());
    }

    /**
     * Method contains the adjustment logic for Commercial and Government
     * Forecast.
     *
     * @param event
     */
    @UiHandler("adjust")
    public void adjustmentLogic(Button.ClickEvent event) {
        for (Object propertyId : checkedDiscountsPropertyIds) {
            String tripleHeader = resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeader(propertyId);
            if (tripleHeaderForCheckedDoubleHeader.get(tripleHeader) == null) {
                NotificationUtils.getErrorNotification("No period selected", "Please select which periods need to be included in the adjustment.");
                return;
            }
        }
        if (!String.valueOf(adjustment.getValue()).matches(Constant.NUM_WIHT2DECIMAL) || adjustment.getValue().length() > 38) {
            AbstractNotificationUtils.getErrorNotification("Incorrect data type entered", "Please enter a numeric value in the Adjustment text box.");
            adjustment.setValue(StringUtils.EMPTY);
            return;
        }
        if (allocMethodology.getValue() != null) {

            final String adjValue = String.valueOf(adjustment.getValue());
            if (adjValue != null && StringUtils.isNotBlank(adjValue)) {
                final String adjType = String.valueOf(type.getValue());
                final String adjBasis = String.valueOf(basis.getValue());
                final String adjVariable = String.valueOf(variable.getValue()).equals(Constant.SALES_SMALL) ? Constant.STRING_ONE : Constant.DASH;
                final String adjustPeriod = (String) adjustPeriods.getValue();
                final String adjMethodology = String.valueOf(allocMethodology.getValue());
                final String historyPeriods;
                final String projectionPeriods;

                if (adjustPeriod.equals(Constant.All)) {
                    if (adjMethodology.equals(Constant.HIST_PER_OF_BUISNESS)) {
                        historyPeriods = getSelectedHistoryPeriods();
                        if (StringUtils.EMPTY.equals(historyPeriods)) {
                            AbstractNotificationUtils.getErrorNotification("Improper calculation variables", "Please select a Historic Period");
                            return;
                        }
                        projectionPeriods = getAllProjectionPeriods();
                    } else {
                        historyPeriods = getAllProjectionPeriods();
                        projectionPeriods = getAllProjectionPeriods();
                    }
                } else {
                    if (adjMethodology.equals(Constant.HIST_PER_OF_BUISNESS)) {
                        historyPeriods = getSelectedHistoryPeriods();
                        if (String.valueOf(historyPeriods).equals(StringUtils.EMPTY)) {
                            AbstractNotificationUtils.getErrorNotification("Improper calculation variables", "Please select a Historic Period");
                            return;
                        }
                        projectionPeriods = getSelectedProjectionPeriods();
                        if (String.valueOf(projectionPeriods).equals(StringUtils.EMPTY)) {
                            AbstractNotificationUtils.getErrorNotification("Improper calculation variables", "Please select a Projection Period.");
                            return;
                        }
                    } else {
                        historyPeriods = getSelectedProjectionPeriods();
                        if (String.valueOf(historyPeriods).equals(StringUtils.EMPTY)) {
                            AbstractNotificationUtils.getErrorNotification("Improper calculation variables", "Please select a Projection Period.");
                            return;
                        }
                        projectionPeriods = getSelectedProjectionPeriods();
                    }
                }
                if (!checkForCheckedRecord()) {
                    AbstractNotificationUtils.getErrorNotification("No level selected", "Please select a Level from the drop down");
                    return;
                }
                String confirmMessage = "Confirm Incremental adjustment";
                String messageBody = StringUtils.EMPTY;

                if (adjType.equals("Incremental")) {
                    confirmMessage = "Confirm Incremental adjustment";
                    if (basis.getValue().equals(Constant.LabelConstants.AMOUNT)) {
                        if (variable.getValue().equals(Constant.UNIT)) {
                            messageBody = "You are about to make the following " + getFormatValue(Constant.UNIT_FORMAT, adjValue, StringUtils.EMPTY)
                                    + " adjustment for the following periods " + projectionPeriods + ".\n Are you sure you want to continue?";
                        } else {
                            messageBody = "You are about to make the following " + getFormatValue(Constant.TWO_DECIMAL, adjValue, Constant.CURRENCY)
                                    + " adjustment for the following periods " + projectionPeriods + ".\n Are you sure you want to continue?";
                        }
                    } else {
                        messageBody = "You are about to make the following " + adjValue
                                + "% adjustment for the following periods " + projectionPeriods + ".\n Are you sure you want to continue?";
                    }
                } else if (adjType.equals("Override")) {
                    confirmMessage = "Confirm Override";
                    if (basis.getValue().equals(Constant.LabelConstants.AMOUNT)) {
                        if (variable.getValue().equals(Constant.UNIT)) {
                            messageBody = "You are about to replace the current values in the list view with the following variable: "
                                    + getFormatValue(Constant.UNIT_FORMAT, adjValue, StringUtils.EMPTY) + ". \n Are you sure you want to continue?";
                        } else {
                            messageBody = "You are about to replace the current values in the list view with the following variable: "
                                    + getFormatValue(Constant.TWO_DECIMAL, adjValue, Constant.CURRENCY) + ". \n Are you sure you want to continue?";
                        }
                    } else {
                        messageBody = "You are about to replace the current values in the list view with the following variable: "
                                + adjValue + "%. \n Are you sure you want to continue?";
                    }
                }

                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                    }

                    @Override
                    public void yesMethod() {
                        try {
                            mSalesProjectionTableLogic.setRefresh(false);
                            salesLogic.adjustSalesProjection(projectionDTO, adjType, adjValue, adjBasis, adjVariable, adjMethodology, historyPeriods, projectionPeriods);
                            refreshTableData(getCheckedRecordsHierarchyNo());
                            mSalesProjectionTableLogic.setRefresh(true);
                        } catch (PortalException ex) {
                            LOGGER.error(ex.getMessage());
                        } catch (Exception ex) {
                            LOGGER.error(ex.getMessage());
                        }
                    }
                }.getOkCancelMessage(confirmMessage, messageBody);
            } else {
                AbstractNotificationUtils.getErrorNotification("No adjustment entered", "Please enter a numeric value in the Adjustment text box.");
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("No Allocation Methodology selected", "Please select an Allocation Methodology");
        }
    }

    /**
     * Gets the selected History periods that can be used for the adjustment and
     * calculation.
     *
     * @return
     */
    protected String getSelectedHistoryPeriods() {

        String selectedPeriods = StringUtils.EMPTY;
        List list = CommonUtils.getCurrent();
        int currentMonth = (Integer) list.get(0);
        int currentQuarter = (Integer) list.get(1);
        int currentYear = (Integer) list.get(2);
        int currentSemi = (Integer) list.get(3);
        String selectedFreq = projectionDTO.getFrequency();
        for (Object key : checkBoxMap.keySet()) {
            String temp[] = ((String) key).split("-");
            int tempYear = Integer.parseInt(ANNUAL.equals(selectedFreq) ? temp[0] : temp[1]);
            int tempQuarter = QUARTERLY.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll("[^0-9]", StringUtils.EMPTY)).trim()) : 0;
            int tempSemi = SEMI_ANNUAL.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll("[^0-9]", StringUtils.EMPTY)).trim()) : 0;
            int tempMonth = MONTHLY.equals(selectedFreq) ? CommonUtils.getMonthNumber(temp[0].trim()) : 0;
            boolean condition = false;
            switch (selectedFreq) {
                case MONTHLY:
                    condition = tempYear < currentYear || (tempYear == currentYear && tempMonth < currentMonth);
                    break;
                case Constant.QUARTERLY:
                    condition = tempYear < currentYear || (tempYear == currentYear && tempQuarter < currentQuarter);
                    break;
                case "Semi-Annual":
                    condition = tempYear < currentYear || (tempYear == currentYear && tempSemi < currentSemi);
                    break;
                case ANNUAL:
                    condition = tempYear < currentYear;
                    break;
            }
            if (condition) {
                if (checkBoxMap.get(key)) {
                    if (!selectedPeriods.equals(StringUtils.EMPTY)) {
                        selectedPeriods = selectedPeriods + ",";
                    }
                    String value = (String) key;
                    value = MONTHLY.equals(selectedFreq) ? CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + tempMonth + " " + tempYear : value.replace('-', ' ');
                    selectedPeriods = selectedPeriods + value;
                }
            }
        }
        return selectedPeriods.toUpperCase();
    }

    /**
     * Gets all Projection Periods that can be used for the adjustment.
     *
     * @return
     */
    protected String getAllProjectionPeriods() {

        String selectedPeriods = StringUtils.EMPTY;
        Date currentDate = projectionDTO.getSessionDTO().getForecastDTO().getProjectionStartDate();
        int projStartMonth = currentDate.getMonth() + 1;
        int projStartQuarter = CommonUtils.getQuarter(projStartMonth);
        int projStartSemi = CommonUtils.getSemiAnnual(projStartMonth);
        int projStartYear = currentDate.getYear() + 1900;
        String selectedFreq = projectionDTO.getFrequency();
        for (Object key : rightHeader.getDoubleColumns()) {
            if (!String.valueOf(key).equals(Constant.GROUP)) {
                String temp[] = ((String) key).split("-");
                int tempYear = Integer.parseInt(ANNUAL.equals(selectedFreq) ? temp[0] : temp[1]);
                int tempQuarter = QUARTERLY.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll("[^0-9]", StringUtils.EMPTY)).trim()) : 0;
                int tempSemi = SEMI_ANNUAL.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll("[^0-9]", StringUtils.EMPTY)).trim()) : 0;
                int tempMonth = MONTHLY.equals(selectedFreq) ? CommonUtils.getMonthNumber(temp[0].trim()) : 0;
                boolean condition = false;
                switch (selectedFreq) {
                    case MONTHLY:
                        condition = tempYear > projStartYear || (tempYear == projStartYear && tempMonth >= projStartMonth);
                        break;
                    case Constant.QUARTERLY:
                        condition = tempYear > projStartYear || (tempYear == projStartYear && tempQuarter >= projStartQuarter);
                        break;
                    case "Semi-Annual":
                        condition = tempYear > projStartYear || (tempYear == projStartYear && tempSemi >= projStartSemi);
                        break;
                    case ANNUAL:
                        condition = tempYear >= projStartYear;
                        break;
                }
                if (condition) {
                    if (!selectedPeriods.equals(StringUtils.EMPTY)) {
                        selectedPeriods = selectedPeriods + ",";
                    }
                    String value = (String) key;
                    value = MONTHLY.equals(selectedFreq) ? CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + tempMonth + " " + tempYear : value.replace('-', ' ');
                    selectedPeriods = selectedPeriods + value;
                }
            }
        }
        return selectedPeriods.toUpperCase();
    }

    /**
     * Gets the Projection Periods that can be used for the adjustment.
     *
     * @return
     */
    protected String getSelectedProjectionPeriods() {
        String selectedPeriods = StringUtils.EMPTY;
        Date currentDate = projectionDTO.getSessionDTO().getForecastDTO().getProjectionStartDate();
        int projStartMonth = currentDate.getMonth() + 1;
        int projStartQuarter = CommonUtils.getQuarter(projStartMonth);
        int projStartSemi = CommonUtils.getSemiAnnual(projStartMonth);
        int projStartYear = currentDate.getYear() + 1900;
        String selectedFreq = projectionDTO.getFrequency();

        for (Object key : checkBoxMap.keySet()) {
            String temp[] = ((String) key).split("-");
            int tempYear = Integer.parseInt(ANNUAL.equals(selectedFreq) ? temp[0] : temp[1]);
            int tempQuarter = QUARTERLY.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll("[^0-9]", StringUtils.EMPTY)).trim()) : 0;
            int tempSemi = SEMI_ANNUAL.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll("[^0-9]", StringUtils.EMPTY)).trim()) : 0;
            int tempMonth = MONTHLY.equals(selectedFreq) ? CommonUtils.getMonthNumber(temp[0].trim()) : 0;
            boolean condition = false;
            switch (selectedFreq) {
                case MONTHLY:
                    condition = tempYear > projStartYear || (tempYear == projStartYear && tempMonth >= projStartMonth);
                    break;
                case Constant.QUARTERLY:
                    condition = tempYear > projStartYear || (tempYear == projStartYear && tempQuarter >= projStartQuarter);
                    break;
                case "Semi-Annual":
                    condition = tempYear > projStartYear || (tempYear == projStartYear && tempSemi >= projStartSemi);
                    break;
                case ANNUAL:
                    condition = tempYear >= projStartYear;
                    break;
            }

            if (condition) {
                if (checkBoxMap.get(key)) {
                    if (!selectedPeriods.equals(StringUtils.EMPTY)) {
                        selectedPeriods = selectedPeriods + ",";
                    }
                    String value = (String) key;
                    value = MONTHLY.equals(selectedFreq) ? CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + tempMonth + " " + tempYear : value.replace('-', ' ');
                    selectedPeriods = selectedPeriods + value;
                }
            }
        }
        return selectedPeriods.toUpperCase();
    }

    /**
     * Formats the given value based on the passed decimal format.
     *
     * @param FORMAT
     * @param value
     * @param appendChar
     * @return
     */
    public String getFormatValue(DecimalFormat FORMAT, String value, String appendChar) {
        if (Constant.CURRENCY.equals(appendChar)) {
            value = appendChar.concat(FORMAT.format(Double.valueOf(value)));
        } else {
            value = FORMAT.format(Double.valueOf(value)).concat(appendChar);
        }
        return value;
    }

    /**
     * Method checks whether at least one record is checked in the table.
     *
     * @return
     */
    protected boolean checkForCheckedRecord() {
        boolean isChecked = false;
        Collection<?> salesList = resultsTable.getLeftFreezeAsTable().getItemIds();
        if (!salesList.isEmpty()) {
            for (Object item : salesList) {
                if ((Boolean) mSalesProjectionTableLogic.getContainerDataSource().getContainerProperty(item, Constant.CHECK).getValue()) {
                    isChecked = true;
                    break;
                }
            }
        }
        return isChecked;
    }

    /**
     * Methods contains the calculate button logic for Commercial and Government
     * Forecast..
     *
     * @throws Exception
     */
    public void calculateButtonLogic() throws Exception {

        Set<String> setMethodologiesValuesVal = new HashSet();

        if (methodology.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("No Methodology selected", "Please select a Methodology.");
            return;
        }
        String calcMethodology = String.valueOf(methodology.getValue());
        String selectedPeriods = getSelectedHistoryPeriods();

        if (forecastStartPeriod.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("No Start Period selected", "Please select a Start Period.");
            return;
        }
        if (forecastEndPeriod.getValue() != null && !endDateValidation()) {
            AbstractNotificationUtils.getErrorNotification("Error", "The Start Period must be before the End Period.Please try again");
            return;
        }

        if (!ismultipleDiscount()) {
            AbstractNotificationUtils.getErrorNotification("Error", "The selected baseline periods are within the Start Period and End Period range.  Please select a baseline period that is prior to the calculation range.");
            return;
        }
        switch ((checkBaseLinePeriods(forecastEndPeriod.getValue() != null))) {
            case 1:
                AbstractNotificationUtils.getErrorNotification("Baseline period within calculation range", alertMsg.getString("SP_MSG_ID_01"));
                return;
            case 2:
                AbstractNotificationUtils.getErrorNotification("Baseline period after calculation range", alertMsg.getString("SP_MSG_ID_02"));
                return;

            case 3:
                AbstractNotificationUtils.getErrorNotification("Error", alertMsg.getString("SP_MSG_ID_03"));
                return;
            case 4:
                AbstractNotificationUtils.getErrorNotification("No period selected", "Please select at least two historic periods to use as a baseline for each selected discount.");
                return;
        }

        if (!checkForCheckedRecord()) {
            AbstractNotificationUtils.getErrorNotification("No Hierarchy level selected", "Please select a level in the hierarchy for the methodology.  ");
            return;
        }

        if (!returnsFlag) {
            if (allocationBasis.isEnabled()) {
                if (allocationBasis.getValue() == null) {
                    AbstractNotificationUtils.getErrorNotification("No Allocation Basis selected", "Please select an Allocation Basis.");
                    return;
                }
            }

            String calcBased;
            if (getCalculationBased() == 0) {
                calcBased = Constant.SALES;
            } else if (getCalculationBased() == 1) {
                calcBased = Constant.UNITS;
            } else {
                AbstractNotificationUtils.getErrorNotification("Mandatory Error", "Please select either sales or units for all periods,\n cannot combine both sales and units");
                return;
            }
            if (StringUtils.isEmpty(selectedPeriods) && !"Average".equalsIgnoreCase(calcMethodology) && !"Customer GTS".equalsIgnoreCase(calcMethodology)) {
                AbstractNotificationUtils.getErrorNotification("Error", "Please select a Historic Period.");
                return;
            } else if ("Average".equalsIgnoreCase(calcMethodology)) {
                String[] historySelected = selectedPeriods.split(",");
                if (historySelected.length < 2) {
                    AbstractNotificationUtils.getErrorNotification("Error", "Please select at least two Historic Periods.");
                    return;
                }
            }
            if (calcMethodology.equals("Single Period") || Constant.PERCOFDEMAND.equals(calcMethodology) || Constant.PERCOFEXFACTORY.equals(calcMethodology) || Constant.PERCOFINVENTORYWITHDRAWAL.equals(calcMethodology) || Constant.PERC_OF_ADJUSTED_DEMAND.equals(calcMethodology)) {
                if (selectedPeriods.contains(",")) {
                    AbstractNotificationUtils.getErrorNotification("Single period ", "Please select a single period for calculation.");
                    return;
                }
            }

            if (Constant.ROLLINGANNUALTREND.equalsIgnoreCase(calcMethodology)) {
                String[] historySelected = selectedPeriods.split(",");
                Calendar ob = Calendar.getInstance();
                int curYear = ob.get(Calendar.YEAR);

                if (QUARTERLY.getConstant().equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length == 4) {
                        if (!String.valueOf(forecastStartPeriod.getValue()).trim().startsWith("Q1")) {
                            throwErrorMessage();
                            return;
                        }
                    } else {
                        throwErrorMessage();
                        return;
                    }

                } else if (SEMI_ANNUAL.getConstant().equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length == 2) {
                        if (!String.valueOf(forecastStartPeriod.getValue()).trim().startsWith("S1")) {
                            throwErrorMessage();
                            return;
                        }
                    } else {
                        throwErrorMessage();
                        return;
                    }
                } else if (ANNUAL.equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length != 1) {
                        throwErrorMessage();
                        return;
                    }
                } else if (MONTHLY.equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length == 12) {
                        if (!String.valueOf(forecastStartPeriod.getValue()).startsWith("JAN")) {
                        throwErrorMessage();
                        return;
                    }
                    } else {
                        throwErrorMessage();
                        return;
                }
                }
                if (!getValidation(selectedPeriods)) {

                    throwErrorMessage();
                    return;
                }
            }
            String startPeriodSID = CommonLogic.getPeriodSID(projectionDTO.getFrequency(), String.valueOf(forecastStartPeriod.getValue()), true);
            String endPeriodSID = forecastEndPeriod.getValue() == null ? StringUtils.EMPTY : CommonLogic.getPeriodSID(projectionDTO.getFrequency(), String.valueOf(forecastEndPeriod.getValue()), true);
            if ((!StringUtils.isEmpty(endPeriodSID)) && (Integer.valueOf(startPeriodSID) > Integer.valueOf(endPeriodSID))) {
                AbstractNotificationUtils.getErrorNotification("Error", "End Period should be greater than Start Period.");
                return;
            } else if (setMethodologiesValuesVal.contains(String.valueOf(methodology.getValue())) && !checkHistorySelectedCount(1)) {
                NotificationUtils.getErrorNotification("Error", "Please select only one period for the Single Period methodology.");
            }
            LOGGER.info("CALC Methodology :" + calcMethodology);
            isSalesCalculated = salesLogic.calculateSalesProjection(projectionDTO, calcMethodology, selectedPeriods, calcBased, startPeriodSID, endPeriodSID, String.valueOf(allocationBasis.getValue()));
            refreshTableData(getCheckedRecordsHierarchyNo());
        } else {
            String finalSelectedValue = StringUtils.EMPTY;
            if (MONTHLY.equals(projectionDTO.getFrequency())) {
                Map monthMap = new HashMap();
                monthMap.put("M1", "Jan");
                monthMap.put("M2", "Feb");
                monthMap.put("M3", "Mar");
                monthMap.put("M4", "Apr");
                monthMap.put("M5", "May");
                monthMap.put("M6", "Jun");
                monthMap.put("M7", "Jul");
                monthMap.put("M8", "Aug");
                monthMap.put("M9", "Sep");
                monthMap.put("M10", "Oct");
                monthMap.put("M11", "Nov");
                monthMap.put("M12", "Dec");

                String[] allPeriod = selectedPeriods.split(",");

                for (int i = 0; i < allPeriod.length; i++) {
                    String[] period = allPeriod[i].split(" ");
                    if (i == 0) {
                        finalSelectedValue += monthMap.get(period[0]) + " " + period[1];
                    } else {
                        finalSelectedValue += "," + monthMap.get(period[0]) + " " + period[1];
                    }

                }
                selectedPeriods = finalSelectedValue;
            }
              if (Constant.ROLLINGANNUALTREND.equalsIgnoreCase(calcMethodology)) {
                String[] historySelected = selectedPeriods.split(",");
                Calendar ob = Calendar.getInstance();
                int curYear = ob.get(Calendar.YEAR);

                if (QUARTERLY.getConstant().equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length == 4) {
                        if (!String.valueOf(forecastStartPeriod.getValue()).trim().startsWith("Q1")) {
                            throwErrorMessage();
                            return;
                        }
                    } else {
                        throwErrorMessage();
                        return;
                    }

                } else if (SEMI_ANNUAL.getConstant().equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length == 2) {
                        if (!String.valueOf(forecastStartPeriod.getValue()).trim().startsWith("S1")) {
                            throwErrorMessage();
                            return;
                        }
                    } else {
                        throwErrorMessage();
                        return;
                    }
                } else if (ANNUAL.equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length != 1) {
                        throwErrorMessage();
                        return;
                    }
                } else if (MONTHLY.equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length != 12) {                                            
                        throwErrorMessage();
                        return;
                    }
                }
//                if (!getValidation(selectedPeriods)) {
//                    throwErrorMessage();
//                    return;
//                }
            }
            String startPeriod = String.valueOf(forecastStartPeriod.getValue());
            String endPeriod = forecastEndPeriod.getValue() == null ? StringUtils.EMPTY : String.valueOf(forecastEndPeriod.getValue());
            isSalesCalculated = salesLogic.calculateReturnsProjection(projectionDTO, calcMethodology, selectedPeriods, startPeriod, endPeriod);
            refreshTableData(getReturnsCheckedRecordsHierarchyNo());

        }
    }

    /**
     * Gets the variable for the calculation.
     *
     * @return
     */
    public int getCalculationBased() {

        boolean tempSalesvalue = false;
        boolean tempUnitValue = false;

        for (Object key : radioMap.keySet()) {
            String value = radioMap.get(key);
            if (checkBoxMap.get(key)) {
                if (value != null) {
                    if (value.contains(Constant.ACTUALSALES)) {
                        tempSalesvalue = true;
                    }

                    if (value.contains(Constant.ACTUALUNITS)) {
                        tempUnitValue = true;
                    }
                }
            }
        }

        if (tempSalesvalue && tempUnitValue) {
            return -1;

        } else if (tempSalesvalue && !tempUnitValue) {

            return 0;
        } else if (!tempSalesvalue && tempUnitValue) {

            return 1;
        }

        return 0;
    }


    /**
     * Configures the frequency DDLB in the Sales Projection Selection.
     *
     * @param frequency
     * @param history
     * @param flag
     */
    private void configureFrequency(final ComboBox frequency, final ComboBox history, boolean flag) {
        frequency.addItem(MONTHLY);
        frequency.addItem(QUARTERLY.getConstant());

        frequency.addItem(SEMI_ANNUAL.getConstant());

        frequency.addItem(ANNUAL);
        frequency.select(QUARTERLY.getConstant());
        frequency.setNullSelectionAllowed(false);
        frequency.setImmediate(true);
        frequency.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                loadFrequency(frequency, history);
            }
        });
    }

    /**
     * Loads the history dropdown based on the frequency.
     *
     * @param frequency
     * @param history
     */
    public void loadFrequency(final ComboBox frequency, final ComboBox history) {
        LOGGER.info("loadFrequency for " + String.valueOf(frequency.getValue()));
        CommonUtils.frequenceValueChange(String.valueOf(frequency.getValue()), history, session);
        LOGGER.info("loadFrequency ends ");
    }

    /**
     * Checks for the valid selection in the Sales Projection Selection to load
     * the
     *
     * @return
     */
    public boolean checkSelection() {
        boolean checkSelection = false;
        if (variables.getValue() != null) {
            String tempVariables = variables.getValue().toString();

            tempVariables = tempVariables.substring(1, tempVariables.length() - 1);

            final String[] col = tempVariables.split(",");

            String actualsOrProjecton = actualsProjections.getValue().toString();

            boolean sales = false;
            boolean units = false;
            boolean accGrowth = false;
            boolean proGrowth = false;

            for (String value : col) {
                value = value.trim();
                switch (value) {
                    case Constant.SALES_SMALL:
                        sales = true;
                        break;
                    case Constant.UNITS_SMALL:
                        units = true;
                        break;
                    case Constant.ACCOUNT_GROWTH:
                        accGrowth = true;
                        break;
                    case Constant.PRODUCT_GROWTH:
                        proGrowth = true;
                        break;
                }
            }

            if (actualsOrProjecton.equalsIgnoreCase(Constant.ACTUALS)) {
                if (!sales && !units) {
                    AbstractNotificationUtils.getErrorNotification("Improper Variable Selection", "Please select a Variable as Sales or Units");
                } else {
                    checkSelection = true;
                }

            } else if (actualsOrProjecton.equalsIgnoreCase(Constant.PROJECTIONS) || actualsOrProjecton.equalsIgnoreCase(Constant.BOTH)) {
                if (!sales && !units && !accGrowth && !proGrowth) {
                    AbstractNotificationUtils.getErrorNotification("Improper Variable Selection", "Please select any one Variable");
                } else {
                    checkSelection = true;
                }
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("No Variable Selected", "Please select any one Variable");
        }

        return checkSelection;
    }

    protected void configureGraph() throws Exception {
        totalLives.setEnabled(true);

        totalLives.setEnabled(false);
    }

    /**
     * Loads the Periods for the mass update based on the frequency.
     */
    protected void loadPeriods() {
        startPeriod.removeAllItems();
        endPeriod.removeAllItems();
        forecastStartPeriod.removeAllItems();
        forecastEndPeriod.removeAllItems();
        startPeriod.addItem(SELECT_ONE.getConstant());
        endPeriod.addItem(SELECT_ONE.getConstant());
        forecastStartPeriod.addItem(SELECT_ONE.getConstant());
        forecastEndPeriod.addItem(SELECT_ONE.getConstant());

        if (Constant.DESCENDING.equalsIgnoreCase(String.valueOf(proPeriodOrd.getValue()))) {
            Collections.reverse(rightHeader.getDoubleProjectedHeaders());
            startPeriod.addItems(rightHeader.getDoubleProjectedHeaders());
            endPeriod.addItems(rightHeader.getDoubleProjectedHeaders());
        } else {
            startPeriod.addItems(rightHeader.getDoubleProjectedHeaders());
            endPeriod.addItems(rightHeader.getDoubleProjectedHeaders());
        }
        forecastStartPeriod.addItems(rightHeader.getDoubleProjectedHeaders());
        forecastEndPeriod.addItems(rightHeader.getDoubleProjectedHeaders());
        startPeriod.setNullSelectionAllowed(true);
        endPeriod.setNullSelectionAllowed(true);
        startPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
        endPeriod.setNullSelectionItemId(SELECT_ONE.getConstant());
        startPeriod.select(null);
        endPeriod.select(null);
        forecastStartPeriod.select(null);
        forecastEndPeriod.select(null);
    }

    /**
     * Loads the Level DDLB for the Expand / Collapse based on the Hierarchy.
     */
    public void loadLevelAndFilterValue() {
        LOGGER.info("loadLevelAndFilterValue initiated ");
        level.removeAllItems();
        level.addItem(SELECT_ONE);
        level.setNullSelectionItemId(SELECT_ONE);
        level.setValue(SELECT_ONE);
        List<Leveldto> hierarchy = null;
        if (projectionDTO.isIsCustomHierarchy()) {
            hierarchy = CommonLogic.getCustomTree(customId);
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            hierarchy = CommonLogic.getCustomerHierarchy(session.getProjectionId(), projectionDTO.getCustomerLevelNo(), projectionDTO.getCustRelationshipBuilderSid());
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            hierarchy = CommonLogic.getProductHierarchy(session.getProjectionId(), projectionDTO.getProductLevelNo(), projectionDTO.getProdRelationshipBuilderSid());
        }
        int maxLevel = hierarchy.size() - 1;
        if (hierarchy != null) {
            for (Leveldto levelDto : hierarchy) {
                String levelFiterSid = levelDto.getTreeLevelNo() + "~" + levelDto.getHierarchyIndicator();
                String caption = Constant.LEVEL+ levelDto.getTreeLevelNo() + " - " + levelDto.getLevel();
                Object itemId = levelFiterSid;
                if (levelDto.getCount() <= maxLevel) {
                    level.addItem(itemId);
                    level.setItemCaption(itemId, caption);
                }
            }
        }
        LOGGER.info("loadLevelAndFilterValue ends ");
    }

    /**
     * Configures the table for the excel export.
     */
    protected void configureExcelResultTable() {
        excelContainer = new CustomTreeContainer<SalesRowDto>(SalesRowDto.class);
        List<String> columnHeader = new ArrayList<String>();
        List<Object> visibleColumns = new ArrayList<Object>();
        projectionDTO.setHierarchyIndicator(Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue())) ? Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY : Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        if (returnsFlag) {
            excelTable.setRefresh(false);
            excelTable.setVisible(Boolean.FALSE);
            excelContainer.setColumnProperties(excelHeader.getProperties());
            excelTable.setContainerDataSource(excelContainer);
            excelTable.setVisibleColumns(excelHeader.getSingleColumns().toArray());
            excelTable.setColumnHeaders(excelHeader.getSingleHeaders().toArray(new String[excelHeader.getSingleHeaders().size()]));
            tableLayout.addComponent(excelTable);
        } else {
            visibleColumns.add(Constant.LEVELNAME);
            columnHeader.add("Level");
            visibleColumns.add(Constant.BASELINE);
            columnHeader.add("Base Line");
            visibleColumns.add(Constant.METHODOLOGY);
            columnHeader.add("Methodology");

            for (Object obj : excelHeader.getSingleColumns()) {
                visibleColumns.add(obj);
            }
            for (String header : excelHeader.getSingleHeaders()) {
                columnHeader.add(StringUtils.EMPTY + header);
            }
            excelTable.setRefresh(false);
            excelTable.setVisible(Boolean.FALSE);
            excelContainer.setColumnProperties(excelHeader.getProperties());
            excelTable.setContainerDataSource(excelContainer);
            excelTable.setVisibleColumns(visibleColumns.toArray());
            excelTable.setColumnHeaders(Arrays.copyOf(columnHeader.toArray(), columnHeader.size(), String[].class));
            tableLayout.addComponent(excelTable);
        }

    }

    protected void levelFilterDdlbChangeOption(boolean excelExport) {
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(levelFilter.getValue());
        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        if (levelNo < 0) {
            levelNo = 0;
        }
        projectionDTO.setIsFilter(true);
        if (levelNo == 0) {
            projectionDTO.setIsFilter(false);
        }
        String hierarchyNo = String.valueOf(levelHierarchy.get(1));
        loadExcelResultTable(levelNo, hierarchyNo);
    }

    /**
     * Initial setup of the excel container based on the selection.
     *
     * @param levelNo
     * @param hierarchyNo
     */
    protected void loadExcelResultTable(int levelNo, String hierarchyNo) {
        try {
            excelContainer.removeAllItems();
            projectionDTO.setFilterLevelNo(levelNo);
            projectionDTO.setFilterHierarchyNo(hierarchyNo);
            projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setHierarchyIndicator(Constant.CUSTOMER_SMALL.equals(String.valueOf(view.getValue())) ? Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY : Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            projectionDTO.setCustomerLevelNo(Integer.valueOf(session.getCustomerLevelNumber()));
            projectionDTO.setProductLevelNo(Integer.valueOf(session.getProductLevelNumber()));
            projectionDTO.clearNonFetchableIndex();
            int count = 0;

            if ("Returns".equalsIgnoreCase(screenName)) {
                count = salesLogic.getConfiguredSalesProjectionCount(new Object(), projectionDTO, true, initialProjSelDTO);
            } else {
                count = salesLogic.getConfiguredSalesProjectionCount(new Object(), projectionDTO, true, projectionDTO);
            }
            List<SalesRowDto> resultList = salesLogic.getConfiguredSalesProjection(new Object(), 0, count, projectionDTO);
            loadDataToContainer(resultList, null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Recursive method to load the inner levels of each level in the Sales
     * Projection grid for the excel Container.
     *
     * @param id
     */
    protected void addLowerLevelsForExport(Object id) {
        try {
            projectionDTO.setFilterLevelNo(0);
            projectionDTO.setFilterHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            int count = salesLogic.getConfiguredSalesProjectionCount(id, projectionDTO, true, projectionDTO);
            List<SalesRowDto> resultList = salesLogic.getConfiguredSalesProjection(id, 0, count, projectionDTO);
            loadDataToContainer(resultList, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Initial Method Call to load the excel container.
     *
     * @param resultList
     * @param parentId
     */
    protected void loadDataToContainer(List<SalesRowDto> resultList, Object parentId) {
        try {
            if (resultList.size() > 0) {
                for (SalesRowDto dto : resultList) {
                    excelContainer.addBean(dto);
                    if (parentId != null) {
                        excelContainer.setParent(dto, parentId);
                    }
                    if (dto.getParent() == 1) {
                        excelContainer.setChildrenAllowed(dto, true);
                        addLowerLevelsForExport(dto);
                    } else {
                        excelContainer.setChildrenAllowed(dto, false);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Method does the initial setup for the Expand/Collapse.
     *
     * @param isExpand
     * @param value
     */
    protected void expandCollapseLevelOption(boolean isExpand, Object value) {
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(value);
        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));

        if (levelNo > 0) {
            Object val = levelFilter.getValue();
            if (val != null) {
                if (!SELECT_ONE.getConstant().equals(String.valueOf(val))) {
                    levelFilter.setValue(SELECT_ONE);
                    mSalesProjectionTableLogic.clearAll();
                    projectionDTO.setLevelFilter(false);
                    projectionDTO.setLevelFilterValue(StringUtils.EMPTY);
                } else {
                    mSalesProjectionTableLogic.clearAllExceptCurrentPage();
                }
            } else {
                mSalesProjectionTableLogic.clearAllExceptCurrentPage();
            }
            if (!isExpand) {
                levelNo--;
            }
            mSalesProjectionTableLogic.loadExpandData(levelNo);
        }
    }

    /**
     * Configures the fields for the view mode.
     */
    private void configureOnViewMode() {
        massUpdateLayout.setEnabled(false);
        forecastLayout.setEnabled(false);
        adjustmentLayout.setEnabled(false);
        pmpy.setEnabled(false);

    }

    /**
     * Launches the Alternate Contract Lookup to import the actuals for the
     * Contract that don't have the actuals
     *
     * @throws PortalException
     * @throws Exception
     */
    protected void contractLookupLogic() throws PortalException, Exception {
        LOGGER.info("Entering Contract LookUp Logic");
        boolean contractSelected = false;
        SalesRowDto salesRow = new SalesRowDto();
        int i = 0;
        for (Object obj : rightTable.getItemIds()) {
            SalesRowDto dto = getBeanFromId(obj);
            if ((Boolean) dto.getPropertyValue(Constant.CHECK) && (("CONTRACT".equalsIgnoreCase(dto.getHierarchyLevel()) || "CH".equalsIgnoreCase(dto.getHierarchyLevel()) || Constant.CONTRACT.equalsIgnoreCase(dto.getHierarchyLevel())))) {
                contractSelected = true;
                i++;
                salesRow = dto;
            }
        }

        if (contractSelected && i == 1) {

            if (!salesLogic.checkActuals(salesRow.getHierarchyNo())) {
                tableHirarechyNos.add(mSalesProjectionTableLogic.getTreeLevelonCurrentPage(salesRow));
                final ContractBrandLookup contractBrandLookup = new ContractBrandLookup("Alternate History", projectionDTO.getSessionDTO(), Constant.CONTRACT, projectionDTO.getHierarchyNo(), screenName);
                contractBrandLookup.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
                        ContractBrandDTO dto = contractBrandLookup.getContractBrandDTO();
                        if (dto != null) {
                            contract.setReadOnly(false);
                            contract.setValue(dto.getContractName());
                        }
                        contract.setReadOnly(true);
                    }
                });
                getUI().addWindow(contractBrandLookup);
            } else {
                AbstractNotificationUtils.getErrorNotification("Contract LookUp", "Selected contract should not have  History");
            }
        } else {
            if (contractSelected && i > 1) {
                AbstractNotificationUtils.getErrorNotification("More than one Contract Selected",
                        "There are More than one Contract selected.\n Please select only one Contract and try again");
            } else {
                AbstractNotificationUtils.getErrorNotification("No Contract Selected.", "Please select a Contract. ");
            }
        }

        LOGGER.info("Ending Contract LookUp Logic");
    }


    /**
     * Launches the Alternate Brand Lookup to import the actuals for the brand
     * that don't have the actuals
     *
     * @throws PortalException
     * @throws Exception
     */
    protected void brandLookupLogic() throws PortalException, Exception {
        boolean contractSelected = false;
        SalesRowDto salesRow = new SalesRowDto();
        int i = 0;
        for (Object obj : rightTable.getItemIds()) {
            SalesRowDto dto = getBeanFromId(obj);
            if ((Boolean) dto.getPropertyValue(Constant.CHECK) && (("BRAND".equalsIgnoreCase(dto.getHierarchyLevel()) || Constant.BRAND.equalsIgnoreCase(dto.getHierarchyLevel())))) {
                contractSelected = true;
                i++;
                salesRow = dto;
            }
        }
        if (contractSelected && i == 1) {
            if (!salesLogic.checkActuals(salesRow.getHierarchyNo())) {
                tableHirarechyNos.add(mSalesProjectionTableLogic.getTreeLevelonCurrentPage(salesRow));
                final ContractBrandLookup contractBrandLookup = new ContractBrandLookup("Alternate History", projectionDTO.getSessionDTO(), Constant.BRAND, projectionDTO.getHierarchyNo(), screenName);
                getUI().addWindow(contractBrandLookup);
                contractBrandLookup.addCloseListener(new Window.CloseListener() {
                    public void windowClose(Window.CloseEvent e) {
                        mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
                        ContractBrandDTO dto = contractBrandLookup.getContractBrandDTO();
                        if (dto != null) {
                            brand.setReadOnly(false);
                            brand.setValue(dto.getBrand());
                        }
                        brand.setReadOnly(true);
                    }
                });
            } else {
                AbstractNotificationUtils.getErrorNotification("Brand LookUp", "Selected Brand should not have  History");
            }
        } else {
            if (contractSelected && i > 1) {
                AbstractNotificationUtils.getErrorNotification("More than one Brand Selected",
                        "There are More than one Brands selected.\n Please select only one Brand and try again");
            } else {
                AbstractNotificationUtils.getErrorNotification("No Brand Selected.", "Please select a Brand. ");
            }
        }
    }


    private void setProjectionSelection() {
        Map<Object, Object> map = null;
        if ("Returns".equalsIgnoreCase(screenName)) {
            map = CommonLogic.getReturnsProjectionSelection(session.getProjectionId(), "Sales Projection");
        } else {
            map = CommonLogic.getNMProjectionSelection(session.getProjectionId(), "Sales Projection");
        }

        if (map != null && !map.isEmpty()) {
            Object value = map.get(Constant.FREQUENCY_SMALL);
            if (value != null) {
                nmFrequencyDdlb.setValue(map.get(Constant.FREQUENCY_SMALL));
                nmFrequencyDdlb.setImmediate(true);
            }
            value = map.get(Constant.HISTORY_CAPS);
            if (value != null) {
                int i = Integer.parseInt(String.valueOf(value));
                historyDdlb.setValue(i);
                historyDdlb.setImmediate(true);
            }
            value = map.get(Constant.PERIOD_ORDER);
            if (value != null) {
                proPeriodOrd.setValue(String.valueOf(value));
            }
            value = map.get("Actuals/Projections");
            if (value != null) {
                actualsProjections.setValue(String.valueOf(value));
            }
            value = map.get(Constant.VARIABLES);

            if (value != null) {
                String value1 = (String) value;
                value1 = value1.substring(1, value1.length() - 1);
                String[] col = value1.trim().split(",");
                for (int i = 0; i < col.length; i++) {
                    String tempValue = col[i].trim();
                    if (tempValue.equalsIgnoreCase(Constant.SALES_SMALL)) {
                        variables.select(Constant.SALES_SMALL);
                    }
                    if (tempValue.equalsIgnoreCase(Constant.UNITS_SMALL)) {
                        variables.select(Constant.UNITS_SMALL);
                    }
                    if (tempValue.equalsIgnoreCase(Constant.PRODUCT_GROWTH)) {
                        variables.select(Constant.PRODUCT_GROWTH);
                    }

                    if (tempValue.equalsIgnoreCase(Constant.ACCOUNT_GROWTH)) {
                        variables.select(Constant.ACCOUNT_GROWTH);
                    }
                    variables.setImmediate(true);
                }
            }
        }
    }

    /**
     * Refreshes the group ddlb after populate button.
     */
    public void refreshGroupDdlb() {
        try {
            massGroupBean.removeAllItems();
            massGroupBean.addBean(Constant.SELECT_ONE);
            massGroupBean.addAll(salesLogic.loadSalesGroup(projectionDTO));
            groupBean.removeAllItems();
            groupBean.addBean(Constant.SHOW_ALL_GROUPS);
            groupBean.addAll(salesLogic.loadSalesGroup(projectionDTO));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public boolean getValidation(String selectedPeriods) {
        boolean flag = true;
        if (!ANNUAL.equals(String.valueOf(nmFrequencyDdlb.getValue()))) {
            String[] historySelected = selectedPeriods.split(",");
            for (int i = 0; i < historySelected.length; i++) {
                if (!historySelected[0].split(" ")[1].equalsIgnoreCase(historySelected[i].split(" ")[1])) {
                    flag = false;
                    return flag;
                }
            }
        } else {
            if (selectedPeriods.length() > 4) {
                flag = false;
                return flag;
            }
        }
        return flag;
    }

    public boolean getRangeValidation(String selectedPeriods) {
        boolean flag = false;
        String monthValue = StringUtils.EMPTY;
        String[] historySelected = selectedPeriods.split(",");
        if (MONTHLY.equalsIgnoreCase(String.valueOf(nmFrequencyDdlb.getValue()))) {
            String[] month = String.valueOf(forecastStartPeriod.getValue()).split(" ");
            monthValue = CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + CommonUtils.getMonthNumber(month[0].trim()) + " " + month[1];
        }
        for (int i = 0; i < historySelected.length; i++) {
            if (historySelected[i].equalsIgnoreCase(String.valueOf(forecastStartPeriod.getValue()))
                    || historySelected[i].equalsIgnoreCase(monthValue)) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    @UiHandler("methodologyDdlb")
    public void methodologyDdlb(Property.ValueChangeEvent event) {
        LOGGER.info("methodologyDdlb ValueChangeEvent initiated " + methodology.getValue());

        if (methodology.getValue() != null && (Constant.PERCOFDEMAND.equals(methodology.getValue()) || Constant.PERCOFEXFACTORY.equals(methodology.getValue())
                || Constant.PERCOFEXFACTORYSALES.equals(methodology.getValue()) || Constant.PERCOFINVENTORYWITHDRAWAL.equals(methodology.getValue())
                || Constant.CUSTOMER_GTS.equals(methodology.getValue()) || Constant.PERC_OF_ADJUSTED_DEMAND.equals(methodology.getValue()))) {
            allocationBasis.select(Constant.SELECT_ONE);
            allocationBasis.setEnabled(false);
        } else {
            allocationBasis.setEnabled(true);
        }
        LOGGER.info("methodologyDdlb ValueChangeEvent ends ");
    }

    public boolean isSalesCalculated() {
        return isSalesCalculated;
    }

    public boolean endDateValidation() {
        if (!ANNUAL.equals(String.valueOf(nmFrequencyDdlb.getValue()))) {
            String startValue = forecastStartPeriod.getValue().toString().replace(" ", "~").trim();
            String endValue = forecastEndPeriod.getValue().toString().replace(" ", "~").trim();
            String startValue1[] = startValue.split("~");
            String endValue1[] = endValue.split("~");
            if (Integer.valueOf(startValue1[1].trim()) > Integer.valueOf(endValue1[1].trim())) {
                return false;
            } else if (Integer.valueOf(startValue1[1].trim()) == Integer.valueOf(endValue1[1].trim())) {
                startValue1[0] = startValue1[0].replace(Constant.Q, StringUtils.EMPTY).trim();
                endValue1[0] = endValue1[0].replace(Constant.S, StringUtils.EMPTY).trim();
                if (Integer.valueOf(startValue1[0]) > Integer.valueOf(endValue1[0])) {
                    return false;
                } else {
                    return true;
                }

            } else {
                return true;
            }
        } else {
            String startValue = forecastStartPeriod.getValue().toString();
            String endValue = forecastEndPeriod.getValue().toString();
            if (Integer.valueOf(startValue) > Integer.valueOf(endValue)) {
                return false;
            } else {
                return true;
            }
        }
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

    /**
     * Checks the Base Line Selection and return the values that is used to
     * display the Alert Message.
     *
     * @param isEndPeriodSelected
     * @return m = 1, If the base line selection is within the start and end
     * period. m = 2, If the base line is selection after start or end period. m
     * = 0, If the base line is selected correctly.
     */
    private int checkBaseLinePeriods(boolean isEndPeriodSelected) {

        String frequency = String.valueOf(nmFrequencyDdlb.getValue());
        String forecastStart = String.valueOf(forecastStartPeriod.getValue());
        String forecastEnd = String.valueOf(forecastEndPeriod.getValue());
        int startPeriodValue = !ANNUAL.equals(frequency) ? getQuaterAndYear(forecastStart, false) : Integer.valueOf(forecastStart);
        int endPeriodValue = isEndPeriodSelected ? !ANNUAL.equals(frequency) ? getQuaterAndYear(forecastEnd, false) : Integer.valueOf(forecastEnd) : 0;

        int m = 0;
        int count = 0;
        for (Map.Entry<Object, Boolean> entry : checkBoxMap.entrySet()) {
            if (checkBoxMap.get(entry.getKey())) {
                String columnName = String.valueOf(entry.getKey());
                int temp = !ANNUAL.equals(frequency) ? getQuaterAndYear(columnName, true) : Integer.valueOf(columnName);
                if (entry.getValue() == true) {
                    count = count + 1;
                }
                if (isEndPeriodSelected && temp >= startPeriodValue && temp <= endPeriodValue) {
                    m = 1;
                    break;
                } else if ((temp >= startPeriodValue && !isEndPeriodSelected) || (isEndPeriodSelected && temp >= startPeriodValue && temp >= endPeriodValue)) {
                    m = 2;
                    break;
                }
            }
        }

        if (count == 0) {
            m = 3;
        }
        if (methodology.getValue().equals("Average") && count < 2 && count != 0) {
            m = 4;
        }
        if (methodology.getValue().equals("Single Period") && count > 1) {
            m = 5;
        }
        if (methodology.getValue().equals(Constant.CUSTOMER_GTS)) {
            m = 5;
        }
        return m;
    }

    /**
     * Method returns a integer value which has the Year followed by the
     * frequency value which used to compare periods.
     *
     * @param str
     * @param isTableColumn
     * @return
     */
    private int getQuaterAndYear(String str, boolean isTableColumn) {
        int a[] = new int[2];

        String[] splited = str.split(isTableColumn ? "-" : "\\s+");
        int resultValue = 0;
        a[1] = Integer.valueOf(splited[1]);

        if (QUARTERLY.getConstant().equals(String.valueOf(nmFrequencyDdlb.getValue()))) {
            a[0] = Integer.valueOf(splited[0].replaceAll(isTableColumn ? "[q]+" : "[Q]+", StringUtils.EMPTY));
            resultValue = Integer.valueOf(a[1] + StringUtils.EMPTY + a[0]);
        } else if (SEMI_ANNUAL.getConstant().equals(String.valueOf(nmFrequencyDdlb.getValue()))) {
            a[0] = Integer.valueOf(splited[0].replaceAll(isTableColumn ? "[s]+" : "[S]+", StringUtils.EMPTY));
            resultValue = Integer.valueOf(a[1] + StringUtils.EMPTY + a[0]);
        } else if (MONTHLY.equals(String.valueOf(nmFrequencyDdlb.getValue()))) {
            DateFormatSymbols dfs = new DateFormatSymbols();
            List<String> monthsList = Arrays.asList(dfs.getShortMonths());
            a[0] = monthsList.indexOf(StringUtils.capitalize(splited[0].trim()));
            resultValue = Integer.valueOf(a[1] + (a[0] < 10 ? Constant.DASH + a[0] : StringUtils.EMPTY + a[0]));
        }

        return resultValue;
    }

    private boolean checkHistorySelectedCount(int i) {
        for (Object key : tripleHeaderForCheckedDoubleHeader.keySet()) {
            Map<String, List<String>> checkedDoubleHeaders = tripleHeaderForCheckedDoubleHeader.get(String.valueOf(key));

            if (checkedDoubleHeaders != null) {
                List<String> checkedHistoryList = checkedDoubleHeaders.get("H");
                if (checkedHistoryList != null) {
                    if (i == 2 && checkedHistoryList.size() < i) {

                        return false;
                    }
                    if (i == 0 && checkedHistoryList.size() == i) {
                        return false;
                    }
                    if (i == 1 && checkedHistoryList.size() != i) {
                        return false;
                    }
                } else {
                    return false;
                }

            } else {
                return false;
            }
        }
        return true;

    }

    public void NonMandatedFilter() {
        leftTable.setFilterGenerator(new ExtFilterGenerator() {

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
                    ComboBox group = new ComboBox();
                    group.addValueChangeListener(groupFilterValueChangeListener);
                    group.setContainerDataSource(groupBean);
                    group.setNullSelectionAllowed(true);
                    group.setNullSelectionItemId(Constant.SHOW_ALL_GROUPS);
                    group.select(Constant.SHOW_ALL_GROUPS);
                    group.setWidth("100%");
                    return group;
                } else if (Constant.METHODOLOGY.equals(propertyId)) {
                    TextField metohdologyFilter = new TextField();
                    metohdologyFilter.setReadOnly(true);
                    metohdologyFilter.setWidth("100%");
                    return metohdologyFilter;
                } else if (Constant.BASELINE.equals(propertyId)) {
                    TextField baseLineFilter = new TextField();
                    baseLineFilter.setReadOnly(true);
                    baseLineFilter.setWidth("100%");
                    return baseLineFilter;
                } else if (Constant.LEVELNAME.equals(propertyId)) {
                    TextField levelField = new TextField();
                    levelField.setReadOnly(true);
                    levelField.setWidth("100%");
                    return levelField;
                } else if (Constant.CHECK.equals(propertyId)) {
                    TextField checkField = new TextField();
                    checkField.setWidth("100%");
                    checkField.setReadOnly(true);
                    return checkField;
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
    }

    public void MandatedFilter() {
        leftTable.setFilterGenerator(new ExtFilterGenerator() {

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

                } else if (Constant.METHODOLOGY.equals(propertyId)) {
                    TextField metohdologyFilter = new TextField();
                    metohdologyFilter.setReadOnly(true);
                    metohdologyFilter.setWidth("100%");
                    return metohdologyFilter;

                } else if (Constant.BASELINE.equals(propertyId)) {
                    TextField baseLineFilter = new TextField();
                    baseLineFilter.setReadOnly(true);
                    baseLineFilter.setWidth("100%");
                    return baseLineFilter;

                } else if (Constant.LEVELNAME.equals(propertyId)) {
                    TextField levelField = new TextField();
                    levelField.setReadOnly(true);
                    levelField.setWidth("100%");
                    return levelField;

                } else if (Constant.CHECK.equals(propertyId)) {
                    TextField checkField = new TextField();
                    checkField.setWidth("100%");
                    checkField.setReadOnly(true);
                    return checkField;
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
    }

    private void throwErrorMessage() {
        AbstractNotificationUtils.getErrorNotification("Select complete annual period",
                "The Rolling Annual Trend methodology requires a complete calendar year of periods to use as a baseline.\n  Please select a complete calendar year of periods and try again.");

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

    private Integer[] getPeriod(Object key, int frequencyDivision) {
        Integer[] periods = new Integer[2];
        switch (frequencyDivision) {
            case 1:
                periods[0] = Integer.valueOf(key.toString().substring(0, 4));
                break;
            case 2:
                periods[0] = (int) key.toString().charAt(1) - 48;
                periods[1] = Integer.valueOf(key.toString().substring(3, 7));
                break;
            case 4:
                periods[0] = (int) key.toString().charAt(1) - 48;
                periods[1] = Integer.valueOf(key.toString().substring(3, 7));
                break;
            case 12:
                periods[0] = CommonUtils.getIntegerForMonth(StringUtils.capitalize(key.toString().substring(0, 3)));
                periods[1] = Integer.valueOf(key.toString().substring(4, 8));
                break;
        }
        return periods;
    }

    public boolean endDateValidationForReturns() {
        if (!ANNUAL.equals(String.valueOf(nmFrequencyDdlb.getValue())) && !MONTHLY.equals(String.valueOf(nmFrequencyDdlb.getValue()))) {
            String startValue = startPeriod.getValue().toString().replace(" ", "~").trim();
            String endValue = endPeriod.getValue().toString().replace(" ", "~").trim();
            String startValue1[] = startValue.split("~");
            String endValue1[] = endValue.split("~");
            if (Integer.valueOf(startValue1[1].trim()) > Integer.valueOf(endValue1[1].trim())) {
                return false;
            } else if (Integer.valueOf(startValue1[1].trim()) == Integer.valueOf(endValue1[1].trim())) {
                startValue1[0] = startValue1[0].replace(Constant.Q, StringUtils.EMPTY).trim();
                endValue1[0] = endValue1[0].replace(Constant.S, StringUtils.EMPTY).trim();
                if (Integer.valueOf(startValue1[0]) > Integer.valueOf(endValue1[0])) {
                    return false;
                } else {
                    return true;
                }

            } else {
                return true;
            }
        } else if (MONTHLY.equals(String.valueOf(nmFrequencyDdlb.getValue()))) {
            Map<String, String> monthmap = new HashMap<>();
            loadMonthMap(monthmap);
            String startTempYear = startPeriod.getValue().toString().trim().substring(3);
            String startTmpSubYear = startPeriod.getValue().toString().replace(startTempYear, StringUtils.EMPTY).trim();
            String startTempSubYear = monthmap.get(startTmpSubYear);
            String endTempYear = endPeriod.getValue().toString().trim().substring(3);
            String endTmpSubYear = endPeriod.getValue().toString().replace(endTempYear, StringUtils.EMPTY).trim();
            String endTempSubYear = monthmap.get(endTmpSubYear);
            if (Double.valueOf(startTempYear + startTempSubYear) < Double.valueOf(endTempYear + endTempSubYear)) {
                return true;
            } else {
                return false;
            }
        } else {
            String startValue = startPeriod.getValue().toString();
            String endValue = endPeriod.getValue().toString();
            if (Integer.valueOf(startValue) > Integer.valueOf(endValue)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean validateForAlternateHistory() {

        if (CUSTOMER.getConstant().equals(String.valueOf(view.getValue()))) {

            int numoFCustomers = getCheckedCustomercount(logic);

            if (numoFCustomers > 0) {

                int projectionId = session.getProjectionId();
                String ccpIds = logic.getCCPList(logic.buildCCPListQuery(true, projectionId,session.getUserId(), session.getSessionId()));

                boolean flag = logic.checkActuals(ccpIds, session);

                if (flag) {
                    NotificationUtils.getAlertNotification("Info", "Selected Customer has Actuals");
                    return false;
                }

            } else {

                NotificationUtils.getErrorNotification("No Trading Partner Selected.", "Please select a Trading Partner.");
                return false;

            }

        } else if (PRODUCT.getConstant().equals(String.valueOf(view.getValue()))) {
            int numOfNDC = getCheckedProductCount(logic);
            if (numOfNDC > 0) {
                String ccpIds = logic.getCCPList(logic.buildCCPListQuery(false, session.getProjectionId(),session.getUserId(), session.getSessionId()));
                boolean flag = logic.checkActuals(ccpIds, session);

                if (flag) {
                    NotificationUtils.getAlertNotification("Info", "Selected NDC has Actuals");
                    return false;
                }
            } else {

                NotificationUtils.getAlertNotification("No NDC Level Selected", "Please select a NDC level for which  Alternate History to be imported.");
                return false;

            }
        }
        return true;
    }

    public int getCheckedCustomercount(final AlternateHistoryLogic logic) {
        return logic.getCCPCount(logic.buildCCPCountQuery(true, session.getProjectionId(), session.getUserId(), session.getSessionId()));
    }

    public int getCheckedProductCount(final AlternateHistoryLogic logic) {
        return logic.getCCPCount(logic.buildCCPCountQuery(false, session.getProjectionId(), session.getUserId(), session.getSessionId()));
    }

            }
