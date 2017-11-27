package com.stpl.gtn.gtn2o.ui.module.priceschedule.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkCustomTabChangeAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPSDesignationChangeAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPSPriceProtecTabMassFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPSPriceProtectionTabTableCheckAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPSPriceTabMassFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPSPriceTabTableCheckAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPSdefaultValuesetAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPsResetAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameWorkAddDataTableAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameWorkPSLoadAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameWorkPSSaveAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameWorkPSSaveMandatoryAlertAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameWorkPSSavePriceTabMandatoryAlertAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameWorkTableRecordTypeAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameworkPSDeleteConfirmationAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameworkPSPriceProtectionTabPopulateAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameworkPSPriceTabPopulateAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameworkPSSaveConfirmationAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPSPriceProtectionTabAlertAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.validation.GtnFrameworkPSPriceProtectionTabAddLineAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.validation.GtnFramworkItemAdditionSelectValidateAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.validation.GtnUIFrameworkPSPopulateCheckAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.validation.GtnUIFrameworkPSValidationActionIsRecordSelectedAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPPFieldFactoryDynamicComponentAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPricingTabFieldFactoryAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPsFieldFactoryPopupSelectAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFramworkPsPriceProtectionResultsFieldFactoryAction;

public class GtnUIFrameworkPsDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFramworkItemAdditionSelectValidateAction.class.getName(),
				new GtnFramworkItemAdditionSelectValidateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkPSPopulateCheckAction.class.getName(),
				new GtnUIFrameworkPSPopulateCheckAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnUIFrameworkPSValidationActionIsRecordSelectedAction.class.getName(),
				new GtnUIFrameworkPSValidationActionIsRecordSelectedAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCustomTabChangeAction.class.getName(),
				new GtnFrameworkCustomTabChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPSdefaultValuesetAction.class.getName(),
				new GtnFrameworkPSdefaultValuesetAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPSDesignationChangeAction.class.getName(),
				new GtnFrameworkPSDesignationChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkPSPriceProtecTabMassFieldValueChangeAction.class.getName(),
				new GtnFrameworkPSPriceProtecTabMassFieldValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPSPriceProtectionTabTableCheckAction.class.getName(),
				new GtnFrameworkPSPriceProtectionTabTableCheckAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPSPriceTabMassFieldValueChangeAction.class.getName(),
				new GtnFrameworkPSPriceTabMassFieldValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPSPriceTabTableCheckAction.class.getName(),
				new GtnFrameworkPSPriceTabTableCheckAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPsResetAction.class.getName(),
				new GtnFrameworkPsResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkAddDataTableAction.class.getName(),
				new GtnUIFrameWorkAddDataTableAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkPSDeleteConfirmationAction.class.getName(),
				new GtnUIFrameworkPSDeleteConfirmationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkPSLoadAction.class.getName(),
				new GtnUIFrameWorkPSLoadAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkPSPriceProtectionTabPopulateAction.class.getName(),
				new GtnUIFrameworkPSPriceProtectionTabPopulateAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkPSPriceTabPopulateAction.class.getName(),
				new GtnUIFrameworkPSPriceTabPopulateAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkPSSaveAction.class.getName(),
				new GtnUIFrameWorkPSSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkPSSaveConfirmationAction.class.getName(),
				new GtnUIFrameworkPSSaveConfirmationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkPSSaveMandatoryAlertAction.class.getName(),
				new GtnUIFrameWorkPSSaveMandatoryAlertAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkPSSavePriceTabMandatoryAlertAction.class.getName(),
				new GtnUIFrameWorkPSSavePriceTabMandatoryAlertAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFramworkPsPriceProtectionResultsFieldFactoryAction.class.getName(),
				new GtnFramworkPsPriceProtectionResultsFieldFactoryAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPricingTabFieldFactoryAction.class.getName(),
				new GtnFrameworkPricingTabFieldFactoryAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPPFieldFactoryDynamicComponentAction.class.getName(),
				new GtnFrameworkPPFieldFactoryDynamicComponentAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkTableRecordTypeAction.class.getName(),
				new GtnUIFrameWorkTableRecordTypeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPSPriceProtectionTabAddLineAction.class.getName(),
				new GtnFrameworkPSPriceProtectionTabAddLineAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPsFieldFactoryPopupSelectAction.class.getName(),
				new GtnFrameworkPsFieldFactoryPopupSelectAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkPSPriceProtectionTabAlertAction.class.getName(),
				new GtnFrameworkPSPriceProtectionTabAlertAction());
                
	}

}
