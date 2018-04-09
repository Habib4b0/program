/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * The Class Constant.
 *
 * @author lokeshwari
 */
public class Constant {

    public static final String EDIT = "Edit";
    /**
     * The null.
     */
    public static final String NULL = "null";
    /**
     * The user id.
     */
    public static final String USER_ID = "userId";
    /**
     * The yes.
     */
    public static final String YES = "YES";
    /**
     * The fail.
     */
    public static final String FAIL = "fail";
    /**
     * The view name.
     */
    public static final String VIEW_NAME = "viewName";
    /**
     * The Constant ZERO.
     */
    public static final int ZERO = 0;
    public static final String TEN_STRING = "10";
    /**
     * The Constant ZERO String
     */
    public static final String ZERO_STRING = "0";
    /**
     * The private.
     */
    public static final String PRIVATE = "Private";
    /**
     * The save.
     */
    public static final String SAVE = "save";
    /**
     * The projection name.
     */
    public static final String PROJECTION_NAME = "projectionName";
    /**
     * The description.
     */
    public static final String DESCRIPTION = "description";
    /**
     * The company.
     */
    public static final String COMPANY = "company";
    /**
     * The segment.
     */
    public static final String SEGMENT = "segment";
    /**
     * The market type.
     */
    public static final String MARKET_TYPE = "marketType";
    /**
     * The contract holder.
     */
    public static final String CONTRACT_HOLDER = "contractHolder";
    /**
     * The customer id.
     */
    public static final String CUSTOMER_ID = "customerId";
    /**
     * The brand type.
     */
    public static final String BRAND_TYPE = "brandType";
    /**
     * The session id
     */
    public static final String SESSION_ID = "sessionId";
    /**
     * The product level.
     */
    public static final String PRODUCT_LEVEL = "productLevel";
    /**
     * The form.
     */
    public static final String FORM = "form";
    /**
     * The strength.
     */
    public static final String STRENGTH = "strength";
    /**
     * The from.
     */
    public static final String FROM = "from";
    /**
     * The Constant ONE.
     */
    public static final int ONE = 1;
    /**
     * The multiple.
     */
    public static final String MULTIPLE = "Multiple";
    /**
     * The item no.
     */
    public static final String ITEM_NO = "itemNo";
    /**
     * The brand.
     */
    public static final String BRAND = "brand";
    /**
     * The success.
     */
    public static final String SUCCESS = "success";
    /**
     * The view id.
     */
    public static final String VIEW_ID = "viewId";
    /**
     * The view type.
     */
    public static final String VIEW_TYPE = "viewType";
    /**
     * The projection id.
     */
    public static final String PROJECTION_ID = "projectionId";
    /**
     * The company type.
     */
    public static final String COMPANY_TYPE = "companyType";
    /**
     * The contract holder name.
     */
    public static final String CONTRACT_HOLDER_NAME = "contractHolderName";
    /**
     * The company name.
     */
    public static final String COMPANY_NAME = "companyName";
    /**
     * The company no.
     */
    public static final String COMPANY_NO = "companyNo";
    /**
     * The brand name.
     */
    public static final String BRAND_NAME = "brandName";
    /**
     * The Constant BRAND_SEARCH.
     */
    public static final String BRAND_SEARCH = "brandSearch";
    /**
     * The brand caps.
     */
    public static final String BRAND_CAPS = "Brand";
    /**
     * The add space.
     */
    public static final String ADD_SPACE = "&nbsp;";
    /**
     * The reset.
     */
    public static final String RESET = "RESET";
    /**
     * The generate.
     */
    public static final String GENERATE = "GENERATE";
    /**
     * The import.
     */
    public static final String IMPORT = "IMPORT";
    /**
     * The populate.
     */
    public static final String POPULATE = "POPULATE";
    /**
     * The calculate.
     */
    public static final String CALCULATE = "CALCULATE";
    /**
     * The remove.
     */
    public static final String REMOVE = "REMOVE";
    /**
     * The date added.
     */
    public static final String DATE_ADDED = "dateAdded";
    /**
     * The document name.
     */
    public static final String DOCUMENT_NAME = "documentName";
    /**
     * The user name.
     */
    public static final String USER_NAME = "userName";
    /**
     * The word export.
     */
    public static final String WORD_EXPORT = "Export to word";
    /**
     * The pdf export.
     */
    public static final String PDF_EXPORT = "Export to PDF";
    /**
     * The add.
     */
    public static final String ADD = "ADD";
    /**
     * The null.
     */
    public static final Object NULL_OBJECT = null;
    /**
     * The btn left.
     */
    public static final String BTN_LEFT = "<";
    /**
     * The date.
     */
    public static final String DATE_FORMAT_1 = "yyyy/MM/dd";
    /**
     * The type.
     */
    public static final String TYPE = "type";
    /**
     * The Q2 y1 ch.
     */
    public static final String Q2Y1CH = "q2y1Ch";
    /**
     * The Q3 y1 ch.
     */
    public static final String Q3Y1CH = "q3y1Ch";
    /**
     * The Q4 y1 ch.
     */
    public static final String Q4Y1CH = "q4y1Ch";
    /**
     * The Q1 y2 ch.
     */
    public static final String Q1Y2CH = "q1y2Ch";
    /**
     * The Q2 y2 ch.
     */
    public static final String Q2Y2CH = "q2y2Ch";
    /**
     * The Q3 y2 ch.
     */
    public static final String Q3Y2CH = "q3y2Ch";
    /**
     * The Q4 y2 ch.
     */
    public static final String Q4Y2CH = "q4y2Ch";
    /**
     * The Q1 y3 ch.
     */
    public static final String Q1Y3CH = "q1y3Ch";
    /**
     * The Q2 y3 ch.
     */
    public static final String Q2Y3CH = "q2y3Ch";
    /**
     * The Q3 y3 ch.
     */
    public static final String Q3Y3CH = "q3y3Ch";
    /**
     * The Q4 y3 ch.
     */
    public static final String Q4Y3CH = "q4y3Ch";
    /**
     * The Q2 y1 tp.
     */
    public static final String Q2Y1TP = "q2y1Tp";
    /**
     * The Q3 y1 tp.
     */
    public static final String Q3Y1TP = "q3y1Tp";
    /**
     * The Q4 y1 tp.
     */
    public static final String Q4Y1TP = "q4y1Tp";
    /**
     * The Q1 y2 tp.
     */
    public static final String Q1Y2TP = "q1y2Tp";
    /**
     * The Q2 y2 tp.
     */
    public static final String Q2Y2TP = "q2y2Tp";
    /**
     * The Q3 y2 tp.
     */
    public static final String Q3Y2TP = "q3y2Tp";
    /**
     * The Q4 y2 tp.
     */
    public static final String Q4Y2TP = "q4y2Tp";
    /**
     * The Q1 y3 tp.
     */
    public static final String Q1Y3TP = "q1y3Tp";
    /**
     * The Q2 y3 tp.
     */
    public static final String Q2Y3TP = "q2y3Tp";
    /**
     * The Q3 y3 tp.
     */
    public static final String Q3Y3TP = "q3y3Tp";
    /**
     * The Q4 y3 tp.
     */
    public static final String Q4Y3TP = "q4y3Tp";
    /**
     * The date des.
     */
    public static final String DATE_DES = "Select Date";
    /**
     * The search icon style.
     */
    public static final String SEARCH_ICON_STYLE = "searchicon";
    /**
     * The table header style.
     */
    public static final String TABLE_HEADER_STYLE = "table-header-normal";
    /**
     * The select one.
     */
    public static final String SELECT_ONE = "-Select One-";
    /**
     * The SALES_SMALL dollars.
     */
    public static final String SALES_DOLLARS = "Sales Dollars";
    /**
     * The unit volume.
     */
    public static final String UNIT_VOLUME = "Unit Volume";
    /**
     * The parent details.
     */
    public static final String PARENT_DETAILS = "parentDetails";
    /**
     * The trading SALES_SMALL sys id.
     */
    public static final String TRADING_SALES_SYS_ID = "tradingSalesSystemId";
    /**
     * The C_LIVES.
     */
    public static final String C_LIVES = "C_LIVES";
    /**
     * The A_SALES.
     */
    public static final String A_SALES = "A_SALES";
    /**
     * The B_UNITS.
     */
    public static final String B_UNITS = "B_UNITS";
    /**
     * The width.
     */
    public static final String WIDTH = "75";
    /**
     * The one year.
     */
    public static final String ONE_YEAR = "1 Year";
    /**
     * The two years.
     */
    public static final String TWO_YEARS = "2 Year";
    /**
     * The three years.
     */
    public static final String THREE_YEARS = "3 Year";
    /**
     * The one semi annual.
     */
    public static final String ONE_SEMI_ANNUAL = "1 Semi-Annual";
    /**
     * The two semi annual.
     */
    public static final String TWO_SEMI_ANNUAL = "2 Semi-Annual";
    /**
     * The three semi annual.
     */
    public static final String THREE_SEMI_ANNUAL = "3 Semi-Annual";
    /**
     * The four semi annual.
     */
    public static final String FOUR_SEMI_ANNUAL = "4 Semi-Annual";
    /**
     * The five semi annual.
     */
    public static final String FIVE_SEMI_ANNUAL = "5 Semi-Annual";
    /**
     * The six semi annual.
     */
    public static final String SIX_SEMI_ANNUAL = "6 Semi-Annual";
    /**
     * The one quarter.
     */
    public static final String ONE_QUARTER = "1 Quarters";
    /**
     * The two quarters.
     */
    public static final String TWO_QUARTERS = "2 Quarters";
    /**
     * The three quarters.
     */
    public static final String THREE_QUARTERS = "3 Quarters";
    /**
     * The four quarters.
     */
    public static final String FOUR_QUARTERS = "4 Quarters";
    /**
     * The five quarters.
     */
    public static final String FIVE_QUARTERS = "5 Quarters";
    /**
     * The six quarters.
     */
    public static final String SIX_QUARTERS = "6 Quarters";
    /**
     * The seven quarters.
     */
    public static final String SEVEN_QUARTERS = "7 Quarters";
    /**
     * The eight quarters.
     */
    public static final String EIGHT_QUARTERS = "8 Quarters";
    /**
     * The nine quarters.
     */
    public static final String NINE_QUARTERS = "9 Quarters";
    /**
     * The ten quarters.
     */
    public static final String TEN_QUARTERS = "10 Quarters";
    /**
     * The eleven quarters.
     */
    public static final String ELEVEN_QUARTERS = "11 Quarters";
    /**
     * The twele quarters.
     */
    public static final String TWELE_QUARTERS = "12 Quarters";
    /**
     * The Y3 ch.
     */
    public static final String Y3CH = "y3Ch";
    /**
     * The Y3 tp.
     */
    public static final String Y3TP = "y3Tp";
    /**
     * The Y2 ch.
     */
    public static final String Y2CH = "y2Ch";
    /**
     * The Y2 tp.
     */
    public static final String Y2TP = "y2Tp";
    /**
     * The S2 y2 ch.
     */
    public static final String S2Y2CH = "s2y2Ch";
    /**
     * The S2 y2 tp.
     */
    public static final String S2Y2TP = "s2y2Tp";
    /**
     * The S1 y2 ch.
     */
    public static final String S1Y2CH = "s1y2Ch";
    /**
     * The S1 y2 tp.
     */
    public static final String S1Y2TP = "s1y2Tp";
    /**
     * The S2 y1 ch.
     */
    public static final String S2Y1CH = "s2y1Ch";
    /**
     * The S2 y1 tp.
     */
    public static final String S2Y1TP = "s2y1Tp";
    /**
     * The S2 y3 ch.
     */
    public static final String S2Y3CH = "s2y3Ch";
    /**
     * The S2 y3 tp.
     */
    public static final String S2Y3TP = "s2y3Tp";
    /**
     * The S1 y3 ch.
     */
    public static final String S1Y3CH = "s1y3Ch";
    /**
     * The S1 y3 tp.
     */
    public static final String S1Y3TP = "s1y3Tp";
    /**
     * The SALES_SMALL.
     */
    public static final String SALES = "sales";
    /**
     * The UNITS_SMALL.
     */
    public static final String UNITS = "units";
    /**
     * The annual.
     */
    public static final String ANNUAL = "Annual";
    /**
     * The quarterly.
     */
    public static final String QUARTERLY = "Quarterly";
    public static final String SEMI_ANNUALLY = "Semi-Annually";
    /**
     * The semi annualy.
     */
    public static final String SEMI_ANNUALY = "Semi-Annual";
    /**
     * The monthly.
     */
    public static final String MONTHLY = "Monthly";
    /**
     * The SALES_SMALL caps.
     */
    public static final String SALES_CAPS = "Sales";
    /**
     * The search.
     */
    public static final String SEARCH = "SEARCH";
    /**
     * The select.
     */
    public static final String SELECT = "SELECT";
    /**
     * The close.
     */
    public static final String CLOSE = "CLOSE";
    /**
     * The results.
     */
    public static final String RESULTS = "Results";
    /**
     * The tp name.
     */
    public static final String TP_NAME = "tradingPartnerName";
    /**
     * The tradingpartnerno.
     */
    public static final String TRADINGPARTNERNO = "tradingPartnerNo";
    /**
     * The Constant FLAT.
     */
    public static final Object FLAT = "Flat";
    /**
     * The Constant PROJECTED.
     */
    public static final Object PROJECTED = "Projected";
    /**
     * The DOUBLE.
     */
    public static final double DOUBLE_VALUE = 0.0;
    /**
     * The Constant TWO.
     */
    public static final int TWO = 2;
    /**
     * The Constant THREE.
     */
    public static final int THREE = 3;
    /**
     * The Constant FOUR.
     */
    public static final int FOUR = 4;
    /**
     * The Constant FIVE.
     */
    public static final int FIVE = 5;
    /**
     * The Constant SIX.
     */
    public static final int SIX = 6;
    /**
     * The Constant SEVEN.
     */
    public static final int SEVEN = 7;
    /**
     * The Constant EIGHT.
     */
    public static final int EIGHT = 8;
    /**
     * The Constant NINE.
     */
    public static final int NINE = 9;
    /**
     * The Constant TEN.
     */
    public static final int TEN = 10;
    /**
     * The Constant ELEVEN.
     */
    public static final int ELEVEN = 11;
    /**
     * The Constant TWELVE.
     */
    public static final int TWELVE = 12;
    public static final String PRICECAP = "PriceCap";
    public static final String GROUP = "group";
    public static final String RESET_SMALL = "Reset";
    public static final String PRICECAP_SPACE = "Price Cap";
    public static final String Q = "Q";
    public static final String Q_SMALL = "q";
    public static final String SPACE = " ";
    public static final String ACTUALS = "Actuals";
    public static final String ACCRUALS = "Accruals";
    public static final String PROJECTIONS = "Projections";
    public static final String BOTH = "Both";
    public static final String GROUPFCAPS = "Group";
    public static final String FREQUENCY = "frequency";
    public static final String HISTORY = "history";
    public static final String SALESORUNITS = "salesOrUnits";
    public static final String ACTUALSORPROJECTIONS = "projection";
    public static final String ORDER = "order";
    public static final String VIEW = "view";
    public static final String ANNUALLY = "Annually";
    public static final String SEMIANNUALLY = "Semi-Annually";
    public static final String S = "S";
    public static final String S_SMALL = "s";
    public static final String ACTUAL = "Actual";
    public static final String ACCRUAL = "Accrual";
    public static final String PERIOD = "Period";
    public static final String VARIABLE = "Variable";
    public static final String DASH = "0";
    public static final String ASCENDING = "Ascending";
    public static final String DESCENDING = "Descending";
    public static final String AVERAGE = "Average";
    public static final String LINEAR = "Linear";
    public static final String LOGARITHMIC = "Logarithmic";
    public static final String MOVING_AVERAGE = "Moving Average";
    public static final String POLYNOMIAL = "Polynomial";
    public static final String SINGLE_PERIOD = "Single Period";
    public static final String PERCENTAGEOFBUSINESS = "% of Business";
    public static final String HISPEROFBUSINESS = "Historical % of Business";
    public static final String FOREPERCOFBUSINESS = "Forecast % of Business";
    public static final String PERCOFEXFACTORY = "% of Ex-Factory";
    public static final String PERCOFEXFACTORYSALES = "% of Ex-Factory Sales";
    public static final String PERCOFDEMAND = "% of Demand";
    public static final String PERCOFINVENTORYWITHDRAWAL = "% of Inventory Withdrawal";
    public static final String SALES_SMALL = "Sales";
    public static final String UNITS_SMALL = "Units";
    public static final String PRODUCT_GROWTH = "Product Growth";
    public static final String ACCOUNT_GROWTH = "Account Growth";
    public static final String PROJECTED_RETURN_PER = "Projected Return %";
    public static final String PROJECTED_RPU = "Projected RPU";
    public static final String PROJECTED_RETURN_AMT = "Projected Return Amount";
    public static final String GROWTH_RATE = "Growth Rate";
    public static final String CUSTOMER_SMALL = "Customer";
    public static final String PRODUCT_LABEL = "Product";
    public static final String CUSTOM_LABEL = "Custom";
    public static final String HORIZONTAL = "horizontal";
    public static final String PPA_PROJECTION_RESULT = "PPA Projection Results";
    public static final String PROJECTION_RESULTS = "Projection Results";
    public static final String SALES_PROJECTION = "Sales Projection";
    public static final String TRADING_PARTNER = "Trading Partner";
    public static final String CHECK_RECORD = "CheckRecord";
    public static final String YEAR = "Year";
    public static final String ERROR = "Error";
    public static final String PERCENT = "%";
    public static final String CORRECTVALUE = "Please enter correct value";
    public static final String DASH_NO_DATA = "-";
    public static final String ALL_GROUP = "All PPA Group";
    public static final String SUPPLEMENTAL_INSERT_PRC = "PRC_M_SUPP_INSERT";
    public static final String SUPPLEMENTAL_DISCOUNT = "Supplement Discount Projection";
    public static final String PARITY_M_LOOKUP_PRC = "PRC_M_PARITY_LOOKUP";
    public static final String DISCOUNT_PROJECTION_RESULTS = "Discount Projection Results";
    public static final String LEVEL_NDC_8 = "NDC 8";
    public static final String LEVEL_NDC_10 = "NDC 10";
    public static final String LEVEL_NDC_11 = "NDC 11";
    public static final String PROCEDURE_CALL = "Procedure Call";
    public static final String DP_PROCEDURE_CALL = "DP Procedure Call";
    public static final String PRICE_PROTECTION_STATUS = "Price Protection Status";
    public static final String PRICE_PROTECTION_STATUS1 = "Price Protection Status";
    public static final String PRICE_PROTECTION_START_DATE = "Price Protection Start Date";
    public static final String PRICE_PROTECTION_END_DATE = "Price Protection End Date";
    public static final String ACTIVE = "Active";
    public static final String INACTIVE = "Inactive";
    public static final String VALUE = " Value";
    public static final String VARIANCE = " Variance";
    public static final String CHANGE = " %Change";
    public static final String REPLACE_STRING = "[$FIELD]";
    public static final String DATE_FEILD = "DATE_FEILD";
    public static final String DDLB_FIELD = "DDLB_FIELD";
    public static final String TEXT_FIELD = "TEXT_FIELD";
    public static final String FROZEN_FIELDS = "FROZEN_FIELD";
    public static final String LOOKUP_FIELD = "LOOKUP_FIELD";
    public static final String TIRE_ONE = "Tier 1";
    public static final String TIRE_TWO = "Tier 2";
    public static final String QUARTILE = "Quartile";
    public static final String ROLLINGANNUALTREND = "Rolling Annual Trend";
    public static final String PRICEPROTECTIONSTATUS = "priceProtectionStatus";
    public static final String PRICEPROTECTIONSTATUS1 = "priceProtectionStatus1";
    public static final String PRICEPROTECTIONSTARTDATE = "priceProtectionStartDate";
    public static final String PRICEPROTECTIONENDDATE = "priceProtectionEndDate";
    public static final String RIGHT = "Right";
    public static final String LEFT = "Left";
    public static final String CUSTOMER_GTS = "Customer GTS";
    public static final String ACTUALS_PRO = "Actuals/Projections :";
    public static final String FROM_LABEL = "From :";
    public static final String TO_LABEL = "To :";
    public static final String FREQUENCY_LABEL = "Frequency :";
    public static final String CUSTOMER = "Customer";
    public static final String CUSTOMER_GROUP = "Customer Group";
    public static final String COMPANY_ID = "COMPANY_ID";
    private static final Map<String, List<String>> PPA_POPULATE_FIELD_FINDER = new HashMap<>();
    private static final Map<String, String> PPA_DATABASE_COLUMN_IDENTIFIER = new HashMap<>();
    private static final Map<String, String> PPA_COLUMN_HEADER_FINDER = new HashMap<>();
    private static final Map<String, String> PPA_COLUMN_VARIABLE_FINDER = new HashMap<>();
    private static final Map<String, String> PPA_HELPER_TABLE_IDENTIFRIER = new HashMap<>();
    public static final String FLAG = "_FLAG";
    public static final String WIDTH_AUTO = "width-auto";
    public static final String FILTER_TABLE = "filtertable";
    public static final String SAVED = "Saved";
    public static final String SUBMITTED = "Submitted";
    /**
     * The projection master sid.
     */
    public static final String PROJECTION_MASTER_SID = "projectionMasterSid";
    public static final String STRING_EMPTY = "empty";
    public static final String BOOTSTRAP_UI = "bootstrap-ui";
    public static final String BOOTSTRAP = "bootstrap";
    public static final String BOOTSTRAP_NM = "bootstrap-nm";
    public static final String BOOTSTRAP_FORECAST_BOOTSTRAP_NM = "bootstrap-forecast bootstrap-nm";
    public static final String FILTERBAR = "filterbar";
    public static final String SEARCH_TEXT = "searchText";
    public static final String FORECAST = "Forecast";
    public static final String ADJUSTMENT = "Adjustment";
    public static final String OVERRIDE = "Override";
    public static final String POPUPCONTENTCOMBOSIZE = "popupContentComboSize";
    public static final String OPTION_GROUP_WIDTH = "optiongroupwidth";
    public static final String ADD_FULL_SMALL = "add";
    public static final String CENTER_CHECK = "center-check";
    public static final String LEVEL = "Level ";
    public static final String MM = "MM";
    public static final String MANAGED_MEDICAID = "Managed Medicaid";
    public static final String TOTAL = "Total";
    public static final String DETAIL = "Detail";
    public static final String UNIT = "Unit";
    public static final String ALL = "All";
    public static final String TXT_RIGHT_ALIGN = "txtRightAlign";
    public static final String ALIGN_RIGHT = "align-right";
    public static final String P_GROWTH = "pGrowth";
    public static final String A_GROWTH = "aGrowth";
    public static final String TRADINGPARTNER = "TRADING PARTNER";
    public static final String METHODOLOGY = "methodology";
    public static final String HISTORY_CAPS = "History";
    public static final String ACTUALSALES = "ActualSales";
    public static final String ACTUALUNITS = "ActualUnits";
    public static final String BASELINE = "baseline";
    public static final String ACTUALRPU = "ActualRPU";
    public static final String FREQUENCY_SMALL = "Frequency";
    public static final String PERIOD_ORDER = "Period Order";
    public static final String VARIABLES = "Variables";
    public static final String LEVELNAME = "levelName";
    public static final String DF_LEVEL_NAME = "dfLevelName";
    public static final String DF_LEVEL_NUMBER = "dfLevelNumber";
    public static final String LEVEL_NAME_HEADER = "Level Name";
    public static final String LEVEL_NUMBER_HEADER = "Level Number";
    public static final String LEVEL_NAME = "levelName";
    public static final String UPDATE_SMALL = "Update";
    public static final String EDIT_CAPS = "EDIT";
    public static final String VIEW_CAPS = "VIEW";
    public static final String IS_SALES_GENERATED = "isSalesGenerated";
    public static final String IS_RATES_GENERATED = "isRatesGenerated";
    public static final String TRUE = "true";
    public static final String PROJECTION_TOTAL = "Projection Total";
    public static final String DISCOUNT = "Discount-";
    public static final String SEMIANNUALLY_SMALL = "semi-annually";
    public static final String QUARTERLY_SMALL = "quarterly";
    public static final String MONTHLY_SMALL = "monthly";
    public static final String MANDATED_DISCOUNT = "Mandated Discount";
    public static final String NON_MANDATED_SUPPLEMENTAL = "Non-Mandated Supplemental";
    public static final String MANDATED_SUPPLEMENTAL = "Mandated Supplemental";
    public static final String DISCOUNT_SMALL = "Discount";
    public static final String DISCOUNT_LOWER_CASE = "discount";
    public static final String INDICATOR_LOGIC_CUSTOMER_HIERARCHY = "C";
    public static final String INDICATOR_LOGIC_PRODUCT_HIERARCHY = "P";
    public static final String INDICATOR_LOGIC_DEDUCTION_HIERARCHY = "D";
    public static final String SUPPLEMENTAL = "Supplemental";
    public static final String COMMERCIAL_SUPPLEMENTAL_DISCOUNT = "Commercial Supplemental Disocunt";
    public static final String ACTUAL_CAPS = "ACTUAL";
    public static final String TOTALDISCOUNT = "TotalDiscount";
    public static final String ACT = "ACT";
    public static final String PARENT = "parent";
    public static final String STRING_ONE = "1";
    public static final String TP = "TP";
    public static final String LIVES = "Lives";
    public static final String ACTUAL_SALES = "Actual Sales";
    public static final String PROJECTIONDESCRIPTION = "projectionDescription";
    public static final String INDICATOR = "indicator";
    public static final String FILTER = "filter~";
    public static final String R = "R";
    public static final String ORDERBY = "orderBy~";
    public static final String ISORDERED = "isOrdered";
    public static final String LASTNAME = "lastName";
    public static final String FIRSTNAME = "firstName";
    public static final String COMPANY_MASTER = "COMPANY_MASTER";
    public static final String CONTRACT_MASTER = "CONTRACT_MASTER";
    public static final String ITEM_MASTER = "ITEM_MASTER";
    public static final String HIERARCHY_DEFINITION_SID = "hierarchyDefinitionSid";
    public static final String UPDATE = "update";
    public static final String HELPER_TABLE_SID = "helperTableSid";
    public static final String LIST_NAME = "listName";
    public static final String COMPANYMASTERSID = "companyMasterSid";
    public static final String VERSION_NO = "versionNo";
    public static final String HIERARACHY_NO = "hierarchyNo";
    public static final String RS_TYPE = "RS_TYPE";
    public static final String GROWTH = "Growth";
    public static final String PROJECTEDRPU = "ProjectedRPU";
    public static final String PROJECTION_DETAILS_SID = "projectionDetailsSid";
    public static final String CCP_DETAILS_SID = "ccpDetailsSid";
    public static final String CUSTOMER_NO = "customerNo";
    public static final String CUSTOMER_NAME = "customerName";
    public static final String PRODUCT_NO = "productNo";
    public static final String PRODUCT_NAME = "productName";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String CONTRACT_MASTER_SID = "contractMasterSid";
    public static final String SHOW_ALL = "Show All";
    public static final String FILTER_COMBOBOX = "filterCombobox";
    public static final String FORECAST_TYPE = "forecastType";
    public static final String FILE_NAME = "fileName";
    public static final String SCREEN_NAME = "screenName";
    public static final String FIELD_NAME = "fieldName";
    public static final String FIELD_VALUES = "fieldValues";
    public static final String WAC = "WAC";
    public static final String WAC_FCP = "WAC-FCP";
    public static final String WAC_FSS = "WAC-FSS";
    public static final String FSS = "FSS";
    public static final String CPI_URA = "CPI-URA";
    public static final String FCP = "FCP";
    public static final String FCP_DISCOUNT = "FCP Discount";
    public static final String FSS_OGA_DISCOUNT = "FSS (OGA) Discount";
    public static final String FSS_OGA_DISCOUNT1 = "FSS (OGA) Discount";
    public static final String AMP = "AMP";
    public static final String PHS = "PHS";
    public static final String BEST_PRICE = "BEST PRICE";
    public static final String CPIURA = "CPI URA";
    public static final String ANNUAL_FSS = "Annual FSS";
    public static final String TOTAL_URA = "TOTAL URA";
    public static final String PHS_DISCOUNT = "PHS Discount";
    public static final String BEST_PRICE_LOWERCASE = "Best Price";
    public static final String ADJUSTMENT_AMP = "Adjustment AMP";
    public static final String ADJUSTMENT_BEST_PRICE = "Adjustment Best Price";
    public static final String BRAND_MASTER_SID = "brandMasterSid";
    public static final String ITEM_MASTER_SID = "itemMasterSid";
    public static final String NULL_CAPS = "NULL";
    public static final String NOTES = "Notes";
    public static final String ADD_SMALL = "Add";
    public static final String MODE = "mode";
    public static final String EDIT_SMALL = "edit";
    public static final String SELECTONE = "- Select One -";
    public static final String MY_STYLE = "mystyle";
    public static final String CONTRACT_SMALL = "Contract";
    public static final String FORMULA_TYPE = "formulaType";
    public static final String DEDUCTION_TYPE = "deductionType";
    public static final String DEDUCTION_SUB_TYPE = "deductionSubType";
    public static final String DEDUCTION_CATEGORY = "deductionCategory";
    public static final String FILTERR = "@FILTER";
    public static final String RESPONSIVE_PAGED_TABLE = "responsivePagedTable";
    public static final String CONTRACT = "contract";
    public static final String TOTAL_SMALL = "total";
    public static final String SALES_WHOLE_CAPS = "SALES";
    public static final String RATE = "RATE";
    public static final String TOTAL_DISCOUNT_DOLLAR = "totDisDol";
    public static final String TOTAL_RPU = "totalRPU";
    public static final String TOTAL_RPU_CAPS = "Total RPU";
    public static final String MARKET_TYPE_LABEL = "Market Type";
    public static final String TOT_DIS_PER = "totDisPer";
    public static final String SALES_WITH_HYPHEN = "Sales-";
    public static final String PPA = "PPA-";
    public static final String CURRENT = "Current";
    public static final String COMPONENT = "Component";
    public static final String CHECK = "check";
    public static final String PPA_SMALL = "PPA";
    public static final String EQUAL = "=";
    public static final String COMPANY_SMALL = "Company";
    public static final String NDC = "NDC";
    public static final String ACTALLOCATION = "ActAllocation%";
    public static final String PROJALLOCATION = "ProjAllocation%";
    public static final String CHECKRECORD = "checkRecord";
    public static final String BOTH_SMALL = "both";
    public static final String DISCOUNT_EXFAC_SALES = "DISEx%";
    public static final String DISCOUNT_PER_OF_EX_FACTORY = "disPerExfac";
    public static final String DISCOUNT_PER_OF_EX_FACTORY_HEADER = "Discount % of Ex-Factory";
    public static final String NET_SALES_PER_OF_EX_FACTORY = "netSalesExFac";
    public static final String NET_SALES_PER_OF_EX_FACTORY_HEADER = "Net Sales % of Ex-Factory";

