package com.stpl.gtn.gtn2o.ui.action;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.stpl.gtn.gtn2o.ui.config.GtnUIFrameworkWebServiceReportRequestBuilder;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDeductionType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportEndPointUrlConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkUICustomViewHierarchyLoadAction
        implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

    private static final GtnWSLogger GTNLOGGER = GtnWSLogger
            .getGTNLogger(GtnFrameworkUICustomViewHierarchyLoadAction.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        return;

    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        List<Object> parameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
        GtnWsHierarchyType hierarchyType = (GtnWsHierarchyType) parameterList.get(1);
        GtnUIFrameworkDataTable dataTable = null;
        if (hierarchyType == GtnWsHierarchyType.CUSTOMER || hierarchyType == GtnWsHierarchyType.PRODUCT) {
            GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebServiceReportRequestBuilder()
                    .withCustomViewBean().withDataSelectionBean().build();
            request.getGtnWsReportRequest().getReportBean().getCustomViewBean().setHierarchyType(hierarchyType);
            String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getParentViewId();
            GTNLOGGER.info("component Id = = = " + componentId);
            GTNLOGGER.info("sourceComponentId = = =" + sourceComponentId);
            GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
            int relationSid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_customerSelectionRelationship")
                    .getIntegerFromField();
            dataSelectionBean.setCustomerRelationshipBuilderSid(relationSid);
            dataSelectionBean.setCustomerHierarchySid(relationSid);
            relationSid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_relationship").getIntegerFromField();
            dataSelectionBean.setProductRelationshipBuilderSid(relationSid);
            dataSelectionBean.setProductHierarchySid(relationSid);
            request.getGtnWsReportRequest().getReportBean().setDataSelectionBean(dataSelectionBean);
            GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                    GtnWsReportEndPointUrlConstants.LOAD_HIERARCHY,
                    GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME, request,
                    GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
            dataTable = response.getGtnReportResponse().getReportBean().getCustomViewBean().getGridData();
        } else {
            dataTable = loadDeductionHierarchy();
        }
        GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig(
                GtnUIFrameworkActionType.GRID_STATIC_LOAD_ACTION);
        actionConfig.addActionParameter(componentId);
        actionConfig.addActionParameter(dataTable);
        GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);
    }

    private GtnUIFrameworkDataTable loadDeductionHierarchy() {
        GtnWsReportDeductionType[] values = GtnWsReportDeductionType.values();
        List deductionList = Arrays.stream(values).map(value -> {
            Object[] dataArray = new Object[5];
            dataArray[0] = value.toString();
            dataArray[1] = value.getLevelNo();
            dataArray[2] = value.getLevelNo();
            dataArray[3] = GtnWsHierarchyType.DEDUCTION.toString();
            dataArray[4]= -1;
            return dataArray;
        }).collect(Collectors.toList());
        GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
        dataTable.addData(deductionList);
        return dataTable;
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
