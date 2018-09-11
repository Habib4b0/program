/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action.validation.v8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.vaadin.addons.ComboBoxMultiselect;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
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
			else if (baseComponent.getComponent() instanceof RadioButtonGroup) {
				baseComponent.loadV8FieldValue(value);
			}
			else if (baseComponent.getComponent() instanceof VerticalLayout) {
				resetFilterComponents(componentId, baseComponent);
				baseComponent.setV8GridItems(new ArrayList<>());
			}
			else if(baseComponent.getComponent() instanceof ComboBoxMultiselect){
				baseComponent.loadV8MultiSelectValue();
			}
			else if(baseComponent.getComponent() instanceof ComboBox){
				if(baseComponent.getComponentConfig().getCustomReference().equals("integerId")){
				baseComponent.loadV8ComboBoxComponentValue((Integer)value);
				}
				else{
					baseComponent.loadV8ComboBoxComponentValue(String.valueOf(value));
				}
			} else if (baseComponent.getComponent() instanceof DateField) {
				baseComponent.loadV8DateValue(value);
			}

		} catch (Exception typeException) {
			gtnLogger.error("Exception on reset component id=" + componentId, typeException);
		}
	}

	private void resetFilterComponents(String componentId, GtnUIFrameworkBaseComponent baseComponent) {
		
		gtnLogger.debug("componentId" + componentId);
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> filterMap = baseComponent.getComponentConfig()
				.getGtnPagedTableConfig().getCustomFilterConfigMap();
		Set<String> fliterComponents = filterMap.keySet();
		VerticalLayout verticalLayout = (VerticalLayout) baseComponent.getComponent();
		Grid grid = (Grid) verticalLayout.getComponent(0);
		for (String s : fliterComponents) {
			
			Component component = grid.getHeaderRow(1).getCell(s).getComponent();
			HorizontalLayout horizontalLayout = (HorizontalLayout) component;
			
			if (horizontalLayout.getComponent(0) instanceof TextField) {
				
				TextField textField=(TextField) horizontalLayout.getComponent(0);
				textField.setValue("");
				textField.setPlaceholder("Show all");
			}
			else if (horizontalLayout.getComponent(0) instanceof DateField) {
				DateField dateField = (DateField) horizontalLayout.getComponent(0);
				dateField.setValue(null);
				dateField.setPlaceholder("Show all");
			}
		}
	}

}