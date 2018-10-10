package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

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
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class })
public class GtnUiFrameworkNsfContractSelectionActionTest {

	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnUiFrameworkNsfContractSelectionAction fixture = new GtnUiFrameworkNsfContractSelectionAction();
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

		GtnUiFrameworkNsfContractSelectionAction fixture = new GtnUiFrameworkNsfContractSelectionAction();
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
	public void testGtnFrameworkNsfAdd_doAction_if() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfContractSelectionAction fixture = new GtnUiFrameworkNsfContractSelectionAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("Existing Contract").when(baseComponent).getStringFromField();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnFrameworkNsfAdd_doAction_else() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfContractSelectionAction fixture = new GtnUiFrameworkNsfContractSelectionAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("Not Existing Contract -Else Block").when(baseComponent).getStringFromField();

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