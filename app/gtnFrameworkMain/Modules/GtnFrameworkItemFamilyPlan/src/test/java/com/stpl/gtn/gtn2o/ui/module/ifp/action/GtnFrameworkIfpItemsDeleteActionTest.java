package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;


/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class})
public class GtnFrameworkIfpItemsDeleteActionTest {

	@Test
	public void testGtnFrameworkIfpItemsDeleteAction_1()
		throws Exception {
		GtnFrameworkIfpItemsDeleteAction result = new GtnFrameworkIfpItemsDeleteAction();
		assertNotNull(result);
	}

	@Test
	public void testConfigureParams()
		throws Exception {
		
		GtnFrameworkIfpItemsDeleteAction fixture = new GtnFrameworkIfpItemsDeleteAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		fixture.configureParams(gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testCreateInstance()
		throws Exception {
		GtnFrameworkIfpItemsDeleteAction fixture = new GtnFrameworkIfpItemsDeleteAction();

		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testDoAction()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpItemsDeleteAction gtnFrameworkIfpItemsDeleteAction = new GtnFrameworkIfpItemsDeleteAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		gtnFrameworkIfpItemsDeleteAction.doAction(componentId, gtnUIFrameWorkActionConfig);

	}

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

}