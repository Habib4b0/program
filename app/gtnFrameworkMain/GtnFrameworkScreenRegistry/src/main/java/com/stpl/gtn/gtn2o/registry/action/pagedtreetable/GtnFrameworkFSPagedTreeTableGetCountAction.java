package com.stpl.gtn.gtn2o.registry.action.pagedtreetable;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkFSPagedTreeTableGetCountAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkFSPagedTreeTableGetCountAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info(" inside GtnFrameworkFSPagedTreeTableGetCountAction ");
		GtnForecastBean gtnForecastBean;
		boolean levelFilter = (boolean) gtnUIFrameWorkActionConfig.getActionParameter().getCurrentValue();
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();

		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParameterList.get(6).toString(), componentId);

		gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();

		// Servicerequest
		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();

		requestConfig(gtnForecastBean, gtnUIFrameWorkActionConfig.getActionParameter().getLastParent());

		forecastRequest.setGtnForecastBean(gtnForecastBean);

		serviceRequest.setGtnWsForecastRequest(forecastRequest);

		// set bean
		gtnForecastBean.setFrequency(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(1).toString(), componentId).getCaptionFromComboBox());
		gtnForecastBean.setSelectedHistory(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(3).toString(), componentId).getStringFromField());
		gtnForecastBean.setActualOrProjection(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(2).toString(), componentId).getStringFromField());
		gtnForecastBean.setAscending(actionParameterList.get(7).equals(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(4).toString(), componentId).getStringFromField()));
		if (levelFilter) {
			gtnForecastBean.setLevelFilter(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParameterList.get(5).toString(), componentId).getIntegerFromField());
		} else {
			gtnForecastBean.setLevelFilter(0);
		}

		GtnUIFrameworkComponentData resultTableComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParameterList.get(0).toString(), componentId);
		resultTableComponentData.setCustomPagedTreeTableRequest(serviceRequest);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;

	}

	private void requestConfig(final GtnForecastBean gtnForecastBean, final Object parentId) {
		if (parentId instanceof GtnWsRecordBean) {
			GtnWsRecordBean parentDto = (GtnWsRecordBean) parentId;
			List<Object> parentDetails = parentDto.getAdditionalProperties();
			gtnForecastBean.setHierarchyNo(String.valueOf(parentDetails.get(0)));
			gtnForecastBean.setLevelNo(Integer.valueOf(String.valueOf(parentDetails.get(1))));
		} else {
			gtnForecastBean.setHierarchyNo("");
			gtnForecastBean.setLevelNo(1);
		}
	}

}
