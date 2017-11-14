package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkAddToTreeAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkAvaliableNameUpdateAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkConfirmSaveAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkHierarchyChangeAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkRemoveFromTreeAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkResetAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkSaveAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkSearchSecurityAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkVersionChangeAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnUIFrameworkAddButtonAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnUIFrameworkConfirmedDeleteButtonAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnUIFrameworkDeleteButtonAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnUIFrameworkEditButtonAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnUIFrameworkRBRequestAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnUIFrameworkTreeItemClickAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnUIFrameworkViewButtonAction;

public class GtnUIFrameworkRSBuilderDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAddToTreeAction.class.getName(),
				new GtnFrameworkAddToTreeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkConfirmSaveAction.class.getName(),
				new GtnFrameworkConfirmSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkHierarchyChangeAction.class.getName(),
				new GtnFrameworkHierarchyChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRemoveFromTreeAction.class.getName(),
				new GtnFrameworkRemoveFromTreeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkResetAction.class.getName(),
				new GtnFrameworkResetAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkSaveAction.class.getName(),
				new GtnFrameworkSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkSearchSecurityAction.class.getName(),
				new GtnFrameworkSearchSecurityAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkVersionChangeAction.class.getName(),
				new GtnFrameworkVersionChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkAddButtonAction.class.getName(),
				new GtnUIFrameworkAddButtonAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkConfirmedDeleteButtonAction.class.getName(),
				new GtnUIFrameworkConfirmedDeleteButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkDeleteButtonAction.class.getName(),
				new GtnUIFrameworkDeleteButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkEditButtonAction.class.getName(),
				new GtnUIFrameworkEditButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTreeItemClickAction.class.getName(),
				new GtnUIFrameworkTreeItemClickAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkViewButtonAction.class.getName(),
				new GtnUIFrameworkViewButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkRBRequestAction.class.getName(),
				new GtnUIFrameworkRBRequestAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAvaliableNameUpdateAction.class.getName(),
				new GtnFrameworkAvaliableNameUpdateAction());

	}

}
