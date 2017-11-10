package com.stpl.gtn.gtn2o.ui.action.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastAlertMsgConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnFrameworkForecastReturnBusinessUnitValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkForecastReturnBusinessUnitValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info(" inside GtnFrameworkForecastReturnDataSelectionValidationAction");

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest validationRequest = new GtnWsSearchRequest();
		List<Object> inputList = new ArrayList<>();
		inputList.add(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId)
				.getIntegerFromField());
		inputList.add(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(1), componentId)
				.getIntegerFromField());

		validationRequest.setQueryInputList(inputList);
		request.setGtnWsSearchRequest(validationRequest);
		GtnUIFrameworkWebserviceResponse reponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_BUSINESSUNIT_VALIDATION_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		boolean activeBusinessUnit = reponse.getGtnSerachResponse().getCount() == 0;
		Object msg;
		if (activeBusinessUnit) {
			msg = GtnFrameworkForecastAlertMsgConstants.BUSINESS_UNIT_VALIDATION_MSG;

			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();

			alertActionConfig.setActionParameterList(
					Arrays.asList(GtnFrameworkForecastAlertMsgConstants.BUSINESS_UNIT_VALIDATION_MSG_HEADER, msg));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException("Validation Error :" + msg);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
