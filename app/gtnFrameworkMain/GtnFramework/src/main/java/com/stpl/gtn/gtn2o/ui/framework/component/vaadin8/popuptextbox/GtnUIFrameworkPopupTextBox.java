package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.popuptextbox;


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

	private HorizontalLayout popupTextBoxHorizontalComponent;

	@Override
	public AbstractComponent buildVaadinComponent(final GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Entering into the buildVaadinComponent () of GtnUIFrameworkPopupTextField ");
		Binder<ComponentBinderValidatorBean> textBoxBinder = new Binder<>();
		TextField popupTextField = new TextField();
		popupTextField.setId(componentConfig.getComponentId());
		popupTextBoxHorizontalComponent = new HorizontalLayout();
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
		popupTextBoxHorizontalComponent.addComponent(popupTextField);
		gtnLogger.info("End into the buildVaadinComponent () of GtnUIFrameworkPopupTextField ");
		return popupTextBoxHorizontalComponent;
	}

	@Override
	public void postCreateComponent(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {

		HorizontalLayout postCreateHorizontalLayout = (HorizontalLayout) component;
		TextField popupTextField = (TextField) postCreateHorizontalLayout.getComponent(0);
		popupTextField.setVisible(componentConfig.isVisible());
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
		final String componentId = componentData.getComponentIdInMap();
		popupTextBoxHorizontalComponent.addLayoutClickListener(new LayoutClickListener() {

			@Override
			public void layoutClick(LayoutClickEvent event) {
				try {
					if (event.getChildComponent() == popupTextField) {
						for (GtnUIFrameWorkActionConfig popupActionConfig : componentConfig
								.getGtnUIFrameWorkActionConfigList()) {
							final GtnUIFrameWorkAction popupAction = popupActionConfig.getActionType().getGtnUIFrameWorkAction();
							popupAction.configureParams(popupActionConfig);
							popupAction.doAction(componentId, popupActionConfig);
						}
					}

				} catch (GtnFrameworkGeneralException e) {
					gtnLogger.error(e.getMessage(), e);
				}
			}
		});
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType popupAction, String popupDependentComponentId, String popupComponentId,
			Object popupReloadInput) {
		return;

	}

	private void loadStyles(final Component popupComponent, final List<String> popupStyles) {

		if (popupStyles != null) {

			for (String popupStyle : popupStyles) {

				popupComponent.addStyleName(popupStyle);

			}
		}

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		try {
			HorizontalLayout resetToDefaultHorizontalComponent = (HorizontalLayout) GtnUIFrameworkGlobalUI
					.getVaadinComponent(componentId);
			TextField popupTextField = (TextField) resetToDefaultHorizontalComponent.getComponent(0);
			popupTextField.setVisible(componentConfig.isVisible());
			String value = "";
			if (componentConfig.getComponentValue() != null) {
				value = String.valueOf(componentConfig.getComponentValue());
			}
			boolean isReadOnly = popupTextField.isReadOnly();
			popupTextField.setReadOnly(false);
			popupTextField.setValue(value);
			popupTextField.setReadOnly(isReadOnly);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

}