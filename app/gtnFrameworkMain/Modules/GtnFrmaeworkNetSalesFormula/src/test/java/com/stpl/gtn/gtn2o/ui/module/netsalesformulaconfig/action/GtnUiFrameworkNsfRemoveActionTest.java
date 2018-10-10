package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import static org.junit.Assert.assertNotNull;
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
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;

/**
 * 
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnFrameworkNSFCommonLogic.class, GtnUIFrameworkActionExecutor.class })
public class GtnUiFrameworkNsfRemoveActionTest {

	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnUiFrameworkNsfRemoveAction fixture = new GtnUiFrameworkNsfRemoveAction();
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

		GtnUiFrameworkNsfRemoveAction fixture = new GtnUiFrameworkNsfRemoveAction();
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
	public void testGtnUiFrameworkNsfRemove_doAction_isSalesBasis_isCheck_if() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkNSFCommonLogic.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfRemoveAction fixture = new GtnUiFrameworkNsfRemoveAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		
		// isSalesBasis = true
		actionParemeterList.add(true);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		// isCheck = true
		when(GtnFrameworkNSFCommonLogic.confirmCheckRecord(Mockito.anyBoolean(), Mockito.anyString()))
				.thenReturn(true);

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testGtnUiFrameworkNsfRemove_doAction_isSalesBasis_isCheck_else() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkNSFCommonLogic.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfRemoveAction fixture = new GtnUiFrameworkNsfRemoveAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		
		// isSalesBasis = false
		actionParemeterList.add(false);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		// isCheck = false
		when(GtnFrameworkNSFCommonLogic.confirmCheckRecord(Mockito.anyBoolean(), Mockito.anyString()))
				.thenReturn(false);

		try {
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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