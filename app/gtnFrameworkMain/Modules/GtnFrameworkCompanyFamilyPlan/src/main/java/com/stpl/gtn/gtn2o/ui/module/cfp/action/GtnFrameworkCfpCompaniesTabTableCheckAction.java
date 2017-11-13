package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlan;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cfprequest.GtnWsCfpRequest;

public class GtnFrameworkCfpCompaniesTabTableCheckAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpCompaniesTabTableCheckAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.debug("inside GtnFrameworkCfpCompaniesTabTableCheckAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(false);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		try {
			generalWSRequest.setUserId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("userId")));
			generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			request.setGtnWsGeneralRequest(generalWSRequest);
			
			GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI	.getVaadinBaseComponent("cfpCompaniesTabResultDataTable");
			Object value = tableBaseComponent
					.getTableColumnCheckboxValue(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
			
			GtnCFamilyPlan fp = new GtnCFamilyPlan();
			GtnCFamilyPlanCommonUpdateBean bean = new GtnCFamilyPlanCommonUpdateBean();
			fp.setUpdateBean(bean);
			bean.setColumnName(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
			bean.setClassType("java.lang.Boolean");
			bean.setValue(value);
			GtnWsCfpRequest cfpRequest = new GtnWsCfpRequest();
			cfpRequest.setGtnCFamilyPlan(fp);
			request.setGtnWsCfpRequest(cfpRequest);
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl("/GtnWsCompanyFamilyPlan/companiesTabUpdateField",
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			
			GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(false);
			for (GtnWsRecordBean record : tableBaseComponent.getItemsFromDataTable()) {
				tableBaseComponent.setTableColumnValue(record, GtnFrameworkCommonConstants.CHECK_RECORD_ID, value);
			}
			GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(true);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		} finally {
			GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(true);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
