package com.stpl.gtn.gtn2o.ui.action;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportingDashboardSaveProfileLookupBean;

public class GtnReportingDashboardReportProfileLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnReportingDashboardReportProfileLoadAction.class);
	
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(1).toString()).getComponentData().getCustomData();
		String viewData = (String) recordBean.getPropertyValueByIndex(5);
		GtnReportingDashboardSaveProfileLookupBean reportProfileSaveLookupBean = new GtnReportingDashboardSaveProfileLookupBean();
		try {
			reportProfileSaveLookupBean = (GtnReportingDashboardSaveProfileLookupBean) convertJsonToObject(GtnReportingDashboardSaveProfileLookupBean.class,
					viewData.replaceAll("\\\\", "'"));
		} catch (IOException e) {
			gtnLogger.error("Error in converting Bean", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
	
	
	private GtnReportingDashboardSaveProfileLookupBean convertJsonToObject(Class<GtnReportingDashboardSaveProfileLookupBean> reportProfileSaveLookupBean,
			String viewData) throws JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(viewData, reportProfileSaveLookupBean);
	}
}
