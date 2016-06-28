/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.utils;

// TODO: Auto-generated Javadoc
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * The Class Constant.
 *
 * @author lokeshwari
 */
public class Constant {

    public final static String EDIT = "Edit";
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
     * The to.
     */
    public static final String TO_VAL = "toDate";
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
    public static final String DATE = "MM/dd/yyyy";
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
    public static final String PRODUCT = "Product";
    public static final String CUSTOM = "Custom";
    public static final String HORIZONTAL = "horizontal";
    public static final String PPA_PROJECTION_RESULT = "PPA Projection Results";
    public static final String PROJECTION_RESULTS = "Projection Results";
    public static final String SALES_PROJECTION = "Sales Projection";
    public static final String TRADING_PARTNER = "Trading Partner";
    public static final String CHECK_RECORD = "CheckRecord";
    public static final String $ = "$";
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
    public static final String _FLAG = "_FLAG";
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
    public static final String POPUPCONTENTCOMBOSIZE = "popupContentComboSize";
    public static final String OPTION_GROUP_WIDTH = "optiongroupwidth";
    public static final String ADD_FULL_SMALL = "add";
    public static final String CENTER_CHECK = "center-check";
    public static final String LEVEL = "Level ";
    public static final String MM = "MM";
    public static final String Managed_Medicaid = "Managed Medicaid";
    public static final String TOTAL = "Total";
    public static final String DETAIL = "Detail";
    public static final String UNIT = "Unit";
    public static final String All = "All";
    public static final String TXT_RIGHT_ALIGN = "txtRightAlign";
    public static final String ALIGN_RIGHT = "align-right";
    public static final String P_Growth = "pGrowth";
    public static final String A_Growth = "aGrowth";
    public static final String TRADINGPARTNER = "TRADING PARTNER";
    public static final String METHODOLOGY = "methodology";
    public static final String HISTORY_CAPS = "History";
    public static final String ACTUALSALES = "ActualSales";
    public static final String ACTUALUNITS = "ActualUnits";
    public static final String BASELINE = "baseline";
    public static final String ActualRPU = "ActualRPU";
    public static final String FREQUENCY_SMALL = "Frequency";
    public static final String PERIOD_ORDER = "Period Order";
    public static final String VARIABLES = "Variables";
    public static final String LEVELNAME = "levelName";
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
    public static final String INDICATOR_LOGIC_CUSTOMER_HIERARCHY = "C";
    public static final String INDICATOR_LOGIC_PRODUCT_HIERARCHY = "P";
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
    public static final String HIERARCHYDEFINITIONSID = "hierarchyDefinitionSid";
    public static final String UPDATE = "update";
    public static final String HELPER_TABLE_SID = "helperTableSid";
    public static final String LIST_NAME = "listName";
    public static final String COMPANYMASTERSID = "companyMasterSid";
    public static final String VERSION_NO = "versionNo";
    public static final String HIERARACHY_NO = "hierarchyNo";
    public static final String RS_TYPE = "RS_TYPE";
    public static final String GROWTH = "Growth";
    public static final String ProjectedRPU = "ProjectedRPU";
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
    public static final String FSS = "FSS";
    public static final String CPI_URA = "CPI-URA";
    public static final String FCP = "FCP";
    public static final String AMP = "AMP";
    public static final String BEST_PRICE = "BEST PRICE";
    public static final String CPIURA = "CPI URA";
    public static final String TOTAL_URA = "TOTAL URA";
    public static final String Best_Price = "Best Price";
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
    public static final String totDisDol = "totDisDol";
    public static final String totalRPU = "totalRPU";
    public static final String totDisPer = "totDisPer";
    public static final String Total_RPU = "Total RPU";
    public static final String Sales = "Sales-";
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
    /**
     * The UNITS_CAPS.
     */
    public static final String UNITS_CAPS = "Units";
    public static final String PROJECTED_UNITS = "Projected Units";
    public static final String ACTUAL_UNITS = "Actual Units";
    public static final String AND = "AND";
    public static final String ITEM="Item";
   public static final String PERC_OF_ADJUSTED_DEMAND = "% of Adjusted Demand";


    /**
     * Enum for Frequency constants
     */
    public enum FrequencyConstants {

