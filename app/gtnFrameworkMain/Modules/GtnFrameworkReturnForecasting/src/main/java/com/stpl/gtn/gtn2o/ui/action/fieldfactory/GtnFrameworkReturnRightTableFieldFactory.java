
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action.fieldfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
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

/**
 *
 * @author Harlin.Mani
 */
public class GtnFrameworkReturnRightTableFieldFactory
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkReturnRightTableFieldFactory.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId);
			GtnUIFrameworkActionParameter actionParam = componentData.getActionParameter();

			String oldValue = String.valueOf(actionParam.getOldValue())
					.replaceAll(GtnFrameworkForecastConstantCommon.MODULO, "")
					.replaceAll(GtnFrameworkForecastConstantCommon.DOLLER, "");

			String value = String.valueOf(actionParam.getCurrentValue())
					.replaceAll(GtnFrameworkForecastConstantCommon.MODULO, "")
					.replaceAll(GtnFrameworkForecastConstantCommon.DOLLER, "");
			if (!oldValue.equals(value)) {
				actionParam.setOldValue(value);
				doManualEntryCalculation(actionParam.getItemId(), actionParam.getPropertyId(), value, oldValue,
						gtnUIFrameWorkActionConfig, componentData.getViewId(), componentId);
			}

		} catch (Exception ex) {
			gtnLogger.error(componentId, ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void doManualEntryCalculation(GtnWsRecordBean listDto, Object propertyId, Object value, String oldValue,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String viewId, String componentId) {
		gtnLogger.debug("Right Table field Factory -  In Manual Entry Caluculation");
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastRequest requestForCalulate = new GtnWsForecastRequest();
		requestForCalulate.setGtnForecastBean(
				getManualEntryInputBean(listDto, propertyId, value, oldValue, gtnUIFrameWorkActionConfig, componentId));
		gtnUIFrameworkWebserviceRequest.setGtnWsForecastRequest(requestForCalulate);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		client.callGtnWebServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_CALCULATE_SERVICE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, gtnUIFrameworkWebserviceRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		configureRecordsForRefreshUpdate(listDto, viewId, gtnUIFrameWorkActionConfig);
	}

	@SuppressWarnings("unchecked")
	private void configureRecordsForRefreshUpdate(GtnWsRecordBean listDto, String viewId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {
		gtnLogger.debug("Right Table field Factory - Refresh Update");
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(1), viewId).getComponentData();
		FreezePagedTreeTable resultsTable = (FreezePagedTreeTable) componentData.getCustomDataList().get(0);
		GtnUIFrameworkPagedTreeTableLogic tableLogic = (GtnUIFrameworkPagedTreeTableLogic) resultsTable
				.getLeftFreezeAsTable().getContainerLogic();
		Set<String> tableHirarechyNos = (Set<String>) componentData.getCustomDataList().get(2);
		tableHirarechyNos.add(tableLogic.getTreeLevelonCurrentPage(listDto));
	}

	private GtnForecastBean getManualEntryInputBean(GtnWsRecordBean listDto, Object propertyId, Object value,
			String oldValue, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String componentId) {

		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId);

		GtnForecastBean gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();

		List<String> checkedHierarchyNos = new ArrayList<>();

		checkedHierarchyNos.add(String.valueOf(listDto.getAdditionalProperties().get(0)));

		gtnForecastBean.setCheckedHierarchyNumbers(checkedHierarchyNos);

		String[] propertyInformation = String.valueOf(propertyId).split(GtnFrameworkForecastConstantCommon.HYPHEN);

		gtnForecastBean.setForecastMethodology(getMethodology(propertyInformation));

		String period = getPeriod(propertyInformation);

		gtnForecastBean.setCalculationStartPeriod(period);
		gtnForecastBean.setCalculationEndPeriod(period);
		gtnForecastBean.setFrequency(getFrequency(propertyInformation));
		gtnForecastBean.setManualEntryValue(String.valueOf(value));
		gtnForecastBean.setManualEntryOldValue(oldValue);

		return gtnForecastBean;
	}

	private String getFrequency(String[] propertyInformation) {

		if (propertyInformation.length == 2) {
			return GtnFrameworkForecastConstantCommon.SELECTFREQ_ANNUAL;
		}

		if (propertyInformation[0].contains("Q")) {
			return GtnFrameworkForecastConstantCommon.SELECTFREQ_QUAD;
		}

		if (propertyInformation[0].contains("S")) {
			return GtnFrameworkForecastConstantCommon.SELECTFREQ_SEMI;
		}

		return GtnFrameworkForecastConstantCommon.SELECTFREQ_MONTH;
	}

	private String getMethodology(String[] propertyInformation) {
		return "Manual Entry-" + propertyInformation[propertyInformation.length - 1];
	}

	private String getPeriod(String[] propertyInformation) {
		if (propertyInformation.length == 2) {
			return propertyInformation[0];
		} else {
			return propertyInformation[0] + " " + propertyInformation[1];
		}
	}

}