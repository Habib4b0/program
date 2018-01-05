package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnFrameworkLoadRuleDetailsAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnFrameworkNsfDeductionTabCheckAllAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUIFrameworkTableSearchCompletionAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkEnableDisableAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfContractSelectionAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfCopyAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfDeductionMassFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfDeductionPopulateAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfDeductionsTabAddAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfEditAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfFormulaTypeChangeAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfLoadDataAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfRemoveAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfSBMassFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfSBPopulateAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfSalesBasisAddAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfSalesBasisDisplayAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfSaveAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfTabTableResetAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkSetSelectionAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation.GtnUiFrameworkNsfDeleteConfirmationAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation.GtnUiFrameworkNsfSaveConfirmationAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation.GtnUiFrameworkNsfTabTableResetConfirmationAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.fieldfactory.GtnUIFrameWorkNsfNetSalesRulePopupSelectAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.fieldfactory.GtnUiFrameworkNsfDeductionsTabFieldFactoryAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.fieldfactory.GtnUiFrameworkNsfSalesBasisTabFieldFactoryAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.validation.GtnUiFrameworkNsfPopulateValidationAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.validation.GtnUiFrameworkNsfRuleSaveUniqueValidationAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.validation.GtnUiFrameworkNsfSalesBasisPopulateValidationAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.validation.GtnUiFrameworkNsfSaveValidationAction;

public class GtnUIFrameworkNsfDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfDeleteConfirmationAction.class.getName(),
				new GtnUiFrameworkNsfDeleteConfirmationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfSaveConfirmationAction.class.getName(),
				new GtnUiFrameworkNsfSaveConfirmationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfDeductionsTabFieldFactoryAction.class.getName(),
				new GtnUiFrameworkNsfDeductionsTabFieldFactoryAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfSalesBasisTabFieldFactoryAction.class.getName(),
				new GtnUiFrameworkNsfSalesBasisTabFieldFactoryAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfSaveValidationAction.class.getName(),
				new GtnUiFrameworkNsfSaveValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkEnableDisableAction.class.getName(),
				new GtnUiFrameworkEnableDisableAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfContractSelectionAction.class.getName(),
				new GtnUiFrameworkNsfContractSelectionAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfCopyAction.class.getName(),
				new GtnUiFrameworkNsfCopyAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfDeductionsTabAddAction.class.getName(),
				new GtnUiFrameworkNsfDeductionsTabAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfDeleteAction.class.getName(),
				new GtnUiFrameworkNsfDeleteAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfEditAction.class.getName(),
				new GtnUiFrameworkNsfEditAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkTableSearchCompletionAction.class.getName(),
				new GtnUIFrameworkTableSearchCompletionAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfFormulaTypeChangeAction.class.getName(),
				new GtnUiFrameworkNsfFormulaTypeChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfLoadDataAction.class.getName(),
				new GtnUiFrameworkNsfLoadDataAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfSalesBasisAddAction.class.getName(),
				new GtnUiFrameworkNsfSalesBasisAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfSalesBasisDisplayAction.class.getName(),
				new GtnUiFrameworkNsfSalesBasisDisplayAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfSaveAction.class.getName(),
				new GtnUiFrameworkNsfSaveAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkSetSelectionAction.class.getName(),
				new GtnUiFrameworkSetSelectionAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkNsfNetSalesRulePopupSelectAction.class.getName(),
				new GtnUIFrameWorkNsfNetSalesRulePopupSelectAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfSBMassFieldValueChangeAction.class.getName(),
				new GtnUiFrameworkNsfSBMassFieldValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfSBPopulateAction.class.getName(),
				new GtnUiFrameworkNsfSBPopulateAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfPopulateValidationAction.class.getName(),
				new GtnUiFrameworkNsfPopulateValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnUiFrameworkNsfDeductionMassFieldValueChangeAction.class.getName(),
				new GtnUiFrameworkNsfDeductionMassFieldValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfRemoveAction.class.getName(),
				new GtnUiFrameworkNsfRemoveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfSalesBasisPopulateValidationAction.class.getName(),
				new GtnUiFrameworkNsfSalesBasisPopulateValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfDeductionPopulateAction.class.getName(),
				new GtnUiFrameworkNsfDeductionPopulateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfTabTableResetAction.class.getName(),
				new GtnUiFrameworkNsfTabTableResetAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfTabTableResetConfirmationAction.class.getName(),
				new GtnUiFrameworkNsfTabTableResetConfirmationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkLoadRuleDetailsAction.class.getName(),
				new GtnFrameworkLoadRuleDetailsAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkNsfDeductionTabCheckAllAction.class.getName(),
				new GtnFrameworkNsfDeductionTabCheckAllAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUiFrameworkNsfRuleSaveUniqueValidationAction.class.getName(),
				new GtnUiFrameworkNsfRuleSaveUniqueValidationAction());

	}

}