    /**
     * The UNITS_CAPS.
     */
    public static final String UNITS_CAPS = "Units";
    public static final String PROJECTED_UNITS = "Projected Units";
    public static final String ACTUAL_UNITS = "Actual Units";
    public static final String AND = "AND";
    public static final String ITEM = "Item";
    public static final String PERC_OF_ADJUSTED_DEMAND = "% of Adjusted Demand";
    public static final String MERGE_QUERY = "Merge Query";
    public static final String CURRENT_TABLE_NAMES = "CurrentTableNames";
    public static final String INSERTORUPDATE = "InsertOrUpdate";
    public static final String DISCOUNT_LIST_VIEW_SAVE = "DISCOUNT_LIST_VIEW_SAVE";
    public static final String CUST_HIERARCHY_INSERT = "CUST_HIERARCHY_INSERT";
    public static final String PROD_HIERARCHY_INSERT = "PROD_HIERARCHY_INSERT";
    public static final String PROJECTION_DETAILS_INSERT = "PROJECTION_DETAILS_INSERT";
    public static final String DATA_SELECTION_TAB_LOAD = "DATA_SELECTION_TAB_LOAD";
    public static final String SALES_PROCEDURE_CALL = "SALES_PROCEDURE_CALL";
    public static final String DISCOUNT_PROCEDURE_CALL = "DISCOUNT_PROCEDURE_CALL";
    public static final String DISCOUNT_MASTER_PROCEDURE_CALL = "DISCOUNT_MASTER_PROCEDURE_CALL";
    public static final String PPA_PROCEDURE_CALL = "PPA_PROCEDURE_CALL";
    public static final String SUPPLEMENT_PROCEDURE_CALL = "SUPPLEMENT_PROCEDURE_CALL";
    public static final String M_DISCOUNT_PROCEDURE_CALL = "M_DISCOUNT_PROCEDURE_CALL";
    public static final String PROJ_HIERARCHY_INSERT = "PROJ_HIERARCHY_INSERT";
    public static final String FILE_INSERT = "FILE_INSERT";
    public static final String ALL_SALES_GROUP = "All Sales Groups";
    public static final String SALESWITHMINUS = "Sales-";
    public static final String NA_FILE_INSERT = "NA_FILE_INSERT";
    public static final String PRC_PPA_GENERATE_CALL = "PRC_PPA_GENERATE_CALL";
    public static final String PRC_PPA_UPDATE_STMT_CALL = "PRC_PPA_UPDATE_STMT_CALL";
    public static final String PRC_CONTRACT_DETAILS_REBATE = "PRC_CONTRACT_DETAILS_REBATE";
    public static final String CALL_PRC_CONTRACT_DETAILS_REBATE = "CALL_PRC_CONTRACT_DETAILS_REBATE";
    public static final String ALL_DISCOUNT_GROUP = "All Discount Groups";
    public static final String ACTUALS_AND_PROJECTIONS = "Actuals and Projections";
    public static final String PHS_RESULTS = "PHS Results";
    public static final String PERC_OF_EX_FACTORY_SEASONAL_TREND = "% OF Ex-Factory - Seasonal Trend";

