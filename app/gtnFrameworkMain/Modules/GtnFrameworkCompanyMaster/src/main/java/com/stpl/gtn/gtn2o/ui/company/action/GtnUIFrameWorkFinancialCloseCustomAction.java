/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterFinancialCloseBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

public class GtnUIFrameWorkFinancialCloseCustomAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkFinancialCloseCustomAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Entering inside configureParams");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		List<String> searchWhereCondition = new ArrayList<>();

		Integer sessionid = Integer.valueOf(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		GtnCMasterRequest companyMasterRequest = new GtnCMasterRequest();

		List<Object> attachParameter = gtnUIFrameWorkActionConfig.getActionParameterList();
		String resultTableId = (String) attachParameter.get(1);
		String closeOrOpen = (String) attachParameter.get(GtnWsNumericConstants.TWO);
		GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId)
				.getLogicFromPagedDataTable();
		String year = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseYear").getCaptionFromComboBox();
		String month = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseMonth").getCaptionFromComboBox();

		String manualOrAuto = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseMode")
				.getCaptionFromComboBox();
		if ("Manual".equals(manualOrAuto)) {
			String mode = String
					.valueOf(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseMode").getIntegerFromField());
			int companySid = GtnUIFrameworkGlobalUI
					.getSessionProperty(GtnFrameworkCompanyStringContants.COMPANY_MASTER_SID).equals("0") ? 0
							: Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
									.getSessionProperty(GtnFrameworkCompanyStringContants.COMPANY_MASTER_SID)));
			Object[] saveData = { companySid, mode, 0, month + "," + year, 0, 0, 0, closeOrOpen,
					dateFormat.format(new Date()), Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser()),
					dateFormat.format(new Date()), Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser()),
					sessionid };

			companyMasterRequest.setSaveData(saveData);
			gtnRequest.setGtnCMasterRequest(companyMasterRequest);

			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
							+ GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_TEMP_INSERT,
					gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			searchWhereCondition.add("userId");
			searchWhereCondition.add("SessionId");
			logic.startSearchProcess(searchWhereCondition, true);
		} else {
			GtnCMasterFinancialCloseBean gtnCMasterFinancialCloseBean = new GtnCMasterFinancialCloseBean();
			List<GtnCMasterFinancialCloseBean> autoList = new ArrayList<>();
			gtnCMasterFinancialCloseBean.setCalenderDdlb(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseCalendar").getIntegerFromField());
			gtnCMasterFinancialCloseBean.setMinuteValue(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("financialCloseMinute").getCaptionFromComboBox());
			gtnCMasterFinancialCloseBean.setBusinessDayDdlb(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("financialCloseBusinessDay").getCaptionFromComboBox());
			gtnCMasterFinancialCloseBean.setHourDdlb(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("financialCloseBusinessHour").getCaptionFromComboBox());
			autoList.add(gtnCMasterFinancialCloseBean);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getComponentData().setCustomData(autoList);
		}

		final Notification notif = new Notification(
				"Status '" + closeOrOpen + "' updated sucessfully for the selected Year/Month",
				Notification.Type.HUMANIZED_MESSAGE);
		notif.setPosition(Position.TOP_CENTER);
		notif.setStyleName(GtnFrameworkCssConstants.MY_STYLE);
		notif.setDelayMsec(GtnWsNumericConstants.THREE_THOUSAND);
		notif.show(Page.getCurrent());

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
