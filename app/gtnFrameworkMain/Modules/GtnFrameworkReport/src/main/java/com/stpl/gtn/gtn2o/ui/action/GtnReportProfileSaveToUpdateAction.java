package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportingDashboardSaveProfileLookupBean;

public class GtnReportProfileSaveToUpdateAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportProfileSaveToUpdateAction.class);
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
		
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try{
		List<Object> params = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnReportingDashboardSaveProfileLookupBean reportingDashboardSaveProfileLookupBean = (GtnReportingDashboardSaveProfileLookupBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(params.get(1).toString()).getComponentData().getSharedPopupData();
		if(reportingDashboardSaveProfileLookupBean.getRecordBean()!=null){
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(params.get(2).toString()).setHasValue(String.valueOf(reportingDashboardSaveProfileLookupBean.getRecordBean().getPropertyValueByIndex(0)));
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(params.get(3).toString()).setEnable(false);
		}
		}
		catch(Exception ex){
			logger.error("Error in",ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
