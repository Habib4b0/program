
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.utils;

import static com.stpl.app.galforecasting.utils.HeaderUtils.getCommonColumnHeader;
import com.stpl.app.galforecasting.dto.ForecastDTO;
import com.stpl.app.galforecasting.dto.PVSelectionDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.logic.SalesProjectionLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import static com.stpl.app.galforecasting.utils.CommonUtils.isInteger;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.FrequencyConstants.*;
import static com.stpl.app.utils.Constants.LabelConstants.*;
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
import static com.stpl.app.utils.Constants.ProjectionConstants.HISTORY_START_PERIOD;
import static com.stpl.app.utils.Constants.ProjectionConstants.HISTORY_START_YEAR;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTED;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_END_DAY;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_END_PERIOD;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_START_DAY;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_START_MONTH;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_START_MONTH_DDLB;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_START_PERIOD;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_START_YEAR;
import static com.stpl.app.utils.Constants.ProjectionConstants.PROJECTION_START_YEAR_DDLB;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class HeaderUtils.
 *
 * @author SooriyaNarayanan
 */
public class HeaderUtils {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HeaderUtils.class);
    /**
     * The quarter.
     */

    /**
     * The quarters.
     */
    private static String[] quarters = new String[]{" Q1", " Q2", " Q3", " Q4"};
    /**
     * The annual.
     */

    /**
     * The semi annual.
     */
    /**
     * The months.
     */
    private static String[] months = new String[]{" Jan", " Feb", " Mar", " Apr", " May", " Jun", " Jul", " Aug", " Sep", " Oct", " Nov", " Dec"};
    /**
     * The semi annuals.
     */
    private static String[] semiAnnuals = new String[]{" SA1", " SA2"};
    /**
     * The month.
     */

    /**
     * The supplemental columns.
     */
    public static Object[] SUPPLEMENTAL_COLUMNS = new Object[]{"customer",
        "actuals", "projections"};
    /**
     * The supplemental header.
     */
    public static String[] SUPPLEMENTAL_HEADER = new String[]{Constant.CUSTOMER_SMALL,
        ACTUALS.getConstant(), PROJECTIONS.getConstant()};
    /**
     * The supplemental double columns.
     */
    public static Object[] SUPPLEMENTAL_DOUBLE_COLUMNS = new Object[]{"ndc",
        "quarters"};
    /**
     * The supplemental double header.
     */
    public static String[] SUPPLEMENTAL_DOUBLE_HEADER = new String[]{" ", "Q1 2014"};
    /**
     * -- DP TRIPLE HEADER --
     */
    /**
     * The Constant DP_LEFT INNER COLUMNS.
     */
    public static final Object[] DP_LEFT_FIRST_COLUMNS = new Object[]{Constant.CHECK, "level", Constant.GROUP};
    /**
     * The Constant DP_LEFT INNER HEADER.
     */
    public static final String[] DP_LEFT_FIRST_HEADERS = new String[]{" ", "Level Name", Constant.GROUPFCAPS};
    /**
     * The Constant DP_LEFT INNER COLUMNS.
     */
    public static final Object[] DP_RIGHT_FIRST_COLUMNS = new Object[]{"historyCol1ActualRate", "historyCol1ProjRate", "historyCol2ActualRate", "historyCol2ProjRate", "projCol1Rate", "projCol2Rate", "projCol3Rate", "projCol4Rate"};
    /**
     * The Constant DP_LEFT INNER HEADER.
     */
    public static final String[] DP_RIGHT_FIRST_HEADERS = new String[]{"Actual Rate", "Projected Rate", "Actual Rate", "Projected Rate", "Projected Rate", "Projected Rate", "Projected Rate", "Projected Rate"};
    /**
     * The Constant DP_LEFT SECOND COLUMNS.
     */
    public static final Object[] DP_LEFT_SECOND_COLUMNS = new Object[]{Constant.GROUP};
    /**
     * The Constant DP_LEFT SECOND HEADER.
     */
    public static final String[] DP_LEFT_SECOND_HEADERS = new String[]{" "};
    /**
     * The Constant DP_LEFT SECOND COLUMNS.
     */
    public static final Object[] DP_RIGHT_SECOND_COLUMNS = new Object[]{"historyCol1ActualRate", "historyCol1ProjRate", "projCol1Rate", "projCol2Rate", "projCol3Rate", "projCol4Rate"};
    /**
     * The Constant DP_LEFT SECOND HEADER.
     */
    public static final String[] DP_RIGHT_SECOND_HEADERS = new String[]{"Q1 2014", "Q2 2014", "Q3 2014", "Q4 2014", "Q1 2015", "Q2 2015"};
    /**
     * The Constant DP_LEFT SECOND COLUMNS.
     */
    public static final Object[] DP_LEFT_THIRD_COLUMNS = new Object[]{Constant.GROUP};
    /**
     * The Constant DP_LEFT SECOND HEADER.
     */
    public static final String[] DP_LEFT_THIRD_HEADERS = new String[]{" "};
    /**
     * The Constant DP_LEFT SECOND COLUMNS.
     */
    public static final Object[] DP_RIGHT_THIRD_COLUMNS = new Object[]{"historyCol1ActualRate"};
    /**
     * The Constant DP_LEFT SECOND HEADER.
     */
    public static final String[] DP_RIGHT_THIRD_HEADERS = new String[]{" < Price Group 1 >"};
    /**
     * -- DP TRIPLE HEADER --
     */
    /**
     * The Constant DP_RIGHT_TRIPLE_HEADER.
     */
    public static final String[] DP_RIGHT_TRIPLE_HEADER = new String[]{"discount name"};

    /* Right Top Visible Columns */
    /**
     * The Constant DP_RIGHT_TRIPLE_HEADER_COLUMNS.
     */
    public static final Object[] DP_RIGHT_TRIPLE_HEADER_COLUMNS = new Object[]{"discName"};
    /**
     * The Constant DP_LEFT_DOUBLE_HEADER_COLUMNS.
     */
    public static final Object[] DP_LEFT_DOUBLE_HEADER_COLUMNS = new Object[]{Constant.CHECK};
    /**
     * The Constant DP_LEFT_DOUBLE_HEADER.
     */
    public static final String[] DP_LEFT_DOUBLE_HEADER = new String[]{" "};

    /* Right Top Visible Columns */
    /**
     * The Constant DP_LEFT_VISIBLE_COLUMNS.
     */
    public static final Object[] DP_LEFT_VISIBLE_COLUMNS = new Object[]{Constant.CHECK, "level"};
    /* Right Top Visible Columns */
    /**
     * The Constant DP_LEFT_VISIBLE_HEADER.
     */
    public static final String[] DP_LEFT_VISIBLE_HEADER = new String[]{" ", "level"};
    /* Right Top Header */
    /**
     * The Constant SPR_RIGHT_DOUBLE_HEADER.
     */
    public static final String[] SPR_RIGHT_DOUBLE_HEADER = new String[]{"Q1 2014", "Q2 2014", "Q3 2014", "Q4 2014"};

    /* Right Top Visible Columns */
    /**
     * The Constant SPR_RIGHT_DOUBLE_HEADER_COLUMNS.
     */
    public static final Object[] SPR_RIGHT_DOUBLE_HEADER_COLUMNS = new Object[]{"historyCol1Sales", "historyCol2Sales", "historyCol3Sales", "historyCol4Sales"};

    /* Right Lower Header */
    /**
     * The Constant SPR_RIGHT_VISIBLE_HEADER.
     */
    public static final String[] SPR_RIGHT_VISIBLE_HEADER = new String[]{"actuals", "projections", "actuals", "projections", "actuals", "projections", "actuals", "projections"};

    /* Right Lower Visible Columns */
    /**
     * The Constant SPR_RIGHT_VISIBLE_COLUMNS.
     */
    public static final Object[] SPR_RIGHT_VISIBLE_COLUMNS = new Object[]{"historyCol1Sales", "historyCol2Sales", "historyCol3Sales", "historyCol4Sales", "historyCol5Sales", "historyCol6Sales", "historyCol7Sales", "historyCol8Sales"};
    /**
     * The Constant LEFT_TABLE_HEADER_ONE__COLUMNS.
     */
    public static final Object[] LEFT_TABLE_HEADER_ONE__COLUMNS = new Object[]{
        Constant.CHECK, "level"};
    /**
     * The Constant CUST_ID_TRANSF_HEADER.
     */
    public static final String[] LEFT_TABLE_HEADER_ONE__HEADERS = new String[]{
        StringUtils.EMPTY, "Level Name"};
    /**
     * The Constant LEFT_TABLE__HEADER_SECOND_HEADERS.
     */
    public static final String[] LEFT_TABLE__HEADER_SECOND_HEADERS = new String[]{StringUtils.EMPTY};
    /**
     * The Constant LEFT_TABLE__HEADER_SECOND_COLUMNS.
     */
    public static final Object[] LEFT_TABLE__HEADER_SECOND_COLUMNS = new Object[]{"actualsales"};
    /**
     * The comparison columns.
     */
    public static Object[] COMPARISON_COLUMNS = new Object[]{Constant.PROJECTION_NAME, Constant.PROJECTIONDESCRIPTION,
        "marketType", "customer", Constant.CONTRACT, Constant.BRAND, "ndcNo", "ndcName", "createdDate", "createdBy"};
    /**
     * The parity header.
     */
    public static String[] COMPARISON_HEADER = new String[]{"Projection Name", "Description",
        "Market Type", Constant.CUSTOMER_SMALL, Constant.CONTRACT_SMALL, Constant.BRAND_CAPS, "NDC #", "NDC Name", "Created Date", "Created By"};
    /**
     * The SALES_SMALL projection columns.
     */
    public static Object[] SALES_RIGHT_TABLE_COLUMNS = new Object[]{Constant.GROUP,
        Constant.BASELINE, Constant.METHODOLOGY, "actualsales", "projectedsales",
        "actualunits", "projectedunits", "projectedsales1",
        "projectedunits1", "productGrowth", "accountGrowth"};
    /**
     * The SALES_SMALL projection header.
     */
    public static String[] SALES_RIGHT_TABLE_HEADERS = new String[]{Constant.GROUPFCAPS,
        "Base Line", "Methodology", Constant.ACTUAL_SALES, "Projected Sales",
        "Actual Units", "Projected Units", "Projected Sales",
        "Projected Units", Constant.PRODUCT_GROWTH, Constant.ACCOUNT_GROWTH};
    /**
     * The Constant SUPPLEMENTAL_RIGHT_TABLE_ONE_COLUMNS.
     */
    public static final Object[] SUPPLEMENTAL_RIGHT_TABLE_ONE_COLUMNS = new Object[]{
        "actualDiscount", Constant.METHODOLOGY, "contractPrice", "discount1",
        "discount2", "contractEndDate", "methodologyQ4", "contractPriceQ4",
        "methodologyQ1", "contractPriceQ1"};
    /**
     * The Constant RIGHT_TABLE_HEADER_SECOND_HEADERS.
     */
    public static final String[] RIGHT_TABLE_HEADER_SECOND_HEADERS = new String[]{
        " ", "Q4 2013", "Q1 2014 Results"};
    /**
     * The Constant RIGHT_TABLE_HEADER_SECOND_COLUMNS.
     */
    public static final Object[] RIGHT_TABLE_HEADER_SECOND_COLUMNS = new Object[]{
        "default", "q1", "q2"};
    /**
     * The Constant PVS_VISIBLE_COLUMN.
     */
    public static final Object PVS_VISIBLE_COLUMN_LEFT[] = new Object[]{"pvCheck",
        "projectionPeriodPV", "contractHolder", "costomer"};
    /**
     * The Constant PVS_COLUMN_HEADER.
     */
    public static final String PVS_COLUMN_HEADER_LEFT[] = new String[]{StringUtils.EMPTY,
        "Projection Period", "Contract Holder", "Customer ID"};

    /**
     * The Constant PVS_VISIBLE_COLUMN.
     */
    public static final Object PVS_VISIBLE_COLUMN[] = new Object[]{"segment",
        "marketType", "brandPV", "grossSalePriorPV",
        "grossSaleProjectedPV", "grossSaleChangePV",
        "totalDiscountPriorPV", "totalDiscountProjectedPV",
        "totalDiscountChangePV", "netSalePriorPV", "netSaleProjectedPV",
        "netSaleChangePV", "reasonCodePV", "commentaryPV", "camNamePV"};
    /**
     * The Constant PVS_COLUMN_HEADER.
     */
    public static final String PVS_COLUMN_HEADER[] = new String[]{"Segment", "Market Type",
        Constant.BRAND_CAPS, "Gross Sale Prior", "Gross Sale Projected",
        "Gross Sale % Change", "Total Discount % Prior",
        "Total Discount % Projected", "Total Discount % Change",
        "Net Sale $ Prior", "Net Sale $ Projected", "Net Sale % Change",
        "Reason Code", "Commentary", "Created By"};
    /**
     * The Constant PVS_VISIBLE_COLUMN.
     */
    public static final Object PVS_VISIBLE_COLUMN_EXCEL[] = new Object[]{"projectionPeriodPV", "contractHolder", "costomer", "segment",
        "marketType", "brandPV", "grossSalePriorPV",
        "grossSaleProjectedPV", "grossSaleChangePV",
        "totalDiscountPriorPV", "totalDiscountProjectedPV",
        "totalDiscountChangePV", "netSalePriorPV", "netSaleProjectedPV",
        "netSaleChangePV", "reasonCodePV", "commentaryPV", "camNamePV"};
    /**
     * The Constant PVS_COLUMN_HEADER.
     */
    public static final String PVS_COLUMN_HEADER_EXCEL[] = new String[]{"Projection Period", "Contract Holder", "Customer ID", "Segment", "Market Type",
        Constant.BRAND_CAPS, "Gross Sale Prior", "Gross Sale Projected",
        "Gross Sale Change", "Total Discount Prior",
        "Total Discount Projected", "Total Discount Change",
        "Net Sale Prior", "Net Sale Projected", "Net Sale Change",
        "Reason Code", "Commentary", "Created By"};

    public static final Object PPA_DETAILS_VISIBLE_COLUMN_EXCEL[] = new Object[]{"period","rebateScheduleName", "priceProtectionPriceType", "price", "priceChange", "map",
        "totalDeductions", "units", "deductionPerUnit", "netPrice", "netMAP", "priceProtectionAmountPerUnit", "priceProtectionPercent", "totalPriceProtectionDeduction",
        "nep", "nepFormula", "priceToleranceType", "priceTolerance", "priceToleranceInterval", "priceToleranceFrequency", "maxIncrementalChange",
        "resetEligible", "resetType", "resetDate", "resetInterval", "resetFrequency", "netPriceType", "netPriceTypeFormula"};

    public static final String PPA_DETAILS_VISIBLE_HEADER_EXCEL[] = new String[]{Constant.PERIOD,"Rebate Schedule Name", "Price Protection Price Type", "Price", "Price Change", "MAP",
        "Total Deductions", Constant.UNITS_SMALL, "Deduction Per Unit", "Net Price", "Net MAP", "Price Protection Amount Per Unit", "Price Protection %", "Total Price Protection Deduction",
        "NEP", "NEP Formula", "Price Tolerance Type", "Price Tolerance", "Price Tolerance Interval", "Price Tolerance Frequency", "Max Incremental Change",
        "Reset Eligible", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency", "Net Price Type", "Net Price Type Formula"};

    /**
     * The parity columns.
     */
    public static Object[] PARITY_COLUMNS = new Object[]{"contractName",
        "brandName", Constant.ITEM_NO, "itemDesc"};
    /**
     * The parity header.
     */
    public static String[] PARITY_HEADER = new String[]{Constant.CONTRACT_SMALL, Constant.BRAND_CAPS,
        "NDC #", "NDC Description"};
    /**
     * The Constant PVS_VISIBLE_COLUMN.
     */
    public static final Object PV_CIDT_VISIBLE_COLUMN[] = new Object[]{"contractHolderOrTP", "customerID", "segment", "marketType", "movedFromname", "movedFromCustomerID", "movedToname", "movedToCustomerID", "effectiveDate", "annualGrossSales"};
    /**
     * The Constant PVS_COLUMN_HEADER.
     */
    public static final String PV_CIDT_COLUMN_HEADER[] = new String[]{"Contract Holder/Trading Partner", "Customer ID", "Segment", "Market Type", "Moved From (name)", "Moved From (Customer ID)", "Moved To (name)", "Moved To (Customer ID)", "Effective Date", "Annual Gross Sales"};
    /**
     * The Constant PVS_VISIBLE_COLUMN.
     */
    public static final Object PV_SALES_VISIBLE_COLUMN[] = new Object[]{"projectionPeriod", "tradingPartner", "customerID", Constant.GROUP, Constant.BRAND, "productGrowth", "accountGrowth"};
    /**
     * The Constant PVS_COLUMN_HEADER.
     */
    public static final String PV_SALES_COLUMN_HEADER[] = new String[]{"Projection Period", Constant.TRADING_PARTNER, "Customer ID", Constant.GROUPFCAPS, Constant.BRAND_CAPS, Constant.PRODUCT_GROWTH, Constant.ACCOUNT_GROWTH};
    /**
     * The Constant PVS_VISIBLE_COLUMN.
     */
    public static final Object PV_DISCOUNT_VISIBLE_COLUMN[] = new Object[]{"projectionPeriod", "tradingPartner", "customerID", Constant.GROUP, Constant.BRAND};
    /**
     * The Constant PVS_COLUMN_HEADER.
     */
    public static final String PV_DISCOUNT_COLUMN_HEADER[] = new String[]{"Projection Period", Constant.TRADING_PARTNER, "Customer ID", Constant.GROUPFCAPS, Constant.BRAND_CAPS};
    /**
     * The Constant PVS_VISIBLE_COLUMN.
     */
    public static final Object PV_PPA_VISIBLE_COLUMN[] = new Object[]{"projectionPeriod", "tradingPartner", "customerID", Constant.GROUP, Constant.BRAND, "ppaCap", "ppaDiscountPer", "ppaDiscountDollar"};
    /**
     * The Constant PVS_COLUMN_HEADER.
     */
    public static final String PV_PPA_COLUMN_HEADER[] = new String[]{"Projection Period", Constant.TRADING_PARTNER, "Customer ID", Constant.GROUPFCAPS, Constant.BRAND_CAPS, "PPA Cap", "PPA Discount %", "PPA Discount $"};
    public static final Object PPA_DETAILS_VISIBLE_COLUMN_LEFT[] = new Object[]{"period","rebateScheduleName"};
    public static final Object PPA_DETAILS_VISIBLE_COLUMN_RIGHT[] = new Object[]{"priceProtectionPriceType", "price", "priceChange", "map",
        "totalDeductions", "units", "deductionPerUnit", "netPrice", "netMAP", "priceProtectionAmountPerUnit", "priceProtectionPercent", "totalPriceProtectionDeduction",
        "nep", "nepFormula", "priceToleranceType", "priceTolerance", "priceToleranceInterval", "priceToleranceFrequency", "maxIncrementalChange",
        "resetEligible", "resetType", "resetDate", "resetInterval", "resetFrequency", "netPriceType", "netPriceTypeFormula"};
    public static final String PPA_DETAILS_VISIBLE_HEADER_LEFT[] = new String[]{Constant.PERIOD,"Rebate Schedule Name"};
    public static final String PPA_DETAILS_VISIBLE_HEADER_RIGHT[] = new String[]{"Price Protection Price Type", "Price", "Price Change", "MAP",
        "Total Deductions", Constant.UNITS_SMALL, "Deduction Per Unit", "Net Price", "Net MAP", "Price Protection Amount Per Unit", "Price Protection %", "Total Price Protection Deduction",
        "NEP", "NEP Formula", "Price Tolerance Type", "Price Tolerance", "Price Tolerance Interval", "Price Tolerance Frequency", "Max Incremental Change",
        "Reset Eligible", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency", "Net Price Type", "Net Price Type Formula"};
    private static boolean colValue;
    /**
     * The col variance.
     */
    private static boolean colVariance;
    /**
     * The col percentage.
     */
    private static boolean colPercentage;
    /**
     * The Var gts.
     */
    private static boolean varGTS;
    /**
     * The Var contractsales.
     */
    private static boolean varContractsales;
    /**
     * The Var contract UNITS_SMALL.
     */
    private static boolean varContractUnits;
    /**
     * The var percentage.
     */
    private static boolean varPercentage;
    /**
     * The var dis amount.
     */
    private static boolean varDisAmount;
    /**
     * The var dis rate.
     */
    private static boolean varDisRate;
    /**
     * The var net SALES_SMALL.
     */
    private static boolean varNetSales;

    private static Map<String, String> columnHeaderMap = Constant.getColumnHeaderMap();
    private static Map<String, List<String>> populateIdentifier = Constant.getPopulateIdentifier();

    /**
     * Gets the dynamic discount.
     *
     * @param actualsOrProjections the actuals or projections
     * @param yearSelection the year selection
     * @param programSelection the program selection
     * @param yearList the year list
     * @param tableColumn the table column
     * @param tableHeader the table header
     * @param count the count
     * @return the dynamic discount
     */
    public List<List> getDynamicDiscount(final Object actualsOrProjections, final String yearSelection, final Object programSelection, final List<String> yearList, final List<Object> tableColumn, final List<String> tableHeader, final int count) {
        final List<List> list = new ArrayList<List>();

        if (Constant.NULL.equals(yearSelection)) {

            if (actualsOrProjections == ACTUALS.getConstant()) {

                for (final String years : yearList) {

                    tableColumn.add(years + "_" + ACTUALS.getConstant() + count);
                    tableHeader.add(years + " " + ACTUALS.getConstant());

                }

            } else if (actualsOrProjections == PROJECTIONS.getConstant()) {
                for (final String years : yearList) {
                    tableColumn.add(years + "_" + PROJECTIONS.getConstant() + count);
                    tableHeader.add(years + " " + PROJECTIONS.getConstant());
                }
            } else if (actualsOrProjections == BOTH.getConstant()) {
                for (final String years : yearList) {

                    tableColumn.add(years + "_" + ACTUALS.getConstant() + count);
                    tableColumn.add(years + "_" + PROJECTIONS.getConstant() + count);

                    tableHeader.add(years + " " + ACTUALS.getConstant());
                    tableHeader.add(years + " " + PROJECTIONS.getConstant());
                }
            }

        } else {
            if (actualsOrProjections == ACTUALS.getConstant()) {
                tableColumn.add(yearSelection + "_" + ACTUALS.getConstant() + count);
                tableHeader.add(yearSelection + " " + ACTUALS.getConstant());
            } else if (actualsOrProjections == PROJECTIONS.getConstant()) {
                tableColumn.add(yearSelection + "_" + PROJECTIONS.getConstant() + count);
                tableHeader.add(yearSelection + " " + PROJECTIONS.getConstant());
            } else if (actualsOrProjections == BOTH.getConstant()) {
                tableColumn.add(yearSelection + "_" + ACTUALS.getConstant() + count);
                tableColumn.add(yearSelection + "_" + PROJECTIONS.getConstant() + count);

                tableHeader.add(yearSelection + " " + ACTUALS.getConstant());
                tableHeader.add(yearSelection + " " + PROJECTIONS.getConstant());
            }
        }

        list.add(tableColumn);
        list.add(tableHeader);

        return list;
    }

    /**
     * To calculate the graph categories based on the frequency.
     *
     * @param frequency To calculate the categories
     * @param ifActuals the if actuals
     * @return A String array containing the categories calculated based on the
     * frequency
     */
    public static String[] getChartCategories(final String frequency, final boolean ifActuals) {
        final List<Integer> projectedYears = calculateYears(ifActuals);
        final List<String> chartCategories = new ArrayList<String>();

        LOGGER.info("Entering getChartCategories method ");

        if (frequency.equalsIgnoreCase(QUARTERLY.getConstant())) {
            for (int i = Constant.ZERO; i < projectedYears.size(); i++) {
                for (int x = Constant.ZERO; x < quarters.length; x++) {
                    chartCategories.add(projectedYears.get(i) + quarters[x]);
                }
            }
        } else if (frequency.equalsIgnoreCase(ANNUAL.getConstant())) {
            for (int i = Constant.ZERO; i < projectedYears.size(); i++) {
                chartCategories.add(String.valueOf(projectedYears.get(i)));
            }
        } else if (frequency.equalsIgnoreCase(SEMI_ANNUAL.getConstant())) {
            for (int i = Constant.ZERO; i < projectedYears.size(); i++) {
                for (int x = Constant.ZERO; x < semiAnnuals.length; x++) {
                    chartCategories.add(projectedYears.get(i) + semiAnnuals[x]);
                }
            }
        } else if (frequency.equalsIgnoreCase(MONTHLY.getConstant())) {
            for (int i = Constant.ZERO; i < projectedYears.size(); i++) {
                for (int x = Constant.ZERO; x < months.length; x++) {
                    chartCategories.add(projectedYears.get(i) + months[x]);
                }
            }
        }
        LOGGER.info("End of getChartCategories method");
        return Arrays.copyOf(chartCategories.toArray(), chartCategories.toArray().length, String[].class);
    }

    /**
     * Gets the current year and calculates current + 3 for projection.
     *
     * @param ifActuals the if actuals
     * @return Integer list containing current and current + 3 years
     */
    public static List<Integer> calculateYears(final boolean ifActuals) {
        final Calendar now = CommonUtils.getCalendar();
        final List<Integer> projectedYears = new ArrayList<Integer>();
        final int currentYear = now.get(Calendar.YEAR);

        LOGGER.info("Entering calculateYears method ");

        if (ifActuals) {
            projectedYears.add(currentYear - Constant.ONE);
            projectedYears.add(currentYear - Constant.TWO);
            projectedYears.add(currentYear - Constant.THREE);
        } else {
            projectedYears.add(currentYear);
            projectedYears.add(currentYear + Constant.ONE);
            projectedYears.add(currentYear + Constant.TWO);
            projectedYears.add(currentYear + Constant.THREE);
        }
        LOGGER.info("End of calculateYears method");
        return projectedYears;
    }

    public static CustomTableHeaderDTO getSalesProjectionLeftTableColumns(Map selection) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();

        tableHeaderDTO.addSingleColumn(Constant.CHECK, " ", Boolean.class);
        tableHeaderDTO.addSingleColumn(Constant.LEVELNAME, "Level Name", String.class);
        tableHeaderDTO.addSingleColumn(Constant.GROUP, Constant.GROUPFCAPS, String.class);
        tableHeaderDTO.addSingleColumn(Constant.BASELINE, "Base Line", String.class);
        tableHeaderDTO.addSingleColumn(Constant.METHODOLOGY, "Methodology", String.class);
        tableHeaderDTO.addDoubleColumn(Constant.CHECKRECORD, " ");
        tableHeaderDTO.addDoubleHeaderMap(Constant.CHECKRECORD, new Object[]{Constant.CHECK});
        tableHeaderDTO.addDoubleColumn("group1", " ");
        tableHeaderDTO.addDoubleHeaderMap("group1", new Object[]{Constant.LEVELNAME, Constant.GROUP, Constant.BASELINE, Constant.METHODOLOGY});

        return tableHeaderDTO;
    }

    public static List getSalesProjectionRightTableColumns(Map selection, SessionDTO session) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        CustomTableHeaderDTO excelDto = new CustomTableHeaderDTO();

        excelDto.addSingleColumn(Constant.LEVELNAME, "Level Name", String.class);
        excelDto.addSingleColumn(Constant.GROUP, Constant.GROUPFCAPS, String.class);
        excelDto.addSingleColumn(Constant.BASELINE, "Base Line", String.class);
        excelDto.addSingleColumn(Constant.METHODOLOGY, "Methodology", String.class);

        return getCalculatedSalesColumns(selection, tableHeaderDTO, excelDto, session);
    }

    public static CustomTableHeaderDTO getDiscountProjectionLeftTableColumns() {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn(Constant.CHECKRECORD, " ", Boolean.class);
        tableHeaderDTO.addSingleColumn(Constant.LEVELNAME, "Level Name", String.class);
        tableHeaderDTO.addDoubleColumn(Constant.LEVELNAME, " ");
        tableHeaderDTO.addDoubleHeaderMap(Constant.LEVELNAME, new Object[]{Constant.LEVELNAME, Constant.CHECKRECORD});
        tableHeaderDTO.addTripleColumn(Constant.LEVELNAME, " ");
        tableHeaderDTO.addTripleHeaderMap(Constant.LEVELNAME, new Object[]{Constant.LEVELNAME});
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getDiscountProjectionLeftTableColumns(String hierarchyIndicator, boolean isView, CustomTableHeaderDTO excelHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        if (!isView) {
            tableHeaderDTO.addSingleColumn(Constant.CHECKRECORD, " ", Boolean.class);
        }
        tableHeaderDTO.addSingleColumn(Constant.LEVELNAME, "Level Name", String.class);
        tableHeaderDTO.addDoubleColumn(Constant.GROUP, " ");

        excelHeader.addSingleColumn(Constant.LEVELNAME, "Level Name", String.class);
        excelHeader.addDoubleColumn(Constant.GROUP, " ");
        if (!Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
            tableHeaderDTO.addSingleColumn(Constant.GROUP, Constant.GROUPFCAPS, String.class);
            tableHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{Constant.LEVELNAME, Constant.CHECKRECORD, Constant.GROUP});
            excelHeader.addSingleColumn(Constant.GROUP, Constant.GROUPFCAPS, String.class);
            excelHeader.addDoubleHeaderMap(Constant.GROUP, new Object[]{Constant.LEVELNAME, Constant.GROUP});
        } else {
            tableHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{Constant.LEVELNAME, Constant.CHECKRECORD});
            excelHeader.addDoubleHeaderMap(Constant.GROUP, new Object[]{Constant.LEVELNAME});
        }

        return tableHeaderDTO;
    }

    public static List<Object> getDiscountProjectionRightTableColumns(ProjectionSelectionDTO projectionSelection) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
        return getCalculatedDiscountProjectionColumns(tableHeaderDTO, excelHeader, projectionSelection);
    }

    public static CustomTableHeaderDTO getProjectionResultsLeftTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object doubleCol = Constant.GROUP;
        Object[] singleCol = {Constant.GROUP};
        tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);

        fullHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        fullHeaderDTO.addDoubleColumn(doubleCol, " ");
        fullHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getProjectionResultsRightTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getCalculatedProjectionColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }

    public static void prepareCommonColumnHeaders(ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projOrder = projSelDTO.getProjectionOrder();
        int projectionOrder = 0;
        if (projOrder.contains(ASCENDING.getConstant())) {
            projectionOrder = 1;
        } else {
            projectionOrder = 2;
        }
        String yearValue = projSelDTO.getYear();
        int year = isInteger(yearValue) ? Integer.valueOf(yearValue) : 0;
        int historyStartIndex = -1;
        int projectionStartIndex = -1;
        int forecastStartIndex = -1;
        int historyEndIndex = -1;
        int projectionEndIndex = -1;
        int forecastEndIndex = -1;
        List<String> periodList = new ArrayList<String>();
        Map<String, String> periodListMap = new HashMap<String, String>();
        int startPr = 1;
        int lastPr = frequencyDivision;
        if (year != 0) {
            if (year == projSelDTO.getHistoryStartYear()) {
                startPr = projSelDTO.getHistoryStartPeriod();
            }
            if (year == projSelDTO.getForecastDTO().getForecastEndYear()) {
                lastPr = projSelDTO.getForecastEndPeriod();
            }

            for (int pr = startPr; pr <= lastPr; pr++) {
                List<String> common = new ArrayList<String>();

                if ("SPR".equals(projSelDTO.getProjTabName())) {
                    common = getCommonColumnHeaderSPR(frequencyDivision, year, pr);
                } else {
                    if (projSelDTO.getFunctionality().equals("Alternate-History") && projSelDTO.isIsSumarry()) {
                        common = getCommonColumnHeaderForAlternateHistory(frequencyDivision, year, pr);
                    } else {

                        common = getCommonColumnHeader(frequencyDivision, year, pr);
                    }
                }
                String commonColumn = common.get(0);
                String commonHeader = common.get(1);
                if ((year > projSelDTO.getHistoryStartYear() && year < projSelDTO.getHistoryEndYear()) || (year == projSelDTO.getHistoryStartYear() && pr >= projSelDTO.getHistoryStartPeriod()) || (year == projSelDTO.getHistoryEndYear() && pr <= projSelDTO.getHistoryEndPeriod())) {
                    if (historyStartIndex == -1) {
                        historyStartIndex = periodList.size();
                    }
                    historyEndIndex = periodList.size();
                }
                if ((year > projSelDTO.getProjectionStartYear() && year < projSelDTO.getForecastDTO().getProjectionEndYear()) || (year == projSelDTO.getProjectionStartYear() && pr >= projSelDTO.getProjectionStartPeriod()) || (year == projSelDTO.getForecastDTO().getProjectionEndYear() && pr <= projSelDTO.getProjectionEndPeriod())) {

                    if (projectionStartIndex == -1) {
                        projectionStartIndex = periodList.size();
                    }
                    projectionEndIndex = periodList.size();
                }
                if ((year > projSelDTO.getForecastDTO().getForecastStartYear() && year < projSelDTO.getForecastDTO().getForecastEndYear()) || (year == projSelDTO.getForecastDTO().getForecastStartYear() && pr >= projSelDTO.getForecastStartPeriod()) || (year == projSelDTO.getForecastDTO().getForecastEndYear() && pr <= projSelDTO.getForecastEndPeriod())) {

                    if (forecastStartIndex == -1) {
                        forecastStartIndex = periodList.size();
                    }
                    forecastEndIndex = periodList.size();
                }
                periodList.add(commonColumn);
                periodListMap.put(commonColumn, commonHeader);
            }
        } else {
            List<String> projectionColumnList = new ArrayList<String>();
            List<String> projectionHeaderList = new ArrayList<String>();

            startPr = projSelDTO.getProjectionStartPeriod() == 0 ? 1 : projSelDTO.getProjectionStartPeriod();

            for (int yr = projSelDTO.getProjectionStartYear(); yr <= projSelDTO.getForecastDTO().getProjectionEndYear(); yr++) {
                if (yr == projSelDTO.getForecastDTO().getProjectionEndYear()) {
                    lastPr = projSelDTO.getProjectionEndPeriod();
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = new ArrayList<String>();

                    if ("SPR".equals(projSelDTO.getProjTabName())) {
                        common = getCommonColumnHeaderSPR(frequencyDivision, yr, pr);
                    } else {
                        if (projSelDTO.getFunctionality().equals("Alternate-History") && projSelDTO.isIsSumarry()) {

                            common = getCommonColumnHeaderForAlternateHistory(frequencyDivision, yr, pr);
                        } else {
                            common = getCommonColumnHeader(frequencyDivision, yr, pr);
                        }
                    }
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    projectionColumnList.add(commonColumn);
                    projectionHeaderList.add(commonHeader);
                }
                startPr = 1;
            }

            startPr = projSelDTO.getHistoryStartPeriod() == 0 ? 1 : projSelDTO.getHistoryStartPeriod();
            lastPr = frequencyDivision;
            historyStartIndex = 0;
            for (int yr = projSelDTO.getHistoryStartYear(); yr <= projSelDTO.getHistoryEndYear(); yr++) {
                if (yr == projSelDTO.getHistoryEndYear()) {
                    lastPr = projSelDTO.getHistoryEndPeriod();
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = new ArrayList<String>();

                    if ("SPR".equals(projSelDTO.getProjTabName())) {
                        common = getCommonColumnHeaderSPR(frequencyDivision, yr, pr);
                    } else {
                        if (projSelDTO.getFunctionality().equals("Alternate-History") && projSelDTO.isIsSumarry()) {
                            common = getCommonColumnHeaderForAlternateHistory(frequencyDivision, yr, pr);
                        } else {
                            common = getCommonColumnHeader(frequencyDivision, yr, pr);
                        }
                    }
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    if (projectionColumnList.contains(commonColumn)) {
                        projectionColumnList.remove(commonColumn);
                        projectionHeaderList.remove(commonHeader);
                        if (projectionStartIndex == -1) {
                            projectionStartIndex = periodList.size();
                        }
                    }
                    periodList.add(commonColumn);
                    periodListMap.put(commonColumn, commonHeader);
                }
                startPr = 1;
            }
            historyEndIndex = periodList.size() - 1;
            for (int i = 0; i < projectionColumnList.size(); i++) {
                String commonColumn = projectionColumnList.get(i);
                String commonHeader = projectionHeaderList.get(i);
                if (projectionStartIndex == -1) {
                    projectionStartIndex = periodList.size();
                }
                periodList.add(commonColumn);
                periodListMap.put(commonColumn, commonHeader);
            }
            projectionEndIndex = periodList.size() - 1;

            startPr = projSelDTO.getForecastStartPeriod() == 0 ? 1 : projSelDTO.getForecastStartPeriod();
            lastPr = frequencyDivision;

            for (int yr = projSelDTO.getForecastDTO().getForecastStartYear(); yr <= projSelDTO.getForecastDTO().getForecastEndYear(); yr++) {
                if (yr == projSelDTO.getForecastDTO().getForecastEndYear()) {
                    lastPr = projSelDTO.getForecastEndPeriod();
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = new ArrayList<String>();

                    if ("SPR".equals(projSelDTO.getProjTabName())) {
                        common = getCommonColumnHeaderSPR(frequencyDivision, yr, pr);
                    } else {
                        if (projSelDTO.getFunctionality().equals("Alternate-History") && projSelDTO.isIsSumarry()) {
                            common = getCommonColumnHeaderForAlternateHistory(frequencyDivision, yr, pr);
                        } else {
                            common = getCommonColumnHeader(frequencyDivision, yr, pr);
                        }
                    }
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    if (forecastStartIndex == -1) {
                        if (periodList.contains(commonColumn)) {
                            forecastStartIndex = periodList.indexOf(commonColumn);
                        } else {
                            forecastStartIndex = periodList.size();
                        }
                    }
                    if (!periodList.contains(commonColumn)) {
                        periodList.add(commonColumn);
                        periodListMap.put(commonColumn, commonHeader);
                    }
                }
                startPr = 1;
            }
            forecastEndIndex = periodList.size() - 1;
        }
        if (projectionOrder != 1) {
            String historyStart = null;
            String projectionStart = null;
            String forecastStart = null;
            String historyEnd = null;
            String projectionEnd = null;
            String forecastEnd = null;

            if (historyStartIndex >= 0) {
                historyStart = periodList.get(historyStartIndex);
            }
            if (projectionStartIndex >= 0) {
                projectionStart = periodList.get(projectionStartIndex);
            }
            if (forecastStartIndex >= 0) {
                forecastStart = periodList.get(forecastStartIndex);
            }
            if (historyEndIndex >= 0) {
                historyEnd = periodList.get(historyEndIndex);
            }
            if (projectionEndIndex >= 0) {
                projectionEnd = periodList.get(projectionEndIndex);
            }
            if (forecastEndIndex >= 0) {
                forecastEnd = periodList.get(forecastEndIndex);
            }

            Collections.reverse(periodList);

            historyStartIndex = -1;
            projectionStartIndex = -1;
            forecastStartIndex = -1;
            historyEndIndex = -1;
            projectionEndIndex = -1;
            forecastEndIndex = -1;

            if (historyEnd != null) {
                historyStartIndex = periodList.indexOf(historyEnd);
            }
            if (projectionEnd != null) {
                projectionStartIndex = periodList.indexOf(projectionEnd);
            }
            if (forecastEnd != null) {
                forecastStartIndex = periodList.indexOf(forecastEnd);
            }
            if (historyStart != null) {
                historyEndIndex = periodList.indexOf(historyStart);
            }
            if (projectionStart != null) {
                projectionEndIndex = periodList.indexOf(projectionStart);
            }
            if (forecastStart != null) {
                forecastEndIndex = periodList.indexOf(forecastStart);
            }
        }
        projSelDTO.setPeriodList(periodList);
        projSelDTO.setPeriodListMap(periodListMap);
        projSelDTO.setHistoryStartIndex(historyStartIndex);
        projSelDTO.setHistoryEndIndex(historyEndIndex);
        projSelDTO.setProjectionStartIndex(projectionStartIndex);
        projSelDTO.setProjectionEndIndex(projectionEndIndex);
        projSelDTO.setForecastStartIndex(forecastStartIndex);
        projSelDTO.setForecastEndIndex(forecastEndIndex);

    }

    public static CustomTableHeaderDTO getCalculatedProjectionColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        List<String> discountNames = new ArrayList<String>(projSelDTO.getDiscountNameList());
        if (projSelDTO.isPpa()) {
            discountNames.add(PPA_DISCOUNT.getConstant());
        }
        String projections = projSelDTO.getActualsOrProjections();
        String pivotView = projSelDTO.getPivotView();
        String frequency = projSelDTO.getFrequency();

        CommonUtils.getHistoryAndProjectionDetails(projSelDTO);

        int historyNum = projSelDTO.getHistoryNum();
        int frequencyDivision = projSelDTO.getFrequencyDivision();

        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endYear = projSelDTO.getEndYear();
        int endPeriod = projSelDTO.getEndPeriod();
        int projectionOrder = 0;
        if (projOrder.contains(ASCENDING.getConstant())) {
            projectionOrder = 1;
        } else {
            projectionOrder = 2;
        }
        tableHeaderDTO.setFrequency(frequency);
        tableHeaderDTO.setFrequencyDivision(frequencyDivision);
        tableHeaderDTO.setHistoryNum(historyNum);
        tableHeaderDTO.setProjectionNum(projectionNum);
        tableHeaderDTO.setCurrentPeriod(currentPeriod);
        tableHeaderDTO.setCurrentYear(currentYear);
        tableHeaderDTO.setStartYear(startYear);
        tableHeaderDTO.setStartPeriod(startPeriod);
        tableHeaderDTO.setEndYear(endYear);
        tableHeaderDTO.setEndPeriod(endPeriod);
        tableHeaderDTO.setProjectionOrder(projectionOrder);

        fullHeaderDTO.setFrequency(frequency);
        fullHeaderDTO.setFrequencyDivision(frequencyDivision);
        fullHeaderDTO.setHistoryNum(historyNum);
        fullHeaderDTO.setProjectionNum(projectionNum);
        fullHeaderDTO.setCurrentPeriod(currentPeriod);
        fullHeaderDTO.setCurrentYear(currentYear);
        fullHeaderDTO.setStartYear(startYear);
        fullHeaderDTO.setStartPeriod(startPeriod);
        fullHeaderDTO.setEndYear(endYear);
        fullHeaderDTO.setEndPeriod(endPeriod);
        fullHeaderDTO.setProjectionOrder(projectionOrder);
        prepareCommonColumnHeaders(projSelDTO);
        if (pivotView.contains(VARIABLE.getConstant())) {

            for (int i = 0; i < 14; i++) {

                String commonColumn = StringUtils.EMPTY;
                String oldCommonColumn = StringUtils.EMPTY;
                String commonHeader = StringUtils.EMPTY;
                if (i == 0) {

                    commonColumn = "efs";
                    commonHeader = "Ex-Factory Sales";
                } else if (i == 1) {
                    commonColumn = "dms";
                    commonHeader = "Demand Sales";
                } else if (i == 2) {
                    commonColumn = "iws";
                    commonHeader = "Inventory Withdrawal Sales";
                } else if (i == 3) {
                    commonColumn = "perOfExfac";
                    commonHeader = "% of Ex-Factory";
                } else if (i == 4) {
                    commonColumn = "perOfDemand";
                    commonHeader = "% of Demand";
                } else if (i == 5) {
                    commonColumn = "perOfInvwithdraw";
                    commonHeader = "% of Inventory Withdrawal";
                } else if (i == 6) {
                    if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant()) || projSelDTO.getSalesOrUnit().equals(SALES.getConstant())) {
                        commonColumn = "conSalesWac";
                        commonHeader = "Contract Sales @ WAC";
                    } else {
                        continue;
                    }
                } else if (i == 7) {
                    if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant()) || projSelDTO.getSalesOrUnit().equals(UNITS.getConstant())) {
                        commonColumn = "unitVol";
                        commonHeader = Constant.UNIT_VOLUME;
                    } else {
                        continue;
                    }
                } else if (i == 8) {
                    commonColumn = Constant.totDisPer;
                    commonHeader = "Total Discount %";
                } else if (i == 9) {
                    commonColumn = "totRPU";
                    commonHeader = Constant.Total_RPU;
                } else if (i == 10) {
                    commonColumn = Constant.totDisDol;
                    commonHeader = "Total Discount $";
                } else if (i == 11) {
                    commonColumn = "netSales";
                    commonHeader = "Net Sales";
                } else if (i == 12) {
                    commonColumn = "cogs";
                    commonHeader = "Cost of Goods Sold (COGS)";
                } else if (i == 13) {
                    commonColumn = "netProfit";
                    commonHeader = "Net Profit";
                }

                oldCommonColumn = commonColumn;
                int j = -1;
                boolean disc = true;
                while (disc) {
                    List<Object> dmap = new ArrayList<Object>();
                    if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        if (i == 3 || i == 4 || i == 5 || i == 10) {
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);
                        } else {
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                        }

                    }
                    Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                    dmap.add(singleColumn);
                    if (i == 3 || i == 4 || i == 5 || i == 10) {
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);
                    } else {
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                    }
                    disc = false;
                    if (!dmap.isEmpty()) {
                        tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    }
                    if (!discountNames.isEmpty() && (i == 8 || i == 9 || i == 10)) {
                        if (discountNames.size() > (j + 1)) {
                            disc = true;
                            j++;
                            commonHeader = discountNames.get(j);
                            commonColumn = oldCommonColumn + commonHeader.replace(" ", StringUtils.EMPTY);
                        }

                    }
                }
            }
        } else {

            List<String> periodList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            for (int i = 0; i < periodList.size(); i++) {
                List<Object> dmap = new ArrayList<Object>();
                String commonColumn = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn);
                boolean historyFlag = false;
                boolean projectionCol = false;
                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    historyFlag = true;

                    if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, ACTUALS.getConstant());
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                    }
                }
                if (i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex()) {
                    projectionCol = true;
                } else if (historyFlag && (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTIONS.getConstant()))) {
                    projectionCol = true;
                }

                if (i >= projSelDTO.getForecastStartIndex() && i <= projSelDTO.getForecastEndIndex()) {
                    projectionCol = true;
                }

                if (projectionCol) {
                    Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTIONS.getConstant());
                    fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
            }

        }

        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    public static List<String> getCommonColumnHeader(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<String>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == 4) {
            commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = Constant.Q + period + " " + year;
        } else if (frequencyDivision == 2) {
            commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = Constant.S + period + " " + year;
        } else if (frequencyDivision == 12) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    public static List<String> getCommonColumnHeaderSPR(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<String>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == 4) {
            commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = period + Constant.Q + year;
        } else if (frequencyDivision == 2) {
            commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = period + Constant.S + year;
        } else if (frequencyDivision == 12) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    public static CustomTableHeaderDTO getCalculatedColumns(Map selection, CustomTableHeaderDTO tableHeaderDTO) {
        String freq = selection.get(Constant.FREQUENCY).toString();
        String hist = selection.get(Constant.HISTORY).toString();
        String projFreq = selection.get("projectFrequency").toString();
        String order = selection.get(Constant.ORDER).toString();
        String projection = selection.get("projection").toString();
        Calendar ob = Calendar.getInstance();
        ob.set(2015, 8, 25);
        int curMonth = ob.get(Calendar.MONTH);
        int curDate = ob.get(Calendar.DATE);
        int curYear = ob.get(Calendar.YEAR);
        int current = 1;
        int frequency = 0;
        int projectFrequency = 0;
        int division = 1;
        if (freq.contains(Constant.QUARTERLY)) {
            current = (curMonth / 3);
            division = 4;
            try {
                frequency = Integer.valueOf(hist.replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                projectFrequency = Integer.valueOf(projFreq.replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        } else if (freq.contains(Constant.SEMI_ANNUALLY)) {
            current = (curMonth / 6);
            division = 2;
            try {
                frequency = Integer.valueOf(hist.replace("Semi-Annual", StringUtils.EMPTY).trim());
                projectFrequency = Integer.valueOf(projFreq.replace("Semi-Annual", StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        } else if (freq.contains(MONTHLY.getConstant())) {
            current = curMonth;
            division = 12;
            try {
                frequency = Integer.valueOf(hist.replace("Month", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                projectFrequency = Integer.valueOf(projFreq.replace("Month", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        } else if (freq.contains(Constant.ANNUALLY)) {
            current = curYear;
            division = 1;
            try {
                frequency = Integer.valueOf(hist.replace(Constant.YEAR, StringUtils.EMPTY).trim());
                projectFrequency = Integer.valueOf(projFreq.replace(Constant.YEAR, StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        }
        projectFrequency = projectFrequency + 1;
        int pastYear = curYear;

        int startFreq = current + 1;

        int tempFreq = frequency - current;
        if (tempFreq > 0) {
            pastYear = pastYear - tempFreq / division;
            startFreq = 1;
            if (tempFreq % division > 0) {
                pastYear = pastYear - 1;
                startFreq = division - (tempFreq % division) + 1;
            }
        } else {
            startFreq = startFreq - frequency;
        }

        int squr = startFreq;
        int syear = pastYear;
        if (freq.contains(Constant.ANNUALLY) && !freq.contains(Constant.SEMI_ANNUALLY)) {
            syear = current - frequency;
        }
        for (int i = 0; i < frequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = StringUtils.EMPTY;
            String commonHeader = StringUtils.EMPTY;
            if (freq.contains(Constant.QUARTERLY)) {
                commonColumn = Constant.Q + squr + StringUtils.EMPTY + syear;
                commonHeader = Constant.Q + squr + " " + syear;
            } else if (freq.contains(Constant.SEMI_ANNUALLY)) {
                commonColumn = Constant.S + squr + StringUtils.EMPTY + syear;
                commonHeader = Constant.S + squr + " " + syear;
            } else if (freq.contains(Constant.ANNUALLY)) {
                commonColumn = StringUtils.EMPTY + syear;
                commonHeader = StringUtils.EMPTY + syear;
            } else if (freq.contains(Constant.MONTHLY)) {
                String monthName = getMonthForInt(squr - 1);
                commonColumn = monthName + syear;
                commonHeader = monthName + " " + syear;
            }
            if (selection.containsKey(Constant.SALES)) {

                if (projection.contains(Constant.BOTH_SMALL) || projection.contains("actuals")) {
                    dmap.add(commonColumn + Constant.ACTUALSALES);
                    tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACTUALSALES, Constant.ACTUAL_SALES, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(commonColumn + Constant.ACTUALSALES, Constant.ACTUAL_SALES);
                }
                if (projection.contains(Constant.BOTH_SMALL) || projection.contains("projections")) {
                    dmap.add(commonColumn + "ProjectedSales");
                    tableHeaderDTO.addSingleColumn(commonColumn + "ProjectedSales", "Projected Sales", String.class);
                    tableHeaderDTO.addSingleHistoryColumn(commonColumn + "ProjectedSales", "Projected Sales");
                }
            }
            if (selection.containsKey(Constant.UNITS)) {
                if (projection.contains(Constant.BOTH_SMALL) || projection.contains("actuals")) {
                    dmap.add(commonColumn + Constant.ACTUALUNITS);
                    tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACTUALUNITS, "Actual Units", String.class);
                    tableHeaderDTO.addSingleHistoryColumn(commonColumn + Constant.ACTUALUNITS, "Actual Units");
                }
                if (projection.contains(Constant.BOTH_SMALL) || projection.contains("projections")) {
                    dmap.add(commonColumn + "ProjectedUnits");
                    tableHeaderDTO.addSingleColumn(commonColumn + "ProjectedUnits", "Projected Units", String.class);
                    tableHeaderDTO.addSingleHistoryColumn(commonColumn + "ProjectedUnits", "Projected Units");
                }
            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleHistoryColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHistoryHeaderMap(commonColumn, dmap.toArray());
            }
            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
        }
        squr = current + 1;
        syear = curYear;
        for (int i = 0; i < projectFrequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = StringUtils.EMPTY;
            String commonHeader = StringUtils.EMPTY;
            if (freq.contains(Constant.QUARTERLY)) {
                commonColumn = Constant.Q + squr + StringUtils.EMPTY + syear;
                commonHeader = Constant.Q + squr + " " + syear;
            } else if (freq.contains(Constant.SEMI_ANNUALLY)) {
                commonColumn = Constant.S + squr + StringUtils.EMPTY + syear;
                commonHeader = Constant.S + squr + " " + syear;
            } else if (freq.contains(Constant.ANNUALLY)) {
                commonColumn = StringUtils.EMPTY + syear;
                commonHeader = StringUtils.EMPTY + syear;
            } else if (freq.contains(MONTHLY.getConstant())) {
                String monthName = getMonthForInt(squr - 1);
                commonColumn = monthName + syear;
                commonHeader = monthName + " " + syear;
            }
            if (selection.containsKey(Constant.SALES)) {
                dmap.add(commonColumn + "ProjectedSales");
                tableHeaderDTO.addSingleColumn(commonColumn + "ProjectedSales", "Projected Sales", String.class);
                tableHeaderDTO.addSingleProjectedColumn(commonColumn + "ProjectedSales", "Projected Sales");
            }
            if (selection.containsKey(Constant.UNITS)) {
                dmap.add(commonColumn + "ProjectedUnits");
                tableHeaderDTO.addSingleColumn(commonColumn + "ProjectedUnits", "Projected Units", String.class);
                tableHeaderDTO.addSingleProjectedColumn(commonColumn + "ProjectedUnits", "Projected Units");
            }
            if (selection.containsKey("product")) {
                dmap.add(commonColumn + "ProductGrowth");
                tableHeaderDTO.addSingleColumn(commonColumn + "ProductGrowth", Constant.PRODUCT_GROWTH, String.class);
                tableHeaderDTO.addSingleProjectedColumn(commonColumn + "ProductGrowth", Constant.PRODUCT_GROWTH);
            }
            if (selection.containsKey("account")) {
                dmap.add(commonColumn + "AccountGrowth");
                tableHeaderDTO.addSingleColumn(commonColumn + "AccountGrowth", Constant.ACCOUNT_GROWTH, String.class);
                tableHeaderDTO.addSingleProjectedColumn(commonColumn + "AccountGrowth", Constant.ACCOUNT_GROWTH);
            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader + " Results");
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader + " Results");
                tableHeaderDTO.addDoubleProjectedHeaderMap(commonColumn, dmap.toArray());
            }
            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
        }
        return tableHeaderDTO;
    }

    public static List<Object> getCalculatedDiscountProjectionColumns(CustomTableHeaderDTO tableHeader, CustomTableHeaderDTO excelHeader, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("Entering getCalculatedDiscountProjectionColumns ");
        try {
            List<String> discountNames = projSelDTO.getDiscountProgramsList();
            String actualsOrProjections = projSelDTO.getActualsOrProjections();

            CommonUtils.getHistoryAndProjectionDetails(projSelDTO);
            prepareCommonColumnHeaders(projSelDTO);
            List<String> columnsList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();

            if (discountNames.isEmpty()) {
                discountNames.add("All Discount");
            }

            for (String discountName : discountNames) {
                String discountColumnName = discountName.replaceAll(" ", StringUtils.EMPTY);
                List<Object> tmap = new ArrayList<Object>();

                for (int i = 0; i < columnsList.size(); i++) {
                    List<Object> dmap = new ArrayList<Object>();
                    String column = columnsList.get(i);
                    String commonColumn = discountColumnName + column;
                    String commonHeader = periodListMap.get(column);
                    boolean historyFlag = false;
                    boolean historyActualFlag = false;
                    boolean projectionFlag = false;
                    boolean projectionCol = false;
                    boolean forecastFlag = false;
                    Object singleColumn = commonColumn;
                    Object actualRPColumn = commonColumn;
                    Object prjRPColumn = commonColumn;
                    Object actAmtColumn = commonColumn;
                    Object prjAmtColumn = commonColumn;
                    Object growthColumn = commonColumn;

                    if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                        historyFlag = true;
                        if (actualsOrProjections.contains(Constant.BOTH) || actualsOrProjections.contains(ACTUALS.getConstant())) {
                            historyActualFlag = true;
                            singleColumn = commonColumn + "ActualRate";
                            actualRPColumn = commonColumn + Constant.ActualRPU;
                            prjRPColumn = commonColumn + Constant.ProjectedRPU;
                            actAmtColumn = commonColumn + "ActualAmount";
                            prjAmtColumn = commonColumn + "ProjectedAmount";

                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                                tableHeader.addSingleColumn(singleColumn, "Actual Rate", String.class);
                                excelHeader.addSingleColumn(singleColumn, " Actual Rate", String.class);
                                dmap.add(singleColumn);
                            }
                            if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                                tableHeader.addSingleColumn(actualRPColumn, "Actual RPU", String.class);
                                excelHeader.addSingleColumn(actualRPColumn, "Actual RPU", String.class);
                                dmap.add(actualRPColumn);
                            }
                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                                tableHeader.addSingleColumn(actAmtColumn, "Actual Amount", String.class);
                                excelHeader.addSingleColumn(actAmtColumn, "Actual Amount", String.class);
                                dmap.add(actAmtColumn);
                            }

                        }
                    }

                    if (i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex()) {
                        projectionFlag = true;
                        projectionCol = true;
                        if (historyActualFlag) {

                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                                tableHeader.addSingleHistoryColumn(singleColumn, "Actual Rate");

                            }

                        }
                    } else if (historyFlag && (actualsOrProjections.contains(Constant.BOTH) || actualsOrProjections.contains(PROJECTIONS.getConstant()))) {
                        projectionCol = true;
                    }

                    if (i >= projSelDTO.getForecastStartIndex() && i <= projSelDTO.getForecastEndIndex()) {
                        forecastFlag = true;
                        projectionCol = true;
                    }

                    if (projectionCol) {
                        singleColumn = commonColumn + "ProjectedRate";
                        prjRPColumn = commonColumn + Constant.ProjectedRPU;
                        prjAmtColumn = commonColumn + "ProjectedAmount";
                        growthColumn = commonColumn + Constant.GROWTH;

                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                            tableHeader.addSingleColumn(singleColumn, "Projected Rate", String.class);
                            excelHeader.addSingleColumn(singleColumn, "Projected Rate", String.class);
                            dmap.add(singleColumn);
                        }
                        if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                            tableHeader.addSingleColumn(prjRPColumn, "Projected RPU", String.class);
                            excelHeader.addSingleColumn(prjRPColumn, "Projected RPU", String.class);

                            dmap.add(prjRPColumn);
                        }
                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                            tableHeader.addSingleColumn(prjAmtColumn, "Projected Amount", String.class);
                            excelHeader.addSingleColumn(prjAmtColumn, "Projected Amount", String.class);
                            dmap.add(prjAmtColumn);
                        }

                        if (projSelDTO.getdPVariablesList().contains(GROWTH.getConstant())) {
                            tableHeader.addSingleColumn(growthColumn, Constant.GROWTH, String.class);
                            excelHeader.addSingleColumn(growthColumn, Constant.GROWTH, String.class);
                            dmap.add(growthColumn);
                        }

                        if (historyFlag) {
                            tableHeader.addSingleHistoryColumn(singleColumn, "Projected Rate");

                        }
                        if (projectionFlag) {
                            tableHeader.addSingleProjectedColumn(singleColumn, "Projected Rate");

                        }
                        if (forecastFlag) {
                            tableHeader.addSingleForecastColumn(singleColumn, "Projected Rate");
                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                                tableHeader.addSingleHistoryColumn(singleColumn, "Projected Rate");
                            }
                        }
                        if (projectionFlag) {
                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                                tableHeader.addSingleProjectedColumn(singleColumn, "Projected Rate");
                            }
                            if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                                tableHeader.addSingleProjectedColumn(prjRPColumn, "Projected RPU");
                            }
                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                                tableHeader.addSingleProjectedColumn(prjAmtColumn, "Projected Amount");
                            }
                            if (projSelDTO.getdPVariablesList().contains(GROWTH.getConstant())) {
                                tableHeader.addSingleProjectedColumn(growthColumn, Constant.GROWTH);
                            }
                        }
                        if (forecastFlag) {
                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                                tableHeader.addSingleForecastColumn(singleColumn, "Projected Rate");
                            }
                            if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                                tableHeader.addSingleForecastColumn(prjRPColumn, "Projected RPU");
                            }
                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                                tableHeader.addSingleForecastColumn(prjAmtColumn, "Projected Amount");
                            }
                            if (projSelDTO.getdPVariablesList().contains(GROWTH.getConstant())) {
                                tableHeader.addSingleForecastColumn(growthColumn, Constant.GROWTH);
                            }
                        }
                    }
                    if (!dmap.isEmpty()) {
                        tmap.add(commonColumn);
                        tableHeader.addDoubleColumn(commonColumn, commonHeader);
                        tableHeader.addDoubleHeaderMap(commonColumn, dmap.toArray());

                        excelHeader.addDoubleColumn(commonColumn, discountName + " " + commonHeader);

                        excelHeader.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        if (historyFlag) {
                            tableHeader.addDoubleHistoryColumn(commonColumn, commonHeader);
                            tableHeader.addDoubleHistoryHeaderMap(commonColumn, dmap.toArray());
                        }
                        if (projectionFlag) {
                            tableHeader.addDoubleProjectedColumn(commonColumn, commonHeader);
                            tableHeader.addDoubleProjectedHeaderMap(commonColumn, dmap.toArray());
                        }
                        if (forecastFlag) {
                            tableHeader.addDoubleForecastColumn(commonColumn, commonHeader);
                            tableHeader.addDoubleForecastHeaderMap(commonColumn, dmap.toArray());
                        }

                    }
                }

                if (!tmap.isEmpty()) {
                    tableHeader.addTripleColumn(discountColumnName, discountName);
                    tableHeader.addTripleHeaderMap(discountColumnName, tmap.toArray());
                    excelHeader.addTripleColumn(discountColumnName, discountName);
                    excelHeader.addTripleHeaderMap(discountColumnName, tmap.toArray());
                }

            }

        } catch (Exception e) {

        }
        LOGGER.info("Ending getCalculatedDiscountProjectionColumns ");
        List<Object> headerContents = new ArrayList<Object>();
        headerContents.add(tableHeader);
        headerContents.add(excelHeader);
        return headerContents;
    }

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    public static CustomTableHeaderDTO getPPAProjectionLeftTableColumns(CustomTableHeaderDTO fullHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn(Constant.CHECK_RECORD + ".0", Constant.SPACE, Boolean.class);
        tableHeaderDTO.addSingleColumn(Constant.LEVELNAME, "Level Name", String.class);
        tableHeaderDTO.addSingleColumn(Constant.GROUP, Constant.GROUPFCAPS, String.class);
        tableHeaderDTO.addSingleColumn(Constant.PRICEPROTECTIONSTATUS, "Price Protection Status", String.class);

        tableHeaderDTO.addSingleColumn(Constant.PRICEPROTECTIONSTARTDATE, "Price Protection Start Date", String.class);
        tableHeaderDTO.addSingleColumn(Constant.PRICEPROTECTIONENDDATE, "Price Protection End Date", String.class);
        tableHeaderDTO.addDoubleColumn("group1", " ");
        tableHeaderDTO.addDoubleHeaderMap("group1", new Object[]{Constant.CHECK_RECORD + ".0", Constant.LEVELNAME, Constant.GROUP, "priceProtectionStatus", "priceProtectionStartDate", "priceProtectionEndDate"});
        fullHeader.addSingleColumn(Constant.LEVELNAME, "Level Name", String.class);
        fullHeader.addSingleColumn(Constant.GROUP, Constant.GROUPFCAPS, String.class);

        fullHeader.addSingleColumn(Constant.PRICEPROTECTIONSTATUS1, "Price Protection Status", String.class);

        fullHeader.addSingleColumn(Constant.PRICEPROTECTIONSTARTDATE, "Price Protection Start Date", String.class);
        fullHeader.addSingleColumn(Constant.PRICEPROTECTIONENDDATE, "Price Protection End Date", String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getPPAProjectionRightTableColumns(ProjectionSelectionDTO selection, CustomTableHeaderDTO fullHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();

        return getCalculatedPPAProjectionColumns(tableHeaderDTO, selection, fullHeader);
    }

    public static CustomTableHeaderDTO getPPAOnLoadRightColumns(ProjectionSelectionDTO selection, CustomTableHeaderDTO fullHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("defaultColumn", StringUtils.EMPTY, String.class);
        tableHeaderDTO.addDoubleColumn("group1", " ");
        tableHeaderDTO.addDoubleHeaderMap("group1", new Object[]{"defaultColumn"});
        fullHeader.addSingleColumn("defaultColumn", StringUtils.EMPTY, String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getCalculatedPPAProjectionResultsColumns(ProjectionSelectionDTO selection, CustomTableHeaderDTO fullHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        String projection = selection.getActualsOrProjections();
        String pivotView = selection.getPivotView();
        CommonUtils.getHistoryAndProjectionDetails(selection);
        prepareCommonColumnHeaders(selection);

        if (pivotView.toLowerCase().contains("variable")) {

            for (int i = 0; i < 4; i++) {
                String commonColumn = StringUtils.EMPTY;

                String commonHeader = StringUtils.EMPTY;

                if (i == 0) {
                    commonColumn = "discountPerUnit";
                    commonHeader = "Discount $ Per Unit";
                } else if (i == 1) {
                    commonColumn = "discountPercent";
                    commonHeader = CommonUtils.VAR_DIS_RATE;
                } else if (i == 2) {
                    commonColumn = "unitVolume";
                    commonHeader = Constant.UNIT_VOLUME;
                } else if (i == 3) {
                    commonColumn = "totalDiscount";
                    commonHeader = "Total Discount Amount";
                }

                int j = -1;
                boolean disc = true;
                while (disc) {

                    List<Object> dmap = new ArrayList<Object>();
                    if (projection.contains("actuals") || projection.contains(Constant.ACTUALS)) {
                        dmap.add(commonColumn + Constant.ACTUALS);
                        tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACTUALS, Constant.ACTUALS, String.class);
                        fullHeader.addSingleColumn(commonColumn + Constant.ACTUALS, commonColumn + Constant.SPACE + Constant.ACTUALS, String.class);

                    }
                    if (projection.contains("projections") || projection.contains(Constant.PROJECTIONS)) {
                        dmap.add(commonColumn + Constant.PROJECTIONS);
                        tableHeaderDTO.addSingleColumn(commonColumn + Constant.PROJECTIONS, Constant.PROJECTIONS, String.class);
                        fullHeader.addSingleColumn(commonColumn + Constant.PROJECTIONS, commonColumn + Constant.SPACE + Constant.ACTUALS, String.class);

                    }
                    if (projection.contains(Constant.BOTH_SMALL) || projection.contains(Constant.BOTH)) {
                        dmap.add(commonColumn + Constant.ACTUALS);
                        tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACTUALS, Constant.ACTUALS, String.class);
                        fullHeader.addSingleColumn(commonColumn + Constant.ACTUALS, commonColumn + Constant.SPACE + Constant.ACTUALS, String.class);
                        dmap.add(commonColumn + Constant.PROJECTIONS);
                        tableHeaderDTO.addSingleColumn(commonColumn + Constant.PROJECTIONS, Constant.PROJECTIONS, String.class);
                        fullHeader.addSingleColumn(commonColumn + Constant.PROJECTIONS, commonColumn + Constant.SPACE + Constant.PROJECTIONS, String.class);
                    }
                    disc = false;
                    if (!dmap.isEmpty() && dmap != null) {
                        tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    }

                }
            }

        } else {

            List<String> periodList = selection.getPeriodList();

            Map<String, String> periodListMap = selection.getPeriodListMap();
            for (int i = 0; i < periodList.size(); i++) {
                List<Object> dmap = new ArrayList<Object>();
                String commonColumn = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn);
                boolean historyFlag = false;
                boolean projectionCol = false;
                if (i >= selection.getHistoryStartIndex() && i <= selection.getHistoryEndIndex()) {
                    historyFlag = true;

                    if (projection.contains(BOTH.getConstant()) || projection.contains(ACTUALS.getConstant())) {
                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                        fullHeader.addSingleColumn(singleColumn, commonHeader + Constant.SPACE + ACTUALS.getConstant(), String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, ACTUALS.getConstant());
                    }
                }
                if (i >= selection.getProjectionStartIndex() && i <= selection.getProjectionEndIndex()) {
                    projectionCol = true;
                } else if (historyFlag && (projection.contains(BOTH.getConstant()) || projection.contains(PROJECTIONS.getConstant()))) {
                    projectionCol = true;
                }

                if (i >= selection.getForecastStartIndex() && i <= selection.getForecastEndIndex()) {
                    projectionCol = true;
                }

                if (projectionCol) {
                    Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                    fullHeader.addSingleColumn(singleColumn, commonHeader + Constant.SPACE + PROJECTIONS.getConstant(), String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTIONS.getConstant());
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                }
            }
            selection.setColumns(CommonUtils.objectListToStringList(tableHeaderDTO.getSingleColumns()));
        }

        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getCalculatedPPAProjectionColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO selection, CustomTableHeaderDTO fullHeader) {
        CommonUtils.getHistoryAndProjectionDetails(selection);
        prepareCommonColumnHeaders(selection);
        List<String> periodList = selection.getPeriodList();

        Map<String, String> periodListMap = selection.getPeriodListMap();
        int start = 1;
        for (int i = 0; i < periodList.size(); i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = periodList.get(i);
            String commonHeader = periodListMap.get(commonColumn);
            List<String> columns = selection.getPpaSelectedVariables();

            if ((i >= selection.getProjectionStartIndex() && i <= selection.getProjectionEndIndex()) || (i >= selection.getForecastStartIndex() && i <= selection.getForecastEndIndex())) {
                for (String column : columns) {
                    String singleColumn1 = commonColumn + column + "." + start;
                    String excelsingleColumn = commonColumn + column + "." + (start - 1);
                    start++;
                    dmap.add(singleColumn1);
                    if (columnHeaderMap.containsKey(column) && (populateIdentifier.get(Constant.DDLB_FIELD).contains(columnHeaderMap.get(column))
                            || Constant.PRICE_PROTECTION_STATUS.equals(columnHeaderMap.get(column)))) {
                        tableHeaderDTO.addSingleColumn(singleColumn1, columnHeaderMap.get(column), Object.class);
                        fullHeader.addSingleColumn(excelsingleColumn, commonHeader + Constant.SPACE + columnHeaderMap.get(column), Object.class);
                    } else if ((Constant.PRICE_PROTECTION_START_DATE.equals(columnHeaderMap.get(column)) || Constant.PRICE_PROTECTION_END_DATE.equals(columnHeaderMap.get(column))
                            || populateIdentifier.get(Constant.DATE_FEILD).contains(columnHeaderMap.get(column)))) {

                        tableHeaderDTO.addSingleColumn(singleColumn1, columnHeaderMap.get(column), Date.class);
                        fullHeader.addSingleColumn(excelsingleColumn, commonHeader + Constant.SPACE + columnHeaderMap.get(column), Date.class);
                    } else {
                        tableHeaderDTO.addSingleColumn(singleColumn1, columnHeaderMap.get(column), String.class);
                        fullHeader.addSingleColumn(excelsingleColumn, commonHeader + Constant.SPACE + columnHeaderMap.get(column), String.class);
                    }

                    tableHeaderDTO.addSingleProjectedColumn(singleColumn1, columnHeaderMap.get(column));

                }

            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleProjectedHeaderMap(commonColumn, dmap.toArray());
            }
        }

        return tableHeaderDTO;
    }

    public static List getCalculatedSalesColumns(Map selection, CustomTableHeaderDTO tableHeaderDTO, CustomTableHeaderDTO excelDto, SessionDTO session) {
        ForecastDTO forecastDTO = session.getForecastDTO();

        Map<Object, Object[]> reProjectedColumn = new HashMap<Object, Object[]>();
        Map<Object, Object[]> doubleHeaderHistoryMap = new HashMap<Object, Object[]>();
        List<String> totalProjected = new ArrayList<String>();
        SalesProjectionLogic salesLogic = new SalesProjectionLogic();
        String hist = selection.get(Constant.HISTORY).toString();
        String projFreq = selection.get("projectFrequency").toString();

        String projection = selection.get("projection").toString();

        String salesVar = (String) selection.get(Constant.SALES);
        String unitsVar = (String) selection.get(Constant.UNITS);
        String pGrowthVar = (String) selection.get(Constant.P_Growth);
        String aGrowthVar = (String) selection.get(Constant.A_Growth);

        int curMonth = forecastDTO.getForecastStartMonth() - 1;

        int curYear = forecastDTO.getForecastStartYear();
        boolean reprojectionAllowed = false;
        boolean projAllowed = true;

        int current = (curMonth / 3);
        int frequency = 0;
        int projectFrequency = 0;
        int division = 4;

        try {
            frequency = Integer.valueOf(hist.replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
            projectFrequency = Integer.valueOf(projFreq);
        } catch (NumberFormatException e) {
        }

        projectFrequency = projectFrequency + 1;

        int pastYear = curYear;

        int startFreq = current + 1;

        int tempFreq = frequency - current;
        if (tempFreq > 0) {
            pastYear = pastYear - tempFreq / division;
            startFreq = 1;
            if (tempFreq % division > 0) {
                pastYear = pastYear - 1;
                startFreq = division - (tempFreq % division) + 1;
            }
        } else {
            startFreq = startFreq - frequency;
        }

        int squr = startFreq;
        int syear = pastYear;

        int reprojtionStartQuator = salesLogic.getQuator(forecastDTO.getProjectionStartMonth());
        int reprojtionStartYear = forecastDTO.getProjectionStartYear();
        int reprojtionEndQuator = salesLogic.getQuator(forecastDTO.getProjectionEndMonth());
        int reprojtionEndYear = forecastDTO.getProjectionEndYear();

        for (int i = 0; i < frequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            List<Object> historyObj = new ArrayList<Object>();
            List<Object> projectionObj = new ArrayList<Object>();
            String commonColumn = Constant.Q_SMALL + squr + "-" + syear;
            String commonHeader = Constant.Q + squr + " " + syear;

            if (syear >= reprojtionStartYear && syear <= reprojtionEndYear) {

                if (syear == reprojtionStartYear) {
                    if (squr >= reprojtionStartQuator) {
                        reprojectionAllowed = true;

                    } else {

                        reprojectionAllowed = false;
                    }
                } else if (syear == reprojtionEndYear) {
                    if (squr <= reprojtionEndQuator) {

                        reprojectionAllowed = true;
                    } else {

                        reprojectionAllowed = false;
                    }
                } else {

                    reprojectionAllowed = true;
                }

            }

            if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                if (salesVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ActualSales";
                    dmap.add(singleColumn);
                    historyObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACTUAL_SALES, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.ACTUAL_SALES);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Actual Sales", String.class);
                }
            }
            if (!reprojectionAllowed) {
                if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                    if (salesVar.equals(Constant.TRUE)) {
                        String singleColumn = commonColumn + "-HistoryProjectedSales";
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projected Sales", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, "Projected Sales");
                        excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Sales", String.class);
                    }
                }
            } else {
                if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                    if (salesVar.equals(Constant.TRUE)) {
                        String singleColumn = commonColumn + "-ProjectedSales";
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        projectionObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projected Sales", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, "Projected Sales");
                        excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Sales", String.class);
                    }

                }
            }
            if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {

                if (unitsVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ActualUnits";
                    dmap.add(singleColumn);
                    historyObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Actual Units", String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, "Actual Units");
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Actual Units", String.class);
                }
            }
            if (!reprojectionAllowed) {
                if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                    if (unitsVar.equals(Constant.TRUE)) {
                        String singleColumn = commonColumn + "-HistoryProjectedUnits";
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projected Units", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, "Projected Units");
                        excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Units", String.class);
                    }

                }

            } else {
                if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                    if (unitsVar.equals(Constant.TRUE)) {
                        String singleColumn = commonColumn + "-ProjectedUnits";
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        projectionObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projected Units", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, "Projected Units");
                        excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Units", String.class);
                    }

                }

                if (pGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProductGrowth";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.PRODUCT_GROWTH, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.PRODUCT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Product Growth", String.class);
                }

                if (aGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-AccountGrowth";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACCOUNT_GROWTH, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.ACCOUNT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Account Growth", String.class);
                }
                totalProjected.add(commonHeader);
            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleHistoryColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHistoryHeaderMap(commonColumn, dmap.toArray());
                doubleHeaderHistoryMap.put(commonColumn, historyObj.toArray());
                reProjectedColumn.put(commonColumn, projectionObj.toArray());
            }

            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
        }
        squr = current + 1;
        syear = curYear;
        for (int i = 0; i < projectFrequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            List<Object> projectionObj = new ArrayList<Object>();
            String commonColumn = StringUtils.EMPTY;
            String commonHeader = StringUtils.EMPTY;
            commonColumn = Constant.Q_SMALL + squr + "-" + syear;
            commonHeader = Constant.Q + squr + " " + syear;

            if (syear >= reprojtionStartYear && syear <= reprojtionEndYear) {

                if (syear == reprojtionStartYear) {
                    if (squr >= reprojtionStartQuator) {
                        projAllowed = true;

                    } else {

                        projAllowed = false;
                    }
                } else if (syear == reprojtionEndYear) {
                    if (squr <= reprojtionEndQuator) {

                        projAllowed = true;
                    } else {

                        projAllowed = false;
                    }
                } else {

                    projAllowed = true;
                }

            }

            if (projAllowed) {

                if (salesVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProjectedSales";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Projected Sales", String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, "Projected Sales");
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Sales", String.class);
                }
                if (unitsVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProjectedUnits";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Projected Units", String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, "Projected Units");
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Units", String.class);
                }
                if (pGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProductGrowth";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.PRODUCT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.PRODUCT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Product Growth", String.class);
                }
                if (aGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-AccountGrowth";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACCOUNT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.ACCOUNT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Account Growth", String.class);
                }

                reprojtionStartQuator++;
                if (reprojtionStartQuator > division) {
                    reprojtionStartQuator = 1;
                }

                totalProjected.add(commonHeader);
            }

            if (!projAllowed) {

                if (salesVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProjectedSalesDis";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Projected Sales", String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, "Projected Sales");
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Sales", String.class);
                }
                if (unitsVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProjectedUnitsDis";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Projected Units", String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, "Projected Units");
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Projected Units", String.class);
                }
                if (pGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProductGrowthDis";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.PRODUCT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.PRODUCT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Product Growth", String.class);
                }
                if (aGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-AccountGrowthDis";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACCOUNT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.ACCOUNT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Account Growth", String.class);
                }

            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleProjectedHeaderMap(commonColumn, dmap.toArray());
                reProjectedColumn.put(commonColumn, projectionObj.toArray());

            }
            if (forecastDTO.getForecastEndDate().after(forecastDTO.getProjectionEndDate()) || forecastDTO.getForecastEndDate().equals(forecastDTO.getProjectionEndDate())) {

                if (salesLogic.getQuator(forecastDTO.getForecastEndMonth()) == squr && forecastDTO.getForecastEndYear() == syear) {
                    break;
                }
            }
            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
            totalProjected.add(commonHeader);

        }

        List headerList = new ArrayList();
        headerList.add(tableHeaderDTO);
        headerList.add(excelDto);
        headerList.add(totalProjected);
        headerList.add(doubleHeaderHistoryMap);

        return headerList;
    }

    public static CustomTableHeaderDTO getPPAProjectionResultsLeftTableColumns(CustomTableHeaderDTO fullHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();

        tableHeaderDTO.addSingleColumn(Constant.GROUP, " ", String.class);
        fullHeader.addSingleColumn(Constant.GROUP, " ", String.class);
        return tableHeaderDTO;
    }

    /**
     * getVarianceLeftTableColumns
     *
     * @return
     */
    public static CustomTableHeaderDTO getVarianceLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object doubleCol = Constant.GROUP;
        Object[] singleCol = {Constant.GROUP};
        tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);

        fullHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        fullHeaderDTO.addDoubleColumn(doubleCol, " ");
        fullHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        return tableHeaderDTO;
    }

    /**
     * getVarianceRightTableColumns
     *
     * @param selection
     * @return
     */
    public static List<Object> getVarianceRightTableColumns(PVSelectionDTO pvsdto, CustomTableHeaderDTO fullHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getProjectionVarianceColumns(tableHeaderDTO, pvsdto, fullHeader);
    }

    /**
     * To get Frequency Division, Current period, Current year, History Details
     * (Start year, start period and start month) and Projection Details (End
     * year, End period and End month)
     *
     * @param projSelDTO
     * @return
     */
    static ProjectionSelectionDTO getHistoryAndProjectionDetails(ProjectionSelectionDTO projSelDTO) {
        String frequency = projSelDTO.getFrequency();
        Calendar ob = Calendar.getInstance();
        int curMonth = ob.get(Calendar.MONTH);
        int curYear = ob.get(Calendar.YEAR);
        int currentPeriod = 1;
        int frequencyDivision = 1;
        int historyStartPeriod = 1;
        int historyEndPeriod = 1;
        int historyEndMonth = 1;
        int historyEndYear = 1;
        int forecastStartPeriod = 1;
        int forecastEndPeriod = 1;
        int projectionStartPeriod = 1;
        int projectionEndPeriod = 1;
        if (frequency.equals(QUARTERLY.getConstant())) {
            frequencyDivision = 4;
            currentPeriod = CommonUtils.getPeriod(curMonth + 1, 3);
            historyStartPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), 3);
            historyEndPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), 3);
            forecastStartPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), 3);
            forecastEndPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), 3);
            projectionStartPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), 3);
            projectionEndPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), 3);
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequencyDivision = 2;
            currentPeriod = CommonUtils.getPeriod(curMonth + 1, 6);
            historyStartPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), 6);
            historyEndPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), 6);
            forecastStartPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), 6);
            forecastEndPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), 6);
            projectionStartPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), 6);
            projectionEndPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), 6);
        } else if (frequency.equals(MONTHLY.getConstant())) {
            currentPeriod = curMonth;
            frequencyDivision = 12;
            historyStartPeriod = projSelDTO.getForecastDTO().getHistoryStartMonth();
            historyEndPeriod = projSelDTO.getForecastDTO().getHistoryEndMonth();
            forecastStartPeriod = projSelDTO.getForecastDTO().getForecastStartMonth();
            forecastEndPeriod = projSelDTO.getForecastDTO().getForecastEndMonth();
            projectionStartPeriod = projSelDTO.getForecastDTO().getProjectionStartMonth();
            projectionEndPeriod = projSelDTO.getForecastDTO().getProjectionEndMonth();
        } else if (frequency.equals(ANNUALLY.getConstant())) {
            currentPeriod = curYear;
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
                historyEndMonth = 12;
                historyEndYear = historyEndYear - 1;
            } else if (historyEndPeriod < 1) {
                historyEndMonth = 12;
                historyEndYear = historyEndYear - 1;
            } else if (frequencyDivision == 2) {
                if (historyEndPeriod == 1) {
                    historyEndMonth = 6;
                } else if (historyEndPeriod == 2) {
                    historyEndMonth = 12;
                }
            } else if (frequencyDivision == 4) {
                if (historyEndPeriod == 1) {
                    historyEndMonth = 3;
                } else if (historyEndPeriod == 2) {
                    historyEndMonth = 6;
                } else if (historyEndPeriod == 3) {
                    historyEndMonth = 9;
                } else if (historyEndPeriod == 4) {
                    historyEndMonth = 12;
                }
            } else if (frequencyDivision == 12) {
                historyEndMonth = historyEndPeriod;
            }
        }
        projSelDTO.setFrequencyDivision(frequencyDivision);
        projSelDTO.setCurrentYear(curYear);
        projSelDTO.setCurrentPeriod(currentPeriod);

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

        projSelDTO = getHistoryDetail(projSelDTO);
        projSelDTO = getForecastDetail(projSelDTO);
        return projSelDTO;
    }

    static int getEndDay(int monthNo, int year) {
        Calendar ob = Calendar.getInstance();
        ob.set(year, monthNo - 1, 1);
        int daysInMonth = ob.getActualMaximum(Calendar.DAY_OF_MONTH);
        return daysInMonth;
    }

    /**
     * To get Start year, Start period and Start month
     *
     * @param projSelDTO
     * @return
     */
    static ProjectionSelectionDTO getHistoryDetail(ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        int historyNum = projSelDTO.getHistoryNum();
        int historyStartYear = projSelDTO.getHistoryEndYear();
        int historyEndPeriod = projSelDTO.getHistoryEndPeriod();
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
        } else if (frequencyDivision == 2) {
            if (historyStartFreq == 1) {
                projSelDTO.setHistoryStartMonth(1);
            } else if (historyStartFreq == 2) {
                projSelDTO.setHistoryStartMonth(7);
            }
        } else if (frequencyDivision == 4) {
            if (historyStartFreq == 1) {
                projSelDTO.setHistoryStartMonth(1);
            } else if (historyStartFreq == 2) {
                projSelDTO.setHistoryStartMonth(4);
            } else if (historyStartFreq == 3) {
                projSelDTO.setHistoryStartMonth(7);
            } else if (historyStartFreq == 4) {
                projSelDTO.setHistoryStartMonth(10);
            }
        } else if (frequencyDivision == 12) {
            projSelDTO.setHistoryStartMonth(historyStartFreq);
        }
        if (projSelDTO.getHistoryStartPeriod() == historyStartFreq && projSelDTO.getForecastDTO().getHistoryStartYear() == historyStartYear) {
            projSelDTO.setHistoryStartMonth(projSelDTO.getForecastDTO().getHistoryStartMonth());
        } else {
            projSelDTO.setHistoryStartMonth(projSelDTO.getForecastDTO().getHistoryStartMonth());
        }
        projSelDTO.setHistoryStartPeriod(historyStartFreq);
        projSelDTO.setHistoryStartYear(historyStartYear);
        projSelDTO.setHistoryStartDay(1);

        int startYear = projSelDTO.getCurrentYear();

        int currentPeriod = projSelDTO.getCurrentPeriod();

        int startFreq = currentPeriod + 1;

        int tempFreq = historyNum - currentPeriod;
        if (tempFreq > 0) {
            startYear = startYear - tempFreq / frequencyDivision;
            startFreq = 1;
            if (tempFreq % frequencyDivision > 0) {
                startYear = startYear - 1;
                startFreq = frequencyDivision - (tempFreq % frequencyDivision) + 1;
            }
        } else {
            startFreq = startFreq - historyNum;
        }
        if (frequencyDivision == 1) {
            startYear = currentPeriod - historyNum;
        }

        projSelDTO.setStartYear(startYear);
        projSelDTO.setStartPeriod(startFreq);
        projSelDTO.setStartDay(1);
        if (frequencyDivision == 1) {
            projSelDTO.setStartMonth(frequencyDivision);
        } else if (frequencyDivision == 2) {
            if (startFreq == 1) {
                projSelDTO.setStartMonth(1);
            } else if (startFreq == 2) {
                projSelDTO.setStartMonth(7);
            }
        } else if (frequencyDivision == 4) {
            if (startFreq == 1) {
                projSelDTO.setStartMonth(1);
            } else if (startFreq == 2) {
                projSelDTO.setStartMonth(4);
            } else if (startFreq == 3) {
                projSelDTO.setStartMonth(7);
            } else if (startFreq == 4) {
                projSelDTO.setStartMonth(10);
            }
        } else if (frequencyDivision == 12) {
            projSelDTO.setStartMonth(startFreq);
        }
        return projSelDTO;
    }

    /**
     * To get End year, End period and End month
     *
     * @param projSelDTO
     * @return
     */
    static ProjectionSelectionDTO getForecastDetail(ProjectionSelectionDTO projSelDTO) {
        int endYear = projSelDTO.getCurrentYear();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int projectionNum = projSelDTO.getProjectionNum();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        int endFreq = currentPeriod + 1;
        int tmpFrq = projectionNum - (frequencyDivision - endFreq);

        if (tmpFrq > 0) {
            endYear = endYear + (tmpFrq / frequencyDivision);
            if (tmpFrq % frequencyDivision > 0) {
                endFreq = tmpFrq % frequencyDivision;
                endYear = endYear + 1;
            } else {
                endFreq = frequencyDivision;
            }
        } else {
            endFreq = endFreq + projectionNum;
        }
        if (frequencyDivision == 1) {
            endYear = currentPeriod + projectionNum;
        }
        projSelDTO.setEndYear(endYear);
        projSelDTO.setEndPeriod(endFreq);
        if (frequencyDivision == 1) {
            projSelDTO.setEndMonth(12);
        } else if (frequencyDivision == 2) {
            if (endFreq == 1) {
                projSelDTO.setEndMonth(6);
            } else if (endFreq == 2) {
                projSelDTO.setEndMonth(12);
            }
        } else if (frequencyDivision == 4) {
            if (endFreq == 1) {
                projSelDTO.setEndMonth(3);
            } else if (endFreq == 2) {
                projSelDTO.setEndMonth(6);
            } else if (endFreq == 3) {
                projSelDTO.setEndMonth(9);
            } else if (endFreq == 4) {
                projSelDTO.setEndMonth(12);
            }
        } else if (frequencyDivision == 12) {
            projSelDTO.setEndMonth(endFreq);
        }
        Calendar ob = Calendar.getInstance();
        ob.set(projSelDTO.getEndYear(), projSelDTO.getEndMonth() - 1, 1);
        int daysInMonth = ob.getActualMaximum(Calendar.DAY_OF_MONTH);
        projSelDTO.setEndDay(daysInMonth);
        return projSelDTO;
    }

    /**
     * To get Start year, Start period and Start month
     *
     * @param projSelDTO
     * @return
     */
    static PVSelectionDTO getHistoryDetail(PVSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        int historyNum = projSelDTO.getHistoryNum();

        int historyStartYear = projSelDTO.getHistoryEndYear();
        int historyEndPeriod = projSelDTO.getHistoryEndPeriod();
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
        } else if (frequencyDivision == 2) {
            if (historyStartFreq == 1) {
                projSelDTO.setHistoryStartMonth(1);
            } else if (historyStartFreq == 2) {
                projSelDTO.setHistoryStartMonth(7);
            }
        } else if (frequencyDivision == 4) {
            if (historyStartFreq == 1) {
                projSelDTO.setHistoryStartMonth(1);
            } else if (historyStartFreq == 2) {
                projSelDTO.setHistoryStartMonth(4);
            } else if (historyStartFreq == 3) {
                projSelDTO.setHistoryStartMonth(7);
            } else if (historyStartFreq == 4) {
                projSelDTO.setHistoryStartMonth(10);
            }
        } else if (frequencyDivision == 12) {
            projSelDTO.setHistoryStartMonth(historyStartFreq);
        }
        if (projSelDTO.getHistoryStartPeriod() == historyStartFreq && projSelDTO.getForecastDTO().getHistoryStartYear() == historyStartYear) {
            projSelDTO.setHistoryStartMonth(projSelDTO.getForecastDTO().getHistoryStartMonth());
        } else {
            projSelDTO.setHistoryStartMonth(projSelDTO.getForecastDTO().getHistoryStartMonth());
        }
        projSelDTO.setHistoryStartPeriod(historyStartFreq);
        projSelDTO.setHistoryStartYear(historyStartYear);
        projSelDTO.setHistoryStartDay(1);

        int startYear = projSelDTO.getCurrentYear();

        int currentPeriod = projSelDTO.getCurrentPeriod();

        int startFreq = currentPeriod + 1;

        int tempFreq = historyNum - currentPeriod;
        if (tempFreq > 0) {
            startYear = startYear - tempFreq / frequencyDivision;
            startFreq = 1;
            if (tempFreq % frequencyDivision > 0) {
                startYear = startYear - 1;
                startFreq = frequencyDivision - (tempFreq % frequencyDivision) + 1;
            }
        } else {
            startFreq = startFreq - historyNum;
        }
        if (frequencyDivision == 1) {
            startYear = currentPeriod - historyNum;
        }

        projSelDTO.setStartYear(startYear);
        projSelDTO.setStartPeriod(startFreq);
        projSelDTO.setStartDay(1);
        if (frequencyDivision == 1) {
            projSelDTO.setStartMonth(frequencyDivision);
        } else if (frequencyDivision == 2) {
            if (startFreq == 1) {
                projSelDTO.setStartMonth(1);
            } else if (startFreq == 2) {
                projSelDTO.setStartMonth(7);
            }
        } else if (frequencyDivision == 4) {
            if (startFreq == 1) {
                projSelDTO.setStartMonth(1);
            } else if (startFreq == 2) {
                projSelDTO.setStartMonth(4);
            } else if (startFreq == 3) {
                projSelDTO.setStartMonth(7);
            } else if (startFreq == 4) {
                projSelDTO.setStartMonth(10);
            }
        } else if (frequencyDivision == 12) {
            projSelDTO.setStartMonth(startFreq);
        }
        return projSelDTO;
    }

    public static CustomTableHeaderDTO getDiscountProjectionResultsLeftTableColumns() {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn(Constant.GROUP, Constant.GROUPFCAPS, String.class);
        tableHeaderDTO.addDoubleColumn("groupValue", StringUtils.EMPTY);
        tableHeaderDTO.addDoubleHeaderMap("groupValue", new Object[]{Constant.GROUP, "levelNo", "hierarchySid"});
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getDiscountProjectionResultsCalculatedColumns(Map selection, CustomTableHeaderDTO tableHeaderDTO) {

        String freq = selection.get(Constant.FREQUENCY).toString();
        String projection = selection.get(Constant.ACTUALSORPROJECTIONS).toString();

        Calendar ob = Calendar.getInstance();
        int curMonth = ob.get(Calendar.MONTH);
        int curDate = ob.get(Calendar.DATE);
        int curYear = ob.get(Calendar.YEAR);
        int current = 1;
        int frequency = String.valueOf(selection.get("historyNum")) != null && !Constant.NULL.equals(String.valueOf(selection.get("historyNum"))) ? Integer.parseInt(String.valueOf(selection.get("historyNum"))) : 0;
        int projectFrequency = String.valueOf(selection.get("projectionNum")) != null && !Constant.NULL.equals(String.valueOf(selection.get("projectionNum"))) ? Integer.parseInt(String.valueOf(selection.get("projectionNum"))) : 0;

        int division = 1;
        if (freq.equals(QUARTERLY.getConstant())) {

            current = (curMonth / 3);
            division = 4;

        } else if (freq.equals(SEMI_ANNUALLY.getConstant())) {
            current = (curMonth / 6);
            division = 2;

        } else if (freq.equals(MONTHLY.getConstant())) {
            current = curMonth;
            division = 12;

        } else if (freq.equals(ANNUALLY.getConstant())) {
            current = curYear;
            division = 1;

        }
        projectFrequency = projectFrequency + 1;
        int pastYear = curYear;

        int startFreq = current + 1;

        int tempFreq = frequency - current;

        if (tempFreq > 0) {
            pastYear = pastYear - tempFreq / division;
            startFreq = 1;
            if (tempFreq % division > 0) {
                pastYear = pastYear - 1;
                startFreq = division - (tempFreq % division) + 1;
            }
        } else {
            startFreq = startFreq - frequency;
        }

        int squr = startFreq;
        int syear = pastYear;
        if (freq.contains(ANNUALLY.getConstant()) && !freq.contains(SEMI_ANNUALLY.getConstant())) {
            syear = current - frequency;
        }
        for (int i = 0; i < frequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = StringUtils.EMPTY;
            String commonHeader = StringUtils.EMPTY;
            if (freq.contains(QUARTERLY.getConstant())) {
                commonColumn = Constant.Q + squr + StringUtils.EMPTY + syear;
                commonHeader = Constant.Q + squr + " " + syear;
            } else if (freq.contains(SEMI_ANNUALLY.getConstant())) {
                commonColumn = Constant.S + squr + StringUtils.EMPTY + syear;
                commonHeader = Constant.S + squr + " " + syear;
            } else if (freq.contains(ANNUALLY.getConstant())) {
                commonColumn = StringUtils.EMPTY + syear;
                commonHeader = StringUtils.EMPTY + syear;
            } else if (freq.contains(MONTHLY.getConstant())) {
                String monthName = getMonthForInt(squr - 1);
                commonColumn = monthName + syear;
                commonHeader = monthName + " " + syear;
            }

            if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                dmap.add(commonColumn + "ActualsRate");
                tableHeaderDTO.addSingleColumn(commonColumn + "ActualsRate", "Actuals Rate", String.class);
                dmap.add(commonColumn + "ActualsAmount");
                tableHeaderDTO.addSingleColumn(commonColumn + "ActualsAmount", "Actuals Amount", String.class);

                if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                    dmap.add(commonColumn + "ProjectionsRate");
                    tableHeaderDTO.addSingleColumn(commonColumn + "ProjectionsRate", "Projections Rate", String.class);
                    dmap.add(commonColumn + "ProjectionsAmount");
                    tableHeaderDTO.addSingleColumn(commonColumn + "ProjectionsAmount", "Projections Amount", String.class);
                }
            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }
            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
        }
        squr = current + 1;
        for (int i = 0; i < projectFrequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = StringUtils.EMPTY;
            String commonHeader = StringUtils.EMPTY;
            if (freq.contains(QUARTERLY.getConstant())) {
                commonColumn = Constant.Q + squr + StringUtils.EMPTY + syear;
                commonHeader = Constant.Q + squr + " " + syear;
            } else if (freq.contains(SEMI_ANNUALLY.getConstant())) {
                commonColumn = Constant.S + squr + StringUtils.EMPTY + syear;
                commonHeader = Constant.S + squr + " " + syear;
            } else if (freq.contains(ANNUALLY.getConstant())) {
                commonColumn = StringUtils.EMPTY + syear;
                commonHeader = StringUtils.EMPTY + syear;
            } else if (freq.contains(MONTHLY.getConstant())) {
                String monthName = getMonthForInt(squr - 1);
                commonColumn = monthName + syear;
                commonHeader = monthName + " " + syear;
            }
            if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                dmap.add(commonColumn + "ProjectionsRate");
                tableHeaderDTO.addSingleColumn(commonColumn + "ProjectionsRate", "Projections Rate", String.class);
                dmap.add(commonColumn + "ProjectionsAmount");
                tableHeaderDTO.addSingleColumn(commonColumn + "ProjectionsAmount", "Projections Amount", String.class);

                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
            }
            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
        }
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSalesProjectionResultsRightTableColumns(ProjectionSelectionDTO projectionDTO, SessionDTO session, CustomTableHeaderDTO fullHeaderDTO) {

        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getSalesProjectionResultsCalculatedColumns(tableHeaderDTO, projectionDTO, fullHeaderDTO);
    }

    public static CustomTableHeaderDTO getSalesProjectionResultsCalculatedColumns(Map selection, CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        ForecastDTO forecast = session.getForecastDTO();
        String freq = selection.get(Constant.FREQUENCY).toString();
        String hist = selection.get(Constant.HISTORY).toString();
        String projFreq = selection.get("projectFrequency").toString();
        String order = selection.get(Constant.ORDER).toString();
        String projection = selection.get(Constant.ACTUALSORPROJECTIONS).toString();
        String pivot = selection.get(Constant.VIEW).toString();
        SalesProjectionLogic salesLogic = new SalesProjectionLogic();
        Calendar ob = Calendar.getInstance();
        int curMonth = forecast.getForecastStartMonth();
        int curDate = ob.get(Calendar.DATE);

        int curYear = forecast.getForecastStartYear();
        int current = 1;
        int frequency = String.valueOf(selection.get("historyNum")) != null && !Constant.NULL.equals(String.valueOf(selection.get("historyNum"))) ? Integer.parseInt(String.valueOf(selection.get("historyNum"))) : 0;
        int projectFrequency = String.valueOf(selection.get("projectionNum")) != null && !Constant.NULL.equals(String.valueOf(selection.get("projectionNum"))) ? Integer.parseInt(String.valueOf(selection.get("projectionNum"))) : 0;
        List<String> totalProjected = new ArrayList<String>();
        projSelDTO.setHistoryNum(frequency);
        projSelDTO.setProjectionNum(projectFrequency);
        int division = 1;
        if ("period".equalsIgnoreCase(pivot)) {
            if (freq.equals(QUARTERLY.getConstant())) {
                current = (curMonth / 3);
                division = 4;
            } else if (freq.equals(SEMI_ANNUALLY.getConstant())) {
                current = (curMonth / 6);
                division = 2;

            } else if (freq.equals(MONTHLY.getConstant())) {
                current = curMonth;
                division = 12;

            } else if (freq.equals(ANNUALLY.getConstant())) {
                current = curYear;
                division = 1;

            }

            int pastYear = curYear;

            int startFreq = current + 1;

            int tempFreq = frequency - current;
            if (tempFreq > 0) {
                pastYear = pastYear - tempFreq / division;
                startFreq = 1;
                if (tempFreq % division > 0) {
                    pastYear = pastYear - 1;
                    startFreq = division - (tempFreq % division) + 1;
                }
            } else {
                startFreq = startFreq - frequency;
            }

            int squr = startFreq;
            int syear = pastYear;
            int reprojtionStartQuator = salesLogic.getQuator(forecast.getProjectionStartMonth());
            int reprojtionStartYear = forecast.getProjectionStartYear();
            if (freq.contains(ANNUALLY.getConstant()) && !freq.contains(SEMI_ANNUALLY.getConstant())) {
                syear = current - frequency;
            }

            for (int i = 0; i < frequency; i++) {
                List<Object> dmap = new ArrayList<Object>();
                String commonColumn = StringUtils.EMPTY;
                String commonHeader = StringUtils.EMPTY;
                if (freq.contains(QUARTERLY.getConstant())) {
                    commonColumn = Constant.Q + squr + StringUtils.EMPTY + syear;
                    commonHeader = Constant.Q + squr + " " + syear;
                } else if (freq.contains(SEMI_ANNUALLY.getConstant())) {
                    commonColumn = Constant.S + squr + StringUtils.EMPTY + syear;
                    commonHeader = Constant.S + squr + " " + syear;
                } else if (freq.contains(ANNUALLY.getConstant())) {
                    commonColumn = StringUtils.EMPTY + syear;
                    commonHeader = StringUtils.EMPTY + syear;
                } else if (freq.contains(MONTHLY.getConstant())) {
                    String monthName = getMonthForInt(squr - 1);
                    commonColumn = (monthName + syear).toUpperCase();
                    commonHeader = monthName + " " + syear;
                }
                if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                    dmap.add(commonColumn + ACTUALS.getConstant());
                    tableHeaderDTO.addSingleColumn(commonColumn + ACTUALS.getConstant(), ACTUALS.getConstant(), String.class);
                    tableHeaderDTO.addSingleHistoryColumn(commonColumn + ACTUALS.getConstant(), ACTUALS.getConstant());
                }
                if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                    dmap.add(commonColumn + PROJECTIONS.getConstant());
                    tableHeaderDTO.addSingleColumn(commonColumn + PROJECTIONS.getConstant(), PROJECTIONS.getConstant(), String.class);
                    tableHeaderDTO.addSingleHistoryColumn(commonColumn + PROJECTIONS.getConstant(), PROJECTIONS.getConstant());
                }

                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    tableHeaderDTO.addDoubleHistoryColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHistoryHeaderMap(commonColumn, dmap.toArray());
                }
                squr++;
                if (squr > division) {
                    squr = 1;
                    syear++;
                }
            }
            squr = current + 1;

            for (int i = 0; i < projectFrequency; i++) {
                List<Object> dmap = new ArrayList<Object>();
                String commonColumn = StringUtils.EMPTY;
                String commonHeader = StringUtils.EMPTY;
                if (freq.contains(QUARTERLY.getConstant())) {
                    commonColumn = Constant.Q + squr + StringUtils.EMPTY + syear;
                    commonHeader = Constant.Q + squr + " " + syear;
                } else if (freq.contains(SEMI_ANNUALLY.getConstant())) {
                    commonColumn = Constant.S + squr + StringUtils.EMPTY + syear;
                    commonHeader = Constant.S + squr + " " + syear;
                } else if (freq.contains(ANNUALLY.getConstant())) {
                    commonColumn = StringUtils.EMPTY + syear;
                    commonHeader = StringUtils.EMPTY + syear;
                } else if (freq.contains(MONTHLY.getConstant())) {
                    String monthName = getMonthForInt(squr - 1);
                    commonColumn = (monthName + syear).toUpperCase();
                    commonHeader = monthName + " " + syear;
                }

                dmap.add(commonColumn + PROJECTIONS.getConstant());
                tableHeaderDTO.addSingleColumn(commonColumn + PROJECTIONS.getConstant(), PROJECTIONS.getConstant(), String.class);
                tableHeaderDTO.addSingleProjectedColumn(commonColumn + PROJECTIONS.getConstant(), PROJECTIONS.getConstant());
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
                squr++;
                if (squr > division) {
                    squr = 1;
                    syear++;
                }
            }
        } else {
            List<Object> dmap = new ArrayList<Object>();
            List<Object> dmap1 = new ArrayList<Object>();
            List<Object> dmap2 = new ArrayList<Object>();
            if (projection.contains(ACTUALS.getConstant()) || projection.contains(Constant.BOTH)) {
                dmap.add("gtsActuals");
                tableHeaderDTO.addSingleColumn("gtsActuals", ACTUALS.getConstant(), String.class);
            }
            if (projection.contains(PROJECTIONS.getConstant()) || projection.contains(Constant.BOTH)) {
                dmap.add("gtsProjections");
                tableHeaderDTO.addSingleColumn("gtsProjections", PROJECTIONS.getConstant(), String.class);
            }
            if (projection.contains(ACTUALS.getConstant()) || projection.contains(Constant.BOTH)) {
                dmap1.add("cswActuals");
                tableHeaderDTO.addSingleColumn("cswActuals", ACTUALS.getConstant(), String.class);
            }
            if (projection.contains(PROJECTIONS.getConstant()) || projection.contains(Constant.BOTH)) {
                dmap1.add("cswProjections");
                tableHeaderDTO.addSingleColumn("cswProjections", PROJECTIONS.getConstant(), String.class);
            }
            if (projection.contains(ACTUALS.getConstant()) || projection.contains(Constant.BOTH)) {
                dmap2.add("uvActuals");
                tableHeaderDTO.addSingleColumn("uvActuals", ACTUALS.getConstant(), String.class);
            }
            if (projection.contains(PROJECTIONS.getConstant()) || projection.contains(Constant.BOTH)) {
                dmap2.add("uvProjections");
                tableHeaderDTO.addSingleColumn("uvProjections", PROJECTIONS.getConstant(), String.class);
            }
            if (!dmap.isEmpty() || !dmap1.isEmpty() || !dmap2.isEmpty()) {
                tableHeaderDTO.addDoubleColumn("gts", "Gross Trade Sales");
                tableHeaderDTO.addDoubleHeaderMap("gts", dmap.toArray());
                tableHeaderDTO.addDoubleColumn(Constant.CONTRACT, "Contract Sales @ WAC");
                tableHeaderDTO.addDoubleHeaderMap(Constant.CONTRACT, dmap1.toArray());
                tableHeaderDTO.addDoubleColumn("unit", Constant.UNIT_VOLUME);
                tableHeaderDTO.addDoubleHeaderMap("unit", dmap2.toArray());
            }
            projSelDTO = getHistoryAndProjectionDetails(projSelDTO);
            int startPeriod = projSelDTO.getStartPeriod();
            int endPeriod = projSelDTO.getEndPeriod();
            int startYear = projSelDTO.getStartYear();
            int endYear = projSelDTO.getEndYear();
            int frequencyDivision = projSelDTO.getFrequencyDivision();
            int startPr = startPeriod;
            int lastPr = frequencyDivision;
            List<String> periodList = new ArrayList<String>();
            Map<String, String> periodListMap = new HashMap<String, String>();
            for (int yr = startYear; yr <= endYear; yr++) {
                if (yr == endYear) {
                    lastPr = endPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {

                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    periodList.add(commonColumn);
                    periodListMap.put(commonColumn, commonHeader);
                }
                startPr = 1;
            }

            projSelDTO.setPeriodList(periodList);
            projSelDTO.setPeriodListMap(periodListMap);
        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(tableHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSalesProjectionResultsCalculatedColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        String projections = projSelDTO.getActualsOrProjections();
        String pivotView = projSelDTO.getPivotView();
        String frequency = projSelDTO.getFrequency();
        CommonUtils.getHistoryAndProjectionDetails(projSelDTO);
        int historyNum = projSelDTO.getHistoryNum();
        int frequencyDivision = projSelDTO.getFrequencyDivision();

        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endYear = projSelDTO.getEndYear();
        int endPeriod = projSelDTO.getEndPeriod();
        int projectionOrder = 0;
        if (projOrder.contains(ASCENDING.getConstant())) {
            projectionOrder = 1;
        } else {
            projectionOrder = 2;
        }
        tableHeaderDTO.setFrequency(frequency);
        tableHeaderDTO.setFrequencyDivision(frequencyDivision);
        tableHeaderDTO.setHistoryNum(historyNum);
        tableHeaderDTO.setProjectionNum(projectionNum);
        tableHeaderDTO.setCurrentPeriod(currentPeriod);
        tableHeaderDTO.setCurrentYear(currentYear);
        tableHeaderDTO.setStartYear(startYear);
        tableHeaderDTO.setStartPeriod(startPeriod);
        tableHeaderDTO.setEndYear(endYear);
        tableHeaderDTO.setEndPeriod(endPeriod);
        tableHeaderDTO.setProjectionOrder(projectionOrder);

        fullHeaderDTO.setFrequency(frequency);
        fullHeaderDTO.setFrequencyDivision(frequencyDivision);
        fullHeaderDTO.setHistoryNum(historyNum);
        fullHeaderDTO.setProjectionNum(projectionNum);
        fullHeaderDTO.setCurrentPeriod(currentPeriod);
        fullHeaderDTO.setCurrentYear(currentYear);
        fullHeaderDTO.setStartYear(startYear);
        fullHeaderDTO.setStartPeriod(startPeriod);
        fullHeaderDTO.setEndYear(endYear);
        fullHeaderDTO.setEndPeriod(endPeriod);
        fullHeaderDTO.setProjectionOrder(projectionOrder);
        prepareCommonColumnHeaders(projSelDTO);
        if (pivotView.contains(VARIABLE.getConstant())) {

            for (int i = 0; i < 5; i++) {

                String commonColumn = StringUtils.EMPTY;
                String oldCommonColumn = StringUtils.EMPTY;
                String commonHeader = StringUtils.EMPTY;
                if (i == 0) {
                    commonColumn = "efs";
                    commonHeader = "Ex-Factory Sales";
                } else if (i == 1) {
                    commonColumn = "dms";
                    commonHeader = "Demand Sales";
                } else if (i == 2) {
                    commonColumn = "iws";
                    commonHeader = "Inventory Withdraw";
                } else if (i == 3) {
                    if (projSelDTO.getSalesOrUnit().equalsIgnoreCase("Sales") || projSelDTO.getSalesOrUnit().equalsIgnoreCase("Both")) {
                        commonColumn = "csw";
                        commonHeader = "Contract Sales @ WAC";
                    } else {
                        continue;
                    }
                } else if (i == 4) {
                    if (projSelDTO.getSalesOrUnit().equalsIgnoreCase("Units") || projSelDTO.getSalesOrUnit().equalsIgnoreCase("Both")) {
                        commonColumn = "uv";
                        commonHeader = Constant.UNIT_VOLUME;
                    } else {
                        continue;
                    }
                }
                oldCommonColumn = commonColumn;
                int j = -1;

                List<Object> dmap = new ArrayList<Object>();
                if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                    Object singleColumn = commonColumn + ACTUALS.getConstant();
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                }
                Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                dmap.add(singleColumn);
                tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
            }
        } else {

            List<String> periodList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            for (int i = 0; i < periodList.size(); i++) {
                List<Object> dmap = new ArrayList<Object>();
                String commonColumn = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn);
                boolean historyFlag = false;
                boolean projectionCol = false;
                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    historyFlag = true;

                    if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, ACTUALS.getConstant());
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                    }
                }
                if (i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex()) {
                    projectionCol = true;
                } else if (historyFlag && (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTIONS.getConstant()))) {
                    projectionCol = true;
                }

                if (i >= projSelDTO.getForecastStartIndex() && i <= projSelDTO.getForecastEndIndex()) {
                    projectionCol = true;
                }

                if (projectionCol) {
                    Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTIONS.getConstant());
                    fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
            }

        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(tableHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSalesProjectionResultsLeftTableColumns(Map selection, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("levelValue", " ", String.class);

        fullHeaderDTO.addSingleColumn("levelValue", "Level Name", String.class);
        fullHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        fullHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{"levelValue"});
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getAlternateHistoryLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn(Constant.CHECK, " ", Boolean.class);
        tableHeaderDTO.addSingleColumn("contractNo", "Contract No", String.class);
        tableHeaderDTO.addSingleColumn("contractName", "Contract Name", String.class);
         tableHeaderDTO.addSingleColumn(Constant.CUSTOMER_NO, "Customer No", String.class);
        tableHeaderDTO.addSingleColumn(Constant.CUSTOMER_NAME, "Customer Name", String.class);
        tableHeaderDTO.addSingleColumn(Constant.ITEM_NO, "Item No", String.class);
        tableHeaderDTO.addSingleColumn("itemName", "Item Name", String.class);

        tableHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        tableHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{"contractNo", "contractName", Constant.CUSTOMER_NO, Constant.CUSTOMER_NAME, Constant.ITEM_NO, "itemName"});

        fullHeaderDTO.addSingleColumn("contractNo", "Contract No", String.class);
        fullHeaderDTO.addSingleColumn("contractName", "Contract Name", String.class);
        fullHeaderDTO.addSingleColumn(Constant.CUSTOMER_NO, "Company No", String.class);
        fullHeaderDTO.addSingleColumn(Constant.CUSTOMER_NAME, "Company Name", String.class);
        fullHeaderDTO.addSingleColumn(Constant.ITEM_NO, "Item No", String.class);
        fullHeaderDTO.addSingleColumn("itemName", "Item Name", String.class);

        fullHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        fullHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{Constant.CHECK, "contractNo", "contractName", Constant.CUSTOMER_NO, Constant.CUSTOMER_NAME, Constant.ITEM_NO, "itemName"});
        return tableHeaderDTO;
    }

    static Map<String, Integer> getHistoryAndProjVarianceDetails(Map selection, PVSelectionDTO pvsdto) {
        String frequency = selection.get(Constant.FREQUENCY).toString();
        String projFreq = selection.get("projectFrequency").toString();
        String hist = selection.get(Constant.HISTORY).toString();
        int projNum = Integer.valueOf(selection.get("projNum").toString());
        Map<String, Integer> histProjMap = new HashMap<String, Integer>();
        Calendar ob = Calendar.getInstance();
        int curMonth = ob.get(Calendar.MONTH);
        int curYear = ob.get(Calendar.YEAR);
        int currentPeriod = 1;
        int historyNum = 0;
        int projectionNum = 0;
        int frequencyDivision = 1;
        if (frequency.contains(QUARTERLY.getConstant())) {
            currentPeriod = (curMonth / 3);
            frequencyDivision = 4;
            try {
                historyNum = Integer.valueOf(hist.toLowerCase().replace("quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                projectionNum = Integer.valueOf(projFreq.toLowerCase().replace("quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        } else if (frequency.contains(SEMI_ANNUAL.getConstant())) {
            currentPeriod = (curMonth / 6);
            frequencyDivision = 2;
            try {
                historyNum = Integer.valueOf(hist.toLowerCase().replace("semi-annual", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                projectionNum = Integer.valueOf(projFreq.toLowerCase().replace("semi-annual", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        } else if (frequency.contains(MONTHLY.getConstant())) {
            currentPeriod = curMonth;
            frequencyDivision = 12;
            try {
                historyNum = Integer.valueOf(hist.toLowerCase().replace("month", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                projectionNum = Integer.valueOf(projFreq.toLowerCase().replace("month", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        } else if (frequency.contains(ANNUAL.getConstant())) {
            currentPeriod = curYear;
            frequencyDivision = 1;
            try {
                historyNum = Integer.valueOf(hist.toLowerCase().replace("year", StringUtils.EMPTY).trim());
                projectionNum = Integer.valueOf(projFreq.toLowerCase().replace("year", StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        }
        histProjMap.put("frequencyDivision", frequencyDivision);
        histProjMap.put("historyNum", historyNum);
        histProjMap.put("projectionNum", projectionNum);
        histProjMap.put("currentPeriod", currentPeriod);
        histProjMap.put("currentYear", curYear);
        histProjMap.put("projNum", projNum);

        pvsdto.setFrequencyDivision(frequencyDivision);
        pvsdto.setHistoryNum(historyNum);
        pvsdto.setProjectionNum(projectionNum);
        pvsdto.setCurrentPeriod(currentPeriod);
        pvsdto.setCurrentYear(curYear);
        histProjMap = getHistoryDetail(histProjMap, pvsdto);
        histProjMap = getProjectionDetail(histProjMap, pvsdto);
        return histProjMap;
    }

    static Map<String, Integer> getHistoryDetail(Map<String, Integer> histProjMap, PVSelectionDTO pvsdto) {
        int startYear = histProjMap.get("currentYear");
        int currentPeriod = histProjMap.get("currentPeriod");
        int historyNum = histProjMap.get("historyNum");
        int frequencyDivision = histProjMap.get("frequencyDivision");
        int startFreq = currentPeriod + 1;
        int startDay = 1;
        int startMonth = 0;

        int tempFreq = historyNum - currentPeriod;
        if (tempFreq > 0) {
            startYear = startYear - tempFreq / frequencyDivision;
            startFreq = 1;
            if (tempFreq % frequencyDivision > 0) {
                startYear = startYear - 1;
                startFreq = frequencyDivision - (tempFreq % frequencyDivision) + 1;
            }
        } else {
            startFreq = startFreq - historyNum;
        }
        if (frequencyDivision == 1) {
            startYear = currentPeriod - historyNum;
        }
        histProjMap.put("startYear", startYear);
        histProjMap.put("startPeriod", startFreq);
        if (frequencyDivision == 1) {
            startYear = currentPeriod - historyNum;
        }

        if (frequencyDivision == 1) {
            startMonth = frequencyDivision;
        } else if (frequencyDivision == 2) {
            if (startFreq == 1) {
                startMonth = 1;
            } else if (startFreq == 2) {
                startMonth = 7;
            }
        } else if (frequencyDivision == 4) {
            if (startFreq == 1) {
                startMonth = 1;
            } else if (startFreq == 2) {
                startMonth = 4;
            } else if (startFreq == 3) {
                startMonth = 7;
            } else if (startFreq == 4) {
                startMonth = 10;
            }
        } else if (frequencyDivision == 12) {
            startMonth = startFreq;
        }
        histProjMap.put("startDay", startDay);
        histProjMap.put("startMonth", startMonth);
        pvsdto.setStartDay(startDay);
        pvsdto.setStartMonth(startMonth);
        return histProjMap;
    }

    static Map<String, Integer> getProjectionDetail(Map<String, Integer> histProjMap, PVSelectionDTO pvsdto) {
        int endYear = histProjMap.get("currentYear");
        int currentPeriod = histProjMap.get("currentPeriod");
        int projectionNum = histProjMap.get("projNum");
        int frequencyDivision = histProjMap.get("frequencyDivision");
        int endFreq = currentPeriod + 1;
        int tmpFrq = projectionNum - (frequencyDivision - endFreq);
        int endMonth = 0;
        int endDay;

        if (tmpFrq > 0) {
            endYear = endYear + (tmpFrq / frequencyDivision);
            if (tmpFrq % frequencyDivision > 0) {

                endFreq = tmpFrq % frequencyDivision;
                endYear = endYear + 1;
            } else {

                endFreq = frequencyDivision;
            }
        } else {
            endFreq = endFreq + projectionNum;
        }
        if (frequencyDivision == 1) {
            endYear = currentPeriod + projectionNum;
        }

        histProjMap.put("endYear", endYear);
        histProjMap.put("endPeriod", endFreq);
        pvsdto.setEndYear(endYear);
        pvsdto.setEndPeriod(endFreq);
        if (frequencyDivision == 1) {
            endMonth = 12;
        } else if (frequencyDivision == 2) {
            if (endFreq == 1) {
                endMonth = 6;
            } else if (endFreq == 2) {
                endMonth = 12;
            }
        } else if (frequencyDivision == 4) {
            if (endFreq == 1) {
                endMonth = 3;
            } else if (endFreq == 2) {
                endMonth = 6;
            } else if (endFreq == 3) {
                endMonth = 9;
            } else if (endFreq == 4) {
                endMonth = 12;
            }
        } else if (frequencyDivision == 12) {
            endMonth = endFreq;
        }
        Calendar ob = Calendar.getInstance();
        ob.set(endYear, endMonth - 1, 1);
        int daysInMonth = ob.getActualMaximum(Calendar.DAY_OF_MONTH);
        endDay = daysInMonth;
        histProjMap.put("endDay", endDay);
        histProjMap.put("endMonth", endMonth);
        pvsdto.setEndDay(endDay);
        pvsdto.setEndMonth(endMonth);
        return histProjMap;
    }

    /**
     * Sets the base variables.
     *
     * @param columns the columns
     * @param varriables the varriables
     */
    public static void setBaseVariables(String columns, String varriables, PVSelectionDTO projSelDTO) {
        LOGGER.info("Entering setBaseVariables method");
        projSelDTO.setColValue(false);
        projSelDTO.setColVariance(false);
        projSelDTO.setColPercentage(false);
        projSelDTO.setVarExFacSales(false);
        projSelDTO.setVarDemandSales(false);
        projSelDTO.setVarInvSales(false);
        projSelDTO.setVarPerExFacSales(false);
        projSelDTO.setVarPerDemandSales(false);
        projSelDTO.setVarPerInvSales(false);
        projSelDTO.setVarContractsales(false);
        projSelDTO.setVarContractUnits(false);
        projSelDTO.setVarDisAmount(false);
        projSelDTO.setVarDisRate(false);
        projSelDTO.setVarNetSales(false);
        projSelDTO.setVarCOGC(false);
        projSelDTO.setVarRPU(false);
        projSelDTO.setVarNetProfit(false);

        columns = columns.substring(1, columns.length() - 1);
        varriables = varriables.substring(1, varriables.length() - 1);
        final String[] col = columns.split(",");
        final String[] var = varriables.split(",");
        for (String value : col) {

            value = StringUtils.trim(value);
            if (value.equalsIgnoreCase(StringUtils.trim(CommonUtils.COL_VALUE))) {
                projSelDTO.setColValue(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(CommonUtils.COL_VARIANCE))) {
                projSelDTO.setColVariance(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(CommonUtils.COL_PERCENTAGE))) {
                projSelDTO.setColPercentage(true);
            }
        }

        for (String value : var) {

            value = StringUtils.trim(value);
            if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.EX_FACTORY_SALES.toString()))) {
                projSelDTO.setVarExFacSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.DEMAND_SALES.toString()))) {
                projSelDTO.setVarDemandSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.INVENTORY_SALES.toString()))) {
                projSelDTO.setVarInvSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.PER_EX_FACTORY.toString()))) {
                projSelDTO.setVarPerExFacSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.PER_DEMAND.toString()))) {
                projSelDTO.setVarPerDemandSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString()))) {
                projSelDTO.setVarPerInvSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_CONTRACT_SALES.toString()))) {
                projSelDTO.setVarContractsales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_CONTRACT_UNITS.toString()))) {
                projSelDTO.setVarContractUnits(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_DIS_AMOUNT.toString()))) {
                projSelDTO.setVarDisAmount(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_DIS_RATE.toString()))) {
                projSelDTO.setVarDisRate(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_RPU.toString()))) {
                projSelDTO.setVarRPU(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_NETSALES.toString()))) {
                projSelDTO.setVarNetSales(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_COGS.toString()))) {
                projSelDTO.setVarCOGC(true);
            } else if (value.equalsIgnoreCase(StringUtils.trim(Constant.PVVariables.VAR_NET_PROFITE.toString()))) {
                projSelDTO.setVarNetProfit(true);
            }
        }

        LOGGER.info("End of setBaseVariables method");
    }

    static CustomTableHeaderDTO loadSingleHeader(String commonColumn, final String commonHeader, final PVSelectionDTO selection, final CustomTableHeaderDTO tableHeaderDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String currentProjName = selection.getCurrentProjectionName();
        List<Integer> projList = selection.getProjIdList();
        Map<Integer, String> priorMap = selection.getProjectionMap();
        boolean disc = true;
        while (disc) {
            List<Object> dmap = new ArrayList<Object>();
            tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT + selection.getCurrentProjId(), currentProjName, String.class);
            fullHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT + selection.getCurrentProjId(), commonHeader + " " + currentProjName, String.class);
            dmap.add(commonColumn + Constant.CURRENT + selection.getCurrentProjId());
            if (!projList.isEmpty()) {
                for (int j = 0; j < projList.size(); j++) {
                    tableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                    fullHeaderDTO.addSingleColumn(commonColumn + projList.get(j), commonHeader + " " + priorMap.get(projList.get(j)), String.class);
                    dmap.add(commonColumn + projList.get(j));
                }
            }
            disc = false;
            if (!dmap.isEmpty() && dmap != null) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }

        }
        return tableHeaderDTO;
    }

    static CustomTableHeaderDTO loadSingleDiscountHeader(String commonColumn, String commonHeader, final PVSelectionDTO selection, final CustomTableHeaderDTO tableHeaderDTO, final CustomTableHeaderDTO fullHeader,Map<String, Object> headerMap) {
        String column = commonColumn;
        List<String> discountNames = new ArrayList<String>(selection.getDiscountNameList());
        //PPA
        List list = CommonLogic.getPPADiscountNameList(selection);
        if(list!=null){
            discountNames.addAll(list);
        }
        List<Integer> projList = selection.getProjIdList();
        Map<Integer, String> priorMap = selection.getProjectionMap();
        List<String> commonColumnList = new ArrayList<String>();
        List<Object> dmap = new ArrayList<Object>();
        if (!discountNames.isEmpty()) {

            for (int i = 0; i < discountNames.size(); i++) {
                dmap.clear();
                String disCommonHeader = discountNames.get(i);
                commonColumn = column + disCommonHeader.replace(" ", StringUtils.EMPTY) + i;
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT + selection.getCurrentProjId(), selection.getCurrentProjectionName(), String.class);
                fullHeader.addSingleColumn(commonColumn + Constant.CURRENT + selection.getCurrentProjId(),  disCommonHeader+" "+selection.getCurrentProjectionName() , String.class);
                dmap.add(commonColumn + Constant.CURRENT + selection.getCurrentProjId());
                if (!projList.isEmpty()) {
                    for (int j = 0; j < projList.size(); j++) {
                        tableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                         fullHeader.addSingleColumn(commonColumn + projList.get(j), disCommonHeader+" "+priorMap.get(projList.get(j)), String.class);
                        dmap.add(commonColumn + projList.get(j));
                    }
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, disCommonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
                commonColumnList.add(commonColumn);
                headerMap.put(column, commonColumnList);
            }

        }

        // Removed PPA and Returns static lines as per GALUAT-321 issue
        
        /*
        commonColumn = column + Constant.PPA_SMALL;
        tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT + selection.getCurrentProjId(), selection.getCurrentProjectionName(), String.class);
         fullHeader.addSingleColumn(commonColumn + Constant.CURRENT  + selection.getCurrentProjId(),  StringUtils.EMPTY + "PPA" + " " + commonHeader+" "+selection.getCurrentProjectionName(), String.class);
        dmap.clear();
        dmap.add(commonColumn + Constant.CURRENT + selection.getCurrentProjId());
        if (!projList.isEmpty()) {
            for (int j = 0; j < projList.size(); j++) {
                tableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                fullHeader.addSingleColumn(commonColumn + projList.get(j),  "PPA" + " " + commonHeader+ " " + priorMap.get(projList.get(j)), String.class);
                dmap.add(commonColumn + projList.get(j));
            }
        }
        if (!dmap.isEmpty()) {
            tableHeaderDTO.addDoubleColumn(commonColumn, "PPA " + commonHeader);
            tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
        }
        commonColumnList.add(commonColumn);
        headerMap.put(column, commonColumnList);

        commonColumn = column + "Returns";
        tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT + selection.getCurrentProjId(), selection.getCurrentProjectionName(), String.class);
          fullHeader.addSingleColumn(commonColumn + "Current" + selection.getCurrentProjId(),StringUtils.EMPTY + "Returns" +" "+commonHeader +" "+selection.getCurrentProjectionName(), String.class);
        dmap.clear();
        dmap.add(commonColumn + Constant.CURRENT + selection.getCurrentProjId());
        if (!projList.isEmpty()) {
            for (int j = 0; j < projList.size(); j++) {
                tableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                fullHeader.addSingleColumn(commonColumn + projList.get(j),  "Returns" +" "+commonHeader+ " " + priorMap.get(projList.get(j)), String.class);
                dmap.add(commonColumn + projList.get(j));
            }
        }
        if (!dmap.isEmpty()) {
            tableHeaderDTO.addDoubleColumn(commonColumn, "Returns " + commonHeader);
            tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
        }
        commonColumnList.add(commonColumn);
        headerMap.put(column, commonColumnList);
        */
        selection.setHeaderMap(headerMap);

        return tableHeaderDTO;
    }

    public static List<String> getCommonColumnHeaderForPV(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<String>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == 4) {
            commonColumn = Constant.Q + period + StringUtils.EMPTY + year;
            commonHeader = Constant.Q + period + " " + year;
        } else if (frequencyDivision == 2) {
            commonColumn = Constant.S + period + StringUtils.EMPTY + year;
            commonHeader = Constant.S + period + " " + year;
        } else if (frequencyDivision == 12) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    public static CustomTableHeaderDTO getDiscountProjectionResultsLeftTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object doubleCol = Constant.GROUP;
        Object[] singleCol = {Constant.GROUP};
        tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        tableHeaderDTO.addDoubleColumn(doubleCol, " ");
        tableHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        fullHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
        fullHeaderDTO.addDoubleColumn(doubleCol, " ");
        fullHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getDiscountProjectionResultsRightTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getCalculatedDPRColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }

    public static CustomTableHeaderDTO getCalculatedDiscountProjectionColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        List<String> discountNames = projSelDTO.getDiscountNameList();
        String projections = projSelDTO.getActualsOrProjections();
        String pivotView = projSelDTO.getPivotView();
        String frequency = projSelDTO.getFrequency();
        projSelDTO = getHistoryAndProjectionDetails(projSelDTO);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        int historyNum = projSelDTO.getHistoryNum();
        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endYear = projSelDTO.getEndYear();
        int endPeriod = projSelDTO.getEndPeriod();
        int projectionOrder = 0;
        if (projOrder.contains(ASCENDING.getConstant())) {
            projectionOrder = 1;
        } else {
            projectionOrder = 2;
        }
        tableHeaderDTO.setFrequency(frequency);
        tableHeaderDTO.setFrequencyDivision(frequencyDivision);
        tableHeaderDTO.setHistoryNum(historyNum);
        tableHeaderDTO.setProjectionNum(projectionNum);
        tableHeaderDTO.setCurrentPeriod(currentPeriod);
        tableHeaderDTO.setCurrentYear(currentYear);
        tableHeaderDTO.setStartYear(startYear);
        tableHeaderDTO.setStartPeriod(startPeriod);
        tableHeaderDTO.setEndYear(endYear);
        tableHeaderDTO.setEndPeriod(endPeriod);
        tableHeaderDTO.setProjectionOrder(projectionOrder);

        fullHeaderDTO.setFrequency(frequency);
        fullHeaderDTO.setFrequencyDivision(frequencyDivision);
        fullHeaderDTO.setHistoryNum(historyNum);
        fullHeaderDTO.setProjectionNum(projectionNum);
        fullHeaderDTO.setCurrentPeriod(currentPeriod);
        fullHeaderDTO.setCurrentYear(currentYear);
        fullHeaderDTO.setStartYear(startYear);
        fullHeaderDTO.setStartPeriod(startPeriod);
        fullHeaderDTO.setEndYear(endYear);
        fullHeaderDTO.setEndPeriod(endPeriod);
        fullHeaderDTO.setProjectionOrder(projectionOrder);

        if (pivotView.contains(DISCOUNT.getConstant())) {
            if (!discountNames.isEmpty()) {
                for (int i = 0; i < discountNames.size(); i++) {
                    String commonColumn = StringUtils.EMPTY;
                    String commonHeader = discountNames.get(i);
                    commonColumn = commonHeader.replace(" ", StringUtils.EMPTY);
                    List<Object> dmap = new ArrayList<Object>();
                    if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("actuals") || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn1 = commonColumn + ACTUALRATE.getConstant();
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                        Object singleColumn2 = commonColumn + ACTUALAMOUNT.getConstant();
                        dmap.add(singleColumn2);
                        tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);
                    }
                    if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("projections") || projections.contains(PROJECTIONS.getConstant())) {
                        Object singleColumn1 = commonColumn + PROJECTEDRATE.getConstant();
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                        Object singleColumn2 = commonColumn + PROJECTEDAMOUNT.getConstant();
                        dmap.add(singleColumn2);
                        tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);
                    }
                    if (!dmap.isEmpty()) {
                        tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    }

                }
            } else {
                Object doubleCol = "empty";
                Object[] singleCol = {"empty"};
                tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
                tableHeaderDTO.addDoubleColumn(doubleCol, " ");
                tableHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
            }

            int startPr = startPeriod;
            int lastPr = frequencyDivision;
            List<String> periodList = new ArrayList<String>();
            Map<String, String> periodListMap = new HashMap<String, String>();
            for (int yr = startYear; yr <= endYear; yr++) {
                if (yr == endYear) {
                    lastPr = endPeriod;
                }
                if (frequencyDivision == 1) {
                    startPr = yr;
                    lastPr = yr;
                }
                for (int pr = startPr; pr <= lastPr; pr++) {

                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonColumn = common.get(0);
                    String commonHeader = common.get(1);
                    periodList.add(commonColumn);
                    periodListMap.put(commonColumn, commonHeader);
                }
                startPr = 1;
            }

            projSelDTO.setPeriodList(periodList);
            projSelDTO.setPeriodListMap(periodListMap);
        } else {
            if (frequencyDivision != 1) {
                currentPeriod = currentPeriod + 1;
            }
            boolean hist = false;
            boolean proj = false;
            if (projectionOrder == 1) {
                int startPr = startPeriod;
                int lastPr = frequencyDivision;
                hist = true;
                proj = false;
                for (int yr = startYear; yr <= endYear; yr++) {
                    if (yr == endYear) {
                        lastPr = endPeriod;
                    }
                    if (frequencyDivision == 1) {
                        startPr = yr;
                        lastPr = yr;
                    }
                    for (int pr = startPr; pr <= lastPr; pr++) {
                        if (pr == currentPeriod && yr == currentYear) {
                            hist = false;
                            proj = true;
                        }

                        List<Object> dmap = new ArrayList<Object>();
                        List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                        if (hist) {
                            if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("actuals") || projections.contains(ACTUALS.getConstant())) {
                                Object singleColumn1 = commonColumn + ACTUALRATE.getConstant();
                                dmap.add(singleColumn1);
                                tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                                fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                                Object singleColumn2 = commonColumn + ACTUALAMOUNT.getConstant();
                                dmap.add(singleColumn2);
                                tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                                fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);

                            }
                        }
                        if (hist && (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("projections") || projections.contains(PROJECTIONS.getConstant())) || proj) {

                            Object singleColumn1 = commonColumn + PROJECTEDRATE.getConstant();
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                            Object singleColumn2 = commonColumn + PROJECTEDAMOUNT.getConstant();
                            dmap.add(singleColumn2);
                            tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);
                        }

                        if (!dmap.isEmpty()) {
                            tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        }

                    }
                    startPr = 1;
                }
            } else {

                int startPr = endPeriod;
                int lastPr = 1;
                proj = true;
                hist = false;
                for (int yr = endYear; yr >= startYear; yr--) {
                    if (yr == startYear) {
                        lastPr = startPeriod;
                    }
                    if (frequencyDivision == 1) {
                        startPr = yr;
                        lastPr = yr;
                    }
                    for (int pr = startPr; pr >= lastPr; pr--) {

                        List<Object> dmap = new ArrayList<Object>();
                        List<String> common = getCommonColumnHeaderForDiscount(frequencyDivision, yr, pr);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                        if (hist) {
                            if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("actuals") || projections.contains(ACTUALS.getConstant())) {
                                Object singleColumn1 = commonColumn + ACTUALRATE.getConstant();
                                dmap.add(singleColumn1);
                                tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                                fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                                Object singleColumn2 = commonColumn + ACTUALAMOUNT.getConstant();
                                dmap.add(singleColumn2);
                                tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                                fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);
                            }
                        }
                        if (hist || proj) {
                            if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("projections") || projections.contains(PROJECTIONS.getConstant())) {
                                Object singleColumn1 = commonColumn + PROJECTEDRATE.getConstant();
                                dmap.add(singleColumn1);
                                tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                                fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                                Object singleColumn2 = commonColumn + PROJECTEDAMOUNT.getConstant();
                                dmap.add(singleColumn2);
                                tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                                fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);
                            }
                        }

                        if (!dmap.isEmpty()) {
                            tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                            tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                            fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        }
                        if (pr == currentPeriod && yr == currentYear) {
                            hist = true;
                            proj = false;
                        }
                    }
                    startPr = frequencyDivision;
                }

            }
        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    public static List<String> getCommonColumnHeaderForDiscount(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<String>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == 4) {
            commonColumn = Constant.Q + period + StringUtils.EMPTY + year;
            commonHeader = Constant.Q + period + " " + year;
        } else if (frequencyDivision == 2) {
            commonColumn = Constant.S + period + StringUtils.EMPTY + year;
            commonHeader = Constant.S + period + " " + year;
        } else if (frequencyDivision == 12) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    public static List<Object> getProjectionVarianceColumns(CustomTableHeaderDTO tableHeaderDTO, PVSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeader) {
        String freq = projSelDTO.getFrequency();
        String currentProjName = projSelDTO.getCurrentProjectionName();
        String headerIndicator = projSelDTO.getPivotView();
        List<Integer> projList = projSelDTO.getProjIdList();
        Map<Integer, String> priorMap = projSelDTO.getProjectionMap();
        String pivotView = projSelDTO.getPivotView();
        String projOrder = projSelDTO.getProjectionPeriodOrder();
        String variableCategory = projSelDTO.getVariableCategory() != null ? projSelDTO.getVariableCategory() : StringUtils.EMPTY;
        String variable = projSelDTO.getVariables() != null ? projSelDTO.getVariables() : StringUtils.EMPTY;
        List<String> discountNames = new ArrayList<String>(projSelDTO.getDiscountNameList());
        CommonUtils.getHistoryAndProjectionDetails(projSelDTO);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        int historyNum = projSelDTO.getHistoryNum();
        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endYear = projSelDTO.getEndYear();
        int endPeriod = projSelDTO.getEndPeriod();
        int projectionOrder = 0;
        if (projOrder.contains(ASCENDING.getConstant())) {
            projectionOrder = 1;
        } else {
            projectionOrder = 2;
        }
        tableHeaderDTO.setFrequency(freq);
        tableHeaderDTO.setFrequencyDivision(frequencyDivision);
        tableHeaderDTO.setHistoryNum(historyNum);
        tableHeaderDTO.setProjectionNum(projectionNum);
        tableHeaderDTO.setCurrentPeriod(currentPeriod);
        tableHeaderDTO.setCurrentYear(currentYear);
        tableHeaderDTO.setStartYear(startYear);
        tableHeaderDTO.setStartPeriod(startPeriod);
        tableHeaderDTO.setEndYear(endYear);
        tableHeaderDTO.setEndPeriod(endPeriod);
        tableHeaderDTO.setProjectionOrder(projectionOrder);

        fullHeader.setFrequency(freq);
        fullHeader.setFrequencyDivision(frequencyDivision);
        fullHeader.setHistoryNum(historyNum);
        fullHeader.setProjectionNum(projectionNum);
        fullHeader.setCurrentPeriod(currentPeriod);
        fullHeader.setCurrentYear(currentYear);
        fullHeader.setStartYear(startYear);
        fullHeader.setStartPeriod(startPeriod);
        fullHeader.setEndYear(endYear);
        fullHeader.setEndPeriod(endPeriod);
        fullHeader.setProjectionOrder(projectionOrder);

        projSelDTO.setFrequency(freq);
        projSelDTO.setFrequencyDivision(frequencyDivision);
        projSelDTO.setHistoryNum(historyNum);
        projSelDTO.setProjectionNum(projectionNum);
        projSelDTO.setCurrentPeriod(currentPeriod);
        projSelDTO.setCurrentYear(currentYear);
        projSelDTO.setStartYear(startYear);
        projSelDTO.setStartPeriod(startPeriod);
        projSelDTO.setEndYear(endYear);
        projSelDTO.setEndPeriod(endPeriod);

        prepareCommonColumnHeaders(projSelDTO);
        if (pivotView.equals(Constant.PERIOD) || headerIndicator.equals(Constant.PERIOD)) {
            List<String> periodList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            for (int i = 0; i < periodList.size(); i++) {
                List<Object> dmap = new ArrayList<Object>();
                String commonColumn1 = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn1);
                String commonColumn = commonColumn1;
                if (frequencyDivision != 12) {
                    commonColumn = commonColumn1.toUpperCase();
                }
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT + projSelDTO.getCurrentProjId(), currentProjName, String.class);
                tableHeaderDTO.addSingleProjectedColumn(commonColumn + Constant.CURRENT + projSelDTO.getCurrentProjId(), Constant.CURRENT);
                fullHeader.addSingleColumn(commonColumn + Constant.CURRENT + projSelDTO.getCurrentProjId(), commonHeader + " " + currentProjName, String.class);
                dmap.add(commonColumn + Constant.CURRENT + projSelDTO.getCurrentProjId());
                if (!projList.isEmpty()) {
                    for (int j = 0; j < projList.size(); j++) {
                        tableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                        tableHeaderDTO.addSingleProjectedColumn(commonColumn + projList.get(j), StringUtils.EMPTY + projList.get(j));
                        fullHeader.addSingleColumn(commonColumn + projList.get(j), commonHeader + " " + priorMap.get(projList.get(j)), String.class);
                        dmap.add(commonColumn + projList.get(j));
                    }
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                }
            }
        } else {
            setBaseVariables(variableCategory, variable, projSelDTO);
            Map<String, Object> headerMap = new HashMap<String, Object>();
            String commonColumn = StringUtils.EMPTY;

            String commonHeader = StringUtils.EMPTY;

            if (projSelDTO.isVarExFacSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "ExFacValue";
                    commonHeader = "Ex-Factory Sales Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "ExFacVariance";
                    commonHeader = "Ex-Factory Sales variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "ExFacPer";
                    commonHeader = "Ex-Factory Sales % change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
            }

            if (projSelDTO.isVarDemandSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "DemandSalesValue";
                    commonHeader = "Demand Sales Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "DemandSalesVariance";
                    commonHeader = "Demand Sales variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "DemandSalesPer";
                    commonHeader = "Demand Sales  % change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
            }

            if (projSelDTO.isVarInvSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "InvWithValue";
                    commonHeader = "Inventory Withdrawal Sales value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "InvWithVariance";
                    commonHeader = "Inventory Withdrawal Sales variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "InvWithPer";
                    commonHeader = "Inventory Withdrawal Sales % change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
            }

            if (projSelDTO.isVarPerExFacSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "PerExFacValue";
                    commonHeader = "% Of Ex-Factory Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "PerExFacVariance";
                    commonHeader = "% Of  Ex-Factory variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "PerExFacPer";
                    commonHeader = "% Of Ex-Factory % change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
            }

            if (projSelDTO.isVarPerDemandSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "PerDemandSalesValue";
                    commonHeader = "% Of Demand Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "PerDemandSalesVariance";
                    commonHeader = "% Of Demand variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "PerDemandSalesPer";
                    commonHeader = "% Of Demand change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
            }

            if (projSelDTO.isVarPerInvSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "PerInvWithValue";
                    commonHeader = "% Of Inventory Withdrawal value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "PerInvWithVariance";
                    commonHeader = "% Of Inventory Withdrawal variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "PerInvWithPer";
                    commonHeader = "% Of Inventory Withdrawal % change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
            }

            if (projSelDTO.isVarContractsales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "ContractSalesWACValue";
                    commonHeader = "Contract Sales @ WAC Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "ContractSalesWACVar";
                    commonHeader = "Contract Sales @ WAC Variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "ContractSalesWACVarPer";
                    commonHeader = "Contract Sales @ WAC Change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
            }
            if (projSelDTO.isVarContractUnits()) {

                if (projSelDTO.isColValue()) {
                    commonColumn = "ContractUnitsValue";
                    commonHeader = "Contract Units Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "ContractUnitsVar";
                    commonHeader = "Contract Units Variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "ContractUnitsPer";
                    commonHeader = "Contract Units Change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
            }
            if (projSelDTO.isVarDisAmount()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "DiscountAmountValue";
                    commonHeader = "Discount $ Value";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && discountNames.size() > 0) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader,headerMap);
                    }
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "DiscountAmountVar";
                    commonHeader = "Discount $ Variance";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && discountNames.size() > 0) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "DiscountAmountPer";
                    commonHeader = "Discount $ % change";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && discountNames.size() > 0) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }
                }
            }
            if (projSelDTO.isVarDisRate()) {

                if (projSelDTO.isColValue()) {
                    commonColumn = "DiscountSalesValue";
                    commonHeader = "Discount % Value";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && discountNames.size() > 0) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "DiscountSalesVar";
                    commonHeader = "Discount % Variance";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && discountNames.size() > 0) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "DiscountSalesPer";
                    commonHeader = "Discount % % Change";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && discountNames.size() > 0) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }
                }
            }
            if (projSelDTO.isVarRPU()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "RPUValue";
                    commonHeader = "RPU Value";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && discountNames.size() > 0) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "RPUVariance";
                    commonHeader = "RPU Variance";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && discountNames.size() > 0) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "RPUPer";
                    commonHeader = "RPU %Change";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && discountNames.size() > 0) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }
                }
            }
            if (projSelDTO.isVarNetSales()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "NetSalesValue";
                    commonHeader = "Net Sales Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "NetSalesVariance";
                    commonHeader = "Net Sales Variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "NetSalesPer";
                    commonHeader = "Net Sales %Change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
            }
            if (projSelDTO.isVarCOGC()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "COGCValue";
                    commonHeader = "COGS Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "COGCVariance";
                    commonHeader = "COGS Variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "COGCPer";
                    commonHeader = "COGS %Change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
            }
            if (projSelDTO.isVarNetProfit()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "NetProfitValue";
                    commonHeader = "Net Profit Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "NetProfitVariance";
                    commonHeader = "Net Profit Variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "NetProfitPer";
                    commonHeader = "Net Profit %Change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
            }
            List<String> pivotList = new ArrayList<String>();
            List<String> periodList = projSelDTO.getPeriodList();
            List<String> periodListUpper = new ArrayList<String>();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            Map<String, String> periodListMapUpper = new HashMap<String, String>();
            for (int i = 0; i < periodList.size(); i++) {
                String commonColumn1 = periodList.get(i);
                String comHeader = periodListMap.get(commonColumn1);
                String comColumn = commonColumn1;
                if (frequencyDivision != 12) {
                    comColumn = commonColumn1.toUpperCase();
                }
                periodListUpper.add(comColumn);
                pivotList.add(comHeader);
                periodListMapUpper.put(comColumn, comHeader);

            }
            projSelDTO.setPeriodList(periodListUpper);
            projSelDTO.setPeriodListMap(periodListMapUpper);
            projSelDTO.setRightHeader(tableHeaderDTO);
        }
        projSelDTO.setPeriodHeaderList(projSelDTO.getPeriodList());
        List<Object> headerContents = new ArrayList<Object>();
        headerContents.add(tableHeaderDTO);
        projSelDTO.setColumns(CommonUtils.objectListToStringList(tableHeaderDTO.getSingleColumns()));
        return headerContents;
    }

    static void getHistoryAndProjectionDetails(PVSelectionDTO projSelDTO, String frequency) {
        int frequencyDivision = 1;
        int historyStartPeriod = 1;
        int historyEndPeriod = 1;
        int historyEndMonth = 1;
        int historyEndYear = 1;
        int forecastStartPeriod = 1;
        int forecastEndPeriod = 1;
        int projectionStartPeriod = 1;
        int projectionEndPeriod = 1;
        if (frequency.equals(QUARTERLY.getConstant())) {
            frequencyDivision = 4;
            historyStartPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), 3);
            historyEndPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), 3);
            forecastStartPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), 3);
            forecastEndPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), 3);
            projectionStartPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), 3);
            projectionEndPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), 3);
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequencyDivision = 2;
            historyStartPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), 6);
            historyEndPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), 6);
            forecastStartPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), 6);
            forecastEndPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), 6);
            projectionStartPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), 6);
            projectionEndPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), 6);
        } else if (frequency.equals(MONTHLY.getConstant())) {
            frequencyDivision = 12;
            historyStartPeriod = projSelDTO.getForecastDTO().getHistoryStartMonth();
            historyEndPeriod = projSelDTO.getForecastDTO().getHistoryEndMonth();
            forecastStartPeriod = projSelDTO.getForecastDTO().getForecastStartMonth();
            forecastEndPeriod = projSelDTO.getForecastDTO().getForecastEndMonth();
            projectionStartPeriod = projSelDTO.getForecastDTO().getProjectionStartMonth();
            projectionEndPeriod = projSelDTO.getForecastDTO().getProjectionEndMonth();
        } else if (frequency.equals(ANNUALLY.getConstant())) {
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
                historyEndMonth = 12;
                historyEndYear = historyEndYear - 1;
            } else if (historyEndPeriod < 1) {
                historyEndMonth = 12;
                historyEndYear = historyEndYear - 1;
            } else if (frequencyDivision == 2) {
                if (historyEndPeriod == 1) {
                    historyEndMonth = 6;
                } else if (historyEndPeriod == 2) {
                    historyEndMonth = 12;
                }
            } else if (frequencyDivision == 4) {
                if (historyEndPeriod == 1) {
                    historyEndMonth = 3;
                } else if (historyEndPeriod == 2) {
                    historyEndMonth = 6;
                } else if (historyEndPeriod == 3) {
                    historyEndMonth = 9;
                } else if (historyEndPeriod == 4) {
                    historyEndMonth = 12;
                }
            } else if (frequencyDivision == 12) {
                historyEndMonth = historyEndPeriod;
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

    static int getPeriod(int monthNo, int division) {
        return ((monthNo - 1) / division) + 1;
    }

    public static CustomTableHeaderDTO getCalculatedDPRColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        return getCalculatedDiscountProjectionResultsColumns(tableHeaderDTO, projSelDTO, fullHeaderDTO);
    }

    public static CustomTableHeaderDTO getCalculatedDiscountProjectionResultsColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        List<String> discountNames = projSelDTO.getDiscountNameList();
        String projections = projSelDTO.getActualsOrProjections();
        String pivotView = projSelDTO.getPivotView();
        String frequency = projSelDTO.getFrequency();
        CommonUtils.getHistoryAndProjectionDetails(projSelDTO);
        int historyNum = projSelDTO.getHistoryNum();
        int frequencyDivision = projSelDTO.getFrequencyDivision();

        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endYear = projSelDTO.getEndYear();
        int endPeriod = projSelDTO.getEndPeriod();
        int projectionOrder = 0;
        if (projOrder.contains(ASCENDING.getConstant())) {
            projectionOrder = 1;
        } else {
            projectionOrder = 2;
        }
        tableHeaderDTO.setFrequency(frequency);
        tableHeaderDTO.setFrequencyDivision(frequencyDivision);
        tableHeaderDTO.setHistoryNum(historyNum);
        tableHeaderDTO.setProjectionNum(projectionNum);
        tableHeaderDTO.setCurrentPeriod(currentPeriod);
        tableHeaderDTO.setCurrentYear(currentYear);
        tableHeaderDTO.setStartYear(startYear);
        tableHeaderDTO.setStartPeriod(startPeriod);
        tableHeaderDTO.setEndYear(endYear);
        tableHeaderDTO.setEndPeriod(endPeriod);
        tableHeaderDTO.setProjectionOrder(projectionOrder);

        fullHeaderDTO.setFrequency(frequency);
        fullHeaderDTO.setFrequencyDivision(frequencyDivision);
        fullHeaderDTO.setHistoryNum(historyNum);
        fullHeaderDTO.setProjectionNum(projectionNum);
        fullHeaderDTO.setCurrentPeriod(currentPeriod);
        fullHeaderDTO.setCurrentYear(currentYear);
        fullHeaderDTO.setStartYear(startYear);
        fullHeaderDTO.setStartPeriod(startPeriod);
        fullHeaderDTO.setEndYear(endYear);
        fullHeaderDTO.setEndPeriod(endPeriod);
        fullHeaderDTO.setProjectionOrder(projectionOrder);
        prepareCommonColumnHeaders(projSelDTO);
        if (pivotView.contains(DISCOUNT.getConstant())) {
            if (!discountNames.isEmpty()) {
                for (int i = 0; i < discountNames.size(); i++) {
                    String commonColumn = StringUtils.EMPTY;
                    String commonHeader = discountNames.get(i);
                    commonColumn = commonHeader.replace(" ", StringUtils.EMPTY);
                    List<Object> dmap = new ArrayList<Object>();
                    if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("actuals") || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn1 = commonColumn + ACTUALRATE.getConstant();
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                        Object singleColumn2 = commonColumn + ACTUALAMOUNT.getConstant();
                        dmap.add(singleColumn2);
                        tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);
                    }
                    if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains("projections") || projections.contains(PROJECTIONS.getConstant())) {
                        Object singleColumn1 = commonColumn + PROJECTEDRATE.getConstant();
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                        Object singleColumn2 = commonColumn + PROJECTEDAMOUNT.getConstant();
                        dmap.add(singleColumn2);
                        tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);
                    }
                    if (!dmap.isEmpty()) {
                        tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    }

                }
            } else {
                Object doubleCol = "empty";
                Object[] singleCol = {"empty"};
                tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
                tableHeaderDTO.addDoubleColumn(doubleCol, " ");
                tableHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
            }
        } else {

            List<String> periodList = projSelDTO.getPeriodList();

            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            for (int i = 0; i < periodList.size(); i++) {
                List<Object> dmap = new ArrayList<Object>();
                String commonColumn = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn);
                boolean historyFlag = false;
                boolean projectionCol = false;
                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    historyFlag = true;

                    if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn1 = commonColumn + ACTUALRATE.getConstant();
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                        Object singleColumn2 = commonColumn + ACTUALAMOUNT.getConstant();
                        dmap.add(singleColumn2);
                        tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);

                    }
                }
                if (i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex()) {
                    projectionCol = true;
                } else if (historyFlag && (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTIONS.getConstant()))) {
                    projectionCol = true;
                }

                if (i >= projSelDTO.getForecastStartIndex() && i <= projSelDTO.getForecastEndIndex()) {
                    projectionCol = true;
                }

                if (projectionCol) {
                    Object singleColumn1 = commonColumn + PROJECTEDRATE.getConstant();
                    dmap.add(singleColumn1);
                    tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTED_RATE.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTED_RATE.getConstant(), String.class);

                    Object singleColumn2 = commonColumn + PROJECTEDAMOUNT.getConstant();
                    dmap.add(singleColumn2);
                    tableHeaderDTO.addSingleColumn(singleColumn2, PROJECTED_AMOUNT.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + PROJECTED_AMOUNT.getConstant(), String.class);
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
            }

        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    static String loadDiscountHeader(String commonColumn, CustomTableHeaderDTO tableHeaderDTO, PVSelectionDTO selection, List<Object> dmap, String commonHeader) {
        String column = commonColumn;
        List<String> discountNames = new ArrayList<String>(selection.getDiscountNameList());
        List<Integer> projList = selection.getProjIdList();
        Map<Integer, String> priorMap = selection.getProjectionMap();
        if (!discountNames.isEmpty()) {
            for (int i = 0; i < discountNames.size(); i++) {
                commonHeader = discountNames.get(i);
                commonColumn = column + commonHeader.replace(" ", StringUtils.EMPTY);
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT + selection.getCurrentProjId(), selection.getCurrentProjectionName(), String.class);
                dmap.add(commonColumn + Constant.CURRENT + selection.getCurrentProjId());
                if (!projList.isEmpty()) {
                    for (int j = 0; j < projList.size(); j++) {
                        commonColumn = column + commonHeader.replace(" ", StringUtils.EMPTY);
                        tableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                        dmap.add(commonColumn + projList.get(j));
                    }
                }
            }
        }
        return commonColumn;
    }

    public static CustomTableHeaderDTO getSalesProjectionResultsLeftTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("relationshipLevelName", Constant.CUSTOMER_SMALL, String.class);
        fullHeaderDTO.addSingleColumn("relationshipLevelName", Constant.CUSTOMER_SMALL, String.class);
        fullHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        fullHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{"levelValue"});

        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSalesProjectionResultsRightTableColumns(ProjectionSelectionDTO projectionDTO, CustomTableHeaderDTO fullHeaderDTO) {
        LOGGER.info("----Inside getSalesProjectionResultsRightTableColumns() -----");
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getCalculatedSPRColumns(tableHeaderDTO, projectionDTO, fullHeaderDTO);
    }

    public static CustomTableHeaderDTO getCalculatedSPRColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        String projections = projSelDTO.getActualsOrProjections();
        String pivotView = projSelDTO.getPivotView();
        String frequency = projSelDTO.getFrequency();
        CommonUtils.getHistoryAndProjectionDetails(projSelDTO);
        int historyNum = projSelDTO.getHistoryNum();
        int frequencyDivision = projSelDTO.getFrequencyDivision();

        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endYear = projSelDTO.getEndYear();
        int endPeriod = projSelDTO.getEndPeriod();
        int projectionOrder = 0;
        if (projOrder.contains(ASCENDING.getConstant())) {
            projectionOrder = 1;
        } else {
            projectionOrder = 2;
        }
        tableHeaderDTO.setFrequency(frequency);
        tableHeaderDTO.setFrequencyDivision(frequencyDivision);
        tableHeaderDTO.setHistoryNum(historyNum);
        tableHeaderDTO.setProjectionNum(projectionNum);
        tableHeaderDTO.setCurrentPeriod(currentPeriod);
        tableHeaderDTO.setCurrentYear(currentYear);
        tableHeaderDTO.setStartYear(startYear);
        tableHeaderDTO.setStartPeriod(startPeriod);
        tableHeaderDTO.setEndYear(endYear);
        tableHeaderDTO.setEndPeriod(endPeriod);
        tableHeaderDTO.setProjectionOrder(projectionOrder);

        fullHeaderDTO.setFrequency(frequency);
        fullHeaderDTO.setFrequencyDivision(frequencyDivision);
        fullHeaderDTO.setHistoryNum(historyNum);
        fullHeaderDTO.setProjectionNum(projectionNum);
        fullHeaderDTO.setCurrentPeriod(currentPeriod);
        fullHeaderDTO.setCurrentYear(currentYear);
        fullHeaderDTO.setStartYear(startYear);
        fullHeaderDTO.setStartPeriod(startPeriod);
        fullHeaderDTO.setEndYear(endYear);
        fullHeaderDTO.setEndPeriod(endPeriod);
        fullHeaderDTO.setProjectionOrder(projectionOrder);
        prepareCommonColumnHeaders(projSelDTO);
        if (pivotView.contains(VARIABLE.getConstant())) {

            for (int i = 0; i < 5; i++) {

                String commonColumn = StringUtils.EMPTY;
                String commonHeader = StringUtils.EMPTY;
                if (i == 0) {
                    commonColumn = "efs";
                    commonHeader = "Ex-Factory Sales";
                } else if (i == 1) {
                    commonColumn = "dms";
                    commonHeader = "Demand Sales";
                } else if (i == 2) {
                    commonColumn = "iws";
                    commonHeader = "Inventory Withdraw";
                } else if (i == 3) {
                    if (Constant.SALES_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit())) {
                        commonColumn = "csw";
                        commonHeader = "Contract Sales @ WAC";
                    } else {
                        continue;
                    }
                } else if (i == 4) {
                    if ((Constant.UNITS_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit()))) {
                        commonColumn = "uv";
                        commonHeader = Constant.UNIT_VOLUME;
                    } else {
                        continue;
                    }
                }
                
              //  boolean disc = true;
              //  while (disc) {
                    List<Object> dmap = new ArrayList<Object>();
