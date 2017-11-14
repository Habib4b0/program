package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import java.util.Arrays;

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
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkCGrpEditAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCGrpEditAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI.getValueFromTable(gtnUIFrameWorkActionConfig);
		if (gtnWsRecordBean == null) {
			return;
		}
		GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", true);
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();

		GtnCompanyGrpInformationBean cGrpInfoBean = new GtnCompanyGrpInformationBean();

		GtnCompanyGroupBean cGrpBean = new GtnCompanyGroupBean();
		cGrpBean.setGtnCompanyGrpInformationBean(cGrpInfoBean);
		int systemId = (Integer) gtnWsRecordBean.getPropertyValueByIndex(7);
		cGrpInfoBean.setCompanyGrpSid(systemId);
		GtnUIFrameworkGlobalUI.addSessionProperty("companyGrpSid", systemId);
		GtnCompanyGroupRequest cGrpRequest = new GtnCompanyGroupRequest();
		cGrpRequest.setGtnCompanyGroupBean(cGrpBean);
		gtnRequest.setGtnCompanyGroupRequest(cGrpRequest);
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		gtnRequest.setGtnWsGeneralRequest(generalWSRequest);
		try {
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SERVICE
							+ GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_FETCH_SERVICE,
					gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			boolean isEditable = (boolean) gtnUIFrameWorkActionConfig.getActionParameterList().get(3);
			setValueToComponents(response.getGtnWsCompanyGroupResponse().getGtnCompanyGrpInformationBean(), isEditable,
					componentId);

			GtnUIFrameworkPagedTableLogic cfpCaTabRightTablelogic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("cGrpSelectedResultTable").getLogicFromPagedDataTable();
			cfpCaTabRightTablelogic.startSearchProcess(null, Boolean.TRUE);

		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(GtnCompanyGrpInformationBean info, boolean isEditable, String sourceComponentId) {

		gtnLogger.debug("Setting values in Edit action for bean " + info.getCompanyGrpSid());

		GtnUIFrameworkBaseComponent cGrpInformationCGrpName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("cGrpInformationCGrpName", sourceComponentId);
		GtnUIFrameworkBaseComponent cGrpInformationCGrpNo = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("cGrpInformationCGrpNo", sourceComponentId);
		GtnUIFrameworkBaseComponent cGrpInformationCGrpDesc = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("cGrpInformationCGrpDesc", sourceComponentId);

		cGrpInformationCGrpName.loadFieldValue(info.getCompanyGrpName());
		cGrpInformationCGrpNo.loadFieldValue(info.getCompanyGrpNo());
		cGrpInformationCGrpDesc.loadFieldValue(info.getCompanyGrpDesc());

		GtnUIFrameworkGlobalUI.addSessionProperty("companyGrpSid", info.getCompanyGrpSid());
		GtnUIFrameworkGlobalUI.addSessionProperty("versionId", info.getVersionNo());

		cGrpInformationCGrpName.setComponentEnable(false);
		cGrpInformationCGrpNo.setComponentEnable(false);
		cGrpInformationCGrpDesc.setComponentEnable(false);

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

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpAvailablesearchResultTable").getExtCustomTable()
				.removeAllItems();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpAddSaveButton").setCaption("UPDATE");

		GtnUIFrameworkGlobalUI.setVisibleFlagForComponent(isEditable,
				Arrays.asList("cGrpCustomerSearchPanel", "cGrpsearchButtonlayout", "cGrpAvailableResultPanel",
						"availableTableActionButtonlayout", "cGrpSelectedTablegtnRemove01layout",
						"cGrpSelectedTablegtnRemoveAll01layout", "cGrpAddSaveButton", "CGrpADDResetButton"),
				sourceComponentId);

	}

}
