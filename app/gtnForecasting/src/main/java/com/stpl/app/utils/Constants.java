/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import com.stpl.app.gtnforecasting.utils.Constant;
import java.util.Calendar;
import org.apache.commons.lang.StringUtils;

/**
 * This class contains all the constants used across all the screens. Please do
 * not modify any constants If any constant is to be changes please make sure it
 * is not used anywhere else. Before adding a new constant, please make sure its
 * not already exists. Also, add ".getConstant()" at the end of enum constants
 * when enums are used. ".getConstant()" will return the string value of the
 * enum. If ".getConstant()" is not used, the enum used will be used as an
 * object and not as String
 *
 * @author soundarrajan
 */
public class Constants {

    public final String[] reasonCodes = {"Added new Trading Partner", "Added to Formulary", "Class limitation", "Decrease in Membership", "Deleted Trading Partner Other", "Increase in Membership", "New Contract Discount Rates", "Other", "Removed from Formulary"};

    /**
     * Enum for label constants
     */
    public enum LabelConstants {
        PRODUCT_HIERARCHY(PRODUCT1),
        METHODOLOGY_MOVING_AVERAGE("Moving Average"),
        LEVEL_BRAND(BRAND1),
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
        PER_EX_FACTORY("% of Ex-Factory"),
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
        PROJECTED_RATE(PROJECTED_RATE1),
        CFF_PROJECTED_RATE(PROJECTED_RATE1),
        ACTUALRATE("ActualsRate"),
        PROJECTEDRATE("ProjectionsRate"),
        CFF_PROJECTEDRATE("ProjectionsRate"),
        ACTUALAMOUNT("ActualsAmount"),
        ACTUALEXFACTORY("Actual % of Ex-Factory"),
        ACTUALRPU("ActualsRPU"),
        PROJECTEDRPU("ProjectedRPU"),
        PROJECTEDAMOUNT("ProjectionsAmount"),
        PROJECTEDEXFACTORY("Projected % of Ex-Factory"),
        ACTUAL_AMOUNT("Actual Amount"),
        ACTUAL_RPU("Actual RPU"),
        PROJECTED_AMOUNT("Projected Amount"),
        PROJECTED_RPU("Projected RPU"),
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
        BRAND(BRAND1),
        CONTRACT(CONTRACT1),
        CONTRACT_NAME("Contract Name"),
        CONTRACT_NO("Contract No"),
        CUSTOMER_ID("Customer ID"),
        CONTRACT_HOLDER_NAME("Contract Holder Name"),
        COMPANY(COMPANY1),
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
        TAB_DATA_ASSUMPTIONS("Data Assumptions"),
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
        CUSTOMER(CUSTOMER1),
        PRODUCT(PRODUCT1),
        CUSTOM(CUSTOM1),
        BRAND_SEARCH("Brand Search"),
        TIME_PERIOD("Time Period"),
        CUSTOMER_GROUP("Customer Group"),
        PRODUCT_GROUP("Product Group"),
        ENABLE(ENABLE1),
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
        INVENTORY_WITHDRAW("Inventory Withdraw"),
        PERC_OF_BUSSINESS("% of Business"),
        CONTRACT_SALES_AT_WAC("Contract Sales @ WAC"),
        SALES_PERC_OF_EX_FACTORY_SALES("Contract Sales as % of Ex-Factory Sales"),
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
        DISABLE(DISABLE1),
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
        CUSTOMER_HIERARCHY(CUSTOMER1),
        CUSTOM_HIERARCHY(CUSTOM1),        
        LEVEL_NDC_11("NDC 11"),
        TAB_SALES_ALLOCATION("Sales Allocation"),
        THERAPEUTIC_CLASS("therapeuticClass"),
        SCREEN_NAME("screenName"),
        ACTUAL_PAYMENTS("Actual Payments"),
        PROJECTED_PAYMENTS("Projected Payments"),
        ALLOCATIONPER("Allocation %"),
        ACTALLOCPER("ActAllocation%"),
        PRJALLOCPER("ProjAllocation%"),
        ACTUAL_UNITS("Actual Units"),
        PROJECTED_UNITS("Projected Units"),
        DISCOUNT_PROJ("discount projection"),
        DATA_SELECTION_LANDING_SCREEN("Data Selection Landing Screen"),
        SALES_PROJ("sales projection");
        
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
    public static final String PRODUCT1 = "Product";
    public static final String BRAND1 = "Brand";
    public static final String PROJECTED_RATE1 = "Projected Rate";
    public static final String CONTRACT1 = "Contract";
    public static final String COMPANY1 = "Company";
    public static final String CUSTOMER1 = "Customer";
    public static final String CUSTOM1 = "Custom";
    public static final String ENABLE1 = "Enable";
    public static final String DISABLE1 = "Disable";

