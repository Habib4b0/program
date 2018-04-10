package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.checkboxgroup;

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
import com.vaadin.data.HasValue;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.CheckBoxGroup;

public class GtnUIFrameworkCheckBoxGroupComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkCheckBoxGroupComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Entering into the buildVaadinComponent( ) of GtnUIFrameworkCheckBoxGroupComponent");

		CheckBoxGroup vaadinCheckBoxGroup = new CheckBoxGroup();
		if (componentConfig.getComponentName() != null && !componentConfig.getComponentName().isEmpty()) {
			vaadinCheckBoxGroup.setCaption(componentConfig.getComponentName());
		}

		try {

			GtnUIFrameworkOptionGroupConfig checkBoxOptionConfig = componentConfig.getGtnUIFrameworkOptionGroupConfig();
			setVaadinCheckBoxGroupStyles(componentConfig, vaadinCheckBoxGroup, checkBoxOptionConfig);
			if (checkBoxOptionConfig.isValuesFromService()) {
				GtnUIFrameworkWebServiceClient checkBoxWsclient = new GtnUIFrameworkWebServiceClient();
				GtnUIFrameworkWebserviceRequest checkBoxRequest = new GtnUIFrameworkWebserviceRequest();
				GtnWsGeneralRequest checkBoxGeneralWSRequest = new GtnWsGeneralRequest();
				checkBoxGeneralWSRequest.setComboBoxType(checkBoxOptionConfig.getOptionGroupType());
				checkBoxRequest.setGtnWsGeneralRequest(checkBoxGeneralWSRequest);
				GtnUIFrameworkWebserviceComboBoxResponse checkBoxResponse = checkBoxWsclient
						.callGtnWebServiceUrl(checkBoxOptionConfig.getLoadingUrl(), checkBoxRequest,
								GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
						.getGtnUIFrameworkWebserviceComboBoxResponse();

				if (checkBoxResponse.getItemCodeList() != null) {
					CheckBoxGroup vaadinCheckBoxGroupResponse = new CheckBoxGroup<>(componentConfig.getComponentName(),
							checkBoxResponse.getItemCodeList());
					vaadinCheckBoxGroupResponse
							.setItemCaptionGenerator(item -> checkBoxResponse.getItemValueList().get((int) item));
					setVaadinCheckBoxGroupStyles(componentConfig, vaadinCheckBoxGroupResponse, checkBoxOptionConfig);
					addValueChangeListnerVaadinCheckBoxGroup(componentConfig, vaadinCheckBoxGroupResponse);
					gtnLogger.info("End into the buildVaadinComponent( ) of GtnUIFrameworkCheckBoxGroupComponent");
					return vaadinCheckBoxGroupResponse;
				}
			}

			if (!checkBoxOptionConfig.isValuesFromService()) {
				vaadinCheckBoxGroup.setItems(checkBoxOptionConfig.getItemValues());
				if (checkBoxOptionConfig.getDefaultSelection() != null
						&& !"".equals(checkBoxOptionConfig.getDefaultSelection())) {
					vaadinCheckBoxGroup.select(checkBoxOptionConfig.getDefaultSelection());
				}
			}

			addValueChangeListnerVaadinCheckBoxGroup(componentConfig, vaadinCheckBoxGroup);
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage());
		}
		gtnLogger.info("End into the buildVaadinComponent( ) of GtnUIFrameworkCheckBoxGroupComponent");
		return vaadinCheckBoxGroup;
	}

	private void addValueChangeListnerVaadinCheckBoxGroup(GtnUIFrameworkComponentConfig componentConfig,
			CheckBoxGroup vaadinCheckBoxGroup) {
		if (componentConfig.getGtnUIFrameWorkActionConfigList() != null) {
			vaadinCheckBoxGroup.addValueChangeListener(new HasValue.ValueChangeListener() {
				@Override
				public void valueChange(HasValue.ValueChangeEvent event) {
					try {
						GtnUIFrameworkActionExecutor.executeActionList(componentConfig.getComponentId(),
								componentConfig.getGtnUIFrameWorkActionConfigList());
					} catch (GtnFrameworkGeneralException ex) {
						gtnLogger.error("Error While Changiing option group value :", ex);

					}
				}
			});
		}
	}

	private void setVaadinCheckBoxGroupStyles(GtnUIFrameworkComponentConfig componentConfig,
			CheckBoxGroup vaadinCheckBoxGroup, GtnUIFrameworkOptionGroupConfig optionConfig) {
		for (String checkBoxStyle : componentConfig.getComponentStyle()) {
			vaadinCheckBoxGroup.addStyleName(checkBoxStyle);
		}
		vaadinCheckBoxGroup.setVisible(componentConfig.isVisible());
		vaadinCheckBoxGroup.setEnabled(optionConfig.isEnable());
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		CheckBoxGroup vaadinOptiongroup = (CheckBoxGroup) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		GtnUIFrameworkOptionGroupConfig checkBoxOptionConfig = componentConfig.getGtnUIFrameworkOptionGroupConfig();
		vaadinOptiongroup.setVisible(componentConfig.isVisible());
		vaadinOptiongroup.setEnabled(checkBoxOptionConfig.isEnable());
		if (checkBoxOptionConfig.getDefaultSelection() == null) {
			vaadinOptiongroup.select(checkBoxOptionConfig.getItemValues().get(0));
		} else if (!"".equals(checkBoxOptionConfig.getDefaultSelection())) {
			vaadinOptiongroup.select(checkBoxOptionConfig.getDefaultSelection());
		}

	}
}
