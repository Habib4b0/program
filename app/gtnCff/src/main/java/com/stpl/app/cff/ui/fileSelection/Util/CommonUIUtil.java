/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.Util;

// TODO: Auto-generated Javadoc
import com.stpl.app.cff.util.StringConstantsUtil;

/**
 * The Class CommonUIUtil.
 *
 * @author Elangovan
 */
public final class CommonUIUtil {

    /**
     * The Constant COUNTRY.
     */
    public static final String COUNTRY = "Country";

    /**
     * The Constant FILE_MGT_HISTORY_RESULT_HEADER.
     */
    private static final String[] FILE_MGMT_HIST_RES_HEADER  = new String[]{"File", "Effective Date", "Type", StringConstantsUtil.VERSION_LABEL, "From Period", "To Period"};

    /**
     * The Constant FILE_MGT_LOOKUP_RESULT_COLUMNS.
     */
    private static final Object[] FILE_MGMT_LOOKUP_RES_COLS = new Object[]{"fileType", "country", "fileName", "type", "version", "fromDate", "toDate"};

    /**
     * The Constant FILE_MGT_LOOKUP_RESULT_HEADER.
     */
    private static final String[] FILE_MGMT_LOOKUP_RES_HEADER = new String[]{"File Type", COUNTRY, "File Name", "Type", StringConstantsUtil.VERSION_LABEL, "From Date", "To Date"};
    public static final String UNITS_PROPERTY = "units";
    public static final String ITEM_NAME_PROPERTY = "itemName";
    public static final String ITEM_NO = "itemNo";
    public static final String MONTH_PROPERTY = "month";
    public static final String PRICE_PROPERTY = "price";
    public static final String START_DATE = "startDate";

