package com.stpl.gtn.gtn2o.ui.framework.component.chart;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractComponent;

public class GtnUIFrameworkBarChart implements GtnUIFrameworkComponent {

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		// BarChartConfig
		// ChartJs chart = new ChartJs();
		// BarChartConfig config = new BarChartConfig();
		// // BarChartOptions barChartOptions = new BarChartOptions(config);
		// chart.configure(config);
		return null;
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput) {

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {

	}

}
