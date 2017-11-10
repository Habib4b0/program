package com.stpl.gtn.gtn2o.ui.action.pagedtreetable;

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

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkFSPagedTreeTableFillCountDataAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkFSPagedTreeTableFillCountDataAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info(" inside GtnFrameworkFSPagedTreeTableFillCountDataAction ");

		GtnForecastBean gtnForecastBean;
		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParameterList.get(6).toString(), componentId);

		gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();

		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setGtnForecastBean(gtnForecastBean);
		gtnForecastBean.setExpandCollapseLevelNo(gtnUIFrameWorkActionConfig.getActionParameter().getLevelNo());
		serviceRequest.setGtnWsForecastRequest(forecastRequest);

		GtnUIFrameworkComponentData resultTableComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParameterList.get(0).toString(), componentId);
		resultTableComponentData.setCustomPagedTreeTableRequest(serviceRequest);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
