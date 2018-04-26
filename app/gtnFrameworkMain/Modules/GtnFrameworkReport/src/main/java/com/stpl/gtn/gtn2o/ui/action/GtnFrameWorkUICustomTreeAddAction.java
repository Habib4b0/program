package com.stpl.gtn.gtn2o.ui.action;

import java.util.Set;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.vaadin.data.TreeData;
import com.vaadin.ui.TreeGrid;

public class GtnFrameWorkUICustomTreeAddAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private static final String INVALID_STRUCTURE_CAPTION = "Invalid Structure";
	private static final String INVALID_MSG = "You cannot add %s as a child to %s";

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent leftGrid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				(String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1), componentId);
		GtnUIFrameworkBaseComponent rightTree = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				(String) gtnUIFrameWorkActionConfig.getActionParameterList().get(2), componentId);

		Set<GtnWsRecordBean> selectedBean = leftGrid.getValueFromGird();
		if (selectedBean.isEmpty()) {
			GtnUIFrameWorkActionConfig notificationConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			notificationConfig.addActionParameter(GtnFrameworkReportStringConstants.NO_LEVEL_SELECTED_MSG);
			notificationConfig.addActionParameter(GtnFrameworkReportStringConstants.NO_LEVEL_SELECTED_CAPTION);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationConfig);
			return;
		}

		TreeGrid<GtnWsRecordBean> grid = ((TreeGrid<GtnWsRecordBean>) rightTree.getComponent());
		boolean isEmpty = grid.getSelectedItems().isEmpty();
		GtnWsRecordBean parentBean = isEmpty ? null : grid.getSelectedItems().iterator().next();
		GtnWsRecordBean beanTobeAdded = selectedBean.iterator().next();
		addToTree(leftGrid, grid, parentBean, beanTobeAdded);
	}

	private void addToTree(GtnUIFrameworkBaseComponent leftGrid, TreeGrid<GtnWsRecordBean> grid,
			GtnWsRecordBean parentBean, GtnWsRecordBean beanTobeAdded) throws GtnFrameworkGeneralException {
		checkValidations(grid, beanTobeAdded, parentBean);
		grid.getTreeData().addItem(parentBean, beanTobeAdded);
		grid.select(beanTobeAdded);
		leftGrid.removeItemsFromGrid(beanTobeAdded);
		leftGrid.deSelectItemInGrid(beanTobeAdded);
		grid.expand(grid.getTreeData().getParent(beanTobeAdded));
		grid.getDataProvider().refreshAll();
		grid.markAsDirty();
	}

	private void checkValidations(TreeGrid<GtnWsRecordBean> grid, GtnWsRecordBean beanTobeAdded,
			GtnWsRecordBean parentBean) throws GtnFrameworkGeneralException {
		TreeData<GtnWsRecordBean> data = grid.getTreeData();
		isLowerValueAlreadyAdded(parentBean, beanTobeAdded, data);
		isChildAlreadAdded(data, parentBean, beanTobeAdded);

	}

	private void isChildAlreadAdded(TreeData<GtnWsRecordBean> gridData, GtnWsRecordBean selectedBean,
			GtnWsRecordBean beanTobeAdded) throws GtnFrameworkGeneralException {
		if (!gridData.getChildren(selectedBean).isEmpty()) {
			GtnUIFrameWorkActionConfig notificationConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			String errorMsg = String.format(INVALID_MSG, beanTobeAdded.getStringPropertyByIndex(0),
					selectedBean.getStringPropertyByIndex(0));
			notificationConfig.addActionParameter(errorMsg);
			notificationConfig.addActionParameter(INVALID_STRUCTURE_CAPTION);
			GtnUIFrameworkActionExecutor.executeSingleAction(null, notificationConfig);
			throw new GtnFrameworkSkipActionException("ChilAlreadyAdded");
		}

	}

	private void isLowerValueAlreadyAdded(GtnWsRecordBean next, GtnWsRecordBean beanTobeAdded,
			TreeData<GtnWsRecordBean> gridData) throws GtnFrameworkGeneralException {
		if (next != null) {
			int currentLevel = beanTobeAdded.getIntegerPropertyByIndex(1);
			if (next.getPropertyValueByIndex(2).equals(beanTobeAdded.getStringPropertyByIndex(2))
					&& next.getIntegerPropertyByIndex(1) > currentLevel) {
				GtnUIFrameWorkActionConfig notificationConfig = new GtnUIFrameWorkActionConfig(
						GtnUIFrameworkActionType.NOTIFICATION_ACTION);
				String errorMsg = String.format(INVALID_MSG, beanTobeAdded.getStringPropertyByIndex(0),
						next.getStringPropertyByIndex(0));
				notificationConfig.addActionParameter(errorMsg);
				notificationConfig.addActionParameter(INVALID_STRUCTURE_CAPTION);
				GtnUIFrameworkActionExecutor.executeSingleAction(null, notificationConfig);
				throw new GtnFrameworkSkipActionException("Lower Level Already added. Cannot add higher Level");
			}
			isLowerValueAlreadyAdded(gridData.getParent(next), beanTobeAdded, gridData);
		}
		return;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
