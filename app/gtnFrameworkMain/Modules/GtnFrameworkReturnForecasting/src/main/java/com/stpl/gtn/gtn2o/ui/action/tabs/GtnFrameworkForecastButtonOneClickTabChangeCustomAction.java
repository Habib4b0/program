
package com.stpl.gtn.gtn2o.ui.action.tabs;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
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
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TabSheet;

public class GtnFrameworkForecastButtonOneClickTabChangeCustomAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkForecastButtonOneClickTabChangeCustomAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info(" inside GtnFrameworkForecastButtonOneClickTabChangeCustomAction");

		GtnUIFrameWorkActionConfig tempActionConfig = (GtnUIFrameWorkActionConfig) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(1);

		List<Object> actionParameterList = tempActionConfig.getActionParameterList();
		final TabSheet tabSheet = (TabSheet) getAbstractComponent(actionParameterList.get(7).toString(), componentId);
		final Button previousButton = (Button) getAbstractComponent(actionParameterList.get(8).toString(), componentId);
		final Button nextButton = (Button) getAbstractComponent(actionParameterList.get(9).toString(), componentId);
		GtnForecastBean gtnForecastBean = (GtnForecastBean) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);

		reloadProjectionOnDataSelectionChange(gtnForecastBean, actionParameterList, componentId);
		saveValuesInDataSelectionProperties(gtnForecastBean, actionParameterList.get(11).toString(),
				tempActionConfig.getFieldValues(), componentId);
		tabSheet.setSelectedTab(1);
		reloadLevelsInReturnsTab(componentId, tempActionConfig);
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(13).toString(), componentId);
		gtnUIFrameworkBaseComponent.clickButton();
		nextButton.setVisible(false);
		previousButton.setVisible(true);

	}

	private void reloadLevelsInReturnsTab(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {
		List<Object> emptyInputList = new ArrayList<>();
		emptyInputList.add("");
		GtnUIFrameworkComboboxComponent gtnUIFrameworkComponent = new GtnUIFrameworkComboboxComponent();
		gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
				gtnUIFrameWorkActionConfig.getFieldValues().get(19), componentId, emptyInputList);
		gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
				gtnUIFrameWorkActionConfig.getFieldValues().get(20), componentId, emptyInputList);
		gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
				gtnUIFrameWorkActionConfig.getFieldValues().get(21), componentId, emptyInputList);
	}

	private void saveValuesInDataSelectionProperties(GtnForecastBean gtnForecastBean, String namespace,
			List<String> fieldValuesParameterList, String sourceComponentId)
			throws GtnFrameworkValidationFailedException {
		String prefix = namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE;
		DataSelectionBean dataSelectionBean = new DataSelectionBean();
		dataSelectionBean
				.setProjectionName(getStringValue(prefix + fieldValuesParameterList.get(9), sourceComponentId));
		dataSelectionBean
				.setProjectionDescription(getStringValue(prefix + fieldValuesParameterList.get(10), sourceComponentId));

		dataSelectionBean.setGlCompanyId(getIntegerValue(prefix + fieldValuesParameterList.get(5), sourceComponentId));
		dataSelectionBean
				.setBusinessUnitId(getIntegerValue(prefix + fieldValuesParameterList.get(7), sourceComponentId));

		dataSelectionBean.setFromDate(getIntegerValue(prefix + fieldValuesParameterList.get(12), sourceComponentId));
		dataSelectionBean.setToDate(getIntegerValue(prefix + fieldValuesParameterList.get(14), sourceComponentId));

		dataSelectionBean.setProductHierarchyId(
				(Serializable) getSelectedId(prefix + fieldValuesParameterList.get(16), sourceComponentId));
		dataSelectionBean
				.setProductHierarchyName(getStringValue(prefix + fieldValuesParameterList.get(16), sourceComponentId));

		dataSelectionBean
				.setRelationshipBuilderId(getStringValue(prefix + fieldValuesParameterList.get(17), sourceComponentId));
		dataSelectionBean
				.setForecastLevelId(getStringValue(prefix + fieldValuesParameterList.get(18), sourceComponentId));

		dataSelectionBean.setProductGroupName("");
		dataSelectionBean
				.setInnerProductLevelId(getIntegerValue(prefix + fieldValuesParameterList.get(23), sourceComponentId));
		dataSelectionBean.setSelectedProducts(
				getValueFromDualListBoxAsList(prefix + fieldValuesParameterList.get(24), sourceComponentId));

		callWebServiceToSaveDataSelection(gtnForecastBean, dataSelectionBean);

	}

	private void callWebServiceToSaveDataSelection(GtnForecastBean gtnForecastBean,
			DataSelectionBean dataSelectionBean) {
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setGtnForecastBean(gtnForecastBean);
		forecastRequest.setDataSelectionBean(dataSelectionBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		wsclient.callGtnWebServiceUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_WRITE_DATA_SELECTION_FILE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	private AbstractComponent getAbstractComponent(String componentId, String sourceComponentId) {
		return GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(componentId), sourceComponentId);
	}

	private void reloadProjectionOnDataSelectionChange(GtnForecastBean gtnForecastBean,
			List<Object> actionParameterList, String sourceComponentId) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(
				createGtnWsForecastRequest(actionParameterList, gtnForecastBean, sourceComponentId));
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		final String url = gtnForecastBean.isEditMode()
				? GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_CREATE_FILE_DS_TAB_CHANGE_SERVICE
				: GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_CREATE_FILE_SERVICE;
		wsclient.callGtnWebServiceUrl(url, GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	private GtnWsForecastRequest createGtnWsForecastRequest(List<Object> actionParameterList,
			GtnForecastBean gtnForecastBean, String sourceComponentId) {
		GtnWsForecastRequest request = new GtnWsForecastRequest();
		gtnForecastBean.setRelationshipBuilderSid(
				getValueFromComboBox(actionParameterList.get(2).toString(), sourceComponentId));
		gtnForecastBean
				.setForecastLevel(getValueFromComboBox(actionParameterList.get(3).toString(), sourceComponentId));
		request.setGtnForecastBean(gtnForecastBean);
		if (gtnForecastBean.isEditMode()) {
			gtnForecastBean.setQueryParameters(
					createParametersForQueryInEdit(actionParameterList, gtnForecastBean, sourceComponentId));
		} else {
			gtnForecastBean.setQueryParameters(
					createParametersForQuery(actionParameterList, gtnForecastBean, sourceComponentId));
		}
		return request;
	}

	@SuppressWarnings("deprecation")
	private Map<String, List<String>> createParametersForQueryInEdit(List<Object> actionParameterList,
			GtnForecastBean gtnForecastBean, String sourceComponentId) {

		Map<String, List<String>> map = new HashMap<>(3);
		List<String> masterQueryInputs = new ArrayList<>(1);
		masterQueryInputs.add(getValueFromDualListBox(actionParameterList.get(4).toString(), sourceComponentId));
		masterQueryInputs.add(gtnForecastBean.getProjectionMasterSid());
		map.put("RETURNS_FORECAST_MASTER_DS_RELOAD", masterQueryInputs);

		List<String> actualQueryInputs = new ArrayList<>(3);
		actualQueryInputs.add(getValueFromDualListBox(actionParameterList.get(4).toString(), sourceComponentId));

		Date startDate = new Date(gtnForecastBean.getHistoryStartYear() - 1900, gtnForecastBean.getHistoryStartMonth(),
				1);
		Date endDate = new Date(gtnForecastBean.getHistoryEndYear() - 1900, gtnForecastBean.getHistoryEndMonth(), 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		actualQueryInputs.add(simpleDateFormat.format(startDate));
		actualQueryInputs.add(simpleDateFormat.format(endDate));
		map.put("RETURNS_FORECAST_ACTUAL", actualQueryInputs);

		List<String> projectionQueryInputs = new ArrayList<>(3);
		projectionQueryInputs.add(getValueFromDualListBox(actionParameterList.get(4).toString(), sourceComponentId));
		projectionQueryInputs.add(simpleDateFormat.format(gtnForecastBean.getForecastStartDate()));
		projectionQueryInputs.add(simpleDateFormat.format(gtnForecastBean.getForecastEndDate()));
		projectionQueryInputs.add(gtnForecastBean.getProjectionMasterSid());
		map.put("RETURNS_FORECAST_PROJECTION_DS_RELOAD", projectionQueryInputs);

		return map;
	}

	@SuppressWarnings("deprecation")
	private Map<String, List<String>> createParametersForQuery(List<Object> actionParameterList,
			GtnForecastBean gtnForecastBean, String sourceComponentId) {

		Map<String, List<String>> map = new HashMap<>(3);
		List<String> masterQueryInputs = new ArrayList<>(1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		masterQueryInputs.add(getValueFromDualListBox(actionParameterList.get(4).toString(), sourceComponentId));
		masterQueryInputs.add(simpleDateFormat.format(gtnForecastBean.getForecastStartDate()));
		masterQueryInputs.add(simpleDateFormat.format(gtnForecastBean.getForecastEndDate()));
		map.put("RETURNS_FORECAST_MASTER", masterQueryInputs);

		List<String> actualQueryInputs = new ArrayList<>(3);
		actualQueryInputs.add(getValueFromDualListBox(actionParameterList.get(4).toString(), sourceComponentId));
		Date startDate = new Date(gtnForecastBean.getHistoryStartYear() - 1900, gtnForecastBean.getHistoryStartMonth(),
				1);
		Date endDate = new Date(gtnForecastBean.getHistoryEndYear() - 1900, gtnForecastBean.getHistoryEndMonth(), 1);
		actualQueryInputs.add(simpleDateFormat.format(startDate));
		actualQueryInputs.add(simpleDateFormat.format(endDate));
		map.put("RETURNS_FORECAST_ACTUAL", actualQueryInputs);

		List<String> projectionQueryInputs = new ArrayList<>(3);
		projectionQueryInputs.add(getValueFromDualListBox(actionParameterList.get(4).toString(), sourceComponentId));
		projectionQueryInputs.add(simpleDateFormat.format(gtnForecastBean.getForecastStartDate()));
		projectionQueryInputs.add(simpleDateFormat.format(gtnForecastBean.getForecastEndDate()));
		map.put("RETURNS_FORECAST_PROJECTION", projectionQueryInputs);

		return map;
	}

	private int getValueFromComboBox(String componentId, String sourceComponentId) {
		ComboBox comboBox = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId, sourceComponentId);
		return Integer.valueOf(comboBox.getValue().toString());
	}

	private String getValueFromDualListBox(String componentId, String sourceComponentId) {
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentId, sourceComponentId);
		List<GtnWsRecordBean> rightTableBeanList = gtnUIFrameworkBaseComponent.getDualListBoxRightTableData();
		return getHierarchyNoAsString(rightTableBeanList);
	}

	private String getHierarchyNoAsString(List<GtnWsRecordBean> beanList) {
		StringBuilder stringBuilder = new StringBuilder();
		for (GtnWsRecordBean bean : beanList) {
			stringBuilder.append("('");
			stringBuilder.append(bean.getAdditionalProperties().get(1));
			stringBuilder.append("')");
			stringBuilder.append(",");
		}
		return stringBuilder.substring(0, stringBuilder.length() - 1);
	}

	private String getStringValue(String componentId, String sourceComponentId)
			throws GtnFrameworkValidationFailedException {
		return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId).getStringFromField();
	}

	private int getIntegerValue(String componentId, String sourceComponentId)
			throws GtnFrameworkValidationFailedException {
		return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId).getIntegerFromField();
	}

	private Object getSelectedId(String componentId, String sourceComponentId)
			throws GtnFrameworkValidationFailedException {
		return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId).getIdFromField();
	}

	private List<String> getValueFromDualListBoxAsList(String componentId, String sourceComponentId) {
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentId, sourceComponentId);
		List<GtnWsRecordBean> rightTableBeanList = gtnUIFrameworkBaseComponent.getDualListBoxRightTableData();
		return getHierarchyNoList(rightTableBeanList);
	}

	private List<String> getHierarchyNoList(List<GtnWsRecordBean> beanList) {
		List<String> hierarchyNoList = new ArrayList<>();
		for (GtnWsRecordBean bean : beanList) {
			hierarchyNoList.add(bean.getAdditionalProperties().get(1).toString());
		}
		return hierarchyNoList;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
