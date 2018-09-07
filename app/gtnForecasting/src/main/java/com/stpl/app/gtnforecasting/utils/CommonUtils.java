/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.gtnforecasting.dao.DataSelectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import static com.stpl.app.gtnforecasting.ui.form.lookups.AltHistorySelection.getMonthMap;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.getDateTime;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.getEndMonth;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.getPeriod;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.getStartMonth;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import static com.stpl.app.gtnforecasting.utils.Constant.ZERO;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.serviceUtils.Constants.FrequencyConstants.ANNUALLY;
import com.stpl.app.serviceUtils.ConstantsUtils;
import static com.stpl.app.utils.Constants.CommonConstants.*;
import static com.stpl.app.utils.Constants.FrequencyConstants.*;
import static com.stpl.app.utils.Constants.LevelConstants.*;
import static com.stpl.app.utils.Constants.ProjectionConstants.FORECAST_END_DAY;
import static com.stpl.app.utils.Constants.ProjectionConstants.FORECAST_END_PERIOD;
import static com.stpl.app.utils.Constants.ProjectionConstants.FORECAST_START_DAY;
import static com.stpl.app.utils.Constants.ProjectionConstants.FORECAST_START_PERIOD;
import static com.stpl.app.utils.Constants.ProjectionConstants.FREQUENCY_DIVISION;
import static com.stpl.app.utils.Constants.ProjectionConstants.HISTORY_END_DAY;
import static com.stpl.app.utils.Constants.ProjectionConstants.HISTORY_END_MONTH;
import static com.stpl.app.utils.Constants.ProjectionConstants.HISTORY_END_PERIOD;
import static com.stpl.app.utils.Constants.ProjectionConstants.HISTORY_END_YEAR;
import static com.stpl.app.utils.Constants.ProjectionConstants.HISTORY_START_DAY;
import static com.stpl.app.utils.Constants.ProjectionConstants.HISTORY_START_MONTH;
import static com.stpl.app.utils.Constants.ProjectionConstants.HISTORY_START_MONTH_DDLB;
import static com.stpl.app.utils.Constants.ProjectionConstants.HISTORY_START_PERIOD;
import static com.stpl.app.utils.Constants.ProjectionConstants.HISTORY_START_PERIOD_DDLB;
import static com.stpl.app.utils.Constants.ProjectionConstants.HISTORY_START_YEAR;
import static com.stpl.app.utils.Constants.ProjectionConstants.HISTORY_START_YEAR_DDLB;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_END_DAY;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_END_PERIOD;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_START_DAY;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_START_MONTH;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_START_MONTH_DDLB;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_START_PERIOD;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_START_YEAR;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_START_YEAR_DDLB;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinPortletService;
import com.vaadin.shared.Position;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.NativeSelect;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.portlet.PortletConfig;
import javax.portlet.PortletSession;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.inflater.filter.AttributeFilter;


/**
 * Contains the common methods which support the module functionality.
 *
 * @author soundarrajan
 */
public class CommonUtils {

    /**
     * The log.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);
    /**
     * The Constant historyBean.
     */
    public static final BeanItemContainer<String> historyBean = new BeanItemContainer<>(String.class);
    public static final String YEARS1 = "Years";
    /**
     * The Constant CHAR_PERCENT.
     */
    public static final char CHAR_PERCENT = '%';
    /**
     * The Constant CHAR_ASTERISK.
     */
    public static final char CHAR_ASTERISK = '*';
    /**
     * The Constant NONMANDATED.
     */
    public static final String NONMANDATED = "Non-mandated";
    /**
     * used for dataSelection.
     */
    public static final String BRAND = "Brand";
    /**
     * The Constant MANUFACTURER.
     */
    public static final String MANUFACTURER = "manufacturer";
    /**
     * The Constant STRING_NULL.
     */
    public static final String STRING_NULL = "null";
    /**
     * The Constant TRUE.
     */
    public static final boolean TRUE = true;
    /**
     * The Constant FALSE.
     */
    public static final boolean FALSE = false;
    /**
     * The Constant COL_CURRENT.
     */
    public static final String COL_VALUE = "Value";
    /**
     * The Constant COL_PRIOR.
     */
    public static final String COL_PRIOR = "Prior";
    /**
     * The Constant COL_VARIANCE.
     */
    public static final String COL_VARIANCE = "Variance";
    /**
     * The Constant COL_PERCENTAGE.
     */
    public static final String COL_PERCENTAGE = "% Change";

    public static final String GENERATE_BUTTON = "generateBtn";
    public static final String SEARCH_BUTTON = "searchBtn";
    public static final String RESET_BUTTON = "resetBtn";
    public static final String SAVE_VIEW = "saveViewBtn";
    public static final String DELETE_VIEW = "deleteViewBtn";
    public static final String RESULT_RESET = "resultResetBtn";
    public static final String EDIT_BUTTON = "editBtn";
    public static final String VIEW_BUTTON = "viewBtn";
    public static final String DELETE_BUTTON = "deleteBtn";
    public static final String MOVE_RIGHT_BUTTON = "moveRightBtn";
    public static final String MOVE_LEFT_BUTTON = "moveLeftBtn";
    public static final String ALL_BUTTON = "allBtn";
    public static final String MOVE_RIGHT_PRODUCT = "moveRightProduct";
    public static final String MOVE_LEFT_PRODUCT = "moveLeftProduct";
    public static final String ALL_PROD_BUTTON = "allProductBtn";
    public static final String ALL_BTN = "allProductBtn";
    public static final String ALL = "all";

    public static final String BTN_SAVE = "btnSave";
    public static final String BTN_PREV = "btnPrev";
    public static final String BTN_NEXT = "btnNext";
    public static final String BTN_CLOSE = "btnClose";
    public static final String BTN_SUBMIT = "btnSubmit";
    /*Buttons For Sales Projection*/
    public static final String POPULATE_SALES = "populate";
    public static final String POPULATE_BTN = "populateBtn";
    public static final String CALCULATE = "calculate";
    public static final String CALCULATE_BTN = "calculateBtn";
    public static final String EXPAND = "expand";
    public static final String EXPAND_BTN = "expandBtn";
    public static final String COLLAPSE = "collapse";
    public static final String COLLAPSE_BTN = "collapseBtn";
    public static final String NEW = "newBtn";
    public static final String EDIT = "editBtn";
    public static final String REFRESH = "refreshBtn";
    public static final String ADJUST = "adjust";
    public static final String ADJUST_BTN = "adjustBtn";
    public static final String PMPY = "pmpy";
    public static final String PROGRAM_SELECTION = "programSelectionLookup";
    public static final String ALT_HISTORY_BTN = "altHistoryBtn";
    public static final String TOTAL_LIVES_LAYOUT = "totalLivesLayout";
    /*
     * Projection Variance variables
     */
    /**
     * The Constant VAR_GTS.
     */
    public static final String VAR_GTS = "Gross Trade Sales";
    /**
     * The Constant VAR_CONTRACT_SALES.
     */
    public static final String VAR_CONTRACT_SALES = "Contract Sales @ WAC";
    /**
     * The Constant VAR_CONTRACT_UNITS.
     */
    public static final String VAR_CONTRACT_UNITS = "Contract Units";
    /**
     * The Constant VAR_PERCENTAGE.
     */
    public static final String VAR_PERCENTAGE = "% of Business";
    /**
     * The Constant VAR_DIS_AMOUNT.
     */
    public static final String VAR_DIS_AMOUNT = "Discount $";
     /**
     * The Constant VAR_DIS_PER_EXFACTORY.
     */
    public static final String VAR_DIS_PER_EXFAC = "Discount % of Ex-Factory";
    /**
     * The Constant VAR_DIS_RATE.
     */
    public static final String VAR_DIS_RATE = "Discount %";
    /**
     * The Constant VAR_NETSALES.
     */
    public static final String VAR_NETSALES = "Net Sales";
    public static final String GENERATE_BTN = "generateBtn";

    public static final String SEARCH_BTN = "searchBtn";

    public static final String RESET_BTN = "resetBtn";

    public static final String VIEW_BTN = "viewBtn";

    public static final String DELETE_BTN = "deleteBtn";

    public static final String RESULT_RESET_BTN = "resultResetBtn";

    public static final String SAVE_VIEW_BTN = "saveViewBtn";

    public static final String DELETE_VIEW_BTN = "deleteviewBtn";

    public static final String MOVE_LEFT_BTN = "moveLeftBtn";

    public static final String MOVE_RIGHT_BTN = "moveRightBtn";

    public static final String MOVE_LEFT_PRODUCT_BTN = "moveLeftProduct";

    public static final String MOVE_RIGHT_PRODUCT_BTN = "moveRightProduct";

    public static final String ALL_BTN1 = "allBtn";

    /**
     * The Constant NONMANDATED.
     */
    /**
     * The Constant HISTORY LOOKUP CONTRACT COLUMNS.
     */
    public final Object[] historyLookupContractColumnsMandated = new Object[]{"customer", "contractNumber", "contractName"};

    /**
     * The Constant HISTORY LOOKUP CONTRACT HEADERS.
     */
    public final String[] historyLookupContractHeadersMandated = new String[]{Constant.CUSTOMER_SMALL, "Contract Number", "Contract Name"};

    /**
     * The Constant HISTORY LOOKUP CONTRACT COLUMNS.
     */
    public final Object[] historyLookupContractColumnsNonMandated = new Object[]{"customer", "contractNumber", "contractName"};