    /**
     * set height for 390px
     */
    public static final String PX_390 = "390px";
    public static final String FOUR_EIGHT_ONE_PX = "481px";
    public static final String TWO_TEN_PX = "210px";
    public static final String IMAGE_DISABLED = "Image-Disabled";
    public static final String ARE_YOU_SURE_YOU_WANT_TO_CONTINUE = ".\n Are you sure you want to continue?";
    public static final String REPLACE_THE_CURRENT_VALUES = "You are about to replace the current values in the list view with the following variable: ";
    public static final String YOU_ARE_ABOUT_TO_MAKE_THE_FOLLOWING = "You are about to make the following ";
    public static final String FALSE = "false";
    public static final String FROM_ZERO_TO_NINE = "[^0-9]";
    public static final String IMPROPER_CALCULATION_VARIABLES = "Improper calculation variables";
    public static final String SALES_PROJECTION_LABEL = "Sales Projection";
    public static final String AT_PROJECTION_MASTER_SID = "@PROJECTION_MASTER_SID";
    public static final String ASC_SPACE = " ASC ";
    public static final String AT_COMPANY_MASTER_SID = "@COMPANY_MASTER_SID";
    public static final String PRICE = "Price";
    public static final String ST_ACCRUAL_PROJ_DETAILS = "ST_ACCRUAL_PROJ_DETAILS";
    public static final String AT_TABLE_NAME = "@TABLE_NAME";
    public static final String EXCLUDED_UNITS = "ExcludedUnits";
    public static final String DESC_SPACE = " DESC ";
    public static final String NET_UNITS = "NetUnits";
    public static final String TOTAL_UNITS = "TotalUnits";
    public static final String GROSS_TRADE_SALES = "GROSS_TRADE_SALES";
    public static final String DATE_FORMAT_DD_MM = "dd-MM-yyyy";
    public static final String ACCRUAL_PROJ_DETAILS = "ACCRUAL_PROJ_DETAILS";
    public static final String WORKFLOW_WITH_WORKFLOW_ID = "The workflow with workflow Id ";
    public static final String WORKFLOW_ID = "Workflow Id ";
    public static final String APPROVE_FLAG = "approveFlag";
    public static final String SUPPORT_MAIL = "support@bpitechnologies.com";
    public static final String THANKS_BPI_TECHNICAL_TEAM = "<br /><br />Thanks,<br />BPI Technical Team";
    public static final String BR_BR = "Hi,<br /><br />";
    public static final String PERIOD_BASIS = "Period Basis";
    public static final String DETAILS = "Details";
    public static final String RATE_BASIS = "Rate Basis";
    public static final String CHECK_ALL_CAPS = "Check All";
    public static final String REMOVE_ATTACHMENT = "Remove Attachment";
    public static final String QUESTION_PROJECTION_ID = "?PROJECTION_ID";
    public static final String ADMINISTRATOR = "Administrator";
    public static final String REBATE_SCHEDULE_ID_15 = "REBATE_SCHEDULE_ID_15";
    public static final String CONTRACT_END_DATE = "contractEndDate";
    public static final String DISABLE = "disable";
    public static final String NO_VALUE_SELECTED = "No Value Selected";
    public static final String SUPPLEMENTAL_DISCOUNT_PROJECTION = "Supplemental Discount Projection";
    public static final String FORMULA_NAME = "formulaName";
    public static final String SELECTED_DISCOUNTS = "Selected Discounts";
    public static final String PROJECTION_PERIOD_ORDER_LABEL = "Projection Period Order";
    public static final String YEAR_SELECTION_DDLB = "year Selection Ddlb";
    public static final String HISTORICAL_OF_BUSINESS = "Historical % of Business";
    public static final String DISCOUNT_RATE_LABEL = "Discount Rate";
    public static final String DISCOUNT_AMOUNT_LABEL = "Discount Amount";
    public static final String SIX_FIFTY_PX = "650px";
    public static final String NO_START_PERIOD_SELECTED = "No Start Period selected";
    public static final String NO_PERIOD_SELECTED = "No period selected";
    public static final String LEVEL_LABEL = "Level";
    public static final String ACTUALS_PROJECTIONS = "Actuals / projections";
    public static final String DISCOUNT_PROJECTION_LABEL = "Discount Projection";
    public static final String PROJECTED_RATE = "ProjectedRate";
    public static final String GROUP_FILTER_CONFLICT = "Group Filter Conflict";
    public static final String AT_USER_GROUP = "@USER_GROUP";
    public static final String AT_COLUMN_NAME = "@COLUMN_NAME";
    public static final String COUNT_PROGRAM_CATEGORY = "GET_COUNT_PROGRAM_CATEGORY";
    public static final String RS_CONTRACT_SID = "RS_CONTRACT_SID";
    public static final String GET_COUNT_PROGRAM = "GET_COUNT_PROGRAM";
    public static final String AT_DISCOUNT = "@DISCOUNT";
    public static final String PRICE_GROUP_TYPE = "PRICE_GROUP_TYPE";
    public static final String QUESTION_HIERARCHY_NO_VALUES = "[?HIERARCHY_NO_VALUES]";
    public static final String QUESTION_CUSTOMERPARENT = "[?CustomerParent]";
    public static final String QUESTION_PRODUCTPARENT = "[?ProductParent]";
    public static final String HIERARCHY_NO = "@HIERARCHY_NO";
    public static final String AT_RS_COLUMN = "@RS_COLUMN";
    public static final String ZERO_AS_PERIODS = ",'0' AS  PERIODS";
    public static final String IMONTH = ", I.\"MONTH\"";
    public static final String IMONTH_SPACE = ", I.\"MONTH\" ";
    public static final String IMONTH_AS_PERIODS = ", I.MONTH AS PERIODS";
    public static final String IQUARTER = ", I.QUARTER";
    public static final String IQUARTER_SPACE = ", I.QUARTER ";
    public static final String ISEMI_ANNUAL_SPACE = ", I.SEMI_ANNUAL ";
    public static final String ISEMI_ANNUAL = ", I.SEMI_ANNUAL";
    public static final String PERIODS_QUOTE = ", PERIODS";
    public static final String PRMONTH = ",PR.MONTH";
    public static final String RS_CONTRACT_SID_SPACE = "    ,RS_CONTRACT_SID  ";
    public static final String FREQUENCY1 = "?FREQUENCY?";
    public static final String HNOP1 = "?HNOP?";
    public static final String GROUPFREQJOIN1 = "?GROUPFREQJOIN?";
    public static final String SELECTFREQJOIN1 = "?SELECTFREQJOIN?";
    public static final String ORDERFREQJOIN1 = "?ORDERFREQJOIN?";
    public static final String HNOC1 = "?HNOC?";
    public static final String NDC_NINE_QUESTION = "?NDC9";
    public static final String HNO1 = "?HNO?";
    public static final String PHTABLE = "?PHTABLE?";
    public static final String RBSID1 = "?RBSID?";
    public static final String LEVELNO1 = "?LEVELNO?";
    public static final String PMSID1 = "?PMSID?";
    public static final String SESSIONID1 = "?SESSIONID?";
    public static final String PROJECTION_STARTDATE = "?PROJECTIONSTARTDATE?";
    public static final String USERID1 = "?USERID?";
    public static final String PROJECTION_RESULTS_LABEL = "Projection Results";
    public static final String IQUARTER_AS_PERIODS = ", I.QUARTER AS PERIODS";
    public static final String AND_MSESSION_ID = " and M.SESSION_ID = ";
    public static final String UPDATE_M_SET_CHECK_RECORD = "UPDATE M SET CHECK_RECORD = ";
    public static final String PROJECTION_PROD_HIERARCHY = "PROJECTION_PROD_HIERARCHY ";
    public static final String PROJECTION_CUST_HIERARCHY = "PROJECTION_CUST_HIERARCHY ";
    public static final String DISCOUNT_TWO = "Discount2";
    public static final String DISCOUNT_ONE = "Discount1";
    public static final String CONTRACT_PRICE_PROPERTY = "ContractPrice";
    public static final String IMID1 = "?IMID";
    public static final String ZERO_SLASH_C = ", 0)) / C.";
    public static final String CASE_WHEN_P = ", CASE WHEN P";
    public static final String UNDERSCORE_PROJECTION_SALES = "_PROJECTION_SALES ";
    public static final String ISEMI_ANNUAL_AS_PERIODS = ", I.SEMI_ANNUAL AS PERIODS";
    public static final String PERIODS_SPACE_QUERY = "                     , PERIODS\n";
    public static final String REGEX_AZ = ".*[a-zA-Z]+.*";
    public static final String PERIODS_EQUAL = ".PERIODS=";
    public static final String DOT_PERIODS_QUOTE = ".PERIODS, ";
    public static final String ADDEDREMOVED_NDCS_ALERT = "You have added/removed NDC's from the current Projection. Are you sure you want to continue?";
    public static final String WHERE_RL_D2HIERARCHY_NO_LIKE = " WHERE RLD2.HIERARCHY_NO like '";
    public static final String WHERE_FINALQTEMP_INDEX_GREATER = "WHERE  FINALQ.TEMP_INDEX > ";
    public static final String LEVEL_NO_QUOTE = ".LEVEL_NO, ";
    public static final String HIERARCHY_NO_PERCENT = ".hierarchy_no + '%'";
    public static final String LEVEL_NAME_QUOTE = ".LEVEL_NAME,";
    public static final String PARENT_NODE_QUOTE = ".PARENT_NODE,";
    public static final String DOT_PERIODS = ".PERIODS  ";
    public static final String DOT_PERIODS_QUERY = ".PERIODS";
    public static final String OR_DOLLAR = "^.|.$";
    public static final String WHERE_FILTER_VALUE = "whereFilterValue";
    public static final String TILT_FROM = "~from";
    public static final String THEN = "   THEN\n";
    public static final String HIERARCHY_COLUMN_QUESTION = "[?HIERARCHY_COLUMN]";
    public static final String FREQUENCY_QUESTION = "[?FREQUENCY]";
    public static final String USER_GROUP_CONDITIONQUERY = "user-group-condition-query";
    public static final String USER_GROUP_JOIN = "[?USER_GROUP_JOIN]";
    public static final String INSERT_SELECTED_RS_MODEL = "insert-selected-rs-model";
    public static final String PRIOR_HIERARCHY = "prior-hierarchy";
    public static final String CURRENT_HIERARCHY = "current-hierarchy";
    public static final String ORDER_BY_QUESTION = "[?ORDER_BY]";
    public static final String PREVIOUS_HIERARCHY_NO_QUESTION = "[?PREVIOUS_HIERARCHY_NO]";
    public static final String RS_CONTRACT_SID_QUESTION = "[?RS_CONTRACT_SID]";
    public static final String JOIN_SPACE = "JOIN ";
    public static final String MANDATED_DISCOUNT1 = "MandatedDiscount";
    public static final String MANDATED = "mandated";
    public static final String ACTUALS_AMOUNT = "ActualsAmount";
    public static final String ACTUALS_RPU = "ActualsRPU";
    public static final String PROJECTIONS_RPU = "ProjectionsRPU";
    public static final String LEFT_JOIN = "LEFT JOIN ";
    public static final String ACTUALS_RATE = "ActualsRate";
    public static final String PROJECTIONS_AMOUNT = "ProjectionsAmount";
    public static final String PROJECTIONS_RATE = "ProjectionsRate";
    public static final String MONTH_SPACE = "\"MONTH\"";
    public static final String YEAR_SPACE = "\"YEAR\"";
    public static final String QUARTER = "QUARTER";
    public static final String SEMI_ANNUAL = "SEMI_ANNUAL";
    public static final String PD_HIERARCHY_NO = "pd.HIERARCHY_NO,\n";
    public static final String NM_PROJ_DETAILS_SID_QUERY = "getNMProjDetailsSid_Query";
    public static final String SELECT_FROM_PROJECTION_DETAILS = " SELECT * FROM #PROJECTION_DETAILS ";
    public static final String TABLENAME_QUESTION = "\\?TABLENAME";
    public static final String UNION = "Union\n";
    public static final String CONFIGURATIONS_DEFAULT = "configurations/default";
    public static final String SEGMENT_LABEL = "Segment";
    public static final String SELECT_SMALL_SPACE = " select ";
    public static final String SEMI_ANNUAL_SMALL = "SemiAnnual";
    public static final String SEMIANNUAL_CAPS = "SEMI-ANNUAL";
    public static final String SUPPLEMENTAL_DISCOUNT_LABEL = "Supplemental Discount";
    public static final String EX_FAC_VALUE = "ExFacValue";
    public static final String MANDATED_DISCOUNT_SALES_VALUE = "mandatedDiscountSalesValue";
    public static final String DISCOUNT_AMOUNT_VALUE = "DiscountAmountValue";
    public static final String PER_EX_FAC_VALUE = "PerExFacValue";
    public static final String INV_WITH_VALUE = "InvWithValue";
    public static final String MANDATED_DISCOUNT_AMOUNT_VALUE = "mandatedDiscountAmountValue";
    public static final String MANDATED_DISCOUNT_RPU_VALUE = "mandatedDiscountRPUValue";
    public static final String SUPPLEMENTAL_DISCOUNT_SALES_VALUE = "supplementalDiscountSalesValue";
    public static final String DISCOUNT_SALES_VALUE = "DiscountSalesValue";
    public static final String CONTRACT_SALES_WAC_VALUE = "ContractSalesWACValue";
    public static final String SUPPLEMENTAL_DISCOUNT_AMOUNT_VALUE = "supplementalDiscountAmountValue";
    public static final String PER_INV_WITH_VALUE = "PerInvWithValue";
    public static final String DEMAND_SALES_VALUE = "DemandSalesValue";
    public static final String RPU_VALUE = "RPUValue";
    public static final String PER_DEMAND_SALES_VALUE = "PerDemandSalesValue";
    public static final String SUPPLEMENTAL_RPU_VALUE = "supplementalRPUValue";
    public static final String CONTRACT_UNITS_VALUE = "ContractUnitsValue";
    public static final String DOT_YEARS_SPACE = ".YEARS ";
    public static final String HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE = "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%' \n";
    public static final String HLDP_ON_CCP_MAP_HIERARCHY_NO_LIKE = "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'";
    public static final String ZERO_AS_PERIODS_COMMA = "'0' as PERIODS, ";
    public static final String AND_SPACE = "' AND '";
    public static final String SLASH_N = "'  \n ";
    public static final String AS_HIERARCHY_INDICATOR_COMMA = "' as HIERARCHY_INDICATOR,";
    public static final String CUSTOMER_AS_LEVEL_NAME = " 'Customer' as LEVEL_NAME,\n";
    public static final String TOTAL_DISCOUNTS_AS_DISCOUNTS = "'Total discounts' as DISCOUNTS";
    public static final String START_QUESTION = "[?START]";
    public static final String END_QUESTION = "[?END]";
    public static final String HIERARCHY_TABLE = "[?HIERARCHY_TABLE]";
    public static final String SELECTED_HIERARCHY_JOIN = "[?SELECTED_HIERARCHY_JOIN]";
    public static final String REGEX_ZERO_TO_NINE = "[^0-9]";
    public static final String REGEX_D = "[^\\d.]";
    public static final String CUSTOM_VIEW_MASTER_SID = "[$CUSTOM_VIEW_MASTER_SID]";
    public static final String CONTRACT_MASTER_SID_DOLLAR = "[$CONTRACT_MASTER_SID]";
    public static final String COMPANY_MASTER_SID_DOLLAR = "[$COMPANY_MASTER_SID]";
    public static final String AVAILABLE_CHECKBOX = "[$AVAILABLE_CHECKBOX]";
    public static final String TOTAL_COLUMN = "[$TOTAL_COLUMN]";
    public static final String ALLOCATION_PERCENT_COLUMN = "[$ALLOCATION_PERCENT_COLUMN]";
    public static final String UPDATE_COLUMN = "[$UPDATE_COLUMN]";
    public static final String SESSION_ID_DOLLAR = "[$SESSION_ID]";
    public static final String USER_ID_DOLLAR = "[$USER_ID]";
    public static final String CLOSE_BRACKET = "  (\n";
    public static final String AND_RSMRS_NAME_IN = " and RSM.RS_NAME IN (";
    public static final String PROJSID = "\\?PROJSID";
    public static final String LEVELNO_QUESTION = "\\?LEVELNO";
    public static final String HIERNO = "\\?HIERNO";
    public static final String MONTH_WITHOUT_SPACE = "MONTH";
    public static final String FREQUENCY_VAR = "@frequency_var";
    public static final String PROJ_ID = "@projID";
    public static final String CCP_DETAILS_CCP = " CCP_DETAILS CCP,\n";
    public static final String AND_PER_PERIOD_SID_SUP_PERIOD_SID = "  and Per.PERIOD_SID = SUPMAS.PERIOD_SID\n";
    public static final String COMPANY_MASTER_TT = " COMPANY_MASTER TT,\n";
    public static final String WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS = "where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n";
    public static final String AND_TTCOMPANY_MASTER_SID_CCP_MAS = " and TT.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n";
    public static final String FROM_ST_CCP_HIERARCHY_PD = " FROM ST_CCP_HIERARCHY PD,\n";
    public static final String AND_PER_YEAR = "AND per.YEAR =";
    public static final String AND_PER_DOT = " AND per.";
    public static final String PER_YEAR_EQUAL = "per.YEAR =";
    public static final String AND_TT = " and TT.";
    public static final String AND_PER_YEAR_EQUAL = " AND  per.YEAR =";
    public static final String SPACE_PER = "           per.";
    public static final String AND_FINALQTEMP_INDEX_LESSER = " AND FINALQ.TEMP_INDEX <=  ";
    public static final String ACTUALS_PROPERTY = "actuals";
    public static final String ACTUALS_EX_FACTORY = "ActualsEx-Factory";
    public static final String PROJECTIONS1 = "projections";
    public static final String PROJECTIONS_EX_FACTORY = "ProjectionsEx-Factory";
    public static final String EMPTY_STRING = "empty";
    public static final String CUSTOM_VIEW_MASTER_SID_PROPERTY = "customViewMasterSid";
    public static final String ROWS_ONLY_SPACE = " ROWS ONLY";
    public static final String ROWS_FETCH_NEXT_SPACE = " ROWS FETCH NEXT ";
    public static final String JOIN_PROJECTION_DETAILS_PD_ON_PDCCP = " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=";
    public static final String ITEM_IDENTIFIER_TYPE = "itemIdentifierType";
    public static final String ITEM_IDENTIFIER = "itemIdentifier";
    public static final String FILTERITEM_IDENTIFIER = "filter~itemIdentifier";
    public static final String FILTERTHERAPUTIC_CLASS = "filter~theraputicClass";
    public static final String FILTERBRAND = "filter~brand";
    public static final String FILTERITEM_NAME = "filter~itemName";
    public static final String FILTERITEM_IDENTIFIER_TYPE = "filter~itemIdentifierType";
    public static final String FILTERBUSINESS_UNIT_NAME = "filter~businessUnitName";
    public static final String FILTERBUSINESS_UNIT_NO = "filter~businessUnitNo";
    public static final String FILTERITEM_NO = "filter~itemNo";
    public static final String AND_CMCOMPANY_NAME_LIKE = " AND CM.COMPANY_NAME like '";
    public static final String FILTERCONTRACT_NAME = "filter~contractName";
    public static final String FILTERCUSTOMER_NAME = "filter~customerName";
    public static final String FILTERCHECK = "filter~check";
    public static final String FILTERMARKET_TYPE = "filter~marketType";
    public static final String FILTERCUSTOMER_NO = "filter~customerNo";
    public static final String CONTRACT_NAME = "contractName";
    public static final String CONTRACT_NO = "contractNo";
    public static final String CUMULATIVE_FILE_PATH = "cumulative.file.path";
    public static final String NEXT_COLUMN_BUTTON = "next-column-button";
    public static final String PREV_NEXT_LAYOUT = "prev-next-layout";
    public static final String PREV_COLUMN_BUTTON = "prev-column-button";
    public static final String FF_BG_COLOR = "#FFFFFF";
    public static final String AND_RLDRELATIONSHIP_BUILDER_SID = " AND RLD.RELATIONSHIP_BUILDER_SID = ";
    public static final String AND_CVDCUSTOM_VIEW_MASTER_SID = " AND CVD.CUSTOM_VIEW_MASTER_SID=";
    public static final String AND_CVDLEVEL_NO_LIKE = " AND CVD.LEVEL_NO  like '";
    public static final String M_PROJECTION_SELECTION = "M_PROJECTION_SELECTION";
    public static final String AND_SMALL_SPACE = " and ";
    public static final String SELECTED_HIERARCHY_NO = "selected-hierarchy-no";
    public static final String INVENTORY_WITHDRAWAL_FORECAST_DETAIL = "Inventory Withdrawal - Forecast Detail";
    public static final String CUSTOMER_SALES = "Customer Sales";
    public static final String DEMAND = "Demand";
    public static final String ADJUSTED_DEMAND = "Adjusted Demand";
    public static final String EX_FACTORY_SALES_LABEL = "Ex-Factory Sales";
    public static final String TABLE_NAME = "tableName";
    public static final String RELATIONSHIP_BUILDER_SID = "relationshipBuilderSid";
    public static final String RELATIONSHIP_LEVEL_SID = "relationshipLevelSid";
    public static final String PROJECTION_MASTER_SID_AT = "@PROJECTION_MASTER_SID";
    public static final String PROJECTION_ID_AT = "@PROJECTION_ID";
    public static final String HIERARCHY_DETAILS_AT = "@HIERARCHY_DETAILS";
    public static final String RELATION_SID_AT = "@RELATION_SID";
    public static final String DELETION = "DELETION";
    public static final String PRODUCT_AT = "@PRODUCT";
    public static final String CONTRACT_AT = "@CONTRACT";
    public static final String CUSTOMER_AT = "@CUSTOMER";
    public static final String SALES_PROJECTION_RESULTS = "Sales Projection Results";
    public static final String ITEM_NAME = "Item Name";
    public static final String CONTRACT_NAME_LABEL = "Contract Name";
    public static final String CONTRACT_NO1 = "contractNo";
    public static final String ITEM_NAME_SMALL_PROPERY = "itemName";
    public static final String ITEM_NO_LABEL = "Item No";
    public static final String CONTRACT_NO_LABEL = "Contract No";
    public static final String PROJECTED_AMOUNT_LABEL = "Projected Amount";
    public static final String DEFAULT_COLUMN = "defaultColumn";
    public static final String PROJECTED_AMOUNT1 = "ProjectedAmount";
    public static final String PROJECTED_RPU1 = "Projected RPU";
    public static final String DEMAND_SALES1 = "Demand Sales";
    public static final String CONTRACT_SALES_WAC_AT = "Contract Sales @ WAC";
    public static final String QUARTER1 = "Quarter";
    public static final String PROJECT_FREQUENCY1 = "projectFrequency";
    public static final String PROJECTED_SALES1 = "ProjectedSales";
    public static final String ACCOUNT_GROWTH1 = "AccountGrowth";
    public static final String PROJECTED_UNITS1 = "ProjectedUnits";
    public static final String PRODUCT_GROWTH1 = "ProductGrowth";
    public static final String TOTAL_DISCOUNT_CHANGE_PERCENTAGE = "totaldiscountchangepercentage";
    public static final String TOTAL_DISCOUNT_PER_PRIOR_PERCENTAGE = "totaldiscountperpriorpercentage";
    public static final String CONTRACT_SALES_WAC_VAR_PER = "ContractSalesWACVarPer";
    public static final String CURRENCY_TWO_DECIMAL = "currencyTwoDecimal";
    public static final String PERCENT_ONE_DECIMAL = "percentOneDecimal";
    public static final String TOTAL_DISCOUNT_PER_PROJECTEDPERCENTAGE = "totaldiscountperprojectedpercentage";
    public static final String HIERARCHY_SID_AT = "@HIERARCHY_SID";
    public static final String ONE_DECIMAL = "oneDecimal";
    public static final String PERCENT_TWO_DECIMAL = "percentTwoDecimal";
    public static final String DISCOUNT_DOL = "discountDol";
    public static final String PERCENT2_DECIMAL = "percent2Decimal";
    public static final String CURRENCY_DECIMAL = "currencyDecimal";
    public static final String MONTH1 = "Month";
    public static final String CCPD_DOT = " ccpd.";
    public static final String CONTRACT_MASTER_SID1 = "CONTRACT_MASTER_SID";
    public static final String ITEM_MASTER_SID1 = "ITEM_MASTER_SID";
    public static final String SPACE_WHERE = " WHERE ";
    public static final String FROM_SPACE = " FROM ";
    public static final String COMPANY_MASTER_SID1 = "COMPANY_MASTER_SID";
    public static final String TABLE1 = "table";
    public static final String COLUMN1 = "column";
    public static final String PERCENT_THREE_DECIMAL = "percentThreeDecimal";
    public static final String EEE_MMM_Z_YYYY = "EEE MMM dd HH:mm:ss Z yyyy";
    public static final String NET_EXFACT_SALES = "Net Ex-Factory Sales";
    public static final String NET_EXFACT_SALES_PER_EXFACT = "Net Ex-Factory Sales as % of Ex-Factory Sales";
    public static final String NET_EXFACT_SALES_COLUMN_VALUE = "NEFSValue";
    public static final String NET_EXFACT_SALES_HEADER_VALUE = "Net Ex-Factory Sales Value";
    public static final String NET_EXFACT_SALES_COLUMN_VARIANCE = "NEFSVariance";
    public static final String NET_EXFACT_SALES_HEADER_VARIANCE = "Net Ex-Factory Sales Variance";
    public static final String NET_EXFACT_SALES_COLUMN_PER_CHANGE = "NEFSPerChange";
    public static final String NET_EXFACT_SALES_HEADER_PER_CHANGE = "Net Ex-Factory Sales %Change";
    public static final String NET_EXFACT_SALES_PER_EXFACT_COLUMN_VALUE = "NEFSPEFValue";
    public static final String NET_EXFACT_SALES_PER_EXFACT_HEADER_VALUE = "Net Ex-Factory Sales as % of Ex-Factory Sales Value";
    public static final String NET_EXFACT_SALES_PER_EXFACT_COLUMN_VARIANCE = "NEFSPEFVariance";
    public static final String NET_EXFACT_SALES_PER_EXFACT_HEADER_VARIANCE = "Net Ex-Factory Sales as % of Ex-Factory Sales Variance";
    public static final String NET_EXFACT_SALES_PER_EXFACT_COLUMN_PER_CHANGE = "NEFSPEFPerChange";
    public static final String NET_EXFACT_SALES_PER_EXFACT_HEADER_PER_CHANGE = "Net Ex-Factory Sales as % of Ex-Factory Sales %Change";
    public static final String PROGRAM_CATEGORY_LABEL = "Program Category";
    public static final String MASTER_TABLE_SID_COLUMN = "[?MASTER_TABLE_SID_COLUMN]";
    public static final String MODULE = "module";
    public static final String PROD_HIERARCHY_SID = "prodHierarchySid";
    public static final String PRODUCT_HIERARCHY_LEVEL = "productHierarchyLevel";
    public static final String FORECASTING_TYPE = "forecastingType";
    public static final String CUSTOMER_HIERARCHY_LEVEL = "customerHierarchyLevel";
    public static final String CUSTOMER_GROUP_SID = "customerGroupSid";
    public static final String PROD_GROUP_SID = "prodGroupSid";
    public static final String CUSTOMER_HIERARCHY_SID = "customerHierarchySid";
    public static final String FROM_DATE = "fromDate";
    public static final String SELECT_VALUE = "selectValue";
    public static final String LEFT_JOIN_VALUE = "leftJoinValue";
    public static final String LAZY_LOAD_RESULTS = "lazyLoadResults";
    public static final String PMPY_CALCULATOR = "PMPY Calculator";
    public static final String FORECAST_NONFAMP = "FORECAST NON-FAMP";
    public static final String BASIC_URA = "BASIC URA";
    public static final String TOTAL_URA_LABEL = "Total URA";
    public static final String BASIC_URA1 = "Basic URA";
    public static final String LOWEST_COMMERCIAL_NET_PRICE = "LOWEST COMMERCIAL NET PRICE";
    public static final String FILTERMODIFIED_DATE_SEARCHTO = "filter~modifiedDateSearch~to";
    public static final String FILTERMODIFIED_DATE_SEARCHFROM = "filter~modifiedDateSearch~from";
    public static final String NA_PROJ_MASTER_SID = "naProjMasterSid";
    public static final String THERAPEUTIC_CLASS = "therapeuticClass";
    public static final String FILTERPROJECTION_NAME = "filter~projectionName";
    public static final String FILTER_COMPANY = "filter~company";
    public static final String EEE_MMM_DD_H_HMMSS_Z_YYYY = "EEE MMM dd HH:mm:ss z yyyy";
    public static final String FILTER_PRODUCT_GROUP = "filter~productGroup";
    public static final String FILTER_THERAPEUTIC_CLASS = "filter~therapeuticClass";
    public static final String FILTERCREATED_DATE_SEARCHFROM = "filter~createdDateSearch~from";
    public static final String ORDER_BYBUSINESS_UNIT_NAME = "orderBy~businessUnitName";
    public static final String ORDER_BYMODIFIED_DATE_SEARCH = "orderBy~modifiedDateSearch";
    public static final String ORDER_BYCREATED_BY = "orderBy~createdBy";
    public static final String ORDER_BYCOMPANY = "orderBy~company";
    public static final String ORDER_BYPRODUCT_GROUP = "orderBy~productGroup";
    public static final String ORDER_BYPROJECTION_NAME = "orderBy~projectionName";
    public static final String ORDER_BYCREATED_DATE_SEARCH = "orderBy~createdDateSearch";
    public static final String ORDER_BYTHERAPEUTIC_CLASS = "orderBy~therapeuticClass";
    public static final String FILTERCREATED_DATE_SEARCHTO = "filter~createdDateSearch~to";
    public static final String SPACE_AND_SPACE = " AND ";
    public static final String MISSING_DATA = "Missing Data";
    public static final String PLEASE_SELECT_ALL_REQUIRED_FIELDS_BEFORE = "Please select all required fields before clicking the Generate button.";
    public static final String TWO_YEARS_LABEL = "2 Years";
    public static final String FCP_RESULTS = "FCP Results";
    public static final String THREE_FIFTY_THREE_PX = "353px";
    public static final String MASTER_FCP_WORKSHEET = "Master FCP Worksheet";
    public static final String MEDICAID_URA_WORKSHEET = "Medicaid URA Worksheet";
    public static final String IS_CREATED = "isCreated";
    public static final String INVALID_GROWTH_RATE = "Invalid Growth Rate";
    public static final String INVALID_WAC_RATE = "Numeric Value";
    public static final String GROWTH_RATE_IN_PERCENT = "Please enter Growth Rate in Percent with 4 decimal places.";
    public static final String WAC_RATE_IN_PERCENT = "Please enter a numeric value in the ‘% of WAC’ text box.";
    public static final String SPECIAL_STRING_REGEX = "^(0*100{1,1}\\.?((?<=\\.)0*)?%?$)|(^0*\\d{0,2}\\.?((?<=\\.)\\d{4})?%?)$";
    public static final String WAC_SPECIAL_STRING_REGEX = "^(0*100{1,1}\\.?((?<=\\.)0*)?%?$)|(^0*\\d{0,8}\\.?((?<=\\.)\\d{2})?%?)$";
    public static final String NINE_THOUSAND_PX = "900px";
    public static final String NO_GROWTH_RATE = "No Growth Rate";
    public static final String NO_WAC_RATE = "No % of WAC Entered";
    public static final String PLEASE_SELECT_A_PRICE_BASIS = "Please select a Price Basis.";
    public static final String PLEASE_SELECT_A_PRICE_TREND = "Please select a value from the Price Trending field.";
    public static final String PLEASE_SELECT_A_START_PERIOD = "Please select a start period that occurs before the selected end period.";
    public static final String PLEASE_ENTER_IN_A_GROWTH_RATE = "Please enter in a Growth rate %.";
    public static final String PLEASE_ENTER_IN_A_WAC_RATE = "Please enter a value in the ‘% of WAC’ text box.";
    public static final String WARNING = "Warning";
    public static final String NO_FREQUENCY_SELECTED = "No Frequency Selected";
    public static final String PLEASE_SELECT_A_FREQUENCY = "Please select a Frequency.";
    public static final String NO_PRICE_BASIS_SELECTED = "No Price Basis Selected";
    public static final String NO_PRICE_TREND_SELECTED = "No Price Trending price basis Selected";
    public static final String NATIONAL_ASSUMPTIONS_SCREEN = "National Assumptions";
    public static final String UPDATE_CONFIRMATION_ALERT = "Update confirmation";
    public static final String NATIONAL_ASSUMPTION_SCREEN_NAME = "National Assumption";
    public static final String NON_FAMP_RESULTS = "Non-FAMP Results";
    public static final String PPA_FROM_DATE = "PPAFromDate";
    public static final String PPA_TO_DATE = "PPAToDate";
    public static final String AND_IMITEM_NO_LIKE_FILTER_AND_IMITEM_NAME = "AND IM.ITEM_NO LIKE @FILTER AND IM.ITEM_NAME LIKE @FILTER";
    public static final String CONTRACTSID_AT_SMALL = "@contractsid";
    public static final String BRANDSID_AT_SMALL = "@brandsid";
    public static final String PROJID_AT_SMALL = "@projid";
    public static final String CUSTOMER1_SMALL = "customer";
    public static final String COMPSID_AT_SMALL = "@compsid";
    public static final String GROUP1_SMALL = "group1";
    public static final String PERIODS_COMMA = "  PERIODS,";
    public static final String PERIODS_SPACE = " ,PERIODS";
    public static final String TOTAL_DISCOUNT_PERCEN = "Total Discount %";
    public static final String TODIS_LABEL = "TODIS";
    public static final String HISTORY1 = "HISTORY";
    public static final String FUTURE = "FUTURE";
    public static final String TOT_RPU = "totRPU";
    public static final String ST_PRODUCT_FILE = "ST_PRODUCT_FILE";
    public static final String FROM_SLASH_N = " from (\n";
    public static final String CLOSE_SALE_SLASH_N = "\n) SALE \n";
    public static final String AS_PERIODS_SLASH_N = "'0' as PERIODS,\n ";
    public static final String IYEAR_N = " I.\"YEAR\" \n";
    public static final String MONTHLY_COLUMN = "MONTHLY";
    public static final String ANNUAL_CAPS = "ANNUAL";
    public static final String ALL_DISCOUNT_GROUP1 = "All Discount Group";
    public static final String ALL_SALES_GROUP1 = "All Sales Group";
    public static final String NET_SALES_VALUE1 = "NetSalesValue";
    public static final String NET_PROFIT_VALUE1 = "NetProfitValue";
    public static final String NDC_NAME = "ndcName";
    public static final String PROJ_NUM_AT = "@PROJ_NUM";
    public static final String COGC_VALUE = "COGCValue";
    public static final String MANDATED_RPU = "Mandated RPU";
    public static final String CREATED_BY_SMALL = "createdBy";
    public static final String CREATED_DATE_SMALL = "createdDate";
    public static final String PROGRAM1 = "Program";
    public static final String SELECTED_HIERARCHY_NO_HASH = "#SELECTED_HIERARCHY_NO";
    public static final String DISCOUNT_AMOUNT_PER1 = "DiscountAmountPer";
    public static final String RPU_PER1 = "RPUPer";
    public static final String CHANGE1 = "Change";
    public static final String NET_SALES_EX_FACTORY_VARIANCE1 = "NetSalesExFactoryVariance";
    public static final String EX_FAC_VARIANCE1 = "ExFacVariance";
    public static final String PER_INV_WITH_PER1 = "PerInvWithPer";
    public static final String DISCOUNT_PER_EX_FACTORY_VALUE1 = "DiscountPerExFactoryValue";
    public static final String PER_DEMAND_SALES_PER1 = "PerDemandSalesPer";
    public static final String CONTRACT_SALES_WAC_VAR1 = "ContractSalesWACVar";
    public static final String NET_SALES_VARIANCE1 = "NetSalesVariance";
    public static final String NET_PROFIT_PER1 = "NetProfitPer";
    public static final String INV_WITH_PER1 = "InvWithPer";
    public static final String NET_SALES_PER1 = "NetSalesPer";
    public static final String DISCOUNT_SALES_PER1 = "DiscountSalesPer";
    public static final String PER_INV_WITH_VARIANCE1 = "PerInvWithVariance";
    public static final String DISCOUNT_PER_EX_FACTORY_PER1 = "DiscountPerExFactoryPer";
    public static final String PER_EX_FAC_PER1 = "PerExFacPer";
    public static final String DEMAND_SALES_VARIANCE1 = "DemandSalesVariance";
    public static final String DEMAND_SALES_PER1 = "DemandSalesPer";
    public static final String COGC_PER1 = "COGCPer";
    public static final String FILTER_BETWEEN = "filter~Between~";
    public static final String INV_WITH_VARIANCE1 = "InvWithVariance";
    public static final String FILTER_GOE = "filter~GOE~";
    public static final String RPU_VAR1 = "RPUVar";
    public static final String NET_PROFIT_VARIANCE1 = "NetProfitVariance";
    public static final String COGC_VARIANCE1 = "COGCVariance";
    public static final String NET_SALES_EX_FACTORY_PER1 = "NetSalesExFactoryPer";
    public static final String CONTRACT_UNITS_PER1 = "ContractUnitsPer";
    public static final String DISCOUNT_PER_EX_FACTORY_VAR1 = "DiscountPerExFactoryVar";
    public static final String EX_FAC_PER1 = "ExFacPer";
    public static final String CONTRACT_SALES_WAC_VAR_PER1 = "ContractSalesWACVarPer";
    public static final String DISCOUNT_SALES_VAR1 = "DiscountSalesVar";
    public static final String PER_EX_FAC_VARIANCE1 = "PerExFacVariance";
    public static final String DISCOUNT_AMOUNT_VAR1 = "DiscountAmountVar";
    public static final String PER_DEMAND_SALES_VARIANCE1 = "PerDemandSalesVariance";
    public static final String QUARTERLY1 = "QUARTERLY";
    public static final String CONTRACT_UNITS_VAR1 = "ContractUnitsVar";
    public static final String NET_SALES_EX_FACTORY_VALUE1 = "NetSalesExFactoryValue";
    public static final String VARIANCE_COLUMN = "VARIANCE";
    public static final String GET_PROJECTION_LISTS = "getProjectionLists";
    public static final String FILTER_BETWEENCREATED_DATE_FROMTO = "filter~Between~createdDateFrom~to";
    public static final String FILTERCREATED_BY = "filter~createdBy";
    public static final String FILTER_BETWEENCREATED_DATE_FROMFROM = "filter~Between~createdDateFrom~from";
    public static final String AND_PMCREATED_DATE = " AND PM.CREATED_DATE <= '";
    public static final String FILTER_CUSTOMER = "filter~customer";
    public static final String FILTERPROJECTION_DESCRIPTION = "filter~projectionDescription";
    public static final String FILTER_GO_ECREATED_DATE_FROMFROM = "filter~GOE~createdDateFrom~from";
    public static final String FILTER_CONTRACT = "filter~contract";
    public static final String FILTER_GO_ECREATED_DATE_FROMTO = "filter~GOE~createdDateFrom~to";
    public static final String PRODUCT_FILE_DATA_HASH = " #PRODUCT_FILE_DATA ";
    public static final String PLEASE_ENTER_THE_CORRECT_VALUE_DOT = "Please Enter the correct value .";
    public static final String TWO_HUNDRED_PX = "200px";
    public static final String ADJUST_LABEL = "adjust-label";
    public static final String HUNDRED_PERCENT = "100.0%";
    public static final String SINGLE_DECIMAL_FORMAT = "####.0";
    public static final String NO_SEARCH_VALUE_ENTERED_MSG = "There is no search value entered. Please enter a search value and try again.";
    public static final String SEARCH_COMPLETED_HEAD = "Search Completed";
    public static final String NO_SEARCH_VALUE_ENTERED = "No Search Value Entered";
    public static final String NO_RESULT_FOUND_MSG = "There were no results found that match the entered search criteria. \nPlease try again.";
    public static final String NO_RESULTS_FOUND = "No Results";
    public static final String SUMMARY = "Summary";
    public static final String ALLOCATION = "Allocation";
    public static final String SIX_THIRTY = "-06-30";
    public static final String STRING_SEVEN_ONE = "-07-01";
    public static final String PROJECTION_UNITS = "projectionUnits";
    public static final String PROJECTION_PAYMENTS = "projectionPayments";
    public static final String ONE_ONE = "-01-01";
    public static final String TWELVE_THIRTY_ONE = "-12-31";
    public static final String ACTUAL_PAYMENTS = "actualPayments";
    public static final String ACTUAL_UNITS_PROPERTY = "actualUnits";
    public static final String TABLE_HEADER_CENTER = "table-header-center";
    public static final String DATA_SELECTION_VALUES_HAVE_CHANGED = "Data Selection values have changed. All other tabs will be updated and unsaved data will be lost. Continue?";
    public static final String RETURNS_PROJECTION = "Returns Projection";
    public static final String PRC_M_DISCOUNT_INSERT = "PRC_M_DISCOUNT_INSERT";
    public static final String DISCOUNT3 = "discount";
    public static final String SALES1 = "sales";
    public static final String BUSINESS_ROLE_IDS = "businessRoleIds";
    public static final String WORKFLOW_ID_MSG = "The workflow with workflow Id ";
    public static final String SELECTION_CRITERIA_HEADER = "Selection Criteria";
    public static final String NOT_ALL_REQUIRED_FIELDS_POPULATED = "Not all required fields have been populated. Please try again.";
    public static final String DEDUCTION_CATEGORY1 = "Deduction Category";
    public static final String EDIT_MODE_PROJECTION_PROD_SELECTION = "EDIT_MODE_PROJECTION_PROD_SELECTION";
    public static final String DEDUCTION_PROGRAM_TYPE1 = "Deduction Program Type";
    public static final String EDIT_MODE_PROJECTION_CUST_SELECTION = "EDIT_MODE_PROJECTION_CUST_SELECTION";
    public static final String DEDUCTION_SCHEDULE_TYPE1 = "Deduction Schedule Type";
    public static final String RS_CATEGORY1 = "RS_CATEGORY";
    public static final String REBATE_PROGRAM_TYPE1 = "REBATE_PROGRAM_TYPE";
    public static final String DISPLAY_VALUE_PROPERTY = "displayValue";
    public static final String PORTLET_NAME_PROPERTY = "portletName";
    public static final String NM_DISCOUNT_PROJECTION_TABLE = " NM_DISCOUNT_PROJECTION ";
    public static final String IYEAR_AS_YEARS_COMMA = "I.\"YEAR\" as YEARS, ";
    public static final String ST_NM_DISCOUNT_PROJECTION_TABLE = " ST_NM_DISCOUNT_PROJECTION ";
    public static final String NM_ACTUAL_DISCOUNT_TABLE = " NM_ACTUAL_DISCOUNT ";
    public static final String PROJECTION_DETAILS_SID_COLUMN = " PROJECTION_DETAILS_SID ";
    public static final String NM_DISCOUNT_PROJ_MASTER_TABLE = "  NM_DISCOUNT_PROJ_MASTER ";
    public static final String ST_NM_ACTUAL_DISCOUNT_TABLE = " ST_NM_ACTUAL_DISCOUNT ";
    public static final String CCP_DETAILS_SID_COLUMN = " CCP_DETAILS_SID ";
    public static final String ST_NM_DISCOUNT_PROJ_MASTER_TABLE = " ST_NM_DISCOUNT_PROJ_MASTER ";
    public static final String FROM_SMALL = " from ";
    public static final String ST_NM_SALES_PROJECTION_TABLE = " ST_NM_SALES_PROJECTION ";
    public static final String ST_PRODUCT_FILE_TABLE = " ST_PRODUCT_FILE ";
    public static final String NM_ACTUAL_SALES_1 = " NM_ACTUAL_SALES ";
    public static final String NM_SALES_PROJECTION_1 = " NM_SALES_PROJECTION ";
    public static final String PRODUCT_FILE1 = " PRODUCT_FILE ";
    public static final String ST_NM_ACTUAL_SALES_TABLE = " ST_NM_ACTUAL_SALES ";
    public static final String IMONTH_AS_PERIODS_COMMA = "I.\"MONTH\" as PERIODS, ";
    public static final String MULTIPLE_SMALL = "multiple";
    public static final String SPACE_LEFT_JOIN_SPACE = " LEFT JOIN ";
    public static final String FILTERCONTRACT_NO = "filter~contractNo";
    public static final String FREQUENCY_DOLLAR = "[$FREQUENCY]";
    public static final String ST_ALTERNATE_HIST_ALLOCATION = "ST_ALTERNATE_HIST_ALLOCATION";
    public static final String ST_DISC_ALTERNATE_HIST_ALLOCATION = "ST_DISC_ALTERNATE_HIST_ALLOCATION";
    public static final String SELECTED_CHECKBOX = "[$SELECTED_CHECKBOX]";
    public static final String BUSINESS_UNIT_NAME_PROPERTY = "businessUnitName";
    public static final String THERAPUTIC_CLASS_PROPERTY = "theraputicClass";
    public static final String FILTERCONTRACT_HOLDER = "filter~contractHolder";
    public static final String AND_MONTH_EQUAL = "' and \"MONTH\" = '";
    public static final String BUSINESS_UNIT_NO_PROPERTY = "businessUnitNo";
    public static final String PROJECTION_CUST_HIERARCHY1 = " PROJECTION_CUST_HIERARCHY ";
    public static final String PROJECTION_PROD_HIERARCHY1 = " PROJECTION_PROD_HIERARCHY ";
    public static final String START_FREQUENCY_AT = "@START_FREQUENCY";
    public static final String END_FREQUENCY = "@END_FREQUENCY";
    public static final String USER_ENTERED_VALUE = "@USER_ENTERED_VALUE";
    public static final String INPUT = "input";
    public static final String ST_NM_SALES_PROJECTION = "ST_NM_SALES_PROJECTION";
    public static final String LEVEL_NO1 = "@LEVEL_NO";
    public static final String ST_M_SALES_PROJECTION_MASTER = "ST_M_SALES_PROJECTION_MASTER";
    public static final String ST_NM_SALES_PROJECTION_MASTER = "ST_NM_SALES_PROJECTION_MASTER";
    public static final String SESSION_ID1_AT = "@SESSION_ID";
    public static final String USER_ID1_AT = "@USER_ID";
    public static final String ACTUAL_TABLE_AT = "@ACTUAL_TABLE";
    public static final String UNION_AT = "@UNION";
    public static final String USER_ID_ADDITION = "@USER_ID_ADDITION";
    public static final String PROJECTION_TABLE = "@PROJECTION_TABLE";
    public static final String FREQUENCY_GROUP_AT = "@FREQUENCY_GROUP@,";
    public static final String AND_MONTH_ZERO = " AND \"MONTH\" = 0 ";
    public static final String JOIN_CONDITION_AT = "@JOIN_CONDITION";
    public static final String RETURNS_DETAILS_SID_AT = "@RETURNS_DETAILS_SID";
    public static final String VARIABLE1_AT = "@VARIABLE";
    public static final String ACTUAL_UNITS1 = "-ActualUnits";
    public static final String PROJECTED_RETURN_AMOUNT = "PROJECTED_RETURN_AMOUNT";
    public static final String USER_ENTERED_PROPERTY_VALUE = "@USER_ENTERED_PROPERTY_VALUE";
    public static final String WHERE_YEAR_EQUAL = "WHERE YEAR = ";
    public static final String ST_M_SALES_PROJECTION = "ST_M_SALES_PROJECTION";
    public static final String USERID_ADD = "@USERID_ADD";
    public static final String FREQUENCY1_AT = "@FREQUENCY";
    public static final String FREQUENCY_DIVISION = "@FREQUENCY_DIVISION";
    public static final String FREQ_AT = "@FRE@";
    public static final String COLUMN_NAME = "@COLUMN_NAME";
    public static final String AT_FREQUENCY_GROUP_AT = "@FREQUENCY_GROUP@";
    public static final String YEAR1_AT = "@YEAR";
    public static final String VALUE1_AT = "@VALUE";
    public static final String PFREQUENCY_PYEAR = "P.?FREQUENCY?, P.\"YEAR\"";
    public static final String AT_FREQUENCY_AT = "@FREQUENCY@";
    public static final String PERIOD1_AT = "@PERIOD";
    public static final String MASTER_TABLE = "@MASTER_TABLE";
    public static final String ALTERNATE_HISTORY = "Alternate_History";
    public static final String HISTORY_PROJECTED_RETURN_AMOUNT = "-HistoryProjectedReturnAmount";
    public static final String HISTORY_PROJECTED_RPU = "-HistoryProjectedRPU";
    public static final String HISTORY_PROJECTED_RETURN = "-HistoryProjectedReturn%";
    public static final String HISTORY_GROWTH_RATE = "-HistoryGrowthRate";
    public static final String ACCOUNT_GROWTH_TILT = "-AccountGrowth";
    public static final String ACCOUNT_GROWTH_1 = " Account Growth";
    public static final String HISTORY_PROJECTED_UNITS1 = "-HistoryProjectedUnits";
    public static final String PRODUCT_GROWTH_SPACE = "  Product Growth";
    public static final String PROJECTED_SALES_SPACE = "  Projected Sales";
    public static final String PROJECTED_UNITS_SPACE = "  Projected Units";
    public static final String PROJECTED_UNITS_TILT = "-ProjectedUnits";
    public static final String PROJECTED_RETURN_PERCENT = " Projected Return %";
    public static final String ACTUAL_SALES_AT = "-ActualSales";
    public static final String ACTUAL_UNITS_SPACE = " Actual Units";
    public static final String PROJECTED_SALES = " Projected Sales";
    public static final String PROJECTED_RETURN_AMOUNT1 = "Projected Return Amount";
    public static final String PROJECTED_SALES2 = "-ProjectedSales";
    public static final String ACTUAL_UNITS_LABEL = "Actual Units";
    public static final String ACTUAL_UNITS_DASH = "-ActualUnits";
    public static final String SPACE_ACCOUNT_GROWTH = "  Account Growth";
    public static final String LOE_DATE = "loeDate";
    public static final String LEVEL_NAME_LABEL = "Level Name";
    public static final String PRODUCT_GROWTH_TILT = "-ProductGrowth";
    public static final String HISTORY_PROJECTED_SALES_DASH = "-HistoryProjectedSales";
    public static final String CLOSED_DATE = "closedDate";
    public static final String RELATIONSHIP_LEVEL_NAME1 = "relationshipLevelName";
    public static final String LEVEL_VALUE_SMALL = "levelValue";
    public static final String PRC_M_PROJECTION_RESULTS = "PRC_M_PROJECTION_RESULTS";
    public static final String MANDATED_SALES_PROJECTIONS_QUERY_NEW = "mandated-sales-projections-query-new";
    public static final String ITEMSID_AT = "@itemsid";
    public static final String ITEMNO_DDLB = "itemno-ddlb";
    public static final String FREQUENCY_COLUMN = "[$FREQUENCY_COLUMN]";
    public static final String THREE_FIFTEEN_PX = "315px";
    public static final String PERIOD_SMALL = "period";
    public static final String PIVOT_SMALL = "Pivot";
    public static final String NO_RECORD_WAS_SELECTED_PLEASE_TRY_AGAIN = "No record was selected.  Please try again.";
    public static final String SELECT_RECORD = "Select Record";
    public static final String ADJUSTMENT_FSS = "Adjustment FSS";
    public static final String OVERRIDE_FSS = "Override FSS";
    public static final String QNON_FAMP = "QNON-FAMP";
    public static final String ADJUSTMENT_NON_FAMP = "Adjustment Non-FAMP";
    public static final String OVERRIDE_NON_FAMP = "Override Non-FAMP";
    public static final String DISPLAY_VALUE = "displayValue";
    public static final String SELECT_RECORD1 = "Select Record";
    public static final String SPACE_AND_SMALL = " and";
    public static final String SPACE_WHERE_SMALL = " where ";
    public static final String ORDER_BY_PRODUCT_GROUP_NAME = "orderBy~productGroupName";
    public static final String ORDER_BY_PRODUCT_GROUP_DESCRIPTION = "orderBy~productGroupDescription";
    public static final String A = "A";
    public static final String GROUP_VALUE_VERIFICATION = "The group value entered conflicted with the group filter. Kindly Click Refresh button before proceeding this operation";
    public static final String CALCULATION_COMPLETE = "Calculation Complete";
    public static final String FIELD_ERROR = "Field Error";
    public static final String HISTORIC_PERIOD_SELECTION = "Please select a historic period to use as a baseline for each of the selected discounts.";
    public static final String HISTORIC_PERIOD_SELECTION_FOR_SELECTED_DISCOUNTS = "Please select only one period to use as a baseline for each discount selected.";
    public static final String VARIABLE_TYPE_SELECTION = "Please select variable type across the baseline periods.";
    public static final String VARIABLE_TYPE_SELECTION_CONFIRMATION = "No variable types selected";
    public static final String ADJUSTMENT_CONFIRMATION = "Please select which periods need to be included in the adjustment for each of the selected discounts.";
    public static final String INCREMENTAL_ADJUSTMENT_CONFIRMATION = "Confirm Incremental adjustment";
    public static final String CONTINUE_CONFIRMATION = ". Are you sure you want to continue?";
    public static final String CHECK_RECORD_REFERENCE = "@CHECK_RECORD";
    public static final String HIERARCHY_COLUMN = "@HIERARCHY_COLUMN";
    public static final String PROGRAMS_REF = "@PROGRAMS";
    public static final String PROGJOIN = "@PROGJOIN";
    public static final String HELPERTABLE_DESC = "HT.DESCRIPTION";
    public static final String INVALID_STRUCTURE = "Invalid Structure";
    public static final String CANNOT_ADD = "You cannot add " ;
    public static final String CHILD_TO = " as a child to " ;
    public static final String TABLE_RELATION_SHIP_LEVEL_DEFINITION = "RELATIONSHIP_LEVEL_DEFINITION";
    public static final String CUSTOMER_HIERARCHY = "Customer Hierarchy";
    public static final String DISPLAY_FORMAT_SAVE="DisplayFormat";
    public static final String DEDUCTION_DYNAMIC_FILTER="deduction-dynamic-filter";
    public static final String DEDUCTION_SID = "@DEDRELBUILDSID";
    public static final String WHERE_FIRST_CAP = "Where";
    public static final String CUSTOMER_LEVEL_DDLB="CustomerLevel";
    public static final String CUSTOMER_LEVEL_VALUE="CustomerLevelValue";
    public static final String PRODUCT_LEVEL_DDLB="ProductLevel";
    public static final String PRODUCT_LEVEL_VALUE="ProductLevelValue";
    public static final String SALES_INCLUSION_DDLB = "SalesInclusion";
    public static final String DEDUCTION_LEVEL_DDLB="DeductionLevel";
    public static final String DEDUCTION_LEVEL_VALUE="DeductionLevelValue";
    public static final String DEDUCTION_INCLUSION_DDLB = "DeductionInclusion";
    public static final String PHS_AMP = "PHS_AMP";
    public static final String PHS_TOTAL_URA = "PHS_TOTAL_URA";
    public static final String CONVERSION_FACTOR = "CONVERSION_FACTOR";
    public static final String CONVERSION_FACTOR_DEFALUT_VALUE = "No Conversion";
    public static final String CLOSE_CONFIRMATION = "Close Confirmation";
    public static final String COMMA =  ", ";
    public static final String OVERRIDE_AMP = "Override AMP";
    public static final String OVERRIDE_BEST_PRICE = "Override Best Price";
    public static final String OVERRIDE_CPI_URA = "Override CPI URA";
    public static final String RELVALUE = "@RELVALUE";
    public static final String RELVERSION = "@RELVERSION";
    public static final String SELECTED_HIERARCHY_CUSTOM = "selected-hierarchy-no-for-custom";
    public static final String RELJOIN = "@RELJOIN";
    public static final String RELATIONSHIPJOIN = " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD1 ON RLD1.HIERARCHY_NO=A.HIERARCHY_NO AND RLD1.VERSION_NO=";
    public static final String RELATIONSHIPJOINCURRENT = " JOIN #CURRENT_SPLIT RLD1 ON RLD1.HIERARCHY_NO=A.HIERARCHY_NO AND RLD1.VERSION_NO=";
    public static final String PARENTVALIDATE = "PARENT-VALIDATE";
    public static final String DED_JOIN = "?DEDJOIN";
    public static final String I_QUARTER = ", I.QUARTER";
    public static final String I_MONTH = ", I.\"MONTH\"";
    public static final String DECLARE_START_MONTH_INT = " DECLARE @START_MONTH INT=";
    public static final String DECLARE_END_YEAR_INT = " DECLARE @END_YEAR INT=";
    public static final String DECLARE_END_MONTH_INT = " DECLARE @END_MONTH INT=";
    public static final String DECLARE_START_YEAR_INT = " DECLARE @START_YEAR INT=";
    public static final String I_SEMI_ANNUAL = ", I.SEMI_ANNUAL";
    public static final String NM_DISCOUNT_PROJ_MASTER = "NM_DISCOUNT_PROJ_MASTER";
    public static final String FROM_RELATIONSHIP_LEVEL_DEFINITION = "        FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n";
    public static final String WHERE_HLDCHIERARCHY_NO = "    WHERE HLDC.HIERARCHY_NO = '";
    public static final String AND_SESSION_ID = " AND SESSION_ID = ";
    public static final String JOIN_DBO_HIERARCHY_LEVEL_DEFINITION = "        JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n";
    public static final String AND_PCHP = "                                 AND PCH.PROJECTION_MASTER_SID=";
    public static final String JOIN_PROJECTION_DETAILS_PD_ON_PDC = "        JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=";
    public static final String SELECT_RLDRELATIONSHIP_LEVEL_VALUES_R = "    (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n";
    public static final String FROM_RELATIONSHIP_RELATION = "                          FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1\n";
    public static final String RL_D1RELA = "                                 RLD1.RELATIONSHIP_LEVEL_SID,\n";
    public static final String JOIN = " JOIN ";
    public static final String CCPMAP_JOIN = "                          ) CCPMAP JOIN \n";
    public static final String CCP_CCP_D = "                                 CCP.CCP_DETAILS_SID\n";
    public static final String PCH_ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1 = "   PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID AND PCH.PROJECTION_MASTER_SID =  ";
    public static final String JOIN_PROJECTION_DETAILS_PD_ON_PDCCP_D = "    JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=";
    public static final String HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE_HLDCHIE = "%') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'\n";
    public static final String AND_PROJECTION_MASTER = "                     AND PROJECTION_MASTER_SID = ";
    public static final String PCH_ON_PCHRELATIONSHIP_LEVEL_SID_RLDRE = "  PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD.RELATIONSHIP_LEVEL_SID \n";
    public static final String HLD_ON_CCPMAPHIERARCHY_NO_LIKE_HLDHIERAR = "%') HLD ON CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO+'%' \n";
    public static final String SELECT_DISTINCT_HLDCRELATIONSHIP_LEVEL_S = " (SELECT distinct HLDC.RELATIONSHIP_LEVEL_SID, HLDC.HIERARCHY_NO, CCPMAPC.CCP_DETAILS_SID FROM \n";
    public static final String FROM_RELATION = "                          FROM   RELATIONSHIP_LEVEL_DEFINITION RLD\n";
    public static final String WHERE_HLDHIERARCHY_NO = "  WHERE HLD.HIERARCHY_NO = '";
    public static final String JOIN_CCP_MAP_CCP_ON_RLDRELATIONSHIP_L = "    JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n";
    public static final String UPDATE_ST_NM_DISCOUNT_PROJ_MASTER_SET_USE = "UPDATE ST_NM_DISCOUNT_PROJ_MASTER SET USER_GROUP='";
    public static final String SELECT_RL_D1HIERA = "                         (SELECT RLD1.HIERARCHY_NO,\n";
    public static final String CCP_MAP = "   ) CCPMAPC   \n";
    public static final String JOIN_PROJECTION_CUST_HIERARCHY_PC = "        JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=";
    public static final String SPACE_JOIN_NEW_LINE = "    JOIN \n";
    public static final String JOIN_RELATIONSHIP_LEVEL_DEFINITIO = "        JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n";
    public static final String FROM_RELATIONSHIP_LEVEL_DEFINITION_RL = "    FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n";
    public static final String SPACE_JOIN_SPACE = "                          JOIN   ";
    public static final String RL_D1LEVE = "                                 RLD1.LEVEL_NO,RLD1.RELATIONSHIP_LEVEL_VALUES \n";
    public static final String SELECT_PROJECTION_DETAILS_SID_FROM_PROJE = " SELECT PROJECTION_DETAILS_SID FROM PROJECTION_DETAILS WHERE CCP_DETAILS_SID IN";
    public static final String JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CUSTOM_VIEW_MASTER = "        JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID=";
    public static final String CLOSE_BRACKET_CCP = ") CCP";
    public static final String SELECT_CCP_MAP_CCP_DETAILS_SID = "(SELECT CCPMAP.CCP_DETAILS_SID \n";
    public static final String SELECT_RL_D2_HIERARCHY_NORLD2_RELATIONSH = "    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n";
    public static final String JOIN_PROJECTION_PROD_HIERARCHY_PC = "        JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=";
    public static final String AND_RL_D1HIERARC = "                         AND  RLD1.HIERARCHY_NO LIKE '";
    public static final String WHERE_RL_D2_HIERARCHY_NO_LIKE = "        WHERE RLD2.HIERARCHY_NO like '";
    public static final String FROM_SELECT_RLD_HIERAR = "                  FROM   (SELECT RLD.HIERARCHY_NO,\n";
    public static final String CCPMAPC = ") CCPMAPC\n";
    public static final String AND_USER_ID = " AND USER_ID = ";
    public static final String WHERE_PROJECTION_DETAILS_SID_IN = " where PROJECTION_DETAILS_SID  IN (  \n";
    public static final String JOIN_PROJECTION = "                          JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID = ";
    public static final String ON_CCPMAPCCCP_DETAILS_SIDCCPMAPPCCP_D = "    ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID\n";
    public static final String RL_D1_LEVEL = "                                 RLD1.LEVEL_NO\n";
    public static final String CCP = ")CCP";
    public static final String HLDP_ON_CCPMAP_HIERARCHY_NO_LIKE_HLDPHIE = "%') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'\n";
    public static final String JOIN_CCP_MAP_CCP_ON_RLDRELATION = "        JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n";
    public static final String JOIN_CCP_MAP = "                          JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID AND RLD.RELATIONSHIP_BUILDER_SID = ";
    public static final String JOIN_SELECT_RLDRELATIONSHIP_LEVEL_VAL = "    JOIN (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n";
    public static final String AND_CVD_LEVEL_NO_LIKE = " AND CVD.LEVEL_NO like '";
    public static final String FROM_ST_NM_DISCOUNT_PROJ_MASTER_M_PROJEC = " From ST_NM_DISCOUNT_PROJ_MASTER M, PROJECTION_DETAILS E, ";
    public static final String AND_MUSER_GROUP = " AND M.USER_GROUP = '";
    public static final String AND_M_PROJECTION_DETAILS_SID_CCP_PROJ = " AND M.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID \n";
    public static final String AND_M_SESSION_ID = " and M.SESSION_ID = ";
    public static final String AND_EPROJECTION_DETAILS_SID_MPROJECTION = " AND E.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID \n";
    public static final String SELECT_CCPMAPPROJECTION_DETAILS_SID = "(SELECT CCPMAP.PROJECTION_DETAILS_SID\n";
    public static final String AND_EPROJECTION_MASTER_SID = " AND E.PROJECTION_MASTER_SID ='";
    public static final String WHERE_HLD = "    WHERE HLD";
    public static final String WHERE_M_USER_ID = " Where M.USER_ID = ";
    public static final String DEDUCTION_VALUE = "deductionValue";
    public static final String IS_ORDERED = "isOrdered";
    public static final String DISCOUNT_SID = "discountSid";
    public static final String MM_DD_YYYY = "MM/dd/yyyy";
    public static final String MODULE_NAME = "moduleName";
    public static final String FILTER_CREATED_BY = "filter~createdBy";
    public static final String FILTER_BUSINESS_UNIT_NAME = "filter~businessUnitName";
    public static final String FILTER_DISCOUNT = "filter~discount";
    public static final String BUSINESS_UNIT = "businessUnit";
    public static final String COMPANY_MASTER_SID = "companyMasterSid";
    public static final String FILTER_DEDUCTION_LEVEL = "filter~deductionLevel";
    public static final String FILTER_COMPANY_NAME = "filter~companyName";
    public static final String FILTER_PRODUCT_HIERARCHY = "filter~productHierarchy";
    public static final String COMPANY_SID = "companySid";
    public static final String FILTER_MODIFIED_DATE_SEARCH_TO = "filter~modifiedDateSearch~to";
    public static final String ORDER_BY_CUSTOMER_HIERARCHY_LEVEL = "orderBy~customerHierarchyLevel";
    public static final String ORDER_BY_PM_CREATED_DATE = " ORDER BY PM.CREATED_DATE ";
    public static final String FILTER_PROJECTION_NAME = "filter~projectionName";
    public static final String ORDER_BY_CREATED_DATE_SEARCH = "orderBy~createdDateSearch";
    public static final String FILTER_CUSTOMER_HIERARCHY = "filter~customerHierarchy";
    public static final String FILTER_DESCRIPTION = "filter~description";
    public static final String AND_PMCREATED_DATE_BETWEEN = " AND PM.CREATED_DATE BETWEEN '";
    public static final String FILTER_CREATED_DATE_SEARCH_TO = "filter~createdDateSearch~to";
    public static final String UNDERSCORE_AND_UNDERSCORE = "' AND '";
    public static final String FILTER_PRODUCT_HIERARCHY_LEVEL = "filter~productHierarchyLevel";
    public static final String ORDER_BY_PROJECTION_NAME = "orderBy~projectionName";
    public static final String ORDER_BY_MODIFIED_DATE_SEARCH = "orderBy~modifiedDateSearch";
    public static final String ORDER_BY_CUSTOMER_HIERARCHY = "orderBy~customerHierarchy";
    public static final String ACCRUAL_RATE_PROJECTION = "AccrualRateProjection";
    public static final String FILTER_CREATED_DATE_SEARCH_FROM = "filter~createdDateSearch~from";
    public static final String FILTER_CUSTOMER_HIERARCHY_LEVEL = "filter~customerHierarchyLevel";
    public static final String ORDER_BY_BUSINESS_UNIT_NAME = "orderBy~businessUnitName";
    public static final String ORDER_BY_DESCRIPTION = "orderBy~description";
    public static final String AND_PMFORECASTING_TYPE_LIKE = " AND PM.FORECASTING_TYPE like '";
    public static final String ORDER_BY_PRODUCT_HIERARCHY = "orderBy~productHierarchy";
    public static final String FILTER_MODIFIED_DATE_SEARCH_FROM = "filter~modifiedDateSearch~from";
    public static final String AND_PMPROJECTION_DESCRIPTION_LIKE = " AND PM.PROJECTION_DESCRIPTION like '";
    public static final String TO_DATE = "toDate";
    public static final String ORDER_BY_CREATED_BY = "orderBy~createdBy";
    public static final String FILTER_DEDUCTION_VALUE = "filter~deductionValue";
    public static final String ORDER_BY_COMPANY_NAME = "orderBy~companyName";
    public static final String DEDUCTION_LEVEL = "deductionLevel";
    public static final String ORDER_BY_PRODUCT_HIERARCHY_LEVEL = "orderBy~productHierarchyLevel";
    public static final String SELECT_CMCONTRACT_MASTER_SID_FROM_CONTRACT = ", (SELECT CM.CONTRACT_MASTER_SID FROM CONTRACT_MASTER CM";
    public static final String REMOVE_LEVELS = "removeLevels";
    public static final String DELETE_TEMP_ON_UPDATE = "deleteTempOnUpdate";
    public static final String JOIN_IFP_CONTRACT_DETAILS_IFP_CD_SID_ON_CNT = "Join  IFP_CONTRACT_DETAILS IFP_CD_SID  ON  IFP_CD_SID.IFP_CONTRACT_SID=IFP_SID.IFP_CONTRACT_SID\n";
    public static final String SELECT_DISTINCT_IFP_CD_SID_ITEM_MASTER_SID = "Select Distinct IFP_CD_SID.ITEM_MASTER_SID from CONTRACT_MASTER CM \n";
    public static final String IS_NDC = "isNdc";
    public static final String AVAILABLE_HIER_NO = "availableHierNo";
    public static final String SAVE_CCP = "saveCcp";
    public static final String JOIN_IFP_CONTRACT_DETAILS_IFP_CD_SID_ON_I = "Join IFP_CONTRACT_DETAILS IFP_CD_SID ON IFP_CD_SID.IFP_CONTRACT_SID=IFP_SID.IFP_CONTRACT_SID\n";
    public static final String DESCRIPTION_LIKE = "DESCRIPTION like '";
    public static final String JOIN_RS_CONTRACT_RS_C_TYPE_ON_RS_C_TYPE = "Join RS_CONTRACT RS_C_TYPE ON RS_C_TYPE.CONTRACT_MASTER_SID=CM.CONTRACT_MASTER_SID AND \n";
    public static final String ORDER_BY_DEDUCTION_VALUE = "orderBy~deductionValue";
    public static final String SELECT_DISTINCT_IM_THERAPEUTIC_CLASS_FROM = "Select Distinct IM.THERAPEUTIC_CLASS from CONTRACT_MASTER CM\n";
    public static final String SELECT_DISTINCT_IM_BRAND_MASTER_SID_FROM_C = "Select Distinct IM.BRAND_MASTER_SID from CONTRACT_MASTER CM\n";
    public static final String HTBL = "?HTBL";
    public static final String COMPANY_FILTER = "companyFilter";
    public static final String GET_FS_VALUE = "getFSValue";
    public static final String HNO = "?HNO";
    public static final String AND_LIST_NAME = "' AND LIST_NAME='";
    public static final String HAS_TRADING_PARTNER = "hasTradingPartner";
    public static final String RL_SIDS = "rlSids";
    public static final String AND_NEW_LINE = "AND\n";
    public static final String OFFSET = "offset";
    public static final String IN_SELECT_HELPER_TABLE_SID_FROM_HELPER_TABLE = " IN(Select HELPER_TABLE_SID from HELPER_TABLE where  ";
    public static final String HIERARCHY_NO_LIKE = "HIERARCHY_NO LIKE '";
    public static final String UNSAVED_PROJECTION_IDS = "getUnsavedProjectionIds";
    public static final String CHILD_LEVEL_RL = "getChildLevelRL";
    public static final String PID = "?PID";
    public static final String JOIN_ITEM_MASTER_IM_ON_IM_ITEM_MASTER_SID = "Join ITEM_MASTER IM ON IM.ITEM_MASTER_SID=IFP_CD_SID.ITEM_MASTER_SID AND\n";
    public static final String ORDER_BY_DEDUCTION_LEVEL = "orderBy~deductionLevel";
    public static final String GET_REMOVABLE_CHILDREN = "getRemovableChildren";
    public static final String CHILD_LEVEL_RL_SID = "getChildLevelRLSid";
    public static final String OFFSET_WITH_CAPS = " OFFSET ";
    public static final String LEVEL_LOWER_CASE = "level";
    public static final String IFP_SID_CONTRACT_MASTER_SID_IN_SELECT_CONT = "IFP_SID.CONTRACT_MASTER_SID IN (Select CONTRACT_MASTER_SID from RS_CONTRACT\n";
    public static final String JOIN_IFP_CONTRACT_DETAILS_IFP_CD_SID_ON = "Join IFP_CONTRACT_DETAILS IFP_CD_SID  ON  IFP_CD_SID.IFP_CONTRACT_SID=IFP_SID.IFP_CONTRACT_SID\n";
    public static final String JOIN_IFP_CONTRACT_IFP_SID_ON_CM_CONTRACT_M = "Join IFP_CONTRACT IFP_SID ON CM.CONTRACT_MASTER_SID = IFP_SID.CONTRACT_MASTER_SID\n";
    public static final String ROWS_FETCH_NEXT = " ROWS FETCH NEXT ";
    public static final String RS_C_TYPE = "RS_C_TYPE.";
    public static final String RLC = "?RLC?";
    public static final String ROWS_ONLY = " ROWS ONLY;";
    public static final String IN_SELECT_HELPER_TABLE_SID_FROM_HELPER_TA = " IN(Select HELPER_TABLE_SID from HELPER_TABLE where DESCRIPTION like'";
    public static final String DTBL = "?DTBL";
    public static final String SPACE_ROWS_ONLY_SPACE = " ROWS ONLY ";
    public static final String RELATIONSHIP_SID = "relationshipSid";
    public static final String ORDER_BY_LOWEST_LEVEL = "orderBy~lowestLevel";
    public static final String J1_WHERE_J1 = " J1 WHERE J1.";
    public static final String ORDER_BY_HIGHEST_LEVEL = "orderBy~highestLevel";
    public static final String FILTER_PRODUCT_GROUP_NO = "filter~productGroupNo";
    public static final String FILTER_CUSTOMER_GROUP_DESCRIPTION = "filter~customergroupDescription";
    public static final String WHERE_SPACE = "where ";
    public static final String ACTION = "action";
    public static final String FILTER_CUSTOMER_GROUP_NO = "filter~customerGroupNo";
    public static final String ORDER_BY_HIERARCHY_NAME = "orderBy~hierarchyName";
    public static final String HIERARCHY_NAME = "hierarchyName";
    public static final String GET_HIERARCHY_GROUP = "getHierarchyGroup";
    public static final String FILTER_PRODUCT_GROUP_NAME = "filter~productGroupName";
    public static final String ORDER_BY = "?ORDER_BY?";
    public static final String IN_EXECUTE_QUERY = " in execute query";
    public static final String FILTER_LOWEST_LEVEL = "filter~lowestLevel";
    public static final String SELECT_IM_ITEM_MASTER_SID_FROM_ITEM_MASTER = ", (SELECT IM.ITEM_MASTER_SID FROM ITEM_MASTER IM";
    public static final String FILTER_HIERARCHY_NAME = "filter~hierarchyName";
    public static final String FILTER_CUSTOMER_GROUP_NAME = "filter~customerGroupName";
    public static final String START = "start";
    public static final String FILTER_HIGHEST_LEVEL = "filter~highestLevel";
    public static final String HIERARCHY_TYPE = "hierarchyType";
    public static final String IS_FILTERED = "isFiltered";
    public static final String SPACE_LIKE_SPACE = " like '";
    public static final String COUNT = "count";
    public static final String GROUP_IDENTIFIER = "groupIdentifier";
    public static final String FILTER_PRODUCT_GROUP_DESCRIPTION = "filter~productgroupDescription";
    public static final String SELECTION = "?SELECTION?";
    public static final String ORDER_BY_PRODUCT_GROUP_NO = "orderBy~productGroupNo";
    public static final String FILTER_PRODUCT_LEVEL = "filter~productLevel";
    public static final String ORDER_BY_CUSTOMER_GROUP_DESCRIPTION = "orderBy~customergroupDescription";
    public static final String ORDER_BY_CUSTOMER_LEVEL = "orderBy~customerLevel";
    public static final String ORDER_BY_CUSTOMER_GROUP_NO = "orderBy~customerGroupNo";
    public static final String ORDER_BY_CUSTOMER_GROUP_NAME = "orderBy~customerGroupName";
    public static final String FILTER_CUSTOMER_GROUP = "filter~customerGroup";
    public static final String ORDER_BY_PRODUCT_LEVEL = "orderBy~productLevel";
    public static final String FILTER_BRAND_TYPE = "filter~brandType";
    public static final String ORDER_BY_PRODUCT_GROUP = "orderBy~productGroup";
    public static final String FILTER_CUSTOMER_LEVEL = "filter~customerLevel";
    public static final String ORDER_BY_VIEW_NAME = "orderBy~viewName";
    public static final String ORDER_BY_CUSTOMER_GROUP = "orderBy~customerGroup";
    public static final String FILTER_VIEW_NAME = "filter~viewName";
    public static final String ORDER_BY_BRAND_TYPE = "orderBy~brandType";
    public static final String ORDER_BY_FROM = "orderBy~from";
    public static final String ORDER_BY_TO = "orderBy~to";
    public static final String QUESTION_FILTER_QUESTION = "?FILTER?";
    public static final String WHEN_END_MONTH_8_THEN_CONVERT_DATE = " WHEN @END_MONTH = 8 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @END_YEAR) + '-08-31') \n ";
    public static final String WHEN_START_MONTH_3_THEN_CONVERT_DATE = " WHEN @START_MONTH = 3 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @START_YEAR) + '-03-01') \n ";
    public static final String WHEN_START_MONTH_10_THEN_CONVERT_DATE = " WHEN @START_MONTH = 10 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @START_YEAR) + '-10-01') \n ";
    public static final String WHEN_END_MONTH71_THEN_CONVERT_DATE = " WHEN @END_MONTH = 7 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @END_YEAR) + '-07-31') \n ";
    public static final String SELECT_PR_YEAR_PR = "SELECT PR.YEAR,PR.";
    public static final String WHEN_START_MONTH_5_THEN_CONVERT_DATE = " WHEN @START_MONTH = 5 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @START_YEAR) + '-05-01') \n ";
    public static final String WHEN_END_MONTH_2_THEN_CONVERT_DATE = " WHEN @END_MONTH = 2 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @END_YEAR) + '-02-28') \n ";
    public static final String WHEN_START_MONTH_6_THEN_CONVERT_DATE = " WHEN @START_MONTH = 6 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @START_YEAR) + '-06-01') \n ";
    public static final String WHEN_START_MONTH_2_THEN_CONVERT_DATE = " WHEN @START_MONTH = 2 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @START_YEAR) + '-02-01') \n ";
    public static final String WHEN_START_MONTH_9_THEN_CONVERT_DATE = " WHEN @START_MONTH = 9 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @START_YEAR) + '-09-01') \n ";
    public static final String WHEN_END_MONTH_1_THEN_CONVERT_DATE = " WHEN @END_MONTH = 1 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @END_YEAR) + '-01-31') \n ";
    public static final String WHEN_START_MONTH_12_THEN_CONVERT_DATE = " WHEN @START_MONTH = 12 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @START_YEAR) + '-12-01') \n ";
    public static final String WHEN_END_MONTH_11_THEN_CONVERT_DATE = " WHEN @END_MONTH = 11 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @END_YEAR) + '-11-30') \n ";
    public static final String WHEN_END_MONTH_10_THEN_CONVERT_DATE = " WHEN @END_MONTH = 10 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @END_YEAR) + '-10-31') \n ";
    public static final String WHEN_END_MONTH_4_THEN_CONVERT_DATE = " WHEN @END_MONTH = 4 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @END_YEAR) + '-04-30') \n ";
    public static final String WHEN_START_MONTH_8_THEN_CONVERT_DATE = " WHEN @START_MONTH = 8 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @START_YEAR) + '-08-01') \n ";
    public static final String WHEN_END_MONTH_5_THEN_CONVERT_DATE = " WHEN @END_MONTH = 5 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @END_YEAR) + '-05-31') \n ";
    public static final String WHEN_START_MONTH_11_THEN_CONVERT_DATE = " WHEN @START_MONTH = 11 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @START_YEAR) + '-11-01') \n ";
    public static final String WHEN_END_MONTH_6_THEN_CONVERT_DATE = " WHEN @END_MONTH = 6 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @END_YEAR) + '-06-30') \n ";
    public static final String WHEN_END_MONTH_12_THEN_CONVERT_DATE = " WHEN @END_MONTH = 12 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @END_YEAR) + '-12-31') \n ";
    public static final String WHEN_END_MONTH_3_THEN_CONVERT_DATE = " WHEN @END_MONTH = 3 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @END_YEAR) + '-03-31') \n ";
    public static final String WHERE_MPROJECTION_DETAILS_SID = " WHERE  M.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID \n";
    public static final String WHEN_START_MONTH_7_THEN_CONVERT_DATE = " WHEN @START_MONTH = 7 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @START_YEAR) + '-07-01') \n ";
    public static final String WHEN_START_MONTH_4_THEN_CONVERT_DATE = " WHEN @START_MONTH = 4 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @START_YEAR) + '-04-01') \n ";
    public static final String WHEN_START_MONTH_1_THEN_CONVERT_DATE = " WHEN @START_MONTH = 1 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @START_YEAR) + '-01-01') \n ";
    public static final String WHEN_END_MONTH_9_THEN_CONVERT_DATE = " WHEN @END_MONTH = 9 THEN CONVERT(DATE, CONVERT(VARCHAR(4), @END_YEAR) + '-09-30') \n ";
    public static final String AND_NM_AD_USER_ID_NM_ADM_USER_ID = " AND NMAD.USER_ID = NMADM.USER_ID AND NMAD.SESSION_ID = NMADM.SESSION_ID AND NMADM.RS_MODEL_SID = NMAD.RS_MODEL_SID\n";
    public static final String AND_NM_DP_RS_MODEL_SID_RS_RS_MODEL_SID = " AND NMDP.RS_MODEL_SID = RSM.RS_MODEL_SID AND NMDP.USER_ID = NMSPM.USER_ID AND NMDP.SESSION_ID = NMSPM.SESSION_ID ";
    public static final String AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT_CAST_PR = " AND cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH AS VARCHAR),2)>=";
    public static final String ON_NM_SP_PROJECTION_DETAILS_SID = " ON NMSP.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID AND NMSP.PERIOD_SID = PR.PERIOD_SID";
    public static final String I_YEAR_RS_NAME_ORDER_BY_JRS_NAME = ",I.YEAR,J.RS_NAME order by J.RS_NAME";
    public static final String JOIN_ST_NM_SALES_PROJECTION_MASTER_NM_SP = "  JOIN ST_NM_SALES_PROJECTION_MASTER NMSPM ON NMSPM.PROJECTION_DETAILS_SID = NMSP.PROJECTION_DETAILS_SID\n";
    public static final String AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT_CAST_PRMO = " AND cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH AS VARCHAR),2) <=";
    public static final String GROUP_BY_PR_YEAR = "GROUP BY PR.YEAR,PR.";
    public static final String AND_MUSER_ID = " and M.USER_ID = ";
    public static final String AND_B_SESSION_ID = " and B.SESSION_ID=";
    public static final String ORDER_BY_D_YEAR = " order by D.YEAR";
    public static final String UNION_ALL = " UNION ALL";
    public static final String AS_BASE_MAX_NM_AS_ACTUAL_SALES = " AS BASE, MAX(NMAS.ACTUAL_SALES) AS ACTUAL_SALES, SUM(NMAD.ACTUAL_SALES)";
    public static final String OPEN_BRACKET_NEW_LINE = ") \n ";
    public static final String JOIN_PERIOD_PR_ON_PR_PERIOD_SID_NM_AD = " JOIN PERIOD PR ON PR.PERIOD_SID = NMAD.PERIOD_SID JOIN ST_NM_ACTUAL_SALES NMAS ON NMAS.PERIOD_SID = PR.PERIOD_SID \n";
    public static final String AND_A_PROJECTION_DETAILS_SID_IN = " and A.PROJECTION_DETAILS_SID IN (";
    public static final String DP_ON_PD_PROJ_DETAILS_SID_DP_PROJ = " DP ON PD.PROJECTION_DETAILS_SID=DP.PROJECTION_DETAILS_SID  \n";
    public static final String GROUP_BY_HLEVEL_NO_HRELATIONSHIP_LEVEL_V = " group by H.LEVEL_NO ,H.RELATIONSHIP_LEVEL_VALUES, H.LEVEL_NAME , CCP.HIERARCHY_NO, I.\"YEAR\"  ";
    public static final String ON_NM_DP_PROJECTION_DETAILS_SID_NM = " ON NMDP.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID AND NMDP.PERIOD_SID = PR.PERIOD_SID";
    public static final String AS_ACTUAL_DISCOUNT_MAX_IS_NULL_NM_SP = " AS ACTUAL_DISCOUNT,MAX(ISNULL(NMSP.PROJECTION_SALES, 0)) AS PROJECTION_SALES, SUM(ISNULL(NMDP.PROJECTION_SALES, 0))\n";
    public static final String AND_NM_ADM_SESSION_ID = " AND NMADM.SESSION_ID =";
    public static final String AND_NM_SP_M_USER_ID_NM_SP_USER_ID_AND_NM_SP = " AND NMSPM.USER_ID = NMSP.USER_ID AND NMSPM.SESSION_ID = NMSP.SESSION_ID\n";
    public static final String AND_ASESSION_ID = " and A.SESSION_ID=";
    public static final String AND_NM_AS_PROJECTION_DETAILS_SID_NM_ADM_PRO = " AND NMAS.PROJECTION_DETAILS_SID = NMADM.PROJECTION_DETAILS_SID JOIN ST_NM_SALES_PROJECTION_MASTER NMSPM ";
    public static final String AND_DP_PRICE_GROUP_TYPE_IN = " AND DP.PRICE_GROUP_TYPE in (";
    public static final String JOIN_ST_NM_DISCOUNT_PROJ_MASTER_NM_ADM_ON = " JOIN ST_NM_DISCOUNT_PROJ_MASTER NMADM ON  NMADM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n";
    public static final String JOIN_RS_MODEL_RS_ON_DP_RS_MODEL_SID = " JOIN RS_MODEL RS ON DP.RS_MODEL_SID = RS.RS_MODEL_SID  AND RS.RS_NAME  in (";
    public static final String SELECT_JRS_NAME_YEAR = "Select J.RS_NAME,I.YEAR,I.";
    public static final String AS_BASE_000_AS_ACTUAL_SALES_000_AS_ACTUAL = " AS BASE, 0.00 AS ACTUAL_SALES,0.00 AS ACTUAL_DISCOUNT,MAX(NMSP.PROJECTION_SALES)\n";
    public static final String SPACE_AND_NEW_LINE = " AND \n ";
    public static final String AND_DP_SESSION_ID = " and DP.SESSION_ID = ";
    public static final String AND_BPROJECTION_MASTER_SID = " AND B.PROJECTION_MASTER_SID ='";
    public static final String SPACE_CASE_NEW_LINE = " CASE \n ";
    public static final String JOIN_PERIOD_PR_ON_PR_PERIOD_SID = " JOIN PERIOD PR ON PR.PERIOD_SID = NMDP.PERIOD_SID JOIN ST_NM_SALES_PROJECTION NMSP ON NMSP.PERIOD_SID = PR.PERIOD_SID\n";
    public static final String AS_PROJECTION_SALES_SUM_NM_DP_PROJECTION_SAL = " AS PROJECTION_SALES,SUM(NMDP.PROJECTION_SALES) AS PROJECTION_DISCOUNT,PR.";
    public static final String AND_CAST_PR_YEAR_AS_VARCHAR_RIGHT = " AND cast(PR.YEAR as varchar(4))+RIGHT ('0'+CAST(PR.MONTH AS VARCHAR),2) >=";
    public static final String DOT_HIERARCHY_NO = ".HIERARCHY_NO = '";
    public static final String AND_NM_SPM_SESSION_ID_NM_AS_SESSION_ID = "  AND NMSPM.SESSION_ID = NMAS.SESSION_ID LEFT JOIN ST_NM_SALES_PROJECTION NMSP";
    public static final String FROM_PROJECTION_DETAILS_PD_JOIN_ST_NM = " FROM PROJECTION_DETAILS PD JOIN ST_NM_DISCOUNT_PROJ_MASTER NMDPM ON  NMDPM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n";
    public static final String JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL = "   JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID   \n";
    public static final String SPACE_END_NEW_LINE = " END \n ";
    public static final String ON_NM_SP_M_PROJECTION_DETAILS_SID_NM_AS_PROJ = " ON NMSPM.PROJECTION_DETAILS_SID = NMAS.PROJECTION_DETAILS_SID AND NMSPM.USER_ID = NMAS.USER_ID \n";
    public static final String AND_NM_AD_MUSER_ID = ") AND NMADM.USER_ID =";
    public static final String AND_NM_SP_M_USER_ID_NM_DP_M_USER_ID_AND_NM_SPM = " AND NMSPM.USER_ID = NMDPM.USER_ID AND NMSPM.SESSION_ID = NMDPM.SESSION_ID ";
    public static final String AND_NM_AS_USER_ID_NM_AD_USER_ID = " AND NMAS.USER_ID = NMAD.USER_ID AND NMAS.SESSION_ID = NMAD.SESSION_ID \n";
    public static final String JOIN_RS_MODEL_RSM_ON_RS_M_RS_MODEL_SID_NM = " JOIN RS_MODEL RSM ON RSM.RS_MODEL_SID = NMADM.RS_MODEL_SID AND RSM.RS_MODEL_SID = NMAD.RS_MODEL_SID\n";
    public static final String AND_DP_USER_ID = " and DP.USER_ID = ";
    public static final String AND_NM_DP_MUSER_ID = " AND NMDPM.USER_ID =";
    public static final String AS_PROJECTION_DISCOUNT_PR = " AS PROJECTION_DISCOUNT,PR.";
    public static final String AND_RS_M_RS_NAME_IN = " and RSM.RS_NAME IN (";
    public static final String AND_RS_M_RS_MODEL_SID_NM_DP_RS_MODEL_SID_WH = " AND RSM.RS_MODEL_SID = NMDP.RS_MODEL_SID WHERE PD.PROJECTION_DETAILS_SID in (";
    public static final String PR_MONTH_RS_M_RS_NAME = ",PR.MONTH,RSM.RS_NAME,SUM(NMDP.PROJECTION_RATE) AS PROJECTION_RATE,0.0 AS ACTUAL_UNITS,";
    public static final String PR_MONTH_PD_PROJECTION_DETAILS_SID_RS = ",PR.MONTH,PD.PROJECTION_DETAILS_SID, RSM.RS_NAME ";
    public static final String JOIN_ST_NM_ACTUAL_DISCOUNT_NM_AD_ON_NM_ADP = " JOIN ST_NM_ACTUAL_DISCOUNT NMAD ON NMAD.PROJECTION_DETAILS_SID =NMADM.PROJECTION_DETAILS_SID\n";
    public static final String AND_NM_DP_M_SESSION_ID = " AND NMDPM.SESSION_ID =";
    public static final String WHERE_PD_PROJECTION_DETAILS_SID_IN = " WHERE PD.PROJECTION_DETAILS_SID in(";
    public static final String AND_NM_SP_USER_ID_NM_SPM_USER_ID_AND_NM_SP = " AND NMSP.USER_ID = NMSPM.USER_ID AND NMSP.SESSION_ID = NMSPM.SESSION_ID LEFT JOIN ST_NM_DISCOUNT_PROJECTION NMDP";
    public static final String AND_NM_DP_USER_ID_NM_DP_M_USER_ID = " AND NMDP.USER_ID = NMDPM.USER_ID AND NMDP.SESSION_ID = NMDPM.SESSION_ID AND NMDPM.RS_MODEL_SID = NMDP.RS_MODEL_SID\n";
    public static final String SUM_NM_SP_PROJECTION_UNITS_AS_PROJECTION_UN = " Sum(NMSP.PROJECTION_UNITS) as PROJECTION_UNITS\n";
    public static final String GROUP_BY_I = " group by I.";
    public static final String AND_B_USER_ID = " and B.USER_ID=";
    public static final String PR_MONTH = ",PR.MONTH";
    public static final String HIERVER = "@HIERVER";
    public static final String WHERE_RL_D1_HIERARCHY_NO_LIKE_HLD = "   WHERE RLD1.HIERARCHY_NO like '%' ) HLD      \n";
    public static final String JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP = "   JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID='";
    public static final String AND_P_CH_PROJECTION_MASTER_SID = "   AND PCH.PROJECTION_MASTER_SID='";
    public static final String RELATIONSHIP_LEVEL_SID_HLD = ".RELATIONSHIP_LEVEL_SID,HLD";
    public static final String WHERE_PM_PROJECTION_MASTER_SID = "   WHERE PM.PROJECTION_MASTER_SID='";
    public static final String SPACE_NEW_LINE = "'     \n";
    public static final String JOIN_PROJECTION_DETAILS_PD_ON_PD_CCP_DET = "  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID='";
    public static final String SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RLD = "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD      \n";
    public static final String COUNT_DISTINCT_CASE_WHEN_NM_MAS_CALCULATE = "  count(distinct case when  nm_mas.CALCULATION_PERIODS is null then '1' else  nm_mas.CALCULATION_PERIODS end)     AS calcPeriodCount,    ";
    public static final String COUNT_DISTINCT_CASE_WHEN_NM_MAS_METHODOLOGY = "  count(distinct case when  nm_mas.METHODOLOGY is null then '1' else  nm_mas.METHODOLOGY end)     AS methoCount,    ";
    public static final String HIERARCHY_NO_CCP_MAP_CCCP_DETAILS_SID_FROM = ".HIERARCHY_NO, CCPMAPC.CCP_DETAILS_SID FROM     \n";
    public static final String AND_PC_H2_PROJECTION_MASTER_SID = "   AND PCH2.PROJECTION_MASTER_SID='";
    public static final String SPACE_DOUBLE_NEW_LINE = "'          \n\n";
    public static final String SELECT_NEW_LINE = "  SELECT \n";
    public static final String AND_NM_AC_SESSION_ID = "'  AND nm_ac.SESSION_ID = '";
    public static final String AND_NM_MAS_SESSION_ID = "' AND nm_mas.SESSION_ID = '";
    public static final String AND_NM_MAS_CALCULATION_PERIODS = "    and nm_mas.CALCULATION_PERIODS= '";
    public static final String JOIN_SELECT_DISTINCT_L_CCPRELATION = "        JOIN (SELECT distinct LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from       \n";
    public static final String AND_NM_MAS_USER_GROUP = "    and nm_mas.USER_GROUP= '";
    public static final String JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD = "  JOIN RELATIONSHIP_LEVEL_DEFINITION rld  ON  CCP.RELATIONSHIP_LEVEL_SID=rld.RELATIONSHIP_LEVEL_SID ";
    public static final String AND_RLD_LEVEL_NO = "   and rld.LEVEL_NO = '";
    public static final String WHERE_PD_PROJECTION_MASTER_SID = " WHERE  pd.PROJECTION_MASTER_SID =  '";
    public static final String AND_NM_MAS_METHODOLOGY = "    and nm_mas.METHODOLOGY= '";
    public static final String AVG_NM_SP_PRODUCT_GROWTH_SUM_NM_SP_PROJEC = "  avg(nm_sp.PRODUCT_GROWTH),   sum(nm_sp.PROJECTION_SALES),   sum(nm_sp.PROJECTION_UNITS), \n";
    public static final String JOIN_PERIOD_P = "   JOIN PERIOD p     \n";
    public static final String JOIN_ST_NM_ACTUAL_SALES_NM_AC = "   JOIN  ST_NM_ACTUAL_SALES nm_ac     \n";
    public static final String AVG_NM_SP_ACCOUNT_GROWTH = "  avg(nm_sp.ACCOUNT_GROWTH),   \n";
    public static final String SELECT_CCP_MAP_CCP_DETAILS_SID_HLD_HIER = "   (SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from       \n";
    public static final String UNION_NEW_LINE = "   Union \n";
    public static final String ON_PERIOD_SID_NM_AC_PERIOD_SID = "   ON p.period_sid = nm_ac.PERIOD_SID        \n";
    public static final String JOIN_PROJECTION_PROD_HIERARCHY_PCH = "    JOIN PROJECTION_PROD_HIERARCHY PCH     \n";
    public static final String WHERE = "WHERE";
    public static final String FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD2 = "   FROM RELATIONSHIP_LEVEL_DEFINITION RLD2      \n";
    public static final String JOIN_SELECT_DISTINCT_HLD = "   JOIN    (SELECT distinct HLD";
    public static final String CHECK_RECORD_0 = " CHECK_RECORD=0 ";
    public static final String ON_PCH_RELATIONSHIP_LEVEL_SID_RL_D1_REL = "   ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID       \n";
    public static final String BLANK_QUOTES = "     ";
    public static final String COLON_AND_CVD_LEVEL_NO_LIKE = "' AND CVD.LEVEL_NO like '";
    public static final String JOIN_PERIOD_P_ON_PERIOD_SID_NM_SP_PERIOD = "  JOIN PERIOD p  ON p.period_sid = nm_sp.PERIOD_SID     WHERE  pd.PROJECTION_MASTER_SID =  '";
    public static final String SELECT_RL_D1_HIERARCHY_NO_RLD1_RELATIONSHIP = "   (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID      \n";
    public static final String SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RL = "   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID       \n";
    public static final String JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON_CVDC = "   JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID='";
    public static final String ON_CCP_DETAILS_SI_DPD_CCP_DETAILS_SID = "   ON CCP.CCP_DETAILS_SID=pd.CCP_DETAILS_SID   \n";
    public static final String JOIN_PROJECTION_CUST_HIERARCHY_PCH = "    JOIN PROJECTION_CUST_HIERARCHY PCH     \n";
    public static final String JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD_JOIN_NM_SALES_PROJ = "   JOIN RELATIONSHIP_LEVEL_DEFINITION rld  ON  CCP.RELATIONSHIP_LEVEL_SID=rld.RELATIONSHIP_LEVEL_SID   JOIN ST_NM_SALES_PROJECTION_MASTER nm_mas   \n";
    public static final String IN_NEW_LINE = "     in (  \n";
    public static final String JOIN_ST_NM_SALES_PROJECTION_MASTER = "  JOIN ST_NM_SALES_PROJECTION_MASTER nm_mas   \n";
    public static final String WHERE_CCP_MAP_HIERARCHY_NO_LIKE_HLD_HIER = "   WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO + '%' ) LCCP      \n";
    public static final String JOIN_PERIOD_P_ON_PPERIOD_SID_NM_SP_PE = "   JOIN PERIOD p  ON p.period_sid = nm_sp.PERIOD_SID     WHERE  pd.PROJECTION_MASTER_SID =  '";
    public static final String SELECT_PROJECTION_DETAILS_SID_FROM_PROJ_DETAILS = "  SELECT PROJECTION_DETAILS_SID FROM  PROJECTION_DETAILS WHERE CCP_DETAILS_SID IN  \n";
    public static final String FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD1 = "   FROM RELATIONSHIP_LEVEL_DEFINITION RLD1       \n";
    public static final String HLD_WHERE_CCP_MAP_HIERARCHY_NO_LIKE_HLD = " ) HLD  WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO + '%' ) LCCP      \n";
    public static final String AND_NM_SP_SESSION_ID = "'  AND nm_sp.SESSION_ID = '";
    public static final String JOIN_DBO_HIERARCHY_LEVEL_DEFINITION_HLD = "   JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID   \n";
    public static final String WHERE_RLD2_HIERARCHY_NO_LIKE = "   WHERE RLD2.HIERARCHY_NO like  '";
    public static final String JOIN_PROJECTION_CUST_HIERARCHY_PC_H2 = "   JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID='";
    public static final String CCP_MAP_ON_CCP_MAP_CCP_DETAILS_SID = "   ) CCPMAPP ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID   \n";
    public static final String SELECT_DISTINCT_PD_PROJECTION_DETAILS = "   SELECT distinct pd.PROJECTION_DETAILS_SID   \n";
    public static final String SELECT_RLD_RELATIONSHIP_LEVEL_VALUES = "   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD   \n";
    public static final String SELECT_LCCP_DETAILS_SID_FROM = "   (SELECT LCCP.CCP_DETAILS_SID from       \n";
    public static final String AND_RLD_HIERARCHY_NO = "'  and rld.HIERARCHY_NO='";
    public static final String HLDP_ON_CCP_MAP_HIERARCHY_NO_LIKE_HLD = "' ) HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'   \n";
    public static final String HLD_NEW_LINE = " ) HLD      \n";
    public static final String FROM_PROJECTION_DETAILS_PD = "   FROM   projection_details pd   \n";
    public static final String JOIN_SELECT_RL_D2_HIERARCHY_NO_RLD = "   JOIN  (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD   \n";
    public static final String CCP_NEW_LINE = "   ) CCP   \n";
    public static final String WHERE_L_CCP_HIERARCHY_NO_IN = "   WHERE LCCP.HIERARCHY_NO in       \n";
    public static final String JOIN_CCP_MAP_CCP_ON_RLD_RELATIONSHIP_LEVEL_SID = "   JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID      \n";
    public static final String JOIN_PROJECTION_MASTER_PM_ON_PD_PROJECTION = "   JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID       \n";
    public static final String CVD_CUSTOM_VIEW_MASTER_SID = "   CVD.CUSTOM_VIEW_MASTER_SID='";
    public static final String JOIN_PROJECTION_PROD_HIERARCHY_PCH2 = "    JOIN PROJECTION_PROD_HIERARCHY PCH2     \n";
    public static final String FROM_RELATIONSHIP_LEVEL_DEFINITION_RLD = "   FROM RELATIONSHIP_LEVEL_DEFINITION RLD       \n";
    public static final String ON_NM_MAS_PROJECTION_DETAILS_SID_NM_SP = "   ON nm_mas.PROJECTION_DETAILS_SID = nm_sp.PROJECTION_DETAILS_SID   \n";
    public static final String JOIN_PROJECTION_PROD_HIERARCHY_PC_H2 = "   JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID='";
    public static final String ON_PCH2_RELATIONSHIP_LEVEL_SID_RLD2 = "   ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID       \n";
    public static final String HLDC_ON_CCP_MAP_HIERARCHY_NO_LIKE_HLD = "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'   \n";
    public static final String JOIN_PROJECTION_CUST_HIERARCHY_PCH2 = "    JOIN PROJECTION_CUST_HIERARCHY PCH2     \n";
    public static final String ON_PD_PROJECTION_DETAILS_SID_NM_MAS_PROJ_DETAILS_SID = "   ON pd.PROJECTION_DETAILS_SID = nm_mas.PROJECTION_DETAILS_SID   \n";
    public static final String CHECK_RECORD_1 = " CHECK_RECORD=1 ";
    public static final String JOIN_ST_NM_SALES_PROJECTION_NM_SP = "  JOIN ST_NM_SALES_PROJECTION nm_sp \n";
    public static final String JOIN_SELECT_RL_D2_HIERARCHY_NO_RLD_REL_LEVEL = "   JOIN    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD   \n";
    public static final String CCP_MAP_NEW_LINE = "') CCPMAP,       \n";
    public static final String SELECT_RL_D2_HIERARCHY_NO = "   (SELECT RLD2.HIERARCHY_NO       \n";
    public static final String AND_NM_SP_USER_ID = "'  AND nm_sp.USER_ID  = '";
    public static final String ON_PD_PROJECTION_DETAILS_SID_NM_MAS_PROJ = "  ON pd.PROJECTION_DETAILS_SID = nm_mas.PROJECTION_DETAILS_SID   \n";
    public static final String COLON_AND_NM_MAS_USER_ID = "   AND nm_mas.USER_ID ='";
    public static final String JOIN_DBO_CUSTOM_VIEW_MASTER_CVM_ON = "   JOIN dbo.CUSTOM_VIEW_MASTER CVM ON   \n";
    public static final String CLOSE_BRACE_AND_USER_ID = "  )  AND USER_ID = '";
    public static final String COLON_AND_SESSION_ID = "' AND SESSION_ID = '";
    public static final String JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD2 = "   JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID   \n";
    public static final String PROJECTION_CUST_HIERARCHY_G = "PROJECTION_CUST_HIERARCHY G,";
    public static final String AND_BUSER_ID = "' and B.USER_ID = '";
    public static final String COUNT_DISTINCT_CASE_WHEN_NM_MAS_CALCULATION = " count(distinct case when  nm_mas.CALCULATION_BASED is null then '1' else  nm_mas.CALCULATION_BASED end)     AS calcBasedcount , SUM(CASE(nm_mas.CHECK_RECORD) WHEN 1 THEN 0 ELSE 1 END) AS UNCHECK_COUNT     ";
    public static final String NEW_LINE = "       \n";
    public static final String AND_NM_MAS_USER_ID = "'  AND nm_mas.USER_ID = '";
    public static final String PROJECTION_PROD_HIERARCHY_G = "PROJECTION_PROD_HIERARCHY G,";
    public static final String AND_G_PROJECTION_MASTER_SID_PROJECTION = " and G.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID and G.RELATIONSHIP_LEVEL_SID = H.RELATIONSHIP_LEVEL_SID";
    public static final String AND_BSESSION_ID = "' and B.SESSION_ID = '";
    public static final String AND_A_PERIOD_SID_IPERIOD_SID_AND_HLEVEL = " and A.PERIOD_SID = I.PERIOD_SID and H.LEVEL_NO =";
    public static final String JOIN_RELATIONSHIP_LEVEL_DEFINITION_RLD2_ON_HLD_HIER = "   JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID  \n";
    public static final String COLON_AND_ASESSION_ID = "' and A.SESSION_ID = '";
    public static final String COMMA_I_MONTH = ",I.MONTH\n";
    public static final String SELECT_RLD_RELATIONSHIP_LEVEL_VALUES_RLD_HIER_NO = "   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD  \n";
    public static final String UPDATE_RECORD = "updateRecord";
    public static final String UNCHECK_ALL = "uncheckAll";
    public static final String GENERATE_SALES_PROJECTION = "generateSalesProjection";
    public static final String PREPARE_PROCEDURE_CALL = "prepareProcedureCall";
    public static final ResourceBundle TABLE_NAME_BUNDLE = ResourceBundle.getBundle("properties.tablename");
    public static final String SAVE_CHECK_RECORD = "saveCheckRecord";
    public static final String GENERATE_SALES_PROJECTION_COUNT = "generateSalesProjectionCount";
    public static final String JOIN_ST_NM_DISCOUNT_PROJECTION_NMDP = " JOIN ST_NM_DISCOUNT_PROJECTION NMDP  ON NMDP.PROJECTION_DETAILS_SID = NMDPM.PROJECTION_DETAILS_SID\n";
    public static final String AND_CAST_PRYEAR_AS_VARCHAR_RIGHT_CAST = " AND  cast(PR.YEAR as varchar(4))+RIGHT('0'+CAST(PR.MONTH AS VARCHAR),2) <=";
    public static final String CLOSE_BRACKET_NEW_LINE = ")  \n";
    public static final int COLUMN_COUNT_TOTAL = 75;
    /**
     * The Constant AMOUNT.
     */
    public static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    public static final String WHERE_BRS_CONTRACT_SID_IN = "  WHERE B.RS_CONTRACT_SID IN ( ";
    public static final String JOIN_SELECTED_HIERARCHY_NO_CCP = "        JOIN #SELECTED_HIERARCHY_NO CCP\n";
    public static final String JOINQUERY = "@JOINQUERY";
    public static final String CROSS_APPLY_SELECT_TOKEN_FROM_UDF_SPLITST = "CROSS APPLY (SELECT TOKEN FROM UDF_SPLITSTRING('";
    public static final String NM_DISCOUNT_PROJ_MASTER_B = "NM_DISCOUNT_PROJ_MASTER B\n";
    public static final String CONCAT_CONDITION = "', ',') C WHERE CH.PROD_HIERARCHY_NO LIKE concat(C.TOKEN , '%')) FN";
    public static final String TWO_DECIMAL_FORMAT = "#,##0.00";
    /**
     * RATE_PER_THREE
     */
    public static final DecimalFormat RATE_PER_THREE = new DecimalFormat(TWO_DECIMAL_FORMAT);
    
