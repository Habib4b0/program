package com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.util.ArrayList;
import java.util.Arrays;

public class GtnFrameworkPeriodViewAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable,GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        return;

    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {

        List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
            periodViewOnchange( parameters,componentId);
    }   

    private void periodViewOnchange( List<Object> parameters,String componentId) throws GtnFrameworkGeneralException {
        
       String periodViewValue=GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString()).getStringFromField();
        if (periodViewValue.equals("Multiple")){
            
            for(int i=2;i<parameters.size();i++){
       
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(i).toString())
                .setComponentEnable(true);       
            }        
        }
        else
        {
            
         GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
	 resetActionConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		List<Object> params = new ArrayList<>();
		params.add(Arrays.asList(new String[] { "modeTo", "defaultModeTo", "frequencyTo",
				"defaultFrequencyTo", "periodTo", "periodToTextBox", "defaultPeriodTo", "defaultPeriodToTextBox"}));
		params.add(Arrays.asList(new Object[] {null,null,null,null,null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				 GtnFrameworkCommonStringConstants.STRING_EMPTY,GtnFrameworkCommonStringConstants.STRING_EMPTY,GtnFrameworkCommonStringConstants.STRING_EMPTY}));
		resetActionConfig.setActionParameterList(params);
                GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetActionConfig);
            
         for(int i=2;i<parameters.size();i++){
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(i).toString())
                .setComponentEnable(false);       
        }       
        
        }
    
    
    
    
    
    
    }


    @Override
    public GtnFrameworkPeriodViewAction createInstance() {
        return new GtnFrameworkPeriodViewAction();
    }
}
