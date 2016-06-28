/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.forecast.ui.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.forecast.dto.ForecastDTO;
import com.stpl.app.adminconsole.forecast.dto.ForecastFilterGenerator;
import com.stpl.app.adminconsole.forecast.logic.ForecastLogic;
import com.stpl.app.adminconsole.forecast.logic.ForecastTableLogic;
import com.stpl.app.adminconsole.itemgroup.util.UISecurityUtil;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import static com.stpl.app.adminconsole.util.ResponsiveUtils.getResponsiveControls;
import com.stpl.app.adminconsole.util.ValidationUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 * The Class ForecastSearchIndex.
 *
 * @author mohamed
 */
public class ForecastSearchIndex extends CustomComponent implements View {

    private static final Logger LOGGER = Logger.getLogger(ForecastSearchIndex.class);

    public ErrorfulFieldGroup forecastBinder;

    @UiField("errorMsg")
    private ErrorLabel errorMsg;

    @UiField("businessProcess")
    private ComboBox businessProcess;

    private PopupDateField fromDate = new PopupDateField();

    @UiField("historicalInterval")
    private ComboBox historicalInterval;

    @UiField("historicalValue")
    private TextField historicalValue;

    @UiField("historicalPeriod")
    private PopupDateField historicalPeriod;

    @UiField("futureInterval")
    private ComboBox futureInterval;

    @UiField("futureValue")
    private TextField futureValue;

    @UiField("futurePeriod")
    private PopupDateField futurePeriod;

    private PopupDateField toDate = new PopupDateField();

    private TextField forecastYear = new TextField();

    @UiField("saveBtn")
    private Button saveBtn;

    @UiField("resetBtn")
    private Button resetBtn;

    public ForecastDTO forecastDTO = new ForecastDTO();

    private ForecastLogic forecastLogic = new ForecastLogic();

    @UiField("mode")
    private OptionGroup mode;

    @UiField("processType")
    private OptionGroup processType;

    @UiField("fromLabel")
    private Label fromLabel;

    @UiField("toLabel")
    private Label toLabel;

    @UiField("historyLabel")
    private Label historyLabel;

    @UiField("futureLabel")
    private Label futureLabel;

    @UiField("historyIntervalLabel")
    private Label historyIntervalLabel;

    @UiField("futureIntervalLabel")
    private Label futureIntervalLabel;
    @UiField("resultsTable")
    private Panel resultsTable;

    @UiField("excelExport")
    private Button excelExport;

    @UiField("historicPeriodLabel")
    private Label historicPeriodLabel;

    @UiField("forecastPeriodLabel")
    private Label forecastPeriodLabel;

    @UiField("historicPeriod")
    private TextField historicPeriod;

    @UiField("forecastPeriod")
    private TextField forecastPeriod;
    String historicalDdlbValue = StringUtils.EMPTY;
    String futureDdlbValue = StringUtils.EMPTY;
    /**
     * The results bean.
     */
    public BeanItemContainer<ForecastDTO> resultsBean = new BeanItemContainer<ForecastDTO>(ForecastDTO.class);
    /**
     * The Constant DISCOUNT_TABLE_COLUMNS.
     */
    public static final Object[] FORECAST_TABLE_COLUMNS = new Object[]{
        "businessProcess", "processType", "mode", "fromDateSearch", "toDateSearch", "historicalInterval", "historicalValue", "futureInterval", "futureValue", "versionNo", "fromPeriod", "toPeriod", "createdBy", "activeFlag"};
    /**
     * The Constant DISCOUNT_TABLE_HEADER.
     */
    public static final String[] FORECAST_TABLE_HEADER = new String[]{
        "Business Process", "Process Type", "Process Mode", "From Date", "To Date", "Historic Interval", "Historic Period", "Future Interval", "Future Period", "Version", "From Period", "To Period", "Created By", "Active Flag"};
    @UiField("tableLayout")
    VerticalLayout tableLayout;
    @UiField("buttonLayout")
    HorizontalLayout buttonLayout;
    private HorizontalLayout controlLayout = new HorizontalLayout();
    ForecastTableLogic tableLogic = new ForecastTableLogic();
    ExtPagedTable results = new ExtPagedTable(tableLogic);
    private ExtFilterTable excelTable;
    private BeanItemContainer<ForecastDTO> excelTableBean;
    CommonUtil commonUtil = new CommonUtil();
    CommonSecurityLogic commonSecurity = new CommonSecurityLogic();
    @UiField("cssLayout1")
    private CssLayout cssLayout1;

