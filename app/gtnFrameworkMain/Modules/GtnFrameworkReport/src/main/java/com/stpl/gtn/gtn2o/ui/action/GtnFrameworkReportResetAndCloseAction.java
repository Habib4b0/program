package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.ComboBoxMultiselect;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class GtnFrameworkReportResetAndCloseAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkReportResetAndCloseAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		List<String> resetComponentIdList = null;
		List<Object> resetComponentValueList = null;
		resetComponentIdList = (List<String>) parameters.get(5);
		resetComponentValueList = (List<Object>) parameters.get(6);
		for (int i = 0; i < resetComponentIdList.size(); i++) {
			resetComponent(resetComponentIdList.get(i), resetComponentValueList.get(i), componentId);
		}	
	}

	private void resetComponent(String componentId, Object value, String sourceComponentId) {
		try {
			GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId,
					sourceComponentId);
			if (baseComponent.getComponent() instanceof TextField) {
				baseComponent.loadV8FieldValue(value);
			}
			if (baseComponent.getComponent() instanceof RadioButtonGroup) {
				baseComponent.loadV8FieldValue(value);
			}
			if (baseComponent.getComponent() instanceof VerticalLayout) {
				baseComponent.setV8GridItems(new ArrayList<>());
			}
			if(baseComponent.getComponent() instanceof ComboBoxMultiselect){
				baseComponent.loadV8MultiSelectValue();
			}
			if(baseComponent.getComponent() instanceof ComboBox){			
					baseComponent.loadV8ComboBoxComponentValue(String.valueOf(value));
			}

		} catch (Exception typeException) {
			logger.error("Exception on reset component id=" + componentId, typeException);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
