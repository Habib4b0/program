package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.config.GtnUIFrameworkWebServiceReportRequestBuilder;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewDataBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportEndPointUrlConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

public class GtnFrameworkCustomViewDeleteAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		boolean isEdit = (boolean) GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportCustomViewLookupcustomViewSave")
				.getComponentData().getCustomData();
		if (isEdit) {

			String textField = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
			String customViewName = (String) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(textField, componentId)
					.getFieldValue();
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebServiceReportRequestBuilder()
					.withCustomViewBean().build();
			GtnWsReportCustomViewDataBean customViewDataBean = new GtnWsReportCustomViewDataBean();
			request.getGtnWsReportRequest().getReportBean().getCustomViewBean()
					.setCustomViewDataBean(customViewDataBean);
			request.getGtnWsReportRequest().getReportBean().getCustomViewBean().getCustomViewDataBean()
					.setCustomViewName(customViewName);

			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsReportEndPointUrlConstants.DELETE_CUSTOM_VIEW,
					GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getParentViewId();
			String id = sourceComponentId + "_" + "reportingDashboardTab_displaySelectionTabCustomView";
			GtnUIFrameworkBaseComponent baseComboBoxComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(id);
			GtnUIFrameworkComponentData componentData = baseComboBoxComponent.getComponentData();
			componentData.getCurrentGtnComponent().resetToDefault(id, componentData.getCurrentComponentConfig());
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
