package com.stpl.gtn.gtn2o.ui.action;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;

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
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString(),
						componentId)
				.loadV8ComboBoxComponentValue(dataSelectionBean.getFrequency());

		loadFromTo(dataSelectionBean.getFrequencyName(), componentId);
	}

	private void loadFromTo(String selectedFrequency, String componentId) {

		try {
			int startSid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_fromPeriod", componentId)
					.getIntegerFromV8ComboBox();
			int endSid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_STATUS", componentId)
					.getIntegerFromV8ComboBox();
			String[] start = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_fromPeriod", componentId)
					.getStringCaptionFromV8ComboBox().split("\\s+");
			String[] end = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_STATUS", componentId)
					.getStringCaptionFromV8ComboBox().split("\\s+");
			int previousQuaterLastMonth = (Character.getNumericValue(start[0].charAt(1)) - 1) * 3;
			Month startMonth = Month.of(previousQuaterLastMonth + 1);
			LocalDate startDate = LocalDate.of(Integer.valueOf(start[2]), startMonth, 1);
			Month endMonth = Month.of(Character.getNumericValue(end[0].charAt(1)) * 3);
			LocalDate endDate = LocalDate.of(Integer.valueOf(end[2]), endMonth, 1);

			Set<String> dateString = new LinkedHashSet<>();
			Set<Integer> periodSid = new LinkedHashSet<>();

			for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusMonths(1)) {
				getFormat(periodSid, startSid, dateString, selectedFrequency, date);
				startSid++;
			}
			getFormat(periodSid, endSid, dateString, selectedFrequency, endDate);

			List dataNew = new ArrayList<>(dateString);
			List periodSidData = new ArrayList<>(periodSid);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeFrom", componentId)
					.addAllItemsToComboBox(dataNew, periodSidData);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeTo", componentId)
					.addAllItemsToComboBox(dataNew, periodSidData);
		} catch (Exception e) {

		}

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
