/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;

/**
 *
 * @author Manikanda.Prabu
 */
public class GtnUIFrameWorkCompanyMasterFinancialResetAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkCompanyMasterFinancialResetAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Entering inside configureParams");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		Calendar mCalendar = Calendar.getInstance();
		String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		List<String> searchWhereCondition = new ArrayList<>();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseMode", componentId)
				.loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseMonth", componentId).loadFieldValue(month);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseBusinessDay", componentId)
				.loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseBusinessHour", componentId)
				.loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseMinute", componentId)
				.loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseCalendar", componentId)
				.loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseYear", componentId)
				.loadComboBoxComponentValue(mCalendar.get(Calendar.YEAR));

		if (!"UPDATE".equalsIgnoreCase(
				GtnUIFrameworkGlobalUI.getVaadinComponent("companyMasterAddSaveButton").getCaption())) {
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnCMasterRequest companyMasterRequest = new GtnCMasterRequest();
			Integer sessionid = Integer.valueOf(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
			Object[] saveData = { Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser()), sessionid };
			companyMasterRequest.setSaveData(saveData);
			request.setGtnCMasterRequest(companyMasterRequest);

			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
							+ GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_TEMP_DELETE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("financialCloseResultTable").getLogicFromPagedDataTable();
			logic.startSearchProcess(null, null, false);
		} else {
			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("financialCloseResultTable").getLogicFromPagedDataTable();
			searchWhereCondition.add("userId");
			searchWhereCondition.add("SessionId");
			logic.startSearchProcess(searchWhereCondition, true);

		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
