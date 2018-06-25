package com.stpl.gtn.gtn2o.ui.framework.action.duallistbox.v8;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.TreeGrid;

public class GtnUIFrameworkV8ConfirmedDualListBoxResetAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		TreeGrid<GtnWsRecordBean> rightTable = (TreeGrid<GtnWsRecordBean>) actionParamsList.get(1);
		GtnWsRecordBean recordBean = rightTable.getTreeData().getRootItems().get(0);
		rightTable.getTreeData().removeItem(recordBean);
		rightTable.getDataProvider().refreshAll();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
