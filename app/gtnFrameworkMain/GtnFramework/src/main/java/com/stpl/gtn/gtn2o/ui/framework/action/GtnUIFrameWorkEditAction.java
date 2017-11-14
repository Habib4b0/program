package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.Calendar;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameWorkEditAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String tableId = (String) parameters.get(1);
		String propertyId = (String) parameters.get(2);
		boolean isAdditionalProperty = false;
		int index = 0;
		if (parameters.size() > 3) {
			isAdditionalProperty = (Boolean) parameters.get(3);
			index = (Integer) parameters.get(4);
		}
		GtnWsRecordBean gtnWsRecordBean = null;
		Object itemId = gtnUIFrameWorkActionConfig.getActionParameterList()
				.get(gtnUIFrameWorkActionConfig.getActionParameterList().size() - 1);
		if (itemId instanceof GtnWsRecordBean) {
			gtnWsRecordBean = (GtnWsRecordBean) itemId;
		} else {
			gtnWsRecordBean = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId, componentId)
					.getValueFromPagedDataTable();
		}
		int sysId = 0;
		if (!isAdditionalProperty) {
			sysId = (Integer) gtnWsRecordBean.getPropertyValue(propertyId);
		} else {
			sysId = Integer.valueOf(String.valueOf(gtnWsRecordBean.getProperties().get(index)));
		}
		GtnUIFrameworkGlobalUI.addSessionProperty("systemId", sysId);
		if (GtnUIFrameworkGlobalUI.getSessionProperty("sessionId") == null) {

			GtnUIFrameworkGlobalUI.addSessionProperty("sessionId", Calendar.getInstance().get(Calendar.MILLISECOND));

		}
		gtnUIFrameWorkActionConfig.addActionParameter(sysId);

		onSucess(componentId, gtnUIFrameWorkActionConfig);

            }

	public void onSucess(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkLoadCustomAction gtnUIFrameWorkLoadCustomAction = new GtnUIFrameWorkLoadCustomAction();
		gtnUIFrameWorkLoadCustomAction.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}