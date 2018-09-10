/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.accrualrateprojection.utils;

import com.stpl.app.gtnforecasting.accrualrateprojection.dto.AccrualRateSelectionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility Class that contains the Constants, Utility Methods and Header
 * Utilities for Accrual Rate Projection.
 *
 * @author sibi
 */
public class AccrualRateUtils {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AccrualRateUtils.class);

    public static final String ADJUSTED_DEMAND = "Forecasted Adjusted Demand";
    public static final String DEMAND = "Forecasted Demand";
    public static final String EX_FACTORY = "Forecasted Ex-Factory Sales - Customer/Product";
    public static final String INVENTORY_WITHDRAWALS = "Forecasted Inventory Withdrawals - Detail";

    public static final String ALL_CUSTOMERS = "All Customers";
    public static final String ALL_BRANDS = "All Brands";
    public static final String ALL_PRODUCTS = "All Products";
    public static final String SELECT_VARIABLES = "-Select Variables-";
    public final Object[] historyPeriods = {Constant.SELECT_ONE, "1 Month", "2 Months", "3 Months", "4 Months", "5 Months", "6 Months"};
    public final Object[] historyPeriods12 = {Constant.SELECT_ONE, "1 Month", "2 Months", "3 Months", "4 Months", "5 Months", "6 Months",
    "7 Months", "8 Months", "9 Months", "10 Months", "11 Months", "12 Months"};
    public static final String SALES = "Sales";
    public static final String PRICE = "Price";
    public static final String COMPANY_ID = "companyId";
    public static final String EXCLUDED_FIELD = "excludedField";
    public static final String COMPANY_NAME= "companyName";
    public static final String RATES = "Rates";
    public static final String DETAILS = "Details";

    public static final String M = "m";
    public static final String DASH = "-";
    public static final String Q = "Q";
    public static final String PRODUCT = "product";
    public static final String GROUP = "group";
    public static final String GROUP_ONE = "group1";
    public static final String SPACE = " ";
    public static final String EFFECTIVE_ACCRUAL_RATE = "Effective Accrual Rate";
    public final String[] availableValuesVisibleHeaders = {"Available Values"};
    public final Object[] availableValuesVisibleColumns = {"companyId"};
    public final String[] excludedValuesVisibleHeaders = {"Excluded Field", " Value"};
    public final Object[] excludedValuesVisibleColumns = {"excludedField", Constant.COMPANY_NAME};
    public static final String DOT = ".";
    public static final String RESET_CONFIRMATION = "Reset Confirmation";
    public static final String RATE = "Rate";
    public static final String AMOUNT = "Amount";

    public static final float MAX_SPLIT_POSITION = 1000;
    public static final float MIN_SPLIT_POSITION = 300;
    public static final float SPLIT_POSITION = 300;
 
    public static final DecimalFormat RATE_FORMAT = new DecimalFormat("#,##0.00");
    public static final DecimalFormat DOLLAR_FORMAT = new DecimalFormat("$#,##0.00");
    public static final DecimalFormat NO_DECIMAL_FORMAT = new DecimalFormat("#,##0");
    public static final String PERCENT = Constant.PERCENT;

    public static final String AVAILABLE_VALUES = "Available Values";
    public static final String EXCLUDED_VALUES = "Excluded Values";

    public static final String CUSTOMER = "customer";    
    public static final String BRAND = "brand";
    
    public static final String MONTH_1_3_FORWARD_QUARTER = "Month 1-3 use forward quarter rate";
    public static final String MONTH_2_3_FORWARD_QUARTER = "Month 2-3 use forward quarter rate";
    public static final String MONTH_3_FORWARD_QUARTER = "Month 3 use forward quarter rate";  
    
    public static final String ADD = "Add";
    public static final String EDIT = "Edit";
    public static final String VIEW = "View";
    public static final String ADD_CASE = "add";
    public static final String OFFSET_WITH_SPACE = " OFFSET ";
    public static final DecimalFormat DOLLAR_FORMAT_WITH_COMMA = new DecimalFormat("$#,##0.00");
    
    /**
     * Enum For Details Variable
     */
    public enum DetailsVariables {

        CHECK_ALL(Constant.CHECK_ALL_CAPS),
        GROSS_TRADE_SALES("Gross Trade Sales"),
        EXCLUDED_GROSS_TRADE_SALES("Excluded Gross Trade Sales"),
        ELIGIBLE_GROSS_TRADE_SALES("Eligible Gross Trade Sales"),
        PERIOD_BASIS_PRICE_CHANGE("Period Basis Price Change"),
        GTS_ACCRUAL_BASIS("GTS Accrual Basis"),
        INVENTORY_WITHDRAWALS_VAR("Inventory Withdrawals"),
        DEMAND_VAR("Demand"),
        ADJUSTED_DEMAND_VAR("Adjusted Demand"),
        PAYMENTS("Payments"),
        RATE("Rate"),
        ACCRUALS("Accruals"),
        EFFECTIVE_ACCRUAL_RATE("Effective Accrual Rate");
        private final String constant;

        private DetailsVariables(String constant) {
            this.constant = constant;
        }

        public String getConstant() {
            return constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] toArray() {
            return Arrays.asList(DetailsVariables.values()).toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY).split(", ");
        }

        public static String[] names() {
            return Arrays.toString(DetailsVariables.values()).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY).split(", ");
        }

        public static String[] getCheckAllVariables() {
            return Arrays.toString(ArrayUtils.removeElement(DetailsVariables.values(), CHECK_ALL)).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY).split(", ");
        }
    }

    /**
     *
     * @param tableHeaderDTO
     */
    public static void getLeftHeadersForDetails(final CustomTableHeaderDTO tableHeaderDTO) {
        tableHeaderDTO.addSingleColumn(Constant.GROUP, "Period Basis For Rate", Object.class);
        tableHeaderDTO.addDoubleColumn(GROUP_ONE, "GL Period");
        tableHeaderDTO.addDoubleHeaderMap(GROUP_ONE, new Object[]{Constant.GROUP});
    }

    public static void getLeftHeadersForSales(final CustomTableHeaderDTO tableHeaderDTO) {
        tableHeaderDTO.addDoubleColumn(GROUP_ONE, " ");
        tableHeaderDTO.addSingleColumn(Constant.GROUP, "Product", Object.class);
        tableHeaderDTO.addDoubleHeaderMap(GROUP_ONE, new Object[]{Constant.GROUP});
    } 


    /**
     * Customizes and generates the headers for the table in rates tab based on
     * the user selection.
     *
     * @param historyStartindex
     * @param filterStartindex
     * @param filterEndIndex
     * @param tableHeaderDTO
     * @param accrualRateSelectionDTO
     */
    public static void createHeadersForDetails(final int historyStartindex, final int filterStartindex, int filterEndIndex, final CustomTableHeaderDTO tableHeaderDTO, final AccrualRateSelectionDTO accrualRateSelectionDTO, final CustomTableHeaderDTO recordHeaderDTO) {

        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        String[] months = dateFormatSymbols.getShortMonths();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, historyStartindex);
        setDefaultCalendarValues(calendar);
        accrualRateSelectionDTO.setStartDate(calendar.getTime());
        accrualRateSelectionDTO.clearAvailableVisibleColumns();
        List<Object> list = new ArrayList<>();
        int j = 0;
        int periodBasisQuarter;
        int year;
        int month;
        for (int i = historyStartindex; i < 0; i++) {
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH); // 1 should be added to get month number as the index start from 0. In some place, index itself used in calculation.
            Object column = M + (month + 1) + DASH + year + DOT + j;
            periodBasisQuarter = generatePeriodBasis(accrualRateSelectionDTO.getPeriodBasis(), (month + 1) % NumericConstants.THREE, month/ NumericConstants.THREE + 1);
            tableHeaderDTO.addSingleColumn(column, Q + (periodBasisQuarter == NumericConstants.FIVE ? 1 : periodBasisQuarter) + ' ' + (periodBasisQuarter == NumericConstants.FIVE ? year + 1 : year), Object.class);
            recordHeaderDTO.addSingleColumn(column, Q + (periodBasisQuarter == NumericConstants.FIVE ? 1 : periodBasisQuarter) + ' ' + (periodBasisQuarter == NumericConstants.FIVE ? year + 1 : year), Object.class);
            tableHeaderDTO.addDoubleColumn(column, months[month] + SPACE + year);
            recordHeaderDTO.addDoubleColumn(column, months[month] + SPACE + year);
            list.add(column);            
            tableHeaderDTO.addDoubleHeaderMap(column, list.toArray());
            recordHeaderDTO.addDoubleHeaderMap(column, list.toArray());
            accrualRateSelectionDTO.addToAvailableVisibleColumns(column.toString());
            list.clear();
            calendar.add(Calendar.MONTH, 1);
            j++;
        }

        int projectionStartIndex = 0;
        int projectionEndIndex = NumericConstants.THIRTY_FIVE;
        if (accrualRateSelectionDTO.isFilterValid()) {
            calendar.add(Calendar.MONTH, filterStartindex);
            projectionStartIndex = filterStartindex;
            if (filterEndIndex != 0) {
                projectionEndIndex = filterEndIndex;
            }
        }        
        accrualRateSelectionDTO.clearFilterlist();

        for (int i = projectionStartIndex; i < projectionEndIndex; i++) {
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH); // 1 should be added to get month number as the index start from 0. In some place, index itself used in calculation.
            Object column = M + (month + 1) + DASH + year + DOT + j;
            periodBasisQuarter = generatePeriodBasis(accrualRateSelectionDTO.getPeriodBasis(), (month + 1) % NumericConstants.THREE, month / NumericConstants.THREE + 1);
            tableHeaderDTO.addSingleColumn(column, Q + (periodBasisQuarter == NumericConstants.FIVE ? 1 : periodBasisQuarter) + ' ' + (periodBasisQuarter == NumericConstants.FIVE ? year + 1 : year), Object.class);
            recordHeaderDTO.addSingleColumn(column, Q + (periodBasisQuarter == NumericConstants.FIVE ? 1 : periodBasisQuarter) + ' ' + (periodBasisQuarter == NumericConstants.FIVE ? year + 1 : year), Object.class);
            tableHeaderDTO.addDoubleColumn(column, months[month] + SPACE + year);
            recordHeaderDTO.addDoubleColumn(column, months[month] + SPACE + year);
            list.add(column);
            tableHeaderDTO.addDoubleHeaderMap(column, list.toArray());            
            recordHeaderDTO.addDoubleHeaderMap(column, list.toArray());            
            accrualRateSelectionDTO.addToAvailableVisibleColumns(column.toString());
            accrualRateSelectionDTO.addFilterlist(column);
            list.clear();
            calendar.add(Calendar.MONTH, 1);
            j++;
        }

        calendar.add(Calendar.MONTH, -1);
        accrualRateSelectionDTO.setEndDate(calendar.getTime());
        }

    /**
     * Generates the Quarter based on the selected period basis.
     *
     * @param periodBasis
     * @param reminder
     * @param quarter
     * @return
     */
    private static int generatePeriodBasis(String periodBasis, final int reminder, final int quarter) {
        int value = 1;
        String generatePeriodBasis = periodBasis == null ? StringUtils.EMPTY : periodBasis;
        switch (generatePeriodBasis) {
            case AccrualRateUtils.MONTH_1_3_FORWARD_QUARTER:
                value = quarter + 1;
                break;
            case AccrualRateUtils.MONTH_2_3_FORWARD_QUARTER:
                value = reminder == 1 ? quarter : quarter + 1;
                break;
            case AccrualRateUtils.MONTH_3_FORWARD_QUARTER:
                value = reminder != 0 ? quarter : quarter + 1;
                break;
            default:
                LOGGER.debug("Invalid Period Basis= {} " , generatePeriodBasis);
                value = quarter + 1;
                break;
        }
        return value;
    }
    
    public static void createHeadersForSales(final int historyStartindex, int filterStartindex, int filterEndIndex, final CustomTableHeaderDTO tableHeaderDTO, final AccrualRateSelectionDTO accrualRateSelectionDTO, List<String> selectedValues, final CustomTableHeaderDTO recordHeaderDTO) {

        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        String[] months = dateFormatSymbols.getShortMonths();
        Calendar calendar = Calendar.getInstance();
        List<Object> list = new ArrayList<>();
        calendar.add(Calendar.MONTH, historyStartindex);
        setDefaultCalendarValues(calendar);
        accrualRateSelectionDTO.setStartDate(calendar.getTime());
        accrualRateSelectionDTO.clearAvailableVisibleColumns();

        int j = 0;
        for (int i = historyStartindex; i < 0; i++) {

            tableHeaderDTO.addDoubleColumn(M + (calendar.get(Calendar.MONTH) + 1) + DASH + calendar.get(Calendar.YEAR), months[calendar.get(Calendar.MONTH)] + SPACE + calendar.get(Calendar.YEAR));
            recordHeaderDTO.addDoubleColumn(M + (calendar.get(Calendar.MONTH) + 1) + DASH + calendar.get(Calendar.YEAR), months[calendar.get(Calendar.MONTH)] + SPACE + calendar.get(Calendar.YEAR));

            if (!selectedValues.isEmpty()) {
                for (int k = 0; k < selectedValues.size(); k++) {
                    Object column = M + (calendar.get(Calendar.MONTH) + 1) + DASH + calendar.get(Calendar.YEAR) + (selectedValues.get(k)).replace(" ", StringUtils.EMPTY) + DOT + j;
                    tableHeaderDTO.addSingleColumn(column, selectedValues.get(k), Object.class);
                    recordHeaderDTO.addSingleColumn(column, selectedValues.get(k), Object.class);
                    list.add(M + (calendar.get(Calendar.MONTH) + 1) + DASH + calendar.get(Calendar.YEAR) + (selectedValues.get(k)).replace(" ", StringUtils.EMPTY) + DOT + j);
                    accrualRateSelectionDTO.addToAvailableVisibleColumns(column.toString());
                    j++;
                }
            }
            Object doubleColumn = M + (calendar.get(Calendar.MONTH) + 1) + DASH + calendar.get(Calendar.YEAR);
            tableHeaderDTO.addDoubleHeaderMap(doubleColumn, list.toArray());
            recordHeaderDTO.addDoubleHeaderMap(doubleColumn, list.toArray());
            calendar.add(Calendar.MONTH, 1);
             
            list.clear();
        }
        int projectionStartIndex = 0;
        int projectionEndIndex = NumericConstants.THIRTY_FIVE;
        if (accrualRateSelectionDTO.isFilterValid()) {
            calendar.add(Calendar.MONTH, filterStartindex);
            projectionStartIndex = filterStartindex;
            if (filterEndIndex != 0) {
                projectionEndIndex = filterEndIndex;
            }
        }

        accrualRateSelectionDTO.clearFilterlist();
        for (int i = projectionStartIndex; i < projectionEndIndex; i++) {

            tableHeaderDTO.addDoubleColumn(M + (calendar.get(Calendar.MONTH) + 1) + DASH + calendar.get(Calendar.YEAR), months[calendar.get(Calendar.MONTH)] + SPACE + calendar.get(Calendar.YEAR));
            recordHeaderDTO.addDoubleColumn(M + (calendar.get(Calendar.MONTH) + 1) + DASH + calendar.get(Calendar.YEAR), months[calendar.get(Calendar.MONTH)] + SPACE + calendar.get(Calendar.YEAR));

            if (!selectedValues.isEmpty()) {
                for (int k = 0; k < selectedValues.size(); k++) {
                    Object column = M + (calendar.get(Calendar.MONTH) + 1) + DASH + calendar.get(Calendar.YEAR) + (selectedValues.get(k)).replace(" ", StringUtils.EMPTY) + DOT + j;
                    tableHeaderDTO.addSingleColumn(column, selectedValues.get(k), Object.class);
                    recordHeaderDTO.addSingleColumn(column, selectedValues.get(k), Object.class);
                    list.add(M + (calendar.get(Calendar.MONTH) + 1) + DASH + calendar.get(Calendar.YEAR) + (selectedValues.get(k)).replace(" ", StringUtils.EMPTY) + DOT + j);
                    accrualRateSelectionDTO.addToAvailableVisibleColumns(column.toString());
                    j++;
                }
            }
            Object doubleColumn = M + (calendar.get(Calendar.MONTH) + 1) + DASH + calendar.get(Calendar.YEAR);
            tableHeaderDTO.addDoubleHeaderMap(doubleColumn, list.toArray());
            recordHeaderDTO.addDoubleHeaderMap(doubleColumn, list.toArray());
            accrualRateSelectionDTO.addFilterlist(doubleColumn);
            calendar.add(Calendar.MONTH, 1);

            list.clear();
        }
        calendar.add(Calendar.MONTH, -1);
        accrualRateSelectionDTO.setEndDate(calendar.getTime());
    }

    /**
     * Customizes and generates the headers for the tables in all tabs available
     * in Accrual Rate Projection based on the user selection.
     *
     * @param tableHeaderDTO - CustomTableHeaderDTO
     * @param historyValue - Value from History DDLB.
     * @param accrualRateSelectionDTO
     */
    public static void getRightHeadersForAccrual(final CustomTableHeaderDTO tableHeaderDTO, final Object historyValue, final AccrualRateSelectionDTO accrualRateSelectionDTO, final String screenName, final List<String> selectedValues, final CustomTableHeaderDTO recordHeaderDTO) {
        int historyStartPeriod = historyValue == null || historyValue.equals(Constant.SELECT_ONE) ? -1 : Integer.parseInt(DASH + (historyValue.toString().substring(0, 2).trim()));
        int filterEndIndex = 0;
        int filterStartindex = 0;
        if (accrualRateSelectionDTO.isFilterValid()) {
            filterStartindex = getMonthDifference(new GregorianCalendar(accrualRateSelectionDTO.getFilterStartYear(), accrualRateSelectionDTO.getFilterStartMonth() - 1, 1));
            if (accrualRateSelectionDTO.getFilterEndYear() != 0 && accrualRateSelectionDTO.getFilterEndMonth() != 0) {
                filterEndIndex = getMonthDifference(new GregorianCalendar(accrualRateSelectionDTO.getFilterEndYear(), accrualRateSelectionDTO.getFilterEndMonth() - 1, 1)) + 1;
            }
        }
        switch (screenName) {
            case RATES:
                createHeadersForRates(historyStartPeriod, filterStartindex, filterEndIndex, tableHeaderDTO, recordHeaderDTO, accrualRateSelectionDTO);
                break;
            case SALES:
                createHeadersForSales(historyStartPeriod, filterStartindex, filterEndIndex, tableHeaderDTO, accrualRateSelectionDTO, selectedValues, recordHeaderDTO);
                break;
            case DETAILS:
                createHeadersForDetails(historyStartPeriod, filterStartindex, filterEndIndex, tableHeaderDTO, accrualRateSelectionDTO, recordHeaderDTO);
                break;
            default:
                LOGGER.warn("screenName is not valid= {} " , screenName);
                break;
        }
    }

    /**
     * Method creates the header with given inputs. startPeriod is the month
     * from which the headers starts. offset is the number of months required
     * from start period.
     *
     * @param historyStartindex - history start index
     * @param filterStartindex - Start range of the header.
     * @param filterEndIndex - total numbers headers required. End = Start +
     * Offset.
     * @param tableHeaderDTO - CustomTableHeaderDTO
     * @param accrualRateSelectionDTO - dto to use the range of periods that
     * will be used in query.
     */
    public static void createHeadersForRates(final int historyStartindex, final int filterStartindex, int filterEndIndex, final CustomTableHeaderDTO tableHeaderDTO, final CustomTableHeaderDTO recordHeaderDTO, final AccrualRateSelectionDTO accrualRateSelectionDTO) {
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        String[] months = dateFormatSymbols.getShortMonths();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, historyStartindex);
        setDefaultCalendarValues(calendar);
        accrualRateSelectionDTO.setStartDate(calendar.getTime());
        accrualRateSelectionDTO.clearAvailableVisibleColumns();
        int j = 0;
        for (int i = historyStartindex; i < 0; i++) {
            Object column = M + (calendar.get(Calendar.MONTH) + 1) + DASH + calendar.get(Calendar.YEAR) + DOT + j;
            tableHeaderDTO.addSingleColumn(column, months[calendar.get(Calendar.MONTH)] + SPACE + calendar.get(Calendar.YEAR), Object.class);
            recordHeaderDTO.addSingleColumn(column, months[calendar.get(Calendar.MONTH)] + SPACE + calendar.get(Calendar.YEAR), Object.class);
            accrualRateSelectionDTO.addToAvailableVisibleColumns(column.toString());
            calendar.add(Calendar.MONTH, 1);
            j++;
        }

        int projectionStartIndex = 0;
        int projectionEndIndex = NumericConstants.THIRTY_FIVE;
        if (accrualRateSelectionDTO.isFilterValid()) {
            calendar.add(Calendar.MONTH, filterStartindex);
            projectionStartIndex = filterStartindex;
            if (filterEndIndex != 0) {
                projectionEndIndex = filterEndIndex;
            }
        }
        
        accrualRateSelectionDTO.clearFilterlist();        
        for (int i = projectionStartIndex; i < projectionEndIndex; i++) {
            Object column = M + (calendar.get(Calendar.MONTH) + 1) + DASH + calendar.get(Calendar.YEAR) + DOT + j;
            String header = months[calendar.get(Calendar.MONTH)] + SPACE + calendar.get(Calendar.YEAR);
            tableHeaderDTO.addSingleColumn(column, header, Object.class);
            recordHeaderDTO.addSingleColumn(column, header, Object.class);
            accrualRateSelectionDTO.addFilterlist(column);
            accrualRateSelectionDTO.addToAvailableVisibleColumns(column.toString());
            calendar.add(Calendar.MONTH, 1);
            j++;
        }

        calendar.add(Calendar.MONTH, -1);
        accrualRateSelectionDTO.setEndDate(calendar.getTime());
    }

    /**
     * Method sets the values of the calendar object to the start value of a
     * month and day.
     *
     * @param calendar - Calendar that needs to be customized.
     */
    public static void setDefaultCalendarValues(final Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * Methods compares the passed calendar object with the current date
     * calendar object and returns the monthly difference.
     *
     * @param calendar - CalendarObject that needs to be compared.
     * @return - No. of Months difference.
     */
    public static int getMonthDifference(final Calendar calendar) {
        Calendar currentCalendar = Calendar.getInstance();
        int diffYear = calendar.get(Calendar.YEAR) - currentCalendar.get(Calendar.YEAR);
        int diffMonth = diffYear * NumericConstants.TWELVE + calendar.get(Calendar.MONTH) - currentCalendar.get(Calendar.MONTH);
        return diffMonth;
    }

    private AccrualRateUtils() {

    }

    public enum AccrualSalesVariables {

        CHECK_ALL(Constant.CHECK_ALL_CAPS),
        GROSS_TRADE_SALES("Total Units"),
        EXCLUDED_GROSS_TRADE_SALES("Excluded Units"),
        ELIGIBLE_GROSS_TRADE_SALES("Net Units"),
        PERIOD_BASIS_PEICE_CHANGE("Price"),
        GTS_ACCRUAL_BASIS("Sales");
        private final String constant;

        private AccrualSalesVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(AccrualSalesVariables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

        public static String[] getCheckAllVariables() {
            return Arrays.toString(ArrayUtils.removeElement(AccrualSalesVariables.values(), CHECK_ALL)).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }
    }

    public static void getCurrentCheckValue(List<String> selectedVariables, CustomMenuBar.CustomMenuItem customMenuItem, final String tabName) {

        selectedVariables.clear();
        if (customMenuItem != null && customMenuItem.getSize() > 0) {
            List<CustomMenuBar.CustomMenuItem> items = customMenuItem.getChildren();
            for (CustomMenuBar.CustomMenuItem customMenuItem1 : items) {
                if (customMenuItem1.isChecked()) {                    
                    selectedVariables.add(customMenuItem1.getMenuItem().getCaption());
                }
            }
        }
        if (SALES.equals(tabName)) {
            selectedVariables.remove("Check All");
        }

    }
    private static AccrualRateUtils object;

    public static AccrualRateUtils getInstance() {
        if (object == null) {
            object = new AccrualRateUtils();
        }
        return object;
    }
}
