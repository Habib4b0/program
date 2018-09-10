/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.contractheader.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderEditAction;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractHeaderResponse;
import java.util.Arrays;
import java.util.List;
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
public class GtnUIFrameworkContractHeaderCommonValidationActionTest {
    
    public GtnUIFrameworkContractHeaderCommonValidationActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkContractHeaderCommonValidationAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkContractHeaderCommonValidationAction instance = new GtnUIFrameworkContractHeaderCommonValidationAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnUIFrameworkContractHeaderCommonValidationAction.
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
        GtnUIFrameworkContractHeaderCommonValidationAction instance = new GtnUIFrameworkContractHeaderCommonValidationAction();
        GtnUIFrameworkContractHeaderCommonValidationAction in = Mockito.spy(instance);
        doReturn(newResponse).when(in).callContractHeaderValidationService(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        try{
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
        } catch (GtnFrameworkGeneralException e) {
			
	}

    }

    /**
     * Test of createInstance method, of class GtnUIFrameworkContractHeaderCommonValidationAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkContractHeaderCommonValidationAction instance = new GtnUIFrameworkContractHeaderCommonValidationAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
