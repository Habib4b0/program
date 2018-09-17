package com.stpl.gtn.gtn2o.ui.action;

import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;

public class GtnFrameworkUICustomTreeRemoveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private static final GtnWSLogger GTNLOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkUICustomTreeRemoveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String leftGridId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		String rightGridId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);
		GtnWsHierarchyType type = (GtnWsHierarchyType) gtnUIFrameWorkActionConfig.getActionParameterList().get(3);
		Grid<GtnWsRecordBean> leftGrid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(leftGridId, componentId)
				.getGrid();
		TreeGrid<GtnWsRecordBean> rightGrid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(rightGridId, componentId)
				.getTreeGrid();
		checkSelectedItems(rightGrid, type, componentId);
		GtnWsRecordBean removedBean = removeBeanFromTree(rightGrid);
		addToLeftGrid(leftGrid, removedBean);
	}

	private void addToLeftGrid(Grid<GtnWsRecordBean> leftGrid, GtnWsRecordBean removedBean) {
		((ListDataProvider<GtnWsRecordBean>) leftGrid.getDataProvider()).getItems().add(removedBean);
		sortLeftTableData(
				(List<GtnWsRecordBean>) ((ListDataProvider<GtnWsRecordBean>) leftGrid.getDataProvider()).getItems());
		leftGrid.getDataProvider().refreshAll();
	}

	private GtnWsRecordBean removeBeanFromTree(TreeGrid<GtnWsRecordBean> rightGrid) {
		GtnWsRecordBean selectedBean = rightGrid.getSelectedItems().iterator().next();
		rightGrid.deselect(selectedBean);
		rightGrid.getTreeData().removeItem(selectedBean);
		rightGrid.getDataProvider().refreshAll();
		rightGrid.markAsDirty();
		return selectedBean;
	}

	private void checkSelectedItems(TreeGrid<GtnWsRecordBean> rightGrid, GtnWsHierarchyType type, String componentId)
			throws GtnFrameworkGeneralException {
		validateEmptySelect(rightGrid, componentId);
		validateSameLevelToBeRemoved(rightGrid, type, componentId);
		doesChildRemoved(rightGrid, componentId);
	}

	private void doesChildRemoved(TreeGrid<GtnWsRecordBean> rightGrid, String componentId)
			throws GtnFrameworkGeneralException {
		GtnWsRecordBean selectedBean = rightGrid.getSelectedItems().iterator().next();
		List<GtnWsRecordBean> childList = rightGrid.getTreeData().getChildren(selectedBean);
		if (!childList.isEmpty()) {
			GtnUIFrameWorkActionConfig invalidButtonNotificationConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.ALERT_ACTION);
			String message = "Please remove all children nodes before removing a parent node";
			invalidButtonNotificationConfig.addActionParameter("Illegal level");
			invalidButtonNotificationConfig.addActionParameter(message);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, invalidButtonNotificationConfig);
			throw new GtnFrameworkSkipActionException(GtnFrameworkReportStringConstants.EMPTY_SELECTION);
		}
	}

	private void validateSameLevelToBeRemoved(TreeGrid<GtnWsRecordBean> rightGrid, GtnWsHierarchyType type,
			String componentId) throws GtnFrameworkGeneralException {

		GTNLOGGER.info("Inside validateSameLevelToBeRemoved");

		GtnWsRecordBean selectedBean = rightGrid.getSelectedItems().iterator().next();

		GTNLOGGER.info(
				"selectedBean.getStringPropertyByIndex(3): " + selectedBean.getStringPropertyByIndex(3).toLowerCase());
		GTNLOGGER.info("type.toString(): " + type);

		if (!type.toString().startsWith(selectedBean.getStringPropertyByIndex(3).toLowerCase())) {
			GtnUIFrameWorkActionConfig invalidButtonNotificationConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.ALERT_ACTION);
			String message = String.format("Level which is selected belogs to %s Hierarchy",
					selectedBean.getStringPropertyByIndex(3));
			invalidButtonNotificationConfig.addActionParameter("Illegal level");
			invalidButtonNotificationConfig.addActionParameter(message);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, invalidButtonNotificationConfig);
			throw new GtnFrameworkSkipActionException(GtnFrameworkReportStringConstants.EMPTY_SELECTION);
		}
	}

	private void validateEmptySelect(TreeGrid<GtnWsRecordBean> rightGrid, String componentId)
			throws GtnFrameworkGeneralException {
		if (rightGrid.getSelectedItems().isEmpty()) {
			GtnUIFrameWorkActionConfig notificationConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.ALERT_ACTION);
			notificationConfig.addActionParameter(GtnFrameworkReportStringConstants.SELECT_A_ROW_CAPTION);
			notificationConfig.addActionParameter(GtnFrameworkReportStringConstants.NO_LEVEL_SELECTED_MSG);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationConfig);
			throw new GtnFrameworkSkipActionException(GtnFrameworkReportStringConstants.EMPTY_SELECTION);
		}

	}

	private void sortLeftTableData(List<GtnWsRecordBean> items) {
		Collections.sort(items, (comparatorObj1, comparatorObj2) -> comparatorObj1.getIntegerPropertyByIndex(2)
				- comparatorObj2.getIntegerPropertyByIndex(2));
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
