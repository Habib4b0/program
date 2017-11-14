package com.stpl.gtn.gtn2o.ui.action.crud;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;

public class GtnFrameworkForecastReturnSaveAndUpdateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastReturnSaveAndUpdateAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info(" inside GtnFrameworkForecastReturnSaveAndUpdateAction ");
		List<String> fieldValues = gtnUIFrameWorkActionConfig.getFieldValues();

		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		try {

			GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(fieldValues.get(0), componentId);
			GtnForecastBean projMasterBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();

			GtnWsForecastRequest gtnWsForecastRequest = new GtnWsForecastRequest();
			gtnWsForecastRequest.setGtnForecastBean(projMasterBean);
			projMasterBean.setProjectionName(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(1), componentId).getStringFromField());
			projMasterBean.setProjectionDescription(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(2), componentId).getStringFromField());
			projMasterBean.setCompanyMasterSid(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(3), componentId).getIntegerFromField());
			projMasterBean.setBusinessUnitId(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(4), componentId).getIntegerFromField());
			projMasterBean.setModuleName(fieldValues.get(5));
			projMasterBean.setProjectionStartDate(getDateFromQuarter(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(6), componentId).getCaptionFromComboBox()));

			projMasterBean.setProjectionEndDate(getDateFromQuarter(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(7), componentId).getCaptionFromComboBox()));
			projMasterBean.setProductHierarchySid(Integer.valueOf(String.valueOf(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(8), componentId).getIdFromField())));
			projMasterBean.setProductHierarchyLevel(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(9), componentId).getIntegerFromField());
			projMasterBean.setProductHierarchyVersionNo(1);
			String valueFromItemGroupView = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(10), componentId).getStringFromField();
			Object valueOfItemMasterSid = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(10), componentId).getIdFromField();
			if (!"".equals(valueFromItemGroupView) && valueOfItemMasterSid != null) {
				projMasterBean.setItemGroupSid(Integer.valueOf(String.valueOf(valueOfItemMasterSid)));
			}
			projMasterBean.setProductHierarchyInnerLevel(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(11), componentId).getIntegerFromField());
			projMasterBean.setProdRelationshipBuilderSid(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(12), componentId).getIntegerFromField());
			projMasterBean.setCreatedBy(Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser()));

			projMasterBean
					.setRelationshipSidList(getValueFromDualListBoxRelationshipSid(fieldValues.get(13), componentId));
			String actualsProjection = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(14), componentId)
					.getStringFromField();
			projMasterBean.setForecastType(actualsProjection == null ? "" : actualsProjection);
			projMasterBean.setProjectionPeriodOrder(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(15), componentId).getStringFromField());
			projMasterBean.setHistory(String.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(16), componentId).getIntegerFromField()));
			String forecastReturnsFrequency = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(fieldValues.get(17), componentId).getCaptionFromComboBox();
			projMasterBean.setFrequency(forecastReturnsFrequency != null ? forecastReturnsFrequency : "");
			if (GtnFrameworkForecastConstantCommon.EDIT_MODE.equals(actionParametersList.get(1).toString())
					|| projMasterBean.isMultipleSaveFalg() || projMasterBean.getProjectionMasterSid() != null) {
				projMasterBean.setUpdateFlag(true);
			} else if (GtnFrameworkForecastConstantCommon.SUBMIT_MODE.equals(actionParametersList.get(1).toString())) {
				projMasterBean.setSubmitFlag(true);
			} else {
				projMasterBean.setUpdateFlag(false);
			}

			gtnLogger.info("Configuration Completed");
			request.setGtnWsForecastRequest(forecastRequest);
			request.getGtnWsForecastRequest().setGtnForecastBean(projMasterBean);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_MASTER_SAVE_SERVICE,
					GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnForecastBean gtnForecastBeanresponse = response.getGtnWsForecastResponse().getGtnForecastBean();
			projMasterBean.setProjectionMasterSid(gtnForecastBeanresponse.getProjectionMasterSid());
			projMasterBean = buttonEnableDisableLogic(projMasterBean, actionParametersList.get(1).toString(),
					fieldValues, componentId);
			gtnUIFrameworkComponentData.setCustomData(projMasterBean);

		} catch (Exception exception) {
			gtnLogger.error(exception.getMessage(), exception);
			throw new GtnFrameworkGeneralException("Save Error", exception);
		}
	}

	private GtnForecastBean buttonEnableDisableLogic(GtnForecastBean forecastBean, String mode,
			List<String> fieldValues, String sourceComponentId) {
		if (forecastBean.getProjectionMasterSid() != null) {
			AbstractComponent abstractComponentSave = GtnUIFrameworkGlobalUI.getVaadinComponent(fieldValues.get(18),
					sourceComponentId);
			Button componentSaveButton = (Button) abstractComponentSave;
			componentSaveButton.setVisible(false);

			AbstractComponent abstractComponentUpdate = GtnUIFrameworkGlobalUI.getVaadinComponent(fieldValues.get(19),
					sourceComponentId);
			Button componentUpdateButton = (Button) abstractComponentUpdate;
			componentUpdateButton.setVisible(true);

			if ("add".equals(mode)) {
				forecastBean.setMultipleSaveFalg(true);
			}
		}
		final Notification notif = new Notification(forecastBean.getProjectionName() + " has been sucessfully saved",
				Notification.Type.HUMANIZED_MESSAGE);
		notif.setPosition(Position.TOP_CENTER);
		notif.setStyleName("mystyle");
		notif.setDelayMsec(3000);
		notif.show(Page.getCurrent());

		return forecastBean;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private List<String> getValueFromDualListBoxRelationshipSid(String componentId, String sourceComponentId) {
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentId, sourceComponentId);
		List<GtnWsRecordBean> rightTableBeanList = gtnUIFrameworkBaseComponent.getDualListBoxRightTableData();
		return getHierarchyNoAsList(rightTableBeanList);
	}

	private List<String> getHierarchyNoAsList(List<GtnWsRecordBean> beanList) {
		List<String> hierarchyNoList = new ArrayList<>();
		for (GtnWsRecordBean bean : beanList) {
			hierarchyNoList.add(bean.getAdditionalProperties().get(1).toString());
		}
		return hierarchyNoList;
	}

	public static Date getDateFromQuarter(String quarter) throws ParseException {
		SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");
		String hyphen = "-";
		String dd = "01";
		String mm = "01";
		String dateString = "";
		Date date;
		String[] split = quarter.split(" - ");
		String splitQuarter = split[0].replace("Q", "");
		int quarterValue = Integer.parseInt(splitQuarter);
		String yyyy = split[1];
		if (quarterValue == 1) {
			mm = "01";
		} else if (quarterValue == 2) {
			mm = "04";
		} else if (quarterValue == 3) {
			mm = "07";
		} else if (quarterValue == 4) {
			mm = "10";
		}

		dateString = yyyy + hyphen + mm + hyphen + dd;
		date = parse.parse(dateString);

		return date;
	}

}
