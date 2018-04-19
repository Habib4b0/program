package com.stpl.gtn.gtn2o.ui.action.pagedtreetable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class GtnFrameworkFSPagedTreeTableGetBulkDataAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkFSPagedTreeTableGetBulkDataAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info(" inside GtnFrameworkFSPagedTreeTableGetBulkDataAction ");

		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnForecastBean gtnForecastBean;
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParameterList.get(6).toString(), componentId);

		gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();

		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		List<String> hierarchyList = new ArrayList<>(
				gtnUIFrameWorkActionConfig.getActionParameter().getLoadBulkMap().keySet());

		gtnForecastBean.setHierarchyList(hierarchyList);
		gtnForecastBean.setRecordheader(gtnUIFrameWorkActionConfig.getActionParameter().getRecordHeader());

		boolean levelFilter = (boolean) gtnUIFrameWorkActionConfig.getActionParameter().getCurrentValue();

		forecastRequest.setGtnForecastBean(gtnForecastBean);

		if (levelFilter) {
			Collections.sort(hierarchyList, (Comparator<String>) getLevelComparator());
			gtnForecastBean.setStart(Integer.parseInt(hierarchyList.get(0).split("\\.")[0]) - 1);
			gtnForecastBean.setOffset(Integer.parseInt(hierarchyList.get(hierarchyList.size() - 1).split("\\.")[0]));

		}
		serviceRequest.setGtnWsForecastRequest(forecastRequest);

		GtnUIFrameworkComponentData resultTableComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParameterList.get(0).toString(), componentId);
		resultTableComponentData.setCustomPagedTreeTableRequest(serviceRequest);

	}

	private Comparator<String> getLevelComparator() {
		return new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int firstPosition = (Integer.parseInt(o1.split("\\.")[0]));
				int secondPosition = (Integer.parseInt(o2.split("\\.")[0]));
				return Integer.compare(firstPosition, secondPosition);
			}
		};
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
