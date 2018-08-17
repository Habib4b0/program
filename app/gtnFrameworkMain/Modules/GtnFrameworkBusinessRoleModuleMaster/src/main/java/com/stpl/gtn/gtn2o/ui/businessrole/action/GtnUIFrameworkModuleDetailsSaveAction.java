package com.stpl.gtn.gtn2o.ui.businessrole.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsModuleAuthorizationBean;
import com.stpl.gtn.gtn2o.ws.authorization.constants.GtnWsModuleAuthorizationConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.authorization.GtnWsModuleAuthorizationGeneralRequest;

public class GtnUIFrameworkModuleDetailsSaveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkBusinessRoleMasterSave.class);

	@Override

	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Enters Module Update Save");
		List<GtnWsModuleAuthorizationBean> subModulebeanList = new ArrayList<>();
		List<GtnWsRecordBean> componentBeanList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("brmmModuleResultTable", componentId).getItemsFromDataTable();
		for (GtnWsRecordBean gtnWsRecordBean : componentBeanList) {
			GtnWsModuleAuthorizationBean subModuleBean = new GtnWsModuleAuthorizationBean();
			subModuleBean.setModuleSubModuleSid(
					(int) gtnWsRecordBean.getPropertyValueByIndex(gtnWsRecordBean.getProperties().size() - 1));
			subModuleBean.setUpdateModuleFlag(gtnWsRecordBean.getBooleanProperty("componentUpdate"));
			subModulebeanList.add(subModuleBean);
		}
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsModuleAuthorizationGeneralRequest gtnWsModuleSecurityGeneralRequest = new GtnWsModuleAuthorizationGeneralRequest();
		gtnWsModuleSecurityGeneralRequest.setGtnWsModuleSecuritySaveBeanList(subModulebeanList);
		GtnWsGeneralRequest gtnSecurityGeneralRequest = new GtnWsGeneralRequest();
		gtnSecurityGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		serviceRequest.setGtnWsGeneralRequest(gtnSecurityGeneralRequest);
		serviceRequest.setGtnWsModuleAuthorizationGeneralRequest(gtnWsModuleSecurityGeneralRequest);
		wsclient.callGtnWebServiceUrl(
				"/" + GtnWsModuleAuthorizationConstants.GTN_SECURITY_SERVICE_URI + "/"
						+ GtnWsModuleAuthorizationConstants.UPDATE_MODULE_SUBMODULE_UPDATE_FLAG,
				serviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		logger.debug("Module Update Saved Successfully");
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
