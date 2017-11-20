package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkWorkflowInboxDeductionLevelValueAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String forecastdeductionLevel = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.FORECASTDEDUCTIONLEVEL)
				.getCaptionFromComboBox();

		String helperListName;
		switch (forecastdeductionLevel) {
		case GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_CATEGORY:
			helperListName = GtnFrameworkWorkflowInboxClassConstants.RS_CATEGORY;
			break;
		case GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_PROGRAM_TYPE:
			helperListName = GtnFrameworkWorkflowInboxClassConstants.REBATE_PROGRAM_TYPE;
			break;
		case GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_SCHEDULE_TYPE:
			helperListName = GtnFrameworkWorkflowInboxClassConstants.RS_TYPE;
			break;
		default:
			helperListName = GtnFrameworkWorkflowInboxClassConstants.RS_CATEGORY;
			break;
		}

		GtnUIFrameworkComboBoxConfig companyTypeConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.FORECASTDEDUCTIONVALUE)
				.getComponentConfig().getGtnComboboxConfig();
		companyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setComboBoxType(helperListName);

		GtnUIFrameworkComboboxComponent reloadcombobox = new GtnUIFrameworkComboboxComponent();
		reloadcombobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
				GtnFrameworkWorkflowInboxClassConstants.FORECASTDEDUCTIONVALUE, componentId,
				Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY));
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
