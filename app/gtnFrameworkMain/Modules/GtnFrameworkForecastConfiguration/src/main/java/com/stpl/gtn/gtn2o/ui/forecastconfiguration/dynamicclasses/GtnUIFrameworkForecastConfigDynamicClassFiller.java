package com.stpl.gtn.gtn2o.ui.forecastconfiguration.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnFrameworkConfirmSaveAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnFrameworkSaveAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkDataFrequencyValueChangeAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkForecastConfigurationSaveAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkFromPeriodValueChangeAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkHistoryIntervalValueChangeAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkIntervalFrequencyValueChangeAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkModeValueChangeAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkProcessTypeValueChangeAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkToPeriodValueChangeAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;

public class GtnUIFrameworkForecastConfigDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkConfirmSaveAction.class.getName(),
				new GtnFrameworkConfirmSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkSaveAction.class.getName(),
				new GtnFrameworkSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkDataFrequencyValueChangeAction.class.getName(),
				new GtnUIFrameworkDataFrequencyValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkForecastConfigurationSaveAction.class.getName(),
				new GtnUIFrameworkForecastConfigurationSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkFromPeriodValueChangeAction.class.getName(),
				new GtnUIFrameworkFromPeriodValueChangeAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkHistoryIntervalValueChangeAction.class.getName(),
				new GtnUIFrameworkHistoryIntervalValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkIntervalFrequencyValueChangeAction.class.getName(),
				new GtnUIFrameworkIntervalFrequencyValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkModeValueChangeAction.class.getName(),
				new GtnUIFrameworkModeValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkProcessTypeValueChangeAction.class.getName(),
				new GtnUIFrameworkProcessTypeValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkToPeriodValueChangeAction.class.getName(),
				new GtnUIFrameworkToPeriodValueChangeAction());

	}

}