    /**
     * The Constant FILE_MGT_LOOKUP_DETAILS_COLUMNS.
     */
    private static final Object[] FILE_MGMT_LOOKUP_DETAILS_COLS = new Object[]{"year", MONTH_PROPERTY, ITEM_NO, ITEM_NAME_PROPERTY, START_DATE, PRICE_PROPERTY, UNITS_PROPERTY, "dollars"};
    /**
     * The Constant FILE_MGT_LOOKUP_DETAILS_COLUMNS.
     */
    private static final Object[] FILE_MGMT_LOOKUP_DETAILS_COLS_EXCEL = new Object[]{"year", MONTH_PROPERTY, ITEM_NO, ITEM_NAME_PROPERTY, START_DATE, PRICE_PROPERTY, UNITS_PROPERTY, "dollars"};
    public static final String MARKET_SIZE_UNITS = "Market Size Units";
    public static final String ITEM_IDENTIFIER = "Item Identifier";
    public static final String ORGANIZATION_KEY = "Organization Key";
    public static final String MARKET_SHARE_RATIO_LABEL = "Market Share Ratio";
    public static final String NET_SALES_PRICE = "Net Sales Price";
    public static final String TOTAL_DEMAND_AMOUNT = "Total Demand Amount";
    public static final String TOTAL_DEMAND_UNITS = "Total Demand Units";
    public static final String UNCAPTURED_UNITS = "Uncaptured Units";
    public static final String GROSS_PRICE = "Gross Price";
    public static final String GROSS_UNITS_LABEL = "Gross Units";
    public static final String ITEM_ID_LABEL = "Item ID";
    public static final String GROSS_AMOUNT = "Gross Amount";
    public static final String FORECAST_YEAR = "Forecast Year";
    public static final String FORECAST_TYPE = "Forecast Type";
    public static final String NET_SALE_AMOUNT = "Net Sales Amount";
    public static final String INVENTORY_UNIT_CHANGE = "Inventory Unit Change";
    public static final String FORECAST_MONTH_LABEL = "Forecast Month";
    public static final String UNCAPTURED_UNITS_RATIO = "Uncaptured Units Ratio";
    /**
     * The Constant FILE_MGT_LOOKUP_DEMAND_DETAILS_HEADER
     */
    private static final String[] FILE_MGMT_LOOKUP_DEMAND_DETAILS_HEADER = new String[]{FORECAST_TYPE, FORECAST_YEAR, FORECAST_MONTH_LABEL, ITEM_ID_LABEL, StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER_LABEL,
        ITEM_IDENTIFIER, StringConstantsUtil.BRAND_ID_LABEL, StringConstantsUtil.SEGMENT_LABEL,
        MARKET_SIZE_UNITS, MARKET_SHARE_RATIO_LABEL, StringConstantsUtil.MARKET_SHARE_UNITS_LABEL,
        UNCAPTURED_UNITS, UNCAPTURED_UNITS_RATIO, TOTAL_DEMAND_UNITS, TOTAL_DEMAND_AMOUNT, INVENTORY_UNIT_CHANGE, GROSS_UNITS_LABEL, GROSS_PRICE, GROSS_AMOUNT, NET_SALES_PRICE, NET_SALE_AMOUNT, StringConstantsUtil.BATCH_ID_LABEL, ORGANIZATION_KEY};
    public static final String BRAND_ID = "brandId";
    public static final String NET_SALES_AMOUNT = "netSalesAmount";
    public static final String TOTAL_DEMAND_UNITS1 = "totalDemandUnits";
    public static final String MARKET_SHARE_UNITS = "marketShareUnits";
    public static final String GROSS_UNITS = "grossUnits";
    public static final String ORGANIZATION_KEY1 = "organizationKey";
    public static final String UNCAPTURED_UNITS1 = "uncapturedUnits";
    public static final String GROSS_PRICE1 = "grossPrice";
    public static final String UNCAPTURED_UNITS_RATIO1 = "uncapturedUnitsRatio";
    public static final String TOTAL_DEMAND_AMOUNT1 = "totalDemandAmount";
    public static final String GROSS_AMOUNT1 = "grossAmount";
    public static final String BATCH_ID_PROPERTY = "batchId";
    public static final String FORCAST_YEAR = "forcastYear";
    public static final String SEGMENT_PROPERTY = "segment";
    public static final String MARKET_SHARE_RATIO_PROPERTY = "marketShareRatio";
    private static final Object[] FILE_MGMT_LOOKUP_DEMAND_DETAILS_COLS = new Object[]{"forecastType", FORCAST_YEAR, StringConstantsUtil.FORECAST_MONTH, StringConstantsUtil.ITEM_ID,
        StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER, StringConstantsUtil.ITEM_IDENTIFIER, BRAND_ID, SEGMENT_PROPERTY, StringConstantsUtil.MARKET_SIZE_UNITS, MARKET_SHARE_RATIO_PROPERTY, MARKET_SHARE_UNITS, UNCAPTURED_UNITS1, UNCAPTURED_UNITS_RATIO1,
         TOTAL_DEMAND_UNITS1, TOTAL_DEMAND_AMOUNT1, StringConstantsUtil.INVENTORY_UNIT_CHANGE, GROSS_UNITS, GROSS_PRICE1, GROSS_AMOUNT1, StringConstantsUtil.NET_SALES_PRICE, NET_SALES_AMOUNT, BATCH_ID_PROPERTY, ORGANIZATION_KEY1};
    private static final String[] FILE_MGMT_LOOKUP_DEMAND_DETAILS_HEADER_EXCEL = new String[]{FORECAST_TYPE, FORECAST_YEAR, FORECAST_MONTH_LABEL,
        "Item Id", StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER_LABEL, ITEM_IDENTIFIER,
        "Brand Id", StringConstantsUtil.SEGMENT_LABEL, MARKET_SIZE_UNITS, MARKET_SHARE_RATIO_LABEL, StringConstantsUtil.MARKET_SHARE_UNITS_LABEL, UNCAPTURED_UNITS, UNCAPTURED_UNITS_RATIO, TOTAL_DEMAND_UNITS, TOTAL_DEMAND_AMOUNT, INVENTORY_UNIT_CHANGE, GROSS_UNITS_LABEL, GROSS_PRICE, GROSS_AMOUNT, NET_SALES_PRICE, NET_SALE_AMOUNT, "Batch Id", ORGANIZATION_KEY};
    private static final Object[] FILE_MGMT_LOOKUP_DEMAND_DETAILS_COLS_EXCEL = new Object[]{"forecastType", FORCAST_YEAR, StringConstantsUtil.FORECAST_MONTH, StringConstantsUtil.ITEM_ID,
        StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER, StringConstantsUtil.ITEM_IDENTIFIER, BRAND_ID, SEGMENT_PROPERTY, StringConstantsUtil.MARKET_SIZE_UNITS, MARKET_SHARE_RATIO_PROPERTY, MARKET_SHARE_UNITS, UNCAPTURED_UNITS1, UNCAPTURED_UNITS_RATIO1,
         TOTAL_DEMAND_UNITS1, TOTAL_DEMAND_AMOUNT1, StringConstantsUtil.INVENTORY_UNIT_CHANGE, GROSS_UNITS, GROSS_PRICE1, GROSS_AMOUNT1, StringConstantsUtil.NET_SALES_PRICE, NET_SALES_AMOUNT, BATCH_ID_PROPERTY, ORGANIZATION_KEY1};
    public static final String AMOUNT_WITHDRAWN = "amountWithdrawn";
    public static final String UNITS_WITHDRAWN1 = "unitsWithdrawn";
    /**
     * The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_COLUMNS
     */
    private static final Object[] FILE_MGMT_LOOKUP_INV_DETAILS_SUMMARY_COLS = new Object[]{"year", MONTH_PROPERTY, "day", "week", StringConstantsUtil.ITEM_ID, StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER, StringConstantsUtil.ITEM_IDENTIFIER, UNITS_WITHDRAWN1, AMOUNT_WITHDRAWN, PRICE_PROPERTY, BATCH_ID_PROPERTY, ORGANIZATION_KEY1};
    public static final String MONTH_LABEL = "Month";
    public static final String UNITS_WITHDRAWN = "Units Withdrawn";
    public static final String PRICE_LABEL = "Price";
    public static final String AMOUNT_WITHDRAWN1 = "Amount Withdrawn";
    /**
     * The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_HEADER
     */
    private static final String[] FILE_MGMT_LOOKUP_INV_DETAILS_SUMMARY_HEADER = new String[]{"Year", MONTH_LABEL, "Day", "Week", ITEM_ID_LABEL, StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER_LABEL, ITEM_IDENTIFIER, UNITS_WITHDRAWN, AMOUNT_WITHDRAWN1, PRICE_LABEL, StringConstantsUtil.BATCH_ID_LABEL, ORGANIZATION_KEY};
    /**
     * The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_COLUMNS
     */
    private static final Object[] FILE_MGMT_INV_DETAILS_SUM_COLS_EXCEL = new Object[]{"year", MONTH_PROPERTY, "day", "week", StringConstantsUtil.ITEM_ID, StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER, StringConstantsUtil.ITEM_IDENTIFIER, UNITS_WITHDRAWN1, AMOUNT_WITHDRAWN, PRICE_PROPERTY, BATCH_ID_PROPERTY, ORGANIZATION_KEY1};
    /**
     * The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_HEADER
     */
    private static final String[] FILE_MGMT_LOOKUP_INV_DETAILS_SUM_HEADER = new String[]{"Year", MONTH_LABEL, "Day", "Week", ITEM_ID_LABEL, StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER_LABEL, ITEM_IDENTIFIER, UNITS_WITHDRAWN, AMOUNT_WITHDRAWN1, PRICE_LABEL, StringConstantsUtil.BATCH_ID_LABEL, ORGANIZATION_KEY};
    /**
     * The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_COLUMNS
     */
    private static final Object[] FILE_MGMT_LOOKUP_INV_DETAILS_COLS = new Object[]{"year", MONTH_PROPERTY, "day", "week", StringConstantsUtil.COMPANY_ID_PROPERTY, "identifierCodeQualifier", "companyIdentifier", StringConstantsUtil.ITEM_ID, StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER, StringConstantsUtil.ITEM_IDENTIFIER, UNITS_WITHDRAWN1, AMOUNT_WITHDRAWN, PRICE_PROPERTY, BATCH_ID_PROPERTY, ORGANIZATION_KEY1};
    public static final String COMPANY_ID_LABEL = "Company ID";
    /**
     * The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_HEADER
     */
    private static final String[] FILE_MGMT_LOOKUP_INV_DETAILS_HEADER = new String[]{"Year", MONTH_LABEL, "Day", "Week", COMPANY_ID_LABEL, "Identifier Code Qualifier", "Company Identifier", ITEM_ID_LABEL, StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER_LABEL, ITEM_IDENTIFIER, UNITS_WITHDRAWN, AMOUNT_WITHDRAWN1, PRICE_LABEL, StringConstantsUtil.BATCH_ID_LABEL, ORGANIZATION_KEY};

