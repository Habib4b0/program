/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.ui.form;

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

    public static String[] RESON_CODES = {"Added new Trading Partner", "Added to Formulary", "Class limitation", "Decrease in Membership", "Deleted Trading Partner Other", "Increase in Membership", "New Contract Discount Rates", "Other", "Removed from Formulary"};

    /**
     * Enum for label constants
     */
    public enum LabelConstants {

        PROJECTION_SEARCH("Projection Search"),
        PROJECTION_NAME("Projection Name"),
        PROJECTION_DESCRIPTION("Projection Description"),
        PRIVATE_VIEW("Private View"),
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
        PERC_OF_BUSSINESS("% of Business"),
        CONTRACT_SALES_AT_WAC("Contract Sales @ WAC"),
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
        DASH("- - -");
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
        BTN_PMPY("PMPY"),
        BTN_CALCULATE("CALCULATE"),
        BTN_ADD("ADD"),
        BTN_UPDATE("UPDATE"),
        BTN_ADD_TP("ADD TP"),
        BTN_EXPORT_TP("EXPORT TP"),
        ALL("All"),
        SELECT("Select"),
        BTN_REMOVE("REMOVE");
        private String constant;

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
        INDICATOR_CUSTOMER("Customer"),
        INDICATOR_BRAND("Brand"),
        INDICATOR_DATA_SELECTION_TAB("DataSelectionTab"),
        INDICATOR_DATA_SELECTION_INDEX("DataSelectionIndex"),
        INDICATOR_MODULE_NON_MANDATED("Non Mandated"),
        INDICATOR_MODULE_MANDATED("Mandated"),
        INDICATOR_MODULE_CHANNELS("Channels");
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
        COLON(":"),
        HTML_SPACE("&nbsp;"),
        SELECT_ONE("-Select One-"),
        DATE_FORMAT("MM/dd/yyyy"),
        REASON_CODE("Reason Code"),
        NULL("null"),
        ACTION_EDIT("edit"),
        ACTION_VIEW("view"),
        ACTION_GENERATE("add"),
        COMMENTARY("Commentary"),
        VALUE("value"),
        VARIANCE("variance"),
        CHANGE("change");
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

        REGEX_EXTRACT_DIGITS("\\D+");
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
        PMPY_NO_CH_TP("No Contract Holder/Trading Partner entered"),
        PMPY_MAND_NO_CUST_CONT("No Customer/Contract Selected."),
        PMPY_BOTH_PERIODS_SELECTED("Periods in Both List Views Selected"),
        PMPY_CALCULATE_NO_LIVES("No Lives"),
        PMPY_CALCULATE_NO_TOTAL_LIVES("No Total Lives"),
        PMPY_NO_PROJECTION_PERIOD_TOTAL("No Projection Period Total"),
        PMPY_IMPORT_CONFIRMATION("Replace sales confirmation"),
        NO_RESULTS("No Results"),
        NO_SEARCH_CRITERIA("No Search Criteria"),
        TP_LOOKUP_NO_TP_SELECTED("No Trading Partner selected"),
        CONFIRM_RESET("Reset?"),
        MAND_LOAD_CONTRACT_SP("Load Contracts"),
        CALCULATION_ERROR("Calculate Error"),
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
        CLOSE_ALTERNATE_HISTORY("Closing the popup will not submit an alternate Contract Holder and/or Brand back to the Sales Projection screen.\nAre you sure you want to continue?"),
        CUSTOM_TREE_SAVE("Are you sure you want to save the current Tree Structure?"),
        CUSTOM_TREE_SAVE_VALIDATION("That view name is taken.\nPlease enter a new View name."),
        PMPY_NO_CH_TP("Please select a Contract Holder or Trading Partner."),
        PMPY_RESET_SELECTION("Are you sure you want to reset the selection criteria to its default state?"),
        PMPY_POPULATE_NO_PERIOD("There are no historical periods selected from the Contract Holder History list view or the Trading Partner History list view.\nPlease select at least one historical period and try again."),
        PMPY_BOTH_PERIODS_SELECTED("Historical periods can only be selected from either the Contract Holder list view or the Trading Partner list view.\nPlease try again."),
        PMPY_CALCULATE_NO_LIVES("Please enter a numeric value for Lives."),
        PMPY_CALCULATE_NO_TOTAL_LIVES("Please enter a numeric value for Total Lives."),
        PMPY_RESET_CONFIRMATION("Are you sure you want to reset the Calculator group box back to its default settings?"),
        PMPY_NO_PROJECTION_PERIOD_TOTAL("Please complete the PMPY calculation before clicking IMPORT."),
        PMPY_IMPORT_CONFIRMATION("Are you sure you want to use the calculated Sales/Units value for the selected Trading Partner?"),
        PMPY_CLOSE_CONFIRMATION("Are you sure you want to Close the Calculator?"),
        TP_LOOKUP_NO_RESULTS("There were no results found that match the entered search criteria.\nPlease try again."),
        TP_LOOKUP_SEARCH_VALIDATION("Please enter a value to search for."),
        TP_LOOKUP_RESET_CRITERIA("Are you sure you want to reset the search criteria?"),
        TP_LOOKUP_NO_TP_SELECTED("Please select a Trading Partner from the list view."),
        TP_LOOKUP_RESET("Are you sure you want to reset the lookup to its default state?"),
        TP_LOOKUP_CLOSE("Are you sure you want to close the trading partner lookup?"),
        MAND_ALT_HIST_SUBMIT("Closing the popup will not submit an alternate Contract and/or Brand back to the Sales Projection screen.\nAre you sure you want to continue?"),
        MAND_PMPY_SELECT_AVAIL_PROD("Please select an Available Product from the list view."),
        MAND_PMPY_ENTER_PROJ_PER_TOTAL("You did not enter in the entire ‘Projection Period Total’.\nPlease try again."),
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
     * Enum for logic purpose
     */
    public enum LogicConstants {

        VIEW_TYPE();
        private String constant;

        private LogicConstants() {
        }

        public String getConstant() {
            return constant;
        }

        public String getViewType(final String indicator) {
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
        private String constant;

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
        private int constant;

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

        DOT(".");
        private String constant;

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

        DOT(".");
        private String constant;

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
     * Enum for Date format constants
     */
    public enum DateFormatConstants {

        MMddyyyy("MM/dd/yyyy"),
        yyyyMMddhhmmssSSS("yyyy-MM-dd hh:mm:ss.SSS"),
        MMddyyyyhhmmss("MM/dd/yyyy hh:mm:ss");
        private String constant;

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
}
