package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.vaadin.ui.AbstractComponent;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtFilterTable;

public class GtnFrameworkRPSelectMsgAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> selectParameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String tableId = (String) selectParameters.get(1);
		String componentPropertyId = (String) selectParameters.get(2);
		List<String> columInputIds = (List<String>) selectParameters.get(3);

		AbstractComponent abstractComponentObj = GtnUIFrameworkGlobalUI.getVaadinComponent(tableId, componentId);
		GtnUIFrameworkComponentData dataComponent = (GtnUIFrameworkComponentData) abstractComponentObj.getData();
		ExtFilterTable resultForRPTable = (ExtFilterTable) dataComponent.getCustomData();
		GtnUIFrameworkComponentData componentIdData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(componentPropertyId, componentId).getComponentData();
		componentIdData.setCustomData(resultForRPTable.getValue());

		GtnWsRecordBean resultDto = (GtnWsRecordBean) resultForRPTable.getValue();

		for (int i = 0; i < columInputIds.size(); i++) {
			if (resultDto == null) {
				GtnUIFrameWorkActionConfig alertConfigAction = new GtnUIFrameWorkActionConfig();
				alertConfigAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
				List<Object> alertMsgParamsList = new ArrayList<>();
				alertMsgParamsList.add("Select Error");
				alertMsgParamsList.add("Please select a record to view ");
				alertConfigAction.setActionParameterList(alertMsgParamsList);
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertConfigAction);

				throw new GtnFrameworkValidationFailedException("IsRecordSelected  validation Failed");
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
