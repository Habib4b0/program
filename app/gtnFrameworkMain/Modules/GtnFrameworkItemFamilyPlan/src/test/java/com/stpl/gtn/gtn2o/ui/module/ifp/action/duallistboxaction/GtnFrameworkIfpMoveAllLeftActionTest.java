package com.stpl.gtn.gtn2o.ui.module.ifp.action.duallistboxaction;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpValueChangeManager;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkIfpMoveAllLeftActionTest {


	@Test
	public void testConfigureParams()
		throws Exception {
		GtnFrameworkIfpMoveAllLeftAction fixture = new GtnFrameworkIfpMoveAllLeftAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);
	}

	
	@Test
	public void testCreateInstance()
		throws Exception {
		
		GtnFrameworkIfpMoveAllLeftAction fixture = new GtnFrameworkIfpMoveAllLeftAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testDoAction()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpMoveAllLeftAction fixture = new GtnFrameworkIfpMoveAllLeftAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn(5).when(baseComponent).getExtPagedTableSize();
		
		ExtPagedTable extPagedTable = Mockito.mock(ExtPagedTable.class);
		doReturn(extPagedTable).when(baseComponent).getExtPagedTable();
		
		GtnUIFrameworkPagedTableLogic gtnUIFrameworkPagedTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(gtnUIFrameworkPagedTableLogic).when(baseComponent).getLogicFromPagedDataTable();
		
		
		callMoveAllLeftDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}

	public void callMoveAllLeftDoAction(GtnFrameworkIfpMoveAllLeftAction fixture, String componentId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
		
		try{
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		}catch(Exception e)
		{
			System.out.println();e.getMessage();
		}
	}
	
	@Test
	public void testDoAction_getExtPagedTableSize_isZero()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpMoveAllLeftAction fixture = new GtnFrameworkIfpMoveAllLeftAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn(0).when(baseComponent).getExtPagedTableSize();
		
		callMoveAllLeftDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}
	
}