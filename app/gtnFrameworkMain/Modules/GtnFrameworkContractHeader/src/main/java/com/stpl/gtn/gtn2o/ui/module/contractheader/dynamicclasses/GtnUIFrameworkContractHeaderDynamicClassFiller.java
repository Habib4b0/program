package com.stpl.gtn.gtn2o.ui.module.contractheader.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderAddAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderEditAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderPopupTableItemClickAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderResetAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderSaveAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderAliasTabRemoveAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderAttachAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.validation.GtnUIFrameworkContractHeaderAliasAttachValidationAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.validation.GtnUIFrameworkContractHeaderCommonValidationAction;

public class GtnUIFrameworkContractHeaderDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnUIFrameworkContractHeaderAliasAttachValidationAction.class.getName(),
				new GtnUIFrameworkContractHeaderAliasAttachValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnUIFrameworkContractHeaderCommonValidationAction.class.getName(),
				new GtnUIFrameworkContractHeaderCommonValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkContractHeaderAddAction.class.getName(),
				new GtnUIFrameworkContractHeaderAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkContractHeaderDeleteAction.class.getName(),
				new GtnUIFrameworkContractHeaderDeleteAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkContractHeaderEditAction.class.getName(),
				new GtnUIFrameworkContractHeaderEditAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnUIFrameworkContractHeaderPopupTableItemClickAction.class.getName(),
				new GtnUIFrameworkContractHeaderPopupTableItemClickAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkContractHeaderResetAction.class.getName(),
				new GtnUIFrameworkContractHeaderResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkContractHeaderSaveAction.class.getName(),
				new GtnUIFrameworkContractHeaderSaveAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkContractHeaderAliasTabRemoveAction.class.getName(),
				new GtnUIFrameworkContractHeaderAliasTabRemoveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkContractHeaderAttachAction.class.getName(),
				new GtnUIFrameworkContractHeaderAttachAction());

	}

}