    /**
     * The Constant HISTORY LOOKUP CONTRACT HEADERS.
     */
    public final String[] historyLookupContractHeaderNonMandated = new String[]{Constant.CUSTOMER_SMALL, "Contract Number", "Contract Name"};

    /**
     * The Constant HISTORY LOOKUP BRAND COLUMNS.
     */
    public final Object[] historyLookupBrandColumns = new Object[]{Constant.BRAND};

    /**
     * The Constant HISTORY LOOKUP BRAND HEADERS.
     */
    public final String[] historyLookupBrandHeaders = new String[]{"Brand Name"};

    public static final String BUSINESS_PROCESS_TYPE_NONMANDATED = "Non Mandated";
    public static final String BUSINESS_PROCESS_TYPE_MANDATED = "Mandated";
    public static final String BUSINESS_PROCESS_TYPE_CHANNELS = "Channel";
    public static final String BUSINESS_PROCESS_TYPE_RETURNS = "Returns";
    public static final String BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION = "AccrualRateProjection";
    public static final String BUSINESS_PROCESS_INDICATOR_NON_MANDATED = "NM";
    public static final String BUSINESS_PROCESS_INDICATOR_MANDATED = "M";
    public static final String BUSINESS_PROCESS_INDICATOR_CHANNELS = "C";
    public static final DecimalFormat MONEYNODECIMAL = new DecimalFormat("$0");
    public static final DecimalFormat UNITNODECIMAL = new DecimalFormat(DASH);

    public static final DecimalFormat UNITVOLUME = new DecimalFormat("#,##0");
    public static final DecimalFormat MONEY = new DecimalFormat("$#,##0");
    public static final DecimalFormat PERCENTFORMAT = new DecimalFormat("#,##0.00%");
    public static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("#,##0.000%");
    private static PortletConfig portletConfig;
    @SuppressWarnings("unused")
	private static PortletSession portletSession;

    private static final ResourceBundle constantProperties = ResourceBundle.getBundle("properties.Constants");

    private static final ResourceBundle returnsProperties = ResourceBundle.getBundle("custom-sql.ReturnsProjectionQueries");
    public static final String BUSINESS_PROCESS_TYPE = "BUSINESS_PROCESS_TYPE";
    public static final String BUSINESS_PROCESS_TYPE_COMMERCIAL = "Commercial";
    public static final String BUSINESS_PROCESS_TYPE_GOVERNMENT = "Government";
    
    private static final String[] ARRAY_MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
   
    /**
     * Gets the current calendar.
     *
     * @return the calendar
     */
    public static Calendar getCalendar() {

        final Calendar now = Calendar.getInstance();
        LOGGER.debug("Entering getCalendar method");

        // TMPSOLUTION - Hardcoded for temporary solution
        now.set(now.get(Calendar.YEAR), Calendar.JANUARY, NumericConstants.FIVE);
        LOGGER.debug("End of getCalendar method");
        return now;
    }

    /**
     * Generates the startPeriod DDLB.
     *
     * @return startPeriod
     */
    public static final NativeSelect getStartPeriod() {
        final NativeSelect startPeriod = new NativeSelect();

        return startPeriod;
    }

    /**
     * Generates the endPeriod DDLB.
     *
     * @return endPeriod
     */
    public static final NativeSelect getEndPeriod() {
        final NativeSelect endPeriod = new NativeSelect();

        return endPeriod;
    }

    /**
     * Formats Date to "MM/dd/yyyy" format.
     *
     * @param date the date to be formatted
     * @return the formatted date
     */
    public static final String formatDate(final Date date) {
        final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
        return format.format(date);
    }

    /**
     * Convert the date to "MM/dd/yyyy" format.
     *
     * @param date - The Date object to be converted to string
     * @return String - date in string format in the format of "MM/dd/yyyy"
     */
    public static final String convertDateToString(final Date date) {
        String dateTime = StringUtils.EMPTY;
        try {
            dateTime = getDateTime(DATE_FORMAT.getConstant(), date);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return dateTime;
    }

    /**
     * Get the date in string on given format.
     *
     * @param format - Date format input
     * @param date - The Date object to be converted to string
     * @return String - date in string format
     */
    public static final String getDateTime(final String format, final Date date) {
        SimpleDateFormat dateFormat = null;
        String returnValue = StringUtils.EMPTY;
        try {
            if (date != null) {
                dateFormat = new SimpleDateFormat(format);
                returnValue = dateFormat.format(date);
            }
        } catch (Exception ex) {
            LOGGER.error(StringUtils.EMPTY,ex);
        }
        return returnValue;
    }

    /**
     * Configures the level order for customer.
     *
     * @return map containing the configuration for customer
     */
    public static final Map<String, String> customerLevelConfiguration() {
        final Map<String, String> customerMap = new HashMap<>();
        customerMap.put(LEVEL_1.getConstant(), LEVEL_SEGMENT_GROUP.getConstant());
        customerMap.put(LEVEL_2.getConstant(), LEVEL_SEGMENT.getConstant());
        customerMap.put(LEVEL_3.getConstant(), LEVEL_MARKET_TYPE.getConstant());
        customerMap.put(LEVEL_4.getConstant(), LEVEL_CONTRACT_HOLDER.getConstant());
        customerMap.put(LEVEL_5.getConstant(), LEVEL_CONTRACT.getConstant());
        customerMap.put(LEVEL_6.getConstant(), LEVEL_TRADING_PARTNER.getConstant());
        return customerMap;
    }

    /**
     * Configures the level order for product.
     *
     * @return map containing the configuration for product
     */
    public static final Map<String, String> productLevelConfiguration() {
        final Map<String, String> productMap = new HashMap<>();
        productMap.put(LEVEL_1.getConstant(), LEVEL_COMPANY.getConstant());
        productMap.put(LEVEL_2.getConstant(), LEVEL_THERAPEUTIC_CLASS.getConstant());
        productMap.put(LEVEL_3.getConstant(), LEVEL_BRAND.getConstant());
        productMap.put(LEVEL_4.getConstant(), LEVEL_NDC.getConstant());
        return productMap;
    }

    /**
     * Load history.
     *
     * @param session
     * @param frequency the frequency
     * @return the list< string>
     */
    public static final List<String> loadHistory(SessionDTO session, final String frequency) {
        final List<String> history = new ArrayList();
        int endValue = 0;
        String freq = StringUtils.EMPTY;

        endValue = CommonUtils.getProjections(session.getForecastDTO().getHistoryStartDate(), session.getForecastDTO().getHistoryEndDate(), frequency);
        if (ANNUALLY.getConstant().equals(frequency)) {
            freq = YEARS.getConstant();
        } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
            freq = SEMI_ANNUAL.getConstant();
        } else if (QUARTERLY.getConstant().equals(frequency)) {
            freq = QUARTERS.getConstant();
        } else if (MONTHLY.getConstant().equals(frequency)) {
            freq = MONTHS.getConstant();
        }

        for (int i = 1; i <= endValue; i++) {
            if (i == 1 && !SEMI_ANNUAL.getConstant().equals(freq)) {
                final String period = freq.replace(Constant.S_SMALL, StringUtils.EMPTY);
                history.add(String.valueOf(i) + SPACE + period);
            } else {
                history.add(String.valueOf(i) + SPACE + freq);
            }
        }
        return history;
    }

