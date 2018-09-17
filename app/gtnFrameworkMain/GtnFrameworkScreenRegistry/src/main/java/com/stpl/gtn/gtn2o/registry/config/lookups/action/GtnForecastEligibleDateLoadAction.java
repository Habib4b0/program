package com.stpl.gtn.gtn2o.registry.config.lookups.action;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

import com.stpl.gtn.gtn2o.registry.action.GtnCustomerSelectionRelationshipLoadAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnForecastEligibleDateLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnCustomerSelectionRelationshipLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionParams = gtnUIFrameWorkActionConfig.getActionParameterList();
		String fromPeriod = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParams.get(1).toString())
				.getStringCaptionFromV8ComboBox();
		LocalDate localDate = convertQuarterToDate(fromPeriod);
		logger.info("fromPeriod----->" + fromPeriod);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParams.get(2).toString()).loadV8DateValue(localDate);

	}

	private LocalDate convertQuarterToDate(String fromPeriod) {
		fromPeriod = fromPeriod.replace(" ", "");
		Pattern patternOne = Pattern.compile("\\bQ..[0-9]{4}\\b");
		LocalDate localDate = null;
		if (patternOne.matcher(fromPeriod).find()) {
			int[] arr = { 0, 1, 4, 7, 10 };
			localDate = LocalDate.parse(
					"01/" + arr[Character.getNumericValue(fromPeriod.charAt(1))] + "/" + fromPeriod.substring(3),
					DateTimeFormatter.ofPattern("dd/M/yyyy"));
		}
		return localDate;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
