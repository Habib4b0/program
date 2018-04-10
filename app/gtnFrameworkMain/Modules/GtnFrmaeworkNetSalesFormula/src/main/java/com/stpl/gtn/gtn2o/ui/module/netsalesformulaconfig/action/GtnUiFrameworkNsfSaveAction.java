/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfInfoBean;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.netsales.GtnWsNetSalesFormulaGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author STPL
 */
public class GtnUiFrameworkNsfSaveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(final String componentId, final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);

		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		String contractSelectionValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "contractSelection")
				.getStringFromField();

		GtnUIFrameworkBaseComponent formulaType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaType");
		String formulaTypeCaption = formulaType.getCaptionFromComboBox();
		nsfInfoBean.setContractSelection(contractSelectionValue);
		nsfInfoBean.setFormulaTypeDesc(formulaTypeCaption);
		nsfInfoBean.setFormulaType(formulaType.getIntegerFromField());
		settingNsfInfoToBean(nsfInfoBean, viewId);
		if (contractSelectionValue.equals(GtnFrameworkNSFConstants.getExistingContract())) {
			GtnWsRecordBean netSalesRuleBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinComponentData(viewId + "netSalesRule").getCustomData();
			if (netSalesRuleBean != null
					&& netSalesRuleBean.getPropertyValue(GtnFrameworkNSFConstants.getSystemid()) != null) {
				Object netSalesSid = netSalesRuleBean.getPropertyValue(GtnFrameworkNSFConstants.getSystemid());
				nsfInfoBean.setNetSalesSid(Integer.valueOf(String.valueOf(netSalesSid)));
			}
		}
		Object systemId = GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkNSFConstants.getSystemid());
		if (systemId != null) {
			nsfInfoBean.setSystemId((Integer) (systemId));
		}
		GtnUIFrameworkComponentData availableCustomerTableData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(viewId + "availableCustomersTable");
		GtnUIFrameworkPagedTableLogic availableCustomerTableLogic = availableCustomerTableData
				.getCurrentPageTableLogic();
		nsfInfoBean.setAvailableContractSearchCriteriaList(availableCustomerTableLogic.getCurrentSearchCriteria());
		boolean savaflag = saveToDb(nsfInfoBean);
		if (savaflag) {
			if (!"netSalesFormulaAddView_backButton".equals(componentId)) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).setCaption("UPDATE");
			}
			GtnUIFrameWorkActionConfig notificationConfig = new GtnUIFrameWorkActionConfig();
			notificationConfig.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			Object msg = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaName").getStringFromField()
					+ " has been successfully saved";
			notificationConfig.setActionParameterList(Arrays.asList(msg, GtnFrameworkNSFConstants.getEmpty(), 2));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationConfig);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void settingNsfInfoToBean(GtnUIFrameworkNsfInfoBean nsfInfoBean, String viewId)
			throws GtnFrameworkValidationFailedException {
		String formulaId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaId").getStringFromField();
		String formulaNo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaNo").getStringFromField();
		String formulaName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaName").getStringFromField();
		Integer formulaType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaType")
				.getIntegerFromField();
		Integer formulaCategory = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaCategory")
				.getIntegerFromField();

		nsfInfoBean.setFormulaId(formulaId);
		nsfInfoBean.setFormulaNo(formulaNo);
		nsfInfoBean.setFormulaName(formulaName);
		nsfInfoBean.setFormulaType(formulaType);
		nsfInfoBean.setFormulaCategory(formulaCategory);
	}

	private boolean saveToDb(GtnUIFrameworkNsfInfoBean nsfInfoBean) throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		try {

			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

			generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
			generalWSRequest.setSessionId(
					GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID).toString());
			request.setGtnWsGeneralRequest(generalWSRequest);
			GtnWsNetSalesFormulaGeneralRequest gtnWsNetSalesGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
			gtnWsNetSalesGeneralRequest.setnSfInfoBean(nsfInfoBean);
			request.setGtnWsNetSalesGeneralRequest(gtnWsNetSalesGeneralRequest);

			GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					"/" + GtnWsNsfUriConstants.NSF_SERVICE + "/" + GtnWsNsfUriConstants.NS_SAVE_SERVICE, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			return gtnWsresponse.getGtnWsGeneralResponse().isSucess();
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException("Save Error", systemExcption);
		}
	}
}
