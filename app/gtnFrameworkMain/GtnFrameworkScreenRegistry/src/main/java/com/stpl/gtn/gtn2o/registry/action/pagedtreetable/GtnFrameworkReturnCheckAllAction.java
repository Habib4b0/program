package com.stpl.gtn.gtn2o.registry.action.pagedtreetable;

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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnFrameworkReturnCheckAllAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkActionParameter actionParam = gtnUIFrameWorkActionConfig.getActionParameter();

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData("projectionDetailsTabsheetMainLayout", componentId);

		GtnForecastBean gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();

		GtnWsForecastRequest pMasterRequest = new GtnWsForecastRequest();

		gtnForecastBean.setCheckAllFlag(true);
		gtnForecastBean.setCheckValue(String.valueOf(actionParam.getCurrentValue()));

		pMasterRequest.setGtnForecastBean(gtnForecastBean);
		gtnWsRequest.setGtnWsForecastRequest(pMasterRequest);

		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();

		client.callGtnWebServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_UPDATE_CHECKBOX_SERVICE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, gtnWsRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData("resultTable",
				componentId);
		gtnForecastBean.setCheckAllFlag(false);
		FreezePagedTreeTable resultsTable = (FreezePagedTreeTable) componentData.getCustomDataList().get(0);

		GtnUIFrameworkPagedTreeTableLogic tableLogic = (GtnUIFrameworkPagedTreeTableLogic) resultsTable
				.getLeftFreezeAsTable().getContainerLogic();

		Set<String> tableHirarechyNos = (Set<String>) componentData.getCustomDataList().get(1);
		for (Object itemID : resultsTable.getLeftFreezeAsTable().getItemIds()) {
			GtnWsRecordBean dto = (GtnWsRecordBean) itemID;
			dto.addProperties("check", String.valueOf(actionParam.getCurrentValue()));
			resultsTable.getLeftFreezeAsTable().getContainerProperty(itemID, "check")
					.setValue(String.valueOf(actionParam.getCurrentValue()));
			if ("true".equals(String.valueOf(actionParam.getCurrentValue()))) {
				tableHirarechyNos.add(tableLogic.getTreeLevelonCurrentPage(dto));
			} else {
				tableHirarechyNos.remove(tableLogic.getTreeLevelonCurrentPage(dto));
			}
		}

		resultsTable.getLeftFreezeAsTable().setRefresh(true);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}