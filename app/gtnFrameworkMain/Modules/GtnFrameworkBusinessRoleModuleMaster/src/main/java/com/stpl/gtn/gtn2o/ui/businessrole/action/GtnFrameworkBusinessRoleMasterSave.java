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

public class GtnFrameworkBusinessRoleMasterSave implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkBusinessRoleMasterSave.class);

	@Override

	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.info("Enters Component role details Save");
		List<GtnWsModuleAuthorizationBean> saveList = new ArrayList<>();
		int roleSid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("businessRoleName", componentId)
				.getIntegerFromField();
		int moduleSid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("subModuleName", componentId)
				.getIntegerFromField();
		List<GtnWsRecordBean> componentBeanList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("brmmResultTable", componentId).getItemsFromDataTable();
		getSaveDetails(saveList, componentBeanList, roleSid, moduleSid, false);
		List<GtnWsRecordBean> tablePropertyBeanList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("brmmPropertyResultTable", componentId).getItemsFromDataTable();
		getSaveDetails(saveList, tablePropertyBeanList, roleSid, moduleSid, true);

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsModuleAuthorizationGeneralRequest gtnWsModuleSecurityGeneralRequest = new GtnWsModuleAuthorizationGeneralRequest();
		GtnWsGeneralRequest gtnSecurityGeneralRequest = new GtnWsGeneralRequest();
		gtnSecurityGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		gtnWsModuleSecurityGeneralRequest.setGtnWsModuleSecuritySaveBeanList(saveList);
		serviceRequest.setGtnWsGeneralRequest(gtnSecurityGeneralRequest);
		serviceRequest.setGtnWsModuleAuthorizationGeneralRequest(gtnWsModuleSecurityGeneralRequest);
		wsclient.callGtnWebServiceUrl(
				"/" + GtnWsModuleAuthorizationConstants.GTN_SECURITY_SERVICE_URI + "/"
						+ GtnWsModuleAuthorizationConstants.GTN_SECURITY_SAVE_SERVICE_URI,
				serviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		logger.info("Component role details Saved Successfully");
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void getSaveDetails(List<GtnWsModuleAuthorizationBean> saveList, List<GtnWsRecordBean> componentBeanList,
			int roleSid, int moduleSid, boolean isTableProperty) {
		for (GtnWsRecordBean gtnWsRecordBean : componentBeanList) {
			GtnWsModuleAuthorizationBean saveBean = new GtnWsModuleAuthorizationBean();
			saveBean.setGtnComponentSid(
					(int) gtnWsRecordBean.getProperties().get(gtnWsRecordBean.getProperties().size() - 2));
			saveBean.setGtnModuleComponentRoleDetailsSid(
					(int) gtnWsRecordBean.getProperties().get(gtnWsRecordBean.getProperties().size() - 1));
			saveBean.setGtnRoleMasterSid(roleSid);
			saveBean.setGtnModuleMasterSid(moduleSid);
			saveBean.setIsEditable(gtnWsRecordBean.getBooleanProperty("editable"));
			saveBean.setIsVisible(gtnWsRecordBean.getBooleanProperty("visible"));
			saveBean.setIsTableProperty(isTableProperty);
			saveList.add(saveBean);
		}
	}
}
