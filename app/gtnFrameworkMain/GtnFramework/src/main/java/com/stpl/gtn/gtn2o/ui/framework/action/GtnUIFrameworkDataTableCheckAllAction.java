package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkDataTableCheckAllAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent dataTableBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
		GtnUIFrameworkActionParameter actionParameter = dataTableBaseComponent.getComponentData().getActionParameter();
		String propertyId = actionParameter.getPropertyId();
		boolean checAll = (boolean) actionParameter.getCurrentValue();
		List<GtnWsRecordBean> records = new ArrayList<>(dataTableBaseComponent.getItemsFromDataTable());
		for (GtnWsRecordBean record : records) {
			record.addProperties(propertyId, checAll);
		}
		dataTableBaseComponent.removeAllGridItems();
		dataTableBaseComponent.addItemsToGrid(records);
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