    /**
     * Creates the clara.
     *
     * @param xmlClassResourceFileName the xml class resource file name
     * @param controller the controller
     * @param attributeFilters the attribute filters
     * @return the component
     */
    public static Component createClara(String xmlClassResourceFileName, Object controller, AttributeFilter... attributeFilters) {
        LOGGER.debug("createClara method started");
        InputStream xml = null;
        try {
            String path = controller.getClass().getCanonicalName();
            String finalPath = path.substring(0, path.lastIndexOf('.'));
            finalPath = finalPath.replaceAll("\\.", "\\" + File.separator);
            finalPath += xmlClassResourceFileName;
            LOGGER.debug("Path to XML= {}" , finalPath);
            xml = Thread.currentThread().getContextClassLoader().getResource(finalPath).openStream();
            LOGGER.debug("createClara method ends");
            return Clara.create(xml, controller, attributeFilters);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            try {
                if (xml != null) {
                    xml.close();
                }
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return null;
    }

    /**
     * To check whether the given string is integer or not
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        try {
            if ((str != null) && (!"null".equals(str)) && (!"".equals(str)) && (!"All".equals(str))) { // For GAL-9221,GAL-9219,GAL-9197 server log issues	
                 return str.matches("^\\d+$");
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    public static String getViewTableName(String hierarchyIndicator) {
        String viewtable = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_CUST_HIERARCHY";
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_PROD_HIERARCHY";
        }
        return viewtable;
    }

    public static int getMonthForString(String num) {
        String[] months = new DateFormatSymbols().getShortMonths();
        for (int i = 0; i < months.length; i++) {
            if (months[i].equals(num)) {
                return i + 1;
            }
        }
        return 0;
    }

    public static List<Date> getStartandTodate() {
        List<Date> result = new ArrayList<>();
        ForecastConfig forecastConfig = getTimePeriod();
        Date fromDate = null;
        Date toDate = null;
        if (forecastConfig != null) {
            if (forecastConfig.getProcessMode()) {      // Interval
                if (Constant.MONTH1.equalsIgnoreCase(String.valueOf(forecastConfig.getHistFreq()))) {
                    fromDate = DataSelectionUtil.calculateHistory(Constant.MONTH1, forecastConfig.getHistValue());
                    toDate = DataSelectionUtil.calculateProjection(Constant.MONTH1, forecastConfig.getProjValue());
                } else if (Constant.QUARTER1.equalsIgnoreCase(String.valueOf(forecastConfig.getHistFreq()))) {
                    fromDate = DataSelectionUtil.calculateHistory(Constant.QUARTER1, forecastConfig.getHistValue());
                    toDate = DataSelectionUtil.calculateProjection(Constant.QUARTER1, forecastConfig.getProjValue());
                } else if (Constant.SEMI_ANNUAL_SMALL.equalsIgnoreCase(String.valueOf(forecastConfig.getHistFreq()))) {
                    fromDate = DataSelectionUtil.calculateHistory(Constant.SEMI_ANNUAL_SMALL, forecastConfig.getHistValue());
                    toDate = DataSelectionUtil.calculateProjection(Constant.SEMI_ANNUAL_SMALL, forecastConfig.getProjValue());
                } else if (Constant.ANNUAL.equalsIgnoreCase(String.valueOf(forecastConfig.getHistFreq()))) {
                    fromDate = DataSelectionUtil.calculateHistory(Constant.ANNUAL, forecastConfig.getHistValue());
                    toDate = DataSelectionUtil.calculateProjection(Constant.ANNUAL, forecastConfig.getProjValue());
                }
            } else {

                fromDate = forecastConfig.getFromDate();
                toDate = forecastConfig.getToDate();
            }
        }
        result.add(fromDate);
        result.add(toDate);
        return result;

    }

    public static ForecastConfig getTimePeriod() {
        DataSelectionDAO dataSelectionDao = new DataSelectionDAOImpl();
        List resultList = null;
        int businessProcessType = 0;
        try {
            businessProcessType = CommonUtils.getHelperCode(CommonUtils.BUSINESS_PROCESS_TYPE, GlobalConstants.getCommercialConstant());
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(CommonUtils.class.getName()).error( StringUtils.EMPTY, ex);
        }
        DynamicQuery dynamicQuery = ForecastConfigLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType", businessProcessType));
        dynamicQuery.addOrder(OrderFactoryUtil.desc(Constant.VERSION_NO));
        try {
            resultList = dataSelectionDao.getForecastConfig(dynamicQuery);
        } catch (SystemException ex) {
            LoggerFactory.getLogger(CommonUtils.class.getName()).error( StringUtils.EMPTY, ex);
        }
        ForecastConfig forecastConfig = null;
        if (resultList != null && !resultList.isEmpty()) {
            forecastConfig = (ForecastConfig) resultList.get(0);
        }
        return forecastConfig;
    }

    public static int getProjections(Date startDate, Date endDate, String frequency) {
        if (endDate.after(startDate)) {
            if (frequency.equals(ANNUALLY.getConstant()) || frequency.equals(ANNUAL.getConstant())) {
                return endDate.getYear() - startDate.getYear();
            } else {
                Calendar startCalendar = Calendar.getInstance();
                startCalendar.setTime(startDate);
                Calendar endCalendar = Calendar.getInstance();
                endCalendar.setTime(endDate);
                int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
                int diffMonth = diffYear * NumericConstants.TWELVE + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
                if (frequency.equals(QUARTERLY.getConstant())) {
                    if (diffMonth % NumericConstants.THREE == 0) {
                        return diffMonth / NumericConstants.THREE;
                    } else {
                        return (diffMonth / NumericConstants.THREE) + 1;
                    }

                } else if (frequency.equals(SEMI_ANNUALLY.getConstant()) || frequency.equals(SEMI_ANNUAL.getConstant())) {
                    if (diffMonth % NumericConstants.SIX == 0) {
                        return diffMonth / NumericConstants.SIX;
                    } else {
                        return (diffMonth / NumericConstants.SIX) + 1;
                    }
                } else if (frequency.equals(MONTHLY.getConstant())) {
                    return diffMonth;
                }
                return 0;

            }
        }
        return 0;
    }

    public static Date getDate(int month, int year) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(year, month, 1);
        return startCalendar.getTime();
    }

    public Date getDate(int day, int month, int year) {
        Date date = new Date();
        date.setDate(day);
        date.setMonth(month);
        date.setYear(year - NumericConstants.ONE_NINE_ZERO_ZERO);
        return date;
    }

    public Date getLastDate(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        // passing month-1 because 0-->jan, 1-->feb... 11-->dec
        calendar.set(year, month - 1, 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        Date date = calendar.getTime();
        return date;
    }

    static int getPeriodsPerYear(String frequency) {
        int division = 0;
        if (frequency.equals(QUARTERLY.getConstant())) {
            division = NumericConstants.THREE;
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant()) || frequency.equals(SEMI_ANNUAL.getConstant())) {
            division = NumericConstants.SIX;
        } else if (frequency.equals(MONTHLY.getConstant())) {
            division = 1;
        } else if (frequency.equals(ANNUALLY.getConstant()) || frequency.equals(ANNUAL.getConstant())) {
            division = NumericConstants.TWELVE;
        }
        return division;
    }

    static int getFrequencyDivision(String frequency) {
        int frequencyDivision = 0;
        if (frequency.equals(QUARTERLY.getConstant())) {
            frequencyDivision = NumericConstants.FOUR;
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant()) || frequency.equals(SEMI_ANNUAL.getConstant())) {
            frequencyDivision = NumericConstants.TWO;
        } else if (frequency.equals(MONTHLY.getConstant())) {
            frequencyDivision = NumericConstants.TWELVE;
        } else if (frequency.equals(ANNUALLY.getConstant()) || frequency.equals(ANNUAL.getConstant())) {
            frequencyDivision = 1;
        }
        return frequencyDivision;
    }

    static int getPeriod(int monthNo, int division) {
        return ((monthNo - 1) / division) + 1;
    }

    static int getPeriodFromDate(Date date, int division) {
        return (date.getMonth() / division) + 1;
    }

    public static int getStartMonth(int period, String frequency) {

        int month = 1;
        if (frequency.equals(ANNUALLY.getConstant()) || frequency.equals(ANNUAL.getConstant())) {
            month = 1;
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant()) || frequency.equals(SEMI_ANNUAL.getConstant())) {
            if (period == 1) {
                month = 1;
            } else if (period == NumericConstants.TWO) {
                month = NumericConstants.SEVEN;
            }
        } else if (frequency.equals(QUARTERLY.getConstant())) {
            if (period == 1) {
                month = 1;
            } else if (period == NumericConstants.TWO) {
                month = NumericConstants.FOUR;
            } else if (period == NumericConstants.THREE) {
                month = NumericConstants.SEVEN;
            } else if (period == NumericConstants.FOUR) {
                month = NumericConstants.TEN;
            }
        } else if (frequency.equals(MONTHLY.getConstant())) {
            month = period;
        }

        return month;
    }

    public static int getStartMonthForDate(int period, int year, String frequency, Date startDate) {
        int startMonth = 0;
        if ((startDate != null) && (startDate.getYear() == year)) {
            startMonth = startDate.getMonth() + 1;
        }
        int month = getStartMonth(period, frequency);
        if (startMonth != 0 && startMonth >= month) {
            month = startMonth;
        }
        return month;
    }

    public static int getEndMonth(int period, String frequency) {
        int month = 1;
        if (frequency.equals(ANNUALLY.getConstant()) || frequency.equals(ANNUAL.getConstant())) {
            month = NumericConstants.TWELVE;
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant()) || frequency.equals(SEMI_ANNUAL.getConstant())) {
            if (period == 1) {
                month = NumericConstants.SIX;
            } else if (period == NumericConstants.TWO) {
                month = NumericConstants.TWELVE;
            }
        } else if (frequency.equals(QUARTERLY.getConstant())) {
            if (period == 1) {
                month = NumericConstants.THREE;
            } else if (period == NumericConstants.TWO) {
                month = NumericConstants.SIX;
            } else if (period == NumericConstants.THREE) {
                month = NumericConstants.NINE;
            } else if (period == NumericConstants.FOUR) {
                month = NumericConstants.TWELVE;
            }
        } else if (frequency.equals(MONTHLY.getConstant())) {
            month = period;
        }
        return month;
    }

    public static int getEndMonthForDate(int period, int year, String frequency, Date endDate) {
        int endMonth = 0;
        if ((endDate != null) && (endDate.getYear() == year)) {
            endMonth = endDate.getMonth() + 1;
        }
        int month = getEndMonth(period, frequency);
        if (endMonth != 0 && endMonth <= month) {
            month = endMonth;
        }
        return month;
    }

    static int getEndDay(int monthNo, int year) {
        Calendar ob = Calendar.getInstance();
        ob.set(year, monthNo - 1, 1);
        int daysInMonth = ob.getActualMaximum(Calendar.DAY_OF_MONTH);
        return daysInMonth;
    }

    public static Map<String, Integer> getHistoryEndDetails(SessionDTO session, String frequency) {
        Map<String, Integer> history;
        history = session.getHistoryEndDetails(frequency);
        if (history == null || history.isEmpty()) {
            history = getHistoryEndDetail(session, frequency);
            session.addHistoryEndDetails(frequency, history);
        }
        return history;
    }

    private static Map<String, Integer> getHistoryEndDetail(SessionDTO session, String frequency) {
        Map<String, Integer> history = new HashMap<>();
        int frequencyDivision = 1;
        int historyEndPeriod = 1;
        int historyEndMonth = 1;
        int historyEndYear = 1;
        int forecastStartPeriod = 1;
        if (frequency.equals(QUARTERLY.getConstant())) {
            frequencyDivision = NumericConstants.FOUR;
            historyEndPeriod = getPeriod(session.getForecastDTO().getHistoryEndMonth(), NumericConstants.THREE);
            forecastStartPeriod = getPeriod(session.getForecastDTO().getForecastStartMonth(), NumericConstants.THREE);
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant()) || frequency.equals(SEMI_ANNUAL.getConstant())) {
            frequencyDivision = NumericConstants.TWO;
            historyEndPeriod = getPeriod(session.getForecastDTO().getHistoryEndMonth(), NumericConstants.SIX);
            forecastStartPeriod = getPeriod(session.getForecastDTO().getForecastStartMonth(), NumericConstants.SIX);
        } else if (frequency.equals(MONTHLY.getConstant())) {
            frequencyDivision = NumericConstants.TWELVE;
            historyEndPeriod = session.getForecastDTO().getHistoryEndMonth();
            forecastStartPeriod = session.getForecastDTO().getForecastStartMonth();
        } else if (frequency.equals(ANNUALLY.getConstant()) || frequency.equals(ANNUAL.getConstant())) {
            frequencyDivision = 1;
            historyEndPeriod = session.getForecastDTO().getHistoryEndYear();
            forecastStartPeriod = session.getForecastDTO().getForecastStartYear();
        }
        historyEndMonth = session.getForecastDTO().getHistoryEndMonth();
        historyEndYear = session.getForecastDTO().getHistoryEndYear();
        if (historyEndPeriod == forecastStartPeriod && historyEndYear == session.getForecastDTO().getForecastStartYear()) {
            historyEndPeriod--;
            if (frequencyDivision == 1) {
                historyEndYear = historyEndYear - 1;
                historyEndMonth = NumericConstants.TWELVE;
            } else if (historyEndPeriod < 1) {
                historyEndPeriod = frequencyDivision;
                historyEndMonth = NumericConstants.TWELVE;
                historyEndYear = historyEndYear - 1;
            } else {
                historyEndMonth = (NumericConstants.TWELVE / frequencyDivision) * historyEndPeriod;
            }
        }
        history.put(FREQUENCY_DIVISION.getConstant(), frequencyDivision);
        history.put(HISTORY_END_YEAR.getConstant(), historyEndYear);
        history.put(HISTORY_END_MONTH.getConstant(), historyEndMonth);
        history.put(HISTORY_END_PERIOD.getConstant(), historyEndPeriod);
        history.put(HISTORY_END_DAY.getConstant(), getEndDay(historyEndMonth, historyEndYear));
        return history;
    }

    public static void getHistoryAndProjectionDetails(ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = 1;
        int historyStartPeriod = 1;
        int historyEndPeriod = 1;
        int historyEndMonth = 1;
        int historyEndYear = 1;
        int forecastStartPeriod = 1;
        int forecastEndPeriod = 1;
        int projectionStartPeriod = 1;
        int projectionEndPeriod = 1;
        String frequency = projSelDTO.getFrequency();

        if (frequency.equals(QUARTERLY.getConstant())) {
            frequencyDivision = NumericConstants.FOUR;
            int a[] = getQSPeriodDetails(projSelDTO, frequencyDivision);
            historyStartPeriod = a[1];
            historyEndPeriod = a[NumericConstants.TWO];
            forecastStartPeriod = a[NumericConstants.THREE];
            forecastEndPeriod = a[NumericConstants.FOUR];
            projectionStartPeriod = a[NumericConstants.FIVE];
            projectionEndPeriod = a[NumericConstants.SIX];
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant()) || frequency.equals(SEMI_ANNUAL.getConstant())) {
            frequencyDivision = NumericConstants.TWO;
            int a[] = getQSPeriodDetails(projSelDTO, frequencyDivision);
            historyStartPeriod = a[1];
            historyEndPeriod = a[NumericConstants.TWO];
            forecastStartPeriod = a[NumericConstants.THREE];
            forecastEndPeriod = a[NumericConstants.FOUR];
            projectionStartPeriod = a[NumericConstants.FIVE];
            projectionEndPeriod = a[NumericConstants.SIX];
        } else if (frequency.equals(MONTHLY.getConstant())) {
            frequencyDivision = NumericConstants.TWELVE;
            historyStartPeriod = projSelDTO.getForecastDTO().getHistoryStartMonth();
            historyEndPeriod = projSelDTO.getForecastDTO().getHistoryEndMonth();
            forecastStartPeriod = projSelDTO.getForecastDTO().getForecastStartMonth();
            forecastEndPeriod = projSelDTO.getForecastDTO().getForecastEndMonth();
            projectionStartPeriod = projSelDTO.getForecastDTO().getProjectionStartMonth();
            projectionEndPeriod = projSelDTO.getForecastDTO().getProjectionEndMonth();
        } else if (frequency.equals(ANNUALLY.getConstant()) || frequency.equals(ANNUAL.getConstant())) {
            frequencyDivision = 1;
            historyStartPeriod = projSelDTO.getForecastDTO().getHistoryStartYear();
            historyEndPeriod = projSelDTO.getForecastDTO().getHistoryEndYear();
            forecastStartPeriod = projSelDTO.getForecastDTO().getForecastStartYear();
            forecastEndPeriod = projSelDTO.getForecastDTO().getForecastEndYear();
            projectionStartPeriod = projSelDTO.getForecastDTO().getProjectionStartYear();
            projectionEndPeriod = projSelDTO.getForecastDTO().getProjectionEndYear();
        }
        historyEndMonth = projSelDTO.getForecastDTO().getHistoryEndMonth();
        historyEndYear = projSelDTO.getForecastDTO().getHistoryEndYear();
        if (historyEndPeriod == forecastStartPeriod && historyEndYear == projSelDTO.getForecastDTO().getForecastStartYear()) {
            historyEndPeriod--;
            if (frequencyDivision == 1) {
                historyEndYear = historyEndYear - 1;
                historyEndMonth = NumericConstants.TWELVE;
            } else if (historyEndPeriod < 1) {
                historyEndPeriod = frequencyDivision;
                historyEndMonth = NumericConstants.TWELVE;
                historyEndYear = historyEndYear - 1;
            } else {
                historyEndMonth = (NumericConstants.TWELVE / frequencyDivision) * historyEndPeriod;
            }
        }

        projSelDTO.setFrequencyDivision(frequencyDivision);
        projSelDTO.addProjectionDetails(FREQUENCY_DIVISION.getConstant(), frequencyDivision);
        projSelDTO.addProjectionDetails(HISTORY_END_YEAR.getConstant(), historyEndYear);
        projSelDTO.addProjectionDetails(HISTORY_END_MONTH.getConstant(), historyEndMonth);
        projSelDTO.addProjectionDetails(HISTORY_START_YEAR.getConstant(), projSelDTO.getForecastDTO().getHistoryStartYear());
        projSelDTO.addProjectionDetails(HISTORY_START_MONTH.getConstant(), projSelDTO.getForecastDTO().getHistoryStartMonth());
        projSelDTO.addProjectionDetails(HISTORY_START_PERIOD.getConstant(), historyStartPeriod);
        projSelDTO.addProjectionDetails(HISTORY_END_PERIOD.getConstant(), historyEndPeriod);
        projSelDTO.addProjectionDetails(FORECAST_START_PERIOD.getConstant(), forecastStartPeriod);
        projSelDTO.addProjectionDetails(FORECAST_END_PERIOD.getConstant(), forecastEndPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_START_PERIOD.getConstant(), projectionStartPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_END_PERIOD.getConstant(), projectionEndPeriod);
        projSelDTO.addProjectionDetails(PROJECTION_START_YEAR.getConstant(), projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.addProjectionDetails(PROJECTION_START_MONTH.getConstant(), projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.addProjectionDetails(PROJECTION_START_YEAR_DDLB.getConstant(), projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.addProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant(), projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.addProjectionDetails(HISTORY_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(FORECAST_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(PROJECTION_START_DAY.getConstant(), 1);
        projSelDTO.addProjectionDetails(HISTORY_END_DAY.getConstant(), getEndDay(historyEndMonth, historyEndYear));
        projSelDTO.addProjectionDetails(FORECAST_END_DAY.getConstant(), getEndDay(projSelDTO.getForecastDTO().getForecastEndMonth(), projSelDTO.getForecastDTO().getForecastEndYear()));
        projSelDTO.addProjectionDetails(PROJECTION_END_DAY.getConstant(), getEndDay(projSelDTO.getForecastDTO().getProjectionEndMonth(), projSelDTO.getForecastDTO().getProjectionEndYear()));

        projSelDTO.setProjectionStartYear(projSelDTO.getForecastDTO().getProjectionStartYear());
        projSelDTO.setProjectionStartMonth(projSelDTO.getForecastDTO().getProjectionStartMonth());
        projSelDTO.setHistoryEndYear(historyEndYear);
        projSelDTO.setHistoryEndMonth(historyEndMonth);
        projSelDTO.setHistoryStartPeriod(historyStartPeriod);
        projSelDTO.setHistoryEndPeriod(historyEndPeriod);
        projSelDTO.setForecastStartPeriod(forecastStartPeriod);
        projSelDTO.setForecastEndPeriod(forecastEndPeriod);
        projSelDTO.setProjectionStartPeriod(projectionStartPeriod);
        projSelDTO.setProjectionEndPeriod(projectionEndPeriod);
        projSelDTO.setHistoryEndDay(getEndDay(projSelDTO.getForecastDTO().getHistoryEndMonth(), projSelDTO.getForecastDTO().getHistoryEndYear()));
        projSelDTO.setForecastStartDay(1);
        projSelDTO.setForecastEndDay(getEndDay(projSelDTO.getForecastDTO().getForecastEndMonth(), projSelDTO.getForecastDTO().getForecastEndYear()));
        projSelDTO.setProjectionStartDay(1);
        projSelDTO.setProjectionEndDay(getEndDay(projSelDTO.getForecastDTO().getProjectionEndMonth(), projSelDTO.getForecastDTO().getProjectionEndYear()));

        getHistoryDetail(projSelDTO);
    }

    public static int[] getQSPeriodDetails(ProjectionSelectionDTO projSelDTO, int freqDiv) {
        int[] ret = new int[NumericConstants.SEVEN];
        ret[0] = freqDiv;
        ret[1] = getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), NumericConstants.TWELVE / freqDiv);
        ret[NumericConstants.TWO] = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), NumericConstants.TWELVE / freqDiv);
        ret[NumericConstants.THREE] = getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), NumericConstants.TWELVE / freqDiv);
        ret[NumericConstants.FOUR] = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), NumericConstants.TWELVE / freqDiv);
        ret[NumericConstants.FIVE] = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), NumericConstants.TWELVE / freqDiv);
        ret[NumericConstants.SIX] = getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), NumericConstants.TWELVE / freqDiv);
        return ret;
    }

    /**
     * To get Start year, Start period and Start month
     *
     * @param projSelDTO
     * @return
     */
    static void getHistoryDetail(ProjectionSelectionDTO projSelDTO) {
        int historyNum = projSelDTO.getHistoryNum();
        int frequencyDivision = projSelDTO.getProjectionDetails(FREQUENCY_DIVISION.getConstant());

        int historyStartYear = projSelDTO.getProjectionDetails(HISTORY_END_YEAR.getConstant());
        int historyEndPeriod = projSelDTO.getProjectionDetails(HISTORY_END_PERIOD.getConstant());
        int historyStartFreq = historyEndPeriod + 1;
        if (frequencyDivision == 1) {
            historyStartYear = historyStartYear - historyNum + 1;
            historyStartFreq = historyStartYear;
        } else {
            int historyTempFreq = historyNum - historyEndPeriod;
            if (historyTempFreq > 0) {
                historyStartYear = historyStartYear - historyTempFreq / frequencyDivision;
                historyStartFreq = 1;
                if (historyTempFreq % frequencyDivision > 0) {
                    historyStartYear = historyStartYear - 1;
                    historyStartFreq = frequencyDivision - (historyTempFreq % frequencyDivision) + 1;
                }
            } else {
                historyStartFreq = historyStartFreq - historyNum;
            }
        }
        if (frequencyDivision == 1) {
            projSelDTO.setHistoryStartMonth(frequencyDivision);
            projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), frequencyDivision);
        } else if (frequencyDivision == NumericConstants.TWO) {
            if (historyStartFreq == 1) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 1);
                projSelDTO.setHistoryStartMonth(1);
            } else if (historyStartFreq == NumericConstants.TWO) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), NumericConstants.SEVEN);
                projSelDTO.setHistoryStartMonth(NumericConstants.SEVEN);
            }
        } else if (frequencyDivision == NumericConstants.FOUR) {
            if (historyStartFreq == 1) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), 1);
                projSelDTO.setHistoryStartMonth(NumericConstants.ONE);
            } else if (historyStartFreq == NumericConstants.TWO) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), NumericConstants.FOUR);
                projSelDTO.setHistoryStartMonth(NumericConstants.FOUR);
            } else if (historyStartFreq == NumericConstants.THREE) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), NumericConstants.SEVEN);
                projSelDTO.setHistoryStartMonth(NumericConstants.SEVEN);
            } else if (historyStartFreq == NumericConstants.FOUR) {
                projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), NumericConstants.TEN);
                projSelDTO.setHistoryStartMonth(NumericConstants.TEN);
            }
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), historyStartFreq);
            projSelDTO.setHistoryStartMonth(historyStartFreq);
        }
        if (projSelDTO.getProjectionDetails(HISTORY_START_PERIOD.getConstant()) == historyStartFreq && projSelDTO.getProjectionDetails(HISTORY_START_YEAR.getConstant()) == historyStartYear) {
            projSelDTO.addProjectionDetails(HISTORY_START_MONTH_DDLB.getConstant(), projSelDTO.getProjectionDetails(HISTORY_START_MONTH.getConstant()));
        }
        projSelDTO.addProjectionDetails(HISTORY_START_PERIOD_DDLB.getConstant(), historyStartFreq);
        projSelDTO.addProjectionDetails(HISTORY_START_YEAR_DDLB.getConstant(), historyStartYear);
        projSelDTO.setHistoryStartPeriod(historyStartFreq);
        projSelDTO.setHistoryStartYear(historyStartYear);
        projSelDTO.setHistoryStartDay(1);
        boolean modified = false;
        if (projSelDTO.getProjectionDetails(PROJECTION_START_YEAR.getConstant()) < historyStartYear) {
            projSelDTO.setProjectionStartYear(projSelDTO.getHistoryStartYear());
            projSelDTO.addProjectionDetails(PROJECTION_START_YEAR_DDLB.getConstant(), projSelDTO.getHistoryStartYear());
            modified = true;
        }
        if ((projSelDTO.getProjectionStartYear() == projSelDTO.getHistoryStartYear()) && (projSelDTO.getProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant()) < projSelDTO.getHistoryStartMonth() || (modified && projSelDTO.getProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant()) > projSelDTO.getHistoryStartMonth()))) {
            projSelDTO.setProjectionStartMonth(projSelDTO.getHistoryStartMonth());
            projSelDTO.addProjectionDetails(PROJECTION_START_MONTH_DDLB.getConstant(), projSelDTO.getHistoryStartMonth());
            projSelDTO.setProjectionStartPeriod(historyStartFreq);
            projSelDTO.addProjectionDetails(PROJECTION_START_PERIOD.getConstant(), historyStartFreq);
        }
        projSelDTO.setEndYear(projSelDTO.getForecastDTO().getForecastEndYear());
        projSelDTO.setEndPeriod(projSelDTO.getForecastEndPeriod());
        projSelDTO.setEndMonth(projSelDTO.getForecastDTO().getForecastEndMonth());
        projSelDTO.setEndDay(projSelDTO.getForecastEndDay());
        projSelDTO.setStartYear(projSelDTO.getHistoryStartYear());
        projSelDTO.setStartPeriod(projSelDTO.getHistoryStartPeriod());
        projSelDTO.setStartMonth(projSelDTO.getHistoryStartMonth());
        projSelDTO.setStartDay(projSelDTO.getHistoryStartDay());

    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @return
     */
    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote) {
        return CollectionToString(collectionOfString, toAddQuote, false);
    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @param toRemoveSpace
     * @return
     */
    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote, boolean toRemoveSpace) {

        String framedString = StringUtils.EMPTY;
        if (collectionOfString != null && !collectionOfString.isEmpty()) {
            if (toAddQuote) {
                framedString += Arrays.toString(collectionOfString.toArray()).replace('[', '\'').replace(']', '\'').replace(", ", "','");
            } else {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY);
            }

            if (toRemoveSpace) {
                framedString = framedString.replace(", ", StringUtils.EMPTY);
            }
        }
        return framedString;
    }

    public static String[] objectListToStringArray(List<Object> objectList) {
        String[] stringArray = {};
        if (objectList != null) {
            stringArray = new String[objectList.size()];
            stringArray = objectList.toArray(stringArray);
        }
        return stringArray;
    }

    public static String[] stringListToStringArray(List<String> stringList) {
        String[] stringArray = {};
        if (stringList != null) {
            stringArray = new String[stringList.size()];
            stringArray = stringList.toArray(stringArray);
        }
        return stringArray;
    }

    public static List<String> objectListToStringList(List<Object> objectList) {
        return Arrays.asList(objectListToStringArray(objectList));
    }

    public static int getIntegerForMonth(String month) {
        return Arrays.asList(ARRAY_MONTH).indexOf(month) + 1;

    }

    public static String replaceIntegerForMonth(String periods) {
        return periods.replace(ARRAY_MONTH[0], Constant.STRING_ONE)
                .replace(ARRAY_MONTH[1], "2")
                .replace(ARRAY_MONTH[NumericConstants.TWO], "3")
                .replace(ARRAY_MONTH[NumericConstants.THREE], "4")
                .replace(ARRAY_MONTH[NumericConstants.FOUR], "5")
                .replace(ARRAY_MONTH[NumericConstants.FIVE], "6")
                .replace(ARRAY_MONTH[NumericConstants.SIX], "7")
                .replace(ARRAY_MONTH[NumericConstants.SEVEN], "8")
                .replace(ARRAY_MONTH[NumericConstants.EIGHT], "9")
                .replace(ARRAY_MONTH[NumericConstants.NINE], "10")
                .replace(ARRAY_MONTH[NumericConstants.TEN], "11")
                .replace(ARRAY_MONTH[NumericConstants.ELEVEN], "12");
    }

    public static String replaceShortMonthForMonth(String periods) {
        return periods.replace(ARRAY_MONTH[0], "M1")
                .replace(ARRAY_MONTH[1], "M2")
                .replace(ARRAY_MONTH[NumericConstants.TWO], "M3")
                .replace(ARRAY_MONTH[NumericConstants.THREE], "M4")
                .replace(ARRAY_MONTH[NumericConstants.FOUR], "M5")
                .replace(ARRAY_MONTH[NumericConstants.FIVE], "M6")
                .replace(ARRAY_MONTH[NumericConstants.SIX], "M7")
                .replace(ARRAY_MONTH[NumericConstants.SEVEN], "M8")
                .replace(ARRAY_MONTH[NumericConstants.EIGHT], "M9")
                .replace(ARRAY_MONTH[NumericConstants.NINE], "M10")
                .replace(ARRAY_MONTH[NumericConstants.TEN], "M11")
                .replace(ARRAY_MONTH[NumericConstants.ELEVEN], "M12");
    }

    public static List getHistoryDdlbList(int endValue, String period) {
        List history = new ArrayList();
        String periodhistory = period;
        if (periodhistory.equals(Constant.YEAR)) {
            periodhistory = YEARS1;
        } else if (periodhistory.equals(SEMI_ANNUAL.getConstant())) {
            periodhistory = "Semi-Annual Periods";
        }
        for (int i = 1; i <= endValue; i++) {
            if ((i == 1)
                    && (QUARTERS.getConstant().equals(periodhistory) || MONTHS
                    .getConstant().equals(periodhistory) || YEARS1.equals(periodhistory) || "Semi-Annual Periods".equals(periodhistory))) {
                String freq = periodhistory.replace(Constant.S_SMALL, StringUtils.EMPTY);
                history.add(String.valueOf(i) + SPACE.getConstant() + freq);
            } else {
                history.add(i + Constant.SPACE + periodhistory);
            }
        }
        return history;
    }

    public static Locale getLocale() {
        Locale locale = VaadinPortletService.getCurrentPortletRequest().getLocale();
        return locale;
    }

    public static void setPortalConfig(final PortletConfig portletConfig) {
        CommonUtils.setPortletConfig(portletConfig);
    }

    public static void setPortletSession(final PortletSession portletSession) {
        CommonUtils.portletSession = portletSession;

    }

    public static User getUser(final String userId) throws PortalException {
        User loggedUserDetails = null;

        try {
            loggedUserDetails = UserLocalServiceUtil.getUser(Long.parseLong(userId));
        } catch (NoSuchUserException noSuchUserException) {
            LOGGER.error(StringUtils.EMPTY,noSuchUserException);
            loggedUserDetails = null;
        }

        return loggedUserDetails;
    }

    public static void frequenceValueChange(Object value, ComboBox history, SessionDTO session) {
        if (value == null || SELECT_ONE.getConstant().equals(value.toString())) {
            history.removeAllItems();
        } else {
            int historyValue = 0;
            List<String> list = null;
            if (ANNUALLY.getConstant().equals(String.valueOf(value)) || ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(value))) {
                history.removeAllItems();
                list = loadHistory(ANNUALLY.getConstant(), YEAR.getConstant(), session);
                historyValue = 1;

            } else if (SEMI_ANNUALLY.getConstant().equals(String.valueOf(value)) || SEMI_ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(value))) {
                history.removeAllItems();
                list = loadHistory(SEMI_ANNUALLY.getConstant(), SEMI_ANNUAL.getConstant(), session);
                historyValue = NumericConstants.TWO;

            } else if (QUARTERLY.getConstant().equals(String.valueOf(value))) {
                history.removeAllItems();
                historyValue = NumericConstants.FOUR;
                list = loadHistory(QUARTERLY.getConstant(), QUARTERS.getConstant(), session);
            } else if (MONTHLY.getConstant().equals(String.valueOf(value))) {
                history.removeAllItems();
                list = loadHistory(MONTHLY.getConstant(), MONTHS.getConstant(), session);
                historyValue = NumericConstants.TWELVE;
            }
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    int j = i + 1;
                    Object itemId = j;
                    history.addItem(itemId);
                    history.setItemCaption(itemId, list.get(i));
                }
                history.setValue(historyValue);
            }
        }
    }

    public static int getFrequencyNumber(String frequency) {
        int frequencyNo = 0;
        if (frequency.equals(QUARTERLY.getConstant())) {
            frequencyNo = NumericConstants.THREE;
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant()) || frequency.equals(SEMI_ANNUAL.getConstant())) {
            frequencyNo = NumericConstants.SIX;
        } else if (frequency.equals(MONTHLY.getConstant())) {
            frequencyNo = 1;
        } else if (frequency.equals(ANNUALLY.getConstant()) || frequency.equals(ANNUAL.getConstant())) {
            frequencyNo = NumericConstants.TWELVE;
        }
        return frequencyNo;
    }

    public static int getHistoryProjectionNum(String frequency, SessionDTO session) {
        Map<String, Integer> historyEndDetails = getHistoryEndDetails(session, frequency);
        Date dt = getDate(historyEndDetails.get(HISTORY_END_MONTH.getConstant()), historyEndDetails.get(HISTORY_END_YEAR.getConstant()));
        int endValue = getProjections(session.getForecastDTO().getProjectionStartDate(), dt, frequency);
        return endValue;
    }

    public static List<String> loadHistory(String frequency, String period, SessionDTO session) {
        LOGGER.debug("Entering loadHistory method");
        List<String> history;
        history = session.getFrequencyAndQuaterValue(frequency);
        Integer endValue = 0;
        if (history == null || history.isEmpty()) {
            Map<String, Integer> historyEndDetails = getHistoryEndDetails(session, frequency);
            endValue = getProjections(session.getForecastDTO().getHistoryStartDate(), getDate(historyEndDetails.get(HISTORY_END_MONTH.getConstant()), historyEndDetails.get(HISTORY_END_YEAR.getConstant())), frequency);

            history = CommonUtils.getHistoryDdlbList(endValue, period);
            session.addFrequencyAndQuater(frequency, history);
        }
        LOGGER.debug("End of loadHistory method");
        return history;
    }

    public static Integer getProjectionNumber(String frequency, SessionDTO session) {
        LOGGER.debug("Entering getProjectionNumber method");
        Integer period = session.getProjectionPeriod(frequency);
        if (period == null || period.equals(0)) {
            period = CommonUtils.getProjections(session.getForecastDTO().getForecastStartDate(), session.getForecastDTO().getForecastEndDate(), frequency);
            session.addProjectionPeriod(frequency, period);
        }
        LOGGER.debug("End of getProjectionNumber method");
        return period;
    }
