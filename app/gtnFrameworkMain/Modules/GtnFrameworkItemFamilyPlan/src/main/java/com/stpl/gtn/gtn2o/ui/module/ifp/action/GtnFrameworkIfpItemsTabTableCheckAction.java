package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkIfpItemsTabTableCheckAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpItemsTabTableCheckAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkIfpItemsTabTableCheckAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
		columnCheckLogic(gtnUIFrameWorkActionConfig, componentId);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	void columnCheckLogic(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String componentId) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		try {
			generalWSRequest.setUserId(String
					.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID)));
			generalWSRequest.setSessionId(String
					.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)));
			request.setGtnWsGeneralRequest(generalWSRequest);
			GtnIFamilyPlanBean fp = new GtnIFamilyPlanBean();
			GtnIFamilyPlanCommonUpdateBean bean = new GtnIFamilyPlanCommonUpdateBean();
			fp.setUpdateBean(bean);
			bean.setColumnName(GtnFrameworkIfpStringContants.CHECK_RECORD_ID);
			bean.setClassType(Boolean.class.getName());
			bean.setValue(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getExtPagedTable()
					.getColumnCheckBox(GtnFrameworkIfpStringContants.CHECK_RECORD_ID));
			GtnWsIfpRequest cfpRequest = new GtnWsIfpRequest();
			cfpRequest.setGtnIFamilyPlan(fp);
			request.setGtnWsIfpRequest(cfpRequest);
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
							+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANIES_TAB_COLUMN_UPDATE_FIELD_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
					.getLogicFromPagedDataTable();
			logic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), true);

		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error(e.getMessage(), e);
		} finally {
			GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(true);
		}
	}

}
