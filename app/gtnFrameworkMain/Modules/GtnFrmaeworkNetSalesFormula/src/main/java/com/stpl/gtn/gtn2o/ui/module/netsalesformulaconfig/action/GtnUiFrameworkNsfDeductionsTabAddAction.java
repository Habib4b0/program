/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFMessageConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfSelectedDeductionsBean;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.netsales.GtnWsNetSalesFormulaGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author STPL
 */
public class GtnUiFrameworkNsfDeductionsTabAddAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger getLogger() {
		return GtnWSLogger.getGTNLogger(GtnUiFrameworkNsfDeductionsTabAddAction.class);
	}

	@Override

	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		getLogger().debug("Inside Add action ");
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		Set<GtnWsRecordBean> availableDeductionBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + "availableDeductionsTable").getValuesFromPagedDataTable();
		if (availableDeductionBean == null || availableDeductionBean.isEmpty()) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.INFO_ACTION);
			alertActionConfig.setActionParameterList(
					Arrays.asList( GtnFrameworkNSFMessageConstants.GTN_NSF_ADD_CUSTOMER_BUTTON_MSG_HEADER,
							GtnFrameworkNSFMessageConstants.GTN_NSF_ADD_DEDUCTION_BUTTON_MSG_BODY ));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkSkipActionException("Add Error ");

		}  
		String formulaType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaType")
				.getCaptionFromComboBox();
		List<GtnUIFrameworkNsfSelectedDeductionsBean> selectedDeductionsBeans = new ArrayList<>();
		if (formulaType != null && formulaType.equals(GtnFrameworkNSFConstants.getFormulaTypeContract())) {
			for (GtnWsRecordBean gtnWsRecordBean : availableDeductionBean) {
				GtnUIFrameworkNsfSelectedDeductionsBean selectedContractBean = new GtnUIFrameworkNsfSelectedDeductionsBean();
				selectedContractBean.setContract(Boolean.FALSE);
				selectedContractBean
						.setDeductionType(Integer.valueOf(String.valueOf(gtnWsRecordBean.getProperties().get(17))));
				selectedContractBean
						.setDeductionSubType(Integer.valueOf(String.valueOf(gtnWsRecordBean.getProperties().get(19))));
				selectedContractBean
						.setDeductionCategory(Integer.valueOf(String.valueOf(gtnWsRecordBean.getProperties().get(18))));
				selectedContractBean
						.setContractMasterSid(Integer.valueOf(String.valueOf(gtnWsRecordBean.getProperties().get(20))));
				selectedContractBean
						.setRsContractSid(Integer.valueOf(String.valueOf(gtnWsRecordBean.getProperties().get(21))));
				selectedDeductionsBeans.add(selectedContractBean);
			}

		} else {

			for (GtnWsRecordBean GtnWsRecordBean : availableDeductionBean) {
				GtnUIFrameworkNsfSelectedDeductionsBean selectedCustomerBean = new GtnUIFrameworkNsfSelectedDeductionsBean();
				selectedCustomerBean.setContract(Boolean.FALSE);
				selectedCustomerBean
						.setDeductionType(Integer.valueOf(String.valueOf(GtnWsRecordBean.getProperties().get(3))));
				selectedCustomerBean
						.setDeductionSubType(Integer.valueOf(String.valueOf(GtnWsRecordBean.getProperties().get(4))));
				selectedCustomerBean
						.setDeductionCategory(Integer.valueOf(String.valueOf(GtnWsRecordBean.getProperties().get(5))));
				selectedDeductionsBeans.add(selectedCustomerBean);
			}
		}

		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(
				GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID).toString());
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		GtnWsNetSalesFormulaGeneralRequest gtnWsNetSalesGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
		updateRequest.setGtnWsNetSalesGeneralRequest(gtnWsNetSalesGeneralRequest);
		gtnWsNetSalesGeneralRequest.setSelectedDeductionInfoBeanList(selectedDeductionsBeans);

		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		GtnUIFrameworkWebserviceResponse gtnWsresponse = getWsResponse(updateRequest);

		if (gtnWsresponse.getGtnWsGeneralResponse().isSucess()) {
			GtnUIFrameworkComponentData selectedDeductionsComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(viewId + "selectedDeductionsResultTable");
			GtnUIFrameworkPagedTableLogic tableLogic = selectedDeductionsComponentData.getCurrentPageTableLogic();
			List<GtnWebServiceSearchCriteria> additionalSearchCriteriaList = new ArrayList<>();

			GtnWebServiceSearchCriteria userIdCriteria = new GtnWebServiceSearchCriteria();
			GtnWebServiceSearchCriteria sessionIdCriteria = new GtnWebServiceSearchCriteria();
			additionalSearchCriteriaList.add(userIdCriteria);
			additionalSearchCriteriaList.add(sessionIdCriteria);

			userIdCriteria.setFieldId(GtnFrameworkCommonStringConstants.USER_ID);
			userIdCriteria.setExpression("EQUALS");
			userIdCriteria.setFilterValue1(GtnUIFrameworkGlobalUI.getCurrentUser());

			sessionIdCriteria.setFieldId(GtnFrameworkCommonStringConstants.SESSION_ID);
			sessionIdCriteria.setExpression("EQUALS");
			sessionIdCriteria.setFilterValue1(
					GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID).toString());

			tableLogic.setAdditioanlSearchCriteriaList(additionalSearchCriteriaList);
			tableLogic.startSearchProcess(new ArrayList<String>(), true);
		}
	}

	/**
	 * @param updateRequest
	 * @return
	 */
	public GtnUIFrameworkWebserviceResponse getWsResponse(GtnUIFrameworkWebserviceRequest updateRequest) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsNsfUriConstants.NSF_SERVICE + "/" + GtnWsNsfUriConstants.NS_DEDUCTION_SELECTION_INSERT,
				updateRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
