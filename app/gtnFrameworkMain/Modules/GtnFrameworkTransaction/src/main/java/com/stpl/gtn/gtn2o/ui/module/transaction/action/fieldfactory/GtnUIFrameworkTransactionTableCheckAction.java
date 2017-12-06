package com.stpl.gtn.gtn2o.ui.module.transaction.action.fieldfactory;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnTransactionUIConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionTableCheckAllBean;

public class GtnUIFrameworkTransactionTableCheckAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinFieldFactoryComponentData(componentId);
		GtnUIFrameworkActionParameter actionParam = componentData.getActionParameter();
		List<Object> currentRecord = actionParam.getItemId().getProperties();
		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String systemID = String.valueOf(currentRecord.get(currentRecord.size() - 1));
		GtnWSTransactionTableCheckAllBean checkBean = (GtnWSTransactionTableCheckAllBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParametersList.get(2).toString()).getComponentData().getSharedPopupData();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParametersList.get(1).toString())
				.getLogicFromPagedDataTable().handleCheckBoxOnItem(GtnTransactionUIConstants.CHECK_RECORD,
						Boolean.parseBoolean(String.valueOf(actionParam.getCurrentValue())));
		if ("true".equals(String.valueOf(actionParam.getCurrentValue()))) {
			if (!checkBean.isCheckAll()) {
				if (checkBean.removeFromUnCheckedIdSet(systemID) && checkBean.getUnCheckedIdSet().isEmpty()) {
					checkBean.setCheckAll(true);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParametersList.get(1).toString())
							.setPagedTableHeaderCheckBox(true, actionParametersList.get(3).toString());
				}
				checkBean.addtoCheckedIdSet(systemID);
			} else {
				checkBean.removeFromUnCheckedIdSet(systemID);
				if (checkBean.getUnCheckedIdSet().isEmpty()) {
					checkBean.setCheckAll(true);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParametersList.get(1).toString())
							.setPagedTableHeaderCheckBox(true, actionParametersList.get(3).toString());
				}
			}
		} else {
			if (checkBean.isCheckAll()) {
				checkBean.setCheckAll(false);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParametersList.get(1).toString())
						.setPagedTableHeaderCheckBox(false, actionParametersList.get(3).toString());
				checkBean.addtoUnCheckedIdSet(systemID);
			} else {
				checkBean.addtoUnCheckedIdSet(systemID);
				checkBean.removeFromCheckedIdSet(systemID);
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}