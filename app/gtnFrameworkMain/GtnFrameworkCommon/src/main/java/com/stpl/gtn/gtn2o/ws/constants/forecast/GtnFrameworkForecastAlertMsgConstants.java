/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.constants.forecast;

/**
 *
 * @author STPL
 */
public final class GtnFrameworkForecastAlertMsgConstants {
    private GtnFrameworkForecastAlertMsgConstants(){
        /**
         * empty constructor
         */
    }

	public static final String DUAL_LIST_BOX_ITEM_SELECTION_VALIDATION_MSG_HEADER = "No Product Hierarchy Level Selected";

	public static final String DUAL_LIST_BOX_ITEM_SELECTION_VALIDATION_MSG = "No Level was selected to move. Please try again.";

	public static final String RETURNS_DATA_SELECTION_NEXT_BUTTON_ALERT_CONFIRMATION_HEADER = "Update Confirmation";

	public static final String RETURNS_DATA_SELECTION_NEXT_BUTTON_ALERT_CONFIRMATION_MSG = "Data Selection values have changed. All other tabs will be updated and unsaved data will be lost. Continue?";

	public static final String BUSINESS_UNIT_VALIDATION_MSG_HEADER = "File Not Available";

	public static final String BUSINESS_UNIT_VALIDATION_MSG = "No active file available for the selected Business Unit.";

	public static final String RETURNS_LEFT_TREE_TABLE_VALIDATION_MSG_HEADER = "No Hierarchy level selected";

	public static final String RETURNS_LEFT_TREE_TABLE_VALIDATION_MSG = "Please select a level in the hierarchy for the methodology";

	public static final String RETURNS_CLOSE_CONFRMATION_MSG_HEADER = "Close Confirmation";

	public static final String RETURNS_PROJECTION_CLOSE_CONFRMATION_MSG = "Are you sure you want to close this Projection?";

	public static final String RETURNS_CLOSE_AND_CONTIUE_CONFRMATION_MSG = "Closing without saving will delete the projection permanently. Continue?";

	public static final String RETURNS_SAVE_CONFRMATION_MSG_HEADER = "Save?";

	public static final String RETURNS_SAVE_CONFRMATION_MSG = "Any unsaved information will not be saved. Do you want to proceed?";

	public static final String NO_RECORD_SELECTED_MSG_HEADER = "No Record Selected";

	public static final String SELECT_MIN_ONE_RECORD_MSG = "Please select at-least one record";

	public static final String ERROR_MSG_HEADER = "Error";

	public static final String OVERRIDE_CALCULATION_MSG = "You must select at least one period of History to complete an override calculation.";

	public static final String ROLLING_ANNUAL_TREND_MSG = "The Rolling Annual Trend methodology requires a complete calendar year of periods to use as a baseline. "
			+ "\n Please select a complete calendar year of periods and try again.";

	public static final String NO_PERIOD_SELECTED_MSG_HEADER = "No Period Selected";

	public static final String NO_ATLEAST_2_PERIOD_SELECTED_MSG = "Please select at least two Historic Periods.";

	public static final String NO_ATLEAST_1_PERIOD_SELECTED_MSG = "Please select atleast one period.";

	public static final String NO_1_PERIOD_SELECTED_MSG = "Please select only one period.";

	public static final String BASELINE_PERIODS_WITHIN_MSG = "The selected baseline periods are within the Start Period and End Period range. "
			+ "\n Please select a baseline period that is prior to the calculation range.";

	public static final String BASELINE_PERIODS_AFTER_MSG = "The selected baseline periods are after the Start Period and End Period range."
			+ "\n  Please select a baseline period that is prior to the calculation range.";

	public static final String RESET_MESSAGE_HEADER = "Confirm Reset";

	public static final String RESET_MESSAGE_HEADER_MSG = "Are you sure you want to reset the page to default values?";

}
