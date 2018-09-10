/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.contractheader.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnwsContractAliasMasterBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractHeaderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractHeaderResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asi.container.ExtContainer;
import org.junit.After;
import org.junit.AfterClass;
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
public class GtnUIFrameworkContractHeaderSaveActionTest {
    
    public GtnUIFrameworkContractHeaderSaveActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkContractHeaderSaveAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkContractHeaderSaveAction instance = new GtnUIFrameworkContractHeaderSaveAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnUIFrameworkContractHeaderSaveAction.
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
        GtnUIFrameworkContractHeaderSaveAction instance = new GtnUIFrameworkContractHeaderSaveAction();
        GtnUIFrameworkContractHeaderSaveAction in = Mockito.spy(instance);
        doReturn(newResponse).when(in).callContractHeaderSaveService(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        try{
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
               
        } catch (GtnFrameworkGeneralException e) {
			
	}
    }

    /**
     * Test of setProperties method, of class GtnUIFrameworkContractHeaderSaveAction.
     */
    @Test
    public void testSetProperties() throws Exception {
        System.out.println("setProperties");
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
        GtnUIFrameworkContractHeaderSaveAction instance = new GtnUIFrameworkContractHeaderSaveAction();

        try{
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
               
        } catch (GtnFrameworkGeneralException e) {
			
	}
    }

    /**
     * Test of loadNotesTab method, of class GtnUIFrameworkContractHeaderSaveAction.
     */
    @Test
    public void testLoadNotesTab() throws Exception {
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
        doReturn("").when(tableBaseComponent).getValueFromComponent();
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        when(GtnUIFrameworkGlobalUI.getSessionProperty("contractMasterSid")).thenReturn(1);
        GtnUIFrameworkWebserviceResponse newResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractHeaderResponse rbNewResponse = new GtnWsContractHeaderResponse();
        newResponse.setGtnWsContractHeaderResponse(rbNewResponse);
        newResponse.getGtnWsContractHeaderResponse().isContractMasterUsed();
        gtnUIFrameWorkActionConfig.setActionParameterList(obj);
        GtnUIFrameworkContractHeaderSaveAction instance = new GtnUIFrameworkContractHeaderSaveAction();
        List<NotesTabBean> noteBeanList=new ArrayList<>();
        List<NotesDTO> notesDTOs=new ArrayList<>();
        try{
        Method method = instance.getClass().getDeclaredMethod("loadNotesTab",List.class,List.class);
        method.setAccessible(true);
        method.invoke(instance, noteBeanList, notesDTOs);
        //instance.loadNotesTab(noteBeanList, notesDTOs);
         assertFalse(!noteBeanList.isEmpty());
           } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkContractHeaderSaveActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getAliasTabList method, of class GtnUIFrameworkContractHeaderSaveAction.
     */
    @Test
    public void testGetAliasTabList() throws Exception {
        System.out.println("getAliasTabList");
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
        GtnUIFrameworkContractHeaderSaveAction instance = new GtnUIFrameworkContractHeaderSaveAction();
List<GtnWsRecordBean> identifierList=new ArrayList<>();
List<GtnwsContractAliasMasterBean> aliasBeanList=new ArrayList<>();
        try{
        Method method = instance.getClass().getDeclaredMethod("getAliasTabList",List.class,List.class);
        method.setAccessible(true);
        method.invoke(instance, identifierList, aliasBeanList);
        //instance.getAliasTabList(identifierList, aliasBeanList);
        assertFalse(!identifierList.isEmpty());      
         } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkContractHeaderSaveActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of loadTabAfterSave method, of class GtnUIFrameworkContractHeaderSaveAction.
     */
 

    /**
     * Test of loadIdentifierTab method, of class GtnUIFrameworkContractHeaderSaveAction.
     */
    @Test
    public void testLoadIdentifierTab() throws Exception {
        System.out.println("loadIdentifierTab");
        List<GtnwsContractAliasMasterBean> aliasSaveList = new ArrayList<>();
        ExtContainer<GtnWsRecordBean> container = null;
        GtnUIFrameworkContractHeaderSaveAction instance = new GtnUIFrameworkContractHeaderSaveAction();
        Method method = instance.getClass().getDeclaredMethod("loadIdentifierTab",List.class,ExtContainer.class);
        method.setAccessible(true);
        method.invoke(instance, aliasSaveList, container);
        assertFalse(!aliasSaveList.isEmpty());
    }

    /**
     * Test of createInstance method, of class GtnUIFrameworkContractHeaderSaveAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkContractHeaderSaveAction instance = new GtnUIFrameworkContractHeaderSaveAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of setNumericFieldValues method, of class GtnUIFrameworkContractHeaderSaveAction.
     */
    @Test
    public void testSetNumericFieldValues() throws Exception {
        System.out.println("setNumericFieldValues");
        GtnWsContractMasterBean contractMasterBean = new GtnWsContractMasterBean();
        GtnUIFrameworkContractHeaderSaveAction instance = new GtnUIFrameworkContractHeaderSaveAction();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        instance.setNumericFieldValues(contractMasterBean);
        assertFalse(!contractMasterBean.getContractNo().isEmpty());
    }
    
}
