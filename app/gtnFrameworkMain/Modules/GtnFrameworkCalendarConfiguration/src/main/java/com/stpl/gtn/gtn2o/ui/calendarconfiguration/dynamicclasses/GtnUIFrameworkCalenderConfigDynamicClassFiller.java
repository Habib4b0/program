package com.stpl.gtn.gtn2o.ui.calendarconfiguration.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkAddAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkCalendarCurdCommonValidationAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkCopyAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkCurdHolidaySelectionAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkCurdResetAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkCurdSaveAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkCurdYearChangeAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkDeleteAction;
import com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process.GtnFrameworkProcessAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIDynamicObjectFiller;


public class GtnUIFrameworkCalenderConfigDynamicClassFiller implements GtnUIDynamicObjectFiller{

	@Override
	public void addDynamicObject() {
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkAddAction.class.getName(),new GtnFrameworkAddAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCopyAction.class.getName(),new GtnFrameworkCopyAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCurdHolidaySelectionAction.class.getName(),new GtnFrameworkCurdHolidaySelectionAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCurdResetAction.class.getName(),new GtnFrameworkCurdResetAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCurdSaveAction.class.getName(),new GtnFrameworkCurdSaveAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCurdYearChangeAction.class.getName(),new GtnFrameworkCurdYearChangeAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkCalendarCurdCommonValidationAction.class.getName(),new GtnFrameworkCalendarCurdCommonValidationAction());	
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkDeleteAction.class.getName(),new GtnFrameworkDeleteAction());
		GtnUIFrameworkGlobalUI.addDynamicClassObjects(GtnFrameworkProcessAction.class.getName(),new GtnFrameworkProcessAction());	
		
	}

}
