/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.periodconfiguration.constants;

import java.util.Date;

import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action.GtnFrameworkModeFromToAction;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action.GtnFrameworkPeriodConfigSaveAction;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action.GtnFrameworkPeriodConfigurationValidation;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action.GtnFrameworkPeriodViewAction;

public class GtnFrameworkPeriodConfigurationContants {
	public static final String PERIOD_DATE_FORMAT = "MM/dd/yyyy";
	public static final String PERIOD_VIEW_ACTION = GtnFrameworkPeriodViewAction.class.getName();
	public static final String PERIOD_VIEW_MODE_FROM_TO_ACTION = GtnFrameworkModeFromToAction.class.getName();
	public static final String PERIOD_CONFIG_SAVE_ACTION = GtnFrameworkPeriodConfigSaveAction.class.getName();
	public static final String PERIOD_VALUE_VALIDATION_ACTION = GtnFrameworkPeriodConfigurationValidation.class
			.getName();
	public static final String CONFORMATION = " Confirmation ";
	public static final String PERIOD_CONFIGURATION_SAVE_ALERT = " Are you sure you want to save the Period Configuration? ";
	public static final String PERIOD_CONFIGURATION_MANDATORY_CHECK_ALERT = "Please ensure all mandatory fields are populated.";
	public static final String PERIOD_CONFIGURATION_DEFAULT_DATE_RANGE = "Please ensure that the value in the ‘Default Date’ field is within the ‘Date’ range.";
	public static final String PERIOD_TEXT_BOX_VALIDATION = "The value entered in the ‘Period’ field should be before the system date when in the ‘From’ or ‘Historical’ group boxes, and should be after the system date when in the ‘To’ group box.";
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final Class<String> PERIOD_CONFIG_STRING_DATATYPE = String.class;
	public static final Class<Integer> PERIOD_CONFIG_INTEGER_DATATYPE = Integer.class;
	public static final Class<Date> PERIOD_CONFIG_DATE = Date.class;
	public static final String DEFAULT_DATE_TO = "defaultDateTo";
	public static final String DEFAULT_PERIOD_TO_LAYOUT = "defaultPeriodToLayout";
	public static final String MODE_TO = "modeTo";
	public static final String DEFAULT_MODE_FROM_COMPONENT = "defaultModeFromComponent";
	public static final String DEFAULT_PERIOD = "Default Period";
	public static final String PERIOD_TO = "periodTo";
	public static final String DEFAULT_PERIOD_TO = "defaultPeriodTo";
	public static final String BUSINESS_UNIT = "businessUnit";
	public static final String DEFAULT_PERIOD_FROM_TEXT_BOX = "defaultPeriodFromTextBox";
	public static final String PERIOD_VIEW = "periodView";
	public static final String SINGLE = "Single";
	public static final String MULTIPLE = "Multiple";
	public static final String AUTO = "Auto";
	public static final String PERIOD_CONFIG_MAIN_LAYOUT = "PeriodConfigurationMainLayout";
	public static final String PERIOD_CONFIG_LAYOUT = "periodConfigurationLayout";
	public static final String NEGATIVE_ONLY_MSG = "Can contains  Negative Numbers and 0";
	public static final String POSITIVE_ONLY_MSG = "Can contains  Positive and Negative Numbers and Zero";
	public static final String ONE_HUNDRED_TWENTY_SEVEN_PERCENT = "127%";
	public static final String ONE_HUNDRED_PERCENT = "100%";
        public static final String FIFTY_PERCENT = "50%";
	private static final String[] PERIOD_CONFIGURATION_DOUBLE_HEADER_VISIBLE_COLUMNS = { "group1", "group2", "group3" };
	private static final String[] PERIOD_CONFIGURATION_DOUBLE_HEADER_VISIBLE_HEADER = { "", "From Period",
			"To Period" };
	private static final String FROM_DEF_PERIOD_DATE = "fromDefPeriodDate";
	public static final String TO_PERIOD_DATE = "toPeriodDate";
	public static final String FROM_PERIOD_DATE = "fromPeriodDate";
	public static final String TO_DEF_PERIOD_DATE = "toDefPeriodDate";
	private static final String[] PERIOD_CONFIGURATION_TABLE_FIELD_FACTORY = { FROM_PERIOD_DATE, FROM_DEF_PERIOD_DATE,
			TO_PERIOD_DATE, TO_DEF_PERIOD_DATE };
	public static final String FREQUENCY_FROM_COMPONENT = "frequencyFromComponent";
	public static final String PERIOD_TO_LAYOUT = "PeriodToLayout";
	public static final String PERIOD_CONFIGURATION_LAYOUT = "periodConfigurationLayout";
	public static final String DEFAULT_FREQUENCY_TO = "defaultFrequencyTo";
	public static final String ACTION_BUTTONLAYOUT = "actionButtonlayout";
	public static final String DEFAULT_MODE_TO = "defaultModeTo";
	public static final String DEFAULT_FREQUENCY_FROM = "defaultFrequencyFrom";
	public static final String BUSINESS_PROCESS = "businessProcess";
	public static final String PERIOD_CONFIG_FROM_TO_LAYOUT = "periodConfigFromToLayout";
	public static final String PERIOD_CONFIGURATION_MAIN_LAYOUT = "PeriodConfigurationMainLayout";
	public static final String DEFAULT_PERIOD_FROM = "defaultPeriodFrom";
	public static final String SELECTION_LAYOUT = "selectionLayout";
	public static final String PERIOD_FROM_LAYOUT = "periodFromLayout";
	public static final String PERIOD_FROM_COMPONENT_LAYOUT = "periodFromComponentLayout";
	public static final String DEFAULT_DATE_FROM = "defaultDateFrom";
	public static final String PERIOD = "Period";
	public static final String PERIOD_FROM = "periodFrom";
	public static final String PERIOD_FROM_TEXT_BOX = "periodFromTextBox";
	public static final String COMPANY = "company";
	public static final String DEFAULT_PERIOD_FROM_LAYOUT = "defaultPeriodFromLayout";
	public static final String DATE_FROM = "dateFrom";
	public static final String FREQUENCY = "Frequency";
	public static final String MODULE = "module";
	public static final String DEFAULT_PERIOD_TO_TEXT_BOX = "defaultPeriodToTextBox";
	public static final String FREQUENCY_TO = "frequencyTo";
	public static final String MODE_FROM_COMPONENT = "modeFromComponent";
	public static final String PERIOD_TO_COMPONENT_LAYOUT = "periodToComponentLayout";
	public static final String DATE_TO = "dateTo";
	public static final String PERIOD_TO_TEXT_BOX = "periodToTextBox";
        public static final String VALIDATE_TO_DATES = "The ‘To Period’ default date cannot be after the ‘To Period’ date.";
        public static final String VALIDATE_START_DATES = "The value entered in the ‘Period’ field cannot be before Current - 60 months when in the ‘From’ and ‘To’ group boxes.";
        public static final String VALIDATE_FROM_DEFAULT_DATES = "The ‘From Period’ default date cannot be before the ‘From Period’ date.";
        public static final String VALIDATE_DATE_RANGE = "The ‘To Period’ date cannot be before the ‘From Period’ date.";
        public static final String VALIDATE_DEFAULT_DATES = "The ‘To Period’ default date cannot be before the ‘From Period’ default date.";
	private static final String[] FROM_FIELDS = { MODE_FROM_COMPONENT, DEFAULT_MODE_FROM_COMPONENT,
			FREQUENCY_FROM_COMPONENT, DEFAULT_FREQUENCY_FROM, PERIOD_FROM, DEFAULT_PERIOD_FROM, PERIOD_FROM_TEXT_BOX,
			DEFAULT_PERIOD_FROM_TEXT_BOX, DATE_FROM, DEFAULT_DATE_FROM };

