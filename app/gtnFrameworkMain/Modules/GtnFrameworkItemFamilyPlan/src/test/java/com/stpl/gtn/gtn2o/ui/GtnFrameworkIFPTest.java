package com.stpl.gtn.gtn2o.ui;

import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doReturn;
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

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.module.ifp.dynamicclasses.GtnUIFrameworkIfpDynamicClassFiller;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { UI.class, GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkIFPTest {
	
	private transient GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIFPTest.class); 
	
	@Mock
	GtnUIFrameworkEngine gtnUIFrameworkEngine;
	@InjectMocks
	GtnFrameworkIFP gtnFrameworkIFPInstance;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
    public void testInit() {
        System.out.println("init");
        try{
        VaadinRequest vaadinRequest = Mockito.mock(VaadinRequest.class);
        UI ui = new UI() {

            @Override
            protected void init(VaadinRequest request) {
            }
        };
        PowerMockito.mockStatic(UI.class, GtnUIFrameworkGlobalUI.class);
        when(UI.getCurrent()).thenReturn(ui);
        
        GtnUIFrameworkEngine gtnUIFrameworkEngine = Mockito.mock(GtnUIFrameworkEngine.class);
        GtnUIFrameworkRootConfig gtnUIFrameworkRootConfig = Mockito.mock(GtnUIFrameworkRootConfig.class);
        Navigator navigator = Mockito.mock(Navigator.class);
        GtnUIFrameworkIfpDynamicClassFiller gtnUIFrameworkIfpDynamicClassFiller = Mockito.mock(GtnUIFrameworkIfpDynamicClassFiller.class);

        PowerMockito.whenNew(GtnUIFrameworkEngine.class).withAnyArguments().thenReturn(gtnUIFrameworkEngine);
        PowerMockito.whenNew(GtnUIFrameworkRootConfig.class).withAnyArguments().thenReturn(gtnUIFrameworkRootConfig);
        PowerMockito.whenNew(Navigator.class).withAnyArguments().thenReturn(navigator);
        PowerMockito.whenNew(GtnUIFrameworkIfpDynamicClassFiller.class).withNoArguments().thenReturn(gtnUIFrameworkIfpDynamicClassFiller);

        doReturn("").when(vaadinRequest).getRemoteUser();
        GtnUIFrameworkGlobalUI.addSessionProperty("ValueChangeAllowed", "");
        doNothing().when(gtnUIFrameworkEngine).buildVaadinScreen(gtnUIFrameworkRootConfig, navigator, vaadinRequest, ui, "Item Family Plan", gtnUIFrameworkIfpDynamicClassFiller);
        
        gtnFrameworkIFPInstance.init(vaadinRequest);

    }
        catch(Exception e){
        	gtnLogger.error("Error", e);
        }
    }

	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

}