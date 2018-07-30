package com.stpl.gtn.gtn2o.ui.action.validation;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionTableCheckAllBean;
import com.stpl.gtn.gtn2o.ws.transaction.constants.GtnWsTransactionConstants;
import java.util.ArrayList;

public class GtnFrameworkTransactionReprocessRemoveValidation
        implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        return;
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {

        List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();

        GtnWSTransactionTableCheckAllBean checkBean = (GtnWSTransactionTableCheckAllBean) GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(String.valueOf(actionParametersList.get(2))).getComponentData()
                .getSharedPopupData();

        if (!checkBean.isCheckAll() && checkBean.getCheckedIdSet().isEmpty()) {
            Object header = String.valueOf(actionParametersList.get(1));
            String messageBody = "Please check mark at least one row from the Results list view";
            GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
            GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
            alertActionConfig.setActionParameterList(Arrays.asList(header, messageBody));
            alertAction.doAction(componentId, alertActionConfig);
            throw new GtnFrameworkSkipActionException("Skip Action");
        }

        GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
        alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
        List<Object> alertParamsList = new ArrayList<>();
        alertParamsList.add("Already Running ");
        alertParamsList.add("Already Running Process");
        alertAction.setActionParameterList(alertParamsList);

        GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
        GtnWsTransactionRequest gtnWsTransactionRequest = new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("ADJUSTMENT_DETAIL_OUTBOUND_INTERFACE");

        request.setGtnWsTransactionRequest(gtnWsTransactionRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsTransactionConstants.GTN_WS_TRANSACTION_SERVICE
                + GtnWsTransactionConstants.GTN_WS_TRANSACTION_VALIDATION_SERVICE,
                request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

        if ("Y".equals(response.getOutBountData()[0])) {
            GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertAction);
            throw new GtnFrameworkSkipActionException("Skip Action");
        }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
