package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
*
* @author Deepak.kumar
*/
public class GtnFrameworkScheduledProcessTableDoubleClickAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkScheduledProcessTableDoubleClickAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		//  Auto-generated method stub
		
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		
		gtnLogger.info("success");
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String tableId = (String) actionParamList.get(1);
		Object itemId = gtnUIFrameWorkActionConfig.getActionParameterList()
				.get(gtnUIFrameWorkActionConfig.getActionParameterList().size() - 1);
		if (itemId instanceof Boolean) {
			itemId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId).getValueFromPagedDataTable();
		}
		GtnWsRecordBean gtnWsRecordBean = (GtnWsRecordBean) itemId;
		if (gtnWsRecordBean == null) {
			return;
		}		
		gtnLogger.debug("---------------> properties: "+gtnWsRecordBean.getProperties());
		gtnLogger.debug("---------------> RecordHeader: "+gtnWsRecordBean.getRecordHeader());
		
		Object processName = gtnWsRecordBean.getPropertyValueByIndex(0);
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("processName").loadV8FieldValue((String)processName);
		
		Object processStatus = gtnWsRecordBean.getPropertyValueByIndex(1);
		
		if(("Inactive".equals((String)processStatus)) || "Active".equals((String)processStatus)){
			
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("status").loadV8ComboBoxComponentValue((String)processStatus);
		}else {
			gtnLogger.debug("---------------> 4 else");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("status").loadV8ComboBoxComponentValue(Integer.parseInt("0"));
		}
			
		
		Object startDate = gtnWsRecordBean.getPropertyValueByIndex(2);		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("startDate").loadDateValue(startDate);	
		Object endDate = gtnWsRecordBean.getPropertyValueByIndex(3);		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("endDate").loadDateValue(endDate);		
		Object frequencyType = gtnWsRecordBean.getPropertyValueByIndex(4);
		
		if("Time".equals((String)frequencyType)) {
			gtnLogger.info("time part will execute-------------------");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("frequency").selectOptionGroupValue("Time");
		}
		if("Interval".equals((String)frequencyType)) {
			gtnLogger.info("Interval part will execute---------------");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("frequency").selectOptionGroupValue("Interval");
		}
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		// Auto-generated method stub
		return null;
	}


}
