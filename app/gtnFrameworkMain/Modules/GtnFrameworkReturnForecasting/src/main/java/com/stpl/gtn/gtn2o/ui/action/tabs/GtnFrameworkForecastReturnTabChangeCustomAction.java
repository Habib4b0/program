package com.stpl.gtn.gtn2o.ui.action.tabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsClassConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkConfirmationAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastAlertMsgConstants;
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
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkForecastReturnTabChangeCustomAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkForecastReturnTabChangeCustomAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(final String componentId, final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			final List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
			final TabSheet tabSheet = (TabSheet) getAbstractComponent(String.valueOf(actionParameterList.get(7)),
					componentId);
			final Button previousButton = (Button) getAbstractComponent(String.valueOf(actionParameterList.get(8)),
					componentId);
			final Button nextButton = (Button) getAbstractComponent(String.valueOf(actionParameterList.get(9)),
					componentId);

			Tab currentTab = tabSheet.getTab(tabSheet.getSelectedTab());
			int currentTabPosition = tabSheet.getTabPosition(currentTab);

			GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(actionParameterList.get(1).toString(), componentId);
			final GtnForecastBean gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();
			final DataSelectionBean dataSelectionBean = getDataSelectionBean(actionParameterList, componentId);
			if (dataSelectionBean != null && currentTabPosition != 0
					&& !compareDataSelectionTabValues(dataSelectionBean, gtnUIFrameWorkActionConfig.getFieldValues(),
							gtnUIFrameWorkActionConfig.getActionParameterList().get(11).toString()
									+ GtnFrameworkForecastConstantCommon.UNDERSCORE,
							componentId)) {
				removeTab(tabSheet);
				addTab(tabSheet, actionParameterList, componentId);

				List<GtnUIFrameWorkActionConfig> finalYesActionConfigList = yesButtonClickEvent(gtnForecastBean,
						gtnUIFrameWorkActionConfig);
				List<GtnUIFrameWorkActionConfig> finalNoActionConfigList = noButtonClickEvent(
						gtnUIFrameWorkActionConfig);

				GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
				GtnUIFrameWorkConfirmationAction confirmationAction = new GtnUIFrameWorkConfirmationAction();
				confirmationActionConfig.setActionParameterList(Arrays.asList(
						GtnFrameworkForecastAlertMsgConstants.RETURNS_DATA_SELECTION_NEXT_BUTTON_ALERT_CONFIRMATION_HEADER,
						GtnFrameworkForecastAlertMsgConstants.RETURNS_DATA_SELECTION_NEXT_BUTTON_ALERT_CONFIRMATION_MSG,
						finalYesActionConfigList, finalNoActionConfigList));
				confirmationAction.doAction(componentId, confirmationActionConfig);

			} else {
				if (currentTabPosition == 0) {
					nextButton.setVisible(true);
					previousButton.setVisible(false);
				} else if (currentTabPosition == 1) {
					nextButton.setVisible(false);
					previousButton.setVisible(true);
				}
			}
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage(), ex);
		}
	}

	private List<GtnUIFrameWorkActionConfig> yesButtonClickEvent(GtnForecastBean gtnForecastBean,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {
		GtnUIFrameWorkActionConfig yesActionConfig = new GtnUIFrameWorkActionConfig();
		yesActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		yesActionConfig.setActionParameterList(Arrays.asList(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_BUTTONONECLICK_TABCHANGE_CUSTOM_ACTION,
				gtnUIFrameWorkActionConfig, gtnForecastBean));

		List<GtnUIFrameWorkActionConfig> finalYesActionConfigList = new ArrayList<>();
		finalYesActionConfigList.add(yesActionConfig);
		return finalYesActionConfigList;
	}

	private List<GtnUIFrameWorkActionConfig> noButtonClickEvent(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {
		GtnUIFrameWorkActionConfig noActionConfig = new GtnUIFrameWorkActionConfig();
		noActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		noActionConfig.setActionParameterList(Arrays.asList(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_BUTTONTWOCLICK_TABCHANGE_CUSTOM_ACTION,
				gtnUIFrameWorkActionConfig));

		List<GtnUIFrameWorkActionConfig> finalNoActionConfigList = new ArrayList<>();
		finalNoActionConfigList.add(noActionConfig);

		return finalNoActionConfigList;
	}

	private void removeTab(final TabSheet tabSheet) {
		if (tabSheet.getTab(1) != null) {
			tabSheet.removeTab(tabSheet.getTab(1));
		}
	}

	private void addTab(final TabSheet tabSheet, List<Object> actionParameterList, String sourceComponentId) {
		AbstractComponent currentComponentTab = GtnUIFrameworkGlobalUI
				.getVaadinComponent(String.valueOf(String.valueOf(actionParameterList.get(10))), sourceComponentId);
		tabSheet.addTab(currentComponentTab, "Returns Projection");
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private AbstractComponent getAbstractComponent(String componentId, String sourceComponentId) {
		return GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(componentId), sourceComponentId);
	}

	private DataSelectionBean getDataSelectionBean(List<Object> actionParameterList, String componentId) {
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParameterList.get(1).toString(), componentId);
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

	private boolean compareDataSelectionTabValues(DataSelectionBean dataSelectionBean,
			List<String> fieldValuesParameterList, String namespace, String sourceComponentId)
			throws GtnFrameworkValidationFailedException {

		return dataSelectionBean.getProjectionName()
				.equals(getStringValue(namespace + fieldValuesParameterList.get(9), sourceComponentId))
				&& dataSelectionBean.getGlCompanyId() == getIntegerValue(namespace + fieldValuesParameterList.get(5),
						sourceComponentId)
				&& dataSelectionBean.getBusinessUnitId() == getIntegerValue(namespace + fieldValuesParameterList.get(7),
						sourceComponentId)
				&& dataSelectionBean.getFromDate() == getIntegerValue(namespace + fieldValuesParameterList.get(12),
						sourceComponentId)
				&& dataSelectionBean.getToDate() == getIntegerValue(namespace + fieldValuesParameterList.get(14),
						sourceComponentId)
				&& dataSelectionBean.getProductHierarchyId()
						.equals(getSelectedId(namespace + fieldValuesParameterList.get(16), sourceComponentId))
				&& dataSelectionBean.getRelationshipBuilderId()
						.equals(getStringValue(namespace + fieldValuesParameterList.get(17), sourceComponentId))
				&& dataSelectionBean.getForecastLevelId()
						.equals(getStringValue(namespace + fieldValuesParameterList.get(18), sourceComponentId))
				&& compareProductSelectionValues(dataSelectionBean.getSelectedProducts(),
						getValueFromDualListBoxAsList(namespace + fieldValuesParameterList.get(24), sourceComponentId));

	}

	private boolean compareProductSelectionValues(List<String> selectedProducts, List<String> storedProducts) {
		if (selectedProducts.size() == storedProducts.size()) {
			for (String storedProduct : storedProducts) {
				if (!selectedProducts.contains(storedProduct)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	private String getStringValue(String componentId, String sourceComponentId) {
		return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId).getStringFromField();
	}

	private int getIntegerValue(String componentId, String sourceComponentId)
			throws GtnFrameworkValidationFailedException {
		return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId).getIntegerFromField();
	}

	private Object getSelectedId(String componentId, String sourceComponentId) {
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
}
