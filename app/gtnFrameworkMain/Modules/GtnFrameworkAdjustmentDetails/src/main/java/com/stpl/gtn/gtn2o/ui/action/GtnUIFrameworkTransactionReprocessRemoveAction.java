package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkAdjustmentDetailsStringConstants;
import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionTableCheckAllBean;

public class GtnUIFrameworkTransactionReprocessRemoveAction
        implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    private final GtnWSLogger gtnLogger = GtnWSLogger
            .getGTNLogger(GtnUIFrameworkTransactionReprocessRemoveAction.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        return;
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        String tableName = GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.MODULE_NAME)
                .toString();
        List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
        GtnWSTransactionTableCheckAllBean checkBean = (GtnWSTransactionTableCheckAllBean) GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParametersList.get(2).toString()).getComponentData().getSharedPopupData();

        List<String> checkedIdSet = new ArrayList<>(checkBean.getCheckedIdSet());
        List<String> unCheckedIDSet = new ArrayList<>(checkBean.getUnCheckedIdSet());
        GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
        GtnWsTransactionRequest gtnWsTransactionRequest = new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setOutBoundTableName(tableName);
        gtnWsTransactionRequest.setTableName(tableName);
        if (GtnFrameworkAdjustmentDetailsStringConstants.REPROCESS.equals(actionParametersList.get(1).toString())) {
            gtnWsTransactionRequest.setReprocessFlag(true);
        }
        gtnWsTransactionRequest.setStagingTableName(tableName);

        if (checkBean.isCheckAll()) {
            GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(actionParametersList.get(3).toString()).getLogicFromPagedDataTable();
            gtnWsTransactionRequest.setSearchCriteria(logic.getCurrentSearchCriteria());
            gtnWsTransactionRequest.setUncheckedIds(unCheckedIDSet);
        } else {
            gtnWsTransactionRequest.setReprocessIds(checkedIdSet);
        }
        gtnWsTransactionRequest.setOutBoundModule((boolean) actionParametersList.get(5));
        request.setGtnWsTransactionRequest(gtnWsTransactionRequest);
        GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_CONTROLLER
                + GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_UPDATE_REPROCESS_SERVICE, request,
                GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        gtnLogger.debug("Reprocess----" + response.getGtnWsGeneralResponse().isSucess());
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {

        return this;
    }
}
