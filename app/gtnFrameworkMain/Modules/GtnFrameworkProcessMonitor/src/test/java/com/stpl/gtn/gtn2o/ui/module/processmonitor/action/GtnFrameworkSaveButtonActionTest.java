/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.processmonitor.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.processmonitor.constants.GtnWsProcessMonitorConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import de.steinwedel.messagebox.MessageBox;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class})
public class GtnFrameworkSaveButtonActionTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    public GtnFrameworkSaveButtonActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkSaveButtonAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkSaveButtonAction instance = new GtnFrameworkSaveButtonAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnFrameworkSaveButtonAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "V009";
    
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn("addProcessName").when(object).getStringFromField();
        doReturn("addProcess").when(object).getCaptionFromComboBox();
        doReturn(new Date()).when(object).getDateFromDateField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        
        GtnFrameworkSaveButtonAction instance=new GtnFrameworkSaveButtonAction();
        
        GtnFrameworkSaveButtonAction in = Mockito.spy(instance);
        doNothing().when(in).callProcessMonitorServiceScreen(Mockito.any(String.class), Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
        assertFalse(componentId.isEmpty());
    }

    /**
     * Test of callProcessMonitorServiceScreen method, of class GtnFrameworkSaveButtonAction.
     */
    @Test
    public void testCallProcessMonitorServiceScreen() {
        System.out.println("callProcessMonitorServiceScreen");
        String serviceUrl = GtnWsProcessMonitorConstants.GTN_WS_PROCESS_MONITOR_DELETE_SERVICE;
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class); 
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 
        GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
        GtnFrameworkSaveButtonAction instance=new GtnFrameworkSaveButtonAction();       
        GtnFrameworkSaveButtonAction in = Mockito.spy(instance);
        doNothing().when(in).callProcessMonitorServiceScreen(Mockito.any(String.class), Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        instance.callProcessMonitorServiceScreen(serviceUrl, request);
        assertFalse(serviceUrl.isEmpty());
    }

    /**
     * Test of createInstance method, of class GtnFrameworkSaveButtonAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkSaveButtonAction instance = new GtnFrameworkSaveButtonAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of getDdlbDefaultValue method, of class GtnFrameworkSaveButtonAction.
     */
    @Test
    public void testGetDdlbDefaultValue() {
    try{
        System.out.println("getDdlbDefaultValue");
        String value = "the";
        GtnFrameworkSaveButtonAction instance = new GtnFrameworkSaveButtonAction();
        Method method = instance.getClass().getDeclaredMethod("getDdlbDefaultValue",String.class);
        method.setAccessible(true);
        method.invoke(instance, value);
        assertFalse(value.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkSaveButtonActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
