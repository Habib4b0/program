package com.stpl.gtn.gtn2o.ui.action;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;

public class GtnReportVariableReloadInReportingDashboardAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnReportVariableReloadInReportingDashboardAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Configure Params " );
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();	
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
		List<Object> variableList = dataSelectionBean.getVariablesList();
		GtnUIFrameworkBaseComponent variableBaseComponent = GtnUIFrameworkGlobalUI
		.getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabVariable",
				componentId);
		GtnWsReportVariablesType[] variableType = Arrays.copyOfRange(GtnWsReportVariablesType.values(), 0,
				GtnWsReportVariablesType.values().length - 1);
		variableBaseComponent.addAllItemsToMultiSelect(
				Arrays.stream(variableType).map(GtnWsReportVariablesType::toString)
				.collect(Collectors.toList()),
		Arrays.stream(variableType).map(GtnWsReportVariablesType::toString)
				.collect(Collectors.toList()));
		variableBaseComponent.updateSelection(variableList);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
