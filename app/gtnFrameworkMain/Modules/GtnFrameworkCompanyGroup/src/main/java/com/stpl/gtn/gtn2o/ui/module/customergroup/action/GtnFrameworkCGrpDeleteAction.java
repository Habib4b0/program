package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGroupBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.companygroup.constants.GtnWsCompanyGrpContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.companygroup.GtnCompanyGroupRequest;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkCGrpDeleteAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCGrpDeleteAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkCompanyGrpDeleteAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnUIFrameworkWebserviceRequest companyGrpDeleteWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
			GtnUIFrameworkGlobalUI.addSessionProperty("companyGrpSid", 0);
			GtnUIFrameworkGlobalUI.addSessionProperty("versionId", 1);

			GtnWsGeneralRequest companyGrpDeleteGeneralWSRequest = new GtnWsGeneralRequest();
			companyGrpDeleteGeneralWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
			companyGrpDeleteGeneralWSRequest
					.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			companyGrpDeleteWebserviceRequest.setGtnWsGeneralRequest(companyGrpDeleteGeneralWSRequest);

			GtnWsRecordBean companyGrpDeleteWsRecordBean = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("cGrpsearchResultTable").getValueFromPagedDataTable();
			if (companyGrpDeleteWsRecordBean != null) {
				Integer systemId = (Integer) companyGrpDeleteWsRecordBean.getPropertyValueByIndex(7);
				if (systemId != null) {
					GtnCompanyGroupBean bean = new GtnCompanyGroupBean();
					GtnCompanyGrpInformationBean infoBean = new GtnCompanyGrpInformationBean();
					infoBean.setCompanyGrpSid(systemId);
					bean.setGtnCompanyGrpInformationBean(infoBean);
					GtnCompanyGroupRequest cGrpRequest = new GtnCompanyGroupRequest();
					cGrpRequest.setGtnCompanyGroupBean(bean);
					companyGrpDeleteWebserviceRequest.setGtnCompanyGroupRequest(cGrpRequest);
				}
			}
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SERVICE
							+ GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_DELETE_SERVICE,
					companyGrpDeleteWebserviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpsearchResultTable")
					.getLogicFromPagedDataTable();

			logic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), Boolean.TRUE);

		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
