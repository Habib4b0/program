package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkStringConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.rebateplan.GtnWsRebatePlanInfoBean;
import com.stpl.gtn.gtn2o.ws.rebateplan.GtnWsRebatePlanRuleDetailBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.rebateplan.GtnWsRebatePlanGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.rebateplan.GtnWsRebatePlanGeneralResponse;

public class GtnUIFrameWorkResetYesButtonAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkResetYesButtonAction.class);
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsRebatePlanInfoBean rebatePlanInfoBean = new GtnWsRebatePlanInfoBean();
		String mode = String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("mode"));
		Integer rebatePlanSid = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("systemId");
		String actionType = String.valueOf(actionParamList.get(1));
		int position = 0;
		if (actionType.equalsIgnoreCase("RESET_ALL")) {
			position = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanAddViewtabSheet")
					.getTabSheetSelectedTabIndex();

		} else if (actionType.equalsIgnoreCase("TIER_TABLE_RESET")) {
			position = -1;

		} else {
			position = -2;
		}
		if (rebatePlanSid != null && rebatePlanSid != 0
				&& !GtnUIFrameworkModeType.ADD.equals(GtnUIFrameworkModeType.valueOf(mode))) {
			rebatePlanInfoBean = loadDataFromService(rebatePlanSid, rebatePlanInfoBean);
			loadRebatePlanInfoBasicTopBean(rebatePlanInfoBean);
		} else {
			GtnUIFrameworkBaseComponent rebatePlanCalcualtionFrom = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_FROM);
			rebatePlanCalcualtionFrom.setComponentEnable(true);
			rebatePlanCalcualtionFrom.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkBaseComponent rebatePlanCalcualtionTo = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_TO);
			rebatePlanCalcualtionTo.setComponentEnable(true);
			rebatePlanCalcualtionTo.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkBaseComponent rebatePlanCalcualtionFromComplex = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM_COMPLEX);
			rebatePlanCalcualtionFromComplex.setComponentEnable(true);
			rebatePlanCalcualtionFromComplex.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkBaseComponent rebatePlanCalcualtionToComplex = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO_COMPLEX);
			rebatePlanCalcualtionToComplex.setComponentEnable(true);
			rebatePlanCalcualtionToComplex.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);

		}

		if (GtnUIFrameworkModeType.VIEW.equals(GtnUIFrameworkModeType.valueOf(mode))) {
			loadRebatePlanNotesTab();
		}

		switch (position) {
		case 0:
			loadRebatePlanInfoBasicBean(rebatePlanInfoBean);
			break;
		case 1:
			loadRebatePlanCalculationBean(rebatePlanInfoBean);
			getLoadDetails(rebatePlanInfoBean);
			break;
		case 2:
			loadNotesTab(componentId, rebatePlanInfoBean, logger);
			break;
		case -1:
			getLoadDetails(rebatePlanInfoBean);
			resetRuleDetailsInformation();
			break;
		case -2:
			loadRebatePlanInfoBasicBean(rebatePlanInfoBean);
			loadRebatePlanCalculationBean(rebatePlanInfoBean);
			getLoadDetails(rebatePlanInfoBean);
			loadNotesTab(componentId, rebatePlanInfoBean, logger);
			break;
		default:
			break;
		}

	}

	private void getLoadDetails(GtnWsRebatePlanInfoBean rebatePlanInfoBean)
			throws GtnFrameworkValidationFailedException {

		String resultValue = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkStringConstants.REBATE_PLAN_INFO_FORMULA_TYPE)
				.getCaptionFromComboBox();
		if (resultValue.equals("Complex")) {
			balancedParenthensies("ruleDetailsattachResultTableComplex", rebatePlanInfoBean);
		} else {
			loadRuleDetailsInfo(GtnFrameworkStringConstants.REBATE_DETAILS_ATTACH_RESULT_TABLE, rebatePlanInfoBean);
		}
	}

	private void loadRebatePlanInfoBasicTopBean(GtnWsRebatePlanInfoBean rebatePlanInfoBean) {

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanIdTop")
				.loadFieldValue(rebatePlanInfoBean.getRebatePlanId());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanNameTop")
				.loadFieldValue(rebatePlanInfoBean.getRebatePlanName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanNoTop")
				.loadFieldValue(rebatePlanInfoBean.getRebatePlanNo());
	}

	private void loadRebatePlanInfoBasicBean(GtnWsRebatePlanInfoBean rebatePlanInfoBean) {

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanInformationTabRebatePlanID")
				.loadFieldValue(rebatePlanInfoBean.getRebatePlanId());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanInformationTabRebatePlanName")
				.loadFieldValue(rebatePlanInfoBean.getRebatePlanName());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanInformationTabRebatePlanType")
				.loadComboBoxComponentValue(rebatePlanInfoBean.getRebatePlanType());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanInformationTabRebatePlanNo")
				.loadFieldValue(rebatePlanInfoBean.getRebatePlanNo());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanInformationTabRebateStatus")
				.loadComboBoxComponentValue(rebatePlanInfoBean.getRebatePlanStatus());
		if (rebatePlanInfoBean.getFormulaType().isEmpty()) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkStringConstants.REBATE_PLAN_INFO_FORMULA_TYPE)
					.loadDateValue(null);
		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkStringConstants.REBATE_PLAN_INFO_FORMULA_TYPE)
					.loadFieldValue(rebatePlanInfoBean.getFormulaType());
		}

	}

	private void loadRebatePlanCalculationBean(GtnWsRebatePlanInfoBean rebatePlanInfoBean) {

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsRebateStructure")
				.loadComboBoxComponentValue(rebatePlanInfoBean.getRebateStructure());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsRangeBasedOn")
				.loadComboBoxComponentValue(rebatePlanInfoBean.getRangeBasedOn());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsNetSalesFormula")
				.loadFieldValue(rebatePlanInfoBean.getNetSalesFormulaData());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsNetSalesRule")
				.loadFieldValue(rebatePlanInfoBean.getNetSalesRuleData());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsRebateBasedOn")
				.loadComboBoxComponentValue(rebatePlanInfoBean.getRebateBasedOn());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationSelfGrowthIndicator")
				.loadFieldValue(!rebatePlanInfoBean.getSelfGrowthIndicator().trim().equals("0")
						? rebatePlanInfoBean.getSelfGrowthIndicator().trim() : "");

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationSelfGrowthReference")
				.loadFieldValue(!"".equals(rebatePlanInfoBean.getSelfGrowthReference())
						? rebatePlanInfoBean.getSelfGrowthReference() : "");

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationSelfGrowthFrom")
				.loadDateValue(rebatePlanInfoBean.getSelfGrowthFrom());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationSelfGrowthTo")
				.loadDateValue(rebatePlanInfoBean.getSelfGrowthTo());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationMarketShareIndicator")
				.loadFieldValue(!rebatePlanInfoBean.getMarketShareIndicator().trim().equals("")
						? rebatePlanInfoBean.getMarketShareIndicator() : "");

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationMarketShareReference")
				.loadFieldValue(!"".equals(rebatePlanInfoBean.getMarketShareReference())
						? rebatePlanInfoBean.getMarketShareReference() : "");

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationMarketShareFrom")
				.loadDateValue(rebatePlanInfoBean.getMarketShareFrom());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationMarketShareTo")
				.loadDateValue(rebatePlanInfoBean.getMarketShareTo());
		resetRuleDetailsInformation();

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private static GtnWsRebatePlanInfoBean loadDataFromService(int systemId,
			GtnWsRebatePlanInfoBean rebatePlanInfoBean) {

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		rebatePlanInfoBean.setSystemId(systemId);
		GtnWsRebatePlanGeneralRequest gtnWsRebatePlanGeneralRequest = new GtnWsRebatePlanGeneralRequest();
		gtnWsRebatePlanGeneralRequest.setRebatePlanInfoBean(rebatePlanInfoBean);
		request.setGtnWsRebatePlanGeneralRequest(gtnWsRebatePlanGeneralRequest);

		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.RP_SERVICE + "/" + GtnWsCDRContants.GET_RP_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		GtnWsRebatePlanGeneralResponse getGtnWsRebatePlanGeneralResponse = response.getGtnWsRebatePlanGeneralResponse();

		return getGtnWsRebatePlanGeneralResponse.getRebatePlanInfoBean();
	}

	private void loadNotesTab(String componentId, GtnWsRebatePlanInfoBean rebatePlaninfoBean, GtnWSLogger logger) {
		try {
			GtnUIFrameWorkActionConfig rpResetNotesTabLoadAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.LOAD_NOTES_TAB);
			rpResetNotesTabLoadAction.addActionParameter(rebatePlaninfoBean.getNoteBeanList());
			rpResetNotesTabLoadAction.addActionParameter(rebatePlaninfoBean.getInternalNotes());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, rpResetNotesTabLoadAction);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void loadRuleDetailsInfo(String resultTableId, GtnWsRebatePlanInfoBean rebatePlaninfoBean)
			throws GtnFrameworkValidationFailedException {
		Double tierTo = 0d;
		Double tierFrom = 0d;
		List<GtnWsRecordBean> resultLiist = new ArrayList<>();
		GtnWsRecordBean dto;
		for (GtnWsRebatePlanRuleDetailBean ruleDetailsBean : rebatePlaninfoBean.getRebatePlanRuleDetailBean()) {
			tierFrom = ruleDetailsBean.getFromDesc();
			tierTo = ruleDetailsBean.getToDesc();
			dto = new GtnWsRecordBean();
			List<Object> recordHeader = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId)
					.getTableRecordHeader();
			setValueToDto(dto, ruleDetailsBean, tierTo, recordHeader);
			dto.addProperties(String.valueOf(recordHeader.get(2)), ruleDetailsBean.getOperatorDesc());
			dto.addProperties(String.valueOf(recordHeader.get(3)), ruleDetailsBean.getValueDesc());

			dto.addAdditionalProperty(ruleDetailsBean.getFrom());
			dto.addAdditionalProperty(ruleDetailsBean.getToDesc());
			dto.addAdditionalProperty(ruleDetailsBean.getOperator());
			if (ruleDetailsBean.getOperatorDesc().equals("$")) {
				dto.addAdditionalProperty(ruleDetailsBean.getValueDesc());
			} else {
				dto.addAdditionalProperty(ruleDetailsBean.getValue());
			}
			resultLiist.add(dto);
		}
		getToFromTextValue(tierTo, tierFrom, GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM,
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO, resultLiist);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId).loadContainer(resultTableId, resultLiist);

	}

	private void setValueToDto(GtnWsRecordBean dto, GtnWsRebatePlanRuleDetailBean ruleDetailsBean, Double tierTo,
			List<Object> recordHeader) {
		dto.setRecordHeader(recordHeader);
		dto.addProperties(String.valueOf(recordHeader.get(0)), ruleDetailsBean.getFromDesc());
		dto.addProperties(String.valueOf(recordHeader.get(1)),
				Double.compare(tierTo, 0d) == 0 || Double.compare(tierTo, 0.0) == 0 ? null
						: ruleDetailsBean.getToDesc());

	}

	private void getToFromTextValue(Double tierTo, Double tierFrom, String fromTier, String toTier,
			List<GtnWsRecordBean> resultList) {
		if (!resultList.isEmpty()) {
			if (Double.compare(tierTo, 0d) == 0 || Double.compare(tierTo, 0.0) == 0) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(toTier).getComponent().setEnabled(false);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fromTier).loadFieldValue(tierFrom);

			} else {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(toTier).getComponent().setEnabled(false);

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fromTier).loadFieldValue((tierTo + 0.01));

			}
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fromTier).getComponent().setEnabled(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(toTier).getComponent().setEnabled(false);
		}
	}

	static String getDefaultValue(String value) {
		return value.equals(GtnFrameworkCommonConstants.SELECT_ONE) || "0".equals(value)
				? GtnFrameworkCommonStringConstants.STRING_EMPTY : value;
	}

	public String getValueDesc(String valueDesc) {
		return valueDesc;
	}

	public void balancedParenthensies(String resultTableId, GtnWsRebatePlanInfoBean rebatePlaninfoBean)
			throws GtnFrameworkValidationFailedException {
		Double tierTo = 0d;
		Double tierFrom = 0d;
		List<GtnWsRecordBean> resultList = new ArrayList<>();
		GtnWsRecordBean dto;
		for (GtnWsRebatePlanRuleDetailBean ruleDetailsBeanComplex : rebatePlaninfoBean.getRebatePlanRuleDetailBean()) {
			tierFrom = ruleDetailsBeanComplex.getFromDesc();
			tierTo = ruleDetailsBeanComplex.getToDesc();
			dto = new GtnWsRecordBean();
			List<Object> recordHeaderComplex = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId)
					.getTableRecordHeader();
			setValueToDto(dto, ruleDetailsBeanComplex, tierTo, recordHeaderComplex);

			dto.addAdditionalProperty(ruleDetailsBeanComplex.getFrom());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getToDesc());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getOperatorDesc());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getValue());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getOperatorType());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getAdjustmentOperator1());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getAdjustmentValue1itemPricingQualifier());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getOperatorType2());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getAdjustmentOperator2());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getAdjustmentValue2itemPricingQualifier());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getOperatorType3());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getAdjustmentOperator3());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getAdjustmentValue3itemPricingQualifier());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getOperatorType4());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getAdjustmentOperator4());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getAdjustmentValue4itemPricingQualifier());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getOperatorType5());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getAdjustmentOperator5());
			dto.addAdditionalProperty(ruleDetailsBeanComplex.getAdjustmentValue5itemPricingQualifier());
			String s = ruleDetailsBeanComplex.getValueDesc();

			List<Character> splitList = new ArrayList<>();
			StringBuilder sym = new StringBuilder();
			String[] signs = s.split("[\\(\\)]");
			String value = getValueFromFormula(s);
			splitOperator(s, splitList);

			for (int i = 0; i < splitList.size(); i++) {
				Character s3 = splitList.get(i);
				String comma = "";
				if (i != splitList.size() - 1) {
					comma = ",";
				}
				if ((s3 == '$' || s3 == '%')) {
					sym.append(s3);
					sym.append(comma);
				}
			}

			splitValue(s, dto, recordHeaderComplex, signs, sym, value);
			resultList.add(dto);

		}
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId).loadContainer(resultTableId, resultList);

		getToFromTextValue(tierTo, tierFrom, GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM_COMPLEX,
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO_COMPLEX, resultList);
	}

	public void splitValue(String s, GtnWsRecordBean dto, List<Object> recordHeader, String[] signarray,
			StringBuilder sym, String value) {
		String[] symarray = sym.toString().split(",");
		List<String> list = Arrays.asList(value.split(","));
		int k = 3;
		for (int j = 0; j < list.size(); j++) {

			dto.addProperties(String.valueOf(recordHeader.get(k)), list.get(j));
			dto.addAdditionalProperties(k, list.get(j));
			k += 3;
		}
		int n = 4;
		for (int i = 0; i < signarray.length; i++) {
			if (signarray[i].length() == 1) {
				String a = signarray[i];
				dto.addProperties(String.valueOf(recordHeader.get(n)), a);
				dto.addAdditionalProperties(n, a);
				n += 3;
			}
		}
		int m = 2;
		for (int i = 0; i < symarray.length; i++) {
			String a = symarray[i];
			dto.addProperties(String.valueOf(recordHeader.get(m)), a);
			dto.addAdditionalProperties(m, a);
			m += 3;
		}

	}

	private String getValueFromFormula(String formulaString) {
		String operationValue = formulaString.replace(")-(", "");
		operationValue = operationValue.replace(")+(", ",");
		operationValue = operationValue.replace(")*(", ",");
		operationValue = operationValue.replace(")/(", ",");
		operationValue = operationValue.replace(")(", ",");
		operationValue = operationValue.replace("))", "");
		operationValue = operationValue.replace(")$", ",");
		operationValue = operationValue.replace("%)", ",");
		operationValue = operationValue.replace("),", ",");
		operationValue = operationValue.replace(",,", ",");
		operationValue = operationValue.replace(")", ",");
		for (int i = 0; i <= 4; i++) {
			if (operationValue.charAt(0) == '(') {
				operationValue = operationValue.substring(i + 1, operationValue.length());
			}

		}
		if (operationValue.charAt(0) == '$') {
			operationValue = operationValue.substring(1, operationValue.length());
		}
		operationValue = operationValue.replace(",$", ",");
		operationValue = operationValue.replace("$", ",");
		operationValue = operationValue.replace("%", "");
		return operationValue;
	}

	public void splitOperator(String s, List<Character> list) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != '(' && s.charAt(i) != ')') {
				list.add(s.charAt(i));
			}
		}
	}

	private void loadRebatePlanNotesTab() {
		GtnUIFrameworkNotesTab rebateplannotesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinComponent(GtnFrameworkStringConstants.NOTES_TAB);
		rebateplannotesTab.resetAddMode();
		rebateplannotesTab.removeAndDisablingComponents(false);

		rebateplannotesTab.setNotesTabEnable(false);
	}

	private void resetRuleDetailsInformation() {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsOperator").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsValue").loadDateValue(null);
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_OPERATOR_COMPLEX)
				.setEnable(false);
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_VALUE_COMPLEX)
				.setEnable(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsOperatorType").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsAdjustmentOperator1").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsAdjustmentValue").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsOperatorType2").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsAdjustmentOperator2").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsAdjustmentValue2").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsOperatorType3").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsAdjustmentOperator3").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsAdjustmentValue3").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsAdjustmentOperator4").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsAdjustmentValue4").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsOperatorType4").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsOperatorType5").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsAdjustmentOperator5").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanCalculationsAdjustmentValue5").loadDateValue(null);

	}
}
