/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config.action;

import com.stpl.gtn.gtn2o.ui.customview.constants.GtnFrameworkCVConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import java.util.List;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnFrameworkOptionGroupChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
 private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkOptionGroupChangeAction.class);
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
              try
              {
       		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
		String optionValue = component.getStringFromField();
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(2).toString());
		GtnUIFrameworkBaseComponent tableBaseLayout = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());

		GtnUIFrameworkBaseComponent customTreePanel = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(4).toString());

		GtnUIFrameworkBaseComponent customTreeLayout = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(5).toString());

		GtnUIFrameworkBaseComponent customTreeVerticalLayout = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(6).toString());

		GtnUIFrameworkBaseComponent customTreeConstructPanel = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(7).toString());

		GtnUIFrameworkBaseComponent customTreeV001Layout = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(8).toString());

		if (GtnFrameworkCVConstants.CUSTOM_VIEW_OPTION_DISCOUNT.equals(optionValue)) {
			tableBaseComponent.setVisible(true);
			tableBaseLayout.setVisible(true);
			customTreePanel.getComponent().setHeight(GtnFrameworkCssConstants.PIXEL_1500);
			customTreeLayout.getComponent().setHeight(GtnFrameworkCssConstants.PIXEL_1500);
			customTreeVerticalLayout.getComponent().setHeight(GtnFrameworkCssConstants.PIXEL_1500);
			customTreeConstructPanel.getComponent().setHeight("1457px");
			customTreeV001Layout.getComponent().setHeight(GtnFrameworkCssConstants.PIXEL_1500);
		} else {
			tableBaseComponent.setVisible(false);
			tableBaseLayout.setVisible(false);
		}
	}
              catch(Exception ex)
    {
        LOGGER.error("message",ex);
    }
    }

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
