package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.when;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.v7.ui.TextField;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
		GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class })
public class GtnUIFrameworkForecastConfigurationSaveActionTest {

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
		GtnUIFrameworkForecastConfigurationSaveAction instance = new GtnUIFrameworkForecastConfigurationSaveAction();
		instance.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance() {
		System.out.println("createInstance");
		GtnUIFrameworkForecastConfigurationSaveAction instance = new GtnUIFrameworkForecastConfigurationSaveAction();
		GtnUIFrameWorkAction result = instance.createInstance();
		assertEquals(instance, result);

	}

	@Test
	public void testDoAction_1() throws GtnFrameworkGeneralException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		GtnUIFrameworkForecastConfigurationSaveAction instance = new GtnUIFrameworkForecastConfigurationSaveAction();
		String componentId="";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		Constructor cons = (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
		cons.setAccessible(true);
		GtnUIFrameworkBaseComponent object = (GtnUIFrameworkBaseComponent) cons.newInstance(new TextField("cap","value"), "componentId");

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);

		List<Object> parameters = Arrays.asList(1, 2, 3, 4);
		gtnUIFrameWorkActionConfig.setActionParameterList(parameters);
		instance.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction() throws GtnFrameworkGeneralException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		GtnUIFrameworkForecastConfigurationSaveAction instance = new GtnUIFrameworkForecastConfigurationSaveAction();
		String componentId="";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		Constructor cons = (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
		cons.setAccessible(true);
		GtnUIFrameworkBaseComponent object = (GtnUIFrameworkBaseComponent) cons.newInstance(new TextField("cap","value"), "componentId");

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);

		List<Object> parameters = Arrays.asList(1, 2, 3, 4);
		gtnUIFrameWorkActionConfig.setActionParameterList(parameters);
		instance.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
}
