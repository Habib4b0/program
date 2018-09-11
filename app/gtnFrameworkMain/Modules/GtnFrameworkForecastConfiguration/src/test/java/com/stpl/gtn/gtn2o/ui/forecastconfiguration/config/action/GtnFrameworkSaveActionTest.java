package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.mockito.Mockito.doReturn;
import java.lang.reflect.Constructor;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecastconfiguration.GtnWsForecastConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecastconfiguration.GtnWsForecastConfigurationResponse;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkSaveActionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testDoAction() throws GtnFrameworkValidationFailedException, ParseException {
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
        String componentId = "";
        
        GtnFrameworkSaveAction instance= new GtnFrameworkSaveAction();
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        
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
        gtnUIFrameWorkActionConfig.addActionParameter(1);
        gtnUIFrameWorkActionConfig.addActionParameter(1);
        gtnUIFrameWorkActionConfig.addActionParameter(1);
        gtnUIFrameWorkActionConfig.addActionParameter(1);
        
        GtnWsForecastConfigurationRequest request=new GtnWsForecastConfigurationRequest();
        GtnUIFrameworkWebserviceResponse wsResponse=new GtnUIFrameworkWebserviceResponse();
        
        GtnWsForecastConfigurationResponse obj=Mockito.mock(GtnWsForecastConfigurationResponse.class);
        wsResponse.setGtnWsForecastConfigurationResponse(obj);
        doReturn(true).when(obj).isSuccess();
        
        GtnFrameworkSaveAction in=Mockito.spy(instance);
        doReturn(request).when(in).getModifiedRequest(Mockito.any(GtnWsForecastConfigurationRequest.class),Mockito.any(List.class));
        doReturn(wsResponse).when(in).getResponse(Mockito.any(GtnUIFrameworkWebServiceClient.class),Mockito.any(GtnUIFrameworkWebserviceRequest.class));

        GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
        
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
     }

	@Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkSaveAction instance = new GtnFrameworkSaveAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkSaveAction instance = new GtnFrameworkSaveAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    @Test
	public void testGetResponse() throws Exception {
    	GtnFrameworkSaveAction fixture = new GtnFrameworkSaveAction();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		fixture.getResponse(wsclient, request);
	}

    @Test
    public void testGetModifiedRequest() throws GtnFrameworkValidationFailedException, ParseException {
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
    	GtnWsForecastConfigurationRequest fcRequest=new GtnWsForecastConfigurationRequest();
    	List<Object> parameters = IntStream.rangeClosed(0, 20).boxed().collect(Collectors.toList());
    	GtnFrameworkSaveAction instance = new GtnFrameworkSaveAction();
    	when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("122");

    	 GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
         when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);
         doReturn(1).when(object).getIntegerFromField();
         doReturn("Peri").when(object).getStringFromField();
         
    	instance.getModifiedRequest(fcRequest, parameters);
    }
    
}
