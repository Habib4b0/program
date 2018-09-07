package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
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

import com.liferay.portal.kernel.management.jmx.SetAttributeAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecastconfiguration.GtnWsForecastConfigurationResponse;
import com.vaadin.v7.ui.TextField;
import static org.mockito.Mockito.doReturn;

/**
 * @author praveen.kumar
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class,
		GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class })
public class GtnUIFrameworkIntervalFrequencyValueChangeActionTest {

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
		GtnUIFrameworkIntervalFrequencyValueChangeAction instance = new GtnUIFrameworkIntervalFrequencyValueChangeAction();
		instance.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance() {
		System.out.println("createInstance");
		GtnUIFrameworkIntervalFrequencyValueChangeAction instance = new GtnUIFrameworkIntervalFrequencyValueChangeAction();
		GtnUIFrameWorkAction result = instance.createInstance();
		assertEquals(instance, result);
	}

	@Test
	public void testDoAction_1() throws Exception {
		GtnUIFrameworkIntervalFrequencyValueChangeAction instance = new GtnUIFrameworkIntervalFrequencyValueChangeAction();

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);

		Constructor cons = (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
		cons.setAccessible(true);

		GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn(1).when(object).getIntegerFromField();
		doReturn(true).when(object).isValidFieldValue();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		GtnWsForecastConfigurationResponse obj = Mockito.mock(GtnWsForecastConfigurationResponse.class);
		doReturn(true).when(obj).isErrorMessage();
		response.setGtnWsForecastConfigurationResponse(obj);
		GtnUIFrameworkIntervalFrequencyValueChangeAction in = Mockito.spy(instance);
		doReturn(response).when(in).getWsResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));

		in.doAction(componentId, gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testGetWSResponse() throws Exception {
		GtnUIFrameworkIntervalFrequencyValueChangeAction fixture = new GtnUIFrameworkIntervalFrequencyValueChangeAction();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		fixture.getWsResponse(request);
	}

}
