package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkAliasRemoveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());
		GtnUIFrameworkSetter setter = new GtnUIFrameworkSetter();
		if (tableBaseComponent.getItemsFromDataTable().isEmpty()) {
			tableBaseComponent.setTableValue(null);
		}
		Object selectedvalue = tableBaseComponent.getValueFromComponent();
		if (selectedvalue == null) {
			setter.showMessageBox(componentId, GtnUIFrameworkActionType.ALERT_ACTION, parameters.get(2).toString(),
					parameters.get(3).toString());
			return;
		}
		confirmRemoveFromTable(componentId, parameters);
	}

	private void confirmRemoveFromTable(String componentId, List<Object> parameters)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig aliasRemoveConfirmActionConfig = new GtnUIFrameWorkActionConfig();
		aliasRemoveConfirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		aliasRemoveConfirmActionConfig.addActionParameter(parameters.get(4));
		aliasRemoveConfirmActionConfig.addActionParameter(parameters.get(5));
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig removeActionConfig = new GtnUIFrameWorkActionConfig();
		removeActionConfig.setActionType(GtnUIFrameworkActionType.REMOVE_ACTION);
		removeActionConfig.addActionParameter(parameters.get(1));
		successActionConfigList.add(removeActionConfig);
		aliasRemoveConfirmActionConfig.addActionParameter(successActionConfigList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, aliasRemoveConfirmActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
