package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;
import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.TreeGrid;

public class GtnFrameworkUICustomVariablePositionChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String variableTypeId = (String) parameterList.get(1);
		String variableTypeGridId = (String) parameterList.get(2);
		String selectedType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getV8StringFromField();
		boolean isNeedToBeEnabled = selectedType.equals("Rows");

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(variableTypeId, componentId)
				.setComponentEnable(isNeedToBeEnabled);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(variableTypeGridId, componentId)
				.setComponentEnable(isNeedToBeEnabled);

		clearTreeTable(componentId, String.valueOf(parameterList.get(3)));
	}

	private void clearTreeTable(String componentId, String treeComponentId) {
		boolean action = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getComponentConfig()
				.isUserOriginatedFlag();
		if (action) {
			TreeGrid<GtnWsRecordBean> rightGrid = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(treeComponentId, componentId).getTreeGrid();
			Optional.ofNullable(rightGrid).ifPresent(grid -> {
				grid.getTreeData().clear();
				grid.getDataProvider().refreshAll();
			});
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