	private static final String[] TO_FIELDS = { MODE_TO, DEFAULT_MODE_TO, FREQUENCY_TO, DEFAULT_FREQUENCY_TO, PERIOD_TO,
			DEFAULT_PERIOD_TO, PERIOD_TO_TEXT_BOX, DEFAULT_PERIOD_TO_TEXT_BOX, DATE_TO, DEFAULT_DATE_TO };
	private static final String[] PERIOD_CONFIGURATION_TABLE_COLUMNS = { "moduleName", "buscinessProcessName",
			"companyName", "bucinsessUnitName", "periodViewName", "versionNo", "createdBy", "activeFlag",
			"fromModeName", "fromFrequencyName", "fromPeriodValue", FROM_PERIOD_DATE, "fromDefModeName",
			"fromDefFrequencyName", "fromDefPeriodValue", FROM_DEF_PERIOD_DATE, "toModeName", "toFrequencyName",
			"toPeriodValue", TO_PERIOD_DATE, "toDefModeName", "toDefFrequencyName", "toDefPeriodValue",
			TO_DEF_PERIOD_DATE };

	private static final String[] PERIOD_CONFIGURATION_TABLE_HEADER = { "Module", "Business Process", "Company",
			"Business Unit", "Period View", "Version", "Created By", "Active Flag", " Mode", " Frequency", " Period",
			" Date", " Default Mode", " Default Frequency", " Default Period", " Default Date", " Mode", " Frequency",
			" Period", " Date", " Default Mode", " Default Frequency", " Default Period", " Default Date" };

