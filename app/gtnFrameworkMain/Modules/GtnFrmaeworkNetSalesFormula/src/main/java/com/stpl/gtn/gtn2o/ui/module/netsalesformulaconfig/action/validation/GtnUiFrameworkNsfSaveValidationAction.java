/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import java.util.List;

/**
 *
 * @author STPL
 */
public class GtnUiFrameworkNsfSaveValidationAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(final String componentId, final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		String subMessage = validate(viewId);

		if (!"".equals(subMessage)) {
			String msg = "Information for the following Mandatory fields need to be provided:" + " <br> " + subMessage
					+ "  ";

			throw new GtnFrameworkValidationFailedException(msg, componentId);

		}

	}

	private String validate(String viewId) throws GtnFrameworkValidationFailedException {
		StringBuilder subMessage = new StringBuilder();

		GtnUIFrameworkBaseComponent selectedDeductionResultTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + "selectedDeductionsResultTable");
                
               subMessage= GtnUIFrameworkGlobalUI.validateFields(new String[]{viewId + "formulaId",viewId + "formulaNo",viewId + "formulaName",viewId + "formulaType"}, subMessage);
		
		if (selectedDeductionResultTable.getExtPagedTableSize() == 0) {
			if (!subMessage.toString().isEmpty()) {
				subMessage.append(',');
			}
			subMessage.append( "Select at least one Deduction in Deductions tab.");
		}
		subMessage.append(getIndicatorError(selectedDeductionResultTable, subMessage));

		return subMessage.toString();
	}

	private String getIndicatorError(GtnUIFrameworkBaseComponent selectedDeductionResultTable, StringBuilder subMessage)
			throws GtnFrameworkValidationFailedException {
		String msg = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		if (!selectedDeductionResultTable.getItemsFromDataTable().isEmpty() && subMessage.toString().isEmpty()) {
			for (GtnWsRecordBean bean : selectedDeductionResultTable.getItemsFromDataTable()) {
				if (bean.getPropertyValue("indicator").toString().isEmpty()) {
					msg = " Select +/- Indicator for selected deductions in Deductions tab.";
					break;
				}

			}
		}
		return msg;
	}

	private String getValueFromField(final String propertyId) throws GtnFrameworkValidationFailedException {

		return String.valueOf(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(propertyId).getValueFromComponent());

	}

	private boolean checkCondition(final String value) {
		return "null".equals(value) || "".equals(value) || value == null;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
