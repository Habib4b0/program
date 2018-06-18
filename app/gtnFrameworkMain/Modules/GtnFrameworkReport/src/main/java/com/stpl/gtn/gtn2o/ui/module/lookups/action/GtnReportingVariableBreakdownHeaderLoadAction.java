/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.vaadin.ui.AbstractComponent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnReportingVariableBreakdownHeaderLoadAction implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        return;
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
        String variableBreakdownTableId = actionParameterList.get(0).toString();

        AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(variableBreakdownTableId, componentId).getComponent();
        GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();

        String fromPeriod = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParameterList.get(1).toString(), componentId)
                .getStringCaptionFromV8ComboBox();
        String toPeriod = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParameterList.get(2).toString(), componentId)
                .getStringCaptionFromV8ComboBox();
        String frequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(0).toString())
                .getStringCaptionFromV8ComboBox();
        List<String> inputList = new ArrayList<>();
        inputList.add(fromPeriod);
        inputList.add(toPeriod);
        inputList.add(frequency);

        GtnWsReportDataSelectionBean gtnWsReportDataSelectionBean = new GtnWsReportDataSelectionBean();
        gtnWsReportDataSelectionBean.setVariableBreakdownHeaderLoadList(inputList);
        GtnWsReportRequest gtnWsReportRequest = new GtnWsReportRequest();
        gtnWsReportRequest.setDataSelectionBean(gtnWsReportDataSelectionBean);
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        gtnUIFrameworkWebserviceRequest.setGtnWsReportRequest(gtnWsReportRequest);
        gridComponent.setCustomData(gtnUIFrameworkWebserviceRequest);
        gridComponent.setCustomPagedTreeTableRequest(gtnUIFrameworkWebserviceRequest);
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
