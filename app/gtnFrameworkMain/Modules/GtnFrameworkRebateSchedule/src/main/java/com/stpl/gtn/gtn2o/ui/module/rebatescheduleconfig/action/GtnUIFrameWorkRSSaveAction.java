
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.rebateschedule.GtnWsRebateScheduleInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.rebateschedule.GtnWsRebateScheduleGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUIFrameWorkRSSaveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		saveToDb();
	}

	private int getSystemId() {
		String mode = String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("mode"));
		int sysId = 0;
		if (mode != null && mode.equalsIgnoreCase("Edit")) {
			sysId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SYSTEM_ID);
		}
		return sysId;

	}

	@SuppressWarnings("unchecked")
	private void saveToDb() throws GtnFrameworkGeneralException {

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		int sysId = getSystemId();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		request.setGtnWsGeneralRequest(generalWSRequest);

		GtnWsRebateScheduleInfoBean rebateScheduleInfoBean = new GtnWsRebateScheduleInfoBean();
		rebateScheduleInfoBean.setSystemId(sysId);
		List<NotesTabBean> noteBeanList = new ArrayList<>();

		loadRebateScheduleInfo(rebateScheduleInfoBean);

		List<Object> notes = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab").getNotesTabValue();

		rebateScheduleInfoBean.setInternalNotes((String) notes.get(0));
		List<NotesDTO> notesDTOs = (List<NotesDTO>) notes.get(1);

		loadRSNotesTab(noteBeanList, notesDTOs);
		loadNonEditableField(rebateScheduleInfoBean);
		rebateScheduleInfoBean.setNoteBeanList(noteBeanList);
		GtnWsRebateScheduleGeneralRequest getGtnWsRebateScheduleGeneralRequest = new GtnWsRebateScheduleGeneralRequest();
		getGtnWsRebateScheduleGeneralRequest.setRebateScheduleInfoBean(rebateScheduleInfoBean);
		request.setGtnWsRebateScheduleGeneralRequest(getGtnWsRebateScheduleGeneralRequest);

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.RS_SERVICE + GtnWsCDRContants.RS_SAVE_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		boolean flag = gtnResponse.getRebateScheduleInfoBean().isRsIdAlreadyExist();
		if (!flag && gtnResponse.getGtnWsGeneralResponse().isSucess()) {
			GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkCommonConstants.SYSTEM_ID,
					gtnResponse.getRebateScheduleInfoBean().getSystemId());
			GtnUIFrameworkBaseComponent tabsheet = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tabSheet");
			tabsheet.getAsTabSheet().setSelectedTab(0);
		} else {
			throw new GtnFrameworkValidationFailedException(
					"Rebate Schedule ID already exists. Please enter different Rebate Schedule ID",
					GtnFrameworkCommonConstants.REBATE_SCHEDULE_ID);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(GtnFrameworkCommonConstants.REBATE_SCHEDULE_ID);

	}

	private void loadRebateScheduleInfo(final GtnWsRebateScheduleInfoBean rebateScheduleInfoBean)
			throws GtnFrameworkGeneralException {

		rebateScheduleInfoBean.setRebateScheduleId(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.REBATE_SCHEDULE_ID).getStringFromField());
		rebateScheduleInfoBean.setRebateScheduleNo(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleNo1").getStringFromField());
		rebateScheduleInfoBean.setRebateScheduleName(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleName1").getStringFromField());
		rebateScheduleInfoBean.setRebateScheduleAliasId(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleAliasId1").getStringFromField());
		rebateScheduleInfoBean.setParentRebateScheduleName(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentRebateScheduleName").getStringFromField());
		rebateScheduleInfoBean.setRsTransactionRefName(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsTransactionRefName").getStringFromField());
		rebateScheduleInfoBean.setPaymentGracePeriod(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("paymentGracePeriod").getStringFromField());
		rebateScheduleInfoBean.setParentRebateScheduleSid(getIdFromComponent("parentRebateScheduleID"));
		rebateScheduleInfoBean
				.setParentRebateScheduleID(String.valueOf(rebateScheduleInfoBean.getParentRebateScheduleSid()));
		rebateScheduleInfoBean.setRsTransactionReSid(getIdFromComponent("rsTransactionRefId"));
		rebateScheduleInfoBean.setRsTransactionRefId(String.valueOf(rebateScheduleInfoBean.getRsTransactionReSid()));
		rebateScheduleInfoBean.setEvaluationRuleAssociationSid(getIdFromComponent("evaluationRuleAssociation"));
		rebateScheduleInfoBean.setCalculationRuleSid(getIdFromComponent("calculationRule"));

		rebateScheduleInfoBean.setRebateScheduleStatus(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleStatus1").getIntegerFromField());
		rebateScheduleInfoBean.setRebateScheduleType(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleType1").getIntegerFromField());
		rebateScheduleInfoBean.setRebateProgramType(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateProgramType1").getIntegerFromField());
		rebateScheduleInfoBean.setRebateScheduleCategory(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleCategory1").getIntegerFromField());
		rebateScheduleInfoBean.setRebateScheduleTradeClass(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleTradeClass").getIntegerFromField());
		rebateScheduleInfoBean.setRebateScheduleDesignation(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleDesignation").getIntegerFromField());
		rebateScheduleInfoBean.setRsUDC1(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC1").getIntegerFromField());
		rebateScheduleInfoBean.setRsUDC4(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC4").getIntegerFromField());
		rebateScheduleInfoBean.setRsUDC2(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC2").getIntegerFromField());
		rebateScheduleInfoBean.setRsUDC5(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC5").getIntegerFromField());
		rebateScheduleInfoBean.setRsUDC3(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC3").getIntegerFromField());
		rebateScheduleInfoBean.setRsUDC6(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC6").getIntegerFromField());
		rebateScheduleInfoBean.setRsDeductionInclusion(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsDeductionInclusion").getIntegerFromField());
		rebateScheduleInfoBean
				.setRsCalendar(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsCalendar").getIntegerFromField());
		rebateScheduleInfoBean.setRebateFrequency(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateFrequency1").getIntegerFromField());
		rebateScheduleInfoBean
				.setPaymentLevel(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("paymentLevel").getIntegerFromField());
		rebateScheduleInfoBean.setPaymentFrequency(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("paymentFrequency").getIntegerFromField());
		rebateScheduleInfoBean
				.setPaymentTerms(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("paymentTerms").getIntegerFromField());
		rebateScheduleInfoBean
				.setPaymentMethod(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("paymentMethod").getIntegerFromField());
		rebateScheduleInfoBean.setInterestBearingBasis(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("interestBearingBasis").getIntegerFromField());
		rebateScheduleInfoBean.setEvaluationRuleLevel(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("evaluationRuleLevel").getIntegerFromField());
		rebateScheduleInfoBean.setEvaluationRuleType(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("evaluationRuleType").getIntegerFromField());
		rebateScheduleInfoBean.setInterestBearingIndicator(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("interestBearingIndicator").getIntegerFromField());
		rebateScheduleInfoBean.setCalculationRuleLevel(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("calculationRuleLevel").getIntegerFromField());
		rebateScheduleInfoBean.setCalculationType(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("calculationType1").getIntegerFromField());
		rebateScheduleInfoBean.setCalculationLevel(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("calculationLevel").getIntegerFromField());
		rebateScheduleInfoBean.setRebateRuleType(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateRuleType").getIntegerFromField());
		rebateScheduleInfoBean.setRebateScheduleStartDate(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleStartDate").getDateFromDateField());
		rebateScheduleInfoBean.setRebateScheduleEndDate(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateScheduleEndDate").getDateFromDateField());

	}

	private void loadRSNotesTab(List<NotesTabBean> noteBeanList, List<NotesDTO> notesDTOs)
			throws GtnFrameworkGeneralException {
		try {
			NotesTabBean notesTabBean;
			for (NotesDTO note : notesDTOs) {
				notesTabBean = new NotesTabBean();
				notesTabBean.setMasterTableName("RS_MODEL");
				notesTabBean.setFilePath(note.getDocumentFullPath());
				notesTabBean.setFileName(note.getDocumentName());
				notesTabBean.setMasterTableSystemId(note.getDocDetailsId());
				notesTabBean.setCreatedBy(note.getUserId());
				notesTabBean.setCreatedDate(new Date());
				noteBeanList.add(notesTabBean);
			}
		} catch (Exception systemExcption) {

			throw new GtnFrameworkGeneralException("Save Error", systemExcption);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private Integer getIdFromComponent(String componentId) {

		GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId);
		Object value = idComponentData.getCustomData();
		if (value == null) {
			return null;
		}
		if (value instanceof GtnWsRecordBean) {
			GtnWsRecordBean dto = (GtnWsRecordBean) value;
			if (dto.getPropertyValue(GtnFrameworkCommonConstants.SYSTEM_ID) != null)
				return Integer.valueOf(String.valueOf(dto.getPropertyValue(GtnFrameworkCommonConstants.SYSTEM_ID)));

		}
		return null;
	}

	private void loadNonEditableField(GtnWsRebateScheduleInfoBean rebateScheduleInfoBean) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkRSConstants.RS_ID_GRP)
				.setPropertyValue(rebateScheduleInfoBean.getRebateScheduleId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkRSConstants.RS_NO_GRP)
				.setPropertyValue(rebateScheduleInfoBean.getRebateScheduleNo());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkRSConstants.RS_NAME_GRP)
				.setPropertyValue(rebateScheduleInfoBean.getRebateScheduleName());

	}
}
