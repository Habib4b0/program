package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
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
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkCGrpAddButtonActionTest {

	@Test
	public void testGtnFrameworkCGrpAddButtonAction_1()
		throws Exception {
		GtnFrameworkCGrpAddButtonAction result = new GtnFrameworkCGrpAddButtonAction();
		assertNotNull(result);
		// add additional test code here
	}


	@Test
	public void testConfigureParams_1()
		throws Exception {
		GtnFrameworkCGrpAddButtonAction fixture = new GtnFrameworkCGrpAddButtonAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		fixture.configureParams(gtnUIFrameWorkActionConfig);

		// add additional test code here
	}


	@Test
	public void testCreateInstance_1()
		throws Exception {
		GtnFrameworkCGrpAddButtonAction fixture = new GtnFrameworkCGrpAddButtonAction();

		GtnUIFrameWorkAction result = fixture.createInstance();

		// add additional test code here
		assertNotNull(result);
	}


	@Test
	public void testDoAction_1()
		throws Exception {
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCGrpAddButtonAction fixture = new GtnFrameworkCGrpAddButtonAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, gtnUIFrameWorkActionConfig);

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);  

		// add additional test code here
	}
	
	@Test
	public void testDoAction_2()
		throws Exception {
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCGrpAddButtonAction fixture = new GtnFrameworkCGrpAddButtonAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnWsRecordBean gtnWsRecordBean = new GtnWsRecordBean();
		gtnWsRecordBean.setPropertyValueByIndex(27, "1");
		gtnWsRecordBean.setPropertyValueByIndex(28, "1");
		gtnWsRecordBean.setPropertyValueByIndex(29, "1");
		gtnWsRecordBean.setPropertyValueByIndex(30, "1");

		
		Set<GtnWsRecordBean> dtoSet = new HashSet<GtnWsRecordBean>();
		dtoSet.add(gtnWsRecordBean);  
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn(dtoSet).when(baseComponent).getValuesFromPagedDataTable();


	//	GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, gtnUIFrameWorkActionConfig);

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

		// add additional test code here
	}


	/*@Test
	public void testDoAction_2()
		throws Exception {
		GtnFrameworkCGrpAddButtonAction fixture = new GtnFrameworkCGrpAddButtonAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

		// add additional test code here
	}*/

	
	/*@Test
	public void testSetValueToComponents_1()
		throws Exception {
		GtnFrameworkCGrpAddButtonAction fixture = new GtnFrameworkCGrpAddButtonAction();

		fixture.setValueToComponents();

		// add additional test code here
	}*/

	
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnFrameworkCGrpAddButtonActionTest.class);
	}
}