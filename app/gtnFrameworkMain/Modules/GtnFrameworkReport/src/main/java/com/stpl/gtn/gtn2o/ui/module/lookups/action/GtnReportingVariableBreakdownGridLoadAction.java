package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import static com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType.COMBOBOX_VAADIN8;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.HeaderRow;
import java.util.Arrays;

public class GtnReportingVariableBreakdownGridLoadAction
        implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingVariableBreakdownGridLoadAction.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        return;
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        try {
            logger.info("------------GtnReportingVariablghjghjgeBreakdownGridLoadAction----------------");
            int i = 0;
            List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
            String variableBreakdownTableId = actionParameterList.get(1).toString();

            AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(variableBreakdownTableId,
                    componentId);
            GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
            PagedGrid pagedGrid = (PagedGrid) gridComponent.getCustomData();
            Grid<GtnWsRecordBean> grid = (Grid<GtnWsRecordBean>) pagedGrid.getGrid();

            List recordHeader = Arrays.asList(pagedGrid.getTableConfig().getTableColumnMappingId());
            List<Object> list = Arrays.asList("test", "test", "test", "test", "test", 45);
            GtnWsRecordBean dto = new GtnWsRecordBean();
            dto.setRecordHeader(recordHeader);
            dto.setProperties(list);
            grid.setItems(dto);

//            GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI
//					.getVaadinBaseComponentFromParent(
//							gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString(), componentId)
//					.getComponentData();
           // idComponentData.getCustomData().
//            GtnWsReportDataSelectionBean reportDataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
//                    .getVaadinBaseComponent("reportLandingScreen_reportingDashboardComparisonConfig")
//                    .getComponentData().getSharedPopupData();
//            List<GtnReportComparisonProjectionBean> gtnReportComparisonProjectionBeanList = reportDataSelectionBean.getComparisonProjectionBeanList();
//            List<String> projectionMasterSidList = new ArrayList<>();

//            for (GtnReportComparisonProjectionBean gtnReportComparisonProjectionBean : gtnReportComparisonProjectionBeanList) {
//                projectionMasterSidList.add(gtnReportComparisonProjectionBean.getProjectionName());
//            }

            Component vaadinComponent = null;
           // for (String projectionMasterName : projectionMasterSidList) {
                HeaderRow filterRow = grid.appendHeaderRow();

                Object[] filterColumnIdList = pagedGrid.getTableConfig().getTableColumnMappingId();
                for (Object column : filterColumnIdList) {
                    vaadinComponent = getCustomFilterComponent(String.valueOf(column), componentId, i);
                    filterRow.getCell(String.valueOf(column)).setComponent(vaadinComponent);
                }
              //  i++;
           // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Component getCustomFilterComponent(String property, String componentId, int i) {
        try {
            GtnUIFrameworkBaseComponent base = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
                    "reportOptionsTab_variableBreakdownGridComboConfig", componentId);

            GtnUIFrameworkComponent component = COMBOBOX_VAADIN8.getGtnComponent();
            Component vaadinComponent = null;
            vaadinComponent = component.buildVaadinComponent(base.getComponentConfig());
            ComboBox vaadinCombobox = (ComboBox) vaadinComponent;
            vaadinCombobox.setId(property + String.valueOf(i));
            return vaadinCombobox;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<GtnWsRecordBean> getRecordBeanList(List<GtnUIFrameworkDataRow> dataRow) {
        if (dataRow != null && !dataRow.isEmpty()) {
            List<GtnWsRecordBean> dataRowList = new ArrayList<>();
            for (GtnUIFrameworkDataRow gtnWsRecordBean : dataRow) {
                GtnWsRecordBean recordBean = new GtnWsRecordBean();
                recordBean.setProperties(gtnWsRecordBean.getColList());
                recordBean.setRecordHeader(GtnFrameworkReportStringConstants.getVariableBreakdownHeader());
                dataRowList.add(recordBean);
            }
            return dataRowList;
        }
        return Collections.emptyList();
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
