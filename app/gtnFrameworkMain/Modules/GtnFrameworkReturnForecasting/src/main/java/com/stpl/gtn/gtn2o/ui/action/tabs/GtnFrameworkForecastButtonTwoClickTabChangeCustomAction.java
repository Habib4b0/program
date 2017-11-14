package com.stpl.gtn.gtn2o.ui.action.tabs;

import java.util.ArrayList;
import java.util.List;

import org.asi.ui.customwindow.CustomWindow;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkComponentValueSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.DataSelectionBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;

public class GtnFrameworkForecastButtonTwoClickTabChangeCustomAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkForecastButtonTwoClickTabChangeCustomAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info("inside GtnFrameworkForecastButtonTwoClickTabChangeCustomAction");

		GtnUIFrameWorkActionConfig tempActionConfig = (GtnUIFrameWorkActionConfig) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(1);

		List<Object> actionParameterList = tempActionConfig.getActionParameterList();
		final Button previousButton = (Button) getAbstractComponent(String.valueOf(actionParameterList.get(8)),
				componentId);
		final Button nextButton = (Button) getAbstractComponent(String.valueOf(actionParameterList.get(9)),
				componentId);
		final DataSelectionBean dataSelectionBean = getDataSelectionBean(actionParameterList, componentId);

		resetDataSelectionTab(tempActionConfig, dataSelectionBean, componentId);
		previousButton.setVisible(false);
		nextButton.setVisible(true);
	}

	private AbstractComponent getAbstractComponent(String componentId, String sourceComponentId) {
		return GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(componentId), sourceComponentId);
	}

	private void resetDataSelectionTab(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig,
			DataSelectionBean dataSelectionBean, String sourceComponentId)
			throws GtnFrameworkValidationFailedException {
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		loadDataByDefalutValue(actionParameterList.get(11).toString() + GtnFrameworkForecastConstantCommon.UNDERSCORE,
				"", gtnUIFrameWorkActionConfig, dataSelectionBean, sourceComponentId);
	}

	private DataSelectionBean getDataSelectionBean(List<Object> actionParameterList, String sourceComponentId) {
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParameterList.get(1).toString(), sourceComponentId);
		GtnForecastBean gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();
		forecastRequest.setGtnForecastBean(gtnForecastBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_READ_DATA_SELECTION_FILE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return response.getGtnWsForecastResponse().getDataSelectionBean();
	}

	private void loadDataByDefalutValue(String toNamespace, String componentId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, DataSelectionBean dataSelectionBean,
			String sourceComponentId) throws GtnFrameworkValidationFailedException {

		GtnUIFrameworkComponentValueSetter gtnUIFrameworkSetter = new GtnUIFrameworkComponentValueSetter();

		try {
			List<String> fieldValuesParameterList = gtnUIFrameWorkActionConfig.getFieldValues();

			GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
					.getComponentData();
			GtnUIFrameworkComponentData windowComponentData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(componentData.getViewId()).getComponentData();
			CustomWindow vaadinComponent = windowComponentData.getCustomWindow();
			vaadinComponent.setCaption(dataSelectionBean.getProjectionName());
			gtnUIFrameworkSetter.loadFieldValue(sourceComponentId, toNamespace + fieldValuesParameterList.get(1),
					dataSelectionBean.getPrivateViewName());
			gtnUIFrameworkSetter.loadFieldValue(sourceComponentId, toNamespace + fieldValuesParameterList.get(3),
					dataSelectionBean.getPublicViewName());
			gtnUIFrameworkSetter.loadComboBoxComponentValue(sourceComponentId,
					toNamespace + fieldValuesParameterList.get(5), dataSelectionBean.getGlCompanyId());
			gtnUIFrameworkSetter.loadComboBoxComponentValue(sourceComponentId,
					toNamespace + fieldValuesParameterList.get(7), dataSelectionBean.getBusinessUnitId());
			gtnUIFrameworkSetter.loadFieldValue(sourceComponentId, toNamespace + fieldValuesParameterList.get(9),
					dataSelectionBean.getProjectionName());
			gtnUIFrameworkSetter.loadFieldValue(sourceComponentId, toNamespace + fieldValuesParameterList.get(10),
					dataSelectionBean.getProjectionDescription());
			gtnUIFrameworkSetter.loadComboBoxComponentValue(sourceComponentId,
					toNamespace + fieldValuesParameterList.get(12), dataSelectionBean.getFromDate());
			gtnUIFrameworkSetter.loadComboBoxComponentValue(sourceComponentId,
					toNamespace + fieldValuesParameterList.get(14), dataSelectionBean.getToDate());

			gtnUIFrameworkSetter.loadFieldValue(sourceComponentId, toNamespace + fieldValuesParameterList.get(16),
					dataSelectionBean.getProductHierarchyName());
			GtnUIFrameworkComponentData toComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(toNamespace + fieldValuesParameterList.get(16), sourceComponentId);
			toComponentData.setCustomData(dataSelectionBean.getProductHierarchyId());

			List<Object> reloadInput = new ArrayList<>();
			reloadInput.add(toComponentData.getCustomData());

			GtnUIFrameworkComponentData sourceComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(componentId);
			GtnUIFrameworkComboboxComponent gtnUIFrameworkComponent = new GtnUIFrameworkComboboxComponent();
			gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					toNamespace + fieldValuesParameterList.get(17), componentId, sourceComponentData.getParentViewId(),
					reloadInput);
			gtnUIFrameworkSetter.loadComboBoxComponentValue(sourceComponentId,
					toNamespace + fieldValuesParameterList.get(17),
					Integer.valueOf(dataSelectionBean.getRelationshipBuilderId().toString()));

			gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					toNamespace + fieldValuesParameterList.get(18), componentId, sourceComponentData.getParentViewId(),
					reloadInput);
			gtnUIFrameworkSetter.loadComboBoxComponentValue(sourceComponentId,
					toNamespace + fieldValuesParameterList.get(18),
					Integer.valueOf(dataSelectionBean.getForecastLevelId().toString()));

			List<Object> emptyInputList = new ArrayList<>();
			emptyInputList.add("");

			gtnUIFrameworkComponent = new GtnUIFrameworkComboboxComponent();
			gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					fieldValuesParameterList.get(19), componentId, emptyInputList);
			gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					fieldValuesParameterList.get(20), componentId, emptyInputList);
			gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					fieldValuesParameterList.get(21), componentId, emptyInputList);

			gtnUIFrameworkSetter.loadFieldValue(sourceComponentId, toNamespace + fieldValuesParameterList.get(22),
					dataSelectionBean.getProductGroupName());
			gtnUIFrameworkSetter.loadComboBoxComponentValue(sourceComponentId,
					toNamespace + fieldValuesParameterList.get(23), dataSelectionBean.getInnerProductLevelId());

			loadSelectedProductsTable(toNamespace + fieldValuesParameterList.get(24),
					dataSelectionBean.getSelectedProducts().subList(1, dataSelectionBean.getSelectedProducts().size()),
					sourceComponentId);
		} catch (Exception ex) {
			throw new GtnFrameworkValidationFailedException("Error in Fetch Data By Default Value Method", ex);
		}
	}

	private void loadSelectedProductsTable(String componentId, List<String> inputList, String sourceComponentId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentId, sourceComponentId);
		gtnUIFrameworkBaseComponent.loadDualListBoxRightTableData(inputList, sourceComponentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}