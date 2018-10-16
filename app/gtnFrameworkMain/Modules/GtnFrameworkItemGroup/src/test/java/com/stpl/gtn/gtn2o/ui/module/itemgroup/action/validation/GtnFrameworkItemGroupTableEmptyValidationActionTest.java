package com.stpl.gtn.gtn2o.ui.module.itemgroup.action.validation;

import static org.junit.Assert.assertNotNull;

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

import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkItemGroupTableEmptyValidationActionTest {
	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnFrameworkItemGroupTableEmptyValidationAction fixture = new GtnFrameworkItemGroupTableEmptyValidationAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);

	}

	/**
	 * Run the GtnUIFrameWorkAction createInstance() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testCreateInstance() throws Exception {

		GtnFrameworkItemGroupTableEmptyValidationAction fixture = new GtnFrameworkItemGroupTableEmptyValidationAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	/**
	 * Run the void doAction(String,GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGtnFrameworkItemGroupTableEmptyValidation_doAction_getCount_true() throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkItemGroupTableEmptyValidationAction fixture = new GtnFrameworkItemGroupTableEmptyValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParamList = new ArrayList<Object>();
		actionParamList.add(0);
		actionParamList.add("searchTableId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParamList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(baseComponent);

		GtnUIFrameworkPagedTableLogic itemGrpSearchTablelogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(itemGrpSearchTablelogic).when(baseComponent).getLogicFromPagedDataTable();
		
		// (itemGrpSearchTablelogic.getCount() > 0) = true
		doReturn(5).when(itemGrpSearchTablelogic).getCount();
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkItemGroupTableEmptyValidation_doAction_getCount_false() throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkItemGroupTableEmptyValidationAction fixture = new GtnFrameworkItemGroupTableEmptyValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParamList = new ArrayList<Object>();
		actionParamList.add(0);
		actionParamList.add("searchTableId");
		actionParamList.add("2");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParamList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(baseComponent);

		GtnUIFrameworkPagedTableLogic itemGrpSearchTablelogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(itemGrpSearchTablelogic).when(baseComponent).getLogicFromPagedDataTable();
		
		// (itemGrpSearchTablelogic.getCount() > 0) = false
		doReturn(0).when(itemGrpSearchTablelogic).getCount();
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *             if the initialization fails for some reason
	 *
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *             if the clean-up fails for some reason
	 *
	 * 
	 */
	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here
	}

}