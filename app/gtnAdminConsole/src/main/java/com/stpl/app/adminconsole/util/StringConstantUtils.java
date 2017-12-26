/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import org.jboss.logging.Logger;

public class StringConstantUtils {
    
    public static final String CUSTOMER_START_DATE = "customerStartDate";
    public static final String PARENT_END_DATE = "parentEndDate";
    public static final String CUSTOMER_END_DATE = "customerEndDate";
    public static final String PARENT_START_DATE = "parentStartDate";
    public static final String PRIOR_PARENT_START_DATE = "priorParentStartDate";
    public static final String TRADE_CLASS_START_DATE = "tradeClassStartDate";
    public static final String TRADE_CLASS_END_DATE = "tradeClassEndDate";
    public static final String FUNCTIONAL_SCREEN = "Functional Screen";
    public static final String HAS_BEEN_SUCCESSFULLY_SAVED = " has been successfully saved";
    public static final String MYSTYLE = "mystyle";
    public static final String FUNCTIONAL_LIST_VIEW = "Functional List view";
    public static final String UNITS = "units";
    public static final String ITEM_NAME = "itemName";
    public static final String MARKET_SHARE_RATIO = "marketShareRatio";
    public static final String GROSS_UNITS = "grossUnits";
    public static final String INVENTORY_UNIT_CHANGE = "inventoryUnitChange";
    public static final String GROSS_PRICE = "grossPrice";
    public static final String TOTAL_DEMAND_UNITS = "totalDemandUnits";
    public static final String MARKET_SHARE_UNITS = "marketShareUnits";
    public static final String UNCAPTURED_UNITS = "uncapturedUnits";
    public static final String NET_SALES_PRICE = "netSalesPrice";
    public static final String UNCAPTURED_UNITS_RATIO = "uncapturedUnitsRatio";
    public static final String GROSS_AMOUNT = "grossAmount";
    public static final String BATCH_ID = "batchId";
    public static final String SEGMENT = "segment";
    public static final String ITEM_ID = "itemId";
    public static final String FORECAST_MONTH = "forecastMonth";
    public static final String ORGANIZATION_KEY = "organizationKey";
    public static final String TOTAL_DEMAND_AMOUNT = "totalDemandAmount";
    public static final String FORCAST_YEAR = "forcastYear";
    public static final String ITEM_IDENTIFIER_CODE_QUALIFIER = "itemIdentifierCodeQualifier";
    public static final String BRAND_ID = "brandId";
    public static final String MARKET_SIZE_UNITS = "marketSizeUnits";
    public static final String ITEM_IDENTIFIER = "itemIdentifier";
    public static final String NET_SALES_AMOUNT = "netSalesAmount";
    public static final String DFGROSS_PRICE = "DF.GROSS_PRICE";
    public static final String DFSEGMENT = "DF.SEGMENT";
    public static final String DFGROSS_AMOUNT = "DF.GROSS_AMOUNT";
    public static final String ORDER_BY_DFFORECAST_YEAR = " ORDER BY DF.FORECAST_YEAR  ";
    public static final String DFMARKET_SIZE_UNITS = "DF.MARKET_SIZE_UNITS";
    public static final String DFUNCAPTURED_UNITS_RATIO = "DF.UNCAPTURED_UNITS_RATIO";
    public static final String DFTOTAL_DEMAND_AMOUNT = "DF.TOTAL_DEMAND_AMOUNT";
    public static final String AND_DFRECORD_LOCK_STATUS = " AND DF.RECORD_LOCK_STATUS=0";
    public static final String AND_DFCOUNTRY = " AND DF.COUNTRY='";
    public static final String DFNET_SALES_AMOUNT = "DF.NET_SALES_AMOUNT";
    public static final String DFMARKET_SHARE_RATIO = "DF.MARKET_SHARE_RATIO";
    public static final String DFNET_SALES_PRICE = "DF.NET_SALES_PRICE";
    public static final String DFGROSS_UNITS = "DF.GROSS_UNITS";
    public static final String DFFORECAST_YEAR = "DF.FORECAST_YEAR";
    public static final String DFFORECAST_MONTH = "DF.FORECAST_MONTH";
    public static final String DFFORECAST_VER = " DF.FORECAST_VER='";
    public static final String DFUNCAPTURED_UNITS = "DF.UNCAPTURED_UNITS";
    public static final String DFINVENTORY_UNIT_CHANGE = "DF.INVENTORY_UNIT_CHANGE";
    public static final String DFTOTAL_DEMAND_UNITS = "DF.TOTAL_DEMAND_UNITS";
    public static final String DFMARKET_SHARE_UNITS = "DF.MARKET_SHARE_UNITS";
    public static final String DFFORECAST_VER_IN = " DF.FORECAST_VER in ('";
    public static final String DFBRAND_ID = "DF.BRAND_ID";
    public static final String DFORGANIZATION_KEY = "DF.ORGANIZATION_KEY";
    public static final String DFITEM_ID = "DF.ITEM_ID";
    public static final String DFBATCH_ID = "DF.BATCH_ID";
    public static final String AND_INWCOUNTRY_LIKE = " AND INW.COUNTRY LIKE'";
    public static final String AMOUNT_WITHDRAWN = "amountWithdrawn";
    public static final String ITEM_IDENTIFIER_LIST = "ITEM_IDENTIFIER";
    public static final String BATCH_ID_LIST = "BATCH_ID";
    public static final String AMOUNT_WITHDRAWN_LIST = "AMOUNT_WITHDRAWN";
    public static final String UNITS_WITHDRAWN = "unitsWithdrawn";
    public static final String INWFORECAST_VER = " INW.FORECAST_VER='";
    public static final String ORGANIZATION_KEY_LIST = "ORGANIZATION_KEY";
    public static final String ITEM_IDENTIFIER_CODE_QUALIFIER_LIST = "ITEM_IDENTIFIER_CODE_QUALIFIER";
    public static final String MONTH_COLUMN = "MONTH";
    public static final String ITEM_ID_LIST = "ITEM_ID";
    public static final String UNITS_WITHDRAWN_LIST = "UNITS_WITHDRAWN";
    public static final String OFFSET_SPACE = "OFFSET ";
    public static final String ITEM_NO_PROPERTY = "itemNo";
    public static final String ROWS_ONLY = " ROWS ONLY;";
    public static final String START_DATE = "startDate";
    public static final String ITEM_NAME_LIST = "ITEM_NAME";
    public static final String MONTH_PROPERTY = "month";
    public static final String ROWS_FETCH_NEXT = " ROWS FETCH NEXT ";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String PRICE_PROPERTY = "price";
    public static final String TOTAL_DEMAND_AMOUNT_LABEL = "Total Demand Amount";
    public static final String TOTAL_DEMAND_UNITS_LABEL = "Total Demand Units";
    public static final String DEDUCTION_AMOUNT_LABEL = "Deduction Amount";
    public static final String SALES_AMOUNT_LABEL = "Sales Amount";
    public static final String UNITS_WITHDRAWN_LABEL = "Units Withdrawn";
    public static final String UNCAPTURED_UNITS_LABEL = "Uncaptured Units";
    public static final String MARKET_SIZE_UNITS_LABEL = "Market Size Units";
    public static final String PRICE_LABEL = "Price";
    public static final String UNITS_LABEL = "Units";
    public static final String TOTAL_DEMAND_AMOUNT1 = "totalDemandAmount";
    public static final String DEDUCTION_RATE = "Deduction Rate";
    public static final String MARKET_SHARE_UNITS_LABEL = "Market Share Units";
    public static final String MARKET_SIZE_UNITS1 = "marketSizeUnits";
    public static final String AMOUNT_WITHDRAWN_LABEL = "Amount Withdrawn";
    public static final String COUNTRY = "country";
    public static final String COMPANY_IDENTIFIER1 = "companyIdentifier";
    public static final String FORECAST_NAME_PROPERTY = "forecastName";
    public static final String FORECAST_VERSION_PROPERTY = "forecastVersion";
    public static final String SOURCE_PROPERTY = "source";
    public static final String FORECAST_MONTH_PROPERTY = "forecastMonth";
    public static final String IDENTIFIER_CODE_QUALIFIER1 = "identifierCodeQualifier";
    public static final String FORECAST_TYPE_PROPERTY = "forecastType";
    public static final String PLEASE_ENTER_VALUE = "Please Enter value";
    public static final String MARKET_SHARE_RATIO_LABEL = "Market Share Ratio";
    public static final String SEARCH_TEXTNONABSOLUTE = "searchText-nonabsolute";
    public static final String PLEASE_ENTER_THE_VALUE_ALL_FIELDS = "Please Enter the value at all fields";
    public static final String UNIQUE_COMBINATION_ERROR = "Unique combination error";
    public static final String ARE_YOU_SURE_YOU_WANT_TO_RESET = "Are you sure you want to reset the page to default/previous values ";
    public static final String DATE2 = "date2";
    public static final String TEXT5 = "text5";
    public static final String DATE1 = "date1";
    public static final String NO_OF_LEVELS = "noOfLevels";
    public static final String OPTION1 = "option1";
    public static final String TEXT4 = "text4";
    public static final String TEXT1 = "text1";
    public static final String HIERARCHY_CATEGORY = "hierarchyCategory";
    public static final String NO_HIERARCHY_DEFINITION_HAS_BEEN_SELECTED = "No Hierarchy Definition has been selected. Please select a Hierarchy Definition and try again.";
    public static final String RESPONSIVE_GRID = "responsiveGrid";
    public static final String RESULTS = "Results";
    public static final String TEXT2 = "text2";
    public static final String AND_SPACE = " AND ";
    public static final String M_MDDYYYY = "MM/dd/yyyy";
    public static final String TEXT3 = "text3";
    public static final String COMBO1 = "combo1";
    public static final String PLEASE_ENTER_VALID_EMAIL_ADDRESS = "Please enter valid Email Address for ";
    public static final String ERROR = "Error";
    public static final String MANDATORY = "mandatory";
    public static final String TO_DEF_FREQUENCY_NAME = "toDefFrequencyName";
    public static final String DESC_SPACE = " DESC ";
    public static final String FROM_FREQUENCY_NAME = "fromFrequencyName";
    public static final String ASC_SPACE = " ASC ";
    public static final String FROM_PERIOD_VALUE = "fromPeriodValue";
    public static final String TO_DEF_PERIOD_VALUE = "toDefPeriodValue";
    public static final String TO_MODE_NAME = "toModeName";
    public static final String TO_DEF_MODE_NAME = "toDefModeName";
    public static final String FROM_DEF_MODE_NAME = "fromDefModeName";
    public static final String CREATED_BY = "createdBy";
    public static final String FROM_DEF_FREQUENCY_NAME = "fromDefFrequencyName";
    public static final String TO_FREQUENCY_NAME = "toFrequencyName";
    public static final String FROM_MODE_NAME = "fromModeName";
    public static final String TO_PERIOD_VALUE = "toPeriodValue";
    public static final String FROM_DEF_PERIOD_VALUE = "fromDefPeriodValue";
    public static final String ACTIVE_FLAG = "activeFlag";
    public static final String BUCINSESS_UNIT_NAME = "bucinsessUnitName";
    public static final String FROM_DEF_PERIOD_DATE = "fromDefPeriodDate";
    public static final String TO_PERIOD_DATE = "toPeriodDate";
    public static final String PERIOD_VIEW_NAME = "periodViewName";
    public static final String TO_DEF_PERIOD_DATE = "toDefPeriodDate";
    public static final String BUSCINESS_PROCESS_NAME = "buscinessProcessName";
    public static final String MODULE_NAME = "moduleName";
    public static final String COMPANY_NAME = "companyName";
    public static final String PERIOD_CONFIGURATION = "Period Configuration";
    public static final String VERSION_NO = "versionNo";
    public static final String RESULTS_SCREEN = "Results Screen";
    public static final String FROM_PERIOD_DATE = "fromPeriodDate";
    public static final String GROUP3 = "group3";
    public static final String GROUP1 = "group1";
    public static final String GROUP2 = "group2";
    public static final String ALIGNRIGHT = "align-right";
    public static final String PERIODCONFIG_MODE = "PERIODCONFIG_MODE";
    public static final String SINGLE = "Single";
    public static final String ALIGNCENTER = "align-center";
    public static final String PERIODCONFIG_FREQUENCY = "PERIODCONFIG_FREQUENCY";
    public static final String PROCESS_MONITOR = "Process Monitor";
    public static final String LANDING_SCREEN = "Landing screen";
    public static final String HDHIERARCHY_CATEGORY = "HD.HIERARCHY_CATEGORY";
    public static final String HIERARCHY_CATEGORY_PROPERTY = "hierarchyCategory";
    public static final String HDHIERARCHY_NAME = "HD.HIERARCHY_NAME";
    public static final String HDCREATED_DATE = "HD.CREATED_DATE";
    public static final String CREATED_DATE_PROPERTY = "createdDate";
    public static final String LIKE_QUOTE = " LIKE '";
    public static final String RELATIONSHIP_NAME = "relationshipName";
    public static final String HIERARCHY_NAME_DDLB = "hierarchyNameDDLB";
    public static final String CREATION_DATE_TO = "creationDateTo";
    public static final String START_DATE_TO = "startDateTo";
    public static final String START_DATE_FROM = "startDateFrom";
    public static final String RELATIONSHIP_TYPE_PROPERTY = "relationshipType";
    public static final String RELATIONSHIP_DESCRIPTION_PROPERTY = "relationshipDescription";
    public static final String CREATION_DATE_FROM = "creationDateFrom";
    public static final String FINANCIAL_FORECAST_ID = "financialForecastId";
    public static final String PROJECTION_NAME = "projectionName";
    public static final String CUSTOMER_NAME = "customerName";
    public static final String CONTRACT_NO = "contractNo";
    public static final String CONTRACT_NAME = "contractName";
    public static final String FINANCIAL_FORECAST_NAME = "financialForecastName";
    public static final String CUSTOMER_NO = "customerNo";
    public static final String FINANCIAL_FORECAST_NAME_SHOULD_BE_LESS = "Financial Forecast Name should be less than 50 characters";
    public static final String INTERVAL = "Interval";
    public static final String INACTIVE = "Inactive";
    public static final String SELECT_ONE = "-Select One-";
    public static final String PRIMARY = "Primary";
    public static final String RELATIONSHIP_BUILDER_OUTBOUND = "RELATIONSHIP_BUILDER_OUTBOUND";
    public static final String SELECT_ONE_SPACE = "- Select One -";
    public static final String DOWNLOAD = "DOWNLOAD";
    public static final String LIKE_SPACE = " like '";
    public static final String SOURCE = "SOURCE";
    public static final String FMFORECAST_DATE = "FM.FORECAST_DATE";
    public static final String TO_DATE = "toDate";
    public static final String FORECAST_NAME_LIST = "FORECAST_NAME";
    public static final String FROM_DATE = "fromDate";
    public static final String DATEQUERY = "@DATEQUERY";
    public static final String SOURCE1 = "source";
    public static final String A_NULL = ",NULL";
    public static final String AND_COUNTRY_LIKE = "' AND COUNTRY like '";
    public static final String AND_INW_COUNTRY_LIKE = "' AND INW.COUNTRY like '";
    public static final String AND_FORECAST_VER_LIKE = "' AND FORECAST_VER like '";
    public static final String BUSINESS_UNIT = "businessUnit";
    public static final String FILE_TYPE = "fileType";
    public static final String COMPANY = "company";
    public static final String BRAND = "brand";
    public static final String FORECAST_VERSION = "forecastVersion";
    public static final String DFSOURCE = "DF.SOURCE";
    private static final Logger LOGGER = Logger.getLogger(StringConstantUtils.class);

    public StringConstantUtils() {
        LOGGER.debug("StringConstantUtils");
    }
}
