package com.stpl.gtn.gtn2o.ui.forecastconfiguration;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.forecastconfiguration.dynamicclasses.GtnUIFrameworkForecastConfigDynamicClassFiller;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { UI.class })
public class GtnFrameworkForecastConfigurationTest {

	@Mock
	GtnUIFrameworkEngine gtnUIFrameworkEngine;
	@InjectMocks
	GtnFrameworkForecastConfiguration instance;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
    public void testInit() throws Exception {
        System.out.println("init");
        VaadinRequest request = null;
        UI ui = new UI() {

            @Override
            protected void init(VaadinRequest request) {
            }
        };
        PowerMockito.mockStatic(UI.class);
        when(UI.getCurrent()).thenReturn(ui);
        GtnUIFrameworkEngine engine = Mockito.mock(GtnUIFrameworkEngine.class);
        GtnUIFrameworkRootConfig root = Mockito.mock(GtnUIFrameworkRootConfig.class);
        Navigator nav = Mockito.mock(Navigator.class);
        GtnUIFrameworkForecastConfigDynamicClassFiller fil = Mockito.mock(GtnUIFrameworkForecastConfigDynamicClassFiller.class);
        PowerMockito.whenNew(GtnUIFrameworkRootConfig.class).withAnyArguments().thenReturn(root);
        PowerMockito.whenNew(Navigator.class).withAnyArguments().thenReturn(nav);
        PowerMockito.whenNew(GtnUIFrameworkForecastConfigDynamicClassFiller.class).withNoArguments().thenReturn(fil);

        doNothing().when(engine).buildVaadinScreen(root, nav, request, ui, "Forecast Configuration", fil);

        PowerMockito.whenNew(GtnUIFrameworkEngine.class).withAnyArguments().thenReturn(engine);
        instance.init(request);
 
    }
}
