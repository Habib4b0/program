package com.stpl.gtn.gtn2o.ui.company.action;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkCMIdentifierResetAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig arg0) throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	@Override
	public void doAction(String arg0, GtnUIFrameWorkActionConfig arg1) throws GtnFrameworkGeneralException {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.EDIT_LIST_CODE_QUALIFIER)
				.loadDateValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.EDIT_LIST_CODE_QUALIFIER_NAME)
				.loadDateValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.EDIT_LIST_EFFECTIVE_DATE)
				.loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.EDIT_LIST_NOTES_TEXTAREA)
				.loadDateValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
	}

}
