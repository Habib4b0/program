package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})

public class GtnFrameworkCfpCompaniesRecordChangeActionTest {

	
	GtnFrameworkCfpCompaniesRecordChangeAction instance = new GtnFrameworkCfpCompaniesRecordChangeAction();
	
	@Test
	public void test() throws GtnFrameworkGeneralException {
		
		String componentId ="";
		 
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig =new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.addActionParameter(0);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabResultDataTable")).thenReturn(base);

		GtnUIFrameworkPagedTableLogic logic=Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(logic).when(base).getLogicFromPagedDataTable();
		instance.doAction(componentId, gtnUIFrameWorkActionConfig);
		instance.configureParams(gtnUIFrameWorkActionConfig);
		instance.createInstance();
	}

}
