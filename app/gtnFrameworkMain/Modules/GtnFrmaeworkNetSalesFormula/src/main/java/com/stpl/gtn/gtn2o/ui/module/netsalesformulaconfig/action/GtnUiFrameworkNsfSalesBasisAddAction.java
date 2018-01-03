/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFMessageConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUiFrameworkNsfSelectedCustomerBean;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.netsales.GtnWsNetSalesFormulaGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author STPL
 */
public class GtnUiFrameworkNsfSalesBasisAddAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		Set<GtnWsRecordBean> availableCustomerBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + "availableCustomersTable").getValuesFromPagedDataTable();
		if (availableCustomerBean == null || availableCustomerBean.isEmpty()) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.INFO_ACTION);
			alertActionConfig
					.addActionParameter(GtnFrameworkNSFMessageConstants.GTN_NSF_ADD_CUSTOMER_BUTTON_MSG_HEADER);
			alertActionConfig.addActionParameter(GtnFrameworkNSFMessageConstants.GTN_NSF_ADD_CUSTOMER_BUTTON_MSG_BODY);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkSkipActionException("Add Error ");

		}

		List<GtnUiFrameworkNsfSelectedCustomerBean> selectedCustomerBeans = new ArrayList<>();
		for (GtnWsRecordBean GtnWsRecordBean : availableCustomerBean) {
			GtnUiFrameworkNsfSelectedCustomerBean selectedCustomerBean = new GtnUiFrameworkNsfSelectedCustomerBean();
			selectedCustomerBean
					.setContractMasterSid(Integer.valueOf(String.valueOf(GtnWsRecordBean.getProperties().get(6))));
			selectedCustomerBean
					.setContractDetSid(Integer.valueOf(String.valueOf(GtnWsRecordBean.getProperties().get(7))));
			selectedCustomerBean.setContractNo(String.valueOf(GtnWsRecordBean.getPropertyValue("contractNo")));
			selectedCustomerBean.setContractName(String.valueOf(GtnWsRecordBean.getPropertyValue("contractName")));
			selectedCustomerBean.setCfpNo(String.valueOf(GtnWsRecordBean.getPropertyValue("cfpNo")));
			selectedCustomerBean.setCfpName(String.valueOf(GtnWsRecordBean.getPropertyValue("cfpName")));
			selectedCustomerBean.setCustomerNo(String.valueOf(GtnWsRecordBean.getPropertyValue("customerNo")));
			selectedCustomerBean.setCustomerName(String.valueOf(GtnWsRecordBean.getPropertyValue("customerName")));
			selectedCustomerBeans.add(selectedCustomerBean);
		}

		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(
				GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID).toString());
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		GtnWsNetSalesFormulaGeneralRequest gtnWsNetSalesGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
		updateRequest.setGtnWsNetSalesGeneralRequest(gtnWsNetSalesGeneralRequest);
		gtnWsNetSalesGeneralRequest.setNsCustomerInfoBeanList(selectedCustomerBeans);

		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsNsfUriConstants.NSF_SERVICE + "/" + GtnWsNsfUriConstants.NS_SALESBASIS_INSERT, updateRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		if (gtnWsresponse.getGtnWsGeneralResponse().isSucess()) {
			GtnFrameworkNSFCommonLogic.reloadTable(viewId, "selectedCustomersResultTable");
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
