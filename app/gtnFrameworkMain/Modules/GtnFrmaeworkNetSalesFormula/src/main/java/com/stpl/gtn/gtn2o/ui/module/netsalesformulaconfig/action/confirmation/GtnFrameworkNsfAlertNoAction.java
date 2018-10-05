package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnUIFrameworkNsfFormulaType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkNsfAlertNoAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {  
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		GtnUIFrameworkNsfFormulaType formulaTypeValue = GtnUIFrameworkNsfFormulaType.getInstance();
		formulaTypeValue.setFormulaTypeValue("test");
		int size = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + GtnFrameworkCommonConstants.AVAILABLE_DEDUCTIONS_TABLE)
				.getTableColumns().length;
		if (size > 3
				&& !GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + GtnFrameworkCommonConstants.FORMULA_TYPE)
						.getCaptionFromComboBox().equals(GtnFrameworkNSFConstants.getFormulaTypeContract())) {
			setComboValue(formulaTypeValue, viewId + GtnFrameworkCommonConstants.FORMULA_TYPE,
					GtnFrameworkNSFConstants.getFormulaTypeContract());
		} else if (size == 3 && ((GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + GtnFrameworkCommonConstants.FORMULA_TYPE).getCaptionFromComboBox()
				.equals(GtnFrameworkNSFConstants.getFormulaTypeDeductionType()))
				|| (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + GtnFrameworkCommonConstants.FORMULA_TYPE)
						.getCaptionFromComboBox().equals(GtnFrameworkNSFConstants.getFormulaTypeContract())))) {
			setComboValue(formulaTypeValue, viewId + GtnFrameworkCommonConstants.FORMULA_TYPE,
					GtnFrameworkNSFConstants.getFormulaTypeContractDeduction());
		} else if ((GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + GtnFrameworkCommonConstants.FORMULA_TYPE)
				.getCaptionFromComboBox().equals(GtnFrameworkNSFConstants.getFormulaTypeContractDeduction())
				|| (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + GtnFrameworkCommonConstants.FORMULA_TYPE)
						.getCaptionFromComboBox().equals(GtnFrameworkNSFConstants.getFormulaTypeContract())))) {
			setComboValue(formulaTypeValue, viewId + GtnFrameworkCommonConstants.FORMULA_TYPE,
					GtnFrameworkNSFConstants.getFormulaTypeDeductionType());
		}

	}

	private void setComboValue(GtnUIFrameworkNsfFormulaType formulaType, String componentId, String string) {
		formulaType.setChangeAllowed(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).loadComboBoxComponentValueWithDes(string);
		formulaType.setFormulaTypeValue(null);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
