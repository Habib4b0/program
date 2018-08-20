package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkCffResultTableResetAction implements GtnUIFrameWorkAction , GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCffResultTableResetAction.class);
	
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}
	
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Started execution of GtnFrameworkCffResultTableResetAction");
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_OUTBOUND_RESULTS_TABLE);
		
		tableBaseComponent.getComponentConfig().getComponentType().getGtnComponent().resetToDefault(GtnFrameworkProcessSchedulerStringContants.CFF_OUTBOUND_RESULTS_TABLE,
				tableBaseComponent.getComponentConfig());
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_APPROVAL_DATE_FROM_ID)
		.loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_APPROVAL_DATE_TO_ID)
		.loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_CREATION_DATE_FROM_ID)
		.loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_CREATION_DATE_TO_ID)
		.loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.TYPE_ID)
		.loadComboBoxComponentValue(Integer.parseInt("0"));
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_ID)
		.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_NAME_ID)
		.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.PROJECTION_ID)
		.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.PROJECTION_NAME_ID)
		.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CUSTOMER_NO_ID)
		.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CUSTOMER_NAME_ID)
		.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.COMPANY_NO_ID)
		.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.COMPANY_NAME_ID)
		.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CONTRACT_NO_ID)
		.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CONTRACT_NAME_ID)
		.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.BUSINESS_UNIT_NAME_ID)
		.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.BUSINESS_UNIT_NO_ID)
		.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.ITEM_NAME_ID)
		.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.ITEM_NO_ID)
		.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
