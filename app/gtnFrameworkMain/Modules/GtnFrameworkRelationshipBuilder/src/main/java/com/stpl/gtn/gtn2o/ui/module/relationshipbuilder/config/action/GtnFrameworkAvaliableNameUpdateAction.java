package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.constants.GtnFrameworkRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;

public class GtnFrameworkAvaliableNameUpdateAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
		tableBaseComponent.setTableValue(null);
		List<GtnWsRecordBean> records = tableBaseComponent.getComponentData().getDataTableRecordList();
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		if (records != null && !records.isEmpty()) {
			String levelName = records.get(0)
					.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NAME.ordinal());
			tableBaseComponent.setTableColumnHeader(GtnFrameworkRelationshipBuilderConstants.AVAILABLE_LIST_COLUMNID,
					levelName);
			int levelNo = records.get(0)
					.getIntegerPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent((String) parameters.get(1), componentId)
					.setCaption("Available &lt; Level " + levelNo + " &gt; :");
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
