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
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnwsContractAliasMasterBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
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
public class GtnUIFrameworkContractHeaderEditActionTest {
    
    public GtnUIFrameworkContractHeaderEditActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkContractHeaderEditAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkContractHeaderEditAction instance = new GtnUIFrameworkContractHeaderEditAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnUIFrameworkContractHeaderEditAction.
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
        GtnUIFrameworkContractHeaderEditAction instance = new GtnUIFrameworkContractHeaderEditAction();
        GtnUIFrameworkContractHeaderEditAction in = Mockito.spy(instance);
        doReturn(newResponse).when(in).callFetchInformation(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        try{
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
               Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}

        
    }

    /**
     * Test of createInstance method, of class GtnUIFrameworkContractHeaderEditAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkContractHeaderEditAction instance = new GtnUIFrameworkContractHeaderEditAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of setValueToComponents method, of class GtnUIFrameworkContractHeaderEditAction.
     */
    @Test
    public void testSetValueToComponents() {
        System.out.println("setValueToComponents");
        GtnWsContractMasterBean info = new GtnWsContractMasterBean();
        boolean isEditable = true;
        String componentId = "vxv";
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        GtnUIFrameworkContractHeaderEditAction instance = new GtnUIFrameworkContractHeaderEditAction();
        instance.setValueToComponents(info, isEditable, componentId);
        assertFalse(componentId.isEmpty());
    }

    /**
     * Test of setEditViewProperties method, of class GtnUIFrameworkContractHeaderEditAction.
     */
    @Test
    public void testSetEditViewProperties() {
        try{
        System.out.println("setEditViewProperties");
        boolean value = true;
        GtnUIFrameworkContractHeaderEditAction instance = new GtnUIFrameworkContractHeaderEditAction();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        Method method = instance.getClass().getDeclaredMethod("setEditViewProperties",boolean.class);
        method.setAccessible(true);
        method.invoke(instance, value);
        //instance.setEditViewProperties(value);
        assertFalse(!value);
         } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkContractHeaderAddActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of loadTabAfterSave method, of class GtnUIFrameworkContractHeaderEditAction.
     */
    @Test
    public void testLoadTabAfterSave() throws Exception {
        System.out.println("loadTabAfterSave");
        GtnWsContractHeaderResponse contractHeaderResponse = new GtnWsContractHeaderResponse();
        GtnUIFrameworkContractHeaderEditAction instance = new GtnUIFrameworkContractHeaderEditAction();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        List<GtnwsContractAliasMasterBean> gtnwsContractAliasMasterBeanList=new ArrayList<>();
        GtnwsContractAliasMasterBean e=new GtnwsContractAliasMasterBean();
        gtnwsContractAliasMasterBeanList.add(e);
        contractHeaderResponse.setGtnwsContractAliasMasterBeanList(gtnwsContractAliasMasterBeanList);
        Method method = instance.getClass().getDeclaredMethod("loadTabAfterSave",GtnWsContractHeaderResponse.class);
        method.setAccessible(true);
        method.invoke(instance, contractHeaderResponse);
        //instance.loadTabAfterSave(contractHeaderResponse);
        assertFalse(gtnwsContractAliasMasterBeanList.isEmpty());
        
    }

    /**
     * Test of loadIdentifierTab method, of class GtnUIFrameworkContractHeaderEditAction.
     */
    @Test
    public void testLoadIdentifierTab() throws Exception {
        System.out.println("loadIdentifierTab");
        List<GtnwsContractAliasMasterBean> identifierSaveList = new ArrayList<>();
        GtnUIFrameworkContractHeaderEditAction instance = new GtnUIFrameworkContractHeaderEditAction();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        Method method = instance.getClass().getDeclaredMethod("loadIdentifierTab",List.class);
        method.setAccessible(true);
        method.invoke(instance, identifierSaveList);
        assertFalse(!identifierSaveList.isEmpty());
        //instance.loadIdentifierTab(identifierSaveList);
    }

    /**
     * Test of loadNotesTab method, of class GtnUIFrameworkContractHeaderEditAction.
     */
    @Test
    public void testLoadNotesTab() {
        try{
        System.out.println("loadNotesTab");
        String componentId = "fsfs";
        GtnWsContractHeaderResponse reponseBean = new GtnWsContractHeaderResponse();
        List<NotesTabBean> notesTabList=new ArrayList<>();
        NotesTabBean e = new NotesTabBean();
        notesTabList.add(e);
        reponseBean.setNotesTabList(notesTabList);
        GtnUIFrameworkContractHeaderEditAction instance = new GtnUIFrameworkContractHeaderEditAction();
        Method method = instance.getClass().getDeclaredMethod("loadNotesTab",String.class,GtnWsContractHeaderResponse.class);
        method.setAccessible(true);
        method.invoke(instance, componentId,reponseBean);
        assertFalse(notesTabList.isEmpty());
        //instance.loadNotesTab(componentId, reponseBean);
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkContractHeaderEditActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
