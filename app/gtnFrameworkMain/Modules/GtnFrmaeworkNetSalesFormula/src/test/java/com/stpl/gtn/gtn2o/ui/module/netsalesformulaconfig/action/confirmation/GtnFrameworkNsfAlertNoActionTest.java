package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;

/**

 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkNsfAlertNoActionTest {
	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testConfigureParams_1()
		throws Exception {
		GtnFrameworkNsfAlertNoAction fixture = new GtnFrameworkNsfAlertNoAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		fixture.configureParams(gtnUIFrameWorkActionConfig);

		// add additional test code here
	}

	/**
	 * Run the GtnUIFrameWorkAction createInstance() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testCreateInstance_1()
		throws Exception {
		GtnFrameworkNsfAlertNoAction fixture = new GtnFrameworkNsfAlertNoAction();

		GtnUIFrameWorkAction result = fixture.createInstance();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the void doAction(String,GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGtnFrameworkNsfAlertNo_doAction_if_1()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkNsfAlertNoAction fixture = new GtnFrameworkNsfAlertNoAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add("view0");
		actionParemeterList.add("view1");
		
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);
		
		
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		  
		Object[] objects = {1, 2, 3, 4, 5};
		
		doReturn(objects).when(gtnUIFrameworkBaseComponent).getTableColumns();
		doReturn("notEquals").when(gtnUIFrameworkBaseComponent).getCaptionFromComboBox();
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkNsfAlertNo_doAction_if_2()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkNsfAlertNoAction fixture = new GtnFrameworkNsfAlertNoAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add("view0");
		actionParemeterList.add("view1");
		
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);
		
		
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		  
		Object[] objects = {1, 2, 3, 4, 5};
		
		doReturn(objects).when(gtnUIFrameworkBaseComponent).getTableColumns();
		doReturn("Contract").when(gtnUIFrameworkBaseComponent).getCaptionFromComboBox();
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkNsfAlertNo_doAction_elseif_1()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkNsfAlertNoAction fixture = new GtnFrameworkNsfAlertNoAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add("view0");
		actionParemeterList.add("view1");
		
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);
		
		
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		  
		Object[] objects = {1, 2, 3};
		
		doReturn(objects).when(gtnUIFrameworkBaseComponent).getTableColumns();
		doReturn("Deduction Type").when(gtnUIFrameworkBaseComponent).getCaptionFromComboBox();
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkNsfAlertNo_doAction_elseif_2()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkNsfAlertNoAction fixture = new GtnFrameworkNsfAlertNoAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add("view0");
		actionParemeterList.add("view1");
		
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);
		
		
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		  
		Object[] objects = {1, 2, 3};
		
		doReturn(objects).when(gtnUIFrameworkBaseComponent).getTableColumns();
		doReturn("Contract").when(gtnUIFrameworkBaseComponent).getCaptionFromComboBox();
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkNsfAlertNo_doAction_elseif_3()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkNsfAlertNoAction fixture = new GtnFrameworkNsfAlertNoAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add("view0");
		actionParemeterList.add("view1");
		
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);
		
		
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		  
		Object[] objects = {1, 2, 3};
		
		doReturn(objects).when(gtnUIFrameworkBaseComponent).getTableColumns();
		doReturn("notEqualsFormulaTypeContract").when(gtnUIFrameworkBaseComponent).getCaptionFromComboBox();
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
		
	@Test
	public void testGtnFrameworkNsfAlertNo_doAction_else_3()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkNsfAlertNoAction fixture = new GtnFrameworkNsfAlertNoAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add("view0");
		actionParemeterList.add("view1");
		
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);
		
		
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		  
		Object[] objects = {1, 2, 3};
		
		doReturn(objects).when(gtnUIFrameworkBaseComponent).getTableColumns();
		doReturn("Contract Deduction").when(gtnUIFrameworkBaseComponent).getCaptionFromComboBox();
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}	

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

}