package com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsCheckAllUpdateBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsCheckAllUpdateRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;

public class GtnFrameworkPricingTabFieldFactoryAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		if (componentId.contains("priceType") && GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
				.getCaptionFromComboBox().equals("Contract Price")) {
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(componentId.replace("priceType", GtnFrameworkCommonConstants.PS_PRICE))
					.setComponentEnable(true);
		}

		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinFieldFactoryComponentData(componentId);
		GtnUIFrameworkActionParameter actionParam = componentData.getActionParameter();
		if (GtnFrameworkPriceTabValueChangeManager.isValueChangeAllowed()
				&& actionParam.getCurrentValue() != actionParam.getOldValue()) {
			updateField(actionParam.getPropertyId(), actionParam.getCurrentValue(), Boolean.FALSE,
					actionParam.getItemId().getPropertyValue("systemId").toString());
		}
		if (GtnFrameworkCommonConstants.CHECK_RECORD_ID.equals(actionParam.getPropertyId())) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParam.getTableComponentId())
					.getLogicFromPagedDataTable().handleCheckBoxOnItem(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
							Boolean.parseBoolean(String.valueOf(actionParam.getCurrentValue())));
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void updateField(String column, Object value, Boolean checkAll, String systemId) {
		Object tempValue = value;
		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsCheckAllUpdateBean psUpdateBean = new GtnWsCheckAllUpdateBean();

		psUpdateBean.setPropertyId(column);
		if (tempValue instanceof Boolean) {
			tempValue = (Boolean) tempValue ? 1 : 0;
		}
		psUpdateBean.setValue(tempValue);
		psUpdateBean.setMasterSid(Integer.valueOf(systemId));
		psUpdateBean.setCheckAll(checkAll);

		GtnWsCheckAllUpdateRequest gtnWsPSUpdateRequest = new GtnWsCheckAllUpdateRequest();
		gtnWsPSUpdateRequest.setUpdateBean(psUpdateBean);
		updateRequest.setGtnWsCheckAllUpdateRequest(gtnWsPSUpdateRequest);
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_PRICETAB_UPDATE_SERVICE, updateRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

}
