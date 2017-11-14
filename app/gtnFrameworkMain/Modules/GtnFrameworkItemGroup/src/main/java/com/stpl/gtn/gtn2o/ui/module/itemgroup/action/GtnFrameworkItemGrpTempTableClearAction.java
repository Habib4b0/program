/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.itemgroup.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemgroup.constants.GtnWsItemGrpContants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;

/**
 *
 * @author deepika.krishnakumar
 */
public class GtnFrameworkItemGrpTempTableClearAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        return;
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {

        GtnUIFrameworkWebserviceRequest itemGrpGtnRequest = new GtnUIFrameworkWebserviceRequest();

        GtnWsGeneralRequest itemGrpGeneralWSRequest = new GtnWsGeneralRequest();
        itemGrpGeneralWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
        itemGrpGeneralWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
        itemGrpGtnRequest.setGtnWsGeneralRequest(itemGrpGeneralWSRequest);
        new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(GtnWsItemGrpContants.GTN_WS_ITEM_GRP_SERVICE + GtnWsItemGrpContants.GTN_WS_ITEM_GRP_TEMP_TABLE_CLEAR_SERVICE,
                 itemGrpGtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
