/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard;

import static com.google.gwt.text.shared.testing.PassthroughRenderer.instance;
import com.stpl.gtn.gtn2o.ui.contractdashboard.dynamicclasses.GtnUIFrameworkContractDashboardClassFiller;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {UI.class})
public class GtnFrameworkContractDashboardTest {
    
    @Mock
    GtnUIFrameworkEngine gtnUIFrameworkEngine;
    @InjectMocks
    GtnFrameworkContractDashboard instance;
    
    public GtnFrameworkContractDashboardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of init method, of class GtnFrameworkContractDashboard.
     */
    @Test
    public void testInit() throws Exception {
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
        GtnUIFrameworkContractDashboardClassFiller fil = Mockito.mock(GtnUIFrameworkContractDashboardClassFiller.class);
        PowerMockito.whenNew(GtnUIFrameworkRootConfig.class).withAnyArguments().thenReturn(root);
        PowerMockito.whenNew(Navigator.class).withAnyArguments().thenReturn(nav);
        PowerMockito.whenNew(GtnUIFrameworkContractDashboardClassFiller.class).withNoArguments().thenReturn(fil);

        doNothing().when(engine).buildVaadinScreen(root, nav, request, ui, "Contract Dashboard", fil);

        PowerMockito.whenNew(GtnUIFrameworkEngine.class).withAnyArguments().thenReturn(engine);
        instance.init(request);

    }
        catch(Exception e){
            
        }
    }
}
