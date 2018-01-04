/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
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
public class GtnUiFrameworkNsfLoadDataAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private static final GtnWSLogger GTN_LOGGER = GtnWSLogger.getGTNLogger(GtnUiFrameworkNsfLoadDataAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		//
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		int sysId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkNSFConstants.getSystemid());
		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		nsfInfoBean.setSystemId(sysId);
		getValueFromService(nsfInfoBean, viewId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void getValueFromService(GtnUIFrameworkNsfInfoBean nsfInfoBean, String viewId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnUIFrameworkNsfInfoBean nsfBean = nsfInfoBean;
		try {
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
			generalWSRequest.setSessionId(
					GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID).toString());
			request.setGtnWsGeneralRequest(generalWSRequest);

			GtnWsNetSalesFormulaGeneralRequest gtnWsNetSalesGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
			gtnWsNetSalesGeneralRequest.setnSfInfoBean(nsfBean);
			request.setGtnWsNetSalesGeneralRequest(gtnWsNetSalesGeneralRequest);

			GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					"/" + GtnWsNsfUriConstants.NSF_SERVICE + "/" + GtnWsNsfUriConstants.NS_LOAD_SERVICE, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			nsfBean = gtnWsresponse.getGtnWsNetSalesGeneralResponse().getNsfInfoBean();
			if (gtnWsresponse.getGtnWsGeneralResponse().isSucess()) {
				loadValuesToComponents(nsfBean, viewId);
			}

		} catch (Exception systemExcption) {
			GTN_LOGGER.error("Error in NetSalesFormula Load Data Action", systemExcption);

			throw new GtnFrameworkGeneralException("Load Error", systemExcption);
		}
	}

	private void loadValuesToComponents(GtnUIFrameworkNsfInfoBean nsfInfoBean, String viewId)
			throws GtnFrameworkValidationFailedException {

		GtnUIFrameworkBaseComponent formulaId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaId");
		GtnUIFrameworkBaseComponent formulaNo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaNo");
		GtnUIFrameworkBaseComponent formulaName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaName");
		GtnUIFrameworkBaseComponent formulaType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaType");
		GtnUIFrameworkBaseComponent formulaCategory = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + "formulaCategory");

		formulaId.loadFieldValue(nsfInfoBean.getFormulaId());
		formulaNo.loadFieldValue(nsfInfoBean.getFormulaNo());
		formulaName.loadFieldValue(nsfInfoBean.getFormulaName());
		formulaType.loadComboBoxComponentValue(nsfInfoBean.getFormulaType());
		formulaCategory.loadComboBoxComponentValue(nsfInfoBean.getFormulaCategory());

		GtnWsRecordBean netSalesRuleBean = new GtnWsRecordBean();
		netSalesRuleBean.setRecordHeader(Arrays.asList(GtnFrameworkNSFConstants.getSystemid()));
		netSalesRuleBean.addProperties(GtnFrameworkNSFConstants.getSystemid(), nsfInfoBean.getNetSalesSid());
		netSalesRuleBean.addAdditionalProperty(nsfInfoBean.getNetSalesSid());
		GtnUIFrameworkBaseComponent netSalesRule = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + "netSalesRule");
		netSalesRule.setCustomData(netSalesRuleBean);
		netSalesRule.loadFieldValue(nsfInfoBean.getNetSalesRuleName());

		GtnUIFrameworkBaseComponent contractSelection = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + "contractSelection");
		contractSelection.setPropertyValue(nsfInfoBean.getContractSelection());

		GtnFrameworkNSFCommonLogic.reloadTable(viewId, "selectedCustomersResultTable");

		GtnUIFrameworkComponentData selectedDeductionsComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(viewId + "selectedDeductionsResultTable");
		GtnUIFrameworkPagedTableLogic selectedDeductionsTableLogic = selectedDeductionsComponentData
				.getCurrentPageTableLogic();
		selectedDeductionsTableLogic
				.setAdditioanlSearchCriteriaList(GtnFrameworkNSFCommonLogic.getAdditioanlSearchCriteriaList());
		selectedDeductionsTableLogic.startSearchProcess(new ArrayList<String>(), true);

	}

}
