/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config.action;

import com.stpl.gtn.gtn2o.ui.customview.constants.GtnFrameworkCVConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnFrameworkCRValueChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        // No Need to Implement. Its an unused method.
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
        String resultTableId = (String) parameters.get(2);
        String deductionTableId = (String) parameters.get(6);
        GtnWsRecordBean dto = new GtnWsRecordBean();
        GtnUIFrameworkBaseComponent table = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId);
        GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
        GtnUIFrameworkBaseComponent treeTable = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5).toString());
        GtnUIFrameworkBaseComponent dedTable = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(deductionTableId);
        treeTable.clearTree();
        table.clearTree();
        
        int relationSid = component.getIntegerFromField();
        dto.setRecordHeader(table.getTableRecordHeader());
        GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsSearchRequest searchRequest = new GtnWsSearchRequest();
        List<Object> inputList = new ArrayList<>();
        inputList.add(resultTableId.contains(GtnFrameworkCVConstants.CUSTOMER_LEVEL) ? "C" : "P");
        inputList.add(relationSid);
        searchRequest.setQueryInputList(inputList);
        serviceRequest.setGtnWsSearchRequest(searchRequest);
        GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE + GtnWsCustomViewConstants.GET_CUSTOM_VIEW_TABLE_DATA, serviceRequest,
                GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId);
        for (GtnUIFrameworkDataRow record : response.getGtnSerachResponse().getResultSet().getDataTable()) {
            dto = new GtnWsRecordBean();
            dto.setRecordHeader(table.getTableRecordHeader());
            dto.setProperties(record.getColList());
            tableBaseComponent.addItemToDataTable(dto);
        }
        
        if (resultTableId.contains(GtnFrameworkCVConstants.PRODUCT_LEVEL)) {
            dedTable.clearTree();
            List<Object> deduInputList = new ArrayList<>();
            deduInputList.add("D");
            deduInputList.add(relationSid);
            searchRequest.setQueryInputList(deduInputList);
            serviceRequest.setGtnWsSearchRequest(searchRequest);
            GtnUIFrameworkWebserviceResponse responseForDed = wsclient.callGtnWebServiceUrl(GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE + GtnWsCustomViewConstants.GET_DEDUCTION_HIERARCHY_TABLE_DATA, serviceRequest,
                    GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
            GtnUIFrameworkBaseComponent dedTableComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(deductionTableId);
            for (GtnUIFrameworkDataRow record : responseForDed.getGtnSerachResponse().getResultSet().getDataTable()) {
                dto = new GtnWsRecordBean();
                dto.setRecordHeader(table.getTableRecordHeader());
                dto.setProperties(record.getColList());
                dedTableComponent.addItemToDataTable(dto);
            }
        }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
