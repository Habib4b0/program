/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.engine.session.GtnUIFrameworkSessionBean;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsCffOutBoundBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import de.steinwedel.messagebox.MessageBox;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class,GtnUIFrameworkComponentData.class,GtnUIFrameworkSessionBean.class,GtnUIFrameworkPagedTableLogic.class,GtnUIFrameworkActionParameter.class,GtnUIFrameworkComponentConfig.class,GtnUIFrameworkComponentType.class,GtnUIFrameworkComponent.class})
public class GtnFrameworkCheckAllRecordActionTest {
    
    public GtnFrameworkCheckAllRecordActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkCheckAllRecordAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkCheckAllRecordAction instance = new GtnFrameworkCheckAllRecordAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnFrameworkCheckAllRecordAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "V009";
    
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class,GtnUIFrameworkComponentData.class,GtnUIFrameworkSessionBean.class,GtnUIFrameworkPagedTableLogic.class,GtnUIFrameworkComponentConfig.class,GtnUIFrameworkComponentType.class,GtnUIFrameworkComponent.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 

        
        when(object.getTableColumnCheckboxValue(Mockito.anyString())).thenReturn(Boolean.FALSE);
        
        
        when(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")).thenReturn(1);
        
        when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("hg");

        GtnFrameworkCheckAllRecordAction instance=new GtnFrameworkCheckAllRecordAction();
        
        
        GtnFrameworkCheckAllRecordAction in = Mockito.spy(instance);
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse gtnWsGeneralResponse=new GtnWsGeneralResponse();
        
        gtnWsGeneralResponse.setSucess(true);
        
        gtnWsGeneralResponse.isSucess();
        
        gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
        
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callProcessSchedularCffOutbound(Mockito.any(GtnUIFrameworkWebserviceRequest.class));

        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
        assertFalse(componentId.isEmpty());
    }

    /**
     * Test of updateField method, of class GtnFrameworkCheckAllRecordAction.
     */
    @Test
    public void testUpdateField() {
        try{
        System.out.println("updateField");
        String componentId = "V009";
    
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class,GtnUIFrameworkComponentData.class,GtnUIFrameworkSessionBean.class,GtnUIFrameworkPagedTableLogic.class,GtnUIFrameworkComponentConfig.class,GtnUIFrameworkComponentType.class,GtnUIFrameworkComponent.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 

        
        when(object.getTableColumnCheckboxValue(Mockito.anyString())).thenReturn(Boolean.FALSE);
        
        
        when(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")).thenReturn(1);
        
        when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("hg");

        GtnFrameworkCheckAllRecordAction instance=new GtnFrameworkCheckAllRecordAction();
        
        
        GtnFrameworkCheckAllRecordAction in = Mockito.spy(instance);
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse gtnWsGeneralResponse=new GtnWsGeneralResponse();
        
        gtnWsGeneralResponse.setSucess(true);
        
        gtnWsGeneralResponse.isSucess();
        
        gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
        
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callProcessSchedularCffOutbound(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        GtnWsCffOutBoundBean gtnCffOutBoundBean = new GtnWsCffOutBoundBean();
        
        Method method = instance.getClass().getDeclaredMethod("updateField",GtnWsCffOutBoundBean.class);
        method.setAccessible(true);
        method.invoke(instance, gtnCffOutBoundBean);
        assertFalse(componentId.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCheckAllRecordActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Test of createInstance method, of class GtnFrameworkCheckAllRecordAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkCheckAllRecordAction instance = new GtnFrameworkCheckAllRecordAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
