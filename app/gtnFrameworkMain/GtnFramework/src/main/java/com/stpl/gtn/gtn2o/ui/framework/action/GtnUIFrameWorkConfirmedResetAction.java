package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;
import java.util.Map;

import org.asi.calendarfield.CalendarField;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.Property;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.VerticalLayout;

public class GtnUIFrameWorkConfirmedResetAction implements GtnUIFrameWorkAction {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkConfirmedResetAction.class);

	@SuppressWarnings({ "unchecked" })
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> params = (gtnUIFrameWorkActionConfig.getActionParameterList());
		Map<String, Object> resetDataMapTemp = null;
		List<String> resetComponentIdListTemp = null;
		List<Object> resetComponentValueListTemp = null;
		if (params.get(0) instanceof List) {
			resetComponentIdListTemp = (List<String>) params.get(0);
			resetComponentValueListTemp = (List<Object>) params.get(1);
		} else {
			resetDataMapTemp = (Map<String, Object>) params.get(0);
		}
		Map<String, Object> resetDataMap = resetDataMapTemp;
		List<String> resetComponentIdList = resetComponentIdListTemp;
		List<Object> resetComponentValueList = resetComponentValueListTemp;
		if (resetDataMap != null) {
			for (Map.Entry<String, Object> propertyId : resetDataMap.entrySet()) {
				resetComponent(propertyId.getKey(), propertyId.getValue(), componentId);
			}
			return;
		}
		if (resetComponentIdList != null) {
			for (int i = 0; i < resetComponentIdList.size(); i++) {
				resetComponent(resetComponentIdList.get(i), resetComponentValueList.get(i), componentId);
			}
		}
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION.getSingletonAction();
	}

	public void resetComponent(String componentId, Object value, String sourceComponentId) {
		try {
			GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId,
					sourceComponentId);
			if (baseComponent.getComponent() instanceof CalendarField) {
				baseComponent.getComponentConfig().getComponentType().getGtnComponent().resetToDefault(componentId,
						baseComponent.getComponentConfig());
			}
			if (baseComponent.getComponent() instanceof Property) {
				baseComponent.setPropertyValue(value);
			}
			if (baseComponent.getComponent() instanceof VerticalLayout) {
				GtnUIFrameworkComponentData componentData = baseComponent.getComponentData();
				if (componentData.getCustomData() instanceof ExtCustomTable) {
					baseComponent.getComponentConfig().getComponentType().getGtnComponent().resetToDefault(componentId,
							baseComponent.getComponentConfig());
				}
			}
		} catch (Exception typeException) {
			gtnLogger.error("Exception on reset component id=" + componentId, typeException);
		}
	}

}