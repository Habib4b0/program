/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.dto.PMPYCalculationExporterDTO;
import com.stpl.app.gtnforecasting.dto.PMPYCalculatorDTO;
import com.stpl.app.gtnforecasting.dto.PMPYRowDto;
import com.stpl.app.gtnforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.logic.SalesProjectionLogic;
import com.stpl.app.gtnforecasting.queryUtils.PPAQuerys;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.ui.NmSalesGraphWindow;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.CORRECTVALUE;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.app.gtnforecasting.utils.PMPYCalculationExporter;
import com.stpl.app.gtnforecasting.utils.PMPYContractHolderHistoryChart;
import com.stpl.app.gtnforecasting.utils.PMPYTradingPartnerHistoryChart;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.validator.RegexpValidator;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.NativeSelect;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezeFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PMPYCalculator.
 *
 * @author lokeshwari
 */
public class PMPYCalculator extends Window {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PMPYCalculator.class);
    
    

    /**
     * The space.
     */
    private final Label space = new Label(Constant.ADD_SPACE, ContentMode.HTML);

    private CustomTableHeaderDTO rightDto = new CustomTableHeaderDTO();

    /**
     * The effective date.
     */
    private final NativeSelect effectivePeriod = new NativeSelect();

    /**
     * The btn reset search.
     */
    private final Button btnResetSearch = new Button(Constant.RESET);

    /**
     * The pmpy contract holder.
     */
    private final TextField pmpyContractHolderField = new TextField();

    /**
     * The trading partner no.
     */
    private final TextField tradingPartnerNoField = new TextField();

    /**
     * The trading partner name.
     */
    private final TextField tradingPartnerNameField = new TextField();

    /**
     * The contract.
     */
    private ComboBox contractComboBox = new ComboBox();

    /**
     * The frequency.
     */
    private final TextField frequencyField = new TextField();

    /**
     * The history.
     */
    private final TextField historyField = new TextField();

    /**
     * The trading partner.
     */
    private final CustomTextField tradingPartner = new CustomTextField();

    /**
     * The generate.
     */
    private final Button generate = new Button(Constant.GENERATE);

    /**
     * The btn reset calculation.
     */
    private final Button btnResetCalculation = new Button(Constant.RESET);
    /**
     * The btnimport.
     */
    private final Button btnimport = new Button(Constant.IMPORT);

    /**
     * The SALES_SMALL.
     */
    private final TextField salesField = new TextField();

    /**
     * The market share.
     */
    private final TextField marketShareField = new TextField();

    /**
     * The market share1.
     */
    private final TextField marketShare1 = new TextField();

    /**
     * The analog lives.
     */
    private final TextField analogLivesField = new TextField();

    /**
     * The value per life.
     */
    private final TextField valuePerLifeField = new TextField();

    /**
     * The projected lives.
     */
    private final TextField projectedLivesField = new TextField();

    /**
     * The total SALES_SMALL.
     */
    private final TextField totalSalesField = new TextField();

    /**
     * The projection period total.
     */
    private final TextField projectionPeriodTotalField = new TextField();

    /**
     * The SALES_SMALL or UNITS_SMALL.
     */
    private final OptionGroup salesOrUnitsGroup = new OptionGroup();

    /**
     * The populate.
     */
    private final Button populate = new Button(Constant.POPULATE);

    /**
     * The non mandated logic.
     */
    private final NonMandatedLogic nonMandatedLogic = new NonMandatedLogic();

    /**
     * The df calculated amount.
     */

    private final DecimalFormat unitFormat = new DecimalFormat(Constant.SINGLE_DECIMAL_FORMAT);
    private static final DecimalFormat MONEY_TWO_DECIMAL = new DecimalFormat("$#,###.00");
    private static final DecimalFormat MONEY_NO_DECIMAL = new DecimalFormat("$#,###");
    private final DecimalFormat noDecimalPlace = new DecimalFormat("####");
    /**
     * The df calculated unit.
     */
    private final DecimalFormat dfCalculatedUnit = new DecimalFormat("#,###.0");

    /**
     * The df SALES_SMALL.
     */
    private final DecimalFormat dfSales = new DecimalFormat("#");

    /**
     * The df UNITS_SMALL.
     */
    private final DecimalFormat dfUnits = new DecimalFormat("#.0");

    /**
     * The calculated SALES_SMALL value.
     */
    private Double calculatedSalesVal = 0.0;

    /**
     * The calculated UNITS_SMALL value.
     */
    private Double calculatedUnitsVal = 0.0;

    /**
     * The excel export ch.
     */
    private final Image excelExportCh = new Image(null, new ThemeResource(PMPYCalculationExporter.EXCEL_ICON));

    /**
     * The excel export tp.
     */
    private final Image excelExportTp = new Image(null, new ThemeResource(PMPYCalculationExporter.EXCEL_ICON));

    /**
     * The excel export calculation.
     */
    private final Image excelExportCalculation = new Image(null, new ThemeResource(PMPYCalculationExporter.EXCEL_ICON));

    /**
     * The total lives chart ch.
     */
    private final Image totalLivesChartCh = new Image(null, new ThemeResource(PMPYCalculationExporter.CHART_ICON));

    /**
     * The total lives chart tp.
     */
    private final Image totalLivesChartTp = new Image(null, new ThemeResource(PMPYCalculationExporter.CHART_ICON));

    /**
     * The calculate one.
     */
    private final Button calculateOne = new Button(Constant.CALCULATE);

    /**
     * The calculate two.
     */
    private final Button calculateTwo = new Button(Constant.CALCULATE);

    private static final String REGEX = "(^[0-9]+(\\.[0-9])?$)";

    private final Calendar calendar = CommonUtils.getCalendar();

    private final FreezeFilterTable contractHolderTable = new FreezeFilterTable();
    private final FreezeFilterTable tradingHistoryTable = new FreezeFilterTable();
    private ExtTreeContainer<PMPYRowDto> chTreeContainer = new ExtTreeContainer<>(PMPYRowDto.class,ExtContainer.DataStructureMode.MAP);
    private ExtTreeContainer<PMPYRowDto> tpTreeContainer = new ExtTreeContainer<>(PMPYRowDto.class,ExtContainer.DataStructureMode.MAP);

    private String historyPeriods = StringUtils.EMPTY;
    private final List projectionDetailsId;

    private final List<Object> visiColumn = new ArrayList<>();
    private final List<String> visiHeaders = new ArrayList<>();
    private final List<String> chtCheckBoxMap = new ArrayList<>();
    private final List<String> tptCheckBoxMap = new ArrayList<>();
    private boolean valueChange = true;

    /**
     * The max split position.
     */
    private final float maxSplitPosition = 1000;

    /**
     * The min split position.
     */
    private final float minSplitPosition = 1;

    /**
     * The split position.
     */
    private final float splitPosition = 94;

    private String tradeName = StringUtils.EMPTY;

    private String tradeNo = StringUtils.EMPTY;

    private String contHolder = StringUtils.EMPTY;

    private final ExtFilterTreeTable excelTable1 = new ExtFilterTreeTable();

    private final ExtFilterTreeTable excelTable2 = new ExtFilterTreeTable();

    private int projectionId = 0;

    private boolean importEvent = false;

    private final SessionDTO session;
    private final List doubleProjectedColumns;

    public PMPYCalculator(final String history, final List projectionDetailsId, final CustomTableHeaderDTO rightHeader, String tradeName, String tradeNo, String contractHolder, SessionDTO session, List doubleProjectedColumns) {
        super(Constant.PMPY_CALCULATOR);
        LOGGER.debug("Entering PMPYCalculator");
        this.historyPeriods = history;
        this.projectionDetailsId = projectionDetailsId == null ? projectionDetailsId : new ArrayList<>(projectionDetailsId);
        this.rightDto = rightHeader;
        this.tradeName = tradeName;
        this.tradeNo = tradeNo;
        this.contHolder = contractHolder;
        this.projectionId = session.getProjectionId();
        this.session = session;
        this.doubleProjectedColumns = doubleProjectedColumns == null ? doubleProjectedColumns : new ArrayList<>(doubleProjectedColumns);
        configureAttributes();
        init();
        LOGGER.debug("End of PMPYCalculator");
    }

    /**
     * Adds the screen contents and configures all the components.
     */
    public void init() {

        LOGGER.debug("Entering init method");
        center();
        setClosable(true);
        setModal(true);

        setWidth("1750px");
        setHeight("950px");

        setContent(addToContent());
        configureFields();
        LOGGER.debug("End of init method");
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);

    }

    /**
     * Configures all the components.
     */
    public void configureFields() {
        try {
            LOGGER.debug("Entering configureFields method");
            effectivePeriod.setNullSelectionAllowed(BooleanConstant.getTrueFlag());
            effectivePeriod.setNullSelectionItemId(Constant.SELECT_ONE);
            loadEffectivePeriods();
            tradingPartner.setStyleName(Constant.SEARCH_TEXT);
            contractComboBox.setWidth("240px");
            contractComboBox.addStyleName("comboxsize");
            salesOrUnitsGroup.addItem(Constant.SALES_DOLLARS);
            salesOrUnitsGroup.addItem(Constant.UNIT_VOLUME);
            salesOrUnitsGroup.select(Constant.SALES_DOLLARS);
            loadContractHolder();

            marketShareField.setValue(Constant.HUNDRED_PERCENT);
            marketShare1.setValue(Constant.HUNDRED_PERCENT);
            analogLivesField.setImmediate(true);
            analogLivesField.addValidator(new RegexpValidator(REGEX, "Should contain only number"));
            salesField.setEnabled(false);
            valuePerLifeField.setEnabled(false);
            totalSalesField.setEnabled(false);
            projectionPeriodTotalField.setEnabled(false);
            frequencyField.setEnabled(false);
            frequencyField.setWidth("150px");
            historyField.setEnabled(false);

            pmpyContractHolderField.setValue(contHolder);

            tradingPartnerNoField.setValue(tradeNo);

            tradingPartnerNameField.setValue(tradeName);

            pmpyContractHolderField.setReadOnly(true);
            pmpyContractHolderField.focus();
            tradingPartnerNoField.setReadOnly(true);
            tradingPartnerNameField.setReadOnly(true);

            excelExportCalculation.addClickListener(new MouseEvents.ClickListener() {
                /**
                 * Default method.
                 */

                @Override
                public void click(final MouseEvents.ClickEvent event) {
                    // Excel export functionality here
                    exportCalculatedExcel();
                }
            });

            marketShare1.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (valueChange) {
                        if (marketShare1.isValid()) {
                            Double value = Double.valueOf(String.valueOf(marketShare1.getValue()));
                            marketShare1.setValue(unitFormat.format(value) + Constant.PERCENT);
                        } else {
                            MessageBox.showPlain(Icon.INFO, Constant.ERROR, Constant.PLEASE_ENTER_THE_CORRECT_VALUE_DOT, ButtonId.OK);
                            marketShare1.setValue(Constant.HUNDRED_PERCENT);
                        }

                    }
                }
            });
            marketShareField.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (valueChange) {
                        if (marketShareField.isValid()) {
                            Double value = Double.valueOf(String.valueOf(marketShareField.getValue()));
                            marketShareField.setValue(unitFormat.format(value) + Constant.PERCENT);
                        } else {
                            MessageBox.showPlain(Icon.INFO, Constant.ERROR, Constant.PLEASE_ENTER_THE_CORRECT_VALUE_DOT, ButtonId.OK);
                            marketShareField.setValue(Constant.HUNDRED_PERCENT);
                        }

                    }
                }
            });

            analogLivesField.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (valueChange) {
                        if (analogLivesField.isValid()) {
                            Double value = Double.valueOf(String.valueOf(analogLivesField.getValue()));
                            analogLivesField.setValue(noDecimalPlace.format(value));
                        } else {
                            MessageBox.showPlain(Icon.INFO, Constant.ERROR, Constant.PLEASE_ENTER_THE_CORRECT_VALUE_DOT, ButtonId.OK);
                            analogLivesField.setValue(StringUtils.EMPTY);
                        }
                    }
                }
            });
            projectedLivesField.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (valueChange) {
                        if (projectedLivesField.isValid()) {
                            Double value = Double.valueOf(String.valueOf(projectedLivesField.getValue()));
                            projectedLivesField.setValue(noDecimalPlace.format(value));
                        } else {
                            MessageBox.showPlain(Icon.INFO, Constant.ERROR, Constant.PLEASE_ENTER_THE_CORRECT_VALUE_DOT, ButtonId.OK);
                            projectedLivesField.setValue(StringUtils.EMPTY);
                        }
                    }
                }
            });

            btnResetCalculation.addClickListener(new Button.ClickListener() {
                /**
                 * Default method.
                 */
                @Override
                public void buttonClick(final ClickEvent event) {

                    MessageBox.showPlain(Icon.QUESTION, "Confirm Reset", "Are you sure you want to reset the page to default values?", new MessageBoxListener() {
                        /**
                         * Default method.
                         */

                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(Constant.YES)) {
                                // reset the fields here
                                valueChange = false;
                                salesField.setValue(StringUtils.EMPTY);
                                marketShareField.setValue(Constant.HUNDRED_PERCENT);
                                analogLivesField.setValue(StringUtils.EMPTY);
                                valuePerLifeField.setValue(StringUtils.EMPTY);
                                marketShare1.setValue(Constant.HUNDRED_PERCENT);
                                projectedLivesField.setValue(StringUtils.EMPTY);
                                totalSalesField.setValue(StringUtils.EMPTY);
                                projectionPeriodTotalField.setValue(StringUtils.EMPTY);
                                valueChange = true;
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);
                }
            });

            calculateOne.addClickListener(new Button.ClickListener() {
                /**
                 * Default method.
                 */
                @Override
                public void buttonClick(final ClickEvent event) {

                    firstCalculation();

                }
            });

            calculateTwo.addClickListener(new Button.ClickListener() {
                /**
                 * Default method.
                 */
                @Override
                public void buttonClick(final ClickEvent event) {
                    secondCalculation();
                }
            });
            btnimport.addClickListener(new Button.ClickListener() {
                /**
                 * Default method.
                 */
                @Override
                public void buttonClick(final ClickEvent event) {

                    try {
                        importButtonLogic();
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

                        LOGGER.error(e.getMessage());

                    }

                }
            });
            totalLivesChartCh.addClickListener(new MouseEvents.ClickListener() {
                /**
                 * Default method.
                 */
                @Override
                public void click(final MouseEvents.ClickEvent event) {
                    final PMPYContractHolderHistoryChart chart = new PMPYContractHolderHistoryChart(chTreeContainer.getBeans(), (String) contractComboBox.getValue(), rightDto.getDoubleHistoryColumns());

                    final NmSalesGraphWindow salesGraphWindow = new NmSalesGraphWindow(chart.getCharts(), Constant.PMPY_CALCULATOR);
                    UI.getCurrent().addWindow(salesGraphWindow);
                }
            });
            excelExportCh.addClickListener(new MouseEvents.ClickListener() {
                /**
                 * Default method.
                 */
                @Override
                public void click(final MouseEvents.ClickEvent event) {
                    excelTable1.setContainerDataSource(chTreeContainer);
                    ExtFilterTable leftTable = contractHolderTable.getLeftFreezeAsTable();
                    ExtFilterTable rightTable = contractHolderTable.getRightFreezeAsTable();
                    excelTable1.setVisibleColumns(ArrayUtils.addAll(leftTable.getVisibleColumns(), rightTable.getVisibleColumns()));
                    Object[] objectArray = ArrayUtils.addAll(leftTable.getColumnHeaders(), rightTable.getColumnHeaders());
                    excelTable1.setColumnHeaders(Arrays.copyOf(objectArray, objectArray.length, String[].class));
                    ForecastUI.setEXCEL_CLOSE(true);
                    ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable1), "Contract Holder", "PMPY Calculator - Contract Holder", "PMPYcalculatorContractHolder.xls", false);
                    export.export();
                }
            });

            totalLivesChartTp.addClickListener(new MouseEvents.ClickListener() {
                /**
                 * Default method.
                 */
                @Override
                public void click(final MouseEvents.ClickEvent event) {
                    final PMPYTradingPartnerHistoryChart chart = new PMPYTradingPartnerHistoryChart(tpTreeContainer.getBeans(), tradingPartner.getValue(), rightDto.getDoubleHistoryColumns());

                    final NmSalesGraphWindow salesGraphWindow = new NmSalesGraphWindow(chart.getCharts(), Constant.PMPY_CALCULATOR);
                    UI.getCurrent().addWindow(salesGraphWindow);

                }
            });
            excelExportTp.addClickListener(new MouseEvents.ClickListener() {
                /**
                 * Default method.
                 */
                @Override
                public void click(final MouseEvents.ClickEvent event) {
                    excelTable2.setContainerDataSource(tpTreeContainer);
                    ExtFilterTable leftTable = tradingHistoryTable.getLeftFreezeAsTable();
                    ExtFilterTable rightTable = tradingHistoryTable.getRightFreezeAsTable();
                    excelTable2.setVisibleColumns(ArrayUtils.addAll(leftTable.getVisibleColumns(), rightTable.getVisibleColumns()));
                    Object[] objectArray = ArrayUtils.addAll(leftTable.getColumnHeaders(), rightTable.getColumnHeaders());
                    excelTable2.setColumnHeaders(Arrays.copyOf(objectArray, objectArray.length, String[].class));
                    ForecastUI.setEXCEL_CLOSE(true);
                    ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable2), Constant.TRADING_PARTNER, "PMPY Calculator - Trading Partner", "PMPYcalculatorTradingPartner.xls", false);
                    export.export();
                }
            });

            tradingPartner.addClickListener(new CustomTextField.ClickListener() {

                @Override
                public void click(CustomTextField.ClickEvent event) {
                    final PMPYTradingPartnerLookup tpLookUp = new PMPYTradingPartnerLookup(tradingPartner, contractComboBox.getValue());
                    UI.getCurrent().addWindow(tpLookUp);
                }
                /**
                 * Default method.
                 */

            });

            generate.addClickListener(new Button.ClickListener() {
                /**
                 * Default method.
                 */
                @Override
                public void buttonClick(final ClickEvent event) {
                    try {
                        if ((contractComboBox.getValue() == null || StringUtils.EMPTY.equals(String.valueOf(contractComboBox.getValue())) || Constant.NULL.equals(String.valueOf(contractComboBox.getValue())) || String.valueOf(contractComboBox.getValue()).equals(DASH))
                                && (tradingPartner.getValue() == null || StringUtils.EMPTY.equals(String.valueOf(tradingPartner.getValue())) || Constant.NULL.equals(String.valueOf(tradingPartner.getValue())))) {
                            AbstractNotificationUtils.getErrorNotification("No Contract Holder/Trading Partner entered", "Please select a Contract Holder or Trading Partner.");
                        } else {
                            generateButtonLogic();
                        }
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());

                    }

                }
            });

            populate.addClickListener(new Button.ClickListener() {
                /**
                 * Default method.
                 */
                @Override
                public void buttonClick(final ClickEvent event) {
                    final boolean chValue = isContractCheckBoxSelected();
                    final boolean tpValue = isTpCheckBoxSelectd();

                    if (chValue || tpValue) {
                        populateButtonLogic(chValue, tpValue);
                    } else {
                        AbstractNotificationUtils.getErrorNotification("No Periods Selected",
                                "There are no historical periods selected from the Contract Holder History list view or the Trading Partner History list view.  "
                                + "\nPlease select at least one historical period and try again.");
                    }
                }

            });
            LOGGER.debug("End of configureFields method");
        }  catch (Property.ReadOnlyException | UnsupportedOperationException e) {
            LOGGER.error(e.getMessage());
        }

    }

    private boolean isContractCheckBoxSelected() {
        return !chtCheckBoxMap.isEmpty();
    }

    private boolean isTpCheckBoxSelectd() {
        return !tptCheckBoxMap.isEmpty();
    }

    /**
     * To check if any period of the trading partner is checked.
     *
     * @return true if any of the check box in trading partner header is checked
     */
    public boolean isTpHistorySelected() {
        LOGGER.debug("Entering isTpHistorySelected method");
        if (tradingPartner.getValue() != null && !tradingPartner.getValue().toString().isEmpty()) {
            return BooleanConstant.getTrueFlag();
        }
        LOGGER.debug("End of isTpHistorySelected method");
        return BooleanConstant.getFalseFlag();
    }

    /**
     * To check if any period of the contract holder is checked.
     *
     * @return true if any of the check box in contract holder header is checked
     */
    public boolean isContractHistorySelected() {

        LOGGER.debug("Entering isContractHistorySelected method");
        try {
            if (contractComboBox.getValue() != null && !contractComboBox.getValue().toString().isEmpty()) {
                return BooleanConstant.getTrueFlag();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("End of isContractHistorySelected method");
        return BooleanConstant.getFalseFlag();
    }

    /**
     * Adds the component to screen.
     *
     * @return Panel containing all the components
     */
    public Panel addToContent() {
        LOGGER.debug("Entering addToContent method");
        final Panel panel = new Panel();
        final VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.setMargin(true);
        content.addStyleName("pmpy");
        content.addComponent(addTradingPartnerInformation());
        content.addComponent(addSelectionCriteria());
        content.addComponent(getCustomerHistory());
        content.addComponent(excelTable1);
        content.addComponent(excelTable2);
        panel.setCaption(" PMPY Calculator ");
        panel.setContent(content);
        panel.setSizeFull();
        panel.setWidth("100%");
        LOGGER.debug("End of addToContent method");
        return panel;
    }

    /**
     * Adds the information section to the screen.
     *
     * @return panel containing the information section
     */
    private Panel addTradingPartnerInformation() {
        LOGGER.debug("Entering addTradingPartnerInformation method");
        final VerticalLayout vLayout = new VerticalLayout();
        final GridLayout gridLayout = new GridLayout(NumericConstants.SIX, 1);
        final Panel panel = new Panel();
        gridLayout.setSpacing(true);
        gridLayout.setMargin(false);
        gridLayout.addComponent(new Label("Contract Holder:"));
        gridLayout.addComponent(pmpyContractHolderField);
        gridLayout.addComponent(new Label("Trading Partner No:"));
        gridLayout.addComponent(tradingPartnerNoField);
        gridLayout.addComponent(new Label("Trading Partner Name:"));
        gridLayout.addComponent(tradingPartnerNameField);
        vLayout.setStyleName(Constant.WIDTH_AUTO);
        vLayout.addStyleName("pmpycriteria");
        gridLayout.setStyleName(Constant.ADJUST_LABEL);
        vLayout.addComponent(gridLayout);
        panel.setContent(vLayout);
        panel.setSizeFull();
        panel.setCaption("Trading Partner Information");
        LOGGER.debug("End of addTradingPartnerInformation method");

        return panel;
    }

    /**
     * Adds the data selection section to the screen.
     *
     * @return Panel containing data selection section
     */
    private Panel addSelectionCriteria() {
        final VerticalLayout vLayout = new VerticalLayout();
        final GridLayout gridLayout = new GridLayout(10, 1);
        final Panel panel = new Panel();
        gridLayout.setStyleName(Constant.ADJUST_LABEL);
        LOGGER.debug("Entering addSelectionCriteria method");
        gridLayout.setSpacing(true);
        gridLayout.setMargin(false);
        gridLayout.addComponent(new Label("Contract Holders:"));
        gridLayout.addComponent(contractComboBox);

        gridLayout.addComponent(new Label("Trading Partner:"));
        gridLayout.addComponent(tradingPartner);

        gridLayout.addComponent(new Label("Frequency:"));
        gridLayout.addComponent(frequencyField);

        gridLayout.addComponent(new Label("History:"));
        gridLayout.addComponent(historyField);

        gridLayout.addComponent(generate);
        gridLayout.addComponent(resetButton());

        vLayout.setStyleName(Constant.WIDTH_AUTO);
        vLayout.addStyleName("pmpycriteria");
        vLayout.addComponent(gridLayout);
        panel.setContent(vLayout);

        panel.setSizeFull();
        panel.setCaption("Selection Criteria");
        LOGGER.debug("End of addSelectionCriteria method");

        return panel;
    }

    /**
     * Adds the buttons to screen.
     *
     * @return Layout containing the buttons
     */
    private Component addButton() {
        final HorizontalLayout layout1 = new HorizontalLayout();

        LOGGER.debug("Entering addButton method");
        layout1.setSpacing(true);
        layout1.addComponent(btnResetCalculation);
        layout1.addComponent(btnimport);
        layout1.addComponent(getCloseButton());
        layout1.addComponent(excelExportCalculation);
        LOGGER.debug("End of addButton method");

        return layout1;
    }

    /**
     * Reset button.
     *
     * @return the button
     */
    public Button resetButton() {

        LOGGER.debug("Entering resetButton method");
        btnResetSearch.setWidth(Constant.WIDTH);
        btnResetSearch.addClickListener(new ClickListener() {
            /**
             * Default SerailVersionUID.
             */

            /**
             * Default method.
             */
            @Override
            public void buttonClick(final ClickEvent event) {

                MessageBox.showPlain(Icon.QUESTION, "Confirm Reset", "Are you sure you want to reset the page to default values?", new MessageBoxListener() {
                    /**
                     * Default method.
                     */

                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(Constant.YES)) {
                            // reset the fields here
                            contractComboBox.setValue(null);
                            tradingPartner.setValue(StringUtils.EMPTY);

                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }
        });
        LOGGER.debug("End of resetButton method");

        return btnResetSearch;

    }

    /**
     * Gets the customer history.
     *
     * @return the customer history
     */
    private Panel getCustomerHistory() {
        final Panel panel = new Panel();
        final VerticalLayout content = new VerticalLayout();

        LOGGER.debug("Entering getCustomerHistory method");
        content.setSpacing(true);
        content.setMargin(false);
        content.addComponent(getResultLayout());
        panel.setContent(content);
        panel.setSizeFull();
        panel.setWidth("100%");
        LOGGER.debug("End of getCustomerHistory method");

        return panel;
    }

    /**
     * Gets the results.
     *
     * @return the results
     */
    private VerticalLayout getResults() {

        final VerticalLayout content1 = new VerticalLayout();
        final VerticalLayout table2 = new VerticalLayout();
        LOGGER.debug("Entering getResults method");
        content1.setSpacing(true);
        content1.setMargin(false);

        final VerticalLayout table1 = new VerticalLayout();
        table1.setCaption("Contract Holder History");

        table1.addComponent(contractHolderTable);
        table1.addComponent(addChExportButtons());
        content1.addComponent(table1);
        contractHolderTable.setHeight(Constant.TWO_HUNDRED_PX);
        contractHolderTable.getLeftFreezeAsTable().setHeight(Constant.TWO_HUNDRED_PX);
        contractHolderTable.getRightFreezeAsTable().setHeight(Constant.TWO_HUNDRED_PX);
        tradingHistoryTable.setHeight(Constant.TWO_HUNDRED_PX);
        tradingHistoryTable.getLeftFreezeAsTable().setHeight(Constant.TWO_HUNDRED_PX);
        tradingHistoryTable.getRightFreezeAsTable().setHeight(Constant.TWO_HUNDRED_PX);
        table2.setCaption("Trading Partner History");
        table2.addComponent(tradingHistoryTable);
        table2.addComponent(addTpExportButtons());
        content1.addComponent(table2);
        table1.setMargin(false);
        table2.setMargin(false);

        content1.addComponent(getEffectiveDateGrid());
        LOGGER.debug("End of getResults method");

        return content1;

    }

    /**
     * Gets the effective date grid.
     *
     * @return the effective date grid
     */
    private HorizontalLayout getEffectiveDateGrid() {
        final HorizontalLayout grid = new HorizontalLayout();

        LOGGER.debug("Entering getEffectiveDateGrid method");
        grid.setSpacing(true);
        grid.setMargin(false);
        grid.addComponent(new Label("Effective Period"));
        grid.addComponent(effectivePeriod);
        grid.addStyleName("float-right");
        LOGGER.debug("End of getEffectiveDateGrid method");

        return grid;

    }

    /**
     * Gets the result layout.
     *
     * @return the result layout
     */
    private GridLayout getResultLayout() {
        final GridLayout content = new GridLayout(NumericConstants.THREE, 1);
        final VerticalLayout verticalContent = new VerticalLayout();
        content.setSpacing(true);
        LOGGER.debug("Entering getResultLayout method");
        content.addComponent(getResults());
        content.addComponent(new Label(Constant.ADD_SPACE, ContentMode.HTML));
        verticalContent.addComponent(getCalculatorPanel());
        verticalContent.addComponent(addButton());
        content.addComponent(verticalContent);
        LOGGER.debug("End of getResultLayout method");

        return content;
    }

    /**
     * Gets the calculator panel.
     *
     * @return the calculator panel
     */
    private Panel getCalculatorPanel() {
        final Panel panel = new Panel();

        LOGGER.debug("Entering getCalculatorPanel method");
        panel.setCaption("Calculator");
        panel.setContent(getCalculator());

        LOGGER.debug("End of getCalculatorPanel method");

        return panel;
    }

    /**
     * Gets the calculator.
     *
     * @return the calculator
     */
    private VerticalLayout getCalculator() {
        final VerticalLayout content2 = new VerticalLayout();
        content2.setStyleName(Constant.ADJUST_LABEL);
        LOGGER.debug("Entering getCalculator method");
        content2.setSpacing(true);
        content2.setMargin(true);
        content2.addComponent(getCalculatorlayout());
        content2.addComponent(getCalculatorlayout1());
        LOGGER.debug("End of getCalculator method");

        return content2;

    }

    /**
     * Gets the calculatorlayout.
     *
     * @return the calculatorlayout
     */
    private GridLayout getCalculatorlayout() {
        final GridLayout layout3 = new GridLayout(NumericConstants.FOUR, 1);

        LOGGER.debug("Entering getCalculatorlayout method");
        layout3.setSpacing(true);
        salesOrUnitsGroup.setStyleName(Constant.HORIZONTAL);
        layout3.addComponent(new Label("Variable"));
        layout3.addComponent(salesOrUnitsGroup);
        layout3.addComponent(populate);
        LOGGER.debug("End of getCalculatorlayout method");

        return layout3;
    }

    /**
     * Gets the calculatorlayout1.
     *
     * @return the calculatorlayout1
     */
    private GridLayout getCalculatorlayout1() {
        final GridLayout layout = new GridLayout(NumericConstants.THREE, NumericConstants.EIGHT);

        LOGGER.debug("Entering getCalculatorlayout1 method");
        layout.setSpacing(true);
        layout.addComponent(new Label("Sales/Units:"));
        layout.addComponent(salesField);
        salesField.addStyleName(Constant.TXT_RIGHT_ALIGN);
        layout.addComponent(new Label(StringUtils.EMPTY));
        layout.addComponent(new Label("Market Share (%):"));
        layout.addComponent(marketShareField);
        marketShareField.addStyleName(Constant.TXT_RIGHT_ALIGN);
        marketShareField.addValidator(new RegexpValidator("^(0*100{1,1}\\.?((?<=\\.)0*)?%?$)|(^0*\\d{0,2}\\.?((?<=\\.)\\d{0,1})?%?)$", CORRECTVALUE));
        layout.addComponent(new Label(StringUtils.EMPTY));
        layout.addComponent(new Label("Analog Lives:"));
        layout.addComponent(analogLivesField);
        analogLivesField.addStyleName(Constant.TXT_RIGHT_ALIGN);
        analogLivesField.addValidator(new RegexpValidator("\\d*", CORRECTVALUE));
        layout.addComponent(calculateOne);
        layout.addComponent(new Label("Value Per Life"));
        layout.addComponent(valuePerLifeField);
        valuePerLifeField.addStyleName(Constant.TXT_RIGHT_ALIGN);
        layout.addComponent(new Label(StringUtils.EMPTY));
        layout.addComponent(new Label("Market Share (%):"));
        layout.addComponent(marketShare1);
        marketShare1.addStyleName(Constant.TXT_RIGHT_ALIGN);
        marketShareField.addValidator(new RegexpValidator("^(0*100{1,1}\\.?((?<=\\.)0*)?%?$)|(^0*\\d{0,2}\\.?((?<=\\.)\\d{0,1})?%?)$", CORRECTVALUE));
        layout.addComponent(new Label(StringUtils.EMPTY));
        layout.addComponent(new Label("Projected Lives: "));
        layout.addComponent(projectedLivesField);
        projectedLivesField.addStyleName(Constant.TXT_RIGHT_ALIGN);
        projectedLivesField.addValidator(new RegexpValidator("\\d*", CORRECTVALUE));
        layout.addComponent(calculateTwo);
        layout.addComponent(new Label("Total Sales/Units:"));
        layout.addComponent(totalSalesField);
        totalSalesField.addStyleName(Constant.TXT_RIGHT_ALIGN);
        layout.addComponent(new Label(StringUtils.EMPTY));
        layout.addComponent(new Label("Projection Period Total"));
        layout.addComponent(projectionPeriodTotalField);
        projectionPeriodTotalField.addStyleName(Constant.TXT_RIGHT_ALIGN);
        layout.addComponent(new Label(StringUtils.EMPTY));
        LOGGER.debug("End of getCalculatorlayout1 method");

        return layout;
    }

    /**
     * Gets the close button.
     *
     * @return the close button
     */
    public Button getCloseButton() {
        LOGGER.debug("Entering getCloseButton method");
        final Button btnRemove = new Button("CLOSE");

        btnRemove.setErrorHandler(new ErrorHandler() {
            /**
             * Default method.
             */

            @Override
            public void error(final com.vaadin.server.ErrorEvent event) {
                LOGGER.debug("Inside overriden method: Do nothing");
            }
        });
        btnRemove.addClickListener(new ClickListener() {

            /**
             * Default method.
             */
            @Override
            public void buttonClick(final ClickEvent event) {

                MessageBox.showPlain(Icon.QUESTION, "Close Confirmation", "Are you sure you want to Close the Calculator" + " ?", new MessageBoxListener() {
                    /**
                     * Default method.
                     */

                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(Constant.YES)) {
                            close();
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });

        LOGGER.debug("End of getCloseButton method");
        return btnRemove;
    }

    /**
     * Load contract holder.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void loadContractHolder()  {
        LOGGER.debug("Entering loadContractHolder method");
        contractComboBox.setNullSelectionAllowed(BooleanConstant.getTrueFlag());
        contractComboBox.setNullSelectionItemId(Constant.SELECT_ONE);
        contractComboBox.addItem(Constant.SELECT_ONE);
        contractComboBox.select(Constant.SELECT_ONE);
        contractComboBox = nonMandatedLogic.loadPMPYContractHolders(contractComboBox);
        LOGGER.debug("End of loadContractHolder method");
    }

    /**
     * Generate button click logic.
     */
    private void generateButtonLogic()  {
        LOGGER.debug("Entering generateButtonLogic method");
        chTreeContainer.removeAllItems();
        tpTreeContainer.removeAllItems();
        SalesProjectionLogic logic = new SalesProjectionLogic();
        if (isContractHistorySelected()) {
            Object[] contractInputs = new Object[NumericConstants.FIVE];
            contractInputs[0] = String.valueOf(projectionId);
            contractInputs[1] = CommonUtils.getListToString(projectionDetailsId);
            contractInputs[NumericConstants.TWO] = String.valueOf(contractComboBox.getValue() == null ? 0 : contractComboBox.getValue());
            contractInputs[NumericConstants.THREE] = String.valueOf(0);
            contractInputs[NumericConstants.FOUR] = rightDto.getDoubleHistoryColumns();
            chTreeContainer.addAll(logic.getPMPYResultList(contractInputs));
        }
        if (isTpHistorySelected()) {

            Object[] trPInputs = new Object[NumericConstants.FIVE];
            trPInputs[0] = String.valueOf(projectionId);
            trPInputs[1] = CommonUtils.getListToString(projectionDetailsId);
            trPInputs[NumericConstants.TWO] = String.valueOf(0);
            trPInputs[NumericConstants.THREE] = String.valueOf(tradingPartner.getData() == null ? 0 : tradingPartner.getData());
            trPInputs[NumericConstants.FOUR] = rightDto.getDoubleHistoryColumns();
            tpTreeContainer.addAll(logic.getPMPYResultList(trPInputs));

        }
        LOGGER.debug("End of generateButtonLogic method");

    }

    /**
     * Adds the ch table option grp.
     *
     * @param history the history
     * @return the grid layout
     */
    /**
     * Populate button logic.
     *
     * @param history the history
     */
    private void populateButtonLogic(boolean chValue, boolean tpValue) {

        LOGGER.debug("Entering populateButtonLogic method");
        calculatedSalesVal = 0.0;
        calculatedUnitsVal = 0.0;

        if (chValue && tpValue) {
            AbstractNotificationUtils.getErrorNotification("Periods in Both List Views Selected",
                    "Historical periods can only be selected from either the Contract Holder list view or the Trading Partner list view.  " + "\nPlease try again.");
            return;
        } else {
            if (chValue) {
                PMPYRowDto actualSaleDto = null;
                PMPYRowDto actualUnitDto = null;
                for (PMPYRowDto dto : chTreeContainer.getBeans()) {
                    if (dto.getType().equals(Constant.SALES_SMALL)) {
                        actualSaleDto = dto;
                    } else if (dto.getType().equals(Constant.UNITS_SMALL)) {
                        actualUnitDto = dto;
                    }
                }

                Double salesValue;
                Double unitsValue;
                for (Object key : chtCheckBoxMap) {

                    salesValue = Double.valueOf(String.valueOf(actualSaleDto.getProperties().get(key)).replace(",", StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY));
                    unitsValue = Double.valueOf(String.valueOf(actualUnitDto.getProperties().get(key)).replace(",", StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY));
                    calculatedSalesVal += salesValue;
                    calculatedUnitsVal += unitsValue;
                }

            } else if (tpValue) {
                PMPYRowDto actualSaleDto = null;
                PMPYRowDto actualUnitDto = null;
                for (PMPYRowDto dto : tpTreeContainer.getBeans()) {
                    if (dto.getType().equals(Constant.SALES_SMALL)) {
                        actualSaleDto = dto;
                    } else if (dto.getType().equals(Constant.UNITS_SMALL)) {
                        actualUnitDto = dto;
                    }
                }
                Double salesValue;
                Double unitsValue;
                for (Object key : tptCheckBoxMap) {
                    salesValue = Double.valueOf(String.valueOf(actualSaleDto.getProperties().get(key)).replace(",", StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY));
                    unitsValue = Double.valueOf(String.valueOf(actualUnitDto.getProperties().get(key)).replace(",", StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY));
                    calculatedSalesVal += salesValue;
                    calculatedUnitsVal += unitsValue;
                }

            }

            if (salesOrUnitsGroup.getValue() != null) {
                if (Constant.SALES_DOLLARS.equalsIgnoreCase(String.valueOf(salesOrUnitsGroup.getValue()))) {
                    salesField.setValue(MONEY_NO_DECIMAL.format(calculatedSalesVal));
                } else if (Constant.UNIT_VOLUME.equalsIgnoreCase(String.valueOf(salesOrUnitsGroup.getValue()))) {
                    salesField.setValue(dfCalculatedUnit.format(calculatedUnitsVal));
                }
            }
        }
        LOGGER.debug("End of populateButtonLogic method");

    }

    /**
     * Gets the SALES_SMALL.
     *
     * @param obj the obj
     * @return the SALES_SMALL
     */
    public Double getSales(final Object obj) {
        LOGGER.debug("Entering of getSales method");
        Double doubleValue;

        doubleValue = Double.valueOf(String.valueOf(obj));
        dfSales.format(doubleValue);
        LOGGER.debug("End of getSales method");

        return doubleValue;
    }

    /**
     * Gets the UNITS_SMALL.
     *
     * @param obj the obj
     * @return the UNITS_SMALL
     */
    public Double getUnits(final Object obj) {

        LOGGER.debug("Entering of getUnits method");
        final Double value = Double.valueOf(String.valueOf(obj));
        dfUnits.format(value);
        LOGGER.debug("End of getUnits method");

        return value;
    }

    /**
     * Adds the ch export buttons.
     *
     * @return the horizontal layout
     */
    private HorizontalLayout addChExportButtons() {
        final HorizontalLayout exportButtons = new HorizontalLayout();

        LOGGER.debug("Entering of addChExportButtons method");
        exportButtons.addComponentAsFirst(space);
        exportButtons.setSpacing(true);
        exportButtons.addComponent(totalLivesChartCh);
        exportButtons.addComponent(excelExportCh);
        LOGGER.debug("End of addChExportButtons method");

        return exportButtons;
    }

    /**
     * Adds the tp export buttons.
     *
     * @return the horizontal layout
     */
    private HorizontalLayout addTpExportButtons() {
        final HorizontalLayout exportButtons = new HorizontalLayout();

        LOGGER.debug("Entering of addTpExportButtons method");
        exportButtons.addComponentAsFirst(space);
        exportButtons.setSpacing(true);
        exportButtons.addComponent(totalLivesChartTp);
        exportButtons.addComponent(excelExportTp);
        LOGGER.debug("End of addTpExportButtons method");

        return exportButtons;
    }

    /**
     * First calculation.
     */
    private void firstCalculation() {
        LOGGER.debug("Entering of firstCalculation method");

        Double analogLives;

        if (analogLivesField.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(analogLivesField.getValue())) && !Constant.NULL.equals(String.valueOf(analogLivesField.getValue()))
                && isNumeric(String.valueOf(analogLivesField.getValue())) && Double.parseDouble(String.valueOf(analogLivesField.getValue())) != 0.0) {
            analogLives = Double.valueOf(String.valueOf(analogLivesField.getValue()));
        } else {
            AbstractNotificationUtils.getErrorNotification("No Lives", "Please enter a numeric value for Lives.");
            valuePerLifeField.setValue(StringUtils.EMPTY);
            return;
        }
        final String tempSales = String.valueOf(salesField.getValue()).replace(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY);
        Double salesVal = 1.0;
        if (salesField.getValue() != null && !StringUtils.EMPTY.equals(tempSales) && !Constant.NULL.equals(tempSales) && isNumeric(tempSales) && Double.parseDouble(tempSales) != 0.0) {

            if (Constant.SALES.equalsIgnoreCase(getVariableValue())) {
                final DecimalFormat salesFormat = new DecimalFormat("####");
                salesVal = Double.valueOf(salesFormat.format(Double.valueOf(tempSales)));

            } else if (Constant.UNITS.equalsIgnoreCase(getVariableValue())) {
                final DecimalFormat decFormat = new DecimalFormat(Constant.SINGLE_DECIMAL_FORMAT);
                salesVal = Double.valueOf(decFormat.format(Double.valueOf(tempSales)));

            }
        } else {
            valuePerLifeField.setValue(StringUtils.EMPTY);
        }
        Double marketShareValue;

        if (marketShareField.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(marketShareField.getValue())) && !Constant.NULL.equals(String.valueOf(marketShareField.getValue()))
                && isNumeric(String.valueOf(marketShareField.getValue())) && Double.parseDouble(String.valueOf(marketShareField.getValue()).replace(Constant.PERCENT, StringUtils.EMPTY)) != 0.0) {
            marketShareValue = Double.valueOf(String.valueOf(marketShareField.getValue()).replace(Constant.PERCENT, StringUtils.EMPTY));
        } else {
            valuePerLifeField.setValue(StringUtils.EMPTY);
            return;
        }

        final Double salesByMarketshareValue = salesVal / (marketShareValue / 100);
        final Double valuePerLifeValue = salesByMarketshareValue / analogLives;
        if (Constant.SALES.equalsIgnoreCase(getVariableValue())) {

            valuePerLifeField.setValue(String.valueOf(MONEY_TWO_DECIMAL.format(valuePerLifeValue)));
        } else if (Constant.UNITS.equalsIgnoreCase(getVariableValue())) {
            valuePerLifeField.setValue(String.valueOf(MONEY_TWO_DECIMAL.format(valuePerLifeValue)));
        }
        LOGGER.debug("End of firstCalculation method");

    }

    /**
     * Second calculation.
     */
    private void secondCalculation() {
        LOGGER.debug("Entering of secondCalculation method");

        Double projectedLivesValue;

        if (projectedLivesField.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(projectedLivesField.getValue())) && !Constant.NULL.equals(String.valueOf(projectedLivesField.getValue()))
                && isNumeric(String.valueOf(projectedLivesField.getValue())) && Double.parseDouble(String.valueOf(projectedLivesField.getValue())) != 0.0) {
            projectedLivesValue = Double.valueOf(String.valueOf(projectedLivesField.getValue()));
        } else {
            AbstractNotificationUtils.getErrorNotification("No Total Lives", "Please enter a numeric value for Total Lives.");
            totalSalesField.setValue(StringUtils.EMPTY);
            projectionPeriodTotalField.setValue(StringUtils.EMPTY);
            return;
        }
        final String tempValuePerLifeValue = String.valueOf(valuePerLifeField.getValue()).replace(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY);

        double valuePerLifeValue = 1.0;

        if (valuePerLifeField.getValue() != null && !StringUtils.EMPTY.equals(tempValuePerLifeValue) && !Constant.NULL.equals(tempValuePerLifeValue) && isNumeric(tempValuePerLifeValue)
                && Double.parseDouble(tempValuePerLifeValue) != 0.0) {

            if (Constant.SALES.equalsIgnoreCase(getVariableValue())) {

                valuePerLifeValue = Double.parseDouble(tempValuePerLifeValue);
            } else if (Constant.UNITS.equalsIgnoreCase(getVariableValue())) {

                valuePerLifeValue = Double.parseDouble(tempValuePerLifeValue);
            }
        } else {
            totalSalesField.setValue(StringUtils.EMPTY);
            projectionPeriodTotalField.setValue(StringUtils.EMPTY);
            return;
        }

        Double marketShareValue;

        if (marketShare1.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(marketShare1.getValue())) && !Constant.NULL.equals(String.valueOf(marketShare1.getValue()))
                && isNumeric(String.valueOf(marketShare1.getValue()))) {
            marketShareValue = Double.valueOf(String.valueOf(marketShare1.getValue()).replace(Constant.PERCENT, StringUtils.EMPTY));
        } else {
            totalSalesField.setValue(StringUtils.EMPTY);
            projectionPeriodTotalField.setValue(StringUtils.EMPTY);
            return;
        }

        Double projectionPeriodTotalValue;
        final Double totalValue = (valuePerLifeValue * (marketShareValue / 100)) * projectedLivesValue;
        if (Constant.SALES.equalsIgnoreCase(getVariableValue())) {
            totalSalesField.setValue(String.valueOf(MONEY_NO_DECIMAL.format(totalValue)));
            projectionPeriodTotalValue = projectionPeriodTotalCalculation(totalValue);
            projectionPeriodTotalField.setValue(String.valueOf(MONEY_NO_DECIMAL.format(projectionPeriodTotalValue)));
        } else if (Constant.UNITS.equalsIgnoreCase(getVariableValue())) {
            totalSalesField.setValue(String.valueOf(dfCalculatedUnit.format(totalValue)));
            projectionPeriodTotalValue = projectionPeriodTotalCalculation(totalValue);
            projectionPeriodTotalField.setValue(String.valueOf(dfCalculatedUnit.format(projectionPeriodTotalValue)));
        }
        LOGGER.debug("End of secondCalculation method");

    }

    /**
     * Projection period total calculation.
     *
     * @param totalSales the total SALES_SMALL
     * @return the double
     */
    public Double projectionPeriodTotalCalculation(Double totalSales) {
        LOGGER.debug("Entering of projectionPeriodTotalCalculation method");
        Double totalSalesValue;
        int count = 1;
        if (isContractCheckBoxSelected()) {
            count = chtCheckBoxMap.size();
        } else if (isTpCheckBoxSelectd()) {
            count = tptCheckBoxMap.size();
        }
        totalSalesValue = totalSales / count;
        LOGGER.debug("End of projectionPeriodTotalCalculation method");

        return totalSalesValue;
    }

    /**
     * Checks if is numeric.
     *
     * @param str the str
     * @return true, if checks if is numeric
     */
    public boolean isNumeric(final String str) {
        LOGGER.debug("Entering of isNumeric method with str= {}" , str);
        str.replace(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY);
        LOGGER.debug("End of isNumeric method");
        return true;
    }

    /**
     * Gets the variable value.
     *
     * @return the variable value
     */
    public String getVariableValue() {
        String variableValue = StringUtils.EMPTY;

        LOGGER.debug("Entering of getVariableValue method");
        if (salesOrUnitsGroup.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(salesOrUnitsGroup.getValue())) && !Constant.NULL.equals(String.valueOf(salesOrUnitsGroup.getValue()))
                && Constant.SALES_DOLLARS.equalsIgnoreCase(String.valueOf(salesOrUnitsGroup.getValue()))) {
            variableValue = Constant.SALES;
        }
        if (salesOrUnitsGroup.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(salesOrUnitsGroup.getValue())) && !Constant.NULL.equals(String.valueOf(salesOrUnitsGroup.getValue()))
                && Constant.UNIT_VOLUME.equalsIgnoreCase(String.valueOf(salesOrUnitsGroup.getValue()))) {
            variableValue = Constant.UNITS;

        }
        LOGGER.debug("End of getVariableValue method");

        return variableValue;
    }

    /**
     * Import button logic.
     *
     * @throws IllegalAccessException the illegal access exception
     * @throws InvocationTargetException the invocation target exception
     * @throws NoSuchMethodException the no such method exception
     */
    public void importButtonLogic() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object[] inputs = new Object[NumericConstants.NINE];
        final SalesProjectionLogic logic = new SalesProjectionLogic();
        String stringValue = String.valueOf(projectionPeriodTotalField.getValue());

        LOGGER.debug("Entering of importButtonLogic method");
        if (StringUtils.EMPTY.equals(stringValue) || Constant.NULL.equals(stringValue)) {
            AbstractNotificationUtils.getErrorNotification("No Projection Period Total", "Please complete the PMPY calculation before clicking IMPORT.");
            return;
        }

        final String variableVal = getVariableValue();

        stringValue = stringValue.replace(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY);

        if (Constant.SALES.equalsIgnoreCase(variableVal)) {
            final DecimalFormat salesDecimalFormat = new DecimalFormat("####");
            stringValue = String.valueOf(salesDecimalFormat.format(Double.valueOf(stringValue)));
        } else if (Constant.UNITS.equalsIgnoreCase(variableVal)) {
            final DecimalFormat unitDecimalFormat = new DecimalFormat(Constant.SINGLE_DECIMAL_FORMAT);
            stringValue = String.valueOf(unitDecimalFormat.format(Double.valueOf(stringValue)));
        }
        if (effectivePeriod.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("No Effective Date", "Please select an Effective Period.");
            return;
        }
        final String finalStringValue = stringValue;

        if (StringUtils.EMPTY.equals(finalStringValue) || Constant.NULL.equals(finalStringValue)) {
            AbstractNotificationUtils.getErrorNotification("No Projection Period Total", "Please complete the PMPY calculation before clicking IMPORT.");
        } else {
            String tempStringValue = StringUtils.EMPTY;
            final int currentYearValue = calendar.get(Calendar.YEAR);
            final int yearOneVal = currentYearValue + 1;
            final int yearTwoVal = currentYearValue + NumericConstants.TWO;
            final int yearThreeVal = currentYearValue + NumericConstants.THREE;
            String queryNameString = StringUtils.EMPTY;
            final int[] yearArray = new int[]{currentYearValue, yearOneVal, yearTwoVal, yearThreeVal};
            
            int annualValue;
            Double calculatedDoubleValue = 0.0;

            if (Constant.SALES.equals(variableVal)) {
                tempStringValue = Constant.SALES_CAPS;
                queryNameString = "Check GTS Sales Values";
                calculatedDoubleValue = Double.valueOf(dfSales.format(Double.valueOf(finalStringValue)));
            }
            if (Constant.UNITS.equals(variableVal)) {
                tempStringValue = Constant.UNITS_SMALL;
                queryNameString = "Check GTS UNT Values";
                calculatedDoubleValue = Double.valueOf(dfUnits.format(Double.valueOf(finalStringValue)));
            }
            calculatedDoubleValue = getDividedValue(calculatedDoubleValue);
            int startQuatorValue = effectivePeriod.getValue().toString().charAt(1) - NumericConstants.FORTY_EIGHT;
            annualValue = Integer.parseInt(effectivePeriod.getValue().toString().substring(NumericConstants.THREE, NumericConstants.SEVEN));
            inputs[0] = projectionDetailsId;
            inputs[1] = calculatedDoubleValue;
            inputs[NumericConstants.TWO] = annualValue;
            inputs[NumericConstants.THREE] = startQuatorValue;
            inputs[NumericConstants.FOUR] = tempStringValue;

            List inputList = new ArrayList();
            inputList.add(startQuatorValue);
            inputList.add(annualValue);
            inputList.add(session.getSessionId());
            inputList.add(session.getUserId());
            inputList.add(calculatedDoubleValue);
            inputList.add(CommonUtils.getListToString((List) projectionDetailsId));

            Double gtsDoubleValue = Double.valueOf(PPAQuerys.getPPAData(inputList, queryNameString, null).get(0).toString());
            if (gtsDoubleValue == 0) {
                List input1List = new ArrayList();
                List NDCList = new ArrayList();
                input1List.add(CommonUtils.getListToString((List) inputs[0]));
                List<String> ndcList = PPAQuerys.getPPAData(input1List, "Get NDC Values", null);
                for (String str : ndcList) {
                    NDCList.add(str == null ? StringUtils.EMPTY : str);
                }
                MessageBox.showPlain(Icon.INFO, "Error", "The following NDC's " + CommonUtils.getListToString(NDCList) + "is/are not on the current GTS File", ButtonId.OK);

            } else {
                MessageBox.showPlain(Icon.QUESTION, "Replace sales confirmation", "Are you sure you want to use the calculated Sales/Units value for the selected Trading Partner" + " ?",
                        new MessageBoxListener() {
                            /**
                             * Default method.
                             */

                            @Override
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(Constant.YES)) {
                                    try {

                                        logic.importPMPY(inputs, session);
                                        importEvent = true;
                                        close();

                                    } catch (Exception e) {
                                        importEvent = false;
                                        LOGGER.error(e.getMessage());
                                    }
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
            }

        }

        LOGGER.debug("End of importButtonLogic method");
    }

    /**
     * Gets the bean from id.
     *
     * @param identifier the identifier
     * @return the bean from id
     */
    public SalesProjectionResultsDTO getBeanFromId(final Object identifier) {
        BeanItem<?> targetItem = null;

        LOGGER.debug("Entering of getBeanFromId method");
        if (identifier instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) identifier;
        } else if (identifier instanceof SalesProjectionResultsDTO) {
            targetItem = new BeanItem<>((SalesProjectionResultsDTO) identifier);
        }
        LOGGER.debug("End of getBeanFromId method");

        return (SalesProjectionResultsDTO) targetItem.getBean();
    }

    /**
     * Export calculated excel.
     */
    private void exportCalculatedExcel() {
        try {
            LOGGER.debug("Entering of exportCalculatedExcel method");
            final PMPYCalculationExporterDTO exportDto = new PMPYCalculationExporterDTO();
            final PMPYCalculationExporter exporterObject = new PMPYCalculationExporter();

            exportDto.setVariable(String.valueOf(getVariableValue()));
            exportDto.setSales(String.valueOf(salesField.getValue()));
            exportDto.setMarketShare(String.valueOf(marketShareField.getValue()));
            exportDto.setAnalogLives(String.valueOf(analogLivesField.getValue()));
            exportDto.setValuePerLife(String.valueOf(valuePerLifeField.getValue()));
            exportDto.setMarketShare1(String.valueOf(marketShare1.getValue()));
            exportDto.setProjectedLives(String.valueOf(projectedLivesField.getValue()));
            exportDto.setTotalSales(String.valueOf(totalSalesField.getValue()));
            exportDto.setProjectionPeriodTotal(String.valueOf(projectionPeriodTotalField.getValue()));

            exporterObject.export(exportDto);
            LOGGER.debug("End of exportCalculatedExcel method");
        } catch (SystemException e) {

            LOGGER.error(e.getMessage());

        } catch (Exception e) {

            LOGGER.error(e.getMessage());

        }
    }

    /**
     * Gets the percentage.
     *
     * @param percentage the percentage
     * @return the percentage
     */

    /**
     * Gets the divided value.
     *
     * @param actualValue the actual value
     * @return the divided value
     */
    public double getDividedValue(final Double actualValue) {
        double returnDoubleValue = 0;

        LOGGER.debug("Entering of getDividedValue method");
        final DecimalFormat formatter = new DecimalFormat("#.000000");
        if (actualValue != Constant.ZERO) {
            Double temp = actualValue;

            temp = Double.valueOf(formatter.format(temp));
            returnDoubleValue = temp.isInfinite() || temp.isNaN() ? 0 : temp;

        }
        LOGGER.debug("End of getDividedValue method");

        return returnDoubleValue;
    }

    public void configureAttributes() {

        historyField.setValue(historyPeriods);
        frequencyField.setValue("Quaterly");
        for (Object obj : rightDto.getDoubleHistoryColumns()) {
            visiColumn.add(obj);
        }
        for (Object obj : rightDto.getDoubleHistoryHeaders()) {
            visiHeaders.add(String.valueOf(obj));
        }

        tradingHistoryTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        tradingHistoryTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        tradingHistoryTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);

        contractHolderTable.setSplitPosition(splitPosition, Sizeable.Unit.PIXELS);
        contractHolderTable.setMinSplitPosition(minSplitPosition, Sizeable.Unit.PIXELS);
        contractHolderTable.setMaxSplitPosition(maxSplitPosition, Sizeable.Unit.PIXELS);
     
        contractHolderTable.setWidth(NumericConstants.NINE_HUNDRED, UNITS_PIXELS);
        tradingHistoryTable.setWidth(NumericConstants.NINE_HUNDRED, UNITS_PIXELS);
        contractHolderTable.markAsDirty();
        tradingHistoryTable.markAsDirty();
        chTreeContainer = new ExtTreeContainer<>(PMPYRowDto.class,ExtContainer.DataStructureMode.MAP);
        tpTreeContainer = new ExtTreeContainer<>(PMPYRowDto.class,ExtContainer.DataStructureMode.MAP);

        tradingHistoryTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractHolderTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        Map<Object, Class> properties = new HashMap<>();
        properties.put("type", String.class);
        for (Object obj : visiColumn) {
            properties.put(String.valueOf(obj), String.class);

        }
        chTreeContainer.setColumnProperties(properties);
        tpTreeContainer.setColumnProperties(properties);
        contractHolderTable.setContainerDataSource(chTreeContainer);
        tradingHistoryTable.setContainerDataSource(tpTreeContainer);
        contractHolderTable.getLeftFreezeAsTable().setVisibleColumns(new Object[]{"type"});
        contractHolderTable.getLeftFreezeAsTable().setColumnHeaders(new String[]{"Group"});
        contractHolderTable.getRightFreezeAsTable().setVisibleColumns(visiColumn.toArray());
        contractHolderTable.getRightFreezeAsTable().setColumnHeaders(visiHeaders.toArray(new String[visiHeaders.size()]));

        tradingHistoryTable.getLeftFreezeAsTable().setVisibleColumns(new Object[]{"type"});
        tradingHistoryTable.getLeftFreezeAsTable().setColumnHeaders(new String[]{"Group"});
        tradingHistoryTable.getRightFreezeAsTable().setVisibleColumns(visiColumn.toArray());
        tradingHistoryTable.getRightFreezeAsTable().setColumnHeaders(visiHeaders.toArray(new String[visiHeaders.size()]));

        excelTable1.setVisible(false);
        excelTable2.setVisible(false);
        for (Object obj : visiColumn) {
            contractHolderTable.getRightFreezeAsTable().setColumnCheckBox(obj, true);
            tradingHistoryTable.getRightFreezeAsTable().setColumnCheckBox(obj, true);

            contractHolderTable.getRightFreezeAsTable().setColumnWidth(obj, NumericConstants.ONE_THREE_ZERO);
            tradingHistoryTable.getRightFreezeAsTable().setColumnWidth(obj, NumericConstants.ONE_THREE_ZERO);
        }
        alignRight(contractHolderTable, tradingHistoryTable);
        contractHolderTable.getRightFreezeAsTable().addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                if (event.isChecked()) {
                    chtCheckBoxMap.add(event.getPropertyId().toString());
                } else {
                    chtCheckBoxMap.remove(event.getPropertyId());

                }
            }
        });

        tradingHistoryTable.getRightFreezeAsTable().addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                if (event.isChecked()) {
                    tptCheckBoxMap.add(event.getPropertyId().toString());

                } else {

                    tptCheckBoxMap.remove(event.getPropertyId());
                }
            }
        });

    }

    private void alignRight(FreezeFilterTable table, FreezeFilterTable table1) {
        for (Object obj : visiColumn) {
            table.getRightFreezeAsTable().setColumnAlignment(obj, ExtCustomTable.Align.RIGHT);
            table1.getRightFreezeAsTable().setColumnAlignment(obj, ExtCustomTable.Align.RIGHT);
        }
    }

    public boolean isImportEvent() {
        return importEvent;
    }

    private void loadEffectivePeriods() {

        effectivePeriod.addItem(Constant.SELECT_ONE);
        effectivePeriod.addItems(doubleProjectedColumns);
        effectivePeriod.select(Constant.SELECT_ONE);
    }

}
