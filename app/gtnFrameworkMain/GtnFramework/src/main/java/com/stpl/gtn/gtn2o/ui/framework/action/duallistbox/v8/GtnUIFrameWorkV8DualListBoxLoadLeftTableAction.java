package com.stpl.gtn.gtn2o.ui.framework.action.duallistbox.v8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkV8DualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;

/**
 *
 * @author STPL
 */
public class GtnUIFrameWorkV8DualListBoxLoadLeftTableAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(String.valueOf(parameterList.get(0)), componentId);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) gtnUIFrameworkComponentData
				.getCustomData();
		GtnUIFrameworkComponentConfig componentConfig = gtnUIFrameworkComponentData.getCurrentComponentConfig();
		GtnUIFrameworkV8DualListBoxConfig gtnUIFrameworkDualListBoxConfig = componentConfig
				.getGtnUIFrameworkV8DualListBoxConfig();
		loadLeftTable(gtnUIFrameworkDualListBoxConfig, dualListBoxBean);
	}

	public void loadLeftTable(GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig,
			GtnFrameworkV8DualListBoxBean dualListBoxBean) {
		Grid<GtnWsRecordBean> leftTable = dualListBoxBean.getLeftTable();
		Optional.ofNullable(dualListBoxBean.getGtnDualListBoxqueryParameters()).ifPresent(e -> {
			Map<String, String> levelValueMap = (Map<String, String>) e.get(1);
			GtnUIFrameworkWebserviceResponse response = callWebService(dualListBoxConfig.getLeftTableURL(),
					createLeftTableRequest(dualListBoxBean, dualListBoxConfig), dualListBoxConfig);
			List<GtnWsRecordBean> outputList = new ArrayList<>(10);
			for (GtnUIFrameworkDataRow record : response.getGtnSerachResponse().getResultSet().getDataTable()) {
				GtnWsRecordBean recordBean = new GtnWsRecordBean();
				recordBean.setProperties(record.getColList());
				recordBean.addProperties(levelValueMap.get(record.getColumnVAlue(4)));
				recordBean.setRecordHeader(dualListBoxConfig.getRecordHeader());
				outputList.add(recordBean);
			}
			ListDataProvider<GtnWsRecordBean> dataProvider = DataProvider.ofCollection(outputList);
			leftTable.setDataProvider(dataProvider);
		});
	}

	private GtnUIFrameworkWebserviceRequest createLeftTableRequest(final GtnFrameworkV8DualListBoxBean dualListBoxBean,
			GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsSearchRequest(createSearchRequest(dualListBoxBean, dualListBoxConfig));
		request.setGtnWsGeneralRequest(createGeneralRequest());
		return request;
	}

	private GtnWsSearchRequest createSearchRequest(final GtnFrameworkV8DualListBoxBean dualListBoxBean,
			GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig) {
		GtnWsSearchRequest searchRequest = new GtnWsSearchRequest();
		searchRequest.setSearchColumnNameList(new ArrayList<Object>(dualListBoxConfig.getRecordHeader()));
		dualListBoxBean.addGtnDualListBoxqueryParameters(GtnUIFrameworkGlobalUI.getCurrentUser());
		searchRequest.setQueryInputList(dualListBoxBean.getGtnDualListBoxqueryParameters());
		return searchRequest;
	}

	private GtnWsGeneralRequest createGeneralRequest() {
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		return generalRequest;
	}

	private GtnUIFrameworkWebserviceResponse callWebService(final String webServiceUrl,
			final GtnUIFrameworkWebserviceRequest wsRequest, GtnUIFrameworkV8DualListBoxConfig v8DualListBoxConfig) {
		GtnUIFrameworkWebServiceClient wsClient = new GtnUIFrameworkWebServiceClient();
		if (v8DualListBoxConfig.getModuleType().equals("forecast")) {
			return wsClient.callGtnWebServiceUrl(webServiceUrl, "forecast", wsRequest,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		} else {
			return wsClient.callGtnWebServiceUrl(webServiceUrl, v8DualListBoxConfig.getModuleType(), wsRequest,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

}