	private static final Object[] PERIOD_CONFIGURATION_GROUP_1 = { "moduleName", "buscinessProcessName", "companyName",
			"bucinsessUnitName", "periodViewName", "versionNo", "createdBy", "activeFlag" };

	private static final Object[] PERIOD_CONFIGURATION_GROUP_2 = { "fromModeName", "fromFrequencyName",
			"fromPeriodValue", FROM_PERIOD_DATE, "fromDefModeName", "fromDefFrequencyName", "fromDefPeriodValue",
			FROM_DEF_PERIOD_DATE };

	private static final Object[] PERIOD_CONFIGURATION_GROUP_3 = { "toModeName", "toFrequencyName", "toPeriodValue",
			TO_PERIOD_DATE, "toDefModeName", "toDefFrequencyName", "toDefPeriodValue", TO_DEF_PERIOD_DATE };

	private static final Class[] PERIOD_CONFIGURATION_TABLE_COLUMN_TYPE = new Class[] { PERIOD_CONFIG_STRING_DATATYPE,
			PERIOD_CONFIG_STRING_DATATYPE, PERIOD_CONFIG_STRING_DATATYPE, PERIOD_CONFIG_STRING_DATATYPE,
			PERIOD_CONFIG_STRING_DATATYPE, PERIOD_CONFIG_STRING_DATATYPE, PERIOD_CONFIG_STRING_DATATYPE,
			PERIOD_CONFIG_STRING_DATATYPE, PERIOD_CONFIG_STRING_DATATYPE, PERIOD_CONFIG_STRING_DATATYPE,
			PERIOD_CONFIG_STRING_DATATYPE, PERIOD_CONFIG_DATE, PERIOD_CONFIG_STRING_DATATYPE,
			PERIOD_CONFIG_STRING_DATATYPE, PERIOD_CONFIG_STRING_DATATYPE, PERIOD_CONFIG_DATE,
			PERIOD_CONFIG_STRING_DATATYPE, PERIOD_CONFIG_STRING_DATATYPE, PERIOD_CONFIG_STRING_DATATYPE,
			PERIOD_CONFIG_DATE, PERIOD_CONFIG_STRING_DATATYPE, PERIOD_CONFIG_STRING_DATATYPE,
			PERIOD_CONFIG_STRING_DATATYPE, PERIOD_CONFIG_DATE };

	private GtnFrameworkPeriodConfigurationContants() {

	}

	public static String[] getPeriodConfigurationTableHeader() {
		return PERIOD_CONFIGURATION_TABLE_HEADER.clone();
	}

	public static String[] getPeriodConfigurationTableColumns() {
		return PERIOD_CONFIGURATION_TABLE_COLUMNS.clone();
	}

	public static Class[] getPeriodConfigurationTableColumnType() {
		return PERIOD_CONFIGURATION_TABLE_COLUMN_TYPE.clone();
	}

	public static Object[] getPeriodConfigurationGroup1() {
		return PERIOD_CONFIGURATION_GROUP_1.clone();
	}

	public static Object[] getPeriodConfigurationGroup2() {
		return PERIOD_CONFIGURATION_GROUP_2.clone();
	}

	public static Object[] getPeriodConfigurationGroup3() {
		return PERIOD_CONFIGURATION_GROUP_3.clone();
	}

	public static String[] getPeriodConfigurationDoubleHeaderVisibleColumns() {
		return PERIOD_CONFIGURATION_DOUBLE_HEADER_VISIBLE_COLUMNS.clone();
	}

	public static String[] getPeriodConfigurationDoubleHeaderVisibleHeader() {
		return PERIOD_CONFIGURATION_DOUBLE_HEADER_VISIBLE_HEADER.clone();
	}

	public static String[] getPeriodConfigurationTableFieldFactory() {
		return PERIOD_CONFIGURATION_TABLE_FIELD_FACTORY.clone();
	}

	public static String[] getFromFields() {
		return FROM_FIELDS.clone();
	}

	public static String[] getToFields() {
		return TO_FIELDS.clone();
	}
}
