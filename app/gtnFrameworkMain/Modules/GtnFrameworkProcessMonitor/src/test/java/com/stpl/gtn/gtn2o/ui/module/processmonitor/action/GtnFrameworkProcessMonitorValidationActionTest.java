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
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.processmonitor.constants.GtnFrameworkProcessMonitorStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.Date;
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
public class GtnFrameworkProcessMonitorValidationActionTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    public GtnFrameworkProcessMonitorValidationActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkProcessMonitorValidationAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkProcessMonitorValidationAction instance = new GtnFrameworkProcessMonitorValidationAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnFrameworkProcessMonitorValidationAction.
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
        
        GtnFrameworkProcessMonitorValidationAction instance=new GtnFrameworkProcessMonitorValidationAction();
        
        GtnFrameworkProcessMonitorValidationAction in = Mockito.spy(instance);
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callProcessMonitorServiceScreen(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        thrown.expect(GtnFrameworkGeneralException.class);
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
        assertFalse(componentId.isEmpty());
    }

    /**
     * Test of processTypeAutomaticForUpdate method, of class GtnFrameworkProcessMonitorValidationAction.
     */
    @Test
    public void testProcessTypeAutomaticForUpdate() throws Exception {
        System.out.println("processTypeAutomaticForUpdate");
        String componentId = "V009";

        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class); 
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn("addProcessName").when(object).getStringFromField();
        doReturn("addProcess").when(object).getCaptionFromComboBox();
        doReturn(new Date()).when(object).getDateFromDateField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        
        GtnFrameworkProcessMonitorValidationAction instance = new GtnFrameworkProcessMonitorValidationAction();
        instance.processTypeAutomaticForUpdate(componentId);
        assertFalse(componentId.isEmpty());
    }

    /**
     * Test of createInstance method, of class GtnFrameworkProcessMonitorValidationAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkProcessMonitorValidationAction instance = new GtnFrameworkProcessMonitorValidationAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of callAlertAction method, of class GtnFrameworkProcessMonitorValidationAction.
     */
    @Test
    public void testCallAlertAction() throws Exception {
        System.out.println("callAlertAction");
        String message = "callAlertAction";
        String componentId = "V009";
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class); 
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn("addProcessName").when(object).getStringFromField();
        doReturn("addProcess").when(object).getCaptionFromComboBox();
        doReturn(new Date()).when(object).getDateFromDateField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        
        
        GtnFrameworkProcessMonitorValidationAction instance = new GtnFrameworkProcessMonitorValidationAction();
        thrown.expect(GtnFrameworkValidationFailedException.class);
        instance.callAlertAction(message, componentId);
        assertFalse(componentId.isEmpty());
    }

    /**
     * Test of duplicateProcessName method, of class GtnFrameworkProcessMonitorValidationAction.
     */
    @Test
    public void testDuplicateProcessName() throws Exception {
        System.out.println("duplicateProcessName");
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
        
        GtnFrameworkProcessMonitorValidationAction instance=new GtnFrameworkProcessMonitorValidationAction();
        
        GtnFrameworkProcessMonitorValidationAction in = Mockito.spy(instance);
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callProcessMonitorServiceScreen(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        thrown.expect(GtnFrameworkGeneralException.class);
        instance.duplicateProcessName(componentId);
        assertFalse(componentId.isEmpty());
    }

    /**
     * Test of processTypeAutomatic method, of class GtnFrameworkProcessMonitorValidationAction.
     */
    @Test
    public void testProcessTypeAutomatic() throws Exception {
        System.out.println("duplicateProcessName");
        String componentId = "V009";
    
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_AUTOMATIC).when(object).getStringFromField();
        doReturn(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_AUTOMATIC).when(object).getCaptionFromComboBox();
        doReturn(new Date()).when(object).getDateFromDateField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        
        GtnFrameworkProcessMonitorValidationAction instance=new GtnFrameworkProcessMonitorValidationAction();
        
        GtnFrameworkProcessMonitorValidationAction in = Mockito.spy(instance);
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();

        instance.processTypeAutomatic(componentId);
        assertFalse(componentId.isEmpty());
    }
    
}
