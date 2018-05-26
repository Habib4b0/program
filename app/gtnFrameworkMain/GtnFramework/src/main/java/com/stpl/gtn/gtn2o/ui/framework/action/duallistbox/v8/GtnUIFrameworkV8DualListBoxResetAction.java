package com.stpl.gtn.gtn2o.ui.framework.action.duallistbox.v8;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;

public class GtnUIFrameworkV8DualListBoxResetAction implements GtnUIFrameWorkAction {
	
	private static final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkV8DualListBoxResetAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(String.valueOf(actionParamList.get(0)), componentId);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) gtnUIFrameworkComponentData
				.getCustomData();
                String selectedLevelName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(1).toString()).getCaptionFromComboBox();
		Grid<GtnWsRecordBean> leftTable = dualListBoxBean.getLeftTable();
		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		gtnLogger.info("boolean value is ------>" + rightTable.getTreeData().getRootItems().iterator().hasNext());
		if ( rightTable.getTreeData().getRootItems().iterator().hasNext()  && !selectedLevelName.equals("")) {
			String resetMessageHeader = "Confirm Change";
			String resetMessageBody = "You have selected a new Forecast Level. Are you sure you want to proceed? "
					+ "You will lose the current Customer/Product hierarchies if you continue.";

			configureParams(gtnUIFrameWorkActionConfig);
			GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
			confirmActionConfig.addActionParameter(resetMessageHeader);
			confirmActionConfig.addActionParameter(resetMessageBody);
			confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
			List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
			resetActionConfig.setActionType(GtnUIFrameworkActionType.V8CONFIRMED_DUALLISTBOX_RESET_ACTION);
			resetActionConfig.addActionParameter(leftTable);
			resetActionConfig.addActionParameter(rightTable);
			successActionConfigList.add(resetActionConfig);
			confirmActionConfig.addActionParameter(successActionConfigList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmActionConfig);

		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
