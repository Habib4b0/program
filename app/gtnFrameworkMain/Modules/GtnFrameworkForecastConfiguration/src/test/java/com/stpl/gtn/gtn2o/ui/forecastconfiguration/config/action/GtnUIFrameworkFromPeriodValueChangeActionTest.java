package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.when;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
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
import com.stpl.gtn.gtn2o.ws.request.forecastconfiguration.GtnWsForecastConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecastconfiguration.GtnWsForecastConfigurationResponse;
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextField;
import static org.mockito.Mockito.doReturn;

/**
 * @author praveen.kumar
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class,
		GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class })
public class GtnUIFrameworkFromPeriodValueChangeActionTest {

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
		GtnUIFrameworkFromPeriodValueChangeAction instance = new GtnUIFrameworkFromPeriodValueChangeAction();
		instance.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance() {
		System.out.println("createInstance");
		GtnUIFrameworkFromPeriodValueChangeAction instance = new GtnUIFrameworkFromPeriodValueChangeAction();
		GtnUIFrameWorkAction result = instance.createInstance();
		assertEquals(instance, result);
	}

	@Test
	public void testDoAction() throws GtnFrameworkGeneralException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		String componentId = "";
		GtnUIFrameworkFromPeriodValueChangeAction instance = new GtnUIFrameworkFromPeriodValueChangeAction();

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString())).thenReturn(base);

		Constructor cons = (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
		cons.setAccessible(true);
		GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn(new Date()).when(object).getDateFromDateField();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		GtnWsForecastConfigurationResponse obj = Mockito.mock(GtnWsForecastConfigurationResponse.class);
		doReturn(true).when(obj).isErrorMessage();
		response.setGtnWsForecastConfigurationResponse(obj);
		GtnUIFrameworkFromPeriodValueChangeAction in = Mockito.spy(instance);
		doReturn(response).when(in).getResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));

		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGetResponse() throws Exception {
		GtnUIFrameworkFromPeriodValueChangeAction fixture = new GtnUIFrameworkFromPeriodValueChangeAction();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		fixture.getResponse(request);
	}

}
