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

		// loading valueComboBox based on frequency
		List<String> valueList = new ArrayList<String>();
		
		populateValueListBasedOnFrequency(frequency, valueList);

		reloadValueComboBoxInComparisonOptions(componentId, valueList);

		GtnUIFrameworkComboBoxConfig comparisonBasisConfig = getComparisonBasisConfigInComparisonOptions(componentId);
		
		GtnUIFrameworkComboBoxConfig comparisonBasisInDisplaySelectionConfig = getComparisonBasisConfigInDisplaySelectionTab(
				componentId);
		
		List<String> projectionsFromComparisonBasisInDisplaySelection = comparisonBasisInDisplaySelectionConfig.getItemCaptionValues();
		List<String> finalProjectionList = new ArrayList<>();
		for(String projection : projectionsFromComparisonBasisInDisplaySelection) {
			if(!projection.equals(comparisonBasis)) {
				finalProjectionList.add(projection);
			}
		}
		comparisonBasisConfig.setItemCaptionValues(finalProjectionList);
		comparisonBasisConfig.setItemValues(finalProjectionList);

		
		GtnUIFrameworkComboBoxComponent comparisonBasisMassUpdateComboBox = new GtnUIFrameworkComboBoxComponent();
		comparisonBasisMassUpdateComboBox.reloadComponentFromView(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
				"reportOptionsTabComparisonOptions_comparison", componentId, Arrays.asList(""));

	}

	private GtnUIFrameworkComboBoxConfig getComparisonBasisConfigInDisplaySelectionTab(String componentId) {
		GtnUIFrameworkBaseComponent vaadinComparisonBasisInDisplaySelectionBaseComponent = GtnUIFrameworkGlobalUI
		.getVaadinBaseComponent("reportingDashboard_displaySelectionTabComparisonBasis", componentId);
		GtnUIFrameworkComponentConfig comparisonBasisInDisplaySelectionComponentConfig = vaadinComparisonBasisInDisplaySelectionBaseComponent.getComponentConfig();
		GtnUIFrameworkComboBoxConfig comparisonBasisInDisplaySelectionConfig = comparisonBasisInDisplaySelectionComponentConfig.getGtnComboboxConfig();
		return comparisonBasisInDisplaySelectionConfig;
	}

	private GtnUIFrameworkComboBoxConfig getComparisonBasisConfigInComparisonOptions(String componentId) {
		GtnUIFrameworkBaseComponent vaadinComparisonBasisBaseComponentFromView = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromView("reportOptionsTabComparisonOptions_comparison", componentId);
		GtnUIFrameworkComponentConfig comparisonBasiscomponentConfig = vaadinComparisonBasisBaseComponentFromView.getComponentConfig();
		GtnUIFrameworkComboBoxConfig comparisonBasisConfig = comparisonBasiscomponentConfig.getGtnComboboxConfig();
		return comparisonBasisConfig;
	}

	private void reloadValueComboBoxInComparisonOptions(String componentId, List<String> valueList) {
		GtnUIFrameworkBaseComponent vaadinBaseComponentFromView = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromView("reportOptionsTabComparisonOptions_value", componentId);
		GtnUIFrameworkComponentConfig componentConfig = vaadinBaseComponentFromView.getComponentConfig();
		GtnUIFrameworkComboBoxConfig config = componentConfig.getGtnComboboxConfig();
		config.setItemCaptionValues(valueList);
		config.setItemValues(valueList);

		GtnUIFrameworkComboBoxComponent valueComboBox = new GtnUIFrameworkComboBoxComponent();
		valueComboBox.reloadComponentFromView(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
				"reportOptionsTabComparisonOptions_value", componentId, Arrays.asList(""));
	}

	private void populateValueListBasedOnFrequency(String frequency, List<String> valueList) {
		
		switch (frequency) {
		case "Month": {
			getValueList(valueList, 36);
			break;
		}
		case "Quarter": {
			getValueList(valueList, 12);
			break;
		}
		case "Semi-Annual": {
			getValueList(valueList, 6);
			break;
		}
		case "Annual": {
			getValueList(valueList, 3);
			break;
		}
		}
	}
	private void getValueList(List<String> valueList, int i) {
		for ( int j = 1; j <= i; j++)
			 valueList.add("-" + j);		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
