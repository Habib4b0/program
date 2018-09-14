package com.stpl.gtn.gtn2o.ui.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;

public class GtnFrameworkReportCustomViewConfirmDeleteAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkReportCustomViewConfirmDeleteAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// do nothing
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest reportCvRequest = new GtnWsCustomViewRequest();
		reportCvRequest
				.setCvSysId(Integer.parseInt(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString()));
		request.setGtnWsCustomViewRequest(reportCvRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE + "/deleteCustomViewReport", request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsCustomViewResponse cvResponse = response.getGtnWsCustomViewResponse();

		if (cvResponse.isSuccess()) {
			GtnUIFrameWorkActionConfig notification = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			notification.addActionParameter(gtnUIFrameWorkActionConfig.getActionParameterList().get(4).toString()
					+ " has been successfully Deleted");
			notification.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notification);
			String id = gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString();
			boolean customViewFromParent = Boolean
					.parseBoolean(gtnUIFrameWorkActionConfig.getActionParameterList().get(3).toString());
			GtnUIFrameworkBaseComponent customView = customViewFromParent
					? GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(id, componentId)
					: GtnUIFrameworkGlobalUI.getVaadinBaseComponent(id);
			reloadAndSetvalue(id, componentId, customView);
		}

	}

	private void reloadAndSetvalue(String id, String componentId, GtnUIFrameworkBaseComponent customView) {
		try {
			new GtnUIFrameworkComboBoxComponent().reloadComponentFromParent(id, componentId, Arrays.asList(""));
			customView.loadV8ComboBoxComponentValue(String.valueOf(0));
		} catch (GtnFrameworkValidationFailedException e) {
			logger.error(e.getErrorMessage(), e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
