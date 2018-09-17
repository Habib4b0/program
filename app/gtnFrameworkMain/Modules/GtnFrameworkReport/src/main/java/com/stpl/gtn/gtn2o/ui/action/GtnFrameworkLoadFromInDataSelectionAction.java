package com.stpl.gtn.gtn2o.ui.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkLoadFromInDataSelectionAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkLoadFromInDataSelectionAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("configure params");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			String fromSourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
			GtnWsReportDataSelectionBean fromDataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fromSourceComponentId).getComponentData().getSharedPopupData();
			String frequency = fromDataSelectionBean.getFrequencyName();
			fromDataSelectionBean.setFromOrToForDataSelection("FROM");
			GtnUIFrameworkWebserviceRequest fromServiceRequest = new GtnUIFrameworkWebserviceRequest();
			GtnWsReportRequest reportFromServiceRequest = new GtnWsReportRequest();
			reportFromServiceRequest.setDataSelectionBean(fromDataSelectionBean);
			fromServiceRequest.setGtnWsReportRequest(reportFromServiceRequest);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsReportConstants.GTN_REPORT_SERVICE
							+ GtnWsReportConstants.GTN_WS_REPORT_DASHBOARD_LOAD_FROM_AND_TO_IN_DATA_SELECTION,
					"report", fromServiceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			String fromPeriodAndYearInLandingScreen = gtnUIFrameWorkActionConfig.getActionParameterList().size() > 1
					&& !"null".equals(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1)))
							? gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString()
							: GtnUIFrameworkGlobalUI
									.getVaadinBaseComponentFromParent("reportLandingScreen_fromPeriod", componentId)
									.getStringCaptionFromV8ComboBox();

			fromPeriodAndYearInLandingScreen = fromPeriodAndYearInLandingScreen.replaceAll(" ", "");

			fromPeriodAndYearInLandingScreen = fromPeriodAndYearInLandingScreen.replaceAll("-", "");

			List<String> fromItemValueList1 = response.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList();
			List<String> fromItemCodeList1 = response.getGtnUIFrameworkWebserviceComboBoxResponse().getItemCodeList();

			List<String> itemValueList = new ArrayList<>(fromItemValueList1);
			List<String> itemCodeList = new ArrayList<>(fromItemCodeList1);

			String fromStringToBeCompared;

			fromStringToBeCompared = getTheStringToBeCompared(frequency, fromPeriodAndYearInLandingScreen);

			int fromRange = getRangeToBeRemoved(itemValueList, fromStringToBeCompared);
			if (fromRange > 0 && fromRange < itemValueList.size()) {
				itemValueList.subList(0, fromRange).clear();
				itemCodeList.subList(0, fromRange).clear();
			}

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeFrom", componentId)
					.addAllItemsToComboBox(itemValueList, itemCodeList);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeFrom", componentId)
					.loadV8ComboBoxComponentValue(itemCodeList.get(0));

		} catch (ParseException e) {
			logger.error(e.getMessage());
		}

	}

	public static int getRangeToBeRemoved(List<String> itemValueList, String stringToBeCompared) {
		int count = 0;
		for (String s : itemValueList) {
			if (s.equals(stringToBeCompared))
				return count;
			else
				count++;
		}
		return count;
	}

	public static String getTheStringToBeCompared(String frequency, String periodAndYearInLandingScreen)
			throws ParseException {
		String stringToBeCompared = null;

		if (Pattern.matches("[0-9]{4}", periodAndYearInLandingScreen)) {
			stringToBeCompared = convertFromYearToSelectedFrequency(frequency, periodAndYearInLandingScreen);
		} else if (Pattern.matches("[A-Z&&[Q]]{1}\\d*", periodAndYearInLandingScreen)) {
			stringToBeCompared = convertFromQuarterToSelectedFrequency(frequency, periodAndYearInLandingScreen,
					stringToBeCompared);
		} else if (Pattern.matches("[A-Z&&[S]]{1}\\d*", periodAndYearInLandingScreen)) {
			stringToBeCompared = convertFromSemiAnnualToSelectedFrequency(frequency, periodAndYearInLandingScreen,
					stringToBeCompared);
		} else if (Pattern.matches("[a-zA-Z]{3}\\d*", periodAndYearInLandingScreen)) {
			stringToBeCompared = convertFromMonthBasedOnSelectedFrequency(frequency, periodAndYearInLandingScreen);
		}
		return stringToBeCompared;
	}

	public static String convertFromMonthBasedOnSelectedFrequency(String frequency, String periodAndYearInLandingScreen)
			throws ParseException {
		String stringToBeCompared;
		Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(periodAndYearInLandingScreen.substring(0, 3));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		month++;
		if (frequency.startsWith("M")) {
			stringToBeCompared = periodAndYearInLandingScreen.substring(0, 3) + " "
					+ periodAndYearInLandingScreen.substring(3);
		} else if (frequency.startsWith("Q")) {
			int part = returnMonth(month);
			stringToBeCompared = "Q" + part + " - " + periodAndYearInLandingScreen.substring(3);
		} else if (frequency.startsWith("S")) {
			int part = month <= 6 ? 1 : 2;
			stringToBeCompared = "S" + part + "-" + periodAndYearInLandingScreen.substring(3);
		} else {
			stringToBeCompared = periodAndYearInLandingScreen.substring(3);
		}
		return stringToBeCompared;
	}

	private static int returnMonth(int month) {
		if (month <= 3) {
			return 1;
		} else if (month <= 6) {
			return 2;
		} else if (month <= 9) {
			return 3;
		} else {
			return 4;
		}
	}

	public static String convertFromSemiAnnualToSelectedFrequency(String frequency, String periodAndYearInLandingScreen,
			String stringToBeCompared) {
		String returnString;
		if (frequency.startsWith("M")) {
			int semiAnnualIdForMonth = Character.getNumericValue(periodAndYearInLandingScreen.charAt(1));
			returnString = getTheStringToBeCompared(periodAndYearInLandingScreen, stringToBeCompared,
					semiAnnualIdForMonth);
		} else if (frequency.startsWith("Q")) {
			int part = Character.getNumericValue(periodAndYearInLandingScreen.charAt(1)) > 1 ? 3 : 1;
			returnString = "Q" + part + " - " + periodAndYearInLandingScreen.substring(2);
		} else if (frequency.startsWith("S")) {
			returnString = periodAndYearInLandingScreen.substring(0, 2) + "-"
					+ periodAndYearInLandingScreen.substring(2);
		} else {
			returnString = periodAndYearInLandingScreen.substring(2);
		}
		return returnString;
	}

	private static String getTheStringToBeCompared(String periodAndYearInLandingScreen, String stringToBeCompared,
			int semiAnnualIdForMonth) {
		String finalString = stringToBeCompared;
		if (semiAnnualIdForMonth == 1) {
			finalString = "Jan " + periodAndYearInLandingScreen.substring(2);
		} else if (semiAnnualIdForMonth == 2) {
			finalString = "Jul " + periodAndYearInLandingScreen.substring(2);
		}
		return finalString;
	}

	public static String convertFromQuarterToSelectedFrequency(String frequency, String periodAndYearInLandingScreen,
			String stringToBeCompared) {
		String returnString;
		if (frequency.startsWith("M")) {
			int quarterIdForMonth = Character.getNumericValue(periodAndYearInLandingScreen.charAt(1));
			returnString = getTheStringToBeComparedBasedOnQuarter(periodAndYearInLandingScreen, stringToBeCompared,
					quarterIdForMonth);
		} else if (frequency.startsWith("Q")) {
			returnString = periodAndYearInLandingScreen.substring(0, 2) + " - "
					+ periodAndYearInLandingScreen.substring(2);
		} else if (frequency.startsWith("S")) {
			int part = Character.getNumericValue(periodAndYearInLandingScreen.charAt(1)) > 2 ? 2 : 1;
			returnString = "S" + part + "-" + periodAndYearInLandingScreen.substring(2);
		} else {
			returnString = periodAndYearInLandingScreen.substring(2);
		}
		return returnString;
	}

	private static String getTheStringToBeComparedBasedOnQuarter(String periodAndYearInLandingScreen,
			String stringToBeCompared, int quarterIdForMonth) {
		String finalString = stringToBeCompared;
		switch (quarterIdForMonth) {
		case 1:
			finalString = "Jan " + periodAndYearInLandingScreen.substring(2);
			break;
		case 2:
			finalString = "Apr " + periodAndYearInLandingScreen.substring(2);
			break;
		case 3:
			finalString = "Jul " + periodAndYearInLandingScreen.substring(2);
			break;
		case 4:
			finalString = "Oct " + periodAndYearInLandingScreen.substring(2);
			break;
		default:
			break;
		}
		return finalString;
	}

	public static String convertFromYearToSelectedFrequency(String frequency, String periodAndYearInLandingScreen) {

		String finalString;
		if (frequency.startsWith("M")) {
			finalString = "Jan " + periodAndYearInLandingScreen;
		} else if (frequency.startsWith("Q")) {
			finalString = "Q1 - " + periodAndYearInLandingScreen;
		} else if (frequency.startsWith("S")) {
			finalString = "S1-" + periodAndYearInLandingScreen;
		} else {
			finalString = periodAndYearInLandingScreen;
		}
		return finalString;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
