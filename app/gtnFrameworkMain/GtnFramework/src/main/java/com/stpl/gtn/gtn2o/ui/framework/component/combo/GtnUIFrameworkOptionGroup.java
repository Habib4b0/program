package com.stpl.gtn.gtn2o.ui.framework.component.combo;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.vaadin.data.Property;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.OptionGroup;

public class GtnUIFrameworkOptionGroup implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkComboboxComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Entering into the buildVaadinComponent( ) of GtnUIFrameworkOptionGroup");

		OptionGroup vaadinOptiongroup = new OptionGroup();
		if (componentConfig.getComponentName() != null && !componentConfig.getComponentName().isEmpty()) {
			vaadinOptiongroup.setCaption(componentConfig.getComponentName());
		}

		try {

			GtnUIFrameworkOptionGroupConfig optionConfig = componentConfig.getGtnUIFrameworkOptionGroupConfig();
			for (String style : componentConfig.getComponentStyle()) {
				vaadinOptiongroup.addStyleName(style);
			}
			vaadinOptiongroup.setVisible(componentConfig.isVisible());
			vaadinOptiongroup.setEnabled(optionConfig.isEnable());
			vaadinOptiongroup.setMultiSelect(optionConfig.isIsMultiSelect());
			if (optionConfig.isValuesFromService()) {
				GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
				GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
				GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
				generalWSRequest.setComboBoxType(optionConfig.getOptionGroupType());
				request.setGtnWsGeneralRequest(generalWSRequest);
				GtnUIFrameworkWebserviceComboBoxResponse response = wsclient
						.callGtnWebServiceUrl(optionConfig.getLoadingUrl(), request,
								GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
						.getGtnUIFrameworkWebserviceComboBoxResponse();

				if (response.getItemCodeList() != null) {
					vaadinOptiongroup.addItems(response.getItemCodeList());
					for (int valueIndex = 0; valueIndex < response.getItemCodeList().size(); valueIndex++) {
						vaadinOptiongroup.setItemCaption(response.getItemCodeList().get(valueIndex),
								response.getItemValueList().get(valueIndex));
					}
					gtnLogger.info("End into the buildVaadinComponent( ) of GtnUIFrameworkOptionGroup");
					return vaadinOptiongroup;
				}
			}

			if (!optionConfig.isValuesFromService()) {
				vaadinOptiongroup.addItems(optionConfig.getItemValues());
				if (optionConfig.getDefaultSelection() != null && !"".equals(optionConfig.getDefaultSelection())) {
				vaadinOptiongroup.select(optionConfig.getDefaultSelection());
                                } 
			}

			if (componentConfig.getGtnUIFrameWorkActionConfigList() != null) {
				vaadinOptiongroup.addValueChangeListener(new OptionValueChangeListener(componentConfig));
			}
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage());
		}
		gtnLogger.info("End into the buildVaadinComponent( ) of GtnUIFrameworkOptionGroup");
		return vaadinOptiongroup;
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	class OptionValueChangeListener implements Property.ValueChangeListener {

		private static final long serialVersionUID = 1L;
		private GtnUIFrameworkComponentConfig componentConfig;

		public OptionValueChangeListener(GtnUIFrameworkComponentConfig componentConfig) {
			this.componentConfig = componentConfig;
		}

		@Override
		public void valueChange(Property.ValueChangeEvent event) {
			try {
				GtnUIFrameworkActionExecutor.executeActionList(componentConfig.getComponentId(),
						componentConfig.getGtnUIFrameWorkActionConfigList());
			} catch (GtnFrameworkGeneralException ex) {
				gtnLogger.error("Error While Changiing option group value :", ex);

			}
		}

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		OptionGroup vaadinOptiongroup = (OptionGroup) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		GtnUIFrameworkOptionGroupConfig optionConfig = componentConfig.getGtnUIFrameworkOptionGroupConfig();
		vaadinOptiongroup.setVisible(componentConfig.isVisible());
		vaadinOptiongroup.setEnabled(optionConfig.isEnable());
		vaadinOptiongroup.setMultiSelect(optionConfig.isIsMultiSelect());
		if (optionConfig.getDefaultSelection() == null) {
			vaadinOptiongroup.select(optionConfig.getItemValues().get(0));
		} else if (!"".equals(optionConfig.getDefaultSelection())) {
			vaadinOptiongroup.select(optionConfig.getDefaultSelection());
		}

	}
}