    /**
     * Gets the forecast binder.
     *
     * @return the forecast binder
     */
    public ErrorfulFieldGroup getForecastBinder() {
        return forecastBinder;
    }

    /**
     * Sets the forecast binder.
     *
     * @param forecastBinder the new forecast binder
     */
    public void setForecastBinder(final ErrorfulFieldGroup forecastBinder) {
        this.forecastBinder = forecastBinder;
    }

    /**
     * Gets the business process.
     *
     * @return the business process
     */
    public ComboBox getBusinessProcess() {
        return businessProcess;
    }

    /**
     * Sets the business process.
     *
     * @param businessProcess the new business process
     */
    public void setBusinessProcess(final ComboBox businessProcess) {
        this.businessProcess = businessProcess;
    }

    /**
     * Gets the forecast year.
     *
     * @return the forecast year
     */
    public TextField getForecastYear() {
        return forecastYear;
    }

    /**
     * Sets the forecast year.
     *
     * @param forecastYear the new forecast year
     */
    public void setForecastYear(final TextField forecastYear) {
        this.forecastYear = forecastYear;
    }

    /**
     * Gets the save btn.
     *
     * @return the save btn
     */
    public Button getSaveBtn() {
        return saveBtn;
    }

    /**
     * Sets the save btn.
     *
     * @param saveBtn the new save btn
     */
    public void setSaveBtn(final Button saveBtn) {
        this.saveBtn = saveBtn;
    }

    /**
     * Gets the from date.
     *
     * @return the from date
     */
    public PopupDateField getFromDate() {
        return fromDate;
    }

