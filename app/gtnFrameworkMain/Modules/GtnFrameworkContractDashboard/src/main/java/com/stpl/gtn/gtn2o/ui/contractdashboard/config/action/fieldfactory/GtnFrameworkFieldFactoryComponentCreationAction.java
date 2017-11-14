/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory;

import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkFieldFactoryComponentCreationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String currentPropId = parameters.get(2).toString();
		String depandantPropId = parameters.get(1).toString();
		String componentIdPrefix = componentId.substring(0, componentId.length() - currentPropId.length());
		GtnUIFrameworkBaseComponent depandantBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentIdPrefix + depandantPropId);
		GtnUIFrameworkComponentData depandantComponentData = depandantBaseComponent.getComponentData();
		GtnWsRecordBean bean = depandantComponentData.getActionParameter().getItemId();
		String value = depandantBaseComponent.getCaptionFromComboBox();
		gtnUIFrameWorkActionConfig.setComponentConfig(
				getFieldFactoryField(currentPropId, value, gtnUIFrameWorkActionConfig.getComponentConfig(), bean));
	}

	private GtnUIFrameworkComponentConfig addFieldFactoryField(GtnUIFrameworkComponentType componentType,
			List<GtnUIFrameWorkActionConfig> customActionList) {
		GtnUIFrameworkComponentConfig componentConfig = GtnFrameworkComponentConfigProvider.getInstance()
				.getUIFrameworkComponentConfig("", false, null, componentType);
		componentConfig.setGtnUIFrameWorkValueChangeActionConfigList(customActionList);
		return componentConfig;
	}

	private GtnUIFrameworkComponentConfig getFieldFactoryField(String currentPropId, String depandingValue,
			GtnUIFrameworkComponentConfig componentConfig, GtnWsRecordBean bean) {
		if (currentPropId.equals(GtnFrameworkContractDashboardContants.getPriceDetailEditableColumn()[5])) {
			boolean enable = false;
			if (bean.getIntegerPropertyByIndex(18) == 0) {
				enable = true;
			}
			componentConfig.setEnable(enable);
			return componentConfig;
		}
		if (depandingValue.startsWith("P")) {
			componentConfig.setRebuild(true);
			return componentConfig;
		}
		if (depandingValue.startsWith("D")) {
			GtnUIFrameworkComponentConfig textComponentConfig = addFieldFactoryField(
					GtnUIFrameworkComponentType.DATEFIELD,
					componentConfig.getGtnUIFrameWorkValueChangeActionConfigList());
			textComponentConfig.setGtnTextBoxConfig(new GtnUIFrameworkTextBoxConfig());
			textComponentConfig.setRebuild(true);
			return textComponentConfig;
		}
		if (depandingValue.startsWith("M")) {
			return addFieldFactoryTextField(GtnUIFrameworkComponentType.TEXTBOX,
					componentConfig.getGtnUIFrameWorkValueChangeActionConfigList());
		}
		return null;

	}

	private GtnUIFrameworkComponentConfig addFieldFactoryTextField(GtnUIFrameworkComponentType textbox,
			List<GtnUIFrameWorkActionConfig> gtnUIFrameWorkValueChangeActionConfigList) {
		GtnUIFrameworkComponentConfig componentConfig = addFieldFactoryField(textbox,
				gtnUIFrameWorkValueChangeActionConfigList);
		componentConfig.setGtnTextBoxConfig(new GtnUIFrameworkTextBoxConfig());
		componentConfig.setRebuild(true);
		return componentConfig;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
