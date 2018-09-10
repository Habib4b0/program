package com.stpl.gtn.gtn2o.ui.action;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnReportDashboardFrequencyLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportDashboardFrequencyLoadAction.class);

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
		GtnUIFrameworkBaseComponent annualTotal = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				GtnFrameworkReportStringConstants.REPORT_DASHBOARD_TAB + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_ANNUAL_TOTALS,
				componentId);
		if (vaadinFrequencyInReportingDashboardBaseComponent != null
				&& vaadinFrequencyInReportingDashboardBaseComponent.getStringCaptionFromV8ComboBox() != null
				&& annualTotal.getComponent() != null) {
			String selectedFrequency = vaadinFrequencyInReportingDashboardBaseComponent
					.getStringCaptionFromV8ComboBox();
			if (selectedFrequency.equals(GtnFrameworkReportStringConstants.ANNUAL)) {
				annualTotal.setComponentEnable(false);
				annualTotal.loadV8ComboBoxComponentValue("No");
			} else {
				annualTotal.setComponentEnable(true);
				annualTotal.loadV8ComboBoxComponentValue("Yes");
			}
			if (vaadinFrequencyInReportingDashboardBaseComponent.getComponentConfig().isUserOriginatedFlag()) {
				loadFromTo(selectedFrequency, componentId);
			}
		}

	}

	private void loadFromTo(String selectedFrequency, String componentId) {

		try {
			int startSid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_fromPeriod", componentId)
					.getIntegerFromV8ComboBox();
			int endSid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_STATUS", componentId)
					.getIntegerFromV8ComboBox();

			String endString = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_STATUS", componentId)

					.getStringCaptionFromV8ComboBox().replaceAll(" - ", " ");
			endString = endString.replaceAll("-", " ");
			String startString = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_fromPeriod", componentId).getStringCaptionFromV8ComboBox()
					.replaceAll(" - ", " ");
			startString = startString.replaceAll("-", " ");
			String frequency = getFrequency(startString);

			LocalDate startDate = parseDate(startString, frequency);
			LocalDate endDate = parseDate(endString, frequency);

			Set<String> dateString = new LinkedHashSet<>();
			Set<Integer> periodSid = new LinkedHashSet<>();

			for (LocalDate date = startDate; date != null && date.isBefore(endDate); date = date.plusMonths(1)) {
				getFormat(periodSid, startSid, dateString, selectedFrequency, date);
				startSid++;
			}
			getFormat(periodSid, endSid, dateString, selectedFrequency, endDate);

			List<String> dataNew = new ArrayList<>(dateString);
			List<Integer> periodSidData = new ArrayList<>(periodSid);

			GtnUIFrameworkBaseComponent componentFrom = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeFrom", componentId);
			if (componentFrom.getComponent() != null) {
				componentFrom.addAllItemsToComboBox(dataNew, periodSidData);
				componentFrom.loadV8ComboBoxComponentValue("0");
			}

			GtnUIFrameworkBaseComponent componentTo = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeTo", componentId);
			if (componentTo.getComponent() != null) {
				componentTo.addAllItemsToComboBox(dataNew, periodSidData);
				componentTo.loadV8ComboBoxComponentValue("0");
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}

	}

	private LocalDate parseDate(String periodStart, String selectedFreq) {
		LocalDate startDate = null;
		int previousQuaterLastMonth = 0;
		String[] yearData = periodStart.split("\\s+");
		Month startMonth = null;

		switch (selectedFreq) {

		case GtnFrameworkReportStringConstants.QUARTER:
			previousQuaterLastMonth = (Character.getNumericValue(yearData[0].charAt(1)) - 1) * 3;
			startMonth = Month.of(previousQuaterLastMonth + 1);
			startDate = LocalDate.of(Integer.parseInt(yearData[1]), startMonth, 1);
			break;
		case GtnFrameworkReportStringConstants.SEMI_ANNUAL:
			previousQuaterLastMonth = (Character.getNumericValue(yearData[0].charAt(1))) > 1 ? 6 : 1;
			startMonth = Month.of(previousQuaterLastMonth);
			startDate = LocalDate.of(Integer.parseInt(yearData[1]), startMonth, 1);
			break;
		case GtnFrameworkReportStringConstants.ANNUAL:
			startDate = LocalDate.of(Integer.parseInt(yearData[0]), 1, 1);
			break;
		case GtnFrameworkReportStringConstants.MONTH:
			startDate = LocalDate.of(Integer.parseInt(yearData[1]), getMonthIntegerFromYear(yearData[0]), 1);
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
		default:
			break;
		}
		return monthCount;
	}

	private String getFrequency(String startString) {
		Pattern semiAnnualPattern = Pattern.compile("^([S])([1-2])*");
		if (Pattern.matches("[A-Z&&[Q]]{1}..\\d*", startString)) {
			return GtnFrameworkReportStringConstants.QUARTER;
		} else if (semiAnnualPattern.matcher(startString).find()) {
			return GtnFrameworkReportStringConstants.SEMI_ANNUAL;
		} else if (Pattern.matches("[0-9]{4}", startString)) {
			return GtnFrameworkReportStringConstants.ANNUAL;
		}
		return GtnFrameworkReportStringConstants.MONTH;
	}

	public Month getStartMonth(String[] start) {
		int previousQuaterLastMonth = (Character.getNumericValue(start[0].charAt(1)) - 1) * 3;
		return Month.of(previousQuaterLastMonth + 1);
	}

	private static void getFormat(Set<Integer> periodSid, int startSid, Set<String> dateString2,
			String selectedFrequency, LocalDate date) {
		String theDate = "";
		switch (selectedFrequency) {
		case GtnFrameworkReportStringConstants.QUARTER:
			theDate = getQuaterly(date);
			break;
		case GtnFrameworkReportStringConstants.SEMI_ANNUAL:
			theDate = getSemiAnnual(date);
			break;
		case GtnFrameworkReportStringConstants.ANNUAL:
			theDate = getAnnual(date);
			break;
		case GtnFrameworkReportStringConstants.MONTH:
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
		return "S" + (date.get(IsoFields.QUARTER_OF_YEAR) > 2 ? 2 : 1) + " - " + date.getYear();
	}

	private static String getQuaterly(LocalDate date) {
		return "Q" + date.get(IsoFields.QUARTER_OF_YEAR) + " - " + date.getYear();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
