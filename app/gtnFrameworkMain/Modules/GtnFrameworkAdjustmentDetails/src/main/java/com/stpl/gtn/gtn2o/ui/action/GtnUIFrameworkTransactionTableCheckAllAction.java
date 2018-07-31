/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionTableCheckAllBean;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnUIFrameworkTransactionTableCheckAllAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        return;
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
        GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
        GtnUIFrameworkComponentData componentData = baseComponent.getComponentData();
        GtnUIFrameworkActionParameter actionParameter = componentData.getActionParameter();
        boolean check = "true".equals(String.valueOf(actionParameter.getCurrentValue()));

        GtnWSTransactionTableCheckAllBean checkBean = (GtnWSTransactionTableCheckAllBean) GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(actionParametersList.get(1).toString()).getComponentData().getSharedPopupData();
        if (checkBean == null) {
            checkBean = new GtnWSTransactionTableCheckAllBean();
            GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(actionParametersList.get(1).toString()).getComponentData().setSharedPopupData(checkBean);
        }
        checkBean.setCheckAll(check);
        checkBean.setCheckedIdSet(new HashSet<>());
        checkBean.setUnCheckedIdSet(new HashSet<>());
        refreshTable(baseComponent, actionParameter.getPropertyId(), actionParameter.getCurrentValue());

    }

    private void refreshTable(GtnUIFrameworkBaseComponent baseComponent, String propertyId, Object value)
            throws GtnFrameworkValidationFailedException {
        List<GtnWsRecordBean> tableItemIds = baseComponent.getItemsFromDataTable();
        for (GtnWsRecordBean tableItemId : tableItemIds) {
            tableItemId.addProperties(propertyId, value);
            baseComponent.setTableColumnValue(tableItemId, propertyId, value);
        }
        baseComponent.setTableRefresh(true);
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {

        return this;
    }

}
