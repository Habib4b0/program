package com.stpl.gtn.gtn2o.ui.calendarconfiguration;

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

import com.stpl.gtn.gtn2o.ui.calendarconfiguration.dynamicclasses.GtnUIFrameworkCalenderConfigDynamicClassFiller;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * @author praveen.kumar
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { UI.class })
public class GtnFrameworkCalendarConfigurationTest {

private transient GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCalendarConfigurationTest.class); 
	
	@Mock
	GtnUIFrameworkEngine gtnUIFrameworkEngine;
	@InjectMocks
	GtnFrameworkCalendarConfiguration instance;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
    public void testInit() {
        System.out.println("init");
        try{
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
        GtnUIFrameworkCalenderConfigDynamicClassFiller fil = Mockito.mock(GtnUIFrameworkCalenderConfigDynamicClassFiller.class);
        PowerMockito.whenNew(GtnUIFrameworkRootConfig.class).withAnyArguments().thenReturn(root);
        PowerMockito.whenNew(Navigator.class).withAnyArguments().thenReturn(nav);
        PowerMockito.whenNew(GtnUIFrameworkCalenderConfigDynamicClassFiller.class).withNoArguments().thenReturn(fil);
        PowerMockito.whenNew(GtnUIFrameworkEngine.class).withAnyArguments().thenReturn(engine);
        doNothing().when(engine).buildVaadinScreen(root, nav, request, ui, "Calendar Configuration", fil);
        instance.init(request);

    }
        catch(Exception e){
        	gtnLogger.error("Error", e);
        }
    }

}
