package com.stpl.gtn.gtn2o.ui.company.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.company.action.GtnFrameWorkCMEditCustomAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnFrameWorkCMParentCompanyMandatoryAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnFrameWorkCMViewAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnFrameworkCMIdentifierResetAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnFrameworkCompanyMasterEditListPopupCloseAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnFrameworkCompanyMasterIdEditListDeleteAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnFrameworkCompanyMasterIdentifierEditListItemClickAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnFrameworkCompanyMasterIdentifierQualifierSaveAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnFrameworkCompanyMasterQualifierValueChangeAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameWorkCMFinancialCloseModeAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameWorkCompTypeValueChangeAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameWorkCompanyMasterBackCustomAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameWorkCompanyMasterFinancialResetAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameWorkCompanyMasterLoadAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameWorkCompanyMasterParentCompanyPopupSelectAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameWorkFinancialCloseCustomAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameworkCompanyMasterAddResetAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameworkCompanyMasterAttachAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameworkCompanyMasterDeleteAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameworkCompanyMasterResetAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameworkCompanyMasterSaveAction;
import com.stpl.gtn.gtn2o.ui.company.action.GtnUIFrameworkIdentifierEditListAction;
import com.stpl.gtn.gtn2o.ui.company.action.validation.GtnFrameworkCMCommonValidationAction;
import com.stpl.gtn.gtn2o.ui.company.action.validation.GtnFrameworkCMIdEditListDeleteValidationAction;
import com.stpl.gtn.gtn2o.ui.company.action.validation.GtnFrameworkCompanyMasterIdentifierResultsTableValidationAction;
import com.stpl.gtn.gtn2o.ui.company.action.validation.GtnFrameworkCompanyMasterIndentifierValidationAction;
import com.stpl.gtn.gtn2o.ui.company.action.validation.GtnFrameworkCompanyMasterTradeClassParentTabValidation;
import com.stpl.gtn.gtn2o.ui.company.action.validation.GtnFrameworkFinancialCloseValidationAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;

public class GtnUIFrameworkCdrDynamicClassFiller implements GtnUIDynamicObjectFiller {

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCMCommonValidationAction.class.getName(),
				new GtnFrameworkCMCommonValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCMIdEditListDeleteValidationAction.class.getName(),
				new GtnFrameworkCMIdEditListDeleteValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkCompanyMasterIdentifierResultsTableValidationAction.class.getName(),
				new GtnFrameworkCompanyMasterIdentifierResultsTableValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkCompanyMasterIndentifierValidationAction.class.getName(),
				new GtnFrameworkCompanyMasterIndentifierValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkCompanyMasterTradeClassParentTabValidation.class.getName(),
				new GtnFrameworkCompanyMasterTradeClassParentTabValidation());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkFinancialCloseValidationAction.class.getName(),
				new GtnFrameworkFinancialCloseValidationAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameWorkCMEditCustomAction.class.getName(),
				new GtnFrameWorkCMEditCustomAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameWorkCMParentCompanyMandatoryAction.class.getName(),
				new GtnFrameWorkCMParentCompanyMandatoryAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameWorkCMViewAction.class.getName(),
				new GtnFrameWorkCMViewAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCompanyMasterEditListPopupCloseAction.class.getName(),
				new GtnFrameworkCompanyMasterEditListPopupCloseAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCompanyMasterIdEditListDeleteAction.class.getName(),
				new GtnFrameworkCompanyMasterIdEditListDeleteAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCMIdentifierResetAction.class.getName(),
				new GtnFrameworkCMIdentifierResetAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkCompanyMasterIdentifierEditListItemClickAction.class.getName(),
				new GtnFrameworkCompanyMasterIdentifierEditListItemClickAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkCompanyMasterIdentifierQualifierSaveAction.class.getName(),
				new GtnFrameworkCompanyMasterIdentifierQualifierSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnFrameworkCompanyMasterQualifierValueChangeAction.class.getName(),
				new GtnFrameworkCompanyMasterQualifierValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkCMFinancialCloseModeAction.class.getName(),
				new GtnUIFrameWorkCMFinancialCloseModeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkCompanyMasterAttachAction.class.getName(),
				new GtnUIFrameworkCompanyMasterAttachAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkCompanyMasterAddResetAction.class.getName(),
				new GtnUIFrameworkCompanyMasterAddResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkCompanyMasterBackCustomAction.class.getName(),
				new GtnUIFrameWorkCompanyMasterBackCustomAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkCompanyMasterDeleteAction.class.getName(),
				new GtnUIFrameworkCompanyMasterDeleteAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkCompanyMasterFinancialResetAction.class.getName(),
				new GtnUIFrameWorkCompanyMasterFinancialResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkCompanyMasterLoadAction.class.getName(),
				new GtnUIFrameWorkCompanyMasterLoadAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(
				GtnUIFrameWorkCompanyMasterParentCompanyPopupSelectAction.class.getName(),
				new GtnUIFrameWorkCompanyMasterParentCompanyPopupSelectAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkCompanyMasterResetAction.class.getName(),
				new GtnUIFrameworkCompanyMasterResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkCompanyMasterSaveAction.class.getName(),
				new GtnUIFrameworkCompanyMasterSaveAction());

		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkCompTypeValueChangeAction.class.getName(),
				new GtnUIFrameWorkCompTypeValueChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameWorkFinancialCloseCustomAction.class.getName(),
				new GtnUIFrameWorkFinancialCloseCustomAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnUIFrameworkIdentifierEditListAction.class.getName(),
				new GtnUIFrameworkIdentifierEditListAction());

	}

}