    public static final int COLUMN_COUNT_DISCOUNT = 12;
    public static final String ON_BCCP_DETAILS_SID_CCP_DET = "          ON B.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n";
    public static final DecimalFormat AMOUNT_UNITS = new DecimalFormat("#,##0");
    public static final String PRC_PROJ_RESULTS = "PRC_PROJECTION_RESULTS";
    public static final String SELECT_DISTINCT = "SELECT DISTINCT ";
    public static final String INVALID_LEVEL_NO = "Invalid Level No:";
    public static final String DEDUCTION = "DEDUCTION";
    public static final String RELATIONSHIP_LEVEL_DEFINITION_JOIN = " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.PARENT_HIERARCHY_NO LIKE '";
    public static final String TAB_BASED_JOIN = "[?TAB_BASED_JOIN]";
    public static final String RELBUILD_SID = "@RELBUILDSID";
    public static final String STRING_COMMA_ONE = "','1'";
    public static final String JOIN_CCP_MAP_CCP_ON_RLDRELATIONSHIP_LEVEL = " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n";
    public static final String PROJECTION_ID_FLOWER_BRACES = "PROJECTION_ID= {}";
    public static final String BUILDERSID = "@BUILDERSID";
    public static final String USER_DEFINED = "User Defined";
    public static final String EACH = "EACH";
    public static final String SELECT_RL_D2HIERARCHY_NORLD2RELATIONSHIP = " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n";
    public static final String IS_NULL_OPEN = "Isnull(";
    public static final String LEVEL_CAPS = "@LEVEL";
    public static final String LEVEL_VALUES = "@LEVELVALUES";
    public static final String HIERARCHY_NO_JOIN = "', ',') C WHERE CCPH.CUST_HIERARCHY_NO LIKE concat(C.TOKEN , '%')) FN";
    public static final String CUST_HIERARCHY_NO = "CUST_HIERARCHY_NO ";
    public static final String DEDLEVEL_VALUES = "@DEDLEVELVALUES";
    public static final String AND_SPMFILTER_CC_P1 = " AND SPM.FILTER_CCP=1";
    public static final String LEVEL_NO = "levelNo";
    public static final String CCPMAP = ") CCPMAP,";
    public static final boolean VIEW_FLAG = false;
    public static final String JOIN_PARENT_VALIDATE_PR_ON_PRRS_CONTRACT = " JOIN #PARENT_VALIDATE PR ON PR.RS_CONTRACT_SID=MAS.RS_CONTRACT_SID\n ";
    public static final String PROD_HIERARCHY_NO = "PROD_HIERARCHY_NO ";
    public static final String AND_PR_PARENT_HIERARCHY_LIKE_RLD_PARENT_HIER = " AND PR.PARENT_HIERARCHY LIKE RLD.PARENT_HIERARCHY_NO+'%'";
    public static final String WHERE_ITEM_ID = "\t\tWHERE ITEM_ID ='";
    public static final String FROM_ITEM_MASTER = "\t\tFROM ITEM_MASTER\n";
    public static final String AND_NDC9_IN_SELECT_ITEM_MASTER_SID = "  AND NDC9 IN (SELECT ITEM_MASTER_SID\n";
    public static final String USER_ID_WITH_COMMA = " USER_ID,\n";
    public static final String SESSION_ID_WITH_COMMA = " SESSION_ID,\n";