        ANNUAL("Annual"),
        ANNUALLY("Annually"),
        YEAR("Year"),
        YEARS("Years"),
        SEMI_ANNUAL("Semi-Annual"),
        SEMI_ANNUALLY("Semi-Annually"),
        QUARTERLY("Quarterly"),
        MONTHLY("Monthly"),
        MONTHS("Months"),
        QUARTERS("Quarters");
        private String frequencyValue;

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
    };
    public static String COUNTER_VALUE = "counterValue";
    public static final String RESETCOUNT = "ResetCount";
    public static final String PORTLET_NAME = "PortelName";
    public static final String NUM_VALIDATION = "([0-9])*";
    public static final String NUM_WIHT2DECIMAL = "[-+]?(?:\\d+|\\d*(?:\\.\\d{1,2})?)";
    public static final DecimalFormat TWO_DECIMAL = new DecimalFormat("#,##0.00");
    public static final String CURRENCY = "$";
    public static final DecimalFormat UNIT_FORMAT = new DecimalFormat("#,##0");
    public static final String HIST_PER_OF_BUISNESS = "Historical % of Business";
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
        private String constant;

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

        PRODUCT_HIERARCHY("Product"),
        METHODOLOGY_MOVING_AVERAGE("Moving Average"),
        LEVEL_BRAND("Brand"),
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
        DISCOUNT_AMT("Discount Amount"),
        GROWTH("Growth"),
        DISCOUNT_RATE("Discount Rate"),
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
        PROJECTEDRATE("ProjectionsRate"),
        CFF_PROJECTEDRATE("ProjectionsRate"),
        ACTUALAMOUNT("ActualsAmount"),
        PROJECTEDAMOUNT("ProjectionsAmount"),
        ACTUAL_AMOUNT("Actual Amount"),
        PROJECTED_AMOUNT("Projected Amount"),
        BOTH("Both"),
        HIERARCHY("Hierarchy"),
        LEVEL("Level"),
        MASS_FIELD_CS("Contract Sales"),
        MASS_FIELD_POB("% of Business"),
        CUSTOMER_GROUP_NAME("Customer Group Name"),
        CUSTOMER_GROUP_NO("Customer Group No"),
        PRODUCT_GROUP_NAME("Product Group Name"),
        PRODUCT_GROUP_NO("Product Group No"),
        ASCENDING("Ascending"),
        DESCENDING("Descending"),
        PROGRAM("Program"),
        PROGRAM_CATEGORY("Program Category"),
        BRAND_TYPE("Brand Type"),
        BRAND("Brand"),
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
        TAB_SALES_PROJECTION("Sales Projection"),
        TAB_SALES_PROJECTION_RESULTS("Sales Projection Results"),
        TAB_DISCOUNT_PROJECTION("Discount Projection"),
        TAB_DISCOUNT_PROJECTION_RESULTS("Discount Projection Results"),
        TAB_PPA_PROJECTION("PPA Projection"),
        TAB_PPA_PROJECTION_RESULTS("PPA Projection Results"),
        TAB_PROJECTION_RESULTS("Projection Results"),
        TAB_PROJECTION_VARIANCE("Projection Variance"),
        TAB_ASSUMPTIONS("Assumptions"),
        TAB_ADDITIONAL_INFORMATION("Additional Information"),
        PERIOD("Period"),
        DISCOUNT("Discount"),
        TREE_STRUCTURE("Tree Structure"),
        TREE_VIEW_NAME("Tree View Name"),
        SALES("Sales"),
        UNITS("Units"),
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
        CUSTOMER("Customer"),
        PRODUCT("Product"),
        CUSTOM("Custom"),
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
        VALUE("Value"),
        VARIANCE("Variance"),
        PERC_CHANGE("% Change"),
        GROSS_TRADE_SALES("Gross Trade Sales"),
        EX_FACTORY_SALES("Ex-Factory Sales"),
        DEMAND_SALES("Demand Sales"),
        INVENTORY_WITHDRAW("Inventory Withdrawal Sales"),
        PERC_OF_BUSSINESS("% of Business"),
        PERC_OF_EX_FACTORY("% of Ex-Factory"),
        PERC_OF_DEMAND("% of Demand"),
        PERC_OF_INVENTORY_WITHDRAW("% of Inventory Withdrawal"),
        CONTRACT_SALES_AT_WAC("Contract Sales @ WAC"),
        TOTAL_RPU("Total RPU"),
        COGS("Cost of Goods Sold (COGS)"),
        NET_PROFIT("Net Profit"),
        UNIT_VOL("Unit Volume"),
        CONTRACT_UNIT("Contract Units"),
        DISCOUNT_AMOUNT("Discount $"),
        DISCOUNT_PERC("Discount %"),
        TOTAL_DISCOUNT_AMOUNT("Total Discount $"),
        TOTAL_DISCOUNT_PERC("Total Discount %"),
        NET_SALES("Net Sales"),
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
        CUSTOMER_HIERARCHY("Customer"),
        CUSTOM_HIERARCHY("Custom"),
        LEVEL_NDC_11("NDC 11"),
        TAB_SALES_ALLOCATION("Sales Allocation"),
        THERAPEUTIC_CLASS("therapeuticClass"),
        SCREEN_NAME("screenName"),
        Mandated_Discount("Mandated Discount"),
        Supplemental_Discount("Supplemental Discount"),
        RETURNS("Returns");
        private String constant;

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
        DATE_FORMAT("MM/dd/yyyy"),
        FREQUENCY("frequency"),
        HISTORY("history"),
        ACTUALSORPROJECTIONS("projection"),
        ORDER("order"),
        VIEW("view"),
        //ommon variables for Projection Variance
        COL_VALUE("Value"),
        COL_PRIOR("Prior"),
        COL_VARIANCE("Variance"),
        COL_PERCENTAGE("% Change"),
        VAR_GTS("Gross Trade Sales"),
        VAR_CONTRACT_SALES("Contract Sales @ WAC"),
        VAR_CONTRACT_UNITS("Contract Units"),
        VAR_PERCENTAGE("% of Business"),
        VAR_DIS_AMOUNT("Discount $"),
        VAR_DIS_RATE("Discount %"),
        VAR_NETSALES("Net Sales"),
        NULL("null");
        private String constant;

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

