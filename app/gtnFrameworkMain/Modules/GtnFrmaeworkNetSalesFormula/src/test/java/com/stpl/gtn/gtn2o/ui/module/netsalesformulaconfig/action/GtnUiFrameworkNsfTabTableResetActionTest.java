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
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class })
public class GtnUiFrameworkNsfTabTableResetActionTest {

	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnUiFrameworkNsfTabTableResetAction fixture = new GtnUiFrameworkNsfTabTableResetAction();
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

		GtnUiFrameworkNsfTabTableResetAction fixture = new GtnUiFrameworkNsfTabTableResetAction();
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
	public void testGtnUiFrameworkNsfTabTableReset_doAction_isSalesBasis_isSuccess_true() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfTabTableResetAction fixture = new GtnUiFrameworkNsfTabTableResetAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		// isSalesBasis = true
		actionParemeterList.add(true);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(1);

		GtnUIFrameworkWebserviceResponse gtnResponse = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnWsGeneralResponse nsfGeneralResponse = Mockito.mock(GtnWsGeneralResponse.class);

		doReturn(nsfGeneralResponse).when(gtnResponse).getGtnWsGeneralResponse();
		// isSuccess = true
		doReturn(true).when(nsfGeneralResponse).isSucess();

		GtnUiFrameworkNsfTabTableResetAction action = Mockito.spy(fixture);
		doReturn(gtnResponse).when(action).getResponse(Mockito.any());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		action.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfTabTableReset_doAction_isSalesBasis_false() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfTabTableResetAction fixture = new GtnUiFrameworkNsfTabTableResetAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		// isSalesBasis = false
		actionParemeterList.add(false);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(1);

		GtnUIFrameworkWebserviceResponse gtnResponse = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnWsGeneralResponse nsfGeneralResponse = Mockito.mock(GtnWsGeneralResponse.class);

		doReturn(nsfGeneralResponse).when(gtnResponse).getGtnWsGeneralResponse();
		// isSuccess = true
		doReturn(true).when(nsfGeneralResponse).isSucess();

		GtnUiFrameworkNsfTabTableResetAction action = Mockito.spy(fixture);
		doReturn(gtnResponse).when(action).getResponse(Mockito.any());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		action.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfTabTableReset_doAction_isSuccess_false() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfTabTableResetAction fixture = new GtnUiFrameworkNsfTabTableResetAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		// isSalesBasis = false
		actionParemeterList.add(false);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(1);

		GtnUIFrameworkWebserviceResponse gtnResponse = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnWsGeneralResponse nsfGeneralResponse = Mockito.mock(GtnWsGeneralResponse.class);

		doReturn(nsfGeneralResponse).when(gtnResponse).getGtnWsGeneralResponse();
		// isSuccess = false
		doReturn(false).when(nsfGeneralResponse).isSucess();

		GtnUiFrameworkNsfTabTableResetAction action = Mockito.spy(fixture);
		doReturn(gtnResponse).when(action).getResponse(Mockito.any());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		action.doAction(componentId, gtnUIFrameWorkActionConfig);
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