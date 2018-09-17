package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.config.GtnUIFrameworkWebServiceReportRequestBuilder;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsCustomTreeData;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewDataBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportEndPointUrlConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkCustomTreeConfirmedSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {
	private static final GtnWSLogger GTNLOGGER = GtnWSLogger
			.getGTNLogger(GtnFrameworkCustomTreeConfirmedSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebServiceReportRequestBuilder()
				.withCustomViewBean().build();
		GtnWsReportCustomViewDataBean customViewDataBean = new GtnWsReportCustomViewDataBean();
		request.getGtnWsReportRequest().getReportBean().getCustomViewBean().setCustomViewDataBean(customViewDataBean);
		request.getGtnWsReportRequest().getReportBean().getCustomViewBean().getCustomViewDataBean()
				.setCustomViewName((String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1));
		request.getGtnWsReportRequest().getReportBean().getCustomViewBean().getCustomViewDataBean()
				.setCustomTreeData((GtnWsCustomTreeData) gtnUIFrameWorkActionConfig.getActionParameterList().get(2));

		boolean isEdit = (boolean) GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportCustomViewLookupcustomViewSave")
				.getComponentData().getCustomData();

		request.getGtnWsReportRequest().getReportBean().getCustomViewBean().setEdit(isEdit);

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportEndPointUrlConstants.SAVE_CUSTOM_TREE, GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GTNLOGGER.info("Save completed -" + (response != null));

		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		actionConfig.addActionParameter(GtnFrameworkUIBuildCustomTreeAction.class.getName());
		actionConfig.addActionParameter((String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportCustomViewLookupcustomViewSave").getComponentData()
				.setCustomData(Boolean.TRUE);

		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getParentViewId();
		String id = sourceComponentId + "_" + "reportingDashboardTab_displaySelectionTabCustomView";
		GtnUIFrameworkBaseComponent baseComboBoxComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(id);
		GtnUIFrameworkComponentData componentData = baseComboBoxComponent.getComponentData();
		componentData.getCurrentGtnComponent().resetToDefault(id, componentData.getCurrentComponentConfig());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