    /**
     * The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_COLUMNS
     */
    private static final Object[] FILE_MGMT_LOOKUP_INV_DETAILS_COLS_EXCEL = new Object[]{"year", MONTH_PROPERTY, "day", "week", StringConstantsUtil.COMPANY_ID_PROPERTY, "identifierCodeQualifier", "companyIdentifier", StringConstantsUtil.ITEM_ID, StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER, StringConstantsUtil.ITEM_IDENTIFIER, UNITS_WITHDRAWN1, AMOUNT_WITHDRAWN, PRICE_PROPERTY, BATCH_ID_PROPERTY, ORGANIZATION_KEY1};
    /**
     * The Constant FILE_MGT_LOOKUP_INVENTORY_DETAILS_HEADER
     */
    private static final String[] FILE_MGMT_LOOKUP_INV_DETAILS_HEADER_EXCEL = new String[]{"Year", MONTH_LABEL, "Day", "Week", "Company Id", "Identifier Code Qualifier", "Company Identifier", ITEM_ID_LABEL, StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER_LABEL, ITEM_IDENTIFIER, UNITS_WITHDRAWN, AMOUNT_WITHDRAWN1, PRICE_LABEL, StringConstantsUtil.BATCH_ID_LABEL, ORGANIZATION_KEY};
    public static final String DOLLARS = "Dollars";
    public static final String UNITS_LABEL = "Units";
    public static final String ITEM_NAME_LABEL = "Item Name";
    public static final String FORECAST_DATE_LABEL = "Forecast Date";
    public static final String ITEM_HASH = "Item #";

