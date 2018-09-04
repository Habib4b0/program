package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import static org.junit.Assert.*;

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
import com.stpl.gtn.gtn2o.ws.response.forecastconfiguration.GtnWsForecastConfigurationResponse;

import static org.mockito.Mockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @author praveen.kumar
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnUIFrameworkHistoryIntervalValueChangeActionTest {

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
        GtnUIFrameworkHistoryIntervalValueChangeAction instance = new GtnUIFrameworkHistoryIntervalValueChangeAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkHistoryIntervalValueChangeAction instance = new GtnUIFrameworkHistoryIntervalValueChangeAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

	@Test
	public void testDoAction() throws Exception {
        String componentId = "";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        GtnUIFrameworkHistoryIntervalValueChangeAction instance =new GtnUIFrameworkHistoryIntervalValueChangeAction();

        gtnUIFrameWorkActionConfig.addActionParameter(1);
        gtnUIFrameWorkActionConfig.addActionParameter(1);
        gtnUIFrameWorkActionConfig.addActionParameter(1);
        gtnUIFrameWorkActionConfig.addActionParameter(1);
        gtnUIFrameWorkActionConfig.addActionParameter(1);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);

		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn(true).when(base).isValidFieldValue();
		doReturn("val").when(base).getStringFromField();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
    	GtnUIFrameworkWebserviceResponse newResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsForecastConfigurationResponse obj=Mockito.mock(GtnWsForecastConfigurationResponse.class);
        doReturn(true).when(obj).isErrorMessage();
        
        newResponse.setGtnWsForecastConfigurationResponse(obj);
		GtnUIFrameworkHistoryIntervalValueChangeAction in = Mockito.spy(instance);
		doReturn(newResponse).when(in).getWsResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));

		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGetWSResponse() throws Exception {
		GtnUIFrameworkHistoryIntervalValueChangeAction fixture = new GtnUIFrameworkHistoryIntervalValueChangeAction();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		fixture.getWsResponse(request);
	}

}
