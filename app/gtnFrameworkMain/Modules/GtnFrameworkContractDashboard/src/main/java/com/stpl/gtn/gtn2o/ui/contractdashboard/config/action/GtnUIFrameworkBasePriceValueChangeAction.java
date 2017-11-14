package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnUIFrameworkBasePriceValueChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPopulateFieldValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> mainParameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		List<String> parameters = (List<String>) mainParameters.get(1);
		String componentValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getCaptionFromComboBox();
		if (componentValue.isEmpty()) {
			componentValue = componentId;
		}
		componentValue = componentValue.replace(" ", "");
		int start = Integer.parseInt(String.valueOf(mainParameters.get(2)));
		int end = Integer.parseInt(String.valueOf(mainParameters.get(3)));
		for (int i = start; i < parameters.size() - end; i++) {
			String parameter = parameters.get(i);
			GtnUIFrameworkBaseComponent basecomponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameter);
			resetBaseComponenentVaule(basecomponent);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameter)
					.setComponentVisible(parameter.contains("_" + componentValue + "_"));
		}
	}

	private void resetBaseComponenentVaule(GtnUIFrameworkBaseComponent basecomponent) {

		try {

			for (int i = 0; i < GtnFrameworkContractDashboardContants.getPpBaseMassPopulateFields().length; i++) {

				GtnUIFrameworkBaseComponent resetBaseMassUpdate = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkContractDashboardContants.getPpBaseMassPopulateFields()[i]);

				if (!GtnUIFrameworkComponentType.LAYOUT.equals(basecomponent.getComponentConfig().getComponentType())
						&& resetBaseMassUpdate != null) {
					if (basecomponent.getComponentConfig().getComponentType()
							.equals(GtnUIFrameworkComponentType.COMBOBOX)) {
						resetBaseMassUpdate.loadComboBoxComponentValue(null);
					} else {
						resetBaseMassUpdate.setPropertyValue(null);
					}
				}
			}
		} catch (Exception e) {
			gtnLogger.error("Exception in resetBaseComponenentVaule", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