    /**
     * Enum for Frequency constants
     */
    public enum FrequencyConstants {

        ANNUAL("Annual"),
        ANNUALLY("Annually"),
        YEAR("Year"),
        YEARS("Years"),
        SEMI_ANNUAL("Semi-Annual"),
        SEMI_ANNUALLY(SEMIANNUALLY),
        QUARTERLY("Quarterly"),
        MONTHLY("Monthly"),
        MONTHS("Months"),
        QUARTERS("Quarters");
        private final String frequencyValue;

        private FrequencyConstants(String frequencyValue) {
            this.frequencyValue = frequencyValue;
        }

        public String getConstant() {
            return frequencyValue;
        }

        @Override
        public String toString() {
            return frequencyValue;
        }
    }
    public static final String COUNTER_VALUE = "counterValue";
    public static final String RESETCOUNT = "ResetCount";
    public static final String PORTLET_NAME = "PortelName";
    public static final String NUM_VALIDATION = "([0-9])*";
    public static final String NUM_WIHT2DECIMAL = "[-+]?(?:\\d+|\\d*(?:\\.\\d{1,2})?)";
    public static final DecimalFormat TWO_DECIMAL = new DecimalFormat("#,##0.00");
    public static final String CURRENCY = "$";
    public static final DecimalFormat UNIT_FORMAT = new DecimalFormat("#,##0");
    public static final String PROJ_PER_OF_BUISNESS = "Forecast % of Business";
    public static final String SHOW_ALL_GROUPS = "Show All Groups";

