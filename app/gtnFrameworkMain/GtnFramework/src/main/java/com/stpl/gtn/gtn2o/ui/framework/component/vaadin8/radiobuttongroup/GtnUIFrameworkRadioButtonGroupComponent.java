package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.radiobuttongroup;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.RadioButtonGroup;

public class GtnUIFrameworkRadioButtonGroupComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkRadioButtonGroupComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Entering into the buildVaadinComponent( ) of GtnUIFrameworkOptionGroup");

		RadioButtonGroup vaadinRadioButtonGroup = new RadioButtonGroup();
		if (componentConfig.getComponentName() != null && !componentConfig.getComponentName().isEmpty()) {
			vaadinRadioButtonGroup.setCaption(componentConfig.getComponentName());
		}

		try {

			GtnUIFrameworkOptionGroupConfig optionConfig = componentConfig.getGtnUIFrameworkOptionGroupConfig();
			setVaadinRadioButtonGroupStyles(componentConfig, vaadinRadioButtonGroup, optionConfig);
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
					RadioButtonGroup vaadinRadioButtonGroupResponse = new RadioButtonGroup<>(
							componentConfig.getComponentName(), response.getItemCodeList());
					vaadinRadioButtonGroupResponse
							.setItemCaptionGenerator(item -> response.getItemValueList().get((int) item));
					setVaadinRadioButtonGroupStyles(componentConfig, vaadinRadioButtonGroupResponse, optionConfig);
					addValueChangeListnerVaadinRadioButtonGroup(componentConfig, vaadinRadioButtonGroupResponse);
					gtnLogger.info("End into the buildVaadinComponent( ) of GtnUIFrameworkOptionGroup");
					return vaadinRadioButtonGroupResponse;
				}
			}

			if (!optionConfig.isValuesFromService()) {
				vaadinRadioButtonGroup.setItems(optionConfig.getItemValues());
				if (optionConfig.getDefaultSelection() != null && !"".equals(optionConfig.getDefaultSelection())) {
					vaadinRadioButtonGroup.setSelectedItem(optionConfig.getDefaultSelection());
				}
			}

			addValueChangeListnerVaadinRadioButtonGroup(componentConfig, vaadinRadioButtonGroup);
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage());
		}
		gtnLogger.info("End into the buildVaadinComponent( ) of GtnUIFrameworkOptionGroup");
		return vaadinRadioButtonGroup;
	}

	private void addValueChangeListnerVaadinRadioButtonGroup(GtnUIFrameworkComponentConfig componentConfig,
			RadioButtonGroup vaadinRadioButtonGroup) {
		if (componentConfig.getGtnUIFrameWorkActionConfigList() != null) {
			vaadinRadioButtonGroup.addValueChangeListener(e -> {
				try {
					componentConfig.setUserOriginatedFlag(e.isUserOriginated());
					GtnUIFrameworkActionExecutor.executeActionList(componentConfig.getComponentId(),
							componentConfig.getGtnUIFrameWorkActionConfigList());
					componentConfig.setUserOriginatedFlag(false);
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error("Error While Changiing option group value :", ex);

				}
			});
		}
	}

	private void setVaadinRadioButtonGroupStyles(GtnUIFrameworkComponentConfig componentConfig,
			RadioButtonGroup vaadinRadioButtonGroup, GtnUIFrameworkOptionGroupConfig optionConfig) {
		for (String style : componentConfig.getComponentStyle()) {
			vaadinRadioButtonGroup.addStyleName(style);
		}
		vaadinRadioButtonGroup.setVisible(componentConfig.isVisible());
		vaadinRadioButtonGroup.setEnabled(optionConfig.isEnable());
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		RadioButtonGroup vaadinOptiongroup = (RadioButtonGroup) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		GtnUIFrameworkOptionGroupConfig optionConfig = componentConfig.getGtnUIFrameworkOptionGroupConfig();
		vaadinOptiongroup.setItems(optionConfig.getItemValues());
		vaadinOptiongroup.setVisible(componentConfig.isVisible());
		vaadinOptiongroup.setEnabled(optionConfig.isEnable());
		if (optionConfig.getDefaultSelection() == null) {
			vaadinOptiongroup.setSelectedItem(optionConfig.getItemValues().get(0));
		} else if (!"".equals(optionConfig.getDefaultSelection())) {
			vaadinOptiongroup.setSelectedItem(optionConfig.getDefaultSelection());
		}

	}
}
