package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.date;

import java.time.LocalDate;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.bean.ComponentBinderValidatorBean;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.date.validator.GtnUIFrameworkDateValidator;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.Binder;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;

public class GtnUIFrameworkDateFieldComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkDateFieldComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {

		gtnLogger.info("inside Date Component Vaadin8");
		DateField dateField = new DateField(componentConfig.getComponentName());
		Binder<ComponentBinderValidatorBean> dateFieldBinder = new Binder<>();
		loadStyles(dateField, componentConfig.getComponentStyle());
		dateField.setHeight(componentConfig.getComponentHight());
		dateField.setVisible(componentConfig.isVisible());
		dateField.setId(componentConfig.getComponentId());
		dateField.setDateFormat("MM/dd/yyyy");
		dateField.setEnabled(componentConfig.isEnable());
		dateField.setValue((LocalDate) componentConfig.getComponentValue());
		if (!(componentConfig.getDateFieldStyle().isEmpty())) {
			loadStyles(dateField, componentConfig.getDateFieldStyle());
		}
		GtnUIFrameworkDateFieldConfig dateFieldConfig = componentConfig.getGtnDateFieldConfig();
		if (dateFieldConfig != null) {

			if (dateFieldConfig.isRequired()) {
				dateFieldBinder.forField(dateField).asRequired(dateFieldConfig.getRequiredMessage()).bind(
						ComponentBinderValidatorBean::getDateRequiredField,
						ComponentBinderValidatorBean::setDateRequiredField);
			}
			dateField.setEnabled(dateFieldConfig.isEnable());
			if (dateFieldConfig.getValueChangeActionConfigList() != null
					&& !dateFieldConfig.getValueChangeActionConfigList().isEmpty()) {
				dateField.addValueChangeListener(e -> {
					try {
						for (GtnUIFrameWorkActionConfig actionConfig : componentConfig.getGtnDateFieldConfig()
								.getValueChangeActionConfigList()) {
							final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
							action.configureParams(actionConfig);
							action.doAction(componentConfig.getComponentId(), actionConfig);
						}

					} catch (GtnFrameworkGeneralException ex) {
						gtnLogger.error(ex.getMessage(), ex);
					}
				});
			}

		}
		return dateField;
	}

	private void loadStyles(final Component component, final List<String> stylesList) {
		if (stylesList != null) {
			for (String style : stylesList) {
				component.addStyleName(style);
			}
		}
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("reset to default method");
		DateField dateField = (DateField) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		dateField.setVisible(componentConfig.isVisible());
		dateField.setDateFormat("MM/dd/yyyy");
		dateField.setEnabled(componentConfig.isEnable());
		GtnUIFrameworkDateFieldConfig dateFieldConfig = componentConfig.getGtnDateFieldConfig();
		Binder<ComponentBinderValidatorBean> dateFieldBinder = new Binder<>();
		if (dateFieldConfig != null) {
			if (dateFieldConfig.isRequired()) {
				dateFieldBinder.forField(dateField).asRequired(dateFieldConfig.getRequiredMessage()).bind(
						ComponentBinderValidatorBean::getDateRequiredField,
						ComponentBinderValidatorBean::setDateRequiredField);
			}
			dateField.setEnabled(dateFieldConfig.isEnable());
			addDateValidator(dateField, dateFieldBinder, dateFieldConfig);
		}
		dateField.setValue((LocalDate) componentConfig.getComponentValue());
	}

	private void addDateValidator(DateField dateField, Binder<ComponentBinderValidatorBean> dateFieldBinder,
			GtnUIFrameworkDateFieldConfig dateFieldConfig) {
		gtnLogger.info("Validator");
		dateFieldBinder.forField(dateField).withValidator(
				new GtnUIFrameworkDateValidator(dateField.getId(), dateFieldConfig.getValidationActionConfigList()));

	}
}
