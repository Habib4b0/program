package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanValidationBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkIfpTableFieldFactory
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
		String typeString = GtnFrameworkIfpStringContants.CHECK_RECORD_ID.equals(actionParam.getPropertyId())
				? Boolean.class.getName() 
				: Integer.class.getName();
		String dataType = GtnFrameworkIfpStringContants.getDateFieldPropertiesList()
				.contains(actionParam.getPropertyId()) ? Date.class.getName() : typeString;
		if (GtnFrameworkIfpValueChangeManager.isValueChangeAllowed()) {

			updateField(actionParam.getPropertyId(), actionParam.getCurrentValue(), dataType,
					actionParam.getItemId().getProperties().get(17).toString());
		}
		if (GtnFrameworkIfpStringContants.CHECK_RECORD_ID.equals(actionParam.getPropertyId())) {
			checkBoxValueChangeLogic();
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public int updateField(String column, Object value, String type, String imtdCfpDetailsSid) {
		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();

		GtnIFamilyPlanBean fp = new GtnIFamilyPlanBean();
		GtnIFamilyPlanCommonUpdateBean bean = new GtnIFamilyPlanCommonUpdateBean();
		fp.setUpdateBean(bean);
		bean.setColumnName(column);
		bean.setValue(value);
		bean.setClassType(type); 
		bean.setImtdIfpDetailsSid(imtdCfpDetailsSid);
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(
				String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID)));
		generalWSRequest.setSessionId(String
				.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsIfpRequest cfpRequest = new GtnWsIfpRequest();
		cfpRequest.setGtnIFamilyPlan(fp);
		updateRequest.setGtnWsIfpRequest(cfpRequest);
 
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		GtnUIFrameworkWebserviceResponse updateResponse = getResponse(updateRequest);
		
		if (updateResponse.getEditRecord() != null) {
			Object obj = updateResponse.getEditRecord().get("count");
			if (obj != null) {
				return (int) obj;
			}
		}
		return 0;
	}

	public GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebserviceRequest updateRequest) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
						+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANIES_TAB_COLUMN_UPDATE_FIELD_SERVICE,
				updateRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	public void checkBoxValueChangeLogic() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId(
				String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID)));
		gtnWsGeneralRequest.setSessionId(String
				.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		GtnUIFrameworkWebserviceResponse response = getCheckBoxValueChangeLogicResponse(request);
		GtnIFamilyPlanValidationBean validationBean = response.getGtnWsIfpReponse().getGtnIFamilyPlanValidationBean();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabResultDataTable").setPagedTableHeaderCheckBox(
				validationBean.getCheckedCount() == validationBean.getCount(), 
				GtnFrameworkIfpStringContants.CHECK_RECORD_ID);
	}

	public GtnUIFrameworkWebserviceResponse getCheckBoxValueChangeLogicResponse(
			GtnUIFrameworkWebserviceRequest request) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
						+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMMON_VALIDATION_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

}
