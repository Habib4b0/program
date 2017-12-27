    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.abstractforecast;

import static com.stpl.app.gtnforecasting.utils.CommonUtil.stringNullCheck;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.isInteger;
import static com.stpl.app.gtnforecasting.utils.Constant.ANNUAL;
import static com.stpl.app.gtnforecasting.utils.Constant.MONTHLY;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOMER;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOMER_HIERARCHY;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOM_HIERARCHY;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT_HIERARCHY;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_SALES_PROJECTION;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.utils.Constants.ResourceConstants.GRAPH_IMAGE_PATH;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesRowDto;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.GroupFilter;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.salesprojection.logic.AlternateHistoryLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic.MSalesProjectionTableLogic;
import com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic.NMSalesProjectionTableLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.form.lookups.AlternateHistory;
import com.stpl.app.gtnforecasting.ui.form.lookups.CustomTreeBuild;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import com.stpl.app.gtnforecasting.utils.TabNameUtil;
import com.stpl.app.gtnforecasting.utils.TotalLivesChart;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.Constants;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.extfilteringtable.ExtPagedTreeTable;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.extfilteringtable.PageTreeTableLogic;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.Position;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;

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
    private static final Logger LOGGER = Logger
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
    public ExtTreeContainer<SalesRowDto> customContainer = new ExtTreeContainer<>(SalesRowDto.class, ExtContainer.DataStructureMode.MAP);
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
    @UiField("tabsheet1")
    protected TabSheet tabsheet1;
    @UiField("massUpdateLayout")
    private VerticalLayout massUpdateLayout;
    @UiField("salesProjectionSelectionLayout")
    protected VerticalLayout salesProjectionSelectionLayout;
    @UiField("salesProjectionfilterLayout")
    protected VerticalLayout salesProjectionfilterLayout;
    @UiField("customerlevelDdlb")
    protected ComboBox customerlevelDdlb;
    @UiField("customerFilterDdlb")
    protected CustomMenuBar customerFilterDdlb;
    @UiField("productFilterDdlb")
    protected CustomMenuBar productFilterDdlb;
    @UiField("salesInclusionDdlb")
    protected CustomMenuBar salesInclusionDdlb;
    @UiField("displayFormatDdlb")
    protected CustomMenuBar displayFormatDdlb;
    @UiField("productlevelDdlb")
    protected ComboBox productlevelDdlb;
    @UiField("unitOfMeasureDdlb")
    protected ComboBox unitOfMeasureDdlb;
    @UiField("unitOfMeasure")
    protected Label unitOfMeasure;
    @UiField("conversionFactorDdlb")
    protected ComboBox conversionFactorDdlb;

    protected CustomMenuBar.CustomMenuItem salesInclusionValues;
    protected CustomMenuBar.CustomMenuItem customerFilterValues;
    protected CustomMenuBar.CustomMenuItem productFilterValues;
    protected CustomMenuBar.CustomMenuItem displayFormatValues;

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
    protected Label viewLabel;
    @UiField("channelLayout")
    protected VerticalLayout channelLayout;
    @UiField("calculationPanel")
    private GridLayout calculationPanel;
    @UiField("altHistoryBtn")
    protected Button altHistoryBtn;
    @UiField("alternateHistoryPanel")
    protected Panel alternateHistoryPanel;
    @UiField("buttonLayout")
    protected HorizontalLayout buttonLayout;

    protected Button returnsResetBtn = new Button("RESET");
    @UiField("populateLevel")
    protected ComboBox populateLevel;
    @UiField("populateLabel")
    protected Label populateLabel;
    protected int customId = 0;
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
    protected final BeanItemContainer<String> groupBean = new BeanItemContainer<>(String.class);
    protected final BeanItemContainer<String> massGroupBean = new BeanItemContainer<>(String.class);
    protected SessionDTO session;
    protected FreezePagedTreeTable resultsTable;
    protected MSalesProjectionTableLogic mSalesProjectionTableLogic;
    protected ExtCustomTreeTable excelTable = new ExtCustomTreeTable();
    protected ExtTreeContainer<SalesRowDto> excelContainer = new ExtTreeContainer<>(SalesRowDto.class, ExtContainer.DataStructureMode.MAP);
    protected AlternateHistoryLogic logic = new AlternateHistoryLogic();
    protected int uncheckRecordCount;

    public static final String SELECT_ALL_LABEL = "Select All";

    /**
     * Level Filter Listener
     */
    protected Property.ValueChangeListener levelFilterChangeOption = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                projectionDTO.setGroup(StringUtils.EMPTY);
            }
            if (levelFilter.getValue() != null) {
                level.setValue(null);
            }

            levelFilterDdlbChangeOption();
        }
    };
    protected Property.ValueChangeListener groupFilterValueChangeListener = null;
    protected Property.ValueChangeListener baseLineFilterValueChangeListener = null;
    protected Property.ValueChangeListener methodologyFilterValueChangeListener = null;
    /**
     * Refresh Button
     */
    protected ComboBox userGroupTest = new ComboBox();
    protected ComboBox metohdologyFilter = new ComboBox();
    protected ComboBox baseLineFilter = new ComboBox();
    protected final BeanItemContainer<String> methdologyBean = new BeanItemContainer<>(String.class);
    protected final BeanItemContainer<String> baseLineBean = new BeanItemContainer<>(String.class);
    protected String screenName;
    @UiField("mainPanel")
    protected Panel mainPanel;
    @UiField("channelMassUpdatePanel")
    protected Panel channelMassUpdatePanel;
    @UiField("salesProjectionSelection")
    protected Panel salesProjectionSelection;
    @UiField("channelSalesAllocationSelection")
    protected Panel channelSalesAllocationSelection;
    @UiField("totalLivesLayout")
    protected HorizontalLayout totalLivesLayout;
    @UiField("viewLayout")
    protected HorizontalLayout viewLayout;
    @UiField("level")
    protected OptionGroup levelOption;
    @UiField("channelView")
    protected OptionGroup channelView;
    @UiField("spAdjustment")
    protected Panel spAdjustment;
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
    protected DataFormatConverter unitFormat = new DataFormatConverter("#,##0.0");
    private static final String SALES_DECIMAL_FORMAT = "#,##0";
    protected DataFormatConverter unitConversionFormat = new DataFormatConverter(SALES_DECIMAL_FORMAT);
    protected DataFormatConverter salesFormat = new DataFormatConverter(SALES_DECIMAL_FORMAT, DataFormatConverter.INDICATOR_DOLLAR);
    protected DataFormatConverter salesConversionFormat = new DataFormatConverter(SALES_DECIMAL_FORMAT, DataFormatConverter.INDICATOR_DOLLAR);
    protected DataFormatConverter salesConversionTwoDecimalFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_DOLLAR);
    protected DataFormatConverter growthFormat = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_PERCENT);
    protected DataFormatConverter rpuFormat = new DataFormatConverter("0.00", DataFormatConverter.INDICATOR_DOLLAR);
    protected String oldValue = StringUtils.EMPTY;
    protected String oldGroupValue = StringUtils.EMPTY;
    protected final Set<String> tableHirarechyNos = new HashSet<>();
    private Map<Object, Boolean> checkBoxMap = new HashMap<>();
    private Map<Object, String> radioMap = new HashMap<>();
    protected boolean isSalesCalculated;
    protected List<String> checkedList;
    protected Map<String, Map<String, List<String>>> tripleHeaderForCheckedDoubleHeader = new HashMap<>();
    protected List<Object> checkedDiscountsPropertyIds = new ArrayList<>();
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
    protected Label labelVariables;
    @UiField("projPeriodOrdr")
    protected Label projPeriodOrdr;
    @UiField("ForecastHorizonyalLayout")
    protected HorizontalLayout ForecastHorizonyalLayout;
    protected HorizontalLayout forecastReturnsLayout = new HorizontalLayout();
    @UiField("Allocation")
    protected Label Allocation;
    @UiField("forecastSPeriod")
    protected Label forecastSPeriod;
    @UiField("forecastEPeriod")
    protected Label forecastEPeriod;
    protected boolean returnsFlag = false;
    protected static ResourceBundle alertMsg = ResourceBundle.getBundle("properties.alertmessage");
    protected CommonLogic commonLogic = new CommonLogic();
    protected List<String> generateProductToBeLoaded = new ArrayList<>();
    public static final String SALES_TAB = "Sales";
    protected List<String> generateCustomerToBeLoaded = new ArrayList<>();

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
            projectionDTO.setTabName(Constant.SALES_PROJECTION_LABEL);
            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED) || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
                projectionDTO.setScreenName(screenName);
                mSalesProjectionTableLogic = new MSalesProjectionTableLogic();
                resultsTable = new FreezePagedTreeTable(mSalesProjectionTableLogic);
            }
            projectionDTO.setSessionDTO(session);
            addComponent();
            configurefields();
            Utility.loadHierarchyList(session);
            enableDisableFields();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     *
     * Configurefields.
     */
    private void configurefields() {
        level.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        level.setImmediate(true);
        levelFilter.addStyleName(Constant.POPUPCONTENTCOMBOSIZE);
        levelFilter.setImmediate(true);
        viewDdlb.setEnabled(false);
        editBtn.setEnabled(false);
        newBtn.setEnabled(true);
        alternateHistoryPanel.setVisible(false);
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
        variable.addItem(Constant.ACTUAL);
        variable.select(Constant.SALES_SMALL);
        variable.setStyleName(Constant.HORIZONTAL);

        adjustPeriods.addItem(Constant.ALL);
        adjustPeriods.addItem(Constants.ButtonConstants.SELECT.getConstant());
        adjustPeriods.select(Constant.ALL);
        adjustPeriods.setStyleName(Constant.HORIZONTAL);
        adjustment.setStyleName(Constant.TXT_RIGHT_ALIGN);

        graphIcon.setStyleName(Reindeer.BUTTON_LINK);

        view.addItem(Constant.CUSTOMER_SMALL);
        view.addItem(Constant.PRODUCT_LABEL);
        view.addItem(Constant.CUSTOM_LABEL);
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
            fieldDdlb.setEnabled(true);
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
            labelVariables.setVisible(false);
            actualsProjections.setVisible(true);
            GridLayoutProjection.replaceComponent(variables, proPeriodOrd);
            GridLayoutProjection.replaceComponent(labelVariables, projPeriodOrdr);
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
            massUpdate.setVisible(false);
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
            fieldDdlb.addItem(Constant.SALES_SMALL);
            fieldDdlb.addItem(Constant.UNIT_VOLUME);
            fieldDdlb.addItem(Constant.ACCOUNT_GROWTH);
            fieldDdlb.addItem(Constant.PRODUCT_GROWTH);
            valueDdlb.setVisible(false);
            valueDdlb.addItem(Constant.SELECT_ONE);
            valueDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
            valueDdlb.setTextInputAllowed(true);

            valueTxt.setStyleName(Constant.TXT_RIGHT_ALIGN);
            valueTxt.setVisible(true);
            loadMethodologyDdlb(methodology);

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
            valueDdlb.setEnabled(true);
            valueTxt.setEnabled(true);
            startPeriod.setEnabled(true);
            endPeriod.setEnabled(true);
            populate.setEnabled(true);

        }

        configureFrequency(nmFrequencyDdlb, historyDdlb);
        loadFrequency(nmFrequencyDdlb, historyDdlb);

        refreshBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getTableLogic().setRefresh(false);
                refreshTableData(getCheckedRecordsHierarchyNo());
                getTableLogic().setRefresh(true);
                final Notification notif = new Notification("Calculation Complete", Notification.Type.HUMANIZED_MESSAGE);
                notif.setPosition(Position.TOP_CENTER);
                notif.setStyleName(ConstantsUtils.MY_STYLE);
                notif.show(Page.getCurrent());

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
        CommonUtil.getInstance().loadOnDemandCombobox(allocMethodology, "ADJUSTMENT_METHODOLOGIES");

        allocationBasis.addItem(Constant.SELECT_ONE);
        allocationBasis.setNullSelectionItemId(Constant.SELECT_ONE);
        loadAllocationDdlb(allocationBasis);

        allocationBasis.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                if ((Constant.LabelConstants.PERC_OF_EX_FACTORY.getConstant()).equals(allocationBasis.getValue())) {
                    altHistoryBtn.setEnabled(true);
                } else if ((Constant.PERCOFDEMAND).equals(allocationBasis.getValue())
                        || (Constant.PERCOFINVENTORYWITHDRAWAL).equals(allocationBasis.getValue())
                        || (Constant.SELECT_ONE.equals(allocationBasis.getValue()))
                        || (allocationBasis.getValue() == null)) {

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
                LOGGER.debug("groupDdlbChangeOption ValueChangeEvent initiated ");
                String groupValue = String.valueOf(event.getProperty().getValue());
                groupValue = Constant.SHOW_ALL_GROUPS.equals(groupValue) ? Constant.PERCENT : groupValue;
                projectionDTO.setGroup(groupValue);
                projectionDTO.setGroupFilter(groupValue);
                if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                    ((NMSalesProjectionTableLogic) getTableLogic()).setProjectionResultsData(projectionDTO);
                } else {
                    mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
                }

                LOGGER.debug("groupDdlbChangeOption ValueChangeEvent ends ");
            }
        };

        baseLineFilterValueChangeListener = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                return;
            }
        };

        methodologyFilterValueChangeListener = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                return;
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

        variable.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                variableChangeLogic();
            }
        });

        altHistoryBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {

                try {

                    if (validateForAlternateHistory()) {
                        AlternateHistory alternateContractLookup;
                        session.setForecastName(Constant.SALES_PROJECTION_LABEL);
                        session.setFrequency(projectionDTO.getFrequency());
                        alternateContractLookup = new AlternateHistory(session, projectionDTO.getVariableList());

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

                } catch (IllegalArgumentException | NullPointerException ex) {
                    java.util.logging.Logger.getLogger(ForecastSalesProjection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        if (!CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName)) {
            loadLevelFilterValue(returnsFlag ? PRODUCT_HIERARCHY.getConstant() : String.valueOf(view.getValue()));
        }
        view.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                loadOnViewChange();
            }
        });

    }

    @UiHandler("newBtn")
    public void newHierarchyBtn(Button.ClickEvent event) {
        session.setTabName(SALES_TAB);
        customTreeViewLogic();
    }

    /**
     * edit the hierarchy btn.
     *
     * @param event the event
     */
    @UiHandler("editBtn")
    public void editHierarchyBtn(Button.ClickEvent event) {
        session.setTabName(SALES_TAB);
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
        LOGGER.debug("Entering Total Lives Handler");
        TotalLivesChart totalLivesChart = new TotalLivesChart(session);
        getUI().addWindow(totalLivesChart);
        LOGGER.debug("Ending Total Lives Handler");
    }

    /**
     * Reset btn.
     *
     * @param event the event
     */
    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                return;
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                LOGGER.debug("Entering resetBtn method");
                resetBtnLogic();
                LOGGER.debug("End of resetBtn method");
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
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent initiated ");
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            projectionDTO.setGroup(StringUtils.EMPTY);
        }
        customDdlbChangeOption();
        LOGGER.debug("customDdlbChangeOption ValueChangeEvent ends ");
    }

    public int getTabNumber() {
        return Constant.ONE;
    }

    @UiHandler("excelExport")
    public void excelExportListener(Button.ClickEvent event) {
        projectionDTO.setExcel(Boolean.TRUE);
        excelExportLogic();
        projectionDTO.setExcel(Boolean.FALSE);
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
        LOGGER.debug("fieldDdlb value change listener starts");
        fieldDdlbLogic();
        LOGGER.debug("fieldDdlb value change listener ends");
    }

    private void addComponent() {

        spAdjustment.setCaption(Constant.SALES_PROJECTION_LABEL);
        channelSalesAllocationSelection.setVisible(false);
        proPeriodOrd.addItem(ASCENDING.getConstant());
        proPeriodOrd.addItem(Constant.DESCENDING);
        proPeriodOrd.select(ASCENDING.getConstant());
        proPeriodOrd.setStyleName(Constant.HORIZONTAL);
        CommonUtils.frequenceValueChange(QUARTERLY.getConstant(), historyDdlb, session);
        historyDdlb.setValue(NumericConstants.ONE);

    }

    protected void initializeResultTable() {
        resultsTable.markAsDirty();
        resultsTable.setDoubleHeaderVisible(true);
        resultsTable.setSelectable(false);
        resultsTable.setImmediate(true);
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            resultsTable.setSplitPosition(NumericConstants.EIGHT_HUNDRED, Sizeable.Unit.PIXELS);
            resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
            resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        } else {
            resultsTable.setSplitPosition(NumericConstants.SIX_HUNDRED, Sizeable.Unit.PIXELS);
            resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
            resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        }
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
            //FOr Mandated Already loaded in Custom View Value Change Listener
            if (!screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                currentHierarchy = CommonLogic.getCustomTree(customId);
            }
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

    protected abstract void variableChangeLogic();

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
        Map<String, String> selection = new HashMap<>();
        selection.put(Constant.FREQUENCY, QUARTERLY.getConstant());
        selection.put(Constant.HISTORY, history);
        if (session.getForecastDTO().getForecastEndDate().after(session.getForecastDTO().getProjectionEndDate())) {
            selection.put("projectFrequency", String.valueOf(CommonUtils.getProjections(new Date(), session.getForecastDTO().getForecastEndDate(), QUARTERLY.getConstant())));
        } else {
            selection.put("projectFrequency", String.valueOf(CommonUtils.getProjections(new Date(), session.getForecastDTO().getProjectionEndDate(), QUARTERLY.getConstant())));
        }
        selection.put(Constant.ORDER, projectionPeriodorder);
        selection.put("actualsorprojections", actualsOrProjections);
        selection.put(Constant.SALES, Constant.FALSE);
        selection.put(Constant.UNITS, Constant.FALSE);
        selection.put(Constant.P_GROWTH, Constant.FALSE);
        selection.put(Constant.A_GROWTH, Constant.FALSE);

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
                        selection.put(Constant.P_GROWTH, Constant.TRUE);
                        break;
                    case Constant.PRODUCT_GROWTH:
                        selection.put(Constant.A_GROWTH, Constant.TRUE);
                        break;
                    default:
                        LOGGER.warn("value is not valid: " + value);
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
        LOGGER.debug("viewOptionGroup value change listener starts");
        salesLogic.resetChkbox(projectionDTO, false);
        getTableLogic().setRefresh(false);
        leftTable.setColumnCheckBox(Constant.CHECK, true, false);
        leftTable.setColumnCheckBoxDisable(Constant.CHECK, ACTION_VIEW.getConstant().equals(session.getAction()));
        projectionDTO.setHierarchyNo(StringUtils.EMPTY);
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            projectionDTO.setGroup(StringUtils.EMPTY);
            if ((PRODUCT.getConstant()).equals(view.getValue())) {
                resultsTable.setSplitPosition(NumericConstants.SEVEN_HUNDRED, Sizeable.Unit.PIXELS);
                resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
                resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
            } else if ((Constant.CUSTOM_LABEL).equals(view.getValue()) || (Constant.CUSTOMER_SMALL).equals(view.getValue())) {
                resultsTable.setSplitPosition(NumericConstants.EIGHT_HUNDRED, Sizeable.Unit.PIXELS);
                resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
                resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
            }
        } else if ((PRODUCT.getConstant()).equals(view.getValue())) {
            resultsTable.setSplitPosition(NumericConstants.SIX_HUNDRED, Sizeable.Unit.PIXELS);
            resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
            resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        } else if ((Constant.CUSTOM_LABEL).equals(view.getValue()) || (Constant.CUSTOMER_SMALL).equals(view.getValue())) {
            resultsTable.setSplitPosition(NumericConstants.SIX_HUNDRED, Sizeable.Unit.PIXELS);
            resultsTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
            resultsTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
        }
        if ((PRODUCT.getConstant()).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, true);
        } else if ((Constant.CUSTOM_LABEL).equals(view.getValue())) {
            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, false);
        } else if ((Constant.CUSTOMER_SMALL).equals(view.getValue())) {

            leftTable.setColumnCollapsingAllowed(true);
            leftTable.setColumnCollapsed(Constant.GROUP, false);
        }
        if ((Constant.CUSTOM_LABEL).equals(view.getValue())) {
            newBtn.setEnabled(!ACTION_VIEW.getConstant().equals(session.getAction()));
            viewDdlb.setEnabled(!ACTION_VIEW.getConstant().equals(session.getAction()));
            projectionDTO.setIsCustomHierarchy(true);
            projectionDTO.setHierarchyIndicator(Constant.CUSTOM_LABEL);
            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName)) {
                if (!session.getCustomHierarchyMap().isEmpty() && customId != 0) {
                    Utility.loadLevelValue(level, null, null, session.getCustomHierarchyMap().get(customId), Constant.CUSTOM_LABEL);
                } else {
                    CommonUtils.resetDdlb(level);
                    CommonUtils.resetDdlb(levelFilter);
                }
            } else {
                loadLevelFilterValue(String.valueOf(view.getValue()));
                loadLevelAndFilterValue();
            }
            level.setValue(SELECT_ONE);
            level.setEnabled(true);
            levelFilter.setValue(SELECT_ONE);
            levelFilter.setEnabled(false);
            loadCustomDDLB();
            viewDdlb.setValue(SELECT_ONE);
            getTableLogic().clearAll();
        } else if ((PRODUCT.getConstant()).equals(view.getValue())) {
            projectionDTO.setIsCustomHierarchy(false);
            projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            projectionDTO.setView(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            newBtn.setEnabled(true);
            editBtn.setEnabled(false);
            viewDdlb.setEnabled(false);
            viewDdlb.setValue(SELECT_ONE);
            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName)) {
                Utility.loadLevelValue(level, levelFilter, null, session.getProductHierarchyList(), PRODUCT.getConstant());
            } else {
                loadLevelFilterValue(String.valueOf(view.getValue()));
                loadLevelAndFilterValue();
            }
            level.setValue(SELECT_ONE);
            level.setEnabled(true);
            levelFilter.setValue(SELECT_ONE);
            levelFilter.setEnabled(true);
            projectionDTO.setCustomFlag(false);
            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                ((NMSalesProjectionTableLogic) getTableLogic()).setProjectionResultsData(projectionDTO);
            } else {
                mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
            }
        } else {
            projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            projectionDTO.setIsCustomHierarchy(false);
            projectionDTO.setView(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
            newBtn.setEnabled(true);
            editBtn.setEnabled(false);
            viewDdlb.setEnabled(false);
            viewDdlb.setValue(SELECT_ONE);
            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName)) {
                Utility.loadLevelValue(level, levelFilter, null, session.getCustomerHierarchyList(), CUSTOMER.getConstant());
            } else {
                loadLevelFilterValue(String.valueOf(view.getValue()));
                loadLevelAndFilterValue();
            }
            level.setValue(SELECT_ONE);
            level.setEnabled(true);
            levelFilter.setValue(SELECT_ONE);
            levelFilter.setEnabled(true);
            projectionDTO.setCustomFlag(false);
            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                ((NMSalesProjectionTableLogic) getTableLogic()).setProjectionResultsData(projectionDTO);
            } else {
                mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
            }
        }
        getTableLogic().setRefresh(true);
        LOGGER.debug("viewOptionGroup value change listener ends");
    }

    /**
     * Loads the previously created Custom Hierarchy for a particular projection
     * in the Custom DDLB.
     */
    protected void loadCustomDDLB() {
        LOGGER.debug("loadCustomDDLB initiated " + customIdToSelect);
        viewDdlb.setEnabled(true);
        newBtn.setEnabled(true);
        editBtn.setEnabled(false);
        if (session.getCustomerViewList().isEmpty()) {
            customViewList = CommonLogic.getCustomViewList(session.getProjectionId());
            session.setCustomerViewList(customViewList);
        } else {
            customViewList = session.getCustomerViewList();
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
                viewDdlb.select(customIdToSelect);
            } else {
                viewDdlb.setValue(SELECT_ONE);
            }
        }
        customId = customIdToSelect;
        session.setCustomId(customId);
        LOGGER.debug("loadCustomDDLB ends ");
    }

    /**
     * Opens the CustomTreePopup to create a new Custom Hierarchy
     */
    protected void customTreeViewLogic() {

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

    /**
     * Opens the CustomTreePopup to edit the existing custom hierarchy.
     */
    public void editHierarchyLogic() {
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

    /**
     * Configures the field factory for the tables used Mandated and
     * Non-Mandated.
     */
    protected void configureTableFieldFactory() {

        final ExtCustomTable.ColumnCheckListener checkListener = new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                try {
                    checkAll = event.isChecked();
                    if (returnsFlag) {
                        salesLogic.headerCheckALLQuery(session, checkAll ? 1 : 0, true);
                    }
                    checkClearAll(event.isChecked());
                } catch (PortalException | SystemException ex) {
                    LOGGER.error(ex);
                }
            }
        };

        leftTable = resultsTable.getLeftFreezeAsTable();
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            NonMandatedFilter();
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionDTO.getScreenName())) {
            MandatedFilter();
        }
        if ((!returnsFlag) && (leftHeader.getDoubleColumns() != null)) {
            leftTable.setDoubleHeaderVisible(false);
            leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
            leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
        }
        UiUtils.setExtFilterTreeTableColumnWidth(rightTable, NumericConstants.ONE_FOUR_FIVE, TAB_SALES_PROJECTION.getConstant());

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
                    check.setImmediate(true);
                    check.setEnabled(!ACTION_VIEW.getConstant().equals(session.getAction()));
                    check.addClickListener(new ExtCustomCheckBox.ClickListener() {
                        @Override
                        public void click(ExtCustomCheckBox.ClickEvent event) {
                            try {
                                SalesRowDto checkDTO = (SalesRowDto) itemId;
                                Boolean checkValue = check.getValue();
                                getTableLogic().setRefresh(false);
                                final String tableHierarchyNo = getTableLogic().getTreeLevelonCurrentPage(itemId);
                                checkDTO.addBooleanProperties(propertyId, checkValue);
                                int updatedRecordsNo = 0;
                                if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(screenName)
                                        || CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equalsIgnoreCase(screenName)) {
                                    updatedRecordsNo = salesLogic.updateCheckedRecords(projectionDTO, getBeanFromId(itemId), checkValue, false);
                                } else {
                                    updatedRecordsNo = salesLogic.queryToUpdateCheckRecord(session, (checkValue ? 1 : 0), true, checkDTO.getReturnDetailsSid());
                                }

                                updateCheckForParentLevels(itemId, updatedRecordsNo, checkValue);
                                updateCheckForChildLevels(tableHierarchyNo, itemId, checkValue);

                                if (!checkValue) {
                                    ExtPagedTreeTable lefttreeTable = resultsTable.getLeftFreezeAsTable();
                                    lefttreeTable.removeColumnCheckListener(checkListener);
                                    lefttreeTable.setColumnCheckBox(Constant.CHECK, true, false);
                                    lefttreeTable.addColumnCheckListener(checkListener);
                                    checkAll = false;
                                }
                                if (returnsFlag == true) {
                                    List<Integer> listStr = salesLogic.headerCheckALLQuery(session, 0, false);
                                    leftTable.setColumnCheckBox(Constant.CHECK, true, listStr.size() != 1 ? false : (String.valueOf(listStr.get(0)) == "true"));
                                    resultsTable.getLeftFreezeAsTable().setRefresh(false);
                                }
                                resultsTable.getLeftFreezeAsTable().setRefresh(true);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex);
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
                        textField.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
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
                                        getTableLogic().getContainerDataSource().getContainerProperty(itemId, Constant.GROUP).setValue(String.valueOf(((TextField) event.getComponent()).getValue()));
                                        dto.addStringProperties(Constant.GROUP, String.valueOf(((TextField) event.getComponent()).getValue()));
                                        salesLogic.saveSalesGroup(projectionDTO, dto.getHierarchyNo(), newValue);
                                        groupBean.removeAllItems();
                                        groupBean.addBean(Constant.SHOW_ALL_GROUPS);
                                        GroupFilter.initSalesMap(session);
                                        groupBean.addAll(session.getSalesgroupSet());
                                    } catch (PortalException | SystemException | Property.ReadOnlyException ex) {
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
                        && !String.valueOf(propertyId).contains("ActualReturned") && !String.valueOf(propertyId).contains(Constant.ACTUALRPU)) {
                    final TextField textField = new TextField();
                    final SalesRowDto salesRowDto = getBeanFromId(itemId);
                    if (salesRowDto.getSalesInclusion().isEmpty()) {
                        return null;
                    }
                    textField.setData(propertyId + "~" + salesRowDto.getHierarchyNo());
                    textField.setImmediate(true);
                    textField.addStyleName(Constant.TXT_RIGHT_ALIGN);
                    textField.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);
                    textField.setEnabled(true);
                    if (String.valueOf(propertyId).contains(Constant.SALES_SMALL)) {
                        textField.setConverter(getConverter(Constant.SALES_SMALL));
                    } else if (String.valueOf(propertyId).contains(Constant.UNITS_SMALL)) {
                        textField.setConverter(getConverter(Constant.UNITS_SMALL));
                    } else if (String.valueOf(propertyId).contains(Constant.GROWTH)) {
                        textField.setConverter(growthFormat);
                    } else if (String.valueOf(propertyId).contains("Return%") && returnsFlag) {
                        textField.setConverter(growthFormat);
                    } else if (String.valueOf(propertyId).contains("RPU") && returnsFlag) {
                        textField.setConverter(rpuFormat);
                    } else if (String.valueOf(propertyId).contains("ReturnAmount") && returnsFlag) {
                        textField.setConverter(salesFormat);
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
                                    Double newNumber;
                                    Double oldNumber;
                                    newNumber = StringUtils.EMPTY.equals(newValue) || Constant.NULL.equals(newValue) ? 0.0 : Double.valueOf(newValue);
                                    oldNumber = StringUtils.EMPTY.equals(oldValue) || Constant.NULL.equals(oldValue) ? 0.0 : Double.valueOf(oldValue);
                                    if (String.valueOf(propertyId).contains(Constant.SALES_SMALL)) {
                                        newNumber = CommonUtil.getConversionFormattedMultipleValue(projectionDTO, newNumber);
                                        oldNumber = CommonUtil.getConversionFormattedMultipleValue(projectionDTO, oldNumber);
                                    }
                                    Double incOrDec = ((newNumber - oldNumber) / oldNumber) * NumericConstants.HUNDRED;
                                    String tempValue = String.valueOf(((TextField) event.getComponent()).getData());
                                    String tempArray[] = tempValue.split("-");
                                    tempValue = projectionDTO.getFrequencyDivision() == 1 ? tempArray[1] : tempArray[NumericConstants.TWO];
                                    String tempArray1[] = tempValue.split("~");
                                    String changedProperty = tempArray1[0];

                                    String changedValue = ((TextField) event.getComponent()).getValue();
                                    changedValue = StringUtils.isBlank(changedValue) || Constant.NULL.equals(changedValue) ? "0.0" : changedValue;
                                    if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equalsIgnoreCase(screenName)) {
                                        salesLogic.saveEditedRecsReturns(propertyId.toString(), changedValue, incOrDec, salesRowDto, projectionDTO);
                                    } else if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(screenName)) {
                                        salesLogic.saveRecords(propertyId.toString(), changedValue, incOrDec, changedProperty, salesRowDto, projectionDTO, checkAll, !tempArray1[0].contains(Constant.GROWTH));
                                    } else {
                                        salesLogic.saveEditedRecs(propertyId.toString(), changedValue, incOrDec, changedProperty, salesRowDto, projectionDTO, checkAll, !tempArray1[0].contains(Constant.GROWTH));
                                    }
                                    salesRowDto.addStringProperties(propertyId, newValue);
                                    tableHirarechyNos.add(getTableLogic().getTreeLevelonCurrentPage(itemId));
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
                    } else if (radioMap.containsKey(event.getPropertyId())) {
                        radioMap.remove(event.getPropertyId());
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
    public void checkClearAll(boolean checkClear) throws PortalException, SystemException {
        try {
            getTableLogic().setRefresh(false);
            if (returnsFlag) {
                salesLogic.resetChkboxForReturns(projectionDTO, checkAll);
            } else if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(screenName)) {
                salesLogic.updateCheckedRecords(projectionDTO, null, checkClear, true);
            } else {
                salesLogic.resetChkbox(projectionDTO, checkAll);
            }
            for (Object itemID : leftTable.getItemIds()) {
                SalesRowDto dto = (SalesRowDto) itemID;
                dto.addBooleanProperties(Constant.CHECK, checkClear);
                leftTable.getContainerProperty(itemID, Constant.CHECK).setValue(checkClear);
            }

            for (String hierarchyNo : getTableLogic().getAllLevels()) {
                boolean isPresentInContainer = true;
                Object tempId = getTableLogic().getcurrentTreeData(hierarchyNo);
                if (tempId == null) {
                    isPresentInContainer = false;
                    if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                        ((NMSalesProjectionTableLogic) getTableLogic()).getExpandedTreeValues(hierarchyNo);
                    } else {
                        mSalesProjectionTableLogic.getExpandedTreeValues(hierarchyNo);
                    }
                }
                if (tempId != null) {
                    SalesRowDto tempDto = (SalesRowDto) tempId;
                    tempDto.setUncheckCount(checkClear ? 0 : Integer.valueOf(tempDto.getCcpCount()));
                    updateChecks(tempId, isPresentInContainer);
                }
            }
            getTableLogic().setRefresh(true);

        } catch (Property.ReadOnlyException | NumberFormatException ex) {
            LOGGER.error(ex);
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
            getTableLogic().getContainerDataSource().getContainerProperty(tempId, Constant.CHECK).setValue(checkValue);
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
        } else if (newRecordsCount < dto.getUncheckCount()) {
            newRecordsCount = dto.getUncheckCount();
        }

        List<String> hierarchyNos = getTableLogic().getAllParentLevels(itemId);

        for (String hierarchyNo : hierarchyNos) {
            Object tempId = getTableLogic().getcurrentTreeData(hierarchyNo);
            if (tempId == null) {
                if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                    ((NMSalesProjectionTableLogic) getTableLogic()).getExpandedTreeValues(hierarchyNo);
                } else {
                    mSalesProjectionTableLogic.getExpandedTreeValues(hierarchyNo);
                }
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

        List<String> childTableHierarchyNos = getTableLogic().getAllChildLevels(itemId);
        childTableHierarchyNos.add(tableHierarchyNo);
        for (String hierarchyNo : childTableHierarchyNos) {
            Object tempId = getTableLogic().getcurrentTreeData(hierarchyNo);
            if (tempId == null) {
                if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                    ((NMSalesProjectionTableLogic) getTableLogic()).getExpandedTreeValues(hierarchyNo);
                } else {
                    mSalesProjectionTableLogic.getExpandedTreeValues(hierarchyNo);
                }
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
        for (String tableTreeLevelNo : getTableLogic().getAllLevels()) {
            Object itemId = getTableLogic().getcurrentTreeData(tableTreeLevelNo);
            if (itemId == null) {
                if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                    ((NMSalesProjectionTableLogic) getTableLogic()).getExpandedTreeValues(tableTreeLevelNo);
                } else {
                    mSalesProjectionTableLogic.getExpandedTreeValues(tableTreeLevelNo);
                }
            }
            if (itemId != null) {
                int uncheckCount = ((SalesRowDto) itemId).getUncheckCount();
                uncheckRecordCount += uncheckCount;
                finalHirarechyNo.add(tableTreeLevelNo);
            }
        }
        finalHirarechyNo.addAll(getManualEntryRefreshHiearachyNo());
        return finalHirarechyNo;
    }

    public Set<String> getReturnsCheckedRecordsHierarchyNo() {
        Set<String> finalHirarechyNo = new HashSet<>();
        for (String tableTreeLevelNo : getTableLogic().getAllLevels()) {
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
                finalHirarechyNo.addAll(getTableLogic().getAllParentLevels(hirarechyNo));
                finalHirarechyNo.addAll(getTableLogic().getAllChildLevels(hirarechyNo));
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

        try {
            boolean isAtleastChkd = false;
            Collection<?> salesList = resultsTable.getLeftFreezeAsTable().getItemIds();
            if (!salesList.isEmpty()) {
                for (Object item : salesList) {
                    if ((Boolean) getTableLogic().getContainerDataSource().getContainerProperty(item, Constant.CHECK).getValue()) {
                        isAtleastChkd = true;
                        break;
                    }
                }
            }
            if (startPeriod.getValue() != null && endPeriod.getValue() != null && !validateStartEndPeriods(String.valueOf(nmFrequencyDdlb.getValue()), String.valueOf(startPeriod.getValue()), String.valueOf(endPeriod.getValue()))) {
                AbstractNotificationUtils.getErrorNotification(Constant.ERROR, "The Start Period must be before the End Period.Please try again");
                return;
            }
            if (fieldDdlb.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification("No Field Selected", "Please select any Field to update");
            } else if (fieldDdlb.getValue() != null && (Constant.ACCOUNT_GROWTH.equals(String.valueOf(fieldDdlb.getValue()))
                    || Constant.PRODUCT_GROWTH.equals(String.valueOf(fieldDdlb.getValue()))
                    || Constant.PROJECTED_RETURN_PER.equals(String.valueOf(fieldDdlb.getValue()))
                    || Constant.PROJECTED_RETURN_AMT.equals(String.valueOf(fieldDdlb.getValue()))
                    || Constant.PROJECTED_RPU.equals(String.valueOf(fieldDdlb.getValue()))
                    || Constant.GROWTH_RATE.equals(String.valueOf(fieldDdlb.getValue())) || Constant.SALES_CAPS.equals(String.valueOf(fieldDdlb.getValue()))
                    || Constant.UNIT_VOLUME.equals(String.valueOf(fieldDdlb.getValue()))) && startPeriod.getValue() == null) {

                if (returnsFlag) {
                    AbstractNotificationUtils.getErrorNotification("No Start Date Selected", "Please select a start date.");
                } else {
                    AbstractNotificationUtils.getErrorNotification("No Start Date Selected", "Please Select a Start Period.");
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
                    endQuater = rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1).charAt(1) - NumericConstants.FORTY_EIGHT;
                    temp = rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1);
                    length = temp.length();
                    endYear = Integer.valueOf(temp.substring(length - NumericConstants.FOUR, length));
                    if (projectionDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
                        String peroidValue = rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1);
                        String month = peroidValue.substring(0, peroidValue.length() - NumericConstants.FIVE);
                        endQuater = CommonUtils.getIntegerForMonth(month);
                    }
                } else {
                    endPeriodValue = endPeriod.getValue().toString();
                    forecastPeriodend = endPeriodValue;
                    endQuater = endPeriod.getValue().toString().charAt(1) - NumericConstants.FORTY_EIGHT;
                    endPeriod.getValue().toString().length();

                    length = endPeriod.getValue().toString().length();
                    endYear = Integer.valueOf(endPeriod.getValue().toString().substring(length - NumericConstants.FOUR, length));
                    if (projectionDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
                        String peroidValue = endPeriod.getValue().toString();
                        String month = peroidValue.substring(0, peroidValue.length() - NumericConstants.FIVE);
                        endQuater = CommonUtils.getIntegerForMonth(month);
                    }
                }
                if (startPeriod.getValue() == null) {
                    startQuater = rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1).charAt(1) - NumericConstants.FORTY_EIGHT;
                    temp = rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1);
                    length = temp.length();
                    startYear = Integer.valueOf(temp.substring(length - NumericConstants.FOUR, length));
                    if (projectionDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
                        String peroidValue = rightHeader.getDoubleHeaders().get(rightHeader.getDoubleHeaders().size() - 1);
                        String month = peroidValue.substring(0, peroidValue.length() - NumericConstants.FIVE);
                        startQuater = CommonUtils.getIntegerForMonth(month);
                    }
                } else {
                    startPeriodValue = String.valueOf(startPeriod.getValue());
                    startQuater = startPeriod.getValue().toString().charAt(1) - NumericConstants.FORTY_EIGHT;
                    length = startPeriod.getValue().toString().length();
                    startYear = Integer.valueOf(startPeriod.getValue().toString().substring(length - NumericConstants.FOUR, length));
                    if (projectionDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
                        String peroidValue = startPeriod.getValue().toString();
                        String month = peroidValue.substring(0, peroidValue.length() - NumericConstants.FIVE);
                        startQuater = CommonUtils.getIntegerForMonth(month);
                    }
                }
                boolean isUpdated = false;

                String enteredValue = Constant.GROUPFCAPS.equals(String.valueOf(fieldDdlb.getValue())) ? String.valueOf(valueDdlb.getValue()) : String.valueOf(valueTxt.getValue());

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
                } else if (Constant.ACCOUNT_GROWTH.equals(String.valueOf(fieldDdlb.getValue()))) {
                    updateVariable = Constant.ACCOUNT_GROWTH;
                } else if (Constant.PRODUCT_GROWTH.equals(String.valueOf(fieldDdlb.getValue()))) {
                    updateVariable = Constant.PRODUCT_GROWTH;
                } else if (Constant.SALES_SMALL.equals(String.valueOf(fieldDdlb.getValue()))) {
                    updateVariable = Constant.SALES_SMALL;
                } else if (Constant.UNIT_VOLUME.equals(String.valueOf(fieldDdlb.getValue()))) {
                    updateVariable = Constant.UNIT_VOLUME;
                } else if (Constant.GROUPFCAPS.equals(String.valueOf(fieldDdlb.getValue()))) {
                    updateVariable = Constant.GROUPFCAPS;
                } else {
                    updateVariable = StringUtils.EMPTY;
                }

                if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
                    Set<String> returnDetilsSidSet = new HashSet<>();
                    boolean levelFlag = false;
                    for (String tableTreeLevelNo : getTableLogic().getAllLevels()) {
                        Object itemId = getTableLogic().getcurrentTreeData(tableTreeLevelNo);
                        if (itemId == null) {
                            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                                ((NMSalesProjectionTableLogic) getTableLogic()).getExpandedTreeValues(tableTreeLevelNo);
                            } else {
                                mSalesProjectionTableLogic.getExpandedTreeValues(tableTreeLevelNo);
                            }
                        }
                        if (itemId != null) {
                            Map<String, Double> selectedValues = new TreeMap<>();
                            SalesRowDto dto = (SalesRowDto) itemId;
                            boolean levelNoFlag = false;
                            if (Constant.GROWTH_RATE.equals(String.valueOf(fieldDdlb.getValue()))) {
                                levelNoFlag = true;
                            } else if (populateLevel.getValue().toString().trim().matches("^[0-9]+$")) {
                                levelNoFlag = Integer.parseInt(String.valueOf(populateLevel.getValue())) == dto.getLevelNo();
                            } else {
                                levelNoFlag = Integer.parseInt((String.valueOf(populateLevel.getValue())).substring(0, 1)) == dto.getLevelNo();
                            }
                            if (dto.getPropertyValue(Constant.CHECK) != null && (Boolean) dto.getPropertyValue(Constant.CHECK) && levelNoFlag) {
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

                                                String oldAmtValue = String.valueOf(entry.getValue()).replace("$", StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
                                                Double newNumber = Double.valueOf(enteredValue);
                                                Double oldNumber = Double.valueOf(oldAmtValue);
                                                Double incOrDec = ((newNumber - oldNumber) / oldNumber) * NumericConstants.HUNDRED;
                                                selectedValues.put(StringUtils.EMPTY + periods[0], incOrDec);
                                            } else if (periods[0] >= startQuater
                                                    && periods[0] <= endQuater
                                                    && periods[1] >= startYear
                                                    && periods[1] <= endYear) {

                                                String oldProjAmtValue = String.valueOf(entry.getValue()).replace("$", StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
                                                Double newNumber = Double.valueOf(enteredValue);
                                                Double oldNumber = Double.valueOf(oldProjAmtValue);
                                                Double incOrDec = ((newNumber - oldNumber) / oldNumber) * NumericConstants.HUNDRED;
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
                        salesLogic.callRefreshProcedure(projectionDTO.getProjectionId(), Arrays.toString(returnDetilsSid).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY), startPeriod + "," + forecastPeriodend, String.valueOf(projectionDTO.getUserId()), projectionDTO.getSessionDTO().getSessionId(), true);
                        isUpdated = true;
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Selected Valid Record", "Please select a valid record");
                        return;
                    }
                } else {
                    Map<String, Object> inputParameters = loadInputParameters(startYear, endYear, startQuater, endQuater, enteredValue, updateVariable);
                    salesLogic.saveOnMassUpdate(projectionDTO, inputParameters);
                    isUpdated = true;
                    if (Constant.GROUPFCAPS.equals(String.valueOf(fieldDdlb.getValue()))) {
                        refreshGroupDdlb();
                    }
                }

                if (isUpdated) {
                    refreshTableData(getCheckedRecordsHierarchyNo());
                    startPeriod.select(startPeriodValue);
                    endPeriod.select(endPeriodValue);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    public Map<String, Object> loadInputParameters(int startYear, int endYear, int startQuater, int endQuater, String enteredValue, String updateVariable) {
        Map<String, Object> updateValues = new HashMap<>();
        updateValues.put("startYear", startYear);
        updateValues.put("endYear", endYear);
        updateValues.put("startQuarter", startQuater);
        updateValues.put("endQuarter", endQuater);
        updateValues.put("enteredValue", enteredValue);
        updateValues.put("updateVariable", updateVariable);
        return updateValues;
    }

    /**
     * Method contains the logic for the Table Refresh.
     *
     * @param finalHirarechyNo
     */
    public void refreshTableData(Set<String> finalHirarechyNo) {
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            CommonUtil.getInstance().waitsForOtherThreadsToComplete(session.getFutureValue(Constant.FILE_INSERT)[0]);
        }
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
            ((NMSalesProjectionTableLogic) getTableLogic()).forRefresh(finalHirarechyNo);
        } else {
            mSalesProjectionTableLogic.forRefresh(finalHirarechyNo);
        }
        getTableLogic().setCurrentPage(getTableLogic().getCurrentPage());
    }

    /**
     * Method contains the adjustment logic for Commercial and Government
     * Forecast.
     *
     * @param event
     */
    @UiHandler("adjust")
    public void adjustmentLogic(Button.ClickEvent event) {
        if (!checkForCheckedRecord()) {
            AbstractNotificationUtils.getErrorNotification("No Hierarchy level selected", "Please select a level in the hierarchy.");
            return;
        }
        for (Object propertyId : checkedDiscountsPropertyIds) {
            String tripleHeader = resultsTable.getRightFreezeAsTable().getTripleHeaderColumnHeader(propertyId);
            if (tripleHeaderForCheckedDoubleHeader.get(tripleHeader) == null) {
                NotificationUtils.getErrorNotification("No period selected", "Please select which periods need to be included in the adjustment.");
                return;
            }
        }

        if ((Constant.ACTUAL).equals(variable.getValue())) {
            final String adjValue = String.valueOf(adjustment.getValue());
            final String adjType = String.valueOf(type.getValue());
            final String adjBasis = String.valueOf(basis.getValue());
            final String adjVariable = Constant.A;
            final String adjPeriod = (String) adjustPeriods.getValue();
            final String adjMethodology = String.valueOf(allocMethodology.getValue());
            final String HISTORY_PERIODS = null;
            final String projectionPeriods;

            if (adjType.equals("Incremental") || adjType.equals("Override")) {
                if (adjBasis.equals("Percentage")) {
                    AbstractNotificationUtils.getErrorNotification("Incorrect Type&Basis entered",
                            "Please enter a correct adjustment type and adjustment basis ");
                    return;
                }
                if (adjBasis.equals("Amount") && adjType.equals("Incremental")) {
                    AbstractNotificationUtils.getErrorNotification("Incorrect Type&Basis entered",
                            "Please enter a correct adjustment type and adjustment basis ");
                    return;
                }
            }
            if (adjPeriod.equals(Constant.ALL)) {
                projectionPeriods = getAllProjectionPeriods();
            } else {
                projectionPeriods = getSelectedProjectionPeriods();
                if (String.valueOf(projectionPeriods).equals(StringUtils.EMPTY)) {
                    AbstractNotificationUtils.getErrorNotification(Constant.IMPROPER_CALCULATION_VARIABLES,
                            "Please select a Projection Period.");
                    return;
                }

            }

            String confirmMessage = "Confirm Actual variable Adjustment";
            String messageBody = Constant.YOU_ARE_ABOUT_TO_MAKE_THE_FOLLOWING + " adjustment for the following periods "
                    + projectionPeriods + Constant.ARE_YOU_SURE_YOU_WANT_TO_CONTINUE;

            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    return;
                }

                @Override
                public void yesMethod() {
                    try {
                        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                            CommonUtil.getInstance()
                                    .waitsForOtherThreadsToComplete(session.getFutureValue(Constant.FILE_INSERT)[0]);
                        }
                        getTableLogic().setRefresh(false);
                        salesLogic.adjustSalesProjection(projectionDTO, adjType, adjValue, adjBasis, adjVariable,
                                adjMethodology, HISTORY_PERIODS, projectionPeriods);
                        refreshTableData(getCheckedRecordsHierarchyNo());
                        getTableLogic().setRefresh(true);
                        session.setActualAdjustment(true);
                        session.setActualAdjustmentPeriods(projectionPeriods);

                    } catch (PortalException ex) {
                        LOGGER.error(ex);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }.getOkCancelMessage(confirmMessage, messageBody);

        } else {
            session.setActualAdjustment(false);
            if (!String.valueOf(adjustment.getValue()).matches(Constant.NUM_WIHT2DECIMAL) || adjustment.getValue().length() > NumericConstants.THIRTY_EIGHT) {
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

                    if (adjustPeriod.equals(Constant.ALL)) {
                        if (adjMethodology.equals(Constant.HISTORICAL_OF_BUSINESS)) {
                            historyPeriods = getSelectedHistoryPeriods();
                            if (StringUtils.EMPTY.equals(historyPeriods)) {
                                AbstractNotificationUtils.getErrorNotification(Constant.IMPROPER_CALCULATION_VARIABLES, "Please select a Historic Period");
                                return;
                            }
                            projectionPeriods = getAllProjectionPeriods();
                        } else {
                            historyPeriods = getAllProjectionPeriods();
                            projectionPeriods = getAllProjectionPeriods();
                        }
                    } else if (adjMethodology.equals(Constant.HISTORICAL_OF_BUSINESS)) {
                        historyPeriods = getSelectedHistoryPeriods();
                        if (String.valueOf(historyPeriods).equals(StringUtils.EMPTY)) {
                            AbstractNotificationUtils.getErrorNotification(Constant.IMPROPER_CALCULATION_VARIABLES, "Please select a Historic Period");
                            return;
                        }
                        projectionPeriods = getSelectedProjectionPeriods();
                        if (String.valueOf(projectionPeriods).equals(StringUtils.EMPTY)) {
                            AbstractNotificationUtils.getErrorNotification(Constant.IMPROPER_CALCULATION_VARIABLES, "Please select a Projection Period.");
                            return;
                        }
                    } else {
                        historyPeriods = getSelectedProjectionPeriods();
                        if (String.valueOf(historyPeriods).equals(StringUtils.EMPTY)) {
                            AbstractNotificationUtils.getErrorNotification(Constant.IMPROPER_CALCULATION_VARIABLES, "Please select a Projection Period.");
                            return;
                        }
                        projectionPeriods = getSelectedProjectionPeriods();
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
                                messageBody = Constant.YOU_ARE_ABOUT_TO_MAKE_THE_FOLLOWING + getFormatValue(Constant.UNIT_FORMAT, adjValue, StringUtils.EMPTY)
                                        + " adjustment for the following periods " + projectionPeriods + Constant.ARE_YOU_SURE_YOU_WANT_TO_CONTINUE;
                            } else {
                                messageBody = Constant.YOU_ARE_ABOUT_TO_MAKE_THE_FOLLOWING + getFormatValue(Constant.TWO_DECIMAL, adjValue, Constant.CURRENCY)
                                        + " adjustment for the following periods " + projectionPeriods + Constant.ARE_YOU_SURE_YOU_WANT_TO_CONTINUE;
                            }
                        } else {
                            messageBody = Constant.YOU_ARE_ABOUT_TO_MAKE_THE_FOLLOWING + adjValue
                                    + "% adjustment for the following periods " + projectionPeriods + Constant.ARE_YOU_SURE_YOU_WANT_TO_CONTINUE;
                        }
                    } else if (adjType.equals("Override")) {
                        confirmMessage = "Confirm Override";
                        if (basis.getValue().equals(Constant.LabelConstants.AMOUNT)) {
                            if (variable.getValue().equals(Constant.UNIT)) {
                                messageBody = Constant.REPLACE_THE_CURRENT_VALUES
                                        + getFormatValue(Constant.UNIT_FORMAT, adjValue, StringUtils.EMPTY) + ". \n Are you sure you want to continue?";
                            } else {
                                messageBody = Constant.REPLACE_THE_CURRENT_VALUES
                                        + getFormatValue(Constant.TWO_DECIMAL, adjValue, Constant.CURRENCY) + ". \n Are you sure you want to continue?";
                            }
                        } else {
                            messageBody = Constant.REPLACE_THE_CURRENT_VALUES
                                    + adjValue + "%. \n Are you sure you want to continue?";
                        }
                    }

                    new AbstractNotificationUtils() {
                        @Override
                        public void noMethod() {
                            return;
                        }

                        @Override
                        public void yesMethod() {
                            try {
                                if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                                    CommonUtil.getInstance().waitsForOtherThreadsToComplete(session.getFutureValue(Constant.FILE_INSERT)[0]);
                                }
                                getTableLogic().setRefresh(false);
                                salesLogic.adjustSalesProjection(projectionDTO, adjType, adjValue, adjBasis, adjVariable, adjMethodology, historyPeriods, projectionPeriods);
                                refreshTableData(getCheckedRecordsHierarchyNo());
                                getTableLogic().setRefresh(true);
                            } catch (PortalException ex) {
                                LOGGER.error(ex);
                            } catch (Exception ex) {
                                LOGGER.error(ex);
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
        int currentYear = (Integer) list.get(NumericConstants.TWO);
        int currentSemi = (Integer) list.get(NumericConstants.THREE);
        String selectedFreq = projectionDTO.getFrequency();
        for (Object key : checkBoxMap.keySet()) {
            String temp[] = ((String) key).split("-");
            int tempYear = Integer.parseInt(ANNUAL.equals(selectedFreq) ? temp[0] : temp[1]);
            int tempQuarter = QUARTERLY.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll(Constant.FROM_ZERO_TO_NINE, StringUtils.EMPTY)).trim()) : 0;
            int tempSemi = SEMI_ANNUAL.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll(Constant.FROM_ZERO_TO_NINE, StringUtils.EMPTY)).trim()) : 0;
            int tempMonth = MONTHLY.equals(selectedFreq) ? CommonUtils.getMonthNumber(temp[0].trim()) : 0;
            boolean condition = false;
            switch (selectedFreq) {
                case MONTHLY:
                    condition = tempYear < currentYear || (tempYear == currentYear && tempMonth < currentMonth);
                    break;
                case Constant.QUARTERLY:
                    condition = tempYear < currentYear || (tempYear == currentYear && tempQuarter < currentQuarter);
                    break;
                case Constant.SEMI_ANNUALY:
                    condition = tempYear < currentYear || (tempYear == currentYear && tempSemi < currentSemi);
                    break;
                case ANNUAL:
                    condition = tempYear < currentYear;
                    break;
                default:
                    LOGGER.warn("selectedFreq is not valid: " + selectedFreq);
                    break;
            }
            if ((condition) && (checkBoxMap.get(key))) {
                if (!selectedPeriods.equals(StringUtils.EMPTY)) {
                    selectedPeriods = selectedPeriods + ",";
                }
                String value = (String) key;
                value = MONTHLY.equals(selectedFreq) ? CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + tempMonth + " " + tempYear : value.replace('-', ' ');
                selectedPeriods = selectedPeriods + value;
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
        int projStartYear = currentDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO;
        String selectedFreq = projectionDTO.getFrequency();
        for (Object key : rightHeader.getDoubleColumns()) {
            if (!String.valueOf(key).equals(Constant.GROUP)) {
                String temp[] = ((String) key).split("-");
                int tempYear = Integer.parseInt(ANNUAL.equals(selectedFreq) ? temp[0] : temp[1]);
                int tempQuarter = QUARTERLY.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll(Constant.FROM_ZERO_TO_NINE, StringUtils.EMPTY)).trim()) : 0;
                int tempSemi = SEMI_ANNUAL.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll(Constant.FROM_ZERO_TO_NINE, StringUtils.EMPTY)).trim()) : 0;
                int tempMonth = MONTHLY.equals(selectedFreq) ? CommonUtils.getMonthNumber(temp[0].trim()) : 0;
                boolean condition = false;
                switch (selectedFreq) {
                    case MONTHLY:
                        condition = tempYear > projStartYear || (tempYear == projStartYear && tempMonth >= projStartMonth);
                        break;
                    case Constant.QUARTERLY:
                        condition = tempYear > projStartYear || (tempYear == projStartYear && tempQuarter >= projStartQuarter);
                        break;
                    case Constant.SEMI_ANNUALY:
                        condition = tempYear > projStartYear || (tempYear == projStartYear && tempSemi >= projStartSemi);
                        break;
                    case ANNUAL:
                        condition = tempYear >= projStartYear;
                        break;
                    default:
                        LOGGER.warn("selectedFreq is not valid: " + selectedFreq);
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
        int projStartYear = currentDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO;
        String selectedFreq = projectionDTO.getFrequency();

        for (Object key : checkBoxMap.keySet()) {
            String temp[] = ((String) key).split("-");
            int tempYear = Integer.parseInt(ANNUAL.equals(selectedFreq) ? temp[0] : temp[1]);
            int tempQuarter = QUARTERLY.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll(Constant.FROM_ZERO_TO_NINE, StringUtils.EMPTY)).trim()) : 0;
            int tempSemi = SEMI_ANNUAL.getConstant().equals(selectedFreq) ? Integer.parseInt((temp[0].replaceAll(Constant.FROM_ZERO_TO_NINE, StringUtils.EMPTY)).trim()) : 0;
            int tempMonth = MONTHLY.equals(selectedFreq) ? CommonUtils.getMonthNumber(temp[0].trim()) : 0;
            boolean condition = false;
            switch (selectedFreq) {
                case MONTHLY:
                    condition = tempYear > projStartYear || (tempYear == projStartYear && tempMonth >= projStartMonth);
                    break;
                case Constant.QUARTERLY:
                    condition = tempYear > projStartYear || (tempYear == projStartYear && tempQuarter >= projStartQuarter);
                    break;
                case Constant.SEMI_ANNUALY:
                    condition = tempYear > projStartYear || (tempYear == projStartYear && tempSemi >= projStartSemi);
                    break;
                case ANNUAL:
                    condition = tempYear >= projStartYear;
                    break;
                default:
                    LOGGER.warn("selectedFreq is not valid: " + selectedFreq);
                    break;
            }

            if ((condition) && (checkBoxMap.get(key))) {
                if (!selectedPeriods.equals(StringUtils.EMPTY)) {
                    selectedPeriods = selectedPeriods + ",";
                }
                String value = (String) key;
                value = MONTHLY.equals(selectedFreq) ? CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + tempMonth + " " + tempYear : value.replace('-', ' ');
                selectedPeriods = selectedPeriods + value;
            }
        }
        return selectedPeriods.toUpperCase();
    }

    /**
     * Formats the given value based on the passed decimal format.
     *
     * @param decFormat
     * @param value
     * @param appendChar
     * @return
     */
    public String getFormatValue(DecimalFormat decFormat, String value, String appendChar) {
        if (Constant.CURRENCY.equals(appendChar)) {
            value = appendChar.concat(decFormat.format(Double.valueOf(value)));
        } else {
            value = decFormat.format(Double.valueOf(value)).concat(appendChar);
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
                if ((Boolean) getTableLogic().getContainerDataSource().getContainerProperty(item, Constant.CHECK).getValue()) {
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
    public void calculateButtonLogic() {

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
        if (forecastEndPeriod.getValue() != null && !validateStartEndPeriods(String.valueOf(nmFrequencyDdlb.getValue()), String.valueOf(forecastStartPeriod.getValue()), String.valueOf(forecastEndPeriod.getValue()))) {
            AbstractNotificationUtils.getErrorNotification(Constant.ERROR, "The Start Period must be before the End Period.Please try again");
            return;
        }

        if (!ismultipleDiscount()) {
            AbstractNotificationUtils.getErrorNotification(Constant.ERROR, "The selected baseline periods are within the Start Period and End Period range.  Please select a baseline period that is prior to the calculation range.");
            return;
        }
        switch (checkBaseLinePeriods(forecastEndPeriod.getValue() != null)) {
            case 1:
                AbstractNotificationUtils.getErrorNotification("Baseline period within calculation range", alertMsg.getString("SP_MSG_ID_01"));
                return;
            case NumericConstants.TWO:
                AbstractNotificationUtils.getErrorNotification("Baseline period after calculation range", alertMsg.getString("SP_MSG_ID_02"));
                return;

            case NumericConstants.THREE:
                AbstractNotificationUtils.getErrorNotification(Constant.ERROR, alertMsg.getString("SP_MSG_ID_03"));
                return;
            case NumericConstants.FOUR:
                AbstractNotificationUtils.getErrorNotification("No period selected", "Please select at least two historic periods to use as a baseline for each selected discount.");
                return;
            default:
                LOGGER.warn("Baseline period is not valid: ");
                break;
        }

        if (!checkForCheckedRecord()) {
            AbstractNotificationUtils.getErrorNotification("No Hierarchy level selected", "Please select a level in the hierarchy for the methodology.  ");
            return;
        }

        if (!returnsFlag) {
            if ((allocationBasis.isEnabled()) && (allocationBasis.getValue() == null)) {
                AbstractNotificationUtils.getErrorNotification("No Allocation Basis selected", "Please select an Allocation Basis.");
                return;
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
            if (StringUtils.isEmpty(selectedPeriods) && !Constant.AVERAGE.equalsIgnoreCase(calcMethodology) && !"Customer GTS".equalsIgnoreCase(calcMethodology)) {
                AbstractNotificationUtils.getErrorNotification(Constant.ERROR, "Please select a Historic Period.");
                return;
            } else if (Constant.AVERAGE.equalsIgnoreCase(calcMethodology)) {
                String[] historySelected = selectedPeriods.split(",");
                if (historySelected.length < NumericConstants.TWO) {
                    AbstractNotificationUtils.getErrorNotification(Constant.ERROR, "Please select at least two Historic Periods.");
                    return;
                }
            }
            if ((calcMethodology.equals(Constant.SINGLE_PERIOD) || Constant.PERCOFDEMAND.equals(calcMethodology) || Constant.PERCOFINVENTORYWITHDRAWAL.equals(calcMethodology) || Constant.PERC_OF_ADJUSTED_DEMAND.equals(calcMethodology)) && (selectedPeriods.contains(","))) {
                AbstractNotificationUtils.getErrorNotification("Single period ", "Please select a single period for calculation.");
                return;
            }

            if (Constant.ROLLINGANNUALTREND.equalsIgnoreCase(calcMethodology) || Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND.equalsIgnoreCase(calcMethodology)) {
                String[] historySelected = selectedPeriods.split(",");

                if (QUARTERLY.getConstant().equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length == NumericConstants.FOUR) {
                        if (!String.valueOf(forecastStartPeriod.getValue()).trim().startsWith("Q1") && !Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND.equalsIgnoreCase(calcMethodology)) {
                            throwErrorMessage(calcMethodology);
                            return;
                        }
                    } else {
                        throwErrorMessage(calcMethodology);
                        return;
                    }

                } else if (SEMI_ANNUAL.getConstant().equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length == NumericConstants.TWO) {
                        if (!String.valueOf(forecastStartPeriod.getValue()).trim().startsWith("S1") && !Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND.equalsIgnoreCase(calcMethodology)) {
                            throwErrorMessage(calcMethodology);
                            return;
                        }
                    } else {
                        throwErrorMessage(calcMethodology);
                        return;
                    }
                } else if (ANNUAL.equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length != 1) {
                        throwErrorMessage(calcMethodology);
                        return;
                    }
                } else if (MONTHLY.equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length == NumericConstants.TWELVE) {
                        if (!String.valueOf(forecastStartPeriod.getValue()).startsWith("Jan") && !Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND.equalsIgnoreCase(calcMethodology)) {
                            throwErrorMessage(calcMethodology);
                            return;
                        }
                    } else {
                        throwErrorMessage(calcMethodology);
                        return;
                    }
                }
                if (!getValidation(selectedPeriods)) {

                    throwErrorMessage(calcMethodology);
                    return;
                }
            }
            if (!validateStartEndPeriods(String.valueOf(nmFrequencyDdlb.getValue()), String.valueOf(forecastStartPeriod.getValue()), String.valueOf(forecastEndPeriod.getValue()))) {
                AbstractNotificationUtils.getErrorNotification(Constant.ERROR, "End Period should be greater than Start Period.");
                return;
            } else if (setMethodologiesValuesVal.contains(String.valueOf(methodology.getValue())) && !checkHistorySelectedCount(1)) {
                NotificationUtils.getErrorNotification(Constant.ERROR, "Please select only one period for the Single Period methodology.");
            }
            LOGGER.debug("CALC Methodology :" + calcMethodology);
            session.setIsSalesCalculated(true);
            session.setIsSPCalculationDoneAgain(true);
            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                commonLogic.insertPFDTemp(session, calcMethodology, String.valueOf(allocationBasis.getValue()), true);
            }
            isSalesCalculated = salesLogic.calculateSalesProjection(projectionDTO, calcMethodology, selectedPeriods, calcBased, String.valueOf(forecastStartPeriod.getValue()), String.valueOf(forecastEndPeriod.getValue()), String.valueOf(allocationBasis.getValue()));
            refreshTableData(getCheckedRecordsHierarchyNo());

        } else {
            if (Constant.ROLLINGANNUALTREND.equalsIgnoreCase(calcMethodology)) {
                String[] historySelected = selectedPeriods.split(",");

                if (QUARTERLY.getConstant().equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length == NumericConstants.FOUR) {
                        if (!String.valueOf(forecastStartPeriod.getValue()).trim().startsWith("Q1")) {
                            throwErrorMessage(calcMethodology);
                            return;
                        }
                    } else {
                        throwErrorMessage(calcMethodology);
                        return;
                    }

                } else if (SEMI_ANNUAL.getConstant().equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length == NumericConstants.TWO) {
                        if (!String.valueOf(forecastStartPeriod.getValue()).trim().startsWith("S1")) {
                            throwErrorMessage(calcMethodology);
                            return;
                        }
                    } else {
                        throwErrorMessage(calcMethodology);
                        return;
                    }
                } else if (ANNUAL.equalsIgnoreCase(projectionDTO.getFrequency())) {
                    if (historySelected.length != 1) {
                        throwErrorMessage(calcMethodology);
                        return;
                    }
                } else if ((MONTHLY.equalsIgnoreCase(projectionDTO.getFrequency())) && (historySelected.length != NumericConstants.TWELVE)) {
                    throwErrorMessage(calcMethodology);
                    return;
                }
            }
            String startPeriodAt = String.valueOf(forecastStartPeriod.getValue());
            String endPeriodAt = forecastEndPeriod.getValue() == null ? StringUtils.EMPTY : String.valueOf(forecastEndPeriod.getValue());
            if (MONTHLY.equalsIgnoreCase(projectionDTO.getFrequency())) {
                try {
                    startPeriodAt = "M" + CommonLogic.getYearAndPeriod(startPeriodAt, projectionDTO.getFrequency(), true)[1] + " " + CommonLogic.getYearAndPeriod(startPeriodAt, projectionDTO.getFrequency(), true)[0];
                    endPeriodAt = endPeriodAt.isEmpty() ? endPeriodAt : "M" + CommonLogic.getYearAndPeriod(endPeriodAt, projectionDTO.getFrequency(), true)[1] + " " + CommonLogic.getYearAndPeriod(endPeriodAt, projectionDTO.getFrequency(), true)[0];
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
            isSalesCalculated = salesLogic.calculateReturnsProjection(projectionDTO, calcMethodology, selectedPeriods, startPeriodAt, endPeriodAt);
            refreshTableData(getReturnsCheckedRecordsHierarchyNo());

        }
        if (isSalesCalculated == true) {
            final Notification notif = new Notification("Calculation Complete", Notification.Type.HUMANIZED_MESSAGE);
            notif.setPosition(Position.TOP_CENTER);
            notif.setStyleName(ConstantsUtils.MY_STYLE);
            notif.show(Page.getCurrent());
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
                if ((value != null) && (value.contains(Constant.ACTUALSALES))) {
                    tempSalesvalue = true;
                }

                if (value.contains(Constant.ACTUALUNITS)) {
                    tempUnitValue = true;
                }
            }
        }

        if (tempSalesvalue && tempUnitValue) {
            return -1;

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
    private void configureFrequency(final ComboBox frequency, final ComboBox history) {
        frequency.addItem(MONTHLY);
        frequency.addItem(QUARTERLY.getConstant());

        frequency.addItem(SEMI_ANNUAL.getConstant());

        frequency.addItem(ANNUAL);
        frequency.select(QUARTERLY.getConstant());
        frequency.setNullSelectionAllowed(false);
        frequency.setImmediate(true);
        frequency.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
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
        LOGGER.debug("loadFrequency for " + String.valueOf(frequency.getValue()));
        CommonUtils.frequenceValueChange(String.valueOf(frequency.getValue()), history, session);
        LOGGER.debug("loadFrequency ends ");
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
                    default:
                        LOGGER.warn("Value is not valid: " + value);
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
        Object[] displayValidation = CommonUtil.getDisplayFormatSelectedValues(displayFormatValues);
        if (!CommonUtil.nullCheck(displayValidation) && displayValidation.length == 0) {
            AbstractNotificationUtils.getErrorNotification("No Display Format Selected", "Please select value(s) from the Display Format field");
            checkSelection = false;
        }
        return checkSelection;
    }

    protected void configureGraph() {
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
        LOGGER.debug("loadLevelAndFilterValue initiated ");
        level.removeAllItems();
        level.addItem(SELECT_ONE);
        level.setNullSelectionItemId(SELECT_ONE);
        level.setValue(SELECT_ONE);
        List<Leveldto> hierarchy = null;
        if (projectionDTO.isIsCustomHierarchy()) {
            hierarchy = session.getCustomHierarchyMap().get(customId);
            Utility.loadLevelValue(level, levelFilter, null, hierarchy, Constant.CUSTOM_LABEL);
        } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            Utility.loadLevelValue(level, levelFilter, populateLevel, session.getCustomerHierarchyList(), String.valueOf(view.getValue()));
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projectionDTO.getHierarchyIndicator())) {
            Utility.loadLevelValue(level, levelFilter, populateLevel, session.getProductHierarchyList(), String.valueOf(view.getValue()));
        }
        LOGGER.debug("loadLevelAndFilterValue ends ");
    }

    /**
     * Configures the table for the excel export.
     */
    protected void configureExcelResultTable() {
        excelContainer = new ExtTreeContainer<>(SalesRowDto.class, ExtContainer.DataStructureMode.MAP);
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
            excelTable.setRefresh(false);
            excelTable.setVisible(Boolean.FALSE);
            excelContainer.setColumnProperties(excelHeader.getProperties());
            excelTable.setContainerDataSource(excelContainer);
            tableLayout.addComponent(excelTable);
        }

    }

    protected void levelFilterDdlbChangeOption(boolean excelExport) {
        LOGGER.debug("excelExport" + excelExport);
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(levelFilter.getValue());
        int levelNo = Integer.parseInt(String.valueOf(levelHierarchy.get(0)));
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
                projectionDTO.setExcelFlag(true);
                count = salesLogic.getConfiguredSalesProjectionCount(new Object(), projectionDTO, true, projectionDTO);
            }
            List<SalesRowDto> resultList = salesLogic.getConfiguredSalesProjection(new Object(), 0, count, projectionDTO);
            loadDataToContainer(resultList, null);
        } catch (Exception e) {
            LOGGER.error(e);
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
            LOGGER.error(e);
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
            if (!resultList.isEmpty()) {
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
            LOGGER.error(e);
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
        int levelNo = Integer.parseInt(String.valueOf(levelHierarchy.get(0)));
        if (level.getValue() != null) {
            levelFilter.setValue(null);
        }

        if (levelNo > 0) {
            Object val = levelFilter.getValue();
            if (val != null) {
                if (!SELECT_ONE.getConstant().equals(String.valueOf(val))) {
                    levelFilter.setValue(SELECT_ONE);
                    getTableLogic().clearAll();
                    projectionDTO.setLevelFilter(false);
                    projectionDTO.setLevelFilterValue(StringUtils.EMPTY);
                } else {
                    getTableLogic().clearAllExceptCurrentPage();
                }
            } else {
                getTableLogic().clearAllExceptCurrentPage();
            }
            if (!isExpand) {
                levelNo--;
            }
            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionDTO.getScreenName())) {
                ((NMSalesProjectionTableLogic) getTableLogic()).loadExpandData(levelNo);
            } else {
                mSalesProjectionTableLogic.loadExpandData(levelNo);
            }
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

    protected void setProjectionSelection(boolean isReset) {
        Map<Object, Object> map = null;
        if ("Returns".equalsIgnoreCase(screenName)) {
            map = CommonLogic.getReturnsProjectionSelection(session.getProjectionId());
        } else {
            map = CommonLogic.getNMProjectionSelection(session.getProjectionId(), Constant.SALES_PROJECTION_LABEL);
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
            value = map.get(Constant.DISPLAY_FORMAT_SAVE);
            if (!CommonUtil.nullCheck(value)) {
                CommonUtil.setCustomMenuBarValuesInEdit(value, displayFormatValues);
            }
            if (isReset) {
                CommonLogic.unCheckMultiSelect(customerFilterValues);
                CommonLogic.unCheckMultiSelect(productFilterValues);
            }
            value = map.get(Constant.CUSTOMER_LEVEL_DDLB);
            customerlevelDdlb.setValue(CommonUtil.nullCheck(value) || CommonUtil.stringNullCheck(value) ? SELECT_ONE.getConstant() : Integer.parseInt(value.toString()));
            value = map.get(Constant.CUSTOMER_LEVEL_VALUE);
            if (!CommonUtil.nullCheck(value)) {
                generateCustomerToBeLoaded = value.equals(StringUtils.EMPTY) ? Collections.<String>emptyList() : generateCustomerToBeLoaded;
                CommonUtil.setCustomMenuBarValuesInEdit(value, customerFilterValues);
            }
            value = map.get(Constant.PRODUCT_LEVEL_DDLB);
            productlevelDdlb.setValue(CommonUtil.nullCheck(value) || CommonUtil.stringNullCheck(value) ? SELECT_ONE.getConstant() : Integer.parseInt(value.toString()));
            value = map.get(Constant.PRODUCT_LEVEL_VALUE);
            if (!CommonUtil.nullCheck(value)) {
                generateProductToBeLoaded = value.equals(StringUtils.EMPTY) ? Collections.<String>emptyList() : generateProductToBeLoaded;
                CommonUtil.setCustomMenuBarValuesInEdit(value, productFilterValues);
            }
        }
    }

    /**
     * Refreshes the group ddlb after populate button.
     */
    public void refreshGroupDdlb() {
        try {
            GroupFilter.initSalesMap(session);
            massGroupBean.removeAllItems();
            massGroupBean.addBean(Constant.SELECT_ONE);
            massGroupBean.addAll(session.getSalesgroupSet());
            groupBean.removeAllItems();
            groupBean.addBean(Constant.SHOW_ALL_GROUPS);
            groupBean.addAll(session.getSalesgroupSet());
        } catch (Exception ex) {
            LOGGER.error(ex);
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
        } else if (selectedPeriods.length() > NumericConstants.FOUR) {
            flag = false;
            return flag;
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
        LOGGER.debug("methodologyDdlb ValueChangeEvent initiated " + methodology.getValue());

        if (methodology.getValue() != null && (Constant.PERCOFDEMAND.equals(methodology.getValue()) || Constant.PERCOFEXFACTORY.equals(methodology.getValue())
                || Constant.PERCOFEXFACTORYSALES.equals(methodology.getValue()) || Constant.PERCOFINVENTORYWITHDRAWAL.equals(methodology.getValue())
                || Constant.CUSTOMER_GTS.equals(methodology.getValue()) || Constant.PERC_OF_ADJUSTED_DEMAND.equals(methodology.getValue()) || Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND.equals(methodology.getValue()))) {
            allocationBasis.select(Constant.SELECT_ONE);
            allocationBasis.setEnabled(false);
        } else {
            allocationBasis.setEnabled(true);
        }
        LOGGER.debug("methodologyDdlb ValueChangeEvent ends ");
    }

    public boolean isSalesCalculated() {
        return isSalesCalculated;
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
                    m = NumericConstants.TWO;
                    break;
                }
            }
        }

        if (count == 0) {
            m = NumericConstants.THREE;
        }
        if (methodology.getValue().equals(Constant.AVERAGE) && count < NumericConstants.TWO && count != 0) {
            m = NumericConstants.FOUR;
        }
        if (methodology.getValue().equals(Constant.SINGLE_PERIOD) && count > 1) {
            m = NumericConstants.FIVE;
        }
        if (methodology.getValue().equals(Constant.CUSTOMER_GTS)) {
            m = NumericConstants.FIVE;
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
        int a[] = new int[NumericConstants.TWO];

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
            resultValue = Integer.valueOf(a[1] + (a[0] < NumericConstants.TEN ? Constant.DASH + a[0] : StringUtils.EMPTY + a[0]));
        }

        return resultValue;
    }

    private boolean checkHistorySelectedCount(int i) {
        for (Object key : tripleHeaderForCheckedDoubleHeader.keySet()) {
            Map<String, List<String>> checkedDoubleHeaders = tripleHeaderForCheckedDoubleHeader.get(String.valueOf(key));

            if (checkedDoubleHeaders != null) {
                List<String> checkedHistoryList = checkedDoubleHeaders.get("H");
                if (checkedHistoryList != null) {
                    if (i == NumericConstants.TWO && checkedHistoryList.size() < i) {

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
        leftTable.setFilterDecorator(new ExtDemoFilterDecorator());
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
                    TextField metodologyFilter = new TextField();
                    metodologyFilter.setWidth("100%");
                    return metodologyFilter;
                } else if (Constant.BASELINE.equals(propertyId)) {
                    TextField baseFilter = new TextField();
                    baseFilter.setWidth("100%");
                    return baseFilter;
                } else if (Constant.LEVELNAME.equals(propertyId)) {
                    TextField levelField = new TextField();
                    levelField.setWidth("100%");
                    return levelField;
                } else if (Constant.CHECK.equals(propertyId)) {
                    TextField checkField = new TextField();
                    checkField.setWidth("100%");
                    return checkField;
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
        leftTable.resetFilters();
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
                    TextField filterForMetohdology = new TextField();
                    filterForMetohdology.setReadOnly(true);
                    filterForMetohdology.setWidth("100%");
                    return filterForMetohdology;

                } else if (Constant.BASELINE.equals(propertyId)) {
                    TextField filterForBaseline = new TextField();
                    filterForBaseline.setReadOnly(true);
                    filterForBaseline.setWidth("100%");
                    return filterForBaseline;

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
    }

    private void throwErrorMessage(String calcMethodology) {
        if (Constant.ROLLINGANNUALTREND.equalsIgnoreCase(calcMethodology) || Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND.equalsIgnoreCase(calcMethodology)) {
            AbstractNotificationUtils.getErrorNotification("Select complete annual period",
                    "The " + calcMethodology + " methodology requires a complete calendar year of periods to use as a baseline.\n  Please select a complete calendar year of periods and try again.");
        }
    }

    private Integer[] getPeriod(Object key, int frequencyDivision) {
        Integer[] periods = new Integer[NumericConstants.TWO];
        switch (frequencyDivision) {
            case 1:
                periods[0] = Integer.valueOf(key.toString().substring(0, NumericConstants.FOUR));
                break;
            case NumericConstants.TWO:
                periods[0] = (int) key.toString().charAt(1) - NumericConstants.FORTY_EIGHT;
                periods[1] = Integer.valueOf(key.toString().substring(NumericConstants.THREE, NumericConstants.SEVEN));
                break;
            case NumericConstants.FOUR:
                periods[0] = (int) key.toString().charAt(1) - NumericConstants.FORTY_EIGHT;
                periods[1] = Integer.valueOf(key.toString().substring(NumericConstants.THREE, NumericConstants.SEVEN));
                break;
            case NumericConstants.TWELVE:
                periods[0] = CommonUtils.getIntegerForMonth(StringUtils.capitalize(key.toString().substring(0, NumericConstants.THREE)));
                periods[1] = Integer.valueOf(key.toString().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
                break;
            default:
                LOGGER.warn("frequencyDivision is not valid: " + frequencyDivision);
                break;
        }
        return periods;
    }

    public boolean validateForAlternateHistory() {
        if (CUSTOMER.getConstant().equals(String.valueOf(view.getValue()))) {

            int numoFCustomers = getCheckedCustomercount(logic);

            if (numoFCustomers > 0) {

                boolean flag = logic.checkActuals(session);

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
                boolean flag = logic.checkActuals(session);

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
        return logic.getCCPCount(logic.buildCCPCountQuery(true), session);
    }

    public int getCheckedProductCount(final AlternateHistoryLogic logic) {
        return logic.getCCPCount(logic.buildCCPCountQuery(false), session);
    }

    /**
     * Returns true if End Period is greater than or Equal to Start Period
     * Monthly format should be Like Jan 2015,Sep 2016... Quarterly,Semi
     * Annually format should be Like Q1 2015,S1 2016...
     */
    boolean validateStartEndPeriods(String frequency, String start, String end) {
        LOGGER.debug("Inside New Validation Method");
        try {
            if (end.isEmpty() || end.equals("null") || end.equals(SELECT_ONE) || start.trim().equals(end.trim())) {
                return true;
            }
            if (frequency.equals(MONTHLY)) {
                DateFormat df = new SimpleDateFormat("MMMM y");
                if (df.parse(end.trim()).after(df.parse(start.trim()))) {
                    return true;
                }
            } else if (frequency.equals(ANNUAL)) {
                if (Integer.valueOf(end.trim()) > Integer.valueOf(start.trim())) {
                    return true;
                }
            } else {
                int startyear = Integer.parseInt(start.trim().substring(NumericConstants.THREE));
                int endyear = Integer.parseInt(end.trim().substring(NumericConstants.THREE));
                if ((endyear > startyear)
                        || ((startyear == endyear) && (Integer.valueOf(end.trim().substring(1, NumericConstants.TWO)) > Integer.valueOf(start.trim().substring(1, NumericConstants.TWO))))) {
                    return true;
                }
            }
            return false;

        } catch (Exception ex) {
            LOGGER.error(ex);
            return false;
        }
    }

    public PageTreeTableLogic getTableLogic() {
        return mSalesProjectionTableLogic;
    }

    protected DataFormatConverter getConverter(String indicator) {
        if (Constant.SALES_SMALL.equals(indicator)) {
            if (!CommonUtil.isValueEligibleForLoading()) {
                return salesFormat;
            }
            return (stringNullCheck(projectionDTO.getConversionFactor())
                    || StringUtils.isBlank(String.valueOf(projectionDTO.getConversionFactor()))
                    || Constant.CONVERSION_FACTOR_DEFALUT_VALUE.equals(String.valueOf(projectionDTO.getConversionFactor())))
                    ? salesConversionFormat : salesConversionTwoDecimalFormat;
        } else {
            if (!CommonUtil.isValueEligibleForLoading()) {
                return unitFormat;
            }
            return unitConversionFormat;
        }
    }

    public void loadMethodologyDdlb(final ComboBox methodology) {
        String query;
        Map<String, List<String>> input = new HashMap<>();
        List<String> defaultNames = Arrays.asList("1.Single Period", "2.Average", "3.Rolling Annual Trend");
        List<String> exfactNames = Arrays.asList("4.% of Ex-Factory Sales", "9.% OF Ex-Factory - Seasonal Trend");
        List<String> demandNames = Arrays.asList("5.% of Demand");
        List<String> adjDemandNames = Arrays.asList("7.% of Adjusted Demand");
        List<String> inventoryNames = Arrays.asList("6.% of Inventory Withdrawal");
        List<String> custGTSNames = Arrays.asList("8.Customer GTS");
        List<String> outputList = new ArrayList<>();
        input.put("Ex-Factory Sales", exfactNames);
        input.put("Demand", demandNames);
        input.put("Inventory Withdrawal - Forecast Detail", inventoryNames);
        input.put("Inventory Withdrawal - Forecast Summary", inventoryNames);
        input.put("Customer Sales", custGTSNames);
        input.put("Adjusted Demand", adjDemandNames);
        query = SQlUtil.getQuery("get-file-type-query");
        List<String> returnList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        outputList.addAll(defaultNames);
        for (String string : returnList) {
            outputList.addAll(input.get(string));
        }
        Collections.sort(outputList);
        for (String str : outputList) {
            methodology.addItem(str.split("\\.")[1]);
        }
    }

  public void loadAllocationDdlb(final ComboBox allocationBasis) {
            String query;
            Map<String, String> input = new HashMap<>();
            List<String> outputList = new ArrayList<>();
            input.put("Ex-Factory Sales", Constant.LabelConstants.PERC_OF_EX_FACTORY.getConstant());
            input.put("Demand", Constant.PERCOFDEMAND);
            input.put("Inventory Withdrawal - Forecast Detail", Constant.PERCOFINVENTORYWITHDRAWAL);
            input.put("Inventory Withdrawal - Forecast Summary", Constant.PERCOFINVENTORYWITHDRAWAL);
            query = SQlUtil.getQuery("get-file-type-query");
            List<String> returnList = HelperTableLocalServiceUtil.executeSelectQuery(query);
            for (String string : returnList) {
                if (!"Customer Sales".equals(string) && !"Adjusted Demand".equals(string)) {
                    outputList.add(input.get(string));
                }
            }
            
            allocationBasis.addItems(outputList);
    }
    
}
