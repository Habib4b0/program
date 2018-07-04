package com.stpl.gtn.gtn2o.ui.action;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

			GtnUIFrameworkBaseComponent reportingDashboard_displaySelectionTabPeriodRangeFromBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeFrom", componentId);
			GtnUIFrameworkComponentConfig reportingDashboard_displaySelectionTabPeriodRangeFromComponentConfig = reportingDashboard_displaySelectionTabPeriodRangeFromBaseComponent
					.getComponentConfig();
			GtnUIFrameworkComboBoxConfig periodRangeFrom = reportingDashboard_displaySelectionTabPeriodRangeFromComponentConfig.getGtnComboboxConfig();
			periodRangeFrom.setItemCaptionValues(dataNew);
			periodRangeFrom.setItemValues(periodSidData);
			
			GtnUIFrameworkComboBoxComponent periodRangeFromComboBox = new GtnUIFrameworkComboBoxComponent();
			periodRangeFromComboBox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					"reportingDashboard_displaySelectionTabPeriodRangeFrom", componentId, Arrays.asList(""));
			
			GtnUIFrameworkBaseComponent reportingDashboard_displaySelectionTabPeriodRangeToBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeTo", componentId);
			GtnUIFrameworkComponentConfig reportingDashboard_displaySelectionTabPeriodRangeToComponentConfig = reportingDashboard_displaySelectionTabPeriodRangeToBaseComponent
					.getComponentConfig();
			GtnUIFrameworkComboBoxConfig periodRangeTo = reportingDashboard_displaySelectionTabPeriodRangeToComponentConfig.getGtnComboboxConfig();
			periodRangeTo.setItemCaptionValues(dataNew);
			periodRangeTo.setItemValues(periodSidData);
			
			GtnUIFrameworkComboBoxComponent periodRangeToComboBox = new GtnUIFrameworkComboBoxComponent();
			periodRangeToComboBox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					"reportingDashboard_displaySelectionTabPeriodRangeTo", componentId, Arrays.asList(""));
			

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
