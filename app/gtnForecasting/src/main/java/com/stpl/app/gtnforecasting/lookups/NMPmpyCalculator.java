/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.lookups;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.dto.PMPYCalculationExporterDTO;
import com.stpl.app.gtnforecasting.dto.PMPYCalculatorDTO;
import com.stpl.app.gtnforecasting.dto.PMPYRowDto;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.lookups.logic.PmpyLogic;
import com.stpl.app.gtnforecasting.queryUtils.PPAQuerys;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.ui.NmSalesGraphWindow;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.app.gtnforecasting.utils.PMPYCalculationExporter;
import com.stpl.app.gtnforecasting.utils.PMPYContractHolderHistoryChart;
import com.stpl.app.gtnforecasting.utils.PMPYTradingPartnerHistoryChart;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.utils.Constants.ResourceConstants.GRAPH_IMAGE_PATH;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
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
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class NMPmpyCalculator.
 *
 */
public class NMPmpyCalculator extends Window {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(NMPmpyCalculator.class);

    /**
     * The space.
     */

    CustomTableHeaderDTO rightDto = new CustomTableHeaderDTO();

    @UiField("effectivePeriod")
    private ComboBox effectivePeriod;

    @UiField("btnResetSearch")
    private Button btnResetSearch;

    @UiField("pmpyContractHolder")
    private TextField pmpyContractHolder;

    @UiField("tradingPartnerNo")
    private TextField tradingPartnerNo;

    @UiField("tradingPartnerName")
    private TextField tradingPartnerName;

    @UiField("contract")
    private ComboBox contract;

    @UiField("frequency")
    private TextField frequency;

    @UiField("history")
    private TextField history;

    @UiField("tradingPartner")
    private CustomTextField tradingPartner;

    @UiField("generate")
    private Button generate;

    @UiField("btnResetCalculation")
    private Button btnResetCalculation;

    @UiField("btnimport")
    private Button btnimport;

    @UiField("sales")
    private TextField sales;

    @UiField("marketShare")
    private TextField marketShare;

    @UiField("marketShare1")
    private TextField marketShare1;

    @UiField("analogLives")
    private TextField analogLives;

    @UiField("valuePerLife")
    private TextField valuePerLife;

    @UiField("projectedLives")
    private TextField projectedLives;

    @UiField("totalSales")
    private TextField totalSales;

    @UiField("projectionPeriodTotal")
    private TextField projectionPeriodTotal;

    @UiField("salesOrUnits")
    private OptionGroup salesOrUnits;

    @UiField("populate")
    private Button populate;

    @UiField("btnRemove")
    private Button btnRemove;

    @UiField("contractTableLayout")
    private VerticalLayout contractTableLayout;

    @UiField("table2")
    private VerticalLayout table2;

    /**
     * The non mandated logic.
     */
    private final NonMandatedLogic nonMandatedLogic = new NonMandatedLogic();

    /**
     * The df calculated amount.
     */
    public static final String STRING_FORMATOR_DECIMAL = "####.0";
    final DecimalFormat unitFormat = new DecimalFormat(STRING_FORMATOR_DECIMAL);
    public static final String PLEASE_ENTER_THE_CORRECT_VALUE = "Please Enter the correct value .";
    public static final String STRING_HUNDRED_PERCENT = "100.0%";
    
    public static final DecimalFormat MONEY_TWO_DECIMAL = new DecimalFormat("$#,###.00");
    public static final DecimalFormat MONEY_NO_DECIMAL = new DecimalFormat("$#,###");
    final DecimalFormat noDecimalPlace = new DecimalFormat("####");
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
    private Double calculatedSalesValue = 0.0;

    /**
     * The calculated UNITS_SMALL value.
     */
    private Double calculatedUnitsValue = 0.0;

    /**
     * The excel export ch.
     */
    @UiField("excelExportCh")
    private Button excelExportCh;

    /**
     * The excel export tp.
     */
    @UiField("excelExportTp")
    private Button excelExportTp;

    /**
     * The excel export calculation.
     */
    @UiField("excelExportCalculation")
    private Button excelExportCalculation;

    /**
     * The total lives chart ch.
     */
    @UiField("totalLivesChartCh")
    private Button totalLivesChartCh;
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource(
            EXCEL_IMAGE_PATH.getConstant());
    /**
     * The graph image.
     */
    private final Resource graphImage = new ThemeResource(GRAPH_IMAGE_PATH.getConstant());
    /**
     * The total lives chart tp.
     */
    @UiField("totalLivesChartTp")
    private Button totalLivesChartTp;

    @UiField("calculateOne")
    private Button calculateOne;

    @UiField("calculateTwo")
    private Button calculateTwo;

    /**
     * The regex.
     */
    public static String regex = "(^[0-9]+(\\.[0-9])?$)";

    /**
     * The uncheckimg.
     */

    /**
     * The checkedimg.
     */

    /**
     * The checkboxes1.
     */

    /**
     * The checkboxes2.
     */

