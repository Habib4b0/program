package com.stpl.gtn.gtn2o.ui;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;


import com.stpl.gtn.gtn2o.registry.dynamicclasses.GtnUIFrameworkDynamicClassFiller;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinPortletRequest;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Ignore
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { UI.class })
public class GtnUIFrameworkCommercialForecastingTest {

	@Mock
	GtnUIFrameworkEngine gtnUIFrameworkEngine;
	@InjectMocks
	GtnUIFrameworkCommercialForecasting instance;

	@Ignore
	@Test
	public void testGtnUIFrameworkCommercialForecasting_1() throws Exception {
		GtnUIFrameworkCommercialForecasting result = new GtnUIFrameworkCommercialForecasting();
		assertNotNull(result);
		// add additional test code here
	}

	@Test
	public void testInit_1() throws Exception {
		/*
		 * GtnUIFrameworkCommercialForecasting fixture = new
		 * GtnUIFrameworkCommercialForecasting(); VaadinRequest request =
		 * VaadinPortletRequest.getCurrent();
		 * 
		 * fixture.init(request); GtnUIFrameworkRootConfig
		 * rootConfig=Mockito.mock(GtnFrameworkCommercialForecastingConfig.class); //
		 * add additional test code here
		 */
		System.out.println("init");
		try {
			VaadinRequest request = VaadinPortletRequest.getCurrent();
			UI ui = new UI() {

				@Override
				protected void init(VaadinRequest request) {
				}
			};

//	        Constructor<GtnFrameworkComponentConfigProvider> constructor = GtnFrameworkComponentConfigProvider.class.getDeclaredConstructor(new Class[0]);
//	        constructor.setAccessible(true);
//	        Foo foo = constructor.newInstance(new Object[0]);
//	        GtnFrameworkComponentConfigProvider config = Mockito.mock(GtnFrameworkComponentConfigProvider.);
			PowerMockito.mockStatic(UI.class);
			when(UI.getCurrent()).thenReturn(ui);
			GtnUIFrameworkEngine engine = Mockito.mock(GtnUIFrameworkEngine.class);
			GtnUIFrameworkRootConfig root = Mockito.mock(GtnUIFrameworkRootConfig.class);
			Navigator nav = Mockito.mock(Navigator.class);
			GtnUIFrameworkDynamicClassFiller fil = Mockito.mock(GtnUIFrameworkDynamicClassFiller.class);
			PowerMockito.whenNew(GtnUIFrameworkRootConfig.class).withAnyArguments().thenReturn(root);
			PowerMockito.whenNew(Navigator.class).withAnyArguments().thenReturn(nav);
			PowerMockito.whenNew(GtnUIFrameworkDynamicClassFiller.class).withNoArguments().thenReturn(fil);

			doNothing().when(engine).buildVaadinScreen(root, nav, request, ui, "Commercial Forecasting", fil);

			// PowerMockito.whenNew(GtnUIFrameworkEngine.class).withAnyArguments().thenReturn(engine);

			instance.init(request);
		} catch (Exception e) {

		}

	}

	@Before
	public void setUp() throws Exception {
		// add additional set up code here
	}

	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnUIFrameworkCommercialForecastingTest.class);
	}
}