package com.stpl.gtn.gtn2o.ui.module.processmonitor.action;

import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.processmonitor.constants.GtnFrameworkProcessMonitorStringContants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.processmonitor.constants.GtnWsProcessMonitorConstants;

public class GtnFrameworkTableClickAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String tableId = (String) actionParamList.get(1);
		boolean isEditable = (boolean) actionParamList.get(3);

		Object itemId = gtnUIFrameWorkActionConfig.getActionParameterList()
				.get(gtnUIFrameWorkActionConfig.getActionParameterList().size() - 1);
		if (itemId instanceof Boolean) {
			itemId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId).getValueFromPagedDataTable();
		}
		GtnWsRecordBean gtnWsRecordBean = (GtnWsRecordBean) itemId;
		if (gtnWsRecordBean == null) {
			return;
		}

		Object systemid = gtnWsRecordBean.getPropertyValueByIndex(6);

		GtnUIFrameworkGlobalUI.addSessionProperty("processMonitorId", systemid);
		Object processname = gtnWsRecordBean.getPropertyValue("processName");
		Object processType = gtnWsRecordBean.getPropertyValueByIndex(5);

		Object startDate = gtnWsRecordBean.getPropertyValueByIndex(7);
		Object endDate = gtnWsRecordBean.getPropertyValueByIndex(8);
		Object run1Ddlb = gtnWsRecordBean.getPropertyValueByIndex(10) == null ? 0
				: String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(10));
		Object run2Ddlb = gtnWsRecordBean.getPropertyValueByIndex(11) == null ? 0
				: String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(11));
		Object run3Ddlb = gtnWsRecordBean.getPropertyValueByIndex(12) == null ? 0
				: String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(12));

		Object calender = gtnWsRecordBean.getPropertyValueByIndex(13);

		Object minutesDdlbValue = gtnWsRecordBean.getPropertyValueByIndex(14) == null ? 0
				: String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(14));
		Object minutesDdlbValue2 = gtnWsRecordBean.getPropertyValueByIndex(15) == null ? 0
				: String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(15));
		Object minutesDdlbValue3 = gtnWsRecordBean.getPropertyValueByIndex(16) == null ? 0
				: String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(16));

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("processName", componentId).loadFieldValue(processname);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("processType", componentId)
				.loadComboBoxComponentValue((Integer) processType);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("calender", componentId)
				.loadComboBoxComponentValue((Integer) calender);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("startDate", componentId)
				.loadDateValue(startDate != null ? new Date((long) startDate) : startDate);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("endDate", componentId)
				.loadDateValue(endDate != null ? new Date((long) endDate) : endDate);

		GtnUIFrameworkBaseComponent run1DdlbComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run1Ddlb",
				componentId);
		run1DdlbComponent.setPropertyValue(run1Ddlb);

		GtnUIFrameworkBaseComponent run2DdlbComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run2Ddlb",
				componentId);
		run2DdlbComponent.setPropertyValue(run2Ddlb);

		GtnUIFrameworkBaseComponent run3DdlbComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run3Ddlb",
				componentId);
		run3DdlbComponent.setPropertyValue(run3Ddlb);

		GtnUIFrameworkBaseComponent hours1DdlbComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("hours1Ddlb",
				componentId);
		hours1DdlbComponent.setPropertyValue(minutesDdlbValue);

		GtnUIFrameworkBaseComponent hours2DdlbComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("hours2Ddlb",
				componentId);
		hours2DdlbComponent.setPropertyValue(minutesDdlbValue2);

		GtnUIFrameworkBaseComponent hours3DdlbComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("hours3Ddlb",
				componentId);
		hours3DdlbComponent.setPropertyValue(minutesDdlbValue3);

		GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_ADD_BUTTON);
		component.setCaption(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_UPDATE);
		component.setVisible(isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_DELETE)
				.setComponentEnable(isEditable);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
