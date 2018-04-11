package com.stpl.gtn.gtn2o.ui.framework.action.duallistbox;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.bean.GtnFrameworkDualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastAlertMsgConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.TreeGrid;
import java.util.ArrayList;
import java.util.List;

public class GtnUIFrameWorkDuallistBoxRightToLeftTableLoadAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) actionParametersList.get(0);
		GtnFrameworkDualListBoxBean dualListBoxBean = (GtnFrameworkDualListBoxBean) dualListBoxData.getCustomData();
		loadLeftTableOnButtonClick(dualListBoxBean, componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void loadLeftTableOnButtonClick(GtnFrameworkDualListBoxBean dualListBoxBean, String componentId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkDualListBoxConfig dualListBoxConfig = dualListBoxBean.getDualListBoxConfig();
		GtnUIFrameworkHierarchyTreeBuilder treeBuilder = dualListBoxBean.getTreeBuilder();
		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		GtnWsRecordBean deleteNode =  rightTable.getSelectedItems().iterator().next();
		if (deleteNode == null) {
			GtnUIFrameWorkActionConfig gtnUIFrameAlertWorkActionConfig = new GtnUIFrameWorkActionConfig();
			gtnUIFrameAlertWorkActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> alertMsgList = new ArrayList<>(2);
			alertMsgList.add(GtnFrameworkForecastAlertMsgConstants.DUAL_LIST_BOX_ITEM_SELECTION_VALIDATION_MSG_HEADER);
			alertMsgList.add(GtnFrameworkForecastAlertMsgConstants.DUAL_LIST_BOX_ITEM_SELECTION_VALIDATION_MSG);
			gtnUIFrameAlertWorkActionConfig.setActionParameterList(alertMsgList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, gtnUIFrameAlertWorkActionConfig);
			return;
		}

		if (treeBuilder.deleteNode(deleteNode)) {
			treeBuilder.loadRightTreeTable(rightTable, dualListBoxConfig.getLoadingLevel());
		}
		rightTable.deselect(deleteNode);
	}
}
