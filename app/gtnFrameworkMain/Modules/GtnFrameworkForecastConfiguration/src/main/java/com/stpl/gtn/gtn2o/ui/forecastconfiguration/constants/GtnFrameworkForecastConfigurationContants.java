/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.forecastconfiguration.constants;

import java.util.Date;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkForecastConfigurationContants {
	public static final String EXCEL_BUTTONLAYOUT = "excelButtonlayout";
	public static final String PROCESS_TYPE = "processType";
	public static final String DATA_CONFIGURATION_LAYOUT = "dataConfigurationLayout";
	public static final String HISTORICAL_DATA_TIME_PERIOD_TO_LAYOUT = "historicalDataTimePeriodToLayout";
	public static final String FORECAST_CONFIGURATION = "Forecast Configuration";
	public static final String FORECAST_CONFIGURATION_LAYOUT = "forecastConfigurationLayout";
	public static final String FORECAST_CONFIGURATION_CSS_LAYOUT = "forecastConfigurationCssLayout";
	public static final String MODE_LAYOUT = "modeLayout";
	public static final String FORECAST_CONFIGURATION_PANEL = "forecastConfigurationPanel";
	public static final String HISTORICAL_DATA_TIME_PERIOD_TO = "historicalDataTimePeriodTo";
	public static final String DATA_FREQUENCY = "dataFrequency";
	public static final String PROCESS_CONFIG_LAYOUT = "processConfigLayout";
	public static final String HISTORICAL_DATA_INTERVAL = "historicalDataInterval";
	public static final String FC_VIEW = "FCView";
	public static final String INTERVAL_FREQUENCY = "intervalFrequency";
	public static final String HORIZONTAL = "horizontal";
	public static final String SEARCH_RESULT_TABLE = "searchResultTable";
	public static final String ACTION_BUTTONLAYOUT = "actionButtonlayout";
	public static final String BUSINESS_PROCESS_TYPE = "BUSINESS_PROCESS_TYPE";
	public static final String FORECAST_CONFIGURATION_MAIN_PANEL = "forecastConfigurationMainPanel";
	public static final String HISTORICAL_DATA_PERIOD = "historicalDataPeriod";
	public static final String BUSINESS_PROCESS_LAYOUT = "businessProcessLayout";
	public static final String MODE = "mode";
	public static final String DATA_FREQUENCY_LAYOUT = "dataFrequencyLayout";
	public static final String HISTORICAL_DATA_TIME_PERIOD_FROM_LAYOUT = "historicalDataTimePeriodFromLayout";
	public static final String FORECAST_CONFIGURATION_MAIN_LAYOUT = "forecastConfigurationMainLayout";
	public static final String PROCESS_CONFIG_MAIN_LAYOUT = "processConfigMainLayout";
	public static final String BUSINESS_PROCESS = "businessProcess";
	public static final String ACTION_BUTTON_CSSLAYOUT = "actionButtonCsslayout";
	public static final String HISTORICAL_DATA_TIME_PERIOD_FROM = "historicalDataTimePeriodFrom";
	public static final String FUTURE_INTERVAL = "futureInterval";
	public static final String FORECAST_PERIOD = "forecastPeriod";
	public static final String INTERVAL_FREQUENCY_LAYOUT = "intervalFrequencyLayout";
	public static final String FUTURE_INTERVAL_LAYOUT = "futureIntervalLayout";

	private GtnFrameworkForecastConfigurationContants() {
	}

	public static final String ACTION_PACKAGE = "com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.";
	public static final String FORECAST_CONFIGURATION_FREQUENCY_LIST_NAME = "forecastConfigFrequency";
	private static final Object[] FORECAST_CONFIGURATION_TABLE_COLUMNS = new Object[] { "businessProcess",
			"processType", "mode", "fromDateSearch", "toDateSearch", "historicalInterval", "historicalValue",
			"futureInterval", "futureValue", "versionNo", "fromPeriod", "toPeriod", "createdBy", "activeFlag" };

	private static final String[] FORECAST_CONFIGURATION_TABLE_HEADER = new String[] { "Business Process",
			"Process Type", "Process Mode", "From Date", "To Date", "Historic Interval", "Historic Period",
			"Future Interval", "Future Period", "Version", "From Period", "To Period", "Created By", "Active Flag" };

	private static final Class<?>[] FORECAST_CONFIGURATION_TABLE_COLUMN_TYPE = new Class<?>[] { String.class,
			String.class, String.class, Date.class, Date.class, String.class, Integer.class, String.class,
			Integer.class, Integer.class, Date.class, Date.class, String.class, String.class };

	public static Object[] getForecastConfigurationTableColumns() {
		return FORECAST_CONFIGURATION_TABLE_COLUMNS.clone();
	}

	public static String[] getForecastConfigurationTableHeader() {
		return FORECAST_CONFIGURATION_TABLE_HEADER.clone();
	}

	public static Class<?>[] getForecastConfigurationTableColumnType() {
		return FORECAST_CONFIGURATION_TABLE_COLUMN_TYPE.clone();
	}

}
