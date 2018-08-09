package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUiFrameworkComboBoxSourceType;
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
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;

public class GtnUIFrameworkComboBoxComponent implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {
	private static final String TRIGGERED_RELOAD_WITH_RELOAD_INPUT = "Triggered reload with reload input ";
	private static final String SIMPLE_RELOAD = "simpleReload";

	private static final String RELOADED_VAADIN_COMBOBOX = "Reloaded vaadin combobox";

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkComboBoxComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		ComboBox vaadinComboBox = new ComboBox(componentConfig.getComponentName());
		vaadinComboBox = generateComboBox(componentConfig, vaadinComboBox);
		setComponentData(vaadinComboBox, componentConfig);
		return vaadinComboBox;
	}

	private ComboBox generateComboBox(GtnUIFrameworkComponentConfig componentConfig, ComboBox vaadinComboBox) {
		try {

			final GtnUIFrameworkComboBoxConfig comboboxConfig = componentConfig.getGtnComboboxConfig();
			if (componentConfig.getComponentName() != null && !componentConfig.getComponentName().isEmpty()) {
				vaadinComboBox.setCaption(componentConfig.getComponentName());
			}
			loadStyles(vaadinComboBox, componentConfig.getComponentStyle());
                        if(!componentConfig.getComboBoxComponentStyle().isEmpty()){
                        loadStyles(vaadinComboBox, componentConfig.getComboBoxComponentStyle());
                        }

			if (vaadinComboBox != null) {
				setComponentProperties(componentConfig, vaadinComboBox, comboboxConfig);

				if (comboboxConfig.getValueChangeListenerClassName() != null) {
					GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
					vaadinComboBox.addValueChangeListener((ValueChangeListener) (classLoader
							.loadDynamicClass(comboboxConfig.getValueChangeListenerClassName())));
				}

				addValueChangeListener(componentConfig, vaadinComboBox);

				setDefaultFocus(vaadinComboBox, componentConfig);
				vaadinComboBox.setPopupWidth(null);
				return vaadinComboBox;
			}
		} catch (Exception exception) {
			gtnLogger.error("Exception in addVaadinComponent for " + componentConfig.getComponentId(), exception);
		}
		return vaadinComboBox;
	}

	private void addValueChangeListener(GtnUIFrameworkComponentConfig componentConfig, ComboBox<?> vaadinComboBox) {
		if (componentConfig.getGtnUIFrameWorkActionConfigList() != null
				&& !componentConfig.getGtnUIFrameWorkActionConfigList().isEmpty()) {
			vaadinComboBox.addValueChangeListener(event -> {
				try {
					AbstractComponent component = (AbstractComponent) event.getComponent();
					GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
					GtnUIFrameworkComponentConfig valueChangeComponentConfig = componentData
							.getCurrentComponentConfig();
					componentConfig.setUserOriginatedFlag(event.isUserOriginated());
					GtnUIFrameworkActionExecutor.executeActionList(componentData.getComponentIdInMap(),
							valueChangeComponentConfig.getGtnUIFrameWorkActionConfigList());
					componentConfig.setUserOriginatedFlag(false);
				} catch (GtnFrameworkGeneralException e) {
					gtnLogger.error(e.getMessage(), e);
				}
			});
			
		}
	}

	private void setComponentProperties(GtnUIFrameworkComponentConfig componentConfig, ComboBox vaadinComboBox,
			final GtnUIFrameworkComboBoxConfig comboboxConfig) {
		vaadinComboBox.setEnabled(componentConfig.isEnable());
		vaadinComboBox.setPlaceholder(componentConfig.getVaadinComponentPlaceHolder());
		vaadinComboBox.setVisible(componentConfig.isVisible());
		vaadinComboBox.setReadOnly(comboboxConfig.isReadOnly());
		vaadinComboBox.setRequiredIndicatorVisible(comboboxConfig.isRequired());
		vaadinComboBox.setEmptySelectionAllowed(false);
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

		gtnLogger.info(TRIGGERED_RELOAD_WITH_RELOAD_INPUT + reloadInput + "--Action--" + action);
		GtnUIFrameworkComponentConfig comboComponentConfig = getComboBoxComponentConfig(dependentComponentId,
				componentId);
		reloadComboBoxComponent(dependentComponentId, componentId, reloadInput, sourceViewId, comboComponentConfig);

	}

	public void reloadComponentFromView(GtnUIFrameworkActionType action, String dependentComponentId,
			String componentId, Object reloadInput) {

		GtnUIFrameworkComponentConfig comboComponentConfig = getComboBoxComponentConfigFromView(dependentComponentId,
				componentId);
		reloadComboBoxComponentFromView(dependentComponentId, componentId, reloadInput,
				comboComponentConfig.getSourceViewId(), comboComponentConfig);

	}

	public void reloadComponentFromChild(GtnUIFrameworkActionType action, String dependentComponentId,
			String componentId, Object reloadInput) {

		GtnUIFrameworkComponentConfig comboComponentConfig = getComboBoxComponentConfigFromChild(dependentComponentId,
				componentId);
		reloadComboBoxComponentFromChild(dependentComponentId, componentId, reloadInput,
				comboComponentConfig.getSourceViewId(), comboComponentConfig);

	}

	public void reloadComponentFromParent(GtnUIFrameworkActionType action, String dependentComponentId,
			String componentId, Object reloadInput) {

		GtnUIFrameworkComponentConfig comboComponentConfig = getComboBoxComponentConfigFromParent(dependentComponentId,
				componentId);
		reloadComboBoxComponentFromParent(dependentComponentId, componentId, reloadInput,
				comboComponentConfig.getSourceViewId(), comboComponentConfig);

	}

	private void reloadComboBoxComponentFromView(String dependentComponentId, String componentId, Object reloadInput,
			String sourceViewId, GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info(TRIGGERED_RELOAD_WITH_RELOAD_INPUT + reloadInput);
		List<Object> comboBoxRequestInputList = (List<Object>) reloadInput;
		String simpleComboBoxReload = String.valueOf(comboBoxRequestInputList.get(0));
		comboboxConfigLoadAfterStart(componentConfig, dependentComponentId, componentId, "view");
		if (!SIMPLE_RELOAD.equals(simpleComboBoxReload)) {

			if (componentConfig.getReloadLogicActionClassName() != null) {
				try {
					GtnUIFrameworkClassLoader comboBoxClassLoader = new GtnUIFrameworkClassLoader();
					GtnUIFrameWorkAction comboBoxCustomAction = (GtnUIFrameWorkAction) comboBoxClassLoader
							.loadDynamicClass(componentConfig.getReloadLogicActionClassName());
					GtnUIFrameWorkActionConfig comboBoxActionConfig = new GtnUIFrameWorkActionConfig();
					if (componentConfig.getReloadActionConfig() != null) {
						comboBoxActionConfig = componentConfig.getReloadActionConfig();
					}
					comboBoxActionConfig.addActionParameter(reloadInput);
					comboBoxCustomAction.doAction(sourceViewId + "_" + componentConfig.getComponentId(),
							comboBoxActionConfig);
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error(componentId, ex);
				}
				return;
			}

		} else {
			comboBoxRequestInputList.set(0, "");
		}
		GtnUIFrameworkComboBoxConfig comboboxComponentConfig = componentConfig.getGtnComboboxConfig();

		ComboBox vaadinComboBoxComponent = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromView(dependentComponentId, componentId).getComponent();
		vaadinComboBoxComponent.setItems(new ArrayList<>());

		vaadinComboBoxComponent = fillComboBox(componentConfig, comboboxComponentConfig, comboBoxRequestInputList,
				sourceViewId, vaadinComboBoxComponent, reloadInput);
		gtnLogger.info(RELOADED_VAADIN_COMBOBOX + vaadinComboBoxComponent);
	}

	private void reloadComboBoxComponentFromChild(String dependentComponentId, String componentId, Object reloadInput,
			String sourceViewId, GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info(TRIGGERED_RELOAD_WITH_RELOAD_INPUT + reloadInput);
		List<Object> comboBoxRequestInputList = (List<Object>) reloadInput;
		String simpleComboBoxReload = String.valueOf(comboBoxRequestInputList.get(0));
		if (!SIMPLE_RELOAD.equals(simpleComboBoxReload)) {
			if (componentConfig.getReloadLogicActionClassName() != null) {
				try {
					GtnUIFrameworkClassLoader comboBoxClassLoader = new GtnUIFrameworkClassLoader();
					GtnUIFrameWorkAction comboBoxCustomAction = (GtnUIFrameWorkAction) comboBoxClassLoader
							.loadDynamicClass(componentConfig.getReloadLogicActionClassName());
					GtnUIFrameWorkActionConfig comboBoxActionConfig = new GtnUIFrameWorkActionConfig();
					if (componentConfig.getReloadActionConfig() != null) {
						comboBoxActionConfig = componentConfig.getReloadActionConfig();
					}
					comboBoxActionConfig.addActionParameter(reloadInput);
					comboBoxCustomAction.doAction(sourceViewId + "_" + componentConfig.getComponentId(),
							comboBoxActionConfig);
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error(componentId, ex);
				}
				return;
			}

		} else {
			comboBoxRequestInputList.set(0, "");
		}
		GtnUIFrameworkComboBoxConfig comboboxComponentConfig = componentConfig.getGtnComboboxConfig();

		ComboBox vaadinComboBoxComponent = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromChild(dependentComponentId, componentId).getComponent();
		vaadinComboBoxComponent.setItems(new ArrayList<>());

		vaadinComboBoxComponent = fillComboBox(componentConfig, comboboxComponentConfig, comboBoxRequestInputList,
				sourceViewId, vaadinComboBoxComponent, reloadInput);
		gtnLogger.info(RELOADED_VAADIN_COMBOBOX + vaadinComboBoxComponent);

	}

	private GtnUIFrameworkComponentConfig getComboBoxComponentConfigFromView(String dependentComponentId,
			String componentId) {
		AbstractComponent abstractComboComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromView(dependentComponentId, componentId).getComponent();
		GtnUIFrameworkComponentData comboComponentData = (GtnUIFrameworkComponentData) abstractComboComponent.getData();
		return comboComponentData.getCurrentComponentConfig();
	}

	private GtnUIFrameworkComponentConfig getComboBoxComponentConfigFromChild(String dependentComponentId,
			String componentId) {
		AbstractComponent abstractComboComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromChild(dependentComponentId, componentId).getComponent();
		GtnUIFrameworkComponentData comboComponentData = (GtnUIFrameworkComponentData) abstractComboComponent.getData();
		return comboComponentData.getCurrentComponentConfig();
	}

	private void reloadComboBoxComponentFromParent(String dependentComponentId, String componentId, Object reloadInput,
			String sourceViewId, GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info(TRIGGERED_RELOAD_WITH_RELOAD_INPUT + reloadInput);
		List<Object> comboBoxRequestInputList = (List<Object>) reloadInput;
		String simpleComboBoxReload = String.valueOf(comboBoxRequestInputList.get(0));
		if (!SIMPLE_RELOAD.equals(simpleComboBoxReload)) {
			if (componentConfig.getReloadLogicActionClassName() != null) {
				try {
					GtnUIFrameworkClassLoader comboBoxClassLoader = new GtnUIFrameworkClassLoader();
					GtnUIFrameWorkAction comboBoxCustomAction = (GtnUIFrameWorkAction) comboBoxClassLoader
							.loadDynamicClass(componentConfig.getReloadLogicActionClassName());
					GtnUIFrameWorkActionConfig comboBoxActionConfig = new GtnUIFrameWorkActionConfig();
					if (componentConfig.getReloadActionConfig() != null) {
						comboBoxActionConfig = componentConfig.getReloadActionConfig();
					}
					comboBoxActionConfig.addActionParameter(reloadInput);
					comboBoxCustomAction.doAction(sourceViewId + "_" + componentConfig.getComponentId(),
							comboBoxActionConfig);
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error(componentId, ex);
				}
				return;
			}

		} else {
			comboBoxRequestInputList.set(0, "");
		}
		GtnUIFrameworkComboBoxConfig comboboxComponentConfig = componentConfig.getGtnComboboxConfig();

		ComboBox vaadinComboBoxComponent = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(dependentComponentId, componentId).getComponent();
		vaadinComboBoxComponent.setItems(new ArrayList<>());

		vaadinComboBoxComponent = fillComboBox(componentConfig, comboboxComponentConfig, comboBoxRequestInputList,
				sourceViewId, vaadinComboBoxComponent, reloadInput);
		gtnLogger.info(RELOADED_VAADIN_COMBOBOX + vaadinComboBoxComponent);

	}

	private GtnUIFrameworkComponentConfig getComboBoxComponentConfigFromParent(String dependentComponentId,
			String componentId) {
		AbstractComponent abstractComboComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(dependentComponentId, componentId).getComponent();
		GtnUIFrameworkComponentData comboComponentData = (GtnUIFrameworkComponentData) abstractComboComponent.getData();
		return comboComponentData.getCurrentComponentConfig();
	}

	private GtnUIFrameworkComponentConfig getComboBoxComponentConfig(String dependentComponentId, String componentId) {
		AbstractComponent abstractComboComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(dependentComponentId, componentId).getComponent();
		GtnUIFrameworkComponentData comboComponentData = (GtnUIFrameworkComponentData) abstractComboComponent.getData();
		return comboComponentData.getCurrentComponentConfig();

	}

	@SuppressWarnings("unchecked")
	private void reloadComboBoxComponent(String dependentComponentId, String componentId, Object reloadInput,
			String sourceViewId, GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info(TRIGGERED_RELOAD_WITH_RELOAD_INPUT + reloadInput);
		List<Object> comboBoxRequestInputList = (List<Object>) reloadInput;
		String simpleComboBoxReload = String.valueOf(comboBoxRequestInputList.get(0));
		comboboxConfigLoadAfterStart(componentConfig, dependentComponentId, componentId, "notView");
		if (!SIMPLE_RELOAD.equals(simpleComboBoxReload)) {

			if (componentConfig.getReloadLogicActionClassName() != null) {
				try {
					GtnUIFrameworkClassLoader comboBoxClassLoader = new GtnUIFrameworkClassLoader();
					GtnUIFrameWorkAction comboBoxCustomAction = (GtnUIFrameWorkAction) comboBoxClassLoader
							.loadDynamicClass(componentConfig.getReloadLogicActionClassName());
					GtnUIFrameWorkActionConfig comboBoxActionConfig = new GtnUIFrameWorkActionConfig();
					if (componentConfig.getReloadActionConfig() != null) {
						comboBoxActionConfig = componentConfig.getReloadActionConfig();
					}
					comboBoxActionConfig.addActionParameter(reloadInput);
					comboBoxCustomAction.doAction(sourceViewId + "_" + componentConfig.getComponentId(),
							comboBoxActionConfig);
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error(componentId, ex);
				}
				return;
			}

		} else {
			comboBoxRequestInputList.set(0, "");
		}
		GtnUIFrameworkComboBoxConfig comboboxComponentConfig = componentConfig.getGtnComboboxConfig();

		ComboBox vaadinComboBoxComponent = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(dependentComponentId, componentId).getComponent();
		vaadinComboBoxComponent.setItems(new ArrayList<>());

		vaadinComboBoxComponent = fillComboBox(componentConfig, comboboxComponentConfig, comboBoxRequestInputList,
				sourceViewId, vaadinComboBoxComponent, reloadInput);
		gtnLogger.info(RELOADED_VAADIN_COMBOBOX + vaadinComboBoxComponent);

	}

	private void comboboxConfigLoadAfterStart(GtnUIFrameworkComponentConfig componentConfig,
			String dependentComponentId, String componentId, String viewCheck) {
		if (componentConfig.getGtnComboboxConfig().isIsLoadAtStart()) {
			ComboBox vaadinComboBox;
			if ("view".equals(viewCheck)) {
				vaadinComboBox = (ComboBox) GtnUIFrameworkGlobalUI
						.getVaadinBaseComponentFromView(dependentComponentId, componentId).getComponent();
			} else {
				vaadinComboBox = (ComboBox) GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(dependentComponentId, componentId).getComponent();
			}

			vaadinComboBox.addValueChangeListener(event -> {
				try {
					AbstractComponent component = (AbstractComponent) event.getComponent();
					GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
                                        componentConfig.setUserOriginatedFlag(event.isUserOriginated());
					GtnUIFrameworkComponentConfig valueChangeComponentConfig = componentData
							.getCurrentComponentConfig();
					GtnUIFrameworkActionExecutor.executeActionList(componentData.getComponentIdInMap(),
							valueChangeComponentConfig.getGtnUIFrameWorkActionConfigList());
                                        componentConfig.setUserOriginatedFlag(false);

				} catch (GtnFrameworkGeneralException e) {
					gtnLogger.error(e.getMessage(), e);
				}
			});
		}
	}

	private ComboBox fillComboBox(GtnUIFrameworkComponentConfig componentConfig,
			GtnUIFrameworkComboBoxConfig comboboxConfig, List<Object> comboBoxWhereClauseParamList, String sourceViewId,
			ComboBox vaadinCombobox, Object reloadInput) {
		ComboBox vaadinComboBox = vaadinCombobox;
		GtnUIFrameworkWebserviceComboBoxResponse response = getResponseFromSource(comboboxConfig,
				comboBoxWhereClauseParamList, sourceViewId);
		try {
			if (componentConfig.getReloadLogicActionClassName() != null) {
				try {
					GtnUIFrameworkClassLoader comboBoxClassLoader = new GtnUIFrameworkClassLoader();
					GtnUIFrameWorkAction comboBoxCustomAction = (GtnUIFrameWorkAction) comboBoxClassLoader
							.loadDynamicClass(componentConfig.getReloadLogicActionClassName());
					GtnUIFrameWorkActionConfig comboBoxActionConfig = new GtnUIFrameWorkActionConfig();
					if (componentConfig.getReloadActionConfig() != null) {
						comboBoxActionConfig = componentConfig.getReloadActionConfig();
					}
					comboBoxActionConfig.addActionParameter(reloadInput);
					comboBoxCustomAction.doAction(sourceViewId + "_" + componentConfig.getComponentId(),
							comboBoxActionConfig);
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error(componentConfig.getComponentId(), ex);
				}
				return vaadinCombobox;
			}
			if (comboboxConfig.getItemValues() != null) {
				vaadinComboBox.setItems(comboboxConfig.getItemValues());
				if (comboboxConfig.getItemCaptionValues() != null) {

					vaadinComboBox = fillDataAndCaption(comboboxConfig, comboboxConfig.getItemValues(),
							comboboxConfig.getItemCaptionValues(), vaadinComboBox);
				} else {
					vaadinComboBox = fillDataAndCaption(comboboxConfig, comboboxConfig.getItemValues(),
							comboboxConfig.getItemValues(), vaadinComboBox);
				}
				return vaadinComboBox;
			}

			vaadinComboBox = fillComboboxFromResponse(response, comboboxConfig, vaadinComboBox);
		} catch (Exception e) {
			gtnLogger.error("Unable to fill combobox", e);
		}
		return vaadinComboBox;
	}

	private ComboBox fillComboboxFromResponse(GtnUIFrameworkWebserviceComboBoxResponse response,
			GtnUIFrameworkComboBoxConfig comboboxConfig, ComboBox vaadinCombobox) {
		ComboBox vaadinComboBox = vaadinCombobox;
		if (response != null && response.getItemCodeList() != null && !response.getItemCodeList().isEmpty()) {
			if (comboboxConfig.isIntegerItemCode()) {
				List<Integer> integerList = new ArrayList<>();
				for (String str : response.getItemCodeList()) {
					integerList.add(Integer.valueOf(str));
				}
				vaadinComboBox = fillDataAndCaption(comboboxConfig, integerList, response.getItemValueList(),
						vaadinComboBox);
			} else {
				vaadinComboBox = fillDataAndCaption(comboboxConfig, response.getItemCodeList(),
						response.getItemValueList(), vaadinComboBox);
			}
			return vaadinComboBox;
		}
		return vaadinComboBox;
	}

	@SuppressWarnings("unchecked")
	private ComboBox fillDataAndCaption(GtnUIFrameworkComboBoxConfig comboboxConfig, List itemCodeList,
			List<String> itemValueList, ComboBox vaadinComboBox) {

		try {
			List valueList = new ArrayList<>(itemCodeList);
			List<String> captionList = new ArrayList<>(itemValueList);
			vaadinComboBox.setItems(valueList);
			vaadinComboBox.setItemCaptionGenerator(item -> captionList.get(valueList.indexOf(item)));
			if (!comboboxConfig.isHasDefaultValue()) {
				String defaultValue = comboboxConfig.getDefaultValue() != null
						? String.valueOf(comboboxConfig.getDefaultValue())
						: GtnFrameworkCommonStringConstants.SELECT_ONE;
				valueList.add(0, 0);
				captionList.add(0, defaultValue);
				vaadinComboBox.setSelectedItem(0);
			} else {
				for (int i = 0; i < captionList.size(); i++) {
					if (comboboxConfig.getDefaultDesc().equals(captionList.get(i))
							|| comboboxConfig.getDefaultDesc().equals("next")) {
						vaadinComboBox.setSelectedItem(valueList.get(i));
						break;
					}
				}
			}
			gtnLogger.info("Fill Data And Caption to the Component" + vaadinComboBox);
			return vaadinComboBox;
		} catch (Exception e) {
			gtnLogger.error("Error message", e);
		}
		return null;
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

		if ((GtnUiFrameworkComboBoxSourceType.VALUES_FROM_SERVICE) == (comboboxConfig.getSourceType())) {
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
			try {
				comboboxResponse = comboboxWsclient
						.callGtnWebServiceUrl(comboboxConfig.getLoadingUrl(), comboboxConfig.getModuleName(),
								comboboxRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
						.getGtnUIFrameworkWebserviceComboBoxResponse();
			} catch (Exception e) {
				gtnLogger.error("Error message", e);
			}
		}
		return comboboxResponse;
	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		ComboBox vaadinComboBox = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		vaadinComboBox.setItems(Collections.emptyList());
		generateComboBox(componentConfig, vaadinComboBox);
		postCreateComponent(vaadinComboBox, componentConfig);
		vaadinComboBox.getDataProvider().refreshAll();
		vaadinComboBox.markAsDirty();
	}

	private void setDefaultFocus(ComboBox vaadinComboBox, GtnUIFrameworkComponentConfig componentConfig) {
		if (componentConfig.isDefaultFocus()) {
			vaadinComboBox.focus();
		}
	}

	@Override
	public void postCreateComponent(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {
		fillComboBox(componentConfig, componentConfig.getGtnComboboxConfig(), null, componentConfig.getSourceViewId(),
				(ComboBox) component, null);
	}

}