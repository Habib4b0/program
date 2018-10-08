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
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.engine.session.GtnUIFrameworkSessionBean;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import de.steinwedel.messagebox.MessageBox;
import java.util.Arrays;
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
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class,GtnUIFrameworkComponentData.class,GtnUIFrameworkSessionBean.class,GtnUIFrameworkPagedTableLogic.class,GtnUIFrameworkActionParameter.class})
public class GtnFrameworkCffOutBoundTablefieldFactoryActionTest {
    
    public GtnFrameworkCffOutBoundTablefieldFactoryActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkCffOutBoundTablefieldFactoryAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkCffOutBoundTablefieldFactoryAction instance = new GtnFrameworkCffOutBoundTablefieldFactoryAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnFrameworkCffOutBoundTablefieldFactoryAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "V009";
    
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class,GtnUIFrameworkComponentData.class,GtnUIFrameworkSessionBean.class,GtnUIFrameworkPagedTableLogic.class,GtnUIFrameworkActionParameter.class); 
        
        GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
        
        when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

        when(GtnUIFrameworkGlobalUI.getVaadinFieldFactoryComponentData(Mockito.anyString())).thenReturn(componentData); 
        
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        
        gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList("1","2","2","2","2","2","2","2","2","2","2","2","2","2"));
        GtnUIFrameworkActionParameter actionParameter = new GtnUIFrameworkActionParameter();
        
        doReturn(actionParameter).when(componentData).getActionParameter();


        GtnWsRecordBean itemId = new GtnWsRecordBean();
        itemId.addProperties(GtnFrameworkProcessSchedulerStringContants.RS_MODEL_SID);
        itemId.addProperties(GtnFrameworkProcessSchedulerStringContants.PERIOD_SID);
        itemId.addProperties(2);
        itemId.addProperties(2);
        itemId.addProperties("2");
        itemId.addProperties("2");
        itemId.addProperties("2");
        itemId.addProperties("2");
        itemId.addProperties("2");
        itemId.addProperties("2");
        itemId.addProperties("2");
        itemId.addProperties(false);
        itemId.setRecordHeader(Arrays.asList(2,2,2,2,"2","2","2","2","2","2","2",false));
        
        
        
        actionParameter.setItemId(itemId);
        
        actionParameter.setCurrentValue(false);
        
        componentData.setActionParameter(actionParameter);
        
        GtnFrameworkCffOutBoundTablefieldFactoryAction instance=new GtnFrameworkCffOutBoundTablefieldFactoryAction();
        
        GtnFrameworkCffOutBoundTablefieldFactoryAction in = Mockito.spy(instance);
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
        
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callProcessSchedularCffOutboundService(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        

        in.doAction(componentId, gtnUIFrameWorkActionConfig);
        assertFalse(componentId.isEmpty());
    }

    /**
     * Test of createInstance method, of class GtnFrameworkCffOutBoundTablefieldFactoryAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkCffOutBoundTablefieldFactoryAction instance = new GtnFrameworkCffOutBoundTablefieldFactoryAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
