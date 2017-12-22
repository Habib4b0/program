package com.stpl.gtn.gtn2o.ui.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.v7.ui.ComboBox;

public class GtnUIFrameworkMassUpdateFiledDdlbValidateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnUIFrameworkMassUpdateFiledDdlbValidateAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info("inside GtnUIFrameworkMassUpdateFiledDdlbValidateAction");
		AbstractComponent abstractMassUpdateFieldComponentData = GtnUIFrameworkGlobalUI.getVaadinComponent(
				String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1)), componentId);
		ComboBox massUpdateField = (ComboBox) abstractMassUpdateFieldComponentData;

		AbstractComponent abstractMassUpdateLevelLayoutComponentData = GtnUIFrameworkGlobalUI.getVaadinComponent(
				String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(2)), componentId);
		AbstractLayout vaadinLayout = (AbstractLayout) abstractMassUpdateLevelLayoutComponentData;

		if (GtnFrameworkForecastConstantCommon.MASS_UPDATE_TAB_FIELD_TYPE_GROWTH_RATE
				.equals(massUpdateField.getItemCaption(massUpdateField.getValue()))) {
			vaadinLayout.setVisible(false);
		} else {
			vaadinLayout.setVisible(true);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
