package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import java.util.List;

import org.asi.ui.extfilteringtable.ExtCustomTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnCffOutBoundBean;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.processscheduler.GtnWsProcessSchedulerRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class CffOutBoundTablefieldFactoryAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {
	
	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(CffOutBoundTablefieldFactoryAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Auto-generated method stub

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info(" Started execution of CffOutBoundTablefieldFactoryAction");
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinFieldFactoryComponentData(componentId);
		GtnUIFrameworkActionParameter actionParam = componentData.getActionParameter();
		
		gtnLogger.info("action param property id: "+actionParam.getPropertyId());
	
		
		gtnLogger.info("Current Value="+actionParam.getCurrentValue());
		gtnLogger.info("Properties="+actionParam.getItemId().getProperties());
		gtnLogger.info("Item Id="+actionParam.getItemId().getProperties().get(1).toString());
		
		GtnCffOutBoundBean gtnCffOutBoundBean=new GtnCffOutBoundBean();
		gtnCffOutBoundBean.setCffDetailsSid(actionParam.getItemId().getIntegerPropertyByIndex(4));
		gtnCffOutBoundBean.setRsModelSid(actionParam.getItemId().getIntegerProperty(GtnFrameworkProcessSchedulerStringContants.RS_MODEL_SID));
		gtnCffOutBoundBean.setPeriodSid(actionParam.getItemId().getIntegerProperty(GtnFrameworkProcessSchedulerStringContants.PERIOD_SID));
		gtnCffOutBoundBean.setCheckedRecord(Boolean.parseBoolean(actionParam.getCurrentValue().toString()));
		
		
		
		GtnWsProcessSchedulerRequest gtnWsProcessSchedulerRequest=new GtnWsProcessSchedulerRequest();
		gtnWsProcessSchedulerRequest.setCffOutBoundBean(gtnCffOutBoundBean);
		
		GtnUIFrameworkWebserviceRequest wsRequest = new GtnUIFrameworkWebserviceRequest();
		wsRequest.setProcessSchedulerRequest(gtnWsProcessSchedulerRequest);
		
		GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_CFF_OUTBOUND_SERVICE_SCREEN
						+ GtnWsProcessScedulerConstants.UPDATE_CHECK_RECORD,
						wsRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		gtnLogger.info(""+response.getGtnWsGeneralResponse().isSucess());
		
//		String typeString = GtnFrameworkIfpStringContants.CHECK_RECORD_ID.equals(actionParam.getPropertyId())
//				? Boolean.class.getName()
//				: Integer.class.getName();
//		String dataType = GtnFrameworkIfpStringContants.getDateFieldPropertiesList()
//				.contains(actionParam.getPropertyId()) ? Date.class.getName() : typeString;
//				
//		if (GtnFrameworkCommonConstants.CHECK_RECORD_ID.equals(actionParam.getPropertyId())) {
//			checkBoxValueChangeLogic();
//		}
		
	}


	private void checkBoxValueChangeLogic() throws GtnFrameworkValidationFailedException {
		ExtCustomTable extCustomTable=GtnUIFrameworkGlobalUI
		.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_OUTBOUND_RESULTS_TABLE).getExtCustomTable(); 
		
		List<GtnWsRecordBean> gtnWsRecordBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_OUTBOUND_RESULTS_TABLE).getItemsFromDataTable();
		gtnLogger.info("---------------> properties: " + gtnWsRecordBean.size());
//		gtnLogger.info("---------------> RecordHeader: " + gtnWsRecordBean.getRecordHeader());
		/*String sessionId=GtnUIFrameworkGlobalUI.getCurrentSessionBean().getSessionId();
		String userId = GtnUIFrameworkGlobalUI.getCurrentSessionBean().getUserId();
		gtnLogger.info("userId: "+GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.USER_ID) );
		gtnLogger.info("sessionId: "+GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID) );
		GtnUIFrameworkWebserviceRequest wsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		
		
		gtnWsGeneralRequest.setUserId(userId);
		gtnWsGeneralRequest.setSessionId(sessionId);
		gtnWsGeneralRequest.setUserId("1");
		gtnWsGeneralRequest.setSessionId("1");
		wsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);*/
		
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		// Auto-generated method stub
		return null;
	}

}
