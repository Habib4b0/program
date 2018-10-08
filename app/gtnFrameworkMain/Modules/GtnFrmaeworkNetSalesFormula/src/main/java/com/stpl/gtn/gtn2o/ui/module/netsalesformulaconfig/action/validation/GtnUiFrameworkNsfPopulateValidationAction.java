package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.validation;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;

public class GtnUiFrameworkNsfPopulateValidationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		boolean isSalesBasic = (boolean) actionParemeterList.get(1);  
		boolean isCheck = GtnFrameworkNSFCommonLogic.confirmCheckRecord(isSalesBasic,
				"/" + GtnWsNsfUriConstants.NSF_VALIDATION_SERVICE + "/"
						+ GtnWsNsfUriConstants.NSF_POPULATE_VALIDATION_SERVICE);
		String fieldDeductions=GtnUIFrameworkGlobalUI.getVaadinBaseComponent("netSalesFormulaAddView_selectedDeductionsmassUpdateDdlb").getStringFromField();
		String valueDeductions=GtnUIFrameworkGlobalUI.getVaadinBaseComponent("netSalesFormulaAddView_selectedDeductionsAddSubtractDdlb").getStringFromField();
		
		  
		if (!isCheck) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.INFO_ACTION);
			List<Object> alertParams = new ArrayList<>();
			alertParams.add(GtnFrameworkNSFConstants.getPopulateButtonCheckRecordErrorHeader());
			alertParams.add(GtnFrameworkNSFConstants.getPopulateButtonCheckRecordErrorMsg());
			alertActionConfig.setActionParameterList(alertParams);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkSkipActionException("Please check a record.");
		}
		else if(fieldDeductions.isEmpty())
		{
			GtnUIFrameWorkActionConfig alertActionConfigForField = new GtnUIFrameWorkActionConfig();
			alertActionConfigForField.setActionType(GtnUIFrameworkActionType.INFO_ACTION);
			List<Object> alertParamsForField = new ArrayList<>();
			alertParamsForField.add(GtnFrameworkNSFConstants.getNetSalesMassUpdateFieldErrorHeader());
			alertParamsForField.add(GtnFrameworkNSFConstants.getNetSalesMassUpdateFieldErrorMsg());
			alertActionConfigForField.setActionParameterList(alertParamsForField);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfigForField);
		}
		else if(valueDeductions.isEmpty())
		{
			GtnUIFrameWorkActionConfig alertActionConfigForValue = new GtnUIFrameWorkActionConfig();
			alertActionConfigForValue.setActionType(GtnUIFrameworkActionType.INFO_ACTION);
			List<Object> alertParamsForValue = new ArrayList<>();
			alertParamsForValue.add(GtnFrameworkNSFConstants.getNetSalesMassUpdateValueErrorHeader());
			alertParamsForValue.add(GtnFrameworkNSFConstants.getNetSalesMassUpdateValueErrorMsg());
			alertActionConfigForValue.setActionParameterList(alertParamsForValue);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfigForValue);
		}
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
