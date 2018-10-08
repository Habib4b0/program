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
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.engine.session.GtnUIFrameworkSessionBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import de.steinwedel.messagebox.MessageBox;
import java.util.Date;
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
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class,GtnUIFrameworkComponentData.class,GtnUIFrameworkSessionBean.class,GtnUIFrameworkPagedTableLogic.class})
public class GtnFrameworkAdditionalSearchCriteriaActionTest {
    
    public GtnFrameworkAdditionalSearchCriteriaActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkAdditionalSearchCriteriaAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkAdditionalSearchCriteriaAction instance = new GtnFrameworkAdditionalSearchCriteriaAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnFrameworkAdditionalSearchCriteriaAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "V009";
    
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class,GtnUIFrameworkComponentData.class,GtnUIFrameworkSessionBean.class,GtnUIFrameworkPagedTableLogic.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);
        
        when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.any())).thenReturn(componentData); 
        
        when(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID)).thenReturn(1);
        
        GtnUIFrameworkPagedTableLogic tableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
        
        doReturn(tableLogic).when(componentData).getCurrentPageTableLogic();
 

        GtnUIFrameworkSessionBean bean = Mockito.mock(GtnUIFrameworkSessionBean.class);
        when(GtnUIFrameworkGlobalUI.getCurrentSessionBean()).thenReturn(bean);
        doReturn("").when(bean).getSessionId();
        doReturn("").when(bean).getUserId();
        
        GtnFrameworkAdditionalSearchCriteriaAction instance=new GtnFrameworkAdditionalSearchCriteriaAction();

        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
        assertFalse(componentId.isEmpty());
    }

    /**
     * Test of createInstance method, of class GtnFrameworkAdditionalSearchCriteriaAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkAdditionalSearchCriteriaAction instance = new GtnFrameworkAdditionalSearchCriteriaAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
