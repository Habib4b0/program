package com.stpl.gtn.gtn2o.ui.framework.component.listener;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTable.ColumnCheckEvent;

public class GtnUIFrameworkDataTableColumnCheckListener implements ExtCustomTable.ColumnCheckListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GtnUIFrameworkComponentData componentData;

	public GtnUIFrameworkDataTableColumnCheckListener(GtnUIFrameworkComponentData componentData) {
		this.componentData = componentData;
	}

	@Override
	public void columnCheck(ColumnCheckEvent event) {
		try {
			GtnUIFrameworkComponentConfig componentConfig = componentData.getCurrentComponentConfig();
			componentData.setActionParameter(new GtnUIFrameworkActionParameter());
			componentData.getActionParameter().setCurrentValue(event.isChecked());
			componentData.getActionParameter().setPropertyId(String.valueOf(event.getPropertyId()));
			GtnUIFrameworkActionExecutor.executeActionList(componentData.getComponentIdInMap(),
					componentConfig.getGtnPagedTableConfig().getColumnCheckActionConfigList());
		} catch (GtnFrameworkGeneralException ex) {
			getGtnLogger().error(ex.getMessage());
		}

	}

	public GtnWSLogger getGtnLogger() {
		return GtnWSLogger.getGTNLogger(GtnUIFrameworkDataTableColumnCheckListener.class);
	}
}
