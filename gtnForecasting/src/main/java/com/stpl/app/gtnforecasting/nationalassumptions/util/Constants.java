/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.util;

import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.Calendar;

/**
 * This class contains all the constants used across all the screens. Please do
 * not modify any constants If any constant is to be changes please make sure it
 * is not used anywhere else. Before adding a new constant, please make sure its
 * not already exists. Also, add ".getConstant()" at the end of enum constants
 * when enums are used. ".getConstant()" will return the string value of the
 * enum. If ".getConstant()" is not used, the enum used will be used as an
 * object and not as String
 *
 */
public class Constants {
    
    public static final String NULL =Constant.NULL;
    /**
     * Enum for label constants
     */
    public enum LabelConstants {
    	       
        PROJECTION_OPTIONS_CAPTION("Projection Options"),
        CUSTOMER_SELECTION_CAPTION("Customer Selection"),
        PRODUCT_SELECTION_CAPTION("Product Selection"),
        FROM("From"),
        TO("To"),
        ACTUALS("Actuals"),
        PROJECTIONS("Projections"),
        FORECAST("Forecast"),
        BOTH("Both"),
        HIERARCHY("Hierarchy"),
        LEVEL("Level"),
        CUSTOMER_GROUP_NAME("Customer Group Name"),
        CUSTOMER_GROUP_NO("Customer Group #"),
        PRODUCT_GROUP_NAME("Product Group Name"),
        PRODUCT_GROUP_NO("Product Group #"),
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
        PRICE_TYPE("Price Type"),
        DISCOUNT("Discount"),
        TREE_STRUCTURE("Tree Structure"),
        TREE_VIEW_NAME("Tree View Name"),
        SALES("Sales"),
        UNITS("Units"),
        VARIABLE("Variable"),
        LEVEL_FILTER("Level Filter"),
        VIEW("View"),
        PIVOT_VIEW("Pivot View"),
        HISTORY("History"),
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
        FREQUENCY("Frequency"),
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
        PROJECTION_PERIOD_ORDER("Projection Period Order"),
        VALUE("Value"),
        VARIANCE("Variance"),
        PERC_CHANGE("% Change"),
        GROSS_TRADE_SALES("Gross Trade Sales"),
        PERC_OF_BUSSINESS("% of Business"),
        CONTRACT_SALES_AT_WAC("Contract Sales @ WAC"),
        CONTRACT_UNIT("Contract Units"),
        DISCOUNT_AMOUNT("Discount $"),
        DISCOUNT_PERC("Discount %"),
        NET_SALES("Net Sales"),
        WORKFLOW_STATUS("Workflow Status"),
        NDC_NO("NDC #"),
        NDC_NAME("NDC Name"),
        CREATED_DATE("Created Date"),
        PERIOD_PIVOT_VIEW("Period Pivot View"),
        VARIABLE_PIVOT_VIEW("Variable Pivot View"),
        DISABLE("Disable"),
        PRICE_TRENDING("Price Trending"),// Changed as per Galuat-48
        GROWTH("Growth"),
        TYPE("type"),
       CHECK("check"),
       ROLLING_AVERAGE("Rolling Average"),
       EMPTYSTRING(""),
       SINGLE_PERIOD("Single Period"),
       AVERAGE("Average"),
       WEIGHTED_AVG("Weighted Average"),
       WAC("WAC"),
        PHS("PHS"),
        TOTAL_URA("Total URA"),
        PHS_DISCOUNT("PHS Discount"),
        FSS("FSS"),
        NON_FAMP("Non-FAMP"),
        CPI_URA("CPI-URA"),
        FCP("FCP"),
        CPI_URA_SPACE("CPI URA"),
        BASIC_URA("Basic URA"),
        NM_NET_PRICE("Lowest Commercial Net Price"),
        AMP("AMP"),
        BEST_PRICE("Best Price"),
        BEST_PRICE_CAPS("BEST PRICE"),
        DASH("- -"),
        CPI_U("CPI-U"),
        NOTES_DATE(" MM/dd/yyyy, HH:mm:ss "),
        AVERAGE_QUARTER_WAC("Average Quarter WAC"),
        BEGINNING_QUARTER_WAC("Beginning Quarter WAC"),
        ENDING_QUARTER_WAC("Ending Quarter WAC"),
        MID_QUARTER_WAC("Mid-Quarter WAC"),
        DAY_WEIGHTED_WAC("Day Weighted WAC"),
        SALES_WEIGHTED_WAC("Sales Weighted WAC"),
//        PRICE_TYPE_FORECASTING("Price Type Forecasting"),
        NATIONAL_ASSUMPTIONS("National Assumptions"),
        MEDICAID_URA("Medicaid URA Results"),
        FCP_RESULTS("FCP Results"),
        PHS_RESULTS("PHS Results"),
        NON_FAMP_LOOKUP("Non-Famp"),
        MEDICAID_URA_WORKSHEET_LOOKUP("Medicaid Ura Worksheet"),
        MASTER_FCP_WORKSHEET_LOOKUP("Fcp Worksheet"),
        MASTER_PHS_WORKSHEET_LOOKUP("Phs Worksheet"),
        
