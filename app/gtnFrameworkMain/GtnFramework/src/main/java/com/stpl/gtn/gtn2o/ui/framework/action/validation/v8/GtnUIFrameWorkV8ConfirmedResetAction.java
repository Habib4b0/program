/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action.validation.v8;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnUIFrameWorkV8ConfirmedResetAction implements GtnUIFrameWorkAction {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkV8ConfirmedResetAction.class);

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
			
		} catch (Exception typeException) {
			gtnLogger.error("Exception on reset component id=" + componentId, typeException);
		}
	}

}