    public enum ProjectionConstants {

        FREQUENCY_DIVISION("frequencyDivision"),
        FREQUENCY_PERIOD("frequencyPeriod"),
        PERIOD("period"),
        HISTORY_START_PERIOD("historyStartPeriod"),
        HISTORY_START_PERIOD_DDLB("historyStartPeriodDDLB"),
        HISTORY_END_PERIOD("historyEndPeriod"),
        HISTORY_START_DATE("historyStartDate"),
        HISTORY_END_DATE("historyEndDate"),
        HISTORY_END_DATE_DDLB("historyEndDateDDLB"),
        HISTORY_START_YEAR("historyStartYear"),
        HISTORY_START_YEAR_DDLB("historyStartYearDDLB"),
        HISTORY_END_YEAR("historyEndYear"),
        HISTORY_START_MONTH("historyStartMonth"),
        HISTORY_START_MONTH_DDLB("historyStartMonthDDLB"),
        HISTORY_END_MONTH("historyEndMonth"),
        HISTORY_START_DAY("historyStartDay"),
        HISTORY_END_DAY("historyEndDay"),
        FORECAST_START_PERIOD("forecastStartPeriod"),
        FORECAST_END_PERIOD("forecastEndPeriod"),
        FORECAST_START_DATE("forecastStartDate"),
        FORECAST_END_DATE("forecastEndDate"),
        FORECAST_START_YEAR("forecastStartYear"),
        FORECAST_END_YEAR("forecastEndYear"),
        FORECAST_START_MONTH("forecastStartMonth"),
        FORECAST_END_MONTH("forecastEndMonth"),
        FORECAST_START_DAY("forecastStartDay"),
        FORECAST_END_DAY("forecastEndDay"),
        PROJECTION_START_PERIOD("projectionStartPeriod"),
        PROJECTION_START_PERIOD_DDLB("projectionStartPeriodDDLB"),
        PROJECTION_END_PERIOD("projectionEndPeriod"),
        PROJECTION_START_DATE("projectionStartDate"),
        PROJECTION_START_DATE_DDLB("projectionStartDateDDLB"),
        PROJECTION_END_DATE("projectionEndDate"),
        PROJECTION_START_YEAR("projectionStartYear"),
        PROJECTION_START_YEAR_DDLB("projectionStartYearDDLB"),
        PROJECTION_END_YEAR("projectionEndYear"),
        PROJECTION_START_MONTH("projectionStartMonth"),
        PROJECTION_START_MONTH_DDLB("projectionStartMonthDDLB"),
        PROJECTION_END_MONTH("projectionEndMonth"),
        PROJECTION_START_DAY("projectionStartDay"),
        PROJECTION_END_DAY("projectionEndDay");
        private final String constant;

