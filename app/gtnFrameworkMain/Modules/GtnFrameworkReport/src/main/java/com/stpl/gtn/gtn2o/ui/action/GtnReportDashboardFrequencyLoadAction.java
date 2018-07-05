package com.stpl.gtn.gtn2o.ui.action;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnReportDashboardFrequencyLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent vaadinFrequencyInReportingDashboardBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabFrequency", componentId);

		loadFromTo(vaadinFrequencyInReportingDashboardBaseComponent.getStringCaptionFromV8ComboBox(), componentId);
	}

	private void loadFromTo(String selectedFrequency, String componentId) {

		try {
			int startSid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_fromPeriod", componentId)
					.getIntegerFromV8ComboBox();
			int endSid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_STATUS", componentId)
					.getIntegerFromV8ComboBox();

			String endString = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_STATUS", componentId)
					.getStringCaptionFromV8ComboBox().replaceAll(" - ", " ");
			String startString = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_fromPeriod", componentId)
					.getStringCaptionFromV8ComboBox().replaceAll(" - ", " ");
			String frequency = getFrequency(startString);

			LocalDate startDate = parseDate(startString, frequency);
			LocalDate endDate = parseDate(endString, frequency);

			Set<String> dateString = new LinkedHashSet<>();
			Set<Integer> periodSid = new LinkedHashSet<>();

			for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusMonths(1)) {
				getFormat(periodSid, startSid, dateString, selectedFrequency, date);
				startSid++;
			}
			getFormat(periodSid, endSid, dateString, selectedFrequency, endDate);

			List dataNew = new ArrayList<>(dateString);
			List periodSidData = new ArrayList<>(periodSid);

			GtnUIFrameworkBaseComponent reportingDashboard_displaySelectionTabPeriodRangeFromBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeFrom", componentId);
			GtnUIFrameworkComponentConfig reportingDashboard_displaySelectionTabPeriodRangeFromComponentConfig = reportingDashboard_displaySelectionTabPeriodRangeFromBaseComponent
					.getComponentConfig();
			GtnUIFrameworkComboBoxConfig periodRangeFrom = reportingDashboard_displaySelectionTabPeriodRangeFromComponentConfig
					.getGtnComboboxConfig();
			periodRangeFrom.setItemCaptionValues(dataNew);
			periodRangeFrom.setItemValues(periodSidData);

			GtnUIFrameworkComboBoxComponent periodRangeFromComboBox = new GtnUIFrameworkComboBoxComponent();
			periodRangeFromComboBox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					"reportingDashboard_displaySelectionTabPeriodRangeFrom", componentId, Arrays.asList(""));

			GtnUIFrameworkBaseComponent reportingDashboard_displaySelectionTabPeriodRangeToBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeTo", componentId);
			GtnUIFrameworkComponentConfig reportingDashboard_displaySelectionTabPeriodRangeToComponentConfig = reportingDashboard_displaySelectionTabPeriodRangeToBaseComponent
					.getComponentConfig();
			GtnUIFrameworkComboBoxConfig periodRangeTo = reportingDashboard_displaySelectionTabPeriodRangeToComponentConfig
					.getGtnComboboxConfig();
			periodRangeTo.setItemCaptionValues(dataNew);
			periodRangeTo.setItemValues(periodSidData);

			GtnUIFrameworkComboBoxComponent periodRangeToComboBox = new GtnUIFrameworkComboBoxComponent();
			periodRangeToComboBox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					"reportingDashboard_displaySelectionTabPeriodRangeTo", componentId, Arrays.asList(""));

		} catch (Exception e) {

		}

	}

	private LocalDate parseDate(String periodStart, String selectedFreq) {
		LocalDate startDate = null;
		int previousQuaterLastMonth = 0;
		String[] yearData = periodStart.split("\\s+");
		Month startMonth = null;

		switch (selectedFreq) {

		case "Quarter":
			previousQuaterLastMonth = (Character.getNumericValue(yearData[0].charAt(1)) - 1) * 3;
			startMonth = Month.of(previousQuaterLastMonth + 1);
			startDate = LocalDate.of(Integer.valueOf(yearData[1]), startMonth, 1);
			break;
		case "Semi-Annual":
			previousQuaterLastMonth = (Character.getNumericValue(yearData[0].charAt(1))) > 1 ? 6 : 1;
			startMonth = Month.of(previousQuaterLastMonth);
			startDate = LocalDate.of(Integer.valueOf(yearData[1]), startMonth, 1);
			break;
		case "Annual":
			startDate = LocalDate.of(Integer.valueOf(yearData[0]), 1, 1);
			break;
		case "Month":
			startDate = LocalDate.of(Integer.valueOf(yearData[1]), getMonthIntegerFromYear(yearData[0]), 1);
			break;
		default:
			break;
		}
		return startDate;
	}
	
	private int getMonthIntegerFromYear(String month) {
		int monthCount = 0;
		switch (month.toUpperCase()) {
		case "JAN":
			monthCount = 1;
			break;
		case "FEB":
			monthCount = 2;
			break;
		case "MAR":
			monthCount = 3;
			break;
		case "APR":
			monthCount = 4;
			break;
		case "MAY":
			monthCount = 5;
			break;
		case "JUN":
			monthCount = 6;
			break;
		case "JUL":
			monthCount = 7;
			break;
		case "AUG":
			monthCount = 8;
			break;
		case "SEP":
			monthCount = 9;
			break;
		case "OCT":
			monthCount = 10;
			break;
		case "NOV":
			monthCount = 11;
			break;
		case "DEC":
			monthCount = 12;
			break;

		}
		return monthCount;
	}

	private String getFrequency(String startString) {
		Pattern quaterPattern = Pattern.compile("^([Q])([1-4])*");
		Pattern semiAnnualPattern = Pattern.compile("^([S])([1-2])*");
		Pattern yearPattern = Pattern.compile("^([0-9])*");
		if (quaterPattern.matcher(startString).find()) {
			return "Quarter";
		} else if (semiAnnualPattern.matcher(startString).find()) {
			return "Semi-Annual";
		} else if (yearPattern.matcher(startString).find()) {
			return "Annual";
		}
		return "Month";
	}

	private Month getStartMonth(String[] start) {
		int previousQuaterLastMonth = (Character.getNumericValue(start[0].charAt(1)) - 1) * 3;
		Month startMonth = Month.of(previousQuaterLastMonth + 1);
		return startMonth;
	}

	private static void getFormat(Set<Integer> periodSid, int startSid, Set<String> dateString2,
			String selectedFrequency, LocalDate date) {
		String theDate = "";
		switch (selectedFrequency) {
		case "Quarter":
			theDate = getQuaterly(date);
			break;
		case "Semi-Annual":
			theDate = getSemiAnnual(date);
			break;
		case "Annual":
			theDate = getAnnual(date);
			break;
		case "Month":
			theDate = getMOnthly(date);
			break;
		default:
			break;
		}
		if (dateString2.add(theDate)) {
			periodSid.add(startSid);
		}
	}

	private static String getMOnthly(LocalDate date) {

		return date.getMonth().name().substring(0, 3) + " " + date.getYear();
	}

	private static String getAnnual(LocalDate date) {
		return String.valueOf(date.getYear());
	}

	private static String getSemiAnnual(LocalDate date) {
		return "S" + (date.get(IsoFields.QUARTER_OF_YEAR) > 2 ? 2 : 1) + " " + date.getYear();
	}

	private static String getQuaterly(LocalDate date) {
		return "Q" + date.get(IsoFields.QUARTER_OF_YEAR) + " " + date.getYear();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
