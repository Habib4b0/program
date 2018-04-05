package com.stpl.gtn.gtn2o.ui.module.itemgroup.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.vaadin.ui.Component;
import org.asi.ui.extfilteringtable.ExtCustomTable;

public class GtnFrameworkItemGrpAddAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", Boolean.TRUE);
		
		setValueToComponents(componentId);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(String sourceComponentId) throws GtnFrameworkValidationFailedException {

		GtnUIFrameworkBaseComponent itemGrpInformationItemGrpName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpInformationItemGrpName", sourceComponentId);
		GtnUIFrameworkBaseComponent itemGrpInformationItemGrpNo = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpInformationItemGrpNo", sourceComponentId);
		GtnUIFrameworkBaseComponent itemGrpInformationItemGrpDesc = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpInformationItemGrpDesc", sourceComponentId);
		GtnUIFrameworkBaseComponent itemGrpInformationItemGrpCompany = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpInformationItemGrpCompany", sourceComponentId);

		itemGrpInformationItemGrpName.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		itemGrpInformationItemGrpNo.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		itemGrpInformationItemGrpDesc.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		itemGrpInformationItemGrpCompany.loadFieldValue(null);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabCustomerType", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationIGrpDesc", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabCustomerNo", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabBrand", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabStrength", sourceComponentId)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabTherapeuticClass", sourceComponentId)
				.loadFieldValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("iGrpInformationTabForm", sourceComponentId).loadFieldValue(null);

		itemGrpInformationItemGrpName.setComponentEnable(true);
		itemGrpInformationItemGrpNo.setComponentEnable(true);
		itemGrpInformationItemGrpDesc.setComponentEnable(true);
		itemGrpInformationItemGrpCompany.setComponentEnable(true);

		GtnUIFrameworkGlobalUI.addSessionProperty("itemGroupSid", 0);
		GtnUIFrameworkGlobalUI.addSessionProperty("versionId", 1);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpCustomerSearchPanel", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpsearchButtonlayout", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpAvailableResultPanel", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("availableTableActionButtonlayout", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpSelectedTableActionButtonlayout", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpSelectedTablegtnRemove01layout", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpSelectedTablegtnRemoveAll01layout", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpAddSaveButton", sourceComponentId)
				.setComponentVisible(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ItemGrpADDResetButton", sourceComponentId)
				.setComponentVisible(true);

		Component component = GtnUIFrameworkGlobalUI.getVaadinComponent("itemGrpAddSaveButton");
		component.setCaption("SAVE");

		ExtCustomTable table = (ExtCustomTable) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpAvailablesearchResultTable").getExtPagedTable();
		table.removeAllItems();
		ExtCustomTable selectedTable = (ExtCustomTable) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpSelectedResultTable").getExtPagedTable();
		selectedTable.removeAllItems();

	}
}