        private ProjectionConstants(String constant) {
            this.constant = constant;
        }

        public String getConstant() {
            return constant;
        }

        @Override
        public String toString() {
            return constant;
        }
    }

    /**
     * Enum for label constants
     */
    public enum LabelConstants {

        PRODUCT_HIERARCHY(PRODUCT_LABEL),
        METHODOLOGY_MOVING_AVERAGE("Moving Average"),
        LEVEL_BRAND(BRAND_CAPS),
        LEVEL_NDC("NDC"),
        PROJECTION_SEARCH("Projection Search"),
        PROJECTION_NAME("Projection Name"),
        PROJECTION_DESCRIPTION("Projection Description"),
        PRIVATE_VIEW("Private View"),
        DISCOUNT1("Discount 1"),
        DISCOUNT2("Discount 2"),
        ACCESS("Access"),
        PARITY("Parity"),
        DISCOUNT_PARITY("Discount (Parity)"),
        CONTRACT_PRICE("Contract Price"),
        PARITY_SETTINGS("Parity settings"),
        PARITY_REFERENCE("Parity Reference"),
        METHODOLOGY("Methodology"),
        DISCOUNT_AMT(DISCOUNT_AMOUNT_LABEL),
        GROWTH("Growth"),
        DISCOUNT_RATE(DISCOUNT_RATE_LABEL),
        REBATE_PER_UNIT("Rebate Per Unit"),
        PUBLIC_VIEW("Public View"),
        SAVE_VIEW("Save View"),
        VIEW_SEARCH("View Search"),
        VIEW_NAME("View Name"),
        PROJECTION_OPTIONS_CAPTION("Projection Options"),
        CUSTOMER_SELECTION_CAPTION("Customer Selection"),
        PRODUCT_SELECTION_CAPTION("Product Selection"),
        FROM("From"),
        TO("To"),
        ACTUALS("Actuals"),
        CFF_PROJECTIONS("CFF Projections"),
        CFF_PROJECTIONS_COL("CFFProjections"),
        PROJECTIONS("Projections"),
        ACTUAL_RATE("Actual Rate"),
        PROJECTED_RATE("Projected Rate"),
        CFF_PROJECTED_RATE("Projected Rate"),
        ACTUALRATE("ActualsRate"),
        PROJECTEDRATE(PROJECTIONS_RATE),
        CFF_PROJECTEDRATE(PROJECTIONS_RATE),
        ACTUALAMOUNT("ActualsAmount"),
        PROJECTEDAMOUNT("ProjectionsAmount"),
        ACTUAL_AMOUNT("Actual Amount"),
        PROJECTED_AMOUNT("Projected Amount"),
        BOTH("Both"),
        HIERARCHY("Hierarchy"),
        LEVEL("Level"),
        MASS_FIELD_CS("Contract Sales"),
        MASS_FIELD_POB(PERCENTAGEOFBUSINESS),
        CUSTOMER_GROUP_NAME("Customer Group Name"),
        CUSTOMER_GROUP_NO("Customer Group No"),
        PRODUCT_GROUP_NAME("Product Group Name"),
        PRODUCT_GROUP_NO("Product Group No"),
        ASCENDING("Ascending"),
        DESCENDING("Descending"),
        PROGRAM("Program"),
        PROGRAM_CATEGORY("Program Category"),
        BRAND_TYPE("Brand Type"),
        BRAND(BRAND_CAPS),
        CONTRACT("Contract"),
        CONTRACT_NAME("Contract Name"),
        CONTRACT_NO("Contract No"),
        CUSTOMER_ID("Customer ID"),
        CONTRACT_HOLDER_NAME("Contract Holder Name"),
        COMPANY("Company"),
        CONTRACTED("Contracted"),
        ALL_BRANDS("All Brands"),
        MODE("Mode"),
        MODE_ADD("Add"),
        MODE_SEARCH("Search"),
        RESULTS("RESULTS"),
        WINDOW_CUSTOMER_HIERARCHY_LOOKUP("Customer Hierarchy Lookup"),
        WINDOW_PRODUCT_HIERARCHY_LOOKUP("Product Hierarchy Lookup"),
        WINDOW_CUSTOMER_GROUP_LOOKUP("Customer Group Lookup"),
        WINDOW_PRODUCT_GROUP_LOOKUP("Product Group Lookup"),
        SEARCH_CRITERIA("Search Criteria"),
        HIERARCHY_TYPE("Hierarchy Type"),
        HIERARCHY_NAME("Hierarchy Name"),
        PRIMARY("Primary"),
        SECONDARY("Secondary"),
        EXCEL_EXPORT("Export to excel"),
        GRAPH_EXPORT("Show Graph"),
        TAB_DATA_SELECTION("Data Selection"),
        TAB_SALES_PROJECTION(SALES_PROJECTION),
        TAB_SALES_PROJECTION_RESULTS("Sales Projection Results"),
        TAB_DISCOUNT_PROJECTION("Discount Projection"),
        TAB_DISCOUNT_PROJECTION_RESULTS("Discount Projection Results"),
        TAB_PPA_PROJECTION("PPA Projection"),
        TAB_PPA_PROJECTION_RESULTS("PPA Projection Results"),
        TAB_PROJECTION_RESULTS(PROJECTION_RESULTS),
        TAB_PROJECTION_VARIANCE("Projection Variance"),
        TAB_ASSUMPTIONS("Assumptions"),
        TAB_ADDITIONAL_INFORMATION("Additional Information"),
        PERIOD("Period"),
        DISCOUNT("Discount"),
        TREE_STRUCTURE("Tree Structure"),
        TREE_VIEW_NAME("Tree View Name"),
        SALES(SALES_CAPS),
        UNITS(UNITS_SMALL),
        VARIABLE("Variable"),
        LEVEL_FILTER("Level Filter"),
        VIEW("View"),
        PIVOT_VIEW("Pivot View"),
        HISTORY("History :"),
        PRIVATE("Private"),
        PUBLIC("Public"),
        INCREMENTAL("Incremental"),
        OVERRIDE("Override"),
        AMOUNT("Amount"),
        PERCENTAGE("Percentage"),
        CUSTOMER(CUSTOMER_SMALL),
        PRODUCT("Product"),
        CUSTOM(CUSTOM_LABEL),
        BRAND_SEARCH("Brand Search"),
        TIME_PERIOD("Time Period"),
        CUSTOMER_GROUP("Customer Group"),
        PRODUCT_GROUP("Product Group"),
        ENABLE("Enable"),
        PROJECTION_VARIANCE_SELECTION("Projection Variance Selection"),
        COMPARISON("Comparison"),
        FREQUENCY("Frequency :"),
        TOTAL("Total"),
        DETAIL("Detail"),
        DISCOUNT_LEVEL("Discount Level"),
        TOTAL_DISCOUNT("Total Discount"),
        PRICE_GROUP_TYPE("Price Group Type"),
        COMPONENT("Component"),
        PRICE_GROUP("Price Group"),
        DATE_RANGE("Date Range"),
        VARIABLE_CATEGORY("Variable Category"),
        VARIABLES("Variables"),
        PROJECTION_PERIOD_ORDER("Projection Period Order :"),
        VALUE(VALUE1),
        VARIANCE(VARIANCE1),
        PERC_CHANGE(PER_CHANGE),
        GROSS_TRADE_SALES("Gross Trade Sales"),
        EX_FACTORY_SALES(EX_FACTORY_SALES_LABEL),
        DEMAND_SALES(DEMAND_SALES1),
        INVENTORY_WITHDRAW("Inventory Withdrawal Sales"),
        PERC_OF_BUSSINESS(PERCENTAGEOFBUSINESS),
        PERC_OF_EX_FACTORY(PERCOFEXFACTORY),
        PERC_OF_DEMAND("% of Demand"),
        PERC_OF_INVENTORY_WITHDRAW("% of Inventory Withdrawal"),
        CONTRACT_SALES_AT_WAC(CONTRACT_SALES_WAC_AT),
        TOTAL_RPU("Total RPU"),
        COGS("Cost of Goods Sold (COGS)"),
        NET_PROFIT("Net Profit"),
        UNIT_VOL("Unit Volume"),
        CONTRACT_UNIT(CONTRACT_UNITS_LABEL),
        DISCOUNT_AMOUNT(DISCOUNT_DOLLAR_AMOUNT),
        DISCOUNT_PERC(DISCOUNT_PER_AMOUNT),
        TOTAL_DISCOUNT_AMOUNT("Total Discount $"),
        TOTAL_DISCOUNT_PERC("Total Discount %"),
        NET_SALES(NET_SALES1),
        PPA_DISCOUNT("PPA Discount"),
        WORKFLOW_STATUS("Workflow Status"),
        NDC_NO("NDC No"),
        NDC_NAME("NDC Name"),
        CREATED_DATE("Created Date"),
        COMPARISON_LOOKUP("Comparison Lookup"),
        PERIOD_PIVOT_VIEW("Period Pivot View"),
        VARIABLE_PIVOT_VIEW("Variable Pivot View"),
        DISABLE("Disable"),
        CIDT_INDICATOR("cidtIndicator"),
        SALES_INDICATOR("salesIndicator"),
        DISCOUNT_INDICATOR("discountIndicator"),
        PPA_INDICATOR("ppaIndicator"),
        DOLLAR("$"),
        PERCENT("%"),
        DASH("- - -"),
        SPRDASH("---"),
        LEVEL_NDC_8("NDC 8"),
        LEVEL_NDC_10("NDC 10"),
        CUSTOMER_HIERARCHY(CUSTOMER_SMALL),
        CUSTOM_HIERARCHY(CUSTOM_LABEL),
        LEVEL_NDC_11("NDC 11"),
        TAB_SALES_ALLOCATION("Sales Allocation"),
        THERAPEUTIC_CLASS("therapeuticClass"),
        SCREEN_NAME("screenName"),
        MANDATED_DISCOUNT("Mandated Discount"),
        SUPPLEMENTAL_DISCOUNT("Supplemental Discount"),
        RETURNS("Returns");
        private final String constant;

        private LabelConstants(String constant) {
            this.constant = constant;
        }

        public String getConstant() {
            return constant;
        }

        @Override
        public String toString() {
            return constant;
        }
    }
    public static final String DISCOUNT_PER_AMOUNT = "Discount %";
    public static final String DISCOUNT_DOLLAR_AMOUNT = "Discount $";
    public static final String CONTRACT_UNITS_LABEL = "Contract Units";
    public static final String PER_CHANGE = "% Change";
    public static final String VARIANCE1 = "Variance";
    public static final String VALUE1 = "Value";

    /**
     * Enum for common constants
     */
    public enum CommonConstants {

        SPACE(" "),
        COLON(":"),
        HTML_SPACE("&nbsp;"),
        SELECT_ONE("-Select One-"),
        SELECTMETHODOLOGY("- Select Methodology -"),
        REASON_CODE("Reason Code"),
        COMMENTARY("Commentary"),
        METH_COMM("Methodology Commentary"),
        ACSS_COMM("Access Commentary"),
        PARITY_COMM("Parity Commentary"),
        DATE_FORMAT(MM_DD_YYYY),
        FREQUENCY("frequency"),
        HISTORY("history"),
        ACTUALSORPROJECTIONS("projection"),
        ORDER("order"),
        VIEW("view"),
        //ommon variables for Projection Variance
        COL_VALUE(VALUE1),
        COL_PRIOR("Prior"),
        COL_VARIANCE(VARIANCE1),
        COL_PERCENTAGE(PER_CHANGE),
        VAR_GTS("Gross Trade Sales"),
        VAR_CONTRACT_SALES(CONTRACT_SALES_WAC_AT),
        VAR_CONTRACT_UNITS(CONTRACT_UNITS_LABEL),
        VAR_PERCENTAGE(PERCENTAGEOFBUSINESS),
        VAR_DIS_AMOUNT(DISCOUNT_DOLLAR_AMOUNT),
        VAR_DIS_RATE(DISCOUNT_PER_AMOUNT),
        VAR_NETSALES(NET_SALES1),
        NULL("null");
        private final String constant;

        private CommonConstants(String constant) {
            this.constant = constant;
        }

        public String getConstant() {
            return constant;
        }

        @Override
        public String toString() {
            return constant;
        }
    }

    /**
     * Enum for theme resources constants
     */
    public enum ResourceConstants {

        EXCEL_IMAGE_PATH("img/excel.png"),
        GRAPH_IMAGE_PATH("img/chart.png");
        private final String constant;

        private ResourceConstants(String constant) {
            this.constant = constant;
        }

        public String getConstant() {
            return constant;
        }

        @Override
        public String toString() {
            return constant;
        }
    }

    /**
     * Enum for calculating the periods
     */
    public enum CalculatePeriods implements CalendarInterface {

        CALCULATE {
            /**
             * Calculates the Annual value which is same as history year count
             *
             * @return the annual count
             */
            @Override
            public int getAnnualCount() {
                return CalendarConstants.HISTORY_YEAR_COUNT.getConstant();
            }

            /**
             * Calculates the semi-Annual value which is 2 * history year count
             *
             * @param historyYearCount historyYearCount value
             * @return the semi-annual count
             */
            @Override
            public int getSemiAnnualCount() {
                return (NumericConstants.TWO * CalendarConstants.HISTORY_YEAR_COUNT.getConstant()) + (CalendarConstants.CURRENT_MONTH.getConstant() / NumericConstants.SIX);
            }

            /**
             * Calculates the Quarterly value which is 4 * history year count
             *
             * @param historyYearCount historyYearCount value
             * @return the Quarterly count
             */
            @Override
            public int getQuarterCount() {
                return (NumericConstants.FOUR * CalendarConstants.HISTORY_YEAR_COUNT.getConstant()) + (CalendarConstants.CURRENT_MONTH.getConstant() / NumericConstants.THREE);
            }

            /**
             * Calculates the Monthly value which is 12 * history year count
             *
             * @param historyYearCount historyYearCount value
             * @return the Monthly count
             */
            @Override
            public int getMonthCount() {
                return (NumericConstants.TWELVE * CalendarConstants.HISTORY_YEAR_COUNT.getConstant()) + (CalendarConstants.CURRENT_MONTH.getConstant());
            }

            /**
             * Gives the current quarter of current month
             *
             * @return
             */
            @Override
            public int getCurrentQuarter() {
                return CalendarConstants.CURRENT_MONTH.getConstant() / NumericConstants.THREE + 1;
            }

            /**
             * Returns the current + projection count
             *
             * @return returns the total no. of projection periods
             */
            @Override
            public int getProjectionYear() {
                return CalendarConstants.CURRENT_YEAR.getConstant() + CalendarConstants.PROJECTION_YEAR_COUNT.getConstant();
            }
        };
    }

    /**
     * Interface for calculating the periods
     */
    private interface CalendarInterface {

        /**
         * Calculates the Annual value which is same as history year count
         *
         * @return the annual count
         */
        public int getAnnualCount();

        /**
         * Calculates the semi-Annual value which is 2 * history year count
         *
         * @return the semi-annual count
         */
        public int getSemiAnnualCount();

        /**
         * Calculates the Quarterly value which is 4 * history year count
         *
         * @return the Quarterly count
         */
        public int getQuarterCount();

        /**
         * Calculates the Monthly value which is 12 * history year count
         *
         * @return the Monthly count
         */
        public int getMonthCount();

        /**
         * Calculates the Current Quarter value which is current month / 3 + 1
         *
         * @return the Current Quarter
         */
        public int getCurrentQuarter();

        /**
         * Returns the current + projection count
         *
         * @return returns the total no. of projection periods
         */
        public int getProjectionYear();
    }

    /**
     * Enum for Calendar constants
     */
    public enum CalendarConstants {

        CURRENT_YEAR(Calendar.getInstance().get(Calendar.YEAR)),
        CURRENT_MONTH(Calendar.getInstance().get(Calendar.MONTH)),
        HISTORY_YEAR_COUNT(3),
        PROJECTION_YEAR_COUNT(3);
        private final int constant;

        private CalendarConstants(int constant) {
            this.constant = constant;
        }

        public int getConstant() {
            return constant;
        }

        @Override
        public String toString() {
            return String.valueOf(constant);
        }
    }

    /**
     * Enum for Session constants
     */
    public enum SessionConstants {

        ACTION_EDIT("EDIT"),
        ACTION_VIEW("VIEW"),
        ACTION_GENERATE("ADD");
        private final String constant;

        private SessionConstants(String constant) {
            this.constant = constant;
        }

        public String getConstant() {
            return constant;
        }

        @Override
        public String toString() {
            return constant;
        }
    }

    /**
     * Enum for ppa constants
     */
    public enum PPAVariables {

        CHECK_ALL(CHECK_ALL_CAPS),
        PRICE_PROTECTION_PRICE_TYPE("Price Protection Price Type"),
        NEP("NEP"),
        NEP_FORMULA("NEP Formula"),
        BASE_PRICE_TYPE("Base Price Type"),
        BASE_PRICE_MANUAL("Base Price (Manual)"),
        BASE_PRICE_DATE("Base Price (Date)"),
        BASE_PRICE_PRICE_TYPE("Base Price (Price Type)"),
        NET_BASE_PRICE("Net Base Price"),
        NET_BASE_PRICE_FORMULA("Net Base Price Formula"),
        SUBSEQUENT_PERIOD_PRICE_TYPE("Subsequent Period Price Type"),
        NET_SUBSEQUENT_PERIOD_PRICE("Net Subsequent Period Price"),
        NET_SUBSEQUENT_PERIOD_PRICE_FORMULA("Net Subsequent Period Price Formula"),
        PRICE_TOLERANCE_INTERVAL("Price Tolerance Interval"),
        PRICE_TOLERANCE_FREQUENCY("Price Tolerance Frequency"),
        PRICE_TOLERANCE_TYPE("Price Tolerance Type"),
        PRICE_TOLERANCE("Price Tolerance"),
        MAX_INCREMENTAL_CHANGE("Max Incremental Change"),
        RESET_ELIGIBLE("Reset Eligible"),
        RESET_TYPE("Reset Type"),
        RESET_DATE("Reset Date"),
        RESET_INTERVAL("Reset Interval"),
        RESET_FREQUENCY("Reset Frequency"),
        RESET_PRICE_TYPE("Reset Price Type"),
        NET_RESET_PRICE_TYPE("Net Reset Price Type"),
        NET_RESET_PRICE_FORMULA("Net Reset Price Formula"),
        NET_PRICE_TYPE("Net Price Type"),
        NET_PRICE_TYPE_FORMULA("Net Price Type Formula"),
        ATTACHED_DATE("Attached Date");
        private final String constant;

