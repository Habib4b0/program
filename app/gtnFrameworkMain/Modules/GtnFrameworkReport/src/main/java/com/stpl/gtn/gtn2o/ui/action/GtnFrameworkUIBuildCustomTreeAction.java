package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.config.GtnUIFrameworkWebServiceReportRequestBuilder;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkUIBuildCustomTreeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String viewName = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getParentViewId();
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
		GtnWsReportDashboardBean dashBoardBean = new GtnWsReportDashboardBean();
		dashBoardBean.setCustomViewName(viewName);
		dashBoardBean.setSessionId(dataSelectionBean.getUniqueId());
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebServiceReportRequestBuilder().build();
		request.getGtnWsReportRequest().setGtnWsReportDashboardBean(dashBoardBean);

		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(
				"reportDashboard" + GtnFrameworkCommonConstants.RESULT_TABLE, sourceComponentId);
		if (componentData != null) {
			// QueryBean
			// querybean=GtnUIFrameworkGlobalUI.setQueryBean(componentData.getTableConfig());
			// componentData.getPagedGrid().getTableConfig().setQueryBean(querybean);

			PagedTreeGrid grid = (PagedTreeGrid) componentData.getCustomData();
			grid.getTableConfig().setGtnWsReportDashboardBean(dashBoardBean);
		}
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_REPORT_SERVICE + GtnWsReportConstants.GTN_REPORT_BUILD_CUSTOM_TREE,
				GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}
}
