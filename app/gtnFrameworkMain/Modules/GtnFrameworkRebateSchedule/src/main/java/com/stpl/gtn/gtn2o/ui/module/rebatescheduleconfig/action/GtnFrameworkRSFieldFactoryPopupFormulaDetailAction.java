package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Deepika.KrishnaKumar
 */
public class GtnFrameworkRSFieldFactoryPopupFormulaDetailAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String parameter = gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString();

		GtnUIFrameworkBaseComponent formulaComponentId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameter);
		GtnWsRecordBean recordBean = (GtnWsRecordBean) formulaComponentId.getValueFromComponent();

		if (recordBean == null) {

			GtnUIFrameWorkActionConfig alertActionFormulaPopupConfig = new GtnUIFrameWorkActionConfig();
			alertActionFormulaPopupConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

			List<Object> formulaPopupMsgParamsList = new ArrayList<>();
			formulaPopupMsgParamsList.add("Select Error");
			formulaPopupMsgParamsList.add("Please select a row from the Results list view to proceed");
			alertActionFormulaPopupConfig.setActionParameterList(formulaPopupMsgParamsList);

			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionFormulaPopupConfig);

			throw new GtnFrameworkValidationFailedException("IsRecordSelected  validation Failed");
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
