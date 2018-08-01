package com.stpl.gtn.gtn2o.ui.framework.component.chart;

import com.stpl.addons.vaadin.chartjs.ChartJs;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;

public class GtnUIFrameworkBarChart implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnUIFrameworkBarChart.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		ChartJs barChart = new ChartJs();
		barChart.setJsLoggingEnabled(true);
		barChart.setSizeFull();
		return barChart;
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput) {
     			// Functionality not yet needed
	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
		postCreateComponent(baseComponent.getComponent(), componentConfig);
	}

	@Override
	public void postCreateComponent(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {
		try {
			GtnUIFrameworkActionExecutor.executeActionList(componentConfig.getComponentId(),
					componentConfig.getGtnUIFrameWorkActionConfigList());
		} catch (GtnFrameworkGeneralException e) {
			LOGGER.error("Exception in postcreate of Bar Chart", e);
		}

	}

}
