package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
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
public class GtnFrameworkCGrpResetAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCGrpResetAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();

		GtnCompanyGrpInformationBean cGrpInfoBean = new GtnCompanyGrpInformationBean();

		GtnCompanyGroupBean cGrpBean = new GtnCompanyGroupBean();
		cGrpBean.setGtnCompanyGrpInformationBean(cGrpInfoBean);
		Integer systemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("companyGrpSid");
		cGrpInfoBean.setCompanyGrpSid(systemId);
		if (systemId == null || systemId == 0) {
			setValueToComponents(cGrpInfoBean, true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpSelectedResultTable").getExtCustomTable()
					.removeAllItems();
		} else {

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
				setValueToComponents(response.getGtnWsCompanyGroupResponse().getGtnCompanyGrpInformationBean(), false);

				GtnUIFrameworkPagedTableLogic cfpCaTabRightTablelogic = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("cGrpSelectedResultTable").getLogicFromPagedDataTable();
				cfpCaTabRightTablelogic.startSearchProcess(null, true);

			} catch (GtnFrameworkValidationFailedException e) {
				gtnLogger.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(GtnCompanyGrpInformationBean info, boolean isAddMode) {

		gtnLogger
				.debug("Reseting for Company Group - Is Add - " + isAddMode + "for Infor - " + info.getCompanyGrpSid());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationCGrpName")
				.loadFieldValue(info.getCompanyGrpName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationCGrpNo").loadFieldValue(info.getCompanyGrpNo());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationCGrpDesc")
				.loadFieldValue(info.getCompanyGrpDesc());
		GtnUIFrameworkGlobalUI.addSessionProperty("companyGrpSid", info.getCompanyGrpSid());
		GtnUIFrameworkGlobalUI.addSessionProperty("versionId", info.getVersionNo());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationCGrpName").setComponentEnable(isAddMode);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationCGrpNo").setComponentEnable(isAddMode);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationCGrpDesc").setComponentEnable(isAddMode);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCustomerNo")
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabTradeClass").loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCustomerStatus")
				.loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabState").loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCustomerName")
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCustomerType")
				.loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCity")
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabZipcode")
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpAvailablesearchResultTable").getExtCustomTable()
				.removeAllItems();

	}
}
