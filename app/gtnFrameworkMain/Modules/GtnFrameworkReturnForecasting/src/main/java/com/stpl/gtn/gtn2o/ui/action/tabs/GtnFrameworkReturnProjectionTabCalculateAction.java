package com.stpl.gtn.gtn2o.ui.action.tabs;

import java.util.ArrayList;
import java.util.List;

import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkReturnProjectionTabCalculateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkReturnProjectionTabCalculateAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("inside GtnFrameworkReturnProjectionTabCalculateAction");

		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		List<Object> list = gtnUIFrameWorkActionConfig.getActionParameterList();

		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId);

		GtnForecastBean gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();
		try {

			GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(String.valueOf(list.get(4)), componentId);

			FreezePagedTreeTable resultsTable = (FreezePagedTreeTable) componentData.getCustomDataList().get(0);

			final ExtFilterTreeTable rightTable = resultsTable.getRightFreezeAsTable();
			List<String> checkPeriods = new ArrayList<>();

			GtnUIFrameworkPagedTreeTableLogic tableLogic = (GtnUIFrameworkPagedTreeTableLogic) resultsTable
					.getLeftFreezeAsTable().getContainerLogic();

			@SuppressWarnings("unchecked")
			List<GtnWsRecordBean> itemIdsFromTableLogic = ((ExtTreeContainer) tableLogic.getContainerDataSource())
					.getBeans();

			List<String> checkedHierarchyNos = new ArrayList<>();

			for (GtnWsRecordBean GtnWsRecordBean : itemIdsFromTableLogic) {
				if (GtnFrameworkCommonStringConstants.STRING_TRUE
						.equals(GtnWsRecordBean.getPropertyValue(GtnFrameworkCommonStringConstants.STRING_CHECK))) {
					List<Object> parentDetails = GtnWsRecordBean.getAdditionalProperties();
					checkedHierarchyNos.add(String.valueOf(parentDetails.get(0)));
				} else {
					List<Object> parentDetails = GtnWsRecordBean.getAdditionalProperties();
					checkedHierarchyNos.remove(String.valueOf(parentDetails.get(0)));
				}
			}
			gtnForecastBean.setCheckedHierarchyNumbers(checkedHierarchyNos);

			Object[] doubleHeaderVisibleColumns = rightTable.getDoubleHeaderVisibleColumns();
			for (Object object : doubleHeaderVisibleColumns) {
				if (rightTable.getDoubleHeaderColumnCheckBox(object)) {
					checkPeriods.add(rightTable.getDoubleHeaderColumnHeader(object));
				}
			}

			GtnWsForecastRequest requestForCalulate = new GtnWsForecastRequest();
			gtnForecastBean.setMethodologyCalculationFlag(true);
			gtnForecastBean.setForecastMethodology(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(list.get(1)), componentId).getCaptionFromComboBox());

			gtnForecastBean.setCalculationStartPeriod(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(list.get(2)), componentId).getCaptionFromComboBox());
			gtnForecastBean.setCalculationEndPeriod(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(list.get(3)), componentId).getCaptionFromComboBox());

			gtnForecastBean.setFrequency(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(list.get(5)), componentId).getCaptionFromComboBox());

			gtnForecastBean.setBaselinePeriodList(checkPeriods);
			requestForCalulate.setGtnForecastBean(gtnForecastBean);
			gtnUIFrameworkWebserviceRequest.setGtnWsForecastRequest(requestForCalulate);
			GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();

			GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(
					GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_CALCULATE_SERVICE,
					GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, gtnUIFrameworkWebserviceRequest,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			if (response.getGtnWsGeneralResponse().isSucess()) {
				GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig(
						GtnUIFrameworkActionType.NOTIFICATION_ACTION);
				alertActionConfig.addActionParameter("Calculation Complete");
				alertActionConfig.addActionParameter("");
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);

			}
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage());
			throw new GtnFrameworkGeneralException(ex);
		} finally {
			gtnForecastBean.setMethodologyCalculationFlag(false);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
