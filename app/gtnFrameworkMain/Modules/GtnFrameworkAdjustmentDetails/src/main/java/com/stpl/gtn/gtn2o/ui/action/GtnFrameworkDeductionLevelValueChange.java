/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import static com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI.getCurrentSessionBean;
import static com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI.getCurrentUser;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.adjustmentdetails.GtnWsAdjusmentDetailsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.List;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnFrameworkDeductionLevelValueChange implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        // No Need to Implement. Its an unused method.
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
        String deductionLevels = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString()).getCaptionFromComboBox();
        GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString());
        GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsAdjusmentDetailsRequest request = new GtnWsAdjusmentDetailsRequest();
        request.setDeductionLevel(deductionLevels);
        gtnRequest.setGtnWsAdjusmentDetailsRequest(request);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_CONTROLLER
                + GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_SERVICE,
                gtnRequest, getGtnWsSecurityToken());
        component.loadCheckedCombobox("Select Variables", response.getItemCodeList(), response.getItemValueList());
    }

    public static GtnWsSecurityToken getGtnWsSecurityToken() {

        GtnWsSecurityToken token = new GtnWsSecurityToken();
        token.setUserId(getCurrentUser());
        token.setSessionId(getCurrentSessionBean().getSessionId());
        return token;

    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }
}
