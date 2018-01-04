package com.stpl.gtn.gtn2o.ui.framework.component.date;

import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.validator.GtnFrameworkDateValidator;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.v7.data.Property;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.PopupDateField;

public class GtnUIFrameworkDateComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkDateComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {

		PopupDateField dateField = new PopupDateField(componentConfig.getComponentName());
		loadStyles(dateField, componentConfig.getComponentStyle());
		dateField.setVisible(componentConfig.isVisible());
		dateField.setId(componentConfig.getComponentId());
		dateField.setDateFormat("MM/dd/yyyy");
		dateField.setEnabled(componentConfig.isEnable());
		dateField.setValue((Date) componentConfig.getComponentValue());
		GtnUIFrameworkDateFieldConfig dateFieldConfig = componentConfig.getGtnDateFieldConfig();
		if (dateFieldConfig != null) {
			dateField.setRequired(dateFieldConfig.isRequired());
			dateField.setRequiredError(dateFieldConfig.getRequiredMessage());
			dateField.setEnabled(dateFieldConfig.isEnable());
			dateField.setImmediate(dateFieldConfig.isImmediate());
			if (dateFieldConfig.getValueChangeActionConfigList() != null
					&& !dateFieldConfig.getValueChangeActionConfigList().isEmpty()) {
				dateField.addValueChangeListener(new DateFieldValueChangeListener(componentConfig));
			}

		}
		return dateField;
	}

	private void loadStyles(final Component component, final List<String> styles) {

		if (styles != null) {

			for (String style : styles) {

				component.addStyleName(style);

			}
		}

	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		PopupDateField dateField = (PopupDateField) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		dateField.setVisible(componentConfig.isVisible());
		dateField.setDateFormat("MM/dd/yyyy");
		dateField.setEnabled(componentConfig.isEnable());
		GtnUIFrameworkDateFieldConfig dateFieldConfig = componentConfig.getGtnDateFieldConfig();
		if (dateFieldConfig != null) {
			dateField.setRequired(dateFieldConfig.isRequired());
			dateField.setRequiredError(dateFieldConfig.getRequiredMessage());
			dateField.setEnabled(dateFieldConfig.isEnable());
			dateField.setImmediate(dateFieldConfig.isImmediate());
			dateField.removeAllValidators();
			addDateValidator(dateField, dateFieldConfig);
		}
		dateField.setValue((Date) componentConfig.getComponentValue());
	}

	private void addDateValidator(PopupDateField dateField, GtnUIFrameworkDateFieldConfig dateFieldConfig) {
		dateField.addValidator(
				new GtnFrameworkDateValidator(dateField.getId(), dateFieldConfig.getValidationActionConfigList()));

	}

	class DateFieldValueChangeListener implements Property.ValueChangeListener {

		private static final long serialVersionUID = 1L;

		private GtnUIFrameworkComponentConfig componentConfig;

		public DateFieldValueChangeListener(GtnUIFrameworkComponentConfig componentConfig) {
			this.componentConfig = componentConfig;
		}

		@Override
		public void valueChange(Property.ValueChangeEvent event) {
			try {
				for (GtnUIFrameWorkActionConfig actionConfig : componentConfig.getGtnDateFieldConfig()
						.getValueChangeActionConfigList()) {
					final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
					action.configureParams(actionConfig);
					action.doAction(componentConfig.getComponentId(), actionConfig);
				}

			} catch (GtnFrameworkGeneralException e) {
				gtnLogger.error(e.getMessage(), e);
			}
		}

	}

}
