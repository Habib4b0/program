package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.vaadin.ui.AbstractComponent;

public class GtnReportingComparisonBreakdownHeaderLoadAction
		implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingComparisonBreakdownHeaderLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("HeaderLoadACtion Configure Params");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		
		String finalFromPeriod = null;
		String finalToPeriod = null;
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String variableBreakdownTableId = actionParameterList.get(0).toString();

		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(variableBreakdownTableId, componentId).getComponent();
		GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
		
		String frequency = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(3).toString(), componentId)
				.getStringCaptionFromV8ComboBox();

		String fromPeriodSourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
		GtnUIFrameworkBaseComponent fromPeriodBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_fromPeriod", fromPeriodSourceComponentId);
		String periodRangeFrom =  fromPeriodBaseComponent.getStringCaptionFromV8ComboBox();
		finalFromPeriod = periodRangeFrom.replaceAll(" ","");
		
		String toPeriodSourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
		GtnUIFrameworkBaseComponent toPeriodBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_STATUS", toPeriodSourceComponentId);
		String periodRangeTo =  toPeriodBaseComponent.getStringCaptionFromV8ComboBox();
		finalToPeriod = periodRangeTo.replaceAll(" ","");
		
		List<String> inputList = new ArrayList<>();
		
		inputList.add(finalFromPeriod);
		inputList.add(finalToPeriod);
		inputList.add(frequency);

		GtnWsReportDataSelectionBean gtnWsReportDataSelectionBean = new GtnWsReportDataSelectionBean();
		gtnWsReportDataSelectionBean.setVariableBreakdownHeaderLoadList(inputList);
		GtnWsReportRequest gtnWsReportRequest = new GtnWsReportRequest();
		gtnWsReportRequest.setDataSelectionBean(gtnWsReportDataSelectionBean);
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		gtnUIFrameworkWebserviceRequest.setGtnWsReportRequest(gtnWsReportRequest);
		gridComponent.setCustomData(gtnUIFrameworkWebserviceRequest);
		gridComponent.setCustomPagedTreeTableRequest(gtnUIFrameworkWebserviceRequest);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
