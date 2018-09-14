package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Grid;

public class GtnReportReportProfileDeleteAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnReportReportProfileDeleteAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("configure Params");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();

		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromView(actionParamList.get(1).toString(), componentId).getComponent();
		GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
		PagedGrid pagedGridInReportProfile = gridComponent.getPagedGrid();
		Grid<GtnWsRecordBean> grid = pagedGridInReportProfile.getGrid();
		Set<GtnWsRecordBean> recordBean = grid.getSelectedItems();
		List<GtnWsRecordBean> recordBeanList = new ArrayList<>(recordBean);
		GtnWsRecordBean reportWsbean = recordBeanList.get(0);
		Integer viewId = (Integer) reportWsbean.getPropertyValueByIndex(4);
		GtnWsReportDataSelectionBean dSBean = new GtnWsReportDataSelectionBean();
		dSBean.setViewId(viewId);
		GtnWsReportRequest gtnWsReportRequest = new GtnWsReportRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsReportRequest.setDataSelectionBean(dSBean);
		gtnWsGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		request.setGtnWsReportRequest(gtnWsReportRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_REPORT_SERVICE + GtnWsReportConstants.GTN_REPORRT_DELETEVIEW_SERVICE, "report",
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (response.getGtnWsGeneralResponse().isSucess()) {
			pagedGridInReportProfile.removeItem(reportWsbean);
		} else {
			GtnUIFrameWorkActionConfig reportProfileDeleteAlertAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.INFO_ACTION);
			reportProfileDeleteAlertAction.addActionParameter("Cannot Delete ");
			reportProfileDeleteAlertAction.addActionParameter("Only the creator of a view can delete it. ");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, reportProfileDeleteAlertAction);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
