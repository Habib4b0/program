package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownGridLoadAction;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkReportingComparisonOptionsGroupValuesLoadingAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkReportingComparisonOptionsGroupValuesLoadingAction.class);
	
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
		String comparisonBasis = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportingDashboard_displaySelectionTabComparisonBasis",componentId).getStringCaptionFromV8ComboBox();
		String frequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportingDashboard_displaySelectionTabFrequency",componentId).getStringCaptionFromV8ComboBox();
		String periodRangeFrom = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeFrom",componentId).getStringCaptionFromV8ComboBox();
		String periodRangeTo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeTo",componentId).getStringCaptionFromV8ComboBox();
		
		// setting value to Comparison Breakdown LookUp
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("comparisonOptionsLookup_comparisonBasis").loadV8FieldValue(comparisonBasis);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("comparisonOptionsLookup_frequencyConfig").loadV8FieldValue(frequency);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("comparisonOptionsLookup_periodRangeFrom").loadV8FieldValue(periodRangeFrom);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("comparisonOptionsLookup_periodRangeTo").loadV8FieldValue(periodRangeTo);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
