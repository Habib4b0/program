package com.stpl.gtn.gtn2o.ui.module.cfp.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpAddAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpCompaniesDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpCompaniesMassFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpCompaniesRecordChangeAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpCompaniesTabPopulateAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpCompaniesTabTableCheckAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpCompanyAdditionSearchDdlbValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpCompanyAdditionSearchValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpEditAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpInfoDesignationValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpResetAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpSaveAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpTabChangeAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpTableFieldFactory;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkParentCfpPopupTableItemClickAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.duallistboxaction.GtnFrameworkCfpMoveAllLeftAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.duallistboxaction.GtnFrameworkCfpMoveAllRightAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.duallistboxaction.GtnFrameworkCfpMoveLeftAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.duallistboxaction.GtnFrameworkCfpMoveRightAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.validation.GtnFrameworkCfpCommonValidationAction;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.validation.GtnFrameworkCfpIdAndNoValidationAction;

public class GtnUIFrameworkCfpDynamicClassFiller implements GtnUIDynamicObjectFiller{

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpMoveAllLeftAction.class.getName(),new GtnFrameworkCfpMoveAllLeftAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpMoveAllRightAction.class.getName(),new GtnFrameworkCfpMoveAllRightAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpMoveLeftAction.class.getName(),new GtnFrameworkCfpMoveLeftAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpMoveRightAction.class.getName(),new GtnFrameworkCfpMoveRightAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpCommonValidationAction.class.getName(),new GtnFrameworkCfpCommonValidationAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpIdAndNoValidationAction.class.getName(),new GtnFrameworkCfpIdAndNoValidationAction());
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpAddAction.class.getName(),new GtnFrameworkCfpAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpCompaniesDeleteAction.class.getName(),new GtnFrameworkCfpCompaniesDeleteAction());	
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpCompaniesMassFieldValueChangeAction.class.getName(),new GtnFrameworkCfpCompaniesMassFieldValueChangeAction());	
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpCompaniesRecordChangeAction.class.getName(),new GtnFrameworkCfpCompaniesRecordChangeAction());	
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpCompaniesTabPopulateAction.class.getName(),new GtnFrameworkCfpCompaniesTabPopulateAction());	
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpCompaniesTabTableCheckAction.class.getName(),new GtnFrameworkCfpCompaniesTabTableCheckAction());	
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpCompanyAdditionSearchDdlbValueChangeAction.class.getName(),new GtnFrameworkCfpCompanyAdditionSearchDdlbValueChangeAction());	
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpCompanyAdditionSearchValueChangeAction.class.getName(),new GtnFrameworkCfpCompanyAdditionSearchValueChangeAction());	
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpDeleteAction.class.getName(),new GtnFrameworkCfpDeleteAction());	
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpEditAction.class.getName(),new GtnFrameworkCfpEditAction());
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpInfoDesignationValueChangeAction.class.getName(),new GtnFrameworkCfpInfoDesignationValueChangeAction());	
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpResetAction.class.getName(),new GtnFrameworkCfpResetAction());	
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpSaveAction.class.getName(),new GtnFrameworkCfpSaveAction());	
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpTabChangeAction.class.getName(),new GtnFrameworkCfpTabChangeAction());	
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCfpTableFieldFactory.class.getName(),new GtnFrameworkCfpTableFieldFactory());	
		
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkParentCfpPopupTableItemClickAction.class.getName(),new GtnFrameworkParentCfpPopupTableItemClickAction());	
		
	}

}
