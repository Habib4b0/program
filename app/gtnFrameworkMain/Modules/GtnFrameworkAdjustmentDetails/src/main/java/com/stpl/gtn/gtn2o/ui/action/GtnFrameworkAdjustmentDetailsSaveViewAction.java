/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import static com.stpl.gtn.gtn2o.ui.action.GtnFrameworkDeductionLevelValueChange.getGtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.adjustmentdetails.GtnWsAdjustmentDetailsSaveViewMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.List;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnFrameworkAdjustmentDetailsSaveViewAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
        String viewName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString()).getValueFromComponent().toString();
        String viewType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString()).getValueFromComponent().toString();

        GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsAdjustmentDetailsSaveViewMasterRequest request = new GtnWsAdjustmentDetailsSaveViewMasterRequest();
        request.setViewName(viewName);
        request.setViewType(viewType);
        gtnRequest.setGtnWsAdjustmentDetailsSaveViewMasterRequest(request);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_CONTROLLER
                + GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_SAVE_VIEW_MASTER_SERVICE,
                gtnRequest, getGtnWsSecurityToken());
//        component.loadCheckedCombobox("Select Variables", response.getItemCodeList(), response.getItemValueList());
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
