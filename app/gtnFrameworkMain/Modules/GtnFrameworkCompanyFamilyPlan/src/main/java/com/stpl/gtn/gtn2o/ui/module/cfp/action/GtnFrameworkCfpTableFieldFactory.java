package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlan;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cfprequest.GtnWsCfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkCfpTableFieldFactory
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinFieldFactoryComponentData(componentId);
		GtnUIFrameworkActionParameter actionParam = componentData.getActionParameter();
                String checkRecord="checkRecordId".equals(actionParam.getPropertyId()) ? Boolean.class.getName()
												: Integer.class.getName();
                String cfpDateField=GtnFrameworkCfpStringContants.CFP_DATEFIELD_PROPERTIES_LIST
								.contains(actionParam.getPropertyId()) ? Date.class.getName():checkRecord;
		String dataType = GtnFrameworkCfpStringContants.CFP_TEXTFIELD_PROPERTIES_LIST
				.contains(actionParam.getPropertyId())
						? String.class.getName(): cfpDateField;
		if (GtnFrameworkCfpValueChangeManager.isValueChangeAllowed()) {

			updateField(actionParam.getPropertyId(), actionParam.getCurrentValue(), dataType,
					actionParam.getItemId().getProperties().get(17).toString());
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

	public int updateField(String column, Object value, String type, String imtdCfpDetailsSid) {
		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnCFamilyPlan fp = new GtnCFamilyPlan();
		GtnCFamilyPlanCommonUpdateBean bean = new GtnCFamilyPlanCommonUpdateBean();
		fp.setUpdateBean(bean);
		bean.setColumnName(column);
		bean.setValue(value);
		bean.setClassType(type);
		bean.setImtdCfpDetailsSid(imtdCfpDetailsSid);
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
		generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsCfpRequest cfpRequest = new GtnWsCfpRequest();
		cfpRequest.setGtnCFamilyPlan(fp);
		updateRequest.setGtnWsCfpRequest(cfpRequest);
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		GtnUIFrameworkWebserviceResponse updateResponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
						+ GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANIES_TAB_COLUMN_UPDATE_FIELD_SERVICE,
				updateRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (updateResponse.getEditRecord() != null) {
			Object obj = updateResponse.getEditRecord().get("count");
			if (obj != null) {
				return (int) obj;
			}
		}
		return 0;
	}
}
