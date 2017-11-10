package com.stpl.gtn.gtn2o.ui.framework.action.validation;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public class GtnFrameworkDateCompareValidationAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		if (gtnUIFrameWorkActionConfig != null && gtnUIFrameWorkActionConfig.getActionParameterList() != null
				&& gtnUIFrameWorkActionConfig.getActionParameterList().size() >= 4) {
			String targetComponentId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(0);
			if (!checkNullDate(componentId, targetComponentId)) {
				int comparedOutput = compareDate(componentId, targetComponentId);
				int expectedCompareValue = (int) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
				String errorPattern = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);
				String datePatternForError = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(3);
				String errorMessage = generateErrorMessage(errorPattern, datePatternForError, targetComponentId);
				isThatExpected(comparedOutput, expectedCompareValue, errorMessage);
			}
		}
	}

	private boolean checkNullDate(String componentId, String targetComponentId)
			throws GtnFrameworkValidationFailedException {
		Date sourceDate = (Date) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, componentId)
				.getValueFromComponent();
		Date targetDate = (Date) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(targetComponentId)
				.getValueFromComponent();
		return sourceDate == null || targetDate == null;
	}

	private String generateErrorMessage(String errorPattern, String datePatternForError, String targetComponentId)
			throws GtnFrameworkValidationFailedException {
		SimpleDateFormat dt1 = new SimpleDateFormat(datePatternForError);
		Date sourceDate = (Date) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(targetComponentId)
				.getValueFromComponent();
		return errorPattern.replace("?", dt1.format(sourceDate));
	}

	private void isThatExpected(int comparedOutput, int expectedCompareValue, String errorMessage)
			throws GtnFrameworkGeneralException {
		if (comparedOutput == expectedCompareValue) {
			throw new GtnFrameworkGeneralException(errorMessage);
		}

	}

	private int compareDate(String sourceDateComponent, String targetDateComponent)
			throws GtnFrameworkGeneralException {
		Date sourceDate = (Date) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(sourceDateComponent, sourceDateComponent)
				.getValueFromComponent();
		Date targetDate = (Date) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(targetDateComponent)
				.getValueFromComponent();
		return sourceDate.compareTo(targetDate);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
