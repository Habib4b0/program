package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkIfpCompaniesMassFieldValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpCompaniesMassFieldValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkIfpCompaniesMassFieldValueChangeAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String ifpCompaniesTabMassFieldValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassField")
				.getStringFromField();
		boolean isDropDown = "IFP Status".equals(ifpCompaniesTabMassFieldValue);
		boolean isSelectOne = (ifpCompaniesTabMassFieldValue == null) || ifpCompaniesTabMassFieldValue.isEmpty();
		List<String> componentIdList = gtnUIFrameWorkActionConfig.getFieldValues();
		GtnUIFrameworkBaseComponent ifpCompaniesMassDateField = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentIdList.get(0));
		GtnUIFrameworkBaseComponent ifpCompaniesmassDropDown = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentIdList.get(1)); 
		if (isSelectOne) { 
			ifpCompaniesMassDateField.setVisible(false);
			ifpCompaniesmassDropDown.setVisible(false);
		} else {
			ifpCompaniesMassDateField.setVisible(!isDropDown);
			ifpCompaniesmassDropDown.setVisible(isDropDown);
		}
		ifpCompaniesmassDropDown.loadComboBoxComponentValue(null);
		ifpCompaniesMassDateField.loadDateValue(null);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
