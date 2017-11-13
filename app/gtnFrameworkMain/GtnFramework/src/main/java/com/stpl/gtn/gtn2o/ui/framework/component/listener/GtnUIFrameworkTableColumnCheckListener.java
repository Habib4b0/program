package com.stpl.gtn.gtn2o.ui.framework.component.listener;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.ExtCustomTable;

public class GtnUIFrameworkTableColumnCheckListener implements ExtCustomTable.ColumnCheckListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tableComponentId;

	public GtnUIFrameworkTableColumnCheckListener(String tableComponentId) {
		this.tableComponentId = tableComponentId;
	}

	@Override
	public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
		try {
			GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(tableComponentId,
					tableComponentId);
			GtnUIFrameworkComponentConfig componentConfig = componentData.getCurrentComponentConfig();
			componentData.setActionParameter(new GtnUIFrameworkActionParameter());
			componentData.getActionParameter().setCurrentValue(event.isChecked());
			componentData.getActionParameter().setPropertyId(String.valueOf(event.getPropertyId()));
			GtnUIFrameworkActionExecutor.executeActionList(componentData.getComponentIdInMap(),
					componentConfig.getGtnPagedTableConfig().getColumnCheckActionConfigList());
			componentData.getCurrentPageTableLogic().getCheckedRecordCount();
		} catch (GtnFrameworkGeneralException ex) {
			getGtnLogger().error(ex.getMessage());
		}
	}

	public GtnWSLogger getGtnLogger() {
		return GtnWSLogger.getGTNLogger(GtnUIFrameworkTableColumnCheckListener.class);
	}

}
