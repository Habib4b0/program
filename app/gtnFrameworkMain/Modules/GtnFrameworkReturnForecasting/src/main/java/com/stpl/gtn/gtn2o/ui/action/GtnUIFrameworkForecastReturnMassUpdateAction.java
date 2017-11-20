/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;

/**
 *
 * @author Harlin.Mani
 */
public class GtnUIFrameworkForecastReturnMassUpdateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkForecastReturnMassUpdateAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {

			gtnLogger.info("inside GtnUIFrameworkForecastReturnMassUpdateAction");

			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
			List<Object> list = gtnUIFrameWorkActionConfig.getActionParameterList();

			GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(String.valueOf(list.get(1)), componentId);
			GtnForecastBean gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();

			GtnWsForecastRequest requestForCalulate = new GtnWsForecastRequest();

			gtnForecastBean.setForecastMethodology(getMehodology(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(list.get(3)), componentId).getCaptionFromComboBox()));
			gtnForecastBean.setManualEntryValue(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(list.get(4)), componentId).getStringFromField());

			gtnForecastBean.setCalculationStartPeriod(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(list.get(5)), componentId).getCaptionFromComboBox());
			gtnForecastBean.setCalculationEndPeriod(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(list.get(6)), componentId).getCaptionFromComboBox());
			gtnForecastBean.setFrequency(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(list.get(7)), componentId).getCaptionFromComboBox());
			gtnForecastBean.setMassUpdateLevelNo(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(list.get(8)), componentId).getIntegerFromField());

			requestForCalulate.setGtnForecastBean(gtnForecastBean);
			gtnUIFrameworkWebserviceRequest.setGtnWsForecastRequest(requestForCalulate);
			GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();

			client.callGtnWebServiceUrl(
					GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_CALCULATE_SERVICE,
					GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, gtnUIFrameworkWebserviceRequest,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage());
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private String getMehodology(String filed) {
		return "Mass Update-" + filed;
	}
}
