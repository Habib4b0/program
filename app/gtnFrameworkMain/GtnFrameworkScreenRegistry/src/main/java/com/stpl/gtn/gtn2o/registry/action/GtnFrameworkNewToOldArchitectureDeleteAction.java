/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.registry.action;

import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastInputBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class GtnFrameworkNewToOldArchitectureDeleteAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

    private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastInnerLevelLoadAction.class);

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {

        List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
        String gridComponentId = actionParamsList.get(1).toString();

        AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(gridComponentId, componentId);
        GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) abstractComponent.getData();
        Set<GtnWsRecordBean> rows = componentData.getPagedGrid().getValue();
        GtnWsRecordBean selectedRow = rows.isEmpty() ? null : rows.iterator().next();

        String userId = GtnUIFrameworkGlobalUI.getCurrentUser();
        String sessionId = String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId"));

        try {
            GtnFrameworkForecastInputBean inputBean = formForecastInputBean(selectedRow, actionParamsList);
            inputBean.setUserId(userId);
            inputBean.setSessionId(sessionId);
            ForecastUI ui = new ForecastUI();
            String nameSpace = "Commercial Forecasting";
            ui.getForecastingToDelete(inputBean);
            GtnUIFrameWorkActionConfig loadDataSearchTableActionConfig = new GtnUIFrameWorkActionConfig();
            loadDataSearchTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
            loadDataSearchTableActionConfig.setActionParameterList(
                    Arrays.asList(new Object[]{nameSpace + "_" + "projectionResultsTable"}));
            loadDataSearchTableActionConfig.setFieldValues(
                    Arrays.asList(new String[]{nameSpace + "_" + "projectionName",
                nameSpace + "_" + "projectionDescription",
                nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID,
                nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID,
                "forecastLandingScreen_customerHierarchy",
                nameSpace + "_" + "prodhierarchyName"
            }));
            GtnUIFrameworkActionExecutor.executeSingleAction(componentId, loadDataSearchTableActionConfig);

        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkNewToOldArchitectureDeleteAction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private GtnFrameworkForecastInputBean formForecastInputBean(GtnWsRecordBean selectedRow, List<Object> actionParamsList) throws ParseException {
        GtnFrameworkForecastInputBean inputBean = new GtnFrameworkForecastInputBean();
        try {
            inputBean.setProjectionName((String) selectedRow.getPropertyValueByIndex(0));
            inputBean.setProjectionMasterSid((int) selectedRow.getPropertyValueByIndex(12));

        } catch (Exception ex) {
            gtnLogger.info(ex.getMessage());
        }
        return inputBean;
    }

}
