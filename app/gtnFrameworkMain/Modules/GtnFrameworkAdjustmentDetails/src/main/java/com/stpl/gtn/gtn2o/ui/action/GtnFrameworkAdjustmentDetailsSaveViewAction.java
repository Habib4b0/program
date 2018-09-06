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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.adjustmentdetails.GtnWsAdjusmentDetailsRequest;
import com.stpl.gtn.gtn2o.ws.request.adjustmentdetails.GtnWsAdjustmentDetailsSaveViewMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sathya.S
 */
public class GtnFrameworkAdjustmentDetailsSaveViewAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkDeductionLevelValueChange.class);

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
        GtnWsAdjusmentDetailsRequest detailsRequest = new GtnWsAdjusmentDetailsRequest();
        detailsRequest.setAdjustmentType((Integer) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString()).getValueFromComponent());
        detailsRequest.setGlCompany((Integer) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4).toString()).getValueFromComponent());
        detailsRequest.setWorkFlowId(getVaadinComponentValue(parameters, 5));

        detailsRequest.setTransactionLevel(getVaadinComponentValue(parameters, 6));
        detailsRequest.setBusinessUnit((Integer) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(7).toString()).getValueFromComponent());
        detailsRequest.setWorkFlowName(getVaadinComponentValue(parameters, 8));

        detailsRequest.setCustomerNo(getVaadinComponentValue(parameters, 9));
        detailsRequest.setItemNo(getVaadinComponentValue(parameters, 10));
        detailsRequest.setDeductionLevel((Integer) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(11).toString()).getValueFromComponent());
        detailsRequest.setCreatedDate((Date) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(12).toString()).getValueFromComponent());

        detailsRequest.setCustomerName(getVaadinComponentValue(parameters, 13));
        detailsRequest.setItemName(getVaadinComponentValue(parameters, 14));
        detailsRequest.setDeductionValue((GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(15).toString()).getValueFromComponent()) == null ? "" : "");
        detailsRequest.setGlDate((Date) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(16).toString()).getValueFromComponent());

        detailsRequest.setOriginalBatchId(getVaadinComponentValue(parameters, 17));
        detailsRequest.setBrandName(getVaadinComponentValue(parameters, 18));
        detailsRequest.setRedemptionPeriodStartDate((Date) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(19).toString()).getValueFromComponent());
        detailsRequest.setRedemptionPeriodEndDate((Date) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(20).toString()).getValueFromComponent());
        detailsRequest.setPostingIndicator(getVaadinComponentValue(parameters, 21));

        detailsRequest.setAccountCategory(getVaadinComponentValue(parameters, 21));
        detailsRequest.setAccountType(getVaadinComponentValue(parameters, 22));
        detailsRequest.setAdjustmentLevel(getVaadinComponentValue(parameters, 23));
        detailsRequest.setAccount(getVaadinComponentValue(parameters, 24));
        request.setViewName(viewName);
        request.setViewType(viewType);
        gtnRequest.setGtnWsAdjustmentDetailsSaveViewMasterRequest(request);
        gtnRequest.setGtnWsAdjusmentDetailsRequest(detailsRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_CONTROLLER
                + GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_SAVE_VIEW_MASTER_SERVICE,
                gtnRequest, getGtnWsSecurityToken());
    }

    public String getVaadinComponentValue(List<Object> parameters, int i) throws GtnFrameworkValidationFailedException {
        Object value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(i).toString()).getValueFromComponent();
        return value == null ? null : value.toString();
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
