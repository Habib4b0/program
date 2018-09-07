package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.vaadin.addons.ComboBoxMultiselect;

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
                         if(!(componentConfig.getComboBoxComponentStyle().isEmpty())){
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
		if (comboboxConfig.isFocus()) {
			vaadinComboBox.focus();
		}
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

	public void reloadComponentFromView(String dependentComponentId, String componentId, Object reloadInput) {

		GtnUIFrameworkComponentConfig comboComponentConfig = getComboBoxComponentConfigFromView(dependentComponentId,
				componentId);
		reloadComboBoxComponentFromView(dependentComponentId, componentId, reloadInput,
				comboComponentConfig.getSourceViewId(), comboComponentConfig);

	}

	public void reloadComponentFromChild(String dependentComponentId, String componentId, Object reloadInput) {

		GtnUIFrameworkComponentConfig comboComponentConfig = getComboBoxComponentConfigFromChild(dependentComponentId,
				componentId);
		reloadComboBoxComponentFromChild(dependentComponentId, componentId, reloadInput,
				comboComponentConfig.getSourceViewId(), comboComponentConfig);

	}

	public void reloadComponentFromParent(String dependentComponentId, String componentId, Object reloadInput) {

		GtnUIFrameworkComponentConfig comboComponentConfig = getComboBoxComponentConfigFromParent(dependentComponentId,
				componentId);
		reloadComboBoxComponentFromParent(dependentComponentId, componentId, reloadInput,
				comboComponentConfig.getSourceViewId(), comboComponentConfig);

	}

	private void reloadComboBoxComponentFromView(String dependentComponentId, String componentId, Object reloadInput,
			String sourceViewId, GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info(TRIGGERED_RELOAD_WITH_RELOAD_INPUT + reloadInput);
		List<Object> comboBoxViewRequestInputList = (List<Object>) reloadInput;
		String simpleViewComboBoxReload = String.valueOf(comboBoxViewRequestInputList.get(0));
		comboboxConfigLoadAfterStart(componentConfig, dependentComponentId, componentId, "view");
		if (!SIMPLE_RELOAD.equals(simpleViewComboBoxReload)) {

			if (componentConfig.getReloadLogicActionClassName() != null) {
				try {
					GtnUIFrameworkClassLoader comboBoxViewClassLoader = new GtnUIFrameworkClassLoader();
					GtnUIFrameWorkAction comboBoxViewCustomAction = (GtnUIFrameWorkAction) comboBoxViewClassLoader
							.loadDynamicClass(componentConfig.getReloadLogicActionClassName());
					GtnUIFrameWorkActionConfig comboBoxViewActionConfig = new GtnUIFrameWorkActionConfig();
					if (componentConfig.getReloadActionConfig() != null) {
						comboBoxViewActionConfig = componentConfig.getReloadActionConfig();
					}
					comboBoxViewActionConfig.addActionParameter(reloadInput);
					comboBoxViewCustomAction.doAction(sourceViewId + "_" + componentConfig.getComponentId(),
							comboBoxViewActionConfig);
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error(componentId, ex);
				}
				return;
			}

		} else {
			comboBoxViewRequestInputList.set(0, "");
		}
		GtnUIFrameworkComboBoxConfig comboboxViewComponentConfig = componentConfig.getGtnComboboxConfig();

		ComboBox vaadinComboBoxViewComponent = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromView(dependentComponentId, componentId).getComponent();
		vaadinComboBoxViewComponent.setItems(new ArrayList<>());

		vaadinComboBoxViewComponent = fillComboBox(componentConfig, comboboxViewComponentConfig, comboBoxViewRequestInputList,
				sourceViewId, vaadinComboBoxViewComponent, reloadInput);
		gtnLogger.info(RELOADED_VAADIN_COMBOBOX + vaadinComboBoxViewComponent);
	}

	private void reloadComboBoxComponentFromChild(String dependentComponentId, String componentId, Object reloadInput,
			String sourceViewId, GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info(TRIGGERED_RELOAD_WITH_RELOAD_INPUT + reloadInput);
		List<Object> comboBoxChildRequestInputList = (List<Object>) reloadInput;
		String simpleChildComboBoxReload = String.valueOf(comboBoxChildRequestInputList.get(0));
		if (!SIMPLE_RELOAD.equals(simpleChildComboBoxReload)) {
			if (componentConfig.getReloadLogicActionClassName() != null) {
				try {
					GtnUIFrameworkClassLoader comboBoxChildClassLoader = new GtnUIFrameworkClassLoader();
					GtnUIFrameWorkAction comboBoxChildCustomAction = (GtnUIFrameWorkAction) comboBoxChildClassLoader
							.loadDynamicClass(componentConfig.getReloadLogicActionClassName());
					GtnUIFrameWorkActionConfig comboBoxChildActionConfig = new GtnUIFrameWorkActionConfig();
					if (componentConfig.getReloadActionConfig() != null) {
						comboBoxChildActionConfig = componentConfig.getReloadActionConfig();
					}
					comboBoxChildActionConfig.addActionParameter(reloadInput);
					comboBoxChildCustomAction.doAction(sourceViewId + "_" + componentConfig.getComponentId(),
							comboBoxChildActionConfig);
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error(componentId, ex);
				}
				return;
			}

		} else {
			comboBoxChildRequestInputList.set(0, "");
		}
		GtnUIFrameworkComboBoxConfig comboboxChildComponentConfig = componentConfig.getGtnComboboxConfig();

		ComboBox vaadinComboBoxChildComponent = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromChild(dependentComponentId, componentId).getComponent();
		vaadinComboBoxChildComponent.setItems(new ArrayList<>());

		vaadinComboBoxChildComponent = fillComboBox(componentConfig, comboboxChildComponentConfig, comboBoxChildRequestInputList,
				sourceViewId, vaadinComboBoxChildComponent, reloadInput);
		gtnLogger.info(RELOADED_VAADIN_COMBOBOX + vaadinComboBoxChildComponent);

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
		List<Object> comboBoxParentRequestInputList = (List<Object>) reloadInput;
		String simpleParentComboBoxReload = String.valueOf(comboBoxParentRequestInputList.get(0));
		if (!SIMPLE_RELOAD.equals(simpleParentComboBoxReload)) {
			if (componentConfig.getReloadLogicActionClassName() != null) {
				try {
					GtnUIFrameworkClassLoader comboBoxParentClassLoader = new GtnUIFrameworkClassLoader();
					GtnUIFrameWorkAction comboBoxParentCustomAction = (GtnUIFrameWorkAction) comboBoxParentClassLoader
							.loadDynamicClass(componentConfig.getReloadLogicActionClassName());
					GtnUIFrameWorkActionConfig comboBoxParentActionConfig = new GtnUIFrameWorkActionConfig();
					if (componentConfig.getReloadActionConfig() != null) {
						comboBoxParentActionConfig = componentConfig.getReloadActionConfig();
					}
					comboBoxParentActionConfig.addActionParameter(reloadInput);
					comboBoxParentCustomAction.doAction(sourceViewId + "_" + componentConfig.getComponentId(),
							comboBoxParentActionConfig);
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error(componentId, ex);
				}
				return;
			}

		} else {
			comboBoxParentRequestInputList.set(0, "");
		}
		GtnUIFrameworkComboBoxConfig comboboxParentComponentConfig = componentConfig.getGtnComboboxConfig();

		ComboBox vaadinComboBoxParentComponent = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(dependentComponentId, componentId).getComponent();
		vaadinComboBoxParentComponent.setItems(new ArrayList<>());

		vaadinComboBoxParentComponent = fillComboBox(componentConfig, comboboxParentComponentConfig, comboBoxParentRequestInputList,
				sourceViewId, vaadinComboBoxParentComponent, reloadInput);
		gtnLogger.info(RELOADED_VAADIN_COMBOBOX + vaadinComboBoxParentComponent);

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
		List<Object> comboBoxReloadRequestInputList = (List<Object>) reloadInput;
		String simpleReloadComboBoxReload = String.valueOf(comboBoxReloadRequestInputList.get(0));
		comboboxConfigLoadAfterStart(componentConfig, dependentComponentId, componentId, "notView");
		if (!SIMPLE_RELOAD.equals(simpleReloadComboBoxReload)) {

			if (componentConfig.getReloadLogicActionClassName() != null) {
				try {
					GtnUIFrameworkClassLoader comboBoxReloadClassLoader = new GtnUIFrameworkClassLoader();
					GtnUIFrameWorkAction comboBoxReloadCustomAction = (GtnUIFrameWorkAction) comboBoxReloadClassLoader
							.loadDynamicClass(componentConfig.getReloadLogicActionClassName());
					GtnUIFrameWorkActionConfig comboBoxReloadActionConfig = new GtnUIFrameWorkActionConfig();
					if (componentConfig.getReloadActionConfig() != null) {
						comboBoxReloadActionConfig = componentConfig.getReloadActionConfig();
					}
					comboBoxReloadActionConfig.addActionParameter(reloadInput);
					comboBoxReloadCustomAction.doAction(sourceViewId + "_" + componentConfig.getComponentId(),
							comboBoxReloadActionConfig);
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error(componentId, ex);
				}
				return;
			}

		} else {
			comboBoxReloadRequestInputList.set(0, "");
		}
		GtnUIFrameworkComboBoxConfig comboboxReloadComponentConfig = componentConfig.getGtnComboboxConfig();

		ComboBox vaadinReloadComboBoxComponent = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(dependentComponentId, componentId).getComponent();
		vaadinReloadComboBoxComponent.setItems(new ArrayList<>());

		vaadinReloadComboBoxComponent = fillComboBox(componentConfig, comboboxReloadComponentConfig, comboBoxReloadRequestInputList,
				sourceViewId, vaadinReloadComboBoxComponent, reloadInput);
		gtnLogger.info(RELOADED_VAADIN_COMBOBOX + vaadinReloadComboBoxComponent);

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
		GtnUIFrameworkWebserviceComboBoxResponse fillComboResponse = getResponseFromSource(comboboxConfig,
				comboBoxWhereClauseParamList, sourceViewId);
		try {
			if (componentConfig.getReloadLogicActionClassName() != null) {
					GtnUIFrameworkClassLoader fillComboBoxClassLoader = new GtnUIFrameworkClassLoader();
					GtnUIFrameWorkAction fillComboBoxCustomAction = (GtnUIFrameWorkAction) fillComboBoxClassLoader
							.loadDynamicClass(componentConfig.getReloadLogicActionClassName());
					GtnUIFrameWorkActionConfig fillComboBoxActionConfig = new GtnUIFrameWorkActionConfig();
					if (componentConfig.getReloadActionConfig() != null) {
						fillComboBoxActionConfig = componentConfig.getReloadActionConfig();
					}
					fillComboBoxActionConfig.addActionParameter(reloadInput);
					fillComboBoxCustomAction.doAction(sourceViewId + "_" + componentConfig.getComponentId(),
							fillComboBoxActionConfig);
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

			vaadinComboBox = fillComboboxFromResponse(fillComboResponse, comboboxConfig, vaadinComboBox);
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
		vaadinComboBox.markAsDirty();
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
			int valueListSize = valueList.size();
			setPageLengthBasedOnTheNumberOfRecords(valueListSize ,vaadinComboBox);
			return vaadinComboBox;
		} catch (Exception e) {
			gtnLogger.error("Error message", e);
		}
		return null;
	}

	public void setPageLengthBasedOnTheNumberOfRecords(int valueListSize, Component component) {

		int[] arr = new int[7];

		for (int i = 0; i < 7; i++) {
			arr[i] = ((7 + i) - (valueListSize % (7 + i)));
			if (valueListSize % (7 + i) == 0) {
				arr[i] = 0;
			}
		}
		if (component instanceof ComboBox) {
			ComboBox comboBox = (ComboBox) component;
			comboBox.setPageLength(7 + indexOfMin(arr));
		} else if (component instanceof ComboBoxMultiselect) {
			ComboBoxMultiselect comboBoxMultiselect = (ComboBoxMultiselect) component;
			comboBoxMultiselect.setPageLength(7 + indexOfMin(arr));
		}

	}

	

	private int indexOfMin(int[] a) {

		int min = a[0];
		int index = 0;
		for (int i = 0; i < a.length; i++) {
			if (min >= a[i]) {
				min = a[i];
				index = i;
			}
		}
		return index;
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

		GtnUIFrameworkWebserviceComboBoxResponse comboBoxSourceResponse = new GtnUIFrameworkWebserviceComboBoxResponse();

		if ((GtnUiFrameworkComboBoxSourceType.VALUES_FROM_SERVICE) == (comboboxConfig.getSourceType())) {
			return getResponseFromService(comboboxConfig, comboBoxWhereClauseParamList);
		}

		GtnUIFrameworkBaseComponent comboboxBaseSourceComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(comboboxConfig.getSourceComboboxId(), sourceViewId);

		if (comboboxBaseSourceComponent == null) {
			return comboBoxSourceResponse;
		}
		return comboBoxSourceResponse;
	}

	private GtnUIFrameworkWebserviceComboBoxResponse getResponseFromService(GtnUIFrameworkComboBoxConfig comboboxConfig,
			List<Object> comboBoxWhereClauseParamList) {
		GtnUIFrameworkWebserviceComboBoxResponse comboBoxServiceResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
		GtnUIFrameworkWebServiceClient comboboxWsServiceclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest comboboxServiceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest comboboxGeneralWSServiceRequest = new GtnWsGeneralRequest();
		comboboxGeneralWSServiceRequest.setComboBoxType(comboboxConfig.getComboBoxType());
		if (null != comboBoxWhereClauseParamList) {
			comboboxGeneralWSServiceRequest.setComboBoxWhereclauseParamList(comboBoxWhereClauseParamList);
		}

		comboboxServiceRequest.setGtnWsGeneralRequest(comboboxGeneralWSServiceRequest);
		if (comboboxConfig.getLoadingUrl() != null) {
			try {
				comboBoxServiceResponse = comboboxWsServiceclient
						.callGtnWebServiceUrl(comboboxConfig.getLoadingUrl(), comboboxConfig.getModuleName(),
								comboboxServiceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
						.getGtnUIFrameworkWebserviceComboBoxResponse();
			} catch (Exception e) {
				gtnLogger.error("Error message", e);
			}
		}
		return comboBoxServiceResponse;
	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		ComboBox vaadinComboBox = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		vaadinComboBox.setItems(Collections.emptyList());
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

	public void setFocusInComponent(AbstractComponent abstractComponent) {
		ComboBox comboBox = (ComboBox)abstractComponent;
		comboBox.focus();
	}
	
}