        DATA_SELECTION_INDEX("Data Selection Index"),
        DATA_SELECTION_TAB("Data Selection"),
        PRODUCT_GROUP_LOOKUP("Product Group Lookup"),
        ADDITIONAL_INFORMATION("Additional Information"),
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
     * Enum for Module/Screen/Functionality indicator constants
     */
    public enum IndicatorConstants {
        
        INDICATOR_DATA_SELECTION_TAB("DataSelectionTab"),
        INDICATOR_DATA_SELECTION_INDEX("DataSelectionIndex"),
        INDICATOR_MODULE_NON_MANDATED("Non Mandated"),
        INDICATOR_MODULE_MANDATED("Mandated"),
        INDICATOR_MODULE_CHANNELS("Channels"),
        PHS_RESULTS_SCREEN("PHS Results"),
        FCP_RESULTS_SCREEN("FCP Results"),
        URA_RESULTS_SCREEN("Medecaid URA Results"),
        ACTUALS_CAPS("ACTUALS"),
        BASEYEAR("baseYear"),
        PROJ_CAPS("PROJ");

        private String constant;

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
        USER_ID("userId"),
        PROJECTION_ID("projectionId"),
        SESSION_ID("sessionId"),
	EQUAL ("="),
        ACTION("action"),
        COLON(":"),
        HTML_SPACE("&nbsp;"),
        SELECT_ONE("-Select One-"),
        DATE_FORMAT("MM/dd/yyyy"),
        DESCRIPTION("description"),
        SUCCESS("Success"),
        BRAND_NAME ("brandName"),
        SHOW_ALL("Show All");

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
     * Enum for Frequency constants
     */
    public enum FrequencyConstants {

        ANNUAL("Annual"),
        ANNUALLY("Annually"),
        SEMI_ANNUALLY("Semi-Annually"),
        YEARS("Years"),
        SEMI_ANNUAL("Semi-Annual"),
        QUARTERLY("Quarterly"),
        MONTHLY("Monthly"),
        MONTHS("Months"),
        QUARTERS("Quarters"),
        YEAR("Year");
      
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

    }

    /**
     * Enum for level name constants
     */
    public enum LevelConstants {

        LEVEL_SEGMENT_GROUP("Segment Group"),
        LEVEL_SEGMENT("Segment"),
        LEVEL_MARKET_TYPE("Market Type"),
        LEVEL_CONTRACT_HOLDER("Contract Holder"),
        LEVEL_CONTRACT("Contract"),
        LEVEL_TRADING_PARTNER("Trading Partner"),
        LEVEL_COMPANY("Company"),
        LEVEL_THERAPEUTIC_CLASS("Therapeutic Class"),
        LEVEL_BRAND("Brand"),
        LEVEL_NDC("NDC"),
        LEVEL_1_CUSTOMER("Segment Group"),
        LEVEL_2_CUSTOMER("Segment"),
        LEVEL_3_CUSTOMER("Market Type"),
        LEVEL_4_CUSTOMER("Contract Holder"),
        LEVEL_5_CUSTOMER("Contract"),
        LEVEL_6_CUSTOMER("Trading Partner"),
        LEVEL_1_BRAND("Company"),
        LEVEL_2_BRAND("Therapeutic Class"),
        LEVEL_3_BRAND("Brand"),
        LEVEL_4_BRAND("NDC"),
        LEVEL_1("Level 1"),
        LEVEL_2("Level 2"),
        LEVEL_3("Level 3"),
        LEVEL_4("Level 4"),
        LEVEL_5("Level 5"),
        LEVEL_6("Level 6");

        private String constant;

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

    /**
     * Enum for Regex constants
     */
    public enum RegexConstants {

        REGEX_EXTRACT_DIGITS("\\D+"),;

        private String constant;

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
     * Enum for Header constants
     */
    public enum HeaderConstants {
    	
    	DOCUMENT_NAME("documentName"),
    	DATE_ADDED("dateAdded"),
	USER_NAME("userName"),
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
        HEADER_MARKET_TYPE("Market Type"),
        HEADER_COMPANY("Company"),
        HEADER_THERAPEUTIC_CLASS("Therapeutic Class"),
        HEADER_SEGMENT_GROUP("Segment Group"),
        HEADER_SEGMENT("Segment"),
        HEADER_CONTRACT_HOLDER("Contract Holder"),
        HEADER_CONTRACT("Contract"),
        HEADER_TRADING_PARTNER("Trading Partner"),
        HEADER_BRAND("Brand"),
        HEADER_NDC("NDC"),
        NDC11("NDC 11");

