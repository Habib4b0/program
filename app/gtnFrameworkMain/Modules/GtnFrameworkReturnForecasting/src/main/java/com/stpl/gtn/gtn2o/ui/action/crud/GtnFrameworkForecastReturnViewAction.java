package com.stpl.gtn.gtn2o.ui.action.crud;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkForecastReturnViewAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastReturnViewAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		Object[] settertArray = new Object[20];
		GtnWsRecordBean gtnWsRecordBean = null;
		int systemId;
		try {

			List<Object> actionParameter = gtnUIFrameWorkActionConfig.getActionParameterList();

			String namespace = (String) actionParameter.get(2);

			if (GtnFrameworkForecastConstantCommon.PUBLIC_VIEW_NAME.equals(actionParameter.get(1))) {
				gtnWsRecordBean = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
								+ gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId)
						.getValueFromPagedDataTable();
			} else if (GtnFrameworkForecastConstantCommon.PRIVATE_VIEW_NAME.equals(actionParameter.get(1))) {
				gtnWsRecordBean = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
								+ gtnUIFrameWorkActionConfig.getFieldValues().get(1), componentId)
						.getValueFromPagedDataTable();
			}
                    if(gtnWsRecordBean != null){
                        systemId = (Integer) gtnWsRecordBean.getPropertyValueByIndex(12);

			loadDataFromService(systemId, settertArray);

			setDataToService(gtnUIFrameWorkActionConfig, componentId, systemId, settertArray, gtnWsRecordBean);
}
			

		} catch (GtnFrameworkValidationFailedException exception) {
			gtnLogger.error("Error in doAction Method", exception);
			throw exception;
		} catch (Exception ex) {
			gtnLogger.error("Error in doAction Method", ex);
			throw new GtnFrameworkGeneralException("Error in doAction  Method", ex);
		}

	}

	private void setDataToService(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String componentId,
			int systemId, Object[] settertArray, GtnWsRecordBean gtnWsRecordBean) throws GtnFrameworkGeneralException {

		try {

			GtnUIFrameworkSetter gtnUIFrameworkSetter = new GtnUIFrameworkSetter();
			String namespace = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);
			List<Object> actionParameter = gtnUIFrameWorkActionConfig.getActionParameterList();

			AbstractComponent abstractComponentUpdate = GtnUIFrameworkGlobalUI
					.getVaadinComponent(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
							+ gtnUIFrameWorkActionConfig.getFieldValues().get(2), componentId);
			Button componentDeleteButton = (Button) abstractComponentUpdate;
			componentDeleteButton.setEnabled(true);

			gtnUIFrameworkSetter.loadComboBoxComponentValue(
					namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
							+ gtnUIFrameWorkActionConfig.getFieldValues().get(3),
					(Integer) settertArray[0] != 0 ? (Integer) settertArray[0] : null);
			gtnUIFrameworkSetter.loadComboBoxComponentValue(
					namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
							+ gtnUIFrameWorkActionConfig.getFieldValues().get(4),
					(Integer) settertArray[1] != 0 ? (Integer) settertArray[1] : null);
			gtnUIFrameworkSetter.loadFieldValue(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
					+ gtnUIFrameWorkActionConfig.getFieldValues().get(5), (String) settertArray[2]);
			gtnUIFrameworkSetter.loadFieldValue(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
					+ gtnUIFrameWorkActionConfig.getFieldValues().get(6), (String) settertArray[3]);
			gtnUIFrameworkSetter.loadComboBoxComponentValue(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
					+ gtnUIFrameWorkActionConfig.getFieldValues().get(7), (Integer) settertArray[4]);
			gtnUIFrameworkSetter.loadComboBoxComponentValue(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
					+ gtnUIFrameWorkActionConfig.getFieldValues().get(8), (Integer) settertArray[5]);
			gtnUIFrameworkSetter.loadFieldValue(
					namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
							+ gtnUIFrameWorkActionConfig.getFieldValues().get(9),
					(String) settertArray[6] != null ? (String) settertArray[6] : "");

			List<Object> reloadInput = new ArrayList<>();
			Object productHeirarchy = gtnWsRecordBean.getPropertyValueByIndex(13);
			if (productHeirarchy != null) {
				reloadInput.add((Integer) productHeirarchy);
			}
			GtnUIFrameworkComponentData componentData = null;
			AbstractComponent abstractComponent = null;

			abstractComponent = GtnUIFrameworkGlobalUI
					.getVaadinComponent(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
							+ gtnUIFrameWorkActionConfig.getFieldValues().get(9), componentId);
			componentData = (GtnUIFrameworkComponentData) abstractComponent.getData();
			componentData.setCustomData(gtnWsRecordBean.getPropertyValueByIndex(13) != null
					? gtnWsRecordBean.getPropertyValueByIndex(13) : "");

			GtnUIFrameworkComboboxComponent gtnUIFrameworkComponent = new GtnUIFrameworkComboboxComponent();
			if (!reloadInput.isEmpty()) {
				gtnUIFrameworkComponent
						.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
								namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
										+ gtnUIFrameWorkActionConfig.getFieldValues().get(10),
								componentId, reloadInput);
			}
			gtnUIFrameworkSetter.loadComboBoxComponentValue(
					namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
							+ gtnUIFrameWorkActionConfig.getFieldValues().get(10),
					(Integer) settertArray[7] != 0 ? (Integer) settertArray[7] : null);
			if (!reloadInput.isEmpty()) {
				gtnUIFrameworkComponent
						.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
								namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
										+ gtnUIFrameWorkActionConfig.getFieldValues().get(11),
								componentId, reloadInput);
			}
			gtnUIFrameworkSetter.loadComboBoxComponentValue(
					namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
							+ gtnUIFrameWorkActionConfig.getFieldValues().get(11),
					(Integer) settertArray[8] != 0 ? (Integer) settertArray[8] : null);
			gtnUIFrameworkSetter.loadFieldValue(
					namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
							+ gtnUIFrameWorkActionConfig.getFieldValues().get(12),
					(String) settertArray[9] != null ? (String) settertArray[9] : "");
			gtnUIFrameworkSetter.loadComboBoxComponentValue(
					namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
							+ gtnUIFrameWorkActionConfig.getFieldValues().get(13),
					(Integer) settertArray[10] != 0 ? (Integer) settertArray[10] : null);

			GtnForecastBean gtnForecastBean;
			gtnForecastBean = loadDataFromServices(String.valueOf(systemId));

			if (!gtnForecastBean.getRightTableHierarchy().isEmpty()) {
				List<String> levelNoList = gtnForecastBean.getRightTableHierarchy()
						.get(String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(6)));
				loadDualListBoxRightTable(namespace, levelNoList, componentId);
			}
			setViewName(String.valueOf(actionParameter.get(GtnWsNumericConstants.ONE)), namespace,
					gtnUIFrameWorkActionConfig.getFieldValues().get(15),
					gtnUIFrameWorkActionConfig.getFieldValues().get(16), (String) settertArray[11]);
			gtnForecastBean.setViewName((String) settertArray[11]);
			gtnForecastBean.setProjectionMasterSid(settertArray[13].toString());
			gtnForecastBean.setViewCreatedBy(Integer.valueOf(settertArray[12].toString()));

			GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent("returnsForecastMainScreenDataSelection_dataSelectionMainLayout",
							componentId)
					.getComponentData();
			gtnUIFrameworkComponentData.setCustomData(gtnForecastBean);
			setViewName(String.valueOf(actionParameter.get(GtnWsNumericConstants.ONE)), namespace, "viewName",
					"privateViewName", GtnFrameworkCommonStringConstants.STRING_EMPTY);

		} catch (Exception ex) {
			gtnLogger.error(GtnFrameworkCommonConstants.ERROR_IN_LOAD_DATA_FROM_SERVICE_METHOD, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.ERROR_IN_LOAD_DATA_FROM_SERVICE_METHOD,
					ex);
		}

	}

	private void setViewName(String viewType, String namespace, String publicViewComponent, String privateViewComponent,
			String viewName) {

		if (GtnFrameworkForecastConstantCommon.PUBLIC_VIEW_NAME.equals(viewType)) {
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + publicViewComponent)
					.loadFieldValue(viewName);
		} else if (GtnFrameworkForecastConstantCommon.PRIVATE_VIEW_NAME.equals(viewType)) {
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + privateViewComponent)
					.loadFieldValue(viewName);
		}

	}

	private GtnForecastBean loadDataFromServices(String projectionMasterSid) {

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastRequest gtnWsForecastRequest = new GtnWsForecastRequest();
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		gtnForecastBean.setProjectionMasterSid(projectionMasterSid);
		gtnWsForecastRequest.setGtnForecastBean(gtnForecastBean);
		request.setGtnWsForecastRequest(gtnWsForecastRequest);

		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_GET_PROJECTION_VIEW_DETAILS_SERVICE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return response.getGtnWsForecastResponse().getGtnForecastBean();
	}

	private void loadDataFromService(int systemId, Object[] settertArray) throws GtnFrameworkGeneralException {

		try {

			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

			GtnForecastBean gtnForecastBean = new GtnForecastBean();
			gtnForecastBean.setViewId(systemId);

			GtnWsForecastRequest gtnWsForecastRequest = new GtnWsForecastRequest();
			gtnWsForecastRequest.setGtnForecastBean(gtnForecastBean);
			request.setGtnWsForecastRequest(gtnWsForecastRequest);

			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
					GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_GET_VIEW_DETAILS_SERVICE, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			GtnWsForecastResponse gtnWsForecastResponse = response.getGtnWsForecastResponse();
			GtnForecastBean gtnForecastBeanResult = gtnWsForecastResponse.getGtnForecastBean();

			setViewData(gtnForecastBeanResult, settertArray);

		} catch (Exception ex) {
			gtnLogger.error(GtnFrameworkCommonConstants.ERROR_IN_LOAD_DATA_FROM_SERVICE_METHOD, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.ERROR_IN_LOAD_DATA_FROM_SERVICE_METHOD,
					ex);
		}
	}

	private void setViewData(GtnForecastBean gtnForecastBean, Object[] settertArray)
			throws GtnFrameworkGeneralException {

		try {

			settertArray[0] = gtnForecastBean.getCompanyId();
			settertArray[1] = gtnForecastBean.getBusinessUnitId();
			settertArray[2] = gtnForecastBean.getProjectionName();
			settertArray[3] = gtnForecastBean.getProjectionDescription();
			settertArray[4] = gtnForecastBean.getFromPeriod();
			settertArray[5] = gtnForecastBean.getToPeriod();
			settertArray[6] = gtnForecastBean.getProductHirerachy();
			settertArray[7] = gtnForecastBean.getProductRelationship();
			settertArray[8] = gtnForecastBean.getProductForecastLevel();
			settertArray[9] = gtnForecastBean.getProductGroup();
			settertArray[10] = gtnForecastBean.getProductInnerLevel();
			settertArray[11] = gtnForecastBean.getViewName();
			settertArray[12] = gtnForecastBean.getViewCreatedBy();
			settertArray[13] = gtnForecastBean.getProjectionMasterSid();

		} catch (Exception ex) {
			gtnLogger.error(GtnFrameworkCommonConstants.ERROR_IN_LOAD_DATA_FROM_SERVICE_METHOD, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.ERROR_IN_LOAD_DATA_FROM_SERVICE_METHOD,
					ex);
		}
	}

	private void loadDualListBoxRightTable(String namespace, List<String> inputList, String componentId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dualListBoxComp", componentId);
		baseComponent.loadDualListBoxRightTableData(inputList, componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}