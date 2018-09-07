package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecastconfiguration.GtnWsForecastConfigurationResponse;


/**
 * @author praveen.kumar
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkConfirmSaveActionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testDoAction_1() throws Exception {
		
		String componentId = "";
		String tableId= "";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        List<Object> parameters = IntStream.rangeClosed(0, 24).boxed().collect(Collectors.toList());
        parameters.set(1, null);
        parameters.set(2,"val");

        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
        gtnUIFrameWorkActionConfig.setActionParameterList(parameters);
        GtnFrameworkConfirmSaveAction instance = new GtnFrameworkConfirmSaveAction();
        
        GtnUIFrameworkWebserviceResponse newResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsForecastConfigurationResponse obj=Mockito.mock(GtnWsForecastConfigurationResponse.class);
        doReturn(true).when(obj).isSuccess();
        newResponse.setGtnWsForecastConfigurationResponse(obj);

        GtnFrameworkConfirmSaveAction in = Mockito.spy(instance);
		doReturn(newResponse).when(in).getResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class),Mockito.any(GtnUIFrameworkWebServiceClient.class));

		GtnUIFrameworkComponentConfig config=new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkPagedTableConfig tableConfig=new GtnUIFrameworkPagedTableConfig();
		tableConfig.addPostCreationActionConfig(gtnUIFrameWorkActionConfig);
		config.setGtnPagedTableConfig(tableConfig);
		
		GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn(config).when(object).getComponentConfig();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_2() throws Exception {
		String componentId = "";
		String tableId= "";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        List<Object> parameters = IntStream.rangeClosed(0, 24).boxed().collect(Collectors.toList());
        parameters.set(1, null);
        parameters.set(2,"val");
    
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
        gtnUIFrameWorkActionConfig.setActionParameterList(parameters);
        GtnFrameworkConfirmSaveAction instance = new GtnFrameworkConfirmSaveAction();
        
        GtnUIFrameworkWebserviceResponse newResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsForecastConfigurationResponse obj=Mockito.mock(GtnWsForecastConfigurationResponse.class);
        doReturn(false).when(obj).isSuccess();
        newResponse.setGtnWsForecastConfigurationResponse(obj);

        GtnFrameworkConfirmSaveAction in = Mockito.spy(instance);
		doReturn(newResponse).when(in).getResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class),Mockito.any(GtnUIFrameworkWebServiceClient.class));
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
		
	}
	@Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkConfirmSaveAction instance = new GtnFrameworkConfirmSaveAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkConfirmSaveAction instance = new GtnFrameworkConfirmSaveAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
    @Test
	public void testGetResponse() throws Exception {
    	GtnFrameworkConfirmSaveAction fixture = new GtnFrameworkConfirmSaveAction();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		fixture.getResponse(request, wsclient);
	}
}
