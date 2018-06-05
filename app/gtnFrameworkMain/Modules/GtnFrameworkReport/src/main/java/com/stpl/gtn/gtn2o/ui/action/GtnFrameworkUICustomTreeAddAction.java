package com.stpl.gtn.gtn2o.ui.action;

import java.util.Optional;
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
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;
import com.vaadin.data.TreeData;
import com.vaadin.ui.TreeGrid;

public class GtnFrameworkUICustomTreeAddAction
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
               String rowType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent((String) gtnUIFrameWorkActionConfig.getActionParameterList().get(3), componentId)
                .getV8StringFromField();
                boolean isStatic=rowType.equalsIgnoreCase("static");
                @SuppressWarnings("unchecked")
		TreeGrid<GtnWsRecordBean> grid = ((TreeGrid<GtnWsRecordBean>) rightTree.getComponent());
		boolean isEmpty = grid.getSelectedItems().isEmpty();
		GtnWsRecordBean parentBean = isEmpty ? null : grid.getSelectedItems().iterator().next();
                 
		GtnWsRecordBean beanTobeAdded = selectedBean.iterator().next();
                if(isVariableOrDiscount(parentBean)){
                     parentBean=grid.getTreeData().getParent(parentBean);
                 }
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
		isAddingToVariable(parentBean, beanTobeAdded);
	}

	private void isAddingToVariable(GtnWsRecordBean parentBean, GtnWsRecordBean beanTobeAdded)
			throws GtnFrameworkSkipActionException {
//		if (parentBean != null && GtnWsHierarchyType.VARIABLES.toString().equals(parentBean.getStringPropertyByIndex(3))
//				&& !(beanTobeAdded.getStringPropertyByIndex(3).equals(GtnWsReportVariablesType.VARIABLES.toString()))) {
//			throw new GtnFrameworkSkipActionException("Can't add to Variables");
//		}
		if (parentBean == null
				&& GtnWsHierarchyType.VARIABLES.toString().equals(beanTobeAdded.getStringPropertyByIndex(3))
				&& !beanTobeAdded.getStringPropertyByIndex(3).equals(GtnWsReportVariablesType.VARIABLES.toString())) {
			throw new GtnFrameworkSkipActionException("Can't add  Variables to root Level");
		}

	}

	private void isChildAlreadAdded(TreeData<GtnWsRecordBean> gridData, GtnWsRecordBean selectedBean,
			GtnWsRecordBean beanTobeAdded) throws GtnFrameworkGeneralException {
		if (beanTobeAdded.getStringPropertyByIndex(3).equals(GtnWsHierarchyType.VARIABLES.toString())) {
			return;
		}
		Optional<GtnWsRecordBean> child = gridData.getChildren(selectedBean).stream().filter(
				childBean -> !childBean.getStringPropertyByIndex(0).equals(beanTobeAdded.getStringPropertyByIndex(0)))
				.findFirst();

		if (child.isPresent()) {
			GtnUIFrameWorkActionConfig notificationConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			String errorMsg = String.format(INVALID_MSG, beanTobeAdded.getStringPropertyByIndex(3),
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
			if (next.getPropertyValueByIndex(3).equals(beanTobeAdded.getStringPropertyByIndex(3))
					&& next.getIntegerPropertyByIndex(1) > currentLevel
					&& !isVariableOrDiscount(beanTobeAdded)) {
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
	}
        private static boolean isVariableOrDiscount(GtnWsRecordBean bean){
           return bean != null && (GtnWsHierarchyType.VARIABLES.toString().equals(bean.getStringPropertyByIndex(3))
                   ||GtnWsHierarchyType.DEDUCTION.toString().equals(bean.getStringPropertyByIndex(3)));
        }
	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
