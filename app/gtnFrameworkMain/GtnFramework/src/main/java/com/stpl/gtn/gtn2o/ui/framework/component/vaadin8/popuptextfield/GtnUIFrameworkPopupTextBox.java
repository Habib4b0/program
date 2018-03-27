package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.popuptextfield;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.bean.ComponentBinderValidatorBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.Binder;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class GtnUIFrameworkPopupTextBox implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPopupTextBox.class);

	@Override
	public AbstractComponent buildVaadinComponent(final GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Entering into the buildVaadinComponent () of GtnUIFrameworkPopupTextField ");
		Binder<ComponentBinderValidatorBean> textBoxBinder = new Binder<>();
		TextField popupTextField = new TextField();
		if (componentConfig.getComponentName() != null && !componentConfig.getComponentName().isEmpty()) {
			popupTextField.setCaption(componentConfig.getComponentName());
		}
		textBoxBinder.forField(popupTextField).withNullRepresentation(GtnFrameworkCommonStringConstants.STRING_EMPTY)
				.bind(ComponentBinderValidatorBean::getWithNullRepresentation,
						ComponentBinderValidatorBean::setWithNullRepresentation);
		GtnUIFrameworkTextBoxConfig textboxConfig = componentConfig.getGtnTextBoxConfig();
		popupTextField.setVisible(componentConfig.isVisible());
		if (textboxConfig != null) {
			if (textboxConfig.isRequired()) {
				textBoxBinder.forField(popupTextField).asRequired(textboxConfig.getRequiredMessage()).bind(
						ComponentBinderValidatorBean::getRequiredField, ComponentBinderValidatorBean::setRequiredField);
			}
			popupTextField.setEnabled(textboxConfig.isEnable());
			popupTextField.setReadOnly(textboxConfig.isReadOnly());
			if (textboxConfig.isNullSettingAllowed()) {
				textBoxBinder.forField(popupTextField).withNullRepresentation(textboxConfig.getNullRepresentation())
						.bind(ComponentBinderValidatorBean::getWithNullRepresentation,
								ComponentBinderValidatorBean::setWithNullRepresentation);
			}
		}

		popupTextField.addStyleName(GtnFrameworkCssConstants.SEARCHICON);
		loadStyles(popupTextField, componentConfig.getComponentStyle());
		gtnLogger.info("End into the buildVaadinComponent () of GtnUIFrameworkPopupTextField ");
		postCreateComponent(popupTextField, componentConfig);
		return popupTextField;
	}

	@Override
	public void postCreateComponent(AbstractComponent component, final GtnUIFrameworkComponentConfig componentConfig) {
		TextField popupTextField = (TextField) component;
		popupTextField.setVisible(componentConfig.isVisible());
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
		final String componentId = componentData.getComponentIdInMap();

		new HorizontalLayout().addLayoutClickListener(new LayoutClickListener() {
			@Override
			public void layoutClick(LayoutClickEvent event) {
				try {
					if (event.getChildComponent() == popupTextField) {
						for (GtnUIFrameWorkActionConfig actionConfig : componentConfig
								.getGtnUIFrameWorkActionConfigList()) {
							final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
							action.configureParams(actionConfig);
							action.doAction(componentId, actionConfig);
						}
					}
				} catch (GtnFrameworkGeneralException e) {
					gtnLogger.error(e.getMessage(), e);
				}
			}
		});
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	private void loadStyles(final Component component, final List<String> styles) {

		if (styles != null) {

			for (String style : styles) {

				component.addStyleName(style);

			}
		}

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		TextField popupTextField = (TextField) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		popupTextField.setVisible(componentConfig.isVisible());
		String value = "";
		if (componentConfig.getComponentValue() != null) {
			value = String.valueOf(componentConfig.getComponentValue());
		}
		boolean isReadOnly = popupTextField.isReadOnly();
		popupTextField.setReadOnly(false);
		popupTextField.setValue(value);
		popupTextField.setReadOnly(isReadOnly);
	}

}
