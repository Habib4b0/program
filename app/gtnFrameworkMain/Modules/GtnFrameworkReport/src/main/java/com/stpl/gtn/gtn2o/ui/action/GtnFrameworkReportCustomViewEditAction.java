/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GtnFrameworkReportCustomViewEditAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkReportCustomViewEditAction.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        //Do nothing
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        String selectedItem = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_CUSTOM_VIEW).getV8StringFromField();
        if (!"".equals(selectedItem) && !"0".equals(selectedItem)) {
            loadScreen(selectedItem, componentId, gtnUIFrameWorkActionConfig);

        }
    }

    private void loadScreen(String selectedItem, String componentId,
            GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        loadTreeGrid(selectedItem, gtnUIFrameWorkActionConfig, componentId);

    }

    private void loadViewName(String customViewName) {
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportCustomViewLookup_hierarchyName").setHasValue(customViewName);
    }

    private void loadVariableType(String viewType) {
        String[] list=viewType.split("~");
        if(list.length==3){
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportCustomViewLookup_custom_Variable_Type_OptionGroup").setHasValue(list[1]);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportCustomViewLookup_custom_Variable_OptionGroup").setHasValue(list[2]);
        }
    }

    private void loadTreeGrid(String selectedItem,
            GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String componentId) throws GtnFrameworkGeneralException {

        loadFields(selectedItem, gtnUIFrameWorkActionConfig);

    }

    private void removeFromLeftTable(GtnWsRecordBean currentTempData, char hierarchyType) {
        switch (hierarchyType) {
            case 'C':
                removeLeftCustomer(currentTempData);
                break;
            case 'P':
                removeLeftProduct(currentTempData);
                break;
            case 'D':
                removeLeftDeduction(currentTempData);
                break;
            case 'V':
                removeLeftVariables(currentTempData);
                break;
        }

    }

    private void removeLeftVariables(GtnWsRecordBean currentTempData) {
        removeFromGrid("reportCustomViewLookupcustomViewLookupVariableTable", currentTempData);

    }
    
    @SuppressWarnings("unchecked")
    private void removeFromGrid(String string, GtnWsRecordBean currentTempData) {
        Grid<GtnWsRecordBean> gridComponent = (Grid<GtnWsRecordBean>) GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(string).getComponent();

      
        Optional<GtnWsRecordBean> itemList = ((ListDataProvider<GtnWsRecordBean>) gridComponent.getDataProvider())
                .getItems().stream().filter(recordBean -> recordBean.getStringPropertyByIndex(0)
                        .equals(currentTempData.getStringPropertyByIndex(0)))
                .findFirst();
        itemList.ifPresent(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(string)::removeItemsFromGrid);
    }

    private void removeLeftDeduction(GtnWsRecordBean currentTempData) {
        removeFromGrid("reportCustomViewLookupcustomViewLookupDeductionTable", currentTempData);

    }

    private void removeLeftProduct(GtnWsRecordBean currentTempData) {
        removeFromGrid("reportCustomViewLookupcustomViewLookupProductTable", currentTempData);

    }

    private void removeLeftCustomer(GtnWsRecordBean currentTempData) {
        removeFromGrid("reportCustomViewLookupcustomViewLookupCustomerTable", currentTempData);

    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

    private void loadFields(String customSid, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
        final GtnUIFrameworkWebserviceRequest generalRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsCustomViewRequest cvRequest = new GtnWsCustomViewRequest();

        cvRequest.setCvSysId(Integer.parseInt(customSid));
        cvRequest.setCustomViewType("reportStatic");
        generalRequest.setGtnWsCustomViewRequest(cvRequest);
        GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
                GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE
                + GtnWsCustomViewConstants.CUSTOM_VIEW_GET_TREE_DATA,
                generalRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        GtnWsCustomViewResponse cvResponse = response.getGtnWsCustomViewResponse();
        @SuppressWarnings("unchecked")
        TreeGrid<GtnWsRecordBean> treeGrid = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent((String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1))
                .getTreeGrid();

        TreeData<GtnWsRecordBean> treeData = new TreeData<>();
        treeGrid.setTreeData(treeData);
        customizeData(cvResponse, treeGrid);
        loadViewName(cvResponse.getCustomViewName());
        loadVariableType(cvResponse.getCustomViewType());

    }

    public TreeData<GtnWsRecordBean> customizeData(GtnWsCustomViewResponse cvResponse, TreeGrid<GtnWsRecordBean> treeGrid) {
        TreeData<GtnWsRecordBean> treeData = treeGrid.getTreeData();
        Optional.ofNullable(cvResponse.getCvTreeNodeList()).ifPresent(beans-> {

            GtnWsRecordBean parent = null;
            List<GtnWsRecordBean> variables = new ArrayList<>();
            int j = 0;
            for (GtnWsRecordBean bean :beans) {

                char indicator = bean.getStringPropertyByIndex(3).toUpperCase().charAt(0);
                if (parent == null) {
                    treeData.addItem(null, bean);
                } else {
                    if (indicator == 'V') {
                        variables.add(bean);
                        j++;
                        if (j == cvResponse.getCvTreeNodeList().size() - 1) {
                            addVariablesToTree(variables, treeData, parent, treeGrid);
                        }
                        continue;
                    } else {
                        if (!variables.isEmpty()) {
                            addVariablesToTree(variables, treeData, parent, treeGrid);
                        } else {
                            treeData.addItem(parent, bean);
                            treeGrid.expand(parent);
                        }
                    }
                }
                removeFromLeftTable(bean, indicator);
                parent = bean;
                j++;
            }
        });
        return treeData;
    }

    public void addVariablesToTree(List<GtnWsRecordBean> variables, TreeData<GtnWsRecordBean> treeData, GtnWsRecordBean parent, TreeGrid<GtnWsRecordBean> treeGrid) {
        variables.stream().forEach((gtnWsRecordBean) -> {
            treeData.addItem(parent, gtnWsRecordBean);
        });
        treeGrid.expand(parent);
        variables.clear();
    }

}
