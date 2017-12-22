/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getCommonColumnHeader;
import com.stpl.app.gtnforecasting.dto.ForecastDTO;
import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DiscountProjectionLogic;
import com.stpl.app.gtnforecasting.logic.SalesProjectionLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.isInteger;
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
import com.stpl.ifs.ui.util.NumericConstants;
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
    private static final String[] quarters = new String[]{" Q1", " Q2", " Q3", " Q4"};
    /**
     * The annual.
     */

    /**
     * The semi annual.
     */
    /**
     * The months.
     */
    private static final String[] months = new String[]{" Jan", " Feb", " Mar", " Apr", " May", " Jun", " Jul", " Aug", " Sep", " Oct", " Nov", " Dec"};
    /**
     * The semi annuals.
     */
    private static final String[] semiAnnuals = new String[]{" SA1", " SA2"};
    public static final String PROJECTIONS1 = "projections";
    /**
     * The month.
     */

    /**
     * The supplemental columns.
     */
    public Object[] supplementalColumns = new Object[]{"customer",
        Constant.ACTUALS_PROPERTY, PROJECTIONS1};
    /**
     * The supplemental header.
     */
    public String[] supplementalHeader = new String[]{Constant.CUSTOMER_SMALL,
        ACTUALS.getConstant(), PROJECTIONS.getConstant()};
    /**
     * The supplemental double columns.
     */
    public Object[] supplementalDoubleColumns = new Object[]{"ndc",
        "quarters"};
    public static final String Q1_2014 = "Q1 2014";
    /**
     * The supplemental double header.
     */
    public String[] supplementalDoubleHeader = new String[]{" ", Q1_2014};
    public static final String LEVEL1 = "level";
    /**
     * -- DP TRIPLE HEADER --
     */
    /**
     * The Constant DP_LEFT INNER COLUMNS.
     */
    public static final Object[] dpLeftFirstColumns = new Object[]{Constant.CHECK, LEVEL1, Constant.GROUP};
    public static final String LEVEL_NAME1 = "Level Name";
    /**
     * The Constant DP_LEFT INNER HEADER.
     */
    public static final String[] dpLeftFirstHeaders = new String[]{" ", LEVEL_NAME1, Constant.GROUPFCAPS};
    public static final String HISTORY_COL1_ACTUAL_RATE = "historyCol1ActualRate";
    /**
     * The Constant DP_LEFT INNER COLUMNS.
     */
    public static final Object[] dpRigtFirstColumns = new Object[]{HISTORY_COL1_ACTUAL_RATE, "historyCol1ProjRate", "historyCol2ActualRate", "historyCol2ProjRate", "projCol1Rate", "projCol2Rate", "projCol3Rate", "projCol4Rate"};
    public static final String PROJECTED_RATE1 = "Projected Rate";
    public static final String ACTUAL_RATE1 = "Actual Rate";
    /**
     * The Constant DP_LEFT INNER HEADER.
     */
    public static final String[] dpRightFirstHeaders = new String[]{ACTUAL_RATE1, PROJECTED_RATE1, ACTUAL_RATE1, PROJECTED_RATE1, PROJECTED_RATE1, PROJECTED_RATE1, PROJECTED_RATE1, PROJECTED_RATE1};
    /**
     * The Constant DP_LEFT SECOND COLUMNS.
     */
    public static final Object[] dpLeftSecondColumns = new Object[]{Constant.GROUP};
    /**
     * The Constant DP_LEFT SECOND HEADER.
     */
    public static final String[] dpLeftSecondHeaders = new String[]{" "};
    /**
     * The Constant DP_LEFT SECOND COLUMNS.
     */
    public static final Object[] dpRightSecondColumns = new Object[]{HISTORY_COL1_ACTUAL_RATE, "historyCol1ProjRate", "projCol1Rate", "projCol2Rate", "projCol3Rate", "projCol4Rate"};
    /**
     * The Constant DP_LEFT SECOND HEADER.
     */
    public static final String[] dpRightSecondHeaders = new String[]{Q1_2014, "Q2 2014", "Q3 2014", "Q4 2014", "Q1 2015", "Q2 2015"};
    /**
     * The Constant DP_LEFT SECOND COLUMNS.
     */
    public static final Object[] dpLeftThirdColumns = new Object[]{Constant.GROUP};
    /**
     * The Constant DP_LEFT SECOND HEADER.
     */
    public static final String[] dpLeftThirdHeaders = new String[]{" "};
    /**
     * The Constant DP_LEFT SECOND COLUMNS.
     */
    public static final Object[] dpRightThirdColumns = new Object[]{HISTORY_COL1_ACTUAL_RATE};
    /**
     * The Constant DP_LEFT SECOND HEADER.
     */
    public static final String[] dpRightThirdHeaders = new String[]{" < Price Group 1 >"};
    /**
     * -- DP TRIPLE HEADER --
     */
    /**
     * The Constant DP_RIGHT_TRIPLE_HEADER.
     */
    public static final String[] dpRightTripleHeader = new String[]{"discount name"};

    /* Right Top Visible Columns */
    /**
     * The Constant DP_RIGHT_TRIPLE_HEADER_COLUMNS.
     */
    public static final Object[] dpRightTripleHeaderColumns = new Object[]{"discName"};
    /**
     * The Constant DP_LEFT_DOUBLE_HEADER_COLUMNS.
     */
    public static final Object[] dpLeftDoubleHeaderColumns = new Object[]{Constant.CHECK};
    /**
     * The Constant DP_LEFT_DOUBLE_HEADER.
     */
    public static final String[] dpLeftDoubleHeader = new String[]{" "};

    /* Right Top Visible Columns */
    /**
     * The Constant DP_LEFT_VISIBLE_COLUMNS.
     */
    public static final Object[] dpLeftVisibleColumns = new Object[]{Constant.CHECK, LEVEL1};
    /* Right Top Visible Columns */
    /**
     * The Constant DP_LEFT_VISIBLE_HEADER.
     */
    public static final String[] dpLeftVisibleHeader = new String[]{" ", LEVEL1};
    /* Right Top Header */
    /**
     * The Constant SPR_RIGHT_DOUBLE_HEADER.
     */
    public static final String[] sprRightDoubleHeader = new String[]{Q1_2014, "Q2 2014", "Q3 2014", "Q4 2014"};

    /* Right Top Visible Columns */
    /**
     * The Constant SPR_RIGHT_DOUBLE_HEADER_COLUMNS.
     */
    public static final Object[] sprRightDoubleColumns = new Object[]{"historyCol1Sales", "historyCol2Sales", "historyCol3Sales", "historyCol4Sales"};

    /* Right Lower Header */
    /**
     * The Constant SPR_RIGHT_VISIBLE_HEADER.
     */
    public static final String[] sprRightVisibleHeader = new String[]{Constant.ACTUALS_PROPERTY, PROJECTIONS1, Constant.ACTUALS_PROPERTY, PROJECTIONS1, Constant.ACTUALS_PROPERTY, PROJECTIONS1, Constant.ACTUALS_PROPERTY, PROJECTIONS1};

    /* Right Lower Visible Columns */
    /**
     * The Constant SPR_RIGHT_VISIBLE_COLUMNS.
     */
    public static final Object[] sprRightVisibleColumns = new Object[]{"historyCol1Sales", "historyCol2Sales", "historyCol3Sales", "historyCol4Sales", "historyCol5Sales", "historyCol6Sales", "historyCol7Sales", "historyCol8Sales"};
    /**
     * The Constant LEFT_TABLE_HEADER_ONE__COLUMNS.
     */
    public static final Object[] leftTableHeaderOneColumns = new Object[]{
        Constant.CHECK, LEVEL1};
    /**
     * The Constant CUST_ID_TRANSF_HEADER.
     */
    public static final String[] leftTableHeaderOneHeaders = new String[]{
        StringUtils.EMPTY, LEVEL_NAME1};
    /**
     * The Constant LEFT_TABLE__HEADER_SECOND_HEADERS.
     */
    public static final String[] leftTableHeaderSecondHeaders = new String[]{StringUtils.EMPTY};
    /**
     * The Constant LEFT_TABLE__HEADER_SECOND_COLUMNS.
     */
    public static final Object[] leftTableSecondColumns = new Object[]{"actualsales"};
    /**
     * The comparison columns.
     */
    public Object[] comparisonColumns = new Object[]{Constant.PROJECTION_NAME, Constant.PROJECTIONDESCRIPTION,
        Constant.MARKET_TYPE, "customer", Constant.CONTRACT, Constant.BRAND, "ndcNo", "ndcName", "createdDate", "createdBy"};
    public static final String MARKET_TYPE1 = "Market Type";
    public static final String CREATED_BY1 = "Created By";
    public static final String LEVEL_VALUE_PROPERTY = "levelValue";
    /**
     * The parity header.
     */
    public String[] comparisonHeader = new String[]{"Projection Name", "Description", MARKET_TYPE1, Constant.CUSTOMER_SMALL, Constant.CONTRACT_SMALL, Constant.BRAND_CAPS, "NDC #", "NDC Name", "Created Date", CREATED_BY1};
    /**
     * The SALES_SMALL projection columns.
     */
    public Object[] salesRightTable = new Object[]{Constant.GROUP,
        Constant.BASELINE, Constant.METHODOLOGY, "actualsales", "projectedsales",
        "actualunits", "projectedunits", "projectedsales1",
        "projectedunits1", "productGrowth", "accountGrowth"};
    public static final String PROJECTED_UNITS1 = "Projected Units";
    public static final String ACTUAL_UNITS1 = "Actual Units";
    public static final String PROJECTED_SALES1 = "Projected Sales";
    public static final String METHODOLOGY1 = "Methodology";
    public static final String BASE_LINE1 = "Base Line";
    /**
     * The SALES_SMALL projection header.
     */
    public String[] salesRightTableHeaders = new String[]{Constant.GROUPFCAPS, BASE_LINE1, METHODOLOGY1, Constant.ACTUAL_SALES, PROJECTED_SALES1, ACTUAL_UNITS1, PROJECTED_UNITS1, PROJECTED_SALES1, PROJECTED_UNITS1, Constant.PRODUCT_GROWTH, Constant.ACCOUNT_GROWTH};
    /**
     * The Constant SUPPLEMENTAL_RIGHT_TABLE_ONE_COLUMNS.
     */
    public static final Object[] supplementalRightTableOneColumns = new Object[]{
        "actualDiscount", Constant.METHODOLOGY, "contractPrice", "discount1",
        "discount2", "contractEndDate", "methodologyQ4", "contractPriceQ4",
        "methodologyQ1", "contractPriceQ1"};
    /**
     * The Constant RIGHT_TABLE_HEADER_SECOND_HEADERS.
     */
    public static final String[] rightTableHeaderSecondHeaders = new String[]{
        " ", "Q4 2013", "Q1 2014 Results"};
    /**
     * The Constant RIGHT_TABLE_HEADER_SECOND_COLUMNS.
     */
    public static final Object[] rightTableHeaderSecondColumns = new Object[]{
        "default", "q1", "q2"};
    /**
     * The Constant PVS_VISIBLE_COLUMN.
     */
    public static final Object[] pvsVisibleColumnLeft = new Object[]{"pvCheck",
        "projectionPeriodPV", "contractHolder", "costomer"};
    public static final String CUSTOMER_ID2 = "Customer ID";
    public static final String PROJECTION_PERIOD = "Projection Period";
    /**
     * The Constant PVS_COLUMN_HEADER.
     */
    public static final String pvsColumnHeaderLeft[] = new String[]{StringUtils.EMPTY, PROJECTION_PERIOD, "Contract Holder", CUSTOMER_ID2};
    public static final String SEGMENT_PROPERTY = "segment";

    /**
     * The Constant PVS_VISIBLE_COLUMN.
     */
    public static final Object pvsVisibleColumn[] = new Object[]{SEGMENT_PROPERTY,
        Constant.MARKET_TYPE, "brandPV", "grossSalePriorPV",
        "grossSaleProjectedPV", "grossSaleChangePV",
        "totalDiscountPriorPV", "totalDiscountProjectedPV",
        "totalDiscountChangePV", "netSalePriorPV", "netSaleProjectedPV",
        "netSaleChangePV", "reasonCodePV", "commentaryPV", "camNamePV"};
    /**
     * The Constant PVS_COLUMN_HEADER.
     */
    public static final String pvsColumnHeader[] = new String[]{Constant.SEGMENT_LABEL, MARKET_TYPE1,
        Constant.BRAND_CAPS, "Gross Sale Prior", "Gross Sale Projected",
        "Gross Sale % Change", "Total Discount % Prior",
        "Total Discount % Projected", "Total Discount % Change",
        "Net Sale $ Prior", "Net Sale $ Projected", "Net Sale % Change",
        "Reason Code", "Commentary", CREATED_BY1};
    /**
     * The Constant PVS_VISIBLE_COLUMN.
     */
    public static final Object pvsVisibleColumnExcel[] = new Object[]{"projectionPeriodPV", "contractHolder", "costomer", SEGMENT_PROPERTY,
        Constant.MARKET_TYPE, "brandPV", "grossSalePriorPV",
        "grossSaleProjectedPV", "grossSaleChangePV",
        "totalDiscountPriorPV", "totalDiscountProjectedPV",
        "totalDiscountChangePV", "netSalePriorPV", "netSaleProjectedPV",
        "netSaleChangePV", "reasonCodePV", "commentaryPV", "camNamePV"};
    /**
     * The Constant PVS_COLUMN_HEADER.
     */
    public static final String pvsColumnHeaderExcel[] = new String[]{PROJECTION_PERIOD, "Contract Holder", CUSTOMER_ID2, Constant.SEGMENT_LABEL, MARKET_TYPE1,
        Constant.BRAND_CAPS, "Gross Sale Prior", "Gross Sale Projected",
        "Gross Sale Change", "Total Discount Prior",
        "Total Discount Projected", "Total Discount Change",
        "Net Sale Prior", "Net Sale Projected", "Net Sale Change",
        "Reason Code", "Commentary", CREATED_BY1};
    public static final String PERIOD1 = "period";

    public static final Object ppaDetailsVisibleColumnExcel[] = new Object[]{PERIOD1,"rebateScheduleName", "priceProtectionPriceType", "price", "priceChange", "map",
        "totalDeductions", "units", "deductionPerUnit", "netPrice", "netMAP", "priceProtectionAmountPerUnit", "priceProtectionPercent", "totalPriceProtectionDeduction",
        "nep", "nepFormula", "priceToleranceType", "priceTolerance", "priceToleranceInterval", "priceToleranceFrequency", "maxIncrementalChange",
        "resetEligible", "resetType", "resetDate", "resetInterval", "resetFrequency", "netPriceType", "netPriceTypeFormula"};

    public static final String ppaDetailsVisibleHeadersExcel[] = new String[]{Constant.PERIOD,"Rebate Schedule Name", "Price Protection Price Type", "Price", "Price Change", "MAP",
        "Total Deductions", Constant.UNITS_SMALL, "Deduction Per Unit", "Net Price", "Net MAP", "Price Protection Amount Per Unit", "Price Protection %", "Total Price Protection Deduction",
        "NEP", "NEP Formula", "Price Tolerance Type", "Price Tolerance", "Price Tolerance Interval", "Price Tolerance Frequency", "Max Incremental Change",
        "Reset Eligible", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency", "Net Price Type", "Net Price Type Formula"};
    public static final String CONTRACT_NAME1 = "contractName";

    /**
     * The parity columns.
     */
    public Object[] parityColumns = new Object[]{CONTRACT_NAME1,
        "brandName", Constant.ITEM_NO, "itemDesc"};
    /**
     * The parity header.
     */
    public String[] parityHeader = new String[]{Constant.CONTRACT_SMALL, Constant.BRAND_CAPS,
        "NDC #", "NDC Description"};
    public static final String CUSTOMER_ID1 = "customerID";
    /**
     * The Constant PVS_VISIBLE_COLUMN.
     */
    public static final Object pvCidtVisibleColumn[] = new Object[]{"contractHolderOrTP", CUSTOMER_ID1, SEGMENT_PROPERTY, Constant.MARKET_TYPE, "movedFromname", "movedFromCustomerID", "movedToname", "movedToCustomerID", "effectiveDate", "annualGrossSales"};
    /**
     * The Constant PVS_COLUMN_HEADER.
     */
    public static final String piCvdtColumnHeader[] = new String[]{"Contract Holder/Trading Partner", CUSTOMER_ID2, Constant.SEGMENT_LABEL, MARKET_TYPE1, "Moved From (name)", "Moved From (Customer ID)", "Moved To (name)", "Moved To (Customer ID)", "Effective Date", "Annual Gross Sales"};
    public static final String TRADING_PARTNER1 = "tradingPartner";
    public static final String PROJECTION_PERIOD1 = "projectionPeriod";
    /**
     * The Constant PVS_VISIBLE_COLUMN.
     */
    public static final Object pvSalesVisibleColumns[] = new Object[]{PROJECTION_PERIOD1, TRADING_PARTNER1, CUSTOMER_ID1, Constant.GROUP, Constant.BRAND, "productGrowth", "accountGrowth"};
    /**
     * The Constant PVS_COLUMN_HEADER.
     */
    public static final String pvSalesCommonHeader[] = new String[]{PROJECTION_PERIOD, Constant.TRADING_PARTNER, CUSTOMER_ID2, Constant.GROUPFCAPS, Constant.BRAND_CAPS, Constant.PRODUCT_GROWTH, Constant.ACCOUNT_GROWTH};
    /**
     * The Constant PVS_VISIBLE_COLUMN.
     */
    public static final Object pvDiscountVisibleColumn[] = new Object[]{PROJECTION_PERIOD1, TRADING_PARTNER1, CUSTOMER_ID1, Constant.GROUP, Constant.BRAND};
    /**
     * The Constant PVS_COLUMN_HEADER.
     */
    public static final String pvDiscountColumnHeader[] = new String[]{PROJECTION_PERIOD, Constant.TRADING_PARTNER, CUSTOMER_ID2, Constant.GROUPFCAPS, Constant.BRAND_CAPS};
    /**
     * The Constant PVS_VISIBLE_COLUMN.
     */
    public static final Object pvPpaVisibleColumn[] = new Object[]{PROJECTION_PERIOD1, TRADING_PARTNER1, CUSTOMER_ID1, Constant.GROUP, Constant.BRAND, "ppaCap", "ppaDiscountPer", "ppaDiscountDollar"};
    /**
     * The Constant PVS_COLUMN_HEADER.
     */
    public static final String pvPpaColumnHeader[] = new String[]{PROJECTION_PERIOD, Constant.TRADING_PARTNER, CUSTOMER_ID2, Constant.GROUPFCAPS, Constant.BRAND_CAPS, "PPA Cap", "PPA Discount %", "PPA Discount $"};
    public static final Object ppaDetailsVisibleColumnLeft[] = new Object[]{PERIOD1, "rebateScheduleName"};
    public static final Object ppaDetailsVisibleColumnRight[] = new Object[]{"priceProtectionPriceType", "price", "priceChange", "map",
        "totalDeductions", "units", "deductionPerUnit", "netPrice", "netMAP", "priceProtectionAmountPerUnit", "priceProtectionPercent", "totalPriceProtectionDeduction",
        "nep", "nepFormula", "priceToleranceType", "priceTolerance", "priceToleranceInterval", "priceToleranceFrequency", "maxIncrementalChange",
        "resetEligible", "resetType", "resetDate", "resetInterval", "resetFrequency", "netPriceType", "netPriceTypeFormula"};
    public static final String ppaDetailsVisibleHeaderLeft[] = new String[]{Constant.PERIOD, "Rebate Schedule Name"};
    public static final String ppaDetailsVisibleHeaderRight[] = new String[]{"Price Protection Price Type", "Price", "Price Change", "MAP",
        "Total Deductions", Constant.UNITS_SMALL, "Deduction Per Unit", "Net Price", "Net MAP", "Price Protection Amount Per Unit", "Price Protection %", "Total Price Protection Deduction",
        "NEP", "NEP Formula", "Price Tolerance Type", "Price Tolerance", "Price Tolerance Interval", "Price Tolerance Frequency", "Max Incremental Change",
        "Reset Eligible", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency", "Net Price Type", "Net Price Type Formula"};
    /**
     * The col variance.
     */
   
    public static final String SPACE_PROJECTED_SALES = "  Projected Sales";
    public static final String SPACE_PROJECTED_UNITS = "  Projected Units";
    public static final String PRODUCT_GROWTH = "  Product Growth";
    public static final String ACCOUNT_GROWTH = "  Account Growth";
    /**
     * The var dis rate.
     */
    /**
     * The var net SALES_SMALL.
     */

    private static final Map<String, String> columnHeaderMap = Constant.getColumnHeaderMap();
    private static final Map<String, List<String>> populateIdentifier = Constant.getPopulateIdentifier();

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
    public List<List> getDynamicDiscount(final Object actualsOrProjections, final String yearSelection, final List<String> yearList, final List<Object> tableColumn, final List<String> tableHeader, final int count) {
        final List<List> list = new ArrayList<>();

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
        final List<String> chartCategories = new ArrayList<>();

        LOGGER.debug("Entering getChartCategories method ");

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
        LOGGER.debug("End of getChartCategories method");
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
        final List<Integer> projectedYears = new ArrayList<>();
        final int currentYear = now.get(Calendar.YEAR);

        LOGGER.debug("Entering calculateYears method ");

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
        LOGGER.debug("End of calculateYears method");
        return projectedYears;
    }

    public static CustomTableHeaderDTO getSalesProjectionLeftTableColumns() {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();

        tableHeaderDTO.addSingleColumn(Constant.CHECK, " ", Boolean.class);
        tableHeaderDTO.addSingleColumn(Constant.LEVELNAME, LEVEL_NAME1, String.class);
        tableHeaderDTO.addSingleColumn(Constant.GROUP, Constant.GROUPFCAPS, String.class);
        tableHeaderDTO.addSingleColumn(Constant.BASELINE, BASE_LINE1, String.class);
        tableHeaderDTO.addSingleColumn(Constant.METHODOLOGY, METHODOLOGY1, String.class);
        tableHeaderDTO.addDoubleColumn(Constant.CHECKRECORD, " ");
        tableHeaderDTO.addDoubleHeaderMap(Constant.CHECKRECORD, new Object[]{Constant.CHECK});
        tableHeaderDTO.addDoubleColumn(GROUP1, " ");
        tableHeaderDTO.addDoubleHeaderMap(GROUP1, new Object[]{Constant.LEVELNAME, Constant.GROUP, Constant.BASELINE, Constant.METHODOLOGY});

        return tableHeaderDTO;
    }
    public static final String GROUP1 = "group1";

    public static List getSalesProjectionRightTableColumns(Map selection, SessionDTO session) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        CustomTableHeaderDTO excelDto = new CustomTableHeaderDTO();

        excelDto.addSingleColumn(Constant.LEVELNAME, LEVEL_NAME1, String.class);
        excelDto.addSingleColumn(Constant.GROUP, Constant.GROUPFCAPS, String.class);
        excelDto.addSingleColumn(Constant.BASELINE, BASE_LINE1, String.class);
        excelDto.addSingleColumn(Constant.METHODOLOGY, METHODOLOGY1, String.class);

        return getCalculatedSalesColumns(selection, tableHeaderDTO, excelDto, session);
    }

    public static CustomTableHeaderDTO getDiscountProjectionLeftTableColumns() {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn(Constant.CHECKRECORD, " ", Boolean.class);
        tableHeaderDTO.addSingleColumn(Constant.LEVELNAME, LEVEL_NAME1, String.class);
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
        tableHeaderDTO.addSingleColumn(Constant.LEVELNAME, LEVEL_NAME1, String.class);
        tableHeaderDTO.addDoubleColumn(Constant.GROUP, " ");

        excelHeader.addSingleColumn(Constant.LEVELNAME, LEVEL_NAME1, String.class);
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

    public static CustomTableHeaderDTO getProjectionResultsLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
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
            projectionOrder = NumericConstants.TWO;
        }
        String yearValue = projSelDTO.getYear();
        int year = isInteger(yearValue) ? Integer.valueOf(yearValue) : 0;
        int historyStartIndex = -1;
        int projectionStartIndex = -1;
        int forecastStartIndex = -1;
        int historyEndIndex = -1;
        int projectionEndIndex = -1;
        int forecastEndIndex = -1;
        List<String> periodList = new ArrayList<>();
        Map<String, String> periodListMap = new HashMap<>();
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
                List<String> common;

                if ("SPR".equals(projSelDTO.getProjTabName())) {
                    common = getCommonColumnHeaderSPR(frequencyDivision, year, pr);
                } else {
                    if (projSelDTO.getFunctionality().equals(ALTERNATE_HISTORY) && projSelDTO.isIsSumarry()) {
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
            
            startPr = projSelDTO.getStartPeriod();
            for (int yr = projSelDTO.getStartYear(); yr <= projSelDTO.getEndYear(); yr++) {
                if (yr == projSelDTO.getEndYear()) {
                    lastPr = projSelDTO.getEndPeriod();
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
            
            //Calculating projection
            startPr = projSelDTO.getProjectionStartPeriod() == 0 ? 1 : projSelDTO.getProjectionStartPeriod();
            int projectionStartYear = projSelDTO.getProjectionStartYear();
            int projectionEndYear = projSelDTO.getForecastDTO().getProjectionEndYear();
                lastPr = projSelDTO.getProjectionEndPeriod();
            if (frequencyDivision == 1) {
                startPr = projectionStartYear;
                lastPr = projectionStartYear;
            }
            String projectionStartCommon = "";
            String projectionEndCommon = "";
            if ("SPR".equals(projSelDTO.getProjTabName())) {
                projectionStartCommon = getCommonColumnHeaderSPR(frequencyDivision, projectionStartYear, startPr).get(0);
                projectionEndCommon = getCommonColumnHeaderSPR(frequencyDivision, projectionEndYear, lastPr).get(0);
            } else {
                if (projSelDTO.getFunctionality().equals(ALTERNATE_HISTORY) && projSelDTO.isIsSumarry()) {
                    projectionStartCommon = getCommonColumnHeaderForAlternateHistory(frequencyDivision, projectionStartYear, startPr).get(0);
                    projectionEndCommon = getCommonColumnHeaderForAlternateHistory(frequencyDivision, projectionEndYear, lastPr).get(0);
                } else {
                    projectionStartCommon = getCommonColumnHeader(frequencyDivision, projectionStartYear, startPr).get(0);
                    projectionEndCommon = getCommonColumnHeader(frequencyDivision, projectionEndYear, lastPr).get(0);
                }
            }

            projectionStartIndex = periodList.indexOf(projectionStartCommon);
            projectionEndIndex = periodList.indexOf(projectionEndCommon);
            
            //Calculating history
            startPr = projSelDTO.getHistoryStartPeriod() == 0 ? 1 : projSelDTO.getHistoryStartPeriod();
            int historyStartYear = projSelDTO.getHistoryStartYear();
            int historyEndYear = projSelDTO.getHistoryEndYear();
            lastPr = projSelDTO.getHistoryEndPeriod();
            if (frequencyDivision == 1) {
                startPr = historyStartYear;
                lastPr = historyStartYear;
            }
            String historyStartCommon = "";
            String historyEndCommon = "";
            if ("SPR".equals(projSelDTO.getProjTabName())) {
                historyStartCommon = getCommonColumnHeaderSPR(frequencyDivision, historyStartYear, startPr).get(0);
                historyEndCommon = getCommonColumnHeaderSPR(frequencyDivision, historyEndYear, lastPr).get(0);
            } else {
                if (projSelDTO.getFunctionality().equals(ALTERNATE_HISTORY) && projSelDTO.isIsSumarry()) {
                    historyStartCommon = getCommonColumnHeaderForAlternateHistory(frequencyDivision, historyStartYear, startPr).get(0);
                    historyEndCommon = getCommonColumnHeaderForAlternateHistory(frequencyDivision, historyEndYear, lastPr).get(0);
                } else {
                    historyStartCommon = getCommonColumnHeader(frequencyDivision, historyStartYear, startPr).get(0);
                    historyEndCommon = getCommonColumnHeader(frequencyDivision, historyEndYear, lastPr).get(0);
                }
            }
            historyStartIndex = periodList.indexOf(historyStartCommon);
            historyEndIndex = periodList.indexOf(historyEndCommon);
 
            //Calculating forecast
            
            startPr = projSelDTO.getForecastStartPeriod() == 0 ? 1 : projSelDTO.getForecastStartPeriod();

            int forecastStartYear = projSelDTO.getForecastDTO().getForecastStartYear();
            int forecastEndYear = projSelDTO.getForecastDTO().getForecastEndYear();
            lastPr = projSelDTO.getForecastEndPeriod();
            if (frequencyDivision == 1) {
                startPr = forecastStartYear;
                lastPr = forecastStartYear;
            }
                String forecastStartCommon = "";
                String forecastEndCommon = "";
                    if ("SPR".equals(projSelDTO.getProjTabName())) {
                        forecastStartCommon = getCommonColumnHeaderSPR(frequencyDivision, forecastStartYear, startPr).get(0);
                        forecastEndCommon = getCommonColumnHeaderSPR(frequencyDivision, forecastEndYear, lastPr).get(0);
                    } else {
                        if (projSelDTO.getFunctionality().equals(ALTERNATE_HISTORY) && projSelDTO.isIsSumarry()) {
                            forecastStartCommon = getCommonColumnHeaderForAlternateHistory(frequencyDivision, forecastStartYear, startPr).get(0);
                            forecastEndCommon = getCommonColumnHeaderForAlternateHistory(frequencyDivision, forecastEndYear, lastPr).get(0);
                        } else {
                            forecastStartCommon = getCommonColumnHeader(frequencyDivision, forecastStartYear, startPr).get(0);
                            forecastEndCommon = getCommonColumnHeader(frequencyDivision, forecastEndYear, lastPr).get(0);
                        }
                    }
                    
            forecastStartIndex = periodList.indexOf(forecastStartCommon);
            forecastEndIndex = periodList.indexOf(forecastEndCommon);
            
            
            
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
    public static final String ALTERNATE_HISTORY = "Alternate-History";

    public static CustomTableHeaderDTO getCalculatedProjectionColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projOrder = projSelDTO.getProjectionOrder();
        List<String> discountNames = new ArrayList<>(projSelDTO.getDiscountNameList());
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
            projectionOrder = NumericConstants.TWO;
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

            for (int i = 0; i < NumericConstants.FOURTEEN; i++) {

                String commonColumn = StringUtils.EMPTY;
                String oldCommonColumn;
                String commonHeader = StringUtils.EMPTY;
                if (i == 0) {

                    commonColumn = "efs";
                    commonHeader = Constant.EX_FACTORY_SALES_LABEL;
                } else if (i == 1) {
                    commonColumn = "dms";
                    commonHeader = Constant.DEMAND_SALES1;
                } else if (i == NumericConstants.TWO) {
                    commonColumn = "iws";
                    commonHeader = "Inventory Withdrawal Sales";
                } else if (i == NumericConstants.THREE) {
                    commonColumn = "perOfExfac";
                    commonHeader = "% of Ex-Factory";
                } else if (i == NumericConstants.FOUR) {
                    commonColumn = "perOfDemand";
                    commonHeader = "% of Demand";
                } else if (i == NumericConstants.FIVE) {
                    commonColumn = "perOfInvwithdraw";
                    commonHeader = "% of Inventory Withdrawal";
                } else if (i == NumericConstants.SIX) {
                    if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant()) || projSelDTO.getSalesOrUnit().equals(SALES.getConstant())) {
                        commonColumn = "conSalesWac";
                        commonHeader = Constant.CONTRACT_SALES_WAC_AT;
                    } else {
                        continue;
                    }
                } else if (i == NumericConstants.SEVEN) {
                    if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant()) || projSelDTO.getSalesOrUnit().equals(UNITS.getConstant())) {
                        commonColumn = "unitVol";
                        commonHeader = Constant.UNIT_VOLUME;
                    } else {
                        continue;
                    }
                } else if (i == NumericConstants.EIGHT) {
                    commonColumn = Constant.TOT_DIS_PER;
                    commonHeader = "Total Discount %";
                } else if (i == NumericConstants.NINE) {
                    commonColumn = "totRPU";
                    commonHeader = Constant.TOTAL_RPU_CAPS;
                } else if (i == NumericConstants.TEN) {
                    commonColumn = Constant.TOTAL_DISCOUNT_DOLLAR;
                    commonHeader = "Total Discount $";
                } else if (i == NumericConstants.ELEVEN) {
                    commonColumn = "netSales";
                    commonHeader = "Net Sales";
                } else if (i == NumericConstants.TWELVE) {
                    commonColumn = "cogs";
                    commonHeader = "Cost of Goods Sold (COGS)";
                } else if (i == NumericConstants.THIRTEEN) {
                    commonColumn = "netProfit";
                    commonHeader = "Net Profit";
                }

                oldCommonColumn = commonColumn;
                int j = -1;
                boolean disc = true;
                while (disc) {
                    List<Object> dmap = new ArrayList<>();
                    if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        if (i == NumericConstants.THREE || i == NumericConstants.FOUR || i == NumericConstants.FIVE || i == NumericConstants.TEN) {
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_RATE.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);
                        } else {
                            tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                        }

                    }
                    Object singleColumn = commonColumn + PROJECTIONS.getConstant();
                    dmap.add(singleColumn);
                    if (i == NumericConstants.THREE || i == NumericConstants.FOUR || i == NumericConstants.FIVE || i == NumericConstants.TEN) {
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
                    if ((!discountNames.isEmpty() && (i == NumericConstants.EIGHT || i == NumericConstants.NINE || i == NumericConstants.TEN)) && (discountNames.size() > (j + 1))) {
                            disc = true;
                            j++;
                            commonHeader = discountNames.get(j);
                            commonColumn = oldCommonColumn + commonHeader.replace(" ", StringUtils.EMPTY);

                    }
                }
            }
        } else {

            List<String> periodList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            for (int i = 0; i < periodList.size(); i++) {
                List<Object> dmap = new ArrayList<>();
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
        List<String> common = new ArrayList<>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = Constant.Q + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = Constant.S + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    public static List<String> getCommonColumnHeaderSPR(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = period + Constant.Q + year;
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = period + Constant.S + year;
        } else if (frequencyDivision == NumericConstants.TWELVE) {
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
        String projFreq = selection.get(Constant.PROJECT_FREQUENCY1).toString();
        String projection = selection.get("projection").toString();
        Calendar ob = Calendar.getInstance();
        ob.set(NumericConstants.TWO_THOUSAND_FIFTEEN, NumericConstants.EIGHT, NumericConstants.TWENTY_FIVE);
        int curMonth = ob.get(Calendar.MONTH);
        int curYear = ob.get(Calendar.YEAR);
        int current = 1;
        int frequency = 0;
        int projectFrequency = 0;
        int division = 1;
        if (freq.contains(Constant.QUARTERLY)) {
            current = curMonth / NumericConstants.THREE;
            division = NumericConstants.FOUR;
            try {
                frequency = Integer.valueOf(hist.replace(Constant.QUARTER1, StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                projectFrequency = Integer.valueOf(projFreq.replace(Constant.QUARTER1, StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        } else if (freq.contains(Constant.SEMI_ANNUALLY)) {
            current = curMonth / NumericConstants.SIX;
            division = NumericConstants.TWO;
            try {
                frequency = Integer.valueOf(hist.replace(Constant.SEMI_ANNUALY, StringUtils.EMPTY).trim());
                projectFrequency = Integer.valueOf(projFreq.replace(Constant.SEMI_ANNUALY, StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        } else if (freq.contains(MONTHLY.getConstant())) {
            current = curMonth;
            division = NumericConstants.TWELVE;
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
            List<Object> dmap = new ArrayList<>();
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

                if (projection.contains(Constant.BOTH_SMALL) || projection.contains(Constant.ACTUALS_PROPERTY)) {
                    dmap.add(commonColumn + Constant.ACTUALSALES);
                    tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACTUALSALES, Constant.ACTUAL_SALES, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(commonColumn + Constant.ACTUALSALES, Constant.ACTUAL_SALES);
                }
                if (projection.contains(Constant.BOTH_SMALL) || projection.contains(PROJECTIONS1)) {
                    dmap.add(commonColumn + Constant.PROJECTED_SALES1);
                    tableHeaderDTO.addSingleColumn(commonColumn + Constant.PROJECTED_SALES1, PROJECTED_SALES1, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(commonColumn + Constant.PROJECTED_SALES1, PROJECTED_SALES1);
                }
            }
            if (selection.containsKey(Constant.UNITS)) {
                if (projection.contains(Constant.BOTH_SMALL) || projection.contains(Constant.ACTUALS_PROPERTY)) {
                    dmap.add(commonColumn + Constant.ACTUALUNITS);
                    tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACTUALUNITS, ACTUAL_UNITS1, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(commonColumn + Constant.ACTUALUNITS, ACTUAL_UNITS1);
                }
                if (projection.contains(Constant.BOTH_SMALL) || projection.contains(PROJECTIONS1)) {
                    dmap.add(commonColumn + Constant.PROJECTED_UNITS1);
                    tableHeaderDTO.addSingleColumn(commonColumn + Constant.PROJECTED_UNITS1, PROJECTED_UNITS1, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(commonColumn + Constant.PROJECTED_UNITS1, PROJECTED_UNITS1);
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
            List<Object> dmap = new ArrayList<>();
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
                dmap.add(commonColumn + Constant.PROJECTED_SALES1);
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.PROJECTED_SALES1, PROJECTED_SALES1, String.class);
                tableHeaderDTO.addSingleProjectedColumn(commonColumn + Constant.PROJECTED_SALES1, PROJECTED_SALES1);
            }
            if (selection.containsKey(Constant.UNITS)) {
                dmap.add(commonColumn + Constant.PROJECTED_UNITS1);
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.PROJECTED_UNITS1, PROJECTED_UNITS1, String.class);
                tableHeaderDTO.addSingleProjectedColumn(commonColumn + Constant.PROJECTED_UNITS1, PROJECTED_UNITS1);
            }
            if (selection.containsKey("product")) {
                dmap.add(commonColumn + Constant.PRODUCT_GROWTH1);
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.PRODUCT_GROWTH1, Constant.PRODUCT_GROWTH, String.class);
                tableHeaderDTO.addSingleProjectedColumn(commonColumn + Constant.PRODUCT_GROWTH1, Constant.PRODUCT_GROWTH);
            }
            if (selection.containsKey("account")) {
                dmap.add(commonColumn + Constant.ACCOUNT_GROWTH1);
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACCOUNT_GROWTH1, Constant.ACCOUNT_GROWTH, String.class);
                tableHeaderDTO.addSingleProjectedColumn(commonColumn + Constant.ACCOUNT_GROWTH1, Constant.ACCOUNT_GROWTH);
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
        LOGGER.debug("Entering getCalculatedDiscountProjectionColumns ");
        Map<Integer, List> periodListMapForExcel = new HashMap<>();
        try {
            List<String> discountNames=new ArrayList<>();
            List<String> discountProperties=new ArrayList<>();
            if (CommonUtil.isValueEligibleForLoading()) {
                discountNames = new ArrayList(projSelDTO.getDeductionLevelCaptions());
                discountProperties=new ArrayList(projSelDTO.getDeductionLevelFilter());
            } else {
                discountNames =new ArrayList(projSelDTO.getDiscountProgramsList());
            }
            
            String actualsOrProjections = projSelDTO.getActualsOrProjections();

            CommonUtils.getHistoryAndProjectionDetails(projSelDTO);
            prepareCommonColumnHeaders(projSelDTO);
            List<String> columnsList = projSelDTO.getPeriodList();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            boolean isCustom=Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY.equals(projSelDTO.getHierarchyIndicator());
            // Added for tabwise excel export 
            List<Object> singleColumnForExcel = new ArrayList<>();
            List<String> singleHeaderForExcel = new ArrayList<>();
            List<Object> doubleColumnForExcel = new ArrayList<>();
            List<String> doubleHeaderForExcel = new ArrayList<>();
            Map<Object, Object[]> doubleHeaderMap = new HashMap<>();
            List headerListForExcel = new ArrayList();
            int tempYear;
            int j = 0;
            int k = 0;// End here for tab wise excel export

            if (discountNames.isEmpty()) {
                discountNames.add("All Discount");
                discountProperties.add("AllDiscount");
            }
            
            //No triple Header for Custom View .So making size of discount list as 1
            int discountNamesint = isCustom ? 1 : discountNames.size();
            for (int l = 0; l < discountNamesint; l++) {
                String discountName=discountNames.get(l);
           
                String discountColumnName = discountProperties.get(l);
                List<Object> tmap = new ArrayList<>();

                for (int i = 0; i < columnsList.size(); i++) {
                    List<Object> dmap = new ArrayList<>();
                    String column = columnsList.get(i);
                    //Added for tabwise excel export
                    boolean excelTab = projSelDTO.getFrequencyDivision() == NumericConstants.FOUR || projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE;
                    tempYear = excelTab ? projSelDTO.getFrequencyDivision() == NumericConstants.FOUR ? Integer.parseInt(column.substring(NumericConstants.TWO, column.length()))
                            : Integer.parseInt(column.replaceAll("[^\\d.]", StringUtils.EMPTY)) : 0;
                    if (k != tempYear && !headerListForExcel.contains(tempYear)) {
                        singleColumnForExcel = new ArrayList<>();
                        singleHeaderForExcel = new ArrayList<>();
                        headerListForExcel = new ArrayList();
                        doubleColumnForExcel = new ArrayList<>();
                        doubleHeaderForExcel = new ArrayList<>();
                        doubleHeaderMap = new HashMap<>();
                        headerListForExcel.add(singleColumnForExcel);
                        headerListForExcel.add(singleHeaderForExcel);
                        headerListForExcel.add(tempYear);
                        headerListForExcel.add(doubleColumnForExcel);
                        headerListForExcel.add(doubleHeaderForExcel);
                        headerListForExcel.add(doubleHeaderMap);
                        periodListMapForExcel.put(j, headerListForExcel);
                        j++;
                        k = tempYear;
                    } // Ends here
                    String commonColumn=StringUtils.EMPTY;
                    if (!"All Discount".equals(discountName)) {
                        if (!isCustom) {
                            List discountHierarchyNo = projSelDTO.getDeductionLevelFilter();
                            commonColumn = discountHierarchyNo.get(l) + column;
                        } else {
                            commonColumn = column;
                        }
                    } else {
                        commonColumn = discountName + column;
                    }
            
                    String commonHeader = periodListMap.get(column);
                    boolean historyFlag = false;
                    boolean historyActualFlag = false;
                    boolean projectionFlag = false;
                    boolean projectionCol = false;
                    boolean forecastFlag = false;
                    Object singleColumn = commonColumn;
                    Object actualRPColumn;
                    Object prjRPColumn;
                    Object actAmtColumn;
                    Object prjAmtColumn;
                    Object growthColumn;

                    if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                        historyFlag = true;
                        if (actualsOrProjections.contains(Constant.BOTH) || actualsOrProjections.contains(ACTUALS.getConstant())) {
                            historyActualFlag = true;
                            singleColumn = commonColumn + "ActualRate";
                            actualRPColumn = commonColumn + Constant.ACTUALRPU;
                            actAmtColumn = commonColumn + ACTUAL_AMOUNT1;

                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                                tableHeader.addSingleColumn(singleColumn, ACTUAL_RATE1, String.class);
                                excelHeader.addSingleColumn(singleColumn, " Actual Rate", String.class);
                                // Added for tabwise excel export
                                singleColumnForExcel.add(singleColumn);
                                singleHeaderForExcel.add(ACTUAL_RATE1);// Ends here
                                dmap.add(singleColumn);
                            }
                            if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                                tableHeader.addSingleColumn(actualRPColumn, ACTUAL_RPU1, String.class);
                                excelHeader.addSingleColumn(actualRPColumn, ACTUAL_RPU1, String.class);
                                // Added for tabwise excel export
                                singleColumnForExcel.add(actualRPColumn);
                                singleHeaderForExcel.add(ACTUAL_RPU1);//Ends here
                                dmap.add(actualRPColumn);
                            }
                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                                tableHeader.addSingleColumn(actAmtColumn, ACTUAL_AMOUNT_LABEL, String.class);
                                excelHeader.addSingleColumn(actAmtColumn, ACTUAL_AMOUNT_LABEL, String.class);
                                //Added for tabwise excel export
                                singleColumnForExcel.add(actAmtColumn);
                                singleHeaderForExcel.add(ACTUAL_AMOUNT_LABEL);// Ends here
                                dmap.add(actAmtColumn);
                            }

                        }
                    }

                    if (i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex()) {
                        projectionFlag = true;
                        projectionCol = true;
                        if ((historyActualFlag) && (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant()))) {

                                tableHeader.addSingleHistoryColumn(singleColumn, ACTUAL_RATE1);


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
                        prjRPColumn = commonColumn + Constant.PROJECTEDRPU;
                        prjAmtColumn = commonColumn + Constant.PROJECTED_AMOUNT1;
                        growthColumn = commonColumn + Constant.GROWTH;

                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                            tableHeader.addSingleColumn(singleColumn, PROJECTED_RATE1, String.class);
                            excelHeader.addSingleColumn(singleColumn, PROJECTED_RATE1, String.class);
                            //Added for tab wise excel export
                            singleColumnForExcel.add(singleColumn);
                            singleHeaderForExcel.add(PROJECTED_RATE1);//Ends here
                            dmap.add(singleColumn);
                        }
                        if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                            tableHeader.addSingleColumn(prjRPColumn, Constant.PROJECTED_RPU1, String.class);
                            excelHeader.addSingleColumn(prjRPColumn, Constant.PROJECTED_RPU1, String.class);
                            //Added for tabwise excel export
                            singleColumnForExcel.add(prjRPColumn);
                            singleHeaderForExcel.add(Constant.PROJECTED_RPU1);//Ends here
                            dmap.add(prjRPColumn);
                        }
                        if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                            tableHeader.addSingleColumn(prjAmtColumn, Constant.PROJECTED_AMOUNT_LABEL, String.class);
                            excelHeader.addSingleColumn(prjAmtColumn, Constant.PROJECTED_AMOUNT_LABEL, String.class);
                            //Added for tabwise excel export
                            singleColumnForExcel.add(prjAmtColumn);
                            singleHeaderForExcel.add(Constant.PROJECTED_AMOUNT_LABEL);//Ends here
                            dmap.add(prjAmtColumn);
                        }

                        if (projSelDTO.getdPVariablesList().contains(GROWTH.getConstant())) {
                            tableHeader.addSingleColumn(growthColumn, Constant.GROWTH, String.class);
                            excelHeader.addSingleColumn(growthColumn, Constant.GROWTH, String.class);
                            //Added for tabwise excel export
                            singleColumnForExcel.add(growthColumn);
                            singleHeaderForExcel.add(Constant.GROWTH);//Ends here
                            dmap.add(growthColumn);
                        }

                        if (historyFlag) {
                            tableHeader.addSingleHistoryColumn(singleColumn, PROJECTED_RATE1);

                        }
                        if (projectionFlag) {
                            tableHeader.addSingleProjectedColumn(singleColumn, PROJECTED_RATE1);

                        }
                        if (forecastFlag) {
                            tableHeader.addSingleForecastColumn(singleColumn, PROJECTED_RATE1);
                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                                tableHeader.addSingleHistoryColumn(singleColumn, PROJECTED_RATE1);
                            }
                        }
                        if (projectionFlag) {
                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                                tableHeader.addSingleProjectedColumn(singleColumn, PROJECTED_RATE1);
                            }
                            if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                                tableHeader.addSingleProjectedColumn(prjRPColumn, Constant.PROJECTED_RPU1);
                            }
                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                                tableHeader.addSingleProjectedColumn(prjAmtColumn, Constant.PROJECTED_AMOUNT_LABEL);
                            }
                            if (projSelDTO.getdPVariablesList().contains(GROWTH.getConstant())) {
                                tableHeader.addSingleProjectedColumn(growthColumn, Constant.GROWTH);
                            }
                        }
                        if (forecastFlag) {
                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_RATE.getConstant())) {
                                tableHeader.addSingleForecastColumn(singleColumn, PROJECTED_RATE1);
                            }
                            if (projSelDTO.getdPVariablesList().contains(REBATE_PER_UNIT.getConstant())) {
                                tableHeader.addSingleForecastColumn(prjRPColumn, Constant.PROJECTED_RPU1);
                            }
                            if (projSelDTO.getdPVariablesList().contains(DISCOUNT_AMT.getConstant())) {
                                tableHeader.addSingleForecastColumn(prjAmtColumn, Constant.PROJECTED_AMOUNT_LABEL);
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
                        //Added for tabwise excel export
                        doubleColumnForExcel.add(commonColumn);
                        doubleHeaderForExcel.add(discountName + " " + commonHeader);
                        doubleHeaderMap.put(commonColumn, dmap.toArray());//Ends here
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
                
                if (!tmap.isEmpty()&& !isCustom) {
                    tableHeader.addTripleColumn(discountColumnName, discountName.contains("~")?discountName.split("~")[0]:discountName);
                    tableHeader.addTripleHeaderMap(discountColumnName, tmap.toArray());
                    excelHeader.addTripleColumn(discountColumnName, discountName.contains("~")?discountName.split("~")[0]:discountName);
                    excelHeader.addTripleHeaderMap(discountColumnName, tmap.toArray());
                }
            }

        } catch (NumberFormatException e) {
        LOGGER.error(e);
        }
        LOGGER.debug("Ending getCalculatedDiscountProjectionColumns ");
        List<Object> headerContents = new ArrayList<>();
        headerContents.add(tableHeader);
        headerContents.add(excelHeader);
        projSelDTO.setHeaderMapForExcel(periodListMapForExcel);//Added for tabwise excel export
        return headerContents;
    }
    public static final String ACTUAL_AMOUNT1 = "ActualAmount";
    public static final String ACTUAL_RPU1 = "Actual RPU";
    public static final String ACTUAL_AMOUNT_LABEL = "Actual Amount";

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num >= 0 && num <= NumericConstants.ELEVEN) {
            month = months[num];
        }
        return month;
    }

    public static CustomTableHeaderDTO getPPAProjectionLeftTableColumns(CustomTableHeaderDTO fullHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn(Constant.CHECK_RECORD + ".0", Constant.SPACE, Boolean.class);
        tableHeaderDTO.addSingleColumn(Constant.LEVELNAME, LEVEL_NAME1, String.class);
        tableHeaderDTO.addSingleColumn(Constant.GROUP, Constant.GROUPFCAPS, String.class);
        tableHeaderDTO.addSingleColumn(Constant.PRICEPROTECTIONSTATUS, "Price Protection Status", String.class);

        tableHeaderDTO.addSingleColumn(Constant.PRICEPROTECTIONSTARTDATE, "Price Protection Start Date", String.class);
        tableHeaderDTO.addSingleColumn(Constant.PRICEPROTECTIONENDDATE, "Price Protection End Date", String.class);
        tableHeaderDTO.addDoubleColumn(GROUP1, " ");
        tableHeaderDTO.addDoubleHeaderMap(GROUP1, new Object[]{Constant.CHECK_RECORD + ".0", Constant.LEVELNAME, Constant.GROUP, "priceProtectionStatus", "priceProtectionStartDate", "priceProtectionEndDate"});
        fullHeader.addSingleColumn(Constant.LEVELNAME, LEVEL_NAME1, String.class);
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

    public static CustomTableHeaderDTO getPPAOnLoadRightColumns(CustomTableHeaderDTO fullHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn(Constant.DEFAULT_COLUMN, StringUtils.EMPTY, String.class);
        tableHeaderDTO.addDoubleColumn(GROUP1, " ");
        tableHeaderDTO.addDoubleHeaderMap(GROUP1, new Object[]{Constant.DEFAULT_COLUMN});
        fullHeader.addSingleColumn(Constant.DEFAULT_COLUMN, StringUtils.EMPTY, String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getCalculatedPPAProjectionResultsColumns(ProjectionSelectionDTO selection, CustomTableHeaderDTO fullHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        String projection = selection.getActualsOrProjections();
        String pivotView = selection.getPivotView();
        CommonUtils.getHistoryAndProjectionDetails(selection);
        prepareCommonColumnHeaders(selection);

        if (pivotView.toLowerCase().contains("variable")) {

            for (int i = 0; i < NumericConstants.FOUR; i++) {
                String commonColumn = StringUtils.EMPTY;

                String commonHeader = StringUtils.EMPTY;

                if (i == 0) {
                    commonColumn = "discountPerUnit";
                    commonHeader = "Discount $ Per Unit";
                } else if (i == 1) {
                    commonColumn = "discountPercent";
                    commonHeader = CommonUtils.VAR_DIS_RATE;
                } else if (i == NumericConstants.TWO) {
                    commonColumn = "unitVolume";
                    commonHeader = Constant.UNIT_VOLUME;
                } else if (i == NumericConstants.THREE) {
                    commonColumn = "totalDiscount";
                    commonHeader = "Total Discount Amount";
                }

                boolean disc = true;
                while (disc) {

                    List<Object> dmap = new ArrayList<>();
                    if (projection.contains(Constant.ACTUALS_PROPERTY) || projection.contains(Constant.ACTUALS)) {
                        dmap.add(commonColumn + Constant.ACTUALS);
                        tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACTUALS, Constant.ACTUALS, String.class);
                        fullHeader.addSingleColumn(commonColumn + Constant.ACTUALS, commonColumn + Constant.SPACE + Constant.ACTUALS, String.class);

                    }
                    if (projection.contains(PROJECTIONS1) || projection.contains(Constant.PROJECTIONS)) {
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
                List<Object> dmap = new ArrayList<>();
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
            List<Object> dmap = new ArrayList<>();
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
                    } else if (Constant.PRICE_PROTECTION_START_DATE.equals(columnHeaderMap.get(column)) || Constant.PRICE_PROTECTION_END_DATE.equals(columnHeaderMap.get(column))
                            || populateIdentifier.get(Constant.DATE_FEILD).contains(columnHeaderMap.get(column))) {

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

        Map<Object, Object[]> reProjectedColumn = new HashMap<>();
        Map<Object, Object[]> doubleHeaderHistoryMap = new HashMap<>();
        List<String> totalProjected = new ArrayList<>();
        SalesProjectionLogic salesLogic = new SalesProjectionLogic();
        String hist = selection.get(Constant.HISTORY).toString();
        String projFreq = selection.get(Constant.PROJECT_FREQUENCY1).toString();

        String projection = selection.get("projection").toString();

        String salesVar = (String) selection.get(Constant.SALES);
        String unitsVar = (String) selection.get(Constant.UNITS);
        String pGrowthVar = (String) selection.get(Constant.P_GROWTH);
        String aGrowthVar = (String) selection.get(Constant.A_GROWTH);

        int curMonth = forecastDTO.getForecastStartMonth() - 1;

        int curYear = forecastDTO.getForecastStartYear();
        boolean reprojectionAllowed = false;
        boolean projAllowed = true;

        int current = curMonth / NumericConstants.THREE;
        int frequency = 0;
        int projectFrequency = 0;
        int division = NumericConstants.FOUR;

        try {
            frequency = Integer.valueOf(hist.replace(Constant.QUARTER1, StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
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
            List<Object> dmap = new ArrayList<>();
            List<Object> historyObj = new ArrayList<>();
            List<Object> projectionObj = new ArrayList<>();
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

            if ((projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) && (salesVar.equals(Constant.TRUE))) {
                    String singleColumn = commonColumn + "-ActualSales";
                    dmap.add(singleColumn);
                    historyObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACTUAL_SALES, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.ACTUAL_SALES);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Actual Sales", String.class);
            }
            if (!reprojectionAllowed) {
                if ((projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) && (salesVar.equals(Constant.TRUE))) {
                        String singleColumn = commonColumn + "-HistoryProjectedSales";
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_SALES1, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, PROJECTED_SALES1);
                        excelDto.addSingleColumn(singleColumn, commonHeader + SPACE_PROJECTED_SALES, String.class);
                }
            } else {
                if ((projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) && (salesVar.equals(Constant.TRUE))) {
                        String singleColumn = commonColumn + "-ProjectedSales";
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        projectionObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_SALES1, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, PROJECTED_SALES1);
                        excelDto.addSingleColumn(singleColumn, commonHeader + SPACE_PROJECTED_SALES, String.class);

                }
            }
            if ((projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) && (unitsVar.equals(Constant.TRUE))) {

                    String singleColumn = commonColumn + "-ActualUnits";
                    dmap.add(singleColumn);
                    historyObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_UNITS1, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, ACTUAL_UNITS1);
                    excelDto.addSingleColumn(singleColumn, commonHeader + "  Actual Units", String.class);
            }
                if ((!reprojectionAllowed) && (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant()))) {
                    if (unitsVar.equals(Constant.TRUE)) {
                        String singleColumn = commonColumn + "-HistoryProjectedUnits";
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_UNITS1, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, PROJECTED_UNITS1);
                        excelDto.addSingleColumn(singleColumn, commonHeader + SPACE_PROJECTED_UNITS, String.class);
                    }

                }

            else {
                if ((projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) && (unitsVar.equals(Constant.TRUE))) {
                        String singleColumn = commonColumn + "-ProjectedUnits";
                        dmap.add(singleColumn);
                        historyObj.add(singleColumn);
                        projectionObj.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_UNITS1, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, PROJECTED_UNITS1);
                        excelDto.addSingleColumn(singleColumn, commonHeader + SPACE_PROJECTED_UNITS, String.class);

                }

                if (pGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProductGrowth";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.PRODUCT_GROWTH, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.PRODUCT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + PRODUCT_GROWTH, String.class);
                }

                if (aGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-AccountGrowth";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACCOUNT_GROWTH, String.class);
                    tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.ACCOUNT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + ACCOUNT_GROWTH, String.class);
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
            List<Object> dmap = new ArrayList<>();
            List<Object> projectionObj = new ArrayList<>();
            String commonColumn;
            String commonHeader;
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
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_SALES1, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTED_SALES1);
                    excelDto.addSingleColumn(singleColumn, commonHeader + SPACE_PROJECTED_SALES, String.class);
                }
                if (unitsVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProjectedUnits";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_UNITS1, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTED_UNITS1);
                    excelDto.addSingleColumn(singleColumn, commonHeader + SPACE_PROJECTED_UNITS, String.class);
                }
                if (pGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProductGrowth";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.PRODUCT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.PRODUCT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + PRODUCT_GROWTH, String.class);
                }
                if (aGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-AccountGrowth";
                    dmap.add(singleColumn);
                    projectionObj.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACCOUNT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.ACCOUNT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + ACCOUNT_GROWTH, String.class);
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
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_SALES1, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTED_SALES1);
                    excelDto.addSingleColumn(singleColumn, commonHeader + SPACE_PROJECTED_SALES, String.class);
                }
                if (unitsVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProjectedUnitsDis";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, PROJECTED_UNITS1, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTED_UNITS1);
                    excelDto.addSingleColumn(singleColumn, commonHeader + SPACE_PROJECTED_UNITS, String.class);
                }
                if (pGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-ProductGrowthDis";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.PRODUCT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.PRODUCT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + PRODUCT_GROWTH, String.class);
                }
                if (aGrowthVar.equals(Constant.TRUE)) {
                    String singleColumn = commonColumn + "-AccountGrowthDis";
                    dmap.add(singleColumn);
                    tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACCOUNT_GROWTH, String.class);
                    tableHeaderDTO.addSingleProjectedColumn(singleColumn, Constant.ACCOUNT_GROWTH);
                    excelDto.addSingleColumn(singleColumn, commonHeader + ACCOUNT_GROWTH, String.class);
                }

            }

            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleProjectedHeaderMap(commonColumn, dmap.toArray());
                reProjectedColumn.put(commonColumn, projectionObj.toArray());

            }
            if ((forecastDTO.getForecastEndDate().after(forecastDTO.getProjectionEndDate()) || forecastDTO.getForecastEndDate().equals(forecastDTO.getProjectionEndDate())) && (salesLogic.getQuator(forecastDTO.getForecastEndMonth()) == squr && forecastDTO.getForecastEndYear() == syear)) {

                    break;
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
            frequencyDivision = NumericConstants.FOUR;
            currentPeriod = CommonUtils.getPeriod(curMonth + 1, NumericConstants.THREE);
            historyStartPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), NumericConstants.THREE);
            historyEndPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), NumericConstants.THREE);
            forecastStartPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), NumericConstants.THREE);
            forecastEndPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), NumericConstants.THREE);
            projectionStartPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), NumericConstants.THREE);
            projectionEndPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), NumericConstants.THREE);
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequencyDivision = NumericConstants.TWO;
            currentPeriod = CommonUtils.getPeriod(curMonth + 1, NumericConstants.SIX);
            historyStartPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), NumericConstants.SIX);
            historyEndPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), NumericConstants.SIX);
            forecastStartPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), NumericConstants.SIX);
            forecastEndPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), NumericConstants.SIX);
            projectionStartPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), NumericConstants.SIX);
            projectionEndPeriod = CommonUtils.getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), NumericConstants.SIX);
        } else if (frequency.equals(MONTHLY.getConstant())) {
            currentPeriod = curMonth;
            frequencyDivision = NumericConstants.TWELVE;
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
                historyEndMonth = NumericConstants.TWELVE;
                historyEndYear = historyEndYear - 1;
            } else if (historyEndPeriod < 1) {
                historyEndMonth = NumericConstants.TWELVE;
                historyEndYear = historyEndYear - 1;
            } else if (frequencyDivision == NumericConstants.TWO) {
                if (historyEndPeriod == 1) {
                    historyEndMonth = NumericConstants.SIX;
                } else if (historyEndPeriod == NumericConstants.TWO) {
                    historyEndMonth = NumericConstants.TWELVE;
                }
            } else if (frequencyDivision == NumericConstants.FOUR) {
                if (historyEndPeriod == 1) {
                    historyEndMonth = NumericConstants.THREE;
                } else if (historyEndPeriod == NumericConstants.TWO) {
                    historyEndMonth = NumericConstants.SIX;
                } else if (historyEndPeriod == NumericConstants.THREE) {
                    historyEndMonth = NumericConstants.NINE;
                } else if (historyEndPeriod == NumericConstants.FOUR) {
                    historyEndMonth = NumericConstants.TWELVE;
                }
            } else if (frequencyDivision == NumericConstants.TWELVE) {
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
        } else if (frequencyDivision == NumericConstants.TWO) {
            if (historyStartFreq == 1) {
                projSelDTO.setHistoryStartMonth(1);
            } else if (historyStartFreq == NumericConstants.TWO) {
                projSelDTO.setHistoryStartMonth(NumericConstants.SEVEN);
            }
        } else if (frequencyDivision == NumericConstants.FOUR) {
            if (historyStartFreq == 1) {
                projSelDTO.setHistoryStartMonth(1);
            } else if (historyStartFreq == NumericConstants.TWO) {
                projSelDTO.setHistoryStartMonth(NumericConstants.FOUR);
            } else if (historyStartFreq == NumericConstants.THREE) {
                projSelDTO.setHistoryStartMonth(NumericConstants.SEVEN);
            } else if (historyStartFreq == NumericConstants.FOUR) {
                projSelDTO.setHistoryStartMonth(NumericConstants.TEN);
            }
        } else if (frequencyDivision == NumericConstants.TWELVE) {
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
        } else if (frequencyDivision == NumericConstants.TWO) {
            if (startFreq == 1) {
                projSelDTO.setStartMonth(1);
            } else if (startFreq == NumericConstants.TWO) {
                projSelDTO.setStartMonth(NumericConstants.SEVEN);
            }
        } else if (frequencyDivision == NumericConstants.FOUR) {
            if (startFreq == 1) {
                projSelDTO.setStartMonth(1);
            } else if (startFreq == NumericConstants.TWO) {
                projSelDTO.setStartMonth(NumericConstants.FOUR);
            } else if (startFreq == NumericConstants.THREE) {
                projSelDTO.setStartMonth(NumericConstants.SEVEN);
            } else if (startFreq == NumericConstants.FOUR) {
                projSelDTO.setStartMonth(NumericConstants.TEN);
            }
        } else if (frequencyDivision == NumericConstants.TWELVE) {
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
            projSelDTO.setEndMonth(NumericConstants.TWELVE);
        } else if (frequencyDivision == NumericConstants.TWO) {
            if (endFreq == 1) {
                projSelDTO.setEndMonth(NumericConstants.SIX);
            } else if (endFreq == NumericConstants.TWO) {
                projSelDTO.setEndMonth(NumericConstants.TWELVE);
            }
        } else if (frequencyDivision == NumericConstants.FOUR) {
            if (endFreq == 1) {
                projSelDTO.setEndMonth(NumericConstants.THREE);
            } else if (endFreq == NumericConstants.TWO) {
                projSelDTO.setEndMonth(NumericConstants.SIX);
            } else if (endFreq == NumericConstants.THREE) {
                projSelDTO.setEndMonth(NumericConstants.NINE);
            } else if (endFreq == NumericConstants.FOUR) {
                projSelDTO.setEndMonth(NumericConstants.TWELVE);
            }
        } else if (frequencyDivision == NumericConstants.TWELVE) {
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
        } else if (frequencyDivision == NumericConstants.TWO) {
            if (historyStartFreq == 1) {
                projSelDTO.setHistoryStartMonth(1);
            } else if (historyStartFreq == NumericConstants.TWO) {
                projSelDTO.setHistoryStartMonth(NumericConstants.SEVEN);
            }
        } else if (frequencyDivision == NumericConstants.FOUR) {
            if (historyStartFreq == 1) {
                projSelDTO.setHistoryStartMonth(1);
            } else if (historyStartFreq == NumericConstants.TWO) {
                projSelDTO.setHistoryStartMonth(NumericConstants.FOUR);
            } else if (historyStartFreq == NumericConstants.THREE) {
                projSelDTO.setHistoryStartMonth(NumericConstants.SEVEN);
            } else if (historyStartFreq == NumericConstants.FOUR) {
                projSelDTO.setHistoryStartMonth(NumericConstants.TEN);
            }
        } else if (frequencyDivision == NumericConstants.TWELVE) {
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
        } else if (frequencyDivision == NumericConstants.TWO) {
            if (startFreq == 1) {
                projSelDTO.setStartMonth(1);
            } else if (startFreq == NumericConstants.TWO) {
                projSelDTO.setStartMonth(NumericConstants.SEVEN);
            }
        } else if (frequencyDivision == NumericConstants.FOUR) {
            if (startFreq == 1) {
                projSelDTO.setStartMonth(1);
            } else if (startFreq == NumericConstants.TWO) {
                projSelDTO.setStartMonth(NumericConstants.FOUR);
            } else if (startFreq == NumericConstants.THREE) {
                projSelDTO.setStartMonth(NumericConstants.SEVEN);
            } else if (startFreq == NumericConstants.FOUR) {
                projSelDTO.setStartMonth(NumericConstants.TEN);
            }
        } else if (frequencyDivision == NumericConstants.TWELVE) {
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
        int curYear = ob.get(Calendar.YEAR);
        int current = 1;
        int frequency = String.valueOf(selection.get(HISTORY_NUM)) != null && !Constant.NULL.equals(String.valueOf(selection.get(HISTORY_NUM))) ? Integer.parseInt(String.valueOf(selection.get(HISTORY_NUM))) : 0;
        int projectFrequency = String.valueOf(selection.get(PROJECTION_NUM)) != null && !Constant.NULL.equals(String.valueOf(selection.get(PROJECTION_NUM))) ? Integer.parseInt(String.valueOf(selection.get(PROJECTION_NUM))) : 0;

        int division = 1;
        if (freq.equals(QUARTERLY.getConstant())) {

            current = curMonth / NumericConstants.THREE;
            division = NumericConstants.FOUR;

        } else if (freq.equals(SEMI_ANNUALLY.getConstant())) {
            current = curMonth / NumericConstants.SIX;
            division = NumericConstants.TWO;

        } else if (freq.equals(MONTHLY.getConstant())) {
            current = curMonth;
            division = NumericConstants.TWELVE;

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
            List<Object> dmap = new ArrayList<>();
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
                dmap.add(commonColumn + Constant.ACTUALS_RATE);
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACTUALS_RATE, "Actuals Rate", String.class);
                dmap.add(commonColumn + "ActualsAmount");
                tableHeaderDTO.addSingleColumn(commonColumn + "ActualsAmount", "Actuals Amount", String.class);

                if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                    dmap.add(commonColumn + Constant.PROJECTIONS_RATE);
                    tableHeaderDTO.addSingleColumn(commonColumn + Constant.PROJECTIONS_RATE, "Projections Rate", String.class);
                    dmap.add(commonColumn + Constant.PROJECTIONS_AMOUNT);
                    tableHeaderDTO.addSingleColumn(commonColumn + Constant.PROJECTIONS_AMOUNT, "Projections Amount", String.class);
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
            List<Object> dmap = new ArrayList<>();
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
                dmap.add(commonColumn + Constant.PROJECTIONS_RATE);
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.PROJECTIONS_RATE, "Projections Rate", String.class);
                dmap.add(commonColumn + Constant.PROJECTIONS_AMOUNT);
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.PROJECTIONS_AMOUNT, "Projections Amount", String.class);

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
    public static final String HISTORY_NUM = "historyNum";
    public static final String PROJECTION_NUM = "projectionNum";


    public static CustomTableHeaderDTO getSalesProjectionResultsCalculatedColumns(Map selection, CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        ForecastDTO forecast = session.getForecastDTO();
        String freq = selection.get(Constant.FREQUENCY).toString();
        String projection = selection.get(Constant.ACTUALSORPROJECTIONS).toString();
        String pivot = selection.get(Constant.VIEW).toString();
        int curMonth = forecast.getForecastStartMonth();

        int curYear = forecast.getForecastStartYear();
        int current = 1;
        int frequency = String.valueOf(selection.get(HISTORY_NUM)) != null && !Constant.NULL.equals(String.valueOf(selection.get(HISTORY_NUM))) ? Integer.parseInt(String.valueOf(selection.get(HISTORY_NUM))) : 0;
        int projectFrequency = String.valueOf(selection.get(PROJECTION_NUM)) != null && !Constant.NULL.equals(String.valueOf(selection.get(PROJECTION_NUM))) ? Integer.parseInt(String.valueOf(selection.get(PROJECTION_NUM))) : 0;
        projSelDTO.setHistoryNum(frequency);
        projSelDTO.setProjectionNum(projectFrequency);
        int division = 1;
        if (PERIOD1.equalsIgnoreCase(pivot)) {
            if (freq.equals(QUARTERLY.getConstant())) {
                current = curMonth / NumericConstants.THREE;
                division = NumericConstants.FOUR;
            } else if (freq.equals(SEMI_ANNUALLY.getConstant())) {
                current = curMonth / NumericConstants.SIX;
                division = NumericConstants.TWO;

            } else if (freq.equals(MONTHLY.getConstant())) {
                current = curMonth;
                division = NumericConstants.TWELVE;

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
            if (freq.contains(ANNUALLY.getConstant()) && !freq.contains(SEMI_ANNUALLY.getConstant())) {
                syear = current - frequency;
            }

            for (int i = 0; i < frequency; i++) {
                List<Object> dmap = new ArrayList<>();
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
                List<Object> dmap = new ArrayList<>();
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
            List<Object> dmap = new ArrayList<>();
            List<Object> dmap1 = new ArrayList<>();
            List<Object> dmap2 = new ArrayList<>();
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
                tableHeaderDTO.addDoubleColumn(Constant.CONTRACT, Constant.CONTRACT_SALES_WAC_AT);
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
            List<String> periodList = new ArrayList<>();
            Map<String, String> periodListMap = new HashMap<>();
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
            projectionOrder = NumericConstants.TWO;
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
        Map<Integer, List> periodListMapForExcel = new HashMap<>();
        if (pivotView.contains(VARIABLE.getConstant())) {

            for (int i = 0; i < NumericConstants.SIX; i++) {

                String commonColumn = StringUtils.EMPTY;
                String commonHeader = StringUtils.EMPTY;
                if (i == 0) {
                    commonColumn = "efs";
                    commonHeader = Constant.EX_FACTORY_SALES_LABEL;
                } else if (i == 1) {
                    commonColumn = "dms";
                    commonHeader = Constant.DEMAND_SALES1;
                } else if (i == NumericConstants.TWO) {
                    commonColumn = "iws";
                    commonHeader = "Inventory Withdraw";
                } else if (i == NumericConstants.THREE) {
                    if (projSelDTO.getSalesOrUnit().equalsIgnoreCase("Sales") || projSelDTO.getSalesOrUnit().equalsIgnoreCase("Both")) {
                        commonColumn = "csw";
                        commonHeader = Constant.CONTRACT_SALES_WAC_AT;
                    } else {
                        continue;
                    }
                } else if (i == NumericConstants.FOUR) {
                    if (projSelDTO.getSalesOrUnit().equalsIgnoreCase("Units") || projSelDTO.getSalesOrUnit().equalsIgnoreCase("Both")) {
                        commonColumn = "uv";
                        commonHeader = Constant.UNIT_VOLUME;
                    } else {
                        continue;
                    }
                }
                 else if (i == NumericConstants.FIVE) {
                        commonColumn = "cexs";
                        commonHeader = "Contract Sales as % of Ex-Factory Sales";
                }

                List<Object> dmap = new ArrayList<>();
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
            List<Object> singleColumnForExcel = new ArrayList<>();//Added for tabwise excel export
            List<String> singleHeaderForExcel = new ArrayList<>();
            List<Object> doubleColumnForExcel = new ArrayList<>();
            List<String> doubleHeaderForExcel = new ArrayList<>();
            Map<Object, Object[]> doubleHeaderMap = new HashMap<>();
            List headerListForExcel;
            Object leftColumn = fullHeaderDTO.getSingleColumns().get(0);
            String leftHeader = fullHeaderDTO.getSingleHeaders().get(0);
            int tempYear;
            int j = 0;
            int k = 0;//Ends here
            for (int i = 0; i < periodList.size(); i++) {
                List<Object> dmap = new ArrayList<>();
                String commonColumn = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn);
                boolean historyFlag = false;
                boolean projectionCol = false;
                boolean excelTab = frequencyDivision == NumericConstants.FOUR || frequencyDivision == NumericConstants.TWELVE;//Added for tabwise excel export
                tempYear = excelTab ?  frequencyDivision == NumericConstants.FOUR ? Integer.parseInt(commonColumn.substring(NumericConstants.TWO, commonColumn.length())) 
                        : Integer.parseInt(commonColumn.replaceAll("[^\\d.]", StringUtils.EMPTY)) : 0;
                if (k != tempYear) {
                    singleColumnForExcel = new ArrayList<>();
                    singleHeaderForExcel = new ArrayList<>();
                    headerListForExcel = new ArrayList();
                    doubleColumnForExcel = new ArrayList<>();
                    doubleHeaderForExcel = new ArrayList<>();
                    doubleHeaderMap = new HashMap<>();
                    headerListForExcel.add(singleColumnForExcel);
                    headerListForExcel.add(singleHeaderForExcel);
                    headerListForExcel.add(tempYear);
                    headerListForExcel.add(doubleColumnForExcel);
                    headerListForExcel.add(doubleHeaderForExcel);
                    headerListForExcel.add(doubleHeaderMap);
                    periodListMapForExcel.put(j, headerListForExcel);
                    j++;
                    singleColumnForExcel.add(leftColumn);
                    singleHeaderForExcel.add(leftHeader);
                    doubleColumnForExcel.add(StringUtils.EMPTY);
                    doubleHeaderForExcel.add(StringUtils.EMPTY);
                    doubleHeaderMap.put(StringUtils.EMPTY, new Object[]{leftColumn});
                    k = tempYear;
                }//Ends here
                if (i >= projSelDTO.getHistoryStartIndex() && i <= projSelDTO.getHistoryEndIndex()) {
                    historyFlag = true;

                    if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn = commonColumn + ACTUALS.getConstant();
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUALS.getConstant(), String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, ACTUALS.getConstant());
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + ACTUALS.getConstant(), String.class);
                        singleColumnForExcel.add(singleColumn);//Added for tabwise excel export
                        singleHeaderForExcel.add(ACTUALS.getConstant());//Ends here
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
                    singleColumnForExcel.add(singleColumn);//Added for tabwise excel export
                    singleHeaderForExcel.add(PROJECTIONS.getConstant());//Ends here
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    tableHeaderDTO.addDoubleProjectedColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    doubleColumnForExcel.add(commonColumn);//Added for tabwise excel export
                    doubleHeaderForExcel.add(commonHeader);
                    doubleHeaderMap.put(commonColumn, dmap.toArray());//Ends here
                }
            }

        }
        projSelDTO.setColumns(CommonUtils.objectListToStringList(tableHeaderDTO.getSingleColumns()));
        projSelDTO.setHeaderMapForExcel(periodListMapForExcel);//Added for tabwise excel export
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSalesProjectionResultsLeftTableColumns( CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn(LEVEL_VALUE_PROPERTY, " ", String.class);
        fullHeaderDTO.addSingleColumn(LEVEL_VALUE_PROPERTY, " ", String.class);
        fullHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        fullHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{LEVEL_VALUE_PROPERTY});
        return tableHeaderDTO;
    }
    
    public static CustomTableHeaderDTO getAlternateHistoryLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn(Constant.CHECK, " ", Boolean.class);
        tableHeaderDTO.addSingleColumn(Constant.CONTRACT_NO1, Constant.CONTRACT_NO_LABEL, String.class);
        tableHeaderDTO.addSingleColumn(CONTRACT_NAME1, Constant.CONTRACT_NAME_LABEL, String.class);
         tableHeaderDTO.addSingleColumn(Constant.CUSTOMER_NO, "Customer No", String.class);
        tableHeaderDTO.addSingleColumn(Constant.CUSTOMER_NAME, "Customer Name", String.class);
        tableHeaderDTO.addSingleColumn(Constant.ITEM_NO, Constant.ITEM_NO_LABEL, String.class);
        tableHeaderDTO.addSingleColumn(Constant.ITEM_NAME_SMALL_PROPERY, Constant.ITEM_NAME, String.class);

        tableHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        tableHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{Constant.CONTRACT_NO1, CONTRACT_NAME1, Constant.CUSTOMER_NO, Constant.CUSTOMER_NAME, Constant.ITEM_NO, Constant.ITEM_NAME_SMALL_PROPERY});

        fullHeaderDTO.addSingleColumn(Constant.CONTRACT_NO1, Constant.CONTRACT_NO_LABEL, String.class);
        fullHeaderDTO.addSingleColumn(CONTRACT_NAME1, Constant.CONTRACT_NAME_LABEL, String.class);
        fullHeaderDTO.addSingleColumn(Constant.CUSTOMER_NO, "Company No", String.class);
        fullHeaderDTO.addSingleColumn(Constant.CUSTOMER_NAME, "Company Name", String.class);
        fullHeaderDTO.addSingleColumn(Constant.ITEM_NO, Constant.ITEM_NO_LABEL, String.class);
        fullHeaderDTO.addSingleColumn(Constant.ITEM_NAME_SMALL_PROPERY, Constant.ITEM_NAME, String.class);

        fullHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        fullHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{Constant.CHECK, Constant.CONTRACT_NO1, CONTRACT_NAME1, Constant.CUSTOMER_NO, Constant.CUSTOMER_NAME, Constant.ITEM_NO, Constant.ITEM_NAME_SMALL_PROPERY});
        return tableHeaderDTO;
    }

    static Map<String, Integer> getHistoryAndProjVarianceDetails(Map selection, PVSelectionDTO pvsdto) {
        String frequency = selection.get(Constant.FREQUENCY).toString();
        String projFreq = selection.get(Constant.PROJECT_FREQUENCY1).toString();
        String hist = selection.get(Constant.HISTORY).toString();
        int projNum = Integer.valueOf(selection.get(PROJ_NUM).toString());
        Map<String, Integer> histProjMap = new HashMap<>();
        Calendar ob = Calendar.getInstance();
        int curMonth = ob.get(Calendar.MONTH);
        int curYear = ob.get(Calendar.YEAR);
        int currentPeriod = 1;
        int historyNum = 0;
        int projectionNum = 0;
        int frequencyDivision = 1;
        if (frequency.contains(QUARTERLY.getConstant())) {
            currentPeriod = curMonth / NumericConstants.THREE;
            frequencyDivision = NumericConstants.FOUR;
            try {
                historyNum = Integer.valueOf(hist.toLowerCase().replace("quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                projectionNum = Integer.valueOf(projFreq.toLowerCase().replace("quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        } else if (frequency.contains(SEMI_ANNUAL.getConstant())) {
            currentPeriod = curMonth / NumericConstants.SIX;
            frequencyDivision = NumericConstants.TWO;
            try {
                historyNum = Integer.valueOf(hist.toLowerCase().replace("semi-annual", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                projectionNum = Integer.valueOf(projFreq.toLowerCase().replace("semi-annual", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        } else if (frequency.contains(MONTHLY.getConstant())) {
            currentPeriod = curMonth;
            frequencyDivision = NumericConstants.TWELVE;
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
        histProjMap.put(FREQUENCY_DIVISION1, frequencyDivision);
        histProjMap.put(HISTORY_NUM, historyNum);
        histProjMap.put(PROJECTION_NUM, projectionNum);
        histProjMap.put(CURRENT_PERIOD, currentPeriod);
        histProjMap.put(CURRENT_YEAR, curYear);
        histProjMap.put(PROJ_NUM, projNum);

        pvsdto.setFrequencyDivision(frequencyDivision);
        pvsdto.setHistoryNum(historyNum);
        pvsdto.setProjectionNum(projectionNum);
        pvsdto.setCurrentPeriod(currentPeriod);
        pvsdto.setCurrentYear(curYear);
        histProjMap = getHistoryDetail(histProjMap, pvsdto);
        histProjMap = getProjectionDetail(histProjMap, pvsdto);
        return histProjMap;
    }
    public static final String PROJ_NUM = "projNum";
    public static final String FREQUENCY_DIVISION1 = "frequencyDivision";
    public static final String CURRENT_PERIOD = "currentPeriod";
    public static final String CURRENT_YEAR = "currentYear";

    static Map<String, Integer> getHistoryDetail(Map<String, Integer> histProjMap, PVSelectionDTO pvsdto) {
        int startYear = histProjMap.get(CURRENT_YEAR);
        int currentPeriod = histProjMap.get(CURRENT_PERIOD);
        int historyNum = histProjMap.get(HISTORY_NUM);
        int frequencyDivision = histProjMap.get(FREQUENCY_DIVISION1);
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
            startMonth = frequencyDivision;
        } else if (frequencyDivision == NumericConstants.TWO) {
            if (startFreq == 1) {
                startMonth = 1;
            } else if (startFreq == NumericConstants.TWO) {
                startMonth = NumericConstants.SEVEN;
            }
        } else if (frequencyDivision == NumericConstants.FOUR) {
            if (startFreq == 1) {
                startMonth = 1;
            } else if (startFreq == NumericConstants.TWO) {
                startMonth = NumericConstants.FOUR;
            } else if (startFreq == NumericConstants.THREE) {
                startMonth = NumericConstants.SEVEN;
            } else if (startFreq == NumericConstants.FOUR) {
                startMonth = NumericConstants.TEN;
            }
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            startMonth = startFreq;
        }
        histProjMap.put("startDay", startDay);
        histProjMap.put("startMonth", startMonth);
        pvsdto.setStartDay(startDay);
        pvsdto.setStartMonth(startMonth);
        return histProjMap;
    }

    static Map<String, Integer> getProjectionDetail(Map<String, Integer> histProjMap, PVSelectionDTO pvsdto) {
        int endYear = histProjMap.get(CURRENT_YEAR);
        int currentPeriod = histProjMap.get(CURRENT_PERIOD);
        int projectionNum = histProjMap.get(PROJ_NUM);
        int frequencyDivision = histProjMap.get(FREQUENCY_DIVISION1);
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
            endMonth = NumericConstants.TWELVE;
        } else if (frequencyDivision == NumericConstants.TWO) {
            if (endFreq == 1) {
                endMonth = NumericConstants.SIX;
            } else if (endFreq == NumericConstants.TWO) {
                endMonth = NumericConstants.TWELVE;
            }
        } else if (frequencyDivision == NumericConstants.FOUR) {
            if (endFreq == 1) {
                endMonth = NumericConstants.THREE;
            } else if (endFreq == NumericConstants.TWO) {
                endMonth = NumericConstants.SIX;
            } else if (endFreq == NumericConstants.THREE) {
                endMonth = NumericConstants.NINE;
            } else if (endFreq == NumericConstants.FOUR) {
                endMonth = NumericConstants.TWELVE;
            }
        } else if (frequencyDivision == NumericConstants.TWELVE) {
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
        LOGGER.debug("Entering setBaseVariables method");
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

        LOGGER.debug("End of setBaseVariables method");
    }

    static CustomTableHeaderDTO loadSingleHeader(String commonColumn, final String commonHeader, final PVSelectionDTO selection, final CustomTableHeaderDTO tableHeaderDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String currentProjName = selection.getCurrentProjectionName();
        String variableCategory = selection.getVariableCategory() != null ? selection.getVariableCategory() : StringUtils.EMPTY;
        List<Integer> projList = selection.getProjIdList();
        Map<Integer, String> priorMap = selection.getProjectionMap();
        boolean disc = true;
        while (disc) {
            List<Object> dmap = new ArrayList<>();
            if (variableCategory.contains(Constant.ACTUALS)) {
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACTUAL + selection.getCurrentProjId(), Constant.ACTUALS, String.class);
                fullHeaderDTO.addSingleColumn(commonColumn + Constant.ACTUAL + selection.getCurrentProjId(), commonHeader + " " + Constant.ACTUALS, String.class);
            } 
            if (variableCategory.contains(Constant.ACCRUALS)) {
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACCRUAL + selection.getCurrentProjId(), Constant.ACCRUALS, String.class);
                fullHeaderDTO.addSingleColumn(commonColumn + Constant.ACCRUAL + selection.getCurrentProjId(), commonHeader + " " + Constant.ACCRUALS, String.class);
            }
            tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT + selection.getCurrentProjId(), currentProjName, String.class);
            fullHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT + selection.getCurrentProjId(), commonHeader + " " + currentProjName, String.class);
            if (variableCategory.contains(Constant.ACTUALS)) {
                dmap.add(commonColumn + Constant.ACTUAL + selection.getCurrentProjId());
            }
            if (variableCategory.contains(Constant.ACCRUALS)) {
                dmap.add(commonColumn + Constant.ACCRUAL + selection.getCurrentProjId());
            }
            dmap.add(commonColumn + Constant.CURRENT + selection.getCurrentProjId());
            if (!projList.isEmpty()) {
                for (int j = 0; j < projList.size(); j++) {
                    tableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                    fullHeaderDTO.addSingleColumn(commonColumn + projList.get(j), commonHeader + " " + priorMap.get(projList.get(j)), String.class);
                    dmap.add(commonColumn + projList.get(j));
                }
            }
            disc = false;
            if (!dmap.isEmpty()) {
                tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                fullHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                fullHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
            }

        }
        return tableHeaderDTO;
    }

    static CustomTableHeaderDTO loadSingleDiscountHeader(String commonColumn, String commonHeader, final PVSelectionDTO selection, final CustomTableHeaderDTO tableHeaderDTO, final CustomTableHeaderDTO fullHeader, Map<String, Object> headerMap) {
        String column = commonColumn;
        String variableCategory = selection.getVariableCategory() != null ? selection.getVariableCategory() : StringUtils.EMPTY;
        List<String> discountNames = new ArrayList<>(selection.getDeductionLevelCaptions());
        //PPA
        List list = CommonLogic.getPPADiscountNameList(selection);
        if (list != null) {
            discountNames.addAll(list);
        }
        List<Integer> projList = selection.getProjIdList();
        Map<Integer, String> priorMap = selection.getProjectionMap();
        List<String> commonColumnList = new ArrayList<>();
        List<Object> dmap = new ArrayList<>();
        if (!discountNames.isEmpty()) {

            for (int i = 0; i < discountNames.size(); i++) {
                dmap.clear();
                String disCommonHeader = discountNames.get(i);
                commonColumn = column + disCommonHeader.replace(" ", StringUtils.EMPTY) + i;
                String name = String.valueOf(discountNames.get(i)).replaceAll(" ", StringUtils.EMPTY);
                selection.addDiscountNameMap(name,i);
                
                if (variableCategory.contains(Constant.ACTUALS)) {
                    tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACTUAL + selection.getCurrentProjId(), Constant.ACTUALS, String.class);
                    fullHeader.addSingleColumn(commonColumn + Constant.ACTUAL + selection.getCurrentProjId(), disCommonHeader + " " + Constant.ACTUALS, String.class);
                    dmap.add(commonColumn + Constant.ACTUAL + selection.getCurrentProjId());
                }

                if (variableCategory.contains(Constant.ACCRUALS)) {
                    tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACCRUAL + selection.getCurrentProjId(), Constant.ACCRUAL, String.class);
                    fullHeader.addSingleColumn(commonColumn + Constant.ACCRUAL + selection.getCurrentProjId(), disCommonHeader + " " + Constant.ACCRUAL, String.class);
                    dmap.add(commonColumn + Constant.ACCRUAL + selection.getCurrentProjId());
                }


                tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT + selection.getCurrentProjId(), selection.getCurrentProjectionName(), String.class);
                fullHeader.addSingleColumn(commonColumn + Constant.CURRENT + selection.getCurrentProjId(), disCommonHeader + " " + selection.getCurrentProjectionName(), String.class);
                dmap.add(commonColumn + Constant.CURRENT + selection.getCurrentProjId());
                if (!projList.isEmpty()) {
                    for (int j = 0; j < projList.size(); j++) {
                        tableHeaderDTO.addSingleColumn(commonColumn + projList.get(j), priorMap.get(projList.get(j)), String.class);
                        fullHeader.addSingleColumn(commonColumn + projList.get(j), disCommonHeader + " " + priorMap.get(projList.get(j)), String.class);
                        dmap.add(commonColumn + projList.get(j));
                    }
                }
                if (!dmap.isEmpty()) {
                    tableHeaderDTO.addDoubleColumn(commonColumn, disCommonHeader);
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    fullHeader.addDoubleColumn(commonColumn, commonHeader);
                    fullHeader.addDoubleHeaderMap(commonColumn, dmap.toArray());
                }
                commonColumnList.add(commonColumn);
                headerMap.put(column, commonColumnList);
            }

        }

        selection.setHeaderMap(headerMap);

        return tableHeaderDTO;
    }

    public static List<String> getCommonColumnHeaderForPV(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = Constant.Q + period + StringUtils.EMPTY + year;
            commonHeader = Constant.Q + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = Constant.S + period + StringUtils.EMPTY + year;
            commonHeader = Constant.S + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    public static CustomTableHeaderDTO getDiscountProjectionResultsLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
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
            projectionOrder = NumericConstants.TWO;
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
                    String commonColumn;
                    String commonHeader = discountNames.get(i);
                    commonColumn = commonHeader.replace(" ", StringUtils.EMPTY);
                    List<Object> dmap = new ArrayList<>();
                    if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains(Constant.ACTUALS_PROPERTY) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn1 = commonColumn + ACTUALRATE.getConstant();
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                        Object singleColumn2 = commonColumn + ACTUALAMOUNT.getConstant();
                        dmap.add(singleColumn2);
                        tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);
                    }
                    if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains(PROJECTIONS1) || projections.contains(PROJECTIONS.getConstant())) {
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
                Object doubleCol = EMPTY_STRING;
                Object[] singleCol = {EMPTY_STRING};
                tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
                tableHeaderDTO.addDoubleColumn(doubleCol, " ");
                tableHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
            }

            int startPr = startPeriod;
            int lastPr = frequencyDivision;
            List<String> periodList = new ArrayList<>();
            Map<String, String> periodListMap = new HashMap<>();
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

                        List<Object> dmap = new ArrayList<>();
                        List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                            if ((hist) && (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains(Constant.ACTUALS_PROPERTY) || projections.contains(ACTUALS.getConstant()))) {
                                Object singleColumn1 = commonColumn + ACTUALRATE.getConstant();
                                dmap.add(singleColumn1);
                                tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                                fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                                Object singleColumn2 = commonColumn + ACTUALAMOUNT.getConstant();
                                dmap.add(singleColumn2);
                                tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                                fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);

                            }
                        if (hist && (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains(PROJECTIONS1) || projections.contains(PROJECTIONS.getConstant())) || proj) {

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

                        List<Object> dmap = new ArrayList<>();
                        List<String> common = getCommonColumnHeaderForDiscount(frequencyDivision, yr, pr);
                        String commonColumn = common.get(0);
                        String commonHeader = common.get(1);
                            if ((hist) && (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains(Constant.ACTUALS_PROPERTY) || projections.contains(ACTUALS.getConstant()))) {
                                Object singleColumn1 = commonColumn + ACTUALRATE.getConstant();
                                dmap.add(singleColumn1);
                                tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                                fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                                Object singleColumn2 = commonColumn + ACTUALAMOUNT.getConstant();
                                dmap.add(singleColumn2);
                                tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                                fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);
                            }
                            if ((hist || proj) && (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains(PROJECTIONS1) || projections.contains(PROJECTIONS.getConstant()))) {
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
    public static final String EMPTY_STRING = "empty";

    public static List<String> getCommonColumnHeaderForDiscount(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = Constant.Q + period + StringUtils.EMPTY + year;
            commonHeader = Constant.Q + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = Constant.S + period + StringUtils.EMPTY + year;
            commonHeader = Constant.S + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWELVE) {
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
        List<String> discountNames = new ArrayList<>();
        if (projSelDTO.getDeductionLevelFilter().isEmpty()) {
             projSelDTO.setDeductionLevelCaptions(new DiscountProjectionLogic().getRsAllList(projSelDTO));
        } 
        discountNames = new ArrayList<>(projSelDTO.getDeductionLevelCaptions());;
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
            projectionOrder = NumericConstants.TWO;
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
                List<Object> dmap = new ArrayList<>();
                String commonColumn1 = periodList.get(i);
                String commonHeader = periodListMap.get(commonColumn1);
                String commonColumn = commonColumn1;
                if (frequencyDivision != NumericConstants.TWELVE) {
                    commonColumn = commonColumn1.toUpperCase();
                }
                if(variableCategory.contains(Constant.ACTUALS)){
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACTUAL + projSelDTO.getCurrentProjId(), Constant.ACTUALS, String.class);
                fullHeader.addSingleColumn(commonColumn + Constant.ACTUAL + projSelDTO.getCurrentProjId(), commonHeader + " " + Constant.ACTUALS, String.class);
                }
                if(variableCategory.contains(Constant.ACCRUALS)){
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.ACCRUAL + projSelDTO.getCurrentProjId(), Constant.ACCRUALS, String.class);
                fullHeader.addSingleColumn(commonColumn + Constant.ACCRUAL + projSelDTO.getCurrentProjId(), commonHeader + " " + Constant.ACCRUALS, String.class);
                }
                tableHeaderDTO.addSingleColumn(commonColumn + Constant.CURRENT + projSelDTO.getCurrentProjId(), currentProjName, String.class);
                tableHeaderDTO.addSingleProjectedColumn(commonColumn + Constant.CURRENT + projSelDTO.getCurrentProjId(), Constant.CURRENT);
                fullHeader.addSingleColumn(commonColumn + Constant.CURRENT + projSelDTO.getCurrentProjId(), commonHeader + " " + currentProjName, String.class);
                if(variableCategory.contains(Constant.ACTUALS)){
                dmap.add(commonColumn + Constant.ACTUAL + projSelDTO.getCurrentProjId());
                }
                if(variableCategory.contains(Constant.ACCRUALS)){
                dmap.add(commonColumn + Constant.ACCRUAL + projSelDTO.getCurrentProjId());
                }
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
                    fullHeader.addDoubleColumn(commonColumn, commonHeader);
                    fullHeader.addDoubleHeaderMap(commonColumn, dmap.toArray());
                    fullHeader.addDoubleProjectedColumn(commonColumn, commonHeader);
                }
            }
        } else {
            setBaseVariables(variableCategory, variable, projSelDTO);
            Map<String, Object> headerMap = new HashMap<>();
            String commonColumn;

            String commonHeader;

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
                    commonHeader = "Contract Sales % Of Ex-Factory Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "PerExFacVariance";
                    commonHeader = "Contract Sales % Of Ex-Factory variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "PerExFacPer";
                    commonHeader = "Contract Sales % Of Ex-Factory % change";
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
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && !discountNames.isEmpty()) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader,headerMap);
                    }
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "DiscountAmountVar";
                    commonHeader = "Discount $ Variance";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && !discountNames.isEmpty()) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "DiscountAmountPer";
                    commonHeader = "Discount $ % change";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && !discountNames.isEmpty()) {
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
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && !discountNames.isEmpty()) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "DiscountSalesVar";
                    commonHeader = "Discount % Variance";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && !discountNames.isEmpty()) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "DiscountSalesPer";
                    commonHeader = "Discount % % Change";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && !discountNames.isEmpty()) {
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
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && !discountNames.isEmpty()) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "RPUVar";
                    commonHeader = "RPU Variance";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && !discountNames.isEmpty()) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "RPUPer";
                    commonHeader = "RPU %Change";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && !discountNames.isEmpty()) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }
                }
            }
            
             if (projSelDTO.isDiscountPerExFactory()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "DiscountPerExFactoryValue";
                    commonHeader = "Discount % Of Ex-Factory Value";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && !discountNames.isEmpty()) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }
                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "DiscountPerExFactoryVar";
                    commonHeader = "Discount % Of Ex-Factory variance";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && !discountNames.isEmpty()) {
                        tableHeaderDTO = loadSingleDiscountHeader(commString, commonHeader, projSelDTO, tableHeaderDTO,fullHeader, headerMap);
                    }
                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "DiscountPerExFactoryPer";
                    commonHeader = "Discount % Of Ex-Factory % change";
                    String commString = commonColumn;
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    if (!projSelDTO.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant()) && !discountNames.isEmpty()) {
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
             if (projSelDTO.isNetSalesExFactory()) {
                if (projSelDTO.isColValue()) {
                    commonColumn = "NetSalesExFactoryValue";
                    commonHeader = "Net Sales % of Ex-Factory Value";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                }
                if (projSelDTO.isColVariance()) {
                    commonColumn = "NetSalesExFactoryVariance";
                    commonHeader = "Net Sales % of Ex-Factory Variance";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                }
                if (projSelDTO.isColPercentage()) {
                    commonColumn = "NetSalesExFactoryPer";
                    commonHeader = "Net Sales % of Ex-Factory %Change";
                    tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                }
            }
            if (Constant.DETAIL.equals(projSelDTO.getLevel()) && (projSelDTO.getView().equals(PRODUCT.getConstant()) || projSelDTO.getView().equals(CUSTOM.getConstant()))) {
                if (projSelDTO.isNetExFactorySales()) {
                    if (projSelDTO.isColValue()) {
                        commonColumn = Constant.NET_EXFACT_SALES_COLUMN_VALUE;
                        commonHeader = Constant.NET_EXFACT_SALES_HEADER_VALUE;
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                    }
                    if (projSelDTO.isColVariance()) {
                        commonColumn = Constant.NET_EXFACT_SALES_COLUMN_VARIANCE;
                        commonHeader = Constant.NET_EXFACT_SALES_HEADER_VARIANCE;
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                    }
                    if (projSelDTO.isColPercentage()) {
                        commonColumn = Constant.NET_EXFACT_SALES_COLUMN_PER_CHANGE;
                        commonHeader = Constant.NET_EXFACT_SALES_HEADER_PER_CHANGE;
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    }
                }
                if (projSelDTO.isNetExFactorySalesPerExFactory()) {
                    if (projSelDTO.isColValue()) {
                        commonColumn = Constant.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VALUE;
                        commonHeader = Constant.NET_EXFACT_SALES_PER_EXFACT_HEADER_VALUE;
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                    }
                    if (projSelDTO.isColVariance()) {
                        commonColumn = Constant.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VARIANCE;
                        commonHeader = Constant.NET_EXFACT_SALES_PER_EXFACT_HEADER_VARIANCE;
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);

                    }
                    if (projSelDTO.isColPercentage()) {
                        commonColumn = Constant.NET_EXFACT_SALES_PER_EXFACT_COLUMN_PER_CHANGE;
                        commonHeader = Constant.NET_EXFACT_SALES_PER_EXFACT_HEADER_PER_CHANGE;
                        tableHeaderDTO = loadSingleHeader(commonColumn, commonHeader, projSelDTO, tableHeaderDTO, fullHeader);
                    }
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
           
            List<String> pivotList = new ArrayList<>();
            List<String> periodList = projSelDTO.getPeriodList();
            List<String> periodListUpper = new ArrayList<>();
            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            Map<String, String> periodListMapUpper = new HashMap<>();
            for (int i = 0; i < periodList.size(); i++) {
                String commonColumn1 = periodList.get(i);
                String comHeader = periodListMap.get(commonColumn1);
                String comColumn = commonColumn1;
                if (frequencyDivision != NumericConstants.TWELVE) {
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
        List<Object> headerContents = new ArrayList<>();
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
            frequencyDivision = NumericConstants.FOUR;
            historyStartPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), NumericConstants.THREE);
            historyEndPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), NumericConstants.THREE);
            forecastStartPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), NumericConstants.THREE);
            forecastEndPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), NumericConstants.THREE);
            projectionStartPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), NumericConstants.THREE);
            projectionEndPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), NumericConstants.THREE);
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequencyDivision = NumericConstants.TWO;
            historyStartPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryStartMonth(), NumericConstants.SIX);
            historyEndPeriod = getPeriod(projSelDTO.getForecastDTO().getHistoryEndMonth(), NumericConstants.SIX);
            forecastStartPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastStartMonth(), NumericConstants.SIX);
            forecastEndPeriod = getPeriod(projSelDTO.getForecastDTO().getForecastEndMonth(), NumericConstants.SIX);
            projectionStartPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionStartMonth(), NumericConstants.SIX);
            projectionEndPeriod = getPeriod(projSelDTO.getForecastDTO().getProjectionEndMonth(), NumericConstants.SIX);
        } else if (frequency.equals(MONTHLY.getConstant())) {
            frequencyDivision = NumericConstants.TWELVE;
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
                historyEndMonth = NumericConstants.TWELVE;
                historyEndYear = historyEndYear - 1;
            } else if (historyEndPeriod < 1) {
                historyEndMonth = NumericConstants.TWELVE;
                historyEndYear = historyEndYear - 1;
            } else if (frequencyDivision == NumericConstants.TWO) {
                if (historyEndPeriod == 1) {
                    historyEndMonth = NumericConstants.SIX;
                } else if (historyEndPeriod == NumericConstants.TWO) {
                    historyEndMonth = NumericConstants.TWELVE;
                }
            } else if (frequencyDivision == NumericConstants.FOUR) {
                if (historyEndPeriod == 1) {
                    historyEndMonth = NumericConstants.THREE;
                } else if (historyEndPeriod == NumericConstants.TWO) {
                    historyEndMonth = NumericConstants.SIX;
                } else if (historyEndPeriod == NumericConstants.THREE) {
                    historyEndMonth = NumericConstants.NINE;
                } else if (historyEndPeriod == NumericConstants.FOUR) {
                    historyEndMonth = NumericConstants.TWELVE;
                }
            } else if (frequencyDivision == NumericConstants.TWELVE) {
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
            projectionOrder = NumericConstants.TWO;
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
                    String commonColumn;
                    String commonHeader = discountNames.get(i);
                    commonColumn = commonHeader.replace(" ", StringUtils.EMPTY);
                    List<Object> dmap = new ArrayList<>();
                    if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains(Constant.ACTUALS_PROPERTY) || projections.contains(ACTUALS.getConstant())) {
                        Object singleColumn1 = commonColumn + ACTUALRATE.getConstant();
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_RATE.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUAL_RATE.getConstant(), String.class);

                        Object singleColumn2 = commonColumn + ACTUALAMOUNT.getConstant();
                        dmap.add(singleColumn2);
                        tableHeaderDTO.addSingleColumn(singleColumn2, ACTUAL_AMOUNT.getConstant(), String.class);
                        fullHeaderDTO.addSingleColumn(singleColumn2, commonHeader + " " + ACTUAL_AMOUNT.getConstant(), String.class);
                    }
                    if (projections.contains(Constant.BOTH_SMALL) || projections.contains(Constant.BOTH) || projections.contains(PROJECTIONS1) || projections.contains(PROJECTIONS.getConstant())) {
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
                Object doubleCol = EMPTY_STRING;
                Object[] singleCol = {EMPTY_STRING};
                tableHeaderDTO.addSingleColumn(singleCol[0], " ", String.class);
                tableHeaderDTO.addDoubleColumn(doubleCol, " ");
                tableHeaderDTO.addDoubleHeaderMap(doubleCol, singleCol);
            }
        } else {

            List<String> periodList = projSelDTO.getPeriodList();

            Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
            for (int i = 0; i < periodList.size(); i++) {
                List<Object> dmap = new ArrayList<>();
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
        List<String> discountNames = new ArrayList<>(selection.getDiscountNameList());
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
        LOGGER.debug("Level"+projSelDTO.getLevel());
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("relationshipLevelName", Constant.CUSTOMER_SMALL, String.class);
        fullHeaderDTO.addSingleColumn("relationshipLevelName", Constant.CUSTOMER_SMALL, String.class);
        fullHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        fullHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{LEVEL_VALUE_PROPERTY});

        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSalesProjectionResultsRightTableColumns(ProjectionSelectionDTO projectionDTO, CustomTableHeaderDTO fullHeaderDTO) {
        LOGGER.debug("----Inside getSalesProjectionResultsRightTableColumns() -----");
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getSalesProjectionResultsCalculatedColumns(tableHeaderDTO, projectionDTO, fullHeaderDTO);
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
            projectionOrder = NumericConstants.TWO;
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

            for (int i = 0; i < NumericConstants.FIVE; i++) {

                String commonColumn = StringUtils.EMPTY;
                String commonHeader = StringUtils.EMPTY;
                if (i == 0) {
                    commonColumn = "efs";
                    commonHeader = Constant.EX_FACTORY_SALES_LABEL;
                } else if (i == 1) {
                    commonColumn = "dms";
                    commonHeader = Constant.DEMAND_SALES1;
                } else if (i == NumericConstants.TWO) {
                    commonColumn = "iws";
                    commonHeader = "Inventory Withdraw";
                } else if (i == NumericConstants.THREE) {
                    if (Constant.SALES_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit())) {
                        commonColumn = "csw";
                        commonHeader = Constant.CONTRACT_SALES_WAC_AT;
                    } else {
                        continue;
                    }
                } else if (i == NumericConstants.FOUR) {
                    if (Constant.UNITS_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit())) {
                        commonColumn = "uv";
                        commonHeader = Constant.UNIT_VOLUME;
                    } else {
                        continue;
                    }
                }
                
                    List<Object> dmap = new ArrayList<>();
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
                List<Object> dmap = new ArrayList<>();
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
        tableHeaderDTO.addSingleColumn(LEVEL_VALUE_PROPERTY, LEVEL_NAME1, String.class);
        tableHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        tableHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{LEVEL_VALUE_PROPERTY});
        fullHeaderDTO.addSingleColumn(LEVEL_VALUE_PROPERTY, LEVEL_NAME1, String.class);
        fullHeaderDTO.addDoubleColumn(Constant.GROUP, StringUtils.EMPTY);
        fullHeaderDTO.addDoubleHeaderMap(Constant.GROUP, new Object[]{LEVEL_VALUE_PROPERTY});
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSalesProjectionResultsRightTableColumnsChannel(ProjectionSelectionDTO projectionDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getSalesProjectionResultsCalculatedColumnsChannel(tableHeaderDTO, projectionDTO, fullHeaderDTO);
    }

    public static CustomTableHeaderDTO getSupplementalLeftTableColumns(CustomTableHeaderDTO fullHeaderDTO, String group) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Object[] singleCol = {Constant.GROUP};
        tableHeaderDTO.addSingleColumn(Constant.CHECK, " ", Boolean.class);
        tableHeaderDTO.addSingleColumn(singleCol[0], group, String.class);
        fullHeaderDTO.addSingleColumn(Constant.CHECK, " ", Boolean.class);
        fullHeaderDTO.addSingleColumn(singleCol[0], group, String.class);

        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSupplementalrightTableColumns(ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        Map<Integer, List> periodListMapForExcel = new HashMap<>();//Added for tabwise excel export
        List<Object> singleColumnForExcel = new ArrayList<>();
        List<String> singleHeaderForExcel = new ArrayList<>();
        List<Object> doubleColumnForExcel = new ArrayList<>();
        List<String> doubleHeaderForExcel = new ArrayList<>();
        Map<Object, Object[]> doubleHeaderMap = new HashMap<>();
        List headerListForExcel;
        int  j = 0, k = 0;// Ends here
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

        List<String> periods = new ArrayList<>();
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

        int lastPr = NumericConstants.FOUR;
        String disable = StringUtils.EMPTY;
        int sizeToCopy = fullHeaderDTO.getSingleColumns().size();//Added for tabwise excel export
        List<Object> singleColumns = new ArrayList<>(fullHeaderDTO.getSingleColumns().subList(1, sizeToCopy));
        List<String> singleHeaders = new ArrayList<>(fullHeaderDTO.getSingleHeaders().subList(1, sizeToCopy));//Ends here

        for (int yr = projSelDTO.getProjectionStartYear(); yr <= endYear; yr++) {
            if (k != yr) {//Added for tabwise excel export
                singleColumnForExcel = new ArrayList<>();
                singleHeaderForExcel = new ArrayList<>();
                headerListForExcel = new ArrayList();
                doubleColumnForExcel = new ArrayList<>();
                doubleHeaderForExcel = new ArrayList<>();
                doubleHeaderMap = new HashMap<>();
                headerListForExcel.add(singleColumnForExcel);
                headerListForExcel.add(singleHeaderForExcel);
                headerListForExcel.add(yr);
                headerListForExcel.add(doubleColumnForExcel);
                headerListForExcel.add(doubleHeaderForExcel);
                headerListForExcel.add(doubleHeaderMap);
                singleColumnForExcel.addAll(singleColumns);
                singleHeaderForExcel.addAll(singleHeaders);
                doubleColumnForExcel.add(StringUtils.EMPTY);
                doubleHeaderForExcel.add(StringUtils.EMPTY);
                doubleHeaderMap.put(StringUtils.EMPTY, singleColumnForExcel.toArray());
                periodListMapForExcel.put(j, headerListForExcel);
                j++;
                k = yr;
            }//Ends here
            if (yr == endYear) {
                lastPr = endPeriod;
            }

            for (int pr = startPr; pr <= lastPr; pr++) {

                List<Object> dmap = new ArrayList<>();
                if (forecastDateLarger && pr > projEndPeriod && yr >= projEndYear) {
                    disable = "disable";
                }
                List<String> common = getCommonColumnHeader(NumericConstants.FOUR, yr, pr);
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
                        singleColumnForExcel.add(supplemental);//Added for tabwise excel export
                        singleHeaderForExcel.add(suppColumn);//Ends here
                    } else {
                        tableHeaderDTO.addSingleColumn(supplemental, suppColumn, String.class);
                        fullHeaderDTO.addSingleColumn(supplemental, commonHeader + " " + suppColumn, String.class);
                        singleColumnForExcel.add(supplemental);//Added for tabwise excel export
                        singleHeaderForExcel.add(suppColumn);//Ends here
                    }
                }
                if (!dmap.isEmpty()) {

                    tableHeaderDTO.addDoubleColumn(commonColumn, commonHeader);
                    Object[] singleMethodCol1 = dmap.toArray();
                    tableHeaderDTO.addDoubleHeaderMap(commonColumn, singleMethodCol1);
                    doubleColumnForExcel.add(commonColumn);//Added for tabwise excel export
                    doubleHeaderForExcel.add(commonHeader);
                    doubleHeaderMap.put(commonColumn, singleMethodCol1);//Ends here
                }
                startPr = 1;
            }
        }
        projSelDTO.setHeaderMapForExcel(periodListMapForExcel);//Added for tabwise excel export
        return tableHeaderDTO;

    }

    public static int getQuarter(int projectionStartMonth) {
        if (projectionStartMonth <= NumericConstants.THREE) {
            projectionStartMonth = 1;
        } else if (projectionStartMonth <= NumericConstants.SIX) {
            projectionStartMonth = NumericConstants.TWO;
        } else if (projectionStartMonth <= NumericConstants.NINE) {
            projectionStartMonth = NumericConstants.THREE;
        } else if (projectionStartMonth <= NumericConstants.TWELVE) {
            projectionStartMonth = NumericConstants.FOUR;
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
            projectionOrder = NumericConstants.TWO;
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
            for (int i = 0; i < NumericConstants.THREE; i++) {
                String commonColumn = StringUtils.EMPTY;
                String commonHeader = StringUtils.EMPTY;
                if (i == 0) {
                    commonColumn = "gts";
                    commonHeader = "Gross Trade Sales";
                } else if (i == 1 && (Constant.SALES_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit()))) {
                    commonColumn = "csw";
                    commonHeader = Constant.CONTRACT_SALES_WAC_AT;
                } else if (i == NumericConstants.TWO && (Constant.UNITS_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit()))) {
                    commonColumn = "uv";
                    commonHeader = Constant.UNIT_VOLUME;
                }
                List<Object> dmap = new ArrayList<>();
                if (i == 0 || (i == 1 && (Constant.SALES_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit()))
                        || (i == NumericConstants.TWO && (Constant.UNITS_SMALL.equalsIgnoreCase(projSelDTO.getSalesOrUnit()) || Constant.BOTH.equalsIgnoreCase(projSelDTO.getSalesOrUnit()))))) {

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
                List<Object> dmap = new ArrayList<>();
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
        LOGGER.debug("Entering getCalculatedDiscountProjectionColumns ");
        CustomTableHeaderDTO tableHeader = new CustomTableHeaderDTO();
        CustomTableHeaderDTO excelHeader = new CustomTableHeaderDTO();
        try {
            String discountName = projSelDTO.getDiscountName();
            String actualsOrProjections = projSelDTO.getActualsOrProjections();
            String type;
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
            List<Object> tmap = new ArrayList<>();
            for (int i = 0; i < columnsList.size(); i++) {
                List<Object> dmap = new ArrayList<>();
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
                        tableHeader.addSingleColumn(singleColumn, ACTUAL_COLUMN + type, String.class);

                        excelHeader.addSingleColumn(singleColumn, discountName + " " + commonHeader + " Actual " + type, String.class);
                        tableHeader.addSingleHistoryColumn(singleColumn, ACTUAL_COLUMN + type);
                    }
                }

                if (i >= projSelDTO.getProjectionStartIndex() && i <= projSelDTO.getProjectionEndIndex()) {
                    projectionFlag = true;
                    projectionCol = true;
                    if (historyActualFlag) {
                        tableHeader.addSingleHistoryColumn(singleColumn, ACTUAL_COLUMN + type);
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
                    tableHeader.addSingleColumn(singleColumn, PROJECTED_COLUMN + type, String.class);
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
                        tableHeader.addSingleHistoryColumn(singleColumn, PROJECTED_COLUMN + type);
                    }
                    if (projectionFlag) {

                        tableHeader.addSingleProjectedColumn(singleColumn, PROJECTED_COLUMN + type);
                        if (PROJECTED.getConstant().equalsIgnoreCase(projType)) {
                            tableHeader.addSingleProjectedColumn(singleProduct, Constant.PRODUCT_GROWTH);
                            tableHeader.addSingleProjectedColumn(singleAccount, Constant.ACCOUNT_GROWTH);
                            excelHeader.addSingleProjectedColumn(singleProduct, Constant.PRODUCT_GROWTH);
                            excelHeader.addSingleProjectedColumn(singleAccount, Constant.ACCOUNT_GROWTH);
                        }
                    }
                    if (forecastFlag) {
                        tableHeader.addSingleForecastColumn(singleColumn, PROJECTED_COLUMN + type);
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
        LOGGER.debug("Ending getCalculatedDiscountProjectionColumns ");
        List<Object> headerContents = new ArrayList<>();

        headerContents.add(tableHeader);
        headerContents.add(excelHeader);
        return headerContents;
    }
    public static final String ACTUAL_COLUMN = "Actual ";
    public static final String PROJECTED_COLUMN = "Projected ";

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
            projectionOrder = NumericConstants.TWO;
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
        projSelDTO.setFunctionality(ALTERNATE_HISTORY);
        prepareCommonColumnHeaders(projSelDTO);
        List<String> periodList = projSelDTO.getPeriodList();

        Map<String, String> periodListMap = projSelDTO.getPeriodListMap();
        for (int i = 0; i < periodList.size(); i++) {
            List<Object> dmap = new ArrayList<>();
            String commonColumn = periodList.get(i);

            String commonHeader = periodListMap.get(commonColumn);
            if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                if (Constant.SALES_PROJECTION.equals(session.getForecastName())) {
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
                            tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_UNITS1, String.class);
                            tableHeaderDTO.addSingleProjectedColumn(singleColumn1, ACTUALS.getConstant());
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + ACTUALS.getConstant(), String.class);
                        }

                    } else {

                        Object singleColumn1 = commonColumn + "-ActualUnits";
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, ACTUAL_UNITS1, String.class);
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
                if (Constant.SALES_PROJECTION.equals(session.getForecastName())) {
                    if (!projSelDTO.isIsSumarry()) {
                        if (projSelDTO.getVariableList().contains(Constants.LabelConstants.SALES.getConstant())) {
                            Object singleColumn = commonColumn + PROJECTION_SALES;
                            dmap.add(singleColumn);
                            tableHeaderDTO.addSingleColumn(singleColumn, "Projection Sales", String.class);
                            tableHeaderDTO.addSingleHistoryColumn(singleColumn, PROJECTION_SALES);
                            fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                        }
                        if (projSelDTO.getVariableList().contains(Constants.LabelConstants.UNITS.getConstant())) {
                            Object singleColumn1 = commonColumn + "projectionUnits";
                            dmap.add(singleColumn1);
                            tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTION_UNITS, String.class);
                            tableHeaderDTO.addSingleProjectedColumn(singleColumn1, PROJECTIONS.getConstant());
                            fullHeaderDTO.addSingleColumn(singleColumn1, commonHeader + " " + PROJECTIONS.getConstant(), String.class);
                        }
                    } else {

                        Object singleColumn1 = commonColumn + "-ProjectionUnits";
                        dmap.add(singleColumn1);
                        tableHeaderDTO.addSingleColumn(singleColumn1, PROJECTION_UNITS, String.class);
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
    public static final String PROJECTION_SALES = "projectionSales";
    public static final String PROJECTION_UNITS = "Projection Units";

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
            projectionOrder = NumericConstants.TWO;
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
            String commonColumn;
            String commonHeader;

            commonColumn = "payment";
            commonHeader = "Payment";

            boolean disc = true;
            while (disc) {
                List<Object> dmap = new ArrayList<>();
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
                List<Object> dmap = new ArrayList<>();
                String commonColumn = periodList.get(i);

                String commonHeader = periodListMap.get(commonColumn);
                if (projections.contains(BOTH.getConstant()) || projections.contains(ACTUALS.getConstant())) {
                    if (Constant.SALES_PROJECTION.equals(session.getForecastName())) {
                        Object singleColumn = commonColumn + Constant.ACTUALSALES;
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, Constant.ACTUAL_SALES, String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.ACTUALSALES);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " " +Constant.ACTUAL_SALES, String.class);

                        singleColumn = commonColumn + Constant.ACTUALUNITS;
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, ACTUAL_UNITS1, String.class);
                        tableHeaderDTO.addSingleProjectedColumn(singleColumn, ACTUALS.getConstant());
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " Actual Units", String.class);
                    } else if (TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) {

                        Object singleColumn = rsName + commonColumn + ACTUAL_AMOUNT1;

                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Actual Payments", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, ACTUAL_AMOUNT1);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader +" Actual Payments", String.class);
                    }
                }
                if (projections.contains(BOTH.getConstant()) || projections.contains(PROJECTIONS.getConstant())) {
                    if (Constant.SALES_PROJECTION.equals(session.getForecastName())) {
                        Object singleColumn = commonColumn + "ProjectionSales";
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projection Sales", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, PROJECTION_SALES);
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " Projection Sales", String.class);

                        singleColumn = commonColumn + "projectionUnits";
                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, PROJECTION_UNITS, String.class);
                        tableHeaderDTO.addSingleProjectedColumn(singleColumn, PROJECTIONS.getConstant());
                        fullHeaderDTO.addSingleColumn(singleColumn, commonHeader + " Projection Units", String.class);
                    } else if (TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) {
                        Object singleColumn = rsName + commonColumn + Constant.PROJECTED_AMOUNT1;

                        dmap.add(singleColumn);
                        tableHeaderDTO.addSingleColumn(singleColumn, "Projection Payments", String.class);
                        tableHeaderDTO.addSingleHistoryColumn(singleColumn, Constant.PROJECTED_AMOUNT1);
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
        tableHeaderDTO.addSingleColumn(Constant.CONTRACT_NO1, "Contract No.", String.class);
        tableHeaderDTO.addSingleColumn(CONTRACT_NAME1, Constant.CONTRACT_NAME_LABEL, String.class);
        tableHeaderDTO.addSingleColumn(Constant.CUSTOMER_NO, "Customer No.", String.class);
        tableHeaderDTO.addSingleColumn(Constant.CUSTOMER_NAME, "Customer Name", String.class);
        tableHeaderDTO.addSingleColumn(Constant.ITEM_NO, "Item No.", String.class);
        tableHeaderDTO.addSingleColumn(Constant.ITEM_NAME_SMALL_PROPERY, Constant.ITEM_NAME, String.class);

       


        fullHeaderDTO.addSingleColumn(Constant.CONTRACT_NO1, Constant.CONTRACT_NO_LABEL, String.class);
        fullHeaderDTO.addSingleColumn(CONTRACT_NAME1, Constant.CONTRACT_NAME_LABEL, String.class);
        fullHeaderDTO.addSingleColumn(Constant.CUSTOMER_NO, "Company No", String.class);
        fullHeaderDTO.addSingleColumn(Constant.CUSTOMER_NAME, "Company Name", String.class);
        fullHeaderDTO.addSingleColumn(Constant.ITEM_NO, Constant.ITEM_NO_LABEL, String.class);
        fullHeaderDTO.addSingleColumn(Constant.ITEM_NAME_SMALL_PROPERY, Constant.ITEM_NAME, String.class);

        return tableHeaderDTO;
   
        
    }

    public static CustomTableHeaderDTO getAltHistAllocRightTableColumns(ProjectionSelectionDTO projectionSelection, CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getCalcAltHistAllocRightTableColumns(tableHeaderDTO, projectionSelection, fullHeaderDTO);
    }
    
    public static CustomTableHeaderDTO getCalcAltHistAllocRightTableColumns(CustomTableHeaderDTO tableHeaderDTO, ProjectionSelectionDTO projSelDTO, CustomTableHeaderDTO fullHeaderDTO) {
        String projections = Constant.BOTH;
        String frequency = projSelDTO.getFrequency();
        CommonUtils.getHistoryAndProjectionDetails(projSelDTO);
        int historyNum = projSelDTO.getHistoryNum();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String actual;
        String projection;

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
            List<Object> dmap = new ArrayList<>();
            String commonColumn = periodList.get(i);
            String commonHeader = periodListMap.get(commonColumn);
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
        List<String> common = new ArrayList<>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = Constant.Q_SMALL + period + "-" + year;
            commonHeader = Constant.Q + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = Constant.S_SMALL + period + "-" + year;
            commonHeader = Constant.S + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

}
