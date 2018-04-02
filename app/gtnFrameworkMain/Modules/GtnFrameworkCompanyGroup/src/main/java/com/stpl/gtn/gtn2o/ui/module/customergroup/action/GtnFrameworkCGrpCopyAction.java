package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGroupBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.companygroup.constants.GtnWsCompanyGrpContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.companygroup.GtnCompanyGroupRequest;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkCGrpCopyAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCGrpCopyAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("In GtnFrameworkCGrpCopyAction");
		String tableId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		GtnWsRecordBean gtnWsRecordBeanCopy = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId)
				.getValueFromPagedDataTable();
		if (gtnWsRecordBeanCopy == null) {
			return;
		}
		GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", Boolean.TRUE);
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();

		GtnCompanyGrpInformationBean cGrpInfoBean = new GtnCompanyGrpInformationBean();

		GtnCompanyGroupBean cGrpBeanCopy = new GtnCompanyGroupBean();
		cGrpBeanCopy.setGtnCompanyGrpInformationBean(cGrpInfoBean);
		int systemId = (Integer) gtnWsRecordBeanCopy.getPropertyValueByIndex(7);
		cGrpInfoBean.setCompanyGrpSid(systemId);
                cGrpInfoBean.setVersionNo((Integer) gtnWsRecordBeanCopy.getPropertyValueByIndex(3));
		GtnCompanyGroupRequest cGrpRequest = new GtnCompanyGroupRequest();
		cGrpRequest.setGtnCompanyGroupBean(cGrpBeanCopy);
		gtnRequest.setGtnCompanyGroupRequest(cGrpRequest);
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		gtnRequest.setGtnWsGeneralRequest(generalWSRequest);
		try {
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SERVICE
							+ GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_COPY_SERVICE,
					gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			boolean isEditable = (boolean) gtnUIFrameWorkActionConfig.getActionParameterList().get(3);

			setValueToComponents(isEditable, componentId);

			GtnUIFrameworkPagedTableLogic cGrpSelectedResultTableLogic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("cGrpSelectedResultTable").getLogicFromPagedDataTable();
			cGrpSelectedResultTableLogic.startSearchProcess(null, true);

			GtnUIFrameworkGlobalUI.addSessionProperty("companyGrpSid", 0);
			GtnUIFrameworkGlobalUI.addSessionProperty("versionId", 1);
		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(boolean isEditable, String sourceComponentId)
			throws GtnFrameworkValidationFailedException {

		GtnUIFrameworkBaseComponent cGrpInformationCGrpName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("cGrpInformationCGrpName", sourceComponentId);
		GtnUIFrameworkBaseComponent cGrpInformationCGrpNo = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("cGrpInformationCGrpNo", sourceComponentId);
		GtnUIFrameworkBaseComponent cGrpInformationCGrpDesc = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("cGrpInformationCGrpDesc", sourceComponentId);

		cGrpInformationCGrpName.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		cGrpInformationCGrpNo.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		cGrpInformationCGrpDesc.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);

		cGrpInformationCGrpName.setComponentEnable(true);
		cGrpInformationCGrpNo.setComponentEnable(true);
		cGrpInformationCGrpDesc.setComponentEnable(true);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCustomerNo", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabTradeClass", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCustomerStatus", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabState", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCustomerName", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCustomerType", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCity", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabZipcode", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpAddSaveButton").setCaption("SAVE");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpAvailablesearchResultTable").getExtPagedTable()
				.removeAllItems();

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpCustomerSearchPanel", sourceComponentId)
				.setComponentVisible(isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpsearchButtonlayout", sourceComponentId)
				.setComponentVisible(isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpAvailableResultPanel", sourceComponentId)
				.setComponentVisible(isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("availableTableActionButtonlayout", sourceComponentId)
				.setComponentVisible(isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpSelectedTableActionButtonlayout", sourceComponentId)
				.setComponentVisible(isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpSelectedTablegtnRemove01layout", sourceComponentId)
				.setComponentVisible(isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpSelectedTablegtnRemoveAll01layout", sourceComponentId)
				.setComponentVisible(isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpAddSaveButton", sourceComponentId)
				.setComponentVisible(isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CGrpADDResetButton", sourceComponentId)
				.setComponentVisible(isEditable);

	}
}
