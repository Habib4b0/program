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
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.processscheduler.GtnWsProcessSchedulerRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class,GtnUIFrameworkComponentData.class,GtnUIFrameworkSessionBean.class,GtnUIFrameworkPagedTableLogic.class,GtnUIFrameworkActionParameter.class,GtnUIFrameworkComponentConfig.class,GtnUIFrameworkComponentType.class,GtnUIFrameworkComponent.class,GtnUIFrameworkComponentData.class,GtnUIFrameworkWebserviceResponse.class,GtnWsGeneralResponse.class,GtnWsProcessSchedulerRequest.class,GtnUIFrameWorkActionConfig.class})
public class GtnFrameworkMandatoryFieldSettingActionTest {
    
    public GtnFrameworkMandatoryFieldSettingActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkMandatoryFieldSettingAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkMandatoryFieldSettingAction instance = new GtnFrameworkMandatoryFieldSettingAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnFrameworkMandatoryFieldSettingAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "V009";
    
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class,GtnUIFrameworkComponentData.class,GtnUIFrameworkSessionBean.class,GtnUIFrameworkPagedTableLogic.class,GtnUIFrameworkComponentConfig.class,GtnUIFrameworkComponentType.class,GtnUIFrameworkComponent.class,GtnUIFrameworkComponentData.class,GtnUIFrameworkWebserviceResponse.class,GtnWsGeneralResponse.class,GtnWsProcessSchedulerRequest.class,GtnUIFrameWorkActionConfig.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        gtnUIFrameWorkActionConfig.addActionParameter("1");
        gtnUIFrameWorkActionConfig.addActionParameter("1");
        gtnUIFrameWorkActionConfig.addActionParameter("1");
        gtnUIFrameWorkActionConfig.addActionParameter("1");
        gtnUIFrameWorkActionConfig.addActionParameter("1");
        gtnUIFrameWorkActionConfig.addActionParameter("1");
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        
        GtnUIFrameworkComponentConfig componentConfig= Mockito.mock(GtnUIFrameworkComponentConfig.class);
        
        GtnUIFrameworkComponentData gtnUIFrameworkComponentData = Mockito.mock(GtnUIFrameworkComponentData.class);

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 

        when(object.getComponentData()).thenReturn(gtnUIFrameworkComponentData); 
        
        when(object.getComponentConfig()).thenReturn(componentConfig);
        
        List<GtnUIFrameWorkActionConfig> actionConfigs=new ArrayList<>();
        
        actionConfigs.add(gtnUIFrameWorkActionConfig);
        
        when(componentConfig.getGtnUIFrameWorkActionConfigList()).thenReturn(actionConfigs);
        
        actionConfigs.add(gtnUIFrameWorkActionConfig);
     
        List<Object> list=new ArrayList<>();
        
        list.add(1);
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        
        
        when(gtnUIFrameworkComponentData.getCustomData()).thenReturn(list); 
        
        GtnFrameworkMandatoryFieldSettingAction instance=new GtnFrameworkMandatoryFieldSettingAction();
        
        
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse gtnWsGeneralResponse=new GtnWsGeneralResponse();
        
        gtnWsGeneralResponse.setSucess(true);
        
        gtnWsGeneralResponse.isSucess();
        
        gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
        
        GtnUIFrameworkWebserviceResponse webServiceResponse=Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
        GtnWsGeneralResponse generalResponse=Mockito.mock(GtnWsGeneralResponse.class);
        when(webServiceResponse.getGtnWsGeneralResponse()).thenReturn(generalResponse); 
        when(generalResponse.isSucess()).thenReturn(true); 
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
        assertFalse(componentId.isEmpty());
    }

    /**
     * Test of createInstance method, of class GtnFrameworkMandatoryFieldSettingAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkMandatoryFieldSettingAction instance = new GtnFrameworkMandatoryFieldSettingAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
