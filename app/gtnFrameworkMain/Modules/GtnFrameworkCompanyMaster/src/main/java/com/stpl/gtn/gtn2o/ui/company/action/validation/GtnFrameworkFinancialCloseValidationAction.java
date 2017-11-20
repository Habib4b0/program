/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action.validation;

import java.util.Arrays;
import java.util.List;

import org.asi.container.ExtContainer;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkFinancialCloseValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkFinancialCloseValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Entering inside configureParams");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> attachParameter = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseResultTable")
				.getLogicFromPagedDataTable();
		@SuppressWarnings("unchecked")
		ExtContainer<GtnWsRecordBean> container = (ExtContainer<GtnWsRecordBean>) logic.getContainerDataSource();
		String closeOrOpen = String.valueOf(attachParameter.get(1));

		Integer userId = Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser());
		Integer sessionId = Integer.valueOf(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));

		String mode = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseMode").getCaptionFromComboBox();
		String month = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseMonth").getCaptionFromComboBox();
		String businessDay = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseBusinessDay")
				.getCaptionFromComboBox();
		String hour = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseBusinessHour")
				.getCaptionFromComboBox();
		String minute = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseMinute").getCaptionFromComboBox();
		String calendar = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseCalendar")
				.getCaptionFromComboBox();
		String year = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseYear").getCaptionFromComboBox();
		validationForAutoAndManual(mode, month, year, componentId, Arrays.asList(businessDay, hour, minute, calendar));

		if ("open".equalsIgnoreCase(closeOrOpen) && container.getItemIds().isEmpty()) {

			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			Object financialOpen = GtnFrameworkCompanyStringContants.GTN_FINANCIAL_CLOSE_OPEN;
			alertActionConfig.setActionParameterList(
					Arrays.asList(financialOpen, GtnFrameworkCompanyStringContants.GTN_FINANCIAL_CLOSE_OPEN_MSG));

			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkSkipActionException(GtnFrameworkCompanyStringContants.GTN_FINANCIAL_CLOSE_OPEN);

		}

		if ("Manual".equals(mode)) {
			validationForOpenOrClose(closeOrOpen, userId, sessionId, month, year, componentId);
		}

	}

	public void validationForAutoAndManual(String mode, String month, String year, String componentId,
			List<String> autoModeComponents) throws GtnFrameworkGeneralException {
		if (mode.equals(GtnFrameworkCompanyStringContants.STRING_EMPTY) ||
		/* If Mode is Auto Mandatory Check */
				("Auto".equals(mode) && isListContainsEmptyElements(autoModeComponents)) ||
				/* If Mode is Manual Mandatory Check */
				(mode.equals(GtnFrameworkCompanyStringContants.MANUAL)
						&& (month.equals(GtnFrameworkCompanyStringContants.STRING_EMPTY)
								|| year.equals(GtnFrameworkCompanyStringContants.STRING_EMPTY)))) {

			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			Object financialCloseMandatory = GtnFrameworkCompanyStringContants.GTN_FINANCIAL_CLOSE_MANDATORY;
			alertActionConfig.setActionParameterList(
					Arrays.asList(financialCloseMandatory, GtnFrameworkCompanyStringContants.GTN_FINANCIAL_CLOSE_MANDATORY_MSG));

			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkSkipActionException(GtnFrameworkCompanyStringContants.GTN_FINANCIAL_CLOSE_MANDATORY);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void validationForOpenOrClose(String openOrClose, Integer userId, Integer sessionId, String periodmonth,
			String periodYear, String componentId) throws GtnFrameworkGeneralException {
		Integer periodYearInt = Integer.valueOf(periodYear);
		Object[] saveData = { periodYearInt, periodmonth, openOrClose, userId, sessionId };

		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnCMasterRequest companyMasterRequest = new GtnCMasterRequest();

		Object[] gtnCMasterFinancialCloseBean;
		companyMasterRequest.setSaveData(saveData);
		gtnRequest.setGtnCMasterRequest(companyMasterRequest);

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
						+ GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE_VALIDATION,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		gtnCMasterFinancialCloseBean = response.getOutBountData();

		if ((gtnCMasterFinancialCloseBean != null)
				&& ((String.valueOf(gtnCMasterFinancialCloseBean[0])).equalsIgnoreCase(openOrClose)
						|| (("open".equalsIgnoreCase(openOrClose))
								&& ("false".equals(String.valueOf(gtnCMasterFinancialCloseBean[0])))))) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			Object closeMsg = GtnFrameworkCompanyStringContants.GTN_FINANCIAL_CLOSE_CLOSE_MSG + openOrClose;
			alertActionConfig.setActionParameterList(Arrays.asList(openOrClose, closeMsg));

			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkSkipActionException(GtnFrameworkCompanyStringContants.GTN_FINANCIAL_CLOSE_CLOSE_MSG);

		}
	}

	boolean isListContainsEmptyElements(List<String> list) {
		for (String string : list) {
			if (string == null || string.trim().isEmpty()) {
				return true;
			}
		}
		return false;

	}

}
