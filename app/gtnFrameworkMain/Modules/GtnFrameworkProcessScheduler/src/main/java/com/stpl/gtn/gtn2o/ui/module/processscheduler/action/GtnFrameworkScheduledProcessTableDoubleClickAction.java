package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();//3
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
//		gtnLogger.info(gtnWsRecordBean.getProperties().size()+ "---------------> properties: "+gtnWsRecordBean.getProperties());
//		gtnLogger.info("---------------> RecordHeader: "+gtnWsRecordBean.getRecordHeader());
		
		Object processName = gtnWsRecordBean.getPropertyValueByIndex(0);
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("processName").loadV8FieldValue((String)processName);
		
		Object processStatus = gtnWsRecordBean.getPropertyValueByIndex(1);
		
		if(("Inactive".equals((String)processStatus)) || "Active".equals((String)processStatus)){
			//gtnLogger.info("---------------> 4 if");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("status").loadV8ComboBoxComponentValue((String)processStatus);
		}else {
			//gtnLogger.info("---------------> 4 else");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("status").loadV8ComboBoxComponentValue(Integer.parseInt("0"));
		}
		
		Object startDate = gtnWsRecordBean.getPropertyValueByIndex(2);		
		//GtnUIFrameworkGlobalUI.getVaadinBaseComponent("startDate").loadDateValue(startDate);
		
		LocalDateTime localStartDate = ((Date) startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("startDate").loadV8DateValue(localStartDate.toLocalDate());	
		
		Object endDate = gtnWsRecordBean.getPropertyValueByIndex(3);		
		//GtnUIFrameworkGlobalUI.getVaadinBaseComponent("endDate").loadDateValue(endDate);
		
		LocalDateTime localEndDate = ((Date) endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("endDate").loadV8DateValue(localEndDate.toLocalDate());	
		String frequencyType =(String) gtnWsRecordBean.getPropertyValueByIndex(4);				
		
	
		if("Time".equalsIgnoreCase(frequencyType.trim())) {
			gtnLogger.info("time part will execute-------------------");			
			
			gtnLogger.info("       "+gtnWsRecordBean.getPropertyValueByIndex(12));
			Object startHour1=  gtnWsRecordBean.getPropertyValueByIndex(12);
			Object startHour2=  gtnWsRecordBean.getPropertyValueByIndex(13);
			Object startHour3=  gtnWsRecordBean.getPropertyValueByIndex(14);
			Object startMinute1=  gtnWsRecordBean.getPropertyValueByIndex(15);
			Object startMinute2=  gtnWsRecordBean.getPropertyValueByIndex(16);
			Object startMinute3=  gtnWsRecordBean.getPropertyValueByIndex(17);
			gtnLogger.info("object loaded");
			startHour1= startHour1==null ? Integer.parseInt("0") :startHour1;
			startHour2= startHour2==null ? Integer.parseInt("0") :startHour2;
			startHour3= startHour3==null ? Integer.parseInt("0") :startHour3;
			startMinute1= startMinute1==null ? Integer.parseInt("0") :startMinute1;
			startMinute2= startMinute2==null ? Integer.parseInt("0") :startMinute2;
			startMinute3= startMinute3==null ? Integer.parseInt("0") :startMinute3;
			
			gtnLogger.info("....startHour1..."+startHour1 );
			gtnLogger.info("....startHour2..."+startHour2 );
			gtnLogger.info("....startHour3..."+startHour3 );
			gtnLogger.info("....startMinute1..."+startMinute1 );
			gtnLogger.info("....startMinute2..."+startMinute2 );
			gtnLogger.info("....startMinute3..."+startMinute3 );
			
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run1").loadV8ComboBoxComponentValue(String.valueOf( startHour1));
			gtnLogger.info("....startMinute3..."+startMinute3 );
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run2").loadV8ComboBoxComponentValue(String.valueOf(startHour2));
			gtnLogger.info("....startMinute3..."+startMinute3 );
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run3").loadV8ComboBoxComponentValue(String.valueOf(startHour3));
			gtnLogger.info("....startMinute3..."+startMinute3 );
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("minutes1").loadV8ComboBoxComponentValue(String.valueOf(startMinute1));
			gtnLogger.info("....startMinute3..."+startMinute3 );
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("minutes").loadV8ComboBoxComponentValue(String.valueOf(startMinute2));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("minutes3").loadV8ComboBoxComponentValue(String.valueOf(startMinute3));
			
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("frequency").selectOptionGroupValue("Time");
		}
		if("Interval".equals((String)frequencyType)) {
			gtnLogger.info("Interval part will execute---------------");
			Object startHour=  gtnWsRecordBean.getPropertyValueByIndex(10);
			Object startMinute=  gtnWsRecordBean.getPropertyValueByIndex(11);
			
			startHour= startHour==null ? Integer.parseInt("0") :startHour;
			startMinute= startMinute==null ? Integer.parseInt("0") :startMinute;
			
			gtnLogger.info("....startHour..."+startHour );
			gtnLogger.info("....startMinute..."+startMinute );
			
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("frequency").selectOptionGroupValue("Interval");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("runEvery").loadV8ComboBoxComponentValue(String.valueOf(startHour));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("minutes").loadV8ComboBoxComponentValue(String.valueOf(startMinute));
		}
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("scheduledProcessEditorUpdateButton").setEnable(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("processName").setEnable(false);
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		// Auto-generated method stub
		return null;
	}


}