//Added for mailNotification
    public static final String WORKFLOW_APPROVAL = "Interface - Workflow Approval";
    public static final String WORKFLOW_SUBMIT = "Interface - Workflow Submission";
    public static final String FORECAST_NON_MANDATED = "Forecasting - Non-Mandated";
    public static final String PROJ_VARIANCE_PRIOR_PROJECTION = "Prior Projection";
    public static final String IS_ALPHABET = "([0-9]|[a-z|A-Z])*";
    //Added for workflow
    public static final String WORKFLOW_NOT_SAVED = "Not Saved";
    public static final String WORKFLOW_STATUS = "WorkflowStatus";

    public static void getMessageNotification(String message) {
        Notification notif = new Notification(message,
                Notification.Type.HUMANIZED_MESSAGE);
        notif.setPosition(Position.MIDDLE_CENTER);
        notif.setStyleName(Constant.MY_STYLE);
        notif.show(Page.getCurrent());

    }

    /**
     * Gets the info notification.
     *
     * @param confirmationHeader the confirmation header
     * @param confirmationMessage the confirmation message
     * @return the info notification
     */
    public static MessageBox getInfoNotification(final String confirmationHeader, final String confirmationMessage) {
        LOGGER.debug("Entering getInfoNotification method ");

        final MessageBox msg = MessageBox.showPlain(Icon.INFO, confirmationHeader, confirmationMessage, new MessageBoxListener() {
            /**
             * The method is triggered when a button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            @SuppressWarnings("PMD")
            @Override
            public void buttonClicked(final ButtonId buttonId) {
                // Do Nothing
            }
        }, ButtonId.OK);
        msg.getButton(ButtonId.OK).focus();
        LOGGER.debug("End of getInfoNotification method");
        return msg;

    }

    public static Map<String, String> getAllUsers()  {
        List<Object> userList = new ArrayList<>();
        Map<String, String> userMap = new HashMap<>();
        DynamicQuery query = UserLocalServiceUtil.dynamicQuery();
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.USER_ID));
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.FIRSTNAME));
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.LASTNAME));
        query.setProjection(productProjectionList);
        try {
            userList = UserLocalServiceUtil.dynamicQuery(query);
            for (int loop = 0, limit = userList.size(); loop < limit; loop++) {
                Object array[] = (Object[]) userList.get(loop);
                userMap.put(String.valueOf(array[0]), String.valueOf(array[NumericConstants.TWO]) + ", " + String.valueOf(array[1]));
            }
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return userMap;
    }

    public static String getListToString(Collection resultList) {
        StringBuilder result = new StringBuilder();
        if (resultList != null && !resultList.isEmpty()) {
            for (Object value : resultList) {
                result.append(value ).append( ',');
            }
            result.deleteCharAt(result.length() - 1);
            return result.toString();
        }
        return "''";
    }

    public static Map<String, String> getAllUserIds()  {
        List<Object> userList = new ArrayList<>();
        Map<String, String> userMap = new HashMap<>();
        DynamicQuery query = UserLocalServiceUtil.dynamicQuery();
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.USER_ID));
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.FIRSTNAME));
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.LASTNAME));
        query.setProjection(productProjectionList);
        try {
            userList = UserLocalServiceUtil.dynamicQuery(query);
            for (int loop = 0, limit = userList.size(); loop < limit; loop++) {
                Object array[] = (Object[]) userList.get(loop);
                userMap.put(String.valueOf(array[NumericConstants.TWO]) + ", " + String.valueOf(array[1]), String.valueOf(array[0]));
            }
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return userMap;
    }

    public static Map<String, String> getAllDiscounts()  {
        List<Object> discountList = new ArrayList<>();
        Map<String, String> userMap = new HashMap<>();
        DynamicQuery query = HelperTableLocalServiceUtil.dynamicQuery();
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.DESCRIPTION));
        query.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, Constant.RS_TYPE));
        query.setProjection(productProjectionList);
        try {
            discountList = HelperTableLocalServiceUtil.dynamicQuery(query);
            for (int loop = 0, limit = discountList.size(); loop < limit; loop++) {
                Object array[] = (Object[]) discountList.get(loop);
                userMap.put(String.valueOf(array[0]), String.valueOf(array[1]));
            }
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return userMap;
    }

    public ComboBox getNativeSelect(final ComboBox select, final List<HelperDTO> helperList) {
        LOGGER.debug("enters getNativeSelect()");
        for (int i = 0; helperList.size() > i; i++) {
            select.addItem(String.valueOf(helperList.get(i)
                    .getId()));
            select.setItemCaption(String.valueOf(helperList.get(i)
                    .getId()), helperList.get(i).getDescription());

        }
        LOGGER.debug("method getNativeSelect ends and returns select");
        return select;
    }

    public static void resetDdlb(ComboBox ddlb) {
        ddlb.removeAllItems();
        ddlb.addItem(SELECT_ONE.getConstant());
        ddlb.setNullSelectionAllowed(true);
        ddlb.setImmediate(true);
        ddlb.setNullSelectionItemId(SELECT_ONE.getConstant());
    }

    public static void loadLevelDdlb(ComboBox ddlb, final boolean isExpCol, List<Leveldto> currentHierarchy) {
        if (ddlb != null) {
            resetDdlb(ddlb);
            if (currentHierarchy != null && !currentHierarchy.isEmpty()) {
                int maxLevel = currentHierarchy.size() - 1;
                for (int i = 0; i < currentHierarchy.size(); i++) {
                    Leveldto levelDto = (Leveldto) currentHierarchy.get(i);
                    if (!isExpCol || levelDto.getCount() <= maxLevel) {
                        ddlb.addItem(levelDto.getTreeLevelNo());
                        ddlb.setItemCaption(levelDto.getTreeLevelNo(), Constant.LEVEL + levelDto.getTreeLevelNo() + " - " + levelDto.getLevel());
                    }
                }
            }
        }
    }

    public static List getCurrent() {
        List list = new ArrayList();
        Calendar ob = Calendar.getInstance();
        int curMonth = ob.get(Calendar.MONTH) + 1;
        int curYear = ob.get(Calendar.YEAR);
        int curQuarter = getQuarter(curMonth);
        int curSemi = getSemiAnnual(curMonth);
        list.add(curMonth);
        list.add(curQuarter);
        list.add(curYear);
        list.add(curSemi);
        return list;
    }

    public static int getQuarter(int month) {
        if (month < NumericConstants.FOUR) {
            return 1;
        }
        if (month < NumericConstants.SEVEN) {
            return NumericConstants.TWO;
        }
        if (month < NumericConstants.TEN) {
            return NumericConstants.THREE;
        }
        return NumericConstants.FOUR;
    }

    public static int getSemiAnnual(int month) {
        if (month < NumericConstants.SEVEN) {
            return 1;
        }
        return NumericConstants.TWO;
    }

    public static ComboBox loadDDLBFromProperties(ComboBox comboBox, String key) {
        comboBox.addItem(SELECT_ONE.getConstant());
        comboBox.setNullSelectionItemId(SELECT_ONE.getConstant());
        List<String> ddlbValues = Arrays.asList(constantProperties.getString(key).split(","));
        if (ddlbValues != null && !ddlbValues.isEmpty()) {
            for (String ddlbValue : ddlbValues) {
                comboBox.addItem(ddlbValue);
            }
        }
        return comboBox;
    }

    /**
     *
     * @param table
     * @param columnWidth
     */
    public static void setExtFilterTreeTableColumnWidth(final ExtFilterTreeTable table, final int columnWidth) {
        if (table != null) {
            for (Object propertyId : table.getVisibleColumns()) {
                table.setColumnWidth(propertyId, columnWidth);
            }
        }
    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @return
     */
    public static String convertCollectionToString(Collection<?> collectionOfString, boolean toAddQuote) {
        return mConvertCollectionToString(collectionOfString, toAddQuote, false);
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> getMonthsofTwoDates(Date startDate, Date endDate) {
        List<String> months = new ArrayList<>();
        DateFormat formater = new SimpleDateFormat("yyyy MMM");
        Calendar beginCalendar = Calendar.getInstance();
        Calendar finishCalendar = Calendar.getInstance();

        beginCalendar.setTime(startDate);
        finishCalendar.setTime(endDate);
        while (beginCalendar.before(finishCalendar) || beginCalendar.equals(finishCalendar)) {
            String date = formater.format(beginCalendar.getTime());
            months.add(date);
            beginCalendar.add(Calendar.MONTH, 1);
        }
        return months;

    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @param toRemoveSpace
     * @return
     */
    public static String mConvertCollectionToString(Collection<?> collectionOfString, boolean toAddQuote, boolean toRemoveSpace) {

        String framedString = StringUtils.EMPTY;
        if (collectionOfString != null && !collectionOfString.isEmpty()) {
            if (toAddQuote) {
                framedString += Arrays.toString(collectionOfString.toArray()).replace('[', '\'').replace(']', '\'').replace(", ", "','");
            } else {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY);
            }

            if (toRemoveSpace) {
                framedString = framedString.replace(", ", StringUtils.EMPTY);
            }
        }
        return framedString;
    }

    /**
     * Get month number from month name
     *
     * @param monthName
     * @return Jan-1........Dec-12
     */
    public static int getMonthNumber(String monthName) {
        int monthNumber = 0;
        try {
            Date date;
            date = new SimpleDateFormat("MMM").parse(monthName);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            monthNumber = cal.get(Calendar.MONTH) + 1;
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage());
        }
        return monthNumber;
    }

    /**
     * Get month name from month number
     *
     * @param monthName
     * @return Jan-1........Dec-12
     */
    public static String getMonthName(int monthNo) {
        String monthName = StringUtils.EMPTY;
        try {
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
            String[] months = dateFormatSymbols.getShortMonths();
            monthName = months[monthNo - 1];
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return monthName;
    }

    public static List getHistoryDdlbListMM(int endValue, String period) {
        List history = new ArrayList();

        for (int i = 1; i <= endValue; i++) {
            if ((i == 1)
                    && (QUARTERS.getConstant().equals(period) || MONTHS
                    .getConstant().equals(period) || YEARS1.equals(period))) {
                String freq = period.replace(Constant.S_SMALL, StringUtils.EMPTY);
                history.add(String.valueOf(i) + SPACE.getConstant() + freq);
            } else {
                history.add(i + Constant.SPACE + period);
            }
        }
        return history;
    }

    public static String getReturnsProperties(String key) {
        LOGGER.debug("getReturnsProperties-->= {} " , key);
        return returnsProperties.getString(key);
    }

    public static int getHelperCode(String listName, String description) throws PortalException {
        final DataSelectionDAO daoHelperCode = new DataSelectionDAOImpl();
        int code = 0;
        final DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LIST_NAME, listName));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.DESCRIPTION, description));
        dynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.HELPER_TABLE_SID));
        List result = daoHelperCode.getHelperTableList(dynamicQuery);
        if (result != null && !result.isEmpty()) {
            code = Integer.parseInt(result.get(ZERO).toString());
        }
        return code;
    }

    /* This method is used to form the date based on selected from/to period */
    public String formDate(String value, String freq, boolean isFromDate) {
        LOGGER.debug("formDate method Starts----->= {}" , freq);
        String date = StringUtils.EMPTY;
        String valueFromDate = value;
        String year = valueFromDate.substring(valueFromDate.length() - NumericConstants.FOUR, valueFromDate.length());
        if (freq.equals(Constant.QUARTERLY)) {
            valueFromDate = valueFromDate.replace(Constant.Q, StringUtils.EMPTY);
            String quarter = valueFromDate.substring(0, 1);
            if (quarter.equals(Constant.STRING_ONE)) {
                if (isFromDate) {
                    date = year + ONE_ONE;
                } else {
                    date = year + "-03-31";
                }
            }
            if (quarter.equals("2")) {
                if (isFromDate) {
                    date = year + "-04-01";
                } else {
                    date = year + "-06-30";
                }
            }
            if (quarter.equals("3")) {
                if (isFromDate) {
                    date = year + SEVEN_ONE;
                } else {
                    date = year + "-09-30";
                }
            }
            if (quarter.equals("4")) {
                if (isFromDate) {
                    date = year + "-10-01";
                } else {
                    date = year + TWO_THIRTYONE;
                }
            }
        } else if (freq.equals(Constant.SEMI_ANNUALLY)) {
            valueFromDate = valueFromDate.toUpperCase(Locale.ENGLISH).replace(Constant.S, StringUtils.EMPTY);
            String semiAnnual = valueFromDate.substring(0, 1);
            if (semiAnnual.equals(Constant.STRING_ONE)) {
                if (isFromDate) {
                    date = year + ONE_ONE;
                } else {
                    date = year + "-06-31";
                }
            }
            if (semiAnnual.equals("2")) {
                if (isFromDate) {
                    date = year + SEVEN_ONE;
                } else {
                    date = year + TWO_THIRTYONE;
                }
            }
        } else if (freq.equals(Constant.ANNUALLY)) {
            if (isFromDate) {
                date = year + ONE_ONE;
            } else {
                date = year + TWO_THIRTYONE;

            }

        } else {

            Map<String, Integer> monthMap = getMonthMap();

            int period = monthMap.get(StringUtils.capitalize(valueFromDate.substring(valueFromDate.length() - NumericConstants.SEVEN, valueFromDate.length() - NumericConstants.FOUR)));

            int startMonth = period + 1;

            if (startMonth == 1) {
                if (isFromDate) {
                    date = year + ONE_ONE;
                } else {
                    date = year + "-01-31";
                }
            } else if (startMonth == NumericConstants.TWO) {
                if (isFromDate) {
                    date = year + "-02-01";
                } else {
                    date = year + "-02-28";
                }
            } else if (startMonth == NumericConstants.THREE) {
                if (isFromDate) {
                    date = year + "-03-01";
                } else {
                    date = year + "-03-31";
                }
            } else if (startMonth == NumericConstants.FOUR) {
                if (isFromDate) {
                    date = year + "-04-01";
                } else {
                    date = year + "-04-30";
                }
            } else if (startMonth == NumericConstants.FIVE) {
                if (isFromDate) {
                    date = year + "-05-01";
                } else {
                    date = year + "-05-31";
                }
            } else if (startMonth == NumericConstants.SIX) {
                if (isFromDate) {
                    date = year + "-06-01";
                } else {
                    date = year + "-06-30";
                }
            } else if (startMonth == NumericConstants.SEVEN) {
                if (isFromDate) {
                    date = year + SEVEN_ONE;
                } else {
                    date = year + "-07-31";
                }
            } else if (startMonth == NumericConstants.EIGHT) {
                if (isFromDate) {
                    date = year + "-08-01";
                } else {
                    date = year + "-08-31";
                }
            } else if (startMonth == NumericConstants.NINE) {
                if (isFromDate) {
                    date = year + "-09-01";
                } else {
                    date = year + "-09-30";
                }
            } else if (startMonth == NumericConstants.TEN) {
                if (isFromDate) {
                    date = year + "-10-01";
                } else {
                    date = year + "-10-31";
                }
            } else if (startMonth == NumericConstants.ELEVEN) {
                if (isFromDate) {
                    date = year + "-11-01";
                } else {
                    date = year + "-11-30";
                }
            } else if (startMonth == NumericConstants.TWELVE) {
                if (isFromDate) {
                    date = year + "-12-01";
                } else {
                    date = year + TWO_THIRTYONE;
                }
            }
        }

        return date;
    }
    public static final String ONE_ONE = "-01-01";
    public static final String SEVEN_ONE = "-07-01";
    public static final String TWO_THIRTYONE = "-12-31";

    public static List<String> prepareProjectionPeriodList(ProjectionSelectionDTO projSelDTO) {
        List<String> projectionPeriodList = new ArrayList<>();
        try {
            String frequency = projSelDTO.getFrequency();
            String projectionStartDate = String.valueOf(projSelDTO.getForecastDTO().getProjectionStartDate());
            String projectionEndDate = String.valueOf(projSelDTO.getForecastDTO().getProjectionEndDate());
            if (frequency.contains(Constant.MONTH1)) {
                projectionPeriodList = monthList(projectionStartDate, projectionEndDate);
            } else if (frequency.contains("Semi")) {
                projectionPeriodList = semiAnnualList(projectionStartDate, projectionEndDate);
            } else if (frequency.contains(Constant.QUARTER1)) {
                projectionPeriodList = quarterList(projectionStartDate, projectionEndDate);
            } else {
                projectionPeriodList = new ArrayList<>();
            }
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage());
        }
        return projectionPeriodList;

    }

    private static List<String> quarterList(String startDateString, String endDateString) throws ParseException {
        List<String> quartersList = new ArrayList<>();
        int startYear = 0;
        int endYear = 0;
        int startMonth = 0;
        int endMonth = 0;
        int startQuarter = 0;
        int endQuarter = 0;
        SimpleDateFormat parser = new SimpleDateFormat(Constant.EEE_MMM_Z_YYYY);
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
        int limit = NumericConstants.FOUR;

        parser.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date startDate = parser.parse(startDateString);
        Date endDate = parser.parse(endDateString);

        startYear = Integer.parseInt(formatYear.format(startDate));
        startMonth = Integer.parseInt(formatMonth.format(startDate));

        endYear = Integer.parseInt(formatYear.format(endDate));
        endMonth = Integer.parseInt(formatMonth.format(endDate));

        startQuarter = (startMonth / NumericConstants.THREE) + 1;
        endQuarter = (endMonth / NumericConstants.THREE) + 1;

        while (startYear <= endYear) {
            if (startYear == endYear) {
                limit = endQuarter;
            }
            while (startQuarter <= limit) {
                quartersList.add("q" + startQuarter + startYear);
                startQuarter++;
            }
            startYear++;
            startQuarter = 1;
        }

        return quartersList;
    }

    private static List<String> semiAnnualList(String startDateString, String endDateString) throws ParseException {
        List<String> semiAnnualList = new ArrayList<>();
        SimpleDateFormat parser = new SimpleDateFormat(Constant.EEE_MMM_Z_YYYY);
        parser.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date startDate = parser.parse(startDateString);
        Date endDate = parser.parse(endDateString);
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
        int startYear = 0; 
        int startMonth = 0;
        int startSemiAnnual = 0;
        int endYear = 0;
        int endMonth = 0;
        int endSemiAnnual = 0;
        int limit = NumericConstants.TWO;

        startMonth = Integer.parseInt(formatMonth.format(startDate));
        startSemiAnnual = (startMonth / NumericConstants.SIX) + 1;

        endMonth = Integer.parseInt(formatMonth.format(endDate));
        endSemiAnnual = (endMonth / NumericConstants.SIX) + 1;

        startYear = Integer.parseInt(formatYear.format(startDate));
        endYear = Integer.parseInt(formatYear.format(endDate));

        while (startYear <= endYear) {
            if (startYear == endYear) {
                limit = endSemiAnnual;
            }
            while (startSemiAnnual <= limit) {
                semiAnnualList.add("s" + startSemiAnnual + startYear);
                startSemiAnnual++;
            }
            startYear++;
            startSemiAnnual = 1;
        }
        return semiAnnualList;
    }

    private static List<String> monthList(String startDateString, String endDateString) throws ParseException {
        List<String> monthList = new ArrayList<>();
        SimpleDateFormat parser = new SimpleDateFormat(Constant.EEE_MMM_Z_YYYY);
        parser.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date startDate = parser.parse(startDateString);
        Date endDate = parser.parse(endDateString);
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
        int startYear = 0;
        int endYear = 0;
        int limit = NumericConstants.TWELVE;
        int startMonth = 0;
        int endMonth = 0;

        startYear = Integer.parseInt(formatYear.format(startDate));
        endYear = Integer.parseInt(formatYear.format(endDate));

        startMonth = Integer.parseInt(formatMonth.format(startDate));
        endMonth = Integer.parseInt(formatMonth.format(endDate));

        while (startYear <= endYear) {
            if (startYear == endYear) {
                limit = endMonth;
            }
            while (startMonth <= limit) {
                monthList.add(trimMonth(startMonth) + startYear);
                startMonth++;
            }
            startYear++;
            startMonth = 1;
        }
        return monthList;
    }

    private static String trimMonth(int startMonth) {
        String monthString = new DateFormatSymbols().getMonths()[startMonth - 1];
        String month = monthString.substring(0, NumericConstants.THREE).toLowerCase(Locale.ENGLISH);
        return month;
    }

    public static String forecastConfigDataHide(String frequency, List<String> forecastConfigList, String column, String dataObject) {

        if (!(frequency.equals(ANNUALLY.getConstant()) || frequency.equals(ANNUAL.getConstant())) && !forecastConfigList.contains(column)) {
            return "0";
        } else {
            return dataObject;
        }
    }

    public static Double forecastConfigDataHide(String frequency, List<String> forecastConfigList, String column, Double dataObject) {

        if (!(frequency.equals(ANNUALLY.getConstant()) || frequency.equals(ANNUAL.getConstant())) && !forecastConfigList.contains(column)) {
            return 0.0;
        } else {
            return dataObject;
        }
    }

    public static Boolean setProjectionZero(ProjectionSelectionDTO dto, String commonColumn) {
        List<String> list = dto.getProjectionHeaderList();
        if (list != null && !list.isEmpty() && !list.contains(commonColumn)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static PortletConfig getPortletConfig() {
            return portletConfig;
    }

    public static void setPortletConfig(PortletConfig portletConfig) {
            CommonUtils.portletConfig = portletConfig;
    }
    
    public static int compareDoubleValues(String value) {
        return Double.compare(Double.parseDouble(value), 0.0);
    }
    
}
