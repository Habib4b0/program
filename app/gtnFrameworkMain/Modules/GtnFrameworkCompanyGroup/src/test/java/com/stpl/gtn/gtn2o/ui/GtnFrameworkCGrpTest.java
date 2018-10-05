package com.stpl.gtn.gtn2o.ui;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

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
import com.stpl.gtn.gtn2o.ui.module.customergroup.dynamicclasses.GtnUIFrameworkCGrpDynamicClassFiller;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { UI.class, GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkCGrpTest {
	
public GtnFrameworkCGrpTest() {
		super();
	}

private static GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCGrpTest.class); 
	
	@InjectMocks
	private GtnFrameworkCGrp gtnFrameworkCGrpInstance;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testInit(){
	        try{
	        VaadinRequest vaadinRequest = Mockito.mock(VaadinRequest.class);
	        UI ui = new UI() {
	         
				private static final long serialVersionUID = 1L;

				@Override
	            protected void init(VaadinRequest request) {
					//init method
	            }
	        };
	        PowerMockito.mockStatic(UI.class, GtnUIFrameworkGlobalUI.class);
	        when(UI.getCurrent()).thenReturn(ui);
	        
	        GtnUIFrameworkEngine gtnUIFrameworkEngine = Mockito.mock(GtnUIFrameworkEngine.class);
	        GtnUIFrameworkRootConfig gtnUIFrameworkRootConfig = Mockito.mock(GtnUIFrameworkRootConfig.class);
	        Navigator navigator = Mockito.mock(Navigator.class);
	        GtnUIFrameworkCGrpDynamicClassFiller gtnUIFrameworkCGrpDynamicClassFiller = Mockito.mock(GtnUIFrameworkCGrpDynamicClassFiller.class);

	        PowerMockito.whenNew(GtnUIFrameworkEngine.class).withAnyArguments().thenReturn(gtnUIFrameworkEngine);
	        PowerMockito.whenNew(GtnUIFrameworkRootConfig.class).withAnyArguments().thenReturn(gtnUIFrameworkRootConfig);
	        PowerMockito.whenNew(Navigator.class).withAnyArguments().thenReturn(navigator);
	        PowerMockito.whenNew(GtnUIFrameworkCGrpDynamicClassFiller.class).withNoArguments().thenReturn(gtnUIFrameworkCGrpDynamicClassFiller);

	        doReturn("").when(vaadinRequest).getRemoteUser();
	        GtnUIFrameworkGlobalUI.addSessionProperty("ValueChangeAllowed", "");
	        doNothing().when(gtnUIFrameworkEngine).buildVaadinScreen(gtnUIFrameworkRootConfig, navigator, vaadinRequest, ui, "Customer Group Master", gtnUIFrameworkCGrpDynamicClassFiller);
	        
	        gtnFrameworkCGrpInstance.init(vaadinRequest);

	        }
	        catch(Exception e){
	        	gtnLogger.error("Error", e);
	        }		
	}

}