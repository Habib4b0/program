package com.stpl.gtn.gtn2o.ui.module.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.fieldfactory.GtnFrameworkRSValueChangeManager;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.rebateschedule.GtnWsRebateScheduleInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;

public class GtnFrameworkRSCommonLogic {

	private GtnFrameworkRSCommonLogic() {
		/**
		 * empty constructor
		 */
	}

	public static void setValuesToFields(String componentId, GtnWsRebateScheduleInfoBean rebateScheduleInfoBean) {

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateSchIdTop", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getRebateScheduleId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheNoTop", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getRebateScheduleNo());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheNameTop", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getRebateScheduleName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleId1", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getRebateScheduleId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleNo1", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getRebateScheduleNo());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleName1", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getRebateScheduleName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleAliasId1", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getRebateScheduleAliasId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentRebateScheduleName", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getParentRebateScheduleName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsTransactionRefName", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getRsTransactionRefName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("paymentGracePeriod", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getPaymentGracePeriod());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentRebateScheduleID", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getParentRebateScheduleID());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsTransactionRefId", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getRsTransactionRefId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("evaluationRuleAssociation", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getEvaluationRuleAssociation());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleStatus1", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRebateScheduleStatus());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleType1", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRebateScheduleType());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateProgramType1", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRebateProgramType());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleCategory1", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRebateScheduleCategory());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleTradeClass", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRebateScheduleTradeClass());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleDesignation", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRebateScheduleDesignation());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsDeductionInclusion", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRsDeductionInclusion());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsCalendar", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRsCalendar());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateFrequency1", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRebateFrequency());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("paymentLevel", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getPaymentLevel());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("paymentFrequency", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getPaymentFrequency());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("paymentTerms", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getPaymentTerms());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("paymentMethod", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getPaymentMethod());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("interestBearingBasis", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getInterestBearingBasis());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("evaluationRuleLevel", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getEvaluationRuleLevel());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("evaluationRuleType", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getEvaluationRuleType());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("interestBearingIndicator", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getInterestBearingIndicator());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("calculationRuleLevel", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getCalculationRuleLevel());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("calculationType1", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getCalculationType());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("calculationLevel", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getCalculationLevel());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateRuleType", componentId)
				.loadComboBoxComponentValue(rebateScheduleInfoBean.getRebateRuleType());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleStartDate", componentId)
				.loadDateValue(rebateScheduleInfoBean.getRebateScheduleStartDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleEndDate", componentId)
				.loadDateValue(rebateScheduleInfoBean.getRebateScheduleEndDate());

	}

	public static List<GtnWebServiceSearchCriteria> addDataTableLoadAction(String ifpId, String url) {
		Map<String, String> inputValueMap = new HashMap<>();
		inputValueMap.put("ifpId", ifpId);
		inputValueMap.put(GtnFrameworkCommonConstants.SESSION_ID,
				GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
		inputValueMap.put("userId", GtnUIFrameworkGlobalUI.getCurrentUser());
		loadDataFromService(inputValueMap, url);
		GtnFrameworkRSValueChangeManager.setValueChangeAllowed(false);

		List<GtnWebServiceSearchCriteria> rsAdditioanlSearchCriteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria rsUserIdCriteria = new GtnWebServiceSearchCriteria();
		GtnWebServiceSearchCriteria rsSessionIdCriteria = new GtnWebServiceSearchCriteria();
		rsAdditioanlSearchCriteriaList.add(rsUserIdCriteria);
		rsAdditioanlSearchCriteriaList.add(rsSessionIdCriteria);
		rsUserIdCriteria.setFieldId("userId");
		rsUserIdCriteria.setExpression("EQUALS");
		rsUserIdCriteria.setFilterValue1(GtnUIFrameworkGlobalUI.getCurrentUser());
		rsSessionIdCriteria.setFieldId(GtnFrameworkCommonConstants.SESSION_ID);
		rsSessionIdCriteria.setExpression("EQUALS");
		rsSessionIdCriteria.setFilterValue1(
				GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
		return rsAdditioanlSearchCriteriaList;
	}

	public static void loadDataFromService(Map<String, String> inputValueMap, String url) {
		GtnUIFrameworkWebServiceClient gtnUIFrameworkWebServiceClient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		gtnUIFrameworkWebserviceRequest.setGtnWsGeneralRequest(generalRequest);
		List<Object> inputList = new ArrayList<>();
		inputList.add(inputValueMap);
		generalRequest.setComboBoxWhereclauseParamList(inputList);
		gtnUIFrameworkWebServiceClient.callGtnWebServiceUrl(url, gtnUIFrameworkWebserviceRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	public static void generateSessionId() {
		GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkCommonConstants.SESSION_ID,
				Calendar.getInstance().get(Calendar.MILLISECOND));
	}

}