    /**
     * Enum for Button constants
     */
    public enum ButtonConstants {

        ADD_TO_SELECTED(">"),
        REMOVE_TO_AVAILABLE("<"),
        ADD_ALL("ALL"),
        BTN_SEARCH("SEARCH"),
        BTN_IMPORT("IMPORT"),
        BTN_GENERATE("GENERATE"),
        BTN_DELETE_VIEW("Delete View"),
        BTN_SAVE_VIEW("Save View"),
        BTN_SAVE("SAVE"),
        BTN_SUBMIT("SUBMIT"),
        BTN_NEXT("NEXT"),
        BTN_PREVIOUS("PREVIOUS"),
        BTN_CLOSE("CLOSE"),
        BTN_RESET("RESET"),
        BTN_EDIT("EDIT"),
        BTN_VIEW("VIEW"),
        BTN_DELETE("DELETE"),
        BTN_CANCEL("CANCEL"),
        BTN_SELECT("SELECT"),
        BTN_EXPAND("EXPAND"),
        BTN_COLLAPSE("COLLAPSE"),
        BTN_NEW("NEW"),
        BTN_POPULATE("POPULATE"),
        BTN_ADJUST("ADJUST"),
        BTN_CALCULATE("CALCULATE"),
        BTN_ADD("ADD"),
        BTN_UPDATE("UPDATE"),
        BTN_ADD_TP("ADD TP"),
        BTN_EXPORT_TP("EXPORT TP"),
        ALL("All"),
        SELECT("Select"),
        BTN_APPROVE("APPROVE"),
        BTN_REJECT("REJECT"),
        BTN_WITHDRAW("WITHDRAWN"),
        BTN_REMOVE("REMOVE");
        private final String constant;

