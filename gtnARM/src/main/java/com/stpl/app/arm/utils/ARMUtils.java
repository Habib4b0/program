
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.utils;

import com.stpl.app.arm.utils.ARMUtils.SalesVariables;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author
 */
public class ARMUtils {

    public static final DecimalFormat TWO_DECIMAL_CURRENCY = new DecimalFormat("$#,##,##0.00");
    public static final DecimalFormat ZERO_DECIMAL_CURRENCY = new DecimalFormat("$#,##,##0");
    public static final DecimalFormat ZERO_DECIMAL = new DecimalFormat("#,##,##0");
    public static final DecimalFormat TWO_DECIMAL_PERCENT = new DecimalFormat("#,##0.00");
    public static final String[] ADJUSTMENT_RESERVE_SEARCH_HEADERS = new String[]{"Company No", "Company Name", "Business Unit No",
        "Business Unit Name", "Deduction Category", "Deduction Type", "Deduction Program",
        "Created By", "Created Date", "Modified Date", "Source"};
    public static final Object[] ADJUSTMENT_RESERVE_SEARCH_COLUMNS = new Object[]{"companyNo", "companyName", "businessUnitNo",
        "businessUnitName", "deductionCategory", "deductionType", "deductionProgram",
        "createdBy", "createdDate", "modifiedDate", "source"};
    public static final String[] ADJUSTMENT_RESERVE_ADD_HEADERS_RESERVE_DETAILS = new String[]{"", "Adjustment Type", "Adjustment Level", "Account Category", "Account Type", "Account",
        "Account Description", "Account Indicator", "Debit Indicator", "Credit Indicator", "Company No", "Division", "Business Unit", "Cost Center",
        "Project", "Future 1", "Future 2", "Balance Type", "Database", "Data Access Set", "Chart Of Accounts",
        "Ledger", "Category", "Source", "Currency", "Journal Name", "Journal Description", "Reverse Journal",
        "Reversal Period/Date", "Line Description", "UDC 1", "UDC 2", "UDC 3", "UDC 4", "UDC 5", "UDC 6"};
    public static final Object[] ADJUSTMENT_RESERVE_ADD_COLUMNS_RESERVE_DETAILS = new Object[]{ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(),
        ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_TYPE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_CATEGORY.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_TYPE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNTDESCRIPTION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNTINDICTOR.getConstant(),
        ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.COMPANYNO.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.DIVISION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.BUSINESS_UNIT.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.COST_CENTER.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.PROJECT.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.FUTURE1.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.FUTURE2.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.BALANCE_TYPE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.DATABASE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.DATA_ACCESS_SET.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.CHART_OF_ACCOUNTS.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.LEDGER.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.CATEGORY.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.SOURCE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.CURRENCY.getConstant(),
        ADJUSTMENT_RESERVE_CONSTANTS.JOURNAL_NAME.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.JOURNAL_DESCRIPTION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.REVERSE_JOURNAL.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.REVERSAL_PERIOD_DATE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.LINE_DESCRIPTION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC1.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC2.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC3.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC4.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC5.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC6.getConstant()};

    public static final Object[] ADJUSTMENT_RESERVE_FOR_EXCEL_COLUMNS_RESERVE_DETAILS = new Object[]{
        ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_TYPE_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_CATEGORY_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_TYPE_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNTDESCRIPTION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_INDICTOR_STR.getConstant(),
        ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.COMPANYNO.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.DIVISION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.BUSINESS_UNIT.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.COST_CENTER.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.PROJECT.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.FUTURE1.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.FUTURE2.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.BALANCE_TYPE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.DATABASE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.DATA_ACCESS_SET.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.CHART_OF_ACCOUNTS.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.LEDGER.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.CATEGORY.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.SOURCE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.CURRENCY_STR.getConstant(),
        ADJUSTMENT_RESERVE_CONSTANTS.JOURNAL_NAME.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.JOURNAL_DESCRIPTION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.REVERSE_JOURNAL.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.REVERSAL_PERIOD_DATE_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.LINE_DESCRIPTION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC1_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC2_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC3_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC4_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC5_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC6_STR.getConstant()};

    public static final String[] ADJUSTMENT_RESERVE_MASSUPDATE_HEADER = new String[]{"Adjustment Type", "Adjustment Level", "Account Category", "Account Type", "Account",
        "Account Description", "Account Indictor",
        "Debit Indicator", "Credit Indicator", "Division", "Cost Center",
        "Project", "Future 1", "Future 2", "Balance Type", "Database", "Data Access Set", "Chart Of Accounts",
        "Ledger", "Category", "Source", "Currency", "Journal Name", "Journal Description", "Reverse Journal",
        "Reversal Period/Date", "Line Description", "UDC 1", "UDC 2", "UDC 3", "UDC 4", "UDC 5", "UDC 6"};

    public static final Object[] ADJUSTMENT_CONFIG_COLUMN = new String[]{"transactionName", "transactionDesc", "methodology", "redemptionPeriod", "createdDate",
        "createdBy", "modifiedDate", "modifiedBy"};
    public static final String[] ADJUSTMENT_CONFIG_HEADER = new String[]{"Adjustment Type Name", "Adjustment Type Desc", "Methodology", "Redemption Period", "Created Date",
        "Created By", "Modified Date", "Modified By"};

