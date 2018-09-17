/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnReportingVariableBreakdownHistoryLoadAction
		implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingVariableBreakdownHistoryLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Inside Configure Parameters");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> actionParams = gtnUIFrameWorkActionConfig.getActionParameterList();
			String fromPeriod = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParams.get(1).toString(), componentId)
					.getStringCaptionFromV8ComboBox();
			String frequency = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParams.get(2).toString(), componentId)
					.getStringCaptionFromV8ComboBox();

			String toPeriod = String.valueOf(LocalDate.now());

			fromPeriod = getVariableBreakdownPeriods(fromPeriod);
			long diffInMonths = ChronoUnit.MONTHS.between(LocalDate.parse(fromPeriod), LocalDate.parse(toPeriod));

			int quotient = 0;
			int historyPeriod = 0;
			List<String> historyPeriodCaptionList = new ArrayList<>();

			switch (frequency) {
			case "Quarter":
				quotient = (int) (diffInMonths / 3);
				historyPeriod = getRoundOffDates((int) diffInMonths, quotient, 3);
				historyPeriodCaptionList = getHistoryPeriodCaptionList(historyPeriod, historyPeriodCaptionList,
						"Quarters");
				break;
			case "Semi-Annual":
				quotient = (int) (diffInMonths / 6);
				historyPeriod = getRoundOffDates((int) diffInMonths, quotient, 6);
				historyPeriodCaptionList = getHistoryPeriodCaptionList(historyPeriod, historyPeriodCaptionList,
						"Semi-Annual Periods");

				break;
			case "Month":
				historyPeriod = (int) diffInMonths;
				historyPeriodCaptionList = getHistoryPeriodCaptionList(historyPeriod, historyPeriodCaptionList,
						"Months");

				break;
			case "Annual":
				historyPeriod = (int) ChronoUnit.YEARS.between(LocalDate.parse(fromPeriod), LocalDate.parse(toPeriod));
				historyPeriodCaptionList = getHistoryPeriodCaptionList(historyPeriod, historyPeriodCaptionList, "Year");
				break;
			default:
				historyPeriodCaptionList = new ArrayList<>();
				break;
			}

			if (historyPeriodCaptionList.isEmpty()) {
				return;
			}
			GtnUIFrameworkComboBoxConfig historyReloadComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParams.get(3).toString(), componentId).getComponentConfig()
					.getGtnComboboxConfig();
			historyReloadComboboxConfig.setItemValues(historyPeriodCaptionList);
			historyReloadComboboxConfig.setItemCaptionValues(historyPeriodCaptionList);

			GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
			combobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION, actionParams.get(3).toString(),
					componentId, Arrays.asList(""));
			if (!historyPeriodCaptionList.isEmpty()) {
				String defaultValue = null;
				switch (frequency) {
				case "Quarter":
					defaultValue = historyPeriodCaptionList.get(3);
					break;
				case "Semi-Annual":
					defaultValue = historyPeriodCaptionList.get(1);
					break;
				case "Month":
					defaultValue = historyPeriodCaptionList.get(11);
					break;
				case "Annual":
					defaultValue = historyPeriodCaptionList.get(0);
					break;
				default:
					break;
				}
				if (actionParams.size() > 4 && actionParams.get(4) != null) {
					defaultValue = String.valueOf(actionParams.get(4));
				}
				historyReloadComboboxConfig.setDefaultDesc(defaultValue);
				if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParams.get(3).toString(),
						componentId) != null) {
					GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(actionParams.get(3).toString(), componentId);
					baseComponent.loadV8ComboBoxComponentValue(defaultValue);
				}
			}
		} catch (Exception ex) {
			logger.error("Error message", ex);
		}
	}

	private List<String> getHistoryPeriodCaptionList(int historyPeriod, List<String> historyPeriodCaptionList,
			String frequencyFormat) {
		for (int i = 1; i <= historyPeriod; i++) {
			historyPeriodCaptionList.add(i + " " + frequencyFormat);
		}
		return historyPeriodCaptionList;
	}

	private int getRoundOffDates(int diffInMonths, int quotient, int num) {
		return diffInMonths % num >= 1 ? quotient + 1 : quotient;

	}

	public String getVariableBreakdownPeriods(String fromDate) {

		String dateFromPeriodQuery = "";

		String splitParameter = " ";

		String fromPeriod = fromDate;

		if (fromPeriod.contains("-")) {
			splitParameter = "-";
		}

		if (fromPeriod.startsWith("Q")) {
			List<String> quarterToDateForFromPeriod = getQuarterToDate(fromPeriod, splitParameter);
			dateFromPeriodQuery = getDateFromFrequency(quarterToDateForFromPeriod);
		} else if (fromPeriod.startsWith("S") && !fromPeriod.toLowerCase(Locale.ENGLISH).startsWith("sep")) {
			List<String> semiAnnualToDateForFromPeriod = getSemiAnnualToDate(fromPeriod, splitParameter);

			dateFromPeriodQuery = getDateFromFrequency(semiAnnualToDateForFromPeriod);

		} else if (fromPeriod.matches("[0-9]+")) {
			List<String> yearToDateForFromPeriod = new ArrayList<>();
			yearToDateForFromPeriod.add(fromPeriod);
			yearToDateForFromPeriod.add("01");
			yearToDateForFromPeriod.add("01");

			dateFromPeriodQuery = getDateFromFrequency(yearToDateForFromPeriod);

		} else {
			List<String> monthToDateForFromPeriod = new ArrayList<>();
			String[] monthToDateForFromPeriodSplit = fromPeriod.split(splitParameter);
			monthToDateForFromPeriod.add(monthToDateForFromPeriodSplit[1]);
			monthToDateForFromPeriod
					.add(GtnFrameworkReportStringConstants.getMonthIntegerFromYear(monthToDateForFromPeriodSplit[0]));
			monthToDateForFromPeriod.add("01");

			dateFromPeriodQuery = getDateFromFrequency(monthToDateForFromPeriod);
		}

		return dateFromPeriodQuery;
	}

	private String getDateFromFrequency(List<String> periodList) {
		StringBuilder dateStringBuilder = new StringBuilder();

		for (int i = 0; i < periodList.size(); i++) {
			if (i < periodList.size() - 1) {
				dateStringBuilder.append(periodList.get(i));
				dateStringBuilder.append('_');
			} else {
				dateStringBuilder.append(periodList.get(i));
			}
		}
		String date = dateStringBuilder.toString();
		return date.trim();
	}

	private List<String> getQuarterToDate(String fromPeriod, String splitParameter) {
		String[] quarterToDateSplit = fromPeriod.trim().split(splitParameter);
		List<String> quarterToDate = new ArrayList<>();
		quarterToDate.add(0, quarterToDateSplit[1].trim());

		quarterToDate.add(1, returnMonthOfQuarter(String.valueOf(quarterToDateSplit[0].trim().charAt(1))));

		quarterToDate.add(2, "01");
		return quarterToDate;
	}

	private List<String> getSemiAnnualToDate(String fromPeriod, String splitParameter) {
		String[] semiAnnual = fromPeriod.trim().split(splitParameter);
		List<String> semiAnnualToDate = new ArrayList<>();
		semiAnnualToDate.add(0, semiAnnual[1].trim());

		semiAnnualToDate.add(1, returnMonthOfSemiAnnual(String.valueOf(semiAnnual[0].trim().charAt(1))));

		semiAnnualToDate.add(2, "01");
		return semiAnnualToDate;
	}

	private String returnMonthOfSemiAnnual(String charAt) {
		String semiAnnualMonth = "";
		switch (charAt) {
		case "1":
			semiAnnualMonth = "01";
			break;
		case "2":
			semiAnnualMonth = "07";
			break;
		default:
			semiAnnualMonth = "0";
		}
		return semiAnnualMonth;
	}

	private String returnMonthOfQuarter(String charAt) {
		String quarterMonth = "";
		switch (charAt) {
		case "1":
			quarterMonth = "01";
			break;
		case "2":
			quarterMonth = "04";
			break;
		case "3":
			quarterMonth = "07";
			break;
		case "4":
			quarterMonth = "10";
			break;
		default:
			quarterMonth = "0";
		}
		return quarterMonth;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
