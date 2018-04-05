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

public class GtnUIFrameworkPopulateFieldValueChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
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
		try {
			List<Object> mainParameters = gtnUIFrameWorkActionConfig.getActionParameterList();
			List<String> parameters = (List<String>) mainParameters.get(1);
			String componentValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getStringFromField();
			if (componentValue==null || componentValue.isEmpty() ) {
				componentValue = componentId;
			}
			componentValue = componentValue.replace(" ", "");
			int start = Integer.parseInt(String.valueOf(mainParameters.get(2)));
			int end = Integer.parseInt(String.valueOf(mainParameters.get(3)));
			String prefix = mainParameters.size() > 4 ? mainParameters.get(4).toString() : "_";
			String postfix = mainParameters.size() > 5 ? mainParameters.get(5).toString() : "_";
			for (int i = start; i < parameters.size() - end; i++) {
				String parameter = parameters.get(i);
				GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameter);
				resetComponenentVaule(component);
				component.setVisible(parameter.contains(prefix + componentValue + postfix));
			}
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnUIFrameworkPopulateFieldValueChangeAction", e);
			throw new GtnFrameworkGeneralException(e);
		}
	}

	private void resetComponenentVaule(GtnUIFrameworkBaseComponent component) {

		try {   

			for (int i = 0; i < GtnFrameworkContractDashboardContants.getPpMassPopulateFields().length; i++) {
				GtnUIFrameworkBaseComponent resetMassUpdate = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkContractDashboardContants.getPpMassPopulateFields()[i]);

				if (!GtnUIFrameworkComponentType.LAYOUT.equals(component.getComponentConfig().getComponentType())
						&& resetMassUpdate != null) {
                                    switch (component.getComponentConfig().getComponentType()) {
                                        case COMBOBOX:
                                            component.loadComboBoxComponentValue(null);
                                            break;
                                        case DATEFIELD:
                                            component.loadDateValue(null);
                                            break;
                                        case POPUPTEXTFIELD:
                                        case TEXTBOX:
                                            component.loadFieldValue(GtnFrameworkContractDashboardContants.STRINGUTILS_EMPTY);
                                            break;
                                        default:
                                            break;
                                    }
				}
			}
		} catch (Exception e) {
			gtnLogger.error("Exception in resetComponenentVaule", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