    public static final Object[] ADJUSTMENT_RESERVE_MASSUPDATE_COLUMN = new Object[]{
        ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_TYPE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_CATEGORY.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_TYPE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNTDESCRIPTION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNTINDICTOR.getConstant(),
        ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.getConstant(), "division", ADJUSTMENT_RESERVE_CONSTANTS.COST_CENTER.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.PROJECT.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.FUTURE1.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.FUTURE2.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.BALANCE_TYPE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.DATABASE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.DATA_ACCESS_SET.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.CHART_OF_ACCOUNTS.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.LEDGER.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.CATEGORY.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.SOURCE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.CURRENCY.getConstant(),
        ADJUSTMENT_RESERVE_CONSTANTS.JOURNAL_NAME.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.JOURNAL_DESCRIPTION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.REVERSE_JOURNAL.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.REVERSAL_PERIOD_DATE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.LINE_DESCRIPTION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC1.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC2.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC3.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC4.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC5.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC6.getConstant()};
    public static final String[] ADJUSTMENT_RESERVE_ADD_HEADERS_GTN_DETAILS = {"", "Adjustment Type", "Adjustment Level", "Account Category", "Account Type",
        "Account", "Account Description", "Account Indicator",
        "GL Company No", "Division", "Business Unit",
        "Cost Center", "Project", "Future 1", "Future 2",
        "UDC 1", "UDC 2", "UDC 3", "UDC 4", "UDC 5", "UDC 6"};
    public static final Object[] ADJUSTMENT_RESERVE_ADD_COLUMNS_GTN_DETAILS = {ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), "adjustmentType", "adjustmentLevel", "accountCategory", "accountType",
        "account", "accountDescription", "accountIndictor",
        ADJUSTMENT_RESERVE_CONSTANTS.COMPANYNO.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.DIVISION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.BUSINESS_UNIT.getConstant(),
        "costCenter", "project", "future1", "future2",
        "UDC1", "UDC2", "UDC3", "UDC4", "UDC5", "UDC6"};
    public static final Object[] ADJUSTMENT_RESERVE_FOR_EXCEL_COLUMNS_GTN_DETAILS = {ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_TYPE_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_CATEGORY_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_TYPE_STR.getConstant(),
        "account", "accountDescription", ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_INDICTOR_STR.getConstant(),
        ADJUSTMENT_RESERVE_CONSTANTS.COMPANYNO.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.DIVISION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.BUSINESS_UNIT.getConstant(),
        "costCenter", "project", "future1", "future2",
        ADJUSTMENT_RESERVE_CONSTANTS.UDC1_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC2_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC3_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC4_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC5_STR.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.UDC6_STR.getConstant()};
    public static final String[] ADJUSTMENT_GTN_DETAILS_Header = {"Adjustment Type", "Adjustment Level", "Account Category", "Account Type",
        "Account", "Account Description", "Account Indictor", "Division",
        "Cost Center", "Project", "Future 1", "Future 2",
        "UDC 1", "UDC 2", "UDC 3", "UDC 4", "UDC 5", "UDC 6"};
    public static final Object[] ADJUSTMENT_GTN_DETAILS_COLUMNS = {"adjustmentType", "adjustmentLevel", "accountCategory", "accountType",
        "account", "accountDescription", "accountIndictor", "division",
        "costCenter", "project", "future1", "future2",
        "UDC1", "UDC2", "UDC3", "UDC4", "UDC5", "UDC6"};
    public static final Object[] FIRST_ROW_LEFT_COLUMNS = {"month"};
    public static final String[] FIRST_ROW_LEFT_HEADERS = {""};
    public static final Object[] SECOND_ROW_LEFT_COLUMNS = {"checkRecord", "month"};
    public static final String[] SECOND_ROW_LEFT_HEADERS = {"", "Month"};
    public static final String[] FIRST_ROW_INVENTORY_RATE_RIGHT_HEADERS = {"Inventory", "Rates"};
    public static final Object[] FIRST_ROW_INVENTORY_RATE_RIGHT_COLUMNS = {"inventory", "rates"};

    public static final String[] FIRST_ROW_Trx6_RATE_RIGHT_HEADERS = {"Inventory"};
    public static final Object[] FIRST_ROW_Trx6_RATE_RIGHT_COLUMNS = {"inventory"};

    public static final String[] FIRST_ROW_Trx7_RATE_RIGHT_HEADERS = {"Sales"};
    public static final Object[] FIRST_ROW_Trx7_RATE_RIGHT_COLUMNS = {"sales"};

    public static final String[] SECOND_ROW_INVENTORY_RATE_RIGHT_HEADERS = {"Inventory Details", "Inventory Customer", "Inventory Calculation", "Price", "Reserve Date", "Rate Basis", "Rate Frequency", "Rate Period"};
    public static final Object[] SECOND_ROW_INVENTORY_RATE_RIGHT_COLUMNS = {"inventoryDetails", "inventoryCustomer", "inventoryCalculation", "price", "reserveDate", "rateBasis", "rateFrequency", "ratePeriod"};

    public static final String[] SECOND_ROW_SALES_RATE_RIGHT_HEADERS = {"Date Type", "Price", "Exclusion Details", "Rate Basis", "Rate Frequency", "Rate Period"};
    public static final Object[] SECOND_ROW_SALES_RATE_RIGHT_COLUMNS = {"dateType", "price", "exclusionDetails", "rateBasis", "rateFrequency", "ratePeriod"};

    public static final String[] EXCEL_ROW_SALES_RATE_SINGLE_HEADERS = {"Month", "Date Type", "Price", "Exclusion Details", "Rate Basis", "Rate Frequency", "Rate Period"};
    public static final Object[] EXCEL_ROW_SALES_RATE_SINGLE_COLUMNS = {"excelmonth", "exceldateType", "excelprice", "excelexclusionDetails", "excelrateBasis", "excelrateFrequency", "excelratePeriod"};
    public static final String[] EXCEL_ROW_INVENTORY_RATE_SINGLE_HEADERS = {"Month", "Inventory Details", "Inventory Customer", "Inventory Calculation", "Price", "Reserve Date", "Rate Basis", "Rate Frequency", "Rate Period"};
    public static final Object[] EXCEL_ROW_INVENTORY_RATE_SINGLE_COLUMNS = {"excelmonth", "excelinventoryDetails", "excelinventoryCustomer", "excelinventoryCalculation", "excelprice", "excelreserveDate", "excelrateBasis", "excelrateFrequency", "excelratePeriod"};

    public static final Object[] EXCEL_ROW_Trx6_SINGLE_COLUMNS = {"excelmonth", "excelinventoryDetails", "excelbaseLinePrice", "exceladjustmentPrice"};
    public static final String[] EXCEL_ROW_Trx6_RATE_SINGLE_HEADERS = {"Month", "Inventory Details", "Baseline Price", "Adjusted Price"};

    public static final Object[] EXCEL_ROW_Trx7_SINGLE_COLUMNS = {"excelmonth", "exceldateType", "excelprice", "excelexclusionDetails"};
    public static final String[] EXCEL_ROW_Trx7_RATE_SINGLE_HEADERS = {"Month", "Data Type", "Price", "Exclusion Details"};

    public static final Object[] EXCEL_ROW_INVENTORY_COLUMNS = {"month", "inventoryDetails", "inventoryCustomer", "inventoryCalculation", "price", "reserveDate"};
    public static final Object[] EXCEL_ROW_SALES_COLUMNS = {"month", "dateType", "price", "exclusionDetails"};
    public static final Object[] EXCEL_ROW_RATE_COLUMNS = {"rateBasis", "rateFrequency", "ratePeriod"};

    public static final String[] FIRST_ROW_SALES_RATE_RIGHT_HEADERS = {"Sales", "Rates"};
    public static final Object[] FIRST_ROW_SALES_RATE_RIGHT_COLUMNS = {"sales", "rates"};

    public static final Object[] SECOND_ROW_INVENTORY_COLUMNS = {"inventoryDetails", "inventoryCustomer", "inventoryCalculation", "price", "reserveDate"};
    public static final Object[] SECOND_ROW_SALES_COLUMNS = {"dateType", "price", "exclusionDetails"};
    public static final Object[] SECOND_ROW_RATE_COLUMNS = {"rateBasis", "rateFrequency", "ratePeriod"};

    public static final String[] VIEW_LOOKUP_HEADERS = {"View Name", "Created By", "Created Date"};
    public static final Object[] VIEW_LOOKUP_COLUMNS = {"viewName", "createdBy", "createdDate"};

    public static final String[] INVENTORY_VIEW_LOOKUP_HEADERS = {"View Name", "View Type", "View Category", "Created Date", "Created By", "Modified Date", "Modified By"};
    public static final Object[] INVENTORY_VIEW_LOOKUP_COLUMNS = {"viewName", "viewType", "viewCategory", "createdDate", "createdBy", "modifiedDate", "modifiedBy"};

    public static final String[] PRIVATE_VIEW_LOOKUP_HEADERS = {"View Name", "Adjustment Type", "Description", "Company",
        "Business Unit", "Time Period: From", "Time Period: To", "Deduction Level", "Customer Hierarchy",
        "Customer Relationship", "Customer Level", "Product Hierarchy", "Product Relationship", "Product Level", "Created Date", "Modified Date", "Created By"};
    public static final Object[] PRIVATE_VIEW_LOOKUP_COLUMNS = {"viewName", "adjustmentType", "projectionDescription", "companyName", "bu_CompanyName", "fromPeriod", "toPeriod", "deductionLevels", "customerHierarchyName",
        "customerRelationship", "customerHierarchyLevel", "productHierarchyName", "productRelationship", "productHierarchyLevel", "createdDate", "modifiedDate", "createdByString"};

    public static final String[] PRIVATE_VIEW_LOOKUP_HEADERS_ADJ_SUMM = {"View Name", "Company", "Business Unit", "Time Period: From", "Time Period: To", "Deduction Level", "Customer Hierarchy",
        "Customer Relationship", "Customer Level", "Product Hierarchy", "Product Relationship", "Product Level", "Created Date", "Modified Date", "Created By"};
    public static final Object[] PRIVATE_VIEW_LOOKUP_COLUMNS_ADJ_SUMM = {"viewName", "companyName", "bu_CompanyName", "fromPeriod", "toPeriod", "deductionLevels", "customerHierarchyName",
        "customerRelationship", "customerHierarchyLevel", "productHierarchyName", "productRelationship", "productHierarchyLevel", "createdDate", "modifiedDate", "createdByString"};

    public static final String[] EXCLUSION_LOOKUP_AVIALABLE_HEADERS = {"Available Values"};
    public static final Object[] EXCLUSION_LOOKUP_AVIALABLE_COLUMNS = {"values"};
    public static final String[] EXCLUSION_LOOKUP_SELECTED_HEADERS = {"Excluded Field", "Values"};
    public static final Object[] EXCLUSION_LOOKUP_SELECTED_COLUMNS = {"excludedField", "values"};

    public static final Object[] HIERARCHY_LOOKUP_COLUMNS = {"hierarchyName", "highestLevel", "lowestLevel", "createdDate", "modifiedDate"};
    public static final String[] HIERARCHY_LOOKUP_HEADERS = {"Hierarchy Name", " Highest Level", " Lowest Level", " Created Date", " Modified Date"};

    public static final Object[] CUSTOMER_GROUP_LOOKUP_COLUMNS = {"customerGroupName", "customerGroupNo", "customerGroupDesc"};
    public static final String[] CUSTOMER_GROUP_LOOKUP_HEADERS = {"Customer Group Name", " Customer Group #", "Customer Group desc"};

    public static final Object[] EXCLUSION_RATE_LOOKUP_HEADERS = {"viewName", "createdBy", "createdDate"};
    public static final String[] EXCLUSION_RATE_LOOKUP_COLUMNS = {"View Name", "Created By", " Created Date"};

    public static final Object[] CUSTOMER_GROUP_INVENTORY_COLUMNS = {"customerGroupName", "include", "indicator"};
    public static final String[] CUSTOMER_GROUP_INVENTORY_HEADERS = {"Customer Group Name", "Include", "+/- Indicator"};

    public static final Object[] PIPELINE_ACCRUAL_LEFT_COLUMN = {"group"};

    public static final Object[] ACCOUNT_CONFIG_SEARCH_COLUMNS = {"companyNo", "companyName", "businessUnitNo", "businessUnitName", "account", "costCentre", "brand", "createdBy", "createdDate", "modifiedDate", "source"};
    public static final String[] ACCOUNT_CONFIG_SEARCH_HEADERS = {"Company No", "Company Name", "Business Unit No", "Business Unit Name", "Account", "Cost Center", "Brand", "Created By", "Created Date", "Modified Date", "Source"};

    public static final Object[] ACCOUNT_CONFIG_ADDMODE_COLUMNS = {"checkRecord", "companyNoHelperDto", "companyName", "businessNoHelperDto", "businessUnitName", "account", "brandDdlb", "costCentre", "createdBy", "createdDate", "modifiedDate", "source"};
    public static final String[] ACCOUNT_CONFIG_ADDMODE_HEADERS = {"", "Company No", "Company Name", "Business Unit No", "Business Unit Name", "Account", "Brand", "Cost Center", "Created By", "Created Date", "Modified Date", "Source"};

    public static final Object[] ACCOUNT_CONFIG_EDITMODE_COLUMNS = {"checkRecord", "companyIdWithName", "companyName", "buIdWithName", "businessUnitName", "account", "brandDdlb", "costCentre", "createdBy", "createdDate", "modifiedDate", "source"};
    public static final String[] ACCOUNT_CONFIG_EDITMODE_HEADERS = {"", "Company No", "Company Name", "Business Unit No", "Business Unit Name", "Account", "Brand", "Cost Center", "Created By", "Created Date", "Modified Date", "Source"};

    public static final Object[] ACCOUNT_CONFIG_VIEWMODE_COLUMNS = {"companyIdWithName", "companyName", "buIdWithName", "businessUnitName", "account", "brandWithIdName", "costCentre", "createdBy", "createdDate", "modifiedDate", "source"};
    public static final String[] ACCOUNT_CONFIG_VIEWMODE_HEADERS = {"Company No", "Company Name", "Business Unit No", "Business Unit Name", "Account", "Brand", "Cost Center", "Created By", "Created Date", "Modified Date", "Source"};

    public static final Object[] ACCOUNT_CONFIG_COMBOBOX_PROPERTIES = new Object[]{"companyNoHelperDto", "businessNoHelperDto", "account", "brandDdlb", "costCentre"};
    public static final Object[] ACCOUNT_CONFIG_COMBOBOX = new Object[]{"companyNoHelperDto", "businessNoHelperDto", "brandDdlb", "account"};

    public static final Object[] EXCEL_ACCOUNT_CONFIG_SEARCH_COLUMNS = {"companyIdWithName", "companyName", "buIdWithName", "businessUnitName", "account", "costCentre", "brandWithIdName", "createdBy", "createdDate", "modifiedDate", "source"};
    public static final String[] EXCEL_ACCOUNT_CONFIG_SEARCH_HEADERS = {"Company No", "Company Name", "Business Unit No", "Business Unit Name", "Account", "Cost Center", "Brand", "Created By", "Created Date", "Modified Date", "Source"};

    public static final String[] ACCOUNT_CONFIG_MASS_UPDATE_VALUES = new String[]{"Company No", "Business Unit No", "Account", "Brand", "Cost Center"};

    public static final String CENTER_CHECK = "center-check";
    public static final String Q = "Q";
    public static final String S = "S";
    public static final String CLOSE = "CLOSE";
    public static final String ADD = "ADD";
    public static final String ADD_SMALL = "Add";
    public static final String EDIT = "Edit";
    public static final String COPY = "Copy";
    public static final String DELETE = "Delete";
    public static final String VIEW = "View";
    public final static String BOOTSTRAP = "bootstrap";
    public final static String ZERO_STRING = "0";
    public final static String BOOTSTRAP_FORECAST_BOOTSTRAP_NM = "bootstrap-forecast bootstrap-nm";
    public static final String FILTERCOMBOBOX = "filterComboBox";
    public static final String DB_CURRENT_DATE = "getdate()";
    public static final String PROPERTY_ID = "propertyId";
    public static final String ITEM_ID = "itemId";
    public static final String CONTAINER = "container";
    public static final Resource EXCEL_EXPORT_IMAGE = new ThemeResource("../../icons/excel.png");
    public static final DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat DB_DATE = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat FMT_ID = new SimpleDateFormat("hhmmssms");

    public static final String MM_DD_YYYY = "MM/dd/yyyy";
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd hh:mm:ss.SSS";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String MM_dd_yyyy_hh_mm_ss = "MM/dd/yyyy hh:mm:ss";
    public static final String NULL = "null";
    public static final String CHAR_ASTERISK = "*";
    public static final String CHAR_PERCENT = "%";
    public static final String CHAR_QUS = "?";
    public static final String SPACE = " ";
    public static final String HIPHEN = "-";
    public static final String LEVEL = "Level";
    public static final String PRIMARY = "Primary";
    public static final String SECONDARY = "Secondary";
    public static final String INDICATOR_LEVEL_CUSTOMER = "Customer Level";
    public static final String INDICATOR_LEVEL_CONTRACT = "Contract Level";
    public static final String COMPANY_MASTER = "COMPANY_MASTER";
    public static final String CONTRACT_MASTER = "CONTRACT_MASTER";
    public static final String ITEM_MASTER = "ITEM_MASTER";
    public static final String POSITIVE = "Positive";
    public static final String NEGATIVE = "Negative";
    public static final String[] ADJUSTMENT_RESERVE_TEXT_BOX = new String[]{ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT.toString(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNTDESCRIPTION.toString(), ADJUSTMENT_RESERVE_CONSTANTS.COST_CENTER.toString(), ADJUSTMENT_RESERVE_CONSTANTS.PROJECT.toString(), ADJUSTMENT_RESERVE_CONSTANTS.FUTURE1.toString(), ADJUSTMENT_RESERVE_CONSTANTS.FUTURE2.toString(), ADJUSTMENT_RESERVE_CONSTANTS.BALANCE_TYPE.toString(), ADJUSTMENT_RESERVE_CONSTANTS.DATABASE.toString(), ADJUSTMENT_RESERVE_CONSTANTS.DATA_ACCESS_SET.toString(), ADJUSTMENT_RESERVE_CONSTANTS.CHART_OF_ACCOUNTS.toString(), ADJUSTMENT_RESERVE_CONSTANTS.CATEGORY.toString(), ADJUSTMENT_RESERVE_CONSTANTS.SOURCE.toString(), ADJUSTMENT_RESERVE_CONSTANTS.JOURNAL_NAME.toString(), ADJUSTMENT_RESERVE_CONSTANTS.JOURNAL_DESCRIPTION.toString(), ADJUSTMENT_RESERVE_CONSTANTS.REVERSE_JOURNAL.toString(), ADJUSTMENT_RESERVE_CONSTANTS.LINE_DESCRIPTION.toString(), ADJUSTMENT_RESERVE_CONSTANTS.LEDGER.toString(), ADJUSTMENT_RESERVE_CONSTANTS.DIVISION.toString()};
    public static final String[] ADJUSTMENT_RESERVE_COMBOBOX = new String[]{ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_TYPE.toString(), ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL.toString(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_TYPE.toString(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_CATEGORY.toString(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNTINDICTOR.toString(),
        ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.toString(), ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.toString(), ADJUSTMENT_RESERVE_CONSTANTS.CURRENCY.toString(), ADJUSTMENT_RESERVE_CONSTANTS.REVERSAL_PERIOD_DATE.toString(), ADJUSTMENT_RESERVE_CONSTANTS.UDC1.toString(), ADJUSTMENT_RESERVE_CONSTANTS.UDC2.toString(), ADJUSTMENT_RESERVE_CONSTANTS.UDC3.toString(), ADJUSTMENT_RESERVE_CONSTANTS.UDC4.toString(), ADJUSTMENT_RESERVE_CONSTANTS.UDC5.toString(), ADJUSTMENT_RESERVE_CONSTANTS.UDC6.toString()};
    public static Map<String, String> dropDownMap;
    public static Map<String, String> DBVariableColumnMap;
    public static Map<String, String> udcMap;
    public static Map<String, String> visibleToDBColumnMap;
    public static Map<String, String> visibleToDBColumnMapForConfig;
    private static Map<String, String> listNameMapper = new HashMap<String, String>();
    private static final Map<String, String> VIEW_FILTER_MAP = new HashMap<>();

    /**
     * The Constant BALANCE_SUMMARY_REPORT.
     */
    public static final String BALANCE_SUMMARY_REPORT = "Balance Summary Report";
    public static final String FIXED_DOLLAR_ADJUSTMENT = "Fixed Dollar Adjustment";

    public static final String[] ADJUSTMENT_DEMAND_PIPELINE_RESERVE_VARIABLE_COMBOBOX = new String[]{ARMConstants.getAdjustmentType(), ARMConstants.getBalanceType(), ARMConstants.getDatabase(), ARMConstants.getDataAccessSet(), ARMConstants.getChartOfAccounts(), ARMConstants.getLedger(),
        ARMConstants.getCategory(), ARMConstants.getSource(), ARMConstants.getCurrency(), ARMConstants.getAccountingDate(), ARMConstants.getBatchName(),
        ARMConstants.getJournalName(), ARMConstants.getJournalDescription(), ARMConstants.getReverseJournal(), ARMConstants.getReversalPeriodDate(), ARMConstants.getCompany(), ARMConstants.getDivision(),
        ARMConstants.getBusinessUnit(), ARMConstants.getCostCenter(), ARMConstants.getAccount(), ARMConstants.getBrand(), ARMConstants.getProject(), ARMConstants.getFuture1(),
        ARMConstants.getFuture2(), ARMConstants.getDebit(), ARMConstants.getCredit(), ARMConstants.getLineDescription(), ARMConstants.getUDC1(), ARMConstants.getUDC2(),
        ARMConstants.getUDC3(), ARMConstants.getUDC4(), ARMConstants.getUDC5(), ARMConstants.getUDC6(), ARMConstants.getAccountCategory(), ARMConstants.getAccountType(),
        ARMConstants.getAdjustmentLevel(), ARMConstants.getAccountIndictor(), ARMConstants.getAccountDescription(), ARMConstants.getRedemptionPeriod(), ARMConstants.getCalculation_Period(),
        ARMConstants.getWorkFlowID(), ARMConstants.getWorkFlowName(), ARMConstants.getWorkFlowStatus()
    };

    public static final String[] ADJUSTMENT_DEMAND_PIPELINE_GTN_VARIABLE_COMBOBOX = new String[]{ARMConstants.getAdjustmentType(), ARMConstants.getDeductionType(), ARMConstants.getGLMonth(),
        ARMConstants.getGLYear(), ARMConstants.getGLString(), ARMConstants.getGLCompanyId(), ARMConstants.getDivision(), ARMConstants.getBusinessUnitId(), ARMConstants.getCostCenter(),
        ARMConstants.getAccount(), ARMConstants.getBrandId(), ARMConstants.getProject(), ARMConstants.getFuture1(), ARMConstants.getFuture2(), ARMConstants.getItemNo(),
        ARMConstants.getDeductionAmount(), ARMConstants.getGLCompanyNo(), ARMConstants.getGLCompanyName(), ARMConstants.getBusinessUnitNo(), ARMConstants.getBusinessUnitName(),
        ARMConstants.getGLDate(), ARMConstants.getCreatedDate(), ARMConstants.getRedemptionPeriod(), ARMConstants.getDetailContractId(), ARMConstants.getDetailContractNo(),
        ARMConstants.getDetailContractName(), ARMConstants.getCompanyId(), ARMConstants.getCompanyNo(), ARMConstants.getCompanyName(), ARMConstants.getItemId(), ARMConstants.getItemName(),
        ARMConstants.getBrandName(), ARMConstants.getDeductionId(), ARMConstants.getDeductionNo(), ARMConstants.getDeductionName(), ARMConstants.getDeductionCategory(),
        ARMConstants.getDeductionProgram(), ARMConstants.getDeductionInclusion(), ARMConstants.getDeductionUDC1(), ARMConstants.getDeductionUDC2(), ARMConstants.getDeductionUDC3(),
        ARMConstants.getDeductionUDC4(), ARMConstants.getDeductionUDC5(), ARMConstants.getDeductionUDC6(), ARMConstants.getDeductionRate(), ARMConstants.getUDC1(), ARMConstants.getUDC2(),
        ARMConstants.getUDC3(), ARMConstants.getUDC4(), ARMConstants.getUDC5(), ARMConstants.getUDC6(), ARMConstants.getAccountType(), ARMConstants.getAccountCategory(),
        ARMConstants.getAdjustmentLevel(), ARMConstants.getAccountDescription(), ARMConstants.getAccountIndictor(), ARMConstants.getCalculation_Period(), ARMConstants.getWorkFlowID(),
        ARMConstants.getWorkFlowName(), ARMConstants.getWorkFlowStatus()
    };

    public static final String[] ADJUSTMENT_DEMAND_SUMMARY_RESERVE_VARIABLE_COMBOBOX = new String[]{ARMConstants.getAdjustmentType(), ARMConstants.getBalanceType(), ARMConstants.getDatabase(), ARMConstants.getDataAccessSet(), ARMConstants.getChartOfAccounts(), ARMConstants.getLedger(),
        ARMConstants.getCategory(), ARMConstants.getSource(), ARMConstants.getCurrency(), ARMConstants.getAccountingDate(), ARMConstants.getBatchName(),
        ARMConstants.getJournalName(), ARMConstants.getJournalDescription(), ARMConstants.getReverseJournal(), ARMConstants.getReversalPeriodDate(), ARMConstants.getCompany(), ARMConstants.getDivision(),
        ARMConstants.getBusinessUnit(), ARMConstants.getCostCenter(), ARMConstants.getAccount(), ARMConstants.getBrand(), ARMConstants.getProject(), ARMConstants.getFuture1(),
        ARMConstants.getFuture2(), ARMConstants.getDebit(), ARMConstants.getCredit(), ARMConstants.getLineDescription(), ARMConstants.getUDC1(), ARMConstants.getUDC2(),
        ARMConstants.getUDC3(), ARMConstants.getUDC4(), ARMConstants.getUDC5(), ARMConstants.getUDC6(), ARMConstants.getAccountCategory(), ARMConstants.getAccountType(),
        ARMConstants.getAdjustmentLevel(), ARMConstants.getAccountIndictor(), ARMConstants.getAccountDescription(), ARMConstants.getRedemptionPeriod(), ARMConstants.getCalculation_Period(),
        ARMConstants.getWorkFlowID(), ARMConstants.getWorkFlowName(), ARMConstants.getWorkFlowStatus()};

    public static final String[] ADJUSTMENT_DEMAND_SUMMARY_GTN_VARIABLE_COMBOBOX = new String[]{ARMConstants.getAdjustmentType(), ARMConstants.getDeductionType(), ARMConstants.getGLMonth(),
        ARMConstants.getGLYear(), ARMConstants.getGLString(), ARMConstants.getGLCompanyId(), ARMConstants.getDivision(), ARMConstants.getBusinessUnitId(), ARMConstants.getCostCenter(),
        ARMConstants.getAccount(), ARMConstants.getBrandId(), ARMConstants.getProject(), ARMConstants.getFuture1(), ARMConstants.getFuture2(), ARMConstants.getItemNo(),
        ARMConstants.getDeductionAmount(), ARMConstants.getGLCompanyNo(), ARMConstants.getGLCompanyName(), ARMConstants.getBusinessUnitNo(), ARMConstants.getBusinessUnitName(),
        ARMConstants.getGLDate(), ARMConstants.getCreatedDate(), ARMConstants.getRedemptionPeriod(), ARMConstants.getDetailContractId(), ARMConstants.getDetailContractNo(),
        ARMConstants.getDetailContractName(), ARMConstants.getCompanyId(), ARMConstants.getCompanyNo(), ARMConstants.getCompanyName(), ARMConstants.getItemId(), ARMConstants.getItemName(),
        ARMConstants.getBrandName(), ARMConstants.getDeductionId(), ARMConstants.getDeductionNo(), ARMConstants.getDeductionName(), ARMConstants.getDeductionCategory(),
        ARMConstants.getDeductionProgram(), ARMConstants.getDeductionInclusion(), ARMConstants.getDeductionUDC1(), ARMConstants.getDeductionUDC2(), ARMConstants.getDeductionUDC3(),
        ARMConstants.getDeductionUDC4(), ARMConstants.getDeductionUDC5(), ARMConstants.getDeductionUDC6(), ARMConstants.getDeductionRate(), ARMConstants.getUDC1(), ARMConstants.getUDC2(),
        ARMConstants.getUDC3(), ARMConstants.getUDC4(), ARMConstants.getUDC5(), ARMConstants.getUDC6(), ARMConstants.getAccountType(), ARMConstants.getAccountCategory(),
        ARMConstants.getAdjustmentLevel(), ARMConstants.getAccountDescription(), ARMConstants.getAccountIndictor(), ARMConstants.getCalculation_Period(), ARMConstants.getWorkFlowID(),
        ARMConstants.getWorkFlowName(), ARMConstants.getWorkFlowStatus()};

    public static final Object[] ADJUSTMENT_RESERVE_ADD_COLUMNS_RESERVE_DETAILS_FOR_EXCEL = new Object[]{"adjustmentType_str", "adjustmentLevel_str", "accountCategory_str", "accountType_str", ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNTDESCRIPTION.getConstant(), "accountIndictor_str", "debitIndicator_str", "creditIndicator_str", ADJUSTMENT_RESERVE_CONSTANTS.COMPANYNO.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.DIVISION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.BUSINESS_UNIT.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.COST_CENTER.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.PROJECT.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.FUTURE1.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.FUTURE2.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.BALANCE_TYPE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.DATABASE.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.DATA_ACCESS_SET.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.CHART_OF_ACCOUNTS.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.LEDGER.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.CATEGORY.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.SOURCE.getConstant(), "currency_str",
        ADJUSTMENT_RESERVE_CONSTANTS.JOURNAL_NAME.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.JOURNAL_DESCRIPTION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.REVERSE_JOURNAL.getConstant(), "reversalPeriodDate_str", ADJUSTMENT_RESERVE_CONSTANTS.LINE_DESCRIPTION.getConstant(), "UDC1_str", "UDC2_str", "UDC3_str", "UDC4_str", "UDC5_str", "UDC6_str"};

    public static final Object[] ADJUSTMENT_RESERVE_ADD_COLUMNS_GTN_DETAILS_FOR_EXCEL = {"adjustmentType_str", "adjustmentLevel_str", "accountCategory_str", "accountType_str",
        "account", "accountDescription", "accountIndictor_str",
        ADJUSTMENT_RESERVE_CONSTANTS.COMPANYNO.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.DIVISION.getConstant(), ADJUSTMENT_RESERVE_CONSTANTS.BUSINESS_UNIT.getConstant(),
        "costCenter", "project", "future1", "future2",
        "UDC1_str", "UDC2_str", "UDC3_str", "UDC4_str", "UDC5_str", "UDC6_str"};

    public static Object getDBColumnForAdjustmnetType() {
        if (DBVariableColumnMap == null) {
            DBVariableColumnMap = new HashMap<>();
            DBVariableColumnMap.put("Demand  Accrual", "Sum(PIPELINE_ACCRUAL)           AS PIPELINE_ACCRUAL");
            DBVariableColumnMap.put("Demand Payment Reconciliation True-Up", "Sum(DEMAND_ACCRUAL)             AS DEMAND_ACCRUAL");
            DBVariableColumnMap.put("Demand Reforecast True-Up", "Sum(PIPELINE_INENTORY_TRUE_UP)  AS PIPELINE_INENTORY_TRUE_UP");
            DBVariableColumnMap.put("Pipeline Accrual", " Sum(DEMAND_PAYMENT_TRUE_UP)     AS DEMAND_PAYMENT_TRUE_UP");
            DBVariableColumnMap.put("Pipeline Inventory True-Up", "Sum(DEMAND_ACCRUAL_RE_FORECAST) AS DEMAND_ACCRUAL_RE_FORECAST ");
        }
        return DBVariableColumnMap;
    }

    public static enum ADJUSTMENT_RESERVE_CONSTANTS {

        CHECK_RECORD("checkRecord"),
        ADJUSTMENT_TYPE("adjustmentType"),
        ADJUSTMENT_LEVEL("adjustmentLevel"),
        ACCOUNT_CATEGORY("accountCategory"),
        ACCOUNT_TYPE("accountType"),
        ACCOUNT("account"),
        ACCOUNTDESCRIPTION("accountDescription"),
        ACCOUNTINDICTOR("accountIndictor"),
        DEBIT_INDICATOR("debitIndicator"),
        CREDIT_INDICATOR("creditIndicator"),
        COMPANYNO("companyNo"),
        DIVISION("division"),
        BUSINESS_UNIT("businessUnit"),
        COST_CENTER("costCenter"),
        PROJECT("project"),
        FUTURE1("future1"),
        FUTURE2("future2"),
        BALANCE_TYPE("balanceType"),
        DATABASE("database"),
        DATA_ACCESS_SET("dataAccessSet"),
        CHART_OF_ACCOUNTS("chartOfAccounts"),
        LEDGER("ledger"),
        CATEGORY("category"),
        SOURCE("source"),
        CURRENCY("currency"),
        JOURNAL_NAME("journalName"),
        JOURNAL_DESCRIPTION("journalDescription"),
        REVERSE_JOURNAL("reverseJournal"),
        REVERSAL_PERIOD_DATE("reversalPeriodDate"),
        LINE_DESCRIPTION("lineDescription"),
        UDC1("UDC1"),
        UDC2("UDC2"),
        UDC3("UDC3"),
        UDC4("UDC4"),
        UDC5("UDC5"),
        UDC6("UDC6"),
        ADJUSTMENT_TYPE_STR("adjustmentType_str"),
        ADJUSTMENT_LEVEL_STR("adjustmentType_str"),
        ACCOUNT_CATEGORY_STR("accountCategory_str"),
        ACCOUNT_TYPE_STR("accountType_str"),
        ACCOUNT_INDICTOR_STR("accountIndictor_str"),
        CURRENCY_STR("currency_str"),
        REVERSAL_PERIOD_DATE_STR("reversalPeriodDate_str"),
        UDC1_STR("UDC1_str"),
        UDC2_STR("UDC2_str"),
        UDC3_STR("UDC3_str"),
        UDC4_STR("UDC4_str"),
        UDC5_STR("UDC5_str"),
        UDC6_STR("UDC6_str"),
        GL_COMPANY_NO("gLCompanyNo"),
        COMPANY_NAME("companyName"),
        BUSINESS_UNIT_NAME("businessUnitName"),
        DEDUCTION_CATEGORY("deductionCategory"),
        DEDUCTION_TYPE("deductionType"),
        DEDUCTION_PROGRAM("deductionProgram"),
        CREATED_BY("createdBy"),
        CREATED_DATE("createdDate"),
        MODIFIED_DATE("modifiedDate"),
        BUSINESS_UNIT_NO("businessUnitNo");

        /* The constant */
        private String constant;

        /**
         * Instantiates a new indicator constants.
         *
         * @param constant the constant
         */
        private ADJUSTMENT_RESERVE_CONSTANTS(String constant) {
            this.constant = constant;
        }

        /**
         * Gets the constant.
         *
         * @return the constant
         */
        public String getConstant() {
            return constant;
        }

        /**
         * To string.
         *
         * @return the string
         */
        @Override
        public String toString() {
            return constant;
        }
    }

    public static enum SalesVariables {

        TOTAL_UNITS("Total Units"),
        TOTAL_SALES("Total Sales"),
        EXCLUDED_UNITS("Excluded Units"),
        EXCLUDED_SALES("Excluded Sales"),
        NET_UNITS("Net Units"),
        NET_SALES("Net Sales"),
        PRICE("Price"),
        PRICE_OVERRIDE("Price Override"),
        NET_CALCULATED_SALES("Net Calculated Sales"),
        SALES_VARIANCE("Sales Variance"),
        SALES_VARIANCE_PER("Sales Variance %"),
        ST_ARM_PIPELINE_SALES("ST_ARM_PIPELINE_SALES"),
        ST_ARM_PIPELINE_RATE("ST_ARM_PIPELINE_RATE"),
        st_ARM_PIPELINE_EXCLUSION_DETAILS("ST_ARM_PIPELINE_EXCLUSION_DETAILS");
        private String constant;

        private SalesVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(SalesVariables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

    }

    public static enum ADJUSTMENT_CONFIG_CONSTANTS {

        TRANSACTION_NAME("transactionName", "TRANSACTION_NAME"),
        TRANSACTION_DESC("transactionDesc", "TRANSACTION_DESC"),
        METHODOLOGY("methodology", "METHODOLGY"),
        REDEMPTION_PERIOD("redemptionPeriod", "REDEMPTION_PERIOD"),
        CREATED_DATE("createdDate", "AC.CREATED_DATE"),
        CREATED_BY("createdBy", "(crt.lastName+' '+crt.firstName)"),
        MODIFIED_DATE("modifiedDate", "AC.MODIFIED_DATE"),
        MODIFIED_BY("modifiedBy", "(mod.lastName+' '+mod.firstName)");
        private String constant;
        private String DB_COLUMN;

        private ADJUSTMENT_CONFIG_CONSTANTS(String constant, String dbColumn) {
            this.DB_COLUMN = dbColumn;
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public String getPropertyId() {
            return constant;
        }

        public String getDBColumn() {
            return DB_COLUMN;
        }

        public static String[] names() {
            return Arrays.toString(SalesVariables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }
    }

    public static final String[] MONTHS = new DateFormatSymbols().getMonths();

    public static Map<String, String> getDropDownMap() {
        if (dropDownMap == null) {
            dropDownMap = new HashMap<>();
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_TYPE.getConstant(), "ARM_ADJUSTMENT_TYPE");
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL.getConstant(), "ARM_ADJUSTMENT_LEVEL");
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_CATEGORY.getConstant(), "ACCOUNT_CATEGORY");
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_TYPE.getConstant(), "ARM_ACCOUNT_TYPE");
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNTINDICTOR.getConstant(), "ACCOUNT_INDICTOR");
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.getConstant(), "CRDIT_DEBIT_INDICATOR");
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.getConstant(), "CRDIT_DEBIT_INDICATOR");
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.CURRENCY.getConstant(), "CURRENCY");
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.REVERSAL_PERIOD_DATE.getConstant(), "REVERSAL_PERIOD");
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.UDC1.getConstant(), "ARM_UDC_1");
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.UDC2.getConstant(), "ARM_UDC_2");
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.UDC3.getConstant(), "ARM_UDC_3");
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.UDC4.getConstant(), "ARM_UDC_4");
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.UDC5.getConstant(), "ARM_UDC_5");
            dropDownMap.put(ADJUSTMENT_RESERVE_CONSTANTS.UDC6.getConstant(), "ARM_UDC_6");
        }
        return dropDownMap;
    }

    public static Map<String, String> getUDCMap() {
        if (udcMap == null) {
            udcMap = new HashMap<>();
            udcMap.put("UDC1", "UDC1");
            udcMap.put("UDC2", "UDC2");
            udcMap.put("UDC3", "UDC3");
            udcMap.put("UDC4", "UDC4");
            udcMap.put("UDC5", "UDC5");
            udcMap.put("UDC6", "UDC6");
        }
        return udcMap;
    }

    public static Map<String, String> getVisibleToDBColumnMap() {
        if (visibleToDBColumnMap == null) {
            visibleToDBColumnMap = new HashMap<>();
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), "CHECK_RECORD");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_TYPE.getConstant(), "ADJUSTMENT_TYPE");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL.getConstant(), "ADJUSTMENT_LEVEL");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_CATEGORY.getConstant(), "ACCOUNT_CATEGORY");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_TYPE.getConstant(), "ACCOUNT_TYPE");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNTINDICTOR.getConstant(), "ACCOUNT_INDICATOR");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.CURRENCY.getConstant(), "CURRENCY");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT.getConstant(), "ACCOUNT");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNTDESCRIPTION.getConstant(), "ACCOUNT_DESC");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.BUSINESS_UNIT.getConstant(), "REVERSAL_PERIOD");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.COST_CENTER.getConstant(), "COST_CENTER");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.DIVISION.getConstant(), "DIVISION");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.PROJECT.getConstant(), "PROJECT");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.FUTURE1.getConstant(), "FUTURE_1");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.FUTURE2.getConstant(), "FUTURE_2");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.BALANCE_TYPE.getConstant(), "BALANCE_TYPE");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.DATABASE.getConstant(), "[DATABASE]");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.DATA_ACCESS_SET.getConstant(), "DATA_ACCESS_SET");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.CHART_OF_ACCOUNTS.getConstant(), "CHART_OF_ACCOUNTS");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.CATEGORY.getConstant(), "CATEGORY");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.SOURCE.getConstant(), "SOURCE");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.JOURNAL_NAME.getConstant(), "JOURNAL_NAME");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.JOURNAL_DESCRIPTION.getConstant(), "JOURNAL_DESCRIPTION");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.REVERSE_JOURNAL.getConstant(), "REVERSE_JOURNAL");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.LINE_DESCRIPTION.getConstant(), "LINE_DESCRIPTION");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.UDC1.getConstant(), "UDC_1");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.UDC2.getConstant(), "UDC_2");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.UDC3.getConstant(), "UDC_3");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.UDC4.getConstant(), "UDC_4");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.UDC5.getConstant(), "UDC_5");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.UDC6.getConstant(), "UDC_6");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.LEDGER.getConstant(), "LEDGER");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.getConstant(), "DEBIT");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.getConstant(), "CREDIT");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.REVERSAL_PERIOD_DATE.getConstant(), "REVERSAL_PERIOD_DATE");
            //Adjustment reserve config. landing screen sorting
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.COMPANYNO.getConstant(), "cm.COMPANY_NO");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.COMPANY_NAME.getConstant(), "cm.COMPANY_NAME");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.BUSINESS_UNIT_NO.getConstant(), "bu.COMPANY_NO");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.BUSINESS_UNIT_NAME.getConstant(), "bu.COMPANY_NAME");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.BUSINESS_UNIT_NAME.getConstant(), "bu.COMPANY_NAME");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.DEDUCTION_CATEGORY.getConstant(), "rs_c.DESCRIPTION");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.DEDUCTION_TYPE.getConstant(), "rs_t.DESCRIPTION");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.DEDUCTION_PROGRAM.getConstant(), "RS_P.DESCRIPTION");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.CREATED_BY.getConstant(), "adj.CREATED_BY");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.CREATED_DATE.getConstant(), "adj.CREATED_DATE");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.MODIFIED_DATE.getConstant(), "adj.MODIFIED_DATE");
            visibleToDBColumnMap.put(ADJUSTMENT_RESERVE_CONSTANTS.CREATED_DATE.getConstant(), "adj.CREATED_DATE");
        }
        return visibleToDBColumnMap;
    }

    public static Map<String, String> getVisibleToDBColumnMapForConfig() {
        if (visibleToDBColumnMapForConfig == null) {
            visibleToDBColumnMapForConfig = new HashMap<>();
            visibleToDBColumnMapForConfig.put(ADJUSTMENT_CONFIG_CONSTANTS.TRANSACTION_NAME.getPropertyId(), ADJUSTMENT_CONFIG_CONSTANTS.TRANSACTION_NAME.getDBColumn());
            visibleToDBColumnMapForConfig.put(ADJUSTMENT_CONFIG_CONSTANTS.TRANSACTION_DESC.getPropertyId(), ADJUSTMENT_CONFIG_CONSTANTS.TRANSACTION_DESC.getDBColumn());
            visibleToDBColumnMapForConfig.put(ADJUSTMENT_CONFIG_CONSTANTS.METHODOLOGY.getPropertyId(), ADJUSTMENT_CONFIG_CONSTANTS.METHODOLOGY.getDBColumn());
            visibleToDBColumnMapForConfig.put(ADJUSTMENT_CONFIG_CONSTANTS.REDEMPTION_PERIOD.getPropertyId(), ADJUSTMENT_CONFIG_CONSTANTS.REDEMPTION_PERIOD.getDBColumn());
            visibleToDBColumnMapForConfig.put(ADJUSTMENT_CONFIG_CONSTANTS.CREATED_DATE.getPropertyId(), ADJUSTMENT_CONFIG_CONSTANTS.CREATED_DATE.getDBColumn());
            visibleToDBColumnMapForConfig.put(ADJUSTMENT_CONFIG_CONSTANTS.CREATED_BY.getPropertyId(), ADJUSTMENT_CONFIG_CONSTANTS.CREATED_BY.getDBColumn());
            visibleToDBColumnMapForConfig.put(ADJUSTMENT_CONFIG_CONSTANTS.MODIFIED_DATE.getPropertyId(), ADJUSTMENT_CONFIG_CONSTANTS.MODIFIED_DATE.getDBColumn());
            visibleToDBColumnMapForConfig.put(ADJUSTMENT_CONFIG_CONSTANTS.MODIFIED_BY.getPropertyId(), ADJUSTMENT_CONFIG_CONSTANTS.MODIFIED_BY.getDBColumn());
        }
        return visibleToDBColumnMapForConfig;
    }

    public static final String COMPANY_SMALL = "Company";
    public static final String CUSTOMER_SMALL = "Customer";
    public static final String INDICATOR_LEVEL_NDC = "NDC Level";
    public static final String TRADING_PARTNER = "Trading Partner";
    public static final String CONTRACT_SMALL = "Contract";
    public static final String NDC = "NDC";
    public static final String REGEX_EXTRACT_DIGITS = "\\D+";
    public static final String HIERARCHYDEFINITIONSID = "hierarchyDefinitionSid";
    public static final String LEVELNAME = "levelName";
    public static final String SCREEN_NAME = "screenName";
    public static final String FIELD_NAME = "fieldName";
    public static final String TRUE = "true";
    public static final String PRODUCT = "Product";
    public static final String CUSTOMERORPRODUCT = "Customer/Product";
    public static final String CUSTOMERORPRODUCT_COLUMN = "group";
    public static final String INDICATOR = "indicator";
    public static final String DOT = ".";
    public static final String HIERARACHY_NO_PROPERTY = "hierarchyNo";

    public static Map listNameMapper() {
        if (listNameMapper.isEmpty()) {
            listNameMapper.put(VariableConstants.INVENTORY_CUSTOMER, "ARM_INVENTORY_CUSTOMER");
            listNameMapper.put(VariableConstants.PRICE, "ARM_PERIOD_BASIS");
            listNameMapper.put(VariableConstants.RESERVE_DATE, "ARM_PERIOD_BASIS");
            listNameMapper.put(VariableConstants.RATE_BASIS, "ARM_RATE_BASIS");
            listNameMapper.put(VariableConstants.RATE_FREQUENCY, "PAYMENT_FREQUENCY");
            listNameMapper.put(VariableConstants.RATE_PERIOD, "ARM_PERIOD_BASIS");
            listNameMapper.put(VariableConstants.DATE_TYPE, "ARM_DATE_TYPE");
            listNameMapper.put(VariableConstants.INVENTORY_DETAILS, "ARM_INVENTORY_DETAILS");
            listNameMapper.put(VariableConstants.ADJUSTED_PRICE, "ARM_PERIOD_BASIS");
            listNameMapper.put(VariableConstants.BASELINE_PRICE, "ARM_PERIOD_BASIS");
        }
        return listNameMapper;
    }

    public final static String FILTER_TABLE = "filtertable";
    public static final String BOOTSTRAP_UI = "bootstrap-ui";
    public static final String BOOTSTRAP_NM = "bootstrap-nm";
    public static final int ZERO = 0;
    public static final String PORTLET_NAME = "PortelName";
    public static final String USER_ID = "userId";
    public static final String MM = "MM";
    public static final String PRIVATE_VIEW = "Private View";
    public static final String PUBLIC_VIEW = "Public View";
    public static final String PRIVATE = "Private";
    public static final String PUBLIC = "Public";
    public static final String SAVE = "save";
    public static final String UPDATE = "update";
    public static final String PROJECTION_ID = "projectionId";
    public static final String HIERARCHY_NO = "hierarchyNo";
    public static final String COMMA = ",";
    public static final String HORIZONTAL = "horizontal";
    public static final String FAIL = "fail";
    public static final String STRING_ONE = "1";
    public static String COUNTER_VALUE = "counterValue";
    //Added for workflow
    public static final String WORKFLOW_NOT_SAVED = "Not Saved";
    public static final String WORKFLOW_STATUS = "WorkflowStatus";
    public static final String BUSINESS_PROCESS_TYPE_ARM = "ARM";
    public static final String WORKFLOW_APPROVAL = "Interface - Workflow Approval";
    /**
     * The success.
     */
    public static final String SUCCESS = "success";
    /**
     * The projection master sid.
     */
    public static final String PROJECTION_MASTER_SID = "projectionMasterSid";
    public static final String R = "R";
    public static final String INDICATOR_LOGIC_CUSTOMER_HIERARCHY = "C";
    /**
     * The description.
     */
    public static final String DESCRIPTION = "description";
    public static final String LIST_NAME = "listName";
    public static final String SEARCH_TEXT = "searchText";
    public static final String NOTES = "Notes";
    public static final String FILTERBAR = "filterbar";
    public static final String SPECIAL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";
    public static final String EQUAL = "=";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String VIEW_SMALL = "view";
    public static final String EDIT_SMALL = "edit";
    public static final String SUBMITTED = "Submitted";
    private static final List<String> salesVariablesList = new ArrayList<>();
    private static final List<String> trx7salesVariablesList = new ArrayList<>();
    /**
     * The yes.
     */
    public static final String YES = "YES";

    public static enum frequencyVarables {

        MONTHLY("Monthly"),
        QUARTERLY("Quarterly"),
        SEMI_ANNUALLY("Semi-Annually"),
        ANNUALLY("Annually");
        private String constant;

        private frequencyVarables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

    }

    public static enum levelVariablesVarables {

        CUSTOMER("Customer"),
        CONTRACT("Contract"),
        BRAND("Brand"),
        DEDUCTION("Deduction"),
        DEDUCTION_PROGRAM("DEDUCTION PROGRAM"),
        DEDUCTION_TYPE("DEDUCTION TYPE"),
        DEDUCTION_CATEGORY("DEDUCTION CATEGORY"),
        DEDUCTION_CATEGORY2("DEDUCTION CATEGORY 2"),
        DEDUCTION_CATEGORY3("DEDUCTION CATEGORY 3"),
        DEDUCTION_CATEGORY4("DEDUCTION CATEGORY 4"),
        DEDUCTION_CATEGORY5("DEDUCTION CATEGORY 5"),
        DEDUCTION_CATEGORY6("DEDUCTION CATEGORY 6"),
        ITEM("Product");

        private String constant;

        private levelVariablesVarables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

    }

    public static List<String> getSalesVariables() {
        if (salesVariablesList.isEmpty()) {
            salesVariablesList.addAll(Arrays.asList(VariableConstants.VARIABLE_SALES_VISIBLE_COLUMN));
            salesVariablesList.add(VariableConstants.GROUP);
            salesVariablesList.add(VariableConstants.PRODUCT_NAME);
            salesVariablesList.add(VariableConstants.PRODUCT_MASTER_SID);
            salesVariablesList.add(VariableConstants.CHILDREN_ALLOWED);
        }
        return salesVariablesList;
    }
    private static final Map<Integer, String> SUMMARY_LEVEL = new HashMap();
    private static final Map<Integer, String> SUMMARY_LEVEL_DEDUCTION_PRODUCT = new HashMap();
    private static final Map<Integer, String> SUMMARY_LEVEL_DEDUCTION_CUSTOMER = new HashMap();
    private static final Map<Integer, String> DEMAND_SUMMARY_LEVEL = new HashMap();
    private static final Map<Integer, String> PIPELINE_SUMMARY_LEVEL = new HashMap();
    private static final Map<Integer, String> TRX7_SUMMARY_LEVEL = new HashMap();
    private static final Map<String, Map<Integer, String>> ADJ_SUMMARY_LEVEL = new HashMap();
    private static final Map<String, String> VIEW_NAME = new HashMap();
    private static final TreeMap<String, Integer> MASTERSIDS = new TreeMap<>();
    private static final TreeMap<String, Integer> MASTERSIDSFORTRX6 = new TreeMap<>();
    private static final Map<String, String> DEDUCTION_VALUES = new HashMap();
    private static final Map<String, String> DEDUCTION_VALUES_LEVELS = new HashMap();
    private static final Map<String, String> DEDUCTION_VALUES_MULTIPLE_PERIOD = new HashMap();
    private static final Map<String, String> DBColumnMapInventoryTrx6 = new HashMap();
    private static final Map<String, String> SUMMARY_VIEW_TYPE = new HashMap();
    private static final Map<String, Map<Integer, String>> LEVEL_FILTER_NAME = new HashMap();
    private static final Map<String, Map<Integer, String>> TRX7_LEVEL_FILTER_NAME = new HashMap();
    private static final Map<String, Map<Integer, String>> PIPELINE_LEVEL_FILTER_NAME = new HashMap();
    private static final Map<String, Map<Integer, String>> LEVEL_FILTER_NAME_MULTI_PERIOD = new HashMap();
    private static final Map<String, String> LEVEL_EXCEL_QUERY_NAME = new HashMap();
    private static final Map<String, String> DEDUCTION_LEVEL_QUERY_NAME = new HashMap();
    private static final Map<String, String[]> LEVEL_MAP = new HashMap<>();
    private static final Map<String, String> PROFILE_MAP = new HashMap<>();

    public static Map<Integer, String> getSummaryLevel() {

        if (SUMMARY_LEVEL.isEmpty()) {
            SUMMARY_LEVEL.put(1, levelVariablesVarables.DEDUCTION.toString());
            SUMMARY_LEVEL.put(NumericConstants.TWO, levelVariablesVarables.CONTRACT.toString());
            SUMMARY_LEVEL.put(NumericConstants.THREE, levelVariablesVarables.CUSTOMER.toString());
            SUMMARY_LEVEL.put(NumericConstants.FOUR, levelVariablesVarables.BRAND.toString());
            SUMMARY_LEVEL.put(NumericConstants.FIVE, levelVariablesVarables.ITEM.toString());
        }
        return SUMMARY_LEVEL;
    }

    public static Map<Integer, String> getSummaryDeductionProductLevel() {

        if (SUMMARY_LEVEL_DEDUCTION_PRODUCT.isEmpty()) {
            SUMMARY_LEVEL_DEDUCTION_PRODUCT.put(1, levelVariablesVarables.BRAND.toString());
            SUMMARY_LEVEL_DEDUCTION_PRODUCT.put(NumericConstants.TWO, levelVariablesVarables.ITEM.toString());
        }
        return SUMMARY_LEVEL_DEDUCTION_PRODUCT;
    }

    public static Map<Integer, String> getSummaryDeductionCustomerLevel() {

        if (SUMMARY_LEVEL_DEDUCTION_CUSTOMER.isEmpty()) {
            SUMMARY_LEVEL_DEDUCTION_CUSTOMER.put(1, levelVariablesVarables.CUSTOMER.toString());
            SUMMARY_LEVEL_DEDUCTION_CUSTOMER.put(NumericConstants.TWO, levelVariablesVarables.BRAND.toString());
            SUMMARY_LEVEL_DEDUCTION_CUSTOMER.put(NumericConstants.THREE, levelVariablesVarables.ITEM.toString());
        }
        return SUMMARY_LEVEL_DEDUCTION_CUSTOMER;
    }

    public static Map<Integer, String> getDemandSummaryLevel_singlePeriod() {

        if (DEMAND_SUMMARY_LEVEL.isEmpty()) {
            DEMAND_SUMMARY_LEVEL.put(1, levelVariablesVarables.DEDUCTION.toString());
            DEMAND_SUMMARY_LEVEL.put(NumericConstants.TWO, levelVariablesVarables.CUSTOMER.toString());
            DEMAND_SUMMARY_LEVEL.put(NumericConstants.THREE, levelVariablesVarables.CONTRACT.toString());
            DEMAND_SUMMARY_LEVEL.put(NumericConstants.FOUR, levelVariablesVarables.BRAND.toString());
            DEMAND_SUMMARY_LEVEL.put(NumericConstants.FIVE, levelVariablesVarables.ITEM.toString());
        }
        return DEMAND_SUMMARY_LEVEL;
    }

    public static Map<Integer, String> getPipelineSummaryLevel_singlePeriod() {

        if (PIPELINE_SUMMARY_LEVEL.isEmpty()) {
            PIPELINE_SUMMARY_LEVEL.put(1, levelVariablesVarables.CUSTOMER.toString());
            PIPELINE_SUMMARY_LEVEL.put(NumericConstants.TWO, levelVariablesVarables.CONTRACT.toString());
            PIPELINE_SUMMARY_LEVEL.put(NumericConstants.THREE, levelVariablesVarables.BRAND.toString());
            PIPELINE_SUMMARY_LEVEL.put(NumericConstants.FOUR, levelVariablesVarables.ITEM.toString());
        }
        return PIPELINE_SUMMARY_LEVEL;
    }

    public static Map<Integer, String> getTrx7SummaryLevel_singlePeriod() {

        if (TRX7_SUMMARY_LEVEL.isEmpty()) {
            TRX7_SUMMARY_LEVEL.put(1, levelVariablesVarables.CONTRACT.toString());
            TRX7_SUMMARY_LEVEL.put(NumericConstants.TWO, levelVariablesVarables.CUSTOMER.toString());
            TRX7_SUMMARY_LEVEL.put(NumericConstants.THREE, levelVariablesVarables.BRAND.toString());
            TRX7_SUMMARY_LEVEL.put(NumericConstants.FOUR, levelVariablesVarables.ITEM.toString());
        }
        return TRX7_SUMMARY_LEVEL;
    }

    public static Map<String, String> getViewName() {

        if (VIEW_NAME.isEmpty()) {
            VIEW_NAME.put(ARMConstants.getDeductionProduct(), "Brand");
            VIEW_NAME.put(ARMConstants.getDeductionCustomer(), "Customer");
            VIEW_NAME.put(ARMConstants.getDeductionCustomerContract(), "Customer");
            VIEW_NAME.put("Deduction", "Deduction");
            VIEW_NAME.put("Deduction Contract", "Contract");
        }
        return VIEW_NAME;
    }
    private static final Map<String, String> deductionLevelMap = new HashMap<>();

    public static Map<String, String> loadDedutionMap() {
        if (deductionLevelMap.isEmpty()) {
            deductionLevelMap.put(ARMConstants.getDeductionCategory(), "A.RS_CATEGORY,1");
            deductionLevelMap.put(ARMConstants.getDeductionType(), "A.RS_TYPE,1");
            deductionLevelMap.put(ARMConstants.getDeductionProgram(), "A.REBATE_PROGRAM_TYPE,1");
            deductionLevelMap.put(ARMConstants.getDeduction(), "A.RS_ID,1");
            deductionLevelMap.put(ARMConstants.getDeductionCategory2(), "U.UDC2,1");
            deductionLevelMap.put(ARMConstants.getDeductionCategory3(), "U.UDC3,1");
            deductionLevelMap.put(ARMConstants.getDeductionCategory4(), "U.UDC4,1");
            deductionLevelMap.put(ARMConstants.getDeductionCategory5(), "U.UDC5,1");
            deductionLevelMap.put(ARMConstants.getDeductionCategory6(), "U.UDC6,1");
        }
        return deductionLevelMap;
    }

    public static Map<Integer, String> getADJ_SummaryLevel(String value) {

        if (ADJ_SUMMARY_LEVEL.isEmpty()) {
            Map<Integer, String> summary_level_Brand = new HashMap();
            Map<Integer, String> summary_level_Customer_ded = new HashMap();
            Map<Integer, String> summary_level_dedution_Customer = new HashMap();
            Map<Integer, String> summary_level_dedution_Contract = new HashMap();
            summary_level_Brand.put(1, levelVariablesVarables.DEDUCTION.toString());
            summary_level_Brand.put(NumericConstants.TWO, levelVariablesVarables.BRAND.toString());
            summary_level_Brand.put(NumericConstants.THREE, levelVariablesVarables.ITEM.toString());

            summary_level_Customer_ded.put(1, levelVariablesVarables.CUSTOMER.toString());
            summary_level_Customer_ded.put(NumericConstants.TWO, levelVariablesVarables.DEDUCTION.toString());
            summary_level_Customer_ded.put(NumericConstants.THREE, levelVariablesVarables.BRAND.toString());
            summary_level_Customer_ded.put(NumericConstants.FOUR, levelVariablesVarables.ITEM.toString());

            summary_level_dedution_Customer.put(1, levelVariablesVarables.DEDUCTION.toString());
            summary_level_dedution_Customer.put(NumericConstants.TWO, levelVariablesVarables.CUSTOMER.toString());
            summary_level_dedution_Customer.put(NumericConstants.THREE, levelVariablesVarables.BRAND.toString());
            summary_level_dedution_Customer.put(NumericConstants.FOUR, levelVariablesVarables.ITEM.toString());

            summary_level_dedution_Contract.put(1, levelVariablesVarables.DEDUCTION.toString());
            summary_level_dedution_Contract.put(NumericConstants.TWO, levelVariablesVarables.CUSTOMER.toString());
            summary_level_dedution_Contract.put(NumericConstants.THREE, levelVariablesVarables.CONTRACT.toString());
            summary_level_dedution_Contract.put(NumericConstants.FOUR, levelVariablesVarables.BRAND.toString());
            summary_level_dedution_Contract.put(NumericConstants.FIVE, levelVariablesVarables.ITEM.toString());

            ADJ_SUMMARY_LEVEL.put(ARMConstants.getDeductionProduct(), summary_level_Brand);
            ADJ_SUMMARY_LEVEL.put(ARMConstants.getCustomerDedection(), summary_level_Customer_ded);
            ADJ_SUMMARY_LEVEL.put(ARMConstants.getDeductionCustomer(), summary_level_dedution_Customer);
            ADJ_SUMMARY_LEVEL.put(ARMConstants.getDeductionCustomerContract(), summary_level_dedution_Contract);
        }
        return ADJ_SUMMARY_LEVEL.get(value);
    }

    public static Map<Integer, String> getLevelAndLevelFilterMultiPeriod(String value) {

        if (LEVEL_FILTER_NAME_MULTI_PERIOD.isEmpty()) {
            Map<Integer, String> summary_level_Brand = new HashMap();
            Map<Integer, String> summary_level_Customer_ded = new HashMap();
            Map<Integer, String> summary_level_dedution_Customer = new HashMap();
            Map<Integer, String> summary_level_dedution_Contract = new HashMap();
            Map<Integer, String> trx6_Levels = new HashMap();

            summary_level_Brand.put(1, levelVariablesVarables.DEDUCTION.toString());
            summary_level_Brand.put(NumericConstants.TWO, levelVariablesVarables.BRAND.toString());
            summary_level_Brand.put(NumericConstants.THREE, levelVariablesVarables.ITEM.toString());

            summary_level_Customer_ded.put(1, levelVariablesVarables.CUSTOMER.toString());
            summary_level_Customer_ded.put(NumericConstants.TWO, levelVariablesVarables.DEDUCTION.toString());
            summary_level_Customer_ded.put(NumericConstants.THREE, levelVariablesVarables.BRAND.toString());
            summary_level_Customer_ded.put(NumericConstants.FOUR, levelVariablesVarables.ITEM.toString());

            summary_level_dedution_Customer.put(1, levelVariablesVarables.DEDUCTION.toString());
            summary_level_dedution_Customer.put(NumericConstants.TWO, levelVariablesVarables.CUSTOMER.toString());
            summary_level_dedution_Customer.put(NumericConstants.THREE, levelVariablesVarables.BRAND.toString());
            summary_level_dedution_Customer.put(NumericConstants.FOUR, levelVariablesVarables.ITEM.toString());

            summary_level_dedution_Contract.put(1, levelVariablesVarables.DEDUCTION.toString());
            summary_level_dedution_Contract.put(NumericConstants.TWO, levelVariablesVarables.CUSTOMER.toString());
            summary_level_dedution_Contract.put(NumericConstants.THREE, levelVariablesVarables.CONTRACT.toString());
            summary_level_dedution_Contract.put(NumericConstants.FOUR, levelVariablesVarables.BRAND.toString());
            summary_level_dedution_Contract.put(NumericConstants.FIVE, levelVariablesVarables.ITEM.toString());

            trx6_Levels.put(1, levelVariablesVarables.CUSTOMER.toString());
            trx6_Levels.put(NumericConstants.TWO, levelVariablesVarables.BRAND.toString());
            trx6_Levels.put(NumericConstants.THREE, levelVariablesVarables.ITEM.toString());

            LEVEL_FILTER_NAME_MULTI_PERIOD.put(ARMConstants.getDeductionProduct(), summary_level_Brand);
            LEVEL_FILTER_NAME_MULTI_PERIOD.put(ARMConstants.getCustomerDedection(), summary_level_Customer_ded);
            LEVEL_FILTER_NAME_MULTI_PERIOD.put(ARMConstants.getDeductionCustomer(), summary_level_dedution_Customer);
            LEVEL_FILTER_NAME_MULTI_PERIOD.put(ARMConstants.getDeductionCustomerContract(), summary_level_dedution_Contract);
            LEVEL_FILTER_NAME_MULTI_PERIOD.put("Trx6_Inventory", trx6_Levels);
        }
        return LEVEL_FILTER_NAME_MULTI_PERIOD.get(value);
    }

    public static String getLevelExcelQueryName(String value) {
        if (LEVEL_EXCEL_QUERY_NAME.isEmpty()) {

            LEVEL_EXCEL_QUERY_NAME.put(levelVariablesVarables.BRAND.toString().toUpperCase(), "B");
            LEVEL_EXCEL_QUERY_NAME.put(levelVariablesVarables.ITEM.toString().toUpperCase(), "I");
            LEVEL_EXCEL_QUERY_NAME.put(levelVariablesVarables.DEDUCTION.toString().toUpperCase(), "D");
            LEVEL_EXCEL_QUERY_NAME.put(levelVariablesVarables.DEDUCTION_CATEGORY.toString().toUpperCase(), "DC");
            LEVEL_EXCEL_QUERY_NAME.put(levelVariablesVarables.DEDUCTION_CATEGORY2.toString().toUpperCase(), "DC2");
            LEVEL_EXCEL_QUERY_NAME.put(levelVariablesVarables.DEDUCTION_CATEGORY3.toString().toUpperCase(), "DC3");
            LEVEL_EXCEL_QUERY_NAME.put(levelVariablesVarables.DEDUCTION_CATEGORY4.toString().toUpperCase(), "DC4");
            LEVEL_EXCEL_QUERY_NAME.put(levelVariablesVarables.DEDUCTION_CATEGORY5.toString().toUpperCase(), "DC5");
            LEVEL_EXCEL_QUERY_NAME.put(levelVariablesVarables.DEDUCTION_CATEGORY6.toString().toUpperCase(), "DC6");
            LEVEL_EXCEL_QUERY_NAME.put(levelVariablesVarables.DEDUCTION_PROGRAM.toString().toUpperCase(), "DP");
            LEVEL_EXCEL_QUERY_NAME.put(levelVariablesVarables.DEDUCTION_TYPE.toString().toUpperCase(), "DT");
            LEVEL_EXCEL_QUERY_NAME.put(levelVariablesVarables.CUSTOMER.toString().toUpperCase(), "T");
            LEVEL_EXCEL_QUERY_NAME.put(levelVariablesVarables.CONTRACT.toString().toUpperCase(), "C");
        }
        return LEVEL_EXCEL_QUERY_NAME.get(value.toUpperCase());
    }

    public static String getDeductionLevelQueryName(String value) {
        if (DEDUCTION_LEVEL_QUERY_NAME.isEmpty()) {
            DEDUCTION_LEVEL_QUERY_NAME.put(ARMConstants.getDeduction(), ARMConstants.getDeduction());
            DEDUCTION_LEVEL_QUERY_NAME.put(ARMConstants.getDeductionCategory(), ARMConstants.getDeductionCategory());
            DEDUCTION_LEVEL_QUERY_NAME.put(ARMConstants.getDeductionCategory2(), ARMConstants.getDeductionCategory2());
            DEDUCTION_LEVEL_QUERY_NAME.put(ARMConstants.getDeductionCategory3(), ARMConstants.getDeductionCategory3());
            DEDUCTION_LEVEL_QUERY_NAME.put(ARMConstants.getDeductionCategory4(), ARMConstants.getDeductionCategory4());
            DEDUCTION_LEVEL_QUERY_NAME.put(ARMConstants.getDeductionCategory5(), ARMConstants.getDeductionCategory5());
            DEDUCTION_LEVEL_QUERY_NAME.put(ARMConstants.getDeductionCategory6(), ARMConstants.getDeductionCategory6());
            DEDUCTION_LEVEL_QUERY_NAME.put(ARMConstants.getDeductionProgram(), ARMConstants.getDeductionProgram());
            DEDUCTION_LEVEL_QUERY_NAME.put(ARMConstants.getDeductionType(), ARMConstants.getDeductionType());
        }
        return DEDUCTION_LEVEL_QUERY_NAME.get(value);
    }

    public static Map<Integer, String> getLevelAndLevelFilter(String value) {

        if (LEVEL_FILTER_NAME.isEmpty()) {
            Map<Integer, String> summary_level_Brand = new HashMap();
            Map<Integer, String> summary_level_Customer_ded = new HashMap();
            Map<Integer, String> summary_level_dedution_Customer = new HashMap();
            Map<Integer, String> summary_level_dedution_Contract = new HashMap();
            Map<Integer, String> summary_level_dedutionContract = new HashMap();
            Map<Integer, String> summary_level_non_dedution_Contract = new HashMap();

            summary_level_Brand.put(1, levelVariablesVarables.BRAND.toString());
            summary_level_Brand.put(NumericConstants.TWO, levelVariablesVarables.ITEM.toString());

            summary_level_Customer_ded.put(1, levelVariablesVarables.CUSTOMER.toString());
            summary_level_Customer_ded.put(NumericConstants.TWO, levelVariablesVarables.DEDUCTION.toString());
            summary_level_Customer_ded.put(NumericConstants.THREE, levelVariablesVarables.BRAND.toString());
            summary_level_Customer_ded.put(NumericConstants.FOUR, levelVariablesVarables.ITEM.toString());

            summary_level_dedution_Customer.put(1, levelVariablesVarables.CUSTOMER.toString());
            summary_level_dedution_Customer.put(NumericConstants.TWO, levelVariablesVarables.BRAND.toString());
            summary_level_dedution_Customer.put(NumericConstants.THREE, levelVariablesVarables.ITEM.toString());

            summary_level_dedutionContract.put(1, levelVariablesVarables.CONTRACT.toString());
            summary_level_dedutionContract.put(NumericConstants.TWO, levelVariablesVarables.CUSTOMER.toString());
            summary_level_dedutionContract.put(NumericConstants.THREE, levelVariablesVarables.ITEM.toString());

            summary_level_dedution_Contract.put(1, levelVariablesVarables.DEDUCTION.toString());
            summary_level_dedution_Contract.put(NumericConstants.TWO, levelVariablesVarables.CUSTOMER.toString());
            summary_level_dedution_Contract.put(NumericConstants.THREE, levelVariablesVarables.CONTRACT.toString());
            summary_level_dedution_Contract.put(NumericConstants.FOUR, levelVariablesVarables.BRAND.toString());
            summary_level_dedution_Contract.put(NumericConstants.FIVE, levelVariablesVarables.ITEM.toString());

            summary_level_non_dedution_Contract.put(1, levelVariablesVarables.CUSTOMER.toString());
            summary_level_non_dedution_Contract.put(NumericConstants.TWO, levelVariablesVarables.CONTRACT.toString());
            summary_level_non_dedution_Contract.put(NumericConstants.THREE, levelVariablesVarables.BRAND.toString());
            summary_level_non_dedution_Contract.put(NumericConstants.FOUR, levelVariablesVarables.ITEM.toString());

            LEVEL_FILTER_NAME.put(ARMConstants.getDeductionProduct(), summary_level_Brand);
            LEVEL_FILTER_NAME.put(ARMConstants.getCustomerDedection(), summary_level_Customer_ded);
            LEVEL_FILTER_NAME.put(ARMConstants.getDeductionCustomer(), summary_level_dedution_Customer);
            LEVEL_FILTER_NAME.put(ARMConstants.getDeductionCustomerContract(), summary_level_dedution_Contract);
            LEVEL_FILTER_NAME.put("Deduction Contract", summary_level_dedutionContract);
            LEVEL_FILTER_NAME.put("non" + ARMConstants.getDeductionCustomerContract(), summary_level_non_dedution_Contract);
        }
        return LEVEL_FILTER_NAME.get(value);
    }

    public static Map<Integer, String> getPipeLineLevelAndLevelFilter(String value) {

        if (PIPELINE_LEVEL_FILTER_NAME.isEmpty()) {
            Map<Integer, String> summary_level_Brand = new HashMap();
            Map<Integer, String> summary_level_Customer_ded = new HashMap();
            Map<Integer, String> summary_level_dedution_Customer = new HashMap();
            Map<Integer, String> summary_level_dedution_Contract = new HashMap();
            Map<Integer, String> summary_level_non_dedution_Contract = new HashMap();
            summary_level_Brand.put(1, levelVariablesVarables.BRAND.toString());
            summary_level_Brand.put(NumericConstants.TWO, levelVariablesVarables.ITEM.toString());

            summary_level_Customer_ded.put(1, levelVariablesVarables.CUSTOMER.toString());
            summary_level_Customer_ded.put(NumericConstants.TWO, levelVariablesVarables.DEDUCTION.toString());
            summary_level_Customer_ded.put(NumericConstants.THREE, levelVariablesVarables.BRAND.toString());
            summary_level_Customer_ded.put(NumericConstants.FOUR, levelVariablesVarables.ITEM.toString());

            summary_level_dedution_Customer.put(1, levelVariablesVarables.CUSTOMER.toString());
            summary_level_dedution_Customer.put(NumericConstants.TWO, levelVariablesVarables.BRAND.toString());
            summary_level_dedution_Customer.put(NumericConstants.THREE, levelVariablesVarables.ITEM.toString());

            summary_level_dedution_Contract.put(1, levelVariablesVarables.DEDUCTION.toString());
            summary_level_dedution_Contract.put(NumericConstants.TWO, levelVariablesVarables.CUSTOMER.toString());
            summary_level_dedution_Contract.put(NumericConstants.THREE, levelVariablesVarables.CONTRACT.toString());
            summary_level_dedution_Contract.put(NumericConstants.FOUR, levelVariablesVarables.BRAND.toString());
            summary_level_dedution_Contract.put(NumericConstants.FIVE, levelVariablesVarables.ITEM.toString());

            summary_level_non_dedution_Contract.put(1, levelVariablesVarables.CUSTOMER.toString());
            summary_level_non_dedution_Contract.put(NumericConstants.TWO, levelVariablesVarables.CONTRACT.toString());
            summary_level_non_dedution_Contract.put(NumericConstants.THREE, levelVariablesVarables.BRAND.toString());
            summary_level_non_dedution_Contract.put(NumericConstants.FOUR, levelVariablesVarables.ITEM.toString());

            PIPELINE_LEVEL_FILTER_NAME.put(ARMConstants.getDeductionProduct(), summary_level_Brand);
            PIPELINE_LEVEL_FILTER_NAME.put(ARMConstants.getCustomerDedection(), summary_level_Customer_ded);
            PIPELINE_LEVEL_FILTER_NAME.put(ARMConstants.getDeductionCustomer(), summary_level_dedution_Customer);
            PIPELINE_LEVEL_FILTER_NAME.put(ARMConstants.getDeductionCustomerContract(), summary_level_dedution_Contract);
            PIPELINE_LEVEL_FILTER_NAME.put("non" + ARMConstants.getDeductionCustomerContract(), summary_level_non_dedution_Contract);
        }
        return PIPELINE_LEVEL_FILTER_NAME.get(value);
    }

    public static TreeMap<String, Integer> getMasterIdsMap() {
        MASTERSIDS.put(levelVariablesVarables.DEDUCTION.toString(), null);
        MASTERSIDS.put(levelVariablesVarables.CONTRACT.toString(), null);
        MASTERSIDS.put(levelVariablesVarables.CUSTOMER.toString(), null);
        MASTERSIDS.put(levelVariablesVarables.BRAND.toString(), null);
        MASTERSIDS.put(levelVariablesVarables.ITEM.toString(), null);
        return MASTERSIDS;
    }

    public static TreeMap<String, Integer> getMasterIdsMapForTrx6() {
        MASTERSIDSFORTRX6.put(levelVariablesVarables.CUSTOMER.toString(), null);
        MASTERSIDSFORTRX6.put(levelVariablesVarables.BRAND.toString(), null);
        MASTERSIDSFORTRX6.put(levelVariablesVarables.ITEM.toString(), null);
        return MASTERSIDSFORTRX6;
    }

    public static Map<String, String> getDeductionValuesMap() {
        DEDUCTION_VALUES.put(ARMConstants.getDeduction(), " B.RS_MODEL_SID ");
        DEDUCTION_VALUES.put(ARMConstants.getDeductionCategory(), " B.RS_CATEGORY_SID ");
        DEDUCTION_VALUES.put(" Deduction Category 1 ", " B.UDC1_SID ");
        DEDUCTION_VALUES.put(ARMConstants.getDeductionCategory2(), " B.UDC2_SID ");
        DEDUCTION_VALUES.put(ARMConstants.getDeductionCategory3(), " B.UDC3_SID ");
        DEDUCTION_VALUES.put(ARMConstants.getDeductionCategory4(), " B.UDC4_SID ");
        DEDUCTION_VALUES.put(ARMConstants.getDeductionCategory5(), " B.UDC5_SID ");
        DEDUCTION_VALUES.put(ARMConstants.getDeductionCategory6(), " B.UDC6_SID ");
        DEDUCTION_VALUES.put(ARMConstants.getDeductionProgram(), " B.REBATE_PROGRAM_TYPE_SID ");
        DEDUCTION_VALUES.put(ARMConstants.getDeductionType(), " B.RS_TYPE_SID ");
        return DEDUCTION_VALUES;
    }

    public static Map<String, String> getDeductionValuesMapForLevel() {
        DEDUCTION_VALUES_LEVELS.put(ARMConstants.getDeduction(), " B.RS_MODEL_SID ");
        DEDUCTION_VALUES_LEVELS.put(ARMConstants.getDeductionCategory(), "  B.RS_CATEGORY ");
        DEDUCTION_VALUES_LEVELS.put(" Deduction Category 1 ", " U.UDC1 ");
        DEDUCTION_VALUES_LEVELS.put(ARMConstants.getDeductionCategory2(), " U.UDC2");
        DEDUCTION_VALUES_LEVELS.put(ARMConstants.getDeductionCategory3(), " U.UDC3");
        DEDUCTION_VALUES_LEVELS.put(ARMConstants.getDeductionCategory4(), " U.UDC4");
        DEDUCTION_VALUES_LEVELS.put(ARMConstants.getDeductionCategory5(), " U.UDC5");
        DEDUCTION_VALUES_LEVELS.put(ARMConstants.getDeductionCategory6(), " U.UDC6");
        DEDUCTION_VALUES_LEVELS.put(ARMConstants.getDeductionProgram(), " B.REBATE_PROGRAM_TYPE ");
        DEDUCTION_VALUES_LEVELS.put(ARMConstants.getDeductionType(), " B.RS_TYPE ");
        return DEDUCTION_VALUES_LEVELS;
    }

    public static Map<String, String> getDeductionValuesMapForMultiplePeriod() {
        DEDUCTION_VALUES_MULTIPLE_PERIOD.put(ARMConstants.getDeduction(), " R.RS_MODEL_SID ");
        DEDUCTION_VALUES_MULTIPLE_PERIOD.put(ARMConstants.getDeductionCategory(), "  R.RS_CATEGORY ");
        DEDUCTION_VALUES_MULTIPLE_PERIOD.put(" Deduction Category 1 ", " U.UDC1 ");
        DEDUCTION_VALUES_MULTIPLE_PERIOD.put(ARMConstants.getDeductionCategory2(), " U.UDC2");
        DEDUCTION_VALUES_MULTIPLE_PERIOD.put(ARMConstants.getDeductionCategory3(), " U.UDC3");
        DEDUCTION_VALUES_MULTIPLE_PERIOD.put(ARMConstants.getDeductionCategory4(), " U.UDC4");
        DEDUCTION_VALUES_MULTIPLE_PERIOD.put(ARMConstants.getDeductionCategory5(), " U.UDC5");
        DEDUCTION_VALUES_MULTIPLE_PERIOD.put(ARMConstants.getDeductionCategory6(), " U.UDC6");
        DEDUCTION_VALUES_MULTIPLE_PERIOD.put(ARMConstants.getDeductionProgram(), " R.REBATE_PROGRAM_TYPE ");
        DEDUCTION_VALUES_MULTIPLE_PERIOD.put(ARMConstants.getDeductionType(), " R.RS_TYPE ");
        return DEDUCTION_VALUES_MULTIPLE_PERIOD;
    }

    public static String getSummaryViewType(String value) {
        SUMMARY_VIEW_TYPE.put(ARMConstants.getDeductionProduct(), "DEDUCTION_PRODUCT");
        SUMMARY_VIEW_TYPE.put(ARMConstants.getCustomerDedection(), "CUSTOMER_DEDUCTION");
        SUMMARY_VIEW_TYPE.put(ARMConstants.getDeductionCustomer(), "DEDUCTION_CUSTOMER");
        SUMMARY_VIEW_TYPE.put(ARMConstants.getDeductionCustomerContract(), "DEDUCTION_CUSTOMER_CONTRACT");
        SUMMARY_VIEW_TYPE.put("Deduction Contract", "DEDUCTION_CONTRACT");

        return SUMMARY_VIEW_TYPE.get(value);
    }

    public static Map<String, String> loadViewFilterMap() {
        if (VIEW_FILTER_MAP.isEmpty()) {
            VIEW_FILTER_MAP.put("viewName", "FVM.VIEW_NAME");
            VIEW_FILTER_MAP.put("adjustmentType", "PM.FORECASTING_TYPE");
            VIEW_FILTER_MAP.put("projectionDescription", "PM.PROJECTION_DESCRIPTION");
            VIEW_FILTER_MAP.put("companyName", "CM.COMPANY_NAME");

            VIEW_FILTER_MAP.put("bu_CompanyName", "CM_BU.COMPANY_NAME");
            VIEW_FILTER_MAP.put("fromPeriod", "PM.FROM_DATE");
            VIEW_FILTER_MAP.put("toPeriod", "PM.TO_DATE");
            VIEW_FILTER_MAP.put("deductionLevels", "AM.DEDUCTION_LEVEL");
            VIEW_FILTER_MAP.put("customerHierarchyName", "HD1.HIERARCHY_NAME");
            VIEW_FILTER_MAP.put("customerRelationship", "CUST_RB.RELATIONSHIP_NAME");
            VIEW_FILTER_MAP.put("customerHierarchyLevel", "PM.CUSTOMER_HIERARCHY_LEVEL");
            VIEW_FILTER_MAP.put("productHierarchyName", "HD2.HIERARCHY_NAME");
            VIEW_FILTER_MAP.put("productRelationship", "PROD_RB.RELATIONSHIP_NAME");
            VIEW_FILTER_MAP.put("productHierarchyLevel", "PM.PRODUCT_HIERARCHY_LEVEL");
            VIEW_FILTER_MAP.put("createdDateString", "FVM.CREATED_DATE");
            VIEW_FILTER_MAP.put("createdDate", "FVM.CREATED_DATE");
            VIEW_FILTER_MAP.put("modifiedDate", "FVM.MODIFIED_DATE");
            VIEW_FILTER_MAP.put("createdByString", "FVM.CREATED_BY");
        }
        return VIEW_FILTER_MAP;
    }

    public static String[] getLevelMap(String levelName) {
        if (LEVEL_MAP.isEmpty()) {
            LEVEL_MAP.put(ARMConstants.getDeductionCategory(), new String[]{"A.RS_CATEGORY,H1.DESCRIPTION AS CATEGORY", "CATEGORY"});
            LEVEL_MAP.put(ARMConstants.getDeductionType(), new String[]{"A.RS_TYPE,H2.DESCRIPTION AS TYPE", "TYPE"});
            LEVEL_MAP.put(ARMConstants.getDeductionProgram(), new String[]{"A.REBATE_PROGRAM_TYPE,H3.DESCRIPTION AS PROGRAM_TYPE", "PROGRAM_TYPE"});
            LEVEL_MAP.put(ARMConstants.getDeductionCategory2(), new String[]{"U.UDC2,H5.DESCRIPTION AS UDC", "UDC", "UDC2"});
            LEVEL_MAP.put(ARMConstants.getDeductionCategory3(), new String[]{"U.UDC3,H6.DESCRIPTION AS UDC", "UDC", "UDC3"});
            LEVEL_MAP.put(ARMConstants.getDeductionCategory4(), new String[]{"U.UDC4,H7.DESCRIPTION AS UDC", "UDC", "UDC4"});
            LEVEL_MAP.put(ARMConstants.getDeductionCategory5(), new String[]{"U.UDC5,H8.DESCRIPTION AS UDC", "UDC", "UDC5"});
            LEVEL_MAP.put(ARMConstants.getDeductionCategory6(), new String[]{"U.UDC6,H9.DESCRIPTION AS UDC", "UDC", "UDC6"});
            LEVEL_MAP.put(ARMConstants.getDeduction(), new String[]{"A.RS_MODEL_SID,A.RS_ID + '- ' + A.RS_NAME AS RS_MODEL_ID", "RS_MODEL_ID"});
        }
        return LEVEL_MAP.get(levelName);
    }

    public static Map<String, String> loadProfileFilterMap() {
        if (PROFILE_MAP.isEmpty()) {
            PROFILE_MAP.put("profileName", "PROFILE_NAME");
            PROFILE_MAP.put("profileDesc", "PROFILE_DESCRIPTION");
        }
        return PROFILE_MAP;
    }
    public static String TOTAL = "Total";
    public static String DEMAND_ACCRUAL_EDIT_TABLE = "ST_ARM_DEMAND_ADJ_SUMMARY";
    public static String DEMAND_ACCRUAL_VIEW_TABLE = "ARM_DEMAND_ADJ_SUMMARY";
    private static final Map<Integer, String> DEDUCTION_LEVEL_MAP = new HashMap<>();

    public static Map<Integer, String> deductionLevelMap() {
        if (DEDUCTION_LEVEL_MAP.isEmpty()) {
            DEDUCTION_LEVEL_MAP.put(1, ARMConstants.getDeductionCategory());
            DEDUCTION_LEVEL_MAP.put(NumericConstants.TWO, ARMConstants.getDeductionType());
            DEDUCTION_LEVEL_MAP.put(NumericConstants.THREE, ARMConstants.getDeductionProgram());
            DEDUCTION_LEVEL_MAP.put(NumericConstants.FOUR, ARMConstants.getDeductionCategory2());
            DEDUCTION_LEVEL_MAP.put(NumericConstants.FIVE, ARMConstants.getDeductionCategory3());
            DEDUCTION_LEVEL_MAP.put(NumericConstants.SIX, ARMConstants.getDeductionCategory4());
            DEDUCTION_LEVEL_MAP.put(NumericConstants.SEVEN, ARMConstants.getDeductionCategory5());
            DEDUCTION_LEVEL_MAP.put(NumericConstants.EIGHT, ARMConstants.getDeductionCategory6());
            DEDUCTION_LEVEL_MAP.put(NumericConstants.NINE, ARMConstants.getDeduction());
        }
        return DEDUCTION_LEVEL_MAP;
    }

    public static enum Trx6_Variables {

        TOTAL_INVENTORY("Total Inventory", "TotalInventory"),
        BASELINE_PRICE("Baseline Price", "BaselinePrice"),
        BASELINE_PRICE_OVERRIDE("Baseline Price Override", "BaselinePriceOverride"),
        BASELINE_CALCULATED_AMOUNT("Baseline Calculated Amount", "BaselineCalculatedAmount"),
        ADJUSTED_PRICE("Adjusted Price", "AdjustedPrice"),
        ADJUSTED_PRICE_OVERRIDE("Adjusted Price Override", "AdjustedPriceOverride"),
        ADJUSTED_CALCULATED_AMOUNT("Adjusted Calculated Amount", "AdjustedCalculatedAmount"),
        PRICE_CHANGE("Price Change", "PriceChange"),
        PRICE_CHANGE_PERCENT("Price Change %", "PriceChangePercent"),
        NET_CALCULATED_AMOUNT("Net Calculated Amount", "NetCalculatedAmount"),
        INFLATION_FACTOR("Inflation Factor", "InflationFactor"),
        INFLATION_ADJUSTMENT("Inflation Adjustment", "InflationsAdjustment");
        private String constant;
        private String column;

        private Trx6_Variables(String constant, String column) {
            this.constant = constant;
            this.column = column;

        }

        @Override
        public String toString() {
            return constant;
        }

        public String getColumn() {
            return column;
        }

        public static String[] names() {
            return Arrays.toString(Trx6_Variables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

    }

    public static final String[] VARIABLE_VISIBLE_COLUMN_TRX6 = new String[]{"currentBalance", "calculatedAdjustment", "adjustmentRatio", "variance"};

    public static enum Trx6_Inventory_Variables {

        CURRENT_BALANCE("currentBalance", "Current Balance "),
        CALCULATED_ADJUSTMENT("calculatedAdjustment", "Calculated Adjustment"),
        VARIANCE("variance", "Variance"),
        OVERRIDE("override", "Override"),
        ADJUSTMENT("adjustment", "Adjustment"),
        ADJUSTMENT_RATIO("adjustmentRatio", "Adjustment Ratio");
        private String constant;
        private String header;

        private Trx6_Inventory_Variables(String constant, String header) {
            this.constant = constant;
            this.header = header;
        }

        @Override
        public String toString() {
            return constant;
        }

        public String getHeader() {
            return header;
        }

        public static String[] names() {
            return Arrays.toString(Trx6_Variables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

    }

    public static Map<String, String> getDB_ColumnMap_For_Inventoy_Trx6() {
        DBColumnMapInventoryTrx6.put(Trx6_Variables.BASELINE_PRICE_OVERRIDE.getColumn(), "BASELINE_PRICE_OVERRIDE");
        DBColumnMapInventoryTrx6.put(Trx6_Variables.ADJUSTED_PRICE_OVERRIDE.getColumn(), "ADJUSTED_PRICE_OVERRIDE");
        DBColumnMapInventoryTrx6.put(Trx6_Variables.INFLATION_FACTOR.getColumn(), "INFLATION_FACTOR");
        return DBColumnMapInventoryTrx6;
    }

    public static String getFormattedValue(Object value, DecimalFormat format) {
        return format.format(Double.valueOf(value.toString()));
    }

    public static String[] VARIABLE_SUMMARY_HEADER = {ARMUtils.Trx6_Inventory_Variables.CURRENT_BALANCE.getHeader(), ARMUtils.Trx6_Inventory_Variables.CALCULATED_ADJUSTMENT.getHeader(), ARMUtils.Trx6_Inventory_Variables.ADJUSTMENT_RATIO.getHeader(), ARMUtils.Trx6_Inventory_Variables.VARIANCE.getHeader(),
        ARMUtils.Trx6_Inventory_Variables.OVERRIDE.getHeader(), ARMUtils.Trx6_Inventory_Variables.ADJUSTMENT.getHeader()};
    public static String[] VARIABLE_INVENTORY_VISIBLE_COLUMN = {"TotalInventory", "BaselinePrice", "BaselinePriceOverride", "BaselineCalculatedAmount", "AdjustedPrice", "AdjustedPriceOverride", "AdjustedCalculatedAmount", "PriceChange", "PriceChangePercent", "NetCalculatedAmount", "InflationFactor", "InflationsAdjustment"};
    public static final String[] VARIABLE_VISIBLE_COLUMN_TRX6_DECUTION = new String[]{"currentBalance", "calculatedAdjustment", "adjustmentRatio", "variance", "override", "adjustment"};

    public static final String[] FIRST_ROW_INVENTORY_RATE_RIGHT_HEADERS_FOR_TRX6 = {"Inventory"};
    public static final Object[] FIRST_ROW_INVENTORY_RATE_RIGHT_COLUMNS_FOR_TRX6 = {"inventory"};
    public static final String[] SECOND_ROW_INVENTORY_RATE_RIGHT_HEADERS_FOR_TRX6 = {"Inventory Details", "Baseline Price", "Adjusted Price"};
    public static final Object[] SECOND_ROW_INVENTORY_RATE_RIGHT_COLUMNS_FOR_TRX6 = {"inventoryDetails", "baselinePrice", "adjustedPrice"};

    public static final String[] FIRST_ROW_INVENTORY_RATE_RIGHT_HEADERS_FOR_TRX7 = {"Sales"};
    public static final Object[] FIRST_ROW_INVENTORY_RATE_RIGHT_COLUMNS_FOR_TRX7 = {"sales"};
    public static final String[] SECOND_ROW_INVENTORY_RATE_RIGHT_HEADERS_FOR_TRX7 = {"Data Type", "Price", "Exclusion Details"};
    public static final Object[] SECOND_ROW_INVENTORY_RATE_RIGHT_COLUMNS_FOR_TRX7 = {"dateType", "price", "exclusionDetails"};

    public static Map<Integer, String> getTYrx7LevelAndLevelFilter(String value) {

        if (TRX7_LEVEL_FILTER_NAME.isEmpty()) {
            Map<Integer, String> summary_level_Brand = new HashMap();
            Map<Integer, String> summary_level_Customer_ded = new HashMap();
            Map<Integer, String> summary_level_dedution_Customer = new HashMap();
            Map<Integer, String> summary_level_dedution_Contract = new HashMap();
            Map<Integer, String> summary_level_dedutionContract = new HashMap();
            Map<Integer, String> summary_level_dedutionSales = new HashMap();

            summary_level_Brand.put(1, levelVariablesVarables.BRAND.toString());
            summary_level_Brand.put(NumericConstants.TWO, levelVariablesVarables.ITEM.toString());

            summary_level_Customer_ded.put(1, levelVariablesVarables.CUSTOMER.toString());
            summary_level_Customer_ded.put(NumericConstants.TWO, levelVariablesVarables.DEDUCTION.toString());
            summary_level_Customer_ded.put(NumericConstants.THREE, levelVariablesVarables.BRAND.toString());
            summary_level_Customer_ded.put(NumericConstants.FOUR, levelVariablesVarables.ITEM.toString());

            summary_level_dedution_Customer.put(1, levelVariablesVarables.CUSTOMER.toString());
            summary_level_dedution_Customer.put(NumericConstants.TWO, levelVariablesVarables.BRAND.toString());
            summary_level_dedution_Customer.put(NumericConstants.THREE, levelVariablesVarables.ITEM.toString());

            summary_level_dedutionContract.put(1, levelVariablesVarables.CONTRACT.toString());
            summary_level_dedutionContract.put(NumericConstants.TWO, levelVariablesVarables.CUSTOMER.toString());
            summary_level_dedutionContract.put(NumericConstants.THREE, levelVariablesVarables.BRAND.toString());
            summary_level_dedutionContract.put(NumericConstants.FOUR, levelVariablesVarables.ITEM.toString());

            summary_level_dedution_Contract.put(1, levelVariablesVarables.DEDUCTION.toString());
            summary_level_dedution_Contract.put(NumericConstants.TWO, levelVariablesVarables.CUSTOMER.toString());
            summary_level_dedution_Contract.put(NumericConstants.THREE, levelVariablesVarables.CONTRACT.toString());
            summary_level_dedution_Contract.put(NumericConstants.FOUR, levelVariablesVarables.BRAND.toString());
            summary_level_dedution_Contract.put(NumericConstants.FIVE, levelVariablesVarables.ITEM.toString());

            summary_level_dedutionSales.put(1, levelVariablesVarables.CUSTOMER.toString());
            summary_level_dedutionSales.put(NumericConstants.TWO, levelVariablesVarables.BRAND.toString());
            summary_level_dedutionSales.put(NumericConstants.THREE, levelVariablesVarables.ITEM.toString());

            TRX7_LEVEL_FILTER_NAME.put(ARMConstants.getDeductionProduct(), summary_level_Brand);
            TRX7_LEVEL_FILTER_NAME.put(ARMConstants.getCustomerDedection(), summary_level_Customer_ded);
            TRX7_LEVEL_FILTER_NAME.put(ARMConstants.getDeductionCustomer(), summary_level_dedution_Customer);
            TRX7_LEVEL_FILTER_NAME.put(ARMConstants.getDeductionCustomerContract(), summary_level_dedution_Contract);
            TRX7_LEVEL_FILTER_NAME.put("Deduction Contract", summary_level_dedutionContract);
            TRX7_LEVEL_FILTER_NAME.put(ARMConstants.getDeductionProduct() + "Sales", summary_level_dedutionSales);//Added for GAL-1127
        }
        return TRX7_LEVEL_FILTER_NAME.get(value);
    }

    public static List<String> getTrx7SalesVariables() {
        if (trx7salesVariablesList.isEmpty()) {
            trx7salesVariablesList.addAll(Arrays.asList(VariableConstants.VARIABLE_SALES_VISIBLE_COLUMN));
            trx7salesVariablesList.add(VariableConstants.GROUP);
            trx7salesVariablesList.add(VariableConstants.PRODUCT_NAME);
            trx7salesVariablesList.add(VariableConstants.PRODUCT_MASTER_SID);
            trx7salesVariablesList.add(VariableConstants.CHILDREN_ALLOWED);
            trx7salesVariablesList.add(VariableConstants.LEVEL_NAME);
            trx7salesVariablesList.add(VariableConstants.COMPANY_SID);
            trx7salesVariablesList.add(VariableConstants.BRNAD_ID);
        }
        return trx7salesVariablesList;
    }
}