    /**
     * The original bean.
     */
    public SalesProjectionResultsDTO originalBean = new SalesProjectionResultsDTO();

    /**
     * The calendar.
     */
    private final Calendar calendar = CommonUtils.getCalendar();
    private final ExtFilterTable contractHolderTable = new ExtFilterTable();
    private final ExtFilterTable tradingHistoryTable = new ExtFilterTable();
    private ExtTreeContainer<PMPYRowDto> chContainer = new ExtTreeContainer<>(PMPYRowDto.class,ExtContainer.DataStructureMode.MAP);
    private ExtTreeContainer<PMPYRowDto> tpContainer = new ExtTreeContainer<>(PMPYRowDto.class,ExtContainer.DataStructureMode.MAP);

    String historyPeriods = StringUtils.EMPTY;
    List projectionDetailsId;

    List<Object> visiColumn = new ArrayList<>();
    List<String> visiHeaders = new ArrayList<>();
    private final List<String> chtCheckBoxMap = new ArrayList<>();
    private final List<String> tptCheckBoxMap = new ArrayList<>();
    boolean valueChange = Boolean.TRUE;

    private String tradeName = StringUtils.EMPTY;

    private String tradeNo = StringUtils.EMPTY;

    private String contHolder = StringUtils.EMPTY;

    ExtFilterTreeTable excelTable1 = new ExtFilterTreeTable();

    ExtFilterTreeTable excelTable2 = new ExtFilterTreeTable();

    private int projectionId = 0;

    private boolean importEvent = false;

    SessionDTO session;
    ProjectionSelectionDTO projectionSelectionDTO;
    PmpyLogic logic = new PmpyLogic();

    /**
     * The Constructor.
     *
     * @param tpInfo the tp info
     * @param originalBean the original bean
     * @param generateBtn the generate btn
     */
    public NMPmpyCalculator(final String history, final List projectionDetailsId, final CustomTableHeaderDTO rightHeader, String tradeName, String tradeNo, String contractHolder, SessionDTO session, ProjectionSelectionDTO projectionSelectionDTO) {
        super(Constant.PMPY_CALCULATOR);
        LOGGER.debug("Entering PMPYCalculator");
        this.historyPeriods = history;
        this.projectionDetailsId = projectionDetailsId;
        this.projectionSelectionDTO = projectionSelectionDTO;
        this.rightDto = rightHeader;
        this.tradeName = tradeName;
        this.tradeNo = tradeNo;
        this.contHolder = contractHolder;
        this.projectionId = session.getProjectionId();
        this.session = session;
        init();
        LOGGER.debug("End of PMPYCalculator");
    }

