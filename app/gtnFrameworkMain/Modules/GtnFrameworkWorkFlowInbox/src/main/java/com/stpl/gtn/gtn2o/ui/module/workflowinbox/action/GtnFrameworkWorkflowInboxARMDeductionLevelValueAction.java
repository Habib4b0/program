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
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkWorkflowInboxARMDeductionLevelValueAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkWorkflowInboxARMDeductionLevelValueAction.class);
	
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Inside GtnFrameworkWorkflowInboxARMDeductionLevelValueAction --> doAction..start");
		String deductionLevelArm = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONLEVELARM)
				.getCaptionFromComboBox();

		String helperListName;
		switch (deductionLevelArm) {
		case GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_CATEGORY:
			helperListName = GtnFrameworkWorkflowInboxClassConstants.RS_CATEGORY;
			break;
		case GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_PROGRAM:
			helperListName = GtnFrameworkWorkflowInboxClassConstants.REBATE_PROGRAM_TYPE;
			break;
		case GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_TYPE:
			helperListName = GtnFrameworkWorkflowInboxClassConstants.RS_TYPE;
			break;
		case GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_CATEGORY_2:
			helperListName = GtnFrameworkWorkflowInboxClassConstants.RS_UDC2;
			break;
		case GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_CATEGORY_3:
			helperListName = GtnFrameworkWorkflowInboxClassConstants.RS_UDC3;
			break;
		case GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_CATEGORY_4:
			helperListName = GtnFrameworkWorkflowInboxClassConstants.RS_UDC4;
			break;
		case GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_CATEGORY_5:
			helperListName = GtnFrameworkWorkflowInboxClassConstants.RS_UDC5;
			break;
		case GtnFrameworkWorkflowInboxClassConstants.DEDUCTION_CATEGORY_6:
			helperListName = GtnFrameworkWorkflowInboxClassConstants.RS_UDC6;
			break;
		case GtnFrameworkWorkflowInboxClassConstants.DEDUCTION:
			helperListName = GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONVALUE_ARMDDLB;
			break;
		default:
			helperListName = GtnFrameworkWorkflowInboxClassConstants.EMPTY;
			break;
		}
		
		//logger.info(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPE).getStringFromField());
		
		GtnUIFrameworkComboBoxConfig companyTypeConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONVALUEARM).getComponentConfig()
				.getGtnComboboxConfig();
		companyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setComboBoxType(helperListName);

		GtnUIFrameworkComboboxComponent reloadcombobox = new GtnUIFrameworkComboboxComponent();
		reloadcombobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONVALUEARM, componentId,
				Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY));
	
		logger.debug("Inside GtnFrameworkWorkflowInboxARMDeductionLevelValueAction --> doAction..end");
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
