package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Grid;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

public class GtnUIFrameworkV8GridAlertAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String gridId = actionParamsList.get(0).toString();
		String alertMsgHeader = actionParamsList.get(1).toString();
		String alertMsgBody = actionParamsList.get(2).toString();

		configureParams(gtnUIFrameWorkActionConfig);
		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(gridId, componentId);
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) abstractComponent.getData();
		Grid<GtnWsRecordBean> grid = componentData.getPagedGrid().getGrid();
		if (grid.getDataProvider() instanceof ListDataProvider) {
			ListDataProvider<GtnWsRecordBean> dataProvider = (ListDataProvider<GtnWsRecordBean>) grid.getDataProvider();
			if (dataProvider.getItems() != null && !dataProvider.getItems().isEmpty()) {
				return;
			} else {
				MessageBox.showPlain(Icon.ERROR, alertMsgHeader, alertMsgBody, new MessageBoxListener() {

					@Override
					public void buttonClicked(final ButtonId buttonId) {
						return;

					}
				}, ButtonId.OK);
				throw new GtnFrameworkSkipActionException("Skip Action");
			}
		}
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkV8GridAlertAction();
	}

}
