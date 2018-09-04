package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.doReturn;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecastconfiguration.GtnWsForecastConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecastconfiguration.GtnWsForecastConfigurationResponse;
/**
 * @author praveen.kumar
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnUIFrameworkModeValueChangeActionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkModeValueChangeAction instance = new GtnUIFrameworkModeValueChangeAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkModeValueChangeAction instance = new GtnUIFrameworkModeValueChangeAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
	@Test
	public void testDoAction_1() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameworkModeValueChangeAction instance = new GtnUIFrameworkModeValueChangeAction();
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("Interval").when(base).getStringFromField();
    	doReturn(true).when(base).isValidFieldValue();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		
		GtnUIFrameworkWebserviceResponse newResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsForecastConfigurationResponse obj1=Mockito.mock(GtnWsForecastConfigurationResponse.class);
		newResponse.setGtnWsForecastConfigurationResponse(obj1);
		GtnUIFrameworkModeValueChangeAction in = Mockito.spy(instance);
		doReturn(newResponse).when(in).getResponseFutureInterval(Mockito.any(GtnUIFrameworkWebserviceRequest.class));

//		GtnUIFrameworkWebserviceResponse newRequest = new GtnUIFrameworkWebserviceResponse();
//		GtnWsForecastConfigurationResponse obj2=Mockito.mock(GtnWsForecastConfigurationResponse.class);
//		newRequest.setGtnWsForecastConfigurationResponse(obj2);
//		doReturn(newRequest).when(in).getResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGetResponse()
		throws Exception {
		GtnUIFrameworkModeValueChangeAction fixture = new GtnUIFrameworkModeValueChangeAction();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkWebserviceResponse result = fixture.getResponse(request);

	}


	@Test
	public void testGetResponseFutureInterval()
		throws Exception {
		GtnUIFrameworkModeValueChangeAction fixture = new GtnUIFrameworkModeValueChangeAction();
		GtnUIFrameworkWebserviceRequest futureRequest = new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkWebserviceResponse result = fixture.getResponseFutureInterval(futureRequest);

	}
}