    /**
     * The Constant FILE_MGT_LOOKUP_DETAILS_HEADER.
     */
    private static final String[] FILE_MGMT_LOOKUP_DETAILS_HEADER = new String[]{"Year", MONTH_LABEL, ITEM_HASH, ITEM_NAME_LABEL, FORECAST_DATE_LABEL, PRICE_LABEL, UNITS_LABEL, DOLLARS};
    private static final String[] FILE_MGMT_LOOKUP_DETAILS_HEADER_EXCEL = new String[]{"Year", MONTH_LABEL, ITEM_HASH, ITEM_NAME_LABEL, FORECAST_DATE_LABEL, PRICE_LABEL, UNITS_LABEL, DOLLARS};

    /**
     * The Constant AC_SEARCH_RESULT_COLUMNS.
     */
    private static final Object[] AC_SEARCH_RESULTS_COLS = new Object[]{"resultRSName", "resultRSDesc", "resultRSType", "resultRSHierarchyName", "versionNo", START_DATE, "createdDate", "modifiedDate",
        "resultCreatedBy"};
    public static final String CREATED_BY_LABEL = "Created By";
    public static final String MODIFIED_DATE_LABEL = "Modified Date";

    /**
     * The Constant AC_SEARCH_RESULT_HEADER.
     */
    private static final String[] AC_SEARCH_RESULTS_HEADER = new String[]{"Relationship Name", "Relationship Description", "Relationship Type", "Hierarchy Name", "version No", "Start Date", "Creation Date", MODIFIED_DATE_LABEL, CREATED_BY_LABEL};

    /**
     * The Constant AC_HB_SEARCH_RESULT_COLUMNS.
     */
    private static final Object[] AC_HB_SEARCH_RESULTS_COLS = new Object[]{"hierarchyName", "hierarchyType", "hierarchyCategoryInString", "noOfLevels", "versionNo", "createdDate", "modifiedDate", "createdBy"};

    /**
     * The Constant AC_HB_SEARCH_RESULT_HEADER.
     */
    private static final String[] AC_HB_SEARCH_RES_HEADER = new String[]{"Hierarchy Name", "Hierarchy Type", "Hierarchy Category", "# of Levels", StringConstantsUtil.VERSION_LABEL, "Creation Date", MODIFIED_DATE_LABEL, CREATED_BY_LABEL};

    /**
     * The Constant AC_HB_LEVEL_DEFINITION_COLUMNS.
     */
    private static final Object[] AC_HB_LEVEL_DEF_COLS = new Object[]{"levelNo", "levelName", "tableName", "fieldName", "userDefinedOrLinked"};

    /**
     * The Constant AC_HB_LEVEL_DEFINITION_HEADER.
     */
    private static final String[] AC_HB_LEVEL_DEF_HEADER = new String[]{"Level #", "Level Name", "Table Name", "Field Name", "User Defined/ Linked"};

    /**
     * The Constant AC_HB_LEVEL_VALUES_COLUMNS.
     */
    private static final Object[] AC_HB_LEVEL_VALS_COLS = new Object[]{"levelValues"};

    /**
     * The Constant AC_HB_LEVEL_VALUES_HEADER.
     */
    private static final String[] AC_HB_LEVEL_VALUES_HEADER = new String[]{"Level Values"};

    /**
     * The Constant TABLE_LOOKUP_COLUMN.
     */
    private static final Object[] TABLE_LOOKUP_COLUMN = new Object[]{"tableName"};

    /**
     * The Constant TABLE_LOOKUP_HEADER.
     */
    private static final String[] TABLE_LOOKUP_HEADER = new String[]{"Table Name"};

    /**
     * The Constant FIELD_LOOKUP_COLUMN.
     */
    private static final Object[] FIELD_LOOKUP_COL = new Object[]{"fieldName"};