        private String constant;

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

   
        CLOSE_PROJECTION_CONFIRMATION("Are you sure you want to close this Projection?"),
        CLOSE_SAVE_CONFIRMATION("Changes have been made to the projection.\nWould you like to save the changes before closing?"),
        SAVE_PROJECTION_CONFIRMATION("Are you sure you want to save the projection?"),
        SUBMIT_PROJECTION_CONFIRMATION("Are you sure you want to submit the projection for approval?"),
        SUBMIT_PROJECTION_VALIDATION("The workflow cannot be submitted for approval.\nNot all required fields have been completed."),
        EDIT_VALIDATION("You cannot edit a view that you did not create.\nPlease select another view, or create a new one."),
        RESET_SP_SELECTIONS("Are you sure you want to reset the �Sales Projection Selections�?"),
        CALCULATE_METHODOLOGY_VALIDATION("Please select a Methodology."),
        CALCULATE_LEVEL_VALIDATION("Please select a level in the hierarchy for the methodology."),
        CALCULATE_SINGLE_HISTORIC_VALIDATION("Please select a Historic Period."),
        ADJUST_ALLOCATION_VALIDATION("Please select an Allocation Methodology."),
        ADJUST_HISTORIC_VALIDATION("Please select a Historic Period."),
        ADJUST_PROJECTION_VALIDATION("Please select a Projection Period."),
        ADJUST_CONFIRM_INCREMENTAL("You are about to make the following ($/ %) adjustment for the following periods (list periods).\nAre you sure you want to continue?"),
        ADJUST_CONFIRM_OVERRIDE("You are about to replace the current values in the list view with the following variable: ($ or %).\nAre you sure you want to continue?"),
        EXPAND_COLLAPSE_LEVEL_VALIDATION("Please select a Level from the drop down."),
        PMPY_VALIDATION("Please select a Trading Partner."),
        EXPORT_TP_VALIDATION("Please select a Trading Partner to export."),
        SPR_RESET("Are you sure you want to reset the values in the Sales Projection Selection section to the previous values?"),
        PMPY_MAND_NO_CUST_CONT("Please select a Customer/Contract."),
        MESSAGE_BOX_YES("YES"),
        MESSAGE_BOX_NO("NO"),
        MESSAGE_BOX_OK("OK"),
        MESSAGE_BOX("OK");

        private String constant;

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

     
        RESET_CONFIRMATION("Reset Confirmation"),
        NO_PERIOD_SELECTED("No period selected"),
        NO_LEVEL_SELECTED("No Level Selected"),
        CONFIRM_OVERRIDE("Confirm Override"),
        EDIT_VIEW("Edit a View"),
        SAVE_CONFIRMATION("Save Confirmation"),
        SAVE_NAME_CHECK("Save Name Check"),
        CONFRIM_SAVE("Confirm Save"),
        CLOSE_CONFIRMATION("Close Confirmation"),
        CLOSE_SAVE_CONFIRMATION("Save?"),
        SUBMIT_CONFIRMATION("Confirm Submit"),
        SUBMIT_MISSING_VALUES("Missing Values"),
        NO_SEARCH_VALUES("No Search Value Entered"),
        NO_RESULTS("No Results"),
        NO_SEARCH_CRITERIA("No Search Criteria"),
        TP_LOOKUP_NO_TP_SELECTED("No Trading Partner selected"),
        CONFIRM_RESET("Reset?"),
        MESSAGE_BOX("OK");

        private String constant;

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
        MAND_PMPY_CLOSE_WORKSHEET_CONFIRMATION("Are you sure you want to Close the Worksheet?"),
        MESSAGE_BOX_YES("YES"),
        MESSAGE_BOX_NO("NO"),
        MESSAGE_BOX_OK("OK"),
        MESSAGE_BOX("OK");

        private String constant;

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
                     * Calculates the Annual value which is same as history year
                     * count
                     *
                     * @return the annual count
                     */
                    public int getAnnualCount() {
                        return CalendarConstants.HISTORY_YEAR_COUNT.getConstant();
                    }

                    /**
                     * Calculates the semi-Annual value which is 2 * history
                     * year count
                     *
                     * @param historyYearCount historyYearCount value
                     * @return the semi-annual count
                     */
                    public int getSemiAnnualCount() {
                        return NumericConstants.TWO * CalendarConstants.HISTORY_YEAR_COUNT.getConstant();
                    }

                    /**
                     * Calculates the Quarterly value which is 4 * history year
                     * count
                     *
                     * @param historyYearCount historyYearCount value
                     * @return the Quarterly count
                     */
                    public int getQuarterCount() {
                        return NumericConstants.FOUR  * CalendarConstants.HISTORY_YEAR_COUNT.getConstant();
                    }

                    /**
                     * Calculates the Monthly value which is 12 * history year
                     * count
                     *
                     * @param historyYearCount historyYearCount value
                     * @return the Monthly count
                     */
                    public int getMonthCount() {
                        return NumericConstants.TWELVE * CalendarConstants.HISTORY_YEAR_COUNT.getConstant();
                    }

                    /**
                     * Gives the current quarter of current month
                     *
                     * @return
                     */
                    public int getCurrentQuarter() {
                        return CalendarConstants.CURRENT_MONTH.getConstant() / NumericConstants.THREE + 1;
                    }

                    /**
                     * Returns the current + projection count
                     *
                     * @return returns the total no. of projection periods
                     */
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

}
