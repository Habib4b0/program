package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.fieldfactory.GtnFrameworkRSValueChangeManager;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.rebateschedule.GtnWsRebateScheduleInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.rebateschedule.GtnWsRebateScheduleGeneralRequest;

public class GtnFrameworkRebateSetupCheckAllAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsRebateScheduleInfoBean rsInfoBean = new GtnWsRebateScheduleInfoBean();
		rsInfoBean.setCheckAll(true);
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psRebateSetupTabResultDataTable");
		rsInfoBean.setColumnId(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		Object value = tableBaseComponent.getTableColumnCheckboxValue(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		rsInfoBean.setValue(value);
		updateField();

		GtnFrameworkRSValueChangeManager.setValueChangeAllowed(Boolean.FALSE);
		GtnUIFrameworkPagedTableLogic logic = tableBaseComponent.getLogicFromPagedDataTable();
		logic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), Boolean.TRUE);
		GtnFrameworkRSValueChangeManager.setValueChangeAllowed(Boolean.TRUE);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void updateField() {
		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		GtnWsRebateScheduleInfoBean rsBean = new GtnWsRebateScheduleInfoBean();
		rsBean.setCheckAll(Boolean.TRUE);
		rsBean.setColumnId(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		rsBean.setValue(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psRebateSetupTabResultDataTable")
				.getTableColumnCheckboxValue(GtnFrameworkCommonConstants.CHECK_RECORD_ID));

		GtnWsRebateScheduleGeneralRequest gtnWsRSUpdateRequest = new GtnWsRebateScheduleGeneralRequest();
		gtnWsRSUpdateRequest.setRebateScheduleInfoBean(rsBean);
		updateRequest.setGtnWsRebateScheduleGeneralRequest(gtnWsRSUpdateRequest);
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.RS_SERVICE + "/" + GtnWsCDRContants.GTN_WS_RS_CHECK_ALL_SERVICE, updateRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

}
