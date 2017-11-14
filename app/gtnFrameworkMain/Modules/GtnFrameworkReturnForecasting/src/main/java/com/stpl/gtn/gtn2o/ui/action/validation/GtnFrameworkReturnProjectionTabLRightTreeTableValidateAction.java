package com.stpl.gtn.gtn2o.ui.action.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastAlertMsgConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.OptionGroup;

/**
 * @author Kalpana.Ramanana This class is used to Validate Tree Table Right
 *         header
 */
public class GtnFrameworkReturnProjectionTabLRightTreeTableValidateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkReturnProjectionTabLRightTreeTableValidateAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {
		return;

	}

	private GtnUIFrameworkComponentData getFrameworkComponentData(String componentId, String sourceComponentId) {
		gtnLogger.info("inside GtnFrameworkReturnProjectionTabLRightTreeTableValidateAction");
		return GtnUIFrameworkGlobalUI.getVaadinComponentData(String.valueOf(componentId), sourceComponentId);
	}

	private AbstractComponent getAbstractComponentData(String componentId, String sourceComponentId) {
		return GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(componentId), sourceComponentId);
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		loadDataFromService(componentId, gtnUIFrameWorkActionConfig);
	}

	private void loadDataFromService(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {

			List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
			List<String> checkPeriods = new ArrayList<>();
			int countCheckedPeriod = 0;
			String[] indexStartPeriod;
			String[] indexBaselinePeriod;

			FreezePagedTreeTable resultsTable = (FreezePagedTreeTable) getFrameworkComponentData(
					String.valueOf(actionParameterList.get(2)), componentId).getCustomDataList().get(0);

			final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();

			Object[] doubleHeaderVisibleColumns = rightTable.getDoubleHeaderVisibleColumns();

			for (Object object : doubleHeaderVisibleColumns) {

				if (rightTable.getDoubleHeaderColumnCheckBox(object)) {
					countCheckedPeriod = countCheckedPeriod + 1;
					checkPeriods.add(rightTable.getDoubleHeaderColumnHeader(object));
				}
			}

			OptionGroup forecastPeriodOrder = (OptionGroup) getAbstractComponentData(
					String.valueOf(actionParameterList.get(6)), componentId);

			if (gtnUIFrameWorkActionConfig.getFieldValues().get(0).equals(forecastPeriodOrder.getValue())) {
				Collections.reverse(checkPeriods);
			}

			countCheckedPeriod(componentId, countCheckedPeriod);

			ComboBox forecastTabMethodology = (ComboBox) getAbstractComponentData(
					String.valueOf(actionParameterList.get(1)), componentId);

			ComboBox forecastReturnsFrequency = (ComboBox) getAbstractComponentData(
					String.valueOf(actionParameterList.get(3)), componentId);

			ComboBox forecastTabStartPeriod = (ComboBox) getAbstractComponentData(
					String.valueOf(actionParameterList.get(4)), componentId);

			String selectedForecastTabMethodology = forecastTabMethodology
					.getItemCaption(forecastTabMethodology.getValue());

			String selectedForecastTabFrequency = forecastReturnsFrequency
					.getItemCaption(forecastReturnsFrequency.getValue());

			indexStartPeriod = getForecastTabFrequency(selectedForecastTabFrequency, forecastTabStartPeriod);

			indexBaselinePeriod = getForecastBaselinePeriod(selectedForecastTabFrequency, checkPeriods,
					countCheckedPeriod);

			validateMethodology(selectedForecastTabMethodology, countCheckedPeriod, selectedForecastTabFrequency,
					indexStartPeriod, indexBaselinePeriod, checkPeriods, componentId);

		} catch (Exception ex) {
			gtnLogger.error("Error in loadDataFromService Method", ex);
			throw new GtnFrameworkGeneralException("Error in loadDataFromService  Method", ex);
		}

	}

	private void validateMethodology(String selectedForecastTabMethodology, int countCheckedPeriod,
			String selectedForecastTabFrequency, String[] indexStartPeriod, String[] indexBaselinePeriod,
			List<String> checkPeriods, String componentId) throws GtnFrameworkGeneralException {

		switch (selectedForecastTabMethodology) {

		case GtnFrameworkForecastConstantCommon.EX_FACTORY_METHODOLOGY:
			singleMethodologyValidation(countCheckedPeriod, selectedForecastTabFrequency, indexStartPeriod,
					indexBaselinePeriod, componentId);
			break;

		case GtnFrameworkForecastConstantCommon.AVERAGE_METHODOLOGY:
			averageMethodologyValidation(countCheckedPeriod, selectedForecastTabFrequency, indexStartPeriod,
					indexBaselinePeriod, componentId);
			break;

		case GtnFrameworkForecastConstantCommon.QUARTILE_METHODOLOGY:
			otherMethodologyValidation(countCheckedPeriod, selectedForecastTabFrequency, indexStartPeriod,
					indexBaselinePeriod, componentId);
			break;

		case GtnFrameworkForecastConstantCommon.ROLLING_ANNUAL_TREND_METHODOLOGY:
			rollingAnnualTrendMethodologyValidation(countCheckedPeriod, selectedForecastTabFrequency, indexStartPeriod,
					indexBaselinePeriod, checkPeriods, componentId);
			break;

		case GtnFrameworkForecastConstantCommon.SINGLE_PERIOD_METHODOLOGY:
			singleMethodologyValidation(countCheckedPeriod, selectedForecastTabFrequency, indexStartPeriod,
					indexBaselinePeriod, componentId);
			break;

		case GtnFrameworkForecastConstantCommon.TIER_1_METHODOLOGY:
			otherMethodologyValidation(countCheckedPeriod, selectedForecastTabFrequency, indexStartPeriod,
					indexBaselinePeriod, componentId);
			break;
		case GtnFrameworkForecastConstantCommon.TIER_2_METHODOLOGY:
			otherMethodologyValidation(countCheckedPeriod, selectedForecastTabFrequency, indexStartPeriod,
					indexBaselinePeriod, componentId);
			break;

		default:
			otherMethodologyValidation(countCheckedPeriod, selectedForecastTabFrequency, indexStartPeriod,
					indexBaselinePeriod, componentId);
		}

	}

	private void countCheckedPeriod(String componentId, int countCheckedPeriod) throws GtnFrameworkGeneralException {
		if (countCheckedPeriod == 0) {
			callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
					GtnFrameworkForecastAlertMsgConstants.OVERRIDE_CALCULATION_MSG, componentId);
		}
	}

	private String[] getForecastBaselinePeriod(String selectedForecastTabFrequency, List<String> checkPeriods,
			int countCheckedPeriod) {

		String[] indexBaselinePeriod = new String[2];

		if (countCheckedPeriod != 0) {
			if (GtnFrameworkForecastConstantCommon.SELECTFREQ_ANNUAL.equals(selectedForecastTabFrequency)) {
				indexBaselinePeriod[0] = checkPeriods.get(checkPeriods.size() - 1);
				return indexBaselinePeriod;
			} else {
				indexBaselinePeriod = checkPeriods.get(checkPeriods.size() - 1).split(" ");
				return indexBaselinePeriod;
			}
		}
		return indexBaselinePeriod;
	}

	private String[] getForecastTabFrequency(String selectedForecastTabFrequency, ComboBox forecastTabStartPeriod) {

		String[] indexStartPeriod = new String[2];

		if (GtnFrameworkForecastConstantCommon.SELECTFREQ_ANNUAL.equals(selectedForecastTabFrequency)) {
			indexStartPeriod[0] = forecastTabStartPeriod.getItemCaption(forecastTabStartPeriod.getValue());
			return indexStartPeriod;
		} else {
			indexStartPeriod = (forecastTabStartPeriod.getItemCaption(forecastTabStartPeriod.getValue())).split(" ");
			return indexStartPeriod;
		}
	}

	private void callFramwWorkAlertAction(String header, String msg, String componentId)
			throws GtnFrameworkGeneralException {

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
		alertActionConfig.addActionParameter(header);
		alertActionConfig.addActionParameter(msg);
		alertAction.doAction(componentId, alertActionConfig);
		throw new GtnFrameworkValidationFailedException("Validation Error :" + msg);

	}

	private void rollingAnnualTrendMethodologyValidation(int countCheckedPeriod, String selectedForecastTabFrequency,

			String[] indexStartPeriod, String[] indexBaselinePeriod, List<String> checkPeriods, String componentId)
			throws GtnFrameworkGeneralException {

		int countForRollingMethodolody = 0;
		int totalCountForRollingMethodolody = 0;
		boolean validateFlag = true;
		String year;

		if (GtnFrameworkForecastConstantCommon.SELECTFREQ_ANNUAL.equals(selectedForecastTabFrequency)) {
			year = checkPeriods.get(0);
		} else {
			year = checkPeriods.get(0).split(" ")[1];
		}

		for (String period : checkPeriods) {
			if (period.contains(year)) {
				countForRollingMethodolody++;
			}
			totalCountForRollingMethodolody++;
		}

		validateFlag = getMessageAlertForRoolingMethodology(selectedForecastTabFrequency, countForRollingMethodolody,
				totalCountForRollingMethodolody, countCheckedPeriod, componentId);

		frequencyValidation(selectedForecastTabFrequency, indexStartPeriod, indexBaselinePeriod, validateFlag,
				componentId);

	}

	private boolean getMessageAlertForRoolingMethodology(String selectedForecastTabFrequency,
			int countForRollingMethodolody, int totalCountForRollingMethodolody, int countCheckedPeriod,
			String componentId) throws GtnFrameworkGeneralException {
		boolean validateFlag = true;
		switch (selectedForecastTabFrequency) {
		case GtnFrameworkForecastConstantCommon.SELECTFREQ_ANNUAL:
			if (countCheckedPeriod != 1 || totalCountForRollingMethodolody > 1) {
				validateFlag = false;
				callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
						GtnFrameworkForecastAlertMsgConstants.ROLLING_ANNUAL_TREND_MSG, componentId);

			}

			break;

		case GtnFrameworkForecastConstantCommon.SELECTFREQ_MONTH:
			if (countForRollingMethodolody != 12 || totalCountForRollingMethodolody > 12) {
				validateFlag = false;
				callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
						GtnFrameworkForecastAlertMsgConstants.ROLLING_ANNUAL_TREND_MSG, componentId);
			}

			break;

		case GtnFrameworkForecastConstantCommon.SELECTFREQ_SEMI:
			if (countForRollingMethodolody != 2 || totalCountForRollingMethodolody > 2) {
				validateFlag = false;
				callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
						GtnFrameworkForecastAlertMsgConstants.ROLLING_ANNUAL_TREND_MSG, componentId);
			}

			break;
		default:
			if (countForRollingMethodolody != 4 || totalCountForRollingMethodolody > 4) {
				validateFlag = false;
				callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
						GtnFrameworkForecastAlertMsgConstants.ROLLING_ANNUAL_TREND_MSG, componentId);
			}

			break;
		}
		return validateFlag;
	}

	private void averageMethodologyValidation(int countCheckedPeriod, String selectedForecastTabFrequency,

			String[] indexStartPeriod, String[] indexBaselinePeriod, String componentId)
			throws GtnFrameworkGeneralException {

		boolean validateFlag = false;

		if (countCheckedPeriod < 2 && countCheckedPeriod != 0) {
			callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.NO_PERIOD_SELECTED_MSG_HEADER,
					GtnFrameworkForecastAlertMsgConstants.NO_ATLEAST_2_PERIOD_SELECTED_MSG, componentId);
		}

		if (countCheckedPeriod >= 2) {
			validateFlag = true;
			frequencyValidation(selectedForecastTabFrequency, indexStartPeriod, indexBaselinePeriod, validateFlag,
					componentId);
		}

	}

	private void otherMethodologyValidation(int countCheckedPeriod, String selectedForecastTabFrequency,

			String[] indexStartPeriod, String[] indexBaselinePeriod, String componentId)
			throws GtnFrameworkGeneralException {

		boolean validateFlag = false;

		if (countCheckedPeriod < 1) {
			callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
					GtnFrameworkForecastAlertMsgConstants.NO_ATLEAST_1_PERIOD_SELECTED_MSG, componentId);
		}

		if (countCheckedPeriod >= 1) {
			validateFlag = true;
			frequencyValidation(selectedForecastTabFrequency, indexStartPeriod, indexBaselinePeriod, validateFlag,
					componentId);
		}

	}

	private void singleMethodologyValidation(int countCheckedPeriod, String selectedForecastTabFrequency,

			String[] indexStartPeriod, String[] indexBaselinePeriod, String componentId)
			throws GtnFrameworkGeneralException {

		boolean validateFlag = false;

		if (countCheckedPeriod > 1) {
			callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
					GtnFrameworkForecastAlertMsgConstants.NO_1_PERIOD_SELECTED_MSG, componentId);
		}

		if (countCheckedPeriod == 1) {
			validateFlag = true;
			frequencyValidation(selectedForecastTabFrequency, indexStartPeriod, indexBaselinePeriod, validateFlag,
					componentId);
		}

	}

	private void frequencyValidation(String selectedForecastTabFrequency, String[] indexStartPeriod,
			String[] indexBaselinePeriod, boolean validateFlag, String componentId)
			throws GtnFrameworkGeneralException {

		if (validateFlag) {

			if ((GtnFrameworkForecastConstantCommon.SELECTFREQ_QUAD.equals(selectedForecastTabFrequency))
					|| (GtnFrameworkForecastConstantCommon.SELECTFREQ_SEMI.equals(selectedForecastTabFrequency))) {
				frequencyQSValidation(indexStartPeriod, indexBaselinePeriod, componentId);
			}

			if (GtnFrameworkForecastConstantCommon.SELECTFREQ_ANNUAL.equals(selectedForecastTabFrequency)) {
				frequencyAnnualValidation(indexStartPeriod, indexBaselinePeriod, componentId);
			}

			if (GtnFrameworkForecastConstantCommon.SELECTFREQ_MONTH.equals(selectedForecastTabFrequency)) {
				frequencyMonthlyValidation(indexStartPeriod, indexBaselinePeriod, componentId);
			}
		}

	}

	private void frequencyQSValidation(String[] indexStartPeriod, String[] indexBaselinePeriod, String componentId)
			throws GtnFrameworkGeneralException {

		getBaselineAlert(indexStartPeriod, indexBaselinePeriod, componentId);

		if (!indexStartPeriod[0].equals(indexBaselinePeriod[0])) {

			if (Integer.parseInt(indexStartPeriod[1]) == Integer.parseInt(indexBaselinePeriod[1])) {

				int a = Integer.parseInt(indexStartPeriod[0].substring(1));
				int b = Integer.parseInt(indexBaselinePeriod[0].substring(1));

				if (a < b) {

					callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
							GtnFrameworkForecastAlertMsgConstants.BASELINE_PERIODS_AFTER_MSG, componentId);
				}
			}

			if (Integer.parseInt(indexStartPeriod[1]) != Integer.parseInt(indexBaselinePeriod[1])
					&& Integer.parseInt(indexStartPeriod[1]) < Integer.parseInt(indexBaselinePeriod[1])) {

				int a = Integer.parseInt(indexStartPeriod[0].substring(1));
				int b = Integer.parseInt(indexBaselinePeriod[0].substring(1));

				if (a < b) {
					callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
							GtnFrameworkForecastAlertMsgConstants.BASELINE_PERIODS_AFTER_MSG, componentId);
				}
			}
		}
	}

	private void getBaselineAlert(String[] indexStartPeriod, String[] indexBaselinePeriod, String componentId)
			throws GtnFrameworkGeneralException {
		if (indexStartPeriod[0].equals(indexBaselinePeriod[0])) {

			if (Integer.parseInt(indexStartPeriod[1]) == Integer.parseInt(indexBaselinePeriod[1])) {
				callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
						GtnFrameworkForecastAlertMsgConstants.BASELINE_PERIODS_WITHIN_MSG, componentId);

			}

			if (Integer.parseInt(indexStartPeriod[1]) < Integer.parseInt(indexBaselinePeriod[1])) {
				callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
						GtnFrameworkForecastAlertMsgConstants.BASELINE_PERIODS_AFTER_MSG, componentId);

			}
		}

	}

	private void frequencyAnnualValidation(String[] indexStartPeriod, String[] indexBaselinePeriod, String componentId)
			throws GtnFrameworkGeneralException {

		int a = Integer.parseInt(indexStartPeriod[0]);
		int b = Integer.parseInt(indexBaselinePeriod[0]);

		if (a == b) {
			callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
					GtnFrameworkForecastAlertMsgConstants.BASELINE_PERIODS_WITHIN_MSG, componentId);
		}

		if (a < b) {
			callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
					GtnFrameworkForecastAlertMsgConstants.BASELINE_PERIODS_AFTER_MSG, componentId);
		}
	}

	private void frequencyMonthlyValidation(String[] indexStartPeriod, String[] indexBaselinePeriod, String componentId)
			throws GtnFrameworkGeneralException {

		getBaselineAlert(indexStartPeriod, indexBaselinePeriod, componentId);

		if (!indexStartPeriod[0].equals(indexBaselinePeriod[0])) {

			if (Integer.parseInt(indexStartPeriod[1]) == Integer.parseInt(indexBaselinePeriod[1])) {

				int a = getMonth(indexStartPeriod[0]);
				int b = getMonth(indexBaselinePeriod[0]);

				if (a < b) {
					callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
							GtnFrameworkForecastAlertMsgConstants.BASELINE_PERIODS_AFTER_MSG, componentId);
				}
			}

			if (Integer.parseInt(indexStartPeriod[1]) != Integer.parseInt(indexBaselinePeriod[1])
					&& Integer.parseInt(indexStartPeriod[1]) < Integer.parseInt(indexBaselinePeriod[1])) {

				int a = getMonth(indexStartPeriod[0]);
				int b = getMonth(indexBaselinePeriod[0]);

				if (a < b) {
					callFramwWorkAlertAction(GtnFrameworkForecastAlertMsgConstants.ERROR_MSG_HEADER,
							GtnFrameworkForecastAlertMsgConstants.BASELINE_PERIODS_AFTER_MSG, componentId);
				}
			}

		}
	}

	private int getMonth(String monthString) {

		int month = 0;

		switch (monthString) {
		case "Jan":
			month = 1;
			break;
		case "Feb":
			month = 2;
			break;
		case "Mar":
			month = 3;
			break;
		case "Apr":
			month = 4;
			break;
		case "May":
			month = 5;
			break;
		case "Jun":
			month = 6;
			break;
		case "Jul":
			month = 7;
			break;
		case "Aug":
			month = 8;
			break;
		case "Sep":
			month = 9;
			break;
		case "Oct":
			month = 10;
			break;
		case "Nov":
			month = 11;
			break;
		case "Dec":
			month = 12;
			break;
		default:
			month = 0;
		}
		return month;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
