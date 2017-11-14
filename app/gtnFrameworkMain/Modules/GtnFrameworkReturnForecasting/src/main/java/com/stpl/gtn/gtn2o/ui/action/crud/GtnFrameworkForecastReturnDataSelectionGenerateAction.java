package com.stpl.gtn.gtn2o.ui.action.crud;

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
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkComponentValueSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkSessionConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.ComboBox;

/**
 *
 * @author STPL
 */
public class GtnFrameworkForecastReturnDataSelectionGenerateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkForecastReturnDataSelectionGenerateAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			gtnLogger.info(" inside GtnFrameworkForecastReturnDataSelectionGenerateAction");
			List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
			GtnUIFrameworkComponentData gtnUIFrameworkTableComponent = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(actionParametersList.get(12).toString(), componentId);

			FreezePagedTreeTable table = (FreezePagedTreeTable) gtnUIFrameworkTableComponent.getCustomDataList().get(0);
			table.getRightFreezeAsTable().setEditable(true);
			table.getRightFreezeAsTable().setEnabled(true);
			table.getLeftFreezeAsTable().setEnabled(true);
			GtnForecastBean gtnForecastBean = createGtnForecastBean(actionParametersList, componentId);

			gtnForecastBean = configureForecastandProjectionDate(gtnForecastBean, actionParametersList, componentId);
			gtnForecastBean
					.setQueryParameters(createParametersForQuery(actionParametersList, gtnForecastBean, componentId));
			gtnForecastBean.setTestFilePath("");

			GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
			forecastRequest.setGtnForecastBean(gtnForecastBean);
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			request.setGtnWsForecastRequest(forecastRequest);
			setDefaultValueIdFromComboBox(actionParametersList, componentId);
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			wsclient.callGtnWebServiceUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_CREATE_FILE_SERVICE,
					GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkSessionConstants.DS_GENERATED_FLAG, true);
			GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(actionParametersList.get(13).toString(), componentId);

			gtnUIFrameworkComponentData.setCustomData(gtnForecastBean);

			/**
			 * Trigger Returns Projection tab Generate button to reload all the
			 * components
			 */
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("returnsProjectionGenerateBtn", componentId).clickButton();

		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException(
					"Error in GtnFrameworkForecastReturnDataSelectionGenerateAction class doAction method", ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private GtnForecastBean createGtnForecastBean(List<Object> actionParametersList, String componentId)
			throws GtnFrameworkValidationFailedException {
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		gtnForecastBean.setModuleName(actionParametersList.get(1).toString());
		gtnForecastBean.setHierarchyType(actionParametersList.get(2).toString());
		gtnForecastBean.setFrequency(actionParametersList.get(3).toString());
		gtnForecastBean
				.setHistory(String.valueOf(getValueFromComboBox(actionParametersList.get(8).toString(), componentId)));
		gtnForecastBean.setRelationshipBuilderSid(
				getValueFromParantViewComboBox(actionParametersList.get(4).toString(), componentId));
		gtnForecastBean
				.setForecastLevel(getValueFromParantViewComboBox(actionParametersList.get(6).toString(), componentId));
		gtnForecastBean.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());

		if (String.valueOf(actionParametersList.get(11)) != null) {
			if ("forecastReturnsResetButton".equals(String.valueOf(actionParametersList.get(11)))) {
				GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
						.getVaadinComponentData(actionParametersList.get(13).toString(), componentId);

				GtnForecastBean gtnForecastDefaultBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();
				gtnForecastBean.setForecastSessionId(gtnForecastDefaultBean.getForecastSessionId());
			} else {
				gtnForecastBean.setForecastSessionId(createForecastSessionId());
			}
		} else {
			gtnForecastBean.setForecastSessionId(createForecastSessionId());
		}
		return gtnForecastBean;
	}

	private GtnForecastBean configureForecastandProjectionDate(GtnForecastBean gtnForecastBean,
			List<Object> actionParametersList, String sourceComponentId) throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();

		gtnForecastBean.setProjectionStartDate(
				getDateFromQuarter(getCaptionFromComboBox(actionParametersList.get(9).toString(), sourceComponentId)));
		gtnForecastBean.setProjectionEndDate(
				getDateFromQuarter(getCaptionFromComboBox(actionParametersList.get(10).toString(), sourceComponentId)));

		forecastRequest.setGtnForecastBean(gtnForecastBean);
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = wsclient.callGtnWebServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_DATE_CONFIGURATION_SERVICE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return gtnUIFrameworkWebserviceResponse.getGtnWsForecastResponse().getGtnForecastBean();

	}

	@SuppressWarnings("deprecation")
	private Map<String, List<String>> createParametersForQuery(List<Object> actionParametersList,
			GtnForecastBean gtnForecastBean, String componentId) {

		Map<String, List<String>> map = new HashMap<>(3);
		List<String> masterQueryInputs = new ArrayList<>(1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		masterQueryInputs.add(getValueFromDualListBox(actionParametersList.get(5).toString(), componentId));
		masterQueryInputs.add(simpleDateFormat.format(gtnForecastBean.getForecastStartDate()));
		masterQueryInputs.add(simpleDateFormat.format(gtnForecastBean.getForecastEndDate()));
		map.put("RETURNS_FORECAST_MASTER", masterQueryInputs);

		List<String> actualQueryInputs = new ArrayList<>(3);
		actualQueryInputs.add(getValueFromDualListBox(actionParametersList.get(5).toString(), componentId));
		Date startDate = new Date(gtnForecastBean.getHistoryStartYear() - 1900, gtnForecastBean.getHistoryStartMonth(),
				1);
		Date endDate = new Date(gtnForecastBean.getHistoryEndYear() - 1900, gtnForecastBean.getHistoryEndMonth(), 1);

		actualQueryInputs.add(simpleDateFormat.format(startDate));
		actualQueryInputs.add(simpleDateFormat.format(endDate));
		map.put("RETURNS_FORECAST_ACTUAL", actualQueryInputs);

		List<String> projectionQueryInputs = new ArrayList<>(3);
		projectionQueryInputs.add(getValueFromDualListBox(actionParametersList.get(5).toString(), componentId));
		projectionQueryInputs.add(simpleDateFormat.format(gtnForecastBean.getForecastStartDate()));
		projectionQueryInputs.add(simpleDateFormat.format(gtnForecastBean.getForecastEndDate()));
		map.put("RETURNS_FORECAST_PROJECTION", projectionQueryInputs);

		return map;
	}

	private String getValueFromComboBox(String componentId, String sourceComponentId)
			throws GtnFrameworkValidationFailedException {
		return String.valueOf(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId).getValueFromComponent());
	}

	private int getValueFromParantViewComboBox(String componentId, String sourceComponentId)
			throws GtnFrameworkValidationFailedException {
		String value = String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(componentId, sourceComponentId).getValueFromComponent());
		return Integer.parseInt(value);

	}

	private String getCaptionFromComboBox(String componentId, String sourceComponentId)
			throws GtnFrameworkValidationFailedException {
		return GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(componentId, sourceComponentId)
				.getCaptionFromComboBox();
	}

	private String getValueFromDualListBox(String componentId, String sourceComponentId) {

		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(componentId, sourceComponentId);
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

	private String createForecastSessionId() {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("hhmmssSSS");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private void setDefaultValueIdFromComboBox(List<Object> actionParametersList, String sourceComponentId) {
		ComboBox comboBox = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent(actionParametersList.get(7).toString(),
				sourceComponentId);
		for (Object object : comboBox.getItemIds()) {
			if (actionParametersList.get(3).equals(comboBox.getItemCaption(object))) {
				GtnUIFrameworkComponentValueSetter gtnUIFrameworkSetter = new GtnUIFrameworkComponentValueSetter();
				gtnUIFrameworkSetter.loadComboBoxComponentValue(sourceComponentId,
						actionParametersList.get(7).toString(), Integer.valueOf(object.toString()));
			}
		}
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