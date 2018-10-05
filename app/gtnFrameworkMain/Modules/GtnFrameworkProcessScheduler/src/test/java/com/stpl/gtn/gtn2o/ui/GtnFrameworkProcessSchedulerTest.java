/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkEngine;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.dynamicclasses.GtnUIFrameworkProcessSchedularDynamicClassFiller;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;

/**
 *
 * @author Hazihabibullah.Syed
 */
public class GtnFrameworkProcessSchedulerTest {
    
    @Mock
    GtnUIFrameworkEngine gtnUIFrameworkEngine;
    @InjectMocks
    GtnFrameworkProcessScheduler instance;
    
    public GtnFrameworkProcessSchedulerTest() {
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
        GtnUIFrameworkProcessSchedularDynamicClassFiller fil = Mockito.mock(GtnUIFrameworkProcessSchedularDynamicClassFiller.class);
        PowerMockito.whenNew(GtnUIFrameworkRootConfig.class).withAnyArguments().thenReturn(root);
        PowerMockito.whenNew(Navigator.class).withAnyArguments().thenReturn(nav);
        PowerMockito.whenNew(GtnUIFrameworkProcessSchedularDynamicClassFiller.class).withNoArguments().thenReturn(fil);

        doNothing().when(engine).buildVaadinScreen(root, nav, request, ui, "Process Scheduler", fil);

        PowerMockito.whenNew(GtnUIFrameworkEngine.class).withAnyArguments().thenReturn(engine);
        instance.init(request);

    }
        catch(Exception e){
            
        }
    }
    
}

    
