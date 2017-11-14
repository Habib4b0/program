package com.stpl.gtn.gtn2o.ui.module.commercial.action;

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
import java.util.Date;
import java.util.List;

public class GtnFrameworkConfigureRightTableHeaderForPTTCompoAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	private final GtnWSLogger gtnWsLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkConfigureRightTableHeaderForPTTCompoAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnWsLogger.info(" inside GtnFrameworkConfigureRightTableHeaderForPTTCompoAction");

		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();

		GtnForecastBean gtnForecastBean = setData();
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsForecastRequest gtnWsForecastRequest = new GtnWsForecastRequest();
		gtnWsForecastRequest.setGtnForecastBean(gtnForecastBean);
		gtnUIFrameworkWebserviceRequest.setGtnWsForecastRequest(gtnWsForecastRequest);

		GtnUIFrameworkComponentData resultTableComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParameterList.get(0).toString(), componentId);
		resultTableComponentData.setCustomPagedTreeTableRequest(gtnUIFrameworkWebserviceRequest);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkConfigureRightTableHeaderForPTTCompoAction();
	}

	@SuppressWarnings("deprecation")
	private GtnForecastBean setData() {

		GtnForecastBean gtnForecastBean = new GtnForecastBean();

		gtnForecastBean.setHistoryStartYear(2014);
		gtnForecastBean.setHistoryStartMonth(0);
		gtnForecastBean.setHistoryEndYear(2017);
		gtnForecastBean.setHistoryEndMonth(0);
		gtnForecastBean.setProjectionStartDate(new Date(2014 - 1900, 0, 1));
		gtnForecastBean.setProjectionEndDate(new Date(2018 - 1900, 11, 1));

		gtnForecastBean.setProjectionStartYear(2014);
		gtnForecastBean.setProjectionStartMonth(0);
		gtnForecastBean.setProjectionEndYear(2018);
		gtnForecastBean.setProjectionEndMonth(11);
		gtnForecastBean.setForecastStartDate(new Date(2014 - 1900, 0, 1));
		gtnForecastBean.setForecastEndDate(new Date(2018 - 1900, 11, 1));

		gtnForecastBean.setFrequency("Monthly");
		gtnForecastBean.setSelectedHistory("3");
		gtnForecastBean.setActualOrProjection("Actuals");
		gtnForecastBean.setAscending(true);

		return gtnForecastBean;

	}

}
