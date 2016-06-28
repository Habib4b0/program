/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.lookups;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.dto.PMPYCalculationExporterDTO;
import com.stpl.app.galforecasting.dto.PMPYCalculatorDTO;
import com.stpl.app.galforecasting.dto.PMPYRowDto;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.app.galforecasting.logic.NonMandatedLogic;
import com.stpl.app.galforecasting.lookups.logic.PmpyLogic;
import com.stpl.app.galforecasting.queryUtils.PPAQuerys;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.ui.NmSalesGraphWindow;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.CORRECTVALUE;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import com.stpl.app.galforecasting.utils.PMPYCalculationExporter;
import com.stpl.app.galforecasting.utils.PMPYContractHolderHistoryChart;
import com.stpl.app.galforecasting.utils.PMPYTradingPartnerHistoryChart;
import com.stpl.app.galforecasting.utils.PMPYUtils;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.ResourceConstants.EXCEL_IMAGE_PATH;
import static com.stpl.app.utils.Constants.ResourceConstants.GRAPH_IMAGE_PATH;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
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
     * The date.
     */
    private Date date = new Date();

    /**
     * The space.
     */
    private Label space = new Label(Constant.ADD_SPACE, ContentMode.HTML);

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
    private NonMandatedLogic nonMandatedLogic = new NonMandatedLogic();

    /**
     * The df calculated amount.
     */
    private DecimalFormat dfCalculatedAmount = new DecimalFormat("#,###");

    private DecimalFormat twoDecimalPlace = new DecimalFormat("#,###.00");
    final DecimalFormat unitFormat = new DecimalFormat("####.0");
    public static final DecimalFormat MONEY_TWO_DECIMAL = new DecimalFormat("$#,###.00");
    public static final DecimalFormat MONEY_NO_DECIMAL = new DecimalFormat("$#,###");
    final DecimalFormat noDecimalPlace = new DecimalFormat("####");
    /**
     * The df calculated unit.
     */
    private DecimalFormat dfCalculatedUnit = new DecimalFormat("#,###.0");

    /**
     * The df SALES_SMALL.
     */
    private DecimalFormat dfSales = new DecimalFormat("#");

    /**
     * The df UNITS_SMALL.
     */
    private DecimalFormat dfUnits = new DecimalFormat("#.0");

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
    private ThemeResource uncheckimg = new ThemeResource(PMPYUtils.UNCHECK);

    /**
     * The checkedimg.
     */
    private ThemeResource checkedimg = new ThemeResource(PMPYUtils.CHECK);

    /**
     * The checkboxes1.
     */
    private List<CheckBox> checkboxes1 = new ArrayList<CheckBox>();

    /**
     * The checkboxes2.
     */
    private List<CheckBox> checkboxes2 = new ArrayList<CheckBox>();

    /**
     * The original bean.
     */
    public SalesProjectionResultsDTO originalBean = new SalesProjectionResultsDTO();

    /**
     * The calendar.
     */
    private Calendar calendar = CommonUtils.getCalendar();
    private ExtFilterTable contractHolderTable = new ExtFilterTable();
    private ExtFilterTable tradingHistoryTable = new ExtFilterTable();
    private CustomTreeContainer<PMPYRowDto> chContainer = new CustomTreeContainer<PMPYRowDto>(PMPYRowDto.class);
    private CustomTreeContainer<PMPYRowDto> tpContainer = new CustomTreeContainer<PMPYRowDto>(PMPYRowDto.class);

    String historyPeriods = StringUtils.EMPTY;
    List projectionDetailsId;

    List<Object> visiColumn = new ArrayList<Object>();
    List<String> visiHeaders = new ArrayList<String>();
    private List<String> chtCheckBoxMap = new ArrayList<String>();
    private List<String> tptCheckBoxMap = new ArrayList<String>();
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
        super("PMPY Calculator");
        LOGGER.info("Entering PMPYCalculator");
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
        LOGGER.info("End of PMPYCalculator");
    }

    /**
     * Adds the screen contents and configures all the components.
     */
    public void init() {
        try {
            LOGGER.info("Entering init method");
            center();
            setClosable(true);
            setModal(true);

            setWidth("1750px");
            setHeight("950px");
            setContent(Clara.create(getClass().getResourceAsStream("/nonmandated/nmPmpyCalculator.xml"), this));
            getResults();
            configureAttributes();
            configureFields();
            LOGGER.info("End of init method");
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
            LOGGER.info("Entering configureFields method");
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
                public void buttonClick(final ClickEvent event) {

                    MessageBox.showPlain(Icon.QUESTION, "Confirm Reset", "Are you sure you want to reset the page to default values?", new MessageBoxListener() {
                        /**
                         * Default method.
                         */

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

                public void error(final com.vaadin.server.ErrorEvent event) {

                }
            });
            btnRemove.addClickListener(new ClickListener() {

                /**
                 * Default method.
                 */
                public void buttonClick(final ClickEvent event) {

                    MessageBox.showPlain(Icon.QUESTION, "Close Confirmation", "Are you sure you want to Close the Calculator" + " ?", new MessageBoxListener() {
                        /**
                         * Default method.
                         */

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

            marketShare.setValue("100.0%");
            marketShare1.setValue("100.0%");
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
                            MessageBox.showPlain(Icon.INFO, Constant.ERROR, "Please Enter the correct value .", ButtonId.OK);
                            marketShare1.setValue("100.0%");
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
                            MessageBox.showPlain(Icon.INFO, Constant.ERROR, "Please Enter the correct value .", ButtonId.OK);
                            marketShare.setValue("100.0%");
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
                            MessageBox.showPlain(Icon.INFO, Constant.ERROR, "Please Enter the correct value .", ButtonId.OK);
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
                            MessageBox.showPlain(Icon.INFO, Constant.ERROR, "Please Enter the correct value .", ButtonId.OK);
                            projectedLives.setValue(StringUtils.EMPTY);
                        }
                    }
                }
            });

            btnResetCalculation.addClickListener(new Button.ClickListener() {
                /**
                 * Default method.
                 */
                public void buttonClick(final ClickEvent event) {

                    MessageBox.showPlain(Icon.QUESTION, "Confirm Reset", "Are you sure you want to reset the page to default values?", new MessageBoxListener() {
                        /**
                         * Default method.
                         */

                        public void buttonClicked(final ButtonId buttonId) {
                            if (buttonId.name().equals(Constant.YES)) {
                                valueChange = Boolean.FALSE;
                                sales.setValue(StringUtils.EMPTY);
                                marketShare.setValue("100.0%");
                                analogLives.setValue(StringUtils.EMPTY);
                                valuePerLife.setValue(StringUtils.EMPTY);
                                marketShare1.setValue("100.0%");
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
                public void buttonClick(final ClickEvent event) {

                    firstCalculation();

                }
            });

            calculateTwo.addClickListener(new Button.ClickListener() {
                /**
                 * Default method.
                 */
                public void buttonClick(final ClickEvent event) {
                    secondCalculation();
                }
            });
            btnimport.addClickListener(new Button.ClickListener() {
                /**
                 * Default method.
                 */
                public void buttonClick(final ClickEvent event) {

                    try {
                        importButtonLogic();
                    } catch (IllegalAccessException e) {

                        LOGGER.error(e.getMessage());

                    } catch (InvocationTargetException e) {

                        LOGGER.error(e.getMessage());

                    } catch (NoSuchMethodException e) {

                        LOGGER.error(e.getMessage());

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
                public void buttonClick(final ClickEvent event) {
                    try {
                        if ((contract.getValue() == null || StringUtils.EMPTY.equals(String.valueOf(contract.getValue())) || Constant.NULL.equals(String.valueOf(contract.getValue())) || String.valueOf(contract.getValue()).equals(DASH))
                                && (tradingPartner.getValue() == null || StringUtils.EMPTY.equals(String.valueOf(tradingPartner.getValue())) || Constant.NULL.equals(String.valueOf(tradingPartner.getValue())))) {
                            AbstractNotificationUtils.getErrorNotification("No Contract Holder/Trading Partner entered", "Please select a Contract Holder or Trading Partner.");
                        } else {
                            generateButtonLogic();
                        }
                    } catch (SystemException e) {

                        LOGGER.error(e.getMessage());

                    } catch (PortalException e) {
                        LOGGER.error(e.getMessage());

                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());

                    }

                }
            });

            populate.addClickListener(new Button.ClickListener() {
                /**
                 * Default method.
                 */
                public void buttonClick(final ClickEvent event) {
                    final boolean chValue = isContractCheckBoxSelected();
                    final boolean tpValue = isTpCheckBoxSelectd();

                    if (chValue || tpValue) {
                        populateButtonLogic(history.getValue(), chValue, tpValue);
                    } else {
                        AbstractNotificationUtils.getErrorNotification("No Periods Selected",
                                "There are no historical periods selected from the Contract Holder History list view or the Trading Partner History list view.  "
                                + "\nPlease select at least one historical period and try again.");
                    }
                }

            });
            LOGGER.info("End of configureFields method");
        } catch (SystemException e) {

            LOGGER.error(e.getMessage());

        } catch (PortalException e) {

            LOGGER.error(e.getMessage());

        } catch (Exception e) {

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
        LOGGER.info("Entering isTpHistorySelected method");
        if (tradingPartner.getValue() != null && !tradingPartner.getValue().toString().isEmpty()) {
            return Boolean.TRUE;
        }
        LOGGER.info("End of isTpHistorySelected method");
        return Boolean.FALSE;
    }

    /**
     * To check if any period of the contract holder is checked.
     *
     * @return true if any of the check box in contract holder header is checked
     */
    public boolean isContractHistorySelected() {

        LOGGER.info("Entering isContractHistorySelected method");
        try {
            if (contract.getValue() != null && !contract.getValue().toString().isEmpty()) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End of isContractHistorySelected method");
        return Boolean.FALSE;
    }

    /**
     * Reset button.
     *
     * @return the button
     */
    public Button resetButton() {

        LOGGER.info("Entering resetButton method");

        return btnResetSearch;

    }

    /**
     * Gets the results.
     *
     * @return the results
     */
    private void getResults() {

        LOGGER.info("Entering getResults method");

        contractTableLayout.addComponent(contractHolderTable);
        table2.addComponent(tradingHistoryTable);

        contractTableLayout.setMargin(false);
        table2.setMargin(false);

        LOGGER.info("End of getResults method");

    }

    /**
     * Gets the calculator.
     *
     * @return the calculator
     */
    private VerticalLayout getCalculator() {
        final VerticalLayout content2 = new VerticalLayout();
        content2.setStyleName("adjust-label");
        LOGGER.info("Entering getCalculator method");
        content2.setSpacing(true);
        content2.setMargin(true);
        content2.addComponent(getCalculatorlayout());
        content2.addComponent(getCalculatorlayout1());
        LOGGER.info("End of getCalculator method");

        return content2;

    }

    /**
     * Gets the calculatorlayout.
     *
     * @return the calculatorlayout
     */
    private GridLayout getCalculatorlayout() {
        final GridLayout layout3 = new GridLayout(4, 1);

        LOGGER.info("Entering getCalculatorlayout method");
        layout3.setSpacing(true);
        salesOrUnits.setStyleName(Constant.HORIZONTAL);
        layout3.addComponent(new Label("Variable"));
        layout3.addComponent(salesOrUnits);
        layout3.addComponent(populate);
        LOGGER.info("End of getCalculatorlayout method");

        return layout3;
    }

    /**
     * Gets the calculatorlayout1.
     *
     * @return the calculatorlayout1
     */
    private GridLayout getCalculatorlayout1() {
        final GridLayout layout = new GridLayout(3, 8);

        LOGGER.info("Entering getCalculatorlayout1 method");
        layout.setSpacing(true);
        layout.addComponent(new Label("Sales/Units:"));
        layout.addComponent(sales);
        sales.addStyleName(Constant.TXT_RIGHT_ALIGN);
        layout.addComponent(new Label(StringUtils.EMPTY));
        layout.addComponent(new Label("Market Share (%):"));
        layout.addComponent(marketShare);
        marketShare.addStyleName(Constant.TXT_RIGHT_ALIGN);
        marketShare.addValidator(new RegexpValidator("^(0*100{1,1}\\.?((?<=\\.)0*)?%?$)|(^0*\\d{0,2}\\.?((?<=\\.)\\d{0,1})?%?)$", CORRECTVALUE));
        layout.addComponent(new Label(StringUtils.EMPTY));
        layout.addComponent(new Label("Analog Lives:"));
        layout.addComponent(analogLives);
        analogLives.addStyleName(Constant.TXT_RIGHT_ALIGN);
        analogLives.addValidator(new RegexpValidator("\\d*", CORRECTVALUE));
        layout.addComponent(calculateOne);
        layout.addComponent(new Label("Value Per Life"));
        layout.addComponent(valuePerLife);
        valuePerLife.addStyleName(Constant.TXT_RIGHT_ALIGN);
        layout.addComponent(new Label(StringUtils.EMPTY));
        layout.addComponent(new Label("Market Share (%):"));
        layout.addComponent(marketShare1);
        marketShare1.addStyleName(Constant.TXT_RIGHT_ALIGN);
        marketShare.addValidator(new RegexpValidator("^(0*100{1,1}\\.?((?<=\\.)0*)?%?$)|(^0*\\d{0,2}\\.?((?<=\\.)\\d{0,1})?%?)$", CORRECTVALUE));
        layout.addComponent(new Label(StringUtils.EMPTY));
        layout.addComponent(new Label("Projected Lives: "));
        layout.addComponent(projectedLives);
        projectedLives.addStyleName(Constant.TXT_RIGHT_ALIGN);
        projectedLives.addValidator(new RegexpValidator("\\d*", CORRECTVALUE));
        layout.addComponent(calculateTwo);
        layout.addComponent(new Label("Total Sales/Units:"));
        layout.addComponent(totalSales);
        totalSales.addStyleName(Constant.TXT_RIGHT_ALIGN);
        layout.addComponent(new Label(StringUtils.EMPTY));
        layout.addComponent(new Label("Projection Period Total"));
        layout.addComponent(projectionPeriodTotal);
        projectionPeriodTotal.addStyleName(Constant.TXT_RIGHT_ALIGN);
        layout.addComponent(new Label(StringUtils.EMPTY));
        LOGGER.info("End of getCalculatorlayout1 method");

        return layout;
    }

    /**
     * Load contract holder.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void loadContractHolder() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering loadContractHolder method");
        contract.setNullSelectionAllowed(Boolean.TRUE);
        contract.setNullSelectionItemId(Constant.SELECT_ONE);
        contract.addItem(Constant.SELECT_ONE);
        contract.select(Constant.SELECT_ONE);
        contract = nonMandatedLogic.loadPMPYContractHolders(projectionId, contract, contHolder);
        LOGGER.info("End of loadContractHolder method");
    }

    /**
     * Generate button logic.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void generateButtonLogic() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering generateButtonLogic method");
        chContainer.removeAllItems();
        tpContainer.removeAllItems();
        if (isContractHistorySelected()) {
            Object[] contractInputs = new Object[5];
            contractInputs[0] = String.valueOf(projectionId);
            contractInputs[1] = CommonUtils.getListToString(projectionDetailsId);
            contractInputs[2] = String.valueOf(contract.getValue() == null ? 0 : contract.getValue());
            contractInputs[3] = String.valueOf(0);
            contractInputs[4] = rightDto.getDoubleHistoryColumns();
            chContainer.addAll(logic.getNmPmpyResultList(contractInputs));

        }
        if (isTpHistorySelected()) {

            Object[] trPInputs = new Object[5];
            trPInputs[0] = String.valueOf(projectionId);
            trPInputs[1] = CommonUtils.getListToString(projectionDetailsId);
            trPInputs[2] = String.valueOf(0);
            trPInputs[3] = String.valueOf(tradingPartner.getData() == null ? 0 : tradingPartner.getData());
            trPInputs[4] = rightDto.getDoubleHistoryColumns();
            tpContainer.addAll(logic.getNmPmpyResultList(trPInputs));

        }
        LOGGER.info("End of generateButtonLogic method");

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
    private void populateButtonLogic(final String history, boolean chValue, boolean tpValue) {

        LOGGER.info("Entering populateButtonLogic method");
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
                for (PMPYRowDto dto : chContainer.getItemIds()) {
                    if (dto.getType().equals(Constant.SALES_SMALL)) {
                        actualSalesDto = dto;
                    } else if (dto.getType().equals(Constant.UNITS_SMALL)) {
                        actualUnitsDto = dto;
                    }
                }

                Double salesValue = 0.0;
                Double unitsValue = 0.0;
                for (Object key : chtCheckBoxMap) {

                    salesValue = Double.valueOf(String.valueOf(actualSalesDto.getProperties().get(key)).replace(",", StringUtils.EMPTY).replace(Constant.$, StringUtils.EMPTY));
                    unitsValue = Double.valueOf(String.valueOf(actualUnitsDto.getProperties().get(key)).replace(",", StringUtils.EMPTY).replace(Constant.$, StringUtils.EMPTY));
                    calculatedSalesValue += salesValue;
                    calculatedUnitsValue += unitsValue;
                }

            } else if (tpValue) {
                PMPYRowDto actualSalesDto = null;
                PMPYRowDto actualUnitsDto = null;
                for (PMPYRowDto dto : tpContainer.getItemIds()) {
                    if (dto.getType().equals(Constant.SALES_SMALL)) {
                        actualSalesDto = dto;
                    } else if (dto.getType().equals(Constant.UNITS_SMALL)) {
                        actualUnitsDto = dto;
                    }
                }
                Double salesValue = 0.0;
                Double unitsValue = 0.0;
                for (Object key : tptCheckBoxMap) {
                    salesValue = Double.valueOf(String.valueOf(actualSalesDto.getProperties().get(key)).replace(",", StringUtils.EMPTY).replace(Constant.$, StringUtils.EMPTY));
                    unitsValue = Double.valueOf(String.valueOf(actualUnitsDto.getProperties().get(key)).replace(",", StringUtils.EMPTY).replace(Constant.$, StringUtils.EMPTY));
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
        LOGGER.info("End of populateButtonLogic method");

    }

    /**
     * Gets the SALES_SMALL.
     *
     * @param obj the obj
     * @return the SALES_SMALL
     */
    public Double getSales(final Object obj) {
        LOGGER.info("Entering of getSales method");
        Double value = 0.0;

        value = Double.valueOf(String.valueOf(obj));
        dfSales.format(value);
        LOGGER.info("End of getSales method");

        return value;
    }

    /**
     * Gets the UNITS_SMALL.
     *
     * @param obj the obj
     * @return the UNITS_SMALL
     */
    public Double getUnits(final Object obj) {

        LOGGER.info("Entering of getUnits method");
        final Double value = Double.valueOf(String.valueOf(obj));
        dfUnits.format(value);
        LOGGER.info("End of getUnits method");

        return value;
    }

    /**
     * First calculation.
     */
    private void firstCalculation() {
        LOGGER.info("Entering of firstCalculation method");

        Double analogLivesValue;

        if (analogLives.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(analogLives.getValue())) && !Constant.NULL.equals(String.valueOf(analogLives.getValue()))
                && isNumeric(String.valueOf(analogLives.getValue())) && Double.valueOf(String.valueOf(analogLives.getValue())) != 0.0) {
            analogLivesValue = Double.valueOf(String.valueOf(analogLives.getValue()));
        } else {
            AbstractNotificationUtils.getErrorNotification("No Lives", "Please enter a numeric value for Lives.");
            valuePerLife.setValue(StringUtils.EMPTY);
            return;
        }
        final String tempSales = String.valueOf(sales.getValue()).replace(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY).replace(Constant.$, StringUtils.EMPTY);
        Double salesValue = 1.0;
        if (sales.getValue() != null && !StringUtils.EMPTY.equals(tempSales) && !Constant.NULL.equals(tempSales) && isNumeric(tempSales) && Double.valueOf(tempSales) != 0.0) {

            if (Constant.SALES.equalsIgnoreCase(getVariableValue())) {
                final DecimalFormat salesFormat = new DecimalFormat("####");
                salesValue = Double.valueOf(salesFormat.format(Double.valueOf(tempSales)));

            } else if (Constant.UNITS.equalsIgnoreCase(getVariableValue())) {
                final DecimalFormat unitFormat = new DecimalFormat("####.0");
                salesValue = Double.valueOf(unitFormat.format(Double.valueOf(tempSales)));

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
        LOGGER.info("End of firstCalculation method");

    }

    /**
     * Second calculation.
     */
    private void secondCalculation() {
        LOGGER.info("Entering of secondCalculation method");

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
        final String tempValuePerLifeValue = String.valueOf(valuePerLife.getValue()).replace(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY).replace(Constant.$, StringUtils.EMPTY);

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
            projectionPeriodTotalValue = projectionPeriodTotalCalculation(totalValue, true);
            projectionPeriodTotal.setValue(String.valueOf(MONEY_NO_DECIMAL.format(projectionPeriodTotalValue)));
        } else if (Constant.UNITS.equalsIgnoreCase(getVariableValue())) {
            totalSales.setValue(String.valueOf(dfCalculatedUnit.format(totalValue)));
            projectionPeriodTotalValue = projectionPeriodTotalCalculation(totalValue, false);
            projectionPeriodTotal.setValue(String.valueOf(dfCalculatedUnit.format(projectionPeriodTotalValue)));
        }
        LOGGER.info("End of secondCalculation method");

    }

    /**
     * Projection period total calculation.
     *
     * @param totalSales the total SALES_SMALL
     * @return the double
     */
    public Double projectionPeriodTotalCalculation(Double totalSales, boolean isSales) {
        LOGGER.info("Entering of projectionPeriodTotalCalculation method");
        Double totalSalesValue = 0.0;
        int count = 1;
        if (isContractCheckBoxSelected()) {
            count = chtCheckBoxMap.size();
        } else if (isTpCheckBoxSelectd()) {
            count = tptCheckBoxMap.size();
        }
        totalSalesValue = totalSales / count;
        LOGGER.info("End of projectionPeriodTotalCalculation method");

        return totalSalesValue;
    }

    /**
     * Checks if is numeric.
     *
     * @param str the str
     * @return true, if checks if is numeric
     */
    public boolean isNumeric(final String str) {
        LOGGER.info("Entering of isNumeric method with str=" + str);
        str.replace(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY);
        LOGGER.info("End of isNumeric method");
        return true;
    }

    /**
     * Gets the variable value.
     *
     * @return the variable value
     */
    public String getVariableValue() {
        String variableValue = StringUtils.EMPTY;

        LOGGER.info("Entering of getVariableValue method");
        if (salesOrUnits.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(salesOrUnits.getValue())) && !Constant.NULL.equals(String.valueOf(salesOrUnits.getValue()))
                && Constant.SALES_DOLLARS.equalsIgnoreCase(String.valueOf(salesOrUnits.getValue()))) {
            variableValue = Constant.SALES;
        }
        if (salesOrUnits.getValue() != null && !StringUtils.EMPTY.equals(String.valueOf(salesOrUnits.getValue())) && !Constant.NULL.equals(String.valueOf(salesOrUnits.getValue()))
                && Constant.UNIT_VOLUME.equalsIgnoreCase(String.valueOf(salesOrUnits.getValue()))) {
            variableValue = Constant.UNITS;

        }
        LOGGER.info("End of getVariableValue method");

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
        final Object[] inputs = new Object[9];

        String value = String.valueOf(projectionPeriodTotal.getValue());

        LOGGER.info("Entering of importButtonLogic method");
        if (StringUtils.EMPTY.equals(value) || Constant.NULL.equals(value)) {
            AbstractNotificationUtils.getErrorNotification("No Projection Period Total", "Please complete the PMPY calculation before clicking IMPORT.");
            return;
        }

        final String variableValue = getVariableValue();

        value = value.replace(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY).replace(Constant.$, StringUtils.EMPTY);

        if (Constant.SALES.equalsIgnoreCase(variableValue)) {
            final DecimalFormat salesFormat = new DecimalFormat("####");
            value = String.valueOf(salesFormat.format(Double.valueOf(value)));
        } else if (Constant.UNITS.equalsIgnoreCase(variableValue)) {
            final DecimalFormat unitFormat = new DecimalFormat("####.0");
            value = String.valueOf(unitFormat.format(Double.valueOf(value)));
        }
        if (effectivePeriod.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("No Effective Date", "Please select an Effective Period.");
            return;
        }
        final String finalValue = value;

        if (StringUtils.EMPTY.equals(finalValue) || Constant.NULL.equals(finalValue)) {
            AbstractNotificationUtils.getErrorNotification("No Projection Period Total", "Please complete the PMPY calculation before clicking IMPORT.");
        } else {
            final String projCol = "projCol";
            String tempValue = StringUtils.EMPTY;
            String otherTempValue = StringUtils.EMPTY;
            final int currentYear = calendar.get(Calendar.YEAR);
            final int yearOne = currentYear + 1;
            final int yearTwo = currentYear + 2;
            final int yearThree = currentYear + 3;
            String queryName = StringUtils.EMPTY;
            final int[] yearArray = new int[]{currentYear, yearOne, yearTwo, yearThree};
            final List yearArrayList = new ArrayList();
            for (int i = 0; i < yearArray.length; i++) {
                yearArrayList.add(yearArray[i]);
            }
            final SimpleDateFormat yearFormatter = new SimpleDateFormat("yyyy");
            int annual = Integer.parseInt(yearFormatter.format(date));
            Double calculatedValue = 0.0;

            if (Constant.SALES.equals(variableValue)) {
                tempValue = Constant.SALES_CAPS;
                otherTempValue = Constant.UNITS_SMALL;
                queryName = "Check GTS Sales Values";
                calculatedValue = Double.valueOf(dfSales.format(Double.valueOf(finalValue)));
            }
            if (Constant.UNITS.equals(variableValue)) {
                tempValue = Constant.UNITS_SMALL;
                queryName = "Check GTS UNT Values";
                otherTempValue = Constant.SALES_CAPS;
                calculatedValue = Double.valueOf(dfUnits.format(Double.valueOf(finalValue)));
            }
            calculatedValue = getDividedValue(calculatedValue);
            int startQuator = effectivePeriod.getValue().toString().charAt(1) - 48;
            annual = Integer.valueOf(effectivePeriod.getValue().toString().substring(3, 7));
            inputs[0] = projectionDetailsId;
            inputs[1] = calculatedValue;
            inputs[2] = annual;
            inputs[3] = startQuator;
            inputs[4] = tempValue;

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

                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(Constant.YES)) {
                                    try {
                                        logic.importPMPY(inputs, projectionSelectionDTO);
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

        LOGGER.info("End of importButtonLogic method");
    }

    /**
     * Export calculated excel.
     */
    private void exportCalculatedExcel() {
        try {
            LOGGER.info("Entering of exportCalculatedExcel method");
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
            LOGGER.info("End of exportCalculatedExcel method");
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
    private Double getPercentage(final Double percentage) {

        LOGGER.info("Entering of getPercentage method");
        final Double percentageValue = percentage / Double.valueOf(100);
        LOGGER.info("End of getPercentage method");

        return percentageValue;
    }

    /**
     * Gets the divided value.
     *
     * @param actualValue the actual value
     * @return the divided value
     */
    public double getDividedValue(final Double actualValue) {
        double returnValue = 0;

        LOGGER.info("Entering of getDividedValue method");
        final DecimalFormat formatter = new DecimalFormat("#.000000");
        if (actualValue != Constant.ZERO) {
            Double temp = actualValue;

            temp = Double.valueOf(formatter.format(temp));
            returnValue = temp.isInfinite() || temp.isNaN() ? 0 : temp;

        }
        LOGGER.info("End of getDividedValue method");

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

        contractHolderTable.setWidth(800, UNITS_PIXELS);
        tradingHistoryTable.setWidth(800, UNITS_PIXELS);
        contractHolderTable.markAsDirty();
        tradingHistoryTable.markAsDirty();
        chContainer = new CustomTreeContainer<PMPYRowDto>(PMPYRowDto.class);
        tpContainer = new CustomTreeContainer<PMPYRowDto>(PMPYRowDto.class);

        tradingHistoryTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        contractHolderTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        tradingHistoryTable.setPageLength(3);
        contractHolderTable.setPageLength(3);
        contractHolderTable.setHeight("160px");
        tradingHistoryTable.setHeight("160px");

        Map<Object, Class> properties = new HashMap<Object, Class>();

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
            contractHolderTable.setColumnWidth(obj, 130);
            tradingHistoryTable.setColumnWidth(obj, 130);
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
        final PMPYContractHolderHistoryChart chart = new PMPYContractHolderHistoryChart(chContainer.getItemIds(), (String) contract.getValue(), rightDto.getDoubleHistoryColumns());
        final NmSalesGraphWindow salesGraphWindow = new NmSalesGraphWindow(chart.getCharts(), "PMPY Calculator");
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

        final PMPYTradingPartnerHistoryChart chart = new PMPYTradingPartnerHistoryChart(tpContainer.getItemIds(), tradingPartner.getValue(), rightDto.getDoubleHistoryColumns());
        final NmSalesGraphWindow salesGraphWindow = new NmSalesGraphWindow(chart.getCharts(), "PMPY Calculator");
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
