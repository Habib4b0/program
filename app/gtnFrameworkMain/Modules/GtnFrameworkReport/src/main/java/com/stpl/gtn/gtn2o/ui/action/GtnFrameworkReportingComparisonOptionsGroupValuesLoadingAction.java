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

		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();

		GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_fromPeriod", sourceComponentId);
		String periodRangeFrom = baseComponent.getStringCaptionFromV8ComboBox();
		periodRangeFrom = periodRangeFrom.replaceAll(" ", "");
		GtnUIFrameworkBaseComponent baseComponent2 = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeFrom", componentId);

		String periodRangeTo = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_STATUS", sourceComponentId).getStringCaptionFromV8ComboBox();
		periodRangeTo = periodRangeTo.replaceAll(" ", "");

		String comparisonBasis = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabComparisonBasis", componentId)
				.getStringCaptionFromV8ComboBox();
		String frequency = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabFrequency", componentId)
				.getStringCaptionFromV8ComboBox();
		String displaySelectionTabPeriodRangeFrom = baseComponent2.getStringCaptionFromV8ComboBox();
		if (!displaySelectionTabPeriodRangeFrom.equals("-Select one-")) {
			periodRangeFrom = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeFrom", componentId)
					.getStringCaptionFromV8ComboBox();
		}
		if (!GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeTo", componentId)
				.getStringCaptionFromV8ComboBox().contains("Select"))
			periodRangeTo = GtnUIFrameworkGlobalUI
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
		List<String> valueList = new ArrayList<>();

		populateValueListBasedOnFrequency(frequency, valueList);

		reloadValueComboBoxInComparisonOptions(componentId, valueList);

		GtnUIFrameworkComboBoxConfig comparisonBasisConfig = getComparisonBasisConfigInComparisonOptions(componentId);

		GtnUIFrameworkComboBoxConfig comparisonBasisInDisplaySelectionConfig = getComparisonBasisConfigInDisplaySelectionTab(
				componentId);

		List<String> projectionsFromComparisonBasisInDisplaySelection = comparisonBasisInDisplaySelectionConfig
				.getItemCaptionValues();
		List<String> finalProjectionList = new ArrayList<>();
		for (String projection : projectionsFromComparisonBasisInDisplaySelection) {
			if (!projection.equals(comparisonBasis)) {
				finalProjectionList.add(projection);
			}
		}
		comparisonBasisConfig.setItemCaptionValues(finalProjectionList);
		comparisonBasisConfig.setItemValues(finalProjectionList);

		GtnUIFrameworkComboBoxComponent comparisonBasisMassUpdateComboBox = new GtnUIFrameworkComboBoxComponent();
		comparisonBasisMassUpdateComboBox.reloadComponentFromView("reportOptionsTabComparisonOptions_comparison",
				componentId, Arrays.asList(""));

	}

	private GtnUIFrameworkComboBoxConfig getComparisonBasisConfigInDisplaySelectionTab(String componentId) {
		GtnUIFrameworkBaseComponent vaadinComparisonBasisInDisplaySelectionBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabComparisonBasis", componentId);
		GtnUIFrameworkComponentConfig comparisonBasisInDisplaySelectionComponentConfig = vaadinComparisonBasisInDisplaySelectionBaseComponent
				.getComponentConfig();
		return comparisonBasisInDisplaySelectionComponentConfig.getGtnComboboxConfig();
	}

	private GtnUIFrameworkComboBoxConfig getComparisonBasisConfigInComparisonOptions(String componentId) {
		GtnUIFrameworkBaseComponent vaadinComparisonBasisBaseComponentFromView = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromView("reportOptionsTabComparisonOptions_comparison", componentId);
		GtnUIFrameworkComponentConfig comparisonBasiscomponentConfig = vaadinComparisonBasisBaseComponentFromView
				.getComponentConfig();
		return comparisonBasiscomponentConfig.getGtnComboboxConfig();

	}

	private void reloadValueComboBoxInComparisonOptions(String componentId, List<String> valueList) {
		GtnUIFrameworkBaseComponent vaadinBaseComponentFromView = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromView("reportOptionsTabComparisonOptions_value", componentId);
		GtnUIFrameworkComponentConfig componentConfig = vaadinBaseComponentFromView.getComponentConfig();
		GtnUIFrameworkComboBoxConfig config = componentConfig.getGtnComboboxConfig();
		config.setItemCaptionValues(valueList);
		config.setItemValues(valueList);

		GtnUIFrameworkComboBoxComponent valueComboBox = new GtnUIFrameworkComboBoxComponent();
		valueComboBox.reloadComponentFromView("reportOptionsTabComparisonOptions_value", componentId,
				Arrays.asList(""));
	}

	private void populateValueListBasedOnFrequency(String frequency, List<String> valueList) {

		switch (frequency) {
		case "Month":
			getValueList(valueList, 36);
			break;

		case "Quarter":
			getValueList(valueList, 12);
			break;

		case "Semi-Annual":
			getValueList(valueList, 6);
			break;

		case "Annual":
			getValueList(valueList, 3);
			break;

		default:
			break;
		}
	}

	private void getValueList(List<String> valueList, int i) {
		valueList.add("0");
		for (int j = 1; j <= i; j++)
			valueList.add("-" + j);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
