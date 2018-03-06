package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkCustomPopupSelectAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkDeleteRSAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkFilterBarInvisibleAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkLoadRuleDetailsAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkNetSaleRulePopupLoadAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkRSItemAdditionFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkRSRebateSetUpTabTableCheckAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkRSTableFieldFactoryFieldUpdateAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkRebateSetupCheckAllAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkRebateSetupMassFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkRsCustomResetAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkAddDataTableAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkCalculationTypeChangeAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkDesignationTypeChangeAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkRSLoadAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkRSSaveAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkRSSearchNoticationAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkSetSelectionConfigAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkTriggerDataTablesAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameworkRSDeleteConfirmationAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameworkRSPopulateAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameworkRSSaveConfirmationAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameworkRSValidationActionIsRecordSelected;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.validation.GtnUIFrameWorkRSSaveMandatoryAlert;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.validation.GtnUIFrameWorkRSSaveRebateSetupTabMandatoryAlert;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.validation.GtnUIFrameworkRsItemAdditionValidationAction;

public class GtnUIFrameworkRSDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCustomPopupSelectAction.class.getName(),
				new GtnFrameworkCustomPopupSelectAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkDeleteRSAction.class.getName(),
				new GtnFrameworkDeleteRSAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkLoadRuleDetailsAction.class.getName(),
				new GtnFrameworkLoadRuleDetailsAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkNetSaleRulePopupLoadAction.class.getName(),
				new GtnFrameworkNetSaleRulePopupLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRebateSetupCheckAllAction.class.getName(),
				new GtnFrameworkRebateSetupCheckAllAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRebateSetupMassFieldValueChangeAction.class.getName(),
				new GtnFrameworkRebateSetupMassFieldValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRsCustomResetAction.class.getName(),
				new GtnFrameworkRsCustomResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRSItemAdditionFieldValueChangeAction.class.getName(),
				new GtnFrameworkRSItemAdditionFieldValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRSRebateSetUpTabTableCheckAction.class.getName(),
				new GtnFrameworkRSRebateSetUpTabTableCheckAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRSTableFieldFactoryFieldUpdateAction.class.getName(),
				new GtnFrameworkRSTableFieldFactoryFieldUpdateAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkAddDataTableAction.class.getName(),
				new GtnUIFrameWorkAddDataTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkCalculationTypeChangeAction.class.getName(),
				new GtnUIFrameWorkCalculationTypeChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkDesignationTypeChangeAction.class.getName(),
				new GtnUIFrameWorkDesignationTypeChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkRSDeleteConfirmationAction.class.getName(),
				new GtnUIFrameworkRSDeleteConfirmationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkRSLoadAction.class.getName(),
				new GtnUIFrameWorkRSLoadAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkRSPopulateAction.class.getName(),
				new GtnUIFrameworkRSPopulateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkRSSaveAction.class.getName(),
				new GtnUIFrameWorkRSSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkRSSaveConfirmationAction.class.getName(),
				new GtnUIFrameworkRSSaveConfirmationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkRSValidationActionIsRecordSelected.class.getName(),
				new GtnUIFrameworkRSValidationActionIsRecordSelected());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkSetSelectionConfigAction.class.getName(),
				new GtnUIFrameWorkSetSelectionConfigAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkTriggerDataTablesAction.class.getName(),
				new GtnUIFrameWorkTriggerDataTablesAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkRsItemAdditionValidationAction.class.getName(),
				new GtnUIFrameworkRsItemAdditionValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkRSSaveMandatoryAlert.class.getName(),
				new GtnUIFrameWorkRSSaveMandatoryAlert());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkRSSaveRebateSetupTabMandatoryAlert.class.getName(),
				new GtnUIFrameWorkRSSaveRebateSetupTabMandatoryAlert());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFilterBarInvisibleAction.class.getName(),
				new GtnFrameworkFilterBarInvisibleAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkRSSearchNoticationAction.class.getName(),
				new GtnUIFrameWorkRSSearchNoticationAction());
                
	}

}
