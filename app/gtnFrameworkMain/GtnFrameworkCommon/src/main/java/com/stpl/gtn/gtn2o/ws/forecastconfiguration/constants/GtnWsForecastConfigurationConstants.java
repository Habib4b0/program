/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecastconfiguration.constants;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsForecastConfigurationConstants {
    private GtnWsForecastConfigurationConstants(){
        /**
         * empty constructor
         */
    }
	public static final String GTN_FORECAST_CONFIGURATION_SERVICE = "/GtnForecastConfigurationService";
	public static final String HISTORY_INTERVAL_VALUE_CHANGE = "/historyIntervalValueChange";
	public static final String FUTURE_FREQUENCY_VALUE_CHANGE = "/futureFrequencyValueChange";
	public static final String LOAD_FORECAST_PERIOD = "/loadForecastPeriod";
	public static final String GET_FORECAST_CONF_TABLE_DATA = "/getForecastConfTableData";
	public static final String FROM_PERIOD_VALUE_CHANGE = "/fromPeriodValueChange";
	public static final String TO_PERIOD_VALUE_CHANGE = "/toPeriodValueChange";
	public static final String CHECK_SAVE_FORECAST_CONF = "/checkSaveForecastConfiguration";
	public static final String SAVE_FORECAST_CONF = "/saveForecastConfiguration";
	public static final String EX_FACTORY_SALES = "Ex-Factory Sales";
	public static final String ENTERED_FUTUREPERIOD = "The entered Future period is beyond the GTS file. All periods beyond the GTS file will display zeros in the Forecast";
	public static final String MODE_VALUE_INTERVAL = "Interval";
	public static final String MODE_VALUE_PERIOD = "Period";
	public static final String PROCESS_TYPE_VALUE_AUTO_UPDATE = "Auto Update";
	public static final String PROCESS_TYPE_VALUE_DEFINED = "Defined";
	public static final String ANNUAL = "Annual";

	public static final String SEMI_ANNUAL = "Semi-Annual";

	public static final String QUARTER = "Quarter";

	public static final String MONTHLY = "Month";
	public static final String HISTORY = "History";
}
