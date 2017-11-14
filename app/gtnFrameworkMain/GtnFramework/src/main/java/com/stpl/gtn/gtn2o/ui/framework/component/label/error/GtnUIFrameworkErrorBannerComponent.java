package com.stpl.gtn.gtn2o.ui.framework.component.label.error;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

public class GtnUIFrameworkErrorBannerComponent implements GtnUIFrameworkComponent {

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException {
		Label label = new Label();
		loadStyles(label);
		label.setVisible(false);
		label.setWidth(GtnFrameworkCssConstants.PERCENT_100);
		return label;

	}

	private void loadStyles(final Component component) {
		component.addStyleName(GtnFrameworkCssConstants.ERROR);

	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		Label errorBanner = (Label) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		if (errorBanner != null) {
			errorBanner.setVisible(false);
			errorBanner.setValue(null);
		}
	}

}
