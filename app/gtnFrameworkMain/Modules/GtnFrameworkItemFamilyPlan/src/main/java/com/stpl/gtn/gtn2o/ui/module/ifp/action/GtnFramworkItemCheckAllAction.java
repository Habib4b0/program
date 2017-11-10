package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import java.io.Serializable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;

public class GtnFramworkItemCheckAllAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnIFamilyPlanCommonUpdateBean ifpPopulate = new GtnIFamilyPlanCommonUpdateBean();
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI	.getVaadinBaseComponent("ifpItemsTabResultDataTable");
		Object value = tableBaseComponent
				.getTableColumnCheckboxValue(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		ifpPopulate.setCheckAll(true);
		ifpPopulate.setColumnName(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		ifpPopulate.setValue((Serializable) value);
		updateField(ifpPopulate);
		for (GtnWsRecordBean record : tableBaseComponent.getItemsFromDataTable()) {
			tableBaseComponent.setTableColumnValue(record, GtnFrameworkCommonConstants.CHECK_RECORD_ID, value);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void updateField(GtnIFamilyPlanCommonUpdateBean ifpUpdateBean) {
		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		GtnWsIfpRequest gtnWsPSUpdateRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean ifpBean = new GtnIFamilyPlanBean();
		ifpBean.setUpdateBean(ifpUpdateBean);
		gtnWsPSUpdateRequest.setGtnIFamilyPlan(ifpBean);
		updateRequest.setGtnWsIfpRequest(gtnWsPSUpdateRequest);
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE + "/"
						+ GtnWsIFamilyPlanContants.GTN_WS_IFP_CHECK_ALL_SERVICE,
				updateRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}
}
