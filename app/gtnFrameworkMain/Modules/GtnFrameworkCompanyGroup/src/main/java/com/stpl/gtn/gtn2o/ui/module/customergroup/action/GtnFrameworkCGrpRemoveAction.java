package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.customergroup.constants.GtnFrameworkCGrpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpDataBean;
import com.stpl.gtn.gtn2o.ws.companygroup.constants.GtnWsCompanyGrpContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.companygroup.GtnCompanyGroupRequest;

public class GtnFrameworkCGrpRemoveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCGrpRemoveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkCGrpRemoveAction");

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {
			Set<GtnWsRecordBean> dtoSet = (Set<GtnWsRecordBean>) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("cGrpSelectedResultTable").getValuesFromPagedDataTable();
			if (dtoSet == null || dtoSet.isEmpty()) {
				GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
				actionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
				Object customerGroupValidationAddMsg = GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_ADD_MSG;
				actionConfig.setActionParameterList(Arrays.asList(customerGroupValidationAddMsg,
						GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_REMOVE_MSG_BODY));
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);
				return;
			}
			List<GtnCompanyGrpDataBean> beanList = new ArrayList<>();
			for (GtnWsRecordBean GtnWsRecordBean : dtoSet) {
				GtnCompanyGrpDataBean bean = new GtnCompanyGrpDataBean();
				bean.setCompanyMasterSid(Integer.valueOf(GtnWsRecordBean.getPropertyValueByIndex(28) == null ? "0"
						: GtnWsRecordBean.getPropertyValueByIndex(28).toString()));
				beanList.add(bean);
			}
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnWsGeneralRequest gRequest = new GtnWsGeneralRequest();
			gRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
			gRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			request.setGtnWsGeneralRequest(gRequest);
			GtnCompanyGroupRequest cGrpRequest = new GtnCompanyGroupRequest();
			cGrpRequest.setGtnCompanyGrpDataBeanList(beanList);
			request.setGtnCompanyGroupRequest(cGrpRequest);
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SERVICE
							+ GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_REMOVE_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("cGrpSelectedResultTable").getLogicFromPagedDataTable();
			logic.startSearchProcess(null, Boolean.TRUE);

		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
