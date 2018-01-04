package com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable;

import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import org.asi.ui.extfilteringtable.ExtCustomTable.ColumnCheckEvent;
import org.asi.ui.extfilteringtable.ExtCustomTable.ColumnCheckListener;

/**
 *
 * @author Nimisha.Rakesh
 */
@SuppressWarnings("serial")
public class GtnUIFrameworkPagedTreeTableColumnListener implements ColumnCheckListener {

	private GtnUIFrameworkPagedTreeTableConfig gtnUIFrameworkPagedTreeTableConfig;
	private ExtPagedTreeTable<?> leftTable;
	private GtnUIFrameworkComponentData pagedTreeTableComponentData;

	public GtnUIFrameworkPagedTreeTableColumnListener(
			GtnUIFrameworkPagedTreeTableConfig gtnUIFrameworkPagedTreeTableConfig, ExtPagedTreeTable<?> leftTable,
			GtnUIFrameworkComponentData pagedTreeTableComponentData) {
		this.gtnUIFrameworkPagedTreeTableConfig = gtnUIFrameworkPagedTreeTableConfig;
		this.leftTable = leftTable;
		this.pagedTreeTableComponentData = pagedTreeTableComponentData;
	}

	@Override
	public void columnCheck(ColumnCheckEvent event) {
		for (GtnUIFrameWorkActionConfig actionConfig : gtnUIFrameworkPagedTreeTableConfig
				.getCheckBoxActionConfigList()) {

			try {
				GtnUIFrameworkActionParameter actionParameter = (GtnUIFrameworkActionParameter) leftTable.getData();
				actionParameter.setCurrentValue(event.isChecked());
				actionParameter.setPropertyId(String.valueOf(event.getPropertyId()));
				final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
				actionConfig.setActionParameter(actionParameter);
				action.configureParams(actionConfig);
				action.doAction(pagedTreeTableComponentData.getComponentIdInMap(), actionConfig);
			} catch (GtnFrameworkGeneralException ex) {
				getLogger().error("Exception while columnCheck", ex);
			}

		}

	}

	public GtnWSLogger getLogger() {
		return GtnWSLogger.getGTNLogger(GtnUIFrameworkPagedTreeTableColumnListener.class);
	}

}