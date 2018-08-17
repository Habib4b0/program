package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkAdditionalSearchCriteriaAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAdditionalSearchCriteriaAction.class);
	
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Auto-generated method stub
		
	}
	
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		
		gtnLogger.info("SeesionId="+
		GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)
		);
		String sessionId=GtnUIFrameworkGlobalUI.getCurrentSessionBean().getSessionId();
		String userId = GtnUIFrameworkGlobalUI.getCurrentSessionBean().getUserId();
		gtnLogger.info("Session="+sessionId+ "  userId="+userId);
		GtnWebServiceSearchCriteria sessionIdSearchCriteria=new GtnWebServiceSearchCriteria();
		sessionIdSearchCriteria.setFieldId("sessionId");
		sessionIdSearchCriteria.setFilterValue1(sessionId);
		
		sessionIdSearchCriteria.setExpression("EQUALS");
		
		GtnWebServiceSearchCriteria userIdSearchCriteria=new GtnWebServiceSearchCriteria();
		userIdSearchCriteria.setFieldId("userId");
		userIdSearchCriteria.setFilterValue1(userId);
		
		userIdSearchCriteria.setExpression("EQUALS");
		
		 GtnUIFrameworkGlobalUI
         .getVaadinComponentData(GtnFrameworkProcessSchedulerStringContants.CFF_OUTBOUND_RESULTS_TABLE)
         .getCurrentPageTableLogic().setAdditioanlSearchCriteriaList(Arrays.asList(sessionIdSearchCriteria,userIdSearchCriteria));
	}
	
	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
