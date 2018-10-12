package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.validation;

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
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

/**
 * 
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class })
public class GtnUiFrameworkNsfRuleSaveUniqueValidationActionTest {

	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnUiFrameworkNsfRuleSaveUniqueValidationAction fixture = new GtnUiFrameworkNsfRuleSaveUniqueValidationAction();
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

		GtnUiFrameworkNsfRuleSaveUniqueValidationAction fixture = new GtnUiFrameworkNsfRuleSaveUniqueValidationAction();
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
	public void testDoAction_1() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfRuleSaveUniqueValidationAction fixture = new GtnUiFrameworkNsfRuleSaveUniqueValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		// boolean isEditMode = "edit".equalsIgnoreCase(mode) = false
		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("mode");

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("").when(baseComponent).getStringFromField();

		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();

		// gtnWsresponse.getGtnWsGeneralResponse().isSucess() = false
		gtnWsGeneralResponse.setSucess(false);
		gtnWsresponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		GtnUiFrameworkNsfRuleSaveUniqueValidationAction gtnUiFrameworkNsfRuleSaveUniqueValidationAction = Mockito
				.spy(fixture);
		doReturn(gtnWsresponse).when(gtnUiFrameworkNsfRuleSaveUniqueValidationAction).getResponse(Mockito.any());

		try {
			gtnUiFrameworkNsfRuleSaveUniqueValidationAction.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testDoAction_2() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkNsfRuleSaveUniqueValidationAction fixture = new GtnUiFrameworkNsfRuleSaveUniqueValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		// boolean isEditMode = "edit".equalsIgnoreCase(mode) = true
		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("edit");

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("").when(baseComponent).getStringFromField();

		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();

		// gtnWsresponse.getGtnWsGeneralResponse().isSucess() = false
		gtnWsGeneralResponse.setSucess(false);
		gtnWsresponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		GtnUiFrameworkNsfRuleSaveUniqueValidationAction gtnUiFrameworkNsfRuleSaveUniqueValidationAction = Mockito
				.spy(fixture);
		doReturn(gtnWsresponse).when(gtnUiFrameworkNsfRuleSaveUniqueValidationAction).getResponse(Mockito.any());

		gtnUiFrameworkNsfRuleSaveUniqueValidationAction.doAction(componentId, gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testDoAction_3() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkNsfRuleSaveUniqueValidationAction fixture = new GtnUiFrameworkNsfRuleSaveUniqueValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		// boolean isEditMode = "edit".equalsIgnoreCase(mode) = true
		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("edit");

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("").when(baseComponent).getStringFromField();

		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();

		// gtnWsresponse.getGtnWsGeneralResponse().isSucess() = true
		gtnWsGeneralResponse.setSucess(true);
		gtnWsresponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		GtnUiFrameworkNsfRuleSaveUniqueValidationAction gtnUiFrameworkNsfRuleSaveUniqueValidationAction = Mockito
				.spy(fixture);
		doReturn(gtnWsresponse).when(gtnUiFrameworkNsfRuleSaveUniqueValidationAction).getResponse(Mockito.any());

		gtnUiFrameworkNsfRuleSaveUniqueValidationAction.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testDoAction_4() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkNsfRuleSaveUniqueValidationAction fixture = new GtnUiFrameworkNsfRuleSaveUniqueValidationAction();
		// componentId.contains("back") = true
		String componentId = "back";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		// boolean isEditMode = "edit".equalsIgnoreCase(mode) = true
		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("edit");

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("").when(baseComponent).getStringFromField();

		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();

		// gtnWsresponse.getGtnWsGeneralResponse().isSucess() = true
		gtnWsGeneralResponse.setSucess(true);
		gtnWsresponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		GtnUiFrameworkNsfRuleSaveUniqueValidationAction gtnUiFrameworkNsfRuleSaveUniqueValidationAction = Mockito
				.spy(fixture);
		doReturn(gtnWsresponse).when(gtnUiFrameworkNsfRuleSaveUniqueValidationAction).getResponse(Mockito.any());

		gtnUiFrameworkNsfRuleSaveUniqueValidationAction.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkIfpDelete_doAction_getResponse() {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUiFrameworkNsfRuleSaveUniqueValidationAction fixture = new GtnUiFrameworkNsfRuleSaveUniqueValidationAction();
		GtnUIFrameworkWebserviceRequest gtnRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		fixture.getResponse(gtnRequest);
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