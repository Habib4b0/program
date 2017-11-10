package com.stpl.gtn.gtn2o.ui.module.rebateplan.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnFrameworkRebatePlanCalculationAddButtonAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnFrameworkRebatePlanDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameWorkAddDataTableAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameWorkFromToTierValueAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameWorkLoadValueComboBox;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameWorkResetOnEditModeAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameWorkResetYesButtonAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameWorkSaveMandatoryAlert;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameWorkTierResultTableItemClickAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameWorkTriggerDataTableAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameworkCustomRemoveAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameworkEnableDisabeRemoveButton;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameworkRPDeleteConfirmationAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameworkRPRemoveConfirmationAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameworkRebatePlanSaveAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameWorkLoadValueFormulaTypeComboBoxAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameworkFromToBlurAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.validation.GtnFrameworkRebatePlanCommonValidationAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.validation.GtnFrameworkRebatePlanEditValidationAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.validation.GtnUIFrameworkRPValidationActionAddMandatoryCheck;

public class GtnUIFrameworkRebatePlanDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRebatePlanCalculationAddButtonAction.class.getName(),
				new GtnFrameworkRebatePlanCalculationAddButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRebatePlanDeleteAction.class.getName(),
				new GtnFrameworkRebatePlanDeleteAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkAddDataTableAction.class.getName(),
				new GtnUIFrameWorkAddDataTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkCustomRemoveAction.class.getName(),
				new GtnUIFrameworkCustomRemoveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkEnableDisabeRemoveButton.class.getName(),
				new GtnUIFrameworkEnableDisabeRemoveButton());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkFromToTierValueAction.class.getName(),
				new GtnUIFrameWorkFromToTierValueAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkLoadValueComboBox.class.getName(),
				new GtnUIFrameWorkLoadValueComboBox());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkRebatePlanSaveAction.class.getName(),
				new GtnUIFrameworkRebatePlanSaveAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkResetOnEditModeAction.class.getName(),
				new GtnUIFrameWorkResetOnEditModeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkResetYesButtonAction.class.getName(),
				new GtnUIFrameWorkResetYesButtonAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkRPDeleteConfirmationAction.class.getName(),
				new GtnUIFrameworkRPDeleteConfirmationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkRPRemoveConfirmationAction.class.getName(),
				new GtnUIFrameworkRPRemoveConfirmationAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkSaveMandatoryAlert.class.getName(),
				new GtnUIFrameWorkSaveMandatoryAlert());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkTierResultTableItemClickAction.class.getName(),
				new GtnUIFrameWorkTierResultTableItemClickAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkTriggerDataTableAction.class.getName(),
				new GtnUIFrameWorkTriggerDataTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRebatePlanCommonValidationAction.class.getName(),
				new GtnFrameworkRebatePlanCommonValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkRPValidationActionAddMandatoryCheck.class.getName(),
				new GtnUIFrameworkRPValidationActionAddMandatoryCheck());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkRebatePlanEditValidationAction.class.getName(),
				new GtnFrameworkRebatePlanEditValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkLoadValueFormulaTypeComboBoxAction.class.getName(),
				new GtnUIFrameWorkLoadValueFormulaTypeComboBoxAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkFromToBlurAction.class.getName(),
				new GtnUIFrameworkFromToBlurAction());

	}

}
