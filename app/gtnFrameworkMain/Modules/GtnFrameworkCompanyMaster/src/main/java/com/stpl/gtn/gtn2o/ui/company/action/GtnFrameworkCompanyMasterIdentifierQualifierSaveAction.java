package com.stpl.gtn.gtn2o.ui.company.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkLoadDataTableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnWsCMasterQualifierBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;

public class GtnFrameworkCompanyMasterIdentifierQualifierSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnFrameworkCompanyMasterIdentifierQualifierSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("inside GtnFrameworkItemMasterIdentifierQualifierSaveAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnWsRecordBean selectedId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editattachResultTable")
				.getValueFromDataTable();

		final Integer companyQualifierSid = selectedId == null ? 0
				: (Integer) selectedId.getPropertyValueByIndex(selectedId.getProperties().size() - 2);

		final String qualifier = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editListidentifierCodeQualifier")
				.getStringFromField();
		final String qualifierName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("editListidentifierCodeQualifierName").getStringFromField();
		final String editListEffectiveDates = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editListEffectiveDates")
				.getStringFromField();

		String failedMsg = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		if (qualifier.isEmpty()) {
			failedMsg = "Enter Qualifier";
		} else if (qualifierName.isEmpty()) {
			failedMsg = "Enter Qualifier Name";
		} else if (editListEffectiveDates.isEmpty()) {
			failedMsg = "Please select Effective Dates";
		}
		if (failedMsg.length() > 0) {
			throw new GtnFrameworkValidationFailedException(failedMsg, componentId);
		}

		GtnWsCMasterQualifierBean qualifierBean = new GtnWsCMasterQualifierBean();
		qualifierBean.setCompanyQualifierSid(companyQualifierSid);
		qualifierBean.setCompanyQualifierValue(qualifier);
		qualifierBean.setCompanyQualifierName(qualifierName);
		qualifierBean.setEffectiveDates(editListEffectiveDates);
		qualifierBean.setNotes(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTextArea").getStringFromField());
		qualifierBean.setUserId(Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser()));
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnCMasterRequest cmRequest = new GtnCMasterRequest();
		cmRequest.setGtnCMasterQualifierBean(qualifierBean);
		gtnRequest.setGtnCMasterRequest(cmRequest);
		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_WS_CM_QUALIFIER_SERVICE
						+ GtnWebServiceUrlConstants.GTN_CM_QUALIFIER_SAVE_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		refreshResultTable(componentId);
		refreshComponenent();

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void refreshComponenent() {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editListidentifierCodeQualifier")
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editListidentifierCodeQualifierName")
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editListEffectiveDates").loadFieldValue("-Select one-");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTextArea")
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editListSaveButton").setCaption("SAVE");
	}

	private void refreshResultTable(String componentId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig refreshActionConfig = new GtnUIFrameWorkActionConfig();
		Object refreshConfig = "editattachResultTable";
		refreshActionConfig.setActionParameterList(Arrays.asList(refreshConfig));
		GtnUIFrameWorkLoadDataTableAction loadDataTableAction = new GtnUIFrameWorkLoadDataTableAction();
		loadDataTableAction.doAction(componentId, refreshActionConfig);
	}
}
