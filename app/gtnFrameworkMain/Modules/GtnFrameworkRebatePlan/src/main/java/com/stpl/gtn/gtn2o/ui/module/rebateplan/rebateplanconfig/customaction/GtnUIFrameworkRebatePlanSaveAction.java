package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkStringConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
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

public class GtnUIFrameworkRebatePlanSaveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private static final GtnWSLogger GTN_LOGGER = GtnWSLogger.getGTNLogger(GtnUIFrameworkRebatePlanSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		saveToDb();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanAddViewtabSheet").setSelectedTabByPostion(0);
		GtnUIFrameWorkActionConfig notificationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		String msg = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanInformationTabRebatePlanID")
				.getStringFromField();
		notificationAction.addActionParameter(
				msg + "," + GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanInformationTabRebatePlanName")
						.getStringFromField() + " has been successfully saved");
		notificationAction.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationAction);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanAddViewAAddDeleteButton").setVisible(true);
		GtnUIFrameworkGlobalUI.addSessionProperty("mode", "Edit");

	}

	private int getSystemId() {
		String mode = String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("mode"));
		int sysId = 0;
		String getCaption = GtnUIFrameworkGlobalUI.getVaadinComponent("rebatePlanAddViewAddSaveButton").getCaption();

		if (mode.equalsIgnoreCase("Edit") || (mode.equalsIgnoreCase("Copy") && getCaption.equalsIgnoreCase("UPDATE"))) {
			sysId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("systemId");
		}

		return sysId;

	}

	@SuppressWarnings("unchecked")
	private void saveToDb() throws GtnFrameworkGeneralException {
        GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		int sysId = getSystemId();
        
		GtnWsRebatePlanInfoBean rebatePlanInfoBean = new GtnWsRebatePlanInfoBean();
		rebatePlanInfoBean.setSystemId(sysId);
		rebatePlanInfoBean.setUserId(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
		List<GtnWsRebatePlanRuleDetailBean> ruleDetailBeanList = new ArrayList<>();
		List<NotesTabBean> noteBeanList = new ArrayList<>();

		loadRebatePlanInfo(rebatePlanInfoBean);

		rebatePlanInfoBean.setRebatePlanRuleDetailBean(ruleDetailBeanList);
		rebatePlanInfoBean.setNoteBeanList(noteBeanList);
		List<GtnWsRecordBean> ruleDetailsList = null;
		String resultValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanInformationTabformulaType")
				.getCaptionFromComboBox();
		if (resultValue.equals("Complex")) {
			ruleDetailsList = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ruleDetailsattachResultTableComplex")
					.getItemsFromDataTable();
			loadRuleDetailsComplexFormula(ruleDetailBeanList, ruleDetailsList);
		} else {
			ruleDetailsList = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ruleDetailsattachResultTable")
					.getItemsFromDataTable();
			loadRuleDetails(ruleDetailBeanList, ruleDetailsList);
		}

		List<Object> notes = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab").getNotesTabValue();

		rebatePlanInfoBean.setInternalNotes((String) notes.get(0));
		List<NotesDTO> notesDTOs = (List<NotesDTO>) notes.get(1);

		loadNotesTab(noteBeanList, notesDTOs);
		GtnWsRebatePlanGeneralRequest gtnWsRebatePlanGeneralRequest = new GtnWsRebatePlanGeneralRequest();
		gtnWsRebatePlanGeneralRequest.setRebatePlanInfoBean(rebatePlanInfoBean);
		request.setGtnWsRebatePlanGeneralRequest(gtnWsRebatePlanGeneralRequest);

		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.RP_SERVICE + GtnWsCDRContants.SAVE_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		GtnWsRebatePlanGeneralResponse getGtnWsRebatePlanGeneralResponse = response.getGtnWsRebatePlanGeneralResponse();

		rebatePlanInfoBean.setSystemId(getGtnWsRebatePlanGeneralResponse.getRebatePlanInfoBean().getSystemId());
		GtnUIFrameworkGlobalUI.addSessionProperty("systemId",
				getGtnWsRebatePlanGeneralResponse.getRebatePlanInfoBean().getSystemId());

		boolean flag = getGtnWsRebatePlanGeneralResponse.getRebatePlanInfoBean().isRebatePlanIdAlreadyExist();

		if (flag) {
			throw new GtnFrameworkValidationFailedException(
					"Rebate Plan Name already exists, Please enter different Rebate Plan Name",
					GtnFrameworkCommonConstants.REBATE_PLAN_INFORMATION_TAB_REBATE_PLAN_NO);
		}
		GtnUIFrameworkActionExecutor
				.clearErrorBanner(GtnFrameworkCommonConstants.REBATE_PLAN_INFORMATION_TAB_REBATE_PLAN_NO);

	}

	private void loadRebatePlanInfo(final GtnWsRebatePlanInfoBean rebatePlanInfoBean)
			throws GtnFrameworkGeneralException {
		try {
			rebatePlanInfoBean.setRebatePlanType(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanInformationTabRebatePlanType").getIntegerFromField());
			rebatePlanInfoBean.setRebatePlanStatus(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanInformationTabRebateStatus").getIntegerFromField());
			rebatePlanInfoBean.setRebateStructure(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanCalculationsRebateStructure").getIntegerFromField());
			rebatePlanInfoBean.setRangeBasedOn(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanCalculationsRangeBasedOn").getIntegerFromField());
			rebatePlanInfoBean.setRebateBasedOn(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanCalculationsRebateBasedOn").getIntegerFromField());
			rebatePlanInfoBean.setRebatePlanId(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanInformationTabRebatePlanID").getStringFromField());
			rebatePlanInfoBean.setRebatePlanName(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanInformationTabRebatePlanName").getStringFromField());
			rebatePlanInfoBean.setRebatePlanNo(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.REBATE_PLAN_INFORMATION_TAB_REBATE_PLAN_NO)
					.getStringFromField());
			rebatePlanInfoBean.setFormulaType(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanInformationTabformulaType").getStringFromField());

			String netSalesFormula = String.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanCalculationsNetSalesFormula").getIdFromField());

			rebatePlanInfoBean.setNetSalesFormula(("".equals(netSalesFormula) || "null".equals(netSalesFormula)) ? null
					: Integer.parseInt(netSalesFormula));

			String netSaleRule = String.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanCalculationsNetSalesRule").getIdFromField());
			rebatePlanInfoBean.setNetSalesRule(
					("".equals(netSaleRule) || "null".equals(netSaleRule)) ? null : Integer.parseInt(netSaleRule));

			String rebatePlanCalculationSelfGrowthIndicator = String.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanCalculationSelfGrowthIndicator").getStringFromField()).trim();
			rebatePlanInfoBean.setSelfGrowthIndicator(("".equals(rebatePlanCalculationSelfGrowthIndicator) ? null
					: rebatePlanCalculationSelfGrowthIndicator));

			rebatePlanInfoBean.setSelfGrowthReference(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanCalculationSelfGrowthReference").getStringFromField());
			rebatePlanInfoBean.setMarketShareIndicator(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanCalculationMarketShareIndicator").getStringFromField());
			rebatePlanInfoBean.setMarketShareReference(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanCalculationMarketShareReference").getStringFromField());

			rebatePlanInfoBean.setSelfGrowthFrom(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanCalculationSelfGrowthFrom").getDateFromDateField());
			rebatePlanInfoBean.setSelfGrowthTo(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanCalculationSelfGrowthTo").getDateFromDateField());
			rebatePlanInfoBean.setMarketShareFrom(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanCalculationMarketShareFrom").getDateFromDateField());
			rebatePlanInfoBean.setMarketShareTo(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("rebatePlanCalculationMarketShareTo").getDateFromDateField());

		} catch (GtnFrameworkValidationFailedException systemExcption) {
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.SAVE_ERROR, systemExcption);
		}

	}

	private void loadRuleDetails(List<GtnWsRebatePlanRuleDetailBean> ruleDetailBeanList,
			final List<GtnWsRecordBean> ruleDetailsList) throws GtnFrameworkGeneralException {
		try {
			GtnWsRebatePlanRuleDetailBean ruleDetailBean;
			for (GtnWsRecordBean ruleDetail : ruleDetailsList) {
				ruleDetailBean = new GtnWsRebatePlanRuleDetailBean();
				ruleDetailBean.setFrom(getDoubleValue(0, ruleDetail));
				if (ruleDetail.getStringProperty(GtnFrameworkCommonConstants.TIER_TO) == null || String
						.valueOf(ruleDetail.getStringProperty(GtnFrameworkCommonConstants.TIER_TO)).isEmpty()) {
                                        ruleDetailBean.setTo(null);
				} else {
					ruleDetailBean.setTo(getDoubleValue(1, ruleDetail));
				}
				loadRuleDetailBean(ruleDetailBean, ruleDetail, ruleDetailBeanList);
			}
		} catch (Exception systemExcption) {
			GTN_LOGGER.error("Error in Rebate Plan Save Action", systemExcption);
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.SAVE_ERROR, systemExcption);
		}

	}

	private void loadRuleDetailBean(GtnWsRebatePlanRuleDetailBean ruleDetailBean, GtnWsRecordBean ruleDetail,
			List<GtnWsRebatePlanRuleDetailBean> ruleDetailBeanList) {
		ruleDetailBean.setOperator(getValue(2, ruleDetail));
		if (!ruleDetail.getAdditionalProperties().contains(GtnFrameworkStringConstants.NEW_ITEM)) {
			if ("$".equals(ruleDetail.getStringProperty("tierOperator"))) {
				ruleDetailBean.setItemPricingQualifierSid(getStringValue(3, ruleDetail));
			} else {
				ruleDetailBean.setReturnRateSid(getValue(3, ruleDetail));
			}

		} else {
			ruleDetailBean.setValueDesc(getStringValue(3, ruleDetail));
		}

		ruleDetailBeanList.add(ruleDetailBean);
	}

	private void loadRuleDetailsComplexFormula(List<GtnWsRebatePlanRuleDetailBean> ruleDetailBeanList,
			final List<GtnWsRecordBean> ruleDetailsList) throws GtnFrameworkValidationFailedException {
		GtnWsRebatePlanRuleDetailBean ruleDetailBean;

		for (GtnWsRecordBean ruleDetail : ruleDetailsList) {
			List<String[]> formulaList = new ArrayList<>();
			ruleDetailBean = new GtnWsRebatePlanRuleDetailBean();
			ruleDetailBean.setFrom(getDoubleValue(0, ruleDetail));
			if (ruleDetail.getStringProperty(GtnFrameworkCommonConstants.TIER_TO) == null
					|| String.valueOf(ruleDetail.getStringProperty(GtnFrameworkCommonConstants.TIER_TO)).isEmpty()) {
				ruleDetailBean.setTo(null);
			} else {
				ruleDetailBean.setTo(getDoubleValue(1, ruleDetail));
			}
			ruleDetailBean.setOperator(getOperatorValue(2, ruleDetail));
			ruleDetailBean.setOperatorType(getStringValueForNewItem(4, ruleDetail));
			ruleDetailBean.setOperatorType2(getStringValueForNewItem(7, ruleDetail));
			ruleDetailBean.setOperatorType3(getStringValueForNewItem(10, ruleDetail));
			ruleDetailBean.setOperatorType4(getStringValueForNewItem(13, ruleDetail));
			ruleDetailBean.setOperatorType5(getStringValueForNewItem(16, ruleDetail));
			ruleDetailBean.setAdjustmentOperator1(getStringValueForNewItem(5, ruleDetail));
			ruleDetailBean.setAdjustmentOperator2(getStringValueForNewItem(8, ruleDetail));
			ruleDetailBean.setAdjustmentOperator3(getStringValueForNewItem(11, ruleDetail));
			ruleDetailBean.setAdjustmentOperator4(getStringValueForNewItem(14, ruleDetail));
			ruleDetailBean.setAdjustmentOperator5(getStringValueForNewItem(17, ruleDetail));
			ruleDetailBean.setAdjustmentValue1itemPricingQualifier(getStringValueForNewItem(6, ruleDetail));
			ruleDetailBean.setAdjustmentValue2itemPricingQualifier(getStringValueForNewItem(9, ruleDetail));
			ruleDetailBean.setAdjustmentValue3itemPricingQualifier(getStringValueForNewItem(12, ruleDetail));
			ruleDetailBean.setAdjustmentValue4itemPricingQualifier(getStringValueForNewItem(15, ruleDetail));
			ruleDetailBean.setAdjustmentValue5itemPricingQualifier(getStringValueForNewItem(18, ruleDetail));

                        loadFormula(formulaList, ruleDetailBean, ruleDetail);
			ruleDetailBean.setItemPricingQualifierSid(getRPFormula(formulaList));
			ruleDetailBean.setFormulaForCalculation(getRPFormulaForCalculation(formulaList));
			ruleDetailBeanList.add(ruleDetailBean);
		}
                
	}

	public void loadFormula(List<String[]> formulaList, GtnWsRebatePlanRuleDetailBean ruleDetailBean,
			final GtnWsRecordBean ruleDetail) throws GtnFrameworkValidationFailedException {
		if (ruleDetailBean.getOperator() != null) {
			addDefaultValue(formulaList, ruleDetailBean, ruleDetail);

		}
		if (ruleDetailBean.getOperatorType() != null) {
			addValueFirst(formulaList, ruleDetailBean);

		}
		if (ruleDetailBean.getOperatorType2() != null) {
			addValue2(formulaList, ruleDetailBean);
		}

		if (ruleDetailBean.getOperatorType3() != null) {
			addValue3(formulaList, ruleDetailBean);
		}
		if (ruleDetailBean.getOperatorType4() != null) {
			addValue4(formulaList, ruleDetailBean);
		}
		if (ruleDetailBean.getOperatorType5() != null) {
			addValue5(formulaList, ruleDetailBean);
		}
	}
        
	private void loadNotesTab(List<NotesTabBean> noteBeanList, List<NotesDTO> notesDTOs)
			throws GtnFrameworkGeneralException {
		try {
			NotesTabBean rpNotesBean;
			for (NotesDTO note : notesDTOs) {
				rpNotesBean = new NotesTabBean();
				rpNotesBean.setMasterTableName("REBATE_PLAN_MASTER");
				rpNotesBean.setFilePath(note.getDocumentFullPath());
				rpNotesBean.setCreatedBy(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
				rpNotesBean.setCreatedDate(new Date());
				noteBeanList.add(rpNotesBean);
			}
		} catch (NumberFormatException systemExcption) {

			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.SAVE_ERROR, systemExcption);
		}

	}

	private int getOperatorValue(int index, GtnWsRecordBean ruleDetail) {
		Object mode = GtnUIFrameworkGlobalUI.getSessionProperty("mode");
		int sysId=getSystemId();
		if (mode != null && (((GtnUIFrameworkModeType.COPY).equals(mode))
				|| ((GtnUIFrameworkModeType.EDIT).equals(mode))|| sysId>0)) {
			return (ruleDetail.getAdditionalProperties().get(index) != null
					&& String.valueOf(ruleDetail.getAdditionalProperties().get(index)).equals("$")) ? 180 : 181;
		}
		return (ruleDetail.getAdditionalProperties().get(index) != null
				&& !String.valueOf(ruleDetail.getAdditionalProperties().get(index)).equals("-Select One-"))
						? Integer.parseInt(ruleDetail.getAdditionalProperties().get(index).toString()) : 0;
	}

	private int getValue(int index, GtnWsRecordBean ruleDetail) {

		return (ruleDetail.getAdditionalProperties().get(index) != null
				&& !String.valueOf(ruleDetail.getAdditionalProperties().get(index)).equals("-Select One-"))
						? Integer.parseInt(ruleDetail.getAdditionalProperties().get(index).toString()) : 0;
	}

	private Double getDoubleValue(int index, GtnWsRecordBean ruleDetail) {
		String data = String.valueOf(ruleDetail.getAdditionalProperties().get(index));
		return data.isEmpty() ? 0d : Double.parseDouble(data);
	}

	private String getStringValue(int index, GtnWsRecordBean ruleDetail) {
		String data = String.valueOf(ruleDetail.getAdditionalProperties().get(index));
		return data.isEmpty() ? "" : data;
	}

	private String getStringValueForNewItem(int index, GtnWsRecordBean ruleDetail) {
		String additionalData = String.valueOf(ruleDetail.getAdditionalProperties().get(index));
		String newItemValue = String.valueOf(ruleDetail.getAdditionalProperties().get(4));
		return GtnFrameworkStringConstants.NEW_ITEM.equals(newItemValue)
				? String.valueOf(ruleDetail.getAdditionalProperties().get(index + 1)) : additionalData;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private boolean isPercent(String value) {
		return "%".equals(value);
	}

	public String getRPFormula(List<String[]> formulaList) {
		StringBuilder finalFormula = new StringBuilder();
		for (int i = 0; i < formulaList.size(); i++) {
			String[] str = formulaList.get(i);
			finalFormula.insert(0, "(");
			for (int j = 0; j < str.length; j++) {
				if (j == 1 && j != str.length - 1) {
					finalFormula.append("(");
				}
				finalFormula.append(str[j]);
				if (j == str.length - 1) {
					finalFormula.append(")");
				}
			}
			if (i != 0) {
				finalFormula.append(")");
			}
		}
		return finalFormula.toString();
	}
        
	public String getRPFormulaForCalculation(List<String[]> formulaList) {
		StringBuilder finalFormula = new StringBuilder();
                String firstLastString="";
		for (int i = 0; i < formulaList.size(); i++) {
			String[] str = formulaList.get(i);
			finalFormula.insert(0, "(");
                        firstLastString=formulaFormation(str, finalFormula, firstLastString, i);
			if (i != 0) {
				finalFormula.append(")");
                                firstLastString=finalFormula.toString();
			}
		}
		return finalFormula.toString().replace("[]", "");
	}

    public String  formulaFormation(String[] str, StringBuilder finalFormula, String firstLastString, int i) {
        String newFormula=firstLastString;
        for (int j = 0; j < str.length; j++) {
            if (j == 1 && j != str.length - 1) {
                finalFormula.append("(");
            }
            String percentString = j == 1 ? str[j] : ("*".concat(firstLastString.concat("/100")));
            String formulaName = str[j].equals("%") ? percentString : str[j];
            formulaName = (i!=0 && formulaName.equals("$")) ?"" : formulaName;
            formulaName = operatorCheck(formulaName) ? "[".concat(formulaName).concat("]") : formulaName;
            finalFormula.append(formulaName);
            if (j == str.length - 1) {
                finalFormula.append(")");
                newFormula=finalFormula.toString();
            }
        }
        return newFormula;
    }

    public boolean operatorCheck(String formulaName) {
        return !formulaName.matches("[0-9]+") && !formulaName.matches("[$ % * \\- / +]+")&& !formulaName.startsWith("*");
    }

	public void addDefaultValue(List<String[]> formulaList, GtnWsRebatePlanRuleDetailBean ruleDetailBean,
			final GtnWsRecordBean ruleDetail) throws GtnFrameworkValidationFailedException {
		String operator = String.valueOf(ruleDetail.getProperties().get(2));
            formulaList.add(new String[] {
				isPercent(operator) ? getStringValue(3, ruleDetail)
						: String.valueOf(ruleDetail.getPropertyValueByIndex(2)),
				isPercent(operator) ? String.valueOf(ruleDetail.getPropertyValueByIndex(2))
						: getStringValue(3, ruleDetail) });
	}

	public void addValueFirst(List<String[]> formulaList, GtnWsRebatePlanRuleDetailBean ruleDetailBean) {
		if (getIsEmpty(ruleDetailBean.getOperatorType()) && getIsEmpty(ruleDetailBean.getAdjustmentOperator1())
				&& getIsEmpty(ruleDetailBean.getAdjustmentValue1itemPricingQualifier())) {
			formulaList.add(new String[] { ruleDetailBean.getOperatorType(),
					isPercent(ruleDetailBean.getAdjustmentOperator1())
							? ruleDetailBean.getAdjustmentValue1itemPricingQualifier()
							: ruleDetailBean.getAdjustmentOperator1(),
					isPercent(ruleDetailBean.getAdjustmentOperator1()) ? ruleDetailBean.getAdjustmentOperator1()
							: ruleDetailBean.getAdjustmentValue1itemPricingQualifier() });
		}
	}

	public void addValue2(List<String[]> formulaList, GtnWsRebatePlanRuleDetailBean ruleDetailBean) {
		if (getIsEmpty(ruleDetailBean.getOperatorType2()) && getIsEmpty(ruleDetailBean.getAdjustmentOperator2())
				&& getIsEmpty(ruleDetailBean.getAdjustmentValue2itemPricingQualifier())) {
			formulaList.add(new String[] { ruleDetailBean.getOperatorType2(),
					isPercent(ruleDetailBean.getAdjustmentOperator2())
							? ruleDetailBean.getAdjustmentValue2itemPricingQualifier()
							: ruleDetailBean.getAdjustmentOperator2(),
					isPercent(ruleDetailBean.getAdjustmentOperator2()) ? ruleDetailBean.getAdjustmentOperator2()
							: ruleDetailBean.getAdjustmentValue2itemPricingQualifier() });
		}
	}
        
	public void addValue3(List<String[]> formulaList, GtnWsRebatePlanRuleDetailBean ruleDetailBean) {
		if (getIsEmpty(ruleDetailBean.getOperatorType3()) && getIsEmpty(ruleDetailBean.getAdjustmentOperator3())
				&& getIsEmpty(ruleDetailBean.getAdjustmentValue3itemPricingQualifier())) {
			formulaList.add(new String[] { ruleDetailBean.getOperatorType3(),
					isPercent(ruleDetailBean.getAdjustmentOperator3())
							? ruleDetailBean.getAdjustmentValue3itemPricingQualifier()
							: ruleDetailBean.getAdjustmentOperator3(),
					isPercent(ruleDetailBean.getAdjustmentOperator3()) ? ruleDetailBean.getAdjustmentOperator3()
							: ruleDetailBean.getAdjustmentValue3itemPricingQualifier() });
		}
	}
        
	public void addValue4(List<String[]> formulaList, GtnWsRebatePlanRuleDetailBean ruleDetailBean) {
		if (getIsEmpty(ruleDetailBean.getOperatorType4()) && getIsEmpty(ruleDetailBean.getAdjustmentOperator4())
				&& getIsEmpty(ruleDetailBean.getAdjustmentValue4itemPricingQualifier())) {
			formulaList.add(new String[] { ruleDetailBean.getOperatorType4(),
					isPercent(ruleDetailBean.getAdjustmentOperator4())
							? ruleDetailBean.getAdjustmentValue4itemPricingQualifier()
							: ruleDetailBean.getAdjustmentOperator4(),
					isPercent(ruleDetailBean.getAdjustmentOperator4()) ? ruleDetailBean.getAdjustmentOperator4()
							: ruleDetailBean.getAdjustmentValue4itemPricingQualifier() });
		}
	}

	public void addValue5(List<String[]> formulaList, GtnWsRebatePlanRuleDetailBean ruleDetailBean) {
		if (getIsEmpty(ruleDetailBean.getOperatorType5()) && getIsEmpty(ruleDetailBean.getAdjustmentOperator5())
				&& getIsEmpty(ruleDetailBean.getAdjustmentValue5itemPricingQualifier())) {
			formulaList.add(new String[] { ruleDetailBean.getOperatorType5(),
					isPercent(ruleDetailBean.getAdjustmentOperator5())
							? ruleDetailBean.getAdjustmentValue5itemPricingQualifier()
							: ruleDetailBean.getAdjustmentOperator5(),
					isPercent(ruleDetailBean.getAdjustmentOperator5()) ? ruleDetailBean.getAdjustmentOperator5()
							: ruleDetailBean.getAdjustmentValue5itemPricingQualifier() });
		}
	}
        
	private boolean getIsEmpty(String value) {
		return value != null && !value.equals("null") && !value.isEmpty();

	}
}
