package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkLoadDataTableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemPricingQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsValidationBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkItemMasterIdEditListDeleteAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionParameter = gtnUIFrameWorkActionConfig.getActionParameterList();
		List<String> fieldValues = gtnUIFrameWorkActionConfig.getFieldValues();

		GtnWsRecordBean selectedId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameter.get(1).toString())
				.getValueFromDataTable();
		boolean isIdentiferTab = (boolean) actionParameter.get(2);
		if (isIdentiferTab) {
			getIdentifierDeleteAction(selectedId, componentId);

		} else {
			getPricingdeleteAction(selectedId, componentId);
		}
		refreshResultTable(actionParameter.get(1).toString());
		refreshComponenent(componentId, fieldValues);
		
	}

	private void getPricingdeleteAction(GtnWsRecordBean selectedId, String componentId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnWsItemMasterRequest imRequest = new GtnWsItemMasterRequest();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsItemPricingQualifierBean qualifierBean = new GtnWsItemPricingQualifierBean();
		qualifierBean.setItemPricingQualifierSid(
				(Integer) selectedId.getPropertyValueByIndex(selectedId.getProperties().size() - 2));
		GtnWsValidationBean valBean = new GtnWsValidationBean();
		imRequest.setGtnWsValidationBean(valBean);
		valBean.setGtnWsItemPricingQualifierBean(qualifierBean);
		request.setGtnWsItemMasterRequest(imRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_PRICING_QUALIFER_DELETE_VALIDATION_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (response != null && response.getGtnWsItemMasterResponse() != null) {
			Object msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_QUALIFIER_VALIDATION_MSG_DELETE_003;
			if (response.getGtnWsItemMasterResponse().isPricingQualfierUsed()) {
				GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
				GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
				alertActionConfig.setActionParameterList(Arrays
						.asList(GtnFrameworkItemMasterStringContants.GTN_ITEM_QUALIFIER_DELETE_FAILED_MSG_HEADER, msg));
				alertAction.doAction(componentId, alertActionConfig);
				throw new GtnFrameworkValidationFailedException("Validation Error :" + msg);
			} else {
				imRequest = new GtnWsItemMasterRequest();
				request = new GtnUIFrameworkWebserviceRequest();
				qualifierBean = new GtnWsItemPricingQualifierBean();
				qualifierBean.setItemPricingQualifierSid(
						(Integer) selectedId.getPropertyValueByIndex(selectedId.getProperties().size() - 2));
				imRequest.setGtnWsItemPricingQualifierBean(qualifierBean);
				request.setGtnWsItemMasterRequest(imRequest);
				wsclient.callGtnWebServiceUrl(
						GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
								+ GtnWsItemMasterContants.GTN_WS_ITEM_PRICING_QUALIFIER_DELETE_SERVICE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			}
		}

	}

	private void getIdentifierDeleteAction(GtnWsRecordBean selectedId, String componentId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnWsItemMasterRequest imRequest = new GtnWsItemMasterRequest();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsItemQualifierBean qualifierBean = new GtnWsItemQualifierBean();
		qualifierBean.setItemQualifierSid(
				(Integer) selectedId.getPropertyValueByIndex(selectedId.getProperties().size() - 2));
		GtnWsValidationBean valBean = new GtnWsValidationBean();
		imRequest.setGtnWsValidationBean(valBean);
		valBean.setGtnWsItemQualifierBean(qualifierBean);
		request.setGtnWsItemMasterRequest(imRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_INDENTIFIER_QUALIFER_DELETE_VALIDATION_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (response != null && response.getGtnWsItemMasterResponse() != null) {
			Object msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_QUALIFIER_VALIDATION_MSG_DELETE_003;
			if (response.getGtnWsItemMasterResponse().isIndentifierQualfierUsed()) {
				GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
				GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
				alertActionConfig.setActionParameterList(Arrays
						.asList(GtnFrameworkItemMasterStringContants.GTN_ITEM_QUALIFIER_DELETE_FAILED_MSG_HEADER, msg));
				alertAction.doAction(componentId, alertActionConfig);
				throw new GtnFrameworkValidationFailedException("Validation Error :" + msg);
			} else {
				imRequest = new GtnWsItemMasterRequest();
				request = new GtnUIFrameworkWebserviceRequest();
				qualifierBean = new GtnWsItemQualifierBean();
				qualifierBean.setItemQualifierSid(
						(Integer) selectedId.getPropertyValueByIndex(selectedId.getProperties().size() - 2));
				imRequest.setGtnWsItemQualifierBean(qualifierBean);
				request.setGtnWsItemMasterRequest(imRequest);
				wsclient.callGtnWebServiceUrl(
						GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
								+ GtnWsItemMasterContants.GTN_WS_ITEM_INDENTIFIER_QUALIFIER_DELETE_SERVICE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			}
		}
	}

	private void refreshComponenent(String componentId, List<String> fieldValues) throws GtnFrameworkGeneralException {

		GtnUIFrameWorkActionConfig imEditListRefreshAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		imEditListRefreshAction.addActionParameter(GtnFrameworkItemMasterIdEditListRefreshAction.class.getName());
		imEditListRefreshAction.setFieldValues(fieldValues);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, imEditListRefreshAction);
	}
        
	private void refreshResultTable(String componentId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig refreshActionConfig = new GtnUIFrameWorkActionConfig();
		refreshActionConfig.addActionParameter(componentId);
		GtnUIFrameWorkLoadDataTableAction loadDataTableAction = new GtnUIFrameWorkLoadDataTableAction();
		loadDataTableAction.doAction(componentId, refreshActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
