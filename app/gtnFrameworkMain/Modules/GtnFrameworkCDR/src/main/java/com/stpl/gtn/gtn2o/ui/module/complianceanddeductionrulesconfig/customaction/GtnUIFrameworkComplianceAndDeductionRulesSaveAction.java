package com.stpl.gtn.gtn2o.ui.module.complianceanddeductionrulesconfig.customaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.GtnWsCDRRuleDetailBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.GtnWsCDRRuleInfoBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.compliancededuction.GtnWsComplianceGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUIFrameworkComplianceAndDeductionRulesSaveAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		saveToDb();
	}

	private int getSystemId() throws GtnFrameworkValidationFailedException {
		GtnWsRecordBean GtnWsRecordBean = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cDRsearchResultTable")
				.getValueFromPagedDataTable();
		if (GtnWsRecordBean == null) {
			return 0;
		}
		return Integer.valueOf(String.valueOf(GtnWsRecordBean.getProperties().get(8)));
	}

	@SuppressWarnings("unchecked")
	private GtnUIFrameworkWebserviceResponse saveToDb() throws GtnFrameworkGeneralException {

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		try {
			int sysId = getSystemId();
			GtnWsCDRRuleInfoBean ruleInfoBean = new GtnWsCDRRuleInfoBean();
			ruleInfoBean.setSystemId(sysId);
			List<GtnWsCDRRuleDetailBean> ruleDetailBeanList = new ArrayList<>();
			List<NotesTabBean> noteBeanList = new ArrayList<>();

			loadRuleInfo(ruleInfoBean);

			
			ruleInfoBean.setNoteBeanList(noteBeanList);

			List<GtnWsRecordBean> ruleDetailsList = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("ruleDetailsattachResultTable").getItemsFromDataTable();
			loadRuleDetails(ruleDetailBeanList, ruleDetailsList);
			ruleInfoBean.setRuleDetailBeanList(ruleDetailBeanList);
			List<Object> notes = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab").getNotesTabValue();

			ruleInfoBean.setInternalNotes((String) notes.get(0));
			List<NotesDTO> notesDTOs = (List<NotesDTO>) notes.get(1);

			loadNotesTab(noteBeanList, notesDTOs);
			GtnWsComplianceGeneralRequest gtnWsComplianceGeneralRequest = new GtnWsComplianceGeneralRequest();
			gtnWsComplianceGeneralRequest.setRuleInfoBean(ruleInfoBean);
			request.setGtnWsComplianceGeneralRequest(gtnWsComplianceGeneralRequest);

			return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					"/" + GtnWsCDRContants.CDR_SERVICE + GtnWsCDRContants.SAVE_SERVICE, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			
					
		} catch (GtnFrameworkValidationFailedException systemExcption) {
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.SAVE_ERROR, systemExcption);
		}

	}

	private void loadRuleInfo(final GtnWsCDRRuleInfoBean ruleInfoBean) throws GtnFrameworkGeneralException {
		try {
			ruleInfoBean.setRuleType(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ruleInformationRuleType").getIntegerFromField());
			ruleInfoBean.setRuleCategory(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ruleInformationRuleCategory").getIntegerFromField());
			ruleInfoBean.setRuleName(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ruleInformationRuleName").getStringFromField());
			ruleInfoBean.setRuleNo(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ruleInformationRuleNo").getStringFromField());
			ruleInfoBean.setUserId(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
		} catch (GtnFrameworkValidationFailedException systemExcption) {
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.SAVE_ERROR, systemExcption);
		}

	}

	private void loadRuleDetails(final List<GtnWsCDRRuleDetailBean> ruleDetailBeanList,
			final List<GtnWsRecordBean> ruleDetailsList) throws GtnFrameworkGeneralException {
		try {
			GtnWsCDRRuleDetailBean ruleDetailBean;
			for (GtnWsRecordBean ruleDetail : ruleDetailsList) {
				ruleDetailBean = new GtnWsCDRRuleDetailBean();

				ruleDetailBean.setLineType(getValue(0, ruleDetail));
				ruleDetailBean.setItemGMSAssociation(getValue(1, ruleDetail));
				ruleDetailBean.setKeyword(getValue(2, ruleDetail));
				ruleDetailBean.setOperator(getValue(3, ruleDetail));
				ruleDetailBean.setValue(getValue(4, ruleDetail));
				ruleDetailBean.setComparision(getValue(5, ruleDetail));
				ruleDetailBean.setLogicaloperaor(getValue(6, ruleDetail));

				ruleDetailBeanList.add(ruleDetailBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.SAVE_ERROR, systemExcption);
		}

	}

	private void loadNotesTab(List<NotesTabBean> noteBeanList, List<NotesDTO> notesDTOs)
			throws GtnFrameworkGeneralException {
		try {
			NotesTabBean notesBean;
			for (NotesDTO note : notesDTOs) {
				notesBean = new NotesTabBean();
				notesBean.setMasterTableName("CDR_MODEL");
				notesBean.setFilePath(note.getDocumentFullPath());
				notesBean.setCreatedBy(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
				notesBean.setCreatedDate(new Date());

				noteBeanList.add(notesBean);
			}
		} catch (NumberFormatException systemExcption) {

			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.SAVE_ERROR, systemExcption);
		}

	}

	private int getValue(int index, GtnWsRecordBean ruleDetail) {
		return (ruleDetail.getAdditionalProperties().get(index) != null
				&& !String.valueOf(ruleDetail.getAdditionalProperties().get(index)).equals("-Select One-"))
						? Integer.parseInt(ruleDetail.getAdditionalProperties().get(index).toString()) : 0;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkComplianceAndDeductionRulesSaveAction();
	}
	

}