    /**
     * Sets the from date.
     *
     * @param fromDate the new from date
     */
    public void setFromDate(PopupDateField fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Gets the historical interval.
     *
     * @return the historical interval
     */
    public ComboBox getHistoricalInterval() {
        return historicalInterval;
    }

    /**
     * Sets the historical interval.
     *
     * @param historicalInterval the new historical interval
     */
    public void setHistoricalInterval(ComboBox historicalInterval) {
        this.historicalInterval = historicalInterval;
    }

    /**
     * Gets the historical period.
     *
     * @return the historical period
     */
    public PopupDateField getHistoricalPeriod() {
        return historicalPeriod;
    }

    /**
     * Sets the historical period.
     *
     * @param historicalPeriod the new historical period
     */
    public void setHistoricalPeriod(PopupDateField historicalPeriod) {
        this.historicalPeriod = historicalPeriod;
    }

    /**
     * Gets the future interval.
     *
     * @return the future interval
     */
    public ComboBox getFutureInterval() {
        return futureInterval;
    }

    /**
     * Sets the future interval.
     *
     * @param futureInterval the new future interval
     */
    public void setFutureInterval(ComboBox futureInterval) {
        this.futureInterval = futureInterval;
    }

    /**
     * Gets the future period.
     *
     * @return the future period
     */
    public PopupDateField getFuturePeriod() {
        return futurePeriod;
    }

    /**
     * Sets the future period.
     *
     * @param futurePeriod the new future period
     */
    public void setFuturePeriod(PopupDateField futurePeriod) {
        this.futurePeriod = futurePeriod;
    }

    /**
     * Gets the to date.
     *
     * @return the to date
     */
    public PopupDateField getToDate() {
        return toDate;
    }

    /**
     * Sets the to date.
     *
     * @param toDate the new to date
     */
    public void setToDate(PopupDateField toDate) {
        this.toDate = toDate;
    }

    /**
     * Gets the reset btn.
     *
     * @return the reset btn
     */
    public Button getResetBtn() {
        return resetBtn;
    }

    /**
     * Sets the reset btn.
     *
     * @param resetBtn the new reset btn
     */
    public void setResetBtn(Button resetBtn) {
        this.resetBtn = resetBtn;
    }

    /**
     * Gets the results.
     *
     * @return the results
     */
    public ExtFilterTable getResults() {
        return results;
    }

    /**
     * Sets the results.
     *
     * @param results the new results
     */
    public void setResults(final ExtPagedTable results) {
        this.results = results;
    }

    /**
     * Gets the forecast dto.
     *
     * @return the forecast dto
     */
    public ForecastDTO getForecastDTO() {
        return forecastDTO;
    }

    /**
     * Sets the forecast dto.
     *
     * @param forecastDTO the new forecast dto
     */
    public void setForecastDTO(final ForecastDTO forecastDTO) {
        this.forecastDTO = forecastDTO;
    }

    /**
     * Gets the forecast logic.
     *
     * @return the forecast logic
     */
    public ForecastLogic getForecastLogic() {
        return forecastLogic;
    }

    /**
     * Sets the forecast logic.
     *
     * @param forecastLogic the new forecast logic
     */
    public void setForecastLogic(final ForecastLogic forecastLogic) {
        this.forecastLogic = forecastLogic;
    }

    /**
     * Gets the results bean.
     *
     * @return the results bean
     */
    public BeanItemContainer<ForecastDTO> getResultsBean() {
        return resultsBean;
    }

    /**
     * Sets the results bean.
     *
     * @param resultsBean the new results bean
     */
    public void setResultsBean(final BeanItemContainer<ForecastDTO> resultsBean) {
        this.resultsBean = resultsBean;
    }

    /**
     * Gets the logger.
     *
     * @return the logger
     */
    public static Logger getLogger() {
        return LOGGER;
    }

    /**
     * Gets the error msg.
     *
     * @return the error msg
     */
    public ErrorLabel getErrorMsg() {
        return errorMsg;
    }

    /**
     * Gets the forecast table columns.
     *
     * @return the forecast table columns
     */
    public static Object[] getForecastTableColumns() {
        return FORECAST_TABLE_COLUMNS;
    }

    /**
     * Gets the forecast table header.
     *
     * @return the forecast table header
     */
    public static String[] getForecastTableHeader() {
        return FORECAST_TABLE_HEADER;
    }

    /**
     * Instantiates a new forecast search index.
     *
     * @param forecastBinder the forecast binder
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public ForecastSearchIndex(final ErrorfulFieldGroup forecastBinder) throws SystemException, PortalException, Exception {

        super();
        this.forecastBinder = new ErrorfulFieldGroup(new BeanItem<ForecastDTO>(forecastDTO));
        try {
            init();
            configureFields();
        } catch (Exception e) {

            LOGGER.error(e);
        }
    }

    /**
     * Inits the.
     *
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private void init() throws SystemException, PortalException, Exception {
        LOGGER.info("ForecastSearchIndex init method is started");
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/forecastConfiguration.xml"), this));
        configureTable();
        getBinder();
        getPermissionDetails();
        LOGGER.info("ForecastSearchIndex init method ends");
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {
        LOGGER.info("getBinder method started");
        forecastBinder.bindMemberFields(this);
        forecastBinder.setItemDataSource(new BeanItem<ForecastDTO>(forecastDTO));
        forecastBinder.setBuffered(true);
        forecastBinder.setErrorDisplay(errorMsg);
        LOGGER.info("getBinder method returns getBinder");
        return forecastBinder;
    }

    /**
     * (non-Javadoc).
     *
     * @param event the event
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }

    /**
     * Configure fields.
     */
    private void configureFields() throws Exception {

        CommonUtils commonUtil = CommonUtils.getInstance();
        commonUtil.loadComboBox(businessProcess, ConstantsUtils.BUSINESS_PROCESS_TYPE, false);

        businessProcess.setRequired(true);
        businessProcess.setRequiredError("Please Select Business Process");
        businessProcess.setValidationVisible(true);
        businessProcess.setImmediate(true);
        commonUtil.loadComboBox(historicalInterval, ConstantsUtils.FORECAST_FREQUENCY, false);
        commonUtil.loadComboBox(futureInterval, ConstantsUtils.FORECAST_FREQUENCY, false);
        futureInterval.setRequired(true);
        historicalInterval.setRequired(true);
        futureValue.setRequired(true);
        historicalValue.setRequired(true);
        futureInterval.setRequiredError("Please Select Future Interval Frequency");
        historicalInterval.setRequiredError("Please Select Historical Data Frequency");
        futureInterval.setValidationVisible(true);
        historicalInterval.setValidationVisible(true);
        futureValue.setImmediate(true);
        futureValue.addValidator(new RegexpValidator(ValidationUtils.NUMERIC_VALIDATION, "Interval Values can contains only Number"));
        futureValue.setRequiredError("Please Enter Future Interval");
        futureValue.setValidationVisible(true);
        historicalValue.setRequiredError("Please Enter Historical Data Interval");
        historicalValue.setValidationVisible(true);
        historicalValue.addValidator(new RegexpValidator(ValidationUtils.NUMERIC_VALIDATION, "Interval Values can contains only Number"));
        historicalValue.setMaxLength(3);
        futureValue.setMaxLength(3);

        mode.addItem(ConstantsUtils.INTERVAL);
        mode.addItem(ConstantsUtils.PERIOD);
        mode.select(ConstantsUtils.INTERVAL);
        mode.setImmediate(true);
        mode.setStyleName(ConstantsUtils.HORIZONTAL);
        processType.addItem(ConstantsUtils.AUTO_UPDATE);
        processType.addItem(ConstantsUtils.DEFINED);
        processType.select(ConstantsUtils.DEFINED);
        processType.setImmediate(true);

        processType.setStyleName(ConstantsUtils.HORIZONTAL);

        historicalInterval.setImmediate(true);
        futureInterval.setImmediate(true);
        futureIntervalLabel.setImmediate(true);
        historyIntervalLabel.setImmediate(true);
        futureLabel.setImmediate(true);
        historyLabel.setImmediate(true);

        futureIntervalLabel.setVisible(true);
        historyIntervalLabel.setVisible(true);
        historyLabel.setVisible(true);
        futureLabel.setVisible(true);
        historicalInterval.setVisible(true);
        futureInterval.setVisible(true);
        futureValue.setVisible(true);
        historicalValue.setVisible(true);
        fromLabel.setVisible(false);
        historicalPeriod.setVisible(false);
        toLabel.setVisible(false);
        futurePeriod.setVisible(false);

        fromDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        toDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        toDate.setImmediate(true);

        excelExport.setDescription(ConstantsUtils.EXCEL_EXPORT);
        excelExport.setIcon(new ThemeResource("../../icons/excel.png"));
        excelExport.setStyleName("link");
        historicPeriodLabel.setEnabled(false);
        historicPeriod.setEnabled(false);
        forecastPeriodLabel.setEnabled(false);
        forecastPeriod.setEnabled(false);
        historicalValue.setImmediate(true);
        loadGrid();

        mode.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * The value change
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (mode.getValue() == ConstantsUtils.INTERVAL) {
                    if (processType.getValue() == ConstantsUtils.AUTO_UPDATE) {
                        futureLabel.setEnabled(false);
                        futureInterval.setEnabled(false);
                        futureIntervalLabel.setEnabled(false);
                        futureValue.setEnabled(false);

                    } else {
                        futureLabel.setEnabled(true);
                        futureInterval.setEnabled(true);
                        futureIntervalLabel.setEnabled(true);
                        futureValue.setEnabled(true);
                        futureInterval.setRequired(true);
                        futureValue.setRequired(true);
                    }
                    fromLabel.setVisible(false);
                    historicalPeriod.setVisible(false);
                    toLabel.setVisible(false);
                    futurePeriod.setVisible(false);

                    futureInterval.setVisible(true);
                    historicalInterval.setVisible(true);
                    futureLabel.setVisible(true);
                    historyLabel.setVisible(true);
                    futureIntervalLabel.setVisible(true);
                    historyIntervalLabel.setVisible(true);
                    futureValue.setVisible(true);
                    historicalValue.setVisible(true);

                    historicalInterval.setRequired(true);

                    historicalValue.setRequired(true);

                    historicPeriodLabel.setVisible(true);
                    historicPeriod.setVisible(true);
                    forecastPeriodLabel.setVisible(true);
                    forecastPeriod.setVisible(true);

                    futureInterval.setImmediate(true);
                    historicalInterval.setImmediate(true);
                    futureValue.setImmediate(true);
                    historicalValue.setImmediate(true);

                    historicalPeriod.setRequired(false);
                    futurePeriod.setRequired(false);
                    historicalPeriod.setImmediate(true);
                    futurePeriod.setImmediate(true);

                } else {
                    if (processType.getValue() == ConstantsUtils.AUTO_UPDATE) {
                        futurePeriod.setEnabled(false);

                    } else {
                        futurePeriod.setEnabled(true);
                        futurePeriod.setRequired(true);
                        futurePeriod.setRequiredError("Please Enter Historical Data Time Period To");
                        futurePeriod.setValidationVisible(true);
                    }
                    historicalPeriod.setRequired(true);

                    historicalPeriod.setRequiredError("Please Enter Historical Data Time Period From");

                    historicalPeriod.setValidationVisible(true);

                    futureInterval.setRequired(false);
                    historicalInterval.setRequired(false);
                    futureValue.setRequired(false);
                    historicalValue.setRequired(false);

                    fromLabel.setVisible(true);
                    historicalPeriod.setVisible(true);
                    toLabel.setVisible(true);
                    futurePeriod.setVisible(true);
                    futureInterval.setVisible(false);
                    historicalInterval.setVisible(false);
                    futureLabel.setVisible(false);
                    historyLabel.setVisible(false);
                    futureIntervalLabel.setVisible(false);
                    historyIntervalLabel.setVisible(false);
                    futureValue.setVisible(false);
                    historicalValue.setVisible(false);

                    historicPeriodLabel.setVisible(false);
                    historicPeriod.setVisible(false);
                    forecastPeriodLabel.setVisible(true);
                    forecastPeriod.setVisible(true);
                }
                fromDate.setValue(null);
                toDate.setValue(null);
                historicalInterval.setValue(null);
                futureInterval.setValue(null);
                historicalPeriod.setValue(null);
                futurePeriod.setValue(null);
                historicalValue.setValue(ConstantsUtils.EMPTY);
                futureValue.setValue(ConstantsUtils.EMPTY);
            }
        });
        processType.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * The value change
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (processType.getValue() == ConstantsUtils.AUTO_UPDATE) {
                    if (mode.getValue() == ConstantsUtils.INTERVAL) {
                        futureLabel.setEnabled(false);
                        futureInterval.setEnabled(false);
                        futureIntervalLabel.setEnabled(false);
                        futureValue.setEnabled(false);
                        futureInterval.setRequired(false);
                        futureValue.setRequired(false);

                    } else {

                        futurePeriod.setEnabled(false);
                        futurePeriod.setRequired(false);
                    }
                } else {

                    if (mode.getValue() == ConstantsUtils.INTERVAL) {
                        futureLabel.setEnabled(true);
                        futureInterval.setEnabled(true);
                        futureIntervalLabel.setEnabled(true);
                        futureValue.setEnabled(true);
                        futureInterval.setRequired(true);
                        futureValue.setRequired(true);

                    } else {
                        futurePeriod.setEnabled(true);
                        futurePeriod.setRequired(true);
                        futurePeriod.setRequiredError("Please Enter Historical Data Time Period To");
                        futurePeriod.setValidationVisible(true);

                    }

                }

            }
        });

        excelExport.addClickListener(new Button.ClickListener() {
            /**
             * Method is called when available excel export button is clicked
             */
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.info("In configureFields selectedResultsExcelExport.addClickListener started");
                try {
                    configureExcelResultTable();
                    loadExcelTable();
                    ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(excelTable), "Forecast Configuration", "Forecast Configuration", "Forecast Configuration.xls", false);
                    excel.export();
                    tableLayout.removeComponent(excelTable);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
                LOGGER.info("In configureFields selectedResultsExcelExport.addClickListener Ended");
            }
        });
        historicalInterval.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                historicalDdlbValue = String.valueOf(historicalInterval.getValue()) == null ? ConstantsUtils.EMPTY : String.valueOf(historicalInterval.getValue());
                historicalValue.setValue(StringUtils.EMPTY);
                historicPeriod.setValue(StringUtils.EMPTY);
            }
        });
        historicalValue.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                final String value = historicalValue.getValue();
                if (historicalDdlbValue.equals(ConstantsUtils.ANNUAL)) {
                    if (!value.equals(ConstantsUtils.EMPTY)) {
                        if (Integer.valueOf(value) <= 3) {
                            historicPeriod.setValue(CommonUtils.getHistoryDetail(Integer.valueOf(value), historicalDdlbValue, new Date()));
                        } else {
                            historicalValue.setValue(StringUtils.EMPTY);
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "History value should be less than or equal to 3.", ButtonId.OK);
                        }
                    }
                }
                if (historicalDdlbValue.equals(ConstantsUtils.SEMI_ANNUAL)) {
                    if (!value.equals(ConstantsUtils.EMPTY)) {
                        if (Integer.valueOf(value) <= 6) {
                            historicPeriod.setValue(CommonUtils.getHistoryDetail(Integer.valueOf(value), historicalDdlbValue, new Date()));
                        } else {
                            historicalValue.setValue(StringUtils.EMPTY);
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "History value should be less than or equal to 6.", ButtonId.OK);
                        }
                    }
                }
                if (historicalDdlbValue.equals(ConstantsUtils.QUARTER)) {
                    if (!value.equals(ConstantsUtils.EMPTY)) {
                        if (Integer.valueOf(value) <= 12) {
                            historicPeriod.setValue(CommonUtils.getHistoryDetail(Integer.valueOf(value), historicalDdlbValue, new Date()));

                        } else {
                            historicalValue.setValue(StringUtils.EMPTY);
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "History value should be less than or equal to 12.", ButtonId.OK);
                        }
                    }
                }
                if (historicalDdlbValue.equals(ConstantsUtils.MONTHLY)) {
                    if (!value.equals(ConstantsUtils.EMPTY)) {
                        if (Integer.valueOf(value) <= 36) {
                            historicPeriod.setValue(CommonUtils.getHistoryDetail(Integer.valueOf(value), historicalDdlbValue, new Date()));
                        } else {
                            historicalValue.setValue(StringUtils.EMPTY);
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "History value should be less than or equal to 36.", ButtonId.OK);
                        }
                    }
                }
            }
        });
        final Date gtsDate = new CommonUtils().getCurrentGTSToDate(ConstantsUtils.EX_FACTORY_SALES);
        final Calendar gtsCal = Calendar.getInstance();
        gtsCal.setTime(gtsDate);
        String str = CommonUtils.getMonthForInt(gtsCal.get(Calendar.MONTH)) + " " + gtsCal.get(Calendar.YEAR);
        forecastPeriod.setValue(str);

        futureInterval.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                futureDdlbValue = String.valueOf(futureInterval.getValue()) == null ? ConstantsUtils.EMPTY : String.valueOf(futureInterval.getValue());
                final String value = futureValue.getValue();
                if (!value.equals(ConstantsUtils.EMPTY)) {
                    Calendar futureDate = forecastLogic.convertPeriod("Projection", futureDdlbValue, Integer.valueOf(value));
                    if (futureDate.getTime().after(gtsCal.getTime())) {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "The entered Future period is beyond the GTS file. All periods beyond the GTS file will display zeros in the Forecast", ButtonId.OK);
                    }
                }
            }
        });
        futureValue.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                final String value = futureValue.getValue();
                if (!value.equals(ConstantsUtils.EMPTY)) {
                    Calendar futureDate = forecastLogic.convertPeriod("Projection", futureDdlbValue, Integer.valueOf(value));
                    if (futureDate.getTime().after(gtsCal.getTime())) {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "The entered Future period is beyond the GTS file. All periods beyond the GTS file will display zeros in the Forecast", ButtonId.OK);
                    }
                }
            }
        });

        futurePeriod.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                final Date toDateVal = futurePeriod.getValue();
                if (toDateVal != null) {
                    if (toDateVal.after(gtsCal.getTime())) {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "The entered Future period is beyond the GTS file. All periods beyond the GTS file will display zeros in the Forecast", ButtonId.OK);
                    }
                }
            }
        });

        historicalPeriod.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                final Date toDateVal = historicalPeriod.getValue();
                if (toDateVal != null) {
                    DateFormat df = new SimpleDateFormat("yyyy");
                    String toDate = df.format(toDateVal);
                    Calendar lastCal = Calendar.getInstance();
                    int a = lastCal.get(Calendar.YEAR) - Integer.parseInt(toDate);
                    if (a > 3) {

                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "History year should be less than or equal to 3 years.", ButtonId.OK);
                        historicalPeriod.setValue(null);
                    }
                    if (a <= 0) {

                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "History year should be less than current year.", ButtonId.OK);
                        historicalPeriod.setValue(null);
                    }
                }
            }
        });
    }

    /**
     * Save on click.
     */
    private void saveButtonLogic() {
        try {
            String status = forecastLogic.saveForecastYears(forecastDTO);
            if (status.equalsIgnoreCase(ConstantsUtils.SUCCESS)) {
                loadGrid();
                CommonUIUtils.getMessageNotification(businessProcess.getValue().toString() + " business process Saved Successfully.");
            }
        } catch (Exception ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4005));
            LOGGER.error(ex);
        }

    }

    /**
     * Reset btn logic.
     */
    private void resetBtnLogic() {

        businessProcess.setValue(null);
        fromDate.setValue(null);
        toDate.setValue(null);
        historicalInterval.setValue(null);
        futureInterval.setValue(null);
        historicalPeriod.setValue(null);
        futurePeriod.setValue(null);
        historicalValue.setValue(ConstantsUtils.EMPTY);
        futureValue.setValue(ConstantsUtils.EMPTY);
    }

    /**
     * TO load the grid
     */
    private void loadGrid() {
        try {
            tableLogic.configureSearchData();
            results.setFilterDecorator(new ExtDemoFilterDecorator());
            results.setFilterGenerator(new ForecastFilterGenerator());
            results.setImmediate(true);
            results.setWidth(99, UNITS_PERCENTAGE);
            results.addStyleName("TableCheckBox");
            results.setSelectable(true);
            results.markAsDirtyRecursive();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void configureTable() throws Exception {

        tableLayout.addComponent(results);
        getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);

        tableLogic.setContainerDataSource(resultsBean);
        tableLogic.setPageLength(15);
        tableLogic.sinkItemPerPageWithPageLength(false);
        setTableDefaultConfig();
        Object[] objColumn = FORECAST_TABLE_COLUMNS;
        for (Object objColumn1 : objColumn) {
            String value = objColumn1.toString();
            if (value.endsWith("Date") || value.contains("fromPeriod") || value.contains("toPeriod")) {
                results.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
            }
        }
        results.setSelectable(true);
        results.markAsDirty();

        results.setConverter("fromDateSearch", new DateToStringConverter());
        results.setConverter("toDateSearch", new DateToStringConverter());
        results.setComponentError(null);
        results.setFilterBarVisible(true);
        results.setFilterDecorator(new ExtDemoFilterDecorator());
        results.setFilterGenerator(new ForecastFilterGenerator());
        results.setValidationVisible(false);
        results.addStyleName("filterbar");

    }

    public void setTableDefaultConfig() throws Exception {
        try {
            results.setVisibleColumns(FORECAST_TABLE_COLUMNS);
            results.setColumnHeaders(FORECAST_TABLE_HEADER);
            results.markAsDirtyRecursive();
            results.setImmediate(true);
            results.setWidth(99, UNITS_PERCENTAGE);
            results.setHeight("250px");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * To configure Excel Results Table
     */
    private void configureExcelResultTable() {
        excelTableBean = new BeanItemContainer<>(ForecastDTO.class);
        excelTable = new ExtFilterTable();
        tableLayout.addComponent(excelTable);
        excelTable.setVisible(false);
        excelTable.setContainerDataSource(excelTableBean);
        excelTable.setVisibleColumns(FORECAST_TABLE_COLUMNS);
        excelTable.setColumnHeaders(FORECAST_TABLE_HEADER);
        excelTable.markAsDirtyRecursive();

    }

    /**
     * To load excel Table similar to Table in UI
     *
     * @param tableFieldLookUpDTO
     * @throws Exception
     */
    private void loadExcelTable() throws Exception {
        excelTableBean.removeAllItems();
        if (results.size() != 0) {
            ForecastLogic logic = new ForecastLogic();
            int recordCount = (Integer) logic.searchForecast(0, 0, null, null, true);
            List<ForecastDTO> resultList = (List<ForecastDTO>) logic.searchForecast(0, recordCount, null, null, false);
            excelTableBean.addAll(resultList);
        }
    }

    /**
     * To get user level permissions
     */
    private void getPermissionDetails() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));

            final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.FORCAST_CONFIGUARATION + "," + "Functional Screen");

            getButtonPermission(functionCompanyHM);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    /**
     * *
     * Commented because field level security is not need here as all fields are
     * mandatory
     *
     * @param functionCompanyHM
     */
    /**
     * This method is used to implement security for buttons
     *
     * @param functionCompanyHM
     */
    private void getButtonPermission(Map<String, AppPermission> functionCompanyHM) {
        if (functionCompanyHM.get(ConstantsUtils.SAVE_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.SAVE_BUTTON)).isFunctionFlag()) {
            saveBtn.setVisible(false);
        } else {
            saveButton();
        }
        if (functionCompanyHM.get(ConstantsUtils.RESET_BUTTON) != null && !((AppPermission) functionCompanyHM.get(ConstantsUtils.RESET_BUTTON)).isFunctionFlag()) {
            resetBtn.setVisible(false);
        } else {
            resetButton();
        }

    }

    /**
     * reset buttton logic
     */
    private void resetButton() {
        resetBtn.addClickListener(new Button.ClickListener() {
            /**
             * The Button click
             */
            public void buttonClick(final Button.ClickEvent event) {

                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values " + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                    /**
                     * Method is called when save button is clicked.
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            try {
                                resetBtnLogic();

                            } catch (Exception e) {
                                LOGGER.error(e);
                                AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }
        });

    }

    /**
     * save button logic
     */
    private void saveButton() {
        saveBtn.addClickListener(new Button.ClickListener() {
            /**
             * The button click
             */
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    forecastBinder.commit();
                    boolean flag = false;
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    final Date gtsDate = new CommonUtils().getCurrentGTSToDate(ConstantsUtils.EX_FACTORY_SALES);
                    String toDate = df.format(gtsDate);
                    if (mode.getValue().equals(String.valueOf(ConstantsUtils.PERIOD))) {
                        if (processType.getValue().equals(String.valueOf(ConstantsUtils.DEFINED))) {
                            Date historyDate = historicalPeriod.getValue();
                            Date futureDate = futurePeriod.getValue();
                            Calendar calHisCalendar = Calendar.getInstance();
                            Calendar calPrCalendar = Calendar.getInstance();
                            Calendar lastCal = Calendar.getInstance();
                            lastCal.setTime(futureDate);
                            int lastDate = lastCal.getActualMaximum(Calendar.DATE);

                            calHisCalendar.setTimeZone(TimeZone.getTimeZone("GMT"));
                            calHisCalendar.add(Calendar.MONTH, -1);
                            calPrCalendar.add(Calendar.MONTH, 1);

                            if (historyDate.getDate() == 1) {
                                if (futureDate.getDate() == lastDate) {
                                    if (futureDate.after(historyDate)) {
                                        if (calHisCalendar.getTime().after(historyDate) && futureDate.after(calPrCalendar.getTime())) {
                                            //Commented for 629 issue
                                            flag = true;
//                                        if (futureDate.before(gtsDate)) {
//                                            flag = true;
//                                        } else {
//                                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "To Date should be less than the forecast time period " + toDate, ButtonId.OK);
//                                        }
                                        } else {
                                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "From / To Should be atleast one month before / After Current Date", ButtonId.OK);
                                        }
                                    } else {
                                        MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "From Date should not be after To date", ButtonId.OK);
                                    }
                                } else {
                                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "To Date should be end date of the month", ButtonId.OK);
                                }
                            } else {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "From Date should be begining date of the month", ButtonId.OK);
                            }
                        } else {
                            Date historyDate = historicalPeriod.getValue();
                            Calendar calHisCalendar = Calendar.getInstance();
                            if (historyDate.getDate() == 1) {
                                if (calHisCalendar.getTime().after(historyDate)) {
                                    //Commented for 629 issue
                                    flag = true;
//                                        if (futureDate.before(gtsDate)) {
//                                            flag = true;
//                                        } else {
//                                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "To Date should be less than the forecast time period " + toDate, ButtonId.OK);
//                                        }
                                } else {
                                    MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "From date should be atleast one month before current Date", ButtonId.OK);
                                }

                            } else {
                                MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "From Date should be begining date of the month", ButtonId.OK);
                            }
                        }
                    } else {
                        //Commented for 629 issue
                        //   Date obtainedDate = forecastLogic.calculateProjection(forecastDTO.getFutureInterval(), Integer.valueOf(forecastDTO.getFutureValue()));
                        if (Integer.valueOf(forecastDTO.getHistoricalValue()) == 0) {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Historical data interval can't be Zero ", ButtonId.OK);
                            return;
                        }
                        if (Integer.valueOf(forecastDTO.getFutureValue()) == 0) {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Future interval can't be Zero ", ButtonId.OK);
                            return;
                        }
                        if (Integer.valueOf(forecastDTO.getHistoricalValue()) != 0 || Integer.valueOf(forecastDTO.getFutureValue() == ConstantsUtils.EMPTY ? "0" : forecastDTO.getFutureValue()) != 0) {
                            //   if (obtainedDate.before(gtsDate)) {
                            flag = true;
                            // } else {
                            //       MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "To Date should be less than the forecast time period " + toDate, ButtonId.OK);
                            //    }
                        } else {
                            MessageBox.showPlain(Icon.INFO, ConstantsUtils.ERROR, "Entered interval is not valid", ButtonId.OK);
                        }
                    }
                    if (flag) {
                        new AbstractNotificationUtils() {
                            @Override
                            /**
                             * No method
                             */
                            public void noMethod() {
                            }

                            @Override
                            /**
                             * Yes method
                             */
                            public void yesMethod() {
                                try {
                                    saveButtonLogic();
                                } catch (Exception e) {
                                    LOGGER.error(e);
                                }
                            }
                        }.getConfirmationMessage("Save Confirmation", "Save record ?");
                    }

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
    }
}
