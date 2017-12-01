/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.validation;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfInfoBean;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.netsales.GtnWsNetSalesFormulaGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Mohamed.Shahul
 */
public class GtnUiFrameworkNsfRuleSaveUniqueValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		String mode = (String) GtnUIFrameworkGlobalUI.getSessionProperty("mode");
		boolean isEditMode = "edit".equalsIgnoreCase(mode);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		settingNsfInfoToBean(nsfInfoBean, viewId);
		GtnWsNetSalesFormulaGeneralRequest gtnWsNetSalesGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
		gtnWsNetSalesGeneralRequest.setnSfInfoBean(nsfInfoBean);
		request.setGtnWsNetSalesGeneralRequest(gtnWsNetSalesGeneralRequest);
		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsNsfUriConstants.NSF_SERVICE + "/" + GtnWsNsfUriConstants.NS_SAVE_UNIQUE_VALIDATION, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (!gtnWsresponse.getGtnWsGeneralResponse().isSucess() && !isEditMode) {
			String msg = "Entered Net Sales Formula Rule is already exists  ";
			GtnUIFrameWorkActionConfig nsfAlertActionConfig = new GtnUIFrameWorkActionConfig();
			nsfAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			nsfAlertActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.ERROR);
			nsfAlertActionConfig.addActionParameter(msg);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, nsfAlertActionConfig);
			throw new GtnFrameworkValidationFailedException("Validation Error :" + msg);

		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void settingNsfInfoToBean(GtnUIFrameworkNsfInfoBean nsfInfoBean, String viewId) {
		String formulaId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaId").getStringFromField();
		String formulaNo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaNo").getStringFromField();
		String formulaName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaName").getStringFromField();

		nsfInfoBean.setFormulaId(formulaId);
		nsfInfoBean.setFormulaNo(formulaNo);
		nsfInfoBean.setFormulaName(formulaName);
	}

}
