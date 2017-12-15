package com.stpl.gtn.gtn2o.ui.module.itemmaster.dynamicclasses;

import com.stpl.gtn.gtn2o.config.commonpopup.commonaction.GtnUIFrameworkItemClickEnableDisableAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterAddAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterAttachAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterBrandValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterEditAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterEditListAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterEditListPopupCloseAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterFocusListnerAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterIdEditListDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterIdEditListRefreshAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterIdentifierQualifierSaveAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterIdentifirerEditListItemClickAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterItemIdentifierValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterItemTypeAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterManufactureIdValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterNewFormulationAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterPricingAttachAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterPricingFieldFactoryUpdateAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterPricingTableConfigureAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterPricingTempTableClearAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterResetAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnFrameworkItemMasterSaveAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterBaseCPIBlurAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterBaselineAMPBlurAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterCommonValidationAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterEditListIndentifierValidationAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterEditListPricingValidationAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterIdEditListDeleteValidationAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterIndentifierValidationAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterLandingScreenValidationAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterPricingResultsTableValidationAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterPricingValidationAction;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterRefreshPrcingEditListIdentifierAction;

public class GtnUIFrameworkItemMasterDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		addValidationActionClasses();
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterAddAction.class.getName(),
				new GtnFrameworkItemMasterAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterAttachAction.class.getName(),
				new GtnFrameworkItemMasterAttachAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterBrandValueChangeAction.class.getName(),
				new GtnFrameworkItemMasterBrandValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterDeleteAction.class.getName(),
				new GtnFrameworkItemMasterDeleteAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterEditAction.class.getName(),
				new GtnFrameworkItemMasterEditAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterEditListAction.class.getName(),
				new GtnFrameworkItemMasterEditListAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterEditListPopupCloseAction.class.getName(),
				new GtnFrameworkItemMasterEditListPopupCloseAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterIdEditListDeleteAction.class.getName(),
				new GtnFrameworkItemMasterIdEditListDeleteAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkItemMasterIdentifierQualifierSaveAction.class.getName(),
				new GtnFrameworkItemMasterIdentifierQualifierSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkItemMasterIdentifirerEditListItemClickAction.class.getName(),
				new GtnFrameworkItemMasterIdentifirerEditListItemClickAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkItemMasterItemIdentifierValueChangeAction.class.getName(),
				new GtnFrameworkItemMasterItemIdentifierValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkItemMasterManufactureIdValueChangeAction.class.getName(),
				new GtnFrameworkItemMasterManufactureIdValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterResetAction.class.getName(),
				new GtnFrameworkItemMasterResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterSaveAction.class.getName(),
				new GtnFrameworkItemMasterSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterNewFormulationAction.class.getName(),
				new GtnFrameworkItemMasterNewFormulationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterItemTypeAction.class.getName(),
				new GtnFrameworkItemMasterItemTypeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkItemMasterPricingFieldFactoryUpdateAction.class.getName(),
				new GtnFrameworkItemMasterPricingFieldFactoryUpdateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterFocusListnerAction.class.getName(),
				new GtnFrameworkItemMasterFocusListnerAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterPricingAttachAction.class.getName(),
				new GtnFrameworkItemMasterPricingAttachAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterPricingTempTableClearAction.class.getName(),
				new GtnFrameworkItemMasterPricingTempTableClearAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkItemClickEnableDisableAction.class.getName(),
				new GtnUIFrameworkItemClickEnableDisableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterPricingTableConfigureAction.class.getName(),
				new GtnFrameworkItemMasterPricingTableConfigureAction());

	}

	private void addValidationActionClasses() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterCommonValidationAction.class.getName(),
				new GtnFrameworkItemMasterCommonValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkItemMasterEditListIndentifierValidationAction.class.getName(),
				new GtnFrameworkItemMasterEditListIndentifierValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkItemMasterEditListPricingValidationAction.class.getName(),
				new GtnFrameworkItemMasterEditListPricingValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkItemMasterIdEditListDeleteValidationAction.class.getName(),
				new GtnFrameworkItemMasterIdEditListDeleteValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterIndentifierValidationAction.class.getName(),
				new GtnFrameworkItemMasterIndentifierValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkItemMasterLandingScreenValidationAction.class.getName(),
				new GtnFrameworkItemMasterLandingScreenValidationAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkItemMasterPricingResultsTableValidationAction.class.getName(),
				new GtnFrameworkItemMasterPricingResultsTableValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterPricingValidationAction.class.getName(),
				new GtnFrameworkItemMasterPricingValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkItemMasterRefreshPrcingEditListIdentifierAction.class.getName(),
				new GtnFrameworkItemMasterRefreshPrcingEditListIdentifierAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterIdEditListRefreshAction.class.getName(),
				new GtnFrameworkItemMasterIdEditListRefreshAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterBaselineAMPBlurAction.class.getName(),
				new GtnFrameworkItemMasterBaselineAMPBlurAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkItemMasterBaseCPIBlurAction.class.getName(),
				new GtnFrameworkItemMasterBaseCPIBlurAction());

	}

}