    /**
     * Adds the screen contents and configures all the components.
     */
    public void init() {
        try {
            LOGGER.debug("Entering init method");
            center();
            setClosable(true);
            setModal(true);

            setWidth("1750px");
            setHeight("950px");
            setContent(Clara.create(getClass().getResourceAsStream("/nonmandated/nmPmpyCalculator.xml"), this));
            getResults();
            configureAttributes();
            configureFields();
            LOGGER.debug("End of init method");
            addStyleName(Constant.BOOTSTRAP_UI);
            addStyleName(Constant.BOOTSTRAP);
            addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Configures all the components.
     */
    public void configureFields() {
        try {
            LOGGER.debug("Entering configureFields method");
            excelExportTp.setIcon(excelExportImage);
            totalLivesChartTp.setIcon(graphImage);
            excelExportCh.setIcon(excelExportImage);
            totalLivesChartCh.setIcon(graphImage);
            excelExportCalculation.setIcon(excelExportImage);

            tradingPartner.setReadOnly(true);

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

                                contract.setValue(null);
                                tradingPartner.setReadOnly(false);
                                tradingPartner.setValue(StringUtils.EMPTY);
                                tradingPartner.setReadOnly(true);
                            }
                        }
                    }, ButtonId.YES, ButtonId.NO);

                }
            });

            btnRemove.setErrorHandler(new ErrorHandler() {
                /**
                 * Default method.
                 */

                @Override
                public void error(final com.vaadin.server.ErrorEvent event) {
                    return;
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

            effectivePeriod.setNullSelectionAllowed(Boolean.TRUE);
            effectivePeriod.setNullSelectionItemId(Constant.SELECT_ONE);
            effectivePeriod.addItem(Constant.SELECT_ONE);
            effectivePeriod.addItems(rightDto.getDoubleProjectedHeaders());
            effectivePeriod.select(Constant.SELECT_ONE);

            contract.setWidth("240px");
            contract.addStyleName("comboxsize");
            salesOrUnits.addItem(Constant.SALES_DOLLARS);
            salesOrUnits.addItem(Constant.UNIT_VOLUME);
            salesOrUnits.select(Constant.SALES_DOLLARS);
            loadContractHolder();

            marketShare.setValue(STRING_HUNDRED_PERCENT);
            marketShare1.setValue(STRING_HUNDRED_PERCENT);
            analogLives.setImmediate(true);
            analogLives.addValidator(new RegexpValidator(regex, "Should contain only number"));
            sales.setEnabled(false);
            valuePerLife.setEnabled(false);
            totalSales.setEnabled(false);
            projectionPeriodTotal.setEnabled(false);
            frequency.setEnabled(false);
            frequency.setWidth("150px");
            history.setEnabled(false);

            pmpyContractHolder.setValue(contHolder);

            tradingPartnerNo.setValue(tradeNo);

            tradingPartnerName.setValue(tradeName);

            pmpyContractHolder.setReadOnly(true);
            pmpyContractHolder.focus();
            tradingPartnerNo.setReadOnly(true);
            tradingPartnerName.setReadOnly(true);

            marketShare1.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (valueChange) {
                        if (marketShare1.isValid()) {
                            Double value = Double.valueOf(String.valueOf(marketShare1.getValue()));
                            marketShare1.setValue(unitFormat.format(value) + Constant.PERCENT);
                        } else {
                            MessageBox.showPlain(Icon.INFO, Constant.ERROR, PLEASE_ENTER_THE_CORRECT_VALUE, ButtonId.OK);
                            marketShare1.setValue(STRING_HUNDRED_PERCENT);
                        }

                    }
                }
            });
            marketShare.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (valueChange) {
                        if (marketShare.isValid()) {
                            Double value = Double.valueOf(String.valueOf(marketShare.getValue()));
                            marketShare.setValue(unitFormat.format(value) + Constant.PERCENT);
                        } else {
                            MessageBox.showPlain(Icon.INFO, Constant.ERROR, PLEASE_ENTER_THE_CORRECT_VALUE, ButtonId.OK);
                            marketShare.setValue(STRING_HUNDRED_PERCENT);
                        }

                    }
                }
            });

            analogLives.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (valueChange) {
                        if (analogLives.isValid()) {
                            Double value = Double.valueOf(String.valueOf(analogLives.getValue()));
                            analogLives.setValue(noDecimalPlace.format(value));
                        } else {
                            MessageBox.showPlain(Icon.INFO, Constant.ERROR, PLEASE_ENTER_THE_CORRECT_VALUE, ButtonId.OK);
                            analogLives.setValue(StringUtils.EMPTY);
                        }
                    }
                }
            });
            projectedLives.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (valueChange) {
                        if (projectedLives.isValid()) {
                            Double value = Double.valueOf(String.valueOf(projectedLives.getValue()));
                            projectedLives.setValue(noDecimalPlace.format(value));
                        } else {
                            MessageBox.showPlain(Icon.INFO, Constant.ERROR, PLEASE_ENTER_THE_CORRECT_VALUE, ButtonId.OK);
                            projectedLives.setValue(StringUtils.EMPTY);
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
                                valueChange = Boolean.FALSE;
                                sales.setValue(StringUtils.EMPTY);
                                marketShare.setValue(STRING_HUNDRED_PERCENT);
                                analogLives.setValue(StringUtils.EMPTY);
                                valuePerLife.setValue(StringUtils.EMPTY);
                                marketShare1.setValue(STRING_HUNDRED_PERCENT);
                                projectedLives.setValue(StringUtils.EMPTY);
                                totalSales.setValue(StringUtils.EMPTY);
                                projectionPeriodTotal.setValue(StringUtils.EMPTY);
                                valueChange = Boolean.TRUE;
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

                        LOGGER.error(e);
                           
                    }

                }
            });
            tradingPartner.addClickListener(new CustomTextField.ClickListener() {

                @Override
                public void click(CustomTextField.ClickEvent event) {
                    final PmpyTradingPartnerLookup tpLookUp = new PmpyTradingPartnerLookup(tradingPartner, contract.getValue());
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
                        if ((contract.getValue() == null || StringUtils.EMPTY.equals(String.valueOf(contract.getValue())) || Constant.NULL.equals(String.valueOf(contract.getValue())) || String.valueOf(contract.getValue()).equals(DASH))
                                && (tradingPartner.getValue() == null || StringUtils.EMPTY.equals(String.valueOf(tradingPartner.getValue())) || Constant.NULL.equals(String.valueOf(tradingPartner.getValue())))) {
                            AbstractNotificationUtils.getErrorNotification("No Contract Holder/Trading Partner entered", "Please select a Contract Holder or Trading Partner.");
                        } else {
                            generateButtonLogic();
                        }
                    }  catch (Exception e) {
                        LOGGER.error(e);

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
        } catch (Exception e) {

            LOGGER.error(e);

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
            return Boolean.TRUE;
        }
        LOGGER.debug("End of isTpHistorySelected method");
        return Boolean.FALSE;
    }

    /**
     * To check if any period of the contract holder is checked.
     *
     * @return true if any of the check box in contract holder header is checked
     */
    public boolean isContractHistorySelected() {

        LOGGER.debug("Entering isContractHistorySelected method");
        try {
            if (contract.getValue() != null && !contract.getValue().toString().isEmpty()) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {

            LOGGER.error(e);
        }
        LOGGER.debug("End of isContractHistorySelected method");
        return Boolean.FALSE;
    }

    /**
     * Reset button.
     *
     * @return the button
     */
    public Button resetButton() {

        LOGGER.debug("Entering resetButton method");

        return btnResetSearch;

    }

    /**
     * Gets the results.
     *
     * @return the results
     */
    private void getResults() {

        LOGGER.debug("Entering getResults method");

        contractTableLayout.addComponent(contractHolderTable);
        table2.addComponent(tradingHistoryTable);

        contractTableLayout.setMargin(false);
        table2.setMargin(false);

        LOGGER.debug("End of getResults method");

    }

    /**
     * Gets the calculator.
     *
     * @return the calculator
     */

    /**
     * Gets the calculatorlayout.
     *
     * @return the calculatorlayout
     */

    /**
     * Gets the calculatorlayout1.
     *
     * @return the calculatorlayout1
     */


    /**
     * Load contract holder.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void loadContractHolder() {
        LOGGER.debug("Entering loadContractHolder method");
        contract.setNullSelectionAllowed(Boolean.TRUE);
        contract.setNullSelectionItemId(Constant.SELECT_ONE);
        contract.addItem(Constant.SELECT_ONE);
        contract.select(Constant.SELECT_ONE);
        contract = nonMandatedLogic.loadPMPYContractHolders(contract);
        LOGGER.debug("End of loadContractHolder method");
    }

    /**
     * Generate button logic.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void generateButtonLogic()  {
        LOGGER.debug("Entering generateButtonLogic method");
        chContainer.removeAllItems();
        tpContainer.removeAllItems();
        if (isContractHistorySelected()) {
            Object[] contractInputs = new Object[NumericConstants.FIVE];
            contractInputs[0] = String.valueOf(projectionId);
            contractInputs[1] = CommonUtils.getListToString(projectionDetailsId);
            contractInputs[NumericConstants.TWO] = String.valueOf(contract.getValue() == null ? 0 : contract.getValue());
            contractInputs[NumericConstants.THREE] = String.valueOf(0);
            contractInputs[NumericConstants.FOUR] = rightDto.getDoubleHistoryColumns();
            chContainer.addAll(logic.getNmPmpyResultList(contractInputs));

        }
        if (isTpHistorySelected()) {

            Object[] trPInputs = new Object[NumericConstants.FIVE];
            trPInputs[0] = String.valueOf(projectionId);
            trPInputs[1] = CommonUtils.getListToString(projectionDetailsId);
            trPInputs[NumericConstants.TWO] = String.valueOf(0);
            trPInputs[NumericConstants.THREE] = String.valueOf(tradingPartner.getData() == null ? 0 : tradingPartner.getData());
            trPInputs[NumericConstants.FOUR] = rightDto.getDoubleHistoryColumns();
            tpContainer.addAll(logic.getNmPmpyResultList(trPInputs));

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
    private void populateButtonLogic( boolean chValue, boolean tpValue) {

        LOGGER.debug("Entering populateButtonLogic method");
        calculatedSalesValue = 0.0;
        calculatedUnitsValue = 0.0;

        if (chValue && tpValue) {
            AbstractNotificationUtils.getErrorNotification("Periods in Both List Views Selected",
                    "Historical periods can only be selected from either the Contract Holder list view or the Trading Partner list view.  " + "\nPlease try again.");
            return;
        } else {
            if (chValue) {
                PMPYRowDto actualSalesDto = null;
                PMPYRowDto actualUnitsDto = null;
                for (PMPYRowDto dto : chContainer.getBeans()) {
                    if (dto.getType().equals(Constant.SALES_SMALL)) {
                        actualSalesDto = dto;
                    } else if (dto.getType().equals(Constant.UNITS_SMALL)) {
                        actualUnitsDto = dto;
                    }
                }

                Double salesValue;
                Double unitsValue;
                for (Object key : chtCheckBoxMap) {

                    salesValue = Double.valueOf(String.valueOf(actualSalesDto.getProperties().get(key)).replace(",", StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY));
                    unitsValue = Double.valueOf(String.valueOf(actualUnitsDto.getProperties().get(key)).replace(",", StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY));
                    calculatedSalesValue += salesValue;
                    calculatedUnitsValue += unitsValue;
                }

            } else if (tpValue) {
                PMPYRowDto actualSalesDto = null;
                PMPYRowDto actualUnitsDto = null;
                for (PMPYRowDto dto : tpContainer.getBeans()) {
                    if (dto.getType().equals(Constant.SALES_SMALL)) {
                        actualSalesDto = dto;
                    } else if (dto.getType().equals(Constant.UNITS_SMALL)) {
                        actualUnitsDto = dto;
                    }
                }
                Double salesValue;
                Double unitsValue;
                for (Object key : tptCheckBoxMap) {
                    salesValue = Double.valueOf(String.valueOf(actualSalesDto.getProperties().get(key)).replace(",", StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY));
                    unitsValue = Double.valueOf(String.valueOf(actualUnitsDto.getProperties().get(key)).replace(",", StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY));
                    calculatedSalesValue += salesValue;
                    calculatedUnitsValue += unitsValue;
                }

            }

            if (salesOrUnits.getValue() != null) {
                if (Constant.SALES_DOLLARS.equalsIgnoreCase(String.valueOf(salesOrUnits.getValue()))) {
                    sales.setValue(MONEY_NO_DECIMAL.format(calculatedSalesValue));
                } else if (Constant.UNIT_VOLUME.equalsIgnoreCase(String.valueOf(salesOrUnits.getValue()))) {
                    sales.setValue(dfCalculatedUnit.format(calculatedUnitsValue));
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
        Double value;

        value = Double.valueOf(String.valueOf(obj));
        dfSales.format(value);
        LOGGER.debug("End of getSales method");

        return value;
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
     * First calculation.
     */
    private void firstCalculation() {
        LOGGER.debug("Entering of firstCalculation method");

        Double analogLivesValue;

        if (analogLives.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(analogLives.getValue())) && !Constant.NULL.equals(String.valueOf(analogLives.getValue()))
                && isNumeric(String.valueOf(analogLives.getValue())) && Double.valueOf(String.valueOf(analogLives.getValue())) != 0.0) {
            analogLivesValue = Double.valueOf(String.valueOf(analogLives.getValue()));
        } else {
            AbstractNotificationUtils.getErrorNotification("No Lives", "Please enter a numeric value for Lives.");
            valuePerLife.setValue(StringUtils.EMPTY);
            return;
        }
        final String tempSales = String.valueOf(sales.getValue()).replace(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY);
        Double salesValue = 1.0;
        if (sales.getValue() != null && !StringUtils.EMPTY.equals(tempSales) && !Constant.NULL.equals(tempSales) && isNumeric(tempSales) && Double.valueOf(tempSales) != 0.0) {

            if (Constant.SALES.equalsIgnoreCase(getVariableValue())) {
                final DecimalFormat salesFormat = new DecimalFormat("####");
                salesValue = Double.valueOf(salesFormat.format(Double.valueOf(tempSales)));

            } else if (Constant.UNITS.equalsIgnoreCase(getVariableValue())) {
                final DecimalFormat unitsFormat = new DecimalFormat(STRING_FORMATOR_DECIMAL);
                salesValue = Double.valueOf(unitsFormat.format(Double.valueOf(tempSales)));

            }
        } else {
            valuePerLife.setValue(StringUtils.EMPTY);
        }
        Double marketShareValue;

        if (marketShare.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(marketShare.getValue())) && !Constant.NULL.equals(String.valueOf(marketShare.getValue()))
                && isNumeric(String.valueOf(marketShare.getValue())) && Double.valueOf(String.valueOf(marketShare.getValue()).replace(Constant.PERCENT, StringUtils.EMPTY)) != 0.0) {
            marketShareValue = Double.valueOf(String.valueOf(marketShare.getValue()).replace(Constant.PERCENT, StringUtils.EMPTY));
        } else {
            valuePerLife.setValue(StringUtils.EMPTY);
            return;
        }

        final Double salesByMarketshareValue = salesValue / (marketShareValue / 100);
        final Double valuePerLifeValue = salesByMarketshareValue / analogLivesValue;
        if (Constant.SALES.equalsIgnoreCase(getVariableValue())) {

            valuePerLife.setValue(String.valueOf(MONEY_TWO_DECIMAL.format(valuePerLifeValue)));
        } else if (Constant.UNITS.equalsIgnoreCase(getVariableValue())) {
            valuePerLife.setValue(String.valueOf(MONEY_TWO_DECIMAL.format(valuePerLifeValue)));
        }
        LOGGER.debug("End of firstCalculation method");

    }

    /**
     * Second calculation.
     */
    private void secondCalculation() {
        LOGGER.debug("Entering of secondCalculation method");

        Double projectedLivesValue;
        if (projectedLives.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(projectedLives.getValue())) && !Constant.NULL.equals(String.valueOf(projectedLives.getValue()))
                && isNumeric(String.valueOf(projectedLives.getValue())) && Double.valueOf(String.valueOf(projectedLives.getValue())) != 0.0) {
            projectedLivesValue = Double.valueOf(String.valueOf(projectedLives.getValue()));
        } else {
            AbstractNotificationUtils.getErrorNotification("No Total Lives", "Please enter a numeric value for Total Lives.");
            totalSales.setValue(StringUtils.EMPTY);
            projectionPeriodTotal.setValue(StringUtils.EMPTY);
            return;
        }
        final String tempValuePerLifeValue = String.valueOf(valuePerLife.getValue()).replace(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY);

        Double valuePerLifeValue = 1.0;

        if (valuePerLife.getValue() != null && !StringUtils.EMPTY.equals(tempValuePerLifeValue) && !Constant.NULL.equals(tempValuePerLifeValue) && isNumeric(tempValuePerLifeValue)
                && Double.valueOf(tempValuePerLifeValue) != 0.0) {

            if (Constant.SALES.equalsIgnoreCase(getVariableValue())) {

                valuePerLifeValue = Double.valueOf(Double.valueOf(tempValuePerLifeValue));
            } else if (Constant.UNITS.equalsIgnoreCase(getVariableValue())) {

                valuePerLifeValue = Double.valueOf(Double.valueOf(tempValuePerLifeValue));
            }
        } else {
            totalSales.setValue(StringUtils.EMPTY);
            projectionPeriodTotal.setValue(StringUtils.EMPTY);
            return;
        }

        Double marketShareValue;

        if (marketShare1.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(marketShare1.getValue())) && !Constant.NULL.equals(String.valueOf(marketShare1.getValue()))
                && isNumeric(String.valueOf(marketShare1.getValue()))) {
            marketShareValue = Double.valueOf(String.valueOf(marketShare1.getValue()).replace(Constant.PERCENT, StringUtils.EMPTY));
        } else {
            totalSales.setValue(StringUtils.EMPTY);
            projectionPeriodTotal.setValue(StringUtils.EMPTY);
            return;
        }

        Double projectionPeriodTotalValue;
        final Double totalValue = (valuePerLifeValue * (marketShareValue / 100)) * projectedLivesValue;
        if (Constant.SALES.equalsIgnoreCase(getVariableValue())) {
            totalSales.setValue(String.valueOf(MONEY_NO_DECIMAL.format(totalValue)));
            projectionPeriodTotalValue = projectionPeriodTotalCalculation(totalValue);
            projectionPeriodTotal.setValue(String.valueOf(MONEY_NO_DECIMAL.format(projectionPeriodTotalValue)));
        } else if (Constant.UNITS.equalsIgnoreCase(getVariableValue())) {
            totalSales.setValue(String.valueOf(dfCalculatedUnit.format(totalValue)));
            projectionPeriodTotalValue = projectionPeriodTotalCalculation(totalValue);
            projectionPeriodTotal.setValue(String.valueOf(dfCalculatedUnit.format(projectionPeriodTotalValue)));
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
        LOGGER.debug("Entering of isNumeric method with str=" + str);
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
        if (salesOrUnits.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(salesOrUnits.getValue())) && !Constant.NULL.equals(String.valueOf(salesOrUnits.getValue()))
                && Constant.SALES_DOLLARS.equalsIgnoreCase(String.valueOf(salesOrUnits.getValue()))) {
            variableValue = Constant.SALES;
        }
        if (salesOrUnits.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(salesOrUnits.getValue())) && !Constant.NULL.equals(String.valueOf(salesOrUnits.getValue()))
                && Constant.UNIT_VOLUME.equalsIgnoreCase(String.valueOf(salesOrUnits.getValue()))) {
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

        String value = String.valueOf(projectionPeriodTotal.getValue());

        LOGGER.debug("Entering of importButtonLogic method");
        if (StringUtils.EMPTY.equals(value) || Constant.NULL.equals(value)) {
            AbstractNotificationUtils.getErrorNotification("No Projection Period Total", "Please complete the PMPY calculation before clicking IMPORT.");
            return;
        }

        final String variableValue = getVariableValue();

        value = value.replace(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY).replace(Constant.CURRENCY, StringUtils.EMPTY);

        if (Constant.SALES.equalsIgnoreCase(variableValue)) {
            final DecimalFormat salesFormat = new DecimalFormat("####");
            value = String.valueOf(salesFormat.format(Double.valueOf(value)));
        } else if (Constant.UNITS.equalsIgnoreCase(variableValue)) {
            final DecimalFormat unitDecimalFormat = new DecimalFormat(STRING_FORMATOR_DECIMAL);
            value = String.valueOf(unitDecimalFormat.format(Double.valueOf(value)));
        }
        if (effectivePeriod.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("No Effective Date", "Please select an Effective Period.");
            return;
        }
        final String finalValue = value;

        if (StringUtils.EMPTY.equals(finalValue) || Constant.NULL.equals(finalValue)) {
            AbstractNotificationUtils.getErrorNotification("No Projection Period Total", "Please complete the PMPY calculation before clicking IMPORT.");
        } else {
            String tempValue = StringUtils.EMPTY;
            final int currentYear = calendar.get(Calendar.YEAR);
            final int yearOne = currentYear + 1;
            final int yearTwo = currentYear + NumericConstants.TWO;
            final int yearThree = currentYear + NumericConstants.THREE;
            String queryName = StringUtils.EMPTY;
            final int[] yearArray = new int[]{currentYear, yearOne, yearTwo, yearThree};
            final List yearArrayList = new ArrayList();
            for (int i = 0; i < yearArray.length; i++) {
                yearArrayList.add(yearArray[i]);
            }
            int annual;
            Double calculatedValue = 0.0;

            if (Constant.SALES.equals(variableValue)) {
                tempValue = Constant.SALES_CAPS;
                queryName = "Check GTS Sales Values";
                calculatedValue = Double.valueOf(dfSales.format(Double.valueOf(finalValue)));
            }
            if (Constant.UNITS.equals(variableValue)) {
                tempValue = Constant.UNITS_SMALL;
                queryName = "Check GTS UNT Values";
                calculatedValue = Double.valueOf(dfUnits.format(Double.valueOf(finalValue)));
            }
            calculatedValue = getDividedValue(calculatedValue);
            int startQuator = effectivePeriod.getValue().toString().charAt(1) - NumericConstants.FORTY_EIGHT;
            annual = Integer.valueOf(effectivePeriod.getValue().toString().substring(NumericConstants.THREE, NumericConstants.SEVEN));
            inputs[0] = projectionDetailsId;
            inputs[1] = calculatedValue;
            inputs[NumericConstants.TWO] = annual;
            inputs[NumericConstants.THREE] = startQuator;
            inputs[NumericConstants.FOUR] = tempValue;

            List input = new ArrayList();
            input.add(startQuator);
            input.add(annual);
            input.add(session.getSessionId());
            input.add(session.getUserId());
            input.add(calculatedValue);
            input.add(CommonUtils.getListToString((List) projectionDetailsId));

            Double gtsValue = Double.valueOf(PPAQuerys.getPPAData(input, queryName, null).get(0).toString());
            if (gtsValue == 0) {
                List input1 = new ArrayList();
                List NDcs = new ArrayList();
                input1.add(CommonUtils.getListToString((List) inputs[0]));
                List<String> ndcs = PPAQuerys.getPPAData(input1, "Get NDC Values", null);
                for (String str : ndcs) {
                    NDcs.add(str == null ? StringUtils.EMPTY : str);
                }
                MessageBox.showPlain(Icon.INFO, "Error", "The following NDC's " + CommonUtils.getListToString(NDcs) + "is/are not on the current GTS File", ButtonId.OK);

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
                                        logic.importPMPY(inputs, projectionSelectionDTO);
                                        importEvent = true;
                                        close();

                                    } catch (Exception e) {
                                        importEvent = false;
                                        LOGGER.error(e);
                                    }
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
            }

        }

        LOGGER.debug("End of importButtonLogic method");
    }

    /**
     * Export calculated excel.
     */
    private void exportCalculatedExcel() {
        try {
            LOGGER.debug("Entering of exportCalculatedExcel method");
            final PMPYCalculationExporterDTO exporterDto = new PMPYCalculationExporterDTO();
            final PMPYCalculationExporter exporter = new PMPYCalculationExporter();

            exporterDto.setVariable(String.valueOf(getVariableValue()));
            exporterDto.setSales(String.valueOf(sales.getValue()));
            exporterDto.setMarketShare(String.valueOf(marketShare.getValue()));
            exporterDto.setAnalogLives(String.valueOf(analogLives.getValue()));
            exporterDto.setValuePerLife(String.valueOf(valuePerLife.getValue()));
            exporterDto.setMarketShare1(String.valueOf(marketShare1.getValue()));
            exporterDto.setProjectedLives(String.valueOf(projectedLives.getValue()));
            exporterDto.setTotalSales(String.valueOf(totalSales.getValue()));
            exporterDto.setProjectionPeriodTotal(String.valueOf(projectionPeriodTotal.getValue()));

            exporter.export(exporterDto);
            LOGGER.debug("End of exportCalculatedExcel method");
        } catch (SystemException e) {
 
            LOGGER.error(e);

        } catch (Exception e) {

            LOGGER.error(e);

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
        double returnValue = 0;

        LOGGER.debug("Entering of getDividedValue method");
        final DecimalFormat formatter = new DecimalFormat("#.000000");
        if (actualValue != Constant.ZERO) {
            Double temp = actualValue;

            temp = Double.valueOf(formatter.format(temp));
            returnValue = temp.isInfinite() || temp.isNaN() ? 0 : temp;

        }
        LOGGER.debug("End of getDividedValue method");

        return returnValue;
    }

    public void configureAttributes() {

        history.setValue(historyPeriods);
        frequency.setValue(Constants.FrequencyConstants.QUARTERLY.getConstant());

        for (Object obj : rightDto.getDoubleHistoryColumns()) {
            visiColumn.add(obj);
        }
        for (Object obj : rightDto.getDoubleHistoryHeaders()) {
            visiHeaders.add(String.valueOf(obj));
        }

        contractHolderTable.setWidth(NumericConstants.EIGHT_HUNDRED, UNITS_PIXELS);
        tradingHistoryTable.setWidth(NumericConstants.EIGHT_HUNDRED, UNITS_PIXELS);
        contractHolderTable.markAsDirty();
        tradingHistoryTable.markAsDirty();
        chContainer = new ExtTreeContainer<>(PMPYRowDto.class,ExtContainer.DataStructureMode.MAP);
        tpContainer = new ExtTreeContainer<>(PMPYRowDto.class,ExtContainer.DataStructureMode.MAP);

        tradingHistoryTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractHolderTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        tradingHistoryTable.setPageLength(NumericConstants.THREE);
        contractHolderTable.setPageLength(NumericConstants.THREE);
        contractHolderTable.setHeight("160px");
        tradingHistoryTable.setHeight("160px");

        Map<Object, Class> properties = new HashMap<>();

        for (Object obj : visiColumn) {
            properties.put(String.valueOf(obj), String.class);

        }
        chContainer.setColumnProperties(properties);
        tpContainer.setColumnProperties(properties);
        contractHolderTable.setContainerDataSource(chContainer);
        tradingHistoryTable.setContainerDataSource(tpContainer);
        contractHolderTable.setVisibleColumns(visiColumn.toArray());
        contractHolderTable.setColumnHeaders(visiHeaders.toArray(new String[visiHeaders.size()]));

        tradingHistoryTable.setVisibleColumns(visiColumn.toArray());
        tradingHistoryTable.setColumnHeaders(visiHeaders.toArray(new String[visiHeaders.size()]));

        excelTable1.setVisible(false);
        excelTable2.setVisible(false);
        for (Object obj : visiColumn) {
            contractHolderTable.setColumnCheckBox(obj, true);
            tradingHistoryTable.setColumnCheckBox(obj, true);
            contractHolderTable.setColumnWidth(obj, NumericConstants.ONE_THREE_ZERO);
            tradingHistoryTable.setColumnWidth(obj, NumericConstants.ONE_THREE_ZERO);
        }

        alignRight(contractHolderTable, tradingHistoryTable);
        contractHolderTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                if (event.isChecked()) {
                    chtCheckBoxMap.add(event.getPropertyId().toString());
                } else {
                    chtCheckBoxMap.remove(event.getPropertyId());

                }
            }
        });

        tradingHistoryTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {

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

    private void alignRight(ExtFilterTable table, ExtFilterTable table1) {
        for (Object obj : visiColumn) {
            table.setColumnAlignment(obj, ExtFilterTable.Align.RIGHT);
            table1.setColumnAlignment(obj, ExtFilterTable.Align.RIGHT);
        }
    }

    public boolean isImportEvent() {
        return importEvent;
    }

    @UiHandler("excelExportCalculation")
    public void excelExportCalculation(Button.ClickEvent event) {
        exportCalculatedExcel();
    }

    @UiHandler("totalLivesChartCh")
    public void totalLivesChartCh(Button.ClickEvent event) {
        final PMPYContractHolderHistoryChart chart = new PMPYContractHolderHistoryChart(chContainer.getBeans(), (String) contract.getValue(), rightDto.getDoubleHistoryColumns());
        final NmSalesGraphWindow salesGraphWindow = new NmSalesGraphWindow(chart.getCharts(), Constant.PMPY_CALCULATOR);
        UI.getCurrent().addWindow(salesGraphWindow);
    }

    @UiHandler("excelExportCh")
    public void excelExportCh(Button.ClickEvent event) {
        excelTable1.setContainerDataSource(chContainer);
        ExtFilterTable leftTable = contractHolderTable;
        ExtFilterTable rightTable = contractHolderTable;
        excelTable1.setVisibleColumns(ArrayUtils.addAll(leftTable.getVisibleColumns(), rightTable.getVisibleColumns()));
        Object[] objectArray = ArrayUtils.addAll(leftTable.getColumnHeaders(), rightTable.getColumnHeaders());
        excelTable1.setColumnHeaders(Arrays.copyOf(objectArray, objectArray.length, String[].class));
        ForecastUI.EXCEL_CLOSE=true;
        ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable1), "Contract Holder", "PMPY Calculator - Contract Holder", "PMPYcalculatorContractHolder.xls", false);
        export.export();
    }

    @UiHandler("totalLivesChartTp")
    public void totalLivesChartTp(Button.ClickEvent event) {

        final PMPYTradingPartnerHistoryChart chart = new PMPYTradingPartnerHistoryChart(tpContainer.getBeans(), tradingPartner.getValue(), rightDto.getDoubleHistoryColumns());
        final NmSalesGraphWindow salesGraphWindow = new NmSalesGraphWindow(chart.getCharts(), Constant.PMPY_CALCULATOR);
        UI.getCurrent().addWindow(salesGraphWindow);

    }

    @UiHandler("excelExportTp")
    public void excelExportTp(Button.ClickEvent event) {
        excelTable2.setContainerDataSource(tpContainer);
        ExtFilterTable leftTable = tradingHistoryTable;
        ExtFilterTable rightTable = tradingHistoryTable;
        excelTable2.setVisibleColumns(ArrayUtils.addAll(leftTable.getVisibleColumns(), rightTable.getVisibleColumns()));
        Object[] objectArray = ArrayUtils.addAll(leftTable.getColumnHeaders(), rightTable.getColumnHeaders());
        excelTable2.setColumnHeaders(Arrays.copyOf(objectArray, objectArray.length, String[].class));
        ForecastUI.EXCEL_CLOSE=true;
        ExcelExport export = new ExcelExport(new ExtCustomTableHolder(excelTable2), Constant.TRADING_PARTNER, "PMPY Calculator - Trading Partner", "PMPYcalculatorTradingPartner.xls", false);
        export.export();
    }

}
