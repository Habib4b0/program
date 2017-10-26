/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.utils;

import java.util.Arrays;
import java.util.Calendar;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class Constants {

    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
    /**
     * The session id
     */
    public static final String SESSION_ID = "sessionId";
    /**
     * The user id.
     */
    public static final String USER_ID = "userId";
    /**
     * The Constant ZERO.
     */
    public static final int ZERO = 0;
    public static final String CHECK_BOX = "checkBox";
    public static final String LIST_NAME = "listName";
    public static final String ALL = "ALL";
    public static final String DESCRIPTION = "description";
    public static final String REBATE_SCHEDULE_TYPE = "RS_TYPE";
    public static final String REBATE_PROGRAM_TYPE = "RS_PROGRAM_TYPE";
    public static final String ZEROSTRING = "0";
    public static final String REBATE_SCHEDULE_CATEGORY = "RS_CATEGORY";
    public static final String REASON_CODES = "REASON_CODES";
    public static final String FIXED_DOLLAR_ADJUSTMENT = "Fixed Dollar Adjustment";
    public static final String Populate = "populateBtn1";
    public static final String Populate1 = "populateBtn2";
    public static final String CALCULATE = "calculateBtn";
    public static final String APPROVE = "approveBtn";
    public static final String REJECT = "rejectBtn";
    public static final String STOP = "stopBtn";
    public static final String WITHDRAW = "withdrawBtn";
    public static final String CANCELWORKFLOW = "cancelBtn";
    public static final String SAVE = "saveBtn";
    public static final String SUBMIT = "saveBtn";
    public static final String ADD_NOTE = "addNote";
    public static final String REMOVE_NOTE = "remove";
    public static final String VIEW_MODE = "View";
    public static final String EDIT_MODE = "Edit";
    public static final String COPY_MODE = "Copy";
    public static final String ADD_MODE = "Add";
    public static final String CHECK = "check";
    /* The null.
     */
    public static final String NULL = "null";
    /**
     * The Constant ACCRUAL_DETAILS_COLUMNS.
     */
    public static final String SELECT_ONE = "-Select One-";
    public static final Object ACCRUAL_DETAILS_COLUMNS[] = new Object[]{
        "businessUnit", "costCenter", "customerNo", "customerName", "contractNo", "brand", "itemNo",
        "itemName", "accrualType", "programType", "programNo", "accrualPeriodStartDate", "accrualPeriodEndDate",
        "amount", "postingDate", "postingIndicator", "glDate", "glAccount", "subLedger", "subLedgerType", "documentType",
        "salesMasterId", "accrualId", "createdBy", "createdDate", "source", "batchId"};
    /**
     * The Constant ACCRUAL_DETAILS_HEADERS.
     */
    public static final String ACCRUAL_DETAILS_HEADERS[] = new String[]{
        "Business Unit", "Cost Center", "Customer No", "Customer Name", "Contract No", "Brand", "Item No",
        "Item Name", "Accrual Type", "Program Type", "Program No", "Accrual Period Start Date", "Accrual Period End Date",
        "Amount", "Posting Date", "Posting Indicator", "GL Date", "GL Account", "Sub Ledger", "Sub Ledger Type", "Document Type",
        "Sales Master ID", "Accrual ID", "Created By", "Created Date", "Source", "Batch ID"};
    /**
     * The Constant BASE_RATE_SUMMARY_COLUMNS.
     */
    public static final Object BASE_RATE_SUMMARY_COLUMNS[] = new Object[]{
        "checkRecord", "customerName", "currentRate", "suggestedRate", "accrualRate", "startDate", "endDate", "alternateContract",
        "alternateProduct", "methodology", "reasonCode", "notes"};
    /**
     * The Constant RESULT_HEADER.
     */
    public static final String BASE_RATE_SUMMARY_HEADERS[] = new String[]{
        StringUtils.EMPTY, "Customer/Product", "Current Rate", "Suggested Rate", "Accrual Rate", "Start Date", "End Date", "Alternate Contract",
        "Alternate Product", "Methodology", "Reason Code", "Notes"};
    /**
     * The Constant PROCESS_SCHEDULAR_COLUMNS.
     */
    public static final Object PROCESS_SCHEDULAR_COLUMNS[] = new Object[]{
        "processName", "status", "startDate", "endDate", "calendar", "frequencyRadio", "lastRunDate",
        "modifiedDate", "modifiedBy"};
    /**
     * The Constant PROCESS_SCHEDULAR_HEADER.
     */
    public static final String PROCESS_SCHEDULAR_HEADER[] = new String[]{
        "Process Name", "Status", "Start Date", "End Date", "Calendar", "Frequency", "Last Run Date",
        "Modified Date", "Modified By"};
    /**
     * The Constant MANUAL_PROCESS_SCHEDULAR_COLUMNS.
     */
    public static final Object MANUAL_PROCESS_SCHEDULAR_COLUMNS[] = new Object[]{
        "processName", "lastRunDate"};
    /**
     * The Constant MANUAL_PROCESS_SCHEDULAR_HEADER.
     */
    public static final String MANUAL_PROCESS_SCHEDULAR_HEADER[] = new String[]{
        "Process Name", "Last Run"};
    /**
     * The Constant FD_AVAILABLE_CUST_PROD_COLUMNS.
     */
    public static final Object FD_AVAILABLE_CUST_PROD_COLUMNS[] = new Object[]{
        "checkRecord", "id", "activeAdj", "pendingAdj"};
    /**
     * The Constant FD_AVAILABLE_CUST_PROD_HEADERS.
     */
    public static final String FD_AVAILABLE_CUST_PROD_HEADERS[] = new String[]{
        " ", "Customer/Product", "Active Adj", "Pending Adj"};
    /**
     * The Constant FD_RESULTS_COLUMNS.
     */
    public static final Object FD_RESULTS_COLUMNS[] = new Object[]{
        "id", "accrualPeriodSales", "accrualPeriodAccruals", "accrualPeriodActuals", "variance", "suggestedAdj"};
    /**
     * The Constant FD_RESULTS_HEADERS.
     */
    public static final String FD_RESULTS_HEADERS[] = new String[]{
        "Customer/Product", "Accrual Period Sales", "Accrual Period Accruals", "Accrual Period Actuals", "Variance", "Suggested Adj"};
    /**
     * The Constant FD_ADJ_IMPACT_COLUMNS.
     */
    public static final Object FD_ADJ_IMPACT_COLUMNS[] = new Object[]{
        "year", "actuals", "accruals", "remainingEstimate", "projectedRemainingEstimate"};
    /**
     * The Constant FD_ADJ_IMPACT_HEADERS.
     */
    public static final String FD_ADJ_IMPACT_HEADERS[] = new String[]{
        "Year", "Actuals", "Accruals", "Remaining Estimate", "Projected Remaining Estimate"};
    public static final String CHECK_RECORD = "checkRecord";
    public static final String FILTERCOMBOBOX = "filterComboBox";

    /**
     * Enum for Module/Screen/Functionality indicator constants.
     */
    public enum IndicatorConstants {

        /**
         * The indicator Base Rate index.
         */
        INDICATOR_BASE_RATE_INDEX("BaseRateIndex"),
        SELECT_ONE("-Select One-"),
        SHOW_ALL("Show All"),
        INDICATOR_FIXED_DOLLAR_INDEX("FixedDollarAdjustIndex"),
        INDICATOR_MAIL_SERVER_INDEX("MailServerConfigIndex"),
        INDICATOR_PROCESS_SCHEDULER_INDEX("ProcessScheduler"),
        MODE_CONTRACT("Contract"),
        MODE_BRAND("Brand"),
        SALES("Sales"),
        PAYMENTS("Payments"),
        RATE("Rate"),
        MANUAL("Manual"),
        AVERAGE("Average"),
        WEIGHTED_AVERAGE("Weighted Average:"),
        TIME_WEIGHTED_AVERAGE("Time Weighted Average"),
        MOVING_AVERAGE("Moving Average:"),
        GROWTH("Growth"),
        PROVISION_GROWTH("Provision Growth Rate:"),
        SALES_GROWTH("Sales Growth Rate:"),
        DUMPING_FACTOR("Damping Factor:"),
        EXPONENTIAL_SMOOTHING("Exponential Smoothing"),
        ENABLE("Enable"),
        DISABLE("Disable"),
        CUST_PROD("Customer/Product"),
        TRANSACTIONAL("Transaction"),
        CUSTOMER("Customer"),
        PRODUCT("Product"),
        PERCENTAGE("%"),
        DOLLAR("$"),
        YES("Yes"),
        NO("No"),
        INTERVAL("Interval"),
        TIME("Time"),
        SELECT_STATUS("Select Status"),
        ACTIVE("Active"),
        INACTIVE("Inactive"),
        HOLIDAY_SCHEDULE("Holiday Schedule"),
        HOLIDAY("Holiday"),
        SELECT_HOUR("Select Hour"),
        SELECT_MINUTE("Select Minute"),
        NDC8("NDC8"),
        NDC10("NDC10"),
        NDC11("NDC11"),
        BRAND("Brand"),
        NDCs("NDC"),
        DEMAND("Demand"),
        PIPELINE("Pipeline"),
        AUTO_RECONCILE("Auto Reconcile"),
        RATE_ADJUSTMENT("Rate Adjustment"),
        ACCRUALS("Accruals"),
        ACTUALS("Actuals"),
        GROSS_TRADE_SALES("Gross Trade Sales"),
        INVENTORY_WITHDRAWS("Inventory Withdraws"),
        CONTRACT_SALES("Contract Sales"),
        ALL("All"),
        INDICATOR_CUSTOMER_GROUP("Customer Group"),
        INDICATOR_PRODUCT_GROUP("Product Group"),
        REASON_CODE("Reason Code"),
        NOTES("Notes"),
        INDEX("Index"),
        RESERVE_ACC("Reserve Acc"),
        /**
         * The indicator public view.
         */
        INDICATOR_PUBLIC_VIEW("Public View"),
        /**
         * The indicator private view.
         */
        INDICATOR_PRIVATE_VIEW("Private View"),
        PROJECTIONS("Projections"),
        ITEM("Item"),
        MMddyyyy("MM/dd/yyyy"),
        yyyyMMddhhmmssSSS("yyyy-MM-dd hh:mm:ss.SSS"),
        MMddyyyyhhmmss("MM/dd/yyyy hh:mm:ss"),
        SUMMARY("Summary"),
        DETAIL("Detail");
        /**
         * The constant.
         */
        private String constant;

        /**
         * Instantiates a new indicator constants.
         *
         * @param constant the constant
         */
        private IndicatorConstants(String constant) {
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

    /**
     * Enum for Frequency constants
     */
    public enum FrequencyConstants {

        ANNUAL("Annual"),
        ANNUALLY("Annually"),
        SEMI_ANNUALLY("Semi-Annually"),
        YEAR("Year"),
        SEMI_ANNUAL("Semi-Annual"),
        QUARTERLY("Quarterly"),
        MONTHLY("Monthly"),
        MONTHS("Months"),
        QUARTERS("Quarters"),
        Q("Q");
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
     * Window messages enum.
     */
    public enum WindowMessagesMain {

        RESET_CRITERIA("Are you sure you want to reset the selection criteria to its default state?"),
        SALES_ALLOCATION_SAVE_CONFIRMATION("Are you sure you want to save the Sales Allocation?"),
        SALES_NO_METHODOLOGY_SELECTED("There is no methodology selected. Please select a methodology to continue."),
        SALES_NO_RECORD_SELECTED("There are no records selected. Please select at least one record to update."),
        SALES_SELECT_LEVEL_EXPAND("There is no level selected to expand. Please select a level to continue."),
        SALES_SELECT_LEVEL_COLLAPSE("There is no level selected to collapse. Please select a level to continue."),
        UNIFIED_RESET_CRITERIA("Are you sure you want to reset the page to default values?"),
        UNIFIED_SAVE_MESSAGE("Save record <record name>?"),
        UNIFIED_SAVE_NOTIFICATION("<record name> has been successfully saved"),
        UNIFIED_DELETE_MESSAGE("Are you sure you want to delete record <record name>?"),
        UNIFIED_DELETE_NOTIFICATION("<record name> has been successfully deleted"),
        DP_RESET_CRITERIA("Are you sure you want to reset the ‘Discount Projection Selections’?"),
        REPLACE_STRING("<record name>");
        private String constant;

        /**
         * Instantiates a new label constants.
         *
         * @param constant the constant
         */
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
     * Enum for label constants.
     */
    public enum LabelConstants {

        /**
         * The private view.
         */
        PRIVATE_VIEW("Private View"),
        /**
         * The public view.
         */
        PUBLIC_VIEW("Public View"),
        /**
         * The mode.
         */
        MODE("Mode"),
        /**
         * The mode add.
         */
        MODE_ADD("Add"),
        /**
         * The mode search.
         */
        MODE_SEARCH("Search"),
        PRIVATE("Private"),
        /**
         * The public.
         */
        PUBLIC("Public");
        private String constant;

        /**
         * Gets the constant.
         *
         * @return the constant
         */
        /**
         * Instantiates a new label constants.
         *
         * @param constant the constant
         */
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
    };

    /**
     * Enum for calculating the periods.
     */
    public enum CalculatePeriods implements CalendarInterface {

        /**
         * The calculate.
         */
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
                        return ((2 * CalendarConstants.HISTORY_YEAR_COUNT.getConstant()) + (CalendarConstants.CURRENT_MONTH.getConstant() / 6));
                    }

                    /**
                     * Calculates the Quarterly value which is 4 * history year
                     * count
                     *
                     * @param historyYearCount historyYearCount value
                     * @return the Quarterly count
                     */
                    public int getQuarterCount() {
                        return ((4 * CalendarConstants.HISTORY_YEAR_COUNT.getConstant()) + (CalendarConstants.CURRENT_MONTH.getConstant() / 3));
                    }

                    /**
                     * Calculates the Monthly value which is 12 * history year
                     * count
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
     * Interface for calculating the periods.
     */
    private interface CalendarInterface {

        /**
         * Calculates the Annual value which is same as history year count.
         *
         * @return the annual count
         */
        public int getAnnualCount();

        /**
         * Calculates the semi-Annual value which is 2 * history year count.
         *
         * @return the semi-annual count
         */
        public int getSemiAnnualCount();

        /**
         * Calculates the Quarterly value which is 4 * history year count.
         *
         * @return the Quarterly count
         */
        public int getQuarterCount();

        /**
         * Calculates the Monthly value which is 12 * history year count.
         *
         * @return the Monthly count
         */
        public int getMonthCount();

        /**
         * Calculates the Current Quarter value which is current month / 3 + 1.
         *
         * @return the Current Quarter
         */
        public int getCurrentQuarter();

        /**
         * Returns the current + projection count.
         *
         * @return returns the total no. of projection periods
         */
        public int getProjectionYear();
    }

    /**
     * Enum for Calendar constants.
     */
    public enum CalendarConstants {

        /**
         * The current year.
         */
        CURRENT_YEAR(Calendar.getInstance().get(Calendar.YEAR)),
        /**
         * The current month.
         */
        CURRENT_MONTH(Calendar.getInstance().get(Calendar.MONTH)),
        /**
         * The history year count.
         */
        HISTORY_YEAR_COUNT(3),
        /**
         * The projection year count.
         */
        PROJECTION_YEAR_COUNT(3),
        CHANNELS_HISTORY(3);
        /**
         * The constant.
         */
        private int constant;

        /**
         * Instantiates a new calendar constants.
         *
         * @param constant the constant
         */
        private CalendarConstants(int constant) {
            this.constant = constant;
        }

        /**
         * Gets the constant.
         *
         * @return the constant
         */
        public int getConstant() {
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
    public static final String COUNTER_VALUE = "counterValue";
    public static final String RESETCOUNT = "ResetCount";
    public static final String ADD_SPACE = "&nbsp;";
    public static final boolean FALSE = false;
    public static final boolean TRUE = true;
    public static final String DOCUMENT_NAME = "documentName";
    public static final String DATE_ADDED = "dateAdded";
    public static final String USER_NAME = "userName";
    public static final String ADD = "ADD";
    public static final String WORD_EXPORT = "Export to word";
    public static final String PDF_EXPORT = "Export to PDF";
    public static final String TABLE_HEADER_STYLE = "table-header-normal";
    public static final Object NULL_OBJECT = null;
    public static final String TEXT_FIELD = "textfield";
    public static final String RESEON_CODE = "Reason Code";
    public static final String PERCENT = "%";
    /**
     * The btn left.
     */
    public static final String BTN_LEFT = "<";
    /**
     * The projection id.
     */
    public static final String PROJECTION_ID = "projectionId";
    public static final String[] ATTACHMENT_HEADER = new String[]{"Document Name", "Date Added", "User Name"};
    public static final Object[] ATTACHMENT_COLUMNS = new Object[]{Constants.DOCUMENT_NAME, Constants.DATE_ADDED, Constants.USER_NAME};

    /**
     * Enum for Message constants
     */
    public enum MessageConstants {

        SEARCH_COMPLETED("Search Completed"),
        NO_SEARCH_RESULTS("Search Completed"),
        NO_SEARCH_CRITERIA_TITLE("No Search Criteria"),
        NO_SEARCH_CRITERIA_BODY("No search criteria were found. Please enter search criteria and try again."),
        CONFIRM_DELETION_TITLE("Confirm Deletion"),
        CONFIRM_DELETION_BODY("Are you sure you want to delete the selected projection?"),
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
    public static final Object PROCESS_MONITOR_COLUMNS[] = new Object[]{
        "processName", "processType", "calender", "modifiedDate", "modifiedBy"};
    public static final String PROCESS_MONITOR_HEADER[] = new String[]{
        "Monitor Name", "Process Type", "Calendar", "Modified Date", "Modified By"};

    /**
     * Enum for common constants
     */
    public enum BasReportVariables {

        CHECK_ALL("Check All"),
        ACCRUAL("Accruals"),
        ACCRUALS_BASE_RATE("Accruals - Base Rate"),
        ACCRUALS_MANUAL_ADJUSTMENTS("Accruals - Manual Adjustments"),
        ACCRUALS_RECONCILIATION("Accruals - Reconciliation"),
        ACCRUALS_OTHER("Accruals - Other"),
        PROJECTED_DEDUCTION_AMOUNT("Projected Deduction Amount"),
        DEDUCTIONS("Deductions Amount"),
        PROJ_DEDUCTION_RATE("Proj. Deduction Rate"),
        DEDUCTION_RATE("Deduction Rate"),
        PROJ_CONTRACT_SALES_AMT("Proj.Contract Sales Amt"),
        CONTRACT_SALES_AMT("Contract Sales Amt"),
        PROJ_CONTRACT_SALES_UNITS("Proj. Contract Sales units"),
        CONTRACT_SALES_UNITS("Contract Sales Units"),
        DEMAND_AMOUNT("Demand Amount"),
        DEMAND_UNITS("Demand Units"),
        GTS_AMOUNT("GTS Amount"),
        GTS_UNITS("GTS Units"),
        INVENTORY_WITHDRAWAL_AMOUNT("Inventory Withdrawal Amount"),
        INVENTORY_WITHDRAWAL_UNITS("Inventory Withdrawal Units"),
        PER_OF_PROJ_DEMAND("% of Proj. Demand"),
        PER_OF_DEMAND("% of Demand"),
        PER_OF_PROJ_GTS("% of Proj. GTS"),
        PER_OF_GTS("% of GTS"),
        PER_OF_PROJ_INVENTORY_WITHDRAWALS("% of Proj. Inventory Withdrawals"),
        PER_OF_INVENTORY_WITHDRAWALS("% of Inventory Withdrawals"),
        PER_ACHIEVED_ACCRUALS_TO_PROJ_DEDUCTIONS("% Achieved - Accruals to Proj. Deductions"),
        VARIANCE_ACCRUALS_TO_PROJ_DEDUCTIONS("Variance - Accruals to Proj. Deductions"),
        PER_ACHIEVED_ACCRUALS_TO_DEDUCTIONS("% Achieved - Accruals to Deductions"),
        VARIANCE_ACCRUALS_TO_DEDUCTIONS("Variance - Accruals to Deductions"),
        PER_ACHIEVED_DEDUCTIONS_TO_PROJ_DEDUCTIONS("% Achieved - Deductions to Proj. Deductions"),
        VARIANCE_DEDUCTIONS_AMT_TO_PROJ_DEDUCTIONS_AMT("Variance - Deductions Amt to Proj. Deductions Amt"),
        VARIANCE_DEDUCTIONS_RATE_TO_PROJ_DEDUCTION_RATE("Variance - Deductions Rate to Proj. Deduction Rate"),
        PER_ACHIEVED_CONTRACT_SALES_TO_PROJ_CONTRACT_SALES("% Achieved - Contract Sales to Proj. Contract Sales"),
        VARIANCE_CONTRACT_SALES_TO_PROJ_CONTRACT_SALES("Variance - Contract Sales to Proj. Contract Sales"),
        PER_ACHIEVED_CONTRACT_UNITS_TO_PROJEC_CONTRACT_UNITS("% Achieved - Contract Units to Projec. Contract Units"),
        VARIANCE_CONTRACT_UNITS_TO_PROJ_CONTRACT_UNITS("Variance - Contract Units to Proj. Contract Units");
        private String constant;

        private BasReportVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(BasReportVariables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

        public static String[] getCheckAllVariables() {
            return Arrays.toString(ArrayUtils.removeElement(BasReportVariables.values(), CHECK_ALL)).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }
    }

    /**
     * Enum for common constants
     */
    public enum HistoryVariables {

        CHECK_ALL("Check All"),
        ACCRUALS_BASE_RATE("Accruals - Base Rate"),
        ACCRUALS_MANUAL_ADJUSTMENTS("Accruals - Manual Adjustments"),
        ACCRUALS_RECONCILIATION("Accruals - Reconciliation"),
        ACCRUALS_OTHER("Accruals - Other");

        private String constant;

        private HistoryVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(HistoryVariables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

        public static String[] getCheckAllVariables() {
            return Arrays.toString(ArrayUtils.removeElement(HistoryVariables.values(), CHECK_ALL)).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }
    }

}
