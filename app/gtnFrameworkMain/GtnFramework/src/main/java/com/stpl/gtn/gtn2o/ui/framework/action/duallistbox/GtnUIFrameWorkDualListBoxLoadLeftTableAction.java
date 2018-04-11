package com.stpl.gtn.gtn2o.ui.framework.action.duallistbox;

import java.util.ArrayList;
import java.util.List;

import org.asi.ui.extfilteringtable.ExtFilterTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.bean.GtnFrameworkDualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author STPL
 */
public class GtnUIFrameWorkDualListBoxLoadLeftTableAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(String.valueOf(parameterList.get(0)), componentId);
		GtnFrameworkDualListBoxBean dualListBoxBean = (GtnFrameworkDualListBoxBean) gtnUIFrameworkComponentData
				.getCustomData();
		GtnUIFrameworkComponentConfig componentConfig = gtnUIFrameworkComponentData.getCurrentComponentConfig();
		GtnUIFrameworkDualListBoxConfig gtnUIFrameworkDualListBoxConfig = componentConfig
				.getGtnUIFrameworkDualListBoxConfig();
		loadLeftTable(gtnUIFrameworkDualListBoxConfig, dualListBoxBean);
	}

	public void loadLeftTable(GtnUIFrameworkDualListBoxConfig dualListBoxConfig,
			GtnFrameworkDualListBoxBean dualListBoxBean) {
		ExtFilterTable leftTable = dualListBoxBean.getLeftTable();
		GtnUIFrameworkWebserviceResponse response = callWebService(dualListBoxConfig.getLeftTableURL(),
				createLeftTableRequest(dualListBoxBean, dualListBoxConfig), dualListBoxConfig);
		List<GtnWsRecordBean> output = response.getGtnUIFrameworkWebserviceDualListBoxResponse()
				.getDualListBoxTableDataList();
		leftTable.getContainerDataSource().removeAllItems();
		if (output != null) {
			for (GtnWsRecordBean bean : output) {
				if (!response.getGtnUIFrameworkWebserviceDualListBoxResponse().getFileName()
						.equals(dualListBoxConfig.getFileName())) {
					dualListBoxConfig
							.setFileName(response.getGtnUIFrameworkWebserviceDualListBoxResponse().getFileName());
				}
				leftTable.getContainerDataSource().addItem(bean);
			}
		}
	}

	private GtnUIFrameworkWebserviceRequest createLeftTableRequest(final GtnFrameworkDualListBoxBean dualListBoxBean,
			GtnUIFrameworkDualListBoxConfig dualListBoxConfig) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsSearchRequest(createSearchRequest(dualListBoxBean, dualListBoxConfig));
		request.setGtnWsGeneralRequest(createGeneralRequest());
		return request;
	}

	private GtnWsSearchRequest createSearchRequest(final GtnFrameworkDualListBoxBean dualListBoxBean,
			GtnUIFrameworkDualListBoxConfig dualListBoxConfig) {
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
			final GtnUIFrameworkWebserviceRequest request, GtnUIFrameworkDualListBoxConfig dualListBoxConfig) {
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();

		if (dualListBoxConfig.getModuleType().equals("forecast")) {
			return client.callGtnWebServiceUrl(webServiceUrl, "forecast", request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		} else {
			return client.callGtnWebServiceUrl(webServiceUrl, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		}

	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}