    /**
     * The Constant FIELD_LOOKUP_HEADER.
     */
    private static final String[] FIELD_LOOKUP_HEADER = new String[]{"Field Name"};

    /**
     * The Constant RB_SELECTED_LEVEL_COLUMN.
     */
    private static final Object[] RB_SELECTED_LEVEL_COL = new Object[]{"levelValue"};

    /**
     * The Constant RB_AVAILABLE_LEVEL_COLUMN.
     */
    private static final Object[] RB_AVAILABLE_LEVEL_COL = new Object[]{"levelValue"};

    /**
     * The Constant HIERARCHY_CATEGORY.
     */
    public static final String HIERARCHY_CATEGORY = "HIERARCHY_CATEGORY";

    /**
     * The Constant ITEM_RESULTS_COLUMNS.
     */
    private static final Object[] ITEM_RESULTS_COLS = new Object[]{
        StringConstantsUtil.ITEM_ID, ITEM_NO, "itemCode", ITEM_NAME_PROPERTY, "itemDesc", "itemStartDate", "itemEndDate", "itemStatus",
        "therapeuticClass", "brand", "form", "strength", "packageSizeCode", "packageSizeIntroDate", "upps",
        "manufacturerId", "manufacturerNo", "manufacturerName", "labelerCode", ORGANIZATION_KEY1,
        "acquisitionDate", "authorizedGeneric", "authorizedGenericStartDate", "authorizedGenericEndDate",
        "firstSaleDate", "itemTypeIndicator", "itemClass", "itemType", "marketTerminationDate", "newFormulationIndicator",
        "newFormulation", "newFormulationStartDate", "newFormulationEndDate", "pediatricExclusiveIndicator",
        "pediatricExclusiveStartDate", "pediatricExclusiveEndDate", "clottingFactorIndicator", "clottingFactorStartDate",
        "clottingFactorEndDate", "primaryUom", "secondaryUom", "shelfLife", "shelfLifeType", "dualPricingIndicator",
        "itemFamilyId", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6", "acquiredAmp", "acquiredBamp", "obraBamp", "dra",
        "dosesPerUnit", "discontinuationDate", "lastLotExpirationDate", "ndc9", "ndc8", "displayBrand", "innovatorCode",
        "baselineAmp", "baseYearCpi"};

    /**
     * The Constant ITEM_RESULTS_HEADER.
     */
    private static final String[] ITEM_RESULTS_HEADER = new String[]{
        ITEM_ID_LABEL, "Item No", "Item Code", ITEM_NAME_LABEL, "Item Desc", "Item Start Date", "Item End Date",
        "Item Status", "Therapeutic Class", "Brand", "Form", "Strength", "Package Size Code",
        "Package Size Intro Date", "UPPS", "Manufacturer ID", "Manufacturer No", "Manufacturer Name",
        "Labeler Code", ORGANIZATION_KEY, "Acquisition Date", "Authorized Generic", "Authorized Generic Start Date",
        "Authorized Generic End Date", "First Sale Date", "Item Type Indicator", "Item Class ", "Item Type ",
        "Market Termination Date", "New Formulation Indicator", "New Formulation", "New Formulation Start Date",
        "New Formulation End Date", "Pediatric Exclusive Indicator", "Pediatric Exclusive Start Date",
        "Pediatric Exclusive End Date", "Clotting Factor Indicator", "Clotting Factor Start Date",
        "Clotting Factor End Date", "Primary UOM", "Secondary UOM", "Shelf Life", "Shelf Life Type",
        "Dual Pricing Indicator", "Item Family ID", "UDC 1", "UDC 2", "UDC 3", "UDC 4", "UDC 5", "UDC 6", "Acquired AMP",
        "Acquired BAMP", "OBRA BAMP", "DRA", "Doses per Unit", "Discontinuation Date", "Last Lot Expiration Date",
        "NDC9", "NDC8", "Display Brand", "Innovator Code", "Baseline AMP", "Base Year CPI"};

