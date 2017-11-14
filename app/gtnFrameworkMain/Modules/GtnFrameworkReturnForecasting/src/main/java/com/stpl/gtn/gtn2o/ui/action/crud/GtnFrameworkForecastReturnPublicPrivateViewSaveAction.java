package com.stpl.gtn.gtn2o.ui.action.crud;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkComponentValueSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkForecastReturnPublicPrivateViewSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkForecastReturnPublicPrivateViewSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentValueSetter gtnUIFrameworkSetter = new GtnUIFrameworkComponentValueSetter();
		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		GtnForecastBean projMasterBean = new GtnForecastBean();
		try {

			projMasterBean.setProjectionName(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId)
					.getStringFromField());
			projMasterBean.setProjectionDescription(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(1), componentId)
					.getStringFromField());
			projMasterBean.setCompanyMasterSid(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(2), componentId)
					.getIntegerFromField());
			projMasterBean.setBusinessUnitId(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(3), componentId)
					.getIntegerFromField());
			projMasterBean.setModuleName(gtnUIFrameWorkActionConfig.getFieldValues().get(4));

			projMasterBean.setProjectionStartDate(getDateFromQuarter(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(5), componentId)
					.getCaptionFromComboBox()));

			projMasterBean.setProjectionEndDate(getDateFromQuarter(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(6), componentId)
					.getCaptionFromComboBox()));
			String valueFromProductHierarchyView = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(7), componentId)
					.getStringFromField();
			Object valueOfProductHierarchySid = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(7), componentId)
					.getIdFromField();
			if (!"".equals(valueFromProductHierarchyView) && valueOfProductHierarchySid != null) {

				projMasterBean.setProductHierarchySid(Integer.valueOf(String.valueOf(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(7), componentId)
						.getIdFromField())));
			}
			projMasterBean.setProductHierarchyLevel(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(8), componentId)
					.getIntegerFromField());
			projMasterBean.setProductHierarchyVersionNo(1);
			String valueFromItemGroupView = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(9), componentId)
					.getStringFromField();
			Object valueOfItemMasterSid = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(9), componentId)
					.getIdFromField();
			if (!"".equals(valueFromItemGroupView) && valueOfItemMasterSid != null) {
				projMasterBean.setItemGroupSid(Integer.valueOf(String.valueOf(valueOfItemMasterSid)));
			}
			projMasterBean.setProductHierarchyInnerLevel(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(10), componentId)
					.getIntegerFromField());
			projMasterBean.setProdRelationshipBuilderSid(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(11), componentId)
					.getIntegerFromField());
			projMasterBean.setCreatedBy(Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser()));

			projMasterBean.setRelationshipSidList(getValueFromDualListBoxRelationshipSid(
					gtnUIFrameWorkActionConfig.getFieldValues().get(12), componentId));
			projMasterBean.setViewName(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(13), componentId)
					.getStringFromField());
			projMasterBean.setViewType(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(14), componentId)
					.getStringFromField());
			projMasterBean = validationAlert(gtnUIFrameWorkActionConfig, componentId, projMasterBean,
					gtnUIFrameworkSetter);
			gtnLogger.info("Configuration Completed");
			request.setGtnWsForecastRequest(forecastRequest);
			request.getGtnWsForecastRequest().setGtnForecastBean(projMasterBean);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_VIEW_SAVE_SERVICE,
					GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			boolean saveFalg = response.getGtnWsGeneralResponse().isSucess();
			String msg = "";
			Object msgHeader = "View Added Successfully";

			if (saveFalg) {
				msg += "You have successfully ";

				if ("update".equals(actionParametersList.get(1).toString())) {
					msg += "updated ";
				} else {
					msg += "added ";
				}
				msg += projMasterBean.getViewType().toLowerCase() + " view ( " + projMasterBean.getViewName() + " )";

				GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
				GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();

				alertActionConfig.setActionParameterList(Arrays.asList(msgHeader, msg));
				alertAction.doAction(componentId, alertActionConfig);
				throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
			}

		} catch (Exception ex) {

			throw new GtnFrameworkGeneralException(
					"Error in GtnFrameworkForecastReturnPublicPrivateViewSaveAction class doAction method", ex);
		}
	}

	private GtnForecastBean validationAlert(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String componentId,
			GtnForecastBean projMasterBean, GtnUIFrameworkComponentValueSetter gtnUIFrameworkSetter)
			throws GtnFrameworkGeneralException {
		String viewName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(13), componentId)
				.getStringFromField();
		String viewType = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(14), componentId)
				.getStringFromField();
		Object msg;
		if (GtnFrameworkForecastConstantCommon.UPDATE
				.equals(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString())) {
			GtnForecastBean bean = (GtnForecastBean) GtnUIFrameworkGlobalUI
					.getVaadinComponentData(gtnUIFrameWorkActionConfig.getFieldValues().get(15), componentId)
					.getCustomData();
			boolean sameUser = bean.getViewCreatedBy() == Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser());
			boolean sameViewName = bean.getViewName().equals(viewName);

			if (!sameUser) {
				msg = "You cannot update " + viewType + " View (" + viewName
						+ ") because it was created by another user.You can choose to save a new profile under a different profile name ";

				GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
				GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();

				alertActionConfig.setActionParameterList(Arrays.asList("Cannot update " + viewType + " view", msg));
				alertAction.doAction(componentId, alertActionConfig);
				throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
			} else if (!sameViewName) {
				msg = "View  name can't be Changed";

				GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
				GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();

				alertActionConfig.setActionParameterList(Arrays.asList("Cannot update view name", msg));
				alertAction.doAction(componentId, alertActionConfig);
				throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
			} else {
				popupCloseAction(componentId, gtnUIFrameWorkActionConfig, gtnUIFrameworkSetter);

			}

			projMasterBean.setViewMode(true);
			projMasterBean.setProjectionMasterSid(bean.getProjectionMasterSid());
		} else {
			GtnForecastBean gtnForecastBean = loadDataFromServices(viewType);
			boolean duplicateViewName = gtnForecastBean.getExistingViewName().contains(viewName);
			if (duplicateViewName) {
				msg = "The " + viewType
						+ " View name you have attempted to save is a duplicate of an existing view name."
						+ "Please enter a different view name";

				GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
				GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();

				alertActionConfig.setActionParameterList(Arrays.asList("Duplicate View Name", msg));
				alertAction.doAction(componentId, alertActionConfig);
				throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
			} else {
				popupCloseAction(componentId, gtnUIFrameWorkActionConfig, gtnUIFrameworkSetter);
			}
		}
		return projMasterBean;
	}

	private void popupCloseAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig,
			GtnUIFrameworkComponentValueSetter gtnUIFrameworkSetter) throws GtnFrameworkGeneralException {
		gtnUIFrameworkSetter.loadFieldValue(componentId, gtnUIFrameWorkActionConfig.getFieldValues().get(13), "");
		gtnUIFrameworkSetter.loadFieldValue(componentId, gtnUIFrameWorkActionConfig.getFieldValues().get(14),
				gtnUIFrameWorkActionConfig.getFieldValues().get(16));
		GtnUIFrameWorkActionConfig closeOnSaveActionConfig = new GtnUIFrameWorkActionConfig();
		closeOnSaveActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeOnSaveActionConfig.addActionParameter(gtnUIFrameWorkActionConfig.getFieldValues().get(17));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, closeOnSaveActionConfig);

	}

	private GtnForecastBean loadDataFromServices(String viewType) {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastRequest gtnWsForecastRequest = new GtnWsForecastRequest();
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		gtnForecastBean.setViewType(viewType);
		gtnWsForecastRequest.setGtnForecastBean(gtnForecastBean);
		request.setGtnWsForecastRequest(gtnWsForecastRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_GET_EXISTING_VIEW_DETAILS_SERVICE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return response.getGtnWsForecastResponse().getGtnForecastBean();
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
