/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.deductioncalendar.bean.GtnWsDeductionCalendarBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.deductioncalendar.GtnWsDeductionCalendarRequest;

/**
 *
 * @author Mahesh.James
 */
public class GtnUiFrameworkItemADDAddAllButtonAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData("dcItemSelectedResultTable");
		GtnUIFrameworkPagedTableLogic tableLogic = componentData.getCurrentPageTableLogic();

		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dcItemAvailablesearchResultTable").getValueFromPagedDataTable();

		GtnWsDeductionCalendarBean deductionCalendarBean = new GtnWsDeductionCalendarBean();

		if ("dcItemAvailableTablegtnAddAll01".equals(componentId)) {
			deductionCalendarBean.setIsAddAll(true);

		} else {
			List<Integer> idList = new ArrayList<>();
			idList.add(Integer.valueOf(String.valueOf(gtnWsRecordBean.getProperties().get(64))));
			deductionCalendarBean.setIdList(idList);
			deductionCalendarBean.setIsAddAll(false);
		}

		insertData(deductionCalendarBean);

		List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList = new ArrayList<>();

		GtnWebServiceSearchCriteria userIdCriteria = new GtnWebServiceSearchCriteria();
		GtnWebServiceSearchCriteria sessionIdCriteria = new GtnWebServiceSearchCriteria();
		additioanlSearchCriteriaList.add(userIdCriteria);
		additioanlSearchCriteriaList.add(sessionIdCriteria);

		userIdCriteria.setFieldId("userId");
		userIdCriteria.setExpression("EQUALS");
		userIdCriteria.setFilterValue1(GtnUIFrameworkGlobalUI.getCurrentUser().toString());

		sessionIdCriteria.setFieldId(GtnFrameworkCommonConstants.SESSION_ID);
		sessionIdCriteria.setExpression("EQUALS");
		sessionIdCriteria.setFilterValue1(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());

		tableLogic.setAdditioanlSearchCriteriaList(additioanlSearchCriteriaList);

		tableLogic.startSearchProcess(new ArrayList<String>(), true);
		GtnFrameworkPriceTabValueChangeManager.setValueChangeAllowed(true);
		if (GtnFrameworkValueChangeManager.isValueChangeAllowed()) {
			GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
			customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			customAction.setActionParameterList(Arrays.asList(new Object[] {
					"com.stpl.gtn.gtn2o.ui.module.deductioncalendarconfig.customaction.DeductionDetaitsLoadOnStartAction" }));

			GtnUIFrameWorkAction action = customAction.getActionType().getGtnUIFrameWorkAction();
			action.doAction(componentId, customAction);

		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUiFrameworkItemADDAddAllButtonAction();
	}

	public void insertData(GtnWsDeductionCalendarBean deductionCalendarBean) {
		if (GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID) == null) {

			GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkCommonConstants.SESSION_ID, Calendar.getInstance().get(Calendar.MILLISECOND));

		}
		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsDeductionCalendarRequest deductionCalendarRequest = new GtnWsDeductionCalendarRequest();
		updateRequest.setDeductionCalendarRequest(deductionCalendarRequest);
		deductionCalendarRequest.setDeductionCalendarBean(deductionCalendarBean);
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.DC_SERVICE + GtnWsCDRContants.DC_ITEM_ADD_SERVICE, updateRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

}