        EXCEL_IMAGE_PATH("../../icons/excel.png"),
        GRAPH_IMAGE_PATH("../../icons/chart.png");
        private String constant;

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
            public int getAnnualCount() {
                return CalendarConstants.HISTORY_YEAR_COUNT.getConstant();
            }

            /**
             * Calculates the semi-Annual value which is 2 * history year count
             *
             * @param historyYearCount historyYearCount value
             * @return the semi-annual count
             */
            public int getSemiAnnualCount() {
                return ((2 * CalendarConstants.HISTORY_YEAR_COUNT.getConstant()) + (CalendarConstants.CURRENT_MONTH.getConstant() / 6));
            }

            /**
             * Calculates the Quarterly value which is 4 * history year count
             *
             * @param historyYearCount historyYearCount value
             * @return the Quarterly count
             */
            public int getQuarterCount() {
                return ((4 * CalendarConstants.HISTORY_YEAR_COUNT.getConstant()) + (CalendarConstants.CURRENT_MONTH.getConstant() / 3));
            }

            /**
             * Calculates the Monthly value which is 12 * history year count
             *
             * @param historyYearCount historyYearCount value
             * @return the Monthly count
             */
            public int getMonthCount() {
                return ((12 * CalendarConstants.HISTORY_YEAR_COUNT.getConstant()) + (CalendarConstants.CURRENT_MONTH.getConstant()));
            }

            /**
             * Gives the current quarter of current month
             *
             * @return
             */
            public int getCurrentQuarter() {
                return (CalendarConstants.CURRENT_MONTH.getConstant() / 3 + 1);
            }

            /**
             * Returns the current + projection count
             *
             * @return returns the total no. of projection periods
             */
            public int getProjectionYear() {
                return (CalendarConstants.CURRENT_YEAR.getConstant() + CalendarConstants.PROJECTION_YEAR_COUNT.getConstant());
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
        private int constant;

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
        private String constant;

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

        CHECK_ALL("Check All"),
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
        private String constant;

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
        private String constant;

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

        CHECK_ALL("Check All"),
        EX_FACTORY_SALES("Ex-Factory Sales"),
        DEMAND_SALES("Demand Sales"),
        INVENTORY_SALES("Inventory Withdrawal Sales"),
        PER_EX_FACTORY("% Of Ex-Factory"),
        PER_DEMAND("% Of Demand"),
        PER_INVENORY_WITHDRAW("% Of Inventory Withdrawal Sales"),
        VAR_CONTRACT_SALES("Contract Sales @ WAC"),
        VAR_CONTRACT_UNITS("Contract Units"),
        VAR_DIS_AMOUNT("Discount $"),
        VAR_DIS_RATE("Discount %"),
        VAR_RPU("RPU"),
        VAR_NETSALES("Net Sales"),
        VAR_COGS("COGS"),
        VAR_NET_PROFITE("Net Profit");
        private String constant;

        private PVVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(PVVariables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

        public static String[] getCheckAllVariables() {
            return Arrays.toString(ArrayUtils.removeElement(PVVariables.values(), CHECK_ALL)).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
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
