package com.stpl.gtn.gtn2o.ui.module.forecasting.action.pagedtreetable;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;

public class GtnFrameworkFSPTTCompRightHeaderFormHeaderAndConfigAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnWSLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkFSPTTCompRightHeaderFormHeaderAndConfigAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnWSLogger.info(" inside GtnFrameworkFSPTTCompRightHeaderFormHeaderAndConfigAction");

		GtnForecastBean gtnForecastBean;
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();

		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsForecastRequest requestForRightHeader = new GtnWsForecastRequest();

		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParameterList.get(6).toString(), componentId);

		gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();

		gtnForecastBean.setFrequency(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(1).toString(), componentId).getCaptionFromComboBox());
		gtnForecastBean.setActualOrProjection(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(2).toString(), componentId).getStringFromField());

		gtnForecastBean.setSelectedHistory(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(3).toString(), componentId).getStringFromField());
		gtnForecastBean.setAscending(actionParameterList.get(7).equals(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(4).toString(), componentId).getStringFromField()));

		requestForRightHeader.setGtnForecastBean(gtnForecastBean);

		gtnUIFrameworkWebserviceRequest.setGtnWsForecastRequest(requestForRightHeader);

		GtnUIFrameworkComponentData resultTableComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParameterList.get(0).toString(), componentId);
		resultTableComponentData.setCustomPagedTreeTableRequest(gtnUIFrameworkWebserviceRequest);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
