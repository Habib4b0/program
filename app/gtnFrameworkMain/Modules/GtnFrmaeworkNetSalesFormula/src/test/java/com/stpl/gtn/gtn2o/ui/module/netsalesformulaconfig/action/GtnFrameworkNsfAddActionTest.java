package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

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
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkNsfAddActionTest {
	/**
	 * Run the GtnFrameworkNsfAddAction() constructor test.
	 *
	 * 
	 */
	@Test
	public void testGtnFrameworkNsfAddAction_1() throws Exception {
		GtnFrameworkNsfAddAction result = new GtnFrameworkNsfAddAction();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams_1() throws Exception {
		GtnFrameworkNsfAddAction fixture = new GtnFrameworkNsfAddAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		fixture.configureParams(gtnUIFrameWorkActionConfig);

		// add additional test code here
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

		GtnFrameworkNsfAddAction fixture = new GtnFrameworkNsfAddAction();
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
	public void testDoAction() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkNsfAddAction fixture = new GtnFrameworkNsfAddAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn("20516");

		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();

		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		//gtnWsresponse.getGtnWsGeneralResponse().isSucess() = true
		gtnWsGeneralResponse.setSucess(true);
		gtnWsresponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		GtnFrameworkNsfAddAction gtnFrameworkNsfAddAction = Mockito.spy(fixture);
		doReturn(gtnWsresponse).when(gtnFrameworkNsfAddAction).getResponse(Mockito.any());

		gtnFrameworkNsfAddAction.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_else() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkNsfAddAction fixture = new GtnFrameworkNsfAddAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn("20516");

		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();

		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		//gtnWsresponse.getGtnWsGeneralResponse().isSucess() = false
		gtnWsGeneralResponse.setSucess(false);
		gtnWsresponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		GtnFrameworkNsfAddAction gtnFrameworkNsfAddAction = Mockito.spy(fixture);
		doReturn(gtnWsresponse).when(gtnFrameworkNsfAddAction).getResponse(Mockito.any());

		gtnFrameworkNsfAddAction.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	/**
	 * Run the GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebserviceRequest request) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGtnFrameworkNsfAdd_doAction_getResponse() {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnFrameworkNsfAddAction gtnFrameworkNsfAddAction = new GtnFrameworkNsfAddAction();
		GtnUIFrameworkWebserviceRequest gtnRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		gtnFrameworkNsfAddAction.getResponse(gtnRequest);
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