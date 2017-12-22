package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import org.asi.ui.extfilteringtable.ExtCustomTable;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

public class GtnUIFrameWorkTableAlertAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> paramsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String tableId = (String) paramsList.get(0);
		String messageHeader = (String) paramsList.get(1);
		String messageBody = (String) paramsList.get(2);

		configureParams(gtnUIFrameWorkActionConfig);
		ExtCustomTable table = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId, componentId).getExtCustomTable();
		if (table.getValue() == null || table.getItemIds().isEmpty()) {

			MessageBox.showPlain(Icon.ERROR, messageHeader, messageBody, new MessageBoxListener() {

				@Override
				public void buttonClicked(final ButtonId buttonId) {
					return;

				}
			}, ButtonId.OK);
			throw new GtnFrameworkSkipActionException("Skip Action");
		}
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameWorkTableAlertAction();
	}

}