//                     if (i == 0 || (i == 1 && (Constant.SALES_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit()))
//                        || (i == 4 && (Constant.UNITS_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit()))) || (i == 2 && (Constant.SALES_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit()))) || (i == 3 && (Constant.SALES_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit()))))) {
                    if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn = commonColumn + ACTUALS;
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS, String.class);

                    }
                    Object singleColumn = commonColumn + PROJECTIONS;
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS, String.class);

                   // disc = false;
                    if (!dmap.isEmpty()) {
                        tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    }
//                     }
                
              //  }
            }
        } else {

            List<String> periodList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            for (int i = 0; i < periodList.size(); i++) {
                List<Object> dmap = new ArrayList<Object>();
                String commonColumn = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn);
                boolean historyFlag = false;
                boolean projectionCol = false;
                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    historyFlag = true;

                    if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn = commonColumn + ACTUALS;
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, ACTUALS.getConstant());
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS, String.class);
                    }
                }
                if (i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex()) {
                    projectionCol = true;
                } else if (historyFlag && (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTIONS.getConstant()))) {
                    projectionCol = true;
                }

                if (i >= projSelDTO.getForecastStartIndex() && i <= projSelDTO.getForecastEndIndex()) {
                    projectionCol = true;
                }

                if (projectionCol) {
                    Object singleColumn = commonColumn + PROJECTIONS;
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTIONS.getConstant());
                    fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS, String.class);
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
            }

        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(fullHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSalesProjectionResultsLeftTableColumnsChannel(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("levelValue", "Level Name", String.class);
        tableHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        tableHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{"levelValue"});
        fullHeaderDTO.addSingleColumn("levelValue", "Level Name", String.class);
        fullHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        fullHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{"levelValue"});
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSalesProjectionResultsRightTableColumnsChannel(ProjectionSelectionDTO projectionDTO, SessionDTO session, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getSalesProjectionResultsCalculatedColumnsChannel(tableHeaderDTO, projectionDTO, fullHeaderDTO);
    }

    public static CustomTableHeaderDTO getSupplementalLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO, String group) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object[] singleCol = {Constant.GROUP};
        Object[] singleCheckCol = {Constant.CHECK};
        tableHeaderDTO.addSingleColumn(Constant.CHECK, " ", Boolean.class);
        tableHeaderDTO.addSingleColumn(singleCol[0], group, String.class);
        fullHeaderDTO.addSingleColumn(Constant.CHECK, " ", Boolean.class);
        fullHeaderDTO.addSingleColumn(singleCol[0], group, String.class);

        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSupplementalrightTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO, SessionDTO session) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        String actualDiscount = "Actual Discount";
        Object actualdisColn = "actualdiscount";
        Object[] singleActualDiscount = {"actualdiscount"};
        tableHeaderDTO.addSingleColumn(actualdisColn, " ", String.class);
        tableHeaderDTO.addDoubleColumn(actualdisColn, actualDiscount);
        tableHeaderDTO.addDoubleHeaderMap(actualdisColn, singleActualDiscount);
        fullHeaderDTO.addSingleColumn(actualdisColn, actualDiscount, String.class);

        String actualContractEndDate = "Contract End Date";
        Object contEndDateColumn = "contractEndDate";
        Object[] singlecontEndDateColumn = {contEndDateColumn};
        tableHeaderDTO.addSingleColumn(contEndDateColumn, " ", String.class);
        tableHeaderDTO.addDoubleColumn(contEndDateColumn, actualContractEndDate);
        tableHeaderDTO.addDoubleHeaderMap(contEndDateColumn, singlecontEndDateColumn);
        fullHeaderDTO.addSingleColumn(contEndDateColumn, actualContractEndDate, String.class);

        List<String> periods = new ArrayList<String>();
        boolean forecastDateLarger = false;
        int startPr = getQuarter(projSelDTO.getProjectionStartMonth()+1);
        int endYear = 0;
        int projEndYear = projSelDTO.getProjectionEndYear();
        int projEndPeriod = getQuarter(projSelDTO.getProjectionEndMonth());
        int endPeriod = 0;
        if (projSelDTO.getProjectionEndDate().after(projSelDTO.getForecastEndDate())) {
            endYear = projSelDTO.getProjectionEndYear();
            endPeriod = getQuarter(projSelDTO.getProjectionEndMonth());
        } else {
            forecastDateLarger = true;
            endYear = projSelDTO.getForecastEndYear();
            endPeriod = getQuarter(projSelDTO.getForecastEndMonth());
        }

        int lastPr = 4;
        String disable = StringUtils.EMPTY;

        for (int yr = projSelDTO.getProjectionStartYear(); yr <= endYear; yr++) {
            if (yr == endYear) {
                lastPr = endPeriod;
            }

            for (int pr = startPr; pr <= lastPr; pr++) {

                List<Object> dmap = new ArrayList<Object>();
                if (forecastDateLarger && pr > projEndPeriod && yr >= projEndYear) {
                    disable = "disable";
                }
                List<String> common = getCommonColumnHeader(4, yr, pr);
                String commonColumn = common.get(0);
                String commonHeader = common.get(1);
                periods.add(commonHeader);
                periods.add(commonHeader);

                projSelDTO.setPeriodList(periods);
                List supplementalColumns = projSelDTO.getVariableList();
                int size = supplementalColumns.size();
                for (int i = 0; i < size; i++) {
                    String suppColumn = String.valueOf(supplementalColumns.get(i));
                    String suppHeader = suppColumn.replace(" ", StringUtils.EMPTY).trim();
                    Object supplemental = commonColumn + suppHeader + disable;
                    dmap.add(supplemental);
                    if (suppColumn.equals(PARITY.getConstant())) {
                        tableHeaderDTO.addSingleColumn(supplemental, suppColumn, Boolean.class);
                        fullHeaderDTO.addSingleColumn(supplemental, commonHeader + " " + suppColumn, Boolean.class);
                    } else {
                        tableHeaderDTO.addSingleColumn(supplemental, suppColumn, String.class);
                        fullHeaderDTO.addSingleColumn(supplemental, commonHeader + " " + suppColumn, String.class);
                    }
                }
                if (!dmap.isEmpty()) {

                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    Object[] singleMethodCol1 = dmap.toArray();
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, singleMethodCol1);
                }
                startPr = 1;
            }
        }
        return tableHeaderDTO;

    }

    public static int getQuarter(int projectionStartMonth) {
        if (projectionStartMonth <= 3) {
            projectionStartMonth = 1;
        } else if (projectionStartMonth <= 6) {
            projectionStartMonth = 2;
        } else if (projectionStartMonth <= 9) {
            projectionStartMonth = 3;
        } else if (projectionStartMonth <= 12) {
            projectionStartMonth = 4;
        }
        return projectionStartMonth;

    }

    public static CustomTableHeaderDTO getSalesProjectionResultsCalculatedColumnsChannel(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        String projections = projSelDTO.getActualsOrProjections();
        String pivotView = projSelDTO.getPivotView();
        String frequency = projSelDTO.getFrequency();
        CommonUtils.getHistoryAndProjectionDetails(projSelDTO);
        int historyNum = projSelDTO.getHistoryNum();
        int frequencyDivision = projSelDTO.getFrequencyDivision();

        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endYear = projSelDTO.getEndYear();
        int endPeriod = projSelDTO.getEndPeriod();
        int projectionOrder = 0;
        if (projOrder.contains(ASCENDING.getConstant())) {
            projectionOrder = 1;
        } else {
            projectionOrder = 2;
        }
        tableHeaderDTO.setFrequency(frequency);
        tableHeaderDTO.setFrequencyDivision(frequencyDivision);
        tableHeaderDTO.setHistoryNum(historyNum);
        tableHeaderDTO.setProjectionNum(projectionNum);
        tableHeaderDTO.setCurrentPeriod(currentPeriod);
        tableHeaderDTO.setCurrentYear(currentYear);
        tableHeaderDTO.setStartYear(startYear);
        tableHeaderDTO.setStartPeriod(startPeriod);
        tableHeaderDTO.setEndYear(endYear);
        tableHeaderDTO.setEndPeriod(endPeriod);
        tableHeaderDTO.setProjectionOrder(projectionOrder);

        fullHeaderDTO.setFrequency(frequency);
        fullHeaderDTO.setFrequencyDivision(frequencyDivision);
        fullHeaderDTO.setHistoryNum(historyNum);
        fullHeaderDTO.setProjectionNum(projectionNum);
        fullHeaderDTO.setCurrentPeriod(currentPeriod);
        fullHeaderDTO.setCurrentYear(currentYear);
        fullHeaderDTO.setStartYear(startYear);
        fullHeaderDTO.setStartPeriod(startPeriod);
        fullHeaderDTO.setEndYear(endYear);
        fullHeaderDTO.setEndPeriod(endPeriod);
        fullHeaderDTO.setProjectionOrder(projectionOrder);
        prepareCommonColumnHeaders(projSelDTO);
        if (pivotView.contains(VARIABLE.getConstant())) {
            for (int i = 0; i < 3; i++) {
                String commonColumn = StringUtils.EMPTY;
                String commonHeader = StringUtils.EMPTY;
                if (i == 0) {
                    commonColumn = "gts";
                    commonHeader = "Gross Trade Sales";
                } else if (i == 1 && (Constant.SALES_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit()))) {
                    commonColumn = "csw";
                    commonHeader = "Contract Sales @ WAC";
                } else if (i == 2 && (Constant.UNITS_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit()))) {
                    commonColumn = "uv";
                    commonHeader = Constant.UNIT_VOLUME;
                }
                int j = -1;
                List<Object> dmap = new ArrayList<Object>();
                if (i == 0 || (i == 1 && (Constant.SALES_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit()))
                        || (i == 2 && (Constant.UNITS_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit()))))) {

                    if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                    }

                    Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);

                    if (!dmap.isEmpty()) {
                        tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                        fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                        fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    }
                }
            }
        } else {

            List<String> periodList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            for (int i = 0; i < periodList.size(); i++) {
                List<Object> dmap = new ArrayList<Object>();
                String commonColumn = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn);
                boolean historyFlag = false;
                boolean projectionCol = false;
                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    historyFlag = true;

                    if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, ACTUALS.getConstant());
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                    }
                }
                if (i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex()) {
                    projectionCol = true;
                } else if (historyFlag && (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTIONS.getConstant()))) {
                    projectionCol = true;
                }

                if (i >= projSelDTO.getForecastStartIndex() && i <= projSelDTO.getForecastEndIndex()) {
                    projectionCol = true;
                }

                if (projectionCol) {
                    Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTIONS.getConstant());
                    fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
            }

        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(tableHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    public static List<Object> getCalculatedDiscountProjectionColumnsForCH(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("Entering getCalculatedDiscountProjectionColumns ");
        CustomTableHeaderDTO tableHeader = new CustomTableHeaderDTO();
        CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
        try {
            String discountName = projSelDTO.getDiscountName();
            String actualsOrProjections = projSelDTO.getActualsOrProjections();
            String type = StringUtils.EMPTY;
            String discType = projSelDTO.getDiscountType();
            String projType = projSelDTO.getProjectionType();
            if (PROJECTED.getConstant().equalsIgnoreCase(projType)) {
                if ("$".equals(discType)) {
                    type = "Amount";
                } else {
                    type = "Rate";
                }
            } else {
                if ("$".equals(discType)) {
                    type = "Amount";
                } else {
                    type = "Rate";
                }
            }
            CommonUtils.getHistoryAndProjectionDetails(projSelDTO);
            prepareCommonColumnHeaders(projSelDTO);
            List<String> columnsList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            String discountColumnName = discountName.replaceAll(" ", StringUtils.EMPTY);
            List<Object> tmap = new ArrayList<Object>();
            for (int i = 0; i < columnsList.size(); i++) {
                List<Object> dmap = new ArrayList<Object>();
                String column = columnsList.get(i);
                String commonColumn = discountColumnName + column;
                String commonHeader = periodListMap.get(column);
                boolean historyFlag = false;
                boolean historyActualFlag = false;
                boolean projectionFlag = false;
                boolean projectionCol = false;
                boolean forecastFlag = false;
                Object singleColumn = commonColumn;
                Object singleAccount = commonColumn;
                Object singleProduct = commonColumn;

                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    historyFlag = true;
                    if (actualsOrProjections.contains(Constant.BOTH) || actualsOrProjections.contains(ACTUALS.getConstant())) {
                        historyActualFlag = true;
                        singleColumn = commonColumn + "Actual" + type;
                        dmap.add(singleColumn);
                        tableHeader.addSingleColumn(singleColumn, "Actual " + type, String.class);

                        excelHeader.addSingleColumn(singleColumn, discountName + " " + commonHeader + " Actual " + type, String.class);
                        tableHeader.addSingleHistoryColumn(singleColumn, "Actual " + type);
                    }
                }

                if (i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex()) {
                    projectionFlag = true;
                    projectionCol = true;
                    if (historyActualFlag) {
                        tableHeader.addSingleHistoryColumn(singleColumn, "Actual " + type);
                    }
                } else if (historyFlag && (actualsOrProjections.contains(Constant.BOTH) || actualsOrProjections.contains(PROJECTIONS.getConstant()))) {
                    projectionCol = true;
                }

                if (i >= projSelDTO.getForecastStartIndex() && i <= projSelDTO.getForecastEndIndex()) {
                    forecastFlag = true;
                    projectionCol = true;
                }

                if (projectionCol) {
                    singleColumn = commonColumn + PROJECTED.getConstant() + type;
                    tableHeader.addSingleColumn(singleColumn, "Projected " + type, String.class);
                    excelHeader.addSingleColumn(singleColumn, discountName + " " + commonHeader + " Projected " + type, String.class);
                    dmap.add(singleColumn);
                    if (PROJECTED.getConstant().equalsIgnoreCase(projType)) {
                        singleProduct = commonColumn + Constant.PRODUCT_GROWTH;
                        singleAccount = commonColumn + Constant.ACCOUNT_GROWTH;
                        dmap.add(singleProduct);
                        dmap.add(singleAccount);
                        tableHeader.addSingleColumn(singleProduct, Constant.PRODUCT_GROWTH, String.class);
                        tableHeader.addSingleColumn(singleAccount, Constant.ACCOUNT_GROWTH, String.class);
                        excelHeader.addSingleColumn(singleProduct, Constant.PRODUCT_GROWTH, String.class);
                        excelHeader.addSingleColumn(singleAccount, Constant.ACCOUNT_GROWTH, String.class);
                    }

                    if (historyFlag) {
                        tableHeader.addSingleHistoryColumn(singleColumn, "Projected " + type);
                    }
                    if (projectionFlag) {

                        tableHeader.addSingleProjectedColumn(singleColumn, "Projected " + type);
                        if (PROJECTED.getConstant().equalsIgnoreCase(projType)) {
                            tableHeader.addSingleProjectedColumn(singleProduct, Constant.PRODUCT_GROWTH);
                            tableHeader.addSingleProjectedColumn(singleAccount, Constant.ACCOUNT_GROWTH);
                            excelHeader.addSingleProjectedColumn(singleProduct, Constant.PRODUCT_GROWTH);
                            excelHeader.addSingleProjectedColumn(singleAccount, Constant.ACCOUNT_GROWTH);
                        }
                    }
                    if (forecastFlag) {
                        tableHeader.addSingleForecastColumn(singleColumn, "Projected " + type);
                        if (PROJECTED.getConstant().equalsIgnoreCase(projType)) {
                            tableHeader.addSingleForecastColumn(singleProduct, Constant.PRODUCT_GROWTH);
                            tableHeader.addSingleForecastColumn(singleAccount, Constant.ACCOUNT_GROWTH);
                            excelHeader.addSingleForecastColumn(singleProduct, Constant.PRODUCT_GROWTH);
                            excelHeader.addSingleForecastColumn(singleAccount, Constant.ACCOUNT_GROWTH);
                        }
                    }
                }

                if (!dmap.isEmpty()) {
                    tmap.add(commonColumn);
                    tableHeader.addDoubleColumn(commonColumn, commonHeader);
                    tableHeader.addDoubleHeaderMap(commonColumn, dmap.toArray());

                    excelHeader.addDoubleColumn(commonColumn, commonHeader);
                    excelHeader.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    if (historyFlag) {
                        tableHeader.addDoubleHistoryColumn(commonColumn, commonHeader);
                        tableHeader.addDoubleHistoryHeaderMap(commonColumn, dmap.toArray());
                    }
                    if (projectionFlag) {
                        tableHeader.addDoubleProjectedColumn(commonColumn, commonHeader);
                        tableHeader.addDoubleProjectedHeaderMap(commonColumn, dmap.toArray());
                    }
                    if (forecastFlag) {
                        tableHeader.addDoubleForecastColumn(commonColumn, commonHeader);
                        tableHeader.addDoubleForecastHeaderMap(commonColumn, dmap.toArray());
                    }

                }
            }

            if (!tmap.isEmpty()) {
                tableHeader.addTripleColumn(discountColumnName, discountName);
                tableHeader.addTripleHeaderMap(discountColumnName, tmap.toArray());
                excelHeader.addTripleColumn(discountColumnName, discountName);
                excelHeader.addTripleHeaderMap(discountColumnName, tmap.toArray());
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending getCalculatedDiscountProjectionColumns ");
        List<Object> headerContents = new ArrayList<Object>();
        List<Object> sd = tableHeader.getSingleColumns();

        headerContents.add(tableHeader);
        headerContents.add(excelHeader);
        return headerContents;
    }

    public static CustomTableHeaderDTO getAHSummeryTabLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();

        tableHeaderDTO.addSingleColumn(Constant.LEVELNAME, "  ", String.class);

        tableHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        tableHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{Constant.LEVELNAME});

        fullHeaderDTO.addSingleColumn(Constant.LEVELNAME, "  ", String.class);

        fullHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        fullHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{Constant.LEVELNAME});
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getAlternateHistoryRightTableColumns(ProjectionSelectionDTO projectionDTO, SessionDTO session, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getAlternateHistoryCalculatedColumns(tableHeaderDTO, projectionDTO, fullHeaderDTO, session);
    }

    public static CustomTableHeaderDTO getAlternateHistoryCalculatedColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO, SessionDTO session) {
        String projOrder = projSelDTO.getProjectionOrder();
        String projections = projSelDTO.getActualsOrProjections();
        String pivotView = projSelDTO.getPivotView();
        String frequency = projSelDTO.getFrequency();
        CommonUtils.getHistoryAndProjectionDetails(projSelDTO);
        int historyNum = projSelDTO.getHistoryNum();
        int frequencyDivision = projSelDTO.getFrequencyDivision();

        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endYear = projSelDTO.getEndYear();
        int endPeriod = projSelDTO.getEndPeriod();
        int projectionOrder = 0;
        if (projOrder.contains(ASCENDING.getConstant())) {
            projectionOrder = 1;
        } else {
            projectionOrder = 2;
        }
        tableHeaderDTO.setFrequency(frequency);
        tableHeaderDTO.setFrequencyDivision(frequencyDivision);
        tableHeaderDTO.setHistoryNum(historyNum);
        tableHeaderDTO.setProjectionNum(projectionNum);
        tableHeaderDTO.setCurrentPeriod(currentPeriod);
        tableHeaderDTO.setCurrentYear(currentYear);
        tableHeaderDTO.setStartYear(startYear);
        tableHeaderDTO.setStartPeriod(startPeriod);
        tableHeaderDTO.setEndYear(endYear);
        tableHeaderDTO.setEndPeriod(endPeriod);
        tableHeaderDTO.setProjectionOrder(projectionOrder);

        fullHeaderDTO.setFrequency(frequency);
        fullHeaderDTO.setFrequencyDivision(frequencyDivision);
        fullHeaderDTO.setHistoryNum(historyNum);
        fullHeaderDTO.setProjectionNum(projectionNum);
        fullHeaderDTO.setCurrentPeriod(currentPeriod);
        fullHeaderDTO.setCurrentYear(currentYear);
        fullHeaderDTO.setStartYear(startYear);
        fullHeaderDTO.setStartPeriod(startPeriod);
        fullHeaderDTO.setEndYear(endYear);
        fullHeaderDTO.setEndPeriod(endPeriod);
        fullHeaderDTO.setProjectionOrder(projectionOrder);
        projSelDTO.setFunctionality("Alternate-History");
        prepareCommonColumnHeaders(projSelDTO);
        List<String> periodList = projSelDTO.getPeriodList();

        Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
        for (int i = 0; i < periodList.size(); i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = periodList.get(i);

            String commonHeader = periodListMap.get(commonColumn);
            boolean historyFlag = false;
            boolean projectionCol = false;
            if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                if ("Sales Projection".equals(session.getForecastName())) {
                    if (!projSelDTO.isIsSumarry()) {
                        if (projSelDTO.getVariableList().contains(Constants.LabelConstants.SALES.getConstant())) {
                            Object singleColumn = commonColumn + "actualSales";
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACTUAL_SALES, String.class);
                            tableHeaderDTO.addSingleHistoryColumn(singleColumn, "actualSales");
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                        }
                        if (projSelDTO.getVariableList().contains(Constants.LabelConstants.UNITS.getConstant())) {
                            Object singleColumn1 = commonColumn + "actualUnits";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, "Actual Units", String.class);
                            tableHeaderDTO.addSingleProjectedColumn(singleColumn1, ACTUALS.getConstant());
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUALS.getConstant(), String.class);
                        }

                    } else {

                        Object singleColumn1 = commonColumn + "-ActualUnits";
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, "Actual Units", String.class);
                        tableHeaderDTO.addSingleProjectedColumn(singleColumn1, ACTUALS.getConstant());
                        fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUALS.getConstant(), String.class);
                    }
                } else if (TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) {
                    Object singleColumn = commonColumn + "actualPayments";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Actual Payments", String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, "actualPayments");
                    fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                }
            }
            if (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTIONS.getConstant())) {
                if ("Sales Projection".equals(session.getForecastName())) {
                    if (!projSelDTO.isIsSumarry()) {
                        if (projSelDTO.getVariableList().contains(Constants.LabelConstants.SALES.getConstant())) {
                            Object singleColumn = commonColumn + "projectionSales";
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, "Projection Sales", String.class);
                            tableHeaderDTO.addSingleHistoryColumn(singleColumn, "projectionSales");
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                        }
                        if (projSelDTO.getVariableList().contains(Constants.LabelConstants.UNITS.getConstant())) {
                            Object singleColumn1 = commonColumn + "projectionUnits";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, "Projection Units", String.class);
                            tableHeaderDTO.addSingleProjectedColumn(singleColumn1, PROJECTIONS.getConstant());
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                        }
                    } else {

                        Object singleColumn1 = commonColumn + "-ProjectionUnits";
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, "Projection Units", String.class);
                        tableHeaderDTO.addSingleProjectedColumn(singleColumn1, PROJECTIONS.getConstant());
                        fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                    }
                } else if (TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) {
                    Object singleColumn = commonColumn + "projectionPayments";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, "Projection Payments", String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, "projectionPayments");
                    fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                }
            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }
        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(tableHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getAlternateHistoryRightTableColumnsForDiscount(ProjectionSelectionDTO projectionDTO, SessionDTO session, CustomTableHeaderDTO fullHeaderDTO, String rsName) {

        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();

        return getAlternateHistoryCalculatedColumnsForDiscount(tableHeaderDTO, projectionDTO, fullHeaderDTO, session, rsName);
    }

    public static CustomTableHeaderDTO getAlternateHistoryCalculatedColumnsForDiscount(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO, SessionDTO session, String rsName) {
        String projOrder = projSelDTO.getProjectionOrder();
        String projections = projSelDTO.getActualsOrProjections();
        String pivotView =  projSelDTO.getVariableView();
        String frequency = projSelDTO.getFrequency();
        CommonUtils.getHistoryAndProjectionDetails(projSelDTO);
        int historyNum = projSelDTO.getHistoryNum();
        int frequencyDivision = projSelDTO.getFrequencyDivision();

        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endYear = projSelDTO.getEndYear();
        int endPeriod = projSelDTO.getEndPeriod();
        int projectionOrder = 0;
        if (projOrder.contains(ASCENDING.getConstant())) {
            projectionOrder = 1;
        } else {
            projectionOrder = 2;
        }
        tableHeaderDTO.setFrequency(frequency);
        tableHeaderDTO.setFrequencyDivision(frequencyDivision);
        tableHeaderDTO.setHistoryNum(historyNum);
        tableHeaderDTO.setProjectionNum(projectionNum);
        tableHeaderDTO.setCurrentPeriod(currentPeriod);
        tableHeaderDTO.setCurrentYear(currentYear);
        tableHeaderDTO.setStartYear(startYear);
        tableHeaderDTO.setStartPeriod(startPeriod);
        tableHeaderDTO.setEndYear(endYear);
        tableHeaderDTO.setEndPeriod(endPeriod);
        tableHeaderDTO.setProjectionOrder(projectionOrder);

        fullHeaderDTO.setFrequency(frequency);
        fullHeaderDTO.setFrequencyDivision(frequencyDivision);
        fullHeaderDTO.setHistoryNum(historyNum);
        fullHeaderDTO.setProjectionNum(projectionNum);
        fullHeaderDTO.setCurrentPeriod(currentPeriod);
        fullHeaderDTO.setCurrentYear(currentYear);
        fullHeaderDTO.setStartYear(startYear);
        fullHeaderDTO.setStartPeriod(startPeriod);
        fullHeaderDTO.setEndYear(endYear);
        fullHeaderDTO.setEndPeriod(endPeriod);
        fullHeaderDTO.setProjectionOrder(projectionOrder);
        prepareCommonColumnHeaders(projSelDTO);

        List<String> periodList = projSelDTO.getPeriodList();

        Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
        if ("Variable".equalsIgnoreCase(pivotView)) {
            String commonColumn = StringUtils.EMPTY;
            String commonHeader = StringUtils.EMPTY;

            commonColumn = "payment";
            commonHeader = "Payment";

            boolean disc = true;
            while (disc) {
                List<Object> dmap = new ArrayList<Object>();
                if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                    Object singleColumn = commonColumn + ACTUALS;
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                    fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS, String.class);

                }
                Object singleColumn = commonColumn + PROJECTIONS;
                dmap.add(singleColumn);
                tableHeaderDTO.addSingleColumn(singleColumn, PROJECTIONS.getConstant(), String.class);
                fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS, String.class);

                disc = false;
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
            }
        } else {
            for (int i = 0; i < periodList.size(); i++) {
                List<Object> dmap = new ArrayList<Object>();
                String commonColumn = periodList.get(i);

                String commonHeader = periodListMap.get(commonColumn);
                boolean historyFlag = false;
                boolean projectionCol = false;
                if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                    if ("Sales Projection".equals(session.getForecastName())) {
                        Object singleColumn = commonColumn + Constant.ACTUALSALES;
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACTUAL_SALES, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.ACTUALSALES);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " +Constant.ACTUAL_SALES, String.class);

                        singleColumn = commonColumn + Constant.ACTUALUNITS;
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Actual Units", String.class);
                        tableHeaderDTO.addSingleProjectedColumn(singleColumn, ACTUALS.getConstant());
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " Actual Units", String.class);
                    } else if (TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) {

                        Object singleColumn = rsName + commonColumn + "ActualAmount";

                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Actual Payments", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, "ActualAmount");
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader +" Actual Payments", String.class);
                    }
                }
                if (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTIONS.getConstant())) {
                    if ("Sales Projection".equals(session.getForecastName())) {
                        Object singleColumn = commonColumn + "ProjectionSales";
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projection Sales", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, "projectionSales");
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " Projection Sales", String.class);

                        singleColumn = commonColumn + "projectionUnits";
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projection Units", String.class);
                        tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTIONS.getConstant());
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " Projection Units", String.class);
                    } else if (TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) {
                        Object singleColumn = rsName + commonColumn + "ProjectedAmount";

                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projection Payments", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, "ProjectedAmount");
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " Projection Payments", String.class);
                    }
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
            }
        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(tableHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getAltHistAllocLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();

        tableHeaderDTO.addSingleColumn(Constant.CHECKRECORD, " ", Boolean.class);
        tableHeaderDTO.addSingleColumn("contractNo", "Contract No.", String.class);
        tableHeaderDTO.addSingleColumn("contractName", "Contract Name", String.class);
        tableHeaderDTO.addSingleColumn(Constant.CUSTOMER_NO, "Customer No.", String.class);
        tableHeaderDTO.addSingleColumn(Constant.CUSTOMER_NAME, "Customer Name", String.class);
        tableHeaderDTO.addSingleColumn(Constant.ITEM_NO, "Item No.", String.class);
        tableHeaderDTO.addSingleColumn("itemName", "Item Name", String.class);

       

//        tableHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
//        tableHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{"contractNo", "contractName", Constant.CUSTOMER_NO, Constant.CUSTOMER_NAME, Constant.ITEM_NO, "itemName"});

        fullHeaderDTO.addSingleColumn("contractNo", "Contract No", String.class);
        fullHeaderDTO.addSingleColumn("contractName", "Contract Name", String.class);
        fullHeaderDTO.addSingleColumn(Constant.CUSTOMER_NO, "Company No", String.class);
        fullHeaderDTO.addSingleColumn(Constant.CUSTOMER_NAME, "Company Name", String.class);
        fullHeaderDTO.addSingleColumn(Constant.ITEM_NO, "Item No", String.class);
        fullHeaderDTO.addSingleColumn("itemName", "Item Name", String.class);

//        fullHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
//        fullHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{Constant.CHECKRECORD, "contractNo", "contractName", Constant.CUSTOMER_NO, Constant.CUSTOMER_NAME, Constant.ITEM_NO, "itemName"});
        return tableHeaderDTO;
   
        
    }

    public static CustomTableHeaderDTO getAltHistAllocRightTableColumns(ProjectionSelectionDTO projectionSelection, SessionDTO session, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
//        CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
        return getCalcAltHistAllocRightTableColumns(tableHeaderDTO, projectionSelection, fullHeaderDTO);
    }
    
    public static CustomTableHeaderDTO getCalcAltHistAllocRightTableColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        String projections = Constant.BOTH;
        String pivotView = projSelDTO.getPivotView();
        String frequency = projSelDTO.getFrequency();
        CommonUtils.getHistoryAndProjectionDetails(projSelDTO);
        int historyNum = projSelDTO.getHistoryNum();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String actual = StringUtils.EMPTY;
        String projection = StringUtils.EMPTY;

        int projectionNum = projSelDTO.getProjectionNum();
        int currentPeriod = projSelDTO.getCurrentPeriod();
        int currentYear = projSelDTO.getCurrentYear();
        int startYear = projSelDTO.getStartYear();
        int startPeriod = projSelDTO.getStartPeriod();
        int endYear = projSelDTO.getEndYear();
        int endPeriod = projSelDTO.getEndPeriod();
        int projectionOrder = 0;

        tableHeaderDTO.setFrequency(frequency);
        tableHeaderDTO.setFrequencyDivision(frequencyDivision);
        tableHeaderDTO.setHistoryNum(historyNum);
        tableHeaderDTO.setProjectionNum(projectionNum);
        tableHeaderDTO.setCurrentPeriod(currentPeriod);
        tableHeaderDTO.setCurrentYear(currentYear);
        tableHeaderDTO.setStartYear(startYear);
        tableHeaderDTO.setStartPeriod(startPeriod);
        tableHeaderDTO.setEndYear(endYear);
        tableHeaderDTO.setEndPeriod(endPeriod);
        tableHeaderDTO.setProjectionOrder(projectionOrder);

        fullHeaderDTO.setFrequency(frequency);
        fullHeaderDTO.setFrequencyDivision(frequencyDivision);
        fullHeaderDTO.setHistoryNum(historyNum);
        fullHeaderDTO.setProjectionNum(projectionNum);
        fullHeaderDTO.setCurrentPeriod(currentPeriod);
        fullHeaderDTO.setCurrentYear(currentYear);
        fullHeaderDTO.setStartYear(startYear);
        fullHeaderDTO.setStartPeriod(startPeriod);
        fullHeaderDTO.setEndYear(endYear);
        fullHeaderDTO.setEndPeriod(endPeriod);
        fullHeaderDTO.setProjectionOrder(projectionOrder);
        prepareCommonColumnHeaders(projSelDTO);

        List<String> periodList = projSelDTO.getPeriodList();

        Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
        if (TabNameUtil.DISCOUNT_PROJECTION.equals(projSelDTO.getScreenName())) {
            actual = ACTUAL_PAYMENTS.getConstant();
            projection = PROJECTED_PAYMENTS.getConstant();
        } else {
            actual = ACTUAL_UNITS.getConstant();
            projection = PROJECTED_UNITS.getConstant();
        }
        for (int i = 0; i < periodList.size(); i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = periodList.get(i);
            String commonHeader = periodListMap.get(commonColumn);
            boolean historyFlag = false;
            boolean projectionCol = false;
            if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUAL_PAYMENTS.getConstant())) {
                Object singleAllColumn = commonColumn + ACTALLOCPER.getConstant();
                dmap.add(singleAllColumn);
                tableHeaderDTO.addSingleColumn(singleAllColumn, ALLOCATIONPER.getConstant(), String.class);
                tableHeaderDTO.addSingleHistoryColumn(singleAllColumn, ALLOCATIONPER.getConstant());
                fullHeaderDTO.addSingleColumn(singleAllColumn, commonHeader + " " + ALLOCATIONPER.getConstant(), String.class);
                Object singleColumn = commonColumn + actual;
                dmap.add(singleColumn);
                tableHeaderDTO.addSingleColumn(singleColumn, actual, String.class);
                tableHeaderDTO.addSingleHistoryColumn(singleColumn, actual);
                fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + actual, String.class);
            }
            if (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTED_PAYMENTS.getConstant())) {
                Object singleAllColumn = commonColumn + PRJALLOCPER.getConstant();
                dmap.add(singleAllColumn);
                tableHeaderDTO.addSingleColumn(singleAllColumn, ALLOCATIONPER.getConstant(), String.class);
                tableHeaderDTO.addSingleHistoryColumn(singleAllColumn, ALLOCATIONPER.getConstant());
                fullHeaderDTO.addSingleColumn(singleAllColumn, commonHeader + " " + ALLOCATIONPER.getConstant(), String.class);
                Object singleColumn = commonColumn + projection;
                dmap.add(singleColumn);
                tableHeaderDTO.addSingleColumn(singleColumn, projection, String.class);
                tableHeaderDTO.addSingleProjectedColumn(singleColumn, projection);
                fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + projection, String.class);
            }
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }
        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(tableHeaderDTO.getSingleColumns()));
        return tableHeaderDTO;
    }

    public static List<String> getCommonColumnHeaderForAlternateHistory(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<String>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == 4) {
            commonColumn = Constant.Q_SMALL + period + "-" + year;
            commonHeader = Constant.Q + period + " " + year;
        } else if (frequencyDivision == 2) {
            commonColumn = Constant.S_SMALL + period + "-" + year;
            commonHeader = Constant.S + period + " " + year;
        } else if (frequencyDivision == 12) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

}
