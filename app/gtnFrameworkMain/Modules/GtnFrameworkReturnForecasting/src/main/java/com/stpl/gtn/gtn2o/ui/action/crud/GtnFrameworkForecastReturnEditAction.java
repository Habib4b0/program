package com.stpl.gtn.gtn2o.ui.action.crud;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkComponentValueSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Stpl
 */
public class GtnFrameworkForecastReturnEditAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastReturnEditAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("deprecation")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentValueSetter gtnUIFrameworkSetter = new GtnUIFrameworkComponentValueSetter();
		List<String> fieldValues = gtnUIFrameWorkActionConfig.getFieldValues();
		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsRecordBean currentData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParametersList.get(1).toString(), componentId)
				.getValueFromDataTable();

		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
				.getComponentData();
		CustomWindow vaadinComponent = componentData.getCustomWindow();

		vaadinComponent.setCaption(currentData.getPropertyValueByIndex(0).toString());

		if (GtnFrameworkForecastConstantCommon.EDIT_MODE.equals(actionParametersList.get(3).toString())) {

			gtnLogger.debug("inside edit " + actionParametersList.get(3));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(1), componentId).setVisible(false);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(2), componentId).setVisible(true);

		} else {

			gtnLogger.debug("inside edit " + actionParametersList.get(3));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(1), componentId).setVisible(true);
		}
		String projectionMasterSid = String
				.valueOf(currentData.getPropertyValueByIndex(currentData.getProperties().size() - 1));
		GtnForecastBean gtnForecastBean = loadDataFromService(projectionMasterSid);
		List<String> levelNoList = gtnForecastBean.getRightTableHierarchy()
				.get(String.valueOf(currentData.getPropertyValueByIndex(3)));
		gtnForecastBean.setRelationshipSidList(levelNoList);

		gtnForecastBean.setProjectionMasterSid(projectionMasterSid);
		gtnForecastBean.setModuleName(actionParametersList.get(2).toString());
		gtnForecastBean = configureForecastandProjectionDate(gtnForecastBean, fieldValues, componentId);
		gtnForecastBean.setHierarchyType(String.valueOf(actionParametersList.get(4)));

		gtnForecastBean.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		gtnForecastBean.setForecastSessionId(createForecastSessionId());

		gtnForecastBean.setRelationshipBuilderSid(Integer.valueOf(currentData.getPropertyValueByIndex(13).toString()));
		gtnForecastBean.setForecastLevel(Integer.valueOf(currentData.getPropertyValueByIndex(3).toString()));
		gtnForecastBean.setProductHierarchySid(Integer.valueOf(currentData.getPropertyValueByIndex(12).toString()));
		gtnForecastBean.setMode(actionParametersList.get(3).toString());
		gtnForecastBean.setTestFilePath("");

		Map<String, List<String>> map = new HashMap<>(2);
		List<String> actualQueryInputs = new ArrayList<>(3);
		actualQueryInputs.add(gtnForecastBean.getSelectedHierarchyNo());

		if (GtnFrameworkForecastConstantCommon.EDIT_MODE.equals(actionParametersList.get(3).toString())) {
			Date startDate = new Date(gtnForecastBean.getHistoryStartYear() - 1900,
					gtnForecastBean.getHistoryStartMonth(), 1);
			Date endDate = new Date(gtnForecastBean.getHistoryEndYear() - 1900, gtnForecastBean.getHistoryEndMonth(),
					1);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			actualQueryInputs.add(simpleDateFormat.format(startDate));
			actualQueryInputs.add(simpleDateFormat.format(endDate));
			map.put("RETURNS_FORECAST_ACTUAL_EDIT", actualQueryInputs);
			GtnUIFrameworkComponentData gtnUIFrameworkTableComponent = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(fieldValues.get(21), componentId);
			FreezePagedTreeTable table = (FreezePagedTreeTable) gtnUIFrameworkTableComponent.getCustomDataList().get(0);
			table.getRightFreezeAsTable().setEditable(true);
			table.getRightFreezeAsTable().setEnabled(true);
			gtnForecastBean.setEditMode(true);

		} else {
			actualQueryInputs.add(gtnForecastBean.getProjectionMasterSid());
			map.put("RETURNS_FORECAST_ACTUAL_VIEW", actualQueryInputs);

			GtnUIFrameworkComponentData gtnUIFrameworkTableComponent = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(fieldValues.get(21), componentId);
			FreezePagedTreeTable table = (FreezePagedTreeTable) gtnUIFrameworkTableComponent.getCustomDataList().get(0);
			table.getRightFreezeAsTable().setTableFieldFactory(null);
			table.getRightFreezeAsTable().setEditable(false);
			table.getRightFreezeAsTable().setEnabled(false);
			gtnForecastBean.setViewModeFlag(true);

		}
		gtnForecastBean.setQueryParameters(map);

		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(fieldValues.get(22), componentId);
		gtnUIFrameworkComponentData.setCustomData(gtnForecastBean);

		gtnUIFrameworkSetter.loadFieldValue(componentId, fieldValues.get(3),
				currentData.getPropertyValueByIndex(0).toString());
		gtnUIFrameworkSetter.loadFieldValue(componentId, fieldValues.get(4),
				currentData.getPropertyValueByIndex(1).toString());
		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(5),
				Integer.valueOf(currentData.getPropertyValueByIndex(15).toString()));
		gtnUIFrameworkSetter.loadFieldValue(componentId, fieldValues.get(6),
				currentData.getPropertyValueByIndex(2).toString());
		GtnUIFrameworkGlobalUI.getVaadinComponentData(fieldValues.get(6), componentId)
				.setCustomData(Integer.valueOf(currentData.getPropertyValueByIndex(12).toString()));

		List<Object> reloadInput = new ArrayList<>();
		reloadInput.add(currentData.getPropertyValueByIndex(12));
		GtnUIFrameworkComboboxComponent gtnUIFrameworkComponent = new GtnUIFrameworkComboboxComponent();
		gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, fieldValues.get(9),
				componentId, reloadInput);
		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(7),
				Integer.valueOf(currentData.getPropertyValueByIndex(10).toString()));
		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(8),
				Integer.valueOf(currentData.getPropertyValueByIndex(9).toString()));
		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(9),
				Integer.valueOf(currentData.getPropertyValueByIndex(13).toString()));
		gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, fieldValues.get(10),
				componentId, reloadInput);
		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(10),
				Integer.valueOf(currentData.getPropertyValueByIndex(3).toString()));

		List<Object> emptyInputList = new ArrayList<>();
		emptyInputList.add("");
		gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, fieldValues.get(11),
				componentId, emptyInputList);
		gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, fieldValues.get(12),
				componentId, emptyInputList);
		gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, fieldValues.get(13),
				componentId, emptyInputList);

		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(14),
				Integer.valueOf(currentData.getPropertyValueByIndex(14).toString()));

		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(15),

				Integer.valueOf(currentData.getPropertyValueByIndex(16).toString()));

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();

		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(16),
				Integer.valueOf(gtnForecastBean.getFrequency()));
		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(17),
				Integer.valueOf(gtnForecastBean.getHistory()));
		gtnUIFrameworkSetter.loadFieldValue(componentId, fieldValues.get(18),
				String.valueOf(gtnForecastBean.getForecastType()));
		gtnUIFrameworkSetter.loadFieldValue(componentId, fieldValues.get(19),
				String.valueOf(gtnForecastBean.getProjectionPeriodOrder()));

		forecastRequest.setGtnForecastBean(gtnForecastBean);
		request.setGtnWsForecastRequest(forecastRequest);
		wsclient.callGtnWebServiceUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_CREATE_FILE_ON_EDIT_SERVICE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(23), componentId).clickButton();
	}

	private GtnForecastBean loadDataFromService(String projectionMasterSid) {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastRequest gtnWsForecastRequest = new GtnWsForecastRequest();
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		gtnForecastBean.setProjectionMasterSid(projectionMasterSid);
		gtnWsForecastRequest.setGtnForecastBean(gtnForecastBean);
		request.setGtnWsForecastRequest(gtnWsForecastRequest);

		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_GET_PROJECTION_SELECTION_DETAILS_SERVICE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return response.getGtnWsForecastResponse().getGtnForecastBean();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private String createForecastSessionId() {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("hhmmssSSS");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private GtnForecastBean configureForecastandProjectionDate(GtnForecastBean gtnForecastBean,
			List<String> fieldValues, String sourceComponentId) throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();

		gtnForecastBean.setProjectionStartDate(
				getDateFromQuarter(getCaptionFromComboBox(fieldValues.get(5), sourceComponentId)));
		gtnForecastBean.setProjectionEndDate(
				getDateFromQuarter(getCaptionFromComboBox(fieldValues.get(15), sourceComponentId)));

		forecastRequest.setGtnForecastBean(gtnForecastBean);
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = wsclient.callGtnWebServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_DATE_CONFIGURATION_SERVICE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return gtnUIFrameworkWebserviceResponse.getGtnWsForecastResponse().getGtnForecastBean();
	}

	private String getCaptionFromComboBox(String componentId, String sourceComponentId)
			throws GtnFrameworkValidationFailedException {
		return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId).getCaptionFromComboBox();
	}

	private Date getDateFromQuarter(String quarter) {
		String[] tempString = quarter.split("-");
		Calendar calendar = Calendar.getInstance();
		int month = (Integer.valueOf(tempString[0].trim().substring(1)) - 1) * 3;
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, Integer.valueOf(tempString[1].trim()));
		return calendar.getTime();
	}

}
