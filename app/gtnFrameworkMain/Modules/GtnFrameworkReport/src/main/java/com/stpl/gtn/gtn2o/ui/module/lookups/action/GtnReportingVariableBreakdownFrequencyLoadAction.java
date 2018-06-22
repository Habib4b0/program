/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import java.util.Arrays;

import java.util.List;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnReportingVariableBreakdownFrequencyLoadAction
		implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingVariableBreakdownFrequencyLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Inside Configure Parameters");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();

		String frequencyId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(1).toString())
				.getCaptionFromV8ComboBox();

                if("reportingDashboardScreen".equals(actionParameterList.get(3).toString())){
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromView(actionParameterList.get(2).toString(), componentId)
				.loadV8ComboBoxComponentValue(Integer.valueOf(frequencyId));
                }
                else{
                  GtnUIFrameworkComboBoxConfig relationComboboxConfig = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(actionParameterList.get(2).toString(), componentId).getComponentConfig()
						.getGtnComboboxConfig();
				relationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				relationComboboxConfig.setComboBoxType(GtnFrameworkReportStringConstants.REPORT_CONFIG_FREQUENCY);

				GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
				combobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
						actionParameterList.get(2).toString(), componentId,
						Arrays.asList(""));
     
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(2).toString(), componentId).loadV8ComboBoxComponentValue(Integer.valueOf(frequencyId)); 
                }

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
