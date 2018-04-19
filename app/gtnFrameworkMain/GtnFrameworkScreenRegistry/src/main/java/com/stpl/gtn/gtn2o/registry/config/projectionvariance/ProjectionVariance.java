package com.stpl.gtn.gtn2o.registry.config.projectionvariance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkbox.GtnUIFrameworkCheckBoxComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;


public class ProjectionVariance {


	GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addProjectionVarianceTabComponents(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		addProjectionVariancePanel(componentList, nameSpace);
	}

	private void addProjectionVariancePanel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		
		GtnUIFrameworkComponentConfig projectionVarianceMainPanel = new GtnUIFrameworkComponentConfig();
		projectionVarianceMainPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		projectionVarianceMainPanel.setComponentId(nameSpace + "_" + "projectionVarianceMainPanel");
		projectionVarianceMainPanel.setAddToParent(false);
		projectionVarianceMainPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(projectionVarianceMainPanel);
		addprojectionVarianceMainLayout(componentList, nameSpace);
		
	}

	private void addprojectionVarianceMainLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		
		GtnUIFrameworkComponentConfig projectionVarianceMainLayout = configProvider.getVerticalLayoutConfig(
			nameSpace + "_" + "projectionVarianceMainLayout", true, nameSpace + "_" + "projectionVarianceMainPanel");
		projectionVarianceMainLayout.setComponentWidth("100%");
		projectionVarianceMainLayout.setMargin(true);
		componentList.add(projectionVarianceMainLayout);
		new DisplaySelectionFilterOptionTab().addTabSheet(componentList, projectionVarianceMainLayout.getComponentId(), nameSpace);
		new ProjectionVarianceGenerateResetButton().addGenerateResetButtonLayout(componentList, projectionVarianceMainLayout.getComponentId(), nameSpace);
		addProjectionPivoteViewTable(componentList, projectionVarianceMainLayout.getComponentId(), nameSpace);
	}

	private void addProjectionPivoteViewTable(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace)
	{


	}
}