        private PPAVariables(String constant) {
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
            return Arrays.asList(PPAVariables.values()).toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY).split(", ");
        }

        public static String[] names() {
            return Arrays.toString(PPAVariables.values()).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY).split(", ");
        }

        public static String[] getCheckAllVariables() {
            return Arrays.toString(ArrayUtils.removeElement(PPAVariables.values(), CHECK_ALL)).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY).split(", ");
        }
    }

    public static Map<String, List<String>> getPopulateIdentifier() {
        String[] frozenValues = new String[]{Constant.GROUPFCAPS,
            Constant.PRICE_PROTECTION_STATUS,
            Constant.PRICE_PROTECTION_START_DATE,
            Constant.PRICE_PROTECTION_END_DATE};
        PPA_POPULATE_FIELD_FINDER.put(Constant.FROZEN_FIELDS, Arrays.asList(frozenValues));
        String[] ddlbValues = new String[]{Constant.PPAVariables.PRICE_PROTECTION_PRICE_TYPE.toString(),
            Constant.PPAVariables.BASE_PRICE_PRICE_TYPE.toString(),
            Constant.PPAVariables.RESET_PRICE_TYPE.toString(),
            Constant.PPAVariables.SUBSEQUENT_PERIOD_PRICE_TYPE.toString(),
            Constant.PPAVariables.PRICE_TOLERANCE_INTERVAL.toString(),
            Constant.PPAVariables.PRICE_TOLERANCE_FREQUENCY.toString(),
            Constant.PPAVariables.PRICE_TOLERANCE_TYPE.toString(),
            Constant.PPAVariables.RESET_TYPE.toString(),
            Constant.PPAVariables.RESET_FREQUENCY.toString(),
            Constant.PPAVariables.RESET_INTERVAL.toString(),
            Constant.PPAVariables.NET_BASE_PRICE.toString(),
            Constant.PPAVariables.NET_SUBSEQUENT_PERIOD_PRICE.toString(),
            Constant.PPAVariables.NET_PRICE_TYPE.toString(),
            Constant.PPAVariables.RESET_ELIGIBLE.toString(),
            Constant.PPAVariables.NET_RESET_PRICE_TYPE.toString(),
            Constant.PPAVariables.BASE_PRICE_TYPE.toString()};
        PPA_POPULATE_FIELD_FINDER.put(Constant.DDLB_FIELD, Arrays.asList(ddlbValues));
        String[] txtValues = new String[]{Constant.PPAVariables.NEP.toString(),
            Constant.PPAVariables.BASE_PRICE_MANUAL.toString(),
            Constant.PPAVariables.PRICE_TOLERANCE.toString(),
            Constant.PPAVariables.MAX_INCREMENTAL_CHANGE.toString()};
        PPA_POPULATE_FIELD_FINDER.put(Constant.TEXT_FIELD, Arrays.asList(txtValues));
        String[] lookUpValues = new String[]{Constant.PPAVariables.NEP_FORMULA.toString(),
            Constant.PPAVariables.NET_BASE_PRICE_FORMULA.toString(),
            Constant.PPAVariables.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.toString(),
            Constant.PPAVariables.NET_RESET_PRICE_FORMULA.toString(),
            Constant.PPAVariables.NET_PRICE_TYPE_FORMULA.toString()};
        PPA_POPULATE_FIELD_FINDER.put(Constant.LOOKUP_FIELD, Arrays.asList(lookUpValues));
        String[] dateValues = new String[]{Constant.PPAVariables.BASE_PRICE_DATE.toString(),
            Constant.PPAVariables.RESET_DATE.toString(),
            Constant.PPAVariables.ATTACHED_DATE.toString()};
        PPA_POPULATE_FIELD_FINDER.put(Constant.DATE_FEILD, Arrays.asList(dateValues));
        return PPA_POPULATE_FIELD_FINDER;
    }

    /**
     * Enum for ppa columns
     */
    public enum PPAColumns {

        PRICE_PROTECTION_PRICE_TYPE("priceProtectionPriceType"),
        NEP("nep"),
        NEP_FORMULA("nepFormula"),
        BASE_PRICE_TYPE("basePriceType"),
        BASE_PRICE_MANUAL("basePriceManual"),
        BASE_PRICE_DATE("basePriceDate"),
        BASE_PRICE_PRICE_TYPE("basePricePriceType"),
        NET_BASE_PRICE("netBasePrice"),
        NET_BASE_PRICE_FORMULA("netBasePriceFormula"),
        SUBSEQUENT_PERIOD_PRICE_TYPE("subsequentPeriodPriceType"),
        NET_SUBSEQUENT_PERIOD_PRICE("netSubsequentPeriodPrice"),
        NET_SUBSEQUENT_PERIOD_PRICE_FORMULA("netSubsequentPeriodPriceFormula"),
        PRICE_TOLERANCE_INTERVAL("priceToleranceInterval"),
        PRICE_TOLERANCE_FREQUENCY("priceToleranceFrequency"),
        PRICE_TOLERANCE_TYPE("priceToleranceType"),
        PRICE_TOLERANCE("priceTolerance"),
        MAX_INCREMENTAL_CHANGE("maxIncrementalChange"),
        RESET_ELIGIBLE("resetEligible"),
        RESET_TYPE("resetType"),
        RESET_DATE("resetDate"),
        RESET_INTERVAL("resetInterval"),
        RESET_FREQUENCY("resetFrequency"),
        RESET_PRICE_TYPE("resetPriceType"),
        NET_RESET_PRICE_TYPE("netResetPriceType"),
        NET_RESET_PRICE_FORMULA("netResetPriceFormula"),
        NET_PRICE_TYPE("netPriceType"),
        NET_PRICE_TYPE_FORMULA("netPriceTypeFormula"),
        ATTACHED_DATE("attachedDate");
        private final String constant;

        private PPAColumns(String constant) {
            this.constant = constant;
        }

        public String getConstant() {
            return constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(PPAColumns.values()).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY).split(", ");
        }
    }

    /**
     * Enum for common constants
     */
    public enum PVVariables {

        CHECK_ALL(CHECK_ALL_CAPS),
        EX_FACTORY_SALES(EX_FACTORY_SALES_LABEL),
        DEMAND_SALES(DEMAND_SALES1),
        INVENTORY_SALES("Inventory Withdrawal Sales"),
        PER_EX_FACTORY("Contract Sales % Of Ex-Factory"),
        PER_DEMAND("% Of Demand"),
        PER_INVENORY_WITHDRAW("% Of Inventory Withdrawal Sales"),
        VAR_CONTRACT_SALES("Contract Sales @ WAC"),
        VAR_CONTRACT_UNITS(CONTRACT_UNITS_LABEL),
        VAR_DIS_AMOUNT(DISCOUNT_DOLLAR_AMOUNT),
        VAR_DIS_RATE(DISCOUNT_PER_AMOUNT),
        VAR_RPU("RPU"),
        DISCOUNT_PER_EX_FACTORY("Discount % of Ex-Factory"),
        VAR_NETSALES(NET_SALES1),
        NET_SALES_PER_EX_FACTORY("Net Sales % of Ex-Factory"),
        NET_EX_FACTORY_SALES(NET_EXFACT_SALES),
        NET_EX_FACTORY_SALES_PER_EX_FACTORY(NET_EXFACT_SALES_PER_EXFACT),
        VAR_COGS("COGS"),
        VAR_NET_PROFITE("Net Profit");

        private final String constant;

        private PVVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(PVVariables.values()).replaceAll(OR_DOLLAR, StringUtils.EMPTY).split(",");
        }

        public static String[] getCheckAllVariables() {
            return Arrays.toString(ArrayUtils.removeElement(PVVariables.values(), CHECK_ALL)).replaceAll(OR_DOLLAR, StringUtils.EMPTY).split(",");
        }
    }
    public static final String NET_SALES1 = "Net Sales";

    
    public enum PVVariableCategory {

        COL_VALUE(VALUE1),
        COL_VARIANCE(VARIANCE1),
        COL_PERCENTAGE(PER_CHANGE),
        COL_ACTUALS(ACTUALS),
        COL_ACCRUALS(ACCRUALS);
        private final String constant;

        private PVVariableCategory(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            String[] temp = Arrays.toString(PVVariableCategory.values()).replaceAll(OR_DOLLAR, StringUtils.EMPTY).split(",");
            List tempList = new ArrayList(Arrays.asList(temp));
            if (!CommonUtil.isValueEligibleForLoading()) {
                tempList.remove(" "+ACCRUALS);
            }
            return Arrays.copyOf(tempList.toArray(), tempList.toArray().length,String[].class);
        }
    }

    public enum DPRVariables {

        CHECK_ALL(CHECK_ALL_CAPS),
        DISCOUNT_RATE(DISCOUNT_RATE_LABEL),
        REBATE_PER_UNIT("Rebate Per Unit"),
        DISCOUNT_AMT(DISCOUNT_AMOUNT_LABEL),
        PER_EX_FACTORY(PERCOFEXFACTORY);

        private final String constant;

        private DPRVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(DPRVariables.values()).replaceAll(OR_DOLLAR, StringUtils.EMPTY).split(",");
        }

        public static String[] getCheckAllVariables() {
            return Arrays.toString(ArrayUtils.removeElement(DPRVariables.values(), CHECK_ALL)).replaceAll(OR_DOLLAR, StringUtils.EMPTY).split(",");
        }
    }

    public static Map<String, String> getDatabaseColumnIdentifier() {
        if (PPA_DATABASE_COLUMN_IDENTIFIER.isEmpty()) {
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.GROUPFCAPS, "USER_GROUP");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PRICE_PROTECTION_STATUS, "PRICE_PROTECTION_STATUS");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PRICE_PROTECTION_START_DATE, "PRICE_PROTECTION_START_DATE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PRICE_PROTECTION_END_DATE, "PRICE_PROTECTION_END_DATE");

            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.PRICE_PROTECTION_PRICE_TYPE.toString(), "ITEM_PRICING_QUALIFIER_SID");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.NEP.toString(), "NEP");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.NEP_FORMULA.toString(), "NEP_FORMULA");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.BASE_PRICE_TYPE.toString(), "BASE_PRICE_TYPE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.BASE_PRICE_MANUAL.toString(), "BASE_PRICE_MANUAL");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.BASE_PRICE_DATE.toString(), "BASE_PRICE_DATE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.BASE_PRICE_PRICE_TYPE.toString(), "BASE_PRICE_PRICE_TYPE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.NET_BASE_PRICE.toString(), "NET_BASE_PRICE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.NET_BASE_PRICE_FORMULA.toString(), "NET_BASE_PRICE_FORMULA");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.SUBSEQUENT_PERIOD_PRICE_TYPE.toString(), "SUBSEQUENT_PERIOD_PRICE_TYPE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.NET_SUBSEQUENT_PERIOD_PRICE.toString(), "NET_SUBSEQUENT_PERIOD_PRICE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.toString(), "NET_SUBSEQUENT_PERIOD_PRICE_FORMULA");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.PRICE_TOLERANCE_INTERVAL.toString(), "PRICE_TOLERANCE_INTERVAL");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.PRICE_TOLERANCE_FREQUENCY.toString(), "PRICE_TOLERANCE_FREQUENCY");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.PRICE_TOLERANCE_TYPE.toString(), "PRICE_TOLERANCE_TYPE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.PRICE_TOLERANCE.toString(), "PRICE_TOLERANCE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.MAX_INCREMENTAL_CHANGE.toString(), "MAX_INCREMENTAL_CHANGE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.RESET_ELIGIBLE.toString(), "RESET_ELIGIBLE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.RESET_TYPE.toString(), "RESET_TYPE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.RESET_DATE.toString(), "RESET_DATE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.RESET_INTERVAL.toString(), "RESET_INTERVAL");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.RESET_FREQUENCY.toString(), "RESET_FREQUENCY");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.RESET_PRICE_TYPE.toString(), "RESET_PRICE_TYPE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.NET_RESET_PRICE_TYPE.toString(), "NET_RESET_PRICE_TYPE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.NET_RESET_PRICE_FORMULA.toString(), "NET_RESET_PRICE_FORMULA");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.NET_PRICE_TYPE.toString(), "NET_PRICE_TYPE");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.NET_PRICE_TYPE_FORMULA.toString(), "NET_PRICE_TYPE_FORMULA");
            PPA_DATABASE_COLUMN_IDENTIFIER.put(Constant.PPAVariables.ATTACHED_DATE.toString(), "ATTACHED_DATE");
        }
        return PPA_DATABASE_COLUMN_IDENTIFIER;
    }

    public static Map<String, String> getColumnHeaderMap() {
        if (PPA_COLUMN_HEADER_FINDER.isEmpty()) {
            PPA_COLUMN_HEADER_FINDER.put(Constant.GROUP, Constant.GROUPFCAPS);
            PPA_COLUMN_HEADER_FINDER.put(Constant.PRICEPROTECTIONSTATUS, Constant.PRICE_PROTECTION_STATUS);
            PPA_COLUMN_HEADER_FINDER.put(Constant.PRICEPROTECTIONSTATUS1, Constant.PRICE_PROTECTION_STATUS1);
            PPA_COLUMN_HEADER_FINDER.put(Constant.PRICEPROTECTIONSTARTDATE, Constant.PRICE_PROTECTION_START_DATE);
            PPA_COLUMN_HEADER_FINDER.put(Constant.PRICEPROTECTIONENDDATE, Constant.PRICE_PROTECTION_END_DATE);

            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.PRICE_PROTECTION_PRICE_TYPE.getConstant(), PPAVariables.PRICE_PROTECTION_PRICE_TYPE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.NEP.getConstant(), PPAVariables.NEP.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.NEP_FORMULA.getConstant(), PPAVariables.NEP_FORMULA.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.BASE_PRICE_TYPE.getConstant(), PPAVariables.BASE_PRICE_TYPE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.BASE_PRICE_MANUAL.getConstant(), PPAVariables.BASE_PRICE_MANUAL.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.BASE_PRICE_DATE.getConstant(), PPAVariables.BASE_PRICE_DATE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.BASE_PRICE_PRICE_TYPE.getConstant(), PPAVariables.BASE_PRICE_PRICE_TYPE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.NET_BASE_PRICE.getConstant(), PPAVariables.NET_BASE_PRICE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.NET_BASE_PRICE_FORMULA.getConstant(), PPAVariables.NET_BASE_PRICE_FORMULA.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.SUBSEQUENT_PERIOD_PRICE_TYPE.getConstant(), PPAVariables.SUBSEQUENT_PERIOD_PRICE_TYPE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE.getConstant(), PPAVariables.NET_SUBSEQUENT_PERIOD_PRICE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.getConstant(), PPAVariables.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.PRICE_TOLERANCE_INTERVAL.getConstant(), PPAVariables.PRICE_TOLERANCE_INTERVAL.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.PRICE_TOLERANCE_FREQUENCY.getConstant(), PPAVariables.PRICE_TOLERANCE_FREQUENCY.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.PRICE_TOLERANCE_TYPE.getConstant(), PPAVariables.PRICE_TOLERANCE_TYPE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.PRICE_TOLERANCE.getConstant(), PPAVariables.PRICE_TOLERANCE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.MAX_INCREMENTAL_CHANGE.getConstant(), PPAVariables.MAX_INCREMENTAL_CHANGE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.RESET_ELIGIBLE.getConstant(), PPAVariables.RESET_ELIGIBLE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.RESET_TYPE.getConstant(), PPAVariables.RESET_TYPE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.RESET_DATE.getConstant(), PPAVariables.RESET_DATE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.RESET_INTERVAL.getConstant(), PPAVariables.RESET_INTERVAL.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.RESET_FREQUENCY.getConstant(), PPAVariables.RESET_FREQUENCY.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.RESET_PRICE_TYPE.getConstant(), PPAVariables.RESET_PRICE_TYPE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.NET_RESET_PRICE_TYPE.getConstant(), PPAVariables.NET_RESET_PRICE_TYPE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.NET_RESET_PRICE_FORMULA.getConstant(), PPAVariables.NET_RESET_PRICE_FORMULA.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.NET_PRICE_TYPE.getConstant(), PPAVariables.NET_PRICE_TYPE.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.NET_PRICE_TYPE_FORMULA.getConstant(), PPAVariables.NET_PRICE_TYPE_FORMULA.getConstant());
            PPA_COLUMN_HEADER_FINDER.put(PPAColumns.ATTACHED_DATE.getConstant(), PPAVariables.ATTACHED_DATE.getConstant());

        }
        return PPA_COLUMN_HEADER_FINDER;
    }

    public static Map<String, String> getColumnVariableFinderMap() {
        if (PPA_COLUMN_VARIABLE_FINDER.isEmpty()) {
            PPA_COLUMN_VARIABLE_FINDER.put(Constant.GROUPFCAPS, Constant.GROUP);
            PPA_COLUMN_VARIABLE_FINDER.put(Constant.PRICE_PROTECTION_STATUS, Constant.PRICEPROTECTIONSTATUS);
            PPA_COLUMN_VARIABLE_FINDER.put(Constant.PRICE_PROTECTION_STATUS1, Constant.PRICEPROTECTIONSTATUS1);
            PPA_COLUMN_VARIABLE_FINDER.put(Constant.PRICE_PROTECTION_START_DATE, Constant.PRICEPROTECTIONSTARTDATE);
            PPA_COLUMN_VARIABLE_FINDER.put(Constant.PRICE_PROTECTION_END_DATE, Constant.PRICEPROTECTIONENDDATE);
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.PRICE_PROTECTION_PRICE_TYPE.getConstant(), PPAColumns.PRICE_PROTECTION_PRICE_TYPE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.NEP.getConstant(), PPAColumns.NEP.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.NEP_FORMULA.getConstant(), PPAColumns.NEP_FORMULA.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.BASE_PRICE_TYPE.getConstant(), PPAColumns.BASE_PRICE_TYPE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.BASE_PRICE_MANUAL.getConstant(), PPAColumns.BASE_PRICE_MANUAL.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.BASE_PRICE_DATE.getConstant(), PPAColumns.BASE_PRICE_DATE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.BASE_PRICE_PRICE_TYPE.getConstant(), PPAColumns.BASE_PRICE_PRICE_TYPE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.NET_BASE_PRICE.getConstant(), PPAColumns.NET_BASE_PRICE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.NET_BASE_PRICE_FORMULA.getConstant(), PPAColumns.NET_BASE_PRICE_FORMULA.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.SUBSEQUENT_PERIOD_PRICE_TYPE.getConstant(), PPAColumns.SUBSEQUENT_PERIOD_PRICE_TYPE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.NET_SUBSEQUENT_PERIOD_PRICE.getConstant(), PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.getConstant(), PPAColumns.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.PRICE_TOLERANCE_INTERVAL.getConstant(), PPAColumns.PRICE_TOLERANCE_INTERVAL.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.PRICE_TOLERANCE_FREQUENCY.getConstant(), PPAColumns.PRICE_TOLERANCE_FREQUENCY.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.PRICE_TOLERANCE_TYPE.getConstant(), PPAColumns.PRICE_TOLERANCE_TYPE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.PRICE_TOLERANCE.getConstant(), PPAColumns.PRICE_TOLERANCE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.MAX_INCREMENTAL_CHANGE.getConstant(), PPAColumns.MAX_INCREMENTAL_CHANGE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.RESET_ELIGIBLE.getConstant(), PPAColumns.RESET_ELIGIBLE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.RESET_TYPE.getConstant(), PPAColumns.RESET_TYPE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.RESET_DATE.getConstant(), PPAColumns.RESET_DATE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.RESET_INTERVAL.getConstant(), PPAColumns.RESET_INTERVAL.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.RESET_FREQUENCY.getConstant(), PPAColumns.RESET_FREQUENCY.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.RESET_PRICE_TYPE.getConstant(), PPAColumns.RESET_PRICE_TYPE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.NET_RESET_PRICE_TYPE.getConstant(), PPAColumns.NET_RESET_PRICE_TYPE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.NET_RESET_PRICE_FORMULA.getConstant(), PPAColumns.NET_RESET_PRICE_FORMULA.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.NET_PRICE_TYPE.getConstant(), PPAColumns.NET_PRICE_TYPE.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.NET_PRICE_TYPE_FORMULA.getConstant(), PPAColumns.NET_PRICE_TYPE_FORMULA.getConstant());
            PPA_COLUMN_VARIABLE_FINDER.put(PPAVariables.ATTACHED_DATE.getConstant(), PPAColumns.ATTACHED_DATE.getConstant());

        }
        return PPA_COLUMN_VARIABLE_FINDER;
    }

    public static Map<String, String> getHelperTableIdentifier() {
        if (PPA_HELPER_TABLE_IDENTIFRIER.isEmpty()) {
            PPA_HELPER_TABLE_IDENTIFRIER.put(Constant.PPAVariables.RESET_INTERVAL.toString(), HelperListUtil.PRICE_TOLERANCE_INTERVAL);
            PPA_HELPER_TABLE_IDENTIFRIER.put(Constant.PPAVariables.RESET_FREQUENCY.toString(), HelperListUtil.PRICE_TOLERANCE_FRERQUENCY);
            PPA_HELPER_TABLE_IDENTIFRIER.put(Constant.PPAVariables.PRICE_TOLERANCE_INTERVAL.toString(), HelperListUtil.PRICE_TOLERANCE_INTERVAL);
            PPA_HELPER_TABLE_IDENTIFRIER.put(Constant.PPAVariables.PRICE_TOLERANCE_FREQUENCY.toString(), HelperListUtil.PRICE_TOLERANCE_FRERQUENCY);
            PPA_HELPER_TABLE_IDENTIFRIER.put(Constant.PPAVariables.PRICE_TOLERANCE_TYPE.toString(), HelperListUtil.PRICE_TOLERANCE_TYPE);
            PPA_HELPER_TABLE_IDENTIFRIER.put(Constant.PPAVariables.RESET_TYPE.toString(), HelperListUtil.RESET_TYPE);
            PPA_HELPER_TABLE_IDENTIFRIER.put(Constant.PPAVariables.NET_BASE_PRICE.toString(), HelperListUtil.LOCKED_STATUS);
            PPA_HELPER_TABLE_IDENTIFRIER.put(Constant.PPAVariables.NET_SUBSEQUENT_PERIOD_PRICE.toString(), HelperListUtil.LOCKED_STATUS);
            PPA_HELPER_TABLE_IDENTIFRIER.put(Constant.PPAVariables.NET_PRICE_TYPE.toString(), HelperListUtil.LOCKED_STATUS);
            PPA_HELPER_TABLE_IDENTIFRIER.put(Constant.PPAVariables.RESET_ELIGIBLE.toString(), HelperListUtil.LOCKED_STATUS);
            PPA_HELPER_TABLE_IDENTIFRIER.put(Constant.PPAVariables.NET_RESET_PRICE_TYPE.toString(), HelperListUtil.LOCKED_STATUS);
            PPA_HELPER_TABLE_IDENTIFRIER.put(Constant.PPAVariables.BASE_PRICE_TYPE.toString(), HelperListUtil.BASE_PRICE_TYPE);
        }
        return PPA_HELPER_TABLE_IDENTIFRIER;
    }
        }
