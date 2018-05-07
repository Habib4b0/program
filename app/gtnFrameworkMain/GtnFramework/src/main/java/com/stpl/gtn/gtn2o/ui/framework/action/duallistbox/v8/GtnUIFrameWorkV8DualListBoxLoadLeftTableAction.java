package com.stpl.gtn.gtn2o.ui.framework.action.duallistbox.v8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Grid;

/**
 *
 * @author STPL
 */
public class GtnUIFrameWorkV8DualListBoxLoadLeftTableAction implements GtnUIFrameWorkAction {

	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkV8DualListBoxLoadLeftTableAction.class);
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
        Map<String, String> levelValueMap = (Map<String, String>) dualListBoxBean.getGtnDualListBoxqueryParameters().get(1);
        GtnUIFrameworkWebserviceResponse response = callWebService(GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE + dualListBoxConfig.getLeftTableURL(),
                createLeftTableRequest(dualListBoxBean, dualListBoxConfig), dualListBoxConfig);
        List<GtnWsRecordBean> outputList = new ArrayList<>();
		for (GtnUIFrameworkDataRow record : response.getGtnSerachResponse().getResultSet().getDataTable()) {
				GtnWsRecordBean recordBean = new GtnWsRecordBean();
				recordBean.setProperties(record.getColList());
				recordBean.addProperties(levelValueMap.get(record.getColumnVAlue(4)));
				recordBean.setRecordHeader(dualListBoxConfig.getRecordHeader());
				outputList.add(recordBean);
			}
        leftTable.setItems(new ArrayList<>());
        if (outputList != null) {
            leftTable.setItems(outputList);
        }
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
			final GtnUIFrameworkWebserviceRequest request, GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig) {
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		if (dualListBoxConfig.getModuleType().equals("forecast")) {
			response = client.callGtnWebServiceUrl(webServiceUrl, "forecast", request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			logger.info("response ---------->" + response);
			return response;
		} else {
			response = client.callGtnWebServiceUrl(webServiceUrl, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			logger.info("Response in else block------->" + response);
			return response;
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
