package com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory;

import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkPPFieldFactoryDynamicComponentAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String psCurrentPropId = parameters.get(2).toString();
		String psDepandantPropId = parameters.get(1).toString();
		String psComponentIdPrefix = componentId.substring(0, componentId.length() - psCurrentPropId.length());
		GtnUIFrameworkBaseComponent psDepandantBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(psComponentIdPrefix + psDepandantPropId);
		String priceTypeValue = psDepandantBaseComponent.getCaptionFromComboBox();
		gtnUIFrameWorkActionConfig.setComponentConfig(
				getFieldFactoryTextField(priceTypeValue, gtnUIFrameWorkActionConfig.getComponentConfig()));
	}

	private GtnUIFrameworkComponentConfig addFieldFactoryField(GtnUIFrameworkComponentType componentType,
			List<GtnUIFrameWorkActionConfig> customActionList) {
		GtnUIFrameworkComponentConfig componentConfig = GtnFrameworkComponentConfigProvider.getInstance()
				.getUIFrameworkComponentConfig("", false, null, componentType);
		componentConfig.setGtnUIFrameWorkValueChangeActionConfigList(customActionList);
		return componentConfig;
	}

	private GtnUIFrameworkComponentConfig getFieldFactoryTextField(String psDepandingValue,
			GtnUIFrameworkComponentConfig componentConfig) {

		if (psDepandingValue.startsWith("P")) {
			componentConfig.setRebuild(true);
			return componentConfig;
		}
		if (psDepandingValue.startsWith("D")) {
			GtnUIFrameworkComponentConfig textComponentConfig = addFieldFactoryField(
					GtnUIFrameworkComponentType.DATEFIELD,
					componentConfig.getGtnUIFrameWorkValueChangeActionConfigList());
			textComponentConfig.setGtnTextBoxConfig(new GtnUIFrameworkTextBoxConfig());
			textComponentConfig.setRebuild(true);
			return textComponentConfig;
		}
		if (psDepandingValue.startsWith("M")) {
			return addFieldFactoryTextField(GtnUIFrameworkComponentType.TEXTBOX,
					componentConfig.getGtnUIFrameWorkValueChangeActionConfigList());
		}
		return null;

	}

	private GtnUIFrameworkComponentConfig addFieldFactoryTextField(GtnUIFrameworkComponentType textbox,
			List<GtnUIFrameWorkActionConfig> gtnUIFrameWorkValueChangeActionConfigList) {
		GtnUIFrameworkComponentConfig textComponentConfig = addFieldFactoryField(textbox,
				gtnUIFrameWorkValueChangeActionConfigList);
		textComponentConfig.setGtnTextBoxConfig(new GtnUIFrameworkTextBoxConfig());
		textComponentConfig.setRebuild(true);
		return textComponentConfig;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
