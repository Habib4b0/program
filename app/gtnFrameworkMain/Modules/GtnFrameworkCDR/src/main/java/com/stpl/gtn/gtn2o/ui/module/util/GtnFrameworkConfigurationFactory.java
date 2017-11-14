package com.stpl.gtn.gtn2o.ui.module.util;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;

public class GtnFrameworkConfigurationFactory {

	private String viewName;
	private String viewId;
	private boolean isdefaultView;

	public GtnUIFrameworkViewConfig buildView() {
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewId(getViewId());
		view.setViewName(getViewName());
		view.setDefaultView(isIsdefaultView());
		return view;
	}

	public GtnUIFrameworkComponentConfig buildComponentConfig(final String componetId, final String componeentName,
			final boolean addToParent, final GtnUIFrameworkComponentType type) {
		GtnUIFrameworkComponentConfig componentConfig = new GtnUIFrameworkComponentConfig();
		componentConfig.setComponentId(componetId);
		componentConfig.setComponentName(componeentName);
		componentConfig.setAddToParent(addToParent);
		componentConfig.setComponentType(type);
		return componentConfig;
	}

	public GtnUIFrameworkLayoutConfig buildLayoutConfig(final GtnUIFrameworkLayoutType type) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(type);
		return layout;
	}

	public GtnUIFrameWorkActionConfig buildActionConfig(final GtnUIFrameworkActionType type) {

		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		actionConfig.setActionType(type);
		return actionConfig;

	}

	public GtnUIFrameworkComboBoxConfig buildComboBoxConfig(final String type, final String url) {
		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setComboBoxType(type);
		comboBoxConfig.setLoadingUrl(url);
		return comboBoxConfig;
	}

	public GtnUIFrameworkTextBoxConfig buildTextBoxConfig(final boolean enabled, final boolean required,
			final boolean immediate) {
		GtnUIFrameworkTextBoxConfig textBoxConfig = new GtnUIFrameworkTextBoxConfig();
		textBoxConfig.setEnable(enabled);
		textBoxConfig.setRequired(required);
		textBoxConfig.setImmediate(immediate);
		return textBoxConfig;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public boolean isIsdefaultView() {
		return isdefaultView;
	}

	public void setIsdefaultView(boolean isdefaultView) {
		this.isdefaultView = isdefaultView;
	}
}
