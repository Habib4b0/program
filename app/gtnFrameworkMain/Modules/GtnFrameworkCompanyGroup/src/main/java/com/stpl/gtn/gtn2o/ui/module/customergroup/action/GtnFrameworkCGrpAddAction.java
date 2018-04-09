package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkCGrpAddAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCGrpAddAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", Boolean.TRUE);
			setValueToComponents(componentId);
		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(String sourceComponentId) throws GtnFrameworkValidationFailedException {
		gtnLogger.debug("Initialising components of CGroup");
		GtnCompanyGrpInformationBean info = new GtnCompanyGrpInformationBean();

		GtnUIFrameworkBaseComponent cGrpInformationCGrpName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("cGrpInformationCGrpName", sourceComponentId);
		GtnUIFrameworkBaseComponent cGrpInformationCGrpNo = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("cGrpInformationCGrpNo", sourceComponentId);
		GtnUIFrameworkBaseComponent cGrpInformationCGrpDesc = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("cGrpInformationCGrpDesc", sourceComponentId);

		cGrpInformationCGrpName.loadFieldValue(info.getCompanyGrpName());
		cGrpInformationCGrpNo.loadFieldValue(info.getCompanyGrpNo());
		cGrpInformationCGrpDesc.loadFieldValue(info.getCompanyGrpDesc());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCustomerNo", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabTradeClass", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCustomerStatus", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabState", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCustomerName", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCustomerType", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabCity", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpInformationTabZipcode", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);

		GtnUIFrameworkGlobalUI.addSessionProperty("companyGrpSid", 0);
		GtnUIFrameworkGlobalUI.addSessionProperty("versionId", 1);

		cGrpInformationCGrpName.setComponentEnable(true);
		cGrpInformationCGrpNo.setComponentEnable(true);
		cGrpInformationCGrpDesc.setComponentEnable(true);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpCustomerSearchPanel", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpsearchButtonlayout", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpAvailableResultPanel", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("availableTableActionButtonlayout", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpSelectedTableActionButtonlayout", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpSelectedTablegtnRemove01layout", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpSelectedTablegtnRemoveAll01layout", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpAddSaveButton", sourceComponentId).setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CGrpADDResetButton", sourceComponentId)
				.setComponentVisible(true);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpAddSaveButton").setCaption("SAVE");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpAvailablesearchResultTable").getExtPagedTable()
				.removeAllItems();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cGrpSelectedResultTable").getExtPagedTable().removeAllItems();
	}
}
