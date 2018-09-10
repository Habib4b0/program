
/* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.*/

package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.comboboxmultiselect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.vaadin.addons.ComboBoxMultiselect;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox.GtnUIFrameworkCheckedComboBoxConfig;
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
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;

public class GtnUIFrameworkComboBoxMultiselectComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkComboBoxMultiselectComponent.class);

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig multiSelectComponentConfig) {
		gtnLogger.info("Enter buildVaadinComponent method");
		ComboBoxMultiselect comboBoxVaadinMultiSelect = new ComboBoxMultiselect();
		comboBoxVaadinMultiSelect.setCaption(multiSelectComponentConfig.getComponentName());
		comboBoxVaadinMultiSelect.showSelectAllButton(true);
		comboBoxVaadinMultiSelect.setClearButtonCaption("Clear");
		comboBoxVaadinMultiSelect.setSelectAllButtonCaption("Select All");
		comboBoxVaadinMultiSelect.setPopupWidth(null);
		comboBoxVaadinMultiSelect.showClearButton(true);
		
		loadStyles(comboBoxVaadinMultiSelect, multiSelectComponentConfig.getComponentStyle());
		if (!multiSelectComponentConfig.getComboBoxComponentStyle().isEmpty()) {
			loadStyles(comboBoxVaadinMultiSelect, multiSelectComponentConfig.getComboBoxComponentStyle());
		}
		final GtnUIFrameworkCheckedComboBoxConfig checkedComboBoxConfig = multiSelectComponentConfig
				.getGtnCheckedComboboxConfig();
		comboBoxVaadinMultiSelect.setPlaceholder(checkedComboBoxConfig.getDefaultValue());
		GtnUIFrameworkWebServiceClient multiSelectWsClient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest multiSelectWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest multiSelectGeneralWsRequest = new GtnWsGeneralRequest();
		multiSelectGeneralWsRequest.setComboBoxType(checkedComboBoxConfig.getCheckedComboBoxType());
		multiSelectWsRequest.setGtnWsGeneralRequest(multiSelectGeneralWsRequest);
		addblurListener(multiSelectComponentConfig, comboBoxVaadinMultiSelect);

		if (checkedComboBoxConfig.getLoadingUrl() != null) {
			GtnUIFrameworkWebserviceComboBoxResponse multiSelectResponse = multiSelectWsClient
					.callGtnWebServiceUrl(checkedComboBoxConfig.getLoadingUrl(), multiSelectWsRequest,
							GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
					.getGtnUIFrameworkWebserviceComboBoxResponse();
			if (multiSelectResponse != null && multiSelectResponse.getItemValueList() != null) {
				List idList = new ArrayList<>(multiSelectResponse.getItemCodeList());
				List<String> valueList = new ArrayList<>(multiSelectResponse.getItemValueList());
				comboBoxVaadinMultiSelect.setItems(idList);
				comboBoxVaadinMultiSelect.setItemCaptionGenerator(item -> valueList.get(idList.indexOf(item)));
				setSelectedItemInComboboxMultiselect(checkedComboBoxConfig, comboBoxVaadinMultiSelect, idList,
						valueList);
				setPageLengthBasedOnTheNumberOfRecords(valueList.size(),
						comboBoxVaadinMultiSelect);

			}
                        
			return comboBoxVaadinMultiSelect;
		}

		if (checkedComboBoxConfig.getItemValueList() != null) {
			if (checkedComboBoxConfig.getItemCodeList() != null) {
				
				comboBoxVaadinMultiSelect.setItems(checkedComboBoxConfig.getItemCodeList());
				comboBoxVaadinMultiSelect.setItemCaptionGenerator(item -> checkedComboBoxConfig.getItemValueList()
						.get(checkedComboBoxConfig.getItemCodeList().indexOf(item)));
				setPageLengthBasedOnTheNumberOfRecords(checkedComboBoxConfig.getItemValueList().size() ,comboBoxVaadinMultiSelect);
				return comboBoxVaadinMultiSelect;
				
			}
			comboBoxVaadinMultiSelect.setItems(checkedComboBoxConfig.getItemValueList());
			setPageLengthBasedOnTheNumberOfRecords(checkedComboBoxConfig.getItemValueList().size() ,comboBoxVaadinMultiSelect);
		}
		
		return comboBoxVaadinMultiSelect;
	}

    private void setSelectedItemInComboboxMultiselect(final GtnUIFrameworkCheckedComboBoxConfig checkedComboBoxConfig, ComboBoxMultiselect comboBoxVaadinMultiSelect, List idList, List<String> valueList) {
        if(!checkedComboBoxConfig.getSelectedItem().isEmpty()){
            comboBoxVaadinMultiSelect.select(idList.get(valueList.indexOf(checkedComboBoxConfig.getSelectedItem())));
        }
    }

	@Override
	public void reloadComponent(GtnUIFrameworkActionType reloadActionType, String reloadDependentComponentId,
			String reloadComponentId, Object componentReloadInput) {
		GtnUIFrameworkComponentConfig comboComponentConfig = getComboBoxComponentConfig(reloadDependentComponentId,
				reloadComponentId);
		reloadComboBoxComponent(reloadDependentComponentId, reloadComponentId, componentReloadInput,
				comboComponentConfig.getSourceViewId(), comboComponentConfig);
	}

	public void reloadComponent(GtnUIFrameworkActionType multiSelectAction, String multiSelectDependentComponentId,
			String multiSelectComponentId, String multiSelectSourceViewId, Object multiSelectReloadInput) {

		gtnLogger.info(
				"Triggered reload with reload input " + multiSelectReloadInput + "--Action--" + multiSelectAction);
		GtnUIFrameworkComponentConfig comboComponentConfig = getComboBoxComponentConfig(multiSelectDependentComponentId,
				multiSelectComponentId);
		reloadComboBoxComponent(multiSelectDependentComponentId, multiSelectComponentId, multiSelectReloadInput,
				multiSelectSourceViewId, comboComponentConfig);

	}

	private GtnUIFrameworkComponentConfig getComboBoxComponentConfig(String dependentComponentId, String componentId) {
		AbstractComponent abstractComboComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(dependentComponentId,
				componentId);
		GtnUIFrameworkComponentData comboComponentData = (GtnUIFrameworkComponentData) abstractComboComponent.getData();
		return comboComponentData.getCurrentComponentConfig();

	}

	@SuppressWarnings("unchecked")
	private void reloadComboBoxComponent(String dependentComponentId, String componentId, Object reloadInput,
			String sourceViewId, GtnUIFrameworkComponentConfig comboComponentConfig) {
		gtnLogger.info("Triggered reload with reload input " + reloadInput);
		List<Object> comboBoxMultiRequestInputList = (List<Object>) reloadInput;
		String simpleComboBoxReload = String.valueOf(comboBoxMultiRequestInputList.get(0));
		if (!"simpleReload".equals(simpleComboBoxReload)) {
			if (comboComponentConfig.getReloadLogicActionClassName() != null) {
				try {
					GtnUIFrameworkClassLoader comboBoxMultiClassLoader = new GtnUIFrameworkClassLoader();
					GtnUIFrameWorkAction comboBoxMultiCustomAction = (GtnUIFrameWorkAction) comboBoxMultiClassLoader
							.loadDynamicClass(comboComponentConfig.getReloadLogicActionClassName());
					GtnUIFrameWorkActionConfig comboBoxMultiActionConfig = new GtnUIFrameWorkActionConfig();
					if (comboComponentConfig.getReloadActionConfig() != null) {
						comboBoxMultiActionConfig = comboComponentConfig.getReloadActionConfig();
					}
					comboBoxMultiActionConfig.addActionParameter(reloadInput);
					comboBoxMultiCustomAction.doAction(sourceViewId + "_" + comboComponentConfig.getComponentId(),
							comboBoxMultiActionConfig);
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error(componentId, ex);
				}
				return;
			}

		} else {
			comboBoxMultiRequestInputList.set(0, "");
		}
		GtnUIFrameworkComboBoxConfig comboboxComponentConfig = comboComponentConfig.getGtnComboboxConfig();

		ComboBoxMultiselect vaadinMultiSelectComponent = (ComboBoxMultiselect) GtnUIFrameworkGlobalUI
				.getVaadinComponent(dependentComponentId, componentId);
		vaadinMultiSelectComponent.setItems(new ArrayList<>());

		vaadinMultiSelectComponent = fillComboBox(comboboxComponentConfig, comboBoxMultiRequestInputList, sourceViewId,
				vaadinMultiSelectComponent);
		gtnLogger.info("Reloaded vaadin combobox" + vaadinMultiSelectComponent);

	}

	private ComboBoxMultiselect fillComboBox(GtnUIFrameworkComboBoxConfig comboboxConfig,
			List<Object> comboBoxWhereClauseParamList, String sourceViewId,
			ComboBoxMultiselect vaadinMultiSelectComponent) {
		ComboBoxMultiselect vaadinMultiSelect = vaadinMultiSelectComponent;
		GtnUIFrameworkWebserviceComboBoxResponse response = getResponseFromSource(comboboxConfig,
				comboBoxWhereClauseParamList, sourceViewId);
		try {
			if (comboboxConfig.getItemValues() != null) {
				vaadinMultiSelect.setItems(comboboxConfig.getItemValues());
				if (comboboxConfig.getItemCaptionValues() != null) {

					vaadinMultiSelect = fillDataAndCaption(comboboxConfig, comboboxConfig.getItemValues(),
							comboboxConfig.getItemCaptionValues(), vaadinMultiSelect);
				} else {
					vaadinMultiSelect = fillDataAndCaption(comboboxConfig, comboboxConfig.getItemValues(),
							comboboxConfig.getItemValues(), vaadinMultiSelect);
				}
                              
				return vaadinMultiSelect;
			}

			vaadinMultiSelect = fillComboboxFromResponse(response, comboboxConfig, vaadinMultiSelect);
		} catch (Exception e) {
			gtnLogger.error("Unable to fill combobox", e);
		}
		return vaadinMultiSelect;
	}

	private ComboBoxMultiselect fillComboboxFromResponse(GtnUIFrameworkWebserviceComboBoxResponse response,
			GtnUIFrameworkComboBoxConfig comboboxConfig, ComboBoxMultiselect vaadinMultiSelectComponent) {
		ComboBoxMultiselect vaadinMultiSelect = vaadinMultiSelectComponent;
		if (response != null && response.getItemCodeList() != null && !response.getItemCodeList().isEmpty()) {
			if (comboboxConfig.isIntegerItemCode()) {
				List<Integer> integerList = new ArrayList<>();
				for (String str : response.getItemCodeList()) {
					integerList.add(Integer.valueOf(str));
				}
				vaadinMultiSelect = fillDataAndCaption(comboboxConfig, integerList, response.getItemValueList(),
						vaadinMultiSelect);
			} else {
				vaadinMultiSelect = fillDataAndCaption(comboboxConfig, response.getItemCodeList(),
						response.getItemValueList(), vaadinMultiSelect);
			}
			return vaadinMultiSelect;
		}
		return vaadinMultiSelect;
	}

	@SuppressWarnings("unchecked")
	private ComboBoxMultiselect fillDataAndCaption(GtnUIFrameworkComboBoxConfig comboboxConfig, List itemCodeList,
			List<String> itemValueList, ComboBoxMultiselect vaadinMultiSelect) {

		try {
			List valueList = new ArrayList<>(itemCodeList);
			List<String> captionList = new ArrayList<>(itemValueList);
			vaadinMultiSelect.setItems(valueList);
			vaadinMultiSelect.setItemCaptionGenerator(item -> captionList.get(valueList.indexOf(item)));
			if (!comboboxConfig.isHasDefaultValue()) {
				String defaultValue = comboboxConfig.getDefaultValue() != null
						? String.valueOf(comboboxConfig.getDefaultValue())
						: GtnFrameworkCommonStringConstants.SELECT_ONE;
				valueList.add(0, 0);
				captionList.add(0, defaultValue);
				vaadinMultiSelect.setValue(new HashSet<>(Arrays.asList(defaultValue)));
			} else {
				for (int i = 0; i < captionList.size(); i++) {
					if (comboboxConfig.getDefaultDesc().equals(captionList.get(i))) {
						vaadinMultiSelect.setValue(new HashSet<>(Arrays.asList(valueList.get(i))));
						break;
					}
				}
			}
			gtnLogger.info("Fill Data And Caption to the Component" + vaadinMultiSelect);
			setPageLengthBasedOnTheNumberOfRecords(valueList.size() ,vaadinMultiSelect);
			return vaadinMultiSelect;
		} catch (Exception e) {
			gtnLogger.error("Error message", e);
		}
		return null;
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
		return;
	}

	private void loadStyles(final Component checkedComboboxComponent, final List<String> styles) {
		if (styles != null) {
			for (String style : styles) {
				checkedComboboxComponent.addStyleName(style);
			}
		}
	}

	private void addblurListener(GtnUIFrameworkComponentConfig componentConfig,
			ComboBoxMultiselect vaadinComboBoxMultiSelect) {
		if (componentConfig.getGtnUIFrameWorkActionConfigList() != null
				&& !componentConfig.getGtnUIFrameWorkActionConfigList().isEmpty()) {
			vaadinComboBoxMultiSelect.addBlurListener(event -> {
				try {
					AbstractComponent component = (AbstractComponent) event.getComponent();
					GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
					GtnUIFrameworkComponentConfig blurComponentConfig = componentData.getCurrentComponentConfig();
					GtnUIFrameworkActionExecutor.executeActionList(componentData.getComponentIdInMap(),
							blurComponentConfig.getGtnUIFrameWorkActionConfigList());

				} catch (GtnFrameworkGeneralException e) {
					gtnLogger.error(e.getMessage(), e);
				}
			});
		}
	}
	
	public void setPageLengthBasedOnTheNumberOfRecords(int valueListSize, Component component) {

		int[] arr = new int[7];

		for (int i = 0; i < 7; i++) {
			arr[i] = ((7 + i) - (valueListSize % (7 + i)));
			if (valueListSize % (7 + i) == 0) {
				arr[i] = 0;
			}
		}
		if (component instanceof ComboBoxMultiselect) {
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


}
