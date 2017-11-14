/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action.fieldfactory;

import java.util.List;
import java.util.Set;

import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
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
 * @author Harlin.Mani
 */
public class GtnFrameworkReturnLeftFieldFactoryAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkReturnLeftFieldFactoryAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info(" info GtnFrameworkReturnLeftFieldFactoryAction ");

		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinFieldFactoryComponentData(componentId);

		GtnUIFrameworkActionParameter actionParam = componentData.getActionParameter();
		GtnWsRecordBean checkDTO = actionParam.getItemId();
		checkDTO.addProperties(actionParam.getPropertyId(), String.valueOf(actionParam.getCurrentValue()));
		List<Object> additionalProp = checkDTO.getAdditionalProperties();
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId);

		GtnForecastBean gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();

		GtnWsForecastRequest pMasterRequest = new GtnWsForecastRequest();

		gtnForecastBean.setSelectedHierarchyNo(String.valueOf(additionalProp.get(0)));
		gtnForecastBean.setCheckValue(String.valueOf(actionParam.getCurrentValue()));

		pMasterRequest.setGtnForecastBean(gtnForecastBean);
		gtnWsRequest.setGtnWsForecastRequest(pMasterRequest);

		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();

		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = client.callGtnWebServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_UPDATE_CHECKBOX_SERVICE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, gtnWsRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		configureRecordsForRefreshUpdate(checkDTO, (boolean) actionParam.getCurrentValue(), gtnUIFrameWorkActionConfig,
				componentId);

		GtnUIFrameworkBaseComponent treeTableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(1), componentId);
		treeTableBaseComponent.setLeftTreeTableHeaderCheckBox(
				gtnUIFrameworkWebserviceResponse.getGtnWSPagedTreeTableResponse().isCheckAll(),
				actionParam.getPropertyId());
		treeTableBaseComponent.reloadPagedTreeTable();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	@SuppressWarnings("unchecked")
	private void configureRecordsForRefreshUpdate(GtnWsRecordBean listDto, boolean isChecked,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String componentId)
			throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(gtnUIFrameWorkActionConfig.getFieldValues().get(1), componentId);

		FreezePagedTreeTable resultsTable = (FreezePagedTreeTable) componentData.getCustomDataList().get(0);
		GtnUIFrameworkPagedTreeTableLogic tableLogic = (GtnUIFrameworkPagedTreeTableLogic) resultsTable
				.getLeftFreezeAsTable().getContainerLogic();

		Set<String> tableHirarechyNos = (Set<String>) componentData.getCustomDataList().get(1);
		if (isChecked) {
			tableHirarechyNos.add(tableLogic.getTreeLevelonCurrentPage(listDto));
			for (String currentChildNode : tableLogic
					.getAllChildParentLevels(tableLogic.getTreeLevelonCurrentPage(listDto))) {
				tableHirarechyNos.add(currentChildNode);
			}
		} else {
			tableHirarechyNos.remove(tableLogic.getTreeLevelonCurrentPage(listDto));
		}
	}
}