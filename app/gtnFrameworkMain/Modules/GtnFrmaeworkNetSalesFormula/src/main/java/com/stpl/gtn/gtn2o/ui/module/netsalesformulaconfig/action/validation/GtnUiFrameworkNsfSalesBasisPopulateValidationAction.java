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

public class GtnUiFrameworkNsfSalesBasisPopulateValidationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

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
		  
		String fieldSalesBasis=GtnUIFrameworkGlobalUI.getVaadinBaseComponent("netSalesFormulaAddView_massUpdateDdlb").getStringFromField();
		String valueSalesBasis=GtnUIFrameworkGlobalUI.getVaadinBaseComponent("netSalesFormulaAddView_massUpdateNetSalesRuleNo").getStringFromField();
		
		if (!isCheck) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.INFO_ACTION);
			List<Object> alertParams = new ArrayList<>();
			alertParams.add(GtnFrameworkNSFConstants.getPopulateButtonCheckRecordErrorSalesBasisHeader());
			alertParams.add(GtnFrameworkNSFConstants.getPopulateButtonCheckRecordSalesBasisErrorMsg());
			alertActionConfig.setActionParameterList(alertParams);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkSkipActionException("Please check a record.");
		}
		else if(fieldSalesBasis.isEmpty())
		{
			GtnUIFrameWorkActionConfig alertActionConfigForFieldSalesBasis = new GtnUIFrameWorkActionConfig();
			alertActionConfigForFieldSalesBasis.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> alertParamsForFieldSalesBasis = new ArrayList<>();
			alertParamsForFieldSalesBasis.add(GtnFrameworkNSFConstants.getNetSalesMassUpdateFieldErrorHeader());
			alertParamsForFieldSalesBasis.add(GtnFrameworkNSFConstants.getNetSalesMassUpdateFieldErrorMsg());
			alertActionConfigForFieldSalesBasis.setActionParameterList(alertParamsForFieldSalesBasis);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfigForFieldSalesBasis);
		}
		else if(valueSalesBasis.isEmpty())
		{
			GtnUIFrameWorkActionConfig alertActionConfigForValueSalesBasis = new GtnUIFrameWorkActionConfig();
			alertActionConfigForValueSalesBasis.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> alertParamsForValueSalesBasis = new ArrayList<>();
			alertParamsForValueSalesBasis.add(GtnFrameworkNSFConstants.getNetSalesMassUpdateValueErrorHeader());
			alertParamsForValueSalesBasis.add(GtnFrameworkNSFConstants.getNetSalesMassUpdateValueErrorMsg());
			alertActionConfigForValueSalesBasis.setActionParameterList(alertParamsForValueSalesBasis);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfigForValueSalesBasis);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
