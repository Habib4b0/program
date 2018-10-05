package com.stpl.gtn.gtn2o.ui.portlets;

import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.dynamicclasses.GtnUIFrameworkNsfDynamicClassFiller;
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
public class GtnFrameworkNetSalesFormulaPortletTest {
	
	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkNetSalesFormulaPortletTest.class); 
	
	@InjectMocks
	GtnFrameworkNetSalesFormulaPortlet gtnFrameworkNetSalesFormulaPortlet;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
    public void testGtnFrameworkNetSalesFormulaPortlet_1() {
        System.out.println("init");
        try{
        VaadinRequest vaadinRequest = Mockito.mock(VaadinRequest.class);
        UI ui = new UI() {

            @Override
            protected void init(VaadinRequest request) {
            	// Init method
            }
        };
        
        PowerMockito.mockStatic(UI.class, GtnUIFrameworkGlobalUI.class);
        when(UI.getCurrent()).thenReturn(ui);
        
        GtnUIFrameworkEngine gtnUIFrameworkEngine = Mockito.mock(GtnUIFrameworkEngine.class);
        GtnUIFrameworkRootConfig gtnUIFrameworkRootConfig = Mockito.mock(GtnUIFrameworkRootConfig.class);
        Navigator navigator = Mockito.mock(Navigator.class);
        GtnUIFrameworkNsfDynamicClassFiller gtnUIFrameworkIfpDynamicClassFiller = Mockito.mock(GtnUIFrameworkNsfDynamicClassFiller.class);

        PowerMockito.whenNew(GtnUIFrameworkEngine.class).withAnyArguments().thenReturn(gtnUIFrameworkEngine);
        PowerMockito.whenNew(GtnUIFrameworkRootConfig.class).withAnyArguments().thenReturn(gtnUIFrameworkRootConfig);
        PowerMockito.whenNew(Navigator.class).withAnyArguments().thenReturn(navigator);
        PowerMockito.whenNew(GtnUIFrameworkNsfDynamicClassFiller.class).withNoArguments().thenReturn(gtnUIFrameworkIfpDynamicClassFiller);

        doNothing().when(gtnUIFrameworkEngine).buildVaadinScreen(gtnUIFrameworkRootConfig, navigator, vaadinRequest, ui, "Net Sales Formula", gtnUIFrameworkIfpDynamicClassFiller);
        
        gtnFrameworkNetSalesFormulaPortlet.init(vaadinRequest);

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