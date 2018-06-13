package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkReportingComparisonOptionsGroupValuesLoadingAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnFrameworkReportingComparisonOptionsGroupValuesLoadingAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Auto-generated method stub
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		logger.info("----Inside Comparison breakdown group box value loading action class----");

		// getting value from Reporting Dashboard
		String comparisonBasis = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabComparisonBasis", componentId)
				.getStringCaptionFromV8ComboBox();
		String frequency = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabFrequency", componentId)
				.getStringCaptionFromV8ComboBox();
		String periodRangeFrom = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeFrom", componentId)
				.getStringCaptionFromV8ComboBox();
		String periodRangeTo = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeTo", componentId)
				.getStringCaptionFromV8ComboBox();

		// setting value to Comparison Breakdown LookUp
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("comparisonOptionsLookup_comparisonBasis")
				.loadV8FieldValue(comparisonBasis);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("comparisonOptionsLookup_frequencyConfig")
				.loadV8FieldValue(frequency);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("comparisonOptionsLookup_periodRangeFrom")
				.loadV8FieldValue(periodRangeFrom);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("comparisonOptionsLookup_periodRangeTo")
				.loadV8FieldValue(periodRangeTo);

		//loading valueComboBox based on frequency
		List<String> valueList = new ArrayList<String>();

		switch (frequency) {
		case "Month": {
			for (int i = 1; i < 36; i++)
				valueList.add("-" + i);
			break;
		}
		case "Quarter": {
			for (int i = 1; i < 12; i++)
				valueList.add("-" + i);
			break;
		}
		case "Semi-Annual": {
			for (int i = 1; i < 6; i++)
				valueList.add("-" + i);
			break;
		}
		case "Annual": {
			for (int i = 1; i < 3; i++)
				valueList.add("-" + i);
			break;
		}
		}

		/*GtnUIFrameworkComboBoxConfig valueComboboxConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromView("reportOptionsTabComparisonOptions_value", componentId).getComponentConfig()
				.getGtnComboboxConfig();*/
		//GtnUIFrameworkComboBoxConfig valueComboboxConfig = new GtnUIFrameworkComboBoxConfig();
		
		GtnUIFrameworkBaseComponent vaadinBaseComponentFromView = GtnUIFrameworkGlobalUI
		.getVaadinBaseComponentFromView("reportOptionsTabComparisonOptions_value", componentId);
		GtnUIFrameworkComponentConfig componentConfig = vaadinBaseComponentFromView.getComponentConfig();
		GtnUIFrameworkComboBoxConfig config = componentConfig.getGtnComboboxConfig();
		config.setItemCaptionValues(valueList);
		config.setItemValues(valueList);
		
		
		
		GtnUIFrameworkComboBoxComponent valueComboBox = new GtnUIFrameworkComboBoxComponent();
		valueComboBox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,"reportOptionsTabComparisonOptions_value",componentId,Arrays.asList(""));
		//startPeriodCombobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,"reportOptionsTab_variableBreakdownStartPeriod", componentId,Arrays.asList(""));
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
