package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUiFrameworkComboBoxSourceType;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.bean.SessioBeanForCombobox;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkClassLoader;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.vaadin.data.HasValue;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;

public class GtnUIFrameworkComboBoxComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkComboBoxComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {

		try {

			final GtnUIFrameworkComboBoxConfig comboboxConfig = componentConfig.getGtnComboboxConfig();
			ComboBox vaadinComboBox = fillComboBox(comboboxConfig, null, componentConfig.getSourceViewId());

			if (componentConfig.getComponentName() != null && !componentConfig.getComponentName().isEmpty() && vaadinComboBox!= null) {
				vaadinComboBox.setCaption(componentConfig.getComponentName());
			}

			loadStyles(vaadinComboBox, componentConfig.getComponentStyle());
			if (vaadinComboBox!= null) {
			vaadinComboBox.setEnabled(componentConfig.isEnable());
			
			vaadinComboBox.setVisible(componentConfig.isVisible());
			vaadinComboBox.setReadOnly(comboboxConfig.isReadOnly());
			vaadinComboBox.setRequiredIndicatorVisible(comboboxConfig.isRequired());
			vaadinComboBox.setEmptySelectionAllowed(false);

			if (comboboxConfig.getValueChangeListenerClassName() != null) {
				GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
				vaadinComboBox.addValueChangeListener((ValueChangeListener) (classLoader
						.loadDynamicClass(comboboxConfig.getValueChangeListenerClassName())));
			}

			if (componentConfig.getGtnUIFrameWorkActionConfigList() != null
					&& !componentConfig.getGtnUIFrameWorkActionConfigList().isEmpty()) {
				vaadinComboBox.addValueChangeListener(new ValueChangeListener() {
					@Override
					public void valueChange(HasValue.ValueChangeEvent event) {
						try {
							GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
									comboboxConfig.getSourceComboboxId(), componentConfig.getSourceViewId());
							AbstractComponent sourceComponent = baseComponent.getComponent();
							GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) sourceComponent
									.getData();
							GtnUIFrameworkComponentConfig sourceComponentConfig = componentData
									.getCurrentComponentConfig();
							GtnUIFrameworkActionExecutor.executeActionList(componentData.getComponentIdInMap(),
									sourceComponentConfig.getGtnUIFrameWorkActionConfigList());

						} catch (GtnFrameworkGeneralException e) {
							gtnLogger.error(e.getMessage(), e);
						}
					}
				});
			}

