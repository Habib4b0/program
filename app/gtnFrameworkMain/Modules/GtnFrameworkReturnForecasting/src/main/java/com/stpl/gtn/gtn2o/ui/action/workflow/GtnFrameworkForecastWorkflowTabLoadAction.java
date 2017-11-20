package com.stpl.gtn.gtn2o.ui.action.workflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkComponentValueSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnFrameworkForecastWorkflowTabLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastWorkflowTabLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info(" inside GtnFrameworkForecastWorkflowAction ");

		List<String> fieldValues = gtnUIFrameWorkActionConfig.getFieldValues();

		GtnUIFrameworkComponentValueSetter gtnUIFrameworkSetter = new GtnUIFrameworkComponentValueSetter();

		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();

		Map<String, String> projectionDetails = (Map<String, String>) actionParametersList.get(10);

		GtnForecastBean projMasterBean = new GtnForecastBean();
		projMasterBean.setProjectionMasterSid(projectionDetails.get(String.valueOf(actionParametersList.get(4))));
		projMasterBean.setWorkflowId(projectionDetails.get(String.valueOf(actionParametersList.get(5))));
		projMasterBean.setWorkflowStatus(projectionDetails.get(String.valueOf(actionParametersList.get(6))));
		projMasterBean.setUserType(projectionDetails.get(String.valueOf(actionParametersList.get(7))));
		projMasterBean
				.setNoOfApprovals(Integer.valueOf(projectionDetails.get(String.valueOf(actionParametersList.get(8)))));
		projMasterBean
				.setApprovalLevels(Integer.valueOf(projectionDetails.get(String.valueOf(actionParametersList.get(9)))));

		projMasterBean = getProjectionDetails(projMasterBean);

		gtnUIFrameworkSetter.loadFieldValue(componentId, fieldValues.get(3), projMasterBean.getProjectionName());
		gtnUIFrameworkSetter.loadFieldValue(componentId, fieldValues.get(4), projMasterBean.getProjectionDescription());
		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(5), projMasterBean.getToPeriod());
		gtnUIFrameworkSetter.loadFieldValue(componentId, fieldValues.get(6), projMasterBean.getProductHierarchySid());
		GtnUIFrameworkGlobalUI.getVaadinComponentData(fieldValues.get(6), componentId)
				.setCustomData(projMasterBean.getProductHierarchySid());

		List<Object> reloadInput = new ArrayList<>();
		reloadInput.add(projMasterBean.getProductHierarchySid());
		GtnUIFrameworkComboboxComponent gtnUIFrameworkComponent = new GtnUIFrameworkComboboxComponent();
		gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, fieldValues.get(9),
				componentId, reloadInput);
		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(7),
				projMasterBean.getCompanyMasterSid());
		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(8),
				projMasterBean.getBusinessUnitId());
		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(9),
				projMasterBean.getProdRelationshipBuilderSid());
		gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, fieldValues.get(10),
				componentId, reloadInput);

		projMasterBean.setForecastSessionId(createForecastSessionId());
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(fieldValues.get(22), componentId);
		gtnUIFrameworkComponentData.setCustomData(projMasterBean);

		String tempSessionId = projMasterBean.getForecastSessionId();

		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(10),
				projMasterBean.getProductForecastLevel());

		List<Object> emptyInputList = new ArrayList<>();
		emptyInputList.add("");
		gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, fieldValues.get(11),
				componentId, emptyInputList);
		gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, fieldValues.get(12),
				componentId, emptyInputList);
		gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, fieldValues.get(13),
				componentId, emptyInputList);

		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(14),
				projMasterBean.getProductHierarchyInnerLevel());

		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(15),
				projMasterBean.getFromPeriod());

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(16),
				Integer.valueOf(projMasterBean.getFrequency()));
		gtnUIFrameworkSetter.loadComboBoxComponentValue(componentId, fieldValues.get(17),
				Integer.valueOf(projMasterBean.getHistory()));
		gtnUIFrameworkSetter.loadFieldValue(componentId, fieldValues.get(18),
				String.valueOf(projMasterBean.getForecastType()));
		gtnUIFrameworkSetter.loadFieldValue(componentId, fieldValues.get(19),
				String.valueOf(projMasterBean.getProjectionPeriodOrder()));

		List<String> levelNoList = projMasterBean.getRightTableHierarchy()
				.get(String.valueOf(projMasterBean.getProductForecastLevel()));
		projMasterBean.setRelationshipSidList(levelNoList);

		projMasterBean.setModuleName(String.valueOf(actionParametersList.get(1)));
		projMasterBean = configureForecastandProjectionDate(projMasterBean, fieldValues, componentId);
		projMasterBean.setHierarchyType(String.valueOf(actionParametersList.get(2)));
		Map<String, List<String>> map = new HashMap<>(2);
		List<String> actualQueryInputs = new ArrayList<>(3);
		actualQueryInputs.add(projMasterBean.getSelectedHierarchyNo());

		Date startDate = new Date(projMasterBean.getHistoryStartYear() - 1900, projMasterBean.getHistoryStartMonth(),
				1);
		Date endDate = new Date(projMasterBean.getHistoryEndYear() - 1900, projMasterBean.getHistoryEndMonth(), 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		actualQueryInputs.add(simpleDateFormat.format(startDate));
		actualQueryInputs.add(simpleDateFormat.format(endDate));
		map.put("RETURNS_FORECAST_ACTUAL_EDIT", actualQueryInputs);
		GtnUIFrameworkComponentData gtnUIFrameworkTableComponent = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(fieldValues.get(21), componentId);

		FreezePagedTreeTable table = (FreezePagedTreeTable) gtnUIFrameworkTableComponent.getCustomDataList().get(0);
		table.getRightFreezeAsTable().setTableFieldFactory(null);
		table.getRightFreezeAsTable().setEditable(false);
		table.getRightFreezeAsTable().setEnabled(false);

		projMasterBean.setQueryParameters(map);

		projMasterBean.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		projMasterBean.setForecastSessionId(tempSessionId);

		projMasterBean.setRelationshipBuilderSid(projMasterBean.getProdRelationshipBuilderSid());
		projMasterBean.setForecastLevel(projMasterBean.getProductForecastLevel());
		projMasterBean.setProductHierarchySid(projMasterBean.getProductHierarchySid());
		projMasterBean.setMode(String.valueOf(actionParametersList.get(3)));
		projMasterBean.setTestFilePath("");
		projMasterBean.setWorkflowFlag(true);

		gtnUIFrameworkComponentData.setCustomData(projMasterBean);

		forecastRequest.setGtnForecastBean(projMasterBean);
		request.setGtnWsForecastRequest(forecastRequest);
		wsclient.callGtnWebServiceUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_CREATE_FILE_ON_EDIT_SERVICE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		((Button) GtnUIFrameworkGlobalUI.getVaadinComponent(fieldValues.get(23), componentId)).click();
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
			List<String> fieldValues, String sourceComponentId) {
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

	private String getCaptionFromComboBox(String componentId, String sourceComponentId) {
		ComboBox comboBox = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId, sourceComponentId);
		return comboBox.getItemCaption((Integer.valueOf(comboBox.getValue().toString())));
	}

	private Date getDateFromQuarter(String quarter) {
		String[] tempString = quarter.split("-");
		Calendar calendar = Calendar.getInstance();
		int month = (Integer.valueOf(tempString[0].trim().substring(1)) - 1) * 3;
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, Integer.valueOf(tempString[1].trim()));
		return calendar.getTime();
	}

	private GtnForecastBean getProjectionDetails(GtnForecastBean gtnForecastBean) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setGtnForecastBean(gtnForecastBean);
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = wsclient.callGtnWebServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_PROJECTION_DETAILS_FOR_WORKFLOW_SERVICE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return gtnUIFrameworkWebserviceResponse.getGtnWsForecastResponse().getGtnForecastBean();
	}
}