    private static final String[] FILE_MGMT_LOOKUP_CUS_HEADER = new String[]{FORECAST_YEAR, FORECAST_MONTH_LABEL, ITEM_ID_LABEL, COMPANY_ID_LABEL, UNITS_LABEL, "Price Type", PRICE_LABEL, "Sales Amount", "Sales Inclusion", "Deduction ID",
        "Deduction Category", "Deduction Type", "Deduction Program Type",
        "Adjustment Code", "Deduction Rate", "Deduction Amount",
        "Deduction Inclusion", "Forecast Value Type", StringConstantsUtil.BRAND_ID_LABEL, StringConstantsUtil.SEGMENT_LABEL, StringConstantsUtil.BATCH_ID_LABEL, ORGANIZATION_KEY, "Forecast Version", COUNTRY, "Forecast Name", FORECAST_DATE_LABEL};
    private static final Object[] fileMgtLookupCustomerColumns = new Object[]{FORCAST_YEAR, StringConstantsUtil.FORECAST_MONTH, StringConstantsUtil.ITEM_ID,
        StringConstantsUtil.COMPANY_ID_PROPERTY, UNITS_PROPERTY, "priceType", PRICE_PROPERTY, "salesAmount", "salesInclusion",
         "deductionId", "deductionCategory", "deductionType", "deductionProgramType",
         "adjustmentCode", "deductionRate", "deductionAmount", "deductionInclusion", "forecastValueType",
         BRAND_ID, SEGMENT_PROPERTY, BATCH_ID_PROPERTY, ORGANIZATION_KEY1, "forecastVersion", "country", "forecastName",
        "forecastDate"};

    private static final String[] FILE_MGMT_LOOKUP_ADJ_DEMAND_DETAILS_HEADER = new String[]{ITEM_ID_LABEL, ITEM_NAME_LABEL, StringConstantsUtil.BRAND_ID_LABEL, "Brand Name", StringConstantsUtil.SEGMENT_LABEL, "Year", MONTH_LABEL, MARKET_SIZE_UNITS, MARKET_SHARE_RATIO_LABEL, StringConstantsUtil.MARKET_SHARE_UNITS_LABEL, UNCAPTURED_UNITS, UNCAPTURED_UNITS_RATIO, TOTAL_DEMAND_UNITS, TOTAL_DEMAND_AMOUNT, INVENTORY_UNIT_CHANGE, GROSS_UNITS_LABEL, GROSS_PRICE, GROSS_AMOUNT, NET_SALES_PRICE, NET_SALE_AMOUNT, StringConstantsUtil.BATCH_ID_LABEL, "Source", ORGANIZATION_KEY};
    private static final Object[] FILE_MGMT_LOOKUP_ADJ_DEMAND_DETAILS_COLS = new Object[]{StringConstantsUtil.ITEM_ID, ITEM_NAME_PROPERTY, BRAND_ID, "brandName", SEGMENT_PROPERTY, FORCAST_YEAR, StringConstantsUtil.FORECAST_MONTH, StringConstantsUtil.MARKET_SIZE_UNITS, MARKET_SHARE_RATIO_PROPERTY, MARKET_SHARE_UNITS, UNCAPTURED_UNITS1, UNCAPTURED_UNITS_RATIO1, TOTAL_DEMAND_UNITS1, TOTAL_DEMAND_AMOUNT1, StringConstantsUtil.INVENTORY_UNIT_CHANGE, GROSS_UNITS, GROSS_PRICE1, GROSS_AMOUNT1, StringConstantsUtil.NET_SALES_PRICE, NET_SALES_AMOUNT, BATCH_ID_PROPERTY, "source", ORGANIZATION_KEY1};

    /**
     * public constructor to protect to object creation.
     */
    public CommonUIUtil() {
        // public constructor to protect to object creation.
    }

    private static final String[] EXCEL_FILE_MGMT_LOOKUP_ADJ_DEMAND_DETAILS_HEADER = new String[]{ITEM_ID_LABEL, ITEM_NAME_LABEL, StringConstantsUtil.BRAND_ID_LABEL, "Brand Name", StringConstantsUtil.SEGMENT_LABEL, "Year", MONTH_LABEL, MARKET_SIZE_UNITS, MARKET_SHARE_RATIO_LABEL, StringConstantsUtil.MARKET_SHARE_UNITS_LABEL, UNCAPTURED_UNITS, UNCAPTURED_UNITS_RATIO, TOTAL_DEMAND_UNITS, TOTAL_DEMAND_AMOUNT, INVENTORY_UNIT_CHANGE, GROSS_UNITS_LABEL, GROSS_PRICE, GROSS_AMOUNT, NET_SALES_PRICE, NET_SALE_AMOUNT, StringConstantsUtil.BATCH_ID_LABEL, "Source", ORGANIZATION_KEY};

