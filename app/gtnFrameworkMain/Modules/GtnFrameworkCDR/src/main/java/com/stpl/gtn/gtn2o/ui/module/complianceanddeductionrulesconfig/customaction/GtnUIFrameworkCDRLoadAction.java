package com.stpl.gtn.gtn2o.ui.module.complianceanddeductionrulesconfig.customaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.GtnWsCDRRuleDetailBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.GtnWsCDRRuleInfoBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.compliancededuction.GtnWsComplianceGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.compliance.GtnWsComplianceGeneralResponse;

public class GtnUIFrameworkCDRLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWsCDRRuleInfoBean ruleInfoBean;

	private List<GtnWsCDRRuleDetailBean> ruleDetailBeanList;
	private List<NotesTabBean> noteBeanList;

	private GtnUIFrameworkSetter gtnUIFrameworkSetter;
	private int systemId;

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnUIFrameworkSetter = new GtnUIFrameworkSetter();
		List<Object> inputList = gtnUIFrameWorkActionConfig.getActionParameterList();
		systemId = (Integer) inputList.get(inputList.size() - 1);
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		loadDataFromService();

		getGtnUIFrameworkSetter().loadComboBoxComponentValue("ruleInformationRuleType",
				getRuleInfoBean().getRuleType());
		getGtnUIFrameworkSetter().loadComboBoxComponentValue("ruleInformationRuleCategory",
				getRuleInfoBean().getRuleCategory());
		getGtnUIFrameworkSetter().loadFieldValue("ruleInformationRuleName", getRuleInfoBean().getRuleName());

		getGtnUIFrameworkSetter().loadFieldValue("ruleInformationRuleNo", getRuleInfoBean().getRuleNo());

		loadRuleDetailsInfo("ruleDetailsattachResultTable");
		loadNotesTab();

	}

	public GtnWsCDRRuleInfoBean getRuleInfoBean() {
		return ruleInfoBean;
	}

	public void setRuleInfoBean(GtnWsCDRRuleInfoBean ruleInfoBean) {
		this.ruleInfoBean = ruleInfoBean;
	}

	public List<GtnWsCDRRuleDetailBean> getRuleDetailBeanList() {
		return ruleDetailBeanList != null ? Collections.unmodifiableList(ruleDetailBeanList) : null;
	}

	public void setRuleDetailBeanList(List<GtnWsCDRRuleDetailBean> ruleDetailBeanList) {
		this.ruleDetailBeanList = ruleDetailBeanList != null ? Collections.unmodifiableList(ruleDetailBeanList) : null;
	}

	public List<NotesTabBean> getNoteBeanList() {
		return noteBeanList != null ? Collections.unmodifiableList(noteBeanList) : null;
	}

	public void setNoteBeanList(List<NotesTabBean> noteBeanList) {
		this.noteBeanList = noteBeanList != null ? Collections.unmodifiableList(noteBeanList) : null;
	}

	public GtnUIFrameworkSetter getGtnUIFrameworkSetter() {
		return gtnUIFrameworkSetter;
	}

	public void setGtnUIFrameworkSetter(GtnUIFrameworkSetter gtnUIFrameworkSetter) {
		this.gtnUIFrameworkSetter = gtnUIFrameworkSetter;
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	private void loadDataFromService() {

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		GtnWsCDRRuleInfoBean ruleInfoBeanLocal = new GtnWsCDRRuleInfoBean();

		ruleInfoBeanLocal.setSystemId(systemId);
		GtnWsComplianceGeneralRequest gtnWsComplianceGeneralRequest = new GtnWsComplianceGeneralRequest();
		gtnWsComplianceGeneralRequest.setRuleInfoBean(ruleInfoBeanLocal);
		request.setGtnWsComplianceGeneralRequest(gtnWsComplianceGeneralRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.CDR_SERVICE + GtnWsCDRContants.GET_CDR_RULE_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsComplianceGeneralResponse getGtnWsComplianceGeneralResponse = response.getGtnWsComplianceGeneralResponse();
		setRuleInfoBean(getGtnWsComplianceGeneralResponse.getRuleInfoBean());
		setRuleDetailBeanList(getGtnWsComplianceGeneralResponse.getRuleInfoBean().getRuleDetailBeanList());
		setNoteBeanList(getGtnWsComplianceGeneralResponse.getRuleInfoBean().getNoteBeanList());

	}

	private void loadNotesTab() {

		List<NotesDTO> notesDTOList = new ArrayList<>();
		List<Object> result = new ArrayList<>();
		NotesDTO attachmentDTO;
		if (getNoteBeanList() != null) {
			for (NotesTabBean notesTabBean : getNoteBeanList()) {
				attachmentDTO = new NotesDTO();
				attachmentDTO.setDocDetailsId(notesTabBean.getMasterTableSystemId());
				String filePath = notesTabBean.getFilePath();
				attachmentDTO.setDocumentFullPath(filePath);
				attachmentDTO.setDocumentName("--");
				String tempfilePath = notesTabBean.getFilePath();

				attachmentDTO.setDocumentFullPath(tempfilePath);

				attachmentDTO
						.setDocumentName(filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("_"))
								+ filePath.substring(filePath.lastIndexOf(".")));
				SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
				TimeZone central = TimeZone.getTimeZone("CST");
				format.setTimeZone(central);
				attachmentDTO.setDateAdded(format.format(notesTabBean.getCreatedDate()));
				attachmentDTO.setUserId(notesTabBean.getCreatedBy());
				attachmentDTO.setUserName(String.valueOf(notesTabBean.getCreatedByName()));
				notesDTOList.add(attachmentDTO);

			}
		}
		result.add(getRuleInfoBean().getInternalNotes());
		result.add(notesDTOList);
		getGtnUIFrameworkSetter().loadNotesTab(result);
	}

	private void loadRuleDetailsInfo(String resultTableId) {

		List<GtnWsRecordBean> resultLiist = new ArrayList<>();
		GtnWsRecordBean dto;

		for (GtnWsCDRRuleDetailBean ruleDetailsBean : getRuleDetailBeanList()) {
			dto = new GtnWsRecordBean();
			dto.setRecordHeader(getGtnUIFrameworkSetter().getRecordHeader(resultTableId));

			dto.addProperties(String.valueOf(getGtnUIFrameworkSetter().getRecordHeader(resultTableId).get(0)),
					ruleDetailsBean.getLineTypeDesc());
			dto.addProperties(String.valueOf(getGtnUIFrameworkSetter().getRecordHeader(resultTableId).get(1)),
					ruleDetailsBean.getItemGMSAssociationDesc());
			dto.addProperties(String.valueOf(getGtnUIFrameworkSetter().getRecordHeader(resultTableId).get(2)),
					ruleDetailsBean.getKeywordDesc());
			dto.addProperties(String.valueOf(getGtnUIFrameworkSetter().getRecordHeader(resultTableId).get(3)),
					ruleDetailsBean.getOperatorDesc());
			dto.addProperties(String.valueOf(getGtnUIFrameworkSetter().getRecordHeader(resultTableId).get(4)),
					ruleDetailsBean.getValueDesc());
			dto.addProperties(String.valueOf(getGtnUIFrameworkSetter().getRecordHeader(resultTableId).get(5)),
					ruleDetailsBean.getComparisionDesc().equals("-Select One-") ? ""
							: ruleDetailsBean.getComparisionDesc());
			dto.addProperties(String.valueOf(getGtnUIFrameworkSetter().getRecordHeader(resultTableId).get(6)),
					ruleDetailsBean.getLogicaloperaorDesc().equals("-Select One-") ? ""
							: ruleDetailsBean.getLogicaloperaorDesc());

			dto.addAdditionalProperty(ruleDetailsBean.getLineType());
			dto.addAdditionalProperty(ruleDetailsBean.getItemGMSAssociation());
			dto.addAdditionalProperty(ruleDetailsBean.getKeyword());
			dto.addAdditionalProperty(ruleDetailsBean.getOperator());
			dto.addAdditionalProperty(ruleDetailsBean.getValue());
			dto.addAdditionalProperty(ruleDetailsBean.getComparision());
			dto.addAdditionalProperty(ruleDetailsBean.getLogicaloperaor());

			resultLiist.add(dto);
		}
		getGtnUIFrameworkSetter().loadContainer(resultTableId, resultLiist);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkCDRLoadAction();
	}

}
