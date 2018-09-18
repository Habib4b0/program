package com.stpl.gtn.gtn2o.ui.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkComparisonLookupTextFieldEnableAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass{
	
	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkComparisonLookupTextFieldEnableAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Configure Params ");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String currentScreen = actionParameterList.get(4).toString();
		String comparisonLookupComponentId = currentScreen.equals("dataSelection")?actionParameterList.get(2).toString():actionParameterList.get(1).toString();
		String customViewComponentId = actionParameterList.get(3).toString();
		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(customViewComponentId, componentId).getComponent()!= null) {
			GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(customViewComponentId, componentId);
			String caption = 
					baseComponent.getStringCaptionFromV8ComboBox();
			if (!caption.equals("-Select one-")){
				GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
				actionConfig.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
				actionConfig.addActionParameter("disableReadOnly");
				actionConfig.addActionParameter(comparisonLookupComponentId);
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);
			} else if(caption.equals("-Select one-")){
				GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
				actionConfig.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
				actionConfig.addActionParameter("disableReadOnly");
				actionConfig.addActionParameter(comparisonLookupComponentId);
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);
			}
		}
		new GtnUIFrameworkComboBoxComponent().reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_UNIT_OF_MEASURE, componentId, Arrays.asList(""));
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