    private static final String[] EXCEL_FILE_MGMT_LOOKUP_DEMAND_DETAILS_HEADER = new String[]{FORECAST_TYPE, FORECAST_YEAR, FORECAST_MONTH_LABEL,
        "Item Id", StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER_LABEL, ITEM_IDENTIFIER,
        "Brand Id", StringConstantsUtil.SEGMENT_LABEL, MARKET_SIZE_UNITS, MARKET_SHARE_RATIO_LABEL, StringConstantsUtil.MARKET_SHARE_UNITS_LABEL, UNCAPTURED_UNITS, UNCAPTURED_UNITS_RATIO, TOTAL_DEMAND_UNITS, TOTAL_DEMAND_AMOUNT, INVENTORY_UNIT_CHANGE, GROSS_UNITS_LABEL, GROSS_PRICE, GROSS_AMOUNT, NET_SALES_PRICE, NET_SALE_AMOUNT, "Batch Id", ORGANIZATION_KEY};

    private static final String[] EXCEL_FILE_MGMT_LOOKUP_DETAILS_HEADER = new String[]{"Year", MONTH_LABEL, ITEM_HASH, ITEM_NAME_LABEL, FORECAST_DATE_LABEL, PRICE_LABEL, UNITS_LABEL, DOLLARS};

    private static final String[] EXCEL_FILE_MGMT_LOOKUP_INV_DETAILS_HEADER = new String[]{"Year", MONTH_LABEL, "Day", "Week", COMPANY_ID_LABEL, "Company Name", ITEM_ID_LABEL, ITEM_NAME_LABEL, UNITS_WITHDRAWN, AMOUNT_WITHDRAWN1, PRICE_LABEL, CREATED_BY_LABEL, "Created Date", "Modified By", MODIFIED_DATE_LABEL, "Add Chg Del Indicator", StringConstantsUtil.BATCH_ID_LABEL, "Source ID", "Forecast Name", "Forecast Ver", "Country", ORGANIZATION_KEY};

    private static final String[] EXCEL_FILE_MGMT_LOOKUP_INV_DETAILS_SUMMARY = new String[]{"Year", MONTH_LABEL, "Day", "Week", ITEM_ID_LABEL, StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER_LABEL, ITEM_IDENTIFIER, UNITS_WITHDRAWN, AMOUNT_WITHDRAWN1, PRICE_LABEL, StringConstantsUtil.BATCH_ID_LABEL, ORGANIZATION_KEY};
       
    private static CommonUIUtil object;

    public static CommonUIUtil getInstance() {
        if (object == null) {
            object = new CommonUIUtil();
        }
        return object;
    }

	public static String[] getFileMgmtHistResHeader() {
		return FILE_MGMT_HIST_RES_HEADER.clone();
	}

	public static Object[] getFileMgmtLookupResColsList() {
		return FILE_MGMT_LOOKUP_RES_COLS.clone();
	}

	public static String[] getFileMgmtLookupResHeaderList() {
		return FILE_MGMT_LOOKUP_RES_HEADER.clone();
	}

	public static Object[] getFileMgmtLookupDetailsColsList() {
		return FILE_MGMT_LOOKUP_DETAILS_COLS.clone();
	}

	public static Object[] getFileMgmtLookupDetailsColsExcelList() {
		return FILE_MGMT_LOOKUP_DETAILS_COLS_EXCEL.clone();
	}

	public static String[] getFileMgmtLookupDemandDetailsHeaderList() {
		return FILE_MGMT_LOOKUP_DEMAND_DETAILS_HEADER.clone();
	}

	public static Object[] getFileMgmtLookupDemandDetailsColsList() {
		return FILE_MGMT_LOOKUP_DEMAND_DETAILS_COLS.clone();
	}

	public static String[] getFileMgmtLookupDemandDetailsHeaderExcelList() {
		return FILE_MGMT_LOOKUP_DEMAND_DETAILS_HEADER_EXCEL.clone();
	}

	public static Object[] getFileMgmtLookupDemandDetailsColsExcelList() {
		return FILE_MGMT_LOOKUP_DEMAND_DETAILS_COLS_EXCEL.clone();
	}

	public static Object[] getFileMgmtLookupInvDetailsSummaryColsList() {
		return FILE_MGMT_LOOKUP_INV_DETAILS_SUMMARY_COLS.clone();
	}

	public static String[] getFileMgmtLookupInvDetailsSummaryHeaderList() {
		return FILE_MGMT_LOOKUP_INV_DETAILS_SUMMARY_HEADER.clone();
	}

	public static Object[] getFileMgmtInvDetailsSumColsExcelList() {
		return FILE_MGMT_INV_DETAILS_SUM_COLS_EXCEL.clone();
	}

	public static String[] getFileMgmtLookupInvDetailsSumHeaderList() {
		return FILE_MGMT_LOOKUP_INV_DETAILS_SUM_HEADER.clone();
	}

	public static Object[] getFileMgmtLookupInvDetailsColsList() {
		return FILE_MGMT_LOOKUP_INV_DETAILS_COLS.clone();
	}

