package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.TreeData;
import com.vaadin.ui.TreeGrid;

public class GtnReportCCPTableLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportCCPTableLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(String.valueOf(actionParamList.get(1)), componentId);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) gtnUIFrameworkComponentData
				.getCustomData();
		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		rightTable.expand(rightTable.getTreeData().getRootItems());
		List<GtnWsRecordBean> selectedvalues = rightTable.getTreeData().getRootItems();

		for (GtnWsRecordBean gtnWsRecordBean : selectedvalues) {
			logger.info(gtnWsRecordBean.getStringProperty("levelName"));
		}
	}

	private void addSelectedValues(List values, TreeGrid<GtnWsRecordBean> rightTable, GtnWsRecordBean selectedvalues) {
		for (GtnWsRecordBean gtnWsRecordBean : rightTable.getTreeData().getChildren(selectedvalues)) {
			logger.info(gtnWsRecordBean.getStringProperty("levelName"));
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