        private ButtonConstants(String constant) {
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
     * Enum for Module/Screen/Functionality indicator constants
     */
    public enum IndicatorConstants {

        INDICATOR_TIME_PERIOD_CHANGED("TIME CHANGED"),
        INDICATOR_REFRESH_UPDATE("Refresh"),
        INDICATOR_CUSTOMER_GROUP("Customer Group"),
        INDICATOR_PRODUCT_GROUP("Product Group"),
        INDICATOR_CUSTOMER_HIERARCHY("Customer Hierachy"),
        INDICATOR_PRODUCT_HIERARCHY("Product Hierachy"),
        INDICATOR_PUBLIC_VIEW("Public View"),
        INDICATOR_PRIVATE_VIEW("Private View"),
        INDICATOR_CUSTOMER(CUSTOMER1),
        INDICATOR_BRAND(BRAND1),
        INDICATOR_DATA_SELECTION_TAB("DataSelectionTab"),
        INDICATOR_DATA_SELECTION_INDEX("DataSelectionIndex"),
        INDICATOR_MODULE_NON_MANDATED("Non Mandated"),
        INDICATOR_MODULE_MANDATED("Mandated"),
        INDICATOR_LEVEL_NDC("NDC Level"),
        INDICATOR_LEVEL_CUSTOMER("Customer Level"),
        INDICATOR_LEVEL_CONTRACT("Contract Level"),
        INDICATOR_LEVEL_GLCOMP("GL Company Level"),
        INDICATOR_MODULE_CHANNELS("Channels"),
        INDICATOR_SCREEN_SALES("Sales Allocation"),
        INDICATOR_LOGIC_CUSTOMER_HIERARCHY("C"),
        INDICATOR_LOGIC_PRODUCT_HIERARCHY("P"),
        INDICATOR_LOGIC_CUSTOM_HIERARCHY("CP"),
        INDICATOR_HIERARCHY("HIERARCHY"),
        SALES_LEVEL_BRAND("SalesLevelBrand"),
        SALES_LEVEL_NDC("SalesLevelNDC"),
        SALES_LEVEL_TOTAL_CUSTOMER("SalesLevelTotalCustomer"),
        SALES_LEVEL_TOTAL_SALES("SalesLevelTotalSales"),
        SALES_LEVEL_COMPANY("SalesLevelWholeSalerSpecialityDistributor"),
        SALES_LEVEL_WHOLE_SALER("SalesLevelWholeSaler"),
        SALES_LEVEL_SPECIALITY_DISTRIBUTOR("SalesLevelSpecialityDistributor"),
        SALES_LEVEL_INDIVIDUAL_OWNER("SalesLevelContract"),
        SALES_LEVEL_FILTER_BRAND(BRAND1),
        SALES_LEVEL_FILTER_NDC("NDC"),
        SALES_LEVEL_FILTER_TOTAL_CUSTOMER("Total Customer"),
        SALES_LEVEL_FILTER_COMPANY("Wholesales/Speciality Distributor"),
        SALES_LEVEL_FILTER_CONTRACT("Individual Owner Code"),
        VIEW_INDICATOR("viewIndicator"),
        LEVEL_INDICATOR("levelIndicator"),
        LEVEL_NO("LEVEL_NO"),
        HIERARCHY_NO("HIERARCHY_NO"),
        H_INDICATOR("?H_INDICATOR?"),
        LEVEL_NO_C("?LEVELNOC?"),
        LEVEL_NO_P("?LEVELNOP?"),
        EXPANDED_PARENT("parent"),
        VIEW_CUSTOMER_LEVEL_PRODUCT("viewCustomerLevelProduct"),
        INPUT_MAP("input"),
        JOIN_MAP("joinMap"),
        QUERY_NAME("queryName"),
        FORECAST_LEVEL_NO("Forecast Level No"),
        INDICATOR_VIEW_CUSTOMER("C"),
        INDICATOR_VIEW_CUSTOM("CP"),
        INDICATOR_VIEW_PRODUCT("P");
        
        
        private final String constant;

        private IndicatorConstants(String constant) {
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
        DATE_FORMAT(DATE_FORMAT_MMDDYYYY),
        REASON_CODE("Reason Code"),
        NULL("null"),
        ACTION_EDIT("edit"),
        ACTION_VIEW("view"),
        ACTION_GENERATE("add"),
        COMMENTARY("Commentary"),
        VALUE("value"),
        VARIANCE("variance"),
         CHANGE("change"),
        CONTRACT_DETAILS("Contract Details"),   
        SINGLE_PERIOD("Single Period"),
        AVERAGE("Average"),
        ROLLING_ANNUAL_TREND("Rolling Annual Trend"),
        PER_EX_FACTORY_SALES("% of Ex-Factory Sales"),
        PER_OF_DEMAND("% of Demand"),
        PER_INVENTORY_WITHDRAW("% of Inventory Withdrawal");
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
        QUARTERS("Quarters"),
        DEFAULT_JAVA_DATE_FORMAT("EEE MMM dd HH:mm:ss z yyyy"),
        DEFAULT_SQL_DATE_FORMAT("yyyy-MM-dd HH:mm:ss.SSS"),
    	JAVA_DATE_FORMAT("yyyy-MM-dd");
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

    /**
     * Enum for level name constants
     */
    public enum LevelConstants {

        LEVEL_SEGMENT_GROUP(SEGMENT_GROUP_LABEL),
        LEVEL_SEGMENT(Constant.SEGMENT_LABEL),
        LEVEL_MARKET_TYPE(MARKET_TYPE_LABEL),
        LEVEL_CONTRACT_HOLDER(CONTRACT_HOLDER_LABEL),
        LEVEL_CONTRACT(CONTRACT1),
        LEVEL_TRADING_PARTNER(TRADING_PARTNER_LABEL),
        LEVEL_COMPANY(COMPANY1),
        LEVEL_THERAPEUTIC_CLASS(THERAPEUTIC_CLASS_LABEL),
        LEVEL_BRAND(BRAND1),
        LEVEL_NDC("NDC"),
        LEVEL_1_CUSTOMER(SEGMENT_GROUP_LABEL),
        LEVEL_2_CUSTOMER(Constant.SEGMENT_LABEL),
        LEVEL_3_CUSTOMER(MARKET_TYPE_LABEL),
        LEVEL_4_CUSTOMER(CONTRACT_HOLDER_LABEL),
        LEVEL_5_CUSTOMER(CONTRACT1),
        LEVEL_6_CUSTOMER(TRADING_PARTNER_LABEL),
        LEVEL_1_BRAND(COMPANY1),
        LEVEL_2_BRAND(THERAPEUTIC_CLASS_LABEL),
        LEVEL_3_BRAND(BRAND1),
        LEVEL_4_BRAND("NDC"),
        LEVEL_1("Level 1"),
        LEVEL_2("Level 2"),
        LEVEL_3("Level 3"),
        LEVEL_4("Level 4"),
        LEVEL_5("Level 5"),
        LEVEL_6("Level 6");
        private final String constant;

        private LevelConstants(String constant) {
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
    public static final String SEGMENT_GROUP_LABEL = "Segment Group";
    public static final String MARKET_TYPE_LABEL = "Market Type";
    public static final String CONTRACT_HOLDER_LABEL = "Contract Holder";
    public static final String TRADING_PARTNER_LABEL = "Trading Partner";
    public static final String THERAPEUTIC_CLASS_LABEL = "Therapeutic Class";

    /**
     * Enum for Regex constants
     */
    public enum RegexConstants {

        DOUBLE_CHECK("^\\$(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$"),
        REGEX_EXTRACT_DIGITS("\\D+");
        private final String constant;

        private RegexConstants(String constant) {
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
     * Enum for Header constants
     */
    public enum HeaderConstants {

        HEADER_PROJECTION_NAME("Projection Name"),
        HEADER_PROJECTION_DESCRIPTION("Description"),
        HEADER_CREATED_BY("Created By"),
        HEADER_CREATED_DATE("Created Date"),
        HEADER_LAST_MODIFIED_DATE("Last Modified Date"),
        HEADER_CUSTOMER_HIERARCHY("Customer Hierarchy"),
        HEADER_CUSTOMER_LEVEL("Customer Level"),
        HEADER_PRODUCT_HIERARCHY("Product Hierarchy"),
        HEADER_PRODUCT_LEVEL("Product Level"),
        HEADER_LEVEL("Level"),
        HEADER_MARKET_TYPE(MARKET_TYPE_LABEL),
        HEADER_COMPANY(COMPANY1),
        HEADER_THERAPEUTIC_CLASS(THERAPEUTIC_CLASS_LABEL),
        HEADER_SEGMENT_GROUP(SEGMENT_GROUP_LABEL),
        HEADER_SEGMENT(Constant.SEGMENT_LABEL),
        HEADER_CONTRACT_HOLDER(CONTRACT_HOLDER_LABEL),
        HEADER_CONTRACT(CONTRACT1),
        HEADER_TRADING_PARTNER(TRADING_PARTNER_LABEL),
        HEADER_BRAND(BRAND1),
        HEADER_NDC("NDC"),
        NDC11("NDC 11"),
        SALES_GTS_HEADER("Gross Trade Sales"),
        SALES_GTS_VC("-GrossTradeSales"),
        SALES_GTS_VC_TEXTFIELD("-GrossTradeSalesDis"),
        SALES_POB_HEADER("% Of Business"),
        SALES_POB_VC("-%OfBusiness"),
        SALES_POB_VC_TEXTFIELD("-%OfBusinessDis"),
        SALES_CS_HEADER("Contract Sales"),
        SALES_CS_VC("-ContractSales"),
        SALES_CS_VC_TEXTFIELD("-ContractSalesDis");
        private final String constant;

        private HeaderConstants(String constant) {
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
     * Window messages enum
     */
    public enum WindowMessagesMain {

        NM_LOAD_TP_SP("There are trading partners associated with the selected contract holder that do not have any actuals.\nDo you want to display these trading partners?"),
        MAND_LOAD_CONTRACT_SP("There are contracts associated with the selected customer that do not have any actuals.\nDo you want to display these contracts?"),
        CLOSE_PROJECTION_CONFIRMATION("Are you sure you want to close this Projection?"),
        CLOSE_SAVE_CONFIRMATION("Changes have been made to the projection.\nWould you like to save the changes before closing?"),
        SAVE_PROJECTION_CONFIRMATION("Are you sure you want to save the projection?"),
        SUBMIT_PROJECTION_CONFIRMATION("Are you sure you want to submit the projection for approval?"),
        SUBMIT_PROJECTION_VALIDATION("The workflow cannot be submitted for approval.\nNot all required fields have been completed."),
        EDIT_VALIDATION("You cannot edit a view that you did not create.\nPlease select another view, or create a new one."),
        RESET_SP_SELECTIONS("Are you sure you want to reset the ‘Sales Projection Selections’?"),
        CALCULATE_METHODOLOGY_VALIDATION("Please select a Methodology."),
        CALCULATE_LEVEL_VALIDATION("Please select a level in the hierarchy for the methodology."),
        CALCULATE_SINGLE_HISTORIC_VALIDATION("Please select a Historic Period."),
        CALCULATE_TWO_HISTORIC_VALIDATION("Please select at least two Historic Periods."),
        CALCULATE_LINEAR_VALIDATION("The calculated R2 value for the Linear methodology is "),
        CALCULATE_LOGARITHMIC_VALIDATION("The calculated R2 value for the Logarithmic methodology is "),
        ADJUST_PERIODS_VALIDATION("Please select which periods need to be included in the adjustment."),
        ADJUST_ALLOCATION_VALIDATION("Please select an Allocation Methodology."),
        ADJUST_HISTORIC_VALIDATION("Please select a Historic Period."),
        ADJUST_PROJECTION_VALIDATION("Please select a Projection Period."),
        ADJUST_CONFIRM_INCREMENTAL("You are about to make the following ($/ %) adjustment for the following periods (list periods).\nAre you sure you want to continue?"),
        ADJUST_CONFIRM_OVERRIDE("You are about to replace the current values in the list view with the following variable: ($ or %).\nAre you sure you want to continue?"),
        EXPAND_COLLAPSE_LEVEL_VALIDATION("Please select a Level from the drop down."),
        EXPORT_TP_VALIDATION("Please select a Trading Partner to export."),
        SPR_RESET("Are you sure you want to reset the values in the Sales Projection Selection section to the previous values?"),
        SALES_NO_METHODOLOGY_SELECTED("There is no methodology selected. Please select a methodology to continue."),
        MESSAGE_BOX_YES("YES"),
        MESSAGE_BOX_NO("NO"),
        MESSAGE_BOX_OK("OK"),
        SALES_NO_RECORD_SELECTED("There are no records selected. Please select at least one record to update."),
        SALES_SELECT_LEVEL_EXPAND("There is no level selected to expand. Please select a level to continue."),
        SALES_SELECT_LEVEL_COLLAPSE("There is no level selected to collapse. Please select a level to continue."),
        DP_RESET_CRITERIA("Are you sure you want to reset the ‘Discount Projection Selections’?"),
        MESSAGE_BOX("OK");
        private final String constant;

        private WindowMessagesMain(String constant) {
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
     * Window messages enum
     */
    public enum WindowMessagesName {

        LOAD_TP("Load Trading Partners"),
        NO_METHODOLOGY_SELECTED("No Methodology selected"),
        NO_HIERARCHY_LEVEL_SELECTED("No Hierarchy level selected"),
        RESET_CONFIRMATION("Reset Confirmation"),
        NO_PERIOD_SELECTED("No period selected"),
        NO_LEVEL_SELECTED("No Level Selected"),
        R2_FOR_LINEAR("R2 for Linear"),
        R2_FOR_LOGARITHMIC("R2 for Logarithmic"),
        NO_ALLOCATION_METHODOLOGY_SELECTED("No Allocation Methodology selected"),
        IMPROPER_CALCULATION_VARIABLES("Improper calculation variables"),
        CONFIRM_INCREMENTAL_ADJUSTMENT("Confirm Incremental adjustment"),
        CONFIRM_OVERRIDE("Confirm Override"),
        EDIT_VIEW("Edit a View"),
        NO_TP_SELECTED("No Trading Partner Selected."),
        SAVE_CONFIRMATION("Save Confirmation"),
        CUSTOM_TREE_SAVE_VALIDATION("Save Name Check"),
        CONFRIM_SAVE("Confirm Save"),
        CLOSE_CONFIRMATION("Close Confirmation"),
        CLOSE_SAVE_CONFIRMATION("Save?"),
        SUBMIT_CONFIRMATION("Confirm Submit"),
        SUBMIT_MISSING_VALUES("Missing Values"),
        NO_SEARCH_VALUES("No Search Value Entered"),
        NO_RESULT_SELECTED("No Search Value Entered"),
        NO_RESULTS("No Results"),
        NO_SEARCH_CRITERIA("No Search Criteria"),
        TP_LOOKUP_NO_TP_SELECTED("No Trading Partner selected"),
        CONFIRM_RESET("Reset?"),
        MAND_LOAD_CONTRACT_SP("Load Contracts"),
        CALCULATION_ERROR("Calculate Error"),
        SELECT_METHODOLOGY("Select Methodology"),
        SELECT_RECORD("Select Record"),
        SELECT_LEVEL("Select a Level"),
        CONFIRMATION("Confirmation"),
        MESSAGE_BOX("OK");
        private final String constant;

        private WindowMessagesName(String constant) {
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
     * Window messages enum
     */
    public enum WindowMessagesPopups {

        CLOSE_PROJECTION_CONFIRMATION("Are you sure you want to close this Projection?"),
        RESET_SEARCH_CRITERIA("You are about to reset the Search Criteria values. Continue?"),
        NO_SEARCH_VALUES("There is no search value entered.\nPlease enter a search value and try again."),
        NO_RESULT_SELECTED("There are no selected results.\nPlease select a record and try again."),
        CLOSE_ALTERNATE_HISTORY("Closing the popup will not submit an alternate Contract Holder and/or Brand back to the Sales Projection screen.\nAre you sure you want to continue?"),
        CUSTOM_TREE_SAVE("Are you sure you want to save the current Tree Structure?"),
        CUSTOM_TREE_SAVE_VALIDATION("That view name is taken.\nPlease enter a new View name."),
        TP_LOOKUP_NO_RESULTS("There were no results found that match the entered search criteria.\nPlease try again."),
        TP_LOOKUP_SEARCH_VALIDATION("Please enter a value to search for."),
        TP_LOOKUP_RESET_CRITERIA("Are you sure you want to reset the search criteria?"),
        TP_LOOKUP_NO_TP_SELECTED("Please select a Trading Partner from the list view."),
        TP_LOOKUP_RESET("Are you sure you want to reset the lookup to its default state?"),
        TP_LOOKUP_CLOSE("Are you sure you want to close the trading partner lookup?"),
        MESSAGE_BOX_YES("YES"),
        MESSAGE_BOX_NO("NO"),
        MESSAGE_BOX_OK("OK"),
        MESSAGE_BOX("OK");
        private final String constant;

        private WindowMessagesPopups(String constant) {
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
                return (com.stpl.ifs.ui.util.NumericConstants.TWO * CalendarConstants.HISTORY_YEAR_COUNT.getConstant()) + (CalendarConstants.CURRENT_MONTH.getConstant() / com.stpl.ifs.ui.util.NumericConstants.SIX);
            }

            /**
             * Calculates the Quarterly value which is 4 * history year count
             *
             * @param historyYearCount historyYearCount value
             * @return the Quarterly count
             */
            @Override
            public int getQuarterCount() {
                return (com.stpl.ifs.ui.util.NumericConstants.FOUR * CalendarConstants.HISTORY_YEAR_COUNT.getConstant()) + (CalendarConstants.CURRENT_MONTH.getConstant() / com.stpl.ifs.ui.util.NumericConstants.THREE);
            }

            /**
             * Calculates the Monthly value which is 12 * history year count
             *
             * @param historyYearCount historyYearCount value
             * @return the Monthly count
             */
            @Override
            public int getMonthCount() {
                return (com.stpl.ifs.ui.util.NumericConstants.TWELVE * CalendarConstants.HISTORY_YEAR_COUNT.getConstant()) + (CalendarConstants.CURRENT_MONTH.getConstant());
            }

            /**
             * Gives the current quarter of current month
             *
             * @return
             */
            @Override
            public int getCurrentQuarter() {
                return CalendarConstants.CURRENT_MONTH.getConstant() / com.stpl.ifs.ui.util.NumericConstants.THREE + 1;
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
     * Enum for logic purpose
     */
    public enum LogicConstants {

        VIEW_TYPE();
        private volatile String constant;

        private LogicConstants() {
        }

        public String getConstant() {
            return constant;
        }

        public synchronized String getViewType(final String indicator) {
            if (indicator != null && !indicator.isEmpty()) {
                if (indicator.equals(LabelConstants.PRIVATE_VIEW.getConstant())) {
                    constant = LabelConstants.PRIVATE.getConstant();
                } else if (indicator.equals(LabelConstants.PUBLIC_VIEW.getConstant())) {
                    constant = LabelConstants.PUBLIC.getConstant();
                } else {
                    constant = StringUtils.EMPTY;
                }
            } else {
                constant = StringUtils.EMPTY;
            }
            return constant;
        }
    }

    /**
     * Enum for Message constants
     */
    public enum MessageConstants {

        SEARCH_COMPLETED("Search Completed"),
        NO_SEARCH_RESULTS("Search Completed"),
        NO_SEARCH_CRITERIA_TITLE("No Search Criteria"),
        NO_SEARCH_CRITERIA_BODY("No search criteria were found. Please enter search criteria and try again."),
        CONFIRM_DELETION_TITLE("Confirm Deletion"),
        CONFIRM_DELETION_BODY("Are you sure you want to delete record ?#?"),
        NO_RECORDS_TITLE("No Matching Records");
        private final String constant;

        private MessageConstants(String constant) {
            this.constant = constant;
        }

        public String getConstant() {
            return constant;
        }

        @Override
        public String toString() {
            return String.valueOf(constant);
        }
    }

    /**
     * Enum for Length constants
     */
    public enum LengthConstants {

        LENGTH_500(500),
        LENGTH_200(200);
        private final int constant;

        private LengthConstants(int constant) {
            this.constant = constant;
        }

        public int getConstant() {
            return constant;
        }
    }

    /**
     * Enum for String constants
     */
    public enum StringConstants {

        PERCENT("%"),
        SPLIT_ARROW("->"),
        SPLIT_DASH("-"),
        DOT("."),
        QUESTION("?");
        private final String constant;

        private StringConstants(String constant) {
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
     * Enum for Style name, css properties constants
     */
    public enum StyleConstants {
        OPTION_GROUP_HORIZONTAL("horizontal"),
        OPTION_GROUP_WIDTH("optiongroupwidth"),
        DOT(".");
        private final String constant;

        private StyleConstants(String constant) {
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
        PROJECTION_END_DAY("projectionEndDay"),
        PROJECTED("Projected");
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
     * Enum for Date format constants
     */
    public enum DateFormatConstants {

        MMDDYYYY(DATE_FORMAT_MMDDYYYY),
        YYYYMMDDHHMMSSSSS("yyyy-MM-dd hh:mm:ss.SSS"),
        MMDDYYYYHHMMSS("MM/dd/yyyy hh:mm:ss");
        private final String constant;

        private DateFormatConstants(String constant) {
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
    public static final String DATE_FORMAT_MMDDYYYY = "MM/dd/yyyy";
    
     /**
     * Enum for common constants
     */
    public enum CommonConstantsForChannels {

        SPACE(" "),
        COLON(":"),
        HTML_SPACE("&nbsp;"),
        SELECT_ONE("-Select One-"),
        DATE_FORMAT(DATE_FORMAT_MMDDYYYY),
        REASON_CODE("Reason Code"),
        NULL("null"),
        ACTION_EDIT("edit"),
        ACTION_VIEW("view"),
        ACTION_GENERATE("add"),
        COMMENTARY("Commentary"),
        VALUE("value"),
        VARIANCE("variance"),
        CHANGE("change"),
        FLAT("Flat"),
        CUSTOM(CUSTOM1),
        CUSTOMER(CUSTOMER1),
        PRODUCT(PRODUCT1),
        PROJECTED("Projected"),
        PROJECTIONS("Projections"),
        ASCENDING("Ascending"),
        DESCENDING("Descending"),
        HORIZONTAL("horizontal"),
        OPTIONGROUPWIDTH("optiongroupwidth"),
        ACTUAL_RATE("ActualsRate"),
        PROJECTED_RATE(PROJECTED_RATE1),
        DISCOUNT("Discount"),
        BOTH("Both"),
        SALES("Sales"),
        UNITS("Units"),
        VARIABLE("Variable"),
        INCREMENTAL("Incremental"),
        OVERRIDE("Override"),
        AMOUNT("Amount"),
        PERCENTAGE("Percentage"),
        PER("%"),
        DOLLAR("$"),
        ALL("All"),
        SELECTED("Selected"),
        ENABLE(ENABLE1),
        DISABLE(DISABLE1),
        ACTUALS("Actuals");
        private final String constant;

        private CommonConstantsForChannels(String constant) {
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
     * Enum for String constants
     */
    public enum FinderImplIndicators {
        INDICATOR("indicator"),
        SELECT_NAME_GENERATE_SALES_SUB("?SELECTNAMESUBQUERY?"),
        SELECT_NAME_GENERATE_SALES("?SELECTNAME?"),
        SALES_GENERATE_BRAND_COUNT_JOIN("?JOINCOUNTBRAND?"),
        SALES_GENERATE_ITEM_COUNT_JOIN("?JOINCOUNTITEM?"),
        SALES_GENERATE_COMPANY_COUNT_JOIN("?JOINCOUNTCOMPANY?"),
        SALES_GENERATE_THERAPEUTIC("?THERAPEUTIC?"),
        SALES_GENERATE_ITEM("?ITEM?"),
        SALES_GENERATE_BRAND("?BRAND?"),
        SALES_GENERATE_COMPANY("?COMPANY?"),
        SALES_GENERATE_CONTRACT("?CONTRACT?"),
        USER_ID("?USERID?"),
        SESSION_ID("?SESSIONID?"),
        OFFSET("?OFFSET?"),
        START("?START?"),
        GROUPBY("?GROUPBY?"),
        ORDERBY("?ORDERBY?"),
        PM_SID("?PMSID?"),
        SID("?SID?"),
        BRAND_FIELD("?BRANDFIELD?"),
        CONTRACT_FIELD("?CONTRACTFIELD?"),
        COMPANY_FIELD("?COMPANYFIELD?"),
        ITEM_FIELD("?ITEMFIELD?"),
        GTS_SUB_ACTUALS("?GTSSUBJOINQUERYACTUALS?"),
        GTS_SUB_PROJECTIONS("?GTSSUBJOINQUERYPROJECTIONS?"),
        GTS_SUB_ACTUALS_TOTAL_SALES("?GTSSUBJOINQUERYTOTALSALESACTUALS?"),
        GTS_SUB_PROJECTIONS_TOTAL_SALES("?GTSSUBJOINQUERYTOTALSALESPROJECTION?"),
        DENSE_RANK("?DENSE_RANK?"),
        TEMP_TABLE("?TEMP_TABLE?"),
        INDICATOR_ACTUALS("ACTUALS"),
        INDICATOR_PROJECTIONS("PROJECTIONS"),
        SALES_GENERATE_CONTRACT_COUNT_JOIN("?JOINCOUNTCONTRACT?");
        private final String constant;

        private FinderImplIndicators(String constant) {
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
     * Enum for Numeric constants
     */
    public enum NumericConstants {

        ONE("1"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        ZERO("0");
        private final String constant;

        private NumericConstants(String constant) {
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
     * Enum for Date format constants
     */
    public enum DecimalFormatConstants {

        SALES_GTS_CS("###,###,##0.00"),
        SALES_POB("#0.00");
        private final String constant;

        private DecimalFormatConstants(String constant) {
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
     * Enum for Projection Selection
     */
    public enum ProjectionSelectionConstants {

        SCREEN_NAME("screenName"),
        FREQUENCY("frequency"),
        VIEW("view"),
        LEVEL("level"),
        THERAPEUTIC_CLASS("therapeuticClass"),
        PROJECTION_PERIOD_ORDER("projectionPeriodorder"),
        HISTORY("history");
        private final String constant;

        private ProjectionSelectionConstants(String constant) {
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
     * Enum for MassUpdate Constants.
     */
    public enum MassUpdateConstants {

        /**
         * Constant for Enable.
         */
        ENABLE(ENABLE1),
        /**
         * Constant for Disable.
         */
        DISABLE(DISABLE1);
        /**
         * The constant.
         */
        private final String constant;

        /**
         * Instantiates a new MassUpdateConstants constants.
         *
         * @param constant the constant
         */
        private MassUpdateConstants(String constant) {
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
            return String.valueOf(constant);
        }
    }
    
     /**
     * The Constant Avilable Customer Header.
     */
    public final String[] availableItemsHeaders = new String[]{
        "", "Business Unit No", "Business Unit Name", "Theraputic Class", "Brand Name", "Item No", "Item Name","Item Identifier Type","Item Identifier"};
    public final Object[] availableItemsVisibleColumns = new Object[]{
        Constant.CHECK, "businessUnitNo", "businessUnitName", "theraputicClass", Constant.BRAND, Constant.ITEM_NO, "itemName","itemIdentifierType","itemIdentifier"};
    public static final String HIERVER = "@HIERVER";

    public static final String PROGRAM = "Program";
    public static final String PROGRAM_CATEGORY = "Program Category";
     public static final String MONTH = "Monthly";
     public static final String MULTIPLE = "Multiple";
     private static final String COMMERCIAL_FORECASTING_MULTIPLE_VARIABLES_HEADER = "COMMERCIAL_FORECASTING_MULTIPLE_VARIABLES_HEADER";
     private static final String COMMERCIAL_FORECASTING_MULTIPLE_VARIABLES_MESSAGE = "COMMERCIAL_FORECASTING_MULTIPLE_VARIABLES_MESSAGE";
	
     public static String getCommercialForecastingMultipleVariablesHeader() {
		return COMMERCIAL_FORECASTING_MULTIPLE_VARIABLES_HEADER;
	}
	
     public static String getCommercialForecastingMultipleVariablesMessage() {
		return COMMERCIAL_FORECASTING_MULTIPLE_VARIABLES_MESSAGE;
	}
        }