	public static String[] getFileMgmtLookupInvDetailsHeaderList() {
		return FILE_MGMT_LOOKUP_INV_DETAILS_HEADER.clone();
	}

	public static Object[] getFileMgmtLookupInvDetailsColsExcelList() {
		return FILE_MGMT_LOOKUP_INV_DETAILS_COLS_EXCEL.clone();
	}

	public static String[] getFileMgmtLookupInvDetailsHeaderExcelList() {
		return FILE_MGMT_LOOKUP_INV_DETAILS_HEADER_EXCEL.clone();
	}

	public static String[] getFileMgmtLookupDetailsHeaderList() {
		return FILE_MGMT_LOOKUP_DETAILS_HEADER.clone();
	}

	public static String[] getFileMgmtLookupDetailsHeaderExcelList() {
		return FILE_MGMT_LOOKUP_DETAILS_HEADER_EXCEL.clone();
	}

	public static Object[] getAcSearchResultsColsList() {
		return AC_SEARCH_RESULTS_COLS.clone();
	}

	public static String[] getAcSearchResultsHeaderList() {
		return AC_SEARCH_RESULTS_HEADER.clone();
	}

	public static Object[] getAcHbSearchResultsColsList() {
		return AC_HB_SEARCH_RESULTS_COLS.clone();
	}

	public static String[] getAcHbSearchResHeaderList() {
		return AC_HB_SEARCH_RES_HEADER.clone();
	}

	public static Object[] getAcHbLevelDefColsList() {
		return AC_HB_LEVEL_DEF_COLS.clone();
	}

	public static String[] getAcHbLevelDefHeaderList() {
		return AC_HB_LEVEL_DEF_HEADER.clone();
	}

	public static Object[] getAcHbLevelValsColsList() {
		return AC_HB_LEVEL_VALS_COLS.clone();
	}

	public static String[] getAcHbLevelValuesHeaderList() {
		return AC_HB_LEVEL_VALUES_HEADER.clone();
	}

	public static Object[] getTableLookupColumnList() {
		return TABLE_LOOKUP_COLUMN.clone();
	}

	public static String[] getTableLookupHeaderList() {
		return TABLE_LOOKUP_HEADER.clone();
	}

	public static Object[] getFieldLookupColList() {
		return FIELD_LOOKUP_COL.clone();
	}

	public static String[] getFieldLookupHeaderList() {
		return FIELD_LOOKUP_HEADER.clone();
	}

	public static Object[] getRbSelectedLevelColList() {
		return RB_SELECTED_LEVEL_COL.clone();
	}

	public static Object[] getRbAvailableLevelColList() {
		return RB_AVAILABLE_LEVEL_COL.clone();
	}

	public static Object[] getItemResultsColsList() {
		return ITEM_RESULTS_COLS.clone();
	}

	public static String[] getItemResultsHeaderList() {
		return ITEM_RESULTS_HEADER.clone();
	}

	public static String[] getFileMgmtLookupCusHeaderList() {
		return FILE_MGMT_LOOKUP_CUS_HEADER.clone();
	}

	public static Object[] getFileMgmtLookupCustomerColumnsList() {
		return fileMgtLookupCustomerColumns.clone();
	}

	public static String[] getFileMgmtLookupAdjDemandDetailsHeaderList() {
		return FILE_MGMT_LOOKUP_ADJ_DEMAND_DETAILS_HEADER.clone();
	}

	public static Object[] getFileMgmtLookupAdjDemandDetailsColsList() {
		return FILE_MGMT_LOOKUP_ADJ_DEMAND_DETAILS_COLS.clone();
	}

	public static String[] getExcelFileMgmtLookupAdjDemandDetailsHeaderList() {
		return EXCEL_FILE_MGMT_LOOKUP_ADJ_DEMAND_DETAILS_HEADER.clone();
	}

	public static String[] getExcelFileMgmtLookupDemandDetailsHeaderList() {
		return EXCEL_FILE_MGMT_LOOKUP_DEMAND_DETAILS_HEADER.clone();
	}

	public static String[] getExcelFileMgmtLookupDetailsHeaderList() {
		return EXCEL_FILE_MGMT_LOOKUP_DETAILS_HEADER.clone();
	}

	public static String[] getExcelFileMgmtLookupInvDetailsHeaderList() {
		return EXCEL_FILE_MGMT_LOOKUP_INV_DETAILS_HEADER.clone();
	}

	public static String[] getExcelFileMgmtLookupInvDetailsSummaryList() {
		return EXCEL_FILE_MGMT_LOOKUP_INV_DETAILS_SUMMARY.clone();
	}
}