			setDefaultFocus(vaadinComboBox, componentConfig);
			return vaadinComboBox;
			}
		} catch (GtnFrameworkGeneralException ex) {

			gtnLogger.error(ex.getMessage(), ex);

		}

		return null;
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		GtnUIFrameworkComponentConfig comboComponentConfig = getComboBoxComponentConfig(dependentComponentId,
				componentId);
		reloadComboBoxComponent(dependentComponentId, componentId, reloadInput, comboComponentConfig.getSourceViewId(),
				comboComponentConfig);

	}

	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			String sourceViewId, Object reloadInput) {

		gtnLogger.info("Triggered reload with reload input " + reloadInput + "--Action--" + action);
		GtnUIFrameworkComponentConfig comboComponentConfig = getComboBoxComponentConfig(dependentComponentId,
				componentId);
		reloadComboBoxComponent(dependentComponentId, componentId, reloadInput, sourceViewId, comboComponentConfig);

	}

	private GtnUIFrameworkComponentConfig getComboBoxComponentConfig(String dependentComponentId, String componentId) {
		AbstractComponent abstractComboComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(dependentComponentId,
				componentId);
		GtnUIFrameworkComponentData comboComponentData = (GtnUIFrameworkComponentData) abstractComboComponent.getData();
		return comboComponentData.getCurrentComponentConfig();

	}

	@SuppressWarnings("unchecked")
	private void reloadComboBoxComponent(String dependentComponentId, String componentId, Object reloadInput,
			String sourceViewId, GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Triggered reload with reload input " + reloadInput);
		List<Object> comboBoxRequestInputList = (List<Object>) reloadInput;
		String simpleComboBoxReload = String.valueOf(comboBoxRequestInputList.get(0));
		if (!"simpleReload".equals(simpleComboBoxReload)) {
			if (componentConfig.getReloadLogicActionClassName() != null) {
				try {
					GtnUIFrameworkClassLoader comboBoxClassLoader = new GtnUIFrameworkClassLoader();
					GtnUIFrameWorkAction comboBoxCustomAction = (GtnUIFrameWorkAction) comboBoxClassLoader
							.loadDynamicClass(componentConfig.getReloadLogicActionClassName());
					GtnUIFrameWorkActionConfig comboBoxActionConfig = new GtnUIFrameWorkActionConfig();
					comboBoxActionConfig.addActionParameter(reloadInput);
					comboBoxCustomAction.doAction(componentId, comboBoxActionConfig);
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error(componentId, ex);
				}
				return;
			}

		} else {
			comboBoxRequestInputList.set(0, "");
		}
		GtnUIFrameworkComboBoxConfig comboboxComponentConfig = componentConfig.getGtnComboboxConfig();

		ComboBox vaadinComboBoxComponent = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent(dependentComponentId,
				componentId);
		vaadinComboBoxComponent.setItems(new ArrayList<>());

		vaadinComboBoxComponent = fillComboBox(comboboxComponentConfig, comboBoxRequestInputList, sourceViewId);
		gtnLogger.info("Reloaded vaadin combobox" + vaadinComboBoxComponent);
		
	}

	private ComboBox fillComboBox(GtnUIFrameworkComboBoxConfig comboboxConfig,
			List<Object> comboBoxWhereClauseParamList, String sourceViewId) {

		GtnUIFrameworkWebserviceComboBoxResponse response = getResponseFromSource(comboboxConfig,
				comboBoxWhereClauseParamList, sourceViewId);
		ComboBox vaadinCombobox;

		vaadinCombobox = fillComboboxFromResponse(response, comboboxConfig);

		if ( vaadinCombobox !=null && comboboxConfig.getItemValues() != null ) {
			vaadinCombobox.setItems(comboboxConfig.getItemValues());
			if (comboboxConfig.getItemCaptionValues() != null) {

				vaadinCombobox = fillDataAndCaption(comboboxConfig, comboboxConfig.getItemValues(),
						comboboxConfig.getItemCaptionValues());
			} else {
				vaadinCombobox = fillDataAndCaption(comboboxConfig, comboboxConfig.getItemValues(),
						comboboxConfig.getItemValues());
			}
		}
		return vaadinCombobox;
	}

	private ComboBox fillComboboxFromResponse(GtnUIFrameworkWebserviceComboBoxResponse response,
			GtnUIFrameworkComboBoxConfig comboboxConfig) {
		ComboBox vaadinCombobox;
		if (response != null && response.getItemCodeList() != null && !response.getItemCodeList().isEmpty()) {
			if (comboboxConfig.isIntegerItemCode()) {
				List<Integer> integerList = new ArrayList<>();
				for (String str : response.getItemCodeList()) {
					integerList.add(Integer.parseInt(str));
				}
				vaadinCombobox = fillDataAndCaption(comboboxConfig, integerList, response.getItemValueList());
			} else {
				vaadinCombobox = fillDataAndCaption(comboboxConfig, response.getItemCodeList(),
						response.getItemValueList());
			}
			return vaadinCombobox;
		}
		return null;
	}

	private ComboBox fillDataAndCaption(GtnUIFrameworkComboBoxConfig comboboxConfig, List itemCodeList,
			List<String> itemValueList) {
		List valueList = new ArrayList<>(itemCodeList);
		List<String> captionList = new ArrayList<>(itemValueList);
		ComboBox vaadin8ComboBox = null;
		SessioBeanForCombobox sessioBeanForVaadin8 = SessioBeanForCombobox.getInstance();
		sessioBeanForVaadin8.setCaptionList(captionList);
		sessioBeanForVaadin8.setValueList(valueList);
		if (!comboboxConfig.isHasDefaultValue()) {
			String defaultValue = comboboxConfig.getDefaultValue() != null
					? String.valueOf(comboboxConfig.getDefaultValue()) : GtnFrameworkCommonStringConstants.SELECT_ONE;
			valueList.add(0, 0);
			captionList.add(0, defaultValue);
		} 
		vaadin8ComboBox = new ComboBox("", valueList);
		vaadin8ComboBox.setItemCaptionGenerator(item -> captionList.get(valueList.indexOf(item)));

		if(comboboxConfig.isHasDefaultValue()) {
			for (int i = 0; i < captionList.size(); i++) {
				if (comboboxConfig.getDefaultDesc().equals(captionList.get(i))) {
					vaadin8ComboBox.setSelectedItem(valueList.get(i));
					break;
				}
			}
		}
		
		return vaadin8ComboBox;
	}

	private void loadStyles(final Component component, final List<String> comboboxStyles) {
		if (comboboxStyles != null) {
			for (String comboboxStyle : comboboxStyles) {
				component.addStyleName(comboboxStyle);
			}
		}
	}

	private GtnUIFrameworkWebserviceComboBoxResponse getResponseFromSource(GtnUIFrameworkComboBoxConfig comboboxConfig,
			List<Object> comboBoxWhereClauseParamList, String sourceViewId) {

		GtnUIFrameworkWebserviceComboBoxResponse comboboxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();

		if (comboboxConfig.getSourceType().equals(GtnUiFrameworkComboBoxSourceType.VALUES_FROM_SERVICE)) {
			return getResponseFromService(comboboxConfig, comboBoxWhereClauseParamList);
		}

		GtnUIFrameworkBaseComponent comboboxBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(comboboxConfig.getSourceComboboxId(), sourceViewId);

		if (comboboxBaseComponent == null) {
			return comboboxResponse;
		}
		return comboboxResponse;
	}

	private GtnUIFrameworkWebserviceComboBoxResponse getResponseFromService(GtnUIFrameworkComboBoxConfig comboboxConfig,
			List<Object> comboBoxWhereClauseParamList) {
		GtnUIFrameworkWebserviceComboBoxResponse comboboxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
		GtnUIFrameworkWebServiceClient comboboxWsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest comboboxRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest comboboxGeneralWSRequest = new GtnWsGeneralRequest();
		comboboxGeneralWSRequest.setComboBoxType(comboboxConfig.getComboBoxType());
		if (null != comboBoxWhereClauseParamList) {
			comboboxGeneralWSRequest.setComboBoxWhereclauseParamList(comboBoxWhereClauseParamList);
		}

		comboboxRequest.setGtnWsGeneralRequest(comboboxGeneralWSRequest);
		if (comboboxConfig.getLoadingUrl() != null) {
			comboboxResponse = comboboxWsclient
					.callGtnWebServiceUrl(comboboxConfig.getLoadingUrl(), comboboxRequest,
							GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
					.getGtnUIFrameworkWebserviceComboBoxResponse();
		}
		return comboboxResponse;
	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		ComboBox vaadinComboBox = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		vaadinComboBox.setEnabled(componentConfig.isEnable());
		GtnUIFrameworkComboBoxConfig comboboxConfig = componentConfig.getGtnComboboxConfig();
		vaadinComboBox.setRequiredIndicatorVisible(Boolean.TRUE);
		vaadinComboBox.setVisible(componentConfig.isVisible());
		SessioBeanForCombobox sessioBeanForVaadin8 = SessioBeanForCombobox.getInstance();
		List<String> captionList = sessioBeanForVaadin8.getCaptionList();
		List valueList = sessioBeanForVaadin8.getValueList();
		if (comboboxConfig.isHasDefaultValue()) {
			for (int i = 0; i < captionList.size(); i++) {
				if (comboboxConfig.getDefaultDesc().equals(captionList.get(i))) {
					vaadinComboBox.setSelectedItem(valueList.get(i));
					break;
				}
			}
			return;
		}
		vaadinComboBox.setSelectedItem(0);
	}

	private void setDefaultFocus(ComboBox vaadinComboBox, GtnUIFrameworkComponentConfig componentConfig) {
		if (componentConfig.isDefaultFocus()) {
			vaadinComboBox.focus();
		}
	}
}
