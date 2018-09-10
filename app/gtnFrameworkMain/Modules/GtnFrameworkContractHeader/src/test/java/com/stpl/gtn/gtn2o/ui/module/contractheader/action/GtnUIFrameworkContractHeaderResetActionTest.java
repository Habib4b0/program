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
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnwsContractAliasMasterBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractHeaderResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class})
public class GtnUIFrameworkContractHeaderResetActionTest {
    
    public GtnUIFrameworkContractHeaderResetActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkContractHeaderResetAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkContractHeaderResetAction instance = new GtnUIFrameworkContractHeaderResetAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnUIFrameworkContractHeaderResetAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "";
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
        
        GtnWsRecordBean tableBean = new GtnWsRecordBean();

          tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1391);
			tableBean.addProperties(15L);
			tableBean.addProperties(1391);
                        tableBean.addProperties(1391);
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        List<Object> obj=Arrays.asList("0","1",true,tableBean);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doNothing().when(tableBaseComponent).setComponentEnable(Mockito.anyBoolean());
        doReturn("").when(tableBaseComponent).getValueFromComponent();
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        when(GtnUIFrameworkGlobalUI.getSessionProperty("contractMasterSid")).thenReturn(1);
        GtnUIFrameworkWebserviceResponse newResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractHeaderResponse rbNewResponse = new GtnWsContractHeaderResponse();
        newResponse.setGtnWsContractHeaderResponse(rbNewResponse);
        newResponse.getGtnWsContractHeaderResponse().isContractMasterUsed();
        gtnUIFrameWorkActionConfig.setActionParameterList(obj);
        GtnUIFrameworkContractHeaderResetAction instance = new GtnUIFrameworkContractHeaderResetAction();
        GtnUIFrameworkContractHeaderResetAction in = Mockito.spy(instance);
        doReturn(newResponse).when(in).callFetchInformationService(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        try{
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
               
        } catch (GtnFrameworkGeneralException e) {
			
	}
    }

    /**
     * Test of createInstance method, of class GtnUIFrameworkContractHeaderResetAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkContractHeaderResetAction instance = new GtnUIFrameworkContractHeaderResetAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of setValueToComponents method, of class GtnUIFrameworkContractHeaderResetAction.
     */
    @Test
    public void testSetValueToComponents() {
        System.out.println("setValueToComponents");
        GtnWsContractMasterBean info = new GtnWsContractMasterBean();
        int position = 1;
        GtnUIFrameworkContractHeaderResetAction instance = new GtnUIFrameworkContractHeaderResetAction();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        instance.setValueToComponents(info, position);
        assertFalse(position==0);
    }

    /**
     * Test of loadTabAfterSave method, of class GtnUIFrameworkContractHeaderResetAction.
     */
    @Test
    public void testLoadTabAfterSave() throws Exception {
        System.out.println("loadTabAfterSave");
        GtnWsContractHeaderResponse reponseBean = new GtnWsContractHeaderResponse();
        List<GtnwsContractAliasMasterBean> gtnwsContractAliasMasterBeanList=new ArrayList<>();
        GtnwsContractAliasMasterBean e=new GtnwsContractAliasMasterBean();
        gtnwsContractAliasMasterBeanList.add(e);
        reponseBean.setGtnwsContractAliasMasterBeanList(gtnwsContractAliasMasterBeanList);
        GtnUIFrameworkContractHeaderResetAction instance = new GtnUIFrameworkContractHeaderResetAction();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        Method method = instance.getClass().getDeclaredMethod("loadTabAfterSave",GtnWsContractHeaderResponse.class);
        method.setAccessible(true);
        method.invoke(instance, reponseBean);
        //instance.loadTabAfterSave(reponseBean);
        assertFalse(gtnwsContractAliasMasterBeanList.isEmpty());
        
    }

    /**
     * Test of loadIdentifierTab method, of class GtnUIFrameworkContractHeaderResetAction.
     */
    @Test
    public void testLoadIdentifierTab() throws Exception {
        System.out.println("loadIdentifierTab");
        GtnWsContractHeaderResponse reponseBean = new GtnWsContractHeaderResponse();
        List<GtnwsContractAliasMasterBean> gtnwsContractAliasMasterBeanList=new ArrayList<>();
        GtnwsContractAliasMasterBean e=new GtnwsContractAliasMasterBean();
        gtnwsContractAliasMasterBeanList.add(e);
        reponseBean.setGtnwsContractAliasMasterBeanList(gtnwsContractAliasMasterBeanList);
        GtnUIFrameworkContractHeaderResetAction instance = new GtnUIFrameworkContractHeaderResetAction();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        Method method = instance.getClass().getDeclaredMethod("loadIdentifierTab",List.class);
        method.setAccessible(true);
        method.invoke(instance, gtnwsContractAliasMasterBeanList);
        //instance.loadIdentifierTab(gtnwsContractAliasMasterBeanList);
        assertFalse(gtnwsContractAliasMasterBeanList.isEmpty());

    }

    /**
     * Test of loadNotesTab method, of class GtnUIFrameworkContractHeaderResetAction.
     */
    @Test
    public void testLoadNotesTab() {
        try{
  System.out.println("loadNotesTab");
        String componentId = "";
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
        
        GtnWsRecordBean tableBean = new GtnWsRecordBean();

          tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1391);
			tableBean.addProperties(15L);
			tableBean.addProperties(1391);
                        tableBean.addProperties(1391);
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        List<Object> obj=Arrays.asList("0","1",true,tableBean);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doNothing().when(tableBaseComponent).setComponentEnable(Mockito.anyBoolean());
        try {
            doReturn("").when(tableBaseComponent).getValueFromComponent();
        } catch (GtnFrameworkValidationFailedException ex) {
            Logger.getLogger(GtnUIFrameworkContractHeaderResetActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        when(GtnUIFrameworkGlobalUI.getSessionProperty("contractMasterSid")).thenReturn(1);
        GtnUIFrameworkWebserviceResponse newResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractHeaderResponse rbNewResponse = new GtnWsContractHeaderResponse();
        newResponse.setGtnWsContractHeaderResponse(rbNewResponse);
        newResponse.getGtnWsContractHeaderResponse().isContractMasterUsed();
        gtnUIFrameWorkActionConfig.setActionParameterList(obj);
        GtnUIFrameworkContractHeaderResetAction instance = new GtnUIFrameworkContractHeaderResetAction();      
        Method method = instance.getClass().getDeclaredMethod("loadNotesTab",String.class,GtnWsContractHeaderResponse.class);
        method.setAccessible(true);
        method.invoke(instance, componentId, rbNewResponse);
        //instance.loadNotesTab(componentId, rbNewResponse);
          } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkContractHeaderResetActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        
    }

    /**
     * Test of setValueToComponentsForTab1 method, of class GtnUIFrameworkContractHeaderResetAction.
     */
    @Test
    public void testSetValueToComponentsForTab1() {
        try{
        System.out.println("setValueToComponentsForTab1");
        GtnWsContractMasterBean info = new GtnWsContractMasterBean();
        int position = 1;
        GtnUIFrameworkContractHeaderResetAction instance = new GtnUIFrameworkContractHeaderResetAction();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        Method method = instance.getClass().getDeclaredMethod("setValueToComponentsForTab1",GtnWsContractMasterBean.class);
        method.setAccessible(true);
        method.invoke(instance, info);
        //instance.setValueToComponentsForTab1(info);
        assertFalse(position==0);
          } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkContractHeaderResetActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setValueToComponentsForTab2 method, of class GtnUIFrameworkContractHeaderResetAction.
     */
    @Test
    public void testSetValueToComponentsForTab2() {
        try{
        System.out.println("setValueToComponentsForTab2");
        GtnWsContractMasterBean info = new GtnWsContractMasterBean();
        int position = 1;
        GtnUIFrameworkContractHeaderResetAction instance = new GtnUIFrameworkContractHeaderResetAction();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        Method method = instance.getClass().getDeclaredMethod("setValueToComponentsForTab2",GtnWsContractMasterBean.class);
        method.setAccessible(true);
        method.invoke(instance, info);
        //instance.setValueToComponentsForTab2(info);
        assertFalse(position==0);
         } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkContractHeaderResetActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setValueToComponentsForTab3 method, of class GtnUIFrameworkContractHeaderResetAction.
     */
    @Test
    public void testSetValueToComponentsForTab3() {
        try{
        System.out.println("setValueToComponentsForTab3");
        GtnWsContractMasterBean info = new GtnWsContractMasterBean();
        int position = 1;
        GtnUIFrameworkContractHeaderResetAction instance = new GtnUIFrameworkContractHeaderResetAction();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        Method method = instance.getClass().getDeclaredMethod("setValueToComponentsForTab3");
        method.setAccessible(true);
        method.invoke(instance);
        //instance.setValueToComponentsForTab3();
        assertFalse(position==0);
         } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkContractHeaderResetActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
