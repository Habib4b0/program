package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doNothing;
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
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

/**
 * 
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class })
public class GtnUiFrameworkNsfRemoveConfirmationActionTest {


	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams_1() throws Exception {
		GtnUiFrameworkNsfRemoveConfirmationAction fixture = new GtnUiFrameworkNsfRemoveConfirmationAction();
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
	public void testCreateInstance_1() throws Exception {
		GtnUiFrameworkNsfRemoveConfirmationAction fixture = new GtnUiFrameworkNsfRemoveConfirmationAction();

		GtnUIFrameWorkAction result = fixture.createInstance();

		// add additional test code here
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
	public void testGtnUiFrameworkNsfRemoveConfirmationAction_DoAction_if() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfRemoveConfirmationAction fixture = new GtnUiFrameworkNsfRemoveConfirmationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add("view0");
		actionParemeterList.add("view1");
		actionParemeterList.add(true);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("20516");
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());

		GtnUiFrameworkNsfRemoveConfirmationAction gtnUiFrameworkNsfRemoveConfirmationAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);

		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		gtnWsGeneralResponse.setSucess(true);
		response.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		doReturn(response).when(gtnUiFrameworkNsfRemoveConfirmationAction).getResponse(Mockito.any());

		doReturn(gtnWsGeneralResponse).when(response).getGtnWsGeneralResponse();

		GtnFrameworkNSFCommonLogic.reloadTable("", "");

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doNothing().when(baseComponent).setPagedTableHeaderCheckBox(Mockito.anyBoolean(), Mockito.anyString());

		gtnUiFrameworkNsfRemoveConfirmationAction.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnUiFrameworkNsfRemoveConfirmationAction_DoAction_else() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfRemoveConfirmationAction fixture = new GtnUiFrameworkNsfRemoveConfirmationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add("view0");
		actionParemeterList.add("view1");
		actionParemeterList.add(true);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("20516");
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());

		GtnUiFrameworkNsfRemoveConfirmationAction gtnUiFrameworkNsfRemoveConfirmationAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);

		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		// set to false for else
		gtnWsGeneralResponse.setSucess(false);
		response.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		doReturn(response).when(gtnUiFrameworkNsfRemoveConfirmationAction).getResponse(Mockito.any());

		doReturn(gtnWsGeneralResponse).when(response).getGtnWsGeneralResponse();

		GtnFrameworkNSFCommonLogic.reloadTable("", "");

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doNothing().when(baseComponent).setPagedTableHeaderCheckBox(Mockito.anyBoolean(), Mockito.anyString());

		gtnUiFrameworkNsfRemoveConfirmationAction.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testReloadTable_else() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfRemoveConfirmationAction fixture = new GtnUiFrameworkNsfRemoveConfirmationAction();
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doNothing().when(baseComponent).setPagedTableHeaderCheckBox(Mockito.anyBoolean(), Mockito.anyString());

		fixture.reloadTable(false, "");

	}
	/**
	 * Run the GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebserviceRequest request) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGtnFrameworkIfpDelete_doAction_getResponse() {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUiFrameworkNsfRemoveConfirmationAction gtnUiFrameworkNsfRemoveConfirmationAction = new GtnUiFrameworkNsfRemoveConfirmationAction();
		GtnUIFrameworkWebserviceRequest gtnRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		gtnUiFrameworkNsfRemoveConfirmationAction.getResponse(gtnRequest);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *             if the initialization fails for some reason
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
	 */
	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args
	 *            the command line arguments
	 *
	 */

}