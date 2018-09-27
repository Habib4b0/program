package com.stpl.gtn.gtn2o.ui.module.ifp.action;

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
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkIfpDeleteActionTest {

	@Test
	public void testConfigureParams() throws Exception {

		GtnFrameworkIfpDeleteAction fixture = new GtnFrameworkIfpDeleteAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testCreateInstance() throws Exception {

		GtnFrameworkIfpDeleteAction fixture = new GtnFrameworkIfpDeleteAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testGtnFrameworkIfpDelete_doAction_isSuccessFalse() {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkIfpDeleteAction fixture = new GtnFrameworkIfpDeleteAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(0);

		GtnFrameworkIfpDeleteAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse updateResponse = new GtnUIFrameworkWebserviceResponse();
		doReturn(updateResponse).when(gtnFrameworkIfpDeleteAction).getResponse(Mockito.any());

		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		gtnWsGeneralResponse.setSucess(false);
		updateResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		try {
			gtnFrameworkIfpDeleteAction.doAction(componentId, gtnUIFrameWorkActionConfig);

		} catch (GtnFrameworkGeneralException ex) {
			System.out.println(ex.getMessage());
		}

	}
	
	@Test
	public void testGtnFrameworkIfpDelete_doAction_isSuccessTrue() {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkIfpDeleteAction fixture = new GtnFrameworkIfpDeleteAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(0);

		GtnFrameworkIfpDeleteAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse updateResponse = new GtnUIFrameworkWebserviceResponse();
		doReturn(updateResponse).when(gtnFrameworkIfpDeleteAction).getResponse(Mockito.any());

		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		gtnWsGeneralResponse.setSucess(true);
		updateResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		
		GtnUIFrameworkBaseComponent beaseComponent  =Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(beaseComponent);
		doReturn("").when(beaseComponent).getStringFromField();

		try {
			gtnFrameworkIfpDeleteAction.doAction(componentId, gtnUIFrameWorkActionConfig);

		} catch (GtnFrameworkGeneralException ex) {
			System.out.println(ex.getMessage());
		}

	}
	
	@Test
	public void testGtnFrameworkIfpDelete_doAction_getResponse() {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnFrameworkIfpDeleteAction fixture = new GtnFrameworkIfpDeleteAction();
		GtnUIFrameworkWebserviceRequest gtnRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		fixture.getResponse(gtnRequest);
	}

	@Before
	public void setUp() throws Exception {
		// add additional set up code here
	}

	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here
	}

}
