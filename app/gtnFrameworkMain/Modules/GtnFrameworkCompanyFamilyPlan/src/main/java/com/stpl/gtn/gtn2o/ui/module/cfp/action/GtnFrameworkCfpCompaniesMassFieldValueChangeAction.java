package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkCfpCompaniesMassFieldValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpCompaniesMassFieldValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.debug("inside GtnFrameworkCfpCompaniesMassFieldValueChangeAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String cfpCompaniesTabMassFieldValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassField")
				.getStringFromField();
		boolean isDropDown = "CFP Status".equals(cfpCompaniesTabMassFieldValue);
		boolean isSelectOne = (cfpCompaniesTabMassFieldValue == null) || cfpCompaniesTabMassFieldValue.isEmpty();
		List<String> componentIdList = gtnUIFrameWorkActionConfig.getFieldValues();
		GtnUIFrameworkBaseComponent cfpCompaniesMassDateField = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentIdList.get(0));
		GtnUIFrameworkBaseComponent cfpCompaniesmassDropDown = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentIdList.get(1));
		if (isSelectOne) {
			cfpCompaniesMassDateField.setVisible(false);
			cfpCompaniesmassDropDown.setVisible(false);
		} else {
			cfpCompaniesMassDateField.setVisible(!isDropDown);
			cfpCompaniesmassDropDown.setVisible(isDropDown);
		}
		cfpCompaniesmassDropDown.loadComboBoxComponentValue(null);
		cfpCompaniesMassDateField.loadDateValue(null);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
