/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action.validation.v8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.vaadin.addons.ComboBoxMultiselect;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnUIFrameWorkV8ConfirmedResetAction implements GtnUIFrameWorkAction {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkV8ConfirmedResetAction.class);

	@SuppressWarnings({ "unchecked" })
	@Override
	public void doAction(String v8ComponentId, GtnUIFrameWorkActionConfig v8GtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> v8Params = (v8GtnUIFrameWorkActionConfig.getActionParameterList());
		Map<String, Object> v8ResetDataMapTemp = null;
		List<String> v8ResetComponentIdListTemp = null;
		List<Object> v8ResetComponentValueListTemp = null;
		if (v8Params.get(0) instanceof List) {
			v8ResetComponentIdListTemp = (List<String>) v8Params.get(0);
			v8ResetComponentValueListTemp = (List<Object>) v8Params.get(1);
		} else {
			v8ResetDataMapTemp = (Map<String, Object>) v8Params.get(0);
		}
		Map<String, Object> v8ResetDataMap = v8ResetDataMapTemp;
		List<String> v8RresetComponentIdList = v8ResetComponentIdListTemp;
		List<Object> v8ResetComponentValueList = v8ResetComponentValueListTemp;
		if (v8ResetDataMap != null) {
			for (Map.Entry<String, Object> v8PropertyId : v8ResetDataMap.entrySet()) {
				resetComponent(v8PropertyId.getKey(), v8PropertyId.getValue(), v8ComponentId);
			}
			return;
		}
		if (v8RresetComponentIdList != null) {
			for (int i = 0; i < v8RresetComponentIdList.size(); i++) {
				resetComponent(v8RresetComponentIdList.get(i), v8ResetComponentValueList.get(i), v8ComponentId);
			}
		}
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig v8GtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return GtnUIFrameworkActionType.V8_CONFIRMED_RESET_ACTION.getSingletonAction();
	}

	public void resetComponent(String componentId, Object value, String sourceComponentId) {
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
				if(baseComponent.getComponentConfig().getCustomReference().equals("integerId")){
				baseComponent.loadV8ComboBoxComponentValue((int)value);
				}
				else{
					baseComponent.loadV8ComboBoxComponentValue(String.valueOf(value));
				}
			}

		} catch (Exception typeException) {
			gtnLogger.error("Exception on reset component id=" + componentId, typeException);
		}
	}

}