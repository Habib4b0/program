package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkMandatoryFieldSettingAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkMandatoryFieldSettingAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Auto-generated method stub
		
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Executing GtnFrameworkMandatoryFieldSettingAction");
		GtnUIFrameWorkActionConfig validationActionConfig =GtnUIFrameworkGlobalUI.getVaadinBaseComponent("scheduledProcessEditorUpdateButton").getComponentConfig().getGtnUIFrameWorkActionConfigList().get(1);
		List<String> fieldValues= new ArrayList<>();
		gtnLogger.info(" ................... "+GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.FREQUENCY_ID).getStringFromField());
		String optionGroupValue =GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.FREQUENCY_ID).getStringFromField();
		fieldValues.add(GtnFrameworkProcessSchedulerStringContants.PROCESS_NAME_ID);
		fieldValues.add(GtnFrameworkProcessSchedulerStringContants.STATUS_ID);
		fieldValues.add(GtnFrameworkProcessSchedulerStringContants.START_DATE_ID);
		fieldValues.add(GtnFrameworkProcessSchedulerStringContants.END_DATE_ID);		
		
		if(GtnFrameworkProcessSchedulerStringContants.TIME.equals(optionGroupValue)) {
			gtnLogger.info(" ................... time part executed");
			fieldValues.add(GtnFrameworkProcessSchedulerStringContants.RUN1_ID);
		}else if(GtnFrameworkProcessSchedulerStringContants.INTERVAL.equals(optionGroupValue)) {
			fieldValues.add(GtnFrameworkProcessSchedulerStringContants.RUN_EVERY_ID);
			gtnLogger.info(" ................... interval part executed");
		}
		gtnLogger.info(" ................... last item of fieldvalue: "+fieldValues.get(fieldValues.size()-1)+"size  "+fieldValues.size());
		
		validationActionConfig.setFieldValues(fieldValues);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		//  Auto-generated method stub
		return this;
	}

}
