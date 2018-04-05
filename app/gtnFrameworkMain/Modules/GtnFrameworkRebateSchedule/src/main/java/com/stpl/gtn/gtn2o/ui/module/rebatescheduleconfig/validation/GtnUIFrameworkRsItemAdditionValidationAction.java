package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.validation;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public class GtnUIFrameworkRsItemAdditionValidationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		/**
		 * 
		 */

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> paramsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String tableId = (String) paramsList.get(1);
		String message = (String) paramsList.get(2);
		if ("RSItemAdditiongtnSearch01".equals(componentId)) {
			String searchField = (String) paramsList.get(3);
			List<Object> valueFieldIdList = (List<Object>) paramsList.get(4);
			String searchFieldValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(searchField).getStringFromField();
			checkFieldValidation(componentId, valueFieldIdList, searchFieldValue);
			return;
		}

		GtnUIFrameworkBaseComponent table = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId, componentId);

		if (table.getValueFromComponent() == null) {
			throw new GtnFrameworkValidationFailedException(message, componentId);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);

	}

	private void checkFieldValidation(String componentId,List<Object> valueFieldIdList,
			String searchFieldValue) throws GtnFrameworkGeneralException {
		 String message="";
		if ("".equals(searchFieldValue)) {
			message = "Please Select a Search Field to Search";
			throw new GtnFrameworkValidationFailedException(message, componentId);
		}
		for (int i = 0; i < valueFieldIdList.size(); i++) {
			String valueFieldContent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(valueFieldIdList.get(i))).getStringFromField();
			boolean checkFieldIsVisible = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(valueFieldIdList.get(i))).isVisible();
			if (checkFieldIsVisible && "".equals(valueFieldContent)) {
				message = "Please Select or Enter the Value to search";
				throw new GtnFrameworkValidationFailedException(message, componentId);
			}
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
