/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.contractheader.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
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
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class})
public class GtnUIFrameworkContractHeaderAddActionTest {
    
    public GtnUIFrameworkContractHeaderAddActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkContractHeaderAddAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkContractHeaderAddAction instance = new GtnUIFrameworkContractHeaderAddAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnUIFrameworkContractHeaderAddAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "";
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        List<Object> obj=Arrays.asList("0","1","2","3","4","5","6","7");
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doNothing().when(tableBaseComponent).setComponentEnable(Mockito.anyBoolean());
        doReturn("").when(tableBaseComponent).getValueFromComponent();
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        
        gtnUIFrameWorkActionConfig.setActionParameterList(obj);
        GtnUIFrameworkContractHeaderAddAction instance = new GtnUIFrameworkContractHeaderAddAction();
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnUIFrameworkContractHeaderAddAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkContractHeaderAddAction instance = new GtnUIFrameworkContractHeaderAddAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of setValueToComponents method, of class GtnUIFrameworkContractHeaderAddAction.
     */
    @Test
    public void testSetValueToComponents() {
        System.out.println("setValueToComponents");
        String componentId = "setValueToComponents";
        GtnUIFrameworkContractHeaderAddAction instance = new GtnUIFrameworkContractHeaderAddAction();
        instance.setValueToComponents(componentId);
        assertFalse(componentId.isEmpty());
    }

    /**
     * Test of setEditViewProperties method, of class GtnUIFrameworkContractHeaderAddAction.
     */
    @Test
    public void testSetEditViewProperties() {
        try{
        System.out.println("setEditViewProperties");
        boolean value = true;
        GtnUIFrameworkContractHeaderAddAction instance = new GtnUIFrameworkContractHeaderAddAction();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        try {
            doReturn("").when(tableBaseComponent).getValueFromComponent();
        } catch (GtnFrameworkValidationFailedException ex) {
            Logger.getLogger(GtnUIFrameworkContractHeaderAddActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        Method method = instance.getClass().getDeclaredMethod("setEditViewProperties",boolean.class);
        method.setAccessible(true);
        method.invoke(instance, value);
         } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkContractHeaderAddActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        //instance.setEditViewProperties(value);
    }

    /**
     * Test of getDefaultValueActionConfig method, of class GtnUIFrameworkContractHeaderAddAction.
     */
    @Test
    public void testGetDefaultValueActionConfig() {
        try{
        System.out.println("getDefaultValueActionConfig");
        GtnWsContractMasterBean info = new GtnWsContractMasterBean();
        info.setContractId("Value");
        info.setContractNo("fsfs");
        info.setContractName("Sfss");
        info.setContractType(1);
        info.setContractStatus(4);
        info.setDocumentType(4);
        info.setStartDate(new Date());
        info.setEndDate(new Date());
        info.getDocumentClass();
        info.setContractTradeClass(2);             
        info.setRenegotiationStartDate(new Date());
        info.setRenegotiationEndDate(new Date());
        GtnUIFrameworkContractHeaderAddAction instance = new GtnUIFrameworkContractHeaderAddAction();
        Method method = instance.getClass().getDeclaredMethod("getDefaultValueActionConfig",GtnWsContractMasterBean.class);
        method.setAccessible(true);
        method.invoke(instance,info);
        assertFalse(info.getContractName().isEmpty());
         } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkContractHeaderAddActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
}
